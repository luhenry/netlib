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

public class SptrfsTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        float[] d_orig = generateFloatArray(n, 2.0f);
        float[] e_orig = generateFloatArray(n - 1, 0.5f);

        float[] d_expected = d_orig.clone();
        float[] e_expected = e_orig.clone();
        intW info = new intW(0);
        f2j.spttrf(n, d_expected, 0, e_expected, 0, info);
        assertEquals(0, info.val);

        float[] d_actual = d_orig.clone();
        float[] e_actual = e_orig.clone();
        info.val = 0;
        lapack.spttrf(n, d_actual, 0, e_actual, 0, info);
        assertEquals(0, info.val);

        float[] b = generateFloatArray(n, 1.0f);
        float[] x_expected = b.clone();
        float[] x_actual = b.clone();
        info.val = 0;
        f2j.spttrs(n, 1, d_expected, 0, e_expected, 0, x_expected, 0, n, info);
        assertEquals(0, info.val);
        info.val = 0;
        lapack.spttrs(n, 1, d_actual, 0, e_actual, 0, x_actual, 0, n, info);
        assertEquals(0, info.val);

        float[] ferr_expected = new float[1];
        float[] ferr_actual = new float[1];
        float[] berr_expected = new float[1];
        float[] berr_actual = new float[1];
        float[] work_expected = new float[2 * n];
        float[] work_actual = new float[2 * n];

        info.val = 0;
        f2j.sptrfs(n, 1, d_orig, 0, e_orig, 0, d_expected, 0, e_expected, 0, b, 0, n, x_expected, 0, n, ferr_expected, 0, berr_expected, 0, work_expected, 0, info);
        assertEquals(0, info.val);
        info.val = 0;
        lapack.sptrfs(n, 1, d_orig, 0, e_orig, 0, d_actual, 0, e_actual, 0, b, 0, n, x_actual, 0, n, ferr_actual, 0, berr_actual, 0, work_actual, 0, info);
        assertEquals(0, info.val);

        assertArrayEquals(x_expected, x_actual, Math.scalb(sepsilon, Math.getExponent(getMaxValue(x_expected))));
        // FERR is an error estimate that can vary between implementations
        assertArrayEquals(ferr_expected, ferr_actual, Math.max(sepsilon, Math.abs(getMaxValue(ferr_expected)) * 5));
        // BERR should be near machine epsilon, but implementations can vary by small factors
        assertArrayEquals(berr_expected, berr_actual, sepsilon * 10);
    }
}
