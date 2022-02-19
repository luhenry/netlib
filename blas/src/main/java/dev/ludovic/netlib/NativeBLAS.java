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

public interface NativeBLAS extends BLAS {

  public static NativeBLAS getInstance() {
    return dev.ludovic.netlib.blas.InstanceBuilder.NativeBLAS.getInstance();
  }

  boolean has_dasum();
  boolean has_sasum();
  boolean has_daxpy();
  boolean has_saxpy();
  boolean has_dcopy();
  boolean has_scopy();
  boolean has_ddot();
  boolean has_sdot();
  boolean has_sdsdot();
  boolean has_dgbmv();
  boolean has_sgbmv();
  boolean has_dgemm();
  boolean has_sgemm();
  boolean has_dgemv();
  boolean has_sgemv();
  boolean has_dger();
  boolean has_sger();
  boolean has_dnrm2();
  boolean has_snrm2();
  boolean has_drot();
  boolean has_srot();
  boolean has_drotm();
  boolean has_srotm();
  boolean has_drotmg();
  boolean has_srotmg();
  boolean has_dsbmv();
  boolean has_ssbmv();
  boolean has_dscal();
  boolean has_sscal();
  boolean has_dspmv();
  boolean has_sspmv();
  boolean has_dspr();
  boolean has_sspr();
  boolean has_dspr2();
  boolean has_sspr2();
  boolean has_dswap();
  boolean has_sswap();
  boolean has_dsymm();
  boolean has_ssymm();
  boolean has_dsymv();
  boolean has_ssymv();
  boolean has_dsyr();
  boolean has_ssyr();
  boolean has_dsyr2();
  boolean has_ssyr2();
  boolean has_dsyr2k();
  boolean has_ssyr2k();
  boolean has_dsyrk();
  boolean has_ssyrk();
  boolean has_dtbmv();
  boolean has_stbmv();
  boolean has_dtbsv();
  boolean has_stbsv();
  boolean has_dtpmv();
  boolean has_stpmv();
  boolean has_dtpsv();
  boolean has_stpsv();
  boolean has_dtrmm();
  boolean has_strmm();
  boolean has_dtrmv();
  boolean has_strmv();
  boolean has_dtrsm();
  boolean has_strsm();
  boolean has_dtrsv();
  boolean has_strsv();
  boolean has_idamax();
  boolean has_isamax();
}
