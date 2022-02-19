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

package dev.ludovic.netlib.lapack;

import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermissions;

final class JNILAPACK extends AbstractLAPACK implements dev.ludovic.netlib.NativeLAPACK {

  private static final JNILAPACK instance = new JNILAPACK();

  protected JNILAPACK() {
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
            String.format("resources/native/%s-%s/libnetliblapackjni.so", osName, osArch))) {
      assert resource != null;
      Files.copy(resource, temp = Files.createTempFile("libnetliblapackjni.so", "",
                                    PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rwxr-x---"))),
                  StandardCopyOption.REPLACE_EXISTING);
      temp.toFile().deleteOnExit();
    } catch (IOException e) {
      throw new RuntimeException("Unable to load native implementation", e);
    }

    System.load(temp.toString());}

  public static dev.ludovic.netlib.NativeLAPACK getInstance() {
    return instance;
  }

  protected native void dbdsdcK(String uplo, String compq, int n, double[] d, int offsetd, double[] e, int offsete, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int ldvt, double[] q, int offsetq, int[] iq, int offsetiq, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dbdsdc();

  protected native void dbdsqrK(String uplo, int n, int ncvt, int nru, int ncc, double[] d, int offsetd, double[] e, int offsete, double[] vt, int offsetvt, int ldvt, double[] u, int offsetu, int ldu, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dbdsqr();

  protected native void ddisnaK(String job, int m, int n, double[] d, int offsetd, double[] sep, int offsetsep, org.netlib.util.intW info);
  public native boolean has_ddisna();

  protected native void dgbbrdK(String vect, int m, int n, int ncc, int kl, int ku, double[] ab, int offsetab, int ldab, double[] d, int offsetd, double[] e, int offsete, double[] q, int offsetq, int ldq, double[] pt, int offsetpt, int ldpt, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dgbbrd();

  protected native void dgbconK(String norm, int n, int kl, int ku, double[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, double anorm, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dgbcon();

  protected native void dgbequK(int m, int n, int kl, int ku, double[] ab, int offsetab, int ldab, double[] r, int offsetr, double[] c, int offsetc, org.netlib.util.doubleW rowcnd, org.netlib.util.doubleW colcnd, org.netlib.util.doubleW amax, org.netlib.util.intW info);
  public native boolean has_dgbequ();

  protected native void dgbrfsK(String trans, int n, int kl, int ku, int nrhs, double[] ab, int offsetab, int ldab, double[] afb, int offsetafb, int ldafb, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dgbrfs();

  protected native void dgbsvK(int n, int kl, int ku, int nrhs, double[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dgbsv();

  protected native void dgbsvxK(String fact, String trans, int n, int kl, int ku, int nrhs, double[] ab, int offsetab, int ldab, double[] afb, int offsetafb, int ldafb, int[] ipiv, int offsetipiv, org.netlib.util.StringW equed, double[] r, int offsetr, double[] c, int offsetc, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dgbsvx();

  protected native void dgbtf2K(int m, int n, int kl, int ku, double[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, org.netlib.util.intW info);
  public native boolean has_dgbtf2();

  protected native void dgbtrfK(int m, int n, int kl, int ku, double[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, org.netlib.util.intW info);
  public native boolean has_dgbtrf();

  protected native void dgbtrsK(String trans, int n, int kl, int ku, int nrhs, double[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dgbtrs();

  protected native void dgebakK(String job, String side, int n, int ilo, int ihi, double[] scale, int offsetscale, int m, double[] v, int offsetv, int ldv, org.netlib.util.intW info);
  public native boolean has_dgebak();

  protected native void dgebalK(String job, int n, double[] a, int offseta, int lda, org.netlib.util.intW ilo, org.netlib.util.intW ihi, double[] scale, int offsetscale, org.netlib.util.intW info);
  public native boolean has_dgebal();

  protected native void dgebd2K(int m, int n, double[] a, int offseta, int lda, double[] d, int offsetd, double[] e, int offsete, double[] tauq, int offsettauq, double[] taup, int offsettaup, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dgebd2();

  protected native void dgebrdK(int m, int n, double[] a, int offseta, int lda, double[] d, int offsetd, double[] e, int offsete, double[] tauq, int offsettauq, double[] taup, int offsettaup, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dgebrd();

  protected native void dgeconK(String norm, int n, double[] a, int offseta, int lda, double anorm, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dgecon();

  protected native void dgeequK(int m, int n, double[] a, int offseta, int lda, double[] r, int offsetr, double[] c, int offsetc, org.netlib.util.doubleW rowcnd, org.netlib.util.doubleW colcnd, org.netlib.util.doubleW amax, org.netlib.util.intW info);
  public native boolean has_dgeequ();

  protected native void dgeesK(String jobvs, String sort, java.lang.Object select, int n, double[] a, int offseta, int lda, org.netlib.util.intW sdim, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] vs, int offsetvs, int ldvs, double[] work, int offsetwork, int lwork, boolean[] bwork, int offsetbwork, org.netlib.util.intW info);
  public native boolean has_dgees();

  protected native void dgeesxK(String jobvs, String sort, java.lang.Object select, String sense, int n, double[] a, int offseta, int lda, org.netlib.util.intW sdim, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] vs, int offsetvs, int ldvs, org.netlib.util.doubleW rconde, org.netlib.util.doubleW rcondv, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, boolean[] bwork, int offsetbwork, org.netlib.util.intW info);
  public native boolean has_dgeesx();

  protected native void dgeevK(String jobvl, String jobvr, int n, double[] a, int offseta, int lda, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dgeev();

  protected native void dgeevxK(String balanc, String jobvl, String jobvr, String sense, int n, double[] a, int offseta, int lda, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, org.netlib.util.intW ilo, org.netlib.util.intW ihi, double[] scale, int offsetscale, org.netlib.util.doubleW abnrm, double[] rconde, int offsetrconde, double[] rcondv, int offsetrcondv, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dgeevx();

  protected native void dgegsK(String jobvsl, String jobvsr, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] vsl, int offsetvsl, int ldvsl, double[] vsr, int offsetvsr, int ldvsr, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dgegs();

  protected native void dgegvK(String jobvl, String jobvr, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dgegv();

  protected native void dgehd2K(int n, int ilo, int ihi, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dgehd2();

  protected native void dgehrdK(int n, int ilo, int ihi, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dgehrd();

  protected native void dgelq2K(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dgelq2();

  protected native void dgelqfK(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dgelqf();

  protected native void dgelsK(String trans, int m, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dgels();

  protected native void dgelsdK(int m, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] s, int offsets, double rcond, org.netlib.util.intW rank, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dgelsd();

  protected native void dgelssK(int m, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] s, int offsets, double rcond, org.netlib.util.intW rank, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dgelss();

  protected native void dgelsxK(int m, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, int[] jpvt, int offsetjpvt, double rcond, org.netlib.util.intW rank, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dgelsx();

  protected native void dgelsyK(int m, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, int[] jpvt, int offsetjpvt, double rcond, org.netlib.util.intW rank, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dgelsy();

  protected native void dgeql2K(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dgeql2();

  protected native void dgeqlfK(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dgeqlf();

  protected native void dgeqp3K(int m, int n, double[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dgeqp3();

  protected native void dgeqpfK(int m, int n, double[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dgeqpf();

  protected native void dgeqr2K(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dgeqr2();

  protected native void dgeqrfK(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dgeqrf();

  protected native void dgerfsK(String trans, int n, int nrhs, double[] a, int offseta, int lda, double[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dgerfs();

  protected native void dgerq2K(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dgerq2();

  protected native void dgerqfK(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dgerqf();

  protected native void dgesc2K(int n, double[] a, int offseta, int lda, double[] rhs, int offsetrhs, int[] ipiv, int offsetipiv, int[] jpiv, int offsetjpiv, org.netlib.util.doubleW scale);
  public native boolean has_dgesc2();

  protected native void dgesddK(String jobz, int m, int n, double[] a, int offseta, int lda, double[] s, int offsets, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int ldvt, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dgesdd();

  protected native void dgesvK(int n, int nrhs, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dgesv();

  protected native void dgesvdK(String jobu, String jobvt, int m, int n, double[] a, int offseta, int lda, double[] s, int offsets, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int ldvt, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dgesvd();

  protected native void dgesvxK(String fact, String trans, int n, int nrhs, double[] a, int offseta, int lda, double[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, org.netlib.util.StringW equed, double[] r, int offsetr, double[] c, int offsetc, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dgesvx();

  protected native void dgetc2K(int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, int[] jpiv, int offsetjpiv, org.netlib.util.intW info);
  public native boolean has_dgetc2();

  protected native void dgetf2K(int m, int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, org.netlib.util.intW info);
  public native boolean has_dgetf2();

  protected native void dgetrfK(int m, int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, org.netlib.util.intW info);
  public native boolean has_dgetrf();

  protected native void dgetriK(int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dgetri();

  protected native void dgetrsK(String trans, int n, int nrhs, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dgetrs();

  protected native void dggbakK(String job, String side, int n, int ilo, int ihi, double[] lscale, int offsetlscale, double[] rscale, int offsetrscale, int m, double[] v, int offsetv, int ldv, org.netlib.util.intW info);
  public native boolean has_dggbak();

  protected native void dggbalK(String job, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, org.netlib.util.intW ilo, org.netlib.util.intW ihi, double[] lscale, int offsetlscale, double[] rscale, int offsetrscale, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dggbal();

  protected native void dggesK(String jobvsl, String jobvsr, String sort, java.lang.Object selctg, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, org.netlib.util.intW sdim, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] vsl, int offsetvsl, int ldvsl, double[] vsr, int offsetvsr, int ldvsr, double[] work, int offsetwork, int lwork, boolean[] bwork, int offsetbwork, org.netlib.util.intW info);
  public native boolean has_dgges();

  protected native void dggesxK(String jobvsl, String jobvsr, String sort, java.lang.Object selctg, String sense, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, org.netlib.util.intW sdim, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] vsl, int offsetvsl, int ldvsl, double[] vsr, int offsetvsr, int ldvsr, double[] rconde, int offsetrconde, double[] rcondv, int offsetrcondv, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, boolean[] bwork, int offsetbwork, org.netlib.util.intW info);
  public native boolean has_dggesx();

  protected native void dggevK(String jobvl, String jobvr, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dggev();

  protected native void dggevxK(String balanc, String jobvl, String jobvr, String sense, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, org.netlib.util.intW ilo, org.netlib.util.intW ihi, double[] lscale, int offsetlscale, double[] rscale, int offsetrscale, org.netlib.util.doubleW abnrm, org.netlib.util.doubleW bbnrm, double[] rconde, int offsetrconde, double[] rcondv, int offsetrcondv, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, boolean[] bwork, int offsetbwork, org.netlib.util.intW info);
  public native boolean has_dggevx();

  protected native void dggglmK(int n, int m, int p, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] d, int offsetd, double[] x, int offsetx, double[] y, int offsety, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dggglm();

  protected native void dgghrdK(String compq, String compz, int n, int ilo, int ihi, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] q, int offsetq, int ldq, double[] z, int offsetz, int ldz, org.netlib.util.intW info);
  public native boolean has_dgghrd();

  protected native void dgglseK(int m, int n, int p, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] c, int offsetc, double[] d, int offsetd, double[] x, int offsetx, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dgglse();

  protected native void dggqrfK(int n, int m, int p, double[] a, int offseta, int lda, double[] taua, int offsettaua, double[] b, int offsetb, int ldb, double[] taub, int offsettaub, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dggqrf();

  protected native void dggrqfK(int m, int p, int n, double[] a, int offseta, int lda, double[] taua, int offsettaua, double[] b, int offsetb, int ldb, double[] taub, int offsettaub, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dggrqf();

  protected native void dggsvdK(String jobu, String jobv, String jobq, int m, int n, int p, org.netlib.util.intW k, org.netlib.util.intW l, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alpha, int offsetalpha, double[] beta, int offsetbeta, double[] u, int offsetu, int ldu, double[] v, int offsetv, int ldv, double[] q, int offsetq, int ldq, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dggsvd();

  protected native void dggsvpK(String jobu, String jobv, String jobq, int m, int p, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double tola, double tolb, org.netlib.util.intW k, org.netlib.util.intW l, double[] u, int offsetu, int ldu, double[] v, int offsetv, int ldv, double[] q, int offsetq, int ldq, int[] iwork, int offsetiwork, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dggsvp();

  protected native void dgtconK(String norm, int n, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] du2, int offsetdu2, int[] ipiv, int offsetipiv, double anorm, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dgtcon();

  protected native void dgtrfsK(String trans, int n, int nrhs, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] dlf, int offsetdlf, double[] df, int offsetdf, double[] duf, int offsetduf, double[] du2, int offsetdu2, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dgtrfs();

  protected native void dgtsvK(int n, int nrhs, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dgtsv();

  protected native void dgtsvxK(String fact, String trans, int n, int nrhs, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] dlf, int offsetdlf, double[] df, int offsetdf, double[] duf, int offsetduf, double[] du2, int offsetdu2, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dgtsvx();

  protected native void dgttrfK(int n, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] du2, int offsetdu2, int[] ipiv, int offsetipiv, org.netlib.util.intW info);
  public native boolean has_dgttrf();

  protected native void dgttrsK(String trans, int n, int nrhs, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] du2, int offsetdu2, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dgttrs();

  protected native void dgtts2K(int itrans, int n, int nrhs, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] du2, int offsetdu2, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb);
  public native boolean has_dgtts2();

  protected native void dhgeqzK(String job, String compq, String compz, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] t, int offsett, int ldt, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] q, int offsetq, int ldq, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dhgeqz();

  protected native void dhseinK(String side, String eigsrc, String initv, boolean[] select, int offsetselect, int n, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, int mm, org.netlib.util.intW m, double[] work, int offsetwork, int[] ifaill, int offsetifaill, int[] ifailr, int offsetifailr, org.netlib.util.intW info);
  public native boolean has_dhsein();

  protected native void dhseqrK(String job, String compz, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dhseqr();

  protected native boolean disnanK(double din);
  public native boolean has_disnan();

  protected native void dlabadK(org.netlib.util.doubleW small, org.netlib.util.doubleW large);
  public native boolean has_dlabad();

  protected native void dlabrdK(int m, int n, int nb, double[] a, int offseta, int lda, double[] d, int offsetd, double[] e, int offsete, double[] tauq, int offsettauq, double[] taup, int offsettaup, double[] x, int offsetx, int ldx, double[] y, int offsety, int ldy);
  public native boolean has_dlabrd();

  protected native void dlacn2K(int n, double[] v, int offsetv, double[] x, int offsetx, int[] isgn, int offsetisgn, org.netlib.util.doubleW est, org.netlib.util.intW kase, int[] isave, int offsetisave);
  public native boolean has_dlacn2();

  protected native void dlaconK(int n, double[] v, int offsetv, double[] x, int offsetx, int[] isgn, int offsetisgn, org.netlib.util.doubleW est, org.netlib.util.intW kase);
  public native boolean has_dlacon();

  protected native void dlacpyK(String uplo, int m, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb);
  public native boolean has_dlacpy();

  protected native void dladivK(double a, double b, double c, double d, org.netlib.util.doubleW p, org.netlib.util.doubleW q);
  public native boolean has_dladiv();

  protected native void dlae2K(double a, double b, double c, org.netlib.util.doubleW rt1, org.netlib.util.doubleW rt2);
  public native boolean has_dlae2();

  protected native void dlaebzK(int ijob, int nitmax, int n, int mmax, int minp, int nbmin, double abstol, double reltol, double pivmin, double[] d, int offsetd, double[] e, int offsete, double[] e2, int offsete2, int[] nval, int offsetnval, double[] ab, int offsetab, double[] c, int offsetc, org.netlib.util.intW mout, int[] nab, int offsetnab, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dlaebz();

  protected native void dlaed0K(int icompq, int qsiz, int n, double[] d, int offsetd, double[] e, int offsete, double[] q, int offsetq, int ldq, double[] qstore, int offsetqstore, int ldqs, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dlaed0();

  protected native void dlaed1K(int n, double[] d, int offsetd, double[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, org.netlib.util.doubleW rho, int cutpnt, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dlaed1();

  protected native void dlaed2K(org.netlib.util.intW k, int n, int n1, double[] d, int offsetd, double[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, org.netlib.util.doubleW rho, double[] z, int offsetz, double[] dlamda, int offsetdlamda, double[] w, int offsetw, double[] q2, int offsetq2, int[] indx, int offsetindx, int[] indxc, int offsetindxc, int[] indxp, int offsetindxp, int[] coltyp, int offsetcoltyp, org.netlib.util.intW info);
  public native boolean has_dlaed2();

  protected native void dlaed3K(int k, int n, int n1, double[] d, int offsetd, double[] q, int offsetq, int ldq, double rho, double[] dlamda, int offsetdlamda, double[] q2, int offsetq2, int[] indx, int offsetindx, int[] ctot, int offsetctot, double[] w, int offsetw, double[] s, int offsets, org.netlib.util.intW info);
  public native boolean has_dlaed3();

  protected native void dlaed4K(int n, int i, double[] d, int offsetd, double[] z, int offsetz, double[] delta, int offsetdelta, double rho, org.netlib.util.doubleW dlam, org.netlib.util.intW info);
  public native boolean has_dlaed4();

  protected native void dlaed5K(int i, double[] d, int offsetd, double[] z, int offsetz, double[] delta, int offsetdelta, double rho, org.netlib.util.doubleW dlam);
  public native boolean has_dlaed5();

  protected native void dlaed6K(int kniter, boolean orgati, double rho, double[] d, int offsetd, double[] z, int offsetz, double finit, org.netlib.util.doubleW tau, org.netlib.util.intW info);
  public native boolean has_dlaed6();

  protected native void dlaed7K(int icompq, int n, int qsiz, int tlvls, int curlvl, int curpbm, double[] d, int offsetd, double[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, org.netlib.util.doubleW rho, int cutpnt, double[] qstore, int offsetqstore, int[] qptr, int offsetqptr, int[] prmptr, int offsetprmptr, int[] perm, int offsetperm, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, double[] givnum, int offsetgivnum, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dlaed7();

  protected native void dlaed8K(int icompq, org.netlib.util.intW k, int n, int qsiz, double[] d, int offsetd, double[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, org.netlib.util.doubleW rho, int cutpnt, double[] z, int offsetz, double[] dlamda, int offsetdlamda, double[] q2, int offsetq2, int ldq2, double[] w, int offsetw, int[] perm, int offsetperm, org.netlib.util.intW givptr, int[] givcol, int offsetgivcol, double[] givnum, int offsetgivnum, int[] indxp, int offsetindxp, int[] indx, int offsetindx, org.netlib.util.intW info);
  public native boolean has_dlaed8();

  protected native void dlaed9K(int k, int kstart, int kstop, int n, double[] d, int offsetd, double[] q, int offsetq, int ldq, double rho, double[] dlamda, int offsetdlamda, double[] w, int offsetw, double[] s, int offsets, int lds, org.netlib.util.intW info);
  public native boolean has_dlaed9();

  protected native void dlaedaK(int n, int tlvls, int curlvl, int curpbm, int[] prmptr, int offsetprmptr, int[] perm, int offsetperm, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, double[] givnum, int offsetgivnum, double[] q, int offsetq, int[] qptr, int offsetqptr, double[] z, int offsetz, double[] ztemp, int offsetztemp, org.netlib.util.intW info);
  public native boolean has_dlaeda();

  protected native void dlaeinK(boolean rightv, boolean noinit, int n, double[] h, int offseth, int ldh, double wr, double wi, double[] vr, int offsetvr, double[] vi, int offsetvi, double[] b, int offsetb, int ldb, double[] work, int offsetwork, double eps3, double smlnum, double bignum, org.netlib.util.intW info);
  public native boolean has_dlaein();

  protected native void dlaev2K(double a, double b, double c, org.netlib.util.doubleW rt1, org.netlib.util.doubleW rt2, org.netlib.util.doubleW cs1, org.netlib.util.doubleW sn1);
  public native boolean has_dlaev2();

  protected native void dlaexcK(boolean wantq, int n, double[] t, int offsett, int ldt, double[] q, int offsetq, int ldq, int j1, int n1, int n2, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dlaexc();

  protected native void dlag2K(double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double safmin, org.netlib.util.doubleW scale1, org.netlib.util.doubleW scale2, org.netlib.util.doubleW wr1, org.netlib.util.doubleW wr2, org.netlib.util.doubleW wi);
  public native boolean has_dlag2();

  protected native void dlag2sK(int m, int n, double[] a, int offseta, int lda, float[] sa, int offsetsa, int ldsa, org.netlib.util.intW info);
  public native boolean has_dlag2s();

  protected native void dlags2K(boolean upper, double a1, double a2, double a3, double b1, double b2, double b3, org.netlib.util.doubleW csu, org.netlib.util.doubleW snu, org.netlib.util.doubleW csv, org.netlib.util.doubleW snv, org.netlib.util.doubleW csq, org.netlib.util.doubleW snq);
  public native boolean has_dlags2();

  protected native void dlagtfK(int n, double[] a, int offseta, double lambda, double[] b, int offsetb, double[] c, int offsetc, double tol, double[] d, int offsetd, int[] in, int offsetin, org.netlib.util.intW info);
  public native boolean has_dlagtf();

  protected native void dlagtmK(String trans, int n, int nrhs, double alpha, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] x, int offsetx, int ldx, double beta, double[] b, int offsetb, int ldb);
  public native boolean has_dlagtm();

  protected native void dlagtsK(int job, int n, double[] a, int offseta, double[] b, int offsetb, double[] c, int offsetc, double[] d, int offsetd, int[] in, int offsetin, double[] y, int offsety, org.netlib.util.doubleW tol, org.netlib.util.intW info);
  public native boolean has_dlagts();

  protected native void dlagv2K(double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, org.netlib.util.doubleW csl, org.netlib.util.doubleW snl, org.netlib.util.doubleW csr, org.netlib.util.doubleW snr);
  public native boolean has_dlagv2();

  protected native void dlahqrK(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, int iloz, int ihiz, double[] z, int offsetz, int ldz, org.netlib.util.intW info);
  public native boolean has_dlahqr();

  protected native void dlahr2K(int n, int k, int nb, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] t, int offsett, int ldt, double[] y, int offsety, int ldy);
  public native boolean has_dlahr2();

  protected native void dlahrdK(int n, int k, int nb, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] t, int offsett, int ldt, double[] y, int offsety, int ldy);
  public native boolean has_dlahrd();

  protected native void dlaic1K(int job, int j, double[] x, int offsetx, double sest, double[] w, int offsetw, double gamma, org.netlib.util.doubleW sestpr, org.netlib.util.doubleW s, org.netlib.util.doubleW c);
  public native boolean has_dlaic1();

  protected native boolean dlaisnanK(double din1, double din2);
  public native boolean has_dlaisnan();

  protected native void dlaln2K(boolean ltrans, int na, int nw, double smin, double ca, double[] a, int offseta, int lda, double d1, double d2, double[] b, int offsetb, int ldb, double wr, double wi, double[] x, int offsetx, int ldx, org.netlib.util.doubleW scale, org.netlib.util.doubleW xnorm, org.netlib.util.intW info);
  public native boolean has_dlaln2();

  protected native void dlals0K(int icompq, int nl, int nr, int sqre, int nrhs, double[] b, int offsetb, int ldb, double[] bx, int offsetbx, int ldbx, int[] perm, int offsetperm, int givptr, int[] givcol, int offsetgivcol, int ldgcol, double[] givnum, int offsetgivnum, int ldgnum, double[] poles, int offsetpoles, double[] difl, int offsetdifl, double[] difr, int offsetdifr, double[] z, int offsetz, int k, double c, double s, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dlals0();

  protected native void dlalsaK(int icompq, int smlsiz, int n, int nrhs, double[] b, int offsetb, int ldb, double[] bx, int offsetbx, int ldbx, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int[] k, int offsetk, double[] difl, int offsetdifl, double[] difr, int offsetdifr, double[] z, int offsetz, double[] poles, int offsetpoles, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, int ldgcol, int[] perm, int offsetperm, double[] givnum, int offsetgivnum, double[] c, int offsetc, double[] s, int offsets, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dlalsa();

  protected native void dlalsdK(String uplo, int smlsiz, int n, int nrhs, double[] d, int offsetd, double[] e, int offsete, double[] b, int offsetb, int ldb, double rcond, org.netlib.util.intW rank, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dlalsd();

  protected native void dlamrgK(int n1, int n2, double[] a, int offseta, int dtrd1, int dtrd2, int[] index, int offsetindex);
  public native boolean has_dlamrg();

  protected native int dlanegK(int n, double[] d, int offsetd, double[] lld, int offsetlld, double sigma, double pivmin, int r);
  public native boolean has_dlaneg();

  protected native double dlangbK(String norm, int n, int kl, int ku, double[] ab, int offsetab, int ldab, double[] work, int offsetwork);
  public native boolean has_dlangb();

  protected native double dlangeK(String norm, int m, int n, double[] a, int offseta, int lda, double[] work, int offsetwork);
  public native boolean has_dlange();

  protected native double dlangtK(String norm, int n, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu);
  public native boolean has_dlangt();

  protected native double dlanhsK(String norm, int n, double[] a, int offseta, int lda, double[] work, int offsetwork);
  public native boolean has_dlanhs();

  protected native double dlansbK(String norm, String uplo, int n, int k, double[] ab, int offsetab, int ldab, double[] work, int offsetwork);
  public native boolean has_dlansb();

  protected native double dlanspK(String norm, String uplo, int n, double[] ap, int offsetap, double[] work, int offsetwork);
  public native boolean has_dlansp();

  protected native double dlanstK(String norm, int n, double[] d, int offsetd, double[] e, int offsete);
  public native boolean has_dlanst();

  protected native double dlansyK(String norm, String uplo, int n, double[] a, int offseta, int lda, double[] work, int offsetwork);
  public native boolean has_dlansy();

  protected native double dlantbK(String norm, String uplo, String diag, int n, int k, double[] ab, int offsetab, int ldab, double[] work, int offsetwork);
  public native boolean has_dlantb();

  protected native double dlantpK(String norm, String uplo, String diag, int n, double[] ap, int offsetap, double[] work, int offsetwork);
  public native boolean has_dlantp();

  protected native double dlantrK(String norm, String uplo, String diag, int m, int n, double[] a, int offseta, int lda, double[] work, int offsetwork);
  public native boolean has_dlantr();

  protected native void dlanv2K(org.netlib.util.doubleW a, org.netlib.util.doubleW b, org.netlib.util.doubleW c, org.netlib.util.doubleW d, org.netlib.util.doubleW rt1r, org.netlib.util.doubleW rt1i, org.netlib.util.doubleW rt2r, org.netlib.util.doubleW rt2i, org.netlib.util.doubleW cs, org.netlib.util.doubleW sn);
  public native boolean has_dlanv2();

  protected native void dlapllK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, org.netlib.util.doubleW ssmin);
  public native boolean has_dlapll();

  protected native void dlapmtK(boolean forwrd, int m, int n, double[] x, int offsetx, int ldx, int[] k, int offsetk);
  public native boolean has_dlapmt();

  protected native double dlapy2K(double x, double y);
  public native boolean has_dlapy2();

  protected native double dlapy3K(double x, double y, double z);
  public native boolean has_dlapy3();

  protected native void dlaqgbK(int m, int n, int kl, int ku, double[] ab, int offsetab, int ldab, double[] r, int offsetr, double[] c, int offsetc, double rowcnd, double colcnd, double amax, org.netlib.util.StringW equed);
  public native boolean has_dlaqgb();

  protected native void dlaqgeK(int m, int n, double[] a, int offseta, int lda, double[] r, int offsetr, double[] c, int offsetc, double rowcnd, double colcnd, double amax, org.netlib.util.StringW equed);
  public native boolean has_dlaqge();

  protected native void dlaqp2K(int m, int n, int offset, double[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, double[] tau, int offsettau, double[] vn1, int offsetvn1, double[] vn2, int offsetvn2, double[] work, int offsetwork);
  public native boolean has_dlaqp2();

  protected native void dlaqpsK(int m, int n, int offset, int nb, org.netlib.util.intW kb, double[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, double[] tau, int offsettau, double[] vn1, int offsetvn1, double[] vn2, int offsetvn2, double[] auxv, int offsetauxv, double[] f, int offsetf, int ldf);
  public native boolean has_dlaqps();

  protected native void dlaqr0K(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, int iloz, int ihiz, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dlaqr0();

  protected native void dlaqr1K(int n, double[] h, int offseth, int ldh, double sr1, double si1, double sr2, double si2, double[] v, int offsetv);
  public native boolean has_dlaqr1();

  protected native void dlaqr2K(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, double[] h, int offseth, int ldh, int iloz, int ihiz, double[] z, int offsetz, int ldz, org.netlib.util.intW ns, org.netlib.util.intW nd, double[] sr, int offsetsr, double[] si, int offsetsi, double[] v, int offsetv, int ldv, int nh, double[] t, int offsett, int ldt, int nv, double[] wv, int offsetwv, int ldwv, double[] work, int offsetwork, int lwork);
  public native boolean has_dlaqr2();

  protected native void dlaqr3K(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, double[] h, int offseth, int ldh, int iloz, int ihiz, double[] z, int offsetz, int ldz, org.netlib.util.intW ns, org.netlib.util.intW nd, double[] sr, int offsetsr, double[] si, int offsetsi, double[] v, int offsetv, int ldv, int nh, double[] t, int offsett, int ldt, int nv, double[] wv, int offsetwv, int ldwv, double[] work, int offsetwork, int lwork);
  public native boolean has_dlaqr3();

  protected native void dlaqr4K(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, int iloz, int ihiz, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dlaqr4();

  protected native void dlaqr5K(boolean wantt, boolean wantz, int kacc22, int n, int ktop, int kbot, int nshfts, double[] sr, int offsetsr, double[] si, int offsetsi, double[] h, int offseth, int ldh, int iloz, int ihiz, double[] z, int offsetz, int ldz, double[] v, int offsetv, int ldv, double[] u, int offsetu, int ldu, int nv, double[] wv, int offsetwv, int ldwv, int nh, double[] wh, int offsetwh, int ldwh);
  public native boolean has_dlaqr5();

  protected native void dlaqsbK(String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double[] s, int offsets, double scond, double amax, org.netlib.util.StringW equed);
  public native boolean has_dlaqsb();

  protected native void dlaqspK(String uplo, int n, double[] ap, int offsetap, double[] s, int offsets, double scond, double amax, org.netlib.util.StringW equed);
  public native boolean has_dlaqsp();

  protected native void dlaqsyK(String uplo, int n, double[] a, int offseta, int lda, double[] s, int offsets, double scond, double amax, org.netlib.util.StringW equed);
  public native boolean has_dlaqsy();

  protected native void dlaqtrK(boolean ltran, boolean lreal, int n, double[] t, int offsett, int ldt, double[] b, int offsetb, double w, org.netlib.util.doubleW scale, double[] x, int offsetx, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dlaqtr();

  protected native void dlar1vK(int n, int b1, int bn, double lambda, double[] d, int offsetd, double[] l, int offsetl, double[] ld, int offsetld, double[] lld, int offsetlld, double pivmin, double gaptol, double[] z, int offsetz, boolean wantnc, org.netlib.util.intW negcnt, org.netlib.util.doubleW ztz, org.netlib.util.doubleW mingma, org.netlib.util.intW r, int[] isuppz, int offsetisuppz, org.netlib.util.doubleW nrminv, org.netlib.util.doubleW resid, org.netlib.util.doubleW rqcorr, double[] work, int offsetwork);
  public native boolean has_dlar1v();

  protected native void dlar2vK(int n, double[] x, int offsetx, double[] y, int offsety, double[] z, int offsetz, int incx, double[] c, int offsetc, double[] s, int offsets, int incc);
  public native boolean has_dlar2v();

  protected native void dlarfK(String side, int m, int n, double[] v, int offsetv, int incv, double tau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork);
  public native boolean has_dlarf();

  protected native void dlarfbK(String side, String trans, String direct, String storev, int m, int n, int k, double[] v, int offsetv, int ldv, double[] t, int offsett, int ldt, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int ldwork);
  public native boolean has_dlarfb();

  protected native void dlarfgK(int n, org.netlib.util.doubleW alpha, double[] x, int offsetx, int incx, org.netlib.util.doubleW tau);
  public native boolean has_dlarfg();

  protected native void dlarftK(String direct, String storev, int n, int k, double[] v, int offsetv, int ldv, double[] tau, int offsettau, double[] t, int offsett, int ldt);
  public native boolean has_dlarft();

  protected native void dlarfxK(String side, int m, int n, double[] v, int offsetv, double tau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork);
  public native boolean has_dlarfx();

  protected native void dlargvK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] c, int offsetc, int incc);
  public native boolean has_dlargv();

  protected native void dlarnvK(int idist, int[] iseed, int offsetiseed, int n, double[] x, int offsetx);
  public native boolean has_dlarnv();

  protected native void dlarraK(int n, double[] d, int offsetd, double[] e, int offsete, double[] e2, int offsete2, double spltol, double tnrm, org.netlib.util.intW nsplit, int[] isplit, int offsetisplit, org.netlib.util.intW info);
  public native boolean has_dlarra();

  protected native void dlarrbK(int n, double[] d, int offsetd, double[] lld, int offsetlld, int ifirst, int ilast, double rtol1, double rtol2, int offset, double[] w, int offsetw, double[] wgap, int offsetwgap, double[] werr, int offsetwerr, double[] work, int offsetwork, int[] iwork, int offsetiwork, double pivmin, double spdiam, int twist, org.netlib.util.intW info);
  public native boolean has_dlarrb();

  protected native void dlarrcK(String jobt, int n, double vl, double vu, double[] d, int offsetd, double[] e, int offsete, double pivmin, org.netlib.util.intW eigcnt, org.netlib.util.intW lcnt, org.netlib.util.intW rcnt, org.netlib.util.intW info);
  public native boolean has_dlarrc();

  protected native void dlarrdK(String range, String order, int n, double vl, double vu, int il, int iu, double[] gers, int offsetgers, double reltol, double[] d, int offsetd, double[] e, int offsete, double[] e2, int offsete2, double pivmin, int nsplit, int[] isplit, int offsetisplit, org.netlib.util.intW m, double[] w, int offsetw, double[] werr, int offsetwerr, org.netlib.util.doubleW wl, org.netlib.util.doubleW wu, int[] iblock, int offsetiblock, int[] indexw, int offsetindexw, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dlarrd();

  protected native void dlarreK(String range, int n, org.netlib.util.doubleW vl, org.netlib.util.doubleW vu, int il, int iu, double[] d, int offsetd, double[] e, int offsete, double[] e2, int offsete2, double rtol1, double rtol2, double spltol, org.netlib.util.intW nsplit, int[] isplit, int offsetisplit, org.netlib.util.intW m, double[] w, int offsetw, double[] werr, int offsetwerr, double[] wgap, int offsetwgap, int[] iblock, int offsetiblock, int[] indexw, int offsetindexw, double[] gers, int offsetgers, org.netlib.util.doubleW pivmin, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dlarre();

  protected native void dlarrfK(int n, double[] d, int offsetd, double[] l, int offsetl, double[] ld, int offsetld, int clstrt, int clend, double[] w, int offsetw, double[] wgap, int offsetwgap, double[] werr, int offsetwerr, double spdiam, double clgapl, double clgapr, double pivmin, org.netlib.util.doubleW sigma, double[] dplus, int offsetdplus, double[] lplus, int offsetlplus, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dlarrf();

  protected native void dlarrjK(int n, double[] d, int offsetd, double[] e2, int offsete2, int ifirst, int ilast, double rtol, int offset, double[] w, int offsetw, double[] werr, int offsetwerr, double[] work, int offsetwork, int[] iwork, int offsetiwork, double pivmin, double spdiam, org.netlib.util.intW info);
  public native boolean has_dlarrj();

  protected native void dlarrkK(int n, int iw, double gl, double gu, double[] d, int offsetd, double[] e2, int offsete2, double pivmin, double reltol, org.netlib.util.doubleW w, org.netlib.util.doubleW werr, org.netlib.util.intW info);
  public native boolean has_dlarrk();

  protected native void dlarrrK(int n, double[] d, int offsetd, double[] e, int offsete, org.netlib.util.intW info);
  public native boolean has_dlarrr();

  protected native void dlarrvK(int n, double vl, double vu, double[] d, int offsetd, double[] l, int offsetl, double pivmin, int[] isplit, int offsetisplit, int m, int dol, int dou, double minrgp, org.netlib.util.doubleW rtol1, org.netlib.util.doubleW rtol2, double[] w, int offsetw, double[] werr, int offsetwerr, double[] wgap, int offsetwgap, int[] iblock, int offsetiblock, int[] indexw, int offsetindexw, double[] gers, int offsetgers, double[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dlarrv();

  protected native void dlartgK(double f, double g, org.netlib.util.doubleW cs, org.netlib.util.doubleW sn, org.netlib.util.doubleW r);
  public native boolean has_dlartg();

  protected native void dlartvK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] c, int offsetc, double[] s, int offsets, int incc);
  public native boolean has_dlartv();

  protected native void dlaruvK(int[] iseed, int offsetiseed, int n, double[] x, int offsetx);
  public native boolean has_dlaruv();

  protected native void dlarzK(String side, int m, int n, int l, double[] v, int offsetv, int incv, double tau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork);
  public native boolean has_dlarz();

  protected native void dlarzbK(String side, String trans, String direct, String storev, int m, int n, int k, int l, double[] v, int offsetv, int ldv, double[] t, int offsett, int ldt, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int ldwork);
  public native boolean has_dlarzb();

  protected native void dlarztK(String direct, String storev, int n, int k, double[] v, int offsetv, int ldv, double[] tau, int offsettau, double[] t, int offsett, int ldt);
  public native boolean has_dlarzt();

  protected native void dlas2K(double f, double g, double h, org.netlib.util.doubleW ssmin, org.netlib.util.doubleW ssmax);
  public native boolean has_dlas2();

  protected native void dlasclK(String type, int kl, int ku, double cfrom, double cto, int m, int n, double[] a, int offseta, int lda, org.netlib.util.intW info);
  public native boolean has_dlascl();

  protected native void dlasd0K(int n, int sqre, double[] d, int offsetd, double[] e, int offsete, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int ldvt, int smlsiz, int[] iwork, int offsetiwork, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dlasd0();

  protected native void dlasd1K(int nl, int nr, int sqre, double[] d, int offsetd, org.netlib.util.doubleW alpha, org.netlib.util.doubleW beta, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int ldvt, int[] idxq, int offsetidxq, int[] iwork, int offsetiwork, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dlasd1();

  protected native void dlasd2K(int nl, int nr, int sqre, org.netlib.util.intW k, double[] d, int offsetd, double[] z, int offsetz, double alpha, double beta, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int ldvt, double[] dsigma, int offsetdsigma, double[] u2, int offsetu2, int ldu2, double[] vt2, int offsetvt2, int ldvt2, int[] idxp, int offsetidxp, int[] idx, int offsetidx, int[] idxc, int offsetidxc, int[] idxq, int offsetidxq, int[] coltyp, int offsetcoltyp, org.netlib.util.intW info);
  public native boolean has_dlasd2();

  protected native void dlasd3K(int nl, int nr, int sqre, int k, double[] d, int offsetd, double[] q, int offsetq, int ldq, double[] dsigma, int offsetdsigma, double[] u, int offsetu, int ldu, double[] u2, int offsetu2, int ldu2, double[] vt, int offsetvt, int ldvt, double[] vt2, int offsetvt2, int ldvt2, int[] idxc, int offsetidxc, int[] ctot, int offsetctot, double[] z, int offsetz, org.netlib.util.intW info);
  public native boolean has_dlasd3();

  protected native void dlasd4K(int n, int i, double[] d, int offsetd, double[] z, int offsetz, double[] delta, int offsetdelta, double rho, org.netlib.util.doubleW sigma, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dlasd4();

  protected native void dlasd5K(int i, double[] d, int offsetd, double[] z, int offsetz, double[] delta, int offsetdelta, double rho, org.netlib.util.doubleW dsigma, double[] work, int offsetwork);
  public native boolean has_dlasd5();

  protected native void dlasd6K(int icompq, int nl, int nr, int sqre, double[] d, int offsetd, double[] vf, int offsetvf, double[] vl, int offsetvl, org.netlib.util.doubleW alpha, org.netlib.util.doubleW beta, int[] idxq, int offsetidxq, int[] perm, int offsetperm, org.netlib.util.intW givptr, int[] givcol, int offsetgivcol, int ldgcol, double[] givnum, int offsetgivnum, int ldgnum, double[] poles, int offsetpoles, double[] difl, int offsetdifl, double[] difr, int offsetdifr, double[] z, int offsetz, org.netlib.util.intW k, org.netlib.util.doubleW c, org.netlib.util.doubleW s, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dlasd6();

  protected native void dlasd7K(int icompq, int nl, int nr, int sqre, org.netlib.util.intW k, double[] d, int offsetd, double[] z, int offsetz, double[] zw, int offsetzw, double[] vf, int offsetvf, double[] vfw, int offsetvfw, double[] vl, int offsetvl, double[] vlw, int offsetvlw, double alpha, double beta, double[] dsigma, int offsetdsigma, int[] idx, int offsetidx, int[] idxp, int offsetidxp, int[] idxq, int offsetidxq, int[] perm, int offsetperm, org.netlib.util.intW givptr, int[] givcol, int offsetgivcol, int ldgcol, double[] givnum, int offsetgivnum, int ldgnum, org.netlib.util.doubleW c, org.netlib.util.doubleW s, org.netlib.util.intW info);
  public native boolean has_dlasd7();

  protected native void dlasd8K(int icompq, int k, double[] d, int offsetd, double[] z, int offsetz, double[] vf, int offsetvf, double[] vl, int offsetvl, double[] difl, int offsetdifl, double[] difr, int offsetdifr, int lddifr, double[] dsigma, int offsetdsigma, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dlasd8();

  protected native void dlasdaK(int icompq, int smlsiz, int n, int sqre, double[] d, int offsetd, double[] e, int offsete, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int[] k, int offsetk, double[] difl, int offsetdifl, double[] difr, int offsetdifr, double[] z, int offsetz, double[] poles, int offsetpoles, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, int ldgcol, int[] perm, int offsetperm, double[] givnum, int offsetgivnum, double[] c, int offsetc, double[] s, int offsets, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dlasda();

  protected native void dlasdqK(String uplo, int sqre, int n, int ncvt, int nru, int ncc, double[] d, int offsetd, double[] e, int offsete, double[] vt, int offsetvt, int ldvt, double[] u, int offsetu, int ldu, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dlasdq();

  protected native void dlasdtK(int n, org.netlib.util.intW lvl, org.netlib.util.intW nd, int[] inode, int offsetinode, int[] ndiml, int offsetndiml, int[] ndimr, int offsetndimr, int msub);
  public native boolean has_dlasdt();

  protected native void dlasetK(String uplo, int m, int n, double alpha, double beta, double[] a, int offseta, int lda);
  public native boolean has_dlaset();

  protected native void dlasq1K(int n, double[] d, int offsetd, double[] e, int offsete, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dlasq1();

  protected native void dlasq2K(int n, double[] z, int offsetz, org.netlib.util.intW info);
  public native boolean has_dlasq2();

  protected native void dlasq3K(int i0, org.netlib.util.intW n0, double[] z, int offsetz, int pp, org.netlib.util.doubleW dmin, org.netlib.util.doubleW sigma, org.netlib.util.doubleW desig, org.netlib.util.doubleW qmax, org.netlib.util.intW nfail, org.netlib.util.intW iter, org.netlib.util.intW ndiv, boolean ieee);
  public native boolean has_dlasq3();

  protected native void dlasq4K(int i0, int n0, double[] z, int offsetz, int pp, int n0in, double dmin, double dmin1, double dmin2, double dn, double dn1, double dn2, org.netlib.util.doubleW tau, org.netlib.util.intW ttype);
  public native boolean has_dlasq4();

  protected native void dlasq5K(int i0, int n0, double[] z, int offsetz, int pp, double tau, org.netlib.util.doubleW dmin, org.netlib.util.doubleW dmin1, org.netlib.util.doubleW dmin2, org.netlib.util.doubleW dn, org.netlib.util.doubleW dnm1, org.netlib.util.doubleW dnm2, boolean ieee);
  public native boolean has_dlasq5();

  protected native void dlasq6K(int i0, int n0, double[] z, int offsetz, int pp, org.netlib.util.doubleW dmin, org.netlib.util.doubleW dmin1, org.netlib.util.doubleW dmin2, org.netlib.util.doubleW dn, org.netlib.util.doubleW dnm1, org.netlib.util.doubleW dnm2);
  public native boolean has_dlasq6();

  protected native void dlasrK(String side, String pivot, String direct, int m, int n, double[] c, int offsetc, double[] s, int offsets, double[] a, int offseta, int lda);
  public native boolean has_dlasr();

  protected native void dlasrtK(String id, int n, double[] d, int offsetd, org.netlib.util.intW info);
  public native boolean has_dlasrt();

  protected native void dlassqK(int n, double[] x, int offsetx, int incx, org.netlib.util.doubleW scale, org.netlib.util.doubleW sumsq);
  public native boolean has_dlassq();

  protected native void dlasv2K(double f, double g, double h, org.netlib.util.doubleW ssmin, org.netlib.util.doubleW ssmax, org.netlib.util.doubleW snr, org.netlib.util.doubleW csr, org.netlib.util.doubleW snl, org.netlib.util.doubleW csl);
  public native boolean has_dlasv2();

  protected native void dlaswpK(int n, double[] a, int offseta, int lda, int k1, int k2, int[] ipiv, int offsetipiv, int incx);
  public native boolean has_dlaswp();

  protected native void dlasy2K(boolean ltranl, boolean ltranr, int isgn, int n1, int n2, double[] tl, int offsettl, int ldtl, double[] tr, int offsettr, int ldtr, double[] b, int offsetb, int ldb, org.netlib.util.doubleW scale, double[] x, int offsetx, int ldx, org.netlib.util.doubleW xnorm, org.netlib.util.intW info);
  public native boolean has_dlasy2();

  protected native void dlasyfK(String uplo, int n, int nb, org.netlib.util.intW kb, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] w, int offsetw, int ldw, org.netlib.util.intW info);
  public native boolean has_dlasyf();

  protected native void dlatbsK(String uplo, String trans, String diag, String normin, int n, int kd, double[] ab, int offsetab, int ldab, double[] x, int offsetx, org.netlib.util.doubleW scale, double[] cnorm, int offsetcnorm, org.netlib.util.intW info);
  public native boolean has_dlatbs();

  protected native void dlatdfK(int ijob, int n, double[] z, int offsetz, int ldz, double[] rhs, int offsetrhs, org.netlib.util.doubleW rdsum, org.netlib.util.doubleW rdscal, int[] ipiv, int offsetipiv, int[] jpiv, int offsetjpiv);
  public native boolean has_dlatdf();

  protected native void dlatpsK(String uplo, String trans, String diag, String normin, int n, double[] ap, int offsetap, double[] x, int offsetx, org.netlib.util.doubleW scale, double[] cnorm, int offsetcnorm, org.netlib.util.intW info);
  public native boolean has_dlatps();

  protected native void dlatrdK(String uplo, int n, int nb, double[] a, int offseta, int lda, double[] e, int offsete, double[] tau, int offsettau, double[] w, int offsetw, int ldw);
  public native boolean has_dlatrd();

  protected native void dlatrsK(String uplo, String trans, String diag, String normin, int n, double[] a, int offseta, int lda, double[] x, int offsetx, org.netlib.util.doubleW scale, double[] cnorm, int offsetcnorm, org.netlib.util.intW info);
  public native boolean has_dlatrs();

  protected native void dlatrzK(int m, int n, int l, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork);
  public native boolean has_dlatrz();

  protected native void dlatzmK(String side, int m, int n, double[] v, int offsetv, int incv, double tau, double[] c1, int offsetc1, double[] c2, int offsetc2, int Ldc, double[] work, int offsetwork);
  public native boolean has_dlatzm();

  protected native void dlauu2K(String uplo, int n, double[] a, int offseta, int lda, org.netlib.util.intW info);
  public native boolean has_dlauu2();

  protected native void dlauumK(String uplo, int n, double[] a, int offseta, int lda, org.netlib.util.intW info);
  public native boolean has_dlauum();

  protected native void dlazq3K(int i0, org.netlib.util.intW n0, double[] z, int offsetz, int pp, org.netlib.util.doubleW dmin, org.netlib.util.doubleW sigma, org.netlib.util.doubleW desig, org.netlib.util.doubleW qmax, org.netlib.util.intW nfail, org.netlib.util.intW iter, org.netlib.util.intW ndiv, boolean ieee, org.netlib.util.intW ttype, org.netlib.util.doubleW dmin1, org.netlib.util.doubleW dmin2, org.netlib.util.doubleW dn, org.netlib.util.doubleW dn1, org.netlib.util.doubleW dn2, org.netlib.util.doubleW tau);
  public native boolean has_dlazq3();

  protected native void dlazq4K(int i0, int n0, double[] z, int offsetz, int pp, int n0in, double dmin, double dmin1, double dmin2, double dn, double dn1, double dn2, org.netlib.util.doubleW tau, org.netlib.util.intW ttype, org.netlib.util.doubleW g);
  public native boolean has_dlazq4();

  protected native void dopgtrK(String uplo, int n, double[] ap, int offsetap, double[] tau, int offsettau, double[] q, int offsetq, int ldq, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dopgtr();

  protected native void dopmtrK(String side, String uplo, String trans, int m, int n, double[] ap, int offsetap, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dopmtr();

  protected native void dorg2lK(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dorg2l();

  protected native void dorg2rK(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dorg2r();

  protected native void dorgbrK(String vect, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dorgbr();

  protected native void dorghrK(int n, int ilo, int ihi, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dorghr();

  protected native void dorgl2K(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dorgl2();

  protected native void dorglqK(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dorglq();

  protected native void dorgqlK(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dorgql();

  protected native void dorgqrK(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dorgqr();

  protected native void dorgr2K(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dorgr2();

  protected native void dorgrqK(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dorgrq();

  protected native void dorgtrK(String uplo, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dorgtr();

  protected native void dorm2lK(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dorm2l();

  protected native void dorm2rK(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dorm2r();

  protected native void dormbrK(String vect, String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dormbr();

  protected native void dormhrK(String side, String trans, int m, int n, int ilo, int ihi, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dormhr();

  protected native void dorml2K(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dorml2();

  protected native void dormlqK(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dormlq();

  protected native void dormqlK(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dormql();

  protected native void dormqrK(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dormqr();

  protected native void dormr2K(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dormr2();

  protected native void dormr3K(String side, String trans, int m, int n, int k, int l, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dormr3();

  protected native void dormrqK(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dormrq();

  protected native void dormrzK(String side, String trans, int m, int n, int k, int l, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dormrz();

  protected native void dormtrK(String side, String uplo, String trans, int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dormtr();

  protected native void dpbconK(String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double anorm, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dpbcon();

  protected native void dpbequK(String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double[] s, int offsets, org.netlib.util.doubleW scond, org.netlib.util.doubleW amax, org.netlib.util.intW info);
  public native boolean has_dpbequ();

  protected native void dpbrfsK(String uplo, int n, int kd, int nrhs, double[] ab, int offsetab, int ldab, double[] afb, int offsetafb, int ldafb, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dpbrfs();

  protected native void dpbstfK(String uplo, int n, int kd, double[] ab, int offsetab, int ldab, org.netlib.util.intW info);
  public native boolean has_dpbstf();

  protected native void dpbsvK(String uplo, int n, int kd, int nrhs, double[] ab, int offsetab, int ldab, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dpbsv();

  protected native void dpbsvxK(String fact, String uplo, int n, int kd, int nrhs, double[] ab, int offsetab, int ldab, double[] afb, int offsetafb, int ldafb, org.netlib.util.StringW equed, double[] s, int offsets, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dpbsvx();

  protected native void dpbtf2K(String uplo, int n, int kd, double[] ab, int offsetab, int ldab, org.netlib.util.intW info);
  public native boolean has_dpbtf2();

  protected native void dpbtrfK(String uplo, int n, int kd, double[] ab, int offsetab, int ldab, org.netlib.util.intW info);
  public native boolean has_dpbtrf();

  protected native void dpbtrsK(String uplo, int n, int kd, int nrhs, double[] ab, int offsetab, int ldab, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dpbtrs();

  protected native void dpoconK(String uplo, int n, double[] a, int offseta, int lda, double anorm, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dpocon();

  protected native void dpoequK(int n, double[] a, int offseta, int lda, double[] s, int offsets, org.netlib.util.doubleW scond, org.netlib.util.doubleW amax, org.netlib.util.intW info);
  public native boolean has_dpoequ();

  protected native void dporfsK(String uplo, int n, int nrhs, double[] a, int offseta, int lda, double[] af, int offsetaf, int ldaf, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dporfs();

  protected native void dposvK(String uplo, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dposv();

  protected native void dposvxK(String fact, String uplo, int n, int nrhs, double[] a, int offseta, int lda, double[] af, int offsetaf, int ldaf, org.netlib.util.StringW equed, double[] s, int offsets, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dposvx();

  protected native void dpotf2K(String uplo, int n, double[] a, int offseta, int lda, org.netlib.util.intW info);
  public native boolean has_dpotf2();

  protected native void dpotrfK(String uplo, int n, double[] a, int offseta, int lda, org.netlib.util.intW info);
  public native boolean has_dpotrf();

  protected native void dpotriK(String uplo, int n, double[] a, int offseta, int lda, org.netlib.util.intW info);
  public native boolean has_dpotri();

  protected native void dpotrsK(String uplo, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dpotrs();

  protected native void dppconK(String uplo, int n, double[] ap, int offsetap, double anorm, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dppcon();

  protected native void dppequK(String uplo, int n, double[] ap, int offsetap, double[] s, int offsets, org.netlib.util.doubleW scond, org.netlib.util.doubleW amax, org.netlib.util.intW info);
  public native boolean has_dppequ();

  protected native void dpprfsK(String uplo, int n, int nrhs, double[] ap, int offsetap, double[] afp, int offsetafp, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dpprfs();

  protected native void dppsvK(String uplo, int n, int nrhs, double[] ap, int offsetap, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dppsv();

  protected native void dppsvxK(String fact, String uplo, int n, int nrhs, double[] ap, int offsetap, double[] afp, int offsetafp, org.netlib.util.StringW equed, double[] s, int offsets, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dppsvx();

  protected native void dpptrfK(String uplo, int n, double[] ap, int offsetap, org.netlib.util.intW info);
  public native boolean has_dpptrf();

  protected native void dpptriK(String uplo, int n, double[] ap, int offsetap, org.netlib.util.intW info);
  public native boolean has_dpptri();

  protected native void dpptrsK(String uplo, int n, int nrhs, double[] ap, int offsetap, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dpptrs();

  protected native void dptconK(int n, double[] d, int offsetd, double[] e, int offsete, double anorm, org.netlib.util.doubleW rcond, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dptcon();

  protected native void dpteqrK(String compz, int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, int ldz, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dpteqr();

  protected native void dptrfsK(int n, int nrhs, double[] d, int offsetd, double[] e, int offsete, double[] df, int offsetdf, double[] ef, int offsetef, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dptrfs();

  protected native void dptsvK(int n, int nrhs, double[] d, int offsetd, double[] e, int offsete, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dptsv();

  protected native void dptsvxK(String fact, int n, int nrhs, double[] d, int offsetd, double[] e, int offsete, double[] df, int offsetdf, double[] ef, int offsetef, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dptsvx();

  protected native void dpttrfK(int n, double[] d, int offsetd, double[] e, int offsete, org.netlib.util.intW info);
  public native boolean has_dpttrf();

  protected native void dpttrsK(int n, int nrhs, double[] d, int offsetd, double[] e, int offsete, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dpttrs();

  protected native void dptts2K(int n, int nrhs, double[] d, int offsetd, double[] e, int offsete, double[] b, int offsetb, int ldb);
  public native boolean has_dptts2();

  protected native void drsclK(int n, double sa, double[] sx, int offsetsx, int incx);
  public native boolean has_drscl();

  protected native void dsbevK(String jobz, String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dsbev();

  protected native void dsbevdK(String jobz, String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_dsbevd();

  protected native void dsbevxK(String jobz, String range, String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double[] q, int offsetq, int ldq, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info);
  public native boolean has_dsbevx();

  protected native void dsbgstK(String vect, String uplo, int n, int ka, int kb, double[] ab, int offsetab, int ldab, double[] bb, int offsetbb, int ldbb, double[] x, int offsetx, int ldx, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dsbgst();

  protected native void dsbgvK(String jobz, String uplo, int n, int ka, int kb, double[] ab, int offsetab, int ldab, double[] bb, int offsetbb, int ldbb, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dsbgv();

  protected native void dsbgvdK(String jobz, String uplo, int n, int ka, int kb, double[] ab, int offsetab, int ldab, double[] bb, int offsetbb, int ldbb, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_dsbgvd();

  protected native void dsbgvxK(String jobz, String range, String uplo, int n, int ka, int kb, double[] ab, int offsetab, int ldab, double[] bb, int offsetbb, int ldbb, double[] q, int offsetq, int ldq, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info);
  public native boolean has_dsbgvx();

  protected native void dsbtrdK(String vect, String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double[] d, int offsetd, double[] e, int offsete, double[] q, int offsetq, int ldq, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dsbtrd();

  protected native void dsgesvK(int n, int nrhs, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] work, int offsetwork, float[] swork, int offsetswork, org.netlib.util.intW iter, org.netlib.util.intW info);
  public native boolean has_dsgesv();

  protected native void dspconK(String uplo, int n, double[] ap, int offsetap, int[] ipiv, int offsetipiv, double anorm, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dspcon();

  protected native void dspevK(String jobz, String uplo, int n, double[] ap, int offsetap, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dspev();

  protected native void dspevdK(String jobz, String uplo, int n, double[] ap, int offsetap, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_dspevd();

  protected native void dspevxK(String jobz, String range, String uplo, int n, double[] ap, int offsetap, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info);
  public native boolean has_dspevx();

  protected native void dspgstK(int itype, String uplo, int n, double[] ap, int offsetap, double[] bp, int offsetbp, org.netlib.util.intW info);
  public native boolean has_dspgst();

  protected native void dspgvK(int itype, String jobz, String uplo, int n, double[] ap, int offsetap, double[] bp, int offsetbp, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dspgv();

  protected native void dspgvdK(int itype, String jobz, String uplo, int n, double[] ap, int offsetap, double[] bp, int offsetbp, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_dspgvd();

  protected native void dspgvxK(int itype, String jobz, String range, String uplo, int n, double[] ap, int offsetap, double[] bp, int offsetbp, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info);
  public native boolean has_dspgvx();

  protected native void dsprfsK(String uplo, int n, int nrhs, double[] ap, int offsetap, double[] afp, int offsetafp, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dsprfs();

  protected native void dspsvK(String uplo, int n, int nrhs, double[] ap, int offsetap, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dspsv();

  protected native void dspsvxK(String fact, String uplo, int n, int nrhs, double[] ap, int offsetap, double[] afp, int offsetafp, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dspsvx();

  protected native void dsptrdK(String uplo, int n, double[] ap, int offsetap, double[] d, int offsetd, double[] e, int offsete, double[] tau, int offsettau, org.netlib.util.intW info);
  public native boolean has_dsptrd();

  protected native void dsptrfK(String uplo, int n, double[] ap, int offsetap, int[] ipiv, int offsetipiv, org.netlib.util.intW info);
  public native boolean has_dsptrf();

  protected native void dsptriK(String uplo, int n, double[] ap, int offsetap, int[] ipiv, int offsetipiv, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dsptri();

  protected native void dsptrsK(String uplo, int n, int nrhs, double[] ap, int offsetap, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dsptrs();

  protected native void dstebzK(String range, String order, int n, double vl, double vu, int il, int iu, double abstol, double[] d, int offsetd, double[] e, int offsete, org.netlib.util.intW m, org.netlib.util.intW nsplit, double[] w, int offsetw, int[] iblock, int offsetiblock, int[] isplit, int offsetisplit, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dstebz();

  protected native void dstedcK(String compz, int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_dstedc();

  protected native void dstegrK(String jobz, String range, int n, double[] d, int offsetd, double[] e, int offsete, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_dstegr();

  protected native void dsteinK(int n, double[] d, int offsetd, double[] e, int offsete, int m, double[] w, int offsetw, int[] iblock, int offsetiblock, int[] isplit, int offsetisplit, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info);
  public native boolean has_dstein();

  protected native void dstemrK(String jobz, String range, int n, double[] d, int offsetd, double[] e, int offsete, double vl, double vu, int il, int iu, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, int nzc, int[] isuppz, int offsetisuppz, org.netlib.util.booleanW tryrac, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_dstemr();

  protected native void dsteqrK(String compz, int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, int ldz, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dsteqr();

  protected native void dsterfK(int n, double[] d, int offsetd, double[] e, int offsete, org.netlib.util.intW info);
  public native boolean has_dsterf();

  protected native void dstevK(String jobz, int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, int ldz, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dstev();

  protected native void dstevdK(String jobz, int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_dstevd();

  protected native void dstevrK(String jobz, String range, int n, double[] d, int offsetd, double[] e, int offsete, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_dstevr();

  protected native void dstevxK(String jobz, String range, int n, double[] d, int offsetd, double[] e, int offsete, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info);
  public native boolean has_dstevx();

  protected native void dsyconK(String uplo, int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double anorm, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dsycon();

  protected native void dsyevK(String jobz, String uplo, int n, double[] a, int offseta, int lda, double[] w, int offsetw, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dsyev();

  protected native void dsyevdK(String jobz, String uplo, int n, double[] a, int offseta, int lda, double[] w, int offsetw, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_dsyevd();

  protected native void dsyevrK(String jobz, String range, String uplo, int n, double[] a, int offseta, int lda, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_dsyevr();

  protected native void dsyevxK(String jobz, String range, String uplo, int n, double[] a, int offseta, int lda, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info);
  public native boolean has_dsyevx();

  protected native void dsygs2K(int itype, String uplo, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dsygs2();

  protected native void dsygstK(int itype, String uplo, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dsygst();

  protected native void dsygvK(int itype, String jobz, String uplo, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] w, int offsetw, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dsygv();

  protected native void dsygvdK(int itype, String jobz, String uplo, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] w, int offsetw, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_dsygvd();

  protected native void dsygvxK(int itype, String jobz, String range, String uplo, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info);
  public native boolean has_dsygvx();

  protected native void dsyrfsK(String uplo, int n, int nrhs, double[] a, int offseta, int lda, double[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dsyrfs();

  protected native void dsysvK(String uplo, int n, int nrhs, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dsysv();

  protected native void dsysvxK(String fact, String uplo, int n, int nrhs, double[] a, int offseta, int lda, double[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dsysvx();

  protected native void dsytd2K(String uplo, int n, double[] a, int offseta, int lda, double[] d, int offsetd, double[] e, int offsete, double[] tau, int offsettau, org.netlib.util.intW info);
  public native boolean has_dsytd2();

  protected native void dsytf2K(String uplo, int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, org.netlib.util.intW info);
  public native boolean has_dsytf2();

  protected native void dsytrdK(String uplo, int n, double[] a, int offseta, int lda, double[] d, int offsetd, double[] e, int offsete, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dsytrd();

  protected native void dsytrfK(String uplo, int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dsytrf();

  protected native void dsytriK(String uplo, int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dsytri();

  protected native void dsytrsK(String uplo, int n, int nrhs, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dsytrs();

  protected native void dtbconK(String norm, String uplo, String diag, int n, int kd, double[] ab, int offsetab, int ldab, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dtbcon();

  protected native void dtbrfsK(String uplo, String trans, String diag, int n, int kd, int nrhs, double[] ab, int offsetab, int ldab, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dtbrfs();

  protected native void dtbtrsK(String uplo, String trans, String diag, int n, int kd, int nrhs, double[] ab, int offsetab, int ldab, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dtbtrs();

  protected native void dtgevcK(String side, String howmny, boolean[] select, int offsetselect, int n, double[] s, int offsets, int lds, double[] p, int offsetp, int ldp, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, int mm, org.netlib.util.intW m, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dtgevc();

  protected native void dtgex2K(boolean wantq, boolean wantz, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] q, int offsetq, int ldq, double[] z, int offsetz, int ldz, int j1, int n1, int n2, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dtgex2();

  protected native void dtgexcK(boolean wantq, boolean wantz, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] q, int offsetq, int ldq, double[] z, int offsetz, int ldz, org.netlib.util.intW ifst, org.netlib.util.intW ilst, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dtgexc();

  protected native void dtgsenK(int ijob, boolean wantq, boolean wantz, boolean[] select, int offsetselect, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] q, int offsetq, int ldq, double[] z, int offsetz, int ldz, org.netlib.util.intW m, org.netlib.util.doubleW pl, org.netlib.util.doubleW pr, double[] dif, int offsetdif, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_dtgsen();

  protected native void dtgsjaK(String jobu, String jobv, String jobq, int m, int p, int n, int k, int l, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double tola, double tolb, double[] alpha, int offsetalpha, double[] beta, int offsetbeta, double[] u, int offsetu, int ldu, double[] v, int offsetv, int ldv, double[] q, int offsetq, int ldq, double[] work, int offsetwork, org.netlib.util.intW ncycle, org.netlib.util.intW info);
  public native boolean has_dtgsja();

  protected native void dtgsnaK(String job, String howmny, boolean[] select, int offsetselect, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, double[] s, int offsets, double[] dif, int offsetdif, int mm, org.netlib.util.intW m, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dtgsna();

  protected native void dtgsy2K(String trans, int ijob, int m, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] c, int offsetc, int Ldc, double[] d, int offsetd, int ldd, double[] e, int offsete, int lde, double[] f, int offsetf, int ldf, org.netlib.util.doubleW scale, org.netlib.util.doubleW rdsum, org.netlib.util.doubleW rdscal, int[] iwork, int offsetiwork, org.netlib.util.intW pq, org.netlib.util.intW info);
  public native boolean has_dtgsy2();

  protected native void dtgsylK(String trans, int ijob, int m, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] c, int offsetc, int Ldc, double[] d, int offsetd, int ldd, double[] e, int offsete, int lde, double[] f, int offsetf, int ldf, org.netlib.util.doubleW scale, org.netlib.util.doubleW dif, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dtgsyl();

  protected native void dtpconK(String norm, String uplo, String diag, int n, double[] ap, int offsetap, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dtpcon();

  protected native void dtprfsK(String uplo, String trans, String diag, int n, int nrhs, double[] ap, int offsetap, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dtprfs();

  protected native void dtptriK(String uplo, String diag, int n, double[] ap, int offsetap, org.netlib.util.intW info);
  public native boolean has_dtptri();

  protected native void dtptrsK(String uplo, String trans, String diag, int n, int nrhs, double[] ap, int offsetap, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dtptrs();

  protected native void dtrconK(String norm, String uplo, String diag, int n, double[] a, int offseta, int lda, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dtrcon();

  protected native void dtrevcK(String side, String howmny, boolean[] select, int offsetselect, int n, double[] t, int offsett, int ldt, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, int mm, org.netlib.util.intW m, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dtrevc();

  protected native void dtrexcK(String compq, int n, double[] t, int offsett, int ldt, double[] q, int offsetq, int ldq, org.netlib.util.intW ifst, org.netlib.util.intW ilst, double[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_dtrexc();

  protected native void dtrrfsK(String uplo, String trans, String diag, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dtrrfs();

  protected native void dtrsenK(String job, String compq, boolean[] select, int offsetselect, int n, double[] t, int offsett, int ldt, double[] q, int offsetq, int ldq, double[] wr, int offsetwr, double[] wi, int offsetwi, org.netlib.util.intW m, org.netlib.util.doubleW s, org.netlib.util.doubleW sep, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_dtrsen();

  protected native void dtrsnaK(String job, String howmny, boolean[] select, int offsetselect, int n, double[] t, int offsett, int ldt, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, double[] s, int offsets, double[] sep, int offsetsep, int mm, org.netlib.util.intW m, double[] work, int offsetwork, int ldwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_dtrsna();

  protected native void dtrsylK(String trana, String tranb, int isgn, int m, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] c, int offsetc, int Ldc, org.netlib.util.doubleW scale, org.netlib.util.intW info);
  public native boolean has_dtrsyl();

  protected native void dtrti2K(String uplo, String diag, int n, double[] a, int offseta, int lda, org.netlib.util.intW info);
  public native boolean has_dtrti2();

  protected native void dtrtriK(String uplo, String diag, int n, double[] a, int offseta, int lda, org.netlib.util.intW info);
  public native boolean has_dtrtri();

  protected native void dtrtrsK(String uplo, String trans, String diag, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_dtrtrs();

  protected native void dtzrqfK(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, org.netlib.util.intW info);
  public native boolean has_dtzrqf();

  protected native void dtzrzfK(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_dtzrzf();

  protected native int ieeeckK(int ispec, float zero, float one);
  public native boolean has_ieeeck();

  protected native int ilaenvK(int ispec, String name, String opts, int n1, int n2, int n3, int n4);
  public native boolean has_ilaenv();

  protected native void ilaverK(org.netlib.util.intW vers_major, org.netlib.util.intW vers_minor, org.netlib.util.intW vers_patch);
  public native boolean has_ilaver();

  protected native int iparmqK(int ispec, String name, String opts, int n, int ilo, int ihi, int lwork);
  public native boolean has_iparmq();

  protected native boolean lsamenK(int n, String ca, String cb);
  public native boolean has_lsamen();

  protected native void sbdsdcK(String uplo, String compq, int n, float[] d, int offsetd, float[] e, int offsete, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int ldvt, float[] q, int offsetq, int[] iq, int offsetiq, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sbdsdc();

  protected native void sbdsqrK(String uplo, int n, int ncvt, int nru, int ncc, float[] d, int offsetd, float[] e, int offsete, float[] vt, int offsetvt, int ldvt, float[] u, int offsetu, int ldu, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sbdsqr();

  protected native void sdisnaK(String job, int m, int n, float[] d, int offsetd, float[] sep, int offsetsep, org.netlib.util.intW info);
  public native boolean has_sdisna();

  protected native void sgbbrdK(String vect, int m, int n, int ncc, int kl, int ku, float[] ab, int offsetab, int ldab, float[] d, int offsetd, float[] e, int offsete, float[] q, int offsetq, int ldq, float[] pt, int offsetpt, int ldpt, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sgbbrd();

  protected native void sgbconK(String norm, int n, int kl, int ku, float[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, float anorm, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sgbcon();

  protected native void sgbequK(int m, int n, int kl, int ku, float[] ab, int offsetab, int ldab, float[] r, int offsetr, float[] c, int offsetc, org.netlib.util.floatW rowcnd, org.netlib.util.floatW colcnd, org.netlib.util.floatW amax, org.netlib.util.intW info);
  public native boolean has_sgbequ();

  protected native void sgbrfsK(String trans, int n, int kl, int ku, int nrhs, float[] ab, int offsetab, int ldab, float[] afb, int offsetafb, int ldafb, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sgbrfs();

  protected native void sgbsvK(int n, int kl, int ku, int nrhs, float[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_sgbsv();

  protected native void sgbsvxK(String fact, String trans, int n, int kl, int ku, int nrhs, float[] ab, int offsetab, int ldab, float[] afb, int offsetafb, int ldafb, int[] ipiv, int offsetipiv, org.netlib.util.StringW equed, float[] r, int offsetr, float[] c, int offsetc, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, org.netlib.util.floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sgbsvx();

  protected native void sgbtf2K(int m, int n, int kl, int ku, float[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, org.netlib.util.intW info);
  public native boolean has_sgbtf2();

  protected native void sgbtrfK(int m, int n, int kl, int ku, float[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, org.netlib.util.intW info);
  public native boolean has_sgbtrf();

  protected native void sgbtrsK(String trans, int n, int kl, int ku, int nrhs, float[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_sgbtrs();

  protected native void sgebakK(String job, String side, int n, int ilo, int ihi, float[] scale, int offsetscale, int m, float[] v, int offsetv, int ldv, org.netlib.util.intW info);
  public native boolean has_sgebak();

  protected native void sgebalK(String job, int n, float[] a, int offseta, int lda, org.netlib.util.intW ilo, org.netlib.util.intW ihi, float[] scale, int offsetscale, org.netlib.util.intW info);
  public native boolean has_sgebal();

  protected native void sgebd2K(int m, int n, float[] a, int offseta, int lda, float[] d, int offsetd, float[] e, int offsete, float[] tauq, int offsettauq, float[] taup, int offsettaup, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sgebd2();

  protected native void sgebrdK(int m, int n, float[] a, int offseta, int lda, float[] d, int offsetd, float[] e, int offsete, float[] tauq, int offsettauq, float[] taup, int offsettaup, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sgebrd();

  protected native void sgeconK(String norm, int n, float[] a, int offseta, int lda, float anorm, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sgecon();

  protected native void sgeequK(int m, int n, float[] a, int offseta, int lda, float[] r, int offsetr, float[] c, int offsetc, org.netlib.util.floatW rowcnd, org.netlib.util.floatW colcnd, org.netlib.util.floatW amax, org.netlib.util.intW info);
  public native boolean has_sgeequ();

  protected native void sgeesK(String jobvs, String sort, java.lang.Object select, int n, float[] a, int offseta, int lda, org.netlib.util.intW sdim, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] vs, int offsetvs, int ldvs, float[] work, int offsetwork, int lwork, boolean[] bwork, int offsetbwork, org.netlib.util.intW info);
  public native boolean has_sgees();

  protected native void sgeesxK(String jobvs, String sort, java.lang.Object select, String sense, int n, float[] a, int offseta, int lda, org.netlib.util.intW sdim, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] vs, int offsetvs, int ldvs, org.netlib.util.floatW rconde, org.netlib.util.floatW rcondv, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, boolean[] bwork, int offsetbwork, org.netlib.util.intW info);
  public native boolean has_sgeesx();

  protected native void sgeevK(String jobvl, String jobvr, int n, float[] a, int offseta, int lda, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sgeev();

  protected native void sgeevxK(String balanc, String jobvl, String jobvr, String sense, int n, float[] a, int offseta, int lda, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, org.netlib.util.intW ilo, org.netlib.util.intW ihi, float[] scale, int offsetscale, org.netlib.util.floatW abnrm, float[] rconde, int offsetrconde, float[] rcondv, int offsetrcondv, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sgeevx();

  protected native void sgegsK(String jobvsl, String jobvsr, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] vsl, int offsetvsl, int ldvsl, float[] vsr, int offsetvsr, int ldvsr, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sgegs();

  protected native void sgegvK(String jobvl, String jobvr, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sgegv();

  protected native void sgehd2K(int n, int ilo, int ihi, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sgehd2();

  protected native void sgehrdK(int n, int ilo, int ihi, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sgehrd();

  protected native void sgelq2K(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sgelq2();

  protected native void sgelqfK(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sgelqf();

  protected native void sgelsK(String trans, int m, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sgels();

  protected native void sgelsdK(int m, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] s, int offsets, float rcond, org.netlib.util.intW rank, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sgelsd();

  protected native void sgelssK(int m, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] s, int offsets, float rcond, org.netlib.util.intW rank, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sgelss();

  protected native void sgelsxK(int m, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, int[] jpvt, int offsetjpvt, float rcond, org.netlib.util.intW rank, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sgelsx();

  protected native void sgelsyK(int m, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, int[] jpvt, int offsetjpvt, float rcond, org.netlib.util.intW rank, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sgelsy();

  protected native void sgeql2K(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sgeql2();

  protected native void sgeqlfK(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sgeqlf();

  protected native void sgeqp3K(int m, int n, float[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sgeqp3();

  protected native void sgeqpfK(int m, int n, float[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sgeqpf();

  protected native void sgeqr2K(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sgeqr2();

  protected native void sgeqrfK(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sgeqrf();

  protected native void sgerfsK(String trans, int n, int nrhs, float[] a, int offseta, int lda, float[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sgerfs();

  protected native void sgerq2K(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sgerq2();

  protected native void sgerqfK(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sgerqf();

  protected native void sgesc2K(int n, float[] a, int offseta, int lda, float[] rhs, int offsetrhs, int[] ipiv, int offsetipiv, int[] jpiv, int offsetjpiv, org.netlib.util.floatW scale);
  public native boolean has_sgesc2();

  protected native void sgesddK(String jobz, int m, int n, float[] a, int offseta, int lda, float[] s, int offsets, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int ldvt, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sgesdd();

  protected native void sgesvK(int n, int nrhs, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_sgesv();

  protected native void sgesvdK(String jobu, String jobvt, int m, int n, float[] a, int offseta, int lda, float[] s, int offsets, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int ldvt, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sgesvd();

  protected native void sgesvxK(String fact, String trans, int n, int nrhs, float[] a, int offseta, int lda, float[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, org.netlib.util.StringW equed, float[] r, int offsetr, float[] c, int offsetc, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, org.netlib.util.floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sgesvx();

  protected native void sgetc2K(int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, int[] jpiv, int offsetjpiv, org.netlib.util.intW info);
  public native boolean has_sgetc2();

  protected native void sgetf2K(int m, int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, org.netlib.util.intW info);
  public native boolean has_sgetf2();

  protected native void sgetrfK(int m, int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, org.netlib.util.intW info);
  public native boolean has_sgetrf();

  protected native void sgetriK(int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sgetri();

  protected native void sgetrsK(String trans, int n, int nrhs, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_sgetrs();

  protected native void sggbakK(String job, String side, int n, int ilo, int ihi, float[] lscale, int offsetlscale, float[] rscale, int offsetrscale, int m, float[] v, int offsetv, int ldv, org.netlib.util.intW info);
  public native boolean has_sggbak();

  protected native void sggbalK(String job, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, org.netlib.util.intW ilo, org.netlib.util.intW ihi, float[] lscale, int offsetlscale, float[] rscale, int offsetrscale, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sggbal();

  protected native void sggesK(String jobvsl, String jobvsr, String sort, java.lang.Object selctg, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, org.netlib.util.intW sdim, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] vsl, int offsetvsl, int ldvsl, float[] vsr, int offsetvsr, int ldvsr, float[] work, int offsetwork, int lwork, boolean[] bwork, int offsetbwork, org.netlib.util.intW info);
  public native boolean has_sgges();

  protected native void sggesxK(String jobvsl, String jobvsr, String sort, java.lang.Object selctg, String sense, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, org.netlib.util.intW sdim, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] vsl, int offsetvsl, int ldvsl, float[] vsr, int offsetvsr, int ldvsr, float[] rconde, int offsetrconde, float[] rcondv, int offsetrcondv, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, boolean[] bwork, int offsetbwork, org.netlib.util.intW info);
  public native boolean has_sggesx();

  protected native void sggevK(String jobvl, String jobvr, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sggev();

  protected native void sggevxK(String balanc, String jobvl, String jobvr, String sense, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, org.netlib.util.intW ilo, org.netlib.util.intW ihi, float[] lscale, int offsetlscale, float[] rscale, int offsetrscale, org.netlib.util.floatW abnrm, org.netlib.util.floatW bbnrm, float[] rconde, int offsetrconde, float[] rcondv, int offsetrcondv, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, boolean[] bwork, int offsetbwork, org.netlib.util.intW info);
  public native boolean has_sggevx();

  protected native void sggglmK(int n, int m, int p, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] d, int offsetd, float[] x, int offsetx, float[] y, int offsety, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sggglm();

  protected native void sgghrdK(String compq, String compz, int n, int ilo, int ihi, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] q, int offsetq, int ldq, float[] z, int offsetz, int ldz, org.netlib.util.intW info);
  public native boolean has_sgghrd();

  protected native void sgglseK(int m, int n, int p, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] c, int offsetc, float[] d, int offsetd, float[] x, int offsetx, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sgglse();

  protected native void sggqrfK(int n, int m, int p, float[] a, int offseta, int lda, float[] taua, int offsettaua, float[] b, int offsetb, int ldb, float[] taub, int offsettaub, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sggqrf();

  protected native void sggrqfK(int m, int p, int n, float[] a, int offseta, int lda, float[] taua, int offsettaua, float[] b, int offsetb, int ldb, float[] taub, int offsettaub, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sggrqf();

  protected native void sggsvdK(String jobu, String jobv, String jobq, int m, int n, int p, org.netlib.util.intW k, org.netlib.util.intW l, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alpha, int offsetalpha, float[] beta, int offsetbeta, float[] u, int offsetu, int ldu, float[] v, int offsetv, int ldv, float[] q, int offsetq, int ldq, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sggsvd();

  protected native void sggsvpK(String jobu, String jobv, String jobq, int m, int p, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float tola, float tolb, org.netlib.util.intW k, org.netlib.util.intW l, float[] u, int offsetu, int ldu, float[] v, int offsetv, int ldv, float[] q, int offsetq, int ldq, int[] iwork, int offsetiwork, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sggsvp();

  protected native void sgtconK(String norm, int n, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] du2, int offsetdu2, int[] ipiv, int offsetipiv, float anorm, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sgtcon();

  protected native void sgtrfsK(String trans, int n, int nrhs, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] dlf, int offsetdlf, float[] df, int offsetdf, float[] duf, int offsetduf, float[] du2, int offsetdu2, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sgtrfs();

  protected native void sgtsvK(int n, int nrhs, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_sgtsv();

  protected native void sgtsvxK(String fact, String trans, int n, int nrhs, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] dlf, int offsetdlf, float[] df, int offsetdf, float[] duf, int offsetduf, float[] du2, int offsetdu2, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, org.netlib.util.floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sgtsvx();

  protected native void sgttrfK(int n, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] du2, int offsetdu2, int[] ipiv, int offsetipiv, org.netlib.util.intW info);
  public native boolean has_sgttrf();

  protected native void sgttrsK(String trans, int n, int nrhs, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] du2, int offsetdu2, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_sgttrs();

  protected native void sgtts2K(int itrans, int n, int nrhs, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] du2, int offsetdu2, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb);
  public native boolean has_sgtts2();

  protected native void shgeqzK(String job, String compq, String compz, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] t, int offsett, int ldt, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] q, int offsetq, int ldq, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_shgeqz();

  protected native void shseinK(String side, String eigsrc, String initv, boolean[] select, int offsetselect, int n, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, int mm, org.netlib.util.intW m, float[] work, int offsetwork, int[] ifaill, int offsetifaill, int[] ifailr, int offsetifailr, org.netlib.util.intW info);
  public native boolean has_shsein();

  protected native void shseqrK(String job, String compz, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_shseqr();

  protected native boolean sisnanK(float sin);
  public native boolean has_sisnan();

  protected native void slabadK(org.netlib.util.floatW small, org.netlib.util.floatW large);
  public native boolean has_slabad();

  protected native void slabrdK(int m, int n, int nb, float[] a, int offseta, int lda, float[] d, int offsetd, float[] e, int offsete, float[] tauq, int offsettauq, float[] taup, int offsettaup, float[] x, int offsetx, int ldx, float[] y, int offsety, int ldy);
  public native boolean has_slabrd();

  protected native void slacn2K(int n, float[] v, int offsetv, float[] x, int offsetx, int[] isgn, int offsetisgn, org.netlib.util.floatW est, org.netlib.util.intW kase, int[] isave, int offsetisave);
  public native boolean has_slacn2();

  protected native void slaconK(int n, float[] v, int offsetv, float[] x, int offsetx, int[] isgn, int offsetisgn, org.netlib.util.floatW est, org.netlib.util.intW kase);
  public native boolean has_slacon();

  protected native void slacpyK(String uplo, int m, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb);
  public native boolean has_slacpy();

  protected native void sladivK(float a, float b, float c, float d, org.netlib.util.floatW p, org.netlib.util.floatW q);
  public native boolean has_sladiv();

  protected native void slae2K(float a, float b, float c, org.netlib.util.floatW rt1, org.netlib.util.floatW rt2);
  public native boolean has_slae2();

  protected native void slaebzK(int ijob, int nitmax, int n, int mmax, int minp, int nbmin, float abstol, float reltol, float pivmin, float[] d, int offsetd, float[] e, int offsete, float[] e2, int offsete2, int[] nval, int offsetnval, float[] ab, int offsetab, float[] c, int offsetc, org.netlib.util.intW mout, int[] nab, int offsetnab, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_slaebz();

  protected native void slaed0K(int icompq, int qsiz, int n, float[] d, int offsetd, float[] e, int offsete, float[] q, int offsetq, int ldq, float[] qstore, int offsetqstore, int ldqs, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_slaed0();

  protected native void slaed1K(int n, float[] d, int offsetd, float[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, org.netlib.util.floatW rho, int cutpnt, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_slaed1();

  protected native void slaed2K(org.netlib.util.intW k, int n, int n1, float[] d, int offsetd, float[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, org.netlib.util.floatW rho, float[] z, int offsetz, float[] dlamda, int offsetdlamda, float[] w, int offsetw, float[] q2, int offsetq2, int[] indx, int offsetindx, int[] indxc, int offsetindxc, int[] indxp, int offsetindxp, int[] coltyp, int offsetcoltyp, org.netlib.util.intW info);
  public native boolean has_slaed2();

  protected native void slaed3K(int k, int n, int n1, float[] d, int offsetd, float[] q, int offsetq, int ldq, float rho, float[] dlamda, int offsetdlamda, float[] q2, int offsetq2, int[] indx, int offsetindx, int[] ctot, int offsetctot, float[] w, int offsetw, float[] s, int offsets, org.netlib.util.intW info);
  public native boolean has_slaed3();

  protected native void slaed4K(int n, int i, float[] d, int offsetd, float[] z, int offsetz, float[] delta, int offsetdelta, float rho, org.netlib.util.floatW dlam, org.netlib.util.intW info);
  public native boolean has_slaed4();

  protected native void slaed5K(int i, float[] d, int offsetd, float[] z, int offsetz, float[] delta, int offsetdelta, float rho, org.netlib.util.floatW dlam);
  public native boolean has_slaed5();

  protected native void slaed6K(int kniter, boolean orgati, float rho, float[] d, int offsetd, float[] z, int offsetz, float finit, org.netlib.util.floatW tau, org.netlib.util.intW info);
  public native boolean has_slaed6();

  protected native void slaed7K(int icompq, int n, int qsiz, int tlvls, int curlvl, int curpbm, float[] d, int offsetd, float[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, org.netlib.util.floatW rho, int cutpnt, float[] qstore, int offsetqstore, int[] qptr, int offsetqptr, int[] prmptr, int offsetprmptr, int[] perm, int offsetperm, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, float[] givnum, int offsetgivnum, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_slaed7();

  protected native void slaed8K(int icompq, org.netlib.util.intW k, int n, int qsiz, float[] d, int offsetd, float[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, org.netlib.util.floatW rho, int cutpnt, float[] z, int offsetz, float[] dlamda, int offsetdlamda, float[] q2, int offsetq2, int ldq2, float[] w, int offsetw, int[] perm, int offsetperm, org.netlib.util.intW givptr, int[] givcol, int offsetgivcol, float[] givnum, int offsetgivnum, int[] indxp, int offsetindxp, int[] indx, int offsetindx, org.netlib.util.intW info);
  public native boolean has_slaed8();

  protected native void slaed9K(int k, int kstart, int kstop, int n, float[] d, int offsetd, float[] q, int offsetq, int ldq, float rho, float[] dlamda, int offsetdlamda, float[] w, int offsetw, float[] s, int offsets, int lds, org.netlib.util.intW info);
  public native boolean has_slaed9();

  protected native void slaedaK(int n, int tlvls, int curlvl, int curpbm, int[] prmptr, int offsetprmptr, int[] perm, int offsetperm, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, float[] givnum, int offsetgivnum, float[] q, int offsetq, int[] qptr, int offsetqptr, float[] z, int offsetz, float[] ztemp, int offsetztemp, org.netlib.util.intW info);
  public native boolean has_slaeda();

  protected native void slaeinK(boolean rightv, boolean noinit, int n, float[] h, int offseth, int ldh, float wr, float wi, float[] vr, int offsetvr, float[] vi, int offsetvi, float[] b, int offsetb, int ldb, float[] work, int offsetwork, float eps3, float smlnum, float bignum, org.netlib.util.intW info);
  public native boolean has_slaein();

  protected native void slaev2K(float a, float b, float c, org.netlib.util.floatW rt1, org.netlib.util.floatW rt2, org.netlib.util.floatW cs1, org.netlib.util.floatW sn1);
  public native boolean has_slaev2();

  protected native void slaexcK(boolean wantq, int n, float[] t, int offsett, int ldt, float[] q, int offsetq, int ldq, int j1, int n1, int n2, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_slaexc();

  protected native void slag2K(float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float safmin, org.netlib.util.floatW scale1, org.netlib.util.floatW scale2, org.netlib.util.floatW wr1, org.netlib.util.floatW wr2, org.netlib.util.floatW wi);
  public native boolean has_slag2();

  protected native void slag2dK(int m, int n, float[] sa, int offsetsa, int ldsa, double[] a, int offseta, int lda, org.netlib.util.intW info);
  public native boolean has_slag2d();

  protected native void slags2K(boolean upper, float a1, float a2, float a3, float b1, float b2, float b3, org.netlib.util.floatW csu, org.netlib.util.floatW snu, org.netlib.util.floatW csv, org.netlib.util.floatW snv, org.netlib.util.floatW csq, org.netlib.util.floatW snq);
  public native boolean has_slags2();

  protected native void slagtfK(int n, float[] a, int offseta, float lambda, float[] b, int offsetb, float[] c, int offsetc, float tol, float[] d, int offsetd, int[] in, int offsetin, org.netlib.util.intW info);
  public native boolean has_slagtf();

  protected native void slagtmK(String trans, int n, int nrhs, float alpha, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] x, int offsetx, int ldx, float beta, float[] b, int offsetb, int ldb);
  public native boolean has_slagtm();

  protected native void slagtsK(int job, int n, float[] a, int offseta, float[] b, int offsetb, float[] c, int offsetc, float[] d, int offsetd, int[] in, int offsetin, float[] y, int offsety, org.netlib.util.floatW tol, org.netlib.util.intW info);
  public native boolean has_slagts();

  protected native void slagv2K(float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, org.netlib.util.floatW csl, org.netlib.util.floatW snl, org.netlib.util.floatW csr, org.netlib.util.floatW snr);
  public native boolean has_slagv2();

  protected native void slahqrK(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, int iloz, int ihiz, float[] z, int offsetz, int ldz, org.netlib.util.intW info);
  public native boolean has_slahqr();

  protected native void slahr2K(int n, int k, int nb, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] t, int offsett, int ldt, float[] y, int offsety, int ldy);
  public native boolean has_slahr2();

  protected native void slahrdK(int n, int k, int nb, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] t, int offsett, int ldt, float[] y, int offsety, int ldy);
  public native boolean has_slahrd();

  protected native void slaic1K(int job, int j, float[] x, int offsetx, float sest, float[] w, int offsetw, float gamma, org.netlib.util.floatW sestpr, org.netlib.util.floatW s, org.netlib.util.floatW c);
  public native boolean has_slaic1();

  protected native boolean slaisnanK(float sin1, float sin2);
  public native boolean has_slaisnan();

  protected native void slaln2K(boolean ltrans, int na, int nw, float smin, float ca, float[] a, int offseta, int lda, float d1, float d2, float[] b, int offsetb, int ldb, float wr, float wi, float[] x, int offsetx, int ldx, org.netlib.util.floatW scale, org.netlib.util.floatW xnorm, org.netlib.util.intW info);
  public native boolean has_slaln2();

  protected native void slals0K(int icompq, int nl, int nr, int sqre, int nrhs, float[] b, int offsetb, int ldb, float[] bx, int offsetbx, int ldbx, int[] perm, int offsetperm, int givptr, int[] givcol, int offsetgivcol, int ldgcol, float[] givnum, int offsetgivnum, int ldgnum, float[] poles, int offsetpoles, float[] difl, int offsetdifl, float[] difr, int offsetdifr, float[] z, int offsetz, int k, float c, float s, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_slals0();

  protected native void slalsaK(int icompq, int smlsiz, int n, int nrhs, float[] b, int offsetb, int ldb, float[] bx, int offsetbx, int ldbx, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int[] k, int offsetk, float[] difl, int offsetdifl, float[] difr, int offsetdifr, float[] z, int offsetz, float[] poles, int offsetpoles, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, int ldgcol, int[] perm, int offsetperm, float[] givnum, int offsetgivnum, float[] c, int offsetc, float[] s, int offsets, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_slalsa();

  protected native void slalsdK(String uplo, int smlsiz, int n, int nrhs, float[] d, int offsetd, float[] e, int offsete, float[] b, int offsetb, int ldb, float rcond, org.netlib.util.intW rank, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_slalsd();

  protected native void slamrgK(int n1, int n2, float[] a, int offseta, int strd1, int strd2, int[] index, int offsetindex);
  public native boolean has_slamrg();

  protected native int slanegK(int n, float[] d, int offsetd, float[] lld, int offsetlld, float sigma, float pivmin, int r);
  public native boolean has_slaneg();

  protected native float slangbK(String norm, int n, int kl, int ku, float[] ab, int offsetab, int ldab, float[] work, int offsetwork);
  public native boolean has_slangb();

  protected native float slangeK(String norm, int m, int n, float[] a, int offseta, int lda, float[] work, int offsetwork);
  public native boolean has_slange();

  protected native float slangtK(String norm, int n, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu);
  public native boolean has_slangt();

  protected native float slanhsK(String norm, int n, float[] a, int offseta, int lda, float[] work, int offsetwork);
  public native boolean has_slanhs();

  protected native float slansbK(String norm, String uplo, int n, int k, float[] ab, int offsetab, int ldab, float[] work, int offsetwork);
  public native boolean has_slansb();

  protected native float slanspK(String norm, String uplo, int n, float[] ap, int offsetap, float[] work, int offsetwork);
  public native boolean has_slansp();

  protected native float slanstK(String norm, int n, float[] d, int offsetd, float[] e, int offsete);
  public native boolean has_slanst();

  protected native float slansyK(String norm, String uplo, int n, float[] a, int offseta, int lda, float[] work, int offsetwork);
  public native boolean has_slansy();

  protected native float slantbK(String norm, String uplo, String diag, int n, int k, float[] ab, int offsetab, int ldab, float[] work, int offsetwork);
  public native boolean has_slantb();

  protected native float slantpK(String norm, String uplo, String diag, int n, float[] ap, int offsetap, float[] work, int offsetwork);
  public native boolean has_slantp();

  protected native float slantrK(String norm, String uplo, String diag, int m, int n, float[] a, int offseta, int lda, float[] work, int offsetwork);
  public native boolean has_slantr();

  protected native void slanv2K(org.netlib.util.floatW a, org.netlib.util.floatW b, org.netlib.util.floatW c, org.netlib.util.floatW d, org.netlib.util.floatW rt1r, org.netlib.util.floatW rt1i, org.netlib.util.floatW rt2r, org.netlib.util.floatW rt2i, org.netlib.util.floatW cs, org.netlib.util.floatW sn);
  public native boolean has_slanv2();

  protected native void slapllK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, org.netlib.util.floatW ssmin);
  public native boolean has_slapll();

  protected native void slapmtK(boolean forwrd, int m, int n, float[] x, int offsetx, int ldx, int[] k, int offsetk);
  public native boolean has_slapmt();

  protected native float slapy2K(float x, float y);
  public native boolean has_slapy2();

  protected native float slapy3K(float x, float y, float z);
  public native boolean has_slapy3();

  protected native void slaqgbK(int m, int n, int kl, int ku, float[] ab, int offsetab, int ldab, float[] r, int offsetr, float[] c, int offsetc, float rowcnd, float colcnd, float amax, org.netlib.util.StringW equed);
  public native boolean has_slaqgb();

  protected native void slaqgeK(int m, int n, float[] a, int offseta, int lda, float[] r, int offsetr, float[] c, int offsetc, float rowcnd, float colcnd, float amax, org.netlib.util.StringW equed);
  public native boolean has_slaqge();

  protected native void slaqp2K(int m, int n, int offset, float[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, float[] tau, int offsettau, float[] vn1, int offsetvn1, float[] vn2, int offsetvn2, float[] work, int offsetwork);
  public native boolean has_slaqp2();

  protected native void slaqpsK(int m, int n, int offset, int nb, org.netlib.util.intW kb, float[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, float[] tau, int offsettau, float[] vn1, int offsetvn1, float[] vn2, int offsetvn2, float[] auxv, int offsetauxv, float[] f, int offsetf, int ldf);
  public native boolean has_slaqps();

  protected native void slaqr0K(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, int iloz, int ihiz, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_slaqr0();

  protected native void slaqr1K(int n, float[] h, int offseth, int ldh, float sr1, float si1, float sr2, float si2, float[] v, int offsetv);
  public native boolean has_slaqr1();

  protected native void slaqr2K(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, float[] h, int offseth, int ldh, int iloz, int ihiz, float[] z, int offsetz, int ldz, org.netlib.util.intW ns, org.netlib.util.intW nd, float[] sr, int offsetsr, float[] si, int offsetsi, float[] v, int offsetv, int ldv, int nh, float[] t, int offsett, int ldt, int nv, float[] wv, int offsetwv, int ldwv, float[] work, int offsetwork, int lwork);
  public native boolean has_slaqr2();

  protected native void slaqr3K(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, float[] h, int offseth, int ldh, int iloz, int ihiz, float[] z, int offsetz, int ldz, org.netlib.util.intW ns, org.netlib.util.intW nd, float[] sr, int offsetsr, float[] si, int offsetsi, float[] v, int offsetv, int ldv, int nh, float[] t, int offsett, int ldt, int nv, float[] wv, int offsetwv, int ldwv, float[] work, int offsetwork, int lwork);
  public native boolean has_slaqr3();

  protected native void slaqr4K(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, int iloz, int ihiz, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_slaqr4();

  protected native void slaqr5K(boolean wantt, boolean wantz, int kacc22, int n, int ktop, int kbot, int nshfts, float[] sr, int offsetsr, float[] si, int offsetsi, float[] h, int offseth, int ldh, int iloz, int ihiz, float[] z, int offsetz, int ldz, float[] v, int offsetv, int ldv, float[] u, int offsetu, int ldu, int nv, float[] wv, int offsetwv, int ldwv, int nh, float[] wh, int offsetwh, int ldwh);
  public native boolean has_slaqr5();

  protected native void slaqsbK(String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float[] s, int offsets, float scond, float amax, org.netlib.util.StringW equed);
  public native boolean has_slaqsb();

  protected native void slaqspK(String uplo, int n, float[] ap, int offsetap, float[] s, int offsets, float scond, float amax, org.netlib.util.StringW equed);
  public native boolean has_slaqsp();

  protected native void slaqsyK(String uplo, int n, float[] a, int offseta, int lda, float[] s, int offsets, float scond, float amax, org.netlib.util.StringW equed);
  public native boolean has_slaqsy();

  protected native void slaqtrK(boolean ltran, boolean lreal, int n, float[] t, int offsett, int ldt, float[] b, int offsetb, float w, org.netlib.util.floatW scale, float[] x, int offsetx, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_slaqtr();

  protected native void slar1vK(int n, int b1, int bn, float lambda, float[] d, int offsetd, float[] l, int offsetl, float[] ld, int offsetld, float[] lld, int offsetlld, float pivmin, float gaptol, float[] z, int offsetz, boolean wantnc, org.netlib.util.intW negcnt, org.netlib.util.floatW ztz, org.netlib.util.floatW mingma, org.netlib.util.intW r, int[] isuppz, int offsetisuppz, org.netlib.util.floatW nrminv, org.netlib.util.floatW resid, org.netlib.util.floatW rqcorr, float[] work, int offsetwork);
  public native boolean has_slar1v();

  protected native void slar2vK(int n, float[] x, int offsetx, float[] y, int offsety, float[] z, int offsetz, int incx, float[] c, int offsetc, float[] s, int offsets, int incc);
  public native boolean has_slar2v();

  protected native void slarfK(String side, int m, int n, float[] v, int offsetv, int incv, float tau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork);
  public native boolean has_slarf();

  protected native void slarfbK(String side, String trans, String direct, String storev, int m, int n, int k, float[] v, int offsetv, int ldv, float[] t, int offsett, int ldt, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int ldwork);
  public native boolean has_slarfb();

  protected native void slarfgK(int n, org.netlib.util.floatW alpha, float[] x, int offsetx, int incx, org.netlib.util.floatW tau);
  public native boolean has_slarfg();

  protected native void slarftK(String direct, String storev, int n, int k, float[] v, int offsetv, int ldv, float[] tau, int offsettau, float[] t, int offsett, int ldt);
  public native boolean has_slarft();

  protected native void slarfxK(String side, int m, int n, float[] v, int offsetv, float tau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork);
  public native boolean has_slarfx();

  protected native void slargvK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] c, int offsetc, int incc);
  public native boolean has_slargv();

  protected native void slarnvK(int idist, int[] iseed, int offsetiseed, int n, float[] x, int offsetx);
  public native boolean has_slarnv();

  protected native void slarraK(int n, float[] d, int offsetd, float[] e, int offsete, float[] e2, int offsete2, float spltol, float tnrm, org.netlib.util.intW nsplit, int[] isplit, int offsetisplit, org.netlib.util.intW info);
  public native boolean has_slarra();

  protected native void slarrbK(int n, float[] d, int offsetd, float[] lld, int offsetlld, int ifirst, int ilast, float rtol1, float rtol2, int offset, float[] w, int offsetw, float[] wgap, int offsetwgap, float[] werr, int offsetwerr, float[] work, int offsetwork, int[] iwork, int offsetiwork, float pivmin, float spdiam, int twist, org.netlib.util.intW info);
  public native boolean has_slarrb();

  protected native void slarrcK(String jobt, int n, float vl, float vu, float[] d, int offsetd, float[] e, int offsete, float pivmin, org.netlib.util.intW eigcnt, org.netlib.util.intW lcnt, org.netlib.util.intW rcnt, org.netlib.util.intW info);
  public native boolean has_slarrc();

  protected native void slarrdK(String range, String order, int n, float vl, float vu, int il, int iu, float[] gers, int offsetgers, float reltol, float[] d, int offsetd, float[] e, int offsete, float[] e2, int offsete2, float pivmin, int nsplit, int[] isplit, int offsetisplit, org.netlib.util.intW m, float[] w, int offsetw, float[] werr, int offsetwerr, org.netlib.util.floatW wl, org.netlib.util.floatW wu, int[] iblock, int offsetiblock, int[] indexw, int offsetindexw, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_slarrd();

  protected native void slarreK(String range, int n, org.netlib.util.floatW vl, org.netlib.util.floatW vu, int il, int iu, float[] d, int offsetd, float[] e, int offsete, float[] e2, int offsete2, float rtol1, float rtol2, float spltol, org.netlib.util.intW nsplit, int[] isplit, int offsetisplit, org.netlib.util.intW m, float[] w, int offsetw, float[] werr, int offsetwerr, float[] wgap, int offsetwgap, int[] iblock, int offsetiblock, int[] indexw, int offsetindexw, float[] gers, int offsetgers, org.netlib.util.floatW pivmin, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_slarre();

  protected native void slarrfK(int n, float[] d, int offsetd, float[] l, int offsetl, float[] ld, int offsetld, int clstrt, int clend, float[] w, int offsetw, float[] wgap, int offsetwgap, float[] werr, int offsetwerr, float spdiam, float clgapl, float clgapr, float pivmin, org.netlib.util.floatW sigma, float[] dplus, int offsetdplus, float[] lplus, int offsetlplus, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_slarrf();

  protected native void slarrjK(int n, float[] d, int offsetd, float[] e2, int offsete2, int ifirst, int ilast, float rtol, int offset, float[] w, int offsetw, float[] werr, int offsetwerr, float[] work, int offsetwork, int[] iwork, int offsetiwork, float pivmin, float spdiam, org.netlib.util.intW info);
  public native boolean has_slarrj();

  protected native void slarrkK(int n, int iw, float gl, float gu, float[] d, int offsetd, float[] e2, int offsete2, float pivmin, float reltol, org.netlib.util.floatW w, org.netlib.util.floatW werr, org.netlib.util.intW info);
  public native boolean has_slarrk();

  protected native void slarrrK(int n, float[] d, int offsetd, float[] e, int offsete, org.netlib.util.intW info);
  public native boolean has_slarrr();

  protected native void slarrvK(int n, float vl, float vu, float[] d, int offsetd, float[] l, int offsetl, float pivmin, int[] isplit, int offsetisplit, int m, int dol, int dou, float minrgp, org.netlib.util.floatW rtol1, org.netlib.util.floatW rtol2, float[] w, int offsetw, float[] werr, int offsetwerr, float[] wgap, int offsetwgap, int[] iblock, int offsetiblock, int[] indexw, int offsetindexw, float[] gers, int offsetgers, float[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_slarrv();

  protected native void slartgK(float f, float g, org.netlib.util.floatW cs, org.netlib.util.floatW sn, org.netlib.util.floatW r);
  public native boolean has_slartg();

  protected native void slartvK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] c, int offsetc, float[] s, int offsets, int incc);
  public native boolean has_slartv();

  protected native void slaruvK(int[] iseed, int offsetiseed, int n, float[] x, int offsetx);
  public native boolean has_slaruv();

  protected native void slarzK(String side, int m, int n, int l, float[] v, int offsetv, int incv, float tau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork);
  public native boolean has_slarz();

  protected native void slarzbK(String side, String trans, String direct, String storev, int m, int n, int k, int l, float[] v, int offsetv, int ldv, float[] t, int offsett, int ldt, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int ldwork);
  public native boolean has_slarzb();

  protected native void slarztK(String direct, String storev, int n, int k, float[] v, int offsetv, int ldv, float[] tau, int offsettau, float[] t, int offsett, int ldt);
  public native boolean has_slarzt();

  protected native void slas2K(float f, float g, float h, org.netlib.util.floatW ssmin, org.netlib.util.floatW ssmax);
  public native boolean has_slas2();

  protected native void slasclK(String type, int kl, int ku, float cfrom, float cto, int m, int n, float[] a, int offseta, int lda, org.netlib.util.intW info);
  public native boolean has_slascl();

  protected native void slasd0K(int n, int sqre, float[] d, int offsetd, float[] e, int offsete, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int ldvt, int smlsiz, int[] iwork, int offsetiwork, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_slasd0();

  protected native void slasd1K(int nl, int nr, int sqre, float[] d, int offsetd, org.netlib.util.floatW alpha, org.netlib.util.floatW beta, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int ldvt, int[] idxq, int offsetidxq, int[] iwork, int offsetiwork, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_slasd1();

  protected native void slasd2K(int nl, int nr, int sqre, org.netlib.util.intW k, float[] d, int offsetd, float[] z, int offsetz, float alpha, float beta, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int ldvt, float[] dsigma, int offsetdsigma, float[] u2, int offsetu2, int ldu2, float[] vt2, int offsetvt2, int ldvt2, int[] idxp, int offsetidxp, int[] idx, int offsetidx, int[] idxc, int offsetidxc, int[] idxq, int offsetidxq, int[] coltyp, int offsetcoltyp, org.netlib.util.intW info);
  public native boolean has_slasd2();

  protected native void slasd3K(int nl, int nr, int sqre, int k, float[] d, int offsetd, float[] q, int offsetq, int ldq, float[] dsigma, int offsetdsigma, float[] u, int offsetu, int ldu, float[] u2, int offsetu2, int ldu2, float[] vt, int offsetvt, int ldvt, float[] vt2, int offsetvt2, int ldvt2, int[] idxc, int offsetidxc, int[] ctot, int offsetctot, float[] z, int offsetz, org.netlib.util.intW info);
  public native boolean has_slasd3();

  protected native void slasd4K(int n, int i, float[] d, int offsetd, float[] z, int offsetz, float[] delta, int offsetdelta, float rho, org.netlib.util.floatW sigma, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_slasd4();

  protected native void slasd5K(int i, float[] d, int offsetd, float[] z, int offsetz, float[] delta, int offsetdelta, float rho, org.netlib.util.floatW dsigma, float[] work, int offsetwork);
  public native boolean has_slasd5();

  protected native void slasd6K(int icompq, int nl, int nr, int sqre, float[] d, int offsetd, float[] vf, int offsetvf, float[] vl, int offsetvl, org.netlib.util.floatW alpha, org.netlib.util.floatW beta, int[] idxq, int offsetidxq, int[] perm, int offsetperm, org.netlib.util.intW givptr, int[] givcol, int offsetgivcol, int ldgcol, float[] givnum, int offsetgivnum, int ldgnum, float[] poles, int offsetpoles, float[] difl, int offsetdifl, float[] difr, int offsetdifr, float[] z, int offsetz, org.netlib.util.intW k, org.netlib.util.floatW c, org.netlib.util.floatW s, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_slasd6();

  protected native void slasd7K(int icompq, int nl, int nr, int sqre, org.netlib.util.intW k, float[] d, int offsetd, float[] z, int offsetz, float[] zw, int offsetzw, float[] vf, int offsetvf, float[] vfw, int offsetvfw, float[] vl, int offsetvl, float[] vlw, int offsetvlw, float alpha, float beta, float[] dsigma, int offsetdsigma, int[] idx, int offsetidx, int[] idxp, int offsetidxp, int[] idxq, int offsetidxq, int[] perm, int offsetperm, org.netlib.util.intW givptr, int[] givcol, int offsetgivcol, int ldgcol, float[] givnum, int offsetgivnum, int ldgnum, org.netlib.util.floatW c, org.netlib.util.floatW s, org.netlib.util.intW info);
  public native boolean has_slasd7();

  protected native void slasd8K(int icompq, int k, float[] d, int offsetd, float[] z, int offsetz, float[] vf, int offsetvf, float[] vl, int offsetvl, float[] difl, int offsetdifl, float[] difr, int offsetdifr, int lddifr, float[] dsigma, int offsetdsigma, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_slasd8();

  protected native void slasdaK(int icompq, int smlsiz, int n, int sqre, float[] d, int offsetd, float[] e, int offsete, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int[] k, int offsetk, float[] difl, int offsetdifl, float[] difr, int offsetdifr, float[] z, int offsetz, float[] poles, int offsetpoles, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, int ldgcol, int[] perm, int offsetperm, float[] givnum, int offsetgivnum, float[] c, int offsetc, float[] s, int offsets, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_slasda();

  protected native void slasdqK(String uplo, int sqre, int n, int ncvt, int nru, int ncc, float[] d, int offsetd, float[] e, int offsete, float[] vt, int offsetvt, int ldvt, float[] u, int offsetu, int ldu, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_slasdq();

  protected native void slasdtK(int n, org.netlib.util.intW lvl, org.netlib.util.intW nd, int[] inode, int offsetinode, int[] ndiml, int offsetndiml, int[] ndimr, int offsetndimr, int msub);
  public native boolean has_slasdt();

  protected native void slasetK(String uplo, int m, int n, float alpha, float beta, float[] a, int offseta, int lda);
  public native boolean has_slaset();

  protected native void slasq1K(int n, float[] d, int offsetd, float[] e, int offsete, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_slasq1();

  protected native void slasq2K(int n, float[] z, int offsetz, org.netlib.util.intW info);
  public native boolean has_slasq2();

  protected native void slasq3K(int i0, org.netlib.util.intW n0, float[] z, int offsetz, int pp, org.netlib.util.floatW dmin, org.netlib.util.floatW sigma, org.netlib.util.floatW desig, org.netlib.util.floatW qmax, org.netlib.util.intW nfail, org.netlib.util.intW iter, org.netlib.util.intW ndiv, boolean ieee);
  public native boolean has_slasq3();

  protected native void slasq4K(int i0, int n0, float[] z, int offsetz, int pp, int n0in, float dmin, float dmin1, float dmin2, float dn, float dn1, float dn2, org.netlib.util.floatW tau, org.netlib.util.intW ttype);
  public native boolean has_slasq4();

  protected native void slasq5K(int i0, int n0, float[] z, int offsetz, int pp, float tau, org.netlib.util.floatW dmin, org.netlib.util.floatW dmin1, org.netlib.util.floatW dmin2, org.netlib.util.floatW dn, org.netlib.util.floatW dnm1, org.netlib.util.floatW dnm2, boolean ieee);
  public native boolean has_slasq5();

  protected native void slasq6K(int i0, int n0, float[] z, int offsetz, int pp, org.netlib.util.floatW dmin, org.netlib.util.floatW dmin1, org.netlib.util.floatW dmin2, org.netlib.util.floatW dn, org.netlib.util.floatW dnm1, org.netlib.util.floatW dnm2);
  public native boolean has_slasq6();

  protected native void slasrK(String side, String pivot, String direct, int m, int n, float[] c, int offsetc, float[] s, int offsets, float[] a, int offseta, int lda);
  public native boolean has_slasr();

  protected native void slasrtK(String id, int n, float[] d, int offsetd, org.netlib.util.intW info);
  public native boolean has_slasrt();

  protected native void slassqK(int n, float[] x, int offsetx, int incx, org.netlib.util.floatW scale, org.netlib.util.floatW sumsq);
  public native boolean has_slassq();

  protected native void slasv2K(float f, float g, float h, org.netlib.util.floatW ssmin, org.netlib.util.floatW ssmax, org.netlib.util.floatW snr, org.netlib.util.floatW csr, org.netlib.util.floatW snl, org.netlib.util.floatW csl);
  public native boolean has_slasv2();

  protected native void slaswpK(int n, float[] a, int offseta, int lda, int k1, int k2, int[] ipiv, int offsetipiv, int incx);
  public native boolean has_slaswp();

  protected native void slasy2K(boolean ltranl, boolean ltranr, int isgn, int n1, int n2, float[] tl, int offsettl, int ldtl, float[] tr, int offsettr, int ldtr, float[] b, int offsetb, int ldb, org.netlib.util.floatW scale, float[] x, int offsetx, int ldx, org.netlib.util.floatW xnorm, org.netlib.util.intW info);
  public native boolean has_slasy2();

  protected native void slasyfK(String uplo, int n, int nb, org.netlib.util.intW kb, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] w, int offsetw, int ldw, org.netlib.util.intW info);
  public native boolean has_slasyf();

  protected native void slatbsK(String uplo, String trans, String diag, String normin, int n, int kd, float[] ab, int offsetab, int ldab, float[] x, int offsetx, org.netlib.util.floatW scale, float[] cnorm, int offsetcnorm, org.netlib.util.intW info);
  public native boolean has_slatbs();

  protected native void slatdfK(int ijob, int n, float[] z, int offsetz, int ldz, float[] rhs, int offsetrhs, org.netlib.util.floatW rdsum, org.netlib.util.floatW rdscal, int[] ipiv, int offsetipiv, int[] jpiv, int offsetjpiv);
  public native boolean has_slatdf();

  protected native void slatpsK(String uplo, String trans, String diag, String normin, int n, float[] ap, int offsetap, float[] x, int offsetx, org.netlib.util.floatW scale, float[] cnorm, int offsetcnorm, org.netlib.util.intW info);
  public native boolean has_slatps();

  protected native void slatrdK(String uplo, int n, int nb, float[] a, int offseta, int lda, float[] e, int offsete, float[] tau, int offsettau, float[] w, int offsetw, int ldw);
  public native boolean has_slatrd();

  protected native void slatrsK(String uplo, String trans, String diag, String normin, int n, float[] a, int offseta, int lda, float[] x, int offsetx, org.netlib.util.floatW scale, float[] cnorm, int offsetcnorm, org.netlib.util.intW info);
  public native boolean has_slatrs();

  protected native void slatrzK(int m, int n, int l, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork);
  public native boolean has_slatrz();

  protected native void slatzmK(String side, int m, int n, float[] v, int offsetv, int incv, float tau, float[] c1, int offsetc1, float[] c2, int offsetc2, int Ldc, float[] work, int offsetwork);
  public native boolean has_slatzm();

  protected native void slauu2K(String uplo, int n, float[] a, int offseta, int lda, org.netlib.util.intW info);
  public native boolean has_slauu2();

  protected native void slauumK(String uplo, int n, float[] a, int offseta, int lda, org.netlib.util.intW info);
  public native boolean has_slauum();

  protected native void slazq3K(int i0, org.netlib.util.intW n0, float[] z, int offsetz, int pp, org.netlib.util.floatW dmin, org.netlib.util.floatW sigma, org.netlib.util.floatW desig, org.netlib.util.floatW qmax, org.netlib.util.intW nfail, org.netlib.util.intW iter, org.netlib.util.intW ndiv, boolean ieee, org.netlib.util.intW ttype, org.netlib.util.floatW dmin1, org.netlib.util.floatW dmin2, org.netlib.util.floatW dn, org.netlib.util.floatW dn1, org.netlib.util.floatW dn2, org.netlib.util.floatW tau);
  public native boolean has_slazq3();

  protected native void slazq4K(int i0, int n0, float[] z, int offsetz, int pp, int n0in, float dmin, float dmin1, float dmin2, float dn, float dn1, float dn2, org.netlib.util.floatW tau, org.netlib.util.intW ttype, org.netlib.util.floatW g);
  public native boolean has_slazq4();

  protected native void sopgtrK(String uplo, int n, float[] ap, int offsetap, float[] tau, int offsettau, float[] q, int offsetq, int ldq, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sopgtr();

  protected native void sopmtrK(String side, String uplo, String trans, int m, int n, float[] ap, int offsetap, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sopmtr();

  protected native void sorg2lK(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sorg2l();

  protected native void sorg2rK(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sorg2r();

  protected native void sorgbrK(String vect, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sorgbr();

  protected native void sorghrK(int n, int ilo, int ihi, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sorghr();

  protected native void sorgl2K(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sorgl2();

  protected native void sorglqK(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sorglq();

  protected native void sorgqlK(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sorgql();

  protected native void sorgqrK(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sorgqr();

  protected native void sorgr2K(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sorgr2();

  protected native void sorgrqK(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sorgrq();

  protected native void sorgtrK(String uplo, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sorgtr();

  protected native void sorm2lK(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sorm2l();

  protected native void sorm2rK(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sorm2r();

  protected native void sormbrK(String vect, String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sormbr();

  protected native void sormhrK(String side, String trans, int m, int n, int ilo, int ihi, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sormhr();

  protected native void sorml2K(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sorml2();

  protected native void sormlqK(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sormlq();

  protected native void sormqlK(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sormql();

  protected native void sormqrK(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sormqr();

  protected native void sormr2K(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sormr2();

  protected native void sormr3K(String side, String trans, int m, int n, int k, int l, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sormr3();

  protected native void sormrqK(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sormrq();

  protected native void sormrzK(String side, String trans, int m, int n, int k, int l, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sormrz();

  protected native void sormtrK(String side, String uplo, String trans, int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_sormtr();

  protected native void spbconK(String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float anorm, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_spbcon();

  protected native void spbequK(String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float[] s, int offsets, org.netlib.util.floatW scond, org.netlib.util.floatW amax, org.netlib.util.intW info);
  public native boolean has_spbequ();

  protected native void spbrfsK(String uplo, int n, int kd, int nrhs, float[] ab, int offsetab, int ldab, float[] afb, int offsetafb, int ldafb, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_spbrfs();

  protected native void spbstfK(String uplo, int n, int kd, float[] ab, int offsetab, int ldab, org.netlib.util.intW info);
  public native boolean has_spbstf();

  protected native void spbsvK(String uplo, int n, int kd, int nrhs, float[] ab, int offsetab, int ldab, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_spbsv();

  protected native void spbsvxK(String fact, String uplo, int n, int kd, int nrhs, float[] ab, int offsetab, int ldab, float[] afb, int offsetafb, int ldafb, org.netlib.util.StringW equed, float[] s, int offsets, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, org.netlib.util.floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_spbsvx();

  protected native void spbtf2K(String uplo, int n, int kd, float[] ab, int offsetab, int ldab, org.netlib.util.intW info);
  public native boolean has_spbtf2();

  protected native void spbtrfK(String uplo, int n, int kd, float[] ab, int offsetab, int ldab, org.netlib.util.intW info);
  public native boolean has_spbtrf();

  protected native void spbtrsK(String uplo, int n, int kd, int nrhs, float[] ab, int offsetab, int ldab, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_spbtrs();

  protected native void spoconK(String uplo, int n, float[] a, int offseta, int lda, float anorm, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_spocon();

  protected native void spoequK(int n, float[] a, int offseta, int lda, float[] s, int offsets, org.netlib.util.floatW scond, org.netlib.util.floatW amax, org.netlib.util.intW info);
  public native boolean has_spoequ();

  protected native void sporfsK(String uplo, int n, int nrhs, float[] a, int offseta, int lda, float[] af, int offsetaf, int ldaf, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sporfs();

  protected native void sposvK(String uplo, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_sposv();

  protected native void sposvxK(String fact, String uplo, int n, int nrhs, float[] a, int offseta, int lda, float[] af, int offsetaf, int ldaf, org.netlib.util.StringW equed, float[] s, int offsets, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, org.netlib.util.floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sposvx();

  protected native void spotf2K(String uplo, int n, float[] a, int offseta, int lda, org.netlib.util.intW info);
  public native boolean has_spotf2();

  protected native void spotrfK(String uplo, int n, float[] a, int offseta, int lda, org.netlib.util.intW info);
  public native boolean has_spotrf();

  protected native void spotriK(String uplo, int n, float[] a, int offseta, int lda, org.netlib.util.intW info);
  public native boolean has_spotri();

  protected native void spotrsK(String uplo, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_spotrs();

  protected native void sppconK(String uplo, int n, float[] ap, int offsetap, float anorm, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sppcon();

  protected native void sppequK(String uplo, int n, float[] ap, int offsetap, float[] s, int offsets, org.netlib.util.floatW scond, org.netlib.util.floatW amax, org.netlib.util.intW info);
  public native boolean has_sppequ();

  protected native void spprfsK(String uplo, int n, int nrhs, float[] ap, int offsetap, float[] afp, int offsetafp, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_spprfs();

  protected native void sppsvK(String uplo, int n, int nrhs, float[] ap, int offsetap, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_sppsv();

  protected native void sppsvxK(String fact, String uplo, int n, int nrhs, float[] ap, int offsetap, float[] afp, int offsetafp, org.netlib.util.StringW equed, float[] s, int offsets, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, org.netlib.util.floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sppsvx();

  protected native void spptrfK(String uplo, int n, float[] ap, int offsetap, org.netlib.util.intW info);
  public native boolean has_spptrf();

  protected native void spptriK(String uplo, int n, float[] ap, int offsetap, org.netlib.util.intW info);
  public native boolean has_spptri();

  protected native void spptrsK(String uplo, int n, int nrhs, float[] ap, int offsetap, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_spptrs();

  protected native void sptconK(int n, float[] d, int offsetd, float[] e, int offsete, float anorm, org.netlib.util.floatW rcond, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sptcon();

  protected native void spteqrK(String compz, int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, int ldz, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_spteqr();

  protected native void sptrfsK(int n, int nrhs, float[] d, int offsetd, float[] e, int offsete, float[] df, int offsetdf, float[] ef, int offsetef, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sptrfs();

  protected native void sptsvK(int n, int nrhs, float[] d, int offsetd, float[] e, int offsete, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_sptsv();

  protected native void sptsvxK(String fact, int n, int nrhs, float[] d, int offsetd, float[] e, int offsete, float[] df, int offsetdf, float[] ef, int offsetef, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, org.netlib.util.floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sptsvx();

  protected native void spttrfK(int n, float[] d, int offsetd, float[] e, int offsete, org.netlib.util.intW info);
  public native boolean has_spttrf();

  protected native void spttrsK(int n, int nrhs, float[] d, int offsetd, float[] e, int offsete, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_spttrs();

  protected native void sptts2K(int n, int nrhs, float[] d, int offsetd, float[] e, int offsete, float[] b, int offsetb, int ldb);
  public native boolean has_sptts2();

  protected native void srsclK(int n, float sa, float[] sx, int offsetsx, int incx);
  public native boolean has_srscl();

  protected native void ssbevK(String jobz, String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_ssbev();

  protected native void ssbevdK(String jobz, String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_ssbevd();

  protected native void ssbevxK(String jobz, String range, String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float[] q, int offsetq, int ldq, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info);
  public native boolean has_ssbevx();

  protected native void ssbgstK(String vect, String uplo, int n, int ka, int kb, float[] ab, int offsetab, int ldab, float[] bb, int offsetbb, int ldbb, float[] x, int offsetx, int ldx, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_ssbgst();

  protected native void ssbgvK(String jobz, String uplo, int n, int ka, int kb, float[] ab, int offsetab, int ldab, float[] bb, int offsetbb, int ldbb, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_ssbgv();

  protected native void ssbgvdK(String jobz, String uplo, int n, int ka, int kb, float[] ab, int offsetab, int ldab, float[] bb, int offsetbb, int ldbb, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_ssbgvd();

  protected native void ssbgvxK(String jobz, String range, String uplo, int n, int ka, int kb, float[] ab, int offsetab, int ldab, float[] bb, int offsetbb, int ldbb, float[] q, int offsetq, int ldq, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info);
  public native boolean has_ssbgvx();

  protected native void ssbtrdK(String vect, String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float[] d, int offsetd, float[] e, int offsete, float[] q, int offsetq, int ldq, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_ssbtrd();

  protected native void sspconK(String uplo, int n, float[] ap, int offsetap, int[] ipiv, int offsetipiv, float anorm, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sspcon();

  protected native void sspevK(String jobz, String uplo, int n, float[] ap, int offsetap, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sspev();

  protected native void sspevdK(String jobz, String uplo, int n, float[] ap, int offsetap, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_sspevd();

  protected native void sspevxK(String jobz, String range, String uplo, int n, float[] ap, int offsetap, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info);
  public native boolean has_sspevx();

  protected native void sspgstK(int itype, String uplo, int n, float[] ap, int offsetap, float[] bp, int offsetbp, org.netlib.util.intW info);
  public native boolean has_sspgst();

  protected native void sspgvK(int itype, String jobz, String uplo, int n, float[] ap, int offsetap, float[] bp, int offsetbp, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sspgv();

  protected native void sspgvdK(int itype, String jobz, String uplo, int n, float[] ap, int offsetap, float[] bp, int offsetbp, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_sspgvd();

  protected native void sspgvxK(int itype, String jobz, String range, String uplo, int n, float[] ap, int offsetap, float[] bp, int offsetbp, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info);
  public native boolean has_sspgvx();

  protected native void ssprfsK(String uplo, int n, int nrhs, float[] ap, int offsetap, float[] afp, int offsetafp, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_ssprfs();

  protected native void sspsvK(String uplo, int n, int nrhs, float[] ap, int offsetap, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_sspsv();

  protected native void sspsvxK(String fact, String uplo, int n, int nrhs, float[] ap, int offsetap, float[] afp, int offsetafp, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, org.netlib.util.floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sspsvx();

  protected native void ssptrdK(String uplo, int n, float[] ap, int offsetap, float[] d, int offsetd, float[] e, int offsete, float[] tau, int offsettau, org.netlib.util.intW info);
  public native boolean has_ssptrd();

  protected native void ssptrfK(String uplo, int n, float[] ap, int offsetap, int[] ipiv, int offsetipiv, org.netlib.util.intW info);
  public native boolean has_ssptrf();

  protected native void ssptriK(String uplo, int n, float[] ap, int offsetap, int[] ipiv, int offsetipiv, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_ssptri();

  protected native void ssptrsK(String uplo, int n, int nrhs, float[] ap, int offsetap, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_ssptrs();

  protected native void sstebzK(String range, String order, int n, float vl, float vu, int il, int iu, float abstol, float[] d, int offsetd, float[] e, int offsete, org.netlib.util.intW m, org.netlib.util.intW nsplit, float[] w, int offsetw, int[] iblock, int offsetiblock, int[] isplit, int offsetisplit, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_sstebz();

  protected native void sstedcK(String compz, int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_sstedc();

  protected native void sstegrK(String jobz, String range, int n, float[] d, int offsetd, float[] e, int offsete, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_sstegr();

  protected native void ssteinK(int n, float[] d, int offsetd, float[] e, int offsete, int m, float[] w, int offsetw, int[] iblock, int offsetiblock, int[] isplit, int offsetisplit, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info);
  public native boolean has_sstein();

  protected native void sstemrK(String jobz, String range, int n, float[] d, int offsetd, float[] e, int offsete, float vl, float vu, int il, int iu, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, int nzc, int[] isuppz, int offsetisuppz, org.netlib.util.booleanW tryrac, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_sstemr();

  protected native void ssteqrK(String compz, int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, int ldz, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_ssteqr();

  protected native void ssterfK(int n, float[] d, int offsetd, float[] e, int offsete, org.netlib.util.intW info);
  public native boolean has_ssterf();

  protected native void sstevK(String jobz, int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, int ldz, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_sstev();

  protected native void sstevdK(String jobz, int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_sstevd();

  protected native void sstevrK(String jobz, String range, int n, float[] d, int offsetd, float[] e, int offsete, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_sstevr();

  protected native void sstevxK(String jobz, String range, int n, float[] d, int offsetd, float[] e, int offsete, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info);
  public native boolean has_sstevx();

  protected native void ssyconK(String uplo, int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float anorm, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_ssycon();

  protected native void ssyevK(String jobz, String uplo, int n, float[] a, int offseta, int lda, float[] w, int offsetw, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_ssyev();

  protected native void ssyevdK(String jobz, String uplo, int n, float[] a, int offseta, int lda, float[] w, int offsetw, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_ssyevd();

  protected native void ssyevrK(String jobz, String range, String uplo, int n, float[] a, int offseta, int lda, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_ssyevr();

  protected native void ssyevxK(String jobz, String range, String uplo, int n, float[] a, int offseta, int lda, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info);
  public native boolean has_ssyevx();

  protected native void ssygs2K(int itype, String uplo, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_ssygs2();

  protected native void ssygstK(int itype, String uplo, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_ssygst();

  protected native void ssygvK(int itype, String jobz, String uplo, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] w, int offsetw, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_ssygv();

  protected native void ssygvdK(int itype, String jobz, String uplo, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] w, int offsetw, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_ssygvd();

  protected native void ssygvxK(int itype, String jobz, String range, String uplo, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info);
  public native boolean has_ssygvx();

  protected native void ssyrfsK(String uplo, int n, int nrhs, float[] a, int offseta, int lda, float[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_ssyrfs();

  protected native void ssysvK(String uplo, int n, int nrhs, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_ssysv();

  protected native void ssysvxK(String fact, String uplo, int n, int nrhs, float[] a, int offseta, int lda, float[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, org.netlib.util.floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_ssysvx();

  protected native void ssytd2K(String uplo, int n, float[] a, int offseta, int lda, float[] d, int offsetd, float[] e, int offsete, float[] tau, int offsettau, org.netlib.util.intW info);
  public native boolean has_ssytd2();

  protected native void ssytf2K(String uplo, int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, org.netlib.util.intW info);
  public native boolean has_ssytf2();

  protected native void ssytrdK(String uplo, int n, float[] a, int offseta, int lda, float[] d, int offsetd, float[] e, int offsete, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_ssytrd();

  protected native void ssytrfK(String uplo, int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_ssytrf();

  protected native void ssytriK(String uplo, int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_ssytri();

  protected native void ssytrsK(String uplo, int n, int nrhs, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_ssytrs();

  protected native void stbconK(String norm, String uplo, String diag, int n, int kd, float[] ab, int offsetab, int ldab, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_stbcon();

  protected native void stbrfsK(String uplo, String trans, String diag, int n, int kd, int nrhs, float[] ab, int offsetab, int ldab, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_stbrfs();

  protected native void stbtrsK(String uplo, String trans, String diag, int n, int kd, int nrhs, float[] ab, int offsetab, int ldab, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_stbtrs();

  protected native void stgevcK(String side, String howmny, boolean[] select, int offsetselect, int n, float[] s, int offsets, int lds, float[] p, int offsetp, int ldp, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, int mm, org.netlib.util.intW m, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_stgevc();

  protected native void stgex2K(boolean wantq, boolean wantz, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] q, int offsetq, int ldq, float[] z, int offsetz, int ldz, int j1, int n1, int n2, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_stgex2();

  protected native void stgexcK(boolean wantq, boolean wantz, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] q, int offsetq, int ldq, float[] z, int offsetz, int ldz, org.netlib.util.intW ifst, org.netlib.util.intW ilst, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_stgexc();

  protected native void stgsenK(int ijob, boolean wantq, boolean wantz, boolean[] select, int offsetselect, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] q, int offsetq, int ldq, float[] z, int offsetz, int ldz, org.netlib.util.intW m, org.netlib.util.floatW pl, org.netlib.util.floatW pr, float[] dif, int offsetdif, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_stgsen();

  protected native void stgsjaK(String jobu, String jobv, String jobq, int m, int p, int n, int k, int l, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float tola, float tolb, float[] alpha, int offsetalpha, float[] beta, int offsetbeta, float[] u, int offsetu, int ldu, float[] v, int offsetv, int ldv, float[] q, int offsetq, int ldq, float[] work, int offsetwork, org.netlib.util.intW ncycle, org.netlib.util.intW info);
  public native boolean has_stgsja();

  protected native void stgsnaK(String job, String howmny, boolean[] select, int offsetselect, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, float[] s, int offsets, float[] dif, int offsetdif, int mm, org.netlib.util.intW m, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_stgsna();

  protected native void stgsy2K(String trans, int ijob, int m, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] c, int offsetc, int Ldc, float[] d, int offsetd, int ldd, float[] e, int offsete, int lde, float[] f, int offsetf, int ldf, org.netlib.util.floatW scale, org.netlib.util.floatW rdsum, org.netlib.util.floatW rdscal, int[] iwork, int offsetiwork, org.netlib.util.intW pq, org.netlib.util.intW info);
  public native boolean has_stgsy2();

  protected native void stgsylK(String trans, int ijob, int m, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] c, int offsetc, int Ldc, float[] d, int offsetd, int ldd, float[] e, int offsete, int lde, float[] f, int offsetf, int ldf, org.netlib.util.floatW scale, org.netlib.util.floatW dif, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_stgsyl();

  protected native void stpconK(String norm, String uplo, String diag, int n, float[] ap, int offsetap, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_stpcon();

  protected native void stprfsK(String uplo, String trans, String diag, int n, int nrhs, float[] ap, int offsetap, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_stprfs();

  protected native void stptriK(String uplo, String diag, int n, float[] ap, int offsetap, org.netlib.util.intW info);
  public native boolean has_stptri();

  protected native void stptrsK(String uplo, String trans, String diag, int n, int nrhs, float[] ap, int offsetap, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_stptrs();

  protected native void strconK(String norm, String uplo, String diag, int n, float[] a, int offseta, int lda, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_strcon();

  protected native void strevcK(String side, String howmny, boolean[] select, int offsetselect, int n, float[] t, int offsett, int ldt, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, int mm, org.netlib.util.intW m, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_strevc();

  protected native void strexcK(String compq, int n, float[] t, int offsett, int ldt, float[] q, int offsetq, int ldq, org.netlib.util.intW ifst, org.netlib.util.intW ilst, float[] work, int offsetwork, org.netlib.util.intW info);
  public native boolean has_strexc();

  protected native void strrfsK(String uplo, String trans, String diag, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_strrfs();

  protected native void strsenK(String job, String compq, boolean[] select, int offsetselect, int n, float[] t, int offsett, int ldt, float[] q, int offsetq, int ldq, float[] wr, int offsetwr, float[] wi, int offsetwi, org.netlib.util.intW m, org.netlib.util.floatW s, org.netlib.util.floatW sep, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info);
  public native boolean has_strsen();

  protected native void strsnaK(String job, String howmny, boolean[] select, int offsetselect, int n, float[] t, int offsett, int ldt, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, float[] s, int offsets, float[] sep, int offsetsep, int mm, org.netlib.util.intW m, float[] work, int offsetwork, int ldwork, int[] iwork, int offsetiwork, org.netlib.util.intW info);
  public native boolean has_strsna();

  protected native void strsylK(String trana, String tranb, int isgn, int m, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] c, int offsetc, int Ldc, org.netlib.util.floatW scale, org.netlib.util.intW info);
  public native boolean has_strsyl();

  protected native void strti2K(String uplo, String diag, int n, float[] a, int offseta, int lda, org.netlib.util.intW info);
  public native boolean has_strti2();

  protected native void strtriK(String uplo, String diag, int n, float[] a, int offseta, int lda, org.netlib.util.intW info);
  public native boolean has_strtri();

  protected native void strtrsK(String uplo, String trans, String diag, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, org.netlib.util.intW info);
  public native boolean has_strtrs();

  protected native void stzrqfK(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, org.netlib.util.intW info);
  public native boolean has_stzrqf();

  protected native void stzrzfK(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info);
  public native boolean has_stzrzf();

  protected native double dlamchK(String cmach);
  public native boolean has_dlamch();

  protected native void dlamc1K(org.netlib.util.intW beta, org.netlib.util.intW t, org.netlib.util.booleanW rnd, org.netlib.util.booleanW ieee1);
  public native boolean has_dlamc1();

  protected native void dlamc2K(org.netlib.util.intW beta, org.netlib.util.intW t, org.netlib.util.booleanW rnd, org.netlib.util.doubleW eps, org.netlib.util.intW emin, org.netlib.util.doubleW rmin, org.netlib.util.intW emax, org.netlib.util.doubleW rmax);
  public native boolean has_dlamc2();

  protected native double dlamc3K(double a, double b);
  public native boolean has_dlamc3();

  protected native void dlamc4K(org.netlib.util.intW emin, double start, int base);
  public native boolean has_dlamc4();

  protected native void dlamc5K(int beta, int p, int emin, boolean ieee, org.netlib.util.intW emax, org.netlib.util.doubleW rmax);
  public native boolean has_dlamc5();

  protected native double dsecndK();
  public native boolean has_dsecnd();

  protected native boolean lsameK(String ca, String cb);
  public native boolean has_lsame();

  protected native float secondK();
  public native boolean has_second();

  protected native float slamchK(String cmach);
  public native boolean has_slamch();

  protected native void slamc1K(org.netlib.util.intW beta, org.netlib.util.intW t, org.netlib.util.booleanW rnd, org.netlib.util.booleanW ieee1);
  public native boolean has_slamc1();

  protected native void slamc2K(org.netlib.util.intW beta, org.netlib.util.intW t, org.netlib.util.booleanW rnd, org.netlib.util.floatW eps, org.netlib.util.intW emin, org.netlib.util.floatW rmin, org.netlib.util.intW emax, org.netlib.util.floatW rmax);
  public native boolean has_slamc2();

  protected native float slamc3K(float a, float b);
  public native boolean has_slamc3();

  protected native void slamc4K(org.netlib.util.intW emin, float start, int base);
  public native boolean has_slamc4();

  protected native void slamc5K(int beta, int p, int emin, boolean ieee, org.netlib.util.intW emax, org.netlib.util.floatW rmax);
  public native boolean has_slamc5();
}
