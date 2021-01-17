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

package dev.ludovic.netlib;

import dev.ludovic.netlib.blas.JavaARPACK;

public interface ARPACK {

  public static ARPACK getInstance() {
    return JavaARPACK.getInstance();
  }

  public void dmout(int, int, int, double[], int, int, String);
  public void dmout(int, int, int, double[], int, int, int, String);

  public void smout(int, int, int, float[], int, int, String);
  public void smout(int, int, int, float[], int, int, int, String);

  public void dvout(int, int, double[], int, String);
  public void dvout(int, int, double[], int, int, String);

  public void svout(int, int, float[], int, String);
  public void svout(int, int, float[], int, int, String);

  public void dgetv0(org.netlib.util.intW, String, int, boolean, int, int, double[], int, double[], org.netlib.util.doubleW, int[], double[], org.netlib.util.intW);
  public void dgetv0(org.netlib.util.intW, String, int, boolean, int, int, double[], int, int, double[], int, org.netlib.util.doubleW, int[], int, double[], int, org.netlib.util.intW);

  public void sgetv0(org.netlib.util.intW, String, int, boolean, int, int, float[], int, float[], org.netlib.util.floatW, int[], float[], org.netlib.util.intW);
  public void sgetv0(org.netlib.util.intW, String, int, boolean, int, int, float[], int, int, float[], int, org.netlib.util.floatW, int[], int, float[], int, org.netlib.util.intW);

  public void dlaqrb(boolean, int, int, int, double[], int, double[], double[], double[], org.netlib.util.intW);
  public void dlaqrb(boolean, int, int, int, double[], int, int, double[], int, double[], int, double[], int, org.netlib.util.intW);

  public void slaqrb(boolean, int, int, int, float[], int, float[], float[], float[], org.netlib.util.intW);
  public void slaqrb(boolean, int, int, int, float[], int, int, float[], int, float[], int, float[], int, org.netlib.util.intW);

  public void dnaitr(org.netlib.util.intW, String, int, int, int, int, double[], int, org.netlib.util.doubleW, double[], int, int, double[], int, int, int[], int, double[], int, org.netlib.util.intW);
  public void dnaitr(org.netlib.util.intW, String, int, int, int, int, double[], org.netlib.util.doubleW, double[], int, double[], int, int[], double[], org.netlib.util.intW);

  public void snaitr(org.netlib.util.intW, String, int, int, int, int, float[], int, org.netlib.util.floatW, float[], int, int, float[], int, int, int[], int, float[], int, org.netlib.util.intW);
  public void snaitr(org.netlib.util.intW, String, int, int, int, int, float[], org.netlib.util.floatW, float[], int, float[], int, int[], float[], org.netlib.util.intW);

  public void dnapps(int, org.netlib.util.intW, int, double[], double[], double[], int, double[], int, double[], double[], int, double[], double[]);
  public void dnapps(int, org.netlib.util.intW, int, double[], int, double[], int, double[], int, int, double[], int, int, double[], int, double[], int, int, double[], int, double[], int);

  public void snapps(int, org.netlib.util.intW, int, float[], float[], float[], int, float[], int, float[], float[], int, float[], float[]);
  public void snapps(int, org.netlib.util.intW, int, float[], int, float[], int, float[], int, int, float[], int, int, float[], int, float[], int, int, float[], int, float[], int);

  public void dnaup2(org.netlib.util.intW, String, int, String, org.netlib.util.intW, org.netlib.util.intW, double, double[], int, int, int, int, org.netlib.util.intW, double[], int, int, double[], int, int, double[], int, double[], int, double[], int, double[], int, int, double[], int, int[], int, double[], int, org.netlib.util.intW);
  public void dnaup2(org.netlib.util.intW, String, int, String, org.netlib.util.intW, org.netlib.util.intW, double, double[], int, int, int, org.netlib.util.intW, double[], int, double[], int, double[], double[], double[], double[], int, double[], int[], double[], org.netlib.util.intW);

  public void snaup2(org.netlib.util.intW, String, int, String, org.netlib.util.intW, org.netlib.util.intW, float, float[], int, int, int, int, org.netlib.util.intW, float[], int, int, float[], int, int, float[], int, float[], int, float[], int, float[], int, int, float[], int, int[], int, float[], int, org.netlib.util.intW);
  public void snaup2(org.netlib.util.intW, String, int, String, org.netlib.util.intW, org.netlib.util.intW, float, float[], int, int, int, org.netlib.util.intW, float[], int, float[], int, float[], float[], float[], float[], int, float[], int[], float[], org.netlib.util.intW);

  public void dnaupd(org.netlib.util.intW, String, int, String, int, org.netlib.util.doubleW, double[], int, double[], int, int[], int[], double[], double[], int, org.netlib.util.intW);
  public void dnaupd(org.netlib.util.intW, String, int, String, int, org.netlib.util.doubleW, double[], int, int, double[], int, int, int[], int, int[], int, double[], int, double[], int, int, org.netlib.util.intW);

  public void snaupd(org.netlib.util.intW, String, int, String, int, org.netlib.util.floatW, float[], int, float[], int, int[], int[], float[], float[], int, org.netlib.util.intW);
  public void snaupd(org.netlib.util.intW, String, int, String, int, org.netlib.util.floatW, float[], int, int, float[], int, int, int[], int, int[], int, float[], int, float[], int, int, org.netlib.util.intW);

  public void dnconv(int, double[], double[], double[], double, org.netlib.util.intW);
  public void dnconv(int, double[], int, double[], int, double[], int, double, org.netlib.util.intW);

  public void snconv(int, float[], float[], float[], float, org.netlib.util.intW);
  public void snconv(int, float[], int, float[], int, float[], int, float, org.netlib.util.intW);

  public void dneigh(double, org.netlib.util.intW, double[], int, double[], double[], double[], double[], int, double[], org.netlib.util.intW);
  public void dneigh(double, org.netlib.util.intW, double[], int, int, double[], int, double[], int, double[], int, double[], int, int, double[], int, org.netlib.util.intW);

  public void sneigh(float, org.netlib.util.intW, float[], int, float[], float[], float[], float[], int, float[], org.netlib.util.intW);
  public void sneigh(float, org.netlib.util.intW, float[], int, int, float[], int, float[], int, float[], int, float[], int, int, float[], int, org.netlib.util.intW);

  public void dneupd(boolean, String, boolean[], double[], double[], double[], int, double, double, double[], String, int, String, org.netlib.util.intW, double, double[], int, double[], int, int[], int[], double[], double[], int, org.netlib.util.intW);
  public void dneupd(boolean, String, boolean[], int, double[], int, double[], int, double[], int, int, double, double, double[], int, String, int, String, org.netlib.util.intW, double, double[], int, int, double[], int, int, int[], int, int[], int, double[], int, double[], int, int, org.netlib.util.intW);

  public void sneupd(boolean, String, boolean[], float[], float[], float[], int, float, float, float[], String, int, String, org.netlib.util.intW, float, float[], int, float[], int, int[], int[], float[], float[], int, org.netlib.util.intW);
  public void sneupd(boolean, String, boolean[], int, float[], int, float[], int, float[], int, int, float, float, float[], int, String, int, String, org.netlib.util.intW, float, float[], int, int, float[], int, int, int[], int, int[], int, float[], int, float[], int, int, org.netlib.util.intW);

  public void dngets(int, String, org.netlib.util.intW, org.netlib.util.intW, double[], double[], double[], double[], double[]);
  public void dngets(int, String, org.netlib.util.intW, org.netlib.util.intW, double[], int, double[], int, double[], int, double[], int, double[], int);

  public void sngets(int, String, org.netlib.util.intW, org.netlib.util.intW, float[], float[], float[], float[], float[]);
  public void sngets(int, String, org.netlib.util.intW, org.netlib.util.intW, float[], int, float[], int, float[], int, float[], int, float[], int);

  public void dsaitr(org.netlib.util.intW, String, int, int, int, int, double[], int, org.netlib.util.doubleW, double[], int, int, double[], int, int, int[], int, double[], int, org.netlib.util.intW);
  public void dsaitr(org.netlib.util.intW, String, int, int, int, int, double[], org.netlib.util.doubleW, double[], int, double[], int, int[], double[], org.netlib.util.intW);

  public void ssaitr(org.netlib.util.intW, String, int, int, int, int, float[], int, org.netlib.util.floatW, float[], int, int, float[], int, int, int[], int, float[], int, org.netlib.util.intW);
  public void ssaitr(org.netlib.util.intW, String, int, int, int, int, float[], org.netlib.util.floatW, float[], int, float[], int, int[], float[], org.netlib.util.intW);

  public void dsapps(int, int, int, double[], double[], int, double[], int, double[], double[], int, double[]);
  public void dsapps(int, int, int, double[], int, double[], int, int, double[], int, int, double[], int, double[], int, int, double[], int);

  public void ssapps(int, int, int, float[], float[], int, float[], int, float[], float[], int, float[]);
  public void ssapps(int, int, int, float[], int, float[], int, int, float[], int, int, float[], int, float[], int, int, float[], int);

  public void dsaup2(org.netlib.util.intW, String, int, String, org.netlib.util.intW, org.netlib.util.intW, double, double[], int, int, int, int, org.netlib.util.intW, double[], int, int, double[], int, int, double[], int, double[], int, double[], int, int, double[], int, int[], int, double[], int, org.netlib.util.intW);
  public void dsaup2(org.netlib.util.intW, String, int, String, org.netlib.util.intW, org.netlib.util.intW, double, double[], int, int, int, org.netlib.util.intW, double[], int, double[], int, double[], double[], double[], int, double[], int[], double[], org.netlib.util.intW);

  public void ssaup2(org.netlib.util.intW, String, int, String, org.netlib.util.intW, org.netlib.util.intW, float, float[], int, int, int, int, org.netlib.util.intW, float[], int, int, float[], int, int, float[], int, float[], int, float[], int, int, float[], int, int[], int, float[], int, org.netlib.util.intW);
  public void ssaup2(org.netlib.util.intW, String, int, String, org.netlib.util.intW, org.netlib.util.intW, float, float[], int, int, int, org.netlib.util.intW, float[], int, float[], int, float[], float[], float[], int, float[], int[], float[], org.netlib.util.intW);

  public void dsaupd(org.netlib.util.intW, String, int, String, int, org.netlib.util.doubleW, double[], int, double[], int, int[], int[], double[], double[], int, org.netlib.util.intW);
  public void dsaupd(org.netlib.util.intW, String, int, String, int, org.netlib.util.doubleW, double[], int, int, double[], int, int, int[], int, int[], int, double[], int, double[], int, int, org.netlib.util.intW);

  public void ssaupd(org.netlib.util.intW, String, int, String, int, org.netlib.util.floatW, float[], int, float[], int, int[], int[], float[], float[], int, org.netlib.util.intW);
  public void ssaupd(org.netlib.util.intW, String, int, String, int, org.netlib.util.floatW, float[], int, int, float[], int, int, int[], int, int[], int, float[], int, float[], int, int, org.netlib.util.intW);

  public void dsconv(int, double[], double[], double, org.netlib.util.intW);
  public void dsconv(int, double[], int, double[], int, double, org.netlib.util.intW);

  public void ssconv(int, float[], float[], float, org.netlib.util.intW);
  public void ssconv(int, float[], int, float[], int, float, org.netlib.util.intW);

  public void dseigt(double, int, double[], int, double[], double[], double[], org.netlib.util.intW);
  public void dseigt(double, int, double[], int, int, double[], int, double[], int, double[], int, org.netlib.util.intW);

  public void sseigt(float, int, float[], int, float[], float[], float[], org.netlib.util.intW);
  public void sseigt(float, int, float[], int, int, float[], int, float[], int, float[], int, org.netlib.util.intW);

  public void dsesrt(String, boolean, int, double[], int, double[], int);
  public void dsesrt(String, boolean, int, double[], int, int, double[], int, int);

  public void ssesrt(String, boolean, int, float[], int, float[], int);
  public void ssesrt(String, boolean, int, float[], int, int, float[], int, int);

  public void dseupd(boolean, String, boolean[], double[], double[], int, double, String, int, String, org.netlib.util.intW, double, double[], int, double[], int, int[], int[], double[], double[], int, org.netlib.util.intW);
  public void dseupd(boolean, String, boolean[], int, double[], int, double[], int, int, double, String, int, String, org.netlib.util.intW, double, double[], int, int, double[], int, int, int[], int, int[], int, double[], int, double[], int, int, org.netlib.util.intW);

  public void sseupd(boolean, String, boolean[], float[], float[], int, float, String, int, String, org.netlib.util.intW, float, float[], int, float[], int, int[], int[], float[], float[], int, org.netlib.util.intW);
  public void sseupd(boolean, String, boolean[], int, float[], int, float[], int, int, float, String, int, String, org.netlib.util.intW, float, float[], int, int, float[], int, int, int[], int, int[], int, float[], int, float[], int, int, org.netlib.util.intW);

  public void dsgets(int, String, org.netlib.util.intW, org.netlib.util.intW, double[], double[], double[]);
  public void dsgets(int, String, org.netlib.util.intW, org.netlib.util.intW, double[], int, double[], int, double[], int);

  public void ssgets(int, String, org.netlib.util.intW, org.netlib.util.intW, float[], float[], float[]);
  public void ssgets(int, String, org.netlib.util.intW, org.netlib.util.intW, float[], int, float[], int, float[], int);

  public void dsortc(String, boolean, int, double[], double[], double[]);
  public void dsortc(String, boolean, int, double[], int, double[], int, double[], int);

  public void ssortc(String, boolean, int, float[], float[], float[]);
  public void ssortc(String, boolean, int, float[], int, float[], int, float[], int);

  public void dsortr(String, boolean, int, double[], double[]);
  public void dsortr(String, boolean, int, double[], int, double[], int);

  public void ssortr(String, boolean, int, float[], float[]);
  public void ssortr(String, boolean, int, float[], int, float[], int);

  public void dstatn();

  public void sstatn();

  public void dstats();

  public void sstats();

  public void dstqrb(int, double[], double[], double[], double[], org.netlib.util.intW);
  public void dstqrb(int, double[], int, double[], int, double[], int, double[], int, org.netlib.util.intW);

  public void sstqrb(int, float[], float[], float[], float[], org.netlib.util.intW);
  public void sstqrb(int, float[], int, float[], int, float[], int, float[], int, org.netlib.util.intW);

  public int icnteq(int, int[], int, int);
  public int icnteq(int, int[], int);

  public void icopy(int, int[], int, int, int[], int, int);
  public void icopy(int, int[], int, int[], int);

  public void iset(int, int, int[], int, int);
  public void iset(int, int, int[], int);

  public void iswap(int, int[], int, int, int[], int, int);
  public void iswap(int, int[], int, int[], int);

  public void ivout(int, int, int[], int, int, String);
  public void ivout(int, int, int[], int, String);

  public void second(org.netlib.util.floatW);
}
