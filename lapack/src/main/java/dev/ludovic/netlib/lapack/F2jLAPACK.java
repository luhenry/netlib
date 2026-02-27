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

final class F2jLAPACK extends AbstractLAPACK implements JavaLAPACK {

  private static final F2jLAPACK instance = new F2jLAPACK();

  protected F2jLAPACK() {}

  public static JavaLAPACK getInstance() {
    return instance;
  }

  protected void dbdsdcK(String uplo, String compq, int n, double[] d, int offsetd, double[] e, int offsete, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int ldvt, double[] q, int offsetq, int[] iq, int offsetiq, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dbdsdc.dbdsdc(uplo, compq, n, d, offsetd, e, offsete, u, offsetu, ldu, vt, offsetvt, ldvt, q, offsetq, iq, offsetiq, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dbdsqrK(String uplo, int n, int ncvt, int nru, int ncc, double[] d, int offsetd, double[] e, int offsete, double[] vt, int offsetvt, int ldvt, double[] u, int offsetu, int ldu, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dbdsqr.dbdsqr(uplo, n, ncvt, nru, ncc, d, offsetd, e, offsete, vt, offsetvt, ldvt, u, offsetu, ldu, c, offsetc, Ldc, work, offsetwork, info);
  }

  protected void ddisnaK(String job, int m, int n, double[] d, int offsetd, double[] sep, int offsetsep, org.netlib.util.intW info) {
    org.netlib.lapack.Ddisna.ddisna(job, m, n, d, offsetd, sep, offsetsep, info);
  }

  protected void dgbbrdK(String vect, int m, int n, int ncc, int kl, int ku, double[] ab, int offsetab, int ldab, double[] d, int offsetd, double[] e, int offsete, double[] q, int offsetq, int ldq, double[] pt, int offsetpt, int ldpt, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgbbrd.dgbbrd(vect, m, n, ncc, kl, ku, ab, offsetab, ldab, d, offsetd, e, offsete, q, offsetq, ldq, pt, offsetpt, ldpt, c, offsetc, Ldc, work, offsetwork, info);
  }

  protected void dgbconK(String norm, int n, int kl, int ku, double[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, double anorm, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgbcon.dgbcon(norm, n, kl, ku, ab, offsetab, ldab, ipiv, offsetipiv, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dgbequK(int m, int n, int kl, int ku, double[] ab, int offsetab, int ldab, double[] r, int offsetr, double[] c, int offsetc, org.netlib.util.doubleW rowcnd, org.netlib.util.doubleW colcnd, org.netlib.util.doubleW amax, org.netlib.util.intW info) {
    org.netlib.lapack.Dgbequ.dgbequ(m, n, kl, ku, ab, offsetab, ldab, r, offsetr, c, offsetc, rowcnd, colcnd, amax, info);
  }

  protected void dgbrfsK(String trans, int n, int kl, int ku, int nrhs, double[] ab, int offsetab, int ldab, double[] afb, int offsetafb, int ldafb, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgbrfs.dgbrfs(trans, n, kl, ku, nrhs, ab, offsetab, ldab, afb, offsetafb, ldafb, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dgbsvK(int n, int kl, int ku, int nrhs, double[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dgbsv.dgbsv(n, kl, ku, nrhs, ab, offsetab, ldab, ipiv, offsetipiv, b, offsetb, ldb, info);
  }

  protected void dgbsvxK(String fact, String trans, int n, int kl, int ku, int nrhs, double[] ab, int offsetab, int ldab, double[] afb, int offsetafb, int ldafb, int[] ipiv, int offsetipiv, org.netlib.util.StringW equed, double[] r, int offsetr, double[] c, int offsetc, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgbsvx.dgbsvx(fact, trans, n, kl, ku, nrhs, ab, offsetab, ldab, afb, offsetafb, ldafb, ipiv, offsetipiv, equed, r, offsetr, c, offsetc, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dgbtf2K(int m, int n, int kl, int ku, double[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, org.netlib.util.intW info) {
    org.netlib.lapack.Dgbtf2.dgbtf2(m, n, kl, ku, ab, offsetab, ldab, ipiv, offsetipiv, info);
  }

  protected void dgbtrfK(int m, int n, int kl, int ku, double[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, org.netlib.util.intW info) {
    org.netlib.lapack.Dgbtrf.dgbtrf(m, n, kl, ku, ab, offsetab, ldab, ipiv, offsetipiv, info);
  }

  protected void dgbtrsK(String trans, int n, int kl, int ku, int nrhs, double[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dgbtrs.dgbtrs(trans, n, kl, ku, nrhs, ab, offsetab, ldab, ipiv, offsetipiv, b, offsetb, ldb, info);
  }

  protected void dgebakK(String job, String side, int n, int ilo, int ihi, double[] scale, int offsetscale, int m, double[] v, int offsetv, int ldv, org.netlib.util.intW info) {
    org.netlib.lapack.Dgebak.dgebak(job, side, n, ilo, ihi, scale, offsetscale, m, v, offsetv, ldv, info);
  }

  protected void dgebalK(String job, int n, double[] a, int offseta, int lda, org.netlib.util.intW ilo, org.netlib.util.intW ihi, double[] scale, int offsetscale, org.netlib.util.intW info) {
    org.netlib.lapack.Dgebal.dgebal(job, n, a, offseta, lda, ilo, ihi, scale, offsetscale, info);
  }

  protected void dgebd2K(int m, int n, double[] a, int offseta, int lda, double[] d, int offsetd, double[] e, int offsete, double[] tauq, int offsettauq, double[] taup, int offsettaup, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgebd2.dgebd2(m, n, a, offseta, lda, d, offsetd, e, offsete, tauq, offsettauq, taup, offsettaup, work, offsetwork, info);
  }

  protected void dgebrdK(int m, int n, double[] a, int offseta, int lda, double[] d, int offsetd, double[] e, int offsete, double[] tauq, int offsettauq, double[] taup, int offsettaup, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgebrd.dgebrd(m, n, a, offseta, lda, d, offsetd, e, offsete, tauq, offsettauq, taup, offsettaup, work, offsetwork, lwork, info);
  }

  protected void dgeconK(String norm, int n, double[] a, int offseta, int lda, double anorm, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgecon.dgecon(norm, n, a, offseta, lda, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dgeequK(int m, int n, double[] a, int offseta, int lda, double[] r, int offsetr, double[] c, int offsetc, org.netlib.util.doubleW rowcnd, org.netlib.util.doubleW colcnd, org.netlib.util.doubleW amax, org.netlib.util.intW info) {
    org.netlib.lapack.Dgeequ.dgeequ(m, n, a, offseta, lda, r, offsetr, c, offsetc, rowcnd, colcnd, amax, info);
  }

  protected void dgeesK(String jobvs, String sort, java.lang.Object select, int n, double[] a, int offseta, int lda, org.netlib.util.intW sdim, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] vs, int offsetvs, int ldvs, double[] work, int offsetwork, int lwork, boolean[] bwork, int offsetbwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgees.dgees(jobvs, sort, select, n, a, offseta, lda, sdim, wr, offsetwr, wi, offsetwi, vs, offsetvs, ldvs, work, offsetwork, lwork, bwork, offsetbwork, info);
  }

  protected void dgeesxK(String jobvs, String sort, java.lang.Object select, String sense, int n, double[] a, int offseta, int lda, org.netlib.util.intW sdim, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] vs, int offsetvs, int ldvs, org.netlib.util.doubleW rconde, org.netlib.util.doubleW rcondv, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, boolean[] bwork, int offsetbwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgeesx.dgeesx(jobvs, sort, select, sense, n, a, offseta, lda, sdim, wr, offsetwr, wi, offsetwi, vs, offsetvs, ldvs, rconde, rcondv, work, offsetwork, lwork, iwork, offsetiwork, liwork, bwork, offsetbwork, info);
  }

  protected void dgeevK(String jobvl, String jobvr, int n, double[] a, int offseta, int lda, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgeev.dgeev(jobvl, jobvr, n, a, offseta, lda, wr, offsetwr, wi, offsetwi, vl, offsetvl, ldvl, vr, offsetvr, ldvr, work, offsetwork, lwork, info);
  }

  protected void dgeevxK(String balanc, String jobvl, String jobvr, String sense, int n, double[] a, int offseta, int lda, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, org.netlib.util.intW ilo, org.netlib.util.intW ihi, double[] scale, int offsetscale, org.netlib.util.doubleW abnrm, double[] rconde, int offsetrconde, double[] rcondv, int offsetrcondv, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgeevx.dgeevx(balanc, jobvl, jobvr, sense, n, a, offseta, lda, wr, offsetwr, wi, offsetwi, vl, offsetvl, ldvl, vr, offsetvr, ldvr, ilo, ihi, scale, offsetscale, abnrm, rconde, offsetrconde, rcondv, offsetrcondv, work, offsetwork, lwork, iwork, offsetiwork, info);
  }

  protected void dgegsK(String jobvsl, String jobvsr, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] vsl, int offsetvsl, int ldvsl, double[] vsr, int offsetvsr, int ldvsr, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgegs.dgegs(jobvsl, jobvsr, n, a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vsl, offsetvsl, ldvsl, vsr, offsetvsr, ldvsr, work, offsetwork, lwork, info);
  }

  protected void dgegvK(String jobvl, String jobvr, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgegv.dgegv(jobvl, jobvr, n, a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vl, offsetvl, ldvl, vr, offsetvr, ldvr, work, offsetwork, lwork, info);
  }

  protected void dgehd2K(int n, int ilo, int ihi, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgehd2.dgehd2(n, ilo, ihi, a, offseta, lda, tau, offsettau, work, offsetwork, info);
  }

  protected void dgehrdK(int n, int ilo, int ihi, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgehrd.dgehrd(n, ilo, ihi, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void dgelq2K(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgelq2.dgelq2(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, info);
  }

  protected void dgelqfK(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgelqf.dgelqf(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void dgelsK(String trans, int m, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgels.dgels(trans, m, n, nrhs, a, offseta, lda, b, offsetb, ldb, work, offsetwork, lwork, info);
  }

  protected void dgelsdK(int m, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] s, int offsets, double rcond, org.netlib.util.intW rank, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgelsd.dgelsd(m, n, nrhs, a, offseta, lda, b, offsetb, ldb, s, offsets, rcond, rank, work, offsetwork, lwork, iwork, offsetiwork, info);
  }

  protected void dgelssK(int m, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] s, int offsets, double rcond, org.netlib.util.intW rank, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgelss.dgelss(m, n, nrhs, a, offseta, lda, b, offsetb, ldb, s, offsets, rcond, rank, work, offsetwork, lwork, info);
  }

  protected void dgelsxK(int m, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, int[] jpvt, int offsetjpvt, double rcond, org.netlib.util.intW rank, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgelsx.dgelsx(m, n, nrhs, a, offseta, lda, b, offsetb, ldb, jpvt, offsetjpvt, rcond, rank, work, offsetwork, info);
  }

  protected void dgelsyK(int m, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, int[] jpvt, int offsetjpvt, double rcond, org.netlib.util.intW rank, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgelsy.dgelsy(m, n, nrhs, a, offseta, lda, b, offsetb, ldb, jpvt, offsetjpvt, rcond, rank, work, offsetwork, lwork, info);
  }

  protected void dgeql2K(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgeql2.dgeql2(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, info);
  }

  protected void dgeqlfK(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgeqlf.dgeqlf(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void dgeqp3K(int m, int n, double[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgeqp3.dgeqp3(m, n, a, offseta, lda, jpvt, offsetjpvt, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void dgeqpfK(int m, int n, double[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgeqpf.dgeqpf(m, n, a, offseta, lda, jpvt, offsetjpvt, tau, offsettau, work, offsetwork, info);
  }

  protected void dgeqr2K(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgeqr2.dgeqr2(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, info);
  }

  protected void dgeqrfK(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgeqrf.dgeqrf(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void dgerfsK(String trans, int n, int nrhs, double[] a, int offseta, int lda, double[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgerfs.dgerfs(trans, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dgerq2K(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgerq2.dgerq2(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, info);
  }

  protected void dgerqfK(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgerqf.dgerqf(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void dgesc2K(int n, double[] a, int offseta, int lda, double[] rhs, int offsetrhs, int[] ipiv, int offsetipiv, int[] jpiv, int offsetjpiv, org.netlib.util.doubleW scale) {
    org.netlib.lapack.Dgesc2.dgesc2(n, a, offseta, lda, rhs, offsetrhs, ipiv, offsetipiv, jpiv, offsetjpiv, scale);
  }

  protected void dgesddK(String jobz, int m, int n, double[] a, int offseta, int lda, double[] s, int offsets, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int ldvt, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgesdd.dgesdd(jobz, m, n, a, offseta, lda, s, offsets, u, offsetu, ldu, vt, offsetvt, ldvt, work, offsetwork, lwork, iwork, offsetiwork, info);
  }

  protected void dgesvK(int n, int nrhs, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dgesv.dgesv(n, nrhs, a, offseta, lda, ipiv, offsetipiv, b, offsetb, ldb, info);
  }

  protected void dgesvdK(String jobu, String jobvt, int m, int n, double[] a, int offseta, int lda, double[] s, int offsets, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int ldvt, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgesvd.dgesvd(jobu, jobvt, m, n, a, offseta, lda, s, offsets, u, offsetu, ldu, vt, offsetvt, ldvt, work, offsetwork, lwork, info);
  }

  protected void dgesvxK(String fact, String trans, int n, int nrhs, double[] a, int offseta, int lda, double[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, org.netlib.util.StringW equed, double[] r, int offsetr, double[] c, int offsetc, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgesvx.dgesvx(fact, trans, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, ipiv, offsetipiv, equed, r, offsetr, c, offsetc, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dgetc2K(int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, int[] jpiv, int offsetjpiv, org.netlib.util.intW info) {
    org.netlib.lapack.Dgetc2.dgetc2(n, a, offseta, lda, ipiv, offsetipiv, jpiv, offsetjpiv, info);
  }

  protected void dgetf2K(int m, int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, org.netlib.util.intW info) {
    org.netlib.lapack.Dgetf2.dgetf2(m, n, a, offseta, lda, ipiv, offsetipiv, info);
  }

  protected void dgetrfK(int m, int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, org.netlib.util.intW info) {
    org.netlib.lapack.Dgetrf.dgetrf(m, n, a, offseta, lda, ipiv, offsetipiv, info);
  }

  protected void dgetriK(int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgetri.dgetri(n, a, offseta, lda, ipiv, offsetipiv, work, offsetwork, lwork, info);
  }

  protected void dgetrsK(String trans, int n, int nrhs, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dgetrs.dgetrs(trans, n, nrhs, a, offseta, lda, ipiv, offsetipiv, b, offsetb, ldb, info);
  }

  protected void dggbakK(String job, String side, int n, int ilo, int ihi, double[] lscale, int offsetlscale, double[] rscale, int offsetrscale, int m, double[] v, int offsetv, int ldv, org.netlib.util.intW info) {
    org.netlib.lapack.Dggbak.dggbak(job, side, n, ilo, ihi, lscale, offsetlscale, rscale, offsetrscale, m, v, offsetv, ldv, info);
  }

  protected void dggbalK(String job, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, org.netlib.util.intW ilo, org.netlib.util.intW ihi, double[] lscale, int offsetlscale, double[] rscale, int offsetrscale, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dggbal.dggbal(job, n, a, offseta, lda, b, offsetb, ldb, ilo, ihi, lscale, offsetlscale, rscale, offsetrscale, work, offsetwork, info);
  }

  protected void dggesK(String jobvsl, String jobvsr, String sort, java.lang.Object selctg, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, org.netlib.util.intW sdim, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] vsl, int offsetvsl, int ldvsl, double[] vsr, int offsetvsr, int ldvsr, double[] work, int offsetwork, int lwork, boolean[] bwork, int offsetbwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgges.dgges(jobvsl, jobvsr, sort, selctg, n, a, offseta, lda, b, offsetb, ldb, sdim, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vsl, offsetvsl, ldvsl, vsr, offsetvsr, ldvsr, work, offsetwork, lwork, bwork, offsetbwork, info);
  }

  protected void dggesxK(String jobvsl, String jobvsr, String sort, java.lang.Object selctg, String sense, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, org.netlib.util.intW sdim, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] vsl, int offsetvsl, int ldvsl, double[] vsr, int offsetvsr, int ldvsr, double[] rconde, int offsetrconde, double[] rcondv, int offsetrcondv, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, boolean[] bwork, int offsetbwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dggesx.dggesx(jobvsl, jobvsr, sort, selctg, sense, n, a, offseta, lda, b, offsetb, ldb, sdim, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vsl, offsetvsl, ldvsl, vsr, offsetvsr, ldvsr, rconde, offsetrconde, rcondv, offsetrcondv, work, offsetwork, lwork, iwork, offsetiwork, liwork, bwork, offsetbwork, info);
  }

  protected void dggevK(String jobvl, String jobvr, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dggev.dggev(jobvl, jobvr, n, a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vl, offsetvl, ldvl, vr, offsetvr, ldvr, work, offsetwork, lwork, info);
  }

  protected void dggevxK(String balanc, String jobvl, String jobvr, String sense, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, org.netlib.util.intW ilo, org.netlib.util.intW ihi, double[] lscale, int offsetlscale, double[] rscale, int offsetrscale, org.netlib.util.doubleW abnrm, org.netlib.util.doubleW bbnrm, double[] rconde, int offsetrconde, double[] rcondv, int offsetrcondv, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, boolean[] bwork, int offsetbwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dggevx.dggevx(balanc, jobvl, jobvr, sense, n, a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vl, offsetvl, ldvl, vr, offsetvr, ldvr, ilo, ihi, lscale, offsetlscale, rscale, offsetrscale, abnrm, bbnrm, rconde, offsetrconde, rcondv, offsetrcondv, work, offsetwork, lwork, iwork, offsetiwork, bwork, offsetbwork, info);
  }

  protected void dggglmK(int n, int m, int p, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] d, int offsetd, double[] x, int offsetx, double[] y, int offsety, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dggglm.dggglm(n, m, p, a, offseta, lda, b, offsetb, ldb, d, offsetd, x, offsetx, y, offsety, work, offsetwork, lwork, info);
  }

  protected void dgghrdK(String compq, String compz, int n, int ilo, int ihi, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] q, int offsetq, int ldq, double[] z, int offsetz, int ldz, org.netlib.util.intW info) {
    org.netlib.lapack.Dgghrd.dgghrd(compq, compz, n, ilo, ihi, a, offseta, lda, b, offsetb, ldb, q, offsetq, ldq, z, offsetz, ldz, info);
  }

  protected void dgglseK(int m, int n, int p, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] c, int offsetc, double[] d, int offsetd, double[] x, int offsetx, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgglse.dgglse(m, n, p, a, offseta, lda, b, offsetb, ldb, c, offsetc, d, offsetd, x, offsetx, work, offsetwork, lwork, info);
  }

  protected void dggqrfK(int n, int m, int p, double[] a, int offseta, int lda, double[] taua, int offsettaua, double[] b, int offsetb, int ldb, double[] taub, int offsettaub, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dggqrf.dggqrf(n, m, p, a, offseta, lda, taua, offsettaua, b, offsetb, ldb, taub, offsettaub, work, offsetwork, lwork, info);
  }

  protected void dggrqfK(int m, int p, int n, double[] a, int offseta, int lda, double[] taua, int offsettaua, double[] b, int offsetb, int ldb, double[] taub, int offsettaub, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dggrqf.dggrqf(m, p, n, a, offseta, lda, taua, offsettaua, b, offsetb, ldb, taub, offsettaub, work, offsetwork, lwork, info);
  }

  protected void dggsvdK(String jobu, String jobv, String jobq, int m, int n, int p, org.netlib.util.intW k, org.netlib.util.intW l, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alpha, int offsetalpha, double[] beta, int offsetbeta, double[] u, int offsetu, int ldu, double[] v, int offsetv, int ldv, double[] q, int offsetq, int ldq, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dggsvd.dggsvd(jobu, jobv, jobq, m, n, p, k, l, a, offseta, lda, b, offsetb, ldb, alpha, offsetalpha, beta, offsetbeta, u, offsetu, ldu, v, offsetv, ldv, q, offsetq, ldq, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dggsvpK(String jobu, String jobv, String jobq, int m, int p, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double tola, double tolb, org.netlib.util.intW k, org.netlib.util.intW l, double[] u, int offsetu, int ldu, double[] v, int offsetv, int ldv, double[] q, int offsetq, int ldq, int[] iwork, int offsetiwork, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dggsvp.dggsvp(jobu, jobv, jobq, m, p, n, a, offseta, lda, b, offsetb, ldb, tola, tolb, k, l, u, offsetu, ldu, v, offsetv, ldv, q, offsetq, ldq, iwork, offsetiwork, tau, offsettau, work, offsetwork, info);
  }

  protected void dgtconK(String norm, int n, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] du2, int offsetdu2, int[] ipiv, int offsetipiv, double anorm, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgtcon.dgtcon(norm, n, dl, offsetdl, d, offsetd, du, offsetdu, du2, offsetdu2, ipiv, offsetipiv, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dgtrfsK(String trans, int n, int nrhs, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] dlf, int offsetdlf, double[] df, int offsetdf, double[] duf, int offsetduf, double[] du2, int offsetdu2, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgtrfs.dgtrfs(trans, n, nrhs, dl, offsetdl, d, offsetd, du, offsetdu, dlf, offsetdlf, df, offsetdf, duf, offsetduf, du2, offsetdu2, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dgtsvK(int n, int nrhs, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dgtsv.dgtsv(n, nrhs, dl, offsetdl, d, offsetd, du, offsetdu, b, offsetb, ldb, info);
  }

  protected void dgtsvxK(String fact, String trans, int n, int nrhs, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] dlf, int offsetdlf, double[] df, int offsetdf, double[] duf, int offsetduf, double[] du2, int offsetdu2, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dgtsvx.dgtsvx(fact, trans, n, nrhs, dl, offsetdl, d, offsetd, du, offsetdu, dlf, offsetdlf, df, offsetdf, duf, offsetduf, du2, offsetdu2, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dgttrfK(int n, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] du2, int offsetdu2, int[] ipiv, int offsetipiv, org.netlib.util.intW info) {
    org.netlib.lapack.Dgttrf.dgttrf(n, dl, offsetdl, d, offsetd, du, offsetdu, du2, offsetdu2, ipiv, offsetipiv, info);
  }

  protected void dgttrsK(String trans, int n, int nrhs, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] du2, int offsetdu2, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dgttrs.dgttrs(trans, n, nrhs, dl, offsetdl, d, offsetd, du, offsetdu, du2, offsetdu2, ipiv, offsetipiv, b, offsetb, ldb, info);
  }

  protected void dgtts2K(int itrans, int n, int nrhs, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] du2, int offsetdu2, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb) {
    org.netlib.lapack.Dgtts2.dgtts2(itrans, n, nrhs, dl, offsetdl, d, offsetd, du, offsetdu, du2, offsetdu2, ipiv, offsetipiv, b, offsetb, ldb);
  }

  protected void dhgeqzK(String job, String compq, String compz, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] t, int offsett, int ldt, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] q, int offsetq, int ldq, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dhgeqz.dhgeqz(job, compq, compz, n, ilo, ihi, h, offseth, ldh, t, offsett, ldt, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, q, offsetq, ldq, z, offsetz, ldz, work, offsetwork, lwork, info);
  }

  protected void dhseinK(String side, String eigsrc, String initv, boolean[] select, int offsetselect, int n, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, int mm, org.netlib.util.intW m, double[] work, int offsetwork, int[] ifaill, int offsetifaill, int[] ifailr, int offsetifailr, org.netlib.util.intW info) {
    org.netlib.lapack.Dhsein.dhsein(side, eigsrc, initv, select, offsetselect, n, h, offseth, ldh, wr, offsetwr, wi, offsetwi, vl, offsetvl, ldvl, vr, offsetvr, ldvr, mm, m, work, offsetwork, ifaill, offsetifaill, ifailr, offsetifailr, info);
  }

  protected void dhseqrK(String job, String compz, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dhseqr.dhseqr(job, compz, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, z, offsetz, ldz, work, offsetwork, lwork, info);
  }

  protected boolean disnanK(double din) {
    return org.netlib.lapack.Disnan.disnan(din);
  }

  protected void dlabadK(org.netlib.util.doubleW small, org.netlib.util.doubleW large) {
    org.netlib.lapack.Dlabad.dlabad(small, large);
  }

  protected void dlabrdK(int m, int n, int nb, double[] a, int offseta, int lda, double[] d, int offsetd, double[] e, int offsete, double[] tauq, int offsettauq, double[] taup, int offsettaup, double[] x, int offsetx, int ldx, double[] y, int offsety, int ldy) {
    org.netlib.lapack.Dlabrd.dlabrd(m, n, nb, a, offseta, lda, d, offsetd, e, offsete, tauq, offsettauq, taup, offsettaup, x, offsetx, ldx, y, offsety, ldy);
  }

  protected void dlacn2K(int n, double[] v, int offsetv, double[] x, int offsetx, int[] isgn, int offsetisgn, org.netlib.util.doubleW est, org.netlib.util.intW kase, int[] isave, int offsetisave) {
    org.netlib.lapack.Dlacn2.dlacn2(n, v, offsetv, x, offsetx, isgn, offsetisgn, est, kase, isave, offsetisave);
  }

  protected void dlaconK(int n, double[] v, int offsetv, double[] x, int offsetx, int[] isgn, int offsetisgn, org.netlib.util.doubleW est, org.netlib.util.intW kase) {
    org.netlib.lapack.Dlacon.dlacon(n, v, offsetv, x, offsetx, isgn, offsetisgn, est, kase);
  }

  protected void dlacpyK(String uplo, int m, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
    org.netlib.lapack.Dlacpy.dlacpy(uplo, m, n, a, offseta, lda, b, offsetb, ldb);
  }

  protected void dladivK(double a, double b, double c, double d, org.netlib.util.doubleW p, org.netlib.util.doubleW q) {
    org.netlib.lapack.Dladiv.dladiv(a, b, c, d, p, q);
  }

  protected void dlae2K(double a, double b, double c, org.netlib.util.doubleW rt1, org.netlib.util.doubleW rt2) {
    org.netlib.lapack.Dlae2.dlae2(a, b, c, rt1, rt2);
  }

  protected void dlaebzK(int ijob, int nitmax, int n, int mmax, int minp, int nbmin, double abstol, double reltol, double pivmin, double[] d, int offsetd, double[] e, int offsete, double[] e2, int offsete2, int[] nval, int offsetnval, double[] ab, int offsetab, double[] c, int offsetc, org.netlib.util.intW mout, int[] nab, int offsetnab, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlaebz.dlaebz(ijob, nitmax, n, mmax, minp, nbmin, abstol, reltol, pivmin, d, offsetd, e, offsete, e2, offsete2, nval, offsetnval, ab, offsetab, c, offsetc, mout, nab, offsetnab, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dlaed0K(int icompq, int qsiz, int n, double[] d, int offsetd, double[] e, int offsete, double[] q, int offsetq, int ldq, double[] qstore, int offsetqstore, int ldqs, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlaed0.dlaed0(icompq, qsiz, n, d, offsetd, e, offsete, q, offsetq, ldq, qstore, offsetqstore, ldqs, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dlaed1K(int n, double[] d, int offsetd, double[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, org.netlib.util.doubleW rho, int cutpnt, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlaed1.dlaed1(n, d, offsetd, q, offsetq, ldq, indxq, offsetindxq, rho, cutpnt, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dlaed2K(org.netlib.util.intW k, int n, int n1, double[] d, int offsetd, double[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, org.netlib.util.doubleW rho, double[] z, int offsetz, double[] dlamda, int offsetdlamda, double[] w, int offsetw, double[] q2, int offsetq2, int[] indx, int offsetindx, int[] indxc, int offsetindxc, int[] indxp, int offsetindxp, int[] coltyp, int offsetcoltyp, org.netlib.util.intW info) {
    org.netlib.lapack.Dlaed2.dlaed2(k, n, n1, d, offsetd, q, offsetq, ldq, indxq, offsetindxq, rho, z, offsetz, dlamda, offsetdlamda, w, offsetw, q2, offsetq2, indx, offsetindx, indxc, offsetindxc, indxp, offsetindxp, coltyp, offsetcoltyp, info);
  }

  protected void dlaed3K(int k, int n, int n1, double[] d, int offsetd, double[] q, int offsetq, int ldq, double rho, double[] dlamda, int offsetdlamda, double[] q2, int offsetq2, int[] indx, int offsetindx, int[] ctot, int offsetctot, double[] w, int offsetw, double[] s, int offsets, org.netlib.util.intW info) {
    org.netlib.lapack.Dlaed3.dlaed3(k, n, n1, d, offsetd, q, offsetq, ldq, rho, dlamda, offsetdlamda, q2, offsetq2, indx, offsetindx, ctot, offsetctot, w, offsetw, s, offsets, info);
  }

  protected void dlaed4K(int n, int i, double[] d, int offsetd, double[] z, int offsetz, double[] delta, int offsetdelta, double rho, org.netlib.util.doubleW dlam, org.netlib.util.intW info) {
    org.netlib.lapack.Dlaed4.dlaed4(n, i, d, offsetd, z, offsetz, delta, offsetdelta, rho, dlam, info);
  }

  protected void dlaed5K(int i, double[] d, int offsetd, double[] z, int offsetz, double[] delta, int offsetdelta, double rho, org.netlib.util.doubleW dlam) {
    org.netlib.lapack.Dlaed5.dlaed5(i, d, offsetd, z, offsetz, delta, offsetdelta, rho, dlam);
  }

  protected void dlaed6K(int kniter, boolean orgati, double rho, double[] d, int offsetd, double[] z, int offsetz, double finit, org.netlib.util.doubleW tau, org.netlib.util.intW info) {
    org.netlib.lapack.Dlaed6.dlaed6(kniter, orgati, rho, d, offsetd, z, offsetz, finit, tau, info);
  }

  protected void dlaed7K(int icompq, int n, int qsiz, int tlvls, int curlvl, int curpbm, double[] d, int offsetd, double[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, org.netlib.util.doubleW rho, int cutpnt, double[] qstore, int offsetqstore, int[] qptr, int offsetqptr, int[] prmptr, int offsetprmptr, int[] perm, int offsetperm, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, double[] givnum, int offsetgivnum, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlaed7.dlaed7(icompq, n, qsiz, tlvls, curlvl, curpbm, d, offsetd, q, offsetq, ldq, indxq, offsetindxq, rho, cutpnt, qstore, offsetqstore, qptr, offsetqptr, prmptr, offsetprmptr, perm, offsetperm, givptr, offsetgivptr, givcol, offsetgivcol, givnum, offsetgivnum, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dlaed8K(int icompq, org.netlib.util.intW k, int n, int qsiz, double[] d, int offsetd, double[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, org.netlib.util.doubleW rho, int cutpnt, double[] z, int offsetz, double[] dlamda, int offsetdlamda, double[] q2, int offsetq2, int ldq2, double[] w, int offsetw, int[] perm, int offsetperm, org.netlib.util.intW givptr, int[] givcol, int offsetgivcol, double[] givnum, int offsetgivnum, int[] indxp, int offsetindxp, int[] indx, int offsetindx, org.netlib.util.intW info) {
    org.netlib.lapack.Dlaed8.dlaed8(icompq, k, n, qsiz, d, offsetd, q, offsetq, ldq, indxq, offsetindxq, rho, cutpnt, z, offsetz, dlamda, offsetdlamda, q2, offsetq2, ldq2, w, offsetw, perm, offsetperm, givptr, givcol, offsetgivcol, givnum, offsetgivnum, indxp, offsetindxp, indx, offsetindx, info);
  }

  protected void dlaed9K(int k, int kstart, int kstop, int n, double[] d, int offsetd, double[] q, int offsetq, int ldq, double rho, double[] dlamda, int offsetdlamda, double[] w, int offsetw, double[] s, int offsets, int lds, org.netlib.util.intW info) {
    org.netlib.lapack.Dlaed9.dlaed9(k, kstart, kstop, n, d, offsetd, q, offsetq, ldq, rho, dlamda, offsetdlamda, w, offsetw, s, offsets, lds, info);
  }

  protected void dlaedaK(int n, int tlvls, int curlvl, int curpbm, int[] prmptr, int offsetprmptr, int[] perm, int offsetperm, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, double[] givnum, int offsetgivnum, double[] q, int offsetq, int[] qptr, int offsetqptr, double[] z, int offsetz, double[] ztemp, int offsetztemp, org.netlib.util.intW info) {
    org.netlib.lapack.Dlaeda.dlaeda(n, tlvls, curlvl, curpbm, prmptr, offsetprmptr, perm, offsetperm, givptr, offsetgivptr, givcol, offsetgivcol, givnum, offsetgivnum, q, offsetq, qptr, offsetqptr, z, offsetz, ztemp, offsetztemp, info);
  }

  protected void dlaeinK(boolean rightv, boolean noinit, int n, double[] h, int offseth, int ldh, double wr, double wi, double[] vr, int offsetvr, double[] vi, int offsetvi, double[] b, int offsetb, int ldb, double[] work, int offsetwork, double eps3, double smlnum, double bignum, org.netlib.util.intW info) {
    org.netlib.lapack.Dlaein.dlaein(rightv, noinit, n, h, offseth, ldh, wr, wi, vr, offsetvr, vi, offsetvi, b, offsetb, ldb, work, offsetwork, eps3, smlnum, bignum, info);
  }

  protected void dlaev2K(double a, double b, double c, org.netlib.util.doubleW rt1, org.netlib.util.doubleW rt2, org.netlib.util.doubleW cs1, org.netlib.util.doubleW sn1) {
    org.netlib.lapack.Dlaev2.dlaev2(a, b, c, rt1, rt2, cs1, sn1);
  }

  protected void dlaexcK(boolean wantq, int n, double[] t, int offsett, int ldt, double[] q, int offsetq, int ldq, int j1, int n1, int n2, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlaexc.dlaexc(wantq, n, t, offsett, ldt, q, offsetq, ldq, j1, n1, n2, work, offsetwork, info);
  }

  protected void dlag2K(double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double safmin, org.netlib.util.doubleW scale1, org.netlib.util.doubleW scale2, org.netlib.util.doubleW wr1, org.netlib.util.doubleW wr2, org.netlib.util.doubleW wi) {
    org.netlib.lapack.Dlag2.dlag2(a, offseta, lda, b, offsetb, ldb, safmin, scale1, scale2, wr1, wr2, wi);
  }

  protected void dlag2sK(int m, int n, double[] a, int offseta, int lda, float[] sa, int offsetsa, int ldsa, org.netlib.util.intW info) {
    org.netlib.lapack.Dlag2s.dlag2s(m, n, a, offseta, lda, sa, offsetsa, ldsa, info);
  }

  protected void dlags2K(boolean upper, double a1, double a2, double a3, double b1, double b2, double b3, org.netlib.util.doubleW csu, org.netlib.util.doubleW snu, org.netlib.util.doubleW csv, org.netlib.util.doubleW snv, org.netlib.util.doubleW csq, org.netlib.util.doubleW snq) {
    org.netlib.lapack.Dlags2.dlags2(upper, a1, a2, a3, b1, b2, b3, csu, snu, csv, snv, csq, snq);
  }

  protected void dlagtfK(int n, double[] a, int offseta, double lambda, double[] b, int offsetb, double[] c, int offsetc, double tol, double[] d, int offsetd, int[] in, int offsetin, org.netlib.util.intW info) {
    org.netlib.lapack.Dlagtf.dlagtf(n, a, offseta, lambda, b, offsetb, c, offsetc, tol, d, offsetd, in, offsetin, info);
  }

  protected void dlagtmK(String trans, int n, int nrhs, double alpha, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] x, int offsetx, int ldx, double beta, double[] b, int offsetb, int ldb) {
    org.netlib.lapack.Dlagtm.dlagtm(trans, n, nrhs, alpha, dl, offsetdl, d, offsetd, du, offsetdu, x, offsetx, ldx, beta, b, offsetb, ldb);
  }

  protected void dlagtsK(int job, int n, double[] a, int offseta, double[] b, int offsetb, double[] c, int offsetc, double[] d, int offsetd, int[] in, int offsetin, double[] y, int offsety, org.netlib.util.doubleW tol, org.netlib.util.intW info) {
    org.netlib.lapack.Dlagts.dlagts(job, n, a, offseta, b, offsetb, c, offsetc, d, offsetd, in, offsetin, y, offsety, tol, info);
  }

  protected void dlagv2K(double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, org.netlib.util.doubleW csl, org.netlib.util.doubleW snl, org.netlib.util.doubleW csr, org.netlib.util.doubleW snr) {
    org.netlib.lapack.Dlagv2.dlagv2(a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, csl, snl, csr, snr);
  }

  protected void dlahqrK(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, int iloz, int ihiz, double[] z, int offsetz, int ldz, org.netlib.util.intW info) {
    org.netlib.lapack.Dlahqr.dlahqr(wantt, wantz, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, iloz, ihiz, z, offsetz, ldz, info);
  }

  protected void dlahr2K(int n, int k, int nb, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] t, int offsett, int ldt, double[] y, int offsety, int ldy) {
    org.netlib.lapack.Dlahr2.dlahr2(n, k, nb, a, offseta, lda, tau, offsettau, t, offsett, ldt, y, offsety, ldy);
  }

  protected void dlahrdK(int n, int k, int nb, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] t, int offsett, int ldt, double[] y, int offsety, int ldy) {
    org.netlib.lapack.Dlahrd.dlahrd(n, k, nb, a, offseta, lda, tau, offsettau, t, offsett, ldt, y, offsety, ldy);
  }

  protected void dlaic1K(int job, int j, double[] x, int offsetx, double sest, double[] w, int offsetw, double gamma, org.netlib.util.doubleW sestpr, org.netlib.util.doubleW s, org.netlib.util.doubleW c) {
    org.netlib.lapack.Dlaic1.dlaic1(job, j, x, offsetx, sest, w, offsetw, gamma, sestpr, s, c);
  }

  protected boolean dlaisnanK(double din1, double din2) {
    return org.netlib.lapack.Dlaisnan.dlaisnan(din1, din2);
  }

  protected void dlaln2K(boolean ltrans, int na, int nw, double smin, double ca, double[] a, int offseta, int lda, double d1, double d2, double[] b, int offsetb, int ldb, double wr, double wi, double[] x, int offsetx, int ldx, org.netlib.util.doubleW scale, org.netlib.util.doubleW xnorm, org.netlib.util.intW info) {
    org.netlib.lapack.Dlaln2.dlaln2(ltrans, na, nw, smin, ca, a, offseta, lda, d1, d2, b, offsetb, ldb, wr, wi, x, offsetx, ldx, scale, xnorm, info);
  }

  protected void dlals0K(int icompq, int nl, int nr, int sqre, int nrhs, double[] b, int offsetb, int ldb, double[] bx, int offsetbx, int ldbx, int[] perm, int offsetperm, int givptr, int[] givcol, int offsetgivcol, int ldgcol, double[] givnum, int offsetgivnum, int ldgnum, double[] poles, int offsetpoles, double[] difl, int offsetdifl, double[] difr, int offsetdifr, double[] z, int offsetz, int k, double c, double s, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlals0.dlals0(icompq, nl, nr, sqre, nrhs, b, offsetb, ldb, bx, offsetbx, ldbx, perm, offsetperm, givptr, givcol, offsetgivcol, ldgcol, givnum, offsetgivnum, ldgnum, poles, offsetpoles, difl, offsetdifl, difr, offsetdifr, z, offsetz, k, c, s, work, offsetwork, info);
  }

  protected void dlalsaK(int icompq, int smlsiz, int n, int nrhs, double[] b, int offsetb, int ldb, double[] bx, int offsetbx, int ldbx, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int[] k, int offsetk, double[] difl, int offsetdifl, double[] difr, int offsetdifr, double[] z, int offsetz, double[] poles, int offsetpoles, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, int ldgcol, int[] perm, int offsetperm, double[] givnum, int offsetgivnum, double[] c, int offsetc, double[] s, int offsets, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlalsa.dlalsa(icompq, smlsiz, n, nrhs, b, offsetb, ldb, bx, offsetbx, ldbx, u, offsetu, ldu, vt, offsetvt, k, offsetk, difl, offsetdifl, difr, offsetdifr, z, offsetz, poles, offsetpoles, givptr, offsetgivptr, givcol, offsetgivcol, ldgcol, perm, offsetperm, givnum, offsetgivnum, c, offsetc, s, offsets, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dlalsdK(String uplo, int smlsiz, int n, int nrhs, double[] d, int offsetd, double[] e, int offsete, double[] b, int offsetb, int ldb, double rcond, org.netlib.util.intW rank, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlalsd.dlalsd(uplo, smlsiz, n, nrhs, d, offsetd, e, offsete, b, offsetb, ldb, rcond, rank, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dlamrgK(int n1, int n2, double[] a, int offseta, int dtrd1, int dtrd2, int[] index, int offsetindex) {
    org.netlib.lapack.Dlamrg.dlamrg(n1, n2, a, offseta, dtrd1, dtrd2, index, offsetindex);
  }

  protected int dlanegK(int n, double[] d, int offsetd, double[] lld, int offsetlld, double sigma, double pivmin, int r) {
    return org.netlib.lapack.Dlaneg.dlaneg(n, d, offsetd, lld, offsetlld, sigma, pivmin, r);
  }

  protected double dlangbK(String norm, int n, int kl, int ku, double[] ab, int offsetab, int ldab, double[] work, int offsetwork) {
    return org.netlib.lapack.Dlangb.dlangb(norm, n, kl, ku, ab, offsetab, ldab, work, offsetwork);
  }

  protected double dlangeK(String norm, int m, int n, double[] a, int offseta, int lda, double[] work, int offsetwork) {
    return org.netlib.lapack.Dlange.dlange(norm, m, n, a, offseta, lda, work, offsetwork);
  }

  protected double dlangtK(String norm, int n, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu) {
    return org.netlib.lapack.Dlangt.dlangt(norm, n, dl, offsetdl, d, offsetd, du, offsetdu);
  }

  protected double dlanhsK(String norm, int n, double[] a, int offseta, int lda, double[] work, int offsetwork) {
    return org.netlib.lapack.Dlanhs.dlanhs(norm, n, a, offseta, lda, work, offsetwork);
  }

  protected double dlansbK(String norm, String uplo, int n, int k, double[] ab, int offsetab, int ldab, double[] work, int offsetwork) {
    return org.netlib.lapack.Dlansb.dlansb(norm, uplo, n, k, ab, offsetab, ldab, work, offsetwork);
  }

  protected double dlanspK(String norm, String uplo, int n, double[] ap, int offsetap, double[] work, int offsetwork) {
    return org.netlib.lapack.Dlansp.dlansp(norm, uplo, n, ap, offsetap, work, offsetwork);
  }

  protected double dlanstK(String norm, int n, double[] d, int offsetd, double[] e, int offsete) {
    return org.netlib.lapack.Dlanst.dlanst(norm, n, d, offsetd, e, offsete);
  }

  protected double dlansyK(String norm, String uplo, int n, double[] a, int offseta, int lda, double[] work, int offsetwork) {
    return org.netlib.lapack.Dlansy.dlansy(norm, uplo, n, a, offseta, lda, work, offsetwork);
  }

  protected double dlantbK(String norm, String uplo, String diag, int n, int k, double[] ab, int offsetab, int ldab, double[] work, int offsetwork) {
    return org.netlib.lapack.Dlantb.dlantb(norm, uplo, diag, n, k, ab, offsetab, ldab, work, offsetwork);
  }

  protected double dlantpK(String norm, String uplo, String diag, int n, double[] ap, int offsetap, double[] work, int offsetwork) {
    return org.netlib.lapack.Dlantp.dlantp(norm, uplo, diag, n, ap, offsetap, work, offsetwork);
  }

  protected double dlantrK(String norm, String uplo, String diag, int m, int n, double[] a, int offseta, int lda, double[] work, int offsetwork) {
    return org.netlib.lapack.Dlantr.dlantr(norm, uplo, diag, m, n, a, offseta, lda, work, offsetwork);
  }

  protected void dlanv2K(org.netlib.util.doubleW a, org.netlib.util.doubleW b, org.netlib.util.doubleW c, org.netlib.util.doubleW d, org.netlib.util.doubleW rt1r, org.netlib.util.doubleW rt1i, org.netlib.util.doubleW rt2r, org.netlib.util.doubleW rt2i, org.netlib.util.doubleW cs, org.netlib.util.doubleW sn) {
    org.netlib.lapack.Dlanv2.dlanv2(a, b, c, d, rt1r, rt1i, rt2r, rt2i, cs, sn);
  }

  protected void dlapllK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, org.netlib.util.doubleW ssmin) {
    org.netlib.lapack.Dlapll.dlapll(n, x, offsetx, incx, y, offsety, incy, ssmin);
  }

  protected void dlapmtK(boolean forwrd, int m, int n, double[] x, int offsetx, int ldx, int[] k, int offsetk) {
    org.netlib.lapack.Dlapmt.dlapmt(forwrd, m, n, x, offsetx, ldx, k, offsetk);
  }

  protected double dlapy2K(double x, double y) {
    return org.netlib.lapack.Dlapy2.dlapy2(x, y);
  }

  protected double dlapy3K(double x, double y, double z) {
    return org.netlib.lapack.Dlapy3.dlapy3(x, y, z);
  }

  protected void dlaqgbK(int m, int n, int kl, int ku, double[] ab, int offsetab, int ldab, double[] r, int offsetr, double[] c, int offsetc, double rowcnd, double colcnd, double amax, org.netlib.util.StringW equed) {
    org.netlib.lapack.Dlaqgb.dlaqgb(m, n, kl, ku, ab, offsetab, ldab, r, offsetr, c, offsetc, rowcnd, colcnd, amax, equed);
  }

  protected void dlaqgeK(int m, int n, double[] a, int offseta, int lda, double[] r, int offsetr, double[] c, int offsetc, double rowcnd, double colcnd, double amax, org.netlib.util.StringW equed) {
    org.netlib.lapack.Dlaqge.dlaqge(m, n, a, offseta, lda, r, offsetr, c, offsetc, rowcnd, colcnd, amax, equed);
  }

  protected void dlaqp2K(int m, int n, int offset, double[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, double[] tau, int offsettau, double[] vn1, int offsetvn1, double[] vn2, int offsetvn2, double[] work, int offsetwork) {
    org.netlib.lapack.Dlaqp2.dlaqp2(m, n, offset, a, offseta, lda, jpvt, offsetjpvt, tau, offsettau, vn1, offsetvn1, vn2, offsetvn2, work, offsetwork);
  }

  protected void dlaqpsK(int m, int n, int offset, int nb, org.netlib.util.intW kb, double[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, double[] tau, int offsettau, double[] vn1, int offsetvn1, double[] vn2, int offsetvn2, double[] auxv, int offsetauxv, double[] f, int offsetf, int ldf) {
    org.netlib.lapack.Dlaqps.dlaqps(m, n, offset, nb, kb, a, offseta, lda, jpvt, offsetjpvt, tau, offsettau, vn1, offsetvn1, vn2, offsetvn2, auxv, offsetauxv, f, offsetf, ldf);
  }

  protected void dlaqr0K(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, int iloz, int ihiz, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlaqr0.dlaqr0(wantt, wantz, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, iloz, ihiz, z, offsetz, ldz, work, offsetwork, lwork, info);
  }

  protected void dlaqr1K(int n, double[] h, int offseth, int ldh, double sr1, double si1, double sr2, double si2, double[] v, int offsetv) {
    org.netlib.lapack.Dlaqr1.dlaqr1(n, h, offseth, ldh, sr1, si1, sr2, si2, v, offsetv);
  }

  protected void dlaqr2K(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, double[] h, int offseth, int ldh, int iloz, int ihiz, double[] z, int offsetz, int ldz, org.netlib.util.intW ns, org.netlib.util.intW nd, double[] sr, int offsetsr, double[] si, int offsetsi, double[] v, int offsetv, int ldv, int nh, double[] t, int offsett, int ldt, int nv, double[] wv, int offsetwv, int ldwv, double[] work, int offsetwork, int lwork) {
    org.netlib.lapack.Dlaqr2.dlaqr2(wantt, wantz, n, ktop, kbot, nw, h, offseth, ldh, iloz, ihiz, z, offsetz, ldz, ns, nd, sr, offsetsr, si, offsetsi, v, offsetv, ldv, nh, t, offsett, ldt, nv, wv, offsetwv, ldwv, work, offsetwork, lwork);
  }

  protected void dlaqr3K(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, double[] h, int offseth, int ldh, int iloz, int ihiz, double[] z, int offsetz, int ldz, org.netlib.util.intW ns, org.netlib.util.intW nd, double[] sr, int offsetsr, double[] si, int offsetsi, double[] v, int offsetv, int ldv, int nh, double[] t, int offsett, int ldt, int nv, double[] wv, int offsetwv, int ldwv, double[] work, int offsetwork, int lwork) {
    org.netlib.lapack.Dlaqr3.dlaqr3(wantt, wantz, n, ktop, kbot, nw, h, offseth, ldh, iloz, ihiz, z, offsetz, ldz, ns, nd, sr, offsetsr, si, offsetsi, v, offsetv, ldv, nh, t, offsett, ldt, nv, wv, offsetwv, ldwv, work, offsetwork, lwork);
  }

  protected void dlaqr4K(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, int iloz, int ihiz, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlaqr4.dlaqr4(wantt, wantz, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, iloz, ihiz, z, offsetz, ldz, work, offsetwork, lwork, info);
  }

  protected void dlaqr5K(boolean wantt, boolean wantz, int kacc22, int n, int ktop, int kbot, int nshfts, double[] sr, int offsetsr, double[] si, int offsetsi, double[] h, int offseth, int ldh, int iloz, int ihiz, double[] z, int offsetz, int ldz, double[] v, int offsetv, int ldv, double[] u, int offsetu, int ldu, int nv, double[] wv, int offsetwv, int ldwv, int nh, double[] wh, int offsetwh, int ldwh) {
    org.netlib.lapack.Dlaqr5.dlaqr5(wantt, wantz, kacc22, n, ktop, kbot, nshfts, sr, offsetsr, si, offsetsi, h, offseth, ldh, iloz, ihiz, z, offsetz, ldz, v, offsetv, ldv, u, offsetu, ldu, nv, wv, offsetwv, ldwv, nh, wh, offsetwh, ldwh);
  }

  protected void dlaqsbK(String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double[] s, int offsets, double scond, double amax, org.netlib.util.StringW equed) {
    org.netlib.lapack.Dlaqsb.dlaqsb(uplo, n, kd, ab, offsetab, ldab, s, offsets, scond, amax, equed);
  }

  protected void dlaqspK(String uplo, int n, double[] ap, int offsetap, double[] s, int offsets, double scond, double amax, org.netlib.util.StringW equed) {
    org.netlib.lapack.Dlaqsp.dlaqsp(uplo, n, ap, offsetap, s, offsets, scond, amax, equed);
  }

  protected void dlaqsyK(String uplo, int n, double[] a, int offseta, int lda, double[] s, int offsets, double scond, double amax, org.netlib.util.StringW equed) {
    org.netlib.lapack.Dlaqsy.dlaqsy(uplo, n, a, offseta, lda, s, offsets, scond, amax, equed);
  }

  protected void dlaqtrK(boolean ltran, boolean lreal, int n, double[] t, int offsett, int ldt, double[] b, int offsetb, double w, org.netlib.util.doubleW scale, double[] x, int offsetx, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlaqtr.dlaqtr(ltran, lreal, n, t, offsett, ldt, b, offsetb, w, scale, x, offsetx, work, offsetwork, info);
  }

  protected void dlar1vK(int n, int b1, int bn, double lambda, double[] d, int offsetd, double[] l, int offsetl, double[] ld, int offsetld, double[] lld, int offsetlld, double pivmin, double gaptol, double[] z, int offsetz, boolean wantnc, org.netlib.util.intW negcnt, org.netlib.util.doubleW ztz, org.netlib.util.doubleW mingma, org.netlib.util.intW r, int[] isuppz, int offsetisuppz, org.netlib.util.doubleW nrminv, org.netlib.util.doubleW resid, org.netlib.util.doubleW rqcorr, double[] work, int offsetwork) {
    org.netlib.lapack.Dlar1v.dlar1v(n, b1, bn, lambda, d, offsetd, l, offsetl, ld, offsetld, lld, offsetlld, pivmin, gaptol, z, offsetz, wantnc, negcnt, ztz, mingma, r, isuppz, offsetisuppz, nrminv, resid, rqcorr, work, offsetwork);
  }

  protected void dlar2vK(int n, double[] x, int offsetx, double[] y, int offsety, double[] z, int offsetz, int incx, double[] c, int offsetc, double[] s, int offsets, int incc) {
    org.netlib.lapack.Dlar2v.dlar2v(n, x, offsetx, y, offsety, z, offsetz, incx, c, offsetc, s, offsets, incc);
  }

  protected void dlarfK(String side, int m, int n, double[] v, int offsetv, int incv, double tau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork) {
    org.netlib.lapack.Dlarf.dlarf(side, m, n, v, offsetv, incv, tau, c, offsetc, Ldc, work, offsetwork);
  }

  protected void dlarfbK(String side, String trans, String direct, String storev, int m, int n, int k, double[] v, int offsetv, int ldv, double[] t, int offsett, int ldt, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int ldwork) {
    org.netlib.lapack.Dlarfb.dlarfb(side, trans, direct, storev, m, n, k, v, offsetv, ldv, t, offsett, ldt, c, offsetc, Ldc, work, offsetwork, ldwork);
  }

  protected void dlarfgK(int n, org.netlib.util.doubleW alpha, double[] x, int offsetx, int incx, org.netlib.util.doubleW tau) {
    org.netlib.lapack.Dlarfg.dlarfg(n, alpha, x, offsetx, incx, tau);
  }

  protected void dlarftK(String direct, String storev, int n, int k, double[] v, int offsetv, int ldv, double[] tau, int offsettau, double[] t, int offsett, int ldt) {
    org.netlib.lapack.Dlarft.dlarft(direct, storev, n, k, v, offsetv, ldv, tau, offsettau, t, offsett, ldt);
  }

  protected void dlarfxK(String side, int m, int n, double[] v, int offsetv, double tau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork) {
    org.netlib.lapack.Dlarfx.dlarfx(side, m, n, v, offsetv, tau, c, offsetc, Ldc, work, offsetwork);
  }

  protected void dlargvK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] c, int offsetc, int incc) {
    org.netlib.lapack.Dlargv.dlargv(n, x, offsetx, incx, y, offsety, incy, c, offsetc, incc);
  }

  protected void dlarnvK(int idist, int[] iseed, int offsetiseed, int n, double[] x, int offsetx) {
    org.netlib.lapack.Dlarnv.dlarnv(idist, iseed, offsetiseed, n, x, offsetx);
  }

  protected void dlarraK(int n, double[] d, int offsetd, double[] e, int offsete, double[] e2, int offsete2, double spltol, double tnrm, org.netlib.util.intW nsplit, int[] isplit, int offsetisplit, org.netlib.util.intW info) {
    org.netlib.lapack.Dlarra.dlarra(n, d, offsetd, e, offsete, e2, offsete2, spltol, tnrm, nsplit, isplit, offsetisplit, info);
  }

  protected void dlarrbK(int n, double[] d, int offsetd, double[] lld, int offsetlld, int ifirst, int ilast, double rtol1, double rtol2, int offset, double[] w, int offsetw, double[] wgap, int offsetwgap, double[] werr, int offsetwerr, double[] work, int offsetwork, int[] iwork, int offsetiwork, double pivmin, double spdiam, int twist, org.netlib.util.intW info) {
    org.netlib.lapack.Dlarrb.dlarrb(n, d, offsetd, lld, offsetlld, ifirst, ilast, rtol1, rtol2, offset, w, offsetw, wgap, offsetwgap, werr, offsetwerr, work, offsetwork, iwork, offsetiwork, pivmin, spdiam, twist, info);
  }

  protected void dlarrcK(String jobt, int n, double vl, double vu, double[] d, int offsetd, double[] e, int offsete, double pivmin, org.netlib.util.intW eigcnt, org.netlib.util.intW lcnt, org.netlib.util.intW rcnt, org.netlib.util.intW info) {
    org.netlib.lapack.Dlarrc.dlarrc(jobt, n, vl, vu, d, offsetd, e, offsete, pivmin, eigcnt, lcnt, rcnt, info);
  }

  protected void dlarrdK(String range, String order, int n, double vl, double vu, int il, int iu, double[] gers, int offsetgers, double reltol, double[] d, int offsetd, double[] e, int offsete, double[] e2, int offsete2, double pivmin, int nsplit, int[] isplit, int offsetisplit, org.netlib.util.intW m, double[] w, int offsetw, double[] werr, int offsetwerr, org.netlib.util.doubleW wl, org.netlib.util.doubleW wu, int[] iblock, int offsetiblock, int[] indexw, int offsetindexw, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlarrd.dlarrd(range, order, n, vl, vu, il, iu, gers, offsetgers, reltol, d, offsetd, e, offsete, e2, offsete2, pivmin, nsplit, isplit, offsetisplit, m, w, offsetw, werr, offsetwerr, wl, wu, iblock, offsetiblock, indexw, offsetindexw, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dlarreK(String range, int n, org.netlib.util.doubleW vl, org.netlib.util.doubleW vu, int il, int iu, double[] d, int offsetd, double[] e, int offsete, double[] e2, int offsete2, double rtol1, double rtol2, double spltol, org.netlib.util.intW nsplit, int[] isplit, int offsetisplit, org.netlib.util.intW m, double[] w, int offsetw, double[] werr, int offsetwerr, double[] wgap, int offsetwgap, int[] iblock, int offsetiblock, int[] indexw, int offsetindexw, double[] gers, int offsetgers, org.netlib.util.doubleW pivmin, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlarre.dlarre(range, n, vl, vu, il, iu, d, offsetd, e, offsete, e2, offsete2, rtol1, rtol2, spltol, nsplit, isplit, offsetisplit, m, w, offsetw, werr, offsetwerr, wgap, offsetwgap, iblock, offsetiblock, indexw, offsetindexw, gers, offsetgers, pivmin, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dlarrfK(int n, double[] d, int offsetd, double[] l, int offsetl, double[] ld, int offsetld, int clstrt, int clend, double[] w, int offsetw, double[] wgap, int offsetwgap, double[] werr, int offsetwerr, double spdiam, double clgapl, double clgapr, double pivmin, org.netlib.util.doubleW sigma, double[] dplus, int offsetdplus, double[] lplus, int offsetlplus, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlarrf.dlarrf(n, d, offsetd, l, offsetl, ld, offsetld, clstrt, clend, w, offsetw, wgap, offsetwgap, werr, offsetwerr, spdiam, clgapl, clgapr, pivmin, sigma, dplus, offsetdplus, lplus, offsetlplus, work, offsetwork, info);
  }

  protected void dlarrjK(int n, double[] d, int offsetd, double[] e2, int offsete2, int ifirst, int ilast, double rtol, int offset, double[] w, int offsetw, double[] werr, int offsetwerr, double[] work, int offsetwork, int[] iwork, int offsetiwork, double pivmin, double spdiam, org.netlib.util.intW info) {
    org.netlib.lapack.Dlarrj.dlarrj(n, d, offsetd, e2, offsete2, ifirst, ilast, rtol, offset, w, offsetw, werr, offsetwerr, work, offsetwork, iwork, offsetiwork, pivmin, spdiam, info);
  }

  protected void dlarrkK(int n, int iw, double gl, double gu, double[] d, int offsetd, double[] e2, int offsete2, double pivmin, double reltol, org.netlib.util.doubleW w, org.netlib.util.doubleW werr, org.netlib.util.intW info) {
    org.netlib.lapack.Dlarrk.dlarrk(n, iw, gl, gu, d, offsetd, e2, offsete2, pivmin, reltol, w, werr, info);
  }

  protected void dlarrrK(int n, double[] d, int offsetd, double[] e, int offsete, org.netlib.util.intW info) {
    org.netlib.lapack.Dlarrr.dlarrr(n, d, offsetd, e, offsete, info);
  }

  protected void dlarrvK(int n, double vl, double vu, double[] d, int offsetd, double[] l, int offsetl, double pivmin, int[] isplit, int offsetisplit, int m, int dol, int dou, double minrgp, org.netlib.util.doubleW rtol1, org.netlib.util.doubleW rtol2, double[] w, int offsetw, double[] werr, int offsetwerr, double[] wgap, int offsetwgap, int[] iblock, int offsetiblock, int[] indexw, int offsetindexw, double[] gers, int offsetgers, double[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlarrv.dlarrv(n, vl, vu, d, offsetd, l, offsetl, pivmin, isplit, offsetisplit, m, dol, dou, minrgp, rtol1, rtol2, w, offsetw, werr, offsetwerr, wgap, offsetwgap, iblock, offsetiblock, indexw, offsetindexw, gers, offsetgers, z, offsetz, ldz, isuppz, offsetisuppz, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dlartgK(double f, double g, org.netlib.util.doubleW cs, org.netlib.util.doubleW sn, org.netlib.util.doubleW r) {
    org.netlib.lapack.Dlartg.dlartg(f, g, cs, sn, r);
  }

  protected void dlartvK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] c, int offsetc, double[] s, int offsets, int incc) {
    org.netlib.lapack.Dlartv.dlartv(n, x, offsetx, incx, y, offsety, incy, c, offsetc, s, offsets, incc);
  }

  protected void dlaruvK(int[] iseed, int offsetiseed, int n, double[] x, int offsetx) {
    org.netlib.lapack.Dlaruv.dlaruv(iseed, offsetiseed, n, x, offsetx);
  }

  protected void dlarzK(String side, int m, int n, int l, double[] v, int offsetv, int incv, double tau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork) {
    org.netlib.lapack.Dlarz.dlarz(side, m, n, l, v, offsetv, incv, tau, c, offsetc, Ldc, work, offsetwork);
  }

  protected void dlarzbK(String side, String trans, String direct, String storev, int m, int n, int k, int l, double[] v, int offsetv, int ldv, double[] t, int offsett, int ldt, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int ldwork) {
    org.netlib.lapack.Dlarzb.dlarzb(side, trans, direct, storev, m, n, k, l, v, offsetv, ldv, t, offsett, ldt, c, offsetc, Ldc, work, offsetwork, ldwork);
  }

  protected void dlarztK(String direct, String storev, int n, int k, double[] v, int offsetv, int ldv, double[] tau, int offsettau, double[] t, int offsett, int ldt) {
    org.netlib.lapack.Dlarzt.dlarzt(direct, storev, n, k, v, offsetv, ldv, tau, offsettau, t, offsett, ldt);
  }

  protected void dlas2K(double f, double g, double h, org.netlib.util.doubleW ssmin, org.netlib.util.doubleW ssmax) {
    org.netlib.lapack.Dlas2.dlas2(f, g, h, ssmin, ssmax);
  }

  protected void dlasclK(String type, int kl, int ku, double cfrom, double cto, int m, int n, double[] a, int offseta, int lda, org.netlib.util.intW info) {
    org.netlib.lapack.Dlascl.dlascl(type, kl, ku, cfrom, cto, m, n, a, offseta, lda, info);
  }

  protected void dlasd0K(int n, int sqre, double[] d, int offsetd, double[] e, int offsete, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int ldvt, int smlsiz, int[] iwork, int offsetiwork, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlasd0.dlasd0(n, sqre, d, offsetd, e, offsete, u, offsetu, ldu, vt, offsetvt, ldvt, smlsiz, iwork, offsetiwork, work, offsetwork, info);
  }

  protected void dlasd1K(int nl, int nr, int sqre, double[] d, int offsetd, org.netlib.util.doubleW alpha, org.netlib.util.doubleW beta, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int ldvt, int[] idxq, int offsetidxq, int[] iwork, int offsetiwork, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlasd1.dlasd1(nl, nr, sqre, d, offsetd, alpha, beta, u, offsetu, ldu, vt, offsetvt, ldvt, idxq, offsetidxq, iwork, offsetiwork, work, offsetwork, info);
  }

  protected void dlasd2K(int nl, int nr, int sqre, org.netlib.util.intW k, double[] d, int offsetd, double[] z, int offsetz, double alpha, double beta, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int ldvt, double[] dsigma, int offsetdsigma, double[] u2, int offsetu2, int ldu2, double[] vt2, int offsetvt2, int ldvt2, int[] idxp, int offsetidxp, int[] idx, int offsetidx, int[] idxc, int offsetidxc, int[] idxq, int offsetidxq, int[] coltyp, int offsetcoltyp, org.netlib.util.intW info) {
    org.netlib.lapack.Dlasd2.dlasd2(nl, nr, sqre, k, d, offsetd, z, offsetz, alpha, beta, u, offsetu, ldu, vt, offsetvt, ldvt, dsigma, offsetdsigma, u2, offsetu2, ldu2, vt2, offsetvt2, ldvt2, idxp, offsetidxp, idx, offsetidx, idxc, offsetidxc, idxq, offsetidxq, coltyp, offsetcoltyp, info);
  }

  protected void dlasd3K(int nl, int nr, int sqre, int k, double[] d, int offsetd, double[] q, int offsetq, int ldq, double[] dsigma, int offsetdsigma, double[] u, int offsetu, int ldu, double[] u2, int offsetu2, int ldu2, double[] vt, int offsetvt, int ldvt, double[] vt2, int offsetvt2, int ldvt2, int[] idxc, int offsetidxc, int[] ctot, int offsetctot, double[] z, int offsetz, org.netlib.util.intW info) {
    org.netlib.lapack.Dlasd3.dlasd3(nl, nr, sqre, k, d, offsetd, q, offsetq, ldq, dsigma, offsetdsigma, u, offsetu, ldu, u2, offsetu2, ldu2, vt, offsetvt, ldvt, vt2, offsetvt2, ldvt2, idxc, offsetidxc, ctot, offsetctot, z, offsetz, info);
  }

  protected void dlasd4K(int n, int i, double[] d, int offsetd, double[] z, int offsetz, double[] delta, int offsetdelta, double rho, org.netlib.util.doubleW sigma, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlasd4.dlasd4(n, i, d, offsetd, z, offsetz, delta, offsetdelta, rho, sigma, work, offsetwork, info);
  }

  protected void dlasd5K(int i, double[] d, int offsetd, double[] z, int offsetz, double[] delta, int offsetdelta, double rho, org.netlib.util.doubleW dsigma, double[] work, int offsetwork) {
    org.netlib.lapack.Dlasd5.dlasd5(i, d, offsetd, z, offsetz, delta, offsetdelta, rho, dsigma, work, offsetwork);
  }

  protected void dlasd6K(int icompq, int nl, int nr, int sqre, double[] d, int offsetd, double[] vf, int offsetvf, double[] vl, int offsetvl, org.netlib.util.doubleW alpha, org.netlib.util.doubleW beta, int[] idxq, int offsetidxq, int[] perm, int offsetperm, org.netlib.util.intW givptr, int[] givcol, int offsetgivcol, int ldgcol, double[] givnum, int offsetgivnum, int ldgnum, double[] poles, int offsetpoles, double[] difl, int offsetdifl, double[] difr, int offsetdifr, double[] z, int offsetz, org.netlib.util.intW k, org.netlib.util.doubleW c, org.netlib.util.doubleW s, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlasd6.dlasd6(icompq, nl, nr, sqre, d, offsetd, vf, offsetvf, vl, offsetvl, alpha, beta, idxq, offsetidxq, perm, offsetperm, givptr, givcol, offsetgivcol, ldgcol, givnum, offsetgivnum, ldgnum, poles, offsetpoles, difl, offsetdifl, difr, offsetdifr, z, offsetz, k, c, s, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dlasd7K(int icompq, int nl, int nr, int sqre, org.netlib.util.intW k, double[] d, int offsetd, double[] z, int offsetz, double[] zw, int offsetzw, double[] vf, int offsetvf, double[] vfw, int offsetvfw, double[] vl, int offsetvl, double[] vlw, int offsetvlw, double alpha, double beta, double[] dsigma, int offsetdsigma, int[] idx, int offsetidx, int[] idxp, int offsetidxp, int[] idxq, int offsetidxq, int[] perm, int offsetperm, org.netlib.util.intW givptr, int[] givcol, int offsetgivcol, int ldgcol, double[] givnum, int offsetgivnum, int ldgnum, org.netlib.util.doubleW c, org.netlib.util.doubleW s, org.netlib.util.intW info) {
    org.netlib.lapack.Dlasd7.dlasd7(icompq, nl, nr, sqre, k, d, offsetd, z, offsetz, zw, offsetzw, vf, offsetvf, vfw, offsetvfw, vl, offsetvl, vlw, offsetvlw, alpha, beta, dsigma, offsetdsigma, idx, offsetidx, idxp, offsetidxp, idxq, offsetidxq, perm, offsetperm, givptr, givcol, offsetgivcol, ldgcol, givnum, offsetgivnum, ldgnum, c, s, info);
  }

  protected void dlasd8K(int icompq, int k, double[] d, int offsetd, double[] z, int offsetz, double[] vf, int offsetvf, double[] vl, int offsetvl, double[] difl, int offsetdifl, double[] difr, int offsetdifr, int lddifr, double[] dsigma, int offsetdsigma, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlasd8.dlasd8(icompq, k, d, offsetd, z, offsetz, vf, offsetvf, vl, offsetvl, difl, offsetdifl, difr, offsetdifr, lddifr, dsigma, offsetdsigma, work, offsetwork, info);
  }

  protected void dlasdaK(int icompq, int smlsiz, int n, int sqre, double[] d, int offsetd, double[] e, int offsete, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int[] k, int offsetk, double[] difl, int offsetdifl, double[] difr, int offsetdifr, double[] z, int offsetz, double[] poles, int offsetpoles, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, int ldgcol, int[] perm, int offsetperm, double[] givnum, int offsetgivnum, double[] c, int offsetc, double[] s, int offsets, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlasda.dlasda(icompq, smlsiz, n, sqre, d, offsetd, e, offsete, u, offsetu, ldu, vt, offsetvt, k, offsetk, difl, offsetdifl, difr, offsetdifr, z, offsetz, poles, offsetpoles, givptr, offsetgivptr, givcol, offsetgivcol, ldgcol, perm, offsetperm, givnum, offsetgivnum, c, offsetc, s, offsets, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dlasdqK(String uplo, int sqre, int n, int ncvt, int nru, int ncc, double[] d, int offsetd, double[] e, int offsete, double[] vt, int offsetvt, int ldvt, double[] u, int offsetu, int ldu, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlasdq.dlasdq(uplo, sqre, n, ncvt, nru, ncc, d, offsetd, e, offsete, vt, offsetvt, ldvt, u, offsetu, ldu, c, offsetc, Ldc, work, offsetwork, info);
  }

  protected void dlasdtK(int n, org.netlib.util.intW lvl, org.netlib.util.intW nd, int[] inode, int offsetinode, int[] ndiml, int offsetndiml, int[] ndimr, int offsetndimr, int msub) {
    org.netlib.lapack.Dlasdt.dlasdt(n, lvl, nd, inode, offsetinode, ndiml, offsetndiml, ndimr, offsetndimr, msub);
  }

  protected void dlasetK(String uplo, int m, int n, double alpha, double beta, double[] a, int offseta, int lda) {
    org.netlib.lapack.Dlaset.dlaset(uplo, m, n, alpha, beta, a, offseta, lda);
  }

  protected void dlasq1K(int n, double[] d, int offsetd, double[] e, int offsete, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dlasq1.dlasq1(n, d, offsetd, e, offsete, work, offsetwork, info);
  }

  protected void dlasq2K(int n, double[] z, int offsetz, org.netlib.util.intW info) {
    org.netlib.lapack.Dlasq2.dlasq2(n, z, offsetz, info);
  }

  protected void dlasq3K(int i0, org.netlib.util.intW n0, double[] z, int offsetz, int pp, org.netlib.util.doubleW dmin, org.netlib.util.doubleW sigma, org.netlib.util.doubleW desig, org.netlib.util.doubleW qmax, org.netlib.util.intW nfail, org.netlib.util.intW iter, org.netlib.util.intW ndiv, boolean ieee, org.netlib.util.intW ttype, org.netlib.util.doubleW dmin1, org.netlib.util.doubleW dmin2, org.netlib.util.doubleW dn, org.netlib.util.doubleW dn1, org.netlib.util.doubleW dn2, org.netlib.util.doubleW g, org.netlib.util.doubleW tau) {
    // F2j still uses old signature - ignore new parameters for now
    org.netlib.lapack.Dlasq3.dlasq3(i0, n0, z, offsetz, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee);
  }

  protected void dlasq4K(int i0, int n0, double[] z, int offsetz, int pp, int n0in, double dmin, double dmin1, double dmin2, double dn, double dn1, double dn2, org.netlib.util.doubleW tau, org.netlib.util.intW ttype, org.netlib.util.doubleW g) {
    org.netlib.lapack.Dlasq4.dlasq4(i0, n0, z, offsetz, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype);
  }

  protected void dlasq5K(int i0, int n0, double[] z, int offsetz, int pp, double tau, double sigma, org.netlib.util.doubleW dmin, org.netlib.util.doubleW dmin1, org.netlib.util.doubleW dmin2, org.netlib.util.doubleW dn, org.netlib.util.doubleW dnm1, org.netlib.util.doubleW dnm2, boolean ieee, double eps) {
    // F2j still uses old signature - ignore sigma and eps parameters for now
    org.netlib.lapack.Dlasq5.dlasq5(i0, n0, z, offsetz, pp, tau, dmin, dmin1, dmin2, dn, dnm1, dnm2, ieee);
  }

  protected void dlasq6K(int i0, int n0, double[] z, int offsetz, int pp, org.netlib.util.doubleW dmin, org.netlib.util.doubleW dmin1, org.netlib.util.doubleW dmin2, org.netlib.util.doubleW dn, org.netlib.util.doubleW dnm1, org.netlib.util.doubleW dnm2) {
    org.netlib.lapack.Dlasq6.dlasq6(i0, n0, z, offsetz, pp, dmin, dmin1, dmin2, dn, dnm1, dnm2);
  }

  protected void dlasrK(String side, String pivot, String direct, int m, int n, double[] c, int offsetc, double[] s, int offsets, double[] a, int offseta, int lda) {
    org.netlib.lapack.Dlasr.dlasr(side, pivot, direct, m, n, c, offsetc, s, offsets, a, offseta, lda);
  }

  protected void dlasrtK(String id, int n, double[] d, int offsetd, org.netlib.util.intW info) {
    org.netlib.lapack.Dlasrt.dlasrt(id, n, d, offsetd, info);
  }

  protected void dlassqK(int n, double[] x, int offsetx, int incx, org.netlib.util.doubleW scale, org.netlib.util.doubleW sumsq) {
    org.netlib.lapack.Dlassq.dlassq(n, x, offsetx, incx, scale, sumsq);
  }

  protected void dlasv2K(double f, double g, double h, org.netlib.util.doubleW ssmin, org.netlib.util.doubleW ssmax, org.netlib.util.doubleW snr, org.netlib.util.doubleW csr, org.netlib.util.doubleW snl, org.netlib.util.doubleW csl) {
    org.netlib.lapack.Dlasv2.dlasv2(f, g, h, ssmin, ssmax, snr, csr, snl, csl);
  }

  protected void dlaswpK(int n, double[] a, int offseta, int lda, int k1, int k2, int[] ipiv, int offsetipiv, int incx) {
    org.netlib.lapack.Dlaswp.dlaswp(n, a, offseta, lda, k1, k2, ipiv, offsetipiv, incx);
  }

  protected void dlasy2K(boolean ltranl, boolean ltranr, int isgn, int n1, int n2, double[] tl, int offsettl, int ldtl, double[] tr, int offsettr, int ldtr, double[] b, int offsetb, int ldb, org.netlib.util.doubleW scale, double[] x, int offsetx, int ldx, org.netlib.util.doubleW xnorm, org.netlib.util.intW info) {
    org.netlib.lapack.Dlasy2.dlasy2(ltranl, ltranr, isgn, n1, n2, tl, offsettl, ldtl, tr, offsettr, ldtr, b, offsetb, ldb, scale, x, offsetx, ldx, xnorm, info);
  }

  protected void dlasyfK(String uplo, int n, int nb, org.netlib.util.intW kb, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] w, int offsetw, int ldw, org.netlib.util.intW info) {
    org.netlib.lapack.Dlasyf.dlasyf(uplo, n, nb, kb, a, offseta, lda, ipiv, offsetipiv, w, offsetw, ldw, info);
  }

  protected void dlatbsK(String uplo, String trans, String diag, String normin, int n, int kd, double[] ab, int offsetab, int ldab, double[] x, int offsetx, org.netlib.util.doubleW scale, double[] cnorm, int offsetcnorm, org.netlib.util.intW info) {
    org.netlib.lapack.Dlatbs.dlatbs(uplo, trans, diag, normin, n, kd, ab, offsetab, ldab, x, offsetx, scale, cnorm, offsetcnorm, info);
  }

  protected void dlatdfK(int ijob, int n, double[] z, int offsetz, int ldz, double[] rhs, int offsetrhs, org.netlib.util.doubleW rdsum, org.netlib.util.doubleW rdscal, int[] ipiv, int offsetipiv, int[] jpiv, int offsetjpiv) {
    org.netlib.lapack.Dlatdf.dlatdf(ijob, n, z, offsetz, ldz, rhs, offsetrhs, rdsum, rdscal, ipiv, offsetipiv, jpiv, offsetjpiv);
  }

  protected void dlatpsK(String uplo, String trans, String diag, String normin, int n, double[] ap, int offsetap, double[] x, int offsetx, org.netlib.util.doubleW scale, double[] cnorm, int offsetcnorm, org.netlib.util.intW info) {
    org.netlib.lapack.Dlatps.dlatps(uplo, trans, diag, normin, n, ap, offsetap, x, offsetx, scale, cnorm, offsetcnorm, info);
  }

  protected void dlatrdK(String uplo, int n, int nb, double[] a, int offseta, int lda, double[] e, int offsete, double[] tau, int offsettau, double[] w, int offsetw, int ldw) {
    org.netlib.lapack.Dlatrd.dlatrd(uplo, n, nb, a, offseta, lda, e, offsete, tau, offsettau, w, offsetw, ldw);
  }

  protected void dlatrsK(String uplo, String trans, String diag, String normin, int n, double[] a, int offseta, int lda, double[] x, int offsetx, org.netlib.util.doubleW scale, double[] cnorm, int offsetcnorm, org.netlib.util.intW info) {
    org.netlib.lapack.Dlatrs.dlatrs(uplo, trans, diag, normin, n, a, offseta, lda, x, offsetx, scale, cnorm, offsetcnorm, info);
  }

  protected void dlatrzK(int m, int n, int l, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork) {
    org.netlib.lapack.Dlatrz.dlatrz(m, n, l, a, offseta, lda, tau, offsettau, work, offsetwork);
  }

  protected void dlatzmK(String side, int m, int n, double[] v, int offsetv, int incv, double tau, double[] c1, int offsetc1, double[] c2, int offsetc2, int Ldc, double[] work, int offsetwork) {
    org.netlib.lapack.Dlatzm.dlatzm(side, m, n, v, offsetv, incv, tau, c1, offsetc1, c2, offsetc2, Ldc, work, offsetwork);
  }

  protected void dlauu2K(String uplo, int n, double[] a, int offseta, int lda, org.netlib.util.intW info) {
    org.netlib.lapack.Dlauu2.dlauu2(uplo, n, a, offseta, lda, info);
  }

  protected void dlauumK(String uplo, int n, double[] a, int offseta, int lda, org.netlib.util.intW info) {
    org.netlib.lapack.Dlauum.dlauum(uplo, n, a, offseta, lda, info);
  }

  protected void dlazq3K(int i0, org.netlib.util.intW n0, double[] z, int offsetz, int pp, org.netlib.util.doubleW dmin, org.netlib.util.doubleW sigma, org.netlib.util.doubleW desig, org.netlib.util.doubleW qmax, org.netlib.util.intW nfail, org.netlib.util.intW iter, org.netlib.util.intW ndiv, boolean ieee, org.netlib.util.intW ttype, org.netlib.util.doubleW dmin1, org.netlib.util.doubleW dmin2, org.netlib.util.doubleW dn, org.netlib.util.doubleW dn1, org.netlib.util.doubleW dn2, org.netlib.util.doubleW tau) {
    org.netlib.lapack.Dlazq3.dlazq3(i0, n0, z, offsetz, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee, ttype, dmin1, dmin2, dn, dn1, dn2, tau);
  }

  protected void dlazq4K(int i0, int n0, double[] z, int offsetz, int pp, int n0in, double dmin, double dmin1, double dmin2, double dn, double dn1, double dn2, org.netlib.util.doubleW tau, org.netlib.util.intW ttype, org.netlib.util.doubleW g) {
    org.netlib.lapack.Dlazq4.dlazq4(i0, n0, z, offsetz, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype, g);
  }

  protected void dopgtrK(String uplo, int n, double[] ap, int offsetap, double[] tau, int offsettau, double[] q, int offsetq, int ldq, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dopgtr.dopgtr(uplo, n, ap, offsetap, tau, offsettau, q, offsetq, ldq, work, offsetwork, info);
  }

  protected void dopmtrK(String side, String uplo, String trans, int m, int n, double[] ap, int offsetap, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dopmtr.dopmtr(side, uplo, trans, m, n, ap, offsetap, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
  }

  protected void dorg2lK(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dorg2l.dorg2l(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, info);
  }

  protected void dorg2rK(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dorg2r.dorg2r(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, info);
  }

  protected void dorgbrK(String vect, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dorgbr.dorgbr(vect, m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void dorghrK(int n, int ilo, int ihi, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dorghr.dorghr(n, ilo, ihi, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void dorgl2K(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dorgl2.dorgl2(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, info);
  }

  protected void dorglqK(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dorglq.dorglq(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void dorgqlK(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dorgql.dorgql(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void dorgqrK(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dorgqr.dorgqr(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void dorgr2K(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dorgr2.dorgr2(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, info);
  }

  protected void dorgrqK(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dorgrq.dorgrq(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void dorgtrK(String uplo, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dorgtr.dorgtr(uplo, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void dorm2lK(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dorm2l.dorm2l(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
  }

  protected void dorm2rK(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dorm2r.dorm2r(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
  }

  protected void dormbrK(String vect, String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dormbr.dormbr(vect, side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
  }

  protected void dormhrK(String side, String trans, int m, int n, int ilo, int ihi, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dormhr.dormhr(side, trans, m, n, ilo, ihi, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
  }

  protected void dorml2K(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dorml2.dorml2(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
  }

  protected void dormlqK(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dormlq.dormlq(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
  }

  protected void dormqlK(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dormql.dormql(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
  }

  protected void dormqrK(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dormqr.dormqr(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
  }

  protected void dormr2K(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dormr2.dormr2(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
  }

  protected void dormr3K(String side, String trans, int m, int n, int k, int l, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dormr3.dormr3(side, trans, m, n, k, l, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
  }

  protected void dormrqK(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dormrq.dormrq(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
  }

  protected void dormrzK(String side, String trans, int m, int n, int k, int l, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dormrz.dormrz(side, trans, m, n, k, l, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
  }

  protected void dormtrK(String side, String uplo, String trans, int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dormtr.dormtr(side, uplo, trans, m, n, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
  }

  protected void dpbconK(String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double anorm, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dpbcon.dpbcon(uplo, n, kd, ab, offsetab, ldab, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dpbequK(String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double[] s, int offsets, org.netlib.util.doubleW scond, org.netlib.util.doubleW amax, org.netlib.util.intW info) {
    org.netlib.lapack.Dpbequ.dpbequ(uplo, n, kd, ab, offsetab, ldab, s, offsets, scond, amax, info);
  }

  protected void dpbrfsK(String uplo, int n, int kd, int nrhs, double[] ab, int offsetab, int ldab, double[] afb, int offsetafb, int ldafb, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dpbrfs.dpbrfs(uplo, n, kd, nrhs, ab, offsetab, ldab, afb, offsetafb, ldafb, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dpbstfK(String uplo, int n, int kd, double[] ab, int offsetab, int ldab, org.netlib.util.intW info) {
    org.netlib.lapack.Dpbstf.dpbstf(uplo, n, kd, ab, offsetab, ldab, info);
  }

  protected void dpbsvK(String uplo, int n, int kd, int nrhs, double[] ab, int offsetab, int ldab, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dpbsv.dpbsv(uplo, n, kd, nrhs, ab, offsetab, ldab, b, offsetb, ldb, info);
  }

  protected void dpbsvxK(String fact, String uplo, int n, int kd, int nrhs, double[] ab, int offsetab, int ldab, double[] afb, int offsetafb, int ldafb, org.netlib.util.StringW equed, double[] s, int offsets, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dpbsvx.dpbsvx(fact, uplo, n, kd, nrhs, ab, offsetab, ldab, afb, offsetafb, ldafb, equed, s, offsets, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dpbtf2K(String uplo, int n, int kd, double[] ab, int offsetab, int ldab, org.netlib.util.intW info) {
    org.netlib.lapack.Dpbtf2.dpbtf2(uplo, n, kd, ab, offsetab, ldab, info);
  }

  protected void dpbtrfK(String uplo, int n, int kd, double[] ab, int offsetab, int ldab, org.netlib.util.intW info) {
    org.netlib.lapack.Dpbtrf.dpbtrf(uplo, n, kd, ab, offsetab, ldab, info);
  }

  protected void dpbtrsK(String uplo, int n, int kd, int nrhs, double[] ab, int offsetab, int ldab, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dpbtrs.dpbtrs(uplo, n, kd, nrhs, ab, offsetab, ldab, b, offsetb, ldb, info);
  }

  protected void dpoconK(String uplo, int n, double[] a, int offseta, int lda, double anorm, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dpocon.dpocon(uplo, n, a, offseta, lda, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dpoequK(int n, double[] a, int offseta, int lda, double[] s, int offsets, org.netlib.util.doubleW scond, org.netlib.util.doubleW amax, org.netlib.util.intW info) {
    org.netlib.lapack.Dpoequ.dpoequ(n, a, offseta, lda, s, offsets, scond, amax, info);
  }

  protected void dporfsK(String uplo, int n, int nrhs, double[] a, int offseta, int lda, double[] af, int offsetaf, int ldaf, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dporfs.dporfs(uplo, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dposvK(String uplo, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dposv.dposv(uplo, n, nrhs, a, offseta, lda, b, offsetb, ldb, info);
  }

  protected void dposvxK(String fact, String uplo, int n, int nrhs, double[] a, int offseta, int lda, double[] af, int offsetaf, int ldaf, org.netlib.util.StringW equed, double[] s, int offsets, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dposvx.dposvx(fact, uplo, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, equed, s, offsets, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dpotf2K(String uplo, int n, double[] a, int offseta, int lda, org.netlib.util.intW info) {
    org.netlib.lapack.Dpotf2.dpotf2(uplo, n, a, offseta, lda, info);
  }

  protected void dpotrfK(String uplo, int n, double[] a, int offseta, int lda, org.netlib.util.intW info) {
    org.netlib.lapack.Dpotrf.dpotrf(uplo, n, a, offseta, lda, info);
  }

  protected void dpotriK(String uplo, int n, double[] a, int offseta, int lda, org.netlib.util.intW info) {
    org.netlib.lapack.Dpotri.dpotri(uplo, n, a, offseta, lda, info);
  }

  protected void dpotrsK(String uplo, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dpotrs.dpotrs(uplo, n, nrhs, a, offseta, lda, b, offsetb, ldb, info);
  }

  protected void dppconK(String uplo, int n, double[] ap, int offsetap, double anorm, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dppcon.dppcon(uplo, n, ap, offsetap, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dppequK(String uplo, int n, double[] ap, int offsetap, double[] s, int offsets, org.netlib.util.doubleW scond, org.netlib.util.doubleW amax, org.netlib.util.intW info) {
    org.netlib.lapack.Dppequ.dppequ(uplo, n, ap, offsetap, s, offsets, scond, amax, info);
  }

  protected void dpprfsK(String uplo, int n, int nrhs, double[] ap, int offsetap, double[] afp, int offsetafp, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dpprfs.dpprfs(uplo, n, nrhs, ap, offsetap, afp, offsetafp, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dppsvK(String uplo, int n, int nrhs, double[] ap, int offsetap, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dppsv.dppsv(uplo, n, nrhs, ap, offsetap, b, offsetb, ldb, info);
  }

  protected void dppsvxK(String fact, String uplo, int n, int nrhs, double[] ap, int offsetap, double[] afp, int offsetafp, org.netlib.util.StringW equed, double[] s, int offsets, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dppsvx.dppsvx(fact, uplo, n, nrhs, ap, offsetap, afp, offsetafp, equed, s, offsets, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dpptrfK(String uplo, int n, double[] ap, int offsetap, org.netlib.util.intW info) {
    org.netlib.lapack.Dpptrf.dpptrf(uplo, n, ap, offsetap, info);
  }

  protected void dpptriK(String uplo, int n, double[] ap, int offsetap, org.netlib.util.intW info) {
    org.netlib.lapack.Dpptri.dpptri(uplo, n, ap, offsetap, info);
  }

  protected void dpptrsK(String uplo, int n, int nrhs, double[] ap, int offsetap, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dpptrs.dpptrs(uplo, n, nrhs, ap, offsetap, b, offsetb, ldb, info);
  }

  protected void dptconK(int n, double[] d, int offsetd, double[] e, int offsete, double anorm, org.netlib.util.doubleW rcond, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dptcon.dptcon(n, d, offsetd, e, offsete, anorm, rcond, work, offsetwork, info);
  }

  protected void dpteqrK(String compz, int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, int ldz, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dpteqr.dpteqr(compz, n, d, offsetd, e, offsete, z, offsetz, ldz, work, offsetwork, info);
  }

  protected void dptrfsK(int n, int nrhs, double[] d, int offsetd, double[] e, int offsete, double[] df, int offsetdf, double[] ef, int offsetef, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dptrfs.dptrfs(n, nrhs, d, offsetd, e, offsete, df, offsetdf, ef, offsetef, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, info);
  }

  protected void dptsvK(int n, int nrhs, double[] d, int offsetd, double[] e, int offsete, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dptsv.dptsv(n, nrhs, d, offsetd, e, offsete, b, offsetb, ldb, info);
  }

  protected void dptsvxK(String fact, int n, int nrhs, double[] d, int offsetd, double[] e, int offsete, double[] df, int offsetdf, double[] ef, int offsetef, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dptsvx.dptsvx(fact, n, nrhs, d, offsetd, e, offsete, df, offsetdf, ef, offsetef, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, info);
  }

  protected void dpttrfK(int n, double[] d, int offsetd, double[] e, int offsete, org.netlib.util.intW info) {
    org.netlib.lapack.Dpttrf.dpttrf(n, d, offsetd, e, offsete, info);
  }

  protected void dpttrsK(int n, int nrhs, double[] d, int offsetd, double[] e, int offsete, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dpttrs.dpttrs(n, nrhs, d, offsetd, e, offsete, b, offsetb, ldb, info);
  }

  protected void dptts2K(int n, int nrhs, double[] d, int offsetd, double[] e, int offsete, double[] b, int offsetb, int ldb) {
    org.netlib.lapack.Dptts2.dptts2(n, nrhs, d, offsetd, e, offsete, b, offsetb, ldb);
  }

  protected void drsclK(int n, double sa, double[] sx, int offsetsx, int incx) {
    org.netlib.lapack.Drscl.drscl(n, sa, sx, offsetsx, incx);
  }

  protected void dsbevK(String jobz, String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsbev.dsbev(jobz, uplo, n, kd, ab, offsetab, ldab, w, offsetw, z, offsetz, ldz, work, offsetwork, info);
  }

  protected void dsbevdK(String jobz, String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsbevd.dsbevd(jobz, uplo, n, kd, ab, offsetab, ldab, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void dsbevxK(String jobz, String range, String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double[] q, int offsetq, int ldq, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info) {
    org.netlib.lapack.Dsbevx.dsbevx(jobz, range, uplo, n, kd, ab, offsetab, ldab, q, offsetq, ldq, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
  }

  protected void dsbgstK(String vect, String uplo, int n, int ka, int kb, double[] ab, int offsetab, int ldab, double[] bb, int offsetbb, int ldbb, double[] x, int offsetx, int ldx, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsbgst.dsbgst(vect, uplo, n, ka, kb, ab, offsetab, ldab, bb, offsetbb, ldbb, x, offsetx, ldx, work, offsetwork, info);
  }

  protected void dsbgvK(String jobz, String uplo, int n, int ka, int kb, double[] ab, int offsetab, int ldab, double[] bb, int offsetbb, int ldbb, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsbgv.dsbgv(jobz, uplo, n, ka, kb, ab, offsetab, ldab, bb, offsetbb, ldbb, w, offsetw, z, offsetz, ldz, work, offsetwork, info);
  }

  protected void dsbgvdK(String jobz, String uplo, int n, int ka, int kb, double[] ab, int offsetab, int ldab, double[] bb, int offsetbb, int ldbb, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsbgvd.dsbgvd(jobz, uplo, n, ka, kb, ab, offsetab, ldab, bb, offsetbb, ldbb, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void dsbgvxK(String jobz, String range, String uplo, int n, int ka, int kb, double[] ab, int offsetab, int ldab, double[] bb, int offsetbb, int ldbb, double[] q, int offsetq, int ldq, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info) {
    org.netlib.lapack.Dsbgvx.dsbgvx(jobz, range, uplo, n, ka, kb, ab, offsetab, ldab, bb, offsetbb, ldbb, q, offsetq, ldq, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
  }

  protected void dsbtrdK(String vect, String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double[] d, int offsetd, double[] e, int offsete, double[] q, int offsetq, int ldq, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsbtrd.dsbtrd(vect, uplo, n, kd, ab, offsetab, ldab, d, offsetd, e, offsete, q, offsetq, ldq, work, offsetwork, info);
  }

  protected void dsgesvK(int n, int nrhs, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] work, int offsetwork, float[] swork, int offsetswork, org.netlib.util.intW iter, org.netlib.util.intW info) {
    org.netlib.lapack.Dsgesv.dsgesv(n, nrhs, a, offseta, lda, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, work, offsetwork, swork, offsetswork, iter, info);
  }

  protected void dspconK(String uplo, int n, double[] ap, int offsetap, int[] ipiv, int offsetipiv, double anorm, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dspcon.dspcon(uplo, n, ap, offsetap, ipiv, offsetipiv, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dspevK(String jobz, String uplo, int n, double[] ap, int offsetap, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dspev.dspev(jobz, uplo, n, ap, offsetap, w, offsetw, z, offsetz, ldz, work, offsetwork, info);
  }

  protected void dspevdK(String jobz, String uplo, int n, double[] ap, int offsetap, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dspevd.dspevd(jobz, uplo, n, ap, offsetap, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void dspevxK(String jobz, String range, String uplo, int n, double[] ap, int offsetap, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info) {
    org.netlib.lapack.Dspevx.dspevx(jobz, range, uplo, n, ap, offsetap, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
  }

  protected void dspgstK(int itype, String uplo, int n, double[] ap, int offsetap, double[] bp, int offsetbp, org.netlib.util.intW info) {
    org.netlib.lapack.Dspgst.dspgst(itype, uplo, n, ap, offsetap, bp, offsetbp, info);
  }

  protected void dspgvK(int itype, String jobz, String uplo, int n, double[] ap, int offsetap, double[] bp, int offsetbp, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dspgv.dspgv(itype, jobz, uplo, n, ap, offsetap, bp, offsetbp, w, offsetw, z, offsetz, ldz, work, offsetwork, info);
  }

  protected void dspgvdK(int itype, String jobz, String uplo, int n, double[] ap, int offsetap, double[] bp, int offsetbp, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dspgvd.dspgvd(itype, jobz, uplo, n, ap, offsetap, bp, offsetbp, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void dspgvxK(int itype, String jobz, String range, String uplo, int n, double[] ap, int offsetap, double[] bp, int offsetbp, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info) {
    org.netlib.lapack.Dspgvx.dspgvx(itype, jobz, range, uplo, n, ap, offsetap, bp, offsetbp, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
  }

  protected void dsprfsK(String uplo, int n, int nrhs, double[] ap, int offsetap, double[] afp, int offsetafp, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsprfs.dsprfs(uplo, n, nrhs, ap, offsetap, afp, offsetafp, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dspsvK(String uplo, int n, int nrhs, double[] ap, int offsetap, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dspsv.dspsv(uplo, n, nrhs, ap, offsetap, ipiv, offsetipiv, b, offsetb, ldb, info);
  }

  protected void dspsvxK(String fact, String uplo, int n, int nrhs, double[] ap, int offsetap, double[] afp, int offsetafp, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dspsvx.dspsvx(fact, uplo, n, nrhs, ap, offsetap, afp, offsetafp, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dsptrdK(String uplo, int n, double[] ap, int offsetap, double[] d, int offsetd, double[] e, int offsete, double[] tau, int offsettau, org.netlib.util.intW info) {
    org.netlib.lapack.Dsptrd.dsptrd(uplo, n, ap, offsetap, d, offsetd, e, offsete, tau, offsettau, info);
  }

  protected void dsptrfK(String uplo, int n, double[] ap, int offsetap, int[] ipiv, int offsetipiv, org.netlib.util.intW info) {
    org.netlib.lapack.Dsptrf.dsptrf(uplo, n, ap, offsetap, ipiv, offsetipiv, info);
  }

  protected void dsptriK(String uplo, int n, double[] ap, int offsetap, int[] ipiv, int offsetipiv, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsptri.dsptri(uplo, n, ap, offsetap, ipiv, offsetipiv, work, offsetwork, info);
  }

  protected void dsptrsK(String uplo, int n, int nrhs, double[] ap, int offsetap, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dsptrs.dsptrs(uplo, n, nrhs, ap, offsetap, ipiv, offsetipiv, b, offsetb, ldb, info);
  }

  protected void dstebzK(String range, String order, int n, double vl, double vu, int il, int iu, double abstol, double[] d, int offsetd, double[] e, int offsete, org.netlib.util.intW m, org.netlib.util.intW nsplit, double[] w, int offsetw, int[] iblock, int offsetiblock, int[] isplit, int offsetisplit, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dstebz.dstebz(range, order, n, vl, vu, il, iu, abstol, d, offsetd, e, offsete, m, nsplit, w, offsetw, iblock, offsetiblock, isplit, offsetisplit, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dstedcK(String compz, int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dstedc.dstedc(compz, n, d, offsetd, e, offsete, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void dstegrK(String jobz, String range, int n, double[] d, int offsetd, double[] e, int offsete, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dstegr.dstegr(jobz, range, n, d, offsetd, e, offsete, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, isuppz, offsetisuppz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void dsteinK(int n, double[] d, int offsetd, double[] e, int offsete, int m, double[] w, int offsetw, int[] iblock, int offsetiblock, int[] isplit, int offsetisplit, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info) {
    org.netlib.lapack.Dstein.dstein(n, d, offsetd, e, offsete, m, w, offsetw, iblock, offsetiblock, isplit, offsetisplit, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
  }

  protected void dstemrK(String jobz, String range, int n, double[] d, int offsetd, double[] e, int offsete, double vl, double vu, int il, int iu, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, int nzc, int[] isuppz, int offsetisuppz, org.netlib.util.booleanW tryrac, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dstemr.dstemr(jobz, range, n, d, offsetd, e, offsete, vl, vu, il, iu, m, w, offsetw, z, offsetz, ldz, nzc, isuppz, offsetisuppz, tryrac, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void dsteqrK(String compz, int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, int ldz, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsteqr.dsteqr(compz, n, d, offsetd, e, offsete, z, offsetz, ldz, work, offsetwork, info);
  }

  protected void dsterfK(int n, double[] d, int offsetd, double[] e, int offsete, org.netlib.util.intW info) {
    org.netlib.lapack.Dsterf.dsterf(n, d, offsetd, e, offsete, info);
  }

  protected void dstevK(String jobz, int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, int ldz, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dstev.dstev(jobz, n, d, offsetd, e, offsete, z, offsetz, ldz, work, offsetwork, info);
  }

  protected void dstevdK(String jobz, int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dstevd.dstevd(jobz, n, d, offsetd, e, offsete, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void dstevrK(String jobz, String range, int n, double[] d, int offsetd, double[] e, int offsete, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dstevr.dstevr(jobz, range, n, d, offsetd, e, offsete, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, isuppz, offsetisuppz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void dstevxK(String jobz, String range, int n, double[] d, int offsetd, double[] e, int offsete, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info) {
    org.netlib.lapack.Dstevx.dstevx(jobz, range, n, d, offsetd, e, offsete, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
  }

  protected void dsyconK(String uplo, int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double anorm, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsycon.dsycon(uplo, n, a, offseta, lda, ipiv, offsetipiv, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dsyevK(String jobz, String uplo, int n, double[] a, int offseta, int lda, double[] w, int offsetw, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsyev.dsyev(jobz, uplo, n, a, offseta, lda, w, offsetw, work, offsetwork, lwork, info);
  }

  protected void dsyevdK(String jobz, String uplo, int n, double[] a, int offseta, int lda, double[] w, int offsetw, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsyevd.dsyevd(jobz, uplo, n, a, offseta, lda, w, offsetw, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void dsyevrK(String jobz, String range, String uplo, int n, double[] a, int offseta, int lda, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsyevr.dsyevr(jobz, range, uplo, n, a, offseta, lda, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, isuppz, offsetisuppz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void dsyevxK(String jobz, String range, String uplo, int n, double[] a, int offseta, int lda, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info) {
    org.netlib.lapack.Dsyevx.dsyevx(jobz, range, uplo, n, a, offseta, lda, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, ifail, offsetifail, info);
  }

  protected void dsygs2K(int itype, String uplo, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dsygs2.dsygs2(itype, uplo, n, a, offseta, lda, b, offsetb, ldb, info);
  }

  protected void dsygstK(int itype, String uplo, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dsygst.dsygst(itype, uplo, n, a, offseta, lda, b, offsetb, ldb, info);
  }

  protected void dsygvK(int itype, String jobz, String uplo, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] w, int offsetw, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsygv.dsygv(itype, jobz, uplo, n, a, offseta, lda, b, offsetb, ldb, w, offsetw, work, offsetwork, lwork, info);
  }

  protected void dsygvdK(int itype, String jobz, String uplo, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] w, int offsetw, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsygvd.dsygvd(itype, jobz, uplo, n, a, offseta, lda, b, offsetb, ldb, w, offsetw, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void dsygvxK(int itype, String jobz, String range, String uplo, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info) {
    org.netlib.lapack.Dsygvx.dsygvx(itype, jobz, range, uplo, n, a, offseta, lda, b, offsetb, ldb, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, ifail, offsetifail, info);
  }

  protected void dsyrfsK(String uplo, int n, int nrhs, double[] a, int offseta, int lda, double[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsyrfs.dsyrfs(uplo, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dsysvK(String uplo, int n, int nrhs, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsysv.dsysv(uplo, n, nrhs, a, offseta, lda, ipiv, offsetipiv, b, offsetb, ldb, work, offsetwork, lwork, info);
  }

  protected void dsysvxK(String fact, String uplo, int n, int nrhs, double[] a, int offseta, int lda, double[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsysvx.dsysvx(fact, uplo, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, lwork, iwork, offsetiwork, info);
  }

  protected void dsytd2K(String uplo, int n, double[] a, int offseta, int lda, double[] d, int offsetd, double[] e, int offsete, double[] tau, int offsettau, org.netlib.util.intW info) {
    org.netlib.lapack.Dsytd2.dsytd2(uplo, n, a, offseta, lda, d, offsetd, e, offsete, tau, offsettau, info);
  }

  protected void dsytf2K(String uplo, int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, org.netlib.util.intW info) {
    org.netlib.lapack.Dsytf2.dsytf2(uplo, n, a, offseta, lda, ipiv, offsetipiv, info);
  }

  protected void dsytrdK(String uplo, int n, double[] a, int offseta, int lda, double[] d, int offsetd, double[] e, int offsete, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsytrd.dsytrd(uplo, n, a, offseta, lda, d, offsetd, e, offsete, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void dsytrfK(String uplo, int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsytrf.dsytrf(uplo, n, a, offseta, lda, ipiv, offsetipiv, work, offsetwork, lwork, info);
  }

  protected void dsytriK(String uplo, int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dsytri.dsytri(uplo, n, a, offseta, lda, ipiv, offsetipiv, work, offsetwork, info);
  }

  protected void dsytrsK(String uplo, int n, int nrhs, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dsytrs.dsytrs(uplo, n, nrhs, a, offseta, lda, ipiv, offsetipiv, b, offsetb, ldb, info);
  }

  protected void dtbconK(String norm, String uplo, String diag, int n, int kd, double[] ab, int offsetab, int ldab, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dtbcon.dtbcon(norm, uplo, diag, n, kd, ab, offsetab, ldab, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dtbrfsK(String uplo, String trans, String diag, int n, int kd, int nrhs, double[] ab, int offsetab, int ldab, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dtbrfs.dtbrfs(uplo, trans, diag, n, kd, nrhs, ab, offsetab, ldab, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dtbtrsK(String uplo, String trans, String diag, int n, int kd, int nrhs, double[] ab, int offsetab, int ldab, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dtbtrs.dtbtrs(uplo, trans, diag, n, kd, nrhs, ab, offsetab, ldab, b, offsetb, ldb, info);
  }

  protected void dtgevcK(String side, String howmny, boolean[] select, int offsetselect, int n, double[] s, int offsets, int lds, double[] p, int offsetp, int ldp, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, int mm, org.netlib.util.intW m, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dtgevc.dtgevc(side, howmny, select, offsetselect, n, s, offsets, lds, p, offsetp, ldp, vl, offsetvl, ldvl, vr, offsetvr, ldvr, mm, m, work, offsetwork, info);
  }

  protected void dtgex2K(boolean wantq, boolean wantz, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] q, int offsetq, int ldq, double[] z, int offsetz, int ldz, int j1, int n1, int n2, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dtgex2.dtgex2(wantq, wantz, n, a, offseta, lda, b, offsetb, ldb, q, offsetq, ldq, z, offsetz, ldz, j1, n1, n2, work, offsetwork, lwork, info);
  }

  protected void dtgexcK(boolean wantq, boolean wantz, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] q, int offsetq, int ldq, double[] z, int offsetz, int ldz, org.netlib.util.intW ifst, org.netlib.util.intW ilst, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dtgexc.dtgexc(wantq, wantz, n, a, offseta, lda, b, offsetb, ldb, q, offsetq, ldq, z, offsetz, ldz, ifst, ilst, work, offsetwork, lwork, info);
  }

  protected void dtgsenK(int ijob, boolean wantq, boolean wantz, boolean[] select, int offsetselect, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] q, int offsetq, int ldq, double[] z, int offsetz, int ldz, org.netlib.util.intW m, org.netlib.util.doubleW pl, org.netlib.util.doubleW pr, double[] dif, int offsetdif, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dtgsen.dtgsen(ijob, wantq, wantz, select, offsetselect, n, a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, q, offsetq, ldq, z, offsetz, ldz, m, pl, pr, dif, offsetdif, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void dtgsjaK(String jobu, String jobv, String jobq, int m, int p, int n, int k, int l, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double tola, double tolb, double[] alpha, int offsetalpha, double[] beta, int offsetbeta, double[] u, int offsetu, int ldu, double[] v, int offsetv, int ldv, double[] q, int offsetq, int ldq, double[] work, int offsetwork, org.netlib.util.intW ncycle, org.netlib.util.intW info) {
    org.netlib.lapack.Dtgsja.dtgsja(jobu, jobv, jobq, m, p, n, k, l, a, offseta, lda, b, offsetb, ldb, tola, tolb, alpha, offsetalpha, beta, offsetbeta, u, offsetu, ldu, v, offsetv, ldv, q, offsetq, ldq, work, offsetwork, ncycle, info);
  }

  protected void dtgsnaK(String job, String howmny, boolean[] select, int offsetselect, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, double[] s, int offsets, double[] dif, int offsetdif, int mm, org.netlib.util.intW m, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dtgsna.dtgsna(job, howmny, select, offsetselect, n, a, offseta, lda, b, offsetb, ldb, vl, offsetvl, ldvl, vr, offsetvr, ldvr, s, offsets, dif, offsetdif, mm, m, work, offsetwork, lwork, iwork, offsetiwork, info);
  }

  protected void dtgsy2K(String trans, int ijob, int m, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] c, int offsetc, int Ldc, double[] d, int offsetd, int ldd, double[] e, int offsete, int lde, double[] f, int offsetf, int ldf, org.netlib.util.doubleW scale, org.netlib.util.doubleW rdsum, org.netlib.util.doubleW rdscal, int[] iwork, int offsetiwork, org.netlib.util.intW pq, org.netlib.util.intW info) {
    org.netlib.lapack.Dtgsy2.dtgsy2(trans, ijob, m, n, a, offseta, lda, b, offsetb, ldb, c, offsetc, Ldc, d, offsetd, ldd, e, offsete, lde, f, offsetf, ldf, scale, rdsum, rdscal, iwork, offsetiwork, pq, info);
  }

  protected void dtgsylK(String trans, int ijob, int m, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] c, int offsetc, int Ldc, double[] d, int offsetd, int ldd, double[] e, int offsete, int lde, double[] f, int offsetf, int ldf, org.netlib.util.doubleW scale, org.netlib.util.doubleW dif, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dtgsyl.dtgsyl(trans, ijob, m, n, a, offseta, lda, b, offsetb, ldb, c, offsetc, Ldc, d, offsetd, ldd, e, offsete, lde, f, offsetf, ldf, scale, dif, work, offsetwork, lwork, iwork, offsetiwork, info);
  }

  protected void dtpconK(String norm, String uplo, String diag, int n, double[] ap, int offsetap, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dtpcon.dtpcon(norm, uplo, diag, n, ap, offsetap, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dtprfsK(String uplo, String trans, String diag, int n, int nrhs, double[] ap, int offsetap, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dtprfs.dtprfs(uplo, trans, diag, n, nrhs, ap, offsetap, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dtptriK(String uplo, String diag, int n, double[] ap, int offsetap, org.netlib.util.intW info) {
    org.netlib.lapack.Dtptri.dtptri(uplo, diag, n, ap, offsetap, info);
  }

  protected void dtptrsK(String uplo, String trans, String diag, int n, int nrhs, double[] ap, int offsetap, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dtptrs.dtptrs(uplo, trans, diag, n, nrhs, ap, offsetap, b, offsetb, ldb, info);
  }

  protected void dtrconK(String norm, String uplo, String diag, int n, double[] a, int offseta, int lda, org.netlib.util.doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dtrcon.dtrcon(norm, uplo, diag, n, a, offseta, lda, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dtrevcK(String side, String howmny, boolean[] select, int offsetselect, int n, double[] t, int offsett, int ldt, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, int mm, org.netlib.util.intW m, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dtrevc.dtrevc(side, howmny, select, offsetselect, n, t, offsett, ldt, vl, offsetvl, ldvl, vr, offsetvr, ldvr, mm, m, work, offsetwork, info);
  }

  protected void dtrexcK(String compq, int n, double[] t, int offsett, int ldt, double[] q, int offsetq, int ldq, org.netlib.util.intW ifst, org.netlib.util.intW ilst, double[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dtrexc.dtrexc(compq, n, t, offsett, ldt, q, offsetq, ldq, ifst, ilst, work, offsetwork, info);
  }

  protected void dtrrfsK(String uplo, String trans, String diag, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dtrrfs.dtrrfs(uplo, trans, diag, n, nrhs, a, offseta, lda, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void dtrsenK(String job, String compq, boolean[] select, int offsetselect, int n, double[] t, int offsett, int ldt, double[] q, int offsetq, int ldq, double[] wr, int offsetwr, double[] wi, int offsetwi, org.netlib.util.intW m, org.netlib.util.doubleW s, org.netlib.util.doubleW sep, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dtrsen.dtrsen(job, compq, select, offsetselect, n, t, offsett, ldt, q, offsetq, ldq, wr, offsetwr, wi, offsetwi, m, s, sep, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void dtrsnaK(String job, String howmny, boolean[] select, int offsetselect, int n, double[] t, int offsett, int ldt, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, double[] s, int offsets, double[] sep, int offsetsep, int mm, org.netlib.util.intW m, double[] work, int offsetwork, int ldwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dtrsna.dtrsna(job, howmny, select, offsetselect, n, t, offsett, ldt, vl, offsetvl, ldvl, vr, offsetvr, ldvr, s, offsets, sep, offsetsep, mm, m, work, offsetwork, ldwork, iwork, offsetiwork, info);
  }

  protected void dtrsylK(String trana, String tranb, int isgn, int m, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] c, int offsetc, int Ldc, org.netlib.util.doubleW scale, org.netlib.util.intW info) {
    org.netlib.lapack.Dtrsyl.dtrsyl(trana, tranb, isgn, m, n, a, offseta, lda, b, offsetb, ldb, c, offsetc, Ldc, scale, info);
  }

  protected void dtrti2K(String uplo, String diag, int n, double[] a, int offseta, int lda, org.netlib.util.intW info) {
    org.netlib.lapack.Dtrti2.dtrti2(uplo, diag, n, a, offseta, lda, info);
  }

  protected void dtrtriK(String uplo, String diag, int n, double[] a, int offseta, int lda, org.netlib.util.intW info) {
    org.netlib.lapack.Dtrtri.dtrtri(uplo, diag, n, a, offseta, lda, info);
  }

  protected void dtrtrsK(String uplo, String trans, String diag, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Dtrtrs.dtrtrs(uplo, trans, diag, n, nrhs, a, offseta, lda, b, offsetb, ldb, info);
  }

  protected void dtzrqfK(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, org.netlib.util.intW info) {
    org.netlib.lapack.Dtzrqf.dtzrqf(m, n, a, offseta, lda, tau, offsettau, info);
  }

  protected void dtzrzfK(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Dtzrzf.dtzrzf(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected int ieeeckK(int ispec, float zero, float one) {
    return org.netlib.lapack.Ieeeck.ieeeck(ispec, zero, one);
  }

  protected int ilaenvK(int ispec, String name, String opts, int n1, int n2, int n3, int n4) {
    return org.netlib.lapack.Ilaenv.ilaenv(ispec, name, opts, n1, n2, n3, n4);
  }

  protected void ilaverK(org.netlib.util.intW vers_major, org.netlib.util.intW vers_minor, org.netlib.util.intW vers_patch) {
    org.netlib.lapack.Ilaver.ilaver(vers_major, vers_minor, vers_patch);
  }

  protected int iparmqK(int ispec, String name, String opts, int n, int ilo, int ihi, int lwork) {
    return org.netlib.lapack.Iparmq.iparmq(ispec, name, opts, n, ilo, ihi, lwork);
  }

  protected boolean lsamenK(int n, String ca, String cb) {
    return org.netlib.lapack.Lsamen.lsamen(n, ca, cb);
  }

  protected void sbdsdcK(String uplo, String compq, int n, float[] d, int offsetd, float[] e, int offsete, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int ldvt, float[] q, int offsetq, int[] iq, int offsetiq, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sbdsdc.sbdsdc(uplo, compq, n, d, offsetd, e, offsete, u, offsetu, ldu, vt, offsetvt, ldvt, q, offsetq, iq, offsetiq, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void sbdsqrK(String uplo, int n, int ncvt, int nru, int ncc, float[] d, int offsetd, float[] e, int offsete, float[] vt, int offsetvt, int ldvt, float[] u, int offsetu, int ldu, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sbdsqr.sbdsqr(uplo, n, ncvt, nru, ncc, d, offsetd, e, offsete, vt, offsetvt, ldvt, u, offsetu, ldu, c, offsetc, Ldc, work, offsetwork, info);
  }

  protected void sdisnaK(String job, int m, int n, float[] d, int offsetd, float[] sep, int offsetsep, org.netlib.util.intW info) {
    org.netlib.lapack.Sdisna.sdisna(job, m, n, d, offsetd, sep, offsetsep, info);
  }

  protected void sgbbrdK(String vect, int m, int n, int ncc, int kl, int ku, float[] ab, int offsetab, int ldab, float[] d, int offsetd, float[] e, int offsete, float[] q, int offsetq, int ldq, float[] pt, int offsetpt, int ldpt, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgbbrd.sgbbrd(vect, m, n, ncc, kl, ku, ab, offsetab, ldab, d, offsetd, e, offsete, q, offsetq, ldq, pt, offsetpt, ldpt, c, offsetc, Ldc, work, offsetwork, info);
  }

  protected void sgbconK(String norm, int n, int kl, int ku, float[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, float anorm, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgbcon.sgbcon(norm, n, kl, ku, ab, offsetab, ldab, ipiv, offsetipiv, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void sgbequK(int m, int n, int kl, int ku, float[] ab, int offsetab, int ldab, float[] r, int offsetr, float[] c, int offsetc, org.netlib.util.floatW rowcnd, org.netlib.util.floatW colcnd, org.netlib.util.floatW amax, org.netlib.util.intW info) {
    org.netlib.lapack.Sgbequ.sgbequ(m, n, kl, ku, ab, offsetab, ldab, r, offsetr, c, offsetc, rowcnd, colcnd, amax, info);
  }

  protected void sgbrfsK(String trans, int n, int kl, int ku, int nrhs, float[] ab, int offsetab, int ldab, float[] afb, int offsetafb, int ldafb, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgbrfs.sgbrfs(trans, n, kl, ku, nrhs, ab, offsetab, ldab, afb, offsetafb, ldafb, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void sgbsvK(int n, int kl, int ku, int nrhs, float[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Sgbsv.sgbsv(n, kl, ku, nrhs, ab, offsetab, ldab, ipiv, offsetipiv, b, offsetb, ldb, info);
  }

  protected void sgbsvxK(String fact, String trans, int n, int kl, int ku, int nrhs, float[] ab, int offsetab, int ldab, float[] afb, int offsetafb, int ldafb, int[] ipiv, int offsetipiv, org.netlib.util.StringW equed, float[] r, int offsetr, float[] c, int offsetc, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, org.netlib.util.floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgbsvx.sgbsvx(fact, trans, n, kl, ku, nrhs, ab, offsetab, ldab, afb, offsetafb, ldafb, ipiv, offsetipiv, equed, r, offsetr, c, offsetc, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void sgbtf2K(int m, int n, int kl, int ku, float[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, org.netlib.util.intW info) {
    org.netlib.lapack.Sgbtf2.sgbtf2(m, n, kl, ku, ab, offsetab, ldab, ipiv, offsetipiv, info);
  }

  protected void sgbtrfK(int m, int n, int kl, int ku, float[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, org.netlib.util.intW info) {
    org.netlib.lapack.Sgbtrf.sgbtrf(m, n, kl, ku, ab, offsetab, ldab, ipiv, offsetipiv, info);
  }

  protected void sgbtrsK(String trans, int n, int kl, int ku, int nrhs, float[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Sgbtrs.sgbtrs(trans, n, kl, ku, nrhs, ab, offsetab, ldab, ipiv, offsetipiv, b, offsetb, ldb, info);
  }

  protected void sgebakK(String job, String side, int n, int ilo, int ihi, float[] scale, int offsetscale, int m, float[] v, int offsetv, int ldv, org.netlib.util.intW info) {
    org.netlib.lapack.Sgebak.sgebak(job, side, n, ilo, ihi, scale, offsetscale, m, v, offsetv, ldv, info);
  }

  protected void sgebalK(String job, int n, float[] a, int offseta, int lda, org.netlib.util.intW ilo, org.netlib.util.intW ihi, float[] scale, int offsetscale, org.netlib.util.intW info) {
    org.netlib.lapack.Sgebal.sgebal(job, n, a, offseta, lda, ilo, ihi, scale, offsetscale, info);
  }

  protected void sgebd2K(int m, int n, float[] a, int offseta, int lda, float[] d, int offsetd, float[] e, int offsete, float[] tauq, int offsettauq, float[] taup, int offsettaup, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgebd2.sgebd2(m, n, a, offseta, lda, d, offsetd, e, offsete, tauq, offsettauq, taup, offsettaup, work, offsetwork, info);
  }

  protected void sgebrdK(int m, int n, float[] a, int offseta, int lda, float[] d, int offsetd, float[] e, int offsete, float[] tauq, int offsettauq, float[] taup, int offsettaup, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgebrd.sgebrd(m, n, a, offseta, lda, d, offsetd, e, offsete, tauq, offsettauq, taup, offsettaup, work, offsetwork, lwork, info);
  }

  protected void sgeconK(String norm, int n, float[] a, int offseta, int lda, float anorm, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgecon.sgecon(norm, n, a, offseta, lda, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void sgeequK(int m, int n, float[] a, int offseta, int lda, float[] r, int offsetr, float[] c, int offsetc, org.netlib.util.floatW rowcnd, org.netlib.util.floatW colcnd, org.netlib.util.floatW amax, org.netlib.util.intW info) {
    org.netlib.lapack.Sgeequ.sgeequ(m, n, a, offseta, lda, r, offsetr, c, offsetc, rowcnd, colcnd, amax, info);
  }

  protected void sgeesK(String jobvs, String sort, java.lang.Object select, int n, float[] a, int offseta, int lda, org.netlib.util.intW sdim, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] vs, int offsetvs, int ldvs, float[] work, int offsetwork, int lwork, boolean[] bwork, int offsetbwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgees.sgees(jobvs, sort, select, n, a, offseta, lda, sdim, wr, offsetwr, wi, offsetwi, vs, offsetvs, ldvs, work, offsetwork, lwork, bwork, offsetbwork, info);
  }

  protected void sgeesxK(String jobvs, String sort, java.lang.Object select, String sense, int n, float[] a, int offseta, int lda, org.netlib.util.intW sdim, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] vs, int offsetvs, int ldvs, org.netlib.util.floatW rconde, org.netlib.util.floatW rcondv, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, boolean[] bwork, int offsetbwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgeesx.sgeesx(jobvs, sort, select, sense, n, a, offseta, lda, sdim, wr, offsetwr, wi, offsetwi, vs, offsetvs, ldvs, rconde, rcondv, work, offsetwork, lwork, iwork, offsetiwork, liwork, bwork, offsetbwork, info);
  }

  protected void sgeevK(String jobvl, String jobvr, int n, float[] a, int offseta, int lda, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgeev.sgeev(jobvl, jobvr, n, a, offseta, lda, wr, offsetwr, wi, offsetwi, vl, offsetvl, ldvl, vr, offsetvr, ldvr, work, offsetwork, lwork, info);
  }

  protected void sgeevxK(String balanc, String jobvl, String jobvr, String sense, int n, float[] a, int offseta, int lda, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, org.netlib.util.intW ilo, org.netlib.util.intW ihi, float[] scale, int offsetscale, org.netlib.util.floatW abnrm, float[] rconde, int offsetrconde, float[] rcondv, int offsetrcondv, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgeevx.sgeevx(balanc, jobvl, jobvr, sense, n, a, offseta, lda, wr, offsetwr, wi, offsetwi, vl, offsetvl, ldvl, vr, offsetvr, ldvr, ilo, ihi, scale, offsetscale, abnrm, rconde, offsetrconde, rcondv, offsetrcondv, work, offsetwork, lwork, iwork, offsetiwork, info);
  }

  protected void sgegsK(String jobvsl, String jobvsr, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] vsl, int offsetvsl, int ldvsl, float[] vsr, int offsetvsr, int ldvsr, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgegs.sgegs(jobvsl, jobvsr, n, a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vsl, offsetvsl, ldvsl, vsr, offsetvsr, ldvsr, work, offsetwork, lwork, info);
  }

  protected void sgegvK(String jobvl, String jobvr, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgegv.sgegv(jobvl, jobvr, n, a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vl, offsetvl, ldvl, vr, offsetvr, ldvr, work, offsetwork, lwork, info);
  }

  protected void sgehd2K(int n, int ilo, int ihi, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgehd2.sgehd2(n, ilo, ihi, a, offseta, lda, tau, offsettau, work, offsetwork, info);
  }

  protected void sgehrdK(int n, int ilo, int ihi, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgehrd.sgehrd(n, ilo, ihi, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void sgelq2K(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgelq2.sgelq2(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, info);
  }

  protected void sgelqfK(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgelqf.sgelqf(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void sgelsK(String trans, int m, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgels.sgels(trans, m, n, nrhs, a, offseta, lda, b, offsetb, ldb, work, offsetwork, lwork, info);
  }

  protected void sgelsdK(int m, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] s, int offsets, float rcond, org.netlib.util.intW rank, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgelsd.sgelsd(m, n, nrhs, a, offseta, lda, b, offsetb, ldb, s, offsets, rcond, rank, work, offsetwork, lwork, iwork, offsetiwork, info);
  }

  protected void sgelssK(int m, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] s, int offsets, float rcond, org.netlib.util.intW rank, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgelss.sgelss(m, n, nrhs, a, offseta, lda, b, offsetb, ldb, s, offsets, rcond, rank, work, offsetwork, lwork, info);
  }

  protected void sgelsxK(int m, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, int[] jpvt, int offsetjpvt, float rcond, org.netlib.util.intW rank, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgelsx.sgelsx(m, n, nrhs, a, offseta, lda, b, offsetb, ldb, jpvt, offsetjpvt, rcond, rank, work, offsetwork, info);
  }

  protected void sgelsyK(int m, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, int[] jpvt, int offsetjpvt, float rcond, org.netlib.util.intW rank, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgelsy.sgelsy(m, n, nrhs, a, offseta, lda, b, offsetb, ldb, jpvt, offsetjpvt, rcond, rank, work, offsetwork, lwork, info);
  }

  protected void sgeql2K(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgeql2.sgeql2(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, info);
  }

  protected void sgeqlfK(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgeqlf.sgeqlf(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void sgeqp3K(int m, int n, float[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgeqp3.sgeqp3(m, n, a, offseta, lda, jpvt, offsetjpvt, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void sgeqpfK(int m, int n, float[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgeqpf.sgeqpf(m, n, a, offseta, lda, jpvt, offsetjpvt, tau, offsettau, work, offsetwork, info);
  }

  protected void sgeqr2K(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgeqr2.sgeqr2(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, info);
  }

  protected void sgeqrfK(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgeqrf.sgeqrf(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void sgerfsK(String trans, int n, int nrhs, float[] a, int offseta, int lda, float[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgerfs.sgerfs(trans, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void sgerq2K(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgerq2.sgerq2(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, info);
  }

  protected void sgerqfK(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgerqf.sgerqf(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void sgesc2K(int n, float[] a, int offseta, int lda, float[] rhs, int offsetrhs, int[] ipiv, int offsetipiv, int[] jpiv, int offsetjpiv, org.netlib.util.floatW scale) {
    org.netlib.lapack.Sgesc2.sgesc2(n, a, offseta, lda, rhs, offsetrhs, ipiv, offsetipiv, jpiv, offsetjpiv, scale);
  }

  protected void sgesddK(String jobz, int m, int n, float[] a, int offseta, int lda, float[] s, int offsets, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int ldvt, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgesdd.sgesdd(jobz, m, n, a, offseta, lda, s, offsets, u, offsetu, ldu, vt, offsetvt, ldvt, work, offsetwork, lwork, iwork, offsetiwork, info);
  }

  protected void sgesvK(int n, int nrhs, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Sgesv.sgesv(n, nrhs, a, offseta, lda, ipiv, offsetipiv, b, offsetb, ldb, info);
  }

  protected void sgesvdK(String jobu, String jobvt, int m, int n, float[] a, int offseta, int lda, float[] s, int offsets, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int ldvt, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgesvd.sgesvd(jobu, jobvt, m, n, a, offseta, lda, s, offsets, u, offsetu, ldu, vt, offsetvt, ldvt, work, offsetwork, lwork, info);
  }

  protected void sgesvxK(String fact, String trans, int n, int nrhs, float[] a, int offseta, int lda, float[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, org.netlib.util.StringW equed, float[] r, int offsetr, float[] c, int offsetc, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, org.netlib.util.floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgesvx.sgesvx(fact, trans, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, ipiv, offsetipiv, equed, r, offsetr, c, offsetc, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void sgetc2K(int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, int[] jpiv, int offsetjpiv, org.netlib.util.intW info) {
    org.netlib.lapack.Sgetc2.sgetc2(n, a, offseta, lda, ipiv, offsetipiv, jpiv, offsetjpiv, info);
  }

  protected void sgetf2K(int m, int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, org.netlib.util.intW info) {
    org.netlib.lapack.Sgetf2.sgetf2(m, n, a, offseta, lda, ipiv, offsetipiv, info);
  }

  protected void sgetrfK(int m, int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, org.netlib.util.intW info) {
    org.netlib.lapack.Sgetrf.sgetrf(m, n, a, offseta, lda, ipiv, offsetipiv, info);
  }

  protected void sgetriK(int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgetri.sgetri(n, a, offseta, lda, ipiv, offsetipiv, work, offsetwork, lwork, info);
  }

  protected void sgetrsK(String trans, int n, int nrhs, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Sgetrs.sgetrs(trans, n, nrhs, a, offseta, lda, ipiv, offsetipiv, b, offsetb, ldb, info);
  }

  protected void sggbakK(String job, String side, int n, int ilo, int ihi, float[] lscale, int offsetlscale, float[] rscale, int offsetrscale, int m, float[] v, int offsetv, int ldv, org.netlib.util.intW info) {
    org.netlib.lapack.Sggbak.sggbak(job, side, n, ilo, ihi, lscale, offsetlscale, rscale, offsetrscale, m, v, offsetv, ldv, info);
  }

  protected void sggbalK(String job, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, org.netlib.util.intW ilo, org.netlib.util.intW ihi, float[] lscale, int offsetlscale, float[] rscale, int offsetrscale, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sggbal.sggbal(job, n, a, offseta, lda, b, offsetb, ldb, ilo, ihi, lscale, offsetlscale, rscale, offsetrscale, work, offsetwork, info);
  }

  protected void sggesK(String jobvsl, String jobvsr, String sort, java.lang.Object selctg, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, org.netlib.util.intW sdim, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] vsl, int offsetvsl, int ldvsl, float[] vsr, int offsetvsr, int ldvsr, float[] work, int offsetwork, int lwork, boolean[] bwork, int offsetbwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgges.sgges(jobvsl, jobvsr, sort, selctg, n, a, offseta, lda, b, offsetb, ldb, sdim, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vsl, offsetvsl, ldvsl, vsr, offsetvsr, ldvsr, work, offsetwork, lwork, bwork, offsetbwork, info);
  }

  protected void sggesxK(String jobvsl, String jobvsr, String sort, java.lang.Object selctg, String sense, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, org.netlib.util.intW sdim, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] vsl, int offsetvsl, int ldvsl, float[] vsr, int offsetvsr, int ldvsr, float[] rconde, int offsetrconde, float[] rcondv, int offsetrcondv, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, boolean[] bwork, int offsetbwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sggesx.sggesx(jobvsl, jobvsr, sort, selctg, sense, n, a, offseta, lda, b, offsetb, ldb, sdim, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vsl, offsetvsl, ldvsl, vsr, offsetvsr, ldvsr, rconde, offsetrconde, rcondv, offsetrcondv, work, offsetwork, lwork, iwork, offsetiwork, liwork, bwork, offsetbwork, info);
  }

  protected void sggevK(String jobvl, String jobvr, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sggev.sggev(jobvl, jobvr, n, a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vl, offsetvl, ldvl, vr, offsetvr, ldvr, work, offsetwork, lwork, info);
  }

  protected void sggevxK(String balanc, String jobvl, String jobvr, String sense, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, org.netlib.util.intW ilo, org.netlib.util.intW ihi, float[] lscale, int offsetlscale, float[] rscale, int offsetrscale, org.netlib.util.floatW abnrm, org.netlib.util.floatW bbnrm, float[] rconde, int offsetrconde, float[] rcondv, int offsetrcondv, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, boolean[] bwork, int offsetbwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sggevx.sggevx(balanc, jobvl, jobvr, sense, n, a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vl, offsetvl, ldvl, vr, offsetvr, ldvr, ilo, ihi, lscale, offsetlscale, rscale, offsetrscale, abnrm, bbnrm, rconde, offsetrconde, rcondv, offsetrcondv, work, offsetwork, lwork, iwork, offsetiwork, bwork, offsetbwork, info);
  }

  protected void sggglmK(int n, int m, int p, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] d, int offsetd, float[] x, int offsetx, float[] y, int offsety, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sggglm.sggglm(n, m, p, a, offseta, lda, b, offsetb, ldb, d, offsetd, x, offsetx, y, offsety, work, offsetwork, lwork, info);
  }

  protected void sgghrdK(String compq, String compz, int n, int ilo, int ihi, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] q, int offsetq, int ldq, float[] z, int offsetz, int ldz, org.netlib.util.intW info) {
    org.netlib.lapack.Sgghrd.sgghrd(compq, compz, n, ilo, ihi, a, offseta, lda, b, offsetb, ldb, q, offsetq, ldq, z, offsetz, ldz, info);
  }

  protected void sgglseK(int m, int n, int p, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] c, int offsetc, float[] d, int offsetd, float[] x, int offsetx, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgglse.sgglse(m, n, p, a, offseta, lda, b, offsetb, ldb, c, offsetc, d, offsetd, x, offsetx, work, offsetwork, lwork, info);
  }

  protected void sggqrfK(int n, int m, int p, float[] a, int offseta, int lda, float[] taua, int offsettaua, float[] b, int offsetb, int ldb, float[] taub, int offsettaub, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sggqrf.sggqrf(n, m, p, a, offseta, lda, taua, offsettaua, b, offsetb, ldb, taub, offsettaub, work, offsetwork, lwork, info);
  }

  protected void sggrqfK(int m, int p, int n, float[] a, int offseta, int lda, float[] taua, int offsettaua, float[] b, int offsetb, int ldb, float[] taub, int offsettaub, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sggrqf.sggrqf(m, p, n, a, offseta, lda, taua, offsettaua, b, offsetb, ldb, taub, offsettaub, work, offsetwork, lwork, info);
  }

  protected void sggsvdK(String jobu, String jobv, String jobq, int m, int n, int p, org.netlib.util.intW k, org.netlib.util.intW l, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alpha, int offsetalpha, float[] beta, int offsetbeta, float[] u, int offsetu, int ldu, float[] v, int offsetv, int ldv, float[] q, int offsetq, int ldq, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sggsvd.sggsvd(jobu, jobv, jobq, m, n, p, k, l, a, offseta, lda, b, offsetb, ldb, alpha, offsetalpha, beta, offsetbeta, u, offsetu, ldu, v, offsetv, ldv, q, offsetq, ldq, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void sggsvpK(String jobu, String jobv, String jobq, int m, int p, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float tola, float tolb, org.netlib.util.intW k, org.netlib.util.intW l, float[] u, int offsetu, int ldu, float[] v, int offsetv, int ldv, float[] q, int offsetq, int ldq, int[] iwork, int offsetiwork, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sggsvp.sggsvp(jobu, jobv, jobq, m, p, n, a, offseta, lda, b, offsetb, ldb, tola, tolb, k, l, u, offsetu, ldu, v, offsetv, ldv, q, offsetq, ldq, iwork, offsetiwork, tau, offsettau, work, offsetwork, info);
  }

  protected void sgtconK(String norm, int n, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] du2, int offsetdu2, int[] ipiv, int offsetipiv, float anorm, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgtcon.sgtcon(norm, n, dl, offsetdl, d, offsetd, du, offsetdu, du2, offsetdu2, ipiv, offsetipiv, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void sgtrfsK(String trans, int n, int nrhs, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] dlf, int offsetdlf, float[] df, int offsetdf, float[] duf, int offsetduf, float[] du2, int offsetdu2, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgtrfs.sgtrfs(trans, n, nrhs, dl, offsetdl, d, offsetd, du, offsetdu, dlf, offsetdlf, df, offsetdf, duf, offsetduf, du2, offsetdu2, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void sgtsvK(int n, int nrhs, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Sgtsv.sgtsv(n, nrhs, dl, offsetdl, d, offsetd, du, offsetdu, b, offsetb, ldb, info);
  }

  protected void sgtsvxK(String fact, String trans, int n, int nrhs, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] dlf, int offsetdlf, float[] df, int offsetdf, float[] duf, int offsetduf, float[] du2, int offsetdu2, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, org.netlib.util.floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sgtsvx.sgtsvx(fact, trans, n, nrhs, dl, offsetdl, d, offsetd, du, offsetdu, dlf, offsetdlf, df, offsetdf, duf, offsetduf, du2, offsetdu2, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void sgttrfK(int n, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] du2, int offsetdu2, int[] ipiv, int offsetipiv, org.netlib.util.intW info) {
    org.netlib.lapack.Sgttrf.sgttrf(n, dl, offsetdl, d, offsetd, du, offsetdu, du2, offsetdu2, ipiv, offsetipiv, info);
  }

  protected void sgttrsK(String trans, int n, int nrhs, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] du2, int offsetdu2, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Sgttrs.sgttrs(trans, n, nrhs, dl, offsetdl, d, offsetd, du, offsetdu, du2, offsetdu2, ipiv, offsetipiv, b, offsetb, ldb, info);
  }

  protected void sgtts2K(int itrans, int n, int nrhs, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] du2, int offsetdu2, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb) {
    org.netlib.lapack.Sgtts2.sgtts2(itrans, n, nrhs, dl, offsetdl, d, offsetd, du, offsetdu, du2, offsetdu2, ipiv, offsetipiv, b, offsetb, ldb);
  }

  protected void shgeqzK(String job, String compq, String compz, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] t, int offsett, int ldt, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] q, int offsetq, int ldq, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Shgeqz.shgeqz(job, compq, compz, n, ilo, ihi, h, offseth, ldh, t, offsett, ldt, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, q, offsetq, ldq, z, offsetz, ldz, work, offsetwork, lwork, info);
  }

  protected void shseinK(String side, String eigsrc, String initv, boolean[] select, int offsetselect, int n, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, int mm, org.netlib.util.intW m, float[] work, int offsetwork, int[] ifaill, int offsetifaill, int[] ifailr, int offsetifailr, org.netlib.util.intW info) {
    org.netlib.lapack.Shsein.shsein(side, eigsrc, initv, select, offsetselect, n, h, offseth, ldh, wr, offsetwr, wi, offsetwi, vl, offsetvl, ldvl, vr, offsetvr, ldvr, mm, m, work, offsetwork, ifaill, offsetifaill, ifailr, offsetifailr, info);
  }

  protected void shseqrK(String job, String compz, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Shseqr.shseqr(job, compz, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, z, offsetz, ldz, work, offsetwork, lwork, info);
  }

  protected boolean sisnanK(float sin) {
    return org.netlib.lapack.Sisnan.sisnan(sin);
  }

  protected void slabadK(org.netlib.util.floatW small, org.netlib.util.floatW large) {
    org.netlib.lapack.Slabad.slabad(small, large);
  }

  protected void slabrdK(int m, int n, int nb, float[] a, int offseta, int lda, float[] d, int offsetd, float[] e, int offsete, float[] tauq, int offsettauq, float[] taup, int offsettaup, float[] x, int offsetx, int ldx, float[] y, int offsety, int ldy) {
    org.netlib.lapack.Slabrd.slabrd(m, n, nb, a, offseta, lda, d, offsetd, e, offsete, tauq, offsettauq, taup, offsettaup, x, offsetx, ldx, y, offsety, ldy);
  }

  protected void slacn2K(int n, float[] v, int offsetv, float[] x, int offsetx, int[] isgn, int offsetisgn, org.netlib.util.floatW est, org.netlib.util.intW kase, int[] isave, int offsetisave) {
    org.netlib.lapack.Slacn2.slacn2(n, v, offsetv, x, offsetx, isgn, offsetisgn, est, kase, isave, offsetisave);
  }

  protected void slaconK(int n, float[] v, int offsetv, float[] x, int offsetx, int[] isgn, int offsetisgn, org.netlib.util.floatW est, org.netlib.util.intW kase) {
    org.netlib.lapack.Slacon.slacon(n, v, offsetv, x, offsetx, isgn, offsetisgn, est, kase);
  }

  protected void slacpyK(String uplo, int m, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
    org.netlib.lapack.Slacpy.slacpy(uplo, m, n, a, offseta, lda, b, offsetb, ldb);
  }

  protected void sladivK(float a, float b, float c, float d, org.netlib.util.floatW p, org.netlib.util.floatW q) {
    org.netlib.lapack.Sladiv.sladiv(a, b, c, d, p, q);
  }

  protected void slae2K(float a, float b, float c, org.netlib.util.floatW rt1, org.netlib.util.floatW rt2) {
    org.netlib.lapack.Slae2.slae2(a, b, c, rt1, rt2);
  }

  protected void slaebzK(int ijob, int nitmax, int n, int mmax, int minp, int nbmin, float abstol, float reltol, float pivmin, float[] d, int offsetd, float[] e, int offsete, float[] e2, int offsete2, int[] nval, int offsetnval, float[] ab, int offsetab, float[] c, int offsetc, org.netlib.util.intW mout, int[] nab, int offsetnab, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slaebz.slaebz(ijob, nitmax, n, mmax, minp, nbmin, abstol, reltol, pivmin, d, offsetd, e, offsete, e2, offsete2, nval, offsetnval, ab, offsetab, c, offsetc, mout, nab, offsetnab, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void slaed0K(int icompq, int qsiz, int n, float[] d, int offsetd, float[] e, int offsete, float[] q, int offsetq, int ldq, float[] qstore, int offsetqstore, int ldqs, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slaed0.slaed0(icompq, qsiz, n, d, offsetd, e, offsete, q, offsetq, ldq, qstore, offsetqstore, ldqs, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void slaed1K(int n, float[] d, int offsetd, float[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, org.netlib.util.floatW rho, int cutpnt, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slaed1.slaed1(n, d, offsetd, q, offsetq, ldq, indxq, offsetindxq, rho, cutpnt, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void slaed2K(org.netlib.util.intW k, int n, int n1, float[] d, int offsetd, float[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, org.netlib.util.floatW rho, float[] z, int offsetz, float[] dlamda, int offsetdlamda, float[] w, int offsetw, float[] q2, int offsetq2, int[] indx, int offsetindx, int[] indxc, int offsetindxc, int[] indxp, int offsetindxp, int[] coltyp, int offsetcoltyp, org.netlib.util.intW info) {
    org.netlib.lapack.Slaed2.slaed2(k, n, n1, d, offsetd, q, offsetq, ldq, indxq, offsetindxq, rho, z, offsetz, dlamda, offsetdlamda, w, offsetw, q2, offsetq2, indx, offsetindx, indxc, offsetindxc, indxp, offsetindxp, coltyp, offsetcoltyp, info);
  }

  protected void slaed3K(int k, int n, int n1, float[] d, int offsetd, float[] q, int offsetq, int ldq, float rho, float[] dlamda, int offsetdlamda, float[] q2, int offsetq2, int[] indx, int offsetindx, int[] ctot, int offsetctot, float[] w, int offsetw, float[] s, int offsets, org.netlib.util.intW info) {
    org.netlib.lapack.Slaed3.slaed3(k, n, n1, d, offsetd, q, offsetq, ldq, rho, dlamda, offsetdlamda, q2, offsetq2, indx, offsetindx, ctot, offsetctot, w, offsetw, s, offsets, info);
  }

  protected void slaed4K(int n, int i, float[] d, int offsetd, float[] z, int offsetz, float[] delta, int offsetdelta, float rho, org.netlib.util.floatW dlam, org.netlib.util.intW info) {
    org.netlib.lapack.Slaed4.slaed4(n, i, d, offsetd, z, offsetz, delta, offsetdelta, rho, dlam, info);
  }

  protected void slaed5K(int i, float[] d, int offsetd, float[] z, int offsetz, float[] delta, int offsetdelta, float rho, org.netlib.util.floatW dlam) {
    org.netlib.lapack.Slaed5.slaed5(i, d, offsetd, z, offsetz, delta, offsetdelta, rho, dlam);
  }

  protected void slaed6K(int kniter, boolean orgati, float rho, float[] d, int offsetd, float[] z, int offsetz, float finit, org.netlib.util.floatW tau, org.netlib.util.intW info) {
    org.netlib.lapack.Slaed6.slaed6(kniter, orgati, rho, d, offsetd, z, offsetz, finit, tau, info);
  }

  protected void slaed7K(int icompq, int n, int qsiz, int tlvls, int curlvl, int curpbm, float[] d, int offsetd, float[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, org.netlib.util.floatW rho, int cutpnt, float[] qstore, int offsetqstore, int[] qptr, int offsetqptr, int[] prmptr, int offsetprmptr, int[] perm, int offsetperm, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, float[] givnum, int offsetgivnum, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slaed7.slaed7(icompq, n, qsiz, tlvls, curlvl, curpbm, d, offsetd, q, offsetq, ldq, indxq, offsetindxq, rho, cutpnt, qstore, offsetqstore, qptr, offsetqptr, prmptr, offsetprmptr, perm, offsetperm, givptr, offsetgivptr, givcol, offsetgivcol, givnum, offsetgivnum, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void slaed8K(int icompq, org.netlib.util.intW k, int n, int qsiz, float[] d, int offsetd, float[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, org.netlib.util.floatW rho, int cutpnt, float[] z, int offsetz, float[] dlamda, int offsetdlamda, float[] q2, int offsetq2, int ldq2, float[] w, int offsetw, int[] perm, int offsetperm, org.netlib.util.intW givptr, int[] givcol, int offsetgivcol, float[] givnum, int offsetgivnum, int[] indxp, int offsetindxp, int[] indx, int offsetindx, org.netlib.util.intW info) {
    org.netlib.lapack.Slaed8.slaed8(icompq, k, n, qsiz, d, offsetd, q, offsetq, ldq, indxq, offsetindxq, rho, cutpnt, z, offsetz, dlamda, offsetdlamda, q2, offsetq2, ldq2, w, offsetw, perm, offsetperm, givptr, givcol, offsetgivcol, givnum, offsetgivnum, indxp, offsetindxp, indx, offsetindx, info);
  }

  protected void slaed9K(int k, int kstart, int kstop, int n, float[] d, int offsetd, float[] q, int offsetq, int ldq, float rho, float[] dlamda, int offsetdlamda, float[] w, int offsetw, float[] s, int offsets, int lds, org.netlib.util.intW info) {
    org.netlib.lapack.Slaed9.slaed9(k, kstart, kstop, n, d, offsetd, q, offsetq, ldq, rho, dlamda, offsetdlamda, w, offsetw, s, offsets, lds, info);
  }

  protected void slaedaK(int n, int tlvls, int curlvl, int curpbm, int[] prmptr, int offsetprmptr, int[] perm, int offsetperm, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, float[] givnum, int offsetgivnum, float[] q, int offsetq, int[] qptr, int offsetqptr, float[] z, int offsetz, float[] ztemp, int offsetztemp, org.netlib.util.intW info) {
    org.netlib.lapack.Slaeda.slaeda(n, tlvls, curlvl, curpbm, prmptr, offsetprmptr, perm, offsetperm, givptr, offsetgivptr, givcol, offsetgivcol, givnum, offsetgivnum, q, offsetq, qptr, offsetqptr, z, offsetz, ztemp, offsetztemp, info);
  }

  protected void slaeinK(boolean rightv, boolean noinit, int n, float[] h, int offseth, int ldh, float wr, float wi, float[] vr, int offsetvr, float[] vi, int offsetvi, float[] b, int offsetb, int ldb, float[] work, int offsetwork, float eps3, float smlnum, float bignum, org.netlib.util.intW info) {
    org.netlib.lapack.Slaein.slaein(rightv, noinit, n, h, offseth, ldh, wr, wi, vr, offsetvr, vi, offsetvi, b, offsetb, ldb, work, offsetwork, eps3, smlnum, bignum, info);
  }

  protected void slaev2K(float a, float b, float c, org.netlib.util.floatW rt1, org.netlib.util.floatW rt2, org.netlib.util.floatW cs1, org.netlib.util.floatW sn1) {
    org.netlib.lapack.Slaev2.slaev2(a, b, c, rt1, rt2, cs1, sn1);
  }

  protected void slaexcK(boolean wantq, int n, float[] t, int offsett, int ldt, float[] q, int offsetq, int ldq, int j1, int n1, int n2, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slaexc.slaexc(wantq, n, t, offsett, ldt, q, offsetq, ldq, j1, n1, n2, work, offsetwork, info);
  }

  protected void slag2K(float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float safmin, org.netlib.util.floatW scale1, org.netlib.util.floatW scale2, org.netlib.util.floatW wr1, org.netlib.util.floatW wr2, org.netlib.util.floatW wi) {
    org.netlib.lapack.Slag2.slag2(a, offseta, lda, b, offsetb, ldb, safmin, scale1, scale2, wr1, wr2, wi);
  }

  protected void slag2dK(int m, int n, float[] sa, int offsetsa, int ldsa, double[] a, int offseta, int lda, org.netlib.util.intW info) {
    org.netlib.lapack.Slag2d.slag2d(m, n, sa, offsetsa, ldsa, a, offseta, lda, info);
  }

  protected void slags2K(boolean upper, float a1, float a2, float a3, float b1, float b2, float b3, org.netlib.util.floatW csu, org.netlib.util.floatW snu, org.netlib.util.floatW csv, org.netlib.util.floatW snv, org.netlib.util.floatW csq, org.netlib.util.floatW snq) {
    org.netlib.lapack.Slags2.slags2(upper, a1, a2, a3, b1, b2, b3, csu, snu, csv, snv, csq, snq);
  }

  protected void slagtfK(int n, float[] a, int offseta, float lambda, float[] b, int offsetb, float[] c, int offsetc, float tol, float[] d, int offsetd, int[] in, int offsetin, org.netlib.util.intW info) {
    org.netlib.lapack.Slagtf.slagtf(n, a, offseta, lambda, b, offsetb, c, offsetc, tol, d, offsetd, in, offsetin, info);
  }

  protected void slagtmK(String trans, int n, int nrhs, float alpha, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] x, int offsetx, int ldx, float beta, float[] b, int offsetb, int ldb) {
    org.netlib.lapack.Slagtm.slagtm(trans, n, nrhs, alpha, dl, offsetdl, d, offsetd, du, offsetdu, x, offsetx, ldx, beta, b, offsetb, ldb);
  }

  protected void slagtsK(int job, int n, float[] a, int offseta, float[] b, int offsetb, float[] c, int offsetc, float[] d, int offsetd, int[] in, int offsetin, float[] y, int offsety, org.netlib.util.floatW tol, org.netlib.util.intW info) {
    org.netlib.lapack.Slagts.slagts(job, n, a, offseta, b, offsetb, c, offsetc, d, offsetd, in, offsetin, y, offsety, tol, info);
  }

  protected void slagv2K(float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, org.netlib.util.floatW csl, org.netlib.util.floatW snl, org.netlib.util.floatW csr, org.netlib.util.floatW snr) {
    org.netlib.lapack.Slagv2.slagv2(a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, csl, snl, csr, snr);
  }

  protected void slahqrK(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, int iloz, int ihiz, float[] z, int offsetz, int ldz, org.netlib.util.intW info) {
    org.netlib.lapack.Slahqr.slahqr(wantt, wantz, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, iloz, ihiz, z, offsetz, ldz, info);
  }

  protected void slahr2K(int n, int k, int nb, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] t, int offsett, int ldt, float[] y, int offsety, int ldy) {
    org.netlib.lapack.Slahr2.slahr2(n, k, nb, a, offseta, lda, tau, offsettau, t, offsett, ldt, y, offsety, ldy);
  }

  protected void slahrdK(int n, int k, int nb, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] t, int offsett, int ldt, float[] y, int offsety, int ldy) {
    org.netlib.lapack.Slahrd.slahrd(n, k, nb, a, offseta, lda, tau, offsettau, t, offsett, ldt, y, offsety, ldy);
  }

  protected void slaic1K(int job, int j, float[] x, int offsetx, float sest, float[] w, int offsetw, float gamma, org.netlib.util.floatW sestpr, org.netlib.util.floatW s, org.netlib.util.floatW c) {
    org.netlib.lapack.Slaic1.slaic1(job, j, x, offsetx, sest, w, offsetw, gamma, sestpr, s, c);
  }

  protected boolean slaisnanK(float sin1, float sin2) {
    return org.netlib.lapack.Slaisnan.slaisnan(sin1, sin2);
  }

  protected void slaln2K(boolean ltrans, int na, int nw, float smin, float ca, float[] a, int offseta, int lda, float d1, float d2, float[] b, int offsetb, int ldb, float wr, float wi, float[] x, int offsetx, int ldx, org.netlib.util.floatW scale, org.netlib.util.floatW xnorm, org.netlib.util.intW info) {
    org.netlib.lapack.Slaln2.slaln2(ltrans, na, nw, smin, ca, a, offseta, lda, d1, d2, b, offsetb, ldb, wr, wi, x, offsetx, ldx, scale, xnorm, info);
  }

  protected void slals0K(int icompq, int nl, int nr, int sqre, int nrhs, float[] b, int offsetb, int ldb, float[] bx, int offsetbx, int ldbx, int[] perm, int offsetperm, int givptr, int[] givcol, int offsetgivcol, int ldgcol, float[] givnum, int offsetgivnum, int ldgnum, float[] poles, int offsetpoles, float[] difl, int offsetdifl, float[] difr, int offsetdifr, float[] z, int offsetz, int k, float c, float s, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slals0.slals0(icompq, nl, nr, sqre, nrhs, b, offsetb, ldb, bx, offsetbx, ldbx, perm, offsetperm, givptr, givcol, offsetgivcol, ldgcol, givnum, offsetgivnum, ldgnum, poles, offsetpoles, difl, offsetdifl, difr, offsetdifr, z, offsetz, k, c, s, work, offsetwork, info);
  }

  protected void slalsaK(int icompq, int smlsiz, int n, int nrhs, float[] b, int offsetb, int ldb, float[] bx, int offsetbx, int ldbx, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int[] k, int offsetk, float[] difl, int offsetdifl, float[] difr, int offsetdifr, float[] z, int offsetz, float[] poles, int offsetpoles, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, int ldgcol, int[] perm, int offsetperm, float[] givnum, int offsetgivnum, float[] c, int offsetc, float[] s, int offsets, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slalsa.slalsa(icompq, smlsiz, n, nrhs, b, offsetb, ldb, bx, offsetbx, ldbx, u, offsetu, ldu, vt, offsetvt, k, offsetk, difl, offsetdifl, difr, offsetdifr, z, offsetz, poles, offsetpoles, givptr, offsetgivptr, givcol, offsetgivcol, ldgcol, perm, offsetperm, givnum, offsetgivnum, c, offsetc, s, offsets, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void slalsdK(String uplo, int smlsiz, int n, int nrhs, float[] d, int offsetd, float[] e, int offsete, float[] b, int offsetb, int ldb, float rcond, org.netlib.util.intW rank, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slalsd.slalsd(uplo, smlsiz, n, nrhs, d, offsetd, e, offsete, b, offsetb, ldb, rcond, rank, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void slamrgK(int n1, int n2, float[] a, int offseta, int strd1, int strd2, int[] index, int offsetindex) {
    org.netlib.lapack.Slamrg.slamrg(n1, n2, a, offseta, strd1, strd2, index, offsetindex);
  }

  protected int slanegK(int n, float[] d, int offsetd, float[] lld, int offsetlld, float sigma, float pivmin, int r) {
    return org.netlib.lapack.Slaneg.slaneg(n, d, offsetd, lld, offsetlld, sigma, pivmin, r);
  }

  protected float slangbK(String norm, int n, int kl, int ku, float[] ab, int offsetab, int ldab, float[] work, int offsetwork) {
    return org.netlib.lapack.Slangb.slangb(norm, n, kl, ku, ab, offsetab, ldab, work, offsetwork);
  }

  protected float slangeK(String norm, int m, int n, float[] a, int offseta, int lda, float[] work, int offsetwork) {
    return org.netlib.lapack.Slange.slange(norm, m, n, a, offseta, lda, work, offsetwork);
  }

  protected float slangtK(String norm, int n, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu) {
    return org.netlib.lapack.Slangt.slangt(norm, n, dl, offsetdl, d, offsetd, du, offsetdu);
  }

  protected float slanhsK(String norm, int n, float[] a, int offseta, int lda, float[] work, int offsetwork) {
    return org.netlib.lapack.Slanhs.slanhs(norm, n, a, offseta, lda, work, offsetwork);
  }

  protected float slansbK(String norm, String uplo, int n, int k, float[] ab, int offsetab, int ldab, float[] work, int offsetwork) {
    return org.netlib.lapack.Slansb.slansb(norm, uplo, n, k, ab, offsetab, ldab, work, offsetwork);
  }

  protected float slanspK(String norm, String uplo, int n, float[] ap, int offsetap, float[] work, int offsetwork) {
    return org.netlib.lapack.Slansp.slansp(norm, uplo, n, ap, offsetap, work, offsetwork);
  }

  protected float slanstK(String norm, int n, float[] d, int offsetd, float[] e, int offsete) {
    return org.netlib.lapack.Slanst.slanst(norm, n, d, offsetd, e, offsete);
  }

  protected float slansyK(String norm, String uplo, int n, float[] a, int offseta, int lda, float[] work, int offsetwork) {
    return org.netlib.lapack.Slansy.slansy(norm, uplo, n, a, offseta, lda, work, offsetwork);
  }

  protected float slantbK(String norm, String uplo, String diag, int n, int k, float[] ab, int offsetab, int ldab, float[] work, int offsetwork) {
    return org.netlib.lapack.Slantb.slantb(norm, uplo, diag, n, k, ab, offsetab, ldab, work, offsetwork);
  }

  protected float slantpK(String norm, String uplo, String diag, int n, float[] ap, int offsetap, float[] work, int offsetwork) {
    return org.netlib.lapack.Slantp.slantp(norm, uplo, diag, n, ap, offsetap, work, offsetwork);
  }

  protected float slantrK(String norm, String uplo, String diag, int m, int n, float[] a, int offseta, int lda, float[] work, int offsetwork) {
    return org.netlib.lapack.Slantr.slantr(norm, uplo, diag, m, n, a, offseta, lda, work, offsetwork);
  }

  protected void slanv2K(org.netlib.util.floatW a, org.netlib.util.floatW b, org.netlib.util.floatW c, org.netlib.util.floatW d, org.netlib.util.floatW rt1r, org.netlib.util.floatW rt1i, org.netlib.util.floatW rt2r, org.netlib.util.floatW rt2i, org.netlib.util.floatW cs, org.netlib.util.floatW sn) {
    org.netlib.lapack.Slanv2.slanv2(a, b, c, d, rt1r, rt1i, rt2r, rt2i, cs, sn);
  }

  protected void slapllK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, org.netlib.util.floatW ssmin) {
    org.netlib.lapack.Slapll.slapll(n, x, offsetx, incx, y, offsety, incy, ssmin);
  }

  protected void slapmtK(boolean forwrd, int m, int n, float[] x, int offsetx, int ldx, int[] k, int offsetk) {
    org.netlib.lapack.Slapmt.slapmt(forwrd, m, n, x, offsetx, ldx, k, offsetk);
  }

  protected float slapy2K(float x, float y) {
    return org.netlib.lapack.Slapy2.slapy2(x, y);
  }

  protected float slapy3K(float x, float y, float z) {
    return org.netlib.lapack.Slapy3.slapy3(x, y, z);
  }

  protected void slaqgbK(int m, int n, int kl, int ku, float[] ab, int offsetab, int ldab, float[] r, int offsetr, float[] c, int offsetc, float rowcnd, float colcnd, float amax, org.netlib.util.StringW equed) {
    org.netlib.lapack.Slaqgb.slaqgb(m, n, kl, ku, ab, offsetab, ldab, r, offsetr, c, offsetc, rowcnd, colcnd, amax, equed);
  }

  protected void slaqgeK(int m, int n, float[] a, int offseta, int lda, float[] r, int offsetr, float[] c, int offsetc, float rowcnd, float colcnd, float amax, org.netlib.util.StringW equed) {
    org.netlib.lapack.Slaqge.slaqge(m, n, a, offseta, lda, r, offsetr, c, offsetc, rowcnd, colcnd, amax, equed);
  }

  protected void slaqp2K(int m, int n, int offset, float[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, float[] tau, int offsettau, float[] vn1, int offsetvn1, float[] vn2, int offsetvn2, float[] work, int offsetwork) {
    org.netlib.lapack.Slaqp2.slaqp2(m, n, offset, a, offseta, lda, jpvt, offsetjpvt, tau, offsettau, vn1, offsetvn1, vn2, offsetvn2, work, offsetwork);
  }

  protected void slaqpsK(int m, int n, int offset, int nb, org.netlib.util.intW kb, float[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, float[] tau, int offsettau, float[] vn1, int offsetvn1, float[] vn2, int offsetvn2, float[] auxv, int offsetauxv, float[] f, int offsetf, int ldf) {
    org.netlib.lapack.Slaqps.slaqps(m, n, offset, nb, kb, a, offseta, lda, jpvt, offsetjpvt, tau, offsettau, vn1, offsetvn1, vn2, offsetvn2, auxv, offsetauxv, f, offsetf, ldf);
  }

  protected void slaqr0K(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, int iloz, int ihiz, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slaqr0.slaqr0(wantt, wantz, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, iloz, ihiz, z, offsetz, ldz, work, offsetwork, lwork, info);
  }

  protected void slaqr1K(int n, float[] h, int offseth, int ldh, float sr1, float si1, float sr2, float si2, float[] v, int offsetv) {
    org.netlib.lapack.Slaqr1.slaqr1(n, h, offseth, ldh, sr1, si1, sr2, si2, v, offsetv);
  }

  protected void slaqr2K(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, float[] h, int offseth, int ldh, int iloz, int ihiz, float[] z, int offsetz, int ldz, org.netlib.util.intW ns, org.netlib.util.intW nd, float[] sr, int offsetsr, float[] si, int offsetsi, float[] v, int offsetv, int ldv, int nh, float[] t, int offsett, int ldt, int nv, float[] wv, int offsetwv, int ldwv, float[] work, int offsetwork, int lwork) {
    org.netlib.lapack.Slaqr2.slaqr2(wantt, wantz, n, ktop, kbot, nw, h, offseth, ldh, iloz, ihiz, z, offsetz, ldz, ns, nd, sr, offsetsr, si, offsetsi, v, offsetv, ldv, nh, t, offsett, ldt, nv, wv, offsetwv, ldwv, work, offsetwork, lwork);
  }

  protected void slaqr3K(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, float[] h, int offseth, int ldh, int iloz, int ihiz, float[] z, int offsetz, int ldz, org.netlib.util.intW ns, org.netlib.util.intW nd, float[] sr, int offsetsr, float[] si, int offsetsi, float[] v, int offsetv, int ldv, int nh, float[] t, int offsett, int ldt, int nv, float[] wv, int offsetwv, int ldwv, float[] work, int offsetwork, int lwork) {
    org.netlib.lapack.Slaqr3.slaqr3(wantt, wantz, n, ktop, kbot, nw, h, offseth, ldh, iloz, ihiz, z, offsetz, ldz, ns, nd, sr, offsetsr, si, offsetsi, v, offsetv, ldv, nh, t, offsett, ldt, nv, wv, offsetwv, ldwv, work, offsetwork, lwork);
  }

  protected void slaqr4K(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, int iloz, int ihiz, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slaqr4.slaqr4(wantt, wantz, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, iloz, ihiz, z, offsetz, ldz, work, offsetwork, lwork, info);
  }

  protected void slaqr5K(boolean wantt, boolean wantz, int kacc22, int n, int ktop, int kbot, int nshfts, float[] sr, int offsetsr, float[] si, int offsetsi, float[] h, int offseth, int ldh, int iloz, int ihiz, float[] z, int offsetz, int ldz, float[] v, int offsetv, int ldv, float[] u, int offsetu, int ldu, int nv, float[] wv, int offsetwv, int ldwv, int nh, float[] wh, int offsetwh, int ldwh) {
    org.netlib.lapack.Slaqr5.slaqr5(wantt, wantz, kacc22, n, ktop, kbot, nshfts, sr, offsetsr, si, offsetsi, h, offseth, ldh, iloz, ihiz, z, offsetz, ldz, v, offsetv, ldv, u, offsetu, ldu, nv, wv, offsetwv, ldwv, nh, wh, offsetwh, ldwh);
  }

  protected void slaqsbK(String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float[] s, int offsets, float scond, float amax, org.netlib.util.StringW equed) {
    org.netlib.lapack.Slaqsb.slaqsb(uplo, n, kd, ab, offsetab, ldab, s, offsets, scond, amax, equed);
  }

  protected void slaqspK(String uplo, int n, float[] ap, int offsetap, float[] s, int offsets, float scond, float amax, org.netlib.util.StringW equed) {
    org.netlib.lapack.Slaqsp.slaqsp(uplo, n, ap, offsetap, s, offsets, scond, amax, equed);
  }

  protected void slaqsyK(String uplo, int n, float[] a, int offseta, int lda, float[] s, int offsets, float scond, float amax, org.netlib.util.StringW equed) {
    org.netlib.lapack.Slaqsy.slaqsy(uplo, n, a, offseta, lda, s, offsets, scond, amax, equed);
  }

  protected void slaqtrK(boolean ltran, boolean lreal, int n, float[] t, int offsett, int ldt, float[] b, int offsetb, float w, org.netlib.util.floatW scale, float[] x, int offsetx, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slaqtr.slaqtr(ltran, lreal, n, t, offsett, ldt, b, offsetb, w, scale, x, offsetx, work, offsetwork, info);
  }

  protected void slar1vK(int n, int b1, int bn, float lambda, float[] d, int offsetd, float[] l, int offsetl, float[] ld, int offsetld, float[] lld, int offsetlld, float pivmin, float gaptol, float[] z, int offsetz, boolean wantnc, org.netlib.util.intW negcnt, org.netlib.util.floatW ztz, org.netlib.util.floatW mingma, org.netlib.util.intW r, int[] isuppz, int offsetisuppz, org.netlib.util.floatW nrminv, org.netlib.util.floatW resid, org.netlib.util.floatW rqcorr, float[] work, int offsetwork) {
    org.netlib.lapack.Slar1v.slar1v(n, b1, bn, lambda, d, offsetd, l, offsetl, ld, offsetld, lld, offsetlld, pivmin, gaptol, z, offsetz, wantnc, negcnt, ztz, mingma, r, isuppz, offsetisuppz, nrminv, resid, rqcorr, work, offsetwork);
  }

  protected void slar2vK(int n, float[] x, int offsetx, float[] y, int offsety, float[] z, int offsetz, int incx, float[] c, int offsetc, float[] s, int offsets, int incc) {
    org.netlib.lapack.Slar2v.slar2v(n, x, offsetx, y, offsety, z, offsetz, incx, c, offsetc, s, offsets, incc);
  }

  protected void slarfK(String side, int m, int n, float[] v, int offsetv, int incv, float tau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork) {
    org.netlib.lapack.Slarf.slarf(side, m, n, v, offsetv, incv, tau, c, offsetc, Ldc, work, offsetwork);
  }

  protected void slarfbK(String side, String trans, String direct, String storev, int m, int n, int k, float[] v, int offsetv, int ldv, float[] t, int offsett, int ldt, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int ldwork) {
    org.netlib.lapack.Slarfb.slarfb(side, trans, direct, storev, m, n, k, v, offsetv, ldv, t, offsett, ldt, c, offsetc, Ldc, work, offsetwork, ldwork);
  }

  protected void slarfgK(int n, org.netlib.util.floatW alpha, float[] x, int offsetx, int incx, org.netlib.util.floatW tau) {
    org.netlib.lapack.Slarfg.slarfg(n, alpha, x, offsetx, incx, tau);
  }

  protected void slarftK(String direct, String storev, int n, int k, float[] v, int offsetv, int ldv, float[] tau, int offsettau, float[] t, int offsett, int ldt) {
    org.netlib.lapack.Slarft.slarft(direct, storev, n, k, v, offsetv, ldv, tau, offsettau, t, offsett, ldt);
  }

  protected void slarfxK(String side, int m, int n, float[] v, int offsetv, float tau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork) {
    org.netlib.lapack.Slarfx.slarfx(side, m, n, v, offsetv, tau, c, offsetc, Ldc, work, offsetwork);
  }

  protected void slargvK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] c, int offsetc, int incc) {
    org.netlib.lapack.Slargv.slargv(n, x, offsetx, incx, y, offsety, incy, c, offsetc, incc);
  }

  protected void slarnvK(int idist, int[] iseed, int offsetiseed, int n, float[] x, int offsetx) {
    org.netlib.lapack.Slarnv.slarnv(idist, iseed, offsetiseed, n, x, offsetx);
  }

  protected void slarraK(int n, float[] d, int offsetd, float[] e, int offsete, float[] e2, int offsete2, float spltol, float tnrm, org.netlib.util.intW nsplit, int[] isplit, int offsetisplit, org.netlib.util.intW info) {
    org.netlib.lapack.Slarra.slarra(n, d, offsetd, e, offsete, e2, offsete2, spltol, tnrm, nsplit, isplit, offsetisplit, info);
  }

  protected void slarrbK(int n, float[] d, int offsetd, float[] lld, int offsetlld, int ifirst, int ilast, float rtol1, float rtol2, int offset, float[] w, int offsetw, float[] wgap, int offsetwgap, float[] werr, int offsetwerr, float[] work, int offsetwork, int[] iwork, int offsetiwork, float pivmin, float spdiam, int twist, org.netlib.util.intW info) {
    org.netlib.lapack.Slarrb.slarrb(n, d, offsetd, lld, offsetlld, ifirst, ilast, rtol1, rtol2, offset, w, offsetw, wgap, offsetwgap, werr, offsetwerr, work, offsetwork, iwork, offsetiwork, pivmin, spdiam, twist, info);
  }

  protected void slarrcK(String jobt, int n, float vl, float vu, float[] d, int offsetd, float[] e, int offsete, float pivmin, org.netlib.util.intW eigcnt, org.netlib.util.intW lcnt, org.netlib.util.intW rcnt, org.netlib.util.intW info) {
    org.netlib.lapack.Slarrc.slarrc(jobt, n, vl, vu, d, offsetd, e, offsete, pivmin, eigcnt, lcnt, rcnt, info);
  }

  protected void slarrdK(String range, String order, int n, float vl, float vu, int il, int iu, float[] gers, int offsetgers, float reltol, float[] d, int offsetd, float[] e, int offsete, float[] e2, int offsete2, float pivmin, int nsplit, int[] isplit, int offsetisplit, org.netlib.util.intW m, float[] w, int offsetw, float[] werr, int offsetwerr, org.netlib.util.floatW wl, org.netlib.util.floatW wu, int[] iblock, int offsetiblock, int[] indexw, int offsetindexw, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slarrd.slarrd(range, order, n, vl, vu, il, iu, gers, offsetgers, reltol, d, offsetd, e, offsete, e2, offsete2, pivmin, nsplit, isplit, offsetisplit, m, w, offsetw, werr, offsetwerr, wl, wu, iblock, offsetiblock, indexw, offsetindexw, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void slarreK(String range, int n, org.netlib.util.floatW vl, org.netlib.util.floatW vu, int il, int iu, float[] d, int offsetd, float[] e, int offsete, float[] e2, int offsete2, float rtol1, float rtol2, float spltol, org.netlib.util.intW nsplit, int[] isplit, int offsetisplit, org.netlib.util.intW m, float[] w, int offsetw, float[] werr, int offsetwerr, float[] wgap, int offsetwgap, int[] iblock, int offsetiblock, int[] indexw, int offsetindexw, float[] gers, int offsetgers, org.netlib.util.floatW pivmin, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slarre.slarre(range, n, vl, vu, il, iu, d, offsetd, e, offsete, e2, offsete2, rtol1, rtol2, spltol, nsplit, isplit, offsetisplit, m, w, offsetw, werr, offsetwerr, wgap, offsetwgap, iblock, offsetiblock, indexw, offsetindexw, gers, offsetgers, pivmin, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void slarrfK(int n, float[] d, int offsetd, float[] l, int offsetl, float[] ld, int offsetld, int clstrt, int clend, float[] w, int offsetw, float[] wgap, int offsetwgap, float[] werr, int offsetwerr, float spdiam, float clgapl, float clgapr, float pivmin, org.netlib.util.floatW sigma, float[] dplus, int offsetdplus, float[] lplus, int offsetlplus, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slarrf.slarrf(n, d, offsetd, l, offsetl, ld, offsetld, clstrt, clend, w, offsetw, wgap, offsetwgap, werr, offsetwerr, spdiam, clgapl, clgapr, pivmin, sigma, dplus, offsetdplus, lplus, offsetlplus, work, offsetwork, info);
  }

  protected void slarrjK(int n, float[] d, int offsetd, float[] e2, int offsete2, int ifirst, int ilast, float rtol, int offset, float[] w, int offsetw, float[] werr, int offsetwerr, float[] work, int offsetwork, int[] iwork, int offsetiwork, float pivmin, float spdiam, org.netlib.util.intW info) {
    org.netlib.lapack.Slarrj.slarrj(n, d, offsetd, e2, offsete2, ifirst, ilast, rtol, offset, w, offsetw, werr, offsetwerr, work, offsetwork, iwork, offsetiwork, pivmin, spdiam, info);
  }

  protected void slarrkK(int n, int iw, float gl, float gu, float[] d, int offsetd, float[] e2, int offsete2, float pivmin, float reltol, org.netlib.util.floatW w, org.netlib.util.floatW werr, org.netlib.util.intW info) {
    org.netlib.lapack.Slarrk.slarrk(n, iw, gl, gu, d, offsetd, e2, offsete2, pivmin, reltol, w, werr, info);
  }

  protected void slarrrK(int n, float[] d, int offsetd, float[] e, int offsete, org.netlib.util.intW info) {
    org.netlib.lapack.Slarrr.slarrr(n, d, offsetd, e, offsete, info);
  }

  protected void slarrvK(int n, float vl, float vu, float[] d, int offsetd, float[] l, int offsetl, float pivmin, int[] isplit, int offsetisplit, int m, int dol, int dou, float minrgp, org.netlib.util.floatW rtol1, org.netlib.util.floatW rtol2, float[] w, int offsetw, float[] werr, int offsetwerr, float[] wgap, int offsetwgap, int[] iblock, int offsetiblock, int[] indexw, int offsetindexw, float[] gers, int offsetgers, float[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slarrv.slarrv(n, vl, vu, d, offsetd, l, offsetl, pivmin, isplit, offsetisplit, m, dol, dou, minrgp, rtol1, rtol2, w, offsetw, werr, offsetwerr, wgap, offsetwgap, iblock, offsetiblock, indexw, offsetindexw, gers, offsetgers, z, offsetz, ldz, isuppz, offsetisuppz, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void slartgK(float f, float g, org.netlib.util.floatW cs, org.netlib.util.floatW sn, org.netlib.util.floatW r) {
    org.netlib.lapack.Slartg.slartg(f, g, cs, sn, r);
  }

  protected void slartvK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] c, int offsetc, float[] s, int offsets, int incc) {
    org.netlib.lapack.Slartv.slartv(n, x, offsetx, incx, y, offsety, incy, c, offsetc, s, offsets, incc);
  }

  protected void slaruvK(int[] iseed, int offsetiseed, int n, float[] x, int offsetx) {
    org.netlib.lapack.Slaruv.slaruv(iseed, offsetiseed, n, x, offsetx);
  }

  protected void slarzK(String side, int m, int n, int l, float[] v, int offsetv, int incv, float tau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork) {
    org.netlib.lapack.Slarz.slarz(side, m, n, l, v, offsetv, incv, tau, c, offsetc, Ldc, work, offsetwork);
  }

  protected void slarzbK(String side, String trans, String direct, String storev, int m, int n, int k, int l, float[] v, int offsetv, int ldv, float[] t, int offsett, int ldt, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int ldwork) {
    org.netlib.lapack.Slarzb.slarzb(side, trans, direct, storev, m, n, k, l, v, offsetv, ldv, t, offsett, ldt, c, offsetc, Ldc, work, offsetwork, ldwork);
  }

  protected void slarztK(String direct, String storev, int n, int k, float[] v, int offsetv, int ldv, float[] tau, int offsettau, float[] t, int offsett, int ldt) {
    org.netlib.lapack.Slarzt.slarzt(direct, storev, n, k, v, offsetv, ldv, tau, offsettau, t, offsett, ldt);
  }

  protected void slas2K(float f, float g, float h, org.netlib.util.floatW ssmin, org.netlib.util.floatW ssmax) {
    org.netlib.lapack.Slas2.slas2(f, g, h, ssmin, ssmax);
  }

  protected void slasclK(String type, int kl, int ku, float cfrom, float cto, int m, int n, float[] a, int offseta, int lda, org.netlib.util.intW info) {
    org.netlib.lapack.Slascl.slascl(type, kl, ku, cfrom, cto, m, n, a, offseta, lda, info);
  }

  protected void slasd0K(int n, int sqre, float[] d, int offsetd, float[] e, int offsete, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int ldvt, int smlsiz, int[] iwork, int offsetiwork, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slasd0.slasd0(n, sqre, d, offsetd, e, offsete, u, offsetu, ldu, vt, offsetvt, ldvt, smlsiz, iwork, offsetiwork, work, offsetwork, info);
  }

  protected void slasd1K(int nl, int nr, int sqre, float[] d, int offsetd, org.netlib.util.floatW alpha, org.netlib.util.floatW beta, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int ldvt, int[] idxq, int offsetidxq, int[] iwork, int offsetiwork, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slasd1.slasd1(nl, nr, sqre, d, offsetd, alpha, beta, u, offsetu, ldu, vt, offsetvt, ldvt, idxq, offsetidxq, iwork, offsetiwork, work, offsetwork, info);
  }

  protected void slasd2K(int nl, int nr, int sqre, org.netlib.util.intW k, float[] d, int offsetd, float[] z, int offsetz, float alpha, float beta, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int ldvt, float[] dsigma, int offsetdsigma, float[] u2, int offsetu2, int ldu2, float[] vt2, int offsetvt2, int ldvt2, int[] idxp, int offsetidxp, int[] idx, int offsetidx, int[] idxc, int offsetidxc, int[] idxq, int offsetidxq, int[] coltyp, int offsetcoltyp, org.netlib.util.intW info) {
    org.netlib.lapack.Slasd2.slasd2(nl, nr, sqre, k, d, offsetd, z, offsetz, alpha, beta, u, offsetu, ldu, vt, offsetvt, ldvt, dsigma, offsetdsigma, u2, offsetu2, ldu2, vt2, offsetvt2, ldvt2, idxp, offsetidxp, idx, offsetidx, idxc, offsetidxc, idxq, offsetidxq, coltyp, offsetcoltyp, info);
  }

  protected void slasd3K(int nl, int nr, int sqre, int k, float[] d, int offsetd, float[] q, int offsetq, int ldq, float[] dsigma, int offsetdsigma, float[] u, int offsetu, int ldu, float[] u2, int offsetu2, int ldu2, float[] vt, int offsetvt, int ldvt, float[] vt2, int offsetvt2, int ldvt2, int[] idxc, int offsetidxc, int[] ctot, int offsetctot, float[] z, int offsetz, org.netlib.util.intW info) {
    org.netlib.lapack.Slasd3.slasd3(nl, nr, sqre, k, d, offsetd, q, offsetq, ldq, dsigma, offsetdsigma, u, offsetu, ldu, u2, offsetu2, ldu2, vt, offsetvt, ldvt, vt2, offsetvt2, ldvt2, idxc, offsetidxc, ctot, offsetctot, z, offsetz, info);
  }

  protected void slasd4K(int n, int i, float[] d, int offsetd, float[] z, int offsetz, float[] delta, int offsetdelta, float rho, org.netlib.util.floatW sigma, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slasd4.slasd4(n, i, d, offsetd, z, offsetz, delta, offsetdelta, rho, sigma, work, offsetwork, info);
  }

  protected void slasd5K(int i, float[] d, int offsetd, float[] z, int offsetz, float[] delta, int offsetdelta, float rho, org.netlib.util.floatW dsigma, float[] work, int offsetwork) {
    org.netlib.lapack.Slasd5.slasd5(i, d, offsetd, z, offsetz, delta, offsetdelta, rho, dsigma, work, offsetwork);
  }

  protected void slasd6K(int icompq, int nl, int nr, int sqre, float[] d, int offsetd, float[] vf, int offsetvf, float[] vl, int offsetvl, org.netlib.util.floatW alpha, org.netlib.util.floatW beta, int[] idxq, int offsetidxq, int[] perm, int offsetperm, org.netlib.util.intW givptr, int[] givcol, int offsetgivcol, int ldgcol, float[] givnum, int offsetgivnum, int ldgnum, float[] poles, int offsetpoles, float[] difl, int offsetdifl, float[] difr, int offsetdifr, float[] z, int offsetz, org.netlib.util.intW k, org.netlib.util.floatW c, org.netlib.util.floatW s, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slasd6.slasd6(icompq, nl, nr, sqre, d, offsetd, vf, offsetvf, vl, offsetvl, alpha, beta, idxq, offsetidxq, perm, offsetperm, givptr, givcol, offsetgivcol, ldgcol, givnum, offsetgivnum, ldgnum, poles, offsetpoles, difl, offsetdifl, difr, offsetdifr, z, offsetz, k, c, s, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void slasd7K(int icompq, int nl, int nr, int sqre, org.netlib.util.intW k, float[] d, int offsetd, float[] z, int offsetz, float[] zw, int offsetzw, float[] vf, int offsetvf, float[] vfw, int offsetvfw, float[] vl, int offsetvl, float[] vlw, int offsetvlw, float alpha, float beta, float[] dsigma, int offsetdsigma, int[] idx, int offsetidx, int[] idxp, int offsetidxp, int[] idxq, int offsetidxq, int[] perm, int offsetperm, org.netlib.util.intW givptr, int[] givcol, int offsetgivcol, int ldgcol, float[] givnum, int offsetgivnum, int ldgnum, org.netlib.util.floatW c, org.netlib.util.floatW s, org.netlib.util.intW info) {
    org.netlib.lapack.Slasd7.slasd7(icompq, nl, nr, sqre, k, d, offsetd, z, offsetz, zw, offsetzw, vf, offsetvf, vfw, offsetvfw, vl, offsetvl, vlw, offsetvlw, alpha, beta, dsigma, offsetdsigma, idx, offsetidx, idxp, offsetidxp, idxq, offsetidxq, perm, offsetperm, givptr, givcol, offsetgivcol, ldgcol, givnum, offsetgivnum, ldgnum, c, s, info);
  }

  protected void slasd8K(int icompq, int k, float[] d, int offsetd, float[] z, int offsetz, float[] vf, int offsetvf, float[] vl, int offsetvl, float[] difl, int offsetdifl, float[] difr, int offsetdifr, int lddifr, float[] dsigma, int offsetdsigma, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slasd8.slasd8(icompq, k, d, offsetd, z, offsetz, vf, offsetvf, vl, offsetvl, difl, offsetdifl, difr, offsetdifr, lddifr, dsigma, offsetdsigma, work, offsetwork, info);
  }

  protected void slasdaK(int icompq, int smlsiz, int n, int sqre, float[] d, int offsetd, float[] e, int offsete, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int[] k, int offsetk, float[] difl, int offsetdifl, float[] difr, int offsetdifr, float[] z, int offsetz, float[] poles, int offsetpoles, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, int ldgcol, int[] perm, int offsetperm, float[] givnum, int offsetgivnum, float[] c, int offsetc, float[] s, int offsets, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slasda.slasda(icompq, smlsiz, n, sqre, d, offsetd, e, offsete, u, offsetu, ldu, vt, offsetvt, k, offsetk, difl, offsetdifl, difr, offsetdifr, z, offsetz, poles, offsetpoles, givptr, offsetgivptr, givcol, offsetgivcol, ldgcol, perm, offsetperm, givnum, offsetgivnum, c, offsetc, s, offsets, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void slasdqK(String uplo, int sqre, int n, int ncvt, int nru, int ncc, float[] d, int offsetd, float[] e, int offsete, float[] vt, int offsetvt, int ldvt, float[] u, int offsetu, int ldu, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slasdq.slasdq(uplo, sqre, n, ncvt, nru, ncc, d, offsetd, e, offsete, vt, offsetvt, ldvt, u, offsetu, ldu, c, offsetc, Ldc, work, offsetwork, info);
  }

  protected void slasdtK(int n, org.netlib.util.intW lvl, org.netlib.util.intW nd, int[] inode, int offsetinode, int[] ndiml, int offsetndiml, int[] ndimr, int offsetndimr, int msub) {
    org.netlib.lapack.Slasdt.slasdt(n, lvl, nd, inode, offsetinode, ndiml, offsetndiml, ndimr, offsetndimr, msub);
  }

  protected void slasetK(String uplo, int m, int n, float alpha, float beta, float[] a, int offseta, int lda) {
    org.netlib.lapack.Slaset.slaset(uplo, m, n, alpha, beta, a, offseta, lda);
  }

  protected void slasq1K(int n, float[] d, int offsetd, float[] e, int offsete, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Slasq1.slasq1(n, d, offsetd, e, offsete, work, offsetwork, info);
  }

  protected void slasq2K(int n, float[] z, int offsetz, org.netlib.util.intW info) {
    org.netlib.lapack.Slasq2.slasq2(n, z, offsetz, info);
  }

  protected void slasq3K(int i0, org.netlib.util.intW n0, float[] z, int offsetz, int pp, org.netlib.util.floatW dmin, org.netlib.util.floatW sigma, org.netlib.util.floatW desig, org.netlib.util.floatW qmax, org.netlib.util.intW nfail, org.netlib.util.intW iter, org.netlib.util.intW ndiv, boolean ieee, org.netlib.util.intW ttype, org.netlib.util.floatW dmin1, org.netlib.util.floatW dmin2, org.netlib.util.floatW dn, org.netlib.util.floatW dn1, org.netlib.util.floatW dn2, org.netlib.util.floatW g, org.netlib.util.floatW tau) {
    // F2j still uses old signature - ignore new parameters for now
    org.netlib.lapack.Slasq3.slasq3(i0, n0, z, offsetz, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee);
  }

  protected void slasq4K(int i0, int n0, float[] z, int offsetz, int pp, int n0in, float dmin, float dmin1, float dmin2, float dn, float dn1, float dn2, org.netlib.util.floatW tau, org.netlib.util.intW ttype, org.netlib.util.floatW g) {
    org.netlib.lapack.Slasq4.slasq4(i0, n0, z, offsetz, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype);
  }

  protected void slasq5K(int i0, int n0, float[] z, int offsetz, int pp, float tau, float sigma, org.netlib.util.floatW dmin, org.netlib.util.floatW dmin1, org.netlib.util.floatW dmin2, org.netlib.util.floatW dn, org.netlib.util.floatW dnm1, org.netlib.util.floatW dnm2, boolean ieee, float eps) {
    // F2j still uses old signature - ignore sigma and eps parameters for now
    org.netlib.lapack.Slasq5.slasq5(i0, n0, z, offsetz, pp, tau, dmin, dmin1, dmin2, dn, dnm1, dnm2, ieee);
  }

  protected void slasq6K(int i0, int n0, float[] z, int offsetz, int pp, org.netlib.util.floatW dmin, org.netlib.util.floatW dmin1, org.netlib.util.floatW dmin2, org.netlib.util.floatW dn, org.netlib.util.floatW dnm1, org.netlib.util.floatW dnm2) {
    org.netlib.lapack.Slasq6.slasq6(i0, n0, z, offsetz, pp, dmin, dmin1, dmin2, dn, dnm1, dnm2);
  }

  protected void slasrK(String side, String pivot, String direct, int m, int n, float[] c, int offsetc, float[] s, int offsets, float[] a, int offseta, int lda) {
    org.netlib.lapack.Slasr.slasr(side, pivot, direct, m, n, c, offsetc, s, offsets, a, offseta, lda);
  }

  protected void slasrtK(String id, int n, float[] d, int offsetd, org.netlib.util.intW info) {
    org.netlib.lapack.Slasrt.slasrt(id, n, d, offsetd, info);
  }

  protected void slassqK(int n, float[] x, int offsetx, int incx, org.netlib.util.floatW scale, org.netlib.util.floatW sumsq) {
    org.netlib.lapack.Slassq.slassq(n, x, offsetx, incx, scale, sumsq);
  }

  protected void slasv2K(float f, float g, float h, org.netlib.util.floatW ssmin, org.netlib.util.floatW ssmax, org.netlib.util.floatW snr, org.netlib.util.floatW csr, org.netlib.util.floatW snl, org.netlib.util.floatW csl) {
    org.netlib.lapack.Slasv2.slasv2(f, g, h, ssmin, ssmax, snr, csr, snl, csl);
  }

  protected void slaswpK(int n, float[] a, int offseta, int lda, int k1, int k2, int[] ipiv, int offsetipiv, int incx) {
    org.netlib.lapack.Slaswp.slaswp(n, a, offseta, lda, k1, k2, ipiv, offsetipiv, incx);
  }

  protected void slasy2K(boolean ltranl, boolean ltranr, int isgn, int n1, int n2, float[] tl, int offsettl, int ldtl, float[] tr, int offsettr, int ldtr, float[] b, int offsetb, int ldb, org.netlib.util.floatW scale, float[] x, int offsetx, int ldx, org.netlib.util.floatW xnorm, org.netlib.util.intW info) {
    org.netlib.lapack.Slasy2.slasy2(ltranl, ltranr, isgn, n1, n2, tl, offsettl, ldtl, tr, offsettr, ldtr, b, offsetb, ldb, scale, x, offsetx, ldx, xnorm, info);
  }

  protected void slasyfK(String uplo, int n, int nb, org.netlib.util.intW kb, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] w, int offsetw, int ldw, org.netlib.util.intW info) {
    org.netlib.lapack.Slasyf.slasyf(uplo, n, nb, kb, a, offseta, lda, ipiv, offsetipiv, w, offsetw, ldw, info);
  }

  protected void slatbsK(String uplo, String trans, String diag, String normin, int n, int kd, float[] ab, int offsetab, int ldab, float[] x, int offsetx, org.netlib.util.floatW scale, float[] cnorm, int offsetcnorm, org.netlib.util.intW info) {
    org.netlib.lapack.Slatbs.slatbs(uplo, trans, diag, normin, n, kd, ab, offsetab, ldab, x, offsetx, scale, cnorm, offsetcnorm, info);
  }

  protected void slatdfK(int ijob, int n, float[] z, int offsetz, int ldz, float[] rhs, int offsetrhs, org.netlib.util.floatW rdsum, org.netlib.util.floatW rdscal, int[] ipiv, int offsetipiv, int[] jpiv, int offsetjpiv) {
    org.netlib.lapack.Slatdf.slatdf(ijob, n, z, offsetz, ldz, rhs, offsetrhs, rdsum, rdscal, ipiv, offsetipiv, jpiv, offsetjpiv);
  }

  protected void slatpsK(String uplo, String trans, String diag, String normin, int n, float[] ap, int offsetap, float[] x, int offsetx, org.netlib.util.floatW scale, float[] cnorm, int offsetcnorm, org.netlib.util.intW info) {
    org.netlib.lapack.Slatps.slatps(uplo, trans, diag, normin, n, ap, offsetap, x, offsetx, scale, cnorm, offsetcnorm, info);
  }

  protected void slatrdK(String uplo, int n, int nb, float[] a, int offseta, int lda, float[] e, int offsete, float[] tau, int offsettau, float[] w, int offsetw, int ldw) {
    org.netlib.lapack.Slatrd.slatrd(uplo, n, nb, a, offseta, lda, e, offsete, tau, offsettau, w, offsetw, ldw);
  }

  protected void slatrsK(String uplo, String trans, String diag, String normin, int n, float[] a, int offseta, int lda, float[] x, int offsetx, org.netlib.util.floatW scale, float[] cnorm, int offsetcnorm, org.netlib.util.intW info) {
    org.netlib.lapack.Slatrs.slatrs(uplo, trans, diag, normin, n, a, offseta, lda, x, offsetx, scale, cnorm, offsetcnorm, info);
  }

  protected void slatrzK(int m, int n, int l, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork) {
    org.netlib.lapack.Slatrz.slatrz(m, n, l, a, offseta, lda, tau, offsettau, work, offsetwork);
  }

  protected void slatzmK(String side, int m, int n, float[] v, int offsetv, int incv, float tau, float[] c1, int offsetc1, float[] c2, int offsetc2, int Ldc, float[] work, int offsetwork) {
    org.netlib.lapack.Slatzm.slatzm(side, m, n, v, offsetv, incv, tau, c1, offsetc1, c2, offsetc2, Ldc, work, offsetwork);
  }

  protected void slauu2K(String uplo, int n, float[] a, int offseta, int lda, org.netlib.util.intW info) {
    org.netlib.lapack.Slauu2.slauu2(uplo, n, a, offseta, lda, info);
  }

  protected void slauumK(String uplo, int n, float[] a, int offseta, int lda, org.netlib.util.intW info) {
    org.netlib.lapack.Slauum.slauum(uplo, n, a, offseta, lda, info);
  }

  protected void slazq3K(int i0, org.netlib.util.intW n0, float[] z, int offsetz, int pp, org.netlib.util.floatW dmin, org.netlib.util.floatW sigma, org.netlib.util.floatW desig, org.netlib.util.floatW qmax, org.netlib.util.intW nfail, org.netlib.util.intW iter, org.netlib.util.intW ndiv, boolean ieee, org.netlib.util.intW ttype, org.netlib.util.floatW dmin1, org.netlib.util.floatW dmin2, org.netlib.util.floatW dn, org.netlib.util.floatW dn1, org.netlib.util.floatW dn2, org.netlib.util.floatW tau) {
    org.netlib.lapack.Slazq3.slazq3(i0, n0, z, offsetz, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee, ttype, dmin1, dmin2, dn, dn1, dn2, tau);
  }

  protected void slazq4K(int i0, int n0, float[] z, int offsetz, int pp, int n0in, float dmin, float dmin1, float dmin2, float dn, float dn1, float dn2, org.netlib.util.floatW tau, org.netlib.util.intW ttype, org.netlib.util.floatW g) {
    org.netlib.lapack.Slazq4.slazq4(i0, n0, z, offsetz, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype, g);
  }

  protected void sopgtrK(String uplo, int n, float[] ap, int offsetap, float[] tau, int offsettau, float[] q, int offsetq, int ldq, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sopgtr.sopgtr(uplo, n, ap, offsetap, tau, offsettau, q, offsetq, ldq, work, offsetwork, info);
  }

  protected void sopmtrK(String side, String uplo, String trans, int m, int n, float[] ap, int offsetap, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sopmtr.sopmtr(side, uplo, trans, m, n, ap, offsetap, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
  }

  protected void sorg2lK(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sorg2l.sorg2l(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, info);
  }

  protected void sorg2rK(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sorg2r.sorg2r(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, info);
  }

  protected void sorgbrK(String vect, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sorgbr.sorgbr(vect, m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void sorghrK(int n, int ilo, int ihi, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sorghr.sorghr(n, ilo, ihi, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void sorgl2K(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sorgl2.sorgl2(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, info);
  }

  protected void sorglqK(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sorglq.sorglq(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void sorgqlK(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sorgql.sorgql(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void sorgqrK(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sorgqr.sorgqr(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void sorgr2K(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sorgr2.sorgr2(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, info);
  }

  protected void sorgrqK(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sorgrq.sorgrq(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void sorgtrK(String uplo, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sorgtr.sorgtr(uplo, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void sorm2lK(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sorm2l.sorm2l(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
  }

  protected void sorm2rK(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sorm2r.sorm2r(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
  }

  protected void sormbrK(String vect, String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sormbr.sormbr(vect, side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
  }

  protected void sormhrK(String side, String trans, int m, int n, int ilo, int ihi, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sormhr.sormhr(side, trans, m, n, ilo, ihi, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
  }

  protected void sorml2K(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sorml2.sorml2(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
  }

  protected void sormlqK(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sormlq.sormlq(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
  }

  protected void sormqlK(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sormql.sormql(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
  }

  protected void sormqrK(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sormqr.sormqr(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
  }

  protected void sormr2K(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sormr2.sormr2(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
  }

  protected void sormr3K(String side, String trans, int m, int n, int k, int l, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sormr3.sormr3(side, trans, m, n, k, l, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
  }

  protected void sormrqK(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sormrq.sormrq(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
  }

  protected void sormrzK(String side, String trans, int m, int n, int k, int l, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sormrz.sormrz(side, trans, m, n, k, l, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
  }

  protected void sormtrK(String side, String uplo, String trans, int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sormtr.sormtr(side, uplo, trans, m, n, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
  }

  protected void spbconK(String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float anorm, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Spbcon.spbcon(uplo, n, kd, ab, offsetab, ldab, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void spbequK(String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float[] s, int offsets, org.netlib.util.floatW scond, org.netlib.util.floatW amax, org.netlib.util.intW info) {
    org.netlib.lapack.Spbequ.spbequ(uplo, n, kd, ab, offsetab, ldab, s, offsets, scond, amax, info);
  }

  protected void spbrfsK(String uplo, int n, int kd, int nrhs, float[] ab, int offsetab, int ldab, float[] afb, int offsetafb, int ldafb, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Spbrfs.spbrfs(uplo, n, kd, nrhs, ab, offsetab, ldab, afb, offsetafb, ldafb, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void spbstfK(String uplo, int n, int kd, float[] ab, int offsetab, int ldab, org.netlib.util.intW info) {
    org.netlib.lapack.Spbstf.spbstf(uplo, n, kd, ab, offsetab, ldab, info);
  }

  protected void spbsvK(String uplo, int n, int kd, int nrhs, float[] ab, int offsetab, int ldab, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Spbsv.spbsv(uplo, n, kd, nrhs, ab, offsetab, ldab, b, offsetb, ldb, info);
  }

  protected void spbsvxK(String fact, String uplo, int n, int kd, int nrhs, float[] ab, int offsetab, int ldab, float[] afb, int offsetafb, int ldafb, org.netlib.util.StringW equed, float[] s, int offsets, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, org.netlib.util.floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Spbsvx.spbsvx(fact, uplo, n, kd, nrhs, ab, offsetab, ldab, afb, offsetafb, ldafb, equed, s, offsets, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void spbtf2K(String uplo, int n, int kd, float[] ab, int offsetab, int ldab, org.netlib.util.intW info) {
    org.netlib.lapack.Spbtf2.spbtf2(uplo, n, kd, ab, offsetab, ldab, info);
  }

  protected void spbtrfK(String uplo, int n, int kd, float[] ab, int offsetab, int ldab, org.netlib.util.intW info) {
    org.netlib.lapack.Spbtrf.spbtrf(uplo, n, kd, ab, offsetab, ldab, info);
  }

  protected void spbtrsK(String uplo, int n, int kd, int nrhs, float[] ab, int offsetab, int ldab, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Spbtrs.spbtrs(uplo, n, kd, nrhs, ab, offsetab, ldab, b, offsetb, ldb, info);
  }

  protected void spoconK(String uplo, int n, float[] a, int offseta, int lda, float anorm, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Spocon.spocon(uplo, n, a, offseta, lda, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void spoequK(int n, float[] a, int offseta, int lda, float[] s, int offsets, org.netlib.util.floatW scond, org.netlib.util.floatW amax, org.netlib.util.intW info) {
    org.netlib.lapack.Spoequ.spoequ(n, a, offseta, lda, s, offsets, scond, amax, info);
  }

  protected void sporfsK(String uplo, int n, int nrhs, float[] a, int offseta, int lda, float[] af, int offsetaf, int ldaf, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sporfs.sporfs(uplo, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void sposvK(String uplo, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Sposv.sposv(uplo, n, nrhs, a, offseta, lda, b, offsetb, ldb, info);
  }

  protected void sposvxK(String fact, String uplo, int n, int nrhs, float[] a, int offseta, int lda, float[] af, int offsetaf, int ldaf, org.netlib.util.StringW equed, float[] s, int offsets, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, org.netlib.util.floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sposvx.sposvx(fact, uplo, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, equed, s, offsets, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void spotf2K(String uplo, int n, float[] a, int offseta, int lda, org.netlib.util.intW info) {
    org.netlib.lapack.Spotf2.spotf2(uplo, n, a, offseta, lda, info);
  }

  protected void spotrfK(String uplo, int n, float[] a, int offseta, int lda, org.netlib.util.intW info) {
    org.netlib.lapack.Spotrf.spotrf(uplo, n, a, offseta, lda, info);
  }

  protected void spotriK(String uplo, int n, float[] a, int offseta, int lda, org.netlib.util.intW info) {
    org.netlib.lapack.Spotri.spotri(uplo, n, a, offseta, lda, info);
  }

  protected void spotrsK(String uplo, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Spotrs.spotrs(uplo, n, nrhs, a, offseta, lda, b, offsetb, ldb, info);
  }

  protected void sppconK(String uplo, int n, float[] ap, int offsetap, float anorm, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sppcon.sppcon(uplo, n, ap, offsetap, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void sppequK(String uplo, int n, float[] ap, int offsetap, float[] s, int offsets, org.netlib.util.floatW scond, org.netlib.util.floatW amax, org.netlib.util.intW info) {
    org.netlib.lapack.Sppequ.sppequ(uplo, n, ap, offsetap, s, offsets, scond, amax, info);
  }

  protected void spprfsK(String uplo, int n, int nrhs, float[] ap, int offsetap, float[] afp, int offsetafp, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Spprfs.spprfs(uplo, n, nrhs, ap, offsetap, afp, offsetafp, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void sppsvK(String uplo, int n, int nrhs, float[] ap, int offsetap, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Sppsv.sppsv(uplo, n, nrhs, ap, offsetap, b, offsetb, ldb, info);
  }

  protected void sppsvxK(String fact, String uplo, int n, int nrhs, float[] ap, int offsetap, float[] afp, int offsetafp, org.netlib.util.StringW equed, float[] s, int offsets, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, org.netlib.util.floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sppsvx.sppsvx(fact, uplo, n, nrhs, ap, offsetap, afp, offsetafp, equed, s, offsets, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void spptrfK(String uplo, int n, float[] ap, int offsetap, org.netlib.util.intW info) {
    org.netlib.lapack.Spptrf.spptrf(uplo, n, ap, offsetap, info);
  }

  protected void spptriK(String uplo, int n, float[] ap, int offsetap, org.netlib.util.intW info) {
    org.netlib.lapack.Spptri.spptri(uplo, n, ap, offsetap, info);
  }

  protected void spptrsK(String uplo, int n, int nrhs, float[] ap, int offsetap, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Spptrs.spptrs(uplo, n, nrhs, ap, offsetap, b, offsetb, ldb, info);
  }

  protected void sptconK(int n, float[] d, int offsetd, float[] e, int offsete, float anorm, org.netlib.util.floatW rcond, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sptcon.sptcon(n, d, offsetd, e, offsete, anorm, rcond, work, offsetwork, info);
  }

  protected void spteqrK(String compz, int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, int ldz, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Spteqr.spteqr(compz, n, d, offsetd, e, offsete, z, offsetz, ldz, work, offsetwork, info);
  }

  protected void sptrfsK(int n, int nrhs, float[] d, int offsetd, float[] e, int offsete, float[] df, int offsetdf, float[] ef, int offsetef, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sptrfs.sptrfs(n, nrhs, d, offsetd, e, offsete, df, offsetdf, ef, offsetef, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, info);
  }

  protected void sptsvK(int n, int nrhs, float[] d, int offsetd, float[] e, int offsete, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Sptsv.sptsv(n, nrhs, d, offsetd, e, offsete, b, offsetb, ldb, info);
  }

  protected void sptsvxK(String fact, int n, int nrhs, float[] d, int offsetd, float[] e, int offsete, float[] df, int offsetdf, float[] ef, int offsetef, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, org.netlib.util.floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sptsvx.sptsvx(fact, n, nrhs, d, offsetd, e, offsete, df, offsetdf, ef, offsetef, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, info);
  }

  protected void spttrfK(int n, float[] d, int offsetd, float[] e, int offsete, org.netlib.util.intW info) {
    org.netlib.lapack.Spttrf.spttrf(n, d, offsetd, e, offsete, info);
  }

  protected void spttrsK(int n, int nrhs, float[] d, int offsetd, float[] e, int offsete, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Spttrs.spttrs(n, nrhs, d, offsetd, e, offsete, b, offsetb, ldb, info);
  }

  protected void sptts2K(int n, int nrhs, float[] d, int offsetd, float[] e, int offsete, float[] b, int offsetb, int ldb) {
    org.netlib.lapack.Sptts2.sptts2(n, nrhs, d, offsetd, e, offsete, b, offsetb, ldb);
  }

  protected void srsclK(int n, float sa, float[] sx, int offsetsx, int incx) {
    org.netlib.lapack.Srscl.srscl(n, sa, sx, offsetsx, incx);
  }

  protected void ssbevK(String jobz, String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssbev.ssbev(jobz, uplo, n, kd, ab, offsetab, ldab, w, offsetw, z, offsetz, ldz, work, offsetwork, info);
  }

  protected void ssbevdK(String jobz, String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssbevd.ssbevd(jobz, uplo, n, kd, ab, offsetab, ldab, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void ssbevxK(String jobz, String range, String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float[] q, int offsetq, int ldq, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info) {
    org.netlib.lapack.Ssbevx.ssbevx(jobz, range, uplo, n, kd, ab, offsetab, ldab, q, offsetq, ldq, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
  }

  protected void ssbgstK(String vect, String uplo, int n, int ka, int kb, float[] ab, int offsetab, int ldab, float[] bb, int offsetbb, int ldbb, float[] x, int offsetx, int ldx, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssbgst.ssbgst(vect, uplo, n, ka, kb, ab, offsetab, ldab, bb, offsetbb, ldbb, x, offsetx, ldx, work, offsetwork, info);
  }

  protected void ssbgvK(String jobz, String uplo, int n, int ka, int kb, float[] ab, int offsetab, int ldab, float[] bb, int offsetbb, int ldbb, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssbgv.ssbgv(jobz, uplo, n, ka, kb, ab, offsetab, ldab, bb, offsetbb, ldbb, w, offsetw, z, offsetz, ldz, work, offsetwork, info);
  }

  protected void ssbgvdK(String jobz, String uplo, int n, int ka, int kb, float[] ab, int offsetab, int ldab, float[] bb, int offsetbb, int ldbb, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssbgvd.ssbgvd(jobz, uplo, n, ka, kb, ab, offsetab, ldab, bb, offsetbb, ldbb, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void ssbgvxK(String jobz, String range, String uplo, int n, int ka, int kb, float[] ab, int offsetab, int ldab, float[] bb, int offsetbb, int ldbb, float[] q, int offsetq, int ldq, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info) {
    org.netlib.lapack.Ssbgvx.ssbgvx(jobz, range, uplo, n, ka, kb, ab, offsetab, ldab, bb, offsetbb, ldbb, q, offsetq, ldq, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
  }

  protected void ssbtrdK(String vect, String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float[] d, int offsetd, float[] e, int offsete, float[] q, int offsetq, int ldq, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssbtrd.ssbtrd(vect, uplo, n, kd, ab, offsetab, ldab, d, offsetd, e, offsete, q, offsetq, ldq, work, offsetwork, info);
  }

  protected void sspconK(String uplo, int n, float[] ap, int offsetap, int[] ipiv, int offsetipiv, float anorm, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sspcon.sspcon(uplo, n, ap, offsetap, ipiv, offsetipiv, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void sspevK(String jobz, String uplo, int n, float[] ap, int offsetap, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sspev.sspev(jobz, uplo, n, ap, offsetap, w, offsetw, z, offsetz, ldz, work, offsetwork, info);
  }

  protected void sspevdK(String jobz, String uplo, int n, float[] ap, int offsetap, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sspevd.sspevd(jobz, uplo, n, ap, offsetap, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void sspevxK(String jobz, String range, String uplo, int n, float[] ap, int offsetap, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info) {
    org.netlib.lapack.Sspevx.sspevx(jobz, range, uplo, n, ap, offsetap, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
  }

  protected void sspgstK(int itype, String uplo, int n, float[] ap, int offsetap, float[] bp, int offsetbp, org.netlib.util.intW info) {
    org.netlib.lapack.Sspgst.sspgst(itype, uplo, n, ap, offsetap, bp, offsetbp, info);
  }

  protected void sspgvK(int itype, String jobz, String uplo, int n, float[] ap, int offsetap, float[] bp, int offsetbp, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sspgv.sspgv(itype, jobz, uplo, n, ap, offsetap, bp, offsetbp, w, offsetw, z, offsetz, ldz, work, offsetwork, info);
  }

  protected void sspgvdK(int itype, String jobz, String uplo, int n, float[] ap, int offsetap, float[] bp, int offsetbp, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sspgvd.sspgvd(itype, jobz, uplo, n, ap, offsetap, bp, offsetbp, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void sspgvxK(int itype, String jobz, String range, String uplo, int n, float[] ap, int offsetap, float[] bp, int offsetbp, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info) {
    org.netlib.lapack.Sspgvx.sspgvx(itype, jobz, range, uplo, n, ap, offsetap, bp, offsetbp, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
  }

  protected void ssprfsK(String uplo, int n, int nrhs, float[] ap, int offsetap, float[] afp, int offsetafp, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssprfs.ssprfs(uplo, n, nrhs, ap, offsetap, afp, offsetafp, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void sspsvK(String uplo, int n, int nrhs, float[] ap, int offsetap, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Sspsv.sspsv(uplo, n, nrhs, ap, offsetap, ipiv, offsetipiv, b, offsetb, ldb, info);
  }

  protected void sspsvxK(String fact, String uplo, int n, int nrhs, float[] ap, int offsetap, float[] afp, int offsetafp, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, org.netlib.util.floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sspsvx.sspsvx(fact, uplo, n, nrhs, ap, offsetap, afp, offsetafp, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void ssptrdK(String uplo, int n, float[] ap, int offsetap, float[] d, int offsetd, float[] e, int offsete, float[] tau, int offsettau, org.netlib.util.intW info) {
    org.netlib.lapack.Ssptrd.ssptrd(uplo, n, ap, offsetap, d, offsetd, e, offsete, tau, offsettau, info);
  }

  protected void ssptrfK(String uplo, int n, float[] ap, int offsetap, int[] ipiv, int offsetipiv, org.netlib.util.intW info) {
    org.netlib.lapack.Ssptrf.ssptrf(uplo, n, ap, offsetap, ipiv, offsetipiv, info);
  }

  protected void ssptriK(String uplo, int n, float[] ap, int offsetap, int[] ipiv, int offsetipiv, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssptri.ssptri(uplo, n, ap, offsetap, ipiv, offsetipiv, work, offsetwork, info);
  }

  protected void ssptrsK(String uplo, int n, int nrhs, float[] ap, int offsetap, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Ssptrs.ssptrs(uplo, n, nrhs, ap, offsetap, ipiv, offsetipiv, b, offsetb, ldb, info);
  }

  protected void sstebzK(String range, String order, int n, float vl, float vu, int il, int iu, float abstol, float[] d, int offsetd, float[] e, int offsete, org.netlib.util.intW m, org.netlib.util.intW nsplit, float[] w, int offsetw, int[] iblock, int offsetiblock, int[] isplit, int offsetisplit, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sstebz.sstebz(range, order, n, vl, vu, il, iu, abstol, d, offsetd, e, offsete, m, nsplit, w, offsetw, iblock, offsetiblock, isplit, offsetisplit, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void sstedcK(String compz, int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sstedc.sstedc(compz, n, d, offsetd, e, offsete, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void sstegrK(String jobz, String range, int n, float[] d, int offsetd, float[] e, int offsete, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sstegr.sstegr(jobz, range, n, d, offsetd, e, offsete, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, isuppz, offsetisuppz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void ssteinK(int n, float[] d, int offsetd, float[] e, int offsete, int m, float[] w, int offsetw, int[] iblock, int offsetiblock, int[] isplit, int offsetisplit, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info) {
    org.netlib.lapack.Sstein.sstein(n, d, offsetd, e, offsete, m, w, offsetw, iblock, offsetiblock, isplit, offsetisplit, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
  }

  protected void sstemrK(String jobz, String range, int n, float[] d, int offsetd, float[] e, int offsete, float vl, float vu, int il, int iu, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, int nzc, int[] isuppz, int offsetisuppz, org.netlib.util.booleanW tryrac, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sstemr.sstemr(jobz, range, n, d, offsetd, e, offsete, vl, vu, il, iu, m, w, offsetw, z, offsetz, ldz, nzc, isuppz, offsetisuppz, tryrac, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void ssteqrK(String compz, int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, int ldz, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssteqr.ssteqr(compz, n, d, offsetd, e, offsete, z, offsetz, ldz, work, offsetwork, info);
  }

  protected void ssterfK(int n, float[] d, int offsetd, float[] e, int offsete, org.netlib.util.intW info) {
    org.netlib.lapack.Ssterf.ssterf(n, d, offsetd, e, offsete, info);
  }

  protected void sstevK(String jobz, int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, int ldz, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sstev.sstev(jobz, n, d, offsetd, e, offsete, z, offsetz, ldz, work, offsetwork, info);
  }

  protected void sstevdK(String jobz, int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sstevd.sstevd(jobz, n, d, offsetd, e, offsete, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void sstevrK(String jobz, String range, int n, float[] d, int offsetd, float[] e, int offsete, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Sstevr.sstevr(jobz, range, n, d, offsetd, e, offsete, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, isuppz, offsetisuppz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void sstevxK(String jobz, String range, int n, float[] d, int offsetd, float[] e, int offsete, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info) {
    org.netlib.lapack.Sstevx.sstevx(jobz, range, n, d, offsetd, e, offsete, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
  }

  protected void ssyconK(String uplo, int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float anorm, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssycon.ssycon(uplo, n, a, offseta, lda, ipiv, offsetipiv, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void ssyevK(String jobz, String uplo, int n, float[] a, int offseta, int lda, float[] w, int offsetw, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssyev.ssyev(jobz, uplo, n, a, offseta, lda, w, offsetw, work, offsetwork, lwork, info);
  }

  protected void ssyevdK(String jobz, String uplo, int n, float[] a, int offseta, int lda, float[] w, int offsetw, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssyevd.ssyevd(jobz, uplo, n, a, offseta, lda, w, offsetw, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void ssyevrK(String jobz, String range, String uplo, int n, float[] a, int offseta, int lda, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssyevr.ssyevr(jobz, range, uplo, n, a, offseta, lda, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, isuppz, offsetisuppz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void ssyevxK(String jobz, String range, String uplo, int n, float[] a, int offseta, int lda, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info) {
    org.netlib.lapack.Ssyevx.ssyevx(jobz, range, uplo, n, a, offseta, lda, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, ifail, offsetifail, info);
  }

  protected void ssygs2K(int itype, String uplo, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Ssygs2.ssygs2(itype, uplo, n, a, offseta, lda, b, offsetb, ldb, info);
  }

  protected void ssygstK(int itype, String uplo, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Ssygst.ssygst(itype, uplo, n, a, offseta, lda, b, offsetb, ldb, info);
  }

  protected void ssygvK(int itype, String jobz, String uplo, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] w, int offsetw, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssygv.ssygv(itype, jobz, uplo, n, a, offseta, lda, b, offsetb, ldb, w, offsetw, work, offsetwork, lwork, info);
  }

  protected void ssygvdK(int itype, String jobz, String uplo, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] w, int offsetw, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssygvd.ssygvd(itype, jobz, uplo, n, a, offseta, lda, b, offsetb, ldb, w, offsetw, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void ssygvxK(int itype, String jobz, String range, String uplo, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, org.netlib.util.intW info) {
    org.netlib.lapack.Ssygvx.ssygvx(itype, jobz, range, uplo, n, a, offseta, lda, b, offsetb, ldb, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, ifail, offsetifail, info);
  }

  protected void ssyrfsK(String uplo, int n, int nrhs, float[] a, int offseta, int lda, float[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssyrfs.ssyrfs(uplo, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void ssysvK(String uplo, int n, int nrhs, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssysv.ssysv(uplo, n, nrhs, a, offseta, lda, ipiv, offsetipiv, b, offsetb, ldb, work, offsetwork, lwork, info);
  }

  protected void ssysvxK(String fact, String uplo, int n, int nrhs, float[] a, int offseta, int lda, float[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, org.netlib.util.floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssysvx.ssysvx(fact, uplo, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, lwork, iwork, offsetiwork, info);
  }

  protected void ssytd2K(String uplo, int n, float[] a, int offseta, int lda, float[] d, int offsetd, float[] e, int offsete, float[] tau, int offsettau, org.netlib.util.intW info) {
    org.netlib.lapack.Ssytd2.ssytd2(uplo, n, a, offseta, lda, d, offsetd, e, offsete, tau, offsettau, info);
  }

  protected void ssytf2K(String uplo, int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, org.netlib.util.intW info) {
    org.netlib.lapack.Ssytf2.ssytf2(uplo, n, a, offseta, lda, ipiv, offsetipiv, info);
  }

  protected void ssytrdK(String uplo, int n, float[] a, int offseta, int lda, float[] d, int offsetd, float[] e, int offsete, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssytrd.ssytrd(uplo, n, a, offseta, lda, d, offsetd, e, offsete, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected void ssytrfK(String uplo, int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssytrf.ssytrf(uplo, n, a, offseta, lda, ipiv, offsetipiv, work, offsetwork, lwork, info);
  }

  protected void ssytriK(String uplo, int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Ssytri.ssytri(uplo, n, a, offseta, lda, ipiv, offsetipiv, work, offsetwork, info);
  }

  protected void ssytrsK(String uplo, int n, int nrhs, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Ssytrs.ssytrs(uplo, n, nrhs, a, offseta, lda, ipiv, offsetipiv, b, offsetb, ldb, info);
  }

  protected void stbconK(String norm, String uplo, String diag, int n, int kd, float[] ab, int offsetab, int ldab, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Stbcon.stbcon(norm, uplo, diag, n, kd, ab, offsetab, ldab, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void stbrfsK(String uplo, String trans, String diag, int n, int kd, int nrhs, float[] ab, int offsetab, int ldab, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Stbrfs.stbrfs(uplo, trans, diag, n, kd, nrhs, ab, offsetab, ldab, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void stbtrsK(String uplo, String trans, String diag, int n, int kd, int nrhs, float[] ab, int offsetab, int ldab, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Stbtrs.stbtrs(uplo, trans, diag, n, kd, nrhs, ab, offsetab, ldab, b, offsetb, ldb, info);
  }

  protected void stgevcK(String side, String howmny, boolean[] select, int offsetselect, int n, float[] s, int offsets, int lds, float[] p, int offsetp, int ldp, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, int mm, org.netlib.util.intW m, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Stgevc.stgevc(side, howmny, select, offsetselect, n, s, offsets, lds, p, offsetp, ldp, vl, offsetvl, ldvl, vr, offsetvr, ldvr, mm, m, work, offsetwork, info);
  }

  protected void stgex2K(boolean wantq, boolean wantz, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] q, int offsetq, int ldq, float[] z, int offsetz, int ldz, int j1, int n1, int n2, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Stgex2.stgex2(wantq, wantz, n, a, offseta, lda, b, offsetb, ldb, q, offsetq, ldq, z, offsetz, ldz, j1, n1, n2, work, offsetwork, lwork, info);
  }

  protected void stgexcK(boolean wantq, boolean wantz, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] q, int offsetq, int ldq, float[] z, int offsetz, int ldz, org.netlib.util.intW ifst, org.netlib.util.intW ilst, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Stgexc.stgexc(wantq, wantz, n, a, offseta, lda, b, offsetb, ldb, q, offsetq, ldq, z, offsetz, ldz, ifst, ilst, work, offsetwork, lwork, info);
  }

  protected void stgsenK(int ijob, boolean wantq, boolean wantz, boolean[] select, int offsetselect, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] q, int offsetq, int ldq, float[] z, int offsetz, int ldz, org.netlib.util.intW m, org.netlib.util.floatW pl, org.netlib.util.floatW pr, float[] dif, int offsetdif, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Stgsen.stgsen(ijob, wantq, wantz, select, offsetselect, n, a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, q, offsetq, ldq, z, offsetz, ldz, m, pl, pr, dif, offsetdif, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void stgsjaK(String jobu, String jobv, String jobq, int m, int p, int n, int k, int l, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float tola, float tolb, float[] alpha, int offsetalpha, float[] beta, int offsetbeta, float[] u, int offsetu, int ldu, float[] v, int offsetv, int ldv, float[] q, int offsetq, int ldq, float[] work, int offsetwork, org.netlib.util.intW ncycle, org.netlib.util.intW info) {
    org.netlib.lapack.Stgsja.stgsja(jobu, jobv, jobq, m, p, n, k, l, a, offseta, lda, b, offsetb, ldb, tola, tolb, alpha, offsetalpha, beta, offsetbeta, u, offsetu, ldu, v, offsetv, ldv, q, offsetq, ldq, work, offsetwork, ncycle, info);
  }

  protected void stgsnaK(String job, String howmny, boolean[] select, int offsetselect, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, float[] s, int offsets, float[] dif, int offsetdif, int mm, org.netlib.util.intW m, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Stgsna.stgsna(job, howmny, select, offsetselect, n, a, offseta, lda, b, offsetb, ldb, vl, offsetvl, ldvl, vr, offsetvr, ldvr, s, offsets, dif, offsetdif, mm, m, work, offsetwork, lwork, iwork, offsetiwork, info);
  }

  protected void stgsy2K(String trans, int ijob, int m, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] c, int offsetc, int Ldc, float[] d, int offsetd, int ldd, float[] e, int offsete, int lde, float[] f, int offsetf, int ldf, org.netlib.util.floatW scale, org.netlib.util.floatW rdsum, org.netlib.util.floatW rdscal, int[] iwork, int offsetiwork, org.netlib.util.intW pq, org.netlib.util.intW info) {
    org.netlib.lapack.Stgsy2.stgsy2(trans, ijob, m, n, a, offseta, lda, b, offsetb, ldb, c, offsetc, Ldc, d, offsetd, ldd, e, offsete, lde, f, offsetf, ldf, scale, rdsum, rdscal, iwork, offsetiwork, pq, info);
  }

  protected void stgsylK(String trans, int ijob, int m, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] c, int offsetc, int Ldc, float[] d, int offsetd, int ldd, float[] e, int offsete, int lde, float[] f, int offsetf, int ldf, org.netlib.util.floatW scale, org.netlib.util.floatW dif, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Stgsyl.stgsyl(trans, ijob, m, n, a, offseta, lda, b, offsetb, ldb, c, offsetc, Ldc, d, offsetd, ldd, e, offsete, lde, f, offsetf, ldf, scale, dif, work, offsetwork, lwork, iwork, offsetiwork, info);
  }

  protected void stpconK(String norm, String uplo, String diag, int n, float[] ap, int offsetap, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Stpcon.stpcon(norm, uplo, diag, n, ap, offsetap, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void stprfsK(String uplo, String trans, String diag, int n, int nrhs, float[] ap, int offsetap, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Stprfs.stprfs(uplo, trans, diag, n, nrhs, ap, offsetap, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void stptriK(String uplo, String diag, int n, float[] ap, int offsetap, org.netlib.util.intW info) {
    org.netlib.lapack.Stptri.stptri(uplo, diag, n, ap, offsetap, info);
  }

  protected void stptrsK(String uplo, String trans, String diag, int n, int nrhs, float[] ap, int offsetap, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Stptrs.stptrs(uplo, trans, diag, n, nrhs, ap, offsetap, b, offsetb, ldb, info);
  }

  protected void strconK(String norm, String uplo, String diag, int n, float[] a, int offseta, int lda, org.netlib.util.floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Strcon.strcon(norm, uplo, diag, n, a, offseta, lda, rcond, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void strevcK(String side, String howmny, boolean[] select, int offsetselect, int n, float[] t, int offsett, int ldt, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, int mm, org.netlib.util.intW m, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Strevc.strevc(side, howmny, select, offsetselect, n, t, offsett, ldt, vl, offsetvl, ldvl, vr, offsetvr, ldvr, mm, m, work, offsetwork, info);
  }

  protected void strexcK(String compq, int n, float[] t, int offsett, int ldt, float[] q, int offsetq, int ldq, org.netlib.util.intW ifst, org.netlib.util.intW ilst, float[] work, int offsetwork, org.netlib.util.intW info) {
    org.netlib.lapack.Strexc.strexc(compq, n, t, offsett, ldt, q, offsetq, ldq, ifst, ilst, work, offsetwork, info);
  }

  protected void strrfsK(String uplo, String trans, String diag, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Strrfs.strrfs(uplo, trans, diag, n, nrhs, a, offseta, lda, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
  }

  protected void strsenK(String job, String compq, boolean[] select, int offsetselect, int n, float[] t, int offsett, int ldt, float[] q, int offsetq, int ldq, float[] wr, int offsetwr, float[] wi, int offsetwi, org.netlib.util.intW m, org.netlib.util.floatW s, org.netlib.util.floatW sep, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, org.netlib.util.intW info) {
    org.netlib.lapack.Strsen.strsen(job, compq, select, offsetselect, n, t, offsett, ldt, q, offsetq, ldq, wr, offsetwr, wi, offsetwi, m, s, sep, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
  }

  protected void strsnaK(String job, String howmny, boolean[] select, int offsetselect, int n, float[] t, int offsett, int ldt, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, float[] s, int offsets, float[] sep, int offsetsep, int mm, org.netlib.util.intW m, float[] work, int offsetwork, int ldwork, int[] iwork, int offsetiwork, org.netlib.util.intW info) {
    org.netlib.lapack.Strsna.strsna(job, howmny, select, offsetselect, n, t, offsett, ldt, vl, offsetvl, ldvl, vr, offsetvr, ldvr, s, offsets, sep, offsetsep, mm, m, work, offsetwork, ldwork, iwork, offsetiwork, info);
  }

  protected void strsylK(String trana, String tranb, int isgn, int m, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] c, int offsetc, int Ldc, org.netlib.util.floatW scale, org.netlib.util.intW info) {
    org.netlib.lapack.Strsyl.strsyl(trana, tranb, isgn, m, n, a, offseta, lda, b, offsetb, ldb, c, offsetc, Ldc, scale, info);
  }

  protected void strti2K(String uplo, String diag, int n, float[] a, int offseta, int lda, org.netlib.util.intW info) {
    org.netlib.lapack.Strti2.strti2(uplo, diag, n, a, offseta, lda, info);
  }

  protected void strtriK(String uplo, String diag, int n, float[] a, int offseta, int lda, org.netlib.util.intW info) {
    org.netlib.lapack.Strtri.strtri(uplo, diag, n, a, offseta, lda, info);
  }

  protected void strtrsK(String uplo, String trans, String diag, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, org.netlib.util.intW info) {
    org.netlib.lapack.Strtrs.strtrs(uplo, trans, diag, n, nrhs, a, offseta, lda, b, offsetb, ldb, info);
  }

  protected void stzrqfK(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, org.netlib.util.intW info) {
    org.netlib.lapack.Stzrqf.stzrqf(m, n, a, offseta, lda, tau, offsettau, info);
  }

  protected void stzrzfK(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, org.netlib.util.intW info) {
    org.netlib.lapack.Stzrzf.stzrzf(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
  }

  protected double dlamchK(String cmach) {
    return org.netlib.lapack.Dlamch.dlamch(cmach);
  }

  protected void dlamc1K(org.netlib.util.intW beta, org.netlib.util.intW t, org.netlib.util.booleanW rnd, org.netlib.util.booleanW ieee1) {
    org.netlib.lapack.Dlamc1.dlamc1(beta, t, rnd, ieee1);
  }

  protected void dlamc2K(org.netlib.util.intW beta, org.netlib.util.intW t, org.netlib.util.booleanW rnd, org.netlib.util.doubleW eps, org.netlib.util.intW emin, org.netlib.util.doubleW rmin, org.netlib.util.intW emax, org.netlib.util.doubleW rmax) {
    org.netlib.lapack.Dlamc2.dlamc2(beta, t, rnd, eps, emin, rmin, emax, rmax);
  }

  protected double dlamc3K(double a, double b) {
    return org.netlib.lapack.Dlamc3.dlamc3(a, b);
  }

  protected void dlamc4K(org.netlib.util.intW emin, double start, int base) {
    org.netlib.lapack.Dlamc4.dlamc4(emin, start, base);
  }

  protected void dlamc5K(int beta, int p, int emin, boolean ieee, org.netlib.util.intW emax, org.netlib.util.doubleW rmax) {
    org.netlib.lapack.Dlamc5.dlamc5(beta, p, emin, ieee, emax, rmax);
  }

  protected double dsecndK() {
    return org.netlib.lapack.Dsecnd.dsecnd();
  }

  protected boolean lsameK(String ca, String cb) {
    return org.netlib.lapack.Lsame.lsame(ca, cb);
  }

  protected float secondK() {
    return org.netlib.lapack.Second.second();
  }

  protected float slamchK(String cmach) {
    return org.netlib.lapack.Slamch.slamch(cmach);
  }

  protected void slamc1K(org.netlib.util.intW beta, org.netlib.util.intW t, org.netlib.util.booleanW rnd, org.netlib.util.booleanW ieee1) {
    org.netlib.lapack.Slamc1.slamc1(beta, t, rnd, ieee1);
  }

  protected void slamc2K(org.netlib.util.intW beta, org.netlib.util.intW t, org.netlib.util.booleanW rnd, org.netlib.util.floatW eps, org.netlib.util.intW emin, org.netlib.util.floatW rmin, org.netlib.util.intW emax, org.netlib.util.floatW rmax) {
    org.netlib.lapack.Slamc2.slamc2(beta, t, rnd, eps, emin, rmin, emax, rmax);
  }

  protected float slamc3K(float a, float b) {
    return org.netlib.lapack.Slamc3.slamc3(a, b);
  }

  protected void slamc4K(org.netlib.util.intW emin, float start, int base) {
    org.netlib.lapack.Slamc4.slamc4(emin, start, base);
  }

  protected void slamc5K(int beta, int p, int emin, boolean ieee, org.netlib.util.intW emax, org.netlib.util.floatW rmax) {
    org.netlib.lapack.Slamc5.slamc5(beta, p, emin, ieee, emax, rmax);
  }
}
