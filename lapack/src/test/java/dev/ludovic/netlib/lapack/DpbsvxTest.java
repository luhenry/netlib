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

public class DpbsvxTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // DPBSVX is the expert driver for symmetric positive definite banded matrices
        int kd = 3;  // number of superdiagonals
        int ldab = kd + 1;
        int ldafb = kd + 1;

        // Create banded positive definite matrix in banded storage format (upper)
        double[] ab_expected = generateBandedSymmetricMatrix(N, kd, N + 10.0, 1.0);
        double[] ab_actual = ab_expected.clone();

        double[] afb_expected = new double[ldafb * N];
        double[] afb_actual = new double[ldafb * N];

        // Create right-hand side B and solution X
        double[] b_expected = generateDoubleArray(N, 1.0);
        double[] b_actual = b_expected.clone();
        double[] x_expected = new double[N];
        double[] x_actual = new double[N];

        // Equilibration array
        double[] s_expected = new double[N];
        double[] s_actual = new double[N];

        // Condition and error arrays
        doubleW rcond_expected = new doubleW(0.0);
        doubleW rcond_actual = new doubleW(0.0);
        double[] ferr_expected = new double[1];
        double[] ferr_actual = new double[1];
        double[] berr_expected = new double[1];
        double[] berr_actual = new double[1];
        double[] work_expected = new double[3 * N];
        double[] work_actual = new double[3 * N];
        int[] iwork_expected = new int[N];
        int[] iwork_actual = new int[N];

        StringW equed_expected = new StringW("");
        StringW equed_actual = new StringW("");

        // Solve using reference implementation
        intW info = new intW(0);
        f2j.dpbsvx("N", "U", N, kd, 1, ab_expected, 0, ldab, afb_expected, 0, ldafb,
                   equed_expected, s_expected, 0, b_expected, 0, N, x_expected, 0, N,
                   rcond_expected, ferr_expected, 0, berr_expected, 0,
                   work_expected, 0, iwork_expected, 0, info);
        assertEquals(0, info.val, "Reference solve should succeed");

        // Solve using test implementation
        info.val = 0;
        lapack.dpbsvx("N", "U", N, kd, 1, ab_actual, 0, ldab, afb_actual, 0, ldafb,
                      equed_actual, s_actual, 0, b_actual, 0, N, x_actual, 0, N,
                      rcond_actual, ferr_actual, 0, berr_actual, 0,
                      work_actual, 0, iwork_actual, 0, info);
        assertEquals(0, info.val, "Solve should succeed");

        // Compare solutions
        assertArrayEquals(x_expected, x_actual, Math.scalb(depsilon, Math.getExponent(getMaxValue(x_expected)) + 3));

        // Check that RCOND is reasonable
        assertTrue(rcond_actual.val > 0.0, "RCOND should be positive");
        assertEquals(rcond_expected.val, rcond_actual.val, Math.abs(rcond_expected.val) * 0.1, "RCOND should match");
    }
}
