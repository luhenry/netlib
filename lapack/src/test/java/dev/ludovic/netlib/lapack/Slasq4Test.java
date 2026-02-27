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

public class Slasq4Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = 4;
        float[] z = new float[4 * n];
        float[] d = {10.0f, 8.0f, 6.0f, 4.0f};
        float[] e = {0.5f, 0.4f, 0.3f};
        for (int i = 0; i < n; i++) {
            z[4 * i] = d[i] * d[i];
            z[4 * i + 1] = (i < n - 1) ? e[i] * e[i] : 0.0f;
            z[4 * i + 2] = z[4 * i];
            z[4 * i + 3] = z[4 * i + 1];
        }

        float dmin = z[4 * (n - 1)];
        float dmin1 = z[4 * (n - 2)];
        float dmin2 = z[4 * (n - 3)];
        float dn = z[4 * (n - 1)];
        float dn1 = z[4 * (n - 2)];
        float dn2 = z[4 * (n - 3)];

        floatW tau_expected = new floatW(0.0f);
        intW ttype_expected = new intW(0);
        floatW g_expected = new floatW(0.0f);

        f2j.slasq4(1, n, z.clone(), 0, 0, n, dmin, dmin1, dmin2, dn, dn1, dn2, tau_expected, ttype_expected, g_expected);

        floatW tau_actual = new floatW(0.0f);
        intW ttype_actual = new intW(0);
        floatW g_actual = new floatW(0.0f);

        lapack.slasq4(1, n, z.clone(), 0, 0, n, dmin, dmin1, dmin2, dn, dn1, dn2, tau_actual, ttype_actual, g_actual);

        assertEquals(ttype_expected.val, ttype_actual.val);
        assertEquals(tau_expected.val, tau_actual.val, Math.scalb(sepsilon, Math.getExponent(Math.max(Math.abs(tau_expected.val), 1.0f))));
    }
}
