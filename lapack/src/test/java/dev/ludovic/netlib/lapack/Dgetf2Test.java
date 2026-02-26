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

public class Dgetf2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // DGETF2 computes LU factorization of a general m×n matrix (unblocked)
        // A = P * L * U with partial pivoting
        int n = N_SMALL;
        double[] a_expected = generatePositiveDefiniteMatrix(n);
        double[] a_actual = a_expected.clone();
        int[] ipiv_expected = new int[n];
        int[] ipiv_actual = new int[n];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dgetf2(n, n, a_expected, n, ipiv_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.dgetf2(n, n, a_actual, n, ipiv_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(ipiv_expected, ipiv_actual);
        assertArrayEquals(a_expected, a_actual, depsilon * 100);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testRectangular(LAPACK lapack) {
        // Test with m > n (tall matrix)
        int m = 8, n = 5;
        double[] a_expected = generateMatrix(m, n, 1.0);
        // Make it non-singular by adding diagonal dominance
        for (int i = 0; i < Math.min(m, n); i++) {
            a_expected[i + i * m] += 10.0;
        }
        double[] a_actual = a_expected.clone();
        int[] ipiv_expected = new int[Math.min(m, n)];
        int[] ipiv_actual = new int[Math.min(m, n)];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dgetf2(m, n, a_expected, m, ipiv_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.dgetf2(m, n, a_actual, m, ipiv_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(ipiv_expected, ipiv_actual);
        assertArrayEquals(a_expected, a_actual, depsilon * 100);
    }
}
