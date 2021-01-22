/*
 * Copyright 2020, Ludovic Henry
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.ludovic.netlib.blas;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.util.function.Supplier;

import dev.ludovic.netlib.BLAS;

import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.LibraryLookup;
import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.MemoryHandles;
import jdk.incubator.foreign.MemoryLayouts;
import jdk.incubator.foreign.MemorySegment;

import static jdk.incubator.foreign.CLinker.*;

public final class CudaBLAS extends AbstractBLAS {

  private static final Logger LOGGER = Logger.getLogger(CudaBLAS.class.getName());

  private static final CudaBLAS instance = new CudaBLAS();

  private final LibraryLookup cublas = LibraryLookup.ofLibrary("cublas");
  private final LibraryLookup cudart = LibraryLookup.ofLibrary("cudart");

  private final MethodHandle initHandle =
    CLinker.getInstance().downcallHandle(
      cublas.lookup("cublasInit").get(), MethodType.methodType(int.class),
        FunctionDescriptor.of(C_INT));

  private final MethodHandle allocHandle =
    CLinker.getInstance().downcallHandle(
      cublas.lookup("cublasAlloc").get(), MethodType.methodType(int.class, int.class, int.class, MemoryAddress.class),
        FunctionDescriptor.of(C_INT, C_INT, C_INT, C_POINTER));

  private final MethodHandle freeHandle =
    CLinker.getInstance().downcallHandle(
      cublas.lookup("cublasFree").get(), MethodType.methodType(int.class, MemoryAddress.class),
        FunctionDescriptor.of(C_INT, C_POINTER));

  private final MethodHandle memcpyHandle =
    CLinker.getInstance().downcallHandle(
      cudart.lookup("cudaMemcpy").get(), MethodType.methodType(int.class, MemoryAddress.class, MemoryAddress.class, long.class, int.class),
        FunctionDescriptor.of(C_INT, C_POINTER, C_POINTER, C_LONG, C_INT));

  private final VarHandle addressHandle = MemoryHandles.varHandle(MemoryAddress.class, ByteOrder.nativeOrder());

  protected CudaBLAS() {
    try {
      if ((CublasStatus)initHandle.invoke() != CublasStatus.Success) {
        throw new RuntimeException("Failed to load cublas");
      }
    } catch (RuntimeException e) {
      throw e;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  public static BLAS getInstance() {
    return instance;
  }

  private static enum CudaError {
    Success(0);

    private final int val;

    private CudaError(int val) {
      this.val = val;
    }

    public int value() {
      return val;
    }
  }

  private static enum CudaMemcpyKind {
    HostToHost(0),
    HostToDevice(1),
    DeviceToHost(2),
    DeviceToDevice(3);

    private final int val;

    private CudaMemcpyKind(int val) {
      this.val = val;
    }

    public int value() {
      return val;
    }
  }



  private static enum CublasStatus {
    Success(0);

    private final int val;

    private CublasStatus(int val) {
      this.val = val;
    }

    public int value() {
      return val;
    }
  }

  private static enum CublasOperation {
    NoTrans('N'),
    Trans('T');

    private final char val;

    private CublasOperation(char val) {
      this.val = val;
    }

    public char value() {
      return val;
    }

    public static CublasOperation fromString(String trans) {
      switch (trans) {
        case "N": return NoTrans;
        case "T": return Trans;
        case "C": return Trans;
        default: throw new IllegalArgumentException("unknown trans = " + trans);
      }
    }
  }

  private static enum CublasUPLO {
    Lower('L'),
    Upper('U');

    private final char val;

    private CublasUPLO(char val) {
      this.val = val;
    }

    public char value() {
      return val;
    }

    public static CublasUPLO fromString(String uplo) {
      switch (uplo) {
        case "U": return Upper;
        case "L": return Lower;
        default: throw new IllegalArgumentException("unknown uplo = " + uplo);
      }
    }
  }

  private static enum CublasDiag {
    NonUnit('N'),
    Unit('U');

    private final char val;

    private CublasDiag(char val) {
      this.val = val;
    }

    public char value() {
      return val;
    }
    public static CublasDiag fromString(String diag) {
      switch (diag) {
        case "N": return NonUnit;
        case "U": return Unit;
        default: throw new IllegalArgumentException("unknown diag = " + diag);
      }
    }
  }

  private static enum CublasSide {
    Left('L'),
    Right('R');

    private final char val;

    private CublasSide(char val) {
      this.val = val;
    }

    public char value() {
      return val;
    }

    public static CublasSide fromString(String side) {
      switch (side) {
        case "L": return Left;
        case "R": return Right;
        default: throw new IllegalArgumentException("unknown side = " + side);
      }
    }
  }

  private class MemoryNativeCopy implements AutoCloseable {

    private final MemorySegment source;
    private final boolean copyBack;
    private final MemorySegment memory;
    private final boolean copied;

    public MemoryNativeCopy(MemorySegment source, boolean copyBack) {
      this.source = source;
      this.copyBack = copyBack;
      // if (source.isNative()) {
      //   // The source memory segment is already native memory, so no need to copy it around
      //   this.memory = source;
      //   this.copied = false;
      // } else {
        this.memory = MemorySegment.allocateNative(source.byteSize());
        this.memory.copyFrom(source);
        this.copied = true;
      // }
    }

    public MemoryAddress address() {
      return memory.address();
    }

    public void close() {
      if (copied) {
        if (copyBack) {
          source.copyFrom(memory);
        }
        memory.close();
      }
    }
  }

  private class MemoryDeviceCopy implements AutoCloseable {

    private final MemorySegment source;
    private final boolean copyBack;
    private final MemoryAddress devicePtr;
    private final boolean copied;

    public MemoryDeviceCopy(MemorySegment source, boolean copyBack) {
      try {
        this.source = source;
        this.copyBack = copyBack;
        try (MemorySegment devicePtrC = MemorySegment.allocateNative(MemoryLayouts.ADDRESS)) {
          if ((CublasStatus)allocHandle.invoke(source.byteSize() / 8, 8, devicePtrC.address()) != CublasStatus.Success) {
            throw new RuntimeException("Failed to allocate device memory");
          }
          this.devicePtr = (MemoryAddress)addressHandle.get(devicePtrC);
        }
        if ((CudaError)memcpyHandle.invoke(this.devicePtr, source.address(), source.byteSize(), CudaMemcpyKind.HostToDevice) != CudaError.Success) {
          throw new RuntimeException("Failed to copy host memory to device memory");
        }
        this.copied = true;
      } catch (RuntimeException e) {
        throw e;
      } catch (Throwable t) {
        throw new RuntimeException(t);
      }
    }

    public MemoryAddress address() {
      return devicePtr;
    }

    public void close() {
      try {
        if (copied) {
          if (copyBack) {
              if ((CudaError)memcpyHandle.invoke(source.address(), this.devicePtr, source.byteSize(), CudaMemcpyKind.DeviceToHost) != CudaError.Success) {
                throw new RuntimeException("Failed to copy device memory to host memory");
              }
          }
          freeHandle.invoke(devicePtr);
        }
      } catch (RuntimeException e) {
        throw e;
      } catch (Throwable t) {
        throw new RuntimeException(t);
      }
    }
  }

  private MemoryNativeCopy copy(MemorySegment segment) {
    return new MemoryNativeCopy(segment, false);
  }
  private MemoryNativeCopy copy(MemorySegment segment, boolean copyBack) {
    return new MemoryNativeCopy(segment, copyBack);
  }

  private MemorySegment segment(float[] arr, int offset) {
    return MemorySegment.ofArray(arr).asSlice(offset);
  }
  private MemorySegment segment(double[] arr, int offset) {
    return MemorySegment.ofArray(arr).asSlice(offset);
  }

  private final MethodHandle dasumHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDasum").get(), MethodType.methodType(double.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.of(C_DOUBLE, C_INT, C_POINTER, C_INT));

  protected double dasumK(int n, double[] x, int offsetx, int incx) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px, true)) {
      return (double)dasumHandle.invoke(n, cpx.address(), incx);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle sasumHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSasum").get(), MethodType.methodType(float.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.of(C_FLOAT, C_INT, C_POINTER, C_INT));

  protected float sasumK(int n, float[] x, int offsetx, int incx) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px, true)) {
      return (float)sasumHandle.invoke(n, cpx.address(), incx);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle daxpyHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDaxpy").get(), MethodType.methodType(void.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void daxpyK(int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      daxpyHandle.invoke(n, alpha, cpx.address(), incx, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle saxpyHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSaxpy").get(), MethodType.methodType(void.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void saxpyK(int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      saxpyHandle.invoke(n, alpha, cpx.address(), incx, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dcopyHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDcopy").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void dcopyK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      dcopyHandle.invoke(n, cpx.address(), incx, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle scopyHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasScopy").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void scopyK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      scopyHandle.invoke(n, cpx.address(), incx, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle ddotHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDdot").get(), MethodType.methodType(double.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.of(C_DOUBLE, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected double ddotK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py)) {
      return (double)ddotHandle.invoke(n, cpx.address(), incx, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle sdotHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSdot").get(), MethodType.methodType(float.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.of(C_FLOAT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected float sdotK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py)) {
      return (float)sdotHandle.invoke(n, cpx.address(), incx, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle sdsdotHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSdsdot").get(), MethodType.methodType(float.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.of(C_FLOAT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected float sdsdotK(int n, float sb, float[] sx, int offsetsx, int incsx, float[] sy, int offsetsy, int incsy) {
    try (MemorySegment psx = segment(sx, offsetsx); MemoryNativeCopy cpsx = copy(psx);
         MemorySegment psy = segment(sy, offsetsy); MemoryNativeCopy cpsy = copy(psy)) {
      return (float)sdsdotHandle.invoke(n, sb, cpsx.address(), incsx, cpsy.address(), incsy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dgbmvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDgbmv").get(), MethodType.methodType(void.class, char.class, int.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_INT, C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT));

  protected void dgbmvK(String trans, int m, int n, int kl, int ku, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      dgbmvHandle.invoke(CublasOperation.fromString(trans).value(), m, n, kl, ku, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle sgbmvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSgbmv").get(), MethodType.methodType(void.class, char.class, int.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_INT, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT));

  protected void sgbmvK(String trans, int m, int n, int kl, int ku, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      sgbmvHandle.invoke(CublasOperation.fromString(trans).value(), m, n, kl, ku, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dgemmHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDgemm").get(), MethodType.methodType(void.class, char.class, char.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT));

  protected void dgemmK(String transa, String transb, int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment pb = segment(b, offsetb); MemoryNativeCopy cpb = copy(pb);
         MemorySegment pc = segment(c, offsetc); MemoryNativeCopy cpc = copy(pc, true)) {
      dgemmHandle.invoke(CublasOperation.fromString(transa).value(), CublasOperation.fromString(transb).value(), m, n, k, alpha, cpa.address(), lda, cpb.address(), ldb, beta, cpc.address(), ldc);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle sgemmHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSgemm").get(), MethodType.methodType(void.class, char.class, char.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT));

  protected void sgemmK(String transa, String transb, int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment pb = segment(b, offsetb); MemoryNativeCopy cpb = copy(pb);
         MemorySegment pc = segment(c, offsetc); MemoryNativeCopy cpc = copy(pc, true)) {
      sgemmHandle.invoke(CublasOperation.fromString(transa).value(), CublasOperation.fromString(transb).value(), m, n, k, alpha, cpa.address(), lda, cpb.address(), ldb, beta, cpc.address(), ldc);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dgemvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDgemv").get(), MethodType.methodType(void.class, char.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT));

  protected void dgemvK(String trans, int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      dgemvHandle.invoke(CublasOperation.fromString(trans).value(), m, n, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle sgemvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSgemv").get(), MethodType.methodType(void.class, char.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT));

  protected void sgemvK(String trans, int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      sgemvHandle.invoke(CublasOperation.fromString(trans).value(), m, n, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dgerHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDger").get(), MethodType.methodType(void.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void dgerK(int m, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      dgerHandle.invoke(m, n, alpha, cpx.address(), incx, cpy.address(), incy, cpa.address(), lda);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle sgerHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSger").get(), MethodType.methodType(void.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void sgerK(int m, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      sgerHandle.invoke(m, n, alpha, cpx.address(), incx, cpy.address(), incy, cpa.address(), lda);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dnrm2Handle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDnrm2").get(), MethodType.methodType(double.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.of(C_DOUBLE, C_INT, C_POINTER, C_INT));

  protected double dnrm2K(int n, double[] x, int offsetx, int incx) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px)) {
      return (double)dnrm2Handle.invoke(n, cpx.address(), incx);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle snrm2Handle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSnrm2").get(), MethodType.methodType(float.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.of(C_FLOAT, C_INT, C_POINTER, C_INT));

  protected float snrm2K(int n, float[] x, int offsetx, int incx) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px)) {
      return (float)snrm2Handle.invoke(n, cpx.address(), incx);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle drotHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDrot").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, double.class),
          FunctionDescriptor.ofVoid(C_INT, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_DOUBLE));

  protected void drotK(int n, double[] dx, int offsetdx, int incx, double[] dy, int offsetdy, int incy, double c, double s) {
    try (MemorySegment pdx = segment(dx, offsetdx); MemoryNativeCopy cpdx = copy(pdx, true);
         MemorySegment pdy = segment(dy, offsetdy); MemoryNativeCopy cpdy = copy(pdy, true)) {
      drotHandle.invoke(n, cpdx.address(), incx, cpdy.address(), incy, c, s);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle srotHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSrot").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, float.class),
          FunctionDescriptor.ofVoid(C_INT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_FLOAT));

  protected void srotK(int n, float[] sx, int offsetsx, int incx, float[] sy, int offsetsy, int incy, float c, float s) {
    try (MemorySegment psx = segment(sx, offsetsx); MemoryNativeCopy cpsx = copy(psx, true);
         MemorySegment psy = segment(sy, offsetsy); MemoryNativeCopy cpsy = copy(psy, true)) {
      srotHandle.invoke(n, cpsx.address(), incx, cpsy.address(), incy, c, s);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle drotmHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDrotm").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_INT, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER));

  protected void drotmK(int n, double[] dx, int offsetdx, int incx, double[] dy, int offsetdy, int incy, double[] dparam, int offsetdparam) {
    try (MemorySegment pdx = segment(dx, offsetdx); MemoryNativeCopy cpdx = copy(pdx, true);
         MemorySegment pdy = segment(dy, offsetdy); MemoryNativeCopy cpdy = copy(pdy);
         MemorySegment pdparam = segment(dparam, offsetdparam); MemoryNativeCopy cpdparam = copy(pdparam)) {
      drotmHandle.invoke(n, cpdx.address(), incx, cpdy.address(), incy, cpdparam.address());
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle srotmHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSrotm").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_INT, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER));

  protected void srotmK(int n, float[] sx, int offsetsx, int incx, float[] sy, int offsetsy, int incy, float[] sparam, int offsetsparam) {
    try (MemorySegment psx = segment(sx, offsetsx); MemoryNativeCopy cpsx = copy(psx, true);
         MemorySegment psy = segment(sy, offsetsy); MemoryNativeCopy cpsy = copy(psy);
         MemorySegment psparam = segment(sparam, offsetsparam); MemoryNativeCopy cpsparam = copy(psparam)) {
      srotmHandle.invoke(n, cpsx.address(), incx, cpsy.address(), incy, cpsparam.address());
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle drotmgHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDrotmg").get(), MethodType.methodType(void.class, MemoryAddress.class, MemoryAddress.class, MemoryAddress.class, double.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_POINTER, C_POINTER, C_POINTER, C_DOUBLE, C_POINTER));

  protected void drotmgK(org.netlib.util.doubleW dd1, org.netlib.util.doubleW dd2, org.netlib.util.doubleW dx1, double dy1, double[] dparam, int offsetdparam) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle srotmgHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSrotmg").get(), MethodType.methodType(void.class, MemoryAddress.class, MemoryAddress.class, MemoryAddress.class, float.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_POINTER, C_POINTER, C_POINTER, C_FLOAT, C_POINTER));

  protected void srotmgK(org.netlib.util.floatW sd1, org.netlib.util.floatW sd2, org.netlib.util.floatW sx1, float sy1, float[] sparam, int offsetsparam) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle dsbmvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDsbmv").get(), MethodType.methodType(void.class, char.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT));

  protected void dsbmvK(String uplo, int n, int k, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      dsbmvHandle.invoke(CublasUPLO.fromString(uplo).value(), n, k, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle ssbmvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSsbmv").get(), MethodType.methodType(void.class, char.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT));

  protected void ssbmvK(String uplo, int n, int k, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      ssbmvHandle.invoke(CublasUPLO.fromString(uplo).value(), n, k, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dscalHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDscal").get(), MethodType.methodType(void.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_DOUBLE, C_POINTER, C_INT));

  protected void dscalK(int n, double alpha, double[] x, int offsetx, int incx) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px, true)) {
      dscalHandle.invoke(n, alpha, cpx.address(), incx);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle sscalHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSscal").get(), MethodType.methodType(void.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_FLOAT, C_POINTER, C_INT));

  protected void sscalK(int n, float alpha, float[] x, int offsetx, int incx) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px, true)) {
      sscalHandle.invoke(n, alpha, cpx.address(), incx);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dspmvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDspmv").get(), MethodType.methodType(void.class, char.class, int.class, double.class, MemoryAddress.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_INT, C_DOUBLE, C_POINTER, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT));

  protected void dspmvK(String uplo, int n, double alpha, double[] a, int offseta, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      dspmvHandle.invoke(CublasUPLO.fromString(uplo).value(), n, alpha, cpa.address(), cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle sspmvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSspmv").get(), MethodType.methodType(void.class, char.class, int.class, float.class, MemoryAddress.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_INT, C_FLOAT, C_POINTER, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT));

  protected void sspmvK(String uplo, int n, float alpha, float[] a, int offseta, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      sspmvHandle.invoke(CublasUPLO.fromString(uplo).value(), n, alpha, cpa.address(), cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dsprHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDspr").get(), MethodType.methodType(void.class, char.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER));

  protected void dsprK(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      dsprHandle.invoke(CublasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpa.address());
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle ssprHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSspr").get(), MethodType.methodType(void.class, char.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER));

  protected void ssprK(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      ssprHandle.invoke(CublasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpa.address());
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dspr2Handle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDspr2").get(), MethodType.methodType(void.class, char.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER));

  protected void dspr2K(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      dspr2Handle.invoke(CublasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpy.address(), incy, cpa.address());
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle sspr2Handle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSspr2").get(), MethodType.methodType(void.class, char.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER));

  protected void sspr2K(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      sspr2Handle.invoke(CublasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpy.address(), incy, cpa.address());
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dswapHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDswap").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void dswapK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px, true);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      dswapHandle.invoke(n, cpx.address(), incx, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle sswapHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSswap").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void sswapK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px, true);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      sswapHandle.invoke(n, cpx.address(), incx, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dsymmHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDsymm").get(), MethodType.methodType(void.class, char.class, char.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT));

  protected void dsymmK(String side, String uplo, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment pb = segment(b, offsetb); MemoryNativeCopy cpb = copy(pb);
         MemorySegment pc = segment(c, offsetc); MemoryNativeCopy cpc = copy(pc, true)) {
      dsymmHandle.invoke(CublasSide.fromString(side).value(), CublasUPLO.fromString(uplo).value(), m, n, alpha, cpa.address(), lda, cpb.address(), ldb, beta, cpc.address(), ldc);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle ssymmHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSsymm").get(), MethodType.methodType(void.class, char.class, char.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT));

  protected void ssymmK(String side, String uplo, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment pb = segment(b, offsetb); MemoryNativeCopy cpb = copy(pb);
         MemorySegment pc = segment(c, offsetc); MemoryNativeCopy cpc = copy(pc, true)) {
      ssymmHandle.invoke(CublasSide.fromString(side).value(), CublasUPLO.fromString(uplo).value(), m, n, alpha, cpa.address(), lda, cpb.address(), ldb, beta, cpc.address(), ldc);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dsymvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDsymv").get(), MethodType.methodType(void.class, char.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT));

  protected void dsymvK(String uplo, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      dsymvHandle.invoke(CublasUPLO.fromString(uplo).value(), n, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle ssymvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSsymv").get(), MethodType.methodType(void.class, char.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT));

  protected void ssymvK(String uplo, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      ssymvHandle.invoke(CublasUPLO.fromString(uplo).value(), n, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dsyrHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDsyr").get(), MethodType.methodType(void.class, char.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void dsyrK(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta, int lda) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      dsyrHandle.invoke(CublasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpa.address(), lda);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle ssyrHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSsyr").get(), MethodType.methodType(void.class, char.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void ssyrK(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta, int lda) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      ssyrHandle.invoke(CublasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpa.address(), lda);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dsyr2Handle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDsyr2").get(), MethodType.methodType(void.class, char.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void dsyr2K(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      dsyr2Handle.invoke(CublasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpy.address(), incy, cpa.address(), lda);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle ssyr2Handle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSsyr2").get(), MethodType.methodType(void.class, char.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void ssyr2K(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      ssyr2Handle.invoke(CublasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpy.address(), incy, cpa.address(), lda);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dsyr2kHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDsyr2k").get(), MethodType.methodType(void.class, char.class, char.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT));

  protected void dsyr2kK(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment pb = segment(b, offsetb); MemoryNativeCopy cpb = copy(pb);
         MemorySegment pc = segment(c, offsetc); MemoryNativeCopy cpc = copy(pc, true)) {
      dsyr2kHandle.invoke(CublasUPLO.fromString(uplo).value(), CublasOperation.fromString(trans).value(), n, k, alpha, cpa.address(), lda, cpb.address(), ldb, beta, cpc.address(), ldc);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle ssyr2kHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSsyr2k").get(), MethodType.methodType(void.class, char.class, char.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT));

  protected void ssyr2kK(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment pb = segment(b, offsetb); MemoryNativeCopy cpb = copy(pb);
         MemorySegment pc = segment(c, offsetc); MemoryNativeCopy cpc = copy(pc, true)) {
      ssyr2kHandle.invoke(CublasUPLO.fromString(uplo).value(), CublasOperation.fromString(trans).value(), n, k, alpha, cpa.address(), lda, cpb.address(), ldb, beta, cpc.address(), ldc);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dsyrkHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDsyrk").get(), MethodType.methodType(void.class, char.class, char.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT));

  protected void dsyrkK(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double beta, double[] c, int offsetc, int ldc) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle ssyrkHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasSsyrk").get(), MethodType.methodType(void.class, char.class, char.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT));

  protected void ssyrkK(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float beta, float[] c, int offsetc, int ldc) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle dtbmvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDtbmv").get(), MethodType.methodType(void.class, char.class, char.class, char.class, int.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_CHAR, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void dtbmvK(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle stbmvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasStbmv").get(), MethodType.methodType(void.class, char.class, char.class, char.class, int.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_CHAR, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void stbmvK(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle dtbsvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDtbsv").get(), MethodType.methodType(void.class, char.class, char.class, char.class, int.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_CHAR, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void dtbsvK(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle stbsvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasStbsv").get(), MethodType.methodType(void.class, char.class, char.class, char.class, int.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_CHAR, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void stbsvK(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle dtpmvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDtpmv").get(), MethodType.methodType(void.class, char.class, char.class, char.class, int.class, int.class, MemoryAddress.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_CHAR, C_INT, C_INT, C_POINTER, C_POINTER, C_INT));

  protected void dtpmvK(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle stpmvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasStpmv").get(), MethodType.methodType(void.class, char.class, char.class, char.class, int.class, int.class, MemoryAddress.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_CHAR, C_INT, C_INT, C_POINTER, C_POINTER, C_INT));

  protected void stpmvK(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle dtpsvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDtpsv").get(), MethodType.methodType(void.class, char.class, char.class, int.class, int.class, int.class, MemoryAddress.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_CHAR, C_INT, C_INT, C_POINTER, C_POINTER, C_INT));

  protected void dtpsvK(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle stpsvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasStpsv").get(), MethodType.methodType(void.class, char.class, char.class, char.class, int.class, int.class, MemoryAddress.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_CHAR, C_INT, C_INT, C_POINTER, C_POINTER, C_INT));

  protected void stpsvK(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle dtrmmHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDtrmm").get(), MethodType.methodType(void.class, char.class, char.class, char.class, int.class, MemoryAddress.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_CHAR, C_CHAR, C_POINTER, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void dtrmmK(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle strmmHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasStrmm").get(), MethodType.methodType(void.class, char.class, char.class, char.class, char.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_CHAR, C_CHAR, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void strmmK(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle dtrmvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDtrmv").get(), MethodType.methodType(void.class, char.class, char.class, char.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_CHAR, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void dtrmvK(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle strmvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasStrmv").get(), MethodType.methodType(void.class, char.class, char.class, char.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_CHAR, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void strmvK(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle dtrsmHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDtrsm").get(), MethodType.methodType(void.class, char.class, char.class, char.class, char.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_CHAR, C_CHAR, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void dtrsmK(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle strsmHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasStrsm").get(), MethodType.methodType(void.class, char.class, char.class, char.class, char.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
        FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_CHAR, C_CHAR, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void strsmK(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle dtrsvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasDtrsv").get(), MethodType.methodType(void.class, char.class, char.class, char.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_CHAR, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void dtrsvK(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle strsvHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasStrsv").get(), MethodType.methodType(void.class, char.class, char.class, char.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_CHAR, C_CHAR, C_CHAR, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void strsvK(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle idamaxHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasIdamax").get(), MethodType.methodType(int.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.of(C_INT, C_INT, C_POINTER, C_INT));

  protected int idamaxK(int n, double[] dx, int offsetdx, int incdx) {
    try (MemorySegment pdx = segment(dx, offsetdx); MemoryNativeCopy cpdx = copy(pdx)) {
      return (int)idamaxHandle.invoke(n, cpdx.address(), incdx);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle isamaxHandle =
      CLinker.getInstance().downcallHandle(
        cublas.lookup("cublasIsamax").get(), MethodType.methodType(int.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.of(C_INT, C_INT, C_POINTER, C_INT));

  protected int isamaxK(int n, float[] sx, int offsetsx, int incx) {
    try (MemorySegment psx = segment(sx, offsetsx); MemoryNativeCopy cpsx = copy(psx)) {
      return (int)isamaxHandle.invoke(n, cpsx.address(), incx);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }
}
