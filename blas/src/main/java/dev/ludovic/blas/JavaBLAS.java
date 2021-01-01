/*
 * Copyright 2020, Ludovic Henry
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
 */

package dev.ludovic.blas;

public class JavaBLAS implements BLAS {

  private static final JavaBLAS instance = new JavaBLAS();

  //FIXME: remove dependency on F2jBLAS
  private static final com.github.fommil.netlib.F2jBLAS f2j = new com.github.fommil.netlib.F2jBLAS();

  protected JavaBLAS() {}

  public static BLAS getInstance() {
    return instance;
  }

  private int loopBound(int index, int size) {
    assert (size & (size - 1)) == 0;
    return index & ~(size - 1);
  }

  protected IllegalArgumentException illegalArgument(String method, int arg) {
    return new IllegalArgumentException(String.format("** On entry to '%s' parameter number %d had an illegal value", method, arg));
  }

  public double dasum(int n, double[] x, int incx) {
    return dasum(n, x, 0, incx);
  }

  public double dasum(int n, double[] x, int offsetx, int incx) {
    if (n <= 0) {
      return 0.0;
    }

    double sum = 0.0;
    for (int ix = incx < 0 ? (n - 1) * -incx : 0; incx < 0 ? ix >= 0 : ix < n * incx; ix += incx) {
      sum += Math.abs(x[offsetx + ix]);
    }
    return sum;
  }

  public float sasum(int n, float[] x, int incx) {
    return sasum(n, x, 0, incx);
  }

  public float sasum(int n, float[] x, int offsetx, int incx) {
    if (n <= 0) {
      return 0.0f;
    }

    float sum = 0.0f;
    for (int ix = incx < 0 ? (n - 1) * -incx : 0; incx < 0 ? ix >= 0 : ix < n * incx; ix += incx) {
      sum += Math.abs(x[offsetx + ix]);
    }
    return sum;
  }

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
    for (int ix = incx < 0 ? (n - 1) * -incx : 0,
             iy = incy < 0 ? (n - 1) * -incy : 0;
         (incx < 0 ? ix >= 0 : ix < n * incx)
          && (incy < 0 ? iy >= 0 : iy < n * incy);
         ix += incx, iy += incy) {
      y[offsety + iy] += alpha * x[offsetx + ix];
    }
  }

  public void saxpy(int n, float alpha, float[] x, int incx, float[] y, int incy) {
    saxpy(n, alpha, x, 0, incx, y, 0, incy);
  }

  // y += alpha * x
  public void saxpy(int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (n <= 0) {
      return;
    }
    if (alpha == 0.0) {
      return;
    }
    for (int ix = incx < 0 ? (n - 1) * -incx : 0,
             iy = incy < 0 ? (n - 1) * -incy : 0;
         (incx < 0 ? ix >= 0 : ix < n * incx)
          && (incy < 0 ? iy >= 0 : iy < n * incy);
         ix += incx, iy += incy) {
      y[offsety + iy] += alpha * x[offsetx + ix];
    }
  }

  public void dcopy(int n, double[] x, int incx, double[] y, int incy) {
    dcopy(n, x, 0, incx, y, 0, incy);
  }

  public void dcopy(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (n <= 0) {
      return;
    }

    if (x.length >= offsetx + n && incx == 1 && y.length >= offsety + n && incy == 1) {
      System.arraycopy(x, offsetx, y, offsety, n);
    } else {
      for (int ix = incx < 0 ? (n - 1) * -incx : 0,
               iy = incy < 0 ? (n - 1) * -incy : 0;
           (incx < 0 ? ix >= 0 : ix < n * incx)
            && (incy < 0 ? iy >= 0 : iy < n * incy);
           ix += incx, iy += incy) {
        y[offsety + iy] = x[offsetx + ix];
      }
    }
  }

  public void scopy(int n, float[] x, int incx, float[] y, int incy) {
    scopy(n, x, 0, incx, y, 0, incy);
  }

  public void scopy(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (n <= 0) {
      return;
    }

    if (x.length >= offsetx + n && incx == 1 && y.length >= offsety + n && incy == 1) {
      System.arraycopy(x, offsetx, y, offsety, n);
    } else {
      for (int ix = incx < 0 ? (n - 1) * -incx : 0,
               iy = incy < 0 ? (n - 1) * -incy : 0;
           (incx < 0 ? ix >= 0 : ix < n * incx)
            && (incy < 0 ? iy >= 0 : iy < n * incy);
           ix += incx, iy += incy) {
        y[offsety + iy] = x[offsetx + ix];
      }
    }
  }

  public double ddot(int n, double[] x, int incx, double[] y, int incy) {
    return ddot(n, x, 0, incx, y, 0, incy);
  }

  // sum(x * y)
  public double ddot(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (n <= 0) {
      return 0.0;
    }

    double sum = 0.0;
    for (int ix = incx < 0 ? (n - 1) * -incx : 0,
             iy = incy < 0 ? (n - 1) * -incy : 0;
         (incx < 0 ? ix >= 0 : ix < n * incx)
          && (incy < 0 ? iy >= 0 : iy < n * incy);
         ix += incx, iy += incy) {
      sum += x[offsetx + ix] * y[offsety + iy];
    }
    return sum;
  }

  public float sdot(int n, float[] x, int incx, float[] y, int incy) {
    return sdot(n, x, 0, incx, y, 0, incy);
  }

  // sum(x * y)
  public float sdot(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (n <= 0) {
      return 0.0f;
    }

    float sum = 0.0f;
    for (int ix = incx < 0 ? (n - 1) * -incx : 0,
             iy = incy < 0 ? (n - 1) * -incy : 0;
         (incx < 0 ? ix >= 0 : ix < n * incx)
          && (incy < 0 ? iy >= 0 : iy < n * incy);
         ix += incx, iy += incy) {
      sum += x[offsetx + ix] * y[offsety + iy];
    }
    return sum;
  }

  public float sdsdot(int n, float sb, float[] x, int incx, float[] y, int incy) {
    return sdsdot(n, sb, x, 0, incx, y, 0, incy);
  }

  public float sdsdot(int n, float sb, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (n <= 0) {
      return 0.0f;
    }

    double sum = sb;
    for (int ix = incx < 0 ? (n - 1) * -incx : 0,
             iy = incy < 0 ? (n - 1) * -incy : 0;
         (incx < 0 ? ix >= 0 : ix < n * incx)
          && (incy < 0 ? iy >= 0 : iy < n * incy);
         ix += incx, iy += incy) {
      sum += (double)(x[offsetx + ix]) * (double)(y[offsety + iy]);
    }
    return (float)sum;
  }

  public void dgbmv(String trans, int m, int n, int kl, int ku, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    dgbmv(trans, m, n, kl, ku, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void dgbmv(String trans, int m, int n, int kl, int ku, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    f2j.dgbmv(trans, m, n, kl, ku, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  public void sgbmv(String trans, int m, int n, int kl, int ku, float alpha, float[] a, int lda, float[] x, int incx, float beta, float[] y, int incy) {
    sgbmv(trans, m, n, kl, ku, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void sgbmv(String trans, int m, int n, int kl, int ku, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    f2j.sgbmv(trans, m, n, kl, ku, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  public void dgemm(String transa, String transb, int m, int n, int k, double alpha, double[] a, int lda, double[] b, int ldb, double beta, double[] c, int ldc) {
    dgemm(transa, transb, m, n, k, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
  }

  // c = alpha * a * b + beta * c
  public void dgemm(String transa, String transb, int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    f2j.dgemm(transa, transb, m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  public void sgemm(String transa, String transb, int m, int n, int k, float alpha, float[] a, int lda, float[] b, int ldb, float beta, float[] c, int ldc) {
    sgemm(transa, transb, m, n, k, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
  }

  public void sgemm(String transa, String transb, int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    f2j.sgemm(transa, transb, m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  public void dgemv(String trans, int m, int n, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    dgemv(trans, m, n, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  // y = alpha * A * x + beta * y
  public void dgemv(String trans, int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    if (!lsame("N", trans) && !lsame("T", trans) && !lsame("C", trans)) {
      throw illegalArgument("DGEMV", 1);
    }
    if (m < 0) {
      throw illegalArgument("DGEMV", 2);
    }
    if (n < 0) {
      throw illegalArgument("DGEMV", 3);
    }
    if (lda < Math.max(1, m)) {
      throw illegalArgument("DGEMV", 6);
    }
    if (incx == 0) {
      throw illegalArgument("DGEMV", 8);
    }
    if (incy == 0) {
      throw illegalArgument("DGEMV", 11);
    }
    if (m == 0 || n == 0) {
      return;
    }
    // y = beta * y
    if (beta != 1.0) {
      dscal(lsame("N", trans) ? m : n, beta, y, offsety, incy);
    }
    // y += alpha * A * x
    if (alpha != 0.0) {
      if (lsame("N", trans)) {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4) {
          double alphax0 = alpha * x[offsetx + ix + incx * 0];
          double alphax1 = alpha * x[offsetx + ix + incx * 1];
          double alphax2 = alpha * x[offsetx + ix + incx * 2];
          double alphax3 = alpha * x[offsetx + ix + incx * 3];
          for (int row = 0, iy = incy < 0 ? (m - 1) * -incy : 0; row < m; row += 1, iy += incy) {
            y[offsety + iy] += alphax0 * a[offseta + row + (col + 0) * lda]
                            +  alphax1 * a[offseta + row + (col + 1) * lda]
                            +  alphax2 * a[offseta + row + (col + 2) * lda]
                            +  alphax3 * a[offseta + row + (col + 3) * lda];
          }
        }
        for (; col < n; col += 1, ix += incx) {
          double alphax = alpha * x[offsetx + ix];
          for (int row = 0, iy = incy < 0 ? (m - 1) * -incy : 0; row < m; row += 1, iy += incy) {
            y[offsety + iy] += alphax * a[offseta + row + col * lda];
          }
        }
      } else {
        int col = 0, iy = incy < 0 ? (n - 1) * -incy : 0;
        for (; col < loopBound(n, 4); col += 4, iy += incy * 4) {
          double sum0 = 0.0;
          double sum1 = 0.0;
          double sum2 = 0.0;
          double sum3 = 0.0;
          for (int row = 0, ix = incx < 0 ? (m - 1) * -incx : 0; row < m; row += 1, ix += incx) {
            double xix = x[offsetx + ix];
            sum0 += xix * a[offseta + row + (col + 0) * lda];
            sum1 += xix * a[offseta + row + (col + 1) * lda];
            sum2 += xix * a[offseta + row + (col + 2) * lda];
            sum3 += xix * a[offseta + row + (col + 3) * lda];
          }
          y[offsety + iy + incy * 0] += alpha * sum0;
          y[offsety + iy + incy * 1] += alpha * sum1;
          y[offsety + iy + incy * 2] += alpha * sum2;
          y[offsety + iy + incy * 3] += alpha * sum3;
        }
        for (; col < n; col += 1, iy += incy) {
          double sum = 0.0;
          for (int row = 0, ix = incx < 0 ? (m - 1) * -incx : 0; row < m; row += 1, ix += incx) {
            sum += x[offsetx + ix] * a[offseta + row + col * lda];
          }
          y[offsety + iy] += alpha * sum;
        }
      }
    }
  }

  public void sgemv(String trans, int m, int n, float alpha, float[] a, int lda, float[] x, int incx, float beta, float[] y, int incy) {
    sgemv(trans, m, n, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  // y = alpha * A * x + beta * y
  public void sgemv(String trans, int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    if (!lsame("N", trans) && !lsame("T", trans) && !lsame("C", trans)) {
      throw illegalArgument("SGEMV", 1);
    }
    if (m < 0) {
      throw illegalArgument("SGEMV", 2);
    }
    if (n < 0) {
      throw illegalArgument("SGEMV", 3);
    }
    if (lda < Math.max(1, m)) {
      throw illegalArgument("SGEMV", 6);
    }
    if (incx == 0) {
      throw illegalArgument("SGEMV", 8);
    }
    if (incy == 0) {
      throw illegalArgument("SGEMV", 11);
    }
    if (m == 0 || n == 0) {
      return;
    }
    // y = beta * y
    if (beta != 1.0f) {
      sscal(lsame("N", trans) ? m : n, beta, y, offsety, incy);
    }
    // y += alpha * A * x
    if (alpha != 0.0f) {
      if (lsame("N", trans)) {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0;
        for (; col < loopBound(n, 8); col += 8, ix += incx * 8) {
          float alphax0 = alpha * x[offsetx + ix + incx * 0];
          float alphax1 = alpha * x[offsetx + ix + incx * 1];
          float alphax2 = alpha * x[offsetx + ix + incx * 2];
          float alphax3 = alpha * x[offsetx + ix + incx * 3];
          float alphax4 = alpha * x[offsetx + ix + incx * 4];
          float alphax5 = alpha * x[offsetx + ix + incx * 5];
          float alphax6 = alpha * x[offsetx + ix + incx * 6];
          float alphax7 = alpha * x[offsetx + ix + incx * 7];
          for (int row = 0, iy = incy < 0 ? (m - 1) * -incy : 0; row < m; row += 1, iy += incy) {
            y[offsety + iy] += alphax0 * a[offseta + row + (col + 0) * lda]
                            +  alphax1 * a[offseta + row + (col + 1) * lda]
                            +  alphax2 * a[offseta + row + (col + 2) * lda]
                            +  alphax3 * a[offseta + row + (col + 3) * lda]
                            +  alphax4 * a[offseta + row + (col + 4) * lda]
                            +  alphax5 * a[offseta + row + (col + 5) * lda]
                            +  alphax6 * a[offseta + row + (col + 6) * lda]
                            +  alphax7 * a[offseta + row + (col + 7) * lda];
          }
        }
        for (; col < n; col += 1, ix += incx) {
          float alphax = alpha * x[offsetx + ix];
          for (int row = 0, iy = incy < 0 ? (m - 1) * -incy : 0; row < m; row += 1, iy += incy) {
            y[offsety + iy] += alphax * a[offseta + row + col * lda];
          }
        }
      } else {
        int col = 0, iy = incy < 0 ? (n - 1) * -incy : 0;
        for (; col < loopBound(n, 8); col += 8, iy += incy * 8) {
          float sum0 = 0.0f;
          float sum1 = 0.0f;
          float sum2 = 0.0f;
          float sum3 = 0.0f;
          float sum4 = 0.0f;
          float sum5 = 0.0f;
          float sum6 = 0.0f;
          float sum7 = 0.0f;
          for (int row = 0, ix = incx < 0 ? (m - 1) * -incx : 0; row < m; row += 1, ix += incx) {
            sum0 += x[offsetx + ix] * a[offseta + row + (col + 0) * lda];
            sum1 += x[offsetx + ix] * a[offseta + row + (col + 1) * lda];
            sum2 += x[offsetx + ix] * a[offseta + row + (col + 2) * lda];
            sum3 += x[offsetx + ix] * a[offseta + row + (col + 3) * lda];
            sum4 += x[offsetx + ix] * a[offseta + row + (col + 4) * lda];
            sum5 += x[offsetx + ix] * a[offseta + row + (col + 5) * lda];
            sum6 += x[offsetx + ix] * a[offseta + row + (col + 6) * lda];
            sum7 += x[offsetx + ix] * a[offseta + row + (col + 7) * lda];
          }
          y[offsety + iy + incy * 0] += alpha * sum0;
          y[offsety + iy + incy * 1] += alpha * sum1;
          y[offsety + iy + incy * 2] += alpha * sum2;
          y[offsety + iy + incy * 3] += alpha * sum3;
          y[offsety + iy + incy * 4] += alpha * sum4;
          y[offsety + iy + incy * 5] += alpha * sum5;
          y[offsety + iy + incy * 6] += alpha * sum6;
          y[offsety + iy + incy * 7] += alpha * sum7;
        }
        for (; col < n; col += 1, iy += incy) {
          float sum = 0.0f;
          for (int row = 0, ix = incx < 0 ? (m - 1) * -incx : 0; row < m; row += 1, ix += incx) {
            sum += x[offsetx + ix] * a[offseta + row + col * lda];
          }
          y[offsety + iy] += alpha * sum;
        }
      }
    }
  }

  // A += alpha * x * y.t
  public void dger(int m, int n, double alpha, double[] x, int incx, double[] y, int incy, double[] a, int lda) {
    dger(m, n, alpha, x, 0, incx, y, 0, incy, a, 0, lda);
  }

  public void dger(int m, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
    if (m < 0) {
      throw illegalArgument("DGER", 1);
    }
    if (n < 0) {
      throw illegalArgument("DGER", 2);
    }
    if (incx == 0) {
      throw illegalArgument("DGER", 5);
    }
    if (incy == 0) {
      throw illegalArgument("DGER", 7);
    }
    if (lda < Math.max(1, m)) {
      throw illegalArgument("DGER", 9);
    }
    if (m == 0 || n == 0) {
      return;
    }
    if (alpha != 0.0) {
      int col = 0, iy = incy < 0 ? (n - 1) * -incy : 0;
      for (; col < loopBound(n, 4); col += 4, iy += incy * 4) {
        double alphayiy0 = alpha * y[offsety + iy + incy * 0];
        double alphayiy1 = alpha * y[offsety + iy + incy * 1];
        double alphayiy2 = alpha * y[offsety + iy + incy * 2];
        double alphayiy3 = alpha * y[offsety + iy + incy * 3];
        int row = 0, jx = incx < 0 ? (n - 1) * -incx : 0;
        for (; row < m; row += 1, jx += incx) {
          double xjx = x[offsetx + jx];
          a[offseta + row + (col + 0) * lda] += alphayiy0 * xjx;
          a[offseta + row + (col + 1) * lda] += alphayiy1 * xjx;
          a[offseta + row + (col + 2) * lda] += alphayiy2 * xjx;
          a[offseta + row + (col + 3) * lda] += alphayiy3 * xjx;
        }
      }
      for (; col < n; col += 1, iy += incy) {
        double alphayiy = alpha * y[offsety + iy];
        int row = 0, jx = incx < 0 ? (n - 1) * -incx : 0;
        for (; row < m; row += 1, jx += incx) {
          a[offseta + row + col * lda] += alphayiy * x[offsetx + jx];
        }
      }
    }
  }

  public void sger(int m, int n, float alpha, float[] x, int incx, float[] y, int incy, float[] a, int lda) {
    sger(m, n, alpha, x, 0, incx, y, 0, incy, a, 0, lda);
  }

  public void sger(int m, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
    if (m < 0) {
      throw illegalArgument("SGER", 1);
    }
    if (n < 0) {
      throw illegalArgument("SGER", 2);
    }
    if (incx == 0) {
      throw illegalArgument("SGER", 5);
    }
    if (incy == 0) {
      throw illegalArgument("SGER", 7);
    }
    if (lda < Math.max(1, m)) {
      throw illegalArgument("SGER", 9);
    }
    if (m == 0 || n == 0) {
      return;
    }
    if (alpha != 0.0) {
      int col = 0, iy = incy < 0 ? (n - 1) * -incy : 0;
      for (; col < loopBound(n, 4); col += 4, iy += incy * 4) {
        float alphayiy0 = alpha * y[offsety + iy + incy * 0];
        float alphayiy1 = alpha * y[offsety + iy + incy * 1];
        float alphayiy2 = alpha * y[offsety + iy + incy * 2];
        float alphayiy3 = alpha * y[offsety + iy + incy * 3];
        int row = 0, jx = incx < 0 ? (n - 1) * -incx : 0;
        for (; row < m; row += 1, jx += incx) {
          float xjx = x[offsetx + jx];
          a[offseta + row + (col + 0) * lda] += alphayiy0 * xjx;
          a[offseta + row + (col + 1) * lda] += alphayiy1 * xjx;
          a[offseta + row + (col + 2) * lda] += alphayiy2 * xjx;
          a[offseta + row + (col + 3) * lda] += alphayiy3 * xjx;
        }
      }
      for (; col < n; col += 1, iy += incy) {
        float alphayiy = alpha * y[offsety + iy];
        int row = 0, jx = incx < 0 ? (n - 1) * -incx : 0;
        for (; row < m; row += 1, jx += incx) {
          a[offseta + row + col * lda] += alphayiy * x[offsetx + jx];
        }
      }
    }
  }

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

    double sum = 0.0;
    for (int ix = 0; ix < n * incx; ix += incx) {
      sum += Math.pow(x[offsetx + ix], 2);
    }
    return Math.sqrt(sum);
  }

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

    float sum = 0.0f;
    for (int ix = 0; ix < n * incx; ix += incx) {
      sum += (float)Math.pow(x[offsetx + ix], 2);
    }
    return (float)Math.sqrt(sum);
  }

  public void drot(int n, double[] x, int incx, double[] y, int incy, double c, double s) {
    drot(n, x, 0, incx, y, 0, incy, c, s);
  }

  public void drot(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double c, double s) {
    if (n <= 0) {
      return;
    }

    for (int ix = incx < 0 ? (n - 1) * -incx : 0,
             iy = incy < 0 ? (n - 1) * -incy : 0;
         (incx < 0 ? ix >= 0 : ix < n * incx)
          && (incy < 0 ? iy >= 0 : iy < n * incy);
         ix += incx, iy += incy) {
      double xold = x[offsetx + ix];
      double yold = y[offsety + iy];
      x[offsetx + ix] = c * xold + s * yold;
      y[offsety + iy] = c * yold - s * xold;
    }
  }

  public void srot(int n, float[] x, int incx, float[] y, int incy, float c, float s) {
    srot(n, x, 0, incx, y, 0, incy, c, s);
  }

  public void srot(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float c, float s) {
    if (n <= 0) {
      return;
    }

    for (int ix = incx < 0 ? (n - 1) * -incx : 0,
             iy = incy < 0 ? (n - 1) * -incy : 0;
         (incx < 0 ? ix >= 0 : ix < n * incx)
          && (incy < 0 ? iy >= 0 : iy < n * incy);
         ix += incx, iy += incy) {
      float xnew = c * x[offsetx + ix] + s * y[offsety + iy];
      float ynew = c * y[offsety + iy] - s * x[offsetx + ix];
      x[offsetx + ix] = xnew;
      y[offsety + iy] = ynew;
    }
  }

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
    f2j.drotm(n, x, offsetx, incx, y, offsety, incy, param, offsetparam);
  }

  public void srotm(int n, float[] x, int incx, float[] y, int incy, float[] param) {
    srotm(n, x, 0, incx, y, 0, incy, param, 0);
  }

  public void srotm(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] param, int offsetparam) {
    f2j.srotm(n, x, offsetx, incx, y, offsety, incy, param, offsetparam);
  }

  public void drotmg(org.netlib.util.doubleW dd1, org.netlib.util.doubleW dd2, org.netlib.util.doubleW dx1, double dy1, double[] param) {
    drotmg(dd1, dd2, dx1, dy1, param, 0);
  }

  public void drotmg(org.netlib.util.doubleW dd1, org.netlib.util.doubleW dd2, org.netlib.util.doubleW dx1, double dy1, double[] param, int offsetparam) {
    f2j.drotmg(dd1, dd2, dx1, dy1, param, offsetparam);
  }

  public void srotmg(org.netlib.util.floatW sd1, org.netlib.util.floatW sd2, org.netlib.util.floatW sx1, float sy1, float[] param) {
    srotmg(sd1, sd2, sx1, sy1, param, 0);
  }

  public void srotmg(org.netlib.util.floatW sd1, org.netlib.util.floatW sd2, org.netlib.util.floatW sx1, float sy1, float[] param, int offsetparam) {
    f2j.srotmg(sd1, sd2, sx1, sy1, param, offsetparam);
  }

  public void dsbmv(String uplo, int n, int k, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    dsbmv(uplo, n, k, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void dsbmv(String uplo, int n, int k, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    f2j.dsbmv(uplo, n, k, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  public void ssbmv(String uplo, int n, int k, float alpha, float[] a, int lda, float[] x, int incx, float beta, float[] y, int incy) {
    ssbmv(uplo, n, k, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void ssbmv(String uplo, int n, int k, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    f2j.ssbmv(uplo, n, k, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

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
    for (int ix = incx < 0 ? (n - 1) * -incx : 0; incx < 0 ? ix >= 0 : ix < n * incx; ix += incx) {
      x[offsetx + ix] *= alpha;
    }
  }

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
    if (alpha == 1.0) {
      return;
    }
    for (int ix = incx < 0 ? (n - 1) * -incx : 0; incx < 0 ? ix >= 0 : ix < n * incx; ix += incx) {
      x[offsetx + ix] *= alpha;
    }
  }

  public void dspmv(String uplo, int n, double alpha, double[] a, double[] x, int incx, double beta, double[] y, int incy) {
    dspmv(uplo, n, alpha, a, 0, x, 0, incx, beta, y, 0, incy);
  }

  // y = alpha * a * x + beta * y
  public void dspmv(String uplo, int n, double alpha, double[] a, int offseta, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    if (!lsame("U", uplo) && !lsame("L", uplo)) {
      throw illegalArgument("DSPMV", 1);
    }
    if (n < 0) {
      throw illegalArgument("DSPMV", 2);
    }
    if (incx == 0) {
      throw illegalArgument("DSPMV", 6);
    }
    if (incy == 0) {
      throw illegalArgument("DSPMV", 9);
    }
    if (n == 0) {
      return;
    }
    // y = beta * y
    if (beta != 1.0) {
      dscal(n, beta, y, offsety, incy);
    }
    // y += alpha * A * x
    if (alpha != 0.0) {
      if (lsame("U", uplo)) {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0, iy = incy < 0 ? (n - 1) * -incy : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4, iy += incy * 4) {
          double alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          double alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          double alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          double alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          double sumiy0 = 0.0;
          double sumiy1 = 0.0;
          double sumiy2 = 0.0;
          double sumiy3 = 0.0;
          int row = 0, jx = incx < 0 ? (col - 1) * -incx : 0, jy = incy < 0 ? (col - 1) * -incy : 0;
          for (; row < col; row += 1, jx += incx, jy += incy) {
            double a0 = a[offseta + row + (col + 0) * ((col + 0) + 1) / 2];
            double a1 = a[offseta + row + (col + 1) * ((col + 1) + 1) / 2];
            double a2 = a[offseta + row + (col + 2) * ((col + 2) + 1) / 2];
            double a3 = a[offseta + row + (col + 3) * ((col + 3) + 1) / 2];
            y[offsety + jy] += alphaxix0 * a0
                            +  alphaxix1 * a1
                            +  alphaxix2 * a2
                            +  alphaxix3 * a3;
            double xjx = x[offsetx + jx];
            sumiy0 += xjx * a0;
            sumiy1 += xjx * a1;
            sumiy2 += xjx * a2;
            sumiy3 += xjx * a3;
          }
          double a00 = a[offseta + (row + 0) + (col + 0) * ((col + 0) + 1) / 2];
          double a01 = a[offseta + (row + 0) + (col + 1) * ((col + 1) + 1) / 2];
          double a02 = a[offseta + (row + 0) + (col + 2) * ((col + 2) + 1) / 2];
          double a03 = a[offseta + (row + 0) + (col + 3) * ((col + 3) + 1) / 2];
          double a11 = a[offseta + (row + 1) + (col + 1) * ((col + 1) + 1) / 2];
          double a12 = a[offseta + (row + 1) + (col + 2) * ((col + 2) + 1) / 2];
          double a13 = a[offseta + (row + 1) + (col + 3) * ((col + 3) + 1) / 2];
          double a22 = a[offseta + (row + 2) + (col + 2) * ((col + 2) + 1) / 2];
          double a23 = a[offseta + (row + 2) + (col + 3) * ((col + 3) + 1) / 2];
          double a33 = a[offseta + (row + 3) + (col + 3) * ((col + 3) + 1) / 2];
          double xjx0 = x[offsetx + jx + incx * 0];
          double xjx1 = x[offsetx + jx + incx * 1];
          double xjx2 = x[offsetx + jx + incx * 2];
          double xjx3 = x[offsetx + jx + incx * 3];
          sumiy0 += xjx0 * a00
                 +  xjx1 * a01
                 +  xjx2 * a02
                 +  xjx3 * a03;
          sumiy1 += xjx0 * a01
                 +  xjx1 * a11
                 +  xjx2 * a12
                 +  xjx3 * a13;
          sumiy2 += xjx0 * a02
                 +  xjx1 * a12
                 +  xjx2 * a22
                 +  xjx3 * a23;
          sumiy3 += xjx0 * a03
                 +  xjx1 * a13
                 +  xjx2 * a23
                 +  xjx3 * a33;
          y[offsety + iy + incy * 0] += alpha * sumiy0;
          y[offsety + iy + incy * 1] += alpha * sumiy1;
          y[offsety + iy + incy * 2] += alpha * sumiy2;
          y[offsety + iy + incy * 3] += alpha * sumiy3;
        }
        for (; col < n; col += 1, ix += incx, iy += incy) {
          double alphaxix = alpha * x[offsetx + ix];
          double sumiy = 0.0;
          int row = 0, jx = incx < 0 ? (col - 1) * -incx : 0, jy = incy < 0 ? (col - 1) * -incy : 0;
          for (; row < col; row += 1, jx += incx, jy += incy) {
            y[offsety + jy] += alphaxix * a[offseta + row + col * (col + 1) / 2];
            sumiy += x[offsetx + jx] * a[offseta + row + col * (col + 1) / 2];
          }
          sumiy += x[offsetx + jx] * a[offseta + row + col * (col + 1) / 2];
          y[offsety + iy] += alpha * sumiy;
        }
      } else {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0, iy = incy < 0 ? (n - 1) * -incy : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4, iy += incy * 4) {
          double alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          double alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          double alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          double alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          double sumiy0 = 0.0;
          double sumiy1 = 0.0;
          double sumiy2 = 0.0;
          double sumiy3 = 0.0;
          double a00 = a[offseta + /*row=*/(col + 0) + (col + 0) * (2 * n - (col + 0) - 1) / 2];
          double a10 = a[offseta + /*row=*/(col + 1) + (col + 0) * (2 * n - (col + 0) - 1) / 2];
          double a11 = a[offseta + /*row=*/(col + 1) + (col + 1) * (2 * n - (col + 1) - 1) / 2];
          double a20 = a[offseta + /*row=*/(col + 2) + (col + 0) * (2 * n - (col + 0) - 1) / 2];
          double a21 = a[offseta + /*row=*/(col + 2) + (col + 1) * (2 * n - (col + 1) - 1) / 2];
          double a22 = a[offseta + /*row=*/(col + 2) + (col + 2) * (2 * n - (col + 2) - 1) / 2];
          double a30 = a[offseta + /*row=*/(col + 3) + (col + 0) * (2 * n - (col + 0) - 1) / 2];
          double a31 = a[offseta + /*row=*/(col + 3) + (col + 1) * (2 * n - (col + 1) - 1) / 2];
          double a32 = a[offseta + /*row=*/(col + 3) + (col + 2) * (2 * n - (col + 2) - 1) / 2];
          double a33 = a[offseta + /*row=*/(col + 3) + (col + 3) * (2 * n - (col + 3) - 1) / 2];
          double x0 = x[offsetx + (incx < 0 ? (n - (col + 0) - 1) * -incx : (col + 0) * incx)];
          double x1 = x[offsetx + (incx < 0 ? (n - (col + 1) - 1) * -incx : (col + 1) * incx)];
          double x2 = x[offsetx + (incx < 0 ? (n - (col + 2) - 1) * -incx : (col + 2) * incx)];
          double x3 = x[offsetx + (incx < 0 ? (n - (col + 3) - 1) * -incx : (col + 3) * incx)];
          sumiy0 += x0 * a00
                 +  x1 * a10
                 +  x2 * a20
                 +  x3 * a30;
          sumiy1 += x0 * a10
                 +  x1 * a11
                 +  x2 * a21
                 +  x3 * a31;
          sumiy2 += x0 * a20
                 +  x1 * a21
                 +  x2 * a22
                 +  x3 * a32;
          sumiy3 += x0 * a30
                 +  x1 * a31
                 +  x2 * a32
                 +  x3 * a33;
          int row = col + 4, jx = incx < 0 ? (n - (col + 4) - 1) * -incx : (col + 4) * incx, jy = incy < 0 ? (n - (col + 4) - 1) * -incy : (col + 4) * incy;
          for (; row < n; row += 1, jx += incx, jy += incy) {
            double a0 = a[offseta + row + (col + 0) * (2 * n - (col + 0) - 1) / 2];
            double a1 = a[offseta + row + (col + 1) * (2 * n - (col + 1) - 1) / 2];
            double a2 = a[offseta + row + (col + 2) * (2 * n - (col + 2) - 1) / 2];
            double a3 = a[offseta + row + (col + 3) * (2 * n - (col + 3) - 1) / 2];
            y[offsety + jy] += alphaxix0 * a0
                            +  alphaxix1 * a1
                            +  alphaxix2 * a2
                            +  alphaxix3 * a3;
            double xjx = x[offsetx + jx];
            sumiy0 += xjx * a0;
            sumiy1 += xjx * a1;
            sumiy2 += xjx * a2;
            sumiy3 += xjx * a3;
          }
          y[offsety + iy + incy * 0] += alpha * sumiy0;
          y[offsety + iy + incy * 1] += alpha * sumiy1;
          y[offsety + iy + incy * 2] += alpha * sumiy2;
          y[offsety + iy + incy * 3] += alpha * sumiy3;
        }
        for (; col < n; col += 1, ix += incx, iy += incy) {
          double alphaxix = alpha * x[offsetx + ix];
          double sumiy = 0.0;
          sumiy += x[offsetx + (incx < 0 ? (n - col - 1) * -incx : col * incx)] * a[offseta + /*row=*/col + col * (2 * n - col - 1) / 2];
          int row = col + 1, jx = incx < 0 ? (n - (col + 1) - 1) * -incx : (col + 1) * incx, jy = incy < 0 ? (n - (col + 1) - 1) * -incy : (col + 1) * incy;
          for (; row < n; row += 1, jx += incx, jy += incy) {
            y[offsety + jy] += alphaxix * a[offseta + row + col * (2 * n - col - 1) / 2];
            sumiy += x[offsetx + jx] * a[offseta + row + col * (2 * n - col - 1) / 2];
          }
          y[offsety + iy] += alpha * sumiy;
        }
      }
    }
  }

  public void sspmv(String uplo, int n, float alpha, float[] a, float[] x, int incx, float beta, float[] y, int incy) {
    sspmv(uplo, n, alpha, a, 0, x, 0, incx, beta, y, 0, incy);
  }

  public void sspmv(String uplo, int n, float alpha, float[] a, int offseta, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    if (!lsame("U", uplo) && !lsame("L", uplo)) {
      throw illegalArgument("SSPMV", 1);
    }
    if (n < 0) {
      throw illegalArgument("SSPMV", 2);
    }
    if (incx == 0) {
      throw illegalArgument("SSPMV", 6);
    }
    if (incy == 0) {
      throw illegalArgument("SSPMV", 9);
    }
    if (n == 0) {
      return;
    }
    // y = beta * y
    if (beta != 1.0) {
      sscal(n, beta, y, offsety, incy);
    }
    // y += alpha * A * x
    if (alpha != 0.0) {
      if (lsame("U", uplo)) {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0, iy = incy < 0 ? (n - 1) * -incy : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4, iy += incy * 4) {
          float alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          float alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          float alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          float alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          float sumiy0 = 0.0f;
          float sumiy1 = 0.0f;
          float sumiy2 = 0.0f;
          float sumiy3 = 0.0f;
          int row = 0, jx = incx < 0 ? (col - 1) * -incx : 0, jy = incy < 0 ? (col - 1) * -incy : 0;
          for (; row < col; row += 1, jx += incx, jy += incy) {
            float a0 = a[offseta + row + (col + 0) * ((col + 0) + 1) / 2];
            float a1 = a[offseta + row + (col + 1) * ((col + 1) + 1) / 2];
            float a2 = a[offseta + row + (col + 2) * ((col + 2) + 1) / 2];
            float a3 = a[offseta + row + (col + 3) * ((col + 3) + 1) / 2];
            y[offsety + jy] += alphaxix0 * a0
                            +  alphaxix1 * a1
                            +  alphaxix2 * a2
                            +  alphaxix3 * a3;
            float xjx = x[offsetx + jx];
            sumiy0 += xjx * a0;
            sumiy1 += xjx * a1;
            sumiy2 += xjx * a2;
            sumiy3 += xjx * a3;
          }
          float a00 = a[offseta + (row + 0) + (col + 0) * ((col + 0) + 1) / 2];
          float a01 = a[offseta + (row + 0) + (col + 1) * ((col + 1) + 1) / 2];
          float a02 = a[offseta + (row + 0) + (col + 2) * ((col + 2) + 1) / 2];
          float a03 = a[offseta + (row + 0) + (col + 3) * ((col + 3) + 1) / 2];
          float a11 = a[offseta + (row + 1) + (col + 1) * ((col + 1) + 1) / 2];
          float a12 = a[offseta + (row + 1) + (col + 2) * ((col + 2) + 1) / 2];
          float a13 = a[offseta + (row + 1) + (col + 3) * ((col + 3) + 1) / 2];
          float a22 = a[offseta + (row + 2) + (col + 2) * ((col + 2) + 1) / 2];
          float a23 = a[offseta + (row + 2) + (col + 3) * ((col + 3) + 1) / 2];
          float a33 = a[offseta + (row + 3) + (col + 3) * ((col + 3) + 1) / 2];
          float xjx0 = x[offsetx + jx + incx * 0];
          float xjx1 = x[offsetx + jx + incx * 1];
          float xjx2 = x[offsetx + jx + incx * 2];
          float xjx3 = x[offsetx + jx + incx * 3];
          sumiy0 += xjx0 * a00
                 +  xjx1 * a01
                 +  xjx2 * a02
                 +  xjx3 * a03;
          sumiy1 += xjx0 * a01
                 +  xjx1 * a11
                 +  xjx2 * a12
                 +  xjx3 * a13;
          sumiy2 += xjx0 * a02
                 +  xjx1 * a12
                 +  xjx2 * a22
                 +  xjx3 * a23;
          sumiy3 += xjx0 * a03
                 +  xjx1 * a13
                 +  xjx2 * a23
                 +  xjx3 * a33;
          y[offsety + iy + incy * 0] += alpha * sumiy0;
          y[offsety + iy + incy * 1] += alpha * sumiy1;
          y[offsety + iy + incy * 2] += alpha * sumiy2;
          y[offsety + iy + incy * 3] += alpha * sumiy3;
        }
        for (; col < n; col += 1, ix += incx, iy += incy) {
          float alphaxix = alpha * x[offsetx + ix];
          float sumiy = 0.0f;
          int row = 0, jx = incx < 0 ? (col - 1) * -incx : 0, jy = incy < 0 ? (col - 1) * -incy : 0;
          for (; row < col; row += 1, jx += incx, jy += incy) {
            y[offsety + jy] += alphaxix * a[offseta + row + col * (col + 1) / 2];
            sumiy += x[offsetx + jx] * a[offseta + row + col * (col + 1) / 2];
          }
          y[offsety + iy] += alpha * (sumiy + x[offsetx + jx] * a[offseta + row + col * (col + 1) / 2]);
        }
      } else {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0, iy = incy < 0 ? (n - 1) * -incy : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4, iy += incy * 4) {
          float alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          float alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          float alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          float alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          float sumiy0 = 0.0f;
          float sumiy1 = 0.0f;
          float sumiy2 = 0.0f;
          float sumiy3 = 0.0f;
          float a00 = a[offseta + /*row=*/(col + 0) + (col + 0) * (2 * n - (col + 0) - 1) / 2];
          float a10 = a[offseta + /*row=*/(col + 1) + (col + 0) * (2 * n - (col + 0) - 1) / 2];
          float a11 = a[offseta + /*row=*/(col + 1) + (col + 1) * (2 * n - (col + 1) - 1) / 2];
          float a20 = a[offseta + /*row=*/(col + 2) + (col + 0) * (2 * n - (col + 0) - 1) / 2];
          float a21 = a[offseta + /*row=*/(col + 2) + (col + 1) * (2 * n - (col + 1) - 1) / 2];
          float a22 = a[offseta + /*row=*/(col + 2) + (col + 2) * (2 * n - (col + 2) - 1) / 2];
          float a30 = a[offseta + /*row=*/(col + 3) + (col + 0) * (2 * n - (col + 0) - 1) / 2];
          float a31 = a[offseta + /*row=*/(col + 3) + (col + 1) * (2 * n - (col + 1) - 1) / 2];
          float a32 = a[offseta + /*row=*/(col + 3) + (col + 2) * (2 * n - (col + 2) - 1) / 2];
          float a33 = a[offseta + /*row=*/(col + 3) + (col + 3) * (2 * n - (col + 3) - 1) / 2];
          float x0 = x[offsetx + (incx < 0 ? (n - (col + 0) - 1) * -incx : (col + 0) * incx)];
          float x1 = x[offsetx + (incx < 0 ? (n - (col + 1) - 1) * -incx : (col + 1) * incx)];
          float x2 = x[offsetx + (incx < 0 ? (n - (col + 2) - 1) * -incx : (col + 2) * incx)];
          float x3 = x[offsetx + (incx < 0 ? (n - (col + 3) - 1) * -incx : (col + 3) * incx)];
          sumiy0 += x0 * a00
                 +  x1 * a10
                 +  x2 * a20
                 +  x3 * a30;
          sumiy1 += x0 * a10
                 +  x1 * a11
                 +  x2 * a21
                 +  x3 * a31;
          sumiy2 += x0 * a20
                 +  x1 * a21
                 +  x2 * a22
                 +  x3 * a32;
          sumiy3 += x0 * a30
                 +  x1 * a31
                 +  x2 * a32
                 +  x3 * a33;
          int row = col + 4, jx = incx < 0 ? (n - (col + 4) - 1) * -incx : (col + 4) * incx, jy = incy < 0 ? (n - (col + 4) - 1) * -incy : (col + 4) * incy;
          for (; row < n; row += 1, jx += incx, jy += incy) {
            float a0 = a[offseta + row + (col + 0) * (2 * n - (col + 0) - 1) / 2];
            float a1 = a[offseta + row + (col + 1) * (2 * n - (col + 1) - 1) / 2];
            float a2 = a[offseta + row + (col + 2) * (2 * n - (col + 2) - 1) / 2];
            float a3 = a[offseta + row + (col + 3) * (2 * n - (col + 3) - 1) / 2];
            y[offsety + jy] += alphaxix0 * a0
                            +  alphaxix1 * a1
                            +  alphaxix2 * a2
                            +  alphaxix3 * a3;
            float xjx = x[offsetx + jx];
            sumiy0 += xjx * a0;
            sumiy1 += xjx * a1;
            sumiy2 += xjx * a2;
            sumiy3 += xjx * a3;
          }
          y[offsety + iy + incy * 0] += alpha * sumiy0;
          y[offsety + iy + incy * 1] += alpha * sumiy1;
          y[offsety + iy + incy * 2] += alpha * sumiy2;
          y[offsety + iy + incy * 3] += alpha * sumiy3;
        }
        for (; col < n; col += 1, ix += incx, iy += incy) {
          float alphaxix = alpha * x[offsetx + ix];
          float sumiy = 0.0f;
          sumiy += x[offsetx + (incx < 0 ? (n - col - 1) * -incx : col * incx)] * a[offseta + /*row=*/col + col * (2 * n - col - 1) / 2];
          int row = col + 1, jx = incx < 0 ? (n - (col + 1) - 1) * -incx : (col + 1) * incx, jy = incy < 0 ? (n - (col + 1) - 1) * -incy : (col + 1) * incy;
          for (; row < n; row += 1, jx += incx, jy += incy) {
            y[offsety + jy] += alphaxix * a[offseta + row + col * (2 * n - col - 1) / 2];
            sumiy += x[offsetx + jx] * a[offseta + row + col * (2 * n - col - 1) / 2];
          }
          y[offsety + iy] += alpha * sumiy;
        }
      }
    }
  }

  public void dspr(String uplo, int n, double alpha, double[] x, int incx, double[] a) {
    dspr(uplo, n, alpha, x, 0, incx, a, 0);
  }

  // a += alpha * x * x.t
  public void dspr(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta) {
    if (!lsame("U", uplo) && !lsame("L", uplo)) {
      throw illegalArgument("DSPR", 1);
    }
    if (n < 0) {
      throw illegalArgument("DSPR", 2);
    }
    if (incx == 0) {
      throw illegalArgument("DSPR", 5);
    }
    if (n == 0) {
      return;
    }
    if (alpha != 0.0) {
      if (lsame("U", uplo)) {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4) {
          double alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          double alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          double alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          double alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          int row = 0, jx = incx < 0 ? col * -incx : 0;
          for (; row < col + 1; row += 1, jx += incx) {
            double xjx = x[offsetx + jx];
            a[offseta + row + (col + 0) * ((col + 0) + 1) / 2] += alphaxix0 * xjx;
            a[offseta + row + (col + 1) * ((col + 1) + 1) / 2] += alphaxix1 * xjx;
            a[offseta + row + (col + 2) * ((col + 2) + 1) / 2] += alphaxix2 * xjx;
            a[offseta + row + (col + 3) * ((col + 3) + 1) / 2] += alphaxix3 * xjx;
          }
          double xjx0 = x[offsetx + jx + incx * 0];
          a[offseta + (row + 0) + (col + 1) * ((col + 1) + 1) / 2] += alphaxix1 * xjx0;
          a[offseta + (row + 0) + (col + 2) * ((col + 2) + 1) / 2] += alphaxix2 * xjx0;
          a[offseta + (row + 0) + (col + 3) * ((col + 3) + 1) / 2] += alphaxix3 * xjx0;
          double xjx1 = x[offsetx + jx + incx * 1];
          a[offseta + (row + 1) + (col + 2) * ((col + 2) + 1) / 2] += alphaxix2 * xjx1;
          a[offseta + (row + 1) + (col + 3) * ((col + 3) + 1) / 2] += alphaxix3 * xjx1;
          double xjx2 = x[offsetx + jx + incx * 2];
          a[offseta + (row + 2) + (col + 3) * ((col + 3) + 1) / 2] += alphaxix3 * xjx2;
        }
        for (; col < n; col += 1, ix += incx) {
          double alphaxix = alpha * x[offsetx + ix];
          int row = 0, jx = incx < 0 ? col * -incx : 0;
          for (; row < col + 1; row += 1, jx += incx) {
            a[offseta + row + col * (col + 1) / 2] += alphaxix * x[offsetx + jx];
          }
        }
      } else {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4) {
          double alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          double alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          double alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          double alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          double xjx0 = x[offsetx + (incx < 0 ? (n - (col + 0) - 1) * -incx : (col + 0) * incx)];
          a[offseta + /*row=*/(col + 0) + (col + 0) * (2 * n - (col + 0) - 1) / 2] += alphaxix0 * xjx0;
          double xjx1 = x[offsetx + (incx < 0 ? (n - (col + 1) - 1) * -incx : (col + 1) * incx)];
          a[offseta + /*row=*/(col + 1) + (col + 0) * (2 * n - (col + 0) - 1) / 2] += alphaxix0 * xjx1;
          a[offseta + /*row=*/(col + 1) + (col + 1) * (2 * n - (col + 1) - 1) / 2] += alphaxix1 * xjx1;
          double xjx2 = x[offsetx + (incx < 0 ? (n - (col + 2) - 1) * -incx : (col + 2) * incx)];
          a[offseta + /*row=*/(col + 2) + (col + 0) * (2 * n - (col + 0) - 1) / 2] += alphaxix0 * xjx2;
          a[offseta + /*row=*/(col + 2) + (col + 1) * (2 * n - (col + 1) - 1) / 2] += alphaxix1 * xjx2;
          a[offseta + /*row=*/(col + 2) + (col + 2) * (2 * n - (col + 2) - 1) / 2] += alphaxix2 * xjx2;
          int row = col + (4 - 1), jx = incx < 0 ? (n - (col + (4 - 1)) - 1) * -incx : (col + (4 - 1)) * incx;
          for (; row < n; row += 1, jx += incx) {
            double xjx = x[offsetx + jx];
            a[offseta + row + (col + 0) * (2 * n - (col + 0) - 1) / 2] += alphaxix0 * xjx;
            a[offseta + row + (col + 1) * (2 * n - (col + 1) - 1) / 2] += alphaxix1 * xjx;
            a[offseta + row + (col + 2) * (2 * n - (col + 2) - 1) / 2] += alphaxix2 * xjx;
            a[offseta + row + (col + 3) * (2 * n - (col + 3) - 1) / 2] += alphaxix3 * xjx;
          }
        }
        for (; col < n; col += 1, ix += incx) {
          double alphaxix = alpha * x[offsetx + ix];
          int row = col + 1, jx = incx < 0 ? (n - (col + 1) - 1) * -incx : (col + 1) * incx;
          for (; row < n; row += 1, jx += incx) {
            a[offseta + row + col * (2 * n - col - 1) / 2] += alphaxix * x[offsetx + jx];
          }
        }
      }
    }
  }

  public void sspr(String uplo, int n, float alpha, float[] x, int incx, float[] a) {
    sspr(uplo, n, alpha, x, 0, incx, a, 0);
  }

  public void sspr(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta) {
    if (!lsame("U", uplo) && !lsame("L", uplo)) {
      throw illegalArgument("SSPR", 1);
    }
    if (n < 0) {
      throw illegalArgument("SSPR", 2);
    }
    if (incx == 0) {
      throw illegalArgument("SSPR", 5);
    }
    if (n == 0) {
      return;
    }
    // y += alpha * x * x.t
    if (alpha != 0.0) {
      if (lsame("U", uplo)) {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4) {
          float alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          float alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          float alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          float alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          int row = 0, jx = incx < 0 ? col * -incx : 0;
          for (; row < col + 1; row += 1, jx += incx) {
            float xjx = x[offsetx + jx];
            a[offseta + row + (col + 0) * ((col + 0) + 1) / 2] += alphaxix0 * xjx;
            a[offseta + row + (col + 1) * ((col + 1) + 1) / 2] += alphaxix1 * xjx;
            a[offseta + row + (col + 2) * ((col + 2) + 1) / 2] += alphaxix2 * xjx;
            a[offseta + row + (col + 3) * ((col + 3) + 1) / 2] += alphaxix3 * xjx;
          }
          float xjx0 = x[offsetx + jx + incx * 0];
          a[offseta + (row + 0) + (col + 1) * ((col + 1) + 1) / 2] += alphaxix1 * xjx0;
          a[offseta + (row + 0) + (col + 2) * ((col + 2) + 1) / 2] += alphaxix2 * xjx0;
          a[offseta + (row + 0) + (col + 3) * ((col + 3) + 1) / 2] += alphaxix3 * xjx0;
          float xjx1 = x[offsetx + jx + incx * 1];
          a[offseta + (row + 1) + (col + 2) * ((col + 2) + 1) / 2] += alphaxix2 * xjx1;
          a[offseta + (row + 1) + (col + 3) * ((col + 3) + 1) / 2] += alphaxix3 * xjx1;
          float xjx2 = x[offsetx + jx + incx * 2];
          a[offseta + (row + 2) + (col + 3) * ((col + 3) + 1) / 2] += alphaxix3 * xjx2;
        }
        for (; col < n; col += 1, ix += incx) {
          float alphaxix = alpha * x[offsetx + ix];
          int row = 0, jx = incx < 0 ? col * -incx : 0;
          for (; row < col + 1; row += 1, jx += incx) {
            a[offseta + row + col * (col + 1) / 2] += alphaxix * x[offsetx + jx];
          }
        }
      } else {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4) {
          float alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          float alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          float alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          float alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          float xjx0 = x[offsetx + (incx < 0 ? (n - (col + 0) - 1) * -incx : (col + 0) * incx)];
          a[offseta + /*row=*/(col + 0) + (col + 0) * (2 * n - (col + 0) - 1) / 2] += alphaxix0 * xjx0;
          float xjx1 = x[offsetx + (incx < 0 ? (n - (col + 1) - 1) * -incx : (col + 1) * incx)];
          a[offseta + /*row=*/(col + 1) + (col + 0) * (2 * n - (col + 0) - 1) / 2] += alphaxix0 * xjx1;
          a[offseta + /*row=*/(col + 1) + (col + 1) * (2 * n - (col + 1) - 1) / 2] += alphaxix1 * xjx1;
          float xjx2 = x[offsetx + (incx < 0 ? (n - (col + 2) - 1) * -incx : (col + 2) * incx)];
          a[offseta + /*row=*/(col + 2) + (col + 0) * (2 * n - (col + 0) - 1) / 2] += alphaxix0 * xjx2;
          a[offseta + /*row=*/(col + 2) + (col + 1) * (2 * n - (col + 1) - 1) / 2] += alphaxix1 * xjx2;
          a[offseta + /*row=*/(col + 2) + (col + 2) * (2 * n - (col + 2) - 1) / 2] += alphaxix2 * xjx2;
          int row = col + (4 - 1), jx = incx < 0 ? (n - (col + (4 - 1)) - 1) * -incx : (col + (4 - 1)) * incx;
          for (; row < n; row += 1, jx += incx) {
            float xjx = x[offsetx + jx];
            a[offseta + row + (col + 0) * (2 * n - (col + 0) - 1) / 2] += alphaxix0 * xjx;
            a[offseta + row + (col + 1) * (2 * n - (col + 1) - 1) / 2] += alphaxix1 * xjx;
            a[offseta + row + (col + 2) * (2 * n - (col + 2) - 1) / 2] += alphaxix2 * xjx;
            a[offseta + row + (col + 3) * (2 * n - (col + 3) - 1) / 2] += alphaxix3 * xjx;
          }
        }
        for (; col < n; col += 1, ix += incx) {
          float alphaxix = alpha * x[offsetx + ix];
          int row = col + 1, jx = incx < 0 ? (n - (col + 1) - 1) * -incx : (col + 1) * incx;
          for (; row < n; row += 1, jx += incx) {
            a[offseta + row + col * (2 * n - col - 1) / 2] += alphaxix * x[offsetx + jx];
          }
        }
      }
    }
  }

  public void dspr2(String uplo, int n, double alpha, double[] x, int incx, double[] y, int incy, double[] a) {
    dspr2(uplo, n, alpha, x, 0, incx, y, 0, incy, a, 0);
  }

  // a += alpha * x * y.t + alpha * y * x.t
  public void dspr2(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta) {
    if (!lsame("U", uplo) && !lsame("L", uplo)) {
      throw illegalArgument("DSPR2", 1);
    }
    if (n < 0) {
      throw illegalArgument("DSPR2", 2);
    }
    if (incx == 0) {
      throw illegalArgument("DSPR2", 5);
    }
    if (incy == 0) {
      throw illegalArgument("DSPR2", 7);
    }
    if (n == 0) {
      return;
    }
    if (alpha != 0.0) {
      if (lsame("U", uplo)) {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0, iy = incy < 0 ? (n - 1) * -incy : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4, iy += incy * 4) {
          double alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          double alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          double alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          double alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          double alphayiy0 = alpha * y[offsety + iy + incy * 0];
          double alphayiy1 = alpha * y[offsety + iy + incy * 1];
          double alphayiy2 = alpha * y[offsety + iy + incy * 2];
          double alphayiy3 = alpha * y[offsety + iy + incy * 3];
          int row = 0, jx = incx < 0 ? col * -incx : 0, jy = incy < 0 ? (n - 1) * -incy : 0;
          for (; row < col + 1; row += 1, jx += incx, jy += incy) {
            double xjx = x[offsetx + jx];
            double yjy = y[offsety + jy];
            a[offseta + row + (col + 0) * ((col + 0) + 1) / 2] += alphaxix0 * yjy + alphayiy0 * xjx;
            a[offseta + row + (col + 1) * ((col + 1) + 1) / 2] += alphaxix1 * yjy + alphayiy1 * xjx;
            a[offseta + row + (col + 2) * ((col + 2) + 1) / 2] += alphaxix2 * yjy + alphayiy2 * xjx;
            a[offseta + row + (col + 3) * ((col + 3) + 1) / 2] += alphaxix3 * yjy + alphayiy3 * xjx;
          }
          double xjx0 = x[offsetx + jx + incx * 0];
          double yjy0 = y[offsety + jy + incy * 0];
          a[offseta + (row + 0) + (col + 1) * ((col + 1) + 1) / 2] += alphaxix1 * yjy0 + alphayiy1 * xjx0;
          a[offseta + (row + 0) + (col + 2) * ((col + 2) + 1) / 2] += alphaxix2 * yjy0 + alphayiy2 * xjx0;
          a[offseta + (row + 0) + (col + 3) * ((col + 3) + 1) / 2] += alphaxix3 * yjy0 + alphayiy3 * xjx0;
          double xjx1 = x[offsetx + jx + incx * 1];
          double yjy1 = y[offsety + jy + incy * 1];
          a[offseta + (row + 1) + (col + 2) * ((col + 2) + 1) / 2] += alphaxix2 * yjy1 + alphayiy2 * xjx1;
          a[offseta + (row + 1) + (col + 3) * ((col + 3) + 1) / 2] += alphaxix3 * yjy1 + alphayiy3 * xjx1;
          double xjx2 = x[offsetx + jx + incx * 2];
          double yjy2 = y[offsety + jy + incy * 2];
          a[offseta + (row + 2) + (col + 3) * ((col + 3) + 1) / 2] += alphaxix3 * yjy2 + alphayiy3 * xjx2;
        }
        for (; col < n; col += 1, ix += incx, iy += incy) {
          double alphaxix = alpha * x[offsetx + ix];
          double alphayiy = alpha * y[offsety + iy];
          int row = 0, jx = incx < 0 ? col * -incx : 0, jy = incy < 0 ? (n - 1) * -incy : 0;
          for (; row < col + 1; row += 1, jx += incx, jy += incy) {
            a[offseta + row + col * (col + 1) / 2] += alphaxix * y[offsety + jy] + alphayiy * x[offsetx + jx];
          }
        }
      } else {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0, iy = incy < 0 ? (n - 1) * -incy : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4, iy += incy * 4) {
          double alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          double alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          double alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          double alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          double alphayiy0 = alpha * y[offsety + iy + incy * 0];
          double alphayiy1 = alpha * y[offsety + iy + incy * 1];
          double alphayiy2 = alpha * y[offsety + iy + incy * 2];
          double alphayiy3 = alpha * y[offsety + iy + incy * 3];
          double xjx0 = x[offsetx + (incx < 0 ? (n - (col + 0) - 1) * -incx : (col + 0) * incx)];
          double yjy0 = y[offsety + (incy < 0 ? (n - (col + 0) - 1) * -incy : (col + 0) * incy)];
          a[offseta + /*row=*/(col + 0) + (col + 0) * (2 * n - (col + 0) - 1) / 2] += alphaxix0 * yjy0 + alphayiy0 * xjx0;
          double xjx1 = x[offsetx + (incx < 0 ? (n - (col + 1) - 1) * -incx : (col + 1) * incx)];
          double yjy1 = y[offsety + (incy < 0 ? (n - (col + 1) - 1) * -incy : (col + 1) * incy)];
          a[offseta + /*row=*/(col + 1) + (col + 0) * (2 * n - (col + 0) - 1) / 2] += alphaxix0 * yjy1 + alphayiy0 * xjx1;
          a[offseta + /*row=*/(col + 1) + (col + 1) * (2 * n - (col + 1) - 1) / 2] += alphaxix1 * yjy1 + alphayiy1 * xjx1;
          double xjx2 = x[offsetx + (incx < 0 ? (n - (col + 2) - 1) * -incx : (col + 2) * incx)];
          double yjy2 = y[offsety + (incy < 0 ? (n - (col + 2) - 1) * -incy : (col + 2) * incy)];
          a[offseta + /*row=*/(col + 2) + (col + 0) * (2 * n - (col + 0) - 1) / 2] += alphaxix0 * yjy2 + alphayiy0 * xjx2;
          a[offseta + /*row=*/(col + 2) + (col + 1) * (2 * n - (col + 1) - 1) / 2] += alphaxix1 * yjy2 + alphayiy1 * xjx2;
          a[offseta + /*row=*/(col + 2) + (col + 2) * (2 * n - (col + 2) - 1) / 2] += alphaxix2 * yjy2 + alphayiy2 * xjx2;
          int row = col + (4 - 1), jx = incx < 0 ? (n - (col + (4 - 1)) - 1) * -incx : (col + (4 - 1)) * incx, jy = incy < 0 ? (n - (col + (4 - 1)) - 1) * -incy : (col + (4 - 1)) * incy;
          for (; row < n; row += 1, jx += incx) {
            double xjx = x[offsetx + jx];
            double yjy = y[offsety + jy];
            a[offseta + row + (col + 0) * (2 * n - (col + 0) - 1) / 2] += alphaxix0 * yjy + alphayiy0 * xjx;
            a[offseta + row + (col + 1) * (2 * n - (col + 1) - 1) / 2] += alphaxix1 * yjy + alphayiy1 * xjx;
            a[offseta + row + (col + 2) * (2 * n - (col + 2) - 1) / 2] += alphaxix2 * yjy + alphayiy2 * xjx;
            a[offseta + row + (col + 3) * (2 * n - (col + 3) - 1) / 2] += alphaxix3 * yjy + alphayiy3 * xjx;
          }
        }
        for (; col < n; col += 1, ix += incx, iy += incy) {
          double alphaxix = alpha * x[offsetx + ix];
          double alphayiy = alpha * y[offsety + iy];
          int row = col + 1, jx = incx < 0 ? (n - (col + 1) - 1) * -incx : (col + 1) * incx, jy = incy < 0 ? (n - (col + (4 - 1)) - 1) * -incy : (col + (4 - 1)) * incy;
          for (; row < col + 1; row += 1, jx += incx, jy += incy) {
            a[offseta + row + col * (2 * n - col - 1) / 2] += alphaxix * y[offsety + jy] + alphayiy * x[offsetx + jx];
          }
        }
      }
    }
  }

  public void sspr2(String uplo, int n, float alpha, float[] x, int incx, float[] y, int incy, float[] a) {
    sspr2(uplo, n, alpha, x, 0, incx, y, 0, incy, a, 0);
  }

  public void sspr2(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta) {
    if (!lsame("U", uplo) && !lsame("L", uplo)) {
      throw illegalArgument("SSPR2", 1);
    }
    if (n < 0) {
      throw illegalArgument("SSPR2", 2);
    }
    if (incx == 0) {
      throw illegalArgument("SSPR2", 5);
    }
    if (incy == 0) {
      throw illegalArgument("SSPR2", 7);
    }
    if (n == 0) {
      return;
    }
    if (alpha != 0.0) {
      if (lsame("U", uplo)) {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0, iy = incy < 0 ? (n - 1) * -incy : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4, iy += incy * 4) {
          float alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          float alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          float alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          float alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          float alphayiy0 = alpha * y[offsety + iy + incy * 0];
          float alphayiy1 = alpha * y[offsety + iy + incy * 1];
          float alphayiy2 = alpha * y[offsety + iy + incy * 2];
          float alphayiy3 = alpha * y[offsety + iy + incy * 3];
          int row = 0, jx = incx < 0 ? col * -incx : 0, jy = incy < 0 ? (n - 1) * -incy : 0;
          for (; row < col + 1; row += 1, jx += incx, jy += incy) {
            float xjx = x[offsetx + jx];
            float yjy = y[offsety + jy];
            a[offseta + row + (col + 0) * ((col + 0) + 1) / 2] += alphaxix0 * yjy + alphayiy0 * xjx;
            a[offseta + row + (col + 1) * ((col + 1) + 1) / 2] += alphaxix1 * yjy + alphayiy1 * xjx;
            a[offseta + row + (col + 2) * ((col + 2) + 1) / 2] += alphaxix2 * yjy + alphayiy2 * xjx;
            a[offseta + row + (col + 3) * ((col + 3) + 1) / 2] += alphaxix3 * yjy + alphayiy3 * xjx;
          }
          float xjx0 = x[offsetx + jx + incx * 0];
          float yjy0 = y[offsety + jy + incy * 0];
          a[offseta + (row + 0) + (col + 1) * ((col + 1) + 1) / 2] += alphaxix1 * yjy0 + alphayiy1 * xjx0;
          a[offseta + (row + 0) + (col + 2) * ((col + 2) + 1) / 2] += alphaxix2 * yjy0 + alphayiy2 * xjx0;
          a[offseta + (row + 0) + (col + 3) * ((col + 3) + 1) / 2] += alphaxix3 * yjy0 + alphayiy3 * xjx0;
          float xjx1 = x[offsetx + jx + incx * 1];
          float yjy1 = y[offsety + jy + incy * 1];
          a[offseta + (row + 1) + (col + 2) * ((col + 2) + 1) / 2] += alphaxix2 * yjy1 + alphayiy2 * xjx1;
          a[offseta + (row + 1) + (col + 3) * ((col + 3) + 1) / 2] += alphaxix3 * yjy1 + alphayiy3 * xjx1;
          float xjx2 = x[offsetx + jx + incx * 2];
          float yjy2 = y[offsety + jy + incy * 2];
          a[offseta + (row + 2) + (col + 3) * ((col + 3) + 1) / 2] += alphaxix3 * yjy2 + alphayiy3 * xjx2;
        }
        for (; col < n; col += 1, ix += incx, iy += incy) {
          float alphaxix = alpha * x[offsetx + ix];
          float alphayiy = alpha * y[offsety + iy];
          int row = 0, jx = incx < 0 ? col * -incx : 0, jy = incy < 0 ? (n - 1) * -incy : 0;
          for (; row < col + 1; row += 1, jx += incx, jy += incy) {
            a[offseta + row + col * (col + 1) / 2] += alphaxix * y[offsety + jy] + alphayiy * x[offsetx + jx];
          }
        }
      } else {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0, iy = incy < 0 ? (n - 1) * -incy : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4, iy += incy * 4) {
          float alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          float alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          float alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          float alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          float alphayiy0 = alpha * y[offsety + iy + incy * 0];
          float alphayiy1 = alpha * y[offsety + iy + incy * 1];
          float alphayiy2 = alpha * y[offsety + iy + incy * 2];
          float alphayiy3 = alpha * y[offsety + iy + incy * 3];
          float xjx0 = x[offsetx + (incx < 0 ? (n - (col + 0) - 1) * -incx : (col + 0) * incx)];
          float yjy0 = y[offsety + (incy < 0 ? (n - (col + 0) - 1) * -incy : (col + 0) * incy)];
          a[offseta + /*row=*/(col + 0) + (col + 0) * (2 * n - (col + 0) - 1) / 2] += alphaxix0 * yjy0 + alphayiy0 * xjx0;
          float xjx1 = x[offsetx + (incx < 0 ? (n - (col + 1) - 1) * -incx : (col + 1) * incx)];
          float yjy1 = y[offsety + (incy < 0 ? (n - (col + 1) - 1) * -incy : (col + 1) * incy)];
          a[offseta + /*row=*/(col + 1) + (col + 0) * (2 * n - (col + 0) - 1) / 2] += alphaxix0 * yjy1 + alphayiy0 * xjx1;
          a[offseta + /*row=*/(col + 1) + (col + 1) * (2 * n - (col + 1) - 1) / 2] += alphaxix1 * yjy1 + alphayiy1 * xjx1;
          float xjx2 = x[offsetx + (incx < 0 ? (n - (col + 2) - 1) * -incx : (col + 2) * incx)];
          float yjy2 = y[offsety + (incy < 0 ? (n - (col + 2) - 1) * -incy : (col + 2) * incy)];
          a[offseta + /*row=*/(col + 2) + (col + 0) * (2 * n - (col + 0) - 1) / 2] += alphaxix0 * yjy2 + alphayiy0 * xjx2;
          a[offseta + /*row=*/(col + 2) + (col + 1) * (2 * n - (col + 1) - 1) / 2] += alphaxix1 * yjy2 + alphayiy1 * xjx2;
          a[offseta + /*row=*/(col + 2) + (col + 2) * (2 * n - (col + 2) - 1) / 2] += alphaxix2 * yjy2 + alphayiy2 * xjx2;
          int row = col + (4 - 1), jx = incx < 0 ? (n - (col + (4 - 1)) - 1) * -incx : (col + (4 - 1)) * incx, jy = incy < 0 ? (n - (col + (4 - 1)) - 1) * -incy : (col + (4 - 1)) * incy;
          for (; row < n; row += 1, jx += incx) {
            float xjx = x[offsetx + jx];
            float yjy = y[offsety + jy];
            a[offseta + row + (col + 0) * (2 * n - (col + 0) - 1) / 2] += alphaxix0 * yjy + alphayiy0 * xjx;
            a[offseta + row + (col + 1) * (2 * n - (col + 1) - 1) / 2] += alphaxix1 * yjy + alphayiy1 * xjx;
            a[offseta + row + (col + 2) * (2 * n - (col + 2) - 1) / 2] += alphaxix2 * yjy + alphayiy2 * xjx;
            a[offseta + row + (col + 3) * (2 * n - (col + 3) - 1) / 2] += alphaxix3 * yjy + alphayiy3 * xjx;
          }
        }
        for (; col < n; col += 1, ix += incx, iy += incy) {
          float alphaxix = alpha * x[offsetx + ix];
          float alphayiy = alpha * y[offsety + iy];
          int row = col + 1, jx = incx < 0 ? (n - (col + 1) - 1) * -incx : (col + 1) * incx, jy = incy < 0 ? (n - (col + (4 - 1)) - 1) * -incy : (col + (4 - 1)) * incy;
          for (; row < col + 1; row += 1, jx += incx, jy += incy) {
            a[offseta + row + col * (2 * n - col - 1) / 2] += alphaxix * y[offsety + jy] + alphayiy * x[offsetx + jx];
          }
        }
      }
    }
  }

  public void dswap(int n, double[] x, int incx, double[] y, int incy) {
    dswap(n, x, 0, incx, y, 0, incy);
  }

  public void dswap(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (n <= 0) {
      return;
    }
    for (int ix = incx < 0 ? (n - 1) * -incx : 0,
             iy = incy < 0 ? (n - 1) * -incy : 0;
         (incx < 0 ? ix >= 0 : ix < n * incx)
          && (incy < 0 ? iy >= 0 : iy < n * incy);
         ix += incx, iy += incy) {
      double tmp = y[offsety + iy];
      y[offsety + iy] = x[offsetx + ix];
      x[offsetx + ix] = tmp;
    }
  }

  public void sswap(int n, float[] x, int incx, float[] y, int incy) {
    sswap(n, x, 0, incx, y, 0, incy);
  }

  public void sswap(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (n <= 0) {
      return;
    }
    for (int ix = incx < 0 ? (n - 1) * -incx : 0,
             iy = incy < 0 ? (n - 1) * -incy : 0;
         (incx < 0 ? ix >= 0 : ix < n * incx)
          && (incy < 0 ? iy >= 0 : iy < n * incy);
         ix += incx, iy += incy) {
      float tmp = y[offsety + iy];
      y[offsety + iy] = x[offsetx + ix];
      x[offsetx + ix] = tmp;
    }
  }

  public void dsymm(String side, String uplo, int m, int n, double alpha, double[] a, int lda, double[] b, int ldb, double beta, double[] c, int ldc) {
    dsymm(side, uplo, m, n, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
  }

  public void dsymm(String side, String uplo, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    f2j.dsymm(side, uplo, m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  public void ssymm(String side, String uplo, int m, int n, float alpha, float[] a, int lda, float[] b, int ldb, float beta, float[] c, int ldc) {
    ssymm(side, uplo, m, n, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
  }

  public void ssymm(String side, String uplo, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    f2j.ssymm(side, uplo, m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  public void dsymv(String uplo, int n, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    dsymv(uplo, n, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void dsymv(String uplo, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    if (!lsame("U", uplo) && !lsame("L", uplo)) {
      throw illegalArgument("DSYMV", 1);
    }
    if (n < 0) {
      throw illegalArgument("DSYMV", 2);
    }
    if (lda < Math.max(1, n)) {
      throw illegalArgument("DSYMV", 5);
    }
    if (incx == 0) {
      throw illegalArgument("DSYMV", 7);
    }
    if (incy == 0) {
      throw illegalArgument("DSYMV", 10);
    }
    if (n == 0) {
      return;
    }
    // y = beta * y
    if (beta != 1.0) {
      dscal(n, beta, y, offsety, incy);
    }
    // y += alpha * A * x
    if (alpha != 0.0) {
      if (lsame("U", uplo)) {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0, iy = incy < 0 ? (n - 1) * -incy : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4, iy += incy * 4) {
          double alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          double alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          double alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          double alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          double sumiy0 = 0.0;
          double sumiy1 = 0.0;
          double sumiy2 = 0.0;
          double sumiy3 = 0.0;
          int row = 0, jx = incx < 0 ? (col - 1) * -incx : 0, jy = incy < 0 ? (col - 1) * -incy : 0;
          for (; row < col; row += 1, jx += incx, jy += incy) {
            y[offsety + jy] += alphaxix0 * a[offseta + row + (col + 0) * lda]
                            +  alphaxix1 * a[offseta + row + (col + 1) * lda]
                            +  alphaxix2 * a[offseta + row + (col + 2) * lda]
                            +  alphaxix3 * a[offseta + row + (col + 3) * lda];
            double xjx = x[offsetx + jx];
            sumiy0 += xjx * a[offseta + row + (col + 0) * lda];
            sumiy1 += xjx * a[offseta + row + (col + 1) * lda];
            sumiy2 += xjx * a[offseta + row + (col + 2) * lda];
            sumiy3 += xjx * a[offseta + row + (col + 3) * lda];
          }
          double a00 = a[offseta + (row + 0) + (col + 0) * lda];
          double a01 = a[offseta + (row + 0) + (col + 1) * lda];
          double a02 = a[offseta + (row + 0) + (col + 2) * lda];
          double a03 = a[offseta + (row + 0) + (col + 3) * lda];
          double a11 = a[offseta + (row + 1) + (col + 1) * lda];
          double a12 = a[offseta + (row + 1) + (col + 2) * lda];
          double a13 = a[offseta + (row + 1) + (col + 3) * lda];
          double a22 = a[offseta + (row + 2) + (col + 2) * lda];
          double a23 = a[offseta + (row + 2) + (col + 3) * lda];
          double a33 = a[offseta + (row + 3) + (col + 3) * lda];
          double xjx0 = x[offsetx + jx + incx * 0];
          double xjx1 = x[offsetx + jx + incx * 1];
          double xjx2 = x[offsetx + jx + incx * 2];
          double xjx3 = x[offsetx + jx + incx * 3];
          sumiy0 += xjx0 * a00
                 +  xjx1 * a01
                 +  xjx2 * a02
                 +  xjx3 * a03;
          sumiy1 += xjx0 * a01
                 +  xjx1 * a11
                 +  xjx2 * a12
                 +  xjx3 * a13;
          sumiy2 += xjx0 * a02
                 +  xjx1 * a12
                 +  xjx2 * a22
                 +  xjx3 * a23;
          sumiy3 += xjx0 * a03
                 +  xjx1 * a13
                 +  xjx2 * a23
                 +  xjx3 * a33;
          y[offsety + iy + incy * 0] += alpha * sumiy0;
          y[offsety + iy + incy * 1] += alpha * sumiy1;
          y[offsety + iy + incy * 2] += alpha * sumiy2;
          y[offsety + iy + incy * 3] += alpha * sumiy3;
        }
        for (; col < n; col += 1, ix += incx, iy += incy) {
          double alphaxix = alpha * x[offsetx + ix];
          double sumiy = 0.0;
          int row = 0, jx = incx < 0 ? (col - 1) * -incx : 0, jy = incy < 0 ? (col - 1) * -incy : 0;
          for (; row < col; row += 1, jx += incx, jy += incy) {
            y[offsety + jy] += alphaxix * a[offseta + row + col * lda];
            sumiy += x[offsetx + jx] * a[offseta + row + col * lda];
          }
          y[offsety + iy] += alpha * (sumiy + x[offsetx + jx] * a[offseta + row + col * lda]);
        }
      } else {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0, iy = incy < 0 ? (n - 1) * -incy : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4, iy += incy * 4) {
          double alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          double alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          double alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          double alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          double sumiy0 = 0.0;
          double sumiy1 = 0.0;
          double sumiy2 = 0.0;
          double sumiy3 = 0.0;
          double a00 = a[offseta + /*row=*/(col + 0) + (col + 0) * lda];
          double a10 = a[offseta + /*row=*/(col + 1) + (col + 0) * lda];
          double a11 = a[offseta + /*row=*/(col + 1) + (col + 1) * lda];
          double a20 = a[offseta + /*row=*/(col + 2) + (col + 0) * lda];
          double a21 = a[offseta + /*row=*/(col + 2) + (col + 1) * lda];
          double a22 = a[offseta + /*row=*/(col + 2) + (col + 2) * lda];
          double a30 = a[offseta + /*row=*/(col + 3) + (col + 0) * lda];
          double a31 = a[offseta + /*row=*/(col + 3) + (col + 1) * lda];
          double a32 = a[offseta + /*row=*/(col + 3) + (col + 2) * lda];
          double a33 = a[offseta + /*row=*/(col + 3) + (col + 3) * lda];
          double x0 = x[offsetx + (incx < 0 ? (n - (col + 0) - 1) * -incx : (col + 0) * incx)];
          double x1 = x[offsetx + (incx < 0 ? (n - (col + 1) - 1) * -incx : (col + 1) * incx)];
          double x2 = x[offsetx + (incx < 0 ? (n - (col + 2) - 1) * -incx : (col + 2) * incx)];
          double x3 = x[offsetx + (incx < 0 ? (n - (col + 3) - 1) * -incx : (col + 3) * incx)];
          sumiy0 += x0 * a00
                 +  x1 * a10
                 +  x2 * a20
                 +  x3 * a30;
          sumiy1 += x0 * a10
                 +  x1 * a11
                 +  x2 * a21
                 +  x3 * a31;
          sumiy2 += x0 * a20
                 +  x1 * a21
                 +  x2 * a22
                 +  x3 * a32;
          sumiy3 += x0 * a30
                 +  x1 * a31
                 +  x2 * a32
                 +  x3 * a33;
          int row = col + 4, jx = incx < 0 ? (n - (col + 4) - 1) * -incx : (col + 4) * incx, jy = incy < 0 ? (n - (col + 4) - 1) * -incy : (col + 4) * incy;
          for (; row < n; row += 1, jx += incx, jy += incy) {
            double a0 = a[offseta + row + (col + 0) * lda];
            double a1 = a[offseta + row + (col + 1) * lda];
            double a2 = a[offseta + row + (col + 2) * lda];
            double a3 = a[offseta + row + (col + 3) * lda];
            y[offsety + jy] += alphaxix0 * a0
                            +  alphaxix1 * a1
                            +  alphaxix2 * a2
                            +  alphaxix3 * a3;
            double xjx = x[offsetx + jx];
            sumiy0 += xjx * a0;
            sumiy1 += xjx * a1;
            sumiy2 += xjx * a2;
            sumiy3 += xjx * a3;
          }
          y[offsety + iy + incy * 0] += alpha * sumiy0;
          y[offsety + iy + incy * 1] += alpha * sumiy1;
          y[offsety + iy + incy * 2] += alpha * sumiy2;
          y[offsety + iy + incy * 3] += alpha * sumiy3;
        }
        for (; col < n; col += 1, ix += incx, iy += incy) {
          double alphaxix = alpha * x[offsetx + ix];
          double sumiy = 0.0;
          sumiy += x[offsetx + (incx < 0 ? (n - col - 1) * -incx : col * incx)] * a[offseta + /*row=*/col + col * lda];
          int row = col + 1, jx = incx < 0 ? (n - (col + 1) - 1) * -incx : (col + 1) * incx, jy = incy < 0 ? (n - (col + 1) - 1) * -incy : (col + 1) * incy;
          for (; row < n; row += 1, jx += incx, jy += incy) {
            y[offsety + jy] += alphaxix * a[offseta + row + col * lda];
            sumiy += x[offsetx + jx] * a[offseta + row + col * lda];
          }
          y[offsety + iy] += alpha * sumiy;
        }
      }
    }
  }

  public void ssymv(String uplo, int n, float alpha, float[] a, int lda, float[] x, int incx, float beta, float[] y, int incy) {
    ssymv(uplo, n, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
  }

  public void ssymv(String uplo, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    if (!lsame("U", uplo) && !lsame("L", uplo)) {
      throw illegalArgument("SSYMV", 1);
    }
    if (n < 0) {
      throw illegalArgument("SSYMV", 2);
    }
    if (lda < Math.max(1, n)) {
      throw illegalArgument("SSYMV", 5);
    }
    if (incx == 0) {
      throw illegalArgument("SSYMV", 7);
    }
    if (incy == 0) {
      throw illegalArgument("SSYMV", 10);
    }
    if (n == 0) {
      return;
    }
    // y = beta * y
    if (beta != 1.0f) {
      sscal(n, beta, y, offsety, incy);
    }
    // y += alpha * A * x
    if (alpha != 0.0f) {
      if (lsame("U", uplo)) {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0, iy = incy < 0 ? (n - 1) * -incy : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4, iy += incy * 4) {
          float alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          float alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          float alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          float alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          float sumiy0 = 0.0f;
          float sumiy1 = 0.0f;
          float sumiy2 = 0.0f;
          float sumiy3 = 0.0f;
          int row = 0, jx = incx < 0 ? (col - 1) * -incx : 0, jy = incy < 0 ? (col - 1) * -incy : 0;
          for (; row < col; row += 1, jx += incx, jy += incy) {
            y[offsety + jy] += alphaxix0 * a[offseta + row + (col + 0) * lda]
                            +  alphaxix1 * a[offseta + row + (col + 1) * lda]
                            +  alphaxix2 * a[offseta + row + (col + 2) * lda]
                            +  alphaxix3 * a[offseta + row + (col + 3) * lda];
            float xjx = x[offsetx + jx];
            sumiy0 += xjx * a[offseta + row + (col + 0) * lda];
            sumiy1 += xjx * a[offseta + row + (col + 1) * lda];
            sumiy2 += xjx * a[offseta + row + (col + 2) * lda];
            sumiy3 += xjx * a[offseta + row + (col + 3) * lda];
          }
          float a00 = a[offseta + (row + 0) + (col + 0) * lda];
          float a01 = a[offseta + (row + 0) + (col + 1) * lda];
          float a02 = a[offseta + (row + 0) + (col + 2) * lda];
          float a03 = a[offseta + (row + 0) + (col + 3) * lda];
          float a11 = a[offseta + (row + 1) + (col + 1) * lda];
          float a12 = a[offseta + (row + 1) + (col + 2) * lda];
          float a13 = a[offseta + (row + 1) + (col + 3) * lda];
          float a22 = a[offseta + (row + 2) + (col + 2) * lda];
          float a23 = a[offseta + (row + 2) + (col + 3) * lda];
          float a33 = a[offseta + (row + 3) + (col + 3) * lda];
          float xjx0 = x[offsetx + jx + incx * 0];
          float xjx1 = x[offsetx + jx + incx * 1];
          float xjx2 = x[offsetx + jx + incx * 2];
          float xjx3 = x[offsetx + jx + incx * 3];
          sumiy0 += xjx0 * a00
                 +  xjx1 * a01
                 +  xjx2 * a02
                 +  xjx3 * a03;
          sumiy1 += xjx0 * a01
                 +  xjx1 * a11
                 +  xjx2 * a12
                 +  xjx3 * a13;
          sumiy2 += xjx0 * a02
                 +  xjx1 * a12
                 +  xjx2 * a22
                 +  xjx3 * a23;
          sumiy3 += xjx0 * a03
                 +  xjx1 * a13
                 +  xjx2 * a23
                 +  xjx3 * a33;
          y[offsety + iy + incy * 0] += alpha * sumiy0;
          y[offsety + iy + incy * 1] += alpha * sumiy1;
          y[offsety + iy + incy * 2] += alpha * sumiy2;
          y[offsety + iy + incy * 3] += alpha * sumiy3;
        }
        for (; col < n; col += 1, ix += incx, iy += incy) {
          float alphaxix = alpha * x[offsetx + ix];
          float sumiy = 0.0f;
          int row = 0, jx = incx < 0 ? (col - 1) * -incx : 0, jy = incy < 0 ? (col - 1) * -incy : 0;
          for (; row < col; row += 1, jx += incx, jy += incy) {
            y[offsety + jy] += alphaxix * a[offseta + row + col * lda];
            sumiy += x[offsetx + jx] * a[offseta + row + col * lda];
          }
          y[offsety + iy] += alpha * (sumiy + x[offsetx + jx] * a[offseta + row + col * lda]);
        }
      } else {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0, iy = incy < 0 ? (n - 1) * -incy : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4, iy += incy * 4) {
          float alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          float alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          float alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          float alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          float sumiy0 = 0.0f;
          float sumiy1 = 0.0f;
          float sumiy2 = 0.0f;
          float sumiy3 = 0.0f;
          float a00 = a[offseta + /*row=*/(col + 0) + (col + 0) * lda];
          float a10 = a[offseta + /*row=*/(col + 1) + (col + 0) * lda];
          float a11 = a[offseta + /*row=*/(col + 1) + (col + 1) * lda];
          float a20 = a[offseta + /*row=*/(col + 2) + (col + 0) * lda];
          float a21 = a[offseta + /*row=*/(col + 2) + (col + 1) * lda];
          float a22 = a[offseta + /*row=*/(col + 2) + (col + 2) * lda];
          float a30 = a[offseta + /*row=*/(col + 3) + (col + 0) * lda];
          float a31 = a[offseta + /*row=*/(col + 3) + (col + 1) * lda];
          float a32 = a[offseta + /*row=*/(col + 3) + (col + 2) * lda];
          float a33 = a[offseta + /*row=*/(col + 3) + (col + 3) * lda];
          float x0 = x[offsetx + (incx < 0 ? (n - (col + 0) - 1) * -incx : (col + 0) * incx)];
          float x1 = x[offsetx + (incx < 0 ? (n - (col + 1) - 1) * -incx : (col + 1) * incx)];
          float x2 = x[offsetx + (incx < 0 ? (n - (col + 2) - 1) * -incx : (col + 2) * incx)];
          float x3 = x[offsetx + (incx < 0 ? (n - (col + 3) - 1) * -incx : (col + 3) * incx)];
          sumiy0 += x0 * a00
                 +  x1 * a10
                 +  x2 * a20
                 +  x3 * a30;
          sumiy1 += x0 * a10
                 +  x1 * a11
                 +  x2 * a21
                 +  x3 * a31;
          sumiy2 += x0 * a20
                 +  x1 * a21
                 +  x2 * a22
                 +  x3 * a32;
          sumiy3 += x0 * a30
                 +  x1 * a31
                 +  x2 * a32
                 +  x3 * a33;
          int row = col + 4, jx = incx < 0 ? (n - (col + 4) - 1) * -incx : (col + 4) * incx, jy = incy < 0 ? (n - (col + 4) - 1) * -incy : (col + 4) * incy;
          for (; row < n; row += 1, jx += incx, jy += incy) {
            float a0 = a[offseta + row + (col + 0) * lda];
            float a1 = a[offseta + row + (col + 1) * lda];
            float a2 = a[offseta + row + (col + 2) * lda];
            float a3 = a[offseta + row + (col + 3) * lda];
            y[offsety + jy] += alphaxix0 * a0
                            +  alphaxix1 * a1
                            +  alphaxix2 * a2
                            +  alphaxix3 * a3;
            float xjx = x[offsetx + jx];
            sumiy0 += xjx * a0;
            sumiy1 += xjx * a1;
            sumiy2 += xjx * a2;
            sumiy3 += xjx * a3;
          }
          y[offsety + iy + incy * 0] += alpha * sumiy0;
          y[offsety + iy + incy * 1] += alpha * sumiy1;
          y[offsety + iy + incy * 2] += alpha * sumiy2;
          y[offsety + iy + incy * 3] += alpha * sumiy3;
        }
        for (; col < n; col += 1, ix += incx, iy += incy) {
          float alphaxix = alpha * x[offsetx + ix];
          float sumiy = 0.0f;
          sumiy += x[offsetx + (incx < 0 ? (n - col - 1) * -incx : col * incx)] * a[offseta + /*row=*/col + col * lda];
          int row = col + 1, jx = incx < 0 ? (n - (col + 1) - 1) * -incx : (col + 1) * incx, jy = incy < 0 ? (n - (col + 1) - 1) * -incy : (col + 1) * incy;
          for (; row < n; row += 1, jx += incx, jy += incy) {
            y[offsety + jy] += alphaxix * a[offseta + row + col * lda];
            sumiy += x[offsetx + jx] * a[offseta + row + col * lda];
          }
          y[offsety + iy] += alpha * sumiy;
        }
      }
    }
  }

  public void dsyr(String uplo, int n, double alpha, double[] x, int incx, double[] a, int lda) {
    dsyr(uplo, n, alpha, x, 0, incx, a, 0, lda);
  }

  // a += alpha * x * x.t
  public void dsyr(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta, int lda) {
    if (!lsame("U", uplo) && !lsame("L", uplo)) {
      throw illegalArgument("DSYR", 1);
    }
    if (n < 0) {
      throw illegalArgument("DSYR", 2);
    }
    if (incx == 0) {
      throw illegalArgument("DSYR", 5);
    }
    if (lda < Math.max(1, n)) {
      throw illegalArgument("DSYR", 7);
    }
    if (n == 0) {
      return;
    }
    if (alpha != 0.0) {
      if (lsame("U", uplo)) {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4) {
          double alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          double alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          double alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          double alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          int row = 0, jx = incx < 0 ? col * -incx : 0;
          for (; row < col + 1; row += 1, jx += incx) {
            double xjx = x[offsetx + jx];
            a[offseta + row + (col + 0) * lda] += alphaxix0 * xjx;
            a[offseta + row + (col + 1) * lda] += alphaxix1 * xjx;
            a[offseta + row + (col + 2) * lda] += alphaxix2 * xjx;
            a[offseta + row + (col + 3) * lda] += alphaxix3 * xjx;
          }
          double xjx0 = x[offsetx + jx + incx * 0];
          a[offseta + (row + 0) + (col + 1) * lda] += alphaxix1 * xjx0;
          a[offseta + (row + 0) + (col + 2) * lda] += alphaxix2 * xjx0;
          a[offseta + (row + 0) + (col + 3) * lda] += alphaxix3 * xjx0;
          double xjx1 = x[offsetx + jx + incx * 1];
          a[offseta + (row + 1) + (col + 2) * lda] += alphaxix2 * xjx1;
          a[offseta + (row + 1) + (col + 3) * lda] += alphaxix3 * xjx1;
          double xjx2 = x[offsetx + jx + incx * 2];
          a[offseta + (row + 2) + (col + 3) * lda] += alphaxix3 * xjx2;
        }
        for (; col < n; col += 1, ix += incx) {
          double alphaxix = alpha * x[offsetx + ix];
          int row = 0, jx = incx < 0 ? col * -incx : 0;
          for (; row < col + 1; row += 1, jx += incx) {
            a[offseta + row + col * lda] += alphaxix * x[offsetx + jx];
          }
        }
      } else {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4) {
          double alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          double alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          double alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          double alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          double xjx0 = x[offsetx + (incx < 0 ? (n - (col + 0) - 1) * -incx : (col + 0) * incx)];
          a[offseta + /*row=*/(col + 0) + (col + 0) * lda] += alphaxix0 * xjx0;
          double xjx1 = x[offsetx + (incx < 0 ? (n - (col + 1) - 1) * -incx : (col + 1) * incx)];
          a[offseta + /*row=*/(col + 1) + (col + 0) * lda] += alphaxix0 * xjx1;
          a[offseta + /*row=*/(col + 1) + (col + 1) * lda] += alphaxix1 * xjx1;
          double xjx2 = x[offsetx + (incx < 0 ? (n - (col + 2) - 1) * -incx : (col + 2) * incx)];
          a[offseta + /*row=*/(col + 2) + (col + 0) * lda] += alphaxix0 * xjx2;
          a[offseta + /*row=*/(col + 2) + (col + 1) * lda] += alphaxix1 * xjx2;
          a[offseta + /*row=*/(col + 2) + (col + 2) * lda] += alphaxix2 * xjx2;
          int row = col + (4 - 1), jx = incx < 0 ? (n - (col + (4 - 1)) - 1) * -incx : (col + (4 - 1)) * incx;
          for (; row < n; row += 1, jx += incx) {
            double xjx = x[offsetx + jx];
            a[offseta + row + (col + 0) * lda] += alphaxix0 * xjx;
            a[offseta + row + (col + 1) * lda] += alphaxix1 * xjx;
            a[offseta + row + (col + 2) * lda] += alphaxix2 * xjx;
            a[offseta + row + (col + 3) * lda] += alphaxix3 * xjx;
          }
        }
        for (; col < n; col += 1, ix += incx) {
          double alphaxix = alpha * x[offsetx + ix];
          int row = col + 1, jx = incx < 0 ? (n - (col + 1) - 1) * -incx : (col + 1) * incx;
          for (; row < n; row += 1, jx += incx) {
            a[offseta + row + col * lda] += alphaxix * x[offsetx + jx];
          }
        }
      }
    }
  }

  public void ssyr(String uplo, int n, float alpha, float[] x, int incx, float[] a, int lda) {
    ssyr(uplo, n, alpha, x, 0, incx, a, 0, lda);
  }

  public void ssyr(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta, int lda) {
    if (!lsame("U", uplo) && !lsame("L", uplo)) {
      throw illegalArgument("SSYR", 1);
    }
    if (n < 0) {
      throw illegalArgument("SSYR", 2);
    }
    if (incx == 0) {
      throw illegalArgument("SSYR", 5);
    }
    if (lda < Math.max(1, n)) {
      throw illegalArgument("SSYR", 7);
    }
    if (n == 0) {
      return;
    }
    if (alpha != 0.0) {
      if (lsame("U", uplo)) {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4) {
          float alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          float alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          float alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          float alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          int row = 0, jx = incx < 0 ? col * -incx : 0;
          for (; row < col + 1; row += 1, jx += incx) {
            float xjx = x[offsetx + jx];
            a[offseta + row + (col + 0) * lda] += alphaxix0 * xjx;
            a[offseta + row + (col + 1) * lda] += alphaxix1 * xjx;
            a[offseta + row + (col + 2) * lda] += alphaxix2 * xjx;
            a[offseta + row + (col + 3) * lda] += alphaxix3 * xjx;
          }
          float xjx0 = x[offsetx + jx + incx * 0];
          a[offseta + (row + 0) + (col + 1) * lda] += alphaxix1 * xjx0;
          a[offseta + (row + 0) + (col + 2) * lda] += alphaxix2 * xjx0;
          a[offseta + (row + 0) + (col + 3) * lda] += alphaxix3 * xjx0;
          float xjx1 = x[offsetx + jx + incx * 1];
          a[offseta + (row + 1) + (col + 2) * lda] += alphaxix2 * xjx1;
          a[offseta + (row + 1) + (col + 3) * lda] += alphaxix3 * xjx1;
          float xjx2 = x[offsetx + jx + incx * 2];
          a[offseta + (row + 2) + (col + 3) * lda] += alphaxix3 * xjx2;
        }
        for (; col < n; col += 1, ix += incx) {
          float alphaxix = alpha * x[offsetx + ix];
          int row = 0, jx = incx < 0 ? col * -incx : 0;
          for (; row < col + 1; row += 1, jx += incx) {
            a[offseta + row + col * lda] += alphaxix * x[offsetx + jx];
          }
        }
      } else {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4) {
          float alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          float alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          float alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          float alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          float xjx0 = x[offsetx + (incx < 0 ? (n - (col + 0) - 1) * -incx : (col + 0) * incx)];
          a[offseta + /*row=*/(col + 0) + (col + 0) * lda] += alphaxix0 * xjx0;
          float xjx1 = x[offsetx + (incx < 0 ? (n - (col + 1) - 1) * -incx : (col + 1) * incx)];
          a[offseta + /*row=*/(col + 1) + (col + 0) * lda] += alphaxix0 * xjx1;
          a[offseta + /*row=*/(col + 1) + (col + 1) * lda] += alphaxix1 * xjx1;
          float xjx2 = x[offsetx + (incx < 0 ? (n - (col + 2) - 1) * -incx : (col + 2) * incx)];
          a[offseta + /*row=*/(col + 2) + (col + 0) * lda] += alphaxix0 * xjx2;
          a[offseta + /*row=*/(col + 2) + (col + 1) * lda] += alphaxix1 * xjx2;
          a[offseta + /*row=*/(col + 2) + (col + 2) * lda] += alphaxix2 * xjx2;
          int row = col + (4 - 1), jx = incx < 0 ? (n - (col + (4 - 1)) - 1) * -incx : (col + (4 - 1)) * incx;
          for (; row < n; row += 1, jx += incx) {
            float xjx = x[offsetx + jx];
            a[offseta + row + (col + 0) * lda] += alphaxix0 * xjx;
            a[offseta + row + (col + 1) * lda] += alphaxix1 * xjx;
            a[offseta + row + (col + 2) * lda] += alphaxix2 * xjx;
            a[offseta + row + (col + 3) * lda] += alphaxix3 * xjx;
          }
        }
        for (; col < n; col += 1, ix += incx) {
          float alphaxix = alpha * x[offsetx + ix];
          int row = col + 1, jx = incx < 0 ? (n - (col + 1) - 1) * -incx : (col + 1) * incx;
          for (; row < n; row += 1, jx += incx) {
            a[offseta + row + col * lda] += alphaxix * x[offsetx + jx];
          }
        }
      }
    }
  }

  public void dsyr2(String uplo, int n, double alpha, double[] x, int incx, double[] y, int incy, double[] a, int lda) {
    dsyr2(uplo, n, alpha, x, 0, incx, y, 0, incy, a, 0, lda);
  }

  public void dsyr2(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
    if (!lsame("U", uplo) && !lsame("L", uplo)) {
      throw illegalArgument("DSYR2", 1);
    }
    if (n < 0) {
      throw illegalArgument("DSYR2", 2);
    }
    if (incx == 0) {
      throw illegalArgument("DSYR2", 5);
    }
    if (incy == 0) {
      throw illegalArgument("DSYR2", 7);
    }
    if (lda < Math.max(1, n)) {
      throw illegalArgument("DSYR2", 9);
    }
    if (n == 0) {
      return;
    }
    if (alpha != 0.0) {
      if (lsame("U", uplo)) {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0, iy = incy < 0 ? (n - 1) * -incy : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4, iy += incy * 4) {
          double alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          double alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          double alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          double alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          double alphayiy0 = alpha * y[offsety + iy + incy * 0];
          double alphayiy1 = alpha * y[offsety + iy + incy * 1];
          double alphayiy2 = alpha * y[offsety + iy + incy * 2];
          double alphayiy3 = alpha * y[offsety + iy + incy * 3];
          int row = 0, jx = incx < 0 ? col * -incx : 0, jy = incy < 0 ? (n - 1) * -incy : 0;
          for (; row < col + 1; row += 1, jx += incx, jy += incy) {
            double xjx = x[offsetx + jx];
            double yjy = y[offsety + jy];
            a[offseta + row + (col + 0) * lda] += alphaxix0 * yjy + alphayiy0 * xjx;
            a[offseta + row + (col + 1) * lda] += alphaxix1 * yjy + alphayiy1 * xjx;
            a[offseta + row + (col + 2) * lda] += alphaxix2 * yjy + alphayiy2 * xjx;
            a[offseta + row + (col + 3) * lda] += alphaxix3 * yjy + alphayiy3 * xjx;
          }
          double xjx0 = x[offsetx + jx + incx * 0];
          double yjy0 = y[offsety + jy + incy * 0];
          a[offseta + (row + 0) + (col + 1) * lda] += alphaxix1 * yjy0 + alphayiy1 * xjx0;
          a[offseta + (row + 0) + (col + 2) * lda] += alphaxix2 * yjy0 + alphayiy2 * xjx0;
          a[offseta + (row + 0) + (col + 3) * lda] += alphaxix3 * yjy0 + alphayiy3 * xjx0;
          double xjx1 = x[offsetx + jx + incx * 1];
          double yjy1 = y[offsety + jy + incy * 1];
          a[offseta + (row + 1) + (col + 2) * lda] += alphaxix2 * yjy1 + alphayiy2 * xjx1;
          a[offseta + (row + 1) + (col + 3) * lda] += alphaxix3 * yjy1 + alphayiy3 * xjx1;
          double xjx2 = x[offsetx + jx + incx * 2];
          double yjy2 = y[offsety + jy + incy * 2];
          a[offseta + (row + 2) + (col + 3) * lda] += alphaxix3 * yjy2 + alphayiy3 * xjx2;
        }
        for (; col < n; col += 1, ix += incx, iy += incy) {
          double alphaxix = alpha * x[offsetx + ix];
          double alphayiy = alpha * y[offsety + iy];
          int row = 0, jx = incx < 0 ? col * -incx : 0, jy = incy < 0 ? (n - 1) * -incy : 0;
          for (; row < col + 1; row += 1, jx += incx, jy += incy) {
            a[offseta + row + col * lda] += alphaxix * y[offsety + jy] + alphayiy * x[offsetx + jx];
          }
        }
      } else {
        f2j.dsyr2(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
      }
    }
  }

  public void ssyr2(String uplo, int n, float alpha, float[] x, int incx, float[] y, int incy, float[] a, int lda) {
    ssyr2(uplo, n, alpha, x, 0, incx, y, 0, incy, a, 0, lda);
  }

  public void ssyr2(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
    if (!lsame("U", uplo) && !lsame("L", uplo)) {
      throw illegalArgument("SSYR2", 1);
    }
    if (n < 0) {
      throw illegalArgument("SSYR2", 2);
    }
    if (incx == 0) {
      throw illegalArgument("SSYR2", 5);
    }
    if (incy == 0) {
      throw illegalArgument("SSYR2", 7);
    }
    if (lda < Math.max(1, n)) {
      throw illegalArgument("SSYR2", 9);
    }
    if (n == 0) {
      return;
    }
    if (alpha != 0.0) {
      if (lsame("U", uplo)) {
        int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0, iy = incy < 0 ? (n - 1) * -incy : 0;
        for (; col < loopBound(n, 4); col += 4, ix += incx * 4, iy += incy * 4) {
          float alphaxix0 = alpha * x[offsetx + ix + incx * 0];
          float alphaxix1 = alpha * x[offsetx + ix + incx * 1];
          float alphaxix2 = alpha * x[offsetx + ix + incx * 2];
          float alphaxix3 = alpha * x[offsetx + ix + incx * 3];
          float alphayiy0 = alpha * y[offsety + iy + incy * 0];
          float alphayiy1 = alpha * y[offsety + iy + incy * 1];
          float alphayiy2 = alpha * y[offsety + iy + incy * 2];
          float alphayiy3 = alpha * y[offsety + iy + incy * 3];
          int row = 0, jx = incx < 0 ? col * -incx : 0, jy = incy < 0 ? (n - 1) * -incy : 0;
          for (; row < col + 1; row += 1, jx += incx, jy += incy) {
            float xjx = x[offsetx + jx];
            float yjy = y[offsety + jy];
            a[offseta + row + (col + 0) * lda] += alphaxix0 * yjy + alphayiy0 * xjx;
            a[offseta + row + (col + 1) * lda] += alphaxix1 * yjy + alphayiy1 * xjx;
            a[offseta + row + (col + 2) * lda] += alphaxix2 * yjy + alphayiy2 * xjx;
            a[offseta + row + (col + 3) * lda] += alphaxix3 * yjy + alphayiy3 * xjx;
          }
          float xjx0 = x[offsetx + jx + incx * 0];
          float yjy0 = y[offsety + jy + incy * 0];
          a[offseta + (row + 0) + (col + 1) * lda] += alphaxix1 * yjy0 + alphayiy1 * xjx0;
          a[offseta + (row + 0) + (col + 2) * lda] += alphaxix2 * yjy0 + alphayiy2 * xjx0;
          a[offseta + (row + 0) + (col + 3) * lda] += alphaxix3 * yjy0 + alphayiy3 * xjx0;
          float xjx1 = x[offsetx + jx + incx * 1];
          float yjy1 = y[offsety + jy + incy * 1];
          a[offseta + (row + 1) + (col + 2) * lda] += alphaxix2 * yjy1 + alphayiy2 * xjx1;
          a[offseta + (row + 1) + (col + 3) * lda] += alphaxix3 * yjy1 + alphayiy3 * xjx1;
          float xjx2 = x[offsetx + jx + incx * 2];
          float yjy2 = y[offsety + jy + incy * 2];
          a[offseta + (row + 2) + (col + 3) * lda] += alphaxix3 * yjy2 + alphayiy3 * xjx2;
        }
        for (; col < n; col += 1, ix += incx, iy += incy) {
          float alphaxix = alpha * x[offsetx + ix];
          float alphayiy = alpha * y[offsety + iy];
          int row = 0, jx = incx < 0 ? col * -incx : 0, jy = incy < 0 ? (n - 1) * -incy : 0;
          for (; row < col + 1; row += 1, jx += incx, jy += incy) {
            a[offseta + row + col * lda] += alphaxix * y[offsety + jy] + alphayiy * x[offsetx + jx];
          }
        }
      } else {
        f2j.ssyr2(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
      }
    }
  }

  public void dsyr2k(String uplo, String trans, int n, int k, double alpha, double[] a, int lda, double[] b, int ldb, double beta, double[] c, int ldc) {
    dsyr2k(uplo, trans, n, k, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
  }

  public void dsyr2k(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    f2j.dsyr2k(uplo, trans, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  public void ssyr2k(String uplo, String trans, int n, int k, float alpha, float[] a, int lda, float[] b, int ldb, float beta, float[] c, int ldc) {
    ssyr2k(uplo, trans, n, k, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
  }

  public void ssyr2k(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    f2j.ssyr2k(uplo, trans, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  public void dsyrk(String uplo, String trans, int n, int k, double alpha, double[] a, int lda, double beta, double[] c, int ldc) {
    dsyrk(uplo, trans, n, k, alpha, a, 0, lda, beta, c, 0, ldc);
  }

  public void dsyrk(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double beta, double[] c, int offsetc, int ldc) {
    f2j.dsyrk(uplo, trans, n, k, alpha, a, offseta, lda, beta, c, offsetc, ldc);
  }

  public void ssyrk(String uplo, String trans, int n, int k, float alpha, float[] a, int lda, float beta, float[] c, int ldc) {
    ssyrk(uplo, trans, n, k, alpha, a, 0, lda, beta, c, 0, ldc);
  }

  public void ssyrk(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float beta, float[] c, int offsetc, int ldc) {
    f2j.ssyrk(uplo, trans, n, k, alpha, a, offseta, lda, beta, c, offsetc, ldc);
  }

  public void dtbmv(String uplo, String trans, String diag, int n, int k, double[] a, int lda, double[] x, int incx) {
    dtbmv(uplo, trans, diag, n, k, a, 0, lda, x, 0, incx);
  }

  public void dtbmv(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    f2j.dtbmv(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }

  public void stbmv(String uplo, String trans, String diag, int n, int k, float[] a, int lda, float[] x, int incx) {
    stbmv(uplo, trans, diag, n, k, a, 0, lda, x, 0, incx);
  }

  public void stbmv(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    f2j.stbmv(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }

  public void dtbsv(String uplo, String trans, String diag, int n, int k, double[] a, int lda, double[] x, int incx) {
    dtbsv(uplo, trans, diag, n, k, a, 0, lda, x, 0, incx);
  }

  public void dtbsv(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    f2j.dtbsv(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }

  public void stbsv(String uplo, String trans, String diag, int n, int k, float[] a, int lda, float[] x, int incx) {
    stbsv(uplo, trans, diag, n, k, a, 0, lda, x, 0, incx);
  }

  public void stbsv(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    f2j.stbsv(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }

  public void dtpmv(String uplo, String trans, String diag, int n, double[] a, double[] x, int incx) {
    dtpmv(uplo, trans, diag, n, a, 0, x, 0, incx);
  }

  public void dtpmv(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx) {
    f2j.dtpmv(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
  }

  public void stpmv(String uplo, String trans, String diag, int n, float[] a, float[] x, int incx) {
    stpmv(uplo, trans, diag, n, a, 0, x, 0, incx);
  }

  public void stpmv(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx) {
    f2j.stpmv(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
  }

  public void dtpsv(String uplo, String trans, String diag, int n, double[] a, double[] x, int incx) {
    dtpsv(uplo, trans, diag, n, a, 0, x, 0, incx);
  }

  public void dtpsv(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx) {
    f2j.dtpsv(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
  }

  public void stpsv(String uplo, String trans, String diag, int n, float[] a, float[] x, int incx) {
    stpsv(uplo, trans, diag, n, a, 0, x, 0, incx);
  }

  public void stpsv(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx) {
    f2j.stpsv(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
  }

  public void dtrmm(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int lda, double[] b, int ldb) {
    dtrmm(side, uplo, transa, diag, m, n, alpha, a, 0, lda, b, 0, ldb);
  }

  public void dtrmm(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
    f2j.dtrmm(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }

  public void strmm(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int lda, float[] b, int ldb) {
    strmm(side, uplo, transa, diag, m, n, alpha, a, 0, lda, b, 0, ldb);
  }

  public void strmm(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
    f2j.strmm(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }

  public void dtrmv(String uplo, String trans, String diag, int n, double[] a, int lda, double[] x, int incx) {
    dtrmv(uplo, trans, diag, n, a, 0, lda, x, 0, incx);
  }

  public void dtrmv(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    f2j.dtrmv(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }

  public void strmv(String uplo, String trans, String diag, int n, float[] a, int lda, float[] x, int incx) {
    strmv(uplo, trans, diag, n, a, 0, lda, x, 0, incx);
  }

  public void strmv(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    f2j.strmv(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }

  public void dtrsm(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int lda, double[] b, int ldb) {
    dtrsm(side, uplo, transa, diag, m, n, alpha, a, 0, lda, b, 0, ldb);
  }

  public void dtrsm(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
    f2j.dtrsm(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }

  public void strsm(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int lda, float[] b, int ldb) {
    strsm(side, uplo, transa, diag, m, n, alpha, a, 0, lda, b, 0, ldb);
  }

  public void strsm(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
    f2j.strsm(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }

  public void dtrsv(String uplo, String trans, String diag, int n, double[] a, int lda, double[] x, int incx) {
    dtrsv(uplo, trans, diag, n, a, 0, lda, x, 0, incx);
  }

  public void dtrsv(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    f2j.dtrsv(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }

  public void strsv(String uplo, String trans, String diag, int n, float[] a, int lda, float[] x, int incx) {
    strsv(uplo, trans, diag, n, a, 0, lda, x, 0, incx);
  }

  public void strsv(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    f2j.strsv(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }

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
    int imax = 0;
    double max = x[offsetx];
    for (int i = 1, ix = incx; ix < n * incx; i += 1, ix += incx) {
      double val = Math.abs(x[offsetx + ix]);
      if (val > max) {
        imax = i;
        max = val;
      }
    }
    return imax + 1; // +1 because Fortran arrays are 1-indexed
  }

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
    int imax = 0;
    float max = x[offsetx];
    for (int i = 1, ix = incx; ix < n * incx; i += 1, ix += incx) {
      float val = Math.abs(x[offsetx + ix]);
      if (val > max) {
        imax = i;
        max = val;
      }
    }
    return imax + 1; // +1 because Fortran arrays are 1-indexed
  }

  public boolean lsame(String ca, String cb) {
    return ca != null && ca.length() == 1 && ca.equalsIgnoreCase(cb);
  }
}
