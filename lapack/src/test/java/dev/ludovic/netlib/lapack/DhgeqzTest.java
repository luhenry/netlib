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

public class DhgeqzTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // QZ iteration on Hessenberg-Triangular pair, eigenvalues only
        int n = 4;
        // Upper Hessenberg H (column-major)
        double[] h_expected = {
            4.0, 2.0, 0.0, 0.0,
            1.0, 3.0, 1.0, 0.0,
            0.0, 0.5, 5.0, 0.8,
            0.0, 0.0, 0.3, 2.0
        };
        double[] h_actual = h_expected.clone();
        // Upper triangular T (column-major)
        double[] t_expected = {
            2.0, 0.0, 0.0, 0.0,
            1.0, 3.0, 0.0, 0.0,
            0.0, 1.0, 1.0, 0.0,
            0.0, 0.0, 0.5, 4.0
        };
        double[] t_actual = t_expected.clone();
        double[] alphar_expected = new double[n];
        double[] alphar_actual = new double[n];
        double[] alphai_expected = new double[n];
        double[] alphai_actual = new double[n];
        double[] beta_expected = new double[n];
        double[] beta_actual = new double[n];
        double[] q = new double[1];
        double[] z = new double[1];
        double[] work_expected = new double[n];
        double[] work_actual = new double[n];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dhgeqz("E", "N", "N", n, 1, n, h_expected, n, t_expected, n,
                    alphar_expected, alphai_expected, beta_expected, q, 1, z, 1,
                    work_expected, n, info_expected);
        lapack.dhgeqz("E", "N", "N", n, 1, n, h_actual, n, t_actual, n,
                      alphar_actual, alphai_actual, beta_actual, q, 1, z, 1,
                      work_actual, n, info_actual);

        assertEquals(0, info_expected.val);
        assertEquals(info_expected.val, info_actual.val);

        // Compute eigenvalue ratios and sort for comparison
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
