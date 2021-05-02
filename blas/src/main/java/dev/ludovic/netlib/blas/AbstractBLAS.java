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

package dev.ludovic.netlib.blas;

import java.util.Objects;

import dev.ludovic.netlib.BLAS;

abstract class AbstractBLAS<T> implements BLAS {

  protected int loopAlign(int index, int max, int size) {
    return Math.min(loopBound(index + size - 1, size), max);
  }

  protected int loopBound(int index, int size) {
    return index - (index % size);
  }

  private void checkArgument(String method, int arg, boolean check) {
    if (!check) {
      throw new IllegalArgumentException(String.format("** On entry to '%s' parameter number %d had an illegal value", method, arg));
    }
  }

  private void checkIndex(int index, int length) {
    //FIXME: switch to Objects.checkIndex when the minimum version becomes JDK 11
    if (index < 0 || index >= length) {
      throw new IndexOutOfBoundsException(String.format("Index %s out of bounds for length %s", index, length));
    }
  }

  public double dasum(int n, double[] x, int incx) {
    return dasum(n, x, 0, incx);
  }

  public double dasum(int n, double[] x, int offsetx, int incx) {
    if (n <= 0) {
      return 0.0;
    }
    Objects.requireNonNull(x);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    return dasumK(n, x, offsetx, incx);
  }

  protected abstract double dasumK(int n, double[] x, int offsetx, int incx);

  public float sasum(int n, float[] x, int incx) {
    return sasum(n, x, 0, incx);
  }

  public float sasum(int n, float[] x, int offsetx, int incx) {
    if (n <= 0) {
      return 0.0f;
    }
    Objects.requireNonNull(x);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    return sasumK(n, x, offsetx, incx);
  }

  protected abstract float sasumK(int n, float[] x, int offsetx, int incx);

  public void daxpy(int n, double alpha, double[] x, int incx, double[] y, int incy) {
    daxpy(n, alpha, x, 0, incx, y, 0, incy);
  }

  // y += alpha * x
  public void daxpy(int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (n <= 0) {
      return;
    }
    if (alpha == 0.0) {
      return;
    }
    Objects.requireNonNull(x);
    Objects.requireNonNull(y);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    daxpyK(n, alpha, x, offsetx, incx, y, offsety, incy);
  }

  protected abstract void daxpyK(int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy);

  public void saxpy(int n, float alpha, float[] x, int incx, float[] y, int incy) {
    saxpy(n, alpha, x, 0, incx, y, 0, incy);
  }

  // y += alpha * x
  public void saxpy(int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (n <= 0) {
      return;
    }
    if (alpha == 0.0f) {
      return;
    }
    Objects.requireNonNull(x);
    Objects.requireNonNull(y);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    saxpyK(n, alpha, x, offsetx, incx, y, offsety, incy);
  }

  protected abstract void saxpyK(int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy);

  public void dcopy(int n, double[] x, int incx, double[] y, int incy) {
    dcopy(n, x, 0, incx, y, 0, incy);
  }

  public void dcopy(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (n <= 0) {
      return;
    }
    Objects.requireNonNull(x);
    Objects.requireNonNull(y);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    dcopyK(n, x, offsetx, incx, y, offsety, incy);
  }

  protected abstract void dcopyK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy);

  public void scopy(int n, float[] x, int incx, float[] y, int incy) {
    scopy(n, x, 0, incx, y, 0, incy);
  }

  public void scopy(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (n <= 0) {
      return;
    }
    Objects.requireNonNull(x);
    Objects.requireNonNull(y);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    scopyK(n, x, offsetx, incx, y, offsety, incy);
  }

  protected abstract void scopyK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy);

  public double ddot(int n, double[] x, int incx, double[] y, int incy) {
    return ddot(n, x, 0, incx, y, 0, incy);
  }

  // sum(x * y)
  public double ddot(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (n <= 0) {
      return 0.0;
    }
    Objects.requireNonNull(x);
    Objects.requireNonNull(y);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    return ddotK(n, x, offsetx, incx, y, offsety, incy);
  }

  protected abstract double ddotK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy);

  public float sdot(int n, float[] x, int incx, float[] y, int incy) {
    return sdot(n, x, 0, incx, y, 0, incy);
  }

  // sum(x * y)
  public float sdot(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (n <= 0) {
      return 0.0f;
    }
    Objects.requireNonNull(x);
    Objects.requireNonNull(y);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    return sdotK(n, x, offsetx, incx, y, offsety, incy);
  }

  protected abstract float sdotK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy);

  public float sdsdot(int n, float sb, float[] x, int incx, float[] y, int incy) {
    return sdsdot(n, sb, x, 0, incx, y, 0, incy);
  }

  public float sdsdot(int n, float sb, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (n <= 0) {
      return 0.0f;
    }
    return sdsdotK(n, sb, x, offsetx, incx, y, offsety, incy);
  }

  protected abstract float sdsdotK(int n, float sb, float[] x, int offsetx, int incx, float[] y, int offsety, int incy);

  public void dgbmv(String trans, int m, int n, int kl, int ku, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    dgbmv(trans, m, n, kl, ku, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void dgbmv(String trans, int m, int n, int kl, int ku, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    //FIXME: add arguments checks
    dgbmvK(trans, m, n, kl, ku, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected abstract void dgbmvK(String trans, int m, int n, int kl, int ku, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);

  public void sgbmv(String trans, int m, int n, int kl, int ku, float alpha, float[] a, int lda, float[] x, int incx, float beta, float[] y, int incy) {
    sgbmv(trans, m, n, kl, ku, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void sgbmv(String trans, int m, int n, int kl, int ku, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    //FIXME: add arguments checks
    sgbmvK(trans, m, n, kl, ku, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected abstract void sgbmvK(String trans, int m, int n, int kl, int ku, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);

  public void dgemm(String transa, String transb, int m, int n, int k, double alpha, double[] a, int lda, double[] b, int ldb, double beta, double[] c, int ldc) {
    dgemm(transa, transb, m, n, k, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
  }

  // c = alpha * a * b + beta * c
  public void dgemm(String transa, String transb, int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    checkArgument("DGEMM", 1, lsame("T", transa) || lsame("N", transa) || lsame("C", transa));
    checkArgument("DGEMM", 2, lsame("T", transb) || lsame("N", transb) || lsame("C", transb));
    checkArgument("DGEMM", 3, m >= 0);
    checkArgument("DGEMM", 4, n >= 0);
    checkArgument("DGEMM", 5, k >= 0);
    checkArgument("DGEMM", 8, lda >= Math.max(1, lsame("N", transa) ? m : k));
    checkArgument("DGEMM", 10, ldb >= Math.max(1, lsame("N", transb) ? k : n));
    checkArgument("DGEMM", 13, ldc >= Math.max(1, m));
    if (m == 0 || n == 0 || ((alpha == 0.0 || k == 0) && beta == 1.0)) {
      return;
    }
    dgemmK(transa, transb, m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected abstract void dgemmK(String transa, String transb, int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc);

  public void sgemm(String transa, String transb, int m, int n, int k, float alpha, float[] a, int lda, float[] b, int ldb, float beta, float[] c, int ldc) {
    sgemm(transa, transb, m, n, k, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
  }

  // c = alpha * a * b + beta * c
  public void sgemm(String transa, String transb, int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    checkArgument("SGEMM", 1, lsame("T", transa) || lsame("N", transa) || lsame("C", transa));
    checkArgument("SGEMM", 2, lsame("T", transb) || lsame("N", transb) || lsame("C", transb));
    checkArgument("SGEMM", 3, m >= 0);
    checkArgument("SGEMM", 4, n >= 0);
    checkArgument("SGEMM", 5, k >= 0);
    checkArgument("SGEMM", 8, lda >= Math.max(1, lsame("N", transa) ? m : k));
    checkArgument("SGEMM", 10, ldb >= Math.max(1, lsame("N", transb) ? k : n));
    checkArgument("SGEMM", 13, ldc >= Math.max(1, m));
    if (m == 0 || n == 0 || ((alpha == 0.0f || k == 0) && beta == 1.0f)) {
      return;
    }
    sgemmK(transa, transb, m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected abstract void sgemmK(String transa, String transb, int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc);

  public void dgemv(String trans, int m, int n, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    dgemv(trans, m, n, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  // y = alpha * A * x + beta * y
  public void dgemv(String trans, int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    checkArgument("DGEMV", 1, lsame("N", trans) || lsame("T", trans) || lsame("C", trans));
    checkArgument("DGEMV", 2, m >= 0);
    checkArgument("DGEMV", 3, n >= 0);
    checkArgument("DGEMV", 6, lda >= Math.max(1, m));
    checkArgument("DGEMV", 8, incx != 0);
    checkArgument("DGEMV", 11, incy != 0);
    if (m == 0 || n == 0) {
      return;
    }
    dgemvK(trans, m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected abstract void dgemvK(String trans, int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);

  public void sgemv(String trans, int m, int n, float alpha, float[] a, int lda, float[] x, int incx, float beta, float[] y, int incy) {
    sgemv(trans, m, n, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  // y = alpha * A * x + beta * y
  public void sgemv(String trans, int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    checkArgument("SGEMV", 1, lsame("N", trans) || lsame("T", trans) || lsame("C", trans));
    checkArgument("SGEMV", 2, m >= 0);
    checkArgument("SGEMV", 3, n >= 0);
    checkArgument("SGEMV", 6, lda >= Math.max(1, m));
    checkArgument("SGEMV", 8, incx != 0);
    checkArgument("SGEMV", 11, incy != 0);
    if (m == 0 || n == 0) {
      return;
    }
    sgemvK(trans, m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected abstract void sgemvK(String trans, int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);

  // A += alpha * x * y.t
  public void dger(int m, int n, double alpha, double[] x, int incx, double[] y, int incy, double[] a, int lda) {
    dger(m, n, alpha, x, 0, incx, y, 0, incy, a, 0, lda);
  }

  public void dger(int m, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
    checkArgument("DGER", 1, m >= 0);
    checkArgument("DGER", 2, n >= 0);
    checkArgument("DGER", 5, incx != 0);
    checkArgument("DGER", 7, incy != 0);
    checkArgument("DGER", 9, lda >= Math.max(1, m));
    if (m == 0 || n == 0) {
      return;
    }
    if (alpha != 0.0) {
      dgerK(m, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
    }
  }

  protected abstract void dgerK(int m, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda);

  public void sger(int m, int n, float alpha, float[] x, int incx, float[] y, int incy, float[] a, int lda) {
    sger(m, n, alpha, x, 0, incx, y, 0, incy, a, 0, lda);
  }

  public void sger(int m, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
    checkArgument("SGER", 1, m >= 0);
    checkArgument("SGER", 2, n >= 0);
    checkArgument("SGER", 5, incx != 0);
    checkArgument("SGER", 7, incy != 0);
    checkArgument("SGER", 9, lda >= Math.max(1, m));
    if (m == 0 || n == 0) {
      return;
    }
    if (alpha != 0.0f) {
      sgerK(m, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
    }
  }

  protected abstract void sgerK(int m, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda);

  public double dnrm2(int n, double[] x, int incx) {
    return dnrm2(n, x, 0, incx);
  }

  public double dnrm2(int n, double[] x, int offsetx, int incx) {
    if (n <= 0) {
      return 0.0;
    }
    if (incx <= 0) {
      return 0.0;
    }
    if (n == 1) {
      return Math.abs(x[offsetx + 0]);
    }
    return dnrm2K(n, x, offsetx, incx);
  }

  protected abstract double dnrm2K(int n, double[] x, int offsetx, int incx);

  public float snrm2(int n, float[] x, int incx) {
    return snrm2(n, x, 0, incx);
  }

  public float snrm2(int n, float[] x, int offsetx, int incx) {
    if (n <= 0) {
      return 0.0f;
    }
    if (incx <= 0) {
      return 0.0f;
    }
    if (n == 1) {
      return Math.abs(x[offsetx + 0]);
    }
    return snrm2K(n, x, offsetx, incx);
  }

  protected abstract float snrm2K(int n, float[] x, int offsetx, int incx);

  public void drot(int n, double[] x, int incx, double[] y, int incy, double c, double s) {
    drot(n, x, 0, incx, y, 0, incy, c, s);
  }

  public void drot(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double c, double s) {
    if (n <= 0) {
      return;
    }
    drotK(n, x, offsetx, incx, y, offsety, incy, c, s);
  }

  protected abstract void drotK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double c, double s);

  public void srot(int n, float[] x, int incx, float[] y, int incy, float c, float s) {
    srot(n, x, 0, incx, y, 0, incy, c, s);
  }

  public void srot(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float c, float s) {
    if (n <= 0) {
      return;
    }
    srotK(n, x, offsetx, incx, y, offsety, incy, c, s);
  }

  protected abstract void srotK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float c, float s);

  public void drotg(org.netlib.util.doubleW da, org.netlib.util.doubleW db, org.netlib.util.doubleW c, org.netlib.util.doubleW s) {
    double scale = Math.abs(da.val) + Math.abs(db.val);
    if (scale == 0.0) {
      c.val = 1.0;
      s.val = 0.0;
      da.val = 0.0;
      db.val = 0.0;
    } else {
      double r = scale * Math.sqrt(Math.pow(da.val / scale, 2) + Math.pow(db.val / scale, 2))
                      * ((Math.abs(da.val) > Math.abs(db.val) ? da.val : db.val) >= 0.0 ? 1.0 : -1.0);
      c.val = da.val / r;
      s.val = db.val / r;
      double z = 1.0;
      if (Math.abs(da.val) > Math.abs(db.val)) {
        z = s.val;
      } else if (c.val != 0.0) {
        z = 1.0 / c.val;
      }
      da.val = r;
      db.val = z;
    }
  }

  public void srotg(org.netlib.util.floatW sa, org.netlib.util.floatW sb, org.netlib.util.floatW c, org.netlib.util.floatW s) {
    float scale = Math.abs(sa.val) + Math.abs(sb.val);
    if (scale == 0.0f) {
      c.val = 1.0f;
      s.val = 0.0f;
      sa.val = 0.0f;
      sb.val = 0.0f;
    } else {
      float r = (float)(scale * Math.sqrt(Math.pow(sa.val / scale, 2) + Math.pow(sb.val / scale, 2))
                              * ((Math.abs(sa.val) > Math.abs(sb.val) ? sa.val : sb.val) >= 0.0f ? 1.0 : -1.0));
      c.val = sa.val / r;
      s.val = sb.val / r;
      float z = 1.0f;
      if (Math.abs(sa.val) > Math.abs(sb.val)) {
        z = s.val;
      } else if (c.val != 0.0f) {
        z = 1.0f / c.val;
      }
      sa.val = r;
      sb.val = z;
    }
  }

  public void drotm(int n, double[] x, int incx, double[] y, int incy, double[] param) {
    drotm(n, x, 0, incx, y, 0, incy, param, 0);
  }

  public void drotm(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] param, int offsetparam) {
    //FIXME: add arguments checks
    drotmK(n, x, offsetx, incx, y, offsety, incy, param, offsetparam);
  }

  protected abstract void drotmK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] param, int offsetparam);

  public void srotm(int n, float[] x, int incx, float[] y, int incy, float[] param) {
    srotm(n, x, 0, incx, y, 0, incy, param, 0);
  }

  public void srotm(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] param, int offsetparam) {
    //FIXME: add arguments checks
    srotmK(n, x, offsetx, incx, y, offsety, incy, param, offsetparam);
  }

  protected abstract void srotmK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] param, int offsetparam);

  public void drotmg(org.netlib.util.doubleW dd1, org.netlib.util.doubleW dd2, org.netlib.util.doubleW dx1, double dy1, double[] param) {
    drotmg(dd1, dd2, dx1, dy1, param, 0);
  }

  public void drotmg(org.netlib.util.doubleW dd1, org.netlib.util.doubleW dd2, org.netlib.util.doubleW dx1, double dy1, double[] param, int offsetparam) {
    //FIXME: add arguments checks
    drotmgK(dd1, dd2, dx1, dy1, param, offsetparam);
  }

  protected abstract void drotmgK(org.netlib.util.doubleW dd1, org.netlib.util.doubleW dd2, org.netlib.util.doubleW dx1, double dy1, double[] param, int offsetparam);

  public void srotmg(org.netlib.util.floatW sd1, org.netlib.util.floatW sd2, org.netlib.util.floatW sx1, float sy1, float[] param) {
    srotmg(sd1, sd2, sx1, sy1, param, 0);
  }

  public void srotmg(org.netlib.util.floatW sd1, org.netlib.util.floatW sd2, org.netlib.util.floatW sx1, float sy1, float[] param, int offsetparam) {
    //FIXME: add arguments checks
    srotmgK(sd1, sd2, sx1, sy1, param, offsetparam);
  }

  protected abstract void srotmgK(org.netlib.util.floatW sd1, org.netlib.util.floatW sd2, org.netlib.util.floatW sx1, float sy1, float[] param, int offsetparam);

  public void dsbmv(String uplo, int n, int k, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    dsbmv(uplo, n, k, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void dsbmv(String uplo, int n, int k, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    //FIXME: add arguments checks
    dsbmvK(uplo, n, k, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected abstract void dsbmvK(String uplo, int n, int k, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);

  public void ssbmv(String uplo, int n, int k, float alpha, float[] a, int lda, float[] x, int incx, float beta, float[] y, int incy) {
    ssbmv(uplo, n, k, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void ssbmv(String uplo, int n, int k, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    //FIXME: add arguments checks
    ssbmvK(uplo, n, k, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected abstract void ssbmvK(String uplo, int n, int k, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);

  public void dscal(int n, double alpha, double[] x, int incx) {
    dscal(n, alpha, x, 0, incx);
  }

  // x = alpha * x
  public void dscal(int n, double alpha, double[] x, int offsetx, int incx) {
    if (n <= 0) {
      return;
    }
    if (incx <= 0) {
      return;
    }
    if (alpha == 1.0) {
      return;
    }
    dscalK(n, alpha, x, offsetx, incx);
  }

  protected abstract void dscalK(int n, double alpha, double[] x, int offsetx, int incx);

  public void sscal(int n, float alpha, float[] x, int incx) {
    sscal(n, alpha, x, 0, incx);
  }

  // x = alpha * x
  public void sscal(int n, float alpha, float[] x, int offsetx, int incx) {
    if (n <= 0) {
      return;
    }
    if (incx <= 0) {
      return;
    }
    if (alpha == 1.0f) {
      return;
    }
    sscalK(n, alpha, x, offsetx, incx);
  }

  protected abstract void sscalK(int n, float alpha, float[] x, int offsetx, int incx);

  public void dspmv(String uplo, int n, double alpha, double[] a, double[] x, int incx, double beta, double[] y, int incy) {
    dspmv(uplo, n, alpha, a, 0, x, 0, incx, beta, y, 0, incy);
  }

  // y = alpha * a * x + beta * y
  public void dspmv(String uplo, int n, double alpha, double[] a, int offseta, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    checkArgument("DSPMV", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DSPMV", 2, n >= 0);
    checkArgument("DSPMV", 6, incx != 0);
    checkArgument("DSPMV", 9, incy != 0);
    if (n == 0) {
      return;
    }
    dspmvK(uplo, n, alpha, a, offseta, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected abstract void dspmvK(String uplo, int n, double alpha, double[] a, int offseta, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);

  public void sspmv(String uplo, int n, float alpha, float[] a, float[] x, int incx, float beta, float[] y, int incy) {
    sspmv(uplo, n, alpha, a, 0, x, 0, incx, beta, y, 0, incy);
  }

  public void sspmv(String uplo, int n, float alpha, float[] a, int offseta, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    checkArgument("SSPMV", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("SSPMV", 2, n >= 0);
    checkArgument("SSPMV", 6, incx != 0);
    checkArgument("SSPMV", 9, incy != 0);
    if (n == 0) {
      return;
    }
    sspmvK(uplo, n, alpha, a, offseta, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected abstract void sspmvK(String uplo, int n, float alpha, float[] a, int offseta, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);

  public void dspr(String uplo, int n, double alpha, double[] x, int incx, double[] a) {
    dspr(uplo, n, alpha, x, 0, incx, a, 0);
  }

  // a += alpha * x * x.t
  public void dspr(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta) {
    checkArgument("DSPR", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DSPR", 2, n >= 0);
    checkArgument("DSPR", 5, incx != 0);
    if (n == 0) {
      return;
    }
    dsprK(uplo, n, alpha, x, offsetx, incx, a, offseta);
  }

  protected abstract void dsprK(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta);

  public void sspr(String uplo, int n, float alpha, float[] x, int incx, float[] a) {
    sspr(uplo, n, alpha, x, 0, incx, a, 0);
  }

  // a += alpha * x * x.t
  public void sspr(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta) {
    checkArgument("SSPR", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("SSPR", 2, n >= 0);
    checkArgument("SSPR", 5, incx != 0);
    if (n == 0) {
      return;
    }
    ssprK(uplo, n, alpha, x, offsetx, incx, a, offseta);
  }

  protected abstract void ssprK(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta);

  public void dspr2(String uplo, int n, double alpha, double[] x, int incx, double[] y, int incy, double[] a) {
    dspr2(uplo, n, alpha, x, 0, incx, y, 0, incy, a, 0);
  }

  // a += alpha * x * y.t + alpha * y * x.t
  public void dspr2(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta) {
    checkArgument("DSPR2", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DSPR2", 2, n >= 0);
    checkArgument("DSPR2", 5, incx != 0);
    checkArgument("DSPR2", 7, incy != 0);
    if (n == 0) {
      return;
    }
    dspr2K(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta);
  }
  
  protected abstract void dspr2K(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta);

  public void sspr2(String uplo, int n, float alpha, float[] x, int incx, float[] y, int incy, float[] a) {
    sspr2(uplo, n, alpha, x, 0, incx, y, 0, incy, a, 0);
  }

  // a += alpha * x * y.t + alpha * y * x.t
  public void sspr2(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta) {
    checkArgument("SSPR2", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("SSPR2", 2, n >= 0);
    checkArgument("SSPR2", 5, incx != 0);
    checkArgument("SSPR2", 7, incy != 0);
    if (n == 0) {
      return;
    }
    sspr2K(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta);
  }
  
  protected abstract void sspr2K(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta);

  public void dswap(int n, double[] x, int incx, double[] y, int incy) {
    dswap(n, x, 0, incx, y, 0, incy);
  }

  public void dswap(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (n <= 0) {
      return;
    }
    dswapK(n, x, offsetx, incx, y, offsety, incy);
  }

  protected abstract void dswapK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy);

  public void sswap(int n, float[] x, int incx, float[] y, int incy) {
    sswap(n, x, 0, incx, y, 0, incy);
  }

  public void sswap(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (n <= 0) {
      return;
    }
    sswapK(n, x, offsetx, incx, y, offsety, incy);
  }

  protected abstract void sswapK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy);

  public void dsymm(String side, String uplo, int m, int n, double alpha, double[] a, int lda, double[] b, int ldb, double beta, double[] c, int ldc) {
    dsymm(side, uplo, m, n, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
  }

  public void dsymm(String side, String uplo, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    checkArgument("DSYMM", 1, lsame("L", side) || lsame("R", side));
    checkArgument("DSYMM", 2, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DSYMM", 3, m >= 0);
    checkArgument("DSYMM", 4, n >= 0);
    checkArgument("DSYMM", 7, lda >= Math.max(1, lsame("L", side) ? m : n));
    checkArgument("DSYMM", 9, ldb >= Math.max(1, m));
    checkArgument("DSYMM", 12, ldc >= Math.max(1, m));
    if (m == 0 || n == 0 || (alpha == 0.0 && beta == 1.0)) {
      return;
    }
    dsymmK(side, uplo, m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected abstract void dsymmK(String side, String uplo, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc);

  public void ssymm(String side, String uplo, int m, int n, float alpha, float[] a, int lda, float[] b, int ldb, float beta, float[] c, int ldc) {
    ssymm(side, uplo, m, n, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
  }

  public void ssymm(String side, String uplo, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    checkArgument("SSYMM", 1, lsame("L", side) || lsame("R", side));
    checkArgument("SSYMM", 2, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("SSYMM", 3, m >= 0);
    checkArgument("SSYMM", 4, n >= 0);
    checkArgument("SSYMM", 7, lda >= Math.max(1, lsame("L", side) ? m : n));
    checkArgument("SSYMM", 9, ldb >= Math.max(1, m));
    checkArgument("SSYMM", 12, ldc >= Math.max(1, m));
    if (m == 0 || n == 0 || (alpha == 0.0f && beta == 1.0f)) {
      return;
    }
    ssymmK(side, uplo, m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected abstract void ssymmK(String side, String uplo, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc);

  public void dsymv(String uplo, int n, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    dsymv(uplo, n, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void dsymv(String uplo, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    checkArgument("DSYMV", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DSYMV", 2, n >= 0);
    checkArgument("DSYMV", 5, lda >= Math.max(1, n));
    checkArgument("DSYMV", 7, incx != 0);
    checkArgument("DSYMV", 10, incy != 0);
    if (n == 0) {
      return;
    }
    dsymvK(uplo, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected abstract void dsymvK(String uplo, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);

  public void ssymv(String uplo, int n, float alpha, float[] a, int lda, float[] x, int incx, float beta, float[] y, int incy) {
    ssymv(uplo, n, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void ssymv(String uplo, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    checkArgument("SSYMV", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("SSYMV", 2, n >= 0);
    checkArgument("SSYMV", 5, lda >= Math.max(1, n));
    checkArgument("SSYMV", 7, incx != 0);
    checkArgument("SSYMV", 10, incy != 0);
    if (n == 0) {
      return;
    }
    ssymvK(uplo, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected abstract void ssymvK(String uplo, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);

  public void dsyr(String uplo, int n, double alpha, double[] x, int incx, double[] a, int lda) {
    dsyr(uplo, n, alpha, x, 0, incx, a, 0, lda);
  }

  // a += alpha * x * x.t
  public void dsyr(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta, int lda) {
    checkArgument("DSYR", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DSYR", 2, n >= 0);
    checkArgument("DSYR", 5, incx != 0);
    checkArgument("DSYR", 7, lda >= Math.max(1, n));
    if (n == 0) {
      return;
    }
    dsyrK(uplo, n, alpha, x, offsetx, incx, a, offseta, lda);
  }

  protected abstract void dsyrK(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta, int lda);

  public void ssyr(String uplo, int n, float alpha, float[] x, int incx, float[] a, int lda) {
    ssyr(uplo, n, alpha, x, 0, incx, a, 0, lda);
  }

  public void ssyr(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta, int lda) {
    checkArgument("SSYR", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("SSYR", 2, n >= 0);
    checkArgument("SSYR", 5, incx != 0);
    checkArgument("SSYR", 7, lda >= Math.max(1, n));
    if (n == 0) {
      return;
    }
    ssyrK(uplo, n, alpha, x, offsetx, incx, a, offseta, lda);
  }

  protected abstract void ssyrK(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta, int lda);

  public void dsyr2(String uplo, int n, double alpha, double[] x, int incx, double[] y, int incy, double[] a, int lda) {
    dsyr2(uplo, n, alpha, x, 0, incx, y, 0, incy, a, 0, lda);
  }

  public void dsyr2(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
    checkArgument("DSYR2", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DSYR2", 2, n >= 0);
    checkArgument("DSYR2", 5, incx != 0);
    checkArgument("DSYR2", 7, incy != 0);
    checkArgument("DSYR2", 9, lda >= Math.max(1, n));
    if (n == 0) {
      return;
    }
    dsyr2K(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
  }

  protected abstract void dsyr2K(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda);

  public void ssyr2(String uplo, int n, float alpha, float[] x, int incx, float[] y, int incy, float[] a, int lda) {
    ssyr2(uplo, n, alpha, x, 0, incx, y, 0, incy, a, 0, lda);
  }

  public void ssyr2(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
    checkArgument("SSYR2", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("SSYR2", 2, n >= 0);
    checkArgument("SSYR2", 5, incx != 0);
    checkArgument("SSYR2", 7, incy != 0);
    checkArgument("SSYR2", 9, lda >= Math.max(1, n));
    if (n == 0) {
      return;
    }
    ssyr2K(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
  }

  protected abstract void ssyr2K(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda);

  public void dsyr2k(String uplo, String trans, int n, int k, double alpha, double[] a, int lda, double[] b, int ldb, double beta, double[] c, int ldc) {
    dsyr2k(uplo, trans, n, k, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
  }

  public void dsyr2k(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    //FIXME: add arguments checks
    dsyr2kK(uplo, trans, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected abstract void dsyr2kK(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc);

  public void ssyr2k(String uplo, String trans, int n, int k, float alpha, float[] a, int lda, float[] b, int ldb, float beta, float[] c, int ldc) {
    ssyr2k(uplo, trans, n, k, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
  }

  public void ssyr2k(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    //FIXME: add arguments checks
    ssyr2kK(uplo, trans, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected abstract void ssyr2kK(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc);

  public void dsyrk(String uplo, String trans, int n, int k, double alpha, double[] a, int lda, double beta, double[] c, int ldc) {
    dsyrk(uplo, trans, n, k, alpha, a, 0, lda, beta, c, 0, ldc);
  }

  public void dsyrk(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double beta, double[] c, int offsetc, int ldc) {
    //FIXME: add arguments checks
    dsyrkK(uplo, trans, n, k, alpha, a, offseta, lda, beta, c, offsetc, ldc);
  }

  protected abstract void dsyrkK(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double beta, double[] c, int offsetc, int ldc);

  public void ssyrk(String uplo, String trans, int n, int k, float alpha, float[] a, int lda, float beta, float[] c, int ldc) {
    ssyrk(uplo, trans, n, k, alpha, a, 0, lda, beta, c, 0, ldc);
  }

  public void ssyrk(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float beta, float[] c, int offsetc, int ldc) {
    //FIXME: add arguments checks
    ssyrkK(uplo, trans, n, k, alpha, a, offseta, lda, beta, c, offsetc, ldc);
  }

  protected abstract void ssyrkK(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float beta, float[] c, int offsetc, int ldc);

  public void dtbmv(String uplo, String trans, String diag, int n, int k, double[] a, int lda, double[] x, int incx) {
    dtbmv(uplo, trans, diag, n, k, a, 0, lda, x, 0, incx);
  }

  public void dtbmv(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    //FIXME: add arguments checks
    dtbmvK(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }

  protected abstract void dtbmvK(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx);

  public void stbmv(String uplo, String trans, String diag, int n, int k, float[] a, int lda, float[] x, int incx) {
    stbmv(uplo, trans, diag, n, k, a, 0, lda, x, 0, incx);
  }

  public void stbmv(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    //FIXME: add arguments checks
    stbmvK(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }

  protected abstract void stbmvK(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx);

  public void dtbsv(String uplo, String trans, String diag, int n, int k, double[] a, int lda, double[] x, int incx) {
    dtbsv(uplo, trans, diag, n, k, a, 0, lda, x, 0, incx);
  }

  public void dtbsv(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    //FIXME: add arguments checks
    dtbsvK(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }

  protected abstract void dtbsvK(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx);

  public void stbsv(String uplo, String trans, String diag, int n, int k, float[] a, int lda, float[] x, int incx) {
    stbsv(uplo, trans, diag, n, k, a, 0, lda, x, 0, incx);
  }

  public void stbsv(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    //FIXME: add arguments checks
    stbsvK(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }

  protected abstract void stbsvK(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx);

  public void dtpmv(String uplo, String trans, String diag, int n, double[] a, double[] x, int incx) {
    dtpmv(uplo, trans, diag, n, a, 0, x, 0, incx);
  }

  public void dtpmv(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx) {
    //FIXME: add arguments checks
    dtpmvK(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
  }

  protected abstract void dtpmvK(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx);

  public void stpmv(String uplo, String trans, String diag, int n, float[] a, float[] x, int incx) {
    stpmv(uplo, trans, diag, n, a, 0, x, 0, incx);
  }

  public void stpmv(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx) {
    //FIXME: add arguments checks
    stpmvK(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
  }

  protected abstract void stpmvK(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx);

  public void dtpsv(String uplo, String trans, String diag, int n, double[] a, double[] x, int incx) {
    dtpsv(uplo, trans, diag, n, a, 0, x, 0, incx);
  }

  public void dtpsv(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx) {
    //FIXME: add arguments checks
    dtpsvK(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
  }

  protected abstract void dtpsvK(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx);

  public void stpsv(String uplo, String trans, String diag, int n, float[] a, float[] x, int incx) {
    stpsv(uplo, trans, diag, n, a, 0, x, 0, incx);
  }

  public void stpsv(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx) {
    //FIXME: add arguments checks
    stpsvK(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
  }

  protected abstract void stpsvK(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx);

  public void dtrmm(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int lda, double[] b, int ldb) {
    dtrmm(side, uplo, transa, diag, m, n, alpha, a, 0, lda, b, 0, ldb);
  }

  public void dtrmm(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
    //FIXME: add arguments checks
    dtrmmK(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }

  protected abstract void dtrmmK(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb);

  public void strmm(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int lda, float[] b, int ldb) {
    strmm(side, uplo, transa, diag, m, n, alpha, a, 0, lda, b, 0, ldb);
  }

  public void strmm(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
    //FIXME: add arguments checks
    strmmK(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }

  protected abstract void strmmK(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb);

  public void dtrmv(String uplo, String trans, String diag, int n, double[] a, int lda, double[] x, int incx) {
    dtrmv(uplo, trans, diag, n, a, 0, lda, x, 0, incx);
  }

  public void dtrmv(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    //FIXME: add arguments checks
    dtrmvK(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }

  protected abstract void dtrmvK(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx);

  public void strmv(String uplo, String trans, String diag, int n, float[] a, int lda, float[] x, int incx) {
    strmv(uplo, trans, diag, n, a, 0, lda, x, 0, incx);
  }

  public void strmv(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    //FIXME: add arguments checks
    strmvK(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }

  protected abstract void strmvK(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx);

  public void dtrsm(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int lda, double[] b, int ldb) {
    dtrsm(side, uplo, transa, diag, m, n, alpha, a, 0, lda, b, 0, ldb);
  }

  public void dtrsm(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
    //FIXME: add arguments checks
    dtrsmK(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }

  protected abstract void dtrsmK(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb);

  public void strsm(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int lda, float[] b, int ldb) {
    strsm(side, uplo, transa, diag, m, n, alpha, a, 0, lda, b, 0, ldb);
  }

  public void strsm(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
    //FIXME: add arguments checks
    strsmK(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }

  protected abstract void strsmK(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb);

  public void dtrsv(String uplo, String trans, String diag, int n, double[] a, int lda, double[] x, int incx) {
    dtrsv(uplo, trans, diag, n, a, 0, lda, x, 0, incx);
  }

  public void dtrsv(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    //FIXME: add arguments checks
    dtrsvK(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }

  protected abstract void dtrsvK(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx);

  public void strsv(String uplo, String trans, String diag, int n, float[] a, int lda, float[] x, int incx) {
    strsv(uplo, trans, diag, n, a, 0, lda, x, 0, incx);
  }

  public void strsv(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    //FIXME: add arguments checks
    strsvK(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }

  protected abstract void strsvK(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx);

  public int idamax(int n, double[] x, int incx) {
    return idamax(n, x, 0, incx);
  }

  public int idamax(int n, double[] x, int offsetx, int incx) {
    if (n <= 0) {
      return -1;
    }
    if (incx <= 0) {
      return -1;
    }
    if (n == 1) {
      return 0;
    }
    // Fortran arrays use 1-based index
    return idamaxK(n, x, offsetx, incx) - 1;
  }

  protected abstract int idamaxK(int n, double[] x, int offsetx, int incx);

  public int isamax(int n, float[] x, int incx) {
    return isamax(n, x, 0, incx);
  }

  public int isamax(int n, float[] x, int offsetx, int incx) {
    if (n <= 0) {
      return -1;
    }
    if (incx <= 0) {
      return -1;
    }
    if (n == 1) {
      return 0;
    }
    // Fortran arrays use 1-based index
    return isamaxK(n, x, offsetx, incx) - 1;
  }

  protected abstract int isamaxK(int n, float[] x, int offsetx, int incx);

  public boolean lsame(String ca, String cb) {
    return ca != null && ca.length() == 1 && ca.equalsIgnoreCase(cb);
  }
}
