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

public class SptconTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Create symmetric positive definite tridiagonal matrix
        float[] d = new float[N];
        float[] e = new float[N - 1];

        for (int i = 0; i < N; i++) {
            d[i] = (N + 1) * 2.0f;
        }
        for (int i = 0; i < N - 1; i++) {
            e[i] = 1.0f;
        }

        // Compute norm before factorization
        float anorm = f2j.slanst("1", N, d, 0, e, 0);

        // First factor the matrix using spttrf
        float[] d_copy = d.clone();
        float[] e_copy = e.clone();
        intW info = new intW(0);
        f2j.spttrf(N, d_copy, 0, e_copy, 0, info);

        // Test condition number estimation
        floatW rcond_expected = new floatW(0.0f);
        float[] work_expected = new float[N];
        intW info_expected = new intW(0);
        f2j.sptcon(N, d_copy, 0, e_copy, 0, anorm, rcond_expected, work_expected, 0, info_expected);

        floatW rcond_actual = new floatW(0.0f);
        float[] work_actual = new float[N];
        intW info_actual = new intW(0);
        lapack.sptcon(N, d_copy, 0, e_copy, 0, anorm, rcond_actual, work_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertEquals(rcond_expected.val, rcond_actual.val, sepsilon);
    }
}
