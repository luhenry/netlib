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

  // y += alpha * x
  @Override
  public void daxpy(int n, double alpha, double[] x, int incx, double[] y, int incy) {
    if (n >= 0
        && x != null && x.length >= n && incx == 1
        && y != null && y.length >= n && incy == 1) {
      if (alpha != 0.) {
        DoubleVector valpha = DoubleVector.broadcast(DMAX, alpha);
        int i = 0;
        for (; i < DMAX.loopBound(n); i += DMAX.length()) {
          DoubleVector vx = DoubleVector.fromArray(DMAX, x, i);
          DoubleVector vy = DoubleVector.fromArray(DMAX, y, i);
          vx.fma(valpha, vy).intoArray(y, i);
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
    if (n >= 0
        && x != null && x.length >= n && incx == 1
        && y != null && y.length >= n && incy == 1) {
      float sum = 0.0f;
      int i = 0;
      FloatVector vsum = FloatVector.zero(FMAX);
      for (; i < FMAX.loopBound(n); i += FMAX.length()) {
        FloatVector vx = FloatVector.fromArray(FMAX, x, i);
        FloatVector vy = FloatVector.fromArray(FMAX, y, i);
        vsum = vx.fma(vy, vsum);
      }
      sum += vsum.reduceLanes(VectorOperators.ADD);
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
    if (n >= 0
        && x != null && x.length >= n && incx == 1
        && y != null && y.length >= n && incy == 1) {
      double sum = 0.;
      int i = 0;
      DoubleVector vsum = DoubleVector.zero(DMAX);
      for (; i < DMAX.loopBound(n); i += DMAX.length()) {
        DoubleVector vx = DoubleVector.fromArray(DMAX, x, i);
        DoubleVector vy = DoubleVector.fromArray(DMAX, y, i);
        vsum = vx.fma(vy, vsum);
      }
      sum += vsum.reduceLanes(VectorOperators.ADD);
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
    if (n >= 0 && x != null && x.length >= n && incx == 1) {
      if (alpha != 1.) {
        DoubleVector valpha = DoubleVector.broadcast(DMAX, alpha);
        int i = 0;
        for (; i < DMAX.loopBound(n); i += DMAX.length()) {
          DoubleVector vx = DoubleVector.fromArray(DMAX, x, i);
          vx.mul(valpha).intoArray(x, i);
        }
        for (; i < n; i += 1) {
          x[i] *= alpha;
        }
      }
    } else {
      super.dscal(n, alpha, x, incx);
    }
  }

  // x = alpha * x
  @Override
  public void sscal(int n, float alpha, float[] x, int incx) {
    if (n >= 0 && x != null && x.length >= n && incx == 1) {
      if (alpha != 1.) {
        FloatVector valpha = FloatVector.broadcast(FMAX, alpha);
        int i = 0;
        for (; i < FMAX.loopBound(n); i += FMAX.length()) {
          FloatVector vx = FloatVector.fromArray(FMAX, x, i);
          vx.mul(valpha).intoArray(x, i);
        }
        for (; i < n; i += 1) {
          x[i] *= alpha;
        }
      }
    } else {
      super.sscal(n, alpha, x, incx);
    }
  }

  // y = alpha * a * x + beta * y
  @Override
  public void dspmv(String uplo, int n, double alpha, double[] a,
      double[] x, int incx, double beta, double[] y, int incy) {
    if ("U".equals(uplo)
        && n >= 0
        && a != null && a.length >= n * (n + 1) / 2
        && x != null && x.length >= n && incx == 1
        && y != null && y.length >= n && incy == 1) {
      // y = beta * y
      dscal(n, beta, y, 1);
      // y += alpha * A * x
      if (alpha != 0.) {
        DoubleVector valpha = DoubleVector.broadcast(DMAX, alpha);
        for (int row = 0; row < n; row += 1) {
          int col = 0;
          DoubleVector vyrowsum = DoubleVector.zero(DMAX);
          DoubleVector valphaxrow = DoubleVector.broadcast(DMAX, alpha * x[row]);
          for (; col < DMAX.loopBound(row); col += DMAX.length()) {
            DoubleVector vx = DoubleVector.fromArray(DMAX, x, col);
            DoubleVector vy = DoubleVector.fromArray(DMAX, y, col);
            DoubleVector va = DoubleVector.fromArray(DMAX, a, col + row * (row + 1) / 2);
            vyrowsum = valpha.mul(vx).fma(va, vyrowsum);
            valphaxrow.fma(va, vy).intoArray(y, col);
          }
          y[row] += vyrowsum.reduceLanes(VectorOperators.ADD);
          for (; col < row; col += 1) {
            y[row] += alpha * x[col] * a[col + row * (row + 1) / 2];
            y[col] += alpha * x[row] * a[col + row * (row + 1) / 2];
          }
          y[row] += alpha * x[col] * a[col + row * (row + 1) / 2];
        }
      }
    } else {
      super.dspmv(uplo, n, alpha, a, x, incx, beta, y, incy);
    }
  }

  // a += alpha * x * x.t
  @Override
  public void dspr(String uplo, int n, double alpha, double[] x, int incx, double[] a) {
    if ("U".equals(uplo)
        && n >= 0
        && x != null && x.length >= n && incx == 1
        && a != null && a.length >= n * (n + 1) / 2) {
      if (alpha != 0.) {
        for (int row = 0; row < n; row += 1) {
          int col = 0;
          DoubleVector valphaxrow = DoubleVector.broadcast(DMAX, alpha * x[row]);
          for (; col < DMAX.loopBound(row + 1); col += DMAX.length()) {
            DoubleVector vx = DoubleVector.fromArray(DMAX, x, col);
            DoubleVector va = DoubleVector.fromArray(DMAX, a, col + row * (row + 1) / 2);
            vx.fma(valphaxrow, va).intoArray(a, col + row * (row + 1) / 2);
          }
          for (; col < row + 1; col += 1) {
            a[col + row * (row + 1) / 2] += alpha * x[row] * x[col];
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
    if ("U".equals(uplo)
        && n >= 0
        && x != null && x.length >= n && incx == 1
        && a != null && a.length >= n * n && lda == n) {
      if (alpha != 0.) {
        for (int row = 0; row < n; row += 1) {
          int col = 0;
          DoubleVector valphaxrow = DoubleVector.broadcast(DMAX, alpha * x[row]);
          for (; col < DMAX.loopBound(row + 1); col += DMAX.length()) {
            DoubleVector vx = DoubleVector.fromArray(DMAX, x, col);
            DoubleVector va = DoubleVector.fromArray(DMAX, a, col + row * n);
            vx.fma(valphaxrow, va).intoArray(a, col + row * n);
          }
          for (; col < row + 1; col += 1) {
            a[col + row * n] += alpha * x[row] * x[col];
          }
        }
      }
    } else {
      super.dsyr(uplo, n, alpha, x, incx, a, lda);
    }
  }

  // y = alpha * A * x + beta * y
  @Override
  public void dgemv(String trans, int m, int n, double alpha, double[] a, int lda,
      double[] x, int incx, double beta, double[] y, int incy) {
    if ("T".equals(trans)
        && m >= 0 && n >= 0
        && lda == m && a != null && a.length >= m * n
        && x != null && x.length >= m && incx == 1
        && y != null && y.length >= n && incy == 1) {
      if (alpha != 0. || beta != 1.) {
        for (int col = 0; col < n; col += 1) {
          double sum = 0.;
          int row = 0;
          DoubleVector vsum = DoubleVector.zero(DMAX);
          for (; row < DMAX.loopBound(m); row += DMAX.length()) {
            DoubleVector vx = DoubleVector.fromArray(DMAX, x, row);
            DoubleVector va = DoubleVector.fromArray(DMAX, a, row + col * m);
            vsum = va.fma(vx, vsum);
          }
          sum += vsum.reduceLanes(VectorOperators.ADD);
          for (; row < m; row += 1) {
            sum += x[row] * a[row + col * m];
          }
          y[col] = alpha * sum + beta * y[col];
        }
      }
    } else {
      super.dgemv(trans, m, n, alpha, a, lda, x, incx, beta, y, incy);
    }
  }

  // y = alpha * A * x + beta * y
  @Override
  public void sgemv(String trans, int m, int n, float alpha, float[] a, int lda,
      float[] x, int incx, float beta, float[] y, int incy) {
    if ("T".equals(trans)
        && m >= 0 && n >= 0
        && a != null && a.length >= m * n && lda == m
        && x != null && x.length >= m && incx == 1
        && y != null && y.length >= n && incy == 1) {
      // y = beta * y
      sscal(n, beta, y, 1);
      // y += alpha * A * x
      if (alpha != 0.) {
        FloatVector valpha = FloatVector.broadcast(FMAX, alpha);
        for (int col = 0; col < n; col += 1) {
          int row = 0;
          FloatVector vsum = FloatVector.zero(FMAX);
          for (; row < FMAX.loopBound(m); row += FMAX.length()) {
            FloatVector vx = FloatVector.fromArray(FMAX, x, row);
            FloatVector va = FloatVector.fromArray(FMAX, a, row + col * m);
            vsum = valpha.mul(va).fma(vx, vsum);
          }
          y[col] += vsum.reduceLanes(VectorOperators.ADD);
          for (; row < m; row += 1) {
            y[col] += alpha * x[row] * a[row + col * m];
          }
        }
      }
    } else {
      super.sgemv(trans, m, n, alpha, a, lda, x, incx, beta, y, incy);
    }
  }

  // c = alpha * a * b + beta * c
  @Override
  public void dgemm(String transa, String transb, int m, int n, int k, double alpha,
      double[] a, int lda, double[] b, int ldb, double beta, double[] c, int ldc) {
    // System.out.println(String.format("dgemm(transa=%s, transb=%s, m=%s, n=%s, k=%s, alpha=%s, a=%s, lda=%s, b=%s, ldb=%s, beta=%s, c=%s, ldc=%s)",
    //     transa, transb, m, n, k, alpha, a, lda, b, ldb, beta, c, ldc));
    if ("N".equals(transa) && "N".equals(transb)
        && m >= 0 && n >= 0 && k >= 0
        && a != null && a.length >= m * k && lda == m
        && b != null && b.length >= k * n && ldb == k
        && c != null && c.length >= m * n && ldc == m) {
      // C = beta * C
      dscal(m * n, beta, c, 1);
      // C += alpha * A * B
      if (alpha != 0.) {
        DoubleVector valpha = DoubleVector.broadcast(DMAX, alpha);
        for (int col = 0; col < n; col += 1) {
          for (int i = 0; i < k; i += 1) {
            int row = 0;
            for (; row < DMAX.loopBound(m); row += DMAX.length()) {
              DoubleVector va = DoubleVector.fromArray(DMAX, a, i * m + row);
              DoubleVector vc = DoubleVector.fromArray(DMAX, c, col * m + row);
              valpha.lanewise(VectorOperators.MUL, b[col * k + i]).fma(va, vc)
                    .intoArray(c, col * m + row);
            }
            for (; row < m; row += 1) {
              c[col * m + row] += alpha * a[i * m + row] * b[col * k + i];
            }
          }
        }
      }
    } else if ("N".equals(transa) && "T".equals(transb)
        && m >= 0 && n >= 0 && k >= 0
        && a != null && a.length >= m * k && lda == m
        && b != null && b.length >= k * n && ldb == n
        && c != null && c.length >= m * n && ldc == m) {
      // C = beta * C
      dscal(m * n, beta, c, 1);
      // C += alpha * A * B
      if (alpha != 0.) {
        DoubleVector valpha = DoubleVector.broadcast(DMAX, alpha);
        for (int i = 0; i < k; i += 1) {
          for (int col = 0; col < n; col += 1) {
            int row = 0;
            for (; row < DMAX.loopBound(m); row += DMAX.length()) {
              DoubleVector va = DoubleVector.fromArray(DMAX, a, i * m + row);
              DoubleVector vc = DoubleVector.fromArray(DMAX, c, col * m + row);
              valpha.lanewise(VectorOperators.MUL, b[col + i * n]).fma(va, vc)
                    .intoArray(c, col * m + row);
            }
            for (; row < m; row += 1) {
              c[col * m + row] += alpha * a[i * m + row] * b[col + i * n];
            }
          }
        }
      }
    } else if ("T".equals(transa) && "N".equals(transb)
        && m >= 0 && n >= 0 && k >= 0
        && a != null && a.length >= m * k && lda == k
        && b != null && b.length >= k * n && ldb == k
        && c != null && c.length >= m * n && ldc == m) {
      if (alpha != 0. || beta != 1.) {
        for (int col = 0; col < n; col += 1) {
          for (int row = 0; row < m; row += 1) {
            double sum = 0.;
            int i = 0;
            DoubleVector vsum = DoubleVector.zero(DMAX);
            for (; i < DMAX.loopBound(k); i += DMAX.length()) {
              DoubleVector va = DoubleVector.fromArray(DMAX, a, i + row * k);
              DoubleVector vb = DoubleVector.fromArray(DMAX, b, col * k + i);
              vsum = va.fma(vb, vsum);
            }
            sum += vsum.reduceLanes(VectorOperators.ADD);
            for (; i < k; i += 1) {
              sum += a[i + row * k] * b[col * k + i];
            }
            if (beta != 0.) {
              c[col * m + row] = alpha * sum + beta * c[col * m + row];
            } else {
              c[col * m + row] = alpha * sum;
            }
          }
        }
      }
    } else {
      super.dgemm(transa, transb, m, n, k, alpha, a, lda, b, ldb, beta, c, ldc);
    }
  }
}
