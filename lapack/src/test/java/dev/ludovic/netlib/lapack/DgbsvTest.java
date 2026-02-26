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

public class DgbsvTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // DGBSV solves A*X = B for banded matrix using LU factorization
        // Create a banded matrix with KL lower and KU upper diagonals
        int kl = 2;  // number of subdiagonals
        int ku = 2;  // number of superdiagonals
        int ldab = 2 * kl + ku + 1;

        // Create banded matrix in banded storage format
        double[] ab_expected = new double[ldab * N];
        double[] ab_actual = new double[ldab * N];

        // Fill banded matrix (diagonal dominant for stability)
        for (int j = 0; j < N; j++) {
            for (int i = Math.max(0, j - ku); i <= Math.min(N - 1, j + kl); i++) {
                int k = kl + ku + i - j;
                double value = (i == j) ? N + 10.0 : 1.0 / (Math.abs(i - j) + 1.0);
                ab_expected[k + j * ldab] = value;
                ab_actual[k + j * ldab] = value;
            }
        }

        int[] ipiv_expected = new int[N];
        int[] ipiv_actual = new int[N];

        // Create right-hand side B
        double[] b_expected = generateDoubleArray(N, 1.0);
        double[] b_actual = b_expected.clone();

        // Solve using reference implementation
        intW info = new intW(0);
        f2j.dgbsv(N, kl, ku, 1, ab_expected, 0, ldab, ipiv_expected, 0, b_expected, 0, N, info);
        assertEquals(0, info.val, "Reference solve should succeed");

        // Solve using test implementation
        info.val = 0;
        lapack.dgbsv(N, kl, ku, 1, ab_actual, 0, ldab, ipiv_actual, 0, b_actual, 0, N, info);
        assertEquals(0, info.val, "Solve should succeed");

        // Compare solutions
        assertArrayEquals(b_expected, b_actual, Math.scalb(depsilon, Math.getExponent(getMaxValue(b_expected)) + 2));
    }
}
