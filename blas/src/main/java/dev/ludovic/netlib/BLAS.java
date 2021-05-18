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

package dev.ludovic.netlib;

public interface BLAS {

  public static BLAS getInstance() {
    return InstanceBuilder.BLAS.getInstance();
  }

  public default double dasum(int n, double[] x, int incx) {
    return dasum(n, x, 0, incx);
  }

  public double dasum(int n, double[] x, int offsetx, int incx);

  public default float sasum(int n, float[] x, int incx) {
    return sasum(n, x, 0, incx);
  }

  public float sasum(int n, float[] x, int offsetx, int incx);

  public default void daxpy(int n, double alpha, double[] x, int incx, double[] y, int incy) {
    daxpy(n, alpha, x, 0, incx, y, 0, incy);
  }

  public void daxpy(int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy);

  public default void saxpy(int n, float alpha, float[] x, int incx, float[] y, int incy) {
    saxpy(n, alpha, x, 0, incx, y, 0, incy);
  }

  public void saxpy(int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy);

  public default void dcopy(int n, double[] x, int incx, double[] y, int incy) {
    dcopy(n, x, 0, incx, y, 0, incy);
  }

  public void dcopy(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy);

  public default void scopy(int n, float[] x, int incx, float[] y, int incy) {
    scopy(n, x, 0, incx, y, 0, incy);
  }

  public void scopy(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy);

  public default double ddot(int n, double[] x, int incx, double[] y, int incy) {
    return ddot(n, x, 0, incx, y, 0, incy);
  }

  public double ddot(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy);

  public default float sdot(int n, float[] x, int incx, float[] y, int incy) {
    return sdot(n, x, 0, incx, y, 0, incy);
  }

  public float sdot(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy);

  public default float sdsdot(int n, float sb, float[] sx, int incx, float[] sy, int incy) {
    return sdsdot(n, sb, sx, 0, incx, sy, 0, incy);
  }

  public float sdsdot(int n, float sb, float[] sx, int offsetsx, int incx, float[] sy, int offsetsy, int incy);

  public default void dgbmv(String trans, int m, int n, int kl, int ku, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    dgbmv(trans, m, n, kl, ku, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void dgbmv(String trans, int m, int n, int kl, int ku, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);

  public default void sgbmv(String trans, int m, int n, int kl, int ku, float alpha, float[] a, int lda, float[] x, int incx, float beta, float[] y, int incy) {
    sgbmv(trans, m, n, kl, ku, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void sgbmv(String trans, int m, int n, int kl, int ku, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);

  public default void dgemm(String transa, String transb, int m, int n, int k, double alpha, double[] a, int lda, double[] b, int ldb, double beta, double[] c, int ldc) {
    dgemm(transa, transb, m, n, k, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
  }

  public void dgemm(String transa, String transb, int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc);

  public default void sgemm(String transa, String transb, int m, int n, int k, float alpha, float[] a, int lda, float[] b, int ldb, float beta, float[] c, int Ldc) {
    sgemm(transa, transb, m, n, k, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, Ldc);
  }

  public void sgemm(String transa, String transb, int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int Ldc);

  public default void dgemv(String trans, int m, int n, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    dgemv(trans, m, n, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void dgemv(String trans, int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);

  public default void sgemv(String trans, int m, int n, float alpha, float[] a, int lda, float[] x, int incx, float beta, float[] y, int incy) {
    sgemv(trans, m, n, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void sgemv(String trans, int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);

  public default void dger(int m, int n, double alpha, double[] x, int incx, double[] y, int incy, double[] a, int lda) {
    dger(m, n, alpha, x, 0, incx, y, 0, incy, a, 0, lda);
  }

  public void dger(int m, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda);

  public default void sger(int m, int n, float alpha, float[] x, int incx, float[] y, int incy, float[] a, int lda) {
    sger(m, n, alpha, x, 0, incx, y, 0, incy, a, 0, lda);
  }

  public void sger(int m, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda);

  public default double dnrm2(int n, double[] x, int incx) {
    return dnrm2(n, x, 0, incx);
  }

  public double dnrm2(int n, double[] x, int offsetx, int incx);

  public default float snrm2(int n, float[] x, int incx) {
    return snrm2(n, x, 0, incx);
  }

  public float snrm2(int n, float[] x, int offsetx, int incx);

  public default void drot(int n, double[] dx, int incx, double[] dy, int incy, double c, double s) {
    drot(n, dx, 0, incx, dy, 0, incy, c, s);
  }

  public void drot(int n, double[] dx, int offsetdx, int incx, double[] dy, int offsetdy, int incy, double c, double s);

  public default void srot(int n, float[] sx, int incx, float[] sy, int incy, float c, float s) {
    srot(n, sx, 0, incx, sy, 0, incy, c, s);
  }

  public void srot(int n, float[] sx, int offsetsx, int incx, float[] sy, int offsetsy, int incy, float c, float s);

  public void drotg(org.netlib.util.doubleW da, org.netlib.util.doubleW db, org.netlib.util.doubleW c, org.netlib.util.doubleW s);

  public void srotg(org.netlib.util.floatW sa, org.netlib.util.floatW sb, org.netlib.util.floatW c, org.netlib.util.floatW s);

  public default void drotm(int n, double[] dx, int incx, double[] dy, int incy, double[] dparam) {
    drotm(n, dx, 0, incx, dy, 0, incy, dparam, 0);
  }

  public void drotm(int n, double[] dx, int offsetdx, int incx, double[] dy, int offsetdy, int incy, double[] dparam, int offsetdparam);

  public default void srotm(int n, float[] sx, int incx, float[] sy, int incy, float[] sparam) {
    srotm(n, sx, 0, incx, sy, 0, incy, sparam, 0);
  }

  public void srotm(int n, float[] sx, int offsetsx, int incx, float[] sy, int offsetsy, int incy, float[] sparam, int offsetsparam);

  public default void drotmg(org.netlib.util.doubleW dd1, org.netlib.util.doubleW dd2, org.netlib.util.doubleW dx1, double dy1, double[] dparam) {
    drotmg(dd1, dd2, dx1, dy1, dparam, 0);
  }

  public void drotmg(org.netlib.util.doubleW dd1, org.netlib.util.doubleW dd2, org.netlib.util.doubleW dx1, double dy1, double[] dparam, int offsetdparam);

  public default void srotmg(org.netlib.util.floatW sd1, org.netlib.util.floatW sd2, org.netlib.util.floatW sx1, float sy1, float[] sparam) {
    srotmg(sd1, sd2, sx1, sy1, sparam, 0);
  }

  public void srotmg(org.netlib.util.floatW sd1, org.netlib.util.floatW sd2, org.netlib.util.floatW sx1, float sy1, float[] sparam, int offsetsparam);

  public default void dsbmv(String uplo, int n, int k, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    dsbmv(uplo, n, k, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void dsbmv(String uplo, int n, int k, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);

  public default void ssbmv(String uplo, int n, int k, float alpha, float[] a, int lda, float[] x, int incx, float beta, float[] y, int incy) {
    ssbmv(uplo, n, k, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void ssbmv(String uplo, int n, int k, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);

  public default void dscal(int n, double alpha, double[] x, int incx) {
    dscal(n, alpha, x, 0, incx);
  }

  public void dscal(int n, double alpha, double[] x, int offsetx, int incx);

  public default void sscal(int n, float alpha, float[] x, int incx) {
    sscal(n, alpha, x, 0, incx);
  }

  public void sscal(int n, float alpha, float[] x, int offsetx, int incx);

  public default void dspmv(String uplo, int n, double alpha, double[] a, double[] x, int incx, double beta, double[] y, int incy) {
    dspmv(uplo, n, alpha, a, 0, x, 0, incx, beta, y, 0, incy);
  }

  public void dspmv(String uplo, int n, double alpha, double[] a, int offseta, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);

  public default void sspmv(String uplo, int n, float alpha, float[] ap, float[] x, int incx, float beta, float[] y, int incy) {
    sspmv(uplo, n, alpha, ap, 0, x, 0, incx, beta, y, 0, incy);
  }

  public void sspmv(String uplo, int n, float alpha, float[] ap, int offsetap, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);

  public default void dspr(String uplo, int n, double alpha, double[] x, int incx, double[] a) {
    dspr(uplo, n, alpha, x, 0, incx, a, 0);
  }

  public void dspr(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta);

  public default void sspr(String uplo, int n, float alpha, float[] x, int incx, float[] ap) {
    sspr(uplo, n, alpha, x, 0, incx, ap, 0);
  }

  public void sspr(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] ap, int offsetap);

  public default void dspr2(String uplo, int n, double alpha, double[] x, int incx, double[] y, int incy, double[] ap) {
    dspr2(uplo, n, alpha, x, 0, incx, y, 0, incy, ap, 0);
  }

  public void dspr2(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] ap, int offsetap);

  public default void sspr2(String uplo, int n, float alpha, float[] x, int incx, float[] y, int incy, float[] ap) {
    sspr2(uplo, n, alpha, x, 0, incx, y, 0, incy, ap, 0);
  }

  public void sspr2(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] ap, int offsetap);

  public default void dswap(int n, double[] x, int incx, double[] y, int incy) {
    dswap(n, x, 0, incx, y, 0, incy);
  }

  public void dswap(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy);

  public default void sswap(int n, float[] x, int incx, float[] y, int incy) {
    sswap(n, x, 0, incx, y, 0, incy);
  }

  public void sswap(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy);

  public default void dsymm(String side, String uplo, int m, int n, double alpha, double[] a, int lda, double[] b, int ldb, double beta, double[] c, int Ldc) {
    dsymm(side, uplo, m, n, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, Ldc);
  }

  public void dsymm(String side, String uplo, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int Ldc);

  public default void ssymm(String side, String uplo, int m, int n, float alpha, float[] a, int lda, float[] b, int ldb, float beta, float[] c, int Ldc) {
    ssymm(side, uplo, m, n, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, Ldc);
  }

  public void ssymm(String side, String uplo, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int Ldc);

  public default void dsymv(String uplo, int n, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    dsymv(uplo, n, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void dsymv(String uplo, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);

  public default void ssymv(String uplo, int n, float alpha, float[] a, int lda, float[] x, int incx, float beta, float[] y, int incy) {
    ssymv(uplo, n, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void ssymv(String uplo, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);

  public default void dsyr(String uplo, int n, double alpha, double[] x, int incx, double[] a, int lda) {
    dsyr(uplo, n, alpha, x, 0, incx, a, 0, lda);
  }

  public void dsyr(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta, int lda);

  public default void ssyr(String uplo, int n, float alpha, float[] x, int incx, float[] a, int lda) {
    ssyr(uplo, n, alpha, x, 0, incx, a, 0, lda);
  }

  public void ssyr(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta, int lda);

  public default void dsyr2(String uplo, int n, double alpha, double[] x, int incx, double[] y, int incy, double[] a, int lda) {
    dsyr2(uplo, n, alpha, x, 0, incx, y, 0, incy, a, 0, lda);
  }

  public void dsyr2(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda);

  public default void ssyr2(String uplo, int n, float alpha, float[] x, int incx, float[] y, int incy, float[] a, int lda) {
    ssyr2(uplo, n, alpha, x, 0, incx, y, 0, incy, a, 0, lda);
  }

  public void ssyr2(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda);

  public default void dsyr2k(String uplo, String trans, int n, int k, double alpha, double[] a, int lda, double[] b, int ldb, double beta, double[] c, int Ldc) {
    dsyr2k(uplo, trans, n, k, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, Ldc);
  }

  public void dsyr2k(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int Ldc);

  public default void ssyr2k(String uplo, String trans, int n, int k, float alpha, float[] a, int lda, float[] b, int ldb, float beta, float[] c, int Ldc) {
    ssyr2k(uplo, trans, n, k, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, Ldc);
  }

  public void ssyr2k(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int Ldc);

  public default void dsyrk(String uplo, String trans, int n, int k, double alpha, double[] a, int lda, double beta, double[] c, int Ldc) {
    dsyrk(uplo, trans, n, k, alpha, a, 0, lda, beta, c, 0, Ldc);
  }

  public void dsyrk(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double beta, double[] c, int offsetc, int Ldc);

  public default void ssyrk(String uplo, String trans, int n, int k, float alpha, float[] a, int lda, float beta, float[] c, int Ldc) {
    ssyrk(uplo, trans, n, k, alpha, a, 0, lda, beta, c, 0, Ldc);
  }

  public void ssyrk(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float beta, float[] c, int offsetc, int Ldc);

  public default void dtbmv(String uplo, String trans, String diag, int n, int k, double[] a, int lda, double[] x, int incx) {
    dtbmv(uplo, trans, diag, n, k, a, 0, lda, x, 0, incx);
  }

  public void dtbmv(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx);

  public default void stbmv(String uplo, String trans, String diag, int n, int k, float[] a, int lda, float[] x, int incx) {
    stbmv(uplo, trans, diag, n, k, a, 0, lda, x, 0, incx);
  }

  public void stbmv(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx);

  public default void dtbsv(String uplo, String trans, String diag, int n, int k, double[] a, int lda, double[] x, int incx) {
    dtbsv(uplo, trans, diag, n, k, a, 0, lda, x, 0, incx);
  }

  public void dtbsv(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx);

  public default void stbsv(String uplo, String trans, String diag, int n, int k, float[] a, int lda, float[] x, int incx) {
    stbsv(uplo, trans, diag, n, k, a, 0, lda, x, 0, incx);
  }

  public void stbsv(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx);

  public default void dtpmv(String uplo, String trans, String diag, int n, double[] ap, double[] x, int incx) {
    dtpmv(uplo, trans, diag, n, ap, 0, x, 0, incx);
  }

  public void dtpmv(String uplo, String trans, String diag, int n, double[] ap, int offsetap, double[] x, int offsetx, int incx);

  public default void stpmv(String uplo, String trans, String diag, int n, float[] ap, float[] x, int incx) {
    stpmv(uplo, trans, diag, n, ap, 0, x, 0, incx);
  }

  public void stpmv(String uplo, String trans, String diag, int n, float[] ap, int offsetap, float[] x, int offsetx, int incx);

  public default void dtpsv(String uplo, String trans, String diag, int n, double[] ap, double[] x, int incx) {
    dtpsv(uplo, trans, diag, n, ap, 0, x, 0, incx);
  }

  public void dtpsv(String uplo, String trans, String diag, int n, double[] ap, int offsetap, double[] x, int offsetx, int incx);

  public default void stpsv(String uplo, String trans, String diag, int n, float[] ap, float[] x, int incx) {
    stpsv(uplo, trans, diag, n, ap, 0, x, 0, incx);
  }

  public void stpsv(String uplo, String trans, String diag, int n, float[] ap, int offsetap, float[] x, int offsetx, int incx);

  public default void dtrmm(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int lda, double[] b, int ldb) {
    dtrmm(side, uplo, transa, diag, m, n, alpha, a, 0, lda, b, 0, ldb);
  }

  public void dtrmm(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb);

  public default void strmm(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int lda, float[] b, int ldb) {
    strmm(side, uplo, transa, diag, m, n, alpha, a, 0, lda, b, 0, ldb);
  }

  public void strmm(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb);

  public default void dtrmv(String uplo, String trans, String diag, int n, double[] a, int lda, double[] x, int incx) {
    dtrmv(uplo, trans, diag, n, a, 0, lda, x, 0, incx);
  }

  public void dtrmv(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx);

  public default void strmv(String uplo, String trans, String diag, int n, float[] a, int lda, float[] x, int incx) {
    strmv(uplo, trans, diag, n, a, 0, lda, x, 0, incx);
  }

  public void strmv(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx);

  public default void dtrsm(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int lda, double[] b, int ldb) {
    dtrsm(side, uplo, transa, diag, m, n, alpha, a, 0, lda, b, 0, ldb);
  }

  public void dtrsm(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb);

  public default void strsm(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int lda, float[] b, int ldb) {
    strsm(side, uplo, transa, diag, m, n, alpha, a, 0, lda, b, 0, ldb);
  }

  public void strsm(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb);

  public default void dtrsv(String uplo, String trans, String diag, int n, double[] a, int lda, double[] x, int incx) {
    dtrsv(uplo, trans, diag, n, a, 0, lda, x, 0, incx);
  }

  public void dtrsv(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx);

  public default void strsv(String uplo, String trans, String diag, int n, float[] a, int lda, float[] x, int incx) {
    strsv(uplo, trans, diag, n, a, 0, lda, x, 0, incx);
  }

  public void strsv(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx);

  public default int idamax(int n, double[] x, int incx) {
    return idamax(n, x, 0, incx);
  }

  public int idamax(int n, double[] x, int offsetx, int incx);

  public default int isamax(int n, float[] sx, int incx) {
    return isamax(n, sx, 0, incx);
  }

  public int isamax(int n, float[] sx, int offsetsx, int incx);

  public boolean lsame(String ca, String cb);
}
