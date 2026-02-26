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

public class DtgsjaTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int m = N_SMALL;
        int p = N_SMALL;
        int n = N_SMALL;
        int k = 0;
        int l = n;

        // A is m x n upper triangular (preprocessed by DGGSVP3)
        double[] a = new double[m * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= Math.min(j, m - 1); i++) {
                a[i + j * m] = (i == j) ? (i + 1.0) : 0.5 / (j - i + 1.0);
            }
        }

        // B is p x n upper triangular
        double[] b = new double[p * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= Math.min(j, p - 1); i++) {
                b[i + j * p] = (i == j) ? (n + i + 1.0) : 0.3 / (j - i + 1.0);
            }
        }

        double tola = m * depsilon;
        double tolb = p * depsilon;

        double[] a_expected = a.clone();
        double[] a_actual = a.clone();
        double[] b_expected = b.clone();
        double[] b_actual = b.clone();
        double[] alpha_expected = new double[n];
        double[] alpha_actual = new double[n];
        double[] beta_expected = new double[n];
        double[] beta_actual = new double[n];
        double[] u = new double[1];
        double[] v = new double[1];
        double[] q = new double[1];
        double[] work_expected = new double[2 * n];
        double[] work_actual = new double[2 * n];
        intW ncycle_expected = new intW(0);
        intW ncycle_actual = new intW(0);
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dtgsja("N", "N", "N", m, p, n, k, l, a_expected, m, b_expected, p, tola, tolb, alpha_expected, beta_expected, u, 1, v, 1, q, 1, work_expected, ncycle_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.dtgsja("N", "N", "N", m, p, n, k, l, a_actual, m, b_actual, p, tola, tolb, alpha_actual, beta_actual, u, 1, v, 1, q, 1, work_actual, ncycle_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(alpha_expected, alpha_actual, depsilon);
        assertArrayEquals(beta_expected, beta_actual, depsilon);
        assertArrayEquals(a_expected, a_actual, Math.scalb(depsilon, Math.getExponent(getMaxValue(a_expected))));
        assertArrayEquals(b_expected, b_actual, Math.scalb(depsilon, Math.getExponent(getMaxValue(b_expected))));
    }
}
