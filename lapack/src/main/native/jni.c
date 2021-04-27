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

#include "dev_ludovic_netlib_lapack_JNILAPACK.h"

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

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dbdsdcK(JNIEnv *, jobject,
    jstring, jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jintArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dbdsqrK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ddisnaK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgbbrdK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgbconK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jdouble, jobject, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgbequK(JNIEnv *, jobject,
    jint, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgbrfsK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgbsvK(JNIEnv *, jobject,
    jint, jint, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgbsvxK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgbtf2K(JNIEnv *, jobject,
    jint, jint, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgbtrfK(JNIEnv *, jobject,
    jint, jint, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgbtrsK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgebakK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgebalK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jint, jobject, jobject, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgebd2K(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgebrdK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeconK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jint, jdouble, jobject, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeequK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeesK(JNIEnv *, jobject,
    jstring, jstring, jobject, jint, jdoubleArray, jint, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jbooleanArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeesxK(JNIEnv *, jobject,
    jstring, jstring, jobject, jstring, jint, jdoubleArray, jint, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject, jobject, jdoubleArray, jint, jint, jintArray, jint, jint, jbooleanArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeevK(JNIEnv *, jobject,
    jstring, jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeevxK(JNIEnv *, jobject,
    jstring, jstring, jstring, jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jobject, jdoubleArray, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgegsK(JNIEnv *, jobject,
    jstring, jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgegvK(JNIEnv *, jobject,
    jstring, jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgehd2K(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgehrdK(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgelq2K(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgelqfK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgelsK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgelsdK(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdouble, jobject, jdoubleArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgelssK(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdouble, jobject, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgelsxK(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jdouble, jobject, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgelsyK(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jdouble, jobject, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeql2K(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeqlfK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeqp3K(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeqpfK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeqr2K(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgeqrfK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgerfsK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgerq2K(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgerqfK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgesc2K(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jint, jdoubleArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgesddK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgesvK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgesvdK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgesvxK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgetc2K(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgetf2K(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgetrfK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgetriK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgetrsK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggbakK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggbalK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggesK(JNIEnv *, jobject,
    jstring, jstring, jstring, jobject, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jbooleanArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggesxK(JNIEnv *, jobject,
    jstring, jstring, jstring, jobject, jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jintArray, jint, jint, jbooleanArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggevK(JNIEnv *, jobject,
    jstring, jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggevxK(JNIEnv *, jobject,
    jstring, jstring, jstring, jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jobject, jdoubleArray, jint, jdoubleArray, jint, jobject, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jintArray, jint, jbooleanArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggglmK(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgghrdK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgglseK(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggqrfK(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggrqfK(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggsvdK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jint, jobject, jobject, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dggsvpK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdouble, jdouble, jobject, jobject, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgtconK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jdouble, jobject, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgtrfsK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgtsvK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgtsvxK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgttrfK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgttrsK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dgtts2K(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dhgeqzK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dhseinK(JNIEnv *, jobject,
    jstring, jstring, jstring, jbooleanArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jint, jobject, jdoubleArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dhseqrK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

jboolean Java_dev_ludovic_netlib_lapack_JNILAPACK_disnanK(JNIEnv *, jobject,
    jdouble) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlabadK(JNIEnv *, jobject,
    jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlabrdK(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlacn2K(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject, jobject, jintArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaconK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlacpyK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dladivK(JNIEnv *, jobject,
    jdouble, jdouble, jdouble, jdouble, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlae2K(JNIEnv *, jobject,
    jdouble, jdouble, jdouble, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaebzK(JNIEnv *, jobject,
    jint, jint, jint, jint, jint, jint, jdouble, jdouble, jdouble, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jdoubleArray, jint, jdoubleArray, jint, jobject, jintArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaed0K(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaed1K(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jdoubleArray, jint, jint, jintArray, jint, jobject, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaed2K(JNIEnv *, jobject,
    jobject, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jintArray, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jintArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaed3K(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdouble, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jintArray, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaed4K(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdouble, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaed5K(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdouble, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaed6K(JNIEnv *, jobject,
    jint, jboolean, jdouble, jdoubleArray, jint, jdoubleArray, jint, jdouble, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaed7K(JNIEnv *, jobject,
    jint, jint, jint, jint, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jintArray, jint, jobject, jint, jdoubleArray, jint, jintArray, jint, jintArray, jint, jintArray, jint, jintArray, jint, jintArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaed8K(JNIEnv *, jobject,
    jint, jobject, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jintArray, jint, jobject, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jintArray, jint, jobject, jintArray, jint, jdoubleArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaed9K(JNIEnv *, jobject,
    jint, jint, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdouble, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaedaK(JNIEnv *, jobject,
    jint, jint, jint, jint, jintArray, jint, jintArray, jint, jintArray, jint, jintArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaeinK(JNIEnv *, jobject,
    jboolean, jboolean, jint, jdoubleArray, jint, jint, jdouble, jdouble, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdouble, jdouble, jdouble, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaev2K(JNIEnv *, jobject,
    jdouble, jdouble, jdouble, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaexcK(JNIEnv *, jobject,
    jboolean, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jint, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlag2K(JNIEnv *, jobject,
    jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdouble, jobject, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlag2sK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlags2K(JNIEnv *, jobject,
    jboolean, jdouble, jdouble, jdouble, jdouble, jdouble, jdouble, jobject, jobject, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlagtfK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jdouble, jdoubleArray, jint, jdoubleArray, jint, jdouble, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlagtmK(JNIEnv *, jobject,
    jstring, jint, jint, jdouble, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdouble, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlagtsK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jdoubleArray, jint, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlagv2K(JNIEnv *, jobject,
    jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlahqrK(JNIEnv *, jobject,
    jboolean, jboolean, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlahr2K(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlahrdK(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaic1K(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jdouble, jdoubleArray, jint, jdouble, jobject, jobject, jobject) {
  
}

jboolean Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaisnanK(JNIEnv *, jobject,
    jdouble, jdouble) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaln2K(JNIEnv *, jobject,
    jboolean, jint, jint, jdouble, jdouble, jdoubleArray, jint, jint, jdouble, jdouble, jdoubleArray, jint, jint, jdouble, jdouble, jdoubleArray, jint, jint, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlals0K(JNIEnv *, jobject,
    jint, jint, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jint, jintArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdouble, jdouble, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlalsaK(JNIEnv *, jobject,
    jint, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jintArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jintArray, jint, jint, jintArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlalsdK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdouble, jobject, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlamrgK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jint, jintArray, jint) {
  
}

jint Java_dev_ludovic_netlib_lapack_JNILAPACK_dlanegK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jdoubleArray, jint, jdouble, jdouble, jint) {
  
}

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlangbK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint) {
  
}

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlangeK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint) {
  
}

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlangtK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint) {
  
}

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlanhsK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint) {
  
}

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlansbK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint) {
  
}

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlanspK(JNIEnv *, jobject,
    jstring, jstring, jint, jdoubleArray, jint, jdoubleArray, jint) {
  
}

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlanstK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jdoubleArray, jint) {
  
}

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlansyK(JNIEnv *, jobject,
    jstring, jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint) {
  
}

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlantbK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint) {
  
}

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlantpK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jdoubleArray, jint, jdoubleArray, jint) {
  
}

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlantrK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlanv2K(JNIEnv *, jobject,
    jobject, jobject, jobject, jobject, jobject, jobject, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlapllK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlapmtK(JNIEnv *, jobject,
    jboolean, jint, jint, jdoubleArray, jint, jint, jintArray, jint) {
  
}

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlapy2K(JNIEnv *, jobject,
    jdouble, jdouble) {
  
}

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlapy3K(JNIEnv *, jobject,
    jdouble, jdouble, jdouble) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqgbK(JNIEnv *, jobject,
    jint, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdouble, jdouble, jdouble, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqgeK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdouble, jdouble, jdouble, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqp2K(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqpsK(JNIEnv *, jobject,
    jint, jint, jint, jint, jobject, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqr0K(JNIEnv *, jobject,
    jboolean, jboolean, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqr1K(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jint, jdouble, jdouble, jdouble, jdouble, jdoubleArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqr2K(JNIEnv *, jobject,
    jboolean, jboolean, jint, jint, jint, jint, jdoubleArray, jint, jint, jint, jint, jdoubleArray, jint, jint, jobject, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jint, jdoubleArray, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqr3K(JNIEnv *, jobject,
    jboolean, jboolean, jint, jint, jint, jint, jdoubleArray, jint, jint, jint, jint, jdoubleArray, jint, jint, jobject, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jint, jdoubleArray, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqr4K(JNIEnv *, jobject,
    jboolean, jboolean, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqr5K(JNIEnv *, jobject,
    jboolean, jboolean, jint, jint, jint, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jint, jdoubleArray, jint, jint, jint, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqsbK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdouble, jdouble, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqspK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jdouble, jdouble, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqsyK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdouble, jdouble, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaqtrK(JNIEnv *, jobject,
    jboolean, jboolean, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdouble, jobject, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlar1vK(JNIEnv *, jobject,
    jint, jint, jint, jdouble, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdouble, jdouble, jdoubleArray, jint, jboolean, jobject, jobject, jobject, jobject, jintArray, jint, jobject, jobject, jobject, jdoubleArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlar2vK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarfK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jint, jdouble, jdoubleArray, jint, jint, jdoubleArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarfbK(JNIEnv *, jobject,
    jstring, jstring, jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarfgK(JNIEnv *, jobject,
    jint, jobject, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarftK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarfxK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jdouble, jdoubleArray, jint, jint, jdoubleArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlargvK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarnvK(JNIEnv *, jobject,
    jint, jintArray, jint, jint, jdoubleArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarraK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdouble, jdouble, jobject, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarrbK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jdoubleArray, jint, jint, jint, jdouble, jdouble, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jdouble, jdouble, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarrcK(JNIEnv *, jobject,
    jstring, jint, jdouble, jdouble, jdoubleArray, jint, jdoubleArray, jint, jdouble, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarrdK(JNIEnv *, jobject,
    jstring, jstring, jint, jdouble, jdouble, jint, jint, jdoubleArray, jint, jdouble, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdouble, jint, jintArray, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jobject, jobject, jintArray, jint, jintArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarreK(JNIEnv *, jobject,
    jstring, jint, jobject, jobject, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdouble, jdouble, jdouble, jobject, jintArray, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jintArray, jint, jdoubleArray, jint, jobject, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarrfK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdouble, jdouble, jdouble, jdouble, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarrjK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jdoubleArray, jint, jint, jint, jdouble, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jdouble, jdouble, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarrkK(JNIEnv *, jobject,
    jint, jint, jdouble, jdouble, jdoubleArray, jint, jdoubleArray, jint, jdouble, jdouble, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarrrK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarrvK(JNIEnv *, jobject,
    jint, jdouble, jdouble, jdoubleArray, jint, jdoubleArray, jint, jdouble, jintArray, jint, jint, jint, jint, jdouble, jobject, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jintArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlartgK(JNIEnv *, jobject,
    jdouble, jdouble, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlartvK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaruvK(JNIEnv *, jobject,
    jintArray, jint, jint, jdoubleArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarzK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jdoubleArray, jint, jint, jdouble, jdoubleArray, jint, jint, jdoubleArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarzbK(JNIEnv *, jobject,
    jstring, jstring, jstring, jstring, jint, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlarztK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlas2K(JNIEnv *, jobject,
    jdouble, jdouble, jdouble, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasclK(JNIEnv *, jobject,
    jstring, jint, jint, jdouble, jdouble, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasd0K(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jint, jintArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasd1K(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jobject, jobject, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jintArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasd2K(JNIEnv *, jobject,
    jint, jint, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jdouble, jdouble, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jintArray, jint, jintArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasd3K(JNIEnv *, jobject,
    jint, jint, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jintArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasd4K(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdouble, jobject, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasd5K(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdouble, jobject, jdoubleArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasd6K(JNIEnv *, jobject,
    jint, jint, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jobject, jobject, jintArray, jint, jintArray, jint, jobject, jintArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jobject, jobject, jobject, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasd7K(JNIEnv *, jobject,
    jint, jint, jint, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdouble, jdouble, jdoubleArray, jint, jintArray, jint, jintArray, jint, jintArray, jint, jintArray, jint, jobject, jintArray, jint, jint, jdoubleArray, jint, jint, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasd8K(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasdaK(JNIEnv *, jobject,
    jint, jint, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jintArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jintArray, jint, jint, jintArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasdqK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasdtK(JNIEnv *, jobject,
    jint, jobject, jobject, jintArray, jint, jintArray, jint, jintArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasetK(JNIEnv *, jobject,
    jstring, jint, jint, jdouble, jdouble, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasq1K(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasq2K(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasq3K(JNIEnv *, jobject,
    jint, jobject, jdoubleArray, jint, jint, jobject, jobject, jobject, jobject, jobject, jobject, jobject, jboolean) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasq4K(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jint, jdouble, jdouble, jdouble, jdouble, jdouble, jdouble, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasq5K(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jdouble, jobject, jobject, jobject, jobject, jobject, jobject, jboolean) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasq6K(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jobject, jobject, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasrK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasrtK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlassqK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jint, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasv2K(JNIEnv *, jobject,
    jdouble, jdouble, jdouble, jobject, jobject, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlaswpK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jint, jint, jint, jintArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasy2K(JNIEnv *, jobject,
    jboolean, jboolean, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jdoubleArray, jint, jint, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlasyfK(JNIEnv *, jobject,
    jstring, jint, jint, jobject, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlatbsK(JNIEnv *, jobject,
    jstring, jstring, jstring, jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlatdfK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject, jobject, jintArray, jint, jintArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlatpsK(JNIEnv *, jobject,
    jstring, jstring, jstring, jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jobject, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlatrdK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlatrsK(JNIEnv *, jobject,
    jstring, jstring, jstring, jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlatrzK(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlatzmK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jint, jdouble, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlauu2K(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlauumK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlazq3K(JNIEnv *, jobject,
    jint, jobject, jdoubleArray, jint, jint, jobject, jobject, jobject, jobject, jobject, jobject, jobject, jboolean, jobject, jobject, jobject, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlazq4K(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jint, jdouble, jdouble, jdouble, jdouble, jdouble, jdouble, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dopgtrK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dopmtrK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorg2lK(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorg2rK(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorgbrK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorghrK(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorgl2K(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorglqK(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorgqlK(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorgqrK(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorgr2K(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorgrqK(JNIEnv *, jobject,
    jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorgtrK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorm2lK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorm2rK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dormbrK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dormhrK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dorml2K(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dormlqK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dormqlK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dormqrK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dormr2K(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dormr3K(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dormrqK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dormrzK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dormtrK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpbconK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jint, jdouble, jobject, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpbequK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpbrfsK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpbstfK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpbsvK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpbsvxK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpbtf2K(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpbtrfK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpbtrsK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpoconK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jint, jdouble, jobject, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpoequK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dporfsK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dposvK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dposvxK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpotf2K(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpotrfK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpotriK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpotrsK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dppconK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jdouble, jobject, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dppequK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpprfsK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dppsvK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dppsvxK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpptrfK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpptriK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpptrsK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dptconK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jdoubleArray, jint, jdouble, jobject, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpteqrK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dptrfsK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dptsvK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dptsvxK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpttrfK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dpttrsK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dptts2K(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_drsclK(JNIEnv *, jobject,
    jint, jdouble, jdoubleArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsbevK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsbevdK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsbevxK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdouble, jdouble, jint, jint, jdouble, jobject, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsbgstK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsbgvK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsbgvdK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsbgvxK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdouble, jdouble, jint, jint, jdouble, jobject, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsbtrdK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsgesvK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jfloatArray, jint, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dspconK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jintArray, jint, jdouble, jobject, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dspevK(JNIEnv *, jobject,
    jstring, jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dspevdK(JNIEnv *, jobject,
    jstring, jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dspevxK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jdoubleArray, jint, jdouble, jdouble, jint, jint, jdouble, jobject, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dspgstK(JNIEnv *, jobject,
    jint, jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dspgvK(JNIEnv *, jobject,
    jint, jstring, jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dspgvdK(JNIEnv *, jobject,
    jint, jstring, jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dspgvxK(JNIEnv *, jobject,
    jint, jstring, jstring, jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jdouble, jdouble, jint, jint, jdouble, jobject, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsprfsK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dspsvK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jintArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dspsvxK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsptrdK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsptrfK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsptriK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jintArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsptrsK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jintArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dstebzK(JNIEnv *, jobject,
    jstring, jstring, jint, jdouble, jdouble, jint, jint, jdouble, jdoubleArray, jint, jdoubleArray, jint, jobject, jobject, jdoubleArray, jint, jintArray, jint, jintArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dstedcK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dstegrK(JNIEnv *, jobject,
    jstring, jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jdouble, jdouble, jint, jint, jdouble, jobject, jdoubleArray, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsteinK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jintArray, jint, jintArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dstemrK(JNIEnv *, jobject,
    jstring, jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jdouble, jdouble, jint, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jint, jint, jintArray, jint, jobject, jdoubleArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsteqrK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsterfK(JNIEnv *, jobject,
    jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dstevK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dstevdK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dstevrK(JNIEnv *, jobject,
    jstring, jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jdouble, jdouble, jint, jint, jdouble, jobject, jdoubleArray, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dstevxK(JNIEnv *, jobject,
    jstring, jstring, jint, jdoubleArray, jint, jdoubleArray, jint, jdouble, jdouble, jint, jint, jdouble, jobject, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsyconK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jint, jintArray, jint, jdouble, jobject, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsyevK(JNIEnv *, jobject,
    jstring, jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsyevdK(JNIEnv *, jobject,
    jstring, jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsyevrK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jdoubleArray, jint, jint, jdouble, jdouble, jint, jint, jdouble, jobject, jdoubleArray, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsyevxK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jdoubleArray, jint, jint, jdouble, jdouble, jint, jint, jdouble, jobject, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsygs2K(JNIEnv *, jobject,
    jint, jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsygstK(JNIEnv *, jobject,
    jint, jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsygvK(JNIEnv *, jobject,
    jint, jstring, jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsygvdK(JNIEnv *, jobject,
    jint, jstring, jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsygvxK(JNIEnv *, jobject,
    jint, jstring, jstring, jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdouble, jdouble, jint, jint, jdouble, jobject, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsyrfsK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsysvK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsysvxK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsytd2K(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsytf2K(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsytrdK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsytrfK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsytriK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dsytrsK(JNIEnv *, jobject,
    jstring, jint, jint, jdoubleArray, jint, jint, jintArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtbconK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jdoubleArray, jint, jint, jobject, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtbrfsK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtbtrsK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtgevcK(JNIEnv *, jobject,
    jstring, jstring, jbooleanArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jint, jobject, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtgex2K(JNIEnv *, jobject,
    jboolean, jboolean, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jint, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtgexcK(JNIEnv *, jobject,
    jboolean, jboolean, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jobject, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtgsenK(JNIEnv *, jobject,
    jint, jboolean, jboolean, jbooleanArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jobject, jobject, jdoubleArray, jint, jdoubleArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtgsjaK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdouble, jdouble, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtgsnaK(JNIEnv *, jobject,
    jstring, jstring, jbooleanArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject, jdoubleArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtgsy2K(JNIEnv *, jobject,
    jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jobject, jobject, jintArray, jint, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtgsylK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jobject, jdoubleArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtpconK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jdoubleArray, jint, jobject, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtprfsK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtptriK(JNIEnv *, jobject,
    jstring, jstring, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtptrsK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtrconK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jdoubleArray, jint, jint, jobject, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtrevcK(JNIEnv *, jobject,
    jstring, jstring, jbooleanArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jint, jobject, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtrexcK(JNIEnv *, jobject,
    jstring, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jobject, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtrrfsK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jdoubleArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtrsenK(JNIEnv *, jobject,
    jstring, jstring, jbooleanArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jobject, jobject, jobject, jdoubleArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtrsnaK(JNIEnv *, jobject,
    jstring, jstring, jbooleanArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject, jdoubleArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtrsylK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtrti2K(JNIEnv *, jobject,
    jstring, jstring, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtrtriK(JNIEnv *, jobject,
    jstring, jstring, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtrtrsK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtzrqfK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dtzrzfK(JNIEnv *, jobject,
    jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jdoubleArray, jint, jint, jobject) {
  
}

jint Java_dev_ludovic_netlib_lapack_JNILAPACK_ieeeckK(JNIEnv *, jobject,
    jint, jfloat, jfloat) {
  
}

jint Java_dev_ludovic_netlib_lapack_JNILAPACK_ilaenvK(JNIEnv *, jobject,
    jint, jstring, jstring, jint, jint, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ilaverK(JNIEnv *, jobject,
    jobject, jobject, jobject) {
  
}

jint Java_dev_ludovic_netlib_lapack_JNILAPACK_iparmqK(JNIEnv *, jobject,
    jint, jstring, jstring, jint, jint, jint, jint) {
  
}

jboolean Java_dev_ludovic_netlib_lapack_JNILAPACK_lsamenK(JNIEnv *, jobject,
    jint, jstring, jstring) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sbdsdcK(JNIEnv *, jobject,
    jstring, jstring, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jintArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sbdsqrK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sdisnaK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgbbrdK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgbconK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jfloatArray, jint, jint, jintArray, jint, jfloat, jobject, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgbequK(JNIEnv *, jobject,
    jint, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgbrfsK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgbsvK(JNIEnv *, jobject,
    jint, jint, jint, jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgbsvxK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgbtf2K(JNIEnv *, jobject,
    jint, jint, jint, jint, jfloatArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgbtrfK(JNIEnv *, jobject,
    jint, jint, jint, jint, jfloatArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgbtrsK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgebakK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgebalK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jint, jobject, jobject, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgebd2K(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgebrdK(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeconK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jint, jfloat, jobject, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeequK(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeesK(JNIEnv *, jobject,
    jstring, jstring, jobject, jint, jfloatArray, jint, jint, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jbooleanArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeesxK(JNIEnv *, jobject,
    jstring, jstring, jobject, jstring, jint, jfloatArray, jint, jint, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject, jobject, jfloatArray, jint, jint, jintArray, jint, jint, jbooleanArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeevK(JNIEnv *, jobject,
    jstring, jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeevxK(JNIEnv *, jobject,
    jstring, jstring, jstring, jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jobject, jfloatArray, jint, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgegsK(JNIEnv *, jobject,
    jstring, jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgegvK(JNIEnv *, jobject,
    jstring, jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgehd2K(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgehrdK(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgelq2K(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgelqfK(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgelsK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgelsdK(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloat, jobject, jfloatArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgelssK(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloat, jobject, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgelsxK(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jfloat, jobject, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgelsyK(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jfloat, jobject, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeql2K(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeqlfK(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeqp3K(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeqpfK(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeqr2K(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgeqrfK(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgerfsK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgerq2K(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgerqfK(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgesc2K(JNIEnv *, jobject,
    jint, jfloatArray, jint, jint, jfloatArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgesddK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgesvK(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgesvdK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgesvxK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgetc2K(JNIEnv *, jobject,
    jint, jfloatArray, jint, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgetf2K(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgetrfK(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgetriK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgetrsK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggbakK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggbalK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggesK(JNIEnv *, jobject,
    jstring, jstring, jstring, jobject, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jbooleanArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggesxK(JNIEnv *, jobject,
    jstring, jstring, jstring, jobject, jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jintArray, jint, jint, jbooleanArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggevK(JNIEnv *, jobject,
    jstring, jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggevxK(JNIEnv *, jobject,
    jstring, jstring, jstring, jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jobject, jfloatArray, jint, jfloatArray, jint, jobject, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jintArray, jint, jbooleanArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggglmK(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgghrdK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgglseK(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggqrfK(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggrqfK(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggsvdK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jint, jobject, jobject, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sggsvpK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloat, jfloat, jobject, jobject, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgtconK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jfloat, jobject, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgtrfsK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgtsvK(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgtsvxK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgttrfK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgttrsK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sgtts2K(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_shgeqzK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_shseinK(JNIEnv *, jobject,
    jstring, jstring, jstring, jbooleanArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jint, jobject, jfloatArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_shseqrK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

jboolean Java_dev_ludovic_netlib_lapack_JNILAPACK_sisnanK(JNIEnv *, jobject,
    jfloat) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slabadK(JNIEnv *, jobject,
    jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slabrdK(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slacn2K(JNIEnv *, jobject,
    jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject, jobject, jintArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaconK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slacpyK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sladivK(JNIEnv *, jobject,
    jfloat, jfloat, jfloat, jfloat, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slae2K(JNIEnv *, jobject,
    jfloat, jfloat, jfloat, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaebzK(JNIEnv *, jobject,
    jint, jint, jint, jint, jint, jint, jfloat, jfloat, jfloat, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jfloatArray, jint, jfloatArray, jint, jobject, jintArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaed0K(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaed1K(JNIEnv *, jobject,
    jint, jfloatArray, jint, jfloatArray, jint, jint, jintArray, jint, jobject, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaed2K(JNIEnv *, jobject,
    jobject, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jintArray, jint, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jintArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaed3K(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloat, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jintArray, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaed4K(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloat, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaed5K(JNIEnv *, jobject,
    jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloat, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaed6K(JNIEnv *, jobject,
    jint, jboolean, jfloat, jfloatArray, jint, jfloatArray, jint, jfloat, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaed7K(JNIEnv *, jobject,
    jint, jint, jint, jint, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jintArray, jint, jobject, jint, jfloatArray, jint, jintArray, jint, jintArray, jint, jintArray, jint, jintArray, jint, jintArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaed8K(JNIEnv *, jobject,
    jint, jobject, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jintArray, jint, jobject, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jintArray, jint, jobject, jintArray, jint, jfloatArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaed9K(JNIEnv *, jobject,
    jint, jint, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloat, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaedaK(JNIEnv *, jobject,
    jint, jint, jint, jint, jintArray, jint, jintArray, jint, jintArray, jint, jintArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaeinK(JNIEnv *, jobject,
    jboolean, jboolean, jint, jfloatArray, jint, jint, jfloat, jfloat, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloat, jfloat, jfloat, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaev2K(JNIEnv *, jobject,
    jfloat, jfloat, jfloat, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaexcK(JNIEnv *, jobject,
    jboolean, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jint, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slag2K(JNIEnv *, jobject,
    jfloatArray, jint, jint, jfloatArray, jint, jint, jfloat, jobject, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slag2dK(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jdoubleArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slags2K(JNIEnv *, jobject,
    jboolean, jfloat, jfloat, jfloat, jfloat, jfloat, jfloat, jobject, jobject, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slagtfK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jfloat, jfloatArray, jint, jfloatArray, jint, jfloat, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slagtmK(JNIEnv *, jobject,
    jstring, jint, jint, jfloat, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloat, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slagtsK(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jfloatArray, jint, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slagv2K(JNIEnv *, jobject,
    jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slahqrK(JNIEnv *, jobject,
    jboolean, jboolean, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slahr2K(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slahrdK(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaic1K(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jfloat, jfloatArray, jint, jfloat, jobject, jobject, jobject) {
  
}

jboolean Java_dev_ludovic_netlib_lapack_JNILAPACK_slaisnanK(JNIEnv *, jobject,
    jfloat, jfloat) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaln2K(JNIEnv *, jobject,
    jboolean, jint, jint, jfloat, jfloat, jfloatArray, jint, jint, jfloat, jfloat, jfloatArray, jint, jint, jfloat, jfloat, jfloatArray, jint, jint, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slals0K(JNIEnv *, jobject,
    jint, jint, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jint, jintArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloat, jfloat, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slalsaK(JNIEnv *, jobject,
    jint, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jintArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jintArray, jint, jint, jintArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slalsdK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloat, jobject, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slamrgK(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jint, jintArray, jint) {
  
}

jint Java_dev_ludovic_netlib_lapack_JNILAPACK_slanegK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jfloatArray, jint, jfloat, jfloat, jint) {
  
}

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slangbK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint) {
  
}

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slangeK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint) {
  
}

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slangtK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint) {
  
}

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slanhsK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jint, jfloatArray, jint) {
  
}

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slansbK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint) {
  
}

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slanspK(JNIEnv *, jobject,
    jstring, jstring, jint, jfloatArray, jint, jfloatArray, jint) {
  
}

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slanstK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jfloatArray, jint) {
  
}

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slansyK(JNIEnv *, jobject,
    jstring, jstring, jint, jfloatArray, jint, jint, jfloatArray, jint) {
  
}

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slantbK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint) {
  
}

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slantpK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jfloatArray, jint, jfloatArray, jint) {
  
}

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slantrK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slanv2K(JNIEnv *, jobject,
    jobject, jobject, jobject, jobject, jobject, jobject, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slapllK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slapmtK(JNIEnv *, jobject,
    jboolean, jint, jint, jfloatArray, jint, jint, jintArray, jint) {
  
}

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slapy2K(JNIEnv *, jobject,
    jfloat, jfloat) {
  
}

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slapy3K(JNIEnv *, jobject,
    jfloat, jfloat, jfloat) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqgbK(JNIEnv *, jobject,
    jint, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloat, jfloat, jfloat, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqgeK(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloat, jfloat, jfloat, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqp2K(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqpsK(JNIEnv *, jobject,
    jint, jint, jint, jint, jobject, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqr0K(JNIEnv *, jobject,
    jboolean, jboolean, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqr1K(JNIEnv *, jobject,
    jint, jfloatArray, jint, jint, jfloat, jfloat, jfloat, jfloat, jfloatArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqr2K(JNIEnv *, jobject,
    jboolean, jboolean, jint, jint, jint, jint, jfloatArray, jint, jint, jint, jint, jfloatArray, jint, jint, jobject, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jint, jfloatArray, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqr3K(JNIEnv *, jobject,
    jboolean, jboolean, jint, jint, jint, jint, jfloatArray, jint, jint, jint, jint, jfloatArray, jint, jint, jobject, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jint, jfloatArray, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqr4K(JNIEnv *, jobject,
    jboolean, jboolean, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqr5K(JNIEnv *, jobject,
    jboolean, jboolean, jint, jint, jint, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jint, jfloatArray, jint, jint, jint, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqsbK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloat, jfloat, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqspK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jfloatArray, jint, jfloat, jfloat, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqsyK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloat, jfloat, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaqtrK(JNIEnv *, jobject,
    jboolean, jboolean, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloat, jobject, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slar1vK(JNIEnv *, jobject,
    jint, jint, jint, jfloat, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloat, jfloat, jfloatArray, jint, jboolean, jobject, jobject, jobject, jobject, jintArray, jint, jobject, jobject, jobject, jfloatArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slar2vK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarfK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jint, jfloat, jfloatArray, jint, jint, jfloatArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarfbK(JNIEnv *, jobject,
    jstring, jstring, jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarfgK(JNIEnv *, jobject,
    jint, jobject, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarftK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarfxK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jfloat, jfloatArray, jint, jint, jfloatArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slargvK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarnvK(JNIEnv *, jobject,
    jint, jintArray, jint, jint, jfloatArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarraK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloat, jfloat, jobject, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarrbK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jfloatArray, jint, jint, jint, jfloat, jfloat, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jfloat, jfloat, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarrcK(JNIEnv *, jobject,
    jstring, jint, jfloat, jfloat, jfloatArray, jint, jfloatArray, jint, jfloat, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarrdK(JNIEnv *, jobject,
    jstring, jstring, jint, jfloat, jfloat, jint, jint, jfloatArray, jint, jfloat, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloat, jint, jintArray, jint, jobject, jfloatArray, jint, jfloatArray, jint, jobject, jobject, jintArray, jint, jintArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarreK(JNIEnv *, jobject,
    jstring, jint, jobject, jobject, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloat, jfloat, jfloat, jobject, jintArray, jint, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jintArray, jint, jfloatArray, jint, jobject, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarrfK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloat, jfloat, jfloat, jfloat, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarrjK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jfloatArray, jint, jint, jint, jfloat, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jfloat, jfloat, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarrkK(JNIEnv *, jobject,
    jint, jint, jfloat, jfloat, jfloatArray, jint, jfloatArray, jint, jfloat, jfloat, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarrrK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarrvK(JNIEnv *, jobject,
    jint, jfloat, jfloat, jfloatArray, jint, jfloatArray, jint, jfloat, jintArray, jint, jint, jint, jint, jfloat, jobject, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jintArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slartgK(JNIEnv *, jobject,
    jfloat, jfloat, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slartvK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaruvK(JNIEnv *, jobject,
    jintArray, jint, jint, jfloatArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarzK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jfloatArray, jint, jint, jfloat, jfloatArray, jint, jint, jfloatArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarzbK(JNIEnv *, jobject,
    jstring, jstring, jstring, jstring, jint, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slarztK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slas2K(JNIEnv *, jobject,
    jfloat, jfloat, jfloat, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasclK(JNIEnv *, jobject,
    jstring, jint, jint, jfloat, jfloat, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasd0K(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jint, jintArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasd1K(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jobject, jobject, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jintArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasd2K(JNIEnv *, jobject,
    jint, jint, jint, jobject, jfloatArray, jint, jfloatArray, jint, jfloat, jfloat, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jintArray, jint, jintArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasd3K(JNIEnv *, jobject,
    jint, jint, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jintArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasd4K(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloat, jobject, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasd5K(JNIEnv *, jobject,
    jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloat, jobject, jfloatArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasd6K(JNIEnv *, jobject,
    jint, jint, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jobject, jobject, jintArray, jint, jintArray, jint, jobject, jintArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jobject, jobject, jobject, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasd7K(JNIEnv *, jobject,
    jint, jint, jint, jint, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloat, jfloat, jfloatArray, jint, jintArray, jint, jintArray, jint, jintArray, jint, jintArray, jint, jobject, jintArray, jint, jint, jfloatArray, jint, jint, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasd8K(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasdaK(JNIEnv *, jobject,
    jint, jint, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jintArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jintArray, jint, jint, jintArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasdqK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasdtK(JNIEnv *, jobject,
    jint, jobject, jobject, jintArray, jint, jintArray, jint, jintArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasetK(JNIEnv *, jobject,
    jstring, jint, jint, jfloat, jfloat, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasq1K(JNIEnv *, jobject,
    jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasq2K(JNIEnv *, jobject,
    jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasq3K(JNIEnv *, jobject,
    jint, jobject, jfloatArray, jint, jint, jobject, jobject, jobject, jobject, jobject, jobject, jobject, jboolean) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasq4K(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jint, jfloat, jfloat, jfloat, jfloat, jfloat, jfloat, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasq5K(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jfloat, jobject, jobject, jobject, jobject, jobject, jobject, jboolean) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasq6K(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jobject, jobject, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasrK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasrtK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slassqK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jint, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasv2K(JNIEnv *, jobject,
    jfloat, jfloat, jfloat, jobject, jobject, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slaswpK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jint, jint, jint, jintArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasy2K(JNIEnv *, jobject,
    jboolean, jboolean, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jfloatArray, jint, jint, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slasyfK(JNIEnv *, jobject,
    jstring, jint, jint, jobject, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slatbsK(JNIEnv *, jobject,
    jstring, jstring, jstring, jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slatdfK(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject, jobject, jintArray, jint, jintArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slatpsK(JNIEnv *, jobject,
    jstring, jstring, jstring, jstring, jint, jfloatArray, jint, jfloatArray, jint, jobject, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slatrdK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slatrsK(JNIEnv *, jobject,
    jstring, jstring, jstring, jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slatrzK(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slatzmK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jint, jfloat, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slauu2K(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slauumK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slazq3K(JNIEnv *, jobject,
    jint, jobject, jfloatArray, jint, jint, jobject, jobject, jobject, jobject, jobject, jobject, jobject, jboolean, jobject, jobject, jobject, jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slazq4K(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jint, jfloat, jfloat, jfloat, jfloat, jfloat, jfloat, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sopgtrK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sopmtrK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorg2lK(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorg2rK(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorgbrK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorghrK(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorgl2K(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorglqK(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorgqlK(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorgqrK(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorgr2K(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorgrqK(JNIEnv *, jobject,
    jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorgtrK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorm2lK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorm2rK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sormbrK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sormhrK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sorml2K(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sormlqK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sormqlK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sormqrK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sormr2K(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sormr3K(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sormrqK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sormrzK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sormtrK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spbconK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jint, jfloat, jobject, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spbequK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spbrfsK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spbstfK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spbsvK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spbsvxK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spbtf2K(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spbtrfK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spbtrsK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spoconK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jint, jfloat, jobject, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spoequK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jint, jfloatArray, jint, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sporfsK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sposvK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sposvxK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spotf2K(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spotrfK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spotriK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spotrsK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sppconK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jfloat, jobject, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sppequK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jfloatArray, jint, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spprfsK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sppsvK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sppsvxK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jfloatArray, jint, jfloatArray, jint, jobject, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spptrfK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spptriK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spptrsK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sptconK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jfloatArray, jint, jfloat, jobject, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spteqrK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sptrfsK(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sptsvK(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sptsvxK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spttrfK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_spttrsK(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sptts2K(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_srsclK(JNIEnv *, jobject,
    jint, jfloat, jfloatArray, jint, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssbevK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssbevdK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssbevxK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloat, jfloat, jint, jint, jfloat, jobject, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssbgstK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssbgvK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssbgvdK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssbgvxK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloat, jfloat, jint, jint, jfloat, jobject, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssbtrdK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sspconK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jintArray, jint, jfloat, jobject, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sspevK(JNIEnv *, jobject,
    jstring, jstring, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sspevdK(JNIEnv *, jobject,
    jstring, jstring, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sspevxK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jfloatArray, jint, jfloat, jfloat, jint, jint, jfloat, jobject, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sspgstK(JNIEnv *, jobject,
    jint, jstring, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sspgvK(JNIEnv *, jobject,
    jint, jstring, jstring, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sspgvdK(JNIEnv *, jobject,
    jint, jstring, jstring, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sspgvxK(JNIEnv *, jobject,
    jint, jstring, jstring, jstring, jint, jfloatArray, jint, jfloatArray, jint, jfloat, jfloat, jint, jint, jfloat, jobject, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssprfsK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sspsvK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jintArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sspsvxK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssptrdK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssptrfK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssptriK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jintArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssptrsK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jintArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sstebzK(JNIEnv *, jobject,
    jstring, jstring, jint, jfloat, jfloat, jint, jint, jfloat, jfloatArray, jint, jfloatArray, jint, jobject, jobject, jfloatArray, jint, jintArray, jint, jintArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sstedcK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sstegrK(JNIEnv *, jobject,
    jstring, jstring, jint, jfloatArray, jint, jfloatArray, jint, jfloat, jfloat, jint, jint, jfloat, jobject, jfloatArray, jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssteinK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jintArray, jint, jintArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sstemrK(JNIEnv *, jobject,
    jstring, jstring, jint, jfloatArray, jint, jfloatArray, jint, jfloat, jfloat, jint, jint, jobject, jfloatArray, jint, jfloatArray, jint, jint, jint, jintArray, jint, jobject, jfloatArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssteqrK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssterfK(JNIEnv *, jobject,
    jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sstevK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sstevdK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sstevrK(JNIEnv *, jobject,
    jstring, jstring, jint, jfloatArray, jint, jfloatArray, jint, jfloat, jfloat, jint, jint, jfloat, jobject, jfloatArray, jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_sstevxK(JNIEnv *, jobject,
    jstring, jstring, jint, jfloatArray, jint, jfloatArray, jint, jfloat, jfloat, jint, jint, jfloat, jobject, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssyconK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jint, jintArray, jint, jfloat, jobject, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssyevK(JNIEnv *, jobject,
    jstring, jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssyevdK(JNIEnv *, jobject,
    jstring, jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssyevrK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jfloatArray, jint, jint, jfloat, jfloat, jint, jint, jfloat, jobject, jfloatArray, jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssyevxK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jfloatArray, jint, jint, jfloat, jfloat, jint, jint, jfloat, jobject, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssygs2K(JNIEnv *, jobject,
    jint, jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssygstK(JNIEnv *, jobject,
    jint, jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssygvK(JNIEnv *, jobject,
    jint, jstring, jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssygvdK(JNIEnv *, jobject,
    jint, jstring, jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssygvxK(JNIEnv *, jobject,
    jint, jstring, jstring, jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloat, jfloat, jint, jint, jfloat, jobject, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssyrfsK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssysvK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssysvxK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssytd2K(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssytf2K(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssytrdK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssytrfK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssytriK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_ssytrsK(JNIEnv *, jobject,
    jstring, jint, jint, jfloatArray, jint, jint, jintArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stbconK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jfloatArray, jint, jint, jobject, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stbrfsK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stbtrsK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stgevcK(JNIEnv *, jobject,
    jstring, jstring, jbooleanArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jint, jobject, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stgex2K(JNIEnv *, jobject,
    jboolean, jboolean, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jint, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stgexcK(JNIEnv *, jobject,
    jboolean, jboolean, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jobject, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stgsenK(JNIEnv *, jobject,
    jint, jboolean, jboolean, jbooleanArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jobject, jobject, jfloatArray, jint, jfloatArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stgsjaK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloat, jfloat, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stgsnaK(JNIEnv *, jobject,
    jstring, jstring, jbooleanArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject, jfloatArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stgsy2K(JNIEnv *, jobject,
    jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jobject, jobject, jintArray, jint, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stgsylK(JNIEnv *, jobject,
    jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jobject, jfloatArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stpconK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jfloatArray, jint, jobject, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stprfsK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stptriK(JNIEnv *, jobject,
    jstring, jstring, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stptrsK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_strconK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jfloatArray, jint, jint, jobject, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_strevcK(JNIEnv *, jobject,
    jstring, jstring, jbooleanArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jint, jobject, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_strexcK(JNIEnv *, jobject,
    jstring, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jobject, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_strrfsK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jfloatArray, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_strsenK(JNIEnv *, jobject,
    jstring, jstring, jbooleanArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jobject, jobject, jobject, jfloatArray, jint, jint, jintArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_strsnaK(JNIEnv *, jobject,
    jstring, jstring, jbooleanArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject, jfloatArray, jint, jint, jintArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_strsylK(JNIEnv *, jobject,
    jstring, jstring, jint, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_strti2K(JNIEnv *, jobject,
    jstring, jstring, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_strtriK(JNIEnv *, jobject,
    jstring, jstring, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_strtrsK(JNIEnv *, jobject,
    jstring, jstring, jstring, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stzrqfK(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_stzrzfK(JNIEnv *, jobject,
    jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jfloatArray, jint, jint, jobject) {
  
}

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlamchK(JNIEnv *, jobject,
    jstring) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlamc1K(JNIEnv *, jobject,
    jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlamc2K(JNIEnv *, jobject,
    jobject, jobject, jobject, jobject, jobject, jobject, jobject, jobject) {
  
}

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dlamc3K(JNIEnv *, jobject,
    jdouble, jdouble) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlamc4K(JNIEnv *, jobject,
    jobject, jdouble, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_dlamc5K(JNIEnv *, jobject,
    jint, jint, jint, jboolean, jobject, jobject) {
  
}

jdouble Java_dev_ludovic_netlib_lapack_JNILAPACK_dsecndK(JNIEnv *, jobject);
    jboolean Java_dev_ludovic_netlib_lapack_JNILAPACK_lsameK(JNIEnv *, jobject {
  
}

    jstring, jstring) {
  
}

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_secondK(JNIEnv *, jobject);
    jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slamchK(JNIEnv *, jobject {
  
}

    jstring) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slamc1K(JNIEnv *, jobject,
    jobject, jobject, jobject, jobject) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slamc2K(JNIEnv *, jobject,
    jobject, jobject, jobject, jobject, jobject, jobject, jobject, jobject) {
  
}

jfloat Java_dev_ludovic_netlib_lapack_JNILAPACK_slamc3K(JNIEnv *, jobject,
    jfloat, jfloat) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slamc4K(JNIEnv *, jobject,
    jobject, jfloat, jint) {
  
}

void Java_dev_ludovic_netlib_lapack_JNILAPACK_slamc5K(JNIEnv *, jobject,
    jint, jint, jint, jboolean, jobject, jobject) {
  
}


static void *lapack;

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
  jstring native_lib_path = get_system_property(env, (*env)->NewStringUTF(env, "dev.ludovic.netlib.lapack.nativeLibPath"), NULL);
  jstring native_lib = get_system_property(env, (*env)->NewStringUTF(env, "dev.ludovic.netlib.lapack.nativeLib"), (*env)->NewStringUTF(env, "lapack"));
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

  lapack = dlopen(blas_name, RTLD_LAZY);
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
  LOAD_SYMBOL(dlazq3_)
  LOAD_SYMBOL(dlauum_)
  LOAD_SYMBOL(dopgtr_)
  LOAD_SYMBOL(dlazq4_)
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
  LOAD_SYMBOL(slazq3_)
  LOAD_SYMBOL(slazq4_)
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
  LOAD_SYMBOL(dlamc1_)
  LOAD_SYMBOL(dlamc2_)
  LOAD_SYMBOL(dlamc3_)
  LOAD_SYMBOL(dlamc4_)
  LOAD_SYMBOL(dlamc5_)
  LOAD_SYMBOL(dsecnd_)
  LOAD_SYMBOL(lsame_)
  LOAD_SYMBOL(second_)
  LOAD_SYMBOL(slamch_)
  LOAD_SYMBOL(slamc1_)
  LOAD_SYMBOL(slamc2_)
  LOAD_SYMBOL(slamc3_)
  LOAD_SYMBOL(slamc4_)
  LOAD_SYMBOL(slamc5_)

#undef LOAD_SYMBOL

  return JNI_VERSION_1_6;
}

void JNI_OnUnload(UNUSED JavaVM *vm, UNUSED void *reserved) {
  dlclose(lapack);
}
