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

public class Stgsy2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;

        // A is m x m upper triangular (quasi-triangular Schur form)
        float[] a = generateUpperTriangularMatrixFloat(m, 1.0f, 1.0f, 0.5f);

        // B is n x n upper triangular (quasi-triangular Schur form)
        float[] b = generateUpperTriangularMatrixFloat(n, m + 1.0f, 1.0f, 0.3f);

        // D is m x m upper triangular
        float[] d = generateUpperTriangularMatrixFloat(m, 2 * m + 1.0f, 1.0f, 0.2f);

        // E is n x n upper triangular
        float[] e = generateUpperTriangularMatrixFloat(n, 2 * m + n + 1.0f, 1.0f, 0.1f);

        // C is m x n general RHS matrix (overwritten with solution R)
        float[] c = new float[m * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                c[i + j * m] = 1.0f / (i + j + 1.0f);
            }
        }

        // F is m x n general RHS matrix (overwritten with solution L)
        float[] f = new float[m * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                f[i + j * m] = 1.0f / (i + j + 2.0f);
            }
        }

        float[] c_expected = c.clone();
        float[] c_actual = c.clone();
        float[] f_expected = f.clone();
        float[] f_actual = f.clone();
        floatW scale_expected = new floatW(0.0f);
        floatW scale_actual = new floatW(0.0f);
        floatW rdsum_expected = new floatW(0.0f);
        floatW rdsum_actual = new floatW(0.0f);
        floatW rdscal_expected = new floatW(0.0f);
        floatW rdscal_actual = new floatW(0.0f);
        int[] iwork_expected = new int[m + n + 2];
        int[] iwork_actual = new int[m + n + 2];
        intW pq_expected = new intW(0);
        intW pq_actual = new intW(0);
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.stgsy2("N", 0, m, n, a, m, b, n, c_expected, m, d, m, e, n, f_expected, m, scale_expected, rdsum_expected, rdscal_expected, iwork_expected, pq_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.stgsy2("N", 0, m, n, a, m, b, n, c_actual, m, d, m, e, n, f_actual, m, scale_actual, rdsum_actual, rdscal_actual, iwork_actual, pq_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertEquals(scale_expected.val, scale_actual.val, sepsilon);
        assertArrayEquals(c_expected, c_actual, sepsilon);
        assertArrayEquals(f_expected, f_actual, sepsilon);
    }
}
