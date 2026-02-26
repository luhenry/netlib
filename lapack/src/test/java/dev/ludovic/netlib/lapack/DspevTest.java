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

public class DspevTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        int np = n * (n + 1) / 2;
        double[] ap_expected = new double[np];
        double[] ap_actual = new double[np];
        int k = 0;
        for (int j = 0; j < n; j++)
            for (int i = 0; i <= j; i++) {
                ap_expected[k] = dPositiveDefiniteMatrix[i + j * N];
                ap_actual[k] = dPositiveDefiniteMatrix[i + j * N];
                k++;
            }

        double[] w_expected = new double[n];
        double[] w_actual = new double[n];
        double[] z_expected = new double[n * n];
        double[] z_actual = new double[n * n];
        double[] work_expected = new double[3 * n];
        double[] work_actual = new double[3 * n];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dspev("N", "U", n, ap_expected, 0, w_expected, 0, z_expected, 0, n, work_expected, 0, info_expected);
        assertEquals(0, info_expected.val, "Reference dspev should succeed");

        lapack.dspev("N", "U", n, ap_actual, 0, w_actual, 0, z_actual, 0, n, work_actual, 0, info_actual);
        assertEquals(0, info_actual.val, "dspev should succeed");

        assertRelArrayEquals(w_expected, w_actual, depsilon);
    }
}
