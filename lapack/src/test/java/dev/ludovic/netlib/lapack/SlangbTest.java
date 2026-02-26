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

public class SlangbTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Create a banded matrix with KL lower and KU upper diagonals
        int kl = 2;
        int ku = 3;
        int ldab = kl + ku + 1;
        float[] ab = new float[ldab * N];

        // Fill banded storage format
        for (int j = 0; j < N; j++) {
            for (int i = Math.max(0, j - ku); i <= Math.min(N - 1, j + kl); i++) {
                ab[ku + i - j + j * ldab] = sMatrix[i + j * N];
            }
        }
        float[] work = new float[N];

        // Test 1-norm
        float expected = f2j.slangb("1", N, kl, ku, ab, 0, ldab, work, 0);
        float actual = lapack.slangb("1", N, kl, ku, ab, 0, ldab, work, 0);
        assertEquals(expected, actual, Math.scalb(sepsilon, Math.getExponent(expected)));

        // Test Inf-norm
        expected = f2j.slangb("I", N, kl, ku, ab, 0, ldab, work, 0);
        actual = lapack.slangb("I", N, kl, ku, ab, 0, ldab, work, 0);
        assertEquals(expected, actual, Math.scalb(sepsilon, Math.getExponent(expected)));

        // Test Frobenius norm
        expected = f2j.slangb("F", N, kl, ku, ab, 0, ldab, work, 0);
        actual = lapack.slangb("F", N, kl, ku, ab, 0, ldab, work, 0);
        assertEquals(expected, actual, Math.scalb(sepsilon, Math.getExponent(expected)));

        // Test Max norm
        expected = f2j.slangb("M", N, kl, ku, ab, 0, ldab, work, 0);
        actual = lapack.slangb("M", N, kl, ku, ab, 0, ldab, work, 0);
        assertEquals(expected, actual, Math.scalb(sepsilon, Math.getExponent(expected)));
    }
}
