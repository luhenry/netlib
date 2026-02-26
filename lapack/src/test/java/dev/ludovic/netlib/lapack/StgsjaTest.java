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

public class StgsjaTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int m = N_SMALL;
        int p = N_SMALL;
        int n = N_SMALL;
        int k = 0;
        int l = n;

        // A is m x n upper triangular (preprocessed by SGGSVP3)
        float[] a = new float[m * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= Math.min(j, m - 1); i++) {
                a[i + j * m] = (i == j) ? (i + 1.0f) : 0.5f / (j - i + 1.0f);
            }
        }

        // B is p x n upper triangular
        float[] b = new float[p * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= Math.min(j, p - 1); i++) {
                b[i + j * p] = (i == j) ? (n + i + 1.0f) : 0.3f / (j - i + 1.0f);
            }
        }

        float tola = m * sepsilon;
        float tolb = p * sepsilon;

        float[] a_expected = a.clone();
        float[] a_actual = a.clone();
        float[] b_expected = b.clone();
        float[] b_actual = b.clone();
        float[] alpha_expected = new float[n];
        float[] alpha_actual = new float[n];
        float[] beta_expected = new float[n];
        float[] beta_actual = new float[n];
        float[] u = new float[1];
        float[] v = new float[1];
        float[] q = new float[1];
        float[] work_expected = new float[2 * n];
        float[] work_actual = new float[2 * n];
        intW ncycle_expected = new intW(0);
        intW ncycle_actual = new intW(0);
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.stgsja("N", "N", "N", m, p, n, k, l, a_expected, m, b_expected, p, tola, tolb, alpha_expected, beta_expected, u, 1, v, 1, q, 1, work_expected, ncycle_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.stgsja("N", "N", "N", m, p, n, k, l, a_actual, m, b_actual, p, tola, tolb, alpha_actual, beta_actual, u, 1, v, 1, q, 1, work_actual, ncycle_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(alpha_expected, alpha_actual, sepsilon);
        assertArrayEquals(beta_expected, beta_actual, sepsilon);
        assertArrayEquals(a_expected, a_actual, sepsilon);
        assertArrayEquals(b_expected, b_actual, sepsilon);
    }
}
