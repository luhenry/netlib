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

#include <stdlib.h>
#include <string.h>
#include <dlfcn.h>

#include "dev_ludovic_netlib_lapack_JNILAPACK.h"

#define UNUSED __attribute__((unused))

#define TRUE 1
#define FALSE 0

static jfieldID booleanW_val_fieldID;
static jfieldID intW_val_fieldID;
static jfieldID floatW_val_fieldID;
static jfieldID doubleW_val_fieldID;
static jfieldID StringW_val_fieldID;

static void *lapack;

static void throwOOM(JNIEnv *env) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dbdsdc_)(const char *uplo, char *compq, int *n, double *d, double *e, double *u, int *ldu, double *vt, int *ldvt, double *q, int * iq, double *work, int * iwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dbdsdcK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring compq, jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray u, jint offsetu, jint ldu, jdoubleArray vt, jint offsetvt, jint ldvt, jdoubleArray q, jint offsetq, jintArray iq, jint offsetiq, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dbdsqr_)(const char *uplo, int *n, int *ncvt, int *nru, int *ncc, double *d, double *e, double *vt, int *ldvt, double *u, int *ldu, double *c, int *Ldc, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dbdsqrK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint ncvt, jint nru, jint ncc, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray vt, jint offsetvt, jint ldvt, jdoubleArray u, jint offsetu, jint ldu, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ddisna_)(char *job, int *m, int *n, double *d, double *sep, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ddisnaK(JNIEnv *env, UNUSED jobject obj,
    jstring job, jint m, jint n, jdoubleArray d, jint offsetd, jdoubleArray sep, jint offsetsep, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgbbrd_)(char *vect, int *m, int *n, int *ncc, int *kl, int *ku, double *ab, int *ldab, double *d, double *e, double *q, int *ldq, double *pt, int *ldpt, double *c, int *Ldc, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgbbrdK(JNIEnv *env, UNUSED jobject obj,
    jstring vect, jint m, jint n, jint ncc, jint kl, jint ku, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray pt, jint offsetpt, jint ldpt, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgbcon_)(char *norm, int *n, int *kl, int *ku, double *ab, int *ldab, int * ipiv, double *anorm, /*FIXME*/void *rcond, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgbconK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jint n, jint kl, jint ku, jdoubleArray ab, jint offsetab, jint ldab, jintArray ipiv, jint offsetipiv, jdouble anorm, jobject rcond, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgbequ_)(int *m, int *n, int *kl, int *ku, double *ab, int *ldab, double *r, double *c, /*FIXME*/void *rowcnd, /*FIXME*/void *colcnd, /*FIXME*/void *amax, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgbequK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint kl, jint ku, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray r, jint offsetr, jdoubleArray c, jint offsetc, jobject rowcnd, jobject colcnd, jobject amax, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgbrfs_)(char *trans, int *n, int *kl, int *ku, int *nrhs, double *ab, int *ldab, double *afb, int *ldafb, int * ipiv, double *b, int *ldb, double *x, int *ldx, double *ferr, double *berr, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgbrfsK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint n, jint kl, jint ku, jint nrhs, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray afb, jint offsetafb, jint ldafb, jintArray ipiv, jint offsetipiv, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgbsv_)(int *n, int *kl, int *ku, int *nrhs, double *ab, int *ldab, int * ipiv, double *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgbsvK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint kl, jint ku, jint nrhs, jdoubleArray ab, jint offsetab, jint ldab, jintArray ipiv, jint offsetipiv, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgbsvx_)(char *fact, char *trans, int *n, int *kl, int *ku, int *nrhs, double *ab, int *ldab, double *afb, int *ldafb, int * ipiv, jstring *equed, double *r, double *c, double *b, int *ldb, double *x, int *ldx, /*FIXME*/void *rcond, double *ferr, double *berr, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgbsvxK(JNIEnv *env, UNUSED jobject obj,
    jstring fact, jstring trans, jint n, jint kl, jint ku, jint nrhs, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray afb, jint offsetafb, jint ldafb, jintArray ipiv, jint offsetipiv, jobject equed, jdoubleArray r, jint offsetr, jdoubleArray c, jint offsetc, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jobject rcond, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgbtf2_)(int *m, int *n, int *kl, int *ku, double *ab, int *ldab, int * ipiv, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgbtf2K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint kl, jint ku, jdoubleArray ab, jint offsetab, jint ldab, jintArray ipiv, jint offsetipiv, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgbtrf_)(int *m, int *n, int *kl, int *ku, double *ab, int *ldab, int * ipiv, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgbtrfK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint kl, jint ku, jdoubleArray ab, jint offsetab, jint ldab, jintArray ipiv, jint offsetipiv, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgbtrs_)(char *trans, int *n, int *kl, int *ku, int *nrhs, double *ab, int *ldab, int * ipiv, double *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgbtrsK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint n, jint kl, jint ku, jint nrhs, jdoubleArray ab, jint offsetab, jint ldab, jintArray ipiv, jint offsetipiv, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgebak_)(char *job, char *side, int *n, int *ilo, int *ihi, double *scale, int *m, double *v, int *ldv, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgebakK(JNIEnv *env, UNUSED jobject obj,
    jstring job, jstring side, jint n, jint ilo, jint ihi, jdoubleArray scale, jint offsetscale, jint m, jdoubleArray v, jint offsetv, jint ldv, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgebal_)(char *job, int *n, double *a, int *lda, /*FIXME*/void *ilo, /*FIXME*/void *ihi, double *scale, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgebalK(JNIEnv *env, UNUSED jobject obj,
    jstring job, jint n, jdoubleArray a, jint offseta, jint lda, jobject ilo, jobject ihi, jdoubleArray scale, jint offsetscale, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgebd2_)(int *m, int *n, double *a, int *lda, double *d, double *e, double *tauq, double *taup, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgebd2K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray tauq, jint offsettauq, jdoubleArray taup, jint offsettaup, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgebrd_)(int *m, int *n, double *a, int *lda, double *d, double *e, double *tauq, double *taup, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgebrdK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray tauq, jint offsettauq, jdoubleArray taup, jint offsettaup, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgecon_)(char *norm, int *n, double *a, int *lda, double *anorm, /*FIXME*/void *rcond, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeconK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jint n, jdoubleArray a, jint offseta, jint lda, jdouble anorm, jobject rcond, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgeequ_)(int *m, int *n, double *a, int *lda, double *r, double *c, /*FIXME*/void *rowcnd, /*FIXME*/void *colcnd, /*FIXME*/void *amax, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeequK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray r, jint offsetr, jdoubleArray c, jint offsetc, jobject rowcnd, jobject colcnd, jobject amax, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgees_)(char *jobvs, char *sort, /*FIXME*/void *select, int *n, double *a, int *lda, /*FIXME*/void *sdim, double *wr, double *wi, double *vs, int *ldvs, double *work, int *lwork, int *bwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeesK(JNIEnv *env, UNUSED jobject obj,
    jstring jobvs, jstring sort, jobject select, jint n, jdoubleArray a, jint offseta, jint lda, jobject sdim, jdoubleArray wr, jint offsetwr, jdoubleArray wi, jint offsetwi, jdoubleArray vs, jint offsetvs, jint ldvs, jdoubleArray work, jint offsetwork, jint lwork, jbooleanArray bwork, jint offsetbwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgeesx_)(char *jobvs, char *sort, /*FIXME*/void *select, char *sense, int *n, double *a, int *lda, /*FIXME*/void *sdim, double *wr, double *wi, double *vs, int *ldvs, /*FIXME*/void *rconde, /*FIXME*/void *rcondv, double *work, int *lwork, int * iwork, int *liwork, int *bwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeesxK(JNIEnv *env, UNUSED jobject obj,
    jstring jobvs, jstring sort, jobject select, jstring sense, jint n, jdoubleArray a, jint offseta, jint lda, jobject sdim, jdoubleArray wr, jint offsetwr, jdoubleArray wi, jint offsetwi, jdoubleArray vs, jint offsetvs, jint ldvs, jobject rconde, jobject rcondv, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jbooleanArray bwork, jint offsetbwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgeev_)(char *jobvl, char *jobvr, int *n, double *a, int *lda, double *wr, double *wi, double *vl, int *ldvl, double *vr, int *ldvr, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeevK(JNIEnv *env, UNUSED jobject obj,
    jstring jobvl, jstring jobvr, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray wr, jint offsetwr, jdoubleArray wi, jint offsetwi, jdoubleArray vl, jint offsetvl, jint ldvl, jdoubleArray vr, jint offsetvr, jint ldvr, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgeevx_)(char *balanc, char *jobvl, char *jobvr, char *sense, int *n, double *a, int *lda, double *wr, double *wi, double *vl, int *ldvl, double *vr, int *ldvr, /*FIXME*/void *ilo, /*FIXME*/void *ihi, double *scale, /*FIXME*/void *abnrm, double *rconde, double *rcondv, double *work, int *lwork, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeevxK(JNIEnv *env, UNUSED jobject obj,
    jstring balanc, jstring jobvl, jstring jobvr, jstring sense, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray wr, jint offsetwr, jdoubleArray wi, jint offsetwi, jdoubleArray vl, jint offsetvl, jint ldvl, jdoubleArray vr, jint offsetvr, jint ldvr, jobject ilo, jobject ihi, jdoubleArray scale, jint offsetscale, jobject abnrm, jdoubleArray rconde, jint offsetrconde, jdoubleArray rcondv, jint offsetrcondv, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgegs_)(char *jobvsl, char *jobvsr, int *n, double *a, int *lda, double *b, int *ldb, double *alphar, double *alphai, double *beta, double *vsl, int *ldvsl, double *vsr, int *ldvsr, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgegsK(JNIEnv *env, UNUSED jobject obj,
    jstring jobvsl, jstring jobvsr, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray alphar, jint offsetalphar, jdoubleArray alphai, jint offsetalphai, jdoubleArray beta, jint offsetbeta, jdoubleArray vsl, jint offsetvsl, jint ldvsl, jdoubleArray vsr, jint offsetvsr, jint ldvsr, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgegv_)(char *jobvl, char *jobvr, int *n, double *a, int *lda, double *b, int *ldb, double *alphar, double *alphai, double *beta, double *vl, int *ldvl, double *vr, int *ldvr, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgegvK(JNIEnv *env, UNUSED jobject obj,
    jstring jobvl, jstring jobvr, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray alphar, jint offsetalphar, jdoubleArray alphai, jint offsetalphai, jdoubleArray beta, jint offsetbeta, jdoubleArray vl, jint offsetvl, jint ldvl, jdoubleArray vr, jint offsetvr, jint ldvr, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgehd2_)(int *n, int *ilo, int *ihi, double *a, int *lda, double *tau, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgehd2K(JNIEnv *env, UNUSED jobject obj,
    jint n, jint ilo, jint ihi, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgehrd_)(int *n, int *ilo, int *ihi, double *a, int *lda, double *tau, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgehrdK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint ilo, jint ihi, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgelq2_)(int *m, int *n, double *a, int *lda, double *tau, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgelq2K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgelqf_)(int *m, int *n, double *a, int *lda, double *tau, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgelqfK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgels_)(char *trans, int *m, int *n, int *nrhs, double *a, int *lda, double *b, int *ldb, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgelsK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint m, jint n, jint nrhs, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgelsd_)(int *m, int *n, int *nrhs, double *a, int *lda, double *b, int *ldb, double *s, double *rcond, /*FIXME*/void *rank, double *work, int *lwork, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgelsdK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint nrhs, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray s, jint offsets, jdouble rcond, jobject rank, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgelss_)(int *m, int *n, int *nrhs, double *a, int *lda, double *b, int *ldb, double *s, double *rcond, /*FIXME*/void *rank, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgelssK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint nrhs, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray s, jint offsets, jdouble rcond, jobject rank, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgelsx_)(int *m, int *n, int *nrhs, double *a, int *lda, double *b, int *ldb, int * jpvt, double *rcond, /*FIXME*/void *rank, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgelsxK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint nrhs, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jintArray jpvt, jint offsetjpvt, jdouble rcond, jobject rank, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgelsy_)(int *m, int *n, int *nrhs, double *a, int *lda, double *b, int *ldb, int * jpvt, double *rcond, /*FIXME*/void *rank, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgelsyK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint nrhs, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jintArray jpvt, jint offsetjpvt, jdouble rcond, jobject rank, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgeql2_)(int *m, int *n, double *a, int *lda, double *tau, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeql2K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgeqlf_)(int *m, int *n, double *a, int *lda, double *tau, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeqlfK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgeqp3_)(int *m, int *n, double *a, int *lda, int * jpvt, double *tau, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeqp3K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdoubleArray a, jint offseta, jint lda, jintArray jpvt, jint offsetjpvt, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgeqpf_)(int *m, int *n, double *a, int *lda, int * jpvt, double *tau, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeqpfK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdoubleArray a, jint offseta, jint lda, jintArray jpvt, jint offsetjpvt, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgeqr2_)(int *m, int *n, double *a, int *lda, double *tau, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeqr2K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgeqrf_)(int *m, int *n, double *a, int *lda, double *tau, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeqrfK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgerfs_)(char *trans, int *n, int *nrhs, double *a, int *lda, double *af, int *ldaf, int * ipiv, double *b, int *ldb, double *x, int *ldx, double *ferr, double *berr, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgerfsK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint n, jint nrhs, jdoubleArray a, jint offseta, jint lda, jdoubleArray af, jint offsetaf, jint ldaf, jintArray ipiv, jint offsetipiv, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgerq2_)(int *m, int *n, double *a, int *lda, double *tau, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgerq2K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgerqf_)(int *m, int *n, double *a, int *lda, double *tau, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgerqfK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgesc2_)(int *n, double *a, int *lda, double *rhs, int * ipiv, int * jpiv, /*FIXME*/void *scale);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgesc2K(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray rhs, jint offsetrhs, jintArray ipiv, jint offsetipiv, jintArray jpiv, jint offsetjpiv, jobject scale) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgesdd_)(char *jobz, int *m, int *n, double *a, int *lda, double *s, double *u, int *ldu, double *vt, int *ldvt, double *work, int *lwork, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgesddK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray s, jint offsets, jdoubleArray u, jint offsetu, jint ldu, jdoubleArray vt, jint offsetvt, jint ldvt, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgesv_)(int *n, int *nrhs, double *a, int *lda, int * ipiv, double *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgesvK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint nrhs, jdoubleArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgesvd_)(char *jobu, char *jobvt, int *m, int *n, double *a, int *lda, double *s, double *u, int *ldu, double *vt, int *ldvt, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgesvdK(JNIEnv *env, UNUSED jobject obj,
    jstring jobu, jstring jobvt, jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray s, jint offsets, jdoubleArray u, jint offsetu, jint ldu, jdoubleArray vt, jint offsetvt, jint ldvt, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgesvx_)(char *fact, char *trans, int *n, int *nrhs, double *a, int *lda, double *af, int *ldaf, int * ipiv, jstring *equed, double *r, double *c, double *b, int *ldb, double *x, int *ldx, /*FIXME*/void *rcond, double *ferr, double *berr, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgesvxK(JNIEnv *env, UNUSED jobject obj,
    jstring fact, jstring trans, jint n, jint nrhs, jdoubleArray a, jint offseta, jint lda, jdoubleArray af, jint offsetaf, jint ldaf, jintArray ipiv, jint offsetipiv, jobject equed, jdoubleArray r, jint offsetr, jdoubleArray c, jint offsetc, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jobject rcond, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgetc2_)(int *n, double *a, int *lda, int * ipiv, int * jpiv, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgetc2K(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jintArray jpiv, jint offsetjpiv, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgetf2_)(int *m, int *n, double *a, int *lda, int * ipiv, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgetf2K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdoubleArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgetrf_)(int *m, int *n, double *a, int *lda, int * ipiv, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgetrfK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdoubleArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgetri_)(int *n, double *a, int *lda, int * ipiv, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgetriK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgetrs_)(char *trans, int *n, int *nrhs, double *a, int *lda, int * ipiv, double *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgetrsK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint n, jint nrhs, jdoubleArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dggbak_)(char *job, char *side, int *n, int *ilo, int *ihi, double *lscale, double *rscale, int *m, double *v, int *ldv, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggbakK(JNIEnv *env, UNUSED jobject obj,
    jstring job, jstring side, jint n, jint ilo, jint ihi, jdoubleArray lscale, jint offsetlscale, jdoubleArray rscale, jint offsetrscale, jint m, jdoubleArray v, jint offsetv, jint ldv, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dggbal_)(char *job, int *n, double *a, int *lda, double *b, int *ldb, /*FIXME*/void *ilo, /*FIXME*/void *ihi, double *lscale, double *rscale, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggbalK(JNIEnv *env, UNUSED jobject obj,
    jstring job, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jobject ilo, jobject ihi, jdoubleArray lscale, jint offsetlscale, jdoubleArray rscale, jint offsetrscale, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgges_)(char *jobvsl, char *jobvsr, char *sort, /*FIXME*/void *selctg, int *n, double *a, int *lda, double *b, int *ldb, /*FIXME*/void *sdim, double *alphar, double *alphai, double *beta, double *vsl, int *ldvsl, double *vsr, int *ldvsr, double *work, int *lwork, int *bwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggesK(JNIEnv *env, UNUSED jobject obj,
    jstring jobvsl, jstring jobvsr, jstring sort, jobject selctg, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jobject sdim, jdoubleArray alphar, jint offsetalphar, jdoubleArray alphai, jint offsetalphai, jdoubleArray beta, jint offsetbeta, jdoubleArray vsl, jint offsetvsl, jint ldvsl, jdoubleArray vsr, jint offsetvsr, jint ldvsr, jdoubleArray work, jint offsetwork, jint lwork, jbooleanArray bwork, jint offsetbwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dggesx_)(char *jobvsl, char *jobvsr, char *sort, /*FIXME*/void *selctg, char *sense, int *n, double *a, int *lda, double *b, int *ldb, /*FIXME*/void *sdim, double *alphar, double *alphai, double *beta, double *vsl, int *ldvsl, double *vsr, int *ldvsr, double *rconde, double *rcondv, double *work, int *lwork, int * iwork, int *liwork, int *bwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggesxK(JNIEnv *env, UNUSED jobject obj,
    jstring jobvsl, jstring jobvsr, jstring sort, jobject selctg, jstring sense, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jobject sdim, jdoubleArray alphar, jint offsetalphar, jdoubleArray alphai, jint offsetalphai, jdoubleArray beta, jint offsetbeta, jdoubleArray vsl, jint offsetvsl, jint ldvsl, jdoubleArray vsr, jint offsetvsr, jint ldvsr, jdoubleArray rconde, jint offsetrconde, jdoubleArray rcondv, jint offsetrcondv, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jbooleanArray bwork, jint offsetbwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dggev_)(char *jobvl, char *jobvr, int *n, double *a, int *lda, double *b, int *ldb, double *alphar, double *alphai, double *beta, double *vl, int *ldvl, double *vr, int *ldvr, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggevK(JNIEnv *env, UNUSED jobject obj,
    jstring jobvl, jstring jobvr, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray alphar, jint offsetalphar, jdoubleArray alphai, jint offsetalphai, jdoubleArray beta, jint offsetbeta, jdoubleArray vl, jint offsetvl, jint ldvl, jdoubleArray vr, jint offsetvr, jint ldvr, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dggevx_)(char *balanc, char *jobvl, char *jobvr, char *sense, int *n, double *a, int *lda, double *b, int *ldb, double *alphar, double *alphai, double *beta, double *vl, int *ldvl, double *vr, int *ldvr, /*FIXME*/void *ilo, /*FIXME*/void *ihi, double *lscale, double *rscale, /*FIXME*/void *abnrm, /*FIXME*/void *bbnrm, double *rconde, double *rcondv, double *work, int *lwork, int * iwork, int *bwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggevxK(JNIEnv *env, UNUSED jobject obj,
    jstring balanc, jstring jobvl, jstring jobvr, jstring sense, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray alphar, jint offsetalphar, jdoubleArray alphai, jint offsetalphai, jdoubleArray beta, jint offsetbeta, jdoubleArray vl, jint offsetvl, jint ldvl, jdoubleArray vr, jint offsetvr, jint ldvr, jobject ilo, jobject ihi, jdoubleArray lscale, jint offsetlscale, jdoubleArray rscale, jint offsetrscale, jobject abnrm, jobject bbnrm, jdoubleArray rconde, jint offsetrconde, jdoubleArray rcondv, jint offsetrcondv, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jbooleanArray bwork, jint offsetbwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dggglm_)(int *n, int *m, int *p, double *a, int *lda, double *b, int *ldb, double *d, double *x, double *y, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggglmK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint m, jint p, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray d, jint offsetd, jdoubleArray x, jint offsetx, jdoubleArray y, jint offsety, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgghrd_)(char *compq, char *compz, int *n, int *ilo, int *ihi, double *a, int *lda, double *b, int *ldb, double *q, int *ldq, double *z, int *ldz, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgghrdK(JNIEnv *env, UNUSED jobject obj,
    jstring compq, jstring compz, jint n, jint ilo, jint ihi, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray z, jint offsetz, jint ldz, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgglse_)(int *m, int *n, int *p, double *a, int *lda, double *b, int *ldb, double *c, double *d, double *x, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgglseK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint p, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray c, jint offsetc, jdoubleArray d, jint offsetd, jdoubleArray x, jint offsetx, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dggqrf_)(int *n, int *m, int *p, double *a, int *lda, double *taua, double *b, int *ldb, double *taub, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggqrfK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint m, jint p, jdoubleArray a, jint offseta, jint lda, jdoubleArray taua, jint offsettaua, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray taub, jint offsettaub, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dggrqf_)(int *m, int *p, int *n, double *a, int *lda, double *taua, double *b, int *ldb, double *taub, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggrqfK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint p, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray taua, jint offsettaua, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray taub, jint offsettaub, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dggsvd_)(char *jobu, char *jobv, char *jobq, int *m, int *n, int *p, /*FIXME*/void *k, /*FIXME*/void *l, double *a, int *lda, double *b, int *ldb, double *alpha, double *beta, double *u, int *ldu, double *v, int *ldv, double *q, int *ldq, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggsvdK(JNIEnv *env, UNUSED jobject obj,
    jstring jobu, jstring jobv, jstring jobq, jint m, jint n, jint p, jobject k, jobject l, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray alpha, jint offsetalpha, jdoubleArray beta, jint offsetbeta, jdoubleArray u, jint offsetu, jint ldu, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dggsvp_)(char *jobu, char *jobv, char *jobq, int *m, int *p, int *n, double *a, int *lda, double *b, int *ldb, double *tola, double *tolb, /*FIXME*/void *k, /*FIXME*/void *l, double *u, int *ldu, double *v, int *ldv, double *q, int *ldq, int * iwork, double *tau, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggsvpK(JNIEnv *env, UNUSED jobject obj,
    jstring jobu, jstring jobv, jstring jobq, jint m, jint p, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdouble tola, jdouble tolb, jobject k, jobject l, jdoubleArray u, jint offsetu, jint ldu, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray q, jint offsetq, jint ldq, jintArray iwork, jint offsetiwork, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgtcon_)(char *norm, int *n, double *dl, double *d, double *du, double *du22, int * ipiv, double *anorm, /*FIXME*/void *rcond, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgtconK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jint n, jdoubleArray dl, jint offsetdl, jdoubleArray d, jint offsetd, jdoubleArray du, jint offsetdu, jdoubleArray du2, jint offsetdu2, jintArray ipiv, jint offsetipiv, jdouble anorm, jobject rcond, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgtrfs_)(char *trans, int *n, int *nrhs, double *dl, double *d, double *du, double *dlf, double *df, double *duf, double *du22, int * ipiv, double *b, int *ldb, double *x, int *ldx, double *ferr, double *berr, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgtrfsK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint n, jint nrhs, jdoubleArray dl, jint offsetdl, jdoubleArray d, jint offsetd, jdoubleArray du, jint offsetdu, jdoubleArray dlf, jint offsetdlf, jdoubleArray df, jint offsetdf, jdoubleArray duf, jint offsetduf, jdoubleArray du2, jint offsetdu2, jintArray ipiv, jint offsetipiv, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgtsv_)(int *n, int *nrhs, double *dl, double *d, double *du, double *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgtsvK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint nrhs, jdoubleArray dl, jint offsetdl, jdoubleArray d, jint offsetd, jdoubleArray du, jint offsetdu, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgtsvx_)(char *fact, char *trans, int *n, int *nrhs, double *dl, double *d, double *du, double *dlf, double *df, double *duf, double *du22, int * ipiv, double *b, int *ldb, double *x, int *ldx, /*FIXME*/void *rcond, double *ferr, double *berr, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgtsvxK(JNIEnv *env, UNUSED jobject obj,
    jstring fact, jstring trans, jint n, jint nrhs, jdoubleArray dl, jint offsetdl, jdoubleArray d, jint offsetd, jdoubleArray du, jint offsetdu, jdoubleArray dlf, jint offsetdlf, jdoubleArray df, jint offsetdf, jdoubleArray duf, jint offsetduf, jdoubleArray du2, jint offsetdu2, jintArray ipiv, jint offsetipiv, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jobject rcond, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgttrf_)(int *n, double *dl, double *d, double *du, double *du22, int * ipiv, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgttrfK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray dl, jint offsetdl, jdoubleArray d, jint offsetd, jdoubleArray du, jint offsetdu, jdoubleArray du2, jint offsetdu2, jintArray ipiv, jint offsetipiv, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgttrs_)(char *trans, int *n, int *nrhs, double *dl, double *d, double *du, double *du22, int * ipiv, double *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgttrsK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint n, jint nrhs, jdoubleArray dl, jint offsetdl, jdoubleArray d, jint offsetd, jdoubleArray du, jint offsetdu, jdoubleArray du2, jint offsetdu2, jintArray ipiv, jint offsetipiv, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgtts2_)(int *itrans, int *n, int *nrhs, double *dl, double *d, double *du, double *du22, int * ipiv, double *b, int *ldb);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgtts2K(JNIEnv *env, UNUSED jobject obj,
    jint itrans, jint n, jint nrhs, jdoubleArray dl, jint offsetdl, jdoubleArray d, jint offsetd, jdoubleArray du, jint offsetdu, jdoubleArray du2, jint offsetdu2, jintArray ipiv, jint offsetipiv, jdoubleArray b, jint offsetb, jint ldb) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dhgeqz_)(char *job, char *compq, char *compz, int *n, int *ilo, int *ihi, double *h, int *ldh, double *t, int *ldt, double *alphar, double *alphai, double *beta, double *q, int *ldq, double *z, int *ldz, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dhgeqzK(JNIEnv *env, UNUSED jobject obj,
    jstring job, jstring compq, jstring compz, jint n, jint ilo, jint ihi, jdoubleArray h, jint offseth, jint ldh, jdoubleArray t, jint offsett, jint ldt, jdoubleArray alphar, jint offsetalphar, jdoubleArray alphai, jint offsetalphai, jdoubleArray beta, jint offsetbeta, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dhsein_)(char *side, char *eigsrc, char *initv, int *select, int *n, double *h, int *ldh, double *wr, double *wi, double *vl, int *ldvl, double *vr, int *ldvr, int *mm, /*FIXME*/void *m, double *work, int * ifaill, int * ifailr, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dhseinK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring eigsrc, jstring initv, jbooleanArray select, jint offsetselect, jint n, jdoubleArray h, jint offseth, jint ldh, jdoubleArray wr, jint offsetwr, jdoubleArray wi, jint offsetwi, jdoubleArray vl, jint offsetvl, jint ldvl, jdoubleArray vr, jint offsetvr, jint ldvr, jint mm, jobject m, jdoubleArray work, jint offsetwork, jintArray ifaill, jint offsetifaill, jintArray ifailr, jint offsetifailr, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dhseqr_)(char *job, char *compz, int *n, int *ilo, int *ihi, double *h, int *ldh, double *wr, double *wi, double *z, int *ldz, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dhseqrK(JNIEnv *env, UNUSED jobject obj,
    jstring job, jstring compz, jint n, jint ilo, jint ihi, jdoubleArray h, jint offseth, jint ldh, jdoubleArray wr, jint offsetwr, jdoubleArray wi, jint offsetwi, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static int *(*disnan_)(double *din);

jboolean Java_dev_ludovic_netlib_lapack_JNILAPACK_disnanK(JNIEnv *env, UNUSED jobject obj,
    jdouble din) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static void (*dlabad_)(/*FIXME*/void *small, /*FIXME*/void *large);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlabadK(JNIEnv *env, UNUSED jobject obj,
    jobject small, jobject large) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlabrd_)(int *m, int *n, int *nb, double *a, int *lda, double *d, double *e, double *tauq, double *taup, double *x, int *ldx, double *y, int *ldy);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlabrdK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint nb, jdoubleArray a, jint offseta, jint lda, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray tauq, jint offsettauq, jdoubleArray taup, jint offsettaup, jdoubleArray x, jint offsetx, jint ldx, jdoubleArray y, jint offsety, jint ldy) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlacn2_)(int *n, double *v, double *x, int * isgn, /*FIXME*/void *est, /*FIXME*/void *kase, int * isave);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlacn2K(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray v, jint offsetv, jdoubleArray x, jint offsetx, jintArray isgn, jint offsetisgn, jobject est, jobject kase, jintArray isave, jint offsetisave) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlacon_)(int *n, double *v, double *x, int * isgn, /*FIXME*/void *est, /*FIXME*/void *kase);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaconK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray v, jint offsetv, jdoubleArray x, jint offsetx, jintArray isgn, jint offsetisgn, jobject est, jobject kase) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlacpy_)(const char *uplo, int *m, int *n, double *a, int *lda, double *b, int *ldb);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlacpyK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dladiv_)(double *a, double *b, double *c, double *d, /*FIXME*/void *p, /*FIXME*/void *q);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dladivK(JNIEnv *env, UNUSED jobject obj,
    jdouble a, jdouble b, jdouble c, jdouble d, jobject p, jobject q) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlae2_)(double *a, double *b, double *c, /*FIXME*/void *rt1, /*FIXME*/void *rt2);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlae2K(JNIEnv *env, UNUSED jobject obj,
    jdouble a, jdouble b, jdouble c, jobject rt1, jobject rt2) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaebz_)(int *ijob, int *nitmax, int *n, int *mmax, int *minp, int *nbmin, double *abstol, double *reltol, double *pivmin, double *d, double *e, double *e22, int * nval, double *ab, double *c, /*FIXME*/void *mout, int * nab, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaebzK(JNIEnv *env, UNUSED jobject obj,
    jint ijob, jint nitmax, jint n, jint mmax, jint minp, jint nbmin, jdouble abstol, jdouble reltol, jdouble pivmin, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray e2, jint offsete2, jintArray nval, jint offsetnval, jdoubleArray ab, jint offsetab, jdoubleArray c, jint offsetc, jobject mout, jintArray nab, jint offsetnab, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaed0_)(int *icompq, int *qsiz, int *n, double *d, double *e, double *q, int *ldq, double *qstore, int *ldqs, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaed0K(JNIEnv *env, UNUSED jobject obj,
    jint icompq, jint qsiz, jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray qstore, jint offsetqstore, jint ldqs, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaed1_)(int *n, double *d, double *q, int *ldq, int * indxq, /*FIXME*/void *rho, int *cutpnt, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaed1K(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray d, jint offsetd, jdoubleArray q, jint offsetq, jint ldq, jintArray indxq, jint offsetindxq, jobject rho, jint cutpnt, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaed2_)(/*FIXME*/void *k, int *n, int *n1, double *d, double *q, int *ldq, int * indxq, /*FIXME*/void *rho, double *z, double *dlamda, double *w, double *q22, int * indx, int * indxc, int * indxp, int * coltyp, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaed2K(JNIEnv *env, UNUSED jobject obj,
    jobject k, jint n, jint n1, jdoubleArray d, jint offsetd, jdoubleArray q, jint offsetq, jint ldq, jintArray indxq, jint offsetindxq, jobject rho, jdoubleArray z, jint offsetz, jdoubleArray dlamda, jint offsetdlamda, jdoubleArray w, jint offsetw, jdoubleArray q2, jint offsetq2, jintArray indx, jint offsetindx, jintArray indxc, jint offsetindxc, jintArray indxp, jint offsetindxp, jintArray coltyp, jint offsetcoltyp, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaed3_)(int *k, int *n, int *n1, double *d, double *q, int *ldq, double *rho, double *dlamda, double *q22, int * indx, int * ctot, double *w, double *s, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaed3K(JNIEnv *env, UNUSED jobject obj,
    jint k, jint n, jint n1, jdoubleArray d, jint offsetd, jdoubleArray q, jint offsetq, jint ldq, jdouble rho, jdoubleArray dlamda, jint offsetdlamda, jdoubleArray q2, jint offsetq2, jintArray indx, jint offsetindx, jintArray ctot, jint offsetctot, jdoubleArray w, jint offsetw, jdoubleArray s, jint offsets, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaed4_)(int *n, int *i, double *d, double *z, double *delta, double *rho, /*FIXME*/void *dlam, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaed4K(JNIEnv *env, UNUSED jobject obj,
    jint n, jint i, jdoubleArray d, jint offsetd, jdoubleArray z, jint offsetz, jdoubleArray delta, jint offsetdelta, jdouble rho, jobject dlam, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaed5_)(int *i, double *d, double *z, double *delta, double *rho, /*FIXME*/void *dlam);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaed5K(JNIEnv *env, UNUSED jobject obj,
    jint i, jdoubleArray d, jint offsetd, jdoubleArray z, jint offsetz, jdoubleArray delta, jint offsetdelta, jdouble rho, jobject dlam) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaed6_)(int *kniter, int *orgati, double *rho, double *d, double *z, double *finit, /*FIXME*/void *tau, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaed6K(JNIEnv *env, UNUSED jobject obj,
    jint kniter, jboolean orgati, jdouble rho, jdoubleArray d, jint offsetd, jdoubleArray z, jint offsetz, jdouble finit, jobject tau, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaed7_)(int *icompq, int *n, int *qsiz, int *tlvls, int *curlvl, int *curpbm, double *d, double *q, int *ldq, int * indxq, /*FIXME*/void *rho, int *cutpnt, double *qstore, int * qptr, int * prmptr, int * perm, int * givptr, int * givcol, double *givnum, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaed7K(JNIEnv *env, UNUSED jobject obj,
    jint icompq, jint n, jint qsiz, jint tlvls, jint curlvl, jint curpbm, jdoubleArray d, jint offsetd, jdoubleArray q, jint offsetq, jint ldq, jintArray indxq, jint offsetindxq, jobject rho, jint cutpnt, jdoubleArray qstore, jint offsetqstore, jintArray qptr, jint offsetqptr, jintArray prmptr, jint offsetprmptr, jintArray perm, jint offsetperm, jintArray givptr, jint offsetgivptr, jintArray givcol, jint offsetgivcol, jdoubleArray givnum, jint offsetgivnum, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaed8_)(int *icompq, /*FIXME*/void *k, int *n, int *qsiz, double *d, double *q, int *ldq, int * indxq, /*FIXME*/void *rho, int *cutpnt, double *z, double *dlamda, double *q22, int *ldq2, double *w, int * perm, /*FIXME*/void *givptr, int * givcol, double *givnum, int * indxp, int * indx, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaed8K(JNIEnv *env, UNUSED jobject obj,
    jint icompq, jobject k, jint n, jint qsiz, jdoubleArray d, jint offsetd, jdoubleArray q, jint offsetq, jint ldq, jintArray indxq, jint offsetindxq, jobject rho, jint cutpnt, jdoubleArray z, jint offsetz, jdoubleArray dlamda, jint offsetdlamda, jdoubleArray q2, jint offsetq2, jint ldq2, jdoubleArray w, jint offsetw, jintArray perm, jint offsetperm, jobject givptr, jintArray givcol, jint offsetgivcol, jdoubleArray givnum, jint offsetgivnum, jintArray indxp, jint offsetindxp, jintArray indx, jint offsetindx, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaed9_)(int *k, int *kstart, int *kstop, int *n, double *d, double *q, int *ldq, double *rho, double *dlamda, double *w, double *s, int *lds, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaed9K(JNIEnv *env, UNUSED jobject obj,
    jint k, jint kstart, jint kstop, jint n, jdoubleArray d, jint offsetd, jdoubleArray q, jint offsetq, jint ldq, jdouble rho, jdoubleArray dlamda, jint offsetdlamda, jdoubleArray w, jint offsetw, jdoubleArray s, jint offsets, jint lds, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaeda_)(int *n, int *tlvls, int *curlvl, int *curpbm, int * prmptr, int * perm, int * givptr, int * givcol, double *givnum, double *q, int * qptr, double *z, double *ztemp, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaedaK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint tlvls, jint curlvl, jint curpbm, jintArray prmptr, jint offsetprmptr, jintArray perm, jint offsetperm, jintArray givptr, jint offsetgivptr, jintArray givcol, jint offsetgivcol, jdoubleArray givnum, jint offsetgivnum, jdoubleArray q, jint offsetq, jintArray qptr, jint offsetqptr, jdoubleArray z, jint offsetz, jdoubleArray ztemp, jint offsetztemp, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaein_)(int *rightv, int *noinit, int *n, double *h, int *ldh, double *wr, double *wi, double *vr, double *vi, double *b, int *ldb, double *work, double *eps3, double *smlnum, double *bignum, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaeinK(JNIEnv *env, UNUSED jobject obj,
    jboolean rightv, jboolean noinit, jint n, jdoubleArray h, jint offseth, jint ldh, jdouble wr, jdouble wi, jdoubleArray vr, jint offsetvr, jdoubleArray vi, jint offsetvi, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray work, jint offsetwork, jdouble eps3, jdouble smlnum, jdouble bignum, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaev2_)(double *a, double *b, double *c, /*FIXME*/void *rt1, /*FIXME*/void *rt2, /*FIXME*/void *cs1, /*FIXME*/void *sn1);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaev2K(JNIEnv *env, UNUSED jobject obj,
    jdouble a, jdouble b, jdouble c, jobject rt1, jobject rt2, jobject cs1, jobject sn1) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaexc_)(int *wantq, int *n, double *t, int *ldt, double *q, int *ldq, int *j1, int *n1, int *n2, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaexcK(JNIEnv *env, UNUSED jobject obj,
    jboolean wantq, jint n, jdoubleArray t, jint offsett, jint ldt, jdoubleArray q, jint offsetq, jint ldq, jint j1, jint n1, jint n2, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlag2_)(double *a, int *lda, double *b, int *ldb, double *safmin, /*FIXME*/void *scale1, /*FIXME*/void *scale2, /*FIXME*/void *wr1, /*FIXME*/void *wr2, /*FIXME*/void *wi);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlag2K(JNIEnv *env, UNUSED jobject obj,
    jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdouble safmin, jobject scale1, jobject scale2, jobject wr1, jobject wr2, jobject wi) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlag2s_)(int *m, int *n, double *a, int *lda, float *sa, int *ldsa, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlag2sK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdoubleArray a, jint offseta, jint lda, jfloatArray sa, jint offsetsa, jint ldsa, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlags2_)(int *upper, double *a1, double *a2, double *a3, double *b1, double *b2, double *b3, /*FIXME*/void *csu, /*FIXME*/void *snu, /*FIXME*/void *csv, /*FIXME*/void *snv, /*FIXME*/void *csq, /*FIXME*/void *snq);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlags2K(JNIEnv *env, UNUSED jobject obj,
    jboolean upper, jdouble a1, jdouble a2, jdouble a3, jdouble b1, jdouble b2, jdouble b3, jobject csu, jobject snu, jobject csv, jobject snv, jobject csq, jobject snq) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlagtf_)(int *n, double *a, double *lambda, double *b, double *c, double *tol, double *d, int * in, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlagtfK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray a, jint offseta, jdouble lambda, jdoubleArray b, jint offsetb, jdoubleArray c, jint offsetc, jdouble tol, jdoubleArray d, jint offsetd, jintArray in, jint offsetin, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlagtm_)(char *trans, int *n, int *nrhs, double *alpha, double *dl, double *d, double *du, double *x, int *ldx, double *beta, double *b, int *ldb);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlagtmK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint n, jint nrhs, jdouble alpha, jdoubleArray dl, jint offsetdl, jdoubleArray d, jint offsetd, jdoubleArray du, jint offsetdu, jdoubleArray x, jint offsetx, jint ldx, jdouble beta, jdoubleArray b, jint offsetb, jint ldb) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlagts_)(int *job, int *n, double *a, double *b, double *c, double *d, int * in, double *y, /*FIXME*/void *tol, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlagtsK(JNIEnv *env, UNUSED jobject obj,
    jint job, jint n, jdoubleArray a, jint offseta, jdoubleArray b, jint offsetb, jdoubleArray c, jint offsetc, jdoubleArray d, jint offsetd, jintArray in, jint offsetin, jdoubleArray y, jint offsety, jobject tol, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlagv2_)(double *a, int *lda, double *b, int *ldb, double *alphar, double *alphai, double *beta, /*FIXME*/void *csl, /*FIXME*/void *snl, /*FIXME*/void *csr, /*FIXME*/void *snr);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlagv2K(JNIEnv *env, UNUSED jobject obj,
    jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray alphar, jint offsetalphar, jdoubleArray alphai, jint offsetalphai, jdoubleArray beta, jint offsetbeta, jobject csl, jobject snl, jobject csr, jobject snr) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlahqr_)(int *wantt, int *wantz, int *n, int *ilo, int *ihi, double *h, int *ldh, double *wr, double *wi, int *iloz, int *ihiz, double *z, int *ldz, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlahqrK(JNIEnv *env, UNUSED jobject obj,
    jboolean wantt, jboolean wantz, jint n, jint ilo, jint ihi, jdoubleArray h, jint offseth, jint ldh, jdoubleArray wr, jint offsetwr, jdoubleArray wi, jint offsetwi, jint iloz, jint ihiz, jdoubleArray z, jint offsetz, jint ldz, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlahr2_)(int *n, int *k, int *nb, double *a, int *lda, double *tau, double *t, int *ldt, double *y, int *ldy);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlahr2K(JNIEnv *env, UNUSED jobject obj,
    jint n, jint k, jint nb, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray t, jint offsett, jint ldt, jdoubleArray y, jint offsety, jint ldy) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlahrd_)(int *n, int *k, int *nb, double *a, int *lda, double *tau, double *t, int *ldt, double *y, int *ldy);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlahrdK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint k, jint nb, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray t, jint offsett, jint ldt, jdoubleArray y, jint offsety, jint ldy) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaic1_)(int *job, int *j, double *x, double *sest, double *w, double *gamma, /*FIXME*/void *sestpr, /*FIXME*/void *s, /*FIXME*/void *c);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaic1K(JNIEnv *env, UNUSED jobject obj,
    jint job, jint j, jdoubleArray x, jint offsetx, jdouble sest, jdoubleArray w, jint offsetw, jdouble gamma, jobject sestpr, jobject s, jobject c) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static int *(*dlaisnan_)(double *din1, double *din2);

jboolean Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaisnanK(JNIEnv *env, UNUSED jobject obj,
    jdouble din1, jdouble din2) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static void (*dlaln2_)(int *ltrans, int *na, int *nw, double *smin, double *ca, double *a, int *lda, double *d1, double *d2, double *b, int *ldb, double *wr, double *wi, double *x, int *ldx, /*FIXME*/void *scale, /*FIXME*/void *xnorm, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaln2K(JNIEnv *env, UNUSED jobject obj,
    jboolean ltrans, jint na, jint nw, jdouble smin, jdouble ca, jdoubleArray a, jint offseta, jint lda, jdouble d1, jdouble d2, jdoubleArray b, jint offsetb, jint ldb, jdouble wr, jdouble wi, jdoubleArray x, jint offsetx, jint ldx, jobject scale, jobject xnorm, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlals0_)(int *icompq, int *nl, int *nr, int *sqre, int *nrhs, double *b, int *ldb, double *bx, int *ldbx, int * perm, int *givptr, int * givcol, int *ldgcol, double *givnum, int *ldgnum, double *poles, double *difl, double *difr, double *z, int *k, double *c, double *s, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlals0K(JNIEnv *env, UNUSED jobject obj,
    jint icompq, jint nl, jint nr, jint sqre, jint nrhs, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray bx, jint offsetbx, jint ldbx, jintArray perm, jint offsetperm, jint givptr, jintArray givcol, jint offsetgivcol, jint ldgcol, jdoubleArray givnum, jint offsetgivnum, jint ldgnum, jdoubleArray poles, jint offsetpoles, jdoubleArray difl, jint offsetdifl, jdoubleArray difr, jint offsetdifr, jdoubleArray z, jint offsetz, jint k, jdouble c, jdouble s, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlalsa_)(int *icompq, int *smlsiz, int *n, int *nrhs, double *b, int *ldb, double *bx, int *ldbx, double *u, int *ldu, double *vt, int * k, double *difl, double *difr, double *z, double *poles, int * givptr, int * givcol, int *ldgcol, int * perm, double *givnum, double *c, double *s, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlalsaK(JNIEnv *env, UNUSED jobject obj,
    jint icompq, jint smlsiz, jint n, jint nrhs, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray bx, jint offsetbx, jint ldbx, jdoubleArray u, jint offsetu, jint ldu, jdoubleArray vt, jint offsetvt, jintArray k, jint offsetk, jdoubleArray difl, jint offsetdifl, jdoubleArray difr, jint offsetdifr, jdoubleArray z, jint offsetz, jdoubleArray poles, jint offsetpoles, jintArray givptr, jint offsetgivptr, jintArray givcol, jint offsetgivcol, jint ldgcol, jintArray perm, jint offsetperm, jdoubleArray givnum, jint offsetgivnum, jdoubleArray c, jint offsetc, jdoubleArray s, jint offsets, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlalsd_)(const char *uplo, int *smlsiz, int *n, int *nrhs, double *d, double *e, double *b, int *ldb, double *rcond, /*FIXME*/void *rank, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlalsdK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint smlsiz, jint n, jint nrhs, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray b, jint offsetb, jint ldb, jdouble rcond, jobject rank, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlamrg_)(int *n1, int *n2, double *a, int *dtrd1, int *dtrd2, int * index);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlamrgK(JNIEnv *env, UNUSED jobject obj,
    jint n1, jint n2, jdoubleArray a, jint offseta, jint dtrd1, jint dtrd2, jintArray index, jint offsetindex) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static int (*dlaneg_)(int *n, double *d, int *offsetd, double *lld, int *offsetlld, double *sigma, double *pivmin, int *r);

jint Java_dev_ludovic_netlib_lapack_JNILAPACK_dlanegK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray d, jint offsetd, jdoubleArray lld, jint offsetlld, jdouble sigma, jdouble pivmin, jint r) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static double (*dlangb_)(char *norm, int *n, int *kl, int *ku, double *ab, int *offsetab, int *ldab, double *work, int *offsetwork);

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlangbK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jint n, jint kl, jint ku, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static double (*dlange_)(char *norm, int *m, int *n, double *a, int *offseta, int *lda, double *work, int *offsetwork);

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlangeK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static double (*dlangt_)(char *norm, int *n, double *dl, int *offsetdl, double *d, int *offsetd, double *du, int *offsetdu);

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlangtK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jint n, jdoubleArray dl, jint offsetdl, jdoubleArray d, jint offsetd, jdoubleArray du, jint offsetdu) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static double (*dlanhs_)(char *norm, int *n, double *a, int *offseta, int *lda, double *work, int *offsetwork);

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlanhsK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static double (*dlansb_)(char *norm, const char *uplo, int *n, int *k, double *ab, int *offsetab, int *ldab, double *work, int *offsetwork);

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlansbK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jstring uplo, jint n, jint k, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static double (*dlansp_)(char *norm, const char *uplo, int *n, double *ap, int *offsetap, double *work, int *offsetwork);

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlanspK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jstring uplo, jint n, jdoubleArray ap, jint offsetap, jdoubleArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static double (*dlanst_)(char *norm, int *n, double *d, int *offsetd, double *e, int *offsete);

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlanstK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static double (*dlansy_)(char *norm, const char *uplo, int *n, double *a, int *offseta, int *lda, double *work, int *offsetwork);

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlansyK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static double (*dlantb_)(char *norm, const char *uplo, char *diag, int *n, int *k, double *ab, int *offsetab, int *ldab, double *work, int *offsetwork);

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlantbK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jstring uplo, jstring diag, jint n, jint k, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static double (*dlantp_)(char *norm, const char *uplo, char *diag, int *n, double *ap, int *offsetap, double *work, int *offsetwork);

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlantpK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jstring uplo, jstring diag, jint n, jdoubleArray ap, jint offsetap, jdoubleArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static double (*dlantr_)(char *norm, const char *uplo, char *diag, int *m, int *n, double *a, int *offseta, int *lda, double *work, int *offsetwork);

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlantrK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jstring uplo, jstring diag, jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static void (*dlanv2_)(/*FIXME*/void *a, /*FIXME*/void *b, /*FIXME*/void *c, /*FIXME*/void *d, /*FIXME*/void *rt1r, /*FIXME*/void *rt1i, /*FIXME*/void *rt2r, /*FIXME*/void *rt2i, /*FIXME*/void *cs, /*FIXME*/void *sn);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlanv2K(JNIEnv *env, UNUSED jobject obj,
    jobject a, jobject b, jobject c, jobject d, jobject rt1r, jobject rt1i, jobject rt2r, jobject rt2i, jobject cs, jobject sn) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlapll_)(int *n, double *x, int *incx, double *y, int *incy, /*FIXME*/void *ssmin);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlapllK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy, jobject ssmin) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlapmt_)(int *forwrd, int *m, int *n, double *x, int *ldx, int * k);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlapmtK(JNIEnv *env, UNUSED jobject obj,
    jboolean forwrd, jint m, jint n, jdoubleArray x, jint offsetx, jint ldx, jintArray k, jint offsetk) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static double (*dlapy2_)(double *x, double *y);

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlapy2K(JNIEnv *env, UNUSED jobject obj,
    jdouble x, jdouble y) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static double (*dlapy3_)(double *x, double *y, double *z);

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlapy3K(JNIEnv *env, UNUSED jobject obj,
    jdouble x, jdouble y, jdouble z) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static void (*dlaqgb_)(int *m, int *n, int *kl, int *ku, double *ab, int *ldab, double *r, double *c, double *rowcnd, double *colcnd, double *amax, jstring *equed);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqgbK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint kl, jint ku, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray r, jint offsetr, jdoubleArray c, jint offsetc, jdouble rowcnd, jdouble colcnd, jdouble amax, jobject equed) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaqge_)(int *m, int *n, double *a, int *lda, double *r, double *c, double *rowcnd, double *colcnd, double *amax, jstring *equed);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqgeK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray r, jint offsetr, jdoubleArray c, jint offsetc, jdouble rowcnd, jdouble colcnd, jdouble amax, jobject equed) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaqp2_)(int *m, int *n, int *offset, double *a, int *lda, int * jpvt, double *tau, double *vn11, double *vn22, double *work);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqp2K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint offset, jdoubleArray a, jint offseta, jint lda, jintArray jpvt, jint offsetjpvt, jdoubleArray tau, jint offsettau, jdoubleArray vn1, jint offsetvn1, jdoubleArray vn2, jint offsetvn2, jdoubleArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaqps_)(int *m, int *n, int *offset, int *nb, /*FIXME*/void *kb, double *a, int *lda, int * jpvt, double *tau, double *vn11, double *vn22, double *auxv, double *f, int *ldf);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqpsK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint offset, jint nb, jobject kb, jdoubleArray a, jint offseta, jint lda, jintArray jpvt, jint offsetjpvt, jdoubleArray tau, jint offsettau, jdoubleArray vn1, jint offsetvn1, jdoubleArray vn2, jint offsetvn2, jdoubleArray auxv, jint offsetauxv, jdoubleArray f, jint offsetf, jint ldf) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaqr0_)(int *wantt, int *wantz, int *n, int *ilo, int *ihi, double *h, int *ldh, double *wr, double *wi, int *iloz, int *ihiz, double *z, int *ldz, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqr0K(JNIEnv *env, UNUSED jobject obj,
    jboolean wantt, jboolean wantz, jint n, jint ilo, jint ihi, jdoubleArray h, jint offseth, jint ldh, jdoubleArray wr, jint offsetwr, jdoubleArray wi, jint offsetwi, jint iloz, jint ihiz, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaqr1_)(int *n, double *h, int *ldh, double *sr1, double *si1, double *sr2, double *si2, double *v);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqr1K(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray h, jint offseth, jint ldh, jdouble sr1, jdouble si1, jdouble sr2, jdouble si2, jdoubleArray v, jint offsetv) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaqr2_)(int *wantt, int *wantz, int *n, int *ktop, int *kbot, int *nw, double *h, int *ldh, int *iloz, int *ihiz, double *z, int *ldz, /*FIXME*/void *ns, /*FIXME*/void *nd, double *sr, double *si, double *v, int *ldv, int *nh, double *t, int *ldt, int *nv, double *wv, int *ldwv, double *work, int *lwork);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqr2K(JNIEnv *env, UNUSED jobject obj,
    jboolean wantt, jboolean wantz, jint n, jint ktop, jint kbot, jint nw, jdoubleArray h, jint offseth, jint ldh, jint iloz, jint ihiz, jdoubleArray z, jint offsetz, jint ldz, jobject ns, jobject nd, jdoubleArray sr, jint offsetsr, jdoubleArray si, jint offsetsi, jdoubleArray v, jint offsetv, jint ldv, jint nh, jdoubleArray t, jint offsett, jint ldt, jint nv, jdoubleArray wv, jint offsetwv, jint ldwv, jdoubleArray work, jint offsetwork, jint lwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaqr3_)(int *wantt, int *wantz, int *n, int *ktop, int *kbot, int *nw, double *h, int *ldh, int *iloz, int *ihiz, double *z, int *ldz, /*FIXME*/void *ns, /*FIXME*/void *nd, double *sr, double *si, double *v, int *ldv, int *nh, double *t, int *ldt, int *nv, double *wv, int *ldwv, double *work, int *lwork);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqr3K(JNIEnv *env, UNUSED jobject obj,
    jboolean wantt, jboolean wantz, jint n, jint ktop, jint kbot, jint nw, jdoubleArray h, jint offseth, jint ldh, jint iloz, jint ihiz, jdoubleArray z, jint offsetz, jint ldz, jobject ns, jobject nd, jdoubleArray sr, jint offsetsr, jdoubleArray si, jint offsetsi, jdoubleArray v, jint offsetv, jint ldv, jint nh, jdoubleArray t, jint offsett, jint ldt, jint nv, jdoubleArray wv, jint offsetwv, jint ldwv, jdoubleArray work, jint offsetwork, jint lwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaqr4_)(int *wantt, int *wantz, int *n, int *ilo, int *ihi, double *h, int *ldh, double *wr, double *wi, int *iloz, int *ihiz, double *z, int *ldz, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqr4K(JNIEnv *env, UNUSED jobject obj,
    jboolean wantt, jboolean wantz, jint n, jint ilo, jint ihi, jdoubleArray h, jint offseth, jint ldh, jdoubleArray wr, jint offsetwr, jdoubleArray wi, jint offsetwi, jint iloz, jint ihiz, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaqr5_)(int *wantt, int *wantz, int *kacc22, int *n, int *ktop, int *kbot, int *nshfts, double *sr, double *si, double *h, int *ldh, int *iloz, int *ihiz, double *z, int *ldz, double *v, int *ldv, double *u, int *ldu, int *nv, double *wv, int *ldwv, int *nh, double *wh, int *ldwh);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqr5K(JNIEnv *env, UNUSED jobject obj,
    jboolean wantt, jboolean wantz, jint kacc22, jint n, jint ktop, jint kbot, jint nshfts, jdoubleArray sr, jint offsetsr, jdoubleArray si, jint offsetsi, jdoubleArray h, jint offseth, jint ldh, jint iloz, jint ihiz, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray u, jint offsetu, jint ldu, jint nv, jdoubleArray wv, jint offsetwv, jint ldwv, jint nh, jdoubleArray wh, jint offsetwh, jint ldwh) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaqsb_)(const char *uplo, int *n, int *kd, double *ab, int *ldab, double *s, double *scond, double *amax, jstring *equed);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqsbK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint kd, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray s, jint offsets, jdouble scond, jdouble amax, jobject equed) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaqsp_)(const char *uplo, int *n, double *ap, double *s, double *scond, double *amax, jstring *equed);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqspK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray ap, jint offsetap, jdoubleArray s, jint offsets, jdouble scond, jdouble amax, jobject equed) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaqsy_)(const char *uplo, int *n, double *a, int *lda, double *s, double *scond, double *amax, jstring *equed);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqsyK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray s, jint offsets, jdouble scond, jdouble amax, jobject equed) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaqtr_)(int *ltran, int *lreal, int *n, double *t, int *ldt, double *b, double *w, /*FIXME*/void *scale, double *x, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqtrK(JNIEnv *env, UNUSED jobject obj,
    jboolean ltran, jboolean lreal, jint n, jdoubleArray t, jint offsett, jint ldt, jdoubleArray b, jint offsetb, jdouble w, jobject scale, jdoubleArray x, jint offsetx, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlar1v_)(int *n, int *b1, int *bn, double *lambda, double *d, double *l, double *ld, double *lld, double *pivmin, double *gaptol, double *z, int *wantnc, /*FIXME*/void *negcnt, /*FIXME*/void *ztz, /*FIXME*/void *mingma, /*FIXME*/void *r, int * isuppz, /*FIXME*/void *nrminv, /*FIXME*/void *resid, /*FIXME*/void *rqcorr, double *work);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlar1vK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint b1, jint bn, jdouble lambda, jdoubleArray d, jint offsetd, jdoubleArray l, jint offsetl, jdoubleArray ld, jint offsetld, jdoubleArray lld, jint offsetlld, jdouble pivmin, jdouble gaptol, jdoubleArray z, jint offsetz, jboolean wantnc, jobject negcnt, jobject ztz, jobject mingma, jobject r, jintArray isuppz, jint offsetisuppz, jobject nrminv, jobject resid, jobject rqcorr, jdoubleArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlar2v_)(int *n, double *x, double *y, double *z, int *incx, double *c, double *s, int *incc);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlar2vK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray x, jint offsetx, jdoubleArray y, jint offsety, jdoubleArray z, jint offsetz, jint incx, jdoubleArray c, jint offsetc, jdoubleArray s, jint offsets, jint incc) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlarf_)(char *side, int *m, int *n, double *v, int *incv, double *tau, double *c, int *Ldc, double *work);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarfK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jint m, jint n, jdoubleArray v, jint offsetv, jint incv, jdouble tau, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlarfb_)(char *side, char *trans, char *direct, char *storev, int *m, int *n, int *k, double *v, int *ldv, double *t, int *ldt, double *c, int *Ldc, double *work, int *ldwork);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarfbK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jstring direct, jstring storev, jint m, jint n, jint k, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray t, jint offsett, jint ldt, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork, jint ldwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlarfg_)(int *n, /*FIXME*/void *alpha, double *x, int *incx, /*FIXME*/void *tau);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarfgK(JNIEnv *env, UNUSED jobject obj,
    jint n, jobject alpha, jdoubleArray x, jint offsetx, jint incx, jobject tau) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlarft_)(char *direct, char *storev, int *n, int *k, double *v, int *ldv, double *tau, double *t, int *ldt);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarftK(JNIEnv *env, UNUSED jobject obj,
    jstring direct, jstring storev, jint n, jint k, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray tau, jint offsettau, jdoubleArray t, jint offsett, jint ldt) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlarfx_)(char *side, int *m, int *n, double *v, double *tau, double *c, int *Ldc, double *work);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarfxK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jint m, jint n, jdoubleArray v, jint offsetv, jdouble tau, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlargv_)(int *n, double *x, int *incx, double *y, int *incy, double *c, int *incc);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlargvK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy, jdoubleArray c, jint offsetc, jint incc) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlarnv_)(int *idist, int * iseed, int *n, double *x);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarnvK(JNIEnv *env, UNUSED jobject obj,
    jint idist, jintArray iseed, jint offsetiseed, jint n, jdoubleArray x, jint offsetx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlarra_)(int *n, double *d, double *e, double *e22, double *spltol, double *tnrm, /*FIXME*/void *nsplit, int * isplit, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarraK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray e2, jint offsete2, jdouble spltol, jdouble tnrm, jobject nsplit, jintArray isplit, jint offsetisplit, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlarrb_)(int *n, double *d, double *lld, int *ifirst, int *ilast, double *rtol1, double *rtol2, int *offset, double *w, double *wgap, double *werr, double *work, int * iwork, double *pivmin, double *spdiam, int *twist, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarrbK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray d, jint offsetd, jdoubleArray lld, jint offsetlld, jint ifirst, jint ilast, jdouble rtol1, jdouble rtol2, jint offset, jdoubleArray w, jint offsetw, jdoubleArray wgap, jint offsetwgap, jdoubleArray werr, jint offsetwerr, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jdouble pivmin, jdouble spdiam, jint twist, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlarrc_)(char *jobt, int *n, double *vl, double *vu, double *d, double *e, double *pivmin, /*FIXME*/void *eigcnt, /*FIXME*/void *lcnt, /*FIXME*/void *rcnt, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarrcK(JNIEnv *env, UNUSED jobject obj,
    jstring jobt, jint n, jdouble vl, jdouble vu, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdouble pivmin, jobject eigcnt, jobject lcnt, jobject rcnt, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlarrd_)(char *range, char *order, int *n, double *vl, double *vu, int *il, int *iu, double *gers, double *reltol, double *d, double *e, double *e22, double *pivmin, int *nsplit, int * isplit, /*FIXME*/void *m, double *w, double *werr, /*FIXME*/void *wl, /*FIXME*/void *wu, int * iblock, int * indexw, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarrdK(JNIEnv *env, UNUSED jobject obj,
    jstring range, jstring order, jint n, jdouble vl, jdouble vu, jint il, jint iu, jdoubleArray gers, jint offsetgers, jdouble reltol, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray e2, jint offsete2, jdouble pivmin, jint nsplit, jintArray isplit, jint offsetisplit, jobject m, jdoubleArray w, jint offsetw, jdoubleArray werr, jint offsetwerr, jobject wl, jobject wu, jintArray iblock, jint offsetiblock, jintArray indexw, jint offsetindexw, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlarre_)(char *range, int *n, /*FIXME*/void *vl, /*FIXME*/void *vu, int *il, int *iu, double *d, double *e, double *e22, double *rtol1, double *rtol2, double *spltol, /*FIXME*/void *nsplit, int * isplit, /*FIXME*/void *m, double *w, double *werr, double *wgap, int * iblock, int * indexw, double *gers, /*FIXME*/void *pivmin, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarreK(JNIEnv *env, UNUSED jobject obj,
    jstring range, jint n, jobject vl, jobject vu, jint il, jint iu, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray e2, jint offsete2, jdouble rtol1, jdouble rtol2, jdouble spltol, jobject nsplit, jintArray isplit, jint offsetisplit, jobject m, jdoubleArray w, jint offsetw, jdoubleArray werr, jint offsetwerr, jdoubleArray wgap, jint offsetwgap, jintArray iblock, jint offsetiblock, jintArray indexw, jint offsetindexw, jdoubleArray gers, jint offsetgers, jobject pivmin, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlarrf_)(int *n, double *d, double *l, double *ld, int *clstrt, int *clend, double *w, double *wgap, double *werr, double *spdiam, double *clgapl, double *clgapr, double *pivmin, /*FIXME*/void *sigma, double *dplus, double *lplus, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarrfK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray d, jint offsetd, jdoubleArray l, jint offsetl, jdoubleArray ld, jint offsetld, jint clstrt, jint clend, jdoubleArray w, jint offsetw, jdoubleArray wgap, jint offsetwgap, jdoubleArray werr, jint offsetwerr, jdouble spdiam, jdouble clgapl, jdouble clgapr, jdouble pivmin, jobject sigma, jdoubleArray dplus, jint offsetdplus, jdoubleArray lplus, jint offsetlplus, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlarrj_)(int *n, double *d, double *e22, int *ifirst, int *ilast, double *rtol, int *offset, double *w, double *werr, double *work, int * iwork, double *pivmin, double *spdiam, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarrjK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray d, jint offsetd, jdoubleArray e2, jint offsete2, jint ifirst, jint ilast, jdouble rtol, jint offset, jdoubleArray w, jint offsetw, jdoubleArray werr, jint offsetwerr, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jdouble pivmin, jdouble spdiam, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlarrk_)(int *n, int *iw, double *gl, double *gu, double *d, double *e22, double *pivmin, double *reltol, /*FIXME*/void *w, /*FIXME*/void *werr, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarrkK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint iw, jdouble gl, jdouble gu, jdoubleArray d, jint offsetd, jdoubleArray e2, jint offsete2, jdouble pivmin, jdouble reltol, jobject w, jobject werr, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlarrr_)(int *n, double *d, double *e, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarrrK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlarrv_)(int *n, double *vl, double *vu, double *d, double *l, double *pivmin, int * isplit, int *m, int *dol, int *dou, double *minrgp, /*FIXME*/void *rtol1, /*FIXME*/void *rtol2, double *w, double *werr, double *wgap, int * iblock, int * indexw, double *gers, double *z, int *ldz, int * isuppz, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarrvK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdouble vl, jdouble vu, jdoubleArray d, jint offsetd, jdoubleArray l, jint offsetl, jdouble pivmin, jintArray isplit, jint offsetisplit, jint m, jint dol, jint dou, jdouble minrgp, jobject rtol1, jobject rtol2, jdoubleArray w, jint offsetw, jdoubleArray werr, jint offsetwerr, jdoubleArray wgap, jint offsetwgap, jintArray iblock, jint offsetiblock, jintArray indexw, jint offsetindexw, jdoubleArray gers, jint offsetgers, jdoubleArray z, jint offsetz, jint ldz, jintArray isuppz, jint offsetisuppz, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlartg_)(double *f, double *g, /*FIXME*/void *cs, /*FIXME*/void *sn, /*FIXME*/void *r);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlartgK(JNIEnv *env, UNUSED jobject obj,
    jdouble f, jdouble g, jobject cs, jobject sn, jobject r) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlartv_)(int *n, double *x, int *incx, double *y, int *incy, double *c, double *s, int *incc);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlartvK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy, jdoubleArray c, jint offsetc, jdoubleArray s, jint offsets, jint incc) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaruv_)(int * iseed, int *n, double *x);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaruvK(JNIEnv *env, UNUSED jobject obj,
    jintArray iseed, jint offsetiseed, jint n, jdoubleArray x, jint offsetx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlarz_)(char *side, int *m, int *n, int *l, double *v, int *incv, double *tau, double *c, int *Ldc, double *work);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarzK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jint m, jint n, jint l, jdoubleArray v, jint offsetv, jint incv, jdouble tau, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlarzb_)(char *side, char *trans, char *direct, char *storev, int *m, int *n, int *k, int *l, double *v, int *ldv, double *t, int *ldt, double *c, int *Ldc, double *work, int *ldwork);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarzbK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jstring direct, jstring storev, jint m, jint n, jint k, jint l, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray t, jint offsett, jint ldt, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork, jint ldwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlarzt_)(char *direct, char *storev, int *n, int *k, double *v, int *ldv, double *tau, double *t, int *ldt);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarztK(JNIEnv *env, UNUSED jobject obj,
    jstring direct, jstring storev, jint n, jint k, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray tau, jint offsettau, jdoubleArray t, jint offsett, jint ldt) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlas2_)(double *f, double *g, double *h, /*FIXME*/void *ssmin, /*FIXME*/void *ssmax);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlas2K(JNIEnv *env, UNUSED jobject obj,
    jdouble f, jdouble g, jdouble h, jobject ssmin, jobject ssmax) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlascl_)(char *type, int *kl, int *ku, double *cfrom, double *cto, int *m, int *n, double *a, int *lda, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasclK(JNIEnv *env, UNUSED jobject obj,
    jstring type, jint kl, jint ku, jdouble cfrom, jdouble cto, jint m, jint n, jdoubleArray a, jint offseta, jint lda, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasd0_)(int *n, int *sqre, double *d, double *e, double *u, int *ldu, double *vt, int *ldvt, int *smlsiz, int * iwork, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasd0K(JNIEnv *env, UNUSED jobject obj,
    jint n, jint sqre, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray u, jint offsetu, jint ldu, jdoubleArray vt, jint offsetvt, jint ldvt, jint smlsiz, jintArray iwork, jint offsetiwork, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasd1_)(int *nl, int *nr, int *sqre, double *d, /*FIXME*/void *alpha, /*FIXME*/void *beta, double *u, int *ldu, double *vt, int *ldvt, int * idxq, int * iwork, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasd1K(JNIEnv *env, UNUSED jobject obj,
    jint nl, jint nr, jint sqre, jdoubleArray d, jint offsetd, jobject alpha, jobject beta, jdoubleArray u, jint offsetu, jint ldu, jdoubleArray vt, jint offsetvt, jint ldvt, jintArray idxq, jint offsetidxq, jintArray iwork, jint offsetiwork, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasd2_)(int *nl, int *nr, int *sqre, /*FIXME*/void *k, double *d, double *z, double *alpha, double *beta, double *u, int *ldu, double *vt, int *ldvt, double *dsigma, double *u22, int *ldu2, double *vt22, int *ldvt2, int * idxp, int * idx, int * idxc, int * idxq, int * coltyp, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasd2K(JNIEnv *env, UNUSED jobject obj,
    jint nl, jint nr, jint sqre, jobject k, jdoubleArray d, jint offsetd, jdoubleArray z, jint offsetz, jdouble alpha, jdouble beta, jdoubleArray u, jint offsetu, jint ldu, jdoubleArray vt, jint offsetvt, jint ldvt, jdoubleArray dsigma, jint offsetdsigma, jdoubleArray u2, jint offsetu2, jint ldu2, jdoubleArray vt2, jint offsetvt2, jint ldvt2, jintArray idxp, jint offsetidxp, jintArray idx, jint offsetidx, jintArray idxc, jint offsetidxc, jintArray idxq, jint offsetidxq, jintArray coltyp, jint offsetcoltyp, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasd3_)(int *nl, int *nr, int *sqre, int *k, double *d, double *q, int *ldq, double *dsigma, double *u, int *ldu, double *u22, int *ldu2, double *vt, int *ldvt, double *vt22, int *ldvt2, int * idxc, int * ctot, double *z, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasd3K(JNIEnv *env, UNUSED jobject obj,
    jint nl, jint nr, jint sqre, jint k, jdoubleArray d, jint offsetd, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray dsigma, jint offsetdsigma, jdoubleArray u, jint offsetu, jint ldu, jdoubleArray u2, jint offsetu2, jint ldu2, jdoubleArray vt, jint offsetvt, jint ldvt, jdoubleArray vt2, jint offsetvt2, jint ldvt2, jintArray idxc, jint offsetidxc, jintArray ctot, jint offsetctot, jdoubleArray z, jint offsetz, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasd4_)(int *n, int *i, double *d, double *z, double *delta, double *rho, /*FIXME*/void *sigma, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasd4K(JNIEnv *env, UNUSED jobject obj,
    jint n, jint i, jdoubleArray d, jint offsetd, jdoubleArray z, jint offsetz, jdoubleArray delta, jint offsetdelta, jdouble rho, jobject sigma, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasd5_)(int *i, double *d, double *z, double *delta, double *rho, /*FIXME*/void *dsigma, double *work);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasd5K(JNIEnv *env, UNUSED jobject obj,
    jint i, jdoubleArray d, jint offsetd, jdoubleArray z, jint offsetz, jdoubleArray delta, jint offsetdelta, jdouble rho, jobject dsigma, jdoubleArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasd6_)(int *icompq, int *nl, int *nr, int *sqre, double *d, double *vf, double *vl, /*FIXME*/void *alpha, /*FIXME*/void *beta, int * idxq, int * perm, /*FIXME*/void *givptr, int * givcol, int *ldgcol, double *givnum, int *ldgnum, double *poles, double *difl, double *difr, double *z, /*FIXME*/void *k, /*FIXME*/void *c, /*FIXME*/void *s, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasd6K(JNIEnv *env, UNUSED jobject obj,
    jint icompq, jint nl, jint nr, jint sqre, jdoubleArray d, jint offsetd, jdoubleArray vf, jint offsetvf, jdoubleArray vl, jint offsetvl, jobject alpha, jobject beta, jintArray idxq, jint offsetidxq, jintArray perm, jint offsetperm, jobject givptr, jintArray givcol, jint offsetgivcol, jint ldgcol, jdoubleArray givnum, jint offsetgivnum, jint ldgnum, jdoubleArray poles, jint offsetpoles, jdoubleArray difl, jint offsetdifl, jdoubleArray difr, jint offsetdifr, jdoubleArray z, jint offsetz, jobject k, jobject c, jobject s, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasd7_)(int *icompq, int *nl, int *nr, int *sqre, /*FIXME*/void *k, double *d, double *z, double *zw, double *vf, double *vfw, double *vl, double *vlw, double *alpha, double *beta, double *dsigma, int * idx, int * idxp, int * idxq, int * perm, /*FIXME*/void *givptr, int * givcol, int *ldgcol, double *givnum, int *ldgnum, /*FIXME*/void *c, /*FIXME*/void *s, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasd7K(JNIEnv *env, UNUSED jobject obj,
    jint icompq, jint nl, jint nr, jint sqre, jobject k, jdoubleArray d, jint offsetd, jdoubleArray z, jint offsetz, jdoubleArray zw, jint offsetzw, jdoubleArray vf, jint offsetvf, jdoubleArray vfw, jint offsetvfw, jdoubleArray vl, jint offsetvl, jdoubleArray vlw, jint offsetvlw, jdouble alpha, jdouble beta, jdoubleArray dsigma, jint offsetdsigma, jintArray idx, jint offsetidx, jintArray idxp, jint offsetidxp, jintArray idxq, jint offsetidxq, jintArray perm, jint offsetperm, jobject givptr, jintArray givcol, jint offsetgivcol, jint ldgcol, jdoubleArray givnum, jint offsetgivnum, jint ldgnum, jobject c, jobject s, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasd8_)(int *icompq, int *k, double *d, double *z, double *vf, double *vl, double *difl, double *difr, int *lddifr, double *dsigma, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasd8K(JNIEnv *env, UNUSED jobject obj,
    jint icompq, jint k, jdoubleArray d, jint offsetd, jdoubleArray z, jint offsetz, jdoubleArray vf, jint offsetvf, jdoubleArray vl, jint offsetvl, jdoubleArray difl, jint offsetdifl, jdoubleArray difr, jint offsetdifr, jint lddifr, jdoubleArray dsigma, jint offsetdsigma, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasda_)(int *icompq, int *smlsiz, int *n, int *sqre, double *d, double *e, double *u, int *ldu, double *vt, int * k, double *difl, double *difr, double *z, double *poles, int * givptr, int * givcol, int *ldgcol, int * perm, double *givnum, double *c, double *s, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasdaK(JNIEnv *env, UNUSED jobject obj,
    jint icompq, jint smlsiz, jint n, jint sqre, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray u, jint offsetu, jint ldu, jdoubleArray vt, jint offsetvt, jintArray k, jint offsetk, jdoubleArray difl, jint offsetdifl, jdoubleArray difr, jint offsetdifr, jdoubleArray z, jint offsetz, jdoubleArray poles, jint offsetpoles, jintArray givptr, jint offsetgivptr, jintArray givcol, jint offsetgivcol, jint ldgcol, jintArray perm, jint offsetperm, jdoubleArray givnum, jint offsetgivnum, jdoubleArray c, jint offsetc, jdoubleArray s, jint offsets, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasdq_)(const char *uplo, int *sqre, int *n, int *ncvt, int *nru, int *ncc, double *d, double *e, double *vt, int *ldvt, double *u, int *ldu, double *c, int *Ldc, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasdqK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint sqre, jint n, jint ncvt, jint nru, jint ncc, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray vt, jint offsetvt, jint ldvt, jdoubleArray u, jint offsetu, jint ldu, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasdt_)(int *n, /*FIXME*/void *lvl, /*FIXME*/void *nd, int * inode, int * ndiml, int * ndimr, int *msub);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasdtK(JNIEnv *env, UNUSED jobject obj,
    jint n, jobject lvl, jobject nd, jintArray inode, jint offsetinode, jintArray ndiml, jint offsetndiml, jintArray ndimr, jint offsetndimr, jint msub) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaset_)(const char *uplo, int *m, int *n, double *alpha, double *beta, double *a, int *lda);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasetK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint m, jint n, jdouble alpha, jdouble beta, jdoubleArray a, jint offseta, jint lda) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasq1_)(int *n, double *d, double *e, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasq1K(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasq2_)(int *n, double *z, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasq2K(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray z, jint offsetz, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasq3_)(int *i0, /*FIXME*/void *n0, double *z, int *pp, /*FIXME*/void *dmin, /*FIXME*/void *sigma, /*FIXME*/void *desig, /*FIXME*/void *qmax, /*FIXME*/void *nfail, /*FIXME*/void *iter, /*FIXME*/void *ndiv, int *ieee);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasq3K(JNIEnv *env, UNUSED jobject obj,
    jint i0, jobject n0, jdoubleArray z, jint offsetz, jint pp, jobject dmin, jobject sigma, jobject desig, jobject qmax, jobject nfail, jobject iter, jobject ndiv, jboolean ieee) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasq4_)(int *i0, int *n0, double *z, int *pp, int *n0in, double *dmin, double *dmin1, double *dmin2, double *dn, double *dn1, double *dn2, /*FIXME*/void *tau, /*FIXME*/void *ttype);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasq4K(JNIEnv *env, UNUSED jobject obj,
    jint i0, jint n0, jdoubleArray z, jint offsetz, jint pp, jint n0in, jdouble dmin, jdouble dmin1, jdouble dmin2, jdouble dn, jdouble dn1, jdouble dn2, jobject tau, jobject ttype) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasq5_)(int *i0, int *n0, double *z, int *pp, double *tau, /*FIXME*/void *dmin, /*FIXME*/void *dmin1, /*FIXME*/void *dmin2, /*FIXME*/void *dn, /*FIXME*/void *dnm1, /*FIXME*/void *dnm2, int *ieee);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasq5K(JNIEnv *env, UNUSED jobject obj,
    jint i0, jint n0, jdoubleArray z, jint offsetz, jint pp, jdouble tau, jobject dmin, jobject dmin1, jobject dmin2, jobject dn, jobject dnm1, jobject dnm2, jboolean ieee) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasq6_)(int *i0, int *n0, double *z, int *pp, /*FIXME*/void *dmin, /*FIXME*/void *dmin1, /*FIXME*/void *dmin2, /*FIXME*/void *dn, /*FIXME*/void *dnm1, /*FIXME*/void *dnm2);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasq6K(JNIEnv *env, UNUSED jobject obj,
    jint i0, jint n0, jdoubleArray z, jint offsetz, jint pp, jobject dmin, jobject dmin1, jobject dmin2, jobject dn, jobject dnm1, jobject dnm2) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasr_)(char *side, char *pivot, char *direct, int *m, int *n, double *c, double *s, double *a, int *lda);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasrK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring pivot, jstring direct, jint m, jint n, jdoubleArray c, jint offsetc, jdoubleArray s, jint offsets, jdoubleArray a, jint offseta, jint lda) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasrt_)(char *id, int *n, double *d, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasrtK(JNIEnv *env, UNUSED jobject obj,
    jstring id, jint n, jdoubleArray d, jint offsetd, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlassq_)(int *n, double *x, int *incx, /*FIXME*/void *scale, /*FIXME*/void *sumsq);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlassqK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray x, jint offsetx, jint incx, jobject scale, jobject sumsq) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasv2_)(double *f, double *g, double *h, double *ssmin, /*FIXME*/void *ssmax, /*FIXME*/void *snr, double *csr, double *snl, double *csl);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasv2K(JNIEnv *env, UNUSED jobject obj,
    jdouble f, jdouble g, jdouble h, jobject ssmin, jobject ssmax, jobject snr, jobject csr, jobject snl, jobject csl) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlaswp_)(int *n, double *a, int *lda, int *k1, int *k2, int * ipiv, int *incx);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaswpK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray a, jint offseta, jint lda, jint k1, jint k2, jintArray ipiv, jint offsetipiv, jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasy2_)(int *ltranl, int *ltranr, int *isgn, int *n1, int *n2, double *tl, int *ldtl, double *tr, int *ldtr, double *b, int *ldb, /*FIXME*/void *scale, double *x, int *ldx, /*FIXME*/void *xnorm, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasy2K(JNIEnv *env, UNUSED jobject obj,
    jboolean ltranl, jboolean ltranr, jint isgn, jint n1, jint n2, jdoubleArray tl, jint offsettl, jint ldtl, jdoubleArray tr, jint offsettr, jint ldtr, jdoubleArray b, jint offsetb, jint ldb, jobject scale, jdoubleArray x, jint offsetx, jint ldx, jobject xnorm, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlasyf_)(const char *uplo, int *n, int *nb, /*FIXME*/void *kb, double *a, int *lda, int * ipiv, double *w, int *ldw, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasyfK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nb, jobject kb, jdoubleArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jdoubleArray w, jint offsetw, jint ldw, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlatbs_)(const char *uplo, char *trans, char *diag, char *normin, int *n, int *kd, double *ab, int *ldab, double *x, /*FIXME*/void *scale, double *cnorm, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlatbsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jstring diag, jstring normin, jint n, jint kd, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray x, jint offsetx, jobject scale, jdoubleArray cnorm, jint offsetcnorm, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlatdf_)(int *ijob, int *n, double *z, int *ldz, double *rhs, /*FIXME*/void *rdsum, /*FIXME*/void *rdscal, int * ipiv, int * jpiv);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlatdfK(JNIEnv *env, UNUSED jobject obj,
    jint ijob, jint n, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray rhs, jint offsetrhs, jobject rdsum, jobject rdscal, jintArray ipiv, jint offsetipiv, jintArray jpiv, jint offsetjpiv) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlatps_)(const char *uplo, char *trans, char *diag, char *normin, int *n, double *ap, double *x, /*FIXME*/void *scale, double *cnorm, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlatpsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jstring diag, jstring normin, jint n, jdoubleArray ap, jint offsetap, jdoubleArray x, jint offsetx, jobject scale, jdoubleArray cnorm, jint offsetcnorm, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlatrd_)(const char *uplo, int *n, int *nb, double *a, int *lda, double *e, double *tau, double *w, int *ldw);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlatrdK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nb, jdoubleArray a, jint offseta, jint lda, jdoubleArray e, jint offsete, jdoubleArray tau, jint offsettau, jdoubleArray w, jint offsetw, jint ldw) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlatrs_)(const char *uplo, char *trans, char *diag, char *normin, int *n, double *a, int *lda, double *x, /*FIXME*/void *scale, double *cnorm, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlatrsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jstring diag, jstring normin, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray x, jint offsetx, jobject scale, jdoubleArray cnorm, jint offsetcnorm, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlatrz_)(int *m, int *n, int *l, double *a, int *lda, double *tau, double *work);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlatrzK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint l, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlatzm_)(char *side, int *m, int *n, double *v, int *incv, double *tau, double *c11, double *c22, int *Ldc, double *work);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlatzmK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jint m, jint n, jdoubleArray v, jint offsetv, jint incv, jdouble tau, jdoubleArray c1, jint offsetc1, jdoubleArray c2, jint offsetc2, jint Ldc, jdoubleArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlauu2_)(const char *uplo, int *n, double *a, int *lda, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlauu2K(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dlauum_)(const char *uplo, int *n, double *a, int *lda, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlauumK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

// static void (*dlazq3_)(int *i0, /*FIXME*/void *n0, double *z, int *pp, /*FIXME*/void *dmin, /*FIXME*/void *sigma, /*FIXME*/void *desig, /*FIXME*/void *qmax, /*FIXME*/void *nfail, /*FIXME*/void *iter, /*FIXME*/void *ndiv, int *ieee, /*FIXME*/void *ttype, /*FIXME*/void *dmin1, /*FIXME*/void *dmin2, /*FIXME*/void *dn, /*FIXME*/void *dn1, /*FIXME*/void *dn2, /*FIXME*/void *tau);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlazq3K(JNIEnv *env, UNUSED jobject obj,
    jint i0, jobject n0, jdoubleArray z, jint offsetz, jint pp, jobject dmin, jobject sigma, jobject desig, jobject qmax, jobject nfail, jobject iter, jobject ndiv, jboolean ieee, jobject ttype, jobject dmin1, jobject dmin2, jobject dn, jobject dn1, jobject dn2, jobject tau) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

// static void (*dlazq4_)(int *i0, int *n0, double *z, int *pp, int *n0in, double *dmin, double *dmin1, double *dmin2, double *dn, double *dn1, double *dn2, /*FIXME*/void *tau, /*FIXME*/void *ttype, /*FIXME*/void *g);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlazq4K(JNIEnv *env, UNUSED jobject obj,
    jint i0, jint n0, jdoubleArray z, jint offsetz, jint pp, jint n0in, jdouble dmin, jdouble dmin1, jdouble dmin2, jdouble dn, jdouble dn1, jdouble dn2, jobject tau, jobject ttype, jobject g) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dopgtr_)(const char *uplo, int *n, double *ap, double *tau, double *q, int *ldq, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dopgtrK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray ap, jint offsetap, jdoubleArray tau, jint offsettau, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dopmtr_)(char *side, const char *uplo, char *trans, int *m, int *n, double *ap, double *tau, double *c, int *Ldc, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dopmtrK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring uplo, jstring trans, jint m, jint n, jdoubleArray ap, jint offsetap, jdoubleArray tau, jint offsettau, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dorg2l_)(int *m, int *n, int *k, double *a, int *lda, double *tau, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorg2lK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint k, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dorg2r_)(int *m, int *n, int *k, double *a, int *lda, double *tau, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorg2rK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint k, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dorgbr_)(char *vect, int *m, int *n, int *k, double *a, int *lda, double *tau, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorgbrK(JNIEnv *env, UNUSED jobject obj,
    jstring vect, jint m, jint n, jint k, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dorghr_)(int *n, int *ilo, int *ihi, double *a, int *lda, double *tau, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorghrK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint ilo, jint ihi, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dorgl2_)(int *m, int *n, int *k, double *a, int *lda, double *tau, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorgl2K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint k, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dorglq_)(int *m, int *n, int *k, double *a, int *lda, double *tau, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorglqK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint k, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dorgql_)(int *m, int *n, int *k, double *a, int *lda, double *tau, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorgqlK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint k, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dorgqr_)(int *m, int *n, int *k, double *a, int *lda, double *tau, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorgqrK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint k, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dorgr2_)(int *m, int *n, int *k, double *a, int *lda, double *tau, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorgr2K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint k, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dorgrq_)(int *m, int *n, int *k, double *a, int *lda, double *tau, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorgrqK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint k, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dorgtr_)(const char *uplo, int *n, double *a, int *lda, double *tau, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorgtrK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dorm2l_)(char *side, char *trans, int *m, int *n, int *k, double *a, int *lda, double *tau, double *c, int *Ldc, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorm2lK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint k, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dorm2r_)(char *side, char *trans, int *m, int *n, int *k, double *a, int *lda, double *tau, double *c, int *Ldc, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorm2rK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint k, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dormbr_)(char *vect, char *side, char *trans, int *m, int *n, int *k, double *a, int *lda, double *tau, double *c, int *Ldc, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dormbrK(JNIEnv *env, UNUSED jobject obj,
    jstring vect, jstring side, jstring trans, jint m, jint n, jint k, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dormhr_)(char *side, char *trans, int *m, int *n, int *ilo, int *ihi, double *a, int *lda, double *tau, double *c, int *Ldc, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dormhrK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint ilo, jint ihi, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dorml2_)(char *side, char *trans, int *m, int *n, int *k, double *a, int *lda, double *tau, double *c, int *Ldc, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorml2K(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint k, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dormlq_)(char *side, char *trans, int *m, int *n, int *k, double *a, int *lda, double *tau, double *c, int *Ldc, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dormlqK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint k, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dormql_)(char *side, char *trans, int *m, int *n, int *k, double *a, int *lda, double *tau, double *c, int *Ldc, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dormqlK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint k, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dormqr_)(char *side, char *trans, int *m, int *n, int *k, double *a, int *lda, double *tau, double *c, int *Ldc, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dormqrK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint k, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dormr2_)(char *side, char *trans, int *m, int *n, int *k, double *a, int *lda, double *tau, double *c, int *Ldc, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dormr2K(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint k, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dormr3_)(char *side, char *trans, int *m, int *n, int *k, int *l, double *a, int *lda, double *tau, double *c, int *Ldc, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dormr3K(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint k, jint l, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dormrq_)(char *side, char *trans, int *m, int *n, int *k, double *a, int *lda, double *tau, double *c, int *Ldc, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dormrqK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint k, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dormrz_)(char *side, char *trans, int *m, int *n, int *k, int *l, double *a, int *lda, double *tau, double *c, int *Ldc, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dormrzK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint k, jint l, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dormtr_)(char *side, const char *uplo, char *trans, int *m, int *n, double *a, int *lda, double *tau, double *c, int *Ldc, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dormtrK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring uplo, jstring trans, jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpbcon_)(const char *uplo, int *n, int *kd, double *ab, int *ldab, double *anorm, /*FIXME*/void *rcond, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpbconK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint kd, jdoubleArray ab, jint offsetab, jint ldab, jdouble anorm, jobject rcond, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpbequ_)(const char *uplo, int *n, int *kd, double *ab, int *ldab, double *s, /*FIXME*/void *scond, /*FIXME*/void *amax, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpbequK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint kd, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray s, jint offsets, jobject scond, jobject amax, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpbrfs_)(const char *uplo, int *n, int *kd, int *nrhs, double *ab, int *ldab, double *afb, int *ldafb, double *b, int *ldb, double *x, int *ldx, double *ferr, double *berr, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpbrfsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint kd, jint nrhs, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray afb, jint offsetafb, jint ldafb, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpbstf_)(const char *uplo, int *n, int *kd, double *ab, int *ldab, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpbstfK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint kd, jdoubleArray ab, jint offsetab, jint ldab, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpbsv_)(const char *uplo, int *n, int *kd, int *nrhs, double *ab, int *ldab, double *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpbsvK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint kd, jint nrhs, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpbsvx_)(char *fact, const char *uplo, int *n, int *kd, int *nrhs, double *ab, int *ldab, double *afb, int *ldafb, jstring *equed, double *s, double *b, int *ldb, double *x, int *ldx, /*FIXME*/void *rcond, double *ferr, double *berr, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpbsvxK(JNIEnv *env, UNUSED jobject obj,
    jstring fact, jstring uplo, jint n, jint kd, jint nrhs, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray afb, jint offsetafb, jint ldafb, jobject equed, jdoubleArray s, jint offsets, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jobject rcond, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpbtf2_)(const char *uplo, int *n, int *kd, double *ab, int *ldab, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpbtf2K(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint kd, jdoubleArray ab, jint offsetab, jint ldab, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpbtrf_)(const char *uplo, int *n, int *kd, double *ab, int *ldab, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpbtrfK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint kd, jdoubleArray ab, jint offsetab, jint ldab, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpbtrs_)(const char *uplo, int *n, int *kd, int *nrhs, double *ab, int *ldab, double *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpbtrsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint kd, jint nrhs, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpocon_)(const char *uplo, int *n, double *a, int *lda, double *anorm, /*FIXME*/void *rcond, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpoconK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jdouble anorm, jobject rcond, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpoequ_)(int *n, double *a, int *lda, double *s, /*FIXME*/void *scond, /*FIXME*/void *amax, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpoequK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray s, jint offsets, jobject scond, jobject amax, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dporfs_)(const char *uplo, int *n, int *nrhs, double *a, int *lda, double *af, int *ldaf, double *b, int *ldb, double *x, int *ldx, double *ferr, double *berr, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dporfsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jdoubleArray a, jint offseta, jint lda, jdoubleArray af, jint offsetaf, jint ldaf, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dposv_)(const char *uplo, int *n, int *nrhs, double *a, int *lda, double *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dposvK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dposvx_)(char *fact, const char *uplo, int *n, int *nrhs, double *a, int *lda, double *af, int *ldaf, jstring *equed, double *s, double *b, int *ldb, double *x, int *ldx, /*FIXME*/void *rcond, double *ferr, double *berr, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dposvxK(JNIEnv *env, UNUSED jobject obj,
    jstring fact, jstring uplo, jint n, jint nrhs, jdoubleArray a, jint offseta, jint lda, jdoubleArray af, jint offsetaf, jint ldaf, jobject equed, jdoubleArray s, jint offsets, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jobject rcond, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpotf2_)(const char *uplo, int *n, double *a, int *lda, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpotf2K(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpotrf_)(const char *uplo, int *n, double *a, int *lda, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpotrfK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpotri_)(const char *uplo, int *n, double *a, int *lda, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpotriK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpotrs_)(const char *uplo, int *n, int *nrhs, double *a, int *lda, double *b, int *ldb, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpotrsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dppcon_)(const char *uplo, int *n, double *ap, double *anorm, /*FIXME*/void *rcond, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dppconK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray ap, jint offsetap, jdouble anorm, jobject rcond, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dppequ_)(const char *uplo, int *n, double *ap, double *s, /*FIXME*/void *scond, /*FIXME*/void *amax, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dppequK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray ap, jint offsetap, jdoubleArray s, jint offsets, jobject scond, jobject amax, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpprfs_)(const char *uplo, int *n, int *nrhs, double *ap, double *afp, double *b, int *ldb, double *x, int *ldx, double *ferr, double *berr, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpprfsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jdoubleArray ap, jint offsetap, jdoubleArray afp, jint offsetafp, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dppsv_)(const char *uplo, int *n, int *nrhs, double *ap, double *b, int *ldb, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dppsvK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jdoubleArray ap, jint offsetap, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  jboolean failedOOM = FALSE;
  const char *nuplo = NULL; double *nap = NULL, *nb = NULL; int ninfo = 0;
  ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) goto failOOM;
  if (!(nap = (*env)->GetPrimitiveArrayCritical(env, ap, NULL))) goto failOOM;
  if (!(nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) goto failOOM;
  dppsv_(nuplo, &n, &nrhs, nap + offsetap, nb + offsetb, &ldb, &ninfo);
done:
  if (nb) (*env)->ReleasePrimitiveArrayCritical(env, b, nb, failedOOM ? JNI_ABORT : 0);
  if (nap) (*env)->ReleasePrimitiveArrayCritical(env, ap, nap, failedOOM ? JNI_ABORT : 0);
  if (nuplo) (*env)->ReleaseStringUTFChars(env, uplo, nuplo);
  (*env)->SetIntField(env, info, intW_val_fieldID, ninfo);
  if (failedOOM) throwOOM(env);
  return;
failOOM:
  failedOOM = TRUE;
  goto done;
}

static void (*dppsvx_)(char *fact, const char *uplo, int *n, int *nrhs, double *ap, double *afp, jstring *equed, double *s, double *b, int *ldb, double *x, int *ldx, /*FIXME*/void *rcond, double *ferr, double *berr, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dppsvxK(JNIEnv *env, UNUSED jobject obj,
    jstring fact, jstring uplo, jint n, jint nrhs, jdoubleArray ap, jint offsetap, jdoubleArray afp, jint offsetafp, jobject equed, jdoubleArray s, jint offsets, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jobject rcond, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpptrf_)(const char *uplo, int *n, double *ap, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpptrfK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray ap, jint offsetap, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpptri_)(const char *uplo, int *n, double *ap, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpptriK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray ap, jint offsetap, jobject info) {
  jboolean failedOOM = FALSE;
  const char *nuplo = NULL; double *nap = NULL; int ninfo = 0;
  ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) goto failOOM;
  if (!(nap = (*env)->GetPrimitiveArrayCritical(env, ap, NULL))) goto failOOM;
  dpptri_(nuplo, &n, nap + offsetap, &ninfo);
done:
  if (nap) (*env)->ReleasePrimitiveArrayCritical(env, ap, nap, failedOOM ? JNI_ABORT : 0);
  if (nuplo) (*env)->ReleaseStringUTFChars(env, uplo, nuplo);
  (*env)->SetIntField(env, info, intW_val_fieldID, ninfo);
  if (failedOOM) throwOOM(env);
  return;
failOOM:
  failedOOM = TRUE;
  goto done;
}

static void (*dpptrs_)(const char *uplo, int *n, int *nrhs, double *ap, double *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpptrsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jdoubleArray ap, jint offsetap, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dptcon_)(int *n, double *d, double *e, double *anorm, /*FIXME*/void *rcond, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dptconK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdouble anorm, jobject rcond, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpteqr_)(char *compz, int *n, double *d, double *e, double *z, int *ldz, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpteqrK(JNIEnv *env, UNUSED jobject obj,
    jstring compz, jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dptrfs_)(int *n, int *nrhs, double *d, double *e, double *df, double *ef, double *b, int *ldb, double *x, int *ldx, double *ferr, double *berr, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dptrfsK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint nrhs, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray df, jint offsetdf, jdoubleArray ef, jint offsetef, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dptsv_)(int *n, int *nrhs, double *d, double *e, double *b, int *ldb, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dptsvK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint nrhs, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dptsvx_)(char *fact, int *n, int *nrhs, double *d, double *e, double *df, double *ef, double *b, int *ldb, double *x, int *ldx, /*FIXME*/void *rcond, double *ferr, double *berr, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dptsvxK(JNIEnv *env, UNUSED jobject obj,
    jstring fact, jint n, jint nrhs, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray df, jint offsetdf, jdoubleArray ef, jint offsetef, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jobject rcond, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpttrf_)(int *n, double *d, double *e, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpttrfK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dpttrs_)(int *n, int *nrhs, double *d, double *e, double *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpttrsK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint nrhs, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dptts2_)(int *n, int *nrhs, double *d, double *e, double *b, int *ldb);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dptts2K(JNIEnv *env, UNUSED jobject obj,
    jint n, jint nrhs, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray b, jint offsetb, jint ldb) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*drscl_)(int *n, double *sa, double *sx, int *incx);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_drsclK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdouble sa, jdoubleArray sx, jint offsetsx, jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsbev_)(char *jobz, const char *uplo, int *n, int *kd, double *ab, int *ldab, double *w, double *z, int *ldz, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsbevK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring uplo, jint n, jint kd, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray w, jint offsetw, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsbevd_)(char *jobz, const char *uplo, int *n, int *kd, double *ab, int *ldab, double *w, double *z, int *ldz, double *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsbevdK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring uplo, jint n, jint kd, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray w, jint offsetw, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsbevx_)(char *jobz, char *range, const char *uplo, int *n, int *kd, double *ab, int *ldab, double *q, int *ldq, double *vl, double *vu, int *il, int *iu, double *abstol, /*FIXME*/void *m, double *w, double *z, int *ldz, double *work, int * iwork, int * ifail, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsbevxK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring range, jstring uplo, jint n, jint kd, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray q, jint offsetq, jint ldq, jdouble vl, jdouble vu, jint il, jint iu, jdouble abstol, jobject m, jdoubleArray w, jint offsetw, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jintArray ifail, jint offsetifail, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsbgst_)(char *vect, const char *uplo, int *n, int *ka, int *kb, double *ab, int *ldab, double *bb, int *ldbb, double *x, int *ldx, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsbgstK(JNIEnv *env, UNUSED jobject obj,
    jstring vect, jstring uplo, jint n, jint ka, jint kb, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray bb, jint offsetbb, jint ldbb, jdoubleArray x, jint offsetx, jint ldx, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsbgv_)(char *jobz, const char *uplo, int *n, int *ka, int *kb, double *ab, int *ldab, double *bb, int *ldbb, double *w, double *z, int *ldz, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsbgvK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring uplo, jint n, jint ka, jint kb, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray bb, jint offsetbb, jint ldbb, jdoubleArray w, jint offsetw, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsbgvd_)(char *jobz, const char *uplo, int *n, int *ka, int *kb, double *ab, int *ldab, double *bb, int *ldbb, double *w, double *z, int *ldz, double *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsbgvdK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring uplo, jint n, jint ka, jint kb, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray bb, jint offsetbb, jint ldbb, jdoubleArray w, jint offsetw, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsbgvx_)(char *jobz, char *range, const char *uplo, int *n, int *ka, int *kb, double *ab, int *ldab, double *bb, int *ldbb, double *q, int *ldq, double *vl, double *vu, int *il, int *iu, double *abstol, /*FIXME*/void *m, double *w, double *z, int *ldz, double *work, int * iwork, int * ifail, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsbgvxK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring range, jstring uplo, jint n, jint ka, jint kb, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray bb, jint offsetbb, jint ldbb, jdoubleArray q, jint offsetq, jint ldq, jdouble vl, jdouble vu, jint il, jint iu, jdouble abstol, jobject m, jdoubleArray w, jint offsetw, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jintArray ifail, jint offsetifail, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsbtrd_)(char *vect, const char *uplo, int *n, int *kd, double *ab, int *ldab, double *d, double *e, double *q, int *ldq, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsbtrdK(JNIEnv *env, UNUSED jobject obj,
    jstring vect, jstring uplo, jint n, jint kd, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsgesv_)(int *n, int *nrhs, double *a, int *lda, int * ipiv, double *b, int *ldb, double *x, int *ldx, double *work, float *swork, /*FIXME*/void *iter, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsgesvK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint nrhs, jdoubleArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jdoubleArray work, jint offsetwork, jfloatArray swork, jint offsetswork, jobject iter, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dspcon_)(const char *uplo, int *n, double *ap, int * ipiv, double *anorm, /*FIXME*/void *rcond, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dspconK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray ap, jint offsetap, jintArray ipiv, jint offsetipiv, jdouble anorm, jobject rcond, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dspev_)(char *jobz, const char *uplo, int *n, double *ap, double *w, double *z, int *ldz, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dspevK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring uplo, jint n, jdoubleArray ap, jint offsetap, jdoubleArray w, jint offsetw, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dspevd_)(char *jobz, const char *uplo, int *n, double *ap, double *w, double *z, int *ldz, double *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dspevdK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring uplo, jint n, jdoubleArray ap, jint offsetap, jdoubleArray w, jint offsetw, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dspevx_)(char *jobz, char *range, const char *uplo, int *n, double *ap, double *vl, double *vu, int *il, int *iu, double *abstol, /*FIXME*/void *m, double *w, double *z, int *ldz, double *work, int * iwork, int * ifail, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dspevxK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring range, jstring uplo, jint n, jdoubleArray ap, jint offsetap, jdouble vl, jdouble vu, jint il, jint iu, jdouble abstol, jobject m, jdoubleArray w, jint offsetw, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jintArray ifail, jint offsetifail, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dspgst_)(int *itype, const char *uplo, int *n, double *ap, double *bp, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dspgstK(JNIEnv *env, UNUSED jobject obj,
    jint itype, jstring uplo, jint n, jdoubleArray ap, jint offsetap, jdoubleArray bp, jint offsetbp, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dspgv_)(int *itype, char *jobz, const char *uplo, int *n, double *ap, double *bp, double *w, double *z, int *ldz, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dspgvK(JNIEnv *env, UNUSED jobject obj,
    jint itype, jstring jobz, jstring uplo, jint n, jdoubleArray ap, jint offsetap, jdoubleArray bp, jint offsetbp, jdoubleArray w, jint offsetw, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dspgvd_)(int *itype, char *jobz, const char *uplo, int *n, double *ap, double *bp, double *w, double *z, int *ldz, double *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dspgvdK(JNIEnv *env, UNUSED jobject obj,
    jint itype, jstring jobz, jstring uplo, jint n, jdoubleArray ap, jint offsetap, jdoubleArray bp, jint offsetbp, jdoubleArray w, jint offsetw, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dspgvx_)(int *itype, char *jobz, char *range, const char *uplo, int *n, double *ap, double *bp, double *vl, double *vu, int *il, int *iu, double *abstol, /*FIXME*/void *m, double *w, double *z, int *ldz, double *work, int * iwork, int * ifail, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dspgvxK(JNIEnv *env, UNUSED jobject obj,
    jint itype, jstring jobz, jstring range, jstring uplo, jint n, jdoubleArray ap, jint offsetap, jdoubleArray bp, jint offsetbp, jdouble vl, jdouble vu, jint il, jint iu, jdouble abstol, jobject m, jdoubleArray w, jint offsetw, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jintArray ifail, jint offsetifail, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsprfs_)(const char *uplo, int *n, int *nrhs, double *ap, double *afp, int * ipiv, double *b, int *ldb, double *x, int *ldx, double *ferr, double *berr, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsprfsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jdoubleArray ap, jint offsetap, jdoubleArray afp, jint offsetafp, jintArray ipiv, jint offsetipiv, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dspsv_)(const char *uplo, int *n, int *nrhs, double *ap, int * ipiv, double *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dspsvK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jdoubleArray ap, jint offsetap, jintArray ipiv, jint offsetipiv, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dspsvx_)(char *fact, const char *uplo, int *n, int *nrhs, double *ap, double *afp, int * ipiv, double *b, int *ldb, double *x, int *ldx, /*FIXME*/void *rcond, double *ferr, double *berr, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dspsvxK(JNIEnv *env, UNUSED jobject obj,
    jstring fact, jstring uplo, jint n, jint nrhs, jdoubleArray ap, jint offsetap, jdoubleArray afp, jint offsetafp, jintArray ipiv, jint offsetipiv, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jobject rcond, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsptrd_)(const char *uplo, int *n, double *ap, double *d, double *e, double *tau, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsptrdK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray ap, jint offsetap, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray tau, jint offsettau, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsptrf_)(const char *uplo, int *n, double *ap, int * ipiv, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsptrfK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray ap, jint offsetap, jintArray ipiv, jint offsetipiv, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsptri_)(const char *uplo, int *n, double *ap, int * ipiv, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsptriK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray ap, jint offsetap, jintArray ipiv, jint offsetipiv, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsptrs_)(const char *uplo, int *n, int *nrhs, double *ap, int * ipiv, double *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsptrsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jdoubleArray ap, jint offsetap, jintArray ipiv, jint offsetipiv, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dstebz_)(char *range, char *order, int *n, double *vl, double *vu, int *il, int *iu, double *abstol, double *d, double *e, /*FIXME*/void *m, /*FIXME*/void *nsplit, double *w, int * iblock, int * isplit, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dstebzK(JNIEnv *env, UNUSED jobject obj,
    jstring range, jstring order, jint n, jdouble vl, jdouble vu, jint il, jint iu, jdouble abstol, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jobject m, jobject nsplit, jdoubleArray w, jint offsetw, jintArray iblock, jint offsetiblock, jintArray isplit, jint offsetisplit, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dstedc_)(char *compz, int *n, double *d, double *e, double *z, int *ldz, double *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dstedcK(JNIEnv *env, UNUSED jobject obj,
    jstring compz, jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dstegr_)(char *jobz, char *range, int *n, double *d, double *e, double *vl, double *vu, int *il, int *iu, double *abstol, /*FIXME*/void *m, double *w, double *z, int *ldz, int * isuppz, double *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dstegrK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring range, jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdouble vl, jdouble vu, jint il, jint iu, jdouble abstol, jobject m, jdoubleArray w, jint offsetw, jdoubleArray z, jint offsetz, jint ldz, jintArray isuppz, jint offsetisuppz, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dstein_)(int *n, double *d, double *e, int *m, double *w, int * iblock, int * isplit, double *z, int *ldz, double *work, int * iwork, int * ifail, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsteinK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jint m, jdoubleArray w, jint offsetw, jintArray iblock, jint offsetiblock, jintArray isplit, jint offsetisplit, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jintArray ifail, jint offsetifail, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dstemr_)(char *jobz, char *range, int *n, double *d, double *e, double *vl, double *vu, int *il, int *iu, /*FIXME*/void *m, double *w, double *z, int *ldz, int *nzc, int * isuppz, /*FIXME*/void *tryrac, double *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dstemrK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring range, jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdouble vl, jdouble vu, jint il, jint iu, jobject m, jdoubleArray w, jint offsetw, jdoubleArray z, jint offsetz, jint ldz, jint nzc, jintArray isuppz, jint offsetisuppz, jobject tryrac, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsteqr_)(char *compz, int *n, double *d, double *e, double *z, int *ldz, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsteqrK(JNIEnv *env, UNUSED jobject obj,
    jstring compz, jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsterf_)(int *n, double *d, double *e, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsterfK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dstev_)(char *jobz, int *n, double *d, double *e, double *z, int *ldz, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dstevK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dstevd_)(char *jobz, int *n, double *d, double *e, double *z, int *ldz, double *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dstevdK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dstevr_)(char *jobz, char *range, int *n, double *d, double *e, double *vl, double *vu, int *il, int *iu, double *abstol, /*FIXME*/void *m, double *w, double *z, int *ldz, int * isuppz, double *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dstevrK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring range, jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdouble vl, jdouble vu, jint il, jint iu, jdouble abstol, jobject m, jdoubleArray w, jint offsetw, jdoubleArray z, jint offsetz, jint ldz, jintArray isuppz, jint offsetisuppz, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dstevx_)(char *jobz, char *range, int *n, double *d, double *e, double *vl, double *vu, int *il, int *iu, double *abstol, /*FIXME*/void *m, double *w, double *z, int *ldz, double *work, int * iwork, int * ifail, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dstevxK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring range, jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdouble vl, jdouble vu, jint il, jint iu, jdouble abstol, jobject m, jdoubleArray w, jint offsetw, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jintArray ifail, jint offsetifail, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsycon_)(const char *uplo, int *n, double *a, int *lda, int * ipiv, double *anorm, /*FIXME*/void *rcond, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsyconK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jdouble anorm, jobject rcond, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsyev_)(char *jobz, const char *uplo, int *n, double *a, int *lda, double *w, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsyevK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray w, jint offsetw, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsyevd_)(char *jobz, const char *uplo, int *n, double *a, int *lda, double *w, double *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsyevdK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray w, jint offsetw, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsyevr_)(char *jobz, char *range, const char *uplo, int *n, double *a, int *lda, double *vl, double *vu, int *il, int *iu, double *abstol, /*FIXME*/void *m, double *w, double *z, int *ldz, int * isuppz, double *work, int *lwork, int *iwork, int *liwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsyevrK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring range, jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jdouble vl, jdouble vu, jint il, jint iu, jdouble abstol, jobject m, jdoubleArray w, jint offsetw, jdoubleArray z, jint offsetz, jint ldz, jintArray isuppz, jint offsetisuppz, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsyevx_)(char *jobz, char *range, const char *uplo, int *n, double *a, int *lda, double *vl, double *vu, int *il, int *iu, double *abstol, /*FIXME*/void *m, double *w, double *z, int *ldz, double *work, int *lwork, int * iwork, int * ifail, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsyevxK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring range, jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jdouble vl, jdouble vu, jint il, jint iu, jdouble abstol, jobject m, jdoubleArray w, jint offsetw, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jintArray ifail, jint offsetifail, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsygs2_)(int *itype, const char *uplo, int *n, double *a, int *lda, double *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsygs2K(JNIEnv *env, UNUSED jobject obj,
    jint itype, jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsygst_)(int *itype, const char *uplo, int *n, double *a, int *lda, double *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsygstK(JNIEnv *env, UNUSED jobject obj,
    jint itype, jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsygv_)(int *itype, char *jobz, const char *uplo, int *n, double *a, int *lda, double *b, int *ldb, double *w, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsygvK(JNIEnv *env, UNUSED jobject obj,
    jint itype, jstring jobz, jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray w, jint offsetw, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsygvd_)(int *itype, char *jobz, const char *uplo, int *n, double *a, int *lda, double *b, int *ldb, double *w, double *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsygvdK(JNIEnv *env, UNUSED jobject obj,
    jint itype, jstring jobz, jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray w, jint offsetw, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsygvx_)(int *itype, char *jobz, char *range, const char *uplo, int *n, double *a, int *lda, double *b, int *ldb, double *vl, double *vu, int *il, int *iu, double *abstol, /*FIXME*/void *m, double *w, double *z, int *ldz, double *work, int *lwork, int * iwork, int * ifail, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsygvxK(JNIEnv *env, UNUSED jobject obj,
    jint itype, jstring jobz, jstring range, jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdouble vl, jdouble vu, jint il, jint iu, jdouble abstol, jobject m, jdoubleArray w, jint offsetw, jdoubleArray z, jint offsetz, jint ldz, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jintArray ifail, jint offsetifail, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsyrfs_)(const char *uplo, int *n, int *nrhs, double *a, int *lda, double *af, int *ldaf, int * ipiv, double *b, int *ldb, double *x, int *ldx, double *ferr, double *berr, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsyrfsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jdoubleArray a, jint offseta, jint lda, jdoubleArray af, jint offsetaf, jint ldaf, jintArray ipiv, jint offsetipiv, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsysv_)(const char *uplo, int *n, int *nrhs, double *a, int *lda, int * ipiv, double *b, int *ldb, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsysvK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jdoubleArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsysvx_)(char *fact, const char *uplo, int *n, int *nrhs, double *a, int *lda, double *af, int *ldaf, int * ipiv, double *b, int *ldb, double *x, int *ldx, /*FIXME*/void *rcond, double *ferr, double *berr, double *work, int *lwork, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsysvxK(JNIEnv *env, UNUSED jobject obj,
    jstring fact, jstring uplo, jint n, jint nrhs, jdoubleArray a, jint offseta, jint lda, jdoubleArray af, jint offsetaf, jint ldaf, jintArray ipiv, jint offsetipiv, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jobject rcond, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsytd2_)(const char *uplo, int *n, double *a, int *lda, double *d, double *e, double *tau, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsytd2K(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray tau, jint offsettau, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsytf2_)(const char *uplo, int *n, double *a, int *lda, int * ipiv, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsytf2K(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsytrd_)(const char *uplo, int *n, double *a, int *lda, double *d, double *e, double *tau, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsytrdK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsytrf_)(const char *uplo, int *n, double *a, int *lda, int * ipiv, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsytrfK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsytri_)(const char *uplo, int *n, double *a, int *lda, int * ipiv, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsytriK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdoubleArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsytrs_)(const char *uplo, int *n, int *nrhs, double *a, int *lda, int * ipiv, double *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsytrsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jdoubleArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtbcon_)(char *norm, const char *uplo, char *diag, int *n, int *kd, double *ab, int *ldab, /*FIXME*/void *rcond, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtbconK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jstring uplo, jstring diag, jint n, jint kd, jdoubleArray ab, jint offsetab, jint ldab, jobject rcond, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtbrfs_)(const char *uplo, char *trans, char *diag, int *n, int *kd, int *nrhs, double *ab, int *ldab, double *b, int *ldb, double *x, int *ldx, double *ferr, double *berr, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtbrfsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jstring diag, jint n, jint kd, jint nrhs, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtbtrs_)(const char *uplo, char *trans, char *diag, int *n, int *kd, int *nrhs, double *ab, int *ldab, double *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtbtrsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jstring diag, jint n, jint kd, jint nrhs, jdoubleArray ab, jint offsetab, jint ldab, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtgevc_)(char *side, char *howmny, int *select, int *n, double *s, int *lds, double *p, int *ldp, double *vl, int *ldvl, double *vr, int *ldvr, int *mm, /*FIXME*/void *m, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtgevcK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring howmny, jbooleanArray select, jint offsetselect, jint n, jdoubleArray s, jint offsets, jint lds, jdoubleArray p, jint offsetp, jint ldp, jdoubleArray vl, jint offsetvl, jint ldvl, jdoubleArray vr, jint offsetvr, jint ldvr, jint mm, jobject m, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtgex2_)(int *wantq, int *wantz, int *n, double *a, int *lda, double *b, int *ldb, double *q, int *ldq, double *z, int *ldz, int *j1, int *n1, int *n2, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtgex2K(JNIEnv *env, UNUSED jobject obj,
    jboolean wantq, jboolean wantz, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray z, jint offsetz, jint ldz, jint j1, jint n1, jint n2, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtgexc_)(int *wantq, int *wantz, int *n, double *a, int *lda, double *b, int *ldb, double *q, int *ldq, double *z, int *ldz, /*FIXME*/void *ifst, /*FIXME*/void *ilst, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtgexcK(JNIEnv *env, UNUSED jobject obj,
    jboolean wantq, jboolean wantz, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray z, jint offsetz, jint ldz, jobject ifst, jobject ilst, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtgsen_)(int *ijob, int *wantq, int *wantz, int *select, int *n, double *a, int *lda, double *b, int *ldb, double *alphar, double *alphai, double *beta, double *q, int *ldq, double *z, int *ldz, /*FIXME*/void *m, /*FIXME*/void *pl, /*FIXME*/void *pr, double *dif, double *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtgsenK(JNIEnv *env, UNUSED jobject obj,
    jint ijob, jboolean wantq, jboolean wantz, jbooleanArray select, jint offsetselect, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray alphar, jint offsetalphar, jdoubleArray alphai, jint offsetalphai, jdoubleArray beta, jint offsetbeta, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray z, jint offsetz, jint ldz, jobject m, jobject pl, jobject pr, jdoubleArray dif, jint offsetdif, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtgsja_)(char *jobu, char *jobv, char *jobq, int *m, int *p, int *n, int *k, int *l, double *a, int *lda, double *b, int *ldb, double *tola, double *tolb, double *alpha, double *beta, double *u, int *ldu, double *v, int *ldv, double *q, int *ldq, double *work, /*FIXME*/void *ncycle, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtgsjaK(JNIEnv *env, UNUSED jobject obj,
    jstring jobu, jstring jobv, jstring jobq, jint m, jint p, jint n, jint k, jint l, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdouble tola, jdouble tolb, jdoubleArray alpha, jint offsetalpha, jdoubleArray beta, jint offsetbeta, jdoubleArray u, jint offsetu, jint ldu, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray work, jint offsetwork, jobject ncycle, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtgsna_)(char *job, char *howmny, int *select, int *n, double *a, int *lda, double *b, int *ldb, double *vl, int *ldvl, double *vr, int *ldvr, double *s, double *dif, int *mm, /*FIXME*/void *m, double *work, int *lwork, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtgsnaK(JNIEnv *env, UNUSED jobject obj,
    jstring job, jstring howmny, jbooleanArray select, jint offsetselect, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray vl, jint offsetvl, jint ldvl, jdoubleArray vr, jint offsetvr, jint ldvr, jdoubleArray s, jint offsets, jdoubleArray dif, jint offsetdif, jint mm, jobject m, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtgsy2_)(char *trans, int *ijob, int *m, int *n, double *a, int *lda, double *b, int *ldb, double *c, int *Ldc, double *d, int *ldd, double *e, int *lde, double *f, int *ldf, /*FIXME*/void *scale, /*FIXME*/void *rdsum, /*FIXME*/void *rdscal, int * iwork, /*FIXME*/void *pq, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtgsy2K(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint ijob, jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray d, jint offsetd, jint ldd, jdoubleArray e, jint offsete, jint lde, jdoubleArray f, jint offsetf, jint ldf, jobject scale, jobject rdsum, jobject rdscal, jintArray iwork, jint offsetiwork, jobject pq, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtgsyl_)(char *trans, int *ijob, int *m, int *n, double *a, int *lda, double *b, int *ldb, double *c, int *Ldc, double *d, int *ldd, double *e, int *lde, double *f, int *ldf, double *scale, /*FIXME*/void *dif, double *work, int *lwork, int *iwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtgsylK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint ijob, jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray c, jint offsetc, jint Ldc, jdoubleArray d, jint offsetd, jint ldd, jdoubleArray e, jint offsete, jint lde, jdoubleArray f, jint offsetf, jint ldf, jobject scale, jobject dif, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtpcon_)(char *norm, const char *uplo, char *diag, int *n, double *ap, /*FIXME*/void *rcond, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtpconK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jstring uplo, jstring diag, jint n, jdoubleArray ap, jint offsetap, jobject rcond, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtprfs_)(const char *uplo, char *trans, char *diag, int *n, int *nrhs, double *ap, double *b, int *ldb, double *x, int *ldx, double *ferr, double *berr, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtprfsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jstring diag, jint n, jint nrhs, jdoubleArray ap, jint offsetap, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtptri_)(const char *uplo, char *diag, int *n, double *ap, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtptriK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring diag, jint n, jdoubleArray ap, jint offsetap, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtptrs_)(const char *uplo, char *trans, char *diag, int *n, int *nrhs, double *ap, double *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtptrsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jstring diag, jint n, jint nrhs, jdoubleArray ap, jint offsetap, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtrcon_)(char *norm, const char *uplo, char *diag, int *n, double *a, int *lda, /*FIXME*/void *rcond, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtrconK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jstring uplo, jstring diag, jint n, jdoubleArray a, jint offseta, jint lda, jobject rcond, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtrevc_)(char *side, char *howmny, int *select, int *n, double *t, int *ldt, double *vl, int *ldvl, double *vr, int *ldvr, int *mm, /*FIXME*/void *m, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtrevcK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring howmny, jbooleanArray select, jint offsetselect, jint n, jdoubleArray t, jint offsett, jint ldt, jdoubleArray vl, jint offsetvl, jint ldvl, jdoubleArray vr, jint offsetvr, jint ldvr, jint mm, jobject m, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtrexc_)(char *compq, int *n, double *t, int *ldt, double *q, int *ldq, /*FIXME*/void *ifst, /*FIXME*/void *ilst, double *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtrexcK(JNIEnv *env, UNUSED jobject obj,
    jstring compq, jint n, jdoubleArray t, jint offsett, jint ldt, jdoubleArray q, jint offsetq, jint ldq, jobject ifst, jobject ilst, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtrrfs_)(const char *uplo, char *trans, char *diag, int *n, int *nrhs, double *a, int *lda, double *b, int *ldb, double *x, int *ldx, double *ferr, double *berr, double *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtrrfsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jstring diag, jint n, jint nrhs, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray x, jint offsetx, jint ldx, jdoubleArray ferr, jint offsetferr, jdoubleArray berr, jint offsetberr, jdoubleArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtrsen_)(char *job, char *compq, int *select, int *n, double *t, int *ldt, double *q, int *ldq, double *wr, double *wi, /*FIXME*/void *m, /*FIXME*/void *s, /*FIXME*/void *sep, double *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtrsenK(JNIEnv *env, UNUSED jobject obj,
    jstring job, jstring compq, jbooleanArray select, jint offsetselect, jint n, jdoubleArray t, jint offsett, jint ldt, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray wr, jint offsetwr, jdoubleArray wi, jint offsetwi, jobject m, jobject s, jobject sep, jdoubleArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtrsna_)(char *job, char *howmny, int *select, int *n, double *t, int *ldt, double *vl, int *ldvl, double *vr, int *ldvr, double *s, double *sep, int *mm, /*FIXME*/void *m, double *work, int *ldwork, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtrsnaK(JNIEnv *env, UNUSED jobject obj,
    jstring job, jstring howmny, jbooleanArray select, jint offsetselect, jint n, jdoubleArray t, jint offsett, jint ldt, jdoubleArray vl, jint offsetvl, jint ldvl, jdoubleArray vr, jint offsetvr, jint ldvr, jdoubleArray s, jint offsets, jdoubleArray sep, jint offsetsep, jint mm, jobject m, jdoubleArray work, jint offsetwork, jint ldwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtrsyl_)(char *trana, char *tranb, int *isgn, int *m, int *n, double *a, int *lda, double *b, int *ldb, double *c, int *Ldc, /*FIXME*/void *scale, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtrsylK(JNIEnv *env, UNUSED jobject obj,
    jstring trana, jstring tranb, jint isgn, jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdoubleArray c, jint offsetc, jint Ldc, jobject scale, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtrti2_)(const char *uplo, char *diag, int *n, double *a, int *lda, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtrti2K(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring diag, jint n, jdoubleArray a, jint offseta, jint lda, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtrtri_)(const char *uplo, char *diag, int *n, double *a, int *lda, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtrtriK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring diag, jint n, jdoubleArray a, jint offseta, jint lda, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtrtrs_)(const char *uplo, char *trans, char *diag, int *n, int *nrhs, double *a, int *lda, double *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtrtrsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jstring diag, jint n, jint nrhs, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtzrqf_)(int *m, int *n, double *a, int *lda, double *tau, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtzrqfK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtzrzf_)(int *m, int *n, double *a, int *lda, double *tau, double *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtzrzfK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray tau, jint offsettau, jdoubleArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static int (*ieeeck_)(int *ispec, float *zero, float *one);

jint Java_dev_ludovic_netlib_lapack_JNILAPACK_ieeeckK(JNIEnv *env, UNUSED jobject obj,
    jint ispec, jfloat zero, jfloat one) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static int (*ilaenv_)(int *ispec, char *name, char *opts, int *n1, int *n2, int *n3, int *n4);

jint Java_dev_ludovic_netlib_lapack_JNILAPACK_ilaenvK(JNIEnv *env, UNUSED jobject obj,
    jint ispec, jstring name, jstring opts, jint n1, jint n2, jint n3, jint n4) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static void (*ilaver_)(/*FIXME*/void *vers_major, /*FIXME*/void *vers_minor, /*FIXME*/void *vers_patch);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ilaverK(JNIEnv *env, UNUSED jobject obj,
    jobject vers_major, jobject vers_minor, jobject vers_patch) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static int (*iparmq_)(int *ispec, char *name, char *opts, int *n, int *ilo, int *ihi, int *lwork);

jint Java_dev_ludovic_netlib_lapack_JNILAPACK_iparmqK(JNIEnv *env, UNUSED jobject obj,
    jint ispec, jstring name, jstring opts, jint n, jint ilo, jint ihi, jint lwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static int *(*lsamen_)(int *n, char *ca, char *cb);

jboolean Java_dev_ludovic_netlib_lapack_JNILAPACK_lsamenK(JNIEnv *env, UNUSED jobject obj,
    jint n, jstring ca, jstring cb) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static void (*sbdsdc_)(const char *uplo, char *compq, int *n, float *d, float *e, float *u, int *ldu, float *vt, int *ldvt, float *q, int * iq, float *work, int *iwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sbdsdcK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring compq, jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray u, jint offsetu, jint ldu, jfloatArray vt, jint offsetvt, jint ldvt, jfloatArray q, jint offsetq, jintArray iq, jint offsetiq, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sbdsqr_)(const char *uplo, int *n, int *ncvt, int *nru, int *ncc, float *d, float *e, float *vt, int *ldvt, float *u, int *ldu, float *c, int *Ldc, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sbdsqrK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint ncvt, jint nru, jint ncc, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray vt, jint offsetvt, jint ldvt, jfloatArray u, jint offsetu, jint ldu, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sdisna_)(char *job, int *m, int *n, float *d, float *sep, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sdisnaK(JNIEnv *env, UNUSED jobject obj,
    jstring job, jint m, jint n, jfloatArray d, jint offsetd, jfloatArray sep, jint offsetsep, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgbbrd_)(char *vect, int *m, int *n, int *ncc, int *kl, int *ku, float *ab, int *ldab, float *d, float *e, float *q, int *ldq, float *pt, int *ldpt, float *c, int *Ldc, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgbbrdK(JNIEnv *env, UNUSED jobject obj,
    jstring vect, jint m, jint n, jint ncc, jint kl, jint ku, jfloatArray ab, jint offsetab, jint ldab, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray q, jint offsetq, jint ldq, jfloatArray pt, jint offsetpt, jint ldpt, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgbcon_)(char *norm, int *n, int *kl, int *ku, float *ab, int *ldab, int * ipiv, float *anorm, /*FIXME*/void *rcond, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgbconK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jint n, jint kl, jint ku, jfloatArray ab, jint offsetab, jint ldab, jintArray ipiv, jint offsetipiv, jfloat anorm, jobject rcond, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgbequ_)(int *m, int *n, int *kl, int *ku, float *ab, int *ldab, float *r, float *c, /*FIXME*/void *rowcnd, /*FIXME*/void *colcnd, /*FIXME*/void *amax, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgbequK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint kl, jint ku, jfloatArray ab, jint offsetab, jint ldab, jfloatArray r, jint offsetr, jfloatArray c, jint offsetc, jobject rowcnd, jobject colcnd, jobject amax, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgbrfs_)(char *trans, int *n, int *kl, int *ku, int *nrhs, float *ab, int *ldab, float *afb, int *ldafb, int * ipiv, float *b, int *ldb, float *x, int *ldx, float *ferr, float *berr, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgbrfsK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint n, jint kl, jint ku, jint nrhs, jfloatArray ab, jint offsetab, jint ldab, jfloatArray afb, jint offsetafb, jint ldafb, jintArray ipiv, jint offsetipiv, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgbsv_)(int *n, int *kl, int *ku, int *nrhs, float *ab, int *ldab, int *ipiv, float *b, int *ldb, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgbsvK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint kl, jint ku, jint nrhs, jfloatArray ab, jint offsetab, jint ldab, jintArray ipiv, jint offsetipiv, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgbsvx_)(char *fact, char *trans, int *n, int *kl, int *ku, int *nrhs, float *ab, int *ldab, float *afb, int *ldafb, int * ipiv, jstring *equed, float *r, float *c, float *b, int *ldb, float *x, int *ldx, /*FIXME*/void *rcond, float *ferr, float *berr, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgbsvxK(JNIEnv *env, UNUSED jobject obj,
    jstring fact, jstring trans, jint n, jint kl, jint ku, jint nrhs, jfloatArray ab, jint offsetab, jint ldab, jfloatArray afb, jint offsetafb, jint ldafb, jintArray ipiv, jint offsetipiv, jobject equed, jfloatArray r, jint offsetr, jfloatArray c, jint offsetc, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jobject rcond, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgbtf2_)(int *m, int *n, int *kl, int *ku, float *ab, int *ldab, int * ipiv, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgbtf2K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint kl, jint ku, jfloatArray ab, jint offsetab, jint ldab, jintArray ipiv, jint offsetipiv, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgbtrf_)(int *m, int *n, int *kl, int *ku, float *ab, int *ldab, int * ipiv, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgbtrfK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint kl, jint ku, jfloatArray ab, jint offsetab, jint ldab, jintArray ipiv, jint offsetipiv, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgbtrs_)(char *trans, int *n, int *kl, int *ku, int *nrhs, float *ab, int *ldab, int * ipiv, float *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgbtrsK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint n, jint kl, jint ku, jint nrhs, jfloatArray ab, jint offsetab, jint ldab, jintArray ipiv, jint offsetipiv, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgebak_)(char *job, char *side, int *n, int *ilo, int *ihi, float *scale, int *m, float *v, int *ldv, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgebakK(JNIEnv *env, UNUSED jobject obj,
    jstring job, jstring side, jint n, jint ilo, jint ihi, jfloatArray scale, jint offsetscale, jint m, jfloatArray v, jint offsetv, jint ldv, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgebal_)(char *job, int *n, float *a, int *lda, /*FIXME*/void *ilo, /*FIXME*/void *ihi, float *scale, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgebalK(JNIEnv *env, UNUSED jobject obj,
    jstring job, jint n, jfloatArray a, jint offseta, jint lda, jobject ilo, jobject ihi, jfloatArray scale, jint offsetscale, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgebd2_)(int *m, int *n, float *a, int *lda, float *d, float *e, float *tauq, float *taup, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgebd2K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray tauq, jint offsettauq, jfloatArray taup, jint offsettaup, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgebrd_)(int *m, int *n, float *a, int *lda, float *d, float *e, float *tauq, float *taup, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgebrdK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray tauq, jint offsettauq, jfloatArray taup, jint offsettaup, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgecon_)(char *norm, int *n, float *a, int *lda, float *anorm, /*FIXME*/void *rcond, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeconK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jint n, jfloatArray a, jint offseta, jint lda, jfloat anorm, jobject rcond, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgeequ_)(int *m, int *n, float *a, int *lda, float *r, float *c, /*FIXME*/void *rowcnd, /*FIXME*/void *colcnd, /*FIXME*/void *amax, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeequK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray r, jint offsetr, jfloatArray c, jint offsetc, jobject rowcnd, jobject colcnd, jobject amax, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgees_)(char *jobvs, char *sort, /*FIXME*/void *select, int *n, float *a, int *lda, /*FIXME*/void *sdim, float *wr, float *wi, float *vs, int *ldvs, float *work, int *lwork, int *bwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeesK(JNIEnv *env, UNUSED jobject obj,
    jstring jobvs, jstring sort, jobject select, jint n, jfloatArray a, jint offseta, jint lda, jobject sdim, jfloatArray wr, jint offsetwr, jfloatArray wi, jint offsetwi, jfloatArray vs, jint offsetvs, jint ldvs, jfloatArray work, jint offsetwork, jint lwork, jbooleanArray bwork, jint offsetbwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgeesx_)(char *jobvs, char *sort, /*FIXME*/void *select, char *sense, int *n, float *a, int *lda, /*FIXME*/void *sdim, float *wr, float *wi, float *vs, int *ldvs, /*FIXME*/void *rconde, /*FIXME*/void *rcondv, float *work, int *lwork, int * iwork, int *liwork, int *bwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeesxK(JNIEnv *env, UNUSED jobject obj,
    jstring jobvs, jstring sort, jobject select, jstring sense, jint n, jfloatArray a, jint offseta, jint lda, jobject sdim, jfloatArray wr, jint offsetwr, jfloatArray wi, jint offsetwi, jfloatArray vs, jint offsetvs, jint ldvs, jobject rconde, jobject rcondv, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jbooleanArray bwork, jint offsetbwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgeev_)(char *jobvl, char *jobvr, int *n, float *a, int *lda, float *wr, float *wi, float *vl, int *ldvl, float *vr, int *ldvr, float *work, int *lwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeevK(JNIEnv *env, UNUSED jobject obj,
    jstring jobvl, jstring jobvr, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray wr, jint offsetwr, jfloatArray wi, jint offsetwi, jfloatArray vl, jint offsetvl, jint ldvl, jfloatArray vr, jint offsetvr, jint ldvr, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgeevx_)(char *balanc, char *jobvl, char *jobvr, char *sense, int *n, float *a, int *lda, float *wr, float *wi, float *vl, int *ldvl, float *vr, int *ldvr, /*FIXME*/void *ilo, /*FIXME*/void *ihi, float *scale, /*FIXME*/void *abnrm, float *rconde, float *rcondv, float *work, int *lwork, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeevxK(JNIEnv *env, UNUSED jobject obj,
    jstring balanc, jstring jobvl, jstring jobvr, jstring sense, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray wr, jint offsetwr, jfloatArray wi, jint offsetwi, jfloatArray vl, jint offsetvl, jint ldvl, jfloatArray vr, jint offsetvr, jint ldvr, jobject ilo, jobject ihi, jfloatArray scale, jint offsetscale, jobject abnrm, jfloatArray rconde, jint offsetrconde, jfloatArray rcondv, jint offsetrcondv, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgegs_)(char *jobvsl, char *jobvsr, int *n, float *a, int *lda, float *b, int *ldb, float *alphar, float *alphai, float *beta, float *vsl, int *ldvsl, float *vsr, int *ldvsr, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgegsK(JNIEnv *env, UNUSED jobject obj,
    jstring jobvsl, jstring jobvsr, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray alphar, jint offsetalphar, jfloatArray alphai, jint offsetalphai, jfloatArray beta, jint offsetbeta, jfloatArray vsl, jint offsetvsl, jint ldvsl, jfloatArray vsr, jint offsetvsr, jint ldvsr, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgegv_)(char *jobvl, char *jobvr, int *n, float *a, int *lda, float *b, int *ldb, float *alphar, float *alphai, float *beta, float *vl, int *ldvl, float *vr, int *ldvr, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgegvK(JNIEnv *env, UNUSED jobject obj,
    jstring jobvl, jstring jobvr, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray alphar, jint offsetalphar, jfloatArray alphai, jint offsetalphai, jfloatArray beta, jint offsetbeta, jfloatArray vl, jint offsetvl, jint ldvl, jfloatArray vr, jint offsetvr, jint ldvr, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgehd2_)(int *n, int *ilo, int *ihi, float *a, int *lda, float *tau, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgehd2K(JNIEnv *env, UNUSED jobject obj,
    jint n, jint ilo, jint ihi, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgehrd_)(int *n, int *ilo, int *ihi, float *a, int *lda, float *tau, float *work, int *lwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgehrdK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint ilo, jint ihi, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgelq2_)(int *m, int *n, float *a, int *lda, float *tau, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgelq2K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgelqf_)(int *m, int *n, float *a, int *lda, float *tau, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgelqfK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgels_)(char *trans, int *m, int *n, int *nrhs, float *a, int *lda, float *b, int *ldb, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgelsK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint m, jint n, jint nrhs, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgelsd_)(int *m, int *n, int *nrhs, float *a, int *lda, float *b, int *ldb, float *s, float *rcond, /*FIXME*/void *rank, float *work, int *lwork, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgelsdK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint nrhs, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray s, jint offsets, jfloat rcond, jobject rank, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgelss_)(int *m, int *n, int *nrhs, float *a, int *lda, float *b, int *ldb, float *s, float *rcond, /*FIXME*/void *rank, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgelssK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint nrhs, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray s, jint offsets, jfloat rcond, jobject rank, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgelsx_)(int *m, int *n, int *nrhs, float *a, int *lda, float *b, int *ldb, int * jpvt, float *rcond, /*FIXME*/void *rank, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgelsxK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint nrhs, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jintArray jpvt, jint offsetjpvt, jfloat rcond, jobject rank, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgelsy_)(int *m, int *n, int *nrhs, float *a, int *lda, float *b, int *ldb, int * jpvt, float *rcond, /*FIXME*/void *rank, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgelsyK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint nrhs, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jintArray jpvt, jint offsetjpvt, jfloat rcond, jobject rank, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgeql2_)(int *m, int *n, float *a, int *lda, float *tau, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeql2K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgeqlf_)(int *m, int *n, float *a, int *lda, float *tau, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeqlfK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgeqp3_)(int *m, int *n, float *a, int *lda, int * jpvt, float *tau, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeqp3K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloatArray a, jint offseta, jint lda, jintArray jpvt, jint offsetjpvt, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgeqpf_)(int *m, int *n, float *a, int *lda, int * jpvt, float *tau, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeqpfK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloatArray a, jint offseta, jint lda, jintArray jpvt, jint offsetjpvt, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgeqr2_)(int *m, int *n, float *a, int *lda, float *tau, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeqr2K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgeqrf_)(int *m, int *n, float *a, int *lda, float *tau, float *work, int *lwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeqrfK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgerfs_)(char *trans, int *n, int *nrhs, float *a, int *lda, float *af, int *ldaf, int * ipiv, float *b, int *ldb, float *x, int *ldx, float *ferr, float *berr, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgerfsK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint n, jint nrhs, jfloatArray a, jint offseta, jint lda, jfloatArray af, jint offsetaf, jint ldaf, jintArray ipiv, jint offsetipiv, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgerq2_)(int *m, int *n, float *a, int *lda, float *tau, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgerq2K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgerqf_)(int *m, int *n, float *a, int *lda, float *tau, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgerqfK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgesc2_)(int *n, float *a, int *lda, float *rhs, int *ipiv, int *jpiv, /*FIXME*/void *scale);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgesc2K(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray a, jint offseta, jint lda, jfloatArray rhs, jint offsetrhs, jintArray ipiv, jint offsetipiv, jintArray jpiv, jint offsetjpiv, jobject scale) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgesdd_)(char *jobz, int *m, int *n, float *a, int *lda, float *s, float *u, int *ldu, float *vt, int *ldvt, float *work, int *lwork, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgesddK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray s, jint offsets, jfloatArray u, jint offsetu, jint ldu, jfloatArray vt, jint offsetvt, jint ldvt, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgesv_)(int *n, int *nrhs, float *a, int *lda, int * ipiv, float *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgesvK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint nrhs, jfloatArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgesvd_)(char *jobu, char *jobvt, int *m, int *n, float *a, int *lda, float *s, float *u, int *ldu, float *vt, int *ldvt, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgesvdK(JNIEnv *env, UNUSED jobject obj,
    jstring jobu, jstring jobvt, jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray s, jint offsets, jfloatArray u, jint offsetu, jint ldu, jfloatArray vt, jint offsetvt, jint ldvt, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgesvx_)(char *fact, char *trans, int *n, int *nrhs, float *a, int *lda, float *af, int *ldaf, int * ipiv, jstring *equed, float *r, float *c, float *b, int *ldb, float *x, int *ldx, /*FIXME*/void *rcond, float *ferr, float *berr, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgesvxK(JNIEnv *env, UNUSED jobject obj,
    jstring fact, jstring trans, jint n, jint nrhs, jfloatArray a, jint offseta, jint lda, jfloatArray af, jint offsetaf, jint ldaf, jintArray ipiv, jint offsetipiv, jobject equed, jfloatArray r, jint offsetr, jfloatArray c, jint offsetc, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jobject rcond, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgetc2_)(int *n, float *a, int *lda, int * ipiv, int * jpiv, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgetc2K(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jintArray jpiv, jint offsetjpiv, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgetf2_)(int *m, int *n, float *a, int *lda, int * ipiv, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgetf2K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloatArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgetrf_)(int *m, int *n, float *a, int *lda, int * ipiv, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgetrfK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloatArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgetri_)(int *n, float *a, int *lda, int * ipiv, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgetriK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgetrs_)(char *trans, int *n, int *nrhs, float *a, int *lda, int * ipiv, float *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgetrsK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint n, jint nrhs, jfloatArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sggbak_)(char *job, char *side, int *n, int *ilo, int *ihi, float *lscale, float *rscale, int *m, float *v, int *ldv, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggbakK(JNIEnv *env, UNUSED jobject obj,
    jstring job, jstring side, jint n, jint ilo, jint ihi, jfloatArray lscale, jint offsetlscale, jfloatArray rscale, jint offsetrscale, jint m, jfloatArray v, jint offsetv, jint ldv, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sggbal_)(char *job, int *n, float *a, int *lda, float *b, int *ldb, /*FIXME*/void *ilo, /*FIXME*/void *ihi, float *lscale, float *rscale, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggbalK(JNIEnv *env, UNUSED jobject obj,
    jstring job, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jobject ilo, jobject ihi, jfloatArray lscale, jint offsetlscale, jfloatArray rscale, jint offsetrscale, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgges_)(char *jobvsl, char *jobvsr, char *sort, /*FIXME*/void *selctg, int *n, float *a, int *lda, float *b, int *ldb, /*FIXME*/void *sdim, float *alphar, float *alphai, float *beta, float *vsl, int *ldvsl, float *vsr, int *ldvsr, float *work, int *lwork, int *bwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggesK(JNIEnv *env, UNUSED jobject obj,
    jstring jobvsl, jstring jobvsr, jstring sort, jobject selctg, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jobject sdim, jfloatArray alphar, jint offsetalphar, jfloatArray alphai, jint offsetalphai, jfloatArray beta, jint offsetbeta, jfloatArray vsl, jint offsetvsl, jint ldvsl, jfloatArray vsr, jint offsetvsr, jint ldvsr, jfloatArray work, jint offsetwork, jint lwork, jbooleanArray bwork, jint offsetbwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sggesx_)(char *jobvsl, char *jobvsr, char *sort, /*FIXME*/void *selctg, char *sense, int *n, float *a, int *lda, float *b, int *ldb, /*FIXME*/void *sdim, float *alphar, float *alphai, float *beta, float *vsl, int *ldvsl, float *vsr, int *ldvsr, float *rconde, float *rcondv, float *work, int *lwork, int * iwork, int *liwork, int *bwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggesxK(JNIEnv *env, UNUSED jobject obj,
    jstring jobvsl, jstring jobvsr, jstring sort, jobject selctg, jstring sense, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jobject sdim, jfloatArray alphar, jint offsetalphar, jfloatArray alphai, jint offsetalphai, jfloatArray beta, jint offsetbeta, jfloatArray vsl, jint offsetvsl, jint ldvsl, jfloatArray vsr, jint offsetvsr, jint ldvsr, jfloatArray rconde, jint offsetrconde, jfloatArray rcondv, jint offsetrcondv, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jbooleanArray bwork, jint offsetbwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sggev_)(char *jobvl, char *jobvr, int *n, float *a, int *lda, float *b, int *ldb, float *alphar, float *alphai, float *beta, float *vl, int *ldvl, float *vr, int *ldvr, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggevK(JNIEnv *env, UNUSED jobject obj,
    jstring jobvl, jstring jobvr, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray alphar, jint offsetalphar, jfloatArray alphai, jint offsetalphai, jfloatArray beta, jint offsetbeta, jfloatArray vl, jint offsetvl, jint ldvl, jfloatArray vr, jint offsetvr, jint ldvr, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sggevx_)(char *balanc, char *jobvl, char *jobvr, char *sense, int *n, float *a, int *lda, float *b, int *ldb, float *alphar, float *alphai, float *beta, float *vl, int *ldvl, float *vr, int *ldvr, /*FIXME*/void *ilo, /*FIXME*/void *ihi, float *lscale, float *rscale, /*FIXME*/void *abnrm, /*FIXME*/void *bbnrm, float *rconde, float *rcondv, float *work, int *lwork, int * iwork, int *bwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggevxK(JNIEnv *env, UNUSED jobject obj,
    jstring balanc, jstring jobvl, jstring jobvr, jstring sense, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray alphar, jint offsetalphar, jfloatArray alphai, jint offsetalphai, jfloatArray beta, jint offsetbeta, jfloatArray vl, jint offsetvl, jint ldvl, jfloatArray vr, jint offsetvr, jint ldvr, jobject ilo, jobject ihi, jfloatArray lscale, jint offsetlscale, jfloatArray rscale, jint offsetrscale, jobject abnrm, jobject bbnrm, jfloatArray rconde, jint offsetrconde, jfloatArray rcondv, jint offsetrcondv, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jbooleanArray bwork, jint offsetbwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sggglm_)(int *n, int *m, int *p, float *a, int *lda, float *b, int *ldb, float *d, float *x, float *y, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggglmK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint m, jint p, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray d, jint offsetd, jfloatArray x, jint offsetx, jfloatArray y, jint offsety, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgghrd_)(char *compq, char *compz, int *n, int *ilo, int *ihi, float *a, int *lda, float *b, int *ldb, float *q, int *ldq, float *z, int *ldz, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgghrdK(JNIEnv *env, UNUSED jobject obj,
    jstring compq, jstring compz, jint n, jint ilo, jint ihi, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray q, jint offsetq, jint ldq, jfloatArray z, jint offsetz, jint ldz, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgglse_)(int *m, int *n, int *p, float *a, int *lda, float *b, int *ldb, float *c, float *d, float *x, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgglseK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint p, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray c, jint offsetc, jfloatArray d, jint offsetd, jfloatArray x, jint offsetx, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sggqrf_)(int *n, int *m, int *p, float *a, int *lda, float *taua, float *b, int *ldb, float *taub, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggqrfK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint m, jint p, jfloatArray a, jint offseta, jint lda, jfloatArray taua, jint offsettaua, jfloatArray b, jint offsetb, jint ldb, jfloatArray taub, jint offsettaub, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sggrqf_)(int *m, int *p, int *n, float *a, int *lda, float *taua, float *b, int *ldb, float *taub, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggrqfK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint p, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray taua, jint offsettaua, jfloatArray b, jint offsetb, jint ldb, jfloatArray taub, jint offsettaub, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sggsvd_)(char *jobu, char *jobv, char *jobq, int *m, int *n, int *p, /*FIXME*/void *k, /*FIXME*/void *l, float *a, int *lda, float *b, int *ldb, float *alpha, float *beta, float *u, int *ldu, float *v, int *ldv, float *q, int *ldq, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggsvdK(JNIEnv *env, UNUSED jobject obj,
    jstring jobu, jstring jobv, jstring jobq, jint m, jint n, jint p, jobject k, jobject l, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray alpha, jint offsetalpha, jfloatArray beta, jint offsetbeta, jfloatArray u, jint offsetu, jint ldu, jfloatArray v, jint offsetv, jint ldv, jfloatArray q, jint offsetq, jint ldq, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sggsvp_)(char *jobu, char *jobv, char *jobq, int *m, int *p, int *n, float *a, int *lda, float *b, int *ldb, float *tola, float *tolb, /*FIXME*/void *k, /*FIXME*/void *l, float *u, int *ldu, float *v, int *ldv, float *q, int *ldq, int * iwork, float *tau, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggsvpK(JNIEnv *env, UNUSED jobject obj,
    jstring jobu, jstring jobv, jstring jobq, jint m, jint p, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloat tola, jfloat tolb, jobject k, jobject l, jfloatArray u, jint offsetu, jint ldu, jfloatArray v, jint offsetv, jint ldv, jfloatArray q, jint offsetq, jint ldq, jintArray iwork, jint offsetiwork, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgtcon_)(char *norm, int *n, float *dl, float *d, float *du, float *du22, int * ipiv, float *anorm, /*FIXME*/void *rcond, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgtconK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jint n, jfloatArray dl, jint offsetdl, jfloatArray d, jint offsetd, jfloatArray du, jint offsetdu, jfloatArray du2, jint offsetdu2, jintArray ipiv, jint offsetipiv, jfloat anorm, jobject rcond, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgtrfs_)(char *trans, int *n, int *nrhs, float *dl, float *d, float *du, float *dlf, float *df, float *duf, float *du22, int * ipiv, float *b, int *ldb, float *x, int *ldx, float *ferr, float *berr, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgtrfsK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint n, jint nrhs, jfloatArray dl, jint offsetdl, jfloatArray d, jint offsetd, jfloatArray du, jint offsetdu, jfloatArray dlf, jint offsetdlf, jfloatArray df, jint offsetdf, jfloatArray duf, jint offsetduf, jfloatArray du2, jint offsetdu2, jintArray ipiv, jint offsetipiv, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgtsv_)(int *n, int *nrhs, float *dl, float *d, float *du, float *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgtsvK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint nrhs, jfloatArray dl, jint offsetdl, jfloatArray d, jint offsetd, jfloatArray du, jint offsetdu, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgtsvx_)(char *fact, char *trans, int *n, int *nrhs, float *dl, float *d, float *du, float *dlf, float *df, float *duf, float *du22, int * ipiv, float *b, int *ldb, float *x, int *ldx, /*FIXME*/void *rcond, float *ferr, float *berr, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgtsvxK(JNIEnv *env, UNUSED jobject obj,
    jstring fact, jstring trans, jint n, jint nrhs, jfloatArray dl, jint offsetdl, jfloatArray d, jint offsetd, jfloatArray du, jint offsetdu, jfloatArray dlf, jint offsetdlf, jfloatArray df, jint offsetdf, jfloatArray duf, jint offsetduf, jfloatArray du2, jint offsetdu2, jintArray ipiv, jint offsetipiv, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jobject rcond, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgttrf_)(int *n, float *dl, float *d, float *du, float *du22, int * ipiv, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgttrfK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray dl, jint offsetdl, jfloatArray d, jint offsetd, jfloatArray du, jint offsetdu, jfloatArray du2, jint offsetdu2, jintArray ipiv, jint offsetipiv, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgttrs_)(char *trans, int *n, int *nrhs, float *dl, float *d, float *du, float *du22, int * ipiv, float *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgttrsK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint n, jint nrhs, jfloatArray dl, jint offsetdl, jfloatArray d, jint offsetd, jfloatArray du, jint offsetdu, jfloatArray du2, jint offsetdu2, jintArray ipiv, jint offsetipiv, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgtts2_)(int *itrans, int *n, int *nrhs, float *dl, float *d, float *du, float *du22, int * ipiv, float *b, int *ldb);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgtts2K(JNIEnv *env, UNUSED jobject obj,
    jint itrans, jint n, jint nrhs, jfloatArray dl, jint offsetdl, jfloatArray d, jint offsetd, jfloatArray du, jint offsetdu, jfloatArray du2, jint offsetdu2, jintArray ipiv, jint offsetipiv, jfloatArray b, jint offsetb, jint ldb) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*shgeqz_)(char *job, char *compq, char *compz, int *n, int *ilo, int *ihi, float *h, int *ldh, float *t, int *ldt, float *alphar, float *alphai, float *beta, float *q, int *ldq, float *z, int *ldz, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_shgeqzK(JNIEnv *env, UNUSED jobject obj,
    jstring job, jstring compq, jstring compz, jint n, jint ilo, jint ihi, jfloatArray h, jint offseth, jint ldh, jfloatArray t, jint offsett, jint ldt, jfloatArray alphar, jint offsetalphar, jfloatArray alphai, jint offsetalphai, jfloatArray beta, jint offsetbeta, jfloatArray q, jint offsetq, jint ldq, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*shsein_)(char *side, char *eigsrc, char *initv, int *select, int *n, float *h, int *ldh, float *wr, float *wi, float *vl, int *ldvl, float *vr, int *ldvr, int *mm, /*FIXME*/void *m, float *work, int * ifaill, int * ifailr, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_shseinK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring eigsrc, jstring initv, jbooleanArray select, jint offsetselect, jint n, jfloatArray h, jint offseth, jint ldh, jfloatArray wr, jint offsetwr, jfloatArray wi, jint offsetwi, jfloatArray vl, jint offsetvl, jint ldvl, jfloatArray vr, jint offsetvr, jint ldvr, jint mm, jobject m, jfloatArray work, jint offsetwork, jintArray ifaill, jint offsetifaill, jintArray ifailr, jint offsetifailr, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*shseqr_)(char *job, char *compz, int *n, int *ilo, int *ihi, float *h, int *ldh, float *wr, float *wi, float *z, int *ldz, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_shseqrK(JNIEnv *env, UNUSED jobject obj,
    jstring job, jstring compz, jint n, jint ilo, jint ihi, jfloatArray h, jint offseth, jint ldh, jfloatArray wr, jint offsetwr, jfloatArray wi, jint offsetwi, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static int *(*sisnan_)(float *sin);

jboolean Java_dev_ludovic_netlib_lapack_JNILAPACK_sisnanK(JNIEnv *env, UNUSED jobject obj,
    jfloat sin) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static void (*slabad_)(/*FIXME*/void *small, /*FIXME*/void *large);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slabadK(JNIEnv *env, UNUSED jobject obj,
    jobject small, jobject large) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slabrd_)(int *m, int *n, int *nb, float *a, int *lda, float *d, float *e, float *tauq, float *taup, float *x, int *ldx, float *y, int *ldy);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slabrdK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint nb, jfloatArray a, jint offseta, jint lda, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray tauq, jint offsettauq, jfloatArray taup, jint offsettaup, jfloatArray x, jint offsetx, jint ldx, jfloatArray y, jint offsety, jint ldy) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slacn2_)(int *n, float *v, float *x, int * isgn, /*FIXME*/void *est, /*FIXME*/void *kase, int * isave);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slacn2K(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray v, jint offsetv, jfloatArray x, jint offsetx, jintArray isgn, jint offsetisgn, jobject est, jobject kase, jintArray isave, jint offsetisave) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slacon_)(int *n, float *v, float *x, int * isgn, /*FIXME*/void *est, /*FIXME*/void *kase);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaconK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray v, jint offsetv, jfloatArray x, jint offsetx, jintArray isgn, jint offsetisgn, jobject est, jobject kase) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slacpy_)(const char *uplo, int *m, int *n, float *a, int *lda, float *b, int *ldb);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slacpyK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sladiv_)(float *a, float *b, float *c, float *d, /*FIXME*/void *p, /*FIXME*/void *q);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sladivK(JNIEnv *env, UNUSED jobject obj,
    jfloat a, jfloat b, jfloat c, jfloat d, jobject p, jobject q) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slae2_)(float *a, float *b, float *c, /*FIXME*/void *rt1, /*FIXME*/void *rt2);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slae2K(JNIEnv *env, UNUSED jobject obj,
    jfloat a, jfloat b, jfloat c, jobject rt1, jobject rt2) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaebz_)(int *ijob, int *nitmax, int *n, int *mmax, int *minp, int *nbmin, float *abstol, float *reltol, float *pivmin, float *d, float *e, float *e22, int * nval, float *ab, float *c, /*FIXME*/void *mout, int * nab, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaebzK(JNIEnv *env, UNUSED jobject obj,
    jint ijob, jint nitmax, jint n, jint mmax, jint minp, jint nbmin, jfloat abstol, jfloat reltol, jfloat pivmin, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray e2, jint offsete2, jintArray nval, jint offsetnval, jfloatArray ab, jint offsetab, jfloatArray c, jint offsetc, jobject mout, jintArray nab, jint offsetnab, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaed0_)(int *icompq, int *qsiz, int *n, float *d, float *e, float *q, int *ldq, float *qstore, int *ldqs, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaed0K(JNIEnv *env, UNUSED jobject obj,
    jint icompq, jint qsiz, jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray q, jint offsetq, jint ldq, jfloatArray qstore, jint offsetqstore, jint ldqs, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaed1_)(int *n, float *d, float *q, int *ldq, int * indxq, /*FIXME*/void *rho, int *cutpnt, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaed1K(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray d, jint offsetd, jfloatArray q, jint offsetq, jint ldq, jintArray indxq, jint offsetindxq, jobject rho, jint cutpnt, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaed2_)(/*FIXME*/void *k, int *n, int *n1, float *d, float *q, int *ldq, int * indxq, /*FIXME*/void *rho, float *z, float *dlamda, float *w, float *q22, int * indx, int * indxc, int * indxp, int * coltyp, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaed2K(JNIEnv *env, UNUSED jobject obj,
    jobject k, jint n, jint n1, jfloatArray d, jint offsetd, jfloatArray q, jint offsetq, jint ldq, jintArray indxq, jint offsetindxq, jobject rho, jfloatArray z, jint offsetz, jfloatArray dlamda, jint offsetdlamda, jfloatArray w, jint offsetw, jfloatArray q2, jint offsetq2, jintArray indx, jint offsetindx, jintArray indxc, jint offsetindxc, jintArray indxp, jint offsetindxp, jintArray coltyp, jint offsetcoltyp, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaed3_)(int *k, int *n, int *n1, float *d, float *q, int *ldq, float *rho, float *dlamda, float *q22, int * indx, int * ctot, float *w, float *s, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaed3K(JNIEnv *env, UNUSED jobject obj,
    jint k, jint n, jint n1, jfloatArray d, jint offsetd, jfloatArray q, jint offsetq, jint ldq, jfloat rho, jfloatArray dlamda, jint offsetdlamda, jfloatArray q2, jint offsetq2, jintArray indx, jint offsetindx, jintArray ctot, jint offsetctot, jfloatArray w, jint offsetw, jfloatArray s, jint offsets, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaed4_)(int *n, int *i, float *d, float *z, float *delta, float *rho, /*FIXME*/void *dlam, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaed4K(JNIEnv *env, UNUSED jobject obj,
    jint n, jint i, jfloatArray d, jint offsetd, jfloatArray z, jint offsetz, jfloatArray delta, jint offsetdelta, jfloat rho, jobject dlam, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaed5_)(int *i, float *d, float *z, float *delta, float *rho, /*FIXME*/void *dlam);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaed5K(JNIEnv *env, UNUSED jobject obj,
    jint i, jfloatArray d, jint offsetd, jfloatArray z, jint offsetz, jfloatArray delta, jint offsetdelta, jfloat rho, jobject dlam) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaed6_)(int *kniter, int *orgati, float *rho, float *d, float *z, float *finit, /*FIXME*/void *tau, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaed6K(JNIEnv *env, UNUSED jobject obj,
    jint kniter, jboolean orgati, jfloat rho, jfloatArray d, jint offsetd, jfloatArray z, jint offsetz, jfloat finit, jobject tau, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaed7_)(int *icompq, int *n, int *qsiz, int *tlvls, int *curlvl, int *curpbm, float *d, float *q, int *ldq, int * indxq, /*FIXME*/void *rho, int *cutpnt, float *qstore, int * qptr, int * prmptr, int * perm, int * givptr, int * givcol, float *givnum, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaed7K(JNIEnv *env, UNUSED jobject obj,
    jint icompq, jint n, jint qsiz, jint tlvls, jint curlvl, jint curpbm, jfloatArray d, jint offsetd, jfloatArray q, jint offsetq, jint ldq, jintArray indxq, jint offsetindxq, jobject rho, jint cutpnt, jfloatArray qstore, jint offsetqstore, jintArray qptr, jint offsetqptr, jintArray prmptr, jint offsetprmptr, jintArray perm, jint offsetperm, jintArray givptr, jint offsetgivptr, jintArray givcol, jint offsetgivcol, jfloatArray givnum, jint offsetgivnum, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaed8_)(int *icompq, /*FIXME*/void *k, int *n, int *qsiz, float *d, float *q, int *ldq, int * indxq, /*FIXME*/void *rho, int *cutpnt, float *z, float *dlamda, float *q22, int *ldq2, float *w, int * perm, /*FIXME*/void *givptr, int * givcol, float *givnum, int * indxp, int * indx, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaed8K(JNIEnv *env, UNUSED jobject obj,
    jint icompq, jobject k, jint n, jint qsiz, jfloatArray d, jint offsetd, jfloatArray q, jint offsetq, jint ldq, jintArray indxq, jint offsetindxq, jobject rho, jint cutpnt, jfloatArray z, jint offsetz, jfloatArray dlamda, jint offsetdlamda, jfloatArray q2, jint offsetq2, jint ldq2, jfloatArray w, jint offsetw, jintArray perm, jint offsetperm, jobject givptr, jintArray givcol, jint offsetgivcol, jfloatArray givnum, jint offsetgivnum, jintArray indxp, jint offsetindxp, jintArray indx, jint offsetindx, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaed9_)(int *k, int *kstart, int *kstop, int *n, float *d, float *q, int *ldq, float *rho, float *dlamda, float *w, float *s, int *lds, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaed9K(JNIEnv *env, UNUSED jobject obj,
    jint k, jint kstart, jint kstop, jint n, jfloatArray d, jint offsetd, jfloatArray q, jint offsetq, jint ldq, jfloat rho, jfloatArray dlamda, jint offsetdlamda, jfloatArray w, jint offsetw, jfloatArray s, jint offsets, jint lds, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaeda_)(int *n, int *tlvls, int *curlvl, int *curpbm, int * prmptr, int * perm, int * givptr, int * givcol, float *givnum, float *q, int * qptr, float *z, float *ztemp, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaedaK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint tlvls, jint curlvl, jint curpbm, jintArray prmptr, jint offsetprmptr, jintArray perm, jint offsetperm, jintArray givptr, jint offsetgivptr, jintArray givcol, jint offsetgivcol, jfloatArray givnum, jint offsetgivnum, jfloatArray q, jint offsetq, jintArray qptr, jint offsetqptr, jfloatArray z, jint offsetz, jfloatArray ztemp, jint offsetztemp, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaein_)(int *rightv, int *noinit, int *n, float *h, int *ldh, float *wr, float *wi, float *vr, float *vi, float *b, int *ldb, float *work, float *eps3, float *smlnum, float *bignum, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaeinK(JNIEnv *env, UNUSED jobject obj,
    jboolean rightv, jboolean noinit, jint n, jfloatArray h, jint offseth, jint ldh, jfloat wr, jfloat wi, jfloatArray vr, jint offsetvr, jfloatArray vi, jint offsetvi, jfloatArray b, jint offsetb, jint ldb, jfloatArray work, jint offsetwork, jfloat eps3, jfloat smlnum, jfloat bignum, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaev2_)(float *a, float *b, float *c, /*FIXME*/void *rt1, /*FIXME*/void *rt2, /*FIXME*/void *cs1, /*FIXME*/void *sn1);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaev2K(JNIEnv *env, UNUSED jobject obj,
    jfloat a, jfloat b, jfloat c, jobject rt1, jobject rt2, jobject cs1, jobject sn1) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaexc_)(int *wantq, int *n, float *t, int *ldt, float *q, int *ldq, int *j1, int *n1, int *n2, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaexcK(JNIEnv *env, UNUSED jobject obj,
    jboolean wantq, jint n, jfloatArray t, jint offsett, jint ldt, jfloatArray q, jint offsetq, jint ldq, jint j1, jint n1, jint n2, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slag2_)(float *a, int *lda, float *b, int *ldb, float *safmin, /*FIXME*/void *scale1, /*FIXME*/void *scale2, /*FIXME*/void *wr1, /*FIXME*/void *wr2, /*FIXME*/void *wi);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slag2K(JNIEnv *env, UNUSED jobject obj,
    jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloat safmin, jobject scale1, jobject scale2, jobject wr1, jobject wr2, jobject wi) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slag2d_)(int *m, int *n, float *sa, int *ldsa, double *a, int *lda, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slag2dK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloatArray sa, jint offsetsa, jint ldsa, jdoubleArray a, jint offseta, jint lda, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slags2_)(int *upper, float *a1, float *a2, float *a3, float *b1, float *b2, float *b3, /*FIXME*/void *csu, /*FIXME*/void *snu, /*FIXME*/void *csv, /*FIXME*/void *snv, /*FIXME*/void *csq, /*FIXME*/void *snq);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slags2K(JNIEnv *env, UNUSED jobject obj,
    jboolean upper, jfloat a1, jfloat a2, jfloat a3, jfloat b1, jfloat b2, jfloat b3, jobject csu, jobject snu, jobject csv, jobject snv, jobject csq, jobject snq) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slagtf_)(int *n, float *a, float *lambda, float *b, float *c, float *tol, float *d, int * in, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slagtfK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray a, jint offseta, jfloat lambda, jfloatArray b, jint offsetb, jfloatArray c, jint offsetc, jfloat tol, jfloatArray d, jint offsetd, jintArray in, jint offsetin, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slagtm_)(char *trans, int *n, int *nrhs, float *alpha, float *dl, float *d, float *du, float *x, int *ldx, float *beta, float *b, int *ldb);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slagtmK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint n, jint nrhs, jfloat alpha, jfloatArray dl, jint offsetdl, jfloatArray d, jint offsetd, jfloatArray du, jint offsetdu, jfloatArray x, jint offsetx, jint ldx, jfloat beta, jfloatArray b, jint offsetb, jint ldb) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slagts_)(int *job, int *n, float *a, float *b, float *c, float *d, int * in, float *y, /*FIXME*/void *tol, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slagtsK(JNIEnv *env, UNUSED jobject obj,
    jint job, jint n, jfloatArray a, jint offseta, jfloatArray b, jint offsetb, jfloatArray c, jint offsetc, jfloatArray d, jint offsetd, jintArray in, jint offsetin, jfloatArray y, jint offsety, jobject tol, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slagv2_)(float *a, int *lda, float *b, int *ldb, float *alphar, float *alphai, float *beta, /*FIXME*/void *csl, /*FIXME*/void *snl, /*FIXME*/void *csr, /*FIXME*/void *snr);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slagv2K(JNIEnv *env, UNUSED jobject obj,
    jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray alphar, jint offsetalphar, jfloatArray alphai, jint offsetalphai, jfloatArray beta, jint offsetbeta, jobject csl, jobject snl, jobject csr, jobject snr) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slahqr_)(int *wantt, int *wantz, int *n, int *ilo, int *ihi, float *h, int *ldh, float *wr, float *wi, int *iloz, int *ihiz, float *z, int *ldz, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slahqrK(JNIEnv *env, UNUSED jobject obj,
    jboolean wantt, jboolean wantz, jint n, jint ilo, jint ihi, jfloatArray h, jint offseth, jint ldh, jfloatArray wr, jint offsetwr, jfloatArray wi, jint offsetwi, jint iloz, jint ihiz, jfloatArray z, jint offsetz, jint ldz, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slahr2_)(int *n, int *k, int *nb, float *a, int *lda, float *tau, float *t, int *ldt, float *y, int *ldy);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slahr2K(JNIEnv *env, UNUSED jobject obj,
    jint n, jint k, jint nb, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray t, jint offsett, jint ldt, jfloatArray y, jint offsety, jint ldy) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slahrd_)(int *n, int *k, int *nb, float *a, int *lda, float *tau, float *t, int *ldt, float *y, int *ldy);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slahrdK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint k, jint nb, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray t, jint offsett, jint ldt, jfloatArray y, jint offsety, jint ldy) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaic1_)(int *job, int *j, float *x, float *sest, float *w, float *gamma, /*FIXME*/void *sestpr, /*FIXME*/void *s, /*FIXME*/void *c);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaic1K(JNIEnv *env, UNUSED jobject obj,
    jint job, jint j, jfloatArray x, jint offsetx, jfloat sest, jfloatArray w, jint offsetw, jfloat gamma, jobject sestpr, jobject s, jobject c) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static int *(*slaisnan_)(float *sin1, float *sin2);

jboolean Java_dev_ludovic_netlib_lapack_JNILAPACK_slaisnanK(JNIEnv *env, UNUSED jobject obj,
    jfloat sin1, jfloat sin2) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static void (*slaln2_)(int *ltrans, int *na, int *nw, float *smin, float *ca, float *a, int *lda, float *d1, float *d2, float *b, int *ldb, float *wr, float *wi, float *x, int *ldx, /*FIXME*/void *scale, /*FIXME*/void *xnorm, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaln2K(JNIEnv *env, UNUSED jobject obj,
    jboolean ltrans, jint na, jint nw, jfloat smin, jfloat ca, jfloatArray a, jint offseta, jint lda, jfloat d1, jfloat d2, jfloatArray b, jint offsetb, jint ldb, jfloat wr, jfloat wi, jfloatArray x, jint offsetx, jint ldx, jobject scale, jobject xnorm, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slals0_)(int *icompq, int *nl, int *nr, int *sqre, int *nrhs, float *b, int *ldb, float *bx, int *ldbx, int *perm, int *givptr, int * givcol, int *ldgcol, float *givnum, int *ldgnum, float *poles, float *difl, float *difr, float *z, int *k, float *c, float *s, float *work, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slals0K(JNIEnv *env, UNUSED jobject obj,
    jint icompq, jint nl, jint nr, jint sqre, jint nrhs, jfloatArray b, jint offsetb, jint ldb, jfloatArray bx, jint offsetbx, jint ldbx, jintArray perm, jint offsetperm, jint givptr, jintArray givcol, jint offsetgivcol, jint ldgcol, jfloatArray givnum, jint offsetgivnum, jint ldgnum, jfloatArray poles, jint offsetpoles, jfloatArray difl, jint offsetdifl, jfloatArray difr, jint offsetdifr, jfloatArray z, jint offsetz, jint k, jfloat c, jfloat s, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slalsa_)(int *icompq, int *smlsiz, int *n, int *nrhs, float *b, int *ldb, float *bx, int *ldbx, float *u, int *ldu, float *vt, int * k, float *difl, float *difr, float *z, float *poles, int * givptr, int * givcol, int *ldgcol, int * perm, float *givnum, float *c, float *s, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slalsaK(JNIEnv *env, UNUSED jobject obj,
    jint icompq, jint smlsiz, jint n, jint nrhs, jfloatArray b, jint offsetb, jint ldb, jfloatArray bx, jint offsetbx, jint ldbx, jfloatArray u, jint offsetu, jint ldu, jfloatArray vt, jint offsetvt, jintArray k, jint offsetk, jfloatArray difl, jint offsetdifl, jfloatArray difr, jint offsetdifr, jfloatArray z, jint offsetz, jfloatArray poles, jint offsetpoles, jintArray givptr, jint offsetgivptr, jintArray givcol, jint offsetgivcol, jint ldgcol, jintArray perm, jint offsetperm, jfloatArray givnum, jint offsetgivnum, jfloatArray c, jint offsetc, jfloatArray s, jint offsets, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slalsd_)(const char *uplo, int *smlsiz, int *n, int *nrhs, float *d, float *e, float *b, int *ldb, float *rcond, /*FIXME*/void *rank, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slalsdK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint smlsiz, jint n, jint nrhs, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray b, jint offsetb, jint ldb, jfloat rcond, jobject rank, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slamrg_)(int *n1, int *n2, float *a, int *strd1, int *strd2, int * index);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slamrgK(JNIEnv *env, UNUSED jobject obj,
    jint n1, jint n2, jfloatArray a, jint offseta, jint strd1, jint strd2, jintArray index, jint offsetindex) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static int (*slaneg_)(int *n, float *d, int *offsetd, float *lld, int *offsetlld, float *sigma, float *pivmin, int *r);

jint Java_dev_ludovic_netlib_lapack_JNILAPACK_slanegK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray d, jint offsetd, jfloatArray lld, jint offsetlld, jfloat sigma, jfloat pivmin, jint r) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static float (*slangb_)(char *norm, int *n, int *kl, int *ku, float *ab, int *offsetab, int *ldab, float *work, int *offsetwork);

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slangbK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jint n, jint kl, jint ku, jfloatArray ab, jint offsetab, jint ldab, jfloatArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static float (*slange_)(char *norm, int *m, int *n, float *a, int *offseta, int *lda, float *work, int *offsetwork);

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slangeK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static float (*slangt_)(char *norm, int *n, float *dl, int *offsetdl, float *d, int *offsetd, float *du, int *offsetdu);

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slangtK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jint n, jfloatArray dl, jint offsetdl, jfloatArray d, jint offsetd, jfloatArray du, jint offsetdu) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static float (*slanhs_)(char *norm, int *n, float *a, int *offseta, int *lda, float *work, int *offsetwork);

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slanhsK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static float (*slansb_)(char *norm, const char *uplo, int *n, int *k, float *ab, int *offsetab, int *ldab, float *work, int *offsetwork);

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slansbK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jstring uplo, jint n, jint k, jfloatArray ab, jint offsetab, jint ldab, jfloatArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static float (*slansp_)(char *norm, const char *uplo, int *n, float *ap, int *offsetap, float *work, int *offsetwork);

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slanspK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jstring uplo, jint n, jfloatArray ap, jint offsetap, jfloatArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static float (*slanst_)(char *norm, int *n, float *d, int *offsetd, float *e, int *offsete);

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slanstK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static float (*slansy_)(char *norm, const char *uplo, int *n, float *a, int *offseta, int *lda, float *work, int *offsetwork);

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slansyK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static float (*slantb_)(char *norm, const char *uplo, char *diag, int *n, int *k, float *ab, int *offsetab, int *ldab, float *work, int *offsetwork);

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slantbK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jstring uplo, jstring diag, jint n, jint k, jfloatArray ab, jint offsetab, jint ldab, jfloatArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static float (*slantp_)(char *norm, const char *uplo, char *diag, int *n, float *ap, int *offsetap, float *work, int *offsetwork);

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slantpK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jstring uplo, jstring diag, jint n, jfloatArray ap, jint offsetap, jfloatArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static float (*slantr_)(char *norm, const char *uplo, char *diag, int *m, int *n, float *a, int *offseta, int *lda, float *work, int *offsetwork);

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slantrK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jstring uplo, jstring diag, jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static void (*slanv2_)(/*FIXME*/void *a, /*FIXME*/void *b, /*FIXME*/void *c, /*FIXME*/void *d, /*FIXME*/void *rt1r, /*FIXME*/void *rt1i, /*FIXME*/void *rt2r, /*FIXME*/void *rt2i, /*FIXME*/void *cs, /*FIXME*/void *sn);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slanv2K(JNIEnv *env, UNUSED jobject obj,
    jobject a, jobject b, jobject c, jobject d, jobject rt1r, jobject rt1i, jobject rt2r, jobject rt2i, jobject cs, jobject sn) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slapll_)(int *n, float *x, int *incx, float *y, int *incy, /*FIXME*/void *ssmin);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slapllK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy, jobject ssmin) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slapmt_)(int *forwrd, int *m, int *n, float *x, int *ldx, int *k);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slapmtK(JNIEnv *env, UNUSED jobject obj,
    jboolean forwrd, jint m, jint n, jfloatArray x, jint offsetx, jint ldx, jintArray k, jint offsetk) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static float (*slapy2_)(float *x, float *y);

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slapy2K(JNIEnv *env, UNUSED jobject obj,
    jfloat x, jfloat y) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static float (*slapy3_)(float *x, float *y, float *z);

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slapy3K(JNIEnv *env, UNUSED jobject obj,
    jfloat x, jfloat y, jfloat z) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static void (*slaqgb_)(int *m, int *n, int *kl, int *ku, float *ab, int *ldab, float *r, float *c, float *rowcnd, float *colcnd, float *amax, jstring *equed);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqgbK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint kl, jint ku, jfloatArray ab, jint offsetab, jint ldab, jfloatArray r, jint offsetr, jfloatArray c, jint offsetc, jfloat rowcnd, jfloat colcnd, jfloat amax, jobject equed) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaqge_)(int *m, int *n, float *a, int *lda, float *r, float *c, float *rowcnd, float *colcnd, float *amax, jstring *equed);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqgeK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray r, jint offsetr, jfloatArray c, jint offsetc, jfloat rowcnd, jfloat colcnd, jfloat amax, jobject equed) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaqp2_)(int *m, int *n, int *offset, float *a, int *lda, int * jpvt, float *tau, float *vn11, float *vn22, float *work);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqp2K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint offset, jfloatArray a, jint offseta, jint lda, jintArray jpvt, jint offsetjpvt, jfloatArray tau, jint offsettau, jfloatArray vn1, jint offsetvn1, jfloatArray vn2, jint offsetvn2, jfloatArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaqps_)(int *m, int *n, int *offset, int *nb, int *kb, float *a, int *lda, int * jpvt, float *tau, float *vn11, float *vn22, float *auxv, float *f, int *ldf);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqpsK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint offset, jint nb, jobject kb, jfloatArray a, jint offseta, jint lda, jintArray jpvt, jint offsetjpvt, jfloatArray tau, jint offsettau, jfloatArray vn1, jint offsetvn1, jfloatArray vn2, jint offsetvn2, jfloatArray auxv, jint offsetauxv, jfloatArray f, jint offsetf, jint ldf) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaqr0_)(int *wantt, int *wantz, int *n, int *ilo, int *ihi, float *h, int *ldh, float *wr, float *wi, int *iloz, int *ihiz, float *z, int *ldz, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqr0K(JNIEnv *env, UNUSED jobject obj,
    jboolean wantt, jboolean wantz, jint n, jint ilo, jint ihi, jfloatArray h, jint offseth, jint ldh, jfloatArray wr, jint offsetwr, jfloatArray wi, jint offsetwi, jint iloz, jint ihiz, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaqr1_)(int *n, float *h, int *ldh, float *sr1, float *si1, float *sr2, float *si2, float *v);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqr1K(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray h, jint offseth, jint ldh, jfloat sr1, jfloat si1, jfloat sr2, jfloat si2, jfloatArray v, jint offsetv) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaqr2_)(int *wantt, int *wantz, int *n, int *ktop, int *kbot, int *nw, float *h, int *ldh, int *iloz, int *ihiz, float *z, int *ldz, /*FIXME*/void *ns, /*FIXME*/void *nd, float *sr, float *si, float *v, int *ldv, int *nh, float *t, int *ldt, int *nv, float *wv, int *ldwv, float *work, int *lwork);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqr2K(JNIEnv *env, UNUSED jobject obj,
    jboolean wantt, jboolean wantz, jint n, jint ktop, jint kbot, jint nw, jfloatArray h, jint offseth, jint ldh, jint iloz, jint ihiz, jfloatArray z, jint offsetz, jint ldz, jobject ns, jobject nd, jfloatArray sr, jint offsetsr, jfloatArray si, jint offsetsi, jfloatArray v, jint offsetv, jint ldv, jint nh, jfloatArray t, jint offsett, jint ldt, jint nv, jfloatArray wv, jint offsetwv, jint ldwv, jfloatArray work, jint offsetwork, jint lwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaqr3_)(int *wantt, int *wantz, int *n, int *ktop, int *kbot, int *nw, float *h, int *ldh, int *iloz, int *ihiz, float *z, int *ldz, /*FIXME*/void *ns, /*FIXME*/void *nd, float *sr, float *si, float *v, int *ldv, int *nh, float *t, int *ldt, int *nv, float *wv, int *ldwv, float *work, int *lwork);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqr3K(JNIEnv *env, UNUSED jobject obj,
    jboolean wantt, jboolean wantz, jint n, jint ktop, jint kbot, jint nw, jfloatArray h, jint offseth, jint ldh, jint iloz, jint ihiz, jfloatArray z, jint offsetz, jint ldz, jobject ns, jobject nd, jfloatArray sr, jint offsetsr, jfloatArray si, jint offsetsi, jfloatArray v, jint offsetv, jint ldv, jint nh, jfloatArray t, jint offsett, jint ldt, jint nv, jfloatArray wv, jint offsetwv, jint ldwv, jfloatArray work, jint offsetwork, jint lwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaqr4_)(int *wantt, int *wantz, int *n, int *ilo, int *ihi, float *h, int *ldh, float *wr, float *wi, int *iloz, int *ihiz, float *z, int *ldz, float *work, int *lwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqr4K(JNIEnv *env, UNUSED jobject obj,
    jboolean wantt, jboolean wantz, jint n, jint ilo, jint ihi, jfloatArray h, jint offseth, jint ldh, jfloatArray wr, jint offsetwr, jfloatArray wi, jint offsetwi, jint iloz, jint ihiz, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaqr5_)(int *wantt, int *wantz, int *kacc22, int *n, int *ktop, int *kbot, int *nshfts, float *sr, float *si, float *h, int *ldh, int *iloz, int *ihiz, float *z, int *ldz, float *v, int *ldv, float *u, int *ldu, int *nv, float *wv, int *ldwv, int *nh, float *wh, int *ldwh);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqr5K(JNIEnv *env, UNUSED jobject obj,
    jboolean wantt, jboolean wantz, jint kacc22, jint n, jint ktop, jint kbot, jint nshfts, jfloatArray sr, jint offsetsr, jfloatArray si, jint offsetsi, jfloatArray h, jint offseth, jint ldh, jint iloz, jint ihiz, jfloatArray z, jint offsetz, jint ldz, jfloatArray v, jint offsetv, jint ldv, jfloatArray u, jint offsetu, jint ldu, jint nv, jfloatArray wv, jint offsetwv, jint ldwv, jint nh, jfloatArray wh, jint offsetwh, jint ldwh) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaqsb_)(const char *uplo, int *n, int *kd, float *ab, int *ldab, float *s, float *scond, float *amax, jstring *equed);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqsbK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint kd, jfloatArray ab, jint offsetab, jint ldab, jfloatArray s, jint offsets, jfloat scond, jfloat amax, jobject equed) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaqsp_)(const char *uplo, int *n, float *ap, float *s, float *scond, float *amax, jstring *equed);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqspK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray ap, jint offsetap, jfloatArray s, jint offsets, jfloat scond, jfloat amax, jobject equed) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaqsy_)(const char *uplo, int *n, float *a, int *lda, float *s, float *scond, float *amax, jstring *equed);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqsyK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray s, jint offsets, jfloat scond, jfloat amax, jobject equed) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaqtr_)(int *ltran, int *lreal, int *n, float *t, int *ldt, float *b, float *w, /*FIXME*/void *scale, float *x, float *work, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqtrK(JNIEnv *env, UNUSED jobject obj,
    jboolean ltran, jboolean lreal, jint n, jfloatArray t, jint offsett, jint ldt, jfloatArray b, jint offsetb, jfloat w, jobject scale, jfloatArray x, jint offsetx, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slar1v_)(int *n, int *b1, int *bn, float *lambda, float *d, float *l, float *ld, float *lld, float *pivmin, float *gaptol, float *z, int *wantnc, /*FIXME*/void *negcnt, /*FIXME*/void *ztz, /*FIXME*/void *mingma, /*FIXME*/void *r, int * isuppz, /*FIXME*/void *nrminv, /*FIXME*/void *resid, /*FIXME*/void *rqcorr, float *work);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slar1vK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint b1, jint bn, jfloat lambda, jfloatArray d, jint offsetd, jfloatArray l, jint offsetl, jfloatArray ld, jint offsetld, jfloatArray lld, jint offsetlld, jfloat pivmin, jfloat gaptol, jfloatArray z, jint offsetz, jboolean wantnc, jobject negcnt, jobject ztz, jobject mingma, jobject r, jintArray isuppz, jint offsetisuppz, jobject nrminv, jobject resid, jobject rqcorr, jfloatArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slar2v_)(int *n, float *x, float *y, float *z, int *incx, float *c, float *s, int *incc);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slar2vK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray x, jint offsetx, jfloatArray y, jint offsety, jfloatArray z, jint offsetz, jint incx, jfloatArray c, jint offsetc, jfloatArray s, jint offsets, jint incc) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slarf_)(char *side, int *m, int *n, float *v, int *incv, float *tau, float *c, int *Ldc, float *work);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarfK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jint m, jint n, jfloatArray v, jint offsetv, jint incv, jfloat tau, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slarfb_)(char *side, char *trans, char *direct, char *storev, int *m, int *n, int *k, float *v, int *ldv, float *t, int *ldt, float *c, int *Ldc, float *work, int *ldwork);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarfbK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jstring direct, jstring storev, jint m, jint n, jint k, jfloatArray v, jint offsetv, jint ldv, jfloatArray t, jint offsett, jint ldt, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork, jint ldwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slarfg_)(int *n, /*FIXME*/void *alpha, float *x, int *incx, /*FIXME*/void *tau);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarfgK(JNIEnv *env, UNUSED jobject obj,
    jint n, jobject alpha, jfloatArray x, jint offsetx, jint incx, jobject tau) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slarft_)(char *direct, char *storev, int *n, int *k, float *v, int *ldv, float *tau, float *t, int *ldt);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarftK(JNIEnv *env, UNUSED jobject obj,
    jstring direct, jstring storev, jint n, jint k, jfloatArray v, jint offsetv, jint ldv, jfloatArray tau, jint offsettau, jfloatArray t, jint offsett, jint ldt) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slarfx_)(char *side, int *m, int *n, float *v, float *tau, float *c, int *Ldc, float *work);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarfxK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jint m, jint n, jfloatArray v, jint offsetv, jfloat tau, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slargv_)(int *n, float *x, int *incx, float *y, int *incy, float *c, int *incc);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slargvK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy, jfloatArray c, jint offsetc, jint incc) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slarnv_)(int *idist, int * iseed, int *n, float *x);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarnvK(JNIEnv *env, UNUSED jobject obj,
    jint idist, jintArray iseed, jint offsetiseed, jint n, jfloatArray x, jint offsetx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slarra_)(int *n, float *d, float *e, float *e22, float *spltol, float *tnrm, /*FIXME*/void *nsplit, int * isplit, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarraK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray e2, jint offsete2, jfloat spltol, jfloat tnrm, jobject nsplit, jintArray isplit, jint offsetisplit, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slarrb_)(int *n, float *d, float *lld, int *ifirst, int *ilast, float *rtol1, float *rtol2, int *offset, float *w, float *wgap, float *werr, float *work, int * iwork, float *pivmin, float *spdiam, int *twist, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarrbK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray d, jint offsetd, jfloatArray lld, jint offsetlld, jint ifirst, jint ilast, jfloat rtol1, jfloat rtol2, jint offset, jfloatArray w, jint offsetw, jfloatArray wgap, jint offsetwgap, jfloatArray werr, jint offsetwerr, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jfloat pivmin, jfloat spdiam, jint twist, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slarrc_)(char *jobt, int *n, float *vl, float *vu, float *d, float *e, float *pivmin, /*FIXME*/void *eigcnt, /*FIXME*/void *lcnt, /*FIXME*/void *rcnt, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarrcK(JNIEnv *env, UNUSED jobject obj,
    jstring jobt, jint n, jfloat vl, jfloat vu, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloat pivmin, jobject eigcnt, jobject lcnt, jobject rcnt, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slarrd_)(char *range, char *order, int *n, float *vl, float *vu, int *il, int *iu, float *gers, float *reltol, float *d, float *e, float *e22, float *pivmin, int *nsplit, int * isplit, int *m, float *w, float *werr, /*FIXME*/void *wl, /*FIXME*/void *wu, int *iblock, int * indexw, float *work, int * iwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarrdK(JNIEnv *env, UNUSED jobject obj,
    jstring range, jstring order, jint n, jfloat vl, jfloat vu, jint il, jint iu, jfloatArray gers, jint offsetgers, jfloat reltol, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray e2, jint offsete2, jfloat pivmin, jint nsplit, jintArray isplit, jint offsetisplit, jobject m, jfloatArray w, jint offsetw, jfloatArray werr, jint offsetwerr, jobject wl, jobject wu, jintArray iblock, jint offsetiblock, jintArray indexw, jint offsetindexw, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slarre_)(char *range, int *n, /*FIXME*/void *vl, /*FIXME*/void *vu, int *il, int *iu, float *d, float *e, float *e22, float *rtol1, float *rtol2, float *spltol, /*FIXME*/void *nsplit, int * isplit, /*FIXME*/void *m, float *w, float *werr, float *wgap, int * iblock, int * indexw, float *gers, /*FIXME*/void *pivmin, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarreK(JNIEnv *env, UNUSED jobject obj,
    jstring range, jint n, jobject vl, jobject vu, jint il, jint iu, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray e2, jint offsete2, jfloat rtol1, jfloat rtol2, jfloat spltol, jobject nsplit, jintArray isplit, jint offsetisplit, jobject m, jfloatArray w, jint offsetw, jfloatArray werr, jint offsetwerr, jfloatArray wgap, jint offsetwgap, jintArray iblock, jint offsetiblock, jintArray indexw, jint offsetindexw, jfloatArray gers, jint offsetgers, jobject pivmin, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slarrf_)(int *n, float *d, float *l, float *ld, int *clstrt, int *clend, float *w, float *wgap, float *werr, float *spdiam, float *clgapl, float *clgapr, float *pivmin, /*FIXME*/void *sigma, float *dplus, float *lplus, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarrfK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray d, jint offsetd, jfloatArray l, jint offsetl, jfloatArray ld, jint offsetld, jint clstrt, jint clend, jfloatArray w, jint offsetw, jfloatArray wgap, jint offsetwgap, jfloatArray werr, jint offsetwerr, jfloat spdiam, jfloat clgapl, jfloat clgapr, jfloat pivmin, jobject sigma, jfloatArray dplus, jint offsetdplus, jfloatArray lplus, jint offsetlplus, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slarrj_)(int *n, float *d, float *e22, int *ifirst, int *ilast, float *rtol, int *offset, float *w, float *werr, float *work, int * iwork, float *pivmin, float *spdiam, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarrjK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray d, jint offsetd, jfloatArray e2, jint offsete2, jint ifirst, jint ilast, jfloat rtol, jint offset, jfloatArray w, jint offsetw, jfloatArray werr, jint offsetwerr, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jfloat pivmin, jfloat spdiam, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slarrk_)(int *n, int *iw, float *gl, float *gu, float *d, float *e22, float *pivmin, float *reltol, /*FIXME*/void *w, /*FIXME*/void *werr, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarrkK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint iw, jfloat gl, jfloat gu, jfloatArray d, jint offsetd, jfloatArray e2, jint offsete2, jfloat pivmin, jfloat reltol, jobject w, jobject werr, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slarrr_)(int *n, float *d, float *e, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarrrK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slarrv_)(int *n, float *vl, float *vu, float *d, float *l, float *pivmin, int * isplit, int *m, int *dol, int *dou, float *minrgp, /*FIXME*/void *rtol1, /*FIXME*/void *rtol2, float *w, float *werr, float *wgap, int * iblock, int * indexw, float *gers, float *z, int *ldz, int * isuppz, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarrvK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloat vl, jfloat vu, jfloatArray d, jint offsetd, jfloatArray l, jint offsetl, jfloat pivmin, jintArray isplit, jint offsetisplit, jint m, jint dol, jint dou, jfloat minrgp, jobject rtol1, jobject rtol2, jfloatArray w, jint offsetw, jfloatArray werr, jint offsetwerr, jfloatArray wgap, jint offsetwgap, jintArray iblock, jint offsetiblock, jintArray indexw, jint offsetindexw, jfloatArray gers, jint offsetgers, jfloatArray z, jint offsetz, jint ldz, jintArray isuppz, jint offsetisuppz, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slartg_)(float *f, float *g, /*FIXME*/void *cs, /*FIXME*/void *sn, /*FIXME*/void *r);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slartgK(JNIEnv *env, UNUSED jobject obj,
    jfloat f, jfloat g, jobject cs, jobject sn, jobject r) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slartv_)(int *n, float *x, int *incx, float *y, int *incy, float *c, float *s, int *incc);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slartvK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy, jfloatArray c, jint offsetc, jfloatArray s, jint offsets, jint incc) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaruv_)(int *iseed, int *n, float *x);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaruvK(JNIEnv *env, UNUSED jobject obj,
    jintArray iseed, jint offsetiseed, jint n, jfloatArray x, jint offsetx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slarz_)(char *side, int *m, int *n, int *l, float *v, int *incv, float *tau, float *c, int *Ldc, float *work);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarzK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jint m, jint n, jint l, jfloatArray v, jint offsetv, jint incv, jfloat tau, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slarzb_)(char *side, char *trans, char *direct, char *storev, int *m, int *n, int *k, int *l, float *v, int *ldv, float *t, int *ldt, float *c, int *Ldc, float *work, int *ldwork);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarzbK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jstring direct, jstring storev, jint m, jint n, jint k, jint l, jfloatArray v, jint offsetv, jint ldv, jfloatArray t, jint offsett, jint ldt, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork, jint ldwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slarzt_)(char *direct, char *storev, int *n, int *k, float *v, int *ldv, float *tau, float *t, int *ldt);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarztK(JNIEnv *env, UNUSED jobject obj,
    jstring direct, jstring storev, jint n, jint k, jfloatArray v, jint offsetv, jint ldv, jfloatArray tau, jint offsettau, jfloatArray t, jint offsett, jint ldt) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slas2_)(float *f, float *g, float *h, /*FIXME*/void *ssmin, /*FIXME*/void *ssmax);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slas2K(JNIEnv *env, UNUSED jobject obj,
    jfloat f, jfloat g, jfloat h, jobject ssmin, jobject ssmax) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slascl_)(char *type, int *kl, int *ku, float *cfrom, float *cto, int *m, int *n, float *a, int *lda, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasclK(JNIEnv *env, UNUSED jobject obj,
    jstring type, jint kl, jint ku, jfloat cfrom, jfloat cto, jint m, jint n, jfloatArray a, jint offseta, jint lda, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasd0_)(int *n, int *sqre, float *d, float *e, float *u, int *ldu, float *vt, int *ldvt, int *smlsiz, int * iwork, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasd0K(JNIEnv *env, UNUSED jobject obj,
    jint n, jint sqre, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray u, jint offsetu, jint ldu, jfloatArray vt, jint offsetvt, jint ldvt, jint smlsiz, jintArray iwork, jint offsetiwork, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasd1_)(int *nl, int *nr, int *sqre, float *d, /*FIXME*/void *alpha, /*FIXME*/void *beta, float *u, int *ldu, float *vt, int *ldvt, int * idxq, int * iwork, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasd1K(JNIEnv *env, UNUSED jobject obj,
    jint nl, jint nr, jint sqre, jfloatArray d, jint offsetd, jobject alpha, jobject beta, jfloatArray u, jint offsetu, jint ldu, jfloatArray vt, jint offsetvt, jint ldvt, jintArray idxq, jint offsetidxq, jintArray iwork, jint offsetiwork, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasd2_)(int *nl, int *nr, int *sqre, /*FIXME*/void *k, float *d, float *z, float *alpha, float *beta, float *u, int *ldu, float *vt, int *ldvt, float *dsigma, float *u22, int *ldu2, float *vt22, int *ldvt2, int * idxp, int * idx, int * idxc, int * idxq, int * coltyp, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasd2K(JNIEnv *env, UNUSED jobject obj,
    jint nl, jint nr, jint sqre, jobject k, jfloatArray d, jint offsetd, jfloatArray z, jint offsetz, jfloat alpha, jfloat beta, jfloatArray u, jint offsetu, jint ldu, jfloatArray vt, jint offsetvt, jint ldvt, jfloatArray dsigma, jint offsetdsigma, jfloatArray u2, jint offsetu2, jint ldu2, jfloatArray vt2, jint offsetvt2, jint ldvt2, jintArray idxp, jint offsetidxp, jintArray idx, jint offsetidx, jintArray idxc, jint offsetidxc, jintArray idxq, jint offsetidxq, jintArray coltyp, jint offsetcoltyp, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasd3_)(int *nl, int *nr, int *sqre, int *k, float *d, float *q, int *ldq, float *dsigma, float *u, int *ldu, float *u22, int *ldu2, float *vt, int *ldvt, float *vt22, int *ldvt2, int * idxc, int * ctot, float *z, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasd3K(JNIEnv *env, UNUSED jobject obj,
    jint nl, jint nr, jint sqre, jint k, jfloatArray d, jint offsetd, jfloatArray q, jint offsetq, jint ldq, jfloatArray dsigma, jint offsetdsigma, jfloatArray u, jint offsetu, jint ldu, jfloatArray u2, jint offsetu2, jint ldu2, jfloatArray vt, jint offsetvt, jint ldvt, jfloatArray vt2, jint offsetvt2, jint ldvt2, jintArray idxc, jint offsetidxc, jintArray ctot, jint offsetctot, jfloatArray z, jint offsetz, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasd4_)(int *n, int *i, float *d, float *z, float *delta, float *rho, /*FIXME*/void *sigma, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasd4K(JNIEnv *env, UNUSED jobject obj,
    jint n, jint i, jfloatArray d, jint offsetd, jfloatArray z, jint offsetz, jfloatArray delta, jint offsetdelta, jfloat rho, jobject sigma, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasd5_)(int *i, float *d, float *z, float *delta, float *rho, /*FIXME*/void *dsigma, float *work);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasd5K(JNIEnv *env, UNUSED jobject obj,
    jint i, jfloatArray d, jint offsetd, jfloatArray z, jint offsetz, jfloatArray delta, jint offsetdelta, jfloat rho, jobject dsigma, jfloatArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasd6_)(int *icompq, int *nl, int *nr, int *sqre, float *d, float *vf, float *vl, /*FIXME*/void *alpha, /*FIXME*/void *beta, int * idxq, int * perm, /*FIXME*/void *givptr, int * givcol, int *ldgcol, float *givnum, int *ldgnum, float *poles, float *difl, float *difr, float *z, /*FIXME*/void *k, /*FIXME*/void *c, /*FIXME*/void *s, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasd6K(JNIEnv *env, UNUSED jobject obj,
    jint icompq, jint nl, jint nr, jint sqre, jfloatArray d, jint offsetd, jfloatArray vf, jint offsetvf, jfloatArray vl, jint offsetvl, jobject alpha, jobject beta, jintArray idxq, jint offsetidxq, jintArray perm, jint offsetperm, jobject givptr, jintArray givcol, jint offsetgivcol, jint ldgcol, jfloatArray givnum, jint offsetgivnum, jint ldgnum, jfloatArray poles, jint offsetpoles, jfloatArray difl, jint offsetdifl, jfloatArray difr, jint offsetdifr, jfloatArray z, jint offsetz, jobject k, jobject c, jobject s, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasd7_)(int *icompq, int *nl, int *nr, int *sqre, /*FIXME*/void *k, float *d, float *z, float *zw, float *vf, float *vfw, float *vl, float *vlw, float *alpha, float *beta, float *dsigma, int * idx, int * idxp, int * idxq, int * perm, /*FIXME*/void *givptr, int * givcol, int *ldgcol, float *givnum, int *ldgnum, /*FIXME*/void *c, /*FIXME*/void *s, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasd7K(JNIEnv *env, UNUSED jobject obj,
    jint icompq, jint nl, jint nr, jint sqre, jobject k, jfloatArray d, jint offsetd, jfloatArray z, jint offsetz, jfloatArray zw, jint offsetzw, jfloatArray vf, jint offsetvf, jfloatArray vfw, jint offsetvfw, jfloatArray vl, jint offsetvl, jfloatArray vlw, jint offsetvlw, jfloat alpha, jfloat beta, jfloatArray dsigma, jint offsetdsigma, jintArray idx, jint offsetidx, jintArray idxp, jint offsetidxp, jintArray idxq, jint offsetidxq, jintArray perm, jint offsetperm, jobject givptr, jintArray givcol, jint offsetgivcol, jint ldgcol, jfloatArray givnum, jint offsetgivnum, jint ldgnum, jobject c, jobject s, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasd8_)(int *icompq, int *k, float *d, float *z, float *vf, float *vl, float *difl, float *difr, int *lddifr, float *dsigma, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasd8K(JNIEnv *env, UNUSED jobject obj,
    jint icompq, jint k, jfloatArray d, jint offsetd, jfloatArray z, jint offsetz, jfloatArray vf, jint offsetvf, jfloatArray vl, jint offsetvl, jfloatArray difl, jint offsetdifl, jfloatArray difr, jint offsetdifr, jint lddifr, jfloatArray dsigma, jint offsetdsigma, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasda_)(int *icompq, int *smlsiz, int *n, int *sqre, float *d, float *e, float *u, int *ldu, float *vt, int * k, float *difl, float *difr, float *z, float *poles, int * givptr, int * givcol, int *ldgcol, int * perm, float *givnum, float *c, float *s, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasdaK(JNIEnv *env, UNUSED jobject obj,
    jint icompq, jint smlsiz, jint n, jint sqre, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray u, jint offsetu, jint ldu, jfloatArray vt, jint offsetvt, jintArray k, jint offsetk, jfloatArray difl, jint offsetdifl, jfloatArray difr, jint offsetdifr, jfloatArray z, jint offsetz, jfloatArray poles, jint offsetpoles, jintArray givptr, jint offsetgivptr, jintArray givcol, jint offsetgivcol, jint ldgcol, jintArray perm, jint offsetperm, jfloatArray givnum, jint offsetgivnum, jfloatArray c, jint offsetc, jfloatArray s, jint offsets, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasdq_)(const char *uplo, int *sqre, int *n, int *ncvt, int *nru, int *ncc, float *d, float *e, float *vt, int *ldvt, float *u, int *ldu, float *c, int *Ldc, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasdqK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint sqre, jint n, jint ncvt, jint nru, jint ncc, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray vt, jint offsetvt, jint ldvt, jfloatArray u, jint offsetu, jint ldu, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasdt_)(int *n, /*FIXME*/void *lvl, /*FIXME*/void *nd, int * inode, int * ndiml, int * ndimr, int *msub);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasdtK(JNIEnv *env, UNUSED jobject obj,
    jint n, jobject lvl, jobject nd, jintArray inode, jint offsetinode, jintArray ndiml, jint offsetndiml, jintArray ndimr, jint offsetndimr, jint msub) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaset_)(const char *uplo, int *m, int *n, float *alpha, float *beta, float *a, int *lda);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasetK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint m, jint n, jfloat alpha, jfloat beta, jfloatArray a, jint offseta, jint lda) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasq1_)(int *n, float *d, float *e, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasq1K(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasq2_)(int *n, float *z, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasq2K(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray z, jint offsetz, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasq3_)(int *i0, /*FIXME*/void *n0, float *z, int *pp, /*FIXME*/void *dmin, /*FIXME*/void *sigma, /*FIXME*/void *desig, /*FIXME*/void *qmax, /*FIXME*/void *nfail, /*FIXME*/void *iter, /*FIXME*/void *ndiv, int *ieee);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasq3K(JNIEnv *env, UNUSED jobject obj,
    jint i0, jobject n0, jfloatArray z, jint offsetz, jint pp, jobject dmin, jobject sigma, jobject desig, jobject qmax, jobject nfail, jobject iter, jobject ndiv, jboolean ieee) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasq4_)(int *i0, int *n0, float *z, int *pp, int *n0in, float *dmin, float *dmin1, float *dmin2, float *dn, float *dn1, float *dn2, /*FIXME*/void *tau, /*FIXME*/void *ttype);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasq4K(JNIEnv *env, UNUSED jobject obj,
    jint i0, jint n0, jfloatArray z, jint offsetz, jint pp, jint n0in, jfloat dmin, jfloat dmin1, jfloat dmin2, jfloat dn, jfloat dn1, jfloat dn2, jobject tau, jobject ttype) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasq5_)(int *i0, int *n0, float *z, int *pp, float *tau, /*FIXME*/void *dmin, /*FIXME*/void *dmin1, /*FIXME*/void *dmin2, /*FIXME*/void *dn, /*FIXME*/void *dnm1, /*FIXME*/void *dnm2, int *ieee);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasq5K(JNIEnv *env, UNUSED jobject obj,
    jint i0, jint n0, jfloatArray z, jint offsetz, jint pp, jfloat tau, jobject dmin, jobject dmin1, jobject dmin2, jobject dn, jobject dnm1, jobject dnm2, jboolean ieee) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasq6_)(int *i0, int *n0, float *z, int *pp, /*FIXME*/void *dmin, /*FIXME*/void *dmin1, /*FIXME*/void *dmin2, /*FIXME*/void *dn, /*FIXME*/void *dnm1, /*FIXME*/void *dnm2);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasq6K(JNIEnv *env, UNUSED jobject obj,
    jint i0, jint n0, jfloatArray z, jint offsetz, jint pp, jobject dmin, jobject dmin1, jobject dmin2, jobject dn, jobject dnm1, jobject dnm2) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasr_)(char *side, char *pivot, char *direct, int *m, int *n, float *c, float *s, float *a, int *lda);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasrK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring pivot, jstring direct, jint m, jint n, jfloatArray c, jint offsetc, jfloatArray s, jint offsets, jfloatArray a, jint offseta, jint lda) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasrt_)(char *id, int *n, float *d, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasrtK(JNIEnv *env, UNUSED jobject obj,
    jstring id, jint n, jfloatArray d, jint offsetd, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slassq_)(int *n, float *x, int *incx, /*FIXME*/void *scale, /*FIXME*/void *sumsq);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slassqK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray x, jint offsetx, jint incx, jobject scale, jobject sumsq) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasv2_)(float *f, float *g, float *h, /*FIXME*/void *ssmin, /*FIXME*/void *ssmax, /*FIXME*/void *snr, /*FIXME*/void *csr, /*FIXME*/void *snl, /*FIXME*/void *csl);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasv2K(JNIEnv *env, UNUSED jobject obj,
    jfloat f, jfloat g, jfloat h, jobject ssmin, jobject ssmax, jobject snr, jobject csr, jobject snl, jobject csl) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slaswp_)(int *n, float *a, int *lda, int *k1, int *k2, int * ipiv, int *incx);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaswpK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray a, jint offseta, jint lda, jint k1, jint k2, jintArray ipiv, jint offsetipiv, jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasy2_)(int *ltranl, int *ltranr, int *isgn, int *n1, int *n2, float *tl, int *ldtl, float *tr, int *ldtr, float *b, int *ldb, /*FIXME*/void *scale, float *x, int *ldx, /*FIXME*/void *xnorm, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasy2K(JNIEnv *env, UNUSED jobject obj,
    jboolean ltranl, jboolean ltranr, jint isgn, jint n1, jint n2, jfloatArray tl, jint offsettl, jint ldtl, jfloatArray tr, jint offsettr, jint ldtr, jfloatArray b, jint offsetb, jint ldb, jobject scale, jfloatArray x, jint offsetx, jint ldx, jobject xnorm, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slasyf_)(const char *uplo, int *n, int *nb, /*FIXME*/void *kb, float *a, int *lda, int * ipiv, float *w, int *ldw, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasyfK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nb, jobject kb, jfloatArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jfloatArray w, jint offsetw, jint ldw, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slatbs_)(const char *uplo, char *trans, char *diag, char *normin, int *n, int *kd, float *ab, int *ldab, float *x, /*FIXME*/void *scale, float *cnorm, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slatbsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jstring diag, jstring normin, jint n, jint kd, jfloatArray ab, jint offsetab, jint ldab, jfloatArray x, jint offsetx, jobject scale, jfloatArray cnorm, jint offsetcnorm, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slatdf_)(int *ijob, int *n, float *z, int *ldz, float *rhs, /*FIXME*/void *rdsum, /*FIXME*/void *rdscal, int * ipiv, int * jpiv);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slatdfK(JNIEnv *env, UNUSED jobject obj,
    jint ijob, jint n, jfloatArray z, jint offsetz, jint ldz, jfloatArray rhs, jint offsetrhs, jobject rdsum, jobject rdscal, jintArray ipiv, jint offsetipiv, jintArray jpiv, jint offsetjpiv) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slatps_)(const char *uplo, char *trans, char *diag, char *normin, int *n, float *ap, float *x, /*FIXME*/void *scale, float *cnorm, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slatpsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jstring diag, jstring normin, jint n, jfloatArray ap, jint offsetap, jfloatArray x, jint offsetx, jobject scale, jfloatArray cnorm, jint offsetcnorm, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slatrd_)(const char *uplo, int *n, int *nb, float *a, int *lda, float *e, float *tau, float *w, int *ldw);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slatrdK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nb, jfloatArray a, jint offseta, jint lda, jfloatArray e, jint offsete, jfloatArray tau, jint offsettau, jfloatArray w, jint offsetw, jint ldw) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slatrs_)(const char *uplo, char *trans, char *diag, char *normin, int *n, float *a, int *lda, float *x, /*FIXME*/void *scale, float *cnorm, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slatrsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jstring diag, jstring normin, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray x, jint offsetx, jobject scale, jfloatArray cnorm, jint offsetcnorm, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slatrz_)(int *m, int *n, int *l, float *a, int *lda, float *tau, float *work);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slatrzK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint l, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slatzm_)(char *side, int *m, int *n, float *v, int *incv, float *tau, float *c11, float *c22, int *Ldc, float *work);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slatzmK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jint m, jint n, jfloatArray v, jint offsetv, jint incv, jfloat tau, jfloatArray c1, jint offsetc1, jfloatArray c2, jint offsetc2, jint Ldc, jfloatArray work, jint offsetwork) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slauu2_)(const char *uplo, int *n, float *a, int *lda, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slauu2K(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*slauum_)(const char *uplo, int *n, float *a, int *lda, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slauumK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

// static void (*slazq3_)(int *i0, /*FIXME*/void *n0, float *z, int *pp, /*FIXME*/void *dmin, /*FIXME*/void *sigma, /*FIXME*/void *desig, /*FIXME*/void *qmax, /*FIXME*/void *nfail, /*FIXME*/void *iter, /*FIXME*/void *ndiv, int *ieee, /*FIXME*/void *ttype, /*FIXME*/void *dmin1, /*FIXME*/void *dmin2, /*FIXME*/void *dn, /*FIXME*/void *dn1, /*FIXME*/void *dn2, /*FIXME*/void *tau);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slazq3K(JNIEnv *env, UNUSED jobject obj,
    jint i0, jobject n0, jfloatArray z, jint offsetz, jint pp, jobject dmin, jobject sigma, jobject desig, jobject qmax, jobject nfail, jobject iter, jobject ndiv, jboolean ieee, jobject ttype, jobject dmin1, jobject dmin2, jobject dn, jobject dn1, jobject dn2, jobject tau) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

// static void (*slazq4_)(int *i0, int *n0, float *z, int *pp, int *n0in, float *dmin, float *dmin1, float *dmin2, float *dn, float *dn1, float *dn2, /*FIXME*/void *tau, /*FIXME*/void *ttype, /*FIXME*/void *g);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slazq4K(JNIEnv *env, UNUSED jobject obj,
    jint i0, jint n0, jfloatArray z, jint offsetz, jint pp, jint n0in, jfloat dmin, jfloat dmin1, jfloat dmin2, jfloat dn, jfloat dn1, jfloat dn2, jobject tau, jobject ttype, jobject g) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sopgtr_)(const char *uplo, int *n, float *ap, float *tau, float *q, int *ldq, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sopgtrK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray ap, jint offsetap, jfloatArray tau, jint offsettau, jfloatArray q, jint offsetq, jint ldq, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sopmtr_)(char *side, const char *uplo, char *trans, int *m, int *n, float *ap, float *tau, float *c, int *Ldc, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sopmtrK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring uplo, jstring trans, jint m, jint n, jfloatArray ap, jint offsetap, jfloatArray tau, jint offsettau, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sorg2l_)(int *m, int *n, int *k, float *a, int *lda, float *tau, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorg2lK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint k, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sorg2r_)(int *m, int *n, int *k, float *a, int *lda, float *tau, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorg2rK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint k, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sorgbr_)(char *vect, int *m, int *n, int *k, float *a, int *lda, float *tau, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorgbrK(JNIEnv *env, UNUSED jobject obj,
    jstring vect, jint m, jint n, jint k, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sorghr_)(int *n, int *ilo, int *ihi, float *a, int *lda, float *tau, float *work, int *lwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorghrK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint ilo, jint ihi, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sorgl2_)(int *m, int *n, int *k, float *a, int *lda, float *tau, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorgl2K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint k, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sorglq_)(int *m, int *n, int *k, float *a, int *lda, float *tau, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorglqK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint k, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sorgql_)(int *m, int *n, int *k, float *a, int *lda, float *tau, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorgqlK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint k, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sorgqr_)(int *m, int *n, int *k, float *a, int *lda, float *tau, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorgqrK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint k, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sorgr2_)(int *m, int *n, int *k, float *a, int *lda, float *tau, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorgr2K(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint k, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sorgrq_)(int *m, int *n, int *k, float *a, int *lda, float *tau, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorgrqK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jint k, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sorgtr_)(const char *uplo, int *n, float *a, int *lda, float *tau, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorgtrK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sorm2l_)(char *side, char *trans, int *m, int *n, int *k, float *a, int *lda, float *tau, float *c, int *Ldc, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorm2lK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint k, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sorm2r_)(char *side, char *trans, int *m, int *n, int *k, float *a, int *lda, float *tau, float *c, int *Ldc, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorm2rK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint k, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sormbr_)(char *vect, char *side, char *trans, int *m, int *n, int *k, float *a, int *lda, float *tau, float *c, int *Ldc, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sormbrK(JNIEnv *env, UNUSED jobject obj,
    jstring vect, jstring side, jstring trans, jint m, jint n, jint k, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sormhr_)(char *side, char *trans, int *m, int *n, int *ilo, int *ihi, float *a, int *lda, float *tau, float *c, int *Ldc, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sormhrK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint ilo, jint ihi, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sorml2_)(char *side, char *trans, int *m, int *n, int *k, float *a, int *lda, float *tau, float *c, int *Ldc, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorml2K(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint k, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sormlq_)(char *side, char *trans, int *m, int *n, int *k, float *a, int *lda, float *tau, float *c, int *Ldc, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sormlqK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint k, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sormql_)(char *side, char *trans, int *m, int *n, int *k, float *a, int *lda, float *tau, float *c, int *Ldc, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sormqlK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint k, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sormqr_)(char *side, char *trans, int *m, int *n, int *k, float *a, int *lda, float *tau, float *c, int *Ldc, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sormqrK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint k, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sormr2_)(char *side, char *trans, int *m, int *n, int *k, float *a, int *lda, float *tau, float *c, int *Ldc, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sormr2K(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint k, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sormr3_)(char *side, char *trans, int *m, int *n, int *k, int *l, float *a, int *lda, float *tau, float *c, int *Ldc, float *work, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sormr3K(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint k, jint l, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sormrq_)(char *side, char *trans, int *m, int *n, int *k, float *a, int *lda, float *tau, float *c, int *Ldc, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sormrqK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint k, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sormrz_)(char *side, char *trans, int *m, int *n, int *k, int *l, float *a, int *lda, float *tau, float *c, int *Ldc, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sormrzK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring trans, jint m, jint n, jint k, jint l, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sormtr_)(char *side, const char *uplo, char *trans, int *m, int *n, float *a, int *lda, float *tau, float *c, int *Ldc, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sormtrK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring uplo, jstring trans, jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray c, jint offsetc, jint Ldc, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spbcon_)(const char *uplo, int *n, int *kd, float *ab, int *ldab, float *anorm, /*FIXME*/void *rcond, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spbconK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint kd, jfloatArray ab, jint offsetab, jint ldab, jfloat anorm, jobject rcond, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spbequ_)(const char *uplo, int *n, int *kd, float *ab, int *ldab, float *s, /*FIXME*/void *scond, /*FIXME*/void *amax, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spbequK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint kd, jfloatArray ab, jint offsetab, jint ldab, jfloatArray s, jint offsets, jobject scond, jobject amax, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spbrfs_)(const char *uplo, int *n, int *kd, int *nrhs, float *ab, int *ldab, float *afb, int *ldafb, float *b, int *ldb, float *x, int *ldx, float *ferr, float *berr, float *work, int * iwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spbrfsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint kd, jint nrhs, jfloatArray ab, jint offsetab, jint ldab, jfloatArray afb, jint offsetafb, jint ldafb, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spbstf_)(const char *uplo, int *n, int *kd, float *ab, int *ldab, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spbstfK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint kd, jfloatArray ab, jint offsetab, jint ldab, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spbsv_)(const char *uplo, int *n, int *kd, int *nrhs, float *ab, int *ldab, float *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spbsvK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint kd, jint nrhs, jfloatArray ab, jint offsetab, jint ldab, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spbsvx_)(char *fact, const char *uplo, int *n, int *kd, int *nrhs, float *ab, int *ldab, float *afb, int *ldafb, jstring *equed, float *s, float *b, int *ldb, float *x, int *ldx, /*FIXME*/void *rcond, float *ferr, float *berr, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spbsvxK(JNIEnv *env, UNUSED jobject obj,
    jstring fact, jstring uplo, jint n, jint kd, jint nrhs, jfloatArray ab, jint offsetab, jint ldab, jfloatArray afb, jint offsetafb, jint ldafb, jobject equed, jfloatArray s, jint offsets, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jobject rcond, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spbtf2_)(const char *uplo, int *n, int *kd, float *ab, int *ldab, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spbtf2K(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint kd, jfloatArray ab, jint offsetab, jint ldab, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spbtrf_)(const char *uplo, int *n, int *kd, float *ab, int *ldab, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spbtrfK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint kd, jfloatArray ab, jint offsetab, jint ldab, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spbtrs_)(const char *uplo, int *n, int *kd, int *nrhs, float *ab, int *ldab, float *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spbtrsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint kd, jint nrhs, jfloatArray ab, jint offsetab, jint ldab, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spocon_)(const char *uplo, int *n, float *a, int *lda, float *anorm, /*FIXME*/void *rcond, float *work, int *iwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spoconK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jfloat anorm, jobject rcond, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spoequ_)(int *n, float *a, int *lda, float *s, /*FIXME*/void *scond, /*FIXME*/void *amax, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spoequK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray a, jint offseta, jint lda, jfloatArray s, jint offsets, jobject scond, jobject amax, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sporfs_)(const char *uplo, int *n, int *nrhs, float *a, int *lda, float *af, int *ldaf, float *b, int *ldb, float *x, int *ldx, float *ferr, float *berr, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sporfsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jfloatArray a, jint offseta, jint lda, jfloatArray af, jint offsetaf, jint ldaf, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sposv_)(const char *uplo, int *n, int *nrhs, float *a, int *lda, float *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sposvK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sposvx_)(char *fact, const char *uplo, int *n, int *nrhs, float *a, int *lda, float *af, int *ldaf, jstring *equed, float *s, float *b, int *ldb, float *x, int *ldx, /*FIXME*/void *rcond, float *ferr, float *berr, float *work, int *iwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sposvxK(JNIEnv *env, UNUSED jobject obj,
    jstring fact, jstring uplo, jint n, jint nrhs, jfloatArray a, jint offseta, jint lda, jfloatArray af, jint offsetaf, jint ldaf, jobject equed, jfloatArray s, jint offsets, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jobject rcond, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spotf2_)(const char *uplo, int *n, float *a, int *lda, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spotf2K(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spotrf_)(const char *uplo, int *n, float *a, int *lda, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spotrfK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spotri_)(const char *uplo, int *n, float *a, int *lda, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spotriK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spotrs_)(const char *uplo, int *n, int *nrhs, float *a, int *lda, float *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spotrsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sppcon_)(const char *uplo, int *n, float *ap, float *anorm, /*FIXME*/void *rcond, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sppconK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray ap, jint offsetap, jfloat anorm, jobject rcond, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sppequ_)(const char *uplo, int *n, float *ap, float *s, /*FIXME*/void *scond, /*FIXME*/void *amax, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sppequK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray ap, jint offsetap, jfloatArray s, jint offsets, jobject scond, jobject amax, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spprfs_)(const char *uplo, int *n, int *nrhs, float *ap, float *afp, float *b, int *ldb, float *x, int *ldx, float *ferr, float *berr, float *work, int * iwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spprfsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jfloatArray ap, jint offsetap, jfloatArray afp, jint offsetafp, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sppsv_)(const char *uplo, int *n, int *nrhs, float *ap, float *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sppsvK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jfloatArray ap, jint offsetap, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sppsvx_)(char *fact, const char *uplo, int *n, int *nrhs, float *ap, float *afp, jstring *equed, float *s, float *b, int *ldb, float *x, int *ldx, /*FIXME*/void *rcond, float *ferr, float *berr, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sppsvxK(JNIEnv *env, UNUSED jobject obj,
    jstring fact, jstring uplo, jint n, jint nrhs, jfloatArray ap, jint offsetap, jfloatArray afp, jint offsetafp, jobject equed, jfloatArray s, jint offsets, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jobject rcond, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spptrf_)(const char *uplo, int *n, float *ap, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spptrfK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray ap, jint offsetap, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spptri_)(const char *uplo, int *n, float *ap, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spptriK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray ap, jint offsetap, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spptrs_)(const char *uplo, int *n, int *nrhs, float *ap, float *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spptrsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jfloatArray ap, jint offsetap, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sptcon_)(int *n, float *d, float *e, float *anorm, /*FIXME*/void *rcond, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sptconK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloat anorm, jobject rcond, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spteqr_)(char *compz, int *n, float *d, float *e, float *z, int *ldz, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spteqrK(JNIEnv *env, UNUSED jobject obj,
    jstring compz, jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sptrfs_)(int *n, int *nrhs, float *d, float *e, float *df, float *ef, float *b, int *ldb, float *x, int *ldx, float *ferr, float *berr, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sptrfsK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint nrhs, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray df, jint offsetdf, jfloatArray ef, jint offsetef, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sptsv_)(int *n, int *nrhs, float *d, float *e, float *b, int *ldb, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sptsvK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint nrhs, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sptsvx_)(char *fact, int *n, int *nrhs, float *d, float *e, float *df, float *ef, float *b, int *ldb, float *x, int *ldx, /*FIXME*/void *rcond, float *ferr, float *berr, float *work, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sptsvxK(JNIEnv *env, UNUSED jobject obj,
    jstring fact, jint n, jint nrhs, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray df, jint offsetdf, jfloatArray ef, jint offsetef, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jobject rcond, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spttrf_)(int *n, float *d, float *e, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spttrfK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*spttrs_)(int *n, int *nrhs, float *d, float *e, float *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spttrsK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint nrhs, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sptts2_)(int *n, int *nrhs, float *d, float *e, float *b, int *ldb);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sptts2K(JNIEnv *env, UNUSED jobject obj,
    jint n, jint nrhs, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray b, jint offsetb, jint ldb) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*srscl_)(int *n, float *sa, float *sx, int *incx);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_srsclK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloat sa, jfloatArray sx, jint offsetsx, jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssbev_)(char *jobz, const char *uplo, int *n, int *kd, float *ab, int *ldab, float *w, float *z, int *ldz, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssbevK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring uplo, jint n, jint kd, jfloatArray ab, jint offsetab, jint ldab, jfloatArray w, jint offsetw, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssbevd_)(char *jobz, const char *uplo, int *n, int *kd, float *ab, int *ldab, float *w, float *z, int *ldz, float *work, int *lwork, int *iwork, int *liwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssbevdK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring uplo, jint n, jint kd, jfloatArray ab, jint offsetab, jint ldab, jfloatArray w, jint offsetw, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssbevx_)(char *jobz, char *range, const char *uplo, int *n, int *kd, float *ab, int *ldab, float *q, int *ldq, float *vl, float *vu, int *il, int *iu, float *abstol, /*FIXME*/void *m, float *w, float *z, int *ldz, float *work, int *iwork, int *ifail, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssbevxK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring range, jstring uplo, jint n, jint kd, jfloatArray ab, jint offsetab, jint ldab, jfloatArray q, jint offsetq, jint ldq, jfloat vl, jfloat vu, jint il, jint iu, jfloat abstol, jobject m, jfloatArray w, jint offsetw, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jintArray ifail, jint offsetifail, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssbgst_)(char *vect, const char *uplo, int *n, int *ka, int *kb, float *ab, int *ldab, float *bb, int *ldbb, float *x, int *ldx, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssbgstK(JNIEnv *env, UNUSED jobject obj,
    jstring vect, jstring uplo, jint n, jint ka, jint kb, jfloatArray ab, jint offsetab, jint ldab, jfloatArray bb, jint offsetbb, jint ldbb, jfloatArray x, jint offsetx, jint ldx, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssbgv_)(char *jobz, const char *uplo, int *n, int *ka, int *kb, float *ab, int *ldab, float *bb, int *ldbb, float *w, float *z, int *ldz, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssbgvK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring uplo, jint n, jint ka, jint kb, jfloatArray ab, jint offsetab, jint ldab, jfloatArray bb, jint offsetbb, jint ldbb, jfloatArray w, jint offsetw, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssbgvd_)(char *jobz, const char *uplo, int *n, int *ka, int *kb, float *ab, int *ldab, float *bb, int *ldbb, float *w, float *z, int *ldz, float *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssbgvdK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring uplo, jint n, jint ka, jint kb, jfloatArray ab, jint offsetab, jint ldab, jfloatArray bb, jint offsetbb, jint ldbb, jfloatArray w, jint offsetw, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssbgvx_)(char *jobz, char *range, const char *uplo, int *n, int *ka, int *kb, float *ab, int *ldab, float *bb, int *ldbb, float *q, int *ldq, float *vl, float *vu, int *il, int *iu, float *abstol, /*FIXME*/void *m, float *w, float *z, int *ldz, float *work, int * iwork, int * ifail, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssbgvxK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring range, jstring uplo, jint n, jint ka, jint kb, jfloatArray ab, jint offsetab, jint ldab, jfloatArray bb, jint offsetbb, jint ldbb, jfloatArray q, jint offsetq, jint ldq, jfloat vl, jfloat vu, jint il, jint iu, jfloat abstol, jobject m, jfloatArray w, jint offsetw, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jintArray ifail, jint offsetifail, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssbtrd_)(char *vect, const char *uplo, int *n, int *kd, float *ab, int *ldab, float *d, float *e, float *q, int *ldq, float *work, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssbtrdK(JNIEnv *env, UNUSED jobject obj,
    jstring vect, jstring uplo, jint n, jint kd, jfloatArray ab, jint offsetab, jint ldab, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray q, jint offsetq, jint ldq, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sspcon_)(const char *uplo, int *n, float *ap, int * ipiv, float *anorm, /*FIXME*/void *rcond, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sspconK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray ap, jint offsetap, jintArray ipiv, jint offsetipiv, jfloat anorm, jobject rcond, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sspev_)(char *jobz, const char *uplo, int *n, float *ap, float *w, float *z, int *ldz, float *work, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sspevK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring uplo, jint n, jfloatArray ap, jint offsetap, jfloatArray w, jint offsetw, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sspevd_)(char *jobz, const char *uplo, int *n, float *ap, float *w, float *z, int *ldz, float *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sspevdK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring uplo, jint n, jfloatArray ap, jint offsetap, jfloatArray w, jint offsetw, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sspevx_)(char *jobz, char *range, const char *uplo, int *n, float *ap, float *vl, float *vu, int *il, int *iu, float *abstol, /*FIXME*/void *m, float *w, float *z, int *ldz, float *work, int * iwork, int * ifail, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sspevxK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring range, jstring uplo, jint n, jfloatArray ap, jint offsetap, jfloat vl, jfloat vu, jint il, jint iu, jfloat abstol, jobject m, jfloatArray w, jint offsetw, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jintArray ifail, jint offsetifail, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sspgst_)(int *itype, const char *uplo, int *n, float *ap, float *bp, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sspgstK(JNIEnv *env, UNUSED jobject obj,
    jint itype, jstring uplo, jint n, jfloatArray ap, jint offsetap, jfloatArray bp, jint offsetbp, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sspgv_)(int *itype, char *jobz, const char *uplo, int *n, float *ap, float *bp, float *w, float *z, int *ldz, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sspgvK(JNIEnv *env, UNUSED jobject obj,
    jint itype, jstring jobz, jstring uplo, jint n, jfloatArray ap, jint offsetap, jfloatArray bp, jint offsetbp, jfloatArray w, jint offsetw, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sspgvd_)(int *itype, char *jobz, const char *uplo, int *n, float *ap, float *bp, float *w, float *z, int *ldz, float *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sspgvdK(JNIEnv *env, UNUSED jobject obj,
    jint itype, jstring jobz, jstring uplo, jint n, jfloatArray ap, jint offsetap, jfloatArray bp, jint offsetbp, jfloatArray w, jint offsetw, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sspgvx_)(int *itype, char *jobz, char *range, const char *uplo, int *n, float *ap, float *bp, float *vl, float *vu, int *il, int *iu, float *abstol, /*FIXME*/void *m, float *w, float *z, int *ldz, float *work, int * iwork, int * ifail, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sspgvxK(JNIEnv *env, UNUSED jobject obj,
    jint itype, jstring jobz, jstring range, jstring uplo, jint n, jfloatArray ap, jint offsetap, jfloatArray bp, jint offsetbp, jfloat vl, jfloat vu, jint il, jint iu, jfloat abstol, jobject m, jfloatArray w, jint offsetw, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jintArray ifail, jint offsetifail, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssprfs_)(const char *uplo, int *n, int *nrhs, float *ap, float *afp, int * ipiv, float *b, int *ldb, float *x, int *ldx, float *ferr, float *berr, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssprfsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jfloatArray ap, jint offsetap, jfloatArray afp, jint offsetafp, jintArray ipiv, jint offsetipiv, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sspsv_)(const char *uplo, int *n, int *nrhs, float *ap, int * ipiv, float *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sspsvK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jfloatArray ap, jint offsetap, jintArray ipiv, jint offsetipiv, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sspsvx_)(char *fact, const char *uplo, int *n, int *nrhs, float *ap, float *afp, int * ipiv, float *b, int *ldb, float *x, int *ldx, /*FIXME*/void *rcond, float *ferr, float *berr, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sspsvxK(JNIEnv *env, UNUSED jobject obj,
    jstring fact, jstring uplo, jint n, jint nrhs, jfloatArray ap, jint offsetap, jfloatArray afp, jint offsetafp, jintArray ipiv, jint offsetipiv, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jobject rcond, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssptrd_)(const char *uplo, int *n, float *ap, float *d, float *e, float *tau, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssptrdK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray ap, jint offsetap, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray tau, jint offsettau, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssptrf_)(const char *uplo, int *n, float *ap, int *ipiv, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssptrfK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray ap, jint offsetap, jintArray ipiv, jint offsetipiv, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssptri_)(const char *uplo, int *n, float *ap, int * ipiv, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssptriK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray ap, jint offsetap, jintArray ipiv, jint offsetipiv, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssptrs_)(const char *uplo, int *n, int *nrhs, float *ap, int * ipiv, float *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssptrsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jfloatArray ap, jint offsetap, jintArray ipiv, jint offsetipiv, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sstebz_)(char *range, char *order, int *n, float *vl, float *vu, int *il, int *iu, float *abstol, float *d, float *e, /*FIXME*/void *m, /*FIXME*/void *nsplit, float *w, int * iblock, int *isplit, float *work, int *iwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sstebzK(JNIEnv *env, UNUSED jobject obj,
    jstring range, jstring order, jint n, jfloat vl, jfloat vu, jint il, jint iu, jfloat abstol, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jobject m, jobject nsplit, jfloatArray w, jint offsetw, jintArray iblock, jint offsetiblock, jintArray isplit, jint offsetisplit, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sstedc_)(char *compz, int *n, float *d, float *e, float *z, int *ldz, float *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sstedcK(JNIEnv *env, UNUSED jobject obj,
    jstring compz, jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sstegr_)(char *jobz, char *range, int *n, float *d, float *e, float *vl, float *vu, int *il, int *iu, float *abstol, /*FIXME*/void *m, float *w, float *z, int *ldz, int * isuppz, float *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sstegrK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring range, jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloat vl, jfloat vu, jint il, jint iu, jfloat abstol, jobject m, jfloatArray w, jint offsetw, jfloatArray z, jint offsetz, jint ldz, jintArray isuppz, jint offsetisuppz, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sstein_)(int *n, float *d, float *e, int *m, float *w, int * iblock, int * isplit, float *z, int *ldz, float *work, int * iwork, int * ifail, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssteinK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jint m, jfloatArray w, jint offsetw, jintArray iblock, jint offsetiblock, jintArray isplit, jint offsetisplit, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jintArray ifail, jint offsetifail, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sstemr_)(char *jobz, char *range, int *n, float *d, float *e, float *vl, float *vu, int *il, int *iu, /*FIXME*/void *m, float *w, float *z, int *ldz, int *nzc, int * isuppz, /*FIXME*/void *tryrac, float *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sstemrK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring range, jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloat vl, jfloat vu, jint il, jint iu, jobject m, jfloatArray w, jint offsetw, jfloatArray z, jint offsetz, jint ldz, jint nzc, jintArray isuppz, jint offsetisuppz, jobject tryrac, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssteqr_)(char *compz, int *n, float *d, float *e, float *z, int *ldz, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssteqrK(JNIEnv *env, UNUSED jobject obj,
    jstring compz, jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssterf_)(int *n, float *d, float *e, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssterfK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sstev_)(char *jobz, int *n, float *d, float *e, float *z, int *ldz, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sstevK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sstevd_)(char *jobz, int *n, float *d, float *e, float *z, int *ldz, float *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sstevdK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sstevr_)(char *jobz, char *range, int *n, float *d, float *e, float *vl, float *vu, int *il, int *iu, float *abstol, /*FIXME*/void *m, float *w, float *z, int *ldz, int * isuppz, float *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sstevrK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring range, jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloat vl, jfloat vu, jint il, jint iu, jfloat abstol, jobject m, jfloatArray w, jint offsetw, jfloatArray z, jint offsetz, jint ldz, jintArray isuppz, jint offsetisuppz, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sstevx_)(char *jobz, char *range, int *n, float *d, float *e, float *vl, float *vu, int *il, int *iu, float *abstol, /*FIXME*/void *m, float *w, float *z, int *ldz, float *work, int * iwork, int * ifail, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sstevxK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring range, jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloat vl, jfloat vu, jint il, jint iu, jfloat abstol, jobject m, jfloatArray w, jint offsetw, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jintArray ifail, jint offsetifail, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssycon_)(const char *uplo, int *n, float *a, int *lda, int * ipiv, float *anorm, /*FIXME*/void *rcond, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssyconK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jfloat anorm, jobject rcond, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssyev_)(char *jobz, const char *uplo, int *n, float *a, int *lda, float *w, float *work, int *lwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssyevK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray w, jint offsetw, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssyevd_)(char *jobz, const char *uplo, int *n, float *a, int *lda, float *w, float *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssyevdK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray w, jint offsetw, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssyevr_)(char *jobz, char *range, const char *uplo, int *n, float *a, int *lda, float *vl, float *vu, int *il, int *iu, float *abstol, /*FIXME*/void *m, float *w, float *z, int *ldz, int * isuppz, float *work, int *lwork, int *iwork, int *liwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssyevrK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring range, jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jfloat vl, jfloat vu, jint il, jint iu, jfloat abstol, jobject m, jfloatArray w, jint offsetw, jfloatArray z, jint offsetz, jint ldz, jintArray isuppz, jint offsetisuppz, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssyevx_)(char *jobz, char *range, const char *uplo, int *n, float *a, int *lda, float *vl, float *vu, int *il, int *iu, float *abstol, int *m, float *w, float *z, int *ldz, float *work, int *lwork, int * iwork, int *ifail, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssyevxK(JNIEnv *env, UNUSED jobject obj,
    jstring jobz, jstring range, jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jfloat vl, jfloat vu, jint il, jint iu, jfloat abstol, jobject m, jfloatArray w, jint offsetw, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jintArray ifail, jint offsetifail, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssygs2_)(int *itype, const char *uplo, int *n, float *a, int *lda, float *b, int *ldb, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssygs2K(JNIEnv *env, UNUSED jobject obj,
    jint itype, jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssygst_)(int *itype, const char *uplo, int *n, float *a, int *lda, float *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssygstK(JNIEnv *env, UNUSED jobject obj,
    jint itype, jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssygv_)(int *itype, char *jobz, const char *uplo, int *n, float *a, int *lda, float *b, int *ldb, float *w, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssygvK(JNIEnv *env, UNUSED jobject obj,
    jint itype, jstring jobz, jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray w, jint offsetw, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssygvd_)(int *itype, char *jobz, const char *uplo, int *n, float *a, int *lda, float *b, int *ldb, float *w, float *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssygvdK(JNIEnv *env, UNUSED jobject obj,
    jint itype, jstring jobz, jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray w, jint offsetw, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssygvx_)(int *itype, char *jobz, char *range, const char *uplo, int *n, float *a, int *lda, float *b, int *ldb, float *vl, float *vu, int *il, int *iu, float *abstol, /*FIXME*/void *m, float *w, float *z, int *ldz, float *work, int *lwork, int * iwork, int * ifail, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssygvxK(JNIEnv *env, UNUSED jobject obj,
    jint itype, jstring jobz, jstring range, jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloat vl, jfloat vu, jint il, jint iu, jfloat abstol, jobject m, jfloatArray w, jint offsetw, jfloatArray z, jint offsetz, jint ldz, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jintArray ifail, jint offsetifail, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssyrfs_)(const char *uplo, int *n, int *nrhs, float *a, int *lda, float *af, int *ldaf, int * ipiv, float *b, int *ldb, float *x, int *ldx, float *ferr, float *berr, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssyrfsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jfloatArray a, jint offseta, jint lda, jfloatArray af, jint offsetaf, jint ldaf, jintArray ipiv, jint offsetipiv, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssysv_)(const char *uplo, int *n, int *nrhs, float *a, int *lda, int *ipiv, float *b, int *ldb, float *work, int *lwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssysvK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jfloatArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jfloatArray b, jint offsetb, jint ldb, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssysvx_)(char *fact, const char *uplo, int *n, int *nrhs, float *a, int *lda, float *af, int *ldaf, int * ipiv, float *b, int *ldb, float *x, int *ldx, /*FIXME*/void *rcond, float *ferr, float *berr, float *work, int *lwork, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssysvxK(JNIEnv *env, UNUSED jobject obj,
    jstring fact, jstring uplo, jint n, jint nrhs, jfloatArray a, jint offseta, jint lda, jfloatArray af, jint offsetaf, jint ldaf, jintArray ipiv, jint offsetipiv, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jobject rcond, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssytd2_)(const char *uplo, int *n, float *a, int *lda, float *d, float *e, float *tau, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssytd2K(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray tau, jint offsettau, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssytf2_)(const char *uplo, int *n, float *a, int *lda, int * ipiv, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssytf2K(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssytrd_)(const char *uplo, int *n, float *a, int *lda, float *d, float *e, float *tau, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssytrdK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssytrf_)(const char *uplo, int *n, float *a, int *lda, int * ipiv, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssytrfK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssytri_)(const char *uplo, int *n, float *a, int *lda, int * ipiv, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssytriK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloatArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssytrs_)(const char *uplo, int *n, int *nrhs, float *a, int *lda, int * ipiv, float *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssytrsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jint nrhs, jfloatArray a, jint offseta, jint lda, jintArray ipiv, jint offsetipiv, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stbcon_)(char *norm, const char *uplo, char *diag, int *n, int *kd, float *ab, int *ldab, /*FIXME*/void *rcond, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stbconK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jstring uplo, jstring diag, jint n, jint kd, jfloatArray ab, jint offsetab, jint ldab, jobject rcond, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stbrfs_)(const char *uplo, char *trans, char *diag, int *n, int *kd, int *nrhs, float *ab, int *ldab, float *b, int *ldb, float *x, int *ldx, float *ferr, float *berr, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stbrfsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jstring diag, jint n, jint kd, jint nrhs, jfloatArray ab, jint offsetab, jint ldab, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stbtrs_)(const char *uplo, char *trans, char *diag, int *n, int *kd, int *nrhs, float *ab, int *ldab, float *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stbtrsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jstring diag, jint n, jint kd, jint nrhs, jfloatArray ab, jint offsetab, jint ldab, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stgevc_)(char *side, char *howmny, int *select, int *n, float *s, int *lds, float *p, int *ldp, float *vl, int *ldvl, float *vr, int *ldvr, int *mm, /*FIXME*/void *m, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stgevcK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring howmny, jbooleanArray select, jint offsetselect, jint n, jfloatArray s, jint offsets, jint lds, jfloatArray p, jint offsetp, jint ldp, jfloatArray vl, jint offsetvl, jint ldvl, jfloatArray vr, jint offsetvr, jint ldvr, jint mm, jobject m, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stgex2_)(int *wantq, int *wantz, int *n, float *a, int *lda, float *b, int *ldb, float *q, int *ldq, float *z, int *ldz, int *j1, int *n1, int *n2, float *work, int *lwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stgex2K(JNIEnv *env, UNUSED jobject obj,
    jboolean wantq, jboolean wantz, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray q, jint offsetq, jint ldq, jfloatArray z, jint offsetz, jint ldz, jint j1, jint n1, jint n2, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stgexc_)(int *wantq, int *wantz, int *n, float *a, int *lda, float *b, int *ldb, float *q, int *ldq, float *z, int *ldz, /*FIXME*/void *ifst, /*FIXME*/void *ilst, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stgexcK(JNIEnv *env, UNUSED jobject obj,
    jboolean wantq, jboolean wantz, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray q, jint offsetq, jint ldq, jfloatArray z, jint offsetz, jint ldz, jobject ifst, jobject ilst, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stgsen_)(int *ijob, int *wantq, int *wantz, int *select, int *n, float *a, int *lda, float *b, int *ldb, float *alphar, float *alphai, float *beta, float *q, int *ldq, float *z, int *ldz, /*FIXME*/void *m, /*FIXME*/void *pl, /*FIXME*/void *pr, float *dif, float *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stgsenK(JNIEnv *env, UNUSED jobject obj,
    jint ijob, jboolean wantq, jboolean wantz, jbooleanArray select, jint offsetselect, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray alphar, jint offsetalphar, jfloatArray alphai, jint offsetalphai, jfloatArray beta, jint offsetbeta, jfloatArray q, jint offsetq, jint ldq, jfloatArray z, jint offsetz, jint ldz, jobject m, jobject pl, jobject pr, jfloatArray dif, jint offsetdif, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stgsja_)(char *jobu, char *jobv, char *jobq, int *m, int *p, int *n, int *k, int *l, float *a, int *lda, float *b, int *ldb, float *tola, float *tolb, float *alpha, float *beta, float *u, int *ldu, float *v, int *ldv, float *q, int *ldq, float *work, /*FIXME*/void *ncycle, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stgsjaK(JNIEnv *env, UNUSED jobject obj,
    jstring jobu, jstring jobv, jstring jobq, jint m, jint p, jint n, jint k, jint l, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloat tola, jfloat tolb, jfloatArray alpha, jint offsetalpha, jfloatArray beta, jint offsetbeta, jfloatArray u, jint offsetu, jint ldu, jfloatArray v, jint offsetv, jint ldv, jfloatArray q, jint offsetq, jint ldq, jfloatArray work, jint offsetwork, jobject ncycle, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stgsna_)(char *job, char *howmny, int *select, int *n, float *a, int *lda, float *b, int *ldb, float *vl, int *ldvl, float *vr, int *ldvr, float *s, float *dif, int *mm, /*FIXME*/void *m, float *work, int *lwork, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stgsnaK(JNIEnv *env, UNUSED jobject obj,
    jstring job, jstring howmny, jbooleanArray select, jint offsetselect, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray vl, jint offsetvl, jint ldvl, jfloatArray vr, jint offsetvr, jint ldvr, jfloatArray s, jint offsets, jfloatArray dif, jint offsetdif, jint mm, jobject m, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stgsy2_)(char *trans, int *ijob, int *m, int *n, float *a, int *lda, float *b, int *ldb, float *c, int *Ldc, float *d, int *ldd, float *e, int *lde, float *f, int *ldf, /*FIXME*/void *scale, /*FIXME*/void *rdsum, /*FIXME*/void *rdscal, int *iwork, int *pq, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stgsy2K(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint ijob, jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray c, jint offsetc, jint Ldc, jfloatArray d, jint offsetd, jint ldd, jfloatArray e, jint offsete, jint lde, jfloatArray f, jint offsetf, jint ldf, jobject scale, jobject rdsum, jobject rdscal, jintArray iwork, jint offsetiwork, jobject pq, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stgsyl_)(char *trans, int *ijob, int *m, int *n, float *a, int *lda, float *b, int *ldb, float *c, int *Ldc, float *d, int *ldd, float *e, int *lde, float *f, int *ldf, /*FIXME*/void *scale, /*FIXME*/void *dif, float *work, int *lwork, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stgsylK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint ijob, jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray c, jint offsetc, jint Ldc, jfloatArray d, jint offsetd, jint ldd, jfloatArray e, jint offsete, jint lde, jfloatArray f, jint offsetf, jint ldf, jobject scale, jobject dif, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stpcon_)(char *norm, const char *uplo, char *diag, int *n, float *ap, /*FIXME*/void *rcond, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stpconK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jstring uplo, jstring diag, jint n, jfloatArray ap, jint offsetap, jobject rcond, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stprfs_)(const char *uplo, char *trans, char *diag, int *n, int *nrhs, float *ap, float *b, int *ldb, float *x, int *ldx, float *ferr, float *berr, float *work, int * iwork, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stprfsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jstring diag, jint n, jint nrhs, jfloatArray ap, jint offsetap, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stptri_)(const char *uplo, char *diag, int *n, float *ap, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stptriK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring diag, jint n, jfloatArray ap, jint offsetap, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stptrs_)(const char *uplo, char *trans, char *diag, int *n, int *nrhs, float *ap, float *b, int *ldb, int *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stptrsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jstring diag, jint n, jint nrhs, jfloatArray ap, jint offsetap, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*strcon_)(char *norm, const char *uplo, char *diag, int *n, float *a, int *lda, /*FIXME*/void *rcond, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_strconK(JNIEnv *env, UNUSED jobject obj,
    jstring norm, jstring uplo, jstring diag, jint n, jfloatArray a, jint offseta, jint lda, jobject rcond, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*strevc_)(char *side, char *howmny, int *select, int *n, float *t, int *ldt, float *vl, int *ldvl, float *vr, int *ldvr, int *mm, /*FIXME*/void *m, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_strevcK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring howmny, jbooleanArray select, jint offsetselect, jint n, jfloatArray t, jint offsett, jint ldt, jfloatArray vl, jint offsetvl, jint ldvl, jfloatArray vr, jint offsetvr, jint ldvr, jint mm, jobject m, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*strexc_)(char *compq, int *n, float *t, int *ldt, float *q, int *ldq, /*FIXME*/void *ifst, /*FIXME*/void *ilst, float *work, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_strexcK(JNIEnv *env, UNUSED jobject obj,
    jstring compq, jint n, jfloatArray t, jint offsett, jint ldt, jfloatArray q, jint offsetq, jint ldq, jobject ifst, jobject ilst, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*strrfs_)(const char *uplo, char *trans, char *diag, int *n, int *nrhs, float *a, int *lda, float *b, int *ldb, float *x, int *ldx, float *ferr, float *berr, float *work, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_strrfsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jstring diag, jint n, jint nrhs, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray x, jint offsetx, jint ldx, jfloatArray ferr, jint offsetferr, jfloatArray berr, jint offsetberr, jfloatArray work, jint offsetwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*strsen_)(char *job, char *compq, int *select, int *n, float *t, int *ldt, float *q, int *ldq, float *wr, float *wi, /*FIXME*/void *m, /*FIXME*/void *s, /*FIXME*/void *sep, float *work, int *lwork, int * iwork, int *liwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_strsenK(JNIEnv *env, UNUSED jobject obj,
    jstring job, jstring compq, jbooleanArray select, jint offsetselect, jint n, jfloatArray t, jint offsett, jint ldt, jfloatArray q, jint offsetq, jint ldq, jfloatArray wr, jint offsetwr, jfloatArray wi, jint offsetwi, jobject m, jobject s, jobject sep, jfloatArray work, jint offsetwork, jint lwork, jintArray iwork, jint offsetiwork, jint liwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*strsna_)(char *job, char *howmny, int *select, int *n, float *t, int *ldt, float *vl, int *ldvl, float *vr, int *ldvr, float *s, float *sep, int *mm, /*FIXME*/void *m, float *work, int *ldwork, int * iwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_strsnaK(JNIEnv *env, UNUSED jobject obj,
    jstring job, jstring howmny, jbooleanArray select, jint offsetselect, jint n, jfloatArray t, jint offsett, jint ldt, jfloatArray vl, jint offsetvl, jint ldvl, jfloatArray vr, jint offsetvr, jint ldvr, jfloatArray s, jint offsets, jfloatArray sep, jint offsetsep, jint mm, jobject m, jfloatArray work, jint offsetwork, jint ldwork, jintArray iwork, jint offsetiwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*strsyl_)(char *trana, char *tranb, int *isgn, int *m, int *n, float *a, int *lda, float *b, int *ldb, float *c, int *Ldc, /*FIXME*/void *scale, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_strsylK(JNIEnv *env, UNUSED jobject obj,
    jstring trana, jstring tranb, jint isgn, jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloatArray c, jint offsetc, jint Ldc, jobject scale, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*strti2_)(const char *uplo, char *diag, int *n, float *a, int *lda, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_strti2K(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring diag, jint n, jfloatArray a, jint offseta, jint lda, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*strtri_)(const char *uplo, char *diag, int *n, float *a, int *lda, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_strtriK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring diag, jint n, jfloatArray a, jint offseta, jint lda, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*strtrs_)(const char *uplo, char *trans, char *diag, int *n, int *nrhs, float *a, int *lda, float *b, int *ldb, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_strtrsK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jstring diag, jint n, jint nrhs, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stzrqf_)(int *m, int *n, float *a, int *lda, float *tau, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stzrqfK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stzrzf_)(int *m, int *n, float *a, int *lda, float *tau, float *work, int *lwork, /*FIXME*/void *info);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stzrzfK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray tau, jint offsettau, jfloatArray work, jint offsetwork, jint lwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static double (*dlamch_)(char *cmach);

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlamchK(JNIEnv *env, UNUSED jobject obj,
    jstring cmach) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

// static void (*dlamc1_)(/*FIXME*/void *beta, /*FIXME*/void *t, /*FIXME*/void *rnd, /*FIXME*/void *ieee1);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlamc1K(JNIEnv *env, UNUSED jobject obj,
    jobject beta, jobject t, jobject rnd, jobject ieee1) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

// static void (*dlamc2_)(/*FIXME*/void *beta, /*FIXME*/void *t, /*FIXME*/void *rnd, /*FIXME*/void *eps, /*FIXME*/void *emin, /*FIXME*/void *rmin, /*FIXME*/void *emax, /*FIXME*/void *rmax);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlamc2K(JNIEnv *env, UNUSED jobject obj,
    jobject beta, jobject t, jobject rnd, jobject eps, jobject emin, jobject rmin, jobject emax, jobject rmax) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static double (*dlamc3_)(double *a, double *b);

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlamc3K(JNIEnv *env, UNUSED jobject obj,
    jdouble a, jdouble b) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

// static void (*dlamc4_)(/*FIXME*/void *emin, double *start, int *base);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlamc4K(JNIEnv *env, UNUSED jobject obj,
    jobject emin, jdouble start, jint base) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

// static void (*dlamc5_)(int *beta, int *p, int *emin, int *ieee, /*FIXME*/void *emax, /*FIXME*/void *rmax);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlamc5K(JNIEnv *env, UNUSED jobject obj,
    jint beta, jint p, jint emin, jboolean ieee, jobject emax, jobject rmax) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static double (*dsecnd_)(void);

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dsecndK(JNIEnv *env, UNUSED jobject obj) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static int *(*lsame_)(char *ca, char *cb);

jboolean Java_dev_ludovic_netlib_lapack_JNILAPACK_lsameK(JNIEnv *env, UNUSED jobject obj,
    jstring ca, jstring cb) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static float (*second_)(void);

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_secondK(JNIEnv *env, UNUSED jobject obj) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}


static float (*slamch_)(char *cmach);

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slamchK(JNIEnv *env, UNUSED jobject obj,
    jstring cmach) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

// static void (*slamc1_)(/*FIXME*/void *beta, /*FIXME*/void *t, /*FIXME*/void *rnd, /*FIXME*/void *ieee1);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slamc1K(JNIEnv *env, UNUSED jobject obj,
    jobject beta, jobject t, jobject rnd, jobject ieee1) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

// static void (*slamc2_)(/*FIXME*/void *beta, /*FIXME*/void *t, /*FIXME*/void *rnd, /*FIXME*/void *eps, /*FIXME*/void *emin, /*FIXME*/void *rmin, /*FIXME*/void *emax, /*FIXME*/void *rmax);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slamc2K(JNIEnv *env, UNUSED jobject obj,
    jobject beta, jobject t, jobject rnd, jobject eps, jobject emin, jobject rmin, jobject emax, jobject rmax) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static float (*slamc3_)(float *a, float *b);

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slamc3K(JNIEnv *env, UNUSED jobject obj,
    jfloat a, jfloat b) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

// static void (*slamc4_)(/*FIXME*/void *emin, float *start, int *base);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slamc4K(JNIEnv *env, UNUSED jobject obj,
    jobject emin, jfloat start, jint base) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

// static void (*slamc5_)(int *beta, int *p, int *emin, int *ieee, /*FIXME*/void *emax, /*FIXME*/void *rmax);

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slamc5K(JNIEnv *env, UNUSED jobject obj,
    jint beta, jint p, jint emin, jboolean ieee, jobject emax, jobject rmax) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

jboolean get_system_property(JNIEnv *env, jstring key, jstring def, jstring *res) {
  jclass System_class = (*env)->FindClass(env, "java/lang/System");
  if (!System_class) {
    return FALSE;
  }
  jmethodID System_getProperty_methodID = (*env)->GetStaticMethodID(env, System_class, "getProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");
  if (!System_getProperty_methodID) {
    return FALSE;
  }
  *res = (jstring)(*env)->CallStaticObjectMethod(env, System_class, System_getProperty_methodID, key, def);
  return TRUE;
}

jint JNI_OnLoad(JavaVM *vm, UNUSED void *reserved) {
  JNIEnv *env;
  if ((*vm)->GetEnv(vm, (void**)&env, JNI_VERSION_1_6) != JNI_OK) {
    return -1;
  }

  jclass booleanW_class = (*env)->FindClass(env, "org/netlib/util/booleanW");
  if (!booleanW_class) {
    return -1;
  }
  booleanW_val_fieldID = (*env)->GetFieldID(env, booleanW_class, "val", "Z");
  if (!booleanW_val_fieldID) {
    return -1;
  }

  jclass intW_class = (*env)->FindClass(env, "org/netlib/util/intW");
  if (!intW_class) {
    return -1;
  }
  intW_val_fieldID = (*env)->GetFieldID(env, intW_class, "val", "I");
  if (!intW_val_fieldID) {
    return -1;
  }

  jclass floatW_class = (*env)->FindClass(env, "org/netlib/util/floatW");
  if (!floatW_class) {
    return -1;
  }
  floatW_val_fieldID = (*env)->GetFieldID(env, floatW_class, "val", "F");
  if (!floatW_val_fieldID) {
    return -1;
  }

  jclass doubleW_class = (*env)->FindClass(env, "org/netlib/util/doubleW");
  if (!doubleW_class) {
    return -1;
  }
  doubleW_val_fieldID = (*env)->GetFieldID(env, doubleW_class, "val", "D");
  if (!doubleW_val_fieldID) {
    return -1;
  }

  jclass StringW_class = (*env)->FindClass(env, "org/netlib/util/StringW");
  if (!StringW_class) {
    return -1;
  }
  StringW_val_fieldID = (*env)->GetFieldID(env, StringW_class, "val", "Ljava/lang/String;");
  if (!StringW_val_fieldID) {
    return -1;
  }

  jstring property_nativeLibPath;
  if (!get_system_property(env, (*env)->NewStringUTF(env, "dev.ludovic.netlib.lapack.nativeLibPath"), NULL, &property_nativeLibPath)) {
    return -1;
  }
  jstring property_nativeLib;
  if (!get_system_property(env, (*env)->NewStringUTF(env, "dev.ludovic.netlib.lapack.nativeLib"), (*env)->NewStringUTF(env, "lapack"), &property_nativeLib)) {
    return -1;
  }

  char lapack_name[1024];
  if (property_nativeLibPath) {
    const char *utf = (*env)->GetStringUTFChars(env, property_nativeLibPath, NULL);
    snprintf(lapack_name, sizeof(lapack_name), "%s", utf);
    (*env)->ReleaseStringUTFChars(env, property_nativeLibPath, utf);
  } else if (property_nativeLib) {
    const char *utf = (*env)->GetStringUTFChars(env, property_nativeLib, NULL);
    snprintf(lapack_name, sizeof(lapack_name), "lib%s.so", utf);
    (*env)->ReleaseStringUTFChars(env, property_nativeLib, utf);
  } else {
    /* either property_nativeLibPath or property_nativeLib should always be non-NULL */
    return -1;
  }

  lapack = dlopen(lapack_name, RTLD_LAZY);
  if (!lapack) {
    return -1;
  }

#define LOAD_SYMBOL(name) \
  name = dlsym(lapack, #name); \
  if (!name) { \
    return -1; \
  }

  LOAD_SYMBOL(dbdsdc_)
  LOAD_SYMBOL(dbdsqr_)
  LOAD_SYMBOL(ddisna_)
  LOAD_SYMBOL(dgbbrd_)
  LOAD_SYMBOL(dgbcon_)
  LOAD_SYMBOL(dgbequ_)
  LOAD_SYMBOL(dgbrfs_)
  LOAD_SYMBOL(dgbsvx_)
  LOAD_SYMBOL(dgbsv_)
  LOAD_SYMBOL(dgbtrf_)
  LOAD_SYMBOL(dgbtf2_)
  LOAD_SYMBOL(dgebak_)
  LOAD_SYMBOL(dgbtrs_)
  LOAD_SYMBOL(dgebd2_)
  LOAD_SYMBOL(dgebal_)
  LOAD_SYMBOL(dgecon_)
  LOAD_SYMBOL(dgebrd_)
  LOAD_SYMBOL(dgees_)
  LOAD_SYMBOL(dgeequ_)
  LOAD_SYMBOL(dgeev_)
  LOAD_SYMBOL(dgeesx_)
  LOAD_SYMBOL(dgegs_)
  LOAD_SYMBOL(dgeevx_)
  LOAD_SYMBOL(dgehd2_)
  LOAD_SYMBOL(dgegv_)
  LOAD_SYMBOL(dgelq2_)
  LOAD_SYMBOL(dgehrd_)
  LOAD_SYMBOL(dgels_)
  LOAD_SYMBOL(dgelqf_)
  LOAD_SYMBOL(dgelss_)
  LOAD_SYMBOL(dgelsd_)
  LOAD_SYMBOL(dgelsy_)
  LOAD_SYMBOL(dgelsx_)
  LOAD_SYMBOL(dgeqlf_)
  LOAD_SYMBOL(dgeql2_)
  LOAD_SYMBOL(dgeqpf_)
  LOAD_SYMBOL(dgeqp3_)
  LOAD_SYMBOL(dgeqrf_)
  LOAD_SYMBOL(dgeqr2_)
  LOAD_SYMBOL(dgerq2_)
  LOAD_SYMBOL(dgerfs_)
  LOAD_SYMBOL(dgesc2_)
  LOAD_SYMBOL(dgerqf_)
  LOAD_SYMBOL(dgesv_)
  LOAD_SYMBOL(dgesdd_)
  LOAD_SYMBOL(dgesvx_)
  LOAD_SYMBOL(dgesvd_)
  LOAD_SYMBOL(dgetf2_)
  LOAD_SYMBOL(dgetc2_)
  LOAD_SYMBOL(dgetri_)
  LOAD_SYMBOL(dgetrf_)
  LOAD_SYMBOL(dggbak_)
  LOAD_SYMBOL(dgetrs_)
  LOAD_SYMBOL(dgges_)
  LOAD_SYMBOL(dggbal_)
  LOAD_SYMBOL(dggev_)
  LOAD_SYMBOL(dggesx_)
  LOAD_SYMBOL(dggglm_)
  LOAD_SYMBOL(dggevx_)
  LOAD_SYMBOL(dgglse_)
  LOAD_SYMBOL(dgghrd_)
  LOAD_SYMBOL(dggrqf_)
  LOAD_SYMBOL(dggqrf_)
  LOAD_SYMBOL(dggsvp_)
  LOAD_SYMBOL(dggsvd_)
  LOAD_SYMBOL(dgtrfs_)
  LOAD_SYMBOL(dgtcon_)
  LOAD_SYMBOL(dgtsvx_)
  LOAD_SYMBOL(dgtsv_)
  LOAD_SYMBOL(dgttrs_)
  LOAD_SYMBOL(dgttrf_)
  LOAD_SYMBOL(dhgeqz_)
  LOAD_SYMBOL(dgtts2_)
  LOAD_SYMBOL(dhseqr_)
  LOAD_SYMBOL(dhsein_)
  LOAD_SYMBOL(dlabad_)
  LOAD_SYMBOL(disnan_)
  LOAD_SYMBOL(dlacn2_)
  LOAD_SYMBOL(dlabrd_)
  LOAD_SYMBOL(dlacpy_)
  LOAD_SYMBOL(dlacon_)
  LOAD_SYMBOL(dlae2_)
  LOAD_SYMBOL(dladiv_)
  LOAD_SYMBOL(dlaed0_)
  LOAD_SYMBOL(dlaebz_)
  LOAD_SYMBOL(dlaed2_)
  LOAD_SYMBOL(dlaed1_)
  LOAD_SYMBOL(dlaed4_)
  LOAD_SYMBOL(dlaed3_)
  LOAD_SYMBOL(dlaed6_)
  LOAD_SYMBOL(dlaed5_)
  LOAD_SYMBOL(dlaed8_)
  LOAD_SYMBOL(dlaed7_)
  LOAD_SYMBOL(dlaeda_)
  LOAD_SYMBOL(dlaed9_)
  LOAD_SYMBOL(dlaev2_)
  LOAD_SYMBOL(dlaein_)
  LOAD_SYMBOL(dlag2_)
  LOAD_SYMBOL(dlaexc_)
  LOAD_SYMBOL(dlags2_)
  LOAD_SYMBOL(dlag2s_)
  LOAD_SYMBOL(dlagtm_)
  LOAD_SYMBOL(dlagtf_)
  LOAD_SYMBOL(dlagv2_)
  LOAD_SYMBOL(dlagts_)
  LOAD_SYMBOL(dlahr2_)
  LOAD_SYMBOL(dlahqr_)
  LOAD_SYMBOL(dlaic1_)
  LOAD_SYMBOL(dlahrd_)
  LOAD_SYMBOL(dlaln2_)
  LOAD_SYMBOL(dlaisnan_)
  LOAD_SYMBOL(dlalsa_)
  LOAD_SYMBOL(dlals0_)
  LOAD_SYMBOL(dlamrg_)
  LOAD_SYMBOL(dlalsd_)
  LOAD_SYMBOL(dlangb_)
  LOAD_SYMBOL(dlaneg_)
  LOAD_SYMBOL(dlangt_)
  LOAD_SYMBOL(dlange_)
  LOAD_SYMBOL(dlansb_)
  LOAD_SYMBOL(dlanhs_)
  LOAD_SYMBOL(dlanst_)
  LOAD_SYMBOL(dlansp_)
  LOAD_SYMBOL(dlantb_)
  LOAD_SYMBOL(dlansy_)
  LOAD_SYMBOL(dlantr_)
  LOAD_SYMBOL(dlantp_)
  LOAD_SYMBOL(dlapll_)
  LOAD_SYMBOL(dlanv2_)
  LOAD_SYMBOL(dlapy2_)
  LOAD_SYMBOL(dlapmt_)
  LOAD_SYMBOL(dlaqgb_)
  LOAD_SYMBOL(dlapy3_)
  LOAD_SYMBOL(dlaqp2_)
  LOAD_SYMBOL(dlaqge_)
  LOAD_SYMBOL(dlaqr0_)
  LOAD_SYMBOL(dlaqps_)
  LOAD_SYMBOL(dlaqr2_)
  LOAD_SYMBOL(dlaqr1_)
  LOAD_SYMBOL(dlaqr4_)
  LOAD_SYMBOL(dlaqr3_)
  LOAD_SYMBOL(dlaqsb_)
  LOAD_SYMBOL(dlaqr5_)
  LOAD_SYMBOL(dlaqsy_)
  LOAD_SYMBOL(dlaqsp_)
  LOAD_SYMBOL(dlar1v_)
  LOAD_SYMBOL(dlaqtr_)
  LOAD_SYMBOL(dlarf_)
  LOAD_SYMBOL(dlar2v_)
  LOAD_SYMBOL(dlarfg_)
  LOAD_SYMBOL(dlarfb_)
  LOAD_SYMBOL(dlarfx_)
  LOAD_SYMBOL(dlarft_)
  LOAD_SYMBOL(dlarnv_)
  LOAD_SYMBOL(dlargv_)
  LOAD_SYMBOL(dlarrb_)
  LOAD_SYMBOL(dlarra_)
  LOAD_SYMBOL(dlarrd_)
  LOAD_SYMBOL(dlarrc_)
  LOAD_SYMBOL(dlarrf_)
  LOAD_SYMBOL(dlarre_)
  LOAD_SYMBOL(dlarrk_)
  LOAD_SYMBOL(dlarrj_)
  LOAD_SYMBOL(dlarrv_)
  LOAD_SYMBOL(dlarrr_)
  LOAD_SYMBOL(dlartv_)
  LOAD_SYMBOL(dlartg_)
  LOAD_SYMBOL(dlarz_)
  LOAD_SYMBOL(dlaruv_)
  LOAD_SYMBOL(dlarzt_)
  LOAD_SYMBOL(dlarzb_)
  LOAD_SYMBOL(dlascl_)
  LOAD_SYMBOL(dlas2_)
  LOAD_SYMBOL(dlasd1_)
  LOAD_SYMBOL(dlasd0_)
  LOAD_SYMBOL(dlasd3_)
  LOAD_SYMBOL(dlasd2_)
  LOAD_SYMBOL(dlasd5_)
  LOAD_SYMBOL(dlasd4_)
  LOAD_SYMBOL(dlasd7_)
  LOAD_SYMBOL(dlasd6_)
  LOAD_SYMBOL(dlasda_)
  LOAD_SYMBOL(dlasd8_)
  LOAD_SYMBOL(dlasdt_)
  LOAD_SYMBOL(dlasdq_)
  LOAD_SYMBOL(dlasq1_)
  LOAD_SYMBOL(dlaset_)
  LOAD_SYMBOL(dlasq3_)
  LOAD_SYMBOL(dlasq2_)
  LOAD_SYMBOL(dlasq5_)
  LOAD_SYMBOL(dlasq4_)
  LOAD_SYMBOL(dlasr_)
  LOAD_SYMBOL(dlasq6_)
  LOAD_SYMBOL(dlassq_)
  LOAD_SYMBOL(dlasrt_)
  LOAD_SYMBOL(dlaswp_)
  LOAD_SYMBOL(dlasv2_)
  LOAD_SYMBOL(dlasyf_)
  LOAD_SYMBOL(dlasy2_)
  LOAD_SYMBOL(dlatdf_)
  LOAD_SYMBOL(dlatbs_)
  LOAD_SYMBOL(dlatrd_)
  LOAD_SYMBOL(dlatps_)
  LOAD_SYMBOL(dlatrz_)
  LOAD_SYMBOL(dlatrs_)
  LOAD_SYMBOL(dlauu2_)
  LOAD_SYMBOL(dlatzm_)
  // LOAD_SYMBOL(dlazq3_)
  LOAD_SYMBOL(dlauum_)
  LOAD_SYMBOL(dopgtr_)
  // LOAD_SYMBOL(dlazq4_)
  LOAD_SYMBOL(dorg2l_)
  LOAD_SYMBOL(dopmtr_)
  LOAD_SYMBOL(dorgbr_)
  LOAD_SYMBOL(dorg2r_)
  LOAD_SYMBOL(dorgl2_)
  LOAD_SYMBOL(dorghr_)
  LOAD_SYMBOL(dorgql_)
  LOAD_SYMBOL(dorglq_)
  LOAD_SYMBOL(dorgr2_)
  LOAD_SYMBOL(dorgqr_)
  LOAD_SYMBOL(dorgtr_)
  LOAD_SYMBOL(dorgrq_)
  LOAD_SYMBOL(dorm2r_)
  LOAD_SYMBOL(dorm2l_)
  LOAD_SYMBOL(dormhr_)
  LOAD_SYMBOL(dormbr_)
  LOAD_SYMBOL(dormlq_)
  LOAD_SYMBOL(dorml2_)
  LOAD_SYMBOL(dormqr_)
  LOAD_SYMBOL(dormql_)
  LOAD_SYMBOL(dormr3_)
  LOAD_SYMBOL(dormr2_)
  LOAD_SYMBOL(dormrz_)
  LOAD_SYMBOL(dormrq_)
  LOAD_SYMBOL(dpbcon_)
  LOAD_SYMBOL(dormtr_)
  LOAD_SYMBOL(dpbrfs_)
  LOAD_SYMBOL(dpbequ_)
  LOAD_SYMBOL(dpbsv_)
  LOAD_SYMBOL(dpbstf_)
  LOAD_SYMBOL(dpbtf2_)
  LOAD_SYMBOL(dpbsvx_)
  LOAD_SYMBOL(dpbtrs_)
  LOAD_SYMBOL(dpbtrf_)
  LOAD_SYMBOL(dpoequ_)
  LOAD_SYMBOL(dpocon_)
  LOAD_SYMBOL(dposv_)
  LOAD_SYMBOL(dporfs_)
  LOAD_SYMBOL(dpotf2_)
  LOAD_SYMBOL(dposvx_)
  LOAD_SYMBOL(dpotri_)
  LOAD_SYMBOL(dpotrf_)
  LOAD_SYMBOL(dppcon_)
  LOAD_SYMBOL(dpotrs_)
  LOAD_SYMBOL(dpprfs_)
  LOAD_SYMBOL(dppequ_)
  LOAD_SYMBOL(dppsvx_)
  LOAD_SYMBOL(dppsv_)
  LOAD_SYMBOL(dpptri_)
  LOAD_SYMBOL(dpptrf_)
  LOAD_SYMBOL(dptcon_)
  LOAD_SYMBOL(dpptrs_)
  LOAD_SYMBOL(dptrfs_)
  LOAD_SYMBOL(dpteqr_)
  LOAD_SYMBOL(dptsvx_)
  LOAD_SYMBOL(dptsv_)
  LOAD_SYMBOL(dpttrs_)
  LOAD_SYMBOL(dpttrf_)
  LOAD_SYMBOL(drscl_)
  LOAD_SYMBOL(dptts2_)
  LOAD_SYMBOL(dsbevd_)
  LOAD_SYMBOL(dsbev_)
  LOAD_SYMBOL(dsbgst_)
  LOAD_SYMBOL(dsbevx_)
  LOAD_SYMBOL(dsbgvd_)
  LOAD_SYMBOL(dsbgv_)
  LOAD_SYMBOL(dsbtrd_)
  LOAD_SYMBOL(dsbgvx_)
  LOAD_SYMBOL(dspcon_)
  LOAD_SYMBOL(dsgesv_)
  LOAD_SYMBOL(dspevd_)
  LOAD_SYMBOL(dspev_)
  LOAD_SYMBOL(dspgst_)
  LOAD_SYMBOL(dspevx_)
  LOAD_SYMBOL(dspgvd_)
  LOAD_SYMBOL(dspgv_)
  LOAD_SYMBOL(dsprfs_)
  LOAD_SYMBOL(dspgvx_)
  LOAD_SYMBOL(dspsvx_)
  LOAD_SYMBOL(dspsv_)
  LOAD_SYMBOL(dsptrf_)
  LOAD_SYMBOL(dsptrd_)
  LOAD_SYMBOL(dsptrs_)
  LOAD_SYMBOL(dsptri_)
  LOAD_SYMBOL(dstedc_)
  LOAD_SYMBOL(dstebz_)
  LOAD_SYMBOL(dstein_)
  LOAD_SYMBOL(dstegr_)
  LOAD_SYMBOL(dsteqr_)
  LOAD_SYMBOL(dstemr_)
  LOAD_SYMBOL(dstev_)
  LOAD_SYMBOL(dsterf_)
  LOAD_SYMBOL(dstevr_)
  LOAD_SYMBOL(dstevd_)
  LOAD_SYMBOL(dsycon_)
  LOAD_SYMBOL(dstevx_)
  LOAD_SYMBOL(dsyevd_)
  LOAD_SYMBOL(dsyev_)
  LOAD_SYMBOL(dsyevx_)
  LOAD_SYMBOL(dsyevr_)
  LOAD_SYMBOL(dsygst_)
  LOAD_SYMBOL(dsygs2_)
  LOAD_SYMBOL(dsygvd_)
  LOAD_SYMBOL(dsygv_)
  LOAD_SYMBOL(dsyrfs_)
  LOAD_SYMBOL(dsygvx_)
  LOAD_SYMBOL(dsysvx_)
  LOAD_SYMBOL(dsysv_)
  LOAD_SYMBOL(dsytf2_)
  LOAD_SYMBOL(dsytd2_)
  LOAD_SYMBOL(dsytrf_)
  LOAD_SYMBOL(dsytrd_)
  LOAD_SYMBOL(dsytrs_)
  LOAD_SYMBOL(dsytri_)
  LOAD_SYMBOL(dtbrfs_)
  LOAD_SYMBOL(dtbcon_)
  LOAD_SYMBOL(dtgevc_)
  LOAD_SYMBOL(dtbtrs_)
  LOAD_SYMBOL(dtgexc_)
  LOAD_SYMBOL(dtgex2_)
  LOAD_SYMBOL(dtgsja_)
  LOAD_SYMBOL(dtgsen_)
  LOAD_SYMBOL(dtgsy2_)
  LOAD_SYMBOL(dtgsna_)
  LOAD_SYMBOL(dtpcon_)
  LOAD_SYMBOL(dtgsyl_)
  LOAD_SYMBOL(dtptri_)
  LOAD_SYMBOL(dtprfs_)
  LOAD_SYMBOL(dtrcon_)
  LOAD_SYMBOL(dtptrs_)
  LOAD_SYMBOL(dtrexc_)
  LOAD_SYMBOL(dtrevc_)
  LOAD_SYMBOL(dtrsen_)
  LOAD_SYMBOL(dtrrfs_)
  LOAD_SYMBOL(dtrsyl_)
  LOAD_SYMBOL(dtrsna_)
  LOAD_SYMBOL(dtrtri_)
  LOAD_SYMBOL(dtrti2_)
  LOAD_SYMBOL(dtzrqf_)
  LOAD_SYMBOL(dtrtrs_)
  LOAD_SYMBOL(ieeeck_)
  LOAD_SYMBOL(dtzrzf_)
  LOAD_SYMBOL(ilaver_)
  LOAD_SYMBOL(ilaenv_)
  LOAD_SYMBOL(lsamen_)
  LOAD_SYMBOL(iparmq_)
  LOAD_SYMBOL(sbdsqr_)
  LOAD_SYMBOL(sbdsdc_)
  LOAD_SYMBOL(sgbbrd_)
  LOAD_SYMBOL(sdisna_)
  LOAD_SYMBOL(sgbequ_)
  LOAD_SYMBOL(sgbcon_)
  LOAD_SYMBOL(sgbsv_)
  LOAD_SYMBOL(sgbrfs_)
  LOAD_SYMBOL(sgbtf2_)
  LOAD_SYMBOL(sgbsvx_)
  LOAD_SYMBOL(sgbtrs_)
  LOAD_SYMBOL(sgbtrf_)
  LOAD_SYMBOL(sgebal_)
  LOAD_SYMBOL(sgebak_)
  LOAD_SYMBOL(sgebrd_)
  LOAD_SYMBOL(sgebd2_)
  LOAD_SYMBOL(sgeequ_)
  LOAD_SYMBOL(sgecon_)
  LOAD_SYMBOL(sgeesx_)
  LOAD_SYMBOL(sgees_)
  LOAD_SYMBOL(sgeevx_)
  LOAD_SYMBOL(sgeev_)
  LOAD_SYMBOL(sgegv_)
  LOAD_SYMBOL(sgegs_)
  LOAD_SYMBOL(sgehrd_)
  LOAD_SYMBOL(sgehd2_)
  LOAD_SYMBOL(sgelqf_)
  LOAD_SYMBOL(sgelq2_)
  LOAD_SYMBOL(sgelsd_)
  LOAD_SYMBOL(sgels_)
  LOAD_SYMBOL(sgelsx_)
  LOAD_SYMBOL(sgelss_)
  LOAD_SYMBOL(sgeql2_)
  LOAD_SYMBOL(sgelsy_)
  LOAD_SYMBOL(sgeqp3_)
  LOAD_SYMBOL(sgeqlf_)
  LOAD_SYMBOL(sgeqr2_)
  LOAD_SYMBOL(sgeqpf_)
  LOAD_SYMBOL(sgerfs_)
  LOAD_SYMBOL(sgeqrf_)
  LOAD_SYMBOL(sgerqf_)
  LOAD_SYMBOL(sgerq2_)
  LOAD_SYMBOL(sgesdd_)
  LOAD_SYMBOL(sgesc2_)
  LOAD_SYMBOL(sgesvd_)
  LOAD_SYMBOL(sgesv_)
  LOAD_SYMBOL(sgetc2_)
  LOAD_SYMBOL(sgesvx_)
  LOAD_SYMBOL(sgetrf_)
  LOAD_SYMBOL(sgetf2_)
  LOAD_SYMBOL(sgetrs_)
  LOAD_SYMBOL(sgetri_)
  LOAD_SYMBOL(sggbal_)
  LOAD_SYMBOL(sggbak_)
  LOAD_SYMBOL(sggesx_)
  LOAD_SYMBOL(sgges_)
  LOAD_SYMBOL(sggevx_)
  LOAD_SYMBOL(sggev_)
  LOAD_SYMBOL(sgghrd_)
  LOAD_SYMBOL(sggglm_)
  LOAD_SYMBOL(sggqrf_)
  LOAD_SYMBOL(sgglse_)
  LOAD_SYMBOL(sggsvd_)
  LOAD_SYMBOL(sggrqf_)
  LOAD_SYMBOL(sgtcon_)
  LOAD_SYMBOL(sggsvp_)
  LOAD_SYMBOL(sgtsv_)
  LOAD_SYMBOL(sgtrfs_)
  LOAD_SYMBOL(sgttrf_)
  LOAD_SYMBOL(sgtsvx_)
  LOAD_SYMBOL(sgtts2_)
  LOAD_SYMBOL(sgttrs_)
  LOAD_SYMBOL(shsein_)
  LOAD_SYMBOL(shgeqz_)
  LOAD_SYMBOL(sisnan_)
  LOAD_SYMBOL(shseqr_)
  LOAD_SYMBOL(slabrd_)
  LOAD_SYMBOL(slabad_)
  LOAD_SYMBOL(slacon_)
  LOAD_SYMBOL(slacn2_)
  LOAD_SYMBOL(sladiv_)
  LOAD_SYMBOL(slacpy_)
  LOAD_SYMBOL(slaebz_)
  LOAD_SYMBOL(slae2_)
  LOAD_SYMBOL(slaed1_)
  LOAD_SYMBOL(slaed0_)
  LOAD_SYMBOL(slaed3_)
  LOAD_SYMBOL(slaed2_)
  LOAD_SYMBOL(slaed5_)
  LOAD_SYMBOL(slaed4_)
  LOAD_SYMBOL(slaed7_)
  LOAD_SYMBOL(slaed6_)
  LOAD_SYMBOL(slaed9_)
  LOAD_SYMBOL(slaed8_)
  LOAD_SYMBOL(slaein_)
  LOAD_SYMBOL(slaeda_)
  LOAD_SYMBOL(slaexc_)
  LOAD_SYMBOL(slaev2_)
  LOAD_SYMBOL(slag2d_)
  LOAD_SYMBOL(slag2_)
  LOAD_SYMBOL(slagtf_)
  LOAD_SYMBOL(slags2_)
  LOAD_SYMBOL(slagts_)
  LOAD_SYMBOL(slagtm_)
  LOAD_SYMBOL(slahqr_)
  LOAD_SYMBOL(slagv2_)
  LOAD_SYMBOL(slahrd_)
  LOAD_SYMBOL(slahr2_)
  LOAD_SYMBOL(slaisnan_)
  LOAD_SYMBOL(slaic1_)
  LOAD_SYMBOL(slals0_)
  LOAD_SYMBOL(slaln2_)
  LOAD_SYMBOL(slalsd_)
  LOAD_SYMBOL(slalsa_)
  LOAD_SYMBOL(slaneg_)
  LOAD_SYMBOL(slamrg_)
  LOAD_SYMBOL(slange_)
  LOAD_SYMBOL(slangb_)
  LOAD_SYMBOL(slanhs_)
  LOAD_SYMBOL(slangt_)
  LOAD_SYMBOL(slansp_)
  LOAD_SYMBOL(slansb_)
  LOAD_SYMBOL(slansy_)
  LOAD_SYMBOL(slanst_)
  LOAD_SYMBOL(slantp_)
  LOAD_SYMBOL(slantb_)
  LOAD_SYMBOL(slanv2_)
  LOAD_SYMBOL(slantr_)
  LOAD_SYMBOL(slapmt_)
  LOAD_SYMBOL(slapll_)
  LOAD_SYMBOL(slapy3_)
  LOAD_SYMBOL(slapy2_)
  LOAD_SYMBOL(slaqge_)
  LOAD_SYMBOL(slaqgb_)
  LOAD_SYMBOL(slaqps_)
  LOAD_SYMBOL(slaqp2_)
  LOAD_SYMBOL(slaqr1_)
  LOAD_SYMBOL(slaqr0_)
  LOAD_SYMBOL(slaqr3_)
  LOAD_SYMBOL(slaqr2_)
  LOAD_SYMBOL(slaqr5_)
  LOAD_SYMBOL(slaqr4_)
  LOAD_SYMBOL(slaqsp_)
  LOAD_SYMBOL(slaqsb_)
  LOAD_SYMBOL(slaqtr_)
  LOAD_SYMBOL(slaqsy_)
  LOAD_SYMBOL(slar2v_)
  LOAD_SYMBOL(slar1v_)
  LOAD_SYMBOL(slarfb_)
  LOAD_SYMBOL(slarf_)
  LOAD_SYMBOL(slarft_)
  LOAD_SYMBOL(slarfg_)
  LOAD_SYMBOL(slargv_)
  LOAD_SYMBOL(slarfx_)
  LOAD_SYMBOL(slarra_)
  LOAD_SYMBOL(slarnv_)
  LOAD_SYMBOL(slarrc_)
  LOAD_SYMBOL(slarrb_)
  LOAD_SYMBOL(slarre_)
  LOAD_SYMBOL(slarrd_)
  LOAD_SYMBOL(slarrj_)
  LOAD_SYMBOL(slarrf_)
  LOAD_SYMBOL(slarrk_)
  LOAD_SYMBOL(slarrr_)
  LOAD_SYMBOL(slarrv_)
  LOAD_SYMBOL(slartg_)
  LOAD_SYMBOL(slartv_)
  LOAD_SYMBOL(slaruv_)
  LOAD_SYMBOL(slarz_)
  LOAD_SYMBOL(slarzb_)
  LOAD_SYMBOL(slarzt_)
  LOAD_SYMBOL(slas2_)
  LOAD_SYMBOL(slascl_)
  LOAD_SYMBOL(slasd0_)
  LOAD_SYMBOL(slasd1_)
  LOAD_SYMBOL(slasd2_)
  LOAD_SYMBOL(slasd3_)
  LOAD_SYMBOL(slasd4_)
  LOAD_SYMBOL(slasd5_)
  LOAD_SYMBOL(slasd6_)
  LOAD_SYMBOL(slasd7_)
  LOAD_SYMBOL(slasd8_)
  LOAD_SYMBOL(slasda_)
  LOAD_SYMBOL(slasdq_)
  LOAD_SYMBOL(slasdt_)
  LOAD_SYMBOL(slaset_)
  LOAD_SYMBOL(slasq1_)
  LOAD_SYMBOL(slasq2_)
  LOAD_SYMBOL(slasq3_)
  LOAD_SYMBOL(slasq4_)
  LOAD_SYMBOL(slasq5_)
  LOAD_SYMBOL(slasq6_)
  LOAD_SYMBOL(slasr_)
  LOAD_SYMBOL(slasrt_)
  LOAD_SYMBOL(slassq_)
  LOAD_SYMBOL(slasv2_)
  LOAD_SYMBOL(slaswp_)
  LOAD_SYMBOL(slasy2_)
  LOAD_SYMBOL(slasyf_)
  LOAD_SYMBOL(slatbs_)
  LOAD_SYMBOL(slatdf_)
  LOAD_SYMBOL(slatps_)
  LOAD_SYMBOL(slatrd_)
  LOAD_SYMBOL(slatrs_)
  LOAD_SYMBOL(slatrz_)
  LOAD_SYMBOL(slatzm_)
  LOAD_SYMBOL(slauu2_)
  LOAD_SYMBOL(slauum_)
  // LOAD_SYMBOL(slazq3_)
  // LOAD_SYMBOL(slazq4_)
  LOAD_SYMBOL(sopgtr_)
  LOAD_SYMBOL(sopmtr_)
  LOAD_SYMBOL(sorg2l_)
  LOAD_SYMBOL(sorg2r_)
  LOAD_SYMBOL(sorgbr_)
  LOAD_SYMBOL(sorghr_)
  LOAD_SYMBOL(sorgl2_)
  LOAD_SYMBOL(sorglq_)
  LOAD_SYMBOL(sorgql_)
  LOAD_SYMBOL(sorgqr_)
  LOAD_SYMBOL(sorgr2_)
  LOAD_SYMBOL(sorgrq_)
  LOAD_SYMBOL(sorgtr_)
  LOAD_SYMBOL(sorm2l_)
  LOAD_SYMBOL(sorm2r_)
  LOAD_SYMBOL(sormbr_)
  LOAD_SYMBOL(sormhr_)
  LOAD_SYMBOL(sorml2_)
  LOAD_SYMBOL(sormlq_)
  LOAD_SYMBOL(sormql_)
  LOAD_SYMBOL(sormqr_)
  LOAD_SYMBOL(sormr2_)
  LOAD_SYMBOL(sormr3_)
  LOAD_SYMBOL(sormrq_)
  LOAD_SYMBOL(sormrz_)
  LOAD_SYMBOL(sormtr_)
  LOAD_SYMBOL(spbcon_)
  LOAD_SYMBOL(spbequ_)
  LOAD_SYMBOL(spbrfs_)
  LOAD_SYMBOL(spbstf_)
  LOAD_SYMBOL(spbsv_)
  LOAD_SYMBOL(spbsvx_)
  LOAD_SYMBOL(spbtf2_)
  LOAD_SYMBOL(spbtrf_)
  LOAD_SYMBOL(spbtrs_)
  LOAD_SYMBOL(spocon_)
  LOAD_SYMBOL(spoequ_)
  LOAD_SYMBOL(sporfs_)
  LOAD_SYMBOL(sposv_)
  LOAD_SYMBOL(sposvx_)
  LOAD_SYMBOL(spotf2_)
  LOAD_SYMBOL(spotrf_)
  LOAD_SYMBOL(spotri_)
  LOAD_SYMBOL(spotrs_)
  LOAD_SYMBOL(sppcon_)
  LOAD_SYMBOL(sppequ_)
  LOAD_SYMBOL(spprfs_)
  LOAD_SYMBOL(sppsv_)
  LOAD_SYMBOL(sppsvx_)
  LOAD_SYMBOL(spptrf_)
  LOAD_SYMBOL(spptri_)
  LOAD_SYMBOL(spptrs_)
  LOAD_SYMBOL(sptcon_)
  LOAD_SYMBOL(spteqr_)
  LOAD_SYMBOL(sptrfs_)
  LOAD_SYMBOL(sptsv_)
  LOAD_SYMBOL(sptsvx_)
  LOAD_SYMBOL(spttrf_)
  LOAD_SYMBOL(spttrs_)
  LOAD_SYMBOL(sptts2_)
  LOAD_SYMBOL(srscl_)
  LOAD_SYMBOL(ssbev_)
  LOAD_SYMBOL(ssbevd_)
  LOAD_SYMBOL(ssbevx_)
  LOAD_SYMBOL(ssbgst_)
  LOAD_SYMBOL(ssbgv_)
  LOAD_SYMBOL(ssbgvd_)
  LOAD_SYMBOL(ssbgvx_)
  LOAD_SYMBOL(ssbtrd_)
  LOAD_SYMBOL(sspcon_)
  LOAD_SYMBOL(sspev_)
  LOAD_SYMBOL(sspevd_)
  LOAD_SYMBOL(sspevx_)
  LOAD_SYMBOL(sspgst_)
  LOAD_SYMBOL(sspgv_)
  LOAD_SYMBOL(sspgvd_)
  LOAD_SYMBOL(sspgvx_)
  LOAD_SYMBOL(ssprfs_)
  LOAD_SYMBOL(sspsv_)
  LOAD_SYMBOL(sspsvx_)
  LOAD_SYMBOL(ssptrd_)
  LOAD_SYMBOL(ssptrf_)
  LOAD_SYMBOL(ssptri_)
  LOAD_SYMBOL(ssptrs_)
  LOAD_SYMBOL(sstebz_)
  LOAD_SYMBOL(sstedc_)
  LOAD_SYMBOL(sstegr_)
  LOAD_SYMBOL(sstein_)
  LOAD_SYMBOL(sstemr_)
  LOAD_SYMBOL(ssteqr_)
  LOAD_SYMBOL(ssterf_)
  LOAD_SYMBOL(sstev_)
  LOAD_SYMBOL(sstevd_)
  LOAD_SYMBOL(sstevr_)
  LOAD_SYMBOL(sstevx_)
  LOAD_SYMBOL(ssycon_)
  LOAD_SYMBOL(ssyev_)
  LOAD_SYMBOL(ssyevd_)
  LOAD_SYMBOL(ssyevr_)
  LOAD_SYMBOL(ssyevx_)
  LOAD_SYMBOL(ssygs2_)
  LOAD_SYMBOL(ssygst_)
  LOAD_SYMBOL(ssygv_)
  LOAD_SYMBOL(ssygvd_)
  LOAD_SYMBOL(ssygvx_)
  LOAD_SYMBOL(ssyrfs_)
  LOAD_SYMBOL(ssysv_)
  LOAD_SYMBOL(ssysvx_)
  LOAD_SYMBOL(ssytd2_)
  LOAD_SYMBOL(ssytf2_)
  LOAD_SYMBOL(ssytrd_)
  LOAD_SYMBOL(ssytrf_)
  LOAD_SYMBOL(ssytri_)
  LOAD_SYMBOL(ssytrs_)
  LOAD_SYMBOL(stbcon_)
  LOAD_SYMBOL(stbrfs_)
  LOAD_SYMBOL(stbtrs_)
  LOAD_SYMBOL(stgevc_)
  LOAD_SYMBOL(stgex2_)
  LOAD_SYMBOL(stgexc_)
  LOAD_SYMBOL(stgsen_)
  LOAD_SYMBOL(stgsja_)
  LOAD_SYMBOL(stgsna_)
  LOAD_SYMBOL(stgsy2_)
  LOAD_SYMBOL(stgsyl_)
  LOAD_SYMBOL(stpcon_)
  LOAD_SYMBOL(stprfs_)
  LOAD_SYMBOL(stptri_)
  LOAD_SYMBOL(stptrs_)
  LOAD_SYMBOL(strcon_)
  LOAD_SYMBOL(strevc_)
  LOAD_SYMBOL(strexc_)
  LOAD_SYMBOL(strrfs_)
  LOAD_SYMBOL(strsen_)
  LOAD_SYMBOL(strsna_)
  LOAD_SYMBOL(strsyl_)
  LOAD_SYMBOL(strti2_)
  LOAD_SYMBOL(strtri_)
  LOAD_SYMBOL(strtrs_)
  LOAD_SYMBOL(stzrqf_)
  LOAD_SYMBOL(stzrzf_)
  LOAD_SYMBOL(dlamch_)
  // LOAD_SYMBOL(dlamc1_)
  // LOAD_SYMBOL(dlamc2_)
  LOAD_SYMBOL(dlamc3_)
  // LOAD_SYMBOL(dlamc4_)
  // LOAD_SYMBOL(dlamc5_)
  LOAD_SYMBOL(dsecnd_)
  LOAD_SYMBOL(lsame_)
  LOAD_SYMBOL(second_)
  LOAD_SYMBOL(slamch_)
  // LOAD_SYMBOL(slamc1_)
  // LOAD_SYMBOL(slamc2_)
  LOAD_SYMBOL(slamc3_)
  // LOAD_SYMBOL(slamc4_)
  // LOAD_SYMBOL(slamc5_)

#undef LOAD_SYMBOL

  return JNI_VERSION_1_6;
}

void JNI_OnUnload(UNUSED JavaVM *vm, UNUSED void *reserved) {
  dlclose(lapack);
}
