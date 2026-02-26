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

public class Dlaed4Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Solve secular equation for rank-one update of diagonal matrix
        // diag(D) + RHO * Z * Z^T
        int n = 5;
        double[] d = { 1.0, 2.0, 3.0, 5.0, 8.0 }; // sorted eigenvalues
        double[] z = { 0.5, 0.4, 0.3, 0.2, 0.1 }; // updating vector
        double rho = 1.0;

        // Compute all eigenvalues to validate
        for (int i = 1; i <= n; i++) {
            double[] delta_expected = new double[n];
            double[] delta_actual = new double[n];
            doubleW dlam_expected = new doubleW(0);
            doubleW dlam_actual = new doubleW(0);
            intW info_expected = new intW(0);
            intW info_actual = new intW(0);

            f2j.dlaed4(n, i, d, z, delta_expected, rho, dlam_expected, info_expected);
            lapack.dlaed4(n, i, d, z, delta_actual, rho, dlam_actual, info_actual);

            assertEquals(0, info_expected.val);
            assertEquals(info_expected.val, info_actual.val);
            assertEquals(dlam_expected.val, dlam_actual.val, depsilon);
            assertArrayEquals(delta_expected, delta_actual, depsilon);
        }
    }
}
