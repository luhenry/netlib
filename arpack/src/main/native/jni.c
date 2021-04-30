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

#include "dev_ludovic_netlib_arpack_JNIARPACK.h"

#define UNUSED __attribute__((unused))

#define TRUE 1
#define FALSE 0

static jfieldID booleanW_val_fieldID;
static jfieldID intW_val_fieldID;
static jfieldID floatW_val_fieldID;
static jfieldID doubleW_val_fieldID;
static jfieldID StringW_val_fieldID;

static void *arpack;

static void throwOOM(JNIEnv *env) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dmout_)(int *lout, int *m, int *n, double *a, int *lda, int *idigit, char *ifmt);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dmoutK(JNIEnv *env, UNUSED jobject obj,
    jint lout, jint m, jint n, jdoubleArray a, jint offseta, jint lda, jint idigit, jstring ifmt) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*smout_)(int *lout, int *m, int *n, float *a, int *lda, int *idigit, char *ifmt);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_smoutK(JNIEnv *env, UNUSED jobject obj,
    jint lout, jint m, jint n, jfloatArray a, jint offseta, jint lda, jint idigit, jstring ifmt) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dvout_)(int *lout, int *n, double *sx, int *idigit, char *ifmt);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dvoutK(JNIEnv *env, UNUSED jobject obj,
    jint lout, jint n, jdoubleArray sx, jint offsetsx, jint idigit, jstring ifmt) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*svout_)(int *lout, int *n, float *sx, int *idigit, char *ifmt);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_svoutK(JNIEnv *env, UNUSED jobject obj,
    jint lout, jint n, jfloatArray sx, jint offsetsx, jint idigit, jstring ifmt) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ivout_)(int *lout, int *n, int *ix, int *idigit, char *ifmt);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ivoutK(JNIEnv *env, UNUSED jobject obj,
    jint lout, jint n, jintArray ix, jint offsetix, jint idigit, jstring ifmt) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dgetv0_)(int *ido, char *bmat, int *itry, int *initv, int *n, int *j, double *v, int *ldv, double *resid, double *rnorm, int *ipntr, double *workd, int *ierr);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dgetv0K(JNIEnv *env, UNUSED jobject obj,
    jobject ido, jstring bmat, jint itry, jboolean initv, jint n, jint j, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray resid, jint offsetresid, jobject rnorm, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jobject ierr) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sgetv0_)(int *ido, char *bmat, int *itry, int *initv, int *n, int *j, float *v, int *ldv, float *resid, float *rnorm, int *ipntr, float *workd, int *ierr);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sgetv0K(JNIEnv *env, UNUSED jobject obj,
    jobject ido, jstring bmat, jint itry, jboolean initv, jint n, jint j, jfloatArray v, jint offsetv, jint ldv, jfloatArray resid, jint offsetresid, jobject rnorm, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jobject ierr) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

// static void (*dlaqrb_)(int *wantt, int *n, int *ilo, int *ihi, double *h, int *ldh, double *wr, double *wi, double *z, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dlaqrbK(JNIEnv *env, UNUSED jobject obj,
    jboolean wantt, jint n, jint ilo, jint ihi, jdoubleArray h, jint offseth, jint ldh, jdoubleArray wr, jint offsetwr, jdoubleArray wi, jint offsetwi, jdoubleArray z, jint offsetz, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

// static void (*slaqrb_)(int *wantt, int *n, int *ilo, int *ihi, float *h, int *ldh, float *wr, float *wi, float *z, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_slaqrbK(JNIEnv *env, UNUSED jobject obj,
    jboolean wantt, jint n, jint ilo, jint ihi, jfloatArray h, jint offseth, jint ldh, jfloatArray wr, jint offsetwr, jfloatArray wi, jint offsetwi, jfloatArray z, jint offsetz, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dnaitr_)(int *ido, char *bmat, int *n, int *k, int *np, int *nb, double *resid, double *rnorm, double *v, int *ldv, double *h, int *ldh, int *ipntr, double *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dnaitrK(JNIEnv *env, UNUSED jobject obj,
    jobject ido, jstring bmat, jint n, jint k, jint np, jint nb, jdoubleArray resid, jint offsetresid, jobject rnorm, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray h, jint offseth, jint ldh, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*snaitr_)(int *ido, char *bmat, int *n, int *k, int *np, int *nb, float *resid, float *rnorm, float *v, int *ldv, float *h, int *ldh, int *ipntr, float *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_snaitrK(JNIEnv *env, UNUSED jobject obj,
    jobject ido, jstring bmat, jint n, jint k, jint np, jint nb, jfloatArray resid, jint offsetresid, jobject rnorm, jfloatArray v, jint offsetv, jint ldv, jfloatArray h, jint offseth, jint ldh, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dnapps_)(int *n, int *kev, int *np, double *shiftr, double *shifti, double *v, int *ldv, double *h, int *ldh, double *resid, double *q, int *ldq, double *workl, double *workd);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dnappsK(JNIEnv *env, UNUSED jobject obj,
    jint n, jobject kev, jint np, jdoubleArray shiftr, jint offsetshiftr, jdoubleArray shifti, jint offsetshifti, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray h, jint offseth, jint ldh, jdoubleArray resid, jint offsetresid, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray workl, jint offsetworkl, jdoubleArray workd, jint offsetworkd) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*snapps_)(int *n, int *kev, int *np, float *shiftr, float *shifti, float *v, int *ldv, float *h, int *ldh, float *resid, float *q, int *ldq, float *workl, float *workd);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_snappsK(JNIEnv *env, UNUSED jobject obj,
    jint n, jobject kev, jint np, jfloatArray shiftr, jint offsetshiftr, jfloatArray shifti, jint offsetshifti, jfloatArray v, jint offsetv, jint ldv, jfloatArray h, jint offseth, jint ldh, jfloatArray resid, jint offsetresid, jfloatArray q, jint offsetq, jint ldq, jfloatArray workl, jint offsetworkl, jfloatArray workd, jint offsetworkd) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dnaup2_)(int *ido, char *bmat, int *n, char *which, int *nev, int *np, double *tol, double *resid, int *mode, int *iupd, int *ishift, int *mxiter, double *v, int *ldv, double *h, int *ldh, double *ritzr, double *ritzi, double *bounds, double *q, int *ldq, double *workl, int *ipntr, double *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dnaup2K(JNIEnv *env, UNUSED jobject obj,
    jobject ido, jstring bmat, jint n, jstring which, jobject nev, jobject np, jdouble tol, jdoubleArray resid, jint offsetresid, jint mode, jint iupd, jint ishift, jobject mxiter, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray h, jint offseth, jint ldh, jdoubleArray ritzr, jint offsetritzr, jdoubleArray ritzi, jint offsetritzi, jdoubleArray bounds, jint offsetbounds, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray workl, jint offsetworkl, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*snaup2_)(int *ido, char *bmat, int *n, char *which, int *nev, int *np, float *tol, float *resid, int *mode, int *iupd, int *ishift, int *mxiter, float *v, int *ldv, float *h, int *ldh, float *ritzr, float *ritzi, float *bounds, float *q, int *ldq, float *workl, int *ipntr, float *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_snaup2K(JNIEnv *env, UNUSED jobject obj,
    jobject ido, jstring bmat, jint n, jstring which, jobject nev, jobject np, jfloat tol, jfloatArray resid, jint offsetresid, jint mode, jint iupd, jint ishift, jobject mxiter, jfloatArray v, jint offsetv, jint ldv, jfloatArray h, jint offseth, jint ldh, jfloatArray ritzr, jint offsetritzr, jfloatArray ritzi, jint offsetritzi, jfloatArray bounds, jint offsetbounds, jfloatArray q, jint offsetq, jint ldq, jfloatArray workl, jint offsetworkl, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dnaupd_)(int *ido, char *bmat, int *n, char *which, int *nev, double *tol, double *resid, int *ncv, double *v, int *ldv, int *iparam, int *ipntr, double *workd, double *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dnaupdK(JNIEnv *env, UNUSED jobject obj,
    jobject ido, jstring bmat, jint n, jstring which, jint nev, jobject tol, jdoubleArray resid, jint offsetresid, jint ncv, jdoubleArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jdoubleArray workl, jint offsetworkl, jint lworkl, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*snaupd_)(int *ido, char *bmat, int *n, char *which, int *nev, float *tol, float *resid, int *ncv, float *v, int *ldv, int *iparam, int *ipntr, float *workd, float *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_snaupdK(JNIEnv *env, UNUSED jobject obj,
    jobject ido, jstring bmat, jint n, jstring which, jint nev, jobject tol, jfloatArray resid, jint offsetresid, jint ncv, jfloatArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jfloatArray workl, jint offsetworkl, jint lworkl, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dnconv_)(int *n, double *ritzr, double *ritzi, double *bounds, double *tol, int *nconv);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dnconvK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray ritzr, jint offsetritzr, jdoubleArray ritzi, jint offsetritzi, jdoubleArray bounds, jint offsetbounds, jdouble tol, jobject nconv) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*snconv_)(int *n, float *ritzr, float *ritzi, float *bounds, float *tol, int *nconv);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_snconvK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray ritzr, jint offsetritzr, jfloatArray ritzi, jint offsetritzi, jfloatArray bounds, jint offsetbounds, jfloat tol, jobject nconv) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsconv_)(int *n, double *ritz, double *bounds, double *tol, int *nconv);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsconvK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray ritz, jint offsetritz, jdoubleArray bounds, jint offsetbounds, jdouble tol, jobject nconv) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssconv_)(int *n, float *ritz, float *bounds, float *tol, int *nconv);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssconvK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray ritz, jint offsetritz, jfloatArray bounds, jint offsetbounds, jfloat tol, jobject nconv) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dneigh_)(double *rnorm, int *n, double *h, int *ldh, double *ritzr, double *ritzi, double *bounds, double *q, int *ldq, double *workl, int *ierr);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dneighK(JNIEnv *env, UNUSED jobject obj,
    jdouble rnorm, jobject n, jdoubleArray h, jint offseth, jint ldh, jdoubleArray ritzr, jint offsetritzr, jdoubleArray ritzi, jint offsetritzi, jdoubleArray bounds, jint offsetbounds, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray workl, jint offsetworkl, jobject ierr) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sneigh_)(float *rnorm, int *n, float *h, int *ldh, float *ritzr, float *ritzi, float *bounds, float *q, int *ldq, float *workl, int *ierr);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sneighK(JNIEnv *env, UNUSED jobject obj,
    jfloat rnorm, jobject n, jfloatArray h, jint offseth, jint ldh, jfloatArray ritzr, jint offsetritzr, jfloatArray ritzi, jint offsetritzi, jfloatArray bounds, jint offsetbounds, jfloatArray q, jint offsetq, jint ldq, jfloatArray workl, jint offsetworkl, jobject ierr) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dneupd_)(int *rvec, char *howmny, int *select, double *dr, double *di, double *z, int *ldz, double *sigmar, double *sigmai, double *workev, char *bmat, int *n, char *which, int *nev, double *tol, double *resid, int *ncv, double *v, int *ldv, int *iparam, int *ipntr, double *workd, double *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dneupdK(JNIEnv *env, UNUSED jobject obj,
    jboolean rvec, jstring howmny, jbooleanArray select, jint offsetselect, jdoubleArray dr, jint offsetdr, jdoubleArray di, jint offsetdi, jdoubleArray z, jint offsetz, jint ldz, jdouble sigmar, jdouble sigmai, jdoubleArray workev, jint offsetworkev, jstring bmat, jint n, jstring which, jobject nev, jdouble tol, jdoubleArray resid, jint offsetresid, jint ncv, jdoubleArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jdoubleArray workl, jint offsetworkl, jint lworkl, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sneupd_)(int *rvec, char *howmny, int *select, float *dr, float *di, float *z, int *ldz, float *sigmar, float *sigmai, float *workev, char *bmat, int *n, char *which, int *nev, float *tol, float *resid, int *ncv, float *v, int *ldv, int *iparam, int *ipntr, float *workd, float *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sneupdK(JNIEnv *env, UNUSED jobject obj,
    jboolean rvec, jstring howmny, jbooleanArray select, jint offsetselect, jfloatArray dr, jint offsetdr, jfloatArray di, jint offsetdi, jfloatArray z, jint offsetz, jint ldz, jfloat sigmar, jfloat sigmai, jfloatArray workev, jint offsetworkev, jstring bmat, jint n, jstring which, jobject nev, jfloat tol, jfloatArray resid, jint offsetresid, jint ncv, jfloatArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jfloatArray workl, jint offsetworkl, jint lworkl, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dngets_)(int *ishift, char *which, int *kev, int *np, double *ritzr, double *ritzi, double *bounds, double *shiftr, double *shifti);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dngetsK(JNIEnv *env, UNUSED jobject obj,
    jint ishift, jstring which, jobject kev, jobject np, jdoubleArray ritzr, jint offsetritzr, jdoubleArray ritzi, jint offsetritzi, jdoubleArray bounds, jint offsetbounds, jdoubleArray shiftr, jint offsetshiftr, jdoubleArray shifti, jint offsetshifti) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sngets_)(int *ishift, char *which, int *kev, int *np, float *ritzr, float *ritzi, float *bounds, float *shiftr, float *shifti);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sngetsK(JNIEnv *env, UNUSED jobject obj,
    jint ishift, jstring which, jobject kev, jobject np, jfloatArray ritzr, jint offsetritzr, jfloatArray ritzi, jint offsetritzi, jfloatArray bounds, jint offsetbounds, jfloatArray shiftr, jint offsetshiftr, jfloatArray shifti, jint offsetshifti) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsaitr_)(int *ido, char *bmat, int *n, int *k, int *np, int *mode, double *resid, double *rnorm, double *v, int *ldv, double *h, int *ldh, int *ipntr, double *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsaitrK(JNIEnv *env, UNUSED jobject obj,
    jobject ido, jstring bmat, jint n, jint k, jint np, jint mode, jdoubleArray resid, jint offsetresid, jobject rnorm, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray h, jint offseth, jint ldh, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssaitr_)(int *ido, char *bmat, int *n, int *k, int *np, int *mode, float *resid, float *rnorm, float *v, int *ldv, float *h, int *ldh, int *ipntr, float *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssaitrK(JNIEnv *env, UNUSED jobject obj,
    jobject ido, jstring bmat, jint n, jint k, jint np, jint mode, jfloatArray resid, jint offsetresid, jobject rnorm, jfloatArray v, jint offsetv, jint ldv, jfloatArray h, jint offseth, jint ldh, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsapps_)(int *n, int *kev, int *np, double *shift, double *v, int *ldv, double *h, int *ldh, double *resid, double *q, int *ldq, double *workd);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsappsK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint kev, jint np, jdoubleArray shift, jint offsetshift, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray h, jint offseth, jint ldh, jdoubleArray resid, jint offsetresid, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray workd, jint offsetworkd) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssapps_)(int *n, int *kev, int *np, float *shift, float *v, int *ldv, float *h, int *ldh, float *resid, float *q, int *ldq, float *workd);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssappsK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint kev, jint np, jfloatArray shift, jint offsetshift, jfloatArray v, jint offsetv, jint ldv, jfloatArray h, jint offseth, jint ldh, jfloatArray resid, jint offsetresid, jfloatArray q, jint offsetq, jint ldq, jfloatArray workd, jint offsetworkd) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsaup2_)(int *ido, char *bmat, int *n, char *which, int *nev, int *np, double *tol, double *resid, int *mode, int *iupd, int *ishift, int *mxiter, double *v, int *ldv, double *h, int *ldh, double *ritz, double *bounds, double *q, int *ldq, double *workl, int *ipntr, double *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsaup2K(JNIEnv *env, UNUSED jobject obj,
    jobject ido, jstring bmat, jint n, jstring which, jobject nev, jobject np, jdouble tol, jdoubleArray resid, jint offsetresid, jint mode, jint iupd, jint ishift, jobject mxiter, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray h, jint offseth, jint ldh, jdoubleArray ritz, jint offsetritz, jdoubleArray bounds, jint offsetbounds, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray workl, jint offsetworkl, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssaup2_)(int *ido, char *bmat, int *n, char *which, int *nev, int *np, float *tol, float *resid, int *mode, int *iupd, int *ishift, int *mxiter, float *v, int *ldv, float *h, int *ldh, float *ritz, float *bounds, float *q, int *ldq, float *workl, int *ipntr, float *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssaup2K(JNIEnv *env, UNUSED jobject obj,
    jobject ido, jstring bmat, jint n, jstring which, jobject nev, jobject np, jfloat tol, jfloatArray resid, jint offsetresid, jint mode, jint iupd, jint ishift, jobject mxiter, jfloatArray v, jint offsetv, jint ldv, jfloatArray h, jint offseth, jint ldh, jfloatArray ritz, jint offsetritz, jfloatArray bounds, jint offsetbounds, jfloatArray q, jint offsetq, jint ldq, jfloatArray workl, jint offsetworkl, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dseigt_)(double *rnorm, int *n, double *h, int *ldh, double *eig, double *bounds, double *workl, int *ierr);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dseigtK(JNIEnv *env, UNUSED jobject obj,
    jdouble rnorm, jint n, jdoubleArray h, jint offseth, jint ldh, jdoubleArray eig, jint offseteig, jdoubleArray bounds, jint offsetbounds, jdoubleArray workl, jint offsetworkl, jobject ierr) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sseigt_)(float *rnorm, int *n, float *h, int *ldh, float *eig, float *bounds, float *workl, int *ierr);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sseigtK(JNIEnv *env, UNUSED jobject obj,
    jfloat rnorm, jint n, jfloatArray h, jint offseth, jint ldh, jfloatArray eig, jint offseteig, jfloatArray bounds, jint offsetbounds, jfloatArray workl, jint offsetworkl, jobject ierr) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsesrt_)(char *which, int *apply, int *n, double *x, int *na, double *a, int *lda);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsesrtK(JNIEnv *env, UNUSED jobject obj,
    jstring which, jboolean apply, jint n, jdoubleArray x, jint offsetx, jint na, jdoubleArray a, jint offseta, jint lda) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssesrt_)(char *which, int *apply, int *n, float *x, int *na, float *a, int *lda);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssesrtK(JNIEnv *env, UNUSED jobject obj,
    jstring which, jboolean apply, jint n, jfloatArray x, jint offsetx, jint na, jfloatArray a, jint offseta, jint lda) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsaupd_)(int *ido, char *bmat, int *n, char *which, int *nev, double *tol, double *resid, int *ncv, double *v, int *ldv, int *iparam, int *ipntr, double *workd, double *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsaupdK(JNIEnv *env, UNUSED jobject obj,
    jobject ido, jstring bmat, jint n, jstring which, jint nev, jobject tol, jdoubleArray resid, jint offsetresid, jint ncv, jdoubleArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jdoubleArray workl, jint offsetworkl, jint lworkl, jobject info) {
  // TODO
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssaupd_)(int *ido, char *bmat, int *n, char *which, int *nev, float *tol, float *resid, int *ncv, float *v, int *ldv, int *iparam, int *ipntr, float *workd, float *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssaupdK(JNIEnv *env, UNUSED jobject obj,
    jobject ido, jstring bmat, jint n, jstring which, jint nev, jobject tol, jfloatArray resid, jint offsetresid, jint ncv, jfloatArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jfloatArray workl, jint offsetworkl, jint lworkl, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dseupd_)(int *rvec, char *howmny, int *select, double *d, double *z, int *ldz, double *sigma, char *bmat, int *n, char *which, int *nev, double *tol, double *resid, int *ncv, double *v, int *ldv, int *iparam, int *ipntr, double *workd, double *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dseupdK(JNIEnv *env, UNUSED jobject obj,
    jboolean rvec, jstring howmny, jbooleanArray select, jint offsetselect, jdoubleArray d, jint offsetd, jdoubleArray z, jint offsetz, jint ldz, jdouble sigma, jstring bmat, jint n, jstring which, jobject nev, jdouble tol, jdoubleArray resid, jint offsetresid, jint ncv, jdoubleArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jdoubleArray workl, jint offsetworkl, jint lworkl, jobject info) {
  // TODO
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sseupd_)(int *rvec, char *howmny, int *select, float *d, float *z, int *ldz, float *sigma, char *bmat, int *n, char *which, int *nev, float *tol, float *resid, int *ncv, float *v, int *ldv, int *iparam, int *ipntr, float *workd, float *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sseupdK(JNIEnv *env, UNUSED jobject obj,
    jboolean rvec, jstring howmny, jbooleanArray select, jint offsetselect, jfloatArray d, jint offsetd, jfloatArray z, jint offsetz, jint ldz, jfloat sigma, jstring bmat, jint n, jstring which, jobject nev, jfloat tol, jfloatArray resid, jint offsetresid, jint ncv, jfloatArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jfloatArray workl, jint offsetworkl, jint lworkl, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsgets_)(int *ishift, char *which, int *kev, int *np, double *ritz, double *bounds, double *shifts);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsgetsK(JNIEnv *env, UNUSED jobject obj,
    jint ishift, jstring which, jobject kev, jobject np, jdoubleArray ritz, jint offsetritz, jdoubleArray bounds, jint offsetbounds, jdoubleArray shifts, jint offsetshifts) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssgets_)(int *ishift, char *which, int *kev, int *np, float *ritz, float *bounds, float *shifts);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssgetsK(JNIEnv *env, UNUSED jobject obj,
    jint ishift, jstring which, jobject kev, jobject np, jfloatArray ritz, jint offsetritz, jfloatArray bounds, jint offsetbounds, jfloatArray shifts, jint offsetshifts) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsortc_)(char *which, int *apply, int *n, double *xreal, double *ximag, double *y);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsortcK(JNIEnv *env, UNUSED jobject obj,
    jstring which, jboolean apply, jint n, jdoubleArray xreal, jint offsetxreal, jdoubleArray ximag, jint offsetximag, jdoubleArray y, jint offsety) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssortc_)(char *which, int *apply, int *n, float *xreal, float *ximag, float *y);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssortcK(JNIEnv *env, UNUSED jobject obj,
    jstring which, jboolean apply, jint n, jfloatArray xreal, jint offsetxreal, jfloatArray ximag, jint offsetximag, jfloatArray y, jint offsety) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsortr_)(char *which, int *apply, int *n, double *x1, double *x2);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsortrK(JNIEnv *env, UNUSED jobject obj,
    jstring which, jboolean apply, jint n, jdoubleArray x1, jint offsetx1, jdoubleArray x2, jint offsetx2) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssortr_)(char *which, int *apply, int *n, float *x1, float *x2);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssortrK(JNIEnv *env, UNUSED jobject obj,
    jstring which, jboolean apply, jint n, jfloatArray x1, jint offsetx1, jfloatArray x2, jint offsetx2) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dstatn_)();

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dstatnK(JNIEnv *env, UNUSED jobject obj) {
  dstatn_();
}

static void (*sstatn_)();

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sstatnK(JNIEnv *env, UNUSED jobject obj) {
  sstatn_();
}

static void (*dstats_)();

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dstatsK(JNIEnv *env, UNUSED jobject obj) {
  dstats_();
}

static void (*sstats_)();

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sstatsK(JNIEnv *env, UNUSED jobject obj) {
  sstats_();
}

static void (*dstqrb_)(int *n, double *d, double *e, double *z, double *work, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dstqrbK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray z, jint offsetz, jdoubleArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*sstqrb_)(int *n, float *d, float *e, float *z, float *work, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sstqrbK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray z, jint offsetz, jfloatArray work, jint offsetwork, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static int (*icnteq_)(int *n, int *array, int *value);

jint Java_dev_ludovic_netlib_arpack_JNIARPACK_icnteqK(JNIEnv *env, UNUSED jobject obj,
    jint n, jintArray array, jint offsetarray, jint value) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
  return 0;
}

static void (*icopy_)(int *n, int *lx, int *incx, int *ly, int *incy);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_icopyK(JNIEnv *env, UNUSED jobject obj,
    jint n, jintArray lx, jint offsetlx, jint incx, jintArray ly, jint offsetly, jint incy) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*iset_)(int *n, int *value, int *array, int *inc);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_isetK(JNIEnv *env, UNUSED jobject obj,
    jint n, jint value, jintArray array, jint offsetarray, jint inc) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*iswap_)(int *n, int *sx, int *incx, int *sy, int *incy);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_iswapK(JNIEnv *env, UNUSED jobject obj,
    jint n, jintArray sx, jint offsetsx, jint incx, jintArray sy, jint offsetsy, jint incy) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*second_)(float *t);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_secondK(JNIEnv *env, UNUSED jobject obj,
    jobject t) {
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
  if (!get_system_property(env, (*env)->NewStringUTF(env, "dev.ludovic.netlib.arpack.nativeLibPath"), NULL, &property_nativeLibPath)) {
    return -1;
  }
  jstring property_nativeLib;
  if (!get_system_property(env, (*env)->NewStringUTF(env, "dev.ludovic.netlib.arpack.nativeLib"), (*env)->NewStringUTF(env, "arpack"), &property_nativeLib)) {
    return -1;
  }

  char arpack_name[1024];
  if (property_nativeLibPath) {
    const char *utf = (*env)->GetStringUTFChars(env, property_nativeLibPath, NULL);
    snprintf(arpack_name, sizeof(arpack_name), "%s", utf);
    (*env)->ReleaseStringUTFChars(env, property_nativeLibPath, utf);
  } else if (property_nativeLib) {
    const char *utf = (*env)->GetStringUTFChars(env, property_nativeLib, NULL);
    snprintf(arpack_name, sizeof(arpack_name), "lib%s.so", utf);
    (*env)->ReleaseStringUTFChars(env, property_nativeLib, utf);
  } else {
    /* either property_nativeLibPath or property_nativeLib should always be non-NULL */
    return -1;
  }

  arpack = dlopen(arpack_name, RTLD_LAZY);
  if (!arpack) {
    return -1;
  }

#define LOAD_SYMBOL(name) \
  name = dlsym(arpack, #name); \
  if (!name) { \
    return -1; \
  }

  LOAD_SYMBOL(dmout_)
  LOAD_SYMBOL(smout_)
  LOAD_SYMBOL(dvout_)
  LOAD_SYMBOL(svout_)
  LOAD_SYMBOL(ivout_)
  LOAD_SYMBOL(dgetv0_)
  LOAD_SYMBOL(sgetv0_)
  // LOAD_SYMBOL(dlaqrb_)
  // LOAD_SYMBOL(slaqrb_)
  LOAD_SYMBOL(dnaitr_)
  LOAD_SYMBOL(snaitr_)
  LOAD_SYMBOL(dnapps_)
  LOAD_SYMBOL(snapps_)
  LOAD_SYMBOL(dnaup2_)
  LOAD_SYMBOL(snaup2_)
  LOAD_SYMBOL(dnaupd_)
  LOAD_SYMBOL(snaupd_)
  LOAD_SYMBOL(dnconv_)
  LOAD_SYMBOL(snconv_)
  LOAD_SYMBOL(dsconv_)
  LOAD_SYMBOL(ssconv_)
  LOAD_SYMBOL(dneigh_)
  LOAD_SYMBOL(sneigh_)
  LOAD_SYMBOL(dneupd_)
  LOAD_SYMBOL(sneupd_)
  LOAD_SYMBOL(dngets_)
  LOAD_SYMBOL(sngets_)
  LOAD_SYMBOL(dsaitr_)
  LOAD_SYMBOL(ssaitr_)
  LOAD_SYMBOL(dsapps_)
  LOAD_SYMBOL(ssapps_)
  LOAD_SYMBOL(dsaup2_)
  LOAD_SYMBOL(ssaup2_)
  LOAD_SYMBOL(dseigt_)
  LOAD_SYMBOL(sseigt_)
  LOAD_SYMBOL(dsesrt_)
  LOAD_SYMBOL(ssesrt_)
  LOAD_SYMBOL(dsaupd_)
  LOAD_SYMBOL(ssaupd_)
  LOAD_SYMBOL(dseupd_)
  LOAD_SYMBOL(sseupd_)
  LOAD_SYMBOL(dsgets_)
  LOAD_SYMBOL(ssgets_)
  LOAD_SYMBOL(dsortc_)
  LOAD_SYMBOL(ssortc_)
  LOAD_SYMBOL(dsortr_)
  LOAD_SYMBOL(ssortr_)
  LOAD_SYMBOL(dstatn_)
  LOAD_SYMBOL(sstatn_)
  LOAD_SYMBOL(dstats_)
  LOAD_SYMBOL(sstats_)
  LOAD_SYMBOL(dstqrb_)
  LOAD_SYMBOL(sstqrb_)
  LOAD_SYMBOL(icnteq_)
  LOAD_SYMBOL(icopy_)
  LOAD_SYMBOL(iset_)
  LOAD_SYMBOL(iswap_)
  LOAD_SYMBOL(second_)

#undef LOAD_SYMBOL

  return JNI_VERSION_1_6;
}

void JNI_OnUnload(UNUSED JavaVM *vm, UNUSED void *reserved) {
  dlclose(arpack);
}
