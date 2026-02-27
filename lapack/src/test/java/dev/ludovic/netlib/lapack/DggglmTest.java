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

public class DggglmTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // DGGGLM solves the GLM (Generalized Linear Model) problem:
        // minimize ||y||_2 subject to d = A*x + B*y
        // A is n x m, B is n x p, d is n-vector, x is m-vector, y is p-vector
        // Requires n >= m, and m + p >= n
        int n = N_SMALL + 2;
        int m = N_SMALL;
        int p = N_SMALL;

        // Generate matrix A (n x m)
        double[] a_expected = new double[n * m];
        double[] a_actual = new double[n * m];
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                double val = ((i + 1) * 0.5 + (j + 1) * 0.3) / (m + 1);
                if (i == j) val += 1.0;
                a_expected[i + j * n] = val;
                a_actual[i + j * n] = val;
            }
        }

        // Generate matrix B (n x p)
        double[] b_expected = new double[n * p];
        double[] b_actual = new double[n * p];
        for (int j = 0; j < p; j++) {
            for (int i = 0; i < n; i++) {
                double val = ((i + 1) * 0.3 + (j + 1) * 0.2) / (p + 1);
                if (i == j) val += 1.0;
                b_expected[i + j * n] = val;
                b_actual[i + j * n] = val;
            }
        }

        // Generate d vector (n)
        double[] d_expected = new double[n];
        double[] d_actual = new double[n];
        for (int i = 0; i < n; i++) {
            d_expected[i] = (i + 1) * 1.0;
            d_actual[i] = (i + 1) * 1.0;
        }

        // Solution vectors
        double[] x_expected = new double[m];
        double[] x_actual = new double[m];
        double[] y_expected = new double[p];
        double[] y_actual = new double[p];

        // Workspace query
        double[] workQuery = new double[1];
        intW info = new intW(0);
        f2j.dggglm(n, m, p, a_expected.clone(), 0, n, b_expected.clone(), 0, n,
                   d_expected.clone(), 0, x_expected, 0, y_expected, 0,
                   workQuery, 0, -1, info);
        int lwork = Math.max(1, (int) workQuery[0]);
        double[] work_expected = new double[lwork];
        double[] work_actual = new double[lwork];

        // Solve using reference implementation
        info.val = 0;
        f2j.dggglm(n, m, p, a_expected, 0, n, b_expected, 0, n,
                   d_expected, 0, x_expected, 0, y_expected, 0,
                   work_expected, 0, lwork, info);
        assertEquals(0, info.val, "Reference solve should succeed");

        // Solve using test implementation
        info.val = 0;
        lapack.dggglm(n, m, p, a_actual, 0, n, b_actual, 0, n,
                      d_actual, 0, x_actual, 0, y_actual, 0,
                      work_actual, 0, lwork, info);
        assertEquals(0, info.val, "Solve should succeed");

        // Compare solutions (y-vector tolerance is based on x scale since y components can be near zero)
        assertArrayEquals(x_expected, x_actual, Math.scalb(depsilon, Math.getExponent(getMaxValue(x_expected)) + 4));
        assertArrayEquals(y_expected, y_actual, Math.scalb(depsilon, Math.getExponent(getMaxValue(x_expected)) + 4));
    }
}
