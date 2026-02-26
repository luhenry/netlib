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

public class SgesddTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;
        int minmn = Math.min(m, n);

        float[] a_expected = generateMatrixFloat(m, n, 1.0f);
        float[] s_expected = new float[minmn];
        float[] u_expected = new float[m * m];
        float[] vt_expected = new float[n * n];
        int lwork = 3 * minmn * minmn + Math.max(Math.max(m, n), 4 * minmn * minmn + 4 * minmn);
        float[] work_expected = new float[lwork];
        int[] iwork_expected = new int[8 * minmn];
        intW info_expected = new intW(0);

        f2j.sgesdd("A", m, n, a_expected, 0, m, s_expected, 0, u_expected, 0, m, vt_expected, 0, n, work_expected, 0, lwork, iwork_expected, 0, info_expected);
        assertEquals(0, info_expected.val, "Reference sgesdd should succeed");

        float[] a_actual = generateMatrixFloat(m, n, 1.0f);
        float[] s_actual = new float[minmn];
        float[] u_actual = new float[m * m];
        float[] vt_actual = new float[n * n];
        float[] work_actual = new float[lwork];
        int[] iwork_actual = new int[8 * minmn];
        intW info_actual = new intW(0);

        lapack.sgesdd("A", m, n, a_actual, 0, m, s_actual, 0, u_actual, 0, m, vt_actual, 0, n, work_actual, 0, lwork, iwork_actual, 0, info_actual);
        assertEquals(0, info_actual.val, "sgesdd should succeed");

        // Singular values should match (use relative tolerance for large values)
        assertRelArrayEquals(s_expected, s_actual, sepsilon * 100);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSingularValuesOnly(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;
        int minmn = Math.min(m, n);

        float[] a_expected = generateMatrixFloat(m, n, 1.0f);
        float[] s_expected = new float[minmn];
        float[] u_expected = new float[1];
        float[] vt_expected = new float[1];
        int lwork = 3 * minmn + Math.max(Math.max(m, n), 7 * minmn);
        float[] work_expected = new float[lwork];
        int[] iwork_expected = new int[8 * minmn];
        intW info_expected = new intW(0);

        f2j.sgesdd("N", m, n, a_expected, 0, m, s_expected, 0, u_expected, 0, 1, vt_expected, 0, 1, work_expected, 0, lwork, iwork_expected, 0, info_expected);
        assertEquals(0, info_expected.val);

        float[] a_actual = generateMatrixFloat(m, n, 1.0f);
        float[] s_actual = new float[minmn];
        float[] u_actual = new float[1];
        float[] vt_actual = new float[1];
        float[] work_actual = new float[lwork];
        int[] iwork_actual = new int[8 * minmn];
        intW info_actual = new intW(0);

        lapack.sgesdd("N", m, n, a_actual, 0, m, s_actual, 0, u_actual, 0, 1, vt_actual, 0, 1, work_actual, 0, lwork, iwork_actual, 0, info_actual);
        assertEquals(0, info_actual.val);

        assertRelArrayEquals(s_expected, s_actual, sepsilon * 100);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testRectangularMatrix(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL / 2;
        int minmn = Math.min(m, n);

        float[] a_expected = generateMatrixFloat(m, n, 1.0f);
        float[] s_expected = new float[minmn];
        float[] u_expected = new float[m * m];
        float[] vt_expected = new float[n * n];
        int lwork = 3 * minmn * minmn + Math.max(Math.max(m, n), 4 * minmn * minmn + 4 * minmn);
        float[] work_expected = new float[lwork];
        int[] iwork_expected = new int[8 * minmn];
        intW info_expected = new intW(0);

        f2j.sgesdd("A", m, n, a_expected, 0, m, s_expected, 0, u_expected, 0, m, vt_expected, 0, n, work_expected, 0, lwork, iwork_expected, 0, info_expected);
        assertEquals(0, info_expected.val);

        float[] a_actual = generateMatrixFloat(m, n, 1.0f);
        float[] s_actual = new float[minmn];
        float[] u_actual = new float[m * m];
        float[] vt_actual = new float[n * n];
        float[] work_actual = new float[lwork];
        int[] iwork_actual = new int[8 * minmn];
        intW info_actual = new intW(0);

        lapack.sgesdd("A", m, n, a_actual, 0, m, s_actual, 0, u_actual, 0, m, vt_actual, 0, n, work_actual, 0, lwork, iwork_actual, 0, info_actual);
        assertEquals(0, info_actual.val);

        assertRelArrayEquals(s_expected, s_actual, sepsilon * 100);
    }
}
