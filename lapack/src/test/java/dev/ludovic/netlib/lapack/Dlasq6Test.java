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

public class Dlasq6Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // dlasq6 computes one pass of the dqds algorithm with zero shift
        int n = 4;
        double[] d = {10.0, 8.0, 6.0, 4.0};
        double[] e = {0.5, 0.4, 0.3};

        double[] z_expected = new double[4 * n];
        for (int i = 0; i < n; i++) {
            z_expected[4 * i] = d[i] * d[i];
            z_expected[4 * i + 1] = (i < n - 1) ? e[i] * e[i] : 0.0;
            z_expected[4 * i + 2] = z_expected[4 * i];
            z_expected[4 * i + 3] = z_expected[4 * i + 1];
        }

        doubleW dmin_expected = new doubleW(0.0);
        doubleW dmin1_expected = new doubleW(0.0);
        doubleW dmin2_expected = new doubleW(0.0);
        doubleW dn_expected = new doubleW(0.0);
        doubleW dnm1_expected = new doubleW(0.0);
        doubleW dnm2_expected = new doubleW(0.0);

        f2j.dlasq6(1, n, z_expected, 0, 0, dmin_expected, dmin1_expected,
            dmin2_expected, dn_expected, dnm1_expected, dnm2_expected);

        double[] z_actual = new double[4 * n];
        for (int i = 0; i < n; i++) {
            z_actual[4 * i] = d[i] * d[i];
            z_actual[4 * i + 1] = (i < n - 1) ? e[i] * e[i] : 0.0;
            z_actual[4 * i + 2] = z_actual[4 * i];
            z_actual[4 * i + 3] = z_actual[4 * i + 1];
        }

        doubleW dmin_actual = new doubleW(0.0);
        doubleW dmin1_actual = new doubleW(0.0);
        doubleW dmin2_actual = new doubleW(0.0);
        doubleW dn_actual = new doubleW(0.0);
        doubleW dnm1_actual = new doubleW(0.0);
        doubleW dnm2_actual = new doubleW(0.0);

        lapack.dlasq6(1, n, z_actual, 0, 0, dmin_actual, dmin1_actual,
            dmin2_actual, dn_actual, dnm1_actual, dnm2_actual);

        assertArrayEquals(z_expected, z_actual, Math.scalb(depsilon, Math.getExponent(Math.max(getMaxValue(z_expected), 1.0))));
        assertEquals(dmin_expected.val, dmin_actual.val, Math.scalb(depsilon, Math.getExponent(Math.max(Math.abs(dmin_expected.val), 1.0))));
        assertEquals(dn_expected.val, dn_actual.val, Math.scalb(depsilon, Math.getExponent(Math.max(Math.abs(dn_expected.val), 1.0))));
    }
}
