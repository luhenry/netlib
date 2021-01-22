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
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.LibraryLookup;
import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.MemorySegment;

import static jdk.incubator.foreign.CLinker.*;

public final class NativeBLAS extends AbstractBLAS {

  private static final Logger LOGGER = Logger.getLogger(NativeBLAS.class.getName());

  private final LibraryLookup library;

  private static final NativeBLAS instance = new NativeBLAS();

  protected NativeBLAS() {
    library = LibraryLookup.ofLibrary("blas");
  }

  public static BLAS getInstance() {
    return instance;
  }

  private LibraryLookup getLibrary() {
    return library;
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

  private static class Lazy<T> implements Supplier<T> {

    private final Supplier<T> supplier;

    private boolean supplied = false;
    private T val = null;

    private Lazy(Supplier<T> supplier) {
      this.supplier = supplier;
    }

    public static <T> Lazy<T> let(Supplier<T> supplier) {
      return new Lazy(supplier);
    }

    public synchronized T get() {
      if (!supplied) {
        val = supplier.get();
        supplied = true;
      }
      return val;
    }
  }

  private static class MemoryNativeCopy implements AutoCloseable {

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

  private final Lazy<MethodHandle> dasumHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dasum").get(), MethodType.methodType(double.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.of(C_DOUBLE, C_INT, C_POINTER, C_INT)));

  protected double dasumK(int n, double[] x, int offsetx, int incx) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px, true)) {
      return (double)dasumHandle.get().invoke(n, cpx.address(), incx);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> sasumHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_sasum").get(), MethodType.methodType(float.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.of(C_FLOAT, C_INT, C_POINTER, C_INT)));

  protected float sasumK(int n, float[] x, int offsetx, int incx) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px, true)) {
      return (float)sasumHandle.get().invoke(n, cpx.address(), incx);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> daxpyHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_daxpy").get(), MethodType.methodType(void.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void daxpyK(int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      daxpyHandle.get().invoke(n, alpha, cpx.address(), incx, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> saxpyHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_saxpy").get(), MethodType.methodType(void.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void saxpyK(int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      saxpyHandle.get().invoke(n, alpha, cpx.address(), incx, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> dcopyHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dcopy").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void dcopyK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      dcopyHandle.get().invoke(n, cpx.address(), incx, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> scopyHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_scopy").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void scopyK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      scopyHandle.get().invoke(n, cpx.address(), incx, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> ddotHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_ddot").get(), MethodType.methodType(double.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.of(C_DOUBLE, C_INT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected double ddotK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py)) {
      return (double)ddotHandle.get().invoke(n, cpx.address(), incx, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> sdotHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_sdot").get(), MethodType.methodType(float.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.of(C_FLOAT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected float sdotK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py)) {
      return (float)sdotHandle.get().invoke(n, cpx.address(), incx, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> sdsdotHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_sdsdot").get(), MethodType.methodType(float.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.of(C_FLOAT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected float sdsdotK(int n, float sb, float[] sx, int offsetsx, int incsx, float[] sy, int offsetsy, int incsy) {
    try (MemorySegment psx = segment(sx, offsetsx); MemoryNativeCopy cpsx = copy(psx);
         MemorySegment psy = segment(sy, offsetsy); MemoryNativeCopy cpsy = copy(psy)) {
      return (float)sdsdotHandle.get().invoke(n, sb, cpsx.address(), incsx, cpsy.address(), incsy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> dgbmvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dgbmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT)));

  protected void dgbmvK(String trans, int m, int n, int kl, int ku, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      dgbmvHandle.get().invoke(CblasLayout.CblasColMajor.value(), CblasTranspose.fromString(trans).value(), m, n, kl, ku, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> sgbmvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_sgbmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT)));

  protected void sgbmvK(String trans, int m, int n, int kl, int ku, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      sgbmvHandle.get().invoke(CblasLayout.CblasColMajor.value(), CblasTranspose.fromString(trans).value(), m, n, kl, ku, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> dgemmHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dgemm").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT)));

  protected void dgemmK(String transa, String transb, int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment pb = segment(b, offsetb); MemoryNativeCopy cpb = copy(pb);
         MemorySegment pc = segment(c, offsetc); MemoryNativeCopy cpc = copy(pc, true)) {
      dgemmHandle.get().invoke(CblasLayout.CblasColMajor.value(), CblasTranspose.fromString(transa).value(), CblasTranspose.fromString(transb).value(), m, n, k, alpha, cpa.address(), lda, cpb.address(), ldb, beta, cpc.address(), ldc);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> sgemmHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_sgemm").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT)));

  protected void sgemmK(String transa, String transb, int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment pb = segment(b, offsetb); MemoryNativeCopy cpb = copy(pb);
         MemorySegment pc = segment(c, offsetc); MemoryNativeCopy cpc = copy(pc, true)) {
      sgemmHandle.get().invoke(CblasLayout.CblasColMajor.value(), CblasTranspose.fromString(transa).value(), CblasTranspose.fromString(transb).value(), m, n, k, alpha, cpa.address(), lda, cpb.address(), ldb, beta, cpc.address(), ldc);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> dgemvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dgemv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT)));

  protected void dgemvK(String trans, int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      dgemvHandle.get().invoke(CblasLayout.CblasColMajor.value(), CblasTranspose.fromString(trans).value(), m, n, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> sgemvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_sgemv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT)));

  protected void sgemvK(String trans, int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      sgemvHandle.get().invoke(CblasLayout.CblasColMajor.value(), CblasTranspose.fromString(trans).value(), m, n, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> dgerHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dger").get(), MethodType.methodType(void.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void dgerK(int m, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      dgerHandle.get().invoke(CblasLayout.CblasColMajor.value(), m, n, alpha, cpx.address(), incx, cpy.address(), incy, cpa.address(), lda);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> sgerHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_sger").get(), MethodType.methodType(void.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void sgerK(int m, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      sgerHandle.get().invoke(CblasLayout.CblasColMajor.value(), m, n, alpha, cpx.address(), incx, cpy.address(), incy, cpa.address(), lda);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> dnrm2Handle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dnrm2").get(), MethodType.methodType(double.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.of(C_DOUBLE, C_INT, C_POINTER, C_INT)));

  protected double dnrm2K(int n, double[] x, int offsetx, int incx) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px)) {
      return (double)dnrm2Handle.get().invoke(n, cpx.address(), incx);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> snrm2Handle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_snrm2").get(), MethodType.methodType(float.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.of(C_FLOAT, C_INT, C_POINTER, C_INT)));

  protected float snrm2K(int n, float[] x, int offsetx, int incx) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px)) {
      return (float)snrm2Handle.get().invoke(n, cpx.address(), incx);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> drotHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_drot").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, double.class),
          FunctionDescriptor.ofVoid(C_INT, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_DOUBLE)));

  protected void drotK(int n, double[] dx, int offsetdx, int incx, double[] dy, int offsetdy, int incy, double c, double s) {
    try (MemorySegment pdx = segment(dx, offsetdx); MemoryNativeCopy cpdx = copy(pdx, true);
         MemorySegment pdy = segment(dy, offsetdy); MemoryNativeCopy cpdy = copy(pdy, true)) {
      drotHandle.get().invoke(n, cpdx.address(), incx, cpdy.address(), incy, c, s);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> srotHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_srot").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, float.class),
          FunctionDescriptor.ofVoid(C_INT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_FLOAT)));

  protected void srotK(int n, float[] sx, int offsetsx, int incx, float[] sy, int offsetsy, int incy, float c, float s) {
    try (MemorySegment psx = segment(sx, offsetsx); MemoryNativeCopy cpsx = copy(psx, true);
         MemorySegment psy = segment(sy, offsetsy); MemoryNativeCopy cpsy = copy(psy, true)) {
      srotHandle.get().invoke(n, cpsx.address(), incx, cpsy.address(), incy, c, s);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> drotmHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_drotm").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_INT, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER)));

  protected void drotmK(int n, double[] dx, int offsetdx, int incx, double[] dy, int offsetdy, int incy, double[] dparam, int offsetdparam) {
    try (MemorySegment pdx = segment(dx, offsetdx); MemoryNativeCopy cpdx = copy(pdx, true);
         MemorySegment pdy = segment(dy, offsetdy); MemoryNativeCopy cpdy = copy(pdy);
         MemorySegment pdparam = segment(dparam, offsetdparam); MemoryNativeCopy cpdparam = copy(pdparam)) {
      drotmHandle.get().invoke(n, cpdx.address(), incx, cpdy.address(), incy, cpdparam.address());
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> srotmHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_srotm").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_INT, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER)));

  protected void srotmK(int n, float[] sx, int offsetsx, int incx, float[] sy, int offsetsy, int incy, float[] sparam, int offsetsparam) {
    try (MemorySegment psx = segment(sx, offsetsx); MemoryNativeCopy cpsx = copy(psx, true);
         MemorySegment psy = segment(sy, offsetsy); MemoryNativeCopy cpsy = copy(psy);
         MemorySegment psparam = segment(sparam, offsetsparam); MemoryNativeCopy cpsparam = copy(psparam)) {
      srotmHandle.get().invoke(n, cpsx.address(), incx, cpsy.address(), incy, cpsparam.address());
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> drotmgHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_drotmg").get(), MethodType.methodType(void.class, MemoryAddress.class, MemoryAddress.class, MemoryAddress.class, double.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_POINTER, C_POINTER, C_POINTER, C_DOUBLE, C_POINTER)));

  protected void drotmgK(org.netlib.util.doubleW dd1, org.netlib.util.doubleW dd2, org.netlib.util.doubleW dx1, double dy1, double[] dparam, int offsetdparam) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final Lazy<MethodHandle> srotmgHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_srotmg").get(), MethodType.methodType(void.class, MemoryAddress.class, MemoryAddress.class, MemoryAddress.class, float.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_POINTER, C_POINTER, C_POINTER, C_FLOAT, C_POINTER)));

  protected void srotmgK(org.netlib.util.floatW sd1, org.netlib.util.floatW sd2, org.netlib.util.floatW sx1, float sy1, float[] sparam, int offsetsparam) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final Lazy<MethodHandle> dsbmvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dsbmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT)));

  protected void dsbmvK(String uplo, int n, int k, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      dsbmvHandle.get().invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, k, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> ssbmvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_ssbmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT)));

  protected void ssbmvK(String uplo, int n, int k, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      ssbmvHandle.get().invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, k, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> dscalHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dscal").get(), MethodType.methodType(void.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_DOUBLE, C_POINTER, C_INT)));

  protected void dscalK(int n, double alpha, double[] x, int offsetx, int incx) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px, true)) {
      dscalHandle.get().invoke(n, alpha, cpx.address(), incx);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> sscalHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_sscal").get(), MethodType.methodType(void.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_FLOAT, C_POINTER, C_INT)));

  protected void sscalK(int n, float alpha, float[] x, int offsetx, int incx) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px, true)) {
      sscalHandle.get().invoke(n, alpha, cpx.address(), incx);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> dspmvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dspmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, double.class, MemoryAddress.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT)));

  protected void dspmvK(String uplo, int n, double alpha, double[] a, int offseta, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      dspmvHandle.get().invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpa.address(), cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> sspmvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_sspmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, float.class, MemoryAddress.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT)));

  protected void sspmvK(String uplo, int n, float alpha, float[] a, int offseta, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      sspmvHandle.get().invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpa.address(), cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> dsprHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dspr").get(), MethodType.methodType(void.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER)));

  protected void dsprK(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      dsprHandle.get().invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpa.address());
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> ssprHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_sspr").get(), MethodType.methodType(void.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER)));

  protected void ssprK(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      ssprHandle.get().invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpa.address());
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> dspr2Handle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dspr2").get(), MethodType.methodType(void.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER)));

  protected void dspr2K(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      dspr2Handle.get().invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpy.address(), incy, cpa.address());
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> sspr2Handle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_sspr2").get(), MethodType.methodType(void.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER)));

  protected void sspr2K(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      sspr2Handle.get().invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpy.address(), incy, cpa.address());
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> dswapHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dswap").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void dswapK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px, true);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      dswapHandle.get().invoke(n, cpx.address(), incx, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> sswapHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_sswap").get(), MethodType.methodType(void.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void sswapK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px, true);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      sswapHandle.get().invoke(n, cpx.address(), incx, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> dsymmHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dsymm").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT)));

  protected void dsymmK(String side, String uplo, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment pb = segment(b, offsetb); MemoryNativeCopy cpb = copy(pb);
         MemorySegment pc = segment(c, offsetc); MemoryNativeCopy cpc = copy(pc, true)) {
      dsymmHandle.get().invoke(CblasLayout.CblasColMajor.value(), CblasSide.fromString(side).value(), CblasUPLO.fromString(uplo).value(), m, n, alpha, cpa.address(), lda, cpb.address(), ldb, beta, cpc.address(), ldc);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> ssymmHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_ssymm").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT)));

  protected void ssymmK(String side, String uplo, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment pb = segment(b, offsetb); MemoryNativeCopy cpb = copy(pb);
         MemorySegment pc = segment(c, offsetc); MemoryNativeCopy cpc = copy(pc, true)) {
      ssymmHandle.get().invoke(CblasLayout.CblasColMajor.value(), CblasSide.fromString(side).value(), CblasUPLO.fromString(uplo).value(), m, n, alpha, cpa.address(), lda, cpb.address(), ldb, beta, cpc.address(), ldc);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> dsymvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dsymv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class,MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE,C_POINTER, C_INT)));

  protected void dsymvK(String uplo, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      dsymvHandle.get().invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> ssymvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_ssymv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class,MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT,C_POINTER, C_INT)));

  protected void ssymvK(String uplo, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py, true)) {
      ssymvHandle.get().invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpa.address(), lda, cpx.address(), incx, beta, cpy.address(), incy);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> dsyrHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dsyr").get(), MethodType.methodType(void.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void dsyrK(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta, int lda) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      dsyrHandle.get().invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpa.address(), lda);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> ssyrHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_ssyr").get(), MethodType.methodType(void.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void ssyrK(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta, int lda) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      ssyrHandle.get().invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpa.address(), lda);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> dsyr2Handle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dsyr2").get(), MethodType.methodType(void.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void dsyr2K(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      dsyr2Handle.get().invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpy.address(), incy, cpa.address(), lda);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> ssyr2Handle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_ssyr2").get(), MethodType.methodType(void.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void ssyr2K(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
    try (MemorySegment px = segment(x, offsetx); MemoryNativeCopy cpx = copy(px);
         MemorySegment py = segment(y, offsety); MemoryNativeCopy cpy = copy(py);
         MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa, true)) {
      ssyr2Handle.get().invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), n, alpha, cpx.address(), incx, cpy.address(), incy, cpa.address(), lda);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> dsyr2kHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dsyr2k").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT)));

  protected void dsyr2kK(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment pb = segment(b, offsetb); MemoryNativeCopy cpb = copy(pb);
         MemorySegment pc = segment(c, offsetc); MemoryNativeCopy cpc = copy(pc, true)) {
      dsyr2kHandle.get().invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), CblasTranspose.fromString(trans).value(), n, k, alpha, cpa.address(), lda, cpb.address(), ldb, beta, cpc.address(), ldc);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> ssyr2kHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_ssyr2k").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT)));

  protected void ssyr2kK(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    try (MemorySegment pa = segment(a, offseta); MemoryNativeCopy cpa = copy(pa);
         MemorySegment pb = segment(b, offsetb); MemoryNativeCopy cpb = copy(pb);
         MemorySegment pc = segment(c, offsetc); MemoryNativeCopy cpc = copy(pc, true)) {
      ssyr2kHandle.get().invoke(CblasLayout.CblasColMajor.value(), CblasUPLO.fromString(uplo).value(), CblasTranspose.fromString(trans).value(), n, k, alpha, cpa.address(), lda, cpb.address(), ldb, beta, cpc.address(), ldc);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> dsyrkHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dsyrk").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, double.class, MemoryAddress.class, int.class, double.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_DOUBLE, C_POINTER, C_INT)));

  protected void dsyrkK(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double beta, double[] c, int offsetc, int ldc) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final Lazy<MethodHandle> ssyrkHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_ssyrk").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, float.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_FLOAT, C_POINTER, C_INT)));

  protected void ssyrkK(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float beta, float[] c, int offsetc, int ldc) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final Lazy<MethodHandle> dtbmvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dtbmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void dtbmvK(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final Lazy<MethodHandle> stbmvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_stbmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void stbmvK(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final Lazy<MethodHandle> dtbsvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dtbsv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void dtbsvK(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final Lazy<MethodHandle> stbsvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_stbsv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void stbsvK(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final Lazy<MethodHandle> dtpmvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dtpmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_POINTER, C_INT)));

  protected void dtpmvK(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final Lazy<MethodHandle> stpmvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_stpmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_POINTER, C_INT)));

  protected void stpmvK(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final Lazy<MethodHandle> dtpsvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dtpsv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_POINTER, C_INT)));

  protected void dtpsvK(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final Lazy<MethodHandle> stpsvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_stpsv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_POINTER, C_INT)));

  protected void stpsvK(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final Lazy<MethodHandle> dtrmmHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dtrmm").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, MemoryAddress.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void dtrmmK(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final Lazy<MethodHandle> strmmHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_strmm").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void strmmK(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final Lazy<MethodHandle> dtrmvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dtrmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void dtrmvK(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final Lazy<MethodHandle> strmvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_strmv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void strmvK(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final Lazy<MethodHandle> dtrsmHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dtrsm").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, MemoryAddress.class, int.class, int.class, double.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_DOUBLE, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void dtrsmK(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final Lazy<MethodHandle> strsmHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_strsm").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, float.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_INT, C_FLOAT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void strsmK(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final Lazy<MethodHandle> dtrsvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_dtrsv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void dtrsvK(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final Lazy<MethodHandle> strsvHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_strsv").get(), MethodType.methodType(void.class, int.class, int.class, int.class, int.class, int.class, MemoryAddress.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.ofVoid(C_INT, C_INT, C_INT, C_INT, C_INT, C_POINTER, C_INT, C_POINTER, C_INT)));

  protected void strsvK(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    throw new UnsupportedOperationException("not implemented");
  }

  private final Lazy<MethodHandle> idamaxHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_idamax").get(), MethodType.methodType(int.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.of(C_INT, C_INT, C_POINTER, C_INT)));

  protected int idamaxK(int n, double[] dx, int offsetdx, int incdx) {
    try (MemorySegment pdx = segment(dx, offsetdx); MemoryNativeCopy cpdx = copy(pdx)) {
      return (int)idamaxHandle.get().invoke(n, cpdx.address(), incdx);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  private final Lazy<MethodHandle> isamaxHandle = Lazy.let(() ->
      CLinker.getInstance().downcallHandle(
        getLibrary().lookup("cblas_isamax").get(), MethodType.methodType(int.class, int.class, MemoryAddress.class, int.class),
          FunctionDescriptor.of(C_INT, C_INT, C_POINTER, C_INT)));

  protected int isamaxK(int n, float[] sx, int offsetsx, int incx) {
    try (MemorySegment psx = segment(sx, offsetsx); MemoryNativeCopy cpsx = copy(psx)) {
      return (int)isamaxHandle.get().invoke(n, cpsx.address(), incx);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }
}
