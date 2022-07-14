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

import jdk.incubator.vector.DoubleVector;
import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;

class VectorBLAS extends Java11BLAS implements JavaBLAS {

  private static final VectorSpecies<Float>  FMAX = FloatVector.SPECIES_MAX;
  private static final VectorSpecies<Double> DMAX = DoubleVector.SPECIES_MAX;

  private static final VectorBLAS instance = new VectorBLAS();

  protected VectorBLAS() {}

  public static JavaBLAS getInstance() {
    return instance;
  }

  protected double dasumK(int n, double[] x, int offsetx, int incx) {
    if (incx == 1) {
      int i = 0;
      DoubleVector vsum = DoubleVector.zero(DMAX);
      for (; i < DMAX.loopBound(n); i += DMAX.length()) {
        DoubleVector vx = DoubleVector.fromArray(DMAX, x, offsetx + i);
        vsum = vx.abs().add(vsum);
      }
      double sum = vsum.reduceLanes(VectorOperators.ADD);
      for (; i < n; i += 1) {
        sum += Math.abs(x[offsetx + i]);
      }
      return sum;
    } else {
      return super.dasumK(n, x, offsetx, incx);
    }
  }

  protected float sasumK(int n, float[] x, int offsetx, int incx) {
    if (incx == 1) {
      int i = 0;
      FloatVector vsum = FloatVector.zero(FMAX);
      for (; i < FMAX.loopBound(n); i += FMAX.length()) {
        FloatVector vx = FloatVector.fromArray(FMAX, x, offsetx + i);
        vsum = vx.abs().add(vsum);
      }
      float sum = vsum.reduceLanes(VectorOperators.ADD);
      for (; i < n; i += 1) {
        sum += Math.abs(x[offsetx + i]);
      }
      return sum;
    } else {
      return super.sasumK(n, x, offsetx, incx);
    }
  }

  protected void daxpyK(int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
      int i = 0;
      DoubleVector valpha = DoubleVector.broadcast(DMAX, alpha);
      for (; i < DMAX.loopBound(n); i += DMAX.length()) {
        DoubleVector vx = DoubleVector.fromArray(DMAX, x, offsetx + i);
        DoubleVector vy = DoubleVector.fromArray(DMAX, y, offsety + i);
        valpha.fma(vx, vy)
          .intoArray(y, offsety + i);
      }
      for (; i < n; i += 1) {
        y[offsety + i] += alpha * x[offsetx + i];
      }
    } else {
      super.daxpyK(n, alpha, x, offsetx, incx, y, offsety, incy);
    }
  }

  protected void saxpyK(int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
      int i = 0;
      FloatVector valpha = FloatVector.broadcast(FMAX, alpha);
      for (; i < FMAX.loopBound(n); i += FMAX.length()) {
        FloatVector vx = FloatVector.fromArray(FMAX, x, offsetx + i);
        FloatVector vy = FloatVector.fromArray(FMAX, y, offsety + i);
        valpha.fma(vx, vy)
          .intoArray(y, offsety + i);
      }
      for (; i < n; i += 1) {
        y[offsety + i] += alpha * x[offsetx + i];
      }
    } else {
      super.saxpyK(n, alpha, x, offsetx, incx, y, offsety, incy);
    }
  }

  protected double ddotK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
      int i = 0;
      DoubleVector vsum = DoubleVector.zero(DMAX);
      for (; i < DMAX.loopBound(n); i += DMAX.length()) {
        DoubleVector vx = DoubleVector.fromArray(DMAX, x, offsetx + i);
        DoubleVector vy = DoubleVector.fromArray(DMAX, y, offsety + i);
        vsum = vx.fma(vy, vsum);
      }
      double sum = vsum.reduceLanes(VectorOperators.ADD);
      for (; i < n; i += 1) {
        sum += x[offsetx + i] * y[offsety + i];
      }
      return sum;
    } else {
      return super.ddotK(n, x, offsetx, incx, y, offsety, incy);
    }
  }

  protected float sdotK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
      int i = 0;
      FloatVector vsum = FloatVector.zero(FMAX);
      for (; i < FMAX.loopBound(n); i += FMAX.length()) {
        FloatVector vx = FloatVector.fromArray(FMAX, x, offsetx + i);
        FloatVector vy = FloatVector.fromArray(FMAX, y, offsety + i);
        vsum = vx.fma(vy, vsum);
      }
      float sum = vsum.reduceLanes(VectorOperators.ADD);
      for (; i < n; i += 1) {
        sum += x[offsetx + i] * y[offsety + i];
      }
      return sum;
    } else {
      return super.sdotK(n, x, offsetx, incx, y, offsety, incy);
    }
  }

  protected void dgepdotTN(int m, int rows, int rowe, int n, int cols, int cole, int k, int is, int ie, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
    final int Ti = 1;

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
    for (; i < loopAlign(is, ie, Ti * DMAX.length()); i += 1) {
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
    }
    DoubleVector vsum00 = DoubleVector.zero(DMAX);
    DoubleVector vsum01 = DoubleVector.zero(DMAX);
    DoubleVector vsum02 = DoubleVector.zero(DMAX);
    DoubleVector vsum10 = DoubleVector.zero(DMAX);
    DoubleVector vsum11 = DoubleVector.zero(DMAX);
    DoubleVector vsum12 = DoubleVector.zero(DMAX);
    DoubleVector vsum20 = DoubleVector.zero(DMAX);
    DoubleVector vsum21 = DoubleVector.zero(DMAX);
    DoubleVector vsum22 = DoubleVector.zero(DMAX);
    for (; i < loopBound(ie, Ti * DMAX.length()); i += Ti * DMAX.length()) {
      DoubleVector va00 = DoubleVector.fromArray(DMAX, a, offseta + (i + 0 * DMAX.length()) + (row + 0) * lda);
      DoubleVector va01 = DoubleVector.fromArray(DMAX, a, offseta + (i + 0 * DMAX.length()) + (row + 1) * lda);
      DoubleVector va02 = DoubleVector.fromArray(DMAX, a, offseta + (i + 0 * DMAX.length()) + (row + 2) * lda);
      DoubleVector vb00 = DoubleVector.fromArray(DMAX, b, offsetb + (i + 0 * DMAX.length()) + (col + 0) * ldb);
      vsum00 = va00.fma(vb00, vsum00);
      vsum10 = va01.fma(vb00, vsum10);
      vsum20 = va02.fma(vb00, vsum20);
      DoubleVector vb01 = DoubleVector.fromArray(DMAX, b, offsetb + (i + 0 * DMAX.length()) + (col + 1) * ldb);
      vsum01 = va00.fma(vb01, vsum01);
      vsum11 = va01.fma(vb01, vsum11);
      vsum21 = va02.fma(vb01, vsum21);
      DoubleVector vb02 = DoubleVector.fromArray(DMAX, b, offsetb + (i + 0 * DMAX.length()) + (col + 2) * ldb);
      vsum02 = va00.fma(vb02, vsum02);
      vsum12 = va01.fma(vb02, vsum12);
      vsum22 = va02.fma(vb02, vsum22);
    }
    sum00 += vsum00.reduceLanes(VectorOperators.ADD);
    sum01 += vsum01.reduceLanes(VectorOperators.ADD);
    sum02 += vsum02.reduceLanes(VectorOperators.ADD);
    sum10 += vsum10.reduceLanes(VectorOperators.ADD);
    sum11 += vsum11.reduceLanes(VectorOperators.ADD);
    sum12 += vsum12.reduceLanes(VectorOperators.ADD);
    sum20 += vsum20.reduceLanes(VectorOperators.ADD);
    sum21 += vsum21.reduceLanes(VectorOperators.ADD);
    sum22 += vsum22.reduceLanes(VectorOperators.ADD);
    for (; i < ie; i += 1) {
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

  protected void sgepdotTN(int m, int rows, int rowe, int n, int cols, int cole, int k, int is, int ie, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
    final int Ti = 1;

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
    for (; i < loopAlign(is, ie, Ti * FMAX.length()); i += 1) {
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
    }
    FloatVector vsum00 = FloatVector.zero(FMAX);
    FloatVector vsum01 = FloatVector.zero(FMAX);
    FloatVector vsum02 = FloatVector.zero(FMAX);
    FloatVector vsum10 = FloatVector.zero(FMAX);
    FloatVector vsum11 = FloatVector.zero(FMAX);
    FloatVector vsum12 = FloatVector.zero(FMAX);
    FloatVector vsum20 = FloatVector.zero(FMAX);
    FloatVector vsum21 = FloatVector.zero(FMAX);
    FloatVector vsum22 = FloatVector.zero(FMAX);
    for (; i < loopBound(ie, Ti * FMAX.length()); i += Ti * FMAX.length()) {
      FloatVector va00 = FloatVector.fromArray(FMAX, a, offseta + (i + 0 * FMAX.length()) + (row + 0) * lda);
      FloatVector va01 = FloatVector.fromArray(FMAX, a, offseta + (i + 0 * FMAX.length()) + (row + 1) * lda);
      FloatVector va02 = FloatVector.fromArray(FMAX, a, offseta + (i + 0 * FMAX.length()) + (row + 2) * lda);
      FloatVector vb00 = FloatVector.fromArray(FMAX, b, offsetb + (i + 0 * FMAX.length()) + (col + 0) * ldb);
      vsum00 = va00.fma(vb00, vsum00);
      vsum10 = va01.fma(vb00, vsum10);
      vsum20 = va02.fma(vb00, vsum20);
      FloatVector vb01 = FloatVector.fromArray(FMAX, b, offsetb + (i + 0 * FMAX.length()) + (col + 1) * ldb);
      vsum01 = va00.fma(vb01, vsum01);
      vsum11 = va01.fma(vb01, vsum11);
      vsum21 = va02.fma(vb01, vsum21);
      FloatVector vb02 = FloatVector.fromArray(FMAX, b, offsetb + (i + 0 * FMAX.length()) + (col + 2) * ldb);
      vsum02 = va00.fma(vb02, vsum02);
      vsum12 = va01.fma(vb02, vsum12);
      vsum22 = va02.fma(vb02, vsum22);
    }
    sum00 += vsum00.reduceLanes(VectorOperators.ADD);
    sum01 += vsum01.reduceLanes(VectorOperators.ADD);
    sum02 += vsum02.reduceLanes(VectorOperators.ADD);
    sum10 += vsum10.reduceLanes(VectorOperators.ADD);
    sum11 += vsum11.reduceLanes(VectorOperators.ADD);
    sum12 += vsum12.reduceLanes(VectorOperators.ADD);
    sum20 += vsum20.reduceLanes(VectorOperators.ADD);
    sum21 += vsum21.reduceLanes(VectorOperators.ADD);
    sum22 += vsum22.reduceLanes(VectorOperators.ADD);
    for (; i < ie; i += 1) {
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

  protected void dgemvN(int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
      if (beta != 1.0) {
        int row = 0;
        DoubleVector vzero = DoubleVector.zero(DMAX);
        DoubleVector vbeta = DoubleVector.broadcast(DMAX, beta);
        for (; row < DMAX.loopBound(m); row += DMAX.length()) {
          if (beta != 0.0) {
            DoubleVector vy = DoubleVector.fromArray(DMAX, y, offsety + row);
            vbeta.mul(vy)
              .intoArray(y, offsety + row);
          } else {
            vzero.intoArray(y, offsety + row);
          }
        }
        for (; row < m; row += 1) {
          if (beta != 0.0) {
            y[offsety + row] = beta * y[offsety + row];
          } else {
            y[offsety + row] = 0.0;
          }
        }
      }
      int col = 0;
      for (; col < loopBound(n, 4); col += 4) {
        int row = 0;
        DoubleVector valphax0 = DoubleVector.broadcast(DMAX, alpha * x[offsetx + (col + 0)]);
        DoubleVector valphax1 = DoubleVector.broadcast(DMAX, alpha * x[offsetx + (col + 1)]);
        DoubleVector valphax2 = DoubleVector.broadcast(DMAX, alpha * x[offsetx + (col + 2)]);
        DoubleVector valphax3 = DoubleVector.broadcast(DMAX, alpha * x[offsetx + (col + 3)]);
        for (; row < DMAX.loopBound(m); row += DMAX.length()) {
          DoubleVector va0 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 0) * lda);
          DoubleVector va1 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 1) * lda);
          DoubleVector va2 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 2) * lda);
          DoubleVector va3 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 3) * lda);
          DoubleVector vy = DoubleVector.fromArray(DMAX, y, offsety + row);
          valphax0.fma(va0, valphax1.fma(va1, valphax2.fma(va2, valphax3.fma(va3, vy))))
            .intoArray(y, offsety + row);
        }
        double alphax0 = alpha * x[offsetx + (col + 0)];
        double alphax1 = alpha * x[offsetx + (col + 1)];
        double alphax2 = alpha * x[offsetx + (col + 2)];
        double alphax3 = alpha * x[offsetx + (col + 3)];
        for (; row < m; row += 1) {
          y[offsety + row] += alphax0 * a[offseta + row + (col + 0) * lda]
                           +  alphax1 * a[offseta + row + (col + 1) * lda]
                           +  alphax2 * a[offseta + row + (col + 2) * lda]
                           +  alphax3 * a[offseta + row + (col + 3) * lda];
        }
      }
      for (; col < n; col += 1) {
        int row = 0;
        DoubleVector valphax0 = DoubleVector.broadcast(DMAX, alpha * x[offsetx + (col + 0)]);
        for (; row < DMAX.loopBound(m); row += DMAX.length()) {
          DoubleVector va0 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 0) * lda);
          DoubleVector vy = DoubleVector.fromArray(DMAX, y, offsety + row);
          valphax0.fma(va0, vy)
            .intoArray(y, offsety + row);
        }
        double alphax0 = alpha * x[offsetx + (col + 0)];
        for (; row < m; row += 1) {
          y[offsety + row] += alphax0 * a[offseta + row + (col + 0) * lda];
        }
      }
    } else {
      super.dgemvN(m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
    }
  }

  protected void dgemvT(int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
      int col = 0;
      for (; col < loopBound(n, 4); col += 4) {
        int row = 0;
        DoubleVector vsum0 = DoubleVector.zero(DMAX);
        DoubleVector vsum1 = DoubleVector.zero(DMAX);
        DoubleVector vsum2 = DoubleVector.zero(DMAX);
        DoubleVector vsum3 = DoubleVector.zero(DMAX);
        for (; row < DMAX.loopBound(m); row += DMAX.length()) {
          DoubleVector va0 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 0) * lda);
          DoubleVector va1 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 1) * lda);
          DoubleVector va2 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 2) * lda);
          DoubleVector va3 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 3) * lda);
          DoubleVector vx0 = DoubleVector.fromArray(DMAX, x, offsetx + row);
          vsum0 = vx0.fma(va0, vsum0);
          vsum1 = vx0.fma(va1, vsum1);
          vsum2 = vx0.fma(va2, vsum2);
          vsum3 = vx0.fma(va3, vsum3);
        }
        double sum0 = vsum0.reduceLanes(VectorOperators.ADD);
        double sum1 = vsum1.reduceLanes(VectorOperators.ADD);
        double sum2 = vsum2.reduceLanes(VectorOperators.ADD);
        double sum3 = vsum3.reduceLanes(VectorOperators.ADD);
        for (; row < m; row += 1) {
          double a0 = a[offseta + row + (col + 0) * lda];
          double a1 = a[offseta + row + (col + 1) * lda];
          double a2 = a[offseta + row + (col + 2) * lda];
          double a3 = a[offseta + row + (col + 3) * lda];
          double x0 = x[offsetx + row];
          sum0 += x0 * a0;
          sum1 += x0 * a1;
          sum2 += x0 * a2;
          sum3 += x0 * a3;
        }
        if (beta != 0.0) {
          y[offsety + (col + 0)] = alpha * sum0 + beta * y[offsety + (col + 0)];
          y[offsety + (col + 1)] = alpha * sum1 + beta * y[offsety + (col + 1)];
          y[offsety + (col + 2)] = alpha * sum2 + beta * y[offsety + (col + 2)];
          y[offsety + (col + 3)] = alpha * sum3 + beta * y[offsety + (col + 3)];
        } else {
          y[offsety + (col + 0)] = alpha * sum0;
          y[offsety + (col + 1)] = alpha * sum1;
          y[offsety + (col + 2)] = alpha * sum2;
          y[offsety + (col + 3)] = alpha * sum3;
        }
      }
      for (; col < n; col += 1) {
        int row = 0;
        DoubleVector vsum0 = DoubleVector.zero(DMAX);
        for (; row < DMAX.loopBound(m); row += DMAX.length()) {
          DoubleVector va0 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 0) * lda);
          DoubleVector vx0 = DoubleVector.fromArray(DMAX, x, offsetx + row);
          vsum0 = vx0.fma(va0, vsum0);
        }
        double sum0 = vsum0.reduceLanes(VectorOperators.ADD);
        for (; row < m; row += 1) {
          double a0 = a[offseta + row + (col + 0) * lda];
          double x0 = x[offsetx + row];
          sum0 += x0 * a0;
        }
        if (beta != 0.0) {
          y[offsety + (col + 0)] = alpha * sum0 + beta * y[offsety + (col + 0)];
        } else {
          y[offsety + (col + 0)] = alpha * sum0;
        }
      }
    } else {
      super.dgemvT(m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
    }
  }

  protected void sgemvN(int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
      if (beta != 1.0f) {
        int row = 0;
        FloatVector vzero = FloatVector.zero(FMAX);
        FloatVector vbeta = FloatVector.broadcast(FMAX, beta);
        for (; row < FMAX.loopBound(m); row += FMAX.length()) {
          if (beta != 0.0f) {
            FloatVector vy = FloatVector.fromArray(FMAX, y, offsety + row);
            vbeta.mul(vy)
              .intoArray(y, offsety + row);
          } else {
            vzero.intoArray(y, offsety + row);
          }
        }
        for (; row < m; row += 1) {
          if (beta != 0.0f) {
            y[offsety + row] = beta * y[offsety + row];
          } else {
            y[offsety + row] = 0.0f;
          }
        }
      }
      int col = 0;
      for (; col < loopBound(n, 4); col += 4) {
        int row = 0;
        FloatVector valphax0 = FloatVector.broadcast(FMAX, alpha * x[offsetx + (col + 0)]);
        FloatVector valphax1 = FloatVector.broadcast(FMAX, alpha * x[offsetx + (col + 1)]);
        FloatVector valphax2 = FloatVector.broadcast(FMAX, alpha * x[offsetx + (col + 2)]);
        FloatVector valphax3 = FloatVector.broadcast(FMAX, alpha * x[offsetx + (col + 3)]);
        for (; row < FMAX.loopBound(m); row += FMAX.length()) {
          FloatVector va0 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 0) * lda);
          FloatVector va1 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 1) * lda);
          FloatVector va2 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 2) * lda);
          FloatVector va3 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 3) * lda);
          FloatVector vy = FloatVector.fromArray(FMAX, y, offsety + row);
          valphax0.fma(va0, valphax1.fma(va1, valphax2.fma(va2, valphax3.fma(va3, vy))))
            .intoArray(y, offsety + row);
        }
        float alphax0 = alpha * x[offsetx + (col + 0)];
        float alphax1 = alpha * x[offsetx + (col + 1)];
        float alphax2 = alpha * x[offsetx + (col + 2)];
        float alphax3 = alpha * x[offsetx + (col + 3)];
        for (; row < m; row += 1) {
          y[offsety + row] += alphax0 * a[offseta + row + (col + 0) * lda]
                           +  alphax1 * a[offseta + row + (col + 1) * lda]
                           +  alphax2 * a[offseta + row + (col + 2) * lda]
                           +  alphax3 * a[offseta + row + (col + 3) * lda];
        }
      }
      for (; col < n; col += 1) {
        int row = 0;
        FloatVector valphax0 = FloatVector.broadcast(FMAX, alpha * x[offsetx + (col + 0)]);
        for (; row < FMAX.loopBound(m); row += FMAX.length()) {
          FloatVector va0 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 0) * lda);
          FloatVector vy = FloatVector.fromArray(FMAX, y, offsety + row);
          valphax0.fma(va0, vy)
            .intoArray(y, offsety + row);
        }
        float alphax0 = alpha * x[offsetx + (col + 0)];
        for (; row < m; row += 1) {
          y[offsety + row] += alphax0 * a[offseta + row + (col + 0) * lda];
        }
      }
    } else {
      super.sgemvN(m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
    }
  }

  protected void sgemvT(int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
      int col = 0;
      for (; col < loopBound(n, 4); col += 4) {
        int row = 0;
        FloatVector vsum0 = FloatVector.zero(FMAX);
        FloatVector vsum1 = FloatVector.zero(FMAX);
        FloatVector vsum2 = FloatVector.zero(FMAX);
        FloatVector vsum3 = FloatVector.zero(FMAX);
        for (; row < FMAX.loopBound(m); row += FMAX.length()) {
          FloatVector va0 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 0) * lda);
          FloatVector va1 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 1) * lda);
          FloatVector va2 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 2) * lda);
          FloatVector va3 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 3) * lda);
          FloatVector vx0 = FloatVector.fromArray(FMAX, x, offsetx + row);
          vsum0 = vx0.fma(va0, vsum0);
          vsum1 = vx0.fma(va1, vsum1);
          vsum2 = vx0.fma(va2, vsum2);
          vsum3 = vx0.fma(va3, vsum3);
        }
        float sum0 = vsum0.reduceLanes(VectorOperators.ADD);
        float sum1 = vsum1.reduceLanes(VectorOperators.ADD);
        float sum2 = vsum2.reduceLanes(VectorOperators.ADD);
        float sum3 = vsum3.reduceLanes(VectorOperators.ADD);
        for (; row < m; row += 1) {
          float a0 = a[offseta + row + (col + 0) * lda];
          float a1 = a[offseta + row + (col + 1) * lda];
          float a2 = a[offseta + row + (col + 2) * lda];
          float a3 = a[offseta + row + (col + 3) * lda];
          float x0 = x[offsetx + row];
          sum0 += x0 * a0;
          sum1 += x0 * a1;
          sum2 += x0 * a2;
          sum3 += x0 * a3;
        }
        if (beta != 0.0f) {
          y[offsety + (col + 0)] = alpha * sum0 + beta * y[offsety + (col + 0)];
          y[offsety + (col + 1)] = alpha * sum1 + beta * y[offsety + (col + 1)];
          y[offsety + (col + 2)] = alpha * sum2 + beta * y[offsety + (col + 2)];
          y[offsety + (col + 3)] = alpha * sum3 + beta * y[offsety + (col + 3)];
        } else {
          y[offsety + (col + 0)] = alpha * sum0;
          y[offsety + (col + 1)] = alpha * sum1;
          y[offsety + (col + 2)] = alpha * sum2;
          y[offsety + (col + 3)] = alpha * sum3;
        }
      }
      for (; col < n; col += 1) {
        int row = 0;
        FloatVector vsum0 = FloatVector.zero(FMAX);
        for (; row < FMAX.loopBound(m); row += FMAX.length()) {
          FloatVector va0 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 0) * lda);
          FloatVector vx0 = FloatVector.fromArray(FMAX, x, offsetx + row);
          vsum0 = vx0.fma(va0, vsum0);
        }
        float sum0 = vsum0.reduceLanes(VectorOperators.ADD);
        for (; row < m; row += 1) {
          float a0 = a[offseta + row + (col + 0) * lda];
          float x0 = x[offsetx + row];
          sum0 += x0 * a0;
        }
        if (beta != 0.0f) {
          y[offsety + (col + 0)] = alpha * sum0 + beta * y[offsety + (col + 0)];
        } else {
          y[offsety + (col + 0)] = alpha * sum0;
        }
      }
    } else {
      super.sgemvT(m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
    }
  }

  protected void dgerK(int m, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
    if (incx == 1 && incy == 1) {
      int col = 0;
      for (; col < loopBound(n, 4); col += 4) {
        int row = 0;
        DoubleVector valphay0 = DoubleVector.broadcast(DMAX, alpha * y[offsety + (col + 0)]);
        DoubleVector valphay1 = DoubleVector.broadcast(DMAX, alpha * y[offsety + (col + 1)]);
        DoubleVector valphay2 = DoubleVector.broadcast(DMAX, alpha * y[offsety + (col + 2)]);
        DoubleVector valphay3 = DoubleVector.broadcast(DMAX, alpha * y[offsety + (col + 3)]);
        for (; row < DMAX.loopBound(m); row += DMAX.length()) {
          DoubleVector vx0 = DoubleVector.fromArray(DMAX, x, offsetx + row);
          DoubleVector va0 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 0) * lda);
          DoubleVector va1 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 1) * lda);
          DoubleVector va2 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 2) * lda);
          DoubleVector va3 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 3) * lda);
          valphay0.fma(vx0, va0)
            .intoArray(a, offseta + row + (col + 0) * lda);
          valphay1.fma(vx0, va1)
            .intoArray(a, offseta + row + (col + 1) * lda);
          valphay2.fma(vx0, va2)
            .intoArray(a, offseta + row + (col + 2) * lda);
          valphay3.fma(vx0, va3)
            .intoArray(a, offseta + row + (col + 3) * lda);
        }
        double alphay0 = alpha * y[offsety + (col + 0)];
        double alphay1 = alpha * y[offsety + (col + 1)];
        double alphay2 = alpha * y[offsety + (col + 2)];
        double alphay3 = alpha * y[offsety + (col + 3)];
        for (; row < m; row += 1) {
          double x0 = x[offsetx + row];
          a[offseta + row + (col + 0) * lda] += alphay0 * x0;
          a[offseta + row + (col + 1) * lda] += alphay1 * x0;
          a[offseta + row + (col + 2) * lda] += alphay2 * x0;
          a[offseta + row + (col + 3) * lda] += alphay3 * x0;
        }
      }
      for (; col < n; col += 1) {
        int row = 0;
        DoubleVector valphay0 = DoubleVector.broadcast(DMAX, alpha * y[offsety + (col + 0)]);
        for (; row < DMAX.loopBound(m); row += DMAX.length()) {
          DoubleVector vx0 = DoubleVector.fromArray(DMAX, x, offsetx + row);
          DoubleVector va0 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 0) * lda);
          valphay0.fma(vx0, va0)
            .intoArray(a, offseta + row + (col + 0) * lda);
        }
        double alphay0 = alpha * y[offsety + col];
        for (; row < m; row += 1) {
          double x0 = x[offsetx + row];
          a[offseta + row + col * lda] += alphay0 * x0;
        }
      }
    } else {
      super.dgerK(m, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
    }
  }

  protected void sgerK(int m, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
    if (incx == 1 && incy == 1) {
      int col = 0;
      for (; col < loopBound(n, 4); col += 4) {
        int row = 0;
        FloatVector valphay0 = FloatVector.broadcast(FMAX, alpha * y[offsety + (col + 0)]);
        FloatVector valphay1 = FloatVector.broadcast(FMAX, alpha * y[offsety + (col + 1)]);
        FloatVector valphay2 = FloatVector.broadcast(FMAX, alpha * y[offsety + (col + 2)]);
        FloatVector valphay3 = FloatVector.broadcast(FMAX, alpha * y[offsety + (col + 3)]);
        for (; row < FMAX.loopBound(m); row += FMAX.length()) {
          FloatVector vx0 = FloatVector.fromArray(FMAX, x, offsetx + row);
          FloatVector va0 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 0) * lda);
          FloatVector va1 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 1) * lda);
          FloatVector va2 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 2) * lda);
          FloatVector va3 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 3) * lda);
          valphay0.fma(vx0, va0)
            .intoArray(a, offseta + row + (col + 0) * lda);
          valphay1.fma(vx0, va1)
            .intoArray(a, offseta + row + (col + 1) * lda);
          valphay2.fma(vx0, va2)
            .intoArray(a, offseta + row + (col + 2) * lda);
          valphay3.fma(vx0, va3)
            .intoArray(a, offseta + row + (col + 3) * lda);
        }
        float alphay0 = alpha * y[offsety + (col + 0)];
        float alphay1 = alpha * y[offsety + (col + 1)];
        float alphay2 = alpha * y[offsety + (col + 2)];
        float alphay3 = alpha * y[offsety + (col + 3)];
        for (; row < m; row += 1) {
          float x0 = x[offsetx + row];
          a[offseta + row + (col + 0) * lda] += alphay0 * x0;
          a[offseta + row + (col + 1) * lda] += alphay1 * x0;
          a[offseta + row + (col + 2) * lda] += alphay2 * x0;
          a[offseta + row + (col + 3) * lda] += alphay3 * x0;
        }
      }
      for (; col < n; col += 1) {
        int row = 0;
        FloatVector valphay0 = FloatVector.broadcast(FMAX, alpha * y[offsety + (col + 0)]);
        for (; row < FMAX.loopBound(m); row += FMAX.length()) {
          FloatVector vx0 = FloatVector.fromArray(FMAX, x, offsetx + row);
          FloatVector va0 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 0) * lda);
          valphay0.fma(vx0, va0)
            .intoArray(a, offseta + row + (col + 0) * lda);
        }
        float alphay0 = alpha * y[offsety + col];
        for (; row < m; row += 1) {
          float x0 = x[offsetx + row];
          a[offseta + row + col * lda] += alphay0 * x0;
        }
      }
    } else {
      super.sgerK(m, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
    }
  }

  protected double dnrm2K(int n, double[] x, int offsetx, int incx) {
    if (incx == 1) {
      int i = 0;
      DoubleVector vsum = DoubleVector.zero(DMAX);
      for (; i < DMAX.loopBound(n); i += DMAX.length()) {
        DoubleVector vx0 = DoubleVector.fromArray(DMAX, x, offsetx + i);
        vsum = vx0.fma(vx0, vsum);
      }
      double sum = vsum.reduceLanes(VectorOperators.ADD);
      for (; i < n; i += 1) {
        double x0 = x[offsetx + i];
        sum += x0 * x0;
      }
      return Math.sqrt(sum);
    } else {
      return super.dnrm2K(n, x, offsetx, incx);
    }
  }

  protected float snrm2K(int n, float[] x, int offsetx, int incx) {
    if (incx == 1) {
      int i = 0;
      FloatVector vsum = FloatVector.zero(FMAX);
      for (; i < FMAX.loopBound(n); i += FMAX.length()) {
        FloatVector vx0 = FloatVector.fromArray(FMAX, x, offsetx + i);
        vsum = vx0.fma(vx0, vsum);
      }
      float sum = vsum.reduceLanes(VectorOperators.ADD);
      for (; i < n; i += 1) {
        float x0 = x[offsetx + i];
        sum += x0 * x0;
      }
      return (float)Math.sqrt(sum);
    } else {
      return super.snrm2K(n, x, offsetx, incx);
    }
  }

  protected void drotK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double c, double s) {
    if (incx == 1 && incy == 1) {
      int i = 0;
      DoubleVector vc = DoubleVector.broadcast(DMAX, c);
      DoubleVector vs = DoubleVector.broadcast(DMAX, s);
      for (; i < DMAX.loopBound(n); i += DMAX.length()) {
        DoubleVector vx0 = DoubleVector.fromArray(DMAX, x, offsetx + i);
        DoubleVector vy0 = DoubleVector.fromArray(DMAX, y, offsety + i);
        vc.fma(vx0, vs.mul(vy0))
          .intoArray(x, offsetx + i);
        vc.mul(vy0).sub(vs.mul(vx0))
          .intoArray(y, offsety + i);
      }
      for (; i < n; i += 1) {
        double x0 = x[offsetx + i];
        double y0 = y[offsety + i]; 
        x[offsetx + i] = c * x0 + s * y0;
        y[offsety + i] = c * y0 - s * x0;
      }
    } else {
      super.drotK(n, x, offsetx, incx, y, offsety, incy, c, s);
    }
  }

  protected void srotK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float c, float s) {
    if (incx == 1 && incy == 1) {
      int i = 0;
      FloatVector vc = FloatVector.broadcast(FMAX, c);
      FloatVector vs = FloatVector.broadcast(FMAX, s);
      for (; i < FMAX.loopBound(n); i += FMAX.length()) {
        FloatVector vx0 = FloatVector.fromArray(FMAX, x, offsetx + i);
        FloatVector vy0 = FloatVector.fromArray(FMAX, y, offsety + i);
        vc.fma(vx0, vs.mul(vy0))
          .intoArray(x, offsetx + i);
        vc.mul(vy0).sub(vs.mul(vx0))
          .intoArray(y, offsety + i);
      }
      for (; i < n; i += 1) {
        float x0 = x[offsetx + i];
        float y0 = y[offsety + i];
        x[offsetx + i] = c * x0 + s * y0;
        y[offsety + i] = c * y0 - s * x0;
      }
    } else {
      super.srotK(n, x, offsetx, incx, y, offsety, incy, c, s);
    }
  }

  protected void dscalK(int n, double alpha, double[] x, int offsetx, int incx) {
    if (incx == 1) {
      int i = 0;
      DoubleVector valpha = DoubleVector.broadcast(DMAX, alpha);
      for (; i < DMAX.loopBound(n); i += DMAX.length()) {
        DoubleVector vx0 = DoubleVector.fromArray(DMAX, x, offsetx + i);
        valpha.mul(vx0)
          .intoArray(x, offsetx + i);
      }
      for (; i < n; i += 1) {
        x[offsetx + i] *= alpha;
      }
    } else {
      super.dscalK(n, alpha, x, offsetx, incx);
    }
  }

  protected void sscalK(int n, float alpha, float[] x, int offsetx, int incx) {
    if (incx == 1) {
      int i = 0;
      FloatVector valpha = FloatVector.broadcast(FMAX, alpha);
      for (; i < FMAX.loopBound(n); i += FMAX.length()) {
        FloatVector vx0 = FloatVector.fromArray(FMAX, x, offsetx + i);
        valpha.mul(vx0)
          .intoArray(x, offsetx + i);
      }
      for (; i < n; i += 1) {
        x[offsetx + i] *= alpha;
      }
    } else {
      super.sscalK(n, alpha, x, offsetx, incx);
    }
  }

  protected void dspmvU(int n, double alpha, double[] a, int offseta, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
      int col = 0;
      for (; col < loopBound(n, 4); col += 4) {
        int row = 0;
        DoubleVector valphax0 = DoubleVector.broadcast(DMAX, alpha * x[offsetx + (col + 0)]);
        DoubleVector valphax1 = DoubleVector.broadcast(DMAX, alpha * x[offsetx + (col + 1)]);
        DoubleVector valphax2 = DoubleVector.broadcast(DMAX, alpha * x[offsetx + (col + 2)]);
        DoubleVector valphax3 = DoubleVector.broadcast(DMAX, alpha * x[offsetx + (col + 3)]);
        DoubleVector vsum0 = DoubleVector.zero(DMAX);
        DoubleVector vsum1 = DoubleVector.zero(DMAX);
        DoubleVector vsum2 = DoubleVector.zero(DMAX);
        DoubleVector vsum3 = DoubleVector.zero(DMAX);
        for (; row < DMAX.loopBound(col); row += DMAX.length()) {
          DoubleVector va0 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 0) * ((col + 0) + 1) / 2);
          DoubleVector va1 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 1) * ((col + 1) + 1) / 2);
          DoubleVector va2 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 2) * ((col + 2) + 1) / 2);
          DoubleVector va3 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 3) * ((col + 3) + 1) / 2);
          DoubleVector vy0 = DoubleVector.fromArray(DMAX, y, offsety + row);
          valphax0.fma(va0, valphax1.fma(va1, valphax2.fma(va2, valphax3.fma(va3, vy0))))
            .intoArray(y, offsety + row);
          DoubleVector vx0 = DoubleVector.fromArray(DMAX, x, offsetx + row);
          vsum0 = vx0.fma(va0, vsum0);
          vsum1 = vx0.fma(va1, vsum1);
          vsum2 = vx0.fma(va2, vsum2);
          vsum3 = vx0.fma(va3, vsum3);
        }
        double alphax0 = alpha * x[offsetx + (col + 0)];
        double alphax1 = alpha * x[offsetx + (col + 1)];
        double alphax2 = alpha * x[offsetx + (col + 2)];
        double alphax3 = alpha * x[offsetx + (col + 3)];
        double sum0 = vsum0.reduceLanes(VectorOperators.ADD);
        double sum1 = vsum1.reduceLanes(VectorOperators.ADD);
        double sum2 = vsum2.reduceLanes(VectorOperators.ADD);
        double sum3 = vsum3.reduceLanes(VectorOperators.ADD);
        for (; row < col; row += 1) {
          double a0 = a[offseta + row + (col + 0) * ((col + 0) + 1) / 2];
          double a1 = a[offseta + row + (col + 1) * ((col + 1) + 1) / 2];
          double a2 = a[offseta + row + (col + 2) * ((col + 2) + 1) / 2];
          double a3 = a[offseta + row + (col + 3) * ((col + 3) + 1) / 2];
          y[offsety + row] += alphax0 * a0 + alphax1 * a1 + alphax2 * a2 + alphax3 * a3;
          double x0 = x[offsetx + row];
          sum0 += x0 * a0;
          sum1 += x0 * a1;
          sum2 += x0 * a2;
          sum3 += x0 * a3;
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
        double x0 = x[offsetx + (row + 0)];
        double x1 = x[offsetx + (row + 1)];
        double x2 = x[offsetx + (row + 2)];
        double x3 = x[offsetx + (row + 3)];
        sum0 += x0 * a00 + x1 * a01 + x2 * a02 + x3 * a03;
        sum1 += x0 * a01 + x1 * a11 + x2 * a12 + x3 * a13;
        sum2 += x0 * a02 + x1 * a12 + x2 * a22 + x3 * a23;
        sum3 += x0 * a03 + x1 * a13 + x2 * a23 + x3 * a33;
        if (beta != 0.0) {
          y[offsety + (col + 0)] = alpha * sum0 + beta * y[offsety + (col + 0)];
          y[offsety + (col + 1)] = alpha * sum1 + beta * y[offsety + (col + 1)];
          y[offsety + (col + 2)] = alpha * sum2 + beta * y[offsety + (col + 2)];
          y[offsety + (col + 3)] = alpha * sum3 + beta * y[offsety + (col + 3)];
        } else {
          y[offsety + (col + 0)] = alpha * sum0;
          y[offsety + (col + 1)] = alpha * sum1;
          y[offsety + (col + 2)] = alpha * sum2;
          y[offsety + (col + 3)] = alpha * sum3;
        }
      }
      for (; col < n; col += 1) {
        int row = 0;
        double alphax0 = alpha * x[offsetx + (col + 0)];
        double sum0 = 0.0;
        for (; row < col; row += 1) {
          double a0 = a[offseta + row + (col + 0) * ((col + 0) + 1) / 2];
          y[offsety + row] += alphax0 * a0;
          sum0 += x[offsetx + row] * a0;
        }
        sum0 += x[offsetx + row] * a[offseta + row + (col + 0) * ((col + 0) + 1) / 2];
        if (beta != 0.0) {
          y[offsety + (col + 0)] = alpha * sum0 + beta * y[offsety + (col + 0)];
        } else {
          y[offsety + (col + 0)] = alpha * sum0;
        }
      }
    } else {
      super.dspmvU(n, alpha, a, offseta, x, offsetx, incx, beta, y, offsety, incy);
    }
  }


  protected void sspmvU(int n, float alpha, float[] a, int offseta, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
      int col = 0;
      for (; col < loopBound(n, 4); col += 4) {
        int row = 0;
        FloatVector valphax0 = FloatVector.broadcast(FMAX, alpha * x[offsetx + (col + 0)]);
        FloatVector valphax1 = FloatVector.broadcast(FMAX, alpha * x[offsetx + (col + 1)]);
        FloatVector valphax2 = FloatVector.broadcast(FMAX, alpha * x[offsetx + (col + 2)]);
        FloatVector valphax3 = FloatVector.broadcast(FMAX, alpha * x[offsetx + (col + 3)]);
        FloatVector vsum0 = FloatVector.zero(FMAX);
        FloatVector vsum1 = FloatVector.zero(FMAX);
        FloatVector vsum2 = FloatVector.zero(FMAX);
        FloatVector vsum3 = FloatVector.zero(FMAX);
        for (; row < FMAX.loopBound(col); row += FMAX.length()) {
          FloatVector va0 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 0) * ((col + 0) + 1) / 2);
          FloatVector va1 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 1) * ((col + 1) + 1) / 2);
          FloatVector va2 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 2) * ((col + 2) + 1) / 2);
          FloatVector va3 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 3) * ((col + 3) + 1) / 2);
          FloatVector vy0 = FloatVector.fromArray(FMAX, y, offsety + row);
          valphax0.fma(va0, valphax1.fma(va1, valphax2.fma(va2, valphax3.fma(va3, vy0))))
            .intoArray(y, offsety + row);
          FloatVector vx0 = FloatVector.fromArray(FMAX, x, offsetx + row);
          vsum0 = vx0.fma(va0, vsum0);
          vsum1 = vx0.fma(va1, vsum1);
          vsum2 = vx0.fma(va2, vsum2);
          vsum3 = vx0.fma(va3, vsum3);
        }
        float alphax0 = alpha * x[offsetx + (col + 0)];
        float alphax1 = alpha * x[offsetx + (col + 1)];
        float alphax2 = alpha * x[offsetx + (col + 2)];
        float alphax3 = alpha * x[offsetx + (col + 3)];
        float sum0 = vsum0.reduceLanes(VectorOperators.ADD);
        float sum1 = vsum1.reduceLanes(VectorOperators.ADD);
        float sum2 = vsum2.reduceLanes(VectorOperators.ADD);
        float sum3 = vsum3.reduceLanes(VectorOperators.ADD);
        for (; row < col; row += 1) {
          float a0 = a[offseta + row + (col + 0) * ((col + 0) + 1) / 2];
          float a1 = a[offseta + row + (col + 1) * ((col + 1) + 1) / 2];
          float a2 = a[offseta + row + (col + 2) * ((col + 2) + 1) / 2];
          float a3 = a[offseta + row + (col + 3) * ((col + 3) + 1) / 2];
          y[offsety + row] += alphax0 * a0 + alphax1 * a1 + alphax2 * a2 + alphax3 * a3;
          float x0 = x[offsetx + row];
          sum0 += x0 * a0;
          sum1 += x0 * a1;
          sum2 += x0 * a2;
          sum3 += x0 * a3;
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
        float x0 = x[offsetx + (row + 0)];
        float x1 = x[offsetx + (row + 1)];
        float x2 = x[offsetx + (row + 2)];
        float x3 = x[offsetx + (row + 3)];
        sum0 += x0 * a00 + x1 * a01 + x2 * a02 + x3 * a03;
        sum1 += x0 * a01 + x1 * a11 + x2 * a12 + x3 * a13;
        sum2 += x0 * a02 + x1 * a12 + x2 * a22 + x3 * a23;
        sum3 += x0 * a03 + x1 * a13 + x2 * a23 + x3 * a33;
        if (beta != 0.0f) {
          y[offsety + (col + 0)] = alpha * sum0 + beta * y[offsety + (col + 0)];
          y[offsety + (col + 1)] = alpha * sum1 + beta * y[offsety + (col + 1)];
          y[offsety + (col + 2)] = alpha * sum2 + beta * y[offsety + (col + 2)];
          y[offsety + (col + 3)] = alpha * sum3 + beta * y[offsety + (col + 3)];
        } else {
          y[offsety + (col + 0)] = alpha * sum0;
          y[offsety + (col + 1)] = alpha * sum1;
          y[offsety + (col + 2)] = alpha * sum2;
          y[offsety + (col + 3)] = alpha * sum3;
        }
      }
      for (; col < n; col += 1) {
        int row = 0;
        float alphax0 = alpha * x[offsetx + (col + 0)];
        float sum0 = 0.0f;
        for (; row < col; row += 1) {
          float a0 = a[offseta + row + (col + 0) * ((col + 0) + 1) / 2];
          y[offsety + row] += alphax0 * a0;
          sum0 += x[offsetx + row] * a0;
        }
        sum0 += x[offsetx + row] * a[offseta + row + (col + 0) * ((col + 0) + 1) / 2];
        if (beta != 0.0f) {
          y[offsety + (col + 0)] = alpha * sum0 + beta * y[offsety + (col + 0)];
        } else {
          y[offsety + (col + 0)] = alpha * sum0;
        }
      }
    } else {
      super.sspmvU(n, alpha, a, offseta, x, offsetx, incx, beta, y, offsety, incy);
    }
  }

  protected void dswapK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
      int i = 0;
      for (; i < DMAX.loopBound(n); i += DMAX.length()) {
        DoubleVector x0 = DoubleVector.fromArray(DMAX, x, offsetx + i);
        DoubleVector y0 = DoubleVector.fromArray(DMAX, y, offsety + i);
        x0.intoArray(y, offsety + i);
        y0.intoArray(x, offsetx + i);
      }
      for (; i < n; i += 1) {
        double x0 = x[offsetx + i];
        double y0 = y[offsety + i];
        y[offsety + i] = x0;
        x[offsetx + i] = y0;
      }
    } else {
      super.dswapK(n, x, offsetx, incx, y, offsety, incy);
    }
  }

  protected void sswapK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
      int i = 0;
      for (; i < FMAX.loopBound(n); i += FMAX.length()) {
        FloatVector x0 = FloatVector.fromArray(FMAX, x, offsetx + i);
        FloatVector y0 = FloatVector.fromArray(FMAX, y, offsety + i);
        x0.intoArray(y, offsety + i);
        y0.intoArray(x, offsetx + i);
      }
      for (; i < n; i += 1) {
        float x0 = x[offsetx + i];
        float y0 = y[offsety + i];
        y[offsety + i] = x0;
        x[offsetx + i] = y0;
      }
    } else {
      super.sswapK(n, x, offsetx, incx, y, offsety, incy);
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

  protected void dsymvU(int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
      int col = 0;
      for (; col < loopBound(n, 4); col += 4) {
        int row = 0;
        DoubleVector vsum0 = DoubleVector.zero(DMAX);
        DoubleVector vsum1 = DoubleVector.zero(DMAX);
        DoubleVector vsum2 = DoubleVector.zero(DMAX);
        DoubleVector vsum3 = DoubleVector.zero(DMAX);
        DoubleVector valphax0 = DoubleVector.broadcast(DMAX, alpha * x[offsetx + (col + 0)]);
        DoubleVector valphax1 = DoubleVector.broadcast(DMAX, alpha * x[offsetx + (col + 1)]);
        DoubleVector valphax2 = DoubleVector.broadcast(DMAX, alpha * x[offsetx + (col + 2)]);
        DoubleVector valphax3 = DoubleVector.broadcast(DMAX, alpha * x[offsetx + (col + 3)]);
        for (; row < DMAX.loopBound(col); row += DMAX.length()) {
          DoubleVector va0 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 0) * lda);
          DoubleVector va1 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 1) * lda);
          DoubleVector va2 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 2) * lda);
          DoubleVector va3 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 3) * lda);
          DoubleVector vy0 = DoubleVector.fromArray(DMAX, y, offsety + row);
          valphax0.fma(va0, valphax1.fma(va1, valphax2.fma(va2, valphax3.fma(va3, vy0))))
            .intoArray(y, offsety + row);
          DoubleVector vx0 = DoubleVector.fromArray(DMAX, x, offsetx + row);
          vsum0 = vx0.fma(va0, vsum0);
          vsum1 = vx0.fma(va1, vsum1);
          vsum2 = vx0.fma(va2, vsum2);
          vsum3 = vx0.fma(va3, vsum3);
        }
        double sum0 = vsum0.reduceLanes(VectorOperators.ADD);
        double sum1 = vsum1.reduceLanes(VectorOperators.ADD);
        double sum2 = vsum2.reduceLanes(VectorOperators.ADD);
        double sum3 = vsum3.reduceLanes(VectorOperators.ADD);
        double alphax0 = alpha * x[offsetx + (col + 0)];
        double alphax1 = alpha * x[offsetx + (col + 1)];
        double alphax2 = alpha * x[offsetx + (col + 2)];
        double alphax3 = alpha * x[offsetx + (col + 3)];
        for (; row < col; row += 1) {
          double a0 = a[offseta + row + (col + 0) * lda];
          double a1 = a[offseta + row + (col + 1) * lda];
          double a2 = a[offseta + row + (col + 2) * lda];
          double a3 = a[offseta + row + (col + 3) * lda];
          y[offsety + row] += alphax0 * a0 + alphax1 * a1 + alphax2 * a2 + alphax3 * a3;
          double x0 = x[offsetx + row];
          sum0 += x0 * a0;
          sum1 += x0 * a1;
          sum2 += x0 * a2;
          sum3 += x0 * a3;
        }
        {
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
          double x0 = x[offsetx + (row + 0)];
          double x1 = x[offsetx + (row + 1)];
          double x2 = x[offsetx + (row + 2)];
          double x3 = x[offsetx + (row + 3)];
          sum0 += x0 * a00 + x1 * a01 + x2 * a02 + x3 * a03;
          sum1 += x0 * a01 + x1 * a11 + x2 * a12 + x3 * a13;
          sum2 += x0 * a02 + x1 * a12 + x2 * a22 + x3 * a23;
          sum3 += x0 * a03 + x1 * a13 + x2 * a23 + x3 * a33;
        }
        if (beta != 0.0) {
          y[offsety + (col + 0)] = alpha * sum0 + beta * y[offsety + (col + 0)];
          y[offsety + (col + 1)] = alpha * sum1 + beta * y[offsety + (col + 1)];
          y[offsety + (col + 2)] = alpha * sum2 + beta * y[offsety + (col + 2)];
          y[offsety + (col + 3)] = alpha * sum3 + beta * y[offsety + (col + 3)];
        } else {
          y[offsety + (col + 0)] = alpha * sum0;
          y[offsety + (col + 1)] = alpha * sum1;
          y[offsety + (col + 2)] = alpha * sum2;
          y[offsety + (col + 3)] = alpha * sum3;
        }
      }
      for (; col < n; col += 1) {
        int row = 0;
        DoubleVector vsum0 = DoubleVector.zero(DMAX);
        DoubleVector valphax0 = DoubleVector.broadcast(DMAX, alpha * x[offsetx + (col + 0)]);
        for (; row < DMAX.loopBound(col); row += DMAX.length()) {
          DoubleVector va0 = DoubleVector.fromArray(DMAX, a, offseta + row + (col + 0) * lda);
          DoubleVector vy0 = DoubleVector.fromArray(DMAX, y, offsety + row);
          valphax0.fma(va0, vy0)
            .intoArray(y, offsety + row);
          DoubleVector vx0 = DoubleVector.fromArray(DMAX, x, offsetx + row);
          vsum0 = vx0.fma(va0, vsum0);
        }
        double sum0 = vsum0.reduceLanes(VectorOperators.ADD);
        double alphax0 = alpha * x[offsetx + (col + 0)];
        for (; row < col; row += 1) {
          double a0 = a[offseta + row + (col + 0) * lda];
          y[offsety + row] += alphax0 * a0;
          double x0 = x[offsetx + row];
          sum0 += x0 * a0;
        }
        {
          double a00 = a[offseta + (row + 0) + (col + 0) * lda];
          double x0 = x[offsetx + (row + 0)];
          sum0 += x0 * a00;
        }
        if (beta != 0.0) {
          y[offsety + (col + 0)] = alpha * sum0 + beta * y[offsety + (col + 0)];
        } else {
          y[offsety + (col + 0)] = alpha * sum0;
        }
      }
    } else {
      super.dsymvU(n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
    }
  }

  protected void ssymvU(int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    if (incx == 1 && incy == 1) {
      int col = 0;
      for (; col < loopBound(n, 4); col += 4) {
        int row = 0;
        FloatVector vsum0 = FloatVector.zero(FMAX);
        FloatVector vsum1 = FloatVector.zero(FMAX);
        FloatVector vsum2 = FloatVector.zero(FMAX);
        FloatVector vsum3 = FloatVector.zero(FMAX);
        FloatVector valphax0 = FloatVector.broadcast(FMAX, alpha * x[offsetx + (col + 0)]);
        FloatVector valphax1 = FloatVector.broadcast(FMAX, alpha * x[offsetx + (col + 1)]);
        FloatVector valphax2 = FloatVector.broadcast(FMAX, alpha * x[offsetx + (col + 2)]);
        FloatVector valphax3 = FloatVector.broadcast(FMAX, alpha * x[offsetx + (col + 3)]);
        for (; row < FMAX.loopBound(col); row += FMAX.length()) {
          FloatVector va0 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 0) * lda);
          FloatVector va1 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 1) * lda);
          FloatVector va2 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 2) * lda);
          FloatVector va3 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 3) * lda);
          FloatVector vy0 = FloatVector.fromArray(FMAX, y, offsety + row);
          valphax0.fma(va0, valphax1.fma(va1, valphax2.fma(va2, valphax3.fma(va3, vy0))))
            .intoArray(y, offsety + row);
          FloatVector vx0 = FloatVector.fromArray(FMAX, x, offsetx + row);
          vsum0 = vx0.fma(va0, vsum0);
          vsum1 = vx0.fma(va1, vsum1);
          vsum2 = vx0.fma(va2, vsum2);
          vsum3 = vx0.fma(va3, vsum3);
        }
        float sum0 = vsum0.reduceLanes(VectorOperators.ADD);
        float sum1 = vsum1.reduceLanes(VectorOperators.ADD);
        float sum2 = vsum2.reduceLanes(VectorOperators.ADD);
        float sum3 = vsum3.reduceLanes(VectorOperators.ADD);
        float alphax0 = alpha * x[offsetx + (col + 0)];
        float alphax1 = alpha * x[offsetx + (col + 1)];
        float alphax2 = alpha * x[offsetx + (col + 2)];
        float alphax3 = alpha * x[offsetx + (col + 3)];
        for (; row < col; row += 1) {
          float a0 = a[offseta + row + (col + 0) * lda];
          float a1 = a[offseta + row + (col + 1) * lda];
          float a2 = a[offseta + row + (col + 2) * lda];
          float a3 = a[offseta + row + (col + 3) * lda];
          y[offsety + row] += alphax0 * a0 + alphax1 * a1 + alphax2 * a2 + alphax3 * a3;
          float x0 = x[offsetx + row];
          sum0 += x0 * a0;
          sum1 += x0 * a1;
          sum2 += x0 * a2;
          sum3 += x0 * a3;
        }
        {
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
          float x0 = x[offsetx + (row + 0)];
          float x1 = x[offsetx + (row + 1)];
          float x2 = x[offsetx + (row + 2)];
          float x3 = x[offsetx + (row + 3)];
          sum0 += x0 * a00 + x1 * a01 + x2 * a02 + x3 * a03;
          sum1 += x0 * a01 + x1 * a11 + x2 * a12 + x3 * a13;
          sum2 += x0 * a02 + x1 * a12 + x2 * a22 + x3 * a23;
          sum3 += x0 * a03 + x1 * a13 + x2 * a23 + x3 * a33;
        }
        if (beta != 0.0f) {
          y[offsety + (col + 0)] = alpha * sum0 + beta * y[offsety + (col + 0)];
          y[offsety + (col + 1)] = alpha * sum1 + beta * y[offsety + (col + 1)];
          y[offsety + (col + 2)] = alpha * sum2 + beta * y[offsety + (col + 2)];
          y[offsety + (col + 3)] = alpha * sum3 + beta * y[offsety + (col + 3)];
        } else {
          y[offsety + (col + 0)] = alpha * sum0;
          y[offsety + (col + 1)] = alpha * sum1;
          y[offsety + (col + 2)] = alpha * sum2;
          y[offsety + (col + 3)] = alpha * sum3;
        }
      }
      for (; col < n; col += 1) {
        int row = 0;
        FloatVector vsum0 = FloatVector.zero(FMAX);
        FloatVector valphax0 = FloatVector.broadcast(FMAX, alpha * x[offsetx + (col + 0)]);
        for (; row < FMAX.loopBound(col); row += FMAX.length()) {
          FloatVector va0 = FloatVector.fromArray(FMAX, a, offseta + row + (col + 0) * lda);
          FloatVector vy0 = FloatVector.fromArray(FMAX, y, offsety + row);
          valphax0.fma(va0, vy0)
            .intoArray(y, offsety + row);
          FloatVector vx0 = FloatVector.fromArray(FMAX, x, offsetx + row);
          vsum0 = vx0.fma(va0, vsum0);
        }
        float sum0 = vsum0.reduceLanes(VectorOperators.ADD);
        float alphax0 = alpha * x[offsetx + (col + 0)];
        for (; row < col; row += 1) {
          float a0 = a[offseta + row + (col + 0) * lda];
          y[offsety + row] += alphax0 * a0;
          float x0 = x[offsetx + row];
          sum0 += x0 * a0;
        }
        {
          float a00 = a[offseta + (row + 0) + (col + 0) * lda];
          float x0 = x[offsetx + (row + 0)];
          sum0 += x0 * a00;
        }
        if (beta != 0.0f) {
          y[offsety + (col + 0)] = alpha * sum0 + beta * y[offsety + (col + 0)];
        } else {
          y[offsety + (col + 0)] = alpha * sum0;
        }
      }
    } else {
      super.ssymvU(n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
    }
  }
}
