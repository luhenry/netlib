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

public class Dlar1vTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Internal MRRR routine not exposed by NativeLAPACK
        org.junit.jupiter.api.Assumptions.assumeFalse(lapack instanceof NativeLAPACK,
                "Internal MRRR routine not exposed by " + lapack.getClass().getSimpleName());

        int n = 10;
        double[] d = generateDoubleArray(n, 1.0);
        double[] l = generateDoubleArray(n, 0.5);
        double[] ld = generateDoubleArray(n, 0.3);
        double[] lld = generateDoubleArray(n, 0.2);
        double lambda = 2.0;
        double pivmin = 1e-10;
        double gaptol = 1e-6;

        double[] z_expected = new double[n];
        org.netlib.util.intW negcnt_expected = new org.netlib.util.intW(0);
        org.netlib.util.doubleW ztz_expected = new org.netlib.util.doubleW(0.0);
        org.netlib.util.doubleW mingma_expected = new org.netlib.util.doubleW(0.0);
        org.netlib.util.intW r_expected = new org.netlib.util.intW(0);
        int[] isuppz_expected = new int[2];
        org.netlib.util.doubleW nrminv_expected = new org.netlib.util.doubleW(0.0);
        org.netlib.util.doubleW resid_expected = new org.netlib.util.doubleW(0.0);
        org.netlib.util.doubleW rqcorr_expected = new org.netlib.util.doubleW(0.0);
        double[] work_expected = new double[4 * n];
        f2j.dlar1v(n, 1, n, lambda, d, l, ld, lld, pivmin, gaptol, z_expected, true,
                negcnt_expected, ztz_expected, mingma_expected, r_expected, isuppz_expected,
                nrminv_expected, resid_expected, rqcorr_expected, work_expected);

        double[] z_actual = new double[n];
        org.netlib.util.intW negcnt_actual = new org.netlib.util.intW(0);
        org.netlib.util.doubleW ztz_actual = new org.netlib.util.doubleW(0.0);
        org.netlib.util.doubleW mingma_actual = new org.netlib.util.doubleW(0.0);
        org.netlib.util.intW r_actual = new org.netlib.util.intW(0);
        int[] isuppz_actual = new int[2];
        org.netlib.util.doubleW nrminv_actual = new org.netlib.util.doubleW(0.0);
        org.netlib.util.doubleW resid_actual = new org.netlib.util.doubleW(0.0);
        org.netlib.util.doubleW rqcorr_actual = new org.netlib.util.doubleW(0.0);
        double[] work_actual = new double[4 * n];
        lapack.dlar1v(n, 1, n, lambda, d, l, ld, lld, pivmin, gaptol, z_actual, true,
                negcnt_actual, ztz_actual, mingma_actual, r_actual, isuppz_actual,
                nrminv_actual, resid_actual, rqcorr_actual, work_actual);

        assertArrayEquals(z_expected, z_actual, depsilon);
        assertEquals(negcnt_expected.val, negcnt_actual.val);
        assertEquals(ztz_expected.val, ztz_actual.val, depsilon);
    }
}
