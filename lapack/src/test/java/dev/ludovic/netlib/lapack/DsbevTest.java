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

public class DsbevTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testEigenvaluesOnly(LAPACK lapack) {
        // DSBEV: compute eigenvalues of symmetric banded matrix
        int n = N_SMALL;
        int kd = 2;
        int ldab = kd + 1;

        // Create positive definite symmetric banded matrix (upper storage)
        double[] ab = generateBandedSymmetricMatrix(n, kd, n + 10.0, 0.5);

        double[] ab_expected = ab.clone();
        double[] ab_actual = ab.clone();
        double[] w_expected = new double[n];
        double[] w_actual = new double[n];
        double[] z_expected = new double[1];
        double[] z_actual = new double[1];
        double[] work_expected = new double[Math.max(1, 3 * n - 2)];
        double[] work_actual = new double[Math.max(1, 3 * n - 2)];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dsbev("N", "U", n, kd, ab_expected, ldab, w_expected, z_expected, 1, work_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.dsbev("N", "U", n, kd, ab_actual, ldab, w_actual, z_actual, 1, work_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(w_expected, w_actual, 1e-13);
    }
}
