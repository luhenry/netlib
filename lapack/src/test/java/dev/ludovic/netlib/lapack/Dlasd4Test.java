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

public class Dlasd4Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // dlasd4 computes the i-th updated singular value of a secular equation
        int n = 4;
        // d must be in ascending order and positive
        double[] d = {1.0, 2.0, 3.0, 4.0};
        // z entries should be non-zero
        double[] z = {0.5, 0.5, 0.5, 0.5};
        double rho = 1.0;

        for (int idx = 0; idx < n; idx++) {
            double[] delta_expected = new double[n];
            double[] work_expected = new double[n];
            doubleW sigma_expected = new doubleW(0.0);
            intW info_expected = new intW(0);

            f2j.dlasd4(n, idx + 1, d.clone(), 0, z.clone(), 0, delta_expected, 0,
                rho, sigma_expected, work_expected, 0, info_expected);

            double[] delta_actual = new double[n];
            double[] work_actual = new double[n];
            doubleW sigma_actual = new doubleW(0.0);
            intW info_actual = new intW(0);

            lapack.dlasd4(n, idx + 1, d.clone(), 0, z.clone(), 0, delta_actual, 0,
                rho, sigma_actual, work_actual, 0, info_actual);

            assertEquals(info_expected.val, info_actual.val);
            assertEquals(sigma_expected.val, sigma_actual.val,
                Math.scalb(depsilon, Math.getExponent(Math.max(Math.abs(sigma_expected.val), 1.0))));
            assertArrayEquals(delta_expected, delta_actual,
                Math.scalb(depsilon, Math.getExponent(Math.max(getMaxValue(delta_expected), 1.0))));
        }
    }
}
