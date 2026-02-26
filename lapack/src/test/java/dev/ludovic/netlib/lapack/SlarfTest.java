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

public class SlarfTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Generate a Householder vector first
        float[] v = sArray1.clone();
        floatW alpha = new floatW(1.0f);
        floatW tau = new floatW(0.0f);
        f2j.slarfg(N, alpha, v, 0, 1, tau);

        // Apply the reflector to a matrix (side = 'L')
        float[] c_expected = sMatrix.clone();
        float[] work_expected = new float[N];
        f2j.slarf("L", N, N, v, 0, 1, tau.val, c_expected, 0, N, work_expected, 0);

        float[] c_actual = sMatrix.clone();
        float[] work_actual = new float[N];
        lapack.slarf("L", N, N, v, 0, 1, tau.val, c_actual, 0, N, work_actual, 0);

        // Find max value in expected array for relative epsilon
        float maxVal = getMaxValue(c_expected);

        assertArrayEquals(c_expected, c_actual, Math.scalb(sepsilon, Math.getExponent(maxVal)));
    }
}
