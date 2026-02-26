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

public class DlatbsTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        int kd = 2; // number of super/sub diagonals

        // Build upper triangular banded matrix in banded storage
        int ldab = kd + 1;
        double[] ab = new double[ldab * n];
        for (int j = 0; j < n; j++) {
            for (int i = Math.max(0, j - kd); i <= j; i++) {
                ab[(kd + i - j) + j * ldab] = (i == j) ? (n + 1.0) : 1.0;
            }
        }

        double[] x_expected = generateDoubleArray(n, 1.0);
        double[] cnorm_expected = new double[n];
        doubleW scale_expected = new doubleW(0.0);
        intW info_expected = new intW(0);
        f2j.dlatbs("U", "N", "N", "N", n, kd, ab.clone(), 0, ldab, x_expected, 0, scale_expected, cnorm_expected, 0, info_expected);

        double[] x_actual = generateDoubleArray(n, 1.0);
        double[] cnorm_actual = new double[n];
        doubleW scale_actual = new doubleW(0.0);
        intW info_actual = new intW(0);
        lapack.dlatbs("U", "N", "N", "N", n, kd, ab.clone(), 0, ldab, x_actual, 0, scale_actual, cnorm_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertEquals(scale_expected.val, scale_actual.val, depsilon);
        assertArrayEquals(x_expected, x_actual, depsilon);
    }
}
