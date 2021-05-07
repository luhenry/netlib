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

abstract class AbstractBLAS implements BLAS {

  private final static boolean debug = System.getProperty("dev.ludovic.netlib.blas.debug", "false").equals("true");

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

  private <T> void requireNonNull(T obj) {
    Objects.requireNonNull(obj);
  }

  public double dasum(int n, double[] x, int incx) {
    if (debug) System.err.println("dasum");
    return dasum(n, x, 0, incx);
  }

  public double dasum(int n, double[] x, int offsetx, int incx) {
    if (debug) System.err.println("dasum");
    if (n <= 0) {
      return 0.0;
    }
    requireNonNull(x);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    return dasumK(n, x, offsetx, incx);
  }

  protected abstract double dasumK(int n, double[] x, int offsetx, int incx);

  public float sasum(int n, float[] x, int incx) {
    if (debug) System.err.println("sasum");
    return sasum(n, x, 0, incx);
  }

  public float sasum(int n, float[] x, int offsetx, int incx) {
    if (debug) System.err.println("sasum");
    if (n <= 0) {
      return 0.0f;
    }
    requireNonNull(x);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    return sasumK(n, x, offsetx, incx);
  }

  protected abstract float sasumK(int n, float[] x, int offsetx, int incx);

  public void daxpy(int n, double alpha, double[] x, int incx, double[] y, int incy) {
    if (debug) System.err.println("daxpy");
    daxpy(n, alpha, x, 0, incx, y, 0, incy);
  }

  // y += alpha * x
  public void daxpy(int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (debug) System.err.println("daxpy");
    if (n <= 0) {
      return;
    }
    if (alpha == 0.0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    daxpyK(n, alpha, x, offsetx, incx, y, offsety, incy);
  }

  protected abstract void daxpyK(int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy);

  public void saxpy(int n, float alpha, float[] x, int incx, float[] y, int incy) {
    if (debug) System.err.println("saxpy");
    saxpy(n, alpha, x, 0, incx, y, 0, incy);
  }

  // y += alpha * x
  public void saxpy(int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (debug) System.err.println("saxpy");
    if (n <= 0) {
      return;
    }
    if (alpha == 0.0f) {
      return;
    }
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    saxpyK(n, alpha, x, offsetx, incx, y, offsety, incy);
  }

  protected abstract void saxpyK(int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy);

  public void dcopy(int n, double[] x, int incx, double[] y, int incy) {
    if (debug) System.err.println("dcopy");
    dcopy(n, x, 0, incx, y, 0, incy);
  }

  public void dcopy(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (debug) System.err.println("dcopy");
    if (n <= 0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    dcopyK(n, x, offsetx, incx, y, offsety, incy);
  }

  protected abstract void dcopyK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy);

  public void scopy(int n, float[] x, int incx, float[] y, int incy) {
    if (debug) System.err.println("scopy");
    scopy(n, x, 0, incx, y, 0, incy);
  }

  public void scopy(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (debug) System.err.println("scopy");
    if (n <= 0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    scopyK(n, x, offsetx, incx, y, offsety, incy);
  }

  protected abstract void scopyK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy);

  public double ddot(int n, double[] x, int incx, double[] y, int incy) {
    if (debug) System.err.println("ddot");
    return ddot(n, x, 0, incx, y, 0, incy);
  }

  // sum(x * y)
  public double ddot(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (debug) System.err.println("ddot");
    if (n <= 0) {
      return 0.0;
    }
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    return ddotK(n, x, offsetx, incx, y, offsety, incy);
  }

  protected abstract double ddotK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy);

  public float sdot(int n, float[] x, int incx, float[] y, int incy) {
    if (debug) System.err.println("sdot");
    return sdot(n, x, 0, incx, y, 0, incy);
  }

  // sum(x * y)
  public float sdot(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (debug) System.err.println("sdot");
    if (n <= 0) {
      return 0.0f;
    }
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    return sdotK(n, x, offsetx, incx, y, offsety, incy);
  }

  protected abstract float sdotK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy);

  public float sdsdot(int n, float sb, float[] x, int incx, float[] y, int incy) {
    if (debug) System.err.println("sdsdot");
    return sdsdot(n, sb, x, 0, incx, y, 0, incy);
  }

  public float sdsdot(int n, float sb, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (debug) System.err.println("sdsdot");
    if (n <= 0) {
      return 0.0f;
    }
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    return sdsdotK(n, sb, x, offsetx, incx, y, offsety, incy);
  }

  protected abstract float sdsdotK(int n, float sb, float[] x, int offsetx, int incx, float[] y, int offsety, int incy);

  public void dgbmv(String trans, int m, int n, int kl, int ku, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    if (debug) System.err.println("dgbmv");
    dgbmv(trans, m, n, kl, ku, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void dgbmv(String trans, int m, int n, int kl, int ku, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    if (debug) System.err.println("dgbmv");
    requireNonNull(a);
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offseta + n * lda - 1, a.length);
    checkIndex(offsetx + ((lsame("N", trans) ? n : m) - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + ((lsame("N", trans) ? m : n) - 1) * Math.abs(incy), y.length);
    dgbmvK(trans, m, n, kl, ku, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected abstract void dgbmvK(String trans, int m, int n, int kl, int ku, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);

  public void sgbmv(String trans, int m, int n, int kl, int ku, float alpha, float[] a, int lda, float[] x, int incx, float beta, float[] y, int incy) {
    if (debug) System.err.println("sgbmv");
    sgbmv(trans, m, n, kl, ku, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void sgbmv(String trans, int m, int n, int kl, int ku, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    if (debug) System.err.println("sgbmv");
    requireNonNull(a);
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offseta + n * lda - 1, a.length);
    checkIndex(offsetx + ((lsame("N", trans) ? n : m) - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + ((lsame("N", trans) ? m : n) - 1) * Math.abs(incy), y.length);
    sgbmvK(trans, m, n, kl, ku, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected abstract void sgbmvK(String trans, int m, int n, int kl, int ku, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);

  public void dgemm(String transa, String transb, int m, int n, int k, double alpha, double[] a, int lda, double[] b, int ldb, double beta, double[] c, int ldc) {
    if (debug) System.err.println("dgemm");
    dgemm(transa, transb, m, n, k, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
  }

  // c = alpha * a * b + beta * c
  public void dgemm(String transa, String transb, int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    if (debug) System.err.println("dgemm");
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
    requireNonNull(a);
    requireNonNull(b);
    requireNonNull(c);
    checkIndex(offseta + (lsame("N", transa) ? k : m) * lda - 1, a.length);
    checkIndex(offsetb + (lsame("N", transb) ? n : k) * ldb - 1, b.length);
    checkIndex(offsetc + m * n - 1, c.length);
    dgemmK(transa, transb, m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected abstract void dgemmK(String transa, String transb, int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc);

  public void sgemm(String transa, String transb, int m, int n, int k, float alpha, float[] a, int lda, float[] b, int ldb, float beta, float[] c, int ldc) {
    if (debug) System.err.println("sgemm");
    sgemm(transa, transb, m, n, k, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
  }

  // c = alpha * a * b + beta * c
  public void sgemm(String transa, String transb, int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    if (debug) System.err.println("sgemm");
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
    requireNonNull(a);
    requireNonNull(b);
    requireNonNull(c);
    checkIndex(offseta + (lsame("N", transa) ? k : m) * lda - 1, a.length);
    checkIndex(offsetb + (lsame("N", transb) ? n : k) * ldb - 1, b.length);
    checkIndex(offsetc + m * n - 1, c.length);
    sgemmK(transa, transb, m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected abstract void sgemmK(String transa, String transb, int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc);

  public void dgemv(String trans, int m, int n, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    if (debug) System.err.println("dgemv");
    dgemv(trans, m, n, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  // y = alpha * A * x + beta * y
  public void dgemv(String trans, int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    if (debug) System.err.println("dgemv");
    checkArgument("DGEMV", 1, lsame("N", trans) || lsame("T", trans) || lsame("C", trans));
    checkArgument("DGEMV", 2, m >= 0);
    checkArgument("DGEMV", 3, n >= 0);
    checkArgument("DGEMV", 6, lda >= Math.max(1, m));
    checkArgument("DGEMV", 8, incx != 0);
    checkArgument("DGEMV", 11, incy != 0);
    if (m == 0 || n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offseta + n * lda - 1, a.length);
    checkIndex(offsetx + ((lsame("N", trans) ? n : m) - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + ((lsame("N", trans) ? m : n) - 1) * Math.abs(incy), y.length);
    dgemvK(trans, m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected abstract void dgemvK(String trans, int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);

  public void sgemv(String trans, int m, int n, float alpha, float[] a, int lda, float[] x, int incx, float beta, float[] y, int incy) {
    if (debug) System.err.println("sgemv");
    sgemv(trans, m, n, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  // y = alpha * A * x + beta * y
  public void sgemv(String trans, int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    if (debug) System.err.println("sgemv");
    checkArgument("SGEMV", 1, lsame("N", trans) || lsame("T", trans) || lsame("C", trans));
    checkArgument("SGEMV", 2, m >= 0);
    checkArgument("SGEMV", 3, n >= 0);
    checkArgument("SGEMV", 6, lda >= Math.max(1, m));
    checkArgument("SGEMV", 8, incx != 0);
    checkArgument("SGEMV", 11, incy != 0);
    if (m == 0 || n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offseta + n * lda - 1, a.length);
    checkIndex(offsetx + ((lsame("N", trans) ? n : m) - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + ((lsame("N", trans) ? m : n) - 1) * Math.abs(incy), y.length);
    sgemvK(trans, m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected abstract void sgemvK(String trans, int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);

  // A += alpha * x * y.t
  public void dger(int m, int n, double alpha, double[] x, int incx, double[] y, int incy, double[] a, int lda) {
    if (debug) System.err.println("dger");
    dger(m, n, alpha, x, 0, incx, y, 0, incy, a, 0, lda);
  }

  public void dger(int m, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
    if (debug) System.err.println("dger");
    checkArgument("DGER", 1, m >= 0);
    checkArgument("DGER", 2, n >= 0);
    checkArgument("DGER", 5, incx != 0);
    checkArgument("DGER", 7, incy != 0);
    checkArgument("DGER", 9, lda >= Math.max(1, m));
    if (m == 0 || n == 0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(y);
    requireNonNull(a);
    checkIndex(offsetx + (m - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    checkIndex(offseta + n * lda - 1, a.length);
    if (alpha != 0.0) {
      dgerK(m, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
    }
  }

  protected abstract void dgerK(int m, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda);

  public void sger(int m, int n, float alpha, float[] x, int incx, float[] y, int incy, float[] a, int lda) {
    if (debug) System.err.println("sger");
    sger(m, n, alpha, x, 0, incx, y, 0, incy, a, 0, lda);
  }

  public void sger(int m, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
    if (debug) System.err.println("sger");
    checkArgument("SGER", 1, m >= 0);
    checkArgument("SGER", 2, n >= 0);
    checkArgument("SGER", 5, incx != 0);
    checkArgument("SGER", 7, incy != 0);
    checkArgument("SGER", 9, lda >= Math.max(1, m));
    if (m == 0 || n == 0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(y);
    requireNonNull(a);
    checkIndex(offsetx + (m - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    checkIndex(offseta + n * lda - 1, a.length);
    if (alpha != 0.0f) {
      sgerK(m, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
    }
  }

  protected abstract void sgerK(int m, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda);

  public double dnrm2(int n, double[] x, int incx) {
    if (debug) System.err.println("dnrm2");
    return dnrm2(n, x, 0, incx);
  }

  public double dnrm2(int n, double[] x, int offsetx, int incx) {
    if (debug) System.err.println("dnrm2");
    if (n <= 0) {
      return 0.0;
    }
    if (incx <= 0) {
      return 0.0;
    }
    if (n == 1) {
      return Math.abs(x[offsetx + 0]);
    }
    requireNonNull(x);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    return dnrm2K(n, x, offsetx, incx);
  }

  protected abstract double dnrm2K(int n, double[] x, int offsetx, int incx);

  public float snrm2(int n, float[] x, int incx) {
    if (debug) System.err.println("snrm2");
    return snrm2(n, x, 0, incx);
  }

  public float snrm2(int n, float[] x, int offsetx, int incx) {
    if (debug) System.err.println("snrm2");
    if (n <= 0) {
      return 0.0f;
    }
    if (incx <= 0) {
      return 0.0f;
    }
    if (n == 1) {
      return Math.abs(x[offsetx + 0]);
    }
    requireNonNull(x);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    return snrm2K(n, x, offsetx, incx);
  }

  protected abstract float snrm2K(int n, float[] x, int offsetx, int incx);

  public void drot(int n, double[] x, int incx, double[] y, int incy, double c, double s) {
    if (debug) System.err.println("drot");
    drot(n, x, 0, incx, y, 0, incy, c, s);
  }

  public void drot(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double c, double s) {
    if (debug) System.err.println("drot");
    if (n <= 0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    drotK(n, x, offsetx, incx, y, offsety, incy, c, s);
  }

  protected abstract void drotK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double c, double s);

  public void srot(int n, float[] x, int incx, float[] y, int incy, float c, float s) {
    if (debug) System.err.println("srot");
    srot(n, x, 0, incx, y, 0, incy, c, s);
  }

  public void srot(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float c, float s) {
    if (debug) System.err.println("srot");
    if (n <= 0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    srotK(n, x, offsetx, incx, y, offsety, incy, c, s);
  }

  protected abstract void srotK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float c, float s);

  public void drotg(org.netlib.util.doubleW da, org.netlib.util.doubleW db, org.netlib.util.doubleW c, org.netlib.util.doubleW s) {
    if (debug) System.err.println("drotg");
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
    if (debug) System.err.println("srotg");
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
    if (debug) System.err.println("drotm");
    drotm(n, x, 0, incx, y, 0, incy, param, 0);
  }

  public void drotm(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] param, int offsetparam) {
    if (debug) System.err.println("drotm");
    requireNonNull(x);
    requireNonNull(y);
    requireNonNull(param);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    checkIndex(offsetparam + 4, param.length); /* param.length == 5 */
    drotmK(n, x, offsetx, incx, y, offsety, incy, param, offsetparam);
  }

  protected abstract void drotmK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] param, int offsetparam);

  public void srotm(int n, float[] x, int incx, float[] y, int incy, float[] param) {
    if (debug) System.err.println("srotm");
    srotm(n, x, 0, incx, y, 0, incy, param, 0);
  }

  public void srotm(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] param, int offsetparam) {
    if (debug) System.err.println("srotm");
    requireNonNull(x);
    requireNonNull(y);
    requireNonNull(param);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    checkIndex(offsetparam + 4, param.length); /* param.length == 5 */
    srotmK(n, x, offsetx, incx, y, offsety, incy, param, offsetparam);
  }

  protected abstract void srotmK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] param, int offsetparam);

  public void drotmg(org.netlib.util.doubleW dd1, org.netlib.util.doubleW dd2, org.netlib.util.doubleW dx1, double dy1, double[] param) {
    if (debug) System.err.println("drotmg");
    drotmg(dd1, dd2, dx1, dy1, param, 0);
  }

  public void drotmg(org.netlib.util.doubleW dd1, org.netlib.util.doubleW dd2, org.netlib.util.doubleW dx1, double dy1, double[] param, int offsetparam) {
    if (debug) System.err.println("drotmg");
    requireNonNull(dd1);
    requireNonNull(dd2);
    requireNonNull(dx1);
    requireNonNull(param);
    checkIndex(offsetparam + 4, param.length);
    drotmgK(dd1, dd2, dx1, dy1, param, offsetparam);
  }

  protected abstract void drotmgK(org.netlib.util.doubleW dd1, org.netlib.util.doubleW dd2, org.netlib.util.doubleW dx1, double dy1, double[] param, int offsetparam);

  public void srotmg(org.netlib.util.floatW sd1, org.netlib.util.floatW sd2, org.netlib.util.floatW sx1, float sy1, float[] param) {
    if (debug) System.err.println("srotmg");
    srotmg(sd1, sd2, sx1, sy1, param, 0);
  }

  public void srotmg(org.netlib.util.floatW sd1, org.netlib.util.floatW sd2, org.netlib.util.floatW sx1, float sy1, float[] param, int offsetparam) {
    if (debug) System.err.println("srotmg");
    requireNonNull(sd1);
    requireNonNull(sd2);
    requireNonNull(sx1);
    requireNonNull(param);
    checkIndex(offsetparam + 4, param.length);
    srotmgK(sd1, sd2, sx1, sy1, param, offsetparam);
  }

  protected abstract void srotmgK(org.netlib.util.floatW sd1, org.netlib.util.floatW sd2, org.netlib.util.floatW sx1, float sy1, float[] param, int offsetparam);

  public void dsbmv(String uplo, int n, int k, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    if (debug) System.err.println("dsbmv");
    dsbmv(uplo, n, k, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void dsbmv(String uplo, int n, int k, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    if (debug) System.err.println("dsbmv");
    requireNonNull(a);
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offseta + n * lda - 1, a.length);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    dsbmvK(uplo, n, k, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected abstract void dsbmvK(String uplo, int n, int k, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);

  public void ssbmv(String uplo, int n, int k, float alpha, float[] a, int lda, float[] x, int incx, float beta, float[] y, int incy) {
    if (debug) System.err.println("ssbmv");
    ssbmv(uplo, n, k, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void ssbmv(String uplo, int n, int k, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    if (debug) System.err.println("ssbmv");
    requireNonNull(a);
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offseta + n * lda - 1, a.length);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    ssbmvK(uplo, n, k, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected abstract void ssbmvK(String uplo, int n, int k, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);

  public void dscal(int n, double alpha, double[] x, int incx) {
    if (debug) System.err.println("dscal");
    dscal(n, alpha, x, 0, incx);
  }

  // x = alpha * x
  public void dscal(int n, double alpha, double[] x, int offsetx, int incx) {
    if (debug) System.err.println("dscal");
    if (n <= 0) {
      return;
    }
    if (incx <= 0) {
      return;
    }
    if (alpha == 1.0) {
      return;
    }
    requireNonNull(x);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    dscalK(n, alpha, x, offsetx, incx);
  }

  protected abstract void dscalK(int n, double alpha, double[] x, int offsetx, int incx);

  public void sscal(int n, float alpha, float[] x, int incx) {
    if (debug) System.err.println("sscal");
    sscal(n, alpha, x, 0, incx);
  }

  // x = alpha * x
  public void sscal(int n, float alpha, float[] x, int offsetx, int incx) {
    if (debug) System.err.println("sscal");
    if (n <= 0) {
      return;
    }
    if (incx <= 0) {
      return;
    }
    if (alpha == 1.0f) {
      return;
    }
    requireNonNull(x);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    sscalK(n, alpha, x, offsetx, incx);
  }

  protected abstract void sscalK(int n, float alpha, float[] x, int offsetx, int incx);

  public void dspmv(String uplo, int n, double alpha, double[] a, double[] x, int incx, double beta, double[] y, int incy) {
    if (debug) System.err.println("dspmv");
    dspmv(uplo, n, alpha, a, 0, x, 0, incx, beta, y, 0, incy);
  }

  // y = alpha * a * x + beta * y
  public void dspmv(String uplo, int n, double alpha, double[] a, int offseta, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    if (debug) System.err.println("dspmv");
    checkArgument("DSPMV", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DSPMV", 2, n >= 0);
    checkArgument("DSPMV", 6, incx != 0);
    checkArgument("DSPMV", 9, incy != 0);
    if (n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offseta + (n * (n + 1) / 2) - 1, a.length);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    dspmvK(uplo, n, alpha, a, offseta, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected abstract void dspmvK(String uplo, int n, double alpha, double[] a, int offseta, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);

  public void sspmv(String uplo, int n, float alpha, float[] a, float[] x, int incx, float beta, float[] y, int incy) {
    if (debug) System.err.println("sspmv");
    sspmv(uplo, n, alpha, a, 0, x, 0, incx, beta, y, 0, incy);
  }

  public void sspmv(String uplo, int n, float alpha, float[] a, int offseta, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    if (debug) System.err.println("sspmv");
    checkArgument("SSPMV", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("SSPMV", 2, n >= 0);
    checkArgument("SSPMV", 6, incx != 0);
    checkArgument("SSPMV", 9, incy != 0);
    if (n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offseta + (n * (n + 1) / 2) - 1, a.length);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    sspmvK(uplo, n, alpha, a, offseta, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected abstract void sspmvK(String uplo, int n, float alpha, float[] a, int offseta, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);

  public void dspr(String uplo, int n, double alpha, double[] x, int incx, double[] a) {
    if (debug) System.err.println("dspr");
    dspr(uplo, n, alpha, x, 0, incx, a, 0);
  }

  // a += alpha * x * x.t
  public void dspr(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta) {
    if (debug) System.err.println("dspr");
    checkArgument("DSPR", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DSPR", 2, n >= 0);
    checkArgument("DSPR", 5, incx != 0);
    if (n == 0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(a);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offseta + (n * (n + 1) / 2) - 1, a.length);
    dsprK(uplo, n, alpha, x, offsetx, incx, a, offseta);
  }

  protected abstract void dsprK(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta);

  public void sspr(String uplo, int n, float alpha, float[] x, int incx, float[] a) {
    if (debug) System.err.println("sspr");
    sspr(uplo, n, alpha, x, 0, incx, a, 0);
  }

  // a += alpha * x * x.t
  public void sspr(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta) {
    if (debug) System.err.println("sspr");
    checkArgument("SSPR", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("SSPR", 2, n >= 0);
    checkArgument("SSPR", 5, incx != 0);
    if (n == 0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(a);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offseta + (n * (n + 1) / 2) - 1, a.length);
    ssprK(uplo, n, alpha, x, offsetx, incx, a, offseta);
  }

  protected abstract void ssprK(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta);

  public void dspr2(String uplo, int n, double alpha, double[] x, int incx, double[] y, int incy, double[] a) {
    if (debug) System.err.println("dspr2");
    dspr2(uplo, n, alpha, x, 0, incx, y, 0, incy, a, 0);
  }

  // a += alpha * x * y.t + alpha * y * x.t
  public void dspr2(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta) {
    if (debug) System.err.println("dspr2");
    checkArgument("DSPR2", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DSPR2", 2, n >= 0);
    checkArgument("DSPR2", 5, incx != 0);
    checkArgument("DSPR2", 7, incy != 0);
    if (n == 0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(y);
    requireNonNull(a);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    checkIndex(offseta + (n * (n + 1) / 2) - 1, a.length);
    dspr2K(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta);
  }
  
  protected abstract void dspr2K(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta);

  public void sspr2(String uplo, int n, float alpha, float[] x, int incx, float[] y, int incy, float[] a) {
    if (debug) System.err.println("sspr2");
    sspr2(uplo, n, alpha, x, 0, incx, y, 0, incy, a, 0);
  }

  // a += alpha * x * y.t + alpha * y * x.t
  public void sspr2(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta) {
    if (debug) System.err.println("sspr2");
    checkArgument("SSPR2", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("SSPR2", 2, n >= 0);
    checkArgument("SSPR2", 5, incx != 0);
    checkArgument("SSPR2", 7, incy != 0);
    if (n == 0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(y);
    requireNonNull(a);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    checkIndex(offseta + (n * (n + 1) / 2) - 1, a.length);
    sspr2K(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta);
  }
  
  protected abstract void sspr2K(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta);

  public void dswap(int n, double[] x, int incx, double[] y, int incy) {
    if (debug) System.err.println("dswap");
    dswap(n, x, 0, incx, y, 0, incy);
  }

  public void dswap(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (debug) System.err.println("dswap");
    if (n <= 0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    dswapK(n, x, offsetx, incx, y, offsety, incy);
  }

  protected abstract void dswapK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy);

  public void sswap(int n, float[] x, int incx, float[] y, int incy) {
    if (debug) System.err.println("sswap");
    sswap(n, x, 0, incx, y, 0, incy);
  }

  public void sswap(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (debug) System.err.println("sswap");
    if (n <= 0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    sswapK(n, x, offsetx, incx, y, offsety, incy);
  }

  protected abstract void sswapK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy);

  public void dsymm(String side, String uplo, int m, int n, double alpha, double[] a, int lda, double[] b, int ldb, double beta, double[] c, int ldc) {
    if (debug) System.err.println("dsymm");
    dsymm(side, uplo, m, n, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
  }

  public void dsymm(String side, String uplo, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    if (debug) System.err.println("dsymm");
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
    requireNonNull(a);
    requireNonNull(b);
    requireNonNull(c);
    checkIndex(offseta + (lsame("L", side) ? m : n) * lda - 1, a.length);
    checkIndex(offsetb + n * ldb - 1, b.length);
    checkIndex(offsetc + n * ldc - 1, c.length);
    dsymmK(side, uplo, m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected abstract void dsymmK(String side, String uplo, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc);

  public void ssymm(String side, String uplo, int m, int n, float alpha, float[] a, int lda, float[] b, int ldb, float beta, float[] c, int ldc) {
    if (debug) System.err.println("ssymm");
    ssymm(side, uplo, m, n, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
  }

  public void ssymm(String side, String uplo, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    if (debug) System.err.println("ssymm");
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
    requireNonNull(a);
    requireNonNull(b);
    requireNonNull(c);
    checkIndex(offseta + (lsame("L", side) ? m : n) * lda - 1, a.length);
    checkIndex(offsetb + n * ldb - 1, b.length);
    checkIndex(offsetc + n * ldc - 1, c.length);
    ssymmK(side, uplo, m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected abstract void ssymmK(String side, String uplo, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc);

  public void dsymv(String uplo, int n, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    if (debug) System.err.println("dsymv");
    dsymv(uplo, n, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void dsymv(String uplo, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    if (debug) System.err.println("dsymv");
    checkArgument("DSYMV", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DSYMV", 2, n >= 0);
    checkArgument("DSYMV", 5, lda >= Math.max(1, n));
    checkArgument("DSYMV", 7, incx != 0);
    checkArgument("DSYMV", 10, incy != 0);
    if (n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offseta + n * lda - 1, a.length);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    dsymvK(uplo, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected abstract void dsymvK(String uplo, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);

  public void ssymv(String uplo, int n, float alpha, float[] a, int lda, float[] x, int incx, float beta, float[] y, int incy) {
    if (debug) System.err.println("ssymv");
    ssymv(uplo, n, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void ssymv(String uplo, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    if (debug) System.err.println("ssymv");
    checkArgument("SSYMV", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("SSYMV", 2, n >= 0);
    checkArgument("SSYMV", 5, lda >= Math.max(1, n));
    checkArgument("SSYMV", 7, incx != 0);
    checkArgument("SSYMV", 10, incy != 0);
    if (n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(x);
    requireNonNull(y);
    checkIndex(offseta + n * lda - 1, a.length);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    ssymvK(uplo, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected abstract void ssymvK(String uplo, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);

  public void dsyr(String uplo, int n, double alpha, double[] x, int incx, double[] a, int lda) {
    if (debug) System.err.println("dsyr");
    dsyr(uplo, n, alpha, x, 0, incx, a, 0, lda);
  }

  // a += alpha * x * x.t
  public void dsyr(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta, int lda) {
    if (debug) System.err.println("dsyr");
    checkArgument("DSYR", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DSYR", 2, n >= 0);
    checkArgument("DSYR", 5, incx != 0);
    checkArgument("DSYR", 7, lda >= Math.max(1, n));
    if (n == 0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(a);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offseta + n * lda - 1, a.length);
    dsyrK(uplo, n, alpha, x, offsetx, incx, a, offseta, lda);
  }

  protected abstract void dsyrK(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta, int lda);

  public void ssyr(String uplo, int n, float alpha, float[] x, int incx, float[] a, int lda) {
    if (debug) System.err.println("ssyr");
    ssyr(uplo, n, alpha, x, 0, incx, a, 0, lda);
  }

  public void ssyr(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta, int lda) {
    if (debug) System.err.println("ssyr");
    checkArgument("SSYR", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("SSYR", 2, n >= 0);
    checkArgument("SSYR", 5, incx != 0);
    checkArgument("SSYR", 7, lda >= Math.max(1, n));
    if (n == 0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(a);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offseta + n * lda - 1, a.length);
    ssyrK(uplo, n, alpha, x, offsetx, incx, a, offseta, lda);
  }

  protected abstract void ssyrK(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta, int lda);

  public void dsyr2(String uplo, int n, double alpha, double[] x, int incx, double[] y, int incy, double[] a, int lda) {
    if (debug) System.err.println("dsyr2");
    dsyr2(uplo, n, alpha, x, 0, incx, y, 0, incy, a, 0, lda);
  }

  public void dsyr2(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
    if (debug) System.err.println("dsyr2");
    checkArgument("DSYR2", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DSYR2", 2, n >= 0);
    checkArgument("DSYR2", 5, incx != 0);
    checkArgument("DSYR2", 7, incy != 0);
    checkArgument("DSYR2", 9, lda >= Math.max(1, n));
    if (n == 0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(y);
    requireNonNull(a);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    checkIndex(offseta + n * lda - 1, a.length);
    dsyr2K(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
  }

  protected abstract void dsyr2K(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda);

  public void ssyr2(String uplo, int n, float alpha, float[] x, int incx, float[] y, int incy, float[] a, int lda) {
    if (debug) System.err.println("ssyr2");
    ssyr2(uplo, n, alpha, x, 0, incx, y, 0, incy, a, 0, lda);
  }

  public void ssyr2(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
    if (debug) System.err.println("ssyr2");
    checkArgument("SSYR2", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("SSYR2", 2, n >= 0);
    checkArgument("SSYR2", 5, incx != 0);
    checkArgument("SSYR2", 7, incy != 0);
    checkArgument("SSYR2", 9, lda >= Math.max(1, n));
    if (n == 0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(y);
    requireNonNull(a);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
    checkIndex(offseta + n * lda - 1, a.length);
    ssyr2K(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
  }

  protected abstract void ssyr2K(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda);

  public void dsyr2k(String uplo, String trans, int n, int k, double alpha, double[] a, int lda, double[] b, int ldb, double beta, double[] c, int ldc) {
    if (debug) System.err.println("dsyr2k");
    dsyr2k(uplo, trans, n, k, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
  }

  public void dsyr2k(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    if (debug) System.err.println("dsyr2k");
    checkArgument("DSYR2K", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DSYR2K", 2, lsame("N", trans) || lsame("T", trans) || lsame("C", trans));
    checkArgument("DSYR2K", 3, n >= 0);
    checkArgument("DSYR2K", 4, k >= 0);
    checkArgument("DSYR2K", 7, lda >= Math.max(1, lsame("N", trans) ? n : k));
    checkArgument("DSYR2K", 9, ldb >= Math.max(1, lsame("N", trans) ? n : k));
    checkArgument("DSYR2K", 12, ldc >= Math.max(1, n));
    if (n == 0 || ((alpha == 0 || k == 0) && beta == 1.0))
      return;
    requireNonNull(a);
    requireNonNull(b);
    requireNonNull(c);
    checkIndex(offseta + (lsame("N", trans) ? k : n) * lda - 1, a.length);
    checkIndex(offsetb + (lsame("N", trans) ? k : n) * ldb - 1, b.length);
    checkIndex(offsetc + n * ldc - 1, c.length);
    dsyr2kK(uplo, trans, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected abstract void dsyr2kK(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc);

  public void ssyr2k(String uplo, String trans, int n, int k, float alpha, float[] a, int lda, float[] b, int ldb, float beta, float[] c, int ldc) {
    if (debug) System.err.println("ssyr2k");
    ssyr2k(uplo, trans, n, k, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
  }

  public void ssyr2k(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    if (debug) System.err.println("ssyr2k");
    checkArgument("SSYR2K", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("SSYR2K", 2, lsame("N", trans) || lsame("T", trans) || lsame("C", trans));
    checkArgument("SSYR2K", 3, n >= 0);
    checkArgument("SSYR2K", 4, k >= 0);
    checkArgument("SSYR2K", 7, lda >= Math.max(1, lsame("N", trans) ? n : k));
    checkArgument("SSYR2K", 9, ldb >= Math.max(1, lsame("N", trans) ? n : k));
    checkArgument("SSYR2K", 12, ldc >= Math.max(1, n));
    if (n == 0 || ((alpha == 0 || k == 0) && beta == 1.0f))
      return;
    requireNonNull(a);
    requireNonNull(b);
    requireNonNull(c);
    checkIndex(offseta + (lsame("N", trans) ? k : n) * lda - 1, a.length);
    checkIndex(offsetb + (lsame("N", trans) ? k : n) * ldb - 1, b.length);
    checkIndex(offsetc + n * ldc - 1, c.length);
    ssyr2kK(uplo, trans, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected abstract void ssyr2kK(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc);

  public void dsyrk(String uplo, String trans, int n, int k, double alpha, double[] a, int lda, double beta, double[] c, int ldc) {
    if (debug) System.err.println("dsyrk");
    dsyrk(uplo, trans, n, k, alpha, a, 0, lda, beta, c, 0, ldc);
  }

  public void dsyrk(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double beta, double[] c, int offsetc, int ldc) {
    if (debug) System.err.println("dsyrk");
    checkArgument("DSYRK", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DSYRK", 2, lsame("N", trans) || lsame("T", trans) || lsame("C", trans));
    checkArgument("DSYRK", 3, n >= 0);
    checkArgument("DSYRK", 4, k >= 0);
    checkArgument("DSYRK", 7, lda >= Math.max(1, lsame("N", trans) ? n : k));
    checkArgument("DSYRK", 10, ldc >= Math.max(1, n));
    if (n == 0 || ((alpha == 0 || k == 0) && beta == 1.0))
      return;
    requireNonNull(a);
    requireNonNull(c);
    checkIndex(offseta + (lsame("N", trans) ? k : n) * lda - 1, a.length);
    checkIndex(offsetc + n * ldc - 1, c.length);
    dsyrkK(uplo, trans, n, k, alpha, a, offseta, lda, beta, c, offsetc, ldc);
  }

  protected abstract void dsyrkK(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double beta, double[] c, int offsetc, int ldc);

  public void ssyrk(String uplo, String trans, int n, int k, float alpha, float[] a, int lda, float beta, float[] c, int ldc) {
    if (debug) System.err.println("ssyrk");
    ssyrk(uplo, trans, n, k, alpha, a, 0, lda, beta, c, 0, ldc);
  }

  public void ssyrk(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float beta, float[] c, int offsetc, int ldc) {
    if (debug) System.err.println("ssyrk");
    checkArgument("SSYRK", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("SSYRK", 2, lsame("N", trans) || lsame("T", trans) || lsame("C", trans));
    checkArgument("SSYRK", 3, n >= 0);
    checkArgument("SSYRK", 4, k >= 0);
    checkArgument("SSYRK", 7, lda >= Math.max(1, lsame("N", trans) ? n : k));
    checkArgument("SSYRK", 10, ldc >= Math.max(1, n));
    if (n == 0 || ((alpha == 0 || k == 0) && beta == 1.0f))
      return;
    requireNonNull(a);
    requireNonNull(c);
    checkIndex(offseta + (lsame("N", trans) ? k : n) * lda - 1, a.length);
    checkIndex(offsetc + n * ldc - 1, c.length);
    ssyrkK(uplo, trans, n, k, alpha, a, offseta, lda, beta, c, offsetc, ldc);
  }

  protected abstract void ssyrkK(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float beta, float[] c, int offsetc, int ldc);

  public void dtbmv(String uplo, String trans, String diag, int n, int k, double[] a, int lda, double[] x, int incx) {
    if (debug) System.err.println("dtbmv");
    dtbmv(uplo, trans, diag, n, k, a, 0, lda, x, 0, incx);
  }

  public void dtbmv(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    if (debug) System.err.println("dtbmv");
    checkArgument("DTBMV", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DTBMV", 2, lsame("N", trans) || lsame("T", trans) || lsame("C", trans));
    checkArgument("DTBMV", 3, lsame("U", diag) || lsame("N", diag));
    checkArgument("DTBMV", 4, n >= 0);
    checkArgument("DTBMV", 5, k >= 0);
    checkArgument("DTBMV", 7, lda >= Math.max(1, k));
    checkArgument("DTBMV", 9, incx != 0);
    if (n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(x);
    checkIndex(offseta + n * lda - 1, a.length);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    dtbmvK(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }

  protected abstract void dtbmvK(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx);

  public void stbmv(String uplo, String trans, String diag, int n, int k, float[] a, int lda, float[] x, int incx) {
    if (debug) System.err.println("stbmv");
    stbmv(uplo, trans, diag, n, k, a, 0, lda, x, 0, incx);
  }

  public void stbmv(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    if (debug) System.err.println("stbmv");
    checkArgument("STBMV", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("STBMV", 2, lsame("N", trans) || lsame("T", trans) || lsame("C", trans));
    checkArgument("STBMV", 3, lsame("U", diag) || lsame("N", diag));
    checkArgument("STBMV", 4, n >= 0);
    checkArgument("STBMV", 5, k >= 0);
    checkArgument("STBMV", 7, lda >= Math.max(1, k));
    checkArgument("STBMV", 9, incx != 0);
    if (n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(x);
    checkIndex(offseta + n * lda - 1, a.length);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    stbmvK(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }

  protected abstract void stbmvK(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx);

  public void dtbsv(String uplo, String trans, String diag, int n, int k, double[] a, int lda, double[] x, int incx) {
    if (debug) System.err.println("dtbsv");
    dtbsv(uplo, trans, diag, n, k, a, 0, lda, x, 0, incx);
  }

  public void dtbsv(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    if (debug) System.err.println("dtbsv");
    checkArgument("DTBSV", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DTBSV", 2, lsame("N", trans) || lsame("T", trans) || lsame("C", trans));
    checkArgument("DTBSV", 3, lsame("U", diag) || lsame("N", diag));
    checkArgument("DTBSV", 4, n >= 0);
    checkArgument("DTBSV", 5, k >= 0);
    checkArgument("DTBSV", 7, lda >= Math.max(1, k));
    checkArgument("DTBSV", 9, incx != 0);
    if (n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(x);
    checkIndex(offseta + n * lda - 1, a.length);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    dtbsvK(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }

  protected abstract void dtbsvK(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx);

  public void stbsv(String uplo, String trans, String diag, int n, int k, float[] a, int lda, float[] x, int incx) {
    if (debug) System.err.println("stbsv");
    stbsv(uplo, trans, diag, n, k, a, 0, lda, x, 0, incx);
  }

  public void stbsv(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    if (debug) System.err.println("stbsv");
    checkArgument("STBSV", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("STBSV", 2, lsame("N", trans) || lsame("T", trans) || lsame("C", trans));
    checkArgument("STBSV", 3, lsame("U", diag) || lsame("N", diag));
    checkArgument("STBSV", 4, n >= 0);
    checkArgument("STBSV", 5, k >= 0);
    checkArgument("STBSV", 7, lda >= Math.max(1, k));
    checkArgument("STBSV", 9, incx != 0);
    if (n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(x);
    checkIndex(offseta + n * lda - 1, a.length);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    stbsvK(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }

  protected abstract void stbsvK(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx);

  public void dtpmv(String uplo, String trans, String diag, int n, double[] a, double[] x, int incx) {
    if (debug) System.err.println("dtpmv");
    dtpmv(uplo, trans, diag, n, a, 0, x, 0, incx);
  }

  public void dtpmv(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx) {
    if (debug) System.err.println("dtpmv");
    checkArgument("DTPMV", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DTPMV", 2, lsame("N", trans) || lsame("T", trans) || lsame("C", trans));
    checkArgument("DTPMV", 3, lsame("U", diag) || lsame("N", diag));
    checkArgument("DTPMV", 4, n >= 0);
    checkArgument("DTPMV", 7, incx != 0);
    if (n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(x);
    checkIndex(offseta + n * (n + 1) / 2 - 1, a.length);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    dtpmvK(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
  }

  protected abstract void dtpmvK(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx);

  public void stpmv(String uplo, String trans, String diag, int n, float[] a, float[] x, int incx) {
    if (debug) System.err.println("stpmv");
    stpmv(uplo, trans, diag, n, a, 0, x, 0, incx);
  }

  public void stpmv(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx) {
    if (debug) System.err.println("stpmv");
    checkArgument("STPMV", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("STPMV", 2, lsame("N", trans) || lsame("T", trans) || lsame("C", trans));
    checkArgument("STPMV", 3, lsame("U", diag) || lsame("N", diag));
    checkArgument("STPMV", 4, n >= 0);
    checkArgument("STPMV", 7, incx != 0);
    if (n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(x);
    checkIndex(offseta + n * (n + 1) / 2 - 1, a.length);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    stpmvK(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
  }

  protected abstract void stpmvK(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx);

  public void dtpsv(String uplo, String trans, String diag, int n, double[] a, double[] x, int incx) {
    if (debug) System.err.println("dtpsv");
    dtpsv(uplo, trans, diag, n, a, 0, x, 0, incx);
  }

  public void dtpsv(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx) {
    if (debug) System.err.println("dtpsv");
    checkArgument("DTPSV", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DTPSV", 2, lsame("N", trans) || lsame("T", trans) || lsame("C", trans));
    checkArgument("DTPSV", 3, lsame("U", diag) || lsame("N", diag));
    checkArgument("DTPSV", 4, n >= 0);
    checkArgument("DTPSV", 7, incx != 0);
    if (n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(x);
    checkIndex(offseta + n * (n + 1) / 2 - 1, a.length);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    dtpsvK(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
  }

  protected abstract void dtpsvK(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx);

  public void stpsv(String uplo, String trans, String diag, int n, float[] a, float[] x, int incx) {
    if (debug) System.err.println("stpsv");
    stpsv(uplo, trans, diag, n, a, 0, x, 0, incx);
  }

  public void stpsv(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx) {
    if (debug) System.err.println("stpsv");
    checkArgument("STPSV", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("STPSV", 2, lsame("N", trans) || lsame("T", trans) || lsame("C", trans));
    checkArgument("STPSV", 3, lsame("U", diag) || lsame("N", diag));
    checkArgument("STPSV", 4, n >= 0);
    checkArgument("STPSV", 7, incx != 0);
    if (n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(x);
    checkIndex(offseta + n * (n + 1) / 2 - 1, a.length);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    stpsvK(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
  }

  protected abstract void stpsvK(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx);

  public void dtrmm(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int lda, double[] b, int ldb) {
    if (debug) System.err.println("dtrmm");
    dtrmm(side, uplo, transa, diag, m, n, alpha, a, 0, lda, b, 0, ldb);
  }

  public void dtrmm(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
    if (debug) System.err.println("dtrmm");
    checkArgument("DTRMM", 1, lsame("L", side) || lsame("R", side));
    checkArgument("DTRMM", 2, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DTRMM", 3, lsame("N", transa) || lsame("T", transa) || lsame("C", transa));
    checkArgument("DTRMM", 4, lsame("U", diag) || lsame("N", diag));
    checkArgument("DTRMM", 5, m >= 0);
    checkArgument("DTRMM", 6, n >= 0);
    checkArgument("DTRMM", 9, lda >= Math.max(1, lsame("L", side) ? m : n));
    checkArgument("DTRMM", 11, ldb >= Math.max(1, m));
    if (n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(b);
    checkIndex(offseta + (lsame("L", side) ? m : n) * lda - 1, a.length);
    checkIndex(offsetb + n * ldb - 1, b.length);
    dtrmmK(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }

  protected abstract void dtrmmK(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb);

  public void strmm(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int lda, float[] b, int ldb) {
    if (debug) System.err.println("strmm");
    strmm(side, uplo, transa, diag, m, n, alpha, a, 0, lda, b, 0, ldb);
  }

  public void strmm(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
    if (debug) System.err.println("strmm");
    checkArgument("STRMM", 1, lsame("L", side) || lsame("R", side));
    checkArgument("STRMM", 2, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("STRMM", 3, lsame("N", transa) || lsame("T", transa) || lsame("C", transa));
    checkArgument("STRMM", 4, lsame("U", diag) || lsame("N", diag));
    checkArgument("STRMM", 5, m >= 0);
    checkArgument("STRMM", 6, n >= 0);
    checkArgument("STRMM", 9, lda >= Math.max(1, lsame("L", side) ? m : n));
    checkArgument("STRMM", 11, ldb >= Math.max(1, m));
    if (n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(b);
    checkIndex(offseta + (lsame("L", side) ? m : n) * lda - 1, a.length);
    checkIndex(offsetb + n * ldb - 1, b.length);
    strmmK(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }

  protected abstract void strmmK(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb);

  public void dtrmv(String uplo, String trans, String diag, int n, double[] a, int lda, double[] x, int incx) {
    if (debug) System.err.println("dtrmv");
    dtrmv(uplo, trans, diag, n, a, 0, lda, x, 0, incx);
  }

  public void dtrmv(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    if (debug) System.err.println("dtrmv");
    checkArgument("DTRMV", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DTRMV", 2, lsame("N", trans) || lsame("T", trans) || lsame("C", trans));
    checkArgument("DTRMV", 3, lsame("U", diag) || lsame("N", diag));
    checkArgument("DTRMV", 4, n >= 0);
    checkArgument("DTRMV", 6, lda >= Math.max(1, n));
    checkArgument("DTRMV", 8, incx != 0);
    if (n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(x);
    checkIndex(offseta + n * lda - 1, a.length);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    dtrmvK(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }

  protected abstract void dtrmvK(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx);

  public void strmv(String uplo, String trans, String diag, int n, float[] a, int lda, float[] x, int incx) {
    if (debug) System.err.println("strmv");
    strmv(uplo, trans, diag, n, a, 0, lda, x, 0, incx);
  }

  public void strmv(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    if (debug) System.err.println("strmv");
    checkArgument("STRMV", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("STRMV", 2, lsame("N", trans) || lsame("T", trans) || lsame("C", trans));
    checkArgument("STRMV", 3, lsame("U", diag) || lsame("N", diag));
    checkArgument("STRMV", 4, n >= 0);
    checkArgument("STRMV", 6, lda >= Math.max(1, n));
    checkArgument("STRMV", 8, incx != 0);
    if (n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(x);
    checkIndex(offseta + n * lda - 1, a.length);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    strmvK(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }

  protected abstract void strmvK(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx);

  public void dtrsm(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int lda, double[] b, int ldb) {
    if (debug) System.err.println("dtrsm");
    dtrsm(side, uplo, transa, diag, m, n, alpha, a, 0, lda, b, 0, ldb);
  }

  public void dtrsm(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
    if (debug) System.err.println("dtrsm");
    checkArgument("DTRSM", 1, lsame("L", side) || lsame("R", side));
    checkArgument("DTRSM", 2, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DTRSM", 3, lsame("N", transa) || lsame("T", transa) || lsame("C", transa));
    checkArgument("DTRSM", 4, lsame("U", diag) || lsame("N", diag));
    checkArgument("DTRSM", 5, m >= 0);
    checkArgument("DTRSM", 6, n >= 0);
    checkArgument("DTRSM", 9, lda >= Math.max(1, lsame("L", side) ? m : n));
    checkArgument("DTRSM", 11, ldb >= Math.max(1, m));
    if (n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(b);
    checkIndex(offseta + (lsame("L", side) ? m : n) * lda - 1, a.length);
    checkIndex(offsetb + n * ldb - 1, b.length);
    dtrsmK(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }

  protected abstract void dtrsmK(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb);

  public void strsm(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int lda, float[] b, int ldb) {
    if (debug) System.err.println("strsm");
    strsm(side, uplo, transa, diag, m, n, alpha, a, 0, lda, b, 0, ldb);
  }

  public void strsm(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
    if (debug) System.err.println("strsm");
    checkArgument("STRSM", 1, lsame("L", side) || lsame("R", side));
    checkArgument("STRSM", 2, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("STRSM", 3, lsame("N", transa) || lsame("T", transa) || lsame("C", transa));
    checkArgument("STRSM", 4, lsame("U", diag) || lsame("N", diag));
    checkArgument("STRSM", 5, m >= 0);
    checkArgument("STRSM", 6, n >= 0);
    checkArgument("STRSM", 9, lda >= Math.max(1, lsame("L", side) ? m : n));
    checkArgument("STRSM", 11, ldb >= Math.max(1, m));
    if (n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(b);
    checkIndex(offseta + (lsame("L", side) ? m : n) * lda - 1, a.length);
    checkIndex(offsetb + n * ldb - 1, b.length);
    strsmK(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }

  protected abstract void strsmK(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb);

  public void dtrsv(String uplo, String trans, String diag, int n, double[] a, int lda, double[] x, int incx) {
    if (debug) System.err.println("dtrsv");
    dtrsv(uplo, trans, diag, n, a, 0, lda, x, 0, incx);
  }

  public void dtrsv(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    if (debug) System.err.println("dtrsv");
    checkArgument("DTRSV", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("DTRSV", 2, lsame("N", trans) || lsame("T", trans) || lsame("C", trans));
    checkArgument("DTRSV", 3, lsame("U", diag) || lsame("N", diag));
    checkArgument("DTRSV", 4, n >= 0);
    checkArgument("DTRSV", 6, lda >= Math.max(1, n));
    checkArgument("DTRSV", 8, incx != 0);
    if (n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(x);
    checkIndex(offseta + n * lda - 1, a.length);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    dtrsvK(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }

  protected abstract void dtrsvK(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx);

  public void strsv(String uplo, String trans, String diag, int n, float[] a, int lda, float[] x, int incx) {
    if (debug) System.err.println("strsv");
    strsv(uplo, trans, diag, n, a, 0, lda, x, 0, incx);
  }

  public void strsv(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    if (debug) System.err.println("strsv");
    checkArgument("STRSV", 1, lsame("U", uplo) || lsame("L", uplo));
    checkArgument("STRSV", 2, lsame("N", trans) || lsame("T", trans) || lsame("C", trans));
    checkArgument("STRSV", 3, lsame("U", diag) || lsame("N", diag));
    checkArgument("STRSV", 4, n >= 0);
    checkArgument("STRSV", 6, lda >= Math.max(1, n));
    checkArgument("STRSV", 8, incx != 0);
    if (n == 0) {
      return;
    }
    requireNonNull(a);
    requireNonNull(x);
    checkIndex(offseta + n * lda - 1, a.length);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    strsvK(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }

  protected abstract void strsvK(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx);

  public int idamax(int n, double[] x, int incx) {
    if (debug) System.err.println("idamax");
    return idamax(n, x, 0, incx);
  }

  public int idamax(int n, double[] x, int offsetx, int incx) {
    if (debug) System.err.println("idamax");
    if (n <= 0) {
      return -1;
    }
    if (incx <= 0) {
      return -1;
    }
    if (n == 1) {
      return 0;
    }
    requireNonNull(x);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    // Fortran arrays use 1-based index
    return idamaxK(n, x, offsetx, incx) - 1;
  }

  protected abstract int idamaxK(int n, double[] x, int offsetx, int incx);

  public int isamax(int n, float[] x, int incx) {
    if (debug) System.err.println("isamax");
    return isamax(n, x, 0, incx);
  }

  public int isamax(int n, float[] x, int offsetx, int incx) {
    if (debug) System.err.println("isamax");
    if (n <= 0) {
      return -1;
    }
    if (incx <= 0) {
      return -1;
    }
    if (n == 1) {
      return 0;
    }
    requireNonNull(x);
    checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
    // Fortran arrays use 1-based index
    return isamaxK(n, x, offsetx, incx) - 1;
  }

  protected abstract int isamaxK(int n, float[] x, int offsetx, int incx);

  public boolean lsame(String ca, String cb) {
    if (debug) System.err.println("lsame");
    return ca != null && ca.regionMatches(true, 0, cb, 0, ca.length());
  }
}
