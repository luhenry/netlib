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

public class SgelsxTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // SGELSX solves overdetermined/underdetermined systems using QR with column pivoting
        // (deprecated, replaced by SGELSY)
        int m = N_SMALL + 2;
        int n = N_SMALL;
        int nrhs = 1;

        // Generate a well-conditioned tall matrix A (m x n) in column-major order
        float[] a_expected = new float[m * n];
        float[] a_actual = new float[m * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                float val = ((i + 1) * 0.5f + (j + 1) * 0.3f) / (n + 1);
                if (i == j) val += 1.0f;
                a_expected[i + j * m] = val;
                a_actual[i + j * m] = val;
            }
        }

        // Generate right-hand side b (max(m,n) x nrhs)
        int ldb = Math.max(m, n);
        float[] b_expected = new float[ldb * nrhs];
        float[] b_actual = new float[ldb * nrhs];
        for (int i = 0; i < m; i++) {
            b_expected[i] = (i + 1) * 1.0f;
            b_actual[i] = (i + 1) * 1.0f;
        }

        // Column pivoting array (initialized to 0 means free columns)
        int[] jpvt_expected = new int[n];
        int[] jpvt_actual = new int[n];

        float rcond = -1.0f; // use machine precision
        intW rank_expected = new intW(0);
        intW rank_actual = new intW(0);

        float[] work_expected = new float[Math.max(1, Math.min(m, n) + 3 * n)];
        float[] work_actual = new float[Math.max(1, Math.min(m, n) + 3 * n)];

        // Solve using reference implementation
        intW info = new intW(0);
        f2j.sgelsx(m, n, nrhs, a_expected, 0, m, b_expected, 0, ldb, jpvt_expected, 0, rcond, rank_expected, work_expected, 0, info);
        assertEquals(0, info.val, "Reference solve should succeed");

        // Solve using test implementation
        info.val = 0;
        lapack.sgelsx(m, n, nrhs, a_actual, 0, m, b_actual, 0, ldb, jpvt_actual, 0, rcond, rank_actual, work_actual, 0, info);
        assertEquals(0, info.val, "Solve should succeed");

        // Compare rank
        assertEquals(rank_expected.val, rank_actual.val, "Rank should match");

        // Compare the solution vector x (first n entries of b)
        float[] x_expected = new float[n];
        float[] x_actual = new float[n];
        System.arraycopy(b_expected, 0, x_expected, 0, n);
        System.arraycopy(b_actual, 0, x_actual, 0, n);
        assertArrayEquals(x_expected, x_actual, Math.scalb(sepsilon, Math.getExponent(getMaxValue(x_expected)) + 2));
    }
}
