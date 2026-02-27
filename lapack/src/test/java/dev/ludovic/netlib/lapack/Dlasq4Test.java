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

public class Dlasq4Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // dlasq4 computes a shift tau for the dqds algorithm
        int n = 4;
        double[] z = new double[4 * n];
        double[] d = {10.0, 8.0, 6.0, 4.0};
        double[] e = {0.5, 0.4, 0.3};
        for (int i = 0; i < n; i++) {
            z[4 * i] = d[i] * d[i];
            z[4 * i + 1] = (i < n - 1) ? e[i] * e[i] : 0.0;
            z[4 * i + 2] = z[4 * i];
            z[4 * i + 3] = z[4 * i + 1];
        }

        double dmin = z[4 * (n - 1)];
        double dmin1 = z[4 * (n - 2)];
        double dmin2 = z[4 * (n - 3)];
        double dn = z[4 * (n - 1)];
        double dn1 = z[4 * (n - 2)];
        double dn2 = z[4 * (n - 3)];

        doubleW tau_expected = new doubleW(0.0);
        intW ttype_expected = new intW(0);
        doubleW g_expected = new doubleW(0.0);

        f2j.dlasq4(1, n, z.clone(), 0, 0, n, dmin, dmin1, dmin2, dn, dn1, dn2, tau_expected, ttype_expected, g_expected);

        doubleW tau_actual = new doubleW(0.0);
        intW ttype_actual = new intW(0);
        doubleW g_actual = new doubleW(0.0);

        lapack.dlasq4(1, n, z.clone(), 0, 0, n, dmin, dmin1, dmin2, dn, dn1, dn2, tau_actual, ttype_actual, g_actual);

        assertEquals(ttype_expected.val, ttype_actual.val);
        assertEquals(tau_expected.val, tau_actual.val, Math.scalb(depsilon, Math.getExponent(Math.max(Math.abs(tau_expected.val), 1.0))));
    }
}
