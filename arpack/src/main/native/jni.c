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

static void (*dmout_)(int *lout, int *m, int *n, double *a, int *lda, int *idigit, const char *ifmt);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dmoutK(JNIEnv *env, UNUSED jobject obj, jint lout, jint m, jint n, jdoubleArray a, jint offseta, jint lda, jint idigit, jstring ifmt) {
  jboolean failed = FALSE;
  const char *__nifmt = NULL; double *__na = NULL;
  if (!(__nifmt = (*env)->GetStringUTFChars(env, ifmt, NULL))) goto fail;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  dmout_(&lout, &m, &n, __na + offseta, &lda, &idigit, __nifmt);
done:
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, failed ? JNI_ABORT : 0);
  if (__nifmt) (*env)->ReleaseStringUTFChars(env, ifmt, __nifmt);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*smout_)(int *lout, int *m, int *n, float *a, int *lda, int *idigit, const char *ifmt);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_smoutK(JNIEnv *env, UNUSED jobject obj, jint lout, jint m, jint n, jfloatArray a, jint offseta, jint lda, jint idigit, jstring ifmt) {
  jboolean failed = FALSE;
  const char *__nifmt = NULL; float *__na = NULL;
  if (!(__nifmt = (*env)->GetStringUTFChars(env, ifmt, NULL))) goto fail;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  smout_(&lout, &m, &n, __na + offseta, &lda, &idigit, __nifmt);
done:
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, failed ? JNI_ABORT : 0);
  if (__nifmt) (*env)->ReleaseStringUTFChars(env, ifmt, __nifmt);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dvout_)(int *lout, int *n, double *sx, int *idigit, const char *ifmt);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dvoutK(JNIEnv *env, UNUSED jobject obj, jint lout, jint n, jdoubleArray sx, jint offsetsx, jint idigit, jstring ifmt) {
  jboolean failed = FALSE;
  const char *__nifmt = NULL; double *__nsx = NULL;
  if (!(__nifmt = (*env)->GetStringUTFChars(env, ifmt, NULL))) goto fail;
  if (!(__nsx = (*env)->GetPrimitiveArrayCritical(env, sx, NULL))) goto fail;
  dvout_(&lout, &n, __nsx + offsetsx, &idigit, __nifmt);
done:
  if (__nsx) (*env)->ReleasePrimitiveArrayCritical(env, sx, __nsx, failed ? JNI_ABORT : 0);
  if (__nifmt) (*env)->ReleaseStringUTFChars(env, ifmt, __nifmt);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*svout_)(int *lout, int *n, float *sx, int *idigit, const char *ifmt);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_svoutK(JNIEnv *env, UNUSED jobject obj, jint lout, jint n, jfloatArray sx, jint offsetsx, jint idigit, jstring ifmt) {
  jboolean failed = FALSE;
  const char *__nifmt = NULL; float *__nsx = NULL;
  if (!(__nifmt = (*env)->GetStringUTFChars(env, ifmt, NULL))) goto fail;
  if (!(__nsx = (*env)->GetPrimitiveArrayCritical(env, sx, NULL))) goto fail;
  svout_(&lout, &n, __nsx + offsetsx, &idigit, __nifmt);
done:
  if (__nsx) (*env)->ReleasePrimitiveArrayCritical(env, sx, __nsx, failed ? JNI_ABORT : 0);
  if (__nifmt) (*env)->ReleaseStringUTFChars(env, ifmt, __nifmt);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*ivout_)(int *lout, int *n, int *ix, int *idigit, const char *ifmt);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ivoutK(JNIEnv *env, UNUSED jobject obj, jint lout, jint n, jintArray ix, jint offsetix, jint idigit, jstring ifmt) {
  jboolean failed = FALSE;
  const char *__nifmt = NULL; int *__nix = NULL;
  if (!(__nifmt = (*env)->GetStringUTFChars(env, ifmt, NULL))) goto fail;
  if (!(__nix = (*env)->GetPrimitiveArrayCritical(env, ix, NULL))) goto fail;
  ivout_(&lout, &n, __nix + offsetix, &idigit, __nifmt);
done:
  if (__nix) (*env)->ReleasePrimitiveArrayCritical(env, ix, __nix, failed ? JNI_ABORT : 0);
  if (__nifmt) (*env)->ReleaseStringUTFChars(env, ifmt, __nifmt);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dgetv0_)(int *ido, const char *bmat, int *itry, int *initv, int *n, int *j, double *v, int *ldv, double *resid, double *rnorm, int *ipntr, double *workd, int *ierr);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dgetv0K(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint itry, jboolean initv, jint n, jint j, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray resid, jint offsetresid, jobject rnorm, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jobject ierr) {
  jboolean failed = FALSE;
  int __nido = 0; const char *__nbmat = NULL; int __ninitv; double __nrnorm = 0; int __nierr = 0; double *__nv = NULL; double *__nresid = NULL; int *__nipntr = NULL; double *__nworkd = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) goto fail;
  __ninitv = initv;
  __nrnorm = (*env)->GetDoubleField(env, rnorm, doubleW_val_fieldID);
  __nierr = (*env)->GetIntField(env, ierr, intW_val_fieldID);
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  dgetv0_(&__nido, __nbmat, &itry, &__ninitv, &n, &j, __nv + offsetv, &ldv, __nresid + offsetresid, &__nrnorm, __nipntr + offsetipntr, __nworkd + offsetworkd, &__nierr);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, ierr, intW_val_fieldID, __nierr);
  if (!failed) (*env)->SetDoubleField(env, rnorm, doubleW_val_fieldID, __nrnorm);
  if (!failed) initv = __ninitv;
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*sgetv0_)(int *ido, const char *bmat, int *itry, int *initv, int *n, int *j, float *v, int *ldv, float *resid, float *rnorm, int *ipntr, float *workd, int *ierr);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sgetv0K(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint itry, jboolean initv, jint n, jint j, jfloatArray v, jint offsetv, jint ldv, jfloatArray resid, jint offsetresid, jobject rnorm, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jobject ierr) {
  jboolean failed = FALSE;
  int __nido = 0; const char *__nbmat = NULL; int __ninitv; float __nrnorm = 0; int __nierr = 0; float *__nv = NULL; float *__nresid = NULL; int *__nipntr = NULL; float *__nworkd = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) goto fail;
  __ninitv = initv;
  __nrnorm = (*env)->GetFloatField(env, rnorm, floatW_val_fieldID);
  __nierr = (*env)->GetIntField(env, ierr, intW_val_fieldID);
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  sgetv0_(&__nido, __nbmat, &itry, &__ninitv, &n, &j, __nv + offsetv, &ldv, __nresid + offsetresid, &__nrnorm, __nipntr + offsetipntr, __nworkd + offsetworkd, &__nierr);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, ierr, intW_val_fieldID, __nierr);
  if (!failed) (*env)->SetFloatField(env, rnorm, floatW_val_fieldID, __nrnorm);
  if (!failed) initv = __ninitv;
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dnaitr_)(int *ido, const char *bmat, int *n, int *k, int *np, int *nb, double *resid, double *rnorm, double *v, int *ldv, double *h, int *ldh, int *ipntr, double *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dnaitrK(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jint k, jint np, jint nb, jdoubleArray resid, jint offsetresid, jobject rnorm, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray h, jint offseth, jint ldh, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jobject info) {
  jboolean failed = FALSE;
  int __nido = 0; const char *__nbmat = NULL; double __nrnorm = 0; int __ninfo = 0; double *__nresid = NULL; double *__nv = NULL; double *__nh = NULL; int *__nipntr = NULL; double *__nworkd = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) goto fail;
  __nrnorm = (*env)->GetDoubleField(env, rnorm, doubleW_val_fieldID);
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) goto fail;
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  dnaitr_(&__nido, __nbmat, &n, &k, &np, &nb, __nresid + offsetresid, &__nrnorm, __nv + offsetv, &ldv, __nh + offseth, &ldh, __nipntr + offsetipntr, __nworkd + offsetworkd, &__ninfo);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!failed) (*env)->SetDoubleField(env, rnorm, doubleW_val_fieldID, __nrnorm);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*snaitr_)(int *ido, const char *bmat, int *n, int *k, int *np, int *nb, float *resid, float *rnorm, float *v, int *ldv, float *h, int *ldh, int *ipntr, float *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_snaitrK(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jint k, jint np, jint nb, jfloatArray resid, jint offsetresid, jobject rnorm, jfloatArray v, jint offsetv, jint ldv, jfloatArray h, jint offseth, jint ldh, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jobject info) {
  jboolean failed = FALSE;
  int __nido = 0; const char *__nbmat = NULL; float __nrnorm = 0; int __ninfo = 0; float *__nresid = NULL; float *__nv = NULL; float *__nh = NULL; int *__nipntr = NULL; float *__nworkd = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) goto fail;
  __nrnorm = (*env)->GetFloatField(env, rnorm, floatW_val_fieldID);
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) goto fail;
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  snaitr_(&__nido, __nbmat, &n, &k, &np, &nb, __nresid + offsetresid, &__nrnorm, __nv + offsetv, &ldv, __nh + offseth, &ldh, __nipntr + offsetipntr, __nworkd + offsetworkd, &__ninfo);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!failed) (*env)->SetFloatField(env, rnorm, floatW_val_fieldID, __nrnorm);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dnapps_)(int *n, int *kev, int *np, double *shiftr, double *shifti, double *v, int *ldv, double *h, int *ldh, double *resid, double *q, int *ldq, double *workl, double *workd);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dnappsK(JNIEnv *env, UNUSED jobject obj, jint n, jobject kev, jint np, jdoubleArray shiftr, jint offsetshiftr, jdoubleArray shifti, jint offsetshifti, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray h, jint offseth, jint ldh, jdoubleArray resid, jint offsetresid, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray workl, jint offsetworkl, jdoubleArray workd, jint offsetworkd) {
  jboolean failed = FALSE;
  int __nkev = 0; double *__nshiftr = NULL; double *__nshifti = NULL; double *__nv = NULL; double *__nh = NULL; double *__nresid = NULL; double *__nq = NULL; double *__nworkl = NULL; double *__nworkd = NULL;
  __nkev = (*env)->GetIntField(env, kev, intW_val_fieldID);
  if (!(__nshiftr = (*env)->GetPrimitiveArrayCritical(env, shiftr, NULL))) goto fail;
  if (!(__nshifti = (*env)->GetPrimitiveArrayCritical(env, shifti, NULL))) goto fail;
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) goto fail;
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nq = (*env)->GetPrimitiveArrayCritical(env, q, NULL))) goto fail;
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  dnapps_(&n, &__nkev, &np, __nshiftr + offsetshiftr, __nshifti + offsetshifti, __nv + offsetv, &ldv, __nh + offseth, &ldh, __nresid + offsetresid, __nq + offsetq, &ldq, __nworkl + offsetworkl, __nworkd + offsetworkd);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, failed ? JNI_ABORT : 0);
  if (__nq) (*env)->ReleasePrimitiveArrayCritical(env, q, __nq, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (__nshifti) (*env)->ReleasePrimitiveArrayCritical(env, shifti, __nshifti, failed ? JNI_ABORT : 0);
  if (__nshiftr) (*env)->ReleasePrimitiveArrayCritical(env, shiftr, __nshiftr, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, kev, intW_val_fieldID, __nkev);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*snapps_)(int *n, int *kev, int *np, float *shiftr, float *shifti, float *v, int *ldv, float *h, int *ldh, float *resid, float *q, int *ldq, float *workl, float *workd);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_snappsK(JNIEnv *env, UNUSED jobject obj, jint n, jobject kev, jint np, jfloatArray shiftr, jint offsetshiftr, jfloatArray shifti, jint offsetshifti, jfloatArray v, jint offsetv, jint ldv, jfloatArray h, jint offseth, jint ldh, jfloatArray resid, jint offsetresid, jfloatArray q, jint offsetq, jint ldq, jfloatArray workl, jint offsetworkl, jfloatArray workd, jint offsetworkd) {
  jboolean failed = FALSE;
  int __nkev = 0; float *__nshiftr = NULL; float *__nshifti = NULL; float *__nv = NULL; float *__nh = NULL; float *__nresid = NULL; float *__nq = NULL; float *__nworkl = NULL; float *__nworkd = NULL;
  __nkev = (*env)->GetIntField(env, kev, intW_val_fieldID);
  if (!(__nshiftr = (*env)->GetPrimitiveArrayCritical(env, shiftr, NULL))) goto fail;
  if (!(__nshifti = (*env)->GetPrimitiveArrayCritical(env, shifti, NULL))) goto fail;
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) goto fail;
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nq = (*env)->GetPrimitiveArrayCritical(env, q, NULL))) goto fail;
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  snapps_(&n, &__nkev, &np, __nshiftr + offsetshiftr, __nshifti + offsetshifti, __nv + offsetv, &ldv, __nh + offseth, &ldh, __nresid + offsetresid, __nq + offsetq, &ldq, __nworkl + offsetworkl, __nworkd + offsetworkd);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, failed ? JNI_ABORT : 0);
  if (__nq) (*env)->ReleasePrimitiveArrayCritical(env, q, __nq, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (__nshifti) (*env)->ReleasePrimitiveArrayCritical(env, shifti, __nshifti, failed ? JNI_ABORT : 0);
  if (__nshiftr) (*env)->ReleasePrimitiveArrayCritical(env, shiftr, __nshiftr, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, kev, intW_val_fieldID, __nkev);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dnaup2_)(int *ido, const char *bmat, int *n, const char *which, int *nev, int *np, double *tol, double *resid, int *mode, int *iupd, int *ishift, int *mxiter, double *v, int *ldv, double *h, int *ldh, double *ritzr, double *ritzi, double *bounds, double *q, int *ldq, double *workl, int *ipntr, double *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dnaup2K(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jstring which, jobject nev, jobject np, jdouble tol, jdoubleArray resid, jint offsetresid, jint mode, jint iupd, jint ishift, jobject mxiter, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray h, jint offseth, jint ldh, jdoubleArray ritzr, jint offsetritzr, jdoubleArray ritzi, jint offsetritzi, jdoubleArray bounds, jint offsetbounds, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray workl, jint offsetworkl, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jobject info) {
  jboolean failed = FALSE;
  int __nido = 0; const char *__nbmat = NULL; const char *__nwhich = NULL; int __nnev = 0; int __nnp = 0; int __nmxiter = 0; int __ninfo = 0; double *__nresid = NULL; double *__nv = NULL; double *__nh = NULL; double *__nritzr = NULL; double *__nritzi = NULL; double *__nbounds = NULL; double *__nq = NULL; double *__nworkl = NULL; int *__nipntr = NULL; double *__nworkd = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) goto fail;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __nnev = (*env)->GetIntField(env, nev, intW_val_fieldID);
  __nnp = (*env)->GetIntField(env, np, intW_val_fieldID);
  __nmxiter = (*env)->GetIntField(env, mxiter, intW_val_fieldID);
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) goto fail;
  if (!(__nritzr = (*env)->GetPrimitiveArrayCritical(env, ritzr, NULL))) goto fail;
  if (!(__nritzi = (*env)->GetPrimitiveArrayCritical(env, ritzi, NULL))) goto fail;
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) goto fail;
  if (!(__nq = (*env)->GetPrimitiveArrayCritical(env, q, NULL))) goto fail;
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) goto fail;
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  dnaup2_(&__nido, __nbmat, &n, __nwhich, &__nnev, &__nnp, &tol, __nresid + offsetresid, &mode, &iupd, &ishift, &__nmxiter, __nv + offsetv, &ldv, __nh + offseth, &ldh, __nritzr + offsetritzr, __nritzi + offsetritzi, __nbounds + offsetbounds, __nq + offsetq, &ldq, __nworkl + offsetworkl, __nipntr + offsetipntr, __nworkd + offsetworkd, &__ninfo);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, failed ? JNI_ABORT : 0);
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, failed ? JNI_ABORT : 0);
  if (__nq) (*env)->ReleasePrimitiveArrayCritical(env, q, __nq, failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, failed ? JNI_ABORT : 0);
  if (__nritzi) (*env)->ReleasePrimitiveArrayCritical(env, ritzi, __nritzi, failed ? JNI_ABORT : 0);
  if (__nritzr) (*env)->ReleasePrimitiveArrayCritical(env, ritzr, __nritzr, failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!failed) (*env)->SetIntField(env, mxiter, intW_val_fieldID, __nmxiter);
  if (!failed) (*env)->SetIntField(env, np, intW_val_fieldID, __nnp);
  if (!failed) (*env)->SetIntField(env, nev, intW_val_fieldID, __nnev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*snaup2_)(int *ido, const char *bmat, int *n, const char *which, int *nev, int *np, float *tol, float *resid, int *mode, int *iupd, int *ishift, int *mxiter, float *v, int *ldv, float *h, int *ldh, float *ritzr, float *ritzi, float *bounds, float *q, int *ldq, float *workl, int *ipntr, float *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_snaup2K(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jstring which, jobject nev, jobject np, jfloat tol, jfloatArray resid, jint offsetresid, jint mode, jint iupd, jint ishift, jobject mxiter, jfloatArray v, jint offsetv, jint ldv, jfloatArray h, jint offseth, jint ldh, jfloatArray ritzr, jint offsetritzr, jfloatArray ritzi, jint offsetritzi, jfloatArray bounds, jint offsetbounds, jfloatArray q, jint offsetq, jint ldq, jfloatArray workl, jint offsetworkl, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jobject info) {
  jboolean failed = FALSE;
  int __nido = 0; const char *__nbmat = NULL; const char *__nwhich = NULL; int __nnev = 0; int __nnp = 0; int __nmxiter = 0; int __ninfo = 0; float *__nresid = NULL; float *__nv = NULL; float *__nh = NULL; float *__nritzr = NULL; float *__nritzi = NULL; float *__nbounds = NULL; float *__nq = NULL; float *__nworkl = NULL; int *__nipntr = NULL; float *__nworkd = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) goto fail;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __nnev = (*env)->GetIntField(env, nev, intW_val_fieldID);
  __nnp = (*env)->GetIntField(env, np, intW_val_fieldID);
  __nmxiter = (*env)->GetIntField(env, mxiter, intW_val_fieldID);
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) goto fail;
  if (!(__nritzr = (*env)->GetPrimitiveArrayCritical(env, ritzr, NULL))) goto fail;
  if (!(__nritzi = (*env)->GetPrimitiveArrayCritical(env, ritzi, NULL))) goto fail;
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) goto fail;
  if (!(__nq = (*env)->GetPrimitiveArrayCritical(env, q, NULL))) goto fail;
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) goto fail;
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  snaup2_(&__nido, __nbmat, &n, __nwhich, &__nnev, &__nnp, &tol, __nresid + offsetresid, &mode, &iupd, &ishift, &__nmxiter, __nv + offsetv, &ldv, __nh + offseth, &ldh, __nritzr + offsetritzr, __nritzi + offsetritzi, __nbounds + offsetbounds, __nq + offsetq, &ldq, __nworkl + offsetworkl, __nipntr + offsetipntr, __nworkd + offsetworkd, &__ninfo);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, failed ? JNI_ABORT : 0);
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, failed ? JNI_ABORT : 0);
  if (__nq) (*env)->ReleasePrimitiveArrayCritical(env, q, __nq, failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, failed ? JNI_ABORT : 0);
  if (__nritzi) (*env)->ReleasePrimitiveArrayCritical(env, ritzi, __nritzi, failed ? JNI_ABORT : 0);
  if (__nritzr) (*env)->ReleasePrimitiveArrayCritical(env, ritzr, __nritzr, failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!failed) (*env)->SetIntField(env, mxiter, intW_val_fieldID, __nmxiter);
  if (!failed) (*env)->SetIntField(env, np, intW_val_fieldID, __nnp);
  if (!failed) (*env)->SetIntField(env, nev, intW_val_fieldID, __nnev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dnaupd_)(int *ido, const char *bmat, int *n, const char *which, int *nev, double *tol, double *resid, int *ncv, double *v, int *ldv, int *iparam, int *ipntr, double *workd, double *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dnaupdK(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jstring which, jint nev, jobject tol, jdoubleArray resid, jint offsetresid, jint ncv, jdoubleArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jdoubleArray workl, jint offsetworkl, jint lworkl, jobject info) {
  jboolean failed = FALSE;
  int __nido = 0; const char *__nbmat = NULL; const char *__nwhich = NULL; double __ntol = 0; int __ninfo = 0; double *__nresid = NULL; double *__nv = NULL; int *__niparam = NULL; int *__nipntr = NULL; double *__nworkd = NULL; double *__nworkl = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) goto fail;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __ntol = (*env)->GetDoubleField(env, tol, doubleW_val_fieldID);
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__niparam = (*env)->GetPrimitiveArrayCritical(env, iparam, NULL))) goto fail;
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) goto fail;
  dnaupd_(&__nido, __nbmat, &n, __nwhich, &nev, &__ntol, __nresid + offsetresid, &ncv, __nv + offsetv, &ldv, __niparam + offsetiparam, __nipntr + offsetipntr, __nworkd + offsetworkd, __nworkl + offsetworkl, &lworkl, &__ninfo);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, failed ? JNI_ABORT : 0);
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, failed ? JNI_ABORT : 0);
  if (__niparam) (*env)->ReleasePrimitiveArrayCritical(env, iparam, __niparam, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!failed) (*env)->SetDoubleField(env, tol, doubleW_val_fieldID, __ntol);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*snaupd_)(int *ido, const char *bmat, int *n, const char *which, int *nev, float *tol, float *resid, int *ncv, float *v, int *ldv, int *iparam, int *ipntr, float *workd, float *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_snaupdK(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jstring which, jint nev, jobject tol, jfloatArray resid, jint offsetresid, jint ncv, jfloatArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jfloatArray workl, jint offsetworkl, jint lworkl, jobject info) {
  jboolean failed = FALSE;
  int __nido = 0; const char *__nbmat = NULL; const char *__nwhich = NULL; float __ntol = 0; int __ninfo = 0; float *__nresid = NULL; float *__nv = NULL; int *__niparam = NULL; int *__nipntr = NULL; float *__nworkd = NULL; float *__nworkl = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) goto fail;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __ntol = (*env)->GetFloatField(env, tol, floatW_val_fieldID);
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__niparam = (*env)->GetPrimitiveArrayCritical(env, iparam, NULL))) goto fail;
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) goto fail;
  snaupd_(&__nido, __nbmat, &n, __nwhich, &nev, &__ntol, __nresid + offsetresid, &ncv, __nv + offsetv, &ldv, __niparam + offsetiparam, __nipntr + offsetipntr, __nworkd + offsetworkd, __nworkl + offsetworkl, &lworkl, &__ninfo);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, failed ? JNI_ABORT : 0);
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, failed ? JNI_ABORT : 0);
  if (__niparam) (*env)->ReleasePrimitiveArrayCritical(env, iparam, __niparam, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!failed) (*env)->SetFloatField(env, tol, floatW_val_fieldID, __ntol);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dnconv_)(int *n, double *ritzr, double *ritzi, double *bounds, double *tol, int *nconv);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dnconvK(JNIEnv *env, UNUSED jobject obj, jint n, jdoubleArray ritzr, jint offsetritzr, jdoubleArray ritzi, jint offsetritzi, jdoubleArray bounds, jint offsetbounds, jdouble tol, jobject nconv) {
  jboolean failed = FALSE;
  int __nnconv = 0; double *__nritzr = NULL; double *__nritzi = NULL; double *__nbounds = NULL;
  __nnconv = (*env)->GetIntField(env, nconv, intW_val_fieldID);
  if (!(__nritzr = (*env)->GetPrimitiveArrayCritical(env, ritzr, NULL))) goto fail;
  if (!(__nritzi = (*env)->GetPrimitiveArrayCritical(env, ritzi, NULL))) goto fail;
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) goto fail;
  dnconv_(&n, __nritzr + offsetritzr, __nritzi + offsetritzi, __nbounds + offsetbounds, &tol, &__nnconv);
done:
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, failed ? JNI_ABORT : 0);
  if (__nritzi) (*env)->ReleasePrimitiveArrayCritical(env, ritzi, __nritzi, failed ? JNI_ABORT : 0);
  if (__nritzr) (*env)->ReleasePrimitiveArrayCritical(env, ritzr, __nritzr, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, nconv, intW_val_fieldID, __nnconv);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*snconv_)(int *n, float *ritzr, float *ritzi, float *bounds, float *tol, int *nconv);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_snconvK(JNIEnv *env, UNUSED jobject obj, jint n, jfloatArray ritzr, jint offsetritzr, jfloatArray ritzi, jint offsetritzi, jfloatArray bounds, jint offsetbounds, jfloat tol, jobject nconv) {
  jboolean failed = FALSE;
  int __nnconv = 0; float *__nritzr = NULL; float *__nritzi = NULL; float *__nbounds = NULL;
  __nnconv = (*env)->GetIntField(env, nconv, intW_val_fieldID);
  if (!(__nritzr = (*env)->GetPrimitiveArrayCritical(env, ritzr, NULL))) goto fail;
  if (!(__nritzi = (*env)->GetPrimitiveArrayCritical(env, ritzi, NULL))) goto fail;
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) goto fail;
  snconv_(&n, __nritzr + offsetritzr, __nritzi + offsetritzi, __nbounds + offsetbounds, &tol, &__nnconv);
done:
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, failed ? JNI_ABORT : 0);
  if (__nritzi) (*env)->ReleasePrimitiveArrayCritical(env, ritzi, __nritzi, failed ? JNI_ABORT : 0);
  if (__nritzr) (*env)->ReleasePrimitiveArrayCritical(env, ritzr, __nritzr, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, nconv, intW_val_fieldID, __nnconv);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dsconv_)(int *n, double *ritz, double *bounds, double *tol, int *nconv);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsconvK(JNIEnv *env, UNUSED jobject obj, jint n, jdoubleArray ritz, jint offsetritz, jdoubleArray bounds, jint offsetbounds, jdouble tol, jobject nconv) {
  jboolean failed = FALSE;
  int __nnconv = 0; double *__nritz = NULL; double *__nbounds = NULL;
  __nnconv = (*env)->GetIntField(env, nconv, intW_val_fieldID);
  if (!(__nritz = (*env)->GetPrimitiveArrayCritical(env, ritz, NULL))) goto fail;
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) goto fail;
  dsconv_(&n, __nritz + offsetritz, __nbounds + offsetbounds, &tol, &__nnconv);
done:
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, failed ? JNI_ABORT : 0);
  if (__nritz) (*env)->ReleasePrimitiveArrayCritical(env, ritz, __nritz, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, nconv, intW_val_fieldID, __nnconv);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*ssconv_)(int *n, float *ritz, float *bounds, float *tol, int *nconv);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssconvK(JNIEnv *env, UNUSED jobject obj, jint n, jfloatArray ritz, jint offsetritz, jfloatArray bounds, jint offsetbounds, jfloat tol, jobject nconv) {
  jboolean failed = FALSE;
  int __nnconv = 0; float *__nritz = NULL; float *__nbounds = NULL;
  __nnconv = (*env)->GetIntField(env, nconv, intW_val_fieldID);
  if (!(__nritz = (*env)->GetPrimitiveArrayCritical(env, ritz, NULL))) goto fail;
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) goto fail;
  ssconv_(&n, __nritz + offsetritz, __nbounds + offsetbounds, &tol, &__nnconv);
done:
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, failed ? JNI_ABORT : 0);
  if (__nritz) (*env)->ReleasePrimitiveArrayCritical(env, ritz, __nritz, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, nconv, intW_val_fieldID, __nnconv);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dneigh_)(double *rnorm, int *n, double *h, int *ldh, double *ritzr, double *ritzi, double *bounds, double *q, int *ldq, double *workl, int *ierr);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dneighK(JNIEnv *env, UNUSED jobject obj, jdouble rnorm, jobject n, jdoubleArray h, jint offseth, jint ldh, jdoubleArray ritzr, jint offsetritzr, jdoubleArray ritzi, jint offsetritzi, jdoubleArray bounds, jint offsetbounds, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray workl, jint offsetworkl, jobject ierr) {
  jboolean failed = FALSE;
  int __nn = 0; int __nierr = 0; double *__nh = NULL; double *__nritzr = NULL; double *__nritzi = NULL; double *__nbounds = NULL; double *__nq = NULL; double *__nworkl = NULL;
  __nn = (*env)->GetIntField(env, n, intW_val_fieldID);
  __nierr = (*env)->GetIntField(env, ierr, intW_val_fieldID);
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) goto fail;
  if (!(__nritzr = (*env)->GetPrimitiveArrayCritical(env, ritzr, NULL))) goto fail;
  if (!(__nritzi = (*env)->GetPrimitiveArrayCritical(env, ritzi, NULL))) goto fail;
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) goto fail;
  if (!(__nq = (*env)->GetPrimitiveArrayCritical(env, q, NULL))) goto fail;
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) goto fail;
  dneigh_(&rnorm, &__nn, __nh + offseth, &ldh, __nritzr + offsetritzr, __nritzi + offsetritzi, __nbounds + offsetbounds, __nq + offsetq, &ldq, __nworkl + offsetworkl, &__nierr);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, failed ? JNI_ABORT : 0);
  if (__nq) (*env)->ReleasePrimitiveArrayCritical(env, q, __nq, failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, failed ? JNI_ABORT : 0);
  if (__nritzi) (*env)->ReleasePrimitiveArrayCritical(env, ritzi, __nritzi, failed ? JNI_ABORT : 0);
  if (__nritzr) (*env)->ReleasePrimitiveArrayCritical(env, ritzr, __nritzr, failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, ierr, intW_val_fieldID, __nierr);
  if (!failed) (*env)->SetIntField(env, n, intW_val_fieldID, __nn);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*sneigh_)(float *rnorm, int *n, float *h, int *ldh, float *ritzr, float *ritzi, float *bounds, float *q, int *ldq, float *workl, int *ierr);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sneighK(JNIEnv *env, UNUSED jobject obj, jfloat rnorm, jobject n, jfloatArray h, jint offseth, jint ldh, jfloatArray ritzr, jint offsetritzr, jfloatArray ritzi, jint offsetritzi, jfloatArray bounds, jint offsetbounds, jfloatArray q, jint offsetq, jint ldq, jfloatArray workl, jint offsetworkl, jobject ierr) {
  jboolean failed = FALSE;
  int __nn = 0; int __nierr = 0; float *__nh = NULL; float *__nritzr = NULL; float *__nritzi = NULL; float *__nbounds = NULL; float *__nq = NULL; float *__nworkl = NULL;
  __nn = (*env)->GetIntField(env, n, intW_val_fieldID);
  __nierr = (*env)->GetIntField(env, ierr, intW_val_fieldID);
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) goto fail;
  if (!(__nritzr = (*env)->GetPrimitiveArrayCritical(env, ritzr, NULL))) goto fail;
  if (!(__nritzi = (*env)->GetPrimitiveArrayCritical(env, ritzi, NULL))) goto fail;
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) goto fail;
  if (!(__nq = (*env)->GetPrimitiveArrayCritical(env, q, NULL))) goto fail;
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) goto fail;
  sneigh_(&rnorm, &__nn, __nh + offseth, &ldh, __nritzr + offsetritzr, __nritzi + offsetritzi, __nbounds + offsetbounds, __nq + offsetq, &ldq, __nworkl + offsetworkl, &__nierr);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, failed ? JNI_ABORT : 0);
  if (__nq) (*env)->ReleasePrimitiveArrayCritical(env, q, __nq, failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, failed ? JNI_ABORT : 0);
  if (__nritzi) (*env)->ReleasePrimitiveArrayCritical(env, ritzi, __nritzi, failed ? JNI_ABORT : 0);
  if (__nritzr) (*env)->ReleasePrimitiveArrayCritical(env, ritzr, __nritzr, failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, ierr, intW_val_fieldID, __nierr);
  if (!failed) (*env)->SetIntField(env, n, intW_val_fieldID, __nn);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dneupd_)(int *rvec, const char *howmny, int *select, double *dr, double *di, double *z, int *ldz, double *sigmar, double *sigmai, double *workev, const char *bmat, int *n, const char *which, int *nev, double *tol, double *resid, int *ncv, double *v, int *ldv, int *iparam, int *ipntr, double *workd, double *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dneupdK(JNIEnv *env, UNUSED jobject obj, jboolean rvec, jstring howmny, jbooleanArray select, jint offsetselect, jdoubleArray dr, jint offsetdr, jdoubleArray di, jint offsetdi, jdoubleArray z, jint offsetz, jint ldz, jdouble sigmar, jdouble sigmai, jdoubleArray workev, jint offsetworkev, jstring bmat, jint n, jstring which, jobject nev, jdouble tol, jdoubleArray resid, jint offsetresid, jint ncv, jdoubleArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jdoubleArray workl, jint offsetworkl, jint lworkl, jobject info) {
  jboolean failed = FALSE;
  int __nrvec; const char *__nhowmny = NULL; const char *__nbmat = NULL; const char *__nwhich = NULL; int __nnev = 0; int __ninfo = 0; jboolean *__jselect = NULL; int *__nselect = NULL; double *__ndr = NULL; double *__ndi = NULL; double *__nz = NULL; double *__nworkev = NULL; double *__nresid = NULL; double *__nv = NULL; int *__niparam = NULL; int *__nipntr = NULL; double *__nworkd = NULL; double *__nworkl = NULL;
  __nrvec = rvec;
  if (!(__nhowmny = (*env)->GetStringUTFChars(env, howmny, NULL))) goto fail;
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) goto fail;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __nnev = (*env)->GetIntField(env, nev, intW_val_fieldID);
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__jselect = (*env)->GetPrimitiveArrayCritical(env, select, NULL))) { goto fail; } do { int length = (*env)->GetArrayLength(env, select); if (length <= 0) goto fail; if (!(__nselect = malloc(sizeof(jboolean) * length))) goto fail; for (int i = 0; i < length; i++) { __nselect[i] = __jselect[i]; } } while(0);
  if (!(__ndr = (*env)->GetPrimitiveArrayCritical(env, dr, NULL))) goto fail;
  if (!(__ndi = (*env)->GetPrimitiveArrayCritical(env, di, NULL))) goto fail;
  if (!(__nz = (*env)->GetPrimitiveArrayCritical(env, z, NULL))) goto fail;
  if (!(__nworkev = (*env)->GetPrimitiveArrayCritical(env, workev, NULL))) goto fail;
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__niparam = (*env)->GetPrimitiveArrayCritical(env, iparam, NULL))) goto fail;
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) goto fail;
  dneupd_(&__nrvec, __nhowmny, __nselect + offsetselect, __ndr + offsetdr, __ndi + offsetdi, __nz + offsetz, &ldz, &sigmar, &sigmai, __nworkev + offsetworkev, __nbmat, &n, __nwhich, &__nnev, &tol, __nresid + offsetresid, &ncv, __nv + offsetv, &ldv, __niparam + offsetiparam, __nipntr + offsetipntr, __nworkd + offsetworkd, __nworkl + offsetworkl, &lworkl, &__ninfo);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, failed ? JNI_ABORT : 0);
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, failed ? JNI_ABORT : 0);
  if (__niparam) (*env)->ReleasePrimitiveArrayCritical(env, iparam, __niparam, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (__nworkev) (*env)->ReleasePrimitiveArrayCritical(env, workev, __nworkev, failed ? JNI_ABORT : 0);
  if (__nz) (*env)->ReleasePrimitiveArrayCritical(env, z, __nz, failed ? JNI_ABORT : 0);
  if (__ndi) (*env)->ReleasePrimitiveArrayCritical(env, di, __ndi, failed ? JNI_ABORT : 0);
  if (__ndr) (*env)->ReleasePrimitiveArrayCritical(env, dr, __ndr, failed ? JNI_ABORT : 0);
  if (__nselect) { free(__nselect); } if (__jselect) (*env)->ReleasePrimitiveArrayCritical(env, select, __nselect, JNI_ABORT);
  if (!failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!failed) (*env)->SetIntField(env, nev, intW_val_fieldID, __nnev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (__nhowmny) (*env)->ReleaseStringUTFChars(env, howmny, __nhowmny);
  if (!failed) rvec = __nrvec;
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*sneupd_)(int *rvec, const char *howmny, int *select, float *dr, float *di, float *z, int *ldz, float *sigmar, float *sigmai, float *workev, const char *bmat, int *n, const char *which, int *nev, float *tol, float *resid, int *ncv, float *v, int *ldv, int *iparam, int *ipntr, float *workd, float *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sneupdK(JNIEnv *env, UNUSED jobject obj, jboolean rvec, jstring howmny, jbooleanArray select, jint offsetselect, jfloatArray dr, jint offsetdr, jfloatArray di, jint offsetdi, jfloatArray z, jint offsetz, jint ldz, jfloat sigmar, jfloat sigmai, jfloatArray workev, jint offsetworkev, jstring bmat, jint n, jstring which, jobject nev, jfloat tol, jfloatArray resid, jint offsetresid, jint ncv, jfloatArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jfloatArray workl, jint offsetworkl, jint lworkl, jobject info) {
  jboolean failed = FALSE;
  int __nrvec; const char *__nhowmny = NULL; const char *__nbmat = NULL; const char *__nwhich = NULL; int __nnev = 0; int __ninfo = 0; jboolean *__jselect = NULL; int *__nselect = NULL; float *__ndr = NULL; float *__ndi = NULL; float *__nz = NULL; float *__nworkev = NULL; float *__nresid = NULL; float *__nv = NULL; int *__niparam = NULL; int *__nipntr = NULL; float *__nworkd = NULL; float *__nworkl = NULL;
  __nrvec = rvec;
  if (!(__nhowmny = (*env)->GetStringUTFChars(env, howmny, NULL))) goto fail;
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) goto fail;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __nnev = (*env)->GetIntField(env, nev, intW_val_fieldID);
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__jselect = (*env)->GetPrimitiveArrayCritical(env, select, NULL))) { goto fail; } do { int length = (*env)->GetArrayLength(env, select); if (length <= 0) goto fail; if (!(__nselect = malloc(sizeof(jboolean) * length))) goto fail; for (int i = 0; i < length; i++) { __nselect[i] = __jselect[i]; } } while(0);
  if (!(__ndr = (*env)->GetPrimitiveArrayCritical(env, dr, NULL))) goto fail;
  if (!(__ndi = (*env)->GetPrimitiveArrayCritical(env, di, NULL))) goto fail;
  if (!(__nz = (*env)->GetPrimitiveArrayCritical(env, z, NULL))) goto fail;
  if (!(__nworkev = (*env)->GetPrimitiveArrayCritical(env, workev, NULL))) goto fail;
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__niparam = (*env)->GetPrimitiveArrayCritical(env, iparam, NULL))) goto fail;
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) goto fail;
  sneupd_(&__nrvec, __nhowmny, __nselect + offsetselect, __ndr + offsetdr, __ndi + offsetdi, __nz + offsetz, &ldz, &sigmar, &sigmai, __nworkev + offsetworkev, __nbmat, &n, __nwhich, &__nnev, &tol, __nresid + offsetresid, &ncv, __nv + offsetv, &ldv, __niparam + offsetiparam, __nipntr + offsetipntr, __nworkd + offsetworkd, __nworkl + offsetworkl, &lworkl, &__ninfo);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, failed ? JNI_ABORT : 0);
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, failed ? JNI_ABORT : 0);
  if (__niparam) (*env)->ReleasePrimitiveArrayCritical(env, iparam, __niparam, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (__nworkev) (*env)->ReleasePrimitiveArrayCritical(env, workev, __nworkev, failed ? JNI_ABORT : 0);
  if (__nz) (*env)->ReleasePrimitiveArrayCritical(env, z, __nz, failed ? JNI_ABORT : 0);
  if (__ndi) (*env)->ReleasePrimitiveArrayCritical(env, di, __ndi, failed ? JNI_ABORT : 0);
  if (__ndr) (*env)->ReleasePrimitiveArrayCritical(env, dr, __ndr, failed ? JNI_ABORT : 0);
  if (__nselect) { free(__nselect); } if (__jselect) (*env)->ReleasePrimitiveArrayCritical(env, select, __nselect, JNI_ABORT);
  if (!failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!failed) (*env)->SetIntField(env, nev, intW_val_fieldID, __nnev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (__nhowmny) (*env)->ReleaseStringUTFChars(env, howmny, __nhowmny);
  if (!failed) rvec = __nrvec;
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dngets_)(int *ishift, const char *which, int *kev, int *np, double *ritzr, double *ritzi, double *bounds, double *shiftr, double *shifti);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dngetsK(JNIEnv *env, UNUSED jobject obj, jint ishift, jstring which, jobject kev, jobject np, jdoubleArray ritzr, jint offsetritzr, jdoubleArray ritzi, jint offsetritzi, jdoubleArray bounds, jint offsetbounds, jdoubleArray shiftr, jint offsetshiftr, jdoubleArray shifti, jint offsetshifti) {
  jboolean failed = FALSE;
  const char *__nwhich = NULL; int __nkev = 0; int __nnp = 0; double *__nritzr = NULL; double *__nritzi = NULL; double *__nbounds = NULL; double *__nshiftr = NULL; double *__nshifti = NULL;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __nkev = (*env)->GetIntField(env, kev, intW_val_fieldID);
  __nnp = (*env)->GetIntField(env, np, intW_val_fieldID);
  if (!(__nritzr = (*env)->GetPrimitiveArrayCritical(env, ritzr, NULL))) goto fail;
  if (!(__nritzi = (*env)->GetPrimitiveArrayCritical(env, ritzi, NULL))) goto fail;
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) goto fail;
  if (!(__nshiftr = (*env)->GetPrimitiveArrayCritical(env, shiftr, NULL))) goto fail;
  if (!(__nshifti = (*env)->GetPrimitiveArrayCritical(env, shifti, NULL))) goto fail;
  dngets_(&ishift, __nwhich, &__nkev, &__nnp, __nritzr + offsetritzr, __nritzi + offsetritzi, __nbounds + offsetbounds, __nshiftr + offsetshiftr, __nshifti + offsetshifti);
done:
  if (__nshifti) (*env)->ReleasePrimitiveArrayCritical(env, shifti, __nshifti, failed ? JNI_ABORT : 0);
  if (__nshiftr) (*env)->ReleasePrimitiveArrayCritical(env, shiftr, __nshiftr, failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, failed ? JNI_ABORT : 0);
  if (__nritzi) (*env)->ReleasePrimitiveArrayCritical(env, ritzi, __nritzi, failed ? JNI_ABORT : 0);
  if (__nritzr) (*env)->ReleasePrimitiveArrayCritical(env, ritzr, __nritzr, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, np, intW_val_fieldID, __nnp);
  if (!failed) (*env)->SetIntField(env, kev, intW_val_fieldID, __nkev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*sngets_)(int *ishift, const char *which, int *kev, int *np, float *ritzr, float *ritzi, float *bounds, float *shiftr, float *shifti);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sngetsK(JNIEnv *env, UNUSED jobject obj, jint ishift, jstring which, jobject kev, jobject np, jfloatArray ritzr, jint offsetritzr, jfloatArray ritzi, jint offsetritzi, jfloatArray bounds, jint offsetbounds, jfloatArray shiftr, jint offsetshiftr, jfloatArray shifti, jint offsetshifti) {
  jboolean failed = FALSE;
  const char *__nwhich = NULL; int __nkev = 0; int __nnp = 0; float *__nritzr = NULL; float *__nritzi = NULL; float *__nbounds = NULL; float *__nshiftr = NULL; float *__nshifti = NULL;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __nkev = (*env)->GetIntField(env, kev, intW_val_fieldID);
  __nnp = (*env)->GetIntField(env, np, intW_val_fieldID);
  if (!(__nritzr = (*env)->GetPrimitiveArrayCritical(env, ritzr, NULL))) goto fail;
  if (!(__nritzi = (*env)->GetPrimitiveArrayCritical(env, ritzi, NULL))) goto fail;
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) goto fail;
  if (!(__nshiftr = (*env)->GetPrimitiveArrayCritical(env, shiftr, NULL))) goto fail;
  if (!(__nshifti = (*env)->GetPrimitiveArrayCritical(env, shifti, NULL))) goto fail;
  sngets_(&ishift, __nwhich, &__nkev, &__nnp, __nritzr + offsetritzr, __nritzi + offsetritzi, __nbounds + offsetbounds, __nshiftr + offsetshiftr, __nshifti + offsetshifti);
done:
  if (__nshifti) (*env)->ReleasePrimitiveArrayCritical(env, shifti, __nshifti, failed ? JNI_ABORT : 0);
  if (__nshiftr) (*env)->ReleasePrimitiveArrayCritical(env, shiftr, __nshiftr, failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, failed ? JNI_ABORT : 0);
  if (__nritzi) (*env)->ReleasePrimitiveArrayCritical(env, ritzi, __nritzi, failed ? JNI_ABORT : 0);
  if (__nritzr) (*env)->ReleasePrimitiveArrayCritical(env, ritzr, __nritzr, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, np, intW_val_fieldID, __nnp);
  if (!failed) (*env)->SetIntField(env, kev, intW_val_fieldID, __nkev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dsaitr_)(int *ido, const char *bmat, int *n, int *k, int *np, int *mode, double *resid, double *rnorm, double *v, int *ldv, double *h, int *ldh, int *ipntr, double *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsaitrK(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jint k, jint np, jint mode, jdoubleArray resid, jint offsetresid, jobject rnorm, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray h, jint offseth, jint ldh, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jobject info) {
  jboolean failed = FALSE;
  int __nido = 0; const char *__nbmat = NULL; double __nrnorm = 0; int __ninfo = 0; double *__nresid = NULL; double *__nv = NULL; double *__nh = NULL; int *__nipntr = NULL; double *__nworkd = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) goto fail;
  __nrnorm = (*env)->GetDoubleField(env, rnorm, doubleW_val_fieldID);
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) goto fail;
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  dsaitr_(&__nido, __nbmat, &n, &k, &np, &mode, __nresid + offsetresid, &__nrnorm, __nv + offsetv, &ldv, __nh + offseth, &ldh, __nipntr + offsetipntr, __nworkd + offsetworkd, &__ninfo);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!failed) (*env)->SetDoubleField(env, rnorm, doubleW_val_fieldID, __nrnorm);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*ssaitr_)(int *ido, const char *bmat, int *n, int *k, int *np, int *mode, float *resid, float *rnorm, float *v, int *ldv, float *h, int *ldh, int *ipntr, float *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssaitrK(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jint k, jint np, jint mode, jfloatArray resid, jint offsetresid, jobject rnorm, jfloatArray v, jint offsetv, jint ldv, jfloatArray h, jint offseth, jint ldh, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jobject info) {
  jboolean failed = FALSE;
  int __nido = 0; const char *__nbmat = NULL; float __nrnorm = 0; int __ninfo = 0; float *__nresid = NULL; float *__nv = NULL; float *__nh = NULL; int *__nipntr = NULL; float *__nworkd = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) goto fail;
  __nrnorm = (*env)->GetFloatField(env, rnorm, floatW_val_fieldID);
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) goto fail;
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  ssaitr_(&__nido, __nbmat, &n, &k, &np, &mode, __nresid + offsetresid, &__nrnorm, __nv + offsetv, &ldv, __nh + offseth, &ldh, __nipntr + offsetipntr, __nworkd + offsetworkd, &__ninfo);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!failed) (*env)->SetFloatField(env, rnorm, floatW_val_fieldID, __nrnorm);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dsapps_)(int *n, int *kev, int *np, double *shift, double *v, int *ldv, double *h, int *ldh, double *resid, double *q, int *ldq, double *workd);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsappsK(JNIEnv *env, UNUSED jobject obj, jint n, jint kev, jint np, jdoubleArray shift, jint offsetshift, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray h, jint offseth, jint ldh, jdoubleArray resid, jint offsetresid, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray workd, jint offsetworkd) {
  jboolean failed = FALSE;
  double *__nshift = NULL; double *__nv = NULL; double *__nh = NULL; double *__nresid = NULL; double *__nq = NULL; double *__nworkd = NULL;
  if (!(__nshift = (*env)->GetPrimitiveArrayCritical(env, shift, NULL))) goto fail;
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) goto fail;
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nq = (*env)->GetPrimitiveArrayCritical(env, q, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  dsapps_(&n, &kev, &np, __nshift + offsetshift, __nv + offsetv, &ldv, __nh + offseth, &ldh, __nresid + offsetresid, __nq + offsetq, &ldq, __nworkd + offsetworkd);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nq) (*env)->ReleasePrimitiveArrayCritical(env, q, __nq, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (__nshift) (*env)->ReleasePrimitiveArrayCritical(env, shift, __nshift, failed ? JNI_ABORT : 0);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*ssapps_)(int *n, int *kev, int *np, float *shift, float *v, int *ldv, float *h, int *ldh, float *resid, float *q, int *ldq, float *workd);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssappsK(JNIEnv *env, UNUSED jobject obj, jint n, jint kev, jint np, jfloatArray shift, jint offsetshift, jfloatArray v, jint offsetv, jint ldv, jfloatArray h, jint offseth, jint ldh, jfloatArray resid, jint offsetresid, jfloatArray q, jint offsetq, jint ldq, jfloatArray workd, jint offsetworkd) {
  jboolean failed = FALSE;
  float *__nshift = NULL; float *__nv = NULL; float *__nh = NULL; float *__nresid = NULL; float *__nq = NULL; float *__nworkd = NULL;
  if (!(__nshift = (*env)->GetPrimitiveArrayCritical(env, shift, NULL))) goto fail;
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) goto fail;
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nq = (*env)->GetPrimitiveArrayCritical(env, q, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  ssapps_(&n, &kev, &np, __nshift + offsetshift, __nv + offsetv, &ldv, __nh + offseth, &ldh, __nresid + offsetresid, __nq + offsetq, &ldq, __nworkd + offsetworkd);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nq) (*env)->ReleasePrimitiveArrayCritical(env, q, __nq, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (__nshift) (*env)->ReleasePrimitiveArrayCritical(env, shift, __nshift, failed ? JNI_ABORT : 0);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dsaup2_)(int *ido, const char *bmat, int *n, const char *which, int *nev, int *np, double *tol, double *resid, int *mode, int *iupd, int *ishift, int *mxiter, double *v, int *ldv, double *h, int *ldh, double *ritz, double *bounds, double *q, int *ldq, double *workl, int *ipntr, double *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsaup2K(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jstring which, jobject nev, jobject np, jdouble tol, jdoubleArray resid, jint offsetresid, jint mode, jint iupd, jint ishift, jobject mxiter, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray h, jint offseth, jint ldh, jdoubleArray ritz, jint offsetritz, jdoubleArray bounds, jint offsetbounds, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray workl, jint offsetworkl, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jobject info) {
  jboolean failed = FALSE;
  int __nido = 0; const char *__nbmat = NULL; const char *__nwhich = NULL; int __nnev = 0; int __nnp = 0; int __nmxiter = 0; int __ninfo = 0; double *__nresid = NULL; double *__nv = NULL; double *__nh = NULL; double *__nritz = NULL; double *__nbounds = NULL; double *__nq = NULL; double *__nworkl = NULL; int *__nipntr = NULL; double *__nworkd = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) goto fail;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __nnev = (*env)->GetIntField(env, nev, intW_val_fieldID);
  __nnp = (*env)->GetIntField(env, np, intW_val_fieldID);
  __nmxiter = (*env)->GetIntField(env, mxiter, intW_val_fieldID);
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) goto fail;
  if (!(__nritz = (*env)->GetPrimitiveArrayCritical(env, ritz, NULL))) goto fail;
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) goto fail;
  if (!(__nq = (*env)->GetPrimitiveArrayCritical(env, q, NULL))) goto fail;
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) goto fail;
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  dsaup2_(&__nido, __nbmat, &n, __nwhich, &__nnev, &__nnp, &tol, __nresid + offsetresid, &mode, &iupd, &ishift, &__nmxiter, __nv + offsetv, &ldv, __nh + offseth, &ldh, __nritz + offsetritz, __nbounds + offsetbounds, __nq + offsetq, &ldq, __nworkl + offsetworkl, __nipntr + offsetipntr, __nworkd + offsetworkd, &__ninfo);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, failed ? JNI_ABORT : 0);
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, failed ? JNI_ABORT : 0);
  if (__nq) (*env)->ReleasePrimitiveArrayCritical(env, q, __nq, failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, failed ? JNI_ABORT : 0);
  if (__nritz) (*env)->ReleasePrimitiveArrayCritical(env, ritz, __nritz, failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!failed) (*env)->SetIntField(env, mxiter, intW_val_fieldID, __nmxiter);
  if (!failed) (*env)->SetIntField(env, np, intW_val_fieldID, __nnp);
  if (!failed) (*env)->SetIntField(env, nev, intW_val_fieldID, __nnev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*ssaup2_)(int *ido, const char *bmat, int *n, const char *which, int *nev, int *np, float *tol, float *resid, int *mode, int *iupd, int *ishift, int *mxiter, float *v, int *ldv, float *h, int *ldh, float *ritz, float *bounds, float *q, int *ldq, float *workl, int *ipntr, float *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssaup2K(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jstring which, jobject nev, jobject np, jfloat tol, jfloatArray resid, jint offsetresid, jint mode, jint iupd, jint ishift, jobject mxiter, jfloatArray v, jint offsetv, jint ldv, jfloatArray h, jint offseth, jint ldh, jfloatArray ritz, jint offsetritz, jfloatArray bounds, jint offsetbounds, jfloatArray q, jint offsetq, jint ldq, jfloatArray workl, jint offsetworkl, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jobject info) {
  jboolean failed = FALSE;
  int __nido = 0; const char *__nbmat = NULL; const char *__nwhich = NULL; int __nnev = 0; int __nnp = 0; int __nmxiter = 0; int __ninfo = 0; float *__nresid = NULL; float *__nv = NULL; float *__nh = NULL; float *__nritz = NULL; float *__nbounds = NULL; float *__nq = NULL; float *__nworkl = NULL; int *__nipntr = NULL; float *__nworkd = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) goto fail;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __nnev = (*env)->GetIntField(env, nev, intW_val_fieldID);
  __nnp = (*env)->GetIntField(env, np, intW_val_fieldID);
  __nmxiter = (*env)->GetIntField(env, mxiter, intW_val_fieldID);
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) goto fail;
  if (!(__nritz = (*env)->GetPrimitiveArrayCritical(env, ritz, NULL))) goto fail;
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) goto fail;
  if (!(__nq = (*env)->GetPrimitiveArrayCritical(env, q, NULL))) goto fail;
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) goto fail;
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  ssaup2_(&__nido, __nbmat, &n, __nwhich, &__nnev, &__nnp, &tol, __nresid + offsetresid, &mode, &iupd, &ishift, &__nmxiter, __nv + offsetv, &ldv, __nh + offseth, &ldh, __nritz + offsetritz, __nbounds + offsetbounds, __nq + offsetq, &ldq, __nworkl + offsetworkl, __nipntr + offsetipntr, __nworkd + offsetworkd, &__ninfo);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, failed ? JNI_ABORT : 0);
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, failed ? JNI_ABORT : 0);
  if (__nq) (*env)->ReleasePrimitiveArrayCritical(env, q, __nq, failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, failed ? JNI_ABORT : 0);
  if (__nritz) (*env)->ReleasePrimitiveArrayCritical(env, ritz, __nritz, failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!failed) (*env)->SetIntField(env, mxiter, intW_val_fieldID, __nmxiter);
  if (!failed) (*env)->SetIntField(env, np, intW_val_fieldID, __nnp);
  if (!failed) (*env)->SetIntField(env, nev, intW_val_fieldID, __nnev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dseigt_)(double *rnorm, int *n, double *h, int *ldh, double *eig, double *bounds, double *workl, int *ierr);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dseigtK(JNIEnv *env, UNUSED jobject obj, jdouble rnorm, jint n, jdoubleArray h, jint offseth, jint ldh, jdoubleArray eig, jint offseteig, jdoubleArray bounds, jint offsetbounds, jdoubleArray workl, jint offsetworkl, jobject ierr) {
  jboolean failed = FALSE;
  int __nierr = 0; double *__nh = NULL; double *__neig = NULL; double *__nbounds = NULL; double *__nworkl = NULL;
  __nierr = (*env)->GetIntField(env, ierr, intW_val_fieldID);
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) goto fail;
  if (!(__neig = (*env)->GetPrimitiveArrayCritical(env, eig, NULL))) goto fail;
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) goto fail;
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) goto fail;
  dseigt_(&rnorm, &n, __nh + offseth, &ldh, __neig + offseteig, __nbounds + offsetbounds, __nworkl + offsetworkl, &__nierr);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, failed ? JNI_ABORT : 0);
  if (__neig) (*env)->ReleasePrimitiveArrayCritical(env, eig, __neig, failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, ierr, intW_val_fieldID, __nierr);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*sseigt_)(float *rnorm, int *n, float *h, int *ldh, float *eig, float *bounds, float *workl, int *ierr);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sseigtK(JNIEnv *env, UNUSED jobject obj, jfloat rnorm, jint n, jfloatArray h, jint offseth, jint ldh, jfloatArray eig, jint offseteig, jfloatArray bounds, jint offsetbounds, jfloatArray workl, jint offsetworkl, jobject ierr) {
  jboolean failed = FALSE;
  int __nierr = 0; float *__nh = NULL; float *__neig = NULL; float *__nbounds = NULL; float *__nworkl = NULL;
  __nierr = (*env)->GetIntField(env, ierr, intW_val_fieldID);
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) goto fail;
  if (!(__neig = (*env)->GetPrimitiveArrayCritical(env, eig, NULL))) goto fail;
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) goto fail;
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) goto fail;
  sseigt_(&rnorm, &n, __nh + offseth, &ldh, __neig + offseteig, __nbounds + offsetbounds, __nworkl + offsetworkl, &__nierr);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, failed ? JNI_ABORT : 0);
  if (__neig) (*env)->ReleasePrimitiveArrayCritical(env, eig, __neig, failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, ierr, intW_val_fieldID, __nierr);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dsesrt_)(const char *which, int *apply, int *n, double *x, int *na, double *a, int *lda);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsesrtK(JNIEnv *env, UNUSED jobject obj, jstring which, jboolean apply, jint n, jdoubleArray x, jint offsetx, jint na, jdoubleArray a, jint offseta, jint lda) {
  jboolean failed = FALSE;
  const char *__nwhich = NULL; int __napply; double *__nx = NULL; double *__na = NULL;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __napply = apply;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  dsesrt_(__nwhich, &__napply, &n, __nx + offsetx, &na, __na + offseta, &lda);
done:
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, failed ? JNI_ABORT : 0);
  if (!failed) apply = __napply;
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*ssesrt_)(const char *which, int *apply, int *n, float *x, int *na, float *a, int *lda);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssesrtK(JNIEnv *env, UNUSED jobject obj, jstring which, jboolean apply, jint n, jfloatArray x, jint offsetx, jint na, jfloatArray a, jint offseta, jint lda) {
  jboolean failed = FALSE;
  const char *__nwhich = NULL; int __napply; float *__nx = NULL; float *__na = NULL;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __napply = apply;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  ssesrt_(__nwhich, &__napply, &n, __nx + offsetx, &na, __na + offseta, &lda);
done:
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, failed ? JNI_ABORT : 0);
  if (!failed) apply = __napply;
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dsaupd_)(int *ido, const char *bmat, int *n, const char *which, int *nev, double *tol, double *resid, int *ncv, double *v, int *ldv, int *iparam, int *ipntr, double *workd, double *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsaupdK(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jstring which, jint nev, jobject tol, jdoubleArray resid, jint offsetresid, jint ncv, jdoubleArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jdoubleArray workl, jint offsetworkl, jint lworkl, jobject info) {
  jboolean failed = FALSE;
  int __nido = 0; const char *__nbmat = NULL; const char *__nwhich = NULL; double __ntol = 0; int __ninfo = 0; double *__nresid = NULL; double *__nv = NULL; int *__niparam = NULL; int *__nipntr = NULL; double *__nworkd = NULL; double *__nworkl = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) goto fail;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __ntol = (*env)->GetDoubleField(env, tol, doubleW_val_fieldID);
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__niparam = (*env)->GetPrimitiveArrayCritical(env, iparam, NULL))) goto fail;
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) goto fail;
  dsaupd_(&__nido, __nbmat, &n, __nwhich, &nev, &__ntol, __nresid + offsetresid, &ncv, __nv + offsetv, &ldv, __niparam + offsetiparam, __nipntr + offsetipntr, __nworkd + offsetworkd, __nworkl + offsetworkl, &lworkl, &__ninfo);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, failed ? JNI_ABORT : 0);
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, failed ? JNI_ABORT : 0);
  if (__niparam) (*env)->ReleasePrimitiveArrayCritical(env, iparam, __niparam, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!failed) (*env)->SetDoubleField(env, tol, doubleW_val_fieldID, __ntol);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*ssaupd_)(int *ido, const char *bmat, int *n, const char *which, int *nev, float *tol, float *resid, int *ncv, float *v, int *ldv, int *iparam, int *ipntr, float *workd, float *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssaupdK(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jstring which, jint nev, jobject tol, jfloatArray resid, jint offsetresid, jint ncv, jfloatArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jfloatArray workl, jint offsetworkl, jint lworkl, jobject info) {
  jboolean failed = FALSE;
  int __nido = 0; const char *__nbmat = NULL; const char *__nwhich = NULL; float __ntol = 0; int __ninfo = 0; float *__nresid = NULL; float *__nv = NULL; int *__niparam = NULL; int *__nipntr = NULL; float *__nworkd = NULL; float *__nworkl = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) goto fail;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __ntol = (*env)->GetFloatField(env, tol, floatW_val_fieldID);
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__niparam = (*env)->GetPrimitiveArrayCritical(env, iparam, NULL))) goto fail;
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) goto fail;
  ssaupd_(&__nido, __nbmat, &n, __nwhich, &nev, &__ntol, __nresid + offsetresid, &ncv, __nv + offsetv, &ldv, __niparam + offsetiparam, __nipntr + offsetipntr, __nworkd + offsetworkd, __nworkl + offsetworkl, &lworkl, &__ninfo);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, failed ? JNI_ABORT : 0);
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, failed ? JNI_ABORT : 0);
  if (__niparam) (*env)->ReleasePrimitiveArrayCritical(env, iparam, __niparam, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!failed) (*env)->SetFloatField(env, tol, floatW_val_fieldID, __ntol);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dseupd_)(int *rvec, const char *howmny, int *select, double *d, double *z, int *ldz, double *sigma, const char *bmat, int *n, const char *which, int *nev, double *tol, double *resid, int *ncv, double *v, int *ldv, int *iparam, int *ipntr, double *workd, double *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dseupdK(JNIEnv *env, UNUSED jobject obj, jboolean rvec, jstring howmny, jbooleanArray select, jint offsetselect, jdoubleArray d, jint offsetd, jdoubleArray z, jint offsetz, jint ldz, jdouble sigma, jstring bmat, jint n, jstring which, jobject nev, jdouble tol, jdoubleArray resid, jint offsetresid, jint ncv, jdoubleArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jdoubleArray workl, jint offsetworkl, jint lworkl, jobject info) {
  jboolean failed = FALSE;
  int __nrvec; const char *__nhowmny = NULL; const char *__nbmat = NULL; const char *__nwhich = NULL; int __nnev = 0; int __ninfo = 0; jboolean *__jselect = NULL; int *__nselect = NULL; double *__nd = NULL; double *__nz = NULL; double *__nresid = NULL; double *__nv = NULL; int *__niparam = NULL; int *__nipntr = NULL; double *__nworkd = NULL; double *__nworkl = NULL;
  __nrvec = rvec;
  if (!(__nhowmny = (*env)->GetStringUTFChars(env, howmny, NULL))) goto fail;
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) goto fail;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __nnev = (*env)->GetIntField(env, nev, intW_val_fieldID);
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__jselect = (*env)->GetPrimitiveArrayCritical(env, select, NULL))) { goto fail; } do { int length = (*env)->GetArrayLength(env, select); if (length <= 0) goto fail; if (!(__nselect = malloc(sizeof(jboolean) * length))) goto fail; for (int i = 0; i < length; i++) { __nselect[i] = __jselect[i]; } } while(0);
  if (!(__nd = (*env)->GetPrimitiveArrayCritical(env, d, NULL))) goto fail;
  if (!(__nz = (*env)->GetPrimitiveArrayCritical(env, z, NULL))) goto fail;
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__niparam = (*env)->GetPrimitiveArrayCritical(env, iparam, NULL))) goto fail;
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) goto fail;
  dseupd_(&__nrvec, __nhowmny, __nselect + offsetselect, __nd + offsetd, __nz + offsetz, &ldz, &sigma, __nbmat, &n, __nwhich, &__nnev, &tol, __nresid + offsetresid, &ncv, __nv + offsetv, &ldv, __niparam + offsetiparam, __nipntr + offsetipntr, __nworkd + offsetworkd, __nworkl + offsetworkl, &lworkl, &__ninfo);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, failed ? JNI_ABORT : 0);
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, failed ? JNI_ABORT : 0);
  if (__niparam) (*env)->ReleasePrimitiveArrayCritical(env, iparam, __niparam, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (__nz) (*env)->ReleasePrimitiveArrayCritical(env, z, __nz, failed ? JNI_ABORT : 0);
  if (__nd) (*env)->ReleasePrimitiveArrayCritical(env, d, __nd, failed ? JNI_ABORT : 0);
  if (__nselect) { free(__nselect); } if (__jselect) (*env)->ReleasePrimitiveArrayCritical(env, select, __nselect, JNI_ABORT);
  if (!failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!failed) (*env)->SetIntField(env, nev, intW_val_fieldID, __nnev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (__nhowmny) (*env)->ReleaseStringUTFChars(env, howmny, __nhowmny);
  if (!failed) rvec = __nrvec;
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*sseupd_)(int *rvec, const char *howmny, int *select, float *d, float *z, int *ldz, float *sigma, const char *bmat, int *n, const char *which, int *nev, float *tol, float *resid, int *ncv, float *v, int *ldv, int *iparam, int *ipntr, float *workd, float *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sseupdK(JNIEnv *env, UNUSED jobject obj, jboolean rvec, jstring howmny, jbooleanArray select, jint offsetselect, jfloatArray d, jint offsetd, jfloatArray z, jint offsetz, jint ldz, jfloat sigma, jstring bmat, jint n, jstring which, jobject nev, jfloat tol, jfloatArray resid, jint offsetresid, jint ncv, jfloatArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jfloatArray workl, jint offsetworkl, jint lworkl, jobject info) {
  jboolean failed = FALSE;
  int __nrvec; const char *__nhowmny = NULL; const char *__nbmat = NULL; const char *__nwhich = NULL; int __nnev = 0; int __ninfo = 0; jboolean *__jselect = NULL; int *__nselect = NULL; float *__nd = NULL; float *__nz = NULL; float *__nresid = NULL; float *__nv = NULL; int *__niparam = NULL; int *__nipntr = NULL; float *__nworkd = NULL; float *__nworkl = NULL;
  __nrvec = rvec;
  if (!(__nhowmny = (*env)->GetStringUTFChars(env, howmny, NULL))) goto fail;
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) goto fail;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __nnev = (*env)->GetIntField(env, nev, intW_val_fieldID);
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__jselect = (*env)->GetPrimitiveArrayCritical(env, select, NULL))) { goto fail; } do { int length = (*env)->GetArrayLength(env, select); if (length <= 0) goto fail; if (!(__nselect = malloc(sizeof(jboolean) * length))) goto fail; for (int i = 0; i < length; i++) { __nselect[i] = __jselect[i]; } } while(0);
  if (!(__nd = (*env)->GetPrimitiveArrayCritical(env, d, NULL))) goto fail;
  if (!(__nz = (*env)->GetPrimitiveArrayCritical(env, z, NULL))) goto fail;
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) goto fail;
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) goto fail;
  if (!(__niparam = (*env)->GetPrimitiveArrayCritical(env, iparam, NULL))) goto fail;
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) goto fail;
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) goto fail;
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) goto fail;
  sseupd_(&__nrvec, __nhowmny, __nselect + offsetselect, __nd + offsetd, __nz + offsetz, &ldz, &sigma, __nbmat, &n, __nwhich, &__nnev, &tol, __nresid + offsetresid, &ncv, __nv + offsetv, &ldv, __niparam + offsetiparam, __nipntr + offsetipntr, __nworkd + offsetworkd, __nworkl + offsetworkl, &lworkl, &__ninfo);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, failed ? JNI_ABORT : 0);
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, failed ? JNI_ABORT : 0);
  if (__niparam) (*env)->ReleasePrimitiveArrayCritical(env, iparam, __niparam, failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, failed ? JNI_ABORT : 0);
  if (__nz) (*env)->ReleasePrimitiveArrayCritical(env, z, __nz, failed ? JNI_ABORT : 0);
  if (__nd) (*env)->ReleasePrimitiveArrayCritical(env, d, __nd, failed ? JNI_ABORT : 0);
  if (__nselect) { free(__nselect); } if (__jselect) (*env)->ReleasePrimitiveArrayCritical(env, select, __nselect, JNI_ABORT);
  if (!failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!failed) (*env)->SetIntField(env, nev, intW_val_fieldID, __nnev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (__nhowmny) (*env)->ReleaseStringUTFChars(env, howmny, __nhowmny);
  if (!failed) rvec = __nrvec;
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dsgets_)(int *ishift, const char *which, int *kev, int *np, double *ritz, double *bounds, double *shifts);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsgetsK(JNIEnv *env, UNUSED jobject obj, jint ishift, jstring which, jobject kev, jobject np, jdoubleArray ritz, jint offsetritz, jdoubleArray bounds, jint offsetbounds, jdoubleArray shifts, jint offsetshifts) {
  jboolean failed = FALSE;
  const char *__nwhich = NULL; int __nkev = 0; int __nnp = 0; double *__nritz = NULL; double *__nbounds = NULL; double *__nshifts = NULL;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __nkev = (*env)->GetIntField(env, kev, intW_val_fieldID);
  __nnp = (*env)->GetIntField(env, np, intW_val_fieldID);
  if (!(__nritz = (*env)->GetPrimitiveArrayCritical(env, ritz, NULL))) goto fail;
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) goto fail;
  if (!(__nshifts = (*env)->GetPrimitiveArrayCritical(env, shifts, NULL))) goto fail;
  dsgets_(&ishift, __nwhich, &__nkev, &__nnp, __nritz + offsetritz, __nbounds + offsetbounds, __nshifts + offsetshifts);
done:
  if (__nshifts) (*env)->ReleasePrimitiveArrayCritical(env, shifts, __nshifts, failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, failed ? JNI_ABORT : 0);
  if (__nritz) (*env)->ReleasePrimitiveArrayCritical(env, ritz, __nritz, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, np, intW_val_fieldID, __nnp);
  if (!failed) (*env)->SetIntField(env, kev, intW_val_fieldID, __nkev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*ssgets_)(int *ishift, const char *which, int *kev, int *np, float *ritz, float *bounds, float *shifts);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssgetsK(JNIEnv *env, UNUSED jobject obj, jint ishift, jstring which, jobject kev, jobject np, jfloatArray ritz, jint offsetritz, jfloatArray bounds, jint offsetbounds, jfloatArray shifts, jint offsetshifts) {
  jboolean failed = FALSE;
  const char *__nwhich = NULL; int __nkev = 0; int __nnp = 0; float *__nritz = NULL; float *__nbounds = NULL; float *__nshifts = NULL;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __nkev = (*env)->GetIntField(env, kev, intW_val_fieldID);
  __nnp = (*env)->GetIntField(env, np, intW_val_fieldID);
  if (!(__nritz = (*env)->GetPrimitiveArrayCritical(env, ritz, NULL))) goto fail;
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) goto fail;
  if (!(__nshifts = (*env)->GetPrimitiveArrayCritical(env, shifts, NULL))) goto fail;
  ssgets_(&ishift, __nwhich, &__nkev, &__nnp, __nritz + offsetritz, __nbounds + offsetbounds, __nshifts + offsetshifts);
done:
  if (__nshifts) (*env)->ReleasePrimitiveArrayCritical(env, shifts, __nshifts, failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, failed ? JNI_ABORT : 0);
  if (__nritz) (*env)->ReleasePrimitiveArrayCritical(env, ritz, __nritz, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, np, intW_val_fieldID, __nnp);
  if (!failed) (*env)->SetIntField(env, kev, intW_val_fieldID, __nkev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dsortc_)(const char *which, int *apply, int *n, double *xreal, double *ximag, double *y);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsortcK(JNIEnv *env, UNUSED jobject obj, jstring which, jboolean apply, jint n, jdoubleArray xreal, jint offsetxreal, jdoubleArray ximag, jint offsetximag, jdoubleArray y, jint offsety) {
  jboolean failed = FALSE;
  const char *__nwhich = NULL; int __napply; double *__nxreal = NULL; double *__nximag = NULL; double *__ny = NULL;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __napply = apply;
  if (!(__nxreal = (*env)->GetPrimitiveArrayCritical(env, xreal, NULL))) goto fail;
  if (!(__nximag = (*env)->GetPrimitiveArrayCritical(env, ximag, NULL))) goto fail;
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  dsortc_(__nwhich, &__napply, &n, __nxreal + offsetxreal, __nximag + offsetximag, __ny + offsety);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, failed ? JNI_ABORT : 0);
  if (__nximag) (*env)->ReleasePrimitiveArrayCritical(env, ximag, __nximag, failed ? JNI_ABORT : 0);
  if (__nxreal) (*env)->ReleasePrimitiveArrayCritical(env, xreal, __nxreal, failed ? JNI_ABORT : 0);
  if (!failed) apply = __napply;
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*ssortc_)(const char *which, int *apply, int *n, float *xreal, float *ximag, float *y);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssortcK(JNIEnv *env, UNUSED jobject obj, jstring which, jboolean apply, jint n, jfloatArray xreal, jint offsetxreal, jfloatArray ximag, jint offsetximag, jfloatArray y, jint offsety) {
  jboolean failed = FALSE;
  const char *__nwhich = NULL; int __napply; float *__nxreal = NULL; float *__nximag = NULL; float *__ny = NULL;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __napply = apply;
  if (!(__nxreal = (*env)->GetPrimitiveArrayCritical(env, xreal, NULL))) goto fail;
  if (!(__nximag = (*env)->GetPrimitiveArrayCritical(env, ximag, NULL))) goto fail;
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  ssortc_(__nwhich, &__napply, &n, __nxreal + offsetxreal, __nximag + offsetximag, __ny + offsety);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, failed ? JNI_ABORT : 0);
  if (__nximag) (*env)->ReleasePrimitiveArrayCritical(env, ximag, __nximag, failed ? JNI_ABORT : 0);
  if (__nxreal) (*env)->ReleasePrimitiveArrayCritical(env, xreal, __nxreal, failed ? JNI_ABORT : 0);
  if (!failed) apply = __napply;
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dsortr_)(const char *which, int *apply, int *n, double *x1, double *x2);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsortrK(JNIEnv *env, UNUSED jobject obj, jstring which, jboolean apply, jint n, jdoubleArray x1, jint offsetx1, jdoubleArray x2, jint offsetx2) {
  jboolean failed = FALSE;
  const char *__nwhich = NULL; int __napply; double *__nx1 = NULL; double *__nx2 = NULL;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __napply = apply;
  if (!(__nx1 = (*env)->GetPrimitiveArrayCritical(env, x1, NULL))) goto fail;
  if (!(__nx2 = (*env)->GetPrimitiveArrayCritical(env, x2, NULL))) goto fail;
  dsortr_(__nwhich, &__napply, &n, __nx1 + offsetx1, __nx2 + offsetx2);
done:
  if (__nx2) (*env)->ReleasePrimitiveArrayCritical(env, x2, __nx2, failed ? JNI_ABORT : 0);
  if (__nx1) (*env)->ReleasePrimitiveArrayCritical(env, x1, __nx1, failed ? JNI_ABORT : 0);
  if (!failed) apply = __napply;
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*ssortr_)(const char *which, int *apply, int *n, float *x1, float *x2);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssortrK(JNIEnv *env, UNUSED jobject obj, jstring which, jboolean apply, jint n, jfloatArray x1, jint offsetx1, jfloatArray x2, jint offsetx2) {
  jboolean failed = FALSE;
  const char *__nwhich = NULL; int __napply; float *__nx1 = NULL; float *__nx2 = NULL;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) goto fail;
  __napply = apply;
  if (!(__nx1 = (*env)->GetPrimitiveArrayCritical(env, x1, NULL))) goto fail;
  if (!(__nx2 = (*env)->GetPrimitiveArrayCritical(env, x2, NULL))) goto fail;
  ssortr_(__nwhich, &__napply, &n, __nx1 + offsetx1, __nx2 + offsetx2);
done:
  if (__nx2) (*env)->ReleasePrimitiveArrayCritical(env, x2, __nx2, failed ? JNI_ABORT : 0);
  if (__nx1) (*env)->ReleasePrimitiveArrayCritical(env, x1, __nx1, failed ? JNI_ABORT : 0);
  if (!failed) apply = __napply;
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dstatn_)();

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dstatnK(JNIEnv *env, UNUSED jobject obj) {
  jboolean failed = FALSE;

  dstatn_();
done:

  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*sstatn_)();

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sstatnK(JNIEnv *env, UNUSED jobject obj) {
  jboolean failed = FALSE;

  sstatn_();
done:

  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dstats_)();

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dstatsK(JNIEnv *env, UNUSED jobject obj) {
  jboolean failed = FALSE;

  dstats_();
done:

  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*sstats_)();

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sstatsK(JNIEnv *env, UNUSED jobject obj) {
  jboolean failed = FALSE;

  sstats_();
done:

  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dstqrb_)(int *n, double *d, double *e, double *z, double *work, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dstqrbK(JNIEnv *env, UNUSED jobject obj, jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray z, jint offsetz, jdoubleArray work, jint offsetwork, jobject info) {
  jboolean failed = FALSE;
  int __ninfo = 0; double *__nd = NULL; double *__ne = NULL; double *__nz = NULL; double *__nwork = NULL;
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nd = (*env)->GetPrimitiveArrayCritical(env, d, NULL))) goto fail;
  if (!(__ne = (*env)->GetPrimitiveArrayCritical(env, e, NULL))) goto fail;
  if (!(__nz = (*env)->GetPrimitiveArrayCritical(env, z, NULL))) goto fail;
  if (!(__nwork = (*env)->GetPrimitiveArrayCritical(env, work, NULL))) goto fail;
  dstqrb_(&n, __nd + offsetd, __ne + offsete, __nz + offsetz, __nwork + offsetwork, &__ninfo);
done:
  if (__nwork) (*env)->ReleasePrimitiveArrayCritical(env, work, __nwork, failed ? JNI_ABORT : 0);
  if (__nz) (*env)->ReleasePrimitiveArrayCritical(env, z, __nz, failed ? JNI_ABORT : 0);
  if (__ne) (*env)->ReleasePrimitiveArrayCritical(env, e, __ne, failed ? JNI_ABORT : 0);
  if (__nd) (*env)->ReleasePrimitiveArrayCritical(env, d, __nd, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*sstqrb_)(int *n, float *d, float *e, float *z, float *work, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sstqrbK(JNIEnv *env, UNUSED jobject obj, jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray z, jint offsetz, jfloatArray work, jint offsetwork, jobject info) {
  jboolean failed = FALSE;
  int __ninfo = 0; float *__nd = NULL; float *__ne = NULL; float *__nz = NULL; float *__nwork = NULL;
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nd = (*env)->GetPrimitiveArrayCritical(env, d, NULL))) goto fail;
  if (!(__ne = (*env)->GetPrimitiveArrayCritical(env, e, NULL))) goto fail;
  if (!(__nz = (*env)->GetPrimitiveArrayCritical(env, z, NULL))) goto fail;
  if (!(__nwork = (*env)->GetPrimitiveArrayCritical(env, work, NULL))) goto fail;
  sstqrb_(&n, __nd + offsetd, __ne + offsete, __nz + offsetz, __nwork + offsetwork, &__ninfo);
done:
  if (__nwork) (*env)->ReleasePrimitiveArrayCritical(env, work, __nwork, failed ? JNI_ABORT : 0);
  if (__nz) (*env)->ReleasePrimitiveArrayCritical(env, z, __nz, failed ? JNI_ABORT : 0);
  if (__ne) (*env)->ReleasePrimitiveArrayCritical(env, e, __ne, failed ? JNI_ABORT : 0);
  if (__nd) (*env)->ReleasePrimitiveArrayCritical(env, d, __nd, failed ? JNI_ABORT : 0);
  if (!failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static int (*icnteq_)(int *n, int *array, int *value);

jint Java_dev_ludovic_netlib_arpack_JNIARPACK_icnteqK(JNIEnv *env, UNUSED jobject obj, jint n, jintArray array, jint offsetarray, jint value) {
  jint __ret;
  jboolean failed = FALSE;
  int *__narray = NULL;
  if (!(__narray = (*env)->GetPrimitiveArrayCritical(env, array, NULL))) goto fail;
  __ret = icnteq_(&n, __narray + offsetarray, &value);
done:
  if (__narray) (*env)->ReleasePrimitiveArrayCritical(env, array, __narray, failed ? JNI_ABORT : 0);
  if (failed) throwOOM(env);
  return __ret;
fail:
  failed = TRUE;
  goto done;
}

static void (*icopy_)(int *n, int *lx, int *incx, int *ly, int *incy);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_icopyK(JNIEnv *env, UNUSED jobject obj, jint n, jintArray lx, jint offsetlx, jint incx, jintArray ly, jint offsetly, jint incy) {
  jboolean failed = FALSE;
  int *__nlx = NULL; int *__nly = NULL;
  if (!(__nlx = (*env)->GetPrimitiveArrayCritical(env, lx, NULL))) goto fail;
  if (!(__nly = (*env)->GetPrimitiveArrayCritical(env, ly, NULL))) goto fail;
  icopy_(&n, __nlx + offsetlx, &incx, __nly + offsetly, &incy);
done:
  if (__nly) (*env)->ReleasePrimitiveArrayCritical(env, ly, __nly, failed ? JNI_ABORT : 0);
  if (__nlx) (*env)->ReleasePrimitiveArrayCritical(env, lx, __nlx, failed ? JNI_ABORT : 0);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*iset_)(int *n, int *value, int *array, int *inc);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_isetK(JNIEnv *env, UNUSED jobject obj, jint n, jint value, jintArray array, jint offsetarray, jint inc) {
  jboolean failed = FALSE;
  int *__narray = NULL;
  if (!(__narray = (*env)->GetPrimitiveArrayCritical(env, array, NULL))) goto fail;
  iset_(&n, &value, __narray + offsetarray, &inc);
done:
  if (__narray) (*env)->ReleasePrimitiveArrayCritical(env, array, __narray, failed ? JNI_ABORT : 0);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*iswap_)(int *n, int *sx, int *incx, int *sy, int *incy);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_iswapK(JNIEnv *env, UNUSED jobject obj, jint n, jintArray sx, jint offsetsx, jint incx, jintArray sy, jint offsetsy, jint incy) {
  jboolean failed = FALSE;
  int *__nsx = NULL; int *__nsy = NULL;
  if (!(__nsx = (*env)->GetPrimitiveArrayCritical(env, sx, NULL))) goto fail;
  if (!(__nsy = (*env)->GetPrimitiveArrayCritical(env, sy, NULL))) goto fail;
  iswap_(&n, __nsx + offsetsx, &incx, __nsy + offsetsy, &incy);
done:
  if (__nsy) (*env)->ReleasePrimitiveArrayCritical(env, sy, __nsy, failed ? JNI_ABORT : 0);
  if (__nsx) (*env)->ReleasePrimitiveArrayCritical(env, sx, __nsx, failed ? JNI_ABORT : 0);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*second_)(float *t);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_secondK(JNIEnv *env, UNUSED jobject obj, jobject t) {
  jboolean failed = FALSE;
  float __nt = 0;
  __nt = (*env)->GetFloatField(env, t, floatW_val_fieldID);
  second_(&__nt);
done:
  if (!failed) (*env)->SetFloatField(env, t, floatW_val_fieldID, __nt);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

// static void (*dlaqrb_)(int *wantt, int *n, int *ilo, int *ihi, double *h, int *ldh, double *wr, double *wi, double *z, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dlaqrbK(JNIEnv *env, UNUSED jobject obj, jboolean wantt, jint n, jint ilo, jint ihi, jdoubleArray h, jint offseth, jint ldh, jdoubleArray wr, jint offsetwr, jdoubleArray wi, jint offsetwi, jdoubleArray z, jint offsetz, jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

// static void (*slaqrb_)(int *wantt, int *n, int *ilo, int *ihi, float *h, int *ldh, float *wr, float *wi, float *z, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_slaqrbK(JNIEnv *env, UNUSED jobject obj, jboolean wantt, jint n, jint ilo, jint ihi, jfloatArray h, jint offseth, jint ldh, jfloatArray wr, jint offsetwr, jfloatArray wi, jint offsetwi, jfloatArray z, jint offsetz, jobject info) {
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
