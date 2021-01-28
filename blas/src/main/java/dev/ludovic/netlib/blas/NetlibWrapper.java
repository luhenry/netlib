/*
 * Copyright (c) Ludovic Henry. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation. Ludovic Henry designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Ludovic Henry in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact hi@ludovic.dev or visit ludovic.dev if you need additional
 * information or have any questions.
 */

package dev.ludovic.netlib.blas;

import dev.ludovic.netlib.BLAS;

abstract class NetlibWrapper extends AbstractBLAS {

  private final com.github.fommil.netlib.BLAS blas;

  protected NetlibWrapper(com.github.fommil.netlib.BLAS _blas) {
    blas = _blas;
  }

  protected double dasumK(int n, double[] x, int offsetx, int incx) {
    return blas.dasum(n, x, offsetx, incx);
  }

  protected float sasumK(int n, float[] x, int offsetx, int incx) {
    return blas.sasum(n, x, offsetx, incx);
  }

  protected void daxpyK(int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    blas.daxpy(n, alpha, x, offsetx, incx, y, offsety, incy);
  }

  protected void saxpyK(int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    blas.saxpy(n, alpha, x, offsetx, incx, y, offsety, incy);
  }

  protected void dcopyK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    blas.dcopy(n, x, offsetx, incx, y, offsety, incy);
  }

  protected void scopyK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    blas.scopy(n, x, offsetx, incx, y, offsety, incy);
  }

  protected double ddotK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    return blas.ddot(n, x, offsetx, incx, y, offsety, incy);
  }

  protected float sdotK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    return blas.sdot(n, x, offsetx, incx, y, offsety, incy);
  }

  protected float sdsdotK(int n, float sb, float[] sx, int _sx_offset, int incx, float[] sy, int _sy_offset, int incy) {
    return blas.sdsdot(n, sb, sx, _sx_offset, incx, sy, _sy_offset, incy);
  }

  protected void dgbmvK(String trans, int m, int n, int kl, int ku, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    blas.dgbmv(trans, m, n, kl, ku, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected void sgbmvK(String trans, int m, int n, int kl, int ku, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    blas.sgbmv(trans, m, n, kl, ku, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected void dgemmK(String transa, String transb, int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    blas.dgemm(transa, transb, m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected void sgemmK(String transa, String transb, int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    blas.sgemm(transa, transb, m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected void dgemvK(String trans, int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    blas.dgemv(trans, m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected void sgemvK(String trans, int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    blas.sgemv(trans, m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected void dgerK(int m, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
    blas.dger(m, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
  }

  protected void sgerK(int m, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
    blas.sger(m, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
  }

  protected double dnrm2K(int n, double[] x, int offsetx, int incx) {
    return blas.dnrm2(n, x, offsetx, incx);
  }

  protected float snrm2K(int n, float[] x, int offsetx, int incx) {
    return blas.snrm2(n, x, offsetx, incx);
  }

  protected void drotK(int n, double[] dx, int offsetdx, int incx, double[] dy, int offsetdy, int incy, double c, double s) {
    blas.drot(n, dx, offsetdx, incx, dy, offsetdy, incy, c, s);
  }

  protected void srotK(int n, float[] sx, int _sx_offset, int incx, float[] sy, int _sy_offset, int incy, float c, float s) {
    blas.srot(n, sx, _sx_offset, incx, sy, _sy_offset, incy, c, s);
  }

  protected void drotmK(int n, double[] dx, int offsetdx, int incx, double[] dy, int offsetdy, int incy, double[] dparam, int _dparam_offset) {
    blas.drotm(n, dx, offsetdx, incx, dy, offsetdy, incy, dparam, _dparam_offset);
  }

  protected void srotmK(int n, float[] sx, int _sx_offset, int incx, float[] sy, int _sy_offset, int incy, float[] sparam, int _sparam_offset) {
    blas.srotm(n, sx, _sx_offset, incx, sy, _sy_offset, incy, sparam, _sparam_offset);
  }

  protected void drotmgK(org.netlib.util.doubleW dd1, org.netlib.util.doubleW dd2, org.netlib.util.doubleW dx1, double dy1, double[] dparam, int _dparam_offset) {
    blas.drotmg(dd1, dd2, dx1, dy1, dparam, _dparam_offset);
  }

  protected void srotmgK(org.netlib.util.floatW sd1, org.netlib.util.floatW sd2, org.netlib.util.floatW sx1, float sy1, float[] sparam, int _sparam_offset) {
    blas.srotmg(sd1, sd2, sx1, sy1, sparam, _sparam_offset);
  }

  protected void dsbmvK(String uplo, int n, int k, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    blas.dsbmv(uplo, n, k, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected void ssbmvK(String uplo, int n, int k, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    blas.ssbmv(uplo, n, k, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected void dscalK(int n, double alpha, double[] x, int offsetx, int incx) {
    blas.dscal(n, alpha, x, offsetx, incx);
  }

  protected void sscalK(int n, float alpha, float[] x, int offsetx, int incx) {
    blas.sscal(n, alpha, x, offsetx, incx);
  }

  protected void dspmvK(String uplo, int n, double alpha, double[] a, int offseta, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    blas.dspmv(uplo, n, alpha, a, offseta, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected void sspmvK(String uplo, int n, float alpha, float[] ap, int offsetap, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    blas.sspmv(uplo, n, alpha, ap, offsetap, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected void dsprK(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta) {
    blas.dspr(uplo, n, alpha, x, offsetx, incx, a, offseta);
  }

  protected void ssprK(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] ap, int offsetap) {
    blas.sspr(uplo, n, alpha, x, offsetx, incx, ap, offsetap);
  }

  protected void dspr2K(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] ap, int offsetap) {
    blas.dspr2(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, ap, offsetap);
  }

  protected void sspr2K(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] ap, int offsetap) {
    blas.sspr2(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, ap, offsetap);
  }

  protected void dswapK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    blas.dswap(n, x, offsetx, incx, y, offsety, incy);
  }

  protected void sswapK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    blas.sswap(n, x, offsetx, incx, y, offsety, incy);
  }

  protected void dsymmK(String side, String uplo, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    blas.dsymm(side, uplo, m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected void ssymmK(String side, String uplo, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    blas.ssymm(side, uplo, m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected void dsymvK(String uplo, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    blas.dsymv(uplo, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected void ssymvK(String uplo, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    blas.ssymv(uplo, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected void dsyrK(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta, int lda) {
    blas.dsyr(uplo, n, alpha, x, offsetx, incx, a, offseta, lda);
  }

  protected void ssyrK(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta, int lda) {
    blas.ssyr(uplo, n, alpha, x, offsetx, incx, a, offseta, lda);
  }

  protected void dsyr2K(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
    blas.dsyr2(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
  }

  protected void ssyr2K(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
    blas.ssyr2(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
  }

  protected void dsyr2kK(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    blas.dsyr2k(uplo, trans, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected void ssyr2kK(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    blas.ssyr2k(uplo, trans, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected void dsyrkK(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double beta, double[] c, int offsetc, int ldc) {
    blas.dsyrk(uplo, trans, n, k, alpha, a, offseta, lda, beta, c, offsetc, ldc);
  }

  protected void ssyrkK(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float beta, float[] c, int offsetc, int ldc) {
    blas.ssyrk(uplo, trans, n, k, alpha, a, offseta, lda, beta, c, offsetc, ldc);
  }

  protected void dtbmvK(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    blas.dtbmv(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }

  protected void stbmvK(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    blas.stbmv(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }

  protected void dtbsvK(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    blas.dtbsv(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }

  protected void stbsvK(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    blas.stbsv(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }

  protected void dtpmvK(String uplo, String trans, String diag, int n, double[] ap, int offsetap, double[] x, int offsetx, int incx) {
    blas.dtpmv(uplo, trans, diag, n, ap, offsetap, x, offsetx, incx);
  }

  protected void stpmvK(String uplo, String trans, String diag, int n, float[] ap, int offsetap, float[] x, int offsetx, int incx) {
    blas.stpmv(uplo, trans, diag, n, ap, offsetap, x, offsetx, incx);
  }

  protected void dtpsvK(String uplo, String trans, String diag, int n, double[] ap, int offsetap, double[] x, int offsetx, int incx) {
    blas.dtpsv(uplo, trans, diag, n, ap, offsetap, x, offsetx, incx);
  }

  protected void stpsvK(String uplo, String trans, String diag, int n, float[] ap, int offsetap, float[] x, int offsetx, int incx) {
    blas.stpsv(uplo, trans, diag, n, ap, offsetap, x, offsetx, incx);
  }

  protected void dtrmmK(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
    blas.dtrmm(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }

  protected void strmmK(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
    blas.strmm(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }

  protected void dtrmvK(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    blas.dtrmv(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }

  protected void strmvK(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    blas.strmv(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }

  protected void dtrsmK(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
    blas.dtrsm(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }

  protected void strsmK(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
    blas.strsm(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }

  protected void dtrsvK(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    blas.dtrsv(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }

  protected void strsvK(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    blas.strsv(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }

  protected int idamaxK(int n, double[] x, int offsetx, int incx) {
    return blas.idamax(n, x, offsetx, incx);
  }

  protected int isamaxK(int n, float[] sx, int _sx_offset, int incx) {
    return blas.isamax(n, sx, _sx_offset, incx);
  }
}
