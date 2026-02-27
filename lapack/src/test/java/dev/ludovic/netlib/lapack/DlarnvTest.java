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

public class DlarnvTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testUniform01(LAPACK lapack) {
        // DLARNV generates random numbers; idist=1 is uniform (0,1)
        int n = 20;
        int[] iseed_expected = {1, 2, 3, 5}; // iseed(4) must be odd
        int[] iseed_actual = iseed_expected.clone();
        double[] x_expected = new double[n];
        double[] x_actual = new double[n];

        f2j.dlarnv(1, iseed_expected, n, x_expected);
        lapack.dlarnv(1, iseed_actual, n, x_actual);

        assertArrayEquals(x_expected, x_actual, depsilon);
        assertArrayEquals(iseed_expected, iseed_actual);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testUniformSymmetric(LAPACK lapack) {
        // idist=2 is uniform (-1,1)
        int n = 20;
        int[] iseed_expected = {7, 11, 13, 17};
        int[] iseed_actual = iseed_expected.clone();
        double[] x_expected = new double[n];
        double[] x_actual = new double[n];

        f2j.dlarnv(2, iseed_expected, n, x_expected);
        lapack.dlarnv(2, iseed_actual, n, x_actual);

        assertArrayEquals(x_expected, x_actual, depsilon);
        assertArrayEquals(iseed_expected, iseed_actual);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testNormal(LAPACK lapack) {
        // idist=3 is normal (0,1)
        int n = 20;
        int[] iseed_expected = {100, 200, 300, 401};
        int[] iseed_actual = iseed_expected.clone();
        double[] x_expected = new double[n];
        double[] x_actual = new double[n];

        f2j.dlarnv(3, iseed_expected, n, x_expected);
        lapack.dlarnv(3, iseed_actual, n, x_actual);

        assertArrayEquals(x_expected, x_actual, depsilon);
        assertArrayEquals(iseed_expected, iseed_actual);
    }
}
