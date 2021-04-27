/*
 * Copyright 2020, 2021, Ludovic Henry
 *
 * Permission is hereby granted, free of intge, to any person obtaining a copy
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
#include <stdio.h>
#include <string.h>
#include <dlfcn.h>

#include "dev_ludovic_netlib_blas_JNIBLAS.h"

#define UNUSED __attribute__((unused))

static jthrowable fail(JNIEnv *env, const char *klass_name, const char *msg) {
  jclass klass = (*env)->FindClass(env, klass_name);
  jmethodID constructor = (*env)->GetMethodID(env, klass, "<init>", "(Ljava/lang/String;)V");
  return (*env)->NewObject(env, klass, constructor, (*env)->NewStringUTF(env, msg));
}

static jthrowable failOnCopy(JNIEnv *env) {
  return fail(env, "java/lang/OutOfMemoryError", "Failed to copy from heap to native memory");
}

enum {
  CBLAS_ROW_MAJOR = 101,
  CBLAS_COL_MAJOR = 102,

  CBLAS_NO_TRANS = 111,
  CBLAS_TRANS = 112,
  CBLAS_CONJ_TRANS = 113,

  CBLAS_UPPER = 121,
  CBLAS_LOWER = 122,

  CBLAS_NON_UNIT = 131,
  CBLAS_UNIT = 132,

  CBLAS_LEFT = 141,
  CBLAS_RIGHT = 142,
};

static int translate_trans(JNIEnv *env, jstring str, jthrowable *exc) {
  int res = 0;
  const char *utf = NULL;
  *exc = NULL;
  if ((*env)->GetStringUTFLength(env, str) != 1) goto failOnUnknownOp;
  if (!(utf = (*env)->GetStringUTFChars(env, str, NULL))) goto failOnCopy;
  switch (utf[0]) {
  case 'N': res = CBLAS_NO_TRANS; break;
  case 'T': res = CBLAS_TRANS; break;
  case 'C': res = CBLAS_CONJ_TRANS; break;
  default: goto failOnUnknownOp;
  }
done:
  if (utf) (*env)->ReleaseStringUTFChars(env, str, utf);
  return res;
failOnUnknownOp:
  *exc = fail(env, "java/lang/IllegalArgumentException", "Unknown transpose");
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static int translate_uplo(JNIEnv *env, jstring str, jthrowable *exc) {
  int res = 0;
  const char *utf = NULL;
  *exc = NULL;
  if ((*env)->GetStringUTFLength(env, str) <= 0) goto failOnUnknownOp;
  if (!(utf = (*env)->GetStringUTFChars(env, str, NULL))) goto failOnCopy;
  switch (utf[0]) {
  case 'U': res = CBLAS_UPPER; break;
  case 'L': res = CBLAS_LOWER; break;
  default: goto failOnUnknownOp;
  }
done:
  if (utf) (*env)->ReleaseStringUTFChars(env, str, utf);
  return res;
failOnUnknownOp:
  *exc = fail(env, "java/lang/IllegalArgumentException", "Unknown uplo");
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static int translate_diag(JNIEnv *env, jstring str, jthrowable *exc) {
  int res = 0;
  const char *utf = NULL;
  *exc = NULL;
  if ((*env)->GetStringUTFLength(env, str) <= 0) goto failOnUnknownOp;
  if (!(utf = (*env)->GetStringUTFChars(env, str, NULL))) goto failOnCopy;
  switch (utf[0]) {
  case 'N': res = CBLAS_NON_UNIT; break;
  case 'U': res = CBLAS_UNIT; break;
  default: goto failOnUnknownOp;
  }
done:
  if (utf) (*env)->ReleaseStringUTFChars(env, str, utf);
  return res;
failOnUnknownOp:
  *exc = fail(env, "java/lang/IllegalArgumentException", "Unknown diag");
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static int translate_side(JNIEnv *env, jstring str, jthrowable *exc) {
  int res = 0;
  const char *utf = NULL;
  *exc = NULL;
  if ((*env)->GetStringUTFLength(env, str) <= 0) goto failOnUnknownOp;
  if (!(utf = (*env)->GetStringUTFChars(env, str, NULL))) goto failOnCopy;
  switch (utf[0]) {
  case 'L': res = CBLAS_LEFT; break;
  case 'R': res = CBLAS_RIGHT; break;
  default: goto failOnUnknownOp;
  }
done:
  if (utf) (*env)->ReleaseStringUTFChars(env, str, utf);
  return res;
failOnUnknownOp:
  *exc = fail(env, "java/lang/IllegalArgumentException", "Unknown side");
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static double (*cblas_dasum)(int n, double *x, int incx);

jdouble Java_dev_ludovic_netlib_blas_JNIBLAS_dasumK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray x, jint offsetx, jint incx) {
  jdouble ret;
  jthrowable excv = NULL, *exc = &excv;
  double *nx = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  ret = cblas_dasum(n, nx + offsetx, incx);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (*exc) (*env)->Throw(env, *exc);
  return ret;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static float (*cblas_sasum)(int n, float *x, int incx);

jfloat Java_dev_ludovic_netlib_blas_JNIBLAS_sasumK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray x, jint offsetx, jint incx) {
  jfloat ret;
  jthrowable excv = NULL, *exc = &excv;
  float *nx = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  ret = cblas_sasum(n, nx + offsetx, incx);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (*exc) (*env)->Throw(env, *exc);
  return ret;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_daxpy)(int n, double alpha, double *x, int incx, double *y, int incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_daxpyK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy) {
  jthrowable excv = NULL, *exc = &excv;
  double *nx = NULL, *ny = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  cblas_daxpy(n, alpha, nx + offsetx, incx, ny + offsety, incy);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_saxpy)(int n, float alpha, float *x, int incx, float *y, int incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_saxpyK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy) {
  jthrowable excv = NULL, *exc = &excv;
  float *nx = NULL, *ny = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  cblas_saxpy(n, alpha, nx + offsetx, incx, ny + offsety, incy);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_dcopy)(int n, double *x, int incx, double *y, int incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dcopyK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy) {
  jthrowable excv = NULL, *exc = &excv;
  double *nx = NULL, *ny = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  cblas_dcopy(n, nx + offsetx, incx, ny + offsety, incy);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_scopy)(int n, float *x, int incx, float *y, int incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_scopyK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy) {
  jthrowable excv = NULL, *exc = &excv;
  float *nx = NULL, *ny = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  cblas_scopy(n, nx + offsetx, incx, ny + offsety, incy);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static double (*cblas_ddot)(int n, double *x, int incx, double *y, int incy);

jdouble Java_dev_ludovic_netlib_blas_JNIBLAS_ddotK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy) {
  jdouble ret;
  jthrowable excv = NULL, *exc = &excv;
  double *nx = NULL, *ny = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  ret = cblas_ddot(n, nx + offsetx, incx, ny + offsety, incy);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, JNI_ABORT);
  if (*exc) (*env)->Throw(env, *exc);
  return ret;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static float (*cblas_sdot)(int n, float *x, int incx, float *y, int incy);

jfloat Java_dev_ludovic_netlib_blas_JNIBLAS_sdotK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy) {
  jfloat ret;
  jthrowable excv = NULL, *exc = &excv;
  float *nx = NULL, *ny = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  ret = cblas_sdot(n, nx + offsetx, incx, ny + offsety, incy);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, JNI_ABORT);
  if (*exc) (*env)->Throw(env, *exc);
  return ret;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static float (*cblas_sdsdot)(int n, float sb, float *sx, int incsx, float *sy, int incsy);

jfloat Java_dev_ludovic_netlib_blas_JNIBLAS_sdsdotK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloat sb, jfloatArray sx, jint offsetsx, jint incsx, jfloatArray sy, jint offsetsy, jint incsy) {
  jfloat ret;
  jthrowable excv = NULL, *exc = &excv;
  float *nsx = NULL, *nsy = NULL;
  if (!(nsx = (*env)->GetPrimitiveArrayCritical(env, sx, NULL))) goto failOnCopy;
  if (!(nsy = (*env)->GetPrimitiveArrayCritical(env, sy, NULL))) goto failOnCopy;
  ret = cblas_sdsdot(n, sb, nsx + offsetsx, incsx, nsy + offsetsy, incsy);
done:
  if (nsx) (*env)->ReleasePrimitiveArrayCritical(env, sx, nsx, JNI_ABORT);
  if (nsy) (*env)->ReleasePrimitiveArrayCritical(env, sy, nsy, JNI_ABORT);
  if (*exc) (*env)->Throw(env, *exc);
  return ret;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_dgbmv)(int order, int trans, int m, int n, int kl, int ku, double alpha, double *a, int lda, double *x, int incx, double beta, double *y, int incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dgbmvK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint m, jint n, jint kl, jint ku, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray x, jint offsetx, jint incx, jdouble beta, jdoubleArray y, jint offsety, jint incy) {
  jthrowable excv = NULL, *exc = &excv;
  int ntrans; double *na = NULL, *nx = NULL, *ny = NULL;
  if (!(ntrans = translate_trans(env, trans, exc))) goto failOnTranslate;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  cblas_dgbmv(CBLAS_COL_MAJOR, ntrans, m, n, kl, ku, alpha, na + offseta, lda, nx + offsetx, incx, beta, ny + offsety, incy);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_trans */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_sgbmv)(int order, int trans, int m, int n, int kl, int ku, float alpha, float *a, int lda, float *x, int incx, float beta, float *y, int incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sgbmvK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint m, jint n, jint kl, jint ku, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray x, jint offsetx, jint incx, jfloat beta, jfloatArray y, jint offsety, jint incy) {
  jthrowable excv = NULL, *exc = &excv;
  int ntrans; float *na = NULL, *nx = NULL, *ny = NULL;
  if (!(ntrans = translate_trans(env, trans, exc))) goto failOnTranslate;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  cblas_sgbmv(CBLAS_COL_MAJOR, ntrans, m, n, kl, ku, alpha, na + offseta, lda, nx + offsetx, incx, beta, ny + offsety, incy);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_trans */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_dgemm)(int order, int transa, int transb, int m, int n, int k, double alpha, double *a, int lda, double *b, int ldb, double beta, double *c, int ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dgemmK(JNIEnv *env, UNUSED jobject obj,
    jstring transa, jstring transb, jint m, jint n, jint k, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdouble beta, jdoubleArray c, jint offsetc, jint ldc) {
  jthrowable excv = NULL, *exc = &excv;
  int ntransa, ntransb; double *na = NULL, *nb = NULL, *nc = NULL;
  if (!(ntransa = translate_trans(env, transa, exc))) goto failOnTranslate;
  if (!(ntransb = translate_trans(env, transb, exc))) goto failOnTranslate;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  if (!(nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) goto failOnCopy;
  if (!(nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) goto failOnCopy;
  cblas_dgemm(CBLAS_COL_MAJOR, ntransa, ntransb, m, n, k, alpha, na + offseta, lda, nb + offsetb, ldb, beta, nc + offsetc, ldc);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nb) (*env)->ReleasePrimitiveArrayCritical(env, b, nb, JNI_ABORT);
  if (nc) (*env)->ReleasePrimitiveArrayCritical(env, c, nc, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_trans */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_sgemm)(int order, int transa, int transb, int m, int n, int k, float alpha, float *a, int lda, float *b, int ldb, float beta, float *c, int ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sgemmK(JNIEnv *env, UNUSED jobject obj,
    jstring transa, jstring transb, jint m, jint n, jint k, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloat beta, jfloatArray c, jint offsetc, jint ldc) {
  jthrowable excv = NULL, *exc = &excv;
  int ntransa, ntransb; float *na = NULL, *nb = NULL, *nc = NULL;
  if (!(ntransa = translate_trans(env, transa, exc))) goto failOnTranslate;
  if (!(ntransb = translate_trans(env, transb, exc))) goto failOnTranslate;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  if (!(nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) goto failOnCopy;
  if (!(nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) goto failOnCopy;
  cblas_sgemm(CBLAS_COL_MAJOR, ntransa, ntransb, m, n, k, alpha, na + offseta, lda, nb + offsetb, ldb, beta, nc + offsetc, ldc);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nb) (*env)->ReleasePrimitiveArrayCritical(env, b, nb, JNI_ABORT);
  if (nc) (*env)->ReleasePrimitiveArrayCritical(env, c, nc, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_trans */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_dgemv)(int order, int trans, int m, int n, double alpha, double *a, int lda, double *x, int incx, double beta, double *y, int incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dgemvK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint m, jint n, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray x, jint offsetx, jint incx, jdouble beta, jdoubleArray y, jint offsety, jint incy) {
  jthrowable excv = NULL, *exc = &excv;
  int ntrans; jdouble *na = NULL, *nx = NULL, *ny = NULL;
  if (!(ntrans = translate_trans(env, trans, exc))) goto failOnTranslate;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  cblas_dgemv(CBLAS_COL_MAJOR, ntrans, m, n, alpha, na + offseta, lda, nx + offsetx, incx, beta, ny + offsety, incy);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_trans */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_sgemv)(int order, int trans, int m, int n, float alpha, float *a, int lda, float *x, int incx, float beta, float *y, int incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sgemvK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint m, jint n, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray x, jint offsetx, jint incx, jfloat beta, jfloatArray y, jint offsety, jint incy) {
  jthrowable excv = NULL, *exc = &excv;
  int ntrans; float *na = NULL, *nx = NULL, *ny = NULL;
  if (!(ntrans = translate_trans(env, trans, exc))) goto failOnTranslate;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  cblas_sgemv(CBLAS_COL_MAJOR, ntrans, m, n, alpha, na + offseta, lda, nx + offsetx, incx, beta, ny + offsety, incy);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_trans */
goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_dger)(int order, int m, int n, double alpha, double *x, int incx, double *y, int incy, double *a, int lda);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dgerK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy, jdoubleArray a, jint offseta, jint lda) {
  jthrowable excv = NULL, *exc = &excv;
  double *na = NULL, *nx = NULL, *ny = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  cblas_dger(CBLAS_COL_MAJOR, m, n, alpha, nx + offsetx, incx, ny + offsety, incy, na + offseta, lda);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_sger)(int order, int m, int n, float alpha, float *x, int incx, float *y, int incy, float *a, int lda);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sgerK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy, jfloatArray a, jint offseta, jint lda) {
  jthrowable excv = NULL, *exc = &excv;
  float *na = NULL, *nx = NULL, *ny = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  cblas_sger(CBLAS_COL_MAJOR, m, n, alpha, nx + offsetx, incx, ny + offsety, incy, na + offseta, lda);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static double (*cblas_dnrm2)(int n, double *x, int incx);

jdouble Java_dev_ludovic_netlib_blas_JNIBLAS_dnrm2K(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray x, jint offsetx, jint incx) {
  jdouble ret;
  jthrowable excv = NULL, *exc = &excv;
  double *nx = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  ret = cblas_dnrm2(n, nx + offsetx, incx);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (*exc) (*env)->Throw(env, *exc);
  return ret;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static float (*cblas_snrm2)(int n, float *x, int incx);

jfloat Java_dev_ludovic_netlib_blas_JNIBLAS_snrm2K(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray x, jint offsetx, jint incx) {
  jfloat ret;
  jthrowable excv = NULL, *exc = &excv;
  float *nx = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  ret = cblas_snrm2(n, nx + offsetx, incx);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (*exc) (*env)->Throw(env, *exc);
  return ret;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_drot)(int n, double *dx, int incx, double *dy, int incy, double c, double s);

void Java_dev_ludovic_netlib_blas_JNIBLAS_drotK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray dx, jint offsetdx, jint incx, jdoubleArray dy, jint offsetdy, jint incy, jdouble c, jdouble s) {
  jthrowable excv = NULL, *exc = &excv;
  double *ndx = NULL, *ndy = NULL;
  if (!(ndx = (*env)->GetPrimitiveArrayCritical(env, dx, NULL))) goto failOnCopy;
  if (!(ndy = (*env)->GetPrimitiveArrayCritical(env, dy, NULL))) goto failOnCopy;
  cblas_drot(n, ndx + offsetdx, incx, ndy + offsetdy, incy, c, s);
done:
  if (ndx) (*env)->ReleasePrimitiveArrayCritical(env, dx, ndx, 0);
  if (ndy) (*env)->ReleasePrimitiveArrayCritical(env, dy, ndy, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_srot)(int n, float *sx, int incx, float *sy, int incy, float c, float s);

void Java_dev_ludovic_netlib_blas_JNIBLAS_srotK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray sx, jint offsetsx, jint incx, jfloatArray sy, jint offsetsy, jint incy, jfloat c, jfloat s) {
  jthrowable excv = NULL, *exc = &excv;
  float *nsx = NULL, *nsy = NULL;
  if (!(nsx = (*env)->GetPrimitiveArrayCritical(env, sx, NULL))) goto failOnCopy;
  if (!(nsy = (*env)->GetPrimitiveArrayCritical(env, sy, NULL))) goto failOnCopy;
  cblas_srot(n, nsx + offsetsx, incx, nsy + offsetsy, incy, c, s);
done:
  if (nsx) (*env)->ReleasePrimitiveArrayCritical(env, sx, nsx, 0);
  if (nsy) (*env)->ReleasePrimitiveArrayCritical(env, sy, nsy, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_drotm)(int n, double *dx, int incx, double *dy, int incy, double *dparam);

void Java_dev_ludovic_netlib_blas_JNIBLAS_drotmK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray dx, jint offsetdx, jint incx, jdoubleArray dy, jint offsetdy, jint incy, jdoubleArray dparam, jint offsetdparam) {
  jthrowable excv = NULL, *exc = &excv;
  double *ndx = NULL, *ndy = NULL, *ndparam = NULL;
  if (!(ndx = (*env)->GetPrimitiveArrayCritical(env, dx, NULL))) goto failOnCopy;
  if (!(ndy = (*env)->GetPrimitiveArrayCritical(env, dy, NULL))) goto failOnCopy;
  if (!(ndparam = (*env)->GetPrimitiveArrayCritical(env, dparam, NULL))) goto failOnCopy;
  cblas_drotm(n, ndx + offsetdx, incx, ndy + offsetdy, incy, ndparam + offsetdparam);
done:
  if (ndx) (*env)->ReleasePrimitiveArrayCritical(env, dx, ndx, JNI_ABORT);
  if (ndy) (*env)->ReleasePrimitiveArrayCritical(env, dy, ndy, 0);
  if (ndparam) (*env)->ReleasePrimitiveArrayCritical(env, dparam, ndparam, JNI_ABORT);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_srotm)(int n, float *sx, int incx, float *sy, int incy, float *sparam);

void Java_dev_ludovic_netlib_blas_JNIBLAS_srotmK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray sx, jint offsetsx, jint incx, jfloatArray sy, jint offsetsy, jint incy, jfloatArray sparam, jint offsetsparam) {
  jthrowable excv = NULL, *exc = &excv;
  float *nsx = NULL, *nsy = NULL, *nsparam = NULL;
  if (!(nsx = (*env)->GetPrimitiveArrayCritical(env, sx, NULL))) goto failOnCopy;
  if (!(nsy = (*env)->GetPrimitiveArrayCritical(env, sy, NULL))) goto failOnCopy;
  if (!(nsparam = (*env)->GetPrimitiveArrayCritical(env, sparam, NULL))) goto failOnCopy;
  cblas_srotm(n, nsx + offsetsx, incx, nsy + offsetsy, incy, nsparam + offsetsparam);
done:
  if (nsx) (*env)->ReleasePrimitiveArrayCritical(env, sx, nsx, JNI_ABORT);
  if (nsy) (*env)->ReleasePrimitiveArrayCritical(env, sy, nsy, 0);
  if (nsparam) (*env)->ReleasePrimitiveArrayCritical(env, sparam, nsparam, JNI_ABORT);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_drotmg)(jobject dd1, jobject dd2, jobject dx1, double dy1, double *dparam);

void Java_dev_ludovic_netlib_blas_JNIBLAS_drotmgK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jobject dd1, UNUSED jobject dd2, UNUSED jobject dx1, UNUSED jdouble dy1, UNUSED jdoubleArray dparam, UNUSED jint offsetdparam) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*cblas_srotmg)(jobject sd1, jobject sd2, jobject sx1, float sy1, float *sparam);

void Java_dev_ludovic_netlib_blas_JNIBLAS_srotmgK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jobject sd1, UNUSED jobject sd2, UNUSED jobject sx1, UNUSED jfloat sy1, UNUSED jfloatArray sparam, UNUSED jint offsetsparam) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*cblas_dsbmv)(int order, int uplo, int n, int k, double alpha, double *a, int lda, double *x, int incx, double beta, double *y, int incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsbmvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jint n, UNUSED jint k, UNUSED jdouble alpha, UNUSED jdoubleArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jdoubleArray x, UNUSED jint offsetx, UNUSED jint incx, UNUSED jdouble beta, UNUSED jdoubleArray y, UNUSED jint offsety, UNUSED jint incy) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*cblas_ssbmv)(int order, int uplo, int n, int k, float alpha, float *a, int lda, float *x, int incx, float beta, float *y, int incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssbmvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jint n, UNUSED jint k, UNUSED jfloat alpha, UNUSED jfloatArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jfloatArray x, UNUSED jint offsetx, UNUSED jint incx, UNUSED jfloat beta, UNUSED jfloatArray y, UNUSED jint offsety, UNUSED jint incy) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*cblas_dscal)(int n, double alpha, double *x, int incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dscalK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx) {
  jthrowable excv = NULL, *exc = &excv;
  double *nx = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  cblas_dscal(n, alpha, nx + offsetx, incx);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_sscal)(int n, float alpha, float *x, int incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sscalK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx) {
  jthrowable excv = NULL, *exc = &excv;
  float *nx = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  cblas_sscal(n, alpha, nx + offsetx, incx);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_dspmv)(int order, int uplo, int n, double alpha, double *a, double *x, int incx, double beta, double *y, int incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dspmvK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdouble alpha, jdoubleArray a, jint offseta, jdoubleArray x, jint offsetx, jint incx, jdouble beta, jdoubleArray y, jint offsety, jint incy) {
  jthrowable excv = NULL, *exc = &excv;
  int nuplo; double *na = NULL, *nx = NULL, *ny = NULL;
  if (!(nuplo = translate_uplo(env, uplo, exc))) goto failOnTranslate;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  cblas_dspmv(CBLAS_COL_MAJOR, nuplo, n, alpha, na + offseta, nx + offsetx, incx, beta, ny + offsety, incy);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_uplo */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_sspmv)(int order, int uplo, int n, float alpha, float *a, float *x, int incx, float beta, float *y, int incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sspmvK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloat alpha, jfloatArray a, jint offseta, jfloatArray x, jint offsetx, jint incx, jfloat beta, jfloatArray y, jint offsety, jint incy) {
  jthrowable excv = NULL, *exc = &excv;
  int nuplo; float *na = NULL, *nx = NULL, *ny = NULL;
  if (!(nuplo = translate_uplo(env, uplo, exc))) goto failOnTranslate;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  cblas_sspmv(CBLAS_COL_MAJOR, nuplo, n, alpha, na + offseta, nx + offsetx, incx, beta, ny + offsety, incy);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_uplo */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_dspr)(int order, int uplo, int n, double alpha, double *x, int incx, double *a);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsprK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx, jdoubleArray a, jint offseta) {
  jthrowable excv = NULL, *exc = &excv;
  int nuplo; double *na = NULL, *nx = NULL;
  if (!(nuplo = translate_uplo(env, uplo, exc))) goto failOnTranslate;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  cblas_dspr(CBLAS_COL_MAJOR, nuplo, n, alpha, nx + offsetx, incx, na + offseta);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_uplo */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_sspr)(int order, int uplo, int n, float alpha, float *x, int incx, float *a);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssprK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx, jfloatArray a, jint offseta) {
  jthrowable excv = NULL, *exc = &excv;
  int nuplo; float *na = NULL, *nx = NULL;
  if (!(nuplo = translate_uplo(env, uplo, exc))) goto failOnTranslate;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  cblas_sspr(CBLAS_COL_MAJOR, nuplo, n, alpha, nx + offsetx, incx, na + offseta);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_uplo */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_dspr2)(int order, int uplo, int n, double alpha, double *x, int incx, double *y, int incy, double *a);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dspr2K(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy, jdoubleArray a, jint offseta) {
  jthrowable excv = NULL, *exc = &excv;
  int nuplo; double *na = NULL, *nx = NULL, *ny = NULL;
  if (!(nuplo = translate_uplo(env, uplo, exc))) goto failOnTranslate;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  cblas_dspr2(CBLAS_COL_MAJOR, nuplo, n, alpha, nx + offsetx, incx, ny + offsety, incy, na + offseta);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_uplo */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_sspr2)(int order, int uplo, int n, float alpha, float *x, int incx, float *y, int incy, float *a);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sspr2K(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy, jfloatArray a, jint offseta) {
  jthrowable excv = NULL, *exc = &excv;
  int nuplo; float *na = NULL, *nx = NULL, *ny = NULL;
  if (!(nuplo = translate_uplo(env, uplo, exc))) goto failOnTranslate;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  cblas_sspr2(CBLAS_COL_MAJOR, nuplo, n, alpha, nx + offsetx, incx, ny + offsety, incy, na + offseta);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_uplo */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_dswap)(int n, double *x, int incx, double *y, int incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dswapK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy) {
  jthrowable excv = NULL, *exc = &excv;
  double *nx = NULL, *ny = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  cblas_dswap(n, nx + offsetx, incx, ny + offsety, incy);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, 0);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_sswap)(int n, float *x, int incx, float *y, int incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sswapK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy) {
  jthrowable excv = NULL, *exc = &excv;
  float *nx = NULL, *ny = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  cblas_sswap(n, nx + offsetx, incx, ny + offsety, incy);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, 0);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_dsymm)(int order, int side, int uplo, int m, int n, double alpha, double *a, int lda, double *b, int ldb, double beta, double *c, int ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsymmK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring uplo, jint m, jint n, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdouble beta, jdoubleArray c, jint offsetc, jint ldc) {
  jthrowable excv = NULL, *exc = &excv;
  int nside, nuplo; double *na = NULL, *nb = NULL, *nc = NULL;
  if (!(nside = translate_side(env, side, exc))) goto failOnTranslate;
  if (!(nuplo = translate_uplo(env, uplo, exc))) goto failOnTranslate;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  if (!(nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) goto failOnCopy;
  if (!(nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) goto failOnCopy;
  cblas_dsymm(CBLAS_COL_MAJOR, nside, nuplo, m, n, alpha, na + offseta, lda, nb + offsetb, ldb, beta, nc + offsetc, ldc);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nb) (*env)->ReleasePrimitiveArrayCritical(env, b, nb, JNI_ABORT);
  if (nc) (*env)->ReleasePrimitiveArrayCritical(env, c, nc, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_side or translate_uplo */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_ssymm)(int order, int side, int uplo, int m, int n, float alpha, float *a, int lda, float *b, int ldb, float beta, float *c, int ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssymmK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring uplo, jint m, jint n, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloat beta, jfloatArray c, jint offsetc, jint ldc) {
  jthrowable excv = NULL, *exc = &excv;
  int nside, nuplo; float *na = NULL, *nb = NULL, *nc = NULL;
  if (!(nside = translate_side(env, side, exc))) goto failOnTranslate;
  if (!(nuplo = translate_uplo(env, uplo, exc))) goto failOnTranslate;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  if (!(nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) goto failOnCopy;
  if (!(nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) goto failOnCopy;
  cblas_ssymm(CBLAS_COL_MAJOR, nside, nuplo, m, n, alpha, na + offseta, lda, nb + offsetb, ldb, beta, nc + offsetc, ldc);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nb) (*env)->ReleasePrimitiveArrayCritical(env, b, nb, JNI_ABORT);
  if (nc) (*env)->ReleasePrimitiveArrayCritical(env, c, nc, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_side or translate_uplo */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_dsymv)(int order, int uplo, int n, double alpha, double *a, int lda, double *x, int incx, double beta, double *y, int incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsymvK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray x, jint offsetx, jint incx, jdouble beta, jdoubleArray y, jint offsety, jint incy) {
  jthrowable excv = NULL, *exc = &excv;
  int nuplo; double *na = NULL, *nx = NULL, *ny = NULL;
  if (!(nuplo = translate_uplo(env, uplo, exc))) goto failOnTranslate;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  cblas_dsymv(CBLAS_COL_MAJOR, nuplo, n, alpha, na + offseta, lda, nx + offsetx, incx, beta, ny + offsety, incy);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_uplo */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_ssymv)(int order, int uplo, int n, float alpha, float *a, int lda, float *x, int incx, float beta, float *y, int incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssymvK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray x, jint offsetx, jint incx, jfloat beta, jfloatArray y, jint offsety, jint incy) {
  jthrowable excv = NULL, *exc = &excv;
  int nuplo; float *na = NULL, *nx = NULL, *ny = NULL;
  if (!(nuplo = translate_uplo(env, uplo, exc))) goto failOnTranslate;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  cblas_ssymv(CBLAS_COL_MAJOR, nuplo, n, alpha, na + offseta, lda, nx + offsetx, incx, beta, ny + offsety, incy);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_uplo */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_dsyr)(int order, int uplo, int n, double alpha, double *x, int incx, double *a, int lda);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsyrK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx, jdoubleArray a, jint offseta, jint lda) {
  jthrowable excv = NULL, *exc = &excv;
  int nuplo; double *na = NULL, *nx = NULL;
  if (!(nuplo = translate_uplo(env, uplo, exc))) goto failOnTranslate;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  cblas_dsyr(CBLAS_COL_MAJOR, nuplo, n, alpha, nx + offsetx, incx, na + offseta, lda);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_uplo */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_ssyr)(int order, int uplo, int n, float alpha, float *x, int incx, float *a, int lda);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssyrK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx, jfloatArray a, jint offseta, jint lda) {
  jthrowable excv = NULL, *exc = &excv;
  int nuplo; float *na = NULL, *nx = NULL;
  if (!(nuplo = translate_uplo(env, uplo, exc))) goto failOnTranslate;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  cblas_ssyr(CBLAS_COL_MAJOR, nuplo, n, alpha, nx + offsetx, incx, na + offseta, lda);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_uplo */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_dsyr2)(int order, int uplo, int n, double alpha, double *x, int incx, double *y, int incy, double *a, int lda);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsyr2K(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy, jdoubleArray a, jint offseta, jint lda) {
  jthrowable excv = NULL, *exc = &excv;
  int nuplo; double *na = NULL, *nx = NULL, *ny = NULL;
  if (!(nuplo = translate_uplo(env, uplo, exc))) goto failOnTranslate;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  cblas_dsyr2(CBLAS_COL_MAJOR, nuplo, n, alpha, nx + offsetx, incx, ny + offsety, incy, na + offseta, lda);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_uplo */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_ssyr2)(int order, int uplo, int n, float alpha, float *x, int incx, float *y, int incy, float *a, int lda);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssyr2K(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy, jfloatArray a, jint offseta, jint lda) {
  jthrowable excv = NULL, *exc = &excv;
  int nuplo; float *na = NULL, *nx = NULL, *ny = NULL;
  if (!(nuplo = translate_uplo(env, uplo, exc))) goto failOnTranslate;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto failOnCopy;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto failOnCopy;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  cblas_ssyr2(CBLAS_COL_MAJOR, nuplo, n, alpha, nx + offsetx, incx, ny + offsety, incy, na + offseta, lda);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_uplo */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_dsyr2k)(int order, int uplo, int trans, int n, int k, double alpha, double *a, int lda, double *b, int ldb, double beta, double *c, int ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsyr2kK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jint n, jint k, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdouble beta, jdoubleArray c, jint offsetc, jint ldc) {
  jthrowable excv = NULL, *exc = &excv;
  int nuplo, ntrans; double *na = NULL, *nb = NULL, *nc = NULL;
  if (!(nuplo = translate_uplo(env, uplo, exc))) goto failOnTranslate;
  if (!(ntrans = translate_trans(env, trans, exc))) goto failOnTranslate;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  if (!(nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) goto failOnCopy;
  if (!(nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) goto failOnCopy;
  cblas_dsyr2k(CBLAS_COL_MAJOR, nuplo, ntrans, n, k, alpha, na + offseta, lda, nb + offsetb, ldb, beta, nc + offsetc, ldc);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nb) (*env)->ReleasePrimitiveArrayCritical(env, b, nb, JNI_ABORT);
  if (nc) (*env)->ReleasePrimitiveArrayCritical(env, c, nc, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_uplo or translate_trans */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_ssyr2k)(int order, int uplo, int trans, int n, int k, float alpha, float *a, int lda, float *b, int ldb, float beta, float *c, int ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssyr2kK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jint n, jint k, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloat beta, jfloatArray c, jint offsetc, jint ldc) {
  jthrowable excv = NULL, *exc = &excv;
  int nuplo, ntrans; float *na = NULL, *nb = NULL, *nc = NULL;
  if (!(nuplo = translate_uplo(env, uplo, exc))) goto failOnTranslate;
  if (!(ntrans = translate_trans(env, trans, exc))) goto failOnTranslate;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  if (!(nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) goto failOnCopy;
  if (!(nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) goto failOnCopy;
  cblas_ssyr2k(CBLAS_COL_MAJOR, nuplo, ntrans, n, k, alpha, na + offseta, lda, nb + offsetb, ldb, beta, nc + offsetc, ldc);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nb) (*env)->ReleasePrimitiveArrayCritical(env, b, nb, JNI_ABORT);
  if (nc) (*env)->ReleasePrimitiveArrayCritical(env, c, nc, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_uplo or translate_trans */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_dsyrk)(int order, int uplo, int trans, int n, int k, double alpha, double *a, int lda, double beta, double *c, int ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsyrkK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jint n, jint k, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdouble beta, jdoubleArray c, jint offsetc, jint ldc) {
  jthrowable excv = NULL, *exc = &excv;
  int nuplo, ntrans; double *na = NULL, *nc = NULL;
  if (!(nuplo = translate_uplo(env, uplo, exc))) goto failOnTranslate;
  if (!(ntrans = translate_trans(env, trans, exc))) goto failOnTranslate;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  if (!(nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) goto failOnCopy;
  cblas_dsyrk(CBLAS_COL_MAJOR, nuplo, ntrans, n, k, alpha, na + offseta, lda, beta, nc + offsetc, ldc);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nc) (*env)->ReleasePrimitiveArrayCritical(env, c, nc, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_uplo or translate_trans */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_ssyrk)(int order, int uplo, int trans, int n, int k, float alpha, float *a, int lda, float beta, float *c, int ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssyrkK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jint n, jint k, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloat beta, jfloatArray c, jint offsetc, jint ldc) {
  jthrowable excv = NULL, *exc = &excv;
  int nuplo, ntrans; float *na = NULL, *nc = NULL;
  if (!(nuplo = translate_uplo(env, uplo, exc))) goto failOnTranslate;
  if (!(ntrans = translate_trans(env, trans, exc))) goto failOnTranslate;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto failOnCopy;
  if (!(nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) goto failOnCopy;
  cblas_ssyrk(CBLAS_COL_MAJOR, nuplo, ntrans, n, k, alpha, na + offseta, lda, beta, nc + offsetc, ldc);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nc) (*env)->ReleasePrimitiveArrayCritical(env, c, nc, 0);
  if (*exc) (*env)->Throw(env, *exc);
  return;
failOnTranslate:
  /* exc has been set by translate_uplo or translate_trans */
  goto done;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void (*cblas_dtbmv)(int order, int uplo, int trans, int diag, int n, int k, double *a, int lda, double *x, int incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtbmvK(UNUSED JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jint k, UNUSED jdoubleArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jdoubleArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*cblas_stbmv)(int order, int uplo, int trans, int diag, int n, int k, float *a, int lda, float *x, int incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_stbmvK(UNUSED JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jint k, UNUSED jfloatArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jfloatArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*cblas_dtbsv)(int order, int uplo, int trans, int diag, int n, int k, double *a, int lda, double *x, int incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtbsvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jint k, UNUSED jdoubleArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jdoubleArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*cblas_stbsv)(int order, int uplo, int trans, int diag, int n, int k, float *a, int lda, float *x, int incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_stbsvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jint k, UNUSED jfloatArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jfloatArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*cblas_dtpmv)(int order, int uplo, int trans, int diag, int n, double *a, double *x, int incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtpmvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jdoubleArray a, UNUSED jint offseta, UNUSED jdoubleArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*cblas_stpmv)(int order, int uplo, int trans, int diag, int n, float *a, float *x, int incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_stpmvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jfloatArray a, UNUSED jint offseta, UNUSED jfloatArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*cblas_dtpsv)(int order, int uplo, int trans, int diag, int n, double *a, double *x, int incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtpsvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jdoubleArray a, UNUSED jint offseta, UNUSED jdoubleArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*cblas_stpsv)(int order, int uplo, int trans, int diag, int n, float *a, float *x, int incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_stpsvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jfloatArray a, UNUSED jint offseta, UNUSED jfloatArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*cblas_dtrmm)(int order, int side, int uplo, int transa, int diag, int m, int n, double alpha, double *a, int lda, double *b, int ldb);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtrmmK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring side, UNUSED jstring uplo, UNUSED jstring transa, UNUSED jstring diag, UNUSED jint m, UNUSED jint n, UNUSED jdouble alpha, UNUSED jdoubleArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jdoubleArray b, UNUSED jint offsetb, UNUSED jint ldb) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*cblas_strmm)(int order, int side, int uplo, int transa, int diag, int m, int n, float alpha, float *a, int lda, float *b, int ldb);

void Java_dev_ludovic_netlib_blas_JNIBLAS_strmmK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring side, UNUSED jstring uplo, UNUSED jstring transa, UNUSED jstring diag, UNUSED jint m, UNUSED jint n, UNUSED jfloat alpha, UNUSED jfloatArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jfloatArray b, UNUSED jint offsetb, UNUSED jint ldb) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*cblas_dtrmv)(int order, int uplo, int trans, int diag, int n, double *a, int lda, double *x, int incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtrmvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jdoubleArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jdoubleArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*cblas_strmv)(int order, int uplo, int trans, int diag, int n, float *a, int lda, float *x, int incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_strmvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jfloatArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jfloatArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*cblas_dtrsm)(int order, int side, int uplo, int transa, int diag, int m, int n, double alpha, double *a, int lda, double *b, int ldb);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtrsmK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring side, UNUSED jstring uplo, UNUSED jstring transa, UNUSED jstring diag, UNUSED jint m, UNUSED jint n, UNUSED jdouble alpha, UNUSED jdoubleArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jdoubleArray b, UNUSED jint offsetb, UNUSED jint ldb) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*cblas_strsm)(int order, int side, int uplo, int transa, int diag, int m, int n, float alpha, float *a, int lda, float *b, int ldb);

void Java_dev_ludovic_netlib_blas_JNIBLAS_strsmK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring side, UNUSED jstring uplo, UNUSED jstring transa, UNUSED jstring diag, UNUSED jint m, UNUSED jint n, UNUSED jfloat alpha, UNUSED jfloatArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jfloatArray b, UNUSED jint offsetb, UNUSED jint ldb) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*cblas_dtrsv)(int order, int uplo, int trans, int diag, int n, double *a, int lda, double *x, int incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtrsvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jdoubleArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jdoubleArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*cblas_strsv)(int order, int uplo, int trans, int diag, int n, float *a, int lda, float *x, int incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_strsvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jfloatArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jfloatArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static int (*cblas_idamax)(int n, double *dx, int incdx);

jint Java_dev_ludovic_netlib_blas_JNIBLAS_idamaxK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray dx, jint offsetdx, jint incdx) {
  jint ret;
  jthrowable excv = NULL, *exc = &excv;
  double *ndx = NULL;
  if (!(ndx = (*env)->GetPrimitiveArrayCritical(env, dx, NULL))) goto failOnCopy;
  ret = cblas_idamax(n, ndx + offsetdx, incdx);
done:
  if (ndx) (*env)->ReleasePrimitiveArrayCritical(env, dx, ndx, JNI_ABORT);
  if (*exc) (*env)->Throw(env, *exc);
  return ret;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static int (*cblas_isamax)(int n, float *sx, int incx);

jint Java_dev_ludovic_netlib_blas_JNIBLAS_isamaxK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray sx, jint offsetsx, jint incx) {
  jint ret;
  jthrowable excv = NULL, *exc = &excv;
  float *nsx = NULL;
  if (!(nsx = (*env)->GetPrimitiveArrayCritical(env, sx, NULL))) goto failOnCopy;
  ret = cblas_isamax(n, nsx + offsetsx, incx);
done:
  if (nsx) (*env)->ReleasePrimitiveArrayCritical(env, sx, nsx, JNI_ABORT);
  if (*exc) (*env)->Throw(env, *exc);
  return ret;
failOnCopy:
  *exc = failOnCopy(env);
  goto done;
}

static void *blas;

jstring get_system_property(JNIEnv *env, jstring key, jstring def) {
  jclass klass = (*env)->FindClass(env, "java/lang/System");
  jmethodID method = (*env)->GetStaticMethodID(env, klass, "getProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");
  return (jstring)(*env)->CallStaticObjectMethod(env, klass, method, key, def);
}

jint JNI_OnLoad(JavaVM *vm, UNUSED void *reserved) {
  JNIEnv *env;
  if ((*vm)->GetEnv(vm, (void**)&env, JNI_VERSION_1_6) != JNI_OK) {
    return -1;
  }

  char blas_name[1024];
  jstring native_lib_path = get_system_property(env, (*env)->NewStringUTF(env, "dev.ludovic.netlib.blas.nativeLibPath"), NULL);
  jstring native_lib = get_system_property(env, (*env)->NewStringUTF(env, "dev.ludovic.netlib.blas.nativeLib"), (*env)->NewStringUTF(env, "blas"));
  if (native_lib_path) {
    const char *utf = (*env)->GetStringUTFChars(env, native_lib_path, NULL);
    strncpy(blas_name, utf, sizeof(blas_name));
    blas_name[sizeof(blas_name) - 1] = '\0';
    (*env)->ReleaseStringUTFChars(env, native_lib_path, utf);
  } else {
    const char *utf = (*env)->GetStringUTFChars(env, native_lib, NULL);
    snprintf(blas_name, sizeof(blas_name), "lib%s.so", utf);
    (*env)->ReleaseStringUTFChars(env, native_lib, utf);
  }

  blas = dlopen(blas_name, RTLD_LAZY);
  if (!blas) {
    return -1;
  }

#define LOAD_SYMBOL(name) \
  name = dlsym(blas, #name); \
  if (!name) { \
    return -1; \
  }

  LOAD_SYMBOL(cblas_dasum);
  LOAD_SYMBOL(cblas_sasum);
  LOAD_SYMBOL(cblas_daxpy);
  LOAD_SYMBOL(cblas_saxpy);
  LOAD_SYMBOL(cblas_dcopy);
  LOAD_SYMBOL(cblas_scopy);
  LOAD_SYMBOL(cblas_ddot);
  LOAD_SYMBOL(cblas_sdot);
  LOAD_SYMBOL(cblas_sdsdot);
  LOAD_SYMBOL(cblas_dgbmv);
  LOAD_SYMBOL(cblas_sgbmv);
  LOAD_SYMBOL(cblas_dgemm);
  LOAD_SYMBOL(cblas_sgemm);
  LOAD_SYMBOL(cblas_dgemv);
  LOAD_SYMBOL(cblas_sgemv);
  LOAD_SYMBOL(cblas_dger);
  LOAD_SYMBOL(cblas_sger);
  LOAD_SYMBOL(cblas_dnrm2);
  LOAD_SYMBOL(cblas_snrm2);
  LOAD_SYMBOL(cblas_drot);
  LOAD_SYMBOL(cblas_srot);
  LOAD_SYMBOL(cblas_drotm);
  LOAD_SYMBOL(cblas_srotm);
  LOAD_SYMBOL(cblas_drotmg);
  LOAD_SYMBOL(cblas_srotmg);
  LOAD_SYMBOL(cblas_dsbmv);
  LOAD_SYMBOL(cblas_ssbmv);
  LOAD_SYMBOL(cblas_dscal);
  LOAD_SYMBOL(cblas_sscal);
  LOAD_SYMBOL(cblas_dspmv);
  LOAD_SYMBOL(cblas_sspmv);
  LOAD_SYMBOL(cblas_dspr);
  LOAD_SYMBOL(cblas_sspr);
  LOAD_SYMBOL(cblas_dspr2);
  LOAD_SYMBOL(cblas_sspr2);
  LOAD_SYMBOL(cblas_dswap);
  LOAD_SYMBOL(cblas_sswap);
  LOAD_SYMBOL(cblas_dsymm);
  LOAD_SYMBOL(cblas_ssymm);
  LOAD_SYMBOL(cblas_dsymv);
  LOAD_SYMBOL(cblas_ssymv);
  LOAD_SYMBOL(cblas_dsyr);
  LOAD_SYMBOL(cblas_ssyr);
  LOAD_SYMBOL(cblas_dsyr2);
  LOAD_SYMBOL(cblas_ssyr2);
  LOAD_SYMBOL(cblas_dsyr2k);
  LOAD_SYMBOL(cblas_ssyr2k);
  LOAD_SYMBOL(cblas_dsyrk);
  LOAD_SYMBOL(cblas_ssyrk);
  LOAD_SYMBOL(cblas_dtbmv);
  LOAD_SYMBOL(cblas_stbmv);
  LOAD_SYMBOL(cblas_dtbsv);
  LOAD_SYMBOL(cblas_stbsv);
  LOAD_SYMBOL(cblas_dtpmv);
  LOAD_SYMBOL(cblas_stpmv);
  LOAD_SYMBOL(cblas_dtpsv);
  LOAD_SYMBOL(cblas_stpsv);
  LOAD_SYMBOL(cblas_dtrmm);
  LOAD_SYMBOL(cblas_strmm);
  LOAD_SYMBOL(cblas_dtrmv);
  LOAD_SYMBOL(cblas_strmv);
  LOAD_SYMBOL(cblas_dtrsm);
  LOAD_SYMBOL(cblas_strsm);
  LOAD_SYMBOL(cblas_dtrsv);
  LOAD_SYMBOL(cblas_strsv);
  LOAD_SYMBOL(cblas_idamax);
  LOAD_SYMBOL(cblas_isamax);

#undef LOAD_SYMBOL

  return JNI_VERSION_1_6;
}

void JNI_OnUnload(UNUSED JavaVM *vm, UNUSED void *reserved) {
  dlclose(blas);
}
