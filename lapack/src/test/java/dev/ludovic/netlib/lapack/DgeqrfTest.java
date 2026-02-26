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

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;
import org.netlib.util.*;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class DgeqrfTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // v3.1 uses DLARFP, v3.12 uses DLARFG. Verify ||A - Q*R|| < eps * ||A||.
        int n = N_SMALL;
        double[] a_orig = generateMatrix(n, n, 1.0);
        double[] a = a_orig.clone();
        double[] tau = new double[n];
        int lwork = n * n;
        double[] work = new double[lwork];
        intW info = new intW(0);

        lapack.dgeqrf(n, n, a, 0, n, tau, 0, work, 0, lwork, info);
        assertEquals(0, info.val, "QR factorization should succeed");

        // Extract R (upper triangle)
        double[] r = new double[n * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                r[i + j * n] = a[i + j * n];
            }
        }

        // Reconstruct Q using dorgqr
        double[] q = a.clone();
        double[] work2 = new double[lwork];
        intW info2 = new intW(0);
        lapack.dorgqr(n, n, n, q, 0, n, tau, 0, work2, 0, lwork, info2);
        assertEquals(0, info2.val);

        // Verify ||A - Q*R||_F < eps * ||A||_F
        double[] qr = matrixMultiply(q, r, n, n, n);
        double normA = 0.0;
        double normDiff = 0.0;
        for (int i = 0; i < n * n; i++) {
            normA += a_orig[i] * a_orig[i];
            double d = a_orig[i] - qr[i];
            normDiff += d * d;
        }
        normA = Math.sqrt(normA);
        normDiff = Math.sqrt(normDiff);
        assertTrue(normDiff < depsilon * 100 * normA,
            "||A - Q*R|| = " + normDiff + " should be < " + (depsilon * 100 * normA));
    }
}
