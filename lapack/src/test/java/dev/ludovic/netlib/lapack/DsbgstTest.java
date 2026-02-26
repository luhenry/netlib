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

public class DsbgstTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testNoTransformMatrix(LAPACK lapack) {
        int n = N_SMALL;
        int ka = 2;
        int kb = 1;
        int ldab = ka + 1;
        int ldbb = kb + 1;

        // Create positive definite symmetric banded matrix A (upper, ka superdiagonals)
        double[] ab = generateBandedSymmetricMatrix(n, ka, n + 10.0, 0.5);

        // Create positive definite symmetric banded matrix B (upper, kb superdiagonals)
        double[] bb = generateBandedSymmetricMatrix(n, kb, n + 5.0, 0.3);

        // Factor B with split Cholesky (dpbstf)
        intW info = new intW(0);
        f2j.dpbstf("U", n, kb, bb, ldbb, info);
        assertEquals(0, info.val);

        // Now reduce A to standard form
        double[] ab_expected = ab.clone();
        double[] ab_actual = ab.clone();
        double[] x_expected = new double[1];
        double[] x_actual = new double[1];
        double[] work_expected = new double[2 * n];
        double[] work_actual = new double[2 * n];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dsbgst("N", "U", n, ka, kb, ab_expected, ldab, bb, ldbb, x_expected, 1, work_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.dsbgst("N", "U", n, ka, kb, ab_actual, ldab, bb, ldbb, x_actual, 1, work_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(ab_expected, ab_actual, 1e-13);
    }
}
