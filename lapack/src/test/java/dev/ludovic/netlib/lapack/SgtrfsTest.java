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

public class SgtrfsTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        float[] dl_orig = generateFloatArray(N - 1, 0.5f);
        float[] dl_expected = dl_orig.clone();
        float[] dl_actual = dl_orig.clone();
        float[] d_orig = generateFloatArray(N, 2.0f);
        float[] d_expected = d_orig.clone();
        float[] d_actual = d_orig.clone();
        float[] du_orig = generateFloatArray(N - 1, 0.5f);
        float[] du_expected = du_orig.clone();
        float[] du_actual = du_orig.clone();
        float[] du2_expected = new float[N - 2];
        float[] du2_actual = new float[N - 2];
        int[] ipiv_expected = new int[N];
        int[] ipiv_actual = new int[N];
        intW info = new intW(0);
        f2j.sgttrf(N, dl_expected, 0, d_expected, 0, du_expected, 0, du2_expected, 0, ipiv_expected, 0, info);
        assertEquals(0, info.val);
        info.val = 0;
        lapack.sgttrf(N, dl_actual, 0, d_actual, 0, du_actual, 0, du2_actual, 0, ipiv_actual, 0, info);
        assertEquals(0, info.val);
        float[] b = generateFloatArray(N, 1.0f);
        float[] x_expected = b.clone();
        float[] x_actual = b.clone();
        info.val = 0;
        f2j.sgttrs("N", N, 1, dl_expected, 0, d_expected, 0, du_expected, 0, du2_expected, 0, ipiv_expected, 0, x_expected, 0, N, info);
        assertEquals(0, info.val);
        info.val = 0;
        lapack.sgttrs("N", N, 1, dl_actual, 0, d_actual, 0, du_actual, 0, du2_actual, 0, ipiv_actual, 0, x_actual, 0, N, info);
        assertEquals(0, info.val);
        float[] ferr_expected = new float[1];
        float[] ferr_actual = new float[1];
        float[] berr_expected = new float[1];
        float[] berr_actual = new float[1];
        float[] work_expected = new float[3 * N];
        float[] work_actual = new float[3 * N];
        int[] iwork_expected = new int[N];
        int[] iwork_actual = new int[N];
        info.val = 0;
        f2j.sgtrfs("N", N, 1, dl_orig, 0, d_orig, 0, du_orig, 0, dl_expected, 0, d_expected, 0, du_expected, 0, du2_expected, 0, ipiv_expected, 0, b, 0, N, x_expected, 0, N, ferr_expected, 0, berr_expected, 0, work_expected, 0, iwork_expected, 0, info);
        assertEquals(0, info.val);
        info.val = 0;
        lapack.sgtrfs("N", N, 1, dl_orig, 0, d_orig, 0, du_orig, 0, dl_actual, 0, d_actual, 0, du_actual, 0, du2_actual, 0, ipiv_actual, 0, b, 0, N, x_actual, 0, N, ferr_actual, 0, berr_actual, 0, work_actual, 0, iwork_actual, 0, info);
        assertEquals(0, info.val);
        assertArrayEquals(x_expected, x_actual, Math.scalb(sepsilon, Math.getExponent(getMaxValue(x_expected))));
        // FERR is an error estimate that can vary between implementations
        assertArrayEquals(ferr_expected, ferr_actual, Math.max(sepsilon, Math.abs(getMaxValue(ferr_expected)) * 5));
        // BERR should be near machine epsilon, but implementations can vary by small factors
        assertArrayEquals(berr_expected, berr_actual, sepsilon * 10);
    }
}
