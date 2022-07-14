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

final class F2jARPACK extends AbstractARPACK implements JavaARPACK {

  private static final F2jARPACK instance = new F2jARPACK();

  protected F2jARPACK() {}

  public static JavaARPACK getInstance() {
    return instance;
  }

  protected void dmoutK(int lout, int m, int n, double[] a, int offseta, int lda, int idigit, String ifmt) {
    org.netlib.arpack.Dmout.dmout(lout, m, n, a, offseta, lda, idigit, ifmt);
  }

  protected void smoutK(int lout, int m, int n, float[] a, int offseta, int lda, int idigit, String ifmt) {
    org.netlib.arpack.Smout.smout(lout, m, n, a, offseta, lda, idigit, ifmt);
  }

  protected void dvoutK(int lout, int n, double[] sx, int offsetsx, int idigit, String ifmt) {
    org.netlib.arpack.Dvout.dvout(lout, n, sx, offsetsx, idigit, ifmt);
  }

  protected void svoutK(int lout, int n, float[] sx, int offsetsx, int idigit, String ifmt) {
    org.netlib.arpack.Svout.svout(lout, n, sx, offsetsx, idigit, ifmt);
  }

  protected void ivoutK(int lout, int n, int[] ix, int offsetix, int idigit, String ifmt) {
    org.netlib.arpack.Ivout.ivout(lout, n, ix, offsetix, idigit, ifmt);
  }

  protected void dgetv0K(org.netlib.util.intW ido, String bmat, int itry, boolean initv, int n, int j, double[] v, int offsetv, int ldv, double[] resid, int offsetresid, org.netlib.util.doubleW rnorm, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW ierr) {
    org.netlib.arpack.Dgetv0.dgetv0(ido, bmat, itry, initv, n, j, v, offsetv, ldv, resid, offsetresid, rnorm, ipntr, offsetipntr, workd, offsetworkd, ierr);
  }

  protected void sgetv0K(org.netlib.util.intW ido, String bmat, int itry, boolean initv, int n, int j, float[] v, int offsetv, int ldv, float[] resid, int offsetresid, org.netlib.util.floatW rnorm, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW ierr) {
    org.netlib.arpack.Sgetv0.sgetv0(ido, bmat, itry, initv, n, j, v, offsetv, ldv, resid, offsetresid, rnorm, ipntr, offsetipntr, workd, offsetworkd, ierr);
  }

  protected void dlaqrbK(boolean wantt, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] z, int offsetz, org.netlib.util.intW info) {
    org.netlib.arpack.Dlaqrb.dlaqrb(wantt, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, z, offsetz, info);
  }

  protected void slaqrbK(boolean wantt, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] z, int offsetz, org.netlib.util.intW info) {
    org.netlib.arpack.Slaqrb.slaqrb(wantt, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, z, offsetz, info);
  }

  protected void dnaitrK(org.netlib.util.intW ido, String bmat, int n, int k, int np, int nb, double[] resid, int offsetresid, org.netlib.util.doubleW rnorm, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW info) {
    org.netlib.arpack.Dnaitr.dnaitr(ido, bmat, n, k, np, nb, resid, offsetresid, rnorm, v, offsetv, ldv, h, offseth, ldh, ipntr, offsetipntr, workd, offsetworkd, info);
  }

  protected void snaitrK(org.netlib.util.intW ido, String bmat, int n, int k, int np, int nb, float[] resid, int offsetresid, org.netlib.util.floatW rnorm, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW info) {
    org.netlib.arpack.Snaitr.snaitr(ido, bmat, n, k, np, nb, resid, offsetresid, rnorm, v, offsetv, ldv, h, offseth, ldh, ipntr, offsetipntr, workd, offsetworkd, info);
  }

  protected void dnappsK(int n, org.netlib.util.intW kev, int np, double[] shiftr, int offsetshiftr, double[] shifti, int offsetshifti, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] resid, int offsetresid, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, double[] workd, int offsetworkd) {
    org.netlib.arpack.Dnapps.dnapps(n, kev, np, shiftr, offsetshiftr, shifti, offsetshifti, v, offsetv, ldv, h, offseth, ldh, resid, offsetresid, q, offsetq, ldq, workl, offsetworkl, workd, offsetworkd);
  }

  protected void snappsK(int n, org.netlib.util.intW kev, int np, float[] shiftr, int offsetshiftr, float[] shifti, int offsetshifti, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] resid, int offsetresid, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, float[] workd, int offsetworkd) {
    org.netlib.arpack.Snapps.snapps(n, kev, np, shiftr, offsetshiftr, shifti, offsetshifti, v, offsetv, ldv, h, offseth, ldh, resid, offsetresid, q, offsetq, ldq, workl, offsetworkl, workd, offsetworkd);
  }

  protected void dnaup2K(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, double tol, double[] resid, int offsetresid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW info) {
    org.netlib.arpack.Dnaup2.dnaup2(ido, bmat, n, which, nev, np, tol, resid, offsetresid, mode, iupd, ishift, mxiter, v, offsetv, ldv, h, offseth, ldh, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ipntr, offsetipntr, workd, offsetworkd, info);
  }

  protected void snaup2K(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, float tol, float[] resid, int offsetresid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW info) {
    org.netlib.arpack.Snaup2.snaup2(ido, bmat, n, which, nev, np, tol, resid, offsetresid, mode, iupd, ishift, mxiter, v, offsetv, ldv, h, offseth, ldh, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ipntr, offsetipntr, workd, offsetworkd, info);
  }

  protected void dnaupdK(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.doubleW tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info) {
    org.netlib.arpack.Dnaupd.dnaupd(ido, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
  }

  protected void snaupdK(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.floatW tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info) {
    org.netlib.arpack.Snaupd.snaupd(ido, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
  }

  protected void dnconvK(int n, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double tol, org.netlib.util.intW nconv) {
    org.netlib.arpack.Dnconv.dnconv(n, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, tol, nconv);
  }

  protected void snconvK(int n, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float tol, org.netlib.util.intW nconv) {
    org.netlib.arpack.Snconv.snconv(n, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, tol, nconv);
  }

  protected void dsconvK(int n, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double tol, org.netlib.util.intW nconv) {
    org.netlib.arpack.Dsconv.dsconv(n, ritz, offsetritz, bounds, offsetbounds, tol, nconv);
  }

  protected void ssconvK(int n, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float tol, org.netlib.util.intW nconv) {
    org.netlib.arpack.Ssconv.ssconv(n, ritz, offsetritz, bounds, offsetbounds, tol, nconv);
  }

  protected void dneighK(double rnorm, org.netlib.util.intW n, double[] h, int offseth, int ldh, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, org.netlib.util.intW ierr) {
    org.netlib.arpack.Dneigh.dneigh(rnorm, n, h, offseth, ldh, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ierr);
  }

  protected void sneighK(float rnorm, org.netlib.util.intW n, float[] h, int offseth, int ldh, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, org.netlib.util.intW ierr) {
    org.netlib.arpack.Sneigh.sneigh(rnorm, n, h, offseth, ldh, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ierr);
  }

  protected void dneupdK(boolean rvec, String howmny, boolean[] select, int offsetselect, double[] dr, int offsetdr, double[] di, int offsetdi, double[] z, int offsetz, int ldz, double sigmar, double sigmai, double[] workev, int offsetworkev, String bmat, int n, String which, org.netlib.util.intW nev, double tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info) {
    org.netlib.arpack.Dneupd.dneupd(rvec, howmny, select, offsetselect, dr, offsetdr, di, offsetdi, z, offsetz, ldz, sigmar, sigmai, workev, offsetworkev, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
  }

  protected void sneupdK(boolean rvec, String howmny, boolean[] select, int offsetselect, float[] dr, int offsetdr, float[] di, int offsetdi, float[] z, int offsetz, int ldz, float sigmar, float sigmai, float[] workev, int offsetworkev, String bmat, int n, String which, org.netlib.util.intW nev, float tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info) {
    org.netlib.arpack.Sneupd.sneupd(rvec, howmny, select, offsetselect, dr, offsetdr, di, offsetdi, z, offsetz, ldz, sigmar, sigmai, workev, offsetworkev, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
  }

  protected void dngetsK(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] shiftr, int offsetshiftr, double[] shifti, int offsetshifti) {
    org.netlib.arpack.Dngets.dngets(ishift, which, kev, np, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, shiftr, offsetshiftr, shifti, offsetshifti);
  }

  protected void sngetsK(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] shiftr, int offsetshiftr, float[] shifti, int offsetshifti) {
    org.netlib.arpack.Sngets.sngets(ishift, which, kev, np, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, shiftr, offsetshiftr, shifti, offsetshifti);
  }

  protected void dsaitrK(org.netlib.util.intW ido, String bmat, int n, int k, int np, int mode, double[] resid, int offsetresid, org.netlib.util.doubleW rnorm, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW info) {
    org.netlib.arpack.Dsaitr.dsaitr(ido, bmat, n, k, np, mode, resid, offsetresid, rnorm, v, offsetv, ldv, h, offseth, ldh, ipntr, offsetipntr, workd, offsetworkd, info);
  }

  protected void ssaitrK(org.netlib.util.intW ido, String bmat, int n, int k, int np, int mode, float[] resid, int offsetresid, org.netlib.util.floatW rnorm, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW info) {
    org.netlib.arpack.Ssaitr.ssaitr(ido, bmat, n, k, np, mode, resid, offsetresid, rnorm, v, offsetv, ldv, h, offseth, ldh, ipntr, offsetipntr, workd, offsetworkd, info);
  }

  protected void dsappsK(int n, int kev, int np, double[] shift, int offsetshift, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] resid, int offsetresid, double[] q, int offsetq, int ldq, double[] workd, int offsetworkd) {
    org.netlib.arpack.Dsapps.dsapps(n, kev, np, shift, offsetshift, v, offsetv, ldv, h, offseth, ldh, resid, offsetresid, q, offsetq, ldq, workd, offsetworkd);
  }

  protected void ssappsK(int n, int kev, int np, float[] shift, int offsetshift, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] resid, int offsetresid, float[] q, int offsetq, int ldq, float[] workd, int offsetworkd) {
    org.netlib.arpack.Ssapps.ssapps(n, kev, np, shift, offsetshift, v, offsetv, ldv, h, offseth, ldh, resid, offsetresid, q, offsetq, ldq, workd, offsetworkd);
  }

  protected void dsaup2K(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, double tol, double[] resid, int offsetresid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW info) {
    org.netlib.arpack.Dsaup2.dsaup2(ido, bmat, n, which, nev, np, tol, resid, offsetresid, mode, iupd, ishift, mxiter, v, offsetv, ldv, h, offseth, ldh, ritz, offsetritz, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ipntr, offsetipntr, workd, offsetworkd, info);
  }

  protected void ssaup2K(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, float tol, float[] resid, int offsetresid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW info) {
    org.netlib.arpack.Ssaup2.ssaup2(ido, bmat, n, which, nev, np, tol, resid, offsetresid, mode, iupd, ishift, mxiter, v, offsetv, ldv, h, offseth, ldh, ritz, offsetritz, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ipntr, offsetipntr, workd, offsetworkd, info);
  }

  protected void dseigtK(double rnorm, int n, double[] h, int offseth, int ldh, double[] eig, int offseteig, double[] bounds, int offsetbounds, double[] workl, int offsetworkl, org.netlib.util.intW ierr) {
    org.netlib.arpack.Dseigt.dseigt(rnorm, n, h, offseth, ldh, eig, offseteig, bounds, offsetbounds, workl, offsetworkl, ierr);
  }

  protected void sseigtK(float rnorm, int n, float[] h, int offseth, int ldh, float[] eig, int offseteig, float[] bounds, int offsetbounds, float[] workl, int offsetworkl, org.netlib.util.intW ierr) {
    org.netlib.arpack.Sseigt.sseigt(rnorm, n, h, offseth, ldh, eig, offseteig, bounds, offsetbounds, workl, offsetworkl, ierr);
  }

  protected void dsesrtK(String which, boolean apply, int n, double[] x, int offsetx, int na, double[] a, int offseta, int lda) {
    org.netlib.arpack.Dsesrt.dsesrt(which, apply, n, x, offsetx, na, a, offseta, lda);
  }

  protected void ssesrtK(String which, boolean apply, int n, float[] x, int offsetx, int na, float[] a, int offseta, int lda) {
    org.netlib.arpack.Ssesrt.ssesrt(which, apply, n, x, offsetx, na, a, offseta, lda);
  }

  protected void dsaupdK(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.doubleW tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info) {
    org.netlib.arpack.Dsaupd.dsaupd(ido, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
  }

  protected void ssaupdK(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.floatW tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info) {
    org.netlib.arpack.Ssaupd.ssaupd(ido, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
  }

  protected void dseupdK(boolean rvec, String howmny, boolean[] select, int offsetselect, double[] d, int offsetd, double[] z, int offsetz, int ldz, double sigma, String bmat, int n, String which, org.netlib.util.intW nev, double tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info) {
    org.netlib.arpack.Dseupd.dseupd(rvec, howmny, select, offsetselect, d, offsetd, z, offsetz, ldz, sigma, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
  }

  protected void sseupdK(boolean rvec, String howmny, boolean[] select, int offsetselect, float[] d, int offsetd, float[] z, int offsetz, int ldz, float sigma, String bmat, int n, String which, org.netlib.util.intW nev, float tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info) {
    org.netlib.arpack.Sseupd.sseupd(rvec, howmny, select, offsetselect, d, offsetd, z, offsetz, ldz, sigma, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
  }

  protected void dsgetsK(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double[] shifts, int offsetshifts) {
    org.netlib.arpack.Dsgets.dsgets(ishift, which, kev, np, ritz, offsetritz, bounds, offsetbounds, shifts, offsetshifts);
  }

  protected void ssgetsK(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float[] shifts, int offsetshifts) {
    org.netlib.arpack.Ssgets.ssgets(ishift, which, kev, np, ritz, offsetritz, bounds, offsetbounds, shifts, offsetshifts);
  }

  protected void dsortcK(String which, boolean apply, int n, double[] xreal, int offsetxreal, double[] ximag, int offsetximag, double[] y, int offsety) {
    org.netlib.arpack.Dsortc.dsortc(which, apply, n, xreal, offsetxreal, ximag, offsetximag, y, offsety);
  }

  protected void ssortcK(String which, boolean apply, int n, float[] xreal, int offsetxreal, float[] ximag, int offsetximag, float[] y, int offsety) {
    org.netlib.arpack.Ssortc.ssortc(which, apply, n, xreal, offsetxreal, ximag, offsetximag, y, offsety);
  }

  protected void dsortrK(String which, boolean apply, int n, double[] x1, int offsetx1, double[] x2, int offsetx2) {
    org.netlib.arpack.Dsortr.dsortr(which, apply, n, x1, offsetx1, x2, offsetx2);
  }

  protected void ssortrK(String which, boolean apply, int n, float[] x1, int offsetx1, float[] x2, int offsetx2) {
    org.netlib.arpack.Ssortr.ssortr(which, apply, n, x1, offsetx1, x2, offsetx2);
  }

  protected void dstatnK() {
    org.netlib.arpack.Dstatn.dstatn();
  }

  protected void sstatnK() {
    org.netlib.arpack.Sstatn.sstatn();
  }

  protected void dstatsK() {
    org.netlib.arpack.Dstats.dstats();
  }

  protected void sstatsK() {
    org.netlib.arpack.Sstats.sstats();
  }

  protected void dstqrbK(int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.arpack.Dstqrb.dstqrb(n, d, offsetd, e, offsete, z, offsetz, work, offsetwork, info);
  }

  protected void sstqrbK(int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.arpack.Sstqrb.sstqrb(n, d, offsetd, e, offsete, z, offsetz, work, offsetwork, info);
  }

  protected int icnteqK(int n, int[] array, int offsetarray, int value) {
    return org.netlib.arpack.Icnteq.icnteq(n, array, offsetarray, value);
  }

  protected void icopyK(int n, int[] lx, int offsetlx, int incx, int[] ly, int offsetly, int incy) {
    org.netlib.arpack.Icopy.icopy(n, lx, offsetlx, incx, ly, offsetly, incy);
  }

  protected void isetK(int n, int value, int[] array, int offsetarray, int inc) {
    org.netlib.arpack.Iset.iset(n, value, array, offsetarray, inc);
  }

  protected void iswapK(int n, int[] sx, int offsetsx, int incx, int[] sy, int offsetsy, int incy) {
    org.netlib.arpack.Iswap.iswap(n, sx, offsetsx, incx, sy, offsetsy, incy);
  }

  protected void secondK(org.netlib.util.floatW t) {
    org.netlib.arpack.Second.second(t);
  }
}
