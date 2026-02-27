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

public class DggsvpTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;
        int p = N_SMALL;
        double tola = 1.0e-10;
        double tolb = 1.0e-10;

        double[] a_expected = generateMatrix(m, n, 1.0);
        double[] b_expected = generateMatrix(p, n, 2.0);
        double[] u_expected = new double[m * m];
        double[] v_expected = new double[p * p];
        double[] q_expected = new double[n * n];
        int[] iwork_expected = new int[n];
        double[] tau_expected = new double[n];
        double[] work_expected = new double[Math.max(Math.max(3 * n, m), p)];
        intW k_expected = new intW(0);
        intW l_expected = new intW(0);
        intW info_expected = new intW(0);

        f2j.dggsvp("U", "V", "Q", m, p, n, a_expected, 0, m, b_expected, 0, p, tola, tolb, k_expected, l_expected, u_expected, 0, m, v_expected, 0, p, q_expected, 0, n, iwork_expected, 0, tau_expected, 0, work_expected, 0, info_expected);
        assertEquals(0, info_expected.val, "Reference dggsvp should succeed");

        double[] a_actual = generateMatrix(m, n, 1.0);
        double[] b_actual = generateMatrix(p, n, 2.0);
        double[] u_actual = new double[m * m];
        double[] v_actual = new double[p * p];
        double[] q_actual = new double[n * n];
        int[] iwork_actual = new int[n];
        double[] tau_actual = new double[n];
        double[] work_actual = new double[Math.max(Math.max(3 * n, m), p)];
        intW k_actual = new intW(0);
        intW l_actual = new intW(0);
        intW info_actual = new intW(0);

        lapack.dggsvp("U", "V", "Q", m, p, n, a_actual, 0, m, b_actual, 0, p, tola, tolb, k_actual, l_actual, u_actual, 0, m, v_actual, 0, p, q_actual, 0, n, iwork_actual, 0, tau_actual, 0, work_actual, 0, info_actual);
        assertEquals(0, info_actual.val, "dggsvp should succeed");

        assertEquals(k_expected.val, k_actual.val);
        assertEquals(l_expected.val, l_actual.val);
        assertRelArrayEquals(a_expected, a_actual, depsilon);
        assertRelArrayEquals(b_expected, b_actual, depsilon);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testNoVectors(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;
        int p = N_SMALL;
        double tola = 1.0e-10;
        double tolb = 1.0e-10;

        double[] a_expected = generateMatrix(m, n, 1.0);
        double[] b_expected = generateMatrix(p, n, 2.0);
        double[] u_expected = new double[1];
        double[] v_expected = new double[1];
        double[] q_expected = new double[1];
        int[] iwork_expected = new int[n];
        double[] tau_expected = new double[n];
        double[] work_expected = new double[Math.max(Math.max(3 * n, m), p)];
        intW k_expected = new intW(0);
        intW l_expected = new intW(0);
        intW info_expected = new intW(0);

        f2j.dggsvp("N", "N", "N", m, p, n, a_expected, 0, m, b_expected, 0, p, tola, tolb, k_expected, l_expected, u_expected, 0, 1, v_expected, 0, 1, q_expected, 0, 1, iwork_expected, 0, tau_expected, 0, work_expected, 0, info_expected);
        assertEquals(0, info_expected.val);

        double[] a_actual = generateMatrix(m, n, 1.0);
        double[] b_actual = generateMatrix(p, n, 2.0);
        double[] u_actual = new double[1];
        double[] v_actual = new double[1];
        double[] q_actual = new double[1];
        int[] iwork_actual = new int[n];
        double[] tau_actual = new double[n];
        double[] work_actual = new double[Math.max(Math.max(3 * n, m), p)];
        intW k_actual = new intW(0);
        intW l_actual = new intW(0);
        intW info_actual = new intW(0);

        lapack.dggsvp("N", "N", "N", m, p, n, a_actual, 0, m, b_actual, 0, p, tola, tolb, k_actual, l_actual, u_actual, 0, 1, v_actual, 0, 1, q_actual, 0, 1, iwork_actual, 0, tau_actual, 0, work_actual, 0, info_actual);
        assertEquals(0, info_actual.val);

        assertEquals(k_expected.val, k_actual.val);
        assertEquals(l_expected.val, l_actual.val);
        assertRelArrayEquals(a_expected, a_actual, depsilon);
        assertRelArrayEquals(b_expected, b_actual, depsilon);
    }
}
