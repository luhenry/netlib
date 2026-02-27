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

public class SsyrfsTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testUpper(LAPACK lapack) {
        int n = N_SMALL;
        int nrhs = 1;

        float[] a = new float[n * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                float val = (i == j) ? (i % 2 == 0 ? 10.0f : -5.0f) : 1.0f / (i + j + 1.0f);
                a[i + j * n] = val;
                a[j + i * n] = val;
            }
        }

        float[] b = generateFloatArray(n, 1.0f);

        float[] af = a.clone();
        int[] ipiv = new int[n];
        float[] work_f = new float[n * 64];
        intW info = new intW(0);
        f2j.ssytrf("U", n, af, n, ipiv, work_f, work_f.length, info);
        assertEquals(0, info.val);

        float[] x = b.clone();
        info.val = 0;
        f2j.ssytrs("U", n, nrhs, af, n, ipiv, x, n, info);
        assertEquals(0, info.val);

        float[] x_expected = x.clone();
        float[] x_actual = x.clone();
        float[] ferr_expected = new float[nrhs];
        float[] ferr_actual = new float[nrhs];
        float[] berr_expected = new float[nrhs];
        float[] berr_actual = new float[nrhs];
        float[] work_expected = new float[3 * n];
        float[] work_actual = new float[3 * n];
        int[] iwork_expected = new int[n];
        int[] iwork_actual = new int[n];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.ssyrfs("U", n, nrhs, a, n, af, n, ipiv, b, n, x_expected, n, ferr_expected, berr_expected, work_expected, iwork_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.ssyrfs("U", n, nrhs, a, n, af, n, ipiv, b, n, x_actual, n, ferr_actual, berr_actual, work_actual, iwork_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(x_expected, x_actual, sepsilon);
        assertArrayEquals(ferr_expected, ferr_actual, sepsilon);
        assertArrayEquals(berr_expected, berr_actual, sepsilon);
    }
}
