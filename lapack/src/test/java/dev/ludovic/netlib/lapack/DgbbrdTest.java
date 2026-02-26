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

public class DgbbrdTest extends LAPACKTest {

    // Pack a general matrix into banded storage with kl lower and ku upper diagonals
    private double[] packGeneralBanded(double[] matrix, int m, int n, int kl, int ku) {
        int ldab = kl + ku + 1;
        double[] ab = new double[ldab * n];
        for (int j = 0; j < n; j++) {
            for (int i = Math.max(0, j - ku); i <= Math.min(m - 1, j + kl); i++) {
                ab[(kl + ku + i - j) + j * ldab] = matrix[i + j * m];
            }
        }
        return ab;
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int m = N;
        int n = N;
        int kl = 2;
        int ku = 2;
        int ncc = 0;
        int ldab = kl + ku + 1;
        int minmn = Math.min(m, n);
        double[] ab = packGeneralBanded(dMatrix, m, n, kl, ku);
        double[] d = new double[minmn];
        double[] e = new double[minmn - 1];
        double[] q = new double[m * m];
        double[] pt = new double[n * n];
        double[] c = new double[1];
        double[] work = new double[2 * Math.max(m, n)];
        intW info = new intW(0);

        lapack.dgbbrd("N", m, n, ncc, kl, ku, ab, 0, ldab, d, 0, e, 0, q, 0, m, pt, 0, n, c, 0, 1, work, 0, info);

        assertEquals(0, info.val, "dgbbrd should succeed");
    }
}
