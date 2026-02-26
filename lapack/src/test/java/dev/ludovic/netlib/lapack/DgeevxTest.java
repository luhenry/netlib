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

package dev.ludovic.netlib.lapack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;
import org.netlib.util.*;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class DgeevxTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        double[] a_expected = generateMatrix(n, n, 1.0);
        double[] a_actual = a_expected.clone();
        double[] wr_expected = new double[n];
        double[] wr_actual = new double[n];
        double[] wi_expected = new double[n];
        double[] wi_actual = new double[n];
        double[] vl_expected = new double[n * n];
        double[] vl_actual = new double[n * n];
        double[] vr_expected = new double[n * n];
        double[] vr_actual = new double[n * n];
        intW ilo_expected = new intW(0);
        intW ilo_actual = new intW(0);
        intW ihi_expected = new intW(0);
        intW ihi_actual = new intW(0);
        double[] scale_expected = new double[n];
        double[] scale_actual = new double[n];
        doubleW abnrm_expected = new doubleW(0.0);
        doubleW abnrm_actual = new doubleW(0.0);
        double[] rconde_expected = new double[n];
        double[] rconde_actual = new double[n];
        double[] rcondv_expected = new double[n];
        double[] rcondv_actual = new double[n];
        int lwork = n * (n + 6);
        double[] work_expected = new double[lwork];
        double[] work_actual = new double[lwork];
        int[] iwork_expected = new int[2 * n - 2];
        int[] iwork_actual = new int[2 * n - 2];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        // balanc="N" for no balancing, sense="N" for no condition estimation
        f2j.dgeevx("N", "V", "V", "N", n, a_expected, 0, n,
                    wr_expected, 0, wi_expected, 0,
                    vl_expected, 0, n, vr_expected, 0, n,
                    ilo_expected, ihi_expected, scale_expected, 0,
                    abnrm_expected, rconde_expected, 0, rcondv_expected, 0,
                    work_expected, 0, lwork, iwork_expected, 0, info_expected);
        assertEquals(0, info_expected.val, "Reference dgeevx should succeed");

        lapack.dgeevx("N", "V", "V", "N", n, a_actual, 0, n,
                      wr_actual, 0, wi_actual, 0,
                      vl_actual, 0, n, vr_actual, 0, n,
                      ilo_actual, ihi_actual, scale_actual, 0,
                      abnrm_actual, rconde_actual, 0, rcondv_actual, 0,
                      work_actual, 0, lwork, iwork_actual, 0, info_actual);
        assertEquals(0, info_actual.val, "dgeevx should succeed");

        // Eigenvalues may be in different order between implementations; sort before comparing
        java.util.Arrays.sort(wr_expected);
        java.util.Arrays.sort(wr_actual);
        assertArrayEquals(wr_expected, wr_actual, depsilon * 100);
        assertEquals(ilo_expected.val, ilo_actual.val);
        assertEquals(ihi_expected.val, ihi_actual.val);
        assertEquals(abnrm_expected.val, abnrm_actual.val, depsilon * 100);
    }
}
