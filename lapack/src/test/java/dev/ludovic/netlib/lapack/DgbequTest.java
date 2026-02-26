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

public class DgbequTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Create a banded matrix
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

        double[] r_expected = new double[N];
        double[] c_expected = new double[N];
        doubleW rowcnd_expected = new doubleW(0.0);
        doubleW colcnd_expected = new doubleW(0.0);
        doubleW amax_expected = new doubleW(0.0);
        intW info_expected = new intW(0);
        f2j.dgbequ(N, N, kl, ku, ab, 0, ldab, r_expected, 0, c_expected, 0, rowcnd_expected, colcnd_expected, amax_expected, info_expected);

        double[] r_actual = new double[N];
        double[] c_actual = new double[N];
        doubleW rowcnd_actual = new doubleW(0.0);
        doubleW colcnd_actual = new doubleW(0.0);
        doubleW amax_actual = new doubleW(0.0);
        intW info_actual = new intW(0);
        lapack.dgbequ(N, N, kl, ku, ab, 0, ldab, r_actual, 0, c_actual, 0, rowcnd_actual, colcnd_actual, amax_actual, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(r_expected, r_actual, depsilon);
        assertArrayEquals(c_expected, c_actual, depsilon);
        assertEquals(rowcnd_expected.val, rowcnd_actual.val, depsilon);
        assertEquals(colcnd_expected.val, colcnd_actual.val, depsilon);
        assertEquals(amax_expected.val, amax_actual.val, depsilon);
    }
}
