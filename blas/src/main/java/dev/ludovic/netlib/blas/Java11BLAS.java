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

class Java11BLAS extends Java8BLAS {

  private static final Java11BLAS instance = new Java11BLAS();

  protected Java11BLAS() {}

  public static JavaBLAS getInstance() {
    return instance;
  }

  protected void daxpyK(int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
      for (int ix = 0, iy = 0; (ix < n) && (iy < n); ix++, iy++) {
        y[offsety + iy] = Math.fma(alpha, x[offsetx + ix], y[offsety + iy]);
      }
    } else {
      for (int ix = incx < 0 ? (n - 1) * -incx : 0,
               iy = incy < 0 ? (n - 1) * -incy : 0;
           (incx < 0 ? ix >= 0 : ix < n * incx)
             && (incy < 0 ? iy >= 0 : iy < n * incy);
           ix += incx, iy += incy) {
        y[offsety + iy] = Math.fma(alpha, x[offsetx + ix], y[offsety + iy]);
      }
    }
  }

  protected void saxpyK(int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
      for (int ix = 0, iy = 0; (ix < n) && (iy < n); ix++, iy++) {
        y[offsety + iy] = Math.fma(alpha, x[offsetx + ix], y[offsety + iy]);
      }
    } else {
      for (int ix = incx < 0 ? (n - 1) * -incx : 0,
               iy = incy < 0 ? (n - 1) * -incy : 0;
           (incx < 0 ? ix >= 0 : ix < n * incx)
             && (incy < 0 ? iy >= 0 : iy < n * incy);
           ix += incx, iy += incy) {
        y[offsety + iy] = Math.fma(alpha, x[offsetx + ix], y[offsety + iy]);
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
        sum0 = Math.fma(x[offsetx + ix + 0], y[offsety + iy + 0], sum0);
        sum1 = Math.fma(x[offsetx + ix + 1], y[offsety + iy + 1], sum1);
        sum2 = Math.fma(x[offsetx + ix + 2], y[offsety + iy + 2], sum2);
        sum3 = Math.fma(x[offsetx + ix + 3], y[offsety + iy + 3], sum3);
      }
      sum += sum0 + sum1 + sum2 + sum3;
      for (; ix < n && iy < n; ix += 1, iy += 1) {
        sum = Math.fma(x[offsetx + ix], y[offsety + iy], sum);
      }
    } else {
      for (int ix = incx < 0 ? (n - 1) * -incx : 0,
              iy = incy < 0 ? (n - 1) * -incy : 0;
          (incx < 0 ? ix >= 0 : ix < n * incx)
            && (incy < 0 ? iy >= 0 : iy < n * incy);
          ix += incx, iy += incy) {
        sum = Math.fma(x[offsetx + ix], y[offsety + iy], sum);
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
        sum0 = Math.fma(x[offsetx + ix + 0], y[offsety + iy + 0], sum0);
        sum1 = Math.fma(x[offsetx + ix + 1], y[offsety + iy + 1], sum1);
        sum2 = Math.fma(x[offsetx + ix + 2], y[offsety + iy + 2], sum2);
        sum3 = Math.fma(x[offsetx + ix + 3], y[offsety + iy + 3], sum3);
      }
      sum += sum0 + sum1 + sum2 + sum3;
      for (; ix < n && iy < n; ix += 1, iy += 1) {
        sum = Math.fma(x[offsetx + ix], y[offsety + iy], sum);
      }
    } else {
      for (int ix = incx < 0 ? (n - 1) * -incx : 0,
               iy = incy < 0 ? (n - 1) * -incy : 0;
           (incx < 0 ? ix >= 0 : ix < n * incx)
             && (incy < 0 ? iy >= 0 : iy < n * incy);
           ix += incx, iy += incy) {
        sum = Math.fma(x[offsetx + ix], y[offsety + iy], sum);
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
        sum0 = Math.fma((double)x[offsetx + ix + 0], (double)y[offsety + iy + 0], sum0);
        sum1 = Math.fma((double)x[offsetx + ix + 1], (double)y[offsety + iy + 1], sum1);
        sum2 = Math.fma((double)x[offsetx + ix + 2], (double)y[offsety + iy + 2], sum2);
        sum3 = Math.fma((double)x[offsetx + ix + 3], (double)y[offsety + iy + 3], sum3);
      }
      sum += sum0 + sum1 + sum2 + sum3;
      for (; ix < n && iy < n; ix += 1, iy += 1) {
        sum = Math.fma((double)(x[offsetx + ix]), (double)(y[offsety + iy]), sum);
      }
    } else {
      for (int ix = incx < 0 ? (n - 1) * -incx : 0,
               iy = incy < 0 ? (n - 1) * -incy : 0;
           (incx < 0 ? ix >= 0 : ix < n * incx)
            && (incy < 0 ? iy >= 0 : iy < n * incy);
           ix += incx, iy += incy) {
        sum = Math.fma((double)(x[offsetx + ix]), (double)(y[offsety + iy]), sum);
      }
    }
    return (float)sum;
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
          sum00 = Math.fma(a0, b0, sum00);
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + (row + 0) + (col + 0) * ldc]);
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
          sum00 = Math.fma(a0, b0, sum00);
          sum10 = Math.fma(a1, b0, sum10);
          sum20 = Math.fma(a2, b0, sum20);
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + (row + 0) + (col + 0) * ldc]);
        c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, c[offsetc + (row + 1) + (col + 0) * ldc]);
        c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, c[offsetc + (row + 2) + (col + 0) * ldc]);
      }
      for (; row < rowe; row += 1) {
        double sum00 = 0.0;
        for (int i = is; i < ie; i += 1) {
          double a0 = a[offseta + i + (row + 0) * lda];
          double b0 = b[offsetb + i + (col + 0) * ldb];
          sum00 = Math.fma(a0, b0, sum00);
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + (row + 0) + (col + 0) * ldc]);
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
          sum00 = Math.fma(a0, b0, sum00);
          sum01 = Math.fma(a0, b1, sum01);
          sum02 = Math.fma(a0, b2, sum02);
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + (row + 0) + (col + 0) * ldc]);
        c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, c[offsetc + (row + 0) + (col + 1) * ldc]);
        c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, c[offsetc + (row + 0) + (col + 2) * ldc]);
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
          sum00 = Math.fma(a0, b0, sum00);
          sum01 = Math.fma(a0, b1, sum01);
          sum02 = Math.fma(a0, b2, sum02);
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + (row + 0) + (col + 0) * ldc]);
        c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, c[offsetc + (row + 0) + (col + 1) * ldc]);
        c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, c[offsetc + (row + 0) + (col + 2) * ldc]);
      }
    }
    for (; col < cole; col += 1) {
      int row = rows;
      for (; row < loopAlign(rows, rowe, Trow); row += 1) {
        double sum00 = 0.0;
        for (int i = is; i < ie; i += 1) {
          double a0 = a[offseta + i + (row + 0) * lda];
          double b0 = b[offsetb + i + (col + 0) * ldb];
          sum00 = Math.fma(a0, b0, sum00);
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + (row + 0) + (col + 0) * ldc]);
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
          sum00 = Math.fma(a0, b0, sum00);
          sum10 = Math.fma(a1, b0, sum10);
          sum20 = Math.fma(a2, b0, sum20);
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + (row + 0) + (col + 0) * ldc]);
        c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, c[offsetc + (row + 1) + (col + 0) * ldc]);
        c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, c[offsetc + (row + 2) + (col + 0) * ldc]);
      }
      for (; row < rowe; row += 1) {
        double sum00 = 0.0;
        for (int i = is; i < ie; i += 1) {
          double a0 = a[offseta + i + (row + 0) * lda];
          double b0 = b[offsetb + i + (col + 0) * ldb];
          sum00 = Math.fma(a0, b0, sum00);
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + (row + 0) + (col + 0) * ldc]);
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
      sum00 = Math.fma(a0, b0, sum00);
      sum10 = Math.fma(a1, b0, sum10);
      sum20 = Math.fma(a2, b0, sum20);
      double b1 = b[offsetb + i + (col + 1) * ldb];
      sum01 = Math.fma(a0, b1, sum01);
      sum11 = Math.fma(a1, b1, sum11);
      sum21 = Math.fma(a2, b1, sum21);
      double b2 = b[offsetb + i + (col + 2) * ldb];
      sum02 = Math.fma(a0, b2, sum02);
      sum12 = Math.fma(a1, b2, sum12);
      sum22 = Math.fma(a2, b2, sum22);
    }
    for (; i < loopBound(ie, Ti); i += Ti) {
      double a00 = a[offseta + (i + 0) + (row + 0) * lda];
      double a01 = a[offseta + (i + 0) + (row + 1) * lda];
      double a02 = a[offseta + (i + 0) + (row + 2) * lda];
      double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
      sum00 = Math.fma(a00, b00, sum00);
      sum10 = Math.fma(a01, b00, sum10);
      sum20 = Math.fma(a02, b00, sum20);
      double b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
      sum01 = Math.fma(a00, b01, sum01);
      sum11 = Math.fma(a01, b01, sum11);
      sum21 = Math.fma(a02, b01, sum21);
      double b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
      sum02 = Math.fma(a00, b02, sum02);
      sum12 = Math.fma(a01, b02, sum12);
      sum22 = Math.fma(a02, b02, sum22);
      double a10 = a[offseta + (i + 1) + (row + 0) * lda];
      double a11 = a[offseta + (i + 1) + (row + 1) * lda];
      double a12 = a[offseta + (i + 1) + (row + 2) * lda];
      double b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
      sum00 = Math.fma(a10, b10, sum00);
      sum10 = Math.fma(a11, b10, sum10);
      sum20 = Math.fma(a12, b10, sum20);
      double b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
      sum01 = Math.fma(a10, b11, sum01);
      sum11 = Math.fma(a11, b11, sum11);
      sum21 = Math.fma(a12, b11, sum21);
      double b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
      sum02 = Math.fma(a10, b12, sum02);
      sum12 = Math.fma(a11, b12, sum12);
      sum22 = Math.fma(a12, b12, sum22);
    }
    for (; i < ie; i += 1) {
      double a0 = a[offseta + i + (row + 0) * lda];
      double a1 = a[offseta + i + (row + 1) * lda];
      double a2 = a[offseta + i + (row + 2) * lda];
      double b0 = b[offsetb + i + (col + 0) * ldb];
      sum00 = Math.fma(a0, b0, sum00);
      sum10 = Math.fma(a1, b0, sum10);
      sum20 = Math.fma(a2, b0, sum20);
      double b1 = b[offsetb + i + (col + 1) * ldb];
      sum01 = Math.fma(a0, b1, sum01);
      sum11 = Math.fma(a1, b1, sum11);
      sum21 = Math.fma(a2, b1, sum21);
      double b2 = b[offsetb + i + (col + 2) * ldb];
      sum02 = Math.fma(a0, b2, sum02);
      sum12 = Math.fma(a1, b2, sum12);
      sum22 = Math.fma(a2, b2, sum22);
    }
    c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + (row + 0) + (col + 0) * ldc]);
    c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, c[offsetc + (row + 0) + (col + 1) * ldc]);
    c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, c[offsetc + (row + 0) + (col + 2) * ldc]);
    c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, c[offsetc + (row + 1) + (col + 0) * ldc]);
    c[offsetc + (row + 1) + (col + 1) * ldc] = Math.fma(alpha, sum11, c[offsetc + (row + 1) + (col + 1) * ldc]);
    c[offsetc + (row + 1) + (col + 2) * ldc] = Math.fma(alpha, sum12, c[offsetc + (row + 1) + (col + 2) * ldc]);
    c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, c[offsetc + (row + 2) + (col + 0) * ldc]);
    c[offsetc + (row + 2) + (col + 1) * ldc] = Math.fma(alpha, sum21, c[offsetc + (row + 2) + (col + 1) * ldc]);
    c[offsetc + (row + 2) + (col + 2) * ldc] = Math.fma(alpha, sum22, c[offsetc + (row + 2) + (col + 2) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          sum10 = Math.fma(a10, b00, sum10);
          sum11 = Math.fma(a10, b01, sum11);
          sum12 = Math.fma(a10, b02, sum12);
          sum20 = Math.fma(a20, b00, sum20);
          sum21 = Math.fma(a20, b01, sum21);
          sum22 = Math.fma(a20, b02, sum22);
          double a01 = a[offseta + (row + 0) + (i + 1) * lda];
          double a11 = a[offseta + (row + 1) + (i + 1) * lda];
          double a21 = a[offseta + (row + 2) + (i + 1) * lda];
          double b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          double b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
          double b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum01 = Math.fma(a01, b11, sum01);
          sum02 = Math.fma(a01, b12, sum02);
          sum10 = Math.fma(a11, b10, sum10);
          sum11 = Math.fma(a11, b11, sum11);
          sum12 = Math.fma(a11, b12, sum12);
          sum20 = Math.fma(a21, b10, sum20);
          sum21 = Math.fma(a21, b11, sum21);
          sum22 = Math.fma(a21, b12, sum22);
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double a10 = a[offseta + (row + 1) + (i + 0) * lda];
          double a20 = a[offseta + (row + 2) + (i + 0) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          double b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          double b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          sum10 = Math.fma(a10, b00, sum10);
          sum11 = Math.fma(a10, b01, sum11);
          sum12 = Math.fma(a10, b02, sum12);
          sum20 = Math.fma(a20, b00, sum20);
          sum21 = Math.fma(a20, b01, sum21);
          sum22 = Math.fma(a20, b02, sum22);
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + (row + 0) + (col + 1) * ldc]);
          c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + (row + 0) + (col + 2) * ldc]);
          c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + (row + 1) + (col + 0) * ldc]);
          c[offsetc + (row + 1) + (col + 1) * ldc] = Math.fma(alpha, sum11, beta * c[offsetc + (row + 1) + (col + 1) * ldc]);
          c[offsetc + (row + 1) + (col + 2) * ldc] = Math.fma(alpha, sum12, beta * c[offsetc + (row + 1) + (col + 2) * ldc]);
          c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + (row + 2) + (col + 0) * ldc]);
          c[offsetc + (row + 2) + (col + 1) * ldc] = Math.fma(alpha, sum21, beta * c[offsetc + (row + 2) + (col + 1) * ldc]);
          c[offsetc + (row + 2) + (col + 2) * ldc] = Math.fma(alpha, sum22, beta * c[offsetc + (row + 2) + (col + 2) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          double a01 = a[offseta + (row + 0) + (i + 1) * lda];
          double b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          double b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
          double b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum01 = Math.fma(a01, b11, sum01);
          sum02 = Math.fma(a01, b12, sum02);
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          double b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          double b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + (row + 0) + (col + 1) * ldc]);
          c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + (row + 0) + (col + 2) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum10 = Math.fma(a10, b00, sum10);
          sum20 = Math.fma(a20, b00, sum20);
          double a01 = a[offseta + (row + 0) + (i + 1) * lda];
          double a11 = a[offseta + (row + 1) + (i + 1) * lda];
          double a21 = a[offseta + (row + 2) + (i + 1) * lda];
          double b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum10 = Math.fma(a11, b10, sum10);
          sum20 = Math.fma(a21, b10, sum20);
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double a10 = a[offseta + (row + 1) + (i + 0) * lda];
          double a20 = a[offseta + (row + 2) + (i + 0) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum10 = Math.fma(a10, b00, sum10);
          sum20 = Math.fma(a20, b00, sum20);
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + (row + 1) + (col + 0) * ldc]);
          c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + (row + 2) + (col + 0) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          double a01 = a[offseta + (row + 0) + (i + 1) * lda];
          double b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          sum10 = Math.fma(a10, b00, sum10);
          sum11 = Math.fma(a10, b01, sum11);
          sum12 = Math.fma(a10, b02, sum12);
          sum20 = Math.fma(a20, b00, sum20);
          sum21 = Math.fma(a20, b01, sum21);
          sum22 = Math.fma(a20, b02, sum22);
          double a01 = a[offseta + (row + 0) + (i + 1) * lda];
          double a11 = a[offseta + (row + 1) + (i + 1) * lda];
          double a21 = a[offseta + (row + 2) + (i + 1) * lda];
          double b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          double b11 = b[offsetb + (col + 1) + (i + 1) * ldb];
          double b12 = b[offsetb + (col + 2) + (i + 1) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum01 = Math.fma(a01, b11, sum01);
          sum02 = Math.fma(a01, b12, sum02);
          sum10 = Math.fma(a11, b10, sum10);
          sum11 = Math.fma(a11, b11, sum11);
          sum12 = Math.fma(a11, b12, sum12);
          sum20 = Math.fma(a21, b10, sum20);
          sum21 = Math.fma(a21, b11, sum21);
          sum22 = Math.fma(a21, b12, sum22);
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double a10 = a[offseta + (row + 1) + (i + 0) * lda];
          double a20 = a[offseta + (row + 2) + (i + 0) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          double b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          double b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          sum10 = Math.fma(a10, b00, sum10);
          sum11 = Math.fma(a10, b01, sum11);
          sum12 = Math.fma(a10, b02, sum12);
          sum20 = Math.fma(a20, b00, sum20);
          sum21 = Math.fma(a20, b01, sum21);
          sum22 = Math.fma(a20, b02, sum22);
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + (row + 0) + (col + 1) * ldc]);
          c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + (row + 0) + (col + 2) * ldc]);
          c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + (row + 1) + (col + 0) * ldc]);
          c[offsetc + (row + 1) + (col + 1) * ldc] = Math.fma(alpha, sum11, beta * c[offsetc + (row + 1) + (col + 1) * ldc]);
          c[offsetc + (row + 1) + (col + 2) * ldc] = Math.fma(alpha, sum12, beta * c[offsetc + (row + 1) + (col + 2) * ldc]);
          c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + (row + 2) + (col + 0) * ldc]);
          c[offsetc + (row + 2) + (col + 1) * ldc] = Math.fma(alpha, sum21, beta * c[offsetc + (row + 2) + (col + 1) * ldc]);
          c[offsetc + (row + 2) + (col + 2) * ldc] = Math.fma(alpha, sum22, beta * c[offsetc + (row + 2) + (col + 2) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          double a01 = a[offseta + (row + 0) + (i + 1) * lda];
          double b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          double b11 = b[offsetb + (col + 1) + (i + 1) * ldb];
          double b12 = b[offsetb + (col + 2) + (i + 1) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum01 = Math.fma(a01, b11, sum01);
          sum02 = Math.fma(a01, b12, sum02);
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          double b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          double b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + (row + 0) + (col + 1) * ldc]);
          c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + (row + 0) + (col + 2) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum10 = Math.fma(a10, b00, sum10);
          sum20 = Math.fma(a20, b00, sum20);
          double a01 = a[offseta + (row + 0) + (i + 1) * lda];
          double a11 = a[offseta + (row + 1) + (i + 1) * lda];
          double a21 = a[offseta + (row + 2) + (i + 1) * lda];
          double b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum10 = Math.fma(a11, b10, sum10);
          sum20 = Math.fma(a21, b10, sum20);
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double a10 = a[offseta + (row + 1) + (i + 0) * lda];
          double a20 = a[offseta + (row + 2) + (i + 0) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum10 = Math.fma(a10, b00, sum10);
          sum20 = Math.fma(a20, b00, sum20);
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + (row + 1) + (col + 0) * ldc]);
          c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + (row + 2) + (col + 0) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          double a01 = a[offseta + (row + 0) + (i + 1) * lda];
          double b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (row + 0) + (i + 0) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          sum10 = Math.fma(a10, b00, sum10);
          sum11 = Math.fma(a10, b01, sum11);
          sum12 = Math.fma(a10, b02, sum12);
          sum20 = Math.fma(a20, b00, sum20);
          sum21 = Math.fma(a20, b01, sum21);
          sum22 = Math.fma(a20, b02, sum22);
          double a01 = a[offseta + (i + 1) + (row + 0) * lda];
          double a11 = a[offseta + (i + 1) + (row + 1) * lda];
          double a21 = a[offseta + (i + 1) + (row + 2) * lda];
          double b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          double b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
          double b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum01 = Math.fma(a01, b11, sum01);
          sum02 = Math.fma(a01, b12, sum02);
          sum10 = Math.fma(a11, b10, sum10);
          sum11 = Math.fma(a11, b11, sum11);
          sum12 = Math.fma(a11, b12, sum12);
          sum20 = Math.fma(a21, b10, sum20);
          sum21 = Math.fma(a21, b11, sum21);
          sum22 = Math.fma(a21, b12, sum22);
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double a10 = a[offseta + (i + 0) + (row + 1) * lda];
          double a20 = a[offseta + (i + 0) + (row + 2) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          double b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          double b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          sum10 = Math.fma(a10, b00, sum10);
          sum11 = Math.fma(a10, b01, sum11);
          sum12 = Math.fma(a10, b02, sum12);
          sum20 = Math.fma(a20, b00, sum20);
          sum21 = Math.fma(a20, b01, sum21);
          sum22 = Math.fma(a20, b02, sum22);
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + (row + 0) + (col + 1) * ldc]);
          c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + (row + 0) + (col + 2) * ldc]);
          c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + (row + 1) + (col + 0) * ldc]);
          c[offsetc + (row + 1) + (col + 1) * ldc] = Math.fma(alpha, sum11, beta * c[offsetc + (row + 1) + (col + 1) * ldc]);
          c[offsetc + (row + 1) + (col + 2) * ldc] = Math.fma(alpha, sum12, beta * c[offsetc + (row + 1) + (col + 2) * ldc]);
          c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + (row + 2) + (col + 0) * ldc]);
          c[offsetc + (row + 2) + (col + 1) * ldc] = Math.fma(alpha, sum21, beta * c[offsetc + (row + 2) + (col + 1) * ldc]);
          c[offsetc + (row + 2) + (col + 2) * ldc] = Math.fma(alpha, sum22, beta * c[offsetc + (row + 2) + (col + 2) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          double a01 = a[offseta + (i + 1) + (row + 0) * lda];
          double b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          double b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
          double b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum01 = Math.fma(a01, b11, sum01);
          sum02 = Math.fma(a01, b12, sum02);
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          double b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          double b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + (row + 0) + (col + 1) * ldc]);
          c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + (row + 0) + (col + 2) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum10 = Math.fma(a10, b00, sum10);
          sum20 = Math.fma(a20, b00, sum20);
          double a01 = a[offseta + (i + 1) + (row + 0) * lda];
          double a11 = a[offseta + (i + 1) + (row + 1) * lda];
          double a21 = a[offseta + (i + 1) + (row + 2) * lda];
          double b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum10 = Math.fma(a11, b10, sum10);
          sum20 = Math.fma(a21, b10, sum20);
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double a10 = a[offseta + (i + 0) + (row + 1) * lda];
          double a20 = a[offseta + (i + 0) + (row + 2) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum10 = Math.fma(a10, b00, sum10);
          sum20 = Math.fma(a20, b00, sum20);
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + (row + 1) + (col + 0) * ldc]);
          c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + (row + 2) + (col + 0) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          double a01 = a[offseta + (i + 1) + (row + 0) * lda];
          double b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          sum10 = Math.fma(a10, b00, sum10);
          sum11 = Math.fma(a10, b01, sum11);
          sum12 = Math.fma(a10, b02, sum12);
          sum20 = Math.fma(a20, b00, sum20);
          sum21 = Math.fma(a20, b01, sum21);
          sum22 = Math.fma(a20, b02, sum22);
          double a01 = a[offseta + (i + 1) + (row + 0) * lda];
          double a11 = a[offseta + (i + 1) + (row + 1) * lda];
          double a21 = a[offseta + (i + 1) + (row + 2) * lda];
          double b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          double b11 = b[offsetb + (col + 1) + (i + 1) * ldb];
          double b12 = b[offsetb + (col + 2) + (i + 1) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum01 = Math.fma(a01, b11, sum01);
          sum02 = Math.fma(a01, b12, sum02);
          sum10 = Math.fma(a11, b10, sum10);
          sum11 = Math.fma(a11, b11, sum11);
          sum12 = Math.fma(a11, b12, sum12);
          sum20 = Math.fma(a21, b10, sum20);
          sum21 = Math.fma(a21, b11, sum21);
          sum22 = Math.fma(a21, b12, sum22);
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double a10 = a[offseta + (i + 0) + (row + 1) * lda];
          double a20 = a[offseta + (i + 0) + (row + 2) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          double b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          double b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          sum10 = Math.fma(a10, b00, sum10);
          sum11 = Math.fma(a10, b01, sum11);
          sum12 = Math.fma(a10, b02, sum12);
          sum20 = Math.fma(a20, b00, sum20);
          sum21 = Math.fma(a20, b01, sum21);
          sum22 = Math.fma(a20, b02, sum22);
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + (row + 0) + (col + 1) * ldc]);
          c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + (row + 0) + (col + 2) * ldc]);
          c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + (row + 1) + (col + 0) * ldc]);
          c[offsetc + (row + 1) + (col + 1) * ldc] = Math.fma(alpha, sum11, beta * c[offsetc + (row + 1) + (col + 1) * ldc]);
          c[offsetc + (row + 1) + (col + 2) * ldc] = Math.fma(alpha, sum12, beta * c[offsetc + (row + 1) + (col + 2) * ldc]);
          c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + (row + 2) + (col + 0) * ldc]);
          c[offsetc + (row + 2) + (col + 1) * ldc] = Math.fma(alpha, sum21, beta * c[offsetc + (row + 2) + (col + 1) * ldc]);
          c[offsetc + (row + 2) + (col + 2) * ldc] = Math.fma(alpha, sum22, beta * c[offsetc + (row + 2) + (col + 2) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          double a01 = a[offseta + (i + 1) + (row + 0) * lda];
          double b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          double b11 = b[offsetb + (col + 1) + (i + 1) * ldb];
          double b12 = b[offsetb + (col + 2) + (i + 1) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum01 = Math.fma(a01, b11, sum01);
          sum02 = Math.fma(a01, b12, sum02);
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          double b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          double b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + (row + 0) + (col + 1) * ldc]);
          c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + (row + 0) + (col + 2) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum10 = Math.fma(a10, b00, sum10);
          sum20 = Math.fma(a20, b00, sum20);
          double a01 = a[offseta + (i + 1) + (row + 0) * lda];
          double a11 = a[offseta + (i + 1) + (row + 1) * lda];
          double a21 = a[offseta + (i + 1) + (row + 2) * lda];
          double b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum10 = Math.fma(a11, b10, sum10);
          sum20 = Math.fma(a21, b10, sum20);
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double a10 = a[offseta + (i + 0) + (row + 1) * lda];
          double a20 = a[offseta + (i + 0) + (row + 2) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum10 = Math.fma(a10, b00, sum10);
          sum20 = Math.fma(a20, b00, sum20);
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + (row + 1) + (col + 0) * ldc]);
          c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + (row + 2) + (col + 0) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          double a01 = a[offseta + (i + 1) + (row + 0) * lda];
          double b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
        }
        for (; i < k; i += 1) {
          double a00 = a[offseta + (i + 0) + (row + 0) * lda];
          double b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
        }
        if (beta != 0.0) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
        }
      }
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
          sum00 = Math.fma(a0, b0, sum00);
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + (row + 0) + (col + 0) * ldc]);
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
          sum00 = Math.fma(a0, b0, sum00);
          sum10 = Math.fma(a1, b0, sum10);
          sum20 = Math.fma(a2, b0, sum20);
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + (row + 0) + (col + 0) * ldc]);
        c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, c[offsetc + (row + 1) + (col + 0) * ldc]);
        c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, c[offsetc + (row + 2) + (col + 0) * ldc]);
      }
      for (; row < rowe; row += 1) {
        float sum00 = 0.0f;
        for (int i = is; i < ie; i += 1) {
          float a0 = a[offseta + i + (row + 0) * lda];
          float b0 = b[offsetb + i + (col + 0) * ldb];
          sum00 = Math.fma(a0, b0, sum00);
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + (row + 0) + (col + 0) * ldc]);
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
          sum00 = Math.fma(a0, b0, sum00);
          sum01 = Math.fma(a0, b1, sum01);
          sum02 = Math.fma(a0, b2, sum02);
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + (row + 0) + (col + 0) * ldc]);
        c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, c[offsetc + (row + 0) + (col + 1) * ldc]);
        c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, c[offsetc + (row + 0) + (col + 2) * ldc]);
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
          sum00 = Math.fma(a0, b0, sum00);
          sum01 = Math.fma(a0, b1, sum01);
          sum02 = Math.fma(a0, b2, sum02);
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + (row + 0) + (col + 0) * ldc]);
        c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, c[offsetc + (row + 0) + (col + 1) * ldc]);
        c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, c[offsetc + (row + 0) + (col + 2) * ldc]);
      }
    }
    for (; col < cole; col += 1) {
      int row = rows;
      for (; row < loopAlign(rows, rowe, Trow); row += 1) {
        float sum00 = 0.0f;
        for (int i = is; i < ie; i += 1) {
          float a0 = a[offseta + i + (row + 0) * lda];
          float b0 = b[offsetb + i + (col + 0) * ldb];
          sum00 = Math.fma(a0, b0, sum00);
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + (row + 0) + (col + 0) * ldc]);
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
          sum00 = Math.fma(a0, b0, sum00);
          sum10 = Math.fma(a1, b0, sum10);
          sum20 = Math.fma(a2, b0, sum20);
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + (row + 0) + (col + 0) * ldc]);
        c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, c[offsetc + (row + 1) + (col + 0) * ldc]);
        c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, c[offsetc + (row + 2) + (col + 0) * ldc]);
      }
      for (; row < rowe; row += 1) {
        float sum00 = 0.0f;
        for (int i = is; i < ie; i += 1) {
          float a0 = a[offseta + i + (row + 0) * lda];
          float b0 = b[offsetb + i + (col + 0) * ldb];
          sum00 = Math.fma(a0, b0, sum00);
        }
        c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + (row + 0) + (col + 0) * ldc]);
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
      sum00 = Math.fma(a0, b0, sum00);
      sum10 = Math.fma(a1, b0, sum10);
      sum20 = Math.fma(a2, b0, sum20);
      float b1 = b[offsetb + i + (col + 1) * ldb];
      sum01 = Math.fma(a0, b1, sum01);
      sum11 = Math.fma(a1, b1, sum11);
      sum21 = Math.fma(a2, b1, sum21);
      float b2 = b[offsetb + i + (col + 2) * ldb];
      sum02 = Math.fma(a0, b2, sum02);
      sum12 = Math.fma(a1, b2, sum12);
      sum22 = Math.fma(a2, b2, sum22);
    }
    for (; i < loopBound(ie, Ti); i += Ti) {
      float a00 = a[offseta + (i + 0) + (row + 0) * lda];
      float a01 = a[offseta + (i + 0) + (row + 1) * lda];
      float a02 = a[offseta + (i + 0) + (row + 2) * lda];
      float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
      sum00 = Math.fma(a00, b00, sum00);
      sum10 = Math.fma(a01, b00, sum10);
      sum20 = Math.fma(a02, b00, sum20);
      float b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
      sum01 = Math.fma(a00, b01, sum01);
      sum11 = Math.fma(a01, b01, sum11);
      sum21 = Math.fma(a02, b01, sum21);
      float b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
      sum02 = Math.fma(a00, b02, sum02);
      sum12 = Math.fma(a01, b02, sum12);
      sum22 = Math.fma(a02, b02, sum22);
      float a10 = a[offseta + (i + 1) + (row + 0) * lda];
      float a11 = a[offseta + (i + 1) + (row + 1) * lda];
      float a12 = a[offseta + (i + 1) + (row + 2) * lda];
      float b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
      sum00 = Math.fma(a10, b10, sum00);
      sum10 = Math.fma(a11, b10, sum10);
      sum20 = Math.fma(a12, b10, sum20);
      float b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
      sum01 = Math.fma(a10, b11, sum01);
      sum11 = Math.fma(a11, b11, sum11);
      sum21 = Math.fma(a12, b11, sum21);
      float b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
      sum02 = Math.fma(a10, b12, sum02);
      sum12 = Math.fma(a11, b12, sum12);
      sum22 = Math.fma(a12, b12, sum22);
    }
    for (; i < ie; i += 1) {
      float a0 = a[offseta + i + (row + 0) * lda];
      float a1 = a[offseta + i + (row + 1) * lda];
      float a2 = a[offseta + i + (row + 2) * lda];
      float b0 = b[offsetb + i + (col + 0) * ldb];
      sum00 = Math.fma(a0, b0, sum00);
      sum10 = Math.fma(a1, b0, sum10);
      sum20 = Math.fma(a2, b0, sum20);
      float b1 = b[offsetb + i + (col + 1) * ldb];
      sum01 = Math.fma(a0, b1, sum01);
      sum11 = Math.fma(a1, b1, sum11);
      sum21 = Math.fma(a2, b1, sum21);
      float b2 = b[offsetb + i + (col + 2) * ldb];
      sum02 = Math.fma(a0, b2, sum02);
      sum12 = Math.fma(a1, b2, sum12);
      sum22 = Math.fma(a2, b2, sum22);
    }
    c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + (row + 0) + (col + 0) * ldc]);
    c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, c[offsetc + (row + 0) + (col + 1) * ldc]);
    c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, c[offsetc + (row + 0) + (col + 2) * ldc]);
    c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, c[offsetc + (row + 1) + (col + 0) * ldc]);
    c[offsetc + (row + 1) + (col + 1) * ldc] = Math.fma(alpha, sum11, c[offsetc + (row + 1) + (col + 1) * ldc]);
    c[offsetc + (row + 1) + (col + 2) * ldc] = Math.fma(alpha, sum12, c[offsetc + (row + 1) + (col + 2) * ldc]);
    c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, c[offsetc + (row + 2) + (col + 0) * ldc]);
    c[offsetc + (row + 2) + (col + 1) * ldc] = Math.fma(alpha, sum21, c[offsetc + (row + 2) + (col + 1) * ldc]);
    c[offsetc + (row + 2) + (col + 2) * ldc] = Math.fma(alpha, sum22, c[offsetc + (row + 2) + (col + 2) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          sum10 = Math.fma(a10, b00, sum10);
          sum11 = Math.fma(a10, b01, sum11);
          sum12 = Math.fma(a10, b02, sum12);
          sum20 = Math.fma(a20, b00, sum20);
          sum21 = Math.fma(a20, b01, sum21);
          sum22 = Math.fma(a20, b02, sum22);
          float a01 = a[offseta + (row + 0) + (i + 1) * lda];
          float a11 = a[offseta + (row + 1) + (i + 1) * lda];
          float a21 = a[offseta + (row + 2) + (i + 1) * lda];
          float b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          float b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
          float b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum01 = Math.fma(a01, b11, sum01);
          sum02 = Math.fma(a01, b12, sum02);
          sum10 = Math.fma(a11, b10, sum10);
          sum11 = Math.fma(a11, b11, sum11);
          sum12 = Math.fma(a11, b12, sum12);
          sum20 = Math.fma(a21, b10, sum20);
          sum21 = Math.fma(a21, b11, sum21);
          sum22 = Math.fma(a21, b12, sum22);
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float a10 = a[offseta + (row + 1) + (i + 0) * lda];
          float a20 = a[offseta + (row + 2) + (i + 0) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          float b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          float b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          sum10 = Math.fma(a10, b00, sum10);
          sum11 = Math.fma(a10, b01, sum11);
          sum12 = Math.fma(a10, b02, sum12);
          sum20 = Math.fma(a20, b00, sum20);
          sum21 = Math.fma(a20, b01, sum21);
          sum22 = Math.fma(a20, b02, sum22);
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + (row + 0) + (col + 1) * ldc]);
          c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + (row + 0) + (col + 2) * ldc]);
          c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + (row + 1) + (col + 0) * ldc]);
          c[offsetc + (row + 1) + (col + 1) * ldc] = Math.fma(alpha, sum11, beta * c[offsetc + (row + 1) + (col + 1) * ldc]);
          c[offsetc + (row + 1) + (col + 2) * ldc] = Math.fma(alpha, sum12, beta * c[offsetc + (row + 1) + (col + 2) * ldc]);
          c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + (row + 2) + (col + 0) * ldc]);
          c[offsetc + (row + 2) + (col + 1) * ldc] = Math.fma(alpha, sum21, beta * c[offsetc + (row + 2) + (col + 1) * ldc]);
          c[offsetc + (row + 2) + (col + 2) * ldc] = Math.fma(alpha, sum22, beta * c[offsetc + (row + 2) + (col + 2) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          float a01 = a[offseta + (row + 0) + (i + 1) * lda];
          float b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          float b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
          float b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum01 = Math.fma(a01, b11, sum01);
          sum02 = Math.fma(a01, b12, sum02);
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          float b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          float b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + (row + 0) + (col + 1) * ldc]);
          c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + (row + 0) + (col + 2) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum10 = Math.fma(a10, b00, sum10);
          sum20 = Math.fma(a20, b00, sum20);
          float a01 = a[offseta + (row + 0) + (i + 1) * lda];
          float a11 = a[offseta + (row + 1) + (i + 1) * lda];
          float a21 = a[offseta + (row + 2) + (i + 1) * lda];
          float b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum10 = Math.fma(a11, b10, sum10);
          sum20 = Math.fma(a21, b10, sum20);
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float a10 = a[offseta + (row + 1) + (i + 0) * lda];
          float a20 = a[offseta + (row + 2) + (i + 0) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum10 = Math.fma(a10, b00, sum10);
          sum20 = Math.fma(a20, b00, sum20);
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + (row + 1) + (col + 0) * ldc]);
          c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + (row + 2) + (col + 0) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          float a01 = a[offseta + (row + 0) + (i + 1) * lda];
          float b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          sum10 = Math.fma(a10, b00, sum10);
          sum11 = Math.fma(a10, b01, sum11);
          sum12 = Math.fma(a10, b02, sum12);
          sum20 = Math.fma(a20, b00, sum20);
          sum21 = Math.fma(a20, b01, sum21);
          sum22 = Math.fma(a20, b02, sum22);
          float a01 = a[offseta + (row + 0) + (i + 1) * lda];
          float a11 = a[offseta + (row + 1) + (i + 1) * lda];
          float a21 = a[offseta + (row + 2) + (i + 1) * lda];
          float b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          float b11 = b[offsetb + (col + 1) + (i + 1) * ldb];
          float b12 = b[offsetb + (col + 2) + (i + 1) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum01 = Math.fma(a01, b11, sum01);
          sum02 = Math.fma(a01, b12, sum02);
          sum10 = Math.fma(a11, b10, sum10);
          sum11 = Math.fma(a11, b11, sum11);
          sum12 = Math.fma(a11, b12, sum12);
          sum20 = Math.fma(a21, b10, sum20);
          sum21 = Math.fma(a21, b11, sum21);
          sum22 = Math.fma(a21, b12, sum22);
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float a10 = a[offseta + (row + 1) + (i + 0) * lda];
          float a20 = a[offseta + (row + 2) + (i + 0) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          float b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          float b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          sum10 = Math.fma(a10, b00, sum10);
          sum11 = Math.fma(a10, b01, sum11);
          sum12 = Math.fma(a10, b02, sum12);
          sum20 = Math.fma(a20, b00, sum20);
          sum21 = Math.fma(a20, b01, sum21);
          sum22 = Math.fma(a20, b02, sum22);
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + (row + 0) + (col + 1) * ldc]);
          c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + (row + 0) + (col + 2) * ldc]);
          c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + (row + 1) + (col + 0) * ldc]);
          c[offsetc + (row + 1) + (col + 1) * ldc] = Math.fma(alpha, sum11, beta * c[offsetc + (row + 1) + (col + 1) * ldc]);
          c[offsetc + (row + 1) + (col + 2) * ldc] = Math.fma(alpha, sum12, beta * c[offsetc + (row + 1) + (col + 2) * ldc]);
          c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + (row + 2) + (col + 0) * ldc]);
          c[offsetc + (row + 2) + (col + 1) * ldc] = Math.fma(alpha, sum21, beta * c[offsetc + (row + 2) + (col + 1) * ldc]);
          c[offsetc + (row + 2) + (col + 2) * ldc] = Math.fma(alpha, sum22, beta * c[offsetc + (row + 2) + (col + 2) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          float a01 = a[offseta + (row + 0) + (i + 1) * lda];
          float b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          float b11 = b[offsetb + (col + 1) + (i + 1) * ldb];
          float b12 = b[offsetb + (col + 2) + (i + 1) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum01 = Math.fma(a01, b11, sum01);
          sum02 = Math.fma(a01, b12, sum02);
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          float b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          float b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + (row + 0) + (col + 1) * ldc]);
          c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + (row + 0) + (col + 2) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum10 = Math.fma(a10, b00, sum10);
          sum20 = Math.fma(a20, b00, sum20);
          float a01 = a[offseta + (row + 0) + (i + 1) * lda];
          float a11 = a[offseta + (row + 1) + (i + 1) * lda];
          float a21 = a[offseta + (row + 2) + (i + 1) * lda];
          float b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum10 = Math.fma(a11, b10, sum10);
          sum20 = Math.fma(a21, b10, sum20);
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float a10 = a[offseta + (row + 1) + (i + 0) * lda];
          float a20 = a[offseta + (row + 2) + (i + 0) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum10 = Math.fma(a10, b00, sum10);
          sum20 = Math.fma(a20, b00, sum20);
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + (row + 1) + (col + 0) * ldc]);
          c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + (row + 2) + (col + 0) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          float a01 = a[offseta + (row + 0) + (i + 1) * lda];
          float b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (row + 0) + (i + 0) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          sum10 = Math.fma(a10, b00, sum10);
          sum11 = Math.fma(a10, b01, sum11);
          sum12 = Math.fma(a10, b02, sum12);
          sum20 = Math.fma(a20, b00, sum20);
          sum21 = Math.fma(a20, b01, sum21);
          sum22 = Math.fma(a20, b02, sum22);
          float a01 = a[offseta + (i + 1) + (row + 0) * lda];
          float a11 = a[offseta + (i + 1) + (row + 1) * lda];
          float a21 = a[offseta + (i + 1) + (row + 2) * lda];
          float b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          float b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
          float b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum01 = Math.fma(a01, b11, sum01);
          sum02 = Math.fma(a01, b12, sum02);
          sum10 = Math.fma(a11, b10, sum10);
          sum11 = Math.fma(a11, b11, sum11);
          sum12 = Math.fma(a11, b12, sum12);
          sum20 = Math.fma(a21, b10, sum20);
          sum21 = Math.fma(a21, b11, sum21);
          sum22 = Math.fma(a21, b12, sum22);
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float a10 = a[offseta + (i + 0) + (row + 1) * lda];
          float a20 = a[offseta + (i + 0) + (row + 2) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          float b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          float b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          sum10 = Math.fma(a10, b00, sum10);
          sum11 = Math.fma(a10, b01, sum11);
          sum12 = Math.fma(a10, b02, sum12);
          sum20 = Math.fma(a20, b00, sum20);
          sum21 = Math.fma(a20, b01, sum21);
          sum22 = Math.fma(a20, b02, sum22);
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + (row + 0) + (col + 1) * ldc]);
          c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + (row + 0) + (col + 2) * ldc]);
          c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + (row + 1) + (col + 0) * ldc]);
          c[offsetc + (row + 1) + (col + 1) * ldc] = Math.fma(alpha, sum11, beta * c[offsetc + (row + 1) + (col + 1) * ldc]);
          c[offsetc + (row + 1) + (col + 2) * ldc] = Math.fma(alpha, sum12, beta * c[offsetc + (row + 1) + (col + 2) * ldc]);
          c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + (row + 2) + (col + 0) * ldc]);
          c[offsetc + (row + 2) + (col + 1) * ldc] = Math.fma(alpha, sum21, beta * c[offsetc + (row + 2) + (col + 1) * ldc]);
          c[offsetc + (row + 2) + (col + 2) * ldc] = Math.fma(alpha, sum22, beta * c[offsetc + (row + 2) + (col + 2) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          float a01 = a[offseta + (i + 1) + (row + 0) * lda];
          float b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          float b11 = b[offsetb + (i + 1) + (col + 1) * ldb];
          float b12 = b[offsetb + (i + 1) + (col + 2) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum01 = Math.fma(a01, b11, sum01);
          sum02 = Math.fma(a01, b12, sum02);
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          float b01 = b[offsetb + (i + 0) + (col + 1) * ldb];
          float b02 = b[offsetb + (i + 0) + (col + 2) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + (row + 0) + (col + 1) * ldc]);
          c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + (row + 0) + (col + 2) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum10 = Math.fma(a10, b00, sum10);
          sum20 = Math.fma(a20, b00, sum20);
          float a01 = a[offseta + (i + 1) + (row + 0) * lda];
          float a11 = a[offseta + (i + 1) + (row + 1) * lda];
          float a21 = a[offseta + (i + 1) + (row + 2) * lda];
          float b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum10 = Math.fma(a11, b10, sum10);
          sum20 = Math.fma(a21, b10, sum20);
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float a10 = a[offseta + (i + 0) + (row + 1) * lda];
          float a20 = a[offseta + (i + 0) + (row + 2) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum10 = Math.fma(a10, b00, sum10);
          sum20 = Math.fma(a20, b00, sum20);
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + (row + 1) + (col + 0) * ldc]);
          c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + (row + 2) + (col + 0) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          float a01 = a[offseta + (i + 1) + (row + 0) * lda];
          float b10 = b[offsetb + (i + 1) + (col + 0) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float b00 = b[offsetb + (i + 0) + (col + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          sum10 = Math.fma(a10, b00, sum10);
          sum11 = Math.fma(a10, b01, sum11);
          sum12 = Math.fma(a10, b02, sum12);
          sum20 = Math.fma(a20, b00, sum20);
          sum21 = Math.fma(a20, b01, sum21);
          sum22 = Math.fma(a20, b02, sum22);
          float a01 = a[offseta + (i + 1) + (row + 0) * lda];
          float a11 = a[offseta + (i + 1) + (row + 1) * lda];
          float a21 = a[offseta + (i + 1) + (row + 2) * lda];
          float b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          float b11 = b[offsetb + (col + 1) + (i + 1) * ldb];
          float b12 = b[offsetb + (col + 2) + (i + 1) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum01 = Math.fma(a01, b11, sum01);
          sum02 = Math.fma(a01, b12, sum02);
          sum10 = Math.fma(a11, b10, sum10);
          sum11 = Math.fma(a11, b11, sum11);
          sum12 = Math.fma(a11, b12, sum12);
          sum20 = Math.fma(a21, b10, sum20);
          sum21 = Math.fma(a21, b11, sum21);
          sum22 = Math.fma(a21, b12, sum22);
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float a10 = a[offseta + (i + 0) + (row + 1) * lda];
          float a20 = a[offseta + (i + 0) + (row + 2) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          float b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          float b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          sum10 = Math.fma(a10, b00, sum10);
          sum11 = Math.fma(a10, b01, sum11);
          sum12 = Math.fma(a10, b02, sum12);
          sum20 = Math.fma(a20, b00, sum20);
          sum21 = Math.fma(a20, b01, sum21);
          sum22 = Math.fma(a20, b02, sum22);
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + (row + 0) + (col + 1) * ldc]);
          c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + (row + 0) + (col + 2) * ldc]);
          c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + (row + 1) + (col + 0) * ldc]);
          c[offsetc + (row + 1) + (col + 1) * ldc] = Math.fma(alpha, sum11, beta * c[offsetc + (row + 1) + (col + 1) * ldc]);
          c[offsetc + (row + 1) + (col + 2) * ldc] = Math.fma(alpha, sum12, beta * c[offsetc + (row + 1) + (col + 2) * ldc]);
          c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + (row + 2) + (col + 0) * ldc]);
          c[offsetc + (row + 2) + (col + 1) * ldc] = Math.fma(alpha, sum21, beta * c[offsetc + (row + 2) + (col + 1) * ldc]);
          c[offsetc + (row + 2) + (col + 2) * ldc] = Math.fma(alpha, sum22, beta * c[offsetc + (row + 2) + (col + 2) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
          float a01 = a[offseta + (i + 1) + (row + 0) * lda];
          float b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          float b11 = b[offsetb + (col + 1) + (i + 1) * ldb];
          float b12 = b[offsetb + (col + 2) + (i + 1) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum01 = Math.fma(a01, b11, sum01);
          sum02 = Math.fma(a01, b12, sum02);
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          float b01 = b[offsetb + (col + 1) + (i + 0) * ldb];
          float b02 = b[offsetb + (col + 2) + (i + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum01 = Math.fma(a00, b01, sum01);
          sum02 = Math.fma(a00, b02, sum02);
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 0) + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + (row + 0) + (col + 1) * ldc]);
          c[offsetc + (row + 0) + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + (row + 0) + (col + 2) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          sum10 = Math.fma(a10, b00, sum10);
          sum20 = Math.fma(a20, b00, sum20);
          float a01 = a[offseta + (i + 1) + (row + 0) * lda];
          float a11 = a[offseta + (i + 1) + (row + 1) * lda];
          float a21 = a[offseta + (i + 1) + (row + 2) * lda];
          float b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
          sum10 = Math.fma(a11, b10, sum10);
          sum20 = Math.fma(a21, b10, sum20);
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float a10 = a[offseta + (i + 0) + (row + 1) * lda];
          float a20 = a[offseta + (i + 0) + (row + 2) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
          sum10 = Math.fma(a10, b00, sum10);
          sum20 = Math.fma(a20, b00, sum20);
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
          c[offsetc + (row + 1) + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + (row + 1) + (col + 0) * ldc]);
          c[offsetc + (row + 2) + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + (row + 2) + (col + 0) * ldc]);
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
          sum00 = Math.fma(a00, b00, sum00);
          float a01 = a[offseta + (i + 1) + (row + 0) * lda];
          float b10 = b[offsetb + (col + 0) + (i + 1) * ldb];
          sum00 = Math.fma(a01, b10, sum00);
        }
        for (; i < k; i += 1) {
          float a00 = a[offseta + (i + 0) + (row + 0) * lda];
          float b00 = b[offsetb + (col + 0) + (i + 0) * ldb];
          sum00 = Math.fma(a00, b00, sum00);
        }
        if (beta != 0.0f) {
          c[offsetc + (row + 0) + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + (row + 0) + (col + 0) * ldc]);
        } else {
          c[offsetc + (row + 0) + (col + 0) * ldc] = alpha * sum00;
        }
      }
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
        y[offsety + iy] = Math.fma(alphax0, a[offseta + row + (col + 0) * lda],
                          Math.fma(alphax1, a[offseta + row + (col + 1) * lda],
                          Math.fma(alphax2, a[offseta + row + (col + 2) * lda],
                          Math.fma(alphax3, a[offseta + row + (col + 3) * lda], y[offsety + iy]))));
      }
    }
    for (; col < n; col += 1, ix += incx) {
      int row = 0, iy = incy < 0 ? (m - 1) * -incy : 0;
      double alphax = alpha * x[offsetx + ix];
      for (; row < m; row += 1, iy += incy) {
        y[offsety + iy] = Math.fma(alphax, a[offseta + row + col * lda], y[offsety + iy]);
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
        sum0 = Math.fma(xix, a[offseta + row + (col + 0) * lda], sum0);
        sum1 = Math.fma(xix, a[offseta + row + (col + 1) * lda], sum1);
        sum2 = Math.fma(xix, a[offseta + row + (col + 2) * lda], sum2);
        sum3 = Math.fma(xix, a[offseta + row + (col + 3) * lda], sum3);
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
        sum = Math.fma(x[offsetx + ix], a[offseta + row + col * lda], sum);
      }
      if (beta != 0.0) {
        y[offsety + iy] = alpha * sum + beta * y[offsety + iy];
      } else {
        y[offsety + iy] = alpha * sum;
      }
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
        y[offsety + iy] = Math.fma(alphax0, a[offseta + row + (col + 0) * lda],
                          Math.fma(alphax1, a[offseta + row + (col + 1) * lda],
                          Math.fma(alphax2, a[offseta + row + (col + 2) * lda],
                          Math.fma(alphax3, a[offseta + row + (col + 3) * lda],
                          Math.fma(alphax4, a[offseta + row + (col + 4) * lda],
                          Math.fma(alphax5, a[offseta + row + (col + 5) * lda],
                          Math.fma(alphax6, a[offseta + row + (col + 6) * lda],
                          Math.fma(alphax7, a[offseta + row + (col + 7) * lda], y[offsety + iy]))))))));
      }
    }
    for (; col < n; col += 1, ix += incx) {
      float alphax = alpha * x[offsetx + ix];
      for (int row = 0, iy = incy < 0 ? (m - 1) * -incy : 0; row < m; row += 1, iy += incy) {
        y[offsety + iy] = Math.fma(alphax, a[offseta + row + col * lda], y[offsety + iy]);
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
        sum0 = Math.fma(x[offsetx + ix], a[offseta + row + (col + 0) * lda], sum0);
        sum1 = Math.fma(x[offsetx + ix], a[offseta + row + (col + 1) * lda], sum1);
        sum2 = Math.fma(x[offsetx + ix], a[offseta + row + (col + 2) * lda], sum2);
        sum3 = Math.fma(x[offsetx + ix], a[offseta + row + (col + 3) * lda], sum3);
        sum4 = Math.fma(x[offsetx + ix], a[offseta + row + (col + 4) * lda], sum4);
        sum5 = Math.fma(x[offsetx + ix], a[offseta + row + (col + 5) * lda], sum5);
        sum6 = Math.fma(x[offsetx + ix], a[offseta + row + (col + 6) * lda], sum6);
        sum7 = Math.fma(x[offsetx + ix], a[offseta + row + (col + 7) * lda], sum7);
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
        sum = Math.fma(x[offsetx + ix], a[offseta + row + col * lda], sum);
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
        a[offseta + row + (col + 0) * lda] = Math.fma(alphayiy0, xjx, a[offseta + row + (col + 0) * lda]);
        a[offseta + row + (col + 1) * lda] = Math.fma(alphayiy1, xjx, a[offseta + row + (col + 1) * lda]);
        a[offseta + row + (col + 2) * lda] = Math.fma(alphayiy2, xjx, a[offseta + row + (col + 2) * lda]);
        a[offseta + row + (col + 3) * lda] = Math.fma(alphayiy3, xjx, a[offseta + row + (col + 3) * lda]);
      }
    }
    for (; col < n; col += 1, iy += incy) {
      double alphayiy = alpha * y[offsety + iy];
      int row = 0, jx = incx < 0 ? (n - 1) * -incx : 0;
      for (; row < m; row += 1, jx += incx) {
        a[offseta + row + col * lda] = Math.fma(alphayiy, x[offsetx + jx], a[offseta + row + col * lda]);
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
        a[offseta + row + (col + 0) * lda] = Math.fma(alphayiy0, xjx, a[offseta + row + (col + 0) * lda]);
        a[offseta + row + (col + 1) * lda] = Math.fma(alphayiy1, xjx, a[offseta + row + (col + 1) * lda]);
        a[offseta + row + (col + 2) * lda] = Math.fma(alphayiy2, xjx, a[offseta + row + (col + 2) * lda]);
        a[offseta + row + (col + 3) * lda] = Math.fma(alphayiy3, xjx, a[offseta + row + (col + 3) * lda]);
      }
    }
    for (; col < n; col += 1, iy += incy) {
      float alphayiy = alpha * y[offsety + iy];
      int row = 0, jx = incx < 0 ? (n - 1) * -incx : 0;
      for (; row < m; row += 1, jx += incx) {
        a[offseta + row + col * lda] = Math.fma(alphayiy, x[offsetx + jx], a[offseta + row + col * lda]);
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
        sum0 = Math.fma(x0, x0, sum0);
        sum1 = Math.fma(x1, x1, sum1);
        sum2 = Math.fma(x2, x2, sum2);
        sum3 = Math.fma(x3, x3, sum3);
      }
    } else {
      for (; ix < loopBound(n, 4) * incx; ix += 4 * incx) {
        double x0 = x[offsetx + ix + (0 * incx)];
        double x1 = x[offsetx + ix + (1 * incx)];
        double x2 = x[offsetx + ix + (2 * incx)];
        double x3 = x[offsetx + ix + (3 * incx)];
        sum0 = Math.fma(x0, x0, sum0);
        sum1 = Math.fma(x1, x1, sum1);
        sum2 = Math.fma(x2, x2, sum2);
        sum3 = Math.fma(x3, x3, sum3);
      }
    }
    double sum = sum0 + sum1 + sum2 + sum3;
    for (; ix < n * incx; ix += incx) {
      double x0 = x[offsetx + ix + 0];
      sum = Math.fma(x0, x0, sum);
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
        sum0 = Math.fma(x0, x0, sum0);
        sum1 = Math.fma(x1, x1, sum1);
        sum2 = Math.fma(x2, x2, sum2);
        sum3 = Math.fma(x3, x3, sum3);
      }
    } else {
      for (; ix < loopBound(n, 4) * incx; ix += 4 * incx) {
        float x0 = x[offsetx + ix + (0 * incx)];
        float x1 = x[offsetx + ix + (1 * incx)];
        float x2 = x[offsetx + ix + (2 * incx)];
        float x3 = x[offsetx + ix + (3 * incx)];
        sum0 = Math.fma(x0, x0, sum0);
        sum1 = Math.fma(x1, x1, sum1);
        sum2 = Math.fma(x2, x2, sum2);
        sum3 = Math.fma(x3, x3, sum3);
      }
    }
    float sum = sum0 + sum1 + sum2 + sum3;
    for (; ix < n * incx; ix += incx) {
      float x0 = x[offsetx + ix + 0];
      sum = Math.fma(x0, x0, sum);
    }
    return (float)Math.sqrt(sum);
  }

  protected void daxpyiK(int n, double alpha, double[] x, int offsetx, int[] indx, int offsetindx, double[] y, int offsety) {
    for (int i = 0; i < n; i += 1) {
      int indx0 = indx[offsetindx + (i + 0)];
      y[offsety + indx0] = Math.fma(alpha, x[offsetx + (i + 0)], y[offsety + indx0]);
    }
  }

  protected void saxpyiK(int n, float alpha, float[] x, int offsetx, int[] indx, int offsetindx, float[] y, int offsety) {
    for (int i = 0; i < n; i += 1) {
      int indx0 = indx[offsetindx + (i + 0)];
      y[offsety + indx0] = Math.fma(alpha, x[offsetx + (i + 0)], y[offsety + indx0]);
    }
  }

  protected double ddotiK(int n, double[] x, int offsetx, int[] indx, int offsetindx, double[] y, int offsety) {
    double sum = 0.0;
    int i = 0;
    double sum0 = 0.0;
    double sum1 = 0.0;
    double sum2 = 0.0;
    double sum3 = 0.0;
    for (; i < loopBound(n, 4); i += 4) {
      sum0 = Math.fma(x[offsetx + (i + 0)], y[offsety + indx[offsetindx + (i + 0)]], sum0);
      sum1 = Math.fma(x[offsetx + (i + 1)], y[offsety + indx[offsetindx + (i + 1)]], sum1);
      sum2 = Math.fma(x[offsetx + (i + 2)], y[offsety + indx[offsetindx + (i + 2)]], sum2);
      sum3 = Math.fma(x[offsetx + (i + 3)], y[offsety + indx[offsetindx + (i + 3)]], sum3);
    }
    sum += sum0 + sum1 + sum2 + sum3;
    for (; i < n; i += 1) {
      sum = Math.fma(x[offsetx + (i + 0)], y[offsety + indx[offsetindx + (i + 0)]], sum);
    }
    return sum;
  }

  protected float sdotiK(int n, float[] x, int offsetx, int[] indx, int offsetindx, float[] y, int offsety) {
    float sum = 0.0f;
    int i = 0;
    float sum0 = 0.0f;
    float sum1 = 0.0f;
    float sum2 = 0.0f;
    float sum3 = 0.0f;
    for (; i < loopBound(n, 4); i += 4) {
      sum0 = Math.fma(x[offsetx + (i + 0)], y[offsety + indx[offsetindx + (i + 0)]], sum0);
      sum1 = Math.fma(x[offsetx + (i + 1)], y[offsety + indx[offsetindx + (i + 1)]], sum1);
      sum2 = Math.fma(x[offsetx + (i + 2)], y[offsety + indx[offsetindx + (i + 2)]], sum2);
      sum3 = Math.fma(x[offsetx + (i + 3)], y[offsety + indx[offsetindx + (i + 3)]], sum3);
    }
    sum += sum0 + sum1 + sum2 + sum3;
    for (; i < n; i += 1) {
      sum = Math.fma(x[offsetx + (i + 0)], y[offsety + indx[offsetindx + (i + 0)]], sum);
    }
    return sum;
  }
}
