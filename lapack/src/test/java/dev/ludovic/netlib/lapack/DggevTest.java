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

public class DggevTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Compute generalized eigenvalues of (A, B), no eigenvectors
        int n = 4;
        double[] a_expected = {
            4.0, 1.0, 0.0, 0.5,
            2.0, 3.0, 1.0, 0.0,
            0.0, 0.5, 5.0, 0.3,
            0.3, 0.0, 0.2, 2.0
        };
        double[] a_actual = a_expected.clone();
        double[] b_expected = {
            3.0, 0.0, 0.0, 0.0,
            1.0, 2.0, 0.0, 0.0,
            0.5, 0.3, 4.0, 0.0,
            0.2, 0.1, 0.5, 1.0
        };
        double[] b_actual = b_expected.clone();
        double[] alphar_expected = new double[n];
        double[] alphar_actual = new double[n];
        double[] alphai_expected = new double[n];
        double[] alphai_actual = new double[n];
        double[] beta_expected = new double[n];
        double[] beta_actual = new double[n];
        double[] vl = new double[1];
        double[] vr = new double[1];
        int lwork = 8 * n;
        double[] work_expected = new double[lwork];
        double[] work_actual = new double[lwork];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dggev("N", "N", n, a_expected, n, b_expected, n,
                  alphar_expected, alphai_expected, beta_expected,
                  vl, 1, vr, 1, work_expected, lwork, info_expected);
        lapack.dggev("N", "N", n, a_actual, n, b_actual, n,
                     alphar_actual, alphai_actual, beta_actual,
                     vl, 1, vr, 1, work_actual, lwork, info_actual);

        assertEquals(0, info_expected.val);
        assertEquals(info_expected.val, info_actual.val);

        // Compute eigenvalue ratios and sort
        double[] eig_expected = new double[n];
        double[] eig_actual = new double[n];
        for (int i = 0; i < n; i++) {
            eig_expected[i] = alphar_expected[i] / beta_expected[i];
            eig_actual[i] = alphar_actual[i] / beta_actual[i];
        }
        java.util.Arrays.sort(eig_expected);
        java.util.Arrays.sort(eig_actual);
        assertArrayEquals(eig_expected, eig_actual, depsilon);
    }
}
