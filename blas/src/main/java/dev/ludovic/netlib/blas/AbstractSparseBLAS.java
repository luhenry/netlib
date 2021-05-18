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
}
