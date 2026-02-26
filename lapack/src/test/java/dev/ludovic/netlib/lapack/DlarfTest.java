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

public class DlarfTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Generate a Householder vector first
        double[] v = dArray1.clone();
        doubleW alpha = new doubleW(1.0);
        doubleW tau = new doubleW(0.0);
        f2j.dlarfg(N, alpha, v, 0, 1, tau);

        // Apply the reflector to a matrix (side = 'L')
        double[] c_expected = dMatrix.clone();
        double[] work_expected = new double[N];
        f2j.dlarf("L", N, N, v, 0, 1, tau.val, c_expected, 0, N, work_expected, 0);

        double[] c_actual = dMatrix.clone();
        double[] work_actual = new double[N];
        lapack.dlarf("L", N, N, v, 0, 1, tau.val, c_actual, 0, N, work_actual, 0);

        // Find max value in expected array for relative epsilon
        double maxVal = getMaxValue(c_expected);

        assertArrayEquals(c_expected, c_actual, Math.scalb(depsilon, Math.getExponent(maxVal)));
    }
}
