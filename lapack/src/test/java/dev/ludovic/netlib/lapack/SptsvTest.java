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

public class SptsvTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // SPTSV solves A*X = B for symmetric positive definite tridiagonal matrix
        // Matrix is stored as D (diagonal) and E (subdiagonal/superdiagonal)
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

        // Create right-hand side B
        float[] b_expected = generateFloatArray(N, 1.0f);
        float[] b_actual = b_expected.clone();

        // Solve using reference implementation
        intW info = new intW(0);
        f2j.sptsv(N, 1, d_expected, 0, e_expected, 0, b_expected, 0, N, info);
        assertEquals(0, info.val, "Reference solve should succeed");

        // Solve using test implementation
        info.val = 0;
        lapack.sptsv(N, 1, d_actual, 0, e_actual, 0, b_actual, 0, N, info);
        assertEquals(0, info.val, "Solve should succeed");

        // Compare solutions
        assertArrayEquals(b_expected, b_actual, Math.scalb(sepsilon, Math.getExponent(getMaxValue(b_expected)) + 2));
    }
}
