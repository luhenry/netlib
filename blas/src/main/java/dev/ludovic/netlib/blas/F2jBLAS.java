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

import dev.ludovic.netlib.BLAS;

public final class F2jBLAS extends AbstractBLAS implements dev.ludovic.netlib.JavaBLAS {

  private static final F2jBLAS instance = new F2jBLAS();

  protected F2jBLAS() {}

  public static dev.ludovic.netlib.JavaBLAS getInstance() {
    return instance;
  }

  protected double dasumK(int n, double[] x, int offsetx, int incx) {
    return org.netlib.blas.Dasum.dasum(n, x, offsetx, incx);
  }
  protected float sasumK(int n, float[] x, int offsetx, int incx) {
    return org.netlib.blas.Sasum.sasum(n, x, offsetx, incx);
  }
  protected void daxpyK(int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    org.netlib.blas.Daxpy.daxpy(n, alpha, x, offsetx, incx, y, offsety, incy);
  }
  protected void saxpyK(int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    org.netlib.blas.Saxpy.saxpy(n, alpha, x, offsetx, incx, y, offsety, incy);
  }
  protected void dcopyK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    org.netlib.blas.Dcopy.dcopy(n, x, offsetx, incx, y, offsety, incy);
  }
  protected void scopyK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    org.netlib.blas.Scopy.scopy(n, x, offsetx, incx, y, offsety, incy);
  }
  protected double ddotK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    return org.netlib.blas.Ddot.ddot(n, x, offsetx, incx, y, offsety, incy);
  }
  protected float sdotK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    return org.netlib.blas.Sdot.sdot(n, x, offsetx, incx, y, offsety, incy);
  }
  protected float sdsdotK(int n, float sb, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    return org.netlib.blas.Sdsdot.sdsdot(n, sb, x, offsetx, incx, y, offsety, incy);
  }
  protected void dgbmvK(String trans, int m, int n, int kl, int ku, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    org.netlib.blas.Dgbmv.dgbmv(trans, m, n, kl, ku, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }
  protected void sgbmvK(String trans, int m, int n, int kl, int ku, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    org.netlib.blas.Sgbmv.sgbmv(trans, m, n, kl, ku, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }
  protected void dgemmK(String transa, String transb, int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    org.netlib.blas.Dgemm.dgemm(transa, transb, m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }
  protected void sgemmK(String transa, String transb, int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    org.netlib.blas.Sgemm.sgemm(transa, transb, m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }
  protected void dgemvK(String trans, int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    org.netlib.blas.Dgemv.dgemv(trans, m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }
  protected void sgemvK(String trans, int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    org.netlib.blas.Sgemv.sgemv(trans, m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }
  protected void dgerK(int m, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
    org.netlib.blas.Dger.dger(m, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
  }
  protected void sgerK(int m, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
    org.netlib.blas.Sger.sger(m, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
  }
  protected double dnrm2K(int n, double[] x, int offsetx, int incx) {
    return org.netlib.blas.Dnrm2.dnrm2(n, x, offsetx, incx);
  }
  protected float snrm2K(int n, float[] x, int offsetx, int incx) {
    return org.netlib.blas.Snrm2.snrm2(n, x, offsetx, incx);
  }
  protected void drotK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double c, double s) {
    org.netlib.blas.Drot.drot(n, x, offsetx, incx, y, offsety, incy, c, s);
  }
  protected void srotK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float c, float s) {
    org.netlib.blas.Srot.srot(n, x, offsetx, incx, y, offsety, incy, c, s);
  }
  protected void drotmK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] param, int offsetparam) {
    org.netlib.blas.Drotm.drotm(n, x, offsetx, incx, y, offsety, incy, param, offsetparam);
  }
  protected void srotmK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] param, int offsetparam) {
    org.netlib.blas.Srotm.srotm(n, x, offsetx, incx, y, offsety, incy, param, offsetparam);
  }
  protected void drotmgK(org.netlib.util.doubleW dd1, org.netlib.util.doubleW dd2, org.netlib.util.doubleW dx1, double dy1, double[] param, int offsetparam) {
    org.netlib.blas.Drotmg.drotmg(dd1, dd2, dx1, dy1, param, offsetparam);
  }
  protected void srotmgK(org.netlib.util.floatW sd1, org.netlib.util.floatW sd2, org.netlib.util.floatW sx1, float sy1, float[] param, int offsetparam) {
    org.netlib.blas.Srotmg.srotmg(sd1, sd2, sx1, sy1, param, offsetparam);
  }
  protected void dsbmvK(String uplo, int n, int k, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    org.netlib.blas.Dsbmv.dsbmv(uplo, n, k, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }
  protected void ssbmvK(String uplo, int n, int k, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    org.netlib.blas.Ssbmv.ssbmv(uplo, n, k, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }
  protected void dscalK(int n, double alpha, double[] x, int offsetx, int incx) {
    org.netlib.blas.Dscal.dscal(n, alpha, x, offsetx, incx);
  }
  protected void sscalK(int n, float alpha, float[] x, int offsetx, int incx) {
    org.netlib.blas.Sscal.sscal(n, alpha, x, offsetx, incx);
  }
  protected void dspmvK(String uplo, int n, double alpha, double[] a, int offseta, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    org.netlib.blas.Dspmv.dspmv(uplo, n, alpha, a, offseta, x, offsetx, incx, beta, y, offsety, incy);
  }
  protected void sspmvK(String uplo, int n, float alpha, float[] a, int offseta, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    org.netlib.blas.Sspmv.sspmv(uplo, n, alpha, a, offseta, x, offsetx, incx, beta, y, offsety, incy);
  }
  protected void dsprK(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta) {
    org.netlib.blas.Dspr.dspr(uplo, n, alpha, x, offsetx, incx, a, offseta);
  }
  protected void ssprK(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta) {
    org.netlib.blas.Sspr.sspr(uplo, n, alpha, x, offsetx, incx, a, offseta);
  }
  protected void dspr2K(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta) {
    org.netlib.blas.Dspr2.dspr2(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta);
  }
  protected void sspr2K(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta) {
    org.netlib.blas.Sspr2.sspr2(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta);
  }
  protected void dswapK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    org.netlib.blas.Dswap.dswap(n, x, offsetx, incx, y, offsety, incy);
  }
  protected void sswapK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    org.netlib.blas.Sswap.sswap(n, x, offsetx, incx, y, offsety, incy);
  }
  protected void dsymmK(String side, String uplo, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    org.netlib.blas.Dsymm.dsymm(side, uplo, m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }
  protected void ssymmK(String side, String uplo, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    org.netlib.blas.Ssymm.ssymm(side, uplo, m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }
  protected void dsymvK(String uplo, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    org.netlib.blas.Dsymv.dsymv(uplo, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }
  protected void ssymvK(String uplo, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    org.netlib.blas.Ssymv.ssymv(uplo, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }
  protected void dsyrK(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta, int lda) {
    org.netlib.blas.Dsyr.dsyr(uplo, n, alpha, x, offsetx, incx, a, offseta, lda);
  }
  protected void ssyrK(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta, int lda) {
    org.netlib.blas.Ssyr.ssyr(uplo, n, alpha, x, offsetx, incx, a, offseta, lda);
  }
  protected void dsyr2K(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
    org.netlib.blas.Dsyr2.dsyr2(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
  }
  protected void ssyr2K(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
    org.netlib.blas.Ssyr2.ssyr2(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
  }
  protected void dsyr2kK(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    org.netlib.blas.Dsyr2k.dsyr2k(uplo, trans, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }
  protected void ssyr2kK(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    org.netlib.blas.Ssyr2k.ssyr2k(uplo, trans, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }
  protected void dsyrkK(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double beta, double[] c, int offsetc, int ldc) {
    org.netlib.blas.Dsyrk.dsyrk(uplo, trans, n, k, alpha, a, offseta, lda, beta, c, offsetc, ldc);
  }
  protected void ssyrkK(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float beta, float[] c, int offsetc, int ldc) {
    org.netlib.blas.Ssyrk.ssyrk(uplo, trans, n, k, alpha, a, offseta, lda, beta, c, offsetc, ldc);
  }
  protected void dtbmvK(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    org.netlib.blas.Dtbmv.dtbmv(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }
  protected void stbmvK(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    org.netlib.blas.Stbmv.stbmv(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }
  protected void dtbsvK(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    org.netlib.blas.Dtbsv.dtbsv(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }
  protected void stbsvK(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    org.netlib.blas.Stbsv.stbsv(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }
  protected void dtpmvK(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx) {
    org.netlib.blas.Dtpmv.dtpmv(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
  }
  protected void stpmvK(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx) {
    org.netlib.blas.Stpmv.stpmv(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
  }
  protected void dtpsvK(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx) {
    org.netlib.blas.Dtpsv.dtpsv(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
  }
  protected void stpsvK(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx) {
    org.netlib.blas.Stpsv.stpsv(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
  }
  protected void dtrmmK(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
    org.netlib.blas.Dtrmm.dtrmm(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }
  protected void strmmK(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
    org.netlib.blas.Strmm.strmm(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }
  protected void dtrmvK(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    org.netlib.blas.Dtrmv.dtrmv(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }
  protected void strmvK(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    org.netlib.blas.Strmv.strmv(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }
  protected void dtrsmK(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
    org.netlib.blas.Dtrsm.dtrsm(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }
  protected void strsmK(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
    org.netlib.blas.Strsm.strsm(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }
  protected void dtrsvK(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    org.netlib.blas.Dtrsv.dtrsv(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }
  protected void strsvK(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    org.netlib.blas.Strsv.strsv(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }
  protected int idamaxK(int n, double[] x, int offsetx, int incx) {
    return org.netlib.blas.Idamax.idamax(n, x, offsetx, incx);
  }
  protected int isamaxK(int n, float[] x, int offsetx, int incx) {
    return org.netlib.blas.Isamax.isamax(n, x, offsetx, incx);
  }
}
