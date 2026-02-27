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

public class SlatpsTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;

        // Upper triangular packed storage
        float[] ap = new float[n * (n + 1) / 2];
        int idx = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                ap[idx++] = (i == j) ? (n + 1.0f) : 1.0f / (i + j + 2.0f);
            }
        }

        float[] x_expected = generateFloatArray(n, 1.0f);
        float[] cnorm_expected = new float[n];
        floatW scale_expected = new floatW(0.0f);
        intW info_expected = new intW(0);
        f2j.slatps("U", "N", "N", "N", n, ap.clone(), 0, x_expected, 0, scale_expected, cnorm_expected, 0, info_expected);

        float[] x_actual = generateFloatArray(n, 1.0f);
        float[] cnorm_actual = new float[n];
        floatW scale_actual = new floatW(0.0f);
        intW info_actual = new intW(0);
        lapack.slatps("U", "N", "N", "N", n, ap.clone(), 0, x_actual, 0, scale_actual, cnorm_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertEquals(scale_expected.val, scale_actual.val, sepsilon);
        assertArrayEquals(x_expected, x_actual, sepsilon);
    }
}
