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

public class DgeconTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // First factor the matrix using dgetrf
        double[] a = dPositiveDefiniteMatrix.clone();
        int[] ipiv = new int[N];
        intW info = new intW(0);
        f2j.dgetrf(N, N, a, 0, N, ipiv, 0, info);

        // Compute 1-norm before factorization
        double anorm = f2j.dlange("1", N, N, dPositiveDefiniteMatrix, 0, N, new double[N], 0);

        // Test condition number estimation with 1-norm
        doubleW rcond_expected = new doubleW(0.0);
        double[] work_expected = new double[4 * N];
        int[] iwork_expected = new int[N];
        intW info_expected = new intW(0);
        f2j.dgecon("1", N, a, 0, N, anorm, rcond_expected, work_expected, 0, iwork_expected, 0, info_expected);

        doubleW rcond_actual = new doubleW(0.0);
        double[] work_actual = new double[4 * N];
        int[] iwork_actual = new int[N];
        intW info_actual = new intW(0);
        lapack.dgecon("1", N, a, 0, N, anorm, rcond_actual, work_actual, 0, iwork_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertEquals(rcond_expected.val, rcond_actual.val, depsilon);
    }
}
