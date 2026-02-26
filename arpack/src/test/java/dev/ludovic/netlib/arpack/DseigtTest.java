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

package dev.ludovic.netlib.arpack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import org.netlib.util.intW;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class DseigtTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        int n = 10;
        int ldh = 10;
        double rnorm = 1.0;

        // Test case 1: Simple tridiagonal matrix
        {
            double[] h = new double[ldh * n];
            // Create a simple tridiagonal matrix in upper Hessenberg form
            for (int i = 0; i < n; i++) {
                h[i * ldh + i] = 2.0 + i * 0.1; // Diagonal
                if (i < n - 1) {
                    h[i * ldh + i + 1] = 0.5; // Super-diagonal
                }
            }

            double[] expected_eig = new double[n];
            double[] expected_bounds = new double[n];
            double[] expected_workl = new double[3 * n];
            intW expected_ierr = new intW(0);

            double[] actual_eig = new double[n];
            double[] actual_bounds = new double[n];
            double[] actual_workl = new double[3 * n];
            intW actual_ierr = new intW(0);

            f2j.dseigt(rnorm, n, h.clone(), ldh, expected_eig, expected_bounds, expected_workl, expected_ierr);
            arpack.dseigt(rnorm, n, h.clone(), ldh, actual_eig, actual_bounds, actual_workl, actual_ierr);

            assertArrayEquals(expected_eig, actual_eig, depsilon);
            assertArrayEquals(expected_bounds, actual_bounds, depsilon);
            assertEquals(expected_ierr.val, actual_ierr.val);
        }

        // Test case 2: Different matrix values
        {
            double[] h = new double[ldh * n];
            for (int i = 0; i < n; i++) {
                h[i * ldh + i] = 3.0 + i * 0.2;
                if (i < n - 1) {
                    h[i * ldh + i + 1] = 0.3;
                }
            }

            double[] expected_eig = new double[n];
            double[] expected_bounds = new double[n];
            double[] expected_workl = new double[3 * n];
            intW expected_ierr = new intW(0);

            double[] actual_eig = new double[n];
            double[] actual_bounds = new double[n];
            double[] actual_workl = new double[3 * n];
            intW actual_ierr = new intW(0);

            f2j.dseigt(rnorm, n, h.clone(), ldh, expected_eig, expected_bounds, expected_workl, expected_ierr);
            arpack.dseigt(rnorm, n, h.clone(), ldh, actual_eig, actual_bounds, actual_workl, actual_ierr);

            assertArrayEquals(expected_eig, actual_eig, depsilon);
            assertArrayEquals(expected_bounds, actual_bounds, depsilon);
            assertEquals(expected_ierr.val, actual_ierr.val);
        }

        // Test case 3: Smaller size
        {
            int small_n = 5;
            int small_ldh = 5;
            double[] h = new double[small_ldh * small_n];
            for (int i = 0; i < small_n; i++) {
                h[i * small_ldh + i] = 1.5 + i * 0.15;
                if (i < small_n - 1) {
                    h[i * small_ldh + i + 1] = 0.4;
                }
            }

            double[] expected_eig = new double[small_n];
            double[] expected_bounds = new double[small_n];
            double[] expected_workl = new double[3 * small_n];
            intW expected_ierr = new intW(0);

            double[] actual_eig = new double[small_n];
            double[] actual_bounds = new double[small_n];
            double[] actual_workl = new double[3 * small_n];
            intW actual_ierr = new intW(0);

            f2j.dseigt(rnorm, small_n, h.clone(), small_ldh, expected_eig, expected_bounds, expected_workl, expected_ierr);
            arpack.dseigt(rnorm, small_n, h.clone(), small_ldh, actual_eig, actual_bounds, actual_workl, actual_ierr);

            assertArrayEquals(expected_eig, actual_eig, depsilon);
            assertArrayEquals(expected_bounds, actual_bounds, depsilon);
            assertEquals(expected_ierr.val, actual_ierr.val);
        }

        // Test case 4: Different rnorm
        {
            double different_rnorm = 2.5;
            double[] h = new double[ldh * n];
            for (int i = 0; i < n; i++) {
                h[i * ldh + i] = 2.5 + i * 0.1;
                if (i < n - 1) {
                    h[i * ldh + i + 1] = 0.6;
                }
            }

            double[] expected_eig = new double[n];
            double[] expected_bounds = new double[n];
            double[] expected_workl = new double[3 * n];
            intW expected_ierr = new intW(0);

            double[] actual_eig = new double[n];
            double[] actual_bounds = new double[n];
            double[] actual_workl = new double[3 * n];
            intW actual_ierr = new intW(0);

            f2j.dseigt(different_rnorm, n, h.clone(), ldh, expected_eig, expected_bounds, expected_workl, expected_ierr);
            arpack.dseigt(different_rnorm, n, h.clone(), ldh, actual_eig, actual_bounds, actual_workl, actual_ierr);

            assertArrayEquals(expected_eig, actual_eig, depsilon);
            assertArrayEquals(expected_bounds, actual_bounds, depsilon);
            assertEquals(expected_ierr.val, actual_ierr.val);
        }
    }
}
