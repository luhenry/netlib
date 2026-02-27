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

public class Dlasd8Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // dlasd8 finds the square root of each updated singular value.
        // It's called internally by dlasd6 after deflation.
        // Test with well-formed inputs: k=4, distinct dsigma values
        int k = 4;
        int icompq = 1;

        double[] d_expected = new double[k];
        double[] z_expected = new double[]{0.5, 0.4, 0.3, 0.2};
        double[] vf_expected = new double[]{0.5, 0.5, 0.5, 0.5};
        double[] vl_expected = new double[]{0.5, 0.5, 0.5, 0.5};
        double[] difl_expected = new double[k];
        double[] difr_expected = new double[k * 2];
        double[] dsigma_expected = new double[]{0.0, 1.0, 2.0, 3.0};
        double[] work_expected = new double[3 * k];
        intW info_expected = new intW(0);

        f2j.dlasd8(icompq, k, d_expected, 0, z_expected, 0, vf_expected, 0, vl_expected, 0,
            difl_expected, 0, difr_expected, 0, k, dsigma_expected, 0, work_expected, 0, info_expected);

        double[] d_actual = new double[k];
        double[] z_actual = new double[]{0.5, 0.4, 0.3, 0.2};
        double[] vf_actual = new double[]{0.5, 0.5, 0.5, 0.5};
        double[] vl_actual = new double[]{0.5, 0.5, 0.5, 0.5};
        double[] difl_actual = new double[k];
        double[] difr_actual = new double[k * 2];
        double[] dsigma_actual = new double[]{0.0, 1.0, 2.0, 3.0};
        double[] work_actual = new double[3 * k];
        intW info_actual = new intW(0);

        lapack.dlasd8(icompq, k, d_actual, 0, z_actual, 0, vf_actual, 0, vl_actual, 0,
            difl_actual, 0, difr_actual, 0, k, dsigma_actual, 0, work_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(d_expected, d_actual, Math.scalb(depsilon, Math.getExponent(Math.max(getMaxValue(d_expected), 1.0))));
        assertArrayEquals(difl_expected, difl_actual, Math.scalb(depsilon, Math.getExponent(Math.max(getMaxValue(difl_expected), 1.0))));
        assertArrayEquals(z_expected, z_actual, Math.scalb(depsilon, Math.getExponent(Math.max(getMaxValue(z_expected), 1.0))));
    }
}
