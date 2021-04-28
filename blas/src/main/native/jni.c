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
#include <string.h>
#include <dlfcn.h>

#include "dev_ludovic_netlib_blas_JNIBLAS.h"

#define UNUSED __attribute__((unused))

#define TRUE 1
#define FALSE 0

static void throwOOM(JNIEnv *env) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/OutOfMemoryError"), "Failed to copy from heap to native memory");
}

static double (*dasum_)(int *n, double *x, int *incx);

jdouble Java_dev_ludovic_netlib_blas_JNIBLAS_dasumK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray x, jint offsetx, jint incx) {
  jdouble ret;
  jboolean failed = FALSE;
  double *nx = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  ret = dasum_(&n, nx + offsetx, &incx);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (failed) throwOOM(env);
  return ret;
fail:
  failed = TRUE;
  goto done;
}

static float (*sasum_)(int *n, float *x, int *incx);

jfloat Java_dev_ludovic_netlib_blas_JNIBLAS_sasumK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray x, jint offsetx, jint incx) {
  jfloat ret;
  jboolean failed = FALSE;
  float *nx = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  ret = sasum_(&n, nx + offsetx, &incx);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (failed) throwOOM(env);
  return ret;
fail:
  failed = TRUE;
  goto done;
}

static void (*daxpy_)(int *n, double *alpha, double *x, int *incx, double *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_daxpyK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy) {
  jboolean failed = FALSE;
  double *nx = NULL, *ny = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  daxpy_(&n, &alpha, nx + offsetx, &incx, ny + offsety, &incy);
done:
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, failed ? JNI_ABORT : 0);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*saxpy_)(int *n, float *alpha, float *x, int *incx, float *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_saxpyK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy) {
  jboolean failed = FALSE;
  float *nx = NULL, *ny = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  saxpy_(&n, &alpha, nx + offsetx, &incx, ny + offsety, &incy);
done:
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, failed ? JNI_ABORT : 0);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dcopy_)(int *n, double *x, int *incx, double *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dcopyK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy) {
  jboolean failed = FALSE;
  double *nx = NULL, *ny = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  dcopy_(&n, nx + offsetx, &incx, ny + offsety, &incy);
done:
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, failed ? JNI_ABORT : 0);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*scopy_)(int *n, float *x, int *incx, float *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_scopyK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy) {
  jboolean failed = FALSE;
  float *nx = NULL, *ny = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  scopy_(&n, nx + offsetx, &incx, ny + offsety, &incy);
done:
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, failed ? JNI_ABORT : 0);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static double (*ddot_)(int *n, double *x, int *incx, double *y, int *incy);

jdouble Java_dev_ludovic_netlib_blas_JNIBLAS_ddotK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy) {
  jboolean failed = FALSE;
  jdouble ret;
  double *nx = NULL, *ny = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  ret = ddot_(&n, nx + offsetx, &incx, ny + offsety, &incy);
done:
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, JNI_ABORT);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (failed) throwOOM(env);
  return ret;
fail:
  failed = TRUE;
  goto done;
}

static float (*sdot_)(int *n, float *x, int *incx, float *y, int *incy);

jfloat Java_dev_ludovic_netlib_blas_JNIBLAS_sdotK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy) {
  jfloat ret;
  jboolean failed = FALSE;
  float *nx = NULL, *ny = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  ret = sdot_(&n, nx + offsetx, &incx, ny + offsety, &incy);
done:
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, JNI_ABORT);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (failed) throwOOM(env);
  return ret;
fail:
  failed = TRUE;
  goto done;
}

static float (*sdsdot_)(int *n, float *sb, float *sx, int *incsx, float *sy, int *incsy);

jfloat Java_dev_ludovic_netlib_blas_JNIBLAS_sdsdotK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloat sb, jfloatArray sx, jint offsetsx, jint incsx, jfloatArray sy, jint offsetsy, jint incsy) {
  jfloat ret;
  jboolean failed = FALSE;
  float *nsx = NULL, *nsy = NULL;
  if (!(nsx = (*env)->GetPrimitiveArrayCritical(env, sx, NULL))) goto fail;
  if (!(nsy = (*env)->GetPrimitiveArrayCritical(env, sy, NULL))) goto fail;
  ret = sdsdot_(&n, &sb, nsx + offsetsx, &incsx, nsy + offsetsy, &incsy);
done:
  if (nsy) (*env)->ReleasePrimitiveArrayCritical(env, sy, nsy, JNI_ABORT);
  if (nsx) (*env)->ReleasePrimitiveArrayCritical(env, sx, nsx, JNI_ABORT);
  if (failed) throwOOM(env);
  return ret;
fail:
  failed = TRUE;
  goto done;
}

static void (*dgbmv_)(const char *trans, int *m, int *n, int *kl, int *ku, double *alpha, double *a, int *lda, double *x, int *incx, double *beta, double *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dgbmvK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint m, jint n, jint kl, jint ku, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray x, jint offsetx, jint incx, jdouble beta, jdoubleArray y, jint offsety, jint incy) {
  jboolean failed = FALSE;
  const char *ntrans = NULL; double *na = NULL, *nx = NULL, *ny = NULL;
  if (!(ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  dgbmv_(ntrans, &m, &n, &kl, &ku, &alpha, na + offseta, &lda, nx + offsetx, &incx, &beta, ny + offsety, &incy);
done:
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, failed ? JNI_ABORT : 0);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (ntrans) (*env)->ReleaseStringUTFChars(env, trans, ntrans);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*sgbmv_)(const char *trans, int *m, int *n, int *kl, int *ku, float *alpha, float *a, int *lda, float *x, int *incx, float *beta, float *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sgbmvK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint m, jint n, jint kl, jint ku, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray x, jint offsetx, jint incx, jfloat beta, jfloatArray y, jint offsety, jint incy) {
  jboolean failed = FALSE;
  const char *ntrans = NULL; float *na = NULL, *nx = NULL, *ny = NULL;
  if (!(ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  sgbmv_(ntrans, &m, &n, &kl, &ku, &alpha, na + offseta, &lda, nx + offsetx, &incx, &beta, ny + offsety, &incy);
done:
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, failed ? JNI_ABORT : 0);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (ntrans) (*env)->ReleaseStringUTFChars(env, trans, ntrans);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dgemm_)(const char *transa, const char *transb, int *m, int *n, int *k, double *alpha, double *a, int *lda, double *b, int *ldb, double *beta, double *c, int *ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dgemmK(JNIEnv *env, UNUSED jobject obj,
    jstring transa, jstring transb, jint m, jint n, jint k, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdouble beta, jdoubleArray c, jint offsetc, jint ldc) {
  jboolean failed = FALSE;
  const char *ntransa = NULL, *ntransb = NULL; double *na = NULL, *nb = NULL, *nc = NULL;
  if (!(ntransa = (*env)->GetStringUTFChars(env, transa, NULL))) goto fail;
  if (!(ntransb = (*env)->GetStringUTFChars(env, transb, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  if (!(nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) goto fail;
  if (!(nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) goto fail;
  dgemm_(ntransa, ntransb, &m, &n, &k, &alpha, na + offseta, &lda, nb + offsetb, &ldb, &beta, nc + offsetc, &ldc);
done:
  if (nc) (*env)->ReleasePrimitiveArrayCritical(env, c, nc, failed ? JNI_ABORT : 0);
  if (nb) (*env)->ReleasePrimitiveArrayCritical(env, b, nb, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (ntransb) (*env)->ReleaseStringUTFChars(env, transb, ntransb);
  if (ntransa) (*env)->ReleaseStringUTFChars(env, transa, ntransa);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*sgemm_)(const char *transa, const char *transb, int *m, int *n, int *k, float *alpha, float *a, int *lda, float *b, int *ldb, float *beta, float *c, int *ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sgemmK(JNIEnv *env, UNUSED jobject obj,
    jstring transa, jstring transb, jint m, jint n, jint k, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloat beta, jfloatArray c, jint offsetc, jint ldc) {
  jboolean failed = FALSE;
  const char *ntransa = NULL, *ntransb = NULL; float *na = NULL, *nb = NULL, *nc = NULL;
  if (!(ntransa = (*env)->GetStringUTFChars(env, transa, NULL))) goto fail;
  if (!(ntransb = (*env)->GetStringUTFChars(env, transb, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  if (!(nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) goto fail;
  if (!(nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) goto fail;
  sgemm_(ntransa, ntransb, &m, &n, &k, &alpha, na + offseta, &lda, nb + offsetb, &ldb, &beta, nc + offsetc, &ldc);
done:
  if (nc) (*env)->ReleasePrimitiveArrayCritical(env, c, nc, failed ? JNI_ABORT : 0);
  if (nb) (*env)->ReleasePrimitiveArrayCritical(env, b, nb, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (ntransb) (*env)->ReleaseStringUTFChars(env, transb, ntransb);
  if (ntransa) (*env)->ReleaseStringUTFChars(env, transa, ntransa);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dgemv_)(const char *trans, int *m, int *n, double *alpha, double *a, int *lda, double *x, int *incx, double *beta, double *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dgemvK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint m, jint n, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray x, jint offsetx, jint incx, jdouble beta, jdoubleArray y, jint offsety, jint incy) {
  jboolean failed = FALSE;
  const char *ntrans = NULL; jdouble *na = NULL, *nx = NULL, *ny = NULL;
  if (!(ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  dgemv_(ntrans, &m, &n, &alpha, na + offseta, &lda, nx + offsetx, &incx, &beta, ny + offsety, &incy);
done:
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, failed ? JNI_ABORT : 0);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (ntrans) (*env)->ReleaseStringUTFChars(env, trans, ntrans);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*sgemv_)(const char *trans, int *m, int *n, float *alpha, float *a, int *lda, float *x, int *incx, float *beta, float *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sgemvK(JNIEnv *env, UNUSED jobject obj,
    jstring trans, jint m, jint n, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray x, jint offsetx, jint incx, jfloat beta, jfloatArray y, jint offsety, jint incy) {
  jboolean failed = FALSE;
  const char *ntrans = NULL; float *na = NULL, *nx = NULL, *ny = NULL;
  if (!(ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  sgemv_(ntrans, &m, &n, &alpha, na + offseta, &lda, nx + offsetx, &incx, &beta, ny + offsety, &incy);
done:
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, failed ? JNI_ABORT : 0);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (ntrans) (*env)->ReleaseStringUTFChars(env, trans, ntrans);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dger_)(int *m, int *n, double *alpha, double *x, int *incx, double *y, int *incy, double *a, int *lda);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dgerK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy, jdoubleArray a, jint offseta, jint lda) {
  jboolean failed = FALSE;
  double *na = NULL, *nx = NULL, *ny = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  dger_(&m, &n, &alpha, nx + offsetx, &incx, ny + offsety, &incy, na + offseta, &lda);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, failed ? JNI_ABORT : 0);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, JNI_ABORT);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*sger_)(int *m, int *n, float *alpha, float *x, int *incx, float *y, int *incy, float *a, int *lda);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sgerK(JNIEnv *env, UNUSED jobject obj,
    jint m, jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy, jfloatArray a, jint offseta, jint lda) {
  jboolean failed = FALSE;
  float *na = NULL, *nx = NULL, *ny = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  sger_(&m, &n, &alpha, nx + offsetx, &incx, ny + offsety, &incy, na + offseta, &lda);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, failed ? JNI_ABORT : 0);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, JNI_ABORT);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static double (*dnrm2_)(int *n, double *x, int *incx);

jdouble Java_dev_ludovic_netlib_blas_JNIBLAS_dnrm2K(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray x, jint offsetx, jint incx) {
  jdouble ret;
  jboolean failed = FALSE;
  double *nx = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  ret = dnrm2_(&n, nx + offsetx, &incx);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (failed) throwOOM(env);
  return ret;
fail:
  failed = TRUE;
  goto done;
}

static float (*snrm2_)(int *n, float *x, int *incx);

jfloat Java_dev_ludovic_netlib_blas_JNIBLAS_snrm2K(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray x, jint offsetx, jint incx) {
  jfloat ret;
  jboolean failed = FALSE;
  float *nx = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  ret = snrm2_(&n, nx + offsetx, &incx);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (failed) throwOOM(env);
  return ret;
fail:
  failed = TRUE;
  goto done;
}

static void (*drot_)(int *n, double *dx, int *incx, double *dy, int *incy, double *c, double *s);

void Java_dev_ludovic_netlib_blas_JNIBLAS_drotK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray dx, jint offsetdx, jint incx, jdoubleArray dy, jint offsetdy, jint incy, jdouble c, jdouble s) {
  jboolean failed = FALSE;
  double *ndx = NULL, *ndy = NULL;
  if (!(ndx = (*env)->GetPrimitiveArrayCritical(env, dx, NULL))) goto fail;
  if (!(ndy = (*env)->GetPrimitiveArrayCritical(env, dy, NULL))) goto fail;
  drot_(&n, ndx + offsetdx, &incx, ndy + offsetdy, &incy, &c, &s);
done:
  if (ndy) (*env)->ReleasePrimitiveArrayCritical(env, dy, ndy, failed ? JNI_ABORT : 0);
  if (ndx) (*env)->ReleasePrimitiveArrayCritical(env, dx, ndx, failed ? JNI_ABORT : 0);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*srot_)(int *n, float *sx, int *incx, float *sy, int *incy, float *c, float *s);

void Java_dev_ludovic_netlib_blas_JNIBLAS_srotK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray sx, jint offsetsx, jint incx, jfloatArray sy, jint offsetsy, jint incy, jfloat c, jfloat s) {
  jboolean failed = FALSE;
  float *nsx = NULL, *nsy = NULL;
  if (!(nsx = (*env)->GetPrimitiveArrayCritical(env, sx, NULL))) goto fail;
  if (!(nsy = (*env)->GetPrimitiveArrayCritical(env, sy, NULL))) goto fail;
  srot_(&n, nsx + offsetsx, &incx, nsy + offsetsy, &incy, &c, &s);
done:
  if (nsy) (*env)->ReleasePrimitiveArrayCritical(env, sy, nsy, failed ? JNI_ABORT : 0);
  if (nsx) (*env)->ReleasePrimitiveArrayCritical(env, sx, nsx, failed ? JNI_ABORT : 0);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*drotm_)(int *n, double *dx, int *incx, double *dy, int *incy, double *dparam);

void Java_dev_ludovic_netlib_blas_JNIBLAS_drotmK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray dx, jint offsetdx, jint incx, jdoubleArray dy, jint offsetdy, jint incy, jdoubleArray dparam, jint offsetdparam) {
  jboolean failed = FALSE;
  double *ndx = NULL, *ndy = NULL, *ndparam = NULL;
  if (!(ndx = (*env)->GetPrimitiveArrayCritical(env, dx, NULL))) goto fail;
  if (!(ndy = (*env)->GetPrimitiveArrayCritical(env, dy, NULL))) goto fail;
  if (!(ndparam = (*env)->GetPrimitiveArrayCritical(env, dparam, NULL))) goto fail;
  drotm_(&n, ndx + offsetdx, &incx, ndy + offsetdy, &incy, ndparam + offsetdparam);
done:
  if (ndparam) (*env)->ReleasePrimitiveArrayCritical(env, dparam, ndparam, JNI_ABORT);
  if (ndy) (*env)->ReleasePrimitiveArrayCritical(env, dy, ndy, failed ? JNI_ABORT : 0);
  if (ndx) (*env)->ReleasePrimitiveArrayCritical(env, dx, ndx, JNI_ABORT);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*srotm_)(int *n, float *sx, int *incx, float *sy, int *incy, float *sparam);

void Java_dev_ludovic_netlib_blas_JNIBLAS_srotmK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray sx, jint offsetsx, jint incx, jfloatArray sy, jint offsetsy, jint incy, jfloatArray sparam, jint offsetsparam) {
  jboolean failed = FALSE;
  float *nsx = NULL, *nsy = NULL, *nsparam = NULL;
  if (!(nsx = (*env)->GetPrimitiveArrayCritical(env, sx, NULL))) goto fail;
  if (!(nsy = (*env)->GetPrimitiveArrayCritical(env, sy, NULL))) goto fail;
  if (!(nsparam = (*env)->GetPrimitiveArrayCritical(env, sparam, NULL))) goto fail;
  srotm_(&n, nsx + offsetsx, &incx, nsy + offsetsy, &incy, nsparam + offsetsparam);
done:
  if (nsparam) (*env)->ReleasePrimitiveArrayCritical(env, sparam, nsparam, JNI_ABORT);
  if (nsy) (*env)->ReleasePrimitiveArrayCritical(env, sy, nsy, failed ? JNI_ABORT : 0);
  if (nsx) (*env)->ReleasePrimitiveArrayCritical(env, sx, nsx, JNI_ABORT);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*drotmg_)(double *dd1, double *dd2, double *dx1, double *dy1, double *dparam);

void Java_dev_ludovic_netlib_blas_JNIBLAS_drotmgK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jobject dd1, UNUSED jobject dd2, UNUSED jobject dx1, UNUSED jdouble dy1, UNUSED jdoubleArray dparam, UNUSED jint offsetdparam) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*srotmg_)(float *sd1, float *sd2, float *sx1, float *sy1, float *sparam);

void Java_dev_ludovic_netlib_blas_JNIBLAS_srotmgK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jobject sd1, UNUSED jobject sd2, UNUSED jobject sx1, UNUSED jfloat sy1, UNUSED jfloatArray sparam, UNUSED jint offsetsparam) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dsbmv_)(const char *uplo, int *n, int *k, double *alpha, double *a, int *lda, double *x, int *incx, double *beta, double *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsbmvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jint n, UNUSED jint k, UNUSED jdouble alpha, UNUSED jdoubleArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jdoubleArray x, UNUSED jint offsetx, UNUSED jint incx, UNUSED jdouble beta, UNUSED jdoubleArray y, UNUSED jint offsety, UNUSED jint incy) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*ssbmv_)(const char *uplo, int *n, int *k, float *alpha, float *a, int *lda, float *x, int *incx, float *beta, float *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssbmvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jint n, UNUSED jint k, UNUSED jfloat alpha, UNUSED jfloatArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jfloatArray x, UNUSED jint offsetx, UNUSED jint incx, UNUSED jfloat beta, UNUSED jfloatArray y, UNUSED jint offsety, UNUSED jint incy) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dscal_)(int *n, double *alpha, double *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dscalK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx) {
  jboolean failed = FALSE;
  double *nx = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  dscal_(&n, &alpha, nx + offsetx, &incx);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, failed ? JNI_ABORT : 0);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*sscal_)(int *n, float *alpha, float *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sscalK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx) {
  jboolean failed = FALSE;
  float *nx = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  sscal_(&n, &alpha, nx + offsetx, &incx);
done:
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, failed ? JNI_ABORT : 0);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dspmv_)(const char *uplo, int *n, double *alpha, double *a, double *x, int *incx, double *beta, double *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dspmvK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdouble alpha, jdoubleArray a, jint offseta, jdoubleArray x, jint offsetx, jint incx, jdouble beta, jdoubleArray y, jint offsety, jint incy) {
  jboolean failed = FALSE;
  const char *nuplo = NULL; double *na = NULL, *nx = NULL, *ny = NULL;
  if (!(nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  dspmv_(nuplo, &n, &alpha, na + offseta, nx + offsetx, &incx, &beta, ny + offsety, &incy);
done:
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, failed ? JNI_ABORT : 0);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nuplo) (*env)->ReleaseStringUTFChars(env, uplo, nuplo);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*sspmv_)(const char *uplo, int *n, float *alpha, float *a, float *x, int *incx, float *beta, float *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sspmvK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloat alpha, jfloatArray a, jint offseta, jfloatArray x, jint offsetx, jint incx, jfloat beta, jfloatArray y, jint offsety, jint incy) {
  jboolean failed = FALSE;
  const char *nuplo = NULL; float *na = NULL, *nx = NULL, *ny = NULL;
  if (!(nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  sspmv_(nuplo, &n, &alpha, na + offseta, nx + offsetx, &incx, &beta, ny + offsety, &incy);
done:
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, failed ? JNI_ABORT : 0);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nuplo) (*env)->ReleaseStringUTFChars(env, uplo, nuplo);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dspr_)(const char *uplo, int *n, double *alpha, double *x, int *incx, double *a);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsprK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx, jdoubleArray a, jint offseta) {
  jboolean failed = FALSE;
  const char *nuplo = NULL; double *na = NULL, *nx = NULL;
  if (!(nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) goto fail;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  dspr_(nuplo, &n, &alpha, nx + offsetx, &incx, na + offseta);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, failed ? JNI_ABORT : 0);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (nuplo) (*env)->ReleaseStringUTFChars(env, uplo, nuplo);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*sspr_)(const char *uplo, int *n, float *alpha, float *x, int *incx, float *a);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssprK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx, jfloatArray a, jint offseta) {
  jboolean failed = FALSE;
  const char *nuplo = NULL; float *na = NULL, *nx = NULL;
  if (!(nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) goto fail;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  sspr_(nuplo, &n, &alpha, nx + offsetx, &incx, na + offseta);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, failed ? JNI_ABORT : 0);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (nuplo) (*env)->ReleaseStringUTFChars(env, uplo, nuplo);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dspr2_)(const char *uplo, int *n, double *alpha, double *x, int *incx, double *y, int *incy, double *a);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dspr2K(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy, jdoubleArray a, jint offseta) {
  jboolean failed = FALSE;
  const char *nuplo = NULL; double *na = NULL, *nx = NULL, *ny = NULL;
  if (!(nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) goto fail;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  dspr2_(nuplo, &n, &alpha, nx + offsetx, &incx, ny + offsety, &incy, na + offseta);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, failed ? JNI_ABORT : 0);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, JNI_ABORT);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (nuplo) (*env)->ReleaseStringUTFChars(env, uplo, nuplo);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*sspr2_)(const char *uplo, int *n, float *alpha, float *x, int *incx, float *y, int *incy, float *a);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sspr2K(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy, jfloatArray a, jint offseta) {
  jboolean failed = FALSE;
  const char *nuplo = NULL; float *na = NULL, *nx = NULL, *ny = NULL;
  if (!(nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) goto fail;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  sspr2_(nuplo, &n, &alpha, nx + offsetx, &incx, ny + offsety, &incy, na + offseta);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, failed ? JNI_ABORT : 0);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, JNI_ABORT);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (nuplo) (*env)->ReleaseStringUTFChars(env, uplo, nuplo);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dswap_)(int *n, double *x, int *incx, double *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dswapK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy) {
  jboolean failed = FALSE;
  double *nx = NULL, *ny = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  dswap_(&n, nx + offsetx, &incx, ny + offsety, &incy);
done:
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, failed ? JNI_ABORT : 0);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, failed ? JNI_ABORT : 0);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*sswap_)(int *n, float *x, int *incx, float *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_sswapK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy) {
  jboolean failed = FALSE;
  float *nx = NULL, *ny = NULL;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  sswap_(&n, nx + offsetx, &incx, ny + offsety, &incy);
done:
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, failed ? JNI_ABORT : 0);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, failed ? JNI_ABORT : 0);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dsymm_)(const char *side, const char *uplo, int *m, int *n, double *alpha, double *a, int *lda, double *b, int *ldb, double *beta, double *c, int *ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsymmK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring uplo, jint m, jint n, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdouble beta, jdoubleArray c, jint offsetc, jint ldc) {
  jboolean failed = FALSE;
  const char *nside = NULL, *nuplo = NULL; double *na = NULL, *nb = NULL, *nc = NULL;
  if (!(nside = (*env)->GetStringUTFChars(env, side, NULL))) goto fail;
  if (!(nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  if (!(nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) goto fail;
  if (!(nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) goto fail;
  dsymm_(nside, nuplo, &m, &n, &alpha, na + offseta, &lda, nb + offsetb, &ldb, &beta, nc + offsetc, &ldc);
done:
  if (nc) (*env)->ReleasePrimitiveArrayCritical(env, c, nc, failed ? JNI_ABORT : 0);
  if (nb) (*env)->ReleasePrimitiveArrayCritical(env, b, nb, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nuplo) (*env)->ReleaseStringUTFChars(env, uplo, nuplo);
  if (nside) (*env)->ReleaseStringUTFChars(env, side, nside);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*ssymm_)(const char *side, const char *uplo, int *m, int *n, float *alpha, float *a, int *lda, float *b, int *ldb, float *beta, float *c, int *ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssymmK(JNIEnv *env, UNUSED jobject obj,
    jstring side, jstring uplo, jint m, jint n, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloat beta, jfloatArray c, jint offsetc, jint ldc) {
  jboolean failed = FALSE;
  const char *nside = NULL, *nuplo = NULL; float *na = NULL, *nb = NULL, *nc = NULL;
  if (!(nside = (*env)->GetStringUTFChars(env, side, NULL))) goto fail;
  if (!(nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  if (!(nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) goto fail;
  if (!(nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) goto fail;
  ssymm_(nside, nuplo, &m, &n, &alpha, na + offseta, &lda, nb + offsetb, &ldb, &beta, nc + offsetc, &ldc);
done:
  if (nc) (*env)->ReleasePrimitiveArrayCritical(env, c, nc, failed ? JNI_ABORT : 0);
  if (nb) (*env)->ReleasePrimitiveArrayCritical(env, b, nb, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nuplo) (*env)->ReleaseStringUTFChars(env, uplo, nuplo);
  if (nside) (*env)->ReleaseStringUTFChars(env, side, nside);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dsymv_)(const char *uplo, int *n, double *alpha, double *a, int *lda, double *x, int *incx, double *beta, double *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsymvK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray x, jint offsetx, jint incx, jdouble beta, jdoubleArray y, jint offsety, jint incy) {
  jboolean failed = FALSE;
  const char *nuplo = NULL; double *na = NULL, *nx = NULL, *ny = NULL;
  if (!(nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  dsymv_(nuplo, &n, &alpha, na + offseta, &lda, nx + offsetx, &incx, &beta, ny + offsety, &incy);
done:
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, failed ? JNI_ABORT : 0);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nuplo) (*env)->ReleaseStringUTFChars(env, uplo, nuplo);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*ssymv_)(const char *uplo, int *n, float *alpha, float *a, int *lda, float *x, int *incx, float *beta, float *y, int *incy);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssymvK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray x, jint offsetx, jint incx, jfloat beta, jfloatArray y, jint offsety, jint incy) {
  jboolean failed = FALSE;
  const char *nuplo = NULL; float *na = NULL, *nx = NULL, *ny = NULL;
  if (!(nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  ssymv_(nuplo, &n, &alpha, na + offseta, &lda, nx + offsetx, &incx, &beta, ny + offsety, &incy);
done:
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, failed ? JNI_ABORT : 0);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nuplo) (*env)->ReleaseStringUTFChars(env, uplo, nuplo);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dsyr_)(const char *uplo, int *n, double *alpha, double *x, int *incx, double *a, int *lda);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsyrK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx, jdoubleArray a, jint offseta, jint lda) {
  jboolean failed = FALSE;
  const char *nuplo = NULL; double *na = NULL, *nx = NULL;
  if (!(nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) goto fail;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  dsyr_(nuplo, &n, &alpha, nx + offsetx, &incx, na + offseta, &lda);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, failed ? JNI_ABORT : 0);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (nuplo) (*env)->ReleaseStringUTFChars(env, uplo, nuplo);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*ssyr_)(const char *uplo, int *n, float *alpha, float *x, int *incx, float *a, int *lda);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssyrK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx, jfloatArray a, jint offseta, jint lda) {
  jboolean failed = FALSE;
  const char *nuplo = NULL; float *na = NULL, *nx = NULL;
  if (!(nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) goto fail;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  ssyr_(nuplo, &n, &alpha, nx + offsetx, &incx, na + offseta, &lda);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, failed ? JNI_ABORT : 0);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (nuplo) (*env)->ReleaseStringUTFChars(env, uplo, nuplo);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dsyr2_)(const char *uplo, int *n, double *alpha, double *x, int *incx, double *y, int *incy, double *a, int *lda);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsyr2K(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jdouble alpha, jdoubleArray x, jint offsetx, jint incx, jdoubleArray y, jint offsety, jint incy, jdoubleArray a, jint offseta, jint lda) {
  jboolean failed = FALSE;
  const char *nuplo = NULL; double *na = NULL, *nx = NULL, *ny = NULL;
  if (!(nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) goto fail;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  dsyr2_(nuplo, &n, &alpha, nx + offsetx, &incx, ny + offsety, &incy, na + offseta, &lda);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, failed ? JNI_ABORT : 0);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, JNI_ABORT);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (nuplo) (*env)->ReleaseStringUTFChars(env, uplo, nuplo);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*ssyr2_)(const char *uplo, int *n, float *alpha, float *x, int *incx, float *y, int *incy, float *a, int *lda);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssyr2K(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jint n, jfloat alpha, jfloatArray x, jint offsetx, jint incx, jfloatArray y, jint offsety, jint incy, jfloatArray a, jint offseta, jint lda) {
  jboolean failed = FALSE;
  const char *nuplo = NULL; float *na = NULL, *nx = NULL, *ny = NULL;
  if (!(nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) goto fail;
  if (!(nx = (*env)->GetPrimitiveArrayCritical(env, x, NULL))) goto fail;
  if (!(ny = (*env)->GetPrimitiveArrayCritical(env, y, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  ssyr2_(nuplo, &n, &alpha, nx + offsetx, &incx, ny + offsety, &incy, na + offseta, &lda);
done:
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, failed ? JNI_ABORT : 0);
  if (ny) (*env)->ReleasePrimitiveArrayCritical(env, y, ny, JNI_ABORT);
  if (nx) (*env)->ReleasePrimitiveArrayCritical(env, x, nx, JNI_ABORT);
  if (nuplo) (*env)->ReleaseStringUTFChars(env, uplo, nuplo);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dsyr2k_)(const char *uplo, const char *trans, int *n, int *k, double *alpha, double *a, int *lda, double *b, int *ldb, double *beta, double *c, int *ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsyr2kK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jint n, jint k, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdoubleArray b, jint offsetb, jint ldb, jdouble beta, jdoubleArray c, jint offsetc, jint ldc) {
  jboolean failed = FALSE;
  const char *nuplo = NULL, *ntrans = NULL; double *na = NULL, *nb = NULL, *nc = NULL;
  if (!(nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) goto fail;
  if (!(ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  if (!(nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) goto fail;
  if (!(nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) goto fail;
  dsyr2k_(nuplo, ntrans, &n, &k, &alpha, na + offseta, &lda, nb + offsetb, &ldb, &beta, nc + offsetc, &ldc);
done:
  if (nc) (*env)->ReleasePrimitiveArrayCritical(env, c, nc, failed ? JNI_ABORT : 0);
  if (nb) (*env)->ReleasePrimitiveArrayCritical(env, b, nb, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (nuplo) (*env)->ReleaseStringUTFChars(env, uplo, nuplo);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*ssyr2k_)(const char *uplo, const char *trans, int *n, int *k, float *alpha, float *a, int *lda, float *b, int *ldb, float *beta, float *c, int *ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssyr2kK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jint n, jint k, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloatArray b, jint offsetb, jint ldb, jfloat beta, jfloatArray c, jint offsetc, jint ldc) {
  jboolean failed = FALSE;
  const char *nuplo = NULL, *ntrans = NULL; float *na = NULL, *nb = NULL, *nc = NULL;
  if (!(nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) goto fail;
  if (!(ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  if (!(nb = (*env)->GetPrimitiveArrayCritical(env, b, NULL))) goto fail;
  if (!(nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) goto fail;
  ssyr2k_(nuplo, ntrans, &n, &k, &alpha, na + offseta, &lda, nb + offsetb, &ldb, &beta, nc + offsetc, &ldc);
done:
  if (nc) (*env)->ReleasePrimitiveArrayCritical(env, c, nc, failed ? JNI_ABORT : 0);
  if (nb) (*env)->ReleasePrimitiveArrayCritical(env, b, nb, JNI_ABORT);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (ntrans) (*env)->ReleaseStringUTFChars(env, trans, ntrans);
  if (nuplo) (*env)->ReleaseStringUTFChars(env, uplo, nuplo);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dsyrk_)(const char *uplo, const char *trans, int *n, int *k, double *alpha, double *a, int *lda, double *beta, double *c, int *ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dsyrkK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jint n, jint k, jdouble alpha, jdoubleArray a, jint offseta, jint lda, jdouble beta, jdoubleArray c, jint offsetc, jint ldc) {
  jboolean failed = FALSE;
  const char *nuplo = NULL, *ntrans = NULL; double *na = NULL, *nc = NULL;
  if (!(nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) goto fail;
  if (!(ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  if (!(nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) goto fail;
  dsyrk_(nuplo, ntrans, &n, &k, &alpha, na + offseta, &lda, &beta, nc + offsetc, &ldc);
done:
  if (nc) (*env)->ReleasePrimitiveArrayCritical(env, c, nc, failed ? JNI_ABORT : 0);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (ntrans) (*env)->ReleaseStringUTFChars(env, trans, ntrans);
  if (nuplo) (*env)->ReleaseStringUTFChars(env, uplo, nuplo);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*ssyrk_)(const char *uplo, const char *trans, int *n, int *k, float *alpha, float *a, int *lda, float *beta, float *c, int *ldc);

void Java_dev_ludovic_netlib_blas_JNIBLAS_ssyrkK(JNIEnv *env, UNUSED jobject obj,
    jstring uplo, jstring trans, jint n, jint k, jfloat alpha, jfloatArray a, jint offseta, jint lda, jfloat beta, jfloatArray c, jint offsetc, jint ldc) {
  jboolean failed = FALSE;
  const char *nuplo = NULL, *ntrans = NULL; float *na = NULL, *nc = NULL;
  if (!(nuplo = (*env)->GetStringUTFChars(env, uplo, NULL))) goto fail;
  if (!(ntrans = (*env)->GetStringUTFChars(env, trans, NULL))) goto fail;
  if (!(na = (*env)->GetPrimitiveArrayCritical(env, a, NULL))) goto fail;
  if (!(nc = (*env)->GetPrimitiveArrayCritical(env, c, NULL))) goto fail;
  ssyrk_(nuplo, ntrans, &n, &k, &alpha, na + offseta, &lda, &beta, nc + offsetc, &ldc);
done:
  if (nc) (*env)->ReleasePrimitiveArrayCritical(env, c, nc, failed ? JNI_ABORT : 0);
  if (na) (*env)->ReleasePrimitiveArrayCritical(env, a, na, JNI_ABORT);
  if (ntrans) (*env)->ReleaseStringUTFChars(env, trans, ntrans);
  if (nuplo) (*env)->ReleaseStringUTFChars(env, uplo, nuplo);
  if (failed) throwOOM(env);
  return;
fail:
  failed = TRUE;
  goto done;
}

static void (*dtbmv_)(const char *uplo, const char *trans, const char *diag, int *n, int *k, double *a, int *lda, double *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtbmvK(UNUSED JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jint k, UNUSED jdoubleArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jdoubleArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stbmv_)(const char *uplo, const char *trans, const char *diag, int *n, int *k, float *a, int *lda, float *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_stbmvK(UNUSED JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jint k, UNUSED jfloatArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jfloatArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtbsv_)(const char *uplo, const char *trans, const char *diag, int *n, int *k, double *a, int *lda, double *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtbsvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jint k, UNUSED jdoubleArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jdoubleArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stbsv_)(const char *uplo, const char *trans, const char *diag, int *n, int *k, float *a, int *lda, float *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_stbsvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jint k, UNUSED jfloatArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jfloatArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtpmv_)(const char *uplo, const char *trans, const char *diag, int *n, double *a, double *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtpmvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jdoubleArray a, UNUSED jint offseta, UNUSED jdoubleArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stpmv_)(const char *uplo, const char *trans, const char *diag, int *n, float *a, float *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_stpmvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jfloatArray a, UNUSED jint offseta, UNUSED jfloatArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtpsv_)(const char *uplo, const char *trans, const char *diag, int *n, double *a, double *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtpsvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jdoubleArray a, UNUSED jint offseta, UNUSED jdoubleArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*stpsv_)(const char *uplo, const char *trans, const char *diag, int *n, float *a, float *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_stpsvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jfloatArray a, UNUSED jint offseta, UNUSED jfloatArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtrmm_)(const char *side, const char *uplo, const char *transa, const char *diag, int *m, int *n, double *alpha, double *a, int *lda, double *b, int *ldb);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtrmmK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring side, UNUSED jstring uplo, UNUSED jstring transa, UNUSED jstring diag, UNUSED jint m, UNUSED jint n, UNUSED jdouble alpha, UNUSED jdoubleArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jdoubleArray b, UNUSED jint offsetb, UNUSED jint ldb) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*strmm_)(const char *side, const char *uplo, const char *transa, const char *diag, int *m, int *n, float *alpha, float *a, int *lda, float *b, int *ldb);

void Java_dev_ludovic_netlib_blas_JNIBLAS_strmmK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring side, UNUSED jstring uplo, UNUSED jstring transa, UNUSED jstring diag, UNUSED jint m, UNUSED jint n, UNUSED jfloat alpha, UNUSED jfloatArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jfloatArray b, UNUSED jint offsetb, UNUSED jint ldb) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtrmv_)(const char *uplo, const char *trans, const char *diag, int *n, double *a, int *lda, double *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtrmvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jdoubleArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jdoubleArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*strmv_)(const char *uplo, const char *trans, const char *diag, int *n, float *a, int *lda, float *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_strmvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jfloatArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jfloatArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtrsm_)(const char *side, const char *uplo, const char *transa, const char *diag, int *m, int *n, double *alpha, double *a, int *lda, double *b, int *ldb);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtrsmK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring side, UNUSED jstring uplo, UNUSED jstring transa, UNUSED jstring diag, UNUSED jint m, UNUSED jint n, UNUSED jdouble alpha, UNUSED jdoubleArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jdoubleArray b, UNUSED jint offsetb, UNUSED jint ldb) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*strsm_)(const char *side, const char *uplo, const char *transa, const char *diag, int *m, int *n, float *alpha, float *a, int *lda, float *b, int *ldb);

void Java_dev_ludovic_netlib_blas_JNIBLAS_strsmK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring side, UNUSED jstring uplo, UNUSED jstring transa, UNUSED jstring diag, UNUSED jint m, UNUSED jint n, UNUSED jfloat alpha, UNUSED jfloatArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jfloatArray b, UNUSED jint offsetb, UNUSED jint ldb) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*dtrsv_)(const char *uplo, const char *trans, const char *diag, int *n, double *a, int *lda, double *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_dtrsvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jdoubleArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jdoubleArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static void (*strsv_)(const char *uplo, const char *trans, const char *diag, int *n, float *a, int *lda, float *x, int *incx);

void Java_dev_ludovic_netlib_blas_JNIBLAS_strsvK(JNIEnv *env, UNUSED jobject obj,
    UNUSED jstring uplo, UNUSED jstring trans, UNUSED jstring diag, UNUSED jint n, UNUSED jfloatArray a, UNUSED jint offseta, UNUSED jint lda, UNUSED jfloatArray x, UNUSED jint offsetx, UNUSED jint incx) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/UnsupportedOperationException"), "not implemented");
}

static int (*idamax_)(int *n, double *dx, int *incdx);

jint Java_dev_ludovic_netlib_blas_JNIBLAS_idamaxK(JNIEnv *env, UNUSED jobject obj,
    jint n, jdoubleArray dx, jint offsetdx, jint incdx) {
  jint ret;
  jboolean failed = FALSE;
  double *ndx = NULL;
  if (!(ndx = (*env)->GetPrimitiveArrayCritical(env, dx, NULL))) goto fail;
  // It returns 1-based index because that's how Fortran works
  ret = idamax_(&n, ndx + offsetdx, &incdx) - 1;
done:
  if (ndx) (*env)->ReleasePrimitiveArrayCritical(env, dx, ndx, JNI_ABORT);
  if (failed) throwOOM(env);
  return ret;
fail:
  failed = TRUE;
  goto done;
}

static int (*isamax_)(int *n, float *sx, int *incsx);

jint Java_dev_ludovic_netlib_blas_JNIBLAS_isamaxK(JNIEnv *env, UNUSED jobject obj,
    jint n, jfloatArray sx, jint offsetsx, jint incsx) {
  jint ret;
  jboolean failed = FALSE;
  float *nsx = NULL;
  if (!(nsx = (*env)->GetPrimitiveArrayCritical(env, sx, NULL))) goto fail;
  // It returns 1-based index because that's how Fortran works
  ret = isamax_(&n, nsx + offsetsx, &incsx) - 1;
done:
  if (nsx) (*env)->ReleasePrimitiveArrayCritical(env, sx, nsx, JNI_ABORT);
  if (failed) throwOOM(env);
  return ret;
fail:
  failed = TRUE;
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
    snprintf(blas_name, sizeof(blas_name), "%s", utf);
    (*env)->ReleaseStringUTFChars(env, native_lib_path, utf);
  } else if (native_lib) {
    const char *utf = (*env)->GetStringUTFChars(env, native_lib, NULL);
    snprintf(blas_name, sizeof(blas_name), "lib%s.so", utf);
    (*env)->ReleaseStringUTFChars(env, native_lib, utf);
  } else {
    /* native_lib should always be non-NULL */
    return -1;
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

  return JNI_VERSION_1_6;
}

void JNI_OnUnload(UNUSED JavaVM *vm, UNUSED void *reserved) {
  dlclose(blas);
}
