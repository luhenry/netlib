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

public class Sgeql2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // v3.1 uses SLARFP, v3.12 uses SLARFG. Verify ||A - Q*L|| < eps * ||A||.
        int n = N_SMALL;
        float[] a_orig = generateMatrixFloat(n, n, 1.0f);
        float[] a = a_orig.clone();
        float[] tau = new float[n];
        float[] work = new float[n];
        intW info = new intW(0);

        lapack.sgeql2(n, n, a, 0, n, tau, 0, work, 0, info);
        assertEquals(0, info.val);

        // Extract L (lower triangle)
        float[] l = new float[n * n];
        for (int j = 0; j < n; j++) {
            for (int i = j; i < n; i++) {
                l[i + j * n] = a[i + j * n];
            }
        }

        // Reconstruct Q using sorgql
        float[] q = a.clone();
        int lwork = n * n;
        float[] work2 = new float[lwork];
        intW info2 = new intW(0);
        lapack.sorgql(n, n, n, q, 0, n, tau, 0, work2, 0, lwork, info2);
        assertEquals(0, info2.val);

        // Compute Q*L using double arithmetic
        double[] ql = new double[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double sum = 0.0;
                for (int k = 0; k < n; k++) {
                    sum += (double) q[i + k * n] * (double) l[k + j * n];
                }
                ql[i + j * n] = sum;
            }
        }

        // Verify ||A - Q*L||_F < eps * ||A||_F
        double normA = 0.0;
        double normDiff = 0.0;
        for (int i = 0; i < n * n; i++) {
            normA += (double) a_orig[i] * (double) a_orig[i];
            double d = (double) a_orig[i] - ql[i];
            normDiff += d * d;
        }
        normA = Math.sqrt(normA);
        normDiff = Math.sqrt(normDiff);
        assertTrue(normDiff < sepsilon * 10 * normA,
            "||A - Q*L|| = " + normDiff + " should be < " + (sepsilon * 10 * normA));
    }
}
