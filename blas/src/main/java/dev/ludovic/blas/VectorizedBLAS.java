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

import jdk.incubator.vector.DoubleVector;
import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;

public class VectorizedBLAS extends JavaBLAS {

  private static final VectorSpecies<Float>  FMAX = FloatVector.SPECIES_MAX;
  private static final VectorSpecies<Double> DMAX = DoubleVector.SPECIES_MAX;

  private static final VectorizedBLAS instance = new VectorizedBLAS();

  protected VectorizedBLAS() {}

  public static BLAS getInstance() {
    return instance;
  }

  @Override
  public double dasum(int n, double[] x, int offsetx, int incx) {
    if (n > 0 && x != null && x.length >= offsetx + n && incx == 1) {
      double sum = 0.;
      int i = 0;
      DoubleVector vsum = DoubleVector.zero(DMAX);
      for (; i < DMAX.loopBound(n); i += DMAX.length()) {
        DoubleVector vx = DoubleVector.fromArray(DMAX, x, offsetx + i);
        vsum = vx.abs().add(vsum);
      }
      sum += vsum.reduceLanes(VectorOperators.ADD);
      for (; i < n; i += 1) {
        sum += Math.abs(x[offsetx + i]);
      }
      return sum;
    } else {
      return super.dasum(n, x, offsetx, incx);
    }
  }

  @Override
  public float sasum(int n, float[] x, int offsetx, int incx) {
    if (n > 0 && x != null && x.length >= offsetx + n && incx == 1) {
      float sum = 0.f;
      int i = 0;
      FloatVector vsum = FloatVector.zero(FMAX);
      for (; i < FMAX.loopBound(n); i += FMAX.length()) {
        FloatVector vx = FloatVector.fromArray(FMAX, x, offsetx + i);
        vsum = vx.abs().add(vsum);
      }
      sum += vsum.reduceLanes(VectorOperators.ADD);
      for (; i < n; i += 1) {
        sum += Math.abs(x[offsetx + i]);
      }
      return sum;
    } else {
      return super.sasum(n, x, offsetx, incx);
    }
  }

  // y += alpha * x
  @Override
  public void daxpy(int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (n > 0
        && x != null && x.length >= offsetx + n && incx == 1
        && y != null && y.length >= offsety + n && incy == 1) {
      if (alpha != 0.) {
        int i = 0;
        DoubleVector valpha = DoubleVector.broadcast(DMAX, alpha);
        for (; i < DMAX.loopBound(n); i += DMAX.length()) {
          DoubleVector vx = DoubleVector.fromArray(DMAX, x, offsetx + i);
          DoubleVector vy = DoubleVector.fromArray(DMAX, y, offsety + i);
          vx.fma(valpha, vy).intoArray(y, offsety + i);
        }
        for (; i < n; i += 1) {
          y[offsety + i] += alpha * x[offsetx + i];
        }
      }
    } else {
      super.daxpy(n, alpha, x, offsetx, incx, y, offsety, incy);
    }
  }

  @Override
  public void saxpy(int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (n > 0
        && x != null && x.length >= offsetx + n && incx == 1
        && y != null && y.length >= offsety + n && incy == 1) {
      if (alpha != 0.f) {
        int i = 0;
        FloatVector valpha = FloatVector.broadcast(FMAX, alpha);
        for (; i < FMAX.loopBound(n); i += FMAX.length()) {
          FloatVector vx = FloatVector.fromArray(FMAX, x, offsetx + i);
          FloatVector vy = FloatVector.fromArray(FMAX, y, offsety + i);
          vx.fma(valpha, vy).intoArray(y, offsety + i);
        }
        for (; i < n; i += 1) {
          y[offsety + i] += alpha * x[offsetx + i];
        }
      }
    } else {
      super.saxpy(n, alpha, x, offsetx, incx, y, offsety, incy);
    }
  }

  // sum(x * y)
  @Override
  public double ddot(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (n > 0
        && x != null && x.length >= offsetx + n && incx == 1
        && y != null && y.length >= offsety + n && incy == 1) {
      double sum = 0.;
      int i = 0;
      DoubleVector vsum = DoubleVector.zero(DMAX);
      for (; i < DMAX.loopBound(n); i += DMAX.length()) {
        DoubleVector vx = DoubleVector.fromArray(DMAX, x, offsetx + i);
        DoubleVector vy = DoubleVector.fromArray(DMAX, y, offsety + i);
        vsum = vx.fma(vy, vsum);
      }
      sum += vsum.reduceLanes(VectorOperators.ADD);
      for (; i < n; i += 1) {
        sum += x[offsetx + i] * y[offsety + i];
      }
      return sum;
    } else {
      return super.ddot(n, x, offsetx, incx, y, offsety, incy);
    }
  }

  // sum(x * y)
  @Override
  public float sdot(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (n > 0
        && x != null && x.length >= offsetx + n && incx == 1
        && y != null && y.length >= offsety + n && incy == 1) {
      float sum = 0.f;
      int i = 0;
      FloatVector vsum = FloatVector.zero(FMAX);
      for (; i < FMAX.loopBound(n); i += FMAX.length()) {
        FloatVector vx = FloatVector.fromArray(FMAX, x, offsetx + i);
        FloatVector vy = FloatVector.fromArray(FMAX, y, offsety + i);
        vsum = vx.fma(vy, vsum);
      }
      sum += vsum.reduceLanes(VectorOperators.ADD);
      for (; i < n; i += 1) {
        sum += x[offsetx + i] * y[offsety + i];
      }
      return sum;
    } else {
      return super.sdot(n, x, offsetx, incx, y, offsety, incy);
    }
  }

  // c = alpha * a * b + beta * c
  @Override
  protected void dgemmNN(int m, int n, int k,
      double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb,
      double beta, double[] c, int offsetc, int ldc) {
    // C = beta * C
    dscal(m * n, beta, c, offsetc, 1);
    // C += alpha * A * B
    DoubleVector valpha = DoubleVector.broadcast(DMAX, alpha);
    for (int col = 0; col < n; col += 1) {
      for (int i = 0; i < k; i += 1) {
        int row = 0;
        for (; row < DMAX.loopBound(m); row += DMAX.length()) {
          DoubleVector va = DoubleVector.fromArray(DMAX, a, offseta + i * lda + row);
          DoubleVector vc = DoubleVector.fromArray(DMAX, c, offsetc + col * ldc + row);
          valpha.mul(b[offsetb + col * ldb + i]).fma(va, vc)
                .intoArray(c, offsetc + col * ldc + row);
        }
        for (; row < m; row += 1) {
          c[offsetc + col * ldc + row] += alpha * a[offseta + i * lda + row] * b[offsetb + col * ldb + i];
        }
      }
    }
  }

  @Override
  protected void dgemmNTKernel(int cols, int cole, int rows, int rowe, int is, int ie,
      double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb,
      double beta, double[] c, int offsetc, int ldc) {
    int col = cols;
    for (; col < loopBound(cole, 2); col += 2) {
      if (beta != 1.0) {
        int row = rows;
        DoubleVector vbeta = DoubleVector.broadcast(DMAX, beta);
        DoubleVector vzero = DoubleVector.zero(DMAX);
        for (; row < DMAX.loopBound(rowe); row += DMAX.length()) {
          if (beta != 0.0) {
            DoubleVector.fromArray(DMAX, c, offsetc + row + (col + 0) * ldc).mul(vbeta).intoArray(c, offsetc + row + (col + 0) * ldc);
            DoubleVector.fromArray(DMAX, c, offsetc + row + (col + 1) * ldc).mul(vbeta).intoArray(c, offsetc + row + (col + 1) * ldc);
          } else {
            vzero.intoArray(c, offsetc + row + (col + 0) * ldc);
            vzero.intoArray(c, offsetc + row + (col + 1) * ldc);
          }
        }
        for (; row < rowe; row += 1) {
          if (beta != 0.0) {
            c[offsetc + row + (col + 0) * ldc] = beta * c[offsetc + row + (col + 0) * ldc];
            c[offsetc + row + (col + 1) * ldc] = beta * c[offsetc + row + (col + 1) * ldc];
          } else {
            c[offsetc + row + (col + 0) * ldc] = 0.0;
            c[offsetc + row + (col + 1) * ldc] = 0.0;
          }
        }
      }
      int i = is;
      for (; i < loopBound(ie, 5); i += 5) {
        int row = rows;
        DoubleVector valphab00 = DoubleVector.broadcast(DMAX, alpha * b[offsetb + (col + 0) + (i + 0) * ldb]);
        DoubleVector valphab01 = DoubleVector.broadcast(DMAX, alpha * b[offsetb + (col + 0) + (i + 1) * ldb]);
        DoubleVector valphab02 = DoubleVector.broadcast(DMAX, alpha * b[offsetb + (col + 0) + (i + 2) * ldb]);
        DoubleVector valphab03 = DoubleVector.broadcast(DMAX, alpha * b[offsetb + (col + 0) + (i + 3) * ldb]);
        DoubleVector valphab04 = DoubleVector.broadcast(DMAX, alpha * b[offsetb + (col + 0) + (i + 4) * ldb]);
        DoubleVector valphab10 = DoubleVector.broadcast(DMAX, alpha * b[offsetb + (col + 1) + (i + 0) * ldb]);
        DoubleVector valphab11 = DoubleVector.broadcast(DMAX, alpha * b[offsetb + (col + 1) + (i + 1) * ldb]);
        DoubleVector valphab12 = DoubleVector.broadcast(DMAX, alpha * b[offsetb + (col + 1) + (i + 2) * ldb]);
        DoubleVector valphab13 = DoubleVector.broadcast(DMAX, alpha * b[offsetb + (col + 1) + (i + 3) * ldb]);
        DoubleVector valphab14 = DoubleVector.broadcast(DMAX, alpha * b[offsetb + (col + 1) + (i + 4) * ldb]);
        for (; row < DMAX.loopBound(rowe); row += DMAX.length()) {
          DoubleVector va0 = DoubleVector.fromArray(DMAX, a, offseta + row + (i + 0) * lda);
          DoubleVector va1 = DoubleVector.fromArray(DMAX, a, offseta + row + (i + 1) * lda);
          DoubleVector va2 = DoubleVector.fromArray(DMAX, a, offseta + row + (i + 2) * lda);
          DoubleVector va3 = DoubleVector.fromArray(DMAX, a, offseta + row + (i + 3) * lda);
          DoubleVector va4 = DoubleVector.fromArray(DMAX, a, offseta + row + (i + 4) * lda);
          DoubleVector vc0 = DoubleVector.fromArray(DMAX, c, offsetc + row + (col + 0) * ldc);
          DoubleVector vc1 = DoubleVector.fromArray(DMAX, c, offsetc + row + (col + 1) * ldc);
          valphab00.fma(va0, valphab01.fma(va1, valphab02.fma(va2, valphab03.fma(va3, valphab04.fma(va4, vc0)))))
            .intoArray(c, offsetc + row + (col + 0) * ldc);
          valphab10.fma(va0, valphab11.fma(va1, valphab12.fma(va2, valphab13.fma(va3, valphab14.fma(va4, vc1)))))
            .intoArray(c, offsetc + row + (col + 1) * ldc);
        }
        double alphab00 = alpha * b[offsetb + (col + 0) + (i + 0) * ldb];
        double alphab01 = alpha * b[offsetb + (col + 0) + (i + 1) * ldb];
        double alphab02 = alpha * b[offsetb + (col + 0) + (i + 2) * ldb];
        double alphab03 = alpha * b[offsetb + (col + 0) + (i + 3) * ldb];
        double alphab04 = alpha * b[offsetb + (col + 0) + (i + 4) * ldb];
        double alphab10 = alpha * b[offsetb + (col + 1) + (i + 0) * ldb];
        double alphab11 = alpha * b[offsetb + (col + 1) + (i + 1) * ldb];
        double alphab12 = alpha * b[offsetb + (col + 1) + (i + 2) * ldb];
        double alphab13 = alpha * b[offsetb + (col + 1) + (i + 3) * ldb];
        double alphab14 = alpha * b[offsetb + (col + 1) + (i + 4) * ldb];
        for (; row < rowe; row += 1) {
          double a0 = a[offseta + row + (i + 0) * lda];
          double a1 = a[offseta + row + (i + 1) * lda];
          double a2 = a[offseta + row + (i + 2) * lda];
          double a3 = a[offseta + row + (i + 3) * lda];
          double a4 = a[offseta + row + (i + 4) * lda];
          c[offsetc + row + (col + 0) * ldc] += alphab00 * a0
                                             +  alphab01 * a1
                                             +  alphab02 * a2
                                             +  alphab03 * a3
                                             +  alphab04 * a4;
          c[offsetc + row + (col + 1) * ldc] += alphab10 * a0
                                             +  alphab11 * a1
                                             +  alphab12 * a2
                                             +  alphab13 * a3
                                             +  alphab14 * a4;
        }
      }
      for (; i < ie; i += 1) {
        int row = rows;
        DoubleVector valphab00 = DoubleVector.broadcast(DMAX, alpha * b[offsetb + (col + 0) + (i + 0) * ldb]);
        DoubleVector valphab10 = DoubleVector.broadcast(DMAX, alpha * b[offsetb + (col + 1) + (i + 0) * ldb]);
        for (; row < DMAX.loopBound(rowe); row += DMAX.length()) {
          DoubleVector va0 = DoubleVector.fromArray(DMAX, a, offseta + row + (i + 0) * lda);
          DoubleVector vc0 = DoubleVector.fromArray(DMAX, c, offsetc + row + (col + 0) * ldc);
          DoubleVector vc1 = DoubleVector.fromArray(DMAX, c, offsetc + row + (col + 1) * ldc);
          valphab00.fma(va0, vc0).intoArray(c, offsetc + row + (col + 0) * ldc);
          valphab10.fma(va0, vc1).intoArray(c, offsetc + row + (col + 1) * ldc);
        }
        double alphab00 = alpha * b[offsetb + (col + 0) + (i + 0) * ldb];
        double alphab10 = alpha * b[offsetb + (col + 1) + (i + 0) * ldb];
        for (; row < rowe; row += 1) {
          double a0 = a[offseta + row + (i + 0) * lda];
          c[offsetc + row + (col + 0) * ldc] += alphab00 * a0;
          c[offsetc + row + (col + 1) * ldc] += alphab10 * a0;
        }
      }
    }
    for (; col < cole; col += 1) {
      if (beta != 1.0) {
        int row = rows;
        DoubleVector vbeta = DoubleVector.broadcast(DMAX, beta);
        DoubleVector vzero = DoubleVector.zero(DMAX);
        for (; row < DMAX.loopBound(rowe); row += DMAX.length()) {
          if (beta != 0.0) {
            DoubleVector.fromArray(DMAX, c, offsetc + row + (col + 0) * ldc).mul(vbeta).intoArray(c, offsetc + row + (col + 0) * ldc);
          } else {
            vzero.intoArray(c, offsetc + row + (col + 0) * ldc);
          }
        }
        for (; row < rowe; row += 1) {
          if (beta != 0.0) {
            c[offsetc + row + (col + 0) * ldc] = beta * c[offsetc + row + (col + 0) * ldc];
          } else {
            c[offsetc + row + (col + 0) * ldc] = 0.0;
          }
        }
      }
      int i = is;
      for (; i < loopBound(ie, 5); i += 5) {
        int row = rows;
        DoubleVector valphab00 = DoubleVector.broadcast(DMAX, alpha * b[offsetb + (col + 0) + (i + 0) * ldb]);
        DoubleVector valphab01 = DoubleVector.broadcast(DMAX, alpha * b[offsetb + (col + 0) + (i + 1) * ldb]);
        DoubleVector valphab02 = DoubleVector.broadcast(DMAX, alpha * b[offsetb + (col + 0) + (i + 2) * ldb]);
        DoubleVector valphab03 = DoubleVector.broadcast(DMAX, alpha * b[offsetb + (col + 0) + (i + 3) * ldb]);
        DoubleVector valphab04 = DoubleVector.broadcast(DMAX, alpha * b[offsetb + (col + 0) + (i + 4) * ldb]);
        for (; row < DMAX.loopBound(rowe); row += DMAX.length()) {
          DoubleVector va0 = DoubleVector.fromArray(DMAX, a, offseta + row + (i + 0) * lda);
          DoubleVector va1 = DoubleVector.fromArray(DMAX, a, offseta + row + (i + 1) * lda);
          DoubleVector va2 = DoubleVector.fromArray(DMAX, a, offseta + row + (i + 2) * lda);
          DoubleVector va3 = DoubleVector.fromArray(DMAX, a, offseta + row + (i + 3) * lda);
          DoubleVector va4 = DoubleVector.fromArray(DMAX, a, offseta + row + (i + 4) * lda);
          DoubleVector vc0 = DoubleVector.fromArray(DMAX, c, offsetc + row + (col + 0) * ldc);
          valphab00.fma(va0, valphab01.fma(va1, valphab02.fma(va2, valphab03.fma(va3, valphab04.fma(va4, vc0)))))
            .intoArray(c, offsetc + row + col * ldc);
        }
        double alphab00 = alpha * b[offsetb + (col + 0) + (i + 0) * ldb];
        double alphab01 = alpha * b[offsetb + (col + 0) + (i + 1) * ldb];
        double alphab02 = alpha * b[offsetb + (col + 0) + (i + 2) * ldb];
        double alphab03 = alpha * b[offsetb + (col + 0) + (i + 3) * ldb];
        double alphab04 = alpha * b[offsetb + (col + 0) + (i + 4) * ldb];
        for (; row < rowe; row += 1) {
          double a0 = a[offseta + row + (i + 0) * lda];
          double a1 = a[offseta + row + (i + 1) * lda];
          double a2 = a[offseta + row + (i + 2) * lda];
          double a3 = a[offseta + row + (i + 3) * lda];
          double a4 = a[offseta + row + (i + 4) * lda];
          c[offsetc + row + (col + 0) * ldc] += alphab00 * a0
                                             +  alphab01 * a1
                                             +  alphab02 * a2
                                             +  alphab03 * a3
                                             +  alphab04 * a4;
        }
      }
      for (; i < ie; i += 1) {
        int row = rows;
        DoubleVector valphab00 = DoubleVector.broadcast(DMAX, alpha * b[offsetb + (col + 0) + (i + 0) * ldb]);
        for (; row < DMAX.loopBound(rowe); row += DMAX.length()) {
          DoubleVector va0 = DoubleVector.fromArray(DMAX, a, offseta + row + (i + 0) * lda);
          DoubleVector vc0 = DoubleVector.fromArray(DMAX, c, offsetc + row + (col + 0) * ldc);
          valphab00.fma(va0, vc0)
            .intoArray(c, offsetc + row + col * ldc);
        }
        double alphab00 = alpha * b[offsetb + (col + 0) + (i + 0) * ldb];
        for (; row < rowe; row += 1) {
          double a0 = a[offseta + row + (i + 0) * lda];
          c[offsetc + row + (col + 0) * ldc] += alphab00 * a0;
        }
      }
    }
  }

  // c = alpha * a * b + beta * c
  @Override
  protected void dgemmTN(int m, int n, int k,
      double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb,
      double beta, double[] c, int offsetc, int ldc) {
    for (int col = 0; col < n; col += 1) {
      for (int row = 0; row < m; row += 1) {
        // C = beta * C
        c[offsetc + col * ldc + row] = beta * c[offsetc + col * ldc + row];
        // C += alpha * A * B
        int i = 0;
        DoubleVector vsum = DoubleVector.zero(DMAX);
        for (; i < DMAX.loopBound(k); i += DMAX.length()) {
          DoubleVector va = DoubleVector.fromArray(DMAX, a, offseta + i + row * lda);
          DoubleVector vb = DoubleVector.fromArray(DMAX, b, offsetb + col * ldb + i);
          vsum = va.fma(vb, vsum);
        }
        c[offsetc + col * ldc + row] += alpha * vsum.reduceLanes(VectorOperators.ADD);
        for (; i < k; i += 1) {
          c[offsetc + col * ldc + row] += alpha * a[offseta + i + row * lda] * b[offsetb + col * ldb + i];
        }
      }
    }
  }

  // y = alpha * A * x + beta * y
  @Override
  protected void dgemvN(int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    // y = beta * y
    dscal(m, beta, y, offsety, 1);
    // y += alpha * A * x
    DoubleVector valpha = DoubleVector.broadcast(DMAX, alpha);
    for (int col = 0; col < n; col += 1) {
      int row = 0;
      for (; row < DMAX.loopBound(m); row += DMAX.length()) {
        DoubleVector va = DoubleVector.fromArray(DMAX, a, offseta + row + col * m);
        DoubleVector vy = DoubleVector.fromArray(DMAX, y, offsety + row);
        valpha.mul(x[offsetx + col]).fma(va, vy)
              .intoArray(y, offsety + row);
      }
      for (; row < m; row += 1) {
        y[offsety + row] += alpha * x[offsetx + col] * a[offseta + row + col * m];
      }
    }
  }

  // y = alpha * A * x + beta * y
  @Override
  protected void dgemvT(int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    for (int col = 0; col < n; col += 1) {
      // y = beta * y
      y[offsety + col] = beta * y[offsety + col];
      // y += alpha * A * x
      int row = 0;
      DoubleVector vsum = DoubleVector.zero(DMAX);
      for (; row < DMAX.loopBound(m); row += DMAX.length()) {
        DoubleVector va = DoubleVector.fromArray(DMAX, a, offseta + row + col * m);
        DoubleVector vx = DoubleVector.fromArray(DMAX, x, offsetx + row);
        vsum = va.fma(vx, vsum);
      }
      y[offsety + col] += alpha * vsum.reduceLanes(VectorOperators.ADD);
      for (; row < m; row += 1) {
        y[offsety + col] += alpha * x[offsetx + row] * a[offseta + row + col * m];
      }
    }
  }

  // y = alpha * A * x + beta * y
  @Override
  protected void sgemvN(int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    // y = beta * y
    sscal(m, beta, y, offsety, 1);
    // y += alpha * A * x
    FloatVector valpha = FloatVector.broadcast(FMAX, alpha);
    for (int col = 0; col < n; col += 1) {
      int row = 0;
      for (; row < FMAX.loopBound(m); row += FMAX.length()) {
        FloatVector va = FloatVector.fromArray(FMAX, a, offseta + row + col * m);
        FloatVector vy = FloatVector.fromArray(FMAX, y, offsety + row);
        valpha.mul(x[offsetx + col]).fma(va, vy)
              .intoArray(y, offsety + row);
      }
      for (; row < m; row += 1) {
        y[offsety + row] += alpha * x[offsetx + col] * a[offseta + row + col * m];
      }
    }
  }

  // y = alpha * A * x + beta * y
  @Override
  protected void sgemvT(int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    for (int col = 0; col < n; col += 1) {
      // y = beta * y
      y[offsety + col] = beta * y[offsety + col];
      // y += alpha * A * x
      int row = 0;
      FloatVector vsum = FloatVector.zero(FMAX);
      for (; row < FMAX.loopBound(m); row += FMAX.length()) {
        FloatVector va = FloatVector.fromArray(FMAX, a, offseta + row + col * m);
        FloatVector vx = FloatVector.fromArray(FMAX, x, offsetx + row);
        vsum = va.fma(vx, vsum);
      }
      y[offsety + col] += alpha * vsum.reduceLanes(VectorOperators.ADD);
      for (; row < m; row += 1) {
        y[offsety + col] += alpha * x[offsetx + row] * a[offseta + row + col * m];
      }
    }
  }

  // x = alpha * x
  @Override
  public void dscal(int n, double alpha, double[] x, int offsetx, int incx) {
    if (n > 0 && x != null && x.length >= offsetx + n && incx == 1) {
      if (alpha != 1.) {
        int i = 0;
        DoubleVector valpha = DoubleVector.broadcast(DMAX, alpha);
        for (; i < DMAX.loopBound(n); i += DMAX.length()) {
          DoubleVector vx = DoubleVector.fromArray(DMAX, x, offsetx + i);
          vx.mul(valpha).intoArray(x, offsetx + i);
        }
        for (; i < n; i += 1) {
          x[offsetx + i] *= alpha;
        }
      }
    } else {
      super.dscal(n, alpha, x, offsetx, incx);
    }
  }

  // x = alpha * x
  @Override
  public void sscal(int n, float alpha, float[] x, int offsetx, int incx) {
    if (n > 0 && x != null && x.length >= offsetx + n && incx == 1) {
      if (alpha != 1.f) {
        int i = 0;
        FloatVector valpha = FloatVector.broadcast(FMAX, alpha);
        for (; i < FMAX.loopBound(n); i += FMAX.length()) {
          FloatVector vx = FloatVector.fromArray(FMAX, x, offsetx + i);
          vx.mul(valpha).intoArray(x, offsetx + i);
        }
        for (; i < n; i += 1) {
          x[offsetx + i] *= alpha;
        }
      }
    } else {
      super.sscal(n, alpha, x, offsetx, incx);
    }
  }

  // y = alpha * a * x + beta * y
  @Override
  public void dspmv(String uplo, int n, double alpha, double[] a, int offseta, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    if (lsame("U", uplo)
        && n > 0
        && a != null && a.length >= offseta + n * (n + 1) / 2
        && x != null && x.length >= offsetx + n && incx == 1
        && y != null && y.length >= offsety + n && incy == 1) {
      // y = beta * y
      dscal(n, beta, y, offsety, 1);
      // y += alpha * A * x
      if (alpha != 0.) {
        DoubleVector valpha = DoubleVector.broadcast(DMAX, alpha);
        for (int row = 0; row < n; row += 1) {
          int col = 0;
          DoubleVector vyrowsum = DoubleVector.zero(DMAX);
          DoubleVector valphaxrow = DoubleVector.broadcast(DMAX, alpha * x[offsetx + row]);
          for (; col < DMAX.loopBound(row); col += DMAX.length()) {
            DoubleVector vx = DoubleVector.fromArray(DMAX, x, offsetx + col);
            DoubleVector vy = DoubleVector.fromArray(DMAX, y, offsety + col);
            DoubleVector va = DoubleVector.fromArray(DMAX, a, offseta + col + row * (row + 1) / 2);
            vyrowsum = valpha.mul(vx).fma(va, vyrowsum);
            valphaxrow.fma(va, vy).intoArray(y, offsety + col);
          }
          y[offsety + row] += vyrowsum.reduceLanes(VectorOperators.ADD);
          for (; col < row; col += 1) {
            y[offsety + row] += alpha * x[offsetx + col] * a[offseta + col + row * (row + 1) / 2];
            y[offsety + col] += alpha * x[offsetx + row] * a[offseta + col + row * (row + 1) / 2];
          }
          y[offsety + row] += alpha * x[offsetx + col] * a[offseta + col + row * (row + 1) / 2];
        }
      }
    } else {
      super.dspmv(uplo, n, alpha, a, offseta, x, offsetx, incx, beta, y, offsety, incy);
    }
  }

  // a += alpha * x * x.t
  @Override
  public void dspr(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta) {
    if (lsame("U", uplo)
        && n > 0
        && x != null && x.length >= offsetx + n && incx == 1
        && a != null && a.length >= offseta + n * (n + 1) / 2) {
      if (alpha != 0.) {
        for (int row = 0; row < n; row += 1) {
          int col = 0;
          DoubleVector valphaxrow = DoubleVector.broadcast(DMAX, alpha * x[offsetx + row]);
          for (; col < DMAX.loopBound(row + 1); col += DMAX.length()) {
            DoubleVector vx = DoubleVector.fromArray(DMAX, x, offsetx + col);
            DoubleVector va = DoubleVector.fromArray(DMAX, a, offseta + col + row * (row + 1) / 2);
            vx.fma(valphaxrow, va).intoArray(a, offseta + col + row * (row + 1) / 2);
          }
          for (; col < row + 1; col += 1) {
            a[offseta + col + row * (row + 1) / 2] += alpha * x[offsetx + row] * x[offsetx + col];
          }
        }
      }
    } else {
      super.dspr(uplo, n, alpha, x, offsetx, incx, a, offseta);
    }
  }

  @Override
  public void dswap(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
    if (n > 0
        && x != null && x.length >= offsetx + n && incx == 1
        && y != null && y.length >= offsety + n && incy == 1) {
      int i = 0;
      for (; i < DMAX.loopBound(n); i += DMAX.length()) {
        DoubleVector vtmp = DoubleVector.fromArray(DMAX, y, offsety + i);
        DoubleVector.fromArray(DMAX, x, offsetx + i).intoArray(y, offsety + i);
        vtmp.intoArray(x, offsetx + i);
      }
      for (; i < n; i += 1) {
        double tmp = y[offsety + i];
        y[offsety + i] = x[offsetx + i];
        x[offsetx + i] = tmp;
      }
    } else {
      super.dswap(n, x, offsetx, incx, y, offsety, incy);
    }
  }

  @Override
  public void sswap(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
    if (n > 0
        && x != null && x.length >= offsetx + n && incx == 1
        && y != null && y.length >= offsety + n && incy == 1) {
      int i = 0;
      for (; i < FMAX.loopBound(n); i += FMAX.length()) {
        FloatVector vtmp = FloatVector.fromArray(FMAX, y, offsety + i);
        FloatVector.fromArray(FMAX, x, offsetx + i).intoArray(y, offsety + i);
        vtmp.intoArray(x, offsetx + i);
      }
      for (; i < n; i += 1) {
        float tmp = y[offsety + i];
        y[offsety + i] = x[offsetx + i];
        x[offsetx + i] = tmp;
      }
    } else {
      super.sswap(n, x, offsetx, incx, y, offsety, incy);
    }
  }

  // a += alpha * x * x.t
  @Override
  public void dsyr(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta, int lda) {
    if (lsame("U", uplo)
        && n > 0
        && x != null && x.length >= offsetx + n && incx == 1
        && a != null && a.length >= offseta + n * n && lda == n) {
      if (alpha != 0.) {
        for (int row = 0; row < n; row += 1) {
          int col = 0;
          DoubleVector valphaxrow = DoubleVector.broadcast(DMAX, alpha * x[offsetx + row]);
          for (; col < DMAX.loopBound(row + 1); col += DMAX.length()) {
            DoubleVector vx = DoubleVector.fromArray(DMAX, x, offsetx + col);
            DoubleVector va = DoubleVector.fromArray(DMAX, a, offseta + col + row * n);
            vx.fma(valphaxrow, va).intoArray(a, offseta + col + row * n);
          }
          for (; col < row + 1; col += 1) {
            a[offseta + col + row * n] += alpha * x[offsetx + row] * x[offsetx + col];
          }
        }
      }
    } else {
      super.dsyr(uplo, n, alpha, x, offsetx, incx, a, offseta, lda);
    }
  }
}
