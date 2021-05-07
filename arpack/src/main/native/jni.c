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

static void (*dmout_)(int *lout, int *m, int *n, double *a, int *lda, int *idigit, const char *ifmt);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dmoutK(JNIEnv *env, UNUSED jobject obj, jint lout, jint m, jint n, jdoubleArray a, jint offseta, jint lda, jint idigit, jstring ifmt) {
  jboolean __failed = FALSE;
  int __nlout __attribute__((aligned(8)));
  int __nm __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nidigit __attribute__((aligned(8)));
  const char *__nifmt = NULL;
  double *__na = NULL;
  __nlout = lout;
  __nm = m;
  __nn = n;
  __nlda = lda;
  __nidigit = idigit;
  if (!(__nifmt = (*env)->GetStringUTFChars(env, ifmt, NULL))) { __failed = TRUE; goto done; }
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  dmout_(&__nlout, &__nm, &__nn, __na + offseta, &__nlda, &__nidigit, __nifmt);
done:
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, __failed ? JNI_ABORT : 0);
  if (__nifmt) (*env)->ReleaseStringUTFChars(env, ifmt, __nifmt);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*smout_)(int *lout, int *m, int *n, float *a, int *lda, int *idigit, const char *ifmt);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_smoutK(JNIEnv *env, UNUSED jobject obj, jint lout, jint m, jint n, jfloatArray a, jint offseta, jint lda, jint idigit, jstring ifmt) {
  jboolean __failed = FALSE;
  int __nlout __attribute__((aligned(8)));
  int __nm __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nidigit __attribute__((aligned(8)));
  const char *__nifmt = NULL;
  float *__na = NULL;
  __nlout = lout;
  __nm = m;
  __nn = n;
  __nlda = lda;
  __nidigit = idigit;
  if (!(__nifmt = (*env)->GetStringUTFChars(env, ifmt, NULL))) { __failed = TRUE; goto done; }
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  smout_(&__nlout, &__nm, &__nn, __na + offseta, &__nlda, &__nidigit, __nifmt);
done:
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, __failed ? JNI_ABORT : 0);
  if (__nifmt) (*env)->ReleaseStringUTFChars(env, ifmt, __nifmt);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dvout_)(int *lout, int *n, double *sx, int *idigit, const char *ifmt);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dvoutK(JNIEnv *env, UNUSED jobject obj, jint lout, jint n, jdoubleArray sx, jint offsetsx, jint idigit, jstring ifmt) {
  jboolean __failed = FALSE;
  int __nlout __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  int __nidigit __attribute__((aligned(8)));
  const char *__nifmt = NULL;
  double *__nsx = NULL;
  __nlout = lout;
  __nn = n;
  __nidigit = idigit;
  if (!(__nifmt = (*env)->GetStringUTFChars(env, ifmt, NULL))) { __failed = TRUE; goto done; }
  if (!(__nsx = (*env)->GetPrimitiveArrayCritical(env, sx, NULL))) { __failed = TRUE; goto done; }
  dvout_(&__nlout, &__nn, __nsx + offsetsx, &__nidigit, __nifmt);
done:
  if (__nsx) (*env)->ReleasePrimitiveArrayCritical(env, sx, __nsx, __failed ? JNI_ABORT : 0);
  if (__nifmt) (*env)->ReleaseStringUTFChars(env, ifmt, __nifmt);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*svout_)(int *lout, int *n, float *sx, int *idigit, const char *ifmt);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_svoutK(JNIEnv *env, UNUSED jobject obj, jint lout, jint n, jfloatArray sx, jint offsetsx, jint idigit, jstring ifmt) {
  jboolean __failed = FALSE;
  int __nlout __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  int __nidigit __attribute__((aligned(8)));
  const char *__nifmt = NULL;
  float *__nsx = NULL;
  __nlout = lout;
  __nn = n;
  __nidigit = idigit;
  if (!(__nifmt = (*env)->GetStringUTFChars(env, ifmt, NULL))) { __failed = TRUE; goto done; }
  if (!(__nsx = (*env)->GetPrimitiveArrayCritical(env, sx, NULL))) { __failed = TRUE; goto done; }
  svout_(&__nlout, &__nn, __nsx + offsetsx, &__nidigit, __nifmt);
done:
  if (__nsx) (*env)->ReleasePrimitiveArrayCritical(env, sx, __nsx, __failed ? JNI_ABORT : 0);
  if (__nifmt) (*env)->ReleaseStringUTFChars(env, ifmt, __nifmt);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*ivout_)(int *lout, int *n, int *ix, int *idigit, const char *ifmt);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ivoutK(JNIEnv *env, UNUSED jobject obj, jint lout, jint n, jintArray ix, jint offsetix, jint idigit, jstring ifmt) {
  jboolean __failed = FALSE;
  int __nlout __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  int __nidigit __attribute__((aligned(8)));
  const char *__nifmt = NULL;
  int *__nix = NULL;
  __nlout = lout;
  __nn = n;
  __nidigit = idigit;
  if (!(__nifmt = (*env)->GetStringUTFChars(env, ifmt, NULL))) { __failed = TRUE; goto done; }
  if (!(__nix = (*env)->GetPrimitiveArrayCritical(env, ix, NULL))) { __failed = TRUE; goto done; }
  ivout_(&__nlout, &__nn, __nix + offsetix, &__nidigit, __nifmt);
done:
  if (__nix) (*env)->ReleasePrimitiveArrayCritical(env, ix, __nix, __failed ? JNI_ABORT : 0);
  if (__nifmt) (*env)->ReleaseStringUTFChars(env, ifmt, __nifmt);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dgetv0_)(int *ido, const char *bmat, int *itry, int *initv, int *n, int *j, double *v, int *ldv, double *resid, double *rnorm, int *ipntr, double *workd, int *ierr);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dgetv0K(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint itry, jboolean initv, jint n, jint j, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray resid, jint offsetresid, jobject rnorm, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jobject ierr) {
  jboolean __failed = FALSE;
  int __nido = 0;
  const char *__nbmat = NULL;
  int __nitry __attribute__((aligned(8)));
  int __ninitv __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  int __nj __attribute__((aligned(8)));
  int __nldv __attribute__((aligned(8)));
  double __nrnorm = 0;
  int __nierr = 0;
  double *__nv = NULL;
  double *__nresid = NULL;
  int *__nipntr = NULL;
  double *__nworkd = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) { __failed = TRUE; goto done; }
  __nitry = itry;
  __ninitv = initv;
  __nn = n;
  __nj = j;
  __nldv = ldv;
  __nrnorm = (*env)->GetDoubleField(env, rnorm, doubleW_val_fieldID);
  __nierr = (*env)->GetIntField(env, ierr, intW_val_fieldID);
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  dgetv0_(&__nido, __nbmat, &__nitry, &__ninitv, &__nn, &__nj, __nv + offsetv, &__nldv, __nresid + offsetresid, &__nrnorm, __nipntr + offsetipntr, __nworkd + offsetworkd, &__nierr);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, ierr, intW_val_fieldID, __nierr);
  if (!__failed) (*env)->SetDoubleField(env, rnorm, doubleW_val_fieldID, __nrnorm);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!__failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*sgetv0_)(int *ido, const char *bmat, int *itry, int *initv, int *n, int *j, float *v, int *ldv, float *resid, float *rnorm, int *ipntr, float *workd, int *ierr);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sgetv0K(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint itry, jboolean initv, jint n, jint j, jfloatArray v, jint offsetv, jint ldv, jfloatArray resid, jint offsetresid, jobject rnorm, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jobject ierr) {
  jboolean __failed = FALSE;
  int __nido = 0;
  const char *__nbmat = NULL;
  int __nitry __attribute__((aligned(8)));
  int __ninitv __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  int __nj __attribute__((aligned(8)));
  int __nldv __attribute__((aligned(8)));
  float __nrnorm = 0;
  int __nierr = 0;
  float *__nv = NULL;
  float *__nresid = NULL;
  int *__nipntr = NULL;
  float *__nworkd = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) { __failed = TRUE; goto done; }
  __nitry = itry;
  __ninitv = initv;
  __nn = n;
  __nj = j;
  __nldv = ldv;
  __nrnorm = (*env)->GetFloatField(env, rnorm, floatW_val_fieldID);
  __nierr = (*env)->GetIntField(env, ierr, intW_val_fieldID);
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  sgetv0_(&__nido, __nbmat, &__nitry, &__ninitv, &__nn, &__nj, __nv + offsetv, &__nldv, __nresid + offsetresid, &__nrnorm, __nipntr + offsetipntr, __nworkd + offsetworkd, &__nierr);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, ierr, intW_val_fieldID, __nierr);
  if (!__failed) (*env)->SetFloatField(env, rnorm, floatW_val_fieldID, __nrnorm);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!__failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

// static void (*dlaqrb_)(int *wantt, int *n, int *ilo, int *ihi, double *h, int *ldh, double *wr, double *wi, double *z, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dlaqrbK(JNIEnv *env, UNUSED jobject obj, UNUSED jboolean wantt, UNUSED jint n, UNUSED jint ilo, UNUSED jint ihi, UNUSED jdoubleArray h, UNUSED jint offseth, UNUSED jint ldh, UNUSED jdoubleArray wr, UNUSED jint offsetwr, UNUSED jdoubleArray wi, UNUSED jint offsetwi, UNUSED jdoubleArray z, UNUSED jint offsetz, UNUSED jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

// static void (*slaqrb_)(int *wantt, int *n, int *ilo, int *ihi, float *h, int *ldh, float *wr, float *wi, float *z, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_slaqrbK(JNIEnv *env, UNUSED jobject obj, UNUSED jboolean wantt, UNUSED jint n, UNUSED jint ilo, UNUSED jint ihi, UNUSED jfloatArray h, UNUSED jint offseth, UNUSED jint ldh, UNUSED jfloatArray wr, UNUSED jint offsetwr, UNUSED jfloatArray wi, UNUSED jint offsetwi, UNUSED jfloatArray z, UNUSED jint offsetz, UNUSED jobject info) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dnaitr_)(int *ido, const char *bmat, int *n, int *k, int *np, int *nb, double *resid, double *rnorm, double *v, int *ldv, double *h, int *ldh, int *ipntr, double *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dnaitrK(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jint k, jint np, jint nb, jdoubleArray resid, jint offsetresid, jobject rnorm, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray h, jint offseth, jint ldh, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jobject info) {
  jboolean __failed = FALSE;
  int __nido = 0;
  const char *__nbmat = NULL;
  int __nn __attribute__((aligned(8)));
  int __nk __attribute__((aligned(8)));
  int __nnp __attribute__((aligned(8)));
  int __nnb __attribute__((aligned(8)));
  double __nrnorm = 0;
  int __nldv __attribute__((aligned(8)));
  int __nldh __attribute__((aligned(8)));
  int __ninfo = 0;
  double *__nresid = NULL;
  double *__nv = NULL;
  double *__nh = NULL;
  int *__nipntr = NULL;
  double *__nworkd = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nk = k;
  __nnp = np;
  __nnb = nb;
  __nrnorm = (*env)->GetDoubleField(env, rnorm, doubleW_val_fieldID);
  __nldv = ldv;
  __nldh = ldh;
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) { __failed = TRUE; goto done; }
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  dnaitr_(&__nido, __nbmat, &__nn, &__nk, &__nnp, &__nnb, __nresid + offsetresid, &__nrnorm, __nv + offsetv, &__nldv, __nh + offseth, &__nldh, __nipntr + offsetipntr, __nworkd + offsetworkd, &__ninfo);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, __failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!__failed) (*env)->SetDoubleField(env, rnorm, doubleW_val_fieldID, __nrnorm);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!__failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*snaitr_)(int *ido, const char *bmat, int *n, int *k, int *np, int *nb, float *resid, float *rnorm, float *v, int *ldv, float *h, int *ldh, int *ipntr, float *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_snaitrK(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jint k, jint np, jint nb, jfloatArray resid, jint offsetresid, jobject rnorm, jfloatArray v, jint offsetv, jint ldv, jfloatArray h, jint offseth, jint ldh, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jobject info) {
  jboolean __failed = FALSE;
  int __nido = 0;
  const char *__nbmat = NULL;
  int __nn __attribute__((aligned(8)));
  int __nk __attribute__((aligned(8)));
  int __nnp __attribute__((aligned(8)));
  int __nnb __attribute__((aligned(8)));
  float __nrnorm = 0;
  int __nldv __attribute__((aligned(8)));
  int __nldh __attribute__((aligned(8)));
  int __ninfo = 0;
  float *__nresid = NULL;
  float *__nv = NULL;
  float *__nh = NULL;
  int *__nipntr = NULL;
  float *__nworkd = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nk = k;
  __nnp = np;
  __nnb = nb;
  __nrnorm = (*env)->GetFloatField(env, rnorm, floatW_val_fieldID);
  __nldv = ldv;
  __nldh = ldh;
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) { __failed = TRUE; goto done; }
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  snaitr_(&__nido, __nbmat, &__nn, &__nk, &__nnp, &__nnb, __nresid + offsetresid, &__nrnorm, __nv + offsetv, &__nldv, __nh + offseth, &__nldh, __nipntr + offsetipntr, __nworkd + offsetworkd, &__ninfo);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, __failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!__failed) (*env)->SetFloatField(env, rnorm, floatW_val_fieldID, __nrnorm);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!__failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dnapps_)(int *n, int *kev, int *np, double *shiftr, double *shifti, double *v, int *ldv, double *h, int *ldh, double *resid, double *q, int *ldq, double *workl, double *workd);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dnappsK(JNIEnv *env, UNUSED jobject obj, jint n, jobject kev, jint np, jdoubleArray shiftr, jint offsetshiftr, jdoubleArray shifti, jint offsetshifti, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray h, jint offseth, jint ldh, jdoubleArray resid, jint offsetresid, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray workl, jint offsetworkl, jdoubleArray workd, jint offsetworkd) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nkev = 0;
  int __nnp __attribute__((aligned(8)));
  int __nldv __attribute__((aligned(8)));
  int __nldh __attribute__((aligned(8)));
  int __nldq __attribute__((aligned(8)));
  double *__nshiftr = NULL;
  double *__nshifti = NULL;
  double *__nv = NULL;
  double *__nh = NULL;
  double *__nresid = NULL;
  double *__nq = NULL;
  double *__nworkl = NULL;
  double *__nworkd = NULL;
  __nn = n;
  __nkev = (*env)->GetIntField(env, kev, intW_val_fieldID);
  __nnp = np;
  __nldv = ldv;
  __nldh = ldh;
  __nldq = ldq;
  if (!(__nshiftr = (*env)->GetPrimitiveArrayCritical(env, shiftr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nshifti = (*env)->GetPrimitiveArrayCritical(env, shifti, NULL))) { __failed = TRUE; goto done; }
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) { __failed = TRUE; goto done; }
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nq = (*env)->GetPrimitiveArrayCritical(env, q, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  dnapps_(&__nn, &__nkev, &__nnp, __nshiftr + offsetshiftr, __nshifti + offsetshifti, __nv + offsetv, &__nldv, __nh + offseth, &__nldh, __nresid + offsetresid, __nq + offsetq, &__nldq, __nworkl + offsetworkl, __nworkd + offsetworkd);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, __failed ? JNI_ABORT : 0);
  if (__nq) (*env)->ReleasePrimitiveArrayCritical(env, q, __nq, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (__nshifti) (*env)->ReleasePrimitiveArrayCritical(env, shifti, __nshifti, __failed ? JNI_ABORT : 0);
  if (__nshiftr) (*env)->ReleasePrimitiveArrayCritical(env, shiftr, __nshiftr, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, kev, intW_val_fieldID, __nkev);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*snapps_)(int *n, int *kev, int *np, float *shiftr, float *shifti, float *v, int *ldv, float *h, int *ldh, float *resid, float *q, int *ldq, float *workl, float *workd);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_snappsK(JNIEnv *env, UNUSED jobject obj, jint n, jobject kev, jint np, jfloatArray shiftr, jint offsetshiftr, jfloatArray shifti, jint offsetshifti, jfloatArray v, jint offsetv, jint ldv, jfloatArray h, jint offseth, jint ldh, jfloatArray resid, jint offsetresid, jfloatArray q, jint offsetq, jint ldq, jfloatArray workl, jint offsetworkl, jfloatArray workd, jint offsetworkd) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nkev = 0;
  int __nnp __attribute__((aligned(8)));
  int __nldv __attribute__((aligned(8)));
  int __nldh __attribute__((aligned(8)));
  int __nldq __attribute__((aligned(8)));
  float *__nshiftr = NULL;
  float *__nshifti = NULL;
  float *__nv = NULL;
  float *__nh = NULL;
  float *__nresid = NULL;
  float *__nq = NULL;
  float *__nworkl = NULL;
  float *__nworkd = NULL;
  __nn = n;
  __nkev = (*env)->GetIntField(env, kev, intW_val_fieldID);
  __nnp = np;
  __nldv = ldv;
  __nldh = ldh;
  __nldq = ldq;
  if (!(__nshiftr = (*env)->GetPrimitiveArrayCritical(env, shiftr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nshifti = (*env)->GetPrimitiveArrayCritical(env, shifti, NULL))) { __failed = TRUE; goto done; }
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) { __failed = TRUE; goto done; }
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nq = (*env)->GetPrimitiveArrayCritical(env, q, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  snapps_(&__nn, &__nkev, &__nnp, __nshiftr + offsetshiftr, __nshifti + offsetshifti, __nv + offsetv, &__nldv, __nh + offseth, &__nldh, __nresid + offsetresid, __nq + offsetq, &__nldq, __nworkl + offsetworkl, __nworkd + offsetworkd);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, __failed ? JNI_ABORT : 0);
  if (__nq) (*env)->ReleasePrimitiveArrayCritical(env, q, __nq, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (__nshifti) (*env)->ReleasePrimitiveArrayCritical(env, shifti, __nshifti, __failed ? JNI_ABORT : 0);
  if (__nshiftr) (*env)->ReleasePrimitiveArrayCritical(env, shiftr, __nshiftr, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, kev, intW_val_fieldID, __nkev);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dnaup2_)(int *ido, const char *bmat, int *n, const char *which, int *nev, int *np, double *tol, double *resid, int *mode, int *iupd, int *ishift, int *mxiter, double *v, int *ldv, double *h, int *ldh, double *ritzr, double *ritzi, double *bounds, double *q, int *ldq, double *workl, int *ipntr, double *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dnaup2K(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jstring which, jobject nev, jobject np, jdouble tol, jdoubleArray resid, jint offsetresid, jint mode, jint iupd, jint ishift, jobject mxiter, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray h, jint offseth, jint ldh, jdoubleArray ritzr, jint offsetritzr, jdoubleArray ritzi, jint offsetritzi, jdoubleArray bounds, jint offsetbounds, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray workl, jint offsetworkl, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jobject info) {
  jboolean __failed = FALSE;
  int __nido = 0;
  const char *__nbmat = NULL;
  int __nn __attribute__((aligned(8)));
  const char *__nwhich = NULL;
  int __nnev = 0;
  int __nnp = 0;
  double __ntol __attribute__((aligned(8)));
  int __nmode __attribute__((aligned(8)));
  int __niupd __attribute__((aligned(8)));
  int __nishift __attribute__((aligned(8)));
  int __nmxiter = 0;
  int __nldv __attribute__((aligned(8)));
  int __nldh __attribute__((aligned(8)));
  int __nldq __attribute__((aligned(8)));
  int __ninfo = 0;
  double *__nresid = NULL;
  double *__nv = NULL;
  double *__nh = NULL;
  double *__nritzr = NULL;
  double *__nritzi = NULL;
  double *__nbounds = NULL;
  double *__nq = NULL;
  double *__nworkl = NULL;
  int *__nipntr = NULL;
  double *__nworkd = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __nnev = (*env)->GetIntField(env, nev, intW_val_fieldID);
  __nnp = (*env)->GetIntField(env, np, intW_val_fieldID);
  __ntol = tol;
  __nmode = mode;
  __niupd = iupd;
  __nishift = ishift;
  __nmxiter = (*env)->GetIntField(env, mxiter, intW_val_fieldID);
  __nldv = ldv;
  __nldh = ldh;
  __nldq = ldq;
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) { __failed = TRUE; goto done; }
  if (!(__nritzr = (*env)->GetPrimitiveArrayCritical(env, ritzr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nritzi = (*env)->GetPrimitiveArrayCritical(env, ritzi, NULL))) { __failed = TRUE; goto done; }
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) { __failed = TRUE; goto done; }
  if (!(__nq = (*env)->GetPrimitiveArrayCritical(env, q, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) { __failed = TRUE; goto done; }
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  dnaup2_(&__nido, __nbmat, &__nn, __nwhich, &__nnev, &__nnp, &__ntol, __nresid + offsetresid, &__nmode, &__niupd, &__nishift, &__nmxiter, __nv + offsetv, &__nldv, __nh + offseth, &__nldh, __nritzr + offsetritzr, __nritzi + offsetritzi, __nbounds + offsetbounds, __nq + offsetq, &__nldq, __nworkl + offsetworkl, __nipntr + offsetipntr, __nworkd + offsetworkd, &__ninfo);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, __failed ? JNI_ABORT : 0);
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, __failed ? JNI_ABORT : 0);
  if (__nq) (*env)->ReleasePrimitiveArrayCritical(env, q, __nq, __failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, __failed ? JNI_ABORT : 0);
  if (__nritzi) (*env)->ReleasePrimitiveArrayCritical(env, ritzi, __nritzi, __failed ? JNI_ABORT : 0);
  if (__nritzr) (*env)->ReleasePrimitiveArrayCritical(env, ritzr, __nritzr, __failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!__failed) (*env)->SetIntField(env, mxiter, intW_val_fieldID, __nmxiter);
  if (!__failed) (*env)->SetIntField(env, np, intW_val_fieldID, __nnp);
  if (!__failed) (*env)->SetIntField(env, nev, intW_val_fieldID, __nnev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!__failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*snaup2_)(int *ido, const char *bmat, int *n, const char *which, int *nev, int *np, float *tol, float *resid, int *mode, int *iupd, int *ishift, int *mxiter, float *v, int *ldv, float *h, int *ldh, float *ritzr, float *ritzi, float *bounds, float *q, int *ldq, float *workl, int *ipntr, float *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_snaup2K(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jstring which, jobject nev, jobject np, jfloat tol, jfloatArray resid, jint offsetresid, jint mode, jint iupd, jint ishift, jobject mxiter, jfloatArray v, jint offsetv, jint ldv, jfloatArray h, jint offseth, jint ldh, jfloatArray ritzr, jint offsetritzr, jfloatArray ritzi, jint offsetritzi, jfloatArray bounds, jint offsetbounds, jfloatArray q, jint offsetq, jint ldq, jfloatArray workl, jint offsetworkl, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jobject info) {
  jboolean __failed = FALSE;
  int __nido = 0;
  const char *__nbmat = NULL;
  int __nn __attribute__((aligned(8)));
  const char *__nwhich = NULL;
  int __nnev = 0;
  int __nnp = 0;
  float __ntol __attribute__((aligned(8)));
  int __nmode __attribute__((aligned(8)));
  int __niupd __attribute__((aligned(8)));
  int __nishift __attribute__((aligned(8)));
  int __nmxiter = 0;
  int __nldv __attribute__((aligned(8)));
  int __nldh __attribute__((aligned(8)));
  int __nldq __attribute__((aligned(8)));
  int __ninfo = 0;
  float *__nresid = NULL;
  float *__nv = NULL;
  float *__nh = NULL;
  float *__nritzr = NULL;
  float *__nritzi = NULL;
  float *__nbounds = NULL;
  float *__nq = NULL;
  float *__nworkl = NULL;
  int *__nipntr = NULL;
  float *__nworkd = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __nnev = (*env)->GetIntField(env, nev, intW_val_fieldID);
  __nnp = (*env)->GetIntField(env, np, intW_val_fieldID);
  __ntol = tol;
  __nmode = mode;
  __niupd = iupd;
  __nishift = ishift;
  __nmxiter = (*env)->GetIntField(env, mxiter, intW_val_fieldID);
  __nldv = ldv;
  __nldh = ldh;
  __nldq = ldq;
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) { __failed = TRUE; goto done; }
  if (!(__nritzr = (*env)->GetPrimitiveArrayCritical(env, ritzr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nritzi = (*env)->GetPrimitiveArrayCritical(env, ritzi, NULL))) { __failed = TRUE; goto done; }
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) { __failed = TRUE; goto done; }
  if (!(__nq = (*env)->GetPrimitiveArrayCritical(env, q, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) { __failed = TRUE; goto done; }
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  snaup2_(&__nido, __nbmat, &__nn, __nwhich, &__nnev, &__nnp, &__ntol, __nresid + offsetresid, &__nmode, &__niupd, &__nishift, &__nmxiter, __nv + offsetv, &__nldv, __nh + offseth, &__nldh, __nritzr + offsetritzr, __nritzi + offsetritzi, __nbounds + offsetbounds, __nq + offsetq, &__nldq, __nworkl + offsetworkl, __nipntr + offsetipntr, __nworkd + offsetworkd, &__ninfo);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, __failed ? JNI_ABORT : 0);
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, __failed ? JNI_ABORT : 0);
  if (__nq) (*env)->ReleasePrimitiveArrayCritical(env, q, __nq, __failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, __failed ? JNI_ABORT : 0);
  if (__nritzi) (*env)->ReleasePrimitiveArrayCritical(env, ritzi, __nritzi, __failed ? JNI_ABORT : 0);
  if (__nritzr) (*env)->ReleasePrimitiveArrayCritical(env, ritzr, __nritzr, __failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!__failed) (*env)->SetIntField(env, mxiter, intW_val_fieldID, __nmxiter);
  if (!__failed) (*env)->SetIntField(env, np, intW_val_fieldID, __nnp);
  if (!__failed) (*env)->SetIntField(env, nev, intW_val_fieldID, __nnev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!__failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dnaupd_)(int *ido, const char *bmat, int *n, const char *which, int *nev, double *tol, double *resid, int *ncv, double *v, int *ldv, int *iparam, int *ipntr, double *workd, double *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dnaupdK(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jstring which, jint nev, jobject tol, jdoubleArray resid, jint offsetresid, jint ncv, jdoubleArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jdoubleArray workl, jint offsetworkl, jint lworkl, jobject info) {
  jboolean __failed = FALSE;
  int __nido = 0;
  const char *__nbmat = NULL;
  int __nn __attribute__((aligned(8)));
  const char *__nwhich = NULL;
  int __nnev __attribute__((aligned(8)));
  double __ntol = 0;
  int __nncv __attribute__((aligned(8)));
  int __nldv __attribute__((aligned(8)));
  int __nlworkl __attribute__((aligned(8)));
  int __ninfo = 0;
  double *__nresid = NULL;
  double *__nv = NULL;
  int *__niparam = NULL;
  int *__nipntr = NULL;
  double *__nworkd = NULL;
  double *__nworkl = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __nnev = nev;
  __ntol = (*env)->GetDoubleField(env, tol, doubleW_val_fieldID);
  __nncv = ncv;
  __nldv = ldv;
  __nlworkl = lworkl;
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__niparam = (*env)->GetPrimitiveArrayCritical(env, iparam, NULL))) { __failed = TRUE; goto done; }
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) { __failed = TRUE; goto done; }
  dnaupd_(&__nido, __nbmat, &__nn, __nwhich, &__nnev, &__ntol, __nresid + offsetresid, &__nncv, __nv + offsetv, &__nldv, __niparam + offsetiparam, __nipntr + offsetipntr, __nworkd + offsetworkd, __nworkl + offsetworkl, &__nlworkl, &__ninfo);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, __failed ? JNI_ABORT : 0);
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, __failed ? JNI_ABORT : 0);
  if (__niparam) (*env)->ReleasePrimitiveArrayCritical(env, iparam, __niparam, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!__failed) (*env)->SetDoubleField(env, tol, doubleW_val_fieldID, __ntol);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!__failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*snaupd_)(int *ido, const char *bmat, int *n, const char *which, int *nev, float *tol, float *resid, int *ncv, float *v, int *ldv, int *iparam, int *ipntr, float *workd, float *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_snaupdK(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jstring which, jint nev, jobject tol, jfloatArray resid, jint offsetresid, jint ncv, jfloatArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jfloatArray workl, jint offsetworkl, jint lworkl, jobject info) {
  jboolean __failed = FALSE;
  int __nido = 0;
  const char *__nbmat = NULL;
  int __nn __attribute__((aligned(8)));
  const char *__nwhich = NULL;
  int __nnev __attribute__((aligned(8)));
  float __ntol = 0;
  int __nncv __attribute__((aligned(8)));
  int __nldv __attribute__((aligned(8)));
  int __nlworkl __attribute__((aligned(8)));
  int __ninfo = 0;
  float *__nresid = NULL;
  float *__nv = NULL;
  int *__niparam = NULL;
  int *__nipntr = NULL;
  float *__nworkd = NULL;
  float *__nworkl = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __nnev = nev;
  __ntol = (*env)->GetFloatField(env, tol, floatW_val_fieldID);
  __nncv = ncv;
  __nldv = ldv;
  __nlworkl = lworkl;
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__niparam = (*env)->GetPrimitiveArrayCritical(env, iparam, NULL))) { __failed = TRUE; goto done; }
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) { __failed = TRUE; goto done; }
  snaupd_(&__nido, __nbmat, &__nn, __nwhich, &__nnev, &__ntol, __nresid + offsetresid, &__nncv, __nv + offsetv, &__nldv, __niparam + offsetiparam, __nipntr + offsetipntr, __nworkd + offsetworkd, __nworkl + offsetworkl, &__nlworkl, &__ninfo);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, __failed ? JNI_ABORT : 0);
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, __failed ? JNI_ABORT : 0);
  if (__niparam) (*env)->ReleasePrimitiveArrayCritical(env, iparam, __niparam, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!__failed) (*env)->SetFloatField(env, tol, floatW_val_fieldID, __ntol);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!__failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dnconv_)(int *n, double *ritzr, double *ritzi, double *bounds, double *tol, int *nconv);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dnconvK(JNIEnv *env, UNUSED jobject obj, jint n, jdoubleArray ritzr, jint offsetritzr, jdoubleArray ritzi, jint offsetritzi, jdoubleArray bounds, jint offsetbounds, jdouble tol, jobject nconv) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  double __ntol __attribute__((aligned(8)));
  int __nnconv = 0;
  double *__nritzr = NULL;
  double *__nritzi = NULL;
  double *__nbounds = NULL;
  __nn = n;
  __ntol = tol;
  __nnconv = (*env)->GetIntField(env, nconv, intW_val_fieldID);
  if (!(__nritzr = (*env)->GetPrimitiveArrayCritical(env, ritzr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nritzi = (*env)->GetPrimitiveArrayCritical(env, ritzi, NULL))) { __failed = TRUE; goto done; }
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) { __failed = TRUE; goto done; }
  dnconv_(&__nn, __nritzr + offsetritzr, __nritzi + offsetritzi, __nbounds + offsetbounds, &__ntol, &__nnconv);
done:
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, __failed ? JNI_ABORT : 0);
  if (__nritzi) (*env)->ReleasePrimitiveArrayCritical(env, ritzi, __nritzi, __failed ? JNI_ABORT : 0);
  if (__nritzr) (*env)->ReleasePrimitiveArrayCritical(env, ritzr, __nritzr, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, nconv, intW_val_fieldID, __nnconv);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*snconv_)(int *n, float *ritzr, float *ritzi, float *bounds, float *tol, int *nconv);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_snconvK(JNIEnv *env, UNUSED jobject obj, jint n, jfloatArray ritzr, jint offsetritzr, jfloatArray ritzi, jint offsetritzi, jfloatArray bounds, jint offsetbounds, jfloat tol, jobject nconv) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  float __ntol __attribute__((aligned(8)));
  int __nnconv = 0;
  float *__nritzr = NULL;
  float *__nritzi = NULL;
  float *__nbounds = NULL;
  __nn = n;
  __ntol = tol;
  __nnconv = (*env)->GetIntField(env, nconv, intW_val_fieldID);
  if (!(__nritzr = (*env)->GetPrimitiveArrayCritical(env, ritzr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nritzi = (*env)->GetPrimitiveArrayCritical(env, ritzi, NULL))) { __failed = TRUE; goto done; }
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) { __failed = TRUE; goto done; }
  snconv_(&__nn, __nritzr + offsetritzr, __nritzi + offsetritzi, __nbounds + offsetbounds, &__ntol, &__nnconv);
done:
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, __failed ? JNI_ABORT : 0);
  if (__nritzi) (*env)->ReleasePrimitiveArrayCritical(env, ritzi, __nritzi, __failed ? JNI_ABORT : 0);
  if (__nritzr) (*env)->ReleasePrimitiveArrayCritical(env, ritzr, __nritzr, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, nconv, intW_val_fieldID, __nnconv);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dsconv_)(int *n, double *ritz, double *bounds, double *tol, int *nconv);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsconvK(JNIEnv *env, UNUSED jobject obj, jint n, jdoubleArray ritz, jint offsetritz, jdoubleArray bounds, jint offsetbounds, jdouble tol, jobject nconv) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  double __ntol __attribute__((aligned(8)));
  int __nnconv = 0;
  double *__nritz = NULL;
  double *__nbounds = NULL;
  __nn = n;
  __ntol = tol;
  __nnconv = (*env)->GetIntField(env, nconv, intW_val_fieldID);
  if (!(__nritz = (*env)->GetPrimitiveArrayCritical(env, ritz, NULL))) { __failed = TRUE; goto done; }
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) { __failed = TRUE; goto done; }
  dsconv_(&__nn, __nritz + offsetritz, __nbounds + offsetbounds, &__ntol, &__nnconv);
done:
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, __failed ? JNI_ABORT : 0);
  if (__nritz) (*env)->ReleasePrimitiveArrayCritical(env, ritz, __nritz, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, nconv, intW_val_fieldID, __nnconv);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*ssconv_)(int *n, float *ritz, float *bounds, float *tol, int *nconv);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssconvK(JNIEnv *env, UNUSED jobject obj, jint n, jfloatArray ritz, jint offsetritz, jfloatArray bounds, jint offsetbounds, jfloat tol, jobject nconv) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  float __ntol __attribute__((aligned(8)));
  int __nnconv = 0;
  float *__nritz = NULL;
  float *__nbounds = NULL;
  __nn = n;
  __ntol = tol;
  __nnconv = (*env)->GetIntField(env, nconv, intW_val_fieldID);
  if (!(__nritz = (*env)->GetPrimitiveArrayCritical(env, ritz, NULL))) { __failed = TRUE; goto done; }
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) { __failed = TRUE; goto done; }
  ssconv_(&__nn, __nritz + offsetritz, __nbounds + offsetbounds, &__ntol, &__nnconv);
done:
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, __failed ? JNI_ABORT : 0);
  if (__nritz) (*env)->ReleasePrimitiveArrayCritical(env, ritz, __nritz, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, nconv, intW_val_fieldID, __nnconv);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dneigh_)(double *rnorm, int *n, double *h, int *ldh, double *ritzr, double *ritzi, double *bounds, double *q, int *ldq, double *workl, int *ierr);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dneighK(JNIEnv *env, UNUSED jobject obj, jdouble rnorm, jobject n, jdoubleArray h, jint offseth, jint ldh, jdoubleArray ritzr, jint offsetritzr, jdoubleArray ritzi, jint offsetritzi, jdoubleArray bounds, jint offsetbounds, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray workl, jint offsetworkl, jobject ierr) {
  jboolean __failed = FALSE;
  double __nrnorm __attribute__((aligned(8)));
  int __nn = 0;
  int __nldh __attribute__((aligned(8)));
  int __nldq __attribute__((aligned(8)));
  int __nierr = 0;
  double *__nh = NULL;
  double *__nritzr = NULL;
  double *__nritzi = NULL;
  double *__nbounds = NULL;
  double *__nq = NULL;
  double *__nworkl = NULL;
  __nrnorm = rnorm;
  __nn = (*env)->GetIntField(env, n, intW_val_fieldID);
  __nldh = ldh;
  __nldq = ldq;
  __nierr = (*env)->GetIntField(env, ierr, intW_val_fieldID);
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) { __failed = TRUE; goto done; }
  if (!(__nritzr = (*env)->GetPrimitiveArrayCritical(env, ritzr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nritzi = (*env)->GetPrimitiveArrayCritical(env, ritzi, NULL))) { __failed = TRUE; goto done; }
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) { __failed = TRUE; goto done; }
  if (!(__nq = (*env)->GetPrimitiveArrayCritical(env, q, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) { __failed = TRUE; goto done; }
  dneigh_(&__nrnorm, &__nn, __nh + offseth, &__nldh, __nritzr + offsetritzr, __nritzi + offsetritzi, __nbounds + offsetbounds, __nq + offsetq, &__nldq, __nworkl + offsetworkl, &__nierr);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, __failed ? JNI_ABORT : 0);
  if (__nq) (*env)->ReleasePrimitiveArrayCritical(env, q, __nq, __failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, __failed ? JNI_ABORT : 0);
  if (__nritzi) (*env)->ReleasePrimitiveArrayCritical(env, ritzi, __nritzi, __failed ? JNI_ABORT : 0);
  if (__nritzr) (*env)->ReleasePrimitiveArrayCritical(env, ritzr, __nritzr, __failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, ierr, intW_val_fieldID, __nierr);
  if (!__failed) (*env)->SetIntField(env, n, intW_val_fieldID, __nn);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*sneigh_)(float *rnorm, int *n, float *h, int *ldh, float *ritzr, float *ritzi, float *bounds, float *q, int *ldq, float *workl, int *ierr);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sneighK(JNIEnv *env, UNUSED jobject obj, jfloat rnorm, jobject n, jfloatArray h, jint offseth, jint ldh, jfloatArray ritzr, jint offsetritzr, jfloatArray ritzi, jint offsetritzi, jfloatArray bounds, jint offsetbounds, jfloatArray q, jint offsetq, jint ldq, jfloatArray workl, jint offsetworkl, jobject ierr) {
  jboolean __failed = FALSE;
  float __nrnorm __attribute__((aligned(8)));
  int __nn = 0;
  int __nldh __attribute__((aligned(8)));
  int __nldq __attribute__((aligned(8)));
  int __nierr = 0;
  float *__nh = NULL;
  float *__nritzr = NULL;
  float *__nritzi = NULL;
  float *__nbounds = NULL;
  float *__nq = NULL;
  float *__nworkl = NULL;
  __nrnorm = rnorm;
  __nn = (*env)->GetIntField(env, n, intW_val_fieldID);
  __nldh = ldh;
  __nldq = ldq;
  __nierr = (*env)->GetIntField(env, ierr, intW_val_fieldID);
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) { __failed = TRUE; goto done; }
  if (!(__nritzr = (*env)->GetPrimitiveArrayCritical(env, ritzr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nritzi = (*env)->GetPrimitiveArrayCritical(env, ritzi, NULL))) { __failed = TRUE; goto done; }
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) { __failed = TRUE; goto done; }
  if (!(__nq = (*env)->GetPrimitiveArrayCritical(env, q, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) { __failed = TRUE; goto done; }
  sneigh_(&__nrnorm, &__nn, __nh + offseth, &__nldh, __nritzr + offsetritzr, __nritzi + offsetritzi, __nbounds + offsetbounds, __nq + offsetq, &__nldq, __nworkl + offsetworkl, &__nierr);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, __failed ? JNI_ABORT : 0);
  if (__nq) (*env)->ReleasePrimitiveArrayCritical(env, q, __nq, __failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, __failed ? JNI_ABORT : 0);
  if (__nritzi) (*env)->ReleasePrimitiveArrayCritical(env, ritzi, __nritzi, __failed ? JNI_ABORT : 0);
  if (__nritzr) (*env)->ReleasePrimitiveArrayCritical(env, ritzr, __nritzr, __failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, ierr, intW_val_fieldID, __nierr);
  if (!__failed) (*env)->SetIntField(env, n, intW_val_fieldID, __nn);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dneupd_)(int *rvec, const char *howmny, int *select, double *dr, double *di, double *z, int *ldz, double *sigmar, double *sigmai, double *workev, const char *bmat, int *n, const char *which, int *nev, double *tol, double *resid, int *ncv, double *v, int *ldv, int *iparam, int *ipntr, double *workd, double *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dneupdK(JNIEnv *env, UNUSED jobject obj, jboolean rvec, jstring howmny, jbooleanArray select, jint offsetselect, jdoubleArray dr, jint offsetdr, jdoubleArray di, jint offsetdi, jdoubleArray z, jint offsetz, jint ldz, jdouble sigmar, jdouble sigmai, jdoubleArray workev, jint offsetworkev, jstring bmat, jint n, jstring which, jobject nev, jdouble tol, jdoubleArray resid, jint offsetresid, jint ncv, jdoubleArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jdoubleArray workl, jint offsetworkl, jint lworkl, jobject info) {
  jboolean __failed = FALSE;
  int __nrvec __attribute__((aligned(8)));
  const char *__nhowmny = NULL;
  int __nldz __attribute__((aligned(8)));
  double __nsigmar __attribute__((aligned(8)));
  double __nsigmai __attribute__((aligned(8)));
  const char *__nbmat = NULL;
  int __nn __attribute__((aligned(8)));
  const char *__nwhich = NULL;
  int __nnev = 0;
  double __ntol __attribute__((aligned(8)));
  int __nncv __attribute__((aligned(8)));
  int __nldv __attribute__((aligned(8)));
  int __nlworkl __attribute__((aligned(8)));
  int __ninfo = 0;
  int *__nselect = NULL; jboolean *__jselect = NULL;
  double *__ndr = NULL;
  double *__ndi = NULL;
  double *__nz = NULL;
  double *__nworkev = NULL;
  double *__nresid = NULL;
  double *__nv = NULL;
  int *__niparam = NULL;
  int *__nipntr = NULL;
  double *__nworkd = NULL;
  double *__nworkl = NULL;
  __nrvec = rvec;
  if (!(__nhowmny = (*env)->GetStringUTFChars(env, howmny, NULL))) { __failed = TRUE; goto done; }
  __nldz = ldz;
  __nsigmar = sigmar;
  __nsigmai = sigmai;
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __nnev = (*env)->GetIntField(env, nev, intW_val_fieldID);
  __ntol = tol;
  __nncv = ncv;
  __nldv = ldv;
  __nlworkl = lworkl;
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__jselect = (*env)->GetPrimitiveArrayCritical(env, select, NULL))) { __failed = TRUE; goto done; } do { int length = (*env)->GetArrayLength(env, select); if (length <= 0) { __failed = TRUE; goto done; } if (!(__nselect = malloc(sizeof(jboolean) * length))) { __failed = TRUE; goto done; } for (int i = 0; i < length; i++) { __nselect[i] = __jselect[i]; } } while(0);
  if (!(__ndr = (*env)->GetPrimitiveArrayCritical(env, dr, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndi = (*env)->GetPrimitiveArrayCritical(env, di, NULL))) { __failed = TRUE; goto done; }
  if (!(__nz = (*env)->GetPrimitiveArrayCritical(env, z, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkev = (*env)->GetPrimitiveArrayCritical(env, workev, NULL))) { __failed = TRUE; goto done; }
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__niparam = (*env)->GetPrimitiveArrayCritical(env, iparam, NULL))) { __failed = TRUE; goto done; }
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) { __failed = TRUE; goto done; }
  dneupd_(&__nrvec, __nhowmny, __nselect + offsetselect, __ndr + offsetdr, __ndi + offsetdi, __nz + offsetz, &__nldz, &__nsigmar, &__nsigmai, __nworkev + offsetworkev, __nbmat, &__nn, __nwhich, &__nnev, &__ntol, __nresid + offsetresid, &__nncv, __nv + offsetv, &__nldv, __niparam + offsetiparam, __nipntr + offsetipntr, __nworkd + offsetworkd, __nworkl + offsetworkl, &__nlworkl, &__ninfo);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, __failed ? JNI_ABORT : 0);
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, __failed ? JNI_ABORT : 0);
  if (__niparam) (*env)->ReleasePrimitiveArrayCritical(env, iparam, __niparam, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (__nworkev) (*env)->ReleasePrimitiveArrayCritical(env, workev, __nworkev, __failed ? JNI_ABORT : 0);
  if (__nz) (*env)->ReleasePrimitiveArrayCritical(env, z, __nz, __failed ? JNI_ABORT : 0);
  if (__ndi) (*env)->ReleasePrimitiveArrayCritical(env, di, __ndi, __failed ? JNI_ABORT : 0);
  if (__ndr) (*env)->ReleasePrimitiveArrayCritical(env, dr, __ndr, __failed ? JNI_ABORT : 0);
  if (__nselect) { free(__nselect); } if (__jselect) (*env)->ReleasePrimitiveArrayCritical(env, select, __nselect, JNI_ABORT);
  if (!__failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!__failed) (*env)->SetIntField(env, nev, intW_val_fieldID, __nnev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (__nhowmny) (*env)->ReleaseStringUTFChars(env, howmny, __nhowmny);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*sneupd_)(int *rvec, const char *howmny, int *select, float *dr, float *di, float *z, int *ldz, float *sigmar, float *sigmai, float *workev, const char *bmat, int *n, const char *which, int *nev, float *tol, float *resid, int *ncv, float *v, int *ldv, int *iparam, int *ipntr, float *workd, float *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sneupdK(JNIEnv *env, UNUSED jobject obj, jboolean rvec, jstring howmny, jbooleanArray select, jint offsetselect, jfloatArray dr, jint offsetdr, jfloatArray di, jint offsetdi, jfloatArray z, jint offsetz, jint ldz, jfloat sigmar, jfloat sigmai, jfloatArray workev, jint offsetworkev, jstring bmat, jint n, jstring which, jobject nev, jfloat tol, jfloatArray resid, jint offsetresid, jint ncv, jfloatArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jfloatArray workl, jint offsetworkl, jint lworkl, jobject info) {
  jboolean __failed = FALSE;
  int __nrvec __attribute__((aligned(8)));
  const char *__nhowmny = NULL;
  int __nldz __attribute__((aligned(8)));
  float __nsigmar __attribute__((aligned(8)));
  float __nsigmai __attribute__((aligned(8)));
  const char *__nbmat = NULL;
  int __nn __attribute__((aligned(8)));
  const char *__nwhich = NULL;
  int __nnev = 0;
  float __ntol __attribute__((aligned(8)));
  int __nncv __attribute__((aligned(8)));
  int __nldv __attribute__((aligned(8)));
  int __nlworkl __attribute__((aligned(8)));
  int __ninfo = 0;
  int *__nselect = NULL; jboolean *__jselect = NULL;
  float *__ndr = NULL;
  float *__ndi = NULL;
  float *__nz = NULL;
  float *__nworkev = NULL;
  float *__nresid = NULL;
  float *__nv = NULL;
  int *__niparam = NULL;
  int *__nipntr = NULL;
  float *__nworkd = NULL;
  float *__nworkl = NULL;
  __nrvec = rvec;
  if (!(__nhowmny = (*env)->GetStringUTFChars(env, howmny, NULL))) { __failed = TRUE; goto done; }
  __nldz = ldz;
  __nsigmar = sigmar;
  __nsigmai = sigmai;
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __nnev = (*env)->GetIntField(env, nev, intW_val_fieldID);
  __ntol = tol;
  __nncv = ncv;
  __nldv = ldv;
  __nlworkl = lworkl;
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__jselect = (*env)->GetPrimitiveArrayCritical(env, select, NULL))) { __failed = TRUE; goto done; } do { int length = (*env)->GetArrayLength(env, select); if (length <= 0) { __failed = TRUE; goto done; } if (!(__nselect = malloc(sizeof(jboolean) * length))) { __failed = TRUE; goto done; } for (int i = 0; i < length; i++) { __nselect[i] = __jselect[i]; } } while(0);
  if (!(__ndr = (*env)->GetPrimitiveArrayCritical(env, dr, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndi = (*env)->GetPrimitiveArrayCritical(env, di, NULL))) { __failed = TRUE; goto done; }
  if (!(__nz = (*env)->GetPrimitiveArrayCritical(env, z, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkev = (*env)->GetPrimitiveArrayCritical(env, workev, NULL))) { __failed = TRUE; goto done; }
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__niparam = (*env)->GetPrimitiveArrayCritical(env, iparam, NULL))) { __failed = TRUE; goto done; }
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) { __failed = TRUE; goto done; }
  sneupd_(&__nrvec, __nhowmny, __nselect + offsetselect, __ndr + offsetdr, __ndi + offsetdi, __nz + offsetz, &__nldz, &__nsigmar, &__nsigmai, __nworkev + offsetworkev, __nbmat, &__nn, __nwhich, &__nnev, &__ntol, __nresid + offsetresid, &__nncv, __nv + offsetv, &__nldv, __niparam + offsetiparam, __nipntr + offsetipntr, __nworkd + offsetworkd, __nworkl + offsetworkl, &__nlworkl, &__ninfo);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, __failed ? JNI_ABORT : 0);
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, __failed ? JNI_ABORT : 0);
  if (__niparam) (*env)->ReleasePrimitiveArrayCritical(env, iparam, __niparam, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (__nworkev) (*env)->ReleasePrimitiveArrayCritical(env, workev, __nworkev, __failed ? JNI_ABORT : 0);
  if (__nz) (*env)->ReleasePrimitiveArrayCritical(env, z, __nz, __failed ? JNI_ABORT : 0);
  if (__ndi) (*env)->ReleasePrimitiveArrayCritical(env, di, __ndi, __failed ? JNI_ABORT : 0);
  if (__ndr) (*env)->ReleasePrimitiveArrayCritical(env, dr, __ndr, __failed ? JNI_ABORT : 0);
  if (__nselect) { free(__nselect); } if (__jselect) (*env)->ReleasePrimitiveArrayCritical(env, select, __nselect, JNI_ABORT);
  if (!__failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!__failed) (*env)->SetIntField(env, nev, intW_val_fieldID, __nnev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (__nhowmny) (*env)->ReleaseStringUTFChars(env, howmny, __nhowmny);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dngets_)(int *ishift, const char *which, int *kev, int *np, double *ritzr, double *ritzi, double *bounds, double *shiftr, double *shifti);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dngetsK(JNIEnv *env, UNUSED jobject obj, jint ishift, jstring which, jobject kev, jobject np, jdoubleArray ritzr, jint offsetritzr, jdoubleArray ritzi, jint offsetritzi, jdoubleArray bounds, jint offsetbounds, jdoubleArray shiftr, jint offsetshiftr, jdoubleArray shifti, jint offsetshifti) {
  jboolean __failed = FALSE;
  int __nishift __attribute__((aligned(8)));
  const char *__nwhich = NULL;
  int __nkev = 0;
  int __nnp = 0;
  double *__nritzr = NULL;
  double *__nritzi = NULL;
  double *__nbounds = NULL;
  double *__nshiftr = NULL;
  double *__nshifti = NULL;
  __nishift = ishift;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __nkev = (*env)->GetIntField(env, kev, intW_val_fieldID);
  __nnp = (*env)->GetIntField(env, np, intW_val_fieldID);
  if (!(__nritzr = (*env)->GetPrimitiveArrayCritical(env, ritzr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nritzi = (*env)->GetPrimitiveArrayCritical(env, ritzi, NULL))) { __failed = TRUE; goto done; }
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) { __failed = TRUE; goto done; }
  if (!(__nshiftr = (*env)->GetPrimitiveArrayCritical(env, shiftr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nshifti = (*env)->GetPrimitiveArrayCritical(env, shifti, NULL))) { __failed = TRUE; goto done; }
  dngets_(&__nishift, __nwhich, &__nkev, &__nnp, __nritzr + offsetritzr, __nritzi + offsetritzi, __nbounds + offsetbounds, __nshiftr + offsetshiftr, __nshifti + offsetshifti);
done:
  if (__nshifti) (*env)->ReleasePrimitiveArrayCritical(env, shifti, __nshifti, __failed ? JNI_ABORT : 0);
  if (__nshiftr) (*env)->ReleasePrimitiveArrayCritical(env, shiftr, __nshiftr, __failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, __failed ? JNI_ABORT : 0);
  if (__nritzi) (*env)->ReleasePrimitiveArrayCritical(env, ritzi, __nritzi, __failed ? JNI_ABORT : 0);
  if (__nritzr) (*env)->ReleasePrimitiveArrayCritical(env, ritzr, __nritzr, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, np, intW_val_fieldID, __nnp);
  if (!__failed) (*env)->SetIntField(env, kev, intW_val_fieldID, __nkev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*sngets_)(int *ishift, const char *which, int *kev, int *np, float *ritzr, float *ritzi, float *bounds, float *shiftr, float *shifti);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sngetsK(JNIEnv *env, UNUSED jobject obj, jint ishift, jstring which, jobject kev, jobject np, jfloatArray ritzr, jint offsetritzr, jfloatArray ritzi, jint offsetritzi, jfloatArray bounds, jint offsetbounds, jfloatArray shiftr, jint offsetshiftr, jfloatArray shifti, jint offsetshifti) {
  jboolean __failed = FALSE;
  int __nishift __attribute__((aligned(8)));
  const char *__nwhich = NULL;
  int __nkev = 0;
  int __nnp = 0;
  float *__nritzr = NULL;
  float *__nritzi = NULL;
  float *__nbounds = NULL;
  float *__nshiftr = NULL;
  float *__nshifti = NULL;
  __nishift = ishift;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __nkev = (*env)->GetIntField(env, kev, intW_val_fieldID);
  __nnp = (*env)->GetIntField(env, np, intW_val_fieldID);
  if (!(__nritzr = (*env)->GetPrimitiveArrayCritical(env, ritzr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nritzi = (*env)->GetPrimitiveArrayCritical(env, ritzi, NULL))) { __failed = TRUE; goto done; }
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) { __failed = TRUE; goto done; }
  if (!(__nshiftr = (*env)->GetPrimitiveArrayCritical(env, shiftr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nshifti = (*env)->GetPrimitiveArrayCritical(env, shifti, NULL))) { __failed = TRUE; goto done; }
  sngets_(&__nishift, __nwhich, &__nkev, &__nnp, __nritzr + offsetritzr, __nritzi + offsetritzi, __nbounds + offsetbounds, __nshiftr + offsetshiftr, __nshifti + offsetshifti);
done:
  if (__nshifti) (*env)->ReleasePrimitiveArrayCritical(env, shifti, __nshifti, __failed ? JNI_ABORT : 0);
  if (__nshiftr) (*env)->ReleasePrimitiveArrayCritical(env, shiftr, __nshiftr, __failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, __failed ? JNI_ABORT : 0);
  if (__nritzi) (*env)->ReleasePrimitiveArrayCritical(env, ritzi, __nritzi, __failed ? JNI_ABORT : 0);
  if (__nritzr) (*env)->ReleasePrimitiveArrayCritical(env, ritzr, __nritzr, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, np, intW_val_fieldID, __nnp);
  if (!__failed) (*env)->SetIntField(env, kev, intW_val_fieldID, __nkev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dsaitr_)(int *ido, const char *bmat, int *n, int *k, int *np, int *mode, double *resid, double *rnorm, double *v, int *ldv, double *h, int *ldh, int *ipntr, double *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsaitrK(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jint k, jint np, jint mode, jdoubleArray resid, jint offsetresid, jobject rnorm, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray h, jint offseth, jint ldh, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jobject info) {
  jboolean __failed = FALSE;
  int __nido = 0;
  const char *__nbmat = NULL;
  int __nn __attribute__((aligned(8)));
  int __nk __attribute__((aligned(8)));
  int __nnp __attribute__((aligned(8)));
  int __nmode __attribute__((aligned(8)));
  double __nrnorm = 0;
  int __nldv __attribute__((aligned(8)));
  int __nldh __attribute__((aligned(8)));
  int __ninfo = 0;
  double *__nresid = NULL;
  double *__nv = NULL;
  double *__nh = NULL;
  int *__nipntr = NULL;
  double *__nworkd = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nk = k;
  __nnp = np;
  __nmode = mode;
  __nrnorm = (*env)->GetDoubleField(env, rnorm, doubleW_val_fieldID);
  __nldv = ldv;
  __nldh = ldh;
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) { __failed = TRUE; goto done; }
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  dsaitr_(&__nido, __nbmat, &__nn, &__nk, &__nnp, &__nmode, __nresid + offsetresid, &__nrnorm, __nv + offsetv, &__nldv, __nh + offseth, &__nldh, __nipntr + offsetipntr, __nworkd + offsetworkd, &__ninfo);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, __failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!__failed) (*env)->SetDoubleField(env, rnorm, doubleW_val_fieldID, __nrnorm);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!__failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*ssaitr_)(int *ido, const char *bmat, int *n, int *k, int *np, int *mode, float *resid, float *rnorm, float *v, int *ldv, float *h, int *ldh, int *ipntr, float *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssaitrK(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jint k, jint np, jint mode, jfloatArray resid, jint offsetresid, jobject rnorm, jfloatArray v, jint offsetv, jint ldv, jfloatArray h, jint offseth, jint ldh, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jobject info) {
  jboolean __failed = FALSE;
  int __nido = 0;
  const char *__nbmat = NULL;
  int __nn __attribute__((aligned(8)));
  int __nk __attribute__((aligned(8)));
  int __nnp __attribute__((aligned(8)));
  int __nmode __attribute__((aligned(8)));
  float __nrnorm = 0;
  int __nldv __attribute__((aligned(8)));
  int __nldh __attribute__((aligned(8)));
  int __ninfo = 0;
  float *__nresid = NULL;
  float *__nv = NULL;
  float *__nh = NULL;
  int *__nipntr = NULL;
  float *__nworkd = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nk = k;
  __nnp = np;
  __nmode = mode;
  __nrnorm = (*env)->GetFloatField(env, rnorm, floatW_val_fieldID);
  __nldv = ldv;
  __nldh = ldh;
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) { __failed = TRUE; goto done; }
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  ssaitr_(&__nido, __nbmat, &__nn, &__nk, &__nnp, &__nmode, __nresid + offsetresid, &__nrnorm, __nv + offsetv, &__nldv, __nh + offseth, &__nldh, __nipntr + offsetipntr, __nworkd + offsetworkd, &__ninfo);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, __failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!__failed) (*env)->SetFloatField(env, rnorm, floatW_val_fieldID, __nrnorm);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!__failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dsapps_)(int *n, int *kev, int *np, double *shift, double *v, int *ldv, double *h, int *ldh, double *resid, double *q, int *ldq, double *workd);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsappsK(JNIEnv *env, UNUSED jobject obj, jint n, jint kev, jint np, jdoubleArray shift, jint offsetshift, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray h, jint offseth, jint ldh, jdoubleArray resid, jint offsetresid, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray workd, jint offsetworkd) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nkev __attribute__((aligned(8)));
  int __nnp __attribute__((aligned(8)));
  int __nldv __attribute__((aligned(8)));
  int __nldh __attribute__((aligned(8)));
  int __nldq __attribute__((aligned(8)));
  double *__nshift = NULL;
  double *__nv = NULL;
  double *__nh = NULL;
  double *__nresid = NULL;
  double *__nq = NULL;
  double *__nworkd = NULL;
  __nn = n;
  __nkev = kev;
  __nnp = np;
  __nldv = ldv;
  __nldh = ldh;
  __nldq = ldq;
  if (!(__nshift = (*env)->GetPrimitiveArrayCritical(env, shift, NULL))) { __failed = TRUE; goto done; }
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) { __failed = TRUE; goto done; }
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nq = (*env)->GetPrimitiveArrayCritical(env, q, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  dsapps_(&__nn, &__nkev, &__nnp, __nshift + offsetshift, __nv + offsetv, &__nldv, __nh + offseth, &__nldh, __nresid + offsetresid, __nq + offsetq, &__nldq, __nworkd + offsetworkd);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nq) (*env)->ReleasePrimitiveArrayCritical(env, q, __nq, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (__nshift) (*env)->ReleasePrimitiveArrayCritical(env, shift, __nshift, __failed ? JNI_ABORT : 0);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*ssapps_)(int *n, int *kev, int *np, float *shift, float *v, int *ldv, float *h, int *ldh, float *resid, float *q, int *ldq, float *workd);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssappsK(JNIEnv *env, UNUSED jobject obj, jint n, jint kev, jint np, jfloatArray shift, jint offsetshift, jfloatArray v, jint offsetv, jint ldv, jfloatArray h, jint offseth, jint ldh, jfloatArray resid, jint offsetresid, jfloatArray q, jint offsetq, jint ldq, jfloatArray workd, jint offsetworkd) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nkev __attribute__((aligned(8)));
  int __nnp __attribute__((aligned(8)));
  int __nldv __attribute__((aligned(8)));
  int __nldh __attribute__((aligned(8)));
  int __nldq __attribute__((aligned(8)));
  float *__nshift = NULL;
  float *__nv = NULL;
  float *__nh = NULL;
  float *__nresid = NULL;
  float *__nq = NULL;
  float *__nworkd = NULL;
  __nn = n;
  __nkev = kev;
  __nnp = np;
  __nldv = ldv;
  __nldh = ldh;
  __nldq = ldq;
  if (!(__nshift = (*env)->GetPrimitiveArrayCritical(env, shift, NULL))) { __failed = TRUE; goto done; }
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) { __failed = TRUE; goto done; }
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nq = (*env)->GetPrimitiveArrayCritical(env, q, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  ssapps_(&__nn, &__nkev, &__nnp, __nshift + offsetshift, __nv + offsetv, &__nldv, __nh + offseth, &__nldh, __nresid + offsetresid, __nq + offsetq, &__nldq, __nworkd + offsetworkd);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nq) (*env)->ReleasePrimitiveArrayCritical(env, q, __nq, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (__nshift) (*env)->ReleasePrimitiveArrayCritical(env, shift, __nshift, __failed ? JNI_ABORT : 0);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dsaup2_)(int *ido, const char *bmat, int *n, const char *which, int *nev, int *np, double *tol, double *resid, int *mode, int *iupd, int *ishift, int *mxiter, double *v, int *ldv, double *h, int *ldh, double *ritz, double *bounds, double *q, int *ldq, double *workl, int *ipntr, double *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsaup2K(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jstring which, jobject nev, jobject np, jdouble tol, jdoubleArray resid, jint offsetresid, jint mode, jint iupd, jint ishift, jobject mxiter, jdoubleArray v, jint offsetv, jint ldv, jdoubleArray h, jint offseth, jint ldh, jdoubleArray ritz, jint offsetritz, jdoubleArray bounds, jint offsetbounds, jdoubleArray q, jint offsetq, jint ldq, jdoubleArray workl, jint offsetworkl, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jobject info) {
  jboolean __failed = FALSE;
  int __nido = 0;
  const char *__nbmat = NULL;
  int __nn __attribute__((aligned(8)));
  const char *__nwhich = NULL;
  int __nnev = 0;
  int __nnp = 0;
  double __ntol __attribute__((aligned(8)));
  int __nmode __attribute__((aligned(8)));
  int __niupd __attribute__((aligned(8)));
  int __nishift __attribute__((aligned(8)));
  int __nmxiter = 0;
  int __nldv __attribute__((aligned(8)));
  int __nldh __attribute__((aligned(8)));
  int __nldq __attribute__((aligned(8)));
  int __ninfo = 0;
  double *__nresid = NULL;
  double *__nv = NULL;
  double *__nh = NULL;
  double *__nritz = NULL;
  double *__nbounds = NULL;
  double *__nq = NULL;
  double *__nworkl = NULL;
  int *__nipntr = NULL;
  double *__nworkd = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __nnev = (*env)->GetIntField(env, nev, intW_val_fieldID);
  __nnp = (*env)->GetIntField(env, np, intW_val_fieldID);
  __ntol = tol;
  __nmode = mode;
  __niupd = iupd;
  __nishift = ishift;
  __nmxiter = (*env)->GetIntField(env, mxiter, intW_val_fieldID);
  __nldv = ldv;
  __nldh = ldh;
  __nldq = ldq;
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) { __failed = TRUE; goto done; }
  if (!(__nritz = (*env)->GetPrimitiveArrayCritical(env, ritz, NULL))) { __failed = TRUE; goto done; }
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) { __failed = TRUE; goto done; }
  if (!(__nq = (*env)->GetPrimitiveArrayCritical(env, q, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) { __failed = TRUE; goto done; }
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  dsaup2_(&__nido, __nbmat, &__nn, __nwhich, &__nnev, &__nnp, &__ntol, __nresid + offsetresid, &__nmode, &__niupd, &__nishift, &__nmxiter, __nv + offsetv, &__nldv, __nh + offseth, &__nldh, __nritz + offsetritz, __nbounds + offsetbounds, __nq + offsetq, &__nldq, __nworkl + offsetworkl, __nipntr + offsetipntr, __nworkd + offsetworkd, &__ninfo);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, __failed ? JNI_ABORT : 0);
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, __failed ? JNI_ABORT : 0);
  if (__nq) (*env)->ReleasePrimitiveArrayCritical(env, q, __nq, __failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, __failed ? JNI_ABORT : 0);
  if (__nritz) (*env)->ReleasePrimitiveArrayCritical(env, ritz, __nritz, __failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!__failed) (*env)->SetIntField(env, mxiter, intW_val_fieldID, __nmxiter);
  if (!__failed) (*env)->SetIntField(env, np, intW_val_fieldID, __nnp);
  if (!__failed) (*env)->SetIntField(env, nev, intW_val_fieldID, __nnev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!__failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*ssaup2_)(int *ido, const char *bmat, int *n, const char *which, int *nev, int *np, float *tol, float *resid, int *mode, int *iupd, int *ishift, int *mxiter, float *v, int *ldv, float *h, int *ldh, float *ritz, float *bounds, float *q, int *ldq, float *workl, int *ipntr, float *workd, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssaup2K(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jstring which, jobject nev, jobject np, jfloat tol, jfloatArray resid, jint offsetresid, jint mode, jint iupd, jint ishift, jobject mxiter, jfloatArray v, jint offsetv, jint ldv, jfloatArray h, jint offseth, jint ldh, jfloatArray ritz, jint offsetritz, jfloatArray bounds, jint offsetbounds, jfloatArray q, jint offsetq, jint ldq, jfloatArray workl, jint offsetworkl, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jobject info) {
  jboolean __failed = FALSE;
  int __nido = 0;
  const char *__nbmat = NULL;
  int __nn __attribute__((aligned(8)));
  const char *__nwhich = NULL;
  int __nnev = 0;
  int __nnp = 0;
  float __ntol __attribute__((aligned(8)));
  int __nmode __attribute__((aligned(8)));
  int __niupd __attribute__((aligned(8)));
  int __nishift __attribute__((aligned(8)));
  int __nmxiter = 0;
  int __nldv __attribute__((aligned(8)));
  int __nldh __attribute__((aligned(8)));
  int __nldq __attribute__((aligned(8)));
  int __ninfo = 0;
  float *__nresid = NULL;
  float *__nv = NULL;
  float *__nh = NULL;
  float *__nritz = NULL;
  float *__nbounds = NULL;
  float *__nq = NULL;
  float *__nworkl = NULL;
  int *__nipntr = NULL;
  float *__nworkd = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __nnev = (*env)->GetIntField(env, nev, intW_val_fieldID);
  __nnp = (*env)->GetIntField(env, np, intW_val_fieldID);
  __ntol = tol;
  __nmode = mode;
  __niupd = iupd;
  __nishift = ishift;
  __nmxiter = (*env)->GetIntField(env, mxiter, intW_val_fieldID);
  __nldv = ldv;
  __nldh = ldh;
  __nldq = ldq;
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) { __failed = TRUE; goto done; }
  if (!(__nritz = (*env)->GetPrimitiveArrayCritical(env, ritz, NULL))) { __failed = TRUE; goto done; }
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) { __failed = TRUE; goto done; }
  if (!(__nq = (*env)->GetPrimitiveArrayCritical(env, q, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) { __failed = TRUE; goto done; }
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  ssaup2_(&__nido, __nbmat, &__nn, __nwhich, &__nnev, &__nnp, &__ntol, __nresid + offsetresid, &__nmode, &__niupd, &__nishift, &__nmxiter, __nv + offsetv, &__nldv, __nh + offseth, &__nldh, __nritz + offsetritz, __nbounds + offsetbounds, __nq + offsetq, &__nldq, __nworkl + offsetworkl, __nipntr + offsetipntr, __nworkd + offsetworkd, &__ninfo);
done:
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, __failed ? JNI_ABORT : 0);
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, __failed ? JNI_ABORT : 0);
  if (__nq) (*env)->ReleasePrimitiveArrayCritical(env, q, __nq, __failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, __failed ? JNI_ABORT : 0);
  if (__nritz) (*env)->ReleasePrimitiveArrayCritical(env, ritz, __nritz, __failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!__failed) (*env)->SetIntField(env, mxiter, intW_val_fieldID, __nmxiter);
  if (!__failed) (*env)->SetIntField(env, np, intW_val_fieldID, __nnp);
  if (!__failed) (*env)->SetIntField(env, nev, intW_val_fieldID, __nnev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!__failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dseigt_)(double *rnorm, int *n, double *h, int *ldh, double *eig, double *bounds, double *workl, int *ierr);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dseigtK(JNIEnv *env, UNUSED jobject obj, jdouble rnorm, jint n, jdoubleArray h, jint offseth, jint ldh, jdoubleArray eig, jint offseteig, jdoubleArray bounds, jint offsetbounds, jdoubleArray workl, jint offsetworkl, jobject ierr) {
  jboolean __failed = FALSE;
  double __nrnorm __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  int __nldh __attribute__((aligned(8)));
  int __nierr = 0;
  double *__nh = NULL;
  double *__neig = NULL;
  double *__nbounds = NULL;
  double *__nworkl = NULL;
  __nrnorm = rnorm;
  __nn = n;
  __nldh = ldh;
  __nierr = (*env)->GetIntField(env, ierr, intW_val_fieldID);
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) { __failed = TRUE; goto done; }
  if (!(__neig = (*env)->GetPrimitiveArrayCritical(env, eig, NULL))) { __failed = TRUE; goto done; }
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) { __failed = TRUE; goto done; }
  dseigt_(&__nrnorm, &__nn, __nh + offseth, &__nldh, __neig + offseteig, __nbounds + offsetbounds, __nworkl + offsetworkl, &__nierr);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, __failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, __failed ? JNI_ABORT : 0);
  if (__neig) (*env)->ReleasePrimitiveArrayCritical(env, eig, __neig, __failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, ierr, intW_val_fieldID, __nierr);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*sseigt_)(float *rnorm, int *n, float *h, int *ldh, float *eig, float *bounds, float *workl, int *ierr);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sseigtK(JNIEnv *env, UNUSED jobject obj, jfloat rnorm, jint n, jfloatArray h, jint offseth, jint ldh, jfloatArray eig, jint offseteig, jfloatArray bounds, jint offsetbounds, jfloatArray workl, jint offsetworkl, jobject ierr) {
  jboolean __failed = FALSE;
  float __nrnorm __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  int __nldh __attribute__((aligned(8)));
  int __nierr = 0;
  float *__nh = NULL;
  float *__neig = NULL;
  float *__nbounds = NULL;
  float *__nworkl = NULL;
  __nrnorm = rnorm;
  __nn = n;
  __nldh = ldh;
  __nierr = (*env)->GetIntField(env, ierr, intW_val_fieldID);
  if (!(__nh = (*env)->GetPrimitiveArrayCritical(env, h, NULL))) { __failed = TRUE; goto done; }
  if (!(__neig = (*env)->GetPrimitiveArrayCritical(env, eig, NULL))) { __failed = TRUE; goto done; }
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) { __failed = TRUE; goto done; }
  sseigt_(&__nrnorm, &__nn, __nh + offseth, &__nldh, __neig + offseteig, __nbounds + offsetbounds, __nworkl + offsetworkl, &__nierr);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, __failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, __failed ? JNI_ABORT : 0);
  if (__neig) (*env)->ReleasePrimitiveArrayCritical(env, eig, __neig, __failed ? JNI_ABORT : 0);
  if (__nh) (*env)->ReleasePrimitiveArrayCritical(env, h, __nh, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, ierr, intW_val_fieldID, __nierr);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dsesrt_)(const char *which, int *apply, int *n, double *x, int *na, double *a, int *lda);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsesrtK(JNIEnv *env, UNUSED jobject obj, jstring which, jboolean apply, jint n, jdoubleArray x, jint offsetx, jint na, jdoubleArray a, jint offseta, jint lda) {
  jboolean __failed = FALSE;
  const char *__nwhich = NULL;
  int __napply __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  int __nna __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  double *__nx = NULL;
  double *__na = NULL;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __napply = apply;
  __nn = n;
  __nna = na;
  __nlda = lda;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  dsesrt_(__nwhich, &__napply, &__nn, __nx + offsetx, &__nna, __na + offseta, &__nlda);
done:
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, __failed ? JNI_ABORT : 0);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*ssesrt_)(const char *which, int *apply, int *n, float *x, int *na, float *a, int *lda);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssesrtK(JNIEnv *env, UNUSED jobject obj, jstring which, jboolean apply, jint n, jfloatArray x, jint offsetx, jint na, jfloatArray a, jint offseta, jint lda) {
  jboolean __failed = FALSE;
  const char *__nwhich = NULL;
  int __napply __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  int __nna __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  float *__nx = NULL;
  float *__na = NULL;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __napply = apply;
  __nn = n;
  __nna = na;
  __nlda = lda;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  ssesrt_(__nwhich, &__napply, &__nn, __nx + offsetx, &__nna, __na + offseta, &__nlda);
done:
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, __failed ? JNI_ABORT : 0);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dsaupd_)(int *ido, const char *bmat, int *n, const char *which, int *nev, double *tol, double *resid, int *ncv, double *v, int *ldv, int *iparam, int *ipntr, double *workd, double *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsaupdK(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jstring which, jint nev, jobject tol, jdoubleArray resid, jint offsetresid, jint ncv, jdoubleArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jdoubleArray workl, jint offsetworkl, jint lworkl, jobject info) {
  jboolean __failed = FALSE;
  int __nido = 0;
  const char *__nbmat = NULL;
  int __nn __attribute__((aligned(8)));
  const char *__nwhich = NULL;
  int __nnev __attribute__((aligned(8)));
  double __ntol = 0;
  int __nncv __attribute__((aligned(8)));
  int __nldv __attribute__((aligned(8)));
  int __nlworkl __attribute__((aligned(8)));
  int __ninfo = 0;
  double *__nresid = NULL;
  double *__nv = NULL;
  int *__niparam = NULL;
  int *__nipntr = NULL;
  double *__nworkd = NULL;
  double *__nworkl = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __nnev = nev;
  __ntol = (*env)->GetDoubleField(env, tol, doubleW_val_fieldID);
  __nncv = ncv;
  __nldv = ldv;
  __nlworkl = lworkl;
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__niparam = (*env)->GetPrimitiveArrayCritical(env, iparam, NULL))) { __failed = TRUE; goto done; }
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) { __failed = TRUE; goto done; }
  dsaupd_(&__nido, __nbmat, &__nn, __nwhich, &__nnev, &__ntol, __nresid + offsetresid, &__nncv, __nv + offsetv, &__nldv, __niparam + offsetiparam, __nipntr + offsetipntr, __nworkd + offsetworkd, __nworkl + offsetworkl, &__nlworkl, &__ninfo);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, __failed ? JNI_ABORT : 0);
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, __failed ? JNI_ABORT : 0);
  if (__niparam) (*env)->ReleasePrimitiveArrayCritical(env, iparam, __niparam, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!__failed) (*env)->SetDoubleField(env, tol, doubleW_val_fieldID, __ntol);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!__failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*ssaupd_)(int *ido, const char *bmat, int *n, const char *which, int *nev, float *tol, float *resid, int *ncv, float *v, int *ldv, int *iparam, int *ipntr, float *workd, float *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssaupdK(JNIEnv *env, UNUSED jobject obj, jobject ido, jstring bmat, jint n, jstring which, jint nev, jobject tol, jfloatArray resid, jint offsetresid, jint ncv, jfloatArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jfloatArray workl, jint offsetworkl, jint lworkl, jobject info) {
  jboolean __failed = FALSE;
  int __nido = 0;
  const char *__nbmat = NULL;
  int __nn __attribute__((aligned(8)));
  const char *__nwhich = NULL;
  int __nnev __attribute__((aligned(8)));
  float __ntol = 0;
  int __nncv __attribute__((aligned(8)));
  int __nldv __attribute__((aligned(8)));
  int __nlworkl __attribute__((aligned(8)));
  int __ninfo = 0;
  float *__nresid = NULL;
  float *__nv = NULL;
  int *__niparam = NULL;
  int *__nipntr = NULL;
  float *__nworkd = NULL;
  float *__nworkl = NULL;
  __nido = (*env)->GetIntField(env, ido, intW_val_fieldID);
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __nnev = nev;
  __ntol = (*env)->GetFloatField(env, tol, floatW_val_fieldID);
  __nncv = ncv;
  __nldv = ldv;
  __nlworkl = lworkl;
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__niparam = (*env)->GetPrimitiveArrayCritical(env, iparam, NULL))) { __failed = TRUE; goto done; }
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) { __failed = TRUE; goto done; }
  ssaupd_(&__nido, __nbmat, &__nn, __nwhich, &__nnev, &__ntol, __nresid + offsetresid, &__nncv, __nv + offsetv, &__nldv, __niparam + offsetiparam, __nipntr + offsetipntr, __nworkd + offsetworkd, __nworkl + offsetworkl, &__nlworkl, &__ninfo);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, __failed ? JNI_ABORT : 0);
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, __failed ? JNI_ABORT : 0);
  if (__niparam) (*env)->ReleasePrimitiveArrayCritical(env, iparam, __niparam, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!__failed) (*env)->SetFloatField(env, tol, floatW_val_fieldID, __ntol);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (!__failed) (*env)->SetIntField(env, ido, intW_val_fieldID, __nido);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dseupd_)(int *rvec, const char *howmny, int *select, double *d, double *z, int *ldz, double *sigma, const char *bmat, int *n, const char *which, int *nev, double *tol, double *resid, int *ncv, double *v, int *ldv, int *iparam, int *ipntr, double *workd, double *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dseupdK(JNIEnv *env, UNUSED jobject obj, jboolean rvec, jstring howmny, jbooleanArray select, jint offsetselect, jdoubleArray d, jint offsetd, jdoubleArray z, jint offsetz, jint ldz, jdouble sigma, jstring bmat, jint n, jstring which, jobject nev, jdouble tol, jdoubleArray resid, jint offsetresid, jint ncv, jdoubleArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jdoubleArray workd, jint offsetworkd, jdoubleArray workl, jint offsetworkl, jint lworkl, jobject info) {
  jboolean __failed = FALSE;
  int __nrvec __attribute__((aligned(8)));
  const char *__nhowmny = NULL;
  int __nldz __attribute__((aligned(8)));
  double __nsigma __attribute__((aligned(8)));
  const char *__nbmat = NULL;
  int __nn __attribute__((aligned(8)));
  const char *__nwhich = NULL;
  int __nnev = 0;
  double __ntol __attribute__((aligned(8)));
  int __nncv __attribute__((aligned(8)));
  int __nldv __attribute__((aligned(8)));
  int __nlworkl __attribute__((aligned(8)));
  int __ninfo = 0;
  int *__nselect = NULL; jboolean *__jselect = NULL;
  double *__nd = NULL;
  double *__nz = NULL;
  double *__nresid = NULL;
  double *__nv = NULL;
  int *__niparam = NULL;
  int *__nipntr = NULL;
  double *__nworkd = NULL;
  double *__nworkl = NULL;
  __nrvec = rvec;
  if (!(__nhowmny = (*env)->GetStringUTFChars(env, howmny, NULL))) { __failed = TRUE; goto done; }
  __nldz = ldz;
  __nsigma = sigma;
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __nnev = (*env)->GetIntField(env, nev, intW_val_fieldID);
  __ntol = tol;
  __nncv = ncv;
  __nldv = ldv;
  __nlworkl = lworkl;
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__jselect = (*env)->GetPrimitiveArrayCritical(env, select, NULL))) { __failed = TRUE; goto done; } do { int length = (*env)->GetArrayLength(env, select); if (length <= 0) { __failed = TRUE; goto done; } if (!(__nselect = malloc(sizeof(jboolean) * length))) { __failed = TRUE; goto done; } for (int i = 0; i < length; i++) { __nselect[i] = __jselect[i]; } } while(0);
  if (!(__nd = (*env)->GetPrimitiveArrayCritical(env, d, NULL))) { __failed = TRUE; goto done; }
  if (!(__nz = (*env)->GetPrimitiveArrayCritical(env, z, NULL))) { __failed = TRUE; goto done; }
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__niparam = (*env)->GetPrimitiveArrayCritical(env, iparam, NULL))) { __failed = TRUE; goto done; }
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) { __failed = TRUE; goto done; }
  dseupd_(&__nrvec, __nhowmny, __nselect + offsetselect, __nd + offsetd, __nz + offsetz, &__nldz, &__nsigma, __nbmat, &__nn, __nwhich, &__nnev, &__ntol, __nresid + offsetresid, &__nncv, __nv + offsetv, &__nldv, __niparam + offsetiparam, __nipntr + offsetipntr, __nworkd + offsetworkd, __nworkl + offsetworkl, &__nlworkl, &__ninfo);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, __failed ? JNI_ABORT : 0);
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, __failed ? JNI_ABORT : 0);
  if (__niparam) (*env)->ReleasePrimitiveArrayCritical(env, iparam, __niparam, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (__nz) (*env)->ReleasePrimitiveArrayCritical(env, z, __nz, __failed ? JNI_ABORT : 0);
  if (__nd) (*env)->ReleasePrimitiveArrayCritical(env, d, __nd, __failed ? JNI_ABORT : 0);
  if (__nselect) { free(__nselect); } if (__jselect) (*env)->ReleasePrimitiveArrayCritical(env, select, __nselect, JNI_ABORT);
  if (!__failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!__failed) (*env)->SetIntField(env, nev, intW_val_fieldID, __nnev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (__nhowmny) (*env)->ReleaseStringUTFChars(env, howmny, __nhowmny);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*sseupd_)(int *rvec, const char *howmny, int *select, float *d, float *z, int *ldz, float *sigma, const char *bmat, int *n, const char *which, int *nev, float *tol, float *resid, int *ncv, float *v, int *ldv, int *iparam, int *ipntr, float *workd, float *workl, int *lworkl, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sseupdK(JNIEnv *env, UNUSED jobject obj, jboolean rvec, jstring howmny, jbooleanArray select, jint offsetselect, jfloatArray d, jint offsetd, jfloatArray z, jint offsetz, jint ldz, jfloat sigma, jstring bmat, jint n, jstring which, jobject nev, jfloat tol, jfloatArray resid, jint offsetresid, jint ncv, jfloatArray v, jint offsetv, jint ldv, jintArray iparam, jint offsetiparam, jintArray ipntr, jint offsetipntr, jfloatArray workd, jint offsetworkd, jfloatArray workl, jint offsetworkl, jint lworkl, jobject info) {
  jboolean __failed = FALSE;
  int __nrvec __attribute__((aligned(8)));
  const char *__nhowmny = NULL;
  int __nldz __attribute__((aligned(8)));
  float __nsigma __attribute__((aligned(8)));
  const char *__nbmat = NULL;
  int __nn __attribute__((aligned(8)));
  const char *__nwhich = NULL;
  int __nnev = 0;
  float __ntol __attribute__((aligned(8)));
  int __nncv __attribute__((aligned(8)));
  int __nldv __attribute__((aligned(8)));
  int __nlworkl __attribute__((aligned(8)));
  int __ninfo = 0;
  int *__nselect = NULL; jboolean *__jselect = NULL;
  float *__nd = NULL;
  float *__nz = NULL;
  float *__nresid = NULL;
  float *__nv = NULL;
  int *__niparam = NULL;
  int *__nipntr = NULL;
  float *__nworkd = NULL;
  float *__nworkl = NULL;
  __nrvec = rvec;
  if (!(__nhowmny = (*env)->GetStringUTFChars(env, howmny, NULL))) { __failed = TRUE; goto done; }
  __nldz = ldz;
  __nsigma = sigma;
  if (!(__nbmat = (*env)->GetStringUTFChars(env, bmat, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __nnev = (*env)->GetIntField(env, nev, intW_val_fieldID);
  __ntol = tol;
  __nncv = ncv;
  __nldv = ldv;
  __nlworkl = lworkl;
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__jselect = (*env)->GetPrimitiveArrayCritical(env, select, NULL))) { __failed = TRUE; goto done; } do { int length = (*env)->GetArrayLength(env, select); if (length <= 0) { __failed = TRUE; goto done; } if (!(__nselect = malloc(sizeof(jboolean) * length))) { __failed = TRUE; goto done; } for (int i = 0; i < length; i++) { __nselect[i] = __jselect[i]; } } while(0);
  if (!(__nd = (*env)->GetPrimitiveArrayCritical(env, d, NULL))) { __failed = TRUE; goto done; }
  if (!(__nz = (*env)->GetPrimitiveArrayCritical(env, z, NULL))) { __failed = TRUE; goto done; }
  if (!(__nresid = (*env)->GetPrimitiveArrayCritical(env, resid, NULL))) { __failed = TRUE; goto done; }
  if (!(__nv = (*env)->GetPrimitiveArrayCritical(env, v, NULL))) { __failed = TRUE; goto done; }
  if (!(__niparam = (*env)->GetPrimitiveArrayCritical(env, iparam, NULL))) { __failed = TRUE; goto done; }
  if (!(__nipntr = (*env)->GetPrimitiveArrayCritical(env, ipntr, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkd = (*env)->GetPrimitiveArrayCritical(env, workd, NULL))) { __failed = TRUE; goto done; }
  if (!(__nworkl = (*env)->GetPrimitiveArrayCritical(env, workl, NULL))) { __failed = TRUE; goto done; }
  sseupd_(&__nrvec, __nhowmny, __nselect + offsetselect, __nd + offsetd, __nz + offsetz, &__nldz, &__nsigma, __nbmat, &__nn, __nwhich, &__nnev, &__ntol, __nresid + offsetresid, &__nncv, __nv + offsetv, &__nldv, __niparam + offsetiparam, __nipntr + offsetipntr, __nworkd + offsetworkd, __nworkl + offsetworkl, &__nlworkl, &__ninfo);
done:
  if (__nworkl) (*env)->ReleasePrimitiveArrayCritical(env, workl, __nworkl, __failed ? JNI_ABORT : 0);
  if (__nworkd) (*env)->ReleasePrimitiveArrayCritical(env, workd, __nworkd, __failed ? JNI_ABORT : 0);
  if (__nipntr) (*env)->ReleasePrimitiveArrayCritical(env, ipntr, __nipntr, __failed ? JNI_ABORT : 0);
  if (__niparam) (*env)->ReleasePrimitiveArrayCritical(env, iparam, __niparam, __failed ? JNI_ABORT : 0);
  if (__nv) (*env)->ReleasePrimitiveArrayCritical(env, v, __nv, __failed ? JNI_ABORT : 0);
  if (__nresid) (*env)->ReleasePrimitiveArrayCritical(env, resid, __nresid, __failed ? JNI_ABORT : 0);
  if (__nz) (*env)->ReleasePrimitiveArrayCritical(env, z, __nz, __failed ? JNI_ABORT : 0);
  if (__nd) (*env)->ReleasePrimitiveArrayCritical(env, d, __nd, __failed ? JNI_ABORT : 0);
  if (__nselect) { free(__nselect); } if (__jselect) (*env)->ReleasePrimitiveArrayCritical(env, select, __nselect, JNI_ABORT);
  if (!__failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (!__failed) (*env)->SetIntField(env, nev, intW_val_fieldID, __nnev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__nbmat) (*env)->ReleaseStringUTFChars(env, bmat, __nbmat);
  if (__nhowmny) (*env)->ReleaseStringUTFChars(env, howmny, __nhowmny);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dsgets_)(int *ishift, const char *which, int *kev, int *np, double *ritz, double *bounds, double *shifts);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsgetsK(JNIEnv *env, UNUSED jobject obj, jint ishift, jstring which, jobject kev, jobject np, jdoubleArray ritz, jint offsetritz, jdoubleArray bounds, jint offsetbounds, jdoubleArray shifts, jint offsetshifts) {
  jboolean __failed = FALSE;
  int __nishift __attribute__((aligned(8)));
  const char *__nwhich = NULL;
  int __nkev = 0;
  int __nnp = 0;
  double *__nritz = NULL;
  double *__nbounds = NULL;
  double *__nshifts = NULL;
  __nishift = ishift;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __nkev = (*env)->GetIntField(env, kev, intW_val_fieldID);
  __nnp = (*env)->GetIntField(env, np, intW_val_fieldID);
  if (!(__nritz = (*env)->GetPrimitiveArrayCritical(env, ritz, NULL))) { __failed = TRUE; goto done; }
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) { __failed = TRUE; goto done; }
  if (!(__nshifts = (*env)->GetPrimitiveArrayCritical(env, shifts, NULL))) { __failed = TRUE; goto done; }
  dsgets_(&__nishift, __nwhich, &__nkev, &__nnp, __nritz + offsetritz, __nbounds + offsetbounds, __nshifts + offsetshifts);
done:
  if (__nshifts) (*env)->ReleasePrimitiveArrayCritical(env, shifts, __nshifts, __failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, __failed ? JNI_ABORT : 0);
  if (__nritz) (*env)->ReleasePrimitiveArrayCritical(env, ritz, __nritz, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, np, intW_val_fieldID, __nnp);
  if (!__failed) (*env)->SetIntField(env, kev, intW_val_fieldID, __nkev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*ssgets_)(int *ishift, const char *which, int *kev, int *np, float *ritz, float *bounds, float *shifts);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssgetsK(JNIEnv *env, UNUSED jobject obj, jint ishift, jstring which, jobject kev, jobject np, jfloatArray ritz, jint offsetritz, jfloatArray bounds, jint offsetbounds, jfloatArray shifts, jint offsetshifts) {
  jboolean __failed = FALSE;
  int __nishift __attribute__((aligned(8)));
  const char *__nwhich = NULL;
  int __nkev = 0;
  int __nnp = 0;
  float *__nritz = NULL;
  float *__nbounds = NULL;
  float *__nshifts = NULL;
  __nishift = ishift;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __nkev = (*env)->GetIntField(env, kev, intW_val_fieldID);
  __nnp = (*env)->GetIntField(env, np, intW_val_fieldID);
  if (!(__nritz = (*env)->GetPrimitiveArrayCritical(env, ritz, NULL))) { __failed = TRUE; goto done; }
  if (!(__nbounds = (*env)->GetPrimitiveArrayCritical(env, bounds, NULL))) { __failed = TRUE; goto done; }
  if (!(__nshifts = (*env)->GetPrimitiveArrayCritical(env, shifts, NULL))) { __failed = TRUE; goto done; }
  ssgets_(&__nishift, __nwhich, &__nkev, &__nnp, __nritz + offsetritz, __nbounds + offsetbounds, __nshifts + offsetshifts);
done:
  if (__nshifts) (*env)->ReleasePrimitiveArrayCritical(env, shifts, __nshifts, __failed ? JNI_ABORT : 0);
  if (__nbounds) (*env)->ReleasePrimitiveArrayCritical(env, bounds, __nbounds, __failed ? JNI_ABORT : 0);
  if (__nritz) (*env)->ReleasePrimitiveArrayCritical(env, ritz, __nritz, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, np, intW_val_fieldID, __nnp);
  if (!__failed) (*env)->SetIntField(env, kev, intW_val_fieldID, __nkev);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dsortc_)(const char *which, int *apply, int *n, double *xreal, double *ximag, double *y);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsortcK(JNIEnv *env, UNUSED jobject obj, jstring which, jboolean apply, jint n, jdoubleArray xreal, jint offsetxreal, jdoubleArray ximag, jint offsetximag, jdoubleArray y, jint offsety) {
  jboolean __failed = FALSE;
  const char *__nwhich = NULL;
  int __napply __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  double *__nxreal = NULL;
  double *__nximag = NULL;
  double *__ny = NULL;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __napply = apply;
  __nn = n;
  if (!(__nxreal = (*env)->GetPrimitiveArrayCritical(env, xreal, NULL))) { __failed = TRUE; goto done; }
  if (!(__nximag = (*env)->GetPrimitiveArrayCritical(env, ximag, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  dsortc_(__nwhich, &__napply, &__nn, __nxreal + offsetxreal, __nximag + offsetximag, __ny + offsety);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, __failed ? JNI_ABORT : 0);
  if (__nximag) (*env)->ReleasePrimitiveArrayCritical(env, ximag, __nximag, __failed ? JNI_ABORT : 0);
  if (__nxreal) (*env)->ReleasePrimitiveArrayCritical(env, xreal, __nxreal, __failed ? JNI_ABORT : 0);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*ssortc_)(const char *which, int *apply, int *n, float *xreal, float *ximag, float *y);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssortcK(JNIEnv *env, UNUSED jobject obj, jstring which, jboolean apply, jint n, jfloatArray xreal, jint offsetxreal, jfloatArray ximag, jint offsetximag, jfloatArray y, jint offsety) {
  jboolean __failed = FALSE;
  const char *__nwhich = NULL;
  int __napply __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  float *__nxreal = NULL;
  float *__nximag = NULL;
  float *__ny = NULL;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __napply = apply;
  __nn = n;
  if (!(__nxreal = (*env)->GetPrimitiveArrayCritical(env, xreal, NULL))) { __failed = TRUE; goto done; }
  if (!(__nximag = (*env)->GetPrimitiveArrayCritical(env, ximag, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  ssortc_(__nwhich, &__napply, &__nn, __nxreal + offsetxreal, __nximag + offsetximag, __ny + offsety);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, __failed ? JNI_ABORT : 0);
  if (__nximag) (*env)->ReleasePrimitiveArrayCritical(env, ximag, __nximag, __failed ? JNI_ABORT : 0);
  if (__nxreal) (*env)->ReleasePrimitiveArrayCritical(env, xreal, __nxreal, __failed ? JNI_ABORT : 0);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dsortr_)(const char *which, int *apply, int *n, double *x1, double *x2);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dsortrK(JNIEnv *env, UNUSED jobject obj, jstring which, jboolean apply, jint n, jdoubleArray x1, jint offsetx1, jdoubleArray x2, jint offsetx2) {
  jboolean __failed = FALSE;
  const char *__nwhich = NULL;
  int __napply __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  double *__nx1 = NULL;
  double *__nx2 = NULL;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __napply = apply;
  __nn = n;
  if (!(__nx1 = (*env)->GetPrimitiveArrayCritical(env, x1, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx2 = (*env)->GetPrimitiveArrayCritical(env, x2, NULL))) { __failed = TRUE; goto done; }
  dsortr_(__nwhich, &__napply, &__nn, __nx1 + offsetx1, __nx2 + offsetx2);
done:
  if (__nx2) (*env)->ReleasePrimitiveArrayCritical(env, x2, __nx2, __failed ? JNI_ABORT : 0);
  if (__nx1) (*env)->ReleasePrimitiveArrayCritical(env, x1, __nx1, __failed ? JNI_ABORT : 0);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*ssortr_)(const char *which, int *apply, int *n, float *x1, float *x2);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_ssortrK(JNIEnv *env, UNUSED jobject obj, jstring which, jboolean apply, jint n, jfloatArray x1, jint offsetx1, jfloatArray x2, jint offsetx2) {
  jboolean __failed = FALSE;
  const char *__nwhich = NULL;
  int __napply __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  float *__nx1 = NULL;
  float *__nx2 = NULL;
  if (!(__nwhich = (*env)->GetStringUTFChars(env, which, NULL))) { __failed = TRUE; goto done; }
  __napply = apply;
  __nn = n;
  if (!(__nx1 = (*env)->GetPrimitiveArrayCritical(env, x1, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx2 = (*env)->GetPrimitiveArrayCritical(env, x2, NULL))) { __failed = TRUE; goto done; }
  ssortr_(__nwhich, &__napply, &__nn, __nx1 + offsetx1, __nx2 + offsetx2);
done:
  if (__nx2) (*env)->ReleasePrimitiveArrayCritical(env, x2, __nx2, __failed ? JNI_ABORT : 0);
  if (__nx1) (*env)->ReleasePrimitiveArrayCritical(env, x1, __nx1, __failed ? JNI_ABORT : 0);
  if (__nwhich) (*env)->ReleaseStringUTFChars(env, which, __nwhich);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dstatn_)();

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dstatnK(JNIEnv *env, UNUSED jobject obj) {
  jboolean __failed = FALSE;
  dstatn_();
done:
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*sstatn_)();

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sstatnK(JNIEnv *env, UNUSED jobject obj) {
  jboolean __failed = FALSE;
  sstatn_();
done:
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dstats_)();

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dstatsK(JNIEnv *env, UNUSED jobject obj) {
  jboolean __failed = FALSE;
  dstats_();
done:
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*sstats_)();

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sstatsK(JNIEnv *env, UNUSED jobject obj) {
  jboolean __failed = FALSE;
  sstats_();
done:
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dstqrb_)(int *n, double *d, double *e, double *z, double *work, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_dstqrbK(JNIEnv *env, UNUSED jobject obj, jint n, jdoubleArray d, jint offsetd, jdoubleArray e, jint offsete, jdoubleArray z, jint offsetz, jdoubleArray work, jint offsetwork, jobject info) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __ninfo = 0;
  double *__nd = NULL;
  double *__ne = NULL;
  double *__nz = NULL;
  double *__nwork = NULL;
  __nn = n;
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nd = (*env)->GetPrimitiveArrayCritical(env, d, NULL))) { __failed = TRUE; goto done; }
  if (!(__ne = (*env)->GetPrimitiveArrayCritical(env, e, NULL))) { __failed = TRUE; goto done; }
  if (!(__nz = (*env)->GetPrimitiveArrayCritical(env, z, NULL))) { __failed = TRUE; goto done; }
  if (!(__nwork = (*env)->GetPrimitiveArrayCritical(env, work, NULL))) { __failed = TRUE; goto done; }
  dstqrb_(&__nn, __nd + offsetd, __ne + offsete, __nz + offsetz, __nwork + offsetwork, &__ninfo);
done:
  if (__nwork) (*env)->ReleasePrimitiveArrayCritical(env, work, __nwork, __failed ? JNI_ABORT : 0);
  if (__nz) (*env)->ReleasePrimitiveArrayCritical(env, z, __nz, __failed ? JNI_ABORT : 0);
  if (__ne) (*env)->ReleasePrimitiveArrayCritical(env, e, __ne, __failed ? JNI_ABORT : 0);
  if (__nd) (*env)->ReleasePrimitiveArrayCritical(env, d, __nd, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*sstqrb_)(int *n, float *d, float *e, float *z, float *work, int *info);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_sstqrbK(JNIEnv *env, UNUSED jobject obj, jint n, jfloatArray d, jint offsetd, jfloatArray e, jint offsete, jfloatArray z, jint offsetz, jfloatArray work, jint offsetwork, jobject info) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __ninfo = 0;
  float *__nd = NULL;
  float *__ne = NULL;
  float *__nz = NULL;
  float *__nwork = NULL;
  __nn = n;
  __ninfo = (*env)->GetIntField(env, info, intW_val_fieldID);
  if (!(__nd = (*env)->GetPrimitiveArrayCritical(env, d, NULL))) { __failed = TRUE; goto done; }
  if (!(__ne = (*env)->GetPrimitiveArrayCritical(env, e, NULL))) { __failed = TRUE; goto done; }
  if (!(__nz = (*env)->GetPrimitiveArrayCritical(env, z, NULL))) { __failed = TRUE; goto done; }
  if (!(__nwork = (*env)->GetPrimitiveArrayCritical(env, work, NULL))) { __failed = TRUE; goto done; }
  sstqrb_(&__nn, __nd + offsetd, __ne + offsete, __nz + offsetz, __nwork + offsetwork, &__ninfo);
done:
  if (__nwork) (*env)->ReleasePrimitiveArrayCritical(env, work, __nwork, __failed ? JNI_ABORT : 0);
  if (__nz) (*env)->ReleasePrimitiveArrayCritical(env, z, __nz, __failed ? JNI_ABORT : 0);
  if (__ne) (*env)->ReleasePrimitiveArrayCritical(env, e, __ne, __failed ? JNI_ABORT : 0);
  if (__nd) (*env)->ReleasePrimitiveArrayCritical(env, d, __nd, __failed ? JNI_ABORT : 0);
  if (!__failed) (*env)->SetIntField(env, info, intW_val_fieldID, __ninfo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static int (*icnteq_)(int *n, int *array, int *value);

jint Java_dev_ludovic_netlib_arpack_JNIARPACK_icnteqK(JNIEnv *env, UNUSED jobject obj, jint n, jintArray array, jint offsetarray, jint value) {
  jint __ret = 0;
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nvalue __attribute__((aligned(8)));
  int *__narray = NULL;
  __nn = n;
  __nvalue = value;
  if (!(__narray = (*env)->GetPrimitiveArrayCritical(env, array, NULL))) { __failed = TRUE; goto done; }
  __ret = icnteq_(&__nn, __narray + offsetarray, &__nvalue);
done:
  if (__narray) (*env)->ReleasePrimitiveArrayCritical(env, array, __narray, __failed ? JNI_ABORT : 0);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
  return __ret;
}

static void (*icopy_)(int *n, int *lx, int *incx, int *ly, int *incy);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_icopyK(JNIEnv *env, UNUSED jobject obj, jint n, jintArray lx, jint offsetlx, jint incx, jintArray ly, jint offsetly, jint incy) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  int *__nlx = NULL;
  int *__nly = NULL;
  __nn = n;
  __nincx = incx;
  __nincy = incy;
  if (!(__nlx = (*env)->GetPrimitiveArrayCritical(env, lx, NULL))) { __failed = TRUE; goto done; }
  if (!(__nly = (*env)->GetPrimitiveArrayCritical(env, ly, NULL))) { __failed = TRUE; goto done; }
  icopy_(&__nn, __nlx + offsetlx, &__nincx, __nly + offsetly, &__nincy);
done:
  if (__nly) (*env)->ReleasePrimitiveArrayCritical(env, ly, __nly, __failed ? JNI_ABORT : 0);
  if (__nlx) (*env)->ReleasePrimitiveArrayCritical(env, lx, __nlx, __failed ? JNI_ABORT : 0);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*iset_)(int *n, int *value, int *array, int *inc);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_isetK(JNIEnv *env, UNUSED jobject obj, jint n, jint value, jintArray array, jint offsetarray, jint inc) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nvalue __attribute__((aligned(8)));
  int __ninc __attribute__((aligned(8)));
  int *__narray = NULL;
  __nn = n;
  __nvalue = value;
  __ninc = inc;
  if (!(__narray = (*env)->GetPrimitiveArrayCritical(env, array, NULL))) { __failed = TRUE; goto done; }
  iset_(&__nn, &__nvalue, __narray + offsetarray, &__ninc);
done:
  if (__narray) (*env)->ReleasePrimitiveArrayCritical(env, array, __narray, __failed ? JNI_ABORT : 0);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*iswap_)(int *n, int *sx, int *incx, int *sy, int *incy);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_iswapK(JNIEnv *env, UNUSED jobject obj, jint n, jintArray sx, jint offsetsx, jint incx, jintArray sy, jint offsetsy, jint incy) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  int *__nsx = NULL;
  int *__nsy = NULL;
  __nn = n;
  __nincx = incx;
  __nincy = incy;
  if (!(__nsx = (*env)->GetPrimitiveArrayCritical(env, sx, NULL))) { __failed = TRUE; goto done; }
  if (!(__nsy = (*env)->GetPrimitiveArrayCritical(env, sy, NULL))) { __failed = TRUE; goto done; }
  iswap_(&__nn, __nsx + offsetsx, &__nincx, __nsy + offsetsy, &__nincy);
done:
  if (__nsy) (*env)->ReleasePrimitiveArrayCritical(env, sy, __nsy, __failed ? JNI_ABORT : 0);
  if (__nsx) (*env)->ReleasePrimitiveArrayCritical(env, sx, __nsx, __failed ? JNI_ABORT : 0);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*second_)(float *t);

void Java_dev_ludovic_netlib_arpack_JNIARPACK_secondK(JNIEnv *env, UNUSED jobject obj, jobject t) {
  jboolean __failed = FALSE;
  float __nt = 0;
  __nt = (*env)->GetFloatField(env, t, floatW_val_fieldID);
  second_(&__nt);
done:
  if (!__failed) (*env)->SetFloatField(env, t, floatW_val_fieldID, __nt);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
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

static void *libhandle;

jboolean load_symbols(void) {
#define LOAD_SYMBOL(name) \
  name = dlsym(libhandle, #name); \
  if (!name) { \
    return FALSE; \
  }

  LOAD_SYMBOL(dmout_);
  LOAD_SYMBOL(smout_);
  LOAD_SYMBOL(dvout_);
  LOAD_SYMBOL(svout_);
  LOAD_SYMBOL(ivout_);
  LOAD_SYMBOL(dgetv0_);
  LOAD_SYMBOL(sgetv0_);
  // LOAD_SYMBOL(dlaqrb_);
  // LOAD_SYMBOL(slaqrb_);
  LOAD_SYMBOL(dnaitr_);
  LOAD_SYMBOL(snaitr_);
  LOAD_SYMBOL(dnapps_);
  LOAD_SYMBOL(snapps_);
  LOAD_SYMBOL(dnaup2_);
  LOAD_SYMBOL(snaup2_);
  LOAD_SYMBOL(dnaupd_);
  LOAD_SYMBOL(snaupd_);
  LOAD_SYMBOL(dnconv_);
  LOAD_SYMBOL(snconv_);
  LOAD_SYMBOL(dsconv_);
  LOAD_SYMBOL(ssconv_);
  LOAD_SYMBOL(dneigh_);
  LOAD_SYMBOL(sneigh_);
  LOAD_SYMBOL(dneupd_);
  LOAD_SYMBOL(sneupd_);
  LOAD_SYMBOL(dngets_);
  LOAD_SYMBOL(sngets_);
  LOAD_SYMBOL(dsaitr_);
  LOAD_SYMBOL(ssaitr_);
  LOAD_SYMBOL(dsapps_);
  LOAD_SYMBOL(ssapps_);
  LOAD_SYMBOL(dsaup2_);
  LOAD_SYMBOL(ssaup2_);
  LOAD_SYMBOL(dseigt_);
  LOAD_SYMBOL(sseigt_);
  LOAD_SYMBOL(dsesrt_);
  LOAD_SYMBOL(ssesrt_);
  LOAD_SYMBOL(dsaupd_);
  LOAD_SYMBOL(ssaupd_);
  LOAD_SYMBOL(dseupd_);
  LOAD_SYMBOL(sseupd_);
  LOAD_SYMBOL(dsgets_);
  LOAD_SYMBOL(ssgets_);
  LOAD_SYMBOL(dsortc_);
  LOAD_SYMBOL(ssortc_);
  LOAD_SYMBOL(dsortr_);
  LOAD_SYMBOL(ssortr_);
  LOAD_SYMBOL(dstatn_);
  LOAD_SYMBOL(sstatn_);
  LOAD_SYMBOL(dstats_);
  LOAD_SYMBOL(sstats_);
  LOAD_SYMBOL(dstqrb_);
  LOAD_SYMBOL(sstqrb_);
  LOAD_SYMBOL(icnteq_);
  LOAD_SYMBOL(icopy_);
  LOAD_SYMBOL(iset_);
  LOAD_SYMBOL(iswap_);
  LOAD_SYMBOL(second_);

#undef LOAD_SYMBOL
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
  if (!get_system_property(env, (*env)->NewStringUTF(env, "dev.ludovic.netlib.arpack.nativeLib"), (*env)->NewStringUTF(env, "libarpack.so.2"), &property_nativeLib)) {
    return -1;
  }

  char name[1024];
  if (property_nativeLibPath) {
    const char *utf = (*env)->GetStringUTFChars(env, property_nativeLibPath, NULL);
    snprintf(name, sizeof(name), "%s", utf);
    (*env)->ReleaseStringUTFChars(env, property_nativeLibPath, utf);
  } else if (property_nativeLib) {
    const char *utf = (*env)->GetStringUTFChars(env, property_nativeLib, NULL);
    snprintf(name, sizeof(name), "%s", utf);
    (*env)->ReleaseStringUTFChars(env, property_nativeLib, utf);
  } else {
    /* either property_nativeLibPath or property_nativeLib should always be non-NULL */
    return -1;
  }

  libhandle = dlopen(name, RTLD_LAZY);
  if (!libhandle) {
    return -1;
  }

  if (!load_symbols()) {
    return -1;
  }

  return JNI_VERSION_1_6;
}

void JNI_OnUnload(UNUSED JavaVM *vm, UNUSED void *reserved) {
  dlclose(libhandle);
}
