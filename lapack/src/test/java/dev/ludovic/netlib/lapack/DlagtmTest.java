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

public class DlagtmTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // B := alpha * T * X + beta * B for tridiagonal T
        int n = 5;
        int nrhs = 2;
        double[] dl = { 0.5, 0.5, 0.5, 0.5 }; // sub-diagonal (n-1)
        double[] d = { 4.0, 5.0, 6.0, 7.0, 8.0 }; // diagonal
        double[] du = { 1.0, 1.0, 1.0, 1.0 }; // super-diagonal (n-1)
        double[] x = new double[n * nrhs];
        for (int i = 0; i < n * nrhs; i++) x[i] = (i + 1) * 0.1;
        double[] b_expected = new double[n * nrhs];
        for (int i = 0; i < n * nrhs; i++) b_expected[i] = 1.0;
        double[] b_actual = b_expected.clone();

        f2j.dlagtm("N", n, nrhs, 1.0, dl, d, du, x, n, 1.0, b_expected, n);
        lapack.dlagtm("N", n, nrhs, 1.0, dl, d, du, x, n, 1.0, b_actual, n);

        assertArrayEquals(b_expected, b_actual, depsilon);
    }
}
