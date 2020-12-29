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
  public void dgemm(String transa, String transb, int m, int n, int k,
      double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb,
      double beta, double[] c, int offsetc, int ldc) {
    if (lsame("N", transa) && lsame("N", transb)
        && m > 0 && n > 0 && k > 0
        && a != null && a.length >= offseta + m * k && lda == m
        && b != null && b.length >= offsetb + k * n && ldb == k
        && c != null && c.length >= offsetc + m * n && ldc == m) {
      // C = beta * C
      dscal(m * n, beta, c, offsetc, 1);
      // C += alpha * A * B
      if (alpha != 0.) {
        DoubleVector valpha = DoubleVector.broadcast(DMAX, alpha);
        for (int col = 0; col < n; col += 1) {
          for (int i = 0; i < k; i += 1) {
            int row = 0;
            for (; row < DMAX.loopBound(m); row += DMAX.length()) {
              DoubleVector va = DoubleVector.fromArray(DMAX, a, offseta + i * m + row);
              DoubleVector vc = DoubleVector.fromArray(DMAX, c, offsetc + col * m + row);
              valpha.mul(b[offsetb + col * k + i]).fma(va, vc)
                    .intoArray(c, offsetc + col * m + row);
            }
            for (; row < m; row += 1) {
              c[offsetc + col * m + row] += alpha * a[offseta + i * m + row] * b[offsetb + col * k + i];
            }
          }
        }
      }
    } else if (lsame("N", transa) && lsame("T", transb)
        && m > 0 && n > 0 && k > 0
        && a != null && a.length >= offseta + m * k && lda == m
        && b != null && b.length >= offsetb + k * n && ldb == n
        && c != null && c.length >= offsetc + m * n && ldc == m) {
      // C = beta * C
      dscal(m * n, beta, c, offsetc, 1);
      // C += alpha * A * B
      if (alpha != 0.) {
        DoubleVector valpha = DoubleVector.broadcast(DMAX, alpha);
        for (int i = 0; i < k; i += 1) {
          for (int col = 0; col < n; col += 1) {
            int row = 0;
            for (; row < DMAX.loopBound(m); row += DMAX.length()) {
              DoubleVector va = DoubleVector.fromArray(DMAX, a, offseta + i * m + row);
              DoubleVector vc = DoubleVector.fromArray(DMAX, c, offsetc + col * m + row);
              valpha.mul(b[offsetb + col + i * n]).fma(va, vc)
                    .intoArray(c, offsetc + col * m + row);
            }
            for (; row < m; row += 1) {
              c[offsetc + col * m + row] += alpha * a[offseta + i * m + row] * b[offsetb + col + i * n];
            }
          }
        }
      }
    } else if (lsame("T", transa) && lsame("N", transb)
        && m > 0 && n > 0 && k > 0
        && a != null && a.length >= offseta + m * k && lda == k
        && b != null && b.length >= offsetb + k * n && ldb == k
        && c != null && c.length >= offsetc + m * n && ldc == m) {
      if (alpha != 0. || beta != 1.) {
        for (int col = 0; col < n; col += 1) {
          for (int row = 0; row < m; row += 1) {
            // C = beta * C
            c[offsetc + col * m + row] = beta * c[offsetc + col * m + row];
            // C += alpha * A * B
            if (alpha != 0.) {
              int i = 0;
              DoubleVector vsum = DoubleVector.zero(DMAX);
              for (; i < DMAX.loopBound(k); i += DMAX.length()) {
                DoubleVector va = DoubleVector.fromArray(DMAX, a, offseta + i + row * k);
                DoubleVector vb = DoubleVector.fromArray(DMAX, b, offsetb + col * k + i);
                vsum = va.fma(vb, vsum);
              }
              c[offsetc + col * m + row] += alpha * vsum.reduceLanes(VectorOperators.ADD);
              for (; i < k; i += 1) {
                c[offsetc + col * m + row] += alpha * a[offseta + i + row * k] * b[offsetb + col * k + i];
              }
            }
          }
        }
      }
    } else if (lsame("T", transa) && lsame("T", transb)
        && m > 0 && n > 0 && k > 0
        && a != null && a.length >= offseta + m * k && lda == k
        && b != null && b.length >= offsetb + k * n && ldb == n
        && c != null && c.length >= offsetc + m * n && ldc == m) {
      if (alpha != 0. || beta != 1.) {
        // FIXME: do block by block
        for (int col = 0; col < n; col += 1) {
          for (int row = 0; row < m; row += 1) {
            // C = beta * C
            c[offsetc + col * m + row] = beta * c[offsetc + col * m + row];
            // C += alpha * A * B
            for (int i = 0; i < k; i += 1) {
              c[offsetc + col * m + row] += alpha * a[offseta + i + row * k] * b[offsetb + col + i * n];
            }
          }
        }
      }
    } else {
      super.dgemm(transa, transb, m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
    }
  }

  // y = alpha * A * x + beta * y
  @Override
  public void dgemv(String trans, int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
    if (lsame("N", trans)
        && m > 0 && n > 0
        && a != null && a.length >= offseta + m * n && lda == m
        && x != null && x.length >= offsetx + n && incx == 1
        && y != null && y.length >= offsety + m && incy == 1) {
      // y = beta * y
      dscal(m, beta, y, offsety, 1);
      // y += alpha * A * x
      if (alpha != 0.) {
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
    } else if (lsame("T", trans)
        && m > 0 && n > 0
        && a != null && a.length >= offseta + m * n && lda == m
        && x != null && x.length >= offsetx + m && incx == 1
        && y != null && y.length >= offsety + n && incy == 1) {
      for (int col = 0; col < n; col += 1) {
        // y = beta * y
        y[offsety + col] = beta * y[offsety + col];
        // y += alpha * A * x
        if (alpha != 0.) {
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
    } else {
      super.dgemv(trans, m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
    }
  }

  // y = alpha * A * x + beta * y
  @Override
  public void sgemv(String trans, int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
    if (lsame("N", trans)
        && m > 0 && n > 0
        && a != null && a.length >= offseta + m * n && lda == m
        && x != null && x.length >= offsetx + n && incx == 1
        && y != null && y.length >= offsety + m && incy == 1) {
      // y = beta * y
      sscal(m, beta, y, offsety, 1);
      // y += alpha * A * x
      if (alpha != 0.f) {
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
    } else if (lsame("T", trans)
        && m > 0 && n > 0
        && a != null && a.length >= offseta + m * n && lda == m
        && x != null && x.length >= offsetx + m && incx == 1
        && y != null && y.length >= offsety + n && incy == 1) {
      for (int col = 0; col < n; col += 1) {
        // y = beta * y
        y[offsety + col] = beta * y[offsety + col];
        // y += alpha * A * x
        if (alpha != 0.f) {
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
    } else {
      super.sgemv(trans, m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
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
