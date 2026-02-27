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

public class DlaqgeTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;

        double[] a = new double[m * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                a[i + j * m] = 1.0 / (i + j + 1.0);
            }
        }

        double[] r = new double[m];
        for (int i = 0; i < m; i++) {
            r[i] = 1.0 / (i + 1.0);
        }

        double[] c = new double[n];
        for (int j = 0; j < n; j++) {
            c[j] = 1.0 / (j + 1.0);
        }

        double rowcnd = 0.01;
        double colcnd = 0.01;
        double amax = 1.0;

        double[] a_expected = a.clone();
        double[] a_actual = a.clone();
        StringW equed_expected = new StringW("N");
        StringW equed_actual = new StringW("N");

        f2j.dlaqge(m, n, a_expected, m, r, c, rowcnd, colcnd, amax, equed_expected);
        lapack.dlaqge(m, n, a_actual, m, r, c, rowcnd, colcnd, amax, equed_actual);

        assertArrayEquals(a_expected, a_actual, depsilon);
    }
}
