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

#include "dev_ludovic_netlib_blas_JNIBLAS.h"

#define UNUSED __attribute__((unused))

#define TRUE 1
#define FALSE 0

static jfieldID booleanW_val_fieldID;
static jfieldID intW_val_fieldID;
static jfieldID floatW_val_fieldID;
static jfieldID doubleW_val_fieldID;
static jfieldID StringW_val_fieldID;

static double (*dasum_)(int *n, double *x, int *incx);

jdouble Java_dev_ludovic_netlib_blas_JNIBLAS_dasumK(JNIEnv *env, UNUSED jobject obj, jint n, jdoubleArray x, jint offsetx, jint incx) {
  jdouble __ret = 0;
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  double *__nx = NULL;
  __nn = n;
  __nincx = incx;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  __ret = dasum_(&__nn, __nx + offsetx, &__nincx);
done:
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
  return __ret;
}

static float (*sasum_)(int *n, float *x, int *incx);

jfloat Java_dev_ludovic_netlib_blas_JNIBLAS_sasumK(JNIEnv *env, UNUSED jobject obj, jint n, jfloatArray x, jint offsetx, jint incx) {
  jfloat __ret = 0;
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  float *__nx = NULL;
  __nn = n;
  __nincx = incx;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  __ret = sasum_(&__nn, __nx + offsetx, &__nincx);
done:
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
  return __ret;
}

static void (*daxpy_)(int *n, double *alpha, double *x, int *incx, double *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_daxpyK(JNIEnv *env, UNUSED jobject obj, jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  double __nalpha __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  double *__nx = NULL;
  double *__ny = NULL;
  __nn = n;
  __nalpha = alpha;
  __nincx = incx;
  __nincy = incy;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  daxpy_(&__nn, &__nalpha, __nx + offsetx, &__nincx, __ny + offsety, &__nincy);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*saxpy_)(int *n, float *alpha, float *x, int *incx, float *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_saxpyK(JNIEnv *env, UNUSED jobject obj, jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  float __nalpha __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  float *__nx = NULL;
  float *__ny = NULL;
  __nn = n;
  __nalpha = alpha;
  __nincx = incx;
  __nincy = incy;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  saxpy_(&__nn, &__nalpha, __nx + offsetx, &__nincx, __ny + offsety, &__nincy);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dcopy_)(int *n, double *x, int *incx, double *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dcopyK(JNIEnv *env, UNUSED jobject obj, jint n, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  double *__nx = NULL;
  double *__ny = NULL;
  __nn = n;
  __nincx = incx;
  __nincy = incy;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  dcopy_(&__nn, __nx + offsetx, &__nincx, __ny + offsety, &__nincy);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*scopy_)(int *n, float *x, int *incx, float *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_scopyK(JNIEnv *env, UNUSED jobject obj, jint n, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  float *__nx = NULL;
  float *__ny = NULL;
  __nn = n;
  __nincx = incx;
  __nincy = incy;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  scopy_(&__nn, __nx + offsetx, &__nincx, __ny + offsety, &__nincy);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static double (*ddot_)(int *n, double *x, int *incx, double *y, int *incy);

jdouble Java_dev_ludovic_netlib_blas_JNIBLAS_ddotK(JNIEnv *env, UNUSED jobject obj, jint n, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy) {
  jdouble __ret = 0;
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  double *__nx = NULL;
  double *__ny = NULL;
  __nn = n;
  __nincx = incx;
  __nincy = incy;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  __ret = ddot_(&__nn, __nx + offsetx, &__nincx, __ny + offsety, &__nincy);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, JNI_ABORT);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
  return __ret;
}

static float (*sdot_)(int *n, float *x, int *incx, float *y, int *incy);

jfloat Java_dev_ludovic_netlib_blas_JNIBLAS_sdotK(JNIEnv *env, UNUSED jobject obj, jint n, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy) {
  jfloat __ret = 0;
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  float *__nx = NULL;
  float *__ny = NULL;
  __nn = n;
  __nincx = incx;
  __nincy = incy;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  __ret = sdot_(&__nn, __nx + offsetx, &__nincx, __ny + offsety, &__nincy);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, JNI_ABORT);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
  return __ret;
}

static float (*sdsdot_)(int *n, float *sb, float *sx, int *incsx, float *sy, int *incsy);

jfloat Java_dev_ludovic_netlib_blas_JNIBLAS_sdsdotK(JNIEnv *env, UNUSED jobject obj, jint n, jfloat sb, jfloatArray sx, jint offsetsx, jint incsx, jfloatArray sy, jint offsetsy, jint incsy) {
  jfloat __ret = 0;
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  float __nsb __attribute__((aligned(8)));
  int __nincsx __attribute__((aligned(8)));
  int __nincsy __attribute__((aligned(8)));
  float *__nsx = NULL;
  float *__nsy = NULL;
  __nn = n;
  __nsb = sb;
  __nincsx = incsx;
  __nincsy = incsy;
  if (!(__nsx = (*env)->GetPrimitiveArrayCritical(env, sx, NULL))) { __failed = TRUE; goto done; }
  if (!(__nsy = (*env)->GetPrimitiveArrayCritical(env, sy, NULL))) { __failed = TRUE; goto done; }
  __ret = sdsdot_(&__nn, &__nsb, __nsx + offsetsx, &__nincsx, __nsy + offsetsy, &__nincsy);
done:
  if (__nsy) (*env)->ReleasePrimitiveArrayCritical(env, sy, __nsy, JNI_ABORT);
  if (__nsx) (*env)->ReleasePrimitiveArrayCritical(env, sx, __nsx, JNI_ABORT);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
  return __ret;
}

static void (*dgbmv_)(const char *trans, int *m, int *n, int *kl, int *ku, double *alpha, double *a, int *lda, double *x, int *incx, double *beta, double *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dgbmvK(JNIEnv *env, UNUSED jobject obj, jstring trans, jint m, jint n, jint kl, jint ku, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray x, jint offsetx, jint incx, jdouble beta, jdoubleArray y, jint offsety, jint incy) {
  jboolean __failed = FALSE;
  const char *__ntrans = NULL;
  int __nm __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  int __nkl __attribute__((aligned(8)));
  int __nku __attribute__((aligned(8)));
  double __nalpha __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  double __nbeta __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  double *__na = NULL;
  double *__nx = NULL;
  double *__ny = NULL;
  if (!(__ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) { __failed = TRUE; goto done; }
  __nm = m;
  __nn = n;
  __nkl = kl;
  __nku = ku;
  __nalpha = alpha;
  __nlda = lda;
  __nincx = incx;
  __nbeta = beta;
  __nincy = incy;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  dgbmv_(__ntrans, &__nm, &__nn, &__nkl, &__nku, &__nalpha, __na + offseta, &__nlda, __nx + offsetx, &__nincx, &__nbeta, __ny + offsety, &__nincy);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ntrans) (*env)->ReleaseStringUTFChars(env, trans, __ntrans);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*sgbmv_)(const char *trans, int *m, int *n, int *kl, int *ku, float *alpha, float *a, int *lda, float *x, int *incx, float *beta, float *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sgbmvK(JNIEnv *env, UNUSED jobject obj, jstring trans, jint m, jint n, jint kl, jint ku, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray x, jint offsetx, jint incx, jfloat beta, jfloatArray y, jint offsety, jint incy) {
  jboolean __failed = FALSE;
  const char *__ntrans = NULL;
  int __nm __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  int __nkl __attribute__((aligned(8)));
  int __nku __attribute__((aligned(8)));
  float __nalpha __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  float __nbeta __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  float *__na = NULL;
  float *__nx = NULL;
  float *__ny = NULL;
  if (!(__ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) { __failed = TRUE; goto done; }
  __nm = m;
  __nn = n;
  __nkl = kl;
  __nku = ku;
  __nalpha = alpha;
  __nlda = lda;
  __nincx = incx;
  __nbeta = beta;
  __nincy = incy;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  sgbmv_(__ntrans, &__nm, &__nn, &__nkl, &__nku, &__nalpha, __na + offseta, &__nlda, __nx + offsetx, &__nincx, &__nbeta, __ny + offsety, &__nincy);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ntrans) (*env)->ReleaseStringUTFChars(env, trans, __ntrans);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dgemm_)(const char *transa, const char *transb, int *m, int *n, int *k, double *alpha, double *a, int *lda, double *b, int *ldb, double *beta, double *c, int *ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dgemmK(JNIEnv *env, UNUSED jobject obj, jstring transa, jstring transb, jint m, jint n, jint k, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdouble beta, jdoubleArray c, jint offsetc, jint ldc) {
  jboolean __failed = FALSE;
  const char *__ntransa = NULL;
  const char *__ntransb = NULL;
  int __nm __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  int __nk __attribute__((aligned(8)));
  double __nalpha __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nldb __attribute__((aligned(8)));
  double __nbeta __attribute__((aligned(8)));
  int __nldc __attribute__((aligned(8)));
  double *__na = NULL;
  double *__nb = NULL;
  double *__nc = NULL;
  if (!(__ntransa = (*env)->GetStringUTFChars(env, transa, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntransb = (*env)->GetStringUTFChars(env, transb, NULL))) { __failed = TRUE; goto done; }
  __nm = m;
  __nn = n;
  __nk = k;
  __nalpha = alpha;
  __nlda = lda;
  __nldb = ldb;
  __nbeta = beta;
  __nldc = ldc;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) { __failed = TRUE; goto done; }
  if (!(__nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) { __failed = TRUE; goto done; }
  dgemm_(__ntransa, __ntransb, &__nm, &__nn, &__nk, &__nalpha, __na + offseta, &__nlda, __nb + offsetb, &__nldb, &__nbeta, __nc + offsetc, &__nldc);
done:
  if (__nc) (*env)->ReleasePrimitiveArrayCritical(env, c, __nc, __failed ? JNI_ABORT : 0);
  if (__nb) (*env)->ReleasePrimitiveArrayCritical(env, b, __nb, JNI_ABORT);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ntransb) (*env)->ReleaseStringUTFChars(env, transb, __ntransb);
  if (__ntransa) (*env)->ReleaseStringUTFChars(env, transa, __ntransa);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*sgemm_)(const char *transa, const char *transb, int *m, int *n, int *k, float *alpha, float *a, int *lda, float *b, int *ldb, float *beta, float *c, int *ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sgemmK(JNIEnv *env, UNUSED jobject obj, jstring transa, jstring transb, jint m, jint n, jint k, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloat beta, jfloatArray c, jint offsetc, jint ldc) {
  jboolean __failed = FALSE;
  const char *__ntransa = NULL;
  const char *__ntransb = NULL;
  int __nm __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  int __nk __attribute__((aligned(8)));
  float __nalpha __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nldb __attribute__((aligned(8)));
  float __nbeta __attribute__((aligned(8)));
  int __nldc __attribute__((aligned(8)));
  float *__na = NULL;
  float *__nb = NULL;
  float *__nc = NULL;
  if (!(__ntransa = (*env)->GetStringUTFChars(env, transa, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntransb = (*env)->GetStringUTFChars(env, transb, NULL))) { __failed = TRUE; goto done; }
  __nm = m;
  __nn = n;
  __nk = k;
  __nalpha = alpha;
  __nlda = lda;
  __nldb = ldb;
  __nbeta = beta;
  __nldc = ldc;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) { __failed = TRUE; goto done; }
  if (!(__nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) { __failed = TRUE; goto done; }
  sgemm_(__ntransa, __ntransb, &__nm, &__nn, &__nk, &__nalpha, __na + offseta, &__nlda, __nb + offsetb, &__nldb, &__nbeta, __nc + offsetc, &__nldc);
done:
  if (__nc) (*env)->ReleasePrimitiveArrayCritical(env, c, __nc, __failed ? JNI_ABORT : 0);
  if (__nb) (*env)->ReleasePrimitiveArrayCritical(env, b, __nb, JNI_ABORT);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ntransb) (*env)->ReleaseStringUTFChars(env, transb, __ntransb);
  if (__ntransa) (*env)->ReleaseStringUTFChars(env, transa, __ntransa);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dgemv_)(const char *trans, int *m, int *n, double *alpha, double *a, int *lda, double *x, int *incx, double *beta, double *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dgemvK(JNIEnv *env, UNUSED jobject obj, jstring trans, jint m, jint n, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray x, jint offsetx, jint incx, jdouble beta, jdoubleArray y, jint offsety, jint incy) {
  jboolean __failed = FALSE;
  const char *__ntrans = NULL;
  int __nm __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  double __nalpha __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  double __nbeta __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  double *__na = NULL;
  double *__nx = NULL;
  double *__ny = NULL;
  if (!(__ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) { __failed = TRUE; goto done; }
  __nm = m;
  __nn = n;
  __nalpha = alpha;
  __nlda = lda;
  __nincx = incx;
  __nbeta = beta;
  __nincy = incy;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  dgemv_(__ntrans, &__nm, &__nn, &__nalpha, __na + offseta, &__nlda, __nx + offsetx, &__nincx, &__nbeta, __ny + offsety, &__nincy);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ntrans) (*env)->ReleaseStringUTFChars(env, trans, __ntrans);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*sgemv_)(const char *trans, int *m, int *n, float *alpha, float *a, int *lda, float *x, int *incx, float *beta, float *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sgemvK(JNIEnv *env, UNUSED jobject obj, jstring trans, jint m, jint n, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray x, jint offsetx, jint incx, jfloat beta, jfloatArray y, jint offsety, jint incy) {
  jboolean __failed = FALSE;
  const char *__ntrans = NULL;
  int __nm __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  float __nalpha __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  float __nbeta __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  float *__na = NULL;
  float *__nx = NULL;
  float *__ny = NULL;
  if (!(__ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) { __failed = TRUE; goto done; }
  __nm = m;
  __nn = n;
  __nalpha = alpha;
  __nlda = lda;
  __nincx = incx;
  __nbeta = beta;
  __nincy = incy;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  sgemv_(__ntrans, &__nm, &__nn, &__nalpha, __na + offseta, &__nlda, __nx + offsetx, &__nincx, &__nbeta, __ny + offsety, &__nincy);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ntrans) (*env)->ReleaseStringUTFChars(env, trans, __ntrans);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dger_)(int *m, int *n, double *alpha, double *x, int *incx, double *y, int *incy, double *a, int *lda);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dgerK(JNIEnv *env, UNUSED jobject obj, jint m, jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy, jdoubleArray a, jint offseta, jint lda) {
  jboolean __failed = FALSE;
  int __nm __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  double __nalpha __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  double *__nx = NULL;
  double *__ny = NULL;
  double *__na = NULL;
  __nm = m;
  __nn = n;
  __nalpha = alpha;
  __nincx = incx;
  __nincy = incy;
  __nlda = lda;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  dger_(&__nm, &__nn, &__nalpha, __nx + offsetx, &__nincx, __ny + offsety, &__nincy, __na + offseta, &__nlda);
done:
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, __failed ? JNI_ABORT : 0);
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, JNI_ABORT);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*sger_)(int *m, int *n, float *alpha, float *x, int *incx, float *y, int *incy, float *a, int *lda);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sgerK(JNIEnv *env, UNUSED jobject obj, jint m, jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy, jfloatArray a, jint offseta, jint lda) {
  jboolean __failed = FALSE;
  int __nm __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  float __nalpha __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  float *__nx = NULL;
  float *__ny = NULL;
  float *__na = NULL;
  __nm = m;
  __nn = n;
  __nalpha = alpha;
  __nincx = incx;
  __nincy = incy;
  __nlda = lda;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  sger_(&__nm, &__nn, &__nalpha, __nx + offsetx, &__nincx, __ny + offsety, &__nincy, __na + offseta, &__nlda);
done:
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, __failed ? JNI_ABORT : 0);
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, JNI_ABORT);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static double (*dnrm2_)(int *n, double *x, int *incx);

jdouble Java_dev_ludovic_netlib_blas_JNIBLAS_dnrm2K(JNIEnv *env, UNUSED jobject obj, jint n, jdoubleArray x, jint offsetx, jint incx) {
  jdouble __ret = 0;
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  double *__nx = NULL;
  __nn = n;
  __nincx = incx;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  __ret = dnrm2_(&__nn, __nx + offsetx, &__nincx);
done:
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
  return __ret;
}

static float (*snrm2_)(int *n, float *x, int *incx);

jfloat Java_dev_ludovic_netlib_blas_JNIBLAS_snrm2K(JNIEnv *env, UNUSED jobject obj, jint n, jfloatArray x, jint offsetx, jint incx) {
  jfloat __ret = 0;
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  float *__nx = NULL;
  __nn = n;
  __nincx = incx;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  __ret = snrm2_(&__nn, __nx + offsetx, &__nincx);
done:
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
  return __ret;
}

static void (*drot_)(int *n, double *dx, int *incx, double *dy, int *incy, double *c, double *s);

void Java_dev_ludovic_netlib_blas_JNIBLAS_drotK(JNIEnv *env, UNUSED jobject obj, jint n, jdoubleArray dx, jint offsetdx, jint incx, jdoubleArray dy, jint offsetdy, jint incy, jdouble c, jdouble s) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  double __nc __attribute__((aligned(8)));
  double __ns __attribute__((aligned(8)));
  double *__ndx = NULL;
  double *__ndy = NULL;
  __nn = n;
  __nincx = incx;
  __nincy = incy;
  __nc = c;
  __ns = s;
  if (!(__ndx = (*env)->GetPrimitiveArrayCritical(env, dx, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndy = (*env)->GetPrimitiveArrayCritical(env, dy, NULL))) { __failed = TRUE; goto done; }
  drot_(&__nn, __ndx + offsetdx, &__nincx, __ndy + offsetdy, &__nincy, &__nc, &__ns);
done:
  if (__ndy) (*env)->ReleasePrimitiveArrayCritical(env, dy, __ndy, __failed ? JNI_ABORT : 0);
  if (__ndx) (*env)->ReleasePrimitiveArrayCritical(env, dx, __ndx, __failed ? JNI_ABORT : 0);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*srot_)(int *n, float *sx, int *incx, float *sy, int *incy, float *c, float *s);

void Java_dev_ludovic_netlib_blas_JNIBLAS_srotK(JNIEnv *env, UNUSED jobject obj, jint n, jfloatArray sx, jint offsetsx, jint incx, jfloatArray sy, jint offsetsy, jint incy, jfloat c, jfloat s) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  float __nc __attribute__((aligned(8)));
  float __ns __attribute__((aligned(8)));
  float *__nsx = NULL;
  float *__nsy = NULL;
  __nn = n;
  __nincx = incx;
  __nincy = incy;
  __nc = c;
  __ns = s;
  if (!(__nsx = (*env)->GetPrimitiveArrayCritical(env, sx, NULL))) { __failed = TRUE; goto done; }
  if (!(__nsy = (*env)->GetPrimitiveArrayCritical(env, sy, NULL))) { __failed = TRUE; goto done; }
  srot_(&__nn, __nsx + offsetsx, &__nincx, __nsy + offsetsy, &__nincy, &__nc, &__ns);
done:
  if (__nsy) (*env)->ReleasePrimitiveArrayCritical(env, sy, __nsy, __failed ? JNI_ABORT : 0);
  if (__nsx) (*env)->ReleasePrimitiveArrayCritical(env, sx, __nsx, __failed ? JNI_ABORT : 0);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*drotm_)(int *n, double *dx, int *incx, double *dy, int *incy, double *dparam);

void Java_dev_ludovic_netlib_blas_JNIBLAS_drotmK(JNIEnv *env, UNUSED jobject obj, jint n, jdoubleArray dx, jint offsetdx, jint incx, jdoubleArray dy, jint offsetdy, jint incy, jdoubleArray dparam, jint offsetdparam) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  double *__ndx = NULL;
  double *__ndy = NULL;
  double *__ndparam = NULL;
  __nn = n;
  __nincx = incx;
  __nincy = incy;
  if (!(__ndx = (*env)->GetPrimitiveArrayCritical(env, dx, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndy = (*env)->GetPrimitiveArrayCritical(env, dy, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndparam = (*env)->GetPrimitiveArrayCritical(env, dparam, NULL))) { __failed = TRUE; goto done; }
  drotm_(&__nn, __ndx + offsetdx, &__nincx, __ndy + offsetdy, &__nincy, __ndparam + offsetdparam);
done:
  if (__ndparam) (*env)->ReleasePrimitiveArrayCritical(env, dparam, __ndparam, JNI_ABORT);
  if (__ndy) (*env)->ReleasePrimitiveArrayCritical(env, dy, __ndy, __failed ? JNI_ABORT : 0);
  if (__ndx) (*env)->ReleasePrimitiveArrayCritical(env, dx, __ndx, JNI_ABORT);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*srotm_)(int *n, float *sx, int *incx, float *sy, int *incy, float *sparam);

void Java_dev_ludovic_netlib_blas_JNIBLAS_srotmK(JNIEnv *env, UNUSED jobject obj, jint n, jfloatArray sx, jint offsetsx, jint incx, jfloatArray sy, jint offsetsy, jint incy, jfloatArray sparam, jint offsetsparam) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  float *__nsx = NULL;
  float *__nsy = NULL;
  float *__nsparam = NULL;
  __nn = n;
  __nincx = incx;
  __nincy = incy;
  if (!(__nsx = (*env)->GetPrimitiveArrayCritical(env, sx, NULL))) { __failed = TRUE; goto done; }
  if (!(__nsy = (*env)->GetPrimitiveArrayCritical(env, sy, NULL))) { __failed = TRUE; goto done; }
  if (!(__nsparam = (*env)->GetPrimitiveArrayCritical(env, sparam, NULL))) { __failed = TRUE; goto done; }
  srotm_(&__nn, __nsx + offsetsx, &__nincx, __nsy + offsetsy, &__nincy, __nsparam + offsetsparam);
done:
  if (__nsparam) (*env)->ReleasePrimitiveArrayCritical(env, sparam, __nsparam, JNI_ABORT);
  if (__nsy) (*env)->ReleasePrimitiveArrayCritical(env, sy, __nsy, __failed ? JNI_ABORT : 0);
  if (__nsx) (*env)->ReleasePrimitiveArrayCritical(env, sx, __nsx, JNI_ABORT);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*drotmg_)(double *dd1, double *dd2, double *dx1, double *dy1, double *dparam);

void Java_dev_ludovic_netlib_blas_JNIBLAS_drotmgK(JNIEnv *env, UNUSED jobject obj, jobject dd1, jobject dd2, jobject dx1, jdouble dy1, jdoubleArray dparam, jint offsetdparam) {
  jboolean __failed = FALSE;
  double __ndd1 = 0;
  double __ndd2 = 0;
  double __ndx1 = 0;
  double __ndy1 __attribute__((aligned(8)));
  double *__ndparam = NULL;
  __ndd1 = (*env)->GetDoubleField(env, dd1, doubleW_val_fieldID);
  __ndd2 = (*env)->GetDoubleField(env, dd2, doubleW_val_fieldID);
  __ndx1 = (*env)->GetDoubleField(env, dx1, doubleW_val_fieldID);
  __ndy1 = dy1;
  if (!(__ndparam = (*env)->GetPrimitiveArrayCritical(env, dparam, NULL))) { __failed = TRUE; goto done; }
  drotmg_(&__ndd1, &__ndd2, &__ndx1, &__ndy1, __ndparam + offsetdparam);
done:
  if (__ndparam) (*env)->ReleasePrimitiveArrayCritical(env, dparam, __ndparam, JNI_ABORT);
  if (!__failed) (*env)->SetDoubleField(env, dx1, doubleW_val_fieldID, __ndx1);
  if (!__failed) (*env)->SetDoubleField(env, dd2, doubleW_val_fieldID, __ndd2);
  if (!__failed) (*env)->SetDoubleField(env, dd1, doubleW_val_fieldID, __ndd1);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*srotmg_)(float *sd1, float *sd2, float *sx1, float *sy1, float *sparam);

void Java_dev_ludovic_netlib_blas_JNIBLAS_srotmgK(JNIEnv *env, UNUSED jobject obj, jobject sd1, jobject sd2, jobject sx1, jfloat sy1, jfloatArray sparam, jint offsetsparam) {
  jboolean __failed = FALSE;
  float __nsd1 = 0;
  float __nsd2 = 0;
  float __nsx1 = 0;
  float __nsy1 __attribute__((aligned(8)));
  float *__nsparam = NULL;
  __nsd1 = (*env)->GetFloatField(env, sd1, floatW_val_fieldID);
  __nsd2 = (*env)->GetFloatField(env, sd2, floatW_val_fieldID);
  __nsx1 = (*env)->GetFloatField(env, sx1, floatW_val_fieldID);
  __nsy1 = sy1;
  if (!(__nsparam = (*env)->GetPrimitiveArrayCritical(env, sparam, NULL))) { __failed = TRUE; goto done; }
  srotmg_(&__nsd1, &__nsd2, &__nsx1, &__nsy1, __nsparam + offsetsparam);
done:
  if (__nsparam) (*env)->ReleasePrimitiveArrayCritical(env, sparam, __nsparam, JNI_ABORT);
  if (!__failed) (*env)->SetFloatField(env, sx1, floatW_val_fieldID, __nsx1);
  if (!__failed) (*env)->SetFloatField(env, sd2, floatW_val_fieldID, __nsd2);
  if (!__failed) (*env)->SetFloatField(env, sd1, floatW_val_fieldID, __nsd1);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dsbmv_)(const char *uplo, int *n, int *k, double *alpha, double *a, int *lda, double *x, int *incx, double *beta, double *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsbmvK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jint n, jint k, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray x, jint offsetx, jint incx, jdouble beta, jdoubleArray y, jint offsety, jint incy) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  int __nn __attribute__((aligned(8)));
  int __nk __attribute__((aligned(8)));
  double __nalpha __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  double __nbeta __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  double *__na = NULL;
  double *__nx = NULL;
  double *__ny = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nk = k;
  __nalpha = alpha;
  __nlda = lda;
  __nincx = incx;
  __nbeta = beta;
  __nincy = incy;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  dsbmv_(__nuplo, &__nn, &__nk, &__nalpha, __na + offseta, &__nlda, __nx + offsetx, &__nincx, &__nbeta, __ny + offsety, &__nincy);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*ssbmv_)(const char *uplo, int *n, int *k, float *alpha, float *a, int *lda, float *x, int *incx, float *beta, float *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssbmvK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jint n, jint k, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray x, jint offsetx, jint incx, jfloat beta, jfloatArray y, jint offsety, jint incy) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  int __nn __attribute__((aligned(8)));
  int __nk __attribute__((aligned(8)));
  float __nalpha __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  float __nbeta __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  float *__na = NULL;
  float *__nx = NULL;
  float *__ny = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nk = k;
  __nalpha = alpha;
  __nlda = lda;
  __nincx = incx;
  __nbeta = beta;
  __nincy = incy;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  ssbmv_(__nuplo, &__nn, &__nk, &__nalpha, __na + offseta, &__nlda, __nx + offsetx, &__nincx, &__nbeta, __ny + offsety, &__nincy);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dscal_)(int *n, double *alpha, double *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dscalK(JNIEnv *env, UNUSED jobject obj, jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  double __nalpha __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  double *__nx = NULL;
  __nn = n;
  __nalpha = alpha;
  __nincx = incx;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  dscal_(&__nn, &__nalpha, __nx + offsetx, &__nincx);
done:
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, __failed ? JNI_ABORT : 0);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*sscal_)(int *n, float *alpha, float *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sscalK(JNIEnv *env, UNUSED jobject obj, jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  float __nalpha __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  float *__nx = NULL;
  __nn = n;
  __nalpha = alpha;
  __nincx = incx;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  sscal_(&__nn, &__nalpha, __nx + offsetx, &__nincx);
done:
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, __failed ? JNI_ABORT : 0);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dspmv_)(const char *uplo, int *n, double *alpha, double *a, double *x, int *incx, double *beta, double *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dspmvK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jint n, jdouble alpha, jdoubleArray a, jint offseta, jdoubleArray x, jint offsetx, jint incx, jdouble beta, jdoubleArray y, jint offsety, jint incy) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  int __nn __attribute__((aligned(8)));
  double __nalpha __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  double __nbeta __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  double *__na = NULL;
  double *__nx = NULL;
  double *__ny = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nalpha = alpha;
  __nincx = incx;
  __nbeta = beta;
  __nincy = incy;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  dspmv_(__nuplo, &__nn, &__nalpha, __na + offseta, __nx + offsetx, &__nincx, &__nbeta, __ny + offsety, &__nincy);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*sspmv_)(const char *uplo, int *n, float *alpha, float *a, float *x, int *incx, float *beta, float *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sspmvK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jint n, jfloat alpha, jfloatArray a, jint offseta, jfloatArray x, jint offsetx, jint incx, jfloat beta, jfloatArray y, jint offsety, jint incy) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  int __nn __attribute__((aligned(8)));
  float __nalpha __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  float __nbeta __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  float *__na = NULL;
  float *__nx = NULL;
  float *__ny = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nalpha = alpha;
  __nincx = incx;
  __nbeta = beta;
  __nincy = incy;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  sspmv_(__nuplo, &__nn, &__nalpha, __na + offseta, __nx + offsetx, &__nincx, &__nbeta, __ny + offsety, &__nincy);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dspr_)(const char *uplo, int *n, double *alpha, double *x, int *incx, double *a);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsprK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx, jdoubleArray a, jint offseta) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  int __nn __attribute__((aligned(8)));
  double __nalpha __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  double *__nx = NULL;
  double *__na = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nalpha = alpha;
  __nincx = incx;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  dspr_(__nuplo, &__nn, &__nalpha, __nx + offsetx, &__nincx, __na + offseta);
done:
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*sspr_)(const char *uplo, int *n, float *alpha, float *x, int *incx, float *a);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssprK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx, jfloatArray a, jint offseta) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  int __nn __attribute__((aligned(8)));
  float __nalpha __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  float *__nx = NULL;
  float *__na = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nalpha = alpha;
  __nincx = incx;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  sspr_(__nuplo, &__nn, &__nalpha, __nx + offsetx, &__nincx, __na + offseta);
done:
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dspr2_)(const char *uplo, int *n, double *alpha, double *x, int *incx, double *y, int *incy, double *a);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dspr2K(JNIEnv *env, UNUSED jobject obj, jstring uplo, jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy, jdoubleArray a, jint offseta) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  int __nn __attribute__((aligned(8)));
  double __nalpha __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  double *__nx = NULL;
  double *__ny = NULL;
  double *__na = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nalpha = alpha;
  __nincx = incx;
  __nincy = incy;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  dspr2_(__nuplo, &__nn, &__nalpha, __nx + offsetx, &__nincx, __ny + offsety, &__nincy, __na + offseta);
done:
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, __failed ? JNI_ABORT : 0);
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, JNI_ABORT);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*sspr2_)(const char *uplo, int *n, float *alpha, float *x, int *incx, float *y, int *incy, float *a);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sspr2K(JNIEnv *env, UNUSED jobject obj, jstring uplo, jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy, jfloatArray a, jint offseta) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  int __nn __attribute__((aligned(8)));
  float __nalpha __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  float *__nx = NULL;
  float *__ny = NULL;
  float *__na = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nalpha = alpha;
  __nincx = incx;
  __nincy = incy;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  sspr2_(__nuplo, &__nn, &__nalpha, __nx + offsetx, &__nincx, __ny + offsety, &__nincy, __na + offseta);
done:
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, __failed ? JNI_ABORT : 0);
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, JNI_ABORT);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dswap_)(int *n, double *x, int *incx, double *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dswapK(JNIEnv *env, UNUSED jobject obj, jint n, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  double *__nx = NULL;
  double *__ny = NULL;
  __nn = n;
  __nincx = incx;
  __nincy = incy;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  dswap_(&__nn, __nx + offsetx, &__nincx, __ny + offsety, &__nincy);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, __failed ? JNI_ABORT : 0);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*sswap_)(int *n, float *x, int *incx, float *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sswapK(JNIEnv *env, UNUSED jobject obj, jint n, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy) {
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  float *__nx = NULL;
  float *__ny = NULL;
  __nn = n;
  __nincx = incx;
  __nincy = incy;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  sswap_(&__nn, __nx + offsetx, &__nincx, __ny + offsety, &__nincy);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, __failed ? JNI_ABORT : 0);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dsymm_)(const char *side, const char *uplo, int *m, int *n, double *alpha, double *a, int *lda, double *b, int *ldb, double *beta, double *c, int *ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsymmK(JNIEnv *env, UNUSED jobject obj, jstring side, jstring uplo, jint m, jint n, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdouble beta, jdoubleArray c, jint offsetc, jint ldc) {
  jboolean __failed = FALSE;
  const char *__nside = NULL;
  const char *__nuplo = NULL;
  int __nm __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  double __nalpha __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nldb __attribute__((aligned(8)));
  double __nbeta __attribute__((aligned(8)));
  int __nldc __attribute__((aligned(8)));
  double *__na = NULL;
  double *__nb = NULL;
  double *__nc = NULL;
  if (!(__nside = (*env)->GetStringUTFChars(env, side, NULL))) { __failed = TRUE; goto done; }
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  __nm = m;
  __nn = n;
  __nalpha = alpha;
  __nlda = lda;
  __nldb = ldb;
  __nbeta = beta;
  __nldc = ldc;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) { __failed = TRUE; goto done; }
  if (!(__nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) { __failed = TRUE; goto done; }
  dsymm_(__nside, __nuplo, &__nm, &__nn, &__nalpha, __na + offseta, &__nlda, __nb + offsetb, &__nldb, &__nbeta, __nc + offsetc, &__nldc);
done:
  if (__nc) (*env)->ReleasePrimitiveArrayCritical(env, c, __nc, __failed ? JNI_ABORT : 0);
  if (__nb) (*env)->ReleasePrimitiveArrayCritical(env, b, __nb, JNI_ABORT);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__nside) (*env)->ReleaseStringUTFChars(env, side, __nside);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*ssymm_)(const char *side, const char *uplo, int *m, int *n, float *alpha, float *a, int *lda, float *b, int *ldb, float *beta, float *c, int *ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssymmK(JNIEnv *env, UNUSED jobject obj, jstring side, jstring uplo, jint m, jint n, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloat beta, jfloatArray c, jint offsetc, jint ldc) {
  jboolean __failed = FALSE;
  const char *__nside = NULL;
  const char *__nuplo = NULL;
  int __nm __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  float __nalpha __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nldb __attribute__((aligned(8)));
  float __nbeta __attribute__((aligned(8)));
  int __nldc __attribute__((aligned(8)));
  float *__na = NULL;
  float *__nb = NULL;
  float *__nc = NULL;
  if (!(__nside = (*env)->GetStringUTFChars(env, side, NULL))) { __failed = TRUE; goto done; }
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  __nm = m;
  __nn = n;
  __nalpha = alpha;
  __nlda = lda;
  __nldb = ldb;
  __nbeta = beta;
  __nldc = ldc;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) { __failed = TRUE; goto done; }
  if (!(__nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) { __failed = TRUE; goto done; }
  ssymm_(__nside, __nuplo, &__nm, &__nn, &__nalpha, __na + offseta, &__nlda, __nb + offsetb, &__nldb, &__nbeta, __nc + offsetc, &__nldc);
done:
  if (__nc) (*env)->ReleasePrimitiveArrayCritical(env, c, __nc, __failed ? JNI_ABORT : 0);
  if (__nb) (*env)->ReleasePrimitiveArrayCritical(env, b, __nb, JNI_ABORT);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__nside) (*env)->ReleaseStringUTFChars(env, side, __nside);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dsymv_)(const char *uplo, int *n, double *alpha, double *a, int *lda, double *x, int *incx, double *beta, double *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsymvK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jint n, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray x, jint offsetx, jint incx, jdouble beta, jdoubleArray y, jint offsety, jint incy) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  int __nn __attribute__((aligned(8)));
  double __nalpha __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  double __nbeta __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  double *__na = NULL;
  double *__nx = NULL;
  double *__ny = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nalpha = alpha;
  __nlda = lda;
  __nincx = incx;
  __nbeta = beta;
  __nincy = incy;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  dsymv_(__nuplo, &__nn, &__nalpha, __na + offseta, &__nlda, __nx + offsetx, &__nincx, &__nbeta, __ny + offsety, &__nincy);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*ssymv_)(const char *uplo, int *n, float *alpha, float *a, int *lda, float *x, int *incx, float *beta, float *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssymvK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jint n, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray x, jint offsetx, jint incx, jfloat beta, jfloatArray y, jint offsety, jint incy) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  int __nn __attribute__((aligned(8)));
  float __nalpha __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  float __nbeta __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  float *__na = NULL;
  float *__nx = NULL;
  float *__ny = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nalpha = alpha;
  __nlda = lda;
  __nincx = incx;
  __nbeta = beta;
  __nincy = incy;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  ssymv_(__nuplo, &__nn, &__nalpha, __na + offseta, &__nlda, __nx + offsetx, &__nincx, &__nbeta, __ny + offsety, &__nincy);
done:
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dsyr_)(const char *uplo, int *n, double *alpha, double *x, int *incx, double *a, int *lda);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsyrK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx, jdoubleArray a, jint offseta, jint lda) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  int __nn __attribute__((aligned(8)));
  double __nalpha __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  double *__nx = NULL;
  double *__na = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nalpha = alpha;
  __nincx = incx;
  __nlda = lda;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  dsyr_(__nuplo, &__nn, &__nalpha, __nx + offsetx, &__nincx, __na + offseta, &__nlda);
done:
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*ssyr_)(const char *uplo, int *n, float *alpha, float *x, int *incx, float *a, int *lda);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssyrK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx, jfloatArray a, jint offseta, jint lda) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  int __nn __attribute__((aligned(8)));
  float __nalpha __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  float *__nx = NULL;
  float *__na = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nalpha = alpha;
  __nincx = incx;
  __nlda = lda;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  ssyr_(__nuplo, &__nn, &__nalpha, __nx + offsetx, &__nincx, __na + offseta, &__nlda);
done:
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, __failed ? JNI_ABORT : 0);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dsyr2_)(const char *uplo, int *n, double *alpha, double *x, int *incx, double *y, int *incy, double *a, int *lda);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsyr2K(JNIEnv *env, UNUSED jobject obj, jstring uplo, jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy, jdoubleArray a, jint offseta, jint lda) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  int __nn __attribute__((aligned(8)));
  double __nalpha __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  double *__nx = NULL;
  double *__ny = NULL;
  double *__na = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nalpha = alpha;
  __nincx = incx;
  __nincy = incy;
  __nlda = lda;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  dsyr2_(__nuplo, &__nn, &__nalpha, __nx + offsetx, &__nincx, __ny + offsety, &__nincy, __na + offseta, &__nlda);
done:
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, __failed ? JNI_ABORT : 0);
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, JNI_ABORT);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*ssyr2_)(const char *uplo, int *n, float *alpha, float *x, int *incx, float *y, int *incy, float *a, int *lda);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssyr2K(JNIEnv *env, UNUSED jobject obj, jstring uplo, jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy, jfloatArray a, jint offseta, jint lda) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  int __nn __attribute__((aligned(8)));
  float __nalpha __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  int __nincy __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  float *__nx = NULL;
  float *__ny = NULL;
  float *__na = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nalpha = alpha;
  __nincx = incx;
  __nincy = incy;
  __nlda = lda;
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  if (!(__ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) { __failed = TRUE; goto done; }
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  ssyr2_(__nuplo, &__nn, &__nalpha, __nx + offsetx, &__nincx, __ny + offsety, &__nincy, __na + offseta, &__nlda);
done:
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, __failed ? JNI_ABORT : 0);
  if (__ny) (*env)->ReleasePrimitiveArrayCritical(env, y, __ny, JNI_ABORT);
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, JNI_ABORT);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dsyr2k_)(const char *uplo, const char *trans, int *n, int *k, double *alpha, double *a, int *lda, double *b, int *ldb, double *beta, double *c, int *ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsyr2kK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jstring trans, jint n, jint k, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdouble beta, jdoubleArray c, jint offsetc, jint ldc) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  const char *__ntrans = NULL;
  int __nn __attribute__((aligned(8)));
  int __nk __attribute__((aligned(8)));
  double __nalpha __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nldb __attribute__((aligned(8)));
  double __nbeta __attribute__((aligned(8)));
  int __nldc __attribute__((aligned(8)));
  double *__na = NULL;
  double *__nb = NULL;
  double *__nc = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nk = k;
  __nalpha = alpha;
  __nlda = lda;
  __nldb = ldb;
  __nbeta = beta;
  __nldc = ldc;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) { __failed = TRUE; goto done; }
  if (!(__nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) { __failed = TRUE; goto done; }
  dsyr2k_(__nuplo, __ntrans, &__nn, &__nk, &__nalpha, __na + offseta, &__nlda, __nb + offsetb, &__nldb, &__nbeta, __nc + offsetc, &__nldc);
done:
  if (__nc) (*env)->ReleasePrimitiveArrayCritical(env, c, __nc, __failed ? JNI_ABORT : 0);
  if (__nb) (*env)->ReleasePrimitiveArrayCritical(env, b, __nb, JNI_ABORT);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ntrans) (*env)->ReleaseStringUTFChars(env, trans, __ntrans);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*ssyr2k_)(const char *uplo, const char *trans, int *n, int *k, float *alpha, float *a, int *lda, float *b, int *ldb, float *beta, float *c, int *ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssyr2kK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jstring trans, jint n, jint k, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloat beta, jfloatArray c, jint offsetc, jint ldc) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  const char *__ntrans = NULL;
  int __nn __attribute__((aligned(8)));
  int __nk __attribute__((aligned(8)));
  float __nalpha __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nldb __attribute__((aligned(8)));
  float __nbeta __attribute__((aligned(8)));
  int __nldc __attribute__((aligned(8)));
  float *__na = NULL;
  float *__nb = NULL;
  float *__nc = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nk = k;
  __nalpha = alpha;
  __nlda = lda;
  __nldb = ldb;
  __nbeta = beta;
  __nldc = ldc;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) { __failed = TRUE; goto done; }
  if (!(__nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) { __failed = TRUE; goto done; }
  ssyr2k_(__nuplo, __ntrans, &__nn, &__nk, &__nalpha, __na + offseta, &__nlda, __nb + offsetb, &__nldb, &__nbeta, __nc + offsetc, &__nldc);
done:
  if (__nc) (*env)->ReleasePrimitiveArrayCritical(env, c, __nc, __failed ? JNI_ABORT : 0);
  if (__nb) (*env)->ReleasePrimitiveArrayCritical(env, b, __nb, JNI_ABORT);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ntrans) (*env)->ReleaseStringUTFChars(env, trans, __ntrans);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dsyrk_)(const char *uplo, const char *trans, int *n, int *k, double *alpha, double *a, int *lda, double *beta, double *c, int *ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsyrkK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jstring trans, jint n, jint k, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdouble beta, jdoubleArray c, jint offsetc, jint ldc) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  const char *__ntrans = NULL;
  int __nn __attribute__((aligned(8)));
  int __nk __attribute__((aligned(8)));
  double __nalpha __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  double __nbeta __attribute__((aligned(8)));
  int __nldc __attribute__((aligned(8)));
  double *__na = NULL;
  double *__nc = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nk = k;
  __nalpha = alpha;
  __nlda = lda;
  __nbeta = beta;
  __nldc = ldc;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) { __failed = TRUE; goto done; }
  dsyrk_(__nuplo, __ntrans, &__nn, &__nk, &__nalpha, __na + offseta, &__nlda, &__nbeta, __nc + offsetc, &__nldc);
done:
  if (__nc) (*env)->ReleasePrimitiveArrayCritical(env, c, __nc, __failed ? JNI_ABORT : 0);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ntrans) (*env)->ReleaseStringUTFChars(env, trans, __ntrans);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*ssyrk_)(const char *uplo, const char *trans, int *n, int *k, float *alpha, float *a, int *lda, float *beta, float *c, int *ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssyrkK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jstring trans, jint n, jint k, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloat beta, jfloatArray c, jint offsetc, jint ldc) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  const char *__ntrans = NULL;
  int __nn __attribute__((aligned(8)));
  int __nk __attribute__((aligned(8)));
  float __nalpha __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  float __nbeta __attribute__((aligned(8)));
  int __nldc __attribute__((aligned(8)));
  float *__na = NULL;
  float *__nc = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nk = k;
  __nalpha = alpha;
  __nlda = lda;
  __nbeta = beta;
  __nldc = ldc;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) { __failed = TRUE; goto done; }
  ssyrk_(__nuplo, __ntrans, &__nn, &__nk, &__nalpha, __na + offseta, &__nlda, &__nbeta, __nc + offsetc, &__nldc);
done:
  if (__nc) (*env)->ReleasePrimitiveArrayCritical(env, c, __nc, __failed ? JNI_ABORT : 0);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ntrans) (*env)->ReleaseStringUTFChars(env, trans, __ntrans);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dtbmv_)(const char *uplo, const char *trans, const char *diag, int *n, int *k, double *a, int *lda, double *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtbmvK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jstring trans, jstring diag, jint n, jint k, jdoubleArray a, jint offseta, jint lda, jdoubleArray x, jint offsetx, jint incx) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  const char *__ntrans = NULL;
  const char *__ndiag = NULL;
  int __nn __attribute__((aligned(8)));
  int __nk __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  double *__na = NULL;
  double *__nx = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndiag = (*env)->GetStringUTFChars(env, diag, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nk = k;
  __nlda = lda;
  __nincx = incx;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  dtbmv_(__nuplo, __ntrans, __ndiag, &__nn, &__nk, __na + offseta, &__nlda, __nx + offsetx, &__nincx);
done:
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, __failed ? JNI_ABORT : 0);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ndiag) (*env)->ReleaseStringUTFChars(env, diag, __ndiag);
  if (__ntrans) (*env)->ReleaseStringUTFChars(env, trans, __ntrans);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*stbmv_)(const char *uplo, const char *trans, const char *diag, int *n, int *k, float *a, int *lda, float *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_stbmvK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jstring trans, jstring diag, jint n, jint k, jfloatArray a, jint offseta, jint lda, jfloatArray x, jint offsetx, jint incx) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  const char *__ntrans = NULL;
  const char *__ndiag = NULL;
  int __nn __attribute__((aligned(8)));
  int __nk __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  float *__na = NULL;
  float *__nx = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndiag = (*env)->GetStringUTFChars(env, diag, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nk = k;
  __nlda = lda;
  __nincx = incx;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  stbmv_(__nuplo, __ntrans, __ndiag, &__nn, &__nk, __na + offseta, &__nlda, __nx + offsetx, &__nincx);
done:
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, __failed ? JNI_ABORT : 0);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ndiag) (*env)->ReleaseStringUTFChars(env, diag, __ndiag);
  if (__ntrans) (*env)->ReleaseStringUTFChars(env, trans, __ntrans);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dtbsv_)(const char *uplo, const char *trans, const char *diag, int *n, int *k, double *a, int *lda, double *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtbsvK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jstring trans, jstring diag, jint n, jint k, jdoubleArray a, jint offseta, jint lda, jdoubleArray x, jint offsetx, jint incx) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  const char *__ntrans = NULL;
  const char *__ndiag = NULL;
  int __nn __attribute__((aligned(8)));
  int __nk __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  double *__na = NULL;
  double *__nx = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndiag = (*env)->GetStringUTFChars(env, diag, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nk = k;
  __nlda = lda;
  __nincx = incx;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  dtbsv_(__nuplo, __ntrans, __ndiag, &__nn, &__nk, __na + offseta, &__nlda, __nx + offsetx, &__nincx);
done:
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, __failed ? JNI_ABORT : 0);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ndiag) (*env)->ReleaseStringUTFChars(env, diag, __ndiag);
  if (__ntrans) (*env)->ReleaseStringUTFChars(env, trans, __ntrans);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*stbsv_)(const char *uplo, const char *trans, const char *diag, int *n, int *k, float *a, int *lda, float *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_stbsvK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jstring trans, jstring diag, jint n, jint k, jfloatArray a, jint offseta, jint lda, jfloatArray x, jint offsetx, jint incx) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  const char *__ntrans = NULL;
  const char *__ndiag = NULL;
  int __nn __attribute__((aligned(8)));
  int __nk __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  float *__na = NULL;
  float *__nx = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndiag = (*env)->GetStringUTFChars(env, diag, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nk = k;
  __nlda = lda;
  __nincx = incx;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  stbsv_(__nuplo, __ntrans, __ndiag, &__nn, &__nk, __na + offseta, &__nlda, __nx + offsetx, &__nincx);
done:
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, __failed ? JNI_ABORT : 0);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ndiag) (*env)->ReleaseStringUTFChars(env, diag, __ndiag);
  if (__ntrans) (*env)->ReleaseStringUTFChars(env, trans, __ntrans);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dtpmv_)(const char *uplo, const char *trans, const char *diag, int *n, double *a, double *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtpmvK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jstring trans, jstring diag, jint n, jdoubleArray a, jint offseta, jdoubleArray x, jint offsetx, jint incx) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  const char *__ntrans = NULL;
  const char *__ndiag = NULL;
  int __nn __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  double *__na = NULL;
  double *__nx = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndiag = (*env)->GetStringUTFChars(env, diag, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nincx = incx;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  dtpmv_(__nuplo, __ntrans, __ndiag, &__nn, __na + offseta, __nx + offsetx, &__nincx);
done:
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, __failed ? JNI_ABORT : 0);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ndiag) (*env)->ReleaseStringUTFChars(env, diag, __ndiag);
  if (__ntrans) (*env)->ReleaseStringUTFChars(env, trans, __ntrans);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*stpmv_)(const char *uplo, const char *trans, const char *diag, int *n, float *a, float *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_stpmvK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jstring trans, jstring diag, jint n, jfloatArray a, jint offseta, jfloatArray x, jint offsetx, jint incx) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  const char *__ntrans = NULL;
  const char *__ndiag = NULL;
  int __nn __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  float *__na = NULL;
  float *__nx = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndiag = (*env)->GetStringUTFChars(env, diag, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nincx = incx;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  stpmv_(__nuplo, __ntrans, __ndiag, &__nn, __na + offseta, __nx + offsetx, &__nincx);
done:
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, __failed ? JNI_ABORT : 0);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ndiag) (*env)->ReleaseStringUTFChars(env, diag, __ndiag);
  if (__ntrans) (*env)->ReleaseStringUTFChars(env, trans, __ntrans);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dtpsv_)(const char *uplo, const char *trans, const char *diag, int *n, double *a, double *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtpsvK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jstring trans, jstring diag, jint n, jdoubleArray a, jint offseta, jdoubleArray x, jint offsetx, jint incx) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  const char *__ntrans = NULL;
  const char *__ndiag = NULL;
  int __nn __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  double *__na = NULL;
  double *__nx = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndiag = (*env)->GetStringUTFChars(env, diag, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nincx = incx;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  dtpsv_(__nuplo, __ntrans, __ndiag, &__nn, __na + offseta, __nx + offsetx, &__nincx);
done:
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, __failed ? JNI_ABORT : 0);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ndiag) (*env)->ReleaseStringUTFChars(env, diag, __ndiag);
  if (__ntrans) (*env)->ReleaseStringUTFChars(env, trans, __ntrans);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*stpsv_)(const char *uplo, const char *trans, const char *diag, int *n, float *a, float *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_stpsvK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jstring trans, jstring diag, jint n, jfloatArray a, jint offseta, jfloatArray x, jint offsetx, jint incx) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  const char *__ntrans = NULL;
  const char *__ndiag = NULL;
  int __nn __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  float *__na = NULL;
  float *__nx = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndiag = (*env)->GetStringUTFChars(env, diag, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nincx = incx;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  stpsv_(__nuplo, __ntrans, __ndiag, &__nn, __na + offseta, __nx + offsetx, &__nincx);
done:
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, __failed ? JNI_ABORT : 0);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ndiag) (*env)->ReleaseStringUTFChars(env, diag, __ndiag);
  if (__ntrans) (*env)->ReleaseStringUTFChars(env, trans, __ntrans);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dtrmm_)(const char *side, const char *uplo, const char *transa, const char *diag, int *m, int *n, double *alpha, double *a, int *lda, double *b, int *ldb);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtrmmK(JNIEnv *env, UNUSED jobject obj, jstring side, jstring uplo, jstring transa, jstring diag, jint m, jint n, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb) {
  jboolean __failed = FALSE;
  const char *__nside = NULL;
  const char *__nuplo = NULL;
  const char *__ntransa = NULL;
  const char *__ndiag = NULL;
  int __nm __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  double __nalpha __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nldb __attribute__((aligned(8)));
  double *__na = NULL;
  double *__nb = NULL;
  if (!(__nside = (*env)->GetStringUTFChars(env, side, NULL))) { __failed = TRUE; goto done; }
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntransa = (*env)->GetStringUTFChars(env, transa, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndiag = (*env)->GetStringUTFChars(env, diag, NULL))) { __failed = TRUE; goto done; }
  __nm = m;
  __nn = n;
  __nalpha = alpha;
  __nlda = lda;
  __nldb = ldb;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) { __failed = TRUE; goto done; }
  dtrmm_(__nside, __nuplo, __ntransa, __ndiag, &__nm, &__nn, &__nalpha, __na + offseta, &__nlda, __nb + offsetb, &__nldb);
done:
  if (__nb) (*env)->ReleasePrimitiveArrayCritical(env, b, __nb, __failed ? JNI_ABORT : 0);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ndiag) (*env)->ReleaseStringUTFChars(env, diag, __ndiag);
  if (__ntransa) (*env)->ReleaseStringUTFChars(env, transa, __ntransa);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__nside) (*env)->ReleaseStringUTFChars(env, side, __nside);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*strmm_)(const char *side, const char *uplo, const char *transa, const char *diag, int *m, int *n, float *alpha, float *a, int *lda, float *b, int *ldb);

void Java_dev_ludovic_netlib_blas_JNIBLAS_strmmK(JNIEnv *env, UNUSED jobject obj, jstring side, jstring uplo, jstring transa, jstring diag, jint m, jint n, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb) {
  jboolean __failed = FALSE;
  const char *__nside = NULL;
  const char *__nuplo = NULL;
  const char *__ntransa = NULL;
  const char *__ndiag = NULL;
  int __nm __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  float __nalpha __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nldb __attribute__((aligned(8)));
  float *__na = NULL;
  float *__nb = NULL;
  if (!(__nside = (*env)->GetStringUTFChars(env, side, NULL))) { __failed = TRUE; goto done; }
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntransa = (*env)->GetStringUTFChars(env, transa, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndiag = (*env)->GetStringUTFChars(env, diag, NULL))) { __failed = TRUE; goto done; }
  __nm = m;
  __nn = n;
  __nalpha = alpha;
  __nlda = lda;
  __nldb = ldb;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) { __failed = TRUE; goto done; }
  strmm_(__nside, __nuplo, __ntransa, __ndiag, &__nm, &__nn, &__nalpha, __na + offseta, &__nlda, __nb + offsetb, &__nldb);
done:
  if (__nb) (*env)->ReleasePrimitiveArrayCritical(env, b, __nb, __failed ? JNI_ABORT : 0);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ndiag) (*env)->ReleaseStringUTFChars(env, diag, __ndiag);
  if (__ntransa) (*env)->ReleaseStringUTFChars(env, transa, __ntransa);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__nside) (*env)->ReleaseStringUTFChars(env, side, __nside);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dtrmv_)(const char *uplo, const char *trans, const char *diag, int *n, double *a, int *lda, double *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtrmvK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jstring trans, jstring diag, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray x, jint offsetx, jint incx) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  const char *__ntrans = NULL;
  const char *__ndiag = NULL;
  int __nn __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  double *__na = NULL;
  double *__nx = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndiag = (*env)->GetStringUTFChars(env, diag, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nlda = lda;
  __nincx = incx;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  dtrmv_(__nuplo, __ntrans, __ndiag, &__nn, __na + offseta, &__nlda, __nx + offsetx, &__nincx);
done:
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, __failed ? JNI_ABORT : 0);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ndiag) (*env)->ReleaseStringUTFChars(env, diag, __ndiag);
  if (__ntrans) (*env)->ReleaseStringUTFChars(env, trans, __ntrans);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*strmv_)(const char *uplo, const char *trans, const char *diag, int *n, float *a, int *lda, float *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_strmvK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jstring trans, jstring diag, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray x, jint offsetx, jint incx) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  const char *__ntrans = NULL;
  const char *__ndiag = NULL;
  int __nn __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  float *__na = NULL;
  float *__nx = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndiag = (*env)->GetStringUTFChars(env, diag, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nlda = lda;
  __nincx = incx;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  strmv_(__nuplo, __ntrans, __ndiag, &__nn, __na + offseta, &__nlda, __nx + offsetx, &__nincx);
done:
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, __failed ? JNI_ABORT : 0);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ndiag) (*env)->ReleaseStringUTFChars(env, diag, __ndiag);
  if (__ntrans) (*env)->ReleaseStringUTFChars(env, trans, __ntrans);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dtrsm_)(const char *side, const char *uplo, const char *transa, const char *diag, int *m, int *n, double *alpha, double *a, int *lda, double *b, int *ldb);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtrsmK(JNIEnv *env, UNUSED jobject obj, jstring side, jstring uplo, jstring transa, jstring diag, jint m, jint n, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb) {
  jboolean __failed = FALSE;
  const char *__nside = NULL;
  const char *__nuplo = NULL;
  const char *__ntransa = NULL;
  const char *__ndiag = NULL;
  int __nm __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  double __nalpha __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nldb __attribute__((aligned(8)));
  double *__na = NULL;
  double *__nb = NULL;
  if (!(__nside = (*env)->GetStringUTFChars(env, side, NULL))) { __failed = TRUE; goto done; }
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntransa = (*env)->GetStringUTFChars(env, transa, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndiag = (*env)->GetStringUTFChars(env, diag, NULL))) { __failed = TRUE; goto done; }
  __nm = m;
  __nn = n;
  __nalpha = alpha;
  __nlda = lda;
  __nldb = ldb;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) { __failed = TRUE; goto done; }
  dtrsm_(__nside, __nuplo, __ntransa, __ndiag, &__nm, &__nn, &__nalpha, __na + offseta, &__nlda, __nb + offsetb, &__nldb);
done:
  if (__nb) (*env)->ReleasePrimitiveArrayCritical(env, b, __nb, __failed ? JNI_ABORT : 0);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ndiag) (*env)->ReleaseStringUTFChars(env, diag, __ndiag);
  if (__ntransa) (*env)->ReleaseStringUTFChars(env, transa, __ntransa);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__nside) (*env)->ReleaseStringUTFChars(env, side, __nside);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*strsm_)(const char *side, const char *uplo, const char *transa, const char *diag, int *m, int *n, float *alpha, float *a, int *lda, float *b, int *ldb);

void Java_dev_ludovic_netlib_blas_JNIBLAS_strsmK(JNIEnv *env, UNUSED jobject obj, jstring side, jstring uplo, jstring transa, jstring diag, jint m, jint n, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb) {
  jboolean __failed = FALSE;
  const char *__nside = NULL;
  const char *__nuplo = NULL;
  const char *__ntransa = NULL;
  const char *__ndiag = NULL;
  int __nm __attribute__((aligned(8)));
  int __nn __attribute__((aligned(8)));
  float __nalpha __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nldb __attribute__((aligned(8)));
  float *__na = NULL;
  float *__nb = NULL;
  if (!(__nside = (*env)->GetStringUTFChars(env, side, NULL))) { __failed = TRUE; goto done; }
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntransa = (*env)->GetStringUTFChars(env, transa, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndiag = (*env)->GetStringUTFChars(env, diag, NULL))) { __failed = TRUE; goto done; }
  __nm = m;
  __nn = n;
  __nalpha = alpha;
  __nlda = lda;
  __nldb = ldb;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) { __failed = TRUE; goto done; }
  strsm_(__nside, __nuplo, __ntransa, __ndiag, &__nm, &__nn, &__nalpha, __na + offseta, &__nlda, __nb + offsetb, &__nldb);
done:
  if (__nb) (*env)->ReleasePrimitiveArrayCritical(env, b, __nb, __failed ? JNI_ABORT : 0);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ndiag) (*env)->ReleaseStringUTFChars(env, diag, __ndiag);
  if (__ntransa) (*env)->ReleaseStringUTFChars(env, transa, __ntransa);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__nside) (*env)->ReleaseStringUTFChars(env, side, __nside);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*dtrsv_)(const char *uplo, const char *trans, const char *diag, int *n, double *a, int *lda, double *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtrsvK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jstring trans, jstring diag, jint n, jdoubleArray a, jint offseta, jint lda, jdoubleArray x, jint offsetx, jint incx) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  const char *__ntrans = NULL;
  const char *__ndiag = NULL;
  int __nn __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  double *__na = NULL;
  double *__nx = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndiag = (*env)->GetStringUTFChars(env, diag, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nlda = lda;
  __nincx = incx;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  dtrsv_(__nuplo, __ntrans, __ndiag, &__nn, __na + offseta, &__nlda, __nx + offsetx, &__nincx);
done:
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, __failed ? JNI_ABORT : 0);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ndiag) (*env)->ReleaseStringUTFChars(env, diag, __ndiag);
  if (__ntrans) (*env)->ReleaseStringUTFChars(env, trans, __ntrans);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static void (*strsv_)(const char *uplo, const char *trans, const char *diag, int *n, float *a, int *lda, float *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_strsvK(JNIEnv *env, UNUSED jobject obj, jstring uplo, jstring trans, jstring diag, jint n, jfloatArray a, jint offseta, jint lda, jfloatArray x, jint offsetx, jint incx) {
  jboolean __failed = FALSE;
  const char *__nuplo = NULL;
  const char *__ntrans = NULL;
  const char *__ndiag = NULL;
  int __nn __attribute__((aligned(8)));
  int __nlda __attribute__((aligned(8)));
  int __nincx __attribute__((aligned(8)));
  float *__na = NULL;
  float *__nx = NULL;
  if (!(__nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) { __failed = TRUE; goto done; }
  if (!(__ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) { __failed = TRUE; goto done; }
  if (!(__ndiag = (*env)->GetStringUTFChars(env, diag, NULL))) { __failed = TRUE; goto done; }
  __nn = n;
  __nlda = lda;
  __nincx = incx;
  if (!(__na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) { __failed = TRUE; goto done; }
  if (!(__nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) { __failed = TRUE; goto done; }
  strsv_(__nuplo, __ntrans, __ndiag, &__nn, __na + offseta, &__nlda, __nx + offsetx, &__nincx);
done:
  if (__nx) (*env)->ReleasePrimitiveArrayCritical(env, x, __nx, __failed ? JNI_ABORT : 0);
  if (__na) (*env)->ReleasePrimitiveArrayCritical(env, a, __na, JNI_ABORT);
  if (__ndiag) (*env)->ReleaseStringUTFChars(env, diag, __ndiag);
  if (__ntrans) (*env)->ReleaseStringUTFChars(env, trans, __ntrans);
  if (__nuplo) (*env)->ReleaseStringUTFChars(env, uplo, __nuplo);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static int (*idamax_)(int *n, double *dx, int *incdx);

jint Java_dev_ludovic_netlib_blas_JNIBLAS_idamaxK(JNIEnv *env, UNUSED jobject obj, jint n, jdoubleArray dx, jint offsetdx, jint incdx) {
  jint __ret = 0;
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nincdx __attribute__((aligned(8)));
  double *__ndx = NULL;
  __nn = n;
  __nincdx = incdx;
  if (!(__ndx = (*env)->GetPrimitiveArrayCritical(env, dx, NULL))) { __failed = TRUE; goto done; }
  __ret = idamax_(&__nn, __ndx + offsetdx, &__nincdx);
done:
  if (__ndx) (*env)->ReleasePrimitiveArrayCritical(env, dx, __ndx, JNI_ABORT);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
  return __ret;
}

static int (*isamax_)(int *n, float *sx, int *incsx);

jint Java_dev_ludovic_netlib_blas_JNIBLAS_isamaxK(JNIEnv *env, UNUSED jobject obj, jint n, jfloatArray sx, jint offsetsx, jint incsx) {
  jint __ret = 0;
  jboolean __failed = FALSE;
  int __nn __attribute__((aligned(8)));
  int __nincsx __attribute__((aligned(8)));
  float *__nsx = NULL;
  __nn = n;
  __nincsx = incsx;
  if (!(__nsx = (*env)->GetPrimitiveArrayCritical(env, sx, NULL))) { __failed = TRUE; goto done; }
  __ret = isamax_(&__nn, __nsx + offsetsx, &__nincsx);
done:
  if (__nsx) (*env)->ReleasePrimitiveArrayCritical(env, sx, __nsx, JNI_ABORT);
  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
  return __ret;
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

  LOAD_SYMBOL(dasum_);
  LOAD_SYMBOL(sasum_);
  LOAD_SYMBOL(daxpy_);
  LOAD_SYMBOL(saxpy_);
  LOAD_SYMBOL(dcopy_);
  LOAD_SYMBOL(scopy_);
  LOAD_SYMBOL(ddot_);
  LOAD_SYMBOL(sdot_);
  LOAD_SYMBOL(sdsdot_);
  LOAD_SYMBOL(dgbmv_);
  LOAD_SYMBOL(sgbmv_);
  LOAD_SYMBOL(dgemm_);
  LOAD_SYMBOL(sgemm_);
  LOAD_SYMBOL(dgemv_);
  LOAD_SYMBOL(sgemv_);
  LOAD_SYMBOL(dger_);
  LOAD_SYMBOL(sger_);
  LOAD_SYMBOL(dnrm2_);
  LOAD_SYMBOL(snrm2_);
  LOAD_SYMBOL(drot_);
  LOAD_SYMBOL(srot_);
  LOAD_SYMBOL(drotm_);
  LOAD_SYMBOL(srotm_);
  LOAD_SYMBOL(drotmg_);
  LOAD_SYMBOL(srotmg_);
  LOAD_SYMBOL(dsbmv_);
  LOAD_SYMBOL(ssbmv_);
  LOAD_SYMBOL(dscal_);
  LOAD_SYMBOL(sscal_);
  LOAD_SYMBOL(dspmv_);
  LOAD_SYMBOL(sspmv_);
  LOAD_SYMBOL(dspr_);
  LOAD_SYMBOL(sspr_);
  LOAD_SYMBOL(dspr2_);
  LOAD_SYMBOL(sspr2_);
  LOAD_SYMBOL(dswap_);
  LOAD_SYMBOL(sswap_);
  LOAD_SYMBOL(dsymm_);
  LOAD_SYMBOL(ssymm_);
  LOAD_SYMBOL(dsymv_);
  LOAD_SYMBOL(ssymv_);
  LOAD_SYMBOL(dsyr_);
  LOAD_SYMBOL(ssyr_);
  LOAD_SYMBOL(dsyr2_);
  LOAD_SYMBOL(ssyr2_);
  LOAD_SYMBOL(dsyr2k_);
  LOAD_SYMBOL(ssyr2k_);
  LOAD_SYMBOL(dsyrk_);
  LOAD_SYMBOL(ssyrk_);
  LOAD_SYMBOL(dtbmv_);
  LOAD_SYMBOL(stbmv_);
  LOAD_SYMBOL(dtbsv_);
  LOAD_SYMBOL(stbsv_);
  LOAD_SYMBOL(dtpmv_);
  LOAD_SYMBOL(stpmv_);
  LOAD_SYMBOL(dtpsv_);
  LOAD_SYMBOL(stpsv_);
  LOAD_SYMBOL(dtrmm_);
  LOAD_SYMBOL(strmm_);
  LOAD_SYMBOL(dtrmv_);
  LOAD_SYMBOL(strmv_);
  LOAD_SYMBOL(dtrsm_);
  LOAD_SYMBOL(strsm_);
  LOAD_SYMBOL(dtrsv_);
  LOAD_SYMBOL(strsv_);
  LOAD_SYMBOL(idamax_);
  LOAD_SYMBOL(isamax_);

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
  if (!get_system_property(env, (*env)->NewStringUTF(env, "dev.ludovic.netlib.blas.nativeLibPath"), NULL, &property_nativeLibPath)) {
    return -1;
  }
  jstring property_nativeLib;
  if (!get_system_property(env, (*env)->NewStringUTF(env, "dev.ludovic.netlib.blas.nativeLib"), (*env)->NewStringUTF(env, "libblas.so.3"), &property_nativeLib)) {
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
