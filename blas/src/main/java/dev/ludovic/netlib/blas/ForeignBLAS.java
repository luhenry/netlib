/*
 * Copyright 2020, 2021, Ludovic Henry
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
 *
 * Please contact git@ludovic.dev or visit ludovic.dev if you need additional
 * information or have any questions.
 */

package dev.ludovic.netlib.blas;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.invoke.MethodType;
import java.nio.file.Paths;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.LibraryLookup;
import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.MemorySegment;

import static jdk.incubator.foreign.CLinker.*;

import dev.ludovic.netlib.BLAS;

public final class ForeignBLAS extends AbstractBLAS implements dev.ludovic.netlib.NativeBLAS {

  private static final Logger LOGGER = Logger.getLogger(ForeignBLAS.class.getName());

  private static final ForeignBLAS instance = new ForeignBLAS();

  private final LibraryLookup blas =
    System.getProperty("dev.ludovic.netlib.blas.nativeLibPath") != null ?
      LibraryLookup.ofPath(Paths.get(System.getProperty("dev.ludovic.netlib.blas.nativeLibPath"))) :
      LibraryLookup.ofLibrary(System.getProperty("dev.ludovic.netlib.blas.nativeLib", "blas"));

  protected ForeignBLAS() {}

  public static dev.ludovic.netlib.NativeBLAS getInstance() {
    return instance;
  }

  private static enum CblasLayout {
    CblasRowMajor(101),
    CblasColMajor(102);

    private final int val;

    private CblasLayout(int val) {
      this.val = val;
    }

    public int value() {
      return val;
    }
  }

  private static enum CblasTranspose {
    CblasNoTrans(111),
    CblasTrans(112),
    CblasConjTrans(113);

    private final int val;

    private CblasTranspose(int val) {
      this.val = val;
    }

    public int value() {
      return val;
    }

    public static CblasTranspose fromString(String trans) {
      switch (trans) {
        case "N": return CblasNoTrans;
        case "T": return CblasTrans;
        case "C": return CblasTrans;
        default: throw new IllegalArgumentException("unknown trans = " + trans);
      }
    }
  }
  private static enum CblasUPLO {
    CblasUpper(121),
    CblasLower(122);

    private final int val;

    private CblasUPLO(int val) {
      this.val = val;
    }

    public int value() {
      return val;
    }

    public static CblasUPLO fromString(String uplo) {
      switch (uplo) {
        case "U": return CblasUpper;
        case "L": return CblasLower;
        default: throw new IllegalArgumentException("unknown uplo = " + uplo);
      }
    }
  }

  private static enum CblasDiag {
    CblasNonUnit(131),
    CblasUnit(132);

    private final int val;

    private CblasDiag(int val) {
      this.val = val;
    }

    public int value() {
      return val;
    }
    public static CblasDiag fromString(String diag) {
      switch (diag) {
        case "N": return CblasNonUnit;
        case "U": return CblasUnit;
        default: throw new IllegalArgumentException("unknown diag = " + diag);
      }
    }
  }

  private static enum CblasSide {
    CblasLeft(141),
    CblasRight(142);

    private final int val;

    private CblasSide(int val) {
      this.val = val;
    }

    public int value() {
      return val;
    }

    public static CblasSide fromString(String side) {
      switch (side) {
        case "L": return CblasLeft;
        case "R": return CblasRight;
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
        blas.lookup("cblas_dasum").get(), MethodType.methodType(double.class, int.class, MemoryAddress.class, int.class),
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
        blas.lookup("cblas_sasum").get(), MethodType.methodType(float.class, int.class, MemoryAddress.class, int.class),
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
        blas.lookup("cblas_daxpy").get(), MethodType.methodType(void.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
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
        blas.lookup("cblas_saxpy").get(), MethodType.methodType(void.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
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
        blas.lookup("cblas_dcopy").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
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
        blas.lookup("cblas_scopy").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
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
        blas.lookup("cblas_ddot").get(), MethodType.methodType(double.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
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
        blas.lookup("cblas_sdot").get(), MethodType.methodType(float.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
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
        blas.lookup("cblas_sdsdot").get(), MethodType.methodType(float.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
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
        blas.lookup("cblas_dgbmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT));

  protected void dgbmvK(String trans, int m, int n, int kl, int ku, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      dgbmvHandle.invoke(CblasLayout.CblasColMajor.value(), CblasTranspose.fromString(trans).value(), m, n, kl, ku, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle sgbmvHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_sgbmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT));

  protected void sgbmvK(String trans, int m, int n, int kl, int ku, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      sgbmvHandle.invoke(CblasLayout.CblasColMajor.value(), CblasTranspose.fromString(trans).value(), m, n, kl, ku, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dgemmHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dgemm").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT));

  protected void dgemmK(String transa, String transb, int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment pb = segment(b, offsetb); MemoryNativeCopy cpb = copy(pb);
         MemorySegment pc = segment(c, offsetc); MemoryNativeCopy cpc = copy(pc, true)) {
      dgemmHandle.invoke(CblasLayout.CblasColMajor.value(), CblasTranspose.fromString(transa).value(), CblasTranspose.fromString(transb).value(), m, n, k, alpha, cpa.address(), lda, cpb.address(), ldb, beta, cpc.address(), ldc);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle sgemmHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_sgemm").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT));

  protected void sgemmK(String transa, String transb, int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment pb = segment(b, offsetb); MemoryNativeCopy cpb = copy(pb);
         MemorySegment pc = segment(c, offsetc); MemoryNativeCopy cpc = copy(pc, true)) {
      sgemmHandle.invoke(CblasLayout.CblasColMajor.value(), CblasTranspose.fromString(transa).value(), CblasTranspose.fromString(transb).value(), m, n, k, alpha, cpa.address(), lda, cpb.address(), ldb, beta, cpc.address(), ldc);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dgemvHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dgemv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT));

  protected void dgemvK(String trans, int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      dgemvHandle.invoke(CblasLayout.CblasColMajor.value(), CblasTranspose.fromString(trans).value(), m, n, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle sgemvHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_sgemv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT));

  protected void sgemvK(String trans, int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      sgemvHandle.invoke(CblasLayout.CblasColMajor.value(), CblasTranspose.fromString(trans).value(), m, n, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dgerHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dger").get(), MethodType.methodType(void.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void dgerK(int m, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      dgerHandle.invoke(CblasLayout.CblasColMajor.value(), m, n, alpha, cpx.address(), incx, cpy.address(), incy, cpa.address(), lda);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle sgerHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_sger").get(), MethodType.methodType(void.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void sgerK(int m, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      sgerHandle.invoke(CblasLayout.CblasColMajor.value(), m, n, alpha, cpx.address(), incx, cpy.address(), incy, cpa.address(), lda);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dnrm2Handle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dnrm2").get(), MethodType.methodType(double.class, int.class, MemoryAddress.class, int.class),
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
        blas.lookup("cblas_snrm2").get(), MethodType.methodType(float.class, int.class, MemoryAddress.class, int.class),
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
        blas.lookup("cblas_drot").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, double.class),
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
        blas.lookup("cblas_srot").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, float.class),
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
        blas.lookup("cblas_drotm").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class),
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
        blas.lookup("cblas_srotm").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class),
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
        blas.lookup("cblas_drotmg").get(), MethodType.methodType(void.class, MemoryAddress.class, MemoryAddress.class, MemoryAddress.class, double.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_POINTER, C_POINTER, C_POINTER, C_DOUBLE, C_POINTER));

  protected void drotmgK(org.netlib.util.doubleW dd1, org.netlib.util.doubleW dd2, org.netlib.util.doubleW dx1, double dy1, double[] dparam, int offsetdparam) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle srotmgHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_srotmg").get(), MethodType.methodType(void.class, MemoryAddress.class, MemoryAddress.class, MemoryAddress.class, float.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_POINTER, C_POINTER, C_POINTER, C_FLOAT, C_POINTER));

  protected void srotmgK(org.netlib.util.floatW sd1, org.netlib.util.floatW sd2, org.netlib.util.floatW sx1, float sy1, float[] sparam, int offsetsparam) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle dsbmvHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dsbmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT));

  protected void dsbmvK(String uplo, int n, int k, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      dsbmvHandle.invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, k, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle ssbmvHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_ssbmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT));

  protected void ssbmvK(String uplo, int n, int k, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      ssbmvHandle.invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, k, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dscalHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dscal").get(), MethodType.methodType(void.class, int.class, double.class, MemoryAddress.class, int.class),
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
        blas.lookup("cblas_sscal").get(), MethodType.methodType(void.class, int.class, float.class, MemoryAddress.class, int.class),
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
        blas.lookup("cblas_dspmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, double.class, MemoryAddress.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT));

  protected void dspmvK(String uplo, int n, double alpha, double[] a, int offseta, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      dspmvHandle.invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpa.address(), cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle sspmvHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_sspmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, float.class, MemoryAddress.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT));

  protected void sspmvK(String uplo, int n, float alpha, float[] a, int offseta, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      sspmvHandle.invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpa.address(), cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dsprHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dspr").get(), MethodType.methodType(void.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER));

  protected void dsprK(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      dsprHandle.invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpa.address());
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle ssprHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_sspr").get(), MethodType.methodType(void.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER));

  protected void ssprK(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      ssprHandle.invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpa.address());
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dspr2Handle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dspr2").get(), MethodType.methodType(void.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER));

  protected void dspr2K(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      dspr2Handle.invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpy.address(), incy, cpa.address());
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle sspr2Handle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_sspr2").get(), MethodType.methodType(void.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER));

  protected void sspr2K(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      sspr2Handle.invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpy.address(), incy, cpa.address());
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dswapHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dswap").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
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
        blas.lookup("cblas_sswap").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
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
        blas.lookup("cblas_dsymm").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT));

  protected void dsymmK(String side, String uplo, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment pb = segment(b, offsetb); MemoryNativeCopy cpb = copy(pb);
         MemorySegment pc = segment(c, offsetc); MemoryNativeCopy cpc = copy(pc, true)) {
      dsymmHandle.invoke(CblasLayout.CblasColMajor.value(), CblasSide.fromString(side).value(), CblasUPLO.fromString(uplo).value(), m, n, alpha, cpa.address(), lda, cpb.address(), ldb, beta, cpc.address(), ldc);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle ssymmHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_ssymm").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT));

  protected void ssymmK(String side, String uplo, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment pb = segment(b, offsetb); MemoryNativeCopy cpb = copy(pb);
         MemorySegment pc = segment(c, offsetc); MemoryNativeCopy cpc = copy(pc, true)) {
      ssymmHandle.invoke(CblasLayout.CblasColMajor.value(), CblasSide.fromString(side).value(), CblasUPLO.fromString(uplo).value(), m, n, alpha, cpa.address(), lda, cpb.address(), ldb, beta, cpc.address(), ldc);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dsymvHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dsymv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT));

  protected void dsymvK(String uplo, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      dsymvHandle.invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle ssymvHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_ssymv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT));

  protected void ssymvK(String uplo, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      ssymvHandle.invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dsyrHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dsyr").get(), MethodType.methodType(void.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void dsyrK(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta, int lda) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      dsyrHandle.invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpa.address(), lda);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle ssyrHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_ssyr").get(), MethodType.methodType(void.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void ssyrK(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta, int lda) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      ssyrHandle.invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpa.address(), lda);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dsyr2Handle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dsyr2").get(), MethodType.methodType(void.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void dsyr2K(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      dsyr2Handle.invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpy.address(), incy, cpa.address(), lda);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle ssyr2Handle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_ssyr2").get(), MethodType.methodType(void.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void ssyr2K(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      ssyr2Handle.invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpy.address(), incy, cpa.address(), lda);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dsyr2kHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dsyr2k").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT));

  protected void dsyr2kK(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment pb = segment(b, offsetb); MemoryNativeCopy cpb = copy(pb);
         MemorySegment pc = segment(c, offsetc); MemoryNativeCopy cpc = copy(pc, true)) {
      dsyr2kHandle.invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), CblasTranspose.fromString(trans).value(), n, k, alpha, cpa.address(), lda, cpb.address(), ldb, beta, cpc.address(), ldc);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle ssyr2kHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_ssyr2k").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT));

  protected void ssyr2kK(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment pb = segment(b, offsetb); MemoryNativeCopy cpb = copy(pb);
         MemorySegment pc = segment(c, offsetc); MemoryNativeCopy cpc = copy(pc, true)) {
      ssyr2kHandle.invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), CblasTranspose.fromString(trans).value(), n, k, alpha, cpa.address(), lda, cpb.address(), ldb, beta, cpc.address(), ldc);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final MethodHandle dsyrkHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dsyrk").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT));

  protected void dsyrkK(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double beta, double[] c, int offsetc, int ldc) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle ssyrkHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_ssyrk").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT));

  protected void ssyrkK(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float beta, float[] c, int offsetc, int ldc) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle dtbmvHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dtbmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void dtbmvK(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle stbmvHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_stbmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void stbmvK(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle dtbsvHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dtbsv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void dtbsvK(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle stbsvHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_stbsv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void stbsvK(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle dtpmvHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dtpmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_POINTER, C_INT));

  protected void dtpmvK(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle stpmvHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_stpmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_POINTER, C_INT));

  protected void stpmvK(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle dtpsvHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dtpsv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_POINTER, C_INT));

  protected void dtpsvK(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle stpsvHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_stpsv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_POINTER, C_INT));

  protected void stpsvK(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle dtrmmHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dtrmm").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, MemoryAddress.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void dtrmmK(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle strmmHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_strmm").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void strmmK(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle dtrmvHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dtrmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void dtrmvK(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle strmvHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_strmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void strmvK(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle dtrsmHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dtrsm").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, MemoryAddress.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void dtrsmK(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle strsmHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_strsm").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void strsmK(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle dtrsvHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_dtrsv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void dtrsvK(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle strsvHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_strsv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT));

  protected void strsvK(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final MethodHandle idamaxHandle =
      CLinker.getInstance().downcallHandle(
        blas.lookup("cblas_idamax").get(), MethodType.methodType(int.class, int.class, MemoryAddress.class, int.class),
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
        blas.lookup("cblas_isamax").get(), MethodType.methodType(int.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.of(C_INT, C_INT, C_POINTER, C_INT));

  protected int isamaxK(int n, float[] sx, int offsetsx, int incx) {
    try (MemorySegment psx = segment(sx, offsetsx); MemoryNativeCopy cpsx = copy(psx)) {
      return (int)isamaxHandle.invoke(n, cpsx.address(), incx);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }
}
