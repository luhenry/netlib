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

import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermissions;

final class JNIARPACK extends AbstractARPACK implements dev.ludovic.netlib.NativeARPACK {

  private static final JNIARPACK instance = new JNIARPACK();

  protected JNIARPACK() {
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
            String.format("resources/native/%s-%s/libnetlibarpackjni.so", osName, osArch))) {
      assert resource != null;
      Files.copy(resource, temp = Files.createTempFile("libnetlibarpackjni.so", "",
                                    PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rwxr-x---"))),
                  StandardCopyOption.REPLACE_EXISTING);
      temp.toFile().deleteOnExit();
    } catch (IOException e) {
      throw new RuntimeException("Unable to load native implementation", e);
    }

    System.load(temp.toString());
  }

  public static dev.ludovic.netlib.NativeARPACK getInstance() {
    return instance;
  }

  protected native void dmoutK(int lout, int m, int n, double[] a, int offseta, int lda, int idigit, String ifmt);
  public native boolean has_dmout();

  protected native void smoutK(int lout, int m, int n, float[] a, int offseta, int lda, int idigit, String ifmt);
  public native boolean has_smout();

  protected native void dvoutK(int lout, int n, double[] sx, int offsetsx, int idigit, String ifmt);
  public native boolean has_dvout();

  protected native void svoutK(int lout, int n, float[] sx, int offsetsx, int idigit, String ifmt);
  public native boolean has_svout();

  protected native void ivoutK(int lout, int n, int[] ix, int offsetix, int idigit, String ifmt);
  public native boolean has_ivout();

  protected native void dgetv0K(org.netlib.util.intW ido, String bmat, int itry, boolean initv, int n, int j, double[] v, int offsetv, int ldv, double[] resid, int offsetresid, org.netlib.util.doubleW rnorm, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW ierr);
  public native boolean has_dgetv0();

  protected native void sgetv0K(org.netlib.util.intW ido, String bmat, int itry, boolean initv, int n, int j, float[] v, int offsetv, int ldv, float[] resid, int offsetresid, org.netlib.util.floatW rnorm, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW ierr);
  public native boolean has_sgetv0();

  protected native void dlaqrbK(boolean wantt, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] z, int offsetz, org.netlib.util.intW info);
  public native boolean has_dlaqrb();

  protected native void slaqrbK(boolean wantt, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] z, int offsetz, org.netlib.util.intW info);
  public native boolean has_slaqrb();

  protected native void dnaitrK(org.netlib.util.intW ido, String bmat, int n, int k, int np, int nb, double[] resid, int offsetresid, org.netlib.util.doubleW rnorm, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW info);
  public native boolean has_dnaitr();

  protected native void snaitrK(org.netlib.util.intW ido, String bmat, int n, int k, int np, int nb, float[] resid, int offsetresid, org.netlib.util.floatW rnorm, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW info);
  public native boolean has_snaitr();

  protected native void dnappsK(int n, org.netlib.util.intW kev, int np, double[] shiftr, int offsetshiftr, double[] shifti, int offsetshifti, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] resid, int offsetresid, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, double[] workd, int offsetworkd);
  public native boolean has_dnapps();

  protected native void snappsK(int n, org.netlib.util.intW kev, int np, float[] shiftr, int offsetshiftr, float[] shifti, int offsetshifti, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] resid, int offsetresid, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, float[] workd, int offsetworkd);
  public native boolean has_snapps();

  protected native void dnaup2K(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, double tol, double[] resid, int offsetresid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW info);
  public native boolean has_dnaup2();

  protected native void snaup2K(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, float tol, float[] resid, int offsetresid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW info);
  public native boolean has_snaup2();

  protected native void dnaupdK(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.doubleW tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);
  public native boolean has_dnaupd();

  protected native void snaupdK(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.floatW tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);
  public native boolean has_snaupd();

  protected native void dnconvK(int n, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double tol, org.netlib.util.intW nconv);
  public native boolean has_dnconv();

  protected native void snconvK(int n, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float tol, org.netlib.util.intW nconv);
  public native boolean has_snconv();

  protected native void dsconvK(int n, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double tol, org.netlib.util.intW nconv);
  public native boolean has_dsconv();

  protected native void ssconvK(int n, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float tol, org.netlib.util.intW nconv);
  public native boolean has_ssconv();

  protected native void dneighK(double rnorm, org.netlib.util.intW n, double[] h, int offseth, int ldh, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, org.netlib.util.intW ierr);
  public native boolean has_dneigh();

  protected native void sneighK(float rnorm, org.netlib.util.intW n, float[] h, int offseth, int ldh, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, org.netlib.util.intW ierr);
  public native boolean has_sneigh();

  protected native void dneupdK(boolean rvec, String howmny, boolean[] select, int offsetselect, double[] dr, int offsetdr, double[] di, int offsetdi, double[] z, int offsetz, int ldz, double sigmar, double sigmai, double[] workev, int offsetworkev, String bmat, int n, String which, org.netlib.util.intW nev, double tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);
  public native boolean has_dneupd();

  protected native void sneupdK(boolean rvec, String howmny, boolean[] select, int offsetselect, float[] dr, int offsetdr, float[] di, int offsetdi, float[] z, int offsetz, int ldz, float sigmar, float sigmai, float[] workev, int offsetworkev, String bmat, int n, String which, org.netlib.util.intW nev, float tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);
  public native boolean has_sneupd();

  protected native void dngetsK(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] shiftr, int offsetshiftr, double[] shifti, int offsetshifti);
  public native boolean has_dngets();

  protected native void sngetsK(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] shiftr, int offsetshiftr, float[] shifti, int offsetshifti);
  public native boolean has_sngets();

  protected native void dsaitrK(org.netlib.util.intW ido, String bmat, int n, int k, int np, int mode, double[] resid, int offsetresid, org.netlib.util.doubleW rnorm, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW info);
  public native boolean has_dsaitr();

  protected native void ssaitrK(org.netlib.util.intW ido, String bmat, int n, int k, int np, int mode, float[] resid, int offsetresid, org.netlib.util.floatW rnorm, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW info);
  public native boolean has_ssaitr();

  protected native void dsappsK(int n, int kev, int np, double[] shift, int offsetshift, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] resid, int offsetresid, double[] q, int offsetq, int ldq, double[] workd, int offsetworkd);
  public native boolean has_dsapps();

  protected native void ssappsK(int n, int kev, int np, float[] shift, int offsetshift, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] resid, int offsetresid, float[] q, int offsetq, int ldq, float[] workd, int offsetworkd);
  public native boolean has_ssapps();

  protected native void dsaup2K(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, double tol, double[] resid, int offsetresid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, org.netlib.util.intW info);
  public native boolean has_dsaup2();

  protected native void ssaup2K(org.netlib.util.intW ido, String bmat, int n, String which, org.netlib.util.intW nev, org.netlib.util.intW np, float tol, float[] resid, int offsetresid, int mode, int iupd, int ishift, org.netlib.util.intW mxiter, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, org.netlib.util.intW info);
  public native boolean has_ssaup2();

  protected native void dseigtK(double rnorm, int n, double[] h, int offseth, int ldh, double[] eig, int offseteig, double[] bounds, int offsetbounds, double[] workl, int offsetworkl, org.netlib.util.intW ierr);
  public native boolean has_dseigt();

  protected native void sseigtK(float rnorm, int n, float[] h, int offseth, int ldh, float[] eig, int offseteig, float[] bounds, int offsetbounds, float[] workl, int offsetworkl, org.netlib.util.intW ierr);
  public native boolean has_sseigt();

  protected native void dsesrtK(String which, boolean apply, int n, double[] x, int offsetx, int na, double[] a, int offseta, int lda);
  public native boolean has_dsesrt();

  protected native void ssesrtK(String which, boolean apply, int n, float[] x, int offsetx, int na, float[] a, int offseta, int lda);
  public native boolean has_ssesrt();

  protected native void dsaupdK(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.doubleW tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);
  public native boolean has_dsaupd();

  protected native void ssaupdK(org.netlib.util.intW ido, String bmat, int n, String which, int nev, org.netlib.util.floatW tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);
  public native boolean has_ssaupd();

  protected native void dseupdK(boolean rvec, String howmny, boolean[] select, int offsetselect, double[] d, int offsetd, double[] z, int offsetz, int ldz, double sigma, String bmat, int n, String which, org.netlib.util.intW nev, double tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);
  public native boolean has_dseupd();

  protected native void sseupdK(boolean rvec, String howmny, boolean[] select, int offsetselect, float[] d, int offsetd, float[] z, int offsetz, int ldz, float sigma, String bmat, int n, String which, org.netlib.util.intW nev, float tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, org.netlib.util.intW info);
  public native boolean has_sseupd();

  protected native void dsgetsK(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double[] shifts, int offsetshifts);
  public native boolean has_dsgets();

  protected native void ssgetsK(int ishift, String which, org.netlib.util.intW kev, org.netlib.util.intW np, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float[] shifts, int offsetshifts);
  public native boolean has_ssgets();

  protected native void dsortcK(String which, boolean apply, int n, double[] xreal, int offsetxreal, double[] ximag, int offsetximag, double[] y, int offsety);
  public native boolean has_dsortc();

  protected native void ssortcK(String which, boolean apply, int n, float[] xreal, int offsetxreal, float[] ximag, int offsetximag, float[] y, int offsety);
  public native boolean has_ssortc();

  protected native void dsortrK(String which, boolean apply, int n, double[] x1, int offsetx1, double[] x2, int offsetx2);
  public native boolean has_dsortr();

  protected native void ssortrK(String which, boolean apply, int n, float[] x1, int offsetx1, float[] x2, int offsetx2);
  public native boolean has_ssortr();

  protected native void dstatnK();
  public native boolean has_dstatn();

  protected native void sstatnK();
  public native boolean has_sstatn();

  protected native void dstatsK();
  public native boolean has_dstats();

  protected native void sstatsK();
  public native boolean has_sstats();

  protected native void dstqrbK(int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dstqrb();

  protected native void sstqrbK(int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sstqrb();

  protected native int icnteqK(int n, int[] array, int offsetarray, int value);
  public native boolean has_icnteq();

  protected native void icopyK(int n, int[] lx, int offsetlx, int incx, int[] ly, int offsetly, int incy);
  public native boolean has_icopy();

  protected native void isetK(int n, int value, int[] array, int offsetarray, int inc);
  public native boolean has_iset();

  protected native void iswapK(int n, int[] sx, int offsetsx, int incx, int[] sy, int offsetsy, int incy);
  public native boolean has_iswap();

  protected native void secondK(org.netlib.util.floatW t);
  public native boolean has_second();
}
