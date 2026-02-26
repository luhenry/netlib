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

public class SgbequTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Create a banded matrix
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

        float[] r_expected = new float[N];
        float[] c_expected = new float[N];
        floatW rowcnd_expected = new floatW(0.0f);
        floatW colcnd_expected = new floatW(0.0f);
        floatW amax_expected = new floatW(0.0f);
        intW info_expected = new intW(0);
        f2j.sgbequ(N, N, kl, ku, ab, 0, ldab, r_expected, 0, c_expected, 0, rowcnd_expected, colcnd_expected, amax_expected, info_expected);

        float[] r_actual = new float[N];
        float[] c_actual = new float[N];
        floatW rowcnd_actual = new floatW(0.0f);
        floatW colcnd_actual = new floatW(0.0f);
        floatW amax_actual = new floatW(0.0f);
        intW info_actual = new intW(0);
        lapack.sgbequ(N, N, kl, ku, ab, 0, ldab, r_actual, 0, c_actual, 0, rowcnd_actual, colcnd_actual, amax_actual, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(r_expected, r_actual, sepsilon);
        assertArrayEquals(c_expected, c_actual, sepsilon);
        assertEquals(rowcnd_expected.val, rowcnd_actual.val, sepsilon);
        assertEquals(colcnd_expected.val, colcnd_actual.val, sepsilon);
        assertEquals(amax_expected.val, amax_actual.val, sepsilon);
    }
}
