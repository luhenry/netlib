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

public class Slasd5Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        float[] d = {1.0f, 3.0f};
        float[] z = {0.5f, 0.5f};
        float rho = 1.0f;

        // Test i=1
        float[] delta_expected = new float[2];
        float[] work_expected = new float[2];
        floatW dsigma_expected = new floatW(0.0f);

        f2j.slasd5(1, d.clone(), 0, z.clone(), 0, delta_expected, 0, rho, dsigma_expected, work_expected, 0);

        float[] delta_actual = new float[2];
        float[] work_actual = new float[2];
        floatW dsigma_actual = new floatW(0.0f);

        lapack.slasd5(1, d.clone(), 0, z.clone(), 0, delta_actual, 0, rho, dsigma_actual, work_actual, 0);

        assertEquals(dsigma_expected.val, dsigma_actual.val,
            Math.scalb(sepsilon, Math.getExponent(Math.max(Math.abs(dsigma_expected.val), 1.0f))));
        assertArrayEquals(delta_expected, delta_actual,
            Math.scalb(sepsilon, Math.getExponent(Math.max(getMaxValue(delta_expected), 1.0f))));

        // Test i=2
        float[] delta2_expected = new float[2];
        float[] work2_expected = new float[2];
        floatW dsigma2_expected = new floatW(0.0f);

        f2j.slasd5(2, d.clone(), 0, z.clone(), 0, delta2_expected, 0, rho, dsigma2_expected, work2_expected, 0);

        float[] delta2_actual = new float[2];
        float[] work2_actual = new float[2];
        floatW dsigma2_actual = new floatW(0.0f);

        lapack.slasd5(2, d.clone(), 0, z.clone(), 0, delta2_actual, 0, rho, dsigma2_actual, work2_actual, 0);

        assertEquals(dsigma2_expected.val, dsigma2_actual.val,
            Math.scalb(sepsilon, Math.getExponent(Math.max(Math.abs(dsigma2_expected.val), 1.0f))));
        assertArrayEquals(delta2_expected, delta2_actual,
            Math.scalb(sepsilon, Math.getExponent(Math.max(getMaxValue(delta2_expected), 1.0f))));
    }
}
