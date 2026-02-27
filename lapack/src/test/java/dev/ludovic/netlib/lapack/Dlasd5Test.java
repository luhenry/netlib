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

public class Dlasd5Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // dlasd5 solves the 2x2 secular equation
        double[] d = {1.0, 3.0};
        double[] z = {0.5, 0.5};
        double rho = 1.0;

        // Test i=1 (first root)
        double[] delta_expected = new double[2];
        double[] work_expected = new double[2];
        doubleW dsigma_expected = new doubleW(0.0);

        f2j.dlasd5(1, d.clone(), 0, z.clone(), 0, delta_expected, 0, rho, dsigma_expected, work_expected, 0);

        double[] delta_actual = new double[2];
        double[] work_actual = new double[2];
        doubleW dsigma_actual = new doubleW(0.0);

        lapack.dlasd5(1, d.clone(), 0, z.clone(), 0, delta_actual, 0, rho, dsigma_actual, work_actual, 0);

        assertEquals(dsigma_expected.val, dsigma_actual.val,
            Math.scalb(depsilon, Math.getExponent(Math.max(Math.abs(dsigma_expected.val), 1.0))));
        assertArrayEquals(delta_expected, delta_actual,
            Math.scalb(depsilon, Math.getExponent(Math.max(getMaxValue(delta_expected), 1.0))));

        // Test i=2 (second root)
        double[] delta2_expected = new double[2];
        double[] work2_expected = new double[2];
        doubleW dsigma2_expected = new doubleW(0.0);

        f2j.dlasd5(2, d.clone(), 0, z.clone(), 0, delta2_expected, 0, rho, dsigma2_expected, work2_expected, 0);

        double[] delta2_actual = new double[2];
        double[] work2_actual = new double[2];
        doubleW dsigma2_actual = new doubleW(0.0);

        lapack.dlasd5(2, d.clone(), 0, z.clone(), 0, delta2_actual, 0, rho, dsigma2_actual, work2_actual, 0);

        assertEquals(dsigma2_expected.val, dsigma2_actual.val,
            Math.scalb(depsilon, Math.getExponent(Math.max(Math.abs(dsigma2_expected.val), 1.0))));
        assertArrayEquals(delta2_expected, delta2_actual,
            Math.scalb(depsilon, Math.getExponent(Math.max(getMaxValue(delta2_expected), 1.0))));
    }
}
