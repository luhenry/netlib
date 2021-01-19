/*
 * Copyright 2020, Ludovic Henry
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
 */

package dev.ludovic.netlib.lapack;

import dev.ludovic.netlib.LAPACK;

public class JavaLAPACK implements LAPACK {

  //FIXME: remove dependency on F2jLAPACK
  private static final com.github.fommil.netlib.F2jLAPACK f2j = new com.github.fommil.netlib.F2jLAPACK();

  private static final JavaLAPACK instance = new JavaLAPACK();

  protected JavaLAPACK() {}

  public static LAPACK getInstance() {
    return instance;
  }

  public void dbdsdc(String uplo, String compq, int n, double[] d, double[] e, double[] u, int ldu, double[] vt, int ldvt, double[] q, int[] iq, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dbdsdc(uplo, compq, n, d, 0, e, 0, u, 0, ldu, vt, 0, ldvt, q, 0, iq, 0, work, 0, iwork, 0, info);
  }

  public void dbdsdc(String uplo, String compq, int n, double[] d, int _d_offset, double[] e, int _e_offset, double[] u, int _u_offset, int ldu, double[] vt, int _vt_offset, int ldvt, double[] q, int _q_offset, int[] iq, int _iq_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dbdsdc(uplo, compq, n, d, _d_offset, e, _e_offset, u, _u_offset, ldu, vt, _vt_offset, ldvt, q, _q_offset, iq, _iq_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dbdsqr(String uplo, int n, int ncvt, int nru, int ncc, double[] d, double[] e, double[] vt, int ldvt, double[] u, int ldu, double[] c, int Ldc, double[] work, org.netlib.util.intW info) {
    f2j.dbdsqr(uplo, n, ncvt, nru, ncc, d, 0, e, 0, vt, 0, ldvt, u, 0, ldu, c, 0, Ldc, work, 0, info);
  }

  public void dbdsqr(String uplo, int n, int ncvt, int nru, int ncc, double[] d, int _d_offset, double[] e, int _e_offset, double[] vt, int _vt_offset, int ldvt, double[] u, int _u_offset, int ldu, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dbdsqr(uplo, n, ncvt, nru, ncc, d, _d_offset, e, _e_offset, vt, _vt_offset, ldvt, u, _u_offset, ldu, c, _c_offset, Ldc, work, _work_offset, info);
  }

  public void ddisna(String job, int m, int n, double[] d, double[] sep, org.netlib.util.intW info) {
    f2j.ddisna(job, m, n, d, 0, sep, 0, info);
  }

  public void ddisna(String job, int m, int n, double[] d, int _d_offset, double[] sep, int _sep_offset, org.netlib.util.intW info) {
    f2j.ddisna(job, m, n, d, _d_offset, sep, _sep_offset, info);
  }

  public void dgbbrd(String vect, int m, int n, int ncc, int kl, int ku, double[] ab, int ldab, double[] d, double[] e, double[] q, int ldq, double[] pt, int ldpt, double[] c, int Ldc, double[] work, org.netlib.util.intW info) {
    f2j.dgbbrd(vect, m, n, ncc, kl, ku, ab, 0, ldab, d, 0, e, 0, q, 0, ldq, pt, 0, ldpt, c, 0, Ldc, work, 0, info);
  }

  public void dgbbrd(String vect, int m, int n, int ncc, int kl, int ku, double[] ab, int _ab_offset, int ldab, double[] d, int _d_offset, double[] e, int _e_offset, double[] q, int _q_offset, int ldq, double[] pt, int _pt_offset, int ldpt, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dgbbrd(vect, m, n, ncc, kl, ku, ab, _ab_offset, ldab, d, _d_offset, e, _e_offset, q, _q_offset, ldq, pt, _pt_offset, ldpt, c, _c_offset, Ldc, work, _work_offset, info);
  }

  public void dgbcon(String norm, int n, int kl, int ku, double[] ab, int ldab, int[] ipiv, double anorm, org.netlib.util.doubleW rcond, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dgbcon(norm, n, kl, ku, ab, 0, ldab, ipiv, 0, anorm, rcond, work, 0, iwork, 0, info);
  }

  public void dgbcon(String norm, int n, int kl, int ku, double[] ab, int _ab_offset, int ldab, int[] ipiv, int _ipiv_offset, double anorm, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dgbcon(norm, n, kl, ku, ab, _ab_offset, ldab, ipiv, _ipiv_offset, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dgbequ(int m, int n, int kl, int ku, double[] ab, int ldab, double[] r, double[] c, org.netlib.util.doubleW rowcnd, org.netlib.util.doubleW colcnd, org.netlib.util.doubleW amax, org.netlib.util.intW info) {
    f2j.dgbequ(m, n, kl, ku, ab, 0, ldab, r, 0, c, 0, rowcnd, colcnd, amax, info);
  }

  public void dgbequ(int m, int n, int kl, int ku, double[] ab, int _ab_offset, int ldab, double[] r, int _r_offset, double[] c, int _c_offset, org.netlib.util.doubleW rowcnd, org.netlib.util.doubleW colcnd, org.netlib.util.doubleW amax, org.netlib.util.intW info) {
    f2j.dgbequ(m, n, kl, ku, ab, _ab_offset, ldab, r, _r_offset, c, _c_offset, rowcnd, colcnd, amax, info);
  }

  public void dgbrfs(String trans, int n, int kl, int ku, int nrhs, double[] ab, int ldab, double[] afb, int ldafb, int[] ipiv, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dgbrfs(trans, n, kl, ku, nrhs, ab, 0, ldab, afb, 0, ldafb, ipiv, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void dgbrfs(String trans, int n, int kl, int ku, int nrhs, double[] ab, int _ab_offset, int ldab, double[] afb, int _afb_offset, int ldafb, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dgbrfs(trans, n, kl, ku, nrhs, ab, _ab_offset, ldab, afb, _afb_offset, ldafb, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dgbsv(int n, int kl, int ku, int nrhs, double[] ab, int ldab, int[] ipiv, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dgbsv(n, kl, ku, nrhs, ab, 0, ldab, ipiv, 0, b, 0, ldb, info);
  }

  public void dgbsv(int n, int kl, int ku, int nrhs, double[] ab, int _ab_offset, int ldab, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dgbsv(n, kl, ku, nrhs, ab, _ab_offset, ldab, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  public void dgbsvx(String fact, String trans, int n, int kl, int ku, int nrhs, double[] ab, int ldab, double[] afb, int ldafb, int[] ipiv, org.netlib.util.StringW equed, double[] r, double[] c, double[] b, int ldb, double[] x, int ldx, org.netlib.util.doubleW rcond, double[] ferr, double[] berr, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dgbsvx(fact, trans, n, kl, ku, nrhs, ab, 0, ldab, afb, 0, ldafb, ipiv, 0, equed, r, 0, c, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void dgbsvx(String fact, String trans, int n, int kl, int ku, int nrhs, double[] ab, int _ab_offset, int ldab, double[] afb, int _afb_offset, int ldafb, int[] ipiv, int _ipiv_offset, org.netlib.util.StringW equed, double[] r, int _r_offset, double[] c, int _c_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dgbsvx(fact, trans, n, kl, ku, nrhs, ab, _ab_offset, ldab, afb, _afb_offset, ldafb, ipiv, _ipiv_offset, equed, r, _r_offset, c, _c_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dgbtf2(int m, int n, int kl, int ku, double[] ab, int ldab, int[] ipiv, org.netlib.util.intW info) {
    f2j.dgbtf2(m, n, kl, ku, ab, 0, ldab, ipiv, 0, info);
  }

  public void dgbtf2(int m, int n, int kl, int ku, double[] ab, int _ab_offset, int ldab, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    f2j.dgbtf2(m, n, kl, ku, ab, _ab_offset, ldab, ipiv, _ipiv_offset, info);
  }

  public void dgbtrf(int m, int n, int kl, int ku, double[] ab, int ldab, int[] ipiv, org.netlib.util.intW info) {
    f2j.dgbtrf(m, n, kl, ku, ab, 0, ldab, ipiv, 0, info);
  }

  public void dgbtrf(int m, int n, int kl, int ku, double[] ab, int _ab_offset, int ldab, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    f2j.dgbtrf(m, n, kl, ku, ab, _ab_offset, ldab, ipiv, _ipiv_offset, info);
  }

  public void dgbtrs(String trans, int n, int kl, int ku, int nrhs, double[] ab, int ldab, int[] ipiv, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dgbtrs(trans, n, kl, ku, nrhs, ab, 0, ldab, ipiv, 0, b, 0, ldb, info);
  }

  public void dgbtrs(String trans, int n, int kl, int ku, int nrhs, double[] ab, int _ab_offset, int ldab, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dgbtrs(trans, n, kl, ku, nrhs, ab, _ab_offset, ldab, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  public void dgebak(String job, String side, int n, int ilo, int ihi, double[] scale, int m, double[] v, int ldv, org.netlib.util.intW info) {
    f2j.dgebak(job, side, n, ilo, ihi, scale, 0, m, v, 0, ldv, info);
  }

  public void dgebak(String job, String side, int n, int ilo, int ihi, double[] scale, int _scale_offset, int m, double[] v, int _v_offset, int ldv, org.netlib.util.intW info) {
    f2j.dgebak(job, side, n, ilo, ihi, scale, _scale_offset, m, v, _v_offset, ldv, info);
  }

  public void dgebal(String job, int n, double[] a, int lda, org.netlib.util.intW ilo, org.netlib.util.intW ihi, double[] scale, org.netlib.util.intW info) {
    f2j.dgebal(job, n, a, 0, lda, ilo, ihi, scale, 0, info);
  }

  public void dgebal(String job, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW ilo, org.netlib.util.intW ihi, double[] scale, int _scale_offset, org.netlib.util.intW info) {
    f2j.dgebal(job, n, a, _a_offset, lda, ilo, ihi, scale, _scale_offset, info);
  }

  public void dgebd2(int m, int n, double[] a, int lda, double[] d, double[] e, double[] tauq, double[] taup, double[] work, org.netlib.util.intW info) {
    f2j.dgebd2(m, n, a, 0, lda, d, 0, e, 0, tauq, 0, taup, 0, work, 0, info);
  }

  public void dgebd2(int m, int n, double[] a, int _a_offset, int lda, double[] d, int _d_offset, double[] e, int _e_offset, double[] tauq, int _tauq_offset, double[] taup, int _taup_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dgebd2(m, n, a, _a_offset, lda, d, _d_offset, e, _e_offset, tauq, _tauq_offset, taup, _taup_offset, work, _work_offset, info);
  }

  public void dgebrd(int m, int n, double[] a, int lda, double[] d, double[] e, double[] tauq, double[] taup, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dgebrd(m, n, a, 0, lda, d, 0, e, 0, tauq, 0, taup, 0, work, 0, lwork, info);
  }

  public void dgebrd(int m, int n, double[] a, int _a_offset, int lda, double[] d, int _d_offset, double[] e, int _e_offset, double[] tauq, int _tauq_offset, double[] taup, int _taup_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dgebrd(m, n, a, _a_offset, lda, d, _d_offset, e, _e_offset, tauq, _tauq_offset, taup, _taup_offset, work, _work_offset, lwork, info);
  }

  public void dgecon(String norm, int n, double[] a, int lda, double anorm, org.netlib.util.doubleW rcond, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dgecon(norm, n, a, 0, lda, anorm, rcond, work, 0, iwork, 0, info);
  }

  public void dgecon(String norm, int n, double[] a, int _a_offset, int lda, double anorm, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dgecon(norm, n, a, _a_offset, lda, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dgeequ(int m, int n, double[] a, int lda, double[] r, double[] c, org.netlib.util.doubleW rowcnd, org.netlib.util.doubleW colcnd, org.netlib.util.doubleW amax, org.netlib.util.intW info) {
    f2j.dgeequ(m, n, a, 0, lda, r, 0, c, 0, rowcnd, colcnd, amax, info);
  }

  public void dgeequ(int m, int n, double[] a, int _a_offset, int lda, double[] r, int _r_offset, double[] c, int _c_offset, org.netlib.util.doubleW rowcnd, org.netlib.util.doubleW colcnd, org.netlib.util.doubleW amax, org.netlib.util.intW info) {
    f2j.dgeequ(m, n, a, _a_offset, lda, r, _r_offset, c, _c_offset, rowcnd, colcnd, amax, info);
  }

  public void dgees(String jobvs, String sort, java.lang.Object select, int n, double[] a, int lda, org.netlib.util.intW sdim, double[] wr, double[] wi, double[] vs, int ldvs, double[] work, int lwork, boolean[] bwork, org.netlib.util.intW info) {
    f2j.dgees(jobvs, sort, select, n, a, 0, lda, sdim, wr, 0, wi, 0, vs, 0, ldvs, work, 0, lwork, bwork, 0, info);
  }

  public void dgees(String jobvs, String sort, java.lang.Object select, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW sdim, double[] wr, int _wr_offset, double[] wi, int _wi_offset, double[] vs, int _vs_offset, int ldvs, double[] work, int _work_offset, int lwork, boolean[] bwork, int _bwork_offset, org.netlib.util.intW info) {
    f2j.dgees(jobvs, sort, select, n, a, _a_offset, lda, sdim, wr, _wr_offset, wi, _wi_offset, vs, _vs_offset, ldvs, work, _work_offset, lwork, bwork, _bwork_offset, info);
  }

  public void dgeesx(String jobvs, String sort, java.lang.Object select, String sense, int n, double[] a, int lda, org.netlib.util.intW sdim, double[] wr, double[] wi, double[] vs, int ldvs, org.netlib.util.doubleW rconde, org.netlib.util.doubleW rcondv, double[] work, int lwork, int[] iwork, int liwork, boolean[] bwork, org.netlib.util.intW info) {
    f2j.dgeesx(jobvs, sort, select, sense, n, a, 0, lda, sdim, wr, 0, wi, 0, vs, 0, ldvs, rconde, rcondv, work, 0, lwork, iwork, 0, liwork, bwork, 0, info);
  }

  public void dgeesx(String jobvs, String sort, java.lang.Object select, String sense, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW sdim, double[] wr, int _wr_offset, double[] wi, int _wi_offset, double[] vs, int _vs_offset, int ldvs, org.netlib.util.doubleW rconde, org.netlib.util.doubleW rcondv, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, boolean[] bwork, int _bwork_offset, org.netlib.util.intW info) {
    f2j.dgeesx(jobvs, sort, select, sense, n, a, _a_offset, lda, sdim, wr, _wr_offset, wi, _wi_offset, vs, _vs_offset, ldvs, rconde, rcondv, work, _work_offset, lwork, iwork, _iwork_offset, liwork, bwork, _bwork_offset, info);
  }

  public void dgeev(String jobvl, String jobvr, int n, double[] a, int lda, double[] wr, double[] wi, double[] vl, int ldvl, double[] vr, int ldvr, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dgeev(jobvl, jobvr, n, a, 0, lda, wr, 0, wi, 0, vl, 0, ldvl, vr, 0, ldvr, work, 0, lwork, info);
  }

  public void dgeev(String jobvl, String jobvr, int n, double[] a, int _a_offset, int lda, double[] wr, int _wr_offset, double[] wi, int _wi_offset, double[] vl, int _vl_offset, int ldvl, double[] vr, int _vr_offset, int ldvr, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dgeev(jobvl, jobvr, n, a, _a_offset, lda, wr, _wr_offset, wi, _wi_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, work, _work_offset, lwork, info);
  }

  public void dgeevx(String balanc, String jobvl, String jobvr, String sense, int n, double[] a, int lda, double[] wr, double[] wi, double[] vl, int ldvl, double[] vr, int ldvr, org.netlib.util.intW ilo, org.netlib.util.intW ihi, double[] scale, org.netlib.util.doubleW abnrm, double[] rconde, double[] rcondv, double[] work, int lwork, int[] iwork, org.netlib.util.intW info) {
    f2j.dgeevx(balanc, jobvl, jobvr, sense, n, a, 0, lda, wr, 0, wi, 0, vl, 0, ldvl, vr, 0, ldvr, ilo, ihi, scale, 0, abnrm, rconde, 0, rcondv, 0, work, 0, lwork, iwork, 0, info);
  }

  public void dgeevx(String balanc, String jobvl, String jobvr, String sense, int n, double[] a, int _a_offset, int lda, double[] wr, int _wr_offset, double[] wi, int _wi_offset, double[] vl, int _vl_offset, int ldvl, double[] vr, int _vr_offset, int ldvr, org.netlib.util.intW ilo, org.netlib.util.intW ihi, double[] scale, int _scale_offset, org.netlib.util.doubleW abnrm, double[] rconde, int _rconde_offset, double[] rcondv, int _rcondv_offset, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dgeevx(balanc, jobvl, jobvr, sense, n, a, _a_offset, lda, wr, _wr_offset, wi, _wi_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, ilo, ihi, scale, _scale_offset, abnrm, rconde, _rconde_offset, rcondv, _rcondv_offset, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  public void dgegs(String jobvsl, String jobvsr, int n, double[] a, int lda, double[] b, int ldb, double[] alphar, double[] alphai, double[] beta, double[] vsl, int ldvsl, double[] vsr, int ldvsr, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dgegs(jobvsl, jobvsr, n, a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, vsl, 0, ldvsl, vsr, 0, ldvsr, work, 0, lwork, info);
  }

  public void dgegs(String jobvsl, String jobvsr, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] alphar, int _alphar_offset, double[] alphai, int _alphai_offset, double[] beta, int _beta_offset, double[] vsl, int _vsl_offset, int ldvsl, double[] vsr, int _vsr_offset, int ldvsr, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dgegs(jobvsl, jobvsr, n, a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vsl, _vsl_offset, ldvsl, vsr, _vsr_offset, ldvsr, work, _work_offset, lwork, info);
  }

  public void dgegv(String jobvl, String jobvr, int n, double[] a, int lda, double[] b, int ldb, double[] alphar, double[] alphai, double[] beta, double[] vl, int ldvl, double[] vr, int ldvr, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dgegv(jobvl, jobvr, n, a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, vl, 0, ldvl, vr, 0, ldvr, work, 0, lwork, info);
  }

  public void dgegv(String jobvl, String jobvr, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] alphar, int _alphar_offset, double[] alphai, int _alphai_offset, double[] beta, int _beta_offset, double[] vl, int _vl_offset, int ldvl, double[] vr, int _vr_offset, int ldvr, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dgegv(jobvl, jobvr, n, a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, work, _work_offset, lwork, info);
  }

  public void dgehd2(int n, int ilo, int ihi, double[] a, int lda, double[] tau, double[] work, org.netlib.util.intW info) {
    f2j.dgehd2(n, ilo, ihi, a, 0, lda, tau, 0, work, 0, info);
  }

  public void dgehd2(int n, int ilo, int ihi, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dgehd2(n, ilo, ihi, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  public void dgehrd(int n, int ilo, int ihi, double[] a, int lda, double[] tau, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dgehrd(n, ilo, ihi, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void dgehrd(int n, int ilo, int ihi, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dgehrd(n, ilo, ihi, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void dgelq2(int m, int n, double[] a, int lda, double[] tau, double[] work, org.netlib.util.intW info) {
    f2j.dgelq2(m, n, a, 0, lda, tau, 0, work, 0, info);
  }

  public void dgelq2(int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dgelq2(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  public void dgelqf(int m, int n, double[] a, int lda, double[] tau, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dgelqf(m, n, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void dgelqf(int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dgelqf(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void dgels(String trans, int m, int n, int nrhs, double[] a, int lda, double[] b, int ldb, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dgels(trans, m, n, nrhs, a, 0, lda, b, 0, ldb, work, 0, lwork, info);
  }

  public void dgels(String trans, int m, int n, int nrhs, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dgels(trans, m, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, work, _work_offset, lwork, info);
  }

  public void dgelsd(int m, int n, int nrhs, double[] a, int lda, double[] b, int ldb, double[] s, double rcond, org.netlib.util.intW rank, double[] work, int lwork, int[] iwork, org.netlib.util.intW info) {
    f2j.dgelsd(m, n, nrhs, a, 0, lda, b, 0, ldb, s, 0, rcond, rank, work, 0, lwork, iwork, 0, info);
  }

  public void dgelsd(int m, int n, int nrhs, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] s, int _s_offset, double rcond, org.netlib.util.intW rank, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dgelsd(m, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, s, _s_offset, rcond, rank, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  public void dgelss(int m, int n, int nrhs, double[] a, int lda, double[] b, int ldb, double[] s, double rcond, org.netlib.util.intW rank, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dgelss(m, n, nrhs, a, 0, lda, b, 0, ldb, s, 0, rcond, rank, work, 0, lwork, info);
  }

  public void dgelss(int m, int n, int nrhs, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] s, int _s_offset, double rcond, org.netlib.util.intW rank, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dgelss(m, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, s, _s_offset, rcond, rank, work, _work_offset, lwork, info);
  }

  public void dgelsx(int m, int n, int nrhs, double[] a, int lda, double[] b, int ldb, int[] jpvt, double rcond, org.netlib.util.intW rank, double[] work, org.netlib.util.intW info) {
    f2j.dgelsx(m, n, nrhs, a, 0, lda, b, 0, ldb, jpvt, 0, rcond, rank, work, 0, info);
  }

  public void dgelsx(int m, int n, int nrhs, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, int[] jpvt, int _jpvt_offset, double rcond, org.netlib.util.intW rank, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dgelsx(m, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, jpvt, _jpvt_offset, rcond, rank, work, _work_offset, info);
  }

  public void dgelsy(int m, int n, int nrhs, double[] a, int lda, double[] b, int ldb, int[] jpvt, double rcond, org.netlib.util.intW rank, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dgelsy(m, n, nrhs, a, 0, lda, b, 0, ldb, jpvt, 0, rcond, rank, work, 0, lwork, info);
  }

  public void dgelsy(int m, int n, int nrhs, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, int[] jpvt, int _jpvt_offset, double rcond, org.netlib.util.intW rank, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dgelsy(m, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, jpvt, _jpvt_offset, rcond, rank, work, _work_offset, lwork, info);
  }

  public void dgeql2(int m, int n, double[] a, int lda, double[] tau, double[] work, org.netlib.util.intW info) {
    f2j.dgeql2(m, n, a, 0, lda, tau, 0, work, 0, info);
  }

  public void dgeql2(int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dgeql2(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  public void dgeqlf(int m, int n, double[] a, int lda, double[] tau, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dgeqlf(m, n, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void dgeqlf(int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dgeqlf(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void dgeqp3(int m, int n, double[] a, int lda, int[] jpvt, double[] tau, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dgeqp3(m, n, a, 0, lda, jpvt, 0, tau, 0, work, 0, lwork, info);
  }

  public void dgeqp3(int m, int n, double[] a, int _a_offset, int lda, int[] jpvt, int _jpvt_offset, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dgeqp3(m, n, a, _a_offset, lda, jpvt, _jpvt_offset, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void dgeqpf(int m, int n, double[] a, int lda, int[] jpvt, double[] tau, double[] work, org.netlib.util.intW info) {
    f2j.dgeqpf(m, n, a, 0, lda, jpvt, 0, tau, 0, work, 0, info);
  }

  public void dgeqpf(int m, int n, double[] a, int _a_offset, int lda, int[] jpvt, int _jpvt_offset, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dgeqpf(m, n, a, _a_offset, lda, jpvt, _jpvt_offset, tau, _tau_offset, work, _work_offset, info);
  }

  public void dgeqr2(int m, int n, double[] a, int lda, double[] tau, double[] work, org.netlib.util.intW info) {
    f2j.dgeqr2(m, n, a, 0, lda, tau, 0, work, 0, info);
  }

  public void dgeqr2(int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dgeqr2(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  public void dgeqrf(int m, int n, double[] a, int lda, double[] tau, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dgeqrf(m, n, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void dgeqrf(int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dgeqrf(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void dgerfs(String trans, int n, int nrhs, double[] a, int lda, double[] af, int ldaf, int[] ipiv, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dgerfs(trans, n, nrhs, a, 0, lda, af, 0, ldaf, ipiv, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void dgerfs(String trans, int n, int nrhs, double[] a, int _a_offset, int lda, double[] af, int _af_offset, int ldaf, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dgerfs(trans, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dgerq2(int m, int n, double[] a, int lda, double[] tau, double[] work, org.netlib.util.intW info) {
    f2j.dgerq2(m, n, a, 0, lda, tau, 0, work, 0, info);
  }

  public void dgerq2(int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dgerq2(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  public void dgerqf(int m, int n, double[] a, int lda, double[] tau, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dgerqf(m, n, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void dgerqf(int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dgerqf(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void dgesc2(int n, double[] a, int lda, double[] rhs, int[] ipiv, int[] jpiv, org.netlib.util.doubleW scale) {
    f2j.dgesc2(n, a, 0, lda, rhs, 0, ipiv, 0, jpiv, 0, scale);
  }

  public void dgesc2(int n, double[] a, int _a_offset, int lda, double[] rhs, int _rhs_offset, int[] ipiv, int _ipiv_offset, int[] jpiv, int _jpiv_offset, org.netlib.util.doubleW scale) {
    f2j.dgesc2(n, a, _a_offset, lda, rhs, _rhs_offset, ipiv, _ipiv_offset, jpiv, _jpiv_offset, scale);
  }

  public void dgesdd(String jobz, int m, int n, double[] a, int lda, double[] s, double[] u, int ldu, double[] vt, int ldvt, double[] work, int lwork, int[] iwork, org.netlib.util.intW info) {
    f2j.dgesdd(jobz, m, n, a, 0, lda, s, 0, u, 0, ldu, vt, 0, ldvt, work, 0, lwork, iwork, 0, info);
  }

  public void dgesdd(String jobz, int m, int n, double[] a, int _a_offset, int lda, double[] s, int _s_offset, double[] u, int _u_offset, int ldu, double[] vt, int _vt_offset, int ldvt, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dgesdd(jobz, m, n, a, _a_offset, lda, s, _s_offset, u, _u_offset, ldu, vt, _vt_offset, ldvt, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  public void dgesv(int n, int nrhs, double[] a, int lda, int[] ipiv, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dgesv(n, nrhs, a, 0, lda, ipiv, 0, b, 0, ldb, info);
  }

  public void dgesv(int n, int nrhs, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dgesv(n, nrhs, a, _a_offset, lda, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  public void dgesvd(String jobu, String jobvt, int m, int n, double[] a, int lda, double[] s, double[] u, int ldu, double[] vt, int ldvt, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dgesvd(jobu, jobvt, m, n, a, 0, lda, s, 0, u, 0, ldu, vt, 0, ldvt, work, 0, lwork, info);
  }

  public void dgesvd(String jobu, String jobvt, int m, int n, double[] a, int _a_offset, int lda, double[] s, int _s_offset, double[] u, int _u_offset, int ldu, double[] vt, int _vt_offset, int ldvt, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dgesvd(jobu, jobvt, m, n, a, _a_offset, lda, s, _s_offset, u, _u_offset, ldu, vt, _vt_offset, ldvt, work, _work_offset, lwork, info);
  }

  public void dgesvx(String fact, String trans, int n, int nrhs, double[] a, int lda, double[] af, int ldaf, int[] ipiv, org.netlib.util.StringW equed, double[] r, double[] c, double[] b, int ldb, double[] x, int ldx, org.netlib.util.doubleW rcond, double[] ferr, double[] berr, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dgesvx(fact, trans, n, nrhs, a, 0, lda, af, 0, ldaf, ipiv, 0, equed, r, 0, c, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void dgesvx(String fact, String trans, int n, int nrhs, double[] a, int _a_offset, int lda, double[] af, int _af_offset, int ldaf, int[] ipiv, int _ipiv_offset, org.netlib.util.StringW equed, double[] r, int _r_offset, double[] c, int _c_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dgesvx(fact, trans, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, ipiv, _ipiv_offset, equed, r, _r_offset, c, _c_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dgetc2(int n, double[] a, int lda, int[] ipiv, int[] jpiv, org.netlib.util.intW info) {
    f2j.dgetc2(n, a, 0, lda, ipiv, 0, jpiv, 0, info);
  }

  public void dgetc2(int n, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, int[] jpiv, int _jpiv_offset, org.netlib.util.intW info) {
    f2j.dgetc2(n, a, _a_offset, lda, ipiv, _ipiv_offset, jpiv, _jpiv_offset, info);
  }

  public void dgetf2(int m, int n, double[] a, int lda, int[] ipiv, org.netlib.util.intW info) {
    f2j.dgetf2(m, n, a, 0, lda, ipiv, 0, info);
  }

  public void dgetf2(int m, int n, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    f2j.dgetf2(m, n, a, _a_offset, lda, ipiv, _ipiv_offset, info);
  }

  public void dgetrf(int m, int n, double[] a, int lda, int[] ipiv, org.netlib.util.intW info) {
    f2j.dgetrf(m, n, a, 0, lda, ipiv, 0, info);
  }

  public void dgetrf(int m, int n, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    f2j.dgetrf(m, n, a, _a_offset, lda, ipiv, _ipiv_offset, info);
  }

  public void dgetri(int n, double[] a, int lda, int[] ipiv, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dgetri(n, a, 0, lda, ipiv, 0, work, 0, lwork, info);
  }

  public void dgetri(int n, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dgetri(n, a, _a_offset, lda, ipiv, _ipiv_offset, work, _work_offset, lwork, info);
  }

  public void dgetrs(String trans, int n, int nrhs, double[] a, int lda, int[] ipiv, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dgetrs(trans, n, nrhs, a, 0, lda, ipiv, 0, b, 0, ldb, info);
  }

  public void dgetrs(String trans, int n, int nrhs, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dgetrs(trans, n, nrhs, a, _a_offset, lda, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  public void dggbak(String job, String side, int n, int ilo, int ihi, double[] lscale, double[] rscale, int m, double[] v, int ldv, org.netlib.util.intW info) {
    f2j.dggbak(job, side, n, ilo, ihi, lscale, 0, rscale, 0, m, v, 0, ldv, info);
  }

  public void dggbak(String job, String side, int n, int ilo, int ihi, double[] lscale, int _lscale_offset, double[] rscale, int _rscale_offset, int m, double[] v, int _v_offset, int ldv, org.netlib.util.intW info) {
    f2j.dggbak(job, side, n, ilo, ihi, lscale, _lscale_offset, rscale, _rscale_offset, m, v, _v_offset, ldv, info);
  }

  public void dggbal(String job, int n, double[] a, int lda, double[] b, int ldb, org.netlib.util.intW ilo, org.netlib.util.intW ihi, double[] lscale, double[] rscale, double[] work, org.netlib.util.intW info) {
    f2j.dggbal(job, n, a, 0, lda, b, 0, ldb, ilo, ihi, lscale, 0, rscale, 0, work, 0, info);
  }

  public void dggbal(String job, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, org.netlib.util.intW ilo, org.netlib.util.intW ihi, double[] lscale, int _lscale_offset, double[] rscale, int _rscale_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dggbal(job, n, a, _a_offset, lda, b, _b_offset, ldb, ilo, ihi, lscale, _lscale_offset, rscale, _rscale_offset, work, _work_offset, info);
  }

  public void dgges(String jobvsl, String jobvsr, String sort, java.lang.Object selctg, int n, double[] a, int lda, double[] b, int ldb, org.netlib.util.intW sdim, double[] alphar, double[] alphai, double[] beta, double[] vsl, int ldvsl, double[] vsr, int ldvsr, double[] work, int lwork, boolean[] bwork, org.netlib.util.intW info) {
    f2j.dgges(jobvsl, jobvsr, sort, selctg, n, a, 0, lda, b, 0, ldb, sdim, alphar, 0, alphai, 0, beta, 0, vsl, 0, ldvsl, vsr, 0, ldvsr, work, 0, lwork, bwork, 0, info);
  }

  public void dgges(String jobvsl, String jobvsr, String sort, java.lang.Object selctg, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, org.netlib.util.intW sdim, double[] alphar, int _alphar_offset, double[] alphai, int _alphai_offset, double[] beta, int _beta_offset, double[] vsl, int _vsl_offset, int ldvsl, double[] vsr, int _vsr_offset, int ldvsr, double[] work, int _work_offset, int lwork, boolean[] bwork, int _bwork_offset, org.netlib.util.intW info) {
    f2j.dgges(jobvsl, jobvsr, sort, selctg, n, a, _a_offset, lda, b, _b_offset, ldb, sdim, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vsl, _vsl_offset, ldvsl, vsr, _vsr_offset, ldvsr, work, _work_offset, lwork, bwork, _bwork_offset, info);
  }

  public void dggesx(String jobvsl, String jobvsr, String sort, java.lang.Object selctg, String sense, int n, double[] a, int lda, double[] b, int ldb, org.netlib.util.intW sdim, double[] alphar, double[] alphai, double[] beta, double[] vsl, int ldvsl, double[] vsr, int ldvsr, double[] rconde, double[] rcondv, double[] work, int lwork, int[] iwork, int liwork, boolean[] bwork, org.netlib.util.intW info) {
    f2j.dggesx(jobvsl, jobvsr, sort, selctg, sense, n, a, 0, lda, b, 0, ldb, sdim, alphar, 0, alphai, 0, beta, 0, vsl, 0, ldvsl, vsr, 0, ldvsr, rconde, 0, rcondv, 0, work, 0, lwork, iwork, 0, liwork, bwork, 0, info);
  }

  public void dggesx(String jobvsl, String jobvsr, String sort, java.lang.Object selctg, String sense, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, org.netlib.util.intW sdim, double[] alphar, int _alphar_offset, double[] alphai, int _alphai_offset, double[] beta, int _beta_offset, double[] vsl, int _vsl_offset, int ldvsl, double[] vsr, int _vsr_offset, int ldvsr, double[] rconde, int _rconde_offset, double[] rcondv, int _rcondv_offset, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, boolean[] bwork, int _bwork_offset, org.netlib.util.intW info) {
    f2j.dggesx(jobvsl, jobvsr, sort, selctg, sense, n, a, _a_offset, lda, b, _b_offset, ldb, sdim, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vsl, _vsl_offset, ldvsl, vsr, _vsr_offset, ldvsr, rconde, _rconde_offset, rcondv, _rcondv_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, bwork, _bwork_offset, info);
  }

  public void dggev(String jobvl, String jobvr, int n, double[] a, int lda, double[] b, int ldb, double[] alphar, double[] alphai, double[] beta, double[] vl, int ldvl, double[] vr, int ldvr, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dggev(jobvl, jobvr, n, a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, vl, 0, ldvl, vr, 0, ldvr, work, 0, lwork, info);
  }

  public void dggev(String jobvl, String jobvr, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] alphar, int _alphar_offset, double[] alphai, int _alphai_offset, double[] beta, int _beta_offset, double[] vl, int _vl_offset, int ldvl, double[] vr, int _vr_offset, int ldvr, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dggev(jobvl, jobvr, n, a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, work, _work_offset, lwork, info);
  }

  public void dggevx(String balanc, String jobvl, String jobvr, String sense, int n, double[] a, int lda, double[] b, int ldb, double[] alphar, double[] alphai, double[] beta, double[] vl, int ldvl, double[] vr, int ldvr, org.netlib.util.intW ilo, org.netlib.util.intW ihi, double[] lscale, double[] rscale, org.netlib.util.doubleW abnrm, org.netlib.util.doubleW bbnrm, double[] rconde, double[] rcondv, double[] work, int lwork, int[] iwork, boolean[] bwork, org.netlib.util.intW info) {
    f2j.dggevx(balanc, jobvl, jobvr, sense, n, a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, vl, 0, ldvl, vr, 0, ldvr, ilo, ihi, lscale, 0, rscale, 0, abnrm, bbnrm, rconde, 0, rcondv, 0, work, 0, lwork, iwork, 0, bwork, 0, info);
  }

  public void dggevx(String balanc, String jobvl, String jobvr, String sense, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] alphar, int _alphar_offset, double[] alphai, int _alphai_offset, double[] beta, int _beta_offset, double[] vl, int _vl_offset, int ldvl, double[] vr, int _vr_offset, int ldvr, org.netlib.util.intW ilo, org.netlib.util.intW ihi, double[] lscale, int _lscale_offset, double[] rscale, int _rscale_offset, org.netlib.util.doubleW abnrm, org.netlib.util.doubleW bbnrm, double[] rconde, int _rconde_offset, double[] rcondv, int _rcondv_offset, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, boolean[] bwork, int _bwork_offset, org.netlib.util.intW info) {
    f2j.dggevx(balanc, jobvl, jobvr, sense, n, a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, ilo, ihi, lscale, _lscale_offset, rscale, _rscale_offset, abnrm, bbnrm, rconde, _rconde_offset, rcondv, _rcondv_offset, work, _work_offset, lwork, iwork, _iwork_offset, bwork, _bwork_offset, info);
  }

  public void dggglm(int n, int m, int p, double[] a, int lda, double[] b, int ldb, double[] d, double[] x, double[] y, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dggglm(n, m, p, a, 0, lda, b, 0, ldb, d, 0, x, 0, y, 0, work, 0, lwork, info);
  }

  public void dggglm(int n, int m, int p, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] d, int _d_offset, double[] x, int _x_offset, double[] y, int _y_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dggglm(n, m, p, a, _a_offset, lda, b, _b_offset, ldb, d, _d_offset, x, _x_offset, y, _y_offset, work, _work_offset, lwork, info);
  }

  public void dgghrd(String compq, String compz, int n, int ilo, int ihi, double[] a, int lda, double[] b, int ldb, double[] q, int ldq, double[] z, int ldz, org.netlib.util.intW info) {
    f2j.dgghrd(compq, compz, n, ilo, ihi, a, 0, lda, b, 0, ldb, q, 0, ldq, z, 0, ldz, info);
  }

  public void dgghrd(String compq, String compz, int n, int ilo, int ihi, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] q, int _q_offset, int ldq, double[] z, int _z_offset, int ldz, org.netlib.util.intW info) {
    f2j.dgghrd(compq, compz, n, ilo, ihi, a, _a_offset, lda, b, _b_offset, ldb, q, _q_offset, ldq, z, _z_offset, ldz, info);
  }

  public void dgglse(int m, int n, int p, double[] a, int lda, double[] b, int ldb, double[] c, double[] d, double[] x, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dgglse(m, n, p, a, 0, lda, b, 0, ldb, c, 0, d, 0, x, 0, work, 0, lwork, info);
  }

  public void dgglse(int m, int n, int p, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] c, int _c_offset, double[] d, int _d_offset, double[] x, int _x_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dgglse(m, n, p, a, _a_offset, lda, b, _b_offset, ldb, c, _c_offset, d, _d_offset, x, _x_offset, work, _work_offset, lwork, info);
  }

  public void dggqrf(int n, int m, int p, double[] a, int lda, double[] taua, double[] b, int ldb, double[] taub, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dggqrf(n, m, p, a, 0, lda, taua, 0, b, 0, ldb, taub, 0, work, 0, lwork, info);
  }

  public void dggqrf(int n, int m, int p, double[] a, int _a_offset, int lda, double[] taua, int _taua_offset, double[] b, int _b_offset, int ldb, double[] taub, int _taub_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dggqrf(n, m, p, a, _a_offset, lda, taua, _taua_offset, b, _b_offset, ldb, taub, _taub_offset, work, _work_offset, lwork, info);
  }

  public void dggrqf(int m, int p, int n, double[] a, int lda, double[] taua, double[] b, int ldb, double[] taub, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dggrqf(m, p, n, a, 0, lda, taua, 0, b, 0, ldb, taub, 0, work, 0, lwork, info);
  }

  public void dggrqf(int m, int p, int n, double[] a, int _a_offset, int lda, double[] taua, int _taua_offset, double[] b, int _b_offset, int ldb, double[] taub, int _taub_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dggrqf(m, p, n, a, _a_offset, lda, taua, _taua_offset, b, _b_offset, ldb, taub, _taub_offset, work, _work_offset, lwork, info);
  }

  public void dggsvd(String jobu, String jobv, String jobq, int m, int n, int p, org.netlib.util.intW k, org.netlib.util.intW l, double[] a, int lda, double[] b, int ldb, double[] alpha, double[] beta, double[] u, int ldu, double[] v, int ldv, double[] q, int ldq, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dggsvd(jobu, jobv, jobq, m, n, p, k, l, a, 0, lda, b, 0, ldb, alpha, 0, beta, 0, u, 0, ldu, v, 0, ldv, q, 0, ldq, work, 0, iwork, 0, info);
  }

  public void dggsvd(String jobu, String jobv, String jobq, int m, int n, int p, org.netlib.util.intW k, org.netlib.util.intW l, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] alpha, int _alpha_offset, double[] beta, int _beta_offset, double[] u, int _u_offset, int ldu, double[] v, int _v_offset, int ldv, double[] q, int _q_offset, int ldq, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dggsvd(jobu, jobv, jobq, m, n, p, k, l, a, _a_offset, lda, b, _b_offset, ldb, alpha, _alpha_offset, beta, _beta_offset, u, _u_offset, ldu, v, _v_offset, ldv, q, _q_offset, ldq, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dggsvp(String jobu, String jobv, String jobq, int m, int p, int n, double[] a, int lda, double[] b, int ldb, double tola, double tolb, org.netlib.util.intW k, org.netlib.util.intW l, double[] u, int ldu, double[] v, int ldv, double[] q, int ldq, int[] iwork, double[] tau, double[] work, org.netlib.util.intW info) {
    f2j.dggsvp(jobu, jobv, jobq, m, p, n, a, 0, lda, b, 0, ldb, tola, tolb, k, l, u, 0, ldu, v, 0, ldv, q, 0, ldq, iwork, 0, tau, 0, work, 0, info);
  }

  public void dggsvp(String jobu, String jobv, String jobq, int m, int p, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double tola, double tolb, org.netlib.util.intW k, org.netlib.util.intW l, double[] u, int _u_offset, int ldu, double[] v, int _v_offset, int ldv, double[] q, int _q_offset, int ldq, int[] iwork, int _iwork_offset, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dggsvp(jobu, jobv, jobq, m, p, n, a, _a_offset, lda, b, _b_offset, ldb, tola, tolb, k, l, u, _u_offset, ldu, v, _v_offset, ldv, q, _q_offset, ldq, iwork, _iwork_offset, tau, _tau_offset, work, _work_offset, info);
  }

  public void dgtcon(String norm, int n, double[] dl, double[] d, double[] du, double[] du2, int[] ipiv, double anorm, org.netlib.util.doubleW rcond, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dgtcon(norm, n, dl, 0, d, 0, du, 0, du2, 0, ipiv, 0, anorm, rcond, work, 0, iwork, 0, info);
  }

  public void dgtcon(String norm, int n, double[] dl, int _dl_offset, double[] d, int _d_offset, double[] du, int _du_offset, double[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, double anorm, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dgtcon(norm, n, dl, _dl_offset, d, _d_offset, du, _du_offset, du2, _du2_offset, ipiv, _ipiv_offset, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dgtrfs(String trans, int n, int nrhs, double[] dl, double[] d, double[] du, double[] dlf, double[] df, double[] duf, double[] du2, int[] ipiv, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dgtrfs(trans, n, nrhs, dl, 0, d, 0, du, 0, dlf, 0, df, 0, duf, 0, du2, 0, ipiv, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void dgtrfs(String trans, int n, int nrhs, double[] dl, int _dl_offset, double[] d, int _d_offset, double[] du, int _du_offset, double[] dlf, int _dlf_offset, double[] df, int _df_offset, double[] duf, int _duf_offset, double[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dgtrfs(trans, n, nrhs, dl, _dl_offset, d, _d_offset, du, _du_offset, dlf, _dlf_offset, df, _df_offset, duf, _duf_offset, du2, _du2_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dgtsv(int n, int nrhs, double[] dl, double[] d, double[] du, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dgtsv(n, nrhs, dl, 0, d, 0, du, 0, b, 0, ldb, info);
  }

  public void dgtsv(int n, int nrhs, double[] dl, int _dl_offset, double[] d, int _d_offset, double[] du, int _du_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dgtsv(n, nrhs, dl, _dl_offset, d, _d_offset, du, _du_offset, b, _b_offset, ldb, info);
  }

  public void dgtsvx(String fact, String trans, int n, int nrhs, double[] dl, double[] d, double[] du, double[] dlf, double[] df, double[] duf, double[] du2, int[] ipiv, double[] b, int ldb, double[] x, int ldx, org.netlib.util.doubleW rcond, double[] ferr, double[] berr, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dgtsvx(fact, trans, n, nrhs, dl, 0, d, 0, du, 0, dlf, 0, df, 0, duf, 0, du2, 0, ipiv, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void dgtsvx(String fact, String trans, int n, int nrhs, double[] dl, int _dl_offset, double[] d, int _d_offset, double[] du, int _du_offset, double[] dlf, int _dlf_offset, double[] df, int _df_offset, double[] duf, int _duf_offset, double[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dgtsvx(fact, trans, n, nrhs, dl, _dl_offset, d, _d_offset, du, _du_offset, dlf, _dlf_offset, df, _df_offset, duf, _duf_offset, du2, _du2_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dgttrf(int n, double[] dl, double[] d, double[] du, double[] du2, int[] ipiv, org.netlib.util.intW info) {
    f2j.dgttrf(n, dl, 0, d, 0, du, 0, du2, 0, ipiv, 0, info);
  }

  public void dgttrf(int n, double[] dl, int _dl_offset, double[] d, int _d_offset, double[] du, int _du_offset, double[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    f2j.dgttrf(n, dl, _dl_offset, d, _d_offset, du, _du_offset, du2, _du2_offset, ipiv, _ipiv_offset, info);
  }

  public void dgttrs(String trans, int n, int nrhs, double[] dl, double[] d, double[] du, double[] du2, int[] ipiv, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dgttrs(trans, n, nrhs, dl, 0, d, 0, du, 0, du2, 0, ipiv, 0, b, 0, ldb, info);
  }

  public void dgttrs(String trans, int n, int nrhs, double[] dl, int _dl_offset, double[] d, int _d_offset, double[] du, int _du_offset, double[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dgttrs(trans, n, nrhs, dl, _dl_offset, d, _d_offset, du, _du_offset, du2, _du2_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  public void dgtts2(int itrans, int n, int nrhs, double[] dl, double[] d, double[] du, double[] du2, int[] ipiv, double[] b, int ldb) {
    f2j.dgtts2(itrans, n, nrhs, dl, 0, d, 0, du, 0, du2, 0, ipiv, 0, b, 0, ldb);
  }

  public void dgtts2(int itrans, int n, int nrhs, double[] dl, int _dl_offset, double[] d, int _d_offset, double[] du, int _du_offset, double[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb) {
    f2j.dgtts2(itrans, n, nrhs, dl, _dl_offset, d, _d_offset, du, _du_offset, du2, _du2_offset, ipiv, _ipiv_offset, b, _b_offset, ldb);
  }

  public void dhgeqz(String job, String compq, String compz, int n, int ilo, int ihi, double[] h, int ldh, double[] t, int ldt, double[] alphar, double[] alphai, double[] beta, double[] q, int ldq, double[] z, int ldz, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dhgeqz(job, compq, compz, n, ilo, ihi, h, 0, ldh, t, 0, ldt, alphar, 0, alphai, 0, beta, 0, q, 0, ldq, z, 0, ldz, work, 0, lwork, info);
  }

  public void dhgeqz(String job, String compq, String compz, int n, int ilo, int ihi, double[] h, int _h_offset, int ldh, double[] t, int _t_offset, int ldt, double[] alphar, int _alphar_offset, double[] alphai, int _alphai_offset, double[] beta, int _beta_offset, double[] q, int _q_offset, int ldq, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dhgeqz(job, compq, compz, n, ilo, ihi, h, _h_offset, ldh, t, _t_offset, ldt, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, q, _q_offset, ldq, z, _z_offset, ldz, work, _work_offset, lwork, info);
  }

  public void dhsein(String side, String eigsrc, String initv, boolean[] select, int n, double[] h, int ldh, double[] wr, double[] wi, double[] vl, int ldvl, double[] vr, int ldvr, int mm, org.netlib.util.intW m, double[] work, int[] ifaill, int[] ifailr, org.netlib.util.intW info) {
    f2j.dhsein(side, eigsrc, initv, select, 0, n, h, 0, ldh, wr, 0, wi, 0, vl, 0, ldvl, vr, 0, ldvr, mm, m, work, 0, ifaill, 0, ifailr, 0, info);
  }

  public void dhsein(String side, String eigsrc, String initv, boolean[] select, int _select_offset, int n, double[] h, int _h_offset, int ldh, double[] wr, int _wr_offset, double[] wi, int _wi_offset, double[] vl, int _vl_offset, int ldvl, double[] vr, int _vr_offset, int ldvr, int mm, org.netlib.util.intW m, double[] work, int _work_offset, int[] ifaill, int _ifaill_offset, int[] ifailr, int _ifailr_offset, org.netlib.util.intW info) {
    f2j.dhsein(side, eigsrc, initv, select, _select_offset, n, h, _h_offset, ldh, wr, _wr_offset, wi, _wi_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, mm, m, work, _work_offset, ifaill, _ifaill_offset, ifailr, _ifailr_offset, info);
  }

  public void dhseqr(String job, String compz, int n, int ilo, int ihi, double[] h, int ldh, double[] wr, double[] wi, double[] z, int ldz, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dhseqr(job, compz, n, ilo, ihi, h, 0, ldh, wr, 0, wi, 0, z, 0, ldz, work, 0, lwork, info);
  }

  public void dhseqr(String job, String compz, int n, int ilo, int ihi, double[] h, int _h_offset, int ldh, double[] wr, int _wr_offset, double[] wi, int _wi_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dhseqr(job, compz, n, ilo, ihi, h, _h_offset, ldh, wr, _wr_offset, wi, _wi_offset, z, _z_offset, ldz, work, _work_offset, lwork, info);
  }

  public boolean disnan(double din) {
    return f2j.disnan(din);
  }

  public void dlabad(org.netlib.util.doubleW small, org.netlib.util.doubleW large) {
    f2j.dlabad(small, large);
  }

  public void dlabrd(int m, int n, int nb, double[] a, int lda, double[] d, double[] e, double[] tauq, double[] taup, double[] x, int ldx, double[] y, int ldy) {
    f2j.dlabrd(m, n, nb, a, 0, lda, d, 0, e, 0, tauq, 0, taup, 0, x, 0, ldx, y, 0, ldy);
  }

  public void dlabrd(int m, int n, int nb, double[] a, int _a_offset, int lda, double[] d, int _d_offset, double[] e, int _e_offset, double[] tauq, int _tauq_offset, double[] taup, int _taup_offset, double[] x, int _x_offset, int ldx, double[] y, int _y_offset, int ldy) {
    f2j.dlabrd(m, n, nb, a, _a_offset, lda, d, _d_offset, e, _e_offset, tauq, _tauq_offset, taup, _taup_offset, x, _x_offset, ldx, y, _y_offset, ldy);
  }

  public void dlacn2(int n, double[] v, double[] x, int[] isgn, org.netlib.util.doubleW est, org.netlib.util.intW kase, int[] isave) {
    f2j.dlacn2(n, v, 0, x, 0, isgn, 0, est, kase, isave, 0);
  }

  public void dlacn2(int n, double[] v, int _v_offset, double[] x, int _x_offset, int[] isgn, int _isgn_offset, org.netlib.util.doubleW est, org.netlib.util.intW kase, int[] isave, int _isave_offset) {
    f2j.dlacn2(n, v, _v_offset, x, _x_offset, isgn, _isgn_offset, est, kase, isave, _isave_offset);
  }

  public void dlacon(int n, double[] v, double[] x, int[] isgn, org.netlib.util.doubleW est, org.netlib.util.intW kase) {
    f2j.dlacon(n, v, 0, x, 0, isgn, 0, est, kase);
  }

  public void dlacon(int n, double[] v, int _v_offset, double[] x, int _x_offset, int[] isgn, int _isgn_offset, org.netlib.util.doubleW est, org.netlib.util.intW kase) {
    f2j.dlacon(n, v, _v_offset, x, _x_offset, isgn, _isgn_offset, est, kase);
  }

  public void dlacpy(String uplo, int m, int n, double[] a, int lda, double[] b, int ldb) {
    f2j.dlacpy(uplo, m, n, a, 0, lda, b, 0, ldb);
  }

  public void dlacpy(String uplo, int m, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb) {
    f2j.dlacpy(uplo, m, n, a, _a_offset, lda, b, _b_offset, ldb);
  }

  public void dladiv(double a, double b, double c, double d, org.netlib.util.doubleW p, org.netlib.util.doubleW q) {
    f2j.dladiv(a, b, c, d, p, q);
  }

  public void dlae2(double a, double b, double c, org.netlib.util.doubleW rt1, org.netlib.util.doubleW rt2) {
    f2j.dlae2(a, b, c, rt1, rt2);
  }

  public void dlaebz(int ijob, int nitmax, int n, int mmax, int minp, int nbmin, double abstol, double reltol, double pivmin, double[] d, double[] e, double[] e2, int[] nval, double[] ab, double[] c, org.netlib.util.intW mout, int[] nab, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dlaebz(ijob, nitmax, n, mmax, minp, nbmin, abstol, reltol, pivmin, d, 0, e, 0, e2, 0, nval, 0, ab, 0, c, 0, mout, nab, 0, work, 0, iwork, 0, info);
  }

  public void dlaebz(int ijob, int nitmax, int n, int mmax, int minp, int nbmin, double abstol, double reltol, double pivmin, double[] d, int _d_offset, double[] e, int _e_offset, double[] e2, int _e2_offset, int[] nval, int _nval_offset, double[] ab, int _ab_offset, double[] c, int _c_offset, org.netlib.util.intW mout, int[] nab, int _nab_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dlaebz(ijob, nitmax, n, mmax, minp, nbmin, abstol, reltol, pivmin, d, _d_offset, e, _e_offset, e2, _e2_offset, nval, _nval_offset, ab, _ab_offset, c, _c_offset, mout, nab, _nab_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dlaed0(int icompq, int qsiz, int n, double[] d, double[] e, double[] q, int ldq, double[] qstore, int ldqs, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dlaed0(icompq, qsiz, n, d, 0, e, 0, q, 0, ldq, qstore, 0, ldqs, work, 0, iwork, 0, info);
  }

  public void dlaed0(int icompq, int qsiz, int n, double[] d, int _d_offset, double[] e, int _e_offset, double[] q, int _q_offset, int ldq, double[] qstore, int _qstore_offset, int ldqs, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dlaed0(icompq, qsiz, n, d, _d_offset, e, _e_offset, q, _q_offset, ldq, qstore, _qstore_offset, ldqs, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dlaed1(int n, double[] d, double[] q, int ldq, int[] indxq, org.netlib.util.doubleW rho, int cutpnt, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dlaed1(n, d, 0, q, 0, ldq, indxq, 0, rho, cutpnt, work, 0, iwork, 0, info);
  }

  public void dlaed1(int n, double[] d, int _d_offset, double[] q, int _q_offset, int ldq, int[] indxq, int _indxq_offset, org.netlib.util.doubleW rho, int cutpnt, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dlaed1(n, d, _d_offset, q, _q_offset, ldq, indxq, _indxq_offset, rho, cutpnt, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dlaed2(org.netlib.util.intW k, int n, int n1, double[] d, double[] q, int ldq, int[] indxq, org.netlib.util.doubleW rho, double[] z, double[] dlamda, double[] w, double[] q2, int[] indx, int[] indxc, int[] indxp, int[] coltyp, org.netlib.util.intW info) {
    f2j.dlaed2(k, n, n1, d, 0, q, 0, ldq, indxq, 0, rho, z, 0, dlamda, 0, w, 0, q2, 0, indx, 0, indxc, 0, indxp, 0, coltyp, 0, info);
  }

  public void dlaed2(org.netlib.util.intW k, int n, int n1, double[] d, int _d_offset, double[] q, int _q_offset, int ldq, int[] indxq, int _indxq_offset, org.netlib.util.doubleW rho, double[] z, int _z_offset, double[] dlamda, int _dlamda_offset, double[] w, int _w_offset, double[] q2, int _q2_offset, int[] indx, int _indx_offset, int[] indxc, int _indxc_offset, int[] indxp, int _indxp_offset, int[] coltyp, int _coltyp_offset, org.netlib.util.intW info) {
    f2j.dlaed2(k, n, n1, d, _d_offset, q, _q_offset, ldq, indxq, _indxq_offset, rho, z, _z_offset, dlamda, _dlamda_offset, w, _w_offset, q2, _q2_offset, indx, _indx_offset, indxc, _indxc_offset, indxp, _indxp_offset, coltyp, _coltyp_offset, info);
  }

  public void dlaed3(int k, int n, int n1, double[] d, double[] q, int ldq, double rho, double[] dlamda, double[] q2, int[] indx, int[] ctot, double[] w, double[] s, org.netlib.util.intW info) {
    f2j.dlaed3(k, n, n1, d, 0, q, 0, ldq, rho, dlamda, 0, q2, 0, indx, 0, ctot, 0, w, 0, s, 0, info);
  }

  public void dlaed3(int k, int n, int n1, double[] d, int _d_offset, double[] q, int _q_offset, int ldq, double rho, double[] dlamda, int _dlamda_offset, double[] q2, int _q2_offset, int[] indx, int _indx_offset, int[] ctot, int _ctot_offset, double[] w, int _w_offset, double[] s, int _s_offset, org.netlib.util.intW info) {
    f2j.dlaed3(k, n, n1, d, _d_offset, q, _q_offset, ldq, rho, dlamda, _dlamda_offset, q2, _q2_offset, indx, _indx_offset, ctot, _ctot_offset, w, _w_offset, s, _s_offset, info);
  }

  public void dlaed4(int n, int i, double[] d, double[] z, double[] delta, double rho, org.netlib.util.doubleW dlam, org.netlib.util.intW info) {
    f2j.dlaed4(n, i, d, 0, z, 0, delta, 0, rho, dlam, info);
  }

  public void dlaed4(int n, int i, double[] d, int _d_offset, double[] z, int _z_offset, double[] delta, int _delta_offset, double rho, org.netlib.util.doubleW dlam, org.netlib.util.intW info) {
    f2j.dlaed4(n, i, d, _d_offset, z, _z_offset, delta, _delta_offset, rho, dlam, info);
  }

  public void dlaed5(int i, double[] d, double[] z, double[] delta, double rho, org.netlib.util.doubleW dlam) {
    f2j.dlaed5(i, d, 0, z, 0, delta, 0, rho, dlam);
  }

  public void dlaed5(int i, double[] d, int _d_offset, double[] z, int _z_offset, double[] delta, int _delta_offset, double rho, org.netlib.util.doubleW dlam) {
    f2j.dlaed5(i, d, _d_offset, z, _z_offset, delta, _delta_offset, rho, dlam);
  }

  public void dlaed6(int kniter, boolean orgati, double rho, double[] d, double[] z, double finit, org.netlib.util.doubleW tau, org.netlib.util.intW info) {
    f2j.dlaed6(kniter, orgati, rho, d, 0, z, 0, finit, tau, info);
  }

  public void dlaed6(int kniter, boolean orgati, double rho, double[] d, int _d_offset, double[] z, int _z_offset, double finit, org.netlib.util.doubleW tau, org.netlib.util.intW info) {
    f2j.dlaed6(kniter, orgati, rho, d, _d_offset, z, _z_offset, finit, tau, info);
  }

  public void dlaed7(int icompq, int n, int qsiz, int tlvls, int curlvl, int curpbm, double[] d, double[] q, int ldq, int[] indxq, org.netlib.util.doubleW rho, int cutpnt, double[] qstore, int[] qptr, int[] prmptr, int[] perm, int[] givptr, int[] givcol, double[] givnum, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dlaed7(icompq, n, qsiz, tlvls, curlvl, curpbm, d, 0, q, 0, ldq, indxq, 0, rho, cutpnt, qstore, 0, qptr, 0, prmptr, 0, perm, 0, givptr, 0, givcol, 0, givnum, 0, work, 0, iwork, 0, info);
  }

  public void dlaed7(int icompq, int n, int qsiz, int tlvls, int curlvl, int curpbm, double[] d, int _d_offset, double[] q, int _q_offset, int ldq, int[] indxq, int _indxq_offset, org.netlib.util.doubleW rho, int cutpnt, double[] qstore, int _qstore_offset, int[] qptr, int _qptr_offset, int[] prmptr, int _prmptr_offset, int[] perm, int _perm_offset, int[] givptr, int _givptr_offset, int[] givcol, int _givcol_offset, double[] givnum, int _givnum_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dlaed7(icompq, n, qsiz, tlvls, curlvl, curpbm, d, _d_offset, q, _q_offset, ldq, indxq, _indxq_offset, rho, cutpnt, qstore, _qstore_offset, qptr, _qptr_offset, prmptr, _prmptr_offset, perm, _perm_offset, givptr, _givptr_offset, givcol, _givcol_offset, givnum, _givnum_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dlaed8(int icompq, org.netlib.util.intW k, int n, int qsiz, double[] d, double[] q, int ldq, int[] indxq, org.netlib.util.doubleW rho, int cutpnt, double[] z, double[] dlamda, double[] q2, int ldq2, double[] w, int[] perm, org.netlib.util.intW givptr, int[] givcol, double[] givnum, int[] indxp, int[] indx, org.netlib.util.intW info) {
    f2j.dlaed8(icompq, k, n, qsiz, d, 0, q, 0, ldq, indxq, 0, rho, cutpnt, z, 0, dlamda, 0, q2, 0, ldq2, w, 0, perm, 0, givptr, givcol, 0, givnum, 0, indxp, 0, indx, 0, info);
  }

  public void dlaed8(int icompq, org.netlib.util.intW k, int n, int qsiz, double[] d, int _d_offset, double[] q, int _q_offset, int ldq, int[] indxq, int _indxq_offset, org.netlib.util.doubleW rho, int cutpnt, double[] z, int _z_offset, double[] dlamda, int _dlamda_offset, double[] q2, int _q2_offset, int ldq2, double[] w, int _w_offset, int[] perm, int _perm_offset, org.netlib.util.intW givptr, int[] givcol, int _givcol_offset, double[] givnum, int _givnum_offset, int[] indxp, int _indxp_offset, int[] indx, int _indx_offset, org.netlib.util.intW info) {
    f2j.dlaed8(icompq, k, n, qsiz, d, _d_offset, q, _q_offset, ldq, indxq, _indxq_offset, rho, cutpnt, z, _z_offset, dlamda, _dlamda_offset, q2, _q2_offset, ldq2, w, _w_offset, perm, _perm_offset, givptr, givcol, _givcol_offset, givnum, _givnum_offset, indxp, _indxp_offset, indx, _indx_offset, info);
  }

  public void dlaed9(int k, int kstart, int kstop, int n, double[] d, double[] q, int ldq, double rho, double[] dlamda, double[] w, double[] s, int lds, org.netlib.util.intW info) {
    f2j.dlaed9(k, kstart, kstop, n, d, 0, q, 0, ldq, rho, dlamda, 0, w, 0, s, 0, lds, info);
  }

  public void dlaed9(int k, int kstart, int kstop, int n, double[] d, int _d_offset, double[] q, int _q_offset, int ldq, double rho, double[] dlamda, int _dlamda_offset, double[] w, int _w_offset, double[] s, int _s_offset, int lds, org.netlib.util.intW info) {
    f2j.dlaed9(k, kstart, kstop, n, d, _d_offset, q, _q_offset, ldq, rho, dlamda, _dlamda_offset, w, _w_offset, s, _s_offset, lds, info);
  }

  public void dlaeda(int n, int tlvls, int curlvl, int curpbm, int[] prmptr, int[] perm, int[] givptr, int[] givcol, double[] givnum, double[] q, int[] qptr, double[] z, double[] ztemp, org.netlib.util.intW info) {
    f2j.dlaeda(n, tlvls, curlvl, curpbm, prmptr, 0, perm, 0, givptr, 0, givcol, 0, givnum, 0, q, 0, qptr, 0, z, 0, ztemp, 0, info);
  }

  public void dlaeda(int n, int tlvls, int curlvl, int curpbm, int[] prmptr, int _prmptr_offset, int[] perm, int _perm_offset, int[] givptr, int _givptr_offset, int[] givcol, int _givcol_offset, double[] givnum, int _givnum_offset, double[] q, int _q_offset, int[] qptr, int _qptr_offset, double[] z, int _z_offset, double[] ztemp, int _ztemp_offset, org.netlib.util.intW info) {
    f2j.dlaeda(n, tlvls, curlvl, curpbm, prmptr, _prmptr_offset, perm, _perm_offset, givptr, _givptr_offset, givcol, _givcol_offset, givnum, _givnum_offset, q, _q_offset, qptr, _qptr_offset, z, _z_offset, ztemp, _ztemp_offset, info);
  }

  public void dlaein(boolean rightv, boolean noinit, int n, double[] h, int ldh, double wr, double wi, double[] vr, double[] vi, double[] b, int ldb, double[] work, double eps3, double smlnum, double bignum, org.netlib.util.intW info) {
    f2j.dlaein(rightv, noinit, n, h, 0, ldh, wr, wi, vr, 0, vi, 0, b, 0, ldb, work, 0, eps3, smlnum, bignum, info);
  }

  public void dlaein(boolean rightv, boolean noinit, int n, double[] h, int _h_offset, int ldh, double wr, double wi, double[] vr, int _vr_offset, double[] vi, int _vi_offset, double[] b, int _b_offset, int ldb, double[] work, int _work_offset, double eps3, double smlnum, double bignum, org.netlib.util.intW info) {
    f2j.dlaein(rightv, noinit, n, h, _h_offset, ldh, wr, wi, vr, _vr_offset, vi, _vi_offset, b, _b_offset, ldb, work, _work_offset, eps3, smlnum, bignum, info);
  }

  public void dlaev2(double a, double b, double c, org.netlib.util.doubleW rt1, org.netlib.util.doubleW rt2, org.netlib.util.doubleW cs1, org.netlib.util.doubleW sn1) {
    f2j.dlaev2(a, b, c, rt1, rt2, cs1, sn1);
  }

  public void dlaexc(boolean wantq, int n, double[] t, int ldt, double[] q, int ldq, int j1, int n1, int n2, double[] work, org.netlib.util.intW info) {
    f2j.dlaexc(wantq, n, t, 0, ldt, q, 0, ldq, j1, n1, n2, work, 0, info);
  }

  public void dlaexc(boolean wantq, int n, double[] t, int _t_offset, int ldt, double[] q, int _q_offset, int ldq, int j1, int n1, int n2, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dlaexc(wantq, n, t, _t_offset, ldt, q, _q_offset, ldq, j1, n1, n2, work, _work_offset, info);
  }

  public void dlag2(double[] a, int lda, double[] b, int ldb, double safmin, org.netlib.util.doubleW scale1, org.netlib.util.doubleW scale2, org.netlib.util.doubleW wr1, org.netlib.util.doubleW wr2, org.netlib.util.doubleW wi) {
    f2j.dlag2(a, 0, lda, b, 0, ldb, safmin, scale1, scale2, wr1, wr2, wi);
  }

  public void dlag2(double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double safmin, org.netlib.util.doubleW scale1, org.netlib.util.doubleW scale2, org.netlib.util.doubleW wr1, org.netlib.util.doubleW wr2, org.netlib.util.doubleW wi) {
    f2j.dlag2(a, _a_offset, lda, b, _b_offset, ldb, safmin, scale1, scale2, wr1, wr2, wi);
  }

  public void dlag2s(int m, int n, double[] a, int lda, float[] sa, int ldsa, org.netlib.util.intW info) {
    f2j.dlag2s(m, n, a, 0, lda, sa, 0, ldsa, info);
  }

  public void dlag2s(int m, int n, double[] a, int _a_offset, int lda, float[] sa, int _sa_offset, int ldsa, org.netlib.util.intW info) {
    f2j.dlag2s(m, n, a, _a_offset, lda, sa, _sa_offset, ldsa, info);
  }

  public void dlags2(boolean upper, double a1, double a2, double a3, double b1, double b2, double b3, org.netlib.util.doubleW csu, org.netlib.util.doubleW snu, org.netlib.util.doubleW csv, org.netlib.util.doubleW snv, org.netlib.util.doubleW csq, org.netlib.util.doubleW snq) {
    f2j.dlags2(upper, a1, a2, a3, b1, b2, b3, csu, snu, csv, snv, csq, snq);
  }

  public void dlagtf(int n, double[] a, double lambda, double[] b, double[] c, double tol, double[] d, int[] in, org.netlib.util.intW info) {
    f2j.dlagtf(n, a, 0, lambda, b, 0, c, 0, tol, d, 0, in, 0, info);
  }

  public void dlagtf(int n, double[] a, int _a_offset, double lambda, double[] b, int _b_offset, double[] c, int _c_offset, double tol, double[] d, int _d_offset, int[] in, int _in_offset, org.netlib.util.intW info) {
    f2j.dlagtf(n, a, _a_offset, lambda, b, _b_offset, c, _c_offset, tol, d, _d_offset, in, _in_offset, info);
  }

  public void dlagtm(String trans, int n, int nrhs, double alpha, double[] dl, double[] d, double[] du, double[] x, int ldx, double beta, double[] b, int ldb) {
    f2j.dlagtm(trans, n, nrhs, alpha, dl, 0, d, 0, du, 0, x, 0, ldx, beta, b, 0, ldb);
  }

  public void dlagtm(String trans, int n, int nrhs, double alpha, double[] dl, int _dl_offset, double[] d, int _d_offset, double[] du, int _du_offset, double[] x, int _x_offset, int ldx, double beta, double[] b, int _b_offset, int ldb) {
    f2j.dlagtm(trans, n, nrhs, alpha, dl, _dl_offset, d, _d_offset, du, _du_offset, x, _x_offset, ldx, beta, b, _b_offset, ldb);
  }

  public void dlagts(int job, int n, double[] a, double[] b, double[] c, double[] d, int[] in, double[] y, org.netlib.util.doubleW tol, org.netlib.util.intW info) {
    f2j.dlagts(job, n, a, 0, b, 0, c, 0, d, 0, in, 0, y, 0, tol, info);
  }

  public void dlagts(int job, int n, double[] a, int _a_offset, double[] b, int _b_offset, double[] c, int _c_offset, double[] d, int _d_offset, int[] in, int _in_offset, double[] y, int _y_offset, org.netlib.util.doubleW tol, org.netlib.util.intW info) {
    f2j.dlagts(job, n, a, _a_offset, b, _b_offset, c, _c_offset, d, _d_offset, in, _in_offset, y, _y_offset, tol, info);
  }

  public void dlagv2(double[] a, int lda, double[] b, int ldb, double[] alphar, double[] alphai, double[] beta, org.netlib.util.doubleW csl, org.netlib.util.doubleW snl, org.netlib.util.doubleW csr, org.netlib.util.doubleW snr) {
    f2j.dlagv2(a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, csl, snl, csr, snr);
  }

  public void dlagv2(double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] alphar, int _alphar_offset, double[] alphai, int _alphai_offset, double[] beta, int _beta_offset, org.netlib.util.doubleW csl, org.netlib.util.doubleW snl, org.netlib.util.doubleW csr, org.netlib.util.doubleW snr) {
    f2j.dlagv2(a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, csl, snl, csr, snr);
  }

  public void dlahqr(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int ldh, double[] wr, double[] wi, int iloz, int ihiz, double[] z, int ldz, org.netlib.util.intW info) {
    f2j.dlahqr(wantt, wantz, n, ilo, ihi, h, 0, ldh, wr, 0, wi, 0, iloz, ihiz, z, 0, ldz, info);
  }

  public void dlahqr(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int _h_offset, int ldh, double[] wr, int _wr_offset, double[] wi, int _wi_offset, int iloz, int ihiz, double[] z, int _z_offset, int ldz, org.netlib.util.intW info) {
    f2j.dlahqr(wantt, wantz, n, ilo, ihi, h, _h_offset, ldh, wr, _wr_offset, wi, _wi_offset, iloz, ihiz, z, _z_offset, ldz, info);
  }

  public void dlahr2(int n, int k, int nb, double[] a, int lda, double[] tau, double[] t, int ldt, double[] y, int ldy) {
    f2j.dlahr2(n, k, nb, a, 0, lda, tau, 0, t, 0, ldt, y, 0, ldy);
  }

  public void dlahr2(int n, int k, int nb, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] t, int _t_offset, int ldt, double[] y, int _y_offset, int ldy) {
    f2j.dlahr2(n, k, nb, a, _a_offset, lda, tau, _tau_offset, t, _t_offset, ldt, y, _y_offset, ldy);
  }

  public void dlahrd(int n, int k, int nb, double[] a, int lda, double[] tau, double[] t, int ldt, double[] y, int ldy) {
    f2j.dlahrd(n, k, nb, a, 0, lda, tau, 0, t, 0, ldt, y, 0, ldy);
  }

  public void dlahrd(int n, int k, int nb, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] t, int _t_offset, int ldt, double[] y, int _y_offset, int ldy) {
    f2j.dlahrd(n, k, nb, a, _a_offset, lda, tau, _tau_offset, t, _t_offset, ldt, y, _y_offset, ldy);
  }

  public void dlaic1(int job, int j, double[] x, double sest, double[] w, double gamma, org.netlib.util.doubleW sestpr, org.netlib.util.doubleW s, org.netlib.util.doubleW c) {
    f2j.dlaic1(job, j, x, 0, sest, w, 0, gamma, sestpr, s, c);
  }

  public void dlaic1(int job, int j, double[] x, int _x_offset, double sest, double[] w, int _w_offset, double gamma, org.netlib.util.doubleW sestpr, org.netlib.util.doubleW s, org.netlib.util.doubleW c) {
    f2j.dlaic1(job, j, x, _x_offset, sest, w, _w_offset, gamma, sestpr, s, c);
  }

  public boolean dlaisnan(double din1, double din2) {
    return f2j.dlaisnan(din1, din2);
  }

  public void dlaln2(boolean ltrans, int na, int nw, double smin, double ca, double[] a, int lda, double d1, double d2, double[] b, int ldb, double wr, double wi, double[] x, int ldx, org.netlib.util.doubleW scale, org.netlib.util.doubleW xnorm, org.netlib.util.intW info) {
    f2j.dlaln2(ltrans, na, nw, smin, ca, a, 0, lda, d1, d2, b, 0, ldb, wr, wi, x, 0, ldx, scale, xnorm, info);
  }

  public void dlaln2(boolean ltrans, int na, int nw, double smin, double ca, double[] a, int _a_offset, int lda, double d1, double d2, double[] b, int _b_offset, int ldb, double wr, double wi, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW scale, org.netlib.util.doubleW xnorm, org.netlib.util.intW info) {
    f2j.dlaln2(ltrans, na, nw, smin, ca, a, _a_offset, lda, d1, d2, b, _b_offset, ldb, wr, wi, x, _x_offset, ldx, scale, xnorm, info);
  }

  public void dlals0(int icompq, int nl, int nr, int sqre, int nrhs, double[] b, int ldb, double[] bx, int ldbx, int[] perm, int givptr, int[] givcol, int ldgcol, double[] givnum, int ldgnum, double[] poles, double[] difl, double[] difr, double[] z, int k, double c, double s, double[] work, org.netlib.util.intW info) {
    f2j.dlals0(icompq, nl, nr, sqre, nrhs, b, 0, ldb, bx, 0, ldbx, perm, 0, givptr, givcol, 0, ldgcol, givnum, 0, ldgnum, poles, 0, difl, 0, difr, 0, z, 0, k, c, s, work, 0, info);
  }

  public void dlals0(int icompq, int nl, int nr, int sqre, int nrhs, double[] b, int _b_offset, int ldb, double[] bx, int _bx_offset, int ldbx, int[] perm, int _perm_offset, int givptr, int[] givcol, int _givcol_offset, int ldgcol, double[] givnum, int _givnum_offset, int ldgnum, double[] poles, int _poles_offset, double[] difl, int _difl_offset, double[] difr, int _difr_offset, double[] z, int _z_offset, int k, double c, double s, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dlals0(icompq, nl, nr, sqre, nrhs, b, _b_offset, ldb, bx, _bx_offset, ldbx, perm, _perm_offset, givptr, givcol, _givcol_offset, ldgcol, givnum, _givnum_offset, ldgnum, poles, _poles_offset, difl, _difl_offset, difr, _difr_offset, z, _z_offset, k, c, s, work, _work_offset, info);
  }

  public void dlalsa(int icompq, int smlsiz, int n, int nrhs, double[] b, int ldb, double[] bx, int ldbx, double[] u, int ldu, double[] vt, int[] k, double[] difl, double[] difr, double[] z, double[] poles, int[] givptr, int[] givcol, int ldgcol, int[] perm, double[] givnum, double[] c, double[] s, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dlalsa(icompq, smlsiz, n, nrhs, b, 0, ldb, bx, 0, ldbx, u, 0, ldu, vt, 0, k, 0, difl, 0, difr, 0, z, 0, poles, 0, givptr, 0, givcol, 0, ldgcol, perm, 0, givnum, 0, c, 0, s, 0, work, 0, iwork, 0, info);
  }

  public void dlalsa(int icompq, int smlsiz, int n, int nrhs, double[] b, int _b_offset, int ldb, double[] bx, int _bx_offset, int ldbx, double[] u, int _u_offset, int ldu, double[] vt, int _vt_offset, int[] k, int _k_offset, double[] difl, int _difl_offset, double[] difr, int _difr_offset, double[] z, int _z_offset, double[] poles, int _poles_offset, int[] givptr, int _givptr_offset, int[] givcol, int _givcol_offset, int ldgcol, int[] perm, int _perm_offset, double[] givnum, int _givnum_offset, double[] c, int _c_offset, double[] s, int _s_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dlalsa(icompq, smlsiz, n, nrhs, b, _b_offset, ldb, bx, _bx_offset, ldbx, u, _u_offset, ldu, vt, _vt_offset, k, _k_offset, difl, _difl_offset, difr, _difr_offset, z, _z_offset, poles, _poles_offset, givptr, _givptr_offset, givcol, _givcol_offset, ldgcol, perm, _perm_offset, givnum, _givnum_offset, c, _c_offset, s, _s_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dlalsd(String uplo, int smlsiz, int n, int nrhs, double[] d, double[] e, double[] b, int ldb, double rcond, org.netlib.util.intW rank, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dlalsd(uplo, smlsiz, n, nrhs, d, 0, e, 0, b, 0, ldb, rcond, rank, work, 0, iwork, 0, info);
  }

  public void dlalsd(String uplo, int smlsiz, int n, int nrhs, double[] d, int _d_offset, double[] e, int _e_offset, double[] b, int _b_offset, int ldb, double rcond, org.netlib.util.intW rank, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dlalsd(uplo, smlsiz, n, nrhs, d, _d_offset, e, _e_offset, b, _b_offset, ldb, rcond, rank, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dlamrg(int n1, int n2, double[] a, int dtrd1, int dtrd2, int[] index) {
    f2j.dlamrg(n1, n2, a, 0, dtrd1, dtrd2, index, 0);
  }

  public void dlamrg(int n1, int n2, double[] a, int _a_offset, int dtrd1, int dtrd2, int[] index, int _index_offset) {
    f2j.dlamrg(n1, n2, a, _a_offset, dtrd1, dtrd2, index, _index_offset);
  }

  public int dlaneg(int n, double[] d, double[] lld, double sigma, double pivmin, int r) {
    return f2j.dlaneg(n, d, 0, lld, 0, sigma, pivmin, r);
  }

  public int dlaneg(int n, double[] d, int _d_offset, double[] lld, int _lld_offset, double sigma, double pivmin, int r) {
    return f2j.dlaneg(n, d, _d_offset, lld, _lld_offset, sigma, pivmin, r);
  }

  public double dlangb(String norm, int n, int kl, int ku, double[] ab, int ldab, double[] work) {
    return f2j.dlangb(norm, n, kl, ku, ab, 0, ldab, work, 0);
  }

  public double dlangb(String norm, int n, int kl, int ku, double[] ab, int _ab_offset, int ldab, double[] work, int _work_offset) {
    return f2j.dlangb(norm, n, kl, ku, ab, _ab_offset, ldab, work, _work_offset);
  }

  public double dlange(String norm, int m, int n, double[] a, int lda, double[] work) {
    return f2j.dlange(norm, m, n, a, 0, lda, work, 0);
  }

  public double dlange(String norm, int m, int n, double[] a, int _a_offset, int lda, double[] work, int _work_offset) {
    return f2j.dlange(norm, m, n, a, _a_offset, lda, work, _work_offset);
  }

  public double dlangt(String norm, int n, double[] dl, double[] d, double[] du) {
    return f2j.dlangt(norm, n, dl, 0, d, 0, du, 0);
  }

  public double dlangt(String norm, int n, double[] dl, int _dl_offset, double[] d, int _d_offset, double[] du, int _du_offset) {
    return f2j.dlangt(norm, n, dl, _dl_offset, d, _d_offset, du, _du_offset);
  }

  public double dlanhs(String norm, int n, double[] a, int lda, double[] work) {
    return f2j.dlanhs(norm, n, a, 0, lda, work, 0);
  }

  public double dlanhs(String norm, int n, double[] a, int _a_offset, int lda, double[] work, int _work_offset) {
    return f2j.dlanhs(norm, n, a, _a_offset, lda, work, _work_offset);
  }

  public double dlansb(String norm, String uplo, int n, int k, double[] ab, int ldab, double[] work) {
    return f2j.dlansb(norm, uplo, n, k, ab, 0, ldab, work, 0);
  }

  public double dlansb(String norm, String uplo, int n, int k, double[] ab, int _ab_offset, int ldab, double[] work, int _work_offset) {
    return f2j.dlansb(norm, uplo, n, k, ab, _ab_offset, ldab, work, _work_offset);
  }

  public double dlansp(String norm, String uplo, int n, double[] ap, double[] work) {
    return f2j.dlansp(norm, uplo, n, ap, 0, work, 0);
  }

  public double dlansp(String norm, String uplo, int n, double[] ap, int _ap_offset, double[] work, int _work_offset) {
    return f2j.dlansp(norm, uplo, n, ap, _ap_offset, work, _work_offset);
  }

  public double dlanst(String norm, int n, double[] d, double[] e) {
    return f2j.dlanst(norm, n, d, 0, e, 0);
  }

  public double dlanst(String norm, int n, double[] d, int _d_offset, double[] e, int _e_offset) {
    return f2j.dlanst(norm, n, d, _d_offset, e, _e_offset);
  }

  public double dlansy(String norm, String uplo, int n, double[] a, int lda, double[] work) {
    return f2j.dlansy(norm, uplo, n, a, 0, lda, work, 0);
  }

  public double dlansy(String norm, String uplo, int n, double[] a, int _a_offset, int lda, double[] work, int _work_offset) {
    return f2j.dlansy(norm, uplo, n, a, _a_offset, lda, work, _work_offset);
  }

  public double dlantb(String norm, String uplo, String diag, int n, int k, double[] ab, int ldab, double[] work) {
    return f2j.dlantb(norm, uplo, diag, n, k, ab, 0, ldab, work, 0);
  }

  public double dlantb(String norm, String uplo, String diag, int n, int k, double[] ab, int _ab_offset, int ldab, double[] work, int _work_offset) {
    return f2j.dlantb(norm, uplo, diag, n, k, ab, _ab_offset, ldab, work, _work_offset);
  }

  public double dlantp(String norm, String uplo, String diag, int n, double[] ap, double[] work) {
    return f2j.dlantp(norm, uplo, diag, n, ap, 0, work, 0);
  }

  public double dlantp(String norm, String uplo, String diag, int n, double[] ap, int _ap_offset, double[] work, int _work_offset) {
    return f2j.dlantp(norm, uplo, diag, n, ap, _ap_offset, work, _work_offset);
  }

  public double dlantr(String norm, String uplo, String diag, int m, int n, double[] a, int lda, double[] work) {
    return f2j.dlantr(norm, uplo, diag, m, n, a, 0, lda, work, 0);
  }

  public double dlantr(String norm, String uplo, String diag, int m, int n, double[] a, int _a_offset, int lda, double[] work, int _work_offset) {
    return f2j.dlantr(norm, uplo, diag, m, n, a, _a_offset, lda, work, _work_offset);
  }

  public void dlanv2(org.netlib.util.doubleW a, org.netlib.util.doubleW b, org.netlib.util.doubleW c, org.netlib.util.doubleW d, org.netlib.util.doubleW rt1r, org.netlib.util.doubleW rt1i, org.netlib.util.doubleW rt2r, org.netlib.util.doubleW rt2i, org.netlib.util.doubleW cs, org.netlib.util.doubleW sn) {
    f2j.dlanv2(a, b, c, d, rt1r, rt1i, rt2r, rt2i, cs, sn);
  }

  public void dlapll(int n, double[] x, int incx, double[] y, int incy, org.netlib.util.doubleW ssmin) {
    f2j.dlapll(n, x, 0, incx, y, 0, incy, ssmin);
  }

  public void dlapll(int n, double[] x, int _x_offset, int incx, double[] y, int _y_offset, int incy, org.netlib.util.doubleW ssmin) {
    f2j.dlapll(n, x, _x_offset, incx, y, _y_offset, incy, ssmin);
  }

  public void dlapmt(boolean forwrd, int m, int n, double[] x, int ldx, int[] k) {
    f2j.dlapmt(forwrd, m, n, x, 0, ldx, k, 0);
  }

  public void dlapmt(boolean forwrd, int m, int n, double[] x, int _x_offset, int ldx, int[] k, int _k_offset) {
    f2j.dlapmt(forwrd, m, n, x, _x_offset, ldx, k, _k_offset);
  }

  public double dlapy2(double x, double y) {
    return f2j.dlapy2(x, y);
  }

  public double dlapy3(double x, double y, double z) {
    return f2j.dlapy3(x, y, z);
  }

  public void dlaqgb(int m, int n, int kl, int ku, double[] ab, int ldab, double[] r, double[] c, double rowcnd, double colcnd, double amax, org.netlib.util.StringW equed) {
    f2j.dlaqgb(m, n, kl, ku, ab, 0, ldab, r, 0, c, 0, rowcnd, colcnd, amax, equed);
  }

  public void dlaqgb(int m, int n, int kl, int ku, double[] ab, int _ab_offset, int ldab, double[] r, int _r_offset, double[] c, int _c_offset, double rowcnd, double colcnd, double amax, org.netlib.util.StringW equed) {
    f2j.dlaqgb(m, n, kl, ku, ab, _ab_offset, ldab, r, _r_offset, c, _c_offset, rowcnd, colcnd, amax, equed);
  }

  public void dlaqge(int m, int n, double[] a, int lda, double[] r, double[] c, double rowcnd, double colcnd, double amax, org.netlib.util.StringW equed) {
    f2j.dlaqge(m, n, a, 0, lda, r, 0, c, 0, rowcnd, colcnd, amax, equed);
  }

  public void dlaqge(int m, int n, double[] a, int _a_offset, int lda, double[] r, int _r_offset, double[] c, int _c_offset, double rowcnd, double colcnd, double amax, org.netlib.util.StringW equed) {
    f2j.dlaqge(m, n, a, _a_offset, lda, r, _r_offset, c, _c_offset, rowcnd, colcnd, amax, equed);
  }

  public void dlaqp2(int m, int n, int offset, double[] a, int lda, int[] jpvt, double[] tau, double[] vn1, double[] vn2, double[] work) {
    f2j.dlaqp2(m, n, offset, a, 0, lda, jpvt, 0, tau, 0, vn1, 0, vn2, 0, work, 0);
  }

  public void dlaqp2(int m, int n, int offset, double[] a, int _a_offset, int lda, int[] jpvt, int _jpvt_offset, double[] tau, int _tau_offset, double[] vn1, int _vn1_offset, double[] vn2, int _vn2_offset, double[] work, int _work_offset) {
    f2j.dlaqp2(m, n, offset, a, _a_offset, lda, jpvt, _jpvt_offset, tau, _tau_offset, vn1, _vn1_offset, vn2, _vn2_offset, work, _work_offset);
  }

  public void dlaqps(int m, int n, int offset, int nb, org.netlib.util.intW kb, double[] a, int lda, int[] jpvt, double[] tau, double[] vn1, double[] vn2, double[] auxv, double[] f, int ldf) {
    f2j.dlaqps(m, n, offset, nb, kb, a, 0, lda, jpvt, 0, tau, 0, vn1, 0, vn2, 0, auxv, 0, f, 0, ldf);
  }

  public void dlaqps(int m, int n, int offset, int nb, org.netlib.util.intW kb, double[] a, int _a_offset, int lda, int[] jpvt, int _jpvt_offset, double[] tau, int _tau_offset, double[] vn1, int _vn1_offset, double[] vn2, int _vn2_offset, double[] auxv, int _auxv_offset, double[] f, int _f_offset, int ldf) {
    f2j.dlaqps(m, n, offset, nb, kb, a, _a_offset, lda, jpvt, _jpvt_offset, tau, _tau_offset, vn1, _vn1_offset, vn2, _vn2_offset, auxv, _auxv_offset, f, _f_offset, ldf);
  }

  public void dlaqr0(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int ldh, double[] wr, double[] wi, int iloz, int ihiz, double[] z, int ldz, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dlaqr0(wantt, wantz, n, ilo, ihi, h, 0, ldh, wr, 0, wi, 0, iloz, ihiz, z, 0, ldz, work, 0, lwork, info);
  }

  public void dlaqr0(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int _h_offset, int ldh, double[] wr, int _wr_offset, double[] wi, int _wi_offset, int iloz, int ihiz, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dlaqr0(wantt, wantz, n, ilo, ihi, h, _h_offset, ldh, wr, _wr_offset, wi, _wi_offset, iloz, ihiz, z, _z_offset, ldz, work, _work_offset, lwork, info);
  }

  public void dlaqr1(int n, double[] h, int ldh, double sr1, double si1, double sr2, double si2, double[] v) {
    f2j.dlaqr1(n, h, 0, ldh, sr1, si1, sr2, si2, v, 0);
  }

  public void dlaqr1(int n, double[] h, int _h_offset, int ldh, double sr1, double si1, double sr2, double si2, double[] v, int _v_offset) {
    f2j.dlaqr1(n, h, _h_offset, ldh, sr1, si1, sr2, si2, v, _v_offset);
  }

  public void dlaqr2(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, double[] h, int ldh, int iloz, int ihiz, double[] z, int ldz, org.netlib.util.intW ns, org.netlib.util.intW nd, double[] sr, double[] si, double[] v, int ldv, int nh, double[] t, int ldt, int nv, double[] wv, int ldwv, double[] work, int lwork) {
    f2j.dlaqr2(wantt, wantz, n, ktop, kbot, nw, h, 0, ldh, iloz, ihiz, z, 0, ldz, ns, nd, sr, 0, si, 0, v, 0, ldv, nh, t, 0, ldt, nv, wv, 0, ldwv, work, 0, lwork);
  }

  public void dlaqr2(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, double[] h, int _h_offset, int ldh, int iloz, int ihiz, double[] z, int _z_offset, int ldz, org.netlib.util.intW ns, org.netlib.util.intW nd, double[] sr, int _sr_offset, double[] si, int _si_offset, double[] v, int _v_offset, int ldv, int nh, double[] t, int _t_offset, int ldt, int nv, double[] wv, int _wv_offset, int ldwv, double[] work, int _work_offset, int lwork) {
    f2j.dlaqr2(wantt, wantz, n, ktop, kbot, nw, h, _h_offset, ldh, iloz, ihiz, z, _z_offset, ldz, ns, nd, sr, _sr_offset, si, _si_offset, v, _v_offset, ldv, nh, t, _t_offset, ldt, nv, wv, _wv_offset, ldwv, work, _work_offset, lwork);
  }

  public void dlaqr3(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, double[] h, int ldh, int iloz, int ihiz, double[] z, int ldz, org.netlib.util.intW ns, org.netlib.util.intW nd, double[] sr, double[] si, double[] v, int ldv, int nh, double[] t, int ldt, int nv, double[] wv, int ldwv, double[] work, int lwork) {
    f2j.dlaqr3(wantt, wantz, n, ktop, kbot, nw, h, 0, ldh, iloz, ihiz, z, 0, ldz, ns, nd, sr, 0, si, 0, v, 0, ldv, nh, t, 0, ldt, nv, wv, 0, ldwv, work, 0, lwork);
  }

  public void dlaqr3(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, double[] h, int _h_offset, int ldh, int iloz, int ihiz, double[] z, int _z_offset, int ldz, org.netlib.util.intW ns, org.netlib.util.intW nd, double[] sr, int _sr_offset, double[] si, int _si_offset, double[] v, int _v_offset, int ldv, int nh, double[] t, int _t_offset, int ldt, int nv, double[] wv, int _wv_offset, int ldwv, double[] work, int _work_offset, int lwork) {
    f2j.dlaqr3(wantt, wantz, n, ktop, kbot, nw, h, _h_offset, ldh, iloz, ihiz, z, _z_offset, ldz, ns, nd, sr, _sr_offset, si, _si_offset, v, _v_offset, ldv, nh, t, _t_offset, ldt, nv, wv, _wv_offset, ldwv, work, _work_offset, lwork);
  }

  public void dlaqr4(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int ldh, double[] wr, double[] wi, int iloz, int ihiz, double[] z, int ldz, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dlaqr4(wantt, wantz, n, ilo, ihi, h, 0, ldh, wr, 0, wi, 0, iloz, ihiz, z, 0, ldz, work, 0, lwork, info);
  }

  public void dlaqr4(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int _h_offset, int ldh, double[] wr, int _wr_offset, double[] wi, int _wi_offset, int iloz, int ihiz, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dlaqr4(wantt, wantz, n, ilo, ihi, h, _h_offset, ldh, wr, _wr_offset, wi, _wi_offset, iloz, ihiz, z, _z_offset, ldz, work, _work_offset, lwork, info);
  }

  public void dlaqr5(boolean wantt, boolean wantz, int kacc22, int n, int ktop, int kbot, int nshfts, double[] sr, double[] si, double[] h, int ldh, int iloz, int ihiz, double[] z, int ldz, double[] v, int ldv, double[] u, int ldu, int nv, double[] wv, int ldwv, int nh, double[] wh, int ldwh) {
    f2j.dlaqr5(wantt, wantz, kacc22, n, ktop, kbot, nshfts, sr, 0, si, 0, h, 0, ldh, iloz, ihiz, z, 0, ldz, v, 0, ldv, u, 0, ldu, nv, wv, 0, ldwv, nh, wh, 0, ldwh);
  }

  public void dlaqr5(boolean wantt, boolean wantz, int kacc22, int n, int ktop, int kbot, int nshfts, double[] sr, int _sr_offset, double[] si, int _si_offset, double[] h, int _h_offset, int ldh, int iloz, int ihiz, double[] z, int _z_offset, int ldz, double[] v, int _v_offset, int ldv, double[] u, int _u_offset, int ldu, int nv, double[] wv, int _wv_offset, int ldwv, int nh, double[] wh, int _wh_offset, int ldwh) {
    f2j.dlaqr5(wantt, wantz, kacc22, n, ktop, kbot, nshfts, sr, _sr_offset, si, _si_offset, h, _h_offset, ldh, iloz, ihiz, z, _z_offset, ldz, v, _v_offset, ldv, u, _u_offset, ldu, nv, wv, _wv_offset, ldwv, nh, wh, _wh_offset, ldwh);
  }

  public void dlaqsb(String uplo, int n, int kd, double[] ab, int ldab, double[] s, double scond, double amax, org.netlib.util.StringW equed) {
    f2j.dlaqsb(uplo, n, kd, ab, 0, ldab, s, 0, scond, amax, equed);
  }

  public void dlaqsb(String uplo, int n, int kd, double[] ab, int _ab_offset, int ldab, double[] s, int _s_offset, double scond, double amax, org.netlib.util.StringW equed) {
    f2j.dlaqsb(uplo, n, kd, ab, _ab_offset, ldab, s, _s_offset, scond, amax, equed);
  }

  public void dlaqsp(String uplo, int n, double[] ap, double[] s, double scond, double amax, org.netlib.util.StringW equed) {
    f2j.dlaqsp(uplo, n, ap, 0, s, 0, scond, amax, equed);
  }

  public void dlaqsp(String uplo, int n, double[] ap, int _ap_offset, double[] s, int _s_offset, double scond, double amax, org.netlib.util.StringW equed) {
    f2j.dlaqsp(uplo, n, ap, _ap_offset, s, _s_offset, scond, amax, equed);
  }

  public void dlaqsy(String uplo, int n, double[] a, int lda, double[] s, double scond, double amax, org.netlib.util.StringW equed) {
    f2j.dlaqsy(uplo, n, a, 0, lda, s, 0, scond, amax, equed);
  }

  public void dlaqsy(String uplo, int n, double[] a, int _a_offset, int lda, double[] s, int _s_offset, double scond, double amax, org.netlib.util.StringW equed) {
    f2j.dlaqsy(uplo, n, a, _a_offset, lda, s, _s_offset, scond, amax, equed);
  }

  public void dlaqtr(boolean ltran, boolean lreal, int n, double[] t, int ldt, double[] b, double w, org.netlib.util.doubleW scale, double[] x, double[] work, org.netlib.util.intW info) {
    f2j.dlaqtr(ltran, lreal, n, t, 0, ldt, b, 0, w, scale, x, 0, work, 0, info);
  }

  public void dlaqtr(boolean ltran, boolean lreal, int n, double[] t, int _t_offset, int ldt, double[] b, int _b_offset, double w, org.netlib.util.doubleW scale, double[] x, int _x_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dlaqtr(ltran, lreal, n, t, _t_offset, ldt, b, _b_offset, w, scale, x, _x_offset, work, _work_offset, info);
  }

  public void dlar1v(int n, int b1, int bn, double lambda, double[] d, double[] l, double[] ld, double[] lld, double pivmin, double gaptol, double[] z, boolean wantnc, org.netlib.util.intW negcnt, org.netlib.util.doubleW ztz, org.netlib.util.doubleW mingma, org.netlib.util.intW r, int[] isuppz, org.netlib.util.doubleW nrminv, org.netlib.util.doubleW resid, org.netlib.util.doubleW rqcorr, double[] work) {
    f2j.dlar1v(n, b1, bn, lambda, d, 0, l, 0, ld, 0, lld, 0, pivmin, gaptol, z, 0, wantnc, negcnt, ztz, mingma, r, isuppz, 0, nrminv, resid, rqcorr, work, 0);
  }

  public void dlar1v(int n, int b1, int bn, double lambda, double[] d, int _d_offset, double[] l, int _l_offset, double[] ld, int _ld_offset, double[] lld, int _lld_offset, double pivmin, double gaptol, double[] z, int _z_offset, boolean wantnc, org.netlib.util.intW negcnt, org.netlib.util.doubleW ztz, org.netlib.util.doubleW mingma, org.netlib.util.intW r, int[] isuppz, int _isuppz_offset, org.netlib.util.doubleW nrminv, org.netlib.util.doubleW resid, org.netlib.util.doubleW rqcorr, double[] work, int _work_offset) {
    f2j.dlar1v(n, b1, bn, lambda, d, _d_offset, l, _l_offset, ld, _ld_offset, lld, _lld_offset, pivmin, gaptol, z, _z_offset, wantnc, negcnt, ztz, mingma, r, isuppz, _isuppz_offset, nrminv, resid, rqcorr, work, _work_offset);
  }

  public void dlar2v(int n, double[] x, double[] y, double[] z, int incx, double[] c, double[] s, int incc) {
    f2j.dlar2v(n, x, 0, y, 0, z, 0, incx, c, 0, s, 0, incc);
  }

  public void dlar2v(int n, double[] x, int _x_offset, double[] y, int _y_offset, double[] z, int _z_offset, int incx, double[] c, int _c_offset, double[] s, int _s_offset, int incc) {
    f2j.dlar2v(n, x, _x_offset, y, _y_offset, z, _z_offset, incx, c, _c_offset, s, _s_offset, incc);
  }

  public void dlarf(String side, int m, int n, double[] v, int incv, double tau, double[] c, int Ldc, double[] work) {
    f2j.dlarf(side, m, n, v, 0, incv, tau, c, 0, Ldc, work, 0);
  }

  public void dlarf(String side, int m, int n, double[] v, int _v_offset, int incv, double tau, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset) {
    f2j.dlarf(side, m, n, v, _v_offset, incv, tau, c, _c_offset, Ldc, work, _work_offset);
  }

  public void dlarfb(String side, String trans, String direct, String storev, int m, int n, int k, double[] v, int ldv, double[] t, int ldt, double[] c, int Ldc, double[] work, int ldwork) {
    f2j.dlarfb(side, trans, direct, storev, m, n, k, v, 0, ldv, t, 0, ldt, c, 0, Ldc, work, 0, ldwork);
  }

  public void dlarfb(String side, String trans, String direct, String storev, int m, int n, int k, double[] v, int _v_offset, int ldv, double[] t, int _t_offset, int ldt, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, int ldwork) {
    f2j.dlarfb(side, trans, direct, storev, m, n, k, v, _v_offset, ldv, t, _t_offset, ldt, c, _c_offset, Ldc, work, _work_offset, ldwork);
  }

  public void dlarfg(int n, org.netlib.util.doubleW alpha, double[] x, int incx, org.netlib.util.doubleW tau) {
    f2j.dlarfg(n, alpha, x, 0, incx, tau);
  }

  public void dlarfg(int n, org.netlib.util.doubleW alpha, double[] x, int _x_offset, int incx, org.netlib.util.doubleW tau) {
    f2j.dlarfg(n, alpha, x, _x_offset, incx, tau);
  }

  public void dlarft(String direct, String storev, int n, int k, double[] v, int ldv, double[] tau, double[] t, int ldt) {
    f2j.dlarft(direct, storev, n, k, v, 0, ldv, tau, 0, t, 0, ldt);
  }

  public void dlarft(String direct, String storev, int n, int k, double[] v, int _v_offset, int ldv, double[] tau, int _tau_offset, double[] t, int _t_offset, int ldt) {
    f2j.dlarft(direct, storev, n, k, v, _v_offset, ldv, tau, _tau_offset, t, _t_offset, ldt);
  }

  public void dlarfx(String side, int m, int n, double[] v, double tau, double[] c, int Ldc, double[] work) {
    f2j.dlarfx(side, m, n, v, 0, tau, c, 0, Ldc, work, 0);
  }

  public void dlarfx(String side, int m, int n, double[] v, int _v_offset, double tau, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset) {
    f2j.dlarfx(side, m, n, v, _v_offset, tau, c, _c_offset, Ldc, work, _work_offset);
  }

  public void dlargv(int n, double[] x, int incx, double[] y, int incy, double[] c, int incc) {
    f2j.dlargv(n, x, 0, incx, y, 0, incy, c, 0, incc);
  }

  public void dlargv(int n, double[] x, int _x_offset, int incx, double[] y, int _y_offset, int incy, double[] c, int _c_offset, int incc) {
    f2j.dlargv(n, x, _x_offset, incx, y, _y_offset, incy, c, _c_offset, incc);
  }

  public void dlarnv(int idist, int[] iseed, int n, double[] x) {
    f2j.dlarnv(idist, iseed, 0, n, x, 0);
  }

  public void dlarnv(int idist, int[] iseed, int _iseed_offset, int n, double[] x, int _x_offset) {
    f2j.dlarnv(idist, iseed, _iseed_offset, n, x, _x_offset);
  }

  public void dlarra(int n, double[] d, double[] e, double[] e2, double spltol, double tnrm, org.netlib.util.intW nsplit, int[] isplit, org.netlib.util.intW info) {
    f2j.dlarra(n, d, 0, e, 0, e2, 0, spltol, tnrm, nsplit, isplit, 0, info);
  }

  public void dlarra(int n, double[] d, int _d_offset, double[] e, int _e_offset, double[] e2, int _e2_offset, double spltol, double tnrm, org.netlib.util.intW nsplit, int[] isplit, int _isplit_offset, org.netlib.util.intW info) {
    f2j.dlarra(n, d, _d_offset, e, _e_offset, e2, _e2_offset, spltol, tnrm, nsplit, isplit, _isplit_offset, info);
  }

  public void dlarrb(int n, double[] d, double[] lld, int ifirst, int ilast, double rtol1, double rtol2, int offset, double[] w, double[] wgap, double[] werr, double[] work, int[] iwork, double pivmin, double spdiam, int twist, org.netlib.util.intW info) {
    f2j.dlarrb(n, d, 0, lld, 0, ifirst, ilast, rtol1, rtol2, offset, w, 0, wgap, 0, werr, 0, work, 0, iwork, 0, pivmin, spdiam, twist, info);
  }

  public void dlarrb(int n, double[] d, int _d_offset, double[] lld, int _lld_offset, int ifirst, int ilast, double rtol1, double rtol2, int offset, double[] w, int _w_offset, double[] wgap, int _wgap_offset, double[] werr, int _werr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, double pivmin, double spdiam, int twist, org.netlib.util.intW info) {
    f2j.dlarrb(n, d, _d_offset, lld, _lld_offset, ifirst, ilast, rtol1, rtol2, offset, w, _w_offset, wgap, _wgap_offset, werr, _werr_offset, work, _work_offset, iwork, _iwork_offset, pivmin, spdiam, twist, info);
  }

  public void dlarrc(String jobt, int n, double vl, double vu, double[] d, double[] e, double pivmin, org.netlib.util.intW eigcnt, org.netlib.util.intW lcnt, org.netlib.util.intW rcnt, org.netlib.util.intW info) {
    f2j.dlarrc(jobt, n, vl, vu, d, 0, e, 0, pivmin, eigcnt, lcnt, rcnt, info);
  }

  public void dlarrc(String jobt, int n, double vl, double vu, double[] d, int _d_offset, double[] e, int _e_offset, double pivmin, org.netlib.util.intW eigcnt, org.netlib.util.intW lcnt, org.netlib.util.intW rcnt, org.netlib.util.intW info) {
    f2j.dlarrc(jobt, n, vl, vu, d, _d_offset, e, _e_offset, pivmin, eigcnt, lcnt, rcnt, info);
  }

  public void dlarrd(String range, String order, int n, double vl, double vu, int il, int iu, double[] gers, double reltol, double[] d, double[] e, double[] e2, double pivmin, int nsplit, int[] isplit, org.netlib.util.intW m, double[] w, double[] werr, org.netlib.util.doubleW wl, org.netlib.util.doubleW wu, int[] iblock, int[] indexw, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dlarrd(range, order, n, vl, vu, il, iu, gers, 0, reltol, d, 0, e, 0, e2, 0, pivmin, nsplit, isplit, 0, m, w, 0, werr, 0, wl, wu, iblock, 0, indexw, 0, work, 0, iwork, 0, info);
  }

  public void dlarrd(String range, String order, int n, double vl, double vu, int il, int iu, double[] gers, int _gers_offset, double reltol, double[] d, int _d_offset, double[] e, int _e_offset, double[] e2, int _e2_offset, double pivmin, int nsplit, int[] isplit, int _isplit_offset, org.netlib.util.intW m, double[] w, int _w_offset, double[] werr, int _werr_offset, org.netlib.util.doubleW wl, org.netlib.util.doubleW wu, int[] iblock, int _iblock_offset, int[] indexw, int _indexw_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dlarrd(range, order, n, vl, vu, il, iu, gers, _gers_offset, reltol, d, _d_offset, e, _e_offset, e2, _e2_offset, pivmin, nsplit, isplit, _isplit_offset, m, w, _w_offset, werr, _werr_offset, wl, wu, iblock, _iblock_offset, indexw, _indexw_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dlarre(String range, int n, org.netlib.util.doubleW vl, org.netlib.util.doubleW vu, int il, int iu, double[] d, double[] e, double[] e2, double rtol1, double rtol2, double spltol, org.netlib.util.intW nsplit, int[] isplit, org.netlib.util.intW m, double[] w, double[] werr, double[] wgap, int[] iblock, int[] indexw, double[] gers, org.netlib.util.doubleW pivmin, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dlarre(range, n, vl, vu, il, iu, d, 0, e, 0, e2, 0, rtol1, rtol2, spltol, nsplit, isplit, 0, m, w, 0, werr, 0, wgap, 0, iblock, 0, indexw, 0, gers, 0, pivmin, work, 0, iwork, 0, info);
  }

  public void dlarre(String range, int n, org.netlib.util.doubleW vl, org.netlib.util.doubleW vu, int il, int iu, double[] d, int _d_offset, double[] e, int _e_offset, double[] e2, int _e2_offset, double rtol1, double rtol2, double spltol, org.netlib.util.intW nsplit, int[] isplit, int _isplit_offset, org.netlib.util.intW m, double[] w, int _w_offset, double[] werr, int _werr_offset, double[] wgap, int _wgap_offset, int[] iblock, int _iblock_offset, int[] indexw, int _indexw_offset, double[] gers, int _gers_offset, org.netlib.util.doubleW pivmin, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dlarre(range, n, vl, vu, il, iu, d, _d_offset, e, _e_offset, e2, _e2_offset, rtol1, rtol2, spltol, nsplit, isplit, _isplit_offset, m, w, _w_offset, werr, _werr_offset, wgap, _wgap_offset, iblock, _iblock_offset, indexw, _indexw_offset, gers, _gers_offset, pivmin, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dlarrf(int n, double[] d, double[] l, double[] ld, int clstrt, int clend, double[] w, double[] wgap, double[] werr, double spdiam, double clgapl, double clgapr, double pivmin, org.netlib.util.doubleW sigma, double[] dplus, double[] lplus, double[] work, org.netlib.util.intW info) {
    f2j.dlarrf(n, d, 0, l, 0, ld, 0, clstrt, clend, w, 0, wgap, 0, werr, 0, spdiam, clgapl, clgapr, pivmin, sigma, dplus, 0, lplus, 0, work, 0, info);
  }

  public void dlarrf(int n, double[] d, int _d_offset, double[] l, int _l_offset, double[] ld, int _ld_offset, int clstrt, int clend, double[] w, int _w_offset, double[] wgap, int _wgap_offset, double[] werr, int _werr_offset, double spdiam, double clgapl, double clgapr, double pivmin, org.netlib.util.doubleW sigma, double[] dplus, int _dplus_offset, double[] lplus, int _lplus_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dlarrf(n, d, _d_offset, l, _l_offset, ld, _ld_offset, clstrt, clend, w, _w_offset, wgap, _wgap_offset, werr, _werr_offset, spdiam, clgapl, clgapr, pivmin, sigma, dplus, _dplus_offset, lplus, _lplus_offset, work, _work_offset, info);
  }

  public void dlarrj(int n, double[] d, double[] e2, int ifirst, int ilast, double rtol, int offset, double[] w, double[] werr, double[] work, int[] iwork, double pivmin, double spdiam, org.netlib.util.intW info) {
    f2j.dlarrj(n, d, 0, e2, 0, ifirst, ilast, rtol, offset, w, 0, werr, 0, work, 0, iwork, 0, pivmin, spdiam, info);
  }

  public void dlarrj(int n, double[] d, int _d_offset, double[] e2, int _e2_offset, int ifirst, int ilast, double rtol, int offset, double[] w, int _w_offset, double[] werr, int _werr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, double pivmin, double spdiam, org.netlib.util.intW info) {
    f2j.dlarrj(n, d, _d_offset, e2, _e2_offset, ifirst, ilast, rtol, offset, w, _w_offset, werr, _werr_offset, work, _work_offset, iwork, _iwork_offset, pivmin, spdiam, info);
  }

  public void dlarrk(int n, int iw, double gl, double gu, double[] d, double[] e2, double pivmin, double reltol, org.netlib.util.doubleW w, org.netlib.util.doubleW werr, org.netlib.util.intW info) {
    f2j.dlarrk(n, iw, gl, gu, d, 0, e2, 0, pivmin, reltol, w, werr, info);
  }

  public void dlarrk(int n, int iw, double gl, double gu, double[] d, int _d_offset, double[] e2, int _e2_offset, double pivmin, double reltol, org.netlib.util.doubleW w, org.netlib.util.doubleW werr, org.netlib.util.intW info) {
    f2j.dlarrk(n, iw, gl, gu, d, _d_offset, e2, _e2_offset, pivmin, reltol, w, werr, info);
  }

  public void dlarrr(int n, double[] d, double[] e, org.netlib.util.intW info) {
    f2j.dlarrr(n, d, 0, e, 0, info);
  }

  public void dlarrr(int n, double[] d, int _d_offset, double[] e, int _e_offset, org.netlib.util.intW info) {
    f2j.dlarrr(n, d, _d_offset, e, _e_offset, info);
  }

  public void dlarrv(int n, double vl, double vu, double[] d, double[] l, double pivmin, int[] isplit, int m, int dol, int dou, double minrgp, org.netlib.util.doubleW rtol1, org.netlib.util.doubleW rtol2, double[] w, double[] werr, double[] wgap, int[] iblock, int[] indexw, double[] gers, double[] z, int ldz, int[] isuppz, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dlarrv(n, vl, vu, d, 0, l, 0, pivmin, isplit, 0, m, dol, dou, minrgp, rtol1, rtol2, w, 0, werr, 0, wgap, 0, iblock, 0, indexw, 0, gers, 0, z, 0, ldz, isuppz, 0, work, 0, iwork, 0, info);
  }

  public void dlarrv(int n, double vl, double vu, double[] d, int _d_offset, double[] l, int _l_offset, double pivmin, int[] isplit, int _isplit_offset, int m, int dol, int dou, double minrgp, org.netlib.util.doubleW rtol1, org.netlib.util.doubleW rtol2, double[] w, int _w_offset, double[] werr, int _werr_offset, double[] wgap, int _wgap_offset, int[] iblock, int _iblock_offset, int[] indexw, int _indexw_offset, double[] gers, int _gers_offset, double[] z, int _z_offset, int ldz, int[] isuppz, int _isuppz_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dlarrv(n, vl, vu, d, _d_offset, l, _l_offset, pivmin, isplit, _isplit_offset, m, dol, dou, minrgp, rtol1, rtol2, w, _w_offset, werr, _werr_offset, wgap, _wgap_offset, iblock, _iblock_offset, indexw, _indexw_offset, gers, _gers_offset, z, _z_offset, ldz, isuppz, _isuppz_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dlartg(double f, double g, org.netlib.util.doubleW cs, org.netlib.util.doubleW sn, org.netlib.util.doubleW r) {
    f2j.dlartg(f, g, cs, sn, r);
  }

  public void dlartv(int n, double[] x, int incx, double[] y, int incy, double[] c, double[] s, int incc) {
    f2j.dlartv(n, x, 0, incx, y, 0, incy, c, 0, s, 0, incc);
  }

  public void dlartv(int n, double[] x, int _x_offset, int incx, double[] y, int _y_offset, int incy, double[] c, int _c_offset, double[] s, int _s_offset, int incc) {
    f2j.dlartv(n, x, _x_offset, incx, y, _y_offset, incy, c, _c_offset, s, _s_offset, incc);
  }

  public void dlaruv(int[] iseed, int n, double[] x) {
    f2j.dlaruv(iseed, 0, n, x, 0);
  }

  public void dlaruv(int[] iseed, int _iseed_offset, int n, double[] x, int _x_offset) {
    f2j.dlaruv(iseed, _iseed_offset, n, x, _x_offset);
  }

  public void dlarz(String side, int m, int n, int l, double[] v, int incv, double tau, double[] c, int Ldc, double[] work) {
    f2j.dlarz(side, m, n, l, v, 0, incv, tau, c, 0, Ldc, work, 0);
  }

  public void dlarz(String side, int m, int n, int l, double[] v, int _v_offset, int incv, double tau, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset) {
    f2j.dlarz(side, m, n, l, v, _v_offset, incv, tau, c, _c_offset, Ldc, work, _work_offset);
  }

  public void dlarzb(String side, String trans, String direct, String storev, int m, int n, int k, int l, double[] v, int ldv, double[] t, int ldt, double[] c, int Ldc, double[] work, int ldwork) {
    f2j.dlarzb(side, trans, direct, storev, m, n, k, l, v, 0, ldv, t, 0, ldt, c, 0, Ldc, work, 0, ldwork);
  }

  public void dlarzb(String side, String trans, String direct, String storev, int m, int n, int k, int l, double[] v, int _v_offset, int ldv, double[] t, int _t_offset, int ldt, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, int ldwork) {
    f2j.dlarzb(side, trans, direct, storev, m, n, k, l, v, _v_offset, ldv, t, _t_offset, ldt, c, _c_offset, Ldc, work, _work_offset, ldwork);
  }

  public void dlarzt(String direct, String storev, int n, int k, double[] v, int ldv, double[] tau, double[] t, int ldt) {
    f2j.dlarzt(direct, storev, n, k, v, 0, ldv, tau, 0, t, 0, ldt);
  }

  public void dlarzt(String direct, String storev, int n, int k, double[] v, int _v_offset, int ldv, double[] tau, int _tau_offset, double[] t, int _t_offset, int ldt) {
    f2j.dlarzt(direct, storev, n, k, v, _v_offset, ldv, tau, _tau_offset, t, _t_offset, ldt);
  }

  public void dlas2(double f, double g, double h, org.netlib.util.doubleW ssmin, org.netlib.util.doubleW ssmax) {
    f2j.dlas2(f, g, h, ssmin, ssmax);
  }

  public void dlascl(String type, int kl, int ku, double cfrom, double cto, int m, int n, double[] a, int lda, org.netlib.util.intW info) {
    f2j.dlascl(type, kl, ku, cfrom, cto, m, n, a, 0, lda, info);
  }

  public void dlascl(String type, int kl, int ku, double cfrom, double cto, int m, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    f2j.dlascl(type, kl, ku, cfrom, cto, m, n, a, _a_offset, lda, info);
  }

  public void dlasd0(int n, int sqre, double[] d, double[] e, double[] u, int ldu, double[] vt, int ldvt, int smlsiz, int[] iwork, double[] work, org.netlib.util.intW info) {
    f2j.dlasd0(n, sqre, d, 0, e, 0, u, 0, ldu, vt, 0, ldvt, smlsiz, iwork, 0, work, 0, info);
  }

  public void dlasd0(int n, int sqre, double[] d, int _d_offset, double[] e, int _e_offset, double[] u, int _u_offset, int ldu, double[] vt, int _vt_offset, int ldvt, int smlsiz, int[] iwork, int _iwork_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dlasd0(n, sqre, d, _d_offset, e, _e_offset, u, _u_offset, ldu, vt, _vt_offset, ldvt, smlsiz, iwork, _iwork_offset, work, _work_offset, info);
  }

  public void dlasd1(int nl, int nr, int sqre, double[] d, org.netlib.util.doubleW alpha, org.netlib.util.doubleW beta, double[] u, int ldu, double[] vt, int ldvt, int[] idxq, int[] iwork, double[] work, org.netlib.util.intW info) {
    f2j.dlasd1(nl, nr, sqre, d, 0, alpha, beta, u, 0, ldu, vt, 0, ldvt, idxq, 0, iwork, 0, work, 0, info);
  }

  public void dlasd1(int nl, int nr, int sqre, double[] d, int _d_offset, org.netlib.util.doubleW alpha, org.netlib.util.doubleW beta, double[] u, int _u_offset, int ldu, double[] vt, int _vt_offset, int ldvt, int[] idxq, int _idxq_offset, int[] iwork, int _iwork_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dlasd1(nl, nr, sqre, d, _d_offset, alpha, beta, u, _u_offset, ldu, vt, _vt_offset, ldvt, idxq, _idxq_offset, iwork, _iwork_offset, work, _work_offset, info);
  }

  public void dlasd2(int nl, int nr, int sqre, org.netlib.util.intW k, double[] d, double[] z, double alpha, double beta, double[] u, int ldu, double[] vt, int ldvt, double[] dsigma, double[] u2, int ldu2, double[] vt2, int ldvt2, int[] idxp, int[] idx, int[] idxc, int[] idxq, int[] coltyp, org.netlib.util.intW info) {
    f2j.dlasd2(nl, nr, sqre, k, d, 0, z, 0, alpha, beta, u, 0, ldu, vt, 0, ldvt, dsigma, 0, u2, 0, ldu2, vt2, 0, ldvt2, idxp, 0, idx, 0, idxc, 0, idxq, 0, coltyp, 0, info);
  }

  public void dlasd2(int nl, int nr, int sqre, org.netlib.util.intW k, double[] d, int _d_offset, double[] z, int _z_offset, double alpha, double beta, double[] u, int _u_offset, int ldu, double[] vt, int _vt_offset, int ldvt, double[] dsigma, int _dsigma_offset, double[] u2, int _u2_offset, int ldu2, double[] vt2, int _vt2_offset, int ldvt2, int[] idxp, int _idxp_offset, int[] idx, int _idx_offset, int[] idxc, int _idxc_offset, int[] idxq, int _idxq_offset, int[] coltyp, int _coltyp_offset, org.netlib.util.intW info) {
    f2j.dlasd2(nl, nr, sqre, k, d, _d_offset, z, _z_offset, alpha, beta, u, _u_offset, ldu, vt, _vt_offset, ldvt, dsigma, _dsigma_offset, u2, _u2_offset, ldu2, vt2, _vt2_offset, ldvt2, idxp, _idxp_offset, idx, _idx_offset, idxc, _idxc_offset, idxq, _idxq_offset, coltyp, _coltyp_offset, info);
  }

  public void dlasd3(int nl, int nr, int sqre, int k, double[] d, double[] q, int ldq, double[] dsigma, double[] u, int ldu, double[] u2, int ldu2, double[] vt, int ldvt, double[] vt2, int ldvt2, int[] idxc, int[] ctot, double[] z, org.netlib.util.intW info) {
    f2j.dlasd3(nl, nr, sqre, k, d, 0, q, 0, ldq, dsigma, 0, u, 0, ldu, u2, 0, ldu2, vt, 0, ldvt, vt2, 0, ldvt2, idxc, 0, ctot, 0, z, 0, info);
  }

  public void dlasd3(int nl, int nr, int sqre, int k, double[] d, int _d_offset, double[] q, int _q_offset, int ldq, double[] dsigma, int _dsigma_offset, double[] u, int _u_offset, int ldu, double[] u2, int _u2_offset, int ldu2, double[] vt, int _vt_offset, int ldvt, double[] vt2, int _vt2_offset, int ldvt2, int[] idxc, int _idxc_offset, int[] ctot, int _ctot_offset, double[] z, int _z_offset, org.netlib.util.intW info) {
    f2j.dlasd3(nl, nr, sqre, k, d, _d_offset, q, _q_offset, ldq, dsigma, _dsigma_offset, u, _u_offset, ldu, u2, _u2_offset, ldu2, vt, _vt_offset, ldvt, vt2, _vt2_offset, ldvt2, idxc, _idxc_offset, ctot, _ctot_offset, z, _z_offset, info);
  }

  public void dlasd4(int n, int i, double[] d, double[] z, double[] delta, double rho, org.netlib.util.doubleW sigma, double[] work, org.netlib.util.intW info) {
    f2j.dlasd4(n, i, d, 0, z, 0, delta, 0, rho, sigma, work, 0, info);
  }

  public void dlasd4(int n, int i, double[] d, int _d_offset, double[] z, int _z_offset, double[] delta, int _delta_offset, double rho, org.netlib.util.doubleW sigma, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dlasd4(n, i, d, _d_offset, z, _z_offset, delta, _delta_offset, rho, sigma, work, _work_offset, info);
  }

  public void dlasd5(int i, double[] d, double[] z, double[] delta, double rho, org.netlib.util.doubleW dsigma, double[] work) {
    f2j.dlasd5(i, d, 0, z, 0, delta, 0, rho, dsigma, work, 0);
  }

  public void dlasd5(int i, double[] d, int _d_offset, double[] z, int _z_offset, double[] delta, int _delta_offset, double rho, org.netlib.util.doubleW dsigma, double[] work, int _work_offset) {
    f2j.dlasd5(i, d, _d_offset, z, _z_offset, delta, _delta_offset, rho, dsigma, work, _work_offset);
  }

  public void dlasd6(int icompq, int nl, int nr, int sqre, double[] d, double[] vf, double[] vl, org.netlib.util.doubleW alpha, org.netlib.util.doubleW beta, int[] idxq, int[] perm, org.netlib.util.intW givptr, int[] givcol, int ldgcol, double[] givnum, int ldgnum, double[] poles, double[] difl, double[] difr, double[] z, org.netlib.util.intW k, org.netlib.util.doubleW c, org.netlib.util.doubleW s, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dlasd6(icompq, nl, nr, sqre, d, 0, vf, 0, vl, 0, alpha, beta, idxq, 0, perm, 0, givptr, givcol, 0, ldgcol, givnum, 0, ldgnum, poles, 0, difl, 0, difr, 0, z, 0, k, c, s, work, 0, iwork, 0, info);
  }

  public void dlasd6(int icompq, int nl, int nr, int sqre, double[] d, int _d_offset, double[] vf, int _vf_offset, double[] vl, int _vl_offset, org.netlib.util.doubleW alpha, org.netlib.util.doubleW beta, int[] idxq, int _idxq_offset, int[] perm, int _perm_offset, org.netlib.util.intW givptr, int[] givcol, int _givcol_offset, int ldgcol, double[] givnum, int _givnum_offset, int ldgnum, double[] poles, int _poles_offset, double[] difl, int _difl_offset, double[] difr, int _difr_offset, double[] z, int _z_offset, org.netlib.util.intW k, org.netlib.util.doubleW c, org.netlib.util.doubleW s, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dlasd6(icompq, nl, nr, sqre, d, _d_offset, vf, _vf_offset, vl, _vl_offset, alpha, beta, idxq, _idxq_offset, perm, _perm_offset, givptr, givcol, _givcol_offset, ldgcol, givnum, _givnum_offset, ldgnum, poles, _poles_offset, difl, _difl_offset, difr, _difr_offset, z, _z_offset, k, c, s, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dlasd7(int icompq, int nl, int nr, int sqre, org.netlib.util.intW k, double[] d, double[] z, double[] zw, double[] vf, double[] vfw, double[] vl, double[] vlw, double alpha, double beta, double[] dsigma, int[] idx, int[] idxp, int[] idxq, int[] perm, org.netlib.util.intW givptr, int[] givcol, int ldgcol, double[] givnum, int ldgnum, org.netlib.util.doubleW c, org.netlib.util.doubleW s, org.netlib.util.intW info) {
    f2j.dlasd7(icompq, nl, nr, sqre, k, d, 0, z, 0, zw, 0, vf, 0, vfw, 0, vl, 0, vlw, 0, alpha, beta, dsigma, 0, idx, 0, idxp, 0, idxq, 0, perm, 0, givptr, givcol, 0, ldgcol, givnum, 0, ldgnum, c, s, info);
  }

  public void dlasd7(int icompq, int nl, int nr, int sqre, org.netlib.util.intW k, double[] d, int _d_offset, double[] z, int _z_offset, double[] zw, int _zw_offset, double[] vf, int _vf_offset, double[] vfw, int _vfw_offset, double[] vl, int _vl_offset, double[] vlw, int _vlw_offset, double alpha, double beta, double[] dsigma, int _dsigma_offset, int[] idx, int _idx_offset, int[] idxp, int _idxp_offset, int[] idxq, int _idxq_offset, int[] perm, int _perm_offset, org.netlib.util.intW givptr, int[] givcol, int _givcol_offset, int ldgcol, double[] givnum, int _givnum_offset, int ldgnum, org.netlib.util.doubleW c, org.netlib.util.doubleW s, org.netlib.util.intW info) {
    f2j.dlasd7(icompq, nl, nr, sqre, k, d, _d_offset, z, _z_offset, zw, _zw_offset, vf, _vf_offset, vfw, _vfw_offset, vl, _vl_offset, vlw, _vlw_offset, alpha, beta, dsigma, _dsigma_offset, idx, _idx_offset, idxp, _idxp_offset, idxq, _idxq_offset, perm, _perm_offset, givptr, givcol, _givcol_offset, ldgcol, givnum, _givnum_offset, ldgnum, c, s, info);
  }

  public void dlasd8(int icompq, int k, double[] d, double[] z, double[] vf, double[] vl, double[] difl, double[] difr, int lddifr, double[] dsigma, double[] work, org.netlib.util.intW info) {
    f2j.dlasd8(icompq, k, d, 0, z, 0, vf, 0, vl, 0, difl, 0, difr, 0, lddifr, dsigma, 0, work, 0, info);
  }

  public void dlasd8(int icompq, int k, double[] d, int _d_offset, double[] z, int _z_offset, double[] vf, int _vf_offset, double[] vl, int _vl_offset, double[] difl, int _difl_offset, double[] difr, int _difr_offset, int lddifr, double[] dsigma, int _dsigma_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dlasd8(icompq, k, d, _d_offset, z, _z_offset, vf, _vf_offset, vl, _vl_offset, difl, _difl_offset, difr, _difr_offset, lddifr, dsigma, _dsigma_offset, work, _work_offset, info);
  }

  public void dlasda(int icompq, int smlsiz, int n, int sqre, double[] d, double[] e, double[] u, int ldu, double[] vt, int[] k, double[] difl, double[] difr, double[] z, double[] poles, int[] givptr, int[] givcol, int ldgcol, int[] perm, double[] givnum, double[] c, double[] s, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dlasda(icompq, smlsiz, n, sqre, d, 0, e, 0, u, 0, ldu, vt, 0, k, 0, difl, 0, difr, 0, z, 0, poles, 0, givptr, 0, givcol, 0, ldgcol, perm, 0, givnum, 0, c, 0, s, 0, work, 0, iwork, 0, info);
  }

  public void dlasda(int icompq, int smlsiz, int n, int sqre, double[] d, int _d_offset, double[] e, int _e_offset, double[] u, int _u_offset, int ldu, double[] vt, int _vt_offset, int[] k, int _k_offset, double[] difl, int _difl_offset, double[] difr, int _difr_offset, double[] z, int _z_offset, double[] poles, int _poles_offset, int[] givptr, int _givptr_offset, int[] givcol, int _givcol_offset, int ldgcol, int[] perm, int _perm_offset, double[] givnum, int _givnum_offset, double[] c, int _c_offset, double[] s, int _s_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dlasda(icompq, smlsiz, n, sqre, d, _d_offset, e, _e_offset, u, _u_offset, ldu, vt, _vt_offset, k, _k_offset, difl, _difl_offset, difr, _difr_offset, z, _z_offset, poles, _poles_offset, givptr, _givptr_offset, givcol, _givcol_offset, ldgcol, perm, _perm_offset, givnum, _givnum_offset, c, _c_offset, s, _s_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dlasdq(String uplo, int sqre, int n, int ncvt, int nru, int ncc, double[] d, double[] e, double[] vt, int ldvt, double[] u, int ldu, double[] c, int Ldc, double[] work, org.netlib.util.intW info) {
    f2j.dlasdq(uplo, sqre, n, ncvt, nru, ncc, d, 0, e, 0, vt, 0, ldvt, u, 0, ldu, c, 0, Ldc, work, 0, info);
  }

  public void dlasdq(String uplo, int sqre, int n, int ncvt, int nru, int ncc, double[] d, int _d_offset, double[] e, int _e_offset, double[] vt, int _vt_offset, int ldvt, double[] u, int _u_offset, int ldu, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dlasdq(uplo, sqre, n, ncvt, nru, ncc, d, _d_offset, e, _e_offset, vt, _vt_offset, ldvt, u, _u_offset, ldu, c, _c_offset, Ldc, work, _work_offset, info);
  }

  public void dlasdt(int n, org.netlib.util.intW lvl, org.netlib.util.intW nd, int[] inode, int[] ndiml, int[] ndimr, int msub) {
    f2j.dlasdt(n, lvl, nd, inode, 0, ndiml, 0, ndimr, 0, msub);
  }

  public void dlasdt(int n, org.netlib.util.intW lvl, org.netlib.util.intW nd, int[] inode, int _inode_offset, int[] ndiml, int _ndiml_offset, int[] ndimr, int _ndimr_offset, int msub) {
    f2j.dlasdt(n, lvl, nd, inode, _inode_offset, ndiml, _ndiml_offset, ndimr, _ndimr_offset, msub);
  }

  public void dlaset(String uplo, int m, int n, double alpha, double beta, double[] a, int lda) {
    f2j.dlaset(uplo, m, n, alpha, beta, a, 0, lda);
  }

  public void dlaset(String uplo, int m, int n, double alpha, double beta, double[] a, int _a_offset, int lda) {
    f2j.dlaset(uplo, m, n, alpha, beta, a, _a_offset, lda);
  }

  public void dlasq1(int n, double[] d, double[] e, double[] work, org.netlib.util.intW info) {
    f2j.dlasq1(n, d, 0, e, 0, work, 0, info);
  }

  public void dlasq1(int n, double[] d, int _d_offset, double[] e, int _e_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dlasq1(n, d, _d_offset, e, _e_offset, work, _work_offset, info);
  }

  public void dlasq2(int n, double[] z, org.netlib.util.intW info) {
    f2j.dlasq2(n, z, 0, info);
  }

  public void dlasq2(int n, double[] z, int _z_offset, org.netlib.util.intW info) {
    f2j.dlasq2(n, z, _z_offset, info);
  }

  public void dlasq3(int i0, org.netlib.util.intW n0, double[] z, int pp, org.netlib.util.doubleW dmin, org.netlib.util.doubleW sigma, org.netlib.util.doubleW desig, org.netlib.util.doubleW qmax, org.netlib.util.intW nfail, org.netlib.util.intW iter, org.netlib.util.intW ndiv, boolean ieee) {
    f2j.dlasq3(i0, n0, z, 0, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee);
  }

  public void dlasq3(int i0, org.netlib.util.intW n0, double[] z, int _z_offset, int pp, org.netlib.util.doubleW dmin, org.netlib.util.doubleW sigma, org.netlib.util.doubleW desig, org.netlib.util.doubleW qmax, org.netlib.util.intW nfail, org.netlib.util.intW iter, org.netlib.util.intW ndiv, boolean ieee) {
    f2j.dlasq3(i0, n0, z, _z_offset, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee);
  }

  public void dlasq4(int i0, int n0, double[] z, int pp, int n0in, double dmin, double dmin1, double dmin2, double dn, double dn1, double dn2, org.netlib.util.doubleW tau, org.netlib.util.intW ttype) {
    f2j.dlasq4(i0, n0, z, 0, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype);
  }

  public void dlasq4(int i0, int n0, double[] z, int _z_offset, int pp, int n0in, double dmin, double dmin1, double dmin2, double dn, double dn1, double dn2, org.netlib.util.doubleW tau, org.netlib.util.intW ttype) {
    f2j.dlasq4(i0, n0, z, _z_offset, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype);
  }

  public void dlasq5(int i0, int n0, double[] z, int pp, double tau, org.netlib.util.doubleW dmin, org.netlib.util.doubleW dmin1, org.netlib.util.doubleW dmin2, org.netlib.util.doubleW dn, org.netlib.util.doubleW dnm1, org.netlib.util.doubleW dnm2, boolean ieee) {
    f2j.dlasq5(i0, n0, z, 0, pp, tau, dmin, dmin1, dmin2, dn, dnm1, dnm2, ieee);
  }

  public void dlasq5(int i0, int n0, double[] z, int _z_offset, int pp, double tau, org.netlib.util.doubleW dmin, org.netlib.util.doubleW dmin1, org.netlib.util.doubleW dmin2, org.netlib.util.doubleW dn, org.netlib.util.doubleW dnm1, org.netlib.util.doubleW dnm2, boolean ieee) {
    f2j.dlasq5(i0, n0, z, _z_offset, pp, tau, dmin, dmin1, dmin2, dn, dnm1, dnm2, ieee);
  }

  public void dlasq6(int i0, int n0, double[] z, int pp, org.netlib.util.doubleW dmin, org.netlib.util.doubleW dmin1, org.netlib.util.doubleW dmin2, org.netlib.util.doubleW dn, org.netlib.util.doubleW dnm1, org.netlib.util.doubleW dnm2) {
    f2j.dlasq6(i0, n0, z, 0, pp, dmin, dmin1, dmin2, dn, dnm1, dnm2);
  }

  public void dlasq6(int i0, int n0, double[] z, int _z_offset, int pp, org.netlib.util.doubleW dmin, org.netlib.util.doubleW dmin1, org.netlib.util.doubleW dmin2, org.netlib.util.doubleW dn, org.netlib.util.doubleW dnm1, org.netlib.util.doubleW dnm2) {
    f2j.dlasq6(i0, n0, z, _z_offset, pp, dmin, dmin1, dmin2, dn, dnm1, dnm2);
  }

  public void dlasr(String side, String pivot, String direct, int m, int n, double[] c, double[] s, double[] a, int lda) {
    f2j.dlasr(side, pivot, direct, m, n, c, 0, s, 0, a, 0, lda);
  }

  public void dlasr(String side, String pivot, String direct, int m, int n, double[] c, int _c_offset, double[] s, int _s_offset, double[] a, int _a_offset, int lda) {
    f2j.dlasr(side, pivot, direct, m, n, c, _c_offset, s, _s_offset, a, _a_offset, lda);
  }

  public void dlasrt(String id, int n, double[] d, org.netlib.util.intW info) {
    f2j.dlasrt(id, n, d, 0, info);
  }

  public void dlasrt(String id, int n, double[] d, int _d_offset, org.netlib.util.intW info) {
    f2j.dlasrt(id, n, d, _d_offset, info);
  }

  public void dlassq(int n, double[] x, int incx, org.netlib.util.doubleW scale, org.netlib.util.doubleW sumsq) {
    f2j.dlassq(n, x, 0, incx, scale, sumsq);
  }

  public void dlassq(int n, double[] x, int _x_offset, int incx, org.netlib.util.doubleW scale, org.netlib.util.doubleW sumsq) {
    f2j.dlassq(n, x, _x_offset, incx, scale, sumsq);
  }

  public void dlasv2(double f, double g, double h, org.netlib.util.doubleW ssmin, org.netlib.util.doubleW ssmax, org.netlib.util.doubleW snr, org.netlib.util.doubleW csr, org.netlib.util.doubleW snl, org.netlib.util.doubleW csl) {
    f2j.dlasv2(f, g, h, ssmin, ssmax, snr, csr, snl, csl);
  }

  public void dlaswp(int n, double[] a, int lda, int k1, int k2, int[] ipiv, int incx) {
    f2j.dlaswp(n, a, 0, lda, k1, k2, ipiv, 0, incx);
  }

  public void dlaswp(int n, double[] a, int _a_offset, int lda, int k1, int k2, int[] ipiv, int _ipiv_offset, int incx) {
    f2j.dlaswp(n, a, _a_offset, lda, k1, k2, ipiv, _ipiv_offset, incx);
  }

  public void dlasy2(boolean ltranl, boolean ltranr, int isgn, int n1, int n2, double[] tl, int ldtl, double[] tr, int ldtr, double[] b, int ldb, org.netlib.util.doubleW scale, double[] x, int ldx, org.netlib.util.doubleW xnorm, org.netlib.util.intW info) {
    f2j.dlasy2(ltranl, ltranr, isgn, n1, n2, tl, 0, ldtl, tr, 0, ldtr, b, 0, ldb, scale, x, 0, ldx, xnorm, info);
  }

  public void dlasy2(boolean ltranl, boolean ltranr, int isgn, int n1, int n2, double[] tl, int _tl_offset, int ldtl, double[] tr, int _tr_offset, int ldtr, double[] b, int _b_offset, int ldb, org.netlib.util.doubleW scale, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW xnorm, org.netlib.util.intW info) {
    f2j.dlasy2(ltranl, ltranr, isgn, n1, n2, tl, _tl_offset, ldtl, tr, _tr_offset, ldtr, b, _b_offset, ldb, scale, x, _x_offset, ldx, xnorm, info);
  }

  public void dlasyf(String uplo, int n, int nb, org.netlib.util.intW kb, double[] a, int lda, int[] ipiv, double[] w, int ldw, org.netlib.util.intW info) {
    f2j.dlasyf(uplo, n, nb, kb, a, 0, lda, ipiv, 0, w, 0, ldw, info);
  }

  public void dlasyf(String uplo, int n, int nb, org.netlib.util.intW kb, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, double[] w, int _w_offset, int ldw, org.netlib.util.intW info) {
    f2j.dlasyf(uplo, n, nb, kb, a, _a_offset, lda, ipiv, _ipiv_offset, w, _w_offset, ldw, info);
  }

  public void dlatbs(String uplo, String trans, String diag, String normin, int n, int kd, double[] ab, int ldab, double[] x, org.netlib.util.doubleW scale, double[] cnorm, org.netlib.util.intW info) {
    f2j.dlatbs(uplo, trans, diag, normin, n, kd, ab, 0, ldab, x, 0, scale, cnorm, 0, info);
  }

  public void dlatbs(String uplo, String trans, String diag, String normin, int n, int kd, double[] ab, int _ab_offset, int ldab, double[] x, int _x_offset, org.netlib.util.doubleW scale, double[] cnorm, int _cnorm_offset, org.netlib.util.intW info) {
    f2j.dlatbs(uplo, trans, diag, normin, n, kd, ab, _ab_offset, ldab, x, _x_offset, scale, cnorm, _cnorm_offset, info);
  }

  public void dlatdf(int ijob, int n, double[] z, int ldz, double[] rhs, org.netlib.util.doubleW rdsum, org.netlib.util.doubleW rdscal, int[] ipiv, int[] jpiv) {
    f2j.dlatdf(ijob, n, z, 0, ldz, rhs, 0, rdsum, rdscal, ipiv, 0, jpiv, 0);
  }

  public void dlatdf(int ijob, int n, double[] z, int _z_offset, int ldz, double[] rhs, int _rhs_offset, org.netlib.util.doubleW rdsum, org.netlib.util.doubleW rdscal, int[] ipiv, int _ipiv_offset, int[] jpiv, int _jpiv_offset) {
    f2j.dlatdf(ijob, n, z, _z_offset, ldz, rhs, _rhs_offset, rdsum, rdscal, ipiv, _ipiv_offset, jpiv, _jpiv_offset);
  }

  public void dlatps(String uplo, String trans, String diag, String normin, int n, double[] ap, double[] x, org.netlib.util.doubleW scale, double[] cnorm, org.netlib.util.intW info) {
    f2j.dlatps(uplo, trans, diag, normin, n, ap, 0, x, 0, scale, cnorm, 0, info);
  }

  public void dlatps(String uplo, String trans, String diag, String normin, int n, double[] ap, int _ap_offset, double[] x, int _x_offset, org.netlib.util.doubleW scale, double[] cnorm, int _cnorm_offset, org.netlib.util.intW info) {
    f2j.dlatps(uplo, trans, diag, normin, n, ap, _ap_offset, x, _x_offset, scale, cnorm, _cnorm_offset, info);
  }

  public void dlatrd(String uplo, int n, int nb, double[] a, int lda, double[] e, double[] tau, double[] w, int ldw) {
    f2j.dlatrd(uplo, n, nb, a, 0, lda, e, 0, tau, 0, w, 0, ldw);
  }

  public void dlatrd(String uplo, int n, int nb, double[] a, int _a_offset, int lda, double[] e, int _e_offset, double[] tau, int _tau_offset, double[] w, int _w_offset, int ldw) {
    f2j.dlatrd(uplo, n, nb, a, _a_offset, lda, e, _e_offset, tau, _tau_offset, w, _w_offset, ldw);
  }

  public void dlatrs(String uplo, String trans, String diag, String normin, int n, double[] a, int lda, double[] x, org.netlib.util.doubleW scale, double[] cnorm, org.netlib.util.intW info) {
    f2j.dlatrs(uplo, trans, diag, normin, n, a, 0, lda, x, 0, scale, cnorm, 0, info);
  }

  public void dlatrs(String uplo, String trans, String diag, String normin, int n, double[] a, int _a_offset, int lda, double[] x, int _x_offset, org.netlib.util.doubleW scale, double[] cnorm, int _cnorm_offset, org.netlib.util.intW info) {
    f2j.dlatrs(uplo, trans, diag, normin, n, a, _a_offset, lda, x, _x_offset, scale, cnorm, _cnorm_offset, info);
  }

  public void dlatrz(int m, int n, int l, double[] a, int lda, double[] tau, double[] work) {
    f2j.dlatrz(m, n, l, a, 0, lda, tau, 0, work, 0);
  }

  public void dlatrz(int m, int n, int l, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset) {
    f2j.dlatrz(m, n, l, a, _a_offset, lda, tau, _tau_offset, work, _work_offset);
  }

  public void dlatzm(String side, int m, int n, double[] v, int incv, double tau, double[] c1, double[] c2, int Ldc, double[] work) {
    f2j.dlatzm(side, m, n, v, 0, incv, tau, c1, 0, c2, 0, Ldc, work, 0);
  }

  public void dlatzm(String side, int m, int n, double[] v, int _v_offset, int incv, double tau, double[] c1, int _c1_offset, double[] c2, int _c2_offset, int Ldc, double[] work, int _work_offset) {
    f2j.dlatzm(side, m, n, v, _v_offset, incv, tau, c1, _c1_offset, c2, _c2_offset, Ldc, work, _work_offset);
  }

  public void dlauu2(String uplo, int n, double[] a, int lda, org.netlib.util.intW info) {
    f2j.dlauu2(uplo, n, a, 0, lda, info);
  }

  public void dlauu2(String uplo, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    f2j.dlauu2(uplo, n, a, _a_offset, lda, info);
  }

  public void dlauum(String uplo, int n, double[] a, int lda, org.netlib.util.intW info) {
    f2j.dlauum(uplo, n, a, 0, lda, info);
  }

  public void dlauum(String uplo, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    f2j.dlauum(uplo, n, a, _a_offset, lda, info);
  }

  public void dlazq3(int i0, org.netlib.util.intW n0, double[] z, int pp, org.netlib.util.doubleW dmin, org.netlib.util.doubleW sigma, org.netlib.util.doubleW desig, org.netlib.util.doubleW qmax, org.netlib.util.intW nfail, org.netlib.util.intW iter, org.netlib.util.intW ndiv, boolean ieee, org.netlib.util.intW ttype, org.netlib.util.doubleW dmin1, org.netlib.util.doubleW dmin2, org.netlib.util.doubleW dn, org.netlib.util.doubleW dn1, org.netlib.util.doubleW dn2, org.netlib.util.doubleW tau) {
    f2j.dlazq3(i0, n0, z, 0, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee, ttype, dmin1, dmin2, dn, dn1, dn2, tau);
  }

  public void dlazq3(int i0, org.netlib.util.intW n0, double[] z, int _z_offset, int pp, org.netlib.util.doubleW dmin, org.netlib.util.doubleW sigma, org.netlib.util.doubleW desig, org.netlib.util.doubleW qmax, org.netlib.util.intW nfail, org.netlib.util.intW iter, org.netlib.util.intW ndiv, boolean ieee, org.netlib.util.intW ttype, org.netlib.util.doubleW dmin1, org.netlib.util.doubleW dmin2, org.netlib.util.doubleW dn, org.netlib.util.doubleW dn1, org.netlib.util.doubleW dn2, org.netlib.util.doubleW tau) {
    f2j.dlazq3(i0, n0, z, _z_offset, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee, ttype, dmin1, dmin2, dn, dn1, dn2, tau);
  }

  public void dlazq4(int i0, int n0, double[] z, int pp, int n0in, double dmin, double dmin1, double dmin2, double dn, double dn1, double dn2, org.netlib.util.doubleW tau, org.netlib.util.intW ttype, org.netlib.util.doubleW g) {
    f2j.dlazq4(i0, n0, z, 0, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype, g);
  }

  public void dlazq4(int i0, int n0, double[] z, int _z_offset, int pp, int n0in, double dmin, double dmin1, double dmin2, double dn, double dn1, double dn2, org.netlib.util.doubleW tau, org.netlib.util.intW ttype, org.netlib.util.doubleW g) {
    f2j.dlazq4(i0, n0, z, _z_offset, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype, g);
  }

  public void dopgtr(String uplo, int n, double[] ap, double[] tau, double[] q, int ldq, double[] work, org.netlib.util.intW info) {
    f2j.dopgtr(uplo, n, ap, 0, tau, 0, q, 0, ldq, work, 0, info);
  }

  public void dopgtr(String uplo, int n, double[] ap, int _ap_offset, double[] tau, int _tau_offset, double[] q, int _q_offset, int ldq, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dopgtr(uplo, n, ap, _ap_offset, tau, _tau_offset, q, _q_offset, ldq, work, _work_offset, info);
  }

  public void dopmtr(String side, String uplo, String trans, int m, int n, double[] ap, double[] tau, double[] c, int Ldc, double[] work, org.netlib.util.intW info) {
    f2j.dopmtr(side, uplo, trans, m, n, ap, 0, tau, 0, c, 0, Ldc, work, 0, info);
  }

  public void dopmtr(String side, String uplo, String trans, int m, int n, double[] ap, int _ap_offset, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dopmtr(side, uplo, trans, m, n, ap, _ap_offset, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  public void dorg2l(int m, int n, int k, double[] a, int lda, double[] tau, double[] work, org.netlib.util.intW info) {
    f2j.dorg2l(m, n, k, a, 0, lda, tau, 0, work, 0, info);
  }

  public void dorg2l(int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dorg2l(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  public void dorg2r(int m, int n, int k, double[] a, int lda, double[] tau, double[] work, org.netlib.util.intW info) {
    f2j.dorg2r(m, n, k, a, 0, lda, tau, 0, work, 0, info);
  }

  public void dorg2r(int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dorg2r(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  public void dorgbr(String vect, int m, int n, int k, double[] a, int lda, double[] tau, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dorgbr(vect, m, n, k, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void dorgbr(String vect, int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dorgbr(vect, m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void dorghr(int n, int ilo, int ihi, double[] a, int lda, double[] tau, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dorghr(n, ilo, ihi, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void dorghr(int n, int ilo, int ihi, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dorghr(n, ilo, ihi, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void dorgl2(int m, int n, int k, double[] a, int lda, double[] tau, double[] work, org.netlib.util.intW info) {
    f2j.dorgl2(m, n, k, a, 0, lda, tau, 0, work, 0, info);
  }

  public void dorgl2(int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dorgl2(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  public void dorglq(int m, int n, int k, double[] a, int lda, double[] tau, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dorglq(m, n, k, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void dorglq(int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dorglq(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void dorgql(int m, int n, int k, double[] a, int lda, double[] tau, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dorgql(m, n, k, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void dorgql(int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dorgql(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void dorgqr(int m, int n, int k, double[] a, int lda, double[] tau, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dorgqr(m, n, k, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void dorgqr(int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dorgqr(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void dorgr2(int m, int n, int k, double[] a, int lda, double[] tau, double[] work, org.netlib.util.intW info) {
    f2j.dorgr2(m, n, k, a, 0, lda, tau, 0, work, 0, info);
  }

  public void dorgr2(int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dorgr2(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  public void dorgrq(int m, int n, int k, double[] a, int lda, double[] tau, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dorgrq(m, n, k, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void dorgrq(int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dorgrq(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void dorgtr(String uplo, int n, double[] a, int lda, double[] tau, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dorgtr(uplo, n, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void dorgtr(String uplo, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dorgtr(uplo, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void dorm2l(String side, String trans, int m, int n, int k, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, org.netlib.util.intW info) {
    f2j.dorm2l(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, info);
  }

  public void dorm2l(String side, String trans, int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dorm2l(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  public void dorm2r(String side, String trans, int m, int n, int k, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, org.netlib.util.intW info) {
    f2j.dorm2r(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, info);
  }

  public void dorm2r(String side, String trans, int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dorm2r(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  public void dormbr(String vect, String side, String trans, int m, int n, int k, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dormbr(vect, side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
  }

  public void dormbr(String vect, String side, String trans, int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dormbr(vect, side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  public void dormhr(String side, String trans, int m, int n, int ilo, int ihi, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dormhr(side, trans, m, n, ilo, ihi, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
  }

  public void dormhr(String side, String trans, int m, int n, int ilo, int ihi, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dormhr(side, trans, m, n, ilo, ihi, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  public void dorml2(String side, String trans, int m, int n, int k, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, org.netlib.util.intW info) {
    f2j.dorml2(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, info);
  }

  public void dorml2(String side, String trans, int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dorml2(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  public void dormlq(String side, String trans, int m, int n, int k, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dormlq(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
  }

  public void dormlq(String side, String trans, int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dormlq(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  public void dormql(String side, String trans, int m, int n, int k, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dormql(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
  }

  public void dormql(String side, String trans, int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dormql(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  public void dormqr(String side, String trans, int m, int n, int k, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dormqr(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
  }

  public void dormqr(String side, String trans, int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dormqr(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  public void dormr2(String side, String trans, int m, int n, int k, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, org.netlib.util.intW info) {
    f2j.dormr2(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, info);
  }

  public void dormr2(String side, String trans, int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dormr2(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  public void dormr3(String side, String trans, int m, int n, int k, int l, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, org.netlib.util.intW info) {
    f2j.dormr3(side, trans, m, n, k, l, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, info);
  }

  public void dormr3(String side, String trans, int m, int n, int k, int l, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dormr3(side, trans, m, n, k, l, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  public void dormrq(String side, String trans, int m, int n, int k, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dormrq(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
  }

  public void dormrq(String side, String trans, int m, int n, int k, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dormrq(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  public void dormrz(String side, String trans, int m, int n, int k, int l, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dormrz(side, trans, m, n, k, l, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
  }

  public void dormrz(String side, String trans, int m, int n, int k, int l, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dormrz(side, trans, m, n, k, l, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  public void dormtr(String side, String uplo, String trans, int m, int n, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dormtr(side, uplo, trans, m, n, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
  }

  public void dormtr(String side, String uplo, String trans, int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] c, int _c_offset, int Ldc, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dormtr(side, uplo, trans, m, n, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  public void dpbcon(String uplo, int n, int kd, double[] ab, int ldab, double anorm, org.netlib.util.doubleW rcond, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dpbcon(uplo, n, kd, ab, 0, ldab, anorm, rcond, work, 0, iwork, 0, info);
  }

  public void dpbcon(String uplo, int n, int kd, double[] ab, int _ab_offset, int ldab, double anorm, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dpbcon(uplo, n, kd, ab, _ab_offset, ldab, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dpbequ(String uplo, int n, int kd, double[] ab, int ldab, double[] s, org.netlib.util.doubleW scond, org.netlib.util.doubleW amax, org.netlib.util.intW info) {
    f2j.dpbequ(uplo, n, kd, ab, 0, ldab, s, 0, scond, amax, info);
  }

  public void dpbequ(String uplo, int n, int kd, double[] ab, int _ab_offset, int ldab, double[] s, int _s_offset, org.netlib.util.doubleW scond, org.netlib.util.doubleW amax, org.netlib.util.intW info) {
    f2j.dpbequ(uplo, n, kd, ab, _ab_offset, ldab, s, _s_offset, scond, amax, info);
  }

  public void dpbrfs(String uplo, int n, int kd, int nrhs, double[] ab, int ldab, double[] afb, int ldafb, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dpbrfs(uplo, n, kd, nrhs, ab, 0, ldab, afb, 0, ldafb, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void dpbrfs(String uplo, int n, int kd, int nrhs, double[] ab, int _ab_offset, int ldab, double[] afb, int _afb_offset, int ldafb, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dpbrfs(uplo, n, kd, nrhs, ab, _ab_offset, ldab, afb, _afb_offset, ldafb, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dpbstf(String uplo, int n, int kd, double[] ab, int ldab, org.netlib.util.intW info) {
    f2j.dpbstf(uplo, n, kd, ab, 0, ldab, info);
  }

  public void dpbstf(String uplo, int n, int kd, double[] ab, int _ab_offset, int ldab, org.netlib.util.intW info) {
    f2j.dpbstf(uplo, n, kd, ab, _ab_offset, ldab, info);
  }

  public void dpbsv(String uplo, int n, int kd, int nrhs, double[] ab, int ldab, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dpbsv(uplo, n, kd, nrhs, ab, 0, ldab, b, 0, ldb, info);
  }

  public void dpbsv(String uplo, int n, int kd, int nrhs, double[] ab, int _ab_offset, int ldab, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dpbsv(uplo, n, kd, nrhs, ab, _ab_offset, ldab, b, _b_offset, ldb, info);
  }

  public void dpbsvx(String fact, String uplo, int n, int kd, int nrhs, double[] ab, int ldab, double[] afb, int ldafb, org.netlib.util.StringW equed, double[] s, double[] b, int ldb, double[] x, int ldx, org.netlib.util.doubleW rcond, double[] ferr, double[] berr, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dpbsvx(fact, uplo, n, kd, nrhs, ab, 0, ldab, afb, 0, ldafb, equed, s, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void dpbsvx(String fact, String uplo, int n, int kd, int nrhs, double[] ab, int _ab_offset, int ldab, double[] afb, int _afb_offset, int ldafb, org.netlib.util.StringW equed, double[] s, int _s_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dpbsvx(fact, uplo, n, kd, nrhs, ab, _ab_offset, ldab, afb, _afb_offset, ldafb, equed, s, _s_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dpbtf2(String uplo, int n, int kd, double[] ab, int ldab, org.netlib.util.intW info) {
    f2j.dpbtf2(uplo, n, kd, ab, 0, ldab, info);
  }

  public void dpbtf2(String uplo, int n, int kd, double[] ab, int _ab_offset, int ldab, org.netlib.util.intW info) {
    f2j.dpbtf2(uplo, n, kd, ab, _ab_offset, ldab, info);
  }

  public void dpbtrf(String uplo, int n, int kd, double[] ab, int ldab, org.netlib.util.intW info) {
    f2j.dpbtrf(uplo, n, kd, ab, 0, ldab, info);
  }

  public void dpbtrf(String uplo, int n, int kd, double[] ab, int _ab_offset, int ldab, org.netlib.util.intW info) {
    f2j.dpbtrf(uplo, n, kd, ab, _ab_offset, ldab, info);
  }

  public void dpbtrs(String uplo, int n, int kd, int nrhs, double[] ab, int ldab, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dpbtrs(uplo, n, kd, nrhs, ab, 0, ldab, b, 0, ldb, info);
  }

  public void dpbtrs(String uplo, int n, int kd, int nrhs, double[] ab, int _ab_offset, int ldab, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dpbtrs(uplo, n, kd, nrhs, ab, _ab_offset, ldab, b, _b_offset, ldb, info);
  }

  public void dpocon(String uplo, int n, double[] a, int lda, double anorm, org.netlib.util.doubleW rcond, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dpocon(uplo, n, a, 0, lda, anorm, rcond, work, 0, iwork, 0, info);
  }

  public void dpocon(String uplo, int n, double[] a, int _a_offset, int lda, double anorm, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dpocon(uplo, n, a, _a_offset, lda, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dpoequ(int n, double[] a, int lda, double[] s, org.netlib.util.doubleW scond, org.netlib.util.doubleW amax, org.netlib.util.intW info) {
    f2j.dpoequ(n, a, 0, lda, s, 0, scond, amax, info);
  }

  public void dpoequ(int n, double[] a, int _a_offset, int lda, double[] s, int _s_offset, org.netlib.util.doubleW scond, org.netlib.util.doubleW amax, org.netlib.util.intW info) {
    f2j.dpoequ(n, a, _a_offset, lda, s, _s_offset, scond, amax, info);
  }

  public void dporfs(String uplo, int n, int nrhs, double[] a, int lda, double[] af, int ldaf, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dporfs(uplo, n, nrhs, a, 0, lda, af, 0, ldaf, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void dporfs(String uplo, int n, int nrhs, double[] a, int _a_offset, int lda, double[] af, int _af_offset, int ldaf, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dporfs(uplo, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dposv(String uplo, int n, int nrhs, double[] a, int lda, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dposv(uplo, n, nrhs, a, 0, lda, b, 0, ldb, info);
  }

  public void dposv(String uplo, int n, int nrhs, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dposv(uplo, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, info);
  }

  public void dposvx(String fact, String uplo, int n, int nrhs, double[] a, int lda, double[] af, int ldaf, org.netlib.util.StringW equed, double[] s, double[] b, int ldb, double[] x, int ldx, org.netlib.util.doubleW rcond, double[] ferr, double[] berr, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dposvx(fact, uplo, n, nrhs, a, 0, lda, af, 0, ldaf, equed, s, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void dposvx(String fact, String uplo, int n, int nrhs, double[] a, int _a_offset, int lda, double[] af, int _af_offset, int ldaf, org.netlib.util.StringW equed, double[] s, int _s_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dposvx(fact, uplo, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, equed, s, _s_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dpotf2(String uplo, int n, double[] a, int lda, org.netlib.util.intW info) {
    f2j.dpotf2(uplo, n, a, 0, lda, info);
  }

  public void dpotf2(String uplo, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    f2j.dpotf2(uplo, n, a, _a_offset, lda, info);
  }

  public void dpotrf(String uplo, int n, double[] a, int lda, org.netlib.util.intW info) {
    f2j.dpotrf(uplo, n, a, 0, lda, info);
  }

  public void dpotrf(String uplo, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    f2j.dpotrf(uplo, n, a, _a_offset, lda, info);
  }

  public void dpotri(String uplo, int n, double[] a, int lda, org.netlib.util.intW info) {
    f2j.dpotri(uplo, n, a, 0, lda, info);
  }

  public void dpotri(String uplo, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    f2j.dpotri(uplo, n, a, _a_offset, lda, info);
  }

  public void dpotrs(String uplo, int n, int nrhs, double[] a, int lda, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dpotrs(uplo, n, nrhs, a, 0, lda, b, 0, ldb, info);
  }

  public void dpotrs(String uplo, int n, int nrhs, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dpotrs(uplo, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, info);
  }

  public void dppcon(String uplo, int n, double[] ap, double anorm, org.netlib.util.doubleW rcond, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dppcon(uplo, n, ap, 0, anorm, rcond, work, 0, iwork, 0, info);
  }

  public void dppcon(String uplo, int n, double[] ap, int _ap_offset, double anorm, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dppcon(uplo, n, ap, _ap_offset, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dppequ(String uplo, int n, double[] ap, double[] s, org.netlib.util.doubleW scond, org.netlib.util.doubleW amax, org.netlib.util.intW info) {
    f2j.dppequ(uplo, n, ap, 0, s, 0, scond, amax, info);
  }

  public void dppequ(String uplo, int n, double[] ap, int _ap_offset, double[] s, int _s_offset, org.netlib.util.doubleW scond, org.netlib.util.doubleW amax, org.netlib.util.intW info) {
    f2j.dppequ(uplo, n, ap, _ap_offset, s, _s_offset, scond, amax, info);
  }

  public void dpprfs(String uplo, int n, int nrhs, double[] ap, double[] afp, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dpprfs(uplo, n, nrhs, ap, 0, afp, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void dpprfs(String uplo, int n, int nrhs, double[] ap, int _ap_offset, double[] afp, int _afp_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dpprfs(uplo, n, nrhs, ap, _ap_offset, afp, _afp_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dppsv(String uplo, int n, int nrhs, double[] ap, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dppsv(uplo, n, nrhs, ap, 0, b, 0, ldb, info);
  }

  public void dppsv(String uplo, int n, int nrhs, double[] ap, int _ap_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dppsv(uplo, n, nrhs, ap, _ap_offset, b, _b_offset, ldb, info);
  }

  public void dppsvx(String fact, String uplo, int n, int nrhs, double[] ap, double[] afp, org.netlib.util.StringW equed, double[] s, double[] b, int ldb, double[] x, int ldx, org.netlib.util.doubleW rcond, double[] ferr, double[] berr, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dppsvx(fact, uplo, n, nrhs, ap, 0, afp, 0, equed, s, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void dppsvx(String fact, String uplo, int n, int nrhs, double[] ap, int _ap_offset, double[] afp, int _afp_offset, org.netlib.util.StringW equed, double[] s, int _s_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dppsvx(fact, uplo, n, nrhs, ap, _ap_offset, afp, _afp_offset, equed, s, _s_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dpptrf(String uplo, int n, double[] ap, org.netlib.util.intW info) {
    f2j.dpptrf(uplo, n, ap, 0, info);
  }

  public void dpptrf(String uplo, int n, double[] ap, int _ap_offset, org.netlib.util.intW info) {
    f2j.dpptrf(uplo, n, ap, _ap_offset, info);
  }

  public void dpptri(String uplo, int n, double[] ap, org.netlib.util.intW info) {
    f2j.dpptri(uplo, n, ap, 0, info);
  }

  public void dpptri(String uplo, int n, double[] ap, int _ap_offset, org.netlib.util.intW info) {
    f2j.dpptri(uplo, n, ap, _ap_offset, info);
  }

  public void dpptrs(String uplo, int n, int nrhs, double[] ap, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dpptrs(uplo, n, nrhs, ap, 0, b, 0, ldb, info);
  }

  public void dpptrs(String uplo, int n, int nrhs, double[] ap, int _ap_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dpptrs(uplo, n, nrhs, ap, _ap_offset, b, _b_offset, ldb, info);
  }

  public void dptcon(int n, double[] d, double[] e, double anorm, org.netlib.util.doubleW rcond, double[] work, org.netlib.util.intW info) {
    f2j.dptcon(n, d, 0, e, 0, anorm, rcond, work, 0, info);
  }

  public void dptcon(int n, double[] d, int _d_offset, double[] e, int _e_offset, double anorm, org.netlib.util.doubleW rcond, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dptcon(n, d, _d_offset, e, _e_offset, anorm, rcond, work, _work_offset, info);
  }

  public void dpteqr(String compz, int n, double[] d, double[] e, double[] z, int ldz, double[] work, org.netlib.util.intW info) {
    f2j.dpteqr(compz, n, d, 0, e, 0, z, 0, ldz, work, 0, info);
  }

  public void dpteqr(String compz, int n, double[] d, int _d_offset, double[] e, int _e_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dpteqr(compz, n, d, _d_offset, e, _e_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  public void dptrfs(int n, int nrhs, double[] d, double[] e, double[] df, double[] ef, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, org.netlib.util.intW info) {
    f2j.dptrfs(n, nrhs, d, 0, e, 0, df, 0, ef, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, info);
  }

  public void dptrfs(int n, int nrhs, double[] d, int _d_offset, double[] e, int _e_offset, double[] df, int _df_offset, double[] ef, int _ef_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dptrfs(n, nrhs, d, _d_offset, e, _e_offset, df, _df_offset, ef, _ef_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, info);
  }

  public void dptsv(int n, int nrhs, double[] d, double[] e, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dptsv(n, nrhs, d, 0, e, 0, b, 0, ldb, info);
  }

  public void dptsv(int n, int nrhs, double[] d, int _d_offset, double[] e, int _e_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dptsv(n, nrhs, d, _d_offset, e, _e_offset, b, _b_offset, ldb, info);
  }

  public void dptsvx(String fact, int n, int nrhs, double[] d, double[] e, double[] df, double[] ef, double[] b, int ldb, double[] x, int ldx, org.netlib.util.doubleW rcond, double[] ferr, double[] berr, double[] work, org.netlib.util.intW info) {
    f2j.dptsvx(fact, n, nrhs, d, 0, e, 0, df, 0, ef, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, info);
  }

  public void dptsvx(String fact, int n, int nrhs, double[] d, int _d_offset, double[] e, int _e_offset, double[] df, int _df_offset, double[] ef, int _ef_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dptsvx(fact, n, nrhs, d, _d_offset, e, _e_offset, df, _df_offset, ef, _ef_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, info);
  }

  public void dpttrf(int n, double[] d, double[] e, org.netlib.util.intW info) {
    f2j.dpttrf(n, d, 0, e, 0, info);
  }

  public void dpttrf(int n, double[] d, int _d_offset, double[] e, int _e_offset, org.netlib.util.intW info) {
    f2j.dpttrf(n, d, _d_offset, e, _e_offset, info);
  }

  public void dpttrs(int n, int nrhs, double[] d, double[] e, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dpttrs(n, nrhs, d, 0, e, 0, b, 0, ldb, info);
  }

  public void dpttrs(int n, int nrhs, double[] d, int _d_offset, double[] e, int _e_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dpttrs(n, nrhs, d, _d_offset, e, _e_offset, b, _b_offset, ldb, info);
  }

  public void dptts2(int n, int nrhs, double[] d, double[] e, double[] b, int ldb) {
    f2j.dptts2(n, nrhs, d, 0, e, 0, b, 0, ldb);
  }

  public void dptts2(int n, int nrhs, double[] d, int _d_offset, double[] e, int _e_offset, double[] b, int _b_offset, int ldb) {
    f2j.dptts2(n, nrhs, d, _d_offset, e, _e_offset, b, _b_offset, ldb);
  }

  public void drscl(int n, double sa, double[] sx, int incx) {
    f2j.drscl(n, sa, sx, 0, incx);
  }

  public void drscl(int n, double sa, double[] sx, int _sx_offset, int incx) {
    f2j.drscl(n, sa, sx, _sx_offset, incx);
  }

  public void dsbev(String jobz, String uplo, int n, int kd, double[] ab, int ldab, double[] w, double[] z, int ldz, double[] work, org.netlib.util.intW info) {
    f2j.dsbev(jobz, uplo, n, kd, ab, 0, ldab, w, 0, z, 0, ldz, work, 0, info);
  }

  public void dsbev(String jobz, String uplo, int n, int kd, double[] ab, int _ab_offset, int ldab, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dsbev(jobz, uplo, n, kd, ab, _ab_offset, ldab, w, _w_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  public void dsbevd(String jobz, String uplo, int n, int kd, double[] ab, int ldab, double[] w, double[] z, int ldz, double[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.dsbevd(jobz, uplo, n, kd, ab, 0, ldab, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void dsbevd(String jobz, String uplo, int n, int kd, double[] ab, int _ab_offset, int ldab, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.dsbevd(jobz, uplo, n, kd, ab, _ab_offset, ldab, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void dsbevx(String jobz, String range, String uplo, int n, int kd, double[] ab, int ldab, double[] q, int ldq, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, double[] z, int ldz, double[] work, int[] iwork, int[] ifail, org.netlib.util.intW info) {
    f2j.dsbevx(jobz, range, uplo, n, kd, ab, 0, ldab, q, 0, ldq, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
  }

  public void dsbevx(String jobz, String range, String uplo, int n, int kd, double[] ab, int _ab_offset, int ldab, double[] q, int _q_offset, int ldq, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    f2j.dsbevx(jobz, range, uplo, n, kd, ab, _ab_offset, ldab, q, _q_offset, ldq, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  public void dsbgst(String vect, String uplo, int n, int ka, int kb, double[] ab, int ldab, double[] bb, int ldbb, double[] x, int ldx, double[] work, org.netlib.util.intW info) {
    f2j.dsbgst(vect, uplo, n, ka, kb, ab, 0, ldab, bb, 0, ldbb, x, 0, ldx, work, 0, info);
  }

  public void dsbgst(String vect, String uplo, int n, int ka, int kb, double[] ab, int _ab_offset, int ldab, double[] bb, int _bb_offset, int ldbb, double[] x, int _x_offset, int ldx, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dsbgst(vect, uplo, n, ka, kb, ab, _ab_offset, ldab, bb, _bb_offset, ldbb, x, _x_offset, ldx, work, _work_offset, info);
  }

  public void dsbgv(String jobz, String uplo, int n, int ka, int kb, double[] ab, int ldab, double[] bb, int ldbb, double[] w, double[] z, int ldz, double[] work, org.netlib.util.intW info) {
    f2j.dsbgv(jobz, uplo, n, ka, kb, ab, 0, ldab, bb, 0, ldbb, w, 0, z, 0, ldz, work, 0, info);
  }

  public void dsbgv(String jobz, String uplo, int n, int ka, int kb, double[] ab, int _ab_offset, int ldab, double[] bb, int _bb_offset, int ldbb, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dsbgv(jobz, uplo, n, ka, kb, ab, _ab_offset, ldab, bb, _bb_offset, ldbb, w, _w_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  public void dsbgvd(String jobz, String uplo, int n, int ka, int kb, double[] ab, int ldab, double[] bb, int ldbb, double[] w, double[] z, int ldz, double[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.dsbgvd(jobz, uplo, n, ka, kb, ab, 0, ldab, bb, 0, ldbb, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void dsbgvd(String jobz, String uplo, int n, int ka, int kb, double[] ab, int _ab_offset, int ldab, double[] bb, int _bb_offset, int ldbb, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.dsbgvd(jobz, uplo, n, ka, kb, ab, _ab_offset, ldab, bb, _bb_offset, ldbb, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void dsbgvx(String jobz, String range, String uplo, int n, int ka, int kb, double[] ab, int ldab, double[] bb, int ldbb, double[] q, int ldq, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, double[] z, int ldz, double[] work, int[] iwork, int[] ifail, org.netlib.util.intW info) {
    f2j.dsbgvx(jobz, range, uplo, n, ka, kb, ab, 0, ldab, bb, 0, ldbb, q, 0, ldq, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
  }

  public void dsbgvx(String jobz, String range, String uplo, int n, int ka, int kb, double[] ab, int _ab_offset, int ldab, double[] bb, int _bb_offset, int ldbb, double[] q, int _q_offset, int ldq, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    f2j.dsbgvx(jobz, range, uplo, n, ka, kb, ab, _ab_offset, ldab, bb, _bb_offset, ldbb, q, _q_offset, ldq, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  public void dsbtrd(String vect, String uplo, int n, int kd, double[] ab, int ldab, double[] d, double[] e, double[] q, int ldq, double[] work, org.netlib.util.intW info) {
    f2j.dsbtrd(vect, uplo, n, kd, ab, 0, ldab, d, 0, e, 0, q, 0, ldq, work, 0, info);
  }

  public void dsbtrd(String vect, String uplo, int n, int kd, double[] ab, int _ab_offset, int ldab, double[] d, int _d_offset, double[] e, int _e_offset, double[] q, int _q_offset, int ldq, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dsbtrd(vect, uplo, n, kd, ab, _ab_offset, ldab, d, _d_offset, e, _e_offset, q, _q_offset, ldq, work, _work_offset, info);
  }

  public void dsgesv(int n, int nrhs, double[] a, int lda, int[] ipiv, double[] b, int ldb, double[] x, int ldx, double[] work, float[] swork, org.netlib.util.intW iter, org.netlib.util.intW info) {
    f2j.dsgesv(n, nrhs, a, 0, lda, ipiv, 0, b, 0, ldb, x, 0, ldx, work, 0, swork, 0, iter, info);
  }

  public void dsgesv(int n, int nrhs, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] work, int _work_offset, float[] swork, int _swork_offset, org.netlib.util.intW iter, org.netlib.util.intW info) {
    f2j.dsgesv(n, nrhs, a, _a_offset, lda, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, work, _work_offset, swork, _swork_offset, iter, info);
  }

  public void dspcon(String uplo, int n, double[] ap, int[] ipiv, double anorm, org.netlib.util.doubleW rcond, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dspcon(uplo, n, ap, 0, ipiv, 0, anorm, rcond, work, 0, iwork, 0, info);
  }

  public void dspcon(String uplo, int n, double[] ap, int _ap_offset, int[] ipiv, int _ipiv_offset, double anorm, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dspcon(uplo, n, ap, _ap_offset, ipiv, _ipiv_offset, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dspev(String jobz, String uplo, int n, double[] ap, double[] w, double[] z, int ldz, double[] work, org.netlib.util.intW info) {
    f2j.dspev(jobz, uplo, n, ap, 0, w, 0, z, 0, ldz, work, 0, info);
  }

  public void dspev(String jobz, String uplo, int n, double[] ap, int _ap_offset, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dspev(jobz, uplo, n, ap, _ap_offset, w, _w_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  public void dspevd(String jobz, String uplo, int n, double[] ap, double[] w, double[] z, int ldz, double[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.dspevd(jobz, uplo, n, ap, 0, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void dspevd(String jobz, String uplo, int n, double[] ap, int _ap_offset, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.dspevd(jobz, uplo, n, ap, _ap_offset, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void dspevx(String jobz, String range, String uplo, int n, double[] ap, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, double[] z, int ldz, double[] work, int[] iwork, int[] ifail, org.netlib.util.intW info) {
    f2j.dspevx(jobz, range, uplo, n, ap, 0, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
  }

  public void dspevx(String jobz, String range, String uplo, int n, double[] ap, int _ap_offset, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    f2j.dspevx(jobz, range, uplo, n, ap, _ap_offset, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  public void dspgst(int itype, String uplo, int n, double[] ap, double[] bp, org.netlib.util.intW info) {
    f2j.dspgst(itype, uplo, n, ap, 0, bp, 0, info);
  }

  public void dspgst(int itype, String uplo, int n, double[] ap, int _ap_offset, double[] bp, int _bp_offset, org.netlib.util.intW info) {
    f2j.dspgst(itype, uplo, n, ap, _ap_offset, bp, _bp_offset, info);
  }

  public void dspgv(int itype, String jobz, String uplo, int n, double[] ap, double[] bp, double[] w, double[] z, int ldz, double[] work, org.netlib.util.intW info) {
    f2j.dspgv(itype, jobz, uplo, n, ap, 0, bp, 0, w, 0, z, 0, ldz, work, 0, info);
  }

  public void dspgv(int itype, String jobz, String uplo, int n, double[] ap, int _ap_offset, double[] bp, int _bp_offset, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dspgv(itype, jobz, uplo, n, ap, _ap_offset, bp, _bp_offset, w, _w_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  public void dspgvd(int itype, String jobz, String uplo, int n, double[] ap, double[] bp, double[] w, double[] z, int ldz, double[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.dspgvd(itype, jobz, uplo, n, ap, 0, bp, 0, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void dspgvd(int itype, String jobz, String uplo, int n, double[] ap, int _ap_offset, double[] bp, int _bp_offset, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.dspgvd(itype, jobz, uplo, n, ap, _ap_offset, bp, _bp_offset, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void dspgvx(int itype, String jobz, String range, String uplo, int n, double[] ap, double[] bp, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, double[] z, int ldz, double[] work, int[] iwork, int[] ifail, org.netlib.util.intW info) {
    f2j.dspgvx(itype, jobz, range, uplo, n, ap, 0, bp, 0, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
  }

  public void dspgvx(int itype, String jobz, String range, String uplo, int n, double[] ap, int _ap_offset, double[] bp, int _bp_offset, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    f2j.dspgvx(itype, jobz, range, uplo, n, ap, _ap_offset, bp, _bp_offset, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  public void dsprfs(String uplo, int n, int nrhs, double[] ap, double[] afp, int[] ipiv, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dsprfs(uplo, n, nrhs, ap, 0, afp, 0, ipiv, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void dsprfs(String uplo, int n, int nrhs, double[] ap, int _ap_offset, double[] afp, int _afp_offset, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dsprfs(uplo, n, nrhs, ap, _ap_offset, afp, _afp_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dspsv(String uplo, int n, int nrhs, double[] ap, int[] ipiv, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dspsv(uplo, n, nrhs, ap, 0, ipiv, 0, b, 0, ldb, info);
  }

  public void dspsv(String uplo, int n, int nrhs, double[] ap, int _ap_offset, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dspsv(uplo, n, nrhs, ap, _ap_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  public void dspsvx(String fact, String uplo, int n, int nrhs, double[] ap, double[] afp, int[] ipiv, double[] b, int ldb, double[] x, int ldx, org.netlib.util.doubleW rcond, double[] ferr, double[] berr, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dspsvx(fact, uplo, n, nrhs, ap, 0, afp, 0, ipiv, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void dspsvx(String fact, String uplo, int n, int nrhs, double[] ap, int _ap_offset, double[] afp, int _afp_offset, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dspsvx(fact, uplo, n, nrhs, ap, _ap_offset, afp, _afp_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dsptrd(String uplo, int n, double[] ap, double[] d, double[] e, double[] tau, org.netlib.util.intW info) {
    f2j.dsptrd(uplo, n, ap, 0, d, 0, e, 0, tau, 0, info);
  }

  public void dsptrd(String uplo, int n, double[] ap, int _ap_offset, double[] d, int _d_offset, double[] e, int _e_offset, double[] tau, int _tau_offset, org.netlib.util.intW info) {
    f2j.dsptrd(uplo, n, ap, _ap_offset, d, _d_offset, e, _e_offset, tau, _tau_offset, info);
  }

  public void dsptrf(String uplo, int n, double[] ap, int[] ipiv, org.netlib.util.intW info) {
    f2j.dsptrf(uplo, n, ap, 0, ipiv, 0, info);
  }

  public void dsptrf(String uplo, int n, double[] ap, int _ap_offset, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    f2j.dsptrf(uplo, n, ap, _ap_offset, ipiv, _ipiv_offset, info);
  }

  public void dsptri(String uplo, int n, double[] ap, int[] ipiv, double[] work, org.netlib.util.intW info) {
    f2j.dsptri(uplo, n, ap, 0, ipiv, 0, work, 0, info);
  }

  public void dsptri(String uplo, int n, double[] ap, int _ap_offset, int[] ipiv, int _ipiv_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dsptri(uplo, n, ap, _ap_offset, ipiv, _ipiv_offset, work, _work_offset, info);
  }

  public void dsptrs(String uplo, int n, int nrhs, double[] ap, int[] ipiv, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dsptrs(uplo, n, nrhs, ap, 0, ipiv, 0, b, 0, ldb, info);
  }

  public void dsptrs(String uplo, int n, int nrhs, double[] ap, int _ap_offset, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dsptrs(uplo, n, nrhs, ap, _ap_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  public void dstebz(String range, String order, int n, double vl, double vu, int il, int iu, double abstol, double[] d, double[] e, org.netlib.util.intW m, org.netlib.util.intW nsplit, double[] w, int[] iblock, int[] isplit, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dstebz(range, order, n, vl, vu, il, iu, abstol, d, 0, e, 0, m, nsplit, w, 0, iblock, 0, isplit, 0, work, 0, iwork, 0, info);
  }

  public void dstebz(String range, String order, int n, double vl, double vu, int il, int iu, double abstol, double[] d, int _d_offset, double[] e, int _e_offset, org.netlib.util.intW m, org.netlib.util.intW nsplit, double[] w, int _w_offset, int[] iblock, int _iblock_offset, int[] isplit, int _isplit_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dstebz(range, order, n, vl, vu, il, iu, abstol, d, _d_offset, e, _e_offset, m, nsplit, w, _w_offset, iblock, _iblock_offset, isplit, _isplit_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dstedc(String compz, int n, double[] d, double[] e, double[] z, int ldz, double[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.dstedc(compz, n, d, 0, e, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void dstedc(String compz, int n, double[] d, int _d_offset, double[] e, int _e_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.dstedc(compz, n, d, _d_offset, e, _e_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void dstegr(String jobz, String range, int n, double[] d, double[] e, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, double[] z, int ldz, int[] isuppz, double[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.dstegr(jobz, range, n, d, 0, e, 0, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, isuppz, 0, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void dstegr(String jobz, String range, int n, double[] d, int _d_offset, double[] e, int _e_offset, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, int[] isuppz, int _isuppz_offset, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.dstegr(jobz, range, n, d, _d_offset, e, _e_offset, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, isuppz, _isuppz_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void dstein(int n, double[] d, double[] e, int m, double[] w, int[] iblock, int[] isplit, double[] z, int ldz, double[] work, int[] iwork, int[] ifail, org.netlib.util.intW info) {
    f2j.dstein(n, d, 0, e, 0, m, w, 0, iblock, 0, isplit, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
  }

  public void dstein(int n, double[] d, int _d_offset, double[] e, int _e_offset, int m, double[] w, int _w_offset, int[] iblock, int _iblock_offset, int[] isplit, int _isplit_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    f2j.dstein(n, d, _d_offset, e, _e_offset, m, w, _w_offset, iblock, _iblock_offset, isplit, _isplit_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  public void dstemr(String jobz, String range, int n, double[] d, double[] e, double vl, double vu, int il, int iu, org.netlib.util.intW m, double[] w, double[] z, int ldz, int nzc, int[] isuppz, org.netlib.util.booleanW tryrac, double[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.dstemr(jobz, range, n, d, 0, e, 0, vl, vu, il, iu, m, w, 0, z, 0, ldz, nzc, isuppz, 0, tryrac, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void dstemr(String jobz, String range, int n, double[] d, int _d_offset, double[] e, int _e_offset, double vl, double vu, int il, int iu, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, int nzc, int[] isuppz, int _isuppz_offset, org.netlib.util.booleanW tryrac, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.dstemr(jobz, range, n, d, _d_offset, e, _e_offset, vl, vu, il, iu, m, w, _w_offset, z, _z_offset, ldz, nzc, isuppz, _isuppz_offset, tryrac, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void dsteqr(String compz, int n, double[] d, double[] e, double[] z, int ldz, double[] work, org.netlib.util.intW info) {
    f2j.dsteqr(compz, n, d, 0, e, 0, z, 0, ldz, work, 0, info);
  }

  public void dsteqr(String compz, int n, double[] d, int _d_offset, double[] e, int _e_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dsteqr(compz, n, d, _d_offset, e, _e_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  public void dsterf(int n, double[] d, double[] e, org.netlib.util.intW info) {
    f2j.dsterf(n, d, 0, e, 0, info);
  }

  public void dsterf(int n, double[] d, int _d_offset, double[] e, int _e_offset, org.netlib.util.intW info) {
    f2j.dsterf(n, d, _d_offset, e, _e_offset, info);
  }

  public void dstev(String jobz, int n, double[] d, double[] e, double[] z, int ldz, double[] work, org.netlib.util.intW info) {
    f2j.dstev(jobz, n, d, 0, e, 0, z, 0, ldz, work, 0, info);
  }

  public void dstev(String jobz, int n, double[] d, int _d_offset, double[] e, int _e_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dstev(jobz, n, d, _d_offset, e, _e_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  public void dstevd(String jobz, int n, double[] d, double[] e, double[] z, int ldz, double[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.dstevd(jobz, n, d, 0, e, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void dstevd(String jobz, int n, double[] d, int _d_offset, double[] e, int _e_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.dstevd(jobz, n, d, _d_offset, e, _e_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void dstevr(String jobz, String range, int n, double[] d, double[] e, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, double[] z, int ldz, int[] isuppz, double[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.dstevr(jobz, range, n, d, 0, e, 0, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, isuppz, 0, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void dstevr(String jobz, String range, int n, double[] d, int _d_offset, double[] e, int _e_offset, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, int[] isuppz, int _isuppz_offset, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.dstevr(jobz, range, n, d, _d_offset, e, _e_offset, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, isuppz, _isuppz_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void dstevx(String jobz, String range, int n, double[] d, double[] e, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, double[] z, int ldz, double[] work, int[] iwork, int[] ifail, org.netlib.util.intW info) {
    f2j.dstevx(jobz, range, n, d, 0, e, 0, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
  }

  public void dstevx(String jobz, String range, int n, double[] d, int _d_offset, double[] e, int _e_offset, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    f2j.dstevx(jobz, range, n, d, _d_offset, e, _e_offset, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  public void dsycon(String uplo, int n, double[] a, int lda, int[] ipiv, double anorm, org.netlib.util.doubleW rcond, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dsycon(uplo, n, a, 0, lda, ipiv, 0, anorm, rcond, work, 0, iwork, 0, info);
  }

  public void dsycon(String uplo, int n, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, double anorm, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dsycon(uplo, n, a, _a_offset, lda, ipiv, _ipiv_offset, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dsyev(String jobz, String uplo, int n, double[] a, int lda, double[] w, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dsyev(jobz, uplo, n, a, 0, lda, w, 0, work, 0, lwork, info);
  }

  public void dsyev(String jobz, String uplo, int n, double[] a, int _a_offset, int lda, double[] w, int _w_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dsyev(jobz, uplo, n, a, _a_offset, lda, w, _w_offset, work, _work_offset, lwork, info);
  }

  public void dsyevd(String jobz, String uplo, int n, double[] a, int lda, double[] w, double[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.dsyevd(jobz, uplo, n, a, 0, lda, w, 0, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void dsyevd(String jobz, String uplo, int n, double[] a, int _a_offset, int lda, double[] w, int _w_offset, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.dsyevd(jobz, uplo, n, a, _a_offset, lda, w, _w_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void dsyevr(String jobz, String range, String uplo, int n, double[] a, int lda, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, double[] z, int ldz, int[] isuppz, double[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.dsyevr(jobz, range, uplo, n, a, 0, lda, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, isuppz, 0, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void dsyevr(String jobz, String range, String uplo, int n, double[] a, int _a_offset, int lda, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, int[] isuppz, int _isuppz_offset, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.dsyevr(jobz, range, uplo, n, a, _a_offset, lda, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, isuppz, _isuppz_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void dsyevx(String jobz, String range, String uplo, int n, double[] a, int lda, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, double[] z, int ldz, double[] work, int lwork, int[] iwork, int[] ifail, org.netlib.util.intW info) {
    f2j.dsyevx(jobz, range, uplo, n, a, 0, lda, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, ifail, 0, info);
  }

  public void dsyevx(String jobz, String range, String uplo, int n, double[] a, int _a_offset, int lda, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    f2j.dsyevx(jobz, range, uplo, n, a, _a_offset, lda, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  public void dsygs2(int itype, String uplo, int n, double[] a, int lda, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dsygs2(itype, uplo, n, a, 0, lda, b, 0, ldb, info);
  }

  public void dsygs2(int itype, String uplo, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dsygs2(itype, uplo, n, a, _a_offset, lda, b, _b_offset, ldb, info);
  }

  public void dsygst(int itype, String uplo, int n, double[] a, int lda, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dsygst(itype, uplo, n, a, 0, lda, b, 0, ldb, info);
  }

  public void dsygst(int itype, String uplo, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dsygst(itype, uplo, n, a, _a_offset, lda, b, _b_offset, ldb, info);
  }

  public void dsygv(int itype, String jobz, String uplo, int n, double[] a, int lda, double[] b, int ldb, double[] w, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dsygv(itype, jobz, uplo, n, a, 0, lda, b, 0, ldb, w, 0, work, 0, lwork, info);
  }

  public void dsygv(int itype, String jobz, String uplo, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] w, int _w_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dsygv(itype, jobz, uplo, n, a, _a_offset, lda, b, _b_offset, ldb, w, _w_offset, work, _work_offset, lwork, info);
  }

  public void dsygvd(int itype, String jobz, String uplo, int n, double[] a, int lda, double[] b, int ldb, double[] w, double[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.dsygvd(itype, jobz, uplo, n, a, 0, lda, b, 0, ldb, w, 0, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void dsygvd(int itype, String jobz, String uplo, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] w, int _w_offset, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.dsygvd(itype, jobz, uplo, n, a, _a_offset, lda, b, _b_offset, ldb, w, _w_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void dsygvx(int itype, String jobz, String range, String uplo, int n, double[] a, int lda, double[] b, int ldb, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, double[] z, int ldz, double[] work, int lwork, int[] iwork, int[] ifail, org.netlib.util.intW info) {
    f2j.dsygvx(itype, jobz, range, uplo, n, a, 0, lda, b, 0, ldb, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, ifail, 0, info);
  }

  public void dsygvx(int itype, String jobz, String range, String uplo, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double vl, double vu, int il, int iu, double abstol, org.netlib.util.intW m, double[] w, int _w_offset, double[] z, int _z_offset, int ldz, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    f2j.dsygvx(itype, jobz, range, uplo, n, a, _a_offset, lda, b, _b_offset, ldb, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  public void dsyrfs(String uplo, int n, int nrhs, double[] a, int lda, double[] af, int ldaf, int[] ipiv, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dsyrfs(uplo, n, nrhs, a, 0, lda, af, 0, ldaf, ipiv, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void dsyrfs(String uplo, int n, int nrhs, double[] a, int _a_offset, int lda, double[] af, int _af_offset, int ldaf, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dsyrfs(uplo, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dsysv(String uplo, int n, int nrhs, double[] a, int lda, int[] ipiv, double[] b, int ldb, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dsysv(uplo, n, nrhs, a, 0, lda, ipiv, 0, b, 0, ldb, work, 0, lwork, info);
  }

  public void dsysv(String uplo, int n, int nrhs, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dsysv(uplo, n, nrhs, a, _a_offset, lda, ipiv, _ipiv_offset, b, _b_offset, ldb, work, _work_offset, lwork, info);
  }

  public void dsysvx(String fact, String uplo, int n, int nrhs, double[] a, int lda, double[] af, int ldaf, int[] ipiv, double[] b, int ldb, double[] x, int ldx, org.netlib.util.doubleW rcond, double[] ferr, double[] berr, double[] work, int lwork, int[] iwork, org.netlib.util.intW info) {
    f2j.dsysvx(fact, uplo, n, nrhs, a, 0, lda, af, 0, ldaf, ipiv, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, lwork, iwork, 0, info);
  }

  public void dsysvx(String fact, String uplo, int n, int nrhs, double[] a, int _a_offset, int lda, double[] af, int _af_offset, int ldaf, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, org.netlib.util.doubleW rcond, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dsysvx(fact, uplo, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  public void dsytd2(String uplo, int n, double[] a, int lda, double[] d, double[] e, double[] tau, org.netlib.util.intW info) {
    f2j.dsytd2(uplo, n, a, 0, lda, d, 0, e, 0, tau, 0, info);
  }

  public void dsytd2(String uplo, int n, double[] a, int _a_offset, int lda, double[] d, int _d_offset, double[] e, int _e_offset, double[] tau, int _tau_offset, org.netlib.util.intW info) {
    f2j.dsytd2(uplo, n, a, _a_offset, lda, d, _d_offset, e, _e_offset, tau, _tau_offset, info);
  }

  public void dsytf2(String uplo, int n, double[] a, int lda, int[] ipiv, org.netlib.util.intW info) {
    f2j.dsytf2(uplo, n, a, 0, lda, ipiv, 0, info);
  }

  public void dsytf2(String uplo, int n, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    f2j.dsytf2(uplo, n, a, _a_offset, lda, ipiv, _ipiv_offset, info);
  }

  public void dsytrd(String uplo, int n, double[] a, int lda, double[] d, double[] e, double[] tau, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dsytrd(uplo, n, a, 0, lda, d, 0, e, 0, tau, 0, work, 0, lwork, info);
  }

  public void dsytrd(String uplo, int n, double[] a, int _a_offset, int lda, double[] d, int _d_offset, double[] e, int _e_offset, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dsytrd(uplo, n, a, _a_offset, lda, d, _d_offset, e, _e_offset, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void dsytrf(String uplo, int n, double[] a, int lda, int[] ipiv, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dsytrf(uplo, n, a, 0, lda, ipiv, 0, work, 0, lwork, info);
  }

  public void dsytrf(String uplo, int n, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dsytrf(uplo, n, a, _a_offset, lda, ipiv, _ipiv_offset, work, _work_offset, lwork, info);
  }

  public void dsytri(String uplo, int n, double[] a, int lda, int[] ipiv, double[] work, org.netlib.util.intW info) {
    f2j.dsytri(uplo, n, a, 0, lda, ipiv, 0, work, 0, info);
  }

  public void dsytri(String uplo, int n, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dsytri(uplo, n, a, _a_offset, lda, ipiv, _ipiv_offset, work, _work_offset, info);
  }

  public void dsytrs(String uplo, int n, int nrhs, double[] a, int lda, int[] ipiv, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dsytrs(uplo, n, nrhs, a, 0, lda, ipiv, 0, b, 0, ldb, info);
  }

  public void dsytrs(String uplo, int n, int nrhs, double[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dsytrs(uplo, n, nrhs, a, _a_offset, lda, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  public void dtbcon(String norm, String uplo, String diag, int n, int kd, double[] ab, int ldab, org.netlib.util.doubleW rcond, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dtbcon(norm, uplo, diag, n, kd, ab, 0, ldab, rcond, work, 0, iwork, 0, info);
  }

  public void dtbcon(String norm, String uplo, String diag, int n, int kd, double[] ab, int _ab_offset, int ldab, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dtbcon(norm, uplo, diag, n, kd, ab, _ab_offset, ldab, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dtbrfs(String uplo, String trans, String diag, int n, int kd, int nrhs, double[] ab, int ldab, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dtbrfs(uplo, trans, diag, n, kd, nrhs, ab, 0, ldab, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void dtbrfs(String uplo, String trans, String diag, int n, int kd, int nrhs, double[] ab, int _ab_offset, int ldab, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dtbrfs(uplo, trans, diag, n, kd, nrhs, ab, _ab_offset, ldab, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dtbtrs(String uplo, String trans, String diag, int n, int kd, int nrhs, double[] ab, int ldab, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dtbtrs(uplo, trans, diag, n, kd, nrhs, ab, 0, ldab, b, 0, ldb, info);
  }

  public void dtbtrs(String uplo, String trans, String diag, int n, int kd, int nrhs, double[] ab, int _ab_offset, int ldab, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dtbtrs(uplo, trans, diag, n, kd, nrhs, ab, _ab_offset, ldab, b, _b_offset, ldb, info);
  }

  public void dtgevc(String side, String howmny, boolean[] select, int n, double[] s, int lds, double[] p, int ldp, double[] vl, int ldvl, double[] vr, int ldvr, int mm, org.netlib.util.intW m, double[] work, org.netlib.util.intW info) {
    f2j.dtgevc(side, howmny, select, 0, n, s, 0, lds, p, 0, ldp, vl, 0, ldvl, vr, 0, ldvr, mm, m, work, 0, info);
  }

  public void dtgevc(String side, String howmny, boolean[] select, int _select_offset, int n, double[] s, int _s_offset, int lds, double[] p, int _p_offset, int ldp, double[] vl, int _vl_offset, int ldvl, double[] vr, int _vr_offset, int ldvr, int mm, org.netlib.util.intW m, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dtgevc(side, howmny, select, _select_offset, n, s, _s_offset, lds, p, _p_offset, ldp, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, mm, m, work, _work_offset, info);
  }

  public void dtgex2(boolean wantq, boolean wantz, int n, double[] a, int lda, double[] b, int ldb, double[] q, int ldq, double[] z, int ldz, int j1, int n1, int n2, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dtgex2(wantq, wantz, n, a, 0, lda, b, 0, ldb, q, 0, ldq, z, 0, ldz, j1, n1, n2, work, 0, lwork, info);
  }

  public void dtgex2(boolean wantq, boolean wantz, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] q, int _q_offset, int ldq, double[] z, int _z_offset, int ldz, int j1, int n1, int n2, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dtgex2(wantq, wantz, n, a, _a_offset, lda, b, _b_offset, ldb, q, _q_offset, ldq, z, _z_offset, ldz, j1, n1, n2, work, _work_offset, lwork, info);
  }

  public void dtgexc(boolean wantq, boolean wantz, int n, double[] a, int lda, double[] b, int ldb, double[] q, int ldq, double[] z, int ldz, org.netlib.util.intW ifst, org.netlib.util.intW ilst, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dtgexc(wantq, wantz, n, a, 0, lda, b, 0, ldb, q, 0, ldq, z, 0, ldz, ifst, ilst, work, 0, lwork, info);
  }

  public void dtgexc(boolean wantq, boolean wantz, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] q, int _q_offset, int ldq, double[] z, int _z_offset, int ldz, org.netlib.util.intW ifst, org.netlib.util.intW ilst, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dtgexc(wantq, wantz, n, a, _a_offset, lda, b, _b_offset, ldb, q, _q_offset, ldq, z, _z_offset, ldz, ifst, ilst, work, _work_offset, lwork, info);
  }

  public void dtgsen(int ijob, boolean wantq, boolean wantz, boolean[] select, int n, double[] a, int lda, double[] b, int ldb, double[] alphar, double[] alphai, double[] beta, double[] q, int ldq, double[] z, int ldz, org.netlib.util.intW m, org.netlib.util.doubleW pl, org.netlib.util.doubleW pr, double[] dif, double[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.dtgsen(ijob, wantq, wantz, select, 0, n, a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, q, 0, ldq, z, 0, ldz, m, pl, pr, dif, 0, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void dtgsen(int ijob, boolean wantq, boolean wantz, boolean[] select, int _select_offset, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] alphar, int _alphar_offset, double[] alphai, int _alphai_offset, double[] beta, int _beta_offset, double[] q, int _q_offset, int ldq, double[] z, int _z_offset, int ldz, org.netlib.util.intW m, org.netlib.util.doubleW pl, org.netlib.util.doubleW pr, double[] dif, int _dif_offset, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.dtgsen(ijob, wantq, wantz, select, _select_offset, n, a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, q, _q_offset, ldq, z, _z_offset, ldz, m, pl, pr, dif, _dif_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void dtgsja(String jobu, String jobv, String jobq, int m, int p, int n, int k, int l, double[] a, int lda, double[] b, int ldb, double tola, double tolb, double[] alpha, double[] beta, double[] u, int ldu, double[] v, int ldv, double[] q, int ldq, double[] work, org.netlib.util.intW ncycle, org.netlib.util.intW info) {
    f2j.dtgsja(jobu, jobv, jobq, m, p, n, k, l, a, 0, lda, b, 0, ldb, tola, tolb, alpha, 0, beta, 0, u, 0, ldu, v, 0, ldv, q, 0, ldq, work, 0, ncycle, info);
  }

  public void dtgsja(String jobu, String jobv, String jobq, int m, int p, int n, int k, int l, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double tola, double tolb, double[] alpha, int _alpha_offset, double[] beta, int _beta_offset, double[] u, int _u_offset, int ldu, double[] v, int _v_offset, int ldv, double[] q, int _q_offset, int ldq, double[] work, int _work_offset, org.netlib.util.intW ncycle, org.netlib.util.intW info) {
    f2j.dtgsja(jobu, jobv, jobq, m, p, n, k, l, a, _a_offset, lda, b, _b_offset, ldb, tola, tolb, alpha, _alpha_offset, beta, _beta_offset, u, _u_offset, ldu, v, _v_offset, ldv, q, _q_offset, ldq, work, _work_offset, ncycle, info);
  }

  public void dtgsna(String job, String howmny, boolean[] select, int n, double[] a, int lda, double[] b, int ldb, double[] vl, int ldvl, double[] vr, int ldvr, double[] s, double[] dif, int mm, org.netlib.util.intW m, double[] work, int lwork, int[] iwork, org.netlib.util.intW info) {
    f2j.dtgsna(job, howmny, select, 0, n, a, 0, lda, b, 0, ldb, vl, 0, ldvl, vr, 0, ldvr, s, 0, dif, 0, mm, m, work, 0, lwork, iwork, 0, info);
  }

  public void dtgsna(String job, String howmny, boolean[] select, int _select_offset, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] vl, int _vl_offset, int ldvl, double[] vr, int _vr_offset, int ldvr, double[] s, int _s_offset, double[] dif, int _dif_offset, int mm, org.netlib.util.intW m, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dtgsna(job, howmny, select, _select_offset, n, a, _a_offset, lda, b, _b_offset, ldb, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, s, _s_offset, dif, _dif_offset, mm, m, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  public void dtgsy2(String trans, int ijob, int m, int n, double[] a, int lda, double[] b, int ldb, double[] c, int Ldc, double[] d, int ldd, double[] e, int lde, double[] f, int ldf, org.netlib.util.doubleW scale, org.netlib.util.doubleW rdsum, org.netlib.util.doubleW rdscal, int[] iwork, org.netlib.util.intW pq, org.netlib.util.intW info) {
    f2j.dtgsy2(trans, ijob, m, n, a, 0, lda, b, 0, ldb, c, 0, Ldc, d, 0, ldd, e, 0, lde, f, 0, ldf, scale, rdsum, rdscal, iwork, 0, pq, info);
  }

  public void dtgsy2(String trans, int ijob, int m, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] c, int _c_offset, int Ldc, double[] d, int _d_offset, int ldd, double[] e, int _e_offset, int lde, double[] f, int _f_offset, int ldf, org.netlib.util.doubleW scale, org.netlib.util.doubleW rdsum, org.netlib.util.doubleW rdscal, int[] iwork, int _iwork_offset, org.netlib.util.intW pq, org.netlib.util.intW info) {
    f2j.dtgsy2(trans, ijob, m, n, a, _a_offset, lda, b, _b_offset, ldb, c, _c_offset, Ldc, d, _d_offset, ldd, e, _e_offset, lde, f, _f_offset, ldf, scale, rdsum, rdscal, iwork, _iwork_offset, pq, info);
  }

  public void dtgsyl(String trans, int ijob, int m, int n, double[] a, int lda, double[] b, int ldb, double[] c, int Ldc, double[] d, int ldd, double[] e, int lde, double[] f, int ldf, org.netlib.util.doubleW scale, org.netlib.util.doubleW dif, double[] work, int lwork, int[] iwork, org.netlib.util.intW info) {
    f2j.dtgsyl(trans, ijob, m, n, a, 0, lda, b, 0, ldb, c, 0, Ldc, d, 0, ldd, e, 0, lde, f, 0, ldf, scale, dif, work, 0, lwork, iwork, 0, info);
  }

  public void dtgsyl(String trans, int ijob, int m, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] c, int _c_offset, int Ldc, double[] d, int _d_offset, int ldd, double[] e, int _e_offset, int lde, double[] f, int _f_offset, int ldf, org.netlib.util.doubleW scale, org.netlib.util.doubleW dif, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dtgsyl(trans, ijob, m, n, a, _a_offset, lda, b, _b_offset, ldb, c, _c_offset, Ldc, d, _d_offset, ldd, e, _e_offset, lde, f, _f_offset, ldf, scale, dif, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  public void dtpcon(String norm, String uplo, String diag, int n, double[] ap, org.netlib.util.doubleW rcond, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dtpcon(norm, uplo, diag, n, ap, 0, rcond, work, 0, iwork, 0, info);
  }

  public void dtpcon(String norm, String uplo, String diag, int n, double[] ap, int _ap_offset, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dtpcon(norm, uplo, diag, n, ap, _ap_offset, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dtprfs(String uplo, String trans, String diag, int n, int nrhs, double[] ap, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dtprfs(uplo, trans, diag, n, nrhs, ap, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void dtprfs(String uplo, String trans, String diag, int n, int nrhs, double[] ap, int _ap_offset, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dtprfs(uplo, trans, diag, n, nrhs, ap, _ap_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dtptri(String uplo, String diag, int n, double[] ap, org.netlib.util.intW info) {
    f2j.dtptri(uplo, diag, n, ap, 0, info);
  }

  public void dtptri(String uplo, String diag, int n, double[] ap, int _ap_offset, org.netlib.util.intW info) {
    f2j.dtptri(uplo, diag, n, ap, _ap_offset, info);
  }

  public void dtptrs(String uplo, String trans, String diag, int n, int nrhs, double[] ap, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dtptrs(uplo, trans, diag, n, nrhs, ap, 0, b, 0, ldb, info);
  }

  public void dtptrs(String uplo, String trans, String diag, int n, int nrhs, double[] ap, int _ap_offset, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dtptrs(uplo, trans, diag, n, nrhs, ap, _ap_offset, b, _b_offset, ldb, info);
  }

  public void dtrcon(String norm, String uplo, String diag, int n, double[] a, int lda, org.netlib.util.doubleW rcond, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dtrcon(norm, uplo, diag, n, a, 0, lda, rcond, work, 0, iwork, 0, info);
  }

  public void dtrcon(String norm, String uplo, String diag, int n, double[] a, int _a_offset, int lda, org.netlib.util.doubleW rcond, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dtrcon(norm, uplo, diag, n, a, _a_offset, lda, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dtrevc(String side, String howmny, boolean[] select, int n, double[] t, int ldt, double[] vl, int ldvl, double[] vr, int ldvr, int mm, org.netlib.util.intW m, double[] work, org.netlib.util.intW info) {
    f2j.dtrevc(side, howmny, select, 0, n, t, 0, ldt, vl, 0, ldvl, vr, 0, ldvr, mm, m, work, 0, info);
  }

  public void dtrevc(String side, String howmny, boolean[] select, int _select_offset, int n, double[] t, int _t_offset, int ldt, double[] vl, int _vl_offset, int ldvl, double[] vr, int _vr_offset, int ldvr, int mm, org.netlib.util.intW m, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dtrevc(side, howmny, select, _select_offset, n, t, _t_offset, ldt, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, mm, m, work, _work_offset, info);
  }

  public void dtrexc(String compq, int n, double[] t, int ldt, double[] q, int ldq, org.netlib.util.intW ifst, org.netlib.util.intW ilst, double[] work, org.netlib.util.intW info) {
    f2j.dtrexc(compq, n, t, 0, ldt, q, 0, ldq, ifst, ilst, work, 0, info);
  }

  public void dtrexc(String compq, int n, double[] t, int _t_offset, int ldt, double[] q, int _q_offset, int ldq, org.netlib.util.intW ifst, org.netlib.util.intW ilst, double[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.dtrexc(compq, n, t, _t_offset, ldt, q, _q_offset, ldq, ifst, ilst, work, _work_offset, info);
  }

  public void dtrrfs(String uplo, String trans, String diag, int n, int nrhs, double[] a, int lda, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.dtrrfs(uplo, trans, diag, n, nrhs, a, 0, lda, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void dtrrfs(String uplo, String trans, String diag, int n, int nrhs, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] x, int _x_offset, int ldx, double[] ferr, int _ferr_offset, double[] berr, int _berr_offset, double[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dtrrfs(uplo, trans, diag, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void dtrsen(String job, String compq, boolean[] select, int n, double[] t, int ldt, double[] q, int ldq, double[] wr, double[] wi, org.netlib.util.intW m, org.netlib.util.doubleW s, org.netlib.util.doubleW sep, double[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.dtrsen(job, compq, select, 0, n, t, 0, ldt, q, 0, ldq, wr, 0, wi, 0, m, s, sep, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void dtrsen(String job, String compq, boolean[] select, int _select_offset, int n, double[] t, int _t_offset, int ldt, double[] q, int _q_offset, int ldq, double[] wr, int _wr_offset, double[] wi, int _wi_offset, org.netlib.util.intW m, org.netlib.util.doubleW s, org.netlib.util.doubleW sep, double[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.dtrsen(job, compq, select, _select_offset, n, t, _t_offset, ldt, q, _q_offset, ldq, wr, _wr_offset, wi, _wi_offset, m, s, sep, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void dtrsna(String job, String howmny, boolean[] select, int n, double[] t, int ldt, double[] vl, int ldvl, double[] vr, int ldvr, double[] s, double[] sep, int mm, org.netlib.util.intW m, double[] work, int ldwork, int[] iwork, org.netlib.util.intW info) {
    f2j.dtrsna(job, howmny, select, 0, n, t, 0, ldt, vl, 0, ldvl, vr, 0, ldvr, s, 0, sep, 0, mm, m, work, 0, ldwork, iwork, 0, info);
  }

  public void dtrsna(String job, String howmny, boolean[] select, int _select_offset, int n, double[] t, int _t_offset, int ldt, double[] vl, int _vl_offset, int ldvl, double[] vr, int _vr_offset, int ldvr, double[] s, int _s_offset, double[] sep, int _sep_offset, int mm, org.netlib.util.intW m, double[] work, int _work_offset, int ldwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.dtrsna(job, howmny, select, _select_offset, n, t, _t_offset, ldt, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, s, _s_offset, sep, _sep_offset, mm, m, work, _work_offset, ldwork, iwork, _iwork_offset, info);
  }

  public void dtrsyl(String trana, String tranb, int isgn, int m, int n, double[] a, int lda, double[] b, int ldb, double[] c, int Ldc, org.netlib.util.doubleW scale, org.netlib.util.intW info) {
    f2j.dtrsyl(trana, tranb, isgn, m, n, a, 0, lda, b, 0, ldb, c, 0, Ldc, scale, info);
  }

  public void dtrsyl(String trana, String tranb, int isgn, int m, int n, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, double[] c, int _c_offset, int Ldc, org.netlib.util.doubleW scale, org.netlib.util.intW info) {
    f2j.dtrsyl(trana, tranb, isgn, m, n, a, _a_offset, lda, b, _b_offset, ldb, c, _c_offset, Ldc, scale, info);
  }

  public void dtrti2(String uplo, String diag, int n, double[] a, int lda, org.netlib.util.intW info) {
    f2j.dtrti2(uplo, diag, n, a, 0, lda, info);
  }

  public void dtrti2(String uplo, String diag, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    f2j.dtrti2(uplo, diag, n, a, _a_offset, lda, info);
  }

  public void dtrtri(String uplo, String diag, int n, double[] a, int lda, org.netlib.util.intW info) {
    f2j.dtrtri(uplo, diag, n, a, 0, lda, info);
  }

  public void dtrtri(String uplo, String diag, int n, double[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    f2j.dtrtri(uplo, diag, n, a, _a_offset, lda, info);
  }

  public void dtrtrs(String uplo, String trans, String diag, int n, int nrhs, double[] a, int lda, double[] b, int ldb, org.netlib.util.intW info) {
    f2j.dtrtrs(uplo, trans, diag, n, nrhs, a, 0, lda, b, 0, ldb, info);
  }

  public void dtrtrs(String uplo, String trans, String diag, int n, int nrhs, double[] a, int _a_offset, int lda, double[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.dtrtrs(uplo, trans, diag, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, info);
  }

  public void dtzrqf(int m, int n, double[] a, int lda, double[] tau, org.netlib.util.intW info) {
    f2j.dtzrqf(m, n, a, 0, lda, tau, 0, info);
  }

  public void dtzrqf(int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, org.netlib.util.intW info) {
    f2j.dtzrqf(m, n, a, _a_offset, lda, tau, _tau_offset, info);
  }

  public void dtzrzf(int m, int n, double[] a, int lda, double[] tau, double[] work, int lwork, org.netlib.util.intW info) {
    f2j.dtzrzf(m, n, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void dtzrzf(int m, int n, double[] a, int _a_offset, int lda, double[] tau, int _tau_offset, double[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.dtzrzf(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public int ieeeck(int ispec, float zero, float one) {
    return f2j.ieeeck(ispec, zero, one);
  }

  public int ilaenv(int ispec, String name, String opts, int n1, int n2, int n3, int n4) {
    return f2j.ilaenv(ispec, name, opts, n1, n2, n3, n4);
  }

  public void ilaver(org.netlib.util.intW vers_major, org.netlib.util.intW vers_minor, org.netlib.util.intW vers_patch) {
    f2j.ilaver(vers_major, vers_minor, vers_patch);
  }

  public int iparmq(int ispec, String name, String opts, int n, int ilo, int ihi, int lwork) {
    return f2j.iparmq(ispec, name, opts, n, ilo, ihi, lwork);
  }

  public boolean lsamen(int n, String ca, String cb) {
    return f2j.lsamen(n, ca, cb);
  }

  public void sbdsdc(String uplo, String compq, int n, float[] d, float[] e, float[] u, int ldu, float[] vt, int ldvt, float[] q, int[] iq, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.sbdsdc(uplo, compq, n, d, 0, e, 0, u, 0, ldu, vt, 0, ldvt, q, 0, iq, 0, work, 0, iwork, 0, info);
  }

  public void sbdsdc(String uplo, String compq, int n, float[] d, int _d_offset, float[] e, int _e_offset, float[] u, int _u_offset, int ldu, float[] vt, int _vt_offset, int ldvt, float[] q, int _q_offset, int[] iq, int _iq_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sbdsdc(uplo, compq, n, d, _d_offset, e, _e_offset, u, _u_offset, ldu, vt, _vt_offset, ldvt, q, _q_offset, iq, _iq_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void sbdsqr(String uplo, int n, int ncvt, int nru, int ncc, float[] d, float[] e, float[] vt, int ldvt, float[] u, int ldu, float[] c, int Ldc, float[] work, org.netlib.util.intW info) {
    f2j.sbdsqr(uplo, n, ncvt, nru, ncc, d, 0, e, 0, vt, 0, ldvt, u, 0, ldu, c, 0, Ldc, work, 0, info);
  }

  public void sbdsqr(String uplo, int n, int ncvt, int nru, int ncc, float[] d, int _d_offset, float[] e, int _e_offset, float[] vt, int _vt_offset, int ldvt, float[] u, int _u_offset, int ldu, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sbdsqr(uplo, n, ncvt, nru, ncc, d, _d_offset, e, _e_offset, vt, _vt_offset, ldvt, u, _u_offset, ldu, c, _c_offset, Ldc, work, _work_offset, info);
  }

  public void sdisna(String job, int m, int n, float[] d, float[] sep, org.netlib.util.intW info) {
    f2j.sdisna(job, m, n, d, 0, sep, 0, info);
  }

  public void sdisna(String job, int m, int n, float[] d, int _d_offset, float[] sep, int _sep_offset, org.netlib.util.intW info) {
    f2j.sdisna(job, m, n, d, _d_offset, sep, _sep_offset, info);
  }

  public void sgbbrd(String vect, int m, int n, int ncc, int kl, int ku, float[] ab, int ldab, float[] d, float[] e, float[] q, int ldq, float[] pt, int ldpt, float[] c, int Ldc, float[] work, org.netlib.util.intW info) {
    f2j.sgbbrd(vect, m, n, ncc, kl, ku, ab, 0, ldab, d, 0, e, 0, q, 0, ldq, pt, 0, ldpt, c, 0, Ldc, work, 0, info);
  }

  public void sgbbrd(String vect, int m, int n, int ncc, int kl, int ku, float[] ab, int _ab_offset, int ldab, float[] d, int _d_offset, float[] e, int _e_offset, float[] q, int _q_offset, int ldq, float[] pt, int _pt_offset, int ldpt, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sgbbrd(vect, m, n, ncc, kl, ku, ab, _ab_offset, ldab, d, _d_offset, e, _e_offset, q, _q_offset, ldq, pt, _pt_offset, ldpt, c, _c_offset, Ldc, work, _work_offset, info);
  }

  public void sgbcon(String norm, int n, int kl, int ku, float[] ab, int ldab, int[] ipiv, float anorm, org.netlib.util.floatW rcond, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.sgbcon(norm, n, kl, ku, ab, 0, ldab, ipiv, 0, anorm, rcond, work, 0, iwork, 0, info);
  }

  public void sgbcon(String norm, int n, int kl, int ku, float[] ab, int _ab_offset, int ldab, int[] ipiv, int _ipiv_offset, float anorm, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sgbcon(norm, n, kl, ku, ab, _ab_offset, ldab, ipiv, _ipiv_offset, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void sgbequ(int m, int n, int kl, int ku, float[] ab, int ldab, float[] r, float[] c, org.netlib.util.floatW rowcnd, org.netlib.util.floatW colcnd, org.netlib.util.floatW amax, org.netlib.util.intW info) {
    f2j.sgbequ(m, n, kl, ku, ab, 0, ldab, r, 0, c, 0, rowcnd, colcnd, amax, info);
  }

  public void sgbequ(int m, int n, int kl, int ku, float[] ab, int _ab_offset, int ldab, float[] r, int _r_offset, float[] c, int _c_offset, org.netlib.util.floatW rowcnd, org.netlib.util.floatW colcnd, org.netlib.util.floatW amax, org.netlib.util.intW info) {
    f2j.sgbequ(m, n, kl, ku, ab, _ab_offset, ldab, r, _r_offset, c, _c_offset, rowcnd, colcnd, amax, info);
  }

  public void sgbrfs(String trans, int n, int kl, int ku, int nrhs, float[] ab, int ldab, float[] afb, int ldafb, int[] ipiv, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.sgbrfs(trans, n, kl, ku, nrhs, ab, 0, ldab, afb, 0, ldafb, ipiv, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void sgbrfs(String trans, int n, int kl, int ku, int nrhs, float[] ab, int _ab_offset, int ldab, float[] afb, int _afb_offset, int ldafb, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sgbrfs(trans, n, kl, ku, nrhs, ab, _ab_offset, ldab, afb, _afb_offset, ldafb, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void sgbsv(int n, int kl, int ku, int nrhs, float[] ab, int ldab, int[] ipiv, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.sgbsv(n, kl, ku, nrhs, ab, 0, ldab, ipiv, 0, b, 0, ldb, info);
  }

  public void sgbsv(int n, int kl, int ku, int nrhs, float[] ab, int _ab_offset, int ldab, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.sgbsv(n, kl, ku, nrhs, ab, _ab_offset, ldab, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  public void sgbsvx(String fact, String trans, int n, int kl, int ku, int nrhs, float[] ab, int ldab, float[] afb, int ldafb, int[] ipiv, org.netlib.util.StringW equed, float[] r, float[] c, float[] b, int ldb, float[] x, int ldx, org.netlib.util.floatW rcond, float[] ferr, float[] berr, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.sgbsvx(fact, trans, n, kl, ku, nrhs, ab, 0, ldab, afb, 0, ldafb, ipiv, 0, equed, r, 0, c, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void sgbsvx(String fact, String trans, int n, int kl, int ku, int nrhs, float[] ab, int _ab_offset, int ldab, float[] afb, int _afb_offset, int ldafb, int[] ipiv, int _ipiv_offset, org.netlib.util.StringW equed, float[] r, int _r_offset, float[] c, int _c_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, org.netlib.util.floatW rcond, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sgbsvx(fact, trans, n, kl, ku, nrhs, ab, _ab_offset, ldab, afb, _afb_offset, ldafb, ipiv, _ipiv_offset, equed, r, _r_offset, c, _c_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void sgbtf2(int m, int n, int kl, int ku, float[] ab, int ldab, int[] ipiv, org.netlib.util.intW info) {
    f2j.sgbtf2(m, n, kl, ku, ab, 0, ldab, ipiv, 0, info);
  }

  public void sgbtf2(int m, int n, int kl, int ku, float[] ab, int _ab_offset, int ldab, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    f2j.sgbtf2(m, n, kl, ku, ab, _ab_offset, ldab, ipiv, _ipiv_offset, info);
  }

  public void sgbtrf(int m, int n, int kl, int ku, float[] ab, int ldab, int[] ipiv, org.netlib.util.intW info) {
    f2j.sgbtrf(m, n, kl, ku, ab, 0, ldab, ipiv, 0, info);
  }

  public void sgbtrf(int m, int n, int kl, int ku, float[] ab, int _ab_offset, int ldab, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    f2j.sgbtrf(m, n, kl, ku, ab, _ab_offset, ldab, ipiv, _ipiv_offset, info);
  }

  public void sgbtrs(String trans, int n, int kl, int ku, int nrhs, float[] ab, int ldab, int[] ipiv, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.sgbtrs(trans, n, kl, ku, nrhs, ab, 0, ldab, ipiv, 0, b, 0, ldb, info);
  }

  public void sgbtrs(String trans, int n, int kl, int ku, int nrhs, float[] ab, int _ab_offset, int ldab, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.sgbtrs(trans, n, kl, ku, nrhs, ab, _ab_offset, ldab, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  public void sgebak(String job, String side, int n, int ilo, int ihi, float[] scale, int m, float[] v, int ldv, org.netlib.util.intW info) {
    f2j.sgebak(job, side, n, ilo, ihi, scale, 0, m, v, 0, ldv, info);
  }

  public void sgebak(String job, String side, int n, int ilo, int ihi, float[] scale, int _scale_offset, int m, float[] v, int _v_offset, int ldv, org.netlib.util.intW info) {
    f2j.sgebak(job, side, n, ilo, ihi, scale, _scale_offset, m, v, _v_offset, ldv, info);
  }

  public void sgebal(String job, int n, float[] a, int lda, org.netlib.util.intW ilo, org.netlib.util.intW ihi, float[] scale, org.netlib.util.intW info) {
    f2j.sgebal(job, n, a, 0, lda, ilo, ihi, scale, 0, info);
  }

  public void sgebal(String job, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW ilo, org.netlib.util.intW ihi, float[] scale, int _scale_offset, org.netlib.util.intW info) {
    f2j.sgebal(job, n, a, _a_offset, lda, ilo, ihi, scale, _scale_offset, info);
  }

  public void sgebd2(int m, int n, float[] a, int lda, float[] d, float[] e, float[] tauq, float[] taup, float[] work, org.netlib.util.intW info) {
    f2j.sgebd2(m, n, a, 0, lda, d, 0, e, 0, tauq, 0, taup, 0, work, 0, info);
  }

  public void sgebd2(int m, int n, float[] a, int _a_offset, int lda, float[] d, int _d_offset, float[] e, int _e_offset, float[] tauq, int _tauq_offset, float[] taup, int _taup_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sgebd2(m, n, a, _a_offset, lda, d, _d_offset, e, _e_offset, tauq, _tauq_offset, taup, _taup_offset, work, _work_offset, info);
  }

  public void sgebrd(int m, int n, float[] a, int lda, float[] d, float[] e, float[] tauq, float[] taup, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sgebrd(m, n, a, 0, lda, d, 0, e, 0, tauq, 0, taup, 0, work, 0, lwork, info);
  }

  public void sgebrd(int m, int n, float[] a, int _a_offset, int lda, float[] d, int _d_offset, float[] e, int _e_offset, float[] tauq, int _tauq_offset, float[] taup, int _taup_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sgebrd(m, n, a, _a_offset, lda, d, _d_offset, e, _e_offset, tauq, _tauq_offset, taup, _taup_offset, work, _work_offset, lwork, info);
  }

  public void sgecon(String norm, int n, float[] a, int lda, float anorm, org.netlib.util.floatW rcond, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.sgecon(norm, n, a, 0, lda, anorm, rcond, work, 0, iwork, 0, info);
  }

  public void sgecon(String norm, int n, float[] a, int _a_offset, int lda, float anorm, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sgecon(norm, n, a, _a_offset, lda, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void sgeequ(int m, int n, float[] a, int lda, float[] r, float[] c, org.netlib.util.floatW rowcnd, org.netlib.util.floatW colcnd, org.netlib.util.floatW amax, org.netlib.util.intW info) {
    f2j.sgeequ(m, n, a, 0, lda, r, 0, c, 0, rowcnd, colcnd, amax, info);
  }

  public void sgeequ(int m, int n, float[] a, int _a_offset, int lda, float[] r, int _r_offset, float[] c, int _c_offset, org.netlib.util.floatW rowcnd, org.netlib.util.floatW colcnd, org.netlib.util.floatW amax, org.netlib.util.intW info) {
    f2j.sgeequ(m, n, a, _a_offset, lda, r, _r_offset, c, _c_offset, rowcnd, colcnd, amax, info);
  }

  public void sgees(String jobvs, String sort, java.lang.Object select, int n, float[] a, int lda, org.netlib.util.intW sdim, float[] wr, float[] wi, float[] vs, int ldvs, float[] work, int lwork, boolean[] bwork, org.netlib.util.intW info) {
    f2j.sgees(jobvs, sort, select, n, a, 0, lda, sdim, wr, 0, wi, 0, vs, 0, ldvs, work, 0, lwork, bwork, 0, info);
  }

  public void sgees(String jobvs, String sort, java.lang.Object select, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW sdim, float[] wr, int _wr_offset, float[] wi, int _wi_offset, float[] vs, int _vs_offset, int ldvs, float[] work, int _work_offset, int lwork, boolean[] bwork, int _bwork_offset, org.netlib.util.intW info) {
    f2j.sgees(jobvs, sort, select, n, a, _a_offset, lda, sdim, wr, _wr_offset, wi, _wi_offset, vs, _vs_offset, ldvs, work, _work_offset, lwork, bwork, _bwork_offset, info);
  }

  public void sgeesx(String jobvs, String sort, java.lang.Object select, String sense, int n, float[] a, int lda, org.netlib.util.intW sdim, float[] wr, float[] wi, float[] vs, int ldvs, org.netlib.util.floatW rconde, org.netlib.util.floatW rcondv, float[] work, int lwork, int[] iwork, int liwork, boolean[] bwork, org.netlib.util.intW info) {
    f2j.sgeesx(jobvs, sort, select, sense, n, a, 0, lda, sdim, wr, 0, wi, 0, vs, 0, ldvs, rconde, rcondv, work, 0, lwork, iwork, 0, liwork, bwork, 0, info);
  }

  public void sgeesx(String jobvs, String sort, java.lang.Object select, String sense, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW sdim, float[] wr, int _wr_offset, float[] wi, int _wi_offset, float[] vs, int _vs_offset, int ldvs, org.netlib.util.floatW rconde, org.netlib.util.floatW rcondv, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, boolean[] bwork, int _bwork_offset, org.netlib.util.intW info) {
    f2j.sgeesx(jobvs, sort, select, sense, n, a, _a_offset, lda, sdim, wr, _wr_offset, wi, _wi_offset, vs, _vs_offset, ldvs, rconde, rcondv, work, _work_offset, lwork, iwork, _iwork_offset, liwork, bwork, _bwork_offset, info);
  }

  public void sgeev(String jobvl, String jobvr, int n, float[] a, int lda, float[] wr, float[] wi, float[] vl, int ldvl, float[] vr, int ldvr, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sgeev(jobvl, jobvr, n, a, 0, lda, wr, 0, wi, 0, vl, 0, ldvl, vr, 0, ldvr, work, 0, lwork, info);
  }

  public void sgeev(String jobvl, String jobvr, int n, float[] a, int _a_offset, int lda, float[] wr, int _wr_offset, float[] wi, int _wi_offset, float[] vl, int _vl_offset, int ldvl, float[] vr, int _vr_offset, int ldvr, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sgeev(jobvl, jobvr, n, a, _a_offset, lda, wr, _wr_offset, wi, _wi_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, work, _work_offset, lwork, info);
  }

  public void sgeevx(String balanc, String jobvl, String jobvr, String sense, int n, float[] a, int lda, float[] wr, float[] wi, float[] vl, int ldvl, float[] vr, int ldvr, org.netlib.util.intW ilo, org.netlib.util.intW ihi, float[] scale, org.netlib.util.floatW abnrm, float[] rconde, float[] rcondv, float[] work, int lwork, int[] iwork, org.netlib.util.intW info) {
    f2j.sgeevx(balanc, jobvl, jobvr, sense, n, a, 0, lda, wr, 0, wi, 0, vl, 0, ldvl, vr, 0, ldvr, ilo, ihi, scale, 0, abnrm, rconde, 0, rcondv, 0, work, 0, lwork, iwork, 0, info);
  }

  public void sgeevx(String balanc, String jobvl, String jobvr, String sense, int n, float[] a, int _a_offset, int lda, float[] wr, int _wr_offset, float[] wi, int _wi_offset, float[] vl, int _vl_offset, int ldvl, float[] vr, int _vr_offset, int ldvr, org.netlib.util.intW ilo, org.netlib.util.intW ihi, float[] scale, int _scale_offset, org.netlib.util.floatW abnrm, float[] rconde, int _rconde_offset, float[] rcondv, int _rcondv_offset, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sgeevx(balanc, jobvl, jobvr, sense, n, a, _a_offset, lda, wr, _wr_offset, wi, _wi_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, ilo, ihi, scale, _scale_offset, abnrm, rconde, _rconde_offset, rcondv, _rcondv_offset, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  public void sgegs(String jobvsl, String jobvsr, int n, float[] a, int lda, float[] b, int ldb, float[] alphar, float[] alphai, float[] beta, float[] vsl, int ldvsl, float[] vsr, int ldvsr, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sgegs(jobvsl, jobvsr, n, a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, vsl, 0, ldvsl, vsr, 0, ldvsr, work, 0, lwork, info);
  }

  public void sgegs(String jobvsl, String jobvsr, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] alphar, int _alphar_offset, float[] alphai, int _alphai_offset, float[] beta, int _beta_offset, float[] vsl, int _vsl_offset, int ldvsl, float[] vsr, int _vsr_offset, int ldvsr, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sgegs(jobvsl, jobvsr, n, a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vsl, _vsl_offset, ldvsl, vsr, _vsr_offset, ldvsr, work, _work_offset, lwork, info);
  }

  public void sgegv(String jobvl, String jobvr, int n, float[] a, int lda, float[] b, int ldb, float[] alphar, float[] alphai, float[] beta, float[] vl, int ldvl, float[] vr, int ldvr, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sgegv(jobvl, jobvr, n, a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, vl, 0, ldvl, vr, 0, ldvr, work, 0, lwork, info);
  }

  public void sgegv(String jobvl, String jobvr, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] alphar, int _alphar_offset, float[] alphai, int _alphai_offset, float[] beta, int _beta_offset, float[] vl, int _vl_offset, int ldvl, float[] vr, int _vr_offset, int ldvr, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sgegv(jobvl, jobvr, n, a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, work, _work_offset, lwork, info);
  }

  public void sgehd2(int n, int ilo, int ihi, float[] a, int lda, float[] tau, float[] work, org.netlib.util.intW info) {
    f2j.sgehd2(n, ilo, ihi, a, 0, lda, tau, 0, work, 0, info);
  }

  public void sgehd2(int n, int ilo, int ihi, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sgehd2(n, ilo, ihi, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  public void sgehrd(int n, int ilo, int ihi, float[] a, int lda, float[] tau, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sgehrd(n, ilo, ihi, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void sgehrd(int n, int ilo, int ihi, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sgehrd(n, ilo, ihi, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void sgelq2(int m, int n, float[] a, int lda, float[] tau, float[] work, org.netlib.util.intW info) {
    f2j.sgelq2(m, n, a, 0, lda, tau, 0, work, 0, info);
  }

  public void sgelq2(int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sgelq2(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  public void sgelqf(int m, int n, float[] a, int lda, float[] tau, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sgelqf(m, n, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void sgelqf(int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sgelqf(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void sgels(String trans, int m, int n, int nrhs, float[] a, int lda, float[] b, int ldb, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sgels(trans, m, n, nrhs, a, 0, lda, b, 0, ldb, work, 0, lwork, info);
  }

  public void sgels(String trans, int m, int n, int nrhs, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sgels(trans, m, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, work, _work_offset, lwork, info);
  }

  public void sgelsd(int m, int n, int nrhs, float[] a, int lda, float[] b, int ldb, float[] s, float rcond, org.netlib.util.intW rank, float[] work, int lwork, int[] iwork, org.netlib.util.intW info) {
    f2j.sgelsd(m, n, nrhs, a, 0, lda, b, 0, ldb, s, 0, rcond, rank, work, 0, lwork, iwork, 0, info);
  }

  public void sgelsd(int m, int n, int nrhs, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] s, int _s_offset, float rcond, org.netlib.util.intW rank, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sgelsd(m, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, s, _s_offset, rcond, rank, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  public void sgelss(int m, int n, int nrhs, float[] a, int lda, float[] b, int ldb, float[] s, float rcond, org.netlib.util.intW rank, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sgelss(m, n, nrhs, a, 0, lda, b, 0, ldb, s, 0, rcond, rank, work, 0, lwork, info);
  }

  public void sgelss(int m, int n, int nrhs, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] s, int _s_offset, float rcond, org.netlib.util.intW rank, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sgelss(m, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, s, _s_offset, rcond, rank, work, _work_offset, lwork, info);
  }

  public void sgelsx(int m, int n, int nrhs, float[] a, int lda, float[] b, int ldb, int[] jpvt, float rcond, org.netlib.util.intW rank, float[] work, org.netlib.util.intW info) {
    f2j.sgelsx(m, n, nrhs, a, 0, lda, b, 0, ldb, jpvt, 0, rcond, rank, work, 0, info);
  }

  public void sgelsx(int m, int n, int nrhs, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, int[] jpvt, int _jpvt_offset, float rcond, org.netlib.util.intW rank, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sgelsx(m, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, jpvt, _jpvt_offset, rcond, rank, work, _work_offset, info);
  }

  public void sgelsy(int m, int n, int nrhs, float[] a, int lda, float[] b, int ldb, int[] jpvt, float rcond, org.netlib.util.intW rank, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sgelsy(m, n, nrhs, a, 0, lda, b, 0, ldb, jpvt, 0, rcond, rank, work, 0, lwork, info);
  }

  public void sgelsy(int m, int n, int nrhs, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, int[] jpvt, int _jpvt_offset, float rcond, org.netlib.util.intW rank, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sgelsy(m, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, jpvt, _jpvt_offset, rcond, rank, work, _work_offset, lwork, info);
  }

  public void sgeql2(int m, int n, float[] a, int lda, float[] tau, float[] work, org.netlib.util.intW info) {
    f2j.sgeql2(m, n, a, 0, lda, tau, 0, work, 0, info);
  }

  public void sgeql2(int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sgeql2(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  public void sgeqlf(int m, int n, float[] a, int lda, float[] tau, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sgeqlf(m, n, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void sgeqlf(int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sgeqlf(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void sgeqp3(int m, int n, float[] a, int lda, int[] jpvt, float[] tau, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sgeqp3(m, n, a, 0, lda, jpvt, 0, tau, 0, work, 0, lwork, info);
  }

  public void sgeqp3(int m, int n, float[] a, int _a_offset, int lda, int[] jpvt, int _jpvt_offset, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sgeqp3(m, n, a, _a_offset, lda, jpvt, _jpvt_offset, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void sgeqpf(int m, int n, float[] a, int lda, int[] jpvt, float[] tau, float[] work, org.netlib.util.intW info) {
    f2j.sgeqpf(m, n, a, 0, lda, jpvt, 0, tau, 0, work, 0, info);
  }

  public void sgeqpf(int m, int n, float[] a, int _a_offset, int lda, int[] jpvt, int _jpvt_offset, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sgeqpf(m, n, a, _a_offset, lda, jpvt, _jpvt_offset, tau, _tau_offset, work, _work_offset, info);
  }

  public void sgeqr2(int m, int n, float[] a, int lda, float[] tau, float[] work, org.netlib.util.intW info) {
    f2j.sgeqr2(m, n, a, 0, lda, tau, 0, work, 0, info);
  }

  public void sgeqr2(int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sgeqr2(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  public void sgeqrf(int m, int n, float[] a, int lda, float[] tau, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sgeqrf(m, n, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void sgeqrf(int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sgeqrf(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void sgerfs(String trans, int n, int nrhs, float[] a, int lda, float[] af, int ldaf, int[] ipiv, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.sgerfs(trans, n, nrhs, a, 0, lda, af, 0, ldaf, ipiv, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void sgerfs(String trans, int n, int nrhs, float[] a, int _a_offset, int lda, float[] af, int _af_offset, int ldaf, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sgerfs(trans, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void sgerq2(int m, int n, float[] a, int lda, float[] tau, float[] work, org.netlib.util.intW info) {
    f2j.sgerq2(m, n, a, 0, lda, tau, 0, work, 0, info);
  }

  public void sgerq2(int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sgerq2(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  public void sgerqf(int m, int n, float[] a, int lda, float[] tau, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sgerqf(m, n, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void sgerqf(int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sgerqf(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void sgesc2(int n, float[] a, int lda, float[] rhs, int[] ipiv, int[] jpiv, org.netlib.util.floatW scale) {
    f2j.sgesc2(n, a, 0, lda, rhs, 0, ipiv, 0, jpiv, 0, scale);
  }

  public void sgesc2(int n, float[] a, int _a_offset, int lda, float[] rhs, int _rhs_offset, int[] ipiv, int _ipiv_offset, int[] jpiv, int _jpiv_offset, org.netlib.util.floatW scale) {
    f2j.sgesc2(n, a, _a_offset, lda, rhs, _rhs_offset, ipiv, _ipiv_offset, jpiv, _jpiv_offset, scale);
  }

  public void sgesdd(String jobz, int m, int n, float[] a, int lda, float[] s, float[] u, int ldu, float[] vt, int ldvt, float[] work, int lwork, int[] iwork, org.netlib.util.intW info) {
    f2j.sgesdd(jobz, m, n, a, 0, lda, s, 0, u, 0, ldu, vt, 0, ldvt, work, 0, lwork, iwork, 0, info);
  }

  public void sgesdd(String jobz, int m, int n, float[] a, int _a_offset, int lda, float[] s, int _s_offset, float[] u, int _u_offset, int ldu, float[] vt, int _vt_offset, int ldvt, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sgesdd(jobz, m, n, a, _a_offset, lda, s, _s_offset, u, _u_offset, ldu, vt, _vt_offset, ldvt, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  public void sgesv(int n, int nrhs, float[] a, int lda, int[] ipiv, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.sgesv(n, nrhs, a, 0, lda, ipiv, 0, b, 0, ldb, info);
  }

  public void sgesv(int n, int nrhs, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.sgesv(n, nrhs, a, _a_offset, lda, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  public void sgesvd(String jobu, String jobvt, int m, int n, float[] a, int lda, float[] s, float[] u, int ldu, float[] vt, int ldvt, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sgesvd(jobu, jobvt, m, n, a, 0, lda, s, 0, u, 0, ldu, vt, 0, ldvt, work, 0, lwork, info);
  }

  public void sgesvd(String jobu, String jobvt, int m, int n, float[] a, int _a_offset, int lda, float[] s, int _s_offset, float[] u, int _u_offset, int ldu, float[] vt, int _vt_offset, int ldvt, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sgesvd(jobu, jobvt, m, n, a, _a_offset, lda, s, _s_offset, u, _u_offset, ldu, vt, _vt_offset, ldvt, work, _work_offset, lwork, info);
  }

  public void sgesvx(String fact, String trans, int n, int nrhs, float[] a, int lda, float[] af, int ldaf, int[] ipiv, org.netlib.util.StringW equed, float[] r, float[] c, float[] b, int ldb, float[] x, int ldx, org.netlib.util.floatW rcond, float[] ferr, float[] berr, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.sgesvx(fact, trans, n, nrhs, a, 0, lda, af, 0, ldaf, ipiv, 0, equed, r, 0, c, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void sgesvx(String fact, String trans, int n, int nrhs, float[] a, int _a_offset, int lda, float[] af, int _af_offset, int ldaf, int[] ipiv, int _ipiv_offset, org.netlib.util.StringW equed, float[] r, int _r_offset, float[] c, int _c_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, org.netlib.util.floatW rcond, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sgesvx(fact, trans, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, ipiv, _ipiv_offset, equed, r, _r_offset, c, _c_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void sgetc2(int n, float[] a, int lda, int[] ipiv, int[] jpiv, org.netlib.util.intW info) {
    f2j.sgetc2(n, a, 0, lda, ipiv, 0, jpiv, 0, info);
  }

  public void sgetc2(int n, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, int[] jpiv, int _jpiv_offset, org.netlib.util.intW info) {
    f2j.sgetc2(n, a, _a_offset, lda, ipiv, _ipiv_offset, jpiv, _jpiv_offset, info);
  }

  public void sgetf2(int m, int n, float[] a, int lda, int[] ipiv, org.netlib.util.intW info) {
    f2j.sgetf2(m, n, a, 0, lda, ipiv, 0, info);
  }

  public void sgetf2(int m, int n, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    f2j.sgetf2(m, n, a, _a_offset, lda, ipiv, _ipiv_offset, info);
  }

  public void sgetrf(int m, int n, float[] a, int lda, int[] ipiv, org.netlib.util.intW info) {
    f2j.sgetrf(m, n, a, 0, lda, ipiv, 0, info);
  }

  public void sgetrf(int m, int n, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    f2j.sgetrf(m, n, a, _a_offset, lda, ipiv, _ipiv_offset, info);
  }

  public void sgetri(int n, float[] a, int lda, int[] ipiv, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sgetri(n, a, 0, lda, ipiv, 0, work, 0, lwork, info);
  }

  public void sgetri(int n, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sgetri(n, a, _a_offset, lda, ipiv, _ipiv_offset, work, _work_offset, lwork, info);
  }

  public void sgetrs(String trans, int n, int nrhs, float[] a, int lda, int[] ipiv, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.sgetrs(trans, n, nrhs, a, 0, lda, ipiv, 0, b, 0, ldb, info);
  }

  public void sgetrs(String trans, int n, int nrhs, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.sgetrs(trans, n, nrhs, a, _a_offset, lda, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  public void sggbak(String job, String side, int n, int ilo, int ihi, float[] lscale, float[] rscale, int m, float[] v, int ldv, org.netlib.util.intW info) {
    f2j.sggbak(job, side, n, ilo, ihi, lscale, 0, rscale, 0, m, v, 0, ldv, info);
  }

  public void sggbak(String job, String side, int n, int ilo, int ihi, float[] lscale, int _lscale_offset, float[] rscale, int _rscale_offset, int m, float[] v, int _v_offset, int ldv, org.netlib.util.intW info) {
    f2j.sggbak(job, side, n, ilo, ihi, lscale, _lscale_offset, rscale, _rscale_offset, m, v, _v_offset, ldv, info);
  }

  public void sggbal(String job, int n, float[] a, int lda, float[] b, int ldb, org.netlib.util.intW ilo, org.netlib.util.intW ihi, float[] lscale, float[] rscale, float[] work, org.netlib.util.intW info) {
    f2j.sggbal(job, n, a, 0, lda, b, 0, ldb, ilo, ihi, lscale, 0, rscale, 0, work, 0, info);
  }

  public void sggbal(String job, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, org.netlib.util.intW ilo, org.netlib.util.intW ihi, float[] lscale, int _lscale_offset, float[] rscale, int _rscale_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sggbal(job, n, a, _a_offset, lda, b, _b_offset, ldb, ilo, ihi, lscale, _lscale_offset, rscale, _rscale_offset, work, _work_offset, info);
  }

  public void sgges(String jobvsl, String jobvsr, String sort, java.lang.Object selctg, int n, float[] a, int lda, float[] b, int ldb, org.netlib.util.intW sdim, float[] alphar, float[] alphai, float[] beta, float[] vsl, int ldvsl, float[] vsr, int ldvsr, float[] work, int lwork, boolean[] bwork, org.netlib.util.intW info) {
    f2j.sgges(jobvsl, jobvsr, sort, selctg, n, a, 0, lda, b, 0, ldb, sdim, alphar, 0, alphai, 0, beta, 0, vsl, 0, ldvsl, vsr, 0, ldvsr, work, 0, lwork, bwork, 0, info);
  }

  public void sgges(String jobvsl, String jobvsr, String sort, java.lang.Object selctg, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, org.netlib.util.intW sdim, float[] alphar, int _alphar_offset, float[] alphai, int _alphai_offset, float[] beta, int _beta_offset, float[] vsl, int _vsl_offset, int ldvsl, float[] vsr, int _vsr_offset, int ldvsr, float[] work, int _work_offset, int lwork, boolean[] bwork, int _bwork_offset, org.netlib.util.intW info) {
    f2j.sgges(jobvsl, jobvsr, sort, selctg, n, a, _a_offset, lda, b, _b_offset, ldb, sdim, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vsl, _vsl_offset, ldvsl, vsr, _vsr_offset, ldvsr, work, _work_offset, lwork, bwork, _bwork_offset, info);
  }

  public void sggesx(String jobvsl, String jobvsr, String sort, java.lang.Object selctg, String sense, int n, float[] a, int lda, float[] b, int ldb, org.netlib.util.intW sdim, float[] alphar, float[] alphai, float[] beta, float[] vsl, int ldvsl, float[] vsr, int ldvsr, float[] rconde, float[] rcondv, float[] work, int lwork, int[] iwork, int liwork, boolean[] bwork, org.netlib.util.intW info) {
    f2j.sggesx(jobvsl, jobvsr, sort, selctg, sense, n, a, 0, lda, b, 0, ldb, sdim, alphar, 0, alphai, 0, beta, 0, vsl, 0, ldvsl, vsr, 0, ldvsr, rconde, 0, rcondv, 0, work, 0, lwork, iwork, 0, liwork, bwork, 0, info);
  }

  public void sggesx(String jobvsl, String jobvsr, String sort, java.lang.Object selctg, String sense, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, org.netlib.util.intW sdim, float[] alphar, int _alphar_offset, float[] alphai, int _alphai_offset, float[] beta, int _beta_offset, float[] vsl, int _vsl_offset, int ldvsl, float[] vsr, int _vsr_offset, int ldvsr, float[] rconde, int _rconde_offset, float[] rcondv, int _rcondv_offset, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, boolean[] bwork, int _bwork_offset, org.netlib.util.intW info) {
    f2j.sggesx(jobvsl, jobvsr, sort, selctg, sense, n, a, _a_offset, lda, b, _b_offset, ldb, sdim, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vsl, _vsl_offset, ldvsl, vsr, _vsr_offset, ldvsr, rconde, _rconde_offset, rcondv, _rcondv_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, bwork, _bwork_offset, info);
  }

  public void sggev(String jobvl, String jobvr, int n, float[] a, int lda, float[] b, int ldb, float[] alphar, float[] alphai, float[] beta, float[] vl, int ldvl, float[] vr, int ldvr, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sggev(jobvl, jobvr, n, a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, vl, 0, ldvl, vr, 0, ldvr, work, 0, lwork, info);
  }

  public void sggev(String jobvl, String jobvr, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] alphar, int _alphar_offset, float[] alphai, int _alphai_offset, float[] beta, int _beta_offset, float[] vl, int _vl_offset, int ldvl, float[] vr, int _vr_offset, int ldvr, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sggev(jobvl, jobvr, n, a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, work, _work_offset, lwork, info);
  }

  public void sggevx(String balanc, String jobvl, String jobvr, String sense, int n, float[] a, int lda, float[] b, int ldb, float[] alphar, float[] alphai, float[] beta, float[] vl, int ldvl, float[] vr, int ldvr, org.netlib.util.intW ilo, org.netlib.util.intW ihi, float[] lscale, float[] rscale, org.netlib.util.floatW abnrm, org.netlib.util.floatW bbnrm, float[] rconde, float[] rcondv, float[] work, int lwork, int[] iwork, boolean[] bwork, org.netlib.util.intW info) {
    f2j.sggevx(balanc, jobvl, jobvr, sense, n, a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, vl, 0, ldvl, vr, 0, ldvr, ilo, ihi, lscale, 0, rscale, 0, abnrm, bbnrm, rconde, 0, rcondv, 0, work, 0, lwork, iwork, 0, bwork, 0, info);
  }

  public void sggevx(String balanc, String jobvl, String jobvr, String sense, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] alphar, int _alphar_offset, float[] alphai, int _alphai_offset, float[] beta, int _beta_offset, float[] vl, int _vl_offset, int ldvl, float[] vr, int _vr_offset, int ldvr, org.netlib.util.intW ilo, org.netlib.util.intW ihi, float[] lscale, int _lscale_offset, float[] rscale, int _rscale_offset, org.netlib.util.floatW abnrm, org.netlib.util.floatW bbnrm, float[] rconde, int _rconde_offset, float[] rcondv, int _rcondv_offset, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, boolean[] bwork, int _bwork_offset, org.netlib.util.intW info) {
    f2j.sggevx(balanc, jobvl, jobvr, sense, n, a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, ilo, ihi, lscale, _lscale_offset, rscale, _rscale_offset, abnrm, bbnrm, rconde, _rconde_offset, rcondv, _rcondv_offset, work, _work_offset, lwork, iwork, _iwork_offset, bwork, _bwork_offset, info);
  }

  public void sggglm(int n, int m, int p, float[] a, int lda, float[] b, int ldb, float[] d, float[] x, float[] y, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sggglm(n, m, p, a, 0, lda, b, 0, ldb, d, 0, x, 0, y, 0, work, 0, lwork, info);
  }

  public void sggglm(int n, int m, int p, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] d, int _d_offset, float[] x, int _x_offset, float[] y, int _y_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sggglm(n, m, p, a, _a_offset, lda, b, _b_offset, ldb, d, _d_offset, x, _x_offset, y, _y_offset, work, _work_offset, lwork, info);
  }

  public void sgghrd(String compq, String compz, int n, int ilo, int ihi, float[] a, int lda, float[] b, int ldb, float[] q, int ldq, float[] z, int ldz, org.netlib.util.intW info) {
    f2j.sgghrd(compq, compz, n, ilo, ihi, a, 0, lda, b, 0, ldb, q, 0, ldq, z, 0, ldz, info);
  }

  public void sgghrd(String compq, String compz, int n, int ilo, int ihi, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] q, int _q_offset, int ldq, float[] z, int _z_offset, int ldz, org.netlib.util.intW info) {
    f2j.sgghrd(compq, compz, n, ilo, ihi, a, _a_offset, lda, b, _b_offset, ldb, q, _q_offset, ldq, z, _z_offset, ldz, info);
  }

  public void sgglse(int m, int n, int p, float[] a, int lda, float[] b, int ldb, float[] c, float[] d, float[] x, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sgglse(m, n, p, a, 0, lda, b, 0, ldb, c, 0, d, 0, x, 0, work, 0, lwork, info);
  }

  public void sgglse(int m, int n, int p, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] c, int _c_offset, float[] d, int _d_offset, float[] x, int _x_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sgglse(m, n, p, a, _a_offset, lda, b, _b_offset, ldb, c, _c_offset, d, _d_offset, x, _x_offset, work, _work_offset, lwork, info);
  }

  public void sggqrf(int n, int m, int p, float[] a, int lda, float[] taua, float[] b, int ldb, float[] taub, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sggqrf(n, m, p, a, 0, lda, taua, 0, b, 0, ldb, taub, 0, work, 0, lwork, info);
  }

  public void sggqrf(int n, int m, int p, float[] a, int _a_offset, int lda, float[] taua, int _taua_offset, float[] b, int _b_offset, int ldb, float[] taub, int _taub_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sggqrf(n, m, p, a, _a_offset, lda, taua, _taua_offset, b, _b_offset, ldb, taub, _taub_offset, work, _work_offset, lwork, info);
  }

  public void sggrqf(int m, int p, int n, float[] a, int lda, float[] taua, float[] b, int ldb, float[] taub, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sggrqf(m, p, n, a, 0, lda, taua, 0, b, 0, ldb, taub, 0, work, 0, lwork, info);
  }

  public void sggrqf(int m, int p, int n, float[] a, int _a_offset, int lda, float[] taua, int _taua_offset, float[] b, int _b_offset, int ldb, float[] taub, int _taub_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sggrqf(m, p, n, a, _a_offset, lda, taua, _taua_offset, b, _b_offset, ldb, taub, _taub_offset, work, _work_offset, lwork, info);
  }

  public void sggsvd(String jobu, String jobv, String jobq, int m, int n, int p, org.netlib.util.intW k, org.netlib.util.intW l, float[] a, int lda, float[] b, int ldb, float[] alpha, float[] beta, float[] u, int ldu, float[] v, int ldv, float[] q, int ldq, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.sggsvd(jobu, jobv, jobq, m, n, p, k, l, a, 0, lda, b, 0, ldb, alpha, 0, beta, 0, u, 0, ldu, v, 0, ldv, q, 0, ldq, work, 0, iwork, 0, info);
  }

  public void sggsvd(String jobu, String jobv, String jobq, int m, int n, int p, org.netlib.util.intW k, org.netlib.util.intW l, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] alpha, int _alpha_offset, float[] beta, int _beta_offset, float[] u, int _u_offset, int ldu, float[] v, int _v_offset, int ldv, float[] q, int _q_offset, int ldq, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sggsvd(jobu, jobv, jobq, m, n, p, k, l, a, _a_offset, lda, b, _b_offset, ldb, alpha, _alpha_offset, beta, _beta_offset, u, _u_offset, ldu, v, _v_offset, ldv, q, _q_offset, ldq, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void sggsvp(String jobu, String jobv, String jobq, int m, int p, int n, float[] a, int lda, float[] b, int ldb, float tola, float tolb, org.netlib.util.intW k, org.netlib.util.intW l, float[] u, int ldu, float[] v, int ldv, float[] q, int ldq, int[] iwork, float[] tau, float[] work, org.netlib.util.intW info) {
    f2j.sggsvp(jobu, jobv, jobq, m, p, n, a, 0, lda, b, 0, ldb, tola, tolb, k, l, u, 0, ldu, v, 0, ldv, q, 0, ldq, iwork, 0, tau, 0, work, 0, info);
  }

  public void sggsvp(String jobu, String jobv, String jobq, int m, int p, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float tola, float tolb, org.netlib.util.intW k, org.netlib.util.intW l, float[] u, int _u_offset, int ldu, float[] v, int _v_offset, int ldv, float[] q, int _q_offset, int ldq, int[] iwork, int _iwork_offset, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sggsvp(jobu, jobv, jobq, m, p, n, a, _a_offset, lda, b, _b_offset, ldb, tola, tolb, k, l, u, _u_offset, ldu, v, _v_offset, ldv, q, _q_offset, ldq, iwork, _iwork_offset, tau, _tau_offset, work, _work_offset, info);
  }

  public void sgtcon(String norm, int n, float[] dl, float[] d, float[] du, float[] du2, int[] ipiv, float anorm, org.netlib.util.floatW rcond, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.sgtcon(norm, n, dl, 0, d, 0, du, 0, du2, 0, ipiv, 0, anorm, rcond, work, 0, iwork, 0, info);
  }

  public void sgtcon(String norm, int n, float[] dl, int _dl_offset, float[] d, int _d_offset, float[] du, int _du_offset, float[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, float anorm, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sgtcon(norm, n, dl, _dl_offset, d, _d_offset, du, _du_offset, du2, _du2_offset, ipiv, _ipiv_offset, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void sgtrfs(String trans, int n, int nrhs, float[] dl, float[] d, float[] du, float[] dlf, float[] df, float[] duf, float[] du2, int[] ipiv, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.sgtrfs(trans, n, nrhs, dl, 0, d, 0, du, 0, dlf, 0, df, 0, duf, 0, du2, 0, ipiv, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void sgtrfs(String trans, int n, int nrhs, float[] dl, int _dl_offset, float[] d, int _d_offset, float[] du, int _du_offset, float[] dlf, int _dlf_offset, float[] df, int _df_offset, float[] duf, int _duf_offset, float[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sgtrfs(trans, n, nrhs, dl, _dl_offset, d, _d_offset, du, _du_offset, dlf, _dlf_offset, df, _df_offset, duf, _duf_offset, du2, _du2_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void sgtsv(int n, int nrhs, float[] dl, float[] d, float[] du, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.sgtsv(n, nrhs, dl, 0, d, 0, du, 0, b, 0, ldb, info);
  }

  public void sgtsv(int n, int nrhs, float[] dl, int _dl_offset, float[] d, int _d_offset, float[] du, int _du_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.sgtsv(n, nrhs, dl, _dl_offset, d, _d_offset, du, _du_offset, b, _b_offset, ldb, info);
  }

  public void sgtsvx(String fact, String trans, int n, int nrhs, float[] dl, float[] d, float[] du, float[] dlf, float[] df, float[] duf, float[] du2, int[] ipiv, float[] b, int ldb, float[] x, int ldx, org.netlib.util.floatW rcond, float[] ferr, float[] berr, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.sgtsvx(fact, trans, n, nrhs, dl, 0, d, 0, du, 0, dlf, 0, df, 0, duf, 0, du2, 0, ipiv, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void sgtsvx(String fact, String trans, int n, int nrhs, float[] dl, int _dl_offset, float[] d, int _d_offset, float[] du, int _du_offset, float[] dlf, int _dlf_offset, float[] df, int _df_offset, float[] duf, int _duf_offset, float[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, org.netlib.util.floatW rcond, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sgtsvx(fact, trans, n, nrhs, dl, _dl_offset, d, _d_offset, du, _du_offset, dlf, _dlf_offset, df, _df_offset, duf, _duf_offset, du2, _du2_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void sgttrf(int n, float[] dl, float[] d, float[] du, float[] du2, int[] ipiv, org.netlib.util.intW info) {
    f2j.sgttrf(n, dl, 0, d, 0, du, 0, du2, 0, ipiv, 0, info);
  }

  public void sgttrf(int n, float[] dl, int _dl_offset, float[] d, int _d_offset, float[] du, int _du_offset, float[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    f2j.sgttrf(n, dl, _dl_offset, d, _d_offset, du, _du_offset, du2, _du2_offset, ipiv, _ipiv_offset, info);
  }

  public void sgttrs(String trans, int n, int nrhs, float[] dl, float[] d, float[] du, float[] du2, int[] ipiv, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.sgttrs(trans, n, nrhs, dl, 0, d, 0, du, 0, du2, 0, ipiv, 0, b, 0, ldb, info);
  }

  public void sgttrs(String trans, int n, int nrhs, float[] dl, int _dl_offset, float[] d, int _d_offset, float[] du, int _du_offset, float[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.sgttrs(trans, n, nrhs, dl, _dl_offset, d, _d_offset, du, _du_offset, du2, _du2_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  public void sgtts2(int itrans, int n, int nrhs, float[] dl, float[] d, float[] du, float[] du2, int[] ipiv, float[] b, int ldb) {
    f2j.sgtts2(itrans, n, nrhs, dl, 0, d, 0, du, 0, du2, 0, ipiv, 0, b, 0, ldb);
  }

  public void sgtts2(int itrans, int n, int nrhs, float[] dl, int _dl_offset, float[] d, int _d_offset, float[] du, int _du_offset, float[] du2, int _du2_offset, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb) {
    f2j.sgtts2(itrans, n, nrhs, dl, _dl_offset, d, _d_offset, du, _du_offset, du2, _du2_offset, ipiv, _ipiv_offset, b, _b_offset, ldb);
  }

  public void shgeqz(String job, String compq, String compz, int n, int ilo, int ihi, float[] h, int ldh, float[] t, int ldt, float[] alphar, float[] alphai, float[] beta, float[] q, int ldq, float[] z, int ldz, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.shgeqz(job, compq, compz, n, ilo, ihi, h, 0, ldh, t, 0, ldt, alphar, 0, alphai, 0, beta, 0, q, 0, ldq, z, 0, ldz, work, 0, lwork, info);
  }

  public void shgeqz(String job, String compq, String compz, int n, int ilo, int ihi, float[] h, int _h_offset, int ldh, float[] t, int _t_offset, int ldt, float[] alphar, int _alphar_offset, float[] alphai, int _alphai_offset, float[] beta, int _beta_offset, float[] q, int _q_offset, int ldq, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.shgeqz(job, compq, compz, n, ilo, ihi, h, _h_offset, ldh, t, _t_offset, ldt, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, q, _q_offset, ldq, z, _z_offset, ldz, work, _work_offset, lwork, info);
  }

  public void shsein(String side, String eigsrc, String initv, boolean[] select, int n, float[] h, int ldh, float[] wr, float[] wi, float[] vl, int ldvl, float[] vr, int ldvr, int mm, org.netlib.util.intW m, float[] work, int[] ifaill, int[] ifailr, org.netlib.util.intW info) {
    f2j.shsein(side, eigsrc, initv, select, 0, n, h, 0, ldh, wr, 0, wi, 0, vl, 0, ldvl, vr, 0, ldvr, mm, m, work, 0, ifaill, 0, ifailr, 0, info);
  }

  public void shsein(String side, String eigsrc, String initv, boolean[] select, int _select_offset, int n, float[] h, int _h_offset, int ldh, float[] wr, int _wr_offset, float[] wi, int _wi_offset, float[] vl, int _vl_offset, int ldvl, float[] vr, int _vr_offset, int ldvr, int mm, org.netlib.util.intW m, float[] work, int _work_offset, int[] ifaill, int _ifaill_offset, int[] ifailr, int _ifailr_offset, org.netlib.util.intW info) {
    f2j.shsein(side, eigsrc, initv, select, _select_offset, n, h, _h_offset, ldh, wr, _wr_offset, wi, _wi_offset, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, mm, m, work, _work_offset, ifaill, _ifaill_offset, ifailr, _ifailr_offset, info);
  }

  public void shseqr(String job, String compz, int n, int ilo, int ihi, float[] h, int ldh, float[] wr, float[] wi, float[] z, int ldz, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.shseqr(job, compz, n, ilo, ihi, h, 0, ldh, wr, 0, wi, 0, z, 0, ldz, work, 0, lwork, info);
  }

  public void shseqr(String job, String compz, int n, int ilo, int ihi, float[] h, int _h_offset, int ldh, float[] wr, int _wr_offset, float[] wi, int _wi_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.shseqr(job, compz, n, ilo, ihi, h, _h_offset, ldh, wr, _wr_offset, wi, _wi_offset, z, _z_offset, ldz, work, _work_offset, lwork, info);
  }

  public boolean sisnan(float sin) {
    return f2j.sisnan(sin);
  }

  public void slabad(org.netlib.util.floatW small, org.netlib.util.floatW large) {
    f2j.slabad(small, large);
  }

  public void slabrd(int m, int n, int nb, float[] a, int lda, float[] d, float[] e, float[] tauq, float[] taup, float[] x, int ldx, float[] y, int ldy) {
    f2j.slabrd(m, n, nb, a, 0, lda, d, 0, e, 0, tauq, 0, taup, 0, x, 0, ldx, y, 0, ldy);
  }

  public void slabrd(int m, int n, int nb, float[] a, int _a_offset, int lda, float[] d, int _d_offset, float[] e, int _e_offset, float[] tauq, int _tauq_offset, float[] taup, int _taup_offset, float[] x, int _x_offset, int ldx, float[] y, int _y_offset, int ldy) {
    f2j.slabrd(m, n, nb, a, _a_offset, lda, d, _d_offset, e, _e_offset, tauq, _tauq_offset, taup, _taup_offset, x, _x_offset, ldx, y, _y_offset, ldy);
  }

  public void slacn2(int n, float[] v, float[] x, int[] isgn, org.netlib.util.floatW est, org.netlib.util.intW kase, int[] isave) {
    f2j.slacn2(n, v, 0, x, 0, isgn, 0, est, kase, isave, 0);
  }

  public void slacn2(int n, float[] v, int _v_offset, float[] x, int _x_offset, int[] isgn, int _isgn_offset, org.netlib.util.floatW est, org.netlib.util.intW kase, int[] isave, int _isave_offset) {
    f2j.slacn2(n, v, _v_offset, x, _x_offset, isgn, _isgn_offset, est, kase, isave, _isave_offset);
  }

  public void slacon(int n, float[] v, float[] x, int[] isgn, org.netlib.util.floatW est, org.netlib.util.intW kase) {
    f2j.slacon(n, v, 0, x, 0, isgn, 0, est, kase);
  }

  public void slacon(int n, float[] v, int _v_offset, float[] x, int _x_offset, int[] isgn, int _isgn_offset, org.netlib.util.floatW est, org.netlib.util.intW kase) {
    f2j.slacon(n, v, _v_offset, x, _x_offset, isgn, _isgn_offset, est, kase);
  }

  public void slacpy(String uplo, int m, int n, float[] a, int lda, float[] b, int ldb) {
    f2j.slacpy(uplo, m, n, a, 0, lda, b, 0, ldb);
  }

  public void slacpy(String uplo, int m, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb) {
    f2j.slacpy(uplo, m, n, a, _a_offset, lda, b, _b_offset, ldb);
  }

  public void sladiv(float a, float b, float c, float d, org.netlib.util.floatW p, org.netlib.util.floatW q) {
    f2j.sladiv(a, b, c, d, p, q);
  }

  public void slae2(float a, float b, float c, org.netlib.util.floatW rt1, org.netlib.util.floatW rt2) {
    f2j.slae2(a, b, c, rt1, rt2);
  }

  public void slaebz(int ijob, int nitmax, int n, int mmax, int minp, int nbmin, float abstol, float reltol, float pivmin, float[] d, float[] e, float[] e2, int[] nval, float[] ab, float[] c, org.netlib.util.intW mout, int[] nab, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.slaebz(ijob, nitmax, n, mmax, minp, nbmin, abstol, reltol, pivmin, d, 0, e, 0, e2, 0, nval, 0, ab, 0, c, 0, mout, nab, 0, work, 0, iwork, 0, info);
  }

  public void slaebz(int ijob, int nitmax, int n, int mmax, int minp, int nbmin, float abstol, float reltol, float pivmin, float[] d, int _d_offset, float[] e, int _e_offset, float[] e2, int _e2_offset, int[] nval, int _nval_offset, float[] ab, int _ab_offset, float[] c, int _c_offset, org.netlib.util.intW mout, int[] nab, int _nab_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.slaebz(ijob, nitmax, n, mmax, minp, nbmin, abstol, reltol, pivmin, d, _d_offset, e, _e_offset, e2, _e2_offset, nval, _nval_offset, ab, _ab_offset, c, _c_offset, mout, nab, _nab_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void slaed0(int icompq, int qsiz, int n, float[] d, float[] e, float[] q, int ldq, float[] qstore, int ldqs, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.slaed0(icompq, qsiz, n, d, 0, e, 0, q, 0, ldq, qstore, 0, ldqs, work, 0, iwork, 0, info);
  }

  public void slaed0(int icompq, int qsiz, int n, float[] d, int _d_offset, float[] e, int _e_offset, float[] q, int _q_offset, int ldq, float[] qstore, int _qstore_offset, int ldqs, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.slaed0(icompq, qsiz, n, d, _d_offset, e, _e_offset, q, _q_offset, ldq, qstore, _qstore_offset, ldqs, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void slaed1(int n, float[] d, float[] q, int ldq, int[] indxq, org.netlib.util.floatW rho, int cutpnt, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.slaed1(n, d, 0, q, 0, ldq, indxq, 0, rho, cutpnt, work, 0, iwork, 0, info);
  }

  public void slaed1(int n, float[] d, int _d_offset, float[] q, int _q_offset, int ldq, int[] indxq, int _indxq_offset, org.netlib.util.floatW rho, int cutpnt, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.slaed1(n, d, _d_offset, q, _q_offset, ldq, indxq, _indxq_offset, rho, cutpnt, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void slaed2(org.netlib.util.intW k, int n, int n1, float[] d, float[] q, int ldq, int[] indxq, org.netlib.util.floatW rho, float[] z, float[] dlamda, float[] w, float[] q2, int[] indx, int[] indxc, int[] indxp, int[] coltyp, org.netlib.util.intW info) {
    f2j.slaed2(k, n, n1, d, 0, q, 0, ldq, indxq, 0, rho, z, 0, dlamda, 0, w, 0, q2, 0, indx, 0, indxc, 0, indxp, 0, coltyp, 0, info);
  }

  public void slaed2(org.netlib.util.intW k, int n, int n1, float[] d, int _d_offset, float[] q, int _q_offset, int ldq, int[] indxq, int _indxq_offset, org.netlib.util.floatW rho, float[] z, int _z_offset, float[] dlamda, int _dlamda_offset, float[] w, int _w_offset, float[] q2, int _q2_offset, int[] indx, int _indx_offset, int[] indxc, int _indxc_offset, int[] indxp, int _indxp_offset, int[] coltyp, int _coltyp_offset, org.netlib.util.intW info) {
    f2j.slaed2(k, n, n1, d, _d_offset, q, _q_offset, ldq, indxq, _indxq_offset, rho, z, _z_offset, dlamda, _dlamda_offset, w, _w_offset, q2, _q2_offset, indx, _indx_offset, indxc, _indxc_offset, indxp, _indxp_offset, coltyp, _coltyp_offset, info);
  }

  public void slaed3(int k, int n, int n1, float[] d, float[] q, int ldq, float rho, float[] dlamda, float[] q2, int[] indx, int[] ctot, float[] w, float[] s, org.netlib.util.intW info) {
    f2j.slaed3(k, n, n1, d, 0, q, 0, ldq, rho, dlamda, 0, q2, 0, indx, 0, ctot, 0, w, 0, s, 0, info);
  }

  public void slaed3(int k, int n, int n1, float[] d, int _d_offset, float[] q, int _q_offset, int ldq, float rho, float[] dlamda, int _dlamda_offset, float[] q2, int _q2_offset, int[] indx, int _indx_offset, int[] ctot, int _ctot_offset, float[] w, int _w_offset, float[] s, int _s_offset, org.netlib.util.intW info) {
    f2j.slaed3(k, n, n1, d, _d_offset, q, _q_offset, ldq, rho, dlamda, _dlamda_offset, q2, _q2_offset, indx, _indx_offset, ctot, _ctot_offset, w, _w_offset, s, _s_offset, info);
  }

  public void slaed4(int n, int i, float[] d, float[] z, float[] delta, float rho, org.netlib.util.floatW dlam, org.netlib.util.intW info) {
    f2j.slaed4(n, i, d, 0, z, 0, delta, 0, rho, dlam, info);
  }

  public void slaed4(int n, int i, float[] d, int _d_offset, float[] z, int _z_offset, float[] delta, int _delta_offset, float rho, org.netlib.util.floatW dlam, org.netlib.util.intW info) {
    f2j.slaed4(n, i, d, _d_offset, z, _z_offset, delta, _delta_offset, rho, dlam, info);
  }

  public void slaed5(int i, float[] d, float[] z, float[] delta, float rho, org.netlib.util.floatW dlam) {
    f2j.slaed5(i, d, 0, z, 0, delta, 0, rho, dlam);
  }

  public void slaed5(int i, float[] d, int _d_offset, float[] z, int _z_offset, float[] delta, int _delta_offset, float rho, org.netlib.util.floatW dlam) {
    f2j.slaed5(i, d, _d_offset, z, _z_offset, delta, _delta_offset, rho, dlam);
  }

  public void slaed6(int kniter, boolean orgati, float rho, float[] d, float[] z, float finit, org.netlib.util.floatW tau, org.netlib.util.intW info) {
    f2j.slaed6(kniter, orgati, rho, d, 0, z, 0, finit, tau, info);
  }

  public void slaed6(int kniter, boolean orgati, float rho, float[] d, int _d_offset, float[] z, int _z_offset, float finit, org.netlib.util.floatW tau, org.netlib.util.intW info) {
    f2j.slaed6(kniter, orgati, rho, d, _d_offset, z, _z_offset, finit, tau, info);
  }

  public void slaed7(int icompq, int n, int qsiz, int tlvls, int curlvl, int curpbm, float[] d, float[] q, int ldq, int[] indxq, org.netlib.util.floatW rho, int cutpnt, float[] qstore, int[] qptr, int[] prmptr, int[] perm, int[] givptr, int[] givcol, float[] givnum, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.slaed7(icompq, n, qsiz, tlvls, curlvl, curpbm, d, 0, q, 0, ldq, indxq, 0, rho, cutpnt, qstore, 0, qptr, 0, prmptr, 0, perm, 0, givptr, 0, givcol, 0, givnum, 0, work, 0, iwork, 0, info);
  }

  public void slaed7(int icompq, int n, int qsiz, int tlvls, int curlvl, int curpbm, float[] d, int _d_offset, float[] q, int _q_offset, int ldq, int[] indxq, int _indxq_offset, org.netlib.util.floatW rho, int cutpnt, float[] qstore, int _qstore_offset, int[] qptr, int _qptr_offset, int[] prmptr, int _prmptr_offset, int[] perm, int _perm_offset, int[] givptr, int _givptr_offset, int[] givcol, int _givcol_offset, float[] givnum, int _givnum_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.slaed7(icompq, n, qsiz, tlvls, curlvl, curpbm, d, _d_offset, q, _q_offset, ldq, indxq, _indxq_offset, rho, cutpnt, qstore, _qstore_offset, qptr, _qptr_offset, prmptr, _prmptr_offset, perm, _perm_offset, givptr, _givptr_offset, givcol, _givcol_offset, givnum, _givnum_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void slaed8(int icompq, org.netlib.util.intW k, int n, int qsiz, float[] d, float[] q, int ldq, int[] indxq, org.netlib.util.floatW rho, int cutpnt, float[] z, float[] dlamda, float[] q2, int ldq2, float[] w, int[] perm, org.netlib.util.intW givptr, int[] givcol, float[] givnum, int[] indxp, int[] indx, org.netlib.util.intW info) {
    f2j.slaed8(icompq, k, n, qsiz, d, 0, q, 0, ldq, indxq, 0, rho, cutpnt, z, 0, dlamda, 0, q2, 0, ldq2, w, 0, perm, 0, givptr, givcol, 0, givnum, 0, indxp, 0, indx, 0, info);
  }

  public void slaed8(int icompq, org.netlib.util.intW k, int n, int qsiz, float[] d, int _d_offset, float[] q, int _q_offset, int ldq, int[] indxq, int _indxq_offset, org.netlib.util.floatW rho, int cutpnt, float[] z, int _z_offset, float[] dlamda, int _dlamda_offset, float[] q2, int _q2_offset, int ldq2, float[] w, int _w_offset, int[] perm, int _perm_offset, org.netlib.util.intW givptr, int[] givcol, int _givcol_offset, float[] givnum, int _givnum_offset, int[] indxp, int _indxp_offset, int[] indx, int _indx_offset, org.netlib.util.intW info) {
    f2j.slaed8(icompq, k, n, qsiz, d, _d_offset, q, _q_offset, ldq, indxq, _indxq_offset, rho, cutpnt, z, _z_offset, dlamda, _dlamda_offset, q2, _q2_offset, ldq2, w, _w_offset, perm, _perm_offset, givptr, givcol, _givcol_offset, givnum, _givnum_offset, indxp, _indxp_offset, indx, _indx_offset, info);
  }

  public void slaed9(int k, int kstart, int kstop, int n, float[] d, float[] q, int ldq, float rho, float[] dlamda, float[] w, float[] s, int lds, org.netlib.util.intW info) {
    f2j.slaed9(k, kstart, kstop, n, d, 0, q, 0, ldq, rho, dlamda, 0, w, 0, s, 0, lds, info);
  }

  public void slaed9(int k, int kstart, int kstop, int n, float[] d, int _d_offset, float[] q, int _q_offset, int ldq, float rho, float[] dlamda, int _dlamda_offset, float[] w, int _w_offset, float[] s, int _s_offset, int lds, org.netlib.util.intW info) {
    f2j.slaed9(k, kstart, kstop, n, d, _d_offset, q, _q_offset, ldq, rho, dlamda, _dlamda_offset, w, _w_offset, s, _s_offset, lds, info);
  }

  public void slaeda(int n, int tlvls, int curlvl, int curpbm, int[] prmptr, int[] perm, int[] givptr, int[] givcol, float[] givnum, float[] q, int[] qptr, float[] z, float[] ztemp, org.netlib.util.intW info) {
    f2j.slaeda(n, tlvls, curlvl, curpbm, prmptr, 0, perm, 0, givptr, 0, givcol, 0, givnum, 0, q, 0, qptr, 0, z, 0, ztemp, 0, info);
  }

  public void slaeda(int n, int tlvls, int curlvl, int curpbm, int[] prmptr, int _prmptr_offset, int[] perm, int _perm_offset, int[] givptr, int _givptr_offset, int[] givcol, int _givcol_offset, float[] givnum, int _givnum_offset, float[] q, int _q_offset, int[] qptr, int _qptr_offset, float[] z, int _z_offset, float[] ztemp, int _ztemp_offset, org.netlib.util.intW info) {
    f2j.slaeda(n, tlvls, curlvl, curpbm, prmptr, _prmptr_offset, perm, _perm_offset, givptr, _givptr_offset, givcol, _givcol_offset, givnum, _givnum_offset, q, _q_offset, qptr, _qptr_offset, z, _z_offset, ztemp, _ztemp_offset, info);
  }

  public void slaein(boolean rightv, boolean noinit, int n, float[] h, int ldh, float wr, float wi, float[] vr, float[] vi, float[] b, int ldb, float[] work, float eps3, float smlnum, float bignum, org.netlib.util.intW info) {
    f2j.slaein(rightv, noinit, n, h, 0, ldh, wr, wi, vr, 0, vi, 0, b, 0, ldb, work, 0, eps3, smlnum, bignum, info);
  }

  public void slaein(boolean rightv, boolean noinit, int n, float[] h, int _h_offset, int ldh, float wr, float wi, float[] vr, int _vr_offset, float[] vi, int _vi_offset, float[] b, int _b_offset, int ldb, float[] work, int _work_offset, float eps3, float smlnum, float bignum, org.netlib.util.intW info) {
    f2j.slaein(rightv, noinit, n, h, _h_offset, ldh, wr, wi, vr, _vr_offset, vi, _vi_offset, b, _b_offset, ldb, work, _work_offset, eps3, smlnum, bignum, info);
  }

  public void slaev2(float a, float b, float c, org.netlib.util.floatW rt1, org.netlib.util.floatW rt2, org.netlib.util.floatW cs1, org.netlib.util.floatW sn1) {
    f2j.slaev2(a, b, c, rt1, rt2, cs1, sn1);
  }

  public void slaexc(boolean wantq, int n, float[] t, int ldt, float[] q, int ldq, int j1, int n1, int n2, float[] work, org.netlib.util.intW info) {
    f2j.slaexc(wantq, n, t, 0, ldt, q, 0, ldq, j1, n1, n2, work, 0, info);
  }

  public void slaexc(boolean wantq, int n, float[] t, int _t_offset, int ldt, float[] q, int _q_offset, int ldq, int j1, int n1, int n2, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.slaexc(wantq, n, t, _t_offset, ldt, q, _q_offset, ldq, j1, n1, n2, work, _work_offset, info);
  }

  public void slag2(float[] a, int lda, float[] b, int ldb, float safmin, org.netlib.util.floatW scale1, org.netlib.util.floatW scale2, org.netlib.util.floatW wr1, org.netlib.util.floatW wr2, org.netlib.util.floatW wi) {
    f2j.slag2(a, 0, lda, b, 0, ldb, safmin, scale1, scale2, wr1, wr2, wi);
  }

  public void slag2(float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float safmin, org.netlib.util.floatW scale1, org.netlib.util.floatW scale2, org.netlib.util.floatW wr1, org.netlib.util.floatW wr2, org.netlib.util.floatW wi) {
    f2j.slag2(a, _a_offset, lda, b, _b_offset, ldb, safmin, scale1, scale2, wr1, wr2, wi);
  }

  public void slag2d(int m, int n, float[] sa, int ldsa, double[] a, int lda, org.netlib.util.intW info) {
    f2j.slag2d(m, n, sa, 0, ldsa, a, 0, lda, info);
  }

  public void slag2d(int m, int n, float[] sa, int _sa_offset, int ldsa, double[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    f2j.slag2d(m, n, sa, _sa_offset, ldsa, a, _a_offset, lda, info);
  }

  public void slags2(boolean upper, float a1, float a2, float a3, float b1, float b2, float b3, org.netlib.util.floatW csu, org.netlib.util.floatW snu, org.netlib.util.floatW csv, org.netlib.util.floatW snv, org.netlib.util.floatW csq, org.netlib.util.floatW snq) {
    f2j.slags2(upper, a1, a2, a3, b1, b2, b3, csu, snu, csv, snv, csq, snq);
  }

  public void slagtf(int n, float[] a, float lambda, float[] b, float[] c, float tol, float[] d, int[] in, org.netlib.util.intW info) {
    f2j.slagtf(n, a, 0, lambda, b, 0, c, 0, tol, d, 0, in, 0, info);
  }

  public void slagtf(int n, float[] a, int _a_offset, float lambda, float[] b, int _b_offset, float[] c, int _c_offset, float tol, float[] d, int _d_offset, int[] in, int _in_offset, org.netlib.util.intW info) {
    f2j.slagtf(n, a, _a_offset, lambda, b, _b_offset, c, _c_offset, tol, d, _d_offset, in, _in_offset, info);
  }

  public void slagtm(String trans, int n, int nrhs, float alpha, float[] dl, float[] d, float[] du, float[] x, int ldx, float beta, float[] b, int ldb) {
    f2j.slagtm(trans, n, nrhs, alpha, dl, 0, d, 0, du, 0, x, 0, ldx, beta, b, 0, ldb);
  }

  public void slagtm(String trans, int n, int nrhs, float alpha, float[] dl, int _dl_offset, float[] d, int _d_offset, float[] du, int _du_offset, float[] x, int _x_offset, int ldx, float beta, float[] b, int _b_offset, int ldb) {
    f2j.slagtm(trans, n, nrhs, alpha, dl, _dl_offset, d, _d_offset, du, _du_offset, x, _x_offset, ldx, beta, b, _b_offset, ldb);
  }

  public void slagts(int job, int n, float[] a, float[] b, float[] c, float[] d, int[] in, float[] y, org.netlib.util.floatW tol, org.netlib.util.intW info) {
    f2j.slagts(job, n, a, 0, b, 0, c, 0, d, 0, in, 0, y, 0, tol, info);
  }

  public void slagts(int job, int n, float[] a, int _a_offset, float[] b, int _b_offset, float[] c, int _c_offset, float[] d, int _d_offset, int[] in, int _in_offset, float[] y, int _y_offset, org.netlib.util.floatW tol, org.netlib.util.intW info) {
    f2j.slagts(job, n, a, _a_offset, b, _b_offset, c, _c_offset, d, _d_offset, in, _in_offset, y, _y_offset, tol, info);
  }

  public void slagv2(float[] a, int lda, float[] b, int ldb, float[] alphar, float[] alphai, float[] beta, org.netlib.util.floatW csl, org.netlib.util.floatW snl, org.netlib.util.floatW csr, org.netlib.util.floatW snr) {
    f2j.slagv2(a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, csl, snl, csr, snr);
  }

  public void slagv2(float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] alphar, int _alphar_offset, float[] alphai, int _alphai_offset, float[] beta, int _beta_offset, org.netlib.util.floatW csl, org.netlib.util.floatW snl, org.netlib.util.floatW csr, org.netlib.util.floatW snr) {
    f2j.slagv2(a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, csl, snl, csr, snr);
  }

  public void slahqr(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int ldh, float[] wr, float[] wi, int iloz, int ihiz, float[] z, int ldz, org.netlib.util.intW info) {
    f2j.slahqr(wantt, wantz, n, ilo, ihi, h, 0, ldh, wr, 0, wi, 0, iloz, ihiz, z, 0, ldz, info);
  }

  public void slahqr(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int _h_offset, int ldh, float[] wr, int _wr_offset, float[] wi, int _wi_offset, int iloz, int ihiz, float[] z, int _z_offset, int ldz, org.netlib.util.intW info) {
    f2j.slahqr(wantt, wantz, n, ilo, ihi, h, _h_offset, ldh, wr, _wr_offset, wi, _wi_offset, iloz, ihiz, z, _z_offset, ldz, info);
  }

  public void slahr2(int n, int k, int nb, float[] a, int lda, float[] tau, float[] t, int ldt, float[] y, int ldy) {
    f2j.slahr2(n, k, nb, a, 0, lda, tau, 0, t, 0, ldt, y, 0, ldy);
  }

  public void slahr2(int n, int k, int nb, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] t, int _t_offset, int ldt, float[] y, int _y_offset, int ldy) {
    f2j.slahr2(n, k, nb, a, _a_offset, lda, tau, _tau_offset, t, _t_offset, ldt, y, _y_offset, ldy);
  }

  public void slahrd(int n, int k, int nb, float[] a, int lda, float[] tau, float[] t, int ldt, float[] y, int ldy) {
    f2j.slahrd(n, k, nb, a, 0, lda, tau, 0, t, 0, ldt, y, 0, ldy);
  }

  public void slahrd(int n, int k, int nb, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] t, int _t_offset, int ldt, float[] y, int _y_offset, int ldy) {
    f2j.slahrd(n, k, nb, a, _a_offset, lda, tau, _tau_offset, t, _t_offset, ldt, y, _y_offset, ldy);
  }

  public void slaic1(int job, int j, float[] x, float sest, float[] w, float gamma, org.netlib.util.floatW sestpr, org.netlib.util.floatW s, org.netlib.util.floatW c) {
    f2j.slaic1(job, j, x, 0, sest, w, 0, gamma, sestpr, s, c);
  }

  public void slaic1(int job, int j, float[] x, int _x_offset, float sest, float[] w, int _w_offset, float gamma, org.netlib.util.floatW sestpr, org.netlib.util.floatW s, org.netlib.util.floatW c) {
    f2j.slaic1(job, j, x, _x_offset, sest, w, _w_offset, gamma, sestpr, s, c);
  }

  public boolean slaisnan(float sin1, float sin2) {
    return f2j.slaisnan(sin1, sin2);
  }

  public void slaln2(boolean ltrans, int na, int nw, float smin, float ca, float[] a, int lda, float d1, float d2, float[] b, int ldb, float wr, float wi, float[] x, int ldx, org.netlib.util.floatW scale, org.netlib.util.floatW xnorm, org.netlib.util.intW info) {
    f2j.slaln2(ltrans, na, nw, smin, ca, a, 0, lda, d1, d2, b, 0, ldb, wr, wi, x, 0, ldx, scale, xnorm, info);
  }

  public void slaln2(boolean ltrans, int na, int nw, float smin, float ca, float[] a, int _a_offset, int lda, float d1, float d2, float[] b, int _b_offset, int ldb, float wr, float wi, float[] x, int _x_offset, int ldx, org.netlib.util.floatW scale, org.netlib.util.floatW xnorm, org.netlib.util.intW info) {
    f2j.slaln2(ltrans, na, nw, smin, ca, a, _a_offset, lda, d1, d2, b, _b_offset, ldb, wr, wi, x, _x_offset, ldx, scale, xnorm, info);
  }

  public void slals0(int icompq, int nl, int nr, int sqre, int nrhs, float[] b, int ldb, float[] bx, int ldbx, int[] perm, int givptr, int[] givcol, int ldgcol, float[] givnum, int ldgnum, float[] poles, float[] difl, float[] difr, float[] z, int k, float c, float s, float[] work, org.netlib.util.intW info) {
    f2j.slals0(icompq, nl, nr, sqre, nrhs, b, 0, ldb, bx, 0, ldbx, perm, 0, givptr, givcol, 0, ldgcol, givnum, 0, ldgnum, poles, 0, difl, 0, difr, 0, z, 0, k, c, s, work, 0, info);
  }

  public void slals0(int icompq, int nl, int nr, int sqre, int nrhs, float[] b, int _b_offset, int ldb, float[] bx, int _bx_offset, int ldbx, int[] perm, int _perm_offset, int givptr, int[] givcol, int _givcol_offset, int ldgcol, float[] givnum, int _givnum_offset, int ldgnum, float[] poles, int _poles_offset, float[] difl, int _difl_offset, float[] difr, int _difr_offset, float[] z, int _z_offset, int k, float c, float s, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.slals0(icompq, nl, nr, sqre, nrhs, b, _b_offset, ldb, bx, _bx_offset, ldbx, perm, _perm_offset, givptr, givcol, _givcol_offset, ldgcol, givnum, _givnum_offset, ldgnum, poles, _poles_offset, difl, _difl_offset, difr, _difr_offset, z, _z_offset, k, c, s, work, _work_offset, info);
  }

  public void slalsa(int icompq, int smlsiz, int n, int nrhs, float[] b, int ldb, float[] bx, int ldbx, float[] u, int ldu, float[] vt, int[] k, float[] difl, float[] difr, float[] z, float[] poles, int[] givptr, int[] givcol, int ldgcol, int[] perm, float[] givnum, float[] c, float[] s, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.slalsa(icompq, smlsiz, n, nrhs, b, 0, ldb, bx, 0, ldbx, u, 0, ldu, vt, 0, k, 0, difl, 0, difr, 0, z, 0, poles, 0, givptr, 0, givcol, 0, ldgcol, perm, 0, givnum, 0, c, 0, s, 0, work, 0, iwork, 0, info);
  }

  public void slalsa(int icompq, int smlsiz, int n, int nrhs, float[] b, int _b_offset, int ldb, float[] bx, int _bx_offset, int ldbx, float[] u, int _u_offset, int ldu, float[] vt, int _vt_offset, int[] k, int _k_offset, float[] difl, int _difl_offset, float[] difr, int _difr_offset, float[] z, int _z_offset, float[] poles, int _poles_offset, int[] givptr, int _givptr_offset, int[] givcol, int _givcol_offset, int ldgcol, int[] perm, int _perm_offset, float[] givnum, int _givnum_offset, float[] c, int _c_offset, float[] s, int _s_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.slalsa(icompq, smlsiz, n, nrhs, b, _b_offset, ldb, bx, _bx_offset, ldbx, u, _u_offset, ldu, vt, _vt_offset, k, _k_offset, difl, _difl_offset, difr, _difr_offset, z, _z_offset, poles, _poles_offset, givptr, _givptr_offset, givcol, _givcol_offset, ldgcol, perm, _perm_offset, givnum, _givnum_offset, c, _c_offset, s, _s_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void slalsd(String uplo, int smlsiz, int n, int nrhs, float[] d, float[] e, float[] b, int ldb, float rcond, org.netlib.util.intW rank, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.slalsd(uplo, smlsiz, n, nrhs, d, 0, e, 0, b, 0, ldb, rcond, rank, work, 0, iwork, 0, info);
  }

  public void slalsd(String uplo, int smlsiz, int n, int nrhs, float[] d, int _d_offset, float[] e, int _e_offset, float[] b, int _b_offset, int ldb, float rcond, org.netlib.util.intW rank, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.slalsd(uplo, smlsiz, n, nrhs, d, _d_offset, e, _e_offset, b, _b_offset, ldb, rcond, rank, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void slamrg(int n1, int n2, float[] a, int strd1, int strd2, int[] index) {
    f2j.slamrg(n1, n2, a, 0, strd1, strd2, index, 0);
  }

  public void slamrg(int n1, int n2, float[] a, int _a_offset, int strd1, int strd2, int[] index, int _index_offset) {
    f2j.slamrg(n1, n2, a, _a_offset, strd1, strd2, index, _index_offset);
  }

  public int slaneg(int n, float[] d, float[] lld, float sigma, float pivmin, int r) {
    return f2j.slaneg(n, d, 0, lld, 0, sigma, pivmin, r);
  }

  public int slaneg(int n, float[] d, int _d_offset, float[] lld, int _lld_offset, float sigma, float pivmin, int r) {
    return f2j.slaneg(n, d, _d_offset, lld, _lld_offset, sigma, pivmin, r);
  }

  public float slangb(String norm, int n, int kl, int ku, float[] ab, int ldab, float[] work) {
    return f2j.slangb(norm, n, kl, ku, ab, 0, ldab, work, 0);
  }

  public float slangb(String norm, int n, int kl, int ku, float[] ab, int _ab_offset, int ldab, float[] work, int _work_offset) {
    return f2j.slangb(norm, n, kl, ku, ab, _ab_offset, ldab, work, _work_offset);
  }

  public float slange(String norm, int m, int n, float[] a, int lda, float[] work) {
    return f2j.slange(norm, m, n, a, 0, lda, work, 0);
  }

  public float slange(String norm, int m, int n, float[] a, int _a_offset, int lda, float[] work, int _work_offset) {
    return f2j.slange(norm, m, n, a, _a_offset, lda, work, _work_offset);
  }

  public float slangt(String norm, int n, float[] dl, float[] d, float[] du) {
    return f2j.slangt(norm, n, dl, 0, d, 0, du, 0);
  }

  public float slangt(String norm, int n, float[] dl, int _dl_offset, float[] d, int _d_offset, float[] du, int _du_offset) {
    return f2j.slangt(norm, n, dl, _dl_offset, d, _d_offset, du, _du_offset);
  }

  public float slanhs(String norm, int n, float[] a, int lda, float[] work) {
    return f2j.slanhs(norm, n, a, 0, lda, work, 0);
  }

  public float slanhs(String norm, int n, float[] a, int _a_offset, int lda, float[] work, int _work_offset) {
    return f2j.slanhs(norm, n, a, _a_offset, lda, work, _work_offset);
  }

  public float slansb(String norm, String uplo, int n, int k, float[] ab, int ldab, float[] work) {
    return f2j.slansb(norm, uplo, n, k, ab, 0, ldab, work, 0);
  }

  public float slansb(String norm, String uplo, int n, int k, float[] ab, int _ab_offset, int ldab, float[] work, int _work_offset) {
    return f2j.slansb(norm, uplo, n, k, ab, _ab_offset, ldab, work, _work_offset);
  }

  public float slansp(String norm, String uplo, int n, float[] ap, float[] work) {
    return f2j.slansp(norm, uplo, n, ap, 0, work, 0);
  }

  public float slansp(String norm, String uplo, int n, float[] ap, int _ap_offset, float[] work, int _work_offset) {
    return f2j.slansp(norm, uplo, n, ap, _ap_offset, work, _work_offset);
  }

  public float slanst(String norm, int n, float[] d, float[] e) {
    return f2j.slanst(norm, n, d, 0, e, 0);
  }

  public float slanst(String norm, int n, float[] d, int _d_offset, float[] e, int _e_offset) {
    return f2j.slanst(norm, n, d, _d_offset, e, _e_offset);
  }

  public float slansy(String norm, String uplo, int n, float[] a, int lda, float[] work) {
    return f2j.slansy(norm, uplo, n, a, 0, lda, work, 0);
  }

  public float slansy(String norm, String uplo, int n, float[] a, int _a_offset, int lda, float[] work, int _work_offset) {
    return f2j.slansy(norm, uplo, n, a, _a_offset, lda, work, _work_offset);
  }

  public float slantb(String norm, String uplo, String diag, int n, int k, float[] ab, int ldab, float[] work) {
    return f2j.slantb(norm, uplo, diag, n, k, ab, 0, ldab, work, 0);
  }

  public float slantb(String norm, String uplo, String diag, int n, int k, float[] ab, int _ab_offset, int ldab, float[] work, int _work_offset) {
    return f2j.slantb(norm, uplo, diag, n, k, ab, _ab_offset, ldab, work, _work_offset);
  }

  public float slantp(String norm, String uplo, String diag, int n, float[] ap, float[] work) {
    return f2j.slantp(norm, uplo, diag, n, ap, 0, work, 0);
  }

  public float slantp(String norm, String uplo, String diag, int n, float[] ap, int _ap_offset, float[] work, int _work_offset) {
    return f2j.slantp(norm, uplo, diag, n, ap, _ap_offset, work, _work_offset);
  }

  public float slantr(String norm, String uplo, String diag, int m, int n, float[] a, int lda, float[] work) {
    return f2j.slantr(norm, uplo, diag, m, n, a, 0, lda, work, 0);
  }

  public float slantr(String norm, String uplo, String diag, int m, int n, float[] a, int _a_offset, int lda, float[] work, int _work_offset) {
    return f2j.slantr(norm, uplo, diag, m, n, a, _a_offset, lda, work, _work_offset);
  }

  public void slanv2(org.netlib.util.floatW a, org.netlib.util.floatW b, org.netlib.util.floatW c, org.netlib.util.floatW d, org.netlib.util.floatW rt1r, org.netlib.util.floatW rt1i, org.netlib.util.floatW rt2r, org.netlib.util.floatW rt2i, org.netlib.util.floatW cs, org.netlib.util.floatW sn) {
    f2j.slanv2(a, b, c, d, rt1r, rt1i, rt2r, rt2i, cs, sn);
  }

  public void slapll(int n, float[] x, int incx, float[] y, int incy, org.netlib.util.floatW ssmin) {
    f2j.slapll(n, x, 0, incx, y, 0, incy, ssmin);
  }

  public void slapll(int n, float[] x, int _x_offset, int incx, float[] y, int _y_offset, int incy, org.netlib.util.floatW ssmin) {
    f2j.slapll(n, x, _x_offset, incx, y, _y_offset, incy, ssmin);
  }

  public void slapmt(boolean forwrd, int m, int n, float[] x, int ldx, int[] k) {
    f2j.slapmt(forwrd, m, n, x, 0, ldx, k, 0);
  }

  public void slapmt(boolean forwrd, int m, int n, float[] x, int _x_offset, int ldx, int[] k, int _k_offset) {
    f2j.slapmt(forwrd, m, n, x, _x_offset, ldx, k, _k_offset);
  }

  public float slapy2(float x, float y) {
    return f2j.slapy2(x, y);
  }

  public float slapy3(float x, float y, float z) {
    return f2j.slapy3(x, y, z);
  }

  public void slaqgb(int m, int n, int kl, int ku, float[] ab, int ldab, float[] r, float[] c, float rowcnd, float colcnd, float amax, org.netlib.util.StringW equed) {
    f2j.slaqgb(m, n, kl, ku, ab, 0, ldab, r, 0, c, 0, rowcnd, colcnd, amax, equed);
  }

  public void slaqgb(int m, int n, int kl, int ku, float[] ab, int _ab_offset, int ldab, float[] r, int _r_offset, float[] c, int _c_offset, float rowcnd, float colcnd, float amax, org.netlib.util.StringW equed) {
    f2j.slaqgb(m, n, kl, ku, ab, _ab_offset, ldab, r, _r_offset, c, _c_offset, rowcnd, colcnd, amax, equed);
  }

  public void slaqge(int m, int n, float[] a, int lda, float[] r, float[] c, float rowcnd, float colcnd, float amax, org.netlib.util.StringW equed) {
    f2j.slaqge(m, n, a, 0, lda, r, 0, c, 0, rowcnd, colcnd, amax, equed);
  }

  public void slaqge(int m, int n, float[] a, int _a_offset, int lda, float[] r, int _r_offset, float[] c, int _c_offset, float rowcnd, float colcnd, float amax, org.netlib.util.StringW equed) {
    f2j.slaqge(m, n, a, _a_offset, lda, r, _r_offset, c, _c_offset, rowcnd, colcnd, amax, equed);
  }

  public void slaqp2(int m, int n, int offset, float[] a, int lda, int[] jpvt, float[] tau, float[] vn1, float[] vn2, float[] work) {
    f2j.slaqp2(m, n, offset, a, 0, lda, jpvt, 0, tau, 0, vn1, 0, vn2, 0, work, 0);
  }

  public void slaqp2(int m, int n, int offset, float[] a, int _a_offset, int lda, int[] jpvt, int _jpvt_offset, float[] tau, int _tau_offset, float[] vn1, int _vn1_offset, float[] vn2, int _vn2_offset, float[] work, int _work_offset) {
    f2j.slaqp2(m, n, offset, a, _a_offset, lda, jpvt, _jpvt_offset, tau, _tau_offset, vn1, _vn1_offset, vn2, _vn2_offset, work, _work_offset);
  }

  public void slaqps(int m, int n, int offset, int nb, org.netlib.util.intW kb, float[] a, int lda, int[] jpvt, float[] tau, float[] vn1, float[] vn2, float[] auxv, float[] f, int ldf) {
    f2j.slaqps(m, n, offset, nb, kb, a, 0, lda, jpvt, 0, tau, 0, vn1, 0, vn2, 0, auxv, 0, f, 0, ldf);
  }

  public void slaqps(int m, int n, int offset, int nb, org.netlib.util.intW kb, float[] a, int _a_offset, int lda, int[] jpvt, int _jpvt_offset, float[] tau, int _tau_offset, float[] vn1, int _vn1_offset, float[] vn2, int _vn2_offset, float[] auxv, int _auxv_offset, float[] f, int _f_offset, int ldf) {
    f2j.slaqps(m, n, offset, nb, kb, a, _a_offset, lda, jpvt, _jpvt_offset, tau, _tau_offset, vn1, _vn1_offset, vn2, _vn2_offset, auxv, _auxv_offset, f, _f_offset, ldf);
  }

  public void slaqr0(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int ldh, float[] wr, float[] wi, int iloz, int ihiz, float[] z, int ldz, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.slaqr0(wantt, wantz, n, ilo, ihi, h, 0, ldh, wr, 0, wi, 0, iloz, ihiz, z, 0, ldz, work, 0, lwork, info);
  }

  public void slaqr0(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int _h_offset, int ldh, float[] wr, int _wr_offset, float[] wi, int _wi_offset, int iloz, int ihiz, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.slaqr0(wantt, wantz, n, ilo, ihi, h, _h_offset, ldh, wr, _wr_offset, wi, _wi_offset, iloz, ihiz, z, _z_offset, ldz, work, _work_offset, lwork, info);
  }

  public void slaqr1(int n, float[] h, int ldh, float sr1, float si1, float sr2, float si2, float[] v) {
    f2j.slaqr1(n, h, 0, ldh, sr1, si1, sr2, si2, v, 0);
  }

  public void slaqr1(int n, float[] h, int _h_offset, int ldh, float sr1, float si1, float sr2, float si2, float[] v, int _v_offset) {
    f2j.slaqr1(n, h, _h_offset, ldh, sr1, si1, sr2, si2, v, _v_offset);
  }

  public void slaqr2(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, float[] h, int ldh, int iloz, int ihiz, float[] z, int ldz, org.netlib.util.intW ns, org.netlib.util.intW nd, float[] sr, float[] si, float[] v, int ldv, int nh, float[] t, int ldt, int nv, float[] wv, int ldwv, float[] work, int lwork) {
    f2j.slaqr2(wantt, wantz, n, ktop, kbot, nw, h, 0, ldh, iloz, ihiz, z, 0, ldz, ns, nd, sr, 0, si, 0, v, 0, ldv, nh, t, 0, ldt, nv, wv, 0, ldwv, work, 0, lwork);
  }

  public void slaqr2(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, float[] h, int _h_offset, int ldh, int iloz, int ihiz, float[] z, int _z_offset, int ldz, org.netlib.util.intW ns, org.netlib.util.intW nd, float[] sr, int _sr_offset, float[] si, int _si_offset, float[] v, int _v_offset, int ldv, int nh, float[] t, int _t_offset, int ldt, int nv, float[] wv, int _wv_offset, int ldwv, float[] work, int _work_offset, int lwork) {
    f2j.slaqr2(wantt, wantz, n, ktop, kbot, nw, h, _h_offset, ldh, iloz, ihiz, z, _z_offset, ldz, ns, nd, sr, _sr_offset, si, _si_offset, v, _v_offset, ldv, nh, t, _t_offset, ldt, nv, wv, _wv_offset, ldwv, work, _work_offset, lwork);
  }

  public void slaqr3(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, float[] h, int ldh, int iloz, int ihiz, float[] z, int ldz, org.netlib.util.intW ns, org.netlib.util.intW nd, float[] sr, float[] si, float[] v, int ldv, int nh, float[] t, int ldt, int nv, float[] wv, int ldwv, float[] work, int lwork) {
    f2j.slaqr3(wantt, wantz, n, ktop, kbot, nw, h, 0, ldh, iloz, ihiz, z, 0, ldz, ns, nd, sr, 0, si, 0, v, 0, ldv, nh, t, 0, ldt, nv, wv, 0, ldwv, work, 0, lwork);
  }

  public void slaqr3(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, float[] h, int _h_offset, int ldh, int iloz, int ihiz, float[] z, int _z_offset, int ldz, org.netlib.util.intW ns, org.netlib.util.intW nd, float[] sr, int _sr_offset, float[] si, int _si_offset, float[] v, int _v_offset, int ldv, int nh, float[] t, int _t_offset, int ldt, int nv, float[] wv, int _wv_offset, int ldwv, float[] work, int _work_offset, int lwork) {
    f2j.slaqr3(wantt, wantz, n, ktop, kbot, nw, h, _h_offset, ldh, iloz, ihiz, z, _z_offset, ldz, ns, nd, sr, _sr_offset, si, _si_offset, v, _v_offset, ldv, nh, t, _t_offset, ldt, nv, wv, _wv_offset, ldwv, work, _work_offset, lwork);
  }

  public void slaqr4(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int ldh, float[] wr, float[] wi, int iloz, int ihiz, float[] z, int ldz, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.slaqr4(wantt, wantz, n, ilo, ihi, h, 0, ldh, wr, 0, wi, 0, iloz, ihiz, z, 0, ldz, work, 0, lwork, info);
  }

  public void slaqr4(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int _h_offset, int ldh, float[] wr, int _wr_offset, float[] wi, int _wi_offset, int iloz, int ihiz, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.slaqr4(wantt, wantz, n, ilo, ihi, h, _h_offset, ldh, wr, _wr_offset, wi, _wi_offset, iloz, ihiz, z, _z_offset, ldz, work, _work_offset, lwork, info);
  }

  public void slaqr5(boolean wantt, boolean wantz, int kacc22, int n, int ktop, int kbot, int nshfts, float[] sr, float[] si, float[] h, int ldh, int iloz, int ihiz, float[] z, int ldz, float[] v, int ldv, float[] u, int ldu, int nv, float[] wv, int ldwv, int nh, float[] wh, int ldwh) {
    f2j.slaqr5(wantt, wantz, kacc22, n, ktop, kbot, nshfts, sr, 0, si, 0, h, 0, ldh, iloz, ihiz, z, 0, ldz, v, 0, ldv, u, 0, ldu, nv, wv, 0, ldwv, nh, wh, 0, ldwh);
  }

  public void slaqr5(boolean wantt, boolean wantz, int kacc22, int n, int ktop, int kbot, int nshfts, float[] sr, int _sr_offset, float[] si, int _si_offset, float[] h, int _h_offset, int ldh, int iloz, int ihiz, float[] z, int _z_offset, int ldz, float[] v, int _v_offset, int ldv, float[] u, int _u_offset, int ldu, int nv, float[] wv, int _wv_offset, int ldwv, int nh, float[] wh, int _wh_offset, int ldwh) {
    f2j.slaqr5(wantt, wantz, kacc22, n, ktop, kbot, nshfts, sr, _sr_offset, si, _si_offset, h, _h_offset, ldh, iloz, ihiz, z, _z_offset, ldz, v, _v_offset, ldv, u, _u_offset, ldu, nv, wv, _wv_offset, ldwv, nh, wh, _wh_offset, ldwh);
  }

  public void slaqsb(String uplo, int n, int kd, float[] ab, int ldab, float[] s, float scond, float amax, org.netlib.util.StringW equed) {
    f2j.slaqsb(uplo, n, kd, ab, 0, ldab, s, 0, scond, amax, equed);
  }

  public void slaqsb(String uplo, int n, int kd, float[] ab, int _ab_offset, int ldab, float[] s, int _s_offset, float scond, float amax, org.netlib.util.StringW equed) {
    f2j.slaqsb(uplo, n, kd, ab, _ab_offset, ldab, s, _s_offset, scond, amax, equed);
  }

  public void slaqsp(String uplo, int n, float[] ap, float[] s, float scond, float amax, org.netlib.util.StringW equed) {
    f2j.slaqsp(uplo, n, ap, 0, s, 0, scond, amax, equed);
  }

  public void slaqsp(String uplo, int n, float[] ap, int _ap_offset, float[] s, int _s_offset, float scond, float amax, org.netlib.util.StringW equed) {
    f2j.slaqsp(uplo, n, ap, _ap_offset, s, _s_offset, scond, amax, equed);
  }

  public void slaqsy(String uplo, int n, float[] a, int lda, float[] s, float scond, float amax, org.netlib.util.StringW equed) {
    f2j.slaqsy(uplo, n, a, 0, lda, s, 0, scond, amax, equed);
  }

  public void slaqsy(String uplo, int n, float[] a, int _a_offset, int lda, float[] s, int _s_offset, float scond, float amax, org.netlib.util.StringW equed) {
    f2j.slaqsy(uplo, n, a, _a_offset, lda, s, _s_offset, scond, amax, equed);
  }

  public void slaqtr(boolean ltran, boolean lreal, int n, float[] t, int ldt, float[] b, float w, org.netlib.util.floatW scale, float[] x, float[] work, org.netlib.util.intW info) {
    f2j.slaqtr(ltran, lreal, n, t, 0, ldt, b, 0, w, scale, x, 0, work, 0, info);
  }

  public void slaqtr(boolean ltran, boolean lreal, int n, float[] t, int _t_offset, int ldt, float[] b, int _b_offset, float w, org.netlib.util.floatW scale, float[] x, int _x_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.slaqtr(ltran, lreal, n, t, _t_offset, ldt, b, _b_offset, w, scale, x, _x_offset, work, _work_offset, info);
  }

  public void slar1v(int n, int b1, int bn, float lambda, float[] d, float[] l, float[] ld, float[] lld, float pivmin, float gaptol, float[] z, boolean wantnc, org.netlib.util.intW negcnt, org.netlib.util.floatW ztz, org.netlib.util.floatW mingma, org.netlib.util.intW r, int[] isuppz, org.netlib.util.floatW nrminv, org.netlib.util.floatW resid, org.netlib.util.floatW rqcorr, float[] work) {
    f2j.slar1v(n, b1, bn, lambda, d, 0, l, 0, ld, 0, lld, 0, pivmin, gaptol, z, 0, wantnc, negcnt, ztz, mingma, r, isuppz, 0, nrminv, resid, rqcorr, work, 0);
  }

  public void slar1v(int n, int b1, int bn, float lambda, float[] d, int _d_offset, float[] l, int _l_offset, float[] ld, int _ld_offset, float[] lld, int _lld_offset, float pivmin, float gaptol, float[] z, int _z_offset, boolean wantnc, org.netlib.util.intW negcnt, org.netlib.util.floatW ztz, org.netlib.util.floatW mingma, org.netlib.util.intW r, int[] isuppz, int _isuppz_offset, org.netlib.util.floatW nrminv, org.netlib.util.floatW resid, org.netlib.util.floatW rqcorr, float[] work, int _work_offset) {
    f2j.slar1v(n, b1, bn, lambda, d, _d_offset, l, _l_offset, ld, _ld_offset, lld, _lld_offset, pivmin, gaptol, z, _z_offset, wantnc, negcnt, ztz, mingma, r, isuppz, _isuppz_offset, nrminv, resid, rqcorr, work, _work_offset);
  }

  public void slar2v(int n, float[] x, float[] y, float[] z, int incx, float[] c, float[] s, int incc) {
    f2j.slar2v(n, x, 0, y, 0, z, 0, incx, c, 0, s, 0, incc);
  }

  public void slar2v(int n, float[] x, int _x_offset, float[] y, int _y_offset, float[] z, int _z_offset, int incx, float[] c, int _c_offset, float[] s, int _s_offset, int incc) {
    f2j.slar2v(n, x, _x_offset, y, _y_offset, z, _z_offset, incx, c, _c_offset, s, _s_offset, incc);
  }

  public void slarf(String side, int m, int n, float[] v, int incv, float tau, float[] c, int Ldc, float[] work) {
    f2j.slarf(side, m, n, v, 0, incv, tau, c, 0, Ldc, work, 0);
  }

  public void slarf(String side, int m, int n, float[] v, int _v_offset, int incv, float tau, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset) {
    f2j.slarf(side, m, n, v, _v_offset, incv, tau, c, _c_offset, Ldc, work, _work_offset);
  }

  public void slarfb(String side, String trans, String direct, String storev, int m, int n, int k, float[] v, int ldv, float[] t, int ldt, float[] c, int Ldc, float[] work, int ldwork) {
    f2j.slarfb(side, trans, direct, storev, m, n, k, v, 0, ldv, t, 0, ldt, c, 0, Ldc, work, 0, ldwork);
  }

  public void slarfb(String side, String trans, String direct, String storev, int m, int n, int k, float[] v, int _v_offset, int ldv, float[] t, int _t_offset, int ldt, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, int ldwork) {
    f2j.slarfb(side, trans, direct, storev, m, n, k, v, _v_offset, ldv, t, _t_offset, ldt, c, _c_offset, Ldc, work, _work_offset, ldwork);
  }

  public void slarfg(int n, org.netlib.util.floatW alpha, float[] x, int incx, org.netlib.util.floatW tau) {
    f2j.slarfg(n, alpha, x, 0, incx, tau);
  }

  public void slarfg(int n, org.netlib.util.floatW alpha, float[] x, int _x_offset, int incx, org.netlib.util.floatW tau) {
    f2j.slarfg(n, alpha, x, _x_offset, incx, tau);
  }

  public void slarft(String direct, String storev, int n, int k, float[] v, int ldv, float[] tau, float[] t, int ldt) {
    f2j.slarft(direct, storev, n, k, v, 0, ldv, tau, 0, t, 0, ldt);
  }

  public void slarft(String direct, String storev, int n, int k, float[] v, int _v_offset, int ldv, float[] tau, int _tau_offset, float[] t, int _t_offset, int ldt) {
    f2j.slarft(direct, storev, n, k, v, _v_offset, ldv, tau, _tau_offset, t, _t_offset, ldt);
  }

  public void slarfx(String side, int m, int n, float[] v, float tau, float[] c, int Ldc, float[] work) {
    f2j.slarfx(side, m, n, v, 0, tau, c, 0, Ldc, work, 0);
  }

  public void slarfx(String side, int m, int n, float[] v, int _v_offset, float tau, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset) {
    f2j.slarfx(side, m, n, v, _v_offset, tau, c, _c_offset, Ldc, work, _work_offset);
  }

  public void slargv(int n, float[] x, int incx, float[] y, int incy, float[] c, int incc) {
    f2j.slargv(n, x, 0, incx, y, 0, incy, c, 0, incc);
  }

  public void slargv(int n, float[] x, int _x_offset, int incx, float[] y, int _y_offset, int incy, float[] c, int _c_offset, int incc) {
    f2j.slargv(n, x, _x_offset, incx, y, _y_offset, incy, c, _c_offset, incc);
  }

  public void slarnv(int idist, int[] iseed, int n, float[] x) {
    f2j.slarnv(idist, iseed, 0, n, x, 0);
  }

  public void slarnv(int idist, int[] iseed, int _iseed_offset, int n, float[] x, int _x_offset) {
    f2j.slarnv(idist, iseed, _iseed_offset, n, x, _x_offset);
  }

  public void slarra(int n, float[] d, float[] e, float[] e2, float spltol, float tnrm, org.netlib.util.intW nsplit, int[] isplit, org.netlib.util.intW info) {
    f2j.slarra(n, d, 0, e, 0, e2, 0, spltol, tnrm, nsplit, isplit, 0, info);
  }

  public void slarra(int n, float[] d, int _d_offset, float[] e, int _e_offset, float[] e2, int _e2_offset, float spltol, float tnrm, org.netlib.util.intW nsplit, int[] isplit, int _isplit_offset, org.netlib.util.intW info) {
    f2j.slarra(n, d, _d_offset, e, _e_offset, e2, _e2_offset, spltol, tnrm, nsplit, isplit, _isplit_offset, info);
  }

  public void slarrb(int n, float[] d, float[] lld, int ifirst, int ilast, float rtol1, float rtol2, int offset, float[] w, float[] wgap, float[] werr, float[] work, int[] iwork, float pivmin, float spdiam, int twist, org.netlib.util.intW info) {
    f2j.slarrb(n, d, 0, lld, 0, ifirst, ilast, rtol1, rtol2, offset, w, 0, wgap, 0, werr, 0, work, 0, iwork, 0, pivmin, spdiam, twist, info);
  }

  public void slarrb(int n, float[] d, int _d_offset, float[] lld, int _lld_offset, int ifirst, int ilast, float rtol1, float rtol2, int offset, float[] w, int _w_offset, float[] wgap, int _wgap_offset, float[] werr, int _werr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, float pivmin, float spdiam, int twist, org.netlib.util.intW info) {
    f2j.slarrb(n, d, _d_offset, lld, _lld_offset, ifirst, ilast, rtol1, rtol2, offset, w, _w_offset, wgap, _wgap_offset, werr, _werr_offset, work, _work_offset, iwork, _iwork_offset, pivmin, spdiam, twist, info);
  }

  public void slarrc(String jobt, int n, float vl, float vu, float[] d, float[] e, float pivmin, org.netlib.util.intW eigcnt, org.netlib.util.intW lcnt, org.netlib.util.intW rcnt, org.netlib.util.intW info) {
    f2j.slarrc(jobt, n, vl, vu, d, 0, e, 0, pivmin, eigcnt, lcnt, rcnt, info);
  }

  public void slarrc(String jobt, int n, float vl, float vu, float[] d, int _d_offset, float[] e, int _e_offset, float pivmin, org.netlib.util.intW eigcnt, org.netlib.util.intW lcnt, org.netlib.util.intW rcnt, org.netlib.util.intW info) {
    f2j.slarrc(jobt, n, vl, vu, d, _d_offset, e, _e_offset, pivmin, eigcnt, lcnt, rcnt, info);
  }

  public void slarrd(String range, String order, int n, float vl, float vu, int il, int iu, float[] gers, float reltol, float[] d, float[] e, float[] e2, float pivmin, int nsplit, int[] isplit, org.netlib.util.intW m, float[] w, float[] werr, org.netlib.util.floatW wl, org.netlib.util.floatW wu, int[] iblock, int[] indexw, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.slarrd(range, order, n, vl, vu, il, iu, gers, 0, reltol, d, 0, e, 0, e2, 0, pivmin, nsplit, isplit, 0, m, w, 0, werr, 0, wl, wu, iblock, 0, indexw, 0, work, 0, iwork, 0, info);
  }

  public void slarrd(String range, String order, int n, float vl, float vu, int il, int iu, float[] gers, int _gers_offset, float reltol, float[] d, int _d_offset, float[] e, int _e_offset, float[] e2, int _e2_offset, float pivmin, int nsplit, int[] isplit, int _isplit_offset, org.netlib.util.intW m, float[] w, int _w_offset, float[] werr, int _werr_offset, org.netlib.util.floatW wl, org.netlib.util.floatW wu, int[] iblock, int _iblock_offset, int[] indexw, int _indexw_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.slarrd(range, order, n, vl, vu, il, iu, gers, _gers_offset, reltol, d, _d_offset, e, _e_offset, e2, _e2_offset, pivmin, nsplit, isplit, _isplit_offset, m, w, _w_offset, werr, _werr_offset, wl, wu, iblock, _iblock_offset, indexw, _indexw_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void slarre(String range, int n, org.netlib.util.floatW vl, org.netlib.util.floatW vu, int il, int iu, float[] d, float[] e, float[] e2, float rtol1, float rtol2, float spltol, org.netlib.util.intW nsplit, int[] isplit, org.netlib.util.intW m, float[] w, float[] werr, float[] wgap, int[] iblock, int[] indexw, float[] gers, org.netlib.util.floatW pivmin, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.slarre(range, n, vl, vu, il, iu, d, 0, e, 0, e2, 0, rtol1, rtol2, spltol, nsplit, isplit, 0, m, w, 0, werr, 0, wgap, 0, iblock, 0, indexw, 0, gers, 0, pivmin, work, 0, iwork, 0, info);
  }

  public void slarre(String range, int n, org.netlib.util.floatW vl, org.netlib.util.floatW vu, int il, int iu, float[] d, int _d_offset, float[] e, int _e_offset, float[] e2, int _e2_offset, float rtol1, float rtol2, float spltol, org.netlib.util.intW nsplit, int[] isplit, int _isplit_offset, org.netlib.util.intW m, float[] w, int _w_offset, float[] werr, int _werr_offset, float[] wgap, int _wgap_offset, int[] iblock, int _iblock_offset, int[] indexw, int _indexw_offset, float[] gers, int _gers_offset, org.netlib.util.floatW pivmin, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.slarre(range, n, vl, vu, il, iu, d, _d_offset, e, _e_offset, e2, _e2_offset, rtol1, rtol2, spltol, nsplit, isplit, _isplit_offset, m, w, _w_offset, werr, _werr_offset, wgap, _wgap_offset, iblock, _iblock_offset, indexw, _indexw_offset, gers, _gers_offset, pivmin, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void slarrf(int n, float[] d, float[] l, float[] ld, int clstrt, int clend, float[] w, float[] wgap, float[] werr, float spdiam, float clgapl, float clgapr, float pivmin, org.netlib.util.floatW sigma, float[] dplus, float[] lplus, float[] work, org.netlib.util.intW info) {
    f2j.slarrf(n, d, 0, l, 0, ld, 0, clstrt, clend, w, 0, wgap, 0, werr, 0, spdiam, clgapl, clgapr, pivmin, sigma, dplus, 0, lplus, 0, work, 0, info);
  }

  public void slarrf(int n, float[] d, int _d_offset, float[] l, int _l_offset, float[] ld, int _ld_offset, int clstrt, int clend, float[] w, int _w_offset, float[] wgap, int _wgap_offset, float[] werr, int _werr_offset, float spdiam, float clgapl, float clgapr, float pivmin, org.netlib.util.floatW sigma, float[] dplus, int _dplus_offset, float[] lplus, int _lplus_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.slarrf(n, d, _d_offset, l, _l_offset, ld, _ld_offset, clstrt, clend, w, _w_offset, wgap, _wgap_offset, werr, _werr_offset, spdiam, clgapl, clgapr, pivmin, sigma, dplus, _dplus_offset, lplus, _lplus_offset, work, _work_offset, info);
  }

  public void slarrj(int n, float[] d, float[] e2, int ifirst, int ilast, float rtol, int offset, float[] w, float[] werr, float[] work, int[] iwork, float pivmin, float spdiam, org.netlib.util.intW info) {
    f2j.slarrj(n, d, 0, e2, 0, ifirst, ilast, rtol, offset, w, 0, werr, 0, work, 0, iwork, 0, pivmin, spdiam, info);
  }

  public void slarrj(int n, float[] d, int _d_offset, float[] e2, int _e2_offset, int ifirst, int ilast, float rtol, int offset, float[] w, int _w_offset, float[] werr, int _werr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, float pivmin, float spdiam, org.netlib.util.intW info) {
    f2j.slarrj(n, d, _d_offset, e2, _e2_offset, ifirst, ilast, rtol, offset, w, _w_offset, werr, _werr_offset, work, _work_offset, iwork, _iwork_offset, pivmin, spdiam, info);
  }

  public void slarrk(int n, int iw, float gl, float gu, float[] d, float[] e2, float pivmin, float reltol, org.netlib.util.floatW w, org.netlib.util.floatW werr, org.netlib.util.intW info) {
    f2j.slarrk(n, iw, gl, gu, d, 0, e2, 0, pivmin, reltol, w, werr, info);
  }

  public void slarrk(int n, int iw, float gl, float gu, float[] d, int _d_offset, float[] e2, int _e2_offset, float pivmin, float reltol, org.netlib.util.floatW w, org.netlib.util.floatW werr, org.netlib.util.intW info) {
    f2j.slarrk(n, iw, gl, gu, d, _d_offset, e2, _e2_offset, pivmin, reltol, w, werr, info);
  }

  public void slarrr(int n, float[] d, float[] e, org.netlib.util.intW info) {
    f2j.slarrr(n, d, 0, e, 0, info);
  }

  public void slarrr(int n, float[] d, int _d_offset, float[] e, int _e_offset, org.netlib.util.intW info) {
    f2j.slarrr(n, d, _d_offset, e, _e_offset, info);
  }

  public void slarrv(int n, float vl, float vu, float[] d, float[] l, float pivmin, int[] isplit, int m, int dol, int dou, float minrgp, org.netlib.util.floatW rtol1, org.netlib.util.floatW rtol2, float[] w, float[] werr, float[] wgap, int[] iblock, int[] indexw, float[] gers, float[] z, int ldz, int[] isuppz, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.slarrv(n, vl, vu, d, 0, l, 0, pivmin, isplit, 0, m, dol, dou, minrgp, rtol1, rtol2, w, 0, werr, 0, wgap, 0, iblock, 0, indexw, 0, gers, 0, z, 0, ldz, isuppz, 0, work, 0, iwork, 0, info);
  }

  public void slarrv(int n, float vl, float vu, float[] d, int _d_offset, float[] l, int _l_offset, float pivmin, int[] isplit, int _isplit_offset, int m, int dol, int dou, float minrgp, org.netlib.util.floatW rtol1, org.netlib.util.floatW rtol2, float[] w, int _w_offset, float[] werr, int _werr_offset, float[] wgap, int _wgap_offset, int[] iblock, int _iblock_offset, int[] indexw, int _indexw_offset, float[] gers, int _gers_offset, float[] z, int _z_offset, int ldz, int[] isuppz, int _isuppz_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.slarrv(n, vl, vu, d, _d_offset, l, _l_offset, pivmin, isplit, _isplit_offset, m, dol, dou, minrgp, rtol1, rtol2, w, _w_offset, werr, _werr_offset, wgap, _wgap_offset, iblock, _iblock_offset, indexw, _indexw_offset, gers, _gers_offset, z, _z_offset, ldz, isuppz, _isuppz_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void slartg(float f, float g, org.netlib.util.floatW cs, org.netlib.util.floatW sn, org.netlib.util.floatW r) {
    f2j.slartg(f, g, cs, sn, r);
  }

  public void slartv(int n, float[] x, int incx, float[] y, int incy, float[] c, float[] s, int incc) {
    f2j.slartv(n, x, 0, incx, y, 0, incy, c, 0, s, 0, incc);
  }

  public void slartv(int n, float[] x, int _x_offset, int incx, float[] y, int _y_offset, int incy, float[] c, int _c_offset, float[] s, int _s_offset, int incc) {
    f2j.slartv(n, x, _x_offset, incx, y, _y_offset, incy, c, _c_offset, s, _s_offset, incc);
  }

  public void slaruv(int[] iseed, int n, float[] x) {
    f2j.slaruv(iseed, 0, n, x, 0);
  }

  public void slaruv(int[] iseed, int _iseed_offset, int n, float[] x, int _x_offset) {
    f2j.slaruv(iseed, _iseed_offset, n, x, _x_offset);
  }

  public void slarz(String side, int m, int n, int l, float[] v, int incv, float tau, float[] c, int Ldc, float[] work) {
    f2j.slarz(side, m, n, l, v, 0, incv, tau, c, 0, Ldc, work, 0);
  }

  public void slarz(String side, int m, int n, int l, float[] v, int _v_offset, int incv, float tau, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset) {
    f2j.slarz(side, m, n, l, v, _v_offset, incv, tau, c, _c_offset, Ldc, work, _work_offset);
  }

  public void slarzb(String side, String trans, String direct, String storev, int m, int n, int k, int l, float[] v, int ldv, float[] t, int ldt, float[] c, int Ldc, float[] work, int ldwork) {
    f2j.slarzb(side, trans, direct, storev, m, n, k, l, v, 0, ldv, t, 0, ldt, c, 0, Ldc, work, 0, ldwork);
  }

  public void slarzb(String side, String trans, String direct, String storev, int m, int n, int k, int l, float[] v, int _v_offset, int ldv, float[] t, int _t_offset, int ldt, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, int ldwork) {
    f2j.slarzb(side, trans, direct, storev, m, n, k, l, v, _v_offset, ldv, t, _t_offset, ldt, c, _c_offset, Ldc, work, _work_offset, ldwork);
  }

  public void slarzt(String direct, String storev, int n, int k, float[] v, int ldv, float[] tau, float[] t, int ldt) {
    f2j.slarzt(direct, storev, n, k, v, 0, ldv, tau, 0, t, 0, ldt);
  }

  public void slarzt(String direct, String storev, int n, int k, float[] v, int _v_offset, int ldv, float[] tau, int _tau_offset, float[] t, int _t_offset, int ldt) {
    f2j.slarzt(direct, storev, n, k, v, _v_offset, ldv, tau, _tau_offset, t, _t_offset, ldt);
  }

  public void slas2(float f, float g, float h, org.netlib.util.floatW ssmin, org.netlib.util.floatW ssmax) {
    f2j.slas2(f, g, h, ssmin, ssmax);
  }

  public void slascl(String type, int kl, int ku, float cfrom, float cto, int m, int n, float[] a, int lda, org.netlib.util.intW info) {
    f2j.slascl(type, kl, ku, cfrom, cto, m, n, a, 0, lda, info);
  }

  public void slascl(String type, int kl, int ku, float cfrom, float cto, int m, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    f2j.slascl(type, kl, ku, cfrom, cto, m, n, a, _a_offset, lda, info);
  }

  public void slasd0(int n, int sqre, float[] d, float[] e, float[] u, int ldu, float[] vt, int ldvt, int smlsiz, int[] iwork, float[] work, org.netlib.util.intW info) {
    f2j.slasd0(n, sqre, d, 0, e, 0, u, 0, ldu, vt, 0, ldvt, smlsiz, iwork, 0, work, 0, info);
  }

  public void slasd0(int n, int sqre, float[] d, int _d_offset, float[] e, int _e_offset, float[] u, int _u_offset, int ldu, float[] vt, int _vt_offset, int ldvt, int smlsiz, int[] iwork, int _iwork_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.slasd0(n, sqre, d, _d_offset, e, _e_offset, u, _u_offset, ldu, vt, _vt_offset, ldvt, smlsiz, iwork, _iwork_offset, work, _work_offset, info);
  }

  public void slasd1(int nl, int nr, int sqre, float[] d, org.netlib.util.floatW alpha, org.netlib.util.floatW beta, float[] u, int ldu, float[] vt, int ldvt, int[] idxq, int[] iwork, float[] work, org.netlib.util.intW info) {
    f2j.slasd1(nl, nr, sqre, d, 0, alpha, beta, u, 0, ldu, vt, 0, ldvt, idxq, 0, iwork, 0, work, 0, info);
  }

  public void slasd1(int nl, int nr, int sqre, float[] d, int _d_offset, org.netlib.util.floatW alpha, org.netlib.util.floatW beta, float[] u, int _u_offset, int ldu, float[] vt, int _vt_offset, int ldvt, int[] idxq, int _idxq_offset, int[] iwork, int _iwork_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.slasd1(nl, nr, sqre, d, _d_offset, alpha, beta, u, _u_offset, ldu, vt, _vt_offset, ldvt, idxq, _idxq_offset, iwork, _iwork_offset, work, _work_offset, info);
  }

  public void slasd2(int nl, int nr, int sqre, org.netlib.util.intW k, float[] d, float[] z, float alpha, float beta, float[] u, int ldu, float[] vt, int ldvt, float[] dsigma, float[] u2, int ldu2, float[] vt2, int ldvt2, int[] idxp, int[] idx, int[] idxc, int[] idxq, int[] coltyp, org.netlib.util.intW info) {
    f2j.slasd2(nl, nr, sqre, k, d, 0, z, 0, alpha, beta, u, 0, ldu, vt, 0, ldvt, dsigma, 0, u2, 0, ldu2, vt2, 0, ldvt2, idxp, 0, idx, 0, idxc, 0, idxq, 0, coltyp, 0, info);
  }

  public void slasd2(int nl, int nr, int sqre, org.netlib.util.intW k, float[] d, int _d_offset, float[] z, int _z_offset, float alpha, float beta, float[] u, int _u_offset, int ldu, float[] vt, int _vt_offset, int ldvt, float[] dsigma, int _dsigma_offset, float[] u2, int _u2_offset, int ldu2, float[] vt2, int _vt2_offset, int ldvt2, int[] idxp, int _idxp_offset, int[] idx, int _idx_offset, int[] idxc, int _idxc_offset, int[] idxq, int _idxq_offset, int[] coltyp, int _coltyp_offset, org.netlib.util.intW info) {
    f2j.slasd2(nl, nr, sqre, k, d, _d_offset, z, _z_offset, alpha, beta, u, _u_offset, ldu, vt, _vt_offset, ldvt, dsigma, _dsigma_offset, u2, _u2_offset, ldu2, vt2, _vt2_offset, ldvt2, idxp, _idxp_offset, idx, _idx_offset, idxc, _idxc_offset, idxq, _idxq_offset, coltyp, _coltyp_offset, info);
  }

  public void slasd3(int nl, int nr, int sqre, int k, float[] d, float[] q, int ldq, float[] dsigma, float[] u, int ldu, float[] u2, int ldu2, float[] vt, int ldvt, float[] vt2, int ldvt2, int[] idxc, int[] ctot, float[] z, org.netlib.util.intW info) {
    f2j.slasd3(nl, nr, sqre, k, d, 0, q, 0, ldq, dsigma, 0, u, 0, ldu, u2, 0, ldu2, vt, 0, ldvt, vt2, 0, ldvt2, idxc, 0, ctot, 0, z, 0, info);
  }

  public void slasd3(int nl, int nr, int sqre, int k, float[] d, int _d_offset, float[] q, int _q_offset, int ldq, float[] dsigma, int _dsigma_offset, float[] u, int _u_offset, int ldu, float[] u2, int _u2_offset, int ldu2, float[] vt, int _vt_offset, int ldvt, float[] vt2, int _vt2_offset, int ldvt2, int[] idxc, int _idxc_offset, int[] ctot, int _ctot_offset, float[] z, int _z_offset, org.netlib.util.intW info) {
    f2j.slasd3(nl, nr, sqre, k, d, _d_offset, q, _q_offset, ldq, dsigma, _dsigma_offset, u, _u_offset, ldu, u2, _u2_offset, ldu2, vt, _vt_offset, ldvt, vt2, _vt2_offset, ldvt2, idxc, _idxc_offset, ctot, _ctot_offset, z, _z_offset, info);
  }

  public void slasd4(int n, int i, float[] d, float[] z, float[] delta, float rho, org.netlib.util.floatW sigma, float[] work, org.netlib.util.intW info) {
    f2j.slasd4(n, i, d, 0, z, 0, delta, 0, rho, sigma, work, 0, info);
  }

  public void slasd4(int n, int i, float[] d, int _d_offset, float[] z, int _z_offset, float[] delta, int _delta_offset, float rho, org.netlib.util.floatW sigma, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.slasd4(n, i, d, _d_offset, z, _z_offset, delta, _delta_offset, rho, sigma, work, _work_offset, info);
  }

  public void slasd5(int i, float[] d, float[] z, float[] delta, float rho, org.netlib.util.floatW dsigma, float[] work) {
    f2j.slasd5(i, d, 0, z, 0, delta, 0, rho, dsigma, work, 0);
  }

  public void slasd5(int i, float[] d, int _d_offset, float[] z, int _z_offset, float[] delta, int _delta_offset, float rho, org.netlib.util.floatW dsigma, float[] work, int _work_offset) {
    f2j.slasd5(i, d, _d_offset, z, _z_offset, delta, _delta_offset, rho, dsigma, work, _work_offset);
  }

  public void slasd6(int icompq, int nl, int nr, int sqre, float[] d, float[] vf, float[] vl, org.netlib.util.floatW alpha, org.netlib.util.floatW beta, int[] idxq, int[] perm, org.netlib.util.intW givptr, int[] givcol, int ldgcol, float[] givnum, int ldgnum, float[] poles, float[] difl, float[] difr, float[] z, org.netlib.util.intW k, org.netlib.util.floatW c, org.netlib.util.floatW s, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.slasd6(icompq, nl, nr, sqre, d, 0, vf, 0, vl, 0, alpha, beta, idxq, 0, perm, 0, givptr, givcol, 0, ldgcol, givnum, 0, ldgnum, poles, 0, difl, 0, difr, 0, z, 0, k, c, s, work, 0, iwork, 0, info);
  }

  public void slasd6(int icompq, int nl, int nr, int sqre, float[] d, int _d_offset, float[] vf, int _vf_offset, float[] vl, int _vl_offset, org.netlib.util.floatW alpha, org.netlib.util.floatW beta, int[] idxq, int _idxq_offset, int[] perm, int _perm_offset, org.netlib.util.intW givptr, int[] givcol, int _givcol_offset, int ldgcol, float[] givnum, int _givnum_offset, int ldgnum, float[] poles, int _poles_offset, float[] difl, int _difl_offset, float[] difr, int _difr_offset, float[] z, int _z_offset, org.netlib.util.intW k, org.netlib.util.floatW c, org.netlib.util.floatW s, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.slasd6(icompq, nl, nr, sqre, d, _d_offset, vf, _vf_offset, vl, _vl_offset, alpha, beta, idxq, _idxq_offset, perm, _perm_offset, givptr, givcol, _givcol_offset, ldgcol, givnum, _givnum_offset, ldgnum, poles, _poles_offset, difl, _difl_offset, difr, _difr_offset, z, _z_offset, k, c, s, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void slasd7(int icompq, int nl, int nr, int sqre, org.netlib.util.intW k, float[] d, float[] z, float[] zw, float[] vf, float[] vfw, float[] vl, float[] vlw, float alpha, float beta, float[] dsigma, int[] idx, int[] idxp, int[] idxq, int[] perm, org.netlib.util.intW givptr, int[] givcol, int ldgcol, float[] givnum, int ldgnum, org.netlib.util.floatW c, org.netlib.util.floatW s, org.netlib.util.intW info) {
    f2j.slasd7(icompq, nl, nr, sqre, k, d, 0, z, 0, zw, 0, vf, 0, vfw, 0, vl, 0, vlw, 0, alpha, beta, dsigma, 0, idx, 0, idxp, 0, idxq, 0, perm, 0, givptr, givcol, 0, ldgcol, givnum, 0, ldgnum, c, s, info);
  }

  public void slasd7(int icompq, int nl, int nr, int sqre, org.netlib.util.intW k, float[] d, int _d_offset, float[] z, int _z_offset, float[] zw, int _zw_offset, float[] vf, int _vf_offset, float[] vfw, int _vfw_offset, float[] vl, int _vl_offset, float[] vlw, int _vlw_offset, float alpha, float beta, float[] dsigma, int _dsigma_offset, int[] idx, int _idx_offset, int[] idxp, int _idxp_offset, int[] idxq, int _idxq_offset, int[] perm, int _perm_offset, org.netlib.util.intW givptr, int[] givcol, int _givcol_offset, int ldgcol, float[] givnum, int _givnum_offset, int ldgnum, org.netlib.util.floatW c, org.netlib.util.floatW s, org.netlib.util.intW info) {
    f2j.slasd7(icompq, nl, nr, sqre, k, d, _d_offset, z, _z_offset, zw, _zw_offset, vf, _vf_offset, vfw, _vfw_offset, vl, _vl_offset, vlw, _vlw_offset, alpha, beta, dsigma, _dsigma_offset, idx, _idx_offset, idxp, _idxp_offset, idxq, _idxq_offset, perm, _perm_offset, givptr, givcol, _givcol_offset, ldgcol, givnum, _givnum_offset, ldgnum, c, s, info);
  }

  public void slasd8(int icompq, int k, float[] d, float[] z, float[] vf, float[] vl, float[] difl, float[] difr, int lddifr, float[] dsigma, float[] work, org.netlib.util.intW info) {
    f2j.slasd8(icompq, k, d, 0, z, 0, vf, 0, vl, 0, difl, 0, difr, 0, lddifr, dsigma, 0, work, 0, info);
  }

  public void slasd8(int icompq, int k, float[] d, int _d_offset, float[] z, int _z_offset, float[] vf, int _vf_offset, float[] vl, int _vl_offset, float[] difl, int _difl_offset, float[] difr, int _difr_offset, int lddifr, float[] dsigma, int _dsigma_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.slasd8(icompq, k, d, _d_offset, z, _z_offset, vf, _vf_offset, vl, _vl_offset, difl, _difl_offset, difr, _difr_offset, lddifr, dsigma, _dsigma_offset, work, _work_offset, info);
  }

  public void slasda(int icompq, int smlsiz, int n, int sqre, float[] d, float[] e, float[] u, int ldu, float[] vt, int[] k, float[] difl, float[] difr, float[] z, float[] poles, int[] givptr, int[] givcol, int ldgcol, int[] perm, float[] givnum, float[] c, float[] s, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.slasda(icompq, smlsiz, n, sqre, d, 0, e, 0, u, 0, ldu, vt, 0, k, 0, difl, 0, difr, 0, z, 0, poles, 0, givptr, 0, givcol, 0, ldgcol, perm, 0, givnum, 0, c, 0, s, 0, work, 0, iwork, 0, info);
  }

  public void slasda(int icompq, int smlsiz, int n, int sqre, float[] d, int _d_offset, float[] e, int _e_offset, float[] u, int _u_offset, int ldu, float[] vt, int _vt_offset, int[] k, int _k_offset, float[] difl, int _difl_offset, float[] difr, int _difr_offset, float[] z, int _z_offset, float[] poles, int _poles_offset, int[] givptr, int _givptr_offset, int[] givcol, int _givcol_offset, int ldgcol, int[] perm, int _perm_offset, float[] givnum, int _givnum_offset, float[] c, int _c_offset, float[] s, int _s_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.slasda(icompq, smlsiz, n, sqre, d, _d_offset, e, _e_offset, u, _u_offset, ldu, vt, _vt_offset, k, _k_offset, difl, _difl_offset, difr, _difr_offset, z, _z_offset, poles, _poles_offset, givptr, _givptr_offset, givcol, _givcol_offset, ldgcol, perm, _perm_offset, givnum, _givnum_offset, c, _c_offset, s, _s_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void slasdq(String uplo, int sqre, int n, int ncvt, int nru, int ncc, float[] d, float[] e, float[] vt, int ldvt, float[] u, int ldu, float[] c, int Ldc, float[] work, org.netlib.util.intW info) {
    f2j.slasdq(uplo, sqre, n, ncvt, nru, ncc, d, 0, e, 0, vt, 0, ldvt, u, 0, ldu, c, 0, Ldc, work, 0, info);
  }

  public void slasdq(String uplo, int sqre, int n, int ncvt, int nru, int ncc, float[] d, int _d_offset, float[] e, int _e_offset, float[] vt, int _vt_offset, int ldvt, float[] u, int _u_offset, int ldu, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.slasdq(uplo, sqre, n, ncvt, nru, ncc, d, _d_offset, e, _e_offset, vt, _vt_offset, ldvt, u, _u_offset, ldu, c, _c_offset, Ldc, work, _work_offset, info);
  }

  public void slasdt(int n, org.netlib.util.intW lvl, org.netlib.util.intW nd, int[] inode, int[] ndiml, int[] ndimr, int msub) {
    f2j.slasdt(n, lvl, nd, inode, 0, ndiml, 0, ndimr, 0, msub);
  }

  public void slasdt(int n, org.netlib.util.intW lvl, org.netlib.util.intW nd, int[] inode, int _inode_offset, int[] ndiml, int _ndiml_offset, int[] ndimr, int _ndimr_offset, int msub) {
    f2j.slasdt(n, lvl, nd, inode, _inode_offset, ndiml, _ndiml_offset, ndimr, _ndimr_offset, msub);
  }

  public void slaset(String uplo, int m, int n, float alpha, float beta, float[] a, int lda) {
    f2j.slaset(uplo, m, n, alpha, beta, a, 0, lda);
  }

  public void slaset(String uplo, int m, int n, float alpha, float beta, float[] a, int _a_offset, int lda) {
    f2j.slaset(uplo, m, n, alpha, beta, a, _a_offset, lda);
  }

  public void slasq1(int n, float[] d, float[] e, float[] work, org.netlib.util.intW info) {
    f2j.slasq1(n, d, 0, e, 0, work, 0, info);
  }

  public void slasq1(int n, float[] d, int _d_offset, float[] e, int _e_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.slasq1(n, d, _d_offset, e, _e_offset, work, _work_offset, info);
  }

  public void slasq2(int n, float[] z, org.netlib.util.intW info) {
    f2j.slasq2(n, z, 0, info);
  }

  public void slasq2(int n, float[] z, int _z_offset, org.netlib.util.intW info) {
    f2j.slasq2(n, z, _z_offset, info);
  }

  public void slasq3(int i0, org.netlib.util.intW n0, float[] z, int pp, org.netlib.util.floatW dmin, org.netlib.util.floatW sigma, org.netlib.util.floatW desig, org.netlib.util.floatW qmax, org.netlib.util.intW nfail, org.netlib.util.intW iter, org.netlib.util.intW ndiv, boolean ieee) {
    f2j.slasq3(i0, n0, z, 0, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee);
  }

  public void slasq3(int i0, org.netlib.util.intW n0, float[] z, int _z_offset, int pp, org.netlib.util.floatW dmin, org.netlib.util.floatW sigma, org.netlib.util.floatW desig, org.netlib.util.floatW qmax, org.netlib.util.intW nfail, org.netlib.util.intW iter, org.netlib.util.intW ndiv, boolean ieee) {
    f2j.slasq3(i0, n0, z, _z_offset, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee);
  }

  public void slasq4(int i0, int n0, float[] z, int pp, int n0in, float dmin, float dmin1, float dmin2, float dn, float dn1, float dn2, org.netlib.util.floatW tau, org.netlib.util.intW ttype) {
    f2j.slasq4(i0, n0, z, 0, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype);
  }

  public void slasq4(int i0, int n0, float[] z, int _z_offset, int pp, int n0in, float dmin, float dmin1, float dmin2, float dn, float dn1, float dn2, org.netlib.util.floatW tau, org.netlib.util.intW ttype) {
    f2j.slasq4(i0, n0, z, _z_offset, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype);
  }

  public void slasq5(int i0, int n0, float[] z, int pp, float tau, org.netlib.util.floatW dmin, org.netlib.util.floatW dmin1, org.netlib.util.floatW dmin2, org.netlib.util.floatW dn, org.netlib.util.floatW dnm1, org.netlib.util.floatW dnm2, boolean ieee) {
    f2j.slasq5(i0, n0, z, 0, pp, tau, dmin, dmin1, dmin2, dn, dnm1, dnm2, ieee);
  }

  public void slasq5(int i0, int n0, float[] z, int _z_offset, int pp, float tau, org.netlib.util.floatW dmin, org.netlib.util.floatW dmin1, org.netlib.util.floatW dmin2, org.netlib.util.floatW dn, org.netlib.util.floatW dnm1, org.netlib.util.floatW dnm2, boolean ieee) {
    f2j.slasq5(i0, n0, z, _z_offset, pp, tau, dmin, dmin1, dmin2, dn, dnm1, dnm2, ieee);
  }

  public void slasq6(int i0, int n0, float[] z, int pp, org.netlib.util.floatW dmin, org.netlib.util.floatW dmin1, org.netlib.util.floatW dmin2, org.netlib.util.floatW dn, org.netlib.util.floatW dnm1, org.netlib.util.floatW dnm2) {
    f2j.slasq6(i0, n0, z, 0, pp, dmin, dmin1, dmin2, dn, dnm1, dnm2);
  }

  public void slasq6(int i0, int n0, float[] z, int _z_offset, int pp, org.netlib.util.floatW dmin, org.netlib.util.floatW dmin1, org.netlib.util.floatW dmin2, org.netlib.util.floatW dn, org.netlib.util.floatW dnm1, org.netlib.util.floatW dnm2) {
    f2j.slasq6(i0, n0, z, _z_offset, pp, dmin, dmin1, dmin2, dn, dnm1, dnm2);
  }

  public void slasr(String side, String pivot, String direct, int m, int n, float[] c, float[] s, float[] a, int lda) {
    f2j.slasr(side, pivot, direct, m, n, c, 0, s, 0, a, 0, lda);
  }

  public void slasr(String side, String pivot, String direct, int m, int n, float[] c, int _c_offset, float[] s, int _s_offset, float[] a, int _a_offset, int lda) {
    f2j.slasr(side, pivot, direct, m, n, c, _c_offset, s, _s_offset, a, _a_offset, lda);
  }

  public void slasrt(String id, int n, float[] d, org.netlib.util.intW info) {
    f2j.slasrt(id, n, d, 0, info);
  }

  public void slasrt(String id, int n, float[] d, int _d_offset, org.netlib.util.intW info) {
    f2j.slasrt(id, n, d, _d_offset, info);
  }

  public void slassq(int n, float[] x, int incx, org.netlib.util.floatW scale, org.netlib.util.floatW sumsq) {
    f2j.slassq(n, x, 0, incx, scale, sumsq);
  }

  public void slassq(int n, float[] x, int _x_offset, int incx, org.netlib.util.floatW scale, org.netlib.util.floatW sumsq) {
    f2j.slassq(n, x, _x_offset, incx, scale, sumsq);
  }

  public void slasv2(float f, float g, float h, org.netlib.util.floatW ssmin, org.netlib.util.floatW ssmax, org.netlib.util.floatW snr, org.netlib.util.floatW csr, org.netlib.util.floatW snl, org.netlib.util.floatW csl) {
    f2j.slasv2(f, g, h, ssmin, ssmax, snr, csr, snl, csl);
  }

  public void slaswp(int n, float[] a, int lda, int k1, int k2, int[] ipiv, int incx) {
    f2j.slaswp(n, a, 0, lda, k1, k2, ipiv, 0, incx);
  }

  public void slaswp(int n, float[] a, int _a_offset, int lda, int k1, int k2, int[] ipiv, int _ipiv_offset, int incx) {
    f2j.slaswp(n, a, _a_offset, lda, k1, k2, ipiv, _ipiv_offset, incx);
  }

  public void slasy2(boolean ltranl, boolean ltranr, int isgn, int n1, int n2, float[] tl, int ldtl, float[] tr, int ldtr, float[] b, int ldb, org.netlib.util.floatW scale, float[] x, int ldx, org.netlib.util.floatW xnorm, org.netlib.util.intW info) {
    f2j.slasy2(ltranl, ltranr, isgn, n1, n2, tl, 0, ldtl, tr, 0, ldtr, b, 0, ldb, scale, x, 0, ldx, xnorm, info);
  }

  public void slasy2(boolean ltranl, boolean ltranr, int isgn, int n1, int n2, float[] tl, int _tl_offset, int ldtl, float[] tr, int _tr_offset, int ldtr, float[] b, int _b_offset, int ldb, org.netlib.util.floatW scale, float[] x, int _x_offset, int ldx, org.netlib.util.floatW xnorm, org.netlib.util.intW info) {
    f2j.slasy2(ltranl, ltranr, isgn, n1, n2, tl, _tl_offset, ldtl, tr, _tr_offset, ldtr, b, _b_offset, ldb, scale, x, _x_offset, ldx, xnorm, info);
  }

  public void slasyf(String uplo, int n, int nb, org.netlib.util.intW kb, float[] a, int lda, int[] ipiv, float[] w, int ldw, org.netlib.util.intW info) {
    f2j.slasyf(uplo, n, nb, kb, a, 0, lda, ipiv, 0, w, 0, ldw, info);
  }

  public void slasyf(String uplo, int n, int nb, org.netlib.util.intW kb, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, float[] w, int _w_offset, int ldw, org.netlib.util.intW info) {
    f2j.slasyf(uplo, n, nb, kb, a, _a_offset, lda, ipiv, _ipiv_offset, w, _w_offset, ldw, info);
  }

  public void slatbs(String uplo, String trans, String diag, String normin, int n, int kd, float[] ab, int ldab, float[] x, org.netlib.util.floatW scale, float[] cnorm, org.netlib.util.intW info) {
    f2j.slatbs(uplo, trans, diag, normin, n, kd, ab, 0, ldab, x, 0, scale, cnorm, 0, info);
  }

  public void slatbs(String uplo, String trans, String diag, String normin, int n, int kd, float[] ab, int _ab_offset, int ldab, float[] x, int _x_offset, org.netlib.util.floatW scale, float[] cnorm, int _cnorm_offset, org.netlib.util.intW info) {
    f2j.slatbs(uplo, trans, diag, normin, n, kd, ab, _ab_offset, ldab, x, _x_offset, scale, cnorm, _cnorm_offset, info);
  }

  public void slatdf(int ijob, int n, float[] z, int ldz, float[] rhs, org.netlib.util.floatW rdsum, org.netlib.util.floatW rdscal, int[] ipiv, int[] jpiv) {
    f2j.slatdf(ijob, n, z, 0, ldz, rhs, 0, rdsum, rdscal, ipiv, 0, jpiv, 0);
  }

  public void slatdf(int ijob, int n, float[] z, int _z_offset, int ldz, float[] rhs, int _rhs_offset, org.netlib.util.floatW rdsum, org.netlib.util.floatW rdscal, int[] ipiv, int _ipiv_offset, int[] jpiv, int _jpiv_offset) {
    f2j.slatdf(ijob, n, z, _z_offset, ldz, rhs, _rhs_offset, rdsum, rdscal, ipiv, _ipiv_offset, jpiv, _jpiv_offset);
  }

  public void slatps(String uplo, String trans, String diag, String normin, int n, float[] ap, float[] x, org.netlib.util.floatW scale, float[] cnorm, org.netlib.util.intW info) {
    f2j.slatps(uplo, trans, diag, normin, n, ap, 0, x, 0, scale, cnorm, 0, info);
  }

  public void slatps(String uplo, String trans, String diag, String normin, int n, float[] ap, int _ap_offset, float[] x, int _x_offset, org.netlib.util.floatW scale, float[] cnorm, int _cnorm_offset, org.netlib.util.intW info) {
    f2j.slatps(uplo, trans, diag, normin, n, ap, _ap_offset, x, _x_offset, scale, cnorm, _cnorm_offset, info);
  }

  public void slatrd(String uplo, int n, int nb, float[] a, int lda, float[] e, float[] tau, float[] w, int ldw) {
    f2j.slatrd(uplo, n, nb, a, 0, lda, e, 0, tau, 0, w, 0, ldw);
  }

  public void slatrd(String uplo, int n, int nb, float[] a, int _a_offset, int lda, float[] e, int _e_offset, float[] tau, int _tau_offset, float[] w, int _w_offset, int ldw) {
    f2j.slatrd(uplo, n, nb, a, _a_offset, lda, e, _e_offset, tau, _tau_offset, w, _w_offset, ldw);
  }

  public void slatrs(String uplo, String trans, String diag, String normin, int n, float[] a, int lda, float[] x, org.netlib.util.floatW scale, float[] cnorm, org.netlib.util.intW info) {
    f2j.slatrs(uplo, trans, diag, normin, n, a, 0, lda, x, 0, scale, cnorm, 0, info);
  }

  public void slatrs(String uplo, String trans, String diag, String normin, int n, float[] a, int _a_offset, int lda, float[] x, int _x_offset, org.netlib.util.floatW scale, float[] cnorm, int _cnorm_offset, org.netlib.util.intW info) {
    f2j.slatrs(uplo, trans, diag, normin, n, a, _a_offset, lda, x, _x_offset, scale, cnorm, _cnorm_offset, info);
  }

  public void slatrz(int m, int n, int l, float[] a, int lda, float[] tau, float[] work) {
    f2j.slatrz(m, n, l, a, 0, lda, tau, 0, work, 0);
  }

  public void slatrz(int m, int n, int l, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset) {
    f2j.slatrz(m, n, l, a, _a_offset, lda, tau, _tau_offset, work, _work_offset);
  }

  public void slatzm(String side, int m, int n, float[] v, int incv, float tau, float[] c1, float[] c2, int Ldc, float[] work) {
    f2j.slatzm(side, m, n, v, 0, incv, tau, c1, 0, c2, 0, Ldc, work, 0);
  }

  public void slatzm(String side, int m, int n, float[] v, int _v_offset, int incv, float tau, float[] c1, int _c1_offset, float[] c2, int _c2_offset, int Ldc, float[] work, int _work_offset) {
    f2j.slatzm(side, m, n, v, _v_offset, incv, tau, c1, _c1_offset, c2, _c2_offset, Ldc, work, _work_offset);
  }

  public void slauu2(String uplo, int n, float[] a, int lda, org.netlib.util.intW info) {
    f2j.slauu2(uplo, n, a, 0, lda, info);
  }

  public void slauu2(String uplo, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    f2j.slauu2(uplo, n, a, _a_offset, lda, info);
  }

  public void slauum(String uplo, int n, float[] a, int lda, org.netlib.util.intW info) {
    f2j.slauum(uplo, n, a, 0, lda, info);
  }

  public void slauum(String uplo, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    f2j.slauum(uplo, n, a, _a_offset, lda, info);
  }

  public void slazq3(int i0, org.netlib.util.intW n0, float[] z, int pp, org.netlib.util.floatW dmin, org.netlib.util.floatW sigma, org.netlib.util.floatW desig, org.netlib.util.floatW qmax, org.netlib.util.intW nfail, org.netlib.util.intW iter, org.netlib.util.intW ndiv, boolean ieee, org.netlib.util.intW ttype, org.netlib.util.floatW dmin1, org.netlib.util.floatW dmin2, org.netlib.util.floatW dn, org.netlib.util.floatW dn1, org.netlib.util.floatW dn2, org.netlib.util.floatW tau) {
    f2j.slazq3(i0, n0, z, 0, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee, ttype, dmin1, dmin2, dn, dn1, dn2, tau);
  }

  public void slazq3(int i0, org.netlib.util.intW n0, float[] z, int _z_offset, int pp, org.netlib.util.floatW dmin, org.netlib.util.floatW sigma, org.netlib.util.floatW desig, org.netlib.util.floatW qmax, org.netlib.util.intW nfail, org.netlib.util.intW iter, org.netlib.util.intW ndiv, boolean ieee, org.netlib.util.intW ttype, org.netlib.util.floatW dmin1, org.netlib.util.floatW dmin2, org.netlib.util.floatW dn, org.netlib.util.floatW dn1, org.netlib.util.floatW dn2, org.netlib.util.floatW tau) {
    f2j.slazq3(i0, n0, z, _z_offset, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee, ttype, dmin1, dmin2, dn, dn1, dn2, tau);
  }

  public void slazq4(int i0, int n0, float[] z, int pp, int n0in, float dmin, float dmin1, float dmin2, float dn, float dn1, float dn2, org.netlib.util.floatW tau, org.netlib.util.intW ttype, org.netlib.util.floatW g) {
    f2j.slazq4(i0, n0, z, 0, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype, g);
  }

  public void slazq4(int i0, int n0, float[] z, int _z_offset, int pp, int n0in, float dmin, float dmin1, float dmin2, float dn, float dn1, float dn2, org.netlib.util.floatW tau, org.netlib.util.intW ttype, org.netlib.util.floatW g) {
    f2j.slazq4(i0, n0, z, _z_offset, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype, g);
  }

  public void sopgtr(String uplo, int n, float[] ap, float[] tau, float[] q, int ldq, float[] work, org.netlib.util.intW info) {
    f2j.sopgtr(uplo, n, ap, 0, tau, 0, q, 0, ldq, work, 0, info);
  }

  public void sopgtr(String uplo, int n, float[] ap, int _ap_offset, float[] tau, int _tau_offset, float[] q, int _q_offset, int ldq, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sopgtr(uplo, n, ap, _ap_offset, tau, _tau_offset, q, _q_offset, ldq, work, _work_offset, info);
  }

  public void sopmtr(String side, String uplo, String trans, int m, int n, float[] ap, float[] tau, float[] c, int Ldc, float[] work, org.netlib.util.intW info) {
    f2j.sopmtr(side, uplo, trans, m, n, ap, 0, tau, 0, c, 0, Ldc, work, 0, info);
  }

  public void sopmtr(String side, String uplo, String trans, int m, int n, float[] ap, int _ap_offset, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sopmtr(side, uplo, trans, m, n, ap, _ap_offset, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  public void sorg2l(int m, int n, int k, float[] a, int lda, float[] tau, float[] work, org.netlib.util.intW info) {
    f2j.sorg2l(m, n, k, a, 0, lda, tau, 0, work, 0, info);
  }

  public void sorg2l(int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sorg2l(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  public void sorg2r(int m, int n, int k, float[] a, int lda, float[] tau, float[] work, org.netlib.util.intW info) {
    f2j.sorg2r(m, n, k, a, 0, lda, tau, 0, work, 0, info);
  }

  public void sorg2r(int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sorg2r(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  public void sorgbr(String vect, int m, int n, int k, float[] a, int lda, float[] tau, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sorgbr(vect, m, n, k, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void sorgbr(String vect, int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sorgbr(vect, m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void sorghr(int n, int ilo, int ihi, float[] a, int lda, float[] tau, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sorghr(n, ilo, ihi, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void sorghr(int n, int ilo, int ihi, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sorghr(n, ilo, ihi, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void sorgl2(int m, int n, int k, float[] a, int lda, float[] tau, float[] work, org.netlib.util.intW info) {
    f2j.sorgl2(m, n, k, a, 0, lda, tau, 0, work, 0, info);
  }

  public void sorgl2(int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sorgl2(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  public void sorglq(int m, int n, int k, float[] a, int lda, float[] tau, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sorglq(m, n, k, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void sorglq(int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sorglq(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void sorgql(int m, int n, int k, float[] a, int lda, float[] tau, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sorgql(m, n, k, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void sorgql(int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sorgql(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void sorgqr(int m, int n, int k, float[] a, int lda, float[] tau, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sorgqr(m, n, k, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void sorgqr(int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sorgqr(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void sorgr2(int m, int n, int k, float[] a, int lda, float[] tau, float[] work, org.netlib.util.intW info) {
    f2j.sorgr2(m, n, k, a, 0, lda, tau, 0, work, 0, info);
  }

  public void sorgr2(int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sorgr2(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, info);
  }

  public void sorgrq(int m, int n, int k, float[] a, int lda, float[] tau, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sorgrq(m, n, k, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void sorgrq(int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sorgrq(m, n, k, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void sorgtr(String uplo, int n, float[] a, int lda, float[] tau, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sorgtr(uplo, n, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void sorgtr(String uplo, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sorgtr(uplo, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void sorm2l(String side, String trans, int m, int n, int k, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, org.netlib.util.intW info) {
    f2j.sorm2l(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, info);
  }

  public void sorm2l(String side, String trans, int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sorm2l(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  public void sorm2r(String side, String trans, int m, int n, int k, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, org.netlib.util.intW info) {
    f2j.sorm2r(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, info);
  }

  public void sorm2r(String side, String trans, int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sorm2r(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  public void sormbr(String vect, String side, String trans, int m, int n, int k, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sormbr(vect, side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
  }

  public void sormbr(String vect, String side, String trans, int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sormbr(vect, side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  public void sormhr(String side, String trans, int m, int n, int ilo, int ihi, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sormhr(side, trans, m, n, ilo, ihi, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
  }

  public void sormhr(String side, String trans, int m, int n, int ilo, int ihi, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sormhr(side, trans, m, n, ilo, ihi, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  public void sorml2(String side, String trans, int m, int n, int k, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, org.netlib.util.intW info) {
    f2j.sorml2(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, info);
  }

  public void sorml2(String side, String trans, int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sorml2(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  public void sormlq(String side, String trans, int m, int n, int k, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sormlq(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
  }

  public void sormlq(String side, String trans, int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sormlq(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  public void sormql(String side, String trans, int m, int n, int k, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sormql(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
  }

  public void sormql(String side, String trans, int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sormql(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  public void sormqr(String side, String trans, int m, int n, int k, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sormqr(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
  }

  public void sormqr(String side, String trans, int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sormqr(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  public void sormr2(String side, String trans, int m, int n, int k, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, org.netlib.util.intW info) {
    f2j.sormr2(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, info);
  }

  public void sormr2(String side, String trans, int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sormr2(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  public void sormr3(String side, String trans, int m, int n, int k, int l, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, org.netlib.util.intW info) {
    f2j.sormr3(side, trans, m, n, k, l, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, info);
  }

  public void sormr3(String side, String trans, int m, int n, int k, int l, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sormr3(side, trans, m, n, k, l, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, info);
  }

  public void sormrq(String side, String trans, int m, int n, int k, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sormrq(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
  }

  public void sormrq(String side, String trans, int m, int n, int k, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sormrq(side, trans, m, n, k, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  public void sormrz(String side, String trans, int m, int n, int k, int l, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sormrz(side, trans, m, n, k, l, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
  }

  public void sormrz(String side, String trans, int m, int n, int k, int l, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sormrz(side, trans, m, n, k, l, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  public void sormtr(String side, String uplo, String trans, int m, int n, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.sormtr(side, uplo, trans, m, n, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
  }

  public void sormtr(String side, String uplo, String trans, int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] c, int _c_offset, int Ldc, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.sormtr(side, uplo, trans, m, n, a, _a_offset, lda, tau, _tau_offset, c, _c_offset, Ldc, work, _work_offset, lwork, info);
  }

  public void spbcon(String uplo, int n, int kd, float[] ab, int ldab, float anorm, org.netlib.util.floatW rcond, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.spbcon(uplo, n, kd, ab, 0, ldab, anorm, rcond, work, 0, iwork, 0, info);
  }

  public void spbcon(String uplo, int n, int kd, float[] ab, int _ab_offset, int ldab, float anorm, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.spbcon(uplo, n, kd, ab, _ab_offset, ldab, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void spbequ(String uplo, int n, int kd, float[] ab, int ldab, float[] s, org.netlib.util.floatW scond, org.netlib.util.floatW amax, org.netlib.util.intW info) {
    f2j.spbequ(uplo, n, kd, ab, 0, ldab, s, 0, scond, amax, info);
  }

  public void spbequ(String uplo, int n, int kd, float[] ab, int _ab_offset, int ldab, float[] s, int _s_offset, org.netlib.util.floatW scond, org.netlib.util.floatW amax, org.netlib.util.intW info) {
    f2j.spbequ(uplo, n, kd, ab, _ab_offset, ldab, s, _s_offset, scond, amax, info);
  }

  public void spbrfs(String uplo, int n, int kd, int nrhs, float[] ab, int ldab, float[] afb, int ldafb, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.spbrfs(uplo, n, kd, nrhs, ab, 0, ldab, afb, 0, ldafb, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void spbrfs(String uplo, int n, int kd, int nrhs, float[] ab, int _ab_offset, int ldab, float[] afb, int _afb_offset, int ldafb, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.spbrfs(uplo, n, kd, nrhs, ab, _ab_offset, ldab, afb, _afb_offset, ldafb, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void spbstf(String uplo, int n, int kd, float[] ab, int ldab, org.netlib.util.intW info) {
    f2j.spbstf(uplo, n, kd, ab, 0, ldab, info);
  }

  public void spbstf(String uplo, int n, int kd, float[] ab, int _ab_offset, int ldab, org.netlib.util.intW info) {
    f2j.spbstf(uplo, n, kd, ab, _ab_offset, ldab, info);
  }

  public void spbsv(String uplo, int n, int kd, int nrhs, float[] ab, int ldab, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.spbsv(uplo, n, kd, nrhs, ab, 0, ldab, b, 0, ldb, info);
  }

  public void spbsv(String uplo, int n, int kd, int nrhs, float[] ab, int _ab_offset, int ldab, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.spbsv(uplo, n, kd, nrhs, ab, _ab_offset, ldab, b, _b_offset, ldb, info);
  }

  public void spbsvx(String fact, String uplo, int n, int kd, int nrhs, float[] ab, int ldab, float[] afb, int ldafb, org.netlib.util.StringW equed, float[] s, float[] b, int ldb, float[] x, int ldx, org.netlib.util.floatW rcond, float[] ferr, float[] berr, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.spbsvx(fact, uplo, n, kd, nrhs, ab, 0, ldab, afb, 0, ldafb, equed, s, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void spbsvx(String fact, String uplo, int n, int kd, int nrhs, float[] ab, int _ab_offset, int ldab, float[] afb, int _afb_offset, int ldafb, org.netlib.util.StringW equed, float[] s, int _s_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, org.netlib.util.floatW rcond, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.spbsvx(fact, uplo, n, kd, nrhs, ab, _ab_offset, ldab, afb, _afb_offset, ldafb, equed, s, _s_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void spbtf2(String uplo, int n, int kd, float[] ab, int ldab, org.netlib.util.intW info) {
    f2j.spbtf2(uplo, n, kd, ab, 0, ldab, info);
  }

  public void spbtf2(String uplo, int n, int kd, float[] ab, int _ab_offset, int ldab, org.netlib.util.intW info) {
    f2j.spbtf2(uplo, n, kd, ab, _ab_offset, ldab, info);
  }

  public void spbtrf(String uplo, int n, int kd, float[] ab, int ldab, org.netlib.util.intW info) {
    f2j.spbtrf(uplo, n, kd, ab, 0, ldab, info);
  }

  public void spbtrf(String uplo, int n, int kd, float[] ab, int _ab_offset, int ldab, org.netlib.util.intW info) {
    f2j.spbtrf(uplo, n, kd, ab, _ab_offset, ldab, info);
  }

  public void spbtrs(String uplo, int n, int kd, int nrhs, float[] ab, int ldab, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.spbtrs(uplo, n, kd, nrhs, ab, 0, ldab, b, 0, ldb, info);
  }

  public void spbtrs(String uplo, int n, int kd, int nrhs, float[] ab, int _ab_offset, int ldab, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.spbtrs(uplo, n, kd, nrhs, ab, _ab_offset, ldab, b, _b_offset, ldb, info);
  }

  public void spocon(String uplo, int n, float[] a, int lda, float anorm, org.netlib.util.floatW rcond, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.spocon(uplo, n, a, 0, lda, anorm, rcond, work, 0, iwork, 0, info);
  }

  public void spocon(String uplo, int n, float[] a, int _a_offset, int lda, float anorm, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.spocon(uplo, n, a, _a_offset, lda, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void spoequ(int n, float[] a, int lda, float[] s, org.netlib.util.floatW scond, org.netlib.util.floatW amax, org.netlib.util.intW info) {
    f2j.spoequ(n, a, 0, lda, s, 0, scond, amax, info);
  }

  public void spoequ(int n, float[] a, int _a_offset, int lda, float[] s, int _s_offset, org.netlib.util.floatW scond, org.netlib.util.floatW amax, org.netlib.util.intW info) {
    f2j.spoequ(n, a, _a_offset, lda, s, _s_offset, scond, amax, info);
  }

  public void sporfs(String uplo, int n, int nrhs, float[] a, int lda, float[] af, int ldaf, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.sporfs(uplo, n, nrhs, a, 0, lda, af, 0, ldaf, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void sporfs(String uplo, int n, int nrhs, float[] a, int _a_offset, int lda, float[] af, int _af_offset, int ldaf, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sporfs(uplo, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void sposv(String uplo, int n, int nrhs, float[] a, int lda, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.sposv(uplo, n, nrhs, a, 0, lda, b, 0, ldb, info);
  }

  public void sposv(String uplo, int n, int nrhs, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.sposv(uplo, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, info);
  }

  public void sposvx(String fact, String uplo, int n, int nrhs, float[] a, int lda, float[] af, int ldaf, org.netlib.util.StringW equed, float[] s, float[] b, int ldb, float[] x, int ldx, org.netlib.util.floatW rcond, float[] ferr, float[] berr, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.sposvx(fact, uplo, n, nrhs, a, 0, lda, af, 0, ldaf, equed, s, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void sposvx(String fact, String uplo, int n, int nrhs, float[] a, int _a_offset, int lda, float[] af, int _af_offset, int ldaf, org.netlib.util.StringW equed, float[] s, int _s_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, org.netlib.util.floatW rcond, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sposvx(fact, uplo, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, equed, s, _s_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void spotf2(String uplo, int n, float[] a, int lda, org.netlib.util.intW info) {
    f2j.spotf2(uplo, n, a, 0, lda, info);
  }

  public void spotf2(String uplo, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    f2j.spotf2(uplo, n, a, _a_offset, lda, info);
  }

  public void spotrf(String uplo, int n, float[] a, int lda, org.netlib.util.intW info) {
    f2j.spotrf(uplo, n, a, 0, lda, info);
  }

  public void spotrf(String uplo, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    f2j.spotrf(uplo, n, a, _a_offset, lda, info);
  }

  public void spotri(String uplo, int n, float[] a, int lda, org.netlib.util.intW info) {
    f2j.spotri(uplo, n, a, 0, lda, info);
  }

  public void spotri(String uplo, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    f2j.spotri(uplo, n, a, _a_offset, lda, info);
  }

  public void spotrs(String uplo, int n, int nrhs, float[] a, int lda, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.spotrs(uplo, n, nrhs, a, 0, lda, b, 0, ldb, info);
  }

  public void spotrs(String uplo, int n, int nrhs, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.spotrs(uplo, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, info);
  }

  public void sppcon(String uplo, int n, float[] ap, float anorm, org.netlib.util.floatW rcond, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.sppcon(uplo, n, ap, 0, anorm, rcond, work, 0, iwork, 0, info);
  }

  public void sppcon(String uplo, int n, float[] ap, int _ap_offset, float anorm, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sppcon(uplo, n, ap, _ap_offset, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void sppequ(String uplo, int n, float[] ap, float[] s, org.netlib.util.floatW scond, org.netlib.util.floatW amax, org.netlib.util.intW info) {
    f2j.sppequ(uplo, n, ap, 0, s, 0, scond, amax, info);
  }

  public void sppequ(String uplo, int n, float[] ap, int _ap_offset, float[] s, int _s_offset, org.netlib.util.floatW scond, org.netlib.util.floatW amax, org.netlib.util.intW info) {
    f2j.sppequ(uplo, n, ap, _ap_offset, s, _s_offset, scond, amax, info);
  }

  public void spprfs(String uplo, int n, int nrhs, float[] ap, float[] afp, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.spprfs(uplo, n, nrhs, ap, 0, afp, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void spprfs(String uplo, int n, int nrhs, float[] ap, int _ap_offset, float[] afp, int _afp_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.spprfs(uplo, n, nrhs, ap, _ap_offset, afp, _afp_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void sppsv(String uplo, int n, int nrhs, float[] ap, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.sppsv(uplo, n, nrhs, ap, 0, b, 0, ldb, info);
  }

  public void sppsv(String uplo, int n, int nrhs, float[] ap, int _ap_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.sppsv(uplo, n, nrhs, ap, _ap_offset, b, _b_offset, ldb, info);
  }

  public void sppsvx(String fact, String uplo, int n, int nrhs, float[] ap, float[] afp, org.netlib.util.StringW equed, float[] s, float[] b, int ldb, float[] x, int ldx, org.netlib.util.floatW rcond, float[] ferr, float[] berr, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.sppsvx(fact, uplo, n, nrhs, ap, 0, afp, 0, equed, s, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void sppsvx(String fact, String uplo, int n, int nrhs, float[] ap, int _ap_offset, float[] afp, int _afp_offset, org.netlib.util.StringW equed, float[] s, int _s_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, org.netlib.util.floatW rcond, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sppsvx(fact, uplo, n, nrhs, ap, _ap_offset, afp, _afp_offset, equed, s, _s_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void spptrf(String uplo, int n, float[] ap, org.netlib.util.intW info) {
    f2j.spptrf(uplo, n, ap, 0, info);
  }

  public void spptrf(String uplo, int n, float[] ap, int _ap_offset, org.netlib.util.intW info) {
    f2j.spptrf(uplo, n, ap, _ap_offset, info);
  }

  public void spptri(String uplo, int n, float[] ap, org.netlib.util.intW info) {
    f2j.spptri(uplo, n, ap, 0, info);
  }

  public void spptri(String uplo, int n, float[] ap, int _ap_offset, org.netlib.util.intW info) {
    f2j.spptri(uplo, n, ap, _ap_offset, info);
  }

  public void spptrs(String uplo, int n, int nrhs, float[] ap, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.spptrs(uplo, n, nrhs, ap, 0, b, 0, ldb, info);
  }

  public void spptrs(String uplo, int n, int nrhs, float[] ap, int _ap_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.spptrs(uplo, n, nrhs, ap, _ap_offset, b, _b_offset, ldb, info);
  }

  public void sptcon(int n, float[] d, float[] e, float anorm, org.netlib.util.floatW rcond, float[] work, org.netlib.util.intW info) {
    f2j.sptcon(n, d, 0, e, 0, anorm, rcond, work, 0, info);
  }

  public void sptcon(int n, float[] d, int _d_offset, float[] e, int _e_offset, float anorm, org.netlib.util.floatW rcond, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sptcon(n, d, _d_offset, e, _e_offset, anorm, rcond, work, _work_offset, info);
  }

  public void spteqr(String compz, int n, float[] d, float[] e, float[] z, int ldz, float[] work, org.netlib.util.intW info) {
    f2j.spteqr(compz, n, d, 0, e, 0, z, 0, ldz, work, 0, info);
  }

  public void spteqr(String compz, int n, float[] d, int _d_offset, float[] e, int _e_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.spteqr(compz, n, d, _d_offset, e, _e_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  public void sptrfs(int n, int nrhs, float[] d, float[] e, float[] df, float[] ef, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, org.netlib.util.intW info) {
    f2j.sptrfs(n, nrhs, d, 0, e, 0, df, 0, ef, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, info);
  }

  public void sptrfs(int n, int nrhs, float[] d, int _d_offset, float[] e, int _e_offset, float[] df, int _df_offset, float[] ef, int _ef_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sptrfs(n, nrhs, d, _d_offset, e, _e_offset, df, _df_offset, ef, _ef_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, info);
  }

  public void sptsv(int n, int nrhs, float[] d, float[] e, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.sptsv(n, nrhs, d, 0, e, 0, b, 0, ldb, info);
  }

  public void sptsv(int n, int nrhs, float[] d, int _d_offset, float[] e, int _e_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.sptsv(n, nrhs, d, _d_offset, e, _e_offset, b, _b_offset, ldb, info);
  }

  public void sptsvx(String fact, int n, int nrhs, float[] d, float[] e, float[] df, float[] ef, float[] b, int ldb, float[] x, int ldx, org.netlib.util.floatW rcond, float[] ferr, float[] berr, float[] work, org.netlib.util.intW info) {
    f2j.sptsvx(fact, n, nrhs, d, 0, e, 0, df, 0, ef, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, info);
  }

  public void sptsvx(String fact, int n, int nrhs, float[] d, int _d_offset, float[] e, int _e_offset, float[] df, int _df_offset, float[] ef, int _ef_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, org.netlib.util.floatW rcond, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sptsvx(fact, n, nrhs, d, _d_offset, e, _e_offset, df, _df_offset, ef, _ef_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, info);
  }

  public void spttrf(int n, float[] d, float[] e, org.netlib.util.intW info) {
    f2j.spttrf(n, d, 0, e, 0, info);
  }

  public void spttrf(int n, float[] d, int _d_offset, float[] e, int _e_offset, org.netlib.util.intW info) {
    f2j.spttrf(n, d, _d_offset, e, _e_offset, info);
  }

  public void spttrs(int n, int nrhs, float[] d, float[] e, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.spttrs(n, nrhs, d, 0, e, 0, b, 0, ldb, info);
  }

  public void spttrs(int n, int nrhs, float[] d, int _d_offset, float[] e, int _e_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.spttrs(n, nrhs, d, _d_offset, e, _e_offset, b, _b_offset, ldb, info);
  }

  public void sptts2(int n, int nrhs, float[] d, float[] e, float[] b, int ldb) {
    f2j.sptts2(n, nrhs, d, 0, e, 0, b, 0, ldb);
  }

  public void sptts2(int n, int nrhs, float[] d, int _d_offset, float[] e, int _e_offset, float[] b, int _b_offset, int ldb) {
    f2j.sptts2(n, nrhs, d, _d_offset, e, _e_offset, b, _b_offset, ldb);
  }

  public void srscl(int n, float sa, float[] sx, int incx) {
    f2j.srscl(n, sa, sx, 0, incx);
  }

  public void srscl(int n, float sa, float[] sx, int _sx_offset, int incx) {
    f2j.srscl(n, sa, sx, _sx_offset, incx);
  }

  public void ssbev(String jobz, String uplo, int n, int kd, float[] ab, int ldab, float[] w, float[] z, int ldz, float[] work, org.netlib.util.intW info) {
    f2j.ssbev(jobz, uplo, n, kd, ab, 0, ldab, w, 0, z, 0, ldz, work, 0, info);
  }

  public void ssbev(String jobz, String uplo, int n, int kd, float[] ab, int _ab_offset, int ldab, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.ssbev(jobz, uplo, n, kd, ab, _ab_offset, ldab, w, _w_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  public void ssbevd(String jobz, String uplo, int n, int kd, float[] ab, int ldab, float[] w, float[] z, int ldz, float[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.ssbevd(jobz, uplo, n, kd, ab, 0, ldab, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void ssbevd(String jobz, String uplo, int n, int kd, float[] ab, int _ab_offset, int ldab, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.ssbevd(jobz, uplo, n, kd, ab, _ab_offset, ldab, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void ssbevx(String jobz, String range, String uplo, int n, int kd, float[] ab, int ldab, float[] q, int ldq, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, float[] z, int ldz, float[] work, int[] iwork, int[] ifail, org.netlib.util.intW info) {
    f2j.ssbevx(jobz, range, uplo, n, kd, ab, 0, ldab, q, 0, ldq, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
  }

  public void ssbevx(String jobz, String range, String uplo, int n, int kd, float[] ab, int _ab_offset, int ldab, float[] q, int _q_offset, int ldq, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    f2j.ssbevx(jobz, range, uplo, n, kd, ab, _ab_offset, ldab, q, _q_offset, ldq, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  public void ssbgst(String vect, String uplo, int n, int ka, int kb, float[] ab, int ldab, float[] bb, int ldbb, float[] x, int ldx, float[] work, org.netlib.util.intW info) {
    f2j.ssbgst(vect, uplo, n, ka, kb, ab, 0, ldab, bb, 0, ldbb, x, 0, ldx, work, 0, info);
  }

  public void ssbgst(String vect, String uplo, int n, int ka, int kb, float[] ab, int _ab_offset, int ldab, float[] bb, int _bb_offset, int ldbb, float[] x, int _x_offset, int ldx, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.ssbgst(vect, uplo, n, ka, kb, ab, _ab_offset, ldab, bb, _bb_offset, ldbb, x, _x_offset, ldx, work, _work_offset, info);
  }

  public void ssbgv(String jobz, String uplo, int n, int ka, int kb, float[] ab, int ldab, float[] bb, int ldbb, float[] w, float[] z, int ldz, float[] work, org.netlib.util.intW info) {
    f2j.ssbgv(jobz, uplo, n, ka, kb, ab, 0, ldab, bb, 0, ldbb, w, 0, z, 0, ldz, work, 0, info);
  }

  public void ssbgv(String jobz, String uplo, int n, int ka, int kb, float[] ab, int _ab_offset, int ldab, float[] bb, int _bb_offset, int ldbb, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.ssbgv(jobz, uplo, n, ka, kb, ab, _ab_offset, ldab, bb, _bb_offset, ldbb, w, _w_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  public void ssbgvd(String jobz, String uplo, int n, int ka, int kb, float[] ab, int ldab, float[] bb, int ldbb, float[] w, float[] z, int ldz, float[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.ssbgvd(jobz, uplo, n, ka, kb, ab, 0, ldab, bb, 0, ldbb, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void ssbgvd(String jobz, String uplo, int n, int ka, int kb, float[] ab, int _ab_offset, int ldab, float[] bb, int _bb_offset, int ldbb, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.ssbgvd(jobz, uplo, n, ka, kb, ab, _ab_offset, ldab, bb, _bb_offset, ldbb, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void ssbgvx(String jobz, String range, String uplo, int n, int ka, int kb, float[] ab, int ldab, float[] bb, int ldbb, float[] q, int ldq, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, float[] z, int ldz, float[] work, int[] iwork, int[] ifail, org.netlib.util.intW info) {
    f2j.ssbgvx(jobz, range, uplo, n, ka, kb, ab, 0, ldab, bb, 0, ldbb, q, 0, ldq, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
  }

  public void ssbgvx(String jobz, String range, String uplo, int n, int ka, int kb, float[] ab, int _ab_offset, int ldab, float[] bb, int _bb_offset, int ldbb, float[] q, int _q_offset, int ldq, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    f2j.ssbgvx(jobz, range, uplo, n, ka, kb, ab, _ab_offset, ldab, bb, _bb_offset, ldbb, q, _q_offset, ldq, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  public void ssbtrd(String vect, String uplo, int n, int kd, float[] ab, int ldab, float[] d, float[] e, float[] q, int ldq, float[] work, org.netlib.util.intW info) {
    f2j.ssbtrd(vect, uplo, n, kd, ab, 0, ldab, d, 0, e, 0, q, 0, ldq, work, 0, info);
  }

  public void ssbtrd(String vect, String uplo, int n, int kd, float[] ab, int _ab_offset, int ldab, float[] d, int _d_offset, float[] e, int _e_offset, float[] q, int _q_offset, int ldq, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.ssbtrd(vect, uplo, n, kd, ab, _ab_offset, ldab, d, _d_offset, e, _e_offset, q, _q_offset, ldq, work, _work_offset, info);
  }

  public void sspcon(String uplo, int n, float[] ap, int[] ipiv, float anorm, org.netlib.util.floatW rcond, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.sspcon(uplo, n, ap, 0, ipiv, 0, anorm, rcond, work, 0, iwork, 0, info);
  }

  public void sspcon(String uplo, int n, float[] ap, int _ap_offset, int[] ipiv, int _ipiv_offset, float anorm, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sspcon(uplo, n, ap, _ap_offset, ipiv, _ipiv_offset, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void sspev(String jobz, String uplo, int n, float[] ap, float[] w, float[] z, int ldz, float[] work, org.netlib.util.intW info) {
    f2j.sspev(jobz, uplo, n, ap, 0, w, 0, z, 0, ldz, work, 0, info);
  }

  public void sspev(String jobz, String uplo, int n, float[] ap, int _ap_offset, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sspev(jobz, uplo, n, ap, _ap_offset, w, _w_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  public void sspevd(String jobz, String uplo, int n, float[] ap, float[] w, float[] z, int ldz, float[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.sspevd(jobz, uplo, n, ap, 0, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void sspevd(String jobz, String uplo, int n, float[] ap, int _ap_offset, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.sspevd(jobz, uplo, n, ap, _ap_offset, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void sspevx(String jobz, String range, String uplo, int n, float[] ap, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, float[] z, int ldz, float[] work, int[] iwork, int[] ifail, org.netlib.util.intW info) {
    f2j.sspevx(jobz, range, uplo, n, ap, 0, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
  }

  public void sspevx(String jobz, String range, String uplo, int n, float[] ap, int _ap_offset, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    f2j.sspevx(jobz, range, uplo, n, ap, _ap_offset, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  public void sspgst(int itype, String uplo, int n, float[] ap, float[] bp, org.netlib.util.intW info) {
    f2j.sspgst(itype, uplo, n, ap, 0, bp, 0, info);
  }

  public void sspgst(int itype, String uplo, int n, float[] ap, int _ap_offset, float[] bp, int _bp_offset, org.netlib.util.intW info) {
    f2j.sspgst(itype, uplo, n, ap, _ap_offset, bp, _bp_offset, info);
  }

  public void sspgv(int itype, String jobz, String uplo, int n, float[] ap, float[] bp, float[] w, float[] z, int ldz, float[] work, org.netlib.util.intW info) {
    f2j.sspgv(itype, jobz, uplo, n, ap, 0, bp, 0, w, 0, z, 0, ldz, work, 0, info);
  }

  public void sspgv(int itype, String jobz, String uplo, int n, float[] ap, int _ap_offset, float[] bp, int _bp_offset, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sspgv(itype, jobz, uplo, n, ap, _ap_offset, bp, _bp_offset, w, _w_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  public void sspgvd(int itype, String jobz, String uplo, int n, float[] ap, float[] bp, float[] w, float[] z, int ldz, float[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.sspgvd(itype, jobz, uplo, n, ap, 0, bp, 0, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void sspgvd(int itype, String jobz, String uplo, int n, float[] ap, int _ap_offset, float[] bp, int _bp_offset, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.sspgvd(itype, jobz, uplo, n, ap, _ap_offset, bp, _bp_offset, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void sspgvx(int itype, String jobz, String range, String uplo, int n, float[] ap, float[] bp, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, float[] z, int ldz, float[] work, int[] iwork, int[] ifail, org.netlib.util.intW info) {
    f2j.sspgvx(itype, jobz, range, uplo, n, ap, 0, bp, 0, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
  }

  public void sspgvx(int itype, String jobz, String range, String uplo, int n, float[] ap, int _ap_offset, float[] bp, int _bp_offset, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    f2j.sspgvx(itype, jobz, range, uplo, n, ap, _ap_offset, bp, _bp_offset, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  public void ssprfs(String uplo, int n, int nrhs, float[] ap, float[] afp, int[] ipiv, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.ssprfs(uplo, n, nrhs, ap, 0, afp, 0, ipiv, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void ssprfs(String uplo, int n, int nrhs, float[] ap, int _ap_offset, float[] afp, int _afp_offset, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.ssprfs(uplo, n, nrhs, ap, _ap_offset, afp, _afp_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void sspsv(String uplo, int n, int nrhs, float[] ap, int[] ipiv, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.sspsv(uplo, n, nrhs, ap, 0, ipiv, 0, b, 0, ldb, info);
  }

  public void sspsv(String uplo, int n, int nrhs, float[] ap, int _ap_offset, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.sspsv(uplo, n, nrhs, ap, _ap_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  public void sspsvx(String fact, String uplo, int n, int nrhs, float[] ap, float[] afp, int[] ipiv, float[] b, int ldb, float[] x, int ldx, org.netlib.util.floatW rcond, float[] ferr, float[] berr, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.sspsvx(fact, uplo, n, nrhs, ap, 0, afp, 0, ipiv, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void sspsvx(String fact, String uplo, int n, int nrhs, float[] ap, int _ap_offset, float[] afp, int _afp_offset, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, org.netlib.util.floatW rcond, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sspsvx(fact, uplo, n, nrhs, ap, _ap_offset, afp, _afp_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void ssptrd(String uplo, int n, float[] ap, float[] d, float[] e, float[] tau, org.netlib.util.intW info) {
    f2j.ssptrd(uplo, n, ap, 0, d, 0, e, 0, tau, 0, info);
  }

  public void ssptrd(String uplo, int n, float[] ap, int _ap_offset, float[] d, int _d_offset, float[] e, int _e_offset, float[] tau, int _tau_offset, org.netlib.util.intW info) {
    f2j.ssptrd(uplo, n, ap, _ap_offset, d, _d_offset, e, _e_offset, tau, _tau_offset, info);
  }

  public void ssptrf(String uplo, int n, float[] ap, int[] ipiv, org.netlib.util.intW info) {
    f2j.ssptrf(uplo, n, ap, 0, ipiv, 0, info);
  }

  public void ssptrf(String uplo, int n, float[] ap, int _ap_offset, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    f2j.ssptrf(uplo, n, ap, _ap_offset, ipiv, _ipiv_offset, info);
  }

  public void ssptri(String uplo, int n, float[] ap, int[] ipiv, float[] work, org.netlib.util.intW info) {
    f2j.ssptri(uplo, n, ap, 0, ipiv, 0, work, 0, info);
  }

  public void ssptri(String uplo, int n, float[] ap, int _ap_offset, int[] ipiv, int _ipiv_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.ssptri(uplo, n, ap, _ap_offset, ipiv, _ipiv_offset, work, _work_offset, info);
  }

  public void ssptrs(String uplo, int n, int nrhs, float[] ap, int[] ipiv, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.ssptrs(uplo, n, nrhs, ap, 0, ipiv, 0, b, 0, ldb, info);
  }

  public void ssptrs(String uplo, int n, int nrhs, float[] ap, int _ap_offset, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.ssptrs(uplo, n, nrhs, ap, _ap_offset, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  public void sstebz(String range, String order, int n, float vl, float vu, int il, int iu, float abstol, float[] d, float[] e, org.netlib.util.intW m, org.netlib.util.intW nsplit, float[] w, int[] iblock, int[] isplit, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.sstebz(range, order, n, vl, vu, il, iu, abstol, d, 0, e, 0, m, nsplit, w, 0, iblock, 0, isplit, 0, work, 0, iwork, 0, info);
  }

  public void sstebz(String range, String order, int n, float vl, float vu, int il, int iu, float abstol, float[] d, int _d_offset, float[] e, int _e_offset, org.netlib.util.intW m, org.netlib.util.intW nsplit, float[] w, int _w_offset, int[] iblock, int _iblock_offset, int[] isplit, int _isplit_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.sstebz(range, order, n, vl, vu, il, iu, abstol, d, _d_offset, e, _e_offset, m, nsplit, w, _w_offset, iblock, _iblock_offset, isplit, _isplit_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void sstedc(String compz, int n, float[] d, float[] e, float[] z, int ldz, float[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.sstedc(compz, n, d, 0, e, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void sstedc(String compz, int n, float[] d, int _d_offset, float[] e, int _e_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.sstedc(compz, n, d, _d_offset, e, _e_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void sstegr(String jobz, String range, int n, float[] d, float[] e, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, float[] z, int ldz, int[] isuppz, float[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.sstegr(jobz, range, n, d, 0, e, 0, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, isuppz, 0, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void sstegr(String jobz, String range, int n, float[] d, int _d_offset, float[] e, int _e_offset, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, int[] isuppz, int _isuppz_offset, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.sstegr(jobz, range, n, d, _d_offset, e, _e_offset, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, isuppz, _isuppz_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void sstein(int n, float[] d, float[] e, int m, float[] w, int[] iblock, int[] isplit, float[] z, int ldz, float[] work, int[] iwork, int[] ifail, org.netlib.util.intW info) {
    f2j.sstein(n, d, 0, e, 0, m, w, 0, iblock, 0, isplit, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
  }

  public void sstein(int n, float[] d, int _d_offset, float[] e, int _e_offset, int m, float[] w, int _w_offset, int[] iblock, int _iblock_offset, int[] isplit, int _isplit_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    f2j.sstein(n, d, _d_offset, e, _e_offset, m, w, _w_offset, iblock, _iblock_offset, isplit, _isplit_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  public void sstemr(String jobz, String range, int n, float[] d, float[] e, float vl, float vu, int il, int iu, org.netlib.util.intW m, float[] w, float[] z, int ldz, int nzc, int[] isuppz, org.netlib.util.booleanW tryrac, float[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.sstemr(jobz, range, n, d, 0, e, 0, vl, vu, il, iu, m, w, 0, z, 0, ldz, nzc, isuppz, 0, tryrac, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void sstemr(String jobz, String range, int n, float[] d, int _d_offset, float[] e, int _e_offset, float vl, float vu, int il, int iu, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, int nzc, int[] isuppz, int _isuppz_offset, org.netlib.util.booleanW tryrac, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.sstemr(jobz, range, n, d, _d_offset, e, _e_offset, vl, vu, il, iu, m, w, _w_offset, z, _z_offset, ldz, nzc, isuppz, _isuppz_offset, tryrac, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void ssteqr(String compz, int n, float[] d, float[] e, float[] z, int ldz, float[] work, org.netlib.util.intW info) {
    f2j.ssteqr(compz, n, d, 0, e, 0, z, 0, ldz, work, 0, info);
  }

  public void ssteqr(String compz, int n, float[] d, int _d_offset, float[] e, int _e_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.ssteqr(compz, n, d, _d_offset, e, _e_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  public void ssterf(int n, float[] d, float[] e, org.netlib.util.intW info) {
    f2j.ssterf(n, d, 0, e, 0, info);
  }

  public void ssterf(int n, float[] d, int _d_offset, float[] e, int _e_offset, org.netlib.util.intW info) {
    f2j.ssterf(n, d, _d_offset, e, _e_offset, info);
  }

  public void sstev(String jobz, int n, float[] d, float[] e, float[] z, int ldz, float[] work, org.netlib.util.intW info) {
    f2j.sstev(jobz, n, d, 0, e, 0, z, 0, ldz, work, 0, info);
  }

  public void sstev(String jobz, int n, float[] d, int _d_offset, float[] e, int _e_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.sstev(jobz, n, d, _d_offset, e, _e_offset, z, _z_offset, ldz, work, _work_offset, info);
  }

  public void sstevd(String jobz, int n, float[] d, float[] e, float[] z, int ldz, float[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.sstevd(jobz, n, d, 0, e, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void sstevd(String jobz, int n, float[] d, int _d_offset, float[] e, int _e_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.sstevd(jobz, n, d, _d_offset, e, _e_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void sstevr(String jobz, String range, int n, float[] d, float[] e, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, float[] z, int ldz, int[] isuppz, float[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.sstevr(jobz, range, n, d, 0, e, 0, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, isuppz, 0, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void sstevr(String jobz, String range, int n, float[] d, int _d_offset, float[] e, int _e_offset, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, int[] isuppz, int _isuppz_offset, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.sstevr(jobz, range, n, d, _d_offset, e, _e_offset, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, isuppz, _isuppz_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void sstevx(String jobz, String range, int n, float[] d, float[] e, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, float[] z, int ldz, float[] work, int[] iwork, int[] ifail, org.netlib.util.intW info) {
    f2j.sstevx(jobz, range, n, d, 0, e, 0, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
  }

  public void sstevx(String jobz, String range, int n, float[] d, int _d_offset, float[] e, int _e_offset, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    f2j.sstevx(jobz, range, n, d, _d_offset, e, _e_offset, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  public void ssycon(String uplo, int n, float[] a, int lda, int[] ipiv, float anorm, org.netlib.util.floatW rcond, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.ssycon(uplo, n, a, 0, lda, ipiv, 0, anorm, rcond, work, 0, iwork, 0, info);
  }

  public void ssycon(String uplo, int n, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, float anorm, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.ssycon(uplo, n, a, _a_offset, lda, ipiv, _ipiv_offset, anorm, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void ssyev(String jobz, String uplo, int n, float[] a, int lda, float[] w, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.ssyev(jobz, uplo, n, a, 0, lda, w, 0, work, 0, lwork, info);
  }

  public void ssyev(String jobz, String uplo, int n, float[] a, int _a_offset, int lda, float[] w, int _w_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.ssyev(jobz, uplo, n, a, _a_offset, lda, w, _w_offset, work, _work_offset, lwork, info);
  }

  public void ssyevd(String jobz, String uplo, int n, float[] a, int lda, float[] w, float[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.ssyevd(jobz, uplo, n, a, 0, lda, w, 0, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void ssyevd(String jobz, String uplo, int n, float[] a, int _a_offset, int lda, float[] w, int _w_offset, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.ssyevd(jobz, uplo, n, a, _a_offset, lda, w, _w_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void ssyevr(String jobz, String range, String uplo, int n, float[] a, int lda, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, float[] z, int ldz, int[] isuppz, float[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.ssyevr(jobz, range, uplo, n, a, 0, lda, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, isuppz, 0, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void ssyevr(String jobz, String range, String uplo, int n, float[] a, int _a_offset, int lda, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, int[] isuppz, int _isuppz_offset, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.ssyevr(jobz, range, uplo, n, a, _a_offset, lda, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, isuppz, _isuppz_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void ssyevx(String jobz, String range, String uplo, int n, float[] a, int lda, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, float[] z, int ldz, float[] work, int lwork, int[] iwork, int[] ifail, org.netlib.util.intW info) {
    f2j.ssyevx(jobz, range, uplo, n, a, 0, lda, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, ifail, 0, info);
  }

  public void ssyevx(String jobz, String range, String uplo, int n, float[] a, int _a_offset, int lda, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    f2j.ssyevx(jobz, range, uplo, n, a, _a_offset, lda, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  public void ssygs2(int itype, String uplo, int n, float[] a, int lda, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.ssygs2(itype, uplo, n, a, 0, lda, b, 0, ldb, info);
  }

  public void ssygs2(int itype, String uplo, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.ssygs2(itype, uplo, n, a, _a_offset, lda, b, _b_offset, ldb, info);
  }

  public void ssygst(int itype, String uplo, int n, float[] a, int lda, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.ssygst(itype, uplo, n, a, 0, lda, b, 0, ldb, info);
  }

  public void ssygst(int itype, String uplo, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.ssygst(itype, uplo, n, a, _a_offset, lda, b, _b_offset, ldb, info);
  }

  public void ssygv(int itype, String jobz, String uplo, int n, float[] a, int lda, float[] b, int ldb, float[] w, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.ssygv(itype, jobz, uplo, n, a, 0, lda, b, 0, ldb, w, 0, work, 0, lwork, info);
  }

  public void ssygv(int itype, String jobz, String uplo, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] w, int _w_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.ssygv(itype, jobz, uplo, n, a, _a_offset, lda, b, _b_offset, ldb, w, _w_offset, work, _work_offset, lwork, info);
  }

  public void ssygvd(int itype, String jobz, String uplo, int n, float[] a, int lda, float[] b, int ldb, float[] w, float[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.ssygvd(itype, jobz, uplo, n, a, 0, lda, b, 0, ldb, w, 0, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void ssygvd(int itype, String jobz, String uplo, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] w, int _w_offset, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.ssygvd(itype, jobz, uplo, n, a, _a_offset, lda, b, _b_offset, ldb, w, _w_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void ssygvx(int itype, String jobz, String range, String uplo, int n, float[] a, int lda, float[] b, int ldb, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, float[] z, int ldz, float[] work, int lwork, int[] iwork, int[] ifail, org.netlib.util.intW info) {
    f2j.ssygvx(itype, jobz, range, uplo, n, a, 0, lda, b, 0, ldb, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, ifail, 0, info);
  }

  public void ssygvx(int itype, String jobz, String range, String uplo, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float vl, float vu, int il, int iu, float abstol, org.netlib.util.intW m, float[] w, int _w_offset, float[] z, int _z_offset, int ldz, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int[] ifail, int _ifail_offset, org.netlib.util.intW info) {
    f2j.ssygvx(itype, jobz, range, uplo, n, a, _a_offset, lda, b, _b_offset, ldb, vl, vu, il, iu, abstol, m, w, _w_offset, z, _z_offset, ldz, work, _work_offset, lwork, iwork, _iwork_offset, ifail, _ifail_offset, info);
  }

  public void ssyrfs(String uplo, int n, int nrhs, float[] a, int lda, float[] af, int ldaf, int[] ipiv, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.ssyrfs(uplo, n, nrhs, a, 0, lda, af, 0, ldaf, ipiv, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void ssyrfs(String uplo, int n, int nrhs, float[] a, int _a_offset, int lda, float[] af, int _af_offset, int ldaf, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.ssyrfs(uplo, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void ssysv(String uplo, int n, int nrhs, float[] a, int lda, int[] ipiv, float[] b, int ldb, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.ssysv(uplo, n, nrhs, a, 0, lda, ipiv, 0, b, 0, ldb, work, 0, lwork, info);
  }

  public void ssysv(String uplo, int n, int nrhs, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.ssysv(uplo, n, nrhs, a, _a_offset, lda, ipiv, _ipiv_offset, b, _b_offset, ldb, work, _work_offset, lwork, info);
  }

  public void ssysvx(String fact, String uplo, int n, int nrhs, float[] a, int lda, float[] af, int ldaf, int[] ipiv, float[] b, int ldb, float[] x, int ldx, org.netlib.util.floatW rcond, float[] ferr, float[] berr, float[] work, int lwork, int[] iwork, org.netlib.util.intW info) {
    f2j.ssysvx(fact, uplo, n, nrhs, a, 0, lda, af, 0, ldaf, ipiv, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, lwork, iwork, 0, info);
  }

  public void ssysvx(String fact, String uplo, int n, int nrhs, float[] a, int _a_offset, int lda, float[] af, int _af_offset, int ldaf, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, org.netlib.util.floatW rcond, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.ssysvx(fact, uplo, n, nrhs, a, _a_offset, lda, af, _af_offset, ldaf, ipiv, _ipiv_offset, b, _b_offset, ldb, x, _x_offset, ldx, rcond, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  public void ssytd2(String uplo, int n, float[] a, int lda, float[] d, float[] e, float[] tau, org.netlib.util.intW info) {
    f2j.ssytd2(uplo, n, a, 0, lda, d, 0, e, 0, tau, 0, info);
  }

  public void ssytd2(String uplo, int n, float[] a, int _a_offset, int lda, float[] d, int _d_offset, float[] e, int _e_offset, float[] tau, int _tau_offset, org.netlib.util.intW info) {
    f2j.ssytd2(uplo, n, a, _a_offset, lda, d, _d_offset, e, _e_offset, tau, _tau_offset, info);
  }

  public void ssytf2(String uplo, int n, float[] a, int lda, int[] ipiv, org.netlib.util.intW info) {
    f2j.ssytf2(uplo, n, a, 0, lda, ipiv, 0, info);
  }

  public void ssytf2(String uplo, int n, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, org.netlib.util.intW info) {
    f2j.ssytf2(uplo, n, a, _a_offset, lda, ipiv, _ipiv_offset, info);
  }

  public void ssytrd(String uplo, int n, float[] a, int lda, float[] d, float[] e, float[] tau, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.ssytrd(uplo, n, a, 0, lda, d, 0, e, 0, tau, 0, work, 0, lwork, info);
  }

  public void ssytrd(String uplo, int n, float[] a, int _a_offset, int lda, float[] d, int _d_offset, float[] e, int _e_offset, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.ssytrd(uplo, n, a, _a_offset, lda, d, _d_offset, e, _e_offset, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public void ssytrf(String uplo, int n, float[] a, int lda, int[] ipiv, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.ssytrf(uplo, n, a, 0, lda, ipiv, 0, work, 0, lwork, info);
  }

  public void ssytrf(String uplo, int n, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.ssytrf(uplo, n, a, _a_offset, lda, ipiv, _ipiv_offset, work, _work_offset, lwork, info);
  }

  public void ssytri(String uplo, int n, float[] a, int lda, int[] ipiv, float[] work, org.netlib.util.intW info) {
    f2j.ssytri(uplo, n, a, 0, lda, ipiv, 0, work, 0, info);
  }

  public void ssytri(String uplo, int n, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.ssytri(uplo, n, a, _a_offset, lda, ipiv, _ipiv_offset, work, _work_offset, info);
  }

  public void ssytrs(String uplo, int n, int nrhs, float[] a, int lda, int[] ipiv, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.ssytrs(uplo, n, nrhs, a, 0, lda, ipiv, 0, b, 0, ldb, info);
  }

  public void ssytrs(String uplo, int n, int nrhs, float[] a, int _a_offset, int lda, int[] ipiv, int _ipiv_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.ssytrs(uplo, n, nrhs, a, _a_offset, lda, ipiv, _ipiv_offset, b, _b_offset, ldb, info);
  }

  public void stbcon(String norm, String uplo, String diag, int n, int kd, float[] ab, int ldab, org.netlib.util.floatW rcond, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.stbcon(norm, uplo, diag, n, kd, ab, 0, ldab, rcond, work, 0, iwork, 0, info);
  }

  public void stbcon(String norm, String uplo, String diag, int n, int kd, float[] ab, int _ab_offset, int ldab, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.stbcon(norm, uplo, diag, n, kd, ab, _ab_offset, ldab, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void stbrfs(String uplo, String trans, String diag, int n, int kd, int nrhs, float[] ab, int ldab, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.stbrfs(uplo, trans, diag, n, kd, nrhs, ab, 0, ldab, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void stbrfs(String uplo, String trans, String diag, int n, int kd, int nrhs, float[] ab, int _ab_offset, int ldab, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.stbrfs(uplo, trans, diag, n, kd, nrhs, ab, _ab_offset, ldab, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void stbtrs(String uplo, String trans, String diag, int n, int kd, int nrhs, float[] ab, int ldab, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.stbtrs(uplo, trans, diag, n, kd, nrhs, ab, 0, ldab, b, 0, ldb, info);
  }

  public void stbtrs(String uplo, String trans, String diag, int n, int kd, int nrhs, float[] ab, int _ab_offset, int ldab, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.stbtrs(uplo, trans, diag, n, kd, nrhs, ab, _ab_offset, ldab, b, _b_offset, ldb, info);
  }

  public void stgevc(String side, String howmny, boolean[] select, int n, float[] s, int lds, float[] p, int ldp, float[] vl, int ldvl, float[] vr, int ldvr, int mm, org.netlib.util.intW m, float[] work, org.netlib.util.intW info) {
    f2j.stgevc(side, howmny, select, 0, n, s, 0, lds, p, 0, ldp, vl, 0, ldvl, vr, 0, ldvr, mm, m, work, 0, info);
  }

  public void stgevc(String side, String howmny, boolean[] select, int _select_offset, int n, float[] s, int _s_offset, int lds, float[] p, int _p_offset, int ldp, float[] vl, int _vl_offset, int ldvl, float[] vr, int _vr_offset, int ldvr, int mm, org.netlib.util.intW m, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.stgevc(side, howmny, select, _select_offset, n, s, _s_offset, lds, p, _p_offset, ldp, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, mm, m, work, _work_offset, info);
  }

  public void stgex2(boolean wantq, boolean wantz, int n, float[] a, int lda, float[] b, int ldb, float[] q, int ldq, float[] z, int ldz, int j1, int n1, int n2, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.stgex2(wantq, wantz, n, a, 0, lda, b, 0, ldb, q, 0, ldq, z, 0, ldz, j1, n1, n2, work, 0, lwork, info);
  }

  public void stgex2(boolean wantq, boolean wantz, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] q, int _q_offset, int ldq, float[] z, int _z_offset, int ldz, int j1, int n1, int n2, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.stgex2(wantq, wantz, n, a, _a_offset, lda, b, _b_offset, ldb, q, _q_offset, ldq, z, _z_offset, ldz, j1, n1, n2, work, _work_offset, lwork, info);
  }

  public void stgexc(boolean wantq, boolean wantz, int n, float[] a, int lda, float[] b, int ldb, float[] q, int ldq, float[] z, int ldz, org.netlib.util.intW ifst, org.netlib.util.intW ilst, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.stgexc(wantq, wantz, n, a, 0, lda, b, 0, ldb, q, 0, ldq, z, 0, ldz, ifst, ilst, work, 0, lwork, info);
  }

  public void stgexc(boolean wantq, boolean wantz, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] q, int _q_offset, int ldq, float[] z, int _z_offset, int ldz, org.netlib.util.intW ifst, org.netlib.util.intW ilst, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.stgexc(wantq, wantz, n, a, _a_offset, lda, b, _b_offset, ldb, q, _q_offset, ldq, z, _z_offset, ldz, ifst, ilst, work, _work_offset, lwork, info);
  }

  public void stgsen(int ijob, boolean wantq, boolean wantz, boolean[] select, int n, float[] a, int lda, float[] b, int ldb, float[] alphar, float[] alphai, float[] beta, float[] q, int ldq, float[] z, int ldz, org.netlib.util.intW m, org.netlib.util.floatW pl, org.netlib.util.floatW pr, float[] dif, float[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.stgsen(ijob, wantq, wantz, select, 0, n, a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, q, 0, ldq, z, 0, ldz, m, pl, pr, dif, 0, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void stgsen(int ijob, boolean wantq, boolean wantz, boolean[] select, int _select_offset, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] alphar, int _alphar_offset, float[] alphai, int _alphai_offset, float[] beta, int _beta_offset, float[] q, int _q_offset, int ldq, float[] z, int _z_offset, int ldz, org.netlib.util.intW m, org.netlib.util.floatW pl, org.netlib.util.floatW pr, float[] dif, int _dif_offset, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.stgsen(ijob, wantq, wantz, select, _select_offset, n, a, _a_offset, lda, b, _b_offset, ldb, alphar, _alphar_offset, alphai, _alphai_offset, beta, _beta_offset, q, _q_offset, ldq, z, _z_offset, ldz, m, pl, pr, dif, _dif_offset, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void stgsja(String jobu, String jobv, String jobq, int m, int p, int n, int k, int l, float[] a, int lda, float[] b, int ldb, float tola, float tolb, float[] alpha, float[] beta, float[] u, int ldu, float[] v, int ldv, float[] q, int ldq, float[] work, org.netlib.util.intW ncycle, org.netlib.util.intW info) {
    f2j.stgsja(jobu, jobv, jobq, m, p, n, k, l, a, 0, lda, b, 0, ldb, tola, tolb, alpha, 0, beta, 0, u, 0, ldu, v, 0, ldv, q, 0, ldq, work, 0, ncycle, info);
  }

  public void stgsja(String jobu, String jobv, String jobq, int m, int p, int n, int k, int l, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float tola, float tolb, float[] alpha, int _alpha_offset, float[] beta, int _beta_offset, float[] u, int _u_offset, int ldu, float[] v, int _v_offset, int ldv, float[] q, int _q_offset, int ldq, float[] work, int _work_offset, org.netlib.util.intW ncycle, org.netlib.util.intW info) {
    f2j.stgsja(jobu, jobv, jobq, m, p, n, k, l, a, _a_offset, lda, b, _b_offset, ldb, tola, tolb, alpha, _alpha_offset, beta, _beta_offset, u, _u_offset, ldu, v, _v_offset, ldv, q, _q_offset, ldq, work, _work_offset, ncycle, info);
  }

  public void stgsna(String job, String howmny, boolean[] select, int n, float[] a, int lda, float[] b, int ldb, float[] vl, int ldvl, float[] vr, int ldvr, float[] s, float[] dif, int mm, org.netlib.util.intW m, float[] work, int lwork, int[] iwork, org.netlib.util.intW info) {
    f2j.stgsna(job, howmny, select, 0, n, a, 0, lda, b, 0, ldb, vl, 0, ldvl, vr, 0, ldvr, s, 0, dif, 0, mm, m, work, 0, lwork, iwork, 0, info);
  }

  public void stgsna(String job, String howmny, boolean[] select, int _select_offset, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] vl, int _vl_offset, int ldvl, float[] vr, int _vr_offset, int ldvr, float[] s, int _s_offset, float[] dif, int _dif_offset, int mm, org.netlib.util.intW m, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.stgsna(job, howmny, select, _select_offset, n, a, _a_offset, lda, b, _b_offset, ldb, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, s, _s_offset, dif, _dif_offset, mm, m, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  public void stgsy2(String trans, int ijob, int m, int n, float[] a, int lda, float[] b, int ldb, float[] c, int Ldc, float[] d, int ldd, float[] e, int lde, float[] f, int ldf, org.netlib.util.floatW scale, org.netlib.util.floatW rdsum, org.netlib.util.floatW rdscal, int[] iwork, org.netlib.util.intW pq, org.netlib.util.intW info) {
    f2j.stgsy2(trans, ijob, m, n, a, 0, lda, b, 0, ldb, c, 0, Ldc, d, 0, ldd, e, 0, lde, f, 0, ldf, scale, rdsum, rdscal, iwork, 0, pq, info);
  }

  public void stgsy2(String trans, int ijob, int m, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] c, int _c_offset, int Ldc, float[] d, int _d_offset, int ldd, float[] e, int _e_offset, int lde, float[] f, int _f_offset, int ldf, org.netlib.util.floatW scale, org.netlib.util.floatW rdsum, org.netlib.util.floatW rdscal, int[] iwork, int _iwork_offset, org.netlib.util.intW pq, org.netlib.util.intW info) {
    f2j.stgsy2(trans, ijob, m, n, a, _a_offset, lda, b, _b_offset, ldb, c, _c_offset, Ldc, d, _d_offset, ldd, e, _e_offset, lde, f, _f_offset, ldf, scale, rdsum, rdscal, iwork, _iwork_offset, pq, info);
  }

  public void stgsyl(String trans, int ijob, int m, int n, float[] a, int lda, float[] b, int ldb, float[] c, int Ldc, float[] d, int ldd, float[] e, int lde, float[] f, int ldf, org.netlib.util.floatW scale, org.netlib.util.floatW dif, float[] work, int lwork, int[] iwork, org.netlib.util.intW info) {
    f2j.stgsyl(trans, ijob, m, n, a, 0, lda, b, 0, ldb, c, 0, Ldc, d, 0, ldd, e, 0, lde, f, 0, ldf, scale, dif, work, 0, lwork, iwork, 0, info);
  }

  public void stgsyl(String trans, int ijob, int m, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] c, int _c_offset, int Ldc, float[] d, int _d_offset, int ldd, float[] e, int _e_offset, int lde, float[] f, int _f_offset, int ldf, org.netlib.util.floatW scale, org.netlib.util.floatW dif, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.stgsyl(trans, ijob, m, n, a, _a_offset, lda, b, _b_offset, ldb, c, _c_offset, Ldc, d, _d_offset, ldd, e, _e_offset, lde, f, _f_offset, ldf, scale, dif, work, _work_offset, lwork, iwork, _iwork_offset, info);
  }

  public void stpcon(String norm, String uplo, String diag, int n, float[] ap, org.netlib.util.floatW rcond, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.stpcon(norm, uplo, diag, n, ap, 0, rcond, work, 0, iwork, 0, info);
  }

  public void stpcon(String norm, String uplo, String diag, int n, float[] ap, int _ap_offset, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.stpcon(norm, uplo, diag, n, ap, _ap_offset, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void stprfs(String uplo, String trans, String diag, int n, int nrhs, float[] ap, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.stprfs(uplo, trans, diag, n, nrhs, ap, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void stprfs(String uplo, String trans, String diag, int n, int nrhs, float[] ap, int _ap_offset, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.stprfs(uplo, trans, diag, n, nrhs, ap, _ap_offset, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void stptri(String uplo, String diag, int n, float[] ap, org.netlib.util.intW info) {
    f2j.stptri(uplo, diag, n, ap, 0, info);
  }

  public void stptri(String uplo, String diag, int n, float[] ap, int _ap_offset, org.netlib.util.intW info) {
    f2j.stptri(uplo, diag, n, ap, _ap_offset, info);
  }

  public void stptrs(String uplo, String trans, String diag, int n, int nrhs, float[] ap, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.stptrs(uplo, trans, diag, n, nrhs, ap, 0, b, 0, ldb, info);
  }

  public void stptrs(String uplo, String trans, String diag, int n, int nrhs, float[] ap, int _ap_offset, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.stptrs(uplo, trans, diag, n, nrhs, ap, _ap_offset, b, _b_offset, ldb, info);
  }

  public void strcon(String norm, String uplo, String diag, int n, float[] a, int lda, org.netlib.util.floatW rcond, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.strcon(norm, uplo, diag, n, a, 0, lda, rcond, work, 0, iwork, 0, info);
  }

  public void strcon(String norm, String uplo, String diag, int n, float[] a, int _a_offset, int lda, org.netlib.util.floatW rcond, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.strcon(norm, uplo, diag, n, a, _a_offset, lda, rcond, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void strevc(String side, String howmny, boolean[] select, int n, float[] t, int ldt, float[] vl, int ldvl, float[] vr, int ldvr, int mm, org.netlib.util.intW m, float[] work, org.netlib.util.intW info) {
    f2j.strevc(side, howmny, select, 0, n, t, 0, ldt, vl, 0, ldvl, vr, 0, ldvr, mm, m, work, 0, info);
  }

  public void strevc(String side, String howmny, boolean[] select, int _select_offset, int n, float[] t, int _t_offset, int ldt, float[] vl, int _vl_offset, int ldvl, float[] vr, int _vr_offset, int ldvr, int mm, org.netlib.util.intW m, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.strevc(side, howmny, select, _select_offset, n, t, _t_offset, ldt, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, mm, m, work, _work_offset, info);
  }

  public void strexc(String compq, int n, float[] t, int ldt, float[] q, int ldq, org.netlib.util.intW ifst, org.netlib.util.intW ilst, float[] work, org.netlib.util.intW info) {
    f2j.strexc(compq, n, t, 0, ldt, q, 0, ldq, ifst, ilst, work, 0, info);
  }

  public void strexc(String compq, int n, float[] t, int _t_offset, int ldt, float[] q, int _q_offset, int ldq, org.netlib.util.intW ifst, org.netlib.util.intW ilst, float[] work, int _work_offset, org.netlib.util.intW info) {
    f2j.strexc(compq, n, t, _t_offset, ldt, q, _q_offset, ldq, ifst, ilst, work, _work_offset, info);
  }

  public void strrfs(String uplo, String trans, String diag, int n, int nrhs, float[] a, int lda, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, org.netlib.util.intW info) {
    f2j.strrfs(uplo, trans, diag, n, nrhs, a, 0, lda, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
  }

  public void strrfs(String uplo, String trans, String diag, int n, int nrhs, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] x, int _x_offset, int ldx, float[] ferr, int _ferr_offset, float[] berr, int _berr_offset, float[] work, int _work_offset, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.strrfs(uplo, trans, diag, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, x, _x_offset, ldx, ferr, _ferr_offset, berr, _berr_offset, work, _work_offset, iwork, _iwork_offset, info);
  }

  public void strsen(String job, String compq, boolean[] select, int n, float[] t, int ldt, float[] q, int ldq, float[] wr, float[] wi, org.netlib.util.intW m, org.netlib.util.floatW s, org.netlib.util.floatW sep, float[] work, int lwork, int[] iwork, int liwork, org.netlib.util.intW info) {
    f2j.strsen(job, compq, select, 0, n, t, 0, ldt, q, 0, ldq, wr, 0, wi, 0, m, s, sep, work, 0, lwork, iwork, 0, liwork, info);
  }

  public void strsen(String job, String compq, boolean[] select, int _select_offset, int n, float[] t, int _t_offset, int ldt, float[] q, int _q_offset, int ldq, float[] wr, int _wr_offset, float[] wi, int _wi_offset, org.netlib.util.intW m, org.netlib.util.floatW s, org.netlib.util.floatW sep, float[] work, int _work_offset, int lwork, int[] iwork, int _iwork_offset, int liwork, org.netlib.util.intW info) {
    f2j.strsen(job, compq, select, _select_offset, n, t, _t_offset, ldt, q, _q_offset, ldq, wr, _wr_offset, wi, _wi_offset, m, s, sep, work, _work_offset, lwork, iwork, _iwork_offset, liwork, info);
  }

  public void strsna(String job, String howmny, boolean[] select, int n, float[] t, int ldt, float[] vl, int ldvl, float[] vr, int ldvr, float[] s, float[] sep, int mm, org.netlib.util.intW m, float[] work, int ldwork, int[] iwork, org.netlib.util.intW info) {
    f2j.strsna(job, howmny, select, 0, n, t, 0, ldt, vl, 0, ldvl, vr, 0, ldvr, s, 0, sep, 0, mm, m, work, 0, ldwork, iwork, 0, info);
  }

  public void strsna(String job, String howmny, boolean[] select, int _select_offset, int n, float[] t, int _t_offset, int ldt, float[] vl, int _vl_offset, int ldvl, float[] vr, int _vr_offset, int ldvr, float[] s, int _s_offset, float[] sep, int _sep_offset, int mm, org.netlib.util.intW m, float[] work, int _work_offset, int ldwork, int[] iwork, int _iwork_offset, org.netlib.util.intW info) {
    f2j.strsna(job, howmny, select, _select_offset, n, t, _t_offset, ldt, vl, _vl_offset, ldvl, vr, _vr_offset, ldvr, s, _s_offset, sep, _sep_offset, mm, m, work, _work_offset, ldwork, iwork, _iwork_offset, info);
  }

  public void strsyl(String trana, String tranb, int isgn, int m, int n, float[] a, int lda, float[] b, int ldb, float[] c, int Ldc, org.netlib.util.floatW scale, org.netlib.util.intW info) {
    f2j.strsyl(trana, tranb, isgn, m, n, a, 0, lda, b, 0, ldb, c, 0, Ldc, scale, info);
  }

  public void strsyl(String trana, String tranb, int isgn, int m, int n, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, float[] c, int _c_offset, int Ldc, org.netlib.util.floatW scale, org.netlib.util.intW info) {
    f2j.strsyl(trana, tranb, isgn, m, n, a, _a_offset, lda, b, _b_offset, ldb, c, _c_offset, Ldc, scale, info);
  }

  public void strti2(String uplo, String diag, int n, float[] a, int lda, org.netlib.util.intW info) {
    f2j.strti2(uplo, diag, n, a, 0, lda, info);
  }

  public void strti2(String uplo, String diag, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    f2j.strti2(uplo, diag, n, a, _a_offset, lda, info);
  }

  public void strtri(String uplo, String diag, int n, float[] a, int lda, org.netlib.util.intW info) {
    f2j.strtri(uplo, diag, n, a, 0, lda, info);
  }

  public void strtri(String uplo, String diag, int n, float[] a, int _a_offset, int lda, org.netlib.util.intW info) {
    f2j.strtri(uplo, diag, n, a, _a_offset, lda, info);
  }

  public void strtrs(String uplo, String trans, String diag, int n, int nrhs, float[] a, int lda, float[] b, int ldb, org.netlib.util.intW info) {
    f2j.strtrs(uplo, trans, diag, n, nrhs, a, 0, lda, b, 0, ldb, info);
  }

  public void strtrs(String uplo, String trans, String diag, int n, int nrhs, float[] a, int _a_offset, int lda, float[] b, int _b_offset, int ldb, org.netlib.util.intW info) {
    f2j.strtrs(uplo, trans, diag, n, nrhs, a, _a_offset, lda, b, _b_offset, ldb, info);
  }

  public void stzrqf(int m, int n, float[] a, int lda, float[] tau, org.netlib.util.intW info) {
    f2j.stzrqf(m, n, a, 0, lda, tau, 0, info);
  }

  public void stzrqf(int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, org.netlib.util.intW info) {
    f2j.stzrqf(m, n, a, _a_offset, lda, tau, _tau_offset, info);
  }

  public void stzrzf(int m, int n, float[] a, int lda, float[] tau, float[] work, int lwork, org.netlib.util.intW info) {
    f2j.stzrzf(m, n, a, 0, lda, tau, 0, work, 0, lwork, info);
  }

  public void stzrzf(int m, int n, float[] a, int _a_offset, int lda, float[] tau, int _tau_offset, float[] work, int _work_offset, int lwork, org.netlib.util.intW info) {
    f2j.stzrzf(m, n, a, _a_offset, lda, tau, _tau_offset, work, _work_offset, lwork, info);
  }

  public double dlamch(String cmach) {
    return f2j.dlamch(cmach);
  }

  public void dlamc1(org.netlib.util.intW beta, org.netlib.util.intW t, org.netlib.util.booleanW rnd, org.netlib.util.booleanW ieee1) {
    f2j.dlamc1(beta, t, rnd, ieee1);
  }

  public void dlamc2(org.netlib.util.intW beta, org.netlib.util.intW t, org.netlib.util.booleanW rnd, org.netlib.util.doubleW eps, org.netlib.util.intW emin, org.netlib.util.doubleW rmin, org.netlib.util.intW emax, org.netlib.util.doubleW rmax) {
    f2j.dlamc2(beta, t, rnd, eps, emin, rmin, emax, rmax);
  }

  public double dlamc3(double a, double b) {
    return f2j.dlamc3(a, b);
  }

  public void dlamc4(org.netlib.util.intW emin, double start, int base) {
    f2j.dlamc4(emin, start, base);
  }

  public void dlamc5(int beta, int p, int emin, boolean ieee, org.netlib.util.intW emax, org.netlib.util.doubleW rmax) {
    f2j.dlamc5(beta, p, emin, ieee, emax, rmax);
  }

  public double dsecnd() {
    return f2j.dsecnd();
  }

  public boolean lsame(String ca, String cb) {
    return f2j.lsame(ca, cb);
  }

  public float second() {
    return f2j.second();
  }

  public float slamch(String cmach) {
    return f2j.slamch(cmach);
  }

  public void slamc1(org.netlib.util.intW beta, org.netlib.util.intW t, org.netlib.util.booleanW rnd, org.netlib.util.booleanW ieee1) {
    f2j.slamc1(beta, t, rnd, ieee1);
  }

  public void slamc2(org.netlib.util.intW beta, org.netlib.util.intW t, org.netlib.util.booleanW rnd, org.netlib.util.floatW eps, org.netlib.util.intW emin, org.netlib.util.floatW rmin, org.netlib.util.intW emax, org.netlib.util.floatW rmax) {
    f2j.slamc2(beta, t, rnd, eps, emin, rmin, emax, rmax);
  }

  public float slamc3(float a, float b) {
    return f2j.slamc3(a, b);
  }

  public void slamc4(org.netlib.util.intW emin, float start, int base) {
    f2j.slamc4(emin, start, base);
  }

  public void slamc5(int beta, int p, int emin, boolean ieee, org.netlib.util.intW emax, org.netlib.util.floatW rmax) {
    f2j.slamc5(beta, p, emin, ieee, emax, rmax);
  }
}
