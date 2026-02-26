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

public class DormqlTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int m = N;
        int n = N;
        int k = Math.min(m, n);

        // First, perform QL factorization to get elementary reflectors
        double[] a = dMatrix.clone();
        double[] tau = new double[k];
        double[] work = new double[n];
        intW info = new intW(0);

        lapack.dgeqlf(m, n, a, 0, m, tau, 0, work, 0, n, info);
        assertEquals(0, info.val, "QL factorization should succeed");

        // Create a matrix C to apply Q to
        double[] c = generateMatrix(m, n, 2.0);
        double[] c_copy = c.clone();

        // Apply Q from the left: C := Q * C
        lapack.dormql("L", "N", m, n, k, a, 0, m, tau, 0, c, 0, m, work, 0, n, info);
        assertEquals(0, info.val, "dormql should succeed");

        // Verify by generating explicit Q and multiplying
        double[] q = a.clone();
        lapack.dorgql(m, n, k, q, 0, m, tau, 0, work, 0, n, info);

        // Compute Q * C_copy manually
        double[] expected = new double[m * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                double sum = 0.0;
                for (int l = 0; l < n; l++) {
                    sum += q[i + l * m] * c_copy[l + j * m];
                }
                expected[i + j * m] = sum;
            }
        }

        // Compare results
        assertArrayEquals(expected, c, depsilon * m * n * 10);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testCompareWithReference(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;
        int k = Math.min(m, n);

        // Generate QL factorization
        double[] a = generateMatrix(m, n, 1.0);
        double[] tau = new double[k];
        double[] work = new double[n];
        intW info = new intW(0);

        lapack.dgeqlf(m, n, a, 0, m, tau, 0, work, 0, n, info);

        // Create matrix C
        double[] c_test = generateMatrix(m, n, 2.0);
        double[] c_ref = c_test.clone();

        // Apply with test implementation
        lapack.dormql("L", "N", m, n, k, a, 0, m, tau, 0, c_test, 0, m, work, 0, n, info);

        // Apply with reference implementation
        f2j.dormql("L", "N", m, n, k, a, 0, m, tau, 0, c_ref, 0, m, work, 0, n, info);

        // Compare results
        assertArrayEquals(c_ref, c_test, Math.scalb(depsilon, Math.getExponent(getMaxValue(c_ref)) + 4));
    }
}
