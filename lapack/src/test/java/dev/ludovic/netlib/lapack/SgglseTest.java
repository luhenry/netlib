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

public class SgglseTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // SGGLSE solves the LSE (Linear equality-constrained Least Squares) problem:
        // minimize ||c - A*x||_2 subject to B*x = d
        // A is m x n, B is p x n, c is m-vector, d is p-vector, x is n-vector
        // Requires m >= n >= p
        int n = N_SMALL;
        int m = N_SMALL + 2;
        int p = N_SMALL - 2;

        // Generate matrix A (m x n)
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

        // Generate constraint matrix B (p x n)
        float[] b_expected = new float[p * n];
        float[] b_actual = new float[p * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < p; i++) {
                float val = ((i + 1) * 0.3f + (j + 1) * 0.2f) / (n + 1);
                if (i == j) val += 1.0f;
                b_expected[i + j * p] = val;
                b_actual[i + j * p] = val;
            }
        }

        // Generate c vector (m)
        float[] c_expected = new float[m];
        float[] c_actual = new float[m];
        for (int i = 0; i < m; i++) {
            c_expected[i] = (i + 1) * 1.0f;
            c_actual[i] = (i + 1) * 1.0f;
        }

        // Generate d vector (p)
        float[] d_expected = new float[p];
        float[] d_actual = new float[p];
        for (int i = 0; i < p; i++) {
            d_expected[i] = (i + 1) * 0.5f;
            d_actual[i] = (i + 1) * 0.5f;
        }

        // Solution vector x (n)
        float[] x_expected = new float[n];
        float[] x_actual = new float[n];

        // Workspace query
        float[] workQuery = new float[1];
        intW info = new intW(0);
        f2j.sgglse(m, n, p, a_expected.clone(), 0, m, b_expected.clone(), 0, p,
                   c_expected.clone(), 0, d_expected.clone(), 0, x_expected, 0,
                   workQuery, 0, -1, info);
        int lwork = Math.max(1, (int) workQuery[0]);
        float[] work_expected = new float[lwork];
        float[] work_actual = new float[lwork];

        // Solve using reference implementation
        info.val = 0;
        f2j.sgglse(m, n, p, a_expected, 0, m, b_expected, 0, p,
                   c_expected, 0, d_expected, 0, x_expected, 0,
                   work_expected, 0, lwork, info);
        assertEquals(0, info.val, "Reference solve should succeed");

        // Solve using test implementation
        info.val = 0;
        lapack.sgglse(m, n, p, a_actual, 0, m, b_actual, 0, p,
                      c_actual, 0, d_actual, 0, x_actual, 0,
                      work_actual, 0, lwork, info);
        assertEquals(0, info.val, "Solve should succeed");

        // Compare solutions
        assertArrayEquals(x_expected, x_actual, Math.scalb(sepsilon, Math.getExponent(getMaxValue(x_expected)) + 2));
    }
}
