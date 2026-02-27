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

public class Dlaed9Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // dlaed9 finds roots of the secular equation between indices kstart and kstop.
        // Similar to dlaed4 but for a range.
        int k = 4;
        int n = k;
        int kstart = 1;
        int kstop = k;
        double rho = 1.0;

        double[] dlamda_expected = {1.0, 2.0, 3.0, 4.0};
        double[] w_expected = {0.5, 0.5, 0.5, 0.5};
        double[] d_expected = new double[n];
        double[] q_expected = new double[n * n];
        double[] s_expected = new double[k * k];
        intW info_expected = new intW(0);

        f2j.dlaed9(k, kstart, kstop, n, d_expected, 0, q_expected, 0, n,
            rho, dlamda_expected, 0, w_expected, 0, s_expected, 0, k, info_expected);

        double[] dlamda_actual = {1.0, 2.0, 3.0, 4.0};
        double[] w_actual = {0.5, 0.5, 0.5, 0.5};
        double[] d_actual = new double[n];
        double[] q_actual = new double[n * n];
        double[] s_actual = new double[k * k];
        intW info_actual = new intW(0);

        lapack.dlaed9(k, kstart, kstop, n, d_actual, 0, q_actual, 0, n,
            rho, dlamda_actual, 0, w_actual, 0, s_actual, 0, k, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(d_expected, d_actual, Math.scalb(depsilon, Math.getExponent(getMaxValue(d_expected))));
        assertArrayEquals(s_expected, s_actual, Math.scalb(depsilon, Math.getExponent(Math.max(getMaxValue(s_expected), 1.0))));
    }
}
