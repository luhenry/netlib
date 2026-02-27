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

public class Dgtts2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testNoTranspose(LAPACK lapack) {
        // DGTTS2 solves A*X=B using tridiagonal LU from DGTTRF
        int n = N_SMALL;
        // Create diagonally dominant tridiagonal matrix
        double[] dl = new double[n - 1]; // subdiagonal
        double[] d = new double[n];      // diagonal
        double[] du = new double[n - 1]; // superdiagonal
        double[] du2 = new double[n - 2]; // second superdiagonal (output from dgttrf)
        for (int i = 0; i < n; i++) {
            d[i] = 10.0;
        }
        for (int i = 0; i < n - 1; i++) {
            dl[i] = 1.0;
            du[i] = 1.0;
        }

        // Factor with dgttrf
        int[] ipiv = new int[n];
        intW info = new intW(0);
        f2j.dgttrf(n, dl, d, du, du2, ipiv, info);
        assertEquals(0, info.val);

        // Create RHS and solve
        double[] b_expected = generateDoubleArray(n, 1.0);
        double[] b_actual = b_expected.clone();

        f2j.dgtts2(0, n, 1, dl, d, du, du2, ipiv, b_expected, n);
        lapack.dgtts2(0, n, 1, dl, d, du, du2, ipiv, b_actual, n);

        assertArrayEquals(b_expected, b_actual, depsilon);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testTranspose(LAPACK lapack) {
        int n = N_SMALL;
        double[] dl = new double[n - 1];
        double[] d = new double[n];
        double[] du = new double[n - 1];
        double[] du2 = new double[n - 2];
        for (int i = 0; i < n; i++) {
            d[i] = 10.0;
        }
        for (int i = 0; i < n - 1; i++) {
            dl[i] = 1.0;
            du[i] = 1.0;
        }

        int[] ipiv = new int[n];
        intW info = new intW(0);
        f2j.dgttrf(n, dl, d, du, du2, ipiv, info);
        assertEquals(0, info.val);

        double[] b_expected = generateDoubleArray(n, 1.0);
        double[] b_actual = b_expected.clone();

        f2j.dgtts2(1, n, 1, dl, d, du, du2, ipiv, b_expected, n);
        lapack.dgtts2(1, n, 1, dl, d, du, du2, ipiv, b_actual, n);

        assertArrayEquals(b_expected, b_actual, depsilon);
    }
}
