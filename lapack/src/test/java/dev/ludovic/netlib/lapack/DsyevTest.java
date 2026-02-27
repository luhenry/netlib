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

public class DsyevTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        double[] a_expected = new double[n * n];
        double[] a_actual = new double[n * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                a_expected[i + j * n] = dPositiveDefiniteMatrix[i + j * N];
                a_actual[i + j * n] = dPositiveDefiniteMatrix[i + j * N];
            }
        }

        double[] w_expected = new double[n];
        double[] w_actual = new double[n];
        int lwork = Math.max(1, 3 * n - 1);
        double[] work_expected = new double[lwork];
        double[] work_actual = new double[lwork];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dsyev("N", "U", n, a_expected, 0, n, w_expected, 0, work_expected, 0, lwork, info_expected);
        assertEquals(0, info_expected.val, "Reference dsyev should succeed");

        lapack.dsyev("N", "U", n, a_actual, 0, n, w_actual, 0, work_actual, 0, lwork, info_actual);
        assertEquals(0, info_actual.val, "dsyev should succeed");

        assertRelArrayEquals(w_expected, w_actual, depsilon);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testWithEigenvectors(LAPACK lapack) {
        int n = N_SMALL;
        double[] a_expected = new double[n * n];
        double[] a_actual = new double[n * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                a_expected[i + j * n] = dPositiveDefiniteMatrix[i + j * N];
                a_actual[i + j * n] = dPositiveDefiniteMatrix[i + j * N];
            }
        }

        double[] w_expected = new double[n];
        double[] w_actual = new double[n];
        int lwork = Math.max(1, 3 * n - 1);
        double[] work_expected = new double[lwork];
        double[] work_actual = new double[lwork];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dsyev("V", "U", n, a_expected, 0, n, w_expected, 0, work_expected, 0, lwork, info_expected);
        assertEquals(0, info_expected.val);

        lapack.dsyev("V", "U", n, a_actual, 0, n, w_actual, 0, work_actual, 0, lwork, info_actual);
        assertEquals(0, info_actual.val);

        assertRelArrayEquals(w_expected, w_actual, depsilon);
    }
}
