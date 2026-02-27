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

public class Dlasd3Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // dlasd3 is called by dlasd1 after dlasd2. Test via dlasd1 indirectly.
        // Direct test: use output of dlasd2 as input.
        // For simplicity, use dlasd0 which calls the entire chain internally.
        // Test dlasd0 with a small well-conditioned problem.
        int n = 5;
        int sqre = 0;
        int smlsiz = 3; // Force divide-and-conquer to exercise dlasd3 (must be >= 3)

        double[] d_expected = new double[n];
        double[] e_expected = new double[n - 1];
        for (int i = 0; i < n; i++) {
            d_expected[i] = (n - i) * 3.0;
        }
        for (int i = 0; i < n - 1; i++) {
            e_expected[i] = 0.5;
        }

        double[] u_expected = new double[n * n];
        double[] vt_expected = new double[n * n];
        int[] iwork_expected = new int[8 * n];
        double[] work_expected = new double[3 * n * n + 4 * n];
        intW info_expected = new intW(0);

        f2j.dlasd0(n, sqre, d_expected, 0, e_expected, 0, u_expected, 0, n,
            vt_expected, 0, n, smlsiz, iwork_expected, 0, work_expected, 0, info_expected);

        double[] d_actual = new double[n];
        double[] e_actual = new double[n - 1];
        for (int i = 0; i < n; i++) {
            d_actual[i] = (n - i) * 3.0;
        }
        for (int i = 0; i < n - 1; i++) {
            e_actual[i] = 0.5;
        }

        double[] u_actual = new double[n * n];
        double[] vt_actual = new double[n * n];
        int[] iwork_actual = new int[8 * n];
        double[] work_actual = new double[3 * n * n + 4 * n];
        intW info_actual = new intW(0);

        lapack.dlasd0(n, sqre, d_actual, 0, e_actual, 0, u_actual, 0, n,
            vt_actual, 0, n, smlsiz, iwork_actual, 0, work_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(d_expected, d_actual, Math.scalb(depsilon, Math.getExponent(getMaxValue(d_expected))));
        assertArrayEquals(u_expected, u_actual, Math.scalb(depsilon, Math.getExponent(Math.max(getMaxValue(u_expected), 1.0))));
        assertArrayEquals(vt_expected, vt_actual, Math.scalb(depsilon, Math.getExponent(Math.max(getMaxValue(vt_expected), 1.0))));
    }
}
