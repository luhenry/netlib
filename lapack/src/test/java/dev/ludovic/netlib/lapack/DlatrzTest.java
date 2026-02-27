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

import static dev.ludovic.netlib.test.TestHelpers.*;

public class DlatrzTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // RZ factorization of M-by-N upper trapezoidal matrix (N >= M)
        int m = 3;
        int n = 6;
        int l = n - m; // = 3
        // Upper trapezoidal: m-by-n matrix stored column-major with lda=m
        double[] a_expected = {
            1.0, 0.0, 0.0,  // col 1
            2.0, 3.0, 0.0,  // col 2
            4.0, 5.0, 6.0,  // col 3
            0.5, 0.3, 0.7,  // col 4
            0.2, 0.4, 0.6,  // col 5
            0.1, 0.8, 0.9   // col 6
        };
        double[] a_actual = a_expected.clone();
        double[] tau_expected = new double[m];
        double[] tau_actual = new double[m];
        double[] work_expected = new double[m];
        double[] work_actual = new double[m];

        f2j.dlatrz(m, n, l, a_expected, m, tau_expected, work_expected);
        lapack.dlatrz(m, n, l, a_actual, m, tau_actual, work_actual);

        assertArrayEquals(a_expected, a_actual, depsilon);
        assertArrayEquals(tau_expected, tau_actual, depsilon);
    }
}
