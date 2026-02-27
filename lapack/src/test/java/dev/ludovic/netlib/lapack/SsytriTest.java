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

public class SsytriTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testUpper(LAPACK lapack) {
        int n = N_SMALL;

        float[] a = new float[n * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                float val = (i == j) ? (i % 2 == 0 ? 10.0f : -5.0f) : 1.0f / (i + j + 1.0f);
                a[i + j * n] = val;
                a[j + i * n] = val;
            }
        }

        int[] ipiv = new int[n];
        float[] work_f = new float[n * 64];
        intW info = new intW(0);
        f2j.ssytrf("U", n, a, n, ipiv, work_f, work_f.length, info);
        assertEquals(0, info.val);

        float[] a_expected = a.clone();
        float[] a_actual = a.clone();
        float[] work_expected = new float[n];
        float[] work_actual = new float[n];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.ssytri("U", n, a_expected, n, ipiv, work_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.ssytri("U", n, a_actual, n, ipiv, work_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(a_expected, a_actual, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testLower(LAPACK lapack) {
        int n = N_SMALL;

        float[] a = new float[n * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                float val = (i == j) ? (i % 2 == 0 ? 10.0f : -5.0f) : 1.0f / (i + j + 1.0f);
                a[i + j * n] = val;
                a[j + i * n] = val;
            }
        }

        int[] ipiv = new int[n];
        float[] work_f = new float[n * 64];
        intW info = new intW(0);
        f2j.ssytrf("L", n, a, n, ipiv, work_f, work_f.length, info);
        assertEquals(0, info.val);

        float[] a_expected = a.clone();
        float[] a_actual = a.clone();
        float[] work_expected = new float[n];
        float[] work_actual = new float[n];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.ssytri("L", n, a_expected, n, ipiv, work_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.ssytri("L", n, a_actual, n, ipiv, work_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(a_expected, a_actual, sepsilon);
    }
}
