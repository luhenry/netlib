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

package dev.ludovic.netlib.arpack;

import java.util.Objects;

import dev.ludovic.netlib.ARPACK;

abstract class AbstractARPACK implements ARPACK {

  private void checkArgument(String method, int arg, boolean check) {
    if (!check) {
      throw new IllegalArgumentException(String.format("** On entry to '%s' parameter number %d had an illegal value", method, arg));
    }
  }

  private void checkIndex(int index, int length) {
    //FIXME: switch to Objects.checkIndex when the minimum version becomes JDK 11
    if (index < 0 || index >= length) {
      throw new IndexOutOfBoundsException(String.format("Index %s out of bounds for length %s", index, length));
    }
  }

  private <T> void requireNonNull(T obj) {
    Objects.requireNonNull(obj);
  }

  public void dmout(int lout, int m, int n, double[] a, int lda, int idigit, String ifmt) {
    dmout(lout, m, n, a, 0, lda, idigit, ifmt);
  }

  public void dmout(int lout, int m, int n, double[] a, int offseta, int lda, int idigit, String ifmt) {
    //FIXME add argument checks
    dmoutK(lout, m, n, a, offseta, lda, idigit, ifmt);
  }

  protected abstract void dmoutK(int lout, int m, int n, double[] a, int offseta, int lda, int idigit, String ifmt);

  public void smout(int lout, int m, int n, float[] a, int lda, int idigit, String ifmt) {
    smout(lout, m, n, a, 0, lda, idigit, ifmt);
  }

  public void smout(int lout, int m, int n, float[] a, int offseta, int lda, int idigit, String ifmt) {
    //FIXME add argument checks
    smoutK(lout, m, n, a, offseta, lda, idigit, ifmt);
  }

  protected abstract void smoutK(int lout, int m, int n, float[] a, int offseta, int lda, int idigit, String ifmt);

  public void dvout(int lout, int n, double[] sx, int idigit, String ifmt) {
    dvout(lout, n, sx, 0, idigit, ifmt);
  }

  public void dvout(int lout, int n, double[] sx, int offsetsx, int idigit, String ifmt) {
    //FIXME add argument checks
    dvoutK(lout, n, sx, offsetsx, idigit, ifmt);
  }

  protected abstract void dvoutK(int lout, int n, double[] sx, int offsetsx, int idigit, String ifmt);

  public void svout(int lout, int n, float[] sx, int idigit, String ifmt) {
    svout(lout, n, sx, 0, idigit, ifmt);
  }

  public void svout(int lout, int n, float[] sx, int offsetsx, int idigit, String ifmt) {
    //FIXME add argument checks
    svoutK(lout, n, sx, offsetsx, idigit, ifmt);
  }

  protected abstract void svoutK(int lout, int n, float[] sx, int offsetsx, int idigit, String ifmt);

  public void ivout(int lout, int n, int[] ix, int idigit, String ifmt) {
    ivout(lout, n, ix, 0, idigit, ifmt);
  }

  public void ivout(int lout, int n, int[] ix, int offsetix, int idigit, String ifmt) {
    //FIXME add argument checks
    ivoutK(lout, n, ix, offsetix, idigit, ifmt);
  }

  protected abstract void ivoutK(int lout, int n, int[] ix, int offsetix, int idigit, String ifmt);

  public void dgetv0(org.netlib.util.intW ido, String bmat, int itry, boolean initv, int n, int j, double[] v, int ldv, double[] resid, org.netlib.util.doubleW rnorm, int[] ipntr, double[] workd, org.netlib.util.intW ierr) {
    dgetv0(ido, bmat, itry, initv, n, j, v, 0, ldv, resid, 0, rnorm, ipntr, 0, workd, 0, ierr);
  }

  public void dgetv0(org.netlib.util.intW ido, String bmat, int itry, boolean initv, int n, int j, double[] v, int offsetv, int ldv, double[] resid, int offsetresid, org.netlib.util.doubleW rnorm, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW ierr) {
    //FIXME add argument checks
    dgetv0K(ido, bmat, itry, initv, n, j, v, offsetv, ldv, resid, offsetresid, rnorm, ipntr, offsetipntr, workd, offsetworkd, ierr);
  }

  protected abstract void dgetv0K(org.netlib.util.intW ido, String bmat, int itry, boolean initv, int n, int j, double[] v, int offsetv, int ldv, double[] resid, int offsetresid, org.netlib.util.doubleW rnorm, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW ierr);

  public void sgetv0(org.netlib.util.intW ido, String bmat, int itry, boolean initv, int n, int j, float[] v, int ldv, float[] resid, org.netlib.util.floatW rnorm, int[] ipntr, float[] workd, org.netlib.util.intW ierr) {
    sgetv0(ido, bmat, itry, initv, n, j, v, 0, ldv, resid, 0, rnorm, ipntr, 0, workd, 0, ierr);
  }

  public void sgetv0(org.netlib.util.intW ido, String bmat, int itry, boolean initv, int n, int j, float[] v, int offsetv, int ldv, float[] resid, int offsetresid, org.netlib.util.floatW rnorm, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW ierr) {
    //FIXME add argument checks
    sgetv0K(ido, bmat, itry, initv, n, j, v, offsetv, ldv, resid, offsetresid, rnorm, ipntr, offsetipntr, workd, offsetworkd, ierr);
  }

  protected abstract void sgetv0K(org.netlib.util.intW ido, String bmat, int itry, boolean initv, int n, int j, float[] v, int offsetv, int ldv, float[] resid, int offsetresid, org.netlib.util.floatW rnorm, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW ierr);

  public void dlaqrb(boolean wantt, int n, int ilo, int ihi, double[] h, int ldh, double[] wr, double[] wi, double[] z, org.netlib.util.intW info) {
    dlaqrb(wantt, n, ilo, ihi, h, 0, ldh, wr, 0, wi, 0, z, 0, info);
  }

  public void dlaqrb(boolean wantt, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] z, int offsetz, org.netlib.util.intW info) {
    //FIXME add argument checks
    dlaqrbK(wantt, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, z, offsetz, info);
  }

  protected abstract void dlaqrbK(boolean wantt, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] z, int offsetz, org.netlib.util.intW info);

  public void slaqrb(boolean wantt, int n, int ilo, int ihi, float[] h, int ldh, float[] wr, float[] wi, float[] z, org.netlib.util.intW info) {
    slaqrb(wantt, n, ilo, ihi, h, 0, ldh, wr, 0, wi, 0, z, 0, info);
  }

  public void slaqrb(boolean wantt, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] z, int offsetz, org.netlib.util.intW info) {
    //FIXME add argument checks
    slaqrbK(wantt, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, z, offsetz, info);
  }

  protected abstract void slaqrbK(boolean wantt, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] z, int offsetz, org.netlib.util.intW info);

  public void dnaitr(org.netlib.util.intW ido, String bmat, int n, int k, int np, int nb, double[] resid, org.netlib.util.doubleW rnorm, double[] v, int ldv, double[] h, int ldh, int[] ipntr, double[] workd, org.netlib.util.intW info) {
    dnaitr(ido, bmat, n, k, np, nb, resid, 0, rnorm, v, 0, ldv, h, 0, ldh, ipntr, 0, workd, 0, info);
  }

  public void dnaitr(org.netlib.util.intW ido, String bmat, int n, int k, int np, int nb, double[] resid, int offsetresid, org.netlib.util.doubleW rnorm, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW info) {
    //FIXME add argument checks
    dnaitrK(ido, bmat, n, k, np, nb, resid, offsetresid, rnorm, v, offsetv, ldv, h, offseth, ldh, ipntr, offsetipntr, workd, offsetworkd, info);
  }

  protected abstract void dnaitrK(org.netlib.util.intW ido, String bmat, int n, int k, int np, int nb, double[] resid, int offsetresid, org.netlib.util.doubleW rnorm, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW info);

  public void snaitr(org.netlib.util.intW ido, String bmat, int n, int k, int np, int nb, float[] resid, org.netlib.util.floatW rnorm, float[] v, int ldv, float[] h, int ldh, int[] ipntr, float[] workd, org.netlib.util.intW info) {
    snaitr(ido, bmat, n, k, np, nb, resid, 0, rnorm, v, 0, ldv, h, 0, ldh, ipntr, 0, workd, 0, info);
  }

  public void snaitr(org.netlib.util.intW ido, String bmat, int n, int k, int np, int nb, float[] resid, int offsetresid, org.netlib.util.floatW rnorm, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW info) {
    //FIXME add argument checks
    snaitrK(ido, bmat, n, k, np, nb, resid, offsetresid, rnorm, v, offsetv, ldv, h, offseth, ldh, ipntr, offsetipntr, workd, offsetworkd, info);
  }

  protected abstract void snaitrK(org.netlib.util.intW ido, String bmat, int n, int k, int np, int nb, float[] resid, int offsetresid, org.netlib.util.floatW rnorm, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW info);

  public void dnapps(int n, org.netlib.util.intW kev, int np, double[] shiftr, double[] shifti, double[] v, int ldv, double[] h, int ldh, double[] resid, double[] q, int ldq, double[] workl, double[] workd) {
    dnapps(n, kev, np, shiftr, 0, shifti, 0, v, 0, ldv, h, 0, ldh, resid, 0, q, 0, ldq, workl, 0, workd, 0);
  }

  public void dnapps(int n, org.netlib.util.intW kev, int np, double[] shiftr, int offsetshiftr, double[] shifti, int offsetshifti, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] resid, int offsetresid, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, double[] workd, int offsetworkd) {
    //FIXME add argument checks
    dnappsK(n, kev, np, shiftr, offsetshiftr, shifti, offsetshifti, v, offsetv, ldv, h, offseth, ldh, resid, offsetresid, q, offsetq, ldq, workl, offsetworkl, workd, offsetworkd);
  }

  protected abstract void dnappsK(int n, org.netlib.util.intW kev, int np, double[] shiftr, int offsetshiftr, double[] shifti, int offsetshifti, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] resid, int offsetresid, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, double[] workd, int offsetworkd);

  public void snapps(int n, org.netlib.util.intW kev, int np, float[] shiftr, float[] shifti, float[] v, int ldv, float[] h, int ldh, float[] resid, float[] q, int ldq, float[] workl, float[] workd) {
    snapps(n, kev, np, shiftr, 0, shifti, 0, v, 0, ldv, h, 0, ldh, resid, 0, q, 0, ldq, workl, 0, workd, 0);
  }

  public void snapps(int n, org.netlib.util.intW kev, int np, float[] shiftr, int offsetshiftr, float[] shifti, int offsetshifti, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] resid, int offsetresid, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, float[] workd, int offsetworkd) {
    //FIXME add argument checks
    snappsK(n, kev, np, shiftr, offsetshiftr, shifti, offsetshifti, v, offsetv, ldv, h, offseth, ldh, resid, offsetresid, q, offsetq, ldq, workl, offsetworkl, workd, offsetworkd);
  }

  protected abstract void snappsK(int n, org.netlib.util.intW kev, int np, float[] shiftr, int offsetshiftr, float[] shifti, int offsetshifti, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] resid, int offsetresid, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, float[] workd, int offsetworkd);

  public void dnaup2(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, double tol, double[] resid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, double[] v, int ldv, double[] h, int ldh, double[] ritzr, double[] ritzi, double[] bounds, double[] q, int ldq, double[] workl, int[] ipntr, double[] workd, org.netlib.util.intW info) {
    dnaup2(ido, bmat, n, which, nev, np, tol, resid, 0, mode, iupd, ishift, mxiter, v, 0, ldv, h, 0, ldh, ritzr, 0, ritzi, 0, bounds, 0, q, 0, ldq, workl, 0, ipntr, 0, workd, 0, info);
  }

  public void dnaup2(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, double tol, double[] resid, int offsetresid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW info) {
    //FIXME add argument checks
    dnaup2K(ido, bmat, n, which, nev, np, tol, resid, offsetresid, mode, iupd, ishift, mxiter, v, offsetv, ldv, h, offseth, ldh, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ipntr, offsetipntr, workd, offsetworkd, info);
  }

  protected abstract void dnaup2K(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, double tol, double[] resid, int offsetresid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW info);

  public void snaup2(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, float tol, float[] resid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, float[] v, int ldv, float[] h, int ldh, float[] ritzr, float[] ritzi, float[] bounds, float[] q, int ldq, float[] workl, int[] ipntr, float[] workd, org.netlib.util.intW info) {
    snaup2(ido, bmat, n, which, nev, np, tol, resid, 0, mode, iupd, ishift, mxiter, v, 0, ldv, h, 0, ldh, ritzr, 0, ritzi, 0, bounds, 0, q, 0, ldq, workl, 0, ipntr, 0, workd, 0, info);
  }

  public void snaup2(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, float tol, float[] resid, int offsetresid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW info) {
    //FIXME add argument checks
    snaup2K(ido, bmat, n, which, nev, np, tol, resid, offsetresid, mode, iupd, ishift, mxiter, v, offsetv, ldv, h, offseth, ldh, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ipntr, offsetipntr, workd, offsetworkd, info);
  }

  protected abstract void snaup2K(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, float tol, float[] resid, int offsetresid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW info);

  public void dnaupd(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.doubleW tol, double[] resid, int ncv, double[] v, int ldv, int[] iparam, int[] ipntr, double[] workd, double[] workl, int lworkl, org.netlib.util.intW info) {
    dnaupd(ido, bmat, n, which, nev, tol, resid, 0, ncv, v, 0, ldv, iparam, 0, ipntr, 0, workd, 0, workl, 0, lworkl, info);
  }

  public void dnaupd(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.doubleW tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info) {
    //FIXME add argument checks
    dnaupdK(ido, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
  }

  protected abstract void dnaupdK(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.doubleW tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);

  public void snaupd(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.floatW tol, float[] resid, int ncv, float[] v, int ldv, int[] iparam, int[] ipntr, float[] workd, float[] workl, int lworkl, org.netlib.util.intW info) {
    snaupd(ido, bmat, n, which, nev, tol, resid, 0, ncv, v, 0, ldv, iparam, 0, ipntr, 0, workd, 0, workl, 0, lworkl, info);
  }

  public void snaupd(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.floatW tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info) {
    //FIXME add argument checks
    snaupdK(ido, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
  }

  protected abstract void snaupdK(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.floatW tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);

  public void dnconv(int n, double[] ritzr, double[] ritzi, double[] bounds, double tol, org.netlib.util.intW nconv) {
    dnconv(n, ritzr, 0, ritzi, 0, bounds, 0, tol, nconv);
  }

  public void dnconv(int n, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double tol, org.netlib.util.intW nconv) {
    //FIXME add argument checks
    dnconvK(n, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, tol, nconv);
  }

  protected abstract void dnconvK(int n, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double tol, org.netlib.util.intW nconv);

  public void snconv(int n, float[] ritzr, float[] ritzi, float[] bounds, float tol, org.netlib.util.intW nconv) {
    snconv(n, ritzr, 0, ritzi, 0, bounds, 0, tol, nconv);
  }

  public void snconv(int n, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float tol, org.netlib.util.intW nconv) {
    //FIXME add argument checks
    snconvK(n, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, tol, nconv);
  }

  protected abstract void snconvK(int n, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float tol, org.netlib.util.intW nconv);

  public void dsconv(int n, double[] ritz, double[] bounds, double tol, org.netlib.util.intW nconv) {
    dsconv(n, ritz, 0, bounds, 0, tol, nconv);
  }

  public void dsconv(int n, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double tol, org.netlib.util.intW nconv) {
    //FIXME add argument checks
    dsconvK(n, ritz, offsetritz, bounds, offsetbounds, tol, nconv);
  }

  protected abstract void dsconvK(int n, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double tol, org.netlib.util.intW nconv);

  public void ssconv(int n, float[] ritz, float[] bounds, float tol, org.netlib.util.intW nconv) {
    ssconv(n, ritz, 0, bounds, 0, tol, nconv);
  }

  public void ssconv(int n, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float tol, org.netlib.util.intW nconv) {
    //FIXME add argument checks
    ssconvK(n, ritz, offsetritz, bounds, offsetbounds, tol, nconv);
  }

  protected abstract void ssconvK(int n, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float tol, org.netlib.util.intW nconv);

  public void dneigh(double rnorm, org.netlib.util.intW n, double[] h, int ldh, double[] ritzr, double[] ritzi, double[] bounds, double[] q, int ldq, double[] workl, org.netlib.util.intW ierr) {
    dneigh(rnorm, n, h, 0, ldh, ritzr, 0, ritzi, 0, bounds, 0, q, 0, ldq, workl, 0, ierr);
  }

  public void dneigh(double rnorm, org.netlib.util.intW n, double[] h, int offseth, int ldh, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, org.netlib.util.intW ierr) {
    //FIXME add argument checks
    dneighK(rnorm, n, h, offseth, ldh, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ierr);
  }

  protected abstract void dneighK(double rnorm, org.netlib.util.intW n, double[] h, int offseth, int ldh, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, org.netlib.util.intW ierr);

  public void sneigh(float rnorm, org.netlib.util.intW n, float[] h, int ldh, float[] ritzr, float[] ritzi, float[] bounds, float[] q, int ldq, float[] workl, org.netlib.util.intW ierr) {
    sneigh(rnorm, n, h, 0, ldh, ritzr, 0, ritzi, 0, bounds, 0, q, 0, ldq, workl, 0, ierr);
  }

  public void sneigh(float rnorm, org.netlib.util.intW n, float[] h, int offseth, int ldh, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, org.netlib.util.intW ierr) {
    //FIXME add argument checks
    sneighK(rnorm, n, h, offseth, ldh, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ierr);
  }

  protected abstract void sneighK(float rnorm, org.netlib.util.intW n, float[] h, int offseth, int ldh, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, org.netlib.util.intW ierr);

  public void dneupd(boolean rvec, String howmny, boolean[] select, double[] dr, double[] di, double[] z, int ldz, double sigmar, double sigmai, double[] workev, String bmat, int n, String which, org.netlib.util.intW nev, double tol, double[] resid, int ncv, double[] v, int ldv, int[] iparam, int[] ipntr, double[] workd, double[] workl, int lworkl, org.netlib.util.intW info) {
    dneupd(rvec, howmny, select, 0, dr, 0, di, 0, z, 0, ldz, sigmar, sigmai, workev, 0, bmat, n, which, nev, tol, resid, 0, ncv, v, 0, ldv, iparam, 0, ipntr, 0, workd, 0, workl, 0, lworkl, info);
  }

  public void dneupd(boolean rvec, String howmny, boolean[] select, int offsetselect, double[] dr, int offsetdr, double[] di, int offsetdi, double[] z, int offsetz, int ldz, double sigmar, double sigmai, double[] workev, int offsetworkev, String bmat, int n, String which, org.netlib.util.intW nev, double tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info) {
    //FIXME add argument checks
    dneupdK(rvec, howmny, select, offsetselect, dr, offsetdr, di, offsetdi, z, offsetz, ldz, sigmar, sigmai, workev, offsetworkev, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
  }

  protected abstract void dneupdK(boolean rvec, String howmny, boolean[] select, int offsetselect, double[] dr, int offsetdr, double[] di, int offsetdi, double[] z, int offsetz, int ldz, double sigmar, double sigmai, double[] workev, int offsetworkev, String bmat, int n, String which, org.netlib.util.intW nev, double tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);

  public void sneupd(boolean rvec, String howmny, boolean[] select, float[] dr, float[] di, float[] z, int ldz, float sigmar, float sigmai, float[] workev, String bmat, int n, String which, org.netlib.util.intW nev, float tol, float[] resid, int ncv, float[] v, int ldv, int[] iparam, int[] ipntr, float[] workd, float[] workl, int lworkl, org.netlib.util.intW info) {
    sneupd(rvec, howmny, select, 0, dr, 0, di, 0, z, 0, ldz, sigmar, sigmai, workev, 0, bmat, n, which, nev, tol, resid, 0, ncv, v, 0, ldv, iparam, 0, ipntr, 0, workd, 0, workl, 0, lworkl, info);
  }

  public void sneupd(boolean rvec, String howmny, boolean[] select, int offsetselect, float[] dr, int offsetdr, float[] di, int offsetdi, float[] z, int offsetz, int ldz, float sigmar, float sigmai, float[] workev, int offsetworkev, String bmat, int n, String which, org.netlib.util.intW nev, float tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info) {
    //FIXME add argument checks
    sneupdK(rvec, howmny, select, offsetselect, dr, offsetdr, di, offsetdi, z, offsetz, ldz, sigmar, sigmai, workev, offsetworkev, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
  }

  protected abstract void sneupdK(boolean rvec, String howmny, boolean[] select, int offsetselect, float[] dr, int offsetdr, float[] di, int offsetdi, float[] z, int offsetz, int ldz, float sigmar, float sigmai, float[] workev, int offsetworkev, String bmat, int n, String which, org.netlib.util.intW nev, float tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);

  public void dngets(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, double[] ritzr, double[] ritzi, double[] bounds, double[] shiftr, double[] shifti) {
    dngets(ishift, which, kev, np, ritzr, 0, ritzi, 0, bounds, 0, shiftr, 0, shifti, 0);
  }

  public void dngets(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] shiftr, int offsetshiftr, double[] shifti, int offsetshifti) {
    //FIXME add argument checks
    dngetsK(ishift, which, kev, np, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, shiftr, offsetshiftr, shifti, offsetshifti);
  }

  protected abstract void dngetsK(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] shiftr, int offsetshiftr, double[] shifti, int offsetshifti);

  public void sngets(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, float[] ritzr, float[] ritzi, float[] bounds, float[] shiftr, float[] shifti) {
    sngets(ishift, which, kev, np, ritzr, 0, ritzi, 0, bounds, 0, shiftr, 0, shifti, 0);
  }

  public void sngets(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] shiftr, int offsetshiftr, float[] shifti, int offsetshifti) {
    //FIXME add argument checks
    sngetsK(ishift, which, kev, np, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, shiftr, offsetshiftr, shifti, offsetshifti);
  }

  protected abstract void sngetsK(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] shiftr, int offsetshiftr, float[] shifti, int offsetshifti);

  public void dsaitr(org.netlib.util.intW ido, String bmat, int n, int k, int np, int mode, double[] resid, org.netlib.util.doubleW rnorm, double[] v, int ldv, double[] h, int ldh, int[] ipntr, double[] workd, org.netlib.util.intW info) {
    dsaitr(ido, bmat, n, k, np, mode, resid, 0, rnorm, v, 0, ldv, h, 0, ldh, ipntr, 0, workd, 0, info);
  }

  public void dsaitr(org.netlib.util.intW ido, String bmat, int n, int k, int np, int mode, double[] resid, int offsetresid, org.netlib.util.doubleW rnorm, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW info) {
    //FIXME add argument checks
    dsaitrK(ido, bmat, n, k, np, mode, resid, offsetresid, rnorm, v, offsetv, ldv, h, offseth, ldh, ipntr, offsetipntr, workd, offsetworkd, info);
  }

  protected abstract void dsaitrK(org.netlib.util.intW ido, String bmat, int n, int k, int np, int mode, double[] resid, int offsetresid, org.netlib.util.doubleW rnorm, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW info);

  public void ssaitr(org.netlib.util.intW ido, String bmat, int n, int k, int np, int mode, float[] resid, org.netlib.util.floatW rnorm, float[] v, int ldv, float[] h, int ldh, int[] ipntr, float[] workd, org.netlib.util.intW info) {
    ssaitr(ido, bmat, n, k, np, mode, resid, 0, rnorm, v, 0, ldv, h, 0, ldh, ipntr, 0, workd, 0, info);
  }

  public void ssaitr(org.netlib.util.intW ido, String bmat, int n, int k, int np, int mode, float[] resid, int offsetresid, org.netlib.util.floatW rnorm, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW info) {
    //FIXME add argument checks
    ssaitrK(ido, bmat, n, k, np, mode, resid, offsetresid, rnorm, v, offsetv, ldv, h, offseth, ldh, ipntr, offsetipntr, workd, offsetworkd, info);
  }

  protected abstract void ssaitrK(org.netlib.util.intW ido, String bmat, int n, int k, int np, int mode, float[] resid, int offsetresid, org.netlib.util.floatW rnorm, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW info);

  public void dsapps(int n, int kev, int np, double[] shift, double[] v, int ldv, double[] h, int ldh, double[] resid, double[] q, int ldq, double[] workd) {
    dsapps(n, kev, np, shift, 0, v, 0, ldv, h, 0, ldh, resid, 0, q, 0, ldq, workd, 0);
  }

  public void dsapps(int n, int kev, int np, double[] shift, int offsetshift, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] resid, int offsetresid, double[] q, int offsetq, int ldq, double[] workd, int offsetworkd) {
    //FIXME add argument checks
    dsappsK(n, kev, np, shift, offsetshift, v, offsetv, ldv, h, offseth, ldh, resid, offsetresid, q, offsetq, ldq, workd, offsetworkd);
  }

  protected abstract void dsappsK(int n, int kev, int np, double[] shift, int offsetshift, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] resid, int offsetresid, double[] q, int offsetq, int ldq, double[] workd, int offsetworkd);

  public void ssapps(int n, int kev, int np, float[] shift, float[] v, int ldv, float[] h, int ldh, float[] resid, float[] q, int ldq, float[] workd) {
    ssapps(n, kev, np, shift, 0, v, 0, ldv, h, 0, ldh, resid, 0, q, 0, ldq, workd, 0);
  }

  public void ssapps(int n, int kev, int np, float[] shift, int offsetshift, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] resid, int offsetresid, float[] q, int offsetq, int ldq, float[] workd, int offsetworkd) {
    //FIXME add argument checks
    ssappsK(n, kev, np, shift, offsetshift, v, offsetv, ldv, h, offseth, ldh, resid, offsetresid, q, offsetq, ldq, workd, offsetworkd);
  }

  protected abstract void ssappsK(int n, int kev, int np, float[] shift, int offsetshift, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] resid, int offsetresid, float[] q, int offsetq, int ldq, float[] workd, int offsetworkd);

  public void dsaup2(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, double tol, double[] resid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, double[] v, int ldv, double[] h, int ldh, double[] ritz, double[] bounds, double[] q, int ldq, double[] workl, int[] ipntr, double[] workd, org.netlib.util.intW info) {
    dsaup2(ido, bmat, n, which, nev, np, tol, resid, 0, mode, iupd, ishift, mxiter, v, 0, ldv, h, 0, ldh, ritz, 0, bounds, 0, q, 0, ldq, workl, 0, ipntr, 0, workd, 0, info);
  }

  public void dsaup2(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, double tol, double[] resid, int offsetresid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW info) {
    //FIXME add argument checks
    dsaup2K(ido, bmat, n, which, nev, np, tol, resid, offsetresid, mode, iupd, ishift, mxiter, v, offsetv, ldv, h, offseth, ldh, ritz, offsetritz, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ipntr, offsetipntr, workd, offsetworkd, info);
  }

  protected abstract void dsaup2K(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, double tol, double[] resid, int offsetresid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW info);

  public void ssaup2(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, float tol, float[] resid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, float[] v, int ldv, float[] h, int ldh, float[] ritz, float[] bounds, float[] q, int ldq, float[] workl, int[] ipntr, float[] workd, org.netlib.util.intW info) {
    ssaup2(ido, bmat, n, which, nev, np, tol, resid, 0, mode, iupd, ishift, mxiter, v, 0, ldv, h, 0, ldh, ritz, 0, bounds, 0, q, 0, ldq, workl, 0, ipntr, 0, workd, 0, info);
  }

  public void ssaup2(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, float tol, float[] resid, int offsetresid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW info) {
    //FIXME add argument checks
    ssaup2K(ido, bmat, n, which, nev, np, tol, resid, offsetresid, mode, iupd, ishift, mxiter, v, offsetv, ldv, h, offseth, ldh, ritz, offsetritz, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ipntr, offsetipntr, workd, offsetworkd, info);
  }

  protected abstract void ssaup2K(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, float tol, float[] resid, int offsetresid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW info);

  public void dseigt(double rnorm, int n, double[] h, int ldh, double[] eig, double[] bounds, double[] workl, org.netlib.util.intW ierr) {
    dseigt(rnorm, n, h, 0, ldh, eig, 0, bounds, 0, workl, 0, ierr);
  }

  public void dseigt(double rnorm, int n, double[] h, int offseth, int ldh, double[] eig, int offseteig, double[] bounds, int offsetbounds, double[] workl, int offsetworkl, org.netlib.util.intW ierr) {
    //FIXME add argument checks
    dseigtK(rnorm, n, h, offseth, ldh, eig, offseteig, bounds, offsetbounds, workl, offsetworkl, ierr);
  }

  protected abstract void dseigtK(double rnorm, int n, double[] h, int offseth, int ldh, double[] eig, int offseteig, double[] bounds, int offsetbounds, double[] workl, int offsetworkl, org.netlib.util.intW ierr);

  public void sseigt(float rnorm, int n, float[] h, int ldh, float[] eig, float[] bounds, float[] workl, org.netlib.util.intW ierr) {
    sseigt(rnorm, n, h, 0, ldh, eig, 0, bounds, 0, workl, 0, ierr);
  }

  public void sseigt(float rnorm, int n, float[] h, int offseth, int ldh, float[] eig, int offseteig, float[] bounds, int offsetbounds, float[] workl, int offsetworkl, org.netlib.util.intW ierr) {
    //FIXME add argument checks
    sseigtK(rnorm, n, h, offseth, ldh, eig, offseteig, bounds, offsetbounds, workl, offsetworkl, ierr);
  }

  protected abstract void sseigtK(float rnorm, int n, float[] h, int offseth, int ldh, float[] eig, int offseteig, float[] bounds, int offsetbounds, float[] workl, int offsetworkl, org.netlib.util.intW ierr);

  public void dsesrt(String which, boolean apply, int n, double[] x, int na, double[] a, int lda) {
    dsesrt(which, apply, n, x, 0, na, a, 0, lda);
  }

  public void dsesrt(String which, boolean apply, int n, double[] x, int offsetx, int na, double[] a, int offseta, int lda) {
    //FIXME add argument checks
    dsesrtK(which, apply, n, x, offsetx, na, a, offseta, lda);
  }

  protected abstract void dsesrtK(String which, boolean apply, int n, double[] x, int offsetx, int na, double[] a, int offseta, int lda);

  public void ssesrt(String which, boolean apply, int n, float[] x, int na, float[] a, int lda) {
    ssesrt(which, apply, n, x, 0, na, a, 0, lda);
  }

  public void ssesrt(String which, boolean apply, int n, float[] x, int offsetx, int na, float[] a, int offseta, int lda) {
    //FIXME add argument checks
    ssesrtK(which, apply, n, x, offsetx, na, a, offseta, lda);
  }

  protected abstract void ssesrtK(String which, boolean apply, int n, float[] x, int offsetx, int na, float[] a, int offseta, int lda);

  public void dsaupd(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.doubleW tol, double[] resid, int ncv, double[] v, int ldv, int[] iparam, int[] ipntr, double[] workd, double[] workl, int lworkl, org.netlib.util.intW info) {
    dsaupd(ido, bmat, n, which, nev, tol, resid, 0, ncv, v, 0, ldv, iparam, 0, ipntr, 0, workd, 0, workl, 0, lworkl, info);
  }

  public void dsaupd(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.doubleW tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info) {
    checkArgument("DSAUPD", 2, lsame("I", bmat) || lsame("G", bmat));
    checkArgument("DSAUPD", 3, n >= 0);
    checkArgument("DSAUPD", 4, lsame("LA", which) || lsame("SA", which) || lsame("LM", which) || lsame("SM", which) || lsame("BE", which));
    checkArgument("DSAUPD", 5, 0 < nev && nev < n);
    checkArgument("DSAUPD", 15, lworkl >= Math.pow(ncv, 2)+ 8 * ncv);
    requireNonNull(ido);
    requireNonNull(tol);
    requireNonNull(resid);
    requireNonNull(v);
    requireNonNull(iparam);
    requireNonNull(ipntr);
    requireNonNull(workd);
    requireNonNull(info);
    checkIndex(offsetresid + n - 1, resid.length);
    checkIndex(offsetv + n * ncv - 1, v.length);
    checkIndex(offsetiparam + 11 - 1, iparam.length);
    checkIndex(offsetipntr + 11 - 1, ipntr.length);
    checkIndex(offsetworkd + 3 * n - 1, workd.length);
    checkIndex(offsetworkl + lworkl - 1, workl.length);
    dsaupdK(ido, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
  }

  protected abstract void dsaupdK(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.doubleW tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);

  public void ssaupd(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.floatW tol, float[] resid, int ncv, float[] v, int ldv, int[] iparam, int[] ipntr, float[] workd, float[] workl, int lworkl, org.netlib.util.intW info) {
    ssaupd(ido, bmat, n, which, nev, tol, resid, 0, ncv, v, 0, ldv, iparam, 0, ipntr, 0, workd, 0, workl, 0, lworkl, info);
  }

  public void ssaupd(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.floatW tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info) {
    checkArgument("SSAUPD", 2, lsame("I", bmat) || lsame("G", bmat));
    checkArgument("SSAUPD", 3, n >= 0);
    checkArgument("SSAUPD", 4, lsame("LA", which) || lsame("SA", which) || lsame("LM", which) || lsame("SM", which) || lsame("BE", which));
    checkArgument("SSAUPD", 5, 0 < nev && nev < n);
    checkArgument("SSAUPD", 15, lworkl >= Math.pow(ncv, 2)+ 8 * ncv);
    requireNonNull(ido);
    requireNonNull(tol);
    requireNonNull(resid);
    requireNonNull(v);
    requireNonNull(iparam);
    requireNonNull(ipntr);
    requireNonNull(workd);
    requireNonNull(info);
    checkIndex(offsetresid + n - 1, resid.length);
    checkIndex(offsetv + n * ncv - 1, v.length);
    checkIndex(offsetiparam + 11 - 1, iparam.length);
    checkIndex(offsetipntr + 11 - 1, ipntr.length);
    checkIndex(offsetworkd + 3 * n - 1, workd.length);
    checkIndex(offsetworkl + lworkl - 1, workl.length);
    ssaupdK(ido, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
  }

  protected abstract void ssaupdK(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.floatW tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);

  public void dseupd(boolean rvec, String howmny, boolean[] select, double[] d, double[] z, int ldz, double sigma, String bmat, int n, String which, org.netlib.util.intW nev, double tol, double[] resid, int ncv, double[] v, int ldv, int[] iparam, int[] ipntr, double[] workd, double[] workl, int lworkl, org.netlib.util.intW info) {
    dseupd(rvec, howmny, select, 0, d, 0, z, 0, ldz, sigma, bmat, n, which, nev, tol, resid, 0, ncv, v, 0, ldv, iparam, 0, ipntr, 0, workd, 0, workl, 0, lworkl, info);
  }

  public void dseupd(boolean rvec, String howmny, boolean[] select, int offsetselect, double[] d, int offsetd, double[] z, int offsetz, int ldz, double sigma, String bmat, int n, String which, org.netlib.util.intW nev, double tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info) {
    checkArgument("DSEUPD", 2, lsame("A", howmny) || lsame("S", howmny));
    checkArgument("DSEUPD", 2, ldz >= Math.max(1, n));
    checkArgument("DSEUPD", 8, lsame("I", bmat) || lsame("G", bmat));
    checkArgument("DSEUPD", 9, n >= 0);
    checkArgument("DSEUPD", 10, lsame("LA", which) || lsame("SA", which) || lsame("LM", which) || lsame("SM", which) || lsame("BE", which));
    checkArgument("DSEUPD", 11, 0 < nev.val && nev.val < n);
    checkArgument("DSEUPD", 21, lworkl >= Math.pow(ncv, 2)+ 8 * ncv);
    requireNonNull(select);
    requireNonNull(d);
    if (rvec)
      requireNonNull(z);
    requireNonNull(resid);
    requireNonNull(v);
    requireNonNull(iparam);
    requireNonNull(ipntr);
    requireNonNull(workd);
    requireNonNull(info);
    checkIndex(offsetselect + nev.val - 1, select.length);
    checkIndex(offsetd + nev.val - 1, d.length);
    if (rvec)
      checkIndex(offsetz + n * nev.val - 1, z.length);
    checkIndex(offsetresid + n - 1, resid.length);
    checkIndex(offsetv + n * ncv - 1, v.length);
    checkIndex(offsetiparam + 11 - 1, iparam.length);
    checkIndex(offsetipntr + 11 - 1, ipntr.length);
    checkIndex(offsetworkd + 3 * n - 1, workd.length);
    checkIndex(offsetworkl + lworkl - 1, workl.length);
    dseupdK(rvec, howmny, select, offsetselect, d, offsetd, z, offsetz, ldz, sigma, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
  }

  protected abstract void dseupdK(boolean rvec, String howmny, boolean[] select, int offsetselect, double[] d, int offsetd, double[] z, int offsetz, int ldz, double sigma, String bmat, int n, String which, org.netlib.util.intW nev, double tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);

  public void sseupd(boolean rvec, String howmny, boolean[] select, float[] d, float[] z, int ldz, float sigma, String bmat, int n, String which, org.netlib.util.intW nev, float tol, float[] resid, int ncv, float[] v, int ldv, int[] iparam, int[] ipntr, float[] workd, float[] workl, int lworkl, org.netlib.util.intW info) {
    sseupd(rvec, howmny, select, 0, d, 0, z, 0, ldz, sigma, bmat, n, which, nev, tol, resid, 0, ncv, v, 0, ldv, iparam, 0, ipntr, 0, workd, 0, workl, 0, lworkl, info);
  }

  public void sseupd(boolean rvec, String howmny, boolean[] select, int offsetselect, float[] d, int offsetd, float[] z, int offsetz, int ldz, float sigma, String bmat, int n, String which, org.netlib.util.intW nev, float tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info) {
    checkArgument("SSEUPD", 2, lsame("A", howmny) || lsame("S", howmny));
    checkArgument("SSEUPD", 2, ldz >= Math.max(1, n));
    checkArgument("SSEUPD", 8, lsame("I", bmat) || lsame("G", bmat));
    checkArgument("SSEUPD", 9, n >= 0);
    checkArgument("SSEUPD", 10, lsame("LA", which) || lsame("SA", which) || lsame("LM", which) || lsame("SM", which) || lsame("BE", which));
    checkArgument("SSEUPD", 11, 0 < nev.val && nev.val < n);
    checkArgument("SSEUPD", 21, lworkl >= Math.pow(ncv, 2)+ 8 * ncv);
    requireNonNull(select);
    requireNonNull(d);
    if (rvec)
      requireNonNull(z);
    requireNonNull(resid);
    requireNonNull(v);
    requireNonNull(iparam);
    requireNonNull(ipntr);
    requireNonNull(workd);
    requireNonNull(info);
    checkIndex(offsetselect + nev.val - 1, select.length);
    checkIndex(offsetd + nev.val - 1, d.length);
    if (rvec)
      checkIndex(offsetz + n * nev.val - 1, z.length);
    checkIndex(offsetresid + n - 1, resid.length);
    checkIndex(offsetv + n * ncv - 1, v.length);
    checkIndex(offsetiparam + 11 - 1, iparam.length);
    checkIndex(offsetipntr + 11 - 1, ipntr.length);
    checkIndex(offsetworkd + 3 * n - 1, workd.length);
    checkIndex(offsetworkl + lworkl - 1, workl.length);
    sseupdK(rvec, howmny, select, offsetselect, d, offsetd, z, offsetz, ldz, sigma, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
  }

  protected abstract void sseupdK(boolean rvec, String howmny, boolean[] select, int offsetselect, float[] d, int offsetd, float[] z, int offsetz, int ldz, float sigma, String bmat, int n, String which, org.netlib.util.intW nev, float tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);

  public void dsgets(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, double[] ritz, double[] bounds, double[] shifts) {
    dsgets(ishift, which, kev, np, ritz, 0, bounds, 0, shifts, 0);
  }

  public void dsgets(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double[] shifts, int offsetshifts) {
    //FIXME add argument checks
    dsgetsK(ishift, which, kev, np, ritz, offsetritz, bounds, offsetbounds, shifts, offsetshifts);
  }

  protected abstract void dsgetsK(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double[] shifts, int offsetshifts);

  public void ssgets(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, float[] ritz, float[] bounds, float[] shifts) {
    ssgets(ishift, which, kev, np, ritz, 0, bounds, 0, shifts, 0);
  }

  public void ssgets(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float[] shifts, int offsetshifts) {
    //FIXME add argument checks
    ssgetsK(ishift, which, kev, np, ritz, offsetritz, bounds, offsetbounds, shifts, offsetshifts);
  }

  protected abstract void ssgetsK(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float[] shifts, int offsetshifts);

  public void dsortc(String which, boolean apply, int n, double[] xreal, double[] ximag, double[] y) {
    dsortc(which, apply, n, xreal, 0, ximag, 0, y, 0);
  }

  public void dsortc(String which, boolean apply, int n, double[] xreal, int offsetxreal, double[] ximag, int offsetximag, double[] y, int offsety) {
    //FIXME add argument checks
    dsortcK(which, apply, n, xreal, offsetxreal, ximag, offsetximag, y, offsety);
  }

  protected abstract void dsortcK(String which, boolean apply, int n, double[] xreal, int offsetxreal, double[] ximag, int offsetximag, double[] y, int offsety);

  public void ssortc(String which, boolean apply, int n, float[] xreal, float[] ximag, float[] y) {
    ssortc(which, apply, n, xreal, 0, ximag, 0, y, 0);
  }

  public void ssortc(String which, boolean apply, int n, float[] xreal, int offsetxreal, float[] ximag, int offsetximag, float[] y, int offsety) {
    //FIXME add argument checks
    ssortcK(which, apply, n, xreal, offsetxreal, ximag, offsetximag, y, offsety);
  }

  protected abstract void ssortcK(String which, boolean apply, int n, float[] xreal, int offsetxreal, float[] ximag, int offsetximag, float[] y, int offsety);

  public void dsortr(String which, boolean apply, int n, double[] x1, double[] x2) {
    dsortr(which, apply, n, x1, 01, x2, 02);
  }

  public void dsortr(String which, boolean apply, int n, double[] x1, int offsetx1, double[] x2, int offsetx2) {
    //FIXME add argument checks
    dsortrK(which, apply, n, x1, offsetx1, x2, offsetx2);
  }

  protected abstract void dsortrK(String which, boolean apply, int n, double[] x1, int offsetx1, double[] x2, int offsetx2);

  public void ssortr(String which, boolean apply, int n, float[] x1, float[] x2) {
    ssortr(which, apply, n, x1, 01, x2, 02);
  }

  public void ssortr(String which, boolean apply, int n, float[] x1, int offsetx1, float[] x2, int offsetx2) {
    //FIXME add argument checks
    ssortrK(which, apply, n, x1, offsetx1, x2, offsetx2);
  }

  protected abstract void ssortrK(String which, boolean apply, int n, float[] x1, int offsetx1, float[] x2, int offsetx2);

  public void dstatn() {
    //FIXME add argument checks
    dstatnK();
  }

  protected abstract void dstatnK();

  public void sstatn() {
    //FIXME add argument checks
    sstatnK();
  }

  protected abstract void sstatnK();

  public void dstats() {
    //FIXME add argument checks
    dstatsK();
  }

  protected abstract void dstatsK();

  public void sstats() {
    //FIXME add argument checks
    sstatsK();
  }

  protected abstract void sstatsK();

  public void dstqrb(int n, double[] d, double[] e, double[] z, double[] work, org.netlib.util.intW info) {
    dstqrb(n, d, 0, e, 0, z, 0, work, 0, info);
  }

  public void dstqrb(int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, double[] work, int offsetwork, org.netlib.util.intW info) {
    //FIXME add argument checks
    dstqrbK(n, d, offsetd, e, offsete, z, offsetz, work, offsetwork, info);
  }

  protected abstract void dstqrbK(int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, double[] work, int offsetwork, org.netlib.util.intW info);

  public void sstqrb(int n, float[] d, float[] e, float[] z, float[] work, org.netlib.util.intW info) {
    sstqrb(n, d, 0, e, 0, z, 0, work, 0, info);
  }

  public void sstqrb(int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, float[] work, int offsetwork, org.netlib.util.intW info) {
    //FIXME add argument checks
    sstqrbK(n, d, offsetd, e, offsete, z, offsetz, work, offsetwork, info);
  }

  protected abstract void sstqrbK(int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, float[] work, int offsetwork, org.netlib.util.intW info);

  public int icnteq(int n, int[] array, int value) {
    return icnteq(n, array, 0, value);
  }

  public int icnteq(int n, int[] array, int offsetarray, int value) {
    //FIXME add argument checksreturn 
    return icnteqK(n, array, offsetarray, value);
  }

  protected abstract int icnteqK(int n, int[] array, int offsetarray, int value);

  public void icopy(int n, int[] lx, int incx, int[] ly, int incy) {
    icopy(n, lx, 0, incx, ly, 0, incy);
  }

  public void icopy(int n, int[] lx, int offsetlx, int incx, int[] ly, int offsetly, int incy) {
    //FIXME add argument checks
    icopyK(n, lx, offsetlx, incx, ly, offsetly, incy);
  }

  protected abstract void icopyK(int n, int[] lx, int offsetlx, int incx, int[] ly, int offsetly, int incy);

  public void iset(int n, int value, int[] array, int inc) {
    iset(n, value, array, 0, inc);
  }

  public void iset(int n, int value, int[] array, int offsetarray, int inc) {
    //FIXME add argument checks
    isetK(n, value, array, offsetarray, inc);
  }

  protected abstract void isetK(int n, int value, int[] array, int offsetarray, int inc);

  public void iswap(int n, int[] sx, int incx, int[] sy, int incy) {
    iswap(n, sx, 0, incx, sy, 0, incy);
  }

  public void iswap(int n, int[] sx, int offsetsx, int incx, int[] sy, int offsetsy, int incy) {
    //FIXME add argument checks
    iswapK(n, sx, offsetsx, incx, sy, offsetsy, incy);
  }

  protected abstract void iswapK(int n, int[] sx, int offsetsx, int incx, int[] sy, int offsetsy, int incy);

  public void second(org.netlib.util.floatW t) {
    //FIXME add argument checks
    secondK(t);
  }

  protected abstract void secondK(org.netlib.util.floatW t);

  public boolean lsame(String ca, String cb) {
    return ca != null && ca.equalsIgnoreCase(cb);
  }
}
