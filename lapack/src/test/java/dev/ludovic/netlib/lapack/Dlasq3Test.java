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

public class Dlasq3Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // dlasq3 is an internal LAPACK routine that calls other internal routines.
        // Native LAPACK 3.12 has complex interdependencies. Test only with F2j.
        org.junit.jupiter.api.Assumptions.assumeTrue(lapack instanceof F2jLAPACK);

        // dlasq3 is an internal routine called by dlasq2.
        // Construct a well-formed Z array for a small 4x4 case.
        int n = 4;
        double[] d = {10.0, 8.0, 6.0, 4.0};
        double[] e = {0.5, 0.4, 0.3};

        double[] z_expected = new double[4 * n];
        for (int i = 0; i < n; i++) {
            z_expected[4 * i] = d[i] * d[i];
            z_expected[4 * i + 1] = (i < n - 1) ? d[i] * d[i] * (e[i] / d[i]) * (e[i] / d[i]) : 0.0;
            z_expected[4 * i + 2] = z_expected[4 * i];
            z_expected[4 * i + 3] = z_expected[4 * i + 1];
        }

        intW n0_expected = new intW(n);
        doubleW dmin_expected = new doubleW(0.0);
        doubleW sigma_expected = new doubleW(0.0);
        doubleW desig_expected = new doubleW(0.0);
        doubleW qmax_expected = new doubleW(z_expected[0]);
        intW nfail_expected = new intW(0);
        intW iter_expected = new intW(0);
        intW ndiv_expected = new intW(0);
        intW ttype_expected = new intW(0);
        doubleW dmin1_expected = new doubleW(0.0);
        doubleW dmin2_expected = new doubleW(0.0);
        doubleW dn_expected = new doubleW(0.0);
        doubleW dn1_expected = new doubleW(0.0);
        doubleW dn2_expected = new doubleW(0.0);
        doubleW g_expected = new doubleW(0.0);
        doubleW tau_expected = new doubleW(0.0);

        f2j.dlasq3(1, n0_expected, z_expected, 0, 0, dmin_expected, sigma_expected,
            desig_expected, qmax_expected, nfail_expected, iter_expected, ndiv_expected, true,
            ttype_expected, dmin1_expected, dmin2_expected, dn_expected, dn1_expected, dn2_expected, g_expected, tau_expected);

        double[] z_actual = new double[4 * n];
        for (int i = 0; i < n; i++) {
            z_actual[4 * i] = d[i] * d[i];
            z_actual[4 * i + 1] = (i < n - 1) ? d[i] * d[i] * (e[i] / d[i]) * (e[i] / d[i]) : 0.0;
            z_actual[4 * i + 2] = z_actual[4 * i];
            z_actual[4 * i + 3] = z_actual[4 * i + 1];
        }

        intW n0_actual = new intW(n);
        doubleW dmin_actual = new doubleW(0.0);
        doubleW sigma_actual = new doubleW(0.0);
        doubleW desig_actual = new doubleW(0.0);
        doubleW qmax_actual = new doubleW(z_actual[0]);
        intW nfail_actual = new intW(0);
        intW iter_actual = new intW(0);
        intW ndiv_actual = new intW(0);
        intW ttype_actual = new intW(0);
        doubleW dmin1_actual = new doubleW(0.0);
        doubleW dmin2_actual = new doubleW(0.0);
        doubleW dn_actual = new doubleW(0.0);
        doubleW dn1_actual = new doubleW(0.0);
        doubleW dn2_actual = new doubleW(0.0);
        doubleW g_actual = new doubleW(0.0);
        doubleW tau_actual = new doubleW(0.0);

        lapack.dlasq3(1, n0_actual, z_actual, 0, 0, dmin_actual, sigma_actual,
            desig_actual, qmax_actual, nfail_actual, iter_actual, ndiv_actual, true,
            ttype_actual, dmin1_actual, dmin2_actual, dn_actual, dn1_actual, dn2_actual, g_actual, tau_actual);

        assertEquals(n0_expected.val, n0_actual.val);
        assertRelArrayEquals(z_expected, z_actual, depsilon);
    }
}
