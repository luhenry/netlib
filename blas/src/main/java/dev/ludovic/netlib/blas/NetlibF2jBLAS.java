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

public final class NetlibF2jBLAS extends NetlibWrapper implements dev.ludovic.netlib.JavaBLAS {

  private static final NetlibF2jBLAS instance;

  static {
    instance = new NetlibF2jBLAS(new com.github.fommil.netlib.F2jBLAS());
  }

  protected NetlibF2jBLAS(com.github.fommil.netlib.BLAS _blas) {
    super(_blas);
  }

  public static dev.ludovic.netlib.JavaBLAS getInstance() {
    return instance;
  }

  protected int idamaxK(int n, double[] x, int offsetx, int incx) {
    // F2j returns 1-based index because that's how Fortran works
    return super.idamaxK(n, x, offsetx, incx) - 1;
  }
  protected int isamaxK(int n, float[] x, int offsetx, int incx) {
    // F2j returns 1-based index because that's how Fortran works
    return super.isamaxK(n, x, offsetx, incx) - 1;
  }
}
