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

public class Dpbtf2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testUpper(LAPACK lapack) {
        // DPBTF2: unblocked Cholesky factorization of positive definite banded matrix
        int n = N_SMALL;
        int kd = 2;
        int ldab = kd + 1;

        double[] ab_expected = generateBandedSymmetricMatrix(n, kd, n + 10.0, 0.5);
        double[] ab_actual = ab_expected.clone();

        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dpbtf2("U", n, kd, ab_expected, ldab, info_expected);
        assertEquals(0, info_expected.val);

        lapack.dpbtf2("U", n, kd, ab_actual, ldab, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(ab_expected, ab_actual, depsilon);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testLower(LAPACK lapack) {
        int n = N_SMALL;
        int kd = 2;
        int ldab = kd + 1;

        double[] ab_expected = new double[ldab * n];
        for (int j = 0; j < n; j++) {
            for (int i = j; i <= Math.min(n - 1, j + kd); i++) {
                int k = i - j;
                ab_expected[k + j * ldab] = (i == j) ? n + 10.0 : 0.5 / (Math.abs(i - j) + 1.0);
            }
        }
        double[] ab_actual = ab_expected.clone();

        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dpbtf2("L", n, kd, ab_expected, ldab, info_expected);
        assertEquals(0, info_expected.val);

        lapack.dpbtf2("L", n, kd, ab_actual, ldab, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(ab_expected, ab_actual, depsilon);
    }
}
