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

public class Dlasq2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // dlasq2 expects a Z array of size 4*n with q and e values squared
        // Z = {q1, qe1, q2, qe2, ..., qn, qen} where qi = d(i)^2, qei = e(i)^2
        int n = 5;
        double[] z_expected = new double[4 * n];
        // Build well-conditioned data: diagonal dominant bidiagonal
        double[] d = {10.0, 8.0, 6.0, 4.0, 2.0};
        double[] e = {0.5, 0.4, 0.3, 0.2};
        for (int i = 0; i < n; i++) {
            z_expected[4 * i] = d[i] * d[i];
            z_expected[4 * i + 1] = (i < n - 1) ? e[i] * e[i] : 0.0;
            z_expected[4 * i + 2] = z_expected[4 * i];
            z_expected[4 * i + 3] = z_expected[4 * i + 1];
        }
        intW info_expected = new intW(0);
        f2j.dlasq2(n, z_expected, 0, info_expected);

        double[] z_actual = new double[4 * n];
        for (int i = 0; i < n; i++) {
            z_actual[4 * i] = d[i] * d[i];
            z_actual[4 * i + 1] = (i < n - 1) ? e[i] * e[i] : 0.0;
            z_actual[4 * i + 2] = z_actual[4 * i];
            z_actual[4 * i + 3] = z_actual[4 * i + 1];
        }
        intW info_actual = new intW(0);
        lapack.dlasq2(n, z_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        // Only compare eigenvalues Z(0:n-1), not iteration counts and diagnostics
        double[] eigenvalues_expected = new double[n];
        double[] eigenvalues_actual = new double[n];
        System.arraycopy(z_expected, 0, eigenvalues_expected, 0, n);
        System.arraycopy(z_actual, 0, eigenvalues_actual, 0, n);
        assertRelArrayEquals(eigenvalues_expected, eigenvalues_actual, depsilon * 10);
    }
}
