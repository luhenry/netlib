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

public class Sorgr2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;
        int k = Math.min(m, n);

        // First, perform RQ factorization to get elementary reflectors
        float[] a = sMatrix.clone();
        float[] tau = new float[k];
        float[] work = new float[n];
        intW info = new intW(0);

        lapack.sgerqf(m, n, a, 0, m, tau, 0, work, 0, n, info);
        assertEquals(0, info.val, "RQ factorization should succeed");

        // Now generate explicit Q matrix using sorgr2
        float[] q = a.clone();
        

        lapack.sorgr2(m, n, k, q, 0, m, tau, 0, work, 0, info);
        assertEquals(0, info.val, "sorgr2 should succeed");

        // Verify orthogonality: Q^T * Q should be identity
        float[] qtq = new float[n * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                float sum = 0.0f;
                for (int l = 0; l < m; l++) {
                    sum += q[l + i * m] * q[l + j * m];
                }
                qtq[i + j * n] = sum;
            }
        }

        // Check if QtQ is identity
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                float expected = (i == j) ? 1.0f : 0.0f;
                assertEquals(expected, qtq[i + j * n], sepsilon * n * 100,
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

        // Generate RQ factorization
        float[] a = generateMatrixFloat(m, n, 1.0f);
        float[] tau = new float[k];
        float[] work = new float[n];
        intW info = new intW(0);

        lapack.sgerqf(m, n, a, 0, m, tau, 0, work, 0, n, info);

        // Generate Q with test implementation
        float[] q_test = a.clone();
        float[] tau_test = tau.clone();
        lapack.sorgr2(m, n, k, q_test, 0, m, tau_test, 0, work, 0, info);

        // Generate Q with reference implementation
        float[] q_ref = a.clone();
        float[] tau_ref = tau.clone();
        f2j.sorgr2(m, n, k, q_ref, 0, m, tau_ref, 0, work, 0, info);

        // Compare results
        assertArrayEquals(q_ref, q_test, Math.scalb(sepsilon, Math.getExponent(getMaxValue(q_ref)) + 4));
    }
}
