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

import com.github.fommil.netlib.F2jBLAS;
import jdk.incubator.vector.DoubleVector;
import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;

public class VectorizedBLAS extends F2jBLAS {

  private static final VectorSpecies<Float>  FMAX = FloatVector.SPECIES_MAX;
  private static final VectorSpecies<Double> DMAX = DoubleVector.SPECIES_MAX;
  private static final VectorSpecies<Double> D128 = DoubleVector.SPECIES_128;

  // y += alpha * x
  @Override
  public void daxpy(int n, double alpha, double[] x, int incx, double[] y, int incy) {
    // printf("daxpy(n=%s, alpha=%s, x=%s, incx=%s, y=%s, incy=%s)\n", // scalastyle = ignore
    //         n, alpha, x, incx, y, incy)
    if (incx == 1 && incy == 1 && n <= x.length && n <= y.length) {
      if (alpha != 0.) {
        int i = 0;
        for (; i < DMAX.loopBound(n); i += DMAX.length()) {
          DoubleVector vx = DoubleVector.fromArray(DMAX, x, i);
          DoubleVector vy = DoubleVector.fromArray(DMAX, y, i);
          vx.lanewise(VectorOperators.MUL, alpha).add(vy)
            .intoArray(y, i);
        }
        for (; i < n; i += 1) {
          y[i] += alpha * x[i];
        }
      }
    } else {
      super.daxpy(n, alpha, x, incx, y, incy);
    }
  }

  // sum(x * y)
  @Override
  public float sdot(int n, float[] x, int incx, float[] y, int incy) {
    // printf("sdot(n=%s, x=%s, incx=%s, y=%s, incy=%s)\n", // scalastyle = ignore
    //         n, x, incx, y, incy)
    if (incx == 1 && incy == 1) {
      float sum = 0.0f;
      int i = 0;
      for (; i < FMAX.loopBound(n); i += FMAX.length()) {
        FloatVector vx = FloatVector.fromArray(FMAX, x, i);
        FloatVector vy = FloatVector.fromArray(FMAX, y, i);
        sum += vx.mul(vy).reduceLanes(VectorOperators.ADD);
      }
      for (; i < n; i += 1) {
        sum += x[i] * y[i];
      }
      return sum;
    } else {
      return super.sdot(n, x, incx, y, incy);
    }
  }

  // sum(x * y)
  @Override
  public double ddot(int n, double[] x, int incx, double[] y, int incy) {
    // printf("ddot(n=%s, x=%s, incx=%s, y=%s, incy=%s)\n", // scalastyle = ignore
    //         n, x, incx, y, incy)
    if (incx == 1 && incy == 1) {
      double sum = 0.;
      int i = 0;
      for (; i < DMAX.loopBound(n); i += DMAX.length()) {
        DoubleVector vx = DoubleVector.fromArray(DMAX, x, i);
        DoubleVector vy = DoubleVector.fromArray(DMAX, y, i);
        sum += vx.mul(vy).reduceLanes(VectorOperators.ADD);
      }
      for (; i < n; i += 1) {
        sum += x[i] * y[i];
      }
      return sum;
    } else {
      return super.ddot(n, x, incx, y, incy);
    }
  }

  // x = alpha * x
  @Override
  public void dscal(int n, double alpha, double[] x, int incx) {
    // printf("dscal(n=%s, alpha=%s, x=%s, incx=%s)\n", // scalastyle = ignore
    //         n, alpha, x, incx)
    if (incx == 1) {
      if (alpha != 1.) {
        int i = 0;
        for (; i < DMAX.loopBound(n); i += DMAX.length()) {
          DoubleVector vx = DoubleVector.fromArray(DMAX, x, i);
          vx.lanewise(VectorOperators.MUL, alpha)
            .intoArray(x, i);
        }
        for (; i < n; i += 1) {
          x[i] *= alpha;
        }
      }
    } else {
      super.dscal(n, alpha, x, incx);
    }
  }

  // y := alpha * a * x + beta * y
  @Override
  public void dspmv(String uplo, int n, double alpha, double[] a, double[] x, int incx, double beta, double[] y, int incy) {
    // printf("dspmv(uplo=%s, n=%s, alpha=%s, a=%s, x=%s, incx=%s, " + // scalastyle = ignore
    //              "beta=%s, y=%s, incy=%s)\n",
    //         uplo, n, alpha, a, x, incx, beta, y, incy)
    if (uplo.equals("U") && incx == 1 && incy == 1) {
      // y = beta * y
      dscal(n, beta, y, 1);
      // y += alpha * A * x
      if (alpha != 0.) {
        for (int col = 0; col < n; col += 1) {
          int row = 0;
          for (; row < DMAX.loopBound(col + 1); row += DMAX.length()) {
            DoubleVector vx = DoubleVector.fromArray(DMAX, x, row);
            DoubleVector va = DoubleVector.fromArray(DMAX, a, row + col * (col + 1) / 2);
            y[col] += alpha * vx.mul(va).reduceLanes(VectorOperators.ADD);
          }
          for (; row < col + 1; row += 1) {
            y[col] += alpha * x[row] * a[row + col * (col + 1) / 2];
          }
          for (; row < n; row += 1) {
            y[col] += alpha * x[row] * a[row * (row + 1) / 2 + col];
          }
        }
      }
    } else {
      super.dspmv(uplo, n, alpha, a, x, incx, beta, y, incy);
    }
  }

  // a += alpha * x * x.t
  @Override
  public void dspr(String uplo, int n, double alpha, double[] x, int incx, double[] a) {
    // printf("dspr(uplo=%s, n=%s, alpha=%s, x=%s, incx=%s, a=%s)\n", // scalastyle = ignore
    //         uplo, n, alpha, x, incx, a)
    if (uplo.equals("U") && incx == 1) {
      if (alpha != 0.) {
        for (int col = 0; col < n; col += 1) {
          int row = 0;
          for (; row < DMAX.loopBound(col + 1); row += DMAX.length()) {
            DoubleVector vx = DoubleVector.fromArray(DMAX, x, row);
            DoubleVector va = DoubleVector.fromArray(DMAX, a, row + col * (col + 1) / 2);
            vx.lanewise(VectorOperators.MUL, alpha * x[col]).add(va)
              .intoArray(a, row + col * (col + 1) / 2);
          }
          for (; row < col + 1; row += 1) {
            a[row + col * (col + 1) / 2] += alpha * x[col] * x[row];
          }
        }
      }
    } else {
      super.dspr(uplo, n, alpha, x, incx, a);
    }
  }

  // a += alpha * x * x.t
  @Override
  public void dsyr(String uplo, int n, double alpha, double[] x, int incx, double[] a, int lda) {
    // printf("dsyr(uplo=%s, n=%s, alpha=%s, x=%s, incx=%s, a=%s, lda=%s)\n", // scalastyle = ignore
    //         uplo, n, alpha, x, incx, a, lda)
    if (uplo.equals("U") && incx == 1) {
      if (alpha != 0.) {
        for (int col = 0; col < n; col += 1) {
          int row = 0;
          for (; row < DMAX.loopBound(col + 1); row += DMAX.length()) {
            DoubleVector vx = DoubleVector.fromArray(DMAX, x, row);
            DoubleVector va = DoubleVector.fromArray(DMAX, a, row + col * n);
            vx.lanewise(VectorOperators.MUL, alpha * x[col]).add(va)
              .intoArray(a, row + col * n);
          }
          for (; row < col + 1; row += 1) {
            a[row + col * n] += alpha * x[col] * x[row];
          }
        }
      }
    } else {
      super.dsyr(uplo, n, alpha, x, incx, a, lda);
    }
  }

  // y = alpha * A * x + beta * y
  @Override
  public void dgemv(String trans, int m, int n, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    // printf("dgemv(trans=%s, m=%s, n=%s, alpha=%s, a=%s, lda=%s, x=%s, " + // scalastyle = ignore
    //              "incx=%s, beta=%s, y=%s, incy=%s)\n",
    //         trans, m, n, alpha, a, lda, x, incx, beta, y, incy)
    if (trans.equals("T") && incx == 1 && incy == 1 && lda == m) {
      // y = beta * y
      dscal(n, beta, y, 1);
      // y += alpha * A * x
      if (alpha != 0.) {
        for (int col = 0; col < n; col += 1) {
          int row = 0;
          for (; row < DMAX.loopBound(m); row += DMAX.length()){
            DoubleVector vx = DoubleVector.fromArray(DMAX, x, row);
            DoubleVector va = DoubleVector.fromArray(DMAX, a, row + col * m);
            y[col] += alpha * vx.mul(va).reduceLanes(VectorOperators.ADD);
          }
          for (; row < m; row += 1){
            y[col] += alpha * x[row] * a[row + col * m];
          }
        }
      }
    } else {
      super.dgemv(trans, m, n, alpha, a, lda, x, incx, beta, y, incy);
    }
  }

  @Override
  public void dgemm(String transa, String transb, int m, int n, int k, double alpha, double[] a, int lda, double[] b, int ldb, double beta, double[] c, int ldc) {
    // printf("dgemm(transa=%s, transb=%s, m=%s, n=%s, k=%s, alpha=%s, " + // scalastyle = ignore
    //              "a=%s, lda=%s, b=%s, ldb=%s, beta=%s, c=%s, ldc=%s)\n",
    //         transa, transb, m, n, k, alpha, a, lda, b, ldb, beta, c, ldc)
    // val start = System.nanoTime()
    if (transa.equals("T") && transb.equals("N") && lda == k && ldb == k && ldc == m) {
      // C = beta * C
      dscal(m * n, beta, c, 1);
      // C += alpha * A * B
      if (alpha != 0.) {
        for (int col = 0; col < n; col += 1) {
          for (int row = 0; row < m; row += 1) {
            int i = 0;
            for (; i < DMAX.loopBound(k); i += DMAX.length()) {
              DoubleVector va = DoubleVector.fromArray(DMAX, a, i + row * k);
              DoubleVector vb = DoubleVector.fromArray(DMAX, b, i + col * k);
              c[row + col * m] += alpha * va.mul(vb).reduceLanes(VectorOperators.ADD);
            }
            for (; i < k; i += 1) {
              c[row + col * m] += alpha * a[i + row * k] * b[i + col * k];
            }
          }
        }
      }
    } else {
      super.dgemm(transa, transb, m, n, k, alpha, a, lda, b, ldb, beta, c, ldc);
    }
    // printf("dgemm(transa=%s, transb=%s, m=%s, n=%s, k=%s, alpha=%s, " + // scalastyle = ignore
    //               "a=%s, lda=%s, b=%s, ldb=%s, beta=%s, c=%s, ldc=%s) -> %dms\n",
    //         transa, transb, m, n, k, alpha, a, lda, b, ldb, beta, c, ldc,
    //         (System.nanoTime() - start) / 1000 / 1000)
  }

}
