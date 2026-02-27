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

public class SgbtrsTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testNoTranspose(LAPACK lapack) {
        int n = N_SMALL;
        int kl = 2, ku = 2;
        int ldab = 2 * kl + ku + 1;

        float[] ab = new float[ldab * n];
        for (int j = 0; j < n; j++) {
            for (int i = Math.max(0, j - ku); i <= Math.min(n - 1, j + kl); i++) {
                int k = kl + ku + i - j;
                ab[k + j * ldab] = (i == j) ? n + 10.0f : 1.0f / (Math.abs(i - j) + 1.0f);
            }
        }

        int[] ipiv = new int[n];
        intW info = new intW(0);
        f2j.sgbtrf(n, n, kl, ku, ab, ldab, ipiv, info);
        assertEquals(0, info.val);

        float[] b_expected = generateFloatArray(n, 1.0f);
        float[] b_actual = b_expected.clone();

        info.val = 0;
        f2j.sgbtrs("N", n, kl, ku, 1, ab, ldab, ipiv, b_expected, n, info);
        assertEquals(0, info.val);

        info.val = 0;
        lapack.sgbtrs("N", n, kl, ku, 1, ab, ldab, ipiv, b_actual, n, info);
        assertEquals(0, info.val);

        assertArrayEquals(b_expected, b_actual, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testTranspose(LAPACK lapack) {
        int n = N_SMALL;
        int kl = 2, ku = 2;
        int ldab = 2 * kl + ku + 1;

        float[] ab = new float[ldab * n];
        for (int j = 0; j < n; j++) {
            for (int i = Math.max(0, j - ku); i <= Math.min(n - 1, j + kl); i++) {
                int k = kl + ku + i - j;
                ab[k + j * ldab] = (i == j) ? n + 10.0f : 1.0f / (Math.abs(i - j) + 1.0f);
            }
        }

        int[] ipiv = new int[n];
        intW info = new intW(0);
        f2j.sgbtrf(n, n, kl, ku, ab, ldab, ipiv, info);
        assertEquals(0, info.val);

        float[] b_expected = generateFloatArray(n, 1.0f);
        float[] b_actual = b_expected.clone();

        info.val = 0;
        f2j.sgbtrs("T", n, kl, ku, 1, ab, ldab, ipiv, b_expected, n, info);
        assertEquals(0, info.val);

        info.val = 0;
        lapack.sgbtrs("T", n, kl, ku, 1, ab, ldab, ipiv, b_actual, n, info);
        assertEquals(0, info.val);

        assertArrayEquals(b_expected, b_actual, sepsilon);
    }
}
