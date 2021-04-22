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

public class Java11BLAS extends JavaBLAS {

  private static final Java11BLAS instance = new Java11BLAS();

  protected Java11BLAS() {}

  public static dev.ludovic.netlib.JavaBLAS getInstance() {
    return instance;
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
          sum11 = Math.fma(a10, b02, sum12);
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
          sum11 = Math.fma(a11, b12, sum12);
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
          sum11 = Math.fma(a10, b02, sum12);
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
          sum11 = Math.fma(a10, b02, sum12);
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
          sum11 = Math.fma(a11, b12, sum12);
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
          sum11 = Math.fma(a10, b02, sum12);
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
          sum11 = Math.fma(a10, b02, sum12);
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
          sum11 = Math.fma(a11, b12, sum12);
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
          sum11 = Math.fma(a10, b02, sum12);
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
          sum11 = Math.fma(a10, b02, sum12);
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
          sum11 = Math.fma(a11, b12, sum12);
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
          sum11 = Math.fma(a10, b02, sum12);
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
}
