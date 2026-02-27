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

public class SgeconTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // First factor the matrix using sgetrf
        float[] a = sPositiveDefiniteMatrix.clone();
        int[] ipiv = new int[N];
        intW info = new intW(0);
        f2j.sgetrf(N, N, a, 0, N, ipiv, 0, info);

        // Compute 1-norm before factorization
        float anorm = f2j.slange("1", N, N, sPositiveDefiniteMatrix, 0, N, new float[N], 0);

        // Test condition number estimation with 1-norm
        floatW rcond_expected = new floatW(0.0f);
        float[] work_expected = new float[4 * N];
        int[] iwork_expected = new int[N];
        intW info_expected = new intW(0);
        f2j.sgecon("1", N, a, 0, N, anorm, rcond_expected, work_expected, 0, iwork_expected, 0, info_expected);

        floatW rcond_actual = new floatW(0.0f);
        float[] work_actual = new float[4 * N];
        int[] iwork_actual = new int[N];
        intW info_actual = new intW(0);
        lapack.sgecon("1", N, a, 0, N, anorm, rcond_actual, work_actual, 0, iwork_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertEquals(rcond_expected.val, rcond_actual.val, sepsilon);
    }
}
