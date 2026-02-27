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

public class DgelsTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // DGELS solves overdetermined/underdetermined systems using QR/LQ factorization
        // Test overdetermined system (m > n): A is m x n, b is m x 1
        int m = N_SMALL + 2;
        int n = N_SMALL;
        int nrhs = 1;

        // Generate a well-conditioned tall matrix A (m x n) in column-major order
        double[] a_expected = new double[m * n];
        double[] a_actual = new double[m * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                double val = ((i + 1) * 0.5 + (j + 1) * 0.3) / (n + 1);
                if (i == j) val += 1.0;
                a_expected[i + j * m] = val;
                a_actual[i + j * m] = val;
            }
        }

        // Generate right-hand side b (m x 1), stored in array of size max(m,n) for dgels
        int ldb = Math.max(m, n);
        double[] b_expected = new double[ldb * nrhs];
        double[] b_actual = new double[ldb * nrhs];
        for (int i = 0; i < m; i++) {
            b_expected[i] = (i + 1) * 1.0;
            b_actual[i] = (i + 1) * 1.0;
        }

        // Workspace query
        double[] workQuery = new double[1];
        intW info = new intW(0);
        f2j.dgels("N", m, n, nrhs, a_expected.clone(), 0, m, b_expected.clone(), 0, ldb, workQuery, 0, -1, info);
        int lwork = (int) workQuery[0];
        double[] work_expected = new double[lwork];
        double[] work_actual = new double[lwork];

        // Solve using reference implementation
        info.val = 0;
        f2j.dgels("N", m, n, nrhs, a_expected, 0, m, b_expected, 0, ldb, work_expected, 0, lwork, info);
        assertEquals(0, info.val, "Reference solve should succeed");

        // Solve using test implementation
        info.val = 0;
        lapack.dgels("N", m, n, nrhs, a_actual, 0, m, b_actual, 0, ldb, work_actual, 0, lwork, info);
        assertEquals(0, info.val, "Solve should succeed");

        // Compare the solution vector x (first n entries of b)
        double[] x_expected = new double[n];
        double[] x_actual = new double[n];
        System.arraycopy(b_expected, 0, x_expected, 0, n);
        System.arraycopy(b_actual, 0, x_actual, 0, n);
        assertArrayEquals(x_expected, x_actual, Math.scalb(depsilon, Math.getExponent(getMaxValue(x_expected)) + 2));
    }
}
