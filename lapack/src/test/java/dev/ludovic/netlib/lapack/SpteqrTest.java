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

public class SpteqrTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        // compz = "N": eigenvalues only
        // Diagonal must be positive for positive definite tridiagonal
        float[] d_expected = new float[n];
        for (int i = 0; i < n; i++) {
            d_expected[i] = (i + 1) * 2.0f;
        }
        float[] e_expected = generateFloatArray(n - 1, 0.5f);
        float[] z_expected = new float[n * n];
        float[] work_expected = new float[4 * n];
        intW info_expected = new intW(0);
        f2j.spteqr("N", n, d_expected, 0, e_expected, 0, z_expected, 0, n, work_expected, 0, info_expected);

        float[] d_actual = new float[n];
        for (int i = 0; i < n; i++) {
            d_actual[i] = (i + 1) * 2.0f;
        }
        float[] e_actual = generateFloatArray(n - 1, 0.5f);
        float[] z_actual = new float[n * n];
        float[] work_actual = new float[4 * n];
        intW info_actual = new intW(0);
        lapack.spteqr("N", n, d_actual, 0, e_actual, 0, z_actual, 0, n, work_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(d_expected, d_actual, Math.scalb(sepsilon, Math.getExponent(getMaxValue(d_expected))));
    }
}
