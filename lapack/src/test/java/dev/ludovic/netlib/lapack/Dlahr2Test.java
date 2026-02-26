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

public class Dlahr2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // dlahr2 reduces the first NB columns of a general n-by-(n-k+1) matrix
        // so that elements below the k-th subdiagonal are zero.
        // dlahr2 algorithm differs between LAPACK 3.1 (f2j) and newer versions (native).
        org.junit.jupiter.api.Assumptions.assumeFalse(
            lapack instanceof NativeLAPACK || lapack instanceof JNILAPACK);
        int n = 8;
        int k = 2;
        int nb = 2;
        int ncols = n - k + 1;

        double[] a_expected = new double[n * ncols];
        for (int j = 0; j < ncols; j++) {
            for (int i = 0; i < n; i++) {
                a_expected[i + j * n] = (i + 1.0) * (j + 1.0);
            }
        }

        double[] tau_expected = new double[nb];
        double[] t_expected = new double[nb * nb];
        double[] y_expected = new double[n * nb];

        f2j.dlahr2(n, k, nb, a_expected, 0, n, tau_expected, 0,
            t_expected, 0, nb, y_expected, 0, n);

        double[] a_actual = new double[n * ncols];
        for (int j = 0; j < ncols; j++) {
            for (int i = 0; i < n; i++) {
                a_actual[i + j * n] = (i + 1.0) * (j + 1.0);
            }
        }

        double[] tau_actual = new double[nb];
        double[] t_actual = new double[nb * nb];
        double[] y_actual = new double[n * nb];

        lapack.dlahr2(n, k, nb, a_actual, 0, n, tau_actual, 0,
            t_actual, 0, nb, y_actual, 0, n);

        double tol = Math.scalb(depsilon, Math.getExponent(Math.max(getMaxValue(a_expected), 1.0)));
        assertArrayEquals(a_expected, a_actual, tol);
        assertArrayEquals(tau_expected, tau_actual, tol);
        assertArrayEquals(t_expected, t_actual, tol);
        assertArrayEquals(y_expected, y_actual, tol);
    }
}
