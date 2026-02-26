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

public class DppconTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Pack the positive definite matrix
        double[] ap = new double[(N * (N + 1)) / 2];
        int idx = 0;
        for (int j = 0; j < N; j++) {
            for (int i = 0; i <= j; i++) {
                ap[idx++] = dPositiveDefiniteMatrix[i + j * N];
            }
        }

        // Compute norm before factorization
        double anorm = f2j.dlansp("1", "U", N, ap, 0, new double[N], 0);

        // First factor the packed matrix using dpptrf
        double[] ap_factored = ap.clone();
        intW info = new intW(0);
        f2j.dpptrf("U", N, ap_factored, 0, info);

        // Test condition number estimation
        doubleW rcond_expected = new doubleW(0.0);
        double[] work_expected = new double[3 * N];
        int[] iwork_expected = new int[N];
        intW info_expected = new intW(0);
        f2j.dppcon("U", N, ap_factored, 0, anorm, rcond_expected, work_expected, 0, iwork_expected, 0, info_expected);

        doubleW rcond_actual = new doubleW(0.0);
        double[] work_actual = new double[3 * N];
        int[] iwork_actual = new int[N];
        intW info_actual = new intW(0);
        lapack.dppcon("U", N, ap_factored, 0, anorm, rcond_actual, work_actual, 0, iwork_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertEquals(rcond_expected.val, rcond_actual.val, depsilon);
    }
}
