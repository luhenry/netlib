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

import dev.ludovic.netlib.LAPACK;

abstract class NetlibWrapper extends AbstractLAPACK {

  private final com.github.fommil.netlib.LAPACK lapack;

  protected NetlibWrapper(com.github.fommil.netlib.LAPACK _lapack) {
      lapack = _lapack;
  }

  protected void dbdsdcK(String uplo, String compq, int n, double[] d, int _d_offset, double[] e, int _e_offset, double[] u, int _u_offset, int ldu, double[] vt, int _vt_offset, int ldvt, double[] q, int _q_offset, int[] iq, int _iq_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dbdsdc(uplo, compq, n, d, _d_offset, e, _e_offset, u, _u_offset, ldu, vt, _vt_offset, ldvt, q, _q_offset, iq, _iq_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dbdsqrK(String uplo, int n, int ncvt, int nru, int ncc, double[] d, int _d_offset, double[] e, int _e_offset, double[] vt, int _vt_offset, int ldvt, double[] u, int _u_offset, int ldu, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dbdsqr(uplo, n, ncvt, nru, ncc, d, _d_offset, e, _e_offset, vt, _vt_offset, ldvt, u, _u_offset, ldu, c, _c_offset, Ldc, work, _work_offset, info);
  }

  protected void ddisnaK(String job, int m, int n, double[] d, int _d_offset, double[] sep, int _sep_offset, org.netlib.util.intW info) {
    lapack.ddisna(job, m, n, d, _d_offset, sep, _sep_offset, info);
  }

  protected void dgbbrdK(String vect, int m, int n, int ncc, int kl, int ku, double[] ab, int _ab_offset, int ldab, double[] d, int _d_offset, double[] e, int _e_offset, double[] q, int _q_offset, int ldq, double[] pt, int _pt_offset, int ldpt, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dgbbrd(vect, m, n, ncc, kl, ku, ab, _ab_offset, ldab, d, _d_offset, e, _e_offset, q, _q_offset, ldq, pt, _pt_offset, ldpt, c, _c_offset, Ldc, work, _work_offset, info);
  }

  protected void dgbconK(String norm, int n, int kl, int ku, double[] ab, int _ab_offset, int ldab, int[] ipiv, int _ipiv_offset, double anorm, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dgbcon(norm, n, kl, ku, ab, _ab_offset, ldab, ipiv, _ipiv_offset, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dgbequK(int m, int n, int kl, int ku, double[] ab, int _ab_offset, int ldab, double[] r, int _r_offset, double[] c, int _c_offset, org.netlib.util.doubleW rowcnd, org.netlib.util.doubleW colcnd, org.netlib.util.doubleW amax, org.netlib.util.intW info) {
    lapack.dgbequ(m, n, kl, ku, ab, _ab_offset, ldab, r, _r_offset, c, _c_offset, rowcnd, colcnd, amax, info);
  }

  protected void dgbrfsK(String trans, int n, int kl, int ku, int nrhs, double[] ab, int _ab_offset, int ldab, double[] afb, int _afb_offset, int ldafb, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dgbrfs(trans, n, kl, ku, nrhs, ab, _ab_offset, ldab, afb, _afb_offset, ldafb, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dgbsvK(int n, int kl, int ku, int nrhs, double[] ab, int _ab_offset, int ldab, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dgbsv(n, kl, ku, nrhs, ab, _ab_offset, ldab, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  protected void dgbsvxK(String fact, String trans, int n, int kl, int ku, int nrhs, double[] ab, int _ab_offset, int ldab, double[] afb, int _afb_offset, int ldafb, int[] ipiv, int _ipiv_offset, org.netlib.util.StringW equed, double[] r, int _r_offset, double[] c, int _c_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dgbsvx(fact, trans, n, kl, ku, nrhs, ab, _ab_offset, ldab, afb, _afb_offset, ldafb, ipiv, _ipiv_offset, equed, r, _r_offset, c, _c_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dgbtf2K(int m, int n, int kl, int ku, double[] ab, int _ab_offset, int ldab, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    lapack.dgbtf2(m, n, kl, ku, ab, _ab_offset, ldab, ipiv, _ipiv_offset, info);
  }

  protected void dgbtrfK(int m, int n, int kl, int ku, double[] ab, int _ab_offset, int ldab, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    lapack.dgbtrf(m, n, kl, ku, ab, _ab_offset, ldab, ipiv, _ipiv_offset, info);
  }

  protected void dgbtrsK(String trans, int n, int kl, int ku, int nrhs, double[] ab, int _ab_offset, int ldab, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dgbtrs(trans, n, kl, ku, nrhs, ab, _ab_offset, ldab, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  protected void dgebakK(String job, String side, int n, int ilo, int ihi, double[] scale, int _scale_offset, int m, double[] v, int _v_offset, int ldv, org.netlib.util.intW info) {
    lapack.dgebak(job, side, n, ilo, ihi, scale, _scale_offset, m, v, _v_offset, ldv, info);
  }

  protected void dgebalK(String job, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW ilo, org.netlib.util.intW ihi, double[] scale, int _scale_offset, org.netlib.util.intW info) {
    lapack.dgebal(job, n, a, _a_offset, lda, ilo, ihi, scale, _scale_offset, info);
  }

  protected void dgebd2K(int m, int n, double[] a, int _a_offset, int lda, double[] d, int _d_offset, double[] e, int _e_offset, double[] tauq, int _tauq_offset, double[] taup, int _taup_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dgebd2(m, n, a, _a_offset, lda, d, _d_offset, e, _e_offset, tauq, _tauq_offset, taup, _taup_offset, work, _work_offset, info);
  }

  protected void dgebrdK(int m, int n, double[] a, int _a_offset, int lda, double[] d, int _d_offset, double[] e, int _e_offset, double[] tauq, int _tauq_offset, double[] taup, int _taup_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dgebrd(m, n, a, _a_offset, lda, d, _d_offset, e, _e_offset, tauq, _tauq_offset, taup, _taup_offset, work, _work_offset, lwork, info);
  }

  protected void dgeconK(String norm, int n, double[] a, int _a_offset, int lda, double anorm, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dgecon(norm, n, a, _a_offset, lda, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dgeequK(int m, int n, double[] a, int _a_offset, int lda, double[] r, int _r_offset, double[] c, int _c_offset, org.netlib.util.doubleW rowcnd, org.netlib.util.doubleW colcnd, org.netlib.util.doubleW amax, org.netlib.util.intW info) {
    lapack.dgeequ(m, n, a, _a_offset, lda, r, _r_offset, c, _c_offset, rowcnd, colcnd, amax, info);
  }

  protected void dgeesK(String jobvs, String sort, java.lang.Object select, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW sdim, double[] wr, int _wr_offset, double[] wi, int _wi_offset, double[] vs, int _vs_offset, int ldvs, double[] work, int _work_offset, int lwork, boolean[] bwork, int _bwork_offset, org.netlib.util.intW info) {
    lapack.dgees(jobvs, sort, select, n, a, _a_offset, lda, sdim, wr, _wr_offset, wi, _wi_offset, vs, _vs_offset, ldvs, work, _work_offset, lwork, bwork, _bwork_offset, info);
  }

  protected void dgeesxK(String jobvs, String sort, java.lang.Object select, String sense, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW sdim, double[] wr, int _wr_offset, double[] wi, int _wi_offset, double[] vs, int _vs_offset, int ldvs, org.netlib.util.doubleW rconde, org.netlib.util.doubleW rcondv, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, boolean[] bwork, int _bwork_offset, org.netlib.util.intW info) {
    lapack.dgeesx(jobvs, sort, select, sense, n, a, _a_offset, lda, sdim, wr, _wr_offset, wi, _wi_offset, vs, _vs_offset, ldvs, rconde, rcondv, work, _work_offset, lwork, iwork, _iwork_offset, liwork, bwork, _bwork_offset, info);
  }

  protected void dgeevK(String jobvl, String jobvr, int n, double[] a, int _a_offset, int lda, double[] wr, int _wr_offset, double[] wi, int _wi_offset, double[] vl, int _vl_offset, int ldvl, double[] vr, int _vr_offset, int ldvr, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dgeev(jobvl, jobvr, n, a, _a_offset, lda, wr, _wr_offset, wi, _wi_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, work, _work_offset, lwork, info);
  }

  protected void dgeevxK(String balanc, String jobvl, String jobvr, String sense, int n, double[] a, int _a_offset, int lda, double[] wr, int _wr_offset, double[] wi, int _wi_offset, double[] vl, int _vl_offset, int ldvl, double[] vr, int _vr_offset, int ldvr, org.netlib.util.intW ilo, org.netlib.util.intW ihi, double[] scale, int _scale_offset, org.netlib.util.doubleW abnrm, double[] rconde, int _rconde_offset, double[] rcondv, int _rcondv_offset, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dgeevx(balanc, jobvl, jobvr, sense, n, a, _a_offset, lda, wr, _wr_offset, wi, _wi_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, ilo, ihi, scale, _scale_offset, abnrm, rconde, _rconde_offset, rcondv, _rcondv_offset, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  protected void dgegsK(String jobvsl, String jobvsr, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] alphar, int _alphar_offset, double[] alphai, int _alphai_offset, double[] beta, int _beta_offset, double[] vsl, int _vsl_offset, int ldvsl, double[] vsr, int _vsr_offset, int ldvsr, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dgegs(jobvsl, jobvsr, n, a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vsl, _vsl_offset, ldvsl, vsr, _vsr_offset, ldvsr, work, _work_offset, lwork, info);
  }

  protected void dgegvK(String jobvl, String jobvr, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] alphar, int _alphar_offset, double[] alphai, int _alphai_offset, double[] beta, int _beta_offset, double[] vl, int _vl_offset, int ldvl, double[] vr, int _vr_offset, int ldvr, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dgegv(jobvl, jobvr, n, a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, work, _work_offset, lwork, info);
  }

  protected void dgehd2K(int n, int ilo, int ihi, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dgehd2(n, ilo, ihi, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  protected void dgehrdK(int n, int ilo, int ihi, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dgehrd(n, ilo, ihi, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void dgelq2K(int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dgelq2(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  protected void dgelqfK(int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dgelqf(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void dgelsK(String trans, int m, int n, int nrhs, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dgels(trans, m, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, work, _work_offset, lwork, info);
  }

  protected void dgelsdK(int m, int n, int nrhs, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] s, int _s_offset, double rcond, org.netlib.util.intW rank, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dgelsd(m, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, s, _s_offset, rcond, rank, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  protected void dgelssK(int m, int n, int nrhs, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] s, int _s_offset, double rcond, org.netlib.util.intW rank, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dgelss(m, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, s, _s_offset, rcond, rank, work, _work_offset, lwork, info);
  }

  protected void dgelsxK(int m, int n, int nrhs, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, int[] jpvt, int _jpvt_offset, double rcond, org.netlib.util.intW rank, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dgelsx(m, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, jpvt, _jpvt_offset, rcond, rank, work, _work_offset, info);
  }

  protected void dgelsyK(int m, int n, int nrhs, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, int[] jpvt, int _jpvt_offset, double rcond, org.netlib.util.intW rank, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dgelsy(m, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, jpvt, _jpvt_offset, rcond, rank, work, _work_offset, lwork, info);
  }

  protected void dgeql2K(int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dgeql2(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  protected void dgeqlfK(int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dgeqlf(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void dgeqp3K(int m, int n, double[] a, int _a_offset, int lda, int[] jpvt, int _jpvt_offset, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dgeqp3(m, n, a, _a_offset, lda, jpvt, _jpvt_offset, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void dgeqpfK(int m, int n, double[] a, int _a_offset, int lda, int[] jpvt, int _jpvt_offset, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dgeqpf(m, n, a, _a_offset, lda, jpvt, _jpvt_offset, tau, _tau_offset, work, _work_offset, info);
  }

  protected void dgeqr2K(int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dgeqr2(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  protected void dgeqrfK(int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dgeqrf(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void dgerfsK(String trans, int n, int nrhs, double[] a, int _a_offset, int lda, double[] af, int _af_offset, int ldaf, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dgerfs(trans, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dgerq2K(int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dgerq2(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  protected void dgerqfK(int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dgerqf(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void dgesc2K(int n, double[] a, int _a_offset, int lda, double[] rhs, int _rhs_offset, int[] ipiv, int _ipiv_offset, int[] jpiv, int _jpiv_offset, org.netlib.util.doubleW scale) {
    lapack.dgesc2(n, a, _a_offset, lda, rhs, _rhs_offset, ipiv, _ipiv_offset, jpiv, _jpiv_offset, scale);
  }

  protected void dgesddK(String jobz, int m, int n, double[] a, int _a_offset, int lda, double[] s, int _s_offset, double[] u, int _u_offset, int ldu, double[] vt, int _vt_offset, int ldvt, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dgesdd(jobz, m, n, a, _a_offset, lda, s, _s_offset, u, _u_offset, ldu, vt, _vt_offset, ldvt, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  protected void dgesvK(int n, int nrhs, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dgesv(n, nrhs, a, _a_offset, lda, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  protected void dgesvdK(String jobu, String jobvt, int m, int n, double[] a, int _a_offset, int lda, double[] s, int _s_offset, double[] u, int _u_offset, int ldu, double[] vt, int _vt_offset, int ldvt, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dgesvd(jobu, jobvt, m, n, a, _a_offset, lda, s, _s_offset, u, _u_offset, ldu, vt, _vt_offset, ldvt, work, _work_offset, lwork, info);
  }

  protected void dgesvxK(String fact, String trans, int n, int nrhs, double[] a, int _a_offset, int lda, double[] af, int _af_offset, int ldaf, int[] ipiv, int _ipiv_offset, org.netlib.util.StringW equed, double[] r, int _r_offset, double[] c, int _c_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dgesvx(fact, trans, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, ipiv, _ipiv_offset, equed, r, _r_offset, c, _c_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dgetc2K(int n, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, int[] jpiv, int _jpiv_offset, org.netlib.util.intW info) {
    lapack.dgetc2(n, a, _a_offset, lda, ipiv, _ipiv_offset, jpiv, _jpiv_offset, info);
  }

  protected void dgetf2K(int m, int n, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    lapack.dgetf2(m, n, a, _a_offset, lda, ipiv, _ipiv_offset, info);
  }

  protected void dgetrfK(int m, int n, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    lapack.dgetrf(m, n, a, _a_offset, lda, ipiv, _ipiv_offset, info);
  }

  protected void dgetriK(int n, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dgetri(n, a, _a_offset, lda, ipiv, _ipiv_offset, work, _work_offset, lwork, info);
  }

  protected void dgetrsK(String trans, int n, int nrhs, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dgetrs(trans, n, nrhs, a, _a_offset, lda, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  protected void dggbakK(String job, String side, int n, int ilo, int ihi, double[] lscale, int _lscale_offset, double[] rscale, int _rscale_offset, int m, double[] v, int _v_offset, int ldv, org.netlib.util.intW info) {
    lapack.dggbak(job, side, n, ilo, ihi, lscale, _lscale_offset, rscale, _rscale_offset, m, v, _v_offset, ldv, info);
  }

  protected void dggbalK(String job, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, org.netlib.util.intW ilo, org.netlib.util.intW ihi, double[] lscale, int _lscale_offset, double[] rscale, int _rscale_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dggbal(job, n, a, _a_offset, lda, b, _b_offset, ldb, ilo, ihi, lscale, _lscale_offset, rscale, _rscale_offset, work, _work_offset, info);
  }

  protected void dggesK(String jobvsl, String jobvsr, String sort, java.lang.Object selctg, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, org.netlib.util.intW sdim, double[] alphar, int _alphar_offset, double[] alphai, int _alphai_offset, double[] beta, int _beta_offset, double[] vsl, int _vsl_offset, int ldvsl, double[] vsr, int _vsr_offset, int ldvsr, double[] work, int _work_offset, int lwork, boolean[] bwork, int _bwork_offset, org.netlib.util.intW info) {
    lapack.dgges(jobvsl, jobvsr, sort, selctg, n, a, _a_offset, lda, b, _b_offset, ldb, sdim, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vsl, _vsl_offset, ldvsl, vsr, _vsr_offset, ldvsr, work, _work_offset, lwork, bwork, _bwork_offset, info);
  }

  protected void dggesxK(String jobvsl, String jobvsr, String sort, java.lang.Object selctg, String sense, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, org.netlib.util.intW sdim, double[] alphar, int _alphar_offset, double[] alphai, int _alphai_offset, double[] beta, int _beta_offset, double[] vsl, int _vsl_offset, int ldvsl, double[] vsr, int _vsr_offset, int ldvsr, double[] rconde, int _rconde_offset, double[] rcondv, int _rcondv_offset, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, boolean[] bwork, int _bwork_offset, org.netlib.util.intW info) {
    lapack.dggesx(jobvsl, jobvsr, sort, selctg, sense, n, a, _a_offset, lda, b, _b_offset, ldb, sdim, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vsl, _vsl_offset, ldvsl, vsr, _vsr_offset, ldvsr, rconde, _rconde_offset, rcondv, _rcondv_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, bwork, _bwork_offset, info);
  }

  protected void dggevK(String jobvl, String jobvr, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] alphar, int _alphar_offset, double[] alphai, int _alphai_offset, double[] beta, int _beta_offset, double[] vl, int _vl_offset, int ldvl, double[] vr, int _vr_offset, int ldvr, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dggev(jobvl, jobvr, n, a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, work, _work_offset, lwork, info);
  }

  protected void dggevxK(String balanc, String jobvl, String jobvr, String sense, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] alphar, int _alphar_offset, double[] alphai, int _alphai_offset, double[] beta, int _beta_offset, double[] vl, int _vl_offset, int ldvl, double[] vr, int _vr_offset, int ldvr, org.netlib.util.intW ilo, org.netlib.util.intW ihi, double[] lscale, int _lscale_offset, double[] rscale, int _rscale_offset, org.netlib.util.doubleW abnrm, org.netlib.util.doubleW bbnrm, double[] rconde, int _rconde_offset, double[] rcondv, int _rcondv_offset, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, boolean[] bwork, int _bwork_offset, org.netlib.util.intW info) {
    lapack.dggevx(balanc, jobvl, jobvr, sense, n, a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, ilo, ihi, lscale, _lscale_offset, rscale, _rscale_offset, abnrm, bbnrm, rconde, _rconde_offset, rcondv, _rcondv_offset, work, _work_offset, lwork, iwork, _iwork_offset, bwork, _bwork_offset, info);
  }

  protected void dggglmK(int n, int m, int p, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] d, int _d_offset, double[] x, int _x_offset, double[] y, int _y_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dggglm(n, m, p, a, _a_offset, lda, b, _b_offset, ldb, d, _d_offset, x, _x_offset, y, _y_offset, work, _work_offset, lwork, info);
  }

  protected void dgghrdK(String compq, String compz, int n, int ilo, int ihi, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] q, int _q_offset, int ldq, double[] z, int _z_offset, int ldz, org.netlib.util.intW info) {
    lapack.dgghrd(compq, compz, n, ilo, ihi, a, _a_offset, lda, b, _b_offset, ldb, q, _q_offset, ldq, z, _z_offset, ldz, info);
  }

  protected void dgglseK(int m, int n, int p, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] c, int _c_offset, double[] d, int _d_offset, double[] x, int _x_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dgglse(m, n, p, a, _a_offset, lda, b, _b_offset, ldb, c, _c_offset, d, _d_offset, x, _x_offset, work, _work_offset, lwork, info);
  }

  protected void dggqrfK(int n, int m, int p, double[] a, int _a_offset, int lda, double[] taua, int _taua_offset, double[] b, int _b_offset, int ldb, double[] taub, int _taub_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dggqrf(n, m, p, a, _a_offset, lda, taua, _taua_offset, b, _b_offset, ldb, taub, _taub_offset, work, _work_offset, lwork, info);
  }

  protected void dggrqfK(int m, int p, int n, double[] a, int _a_offset, int lda, double[] taua, int _taua_offset, double[] b, int _b_offset, int ldb, double[] taub, int _taub_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dggrqf(m, p, n, a, _a_offset, lda, taua, _taua_offset, b, _b_offset, ldb, taub, _taub_offset, work, _work_offset, lwork, info);
  }

  protected void dggsvdK(String jobu, String jobv, String jobq, int m, int n, int p, org.netlib.util.intW k, org.netlib.util.intW l, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] alpha, int _alpha_offset, double[] beta, int _beta_offset, double[] u, int _u_offset, int ldu, double[] v, int _v_offset, int ldv, double[] q, int _q_offset, int ldq, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dggsvd(jobu, jobv, jobq, m, n, p, k, l, a, _a_offset, lda, b, _b_offset, ldb, alpha, _alpha_offset, beta, _beta_offset, u, _u_offset, ldu, v, _v_offset, ldv, q, _q_offset, ldq, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dggsvpK(String jobu, String jobv, String jobq, int m, int p, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double tola, double tolb, org.netlib.util.intW k, org.netlib.util.intW l, double[] u, int _u_offset, int ldu, double[] v, int _v_offset, int ldv, double[] q, int _q_offset, int ldq, int[] iwork, int _iwork_offset, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dggsvp(jobu, jobv, jobq, m, p, n, a, _a_offset, lda, b, _b_offset, ldb, tola, tolb, k, l, u, _u_offset, ldu, v, _v_offset, ldv, q, _q_offset, ldq, iwork, _iwork_offset, tau, _tau_offset, work, _work_offset, info);
  }

  protected void dgtconK(String norm, int n, double[] dl, int _dl_offset, double[] d, int _d_offset, double[] du, int _du_offset, double[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, double anorm, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dgtcon(norm, n, dl, _dl_offset, d, _d_offset, du, _du_offset, du2, _du2_offset, ipiv, _ipiv_offset, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dgtrfsK(String trans, int n, int nrhs, double[] dl, int _dl_offset, double[] d, int _d_offset, double[] du, int _du_offset, double[] dlf, int _dlf_offset, double[] df, int _df_offset, double[] duf, int _duf_offset, double[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dgtrfs(trans, n, nrhs, dl, _dl_offset, d, _d_offset, du, _du_offset, dlf, _dlf_offset, df, _df_offset, duf, _duf_offset, du2, _du2_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dgtsvK(int n, int nrhs, double[] dl, int _dl_offset, double[] d, int _d_offset, double[] du, int _du_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dgtsv(n, nrhs, dl, _dl_offset, d, _d_offset, du, _du_offset, b, _b_offset, ldb, info);
  }

  protected void dgtsvxK(String fact, String trans, int n, int nrhs, double[] dl, int _dl_offset, double[] d, int _d_offset, double[] du, int _du_offset, double[] dlf, int _dlf_offset, double[] df, int _df_offset, double[] duf, int _duf_offset, double[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dgtsvx(fact, trans, n, nrhs, dl, _dl_offset, d, _d_offset, du, _du_offset, dlf, _dlf_offset, df, _df_offset, duf, _duf_offset, du2, _du2_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dgttrfK(int n, double[] dl, int _dl_offset, double[] d, int _d_offset, double[] du, int _du_offset, double[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    lapack.dgttrf(n, dl, _dl_offset, d, _d_offset, du, _du_offset, du2, _du2_offset, ipiv, _ipiv_offset, info);
  }

  protected void dgttrsK(String trans, int n, int nrhs, double[] dl, int _dl_offset, double[] d, int _d_offset, double[] du, int _du_offset, double[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dgttrs(trans, n, nrhs, dl, _dl_offset, d, _d_offset, du, _du_offset, du2, _du2_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  protected void dgtts2K(int itrans, int n, int nrhs, double[] dl, int _dl_offset, double[] d, int _d_offset, double[] du, int _du_offset, double[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb) {
    lapack.dgtts2(itrans, n, nrhs, dl, _dl_offset, d, _d_offset, du, _du_offset, du2, _du2_offset, ipiv, _ipiv_offset, b, _b_offset, ldb);
  }

  protected void dhgeqzK(String job, String compq, String compz, int n, int ilo, int ihi, double[] h, int _h_offset, int ldh, double[] t, int _t_offset, int ldt, double[] alphar, int _alphar_offset, double[] alphai, int _alphai_offset, double[] beta, int _beta_offset, double[] q, int _q_offset, int ldq, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dhgeqz(job, compq, compz, n, ilo, ihi, h, _h_offset, ldh, t, _t_offset, ldt, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, q, _q_offset, ldq, z, _z_offset, ldz, work, _work_offset, lwork, info);
  }

  protected void dhseinK(String side, String eigsrc, String initv, boolean[] select, int _select_offset, int n, double[] h, int _h_offset, int ldh, double[] wr, int _wr_offset, double[] wi, int _wi_offset, double[] vl, int _vl_offset, int ldvl, double[] vr, int _vr_offset, int ldvr, int mm, org.netlib.util.intW m, double[] work, int _work_offset, int[] ifaill, int _ifaill_offset, int[] ifailr, int _ifailr_offset, org.netlib.util.intW info) {
    lapack.dhsein(side, eigsrc, initv, select, _select_offset, n, h, _h_offset, ldh, wr, _wr_offset, wi, _wi_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, mm, m, work, _work_offset, ifaill, _ifaill_offset, ifailr, _ifailr_offset, info);
  }

  protected void dhseqrK(String job, String compz, int n, int ilo, int ihi, double[] h, int _h_offset, int ldh, double[] wr, int _wr_offset, double[] wi, int _wi_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dhseqr(job, compz, n, ilo, ihi, h, _h_offset, ldh, wr, _wr_offset, wi, _wi_offset, z, _z_offset, ldz, work, _work_offset, lwork, info);
  }

  protected boolean disnanK(double din) {
    return lapack.disnan(din);
  }

  protected void dlabadK(org.netlib.util.doubleW small, org.netlib.util.doubleW large) {
    lapack.dlabad(small, large);
  }

  protected void dlabrdK(int m, int n, int nb, double[] a, int _a_offset, int lda, double[] d, int _d_offset, double[] e, int _e_offset, double[] tauq, int _tauq_offset, double[] taup, int _taup_offset, double[] x, int _x_offset, int ldx, double[] y, int _y_offset, int ldy) {
    lapack.dlabrd(m, n, nb, a, _a_offset, lda, d, _d_offset, e, _e_offset, tauq, _tauq_offset, taup, _taup_offset, x, _x_offset, ldx, y, _y_offset, ldy);
  }

  protected void dlacn2K(int n, double[] v, int _v_offset, double[] x, int _x_offset, int[] isgn, int _isgn_offset, org.netlib.util.doubleW est, org.netlib.util.intW kase, int[] isave, int _isave_offset) {
    lapack.dlacn2(n, v, _v_offset, x, _x_offset, isgn, _isgn_offset, est, kase, isave, _isave_offset);
  }

  protected void dlaconK(int n, double[] v, int _v_offset, double[] x, int _x_offset, int[] isgn, int _isgn_offset, org.netlib.util.doubleW est, org.netlib.util.intW kase) {
    lapack.dlacon(n, v, _v_offset, x, _x_offset, isgn, _isgn_offset, est, kase);
  }

  protected void dlacpyK(String uplo, int m, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb) {
    lapack.dlacpy(uplo, m, n, a, _a_offset, lda, b, _b_offset, ldb);
  }

  protected void dladivK(double a, double b, double c, double d, org.netlib.util.doubleW p, org.netlib.util.doubleW q) {
    lapack.dladiv(a, b, c, d, p, q);
  }

  protected void dlae2K(double a, double b, double c, org.netlib.util.doubleW rt1, org.netlib.util.doubleW rt2) {
    lapack.dlae2(a, b, c, rt1, rt2);
  }

  protected void dlaebzK(int ijob, int nitmax, int n, int mmax, int minp, int nbmin, double abstol, double reltol, double pivmin, double[] d, int _d_offset, double[] e, int _e_offset, double[] e2, int _e2_offset, int[] nval, int _nval_offset, double[] ab, int _ab_offset, double[] c, int _c_offset, org.netlib.util.intW mout, int[] nab, int _nab_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dlaebz(ijob, nitmax, n, mmax, minp, nbmin, abstol, reltol, pivmin, d, _d_offset, e, _e_offset, e2, _e2_offset, nval, _nval_offset, ab, _ab_offset, c, _c_offset, mout, nab, _nab_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dlaed0K(int icompq, int qsiz, int n, double[] d, int _d_offset, double[] e, int _e_offset, double[] q, int _q_offset, int ldq, double[] qstore, int _qstore_offset, int ldqs, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dlaed0(icompq, qsiz, n, d, _d_offset, e, _e_offset, q, _q_offset, ldq, qstore, _qstore_offset, ldqs, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dlaed1K(int n, double[] d, int _d_offset, double[] q, int _q_offset, int ldq, int[] indxq, int _indxq_offset, org.netlib.util.doubleW rho, int cutpnt, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dlaed1(n, d, _d_offset, q, _q_offset, ldq, indxq, _indxq_offset, rho, cutpnt, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dlaed2K(org.netlib.util.intW k, int n, int n1, double[] d, int _d_offset, double[] q, int _q_offset, int ldq, int[] indxq, int _indxq_offset, org.netlib.util.doubleW rho, double[] z, int _z_offset, double[] dlamda, int _dlamda_offset, double[] w, int _w_offset, double[] q2, int _q2_offset, int[] indx, int _indx_offset, int[] indxc, int _indxc_offset, int[] indxp, int _indxp_offset, int[] coltyp, int _coltyp_offset, org.netlib.util.intW info) {
    lapack.dlaed2(k, n, n1, d, _d_offset, q, _q_offset, ldq, indxq, _indxq_offset, rho, z, _z_offset, dlamda, _dlamda_offset, w, _w_offset, q2, _q2_offset, indx, _indx_offset, indxc, _indxc_offset, indxp, _indxp_offset, coltyp, _coltyp_offset, info);
  }

  protected void dlaed3K(int k, int n, int n1, double[] d, int _d_offset, double[] q, int _q_offset, int ldq, double rho, double[] dlamda, int _dlamda_offset, double[] q2, int _q2_offset, int[] indx, int _indx_offset, int[] ctot, int _ctot_offset, double[] w, int _w_offset, double[] s, int _s_offset, org.netlib.util.intW info) {
    lapack.dlaed3(k, n, n1, d, _d_offset, q, _q_offset, ldq, rho, dlamda, _dlamda_offset, q2, _q2_offset, indx, _indx_offset, ctot, _ctot_offset, w, _w_offset, s, _s_offset, info);
  }

  protected void dlaed4K(int n, int i, double[] d, int _d_offset, double[] z, int _z_offset, double[] delta, int _delta_offset, double rho, org.netlib.util.doubleW dlam, org.netlib.util.intW info) {
    lapack.dlaed4(n, i, d, _d_offset, z, _z_offset, delta, _delta_offset, rho, dlam, info);
  }

  protected void dlaed5K(int i, double[] d, int _d_offset, double[] z, int _z_offset, double[] delta, int _delta_offset, double rho, org.netlib.util.doubleW dlam) {
    lapack.dlaed5(i, d, _d_offset, z, _z_offset, delta, _delta_offset, rho, dlam);
  }

  protected void dlaed6K(int kniter, boolean orgati, double rho, double[] d, int _d_offset, double[] z, int _z_offset, double finit, org.netlib.util.doubleW tau, org.netlib.util.intW info) {
    lapack.dlaed6(kniter, orgati, rho, d, _d_offset, z, _z_offset, finit, tau, info);
  }

  protected void dlaed7K(int icompq, int n, int qsiz, int tlvls, int curlvl, int curpbm, double[] d, int _d_offset, double[] q, int _q_offset, int ldq, int[] indxq, int _indxq_offset, org.netlib.util.doubleW rho, int cutpnt, double[] qstore, int _qstore_offset, int[] qptr, int _qptr_offset, int[] prmptr, int _prmptr_offset, int[] perm, int _perm_offset, int[] givptr, int _givptr_offset, int[] givcol, int _givcol_offset, double[] givnum, int _givnum_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dlaed7(icompq, n, qsiz, tlvls, curlvl, curpbm, d, _d_offset, q, _q_offset, ldq, indxq, _indxq_offset, rho, cutpnt, qstore, _qstore_offset, qptr, _qptr_offset, prmptr, _prmptr_offset, perm, _perm_offset, givptr, _givptr_offset, givcol, _givcol_offset, givnum, _givnum_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dlaed8K(int icompq, org.netlib.util.intW k, int n, int qsiz, double[] d, int _d_offset, double[] q, int _q_offset, int ldq, int[] indxq, int _indxq_offset, org.netlib.util.doubleW rho, int cutpnt, double[] z, int _z_offset, double[] dlamda, int _dlamda_offset, double[] q2, int _q2_offset, int ldq2, double[] w, int _w_offset, int[] perm, int _perm_offset, org.netlib.util.intW givptr, int[] givcol, int _givcol_offset, double[] givnum, int _givnum_offset, int[] indxp, int _indxp_offset, int[] indx, int _indx_offset, org.netlib.util.intW info) {
    lapack.dlaed8(icompq, k, n, qsiz, d, _d_offset, q, _q_offset, ldq, indxq, _indxq_offset, rho, cutpnt, z, _z_offset, dlamda, _dlamda_offset, q2, _q2_offset, ldq2, w, _w_offset, perm, _perm_offset, givptr, givcol, _givcol_offset, givnum, _givnum_offset, indxp, _indxp_offset, indx, _indx_offset, info);
  }

  protected void dlaed9K(int k, int kstart, int kstop, int n, double[] d, int _d_offset, double[] q, int _q_offset, int ldq, double rho, double[] dlamda, int _dlamda_offset, double[] w, int _w_offset, double[] s, int _s_offset, int lds, org.netlib.util.intW info) {
    lapack.dlaed9(k, kstart, kstop, n, d, _d_offset, q, _q_offset, ldq, rho, dlamda, _dlamda_offset, w, _w_offset, s, _s_offset, lds, info);
  }

  protected void dlaedaK(int n, int tlvls, int curlvl, int curpbm, int[] prmptr, int _prmptr_offset, int[] perm, int _perm_offset, int[] givptr, int _givptr_offset, int[] givcol, int _givcol_offset, double[] givnum, int _givnum_offset, double[] q, int _q_offset, int[] qptr, int _qptr_offset, double[] z, int _z_offset, double[] ztemp, int _ztemp_offset, org.netlib.util.intW info) {
    lapack.dlaeda(n, tlvls, curlvl, curpbm, prmptr, _prmptr_offset, perm, _perm_offset, givptr, _givptr_offset, givcol, _givcol_offset, givnum, _givnum_offset, q, _q_offset, qptr, _qptr_offset, z, _z_offset, ztemp, _ztemp_offset, info);
  }

  protected void dlaeinK(boolean rightv, boolean noinit, int n, double[] h, int _h_offset, int ldh, double wr, double wi, double[] vr, int _vr_offset, double[] vi, int _vi_offset, double[] b, int _b_offset, int ldb, double[] work, int _work_offset, double eps3, double smlnum, double bignum, org.netlib.util.intW info) {
    lapack.dlaein(rightv, noinit, n, h, _h_offset, ldh, wr, wi, vr, _vr_offset, vi, _vi_offset, b, _b_offset, ldb, work, _work_offset, eps3, smlnum, bignum, info);
  }

  protected void dlaev2K(double a, double b, double c, org.netlib.util.doubleW rt1, org.netlib.util.doubleW rt2, org.netlib.util.doubleW cs1, org.netlib.util.doubleW sn1) {
    lapack.dlaev2(a, b, c, rt1, rt2, cs1, sn1);
  }

  protected void dlaexcK(boolean wantq, int n, double[] t, int _t_offset, int ldt, double[] q, int _q_offset, int ldq, int j1, int n1, int n2, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dlaexc(wantq, n, t, _t_offset, ldt, q, _q_offset, ldq, j1, n1, n2, work, _work_offset, info);
  }

  protected void dlag2K(double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double safmin, org.netlib.util.doubleW scale1, org.netlib.util.doubleW scale2, org.netlib.util.doubleW wr1, org.netlib.util.doubleW wr2, org.netlib.util.doubleW wi) {
    lapack.dlag2(a, _a_offset, lda, b, _b_offset, ldb, safmin, scale1, scale2, wr1, wr2, wi);
  }

  protected void dlag2sK(int m, int n, double[] a, int _a_offset, int lda, float[] sa, int _sa_offset, int ldsa, org.netlib.util.intW info) {
    lapack.dlag2s(m, n, a, _a_offset, lda, sa, _sa_offset, ldsa, info);
  }

  protected void dlags2K(boolean upper, double a1, double a2, double a3, double b1, double b2, double b3, org.netlib.util.doubleW csu, org.netlib.util.doubleW snu, org.netlib.util.doubleW csv, org.netlib.util.doubleW snv, org.netlib.util.doubleW csq, org.netlib.util.doubleW snq) {
    lapack.dlags2(upper, a1, a2, a3, b1, b2, b3, csu, snu, csv, snv, csq, snq);
  }

  protected void dlagtfK(int n, double[] a, int _a_offset, double lambda, double[] b, int _b_offset, double[] c, int _c_offset, double tol, double[] d, int _d_offset, int[] in, int _in_offset, org.netlib.util.intW info) {
    lapack.dlagtf(n, a, _a_offset, lambda, b, _b_offset, c, _c_offset, tol, d, _d_offset, in, _in_offset, info);
  }

  protected void dlagtmK(String trans, int n, int nrhs, double alpha, double[] dl, int _dl_offset, double[] d, int _d_offset, double[] du, int _du_offset, double[] x, int _x_offset, int ldx, double beta, double[] b, int _b_offset, int ldb) {
    lapack.dlagtm(trans, n, nrhs, alpha, dl, _dl_offset, d, _d_offset, du, _du_offset, x, _x_offset, ldx, beta, b, _b_offset, ldb);
  }

  protected void dlagtsK(int job, int n, double[] a, int _a_offset, double[] b, int _b_offset, double[] c, int _c_offset, double[] d, int _d_offset, int[] in, int _in_offset, double[] y, int _y_offset, org.netlib.util.doubleW tol, org.netlib.util.intW info) {
    lapack.dlagts(job, n, a, _a_offset, b, _b_offset, c, _c_offset, d, _d_offset, in, _in_offset, y, _y_offset, tol, info);
  }

  protected void dlagv2K(double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] alphar, int _alphar_offset, double[] alphai, int _alphai_offset, double[] beta, int _beta_offset, org.netlib.util.doubleW csl, org.netlib.util.doubleW snl, org.netlib.util.doubleW csr, org.netlib.util.doubleW snr) {
    lapack.dlagv2(a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, csl, snl, csr, snr);
  }

  protected void dlahqrK(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int _h_offset, int ldh, double[] wr, int _wr_offset, double[] wi, int _wi_offset, int iloz, int ihiz, double[] z, int _z_offset, int ldz, org.netlib.util.intW info) {
    lapack.dlahqr(wantt, wantz, n, ilo, ihi, h, _h_offset, ldh, wr, _wr_offset, wi, _wi_offset, iloz, ihiz, z, _z_offset, ldz, info);
  }

  protected void dlahr2K(int n, int k, int nb, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] t, int _t_offset, int ldt, double[] y, int _y_offset, int ldy) {
    lapack.dlahr2(n, k, nb, a, _a_offset, lda, tau, _tau_offset, t, _t_offset, ldt, y, _y_offset, ldy);
  }

  protected void dlahrdK(int n, int k, int nb, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] t, int _t_offset, int ldt, double[] y, int _y_offset, int ldy) {
    lapack.dlahrd(n, k, nb, a, _a_offset, lda, tau, _tau_offset, t, _t_offset, ldt, y, _y_offset, ldy);
  }

  protected void dlaic1K(int job, int j, double[] x, int _x_offset, double sest, double[] w, int _w_offset, double gamma, org.netlib.util.doubleW sestpr, org.netlib.util.doubleW s, org.netlib.util.doubleW c) {
    lapack.dlaic1(job, j, x, _x_offset, sest, w, _w_offset, gamma, sestpr, s, c);
  }

  protected boolean dlaisnanK(double din1, double din2) {
    return lapack.dlaisnan(din1, din2);
  }

  protected void dlaln2K(boolean ltrans, int na, int nw, double smin, double ca, double[] a, int _a_offset, int lda, double d1, double d2, double[] b, int _b_offset, int ldb, double wr, double wi, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW scale, org.netlib.util.doubleW xnorm, org.netlib.util.intW info) {
    lapack.dlaln2(ltrans, na, nw, smin, ca, a, _a_offset, lda, d1, d2, b, _b_offset, ldb, wr, wi, x, _x_offset, ldx, scale, xnorm, info);
  }

  protected void dlals0K(int icompq, int nl, int nr, int sqre, int nrhs, double[] b, int _b_offset, int ldb, double[] bx, int _bx_offset, int ldbx, int[] perm, int _perm_offset, int givptr, int[] givcol, int _givcol_offset, int ldgcol, double[] givnum, int _givnum_offset, int ldgnum, double[] poles, int _poles_offset, double[] difl, int _difl_offset, double[] difr, int _difr_offset, double[] z, int _z_offset, int k, double c, double s, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dlals0(icompq, nl, nr, sqre, nrhs, b, _b_offset, ldb, bx, _bx_offset, ldbx, perm, _perm_offset, givptr, givcol, _givcol_offset, ldgcol, givnum, _givnum_offset, ldgnum, poles, _poles_offset, difl, _difl_offset, difr, _difr_offset, z, _z_offset, k, c, s, work, _work_offset, info);
  }

  protected void dlalsaK(int icompq, int smlsiz, int n, int nrhs, double[] b, int _b_offset, int ldb, double[] bx, int _bx_offset, int ldbx, double[] u, int _u_offset, int ldu, double[] vt, int _vt_offset, int[] k, int _k_offset, double[] difl, int _difl_offset, double[] difr, int _difr_offset, double[] z, int _z_offset, double[] poles, int _poles_offset, int[] givptr, int _givptr_offset, int[] givcol, int _givcol_offset, int ldgcol, int[] perm, int _perm_offset, double[] givnum, int _givnum_offset, double[] c, int _c_offset, double[] s, int _s_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dlalsa(icompq, smlsiz, n, nrhs, b, _b_offset, ldb, bx, _bx_offset, ldbx, u, _u_offset, ldu, vt, _vt_offset, k, _k_offset, difl, _difl_offset, difr, _difr_offset, z, _z_offset, poles, _poles_offset, givptr, _givptr_offset, givcol, _givcol_offset, ldgcol, perm, _perm_offset, givnum, _givnum_offset, c, _c_offset, s, _s_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dlalsdK(String uplo, int smlsiz, int n, int nrhs, double[] d, int _d_offset, double[] e, int _e_offset, double[] b, int _b_offset, int ldb, double rcond, org.netlib.util.intW rank, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dlalsd(uplo, smlsiz, n, nrhs, d, _d_offset, e, _e_offset, b, _b_offset, ldb, rcond, rank, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dlamrgK(int n1, int n2, double[] a, int _a_offset, int dtrd1, int dtrd2, int[] index, int _index_offset) {
    lapack.dlamrg(n1, n2, a, _a_offset, dtrd1, dtrd2, index, _index_offset);
  }

  protected int dlanegK(int n, double[] d, int _d_offset, double[] lld, int _lld_offset, double sigma, double pivmin, int r) {
    return lapack.dlaneg(n, d, _d_offset, lld, _lld_offset, sigma, pivmin, r);
  }

  protected double dlangbK(String norm, int n, int kl, int ku, double[] ab, int _ab_offset, int ldab, double[] work, int _work_offset) {
    return lapack.dlangb(norm, n, kl, ku, ab, _ab_offset, ldab, work, _work_offset);
  }

  protected double dlangeK(String norm, int m, int n, double[] a, int _a_offset, int lda, double[] work, int _work_offset) {
    return lapack.dlange(norm, m, n, a, _a_offset, lda, work, _work_offset);
  }

  protected double dlangtK(String norm, int n, double[] dl, int _dl_offset, double[] d, int _d_offset, double[] du, int _du_offset) {
    return lapack.dlangt(norm, n, dl, _dl_offset, d, _d_offset, du, _du_offset);
  }

  protected double dlanhsK(String norm, int n, double[] a, int _a_offset, int lda, double[] work, int _work_offset) {
    return lapack.dlanhs(norm, n, a, _a_offset, lda, work, _work_offset);
  }

  protected double dlansbK(String norm, String uplo, int n, int k, double[] ab, int _ab_offset, int ldab, double[] work, int _work_offset) {
    return lapack.dlansb(norm, uplo, n, k, ab, _ab_offset, ldab, work, _work_offset);
  }

  protected double dlanspK(String norm, String uplo, int n, double[] ap, int _ap_offset, double[] work, int _work_offset) {
    return lapack.dlansp(norm, uplo, n, ap, _ap_offset, work, _work_offset);
  }

  protected double dlanstK(String norm, int n, double[] d, int _d_offset, double[] e, int _e_offset) {
    return lapack.dlanst(norm, n, d, _d_offset, e, _e_offset);
  }

  protected double dlansyK(String norm, String uplo, int n, double[] a, int _a_offset, int lda, double[] work, int _work_offset) {
    return lapack.dlansy(norm, uplo, n, a, _a_offset, lda, work, _work_offset);
  }

  protected double dlantbK(String norm, String uplo, String diag, int n, int k, double[] ab, int _ab_offset, int ldab, double[] work, int _work_offset) {
    return lapack.dlantb(norm, uplo, diag, n, k, ab, _ab_offset, ldab, work, _work_offset);
  }

  protected double dlantpK(String norm, String uplo, String diag, int n, double[] ap, int _ap_offset, double[] work, int _work_offset) {
    return lapack.dlantp(norm, uplo, diag, n, ap, _ap_offset, work, _work_offset);
  }

  protected double dlantrK(String norm, String uplo, String diag, int m, int n, double[] a, int _a_offset, int lda, double[] work, int _work_offset) {
    return lapack.dlantr(norm, uplo, diag, m, n, a, _a_offset, lda, work, _work_offset);
  }

  protected void dlanv2K(org.netlib.util.doubleW a, org.netlib.util.doubleW b, org.netlib.util.doubleW c, org.netlib.util.doubleW d, org.netlib.util.doubleW rt1r, org.netlib.util.doubleW rt1i, org.netlib.util.doubleW rt2r, org.netlib.util.doubleW rt2i, org.netlib.util.doubleW cs, org.netlib.util.doubleW sn) {
    lapack.dlanv2(a, b, c, d, rt1r, rt1i, rt2r, rt2i, cs, sn);
  }

  protected void dlapllK(int n, double[] x, int _x_offset, int incx, double[] y, int _y_offset, int incy, org.netlib.util.doubleW ssmin) {
    lapack.dlapll(n, x, _x_offset, incx, y, _y_offset, incy, ssmin);
  }

  protected void dlapmtK(boolean forwrd, int m, int n, double[] x, int _x_offset, int ldx, int[] k, int _k_offset) {
    lapack.dlapmt(forwrd, m, n, x, _x_offset, ldx, k, _k_offset);
  }

  protected double dlapy2K(double x, double y) {
    return lapack.dlapy2(x, y);
  }

  protected double dlapy3K(double x, double y, double z) {
    return lapack.dlapy3(x, y, z);
  }

  protected void dlaqgbK(int m, int n, int kl, int ku, double[] ab, int _ab_offset, int ldab, double[] r, int _r_offset, double[] c, int _c_offset, double rowcnd, double colcnd, double amax, org.netlib.util.StringW equed) {
    lapack.dlaqgb(m, n, kl, ku, ab, _ab_offset, ldab, r, _r_offset, c, _c_offset, rowcnd, colcnd, amax, equed);
  }

  protected void dlaqgeK(int m, int n, double[] a, int _a_offset, int lda, double[] r, int _r_offset, double[] c, int _c_offset, double rowcnd, double colcnd, double amax, org.netlib.util.StringW equed) {
    lapack.dlaqge(m, n, a, _a_offset, lda, r, _r_offset, c, _c_offset, rowcnd, colcnd, amax, equed);
  }

  protected void dlaqp2K(int m, int n, int offset, double[] a, int _a_offset, int lda, int[] jpvt, int _jpvt_offset, double[] tau, int _tau_offset, double[] vn1, int _vn1_offset, double[] vn2, int _vn2_offset, double[] work, int _work_offset) {
    lapack.dlaqp2(m, n, offset, a, _a_offset, lda, jpvt, _jpvt_offset, tau, _tau_offset, vn1, _vn1_offset, vn2, _vn2_offset, work, _work_offset);
  }

  protected void dlaqpsK(int m, int n, int offset, int nb, org.netlib.util.intW kb, double[] a, int _a_offset, int lda, int[] jpvt, int _jpvt_offset, double[] tau, int _tau_offset, double[] vn1, int _vn1_offset, double[] vn2, int _vn2_offset, double[] auxv, int _auxv_offset, double[] f, int _f_offset, int ldf) {
    lapack.dlaqps(m, n, offset, nb, kb, a, _a_offset, lda, jpvt, _jpvt_offset, tau, _tau_offset, vn1, _vn1_offset, vn2, _vn2_offset, auxv, _auxv_offset, f, _f_offset, ldf);
  }

  protected void dlaqr0K(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int _h_offset, int ldh, double[] wr, int _wr_offset, double[] wi, int _wi_offset, int iloz, int ihiz, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dlaqr0(wantt, wantz, n, ilo, ihi, h, _h_offset, ldh, wr, _wr_offset, wi, _wi_offset, iloz, ihiz, z, _z_offset, ldz, work, _work_offset, lwork, info);
  }

  protected void dlaqr1K(int n, double[] h, int _h_offset, int ldh, double sr1, double si1, double sr2, double si2, double[] v, int _v_offset) {
    lapack.dlaqr1(n, h, _h_offset, ldh, sr1, si1, sr2, si2, v, _v_offset);
  }

  protected void dlaqr2K(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, double[] h, int _h_offset, int ldh, int iloz, int ihiz, double[] z, int _z_offset, int ldz, org.netlib.util.intW ns, org.netlib.util.intW nd, double[] sr, int _sr_offset, double[] si, int _si_offset, double[] v, int _v_offset, int ldv, int nh, double[] t, int _t_offset, int ldt, int nv, double[] wv, int _wv_offset, int ldwv, double[] work, int _work_offset, int lwork) {
    lapack.dlaqr2(wantt, wantz, n, ktop, kbot, nw, h, _h_offset, ldh, iloz, ihiz, z, _z_offset, ldz, ns, nd, sr, _sr_offset, si, _si_offset, v, _v_offset, ldv, nh, t, _t_offset, ldt, nv, wv, _wv_offset, ldwv, work, _work_offset, lwork);
  }

  protected void dlaqr3K(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, double[] h, int _h_offset, int ldh, int iloz, int ihiz, double[] z, int _z_offset, int ldz, org.netlib.util.intW ns, org.netlib.util.intW nd, double[] sr, int _sr_offset, double[] si, int _si_offset, double[] v, int _v_offset, int ldv, int nh, double[] t, int _t_offset, int ldt, int nv, double[] wv, int _wv_offset, int ldwv, double[] work, int _work_offset, int lwork) {
    lapack.dlaqr3(wantt, wantz, n, ktop, kbot, nw, h, _h_offset, ldh, iloz, ihiz, z, _z_offset, ldz, ns, nd, sr, _sr_offset, si, _si_offset, v, _v_offset, ldv, nh, t, _t_offset, ldt, nv, wv, _wv_offset, ldwv, work, _work_offset, lwork);
  }

  protected void dlaqr4K(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int _h_offset, int ldh, double[] wr, int _wr_offset, double[] wi, int _wi_offset, int iloz, int ihiz, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dlaqr4(wantt, wantz, n, ilo, ihi, h, _h_offset, ldh, wr, _wr_offset, wi, _wi_offset, iloz, ihiz, z, _z_offset, ldz, work, _work_offset, lwork, info);
  }

  protected void dlaqr5K(boolean wantt, boolean wantz, int kacc22, int n, int ktop, int kbot, int nshfts, double[] sr, int _sr_offset, double[] si, int _si_offset, double[] h, int _h_offset, int ldh, int iloz, int ihiz, double[] z, int _z_offset, int ldz, double[] v, int _v_offset, int ldv, double[] u, int _u_offset, int ldu, int nv, double[] wv, int _wv_offset, int ldwv, int nh, double[] wh, int _wh_offset, int ldwh) {
    lapack.dlaqr5(wantt, wantz, kacc22, n, ktop, kbot, nshfts, sr, _sr_offset, si, _si_offset, h, _h_offset, ldh, iloz, ihiz, z, _z_offset, ldz, v, _v_offset, ldv, u, _u_offset, ldu, nv, wv, _wv_offset, ldwv, nh, wh, _wh_offset, ldwh);
  }

  protected void dlaqsbK(String uplo, int n, int kd, double[] ab, int _ab_offset, int ldab, double[] s, int _s_offset, double scond, double amax, org.netlib.util.StringW equed) {
    lapack.dlaqsb(uplo, n, kd, ab, _ab_offset, ldab, s, _s_offset, scond, amax, equed);
  }

  protected void dlaqspK(String uplo, int n, double[] ap, int _ap_offset, double[] s, int _s_offset, double scond, double amax, org.netlib.util.StringW equed) {
    lapack.dlaqsp(uplo, n, ap, _ap_offset, s, _s_offset, scond, amax, equed);
  }

  protected void dlaqsyK(String uplo, int n, double[] a, int _a_offset, int lda, double[] s, int _s_offset, double scond, double amax, org.netlib.util.StringW equed) {
    lapack.dlaqsy(uplo, n, a, _a_offset, lda, s, _s_offset, scond, amax, equed);
  }

  protected void dlaqtrK(boolean ltran, boolean lreal, int n, double[] t, int _t_offset, int ldt, double[] b, int _b_offset, double w, org.netlib.util.doubleW scale, double[] x, int _x_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dlaqtr(ltran, lreal, n, t, _t_offset, ldt, b, _b_offset, w, scale, x, _x_offset, work, _work_offset, info);
  }

  protected void dlar1vK(int n, int b1, int bn, double lambda, double[] d, int _d_offset, double[] l, int _l_offset, double[] ld, int _ld_offset, double[] lld, int _lld_offset, double pivmin, double gaptol, double[] z, int _z_offset, boolean wantnc, org.netlib.util.intW negcnt, org.netlib.util.doubleW ztz, org.netlib.util.doubleW mingma, org.netlib.util.intW r, int[] isuppz, int _isuppz_offset, org.netlib.util.doubleW nrminv, org.netlib.util.doubleW resid, org.netlib.util.doubleW rqcorr, double[] work, int _work_offset) {
    lapack.dlar1v(n, b1, bn, lambda, d, _d_offset, l, _l_offset, ld, _ld_offset, lld, _lld_offset, pivmin, gaptol, z, _z_offset, wantnc, negcnt, ztz, mingma, r, isuppz, _isuppz_offset, nrminv, resid, rqcorr, work, _work_offset);
  }

  protected void dlar2vK(int n, double[] x, int _x_offset, double[] y, int _y_offset, double[] z, int _z_offset, int incx, double[] c, int _c_offset, double[] s, int _s_offset, int incc) {
    lapack.dlar2v(n, x, _x_offset, y, _y_offset, z, _z_offset, incx, c, _c_offset, s, _s_offset, incc);
  }

  protected void dlarfK(String side, int m, int n, double[] v, int _v_offset, int incv, double tau, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset) {
    lapack.dlarf(side, m, n, v, _v_offset, incv, tau, c, _c_offset, Ldc, work, _work_offset);
  }

  protected void dlarfbK(String side, String trans, String direct, String storev, int m, int n, int k, double[] v, int _v_offset, int ldv, double[] t, int _t_offset, int ldt, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, int ldwork) {
    lapack.dlarfb(side, trans, direct, storev, m, n, k, v, _v_offset, ldv, t, _t_offset, ldt, c, _c_offset, Ldc, work, _work_offset, ldwork);
  }

  protected void dlarfgK(int n, org.netlib.util.doubleW alpha, double[] x, int _x_offset, int incx, org.netlib.util.doubleW tau) {
    lapack.dlarfg(n, alpha, x, _x_offset, incx, tau);
  }

  protected void dlarftK(String direct, String storev, int n, int k, double[] v, int _v_offset, int ldv, double[] tau, int _tau_offset, double[] t, int _t_offset, int ldt) {
    lapack.dlarft(direct, storev, n, k, v, _v_offset, ldv, tau, _tau_offset, t, _t_offset, ldt);
  }

  protected void dlarfxK(String side, int m, int n, double[] v, int _v_offset, double tau, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset) {
    lapack.dlarfx(side, m, n, v, _v_offset, tau, c, _c_offset, Ldc, work, _work_offset);
  }

  protected void dlargvK(int n, double[] x, int _x_offset, int incx, double[] y, int _y_offset, int incy, double[] c, int _c_offset, int incc) {
    lapack.dlargv(n, x, _x_offset, incx, y, _y_offset, incy, c, _c_offset, incc);
  }

  protected void dlarnvK(int idist, int[] iseed, int _iseed_offset, int n, double[] x, int _x_offset) {
    lapack.dlarnv(idist, iseed, _iseed_offset, n, x, _x_offset);
  }

  protected void dlarraK(int n, double[] d, int _d_offset, double[] e, int _e_offset, double[] e2, int _e2_offset, double spltol, double tnrm, org.netlib.util.intW nsplit, int[] isplit, int _isplit_offset, org.netlib.util.intW info) {
    lapack.dlarra(n, d, _d_offset, e, _e_offset, e2, _e2_offset, spltol, tnrm, nsplit, isplit, _isplit_offset, info);
  }

  protected void dlarrbK(int n, double[] d, int _d_offset, double[] lld, int _lld_offset, int ifirst, int ilast, double rtol1, double rtol2, int offset, double[] w, int _w_offset, double[] wgap, int _wgap_offset, double[] werr, int _werr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, double pivmin, double spdiam, int twist, org.netlib.util.intW info) {
    lapack.dlarrb(n, d, _d_offset, lld, _lld_offset, ifirst, ilast, rtol1, rtol2, offset, w, _w_offset, wgap, _wgap_offset, werr, _werr_offset, work, _work_offset, iwork, _iwork_offset, pivmin, spdiam, twist, info);
  }

  protected void dlarrcK(String jobt, int n, double vl, double vu, double[] d, int _d_offset, double[] e, int _e_offset, double pivmin, org.netlib.util.intW eigcnt, org.netlib.util.intW lcnt, org.netlib.util.intW rcnt, org.netlib.util.intW info) {
    lapack.dlarrc(jobt, n, vl, vu, d, _d_offset, e, _e_offset, pivmin, eigcnt, lcnt, rcnt, info);
  }

  protected void dlarrdK(String range, String order, int n, double vl, double vu, int il, int iu, double[] gers, int _gers_offset, double reltol, double[] d, int _d_offset, double[] e, int _e_offset, double[] e2, int _e2_offset, double pivmin, int nsplit, int[] isplit, int _isplit_offset, org.netlib.util.intW m, double[] w, int _w_offset, double[] werr, int _werr_offset, org.netlib.util.doubleW wl, org.netlib.util.doubleW wu, int[] iblock, int _iblock_offset, int[] indexw, int _indexw_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dlarrd(range, order, n, vl, vu, il, iu, gers, _gers_offset, reltol, d, _d_offset, e, _e_offset, e2, _e2_offset, pivmin, nsplit, isplit, _isplit_offset, m, w, _w_offset, werr, _werr_offset, wl, wu, iblock, _iblock_offset, indexw, _indexw_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dlarreK(String range, int n, org.netlib.util.doubleW vl, org.netlib.util.doubleW vu, int il, int iu, double[] d, int _d_offset, double[] e, int _e_offset, double[] e2, int _e2_offset, double rtol1, double rtol2, double spltol, org.netlib.util.intW nsplit, int[] isplit, int _isplit_offset, org.netlib.util.intW m, double[] w, int _w_offset, double[] werr, int _werr_offset, double[] wgap, int _wgap_offset, int[] iblock, int _iblock_offset, int[] indexw, int _indexw_offset, double[] gers, int _gers_offset, org.netlib.util.doubleW pivmin, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dlarre(range, n, vl, vu, il, iu, d, _d_offset, e, _e_offset, e2, _e2_offset, rtol1, rtol2, spltol, nsplit, isplit, _isplit_offset, m, w, _w_offset, werr, _werr_offset, wgap, _wgap_offset, iblock, _iblock_offset, indexw, _indexw_offset, gers, _gers_offset, pivmin, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dlarrfK(int n, double[] d, int _d_offset, double[] l, int _l_offset, double[] ld, int _ld_offset, int clstrt, int clend, double[] w, int _w_offset, double[] wgap, int _wgap_offset, double[] werr, int _werr_offset, double spdiam, double clgapl, double clgapr, double pivmin, org.netlib.util.doubleW sigma, double[] dplus, int _dplus_offset, double[] lplus, int _lplus_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dlarrf(n, d, _d_offset, l, _l_offset, ld, _ld_offset, clstrt, clend, w, _w_offset, wgap, _wgap_offset, werr, _werr_offset, spdiam, clgapl, clgapr, pivmin, sigma, dplus, _dplus_offset, lplus, _lplus_offset, work, _work_offset, info);
  }

  protected void dlarrjK(int n, double[] d, int _d_offset, double[] e2, int _e2_offset, int ifirst, int ilast, double rtol, int offset, double[] w, int _w_offset, double[] werr, int _werr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, double pivmin, double spdiam, org.netlib.util.intW info) {
    lapack.dlarrj(n, d, _d_offset, e2, _e2_offset, ifirst, ilast, rtol, offset, w, _w_offset, werr, _werr_offset, work, _work_offset, iwork, _iwork_offset, pivmin, spdiam, info);
  }

  protected void dlarrkK(int n, int iw, double gl, double gu, double[] d, int _d_offset, double[] e2, int _e2_offset, double pivmin, double reltol, org.netlib.util.doubleW w, org.netlib.util.doubleW werr, org.netlib.util.intW info) {
    lapack.dlarrk(n, iw, gl, gu, d, _d_offset, e2, _e2_offset, pivmin, reltol, w, werr, info);
  }

  protected void dlarrrK(int n, double[] d, int _d_offset, double[] e, int _e_offset, org.netlib.util.intW info) {
    lapack.dlarrr(n, d, _d_offset, e, _e_offset, info);
  }

  protected void dlarrvK(int n, double vl, double vu, double[] d, int _d_offset, double[] l, int _l_offset, double pivmin, int[] isplit, int _isplit_offset, int m, int dol, int dou, double minrgp, org.netlib.util.doubleW rtol1, org.netlib.util.doubleW rtol2, double[] w, int _w_offset, double[] werr, int _werr_offset, double[] wgap, int _wgap_offset, int[] iblock, int _iblock_offset, int[] indexw, int _indexw_offset, double[] gers, int _gers_offset, double[] z, int _z_offset, int ldz, int[] isuppz, int _isuppz_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dlarrv(n, vl, vu, d, _d_offset, l, _l_offset, pivmin, isplit, _isplit_offset, m, dol, dou, minrgp, rtol1, rtol2, w, _w_offset, werr, _werr_offset, wgap, _wgap_offset, iblock, _iblock_offset, indexw, _indexw_offset, gers, _gers_offset, z, _z_offset, ldz, isuppz, _isuppz_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dlartgK(double f, double g, org.netlib.util.doubleW cs, org.netlib.util.doubleW sn, org.netlib.util.doubleW r) {
    lapack.dlartg(f, g, cs, sn, r);
  }

  protected void dlartvK(int n, double[] x, int _x_offset, int incx, double[] y, int _y_offset, int incy, double[] c, int _c_offset, double[] s, int _s_offset, int incc) {
    lapack.dlartv(n, x, _x_offset, incx, y, _y_offset, incy, c, _c_offset, s, _s_offset, incc);
  }

  protected void dlaruvK(int[] iseed, int _iseed_offset, int n, double[] x, int _x_offset) {
    lapack.dlaruv(iseed, _iseed_offset, n, x, _x_offset);
  }

  protected void dlarzK(String side, int m, int n, int l, double[] v, int _v_offset, int incv, double tau, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset) {
    lapack.dlarz(side, m, n, l, v, _v_offset, incv, tau, c, _c_offset, Ldc, work, _work_offset);
  }

  protected void dlarzbK(String side, String trans, String direct, String storev, int m, int n, int k, int l, double[] v, int _v_offset, int ldv, double[] t, int _t_offset, int ldt, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, int ldwork) {
    lapack.dlarzb(side, trans, direct, storev, m, n, k, l, v, _v_offset, ldv, t, _t_offset, ldt, c, _c_offset, Ldc, work, _work_offset, ldwork);
  }

  protected void dlarztK(String direct, String storev, int n, int k, double[] v, int _v_offset, int ldv, double[] tau, int _tau_offset, double[] t, int _t_offset, int ldt) {
    lapack.dlarzt(direct, storev, n, k, v, _v_offset, ldv, tau, _tau_offset, t, _t_offset, ldt);
  }

  protected void dlas2K(double f, double g, double h, org.netlib.util.doubleW ssmin, org.netlib.util.doubleW ssmax) {
    lapack.dlas2(f, g, h, ssmin, ssmax);
  }

  protected void dlasclK(String type, int kl, int ku, double cfrom, double cto, int m, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    lapack.dlascl(type, kl, ku, cfrom, cto, m, n, a, _a_offset, lda, info);
  }

  protected void dlasd0K(int n, int sqre, double[] d, int _d_offset, double[] e, int _e_offset, double[] u, int _u_offset, int ldu, double[] vt, int _vt_offset, int ldvt, int smlsiz, int[] iwork, int _iwork_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dlasd0(n, sqre, d, _d_offset, e, _e_offset, u, _u_offset, ldu, vt, _vt_offset, ldvt, smlsiz, iwork, _iwork_offset, work, _work_offset, info);
  }

  protected void dlasd1K(int nl, int nr, int sqre, double[] d, int _d_offset, org.netlib.util.doubleW alpha, org.netlib.util.doubleW beta, double[] u, int _u_offset, int ldu, double[] vt, int _vt_offset, int ldvt, int[] idxq, int _idxq_offset, int[] iwork, int _iwork_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dlasd1(nl, nr, sqre, d, _d_offset, alpha, beta, u, _u_offset, ldu, vt, _vt_offset, ldvt, idxq, _idxq_offset, iwork, _iwork_offset, work, _work_offset, info);
  }

  protected void dlasd2K(int nl, int nr, int sqre, org.netlib.util.intW k, double[] d, int _d_offset, double[] z, int _z_offset, double alpha, double beta, double[] u, int _u_offset, int ldu, double[] vt, int _vt_offset, int ldvt, double[] dsigma, int _dsigma_offset, double[] u2, int _u2_offset, int ldu2, double[] vt2, int _vt2_offset, int ldvt2, int[] idxp, int _idxp_offset, int[] idx, int _idx_offset, int[] idxc, int _idxc_offset, int[] idxq, int _idxq_offset, int[] coltyp, int _coltyp_offset, org.netlib.util.intW info) {
    lapack.dlasd2(nl, nr, sqre, k, d, _d_offset, z, _z_offset, alpha, beta, u, _u_offset, ldu, vt, _vt_offset, ldvt, dsigma, _dsigma_offset, u2, _u2_offset, ldu2, vt2, _vt2_offset, ldvt2, idxp, _idxp_offset, idx, _idx_offset, idxc, _idxc_offset, idxq, _idxq_offset, coltyp, _coltyp_offset, info);
  }

  protected void dlasd3K(int nl, int nr, int sqre, int k, double[] d, int _d_offset, double[] q, int _q_offset, int ldq, double[] dsigma, int _dsigma_offset, double[] u, int _u_offset, int ldu, double[] u2, int _u2_offset, int ldu2, double[] vt, int _vt_offset, int ldvt, double[] vt2, int _vt2_offset, int ldvt2, int[] idxc, int _idxc_offset, int[] ctot, int _ctot_offset, double[] z, int _z_offset, org.netlib.util.intW info) {
    lapack.dlasd3(nl, nr, sqre, k, d, _d_offset, q, _q_offset, ldq, dsigma, _dsigma_offset, u, _u_offset, ldu, u2, _u2_offset, ldu2, vt, _vt_offset, ldvt, vt2, _vt2_offset, ldvt2, idxc, _idxc_offset, ctot, _ctot_offset, z, _z_offset, info);
  }

  protected void dlasd4K(int n, int i, double[] d, int _d_offset, double[] z, int _z_offset, double[] delta, int _delta_offset, double rho, org.netlib.util.doubleW sigma, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dlasd4(n, i, d, _d_offset, z, _z_offset, delta, _delta_offset, rho, sigma, work, _work_offset, info);
  }

  protected void dlasd5K(int i, double[] d, int _d_offset, double[] z, int _z_offset, double[] delta, int _delta_offset, double rho, org.netlib.util.doubleW dsigma, double[] work, int _work_offset) {
    lapack.dlasd5(i, d, _d_offset, z, _z_offset, delta, _delta_offset, rho, dsigma, work, _work_offset);
  }

  protected void dlasd6K(int icompq, int nl, int nr, int sqre, double[] d, int _d_offset, double[] vf, int _vf_offset, double[] vl, int _vl_offset, org.netlib.util.doubleW alpha, org.netlib.util.doubleW beta, int[] idxq, int _idxq_offset, int[] perm, int _perm_offset, org.netlib.util.intW givptr, int[] givcol, int _givcol_offset, int ldgcol, double[] givnum, int _givnum_offset, int ldgnum, double[] poles, int _poles_offset, double[] difl, int _difl_offset, double[] difr, int _difr_offset, double[] z, int _z_offset, org.netlib.util.intW k, org.netlib.util.doubleW c, org.netlib.util.doubleW s, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dlasd6(icompq, nl, nr, sqre, d, _d_offset, vf, _vf_offset, vl, _vl_offset, alpha, beta, idxq, _idxq_offset, perm, _perm_offset, givptr, givcol, _givcol_offset, ldgcol, givnum, _givnum_offset, ldgnum, poles, _poles_offset, difl, _difl_offset, difr, _difr_offset, z, _z_offset, k, c, s, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dlasd7K(int icompq, int nl, int nr, int sqre, org.netlib.util.intW k, double[] d, int _d_offset, double[] z, int _z_offset, double[] zw, int _zw_offset, double[] vf, int _vf_offset, double[] vfw, int _vfw_offset, double[] vl, int _vl_offset, double[] vlw, int _vlw_offset, double alpha, double beta, double[] dsigma, int _dsigma_offset, int[] idx, int _idx_offset, int[] idxp, int _idxp_offset, int[] idxq, int _idxq_offset, int[] perm, int _perm_offset, org.netlib.util.intW givptr, int[] givcol, int _givcol_offset, int ldgcol, double[] givnum, int _givnum_offset, int ldgnum, org.netlib.util.doubleW c, org.netlib.util.doubleW s, org.netlib.util.intW info) {
    lapack.dlasd7(icompq, nl, nr, sqre, k, d, _d_offset, z, _z_offset, zw, _zw_offset, vf, _vf_offset, vfw, _vfw_offset, vl, _vl_offset, vlw, _vlw_offset, alpha, beta, dsigma, _dsigma_offset, idx, _idx_offset, idxp, _idxp_offset, idxq, _idxq_offset, perm, _perm_offset, givptr, givcol, _givcol_offset, ldgcol, givnum, _givnum_offset, ldgnum, c, s, info);
  }

  protected void dlasd8K(int icompq, int k, double[] d, int _d_offset, double[] z, int _z_offset, double[] vf, int _vf_offset, double[] vl, int _vl_offset, double[] difl, int _difl_offset, double[] difr, int _difr_offset, int lddifr, double[] dsigma, int _dsigma_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dlasd8(icompq, k, d, _d_offset, z, _z_offset, vf, _vf_offset, vl, _vl_offset, difl, _difl_offset, difr, _difr_offset, lddifr, dsigma, _dsigma_offset, work, _work_offset, info);
  }

  protected void dlasdaK(int icompq, int smlsiz, int n, int sqre, double[] d, int _d_offset, double[] e, int _e_offset, double[] u, int _u_offset, int ldu, double[] vt, int _vt_offset, int[] k, int _k_offset, double[] difl, int _difl_offset, double[] difr, int _difr_offset, double[] z, int _z_offset, double[] poles, int _poles_offset, int[] givptr, int _givptr_offset, int[] givcol, int _givcol_offset, int ldgcol, int[] perm, int _perm_offset, double[] givnum, int _givnum_offset, double[] c, int _c_offset, double[] s, int _s_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dlasda(icompq, smlsiz, n, sqre, d, _d_offset, e, _e_offset, u, _u_offset, ldu, vt, _vt_offset, k, _k_offset, difl, _difl_offset, difr, _difr_offset, z, _z_offset, poles, _poles_offset, givptr, _givptr_offset, givcol, _givcol_offset, ldgcol, perm, _perm_offset, givnum, _givnum_offset, c, _c_offset, s, _s_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dlasdqK(String uplo, int sqre, int n, int ncvt, int nru, int ncc, double[] d, int _d_offset, double[] e, int _e_offset, double[] vt, int _vt_offset, int ldvt, double[] u, int _u_offset, int ldu, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dlasdq(uplo, sqre, n, ncvt, nru, ncc, d, _d_offset, e, _e_offset, vt, _vt_offset, ldvt, u, _u_offset, ldu, c, _c_offset, Ldc, work, _work_offset, info);
  }

  protected void dlasdtK(int n, org.netlib.util.intW lvl, org.netlib.util.intW nd, int[] inode, int _inode_offset, int[] ndiml, int _ndiml_offset, int[] ndimr, int _ndimr_offset, int msub) {
    lapack.dlasdt(n, lvl, nd, inode, _inode_offset, ndiml, _ndiml_offset, ndimr, _ndimr_offset, msub);
  }

  protected void dlasetK(String uplo, int m, int n, double alpha, double beta, double[] a, int _a_offset, int lda) {
    lapack.dlaset(uplo, m, n, alpha, beta, a, _a_offset, lda);
  }

  protected void dlasq1K(int n, double[] d, int _d_offset, double[] e, int _e_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dlasq1(n, d, _d_offset, e, _e_offset, work, _work_offset, info);
  }

  protected void dlasq2K(int n, double[] z, int _z_offset, org.netlib.util.intW info) {
    lapack.dlasq2(n, z, _z_offset, info);
  }

  protected void dlasq3K(int i0, org.netlib.util.intW n0, double[] z, int _z_offset, int pp, org.netlib.util.doubleW dmin, org.netlib.util.doubleW sigma, org.netlib.util.doubleW desig, org.netlib.util.doubleW qmax, org.netlib.util.intW nfail, org.netlib.util.intW iter, org.netlib.util.intW ndiv, boolean ieee) {
    lapack.dlasq3(i0, n0, z, _z_offset, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee);
  }

  protected void dlasq4K(int i0, int n0, double[] z, int _z_offset, int pp, int n0in, double dmin, double dmin1, double dmin2, double dn, double dn1, double dn2, org.netlib.util.doubleW tau, org.netlib.util.intW ttype) {
    lapack.dlasq4(i0, n0, z, _z_offset, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype);
  }

  protected void dlasq5K(int i0, int n0, double[] z, int _z_offset, int pp, double tau, org.netlib.util.doubleW dmin, org.netlib.util.doubleW dmin1, org.netlib.util.doubleW dmin2, org.netlib.util.doubleW dn, org.netlib.util.doubleW dnm1, org.netlib.util.doubleW dnm2, boolean ieee) {
    lapack.dlasq5(i0, n0, z, _z_offset, pp, tau, dmin, dmin1, dmin2, dn, dnm1, dnm2, ieee);
  }

  protected void dlasq6K(int i0, int n0, double[] z, int _z_offset, int pp, org.netlib.util.doubleW dmin, org.netlib.util.doubleW dmin1, org.netlib.util.doubleW dmin2, org.netlib.util.doubleW dn, org.netlib.util.doubleW dnm1, org.netlib.util.doubleW dnm2) {
    lapack.dlasq6(i0, n0, z, _z_offset, pp, dmin, dmin1, dmin2, dn, dnm1, dnm2);
  }

  protected void dlasrK(String side, String pivot, String direct, int m, int n, double[] c, int _c_offset, double[] s, int _s_offset, double[] a, int _a_offset, int lda) {
    lapack.dlasr(side, pivot, direct, m, n, c, _c_offset, s, _s_offset, a, _a_offset, lda);
  }

  protected void dlasrtK(String id, int n, double[] d, int _d_offset, org.netlib.util.intW info) {
    lapack.dlasrt(id, n, d, _d_offset, info);
  }

  protected void dlassqK(int n, double[] x, int _x_offset, int incx, org.netlib.util.doubleW scale, org.netlib.util.doubleW sumsq) {
    lapack.dlassq(n, x, _x_offset, incx, scale, sumsq);
  }

  protected void dlasv2K(double f, double g, double h, org.netlib.util.doubleW ssmin, org.netlib.util.doubleW ssmax, org.netlib.util.doubleW snr, org.netlib.util.doubleW csr, org.netlib.util.doubleW snl, org.netlib.util.doubleW csl) {
    lapack.dlasv2(f, g, h, ssmin, ssmax, snr, csr, snl, csl);
  }

  protected void dlaswpK(int n, double[] a, int _a_offset, int lda, int k1, int k2, int[] ipiv, int _ipiv_offset, int incx) {
    lapack.dlaswp(n, a, _a_offset, lda, k1, k2, ipiv, _ipiv_offset, incx);
  }

  protected void dlasy2K(boolean ltranl, boolean ltranr, int isgn, int n1, int n2, double[] tl, int _tl_offset, int ldtl, double[] tr, int _tr_offset, int ldtr, double[] b, int _b_offset, int ldb, org.netlib.util.doubleW scale, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW xnorm, org.netlib.util.intW info) {
    lapack.dlasy2(ltranl, ltranr, isgn, n1, n2, tl, _tl_offset, ldtl, tr, _tr_offset, ldtr, b, _b_offset, ldb, scale, x, _x_offset, ldx, xnorm, info);
  }

  protected void dlasyfK(String uplo, int n, int nb, org.netlib.util.intW kb, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, double[] w, int _w_offset, int ldw, org.netlib.util.intW info) {
    lapack.dlasyf(uplo, n, nb, kb, a, _a_offset, lda, ipiv, _ipiv_offset, w, _w_offset, ldw, info);
  }

  protected void dlatbsK(String uplo, String trans, String diag, String normin, int n, int kd, double[] ab, int _ab_offset, int ldab, double[] x, int _x_offset, org.netlib.util.doubleW scale, double[] cnorm, int _cnorm_offset, org.netlib.util.intW info) {
    lapack.dlatbs(uplo, trans, diag, normin, n, kd, ab, _ab_offset, ldab, x, _x_offset, scale, cnorm, _cnorm_offset, info);
  }

  protected void dlatdfK(int ijob, int n, double[] z, int _z_offset, int ldz, double[] rhs, int _rhs_offset, org.netlib.util.doubleW rdsum, org.netlib.util.doubleW rdscal, int[] ipiv, int _ipiv_offset, int[] jpiv, int _jpiv_offset) {
    lapack.dlatdf(ijob, n, z, _z_offset, ldz, rhs, _rhs_offset, rdsum, rdscal, ipiv, _ipiv_offset, jpiv, _jpiv_offset);
  }

  protected void dlatpsK(String uplo, String trans, String diag, String normin, int n, double[] ap, int _ap_offset, double[] x, int _x_offset, org.netlib.util.doubleW scale, double[] cnorm, int _cnorm_offset, org.netlib.util.intW info) {
    lapack.dlatps(uplo, trans, diag, normin, n, ap, _ap_offset, x, _x_offset, scale, cnorm, _cnorm_offset, info);
  }

  protected void dlatrdK(String uplo, int n, int nb, double[] a, int _a_offset, int lda, double[] e, int _e_offset, double[] tau, int _tau_offset, double[] w, int _w_offset, int ldw) {
    lapack.dlatrd(uplo, n, nb, a, _a_offset, lda, e, _e_offset, tau, _tau_offset, w, _w_offset, ldw);
  }

  protected void dlatrsK(String uplo, String trans, String diag, String normin, int n, double[] a, int _a_offset, int lda, double[] x, int _x_offset, org.netlib.util.doubleW scale, double[] cnorm, int _cnorm_offset, org.netlib.util.intW info) {
    lapack.dlatrs(uplo, trans, diag, normin, n, a, _a_offset, lda, x, _x_offset, scale, cnorm, _cnorm_offset, info);
  }

  protected void dlatrzK(int m, int n, int l, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset) {
    lapack.dlatrz(m, n, l, a, _a_offset, lda, tau, _tau_offset, work, _work_offset);
  }

  protected void dlatzmK(String side, int m, int n, double[] v, int _v_offset, int incv, double tau, double[] c1, int _c1_offset, double[] c2, int _c2_offset, int Ldc, double[] work, int _work_offset) {
    lapack.dlatzm(side, m, n, v, _v_offset, incv, tau, c1, _c1_offset, c2, _c2_offset, Ldc, work, _work_offset);
  }

  protected void dlauu2K(String uplo, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    lapack.dlauu2(uplo, n, a, _a_offset, lda, info);
  }

  protected void dlauumK(String uplo, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    lapack.dlauum(uplo, n, a, _a_offset, lda, info);
  }

  protected void dlazq3K(int i0, org.netlib.util.intW n0, double[] z, int _z_offset, int pp, org.netlib.util.doubleW dmin, org.netlib.util.doubleW sigma, org.netlib.util.doubleW desig, org.netlib.util.doubleW qmax, org.netlib.util.intW nfail, org.netlib.util.intW iter, org.netlib.util.intW ndiv, boolean ieee, org.netlib.util.intW ttype, org.netlib.util.doubleW dmin1, org.netlib.util.doubleW dmin2, org.netlib.util.doubleW dn, org.netlib.util.doubleW dn1, org.netlib.util.doubleW dn2, org.netlib.util.doubleW tau) {
    lapack.dlazq3(i0, n0, z, _z_offset, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee, ttype, dmin1, dmin2, dn, dn1, dn2, tau);
  }

  protected void dlazq4K(int i0, int n0, double[] z, int _z_offset, int pp, int n0in, double dmin, double dmin1, double dmin2, double dn, double dn1, double dn2, org.netlib.util.doubleW tau, org.netlib.util.intW ttype, org.netlib.util.doubleW g) {
    lapack.dlazq4(i0, n0, z, _z_offset, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype, g);
  }

  protected void dopgtrK(String uplo, int n, double[] ap, int _ap_offset, double[] tau, int _tau_offset, double[] q, int _q_offset, int ldq, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dopgtr(uplo, n, ap, _ap_offset, tau, _tau_offset, q, _q_offset, ldq, work, _work_offset, info);
  }

  protected void dopmtrK(String side, String uplo, String trans, int m, int n, double[] ap, int _ap_offset, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dopmtr(side, uplo, trans, m, n, ap, _ap_offset, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  protected void dorg2lK(int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dorg2l(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  protected void dorg2rK(int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dorg2r(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  protected void dorgbrK(String vect, int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dorgbr(vect, m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void dorghrK(int n, int ilo, int ihi, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dorghr(n, ilo, ihi, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void dorgl2K(int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dorgl2(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  protected void dorglqK(int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dorglq(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void dorgqlK(int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dorgql(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void dorgqrK(int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dorgqr(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void dorgr2K(int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dorgr2(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  protected void dorgrqK(int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dorgrq(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void dorgtrK(String uplo, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dorgtr(uplo, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void dorm2lK(String side, String trans, int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dorm2l(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  protected void dorm2rK(String side, String trans, int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dorm2r(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  protected void dormbrK(String vect, String side, String trans, int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dormbr(vect, side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  protected void dormhrK(String side, String trans, int m, int n, int ilo, int ihi, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dormhr(side, trans, m, n, ilo, ihi, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  protected void dorml2K(String side, String trans, int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dorml2(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  protected void dormlqK(String side, String trans, int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dormlq(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  protected void dormqlK(String side, String trans, int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dormql(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  protected void dormqrK(String side, String trans, int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dormqr(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  protected void dormr2K(String side, String trans, int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dormr2(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  protected void dormr3K(String side, String trans, int m, int n, int k, int l, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dormr3(side, trans, m, n, k, l, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  protected void dormrqK(String side, String trans, int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dormrq(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  protected void dormrzK(String side, String trans, int m, int n, int k, int l, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dormrz(side, trans, m, n, k, l, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  protected void dormtrK(String side, String uplo, String trans, int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dormtr(side, uplo, trans, m, n, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  protected void dpbconK(String uplo, int n, int kd, double[] ab, int _ab_offset, int ldab, double anorm, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dpbcon(uplo, n, kd, ab, _ab_offset, ldab, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dpbequK(String uplo, int n, int kd, double[] ab, int _ab_offset, int ldab, double[] s, int _s_offset, org.netlib.util.doubleW scond, org.netlib.util.doubleW amax, org.netlib.util.intW info) {
    lapack.dpbequ(uplo, n, kd, ab, _ab_offset, ldab, s, _s_offset, scond, amax, info);
  }

  protected void dpbrfsK(String uplo, int n, int kd, int nrhs, double[] ab, int _ab_offset, int ldab, double[] afb, int _afb_offset, int ldafb, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dpbrfs(uplo, n, kd, nrhs, ab, _ab_offset, ldab, afb, _afb_offset, ldafb, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dpbstfK(String uplo, int n, int kd, double[] ab, int _ab_offset, int ldab, org.netlib.util.intW info) {
    lapack.dpbstf(uplo, n, kd, ab, _ab_offset, ldab, info);
  }

  protected void dpbsvK(String uplo, int n, int kd, int nrhs, double[] ab, int _ab_offset, int ldab, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dpbsv(uplo, n, kd, nrhs, ab, _ab_offset, ldab, b, _b_offset, ldb, info);
  }

  protected void dpbsvxK(String fact, String uplo, int n, int kd, int nrhs, double[] ab, int _ab_offset, int ldab, double[] afb, int _afb_offset, int ldafb, org.netlib.util.StringW equed, double[] s, int _s_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dpbsvx(fact, uplo, n, kd, nrhs, ab, _ab_offset, ldab, afb, _afb_offset, ldafb, equed, s, _s_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dpbtf2K(String uplo, int n, int kd, double[] ab, int _ab_offset, int ldab, org.netlib.util.intW info) {
    lapack.dpbtf2(uplo, n, kd, ab, _ab_offset, ldab, info);
  }

  protected void dpbtrfK(String uplo, int n, int kd, double[] ab, int _ab_offset, int ldab, org.netlib.util.intW info) {
    lapack.dpbtrf(uplo, n, kd, ab, _ab_offset, ldab, info);
  }

  protected void dpbtrsK(String uplo, int n, int kd, int nrhs, double[] ab, int _ab_offset, int ldab, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dpbtrs(uplo, n, kd, nrhs, ab, _ab_offset, ldab, b, _b_offset, ldb, info);
  }

  protected void dpoconK(String uplo, int n, double[] a, int _a_offset, int lda, double anorm, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dpocon(uplo, n, a, _a_offset, lda, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dpoequK(int n, double[] a, int _a_offset, int lda, double[] s, int _s_offset, org.netlib.util.doubleW scond, org.netlib.util.doubleW amax, org.netlib.util.intW info) {
    lapack.dpoequ(n, a, _a_offset, lda, s, _s_offset, scond, amax, info);
  }

  protected void dporfsK(String uplo, int n, int nrhs, double[] a, int _a_offset, int lda, double[] af, int _af_offset, int ldaf, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dporfs(uplo, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dposvK(String uplo, int n, int nrhs, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dposv(uplo, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, info);
  }

  protected void dposvxK(String fact, String uplo, int n, int nrhs, double[] a, int _a_offset, int lda, double[] af, int _af_offset, int ldaf, org.netlib.util.StringW equed, double[] s, int _s_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dposvx(fact, uplo, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, equed, s, _s_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dpotf2K(String uplo, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    lapack.dpotf2(uplo, n, a, _a_offset, lda, info);
  }

  protected void dpotrfK(String uplo, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    lapack.dpotrf(uplo, n, a, _a_offset, lda, info);
  }

  protected void dpotriK(String uplo, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    lapack.dpotri(uplo, n, a, _a_offset, lda, info);
  }

  protected void dpotrsK(String uplo, int n, int nrhs, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dpotrs(uplo, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, info);
  }

  protected void dppconK(String uplo, int n, double[] ap, int _ap_offset, double anorm, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dppcon(uplo, n, ap, _ap_offset, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dppequK(String uplo, int n, double[] ap, int _ap_offset, double[] s, int _s_offset, org.netlib.util.doubleW scond, org.netlib.util.doubleW amax, org.netlib.util.intW info) {
    lapack.dppequ(uplo, n, ap, _ap_offset, s, _s_offset, scond, amax, info);
  }

  protected void dpprfsK(String uplo, int n, int nrhs, double[] ap, int _ap_offset, double[] afp, int _afp_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dpprfs(uplo, n, nrhs, ap, _ap_offset, afp, _afp_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dppsvK(String uplo, int n, int nrhs, double[] ap, int _ap_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dppsv(uplo, n, nrhs, ap, _ap_offset, b, _b_offset, ldb, info);
  }

  protected void dppsvxK(String fact, String uplo, int n, int nrhs, double[] ap, int _ap_offset, double[] afp, int _afp_offset, org.netlib.util.StringW equed, double[] s, int _s_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dppsvx(fact, uplo, n, nrhs, ap, _ap_offset, afp, _afp_offset, equed, s, _s_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dpptrfK(String uplo, int n, double[] ap, int _ap_offset, org.netlib.util.intW info) {
    lapack.dpptrf(uplo, n, ap, _ap_offset, info);
  }

  protected void dpptriK(String uplo, int n, double[] ap, int _ap_offset, org.netlib.util.intW info) {
    lapack.dpptri(uplo, n, ap, _ap_offset, info);
  }

  protected void dpptrsK(String uplo, int n, int nrhs, double[] ap, int _ap_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dpptrs(uplo, n, nrhs, ap, _ap_offset, b, _b_offset, ldb, info);
  }

  protected void dptconK(int n, double[] d, int _d_offset, double[] e, int _e_offset, double anorm, org.netlib.util.doubleW rcond, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dptcon(n, d, _d_offset, e, _e_offset, anorm, rcond, work, _work_offset, info);
  }

  protected void dpteqrK(String compz, int n, double[] d, int _d_offset, double[] e, int _e_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dpteqr(compz, n, d, _d_offset, e, _e_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  protected void dptrfsK(int n, int nrhs, double[] d, int _d_offset, double[] e, int _e_offset, double[] df, int _df_offset, double[] ef, int _ef_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dptrfs(n, nrhs, d, _d_offset, e, _e_offset, df, _df_offset, ef, _ef_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, info);
  }

  protected void dptsvK(int n, int nrhs, double[] d, int _d_offset, double[] e, int _e_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dptsv(n, nrhs, d, _d_offset, e, _e_offset, b, _b_offset, ldb, info);
  }

  protected void dptsvxK(String fact, int n, int nrhs, double[] d, int _d_offset, double[] e, int _e_offset, double[] df, int _df_offset, double[] ef, int _ef_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dptsvx(fact, n, nrhs, d, _d_offset, e, _e_offset, df, _df_offset, ef, _ef_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, info);
  }

  protected void dpttrfK(int n, double[] d, int _d_offset, double[] e, int _e_offset, org.netlib.util.intW info) {
    lapack.dpttrf(n, d, _d_offset, e, _e_offset, info);
  }

  protected void dpttrsK(int n, int nrhs, double[] d, int _d_offset, double[] e, int _e_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dpttrs(n, nrhs, d, _d_offset, e, _e_offset, b, _b_offset, ldb, info);
  }

  protected void dptts2K(int n, int nrhs, double[] d, int _d_offset, double[] e, int _e_offset, double[] b, int _b_offset, int ldb) {
    lapack.dptts2(n, nrhs, d, _d_offset, e, _e_offset, b, _b_offset, ldb);
  }

  protected void drsclK(int n, double sa, double[] sx, int _sx_offset, int incx) {
    lapack.drscl(n, sa, sx, _sx_offset, incx);
  }

  protected void dsbevK(String jobz, String uplo, int n, int kd, double[] ab, int _ab_offset, int ldab, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dsbev(jobz, uplo, n, kd, ab, _ab_offset, ldab, w, _w_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  protected void dsbevdK(String jobz, String uplo, int n, int kd, double[] ab, int _ab_offset, int ldab, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.dsbevd(jobz, uplo, n, kd, ab, _ab_offset, ldab, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void dsbevxK(String jobz, String range, String uplo, int n, int kd, double[] ab, int _ab_offset, int ldab, double[] q, int _q_offset, int ldq, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    lapack.dsbevx(jobz, range, uplo, n, kd, ab, _ab_offset, ldab, q, _q_offset, ldq, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  protected void dsbgstK(String vect, String uplo, int n, int ka, int kb, double[] ab, int _ab_offset, int ldab, double[] bb, int _bb_offset, int ldbb, double[] x, int _x_offset, int ldx, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dsbgst(vect, uplo, n, ka, kb, ab, _ab_offset, ldab, bb, _bb_offset, ldbb, x, _x_offset, ldx, work, _work_offset, info);
  }

  protected void dsbgvK(String jobz, String uplo, int n, int ka, int kb, double[] ab, int _ab_offset, int ldab, double[] bb, int _bb_offset, int ldbb, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dsbgv(jobz, uplo, n, ka, kb, ab, _ab_offset, ldab, bb, _bb_offset, ldbb, w, _w_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  protected void dsbgvdK(String jobz, String uplo, int n, int ka, int kb, double[] ab, int _ab_offset, int ldab, double[] bb, int _bb_offset, int ldbb, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.dsbgvd(jobz, uplo, n, ka, kb, ab, _ab_offset, ldab, bb, _bb_offset, ldbb, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void dsbgvxK(String jobz, String range, String uplo, int n, int ka, int kb, double[] ab, int _ab_offset, int ldab, double[] bb, int _bb_offset, int ldbb, double[] q, int _q_offset, int ldq, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    lapack.dsbgvx(jobz, range, uplo, n, ka, kb, ab, _ab_offset, ldab, bb, _bb_offset, ldbb, q, _q_offset, ldq, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  protected void dsbtrdK(String vect, String uplo, int n, int kd, double[] ab, int _ab_offset, int ldab, double[] d, int _d_offset, double[] e, int _e_offset, double[] q, int _q_offset, int ldq, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dsbtrd(vect, uplo, n, kd, ab, _ab_offset, ldab, d, _d_offset, e, _e_offset, q, _q_offset, ldq, work, _work_offset, info);
  }

  protected void dsgesvK(int n, int nrhs, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] work, int _work_offset, float[] swork, int _swork_offset, org.netlib.util.intW iter, org.netlib.util.intW info) {
    lapack.dsgesv(n, nrhs, a, _a_offset, lda, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, work, _work_offset, swork, _swork_offset, iter, info);
  }

  protected void dspconK(String uplo, int n, double[] ap, int _ap_offset, int[] ipiv, int _ipiv_offset, double anorm, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dspcon(uplo, n, ap, _ap_offset, ipiv, _ipiv_offset, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dspevK(String jobz, String uplo, int n, double[] ap, int _ap_offset, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dspev(jobz, uplo, n, ap, _ap_offset, w, _w_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  protected void dspevdK(String jobz, String uplo, int n, double[] ap, int _ap_offset, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.dspevd(jobz, uplo, n, ap, _ap_offset, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void dspevxK(String jobz, String range, String uplo, int n, double[] ap, int _ap_offset, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    lapack.dspevx(jobz, range, uplo, n, ap, _ap_offset, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  protected void dspgstK(int itype, String uplo, int n, double[] ap, int _ap_offset, double[] bp, int _bp_offset, org.netlib.util.intW info) {
    lapack.dspgst(itype, uplo, n, ap, _ap_offset, bp, _bp_offset, info);
  }

  protected void dspgvK(int itype, String jobz, String uplo, int n, double[] ap, int _ap_offset, double[] bp, int _bp_offset, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dspgv(itype, jobz, uplo, n, ap, _ap_offset, bp, _bp_offset, w, _w_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  protected void dspgvdK(int itype, String jobz, String uplo, int n, double[] ap, int _ap_offset, double[] bp, int _bp_offset, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.dspgvd(itype, jobz, uplo, n, ap, _ap_offset, bp, _bp_offset, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void dspgvxK(int itype, String jobz, String range, String uplo, int n, double[] ap, int _ap_offset, double[] bp, int _bp_offset, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    lapack.dspgvx(itype, jobz, range, uplo, n, ap, _ap_offset, bp, _bp_offset, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  protected void dsprfsK(String uplo, int n, int nrhs, double[] ap, int _ap_offset, double[] afp, int _afp_offset, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dsprfs(uplo, n, nrhs, ap, _ap_offset, afp, _afp_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dspsvK(String uplo, int n, int nrhs, double[] ap, int _ap_offset, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dspsv(uplo, n, nrhs, ap, _ap_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  protected void dspsvxK(String fact, String uplo, int n, int nrhs, double[] ap, int _ap_offset, double[] afp, int _afp_offset, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dspsvx(fact, uplo, n, nrhs, ap, _ap_offset, afp, _afp_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dsptrdK(String uplo, int n, double[] ap, int _ap_offset, double[] d, int _d_offset, double[] e, int _e_offset, double[] tau, int _tau_offset, org.netlib.util.intW info) {
    lapack.dsptrd(uplo, n, ap, _ap_offset, d, _d_offset, e, _e_offset, tau, _tau_offset, info);
  }

  protected void dsptrfK(String uplo, int n, double[] ap, int _ap_offset, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    lapack.dsptrf(uplo, n, ap, _ap_offset, ipiv, _ipiv_offset, info);
  }

  protected void dsptriK(String uplo, int n, double[] ap, int _ap_offset, int[] ipiv, int _ipiv_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dsptri(uplo, n, ap, _ap_offset, ipiv, _ipiv_offset, work, _work_offset, info);
  }

  protected void dsptrsK(String uplo, int n, int nrhs, double[] ap, int _ap_offset, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dsptrs(uplo, n, nrhs, ap, _ap_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  protected void dstebzK(String range, String order, int n, double vl, double vu, int il, int iu, double abstol, double[] d, int _d_offset, double[] e, int _e_offset, org.netlib.util.intW m, org.netlib.util.intW nsplit, double[] w, int _w_offset, int[] iblock, int _iblock_offset, int[] isplit, int _isplit_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dstebz(range, order, n, vl, vu, il, iu, abstol, d, _d_offset, e, _e_offset, m, nsplit, w, _w_offset, iblock, _iblock_offset, isplit, _isplit_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dstedcK(String compz, int n, double[] d, int _d_offset, double[] e, int _e_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.dstedc(compz, n, d, _d_offset, e, _e_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void dstegrK(String jobz, String range, int n, double[] d, int _d_offset, double[] e, int _e_offset, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, int[] isuppz, int _isuppz_offset, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.dstegr(jobz, range, n, d, _d_offset, e, _e_offset, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, isuppz, _isuppz_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void dsteinK(int n, double[] d, int _d_offset, double[] e, int _e_offset, int m, double[] w, int _w_offset, int[] iblock, int _iblock_offset, int[] isplit, int _isplit_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    lapack.dstein(n, d, _d_offset, e, _e_offset, m, w, _w_offset, iblock, _iblock_offset, isplit, _isplit_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  protected void dstemrK(String jobz, String range, int n, double[] d, int _d_offset, double[] e, int _e_offset, double vl, double vu, int il, int iu, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, int nzc, int[] isuppz, int _isuppz_offset, org.netlib.util.booleanW tryrac, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.dstemr(jobz, range, n, d, _d_offset, e, _e_offset, vl, vu, il, iu, m, w, _w_offset, z, _z_offset, ldz, nzc, isuppz, _isuppz_offset, tryrac, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void dsteqrK(String compz, int n, double[] d, int _d_offset, double[] e, int _e_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dsteqr(compz, n, d, _d_offset, e, _e_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  protected void dsterfK(int n, double[] d, int _d_offset, double[] e, int _e_offset, org.netlib.util.intW info) {
    lapack.dsterf(n, d, _d_offset, e, _e_offset, info);
  }

  protected void dstevK(String jobz, int n, double[] d, int _d_offset, double[] e, int _e_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dstev(jobz, n, d, _d_offset, e, _e_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  protected void dstevdK(String jobz, int n, double[] d, int _d_offset, double[] e, int _e_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.dstevd(jobz, n, d, _d_offset, e, _e_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void dstevrK(String jobz, String range, int n, double[] d, int _d_offset, double[] e, int _e_offset, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, int[] isuppz, int _isuppz_offset, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.dstevr(jobz, range, n, d, _d_offset, e, _e_offset, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, isuppz, _isuppz_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void dstevxK(String jobz, String range, int n, double[] d, int _d_offset, double[] e, int _e_offset, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    lapack.dstevx(jobz, range, n, d, _d_offset, e, _e_offset, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  protected void dsyconK(String uplo, int n, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, double anorm, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dsycon(uplo, n, a, _a_offset, lda, ipiv, _ipiv_offset, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dsyevK(String jobz, String uplo, int n, double[] a, int _a_offset, int lda, double[] w, int _w_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dsyev(jobz, uplo, n, a, _a_offset, lda, w, _w_offset, work, _work_offset, lwork, info);
  }

  protected void dsyevdK(String jobz, String uplo, int n, double[] a, int _a_offset, int lda, double[] w, int _w_offset, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.dsyevd(jobz, uplo, n, a, _a_offset, lda, w, _w_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void dsyevrK(String jobz, String range, String uplo, int n, double[] a, int _a_offset, int lda, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, int[] isuppz, int _isuppz_offset, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.dsyevr(jobz, range, uplo, n, a, _a_offset, lda, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, isuppz, _isuppz_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void dsyevxK(String jobz, String range, String uplo, int n, double[] a, int _a_offset, int lda, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    lapack.dsyevx(jobz, range, uplo, n, a, _a_offset, lda, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  protected void dsygs2K(int itype, String uplo, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dsygs2(itype, uplo, n, a, _a_offset, lda, b, _b_offset, ldb, info);
  }

  protected void dsygstK(int itype, String uplo, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dsygst(itype, uplo, n, a, _a_offset, lda, b, _b_offset, ldb, info);
  }

  protected void dsygvK(int itype, String jobz, String uplo, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] w, int _w_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dsygv(itype, jobz, uplo, n, a, _a_offset, lda, b, _b_offset, ldb, w, _w_offset, work, _work_offset, lwork, info);
  }

  protected void dsygvdK(int itype, String jobz, String uplo, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] w, int _w_offset, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.dsygvd(itype, jobz, uplo, n, a, _a_offset, lda, b, _b_offset, ldb, w, _w_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void dsygvxK(int itype, String jobz, String range, String uplo, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    lapack.dsygvx(itype, jobz, range, uplo, n, a, _a_offset, lda, b, _b_offset, ldb, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  protected void dsyrfsK(String uplo, int n, int nrhs, double[] a, int _a_offset, int lda, double[] af, int _af_offset, int ldaf, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dsyrfs(uplo, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dsysvK(String uplo, int n, int nrhs, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dsysv(uplo, n, nrhs, a, _a_offset, lda, ipiv, _ipiv_offset, b, _b_offset, ldb, work, _work_offset, lwork, info);
  }

  protected void dsysvxK(String fact, String uplo, int n, int nrhs, double[] a, int _a_offset, int lda, double[] af, int _af_offset, int ldaf, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dsysvx(fact, uplo, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  protected void dsytd2K(String uplo, int n, double[] a, int _a_offset, int lda, double[] d, int _d_offset, double[] e, int _e_offset, double[] tau, int _tau_offset, org.netlib.util.intW info) {
    lapack.dsytd2(uplo, n, a, _a_offset, lda, d, _d_offset, e, _e_offset, tau, _tau_offset, info);
  }

  protected void dsytf2K(String uplo, int n, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    lapack.dsytf2(uplo, n, a, _a_offset, lda, ipiv, _ipiv_offset, info);
  }

  protected void dsytrdK(String uplo, int n, double[] a, int _a_offset, int lda, double[] d, int _d_offset, double[] e, int _e_offset, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dsytrd(uplo, n, a, _a_offset, lda, d, _d_offset, e, _e_offset, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void dsytrfK(String uplo, int n, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dsytrf(uplo, n, a, _a_offset, lda, ipiv, _ipiv_offset, work, _work_offset, lwork, info);
  }

  protected void dsytriK(String uplo, int n, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dsytri(uplo, n, a, _a_offset, lda, ipiv, _ipiv_offset, work, _work_offset, info);
  }

  protected void dsytrsK(String uplo, int n, int nrhs, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dsytrs(uplo, n, nrhs, a, _a_offset, lda, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  protected void dtbconK(String norm, String uplo, String diag, int n, int kd, double[] ab, int _ab_offset, int ldab, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dtbcon(norm, uplo, diag, n, kd, ab, _ab_offset, ldab, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dtbrfsK(String uplo, String trans, String diag, int n, int kd, int nrhs, double[] ab, int _ab_offset, int ldab, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dtbrfs(uplo, trans, diag, n, kd, nrhs, ab, _ab_offset, ldab, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dtbtrsK(String uplo, String trans, String diag, int n, int kd, int nrhs, double[] ab, int _ab_offset, int ldab, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dtbtrs(uplo, trans, diag, n, kd, nrhs, ab, _ab_offset, ldab, b, _b_offset, ldb, info);
  }

  protected void dtgevcK(String side, String howmny, boolean[] select, int _select_offset, int n, double[] s, int _s_offset, int lds, double[] p, int _p_offset, int ldp, double[] vl, int _vl_offset, int ldvl, double[] vr, int _vr_offset, int ldvr, int mm, org.netlib.util.intW m, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dtgevc(side, howmny, select, _select_offset, n, s, _s_offset, lds, p, _p_offset, ldp, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, mm, m, work, _work_offset, info);
  }

  protected void dtgex2K(boolean wantq, boolean wantz, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] q, int _q_offset, int ldq, double[] z, int _z_offset, int ldz, int j1, int n1, int n2, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dtgex2(wantq, wantz, n, a, _a_offset, lda, b, _b_offset, ldb, q, _q_offset, ldq, z, _z_offset, ldz, j1, n1, n2, work, _work_offset, lwork, info);
  }

  protected void dtgexcK(boolean wantq, boolean wantz, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] q, int _q_offset, int ldq, double[] z, int _z_offset, int ldz, org.netlib.util.intW ifst, org.netlib.util.intW ilst, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dtgexc(wantq, wantz, n, a, _a_offset, lda, b, _b_offset, ldb, q, _q_offset, ldq, z, _z_offset, ldz, ifst, ilst, work, _work_offset, lwork, info);
  }

  protected void dtgsenK(int ijob, boolean wantq, boolean wantz, boolean[] select, int _select_offset, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] alphar, int _alphar_offset, double[] alphai, int _alphai_offset, double[] beta, int _beta_offset, double[] q, int _q_offset, int ldq, double[] z, int _z_offset, int ldz, org.netlib.util.intW m, org.netlib.util.doubleW pl, org.netlib.util.doubleW pr, double[] dif, int _dif_offset, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.dtgsen(ijob, wantq, wantz, select, _select_offset, n, a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, q, _q_offset, ldq, z, _z_offset, ldz, m, pl, pr, dif, _dif_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void dtgsjaK(String jobu, String jobv, String jobq, int m, int p, int n, int k, int l, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double tola, double tolb, double[] alpha, int _alpha_offset, double[] beta, int _beta_offset, double[] u, int _u_offset, int ldu, double[] v, int _v_offset, int ldv, double[] q, int _q_offset, int ldq, double[] work, int _work_offset, org.netlib.util.intW ncycle, org.netlib.util.intW info) {
    lapack.dtgsja(jobu, jobv, jobq, m, p, n, k, l, a, _a_offset, lda, b, _b_offset, ldb, tola, tolb, alpha, _alpha_offset, beta, _beta_offset, u, _u_offset, ldu, v, _v_offset, ldv, q, _q_offset, ldq, work, _work_offset, ncycle, info);
  }

  protected void dtgsnaK(String job, String howmny, boolean[] select, int _select_offset, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] vl, int _vl_offset, int ldvl, double[] vr, int _vr_offset, int ldvr, double[] s, int _s_offset, double[] dif, int _dif_offset, int mm, org.netlib.util.intW m, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dtgsna(job, howmny, select, _select_offset, n, a, _a_offset, lda, b, _b_offset, ldb, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, s, _s_offset, dif, _dif_offset, mm, m, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  protected void dtgsy2K(String trans, int ijob, int m, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] c, int _c_offset, int Ldc, double[] d, int _d_offset, int ldd, double[] e, int _e_offset, int lde, double[] f, int _f_offset, int ldf, org.netlib.util.doubleW scale, org.netlib.util.doubleW rdsum, org.netlib.util.doubleW rdscal, int[] iwork, int _iwork_offset, org.netlib.util.intW pq, org.netlib.util.intW info) {
    lapack.dtgsy2(trans, ijob, m, n, a, _a_offset, lda, b, _b_offset, ldb, c, _c_offset, Ldc, d, _d_offset, ldd, e, _e_offset, lde, f, _f_offset, ldf, scale, rdsum, rdscal, iwork, _iwork_offset, pq, info);
  }

  protected void dtgsylK(String trans, int ijob, int m, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] c, int _c_offset, int Ldc, double[] d, int _d_offset, int ldd, double[] e, int _e_offset, int lde, double[] f, int _f_offset, int ldf, org.netlib.util.doubleW scale, org.netlib.util.doubleW dif, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dtgsyl(trans, ijob, m, n, a, _a_offset, lda, b, _b_offset, ldb, c, _c_offset, Ldc, d, _d_offset, ldd, e, _e_offset, lde, f, _f_offset, ldf, scale, dif, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  protected void dtpconK(String norm, String uplo, String diag, int n, double[] ap, int _ap_offset, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dtpcon(norm, uplo, diag, n, ap, _ap_offset, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dtprfsK(String uplo, String trans, String diag, int n, int nrhs, double[] ap, int _ap_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dtprfs(uplo, trans, diag, n, nrhs, ap, _ap_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dtptriK(String uplo, String diag, int n, double[] ap, int _ap_offset, org.netlib.util.intW info) {
    lapack.dtptri(uplo, diag, n, ap, _ap_offset, info);
  }

  protected void dtptrsK(String uplo, String trans, String diag, int n, int nrhs, double[] ap, int _ap_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dtptrs(uplo, trans, diag, n, nrhs, ap, _ap_offset, b, _b_offset, ldb, info);
  }

  protected void dtrconK(String norm, String uplo, String diag, int n, double[] a, int _a_offset, int lda, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dtrcon(norm, uplo, diag, n, a, _a_offset, lda, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dtrevcK(String side, String howmny, boolean[] select, int _select_offset, int n, double[] t, int _t_offset, int ldt, double[] vl, int _vl_offset, int ldvl, double[] vr, int _vr_offset, int ldvr, int mm, org.netlib.util.intW m, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dtrevc(side, howmny, select, _select_offset, n, t, _t_offset, ldt, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, mm, m, work, _work_offset, info);
  }

  protected void dtrexcK(String compq, int n, double[] t, int _t_offset, int ldt, double[] q, int _q_offset, int ldq, org.netlib.util.intW ifst, org.netlib.util.intW ilst, double[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.dtrexc(compq, n, t, _t_offset, ldt, q, _q_offset, ldq, ifst, ilst, work, _work_offset, info);
  }

  protected void dtrrfsK(String uplo, String trans, String diag, int n, int nrhs, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dtrrfs(uplo, trans, diag, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void dtrsenK(String job, String compq, boolean[] select, int _select_offset, int n, double[] t, int _t_offset, int ldt, double[] q, int _q_offset, int ldq, double[] wr, int _wr_offset, double[] wi, int _wi_offset, org.netlib.util.intW m, org.netlib.util.doubleW s, org.netlib.util.doubleW sep, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.dtrsen(job, compq, select, _select_offset, n, t, _t_offset, ldt, q, _q_offset, ldq, wr, _wr_offset, wi, _wi_offset, m, s, sep, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void dtrsnaK(String job, String howmny, boolean[] select, int _select_offset, int n, double[] t, int _t_offset, int ldt, double[] vl, int _vl_offset, int ldvl, double[] vr, int _vr_offset, int ldvr, double[] s, int _s_offset, double[] sep, int _sep_offset, int mm, org.netlib.util.intW m, double[] work, int _work_offset, int ldwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.dtrsna(job, howmny, select, _select_offset, n, t, _t_offset, ldt, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, s, _s_offset, sep, _sep_offset, mm, m, work, _work_offset, ldwork, iwork, _iwork_offset, info);
  }

  protected void dtrsylK(String trana, String tranb, int isgn, int m, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] c, int _c_offset, int Ldc, org.netlib.util.doubleW scale, org.netlib.util.intW info) {
    lapack.dtrsyl(trana, tranb, isgn, m, n, a, _a_offset, lda, b, _b_offset, ldb, c, _c_offset, Ldc, scale, info);
  }

  protected void dtrti2K(String uplo, String diag, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    lapack.dtrti2(uplo, diag, n, a, _a_offset, lda, info);
  }

  protected void dtrtriK(String uplo, String diag, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    lapack.dtrtri(uplo, diag, n, a, _a_offset, lda, info);
  }

  protected void dtrtrsK(String uplo, String trans, String diag, int n, int nrhs, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.dtrtrs(uplo, trans, diag, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, info);
  }

  protected void dtzrqfK(int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, org.netlib.util.intW info) {
    lapack.dtzrqf(m, n, a, _a_offset, lda, tau, _tau_offset, info);
  }

  protected void dtzrzfK(int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.dtzrzf(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected int ieeeckK(int ispec, float zero, float one) {
    return lapack.ieeeck(ispec, zero, one);
  }

  protected int ilaenvK(int ispec, String name, String opts, int n1, int n2, int n3, int n4) {
    return lapack.ilaenv(ispec, name, opts, n1, n2, n3, n4);
  }

  protected void ilaverK(org.netlib.util.intW vers_major, org.netlib.util.intW vers_minor, org.netlib.util.intW vers_patch) {
    lapack.ilaver(vers_major, vers_minor, vers_patch);
  }

  protected int iparmqK(int ispec, String name, String opts, int n, int ilo, int ihi, int lwork) {
    return lapack.iparmq(ispec, name, opts, n, ilo, ihi, lwork);
  }

  protected boolean lsamenK(int n, String ca, String cb) {
    return lapack.lsamen(n, ca, cb);
  }

  protected void sbdsdcK(String uplo, String compq, int n, float[] d, int _d_offset, float[] e, int _e_offset, float[] u, int _u_offset, int ldu, float[] vt, int _vt_offset, int ldvt, float[] q, int _q_offset, int[] iq, int _iq_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sbdsdc(uplo, compq, n, d, _d_offset, e, _e_offset, u, _u_offset, ldu, vt, _vt_offset, ldvt, q, _q_offset, iq, _iq_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void sbdsqrK(String uplo, int n, int ncvt, int nru, int ncc, float[] d, int _d_offset, float[] e, int _e_offset, float[] vt, int _vt_offset, int ldvt, float[] u, int _u_offset, int ldu, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sbdsqr(uplo, n, ncvt, nru, ncc, d, _d_offset, e, _e_offset, vt, _vt_offset, ldvt, u, _u_offset, ldu, c, _c_offset, Ldc, work, _work_offset, info);
  }

  protected void sdisnaK(String job, int m, int n, float[] d, int _d_offset, float[] sep, int _sep_offset, org.netlib.util.intW info) {
    lapack.sdisna(job, m, n, d, _d_offset, sep, _sep_offset, info);
  }

  protected void sgbbrdK(String vect, int m, int n, int ncc, int kl, int ku, float[] ab, int _ab_offset, int ldab, float[] d, int _d_offset, float[] e, int _e_offset, float[] q, int _q_offset, int ldq, float[] pt, int _pt_offset, int ldpt, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sgbbrd(vect, m, n, ncc, kl, ku, ab, _ab_offset, ldab, d, _d_offset, e, _e_offset, q, _q_offset, ldq, pt, _pt_offset, ldpt, c, _c_offset, Ldc, work, _work_offset, info);
  }

  protected void sgbconK(String norm, int n, int kl, int ku, float[] ab, int _ab_offset, int ldab, int[] ipiv, int _ipiv_offset, float anorm, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sgbcon(norm, n, kl, ku, ab, _ab_offset, ldab, ipiv, _ipiv_offset, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void sgbequK(int m, int n, int kl, int ku, float[] ab, int _ab_offset, int ldab, float[] r, int _r_offset, float[] c, int _c_offset, org.netlib.util.floatW rowcnd, org.netlib.util.floatW colcnd, org.netlib.util.floatW amax, org.netlib.util.intW info) {
    lapack.sgbequ(m, n, kl, ku, ab, _ab_offset, ldab, r, _r_offset, c, _c_offset, rowcnd, colcnd, amax, info);
  }

  protected void sgbrfsK(String trans, int n, int kl, int ku, int nrhs, float[] ab, int _ab_offset, int ldab, float[] afb, int _afb_offset, int ldafb, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sgbrfs(trans, n, kl, ku, nrhs, ab, _ab_offset, ldab, afb, _afb_offset, ldafb, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void sgbsvK(int n, int kl, int ku, int nrhs, float[] ab, int _ab_offset, int ldab, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.sgbsv(n, kl, ku, nrhs, ab, _ab_offset, ldab, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  protected void sgbsvxK(String fact, String trans, int n, int kl, int ku, int nrhs, float[] ab, int _ab_offset, int ldab, float[] afb, int _afb_offset, int ldafb, int[] ipiv, int _ipiv_offset, org.netlib.util.StringW equed, float[] r, int _r_offset, float[] c, int _c_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, org.netlib.util.floatW rcond, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sgbsvx(fact, trans, n, kl, ku, nrhs, ab, _ab_offset, ldab, afb, _afb_offset, ldafb, ipiv, _ipiv_offset, equed, r, _r_offset, c, _c_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void sgbtf2K(int m, int n, int kl, int ku, float[] ab, int _ab_offset, int ldab, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    lapack.sgbtf2(m, n, kl, ku, ab, _ab_offset, ldab, ipiv, _ipiv_offset, info);
  }

  protected void sgbtrfK(int m, int n, int kl, int ku, float[] ab, int _ab_offset, int ldab, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    lapack.sgbtrf(m, n, kl, ku, ab, _ab_offset, ldab, ipiv, _ipiv_offset, info);
  }

  protected void sgbtrsK(String trans, int n, int kl, int ku, int nrhs, float[] ab, int _ab_offset, int ldab, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.sgbtrs(trans, n, kl, ku, nrhs, ab, _ab_offset, ldab, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  protected void sgebakK(String job, String side, int n, int ilo, int ihi, float[] scale, int _scale_offset, int m, float[] v, int _v_offset, int ldv, org.netlib.util.intW info) {
    lapack.sgebak(job, side, n, ilo, ihi, scale, _scale_offset, m, v, _v_offset, ldv, info);
  }

  protected void sgebalK(String job, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW ilo, org.netlib.util.intW ihi, float[] scale, int _scale_offset, org.netlib.util.intW info) {
    lapack.sgebal(job, n, a, _a_offset, lda, ilo, ihi, scale, _scale_offset, info);
  }

  protected void sgebd2K(int m, int n, float[] a, int _a_offset, int lda, float[] d, int _d_offset, float[] e, int _e_offset, float[] tauq, int _tauq_offset, float[] taup, int _taup_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sgebd2(m, n, a, _a_offset, lda, d, _d_offset, e, _e_offset, tauq, _tauq_offset, taup, _taup_offset, work, _work_offset, info);
  }

  protected void sgebrdK(int m, int n, float[] a, int _a_offset, int lda, float[] d, int _d_offset, float[] e, int _e_offset, float[] tauq, int _tauq_offset, float[] taup, int _taup_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sgebrd(m, n, a, _a_offset, lda, d, _d_offset, e, _e_offset, tauq, _tauq_offset, taup, _taup_offset, work, _work_offset, lwork, info);
  }

  protected void sgeconK(String norm, int n, float[] a, int _a_offset, int lda, float anorm, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sgecon(norm, n, a, _a_offset, lda, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void sgeequK(int m, int n, float[] a, int _a_offset, int lda, float[] r, int _r_offset, float[] c, int _c_offset, org.netlib.util.floatW rowcnd, org.netlib.util.floatW colcnd, org.netlib.util.floatW amax, org.netlib.util.intW info) {
    lapack.sgeequ(m, n, a, _a_offset, lda, r, _r_offset, c, _c_offset, rowcnd, colcnd, amax, info);
  }

  protected void sgeesK(String jobvs, String sort, java.lang.Object select, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW sdim, float[] wr, int _wr_offset, float[] wi, int _wi_offset, float[] vs, int _vs_offset, int ldvs, float[] work, int _work_offset, int lwork, boolean[] bwork, int _bwork_offset, org.netlib.util.intW info) {
    lapack.sgees(jobvs, sort, select, n, a, _a_offset, lda, sdim, wr, _wr_offset, wi, _wi_offset, vs, _vs_offset, ldvs, work, _work_offset, lwork, bwork, _bwork_offset, info);
  }

  protected void sgeesxK(String jobvs, String sort, java.lang.Object select, String sense, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW sdim, float[] wr, int _wr_offset, float[] wi, int _wi_offset, float[] vs, int _vs_offset, int ldvs, org.netlib.util.floatW rconde, org.netlib.util.floatW rcondv, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, boolean[] bwork, int _bwork_offset, org.netlib.util.intW info) {
    lapack.sgeesx(jobvs, sort, select, sense, n, a, _a_offset, lda, sdim, wr, _wr_offset, wi, _wi_offset, vs, _vs_offset, ldvs, rconde, rcondv, work, _work_offset, lwork, iwork, _iwork_offset, liwork, bwork, _bwork_offset, info);
  }

  protected void sgeevK(String jobvl, String jobvr, int n, float[] a, int _a_offset, int lda, float[] wr, int _wr_offset, float[] wi, int _wi_offset, float[] vl, int _vl_offset, int ldvl, float[] vr, int _vr_offset, int ldvr, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sgeev(jobvl, jobvr, n, a, _a_offset, lda, wr, _wr_offset, wi, _wi_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, work, _work_offset, lwork, info);
  }

  protected void sgeevxK(String balanc, String jobvl, String jobvr, String sense, int n, float[] a, int _a_offset, int lda, float[] wr, int _wr_offset, float[] wi, int _wi_offset, float[] vl, int _vl_offset, int ldvl, float[] vr, int _vr_offset, int ldvr, org.netlib.util.intW ilo, org.netlib.util.intW ihi, float[] scale, int _scale_offset, org.netlib.util.floatW abnrm, float[] rconde, int _rconde_offset, float[] rcondv, int _rcondv_offset, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sgeevx(balanc, jobvl, jobvr, sense, n, a, _a_offset, lda, wr, _wr_offset, wi, _wi_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, ilo, ihi, scale, _scale_offset, abnrm, rconde, _rconde_offset, rcondv, _rcondv_offset, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  protected void sgegsK(String jobvsl, String jobvsr, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] alphar, int _alphar_offset, float[] alphai, int _alphai_offset, float[] beta, int _beta_offset, float[] vsl, int _vsl_offset, int ldvsl, float[] vsr, int _vsr_offset, int ldvsr, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sgegs(jobvsl, jobvsr, n, a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vsl, _vsl_offset, ldvsl, vsr, _vsr_offset, ldvsr, work, _work_offset, lwork, info);
  }

  protected void sgegvK(String jobvl, String jobvr, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] alphar, int _alphar_offset, float[] alphai, int _alphai_offset, float[] beta, int _beta_offset, float[] vl, int _vl_offset, int ldvl, float[] vr, int _vr_offset, int ldvr, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sgegv(jobvl, jobvr, n, a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, work, _work_offset, lwork, info);
  }

  protected void sgehd2K(int n, int ilo, int ihi, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sgehd2(n, ilo, ihi, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  protected void sgehrdK(int n, int ilo, int ihi, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sgehrd(n, ilo, ihi, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void sgelq2K(int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sgelq2(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  protected void sgelqfK(int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sgelqf(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void sgelsK(String trans, int m, int n, int nrhs, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sgels(trans, m, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, work, _work_offset, lwork, info);
  }

  protected void sgelsdK(int m, int n, int nrhs, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] s, int _s_offset, float rcond, org.netlib.util.intW rank, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sgelsd(m, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, s, _s_offset, rcond, rank, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  protected void sgelssK(int m, int n, int nrhs, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] s, int _s_offset, float rcond, org.netlib.util.intW rank, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sgelss(m, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, s, _s_offset, rcond, rank, work, _work_offset, lwork, info);
  }

  protected void sgelsxK(int m, int n, int nrhs, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, int[] jpvt, int _jpvt_offset, float rcond, org.netlib.util.intW rank, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sgelsx(m, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, jpvt, _jpvt_offset, rcond, rank, work, _work_offset, info);
  }

  protected void sgelsyK(int m, int n, int nrhs, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, int[] jpvt, int _jpvt_offset, float rcond, org.netlib.util.intW rank, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sgelsy(m, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, jpvt, _jpvt_offset, rcond, rank, work, _work_offset, lwork, info);
  }

  protected void sgeql2K(int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sgeql2(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  protected void sgeqlfK(int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sgeqlf(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void sgeqp3K(int m, int n, float[] a, int _a_offset, int lda, int[] jpvt, int _jpvt_offset, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sgeqp3(m, n, a, _a_offset, lda, jpvt, _jpvt_offset, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void sgeqpfK(int m, int n, float[] a, int _a_offset, int lda, int[] jpvt, int _jpvt_offset, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sgeqpf(m, n, a, _a_offset, lda, jpvt, _jpvt_offset, tau, _tau_offset, work, _work_offset, info);
  }

  protected void sgeqr2K(int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sgeqr2(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  protected void sgeqrfK(int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sgeqrf(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void sgerfsK(String trans, int n, int nrhs, float[] a, int _a_offset, int lda, float[] af, int _af_offset, int ldaf, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sgerfs(trans, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void sgerq2K(int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sgerq2(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  protected void sgerqfK(int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sgerqf(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void sgesc2K(int n, float[] a, int _a_offset, int lda, float[] rhs, int _rhs_offset, int[] ipiv, int _ipiv_offset, int[] jpiv, int _jpiv_offset, org.netlib.util.floatW scale) {
    lapack.sgesc2(n, a, _a_offset, lda, rhs, _rhs_offset, ipiv, _ipiv_offset, jpiv, _jpiv_offset, scale);
  }

  protected void sgesddK(String jobz, int m, int n, float[] a, int _a_offset, int lda, float[] s, int _s_offset, float[] u, int _u_offset, int ldu, float[] vt, int _vt_offset, int ldvt, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sgesdd(jobz, m, n, a, _a_offset, lda, s, _s_offset, u, _u_offset, ldu, vt, _vt_offset, ldvt, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  protected void sgesvK(int n, int nrhs, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.sgesv(n, nrhs, a, _a_offset, lda, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  protected void sgesvdK(String jobu, String jobvt, int m, int n, float[] a, int _a_offset, int lda, float[] s, int _s_offset, float[] u, int _u_offset, int ldu, float[] vt, int _vt_offset, int ldvt, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sgesvd(jobu, jobvt, m, n, a, _a_offset, lda, s, _s_offset, u, _u_offset, ldu, vt, _vt_offset, ldvt, work, _work_offset, lwork, info);
  }

  protected void sgesvxK(String fact, String trans, int n, int nrhs, float[] a, int _a_offset, int lda, float[] af, int _af_offset, int ldaf, int[] ipiv, int _ipiv_offset, org.netlib.util.StringW equed, float[] r, int _r_offset, float[] c, int _c_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, org.netlib.util.floatW rcond, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sgesvx(fact, trans, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, ipiv, _ipiv_offset, equed, r, _r_offset, c, _c_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void sgetc2K(int n, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, int[] jpiv, int _jpiv_offset, org.netlib.util.intW info) {
    lapack.sgetc2(n, a, _a_offset, lda, ipiv, _ipiv_offset, jpiv, _jpiv_offset, info);
  }

  protected void sgetf2K(int m, int n, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    lapack.sgetf2(m, n, a, _a_offset, lda, ipiv, _ipiv_offset, info);
  }

  protected void sgetrfK(int m, int n, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    lapack.sgetrf(m, n, a, _a_offset, lda, ipiv, _ipiv_offset, info);
  }

  protected void sgetriK(int n, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sgetri(n, a, _a_offset, lda, ipiv, _ipiv_offset, work, _work_offset, lwork, info);
  }

  protected void sgetrsK(String trans, int n, int nrhs, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.sgetrs(trans, n, nrhs, a, _a_offset, lda, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  protected void sggbakK(String job, String side, int n, int ilo, int ihi, float[] lscale, int _lscale_offset, float[] rscale, int _rscale_offset, int m, float[] v, int _v_offset, int ldv, org.netlib.util.intW info) {
    lapack.sggbak(job, side, n, ilo, ihi, lscale, _lscale_offset, rscale, _rscale_offset, m, v, _v_offset, ldv, info);
  }

  protected void sggbalK(String job, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, org.netlib.util.intW ilo, org.netlib.util.intW ihi, float[] lscale, int _lscale_offset, float[] rscale, int _rscale_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sggbal(job, n, a, _a_offset, lda, b, _b_offset, ldb, ilo, ihi, lscale, _lscale_offset, rscale, _rscale_offset, work, _work_offset, info);
  }

  protected void sggesK(String jobvsl, String jobvsr, String sort, java.lang.Object selctg, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, org.netlib.util.intW sdim, float[] alphar, int _alphar_offset, float[] alphai, int _alphai_offset, float[] beta, int _beta_offset, float[] vsl, int _vsl_offset, int ldvsl, float[] vsr, int _vsr_offset, int ldvsr, float[] work, int _work_offset, int lwork, boolean[] bwork, int _bwork_offset, org.netlib.util.intW info) {
    lapack.sgges(jobvsl, jobvsr, sort, selctg, n, a, _a_offset, lda, b, _b_offset, ldb, sdim, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vsl, _vsl_offset, ldvsl, vsr, _vsr_offset, ldvsr, work, _work_offset, lwork, bwork, _bwork_offset, info);
  }

  protected void sggesxK(String jobvsl, String jobvsr, String sort, java.lang.Object selctg, String sense, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, org.netlib.util.intW sdim, float[] alphar, int _alphar_offset, float[] alphai, int _alphai_offset, float[] beta, int _beta_offset, float[] vsl, int _vsl_offset, int ldvsl, float[] vsr, int _vsr_offset, int ldvsr, float[] rconde, int _rconde_offset, float[] rcondv, int _rcondv_offset, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, boolean[] bwork, int _bwork_offset, org.netlib.util.intW info) {
    lapack.sggesx(jobvsl, jobvsr, sort, selctg, sense, n, a, _a_offset, lda, b, _b_offset, ldb, sdim, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vsl, _vsl_offset, ldvsl, vsr, _vsr_offset, ldvsr, rconde, _rconde_offset, rcondv, _rcondv_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, bwork, _bwork_offset, info);
  }

  protected void sggevK(String jobvl, String jobvr, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] alphar, int _alphar_offset, float[] alphai, int _alphai_offset, float[] beta, int _beta_offset, float[] vl, int _vl_offset, int ldvl, float[] vr, int _vr_offset, int ldvr, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sggev(jobvl, jobvr, n, a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, work, _work_offset, lwork, info);
  }

  protected void sggevxK(String balanc, String jobvl, String jobvr, String sense, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] alphar, int _alphar_offset, float[] alphai, int _alphai_offset, float[] beta, int _beta_offset, float[] vl, int _vl_offset, int ldvl, float[] vr, int _vr_offset, int ldvr, org.netlib.util.intW ilo, org.netlib.util.intW ihi, float[] lscale, int _lscale_offset, float[] rscale, int _rscale_offset, org.netlib.util.floatW abnrm, org.netlib.util.floatW bbnrm, float[] rconde, int _rconde_offset, float[] rcondv, int _rcondv_offset, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, boolean[] bwork, int _bwork_offset, org.netlib.util.intW info) {
    lapack.sggevx(balanc, jobvl, jobvr, sense, n, a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, ilo, ihi, lscale, _lscale_offset, rscale, _rscale_offset, abnrm, bbnrm, rconde, _rconde_offset, rcondv, _rcondv_offset, work, _work_offset, lwork, iwork, _iwork_offset, bwork, _bwork_offset, info);
  }

  protected void sggglmK(int n, int m, int p, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] d, int _d_offset, float[] x, int _x_offset, float[] y, int _y_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sggglm(n, m, p, a, _a_offset, lda, b, _b_offset, ldb, d, _d_offset, x, _x_offset, y, _y_offset, work, _work_offset, lwork, info);
  }

  protected void sgghrdK(String compq, String compz, int n, int ilo, int ihi, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] q, int _q_offset, int ldq, float[] z, int _z_offset, int ldz, org.netlib.util.intW info) {
    lapack.sgghrd(compq, compz, n, ilo, ihi, a, _a_offset, lda, b, _b_offset, ldb, q, _q_offset, ldq, z, _z_offset, ldz, info);
  }

  protected void sgglseK(int m, int n, int p, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] c, int _c_offset, float[] d, int _d_offset, float[] x, int _x_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sgglse(m, n, p, a, _a_offset, lda, b, _b_offset, ldb, c, _c_offset, d, _d_offset, x, _x_offset, work, _work_offset, lwork, info);
  }

  protected void sggqrfK(int n, int m, int p, float[] a, int _a_offset, int lda, float[] taua, int _taua_offset, float[] b, int _b_offset, int ldb, float[] taub, int _taub_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sggqrf(n, m, p, a, _a_offset, lda, taua, _taua_offset, b, _b_offset, ldb, taub, _taub_offset, work, _work_offset, lwork, info);
  }

  protected void sggrqfK(int m, int p, int n, float[] a, int _a_offset, int lda, float[] taua, int _taua_offset, float[] b, int _b_offset, int ldb, float[] taub, int _taub_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sggrqf(m, p, n, a, _a_offset, lda, taua, _taua_offset, b, _b_offset, ldb, taub, _taub_offset, work, _work_offset, lwork, info);
  }

  protected void sggsvdK(String jobu, String jobv, String jobq, int m, int n, int p, org.netlib.util.intW k, org.netlib.util.intW l, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] alpha, int _alpha_offset, float[] beta, int _beta_offset, float[] u, int _u_offset, int ldu, float[] v, int _v_offset, int ldv, float[] q, int _q_offset, int ldq, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sggsvd(jobu, jobv, jobq, m, n, p, k, l, a, _a_offset, lda, b, _b_offset, ldb, alpha, _alpha_offset, beta, _beta_offset, u, _u_offset, ldu, v, _v_offset, ldv, q, _q_offset, ldq, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void sggsvpK(String jobu, String jobv, String jobq, int m, int p, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float tola, float tolb, org.netlib.util.intW k, org.netlib.util.intW l, float[] u, int _u_offset, int ldu, float[] v, int _v_offset, int ldv, float[] q, int _q_offset, int ldq, int[] iwork, int _iwork_offset, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sggsvp(jobu, jobv, jobq, m, p, n, a, _a_offset, lda, b, _b_offset, ldb, tola, tolb, k, l, u, _u_offset, ldu, v, _v_offset, ldv, q, _q_offset, ldq, iwork, _iwork_offset, tau, _tau_offset, work, _work_offset, info);
  }

  protected void sgtconK(String norm, int n, float[] dl, int _dl_offset, float[] d, int _d_offset, float[] du, int _du_offset, float[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, float anorm, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sgtcon(norm, n, dl, _dl_offset, d, _d_offset, du, _du_offset, du2, _du2_offset, ipiv, _ipiv_offset, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void sgtrfsK(String trans, int n, int nrhs, float[] dl, int _dl_offset, float[] d, int _d_offset, float[] du, int _du_offset, float[] dlf, int _dlf_offset, float[] df, int _df_offset, float[] duf, int _duf_offset, float[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sgtrfs(trans, n, nrhs, dl, _dl_offset, d, _d_offset, du, _du_offset, dlf, _dlf_offset, df, _df_offset, duf, _duf_offset, du2, _du2_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void sgtsvK(int n, int nrhs, float[] dl, int _dl_offset, float[] d, int _d_offset, float[] du, int _du_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.sgtsv(n, nrhs, dl, _dl_offset, d, _d_offset, du, _du_offset, b, _b_offset, ldb, info);
  }

  protected void sgtsvxK(String fact, String trans, int n, int nrhs, float[] dl, int _dl_offset, float[] d, int _d_offset, float[] du, int _du_offset, float[] dlf, int _dlf_offset, float[] df, int _df_offset, float[] duf, int _duf_offset, float[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, org.netlib.util.floatW rcond, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sgtsvx(fact, trans, n, nrhs, dl, _dl_offset, d, _d_offset, du, _du_offset, dlf, _dlf_offset, df, _df_offset, duf, _duf_offset, du2, _du2_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void sgttrfK(int n, float[] dl, int _dl_offset, float[] d, int _d_offset, float[] du, int _du_offset, float[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    lapack.sgttrf(n, dl, _dl_offset, d, _d_offset, du, _du_offset, du2, _du2_offset, ipiv, _ipiv_offset, info);
  }

  protected void sgttrsK(String trans, int n, int nrhs, float[] dl, int _dl_offset, float[] d, int _d_offset, float[] du, int _du_offset, float[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.sgttrs(trans, n, nrhs, dl, _dl_offset, d, _d_offset, du, _du_offset, du2, _du2_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  protected void sgtts2K(int itrans, int n, int nrhs, float[] dl, int _dl_offset, float[] d, int _d_offset, float[] du, int _du_offset, float[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb) {
    lapack.sgtts2(itrans, n, nrhs, dl, _dl_offset, d, _d_offset, du, _du_offset, du2, _du2_offset, ipiv, _ipiv_offset, b, _b_offset, ldb);
  }

  protected void shgeqzK(String job, String compq, String compz, int n, int ilo, int ihi, float[] h, int _h_offset, int ldh, float[] t, int _t_offset, int ldt, float[] alphar, int _alphar_offset, float[] alphai, int _alphai_offset, float[] beta, int _beta_offset, float[] q, int _q_offset, int ldq, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.shgeqz(job, compq, compz, n, ilo, ihi, h, _h_offset, ldh, t, _t_offset, ldt, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, q, _q_offset, ldq, z, _z_offset, ldz, work, _work_offset, lwork, info);
  }

  protected void shseinK(String side, String eigsrc, String initv, boolean[] select, int _select_offset, int n, float[] h, int _h_offset, int ldh, float[] wr, int _wr_offset, float[] wi, int _wi_offset, float[] vl, int _vl_offset, int ldvl, float[] vr, int _vr_offset, int ldvr, int mm, org.netlib.util.intW m, float[] work, int _work_offset, int[] ifaill, int _ifaill_offset, int[] ifailr, int _ifailr_offset, org.netlib.util.intW info) {
    lapack.shsein(side, eigsrc, initv, select, _select_offset, n, h, _h_offset, ldh, wr, _wr_offset, wi, _wi_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, mm, m, work, _work_offset, ifaill, _ifaill_offset, ifailr, _ifailr_offset, info);
  }

  protected void shseqrK(String job, String compz, int n, int ilo, int ihi, float[] h, int _h_offset, int ldh, float[] wr, int _wr_offset, float[] wi, int _wi_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.shseqr(job, compz, n, ilo, ihi, h, _h_offset, ldh, wr, _wr_offset, wi, _wi_offset, z, _z_offset, ldz, work, _work_offset, lwork, info);
  }

  protected boolean sisnanK(float sin) {
    return lapack.sisnan(sin);
  }

  protected void slabadK(org.netlib.util.floatW small, org.netlib.util.floatW large) {
    lapack.slabad(small, large);
  }

  protected void slabrdK(int m, int n, int nb, float[] a, int _a_offset, int lda, float[] d, int _d_offset, float[] e, int _e_offset, float[] tauq, int _tauq_offset, float[] taup, int _taup_offset, float[] x, int _x_offset, int ldx, float[] y, int _y_offset, int ldy) {
    lapack.slabrd(m, n, nb, a, _a_offset, lda, d, _d_offset, e, _e_offset, tauq, _tauq_offset, taup, _taup_offset, x, _x_offset, ldx, y, _y_offset, ldy);
  }

  protected void slacn2K(int n, float[] v, int _v_offset, float[] x, int _x_offset, int[] isgn, int _isgn_offset, org.netlib.util.floatW est, org.netlib.util.intW kase, int[] isave, int _isave_offset) {
    lapack.slacn2(n, v, _v_offset, x, _x_offset, isgn, _isgn_offset, est, kase, isave, _isave_offset);
  }

  protected void slaconK(int n, float[] v, int _v_offset, float[] x, int _x_offset, int[] isgn, int _isgn_offset, org.netlib.util.floatW est, org.netlib.util.intW kase) {
    lapack.slacon(n, v, _v_offset, x, _x_offset, isgn, _isgn_offset, est, kase);
  }

  protected void slacpyK(String uplo, int m, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb) {
    lapack.slacpy(uplo, m, n, a, _a_offset, lda, b, _b_offset, ldb);
  }

  protected void sladivK(float a, float b, float c, float d, org.netlib.util.floatW p, org.netlib.util.floatW q) {
    lapack.sladiv(a, b, c, d, p, q);
  }

  protected void slae2K(float a, float b, float c, org.netlib.util.floatW rt1, org.netlib.util.floatW rt2) {
    lapack.slae2(a, b, c, rt1, rt2);
  }

  protected void slaebzK(int ijob, int nitmax, int n, int mmax, int minp, int nbmin, float abstol, float reltol, float pivmin, float[] d, int _d_offset, float[] e, int _e_offset, float[] e2, int _e2_offset, int[] nval, int _nval_offset, float[] ab, int _ab_offset, float[] c, int _c_offset, org.netlib.util.intW mout, int[] nab, int _nab_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.slaebz(ijob, nitmax, n, mmax, minp, nbmin, abstol, reltol, pivmin, d, _d_offset, e, _e_offset, e2, _e2_offset, nval, _nval_offset, ab, _ab_offset, c, _c_offset, mout, nab, _nab_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void slaed0K(int icompq, int qsiz, int n, float[] d, int _d_offset, float[] e, int _e_offset, float[] q, int _q_offset, int ldq, float[] qstore, int _qstore_offset, int ldqs, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.slaed0(icompq, qsiz, n, d, _d_offset, e, _e_offset, q, _q_offset, ldq, qstore, _qstore_offset, ldqs, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void slaed1K(int n, float[] d, int _d_offset, float[] q, int _q_offset, int ldq, int[] indxq, int _indxq_offset, org.netlib.util.floatW rho, int cutpnt, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.slaed1(n, d, _d_offset, q, _q_offset, ldq, indxq, _indxq_offset, rho, cutpnt, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void slaed2K(org.netlib.util.intW k, int n, int n1, float[] d, int _d_offset, float[] q, int _q_offset, int ldq, int[] indxq, int _indxq_offset, org.netlib.util.floatW rho, float[] z, int _z_offset, float[] dlamda, int _dlamda_offset, float[] w, int _w_offset, float[] q2, int _q2_offset, int[] indx, int _indx_offset, int[] indxc, int _indxc_offset, int[] indxp, int _indxp_offset, int[] coltyp, int _coltyp_offset, org.netlib.util.intW info) {
    lapack.slaed2(k, n, n1, d, _d_offset, q, _q_offset, ldq, indxq, _indxq_offset, rho, z, _z_offset, dlamda, _dlamda_offset, w, _w_offset, q2, _q2_offset, indx, _indx_offset, indxc, _indxc_offset, indxp, _indxp_offset, coltyp, _coltyp_offset, info);
  }

  protected void slaed3K(int k, int n, int n1, float[] d, int _d_offset, float[] q, int _q_offset, int ldq, float rho, float[] dlamda, int _dlamda_offset, float[] q2, int _q2_offset, int[] indx, int _indx_offset, int[] ctot, int _ctot_offset, float[] w, int _w_offset, float[] s, int _s_offset, org.netlib.util.intW info) {
    lapack.slaed3(k, n, n1, d, _d_offset, q, _q_offset, ldq, rho, dlamda, _dlamda_offset, q2, _q2_offset, indx, _indx_offset, ctot, _ctot_offset, w, _w_offset, s, _s_offset, info);
  }

  protected void slaed4K(int n, int i, float[] d, int _d_offset, float[] z, int _z_offset, float[] delta, int _delta_offset, float rho, org.netlib.util.floatW dlam, org.netlib.util.intW info) {
    lapack.slaed4(n, i, d, _d_offset, z, _z_offset, delta, _delta_offset, rho, dlam, info);
  }

  protected void slaed5K(int i, float[] d, int _d_offset, float[] z, int _z_offset, float[] delta, int _delta_offset, float rho, org.netlib.util.floatW dlam) {
    lapack.slaed5(i, d, _d_offset, z, _z_offset, delta, _delta_offset, rho, dlam);
  }

  protected void slaed6K(int kniter, boolean orgati, float rho, float[] d, int _d_offset, float[] z, int _z_offset, float finit, org.netlib.util.floatW tau, org.netlib.util.intW info) {
    lapack.slaed6(kniter, orgati, rho, d, _d_offset, z, _z_offset, finit, tau, info);
  }

  protected void slaed7K(int icompq, int n, int qsiz, int tlvls, int curlvl, int curpbm, float[] d, int _d_offset, float[] q, int _q_offset, int ldq, int[] indxq, int _indxq_offset, org.netlib.util.floatW rho, int cutpnt, float[] qstore, int _qstore_offset, int[] qptr, int _qptr_offset, int[] prmptr, int _prmptr_offset, int[] perm, int _perm_offset, int[] givptr, int _givptr_offset, int[] givcol, int _givcol_offset, float[] givnum, int _givnum_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.slaed7(icompq, n, qsiz, tlvls, curlvl, curpbm, d, _d_offset, q, _q_offset, ldq, indxq, _indxq_offset, rho, cutpnt, qstore, _qstore_offset, qptr, _qptr_offset, prmptr, _prmptr_offset, perm, _perm_offset, givptr, _givptr_offset, givcol, _givcol_offset, givnum, _givnum_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void slaed8K(int icompq, org.netlib.util.intW k, int n, int qsiz, float[] d, int _d_offset, float[] q, int _q_offset, int ldq, int[] indxq, int _indxq_offset, org.netlib.util.floatW rho, int cutpnt, float[] z, int _z_offset, float[] dlamda, int _dlamda_offset, float[] q2, int _q2_offset, int ldq2, float[] w, int _w_offset, int[] perm, int _perm_offset, org.netlib.util.intW givptr, int[] givcol, int _givcol_offset, float[] givnum, int _givnum_offset, int[] indxp, int _indxp_offset, int[] indx, int _indx_offset, org.netlib.util.intW info) {
    lapack.slaed8(icompq, k, n, qsiz, d, _d_offset, q, _q_offset, ldq, indxq, _indxq_offset, rho, cutpnt, z, _z_offset, dlamda, _dlamda_offset, q2, _q2_offset, ldq2, w, _w_offset, perm, _perm_offset, givptr, givcol, _givcol_offset, givnum, _givnum_offset, indxp, _indxp_offset, indx, _indx_offset, info);
  }

  protected void slaed9K(int k, int kstart, int kstop, int n, float[] d, int _d_offset, float[] q, int _q_offset, int ldq, float rho, float[] dlamda, int _dlamda_offset, float[] w, int _w_offset, float[] s, int _s_offset, int lds, org.netlib.util.intW info) {
    lapack.slaed9(k, kstart, kstop, n, d, _d_offset, q, _q_offset, ldq, rho, dlamda, _dlamda_offset, w, _w_offset, s, _s_offset, lds, info);
  }

  protected void slaedaK(int n, int tlvls, int curlvl, int curpbm, int[] prmptr, int _prmptr_offset, int[] perm, int _perm_offset, int[] givptr, int _givptr_offset, int[] givcol, int _givcol_offset, float[] givnum, int _givnum_offset, float[] q, int _q_offset, int[] qptr, int _qptr_offset, float[] z, int _z_offset, float[] ztemp, int _ztemp_offset, org.netlib.util.intW info) {
    lapack.slaeda(n, tlvls, curlvl, curpbm, prmptr, _prmptr_offset, perm, _perm_offset, givptr, _givptr_offset, givcol, _givcol_offset, givnum, _givnum_offset, q, _q_offset, qptr, _qptr_offset, z, _z_offset, ztemp, _ztemp_offset, info);
  }

  protected void slaeinK(boolean rightv, boolean noinit, int n, float[] h, int _h_offset, int ldh, float wr, float wi, float[] vr, int _vr_offset, float[] vi, int _vi_offset, float[] b, int _b_offset, int ldb, float[] work, int _work_offset, float eps3, float smlnum, float bignum, org.netlib.util.intW info) {
    lapack.slaein(rightv, noinit, n, h, _h_offset, ldh, wr, wi, vr, _vr_offset, vi, _vi_offset, b, _b_offset, ldb, work, _work_offset, eps3, smlnum, bignum, info);
  }

  protected void slaev2K(float a, float b, float c, org.netlib.util.floatW rt1, org.netlib.util.floatW rt2, org.netlib.util.floatW cs1, org.netlib.util.floatW sn1) {
    lapack.slaev2(a, b, c, rt1, rt2, cs1, sn1);
  }

  protected void slaexcK(boolean wantq, int n, float[] t, int _t_offset, int ldt, float[] q, int _q_offset, int ldq, int j1, int n1, int n2, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.slaexc(wantq, n, t, _t_offset, ldt, q, _q_offset, ldq, j1, n1, n2, work, _work_offset, info);
  }

  protected void slag2K(float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float safmin, org.netlib.util.floatW scale1, org.netlib.util.floatW scale2, org.netlib.util.floatW wr1, org.netlib.util.floatW wr2, org.netlib.util.floatW wi) {
    lapack.slag2(a, _a_offset, lda, b, _b_offset, ldb, safmin, scale1, scale2, wr1, wr2, wi);
  }

  protected void slag2dK(int m, int n, float[] sa, int _sa_offset, int ldsa, double[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    lapack.slag2d(m, n, sa, _sa_offset, ldsa, a, _a_offset, lda, info);
  }

  protected void slags2K(boolean upper, float a1, float a2, float a3, float b1, float b2, float b3, org.netlib.util.floatW csu, org.netlib.util.floatW snu, org.netlib.util.floatW csv, org.netlib.util.floatW snv, org.netlib.util.floatW csq, org.netlib.util.floatW snq) {
    lapack.slags2(upper, a1, a2, a3, b1, b2, b3, csu, snu, csv, snv, csq, snq);
  }

  protected void slagtfK(int n, float[] a, int _a_offset, float lambda, float[] b, int _b_offset, float[] c, int _c_offset, float tol, float[] d, int _d_offset, int[] in, int _in_offset, org.netlib.util.intW info) {
    lapack.slagtf(n, a, _a_offset, lambda, b, _b_offset, c, _c_offset, tol, d, _d_offset, in, _in_offset, info);
  }

  protected void slagtmK(String trans, int n, int nrhs, float alpha, float[] dl, int _dl_offset, float[] d, int _d_offset, float[] du, int _du_offset, float[] x, int _x_offset, int ldx, float beta, float[] b, int _b_offset, int ldb) {
    lapack.slagtm(trans, n, nrhs, alpha, dl, _dl_offset, d, _d_offset, du, _du_offset, x, _x_offset, ldx, beta, b, _b_offset, ldb);
  }

  protected void slagtsK(int job, int n, float[] a, int _a_offset, float[] b, int _b_offset, float[] c, int _c_offset, float[] d, int _d_offset, int[] in, int _in_offset, float[] y, int _y_offset, org.netlib.util.floatW tol, org.netlib.util.intW info) {
    lapack.slagts(job, n, a, _a_offset, b, _b_offset, c, _c_offset, d, _d_offset, in, _in_offset, y, _y_offset, tol, info);
  }

  protected void slagv2K(float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] alphar, int _alphar_offset, float[] alphai, int _alphai_offset, float[] beta, int _beta_offset, org.netlib.util.floatW csl, org.netlib.util.floatW snl, org.netlib.util.floatW csr, org.netlib.util.floatW snr) {
    lapack.slagv2(a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, csl, snl, csr, snr);
  }

  protected void slahqrK(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int _h_offset, int ldh, float[] wr, int _wr_offset, float[] wi, int _wi_offset, int iloz, int ihiz, float[] z, int _z_offset, int ldz, org.netlib.util.intW info) {
    lapack.slahqr(wantt, wantz, n, ilo, ihi, h, _h_offset, ldh, wr, _wr_offset, wi, _wi_offset, iloz, ihiz, z, _z_offset, ldz, info);
  }

  protected void slahr2K(int n, int k, int nb, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] t, int _t_offset, int ldt, float[] y, int _y_offset, int ldy) {
    lapack.slahr2(n, k, nb, a, _a_offset, lda, tau, _tau_offset, t, _t_offset, ldt, y, _y_offset, ldy);
  }

  protected void slahrdK(int n, int k, int nb, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] t, int _t_offset, int ldt, float[] y, int _y_offset, int ldy) {
    lapack.slahrd(n, k, nb, a, _a_offset, lda, tau, _tau_offset, t, _t_offset, ldt, y, _y_offset, ldy);
  }

  protected void slaic1K(int job, int j, float[] x, int _x_offset, float sest, float[] w, int _w_offset, float gamma, org.netlib.util.floatW sestpr, org.netlib.util.floatW s, org.netlib.util.floatW c) {
    lapack.slaic1(job, j, x, _x_offset, sest, w, _w_offset, gamma, sestpr, s, c);
  }

  protected boolean slaisnanK(float sin1, float sin2) {
    return lapack.slaisnan(sin1, sin2);
  }

  protected void slaln2K(boolean ltrans, int na, int nw, float smin, float ca, float[] a, int _a_offset, int lda, float d1, float d2, float[] b, int _b_offset, int ldb, float wr, float wi, float[] x, int _x_offset, int ldx, org.netlib.util.floatW scale, org.netlib.util.floatW xnorm, org.netlib.util.intW info) {
    lapack.slaln2(ltrans, na, nw, smin, ca, a, _a_offset, lda, d1, d2, b, _b_offset, ldb, wr, wi, x, _x_offset, ldx, scale, xnorm, info);
  }

  protected void slals0K(int icompq, int nl, int nr, int sqre, int nrhs, float[] b, int _b_offset, int ldb, float[] bx, int _bx_offset, int ldbx, int[] perm, int _perm_offset, int givptr, int[] givcol, int _givcol_offset, int ldgcol, float[] givnum, int _givnum_offset, int ldgnum, float[] poles, int _poles_offset, float[] difl, int _difl_offset, float[] difr, int _difr_offset, float[] z, int _z_offset, int k, float c, float s, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.slals0(icompq, nl, nr, sqre, nrhs, b, _b_offset, ldb, bx, _bx_offset, ldbx, perm, _perm_offset, givptr, givcol, _givcol_offset, ldgcol, givnum, _givnum_offset, ldgnum, poles, _poles_offset, difl, _difl_offset, difr, _difr_offset, z, _z_offset, k, c, s, work, _work_offset, info);
  }

  protected void slalsaK(int icompq, int smlsiz, int n, int nrhs, float[] b, int _b_offset, int ldb, float[] bx, int _bx_offset, int ldbx, float[] u, int _u_offset, int ldu, float[] vt, int _vt_offset, int[] k, int _k_offset, float[] difl, int _difl_offset, float[] difr, int _difr_offset, float[] z, int _z_offset, float[] poles, int _poles_offset, int[] givptr, int _givptr_offset, int[] givcol, int _givcol_offset, int ldgcol, int[] perm, int _perm_offset, float[] givnum, int _givnum_offset, float[] c, int _c_offset, float[] s, int _s_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.slalsa(icompq, smlsiz, n, nrhs, b, _b_offset, ldb, bx, _bx_offset, ldbx, u, _u_offset, ldu, vt, _vt_offset, k, _k_offset, difl, _difl_offset, difr, _difr_offset, z, _z_offset, poles, _poles_offset, givptr, _givptr_offset, givcol, _givcol_offset, ldgcol, perm, _perm_offset, givnum, _givnum_offset, c, _c_offset, s, _s_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void slalsdK(String uplo, int smlsiz, int n, int nrhs, float[] d, int _d_offset, float[] e, int _e_offset, float[] b, int _b_offset, int ldb, float rcond, org.netlib.util.intW rank, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.slalsd(uplo, smlsiz, n, nrhs, d, _d_offset, e, _e_offset, b, _b_offset, ldb, rcond, rank, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void slamrgK(int n1, int n2, float[] a, int _a_offset, int strd1, int strd2, int[] index, int _index_offset) {
    lapack.slamrg(n1, n2, a, _a_offset, strd1, strd2, index, _index_offset);
  }

  protected int slanegK(int n, float[] d, int _d_offset, float[] lld, int _lld_offset, float sigma, float pivmin, int r) {
    return lapack.slaneg(n, d, _d_offset, lld, _lld_offset, sigma, pivmin, r);
  }

  protected float slangbK(String norm, int n, int kl, int ku, float[] ab, int _ab_offset, int ldab, float[] work, int _work_offset) {
    return lapack.slangb(norm, n, kl, ku, ab, _ab_offset, ldab, work, _work_offset);
  }

  protected float slangeK(String norm, int m, int n, float[] a, int _a_offset, int lda, float[] work, int _work_offset) {
    return lapack.slange(norm, m, n, a, _a_offset, lda, work, _work_offset);
  }

  protected float slangtK(String norm, int n, float[] dl, int _dl_offset, float[] d, int _d_offset, float[] du, int _du_offset) {
    return lapack.slangt(norm, n, dl, _dl_offset, d, _d_offset, du, _du_offset);
  }

  protected float slanhsK(String norm, int n, float[] a, int _a_offset, int lda, float[] work, int _work_offset) {
    return lapack.slanhs(norm, n, a, _a_offset, lda, work, _work_offset);
  }

  protected float slansbK(String norm, String uplo, int n, int k, float[] ab, int _ab_offset, int ldab, float[] work, int _work_offset) {
    return lapack.slansb(norm, uplo, n, k, ab, _ab_offset, ldab, work, _work_offset);
  }

  protected float slanspK(String norm, String uplo, int n, float[] ap, int _ap_offset, float[] work, int _work_offset) {
    return lapack.slansp(norm, uplo, n, ap, _ap_offset, work, _work_offset);
  }

  protected float slanstK(String norm, int n, float[] d, int _d_offset, float[] e, int _e_offset) {
    return lapack.slanst(norm, n, d, _d_offset, e, _e_offset);
  }

  protected float slansyK(String norm, String uplo, int n, float[] a, int _a_offset, int lda, float[] work, int _work_offset) {
    return lapack.slansy(norm, uplo, n, a, _a_offset, lda, work, _work_offset);
  }

  protected float slantbK(String norm, String uplo, String diag, int n, int k, float[] ab, int _ab_offset, int ldab, float[] work, int _work_offset) {
    return lapack.slantb(norm, uplo, diag, n, k, ab, _ab_offset, ldab, work, _work_offset);
  }

  protected float slantpK(String norm, String uplo, String diag, int n, float[] ap, int _ap_offset, float[] work, int _work_offset) {
    return lapack.slantp(norm, uplo, diag, n, ap, _ap_offset, work, _work_offset);
  }

  protected float slantrK(String norm, String uplo, String diag, int m, int n, float[] a, int _a_offset, int lda, float[] work, int _work_offset) {
    return lapack.slantr(norm, uplo, diag, m, n, a, _a_offset, lda, work, _work_offset);
  }

  protected void slanv2K(org.netlib.util.floatW a, org.netlib.util.floatW b, org.netlib.util.floatW c, org.netlib.util.floatW d, org.netlib.util.floatW rt1r, org.netlib.util.floatW rt1i, org.netlib.util.floatW rt2r, org.netlib.util.floatW rt2i, org.netlib.util.floatW cs, org.netlib.util.floatW sn) {
    lapack.slanv2(a, b, c, d, rt1r, rt1i, rt2r, rt2i, cs, sn);
  }

  protected void slapllK(int n, float[] x, int _x_offset, int incx, float[] y, int _y_offset, int incy, org.netlib.util.floatW ssmin) {
    lapack.slapll(n, x, _x_offset, incx, y, _y_offset, incy, ssmin);
  }

  protected void slapmtK(boolean forwrd, int m, int n, float[] x, int _x_offset, int ldx, int[] k, int _k_offset) {
    lapack.slapmt(forwrd, m, n, x, _x_offset, ldx, k, _k_offset);
  }

  protected float slapy2K(float x, float y) {
    return lapack.slapy2(x, y);
  }

  protected float slapy3K(float x, float y, float z) {
    return lapack.slapy3(x, y, z);
  }

  protected void slaqgbK(int m, int n, int kl, int ku, float[] ab, int _ab_offset, int ldab, float[] r, int _r_offset, float[] c, int _c_offset, float rowcnd, float colcnd, float amax, org.netlib.util.StringW equed) {
    lapack.slaqgb(m, n, kl, ku, ab, _ab_offset, ldab, r, _r_offset, c, _c_offset, rowcnd, colcnd, amax, equed);
  }

  protected void slaqgeK(int m, int n, float[] a, int _a_offset, int lda, float[] r, int _r_offset, float[] c, int _c_offset, float rowcnd, float colcnd, float amax, org.netlib.util.StringW equed) {
    lapack.slaqge(m, n, a, _a_offset, lda, r, _r_offset, c, _c_offset, rowcnd, colcnd, amax, equed);
  }

  protected void slaqp2K(int m, int n, int offset, float[] a, int _a_offset, int lda, int[] jpvt, int _jpvt_offset, float[] tau, int _tau_offset, float[] vn1, int _vn1_offset, float[] vn2, int _vn2_offset, float[] work, int _work_offset) {
    lapack.slaqp2(m, n, offset, a, _a_offset, lda, jpvt, _jpvt_offset, tau, _tau_offset, vn1, _vn1_offset, vn2, _vn2_offset, work, _work_offset);
  }

  protected void slaqpsK(int m, int n, int offset, int nb, org.netlib.util.intW kb, float[] a, int _a_offset, int lda, int[] jpvt, int _jpvt_offset, float[] tau, int _tau_offset, float[] vn1, int _vn1_offset, float[] vn2, int _vn2_offset, float[] auxv, int _auxv_offset, float[] f, int _f_offset, int ldf) {
    lapack.slaqps(m, n, offset, nb, kb, a, _a_offset, lda, jpvt, _jpvt_offset, tau, _tau_offset, vn1, _vn1_offset, vn2, _vn2_offset, auxv, _auxv_offset, f, _f_offset, ldf);
  }

  protected void slaqr0K(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int _h_offset, int ldh, float[] wr, int _wr_offset, float[] wi, int _wi_offset, int iloz, int ihiz, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.slaqr0(wantt, wantz, n, ilo, ihi, h, _h_offset, ldh, wr, _wr_offset, wi, _wi_offset, iloz, ihiz, z, _z_offset, ldz, work, _work_offset, lwork, info);
  }

  protected void slaqr1K(int n, float[] h, int _h_offset, int ldh, float sr1, float si1, float sr2, float si2, float[] v, int _v_offset) {
    lapack.slaqr1(n, h, _h_offset, ldh, sr1, si1, sr2, si2, v, _v_offset);
  }

  protected void slaqr2K(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, float[] h, int _h_offset, int ldh, int iloz, int ihiz, float[] z, int _z_offset, int ldz, org.netlib.util.intW ns, org.netlib.util.intW nd, float[] sr, int _sr_offset, float[] si, int _si_offset, float[] v, int _v_offset, int ldv, int nh, float[] t, int _t_offset, int ldt, int nv, float[] wv, int _wv_offset, int ldwv, float[] work, int _work_offset, int lwork) {
    lapack.slaqr2(wantt, wantz, n, ktop, kbot, nw, h, _h_offset, ldh, iloz, ihiz, z, _z_offset, ldz, ns, nd, sr, _sr_offset, si, _si_offset, v, _v_offset, ldv, nh, t, _t_offset, ldt, nv, wv, _wv_offset, ldwv, work, _work_offset, lwork);
  }

  protected void slaqr3K(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, float[] h, int _h_offset, int ldh, int iloz, int ihiz, float[] z, int _z_offset, int ldz, org.netlib.util.intW ns, org.netlib.util.intW nd, float[] sr, int _sr_offset, float[] si, int _si_offset, float[] v, int _v_offset, int ldv, int nh, float[] t, int _t_offset, int ldt, int nv, float[] wv, int _wv_offset, int ldwv, float[] work, int _work_offset, int lwork) {
    lapack.slaqr3(wantt, wantz, n, ktop, kbot, nw, h, _h_offset, ldh, iloz, ihiz, z, _z_offset, ldz, ns, nd, sr, _sr_offset, si, _si_offset, v, _v_offset, ldv, nh, t, _t_offset, ldt, nv, wv, _wv_offset, ldwv, work, _work_offset, lwork);
  }

  protected void slaqr4K(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int _h_offset, int ldh, float[] wr, int _wr_offset, float[] wi, int _wi_offset, int iloz, int ihiz, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.slaqr4(wantt, wantz, n, ilo, ihi, h, _h_offset, ldh, wr, _wr_offset, wi, _wi_offset, iloz, ihiz, z, _z_offset, ldz, work, _work_offset, lwork, info);
  }

  protected void slaqr5K(boolean wantt, boolean wantz, int kacc22, int n, int ktop, int kbot, int nshfts, float[] sr, int _sr_offset, float[] si, int _si_offset, float[] h, int _h_offset, int ldh, int iloz, int ihiz, float[] z, int _z_offset, int ldz, float[] v, int _v_offset, int ldv, float[] u, int _u_offset, int ldu, int nv, float[] wv, int _wv_offset, int ldwv, int nh, float[] wh, int _wh_offset, int ldwh) {
    lapack.slaqr5(wantt, wantz, kacc22, n, ktop, kbot, nshfts, sr, _sr_offset, si, _si_offset, h, _h_offset, ldh, iloz, ihiz, z, _z_offset, ldz, v, _v_offset, ldv, u, _u_offset, ldu, nv, wv, _wv_offset, ldwv, nh, wh, _wh_offset, ldwh);
  }

  protected void slaqsbK(String uplo, int n, int kd, float[] ab, int _ab_offset, int ldab, float[] s, int _s_offset, float scond, float amax, org.netlib.util.StringW equed) {
    lapack.slaqsb(uplo, n, kd, ab, _ab_offset, ldab, s, _s_offset, scond, amax, equed);
  }

  protected void slaqspK(String uplo, int n, float[] ap, int _ap_offset, float[] s, int _s_offset, float scond, float amax, org.netlib.util.StringW equed) {
    lapack.slaqsp(uplo, n, ap, _ap_offset, s, _s_offset, scond, amax, equed);
  }

  protected void slaqsyK(String uplo, int n, float[] a, int _a_offset, int lda, float[] s, int _s_offset, float scond, float amax, org.netlib.util.StringW equed) {
    lapack.slaqsy(uplo, n, a, _a_offset, lda, s, _s_offset, scond, amax, equed);
  }

  protected void slaqtrK(boolean ltran, boolean lreal, int n, float[] t, int _t_offset, int ldt, float[] b, int _b_offset, float w, org.netlib.util.floatW scale, float[] x, int _x_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.slaqtr(ltran, lreal, n, t, _t_offset, ldt, b, _b_offset, w, scale, x, _x_offset, work, _work_offset, info);
  }

  protected void slar1vK(int n, int b1, int bn, float lambda, float[] d, int _d_offset, float[] l, int _l_offset, float[] ld, int _ld_offset, float[] lld, int _lld_offset, float pivmin, float gaptol, float[] z, int _z_offset, boolean wantnc, org.netlib.util.intW negcnt, org.netlib.util.floatW ztz, org.netlib.util.floatW mingma, org.netlib.util.intW r, int[] isuppz, int _isuppz_offset, org.netlib.util.floatW nrminv, org.netlib.util.floatW resid, org.netlib.util.floatW rqcorr, float[] work, int _work_offset) {
    lapack.slar1v(n, b1, bn, lambda, d, _d_offset, l, _l_offset, ld, _ld_offset, lld, _lld_offset, pivmin, gaptol, z, _z_offset, wantnc, negcnt, ztz, mingma, r, isuppz, _isuppz_offset, nrminv, resid, rqcorr, work, _work_offset);
  }

  protected void slar2vK(int n, float[] x, int _x_offset, float[] y, int _y_offset, float[] z, int _z_offset, int incx, float[] c, int _c_offset, float[] s, int _s_offset, int incc) {
    lapack.slar2v(n, x, _x_offset, y, _y_offset, z, _z_offset, incx, c, _c_offset, s, _s_offset, incc);
  }

  protected void slarfK(String side, int m, int n, float[] v, int _v_offset, int incv, float tau, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset) {
    lapack.slarf(side, m, n, v, _v_offset, incv, tau, c, _c_offset, Ldc, work, _work_offset);
  }

  protected void slarfbK(String side, String trans, String direct, String storev, int m, int n, int k, float[] v, int _v_offset, int ldv, float[] t, int _t_offset, int ldt, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, int ldwork) {
    lapack.slarfb(side, trans, direct, storev, m, n, k, v, _v_offset, ldv, t, _t_offset, ldt, c, _c_offset, Ldc, work, _work_offset, ldwork);
  }

  protected void slarfgK(int n, org.netlib.util.floatW alpha, float[] x, int _x_offset, int incx, org.netlib.util.floatW tau) {
    lapack.slarfg(n, alpha, x, _x_offset, incx, tau);
  }

  protected void slarftK(String direct, String storev, int n, int k, float[] v, int _v_offset, int ldv, float[] tau, int _tau_offset, float[] t, int _t_offset, int ldt) {
    lapack.slarft(direct, storev, n, k, v, _v_offset, ldv, tau, _tau_offset, t, _t_offset, ldt);
  }

  protected void slarfxK(String side, int m, int n, float[] v, int _v_offset, float tau, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset) {
    lapack.slarfx(side, m, n, v, _v_offset, tau, c, _c_offset, Ldc, work, _work_offset);
  }

  protected void slargvK(int n, float[] x, int _x_offset, int incx, float[] y, int _y_offset, int incy, float[] c, int _c_offset, int incc) {
    lapack.slargv(n, x, _x_offset, incx, y, _y_offset, incy, c, _c_offset, incc);
  }

  protected void slarnvK(int idist, int[] iseed, int _iseed_offset, int n, float[] x, int _x_offset) {
    lapack.slarnv(idist, iseed, _iseed_offset, n, x, _x_offset);
  }

  protected void slarraK(int n, float[] d, int _d_offset, float[] e, int _e_offset, float[] e2, int _e2_offset, float spltol, float tnrm, org.netlib.util.intW nsplit, int[] isplit, int _isplit_offset, org.netlib.util.intW info) {
    lapack.slarra(n, d, _d_offset, e, _e_offset, e2, _e2_offset, spltol, tnrm, nsplit, isplit, _isplit_offset, info);
  }

  protected void slarrbK(int n, float[] d, int _d_offset, float[] lld, int _lld_offset, int ifirst, int ilast, float rtol1, float rtol2, int offset, float[] w, int _w_offset, float[] wgap, int _wgap_offset, float[] werr, int _werr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, float pivmin, float spdiam, int twist, org.netlib.util.intW info) {
    lapack.slarrb(n, d, _d_offset, lld, _lld_offset, ifirst, ilast, rtol1, rtol2, offset, w, _w_offset, wgap, _wgap_offset, werr, _werr_offset, work, _work_offset, iwork, _iwork_offset, pivmin, spdiam, twist, info);
  }

  protected void slarrcK(String jobt, int n, float vl, float vu, float[] d, int _d_offset, float[] e, int _e_offset, float pivmin, org.netlib.util.intW eigcnt, org.netlib.util.intW lcnt, org.netlib.util.intW rcnt, org.netlib.util.intW info) {
    lapack.slarrc(jobt, n, vl, vu, d, _d_offset, e, _e_offset, pivmin, eigcnt, lcnt, rcnt, info);
  }

  protected void slarrdK(String range, String order, int n, float vl, float vu, int il, int iu, float[] gers, int _gers_offset, float reltol, float[] d, int _d_offset, float[] e, int _e_offset, float[] e2, int _e2_offset, float pivmin, int nsplit, int[] isplit, int _isplit_offset, org.netlib.util.intW m, float[] w, int _w_offset, float[] werr, int _werr_offset, org.netlib.util.floatW wl, org.netlib.util.floatW wu, int[] iblock, int _iblock_offset, int[] indexw, int _indexw_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.slarrd(range, order, n, vl, vu, il, iu, gers, _gers_offset, reltol, d, _d_offset, e, _e_offset, e2, _e2_offset, pivmin, nsplit, isplit, _isplit_offset, m, w, _w_offset, werr, _werr_offset, wl, wu, iblock, _iblock_offset, indexw, _indexw_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void slarreK(String range, int n, org.netlib.util.floatW vl, org.netlib.util.floatW vu, int il, int iu, float[] d, int _d_offset, float[] e, int _e_offset, float[] e2, int _e2_offset, float rtol1, float rtol2, float spltol, org.netlib.util.intW nsplit, int[] isplit, int _isplit_offset, org.netlib.util.intW m, float[] w, int _w_offset, float[] werr, int _werr_offset, float[] wgap, int _wgap_offset, int[] iblock, int _iblock_offset, int[] indexw, int _indexw_offset, float[] gers, int _gers_offset, org.netlib.util.floatW pivmin, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.slarre(range, n, vl, vu, il, iu, d, _d_offset, e, _e_offset, e2, _e2_offset, rtol1, rtol2, spltol, nsplit, isplit, _isplit_offset, m, w, _w_offset, werr, _werr_offset, wgap, _wgap_offset, iblock, _iblock_offset, indexw, _indexw_offset, gers, _gers_offset, pivmin, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void slarrfK(int n, float[] d, int _d_offset, float[] l, int _l_offset, float[] ld, int _ld_offset, int clstrt, int clend, float[] w, int _w_offset, float[] wgap, int _wgap_offset, float[] werr, int _werr_offset, float spdiam, float clgapl, float clgapr, float pivmin, org.netlib.util.floatW sigma, float[] dplus, int _dplus_offset, float[] lplus, int _lplus_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.slarrf(n, d, _d_offset, l, _l_offset, ld, _ld_offset, clstrt, clend, w, _w_offset, wgap, _wgap_offset, werr, _werr_offset, spdiam, clgapl, clgapr, pivmin, sigma, dplus, _dplus_offset, lplus, _lplus_offset, work, _work_offset, info);
  }

  protected void slarrjK(int n, float[] d, int _d_offset, float[] e2, int _e2_offset, int ifirst, int ilast, float rtol, int offset, float[] w, int _w_offset, float[] werr, int _werr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, float pivmin, float spdiam, org.netlib.util.intW info) {
    lapack.slarrj(n, d, _d_offset, e2, _e2_offset, ifirst, ilast, rtol, offset, w, _w_offset, werr, _werr_offset, work, _work_offset, iwork, _iwork_offset, pivmin, spdiam, info);
  }

  protected void slarrkK(int n, int iw, float gl, float gu, float[] d, int _d_offset, float[] e2, int _e2_offset, float pivmin, float reltol, org.netlib.util.floatW w, org.netlib.util.floatW werr, org.netlib.util.intW info) {
    lapack.slarrk(n, iw, gl, gu, d, _d_offset, e2, _e2_offset, pivmin, reltol, w, werr, info);
  }

  protected void slarrrK(int n, float[] d, int _d_offset, float[] e, int _e_offset, org.netlib.util.intW info) {
    lapack.slarrr(n, d, _d_offset, e, _e_offset, info);
  }

  protected void slarrvK(int n, float vl, float vu, float[] d, int _d_offset, float[] l, int _l_offset, float pivmin, int[] isplit, int _isplit_offset, int m, int dol, int dou, float minrgp, org.netlib.util.floatW rtol1, org.netlib.util.floatW rtol2, float[] w, int _w_offset, float[] werr, int _werr_offset, float[] wgap, int _wgap_offset, int[] iblock, int _iblock_offset, int[] indexw, int _indexw_offset, float[] gers, int _gers_offset, float[] z, int _z_offset, int ldz, int[] isuppz, int _isuppz_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.slarrv(n, vl, vu, d, _d_offset, l, _l_offset, pivmin, isplit, _isplit_offset, m, dol, dou, minrgp, rtol1, rtol2, w, _w_offset, werr, _werr_offset, wgap, _wgap_offset, iblock, _iblock_offset, indexw, _indexw_offset, gers, _gers_offset, z, _z_offset, ldz, isuppz, _isuppz_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void slartgK(float f, float g, org.netlib.util.floatW cs, org.netlib.util.floatW sn, org.netlib.util.floatW r) {
    lapack.slartg(f, g, cs, sn, r);
  }

  protected void slartvK(int n, float[] x, int _x_offset, int incx, float[] y, int _y_offset, int incy, float[] c, int _c_offset, float[] s, int _s_offset, int incc) {
    lapack.slartv(n, x, _x_offset, incx, y, _y_offset, incy, c, _c_offset, s, _s_offset, incc);
  }

  protected void slaruvK(int[] iseed, int _iseed_offset, int n, float[] x, int _x_offset) {
    lapack.slaruv(iseed, _iseed_offset, n, x, _x_offset);
  }

  protected void slarzK(String side, int m, int n, int l, float[] v, int _v_offset, int incv, float tau, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset) {
    lapack.slarz(side, m, n, l, v, _v_offset, incv, tau, c, _c_offset, Ldc, work, _work_offset);
  }

  protected void slarzbK(String side, String trans, String direct, String storev, int m, int n, int k, int l, float[] v, int _v_offset, int ldv, float[] t, int _t_offset, int ldt, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, int ldwork) {
    lapack.slarzb(side, trans, direct, storev, m, n, k, l, v, _v_offset, ldv, t, _t_offset, ldt, c, _c_offset, Ldc, work, _work_offset, ldwork);
  }

  protected void slarztK(String direct, String storev, int n, int k, float[] v, int _v_offset, int ldv, float[] tau, int _tau_offset, float[] t, int _t_offset, int ldt) {
    lapack.slarzt(direct, storev, n, k, v, _v_offset, ldv, tau, _tau_offset, t, _t_offset, ldt);
  }

  protected void slas2K(float f, float g, float h, org.netlib.util.floatW ssmin, org.netlib.util.floatW ssmax) {
    lapack.slas2(f, g, h, ssmin, ssmax);
  }

  protected void slasclK(String type, int kl, int ku, float cfrom, float cto, int m, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    lapack.slascl(type, kl, ku, cfrom, cto, m, n, a, _a_offset, lda, info);
  }

  protected void slasd0K(int n, int sqre, float[] d, int _d_offset, float[] e, int _e_offset, float[] u, int _u_offset, int ldu, float[] vt, int _vt_offset, int ldvt, int smlsiz, int[] iwork, int _iwork_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.slasd0(n, sqre, d, _d_offset, e, _e_offset, u, _u_offset, ldu, vt, _vt_offset, ldvt, smlsiz, iwork, _iwork_offset, work, _work_offset, info);
  }

  protected void slasd1K(int nl, int nr, int sqre, float[] d, int _d_offset, org.netlib.util.floatW alpha, org.netlib.util.floatW beta, float[] u, int _u_offset, int ldu, float[] vt, int _vt_offset, int ldvt, int[] idxq, int _idxq_offset, int[] iwork, int _iwork_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.slasd1(nl, nr, sqre, d, _d_offset, alpha, beta, u, _u_offset, ldu, vt, _vt_offset, ldvt, idxq, _idxq_offset, iwork, _iwork_offset, work, _work_offset, info);
  }

  protected void slasd2K(int nl, int nr, int sqre, org.netlib.util.intW k, float[] d, int _d_offset, float[] z, int _z_offset, float alpha, float beta, float[] u, int _u_offset, int ldu, float[] vt, int _vt_offset, int ldvt, float[] dsigma, int _dsigma_offset, float[] u2, int _u2_offset, int ldu2, float[] vt2, int _vt2_offset, int ldvt2, int[] idxp, int _idxp_offset, int[] idx, int _idx_offset, int[] idxc, int _idxc_offset, int[] idxq, int _idxq_offset, int[] coltyp, int _coltyp_offset, org.netlib.util.intW info) {
    lapack.slasd2(nl, nr, sqre, k, d, _d_offset, z, _z_offset, alpha, beta, u, _u_offset, ldu, vt, _vt_offset, ldvt, dsigma, _dsigma_offset, u2, _u2_offset, ldu2, vt2, _vt2_offset, ldvt2, idxp, _idxp_offset, idx, _idx_offset, idxc, _idxc_offset, idxq, _idxq_offset, coltyp, _coltyp_offset, info);
  }

  protected void slasd3K(int nl, int nr, int sqre, int k, float[] d, int _d_offset, float[] q, int _q_offset, int ldq, float[] dsigma, int _dsigma_offset, float[] u, int _u_offset, int ldu, float[] u2, int _u2_offset, int ldu2, float[] vt, int _vt_offset, int ldvt, float[] vt2, int _vt2_offset, int ldvt2, int[] idxc, int _idxc_offset, int[] ctot, int _ctot_offset, float[] z, int _z_offset, org.netlib.util.intW info) {
    lapack.slasd3(nl, nr, sqre, k, d, _d_offset, q, _q_offset, ldq, dsigma, _dsigma_offset, u, _u_offset, ldu, u2, _u2_offset, ldu2, vt, _vt_offset, ldvt, vt2, _vt2_offset, ldvt2, idxc, _idxc_offset, ctot, _ctot_offset, z, _z_offset, info);
  }

  protected void slasd4K(int n, int i, float[] d, int _d_offset, float[] z, int _z_offset, float[] delta, int _delta_offset, float rho, org.netlib.util.floatW sigma, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.slasd4(n, i, d, _d_offset, z, _z_offset, delta, _delta_offset, rho, sigma, work, _work_offset, info);
  }

  protected void slasd5K(int i, float[] d, int _d_offset, float[] z, int _z_offset, float[] delta, int _delta_offset, float rho, org.netlib.util.floatW dsigma, float[] work, int _work_offset) {
    lapack.slasd5(i, d, _d_offset, z, _z_offset, delta, _delta_offset, rho, dsigma, work, _work_offset);
  }

  protected void slasd6K(int icompq, int nl, int nr, int sqre, float[] d, int _d_offset, float[] vf, int _vf_offset, float[] vl, int _vl_offset, org.netlib.util.floatW alpha, org.netlib.util.floatW beta, int[] idxq, int _idxq_offset, int[] perm, int _perm_offset, org.netlib.util.intW givptr, int[] givcol, int _givcol_offset, int ldgcol, float[] givnum, int _givnum_offset, int ldgnum, float[] poles, int _poles_offset, float[] difl, int _difl_offset, float[] difr, int _difr_offset, float[] z, int _z_offset, org.netlib.util.intW k, org.netlib.util.floatW c, org.netlib.util.floatW s, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.slasd6(icompq, nl, nr, sqre, d, _d_offset, vf, _vf_offset, vl, _vl_offset, alpha, beta, idxq, _idxq_offset, perm, _perm_offset, givptr, givcol, _givcol_offset, ldgcol, givnum, _givnum_offset, ldgnum, poles, _poles_offset, difl, _difl_offset, difr, _difr_offset, z, _z_offset, k, c, s, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void slasd7K(int icompq, int nl, int nr, int sqre, org.netlib.util.intW k, float[] d, int _d_offset, float[] z, int _z_offset, float[] zw, int _zw_offset, float[] vf, int _vf_offset, float[] vfw, int _vfw_offset, float[] vl, int _vl_offset, float[] vlw, int _vlw_offset, float alpha, float beta, float[] dsigma, int _dsigma_offset, int[] idx, int _idx_offset, int[] idxp, int _idxp_offset, int[] idxq, int _idxq_offset, int[] perm, int _perm_offset, org.netlib.util.intW givptr, int[] givcol, int _givcol_offset, int ldgcol, float[] givnum, int _givnum_offset, int ldgnum, org.netlib.util.floatW c, org.netlib.util.floatW s, org.netlib.util.intW info) {
    lapack.slasd7(icompq, nl, nr, sqre, k, d, _d_offset, z, _z_offset, zw, _zw_offset, vf, _vf_offset, vfw, _vfw_offset, vl, _vl_offset, vlw, _vlw_offset, alpha, beta, dsigma, _dsigma_offset, idx, _idx_offset, idxp, _idxp_offset, idxq, _idxq_offset, perm, _perm_offset, givptr, givcol, _givcol_offset, ldgcol, givnum, _givnum_offset, ldgnum, c, s, info);
  }

  protected void slasd8K(int icompq, int k, float[] d, int _d_offset, float[] z, int _z_offset, float[] vf, int _vf_offset, float[] vl, int _vl_offset, float[] difl, int _difl_offset, float[] difr, int _difr_offset, int lddifr, float[] dsigma, int _dsigma_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.slasd8(icompq, k, d, _d_offset, z, _z_offset, vf, _vf_offset, vl, _vl_offset, difl, _difl_offset, difr, _difr_offset, lddifr, dsigma, _dsigma_offset, work, _work_offset, info);
  }

  protected void slasdaK(int icompq, int smlsiz, int n, int sqre, float[] d, int _d_offset, float[] e, int _e_offset, float[] u, int _u_offset, int ldu, float[] vt, int _vt_offset, int[] k, int _k_offset, float[] difl, int _difl_offset, float[] difr, int _difr_offset, float[] z, int _z_offset, float[] poles, int _poles_offset, int[] givptr, int _givptr_offset, int[] givcol, int _givcol_offset, int ldgcol, int[] perm, int _perm_offset, float[] givnum, int _givnum_offset, float[] c, int _c_offset, float[] s, int _s_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.slasda(icompq, smlsiz, n, sqre, d, _d_offset, e, _e_offset, u, _u_offset, ldu, vt, _vt_offset, k, _k_offset, difl, _difl_offset, difr, _difr_offset, z, _z_offset, poles, _poles_offset, givptr, _givptr_offset, givcol, _givcol_offset, ldgcol, perm, _perm_offset, givnum, _givnum_offset, c, _c_offset, s, _s_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void slasdqK(String uplo, int sqre, int n, int ncvt, int nru, int ncc, float[] d, int _d_offset, float[] e, int _e_offset, float[] vt, int _vt_offset, int ldvt, float[] u, int _u_offset, int ldu, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.slasdq(uplo, sqre, n, ncvt, nru, ncc, d, _d_offset, e, _e_offset, vt, _vt_offset, ldvt, u, _u_offset, ldu, c, _c_offset, Ldc, work, _work_offset, info);
  }

  protected void slasdtK(int n, org.netlib.util.intW lvl, org.netlib.util.intW nd, int[] inode, int _inode_offset, int[] ndiml, int _ndiml_offset, int[] ndimr, int _ndimr_offset, int msub) {
    lapack.slasdt(n, lvl, nd, inode, _inode_offset, ndiml, _ndiml_offset, ndimr, _ndimr_offset, msub);
  }

  protected void slasetK(String uplo, int m, int n, float alpha, float beta, float[] a, int _a_offset, int lda) {
    lapack.slaset(uplo, m, n, alpha, beta, a, _a_offset, lda);
  }

  protected void slasq1K(int n, float[] d, int _d_offset, float[] e, int _e_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.slasq1(n, d, _d_offset, e, _e_offset, work, _work_offset, info);
  }

  protected void slasq2K(int n, float[] z, int _z_offset, org.netlib.util.intW info) {
    lapack.slasq2(n, z, _z_offset, info);
  }

  protected void slasq3K(int i0, org.netlib.util.intW n0, float[] z, int _z_offset, int pp, org.netlib.util.floatW dmin, org.netlib.util.floatW sigma, org.netlib.util.floatW desig, org.netlib.util.floatW qmax, org.netlib.util.intW nfail, org.netlib.util.intW iter, org.netlib.util.intW ndiv, boolean ieee) {
    lapack.slasq3(i0, n0, z, _z_offset, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee);
  }

  protected void slasq4K(int i0, int n0, float[] z, int _z_offset, int pp, int n0in, float dmin, float dmin1, float dmin2, float dn, float dn1, float dn2, org.netlib.util.floatW tau, org.netlib.util.intW ttype) {
    lapack.slasq4(i0, n0, z, _z_offset, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype);
  }

  protected void slasq5K(int i0, int n0, float[] z, int _z_offset, int pp, float tau, org.netlib.util.floatW dmin, org.netlib.util.floatW dmin1, org.netlib.util.floatW dmin2, org.netlib.util.floatW dn, org.netlib.util.floatW dnm1, org.netlib.util.floatW dnm2, boolean ieee) {
    lapack.slasq5(i0, n0, z, _z_offset, pp, tau, dmin, dmin1, dmin2, dn, dnm1, dnm2, ieee);
  }

  protected void slasq6K(int i0, int n0, float[] z, int _z_offset, int pp, org.netlib.util.floatW dmin, org.netlib.util.floatW dmin1, org.netlib.util.floatW dmin2, org.netlib.util.floatW dn, org.netlib.util.floatW dnm1, org.netlib.util.floatW dnm2) {
    lapack.slasq6(i0, n0, z, _z_offset, pp, dmin, dmin1, dmin2, dn, dnm1, dnm2);
  }

  protected void slasrK(String side, String pivot, String direct, int m, int n, float[] c, int _c_offset, float[] s, int _s_offset, float[] a, int _a_offset, int lda) {
    lapack.slasr(side, pivot, direct, m, n, c, _c_offset, s, _s_offset, a, _a_offset, lda);
  }

  protected void slasrtK(String id, int n, float[] d, int _d_offset, org.netlib.util.intW info) {
    lapack.slasrt(id, n, d, _d_offset, info);
  }

  protected void slassqK(int n, float[] x, int _x_offset, int incx, org.netlib.util.floatW scale, org.netlib.util.floatW sumsq) {
    lapack.slassq(n, x, _x_offset, incx, scale, sumsq);
  }

  protected void slasv2K(float f, float g, float h, org.netlib.util.floatW ssmin, org.netlib.util.floatW ssmax, org.netlib.util.floatW snr, org.netlib.util.floatW csr, org.netlib.util.floatW snl, org.netlib.util.floatW csl) {
    lapack.slasv2(f, g, h, ssmin, ssmax, snr, csr, snl, csl);
  }

  protected void slaswpK(int n, float[] a, int _a_offset, int lda, int k1, int k2, int[] ipiv, int _ipiv_offset, int incx) {
    lapack.slaswp(n, a, _a_offset, lda, k1, k2, ipiv, _ipiv_offset, incx);
  }

  protected void slasy2K(boolean ltranl, boolean ltranr, int isgn, int n1, int n2, float[] tl, int _tl_offset, int ldtl, float[] tr, int _tr_offset, int ldtr, float[] b, int _b_offset, int ldb, org.netlib.util.floatW scale, float[] x, int _x_offset, int ldx, org.netlib.util.floatW xnorm, org.netlib.util.intW info) {
    lapack.slasy2(ltranl, ltranr, isgn, n1, n2, tl, _tl_offset, ldtl, tr, _tr_offset, ldtr, b, _b_offset, ldb, scale, x, _x_offset, ldx, xnorm, info);
  }

  protected void slasyfK(String uplo, int n, int nb, org.netlib.util.intW kb, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, float[] w, int _w_offset, int ldw, org.netlib.util.intW info) {
    lapack.slasyf(uplo, n, nb, kb, a, _a_offset, lda, ipiv, _ipiv_offset, w, _w_offset, ldw, info);
  }

  protected void slatbsK(String uplo, String trans, String diag, String normin, int n, int kd, float[] ab, int _ab_offset, int ldab, float[] x, int _x_offset, org.netlib.util.floatW scale, float[] cnorm, int _cnorm_offset, org.netlib.util.intW info) {
    lapack.slatbs(uplo, trans, diag, normin, n, kd, ab, _ab_offset, ldab, x, _x_offset, scale, cnorm, _cnorm_offset, info);
  }

  protected void slatdfK(int ijob, int n, float[] z, int _z_offset, int ldz, float[] rhs, int _rhs_offset, org.netlib.util.floatW rdsum, org.netlib.util.floatW rdscal, int[] ipiv, int _ipiv_offset, int[] jpiv, int _jpiv_offset) {
    lapack.slatdf(ijob, n, z, _z_offset, ldz, rhs, _rhs_offset, rdsum, rdscal, ipiv, _ipiv_offset, jpiv, _jpiv_offset);
  }

  protected void slatpsK(String uplo, String trans, String diag, String normin, int n, float[] ap, int _ap_offset, float[] x, int _x_offset, org.netlib.util.floatW scale, float[] cnorm, int _cnorm_offset, org.netlib.util.intW info) {
    lapack.slatps(uplo, trans, diag, normin, n, ap, _ap_offset, x, _x_offset, scale, cnorm, _cnorm_offset, info);
  }

  protected void slatrdK(String uplo, int n, int nb, float[] a, int _a_offset, int lda, float[] e, int _e_offset, float[] tau, int _tau_offset, float[] w, int _w_offset, int ldw) {
    lapack.slatrd(uplo, n, nb, a, _a_offset, lda, e, _e_offset, tau, _tau_offset, w, _w_offset, ldw);
  }

  protected void slatrsK(String uplo, String trans, String diag, String normin, int n, float[] a, int _a_offset, int lda, float[] x, int _x_offset, org.netlib.util.floatW scale, float[] cnorm, int _cnorm_offset, org.netlib.util.intW info) {
    lapack.slatrs(uplo, trans, diag, normin, n, a, _a_offset, lda, x, _x_offset, scale, cnorm, _cnorm_offset, info);
  }

  protected void slatrzK(int m, int n, int l, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset) {
    lapack.slatrz(m, n, l, a, _a_offset, lda, tau, _tau_offset, work, _work_offset);
  }

  protected void slatzmK(String side, int m, int n, float[] v, int _v_offset, int incv, float tau, float[] c1, int _c1_offset, float[] c2, int _c2_offset, int Ldc, float[] work, int _work_offset) {
    lapack.slatzm(side, m, n, v, _v_offset, incv, tau, c1, _c1_offset, c2, _c2_offset, Ldc, work, _work_offset);
  }

  protected void slauu2K(String uplo, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    lapack.slauu2(uplo, n, a, _a_offset, lda, info);
  }

  protected void slauumK(String uplo, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    lapack.slauum(uplo, n, a, _a_offset, lda, info);
  }

  protected void slazq3K(int i0, org.netlib.util.intW n0, float[] z, int _z_offset, int pp, org.netlib.util.floatW dmin, org.netlib.util.floatW sigma, org.netlib.util.floatW desig, org.netlib.util.floatW qmax, org.netlib.util.intW nfail, org.netlib.util.intW iter, org.netlib.util.intW ndiv, boolean ieee, org.netlib.util.intW ttype, org.netlib.util.floatW dmin1, org.netlib.util.floatW dmin2, org.netlib.util.floatW dn, org.netlib.util.floatW dn1, org.netlib.util.floatW dn2, org.netlib.util.floatW tau) {
    lapack.slazq3(i0, n0, z, _z_offset, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee, ttype, dmin1, dmin2, dn, dn1, dn2, tau);
  }

  protected void slazq4K(int i0, int n0, float[] z, int _z_offset, int pp, int n0in, float dmin, float dmin1, float dmin2, float dn, float dn1, float dn2, org.netlib.util.floatW tau, org.netlib.util.intW ttype, org.netlib.util.floatW g) {
    lapack.slazq4(i0, n0, z, _z_offset, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype, g);
  }

  protected void sopgtrK(String uplo, int n, float[] ap, int _ap_offset, float[] tau, int _tau_offset, float[] q, int _q_offset, int ldq, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sopgtr(uplo, n, ap, _ap_offset, tau, _tau_offset, q, _q_offset, ldq, work, _work_offset, info);
  }

  protected void sopmtrK(String side, String uplo, String trans, int m, int n, float[] ap, int _ap_offset, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sopmtr(side, uplo, trans, m, n, ap, _ap_offset, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  protected void sorg2lK(int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sorg2l(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  protected void sorg2rK(int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sorg2r(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  protected void sorgbrK(String vect, int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sorgbr(vect, m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void sorghrK(int n, int ilo, int ihi, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sorghr(n, ilo, ihi, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void sorgl2K(int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sorgl2(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  protected void sorglqK(int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sorglq(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void sorgqlK(int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sorgql(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void sorgqrK(int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sorgqr(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void sorgr2K(int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sorgr2(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  protected void sorgrqK(int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sorgrq(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void sorgtrK(String uplo, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sorgtr(uplo, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void sorm2lK(String side, String trans, int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sorm2l(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  protected void sorm2rK(String side, String trans, int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sorm2r(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  protected void sormbrK(String vect, String side, String trans, int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sormbr(vect, side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  protected void sormhrK(String side, String trans, int m, int n, int ilo, int ihi, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sormhr(side, trans, m, n, ilo, ihi, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  protected void sorml2K(String side, String trans, int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sorml2(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  protected void sormlqK(String side, String trans, int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sormlq(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  protected void sormqlK(String side, String trans, int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sormql(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  protected void sormqrK(String side, String trans, int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sormqr(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  protected void sormr2K(String side, String trans, int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sormr2(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  protected void sormr3K(String side, String trans, int m, int n, int k, int l, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sormr3(side, trans, m, n, k, l, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  protected void sormrqK(String side, String trans, int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sormrq(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  protected void sormrzK(String side, String trans, int m, int n, int k, int l, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sormrz(side, trans, m, n, k, l, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  protected void sormtrK(String side, String uplo, String trans, int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.sormtr(side, uplo, trans, m, n, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  protected void spbconK(String uplo, int n, int kd, float[] ab, int _ab_offset, int ldab, float anorm, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.spbcon(uplo, n, kd, ab, _ab_offset, ldab, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void spbequK(String uplo, int n, int kd, float[] ab, int _ab_offset, int ldab, float[] s, int _s_offset, org.netlib.util.floatW scond, org.netlib.util.floatW amax, org.netlib.util.intW info) {
    lapack.spbequ(uplo, n, kd, ab, _ab_offset, ldab, s, _s_offset, scond, amax, info);
  }

  protected void spbrfsK(String uplo, int n, int kd, int nrhs, float[] ab, int _ab_offset, int ldab, float[] afb, int _afb_offset, int ldafb, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.spbrfs(uplo, n, kd, nrhs, ab, _ab_offset, ldab, afb, _afb_offset, ldafb, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void spbstfK(String uplo, int n, int kd, float[] ab, int _ab_offset, int ldab, org.netlib.util.intW info) {
    lapack.spbstf(uplo, n, kd, ab, _ab_offset, ldab, info);
  }

  protected void spbsvK(String uplo, int n, int kd, int nrhs, float[] ab, int _ab_offset, int ldab, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.spbsv(uplo, n, kd, nrhs, ab, _ab_offset, ldab, b, _b_offset, ldb, info);
  }

  protected void spbsvxK(String fact, String uplo, int n, int kd, int nrhs, float[] ab, int _ab_offset, int ldab, float[] afb, int _afb_offset, int ldafb, org.netlib.util.StringW equed, float[] s, int _s_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, org.netlib.util.floatW rcond, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.spbsvx(fact, uplo, n, kd, nrhs, ab, _ab_offset, ldab, afb, _afb_offset, ldafb, equed, s, _s_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void spbtf2K(String uplo, int n, int kd, float[] ab, int _ab_offset, int ldab, org.netlib.util.intW info) {
    lapack.spbtf2(uplo, n, kd, ab, _ab_offset, ldab, info);
  }

  protected void spbtrfK(String uplo, int n, int kd, float[] ab, int _ab_offset, int ldab, org.netlib.util.intW info) {
    lapack.spbtrf(uplo, n, kd, ab, _ab_offset, ldab, info);
  }

  protected void spbtrsK(String uplo, int n, int kd, int nrhs, float[] ab, int _ab_offset, int ldab, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.spbtrs(uplo, n, kd, nrhs, ab, _ab_offset, ldab, b, _b_offset, ldb, info);
  }

  protected void spoconK(String uplo, int n, float[] a, int _a_offset, int lda, float anorm, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.spocon(uplo, n, a, _a_offset, lda, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void spoequK(int n, float[] a, int _a_offset, int lda, float[] s, int _s_offset, org.netlib.util.floatW scond, org.netlib.util.floatW amax, org.netlib.util.intW info) {
    lapack.spoequ(n, a, _a_offset, lda, s, _s_offset, scond, amax, info);
  }

  protected void sporfsK(String uplo, int n, int nrhs, float[] a, int _a_offset, int lda, float[] af, int _af_offset, int ldaf, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sporfs(uplo, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void sposvK(String uplo, int n, int nrhs, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.sposv(uplo, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, info);
  }

  protected void sposvxK(String fact, String uplo, int n, int nrhs, float[] a, int _a_offset, int lda, float[] af, int _af_offset, int ldaf, org.netlib.util.StringW equed, float[] s, int _s_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, org.netlib.util.floatW rcond, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sposvx(fact, uplo, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, equed, s, _s_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void spotf2K(String uplo, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    lapack.spotf2(uplo, n, a, _a_offset, lda, info);
  }

  protected void spotrfK(String uplo, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    lapack.spotrf(uplo, n, a, _a_offset, lda, info);
  }

  protected void spotriK(String uplo, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    lapack.spotri(uplo, n, a, _a_offset, lda, info);
  }

  protected void spotrsK(String uplo, int n, int nrhs, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.spotrs(uplo, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, info);
  }

  protected void sppconK(String uplo, int n, float[] ap, int _ap_offset, float anorm, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sppcon(uplo, n, ap, _ap_offset, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void sppequK(String uplo, int n, float[] ap, int _ap_offset, float[] s, int _s_offset, org.netlib.util.floatW scond, org.netlib.util.floatW amax, org.netlib.util.intW info) {
    lapack.sppequ(uplo, n, ap, _ap_offset, s, _s_offset, scond, amax, info);
  }

  protected void spprfsK(String uplo, int n, int nrhs, float[] ap, int _ap_offset, float[] afp, int _afp_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.spprfs(uplo, n, nrhs, ap, _ap_offset, afp, _afp_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void sppsvK(String uplo, int n, int nrhs, float[] ap, int _ap_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.sppsv(uplo, n, nrhs, ap, _ap_offset, b, _b_offset, ldb, info);
  }

  protected void sppsvxK(String fact, String uplo, int n, int nrhs, float[] ap, int _ap_offset, float[] afp, int _afp_offset, org.netlib.util.StringW equed, float[] s, int _s_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, org.netlib.util.floatW rcond, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sppsvx(fact, uplo, n, nrhs, ap, _ap_offset, afp, _afp_offset, equed, s, _s_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void spptrfK(String uplo, int n, float[] ap, int _ap_offset, org.netlib.util.intW info) {
    lapack.spptrf(uplo, n, ap, _ap_offset, info);
  }

  protected void spptriK(String uplo, int n, float[] ap, int _ap_offset, org.netlib.util.intW info) {
    lapack.spptri(uplo, n, ap, _ap_offset, info);
  }

  protected void spptrsK(String uplo, int n, int nrhs, float[] ap, int _ap_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.spptrs(uplo, n, nrhs, ap, _ap_offset, b, _b_offset, ldb, info);
  }

  protected void sptconK(int n, float[] d, int _d_offset, float[] e, int _e_offset, float anorm, org.netlib.util.floatW rcond, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sptcon(n, d, _d_offset, e, _e_offset, anorm, rcond, work, _work_offset, info);
  }

  protected void spteqrK(String compz, int n, float[] d, int _d_offset, float[] e, int _e_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.spteqr(compz, n, d, _d_offset, e, _e_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  protected void sptrfsK(int n, int nrhs, float[] d, int _d_offset, float[] e, int _e_offset, float[] df, int _df_offset, float[] ef, int _ef_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sptrfs(n, nrhs, d, _d_offset, e, _e_offset, df, _df_offset, ef, _ef_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, info);
  }

  protected void sptsvK(int n, int nrhs, float[] d, int _d_offset, float[] e, int _e_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.sptsv(n, nrhs, d, _d_offset, e, _e_offset, b, _b_offset, ldb, info);
  }

  protected void sptsvxK(String fact, int n, int nrhs, float[] d, int _d_offset, float[] e, int _e_offset, float[] df, int _df_offset, float[] ef, int _ef_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, org.netlib.util.floatW rcond, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sptsvx(fact, n, nrhs, d, _d_offset, e, _e_offset, df, _df_offset, ef, _ef_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, info);
  }

  protected void spttrfK(int n, float[] d, int _d_offset, float[] e, int _e_offset, org.netlib.util.intW info) {
    lapack.spttrf(n, d, _d_offset, e, _e_offset, info);
  }

  protected void spttrsK(int n, int nrhs, float[] d, int _d_offset, float[] e, int _e_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.spttrs(n, nrhs, d, _d_offset, e, _e_offset, b, _b_offset, ldb, info);
  }

  protected void sptts2K(int n, int nrhs, float[] d, int _d_offset, float[] e, int _e_offset, float[] b, int _b_offset, int ldb) {
    lapack.sptts2(n, nrhs, d, _d_offset, e, _e_offset, b, _b_offset, ldb);
  }

  protected void srsclK(int n, float sa, float[] sx, int _sx_offset, int incx) {
    lapack.srscl(n, sa, sx, _sx_offset, incx);
  }

  protected void ssbevK(String jobz, String uplo, int n, int kd, float[] ab, int _ab_offset, int ldab, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.ssbev(jobz, uplo, n, kd, ab, _ab_offset, ldab, w, _w_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  protected void ssbevdK(String jobz, String uplo, int n, int kd, float[] ab, int _ab_offset, int ldab, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.ssbevd(jobz, uplo, n, kd, ab, _ab_offset, ldab, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void ssbevxK(String jobz, String range, String uplo, int n, int kd, float[] ab, int _ab_offset, int ldab, float[] q, int _q_offset, int ldq, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    lapack.ssbevx(jobz, range, uplo, n, kd, ab, _ab_offset, ldab, q, _q_offset, ldq, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  protected void ssbgstK(String vect, String uplo, int n, int ka, int kb, float[] ab, int _ab_offset, int ldab, float[] bb, int _bb_offset, int ldbb, float[] x, int _x_offset, int ldx, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.ssbgst(vect, uplo, n, ka, kb, ab, _ab_offset, ldab, bb, _bb_offset, ldbb, x, _x_offset, ldx, work, _work_offset, info);
  }

  protected void ssbgvK(String jobz, String uplo, int n, int ka, int kb, float[] ab, int _ab_offset, int ldab, float[] bb, int _bb_offset, int ldbb, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.ssbgv(jobz, uplo, n, ka, kb, ab, _ab_offset, ldab, bb, _bb_offset, ldbb, w, _w_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  protected void ssbgvdK(String jobz, String uplo, int n, int ka, int kb, float[] ab, int _ab_offset, int ldab, float[] bb, int _bb_offset, int ldbb, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.ssbgvd(jobz, uplo, n, ka, kb, ab, _ab_offset, ldab, bb, _bb_offset, ldbb, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void ssbgvxK(String jobz, String range, String uplo, int n, int ka, int kb, float[] ab, int _ab_offset, int ldab, float[] bb, int _bb_offset, int ldbb, float[] q, int _q_offset, int ldq, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    lapack.ssbgvx(jobz, range, uplo, n, ka, kb, ab, _ab_offset, ldab, bb, _bb_offset, ldbb, q, _q_offset, ldq, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  protected void ssbtrdK(String vect, String uplo, int n, int kd, float[] ab, int _ab_offset, int ldab, float[] d, int _d_offset, float[] e, int _e_offset, float[] q, int _q_offset, int ldq, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.ssbtrd(vect, uplo, n, kd, ab, _ab_offset, ldab, d, _d_offset, e, _e_offset, q, _q_offset, ldq, work, _work_offset, info);
  }

  protected void sspconK(String uplo, int n, float[] ap, int _ap_offset, int[] ipiv, int _ipiv_offset, float anorm, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sspcon(uplo, n, ap, _ap_offset, ipiv, _ipiv_offset, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void sspevK(String jobz, String uplo, int n, float[] ap, int _ap_offset, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sspev(jobz, uplo, n, ap, _ap_offset, w, _w_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  protected void sspevdK(String jobz, String uplo, int n, float[] ap, int _ap_offset, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.sspevd(jobz, uplo, n, ap, _ap_offset, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void sspevxK(String jobz, String range, String uplo, int n, float[] ap, int _ap_offset, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    lapack.sspevx(jobz, range, uplo, n, ap, _ap_offset, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  protected void sspgstK(int itype, String uplo, int n, float[] ap, int _ap_offset, float[] bp, int _bp_offset, org.netlib.util.intW info) {
    lapack.sspgst(itype, uplo, n, ap, _ap_offset, bp, _bp_offset, info);
  }

  protected void sspgvK(int itype, String jobz, String uplo, int n, float[] ap, int _ap_offset, float[] bp, int _bp_offset, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sspgv(itype, jobz, uplo, n, ap, _ap_offset, bp, _bp_offset, w, _w_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  protected void sspgvdK(int itype, String jobz, String uplo, int n, float[] ap, int _ap_offset, float[] bp, int _bp_offset, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.sspgvd(itype, jobz, uplo, n, ap, _ap_offset, bp, _bp_offset, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void sspgvxK(int itype, String jobz, String range, String uplo, int n, float[] ap, int _ap_offset, float[] bp, int _bp_offset, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    lapack.sspgvx(itype, jobz, range, uplo, n, ap, _ap_offset, bp, _bp_offset, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  protected void ssprfsK(String uplo, int n, int nrhs, float[] ap, int _ap_offset, float[] afp, int _afp_offset, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.ssprfs(uplo, n, nrhs, ap, _ap_offset, afp, _afp_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void sspsvK(String uplo, int n, int nrhs, float[] ap, int _ap_offset, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.sspsv(uplo, n, nrhs, ap, _ap_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  protected void sspsvxK(String fact, String uplo, int n, int nrhs, float[] ap, int _ap_offset, float[] afp, int _afp_offset, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, org.netlib.util.floatW rcond, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sspsvx(fact, uplo, n, nrhs, ap, _ap_offset, afp, _afp_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void ssptrdK(String uplo, int n, float[] ap, int _ap_offset, float[] d, int _d_offset, float[] e, int _e_offset, float[] tau, int _tau_offset, org.netlib.util.intW info) {
    lapack.ssptrd(uplo, n, ap, _ap_offset, d, _d_offset, e, _e_offset, tau, _tau_offset, info);
  }

  protected void ssptrfK(String uplo, int n, float[] ap, int _ap_offset, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    lapack.ssptrf(uplo, n, ap, _ap_offset, ipiv, _ipiv_offset, info);
  }

  protected void ssptriK(String uplo, int n, float[] ap, int _ap_offset, int[] ipiv, int _ipiv_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.ssptri(uplo, n, ap, _ap_offset, ipiv, _ipiv_offset, work, _work_offset, info);
  }

  protected void ssptrsK(String uplo, int n, int nrhs, float[] ap, int _ap_offset, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.ssptrs(uplo, n, nrhs, ap, _ap_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  protected void sstebzK(String range, String order, int n, float vl, float vu, int il, int iu, float abstol, float[] d, int _d_offset, float[] e, int _e_offset, org.netlib.util.intW m, org.netlib.util.intW nsplit, float[] w, int _w_offset, int[] iblock, int _iblock_offset, int[] isplit, int _isplit_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.sstebz(range, order, n, vl, vu, il, iu, abstol, d, _d_offset, e, _e_offset, m, nsplit, w, _w_offset, iblock, _iblock_offset, isplit, _isplit_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void sstedcK(String compz, int n, float[] d, int _d_offset, float[] e, int _e_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.sstedc(compz, n, d, _d_offset, e, _e_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void sstegrK(String jobz, String range, int n, float[] d, int _d_offset, float[] e, int _e_offset, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, int[] isuppz, int _isuppz_offset, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.sstegr(jobz, range, n, d, _d_offset, e, _e_offset, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, isuppz, _isuppz_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void ssteinK(int n, float[] d, int _d_offset, float[] e, int _e_offset, int m, float[] w, int _w_offset, int[] iblock, int _iblock_offset, int[] isplit, int _isplit_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    lapack.sstein(n, d, _d_offset, e, _e_offset, m, w, _w_offset, iblock, _iblock_offset, isplit, _isplit_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  protected void sstemrK(String jobz, String range, int n, float[] d, int _d_offset, float[] e, int _e_offset, float vl, float vu, int il, int iu, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, int nzc, int[] isuppz, int _isuppz_offset, org.netlib.util.booleanW tryrac, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.sstemr(jobz, range, n, d, _d_offset, e, _e_offset, vl, vu, il, iu, m, w, _w_offset, z, _z_offset, ldz, nzc, isuppz, _isuppz_offset, tryrac, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void ssteqrK(String compz, int n, float[] d, int _d_offset, float[] e, int _e_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.ssteqr(compz, n, d, _d_offset, e, _e_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  protected void ssterfK(int n, float[] d, int _d_offset, float[] e, int _e_offset, org.netlib.util.intW info) {
    lapack.ssterf(n, d, _d_offset, e, _e_offset, info);
  }

  protected void sstevK(String jobz, int n, float[] d, int _d_offset, float[] e, int _e_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.sstev(jobz, n, d, _d_offset, e, _e_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  protected void sstevdK(String jobz, int n, float[] d, int _d_offset, float[] e, int _e_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.sstevd(jobz, n, d, _d_offset, e, _e_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void sstevrK(String jobz, String range, int n, float[] d, int _d_offset, float[] e, int _e_offset, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, int[] isuppz, int _isuppz_offset, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.sstevr(jobz, range, n, d, _d_offset, e, _e_offset, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, isuppz, _isuppz_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void sstevxK(String jobz, String range, int n, float[] d, int _d_offset, float[] e, int _e_offset, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    lapack.sstevx(jobz, range, n, d, _d_offset, e, _e_offset, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  protected void ssyconK(String uplo, int n, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, float anorm, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.ssycon(uplo, n, a, _a_offset, lda, ipiv, _ipiv_offset, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void ssyevK(String jobz, String uplo, int n, float[] a, int _a_offset, int lda, float[] w, int _w_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.ssyev(jobz, uplo, n, a, _a_offset, lda, w, _w_offset, work, _work_offset, lwork, info);
  }

  protected void ssyevdK(String jobz, String uplo, int n, float[] a, int _a_offset, int lda, float[] w, int _w_offset, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.ssyevd(jobz, uplo, n, a, _a_offset, lda, w, _w_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void ssyevrK(String jobz, String range, String uplo, int n, float[] a, int _a_offset, int lda, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, int[] isuppz, int _isuppz_offset, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.ssyevr(jobz, range, uplo, n, a, _a_offset, lda, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, isuppz, _isuppz_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void ssyevxK(String jobz, String range, String uplo, int n, float[] a, int _a_offset, int lda, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    lapack.ssyevx(jobz, range, uplo, n, a, _a_offset, lda, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  protected void ssygs2K(int itype, String uplo, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.ssygs2(itype, uplo, n, a, _a_offset, lda, b, _b_offset, ldb, info);
  }

  protected void ssygstK(int itype, String uplo, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.ssygst(itype, uplo, n, a, _a_offset, lda, b, _b_offset, ldb, info);
  }

  protected void ssygvK(int itype, String jobz, String uplo, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] w, int _w_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.ssygv(itype, jobz, uplo, n, a, _a_offset, lda, b, _b_offset, ldb, w, _w_offset, work, _work_offset, lwork, info);
  }

  protected void ssygvdK(int itype, String jobz, String uplo, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] w, int _w_offset, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.ssygvd(itype, jobz, uplo, n, a, _a_offset, lda, b, _b_offset, ldb, w, _w_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void ssygvxK(int itype, String jobz, String range, String uplo, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    lapack.ssygvx(itype, jobz, range, uplo, n, a, _a_offset, lda, b, _b_offset, ldb, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  protected void ssyrfsK(String uplo, int n, int nrhs, float[] a, int _a_offset, int lda, float[] af, int _af_offset, int ldaf, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.ssyrfs(uplo, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void ssysvK(String uplo, int n, int nrhs, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.ssysv(uplo, n, nrhs, a, _a_offset, lda, ipiv, _ipiv_offset, b, _b_offset, ldb, work, _work_offset, lwork, info);
  }

  protected void ssysvxK(String fact, String uplo, int n, int nrhs, float[] a, int _a_offset, int lda, float[] af, int _af_offset, int ldaf, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, org.netlib.util.floatW rcond, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.ssysvx(fact, uplo, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  protected void ssytd2K(String uplo, int n, float[] a, int _a_offset, int lda, float[] d, int _d_offset, float[] e, int _e_offset, float[] tau, int _tau_offset, org.netlib.util.intW info) {
    lapack.ssytd2(uplo, n, a, _a_offset, lda, d, _d_offset, e, _e_offset, tau, _tau_offset, info);
  }

  protected void ssytf2K(String uplo, int n, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    lapack.ssytf2(uplo, n, a, _a_offset, lda, ipiv, _ipiv_offset, info);
  }

  protected void ssytrdK(String uplo, int n, float[] a, int _a_offset, int lda, float[] d, int _d_offset, float[] e, int _e_offset, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.ssytrd(uplo, n, a, _a_offset, lda, d, _d_offset, e, _e_offset, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected void ssytrfK(String uplo, int n, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.ssytrf(uplo, n, a, _a_offset, lda, ipiv, _ipiv_offset, work, _work_offset, lwork, info);
  }

  protected void ssytriK(String uplo, int n, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.ssytri(uplo, n, a, _a_offset, lda, ipiv, _ipiv_offset, work, _work_offset, info);
  }

  protected void ssytrsK(String uplo, int n, int nrhs, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.ssytrs(uplo, n, nrhs, a, _a_offset, lda, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  protected void stbconK(String norm, String uplo, String diag, int n, int kd, float[] ab, int _ab_offset, int ldab, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.stbcon(norm, uplo, diag, n, kd, ab, _ab_offset, ldab, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void stbrfsK(String uplo, String trans, String diag, int n, int kd, int nrhs, float[] ab, int _ab_offset, int ldab, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.stbrfs(uplo, trans, diag, n, kd, nrhs, ab, _ab_offset, ldab, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void stbtrsK(String uplo, String trans, String diag, int n, int kd, int nrhs, float[] ab, int _ab_offset, int ldab, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.stbtrs(uplo, trans, diag, n, kd, nrhs, ab, _ab_offset, ldab, b, _b_offset, ldb, info);
  }

  protected void stgevcK(String side, String howmny, boolean[] select, int _select_offset, int n, float[] s, int _s_offset, int lds, float[] p, int _p_offset, int ldp, float[] vl, int _vl_offset, int ldvl, float[] vr, int _vr_offset, int ldvr, int mm, org.netlib.util.intW m, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.stgevc(side, howmny, select, _select_offset, n, s, _s_offset, lds, p, _p_offset, ldp, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, mm, m, work, _work_offset, info);
  }

  protected void stgex2K(boolean wantq, boolean wantz, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] q, int _q_offset, int ldq, float[] z, int _z_offset, int ldz, int j1, int n1, int n2, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.stgex2(wantq, wantz, n, a, _a_offset, lda, b, _b_offset, ldb, q, _q_offset, ldq, z, _z_offset, ldz, j1, n1, n2, work, _work_offset, lwork, info);
  }

  protected void stgexcK(boolean wantq, boolean wantz, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] q, int _q_offset, int ldq, float[] z, int _z_offset, int ldz, org.netlib.util.intW ifst, org.netlib.util.intW ilst, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.stgexc(wantq, wantz, n, a, _a_offset, lda, b, _b_offset, ldb, q, _q_offset, ldq, z, _z_offset, ldz, ifst, ilst, work, _work_offset, lwork, info);
  }

  protected void stgsenK(int ijob, boolean wantq, boolean wantz, boolean[] select, int _select_offset, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] alphar, int _alphar_offset, float[] alphai, int _alphai_offset, float[] beta, int _beta_offset, float[] q, int _q_offset, int ldq, float[] z, int _z_offset, int ldz, org.netlib.util.intW m, org.netlib.util.floatW pl, org.netlib.util.floatW pr, float[] dif, int _dif_offset, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.stgsen(ijob, wantq, wantz, select, _select_offset, n, a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, q, _q_offset, ldq, z, _z_offset, ldz, m, pl, pr, dif, _dif_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void stgsjaK(String jobu, String jobv, String jobq, int m, int p, int n, int k, int l, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float tola, float tolb, float[] alpha, int _alpha_offset, float[] beta, int _beta_offset, float[] u, int _u_offset, int ldu, float[] v, int _v_offset, int ldv, float[] q, int _q_offset, int ldq, float[] work, int _work_offset, org.netlib.util.intW ncycle, org.netlib.util.intW info) {
    lapack.stgsja(jobu, jobv, jobq, m, p, n, k, l, a, _a_offset, lda, b, _b_offset, ldb, tola, tolb, alpha, _alpha_offset, beta, _beta_offset, u, _u_offset, ldu, v, _v_offset, ldv, q, _q_offset, ldq, work, _work_offset, ncycle, info);
  }

  protected void stgsnaK(String job, String howmny, boolean[] select, int _select_offset, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] vl, int _vl_offset, int ldvl, float[] vr, int _vr_offset, int ldvr, float[] s, int _s_offset, float[] dif, int _dif_offset, int mm, org.netlib.util.intW m, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.stgsna(job, howmny, select, _select_offset, n, a, _a_offset, lda, b, _b_offset, ldb, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, s, _s_offset, dif, _dif_offset, mm, m, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  protected void stgsy2K(String trans, int ijob, int m, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] c, int _c_offset, int Ldc, float[] d, int _d_offset, int ldd, float[] e, int _e_offset, int lde, float[] f, int _f_offset, int ldf, org.netlib.util.floatW scale, org.netlib.util.floatW rdsum, org.netlib.util.floatW rdscal, int[] iwork, int _iwork_offset, org.netlib.util.intW pq, org.netlib.util.intW info) {
    lapack.stgsy2(trans, ijob, m, n, a, _a_offset, lda, b, _b_offset, ldb, c, _c_offset, Ldc, d, _d_offset, ldd, e, _e_offset, lde, f, _f_offset, ldf, scale, rdsum, rdscal, iwork, _iwork_offset, pq, info);
  }

  protected void stgsylK(String trans, int ijob, int m, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] c, int _c_offset, int Ldc, float[] d, int _d_offset, int ldd, float[] e, int _e_offset, int lde, float[] f, int _f_offset, int ldf, org.netlib.util.floatW scale, org.netlib.util.floatW dif, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.stgsyl(trans, ijob, m, n, a, _a_offset, lda, b, _b_offset, ldb, c, _c_offset, Ldc, d, _d_offset, ldd, e, _e_offset, lde, f, _f_offset, ldf, scale, dif, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  protected void stpconK(String norm, String uplo, String diag, int n, float[] ap, int _ap_offset, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.stpcon(norm, uplo, diag, n, ap, _ap_offset, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void stprfsK(String uplo, String trans, String diag, int n, int nrhs, float[] ap, int _ap_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.stprfs(uplo, trans, diag, n, nrhs, ap, _ap_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void stptriK(String uplo, String diag, int n, float[] ap, int _ap_offset, org.netlib.util.intW info) {
    lapack.stptri(uplo, diag, n, ap, _ap_offset, info);
  }

  protected void stptrsK(String uplo, String trans, String diag, int n, int nrhs, float[] ap, int _ap_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.stptrs(uplo, trans, diag, n, nrhs, ap, _ap_offset, b, _b_offset, ldb, info);
  }

  protected void strconK(String norm, String uplo, String diag, int n, float[] a, int _a_offset, int lda, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.strcon(norm, uplo, diag, n, a, _a_offset, lda, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void strevcK(String side, String howmny, boolean[] select, int _select_offset, int n, float[] t, int _t_offset, int ldt, float[] vl, int _vl_offset, int ldvl, float[] vr, int _vr_offset, int ldvr, int mm, org.netlib.util.intW m, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.strevc(side, howmny, select, _select_offset, n, t, _t_offset, ldt, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, mm, m, work, _work_offset, info);
  }

  protected void strexcK(String compq, int n, float[] t, int _t_offset, int ldt, float[] q, int _q_offset, int ldq, org.netlib.util.intW ifst, org.netlib.util.intW ilst, float[] work, int _work_offset, org.netlib.util.intW info) {
    lapack.strexc(compq, n, t, _t_offset, ldt, q, _q_offset, ldq, ifst, ilst, work, _work_offset, info);
  }

  protected void strrfsK(String uplo, String trans, String diag, int n, int nrhs, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.strrfs(uplo, trans, diag, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  protected void strsenK(String job, String compq, boolean[] select, int _select_offset, int n, float[] t, int _t_offset, int ldt, float[] q, int _q_offset, int ldq, float[] wr, int _wr_offset, float[] wi, int _wi_offset, org.netlib.util.intW m, org.netlib.util.floatW s, org.netlib.util.floatW sep, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    lapack.strsen(job, compq, select, _select_offset, n, t, _t_offset, ldt, q, _q_offset, ldq, wr, _wr_offset, wi, _wi_offset, m, s, sep, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  protected void strsnaK(String job, String howmny, boolean[] select, int _select_offset, int n, float[] t, int _t_offset, int ldt, float[] vl, int _vl_offset, int ldvl, float[] vr, int _vr_offset, int ldvr, float[] s, int _s_offset, float[] sep, int _sep_offset, int mm, org.netlib.util.intW m, float[] work, int _work_offset, int ldwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    lapack.strsna(job, howmny, select, _select_offset, n, t, _t_offset, ldt, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, s, _s_offset, sep, _sep_offset, mm, m, work, _work_offset, ldwork, iwork, _iwork_offset, info);
  }

  protected void strsylK(String trana, String tranb, int isgn, int m, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] c, int _c_offset, int Ldc, org.netlib.util.floatW scale, org.netlib.util.intW info) {
    lapack.strsyl(trana, tranb, isgn, m, n, a, _a_offset, lda, b, _b_offset, ldb, c, _c_offset, Ldc, scale, info);
  }

  protected void strti2K(String uplo, String diag, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    lapack.strti2(uplo, diag, n, a, _a_offset, lda, info);
  }

  protected void strtriK(String uplo, String diag, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    lapack.strtri(uplo, diag, n, a, _a_offset, lda, info);
  }

  protected void strtrsK(String uplo, String trans, String diag, int n, int nrhs, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    lapack.strtrs(uplo, trans, diag, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, info);
  }

  protected void stzrqfK(int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, org.netlib.util.intW info) {
    lapack.stzrqf(m, n, a, _a_offset, lda, tau, _tau_offset, info);
  }

  protected void stzrzfK(int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    lapack.stzrzf(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  protected double dlamchK(String cmach) {
    return lapack.dlamch(cmach);
  }

  protected void dlamc1K(org.netlib.util.intW beta, org.netlib.util.intW t, org.netlib.util.booleanW rnd, org.netlib.util.booleanW ieee1) {
    lapack.dlamc1(beta, t, rnd, ieee1);
  }

  protected void dlamc2K(org.netlib.util.intW beta, org.netlib.util.intW t, org.netlib.util.booleanW rnd, org.netlib.util.doubleW eps, org.netlib.util.intW emin, org.netlib.util.doubleW rmin, org.netlib.util.intW emax, org.netlib.util.doubleW rmax) {
    lapack.dlamc2(beta, t, rnd, eps, emin, rmin, emax, rmax);
  }

  protected double dlamc3K(double a, double b) {
    return lapack.dlamc3(a, b);
  }

  protected void dlamc4K(org.netlib.util.intW emin, double start, int base) {
    lapack.dlamc4(emin, start, base);
  }

  protected void dlamc5K(int beta, int p, int emin, boolean ieee, org.netlib.util.intW emax, org.netlib.util.doubleW rmax) {
    lapack.dlamc5(beta, p, emin, ieee, emax, rmax);
  }

  protected double dsecndK() {
    return lapack.dsecnd();
  }

  protected boolean lsameK(String ca, String cb) {
    return lapack.lsame(ca, cb);
  }

  protected float secondK() {
    return lapack.second();
  }

  protected float slamchK(String cmach) {
    return lapack.slamch(cmach);
  }

  protected void slamc1K(org.netlib.util.intW beta, org.netlib.util.intW t, org.netlib.util.booleanW rnd, org.netlib.util.booleanW ieee1) {
    lapack.slamc1(beta, t, rnd, ieee1);
  }

  protected void slamc2K(org.netlib.util.intW beta, org.netlib.util.intW t, org.netlib.util.booleanW rnd, org.netlib.util.floatW eps, org.netlib.util.intW emin, org.netlib.util.floatW rmin, org.netlib.util.intW emax, org.netlib.util.floatW rmax) {
    lapack.slamc2(beta, t, rnd, eps, emin, rmin, emax, rmax);
  }

  protected float slamc3K(float a, float b) {
    return lapack.slamc3(a, b);
  }

  protected void slamc4K(org.netlib.util.intW emin, float start, int base) {
    lapack.slamc4(emin, start, base);
  }

  protected void slamc5K(int beta, int p, int emin, boolean ieee, org.netlib.util.intW emax, org.netlib.util.floatW rmax) {
    lapack.slamc5(beta, p, emin, ieee, emax, rmax);
  }
}
