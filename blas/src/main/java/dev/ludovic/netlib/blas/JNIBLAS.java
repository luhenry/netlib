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

import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermissions;

final class JNIBLAS extends AbstractBLAS implements NativeBLAS {

  private static final JNIBLAS instance = new JNIBLAS();

  protected JNIBLAS() {
    String osName = System.getProperty("os.name");
    if (osName == null || osName.isEmpty()) {
        throw new RuntimeException("Unable to load native implementation");
    }
    String osArch = System.getProperty("os.arch");
    if (osArch == null || osArch.isEmpty()) {
        throw new RuntimeException("Unable to load native implementation");
    }

    Path temp;
    try (InputStream resource = this.getClass().getClassLoader().getResourceAsStream(
            String.format("resources/native/%s-%s/libnetlibblasjni.so", osName, osArch))) {
      assert resource != null;
      Files.copy(resource, temp = Files.createTempFile("libnetlibblasjni.so", "",
                                    PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rwxr-x---"))),
                  StandardCopyOption.REPLACE_EXISTING);
      temp.toFile().deleteOnExit();
    } catch (IOException e) {
      throw new RuntimeException("Unable to load native implementation", e);
    }

    System.load(temp.toString());
  }

  public static NativeBLAS getInstance() {
    return instance;
  }

  protected native double dasumK(int n, double[] x, int offsetx, int incx);
  public native boolean has_dasum();

  protected native float sasumK(int n, float[] x, int offsetx, int incx);
  public native boolean has_sasum();

  protected native void daxpyK(int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy);
  public native boolean has_daxpy();

  protected native void saxpyK(int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy);
  public native boolean has_saxpy();

  protected native void dcopyK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy);
  public native boolean has_dcopy();

  protected native void scopyK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy);
  public native boolean has_scopy();

  protected native double ddotK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy);
  public native boolean has_ddot();

  protected native float sdotK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy);
  public native boolean has_sdot();

  protected native float sdsdotK(int n, float sb, float[] sx, int offsetsx, int incsx, float[] sy, int offsetsy, int incsy);
  public native boolean has_sdsdot();

  protected native void dgbmvK(String trans, int m, int n, int kl, int ku, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);
  public native boolean has_dgbmv();

  protected native void sgbmvK(String trans, int m, int n, int kl, int ku, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);
  public native boolean has_sgbmv();

  protected native void dgemmK(String transa, String transb, int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc);
  public native boolean has_dgemm();

  protected native void sgemmK(String transa, String transb, int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc);
  public native boolean has_sgemm();

  protected native void dgemvK(String trans, int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);
  public native boolean has_dgemv();

  protected native void sgemvK(String trans, int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);
  public native boolean has_sgemv();

  protected native void dgerK(int m, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda);
  public native boolean has_dger();

  protected native void sgerK(int m, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda);
  public native boolean has_sger();

  protected native double dnrm2K(int n, double[] x, int offsetx, int incx);
  public native boolean has_dnrm2();

  protected native float snrm2K(int n, float[] x, int offsetx, int incx);
  public native boolean has_snrm2();

  protected native void drotK(int n, double[] dx, int offsetdx, int incx, double[] dy, int offsetdy, int incy, double c, double s);
  public native boolean has_drot();

  protected native void srotK(int n, float[] sx, int offsetsx, int incx, float[] sy, int offsetsy, int incy, float c, float s);
  public native boolean has_srot();

  protected native void drotmK(int n, double[] dx, int offsetdx, int incx, double[] dy, int offsetdy, int incy, double[] dparam, int offsetdparam);
  public native boolean has_drotm();

  protected native void srotmK(int n, float[] sx, int offsetsx, int incx, float[] sy, int offsetsy, int incy, float[] sparam, int offsetsparam);
  public native boolean has_srotm();

  protected native void drotmgK(org.netlib.util.doubleW dd1, org.netlib.util.doubleW dd2, org.netlib.util.doubleW dx1, double dy1, double[] dparam, int offsetdparam);
  public native boolean has_drotmg();

  protected native void srotmgK(org.netlib.util.floatW sd1, org.netlib.util.floatW sd2, org.netlib.util.floatW sx1, float sy1, float[] sparam, int offsetsparam);
  public native boolean has_srotmg();

  protected native void dsbmvK(String uplo, int n, int k, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);
  public native boolean has_dsbmv();

  protected native void ssbmvK(String uplo, int n, int k, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);
  public native boolean has_ssbmv();

  protected native void dscalK(int n, double alpha, double[] x, int offsetx, int incx);
  public native boolean has_dscal();

  protected native void sscalK(int n, float alpha, float[] x, int offsetx, int incx);
  public native boolean has_sscal();

  protected native void dspmvK(String uplo, int n, double alpha, double[] a, int offseta, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);
  public native boolean has_dspmv();

  protected native void sspmvK(String uplo, int n, float alpha, float[] a, int offseta, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);
  public native boolean has_sspmv();

  protected native void dsprK(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta);
  public native boolean has_dspr();

  protected native void ssprK(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta);
  public native boolean has_sspr();

  protected native void dspr2K(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta);
  public native boolean has_dspr2();

  protected native void sspr2K(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta);
  public native boolean has_sspr2();

  protected native void dswapK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy);
  public native boolean has_dswap();

  protected native void sswapK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy);
  public native boolean has_sswap();

  protected native void dsymmK(String side, String uplo, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc);
  public native boolean has_dsymm();

  protected native void ssymmK(String side, String uplo, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc);
  public native boolean has_ssymm();

  protected native void dsymvK(String uplo, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);
  public native boolean has_dsymv();

  protected native void ssymvK(String uplo, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);
  public native boolean has_ssymv();

  protected native void dsyrK(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta, int lda);
  public native boolean has_dsyr();

  protected native void ssyrK(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta, int lda);
  public native boolean has_ssyr();

  protected native void dsyr2K(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda);
  public native boolean has_dsyr2();

  protected native void ssyr2K(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda);
  public native boolean has_ssyr2();

  protected native void dsyr2kK(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc);
  public native boolean has_dsyr2k();

  protected native void ssyr2kK(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc);
  public native boolean has_ssyr2k();

  protected native void dsyrkK(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double beta, double[] c, int offsetc, int ldc);
  public native boolean has_dsyrk();

  protected native void ssyrkK(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float beta, float[] c, int offsetc, int ldc);
  public native boolean has_ssyrk();

  protected native void dtbmvK(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx);
  public native boolean has_dtbmv();

  protected native void stbmvK(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx);
  public native boolean has_stbmv();

  protected native void dtbsvK(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx);
  public native boolean has_dtbsv();

  protected native void stbsvK(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx);
  public native boolean has_stbsv();

  protected native void dtpmvK(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx);
  public native boolean has_dtpmv();

  protected native void stpmvK(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx);
  public native boolean has_stpmv();

  protected native void dtpsvK(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx);
  public native boolean has_dtpsv();

  protected native void stpsvK(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx);
  public native boolean has_stpsv();

  protected native void dtrmmK(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb);
  public native boolean has_dtrmm();

  protected native void strmmK(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb);
  public native boolean has_strmm();

  protected native void dtrmvK(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx);
  public native boolean has_dtrmv();

  protected native void strmvK(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx);
  public native boolean has_strmv();

  protected native void dtrsmK(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb);
  public native boolean has_dtrsm();

  protected native void strsmK(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb);
  public native boolean has_strsm();

  protected native void dtrsvK(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx);
  public native boolean has_dtrsv();

  protected native void strsvK(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx);
  public native boolean has_strsv();

  protected native int idamaxK(int n, double[] dx, int offsetdx, int incdx);
  public native boolean has_idamax();

  protected native int isamaxK(int n, float[] sx, int offsetsx, int incx);
  public native boolean has_isamax();
}
