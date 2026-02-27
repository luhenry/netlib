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

public class DgesvdTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;
        int minmn = Math.min(m, n);

        double[] a_expected = generateMatrix(m, n, 1.0);
        double[] s_expected = new double[minmn];
        double[] u_expected = new double[m * m];
        double[] vt_expected = new double[n * n];
        int lwork = Math.max(1, 3 * minmn + Math.max(m, n) + minmn);
        double[] work_expected = new double[lwork];
        intW info_expected = new intW(0);

        f2j.dgesvd("A", "A", m, n, a_expected, 0, m, s_expected, 0, u_expected, 0, m, vt_expected, 0, n, work_expected, 0, lwork, info_expected);
        assertEquals(0, info_expected.val, "Reference dgesvd should succeed");

        double[] a_actual = generateMatrix(m, n, 1.0);
        double[] s_actual = new double[minmn];
        double[] u_actual = new double[m * m];
        double[] vt_actual = new double[n * n];
        double[] work_actual = new double[lwork];
        intW info_actual = new intW(0);

        lapack.dgesvd("A", "A", m, n, a_actual, 0, m, s_actual, 0, u_actual, 0, m, vt_actual, 0, n, work_actual, 0, lwork, info_actual);
        assertEquals(0, info_actual.val, "dgesvd should succeed");

        // Singular values should match
        assertRelArrayEquals(s_expected, s_actual, depsilon * 10);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testRectangularTallMatrix(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL / 2;
        int minmn = Math.min(m, n);

        double[] a_expected = generateMatrix(m, n, 1.0);
        double[] s_expected = new double[minmn];
        double[] u_expected = new double[m * m];
        double[] vt_expected = new double[n * n];
        int lwork = Math.max(1, 3 * minmn + Math.max(m, n) + minmn);
        double[] work_expected = new double[lwork];
        intW info_expected = new intW(0);

        f2j.dgesvd("A", "A", m, n, a_expected, 0, m, s_expected, 0, u_expected, 0, m, vt_expected, 0, n, work_expected, 0, lwork, info_expected);
        assertEquals(0, info_expected.val);

        double[] a_actual = generateMatrix(m, n, 1.0);
        double[] s_actual = new double[minmn];
        double[] u_actual = new double[m * m];
        double[] vt_actual = new double[n * n];
        double[] work_actual = new double[lwork];
        intW info_actual = new intW(0);

        lapack.dgesvd("A", "A", m, n, a_actual, 0, m, s_actual, 0, u_actual, 0, m, vt_actual, 0, n, work_actual, 0, lwork, info_actual);
        assertEquals(0, info_actual.val);

        assertRelArrayEquals(s_expected, s_actual, depsilon * 10);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSingularValuesOnly(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;
        int minmn = Math.min(m, n);

        double[] a_expected = generateMatrix(m, n, 1.0);
        double[] s_expected = new double[minmn];
        double[] u_expected = new double[1];
        double[] vt_expected = new double[1];
        int lwork = Math.max(1, 5 * minmn);
        double[] work_expected = new double[lwork];
        intW info_expected = new intW(0);

        f2j.dgesvd("N", "N", m, n, a_expected, 0, m, s_expected, 0, u_expected, 0, 1, vt_expected, 0, 1, work_expected, 0, lwork, info_expected);
        assertEquals(0, info_expected.val);

        double[] a_actual = generateMatrix(m, n, 1.0);
        double[] s_actual = new double[minmn];
        double[] u_actual = new double[1];
        double[] vt_actual = new double[1];
        double[] work_actual = new double[lwork];
        intW info_actual = new intW(0);

        lapack.dgesvd("N", "N", m, n, a_actual, 0, m, s_actual, 0, u_actual, 0, 1, vt_actual, 0, 1, work_actual, 0, lwork, info_actual);
        assertEquals(0, info_actual.val);

        assertRelArrayEquals(s_expected, s_actual, depsilon * 10);
    }
}
