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

public class StzrzfTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int m = N_SMALL;
        int n = 2 * N_SMALL;

        // Upper trapezoidal matrix (m x n, m <= n)
        float[] a = new float[m * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (i <= j) {
                    a[i + j * m] = 1.0f / (i + j + 1.0f);
                }
            }
        }

        float[] a_expected = a.clone();
        float[] a_actual = a.clone();
        float[] tau_expected = new float[m];
        float[] tau_actual = new float[m];
        int lwork = Math.max(1, m);
        float[] work_expected = new float[lwork];
        float[] work_actual = new float[lwork];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.stzrzf(m, n, a_expected, m, tau_expected, work_expected, lwork, info_expected);
        assertEquals(0, info_expected.val);

        lapack.stzrzf(m, n, a_actual, m, tau_actual, work_actual, lwork, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(a_expected, a_actual, sepsilon);
        assertArrayEquals(tau_expected, tau_actual, sepsilon);
    }
}
