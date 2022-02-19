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

public interface NativeARPACK extends ARPACK {

  public static NativeARPACK getInstance() {
    return dev.ludovic.netlib.arpack.InstanceBuilder.NativeARPACK.getInstance();
  }

  boolean has_dmout();
  boolean has_smout();
  boolean has_dvout();
  boolean has_svout();
  boolean has_ivout();
  boolean has_dgetv0();
  boolean has_sgetv0();
  boolean has_dlaqrb();
  boolean has_slaqrb();
  boolean has_dnaitr();
  boolean has_snaitr();
  boolean has_dnapps();
  boolean has_snapps();
  boolean has_dnaup2();
  boolean has_snaup2();
  boolean has_dnaupd();
  boolean has_snaupd();
  boolean has_dnconv();
  boolean has_snconv();
  boolean has_dsconv();
  boolean has_ssconv();
  boolean has_dneigh();
  boolean has_sneigh();
  boolean has_dneupd();
  boolean has_sneupd();
  boolean has_dngets();
  boolean has_sngets();
  boolean has_dsaitr();
  boolean has_ssaitr();
  boolean has_dsapps();
  boolean has_ssapps();
  boolean has_dsaup2();
  boolean has_ssaup2();
  boolean has_dseigt();
  boolean has_sseigt();
  boolean has_dsesrt();
  boolean has_ssesrt();
  boolean has_dsaupd();
  boolean has_ssaupd();
  boolean has_dseupd();
  boolean has_sseupd();
  boolean has_dsgets();
  boolean has_ssgets();
  boolean has_dsortc();
  boolean has_ssortc();
  boolean has_dsortr();
  boolean has_ssortr();
  boolean has_dstatn();
  boolean has_sstatn();
  boolean has_dstats();
  boolean has_sstats();
  boolean has_dstqrb();
  boolean has_sstqrb();
  boolean has_icnteq();
  boolean has_icopy();
  boolean has_iset();
  boolean has_iswap();
  boolean has_second();
}
