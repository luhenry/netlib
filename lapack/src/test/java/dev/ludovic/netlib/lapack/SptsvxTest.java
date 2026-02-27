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

public class SptsvxTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // SPTSVX is the expert driver for symmetric positive definite tridiagonal matrices
        float[] d_expected = new float[N];
        float[] d_actual = new float[N];
        float[] e_expected = new float[N - 1];
        float[] e_actual = new float[N - 1];

        // Fill positive definite tridiagonal matrix
        for (int i = 0; i < N; i++) {
            d_expected[i] = N + 5.0f;
            d_actual[i] = N + 5.0f;
            if (i < N - 1) {
                e_expected[i] = 1.0f;
                e_actual[i] = 1.0f;
            }
        }

        // Factorization arrays
        float[] df_expected = new float[N];
        float[] df_actual = new float[N];
        float[] ef_expected = new float[N - 1];
        float[] ef_actual = new float[N - 1];

        // Create right-hand side B and solution X
        float[] b_expected = generateFloatArray(N, 1.0f);
        float[] b_actual = b_expected.clone();
        float[] x_expected = new float[N];
        float[] x_actual = new float[N];

        // Condition and error arrays
        floatW rcond_expected = new floatW(0.0f);
        floatW rcond_actual = new floatW(0.0f);
        float[] ferr_expected = new float[1];
        float[] ferr_actual = new float[1];
        float[] berr_expected = new float[1];
        float[] berr_actual = new float[1];
        float[] work_expected = new float[2 * N];
        float[] work_actual = new float[2 * N];

        // Solve using reference implementation
        intW info = new intW(0);
        f2j.sptsvx("N", N, 1, d_expected, 0, e_expected, 0, df_expected, 0, ef_expected, 0,
                   b_expected, 0, N, x_expected, 0, N, rcond_expected,
                   ferr_expected, 0, berr_expected, 0, work_expected, 0, info);
        assertEquals(0, info.val, "Reference solve should succeed");

        // Solve using test implementation
        info.val = 0;
        lapack.sptsvx("N", N, 1, d_actual, 0, e_actual, 0, df_actual, 0, ef_actual, 0,
                      b_actual, 0, N, x_actual, 0, N, rcond_actual,
                      ferr_actual, 0, berr_actual, 0, work_actual, 0, info);
        assertEquals(0, info.val, "Solve should succeed");

        // Compare solutions
        assertArrayEquals(x_expected, x_actual, Math.scalb(sepsilon, Math.getExponent(getMaxValue(x_expected)) + 3));

        // Check that RCOND is reasonable
        assertTrue(rcond_actual.val > 0.0f, "RCOND should be positive");
        assertEquals(rcond_expected.val, rcond_actual.val, Math.abs(rcond_expected.val) * 0.1f, "RCOND should match");
    }
}
