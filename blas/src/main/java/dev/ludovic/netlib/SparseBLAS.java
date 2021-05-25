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

package dev.ludovic.netlib;

public interface SparseBLAS {

  public static SparseBLAS getInstance() {
    return InstanceBuilder.SparseBLAS.getInstance();
  }

  public default void daxpyi(int n, double alpha, double[] x, int[] indx, double[] y) {
    daxpyi(n, alpha, x, 0, indx, 0, y, 0);
  }

  public void daxpyi(int n, double alpha, double[] x, int offsetx, int[] indx, int offsetindx, double[] y, int offsety);

  public default void saxpyi(int n, float alpha, float[] x, int[] indx, float[] y) {
    saxpyi(n, alpha, x, 0, indx, 0, y, 0);
  }

  public void saxpyi(int n, float alpha, float[] x, int offsetx, int[] indx, int offsetindx, float[] y, int offsety);

  public default double ddoti(int n, double[] x, int[] indx, double[] y) {
    return ddoti(n, x, 0, indx, 0, y, 0);
  }

  public double ddoti(int n, double[] x, int offsetx, int[] indx, int offsetindx, double[] y, int offsety);

  public default float sdoti(int n, float[] x, int[] indx, float[] y) {
    return sdoti(n, x, 0, indx, 0, y, 0);
  }

  public float sdoti(int n, float[] x, int offsetx, int[] indx, int offsetindx, float[] y, int offsety);

  public default double ddotii(int n, double[] x, int[] indx, double[] y, int[] indy) {
    return ddotii(n, x, 0, indx, 0, y, 0, indy, 0);
  }

  public double ddotii(int n, double[] x, int offsetx, int[] indx, int offsetindx, double[] y, int offsety, int[] indy, int offsetindy);

  public default float sdotii(int n, float[] x, int[] indx, float[] y, int[] indy) {
    return sdotii(n, x, 0, indx, 0, y, 0, indy, 0);
  }

  public float sdotii(int n, float[] x, int offsetx, int[] indx, int offsetindx, float[] y, int offsety, int[] indy, int offsetindy);

  public default void dcopyi(int n, double[] x, int[] indx, double[] y) {
    dcopyi(n, x, 0, indx, 0, y, 0);
  }

  public void dcopyi(int n, double[] x, int offsetx, int[] indx, int offsetindx, double[] y, int offsety);

  public default void scopyi(int n, float[] x, int[] indx, float[] y) {
    scopyi(n, x, 0, indx, 0, y, 0);
  }

  public void scopyi(int n, float[] x, int offsetx, int[] indx, int offsetindx, float[] y, int offsety);

  public default void dspri(String uplo, int n, double alpha, double[] x, int[] indx, double[] a) {
    dspri(uplo, n, alpha, x, 0, indx, 0, a, 0);
  }

  public void dspri(String uplo, int n, double alpha, double[] x, int offsetx, int[] indx, int offsetindx, double[] a, int offseta);

  public default void sspri(String uplo, int n, float alpha, float[] x, int[] indx, float[] a) {
    sspri(uplo, n, alpha, x, 0, indx, 0, a, 0);
  }

  public void sspri(String uplo, int n, float alpha, float[] x, int offsetx, int[] indx, int offsetindx, float[] a, int offseta);
}
