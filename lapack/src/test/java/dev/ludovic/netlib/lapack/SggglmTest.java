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

public class SggglmTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // SGGGLM solves the GLM (Generalized Linear Model) problem:
        // minimize ||y||_2 subject to d = A*x + B*y
        // A is n x m, B is n x p, d is n-vector, x is m-vector, y is p-vector
        // Requires n >= m, and m + p >= n
        int n = N_SMALL + 2;
        int m = N_SMALL;
        int p = N_SMALL;

        // Generate matrix A (n x m)
        float[] a_expected = new float[n * m];
        float[] a_actual = new float[n * m];
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                float val = ((i + 1) * 0.5f + (j + 1) * 0.3f) / (m + 1);
                if (i == j) val += 1.0f;
                a_expected[i + j * n] = val;
                a_actual[i + j * n] = val;
            }
        }

        // Generate matrix B (n x p)
        float[] b_expected = new float[n * p];
        float[] b_actual = new float[n * p];
        for (int j = 0; j < p; j++) {
            for (int i = 0; i < n; i++) {
                float val = ((i + 1) * 0.3f + (j + 1) * 0.2f) / (p + 1);
                if (i == j) val += 1.0f;
                b_expected[i + j * n] = val;
                b_actual[i + j * n] = val;
            }
        }

        // Generate d vector (n)
        float[] d_expected = new float[n];
        float[] d_actual = new float[n];
        for (int i = 0; i < n; i++) {
            d_expected[i] = (i + 1) * 1.0f;
            d_actual[i] = (i + 1) * 1.0f;
        }

        // Solution vectors
        float[] x_expected = new float[m];
        float[] x_actual = new float[m];
        float[] y_expected = new float[p];
        float[] y_actual = new float[p];

        // Workspace query
        float[] workQuery = new float[1];
        intW info = new intW(0);
        f2j.sggglm(n, m, p, a_expected.clone(), 0, n, b_expected.clone(), 0, n,
                   d_expected.clone(), 0, x_expected, 0, y_expected, 0,
                   workQuery, 0, -1, info);
        int lwork = Math.max(1, (int) workQuery[0]);
        float[] work_expected = new float[lwork];
        float[] work_actual = new float[lwork];

        // Solve using reference implementation
        info.val = 0;
        f2j.sggglm(n, m, p, a_expected, 0, n, b_expected, 0, n,
                   d_expected, 0, x_expected, 0, y_expected, 0,
                   work_expected, 0, lwork, info);
        assertEquals(0, info.val, "Reference solve should succeed");

        // Solve using test implementation
        info.val = 0;
        lapack.sggglm(n, m, p, a_actual, 0, n, b_actual, 0, n,
                      d_actual, 0, x_actual, 0, y_actual, 0,
                      work_actual, 0, lwork, info);
        assertEquals(0, info.val, "Solve should succeed");

        // Compare solutions (y-vector tolerance is based on x scale since y components can be near zero)
        assertArrayEquals(x_expected, x_actual, Math.scalb(sepsilon, Math.getExponent(getMaxValue(x_expected)) + 4));
        assertArrayEquals(y_expected, y_actual, Math.scalb(sepsilon, Math.getExponent(getMaxValue(x_expected)) + 5));
    }
}
