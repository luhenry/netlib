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

import dev.ludovic.netlib.BLAS;

public class Java8BLAS extends AbstractBLAS implements dev.ludovic.netlib.JavaBLAS {

  private static final Java8BLAS instance = new Java8BLAS();

  protected Java8BLAS() {}

  public static dev.ludovic.netlib.JavaBLAS getInstance() {
    return instance;
  }

  protected double dasumK(int n, double[] x, int offsetx, int incx) {
    double sum = 0.0;
    if (incx == 1) {
      int ix = 0;
      double sum0 = 0.0;
      double sum1 = 0.0;
      double sum2 = 0.0;
      double sum3 = 0.0;
      for (; ix < loopBound(n, 4); ix += 4) {
        sum0 += Math.abs(x[offsetx + ix + 0]);
        sum1 += Math.abs(x[offsetx + ix + 1]);
        sum2 += Math.abs(x[offsetx + ix + 2]);
        sum3 += Math.abs(x[offsetx + ix + 3]);
      }
      sum += sum0 + sum1 + sum2 + sum3;
      for (; ix < n; ix += 1) {
        sum += Math.abs(x[offsetx + ix]);
      }
    } else {
      for (int ix = incx < 0 ? (n - 1) * -incx : 0; incx < 0 ? ix >= 0 : ix < n * incx; ix += incx) {
        sum += Math.abs(x[offsetx + ix]);
      }
    }
    return sum;
  }

  protected float sasumK(int n, float[] x, int offsetx, int incx) {
    float sum = 0.0f;
    if (incx == 1) {
      int ix = 0;
      float sum0 = 0.0f;
      float sum1 = 0.0f;
      float sum2 = 0.0f;
      float sum3 = 0.0f;
      for (; ix < loopBound(n, 4); ix += 4) {
        sum0 += Math.abs(x[offsetx + ix + 0]);
        sum1 += Math.abs(x[offsetx + ix + 1]);
        sum2 += Math.abs(x[offsetx + ix + 2]);
        sum3 += Math.abs(x[offsetx + ix + 3]);
      }
      sum += sum0 + sum1 + sum2 + sum3;
      for (; ix < n; ix += 1) {
        sum += Math.abs(x[offsetx + ix]);
      }
    } else {
      for (int ix = incx < 0 ? (n - 1) * -incx : 0; incx < 0 ? ix >= 0 : ix < n * incx; ix += incx) {
        sum += Math.abs(x[offsetx + ix]);
      }
    }
    return sum;
  }

  protected void daxpyK(int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
      for (int ix = 0, iy = 0; ix < n && iy < n; ix += 1, iy += 1) {
        y[offsety + iy] += alpha * x[offsetx + ix];
      }
    } else {
      for (int ix = incx < 0 ? (n - 1) * -incx : 0,
               iy = incy < 0 ? (n - 1) * -incy : 0;
           (incx < 0 ? ix >= 0 : ix < n * incx)
             && (incy < 0 ? iy >= 0 : iy < n * incy);
           ix += incx, iy += incy) {
        y[offsety + iy] += alpha * x[offsetx + ix];
      }
    }
  }

  protected void saxpyK(int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
      for (int ix = 0, iy = 0; ix < n && iy < n; ix += 1, iy += 1) {
        y[offsety + iy] += alpha * x[offsetx + ix];
      }
    } else {
      for (int ix = incx < 0 ? (n - 1) * -incx : 0,
               iy = incy < 0 ? (n - 1) * -incy : 0;
           (incx < 0 ? ix >= 0 : ix < n * incx)
             && (incy < 0 ? iy >= 0 : iy < n * incy);
           ix += incx, iy += incy) {
        y[offsety + iy] += alpha * x[offsetx + ix];
      }
    }
  }

  protected void dcopyK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
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

  protected void scopyK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
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

  protected double ddotK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    double sum = 0.0;
    if (incx == 1 && incy == 1) {
      int ix = 0, iy = 0;
      double sum0 = 0.0;
      double sum1 = 0.0;
      double sum2 = 0.0;
      double sum3 = 0.0;
      for (; ix < loopBound(n, 4) && iy < loopBound(n, 4); ix += 4, iy += 4) {
        sum0 += x[offsetx + ix + 0] * y[offsety + iy + 0];
        sum1 += x[offsetx + ix + 1] * y[offsety + iy + 1];
        sum2 += x[offsetx + ix + 2] * y[offsety + iy + 2];
        sum3 += x[offsetx + ix + 3] * y[offsety + iy + 3];
      }
      sum += sum0 + sum1 + sum2 + sum3;
      for (; ix < n && iy < n; ix += 1, iy += 1) {
        sum += x[offsetx + ix] * y[offsety + iy];
      }
    } else {
      for (int ix = incx < 0 ? (n - 1) * -incx : 0,
              iy = incy < 0 ? (n - 1) * -incy : 0;
          (incx < 0 ? ix >= 0 : ix < n * incx)
            && (incy < 0 ? iy >= 0 : iy < n * incy);
          ix += incx, iy += incy) {
        sum += x[offsetx + ix] * y[offsety + iy];
      }
    }
    return sum;
  }

  protected float sdotK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    float sum = 0.0f;
    if (incx == 1 && incy == 1) {
      int ix = 0, iy = 0;
      float sum0 = 0.0f;
      float sum1 = 0.0f;
      float sum2 = 0.0f;
      float sum3 = 0.0f;
      for (; ix < loopBound(n, 4) && iy < loopBound(n, 4); ix += 4, iy += 4) {
        sum0 += x[offsetx + ix + 0] * y[offsety + iy + 0];
        sum1 += x[offsetx + ix + 1] * y[offsety + iy + 1];
        sum2 += x[offsetx + ix + 2] * y[offsety + iy + 2];
        sum3 += x[offsetx + ix + 3] * y[offsety + iy + 3];
      }
      sum += sum0 + sum1 + sum2 + sum3;
      for (; ix < n && iy < n; ix += 1, iy += 1) {
        sum += x[offsetx + ix] * y[offsety + iy];
      }
    } else {
      for (int ix = incx < 0 ? (n - 1) * -incx : 0,
               iy = incy < 0 ? (n - 1) * -incy : 0;
           (incx < 0 ? ix >= 0 : ix < n * incx)
             && (incy < 0 ? iy >= 0 : iy < n * incy);
           ix += incx, iy += incy) {
        sum += x[offsetx + ix] * y[offsety + iy];
      }
    }
    return sum;
  }

  protected float sdsdotK(int n, float sb, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    double sum = sb;
    if (incx == 1 && incy == 1) {
      int ix = 0, iy = 0;
      double sum0 = 0.0;
      double sum1 = 0.0;
      double sum2 = 0.0;
      double sum3 = 0.0;
      for (; ix < loopBound(n, 4) && iy < loopBound(n, 4); ix += 4, iy += 4) {
        sum0 += (double)x[offsetx + ix + 0] * (double)y[offsety + iy + 0];
        sum1 += (double)x[offsetx + ix + 1] * (double)y[offsety + iy + 1];
        sum2 += (double)x[offsetx + ix + 2] * (double)y[offsety + iy + 2];
        sum3 += (double)x[offsetx + ix + 3] * (double)y[offsety + iy + 3];
      }
      sum += sum0 + sum1 + sum2 + sum3;
      for (; ix < n && iy < n; ix += 1, iy += 1) {
        sum += (double)(x[offsetx + ix]) * (double)(y[offsety + iy]);
      }
    } else {
      for (int ix = incx < 0 ? (n - 1) * -incx : 0,
               iy = incy < 0 ? (n - 1) * -incy : 0;
           (incx < 0 ? ix >= 0 : ix < n * incx)
            && (incy < 0 ? iy >= 0 : iy < n * incy);
           ix += incx, iy += incy) {
        sum += (double)(x[offsetx + ix]) * (double)(y[offsety + iy]);
      }
    }
    return (float)sum;
  }

  protected void dgbmvK(String trans, int m, int n, int kl, int ku, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    org.netlib.blas.Dgbmv.dgbmv(trans, m, n, kl, ku, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }
  protected void sgbmvK(String trans, int m, int n, int kl, int ku, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    org.netlib.blas.Sgbmv.sgbmv(trans, m, n, kl, ku, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected void dgemmK(String transa, String transb, int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    if (alpha == 0.0) {
      dgemmBeta(0, m, 0, n, beta, c, offsetc, ldc);
    } else if (m * n * k < 100 * 100 * 100) {
      // The matrices are small and it's faster to do the non-copying version
      if (lsame("N", transa) && lsame("N", transb)) {
        dgemmNN(m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
      } else if (lsame("N", transa)) {
        dgemmNT(m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
      } else if (lsame("N", transb)) {
        dgemmTN(m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
      } else {
        dgemmTT(m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
      }
    } else {
      final int Krow = (int)(Math.ceil((double)(Math.min(60,   m)) / 3) * 3),
                Kcol = (int)(Math.ceil((double)(Math.min(1000, n)) / 3) * 3),
                Ki   = (int)(Math.ceil((double)(Math.min(500,  k)) / 4) * 4);

      assert Krow > 0;
      assert Kcol > 0;
      assert Ki   > 0;

      double[] packeda = new double[Krow * Ki];
      double[] packedb = new double[Kcol * Ki];
      double[] packedc = new double[Kcol * Krow];

      // c = beta * c
      dgemmBeta(0, m, 0, n, beta, c, offsetc, ldc);
      // c += alpha * a * b
      for (int col = 0; col < n; col += Kcol) {
        int cols = col, cole = Math.min(col + Kcol, n);
        for (int i = 0; i < k; i += Ki) {
          int is = i, ie = Math.min(i + Ki, k);
          // pack b
          if (lsame("N", transb)) {
            dgecpyNN(ie - is, cole - cols, b, offsetb, ldb, is, cols, packedb, 0, Ki, 0, 0);
          } else {
            dgecpyTN(ie - is, cole - cols, b, offsetb, ldb, is, cols, packedb, 0, Ki, 0, 0);
          }
          // GEPP
          for (int row = 0; row < m; row += Krow) {
            int rows = row, rowe = Math.min(row + Krow, m);
            // pack A
            if (lsame("N", transa)) {
              dgecpyNT(rowe - rows, ie - is, a, offseta, lda, rows, is, packeda, 0, Ki, 0, 0);
            } else {
              dgecpyTT(rowe - rows, ie - is, a, offseta, lda, rows, is, packeda, 0, Ki, 0, 0);
            }
            // pack C
            dgecpyNN(rowe - rows, cole - cols, c, offsetc, ldc, rows, cols, packedc, 0, Krow, 0, 0);
            // GEBP
            dgebpTN(Krow, 0, rowe - rows, Kcol, 0, cole - cols, Ki, 0, ie - is,
                    alpha, packeda, 0, Ki, packedb, 0, Ki, beta, packedc, 0, Krow);
            // unpack C
            dgecpyNN(rowe - rows, cole - cols, packedc, 0, Krow, 0, 0, c, offsetc, ldc, rows, cols);
          }
        }
      }
    }
  }

  protected void dgemmBeta(int rows, int rowe, int cols, int cole, double beta, double[] c, int offsetc, int ldc) {
    if (beta != 1.0) {
      int col = cols;
      for (; col < loopAlign(cols, cole, 4); col += 1) {
        int row = rows;
        for (; row < rowe; row += 1) {
          if (beta != 0.0) {
            c[offsetc + row + (col + 0) * ldc] = beta * c[offsetc + row + (col + 0) * ldc];
          } else {
            c[offsetc + row + (col + 0) * ldc] = 0.0;
          }
        }
      }
      for (; col < loopBound(cole, 4); col += 4) {
        int row = rows;
        for (; row < rowe; row += 1) {
          if (beta != 0.0) {
            c[offsetc + row + (col + 0) * ldc] = beta * c[offsetc + row + (col + 0) * ldc];
            c[offsetc + row + (col + 1) * ldc] = beta * c[offsetc + row + (col + 1) * ldc];
            c[offsetc + row + (col + 2) * ldc] = beta * c[offsetc + row + (col + 2) * ldc];
            c[offsetc + row + (col + 3) * ldc] = beta * c[offsetc + row + (col + 3) * ldc];
          } else {
            c[offsetc + row + (col + 0) * ldc] = 0.0;
            c[offsetc + row + (col + 1) * ldc] = 0.0;
            c[offsetc + row + (col + 2) * ldc] = 0.0;
            c[offsetc + row + (col + 3) * ldc] = 0.0;
          }
        }
      }
      for (; col < cole; col += 1) {
        int row = rows;
        for (; row < rowe; row += 1) {
          if (beta != 0.0) {
            c[offsetc + row + (col + 0) * ldc] = beta * c[offsetc + row + (col + 0) * ldc];
          } else {
            c[offsetc + row + (col + 0) * ldc] = 0.0;
          }
        }
      }
    }
  }

  protected void dgecpyNN(int m, int n, double[] src, int offsetsrc, int ldsrc, int rowssrc, int colssrc, double[] dst, int offsetdst, int lddst, int rowsdst, int colsdst) {
    int col = 0;
    for (; col < loopBound(n, 4); col += 4) {
      System.arraycopy(src, offsetsrc + rowssrc + (colssrc + col + 0) * ldsrc, dst, offsetdst + rowsdst + (colsdst + col + 0) * lddst, m);
      System.arraycopy(src, offsetsrc + rowssrc + (colssrc + col + 1) * ldsrc, dst, offsetdst + rowsdst + (colsdst + col + 1) * lddst, m);
      System.arraycopy(src, offsetsrc + rowssrc + (colssrc + col + 2) * ldsrc, dst, offsetdst + rowsdst + (colsdst + col + 2) * lddst, m);
      System.arraycopy(src, offsetsrc + rowssrc + (colssrc + col + 3) * ldsrc, dst, offsetdst + rowsdst + (colsdst + col + 3) * lddst, m);
    }
    for (; col < n; col += 1) {
      System.arraycopy(src, offsetsrc + rowssrc + (colssrc + col + 0) * ldsrc, dst, offsetdst + rowsdst + (colsdst + col + 0) * lddst, m);
    }
  }

  protected void dgecpyNT(int m, int n, double[] src, int offsetsrc, int ldsrc, int rowssrc, int colssrc, double[] dst, int offsetdst, int lddst, int rowsdst, int colsdst) {
    int col = 0;
    for (; col < loopBound(n, 3); col += 3) {
      int row = 0;
      for (; row < loopBound(m, 3); row += 3) {
        dst[offsetdst + (colsdst + col + 0) + (rowsdst + row + 0) * lddst] = src[offsetsrc + (rowssrc + row + 0) + (colssrc + col + 0) * ldsrc];
        dst[offsetdst + (colsdst + col + 0) + (rowsdst + row + 1) * lddst] = src[offsetsrc + (rowssrc + row + 1) + (colssrc + col + 0) * ldsrc];
        dst[offsetdst + (colsdst + col + 0) + (rowsdst + row + 2) * lddst] = src[offsetsrc + (rowssrc + row + 2) + (colssrc + col + 0) * ldsrc];
        dst[offsetdst + (colsdst + col + 1) + (rowsdst + row + 0) * lddst] = src[offsetsrc + (rowssrc + row + 0) + (colssrc + col + 1) * ldsrc];
        dst[offsetdst + (colsdst + col + 1) + (rowsdst + row + 1) * lddst] = src[offsetsrc + (rowssrc + row + 1) + (colssrc + col + 1) * ldsrc];
        dst[offsetdst + (colsdst + col + 1) + (rowsdst + row + 2) * lddst] = src[offsetsrc + (rowssrc + row + 2) + (colssrc + col + 1) * ldsrc];
        dst[offsetdst + (colsdst + col + 2) + (rowsdst + row + 0) * lddst] = src[offsetsrc + (rowssrc + row + 0) + (colssrc + col + 2) * ldsrc];
        dst[offsetdst + (colsdst + col + 2) + (rowsdst + row + 1) * lddst] = src[offsetsrc + (rowssrc + row + 1) + (colssrc + col + 2) * ldsrc];
        dst[offsetdst + (colsdst + col + 2) + (rowsdst + row + 2) * lddst] = src[offsetsrc + (rowssrc + row + 2) + (colssrc + col + 2) * ldsrc];
      }
      for (; row < m; row += 1) {
        dst[offsetdst + (colsdst + col + 0) + (rowsdst + row + 0) * lddst] = src[offsetsrc + (rowssrc + row + 0) + (colssrc + col + 0) * ldsrc];
        dst[offsetdst + (colsdst + col + 1) + (rowsdst + row + 0) * lddst] = src[offsetsrc + (rowssrc + row + 0) + (colssrc + col + 1) * ldsrc];
        dst[offsetdst + (colsdst + col + 2) + (rowsdst + row + 0) * lddst] = src[offsetsrc + (rowssrc + row + 0) + (colssrc + col + 2) * ldsrc];
      }
    }
    for (; col < n; col += 1) {
      int row = 0;
      for (; row < loopBound(m, 3); row += 3) {
        dst[offsetdst + (colsdst + col + 0) + (rowsdst + row + 0) * lddst] = src[offsetsrc + (rowssrc + row + 0) + (colssrc + col + 0) * ldsrc];
        dst[offsetdst + (colsdst + col + 0) + (rowsdst + row + 1) * lddst] = src[offsetsrc + (rowssrc + row + 1) + (colssrc + col + 0) * ldsrc];
        dst[offsetdst + (colsdst + col + 0) + (rowsdst + row + 2) * lddst] = src[offsetsrc + (rowssrc + row + 2) + (colssrc + col + 0) * ldsrc];
      }
      for (; row < m; row += 1) {
        dst[offsetdst + (colsdst + col + 0) + (rowsdst + row + 0) * lddst] = src[offsetsrc + (rowssrc + row + 0) + (colssrc + col + 0) * ldsrc];
      }
    }
  }

  protected void dgecpyTN(int m, int n, double[] src, int offsetsrc, int ldsrc, int rowssrc, int colssrc, double[] dst, int offsetdst, int lddst, int rowsdst, int colsdst) {
    int row = 0;
    for (; row < loopBound(m, 3); row += 3) {
      int col = 0;
      for (; col < loopBound(n, 3); col += 3) {
        dst[offsetdst + (rowsdst + row + 0) + (colsdst + col + 0) * lddst] = src[offsetsrc + (colssrc + col + 0) + (rowssrc + row + 0) * ldsrc];
        dst[offsetdst + (rowsdst + row + 0) + (colsdst + col + 1) * lddst] = src[offsetsrc + (colssrc + col + 1) + (rowssrc + row + 0) * ldsrc];
        dst[offsetdst + (rowsdst + row + 0) + (colsdst + col + 2) * lddst] = src[offsetsrc + (colssrc + col + 2) + (rowssrc + row + 0) * ldsrc];
        dst[offsetdst + (rowsdst + row + 1) + (colsdst + col + 0) * lddst] = src[offsetsrc + (colssrc + col + 0) + (rowssrc + row + 1) * ldsrc];
        dst[offsetdst + (rowsdst + row + 1) + (colsdst + col + 1) * lddst] = src[offsetsrc + (colssrc + col + 1) + (rowssrc + row + 1) * ldsrc];
        dst[offsetdst + (rowsdst + row + 1) + (colsdst + col + 2) * lddst] = src[offsetsrc + (colssrc + col + 2) + (rowssrc + row + 1) * ldsrc];
        dst[offsetdst + (rowsdst + row + 2) + (colsdst + col + 0) * lddst] = src[offsetsrc + (colssrc + col + 0) + (rowssrc + row + 2) * ldsrc];
        dst[offsetdst + (rowsdst + row + 2) + (colsdst + col + 1) * lddst] = src[offsetsrc + (colssrc + col + 1) + (rowssrc + row + 2) * ldsrc];
        dst[offsetdst + (rowsdst + row + 2) + (colsdst + col + 2) * lddst] = src[offsetsrc + (colssrc + col + 2) + (rowssrc + row + 2) * ldsrc];
      }
      for (; col < n; col += 1) {
        dst[offsetdst + (rowsdst + row + 0) + (colsdst + col + 0) * lddst] = src[offsetsrc + (colssrc + col + 0) + (rowssrc + row + 0) * ldsrc];
        dst[offsetdst + (rowsdst + row + 1) + (colsdst + col + 0) * lddst] = src[offsetsrc + (colssrc + col + 0) + (rowssrc + row + 1) * ldsrc];
        dst[offsetdst + (rowsdst + row + 2) + (colsdst + col + 0) * lddst] = src[offsetsrc + (colssrc + col + 0) + (rowssrc + row + 2) * ldsrc];
      }
    }
    for (; row < m; row += 1) {
      int col = 0;
      for (; col < loopBound(n, 3); col += 3) {
        dst[offsetdst + (rowsdst + row + 0) + (colsdst + col + 0) * lddst] = src[offsetsrc + (colssrc + col + 0) + (rowssrc + row + 0) * ldsrc];
        dst[offsetdst + (rowsdst + row + 0) + (colsdst + col + 1) * lddst] = src[offsetsrc + (colssrc + col + 1) + (rowssrc + row + 0) * ldsrc];
        dst[offsetdst + (rowsdst + row + 0) + (colsdst + col + 2) * lddst] = src[offsetsrc + (colssrc + col + 2) + (rowssrc + row + 0) * ldsrc];
      }
      for (; col < n; col += 1) {
        dst[offsetdst + (rowsdst + row + 0) + (colsdst + col + 0) * lddst] = src[offsetsrc + (colssrc + col + 0) + (rowssrc + row + 0) * ldsrc];
      }
    }
  }

  protected void dgecpyTT(int m, int n, double[] src, int offsetsrc, int ldsrc, int rowssrc, int colssrc, double[] dst, int offsetdst, int lddst, int rowsdst, int colsdst) {
    int row = 0;
    for (; row < loopBound(m, 4); row += 4) {
      System.arraycopy(src, offsetsrc + colssrc + (rowssrc + row + 0) * ldsrc, dst, offsetdst + colsdst + (rowsdst + row + 0) * lddst, n);
      System.arraycopy(src, offsetsrc + colssrc + (rowssrc + row + 1) * ldsrc, dst, offsetdst + colsdst + (rowsdst + row + 1) * lddst, n);
      System.arraycopy(src, offsetsrc + colssrc + (rowssrc + row + 2) * ldsrc, dst, offsetdst + colsdst + (rowsdst + row + 2) * lddst, n);
      System.arraycopy(src, offsetsrc + colssrc + (rowssrc + row + 3) * ldsrc, dst, offsetdst + colsdst + (rowsdst + row + 3) * lddst, n);
    }
    for (; row < m; row += 1) {
      System.arraycopy(src, offsetsrc + colssrc + (rowssrc + row + 0) * ldsrc, dst, offsetdst + colsdst + (rowsdst + row + 0) * lddst, n);
    }
  }

  protected void dgebpTN(int m, int rows, int rowe, int n, int cols, int cole, int k, int is, int ie, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    final int Tcol = 3, Trow = 3;

    int col = cols;
    for (; col < loopAlign(cols, cole, Tcol); col += 1) {
      int row = rows;
      for (; row < loopAlign(rows, rowe, Trow); row += 1) {
        double sum00 = 0.0;
        for (int i = is; i < ie; i += 1) {
          double a0 = a[offseta + i + (row + 0) * lda];
          double b0 = b[offsetb + i + (col + 0) * ldb];
          sum00 = a0 * b0 + sum00;
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + c[offsetc + (row + 0) + (col + 0) * ldc];
      }
      for (; row < loopBound(rowe, Trow); row += Trow) {
        double sum00 = 0.0;
        double sum10 = 0.0;
        double sum20 = 0.0;
        for (int i = is; i < ie; i += 1) {
          double a0 = a[offseta + i + (row + 0) * lda];
          double a1 = a[offseta + i + (row + 1) * lda];
          double a2 = a[offseta + i + (row + 2) * lda];
          double b0 = b[offsetb + i + (col + 0) * ldb];
          sum00 = a0 * b0 + sum00;
          sum10 = a1 * b0 + sum10;
          sum20 = a2 * b0 + sum20;
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + c[offsetc + (row + 0) + (col + 0) * ldc];
        c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + c[offsetc + (row + 1) + (col + 0) * ldc];
        c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + c[offsetc + (row + 2) + (col + 0) * ldc];
      }
      for (; row < rowe; row += 1) {
        double sum00 = 0.0;
        for (int i = is; i < ie; i += 1) {
          double a0 = a[offseta + i + (row + 0) * lda];
          double b0 = b[offsetb + i + (col + 0) * ldb];
          sum00 = a0 * b0 + sum00;
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + c[offsetc + (row + 0) + (col + 0) * ldc];
      }
    }
    for (; col < loopBound(cole, Tcol); col += Tcol) {
      int row = rows;
      for (; row < loopAlign(rows, rowe, Trow); row += 1) {
        double sum00 = 0.0;
        double sum01 = 0.0;
        double sum02 = 0.0;
        double sum03 = 0.0;
        for (int i = is; i < ie; i += 1) {
          double a0 = a[offseta + i + (row + 0) * lda];
          double b0 = b[offsetb + i + (col + 0) * ldb];
          double b1 = b[offsetb + i + (col + 1) * ldb];
          double b2 = b[offsetb + i + (col + 2) * ldb];
          sum00 = a0 * b0 + sum00;
          sum01 = a0 * b1 + sum01;
          sum02 = a0 * b2 + sum02;
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + c[offsetc + (row + 0) + (col + 0) * ldc];
        c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + c[offsetc + (row + 0) + (col + 1) * ldc];
        c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + c[offsetc + (row + 0) + (col + 2) * ldc];
      }
      for (; row < loopBound(rowe, Trow); row += Trow) {
        dgepdotTN(m, row, row + Trow, n, col, col + Tcol, k, is, ie, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
      }
      for (; row < rowe; row += 1) {
        double sum00 = 0.0;
        double sum01 = 0.0;
        double sum02 = 0.0;
        for (int i = is; i < ie; i += 1) {
          double a0 = a[offseta + i + (row + 0) * lda];
          double b0 = b[offsetb + i + (col + 0) * ldb];
          double b1 = b[offsetb + i + (col + 1) * ldb];
          double b2 = b[offsetb + i + (col + 2) * ldb];
          sum00 = a0 * b0 + sum00;
          sum01 = a0 * b1 + sum01;
          sum02 = a0 * b2 + sum02;
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + c[offsetc + (row + 0) + (col + 0) * ldc];
        c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + c[offsetc + (row + 0) + (col + 1) * ldc];
        c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + c[offsetc + (row + 0) + (col + 2) * ldc];
      }
    }
    for (; col < cole; col += 1) {
      int row = rows;
      for (; row < loopAlign(rows, rowe, Trow); row += 1) {
        double sum00 = 0.0;
        for (int i = is; i < ie; i += 1) {
          double a0 = a[offseta + i + (row + 0) * lda];
          double b0 = b[offsetb + i + (col + 0) * ldb];
          sum00 = a0 * b0 + sum00;
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + c[offsetc + (row + 0) + (col + 0) * ldc];
      }
      for (; row < loopBound(rowe, Trow); row += Trow) {
        double sum00 = 0.0;
        double sum10 = 0.0;
        double sum20 = 0.0;
        for (int i = is; i < ie; i += 1) {
          double a0 = a[offseta + i + (row + 0) * lda];
          double a1 = a[offseta + i + (row + 1) * lda];
          double a2 = a[offseta + i + (row + 2) * lda];
          double b0 = b[offsetb + i + (col + 0) * ldb];
          sum00 = a0 * b0 + sum00;
          sum10 = a1 * b0 + sum10;
          sum20 = a2 * b0 + sum20;
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + c[offsetc + (row + 0) + (col + 0) * ldc];
        c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + c[offsetc + (row + 1) + (col + 0) * ldc];
        c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + c[offsetc + (row + 2) + (col + 0) * ldc];
      }
      for (; row < rowe; row += 1) {
        double sum00 = 0.0;
        for (int i = is; i < ie; i += 1) {
          double a0 = a[offseta + i + (row + 0) * lda];
          double b0 = b[offsetb + i + (col + 0) * ldb];
          sum00 = a0 * b0 + sum00;
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + c[offsetc + (row + 0) + (col + 0) * ldc];
      }
    }
  }

  protected void dgepdotTN(int m, int rows, int rowe, int n, int cols, int cole, int k, int is, int ie, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    final int Ti = 2;

    assert rowe - rows == 3;
    assert cole - cols == 3;

    int row = rows;
    int col = cols;
    int i = is;
    double sum00 = 0.0;
    double sum01 = 0.0;
    double sum02 = 0.0;
    double sum10 = 0.0;
    double sum11 = 0.0;
    double sum12 = 0.0;
    double sum20 = 0.0;
    double sum21 = 0.0;
    double sum22 = 0.0;
    for (; i < loopAlign(is, ie, Ti); i += 1) {
      double a0 = a[offseta + i + (row + 0) * lda];
      double a1 = a[offseta + i + (row + 1) * lda];
      double a2 = a[offseta + i + (row + 2) * lda];
      double b0 = b[offsetb + i + (col + 0) * ldb];
      sum00 = a0 * b0 + sum00;
      sum10 = a1 * b0 + sum10;
      sum20 = a2 * b0 + sum20;
      double b1 = b[offsetb + i + (col + 1) * ldb];
      sum01 = a0 * b1 + sum01;
      sum11 = a1 * b1 + sum11;
      sum21 = a2 * b1 + sum21;
      double b2 = b[offsetb + i + (col + 2) * ldb];
      sum02 = a0 * b2 + sum02;
      sum12 = a1 * b2 + sum12;
      sum22 = a2 * b2 + sum22;
    }
    for (; i < loopBound(ie, Ti); i += Ti) {
      double a00 = a[offseta + (i + 0) + (row + 0) * lda];
      double a01 = a[offseta + (i + 0) + (row + 1) * lda];
      double a02 = a[offseta + (i + 0) + (row + 2) * lda];
      double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
      sum00 = a00 * b00 + sum00;
      sum10 = a01 * b00 + sum10;
      sum20 = a02 * b00 + sum20;
      double b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
      sum01 = a00 * b01 + sum01;
      sum11 = a01 * b01 + sum11;
      sum21 = a02 * b01 + sum21;
      double b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
      sum02 = a00 * b02 + sum02;
      sum12 = a01 * b02 + sum12;
      sum22 = a02 * b02 + sum22;
      double a10 = a[offseta + (i + 1) + (row + 0) * lda];
      double a11 = a[offseta + (i + 1) + (row + 1) * lda];
      double a12 = a[offseta + (i + 1) + (row + 2) * lda];
      double b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
      sum00 = a10 * b10 + sum00;
      sum10 = a11 * b10 + sum10;
      sum20 = a12 * b10 + sum20;
      double b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
      sum01 = a10 * b11 + sum01;
      sum11 = a11 * b11 + sum11;
      sum21 = a12 * b11 + sum21;
      double b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
      sum02 = a10 * b12 + sum02;
      sum12 = a11 * b12 + sum12;
      sum22 = a12 * b12 + sum22;
    }
    for (; i < ie; i += 1) {
      double a0 = a[offseta + i + (row + 0) * lda];
      double a1 = a[offseta + i + (row + 1) * lda];
      double a2 = a[offseta + i + (row + 2) * lda];
      double b0 = b[offsetb + i + (col + 0) * ldb];
      sum00 = a0 * b0 + sum00;
      sum10 = a1 * b0 + sum10;
      sum20 = a2 * b0 + sum20;
      double b1 = b[offsetb + i + (col + 1) * ldb];
      sum01 = a0 * b1 + sum01;
      sum11 = a1 * b1 + sum11;
      sum21 = a2 * b1 + sum21;
      double b2 = b[offsetb + i + (col + 2) * ldb];
      sum02 = a0 * b2 + sum02;
      sum12 = a1 * b2 + sum12;
      sum22 = a2 * b2 + sum22;
    }
    c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + c[offsetc + (row + 0) + (col + 0) * ldc];
    c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + c[offsetc + (row + 0) + (col + 1) * ldc];
    c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + c[offsetc + (row + 0) + (col + 2) * ldc];
    c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + c[offsetc + (row + 1) + (col + 0) * ldc];
    c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11 + c[offsetc + (row + 1) + (col + 1) * ldc];
    c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12 + c[offsetc + (row + 1) + (col + 2) * ldc];
    c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + c[offsetc + (row + 2) + (col + 0) * ldc];
    c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21 + c[offsetc + (row + 2) + (col + 1) * ldc];
    c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22 + c[offsetc + (row + 2) + (col + 2) * ldc];
  }

  protected void dgemmNN(int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    final int Trow = 3, Tcol = 3, Ti = 2;

    int col = 0;
    for (; col < loopBound(n, Tcol); col += Tcol) {
      int row = 0;
      for (; row < loopBound(m, Trow); row += Trow) {
        int i = 0;
        double sum00 = 0.0;
        double sum01 = 0.0;
        double sum02 = 0.0;
        double sum10 = 0.0;
        double sum11 = 0.0;
        double sum12 = 0.0;
        double sum20 = 0.0;
        double sum21 = 0.0;
        double sum22 = 0.0;
        for (; i < loopBound(k, Ti); i += Ti) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double a10 = a[offseta + (row + 1) + (i + 0) * lda];
          double a20 = a[offseta + (row + 2) + (i + 0) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          double b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          double b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          sum10 = a10 * b00 + sum10;
          sum11 = a10 * b01 + sum11;
          sum12 = a10 * b02 + sum12;
          sum20 = a20 * b00 + sum20;
          sum21 = a20 * b01 + sum21;
          sum22 = a20 * b02 + sum22;
          double a01 = a[offseta + (row + 0) + (i + 1) * lda];
          double a11 = a[offseta + (row + 1) + (i + 1) * lda];
          double a21 = a[offseta + (row + 2) + (i + 1) * lda];
          double b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          double b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
          double b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
          sum00 = a01 * b10 + sum00;
          sum01 = a01 * b11 + sum01;
          sum02 = a01 * b12 + sum02;
          sum10 = a11 * b10 + sum10;
          sum11 = a11 * b11 + sum11;
          sum12 = a11 * b12 + sum12;
          sum20 = a21 * b10 + sum20;
          sum21 = a21 * b11 + sum21;
          sum22 = a21 * b12 + sum22;
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double a10 = a[offseta + (row + 1) + (i + 0) * lda];
          double a20 = a[offseta + (row + 2) + (i + 0) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          double b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          double b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          sum10 = a10 * b00 + sum10;
          sum11 = a10 * b01 + sum11;
          sum12 = a10 * b02 + sum12;
          sum20 = a20 * b00 + sum20;
          sum21 = a20 * b01 + sum21;
          sum22 = a20 * b02 + sum22;
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + beta * c[offsetc + (row + 0) + (col + 1) * ldc];
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + beta * c[offsetc + (row + 0) + (col + 2) * ldc];
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + beta * c[offsetc + (row + 1) + (col + 0) * ldc];
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11 + beta * c[offsetc + (row + 1) + (col + 1) * ldc];
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12 + beta * c[offsetc + (row + 1) + (col + 2) * ldc];
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + beta * c[offsetc + (row + 2) + (col + 0) * ldc];
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21 + beta * c[offsetc + (row + 2) + (col + 1) * ldc];
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22 + beta * c[offsetc + (row + 2) + (col + 2) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01;
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02;
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10;
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11;
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12;
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20;
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21;
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22;
        }
      }
      for (; row < m; row += 1) {
        int i = 0;
        double sum00 = 0.0;
        double sum01 = 0.0;
        double sum02 = 0.0;
        for (; i < loopBound(k, Ti); i += Ti) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          double b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          double b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          double a01 = a[offseta + (row + 0) + (i + 1) * lda];
          double b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          double b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
          double b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
          sum00 = a01 * b10 + sum00;
          sum01 = a01 * b11 + sum01;
          sum02 = a01 * b12 + sum02;
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          double b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          double b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + beta * c[offsetc + (row + 0) + (col + 1) * ldc];
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + beta * c[offsetc + (row + 0) + (col + 2) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01;
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02;
        }
      }
    }
    for (; col < n; col += 1) {
      int row = 0;
      for (; row < loopBound(m, Trow); row += Trow) {
        int i = 0;
        double sum00 = 0.0;
        double sum10 = 0.0;
        double sum20 = 0.0;
        for (; i < loopBound(k, Ti); i += Ti) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double a10 = a[offseta + (row + 1) + (i + 0) * lda];
          double a20 = a[offseta + (row + 2) + (i + 0) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum10 = a10 * b00 + sum10;
          sum20 = a20 * b00 + sum20;
          double a01 = a[offseta + (row + 0) + (i + 1) * lda];
          double a11 = a[offseta + (row + 1) + (i + 1) * lda];
          double a21 = a[offseta + (row + 2) + (i + 1) * lda];
          double b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          sum00 = a01 * b10 + sum00;
          sum10 = a11 * b10 + sum10;
          sum20 = a21 * b10 + sum20;
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double a10 = a[offseta + (row + 1) + (i + 0) * lda];
          double a20 = a[offseta + (row + 2) + (i + 0) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum10 = a10 * b00 + sum10;
          sum20 = a20 * b00 + sum20;
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + beta * c[offsetc + (row + 1) + (col + 0) * ldc];
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + beta * c[offsetc + (row + 2) + (col + 0) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10;
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20;
        }
      }
      for (; row < m; row += 1) {
        int i = 0;
        double sum00 = 0.0;
        for (; i < loopBound(k, Ti); i += Ti) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          double a01 = a[offseta + (row + 0) + (i + 1) * lda];
          double b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          sum00 = a01 * b10 + sum00;
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = a00 * b00 + sum00;
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
        }
      }
    }
  }

  protected void dgemmNT(int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    final int Trow = 3, Tcol = 3, Ti = 2;
    int col = 0;
    for (; col < loopBound(n, Tcol); col += Tcol) {
      int row = 0;
      for (; row < loopBound(m, Trow); row += Trow) {
        int i = 0;
        double sum00 = 0.0;
        double sum01 = 0.0;
        double sum02 = 0.0;
        double sum10 = 0.0;
        double sum11 = 0.0;
        double sum12 = 0.0;
        double sum20 = 0.0;
        double sum21 = 0.0;
        double sum22 = 0.0;
        for (; i < loopBound(k, Ti); i += Ti) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double a10 = a[offseta + (row + 1) + (i + 0) * lda];
          double a20 = a[offseta + (row + 2) + (i + 0) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          double b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          double b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          sum10 = a10 * b00 + sum10;
          sum11 = a10 * b01 + sum11;
          sum12 = a10 * b02 + sum12;
          sum20 = a20 * b00 + sum20;
          sum21 = a20 * b01 + sum21;
          sum22 = a20 * b02 + sum22;
          double a01 = a[offseta + (row + 0) + (i + 1) * lda];
          double a11 = a[offseta + (row + 1) + (i + 1) * lda];
          double a21 = a[offseta + (row + 2) + (i + 1) * lda];
          double b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          double b11 = b[offsetb + (col + 1) + (i + 1) * ldb];
          double b12 = b[offsetb + (col + 2) + (i + 1) * ldb];
          sum00 = a01 * b10 + sum00;
          sum01 = a01 * b11 + sum01;
          sum02 = a01 * b12 + sum02;
          sum10 = a11 * b10 + sum10;
          sum11 = a11 * b11 + sum11;
          sum12 = a11 * b12 + sum12;
          sum20 = a21 * b10 + sum20;
          sum21 = a21 * b11 + sum21;
          sum22 = a21 * b12 + sum22;
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double a10 = a[offseta + (row + 1) + (i + 0) * lda];
          double a20 = a[offseta + (row + 2) + (i + 0) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          double b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          double b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          sum10 = a10 * b00 + sum10;
          sum11 = a10 * b01 + sum11;
          sum12 = a10 * b02 + sum12;
          sum20 = a20 * b00 + sum20;
          sum21 = a20 * b01 + sum21;
          sum22 = a20 * b02 + sum22;
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + beta * c[offsetc + (row + 0) + (col + 1) * ldc];
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + beta * c[offsetc + (row + 0) + (col + 2) * ldc];
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + beta * c[offsetc + (row + 1) + (col + 0) * ldc];
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11 + beta * c[offsetc + (row + 1) + (col + 1) * ldc];
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12 + beta * c[offsetc + (row + 1) + (col + 2) * ldc];
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + beta * c[offsetc + (row + 2) + (col + 0) * ldc];
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21 + beta * c[offsetc + (row + 2) + (col + 1) * ldc];
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22 + beta * c[offsetc + (row + 2) + (col + 2) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01;
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02;
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10;
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11;
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12;
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20;
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21;
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22;
        }
      }
      for (; row < m; row += 1) {
        int i = 0;
        double sum00 = 0.0;
        double sum01 = 0.0;
        double sum02 = 0.0;
        for (; i < loopBound(k, Ti); i += Ti) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          double b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          double b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          double a01 = a[offseta + (row + 0) + (i + 1) * lda];
          double b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          double b11 = b[offsetb + (col + 1) + (i + 1) * ldb];
          double b12 = b[offsetb + (col + 2) + (i + 1) * ldb];
          sum00 = a01 * b10 + sum00;
          sum01 = a01 * b11 + sum01;
          sum02 = a01 * b12 + sum02;
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          double b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          double b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + beta * c[offsetc + (row + 0) + (col + 1) * ldc];
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + beta * c[offsetc + (row + 0) + (col + 2) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01;
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02;
        }
      }
    }
    for (; col < n; col += 1) {
      int row = 0;
      for (; row < loopBound(m, Trow); row += Trow) {
        int i = 0;
        double sum00 = 0.0;
        double sum10 = 0.0;
        double sum20 = 0.0;
        for (; i < loopBound(k, Ti); i += Ti) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double a10 = a[offseta + (row + 1) + (i + 0) * lda];
          double a20 = a[offseta + (row + 2) + (i + 0) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum10 = a10 * b00 + sum10;
          sum20 = a20 * b00 + sum20;
          double a01 = a[offseta + (row + 0) + (i + 1) * lda];
          double a11 = a[offseta + (row + 1) + (i + 1) * lda];
          double a21 = a[offseta + (row + 2) + (i + 1) * lda];
          double b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          sum00 = a01 * b10 + sum00;
          sum10 = a11 * b10 + sum10;
          sum20 = a21 * b10 + sum20;
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double a10 = a[offseta + (row + 1) + (i + 0) * lda];
          double a20 = a[offseta + (row + 2) + (i + 0) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum10 = a10 * b00 + sum10;
          sum20 = a20 * b00 + sum20;
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + beta * c[offsetc + (row + 1) + (col + 0) * ldc];
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + beta * c[offsetc + (row + 2) + (col + 0) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10;
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20;
        }
      }
      for (; row < m; row += 1) {
        int i = 0;
        double sum00 = 0.0;
        for (; i < loopBound(k, Ti); i += Ti) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          double a01 = a[offseta + (row + 0) + (i + 1) * lda];
          double b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          sum00 = a01 * b10 + sum00;
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
        }
      }
    }
  }

  protected void dgemmTN(int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    final int Trow = 3, Tcol = 3, Ti = 2;

    int col = 0;
    for (; col < loopBound(n, Tcol); col += Tcol) {
      int row = 0;
      for (; row < loopBound(m, Trow); row += Trow) {
        int i = 0;
        double sum00 = 0.0;
        double sum01 = 0.0;
        double sum02 = 0.0;
        double sum10 = 0.0;
        double sum11 = 0.0;
        double sum12 = 0.0;
        double sum20 = 0.0;
        double sum21 = 0.0;
        double sum22 = 0.0;
        for (; i < loopBound(k, Ti); i += Ti) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double a10 = a[offseta + (i + 0) + (row + 1) * lda];
          double a20 = a[offseta + (i + 0) + (row + 2) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          double b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          double b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          sum10 = a10 * b00 + sum10;
          sum11 = a10 * b01 + sum11;
          sum12 = a10 * b02 + sum12;
          sum20 = a20 * b00 + sum20;
          sum21 = a20 * b01 + sum21;
          sum22 = a20 * b02 + sum22;
          double a01 = a[offseta + (i + 1) + (row + 0) * lda];
          double a11 = a[offseta + (i + 1) + (row + 1) * lda];
          double a21 = a[offseta + (i + 1) + (row + 2) * lda];
          double b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          double b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
          double b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
          sum00 = a01 * b10 + sum00;
          sum01 = a01 * b11 + sum01;
          sum02 = a01 * b12 + sum02;
          sum10 = a11 * b10 + sum10;
          sum11 = a11 * b11 + sum11;
          sum12 = a11 * b12 + sum12;
          sum20 = a21 * b10 + sum20;
          sum21 = a21 * b11 + sum21;
          sum22 = a21 * b12 + sum22;
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double a10 = a[offseta + (i + 0) + (row + 1) * lda];
          double a20 = a[offseta + (i + 0) + (row + 2) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          double b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          double b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          sum10 = a10 * b00 + sum10;
          sum11 = a10 * b01 + sum11;
          sum12 = a10 * b02 + sum12;
          sum20 = a20 * b00 + sum20;
          sum21 = a20 * b01 + sum21;
          sum22 = a20 * b02 + sum22;
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + beta * c[offsetc + (row + 0) + (col + 1) * ldc];
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + beta * c[offsetc + (row + 0) + (col + 2) * ldc];
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + beta * c[offsetc + (row + 1) + (col + 0) * ldc];
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11 + beta * c[offsetc + (row + 1) + (col + 1) * ldc];
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12 + beta * c[offsetc + (row + 1) + (col + 2) * ldc];
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + beta * c[offsetc + (row + 2) + (col + 0) * ldc];
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21 + beta * c[offsetc + (row + 2) + (col + 1) * ldc];
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22 + beta * c[offsetc + (row + 2) + (col + 2) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01;
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02;
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10;
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11;
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12;
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20;
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21;
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22;
        }
      }
      for (; row < m; row += 1) {
        int i = 0;
        double sum00 = 0.0;
        double sum01 = 0.0;
        double sum02 = 0.0;
        for (; i < loopBound(k, Ti); i += Ti) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          double b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          double b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          double a01 = a[offseta + (i + 1) + (row + 0) * lda];
          double b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          double b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
          double b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
          sum00 = a01 * b10 + sum00;
          sum01 = a01 * b11 + sum01;
          sum02 = a01 * b12 + sum02;
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          double b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          double b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + beta * c[offsetc + (row + 0) + (col + 1) * ldc];
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + beta * c[offsetc + (row + 0) + (col + 2) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01;
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02;
        }
      }
    }
    for (; col < n; col += 1) {
      int row = 0;
      for (; row < loopBound(m, Trow); row += Trow) {
        int i = 0;
        double sum00 = 0.0;
        double sum10 = 0.0;
        double sum20 = 0.0;
        for (; i < loopBound(k, Ti); i += Ti) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double a10 = a[offseta + (i + 0) + (row + 1) * lda];
          double a20 = a[offseta + (i + 0) + (row + 2) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum10 = a10 * b00 + sum10;
          sum20 = a20 * b00 + sum20;
          double a01 = a[offseta + (i + 1) + (row + 0) * lda];
          double a11 = a[offseta + (i + 1) + (row + 1) * lda];
          double a21 = a[offseta + (i + 1) + (row + 2) * lda];
          double b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          sum00 = a01 * b10 + sum00;
          sum10 = a11 * b10 + sum10;
          sum20 = a21 * b10 + sum20;
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double a10 = a[offseta + (i + 0) + (row + 1) * lda];
          double a20 = a[offseta + (i + 0) + (row + 2) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum10 = a10 * b00 + sum10;
          sum20 = a20 * b00 + sum20;
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + beta * c[offsetc + (row + 1) + (col + 0) * ldc];
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + beta * c[offsetc + (row + 2) + (col + 0) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10;
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20;
        }
      }
      for (; row < m; row += 1) {
        int i = 0;
        double sum00 = 0.0;
        for (; i < loopBound(k, Ti); i += Ti) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          double a01 = a[offseta + (i + 1) + (row + 0) * lda];
          double b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          sum00 = a01 * b10 + sum00;
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = a00 * b00 + sum00;
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
        }
      }
    }
  }

  protected void dgemmTT(int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    final int Trow = 3, Tcol = 3, Ti = 2;

    int col = 0;
    for (; col < loopBound(n, Tcol); col += Tcol) {
      int row = 0;
      for (; row < loopBound(m, Trow); row += Trow) {
        int i = 0;
        double sum00 = 0.0;
        double sum01 = 0.0;
        double sum02 = 0.0;
        double sum10 = 0.0;
        double sum11 = 0.0;
        double sum12 = 0.0;
        double sum20 = 0.0;
        double sum21 = 0.0;
        double sum22 = 0.0;
        for (; i < loopBound(k, Ti); i += Ti) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double a10 = a[offseta + (i + 0) + (row + 1) * lda];
          double a20 = a[offseta + (i + 0) + (row + 2) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          double b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          double b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          sum10 = a10 * b00 + sum10;
          sum11 = a10 * b01 + sum11;
          sum12 = a10 * b02 + sum12;
          sum20 = a20 * b00 + sum20;
          sum21 = a20 * b01 + sum21;
          sum22 = a20 * b02 + sum22;
          double a01 = a[offseta + (i + 1) + (row + 0) * lda];
          double a11 = a[offseta + (i + 1) + (row + 1) * lda];
          double a21 = a[offseta + (i + 1) + (row + 2) * lda];
          double b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          double b11 = b[offsetb + (col + 1) + (i + 1) * ldb];
          double b12 = b[offsetb + (col + 2) + (i + 1) * ldb];
          sum00 = a01 * b10 + sum00;
          sum01 = a01 * b11 + sum01;
          sum02 = a01 * b12 + sum02;
          sum10 = a11 * b10 + sum10;
          sum11 = a11 * b11 + sum11;
          sum12 = a11 * b12 + sum12;
          sum20 = a21 * b10 + sum20;
          sum21 = a21 * b11 + sum21;
          sum22 = a21 * b12 + sum22;
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double a10 = a[offseta + (i + 0) + (row + 1) * lda];
          double a20 = a[offseta + (i + 0) + (row + 2) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          double b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          double b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          sum10 = a10 * b00 + sum10;
          sum11 = a10 * b01 + sum11;
          sum12 = a10 * b02 + sum12;
          sum20 = a20 * b00 + sum20;
          sum21 = a20 * b01 + sum21;
          sum22 = a20 * b02 + sum22;
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + beta * c[offsetc + (row + 0) + (col + 1) * ldc];
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + beta * c[offsetc + (row + 0) + (col + 2) * ldc];
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + beta * c[offsetc + (row + 1) + (col + 0) * ldc];
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11 + beta * c[offsetc + (row + 1) + (col + 1) * ldc];
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12 + beta * c[offsetc + (row + 1) + (col + 2) * ldc];
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + beta * c[offsetc + (row + 2) + (col + 0) * ldc];
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21 + beta * c[offsetc + (row + 2) + (col + 1) * ldc];
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22 + beta * c[offsetc + (row + 2) + (col + 2) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01;
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02;
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10;
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11;
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12;
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20;
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21;
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22;
        }
      }
      for (; row < m; row += 1) {
        int i = 0;
        double sum00 = 0.0;
        double sum01 = 0.0;
        double sum02 = 0.0;
        for (; i < loopBound(k, Ti); i += Ti) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          double b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          double b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          double a01 = a[offseta + (i + 1) + (row + 0) * lda];
          double b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          double b11 = b[offsetb + (col + 1) + (i + 1) * ldb];
          double b12 = b[offsetb + (col + 2) + (i + 1) * ldb];
          sum00 = a01 * b10 + sum00;
          sum01 = a01 * b11 + sum01;
          sum02 = a01 * b12 + sum02;
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          double b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          double b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + beta * c[offsetc + (row + 0) + (col + 1) * ldc];
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + beta * c[offsetc + (row + 0) + (col + 2) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01;
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02;
        }
      }
    }
    for (; col < n; col += 1) {
      int row = 0;
      for (; row < loopBound(m, Trow); row += Trow) {
        int i = 0;
        double sum00 = 0.0;
        double sum10 = 0.0;
        double sum20 = 0.0;
        for (; i < loopBound(k, Ti); i += Ti) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double a10 = a[offseta + (i + 0) + (row + 1) * lda];
          double a20 = a[offseta + (i + 0) + (row + 2) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum10 = a10 * b00 + sum10;
          sum20 = a20 * b00 + sum20;
          double a01 = a[offseta + (i + 1) + (row + 0) * lda];
          double a11 = a[offseta + (i + 1) + (row + 1) * lda];
          double a21 = a[offseta + (i + 1) + (row + 2) * lda];
          double b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          sum00 = a01 * b10 + sum00;
          sum10 = a11 * b10 + sum10;
          sum20 = a21 * b10 + sum20;
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double a10 = a[offseta + (i + 0) + (row + 1) * lda];
          double a20 = a[offseta + (i + 0) + (row + 2) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum10 = a10 * b00 + sum10;
          sum20 = a20 * b00 + sum20;
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + beta * c[offsetc + (row + 1) + (col + 0) * ldc];
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + beta * c[offsetc + (row + 2) + (col + 0) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10;
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20;
        }
      }
      for (; row < m; row += 1) {
        int i = 0;
        double sum00 = 0.0;
        for (; i < loopBound(k, Ti); i += Ti) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          double a01 = a[offseta + (i + 1) + (row + 0) * lda];
          double b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          sum00 = a01 * b10 + sum00;
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
        }
      }
    }
  }

  protected void sgemmK(String transa, String transb, int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    if (alpha == 0.0f) {
      sgemmBeta(0, m, 0, n, beta, c, offsetc, ldc);
    } else if (m * n * k < 100 * 100 * 100) {
      // The matrices are small and it's faster to do the non-copying version
      if (lsame("N", transa) && lsame("N", transb)) {
        sgemmNN(m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
      } else if (lsame("N", transa)) {
        sgemmNT(m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
      } else if (lsame("N", transb)) {
        sgemmTN(m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
      } else {
        sgemmTT(m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
      }
    } else {
      final int Krow = (int)(Math.ceil((double)(Math.min(60,   m)) / 3) * 3),
                Kcol = (int)(Math.ceil((double)(Math.min(1000, n)) / 3) * 3),
                Ki   = (int)(Math.ceil((double)(Math.min(500,  k)) / 4) * 4);

      assert Krow > 0;
      assert Kcol > 0;
      assert Ki   > 0;

      float[] packeda = new float[Krow * Ki];
      float[] packedb = new float[Kcol * Ki];
      float[] packedc = new float[Kcol * Krow];

      // c = beta * c
      sgemmBeta(0, m, 0, n, beta, c, offsetc, ldc);
      // c += alpha * a * b
      for (int col = 0; col < n; col += Kcol) {
        int cols = col, cole = Math.min(col + Kcol, n);
        for (int i = 0; i < k; i += Ki) {
          int is = i, ie = Math.min(i + Ki, k);
          // pack b
          if (lsame("N", transb)) {
            sgecpyNN(ie - is, cole - cols, b, offsetb, ldb, is, cols, packedb, 0, Ki, 0, 0);
          } else {
            sgecpyTN(ie - is, cole - cols, b, offsetb, ldb, is, cols, packedb, 0, Ki, 0, 0);
          }
          // GEPP
          for (int row = 0; row < m; row += Krow) {
            int rows = row, rowe = Math.min(row + Krow, m);
            // pack A
            if (lsame("N", transa)) {
              sgecpyNT(rowe - rows, ie - is, a, offseta, lda, rows, is, packeda, 0, Ki, 0, 0);
            } else {
              sgecpyTT(rowe - rows, ie - is, a, offseta, lda, rows, is, packeda, 0, Ki, 0, 0);
            }
            // pack C
            sgecpyNN(rowe - rows, cole - cols, c, offsetc, ldc, rows, cols, packedc, 0, Krow, 0, 0);
            // GEBP
            sgebpTN(Krow, 0, rowe - rows, Kcol, 0, cole - cols, Ki, 0, ie - is,
                    alpha, packeda, 0, Ki, packedb, 0, Ki, beta, packedc, 0, Krow);
            // unpack C
            sgecpyNN(rowe - rows, cole - cols, packedc, 0, Krow, 0, 0, c, offsetc, ldc, rows, cols);
          }
        }
      }
    }
  }

  protected void sgemmBeta(int rows, int rowe, int cols, int cole, float beta, float[] c, int offsetc, int ldc) {
    if (beta != 1.0f) {
      int col = cols;
      for (; col < loopAlign(cols, cole, 4); col += 1) {
        int row = rows;
        for (; row < rowe; row += 1) {
          if (beta != 0.0f) {
            c[offsetc + row + (col + 0) * ldc] = beta * c[offsetc + row + (col + 0) * ldc];
          } else {
            c[offsetc + row + (col + 0) * ldc] = 0.0f;
          }
        }
      }
      for (; col < loopBound(cole, 4); col += 4) {
        int row = rows;
        for (; row < rowe; row += 1) {
          if (beta != 0.0f) {
            c[offsetc + row + (col + 0) * ldc] = beta * c[offsetc + row + (col + 0) * ldc];
            c[offsetc + row + (col + 1) * ldc] = beta * c[offsetc + row + (col + 1) * ldc];
            c[offsetc + row + (col + 2) * ldc] = beta * c[offsetc + row + (col + 2) * ldc];
            c[offsetc + row + (col + 3) * ldc] = beta * c[offsetc + row + (col + 3) * ldc];
          } else {
            c[offsetc + row + (col + 0) * ldc] = 0.0f;
            c[offsetc + row + (col + 1) * ldc] = 0.0f;
            c[offsetc + row + (col + 2) * ldc] = 0.0f;
            c[offsetc + row + (col + 3) * ldc] = 0.0f;
          }
        }
      }
      for (; col < cole; col += 1) {
        int row = rows;
        for (; row < rowe; row += 1) {
          if (beta != 0.0f) {
            c[offsetc + row + (col + 0) * ldc] = beta * c[offsetc + row + (col + 0) * ldc];
          } else {
            c[offsetc + row + (col + 0) * ldc] = 0.0f;
          }
        }
      }
    }
  }

  protected void sgecpyNN(int m, int n, float[] src, int offsetsrc, int ldsrc, int rowssrc, int colssrc, float[] dst, int offsetdst, int lddst, int rowsdst, int colsdst) {
    int col = 0;
    for (; col < loopBound(n, 4); col += 4) {
      System.arraycopy(src, offsetsrc + rowssrc + (colssrc + col + 0) * ldsrc, dst, offsetdst + rowsdst + (colsdst + col + 0) * lddst, m);
      System.arraycopy(src, offsetsrc + rowssrc + (colssrc + col + 1) * ldsrc, dst, offsetdst + rowsdst + (colsdst + col + 1) * lddst, m);
      System.arraycopy(src, offsetsrc + rowssrc + (colssrc + col + 2) * ldsrc, dst, offsetdst + rowsdst + (colsdst + col + 2) * lddst, m);
      System.arraycopy(src, offsetsrc + rowssrc + (colssrc + col + 3) * ldsrc, dst, offsetdst + rowsdst + (colsdst + col + 3) * lddst, m);
    }
    for (; col < n; col += 1) {
      System.arraycopy(src, offsetsrc + rowssrc + (colssrc + col + 0) * ldsrc, dst, offsetdst + rowsdst + (colsdst + col + 0) * lddst, m);
    }
  }

  protected void sgecpyNT(int m, int n, float[] src, int offsetsrc, int ldsrc, int rowssrc, int colssrc, float[] dst, int offsetdst, int lddst, int rowsdst, int colsdst) {
    int col = 0;
    for (; col < loopBound(n, 3); col += 3) {
      int row = 0;
      for (; row < loopBound(m, 3); row += 3) {
        dst[offsetdst + (colsdst + col + 0) + (rowsdst + row + 0) * lddst] = src[offsetsrc + (rowssrc + row + 0) + (colssrc + col + 0) * ldsrc];
        dst[offsetdst + (colsdst + col + 0) + (rowsdst + row + 1) * lddst] = src[offsetsrc + (rowssrc + row + 1) + (colssrc + col + 0) * ldsrc];
        dst[offsetdst + (colsdst + col + 0) + (rowsdst + row + 2) * lddst] = src[offsetsrc + (rowssrc + row + 2) + (colssrc + col + 0) * ldsrc];
        dst[offsetdst + (colsdst + col + 1) + (rowsdst + row + 0) * lddst] = src[offsetsrc + (rowssrc + row + 0) + (colssrc + col + 1) * ldsrc];
        dst[offsetdst + (colsdst + col + 1) + (rowsdst + row + 1) * lddst] = src[offsetsrc + (rowssrc + row + 1) + (colssrc + col + 1) * ldsrc];
        dst[offsetdst + (colsdst + col + 1) + (rowsdst + row + 2) * lddst] = src[offsetsrc + (rowssrc + row + 2) + (colssrc + col + 1) * ldsrc];
        dst[offsetdst + (colsdst + col + 2) + (rowsdst + row + 0) * lddst] = src[offsetsrc + (rowssrc + row + 0) + (colssrc + col + 2) * ldsrc];
        dst[offsetdst + (colsdst + col + 2) + (rowsdst + row + 1) * lddst] = src[offsetsrc + (rowssrc + row + 1) + (colssrc + col + 2) * ldsrc];
        dst[offsetdst + (colsdst + col + 2) + (rowsdst + row + 2) * lddst] = src[offsetsrc + (rowssrc + row + 2) + (colssrc + col + 2) * ldsrc];
      }
      for (; row < m; row += 1) {
        dst[offsetdst + (colsdst + col + 0) + (rowsdst + row + 0) * lddst] = src[offsetsrc + (rowssrc + row + 0) + (colssrc + col + 0) * ldsrc];
        dst[offsetdst + (colsdst + col + 1) + (rowsdst + row + 0) * lddst] = src[offsetsrc + (rowssrc + row + 0) + (colssrc + col + 1) * ldsrc];
        dst[offsetdst + (colsdst + col + 2) + (rowsdst + row + 0) * lddst] = src[offsetsrc + (rowssrc + row + 0) + (colssrc + col + 2) * ldsrc];
      }
    }
    for (; col < n; col += 1) {
      int row = 0;
      for (; row < loopBound(m, 3); row += 3) {
        dst[offsetdst + (colsdst + col + 0) + (rowsdst + row + 0) * lddst] = src[offsetsrc + (rowssrc + row + 0) + (colssrc + col + 0) * ldsrc];
        dst[offsetdst + (colsdst + col + 0) + (rowsdst + row + 1) * lddst] = src[offsetsrc + (rowssrc + row + 1) + (colssrc + col + 0) * ldsrc];
        dst[offsetdst + (colsdst + col + 0) + (rowsdst + row + 2) * lddst] = src[offsetsrc + (rowssrc + row + 2) + (colssrc + col + 0) * ldsrc];
      }
      for (; row < m; row += 1) {
        dst[offsetdst + (colsdst + col + 0) + (rowsdst + row + 0) * lddst] = src[offsetsrc + (rowssrc + row + 0) + (colssrc + col + 0) * ldsrc];
      }
    }
  }

  protected void sgecpyTN(int m, int n, float[] src, int offsetsrc, int ldsrc, int rowssrc, int colssrc, float[] dst, int offsetdst, int lddst, int rowsdst, int colsdst) {
    int row = 0;
    for (; row < loopBound(m, 3); row += 3) {
      int col = 0;
      for (; col < loopBound(n, 3); col += 3) {
        dst[offsetdst + (rowsdst + row + 0) + (colsdst + col + 0) * lddst] = src[offsetsrc + (colssrc + col + 0) + (rowssrc + row + 0) * ldsrc];
        dst[offsetdst + (rowsdst + row + 0) + (colsdst + col + 1) * lddst] = src[offsetsrc + (colssrc + col + 1) + (rowssrc + row + 0) * ldsrc];
        dst[offsetdst + (rowsdst + row + 0) + (colsdst + col + 2) * lddst] = src[offsetsrc + (colssrc + col + 2) + (rowssrc + row + 0) * ldsrc];
        dst[offsetdst + (rowsdst + row + 1) + (colsdst + col + 0) * lddst] = src[offsetsrc + (colssrc + col + 0) + (rowssrc + row + 1) * ldsrc];
        dst[offsetdst + (rowsdst + row + 1) + (colsdst + col + 1) * lddst] = src[offsetsrc + (colssrc + col + 1) + (rowssrc + row + 1) * ldsrc];
        dst[offsetdst + (rowsdst + row + 1) + (colsdst + col + 2) * lddst] = src[offsetsrc + (colssrc + col + 2) + (rowssrc + row + 1) * ldsrc];
        dst[offsetdst + (rowsdst + row + 2) + (colsdst + col + 0) * lddst] = src[offsetsrc + (colssrc + col + 0) + (rowssrc + row + 2) * ldsrc];
        dst[offsetdst + (rowsdst + row + 2) + (colsdst + col + 1) * lddst] = src[offsetsrc + (colssrc + col + 1) + (rowssrc + row + 2) * ldsrc];
        dst[offsetdst + (rowsdst + row + 2) + (colsdst + col + 2) * lddst] = src[offsetsrc + (colssrc + col + 2) + (rowssrc + row + 2) * ldsrc];
      }
      for (; col < n; col += 1) {
        dst[offsetdst + (rowsdst + row + 0) + (colsdst + col + 0) * lddst] = src[offsetsrc + (colssrc + col + 0) + (rowssrc + row + 0) * ldsrc];
        dst[offsetdst + (rowsdst + row + 1) + (colsdst + col + 0) * lddst] = src[offsetsrc + (colssrc + col + 0) + (rowssrc + row + 1) * ldsrc];
        dst[offsetdst + (rowsdst + row + 2) + (colsdst + col + 0) * lddst] = src[offsetsrc + (colssrc + col + 0) + (rowssrc + row + 2) * ldsrc];
      }
    }
    for (; row < m; row += 1) {
      int col = 0;
      for (; col < loopBound(n, 3); col += 3) {
        dst[offsetdst + (rowsdst + row + 0) + (colsdst + col + 0) * lddst] = src[offsetsrc + (colssrc + col + 0) + (rowssrc + row + 0) * ldsrc];
        dst[offsetdst + (rowsdst + row + 0) + (colsdst + col + 1) * lddst] = src[offsetsrc + (colssrc + col + 1) + (rowssrc + row + 0) * ldsrc];
        dst[offsetdst + (rowsdst + row + 0) + (colsdst + col + 2) * lddst] = src[offsetsrc + (colssrc + col + 2) + (rowssrc + row + 0) * ldsrc];
      }
      for (; col < n; col += 1) {
        dst[offsetdst + (rowsdst + row + 0) + (colsdst + col + 0) * lddst] = src[offsetsrc + (colssrc + col + 0) + (rowssrc + row + 0) * ldsrc];
      }
    }
  }

  protected void sgecpyTT(int m, int n, float[] src, int offsetsrc, int ldsrc, int rowssrc, int colssrc, float[] dst, int offsetdst, int lddst, int rowsdst, int colsdst) {
    int row = 0;
    for (; row < loopBound(m, 4); row += 4) {
      System.arraycopy(src, offsetsrc + colssrc + (rowssrc + row + 0) * ldsrc, dst, offsetdst + colsdst + (rowsdst + row + 0) * lddst, n);
      System.arraycopy(src, offsetsrc + colssrc + (rowssrc + row + 1) * ldsrc, dst, offsetdst + colsdst + (rowsdst + row + 1) * lddst, n);
      System.arraycopy(src, offsetsrc + colssrc + (rowssrc + row + 2) * ldsrc, dst, offsetdst + colsdst + (rowsdst + row + 2) * lddst, n);
      System.arraycopy(src, offsetsrc + colssrc + (rowssrc + row + 3) * ldsrc, dst, offsetdst + colsdst + (rowsdst + row + 3) * lddst, n);
    }
    for (; row < m; row += 1) {
      System.arraycopy(src, offsetsrc + colssrc + (rowssrc + row + 0) * ldsrc, dst, offsetdst + colsdst + (rowsdst + row + 0) * lddst, n);
    }
  }

  protected void sgebpTN(int m, int rows, int rowe, int n, int cols, int cole, int k, int is, int ie, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    final int Tcol = 3, Trow = 3, Ti = 2;

    int col = cols;
    for (; col < loopAlign(cols, cole, Tcol); col += 1) {
      int row = rows;
      for (; row < loopAlign(rows, rowe, Trow); row += 1) {
        float sum00 = 0.0f;
        for (int i = is; i < ie; i += 1) {
          float a0 = a[offseta + i + (row + 0) * lda];
          float b0 = b[offsetb + i + (col + 0) * ldb];
          sum00 = a0 * b0 + sum00;
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + c[offsetc + (row + 0) + (col + 0) * ldc];
      }
      for (; row < loopBound(rowe, Trow); row += Trow) {
        float sum00 = 0.0f;
        float sum10 = 0.0f;
        float sum20 = 0.0f;
        for (int i = is; i < ie; i += 1) {
          float a0 = a[offseta + i + (row + 0) * lda];
          float a1 = a[offseta + i + (row + 1) * lda];
          float a2 = a[offseta + i + (row + 2) * lda];
          float b0 = b[offsetb + i + (col + 0) * ldb];
          sum00 = a0 * b0 + sum00;
          sum10 = a1 * b0 + sum10;
          sum20 = a2 * b0 + sum20;
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + c[offsetc + (row + 0) + (col + 0) * ldc];
        c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + c[offsetc + (row + 1) + (col + 0) * ldc];
        c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + c[offsetc + (row + 2) + (col + 0) * ldc];
      }
      for (; row < rowe; row += 1) {
        float sum00 = 0.0f;
        for (int i = is; i < ie; i += 1) {
          float a0 = a[offseta + i + (row + 0) * lda];
          float b0 = b[offsetb + i + (col + 0) * ldb];
          sum00 = a0 * b0 + sum00;
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + c[offsetc + (row + 0) + (col + 0) * ldc];
      }
    }
    for (; col < loopBound(cole, Tcol); col += Tcol) {
      int row = rows;
      for (; row < loopAlign(rows, rowe, Trow); row += 1) {
        float sum00 = 0.0f;
        float sum01 = 0.0f;
        float sum02 = 0.0f;
        float sum03 = 0.0f;
        for (int i = is; i < ie; i += 1) {
          float a0 = a[offseta + i + (row + 0) * lda];
          float b0 = b[offsetb + i + (col + 0) * ldb];
          float b1 = b[offsetb + i + (col + 1) * ldb];
          float b2 = b[offsetb + i + (col + 2) * ldb];
          sum00 = a0 * b0 + sum00;
          sum01 = a0 * b1 + sum01;
          sum02 = a0 * b2 + sum02;
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + c[offsetc + (row + 0) + (col + 0) * ldc];
        c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + c[offsetc + (row + 0) + (col + 1) * ldc];
        c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + c[offsetc + (row + 0) + (col + 2) * ldc];
      }
      for (; row < loopBound(rowe, Trow); row += Trow) {
        sgepdotTN(m, row, row + Trow, n, col, col + Tcol, k, is, ie, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
      }
      for (; row < rowe; row += 1) {
        float sum00 = 0.0f;
        float sum01 = 0.0f;
        float sum02 = 0.0f;
        for (int i = is; i < ie; i += 1) {
          float a0 = a[offseta + i + (row + 0) * lda];
          float b0 = b[offsetb + i + (col + 0) * ldb];
          float b1 = b[offsetb + i + (col + 1) * ldb];
          float b2 = b[offsetb + i + (col + 2) * ldb];
          sum00 = a0 * b0 + sum00;
          sum01 = a0 * b1 + sum01;
          sum02 = a0 * b2 + sum02;
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + c[offsetc + (row + 0) + (col + 0) * ldc];
        c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + c[offsetc + (row + 0) + (col + 1) * ldc];
        c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + c[offsetc + (row + 0) + (col + 2) * ldc];
      }
    }
    for (; col < cole; col += 1) {
      int row = rows;
      for (; row < loopAlign(rows, rowe, Trow); row += 1) {
        float sum00 = 0.0f;
        for (int i = is; i < ie; i += 1) {
          float a0 = a[offseta + i + (row + 0) * lda];
          float b0 = b[offsetb + i + (col + 0) * ldb];
          sum00 = a0 * b0 + sum00;
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + c[offsetc + (row + 0) + (col + 0) * ldc];
      }
      for (; row < loopBound(rowe, Trow); row += Trow) {
        float sum00 = 0.0f;
        float sum10 = 0.0f;
        float sum20 = 0.0f;
        for (int i = is; i < ie; i += 1) {
          float a0 = a[offseta + i + (row + 0) * lda];
          float a1 = a[offseta + i + (row + 1) * lda];
          float a2 = a[offseta + i + (row + 2) * lda];
          float b0 = b[offsetb + i + (col + 0) * ldb];
          sum00 = a0 * b0 + sum00;
          sum10 = a1 * b0 + sum10;
          sum20 = a2 * b0 + sum20;
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + c[offsetc + (row + 0) + (col + 0) * ldc];
        c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + c[offsetc + (row + 1) + (col + 0) * ldc];
        c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + c[offsetc + (row + 2) + (col + 0) * ldc];
      }
      for (; row < rowe; row += 1) {
        float sum00 = 0.0f;
        for (int i = is; i < ie; i += 1) {
          float a0 = a[offseta + i + (row + 0) * lda];
          float b0 = b[offsetb + i + (col + 0) * ldb];
          sum00 = a0 * b0 + sum00;
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + c[offsetc + (row + 0) + (col + 0) * ldc];
      }
    }
  }

  protected void sgepdotTN(int m, int rows, int rowe, int n, int cols, int cole, int k, int is, int ie, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    final int Ti = 2;

    assert rowe - rows == 3;
    assert cole - cols == 3;

    int row = rows;
    int col = cols;
    int i = is;
    float sum00 = 0.0f;
    float sum01 = 0.0f;
    float sum02 = 0.0f;
    float sum10 = 0.0f;
    float sum11 = 0.0f;
    float sum12 = 0.0f;
    float sum20 = 0.0f;
    float sum21 = 0.0f;
    float sum22 = 0.0f;
    for (; i < loopAlign(is, ie, Ti); i += 1) {
      float a0 = a[offseta + i + (row + 0) * lda];
      float a1 = a[offseta + i + (row + 1) * lda];
      float a2 = a[offseta + i + (row + 2) * lda];
      float b0 = b[offsetb + i + (col + 0) * ldb];
      sum00 = a0 * b0 + sum00;
      sum10 = a1 * b0 + sum10;
      sum20 = a2 * b0 + sum20;
      float b1 = b[offsetb + i + (col + 1) * ldb];
      sum01 = a0 * b1 + sum01;
      sum11 = a1 * b1 + sum11;
      sum21 = a2 * b1 + sum21;
      float b2 = b[offsetb + i + (col + 2) * ldb];
      sum02 = a0 * b2 + sum02;
      sum12 = a1 * b2 + sum12;
      sum22 = a2 * b2 + sum22;
    }
    for (; i < loopBound(ie, Ti); i += Ti) {
      float a00 = a[offseta + (i + 0) + (row + 0) * lda];
      float a01 = a[offseta + (i + 0) + (row + 1) * lda];
      float a02 = a[offseta + (i + 0) + (row + 2) * lda];
      float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
      sum00 = a00 * b00 + sum00;
      sum10 = a01 * b00 + sum10;
      sum20 = a02 * b00 + sum20;
      float b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
      sum01 = a00 * b01 + sum01;
      sum11 = a01 * b01 + sum11;
      sum21 = a02 * b01 + sum21;
      float b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
      sum02 = a00 * b02 + sum02;
      sum12 = a01 * b02 + sum12;
      sum22 = a02 * b02 + sum22;
      float a10 = a[offseta + (i + 1) + (row + 0) * lda];
      float a11 = a[offseta + (i + 1) + (row + 1) * lda];
      float a12 = a[offseta + (i + 1) + (row + 2) * lda];
      float b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
      sum00 = a10 * b10 + sum00;
      sum10 = a11 * b10 + sum10;
      sum20 = a12 * b10 + sum20;
      float b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
      sum01 = a10 * b11 + sum01;
      sum11 = a11 * b11 + sum11;
      sum21 = a12 * b11 + sum21;
      float b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
      sum02 = a10 * b12 + sum02;
      sum12 = a11 * b12 + sum12;
      sum22 = a12 * b12 + sum22;
    }
    for (; i < ie; i += 1) {
      float a0 = a[offseta + i + (row + 0) * lda];
      float a1 = a[offseta + i + (row + 1) * lda];
      float a2 = a[offseta + i + (row + 2) * lda];
      float b0 = b[offsetb + i + (col + 0) * ldb];
      sum00 = a0 * b0 + sum00;
      sum10 = a1 * b0 + sum10;
      sum20 = a2 * b0 + sum20;
      float b1 = b[offsetb + i + (col + 1) * ldb];
      sum01 = a0 * b1 + sum01;
      sum11 = a1 * b1 + sum11;
      sum21 = a2 * b1 + sum21;
      float b2 = b[offsetb + i + (col + 2) * ldb];
      sum02 = a0 * b2 + sum02;
      sum12 = a1 * b2 + sum12;
      sum22 = a2 * b2 + sum22;
    }
    c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + c[offsetc + (row + 0) + (col + 0) * ldc];
    c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + c[offsetc + (row + 0) + (col + 1) * ldc];
    c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + c[offsetc + (row + 0) + (col + 2) * ldc];
    c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + c[offsetc + (row + 1) + (col + 0) * ldc];
    c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11 + c[offsetc + (row + 1) + (col + 1) * ldc];
    c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12 + c[offsetc + (row + 1) + (col + 2) * ldc];
    c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + c[offsetc + (row + 2) + (col + 0) * ldc];
    c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21 + c[offsetc + (row + 2) + (col + 1) * ldc];
    c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22 + c[offsetc + (row + 2) + (col + 2) * ldc];
  }

  protected void sgemmNN(int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    final int Trow = 3, Tcol = 3, Ti = 2;

    int col = 0;
    for (; col < loopBound(n, Tcol); col += Tcol) {
      int row = 0;
      for (; row < loopBound(m, Trow); row += Trow) {
        int i = 0;
        float sum00 = 0.0f;
        float sum01 = 0.0f;
        float sum02 = 0.0f;
        float sum10 = 0.0f;
        float sum11 = 0.0f;
        float sum12 = 0.0f;
        float sum20 = 0.0f;
        float sum21 = 0.0f;
        float sum22 = 0.0f;
        for (; i < loopBound(k, Ti); i += Ti) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float a10 = a[offseta + (row + 1) + (i + 0) * lda];
          float a20 = a[offseta + (row + 2) + (i + 0) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          float b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          float b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          sum10 = a10 * b00 + sum10;
          sum11 = a10 * b01 + sum11;
          sum12 = a10 * b02 + sum12;
          sum20 = a20 * b00 + sum20;
          sum21 = a20 * b01 + sum21;
          sum22 = a20 * b02 + sum22;
          float a01 = a[offseta + (row + 0) + (i + 1) * lda];
          float a11 = a[offseta + (row + 1) + (i + 1) * lda];
          float a21 = a[offseta + (row + 2) + (i + 1) * lda];
          float b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          float b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
          float b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
          sum00 = a01 * b10 + sum00;
          sum01 = a01 * b11 + sum01;
          sum02 = a01 * b12 + sum02;
          sum10 = a11 * b10 + sum10;
          sum11 = a11 * b11 + sum11;
          sum12 = a11 * b12 + sum12;
          sum20 = a21 * b10 + sum20;
          sum21 = a21 * b11 + sum21;
          sum22 = a21 * b12 + sum22;
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float a10 = a[offseta + (row + 1) + (i + 0) * lda];
          float a20 = a[offseta + (row + 2) + (i + 0) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          float b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          float b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          sum10 = a10 * b00 + sum10;
          sum11 = a10 * b01 + sum11;
          sum12 = a10 * b02 + sum12;
          sum20 = a20 * b00 + sum20;
          sum21 = a20 * b01 + sum21;
          sum22 = a20 * b02 + sum22;
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + beta * c[offsetc + (row + 0) + (col + 1) * ldc];
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + beta * c[offsetc + (row + 0) + (col + 2) * ldc];
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + beta * c[offsetc + (row + 1) + (col + 0) * ldc];
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11 + beta * c[offsetc + (row + 1) + (col + 1) * ldc];
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12 + beta * c[offsetc + (row + 1) + (col + 2) * ldc];
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + beta * c[offsetc + (row + 2) + (col + 0) * ldc];
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21 + beta * c[offsetc + (row + 2) + (col + 1) * ldc];
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22 + beta * c[offsetc + (row + 2) + (col + 2) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01;
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02;
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10;
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11;
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12;
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20;
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21;
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22;
        }
      }
      for (; row < m; row += 1) {
        int i = 0;
        float sum00 = 0.0f;
        float sum01 = 0.0f;
        float sum02 = 0.0f;
        for (; i < loopBound(k, Ti); i += Ti) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          float b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          float b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          float a01 = a[offseta + (row + 0) + (i + 1) * lda];
          float b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          float b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
          float b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
          sum00 = a01 * b10 + sum00;
          sum01 = a01 * b11 + sum01;
          sum02 = a01 * b12 + sum02;
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          float b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          float b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + beta * c[offsetc + (row + 0) + (col + 1) * ldc];
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + beta * c[offsetc + (row + 0) + (col + 2) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01;
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02;
        }
      }
    }
    for (; col < n; col += 1) {
      int row = 0;
      for (; row < loopBound(m, Trow); row += Trow) {
        int i = 0;
        float sum00 = 0.0f;
        float sum10 = 0.0f;
        float sum20 = 0.0f;
        for (; i < loopBound(k, Ti); i += Ti) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float a10 = a[offseta + (row + 1) + (i + 0) * lda];
          float a20 = a[offseta + (row + 2) + (i + 0) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum10 = a10 * b00 + sum10;
          sum20 = a20 * b00 + sum20;
          float a01 = a[offseta + (row + 0) + (i + 1) * lda];
          float a11 = a[offseta + (row + 1) + (i + 1) * lda];
          float a21 = a[offseta + (row + 2) + (i + 1) * lda];
          float b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          sum00 = a01 * b10 + sum00;
          sum10 = a11 * b10 + sum10;
          sum20 = a21 * b10 + sum20;
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float a10 = a[offseta + (row + 1) + (i + 0) * lda];
          float a20 = a[offseta + (row + 2) + (i + 0) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum10 = a10 * b00 + sum10;
          sum20 = a20 * b00 + sum20;
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + beta * c[offsetc + (row + 1) + (col + 0) * ldc];
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + beta * c[offsetc + (row + 2) + (col + 0) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10;
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20;
        }
      }
      for (; row < m; row += 1) {
        int i = 0;
        float sum00 = 0.0f;
        for (; i < loopBound(k, Ti); i += Ti) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          float a01 = a[offseta + (row + 0) + (i + 1) * lda];
          float b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          sum00 = a01 * b10 + sum00;
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = a00 * b00 + sum00;
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
        }
      }
    }
  }

  protected void sgemmNT(int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    final int Trow = 3, Tcol = 3, Ti = 2;
    int col = 0;
    for (; col < loopBound(n, Tcol); col += Tcol) {
      int row = 0;
      for (; row < loopBound(m, Trow); row += Trow) {
        int i = 0;
        float sum00 = 0.0f;
        float sum01 = 0.0f;
        float sum02 = 0.0f;
        float sum10 = 0.0f;
        float sum11 = 0.0f;
        float sum12 = 0.0f;
        float sum20 = 0.0f;
        float sum21 = 0.0f;
        float sum22 = 0.0f;
        for (; i < loopBound(k, Ti); i += Ti) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float a10 = a[offseta + (row + 1) + (i + 0) * lda];
          float a20 = a[offseta + (row + 2) + (i + 0) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          float b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          float b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          sum10 = a10 * b00 + sum10;
          sum11 = a10 * b01 + sum11;
          sum12 = a10 * b02 + sum12;
          sum20 = a20 * b00 + sum20;
          sum21 = a20 * b01 + sum21;
          sum22 = a20 * b02 + sum22;
          float a01 = a[offseta + (row + 0) + (i + 1) * lda];
          float a11 = a[offseta + (row + 1) + (i + 1) * lda];
          float a21 = a[offseta + (row + 2) + (i + 1) * lda];
          float b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          float b11 = b[offsetb + (col + 1) + (i + 1) * ldb];
          float b12 = b[offsetb + (col + 2) + (i + 1) * ldb];
          sum00 = a01 * b10 + sum00;
          sum01 = a01 * b11 + sum01;
          sum02 = a01 * b12 + sum02;
          sum10 = a11 * b10 + sum10;
          sum11 = a11 * b11 + sum11;
          sum12 = a11 * b12 + sum12;
          sum20 = a21 * b10 + sum20;
          sum21 = a21 * b11 + sum21;
          sum22 = a21 * b12 + sum22;
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float a10 = a[offseta + (row + 1) + (i + 0) * lda];
          float a20 = a[offseta + (row + 2) + (i + 0) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          float b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          float b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          sum10 = a10 * b00 + sum10;
          sum11 = a10 * b01 + sum11;
          sum12 = a10 * b02 + sum12;
          sum20 = a20 * b00 + sum20;
          sum21 = a20 * b01 + sum21;
          sum22 = a20 * b02 + sum22;
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + beta * c[offsetc + (row + 0) + (col + 1) * ldc];
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + beta * c[offsetc + (row + 0) + (col + 2) * ldc];
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + beta * c[offsetc + (row + 1) + (col + 0) * ldc];
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11 + beta * c[offsetc + (row + 1) + (col + 1) * ldc];
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12 + beta * c[offsetc + (row + 1) + (col + 2) * ldc];
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + beta * c[offsetc + (row + 2) + (col + 0) * ldc];
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21 + beta * c[offsetc + (row + 2) + (col + 1) * ldc];
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22 + beta * c[offsetc + (row + 2) + (col + 2) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01;
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02;
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10;
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11;
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12;
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20;
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21;
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22;
        }
      }
      for (; row < m; row += 1) {
        int i = 0;
        float sum00 = 0.0f;
        float sum01 = 0.0f;
        float sum02 = 0.0f;
        for (; i < loopBound(k, Ti); i += Ti) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          float b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          float b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          float a01 = a[offseta + (row + 0) + (i + 1) * lda];
          float b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          float b11 = b[offsetb + (col + 1) + (i + 1) * ldb];
          float b12 = b[offsetb + (col + 2) + (i + 1) * ldb];
          sum00 = a01 * b10 + sum00;
          sum01 = a01 * b11 + sum01;
          sum02 = a01 * b12 + sum02;
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          float b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          float b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + beta * c[offsetc + (row + 0) + (col + 1) * ldc];
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + beta * c[offsetc + (row + 0) + (col + 2) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01;
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02;
        }
      }
    }
    for (; col < n; col += 1) {
      int row = 0;
      for (; row < loopBound(m, Trow); row += Trow) {
        int i = 0;
        float sum00 = 0.0f;
        float sum10 = 0.0f;
        float sum20 = 0.0f;
        for (; i < loopBound(k, Ti); i += Ti) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float a10 = a[offseta + (row + 1) + (i + 0) * lda];
          float a20 = a[offseta + (row + 2) + (i + 0) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum10 = a10 * b00 + sum10;
          sum20 = a20 * b00 + sum20;
          float a01 = a[offseta + (row + 0) + (i + 1) * lda];
          float a11 = a[offseta + (row + 1) + (i + 1) * lda];
          float a21 = a[offseta + (row + 2) + (i + 1) * lda];
          float b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          sum00 = a01 * b10 + sum00;
          sum10 = a11 * b10 + sum10;
          sum20 = a21 * b10 + sum20;
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float a10 = a[offseta + (row + 1) + (i + 0) * lda];
          float a20 = a[offseta + (row + 2) + (i + 0) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum10 = a10 * b00 + sum10;
          sum20 = a20 * b00 + sum20;
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + beta * c[offsetc + (row + 1) + (col + 0) * ldc];
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + beta * c[offsetc + (row + 2) + (col + 0) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10;
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20;
        }
      }
      for (; row < m; row += 1) {
        int i = 0;
        float sum00 = 0.0f;
        for (; i < loopBound(k, Ti); i += Ti) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          float a01 = a[offseta + (row + 0) + (i + 1) * lda];
          float b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          sum00 = a01 * b10 + sum00;
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
        }
      }
    }
  }

  protected void sgemmTN(int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    final int Trow = 3, Tcol = 3, Ti = 2;

    int col = 0;
    for (; col < loopBound(n, Tcol); col += Tcol) {
      int row = 0;
      for (; row < loopBound(m, Trow); row += Trow) {
        int i = 0;
        float sum00 = 0.0f;
        float sum01 = 0.0f;
        float sum02 = 0.0f;
        float sum10 = 0.0f;
        float sum11 = 0.0f;
        float sum12 = 0.0f;
        float sum20 = 0.0f;
        float sum21 = 0.0f;
        float sum22 = 0.0f;
        for (; i < loopBound(k, Ti); i += Ti) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float a10 = a[offseta + (i + 0) + (row + 1) * lda];
          float a20 = a[offseta + (i + 0) + (row + 2) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          float b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          float b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          sum10 = a10 * b00 + sum10;
          sum11 = a10 * b01 + sum11;
          sum12 = a10 * b02 + sum12;
          sum20 = a20 * b00 + sum20;
          sum21 = a20 * b01 + sum21;
          sum22 = a20 * b02 + sum22;
          float a01 = a[offseta + (i + 1) + (row + 0) * lda];
          float a11 = a[offseta + (i + 1) + (row + 1) * lda];
          float a21 = a[offseta + (i + 1) + (row + 2) * lda];
          float b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          float b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
          float b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
          sum00 = a01 * b10 + sum00;
          sum01 = a01 * b11 + sum01;
          sum02 = a01 * b12 + sum02;
          sum10 = a11 * b10 + sum10;
          sum11 = a11 * b11 + sum11;
          sum12 = a11 * b12 + sum12;
          sum20 = a21 * b10 + sum20;
          sum21 = a21 * b11 + sum21;
          sum22 = a21 * b12 + sum22;
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float a10 = a[offseta + (i + 0) + (row + 1) * lda];
          float a20 = a[offseta + (i + 0) + (row + 2) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          float b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          float b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          sum10 = a10 * b00 + sum10;
          sum11 = a10 * b01 + sum11;
          sum12 = a10 * b02 + sum12;
          sum20 = a20 * b00 + sum20;
          sum21 = a20 * b01 + sum21;
          sum22 = a20 * b02 + sum22;
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + beta * c[offsetc + (row + 0) + (col + 1) * ldc];
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + beta * c[offsetc + (row + 0) + (col + 2) * ldc];
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + beta * c[offsetc + (row + 1) + (col + 0) * ldc];
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11 + beta * c[offsetc + (row + 1) + (col + 1) * ldc];
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12 + beta * c[offsetc + (row + 1) + (col + 2) * ldc];
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + beta * c[offsetc + (row + 2) + (col + 0) * ldc];
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21 + beta * c[offsetc + (row + 2) + (col + 1) * ldc];
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22 + beta * c[offsetc + (row + 2) + (col + 2) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01;
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02;
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10;
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11;
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12;
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20;
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21;
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22;
        }
      }
      for (; row < m; row += 1) {
        int i = 0;
        float sum00 = 0.0f;
        float sum01 = 0.0f;
        float sum02 = 0.0f;
        for (; i < loopBound(k, Ti); i += Ti) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          float b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          float b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          float a01 = a[offseta + (i + 1) + (row + 0) * lda];
          float b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          float b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
          float b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
          sum00 = a01 * b10 + sum00;
          sum01 = a01 * b11 + sum01;
          sum02 = a01 * b12 + sum02;
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          float b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          float b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + beta * c[offsetc + (row + 0) + (col + 1) * ldc];
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + beta * c[offsetc + (row + 0) + (col + 2) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01;
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02;
        }
      }
    }
    for (; col < n; col += 1) {
      int row = 0;
      for (; row < loopBound(m, Trow); row += Trow) {
        int i = 0;
        float sum00 = 0.0f;
        float sum10 = 0.0f;
        float sum20 = 0.0f;
        for (; i < loopBound(k, Ti); i += Ti) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float a10 = a[offseta + (i + 0) + (row + 1) * lda];
          float a20 = a[offseta + (i + 0) + (row + 2) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum10 = a10 * b00 + sum10;
          sum20 = a20 * b00 + sum20;
          float a01 = a[offseta + (i + 1) + (row + 0) * lda];
          float a11 = a[offseta + (i + 1) + (row + 1) * lda];
          float a21 = a[offseta + (i + 1) + (row + 2) * lda];
          float b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          sum00 = a01 * b10 + sum00;
          sum10 = a11 * b10 + sum10;
          sum20 = a21 * b10 + sum20;
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float a10 = a[offseta + (i + 0) + (row + 1) * lda];
          float a20 = a[offseta + (i + 0) + (row + 2) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum10 = a10 * b00 + sum10;
          sum20 = a20 * b00 + sum20;
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + beta * c[offsetc + (row + 1) + (col + 0) * ldc];
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + beta * c[offsetc + (row + 2) + (col + 0) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10;
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20;
        }
      }
      for (; row < m; row += 1) {
        int i = 0;
        float sum00 = 0.0f;
        for (; i < loopBound(k, Ti); i += Ti) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          float a01 = a[offseta + (i + 1) + (row + 0) * lda];
          float b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          sum00 = a01 * b10 + sum00;
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = a00 * b00 + sum00;
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
        }
      }
    }
  }

  protected void sgemmTT(int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    final int Trow = 3, Tcol = 3, Ti = 2;

    int col = 0;
    for (; col < loopBound(n, Tcol); col += Tcol) {
      int row = 0;
      for (; row < loopBound(m, Trow); row += Trow) {
        int i = 0;
        float sum00 = 0.0f;
        float sum01 = 0.0f;
        float sum02 = 0.0f;
        float sum10 = 0.0f;
        float sum11 = 0.0f;
        float sum12 = 0.0f;
        float sum20 = 0.0f;
        float sum21 = 0.0f;
        float sum22 = 0.0f;
        for (; i < loopBound(k, Ti); i += Ti) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float a10 = a[offseta + (i + 0) + (row + 1) * lda];
          float a20 = a[offseta + (i + 0) + (row + 2) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          float b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          float b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          sum10 = a10 * b00 + sum10;
          sum11 = a10 * b01 + sum11;
          sum12 = a10 * b02 + sum12;
          sum20 = a20 * b00 + sum20;
          sum21 = a20 * b01 + sum21;
          sum22 = a20 * b02 + sum22;
          float a01 = a[offseta + (i + 1) + (row + 0) * lda];
          float a11 = a[offseta + (i + 1) + (row + 1) * lda];
          float a21 = a[offseta + (i + 1) + (row + 2) * lda];
          float b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          float b11 = b[offsetb + (col + 1) + (i + 1) * ldb];
          float b12 = b[offsetb + (col + 2) + (i + 1) * ldb];
          sum00 = a01 * b10 + sum00;
          sum01 = a01 * b11 + sum01;
          sum02 = a01 * b12 + sum02;
          sum10 = a11 * b10 + sum10;
          sum11 = a11 * b11 + sum11;
          sum12 = a11 * b12 + sum12;
          sum20 = a21 * b10 + sum20;
          sum21 = a21 * b11 + sum21;
          sum22 = a21 * b12 + sum22;
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float a10 = a[offseta + (i + 0) + (row + 1) * lda];
          float a20 = a[offseta + (i + 0) + (row + 2) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          float b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          float b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          sum10 = a10 * b00 + sum10;
          sum11 = a10 * b01 + sum11;
          sum12 = a10 * b02 + sum12;
          sum20 = a20 * b00 + sum20;
          sum21 = a20 * b01 + sum21;
          sum22 = a20 * b02 + sum22;
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + beta * c[offsetc + (row + 0) + (col + 1) * ldc];
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + beta * c[offsetc + (row + 0) + (col + 2) * ldc];
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + beta * c[offsetc + (row + 1) + (col + 0) * ldc];
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11 + beta * c[offsetc + (row + 1) + (col + 1) * ldc];
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12 + beta * c[offsetc + (row + 1) + (col + 2) * ldc];
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + beta * c[offsetc + (row + 2) + (col + 0) * ldc];
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21 + beta * c[offsetc + (row + 2) + (col + 1) * ldc];
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22 + beta * c[offsetc + (row + 2) + (col + 2) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01;
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02;
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10;
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11;
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12;
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20;
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21;
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22;
        }
      }
      for (; row < m; row += 1) {
        int i = 0;
        float sum00 = 0.0f;
        float sum01 = 0.0f;
        float sum02 = 0.0f;
        for (; i < loopBound(k, Ti); i += Ti) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          float b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          float b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
          float a01 = a[offseta + (i + 1) + (row + 0) * lda];
          float b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          float b11 = b[offsetb + (col + 1) + (i + 1) * ldb];
          float b12 = b[offsetb + (col + 2) + (i + 1) * ldb];
          sum00 = a01 * b10 + sum00;
          sum01 = a01 * b11 + sum01;
          sum02 = a01 * b12 + sum02;
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          float b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          float b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum01 = a00 * b01 + sum01;
          sum02 = a00 * b02 + sum02;
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + beta * c[offsetc + (row + 0) + (col + 1) * ldc];
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + beta * c[offsetc + (row + 0) + (col + 2) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01;
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02;
        }
      }
    }
    for (; col < n; col += 1) {
      int row = 0;
      for (; row < loopBound(m, Trow); row += Trow) {
        int i = 0;
        float sum00 = 0.0f;
        float sum10 = 0.0f;
        float sum20 = 0.0f;
        for (; i < loopBound(k, Ti); i += Ti) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float a10 = a[offseta + (i + 0) + (row + 1) * lda];
          float a20 = a[offseta + (i + 0) + (row + 2) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum10 = a10 * b00 + sum10;
          sum20 = a20 * b00 + sum20;
          float a01 = a[offseta + (i + 1) + (row + 0) * lda];
          float a11 = a[offseta + (i + 1) + (row + 1) * lda];
          float a21 = a[offseta + (i + 1) + (row + 2) * lda];
          float b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          sum00 = a01 * b10 + sum00;
          sum10 = a11 * b10 + sum10;
          sum20 = a21 * b10 + sum20;
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float a10 = a[offseta + (i + 0) + (row + 1) * lda];
          float a20 = a[offseta + (i + 0) + (row + 2) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          sum10 = a10 * b00 + sum10;
          sum20 = a20 * b00 + sum20;
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + beta * c[offsetc + (row + 1) + (col + 0) * ldc];
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + beta * c[offsetc + (row + 2) + (col + 0) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10;
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20;
        }
      }
      for (; row < m; row += 1) {
        int i = 0;
        float sum00 = 0.0f;
        for (; i < loopBound(k, Ti); i += Ti) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
          float a01 = a[offseta + (i + 1) + (row + 0) * lda];
          float b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          sum00 = a01 * b10 + sum00;
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = a00 * b00 + sum00;
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
        }
      }
    }
  }

  protected void dgemvK(String trans, int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    if (alpha == 0.0) {
      int len = lsame("N", trans) ? m : n;
      for (int i = 0, iy = incy < 0 ? (len - 1) * -incy : 0; i < len; i += 1, iy += incy) {
        if (beta != 0.0) {
          y[offsety + iy] = beta * y[offsety + iy];
        } else {
          y[offsety + iy] = 0.0;
        }
      }
    } else if (lsame("N", trans)) {
      dgemvN(m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
    } else if (lsame("T", trans) || lsame("C", trans)) {
      dgemvT(m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
    }
  }

  protected void dgemvN(int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    if (beta != 1.0) {
      int row = 0, iy = incy < 0 ? (m - 1) * -incy : 0;
      for (; row < m; row += 1, iy += incy) {
        if (beta != 0.0) {
          y[offsety + iy] = beta * y[offsety + iy];
        } else {
          y[offsety + iy] = 0.0;
        }
      }
    }
    int col = 0, ix = incx < 0 ? (n - 1) * -incx : 0;
    for (; col < loopBound(n, 4); col += 4, ix += incx * 4) {
      int row = 0, iy = incy < 0 ? (m - 1) * -incy : 0;
      double alphax0 = alpha * x[offsetx + ix + incx * 0];
      double alphax1 = alpha * x[offsetx + ix + incx * 1];
      double alphax2 = alpha * x[offsetx + ix + incx * 2];
      double alphax3 = alpha * x[offsetx + ix + incx * 3];
      for (; row < m; row += 1, iy += incy) {
        y[offsety + iy] += alphax0 * a[offseta + row + (col + 0) * lda]
                        +  alphax1 * a[offseta + row + (col + 1) * lda]
                        +  alphax2 * a[offseta + row + (col + 2) * lda]
                        +  alphax3 * a[offseta + row + (col + 3) * lda];
      }
    }
    for (; col < n; col += 1, ix += incx) {
      int row = 0, iy = incy < 0 ? (m - 1) * -incy : 0;
      double alphax = alpha * x[offsetx + ix];
      for (; row < m; row += 1, iy += incy) {
        y[offsety + iy] += alphax * a[offseta + row + col * lda];
      }
    }
  }

  protected void dgemvT(int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    int col = 0, iy = incy < 0 ? (n - 1) * -incy : 0;
    for (; col < loopBound(n, 4); col += 4, iy += incy * 4) {
      int row = 0, ix = incx < 0 ? (m - 1) * -incx : 0;
      double sum0 = 0.0;
      double sum1 = 0.0;
      double sum2 = 0.0;
      double sum3 = 0.0;
      for (; row < m; row += 1, ix += incx) {
        double xix = x[offsetx + ix];
        sum0 += xix * a[offseta + row + (col + 0) * lda];
        sum1 += xix * a[offseta + row + (col + 1) * lda];
        sum2 += xix * a[offseta + row + (col + 2) * lda];
        sum3 += xix * a[offseta + row + (col + 3) * lda];
      }
      if (beta != 0.0) {
        y[offsety + iy + incy * 0] = alpha * sum0 + beta * y[offsety + iy + incy * 0];
        y[offsety + iy + incy * 1] = alpha * sum1 + beta * y[offsety + iy + incy * 1];
        y[offsety + iy + incy * 2] = alpha * sum2 + beta * y[offsety + iy + incy * 2];
        y[offsety + iy + incy * 3] = alpha * sum3 + beta * y[offsety + iy + incy * 3];
      } else {
        y[offsety + iy + incy * 0] = alpha * sum0;
        y[offsety + iy + incy * 1] = alpha * sum1;
        y[offsety + iy + incy * 2] = alpha * sum2;
        y[offsety + iy + incy * 3] = alpha * sum3;
      }
    }
    for (; col < n; col += 1, iy += incy) {
      int row = 0, ix = incx < 0 ? (m - 1) * -incx : 0;
      double sum = 0.0;
      for (; row < m; row += 1, ix += incx) {
        sum += x[offsetx + ix] * a[offseta + row + col * lda];
      }
      if (beta != 0.0) {
        y[offsety + iy] = alpha * sum + beta * y[offsety + iy];
      } else {
        y[offsety + iy] = alpha * sum;
      }
    }
  }

  protected void sgemvK(String trans, int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    if (alpha == 0.0f) {
      int len = lsame("N", trans) ? m : n;
      for (int i = 0, iy = incy < 0 ? (len - 1) * -incy : 0; i < len; i += 1, iy += incy) {
        if (beta != 0.0f) {
          y[offsety + iy] = beta * y[offsety + iy];
        } else {
          y[offsety + iy] = 0.0f;
        }
      }
    } else if (lsame("N", trans)) {
      sgemvN(m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
    } else if (lsame("T", trans) || lsame("C", trans)) {
      sgemvT(m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
    }
  }

  protected void sgemvN(int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    // y = beta * y
    for (int row = 0, iy = incy < 0 ? (m - 1) * -incy : 0; row < m; row += 1, iy += incy) {
      if (beta != 0.0f) {
        y[offsety + iy] = beta * y[offsety + iy];
      } else {
        y[offsety + iy] = 0.0f;
      }
    }
    // y += alpha * A * x
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
  }

  protected void sgemvT(int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
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
      if (beta != 0.0f) {
        y[offsety + iy + incy * 0] = alpha * sum0 + beta * y[offsety + iy + incy * 0];
        y[offsety + iy + incy * 1] = alpha * sum1 + beta * y[offsety + iy + incy * 1];
        y[offsety + iy + incy * 2] = alpha * sum2 + beta * y[offsety + iy + incy * 2];
        y[offsety + iy + incy * 3] = alpha * sum3 + beta * y[offsety + iy + incy * 3];
        y[offsety + iy + incy * 4] = alpha * sum4 + beta * y[offsety + iy + incy * 4];
        y[offsety + iy + incy * 5] = alpha * sum5 + beta * y[offsety + iy + incy * 5];
        y[offsety + iy + incy * 6] = alpha * sum6 + beta * y[offsety + iy + incy * 6];
        y[offsety + iy + incy * 7] = alpha * sum7 + beta * y[offsety + iy + incy * 7];
      } else {
        y[offsety + iy + incy * 0] = alpha * sum0;
        y[offsety + iy + incy * 1] = alpha * sum1;
        y[offsety + iy + incy * 2] = alpha * sum2;
        y[offsety + iy + incy * 3] = alpha * sum3;
        y[offsety + iy + incy * 4] = alpha * sum4;
        y[offsety + iy + incy * 5] = alpha * sum5;
        y[offsety + iy + incy * 6] = alpha * sum6;
        y[offsety + iy + incy * 7] = alpha * sum7;
      }
    }
    for (; col < n; col += 1, iy += incy) {
      float sum = 0.0f;
      for (int row = 0, ix = incx < 0 ? (m - 1) * -incx : 0; row < m; row += 1, ix += incx) {
        sum += x[offsetx + ix] * a[offseta + row + col * lda];
      }
      if (beta != 0.0f) {
        y[offsety + iy] = alpha * sum + beta * y[offsety + iy];
      } else {
        y[offsety + iy] = alpha * sum;
      }
    }
  }

  protected void dgerK(int m, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
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

  protected void sgerK(int m, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
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

  protected double dnrm2K(int n, double[] x, int offsetx, int incx) {
    int ix = 0;
    double sum0 = 0.0;
    double sum1 = 0.0;
    double sum2 = 0.0;
    double sum3 = 0.0;
    if (incx == 1) {
      for (; ix < loopBound(n, 4); ix += 4) {
        double x0 = x[offsetx + ix + 0];
        double x1 = x[offsetx + ix + 1];
        double x2 = x[offsetx + ix + 2];
        double x3 = x[offsetx + ix + 3];
        sum0 += x0 * x0;
        sum1 += x1 * x1;
        sum2 += x2 * x2;
        sum3 += x3 * x3;
      }
    } else {
      for (; ix < loopBound(n, 4) * incx; ix += 4 * incx) {
        double x0 = x[offsetx + ix + (0 * incx)];
        double x1 = x[offsetx + ix + (1 * incx)];
        double x2 = x[offsetx + ix + (2 * incx)];
        double x3 = x[offsetx + ix + (3 * incx)];
        sum0 += x0 * x0;
        sum1 += x1 * x1;
        sum2 += x2 * x2;
        sum3 += x3 * x3;
      }
    }
    double sum = sum0 + sum1 + sum2 + sum3;
    for (; ix < n * incx; ix += incx) {
      double x0 = x[offsetx + ix + 0];
      sum += x0 * x0;
    }
    return Math.sqrt(sum);
  }

  protected float snrm2K(int n, float[] x, int offsetx, int incx) {
    int ix = 0;
    float sum0 = 0.0f;
    float sum1 = 0.0f;
    float sum2 = 0.0f;
    float sum3 = 0.0f;
    if (incx == 1) {
      for (; ix < loopBound(n, 4); ix += 4) {
        float x0 = x[offsetx + ix + 0];
        float x1 = x[offsetx + ix + 1];
        float x2 = x[offsetx + ix + 2];
        float x3 = x[offsetx + ix + 3];
        sum0 += x0 * x0;
        sum1 += x1 * x1;
        sum2 += x2 * x2;
        sum3 += x3 * x3;
      }
    } else {
      for (; ix < loopBound(n, 4) * incx; ix += 4 * incx) {
        float x0 = x[offsetx + ix + (0 * incx)];
        float x1 = x[offsetx + ix + (1 * incx)];
        float x2 = x[offsetx + ix + (2 * incx)];
        float x3 = x[offsetx + ix + (3 * incx)];
        sum0 += x0 * x0;
        sum1 += x1 * x1;
        sum2 += x2 * x2;
        sum3 += x3 * x3;
      }
    }
    float sum = sum0 + sum1 + sum2 + sum3;
    for (; ix < n * incx; ix += incx) {
      float x0 = x[offsetx + ix + 0];
      sum += x0 * x0;
    }
    return (float)Math.sqrt(sum);
  }

  protected void drotK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double c, double s) {
    if (incx == 1 && incy == 1) {
      for (int ix = 0, iy = 0; ix < n && iy < n; ix += 1, iy += 1) {
        double x0 = x[offsetx + ix];
        double y0 = y[offsety + iy];
        x[offsetx + ix] = c * x0 + s * y0;
        y[offsety + iy] = c * y0 - s * x0;
      }
    } else {
      for (int ix = incx < 0 ? (n - 1) * -incx : 0,
               iy = incy < 0 ? (n - 1) * -incy : 0;
           (incx < 0 ? ix >= 0 : ix < n * incx)
            && (incy < 0 ? iy >= 0 : iy < n * incy);
           ix += incx, iy += incy) {
        double x0 = x[offsetx + ix];
        double y0 = y[offsety + iy];
        x[offsetx + ix] = c * x0 + s * y0;
        y[offsety + iy] = c * y0 - s * x0;
      }
    }
  }

  protected void srotK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float c, float s) {
    if (incx == 1 && incy == 1) {
      for (int ix = 0, iy = 0; ix < n && iy < n; ix += 1, iy += 1) {
        float x0 = x[offsetx + ix];
        float y0 = y[offsety + iy];
        x[offsetx + ix] = c * x0 + s * y0;
        y[offsety + iy] = c * y0 - s * x0;
      }
    } else {
      for (int ix = incx < 0 ? (n - 1) * -incx : 0,
               iy = incy < 0 ? (n - 1) * -incy : 0;
           (incx < 0 ? ix >= 0 : ix < n * incx)
            && (incy < 0 ? iy >= 0 : iy < n * incy);
           ix += incx, iy += incy) {
        float x0 = x[offsetx + ix];
        float y0 = y[offsety + iy];
        x[offsetx + ix] = c * x0 + s * y0;
        y[offsety + iy] = c * y0 - s * x0;
      }
    }
  }

  protected void drotmK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] param, int offsetparam) {
    org.netlib.blas.Drotm.drotm(n, x, offsetx, incx, y, offsety, incy, param, offsetparam);
  }

  protected void srotmK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] param, int offsetparam) {
    org.netlib.blas.Srotm.srotm(n, x, offsetx, incx, y, offsety, incy, param, offsetparam);
  }

  protected void drotmgK(org.netlib.util.doubleW dd1, org.netlib.util.doubleW dd2, org.netlib.util.doubleW dx1, double dy1, double[] param, int offsetparam) {
    org.netlib.blas.Drotmg.drotmg(dd1, dd2, dx1, dy1, param, offsetparam);
  }

  protected void srotmgK(org.netlib.util.floatW sd1, org.netlib.util.floatW sd2, org.netlib.util.floatW sx1, float sy1, float[] param, int offsetparam) {
    org.netlib.blas.Srotmg.srotmg(sd1, sd2, sx1, sy1, param, offsetparam);
  }

  protected void dsbmvK(String uplo, int n, int k, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    org.netlib.blas.Dsbmv.dsbmv(uplo, n, k, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected void ssbmvK(String uplo, int n, int k, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    org.netlib.blas.Ssbmv.ssbmv(uplo, n, k, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
  }

  protected void dscalK(int n, double alpha, double[] x, int offsetx, int incx) {
    if (incx == 1) {
      for (int ix = 0; ix < n; ix += 1) {
        x[offsetx + ix] *= alpha;
      }
    } else {
      for (int ix = incx < 0 ? (n - 1) * -incx : 0; incx < 0 ? ix >= 0 : ix < n * incx; ix += incx) {
        x[offsetx + ix] *= alpha;
      }
    }
  }

  protected void sscalK(int n, float alpha, float[] x, int offsetx, int incx) {
    if (incx == 1) {
      for (int ix = 0; ix < n; ix += 1) {
        x[offsetx + ix] *= alpha;
      }
    } else {
      for (int ix = incx < 0 ? (n - 1) * -incx : 0; incx < 0 ? ix >= 0 : ix < n * incx; ix += incx) {
        x[offsetx + ix] *= alpha;
      }
    }
  }

  protected void dspmvK(String uplo, int n, double alpha, double[] a, int offseta, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    if (alpha == 0.0) {
      for (int i = 0, iy = incy < 0 ? (n - 1) * -incy : 0; i < n; i += 1, iy += incy) {
        if (beta != 0.0) {
          y[offsety + iy] = beta * y[offsety + iy];
        } else {
          y[offsety + iy] = 0.0;
        }
      }
    } else if (lsame("U", uplo)) {
      dspmvU(n, alpha, a, offseta, x, offsetx, incx, beta, y, offsety, incy);
    } else if (lsame("L", uplo)) {
      dspmvL(n, alpha, a, offseta, x, offsetx, incx, beta, y, offsety, incy);
    }
  }

  protected void dspmvU(int n, double alpha, double[] a, int offseta, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
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
      if (beta != 0.0) {
        y[offsety + iy + incy * 0] = alpha * sumiy0 + beta * y[offsety + iy + incy * 0];
        y[offsety + iy + incy * 1] = alpha * sumiy1 + beta * y[offsety + iy + incy * 1];
        y[offsety + iy + incy * 2] = alpha * sumiy2 + beta * y[offsety + iy + incy * 2];
        y[offsety + iy + incy * 3] = alpha * sumiy3 + beta * y[offsety + iy + incy * 3];
      } else {
        y[offsety + iy + incy * 0] = alpha * sumiy0;
        y[offsety + iy + incy * 1] = alpha * sumiy1;
        y[offsety + iy + incy * 2] = alpha * sumiy2;
        y[offsety + iy + incy * 3] = alpha * sumiy3;
      }
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
      if (beta != 0.0) {
        y[offsety + iy] = alpha * sumiy + beta * y[offsety + iy];
      } else {
        y[offsety + iy] = alpha * sumiy;
      }
    }
  }

  protected void dspmvL(int n, double alpha, double[] a, int offseta, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    // y = beta * y
    if (beta != 1.0) {
      for (int i = 0, iy = incy < 0 ? (n - 1) * -incy : 0; i < n; i += 1, iy += incy) {
        if (beta != 0.0) {
          y[offsety + iy] = beta * y[offsety + iy];
        } else {
          y[offsety + iy] = 0.0;
        }
      }
    }
    // y += alpha * A * x
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

  protected void sspmvK(String uplo, int n, float alpha, float[] a, int offseta, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    if (alpha == 0.0f) {
      for (int i = 0, iy = incy < 0 ? (n - 1) * -incy : 0; i < n; i += 1, iy += incy) {
        if (beta != 0.0f) {
          y[offsety + iy] = beta * y[offsety + iy];
        } else {
          y[offsety + iy] = 0.0f;
        }
      }
    } else if (lsame("U", uplo)) {
      sspmvU(n, alpha, a, offseta, x, offsetx, incx, beta, y, offsety, incy);
    } else if (lsame("L", uplo)) {
      sspmvL(n, alpha, a, offseta, x, offsetx, incx, beta, y, offsety, incy);
    }
  }

  protected void sspmvU(int n, float alpha, float[] a, int offseta, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
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
      if (beta != 0.0f) {
        y[offsety + iy + incy * 0] = alpha * sumiy0 + beta * y[offsety + iy + incy * 0];
        y[offsety + iy + incy * 1] = alpha * sumiy1 + beta * y[offsety + iy + incy * 1];
        y[offsety + iy + incy * 2] = alpha * sumiy2 + beta * y[offsety + iy + incy * 2];
        y[offsety + iy + incy * 3] = alpha * sumiy3 + beta * y[offsety + iy + incy * 3];
      } else {
        y[offsety + iy + incy * 0] = alpha * sumiy0;
        y[offsety + iy + incy * 1] = alpha * sumiy1;
        y[offsety + iy + incy * 2] = alpha * sumiy2;
        y[offsety + iy + incy * 3] = alpha * sumiy3;
      }
    }
    for (; col < n; col += 1, ix += incx, iy += incy) {
      float alphaxix = alpha * x[offsetx + ix];
      float sumiy = 0.0f;
      int row = 0, jx = incx < 0 ? (col - 1) * -incx : 0, jy = incy < 0 ? (col - 1) * -incy : 0;
      for (; row < col; row += 1, jx += incx, jy += incy) {
        y[offsety + jy] += alphaxix * a[offseta + row + col * (col + 1) / 2];
        sumiy += x[offsetx + jx] * a[offseta + row + col * (col + 1) / 2];
      }
      sumiy += x[offsetx + jx] * a[offseta + row + col * (col + 1) / 2];
      if (beta != 0.0f) {
        y[offsety + iy] = alpha * sumiy + beta * y[offsety + iy];
      } else {
        y[offsety + iy] = alpha * sumiy;
      }
    }
  }

  protected void sspmvL(int n, float alpha, float[] a, int offseta, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    // y = beta * y
    if (beta != 1.0f) {
      for (int i = 0, iy = incy < 0 ? (n - 1) * -incy : 0; i < n; i += 1, iy += incy) {
        if (beta != 0.0f) {
          y[offsety + iy] = beta * y[offsety + iy];
        } else {
          y[offsety + iy] = 0.0f;
        }
      }
    }
    // y += alpha * A * x
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

  protected void dsprK(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta) {
    org.netlib.blas.Dspr.dspr(uplo, n, alpha, x, offsetx, incx, a, offseta);
  }

  protected void ssprK(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta) {
    org.netlib.blas.Sspr.sspr(uplo, n, alpha, x, offsetx, incx, a, offseta);
  }

  protected void dspr2K(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta) {
    org.netlib.blas.Dspr2.dspr2(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta);
  }

  protected void sspr2K(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta) {
    org.netlib.blas.Sspr2.sspr2(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta);
  }

  protected void dswapK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
      for (int ix = 0, iy = 0; ix < n && iy < n; ix += 1, iy += 1) {
        double tmp = y[offsety + iy];
        y[offsety + iy] = x[offsetx + ix];
        x[offsetx + ix] = tmp;
      }
    } else {
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
  }

  protected void sswapK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
      for (int ix = 0, iy = 0; ix < n && iy < n; ix += 1, iy += 1) {
        float tmp = y[offsety + iy];
        y[offsety + iy] = x[offsetx + ix];
        x[offsetx + ix] = tmp;
      }
    } else {
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
  }

  protected void dsymmK(String side, String uplo, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    if (alpha == 0.0) {
      // C := beta*C
      int col = 0;
      for (; col < loopBound(n, 4); col += 4) {
        int row = 0;
        for (; row < m; row += 1) {
          if (beta != 0.0) {
            c[offsetc + row + (col + 0) * ldc] = beta * c[offsetc + row + (col + 0) * ldc];
            c[offsetc + row + (col + 1) * ldc] = beta * c[offsetc + row + (col + 1) * ldc];
            c[offsetc + row + (col + 2) * ldc] = beta * c[offsetc + row + (col + 2) * ldc];
            c[offsetc + row + (col + 3) * ldc] = beta * c[offsetc + row + (col + 3) * ldc];
          } else {
            c[offsetc + row + (col + 0) * ldc] = 0.0;
            c[offsetc + row + (col + 1) * ldc] = 0.0;
            c[offsetc + row + (col + 2) * ldc] = 0.0;
            c[offsetc + row + (col + 3) * ldc] = 0.0;
          }
        }
      }
      for (; col < n; col += 1) {
        int row = 0;
        for (; row < m; row += 1) {
          if (beta != 0.0) {
            c[offsetc + row + col * ldc] = beta * c[offsetc + row + col * ldc];
          } else {
            c[offsetc + row + col * ldc] = 0.0;
          }
        }
      }
    } else if (lsame("L", side) && lsame("U", uplo)) {
      dsymmLU(m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
    } else if (lsame("L", side) && lsame("L", uplo)) {
      dsymmLL(m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
    } else if (lsame("R", side) && lsame("U", uplo)) {
      dsymmRU(m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
    } else if (lsame("R", side) && lsame("L", uplo)) {
      dsymmRL(m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
    }
  }

  protected void dsymmLU(int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    // C := alpha*A*B + beta*C
    int col = 0;
    for (; col < loopBound(n, 4); col += 4) {
      int row = 0;
      for (; row < loopBound(m, 4); row += 4) {
        double sum00 = 0.0;
        double sum10 = 0.0;
        double sum20 = 0.0;
        double sum30 = 0.0;
        double sum01 = 0.0;
        double sum11 = 0.0;
        double sum21 = 0.0;
        double sum31 = 0.0;
        double sum02 = 0.0;
        double sum12 = 0.0;
        double sum22 = 0.0;
        double sum32 = 0.0;
        double sum03 = 0.0;
        double sum13 = 0.0;
        double sum23 = 0.0;
        double sum33 = 0.0;
        double alphab00 = alpha * b[offsetb + (row + 0) + (col + 0) * ldb];
        double alphab10 = alpha * b[offsetb + (row + 1) + (col + 0) * ldb];
        double alphab20 = alpha * b[offsetb + (row + 2) + (col + 0) * ldb];
        double alphab30 = alpha * b[offsetb + (row + 3) + (col + 0) * ldb];
        double alphab01 = alpha * b[offsetb + (row + 0) + (col + 1) * ldb];
        double alphab11 = alpha * b[offsetb + (row + 1) + (col + 1) * ldb];
        double alphab21 = alpha * b[offsetb + (row + 2) + (col + 1) * ldb];
        double alphab31 = alpha * b[offsetb + (row + 3) + (col + 1) * ldb];
        double alphab02 = alpha * b[offsetb + (row + 0) + (col + 2) * ldb];
        double alphab12 = alpha * b[offsetb + (row + 1) + (col + 2) * ldb];
        double alphab22 = alpha * b[offsetb + (row + 2) + (col + 2) * ldb];
        double alphab32 = alpha * b[offsetb + (row + 3) + (col + 2) * ldb];
        double alphab03 = alpha * b[offsetb + (row + 0) + (col + 3) * ldb];
        double alphab13 = alpha * b[offsetb + (row + 1) + (col + 3) * ldb];
        double alphab23 = alpha * b[offsetb + (row + 2) + (col + 3) * ldb];
        double alphab33 = alpha * b[offsetb + (row + 3) + (col + 3) * ldb];
        int i = 0;
        for (; i < row; i += 1) {
          double a0 = a[offseta + i + (row + 0) * lda];
          double a1 = a[offseta + i + (row + 1) * lda];
          double a2 = a[offseta + i + (row + 2) * lda];
          double a3 = a[offseta + i + (row + 3) * lda];
          c[offsetc + i + (col + 0) * ldc] += alphab00 * a0
                                           +  alphab10 * a1
                                           +  alphab20 * a2
                                           +  alphab30 * a3;
          c[offsetc + i + (col + 1) * ldc] += alphab01 * a0
                                           +  alphab11 * a1
                                           +  alphab21 * a2
                                           +  alphab31 * a3;
          c[offsetc + i + (col + 2) * ldc] += alphab02 * a0
                                           +  alphab12 * a1
                                           +  alphab22 * a2
                                           +  alphab32 * a3;
          c[offsetc + i + (col + 3) * ldc] += alphab03 * a0
                                           +  alphab13 * a1
                                           +  alphab23 * a2
                                           +  alphab33 * a3;
          double b0 = b[offsetb + i + (col + 0) * ldb];
          double b1 = b[offsetb + i + (col + 1) * ldb];
          double b2 = b[offsetb + i + (col + 2) * ldb];
          double b3 = b[offsetb + i + (col + 3) * ldb];
          sum00 += a0 * b0;
          sum10 += a1 * b0;
          sum20 += a2 * b0;
          sum30 += a3 * b0;
          sum01 += a0 * b1;
          sum11 += a1 * b1;
          sum21 += a2 * b1;
          sum31 += a3 * b1;
          sum02 += a0 * b2;
          sum12 += a1 * b2;
          sum22 += a2 * b2;
          sum32 += a3 * b2;
          sum03 += a0 * b3;
          sum13 += a1 * b3;
          sum23 += a2 * b3;
          sum33 += a3 * b3;
        }
        double a00 = a[offseta + (i + 0) + (row + 0) * lda];
        double a01 = a[offseta + (i + 0) + (row + 1) * lda];
        double a02 = a[offseta + (i + 0) + (row + 2) * lda];
        double a03 = a[offseta + (i + 0) + (row + 3) * lda];
        double a11 = a[offseta + (i + 1) + (row + 1) * lda];
        double a12 = a[offseta + (i + 1) + (row + 2) * lda];
        double a13 = a[offseta + (i + 1) + (row + 3) * lda];
        double a22 = a[offseta + (i + 2) + (row + 2) * lda];
        double a23 = a[offseta + (i + 2) + (row + 3) * lda];
        double a33 = a[offseta + (i + 3) + (row + 3) * lda];
        double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
        double b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
        double b20 = b[offsetb + (i + 2) + (col + 0) * ldb];
        double b30 = b[offsetb + (i + 3) + (col + 0) * ldb];
        double b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
        double b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
        double b21 = b[offsetb + (i + 2) + (col + 1) * ldb];
        double b31 = b[offsetb + (i + 3) + (col + 1) * ldb];
        double b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
        double b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
        double b22 = b[offsetb + (i + 2) + (col + 2) * ldb];
        double b32 = b[offsetb + (i + 3) + (col + 2) * ldb];
        double b03 = b[offsetb + (i + 0) + (col + 3) * ldb];
        double b13 = b[offsetb + (i + 1) + (col + 3) * ldb];
        double b23 = b[offsetb + (i + 2) + (col + 3) * ldb];
        double b33 = b[offsetb + (i + 3) + (col + 3) * ldb];
        sum00 += a00 * b00 + a01 * b10 + a02 * b20 + a03 * b30;
        sum10 += a01 * b00 + a11 * b10 + a12 * b20 + a13 * b30;
        sum20 += a02 * b00 + a12 * b10 + a22 * b20 + a23 * b30;
        sum30 += a03 * b00 + a13 * b10 + a23 * b20 + a33 * b30;
        sum01 += a00 * b01 + a01 * b11 + a02 * b21 + a03 * b31;
        sum11 += a01 * b01 + a11 * b11 + a12 * b21 + a13 * b31;
        sum21 += a02 * b01 + a12 * b11 + a22 * b21 + a23 * b31;
        sum31 += a03 * b01 + a13 * b11 + a23 * b21 + a33 * b31;
        sum02 += a00 * b02 + a01 * b12 + a02 * b22 + a03 * b32;
        sum12 += a01 * b02 + a11 * b12 + a12 * b22 + a13 * b32;
        sum22 += a02 * b02 + a12 * b12 + a22 * b22 + a23 * b32;
        sum32 += a03 * b02 + a13 * b12 + a23 * b22 + a33 * b32;
        sum03 += a00 * b03 + a01 * b13 + a02 * b23 + a03 * b33;
        sum13 += a01 * b03 + a11 * b13 + a12 * b23 + a13 * b33;
        sum23 += a02 * b03 + a12 * b13 + a22 * b23 + a23 * b33;
        sum33 += a03 * b03 + a13 * b13 + a23 * b23 + a33 * b33;
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + beta * c[offsetc + (row + 1) + (col + 0) * ldc];
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + beta * c[offsetc + (row + 2) + (col + 0) * ldc];
          c[offsetc + (row + 3) + (col + 0) * ldc] = alpha * sum30 + beta * c[offsetc + (row + 3) + (col + 0) * ldc];
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + beta * c[offsetc + (row + 0) + (col + 1) * ldc];
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11 + beta * c[offsetc + (row + 1) + (col + 1) * ldc];
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21 + beta * c[offsetc + (row + 2) + (col + 1) * ldc];
          c[offsetc + (row + 3) + (col + 1) * ldc] = alpha * sum31 + beta * c[offsetc + (row + 3) + (col + 1) * ldc];
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + beta * c[offsetc + (row + 0) + (col + 2) * ldc];
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12 + beta * c[offsetc + (row + 1) + (col + 2) * ldc];
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22 + beta * c[offsetc + (row + 2) + (col + 2) * ldc];
          c[offsetc + (row + 3) + (col + 2) * ldc] = alpha * sum32 + beta * c[offsetc + (row + 3) + (col + 2) * ldc];
          c[offsetc + (row + 0) + (col + 3) * ldc] = alpha * sum03 + beta * c[offsetc + (row + 0) + (col + 3) * ldc];
          c[offsetc + (row + 1) + (col + 3) * ldc] = alpha * sum13 + beta * c[offsetc + (row + 1) + (col + 3) * ldc];
          c[offsetc + (row + 2) + (col + 3) * ldc] = alpha * sum23 + beta * c[offsetc + (row + 2) + (col + 3) * ldc];
          c[offsetc + (row + 3) + (col + 3) * ldc] = alpha * sum33 + beta * c[offsetc + (row + 3) + (col + 3) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10;
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20;
          c[offsetc + (row + 3) + (col + 0) * ldc] = alpha * sum30;
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01;
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11;
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21;
          c[offsetc + (row + 3) + (col + 1) * ldc] = alpha * sum31;
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02;
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12;
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22;
          c[offsetc + (row + 3) + (col + 2) * ldc] = alpha * sum32;
          c[offsetc + (row + 0) + (col + 3) * ldc] = alpha * sum03;
          c[offsetc + (row + 1) + (col + 3) * ldc] = alpha * sum13;
          c[offsetc + (row + 2) + (col + 3) * ldc] = alpha * sum23;
          c[offsetc + (row + 3) + (col + 3) * ldc] = alpha * sum33;
        }
      }
      for (; row < m; row += 1) {
        double sum0 = 0.0;
        double sum1 = 0.0;
        double sum2 = 0.0;
        double sum3 = 0.0;
        double alphab0 = alpha * b[offsetb + row + (col + 0) * ldb];
        double alphab1 = alpha * b[offsetb + row + (col + 1) * ldb];
        double alphab2 = alpha * b[offsetb + row + (col + 2) * ldb];
        double alphab3 = alpha * b[offsetb + row + (col + 3) * ldb];
        int i = 0;
        for (; i < row; i += 1) {
          double a0 = a[offseta + i + row * lda];
          c[offsetc + i + (col + 0) * ldc] += alphab0 * a0;
          c[offsetc + i + (col + 1) * ldc] += alphab1 * a0;
          c[offsetc + i + (col + 2) * ldc] += alphab2 * a0;
          c[offsetc + i + (col + 3) * ldc] += alphab3 * a0;
          sum0 += b[offsetb + i + (col + 0) * ldb] * a0;
          sum1 += b[offsetb + i + (col + 1) * ldb] * a0;
          sum2 += b[offsetb + i + (col + 2) * ldb] * a0;
          sum3 += b[offsetb + i + (col + 3) * ldb] * a0;
        }
        double a0 = a[offseta + i + row * lda];
        sum0 += b[offsetb + i + (col + 0) * ldb] * a0;
        sum1 += b[offsetb + i + (col + 1) * ldb] * a0;
        sum2 += b[offsetb + i + (col + 2) * ldb] * a0;
        sum3 += b[offsetb + i + (col + 3) * ldb] * a0;
        if (beta != 0.0) {
          c[offsetc + row + (col + 0) * ldc] = alpha * sum0 + beta * c[offsetc + row + (col + 0) * ldc];
          c[offsetc + row + (col + 1) * ldc] = alpha * sum1 + beta * c[offsetc + row + (col + 1) * ldc];
          c[offsetc + row + (col + 2) * ldc] = alpha * sum2 + beta * c[offsetc + row + (col + 2) * ldc];
          c[offsetc + row + (col + 3) * ldc] = alpha * sum3 + beta * c[offsetc + row + (col + 3) * ldc];
        } else {
          c[offsetc + row + (col + 0) * ldc] = alpha * sum0;
          c[offsetc + row + (col + 1) * ldc] = alpha * sum1;
          c[offsetc + row + (col + 2) * ldc] = alpha * sum2;
          c[offsetc + row + (col + 3) * ldc] = alpha * sum3;
        }
      }
    }
    for (; col < n; col += 1) {
      int row = 0;
      for (; row < loopBound(m, 4); row += 4) {
        double sum0 = 0.0;
        double sum1 = 0.0;
        double sum2 = 0.0;
        double sum3 = 0.0;
        double alphab0 = alpha * b[offsetb + (row + 0) + col * ldb];
        double alphab1 = alpha * b[offsetb + (row + 1) + col * ldb];
        double alphab2 = alpha * b[offsetb + (row + 2) + col * ldb];
        double alphab3 = alpha * b[offsetb + (row + 3) + col * ldb];
        int i = 0;
        for (; i < row; i += 1) {
          double a0 = a[offseta + i + (row + 0) * lda];
          double a1 = a[offseta + i + (row + 1) * lda];
          double a2 = a[offseta + i + (row + 2) * lda];
          double a3 = a[offseta + i + (row + 3) * lda];
          c[offsetc + i + col * ldc] += alphab0 * a0
                                     +  alphab1 * a1
                                     +  alphab2 * a2
                                     +  alphab3 * a3;
          double b0 = b[offsetb + i + col * ldb];
          sum0 += b0 * a0;
          sum1 += b0 * a1;
          sum2 += b0 * a2;
          sum3 += b0 * a3;
        }
        double a00 = a[offseta + (i + 0) + (row + 0) * lda];
        double a01 = a[offseta + (i + 0) + (row + 1) * lda];
        double a02 = a[offseta + (i + 0) + (row + 2) * lda];
        double a03 = a[offseta + (i + 0) + (row + 3) * lda];
        double a11 = a[offseta + (i + 1) + (row + 1) * lda];
        double a12 = a[offseta + (i + 1) + (row + 2) * lda];
        double a13 = a[offseta + (i + 1) + (row + 3) * lda];
        double a22 = a[offseta + (i + 2) + (row + 2) * lda];
        double a23 = a[offseta + (i + 2) + (row + 3) * lda];
        double a33 = a[offseta + (i + 3) + (row + 3) * lda];
        double b0 = b[offsetb + (i + 0) + col * ldb];
        double b1 = b[offsetb + (i + 1) + col * ldb];
        double b2 = b[offsetb + (i + 2) + col * ldb];
        double b3 = b[offsetb + (i + 3) + col * ldb];
        sum0 += b0 * a00 + b1 * a01 + b2 * a02 + b3 * a03;
        sum1 += b0 * a01 + b1 * a11 + b2 * a12 + b3 * a13;
        sum2 += b0 * a02 + b1 * a12 + b2 * a22 + b3 * a23;
        sum3 += b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33;
        if (beta != 0.0) {
          c[offsetc + (row + 0) + col * ldc] = alpha * sum0 + beta * c[offsetc + (row + 0) + col * ldc];
          c[offsetc + (row + 1) + col * ldc] = alpha * sum1 + beta * c[offsetc + (row + 1) + col * ldc];
          c[offsetc + (row + 2) + col * ldc] = alpha * sum2 + beta * c[offsetc + (row + 2) + col * ldc];
          c[offsetc + (row + 3) + col * ldc] = alpha * sum3 + beta * c[offsetc + (row + 3) + col * ldc];
        } else {
          c[offsetc + (row + 0) + col * ldc] = alpha * sum0;
          c[offsetc + (row + 1) + col * ldc] = alpha * sum1;
          c[offsetc + (row + 2) + col * ldc] = alpha * sum2;
          c[offsetc + (row + 3) + col * ldc] = alpha * sum3;
        }
      }
      for (; row < m; row += 1) {
        double alphab = alpha * b[offsetb + row + col * ldb];
        double sum = 0.0;
        int i = 0;
        for (; i < row; i += 1) {
          double aval = a[offseta + i + row * lda];
          c[offsetc + i + col * ldc] += alphab * aval;
          sum += b[offsetb + i + col * ldb] * aval;
        }
        sum += b[offsetb + i + col * ldb] * a[offseta + i + row * lda];
        if (beta != 0.0) {
          c[offsetc + row + col * ldc] = alpha * sum + beta * c[offsetc + row + col * ldc];
        } else {
          c[offsetc + row + col * ldc] = alpha * sum;
        }
      }
    }
  }

  protected void dsymmLL(int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    final int Srow = 4;
    // C := alpha*A*B + beta*C
    int col = 0;
    for (; col < loopBound(n, 4); col += 4) {
      int row = m - 1;
      for (; row >= loopBound(m - 1, Srow); row -= 1) {
        double alphab0 = alpha * b[offsetb + row + (col + 0) * ldb];
        double alphab1 = alpha * b[offsetb + row + (col + 1) * ldb];
        double alphab2 = alpha * b[offsetb + row + (col + 2) * ldb];
        double alphab3 = alpha * b[offsetb + row + (col + 3) * ldb];
        double sum0 = 0.0;
        double sum1 = 0.0;
        double sum2 = 0.0;
        double sum3 = 0.0;
        sum0 += b[offsetb + row + (col + 0) * ldb] * a[offseta + row + row * lda];
        sum1 += b[offsetb + row + (col + 1) * ldb] * a[offseta + row + row * lda];
        sum2 += b[offsetb + row + (col + 2) * ldb] * a[offseta + row + row * lda];
        sum3 += b[offsetb + row + (col + 3) * ldb] * a[offseta + row + row * lda];
        int i = row + 1;
        for (; i < m; i += 1) {
          double airow = a[offseta + i + row * lda];
          c[offsetc + i + (col + 0) * ldc] += alphab0 * airow;
          c[offsetc + i + (col + 1) * ldc] += alphab1 * airow;
          c[offsetc + i + (col + 2) * ldc] += alphab2 * airow;
          c[offsetc + i + (col + 3) * ldc] += alphab3 * airow;
          sum0 += b[offsetb + i + (col + 0) * ldb] * airow;
          sum1 += b[offsetb + i + (col + 1) * ldb] * airow;
          sum2 += b[offsetb + i + (col + 2) * ldb] * airow;
          sum3 += b[offsetb + i + (col + 3) * ldb] * airow;
        }
        if (beta != 0.0) {
          c[offsetc + row + (col + 0) * ldc] = alpha * sum0 + beta * c[offsetc + row + (col + 0) * ldc];
          c[offsetc + row + (col + 1) * ldc] = alpha * sum1 + beta * c[offsetc + row + (col + 1) * ldc];
          c[offsetc + row + (col + 2) * ldc] = alpha * sum2 + beta * c[offsetc + row + (col + 2) * ldc];
          c[offsetc + row + (col + 3) * ldc] = alpha * sum3 + beta * c[offsetc + row + (col + 3) * ldc];
        } else {
          c[offsetc + row + (col + 0) * ldc] = alpha * sum0;
          c[offsetc + row + (col + 1) * ldc] = alpha * sum1;
          c[offsetc + row + (col + 2) * ldc] = alpha * sum2;
          c[offsetc + row + (col + 3) * ldc] = alpha * sum3;
        }
      }
      for (row -= Srow - 1; row >= 0; row -= Srow) {
        double a00 = a[offseta + (row + 0) + (row + 0) * lda];
        double a10 = a[offseta + (row + 1) + (row + 0) * lda];
        double a11 = a[offseta + (row + 1) + (row + 1) * lda];
        double a20 = a[offseta + (row + 2) + (row + 0) * lda];
        double a21 = a[offseta + (row + 2) + (row + 1) * lda];
        double a22 = a[offseta + (row + 2) + (row + 2) * lda];
        double a30 = a[offseta + (row + 3) + (row + 0) * lda];
        double a31 = a[offseta + (row + 3) + (row + 1) * lda];
        double a32 = a[offseta + (row + 3) + (row + 2) * lda];
        double a33 = a[offseta + (row + 3) + (row + 3) * lda];
        double b00 = b[offsetb + (row + 0) + (col + 0) * ldb];
        double b10 = b[offsetb + (row + 1) + (col + 0) * ldb];
        double b20 = b[offsetb + (row + 2) + (col + 0) * ldb];
        double b30 = b[offsetb + (row + 3) + (col + 0) * ldb];
        double b01 = b[offsetb + (row + 0) + (col + 1) * ldb];
        double b11 = b[offsetb + (row + 1) + (col + 1) * ldb];
        double b21 = b[offsetb + (row + 2) + (col + 1) * ldb];
        double b31 = b[offsetb + (row + 3) + (col + 1) * ldb];
        double b02 = b[offsetb + (row + 0) + (col + 2) * ldb];
        double b12 = b[offsetb + (row + 1) + (col + 2) * ldb];
        double b22 = b[offsetb + (row + 2) + (col + 2) * ldb];
        double b32 = b[offsetb + (row + 3) + (col + 2) * ldb];
        double b03 = b[offsetb + (row + 0) + (col + 3) * ldb];
        double b13 = b[offsetb + (row + 1) + (col + 3) * ldb];
        double b23 = b[offsetb + (row + 2) + (col + 3) * ldb];
        double b33 = b[offsetb + (row + 3) + (col + 3) * ldb];
        double alphab00 = alpha * b00;
        double alphab10 = alpha * b10;
        double alphab20 = alpha * b20;
        double alphab30 = alpha * b30;
        double alphab01 = alpha * b01;
        double alphab11 = alpha * b11;
        double alphab21 = alpha * b21;
        double alphab31 = alpha * b31;
        double alphab02 = alpha * b02;
        double alphab12 = alpha * b12;
        double alphab22 = alpha * b22;
        double alphab32 = alpha * b32;
        double alphab03 = alpha * b03;
        double alphab13 = alpha * b13;
        double alphab23 = alpha * b23;
        double alphab33 = alpha * b33;
        double sum00 = 0.0;
        double sum10 = 0.0;
        double sum20 = 0.0;
        double sum30 = 0.0;
        double sum01 = 0.0;
        double sum11 = 0.0;
        double sum21 = 0.0;
        double sum31 = 0.0;
        double sum02 = 0.0;
        double sum12 = 0.0;
        double sum22 = 0.0;
        double sum32 = 0.0;
        double sum03 = 0.0;
        double sum13 = 0.0;
        double sum23 = 0.0;
        double sum33 = 0.0;
        sum00 += b00 * a00 + b10 * a10 + b20 * a20 + b30 * a30;
        sum10 += b00 * a10 + b10 * a11 + b20 * a21 + b30 * a31;
        sum20 += b00 * a20 + b10 * a21 + b20 * a22 + b30 * a32;
        sum30 += b00 * a30 + b10 * a31 + b20 * a32 + b30 * a33;
        sum01 += b01 * a00 + b11 * a10 + b21 * a20 + b31 * a30;
        sum11 += b01 * a10 + b11 * a11 + b21 * a21 + b31 * a31;
        sum21 += b01 * a20 + b11 * a21 + b21 * a22 + b31 * a32;
        sum31 += b01 * a30 + b11 * a31 + b21 * a32 + b31 * a33;
        sum02 += b02 * a00 + b12 * a10 + b22 * a20 + b32 * a30;
        sum12 += b02 * a10 + b12 * a11 + b22 * a21 + b32 * a31;
        sum22 += b02 * a20 + b12 * a21 + b22 * a22 + b32 * a32;
        sum32 += b02 * a30 + b12 * a31 + b22 * a32 + b32 * a33;
        sum03 += b03 * a00 + b13 * a10 + b23 * a20 + b33 * a30;
        sum13 += b03 * a10 + b13 * a11 + b23 * a21 + b33 * a31;
        sum23 += b03 * a20 + b13 * a21 + b23 * a22 + b33 * a32;
        sum33 += b03 * a30 + b13 * a31 + b23 * a32 + b33 * a33;
        int i = row + 4;
        for (; i < m; i += 1) {
          double a0 = a[offseta + i + (row + 0) * lda];
          double a1 = a[offseta + i + (row + 1) * lda];
          double a2 = a[offseta + i + (row + 2) * lda];
          double a3 = a[offseta + i + (row + 3) * lda];
          c[offsetc + i + (col + 0) * ldc] += alphab00 * a0
                                           +  alphab10 * a1
                                           +  alphab20 * a2
                                           +  alphab30 * a3;
          c[offsetc + i + (col + 1) * ldc] += alphab01 * a0
                                           +  alphab11 * a1
                                           +  alphab21 * a2
                                           +  alphab31 * a3;
          c[offsetc + i + (col + 2) * ldc] += alphab02 * a0
                                           +  alphab12 * a1
                                           +  alphab22 * a2
                                           +  alphab32 * a3;
          c[offsetc + i + (col + 3) * ldc] += alphab03 * a0
                                           +  alphab13 * a1
                                           +  alphab23 * a2
                                           +  alphab33 * a3;
          double b0 = b[offsetb + i + (col + 0) * ldb];
          double b1 = b[offsetb + i + (col + 1) * ldb];
          double b2 = b[offsetb + i + (col + 2) * ldb];
          double b3 = b[offsetb + i + (col + 3) * ldb];
          sum00 += b0 * a0;
          sum10 += b0 * a1;
          sum20 += b0 * a2;
          sum30 += b0 * a3;
          sum01 += b1 * a0;
          sum11 += b1 * a1;
          sum21 += b1 * a2;
          sum31 += b1 * a3;
          sum02 += b2 * a0;
          sum12 += b2 * a1;
          sum22 += b2 * a2;
          sum32 += b2 * a3;
          sum03 += b3 * a0;
          sum13 += b3 * a1;
          sum23 += b3 * a2;
          sum33 += b3 * a3;
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + beta * c[offsetc + (row + 1) + (col + 0) * ldc];
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + beta * c[offsetc + (row + 2) + (col + 0) * ldc];
          c[offsetc + (row + 3) + (col + 0) * ldc] = alpha * sum30 + beta * c[offsetc + (row + 3) + (col + 0) * ldc];
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + beta * c[offsetc + (row + 0) + (col + 1) * ldc];
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11 + beta * c[offsetc + (row + 1) + (col + 1) * ldc];
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21 + beta * c[offsetc + (row + 2) + (col + 1) * ldc];
          c[offsetc + (row + 3) + (col + 1) * ldc] = alpha * sum31 + beta * c[offsetc + (row + 3) + (col + 1) * ldc];
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + beta * c[offsetc + (row + 0) + (col + 2) * ldc];
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12 + beta * c[offsetc + (row + 1) + (col + 2) * ldc];
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22 + beta * c[offsetc + (row + 2) + (col + 2) * ldc];
          c[offsetc + (row + 3) + (col + 2) * ldc] = alpha * sum32 + beta * c[offsetc + (row + 3) + (col + 2) * ldc];
          c[offsetc + (row + 0) + (col + 3) * ldc] = alpha * sum03 + beta * c[offsetc + (row + 0) + (col + 3) * ldc];
          c[offsetc + (row + 1) + (col + 3) * ldc] = alpha * sum13 + beta * c[offsetc + (row + 1) + (col + 3) * ldc];
          c[offsetc + (row + 2) + (col + 3) * ldc] = alpha * sum23 + beta * c[offsetc + (row + 2) + (col + 3) * ldc];
          c[offsetc + (row + 3) + (col + 3) * ldc] = alpha * sum33 + beta * c[offsetc + (row + 3) + (col + 3) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10;
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20;
          c[offsetc + (row + 3) + (col + 0) * ldc] = alpha * sum30;
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01;
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11;
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21;
          c[offsetc + (row + 3) + (col + 1) * ldc] = alpha * sum31;
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02;
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12;
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22;
          c[offsetc + (row + 3) + (col + 2) * ldc] = alpha * sum32;
          c[offsetc + (row + 0) + (col + 3) * ldc] = alpha * sum03;
          c[offsetc + (row + 1) + (col + 3) * ldc] = alpha * sum13;
          c[offsetc + (row + 2) + (col + 3) * ldc] = alpha * sum23;
          c[offsetc + (row + 3) + (col + 3) * ldc] = alpha * sum33;
        }
      }
    }
    for (; col < n; col += 1) {
      int row = m - 1;
      for (; row >= loopBound(m - 1, Srow); row -= 1) {
        double alphab0 = alpha * b[offsetb + row + col * ldb];
        double sum0 = 0.0;
        sum0 += b[offsetb + row + col * ldb] * a[offseta + row + row * lda];
        int i = row + 1;
        for (; i < m; i += 1) {
          double a0 = a[offseta + i + row * lda];
          c[offsetc + i + col * ldc] += alphab0 * a0;
          sum0 += b[offsetb + i + col * ldb] * a0;
        }
        if (beta != 0.0) {
          c[offsetc + row + col * ldc] = alpha * sum0 + beta * c[offsetc + row + col * ldc];
        } else {
          c[offsetc + row + col * ldc] = alpha * sum0;
        }
      }
      for (row -= Srow - 1; row >= 0; row -= Srow) {
        double alphab0 = alpha * b[offsetb + (row + 0) + col * ldb];
        double alphab1 = alpha * b[offsetb + (row + 1) + col * ldb];
        double alphab2 = alpha * b[offsetb + (row + 2) + col * ldb];
        double alphab3 = alpha * b[offsetb + (row + 3) + col * ldb];
        double a00 = a[offseta + (row + 0) + (row + 0) * lda];
        double a10 = a[offseta + (row + 1) + (row + 0) * lda];
        double a11 = a[offseta + (row + 1) + (row + 1) * lda];
        double a20 = a[offseta + (row + 2) + (row + 0) * lda];
        double a21 = a[offseta + (row + 2) + (row + 1) * lda];
        double a22 = a[offseta + (row + 2) + (row + 2) * lda];
        double a30 = a[offseta + (row + 3) + (row + 0) * lda];
        double a31 = a[offseta + (row + 3) + (row + 1) * lda];
        double a32 = a[offseta + (row + 3) + (row + 2) * lda];
        double a33 = a[offseta + (row + 3) + (row + 3) * lda];
        double b0 = b[offsetb + (row + 0) + col * ldb];
        double b1 = b[offsetb + (row + 1) + col * ldb];
        double b2 = b[offsetb + (row + 2) + col * ldb];
        double b3 = b[offsetb + (row + 3) + col * ldb];
        double sum0 = 0.0;
        double sum1 = 0.0;
        double sum2 = 0.0;
        double sum3 = 0.0;
        sum0 += b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30;
        sum1 += b0 * a10 + b1 * a11 + b2 * a21 + b3 * a31;
        sum2 += b0 * a20 + b1 * a21 + b2 * a22 + b3 * a32;
        sum3 += b0 * a30 + b1 * a31 + b2 * a32 + b3 * a33;
        int i = row + 4;
        for (; i < m; i += 1) {
          double a0 = a[offseta + i + (row + 0) * lda];
          double a1 = a[offseta + i + (row + 1) * lda];
          double a2 = a[offseta + i + (row + 2) * lda];
          double a3 = a[offseta + i + (row + 3) * lda];
          c[offsetc + i + col * ldc] += alphab0 * a0
                                     +  alphab1 * a1
                                     +  alphab2 * a2
                                     +  alphab3 * a3;
          double bicol = b[offsetb + i + col * ldb];
          sum0 += bicol * a0;
          sum1 += bicol * a1;
          sum2 += bicol * a2;
          sum3 += bicol * a3;
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + col * ldc] = alpha * sum0 + beta * c[offsetc + (row + 0) + col * ldc];
          c[offsetc + (row + 1) + col * ldc] = alpha * sum1 + beta * c[offsetc + (row + 1) + col * ldc];
          c[offsetc + (row + 2) + col * ldc] = alpha * sum2 + beta * c[offsetc + (row + 2) + col * ldc];
          c[offsetc + (row + 3) + col * ldc] = alpha * sum3 + beta * c[offsetc + (row + 3) + col * ldc];
        } else {
          c[offsetc + (row + 0) + col * ldc] = alpha * sum0;
          c[offsetc + (row + 1) + col * ldc] = alpha * sum1;
          c[offsetc + (row + 2) + col * ldc] = alpha * sum2;
          c[offsetc + (row + 3) + col * ldc] = alpha * sum3;
        }
      }
    }
  }

  protected void dsymmRU(int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    // C := alpha*B*A + beta*C
    org.netlib.blas.Dsymm.dsymm("R", "U", m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected void dsymmRL(int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    // C := alpha*B*A + beta*C
    org.netlib.blas.Dsymm.dsymm("R", "L", m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected void ssymmK(String side, String uplo, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    if (alpha == 0.0f) {
      // C := beta*C
      int col = 0;
      for (; col < loopBound(n, 4); col += 4) {
        int row = 0;
        for (; row < m; row += 1) {
          c[offsetc + row + (col + 0) * ldc] = beta * c[offsetc + row + (col + 0) * ldc];
          c[offsetc + row + (col + 1) * ldc] = beta * c[offsetc + row + (col + 1) * ldc];
          c[offsetc + row + (col + 2) * ldc] = beta * c[offsetc + row + (col + 2) * ldc];
          c[offsetc + row + (col + 3) * ldc] = beta * c[offsetc + row + (col + 3) * ldc];
        }
      }
      for (; col < n; col += 1) {
        int row = 0;
        for (; row < m; row += 1) {
          c[offsetc + row + col * ldc] = beta * c[offsetc + row + col * ldc];
        }
      }
    } else if (lsame("L", side) && lsame("U", uplo)) {
      ssymmLU(m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
    } else if (lsame("L", side) && lsame("L", uplo)) {
      ssymmLL(m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
    } else if (lsame("R", side) && lsame("U", uplo)) {
      ssymmRU(m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
    } else if (lsame("R", side) && lsame("L", uplo)) {
      ssymmRL(m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
    }
  }

  protected void ssymmLU(int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    // C := alpha*A*B + beta*C
    int col = 0;
    for (; col < loopBound(n, 4); col += 4) {
      int row = 0;
      for (; row < loopBound(m, 4); row += 4) {
        float sum00 = 0.0f;
        float sum10 = 0.0f;
        float sum20 = 0.0f;
        float sum30 = 0.0f;
        float sum01 = 0.0f;
        float sum11 = 0.0f;
        float sum21 = 0.0f;
        float sum31 = 0.0f;
        float sum02 = 0.0f;
        float sum12 = 0.0f;
        float sum22 = 0.0f;
        float sum32 = 0.0f;
        float sum03 = 0.0f;
        float sum13 = 0.0f;
        float sum23 = 0.0f;
        float sum33 = 0.0f;
        float alphab00 = alpha * b[offsetb + (row + 0) + (col + 0) * ldb];
        float alphab10 = alpha * b[offsetb + (row + 1) + (col + 0) * ldb];
        float alphab20 = alpha * b[offsetb + (row + 2) + (col + 0) * ldb];
        float alphab30 = alpha * b[offsetb + (row + 3) + (col + 0) * ldb];
        float alphab01 = alpha * b[offsetb + (row + 0) + (col + 1) * ldb];
        float alphab11 = alpha * b[offsetb + (row + 1) + (col + 1) * ldb];
        float alphab21 = alpha * b[offsetb + (row + 2) + (col + 1) * ldb];
        float alphab31 = alpha * b[offsetb + (row + 3) + (col + 1) * ldb];
        float alphab02 = alpha * b[offsetb + (row + 0) + (col + 2) * ldb];
        float alphab12 = alpha * b[offsetb + (row + 1) + (col + 2) * ldb];
        float alphab22 = alpha * b[offsetb + (row + 2) + (col + 2) * ldb];
        float alphab32 = alpha * b[offsetb + (row + 3) + (col + 2) * ldb];
        float alphab03 = alpha * b[offsetb + (row + 0) + (col + 3) * ldb];
        float alphab13 = alpha * b[offsetb + (row + 1) + (col + 3) * ldb];
        float alphab23 = alpha * b[offsetb + (row + 2) + (col + 3) * ldb];
        float alphab33 = alpha * b[offsetb + (row + 3) + (col + 3) * ldb];
        int i = 0;
        for (; i < row; i += 1) {
          float a0 = a[offseta + i + (row + 0) * lda];
          float a1 = a[offseta + i + (row + 1) * lda];
          float a2 = a[offseta + i + (row + 2) * lda];
          float a3 = a[offseta + i + (row + 3) * lda];
          c[offsetc + i + (col + 0) * ldc] += alphab00 * a0
                                           +  alphab10 * a1
                                           +  alphab20 * a2
                                           +  alphab30 * a3;
          c[offsetc + i + (col + 1) * ldc] += alphab01 * a0
                                           +  alphab11 * a1
                                           +  alphab21 * a2
                                           +  alphab31 * a3;
          c[offsetc + i + (col + 2) * ldc] += alphab02 * a0
                                           +  alphab12 * a1
                                           +  alphab22 * a2
                                           +  alphab32 * a3;
          c[offsetc + i + (col + 3) * ldc] += alphab03 * a0
                                           +  alphab13 * a1
                                           +  alphab23 * a2
                                           +  alphab33 * a3;
          float b0 = b[offsetb + i + (col + 0) * ldb];
          float b1 = b[offsetb + i + (col + 1) * ldb];
          float b2 = b[offsetb + i + (col + 2) * ldb];
          float b3 = b[offsetb + i + (col + 3) * ldb];
          sum00 += a0 * b0;
          sum10 += a1 * b0;
          sum20 += a2 * b0;
          sum30 += a3 * b0;
          sum01 += a0 * b1;
          sum11 += a1 * b1;
          sum21 += a2 * b1;
          sum31 += a3 * b1;
          sum02 += a0 * b2;
          sum12 += a1 * b2;
          sum22 += a2 * b2;
          sum32 += a3 * b2;
          sum03 += a0 * b3;
          sum13 += a1 * b3;
          sum23 += a2 * b3;
          sum33 += a3 * b3;
        }
        float a00 = a[offseta + (i + 0) + (row + 0) * lda];
        float a01 = a[offseta + (i + 0) + (row + 1) * lda];
        float a02 = a[offseta + (i + 0) + (row + 2) * lda];
        float a03 = a[offseta + (i + 0) + (row + 3) * lda];
        float a11 = a[offseta + (i + 1) + (row + 1) * lda];
        float a12 = a[offseta + (i + 1) + (row + 2) * lda];
        float a13 = a[offseta + (i + 1) + (row + 3) * lda];
        float a22 = a[offseta + (i + 2) + (row + 2) * lda];
        float a23 = a[offseta + (i + 2) + (row + 3) * lda];
        float a33 = a[offseta + (i + 3) + (row + 3) * lda];
        float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
        float b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
        float b20 = b[offsetb + (i + 2) + (col + 0) * ldb];
        float b30 = b[offsetb + (i + 3) + (col + 0) * ldb];
        float b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
        float b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
        float b21 = b[offsetb + (i + 2) + (col + 1) * ldb];
        float b31 = b[offsetb + (i + 3) + (col + 1) * ldb];
        float b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
        float b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
        float b22 = b[offsetb + (i + 2) + (col + 2) * ldb];
        float b32 = b[offsetb + (i + 3) + (col + 2) * ldb];
        float b03 = b[offsetb + (i + 0) + (col + 3) * ldb];
        float b13 = b[offsetb + (i + 1) + (col + 3) * ldb];
        float b23 = b[offsetb + (i + 2) + (col + 3) * ldb];
        float b33 = b[offsetb + (i + 3) + (col + 3) * ldb];
        sum00 += a00 * b00 + a01 * b10 + a02 * b20 + a03 * b30;
        sum10 += a01 * b00 + a11 * b10 + a12 * b20 + a13 * b30;
        sum20 += a02 * b00 + a12 * b10 + a22 * b20 + a23 * b30;
        sum30 += a03 * b00 + a13 * b10 + a23 * b20 + a33 * b30;
        sum01 += a00 * b01 + a01 * b11 + a02 * b21 + a03 * b31;
        sum11 += a01 * b01 + a11 * b11 + a12 * b21 + a13 * b31;
        sum21 += a02 * b01 + a12 * b11 + a22 * b21 + a23 * b31;
        sum31 += a03 * b01 + a13 * b11 + a23 * b21 + a33 * b31;
        sum02 += a00 * b02 + a01 * b12 + a02 * b22 + a03 * b32;
        sum12 += a01 * b02 + a11 * b12 + a12 * b22 + a13 * b32;
        sum22 += a02 * b02 + a12 * b12 + a22 * b22 + a23 * b32;
        sum32 += a03 * b02 + a13 * b12 + a23 * b22 + a33 * b32;
        sum03 += a00 * b03 + a01 * b13 + a02 * b23 + a03 * b33;
        sum13 += a01 * b03 + a11 * b13 + a12 * b23 + a13 * b33;
        sum23 += a02 * b03 + a12 * b13 + a22 * b23 + a23 * b33;
        sum33 += a03 * b03 + a13 * b13 + a23 * b23 + a33 * b33;
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + beta * c[offsetc + (row + 1) + (col + 0) * ldc];
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + beta * c[offsetc + (row + 2) + (col + 0) * ldc];
          c[offsetc + (row + 3) + (col + 0) * ldc] = alpha * sum30 + beta * c[offsetc + (row + 3) + (col + 0) * ldc];
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + beta * c[offsetc + (row + 0) + (col + 1) * ldc];
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11 + beta * c[offsetc + (row + 1) + (col + 1) * ldc];
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21 + beta * c[offsetc + (row + 2) + (col + 1) * ldc];
          c[offsetc + (row + 3) + (col + 1) * ldc] = alpha * sum31 + beta * c[offsetc + (row + 3) + (col + 1) * ldc];
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + beta * c[offsetc + (row + 0) + (col + 2) * ldc];
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12 + beta * c[offsetc + (row + 1) + (col + 2) * ldc];
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22 + beta * c[offsetc + (row + 2) + (col + 2) * ldc];
          c[offsetc + (row + 3) + (col + 2) * ldc] = alpha * sum32 + beta * c[offsetc + (row + 3) + (col + 2) * ldc];
          c[offsetc + (row + 0) + (col + 3) * ldc] = alpha * sum03 + beta * c[offsetc + (row + 0) + (col + 3) * ldc];
          c[offsetc + (row + 1) + (col + 3) * ldc] = alpha * sum13 + beta * c[offsetc + (row + 1) + (col + 3) * ldc];
          c[offsetc + (row + 2) + (col + 3) * ldc] = alpha * sum23 + beta * c[offsetc + (row + 2) + (col + 3) * ldc];
          c[offsetc + (row + 3) + (col + 3) * ldc] = alpha * sum33 + beta * c[offsetc + (row + 3) + (col + 3) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10;
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20;
          c[offsetc + (row + 3) + (col + 0) * ldc] = alpha * sum30;
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01;
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11;
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21;
          c[offsetc + (row + 3) + (col + 1) * ldc] = alpha * sum31;
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02;
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12;
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22;
          c[offsetc + (row + 3) + (col + 2) * ldc] = alpha * sum32;
          c[offsetc + (row + 0) + (col + 3) * ldc] = alpha * sum03;
          c[offsetc + (row + 1) + (col + 3) * ldc] = alpha * sum13;
          c[offsetc + (row + 2) + (col + 3) * ldc] = alpha * sum23;
          c[offsetc + (row + 3) + (col + 3) * ldc] = alpha * sum33;
        }
      }
      for (; row < m; row += 1) {
        float sum0 = 0.0f;
        float sum1 = 0.0f;
        float sum2 = 0.0f;
        float sum3 = 0.0f;
        float alphab0 = alpha * b[offsetb + row + (col + 0) * ldb];
        float alphab1 = alpha * b[offsetb + row + (col + 1) * ldb];
        float alphab2 = alpha * b[offsetb + row + (col + 2) * ldb];
        float alphab3 = alpha * b[offsetb + row + (col + 3) * ldb];
        int i = 0;
        for (; i < row; i += 1) {
          float a0 = a[offseta + i + row * lda];
          c[offsetc + i + (col + 0) * ldc] += alphab0 * a0;
          c[offsetc + i + (col + 1) * ldc] += alphab1 * a0;
          c[offsetc + i + (col + 2) * ldc] += alphab2 * a0;
          c[offsetc + i + (col + 3) * ldc] += alphab3 * a0;
          sum0 += b[offsetb + i + (col + 0) * ldb] * a0;
          sum1 += b[offsetb + i + (col + 1) * ldb] * a0;
          sum2 += b[offsetb + i + (col + 2) * ldb] * a0;
          sum3 += b[offsetb + i + (col + 3) * ldb] * a0;
        }
        float a0 = a[offseta + i + row * lda];
        sum0 += b[offsetb + i + (col + 0) * ldb] * a0;
        sum1 += b[offsetb + i + (col + 1) * ldb] * a0;
        sum2 += b[offsetb + i + (col + 2) * ldb] * a0;
        sum3 += b[offsetb + i + (col + 3) * ldb] * a0;
        if (beta != 0.0f) {
          c[offsetc + row + (col + 0) * ldc] = alpha * sum0 + beta * c[offsetc + row + (col + 0) * ldc];
          c[offsetc + row + (col + 1) * ldc] = alpha * sum1 + beta * c[offsetc + row + (col + 1) * ldc];
          c[offsetc + row + (col + 2) * ldc] = alpha * sum2 + beta * c[offsetc + row + (col + 2) * ldc];
          c[offsetc + row + (col + 3) * ldc] = alpha * sum3 + beta * c[offsetc + row + (col + 3) * ldc];
        } else {
          c[offsetc + row + (col + 0) * ldc] = alpha * sum0;
          c[offsetc + row + (col + 1) * ldc] = alpha * sum1;
          c[offsetc + row + (col + 2) * ldc] = alpha * sum2;
          c[offsetc + row + (col + 3) * ldc] = alpha * sum3;
        }
      }
    }
    for (; col < n; col += 1) {
      int row = 0;
      for (; row < loopBound(m, 4); row += 4) {
        float sum0 = 0.0f;
        float sum1 = 0.0f;
        float sum2 = 0.0f;
        float sum3 = 0.0f;
        float alphab0 = alpha * b[offsetb + (row + 0) + col * ldb];
        float alphab1 = alpha * b[offsetb + (row + 1) + col * ldb];
        float alphab2 = alpha * b[offsetb + (row + 2) + col * ldb];
        float alphab3 = alpha * b[offsetb + (row + 3) + col * ldb];
        int i = 0;
        for (; i < row; i += 1) {
          float a0 = a[offseta + i + (row + 0) * lda];
          float a1 = a[offseta + i + (row + 1) * lda];
          float a2 = a[offseta + i + (row + 2) * lda];
          float a3 = a[offseta + i + (row + 3) * lda];
          c[offsetc + i + col * ldc] += alphab0 * a0
                                     +  alphab1 * a1
                                     +  alphab2 * a2
                                     +  alphab3 * a3;
          float b0 = b[offsetb + i + col * ldb];
          sum0 += b0 * a0;
          sum1 += b0 * a1;
          sum2 += b0 * a2;
          sum3 += b0 * a3;
        }
        float a00 = a[offseta + (i + 0) + (row + 0) * lda];
        float a01 = a[offseta + (i + 0) + (row + 1) * lda];
        float a02 = a[offseta + (i + 0) + (row + 2) * lda];
        float a03 = a[offseta + (i + 0) + (row + 3) * lda];
        float a11 = a[offseta + (i + 1) + (row + 1) * lda];
        float a12 = a[offseta + (i + 1) + (row + 2) * lda];
        float a13 = a[offseta + (i + 1) + (row + 3) * lda];
        float a22 = a[offseta + (i + 2) + (row + 2) * lda];
        float a23 = a[offseta + (i + 2) + (row + 3) * lda];
        float a33 = a[offseta + (i + 3) + (row + 3) * lda];
        float b0 = b[offsetb + (i + 0) + col * ldb];
        float b1 = b[offsetb + (i + 1) + col * ldb];
        float b2 = b[offsetb + (i + 2) + col * ldb];
        float b3 = b[offsetb + (i + 3) + col * ldb];
        sum0 += b0 * a00 + b1 * a01 + b2 * a02 + b3 * a03;
        sum1 += b0 * a01 + b1 * a11 + b2 * a12 + b3 * a13;
        sum2 += b0 * a02 + b1 * a12 + b2 * a22 + b3 * a23;
        sum3 += b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33;
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + col * ldc] = alpha * sum0 + beta * c[offsetc + (row + 0) + col * ldc];
          c[offsetc + (row + 1) + col * ldc] = alpha * sum1 + beta * c[offsetc + (row + 1) + col * ldc];
          c[offsetc + (row + 2) + col * ldc] = alpha * sum2 + beta * c[offsetc + (row + 2) + col * ldc];
          c[offsetc + (row + 3) + col * ldc] = alpha * sum3 + beta * c[offsetc + (row + 3) + col * ldc];
        } else {
          c[offsetc + (row + 0) + col * ldc] = alpha * sum0;
          c[offsetc + (row + 1) + col * ldc] = alpha * sum1;
          c[offsetc + (row + 2) + col * ldc] = alpha * sum2;
          c[offsetc + (row + 3) + col * ldc] = alpha * sum3;
        }
      }
      for (; row < m; row += 1) {
        float alphab = alpha * b[offsetb + row + col * ldb];
        float sum = 0.0f;
        int i = 0;
        for (; i < row; i += 1) {
          float aval = a[offseta + i + row * lda];
          c[offsetc + i + col * ldc] += alphab * aval;
          sum += b[offsetb + i + col * ldb] * aval;
        }
        sum += b[offsetb + i + col * ldb] * a[offseta + i + row * lda];
        if (beta != 0.0f) {
          c[offsetc + row + col * ldc] = alpha * sum + beta * c[offsetc + row + col * ldc];
        } else {
          c[offsetc + row + col * ldc] = alpha * sum;
        }
      }
    }
  }

  protected void ssymmLL(int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    final int Srow = 4;
    // C := alpha*A*B + beta*C
    int col = 0;
    for (; col < loopBound(n, 4); col += 4) {
      int row = m - 1;
      for (; row >= loopBound(m - 1, Srow); row -= 1) {
        float alphab0 = alpha * b[offsetb + row + (col + 0) * ldb];
        float alphab1 = alpha * b[offsetb + row + (col + 1) * ldb];
        float alphab2 = alpha * b[offsetb + row + (col + 2) * ldb];
        float alphab3 = alpha * b[offsetb + row + (col + 3) * ldb];
        float sum0 = 0.0f;
        float sum1 = 0.0f;
        float sum2 = 0.0f;
        float sum3 = 0.0f;
        sum0 += b[offsetb + row + (col + 0) * ldb] * a[offseta + row + row * lda];
        sum1 += b[offsetb + row + (col + 1) * ldb] * a[offseta + row + row * lda];
        sum2 += b[offsetb + row + (col + 2) * ldb] * a[offseta + row + row * lda];
        sum3 += b[offsetb + row + (col + 3) * ldb] * a[offseta + row + row * lda];
        int i = row + 1;
        for (; i < m; i += 1) {
          float airow = a[offseta + i + row * lda];
          c[offsetc + i + (col + 0) * ldc] += alphab0 * airow;
          c[offsetc + i + (col + 1) * ldc] += alphab1 * airow;
          c[offsetc + i + (col + 2) * ldc] += alphab2 * airow;
          c[offsetc + i + (col + 3) * ldc] += alphab3 * airow;
          sum0 += b[offsetb + i + (col + 0) * ldb] * airow;
          sum1 += b[offsetb + i + (col + 1) * ldb] * airow;
          sum2 += b[offsetb + i + (col + 2) * ldb] * airow;
          sum3 += b[offsetb + i + (col + 3) * ldb] * airow;
        }
        if (beta != 0.0f) {
          c[offsetc + row + (col + 0) * ldc] = alpha * sum0 + beta * c[offsetc + row + (col + 0) * ldc];
          c[offsetc + row + (col + 1) * ldc] = alpha * sum1 + beta * c[offsetc + row + (col + 1) * ldc];
          c[offsetc + row + (col + 2) * ldc] = alpha * sum2 + beta * c[offsetc + row + (col + 2) * ldc];
          c[offsetc + row + (col + 3) * ldc] = alpha * sum3 + beta * c[offsetc + row + (col + 3) * ldc];
        } else {
          c[offsetc + row + (col + 0) * ldc] = alpha * sum0;
          c[offsetc + row + (col + 1) * ldc] = alpha * sum1;
          c[offsetc + row + (col + 2) * ldc] = alpha * sum2;
          c[offsetc + row + (col + 3) * ldc] = alpha * sum3;
        }
      }
      for (row -= Srow - 1; row >= 0; row -= Srow) {
        float a00 = a[offseta + (row + 0) + (row + 0) * lda];
        float a10 = a[offseta + (row + 1) + (row + 0) * lda];
        float a11 = a[offseta + (row + 1) + (row + 1) * lda];
        float a20 = a[offseta + (row + 2) + (row + 0) * lda];
        float a21 = a[offseta + (row + 2) + (row + 1) * lda];
        float a22 = a[offseta + (row + 2) + (row + 2) * lda];
        float a30 = a[offseta + (row + 3) + (row + 0) * lda];
        float a31 = a[offseta + (row + 3) + (row + 1) * lda];
        float a32 = a[offseta + (row + 3) + (row + 2) * lda];
        float a33 = a[offseta + (row + 3) + (row + 3) * lda];
        float b00 = b[offsetb + (row + 0) + (col + 0) * ldb];
        float b10 = b[offsetb + (row + 1) + (col + 0) * ldb];
        float b20 = b[offsetb + (row + 2) + (col + 0) * ldb];
        float b30 = b[offsetb + (row + 3) + (col + 0) * ldb];
        float b01 = b[offsetb + (row + 0) + (col + 1) * ldb];
        float b11 = b[offsetb + (row + 1) + (col + 1) * ldb];
        float b21 = b[offsetb + (row + 2) + (col + 1) * ldb];
        float b31 = b[offsetb + (row + 3) + (col + 1) * ldb];
        float b02 = b[offsetb + (row + 0) + (col + 2) * ldb];
        float b12 = b[offsetb + (row + 1) + (col + 2) * ldb];
        float b22 = b[offsetb + (row + 2) + (col + 2) * ldb];
        float b32 = b[offsetb + (row + 3) + (col + 2) * ldb];
        float b03 = b[offsetb + (row + 0) + (col + 3) * ldb];
        float b13 = b[offsetb + (row + 1) + (col + 3) * ldb];
        float b23 = b[offsetb + (row + 2) + (col + 3) * ldb];
        float b33 = b[offsetb + (row + 3) + (col + 3) * ldb];
        float alphab00 = alpha * b00;
        float alphab10 = alpha * b10;
        float alphab20 = alpha * b20;
        float alphab30 = alpha * b30;
        float alphab01 = alpha * b01;
        float alphab11 = alpha * b11;
        float alphab21 = alpha * b21;
        float alphab31 = alpha * b31;
        float alphab02 = alpha * b02;
        float alphab12 = alpha * b12;
        float alphab22 = alpha * b22;
        float alphab32 = alpha * b32;
        float alphab03 = alpha * b03;
        float alphab13 = alpha * b13;
        float alphab23 = alpha * b23;
        float alphab33 = alpha * b33;
        float sum00 = 0.0f;
        float sum10 = 0.0f;
        float sum20 = 0.0f;
        float sum30 = 0.0f;
        float sum01 = 0.0f;
        float sum11 = 0.0f;
        float sum21 = 0.0f;
        float sum31 = 0.0f;
        float sum02 = 0.0f;
        float sum12 = 0.0f;
        float sum22 = 0.0f;
        float sum32 = 0.0f;
        float sum03 = 0.0f;
        float sum13 = 0.0f;
        float sum23 = 0.0f;
        float sum33 = 0.0f;
        sum00 += b00 * a00 + b10 * a10 + b20 * a20 + b30 * a30;
        sum10 += b00 * a10 + b10 * a11 + b20 * a21 + b30 * a31;
        sum20 += b00 * a20 + b10 * a21 + b20 * a22 + b30 * a32;
        sum30 += b00 * a30 + b10 * a31 + b20 * a32 + b30 * a33;
        sum01 += b01 * a00 + b11 * a10 + b21 * a20 + b31 * a30;
        sum11 += b01 * a10 + b11 * a11 + b21 * a21 + b31 * a31;
        sum21 += b01 * a20 + b11 * a21 + b21 * a22 + b31 * a32;
        sum31 += b01 * a30 + b11 * a31 + b21 * a32 + b31 * a33;
        sum02 += b02 * a00 + b12 * a10 + b22 * a20 + b32 * a30;
        sum12 += b02 * a10 + b12 * a11 + b22 * a21 + b32 * a31;
        sum22 += b02 * a20 + b12 * a21 + b22 * a22 + b32 * a32;
        sum32 += b02 * a30 + b12 * a31 + b22 * a32 + b32 * a33;
        sum03 += b03 * a00 + b13 * a10 + b23 * a20 + b33 * a30;
        sum13 += b03 * a10 + b13 * a11 + b23 * a21 + b33 * a31;
        sum23 += b03 * a20 + b13 * a21 + b23 * a22 + b33 * a32;
        sum33 += b03 * a30 + b13 * a31 + b23 * a32 + b33 * a33;
        int i = row + 4;
        for (; i < m; i += 1) {
          float a0 = a[offseta + i + (row + 0) * lda];
          float a1 = a[offseta + i + (row + 1) * lda];
          float a2 = a[offseta + i + (row + 2) * lda];
          float a3 = a[offseta + i + (row + 3) * lda];
          c[offsetc + i + (col + 0) * ldc] += alphab00 * a0
                                           +  alphab10 * a1
                                           +  alphab20 * a2
                                           +  alphab30 * a3;
          c[offsetc + i + (col + 1) * ldc] += alphab01 * a0
                                           +  alphab11 * a1
                                           +  alphab21 * a2
                                           +  alphab31 * a3;
          c[offsetc + i + (col + 2) * ldc] += alphab02 * a0
                                           +  alphab12 * a1
                                           +  alphab22 * a2
                                           +  alphab32 * a3;
          c[offsetc + i + (col + 3) * ldc] += alphab03 * a0
                                           +  alphab13 * a1
                                           +  alphab23 * a2
                                           +  alphab33 * a3;
          float b0 = b[offsetb + i + (col + 0) * ldb];
          float b1 = b[offsetb + i + (col + 1) * ldb];
          float b2 = b[offsetb + i + (col + 2) * ldb];
          float b3 = b[offsetb + i + (col + 3) * ldb];
          sum00 += b0 * a0;
          sum10 += b0 * a1;
          sum20 += b0 * a2;
          sum30 += b0 * a3;
          sum01 += b1 * a0;
          sum11 += b1 * a1;
          sum21 += b1 * a2;
          sum31 += b1 * a3;
          sum02 += b2 * a0;
          sum12 += b2 * a1;
          sum22 += b2 * a2;
          sum32 += b2 * a3;
          sum03 += b3 * a0;
          sum13 += b3 * a1;
          sum23 += b3 * a2;
          sum33 += b3 * a3;
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00 + beta * c[offsetc + (row + 0) + (col + 0) * ldc];
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10 + beta * c[offsetc + (row + 1) + (col + 0) * ldc];
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20 + beta * c[offsetc + (row + 2) + (col + 0) * ldc];
          c[offsetc + (row + 3) + (col + 0) * ldc] = alpha * sum30 + beta * c[offsetc + (row + 3) + (col + 0) * ldc];
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01 + beta * c[offsetc + (row + 0) + (col + 1) * ldc];
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11 + beta * c[offsetc + (row + 1) + (col + 1) * ldc];
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21 + beta * c[offsetc + (row + 2) + (col + 1) * ldc];
          c[offsetc + (row + 3) + (col + 1) * ldc] = alpha * sum31 + beta * c[offsetc + (row + 3) + (col + 1) * ldc];
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02 + beta * c[offsetc + (row + 0) + (col + 2) * ldc];
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12 + beta * c[offsetc + (row + 1) + (col + 2) * ldc];
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22 + beta * c[offsetc + (row + 2) + (col + 2) * ldc];
          c[offsetc + (row + 3) + (col + 2) * ldc] = alpha * sum32 + beta * c[offsetc + (row + 3) + (col + 2) * ldc];
          c[offsetc + (row + 0) + (col + 3) * ldc] = alpha * sum03 + beta * c[offsetc + (row + 0) + (col + 3) * ldc];
          c[offsetc + (row + 1) + (col + 3) * ldc] = alpha * sum13 + beta * c[offsetc + (row + 1) + (col + 3) * ldc];
          c[offsetc + (row + 2) + (col + 3) * ldc] = alpha * sum23 + beta * c[offsetc + (row + 2) + (col + 3) * ldc];
          c[offsetc + (row + 3) + (col + 3) * ldc] = alpha * sum33 + beta * c[offsetc + (row + 3) + (col + 3) * ldc];
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
          c[offsetc + (row + 1) + (col + 0) * ldc] = alpha * sum10;
          c[offsetc + (row + 2) + (col + 0) * ldc] = alpha * sum20;
          c[offsetc + (row + 3) + (col + 0) * ldc] = alpha * sum30;
          c[offsetc + (row + 0) + (col + 1) * ldc] = alpha * sum01;
          c[offsetc + (row + 1) + (col + 1) * ldc] = alpha * sum11;
          c[offsetc + (row + 2) + (col + 1) * ldc] = alpha * sum21;
          c[offsetc + (row + 3) + (col + 1) * ldc] = alpha * sum31;
          c[offsetc + (row + 0) + (col + 2) * ldc] = alpha * sum02;
          c[offsetc + (row + 1) + (col + 2) * ldc] = alpha * sum12;
          c[offsetc + (row + 2) + (col + 2) * ldc] = alpha * sum22;
          c[offsetc + (row + 3) + (col + 2) * ldc] = alpha * sum32;
          c[offsetc + (row + 0) + (col + 3) * ldc] = alpha * sum03;
          c[offsetc + (row + 1) + (col + 3) * ldc] = alpha * sum13;
          c[offsetc + (row + 2) + (col + 3) * ldc] = alpha * sum23;
          c[offsetc + (row + 3) + (col + 3) * ldc] = alpha * sum33;
        }
      }
    }
    for (; col < n; col += 1) {
      int row = m - 1;
      for (; row >= loopBound(m - 1, Srow); row -= 1) {
        float alphab0 = alpha * b[offsetb + row + col * ldb];
        float sum0 = 0.0f;
        sum0 += b[offsetb + row + col * ldb] * a[offseta + row + row * lda];
        int i = row + 1;
        for (; i < m; i += 1) {
          float a0 = a[offseta + i + row * lda];
          c[offsetc + i + col * ldc] += alphab0 * a0;
          sum0 += b[offsetb + i + col * ldb] * a0;
        }
        if (beta != 0.0f) {
          c[offsetc + row + col * ldc] = alpha * sum0 + beta * c[offsetc + row + col * ldc];
        } else {
          c[offsetc + row + col * ldc] = alpha * sum0;
        }
      }
      for (row -= Srow - 1; row >= 0; row -= Srow) {
        float alphab0 = alpha * b[offsetb + (row + 0) + col * ldb];
        float alphab1 = alpha * b[offsetb + (row + 1) + col * ldb];
        float alphab2 = alpha * b[offsetb + (row + 2) + col * ldb];
        float alphab3 = alpha * b[offsetb + (row + 3) + col * ldb];
        float a00 = a[offseta + (row + 0) + (row + 0) * lda];
        float a10 = a[offseta + (row + 1) + (row + 0) * lda];
        float a11 = a[offseta + (row + 1) + (row + 1) * lda];
        float a20 = a[offseta + (row + 2) + (row + 0) * lda];
        float a21 = a[offseta + (row + 2) + (row + 1) * lda];
        float a22 = a[offseta + (row + 2) + (row + 2) * lda];
        float a30 = a[offseta + (row + 3) + (row + 0) * lda];
        float a31 = a[offseta + (row + 3) + (row + 1) * lda];
        float a32 = a[offseta + (row + 3) + (row + 2) * lda];
        float a33 = a[offseta + (row + 3) + (row + 3) * lda];
        float b0 = b[offsetb + (row + 0) + col * ldb];
        float b1 = b[offsetb + (row + 1) + col * ldb];
        float b2 = b[offsetb + (row + 2) + col * ldb];
        float b3 = b[offsetb + (row + 3) + col * ldb];
        float sum0 = 0.0f;
        float sum1 = 0.0f;
        float sum2 = 0.0f;
        float sum3 = 0.0f;
        sum0 += b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30;
        sum1 += b0 * a10 + b1 * a11 + b2 * a21 + b3 * a31;
        sum2 += b0 * a20 + b1 * a21 + b2 * a22 + b3 * a32;
        sum3 += b0 * a30 + b1 * a31 + b2 * a32 + b3 * a33;
        int i = row + 4;
        for (; i < m; i += 1) {
          float a0 = a[offseta + i + (row + 0) * lda];
          float a1 = a[offseta + i + (row + 1) * lda];
          float a2 = a[offseta + i + (row + 2) * lda];
          float a3 = a[offseta + i + (row + 3) * lda];
          c[offsetc + i + col * ldc] += alphab0 * a0
                                     +  alphab1 * a1
                                     +  alphab2 * a2
                                     +  alphab3 * a3;
          float bicol = b[offsetb + i + col * ldb];
          sum0 += bicol * a0;
          sum1 += bicol * a1;
          sum2 += bicol * a2;
          sum3 += bicol * a3;
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + col * ldc] = alpha * sum0 + beta * c[offsetc + (row + 0) + col * ldc];
          c[offsetc + (row + 1) + col * ldc] = alpha * sum1 + beta * c[offsetc + (row + 1) + col * ldc];
          c[offsetc + (row + 2) + col * ldc] = alpha * sum2 + beta * c[offsetc + (row + 2) + col * ldc];
          c[offsetc + (row + 3) + col * ldc] = alpha * sum3 + beta * c[offsetc + (row + 3) + col * ldc];
        } else {
          c[offsetc + (row + 0) + col * ldc] = alpha * sum0;
          c[offsetc + (row + 1) + col * ldc] = alpha * sum1;
          c[offsetc + (row + 2) + col * ldc] = alpha * sum2;
          c[offsetc + (row + 3) + col * ldc] = alpha * sum3;
        }
      }
    }
  }

  protected void ssymmRU(int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    // C := alpha*B*A + beta*C
    org.netlib.blas.Ssymm.ssymm("R", "U", m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected void ssymmRL(int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    // C := alpha*B*A + beta*C
    org.netlib.blas.Ssymm.ssymm("R", "L", m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected void dsymvK(String uplo, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    if (alpha == 0.0) {
      for (int i = 0, iy = incy < 0 ? (n - 1) * -incy : 0; i < n; i += 1, iy += incy) {
        if (beta != 0.0) {
          y[offsety + iy] = beta * y[offsety + iy];
        } else {
          y[offsety + iy] = 0.0;
        }
      }
    } else if (lsame("U", uplo)) {
      dsymvU(n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
    } else if (lsame("L", uplo)) {
      dsymvL(n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
    }
  }

  protected void dsymvU(int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
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
        double a0 = a[offseta + row + (col + 0) * lda];
        double a1 = a[offseta + row + (col + 1) * lda];
        double a2 = a[offseta + row + (col + 2) * lda];
        double a3 = a[offseta + row + (col + 3) * lda];
        y[offsety + jy] += alphaxix0 * a0 + alphaxix1 * a1 + alphaxix2 * a2 + alphaxix3 * a3;
        double x0 = x[offsetx + jx];
        sumiy0 += x0 * a0;
        sumiy1 += x0 * a1;
        sumiy2 += x0 * a2;
        sumiy3 += x0 * a3;
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
      if (beta != 0.0) {
        y[offsety + iy + incy * 0] = alpha * sumiy0 + beta * y[offsety + iy + incy * 0];
        y[offsety + iy + incy * 1] = alpha * sumiy1 + beta * y[offsety + iy + incy * 1];
        y[offsety + iy + incy * 2] = alpha * sumiy2 + beta * y[offsety + iy + incy * 2];
        y[offsety + iy + incy * 3] = alpha * sumiy3 + beta * y[offsety + iy + incy * 3];
      } else {
        y[offsety + iy + incy * 0] = alpha * sumiy0;
        y[offsety + iy + incy * 1] = alpha * sumiy1;
        y[offsety + iy + incy * 2] = alpha * sumiy2;
        y[offsety + iy + incy * 3] = alpha * sumiy3;
      }
    }
    for (; col < n; col += 1, ix += incx, iy += incy) {
      double alphaxix = alpha * x[offsetx + ix];
      double sumiy = 0.0;
      int row = 0, jx = incx < 0 ? (col - 1) * -incx : 0, jy = incy < 0 ? (col - 1) * -incy : 0;
      for (; row < col; row += 1, jx += incx, jy += incy) {
        double a0 = a[offseta + row + col * lda];
        y[offsety + jy] += alphaxix * a0;
        sumiy += x[offsetx + jx] * a0;
      }
      sumiy += x[offsetx + jx] * a[offseta + row + col * lda];
      if (beta != 0.0) {
        y[offsety + iy] = alpha * sumiy + beta * y[offsety + iy];
      } else {
        y[offsety + iy] = alpha * sumiy;
      }
    }
  }

  protected void dsymvL(int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    // y = beta * y
    if (beta != 1.0) {
      for (int i = 0, iy = incy < 0 ? (n - 1) * -incy : 0; i < n; i += 1, iy += incy) {
        if (beta != 0.0) {
          y[offsety + iy] = beta * y[offsety + iy];
        } else {
          y[offsety + iy] = 0.0;
        }
      }
    }
    // y += alpha * A * x 
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

  protected void ssymvK(String uplo, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    if (alpha == 0.0f) {
      for (int i = 0, iy = incy < 0 ? (n - 1) * -incy : 0; i < n; i += 1, iy += incy) {
        if (beta != 0.0f) {
          y[offsety + iy] = beta * y[offsety + iy];
        } else {
          y[offsety + iy] = 0.0f;
        }
      }
    } else if (lsame("U", uplo)) {
      ssymvU(n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
    } else if (lsame("L", uplo)) {
      ssymvL(n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
    }
  }

  protected void ssymvU(int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
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
      if (beta != 0.0f) {
        y[offsety + iy + incy * 0] = alpha * sumiy0 + beta * y[offsety + iy + incy * 0];
        y[offsety + iy + incy * 1] = alpha * sumiy1 + beta * y[offsety + iy + incy * 1];
        y[offsety + iy + incy * 2] = alpha * sumiy2 + beta * y[offsety + iy + incy * 2];
        y[offsety + iy + incy * 3] = alpha * sumiy3 + beta * y[offsety + iy + incy * 3];
      } else {
        y[offsety + iy + incy * 0] = alpha * sumiy0;
        y[offsety + iy + incy * 1] = alpha * sumiy1;
        y[offsety + iy + incy * 2] = alpha * sumiy2;
        y[offsety + iy + incy * 3] = alpha * sumiy3;
      }
    }
    for (; col < n; col += 1, ix += incx, iy += incy) {
      float alphaxix = alpha * x[offsetx + ix];
      float sumiy = 0.0f;
      int row = 0, jx = incx < 0 ? (col - 1) * -incx : 0, jy = incy < 0 ? (col - 1) * -incy : 0;
      for (; row < col; row += 1, jx += incx, jy += incy) {
        y[offsety + jy] += alphaxix * a[offseta + row + col * lda];
        sumiy += x[offsetx + jx] * a[offseta + row + col * lda];
      }
      sumiy += x[offsetx + jx] * a[offseta + row + col * lda];
      if (beta != 0.0f) {
        y[offsety + iy] = alpha * sumiy + beta * y[offsety + iy];
      } else {
        y[offsety + iy] = alpha * sumiy;
      }
    }
  }

  protected void ssymvL(int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    // y = beta * y
    if (beta != 1.0f) {
      for (int i = 0, iy = incy < 0 ? (n - 1) * -incy : 0; i < n; i += 1, iy += incy) {
        if (beta != 0.0f) {
          y[offsety + iy] = beta * y[offsety + iy];
        } else {
          y[offsety + iy] = 0.0f;
        }
      }
    }
    // y += alpha * A * x 
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

  protected void dsyrK(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta, int lda) {
    org.netlib.blas.Dsyr.dsyr(uplo, n, alpha, x, offsetx, incx, a, offseta, lda);
  }

  protected void ssyrK(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta, int lda) {
    org.netlib.blas.Ssyr.ssyr(uplo, n, alpha, x, offsetx, incx, a, offseta, lda);
  }

  protected void dsyr2K(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
    org.netlib.blas.Dsyr2.dsyr2(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
  }

  protected void ssyr2K(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
    org.netlib.blas.Ssyr2.ssyr2(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
  }

  protected void dsyr2kK(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    org.netlib.blas.Dsyr2k.dsyr2k(uplo, trans, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected void ssyr2kK(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    org.netlib.blas.Ssyr2k.ssyr2k(uplo, trans, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
  }

  protected void dsyrkK(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double beta, double[] c, int offsetc, int ldc) {
    org.netlib.blas.Dsyrk.dsyrk(uplo, trans, n, k, alpha, a, offseta, lda, beta, c, offsetc, ldc);
  }

  protected void ssyrkK(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float beta, float[] c, int offsetc, int ldc) {
    org.netlib.blas.Ssyrk.ssyrk(uplo, trans, n, k, alpha, a, offseta, lda, beta, c, offsetc, ldc);
  }

  protected void dtbmvK(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    org.netlib.blas.Dtbmv.dtbmv(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }

  protected void stbmvK(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    org.netlib.blas.Stbmv.stbmv(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }

  protected void dtbsvK(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    org.netlib.blas.Dtbsv.dtbsv(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }

  protected void stbsvK(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    org.netlib.blas.Stbsv.stbsv(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
  }

  protected void dtpmvK(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx) {
    org.netlib.blas.Dtpmv.dtpmv(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
  }

  protected void stpmvK(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx) {
    org.netlib.blas.Stpmv.stpmv(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
  }

  protected void dtpsvK(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx) {
    org.netlib.blas.Dtpsv.dtpsv(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
  }

  protected void stpsvK(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx) {
    org.netlib.blas.Stpsv.stpsv(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
  }

  protected void dtrmmK(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
    org.netlib.blas.Dtrmm.dtrmm(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }

  protected void strmmK(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
    org.netlib.blas.Strmm.strmm(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }

  protected void dtrmvK(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    org.netlib.blas.Dtrmv.dtrmv(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }

  protected void strmvK(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    org.netlib.blas.Strmv.strmv(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }

  protected void dtrsmK(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
    org.netlib.blas.Dtrsm.dtrsm(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }

  protected void strsmK(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
    org.netlib.blas.Strsm.strsm(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
  }

  protected void dtrsvK(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
    org.netlib.blas.Dtrsv.dtrsv(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }

  protected void strsvK(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
    org.netlib.blas.Strsv.strsv(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
  }

  protected int idamaxK(int n, double[] x, int offsetx, int incx) {
    return org.netlib.blas.Idamax.idamax(n, x, offsetx, incx);
  }

  protected int isamaxK(int n, float[] x, int offsetx, int incx) {
    return org.netlib.blas.Isamax.isamax(n, x, offsetx, incx);
  }
}
