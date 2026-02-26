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

public class Dorg2rTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;
        int k = Math.min(m, n);

        // First, perform QR factorization to get elementary reflectors
        double[] a = dMatrix.clone();
        double[] tau = new double[k];
        double[] work = new double[n];
        intW info = new intW(0);

        lapack.dgeqrf(m, n, a, 0, m, tau, 0, work, 0, n, info);
        assertEquals(0, info.val, "QR factorization should succeed");

        // Now generate explicit Q matrix using dorg2r
        double[] q = a.clone();
        

        lapack.dorg2r(m, n, k, q, 0, m, tau, 0, work, 0, info);
        assertEquals(0, info.val, "dorg2r should succeed");

        // Verify orthogonality: Q^T * Q should be identity
        double[] qtq = new double[n * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                double sum = 0.0;
                for (int l = 0; l < m; l++) {
                    sum += q[l + i * m] * q[l + j * m];
                }
                qtq[i + j * n] = sum;
            }
        }

        // Check if QtQ is identity
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double expected = (i == j) ? 1.0 : 0.0;
                assertEquals(expected, qtq[i + j * n], depsilon * n * 100,
                    "Q^T * Q should be identity at (" + i + "," + j + ")");
            }
        }
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testCompareWithReference(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;
        int k = Math.min(m, n);

        // Generate QR factorization
        double[] a = generateMatrix(m, n, 1.0);
        double[] tau = new double[k];
        double[] work = new double[n];
        intW info = new intW(0);

        lapack.dgeqrf(m, n, a, 0, m, tau, 0, work, 0, n, info);

        // Generate Q with test implementation
        double[] q_test = a.clone();
        double[] tau_test = tau.clone();
        lapack.dorg2r(m, n, k, q_test, 0, m, tau_test, 0, work, 0, info);

        // Generate Q with reference implementation
        double[] q_ref = a.clone();
        double[] tau_ref = tau.clone();
        f2j.dorg2r(m, n, k, q_ref, 0, m, tau_ref, 0, work, 0, info);

        // Compare results
        assertArrayEquals(q_ref, q_test, Math.scalb(depsilon, Math.getExponent(getMaxValue(q_ref)) + 4));
    }
}
