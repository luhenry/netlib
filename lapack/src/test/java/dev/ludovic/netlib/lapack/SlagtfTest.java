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

public class SlagtfTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // LU factorization of (T - lambda*I) for tridiagonal T
        int n = 5;
        float[] a_expected = { 4.0f, 5.0f, 6.0f, 7.0f, 8.0f }; // diagonal
        float[] a_actual = a_expected.clone();
        float lambda = 1.0f;
        float[] b_expected = { 1.0f, 1.0f, 1.0f, 1.0f }; // super-diagonal (n-1)
        float[] b_actual = b_expected.clone();
        float[] c_expected = { 0.5f, 0.5f, 0.5f, 0.5f }; // sub-diagonal (n-1)
        float[] c_actual = c_expected.clone();
        float tol = 0.0f;
        float[] d_expected = new float[n - 2];
        float[] d_actual = new float[n - 2];
        int[] in_expected = new int[n];
        int[] in_actual = new int[n];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.slagtf(n, a_expected, lambda, b_expected, c_expected, tol, d_expected, in_expected, info_expected);
        lapack.slagtf(n, a_actual, lambda, b_actual, c_actual, tol, d_actual, in_actual, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(a_expected, a_actual, sepsilon);
        assertArrayEquals(b_expected, b_actual, sepsilon);
        assertArrayEquals(c_expected, c_actual, sepsilon);
        assertArrayEquals(d_expected, d_actual, sepsilon);
        assertArrayEquals(in_expected, in_actual);
    }
}
