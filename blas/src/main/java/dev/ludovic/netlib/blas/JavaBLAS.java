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

public interface JavaBLAS extends BLAS {

  public static JavaBLAS getInstance() {
    return InstanceBuilder.javaBlas();
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

  public default void dsyri(String uplo, int n, double alpha, double[] x, int[] indx, double[] a, int lda) {
    dsyri(uplo, n, alpha, x, 0, indx, 0, a, 0, lda);
  }

  public void dsyri(String uplo, int n, double alpha, double[] x, int offsetx, int[] indx, int offsetindx, double[] a, int offseta, int lda);

  public default void ssyri(String uplo, int n, float alpha, float[] x, int[] indx, float[] a, int lda) {
    ssyri(uplo, n, alpha, x, 0, indx, 0, a, 0, lda);
  }

  public void ssyri(String uplo, int n, float alpha, float[] x, int offsetx, int[] indx, int offsetindx, float[] a, int offseta, int lda);

  public default void dgemvi(String trans, int m, int n, double alpha, double[] a, int lda, double[] x, int[] indx, int offsetindx, double beta, double[] y, int incy) {
    dgemvi(trans, m, n, alpha, a, 0, lda, x, 0, indx, offsetindx, beta, y, 0, incy);
  }

  public void dgemvi(String trans, int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int[] indx, int offsetindx, double beta, double[] y, int offsety, int incy);

  public default void sgemvi(String trans, int m, int n, float alpha, float[] a, int lda, float[] x, int[] indx, int offsetindx, float beta, float[] y, int incy) {
    sgemvi(trans, m, n, alpha, a, 0, lda, x, 0, indx, offsetindx, beta, y, 0, incy);
  }

  public void sgemvi(String trans, int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int[] indx, int offsetindx, float beta, float[] y, int offsety, int incy);

  public default void dgemvi(String trans, int m, int n, double alpha, double[] a, int[] inda, int[] ptra, double[] x, int incx, double beta, double[] y, int incy) {
    dgemvi(trans, m, n, alpha, a, 0, inda, 0, ptra, 0, x, 0, incx, beta, y, 0, incy);
  }

  public void dgemvi(String trans, int m, int n, double alpha, double[] a, int offseta, int[] inda, int offsetinda, int[] ptra, int offsetptra, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy);

  public default void sgemvi(String trans, int m, int n, float alpha, float[] a, int[] inda, int[] ptra, float[] x, int incx, float beta, float[] y, int incy) {
    sgemvi(trans, m, n, alpha, a, 0, inda, 0, ptra, 0, x, 0, incx, beta, y, 0, incy);
  }

  public void sgemvi(String trans, int m, int n, float alpha, float[] a, int offseta, int[] inda, int offsetinda, int[] ptra, int offsetptra, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy);

  public default void dgemvii(String trans, int m, int n, double alpha, double[] a, int[] inda, int[] ptra, double[] x, int[] indx, double beta, double[] y, int incy) {
    dgemvii(trans, m, n, alpha, a, 0, inda, 0, ptra, 0, x, 0, indx, 0, beta, y, 0, incy);
  }

  public void dgemvii(String trans, int m, int n, double alpha, double[] a, int offseta, int[] inda, int offsetinda, int[] ptra, int offsetptra, double[] x, int offsetx, int[] indx, int offsetindx, double beta, double[] y, int offsety, int incy);

  public default void sgemvii(String trans, int m, int n, float alpha, float[] a, int[] inda, int[] ptra, float[] x, int[] indx, float beta, float[] y, int incy) {
    sgemvii(trans, m, n, alpha, a, 0, inda, 0, ptra, 0, x, 0, indx, 0, beta, y, 0, incy);
  }

  public void sgemvii(String trans, int m, int n, float alpha, float[] a, int offseta, int[] inda, int offsetinda, int[] ptra, int offsetptra, float[] x, int offsetx, int[] indx, int offsetindx, float beta, float[] y, int offsety, int incy);

  public default void dgemmi(String transa, String transb, int m, int n, int k, double alpha, double[] a, int[] inda, int[] ptra, double[] b, int ldb, double beta, double[] c, int ldc) {
    dgemmi(transa, transb, m, n, k, alpha, a, 0, inda, 0, ptra, 0, b, 0, ldb, beta, c, 0, ldc);
  }

  public void dgemmi(String transa, String transb, int m, int n, int k, double alpha, double[] a, int offseta, int[] inda, int offsetinda, int[] ptra, int offsetptra, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc);

  public default void sgemmi(String transa, String transb, int m, int n, int k, float alpha, float[] a, int[] inda, int[] ptra, float[] b, int ldb, float beta, float[] c, int Ldc) {
    sgemmi(transa, transb, m, n, k, alpha, a, 0, inda, 0, ptra, 0, b, 0, ldb, beta, c, 0, Ldc);
  }

  public void sgemmi(String transa, String transb, int m, int n, int k, float alpha, float[] a, int offseta, int[] inda, int offsetinda, int[] ptra, int offsetptra, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int Ldc);
}
