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

public class SgtconTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Create tridiagonal matrix
        float[] dl = new float[N - 1];
        float[] d = new float[N];
        float[] du = new float[N - 1];

        for (int i = 0; i < N; i++) {
            d[i] = (i + 1) * 2.0f;
        }
        for (int i = 0; i < N - 1; i++) {
            dl[i] = -(i + 1);
            du[i] = (i + 1);
        }

        // Compute norm before factorization
        float anorm = f2j.slangt("1", N, dl, 0, d, 0, du, 0);

        // First factor the tridiagonal matrix using sgttrf
        float[] dl_copy = dl.clone();
        float[] d_copy = d.clone();
        float[] du_copy = du.clone();
        float[] du2 = new float[N - 2];
        int[] ipiv = new int[N];
        intW info = new intW(0);
        f2j.sgttrf(N, dl_copy, 0, d_copy, 0, du_copy, 0, du2, 0, ipiv, 0, info);

        // Test condition number estimation
        floatW rcond_expected = new floatW(0.0f);
        float[] work_expected = new float[2 * N];
        int[] iwork_expected = new int[N];
        intW info_expected = new intW(0);
        f2j.sgtcon("1", N, dl_copy, 0, d_copy, 0, du_copy, 0, du2, 0, ipiv, 0, anorm, rcond_expected, work_expected, 0, iwork_expected, 0, info_expected);

        floatW rcond_actual = new floatW(0.0f);
        float[] work_actual = new float[2 * N];
        int[] iwork_actual = new int[N];
        intW info_actual = new intW(0);
        lapack.sgtcon("1", N, dl_copy, 0, d_copy, 0, du_copy, 0, du2, 0, ipiv, 0, anorm, rcond_actual, work_actual, 0, iwork_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertEquals(rcond_expected.val, rcond_actual.val, sepsilon);
    }
}
