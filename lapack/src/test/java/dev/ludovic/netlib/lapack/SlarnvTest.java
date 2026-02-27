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

import static dev.ludovic.netlib.test.TestHelpers.*;

public class SlarnvTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testUniform01(LAPACK lapack) {
        int n = 20;
        int[] iseed_expected = {1, 2, 3, 5};
        int[] iseed_actual = iseed_expected.clone();
        float[] x_expected = new float[n];
        float[] x_actual = new float[n];

        f2j.slarnv(1, iseed_expected, n, x_expected);
        lapack.slarnv(1, iseed_actual, n, x_actual);

        assertArrayEquals(x_expected, x_actual, sepsilon);
        assertArrayEquals(iseed_expected, iseed_actual);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testUniformSymmetric(LAPACK lapack) {
        int n = 20;
        int[] iseed_expected = {7, 11, 13, 17};
        int[] iseed_actual = iseed_expected.clone();
        float[] x_expected = new float[n];
        float[] x_actual = new float[n];

        f2j.slarnv(2, iseed_expected, n, x_expected);
        lapack.slarnv(2, iseed_actual, n, x_actual);

        assertArrayEquals(x_expected, x_actual, sepsilon);
        assertArrayEquals(iseed_expected, iseed_actual);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testNormal(LAPACK lapack) {
        int n = 20;
        int[] iseed_expected = {100, 200, 300, 401};
        int[] iseed_actual = iseed_expected.clone();
        float[] x_expected = new float[n];
        float[] x_actual = new float[n];

        f2j.slarnv(3, iseed_expected, n, x_expected);
        lapack.slarnv(3, iseed_actual, n, x_actual);

        assertArrayEquals(x_expected, x_actual, sepsilon);
        assertArrayEquals(iseed_expected, iseed_actual);
    }
}
