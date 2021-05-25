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

import dev.ludovic.netlib.SparseBLAS;

abstract class AbstractSparseBLAS extends AbstractBLAS implements SparseBLAS {

  private final static boolean debug = System.getProperty("dev.ludovic.netlib.blas.debug", "false").equals("true");

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

  public void daxpyi(int n, double alpha, double[] x, int offsetx, int[] indx, int offsetindx, double[] y, int offsety) {
    if (debug) System.err.println("daxpyi");
    if (n <= 0) {
      return;
    }
    if (alpha == 0.0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(indx);
    requireNonNull(y);
    checkIndex(offsetx + n - 1, x.length);
    checkIndex(offsetindx + n - 1, indx.length);
    checkIndex(offsety + indx[offsetindx + n - 1], y.length);
    daxpyiK(n, alpha, x, offsetx, indx, offsetindx, y, offsety);
  }

  protected abstract void daxpyiK(int n, double alpha, double[] x, int offsetx, int[] indx, int offsetindx, double[] y, int offsety);

  public void saxpyi(int n, float alpha, float[] x, int offsetx, int[] indx, int offsetindx, float[] y, int offsety) {
    if (debug) System.err.println("saxpyi");
    if (n <= 0) {
      return;
    }
    if (alpha == 0.0f) {
      return;
    }
    requireNonNull(x);
    requireNonNull(indx);
    requireNonNull(y);
    checkIndex(offsetx + n - 1, x.length);
    checkIndex(offsetindx + n - 1, indx.length);
    checkIndex(offsety + indx[offsetindx + n - 1], y.length);
    saxpyiK(n, alpha, x, offsetx, indx, offsetindx, y, offsety);
  }

  protected abstract void saxpyiK(int n, float alpha, float[] x, int offsetx, int[] indx, int offsetindx, float[] y, int offsety);

  public double ddoti(int n, double[] x, int offsetx, int[] indx, int offsetindx, double[] y, int offsety) {
    if (debug) System.err.println("ddoti");
    if (n <= 0) {
      return 0.0;
    }
    requireNonNull(x);
    requireNonNull(indx);
    requireNonNull(y);
    checkIndex(offsetx + n - 1, x.length);
    checkIndex(offsetindx + n - 1, indx.length);
    checkIndex(offsety + indx[offsetindx + n - 1], y.length);
    return ddotiK(n, x, offsetx, indx, offsetindx, y, offsety);
  }

  protected abstract double ddotiK(int n, double[] x, int offsetx, int[] indx, int offsetindx, double[] y, int offsety);

  public float sdoti(int n, float[] x, int offsetx, int[] indx, int offsetindx, float[] y, int offsety) {
    if (debug) System.err.println("sdoti");
    if (n <= 0) {
      return 0.0f;
    }
    requireNonNull(x);
    requireNonNull(indx);
    requireNonNull(y);
    checkIndex(offsetx + n - 1, x.length);
    checkIndex(offsetindx + n - 1, indx.length);
    checkIndex(offsety + indx[offsetindx + n - 1], y.length);
    return sdotiK(n, x, offsetx, indx, offsetindx, y, offsety);
  }

  protected abstract float sdotiK(int n, float[] x, int offsetx, int[] indx, int offsetindx, float[] y, int offsety);

  public double ddotii(int n, double[] x, int offsetx, int[] indx, int offsetindx, double[] y, int offsety, int[] indy, int offsetindy) {
    if (debug) System.err.println("ddotii");
    if (n <= 0) {
      return 0.0;
    }
    requireNonNull(x);
    requireNonNull(indx);
    requireNonNull(y);
    requireNonNull(indy);
    checkIndex(offsetx + n - 1, x.length);
    checkIndex(offsetindx + n - 1, indx.length);
    checkIndex(offsety + n - 1, y.length);
    checkIndex(offsetindy + n - 1, indy.length);
    return ddotiiK(n, x, offsetx, indx, offsetindx, y, offsety, indy, offsetindy);
  }

  protected abstract double ddotiiK(int n, double[] x, int offsetx, int[] indx, int offsetindx, double[] y, int offsety, int[] indy, int offsetindy);

  public float sdotii(int n, float[] x, int offsetx, int[] indx, int offsetindx, float[] y, int offsety, int[] indy, int offsetindy) {
    if (debug) System.err.println("sdotii");
    if (n <= 0) {
      return 0.0f;
    }
    requireNonNull(x);
    requireNonNull(indx);
    requireNonNull(y);
    requireNonNull(indy);
    checkIndex(offsetx + n - 1, x.length);
    checkIndex(offsetindx + n - 1, indx.length);
    checkIndex(offsety + n - 1, y.length);
    checkIndex(offsetindy + n - 1, indy.length);
    return sdotiiK(n, x, offsetx, indx, offsetindx, y, offsety, indy, offsetindy);
  }

  protected abstract float sdotiiK(int n, float[] x, int offsetx, int[] indx, int offsetindx, float[] y, int offsety, int[] indy, int offsetindy);

  public void dcopyi(int n, double[] x, int offsetx, int[] indx, int offsetindx, double[] y, int offsety) {
    if (debug) System.err.println ("dcopyi");
    if (n <= 0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(indx);
    requireNonNull(y);
    checkIndex(offsetx + n - 1, x.length);
    checkIndex(offsetindx + n - 1, indx.length);
    checkIndex(offsety + indx[offsetindx + n - 1], y.length);
    dcopyiK(n, x, offsetx, indx, offsetindx, y, offsety);
  }

  protected abstract void dcopyiK(int n, double[] x, int offsetx, int[] indx, int offsetindx, double[] y, int offsety);

  public void scopyi(int n, float[] x, int offsetx, int[] indx, int offsetindx, float[] y, int offsety) {
    if (debug) System.err.println ("scopyi");
    if (n <= 0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(indx);
    requireNonNull(y);
    checkIndex(offsetx + n - 1, x.length);
    checkIndex(offsetindx + n - 1, indx.length);
    checkIndex(offsety + indx[offsetindx + n - 1], y.length);
    scopyiK(n, x, offsetx, indx, offsetindx, y, offsety);
  }

  protected abstract void scopyiK(int n, float[] x, int offsetx, int[] indx, int offsetindx, float[] y, int offsety);

  public void dspri(String uplo, int n, double alpha, double[] x, int offsetx, int[] indx, int offsetindx, double[] a, int offseta) {
    if (debug) System.err.println("dspri");
    checkArgument("DSPRI", 1, lsame("U", uplo) || lsame("L", uplo));
    if (n <= 0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(indx);
    requireNonNull(a);
    checkIndex(offsetx + n - 1, x.length);
    checkIndex(offsetindx + n - 1, indx.length);
    //TODO checkIndex(offseta + ..., a.length);
    if (lsame("U", uplo))
      dspriU(n, alpha, x, offsetx, indx, offsetindx, a, offseta);
    else
      dspriL(n, alpha, x, offsetx, indx, offsetindx, a, offseta);
  }

  protected abstract void dspriU(int n, double alpha, double[] x, int offsetx, int[] indx, int offsetindx, double[] a, int offseta);
  protected abstract void dspriL(int n, double alpha, double[] x, int offsetx, int[] indx, int offsetindx, double[] a, int offseta);

  public void sspri(String uplo, int n, float alpha, float[] x, int offsetx, int[] indx, int offsetindx, float[] a, int offseta) {
    if (debug) System.err.println("sspri");
    checkArgument("SSPRI", 1, lsame("U", uplo) || lsame("L", uplo));
    if (n <= 0) {
      return;
    }
    requireNonNull(x);
    requireNonNull(indx);
    requireNonNull(a);
    checkIndex(offsetx + n - 1, x.length);
    checkIndex(offsetindx + n - 1, indx.length);
    //TODO checkIndex(offseta + ..., a.length);
    if (lsame("U", uplo))
      sspriU(n, alpha, x, offsetx, indx, offsetindx, a, offseta);
    else
      sspriL(n, alpha, x, offsetx, indx, offsetindx, a, offseta);
  }

  protected abstract void sspriU(int n, float alpha, float[] x, int offsetx, int[] indx, int offsetindx, float[] a, int offseta);
  protected abstract void sspriL(int n, float alpha, float[] x, int offsetx, int[] indx, int offsetindx, float[] a, int offseta);
}
