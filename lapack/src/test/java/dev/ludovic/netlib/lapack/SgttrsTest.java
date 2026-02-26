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

public class SgttrsTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Create tridiagonal matrix
        float[] dl_expected = generateFloatArray(N - 1, 0.5f);
        float[] dl_actual = dl_expected.clone();
        float[] d_expected = generateFloatArray(N, 2.0f);
        float[] d_actual = d_expected.clone();
        float[] du_expected = generateFloatArray(N - 1, 0.5f);
        float[] du_actual = du_expected.clone();
        float[] du2_expected = new float[N - 2];
        float[] du2_actual = new float[N - 2];
        int[] ipiv_expected = new int[N];
        int[] ipiv_actual = new int[N];

        // First, factor using sgttrf
        intW info = new intW(0);
        f2j.sgttrf(N, dl_expected, 0, d_expected, 0, du_expected, 0, du2_expected, 0, ipiv_expected, 0, info);
        assertEquals(0, info.val, "Reference factorization should succeed");

        info.val = 0;
        lapack.sgttrf(N, dl_actual, 0, d_actual, 0, du_actual, 0, du2_actual, 0, ipiv_actual, 0, info);
        assertEquals(0, info.val, "Factorization should succeed");

        // Create right-hand side B
        float[] b_expected = generateFloatArray(N, 1.0f);
        float[] b_actual = b_expected.clone();

        // Solve using reference implementation
        info.val = 0;
        f2j.sgttrs("N", N, 1, dl_expected, 0, d_expected, 0, du_expected, 0, du2_expected, 0, ipiv_expected, 0, b_expected, 0, N, info);
        assertEquals(0, info.val, "Reference solve should succeed");

        // Solve using test implementation
        info.val = 0;
        lapack.sgttrs("N", N, 1, dl_actual, 0, d_actual, 0, du_actual, 0, du2_actual, 0, ipiv_actual, 0, b_actual, 0, N, info);
        assertEquals(0, info.val, "Solve should succeed");

        // Compare solutions
        assertArrayEquals(b_expected, b_actual, Math.scalb(sepsilon, Math.getExponent(getMaxValue(b_expected))));
    }
}
