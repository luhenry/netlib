/*
 * Copyright 2020, 2022, Ludovic Henry
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

import static dev.ludovic.netlib.test.TestHelpers.*;

public class DlangbTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Create a banded matrix with KL lower and KU upper diagonals
        int kl = 2;
        int ku = 3;
        int ldab = kl + ku + 1;
        double[] ab = new double[ldab * N];

        // Fill banded storage format
        for (int j = 0; j < N; j++) {
            for (int i = Math.max(0, j - ku); i <= Math.min(N - 1, j + kl); i++) {
                ab[ku + i - j + j * ldab] = dMatrix[i + j * N];
            }
        }
        double[] work = new double[N];

        // Test 1-norm
        double expected = f2j.dlangb("1", N, kl, ku, ab, 0, ldab, work, 0);
        double actual = lapack.dlangb("1", N, kl, ku, ab, 0, ldab, work, 0);
        assertEquals(expected, actual, Math.scalb(depsilon, Math.getExponent(expected)));

        // Test Inf-norm
        expected = f2j.dlangb("I", N, kl, ku, ab, 0, ldab, work, 0);
        actual = lapack.dlangb("I", N, kl, ku, ab, 0, ldab, work, 0);
        assertEquals(expected, actual, Math.scalb(depsilon, Math.getExponent(expected)));

        // Test Frobenius norm
        expected = f2j.dlangb("F", N, kl, ku, ab, 0, ldab, work, 0);
        actual = lapack.dlangb("F", N, kl, ku, ab, 0, ldab, work, 0);
        assertEquals(expected, actual, Math.scalb(depsilon, Math.getExponent(expected)));

        // Test Max norm
        expected = f2j.dlangb("M", N, kl, ku, ab, 0, ldab, work, 0);
        actual = lapack.dlangb("M", N, kl, ku, ab, 0, ldab, work, 0);
        assertEquals(expected, actual, Math.scalb(depsilon, Math.getExponent(expected)));
    }
}
