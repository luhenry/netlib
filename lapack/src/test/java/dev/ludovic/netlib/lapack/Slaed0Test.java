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

public class Slaed0Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // slaed0 computes all eigenvalues/eigenvectors of a symmetric tridiagonal
        // matrix using divide and conquer. ICOMPQ=2: tridiagonal eigenvectors.
        int icompq = 2;
        int n = 6;
        int qsiz = n;

        float[] d_expected = new float[n];
        float[] e_expected = new float[n - 1];
        for (int i = 0; i < n; i++) {
            d_expected[i] = (i + 1) * 2.0f;
        }
        for (int i = 0; i < n - 1; i++) {
            e_expected[i] = 1.0f;
        }

        float[] q_expected = generateIdentityMatrixFloat(n);

        float[] qstore_expected = new float[n * n];
        // ICOMPQ=2: work >= 4*N + N^2, iwork >= 3 + 5*N
        float[] work_expected = new float[4 * n + n * n];
        int[] iwork_expected = new int[3 + 5 * n];
        intW info_expected = new intW(0);

        f2j.slaed0(icompq, qsiz, n, d_expected, 0, e_expected, 0,
            q_expected, 0, n, qstore_expected, 0, n,
            work_expected, 0, iwork_expected, 0, info_expected);

        float[] d_actual = new float[n];
        float[] e_actual = new float[n - 1];
        for (int i = 0; i < n; i++) {
            d_actual[i] = (i + 1) * 2.0f;
        }
        for (int i = 0; i < n - 1; i++) {
            e_actual[i] = 1.0f;
        }

        float[] q_actual = generateIdentityMatrixFloat(n);

        float[] qstore_actual = new float[n * n];
        float[] work_actual = new float[4 * n + n * n];
        int[] iwork_actual = new int[3 + 5 * n];
        intW info_actual = new intW(0);

        lapack.slaed0(icompq, qsiz, n, d_actual, 0, e_actual, 0,
            q_actual, 0, n, qstore_actual, 0, n,
            work_actual, 0, iwork_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(d_expected, d_actual, Math.scalb(sepsilon, Math.getExponent(getMaxValue(d_expected))));
        assertArrayEquals(q_expected, q_actual, Math.scalb(sepsilon, Math.getExponent(Math.max(getMaxValue(q_expected), 1.0f))));
    }
}
