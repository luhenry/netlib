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

public interface ARPACK {

  public void dmout(int lout, int m, int n, double[] a, int lda, int idigit, String ifmt);
  public void dmout(int lout, int m, int n, double[] a, int offseta, int lda, int idigit, String ifmt);
  public void smout(int lout, int m, int n, float[] a, int lda, int idigit, String ifmt);
  public void smout(int lout, int m, int n, float[] a, int offseta, int lda, int idigit, String ifmt);

  public void dvout(int lout, int n, double[] sx, int idigit, String ifmt);
  public void dvout(int lout, int n, double[] sx, int offsetsx, int idigit, String ifmt);
  public void svout(int lout, int n, float[] sx, int idigit, String ifmt);
  public void svout(int lout, int n, float[] sx, int offsetsx, int idigit, String ifmt);
  public void ivout(int lout, int n, int[] ix, int idigit, String ifmt);
  public void ivout(int lout, int n, int[] ix, int offsetix, int idigit, String ifmt);

  public void dgetv0(org.netlib.util.intW ido, String bmat, int itry, boolean initv, int n, int j, double[] v, int ldv, double[] resid, org.netlib.util.doubleW rnorm, int[] ipntr, double[] workd, org.netlib.util.intW ierr);
  public void dgetv0(org.netlib.util.intW ido, String bmat, int itry, boolean initv, int n, int j, double[] v, int offsetv, int ldv, double[] resid, int offsetresid, org.netlib.util.doubleW rnorm, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW ierr);
  public void sgetv0(org.netlib.util.intW ido, String bmat, int itry, boolean initv, int n, int j, float[] v, int ldv, float[] resid, org.netlib.util.floatW rnorm, int[] ipntr, float[] workd, org.netlib.util.intW ierr);
  public void sgetv0(org.netlib.util.intW ido, String bmat, int itry, boolean initv, int n, int j, float[] v, int offsetv, int ldv, float[] resid, int offsetresid, org.netlib.util.floatW rnorm, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW ierr);

  public void dlaqrb(boolean wantt, int n, int ilo, int ihi, double[] h, int ldh, double[] wr, double[] wi, double[] z, org.netlib.util.intW info);
  public void dlaqrb(boolean wantt, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] z, int offsetz, org.netlib.util.intW info);
  public void slaqrb(boolean wantt, int n, int ilo, int ihi, float[] h, int ldh, float[] wr, float[] wi, float[] z, org.netlib.util.intW info);
  public void slaqrb(boolean wantt, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] z, int offsetz, org.netlib.util.intW info);

  public void dnaitr(org.netlib.util.intW ido, String bmat, int n, int k, int np, int nb, double[] resid, org.netlib.util.doubleW rnorm, double[] v, int ldv, double[] h, int ldh, int[] ipntr, double[] workd, org.netlib.util.intW info);
  public void dnaitr(org.netlib.util.intW ido, String bmat, int n, int k, int np, int nb, double[] resid, int offsetresid, org.netlib.util.doubleW rnorm, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW info);
  public void snaitr(org.netlib.util.intW ido, String bmat, int n, int k, int np, int nb, float[] resid, org.netlib.util.floatW rnorm, float[] v, int ldv, float[] h, int ldh, int[] ipntr, float[] workd, org.netlib.util.intW info);
  public void snaitr(org.netlib.util.intW ido, String bmat, int n, int k, int np, int nb, float[] resid, int offsetresid, org.netlib.util.floatW rnorm, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW info);

  public void dnapps(int n, org.netlib.util.intW kev, int np, double[] shiftr, double[] shifti, double[] v, int ldv, double[] h, int ldh, double[] resid, double[] q, int ldq, double[] workl, double[] workd);
  public void dnapps(int n, org.netlib.util.intW kev, int np, double[] shiftr, int offsetshiftr, double[] shifti, int offsetshifti, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] resid, int offsetresid, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, double[] workd, int offsetworkd);
  public void snapps(int n, org.netlib.util.intW kev, int np, float[] shiftr, float[] shifti, float[] v, int ldv, float[] h, int ldh, float[] resid, float[] q, int ldq, float[] workl, float[] workd);
  public void snapps(int n, org.netlib.util.intW kev, int np, float[] shiftr, int offsetshiftr, float[] shifti, int offsetshifti, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] resid, int offsetresid, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, float[] workd, int offsetworkd);

  public void dnaup2(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, double tol, double[] resid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, double[] v, int ldv, double[] h, int ldh, double[] ritzr, double[] ritzi, double[] bounds, double[] q, int ldq, double[] workl, int[] ipntr, double[] workd, org.netlib.util.intW info);
  public void dnaup2(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, double tol, double[] resid, int offsetresid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW info);
  public void snaup2(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, float tol, float[] resid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, float[] v, int ldv, float[] h, int ldh, float[] ritzr, float[] ritzi, float[] bounds, float[] q, int ldq, float[] workl, int[] ipntr, float[] workd, org.netlib.util.intW info);
  public void snaup2(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, float tol, float[] resid, int offsetresid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW info);

  public void dnaupd(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.doubleW tol, double[] resid, int ncv, double[] v, int ldv, int[] iparam, int[] ipntr, double[] workd, double[] workl, int lworkl, org.netlib.util.intW info);
  public void dnaupd(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.doubleW tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);
  public void snaupd(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.floatW tol, float[] resid, int ncv, float[] v, int ldv, int[] iparam, int[] ipntr, float[] workd, float[] workl, int lworkl, org.netlib.util.intW info);
  public void snaupd(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.floatW tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);

  public void dnconv(int n, double[] ritzr, double[] ritzi, double[] bounds, double tol, org.netlib.util.intW nconv);
  public void dnconv(int n, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double tol, org.netlib.util.intW nconv);
  public void snconv(int n, float[] ritzr, float[] ritzi, float[] bounds, float tol, org.netlib.util.intW nconv);
  public void snconv(int n, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float tol, org.netlib.util.intW nconv);

  public void dsconv(int n, double[] ritz, double[] bounds, double tol, org.netlib.util.intW nconv);
  public void dsconv(int n, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double tol, org.netlib.util.intW nconv);
  public void ssconv(int n, float[] ritz, float[] bounds, float tol, org.netlib.util.intW nconv);
  public void ssconv(int n, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float tol, org.netlib.util.intW nconv);

  public void dneigh(double rnorm, org.netlib.util.intW n, double[] h, int ldh, double[] ritzr, double[] ritzi, double[] bounds, double[] q, int ldq, double[] workl, org.netlib.util.intW ierr);
  public void dneigh(double rnorm, org.netlib.util.intW n, double[] h, int offseth, int ldh, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, org.netlib.util.intW ierr);
  public void sneigh(float rnorm, org.netlib.util.intW n, float[] h, int ldh, float[] ritzr, float[] ritzi, float[] bounds, float[] q, int ldq, float[] workl, org.netlib.util.intW ierr);
  public void sneigh(float rnorm, org.netlib.util.intW n, float[] h, int offseth, int ldh, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, org.netlib.util.intW ierr);

  public void dneupd(boolean rvec, String howmny, boolean[] select, double[] dr, double[] di, double[] z, int ldz, double sigmar, double sigmai, double[] workev, String bmat, int n, String which, org.netlib.util.intW nev, double tol, double[] resid, int ncv, double[] v, int ldv, int[] iparam, int[] ipntr, double[] workd, double[] workl, int lworkl, org.netlib.util.intW info);
  public void dneupd(boolean rvec, String howmny, boolean[] select, int offsetselect, double[] dr, int offsetdr, double[] di, int offsetdi, double[] z, int offsetz, int ldz, double sigmar, double sigmai, double[] workev, int offsetworkev, String bmat, int n, String which, org.netlib.util.intW nev, double tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);
  public void sneupd(boolean rvec, String howmny, boolean[] select, float[] dr, float[] di, float[] z, int ldz, float sigmar, float sigmai, float[] workev, String bmat, int n, String which, org.netlib.util.intW nev, float tol, float[] resid, int ncv, float[] v, int ldv, int[] iparam, int[] ipntr, float[] workd, float[] workl, int lworkl, org.netlib.util.intW info);
  public void sneupd(boolean rvec, String howmny, boolean[] select, int offsetselect, float[] dr, int offsetdr, float[] di, int offsetdi, float[] z, int offsetz, int ldz, float sigmar, float sigmai, float[] workev, int offsetworkev, String bmat, int n, String which, org.netlib.util.intW nev, float tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);

  public void dngets(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, double[] ritzr, double[] ritzi, double[] bounds, double[] shiftr, double[] shifti);
  public void dngets(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] shiftr, int offsetshiftr, double[] shifti, int offsetshifti);
  public void sngets(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, float[] ritzr, float[] ritzi, float[] bounds, float[] shiftr, float[] shifti);
  public void sngets(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] shiftr, int offsetshiftr, float[] shifti, int offsetshifti);

  public void dsaitr(org.netlib.util.intW ido, String bmat, int n, int k, int np, int mode, double[] resid, org.netlib.util.doubleW rnorm, double[] v, int ldv, double[] h, int ldh, int[] ipntr, double[] workd, org.netlib.util.intW info);
  public void dsaitr(org.netlib.util.intW ido, String bmat, int n, int k, int np, int mode, double[] resid, int offsetresid, org.netlib.util.doubleW rnorm, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW info);
  public void ssaitr(org.netlib.util.intW ido, String bmat, int n, int k, int np, int mode, float[] resid, org.netlib.util.floatW rnorm, float[] v, int ldv, float[] h, int ldh, int[] ipntr, float[] workd, org.netlib.util.intW info);
  public void ssaitr(org.netlib.util.intW ido, String bmat, int n, int k, int np, int mode, float[] resid, int offsetresid, org.netlib.util.floatW rnorm, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW info);

  public void dsapps(int n, int kev, int np, double[] shift, double[] v, int ldv, double[] h, int ldh, double[] resid, double[] q, int ldq, double[] workd);
  public void dsapps(int n, int kev, int np, double[] shift, int offsetshift, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] resid, int offsetresid, double[] q, int offsetq, int ldq, double[] workd, int offsetworkd);
  public void ssapps(int n, int kev, int np, float[] shift, float[] v, int ldv, float[] h, int ldh, float[] resid, float[] q, int ldq, float[] workd);
  public void ssapps(int n, int kev, int np, float[] shift, int offsetshift, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] resid, int offsetresid, float[] q, int offsetq, int ldq, float[] workd, int offsetworkd);

  public void dsaup2(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, double tol, double[] resid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, double[] v, int ldv, double[] h, int ldh, double[] ritz, double[] bounds, double[] q, int ldq, double[] workl, int[] ipntr, double[] workd, org.netlib.util.intW info);
  public void dsaup2(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, double tol, double[] resid, int offsetresid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW info);
  public void ssaup2(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, float tol, float[] resid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, float[] v, int ldv, float[] h, int ldh, float[] ritz, float[] bounds, float[] q, int ldq, float[] workl, int[] ipntr, float[] workd, org.netlib.util.intW info);
  public void ssaup2(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, float tol, float[] resid, int offsetresid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW info);

  public void dseigt(double rnorm, int n, double[] h, int ldh, double[] eig, double[] bounds, double[] workl, org.netlib.util.intW ierr);
  public void dseigt(double rnorm, int n, double[] h, int offseth, int ldh, double[] eig, int offseteig, double[] bounds, int offsetbounds, double[] workl, int offsetworkl, org.netlib.util.intW ierr);
  public void sseigt(float rnorm, int n, float[] h, int ldh, float[] eig, float[] bounds, float[] workl, org.netlib.util.intW ierr);
  public void sseigt(float rnorm, int n, float[] h, int offseth, int ldh, float[] eig, int offseteig, float[] bounds, int offsetbounds, float[] workl, int offsetworkl, org.netlib.util.intW ierr);

  public void dsesrt(String which, boolean apply, int n, double[] x, int na, double[] a, int lda);
  public void dsesrt(String which, boolean apply, int n, double[] x, int offsetx, int na, double[] a, int offseta, int lda);
  public void ssesrt(String which, boolean apply, int n, float[] x, int na, float[] a, int lda);
  public void ssesrt(String which, boolean apply, int n, float[] x, int offsetx, int na, float[] a, int offseta, int lda);

  public void dsaupd(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.doubleW tol, double[] resid, int ncv, double[] v, int ldv, int[] iparam, int[] ipntr, double[] workd, double[] workl, int lworkl, org.netlib.util.intW info);
  public void dsaupd(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.doubleW tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);
  public void ssaupd(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.floatW tol, float[] resid, int ncv, float[] v, int ldv, int[] iparam, int[] ipntr, float[] workd, float[] workl, int lworkl, org.netlib.util.intW info);
  public void ssaupd(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.floatW tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);

  public void dseupd(boolean rvec, String howmny, boolean[] select, double[] d, double[] z, int ldz, double sigma, String bmat, int n, String which, org.netlib.util.intW nev, double tol, double[] resid, int ncv, double[] v, int ldv, int[] iparam, int[] ipntr, double[] workd, double[] workl, int lworkl, org.netlib.util.intW info);
  public void dseupd(boolean rvec, String howmny, boolean[] select, int offsetselect, double[] d, int offsetd, double[] z, int offsetz, int ldz, double sigma, String bmat, int n, String which, org.netlib.util.intW nev, double tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);
  public void sseupd(boolean rvec, String howmny, boolean[] select, float[] d, float[] z, int ldz, float sigma, String bmat, int n, String which, org.netlib.util.intW nev, float tol, float[] resid, int ncv, float[] v, int ldv, int[] iparam, int[] ipntr, float[] workd, float[] workl, int lworkl, org.netlib.util.intW info);
  public void sseupd(boolean rvec, String howmny, boolean[] select, int offsetselect, float[] d, int offsetd, float[] z, int offsetz, int ldz, float sigma, String bmat, int n, String which, org.netlib.util.intW nev, float tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);

  public void dsgets(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, double[] ritz, double[] bounds, double[] shifts);
  public void dsgets(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double[] shifts, int offsetshifts);
  public void ssgets(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, float[] ritz, float[] bounds, float[] shifts);
  public void ssgets(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float[] shifts, int offsetshifts);

  public void dsortc(String which, boolean apply, int n, double[] xreal, double[] ximag, double[] y);
  public void dsortc(String which, boolean apply, int n, double[] xreal, int offsetxreal, double[] ximag, int offsetximag, double[] y, int offsety);
  public void ssortc(String which, boolean apply, int n, float[] xreal, float[] ximag, float[] y);
  public void ssortc(String which, boolean apply, int n, float[] xreal, int offsetxreal, float[] ximag, int offsetximag, float[] y, int offsety);

  public void dsortr(String which, boolean apply, int n, double[] x1, double[] x2);
  public void dsortr(String which, boolean apply, int n, double[] x1, int offsetx1, double[] x2, int offsetx2);
  public void ssortr(String which, boolean apply, int n, float[] x1, float[] x2);
  public void ssortr(String which, boolean apply, int n, float[] x1, int offsetx1, float[] x2, int offsetx2);

  public void dstatn();
  public void sstatn();

  public void dstats();
  public void sstats();

  public void dstqrb(int n, double[] d, double[] e, double[] z, double[] work, org.netlib.util.intW info);
  public void dstqrb(int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, double[] work, int offsetwork, org.netlib.util.intW info);
  public void sstqrb(int n, float[] d, float[] e, float[] z, float[] work, org.netlib.util.intW info);
  public void sstqrb(int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, float[] work, int offsetwork, org.netlib.util.intW info);

  public int icnteq(int n, int[] array, int value);
  public int icnteq(int n, int[] array, int offsetarray, int value);

  public void icopy(int n, int[] lx, int incx, int[] ly, int incy);
  public void icopy(int n, int[] lx, int offsetlx, int incx, int[] ly, int offsetly, int incy);

  public void iset(int n, int value, int[] array, int inc);
  public void iset(int n, int value, int[] array, int offsetarray, int inc);

  public void iswap(int n, int[] sx, int incx, int[] sy, int incy);
  public void iswap(int n, int[] sx, int offsetsx, int incx, int[] sy, int offsetsy, int incy);

  public void second(org.netlib.util.floatW t);
}
