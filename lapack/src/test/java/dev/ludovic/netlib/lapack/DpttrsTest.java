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

public class DpttrsTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Create tridiagonal positive definite matrix
        double[] d_expected = new double[N];
        double[] d_actual = new double[N];
        double[] e_expected = new double[N - 1];
        double[] e_actual = new double[N - 1];

        // Fill diagonal and off-diagonal
        for (int i = 0; i < N; i++) {
            d_expected[i] = N + 1.0;
            d_actual[i] = N + 1.0;
        }
        for (int i = 0; i < N - 1; i++) {
            e_expected[i] = 0.5;
            e_actual[i] = 0.5;
        }

        // First, factor using dpttrf
        intW info = new intW(0);
        f2j.dpttrf(N, d_expected, 0, e_expected, 0, info);
        assertEquals(0, info.val, "Reference factorization should succeed");

        info.val = 0;
        lapack.dpttrf(N, d_actual, 0, e_actual, 0, info);
        assertEquals(0, info.val, "Factorization should succeed");

        // Create right-hand side B
        double[] b_expected = generateDoubleArray(N, 1.0);
        double[] b_actual = b_expected.clone();

        // Solve using reference implementation
        info.val = 0;
        f2j.dpttrs(N, 1, d_expected, 0, e_expected, 0, b_expected, 0, N, info);
        assertEquals(0, info.val, "Reference solve should succeed");

        // Solve using test implementation
        info.val = 0;
        lapack.dpttrs(N, 1, d_actual, 0, e_actual, 0, b_actual, 0, N, info);
        assertEquals(0, info.val, "Solve should succeed");

        // Compare solutions
        assertArrayEquals(b_expected, b_actual, Math.scalb(depsilon, Math.getExponent(getMaxValue(b_expected))));
    }
}
