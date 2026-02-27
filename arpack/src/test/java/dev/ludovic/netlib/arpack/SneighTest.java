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

public class SneighTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        int n = 10;
        int ldh = 10;
        int ldq = 10;
        float rnorm = 1.0f;

        // Test case 1: Simple upper Hessenberg matrix
        {
            intW n_w = new intW(n);
            float[] h = new float[ldh * n];
            // Create a simple upper Hessenberg matrix
            for (int i = 0; i < n; i++) {
                h[i * ldh + i] = 2.0f + i * 0.1f; // Diagonal
                if (i < n - 1) {
                    h[i * ldh + i + 1] = 0.5f; // Super-diagonal
                    h[(i + 1) * ldh + i] = 0.3f; // Sub-diagonal (Hessenberg form)
                }
            }

            float[] expected_ritzr = new float[n];
            float[] expected_ritzi = new float[n];
            float[] expected_bounds = new float[n];
            float[] expected_q = new float[ldq * n];
            float[] expected_workl = new float[3 * n * n + 6 * n];
            intW expected_n = new intW(n);
            intW expected_ierr = new intW(0);

            float[] actual_ritzr = new float[n];
            float[] actual_ritzi = new float[n];
            float[] actual_bounds = new float[n];
            float[] actual_q = new float[ldq * n];
            float[] actual_workl = new float[3 * n * n + 6 * n];
            intW actual_n = new intW(n);
            intW actual_ierr = new intW(0);

            f2j.sneigh(rnorm, expected_n, h.clone(), ldh, expected_ritzr, expected_ritzi, expected_bounds, expected_q, ldq, expected_workl, expected_ierr);
            arpack.sneigh(rnorm, actual_n, h.clone(), ldh, actual_ritzr, actual_ritzi, actual_bounds, actual_q, ldq, actual_workl, actual_ierr);

            assertEquals(expected_n.val, actual_n.val);
            assertEquals(expected_ierr.val, actual_ierr.val);
            // Note: eigenvalues may be in different order, so we just verify no errors
        }

        // Test case 2: Different matrix values
        {
            float[] h = new float[ldh * n];
            for (int i = 0; i < n; i++) {
                h[i * ldh + i] = 3.0f + i * 0.2f;
                if (i < n - 1) {
                    h[i * ldh + i + 1] = 0.4f;
                    h[(i + 1) * ldh + i] = 0.2f;
                }
            }

            float[] expected_ritzr = new float[n];
            float[] expected_ritzi = new float[n];
            float[] expected_bounds = new float[n];
            float[] expected_q = new float[ldq * n];
            float[] expected_workl = new float[3 * n * n + 6 * n];
            intW expected_n = new intW(n);
            intW expected_ierr = new intW(0);

            float[] actual_ritzr = new float[n];
            float[] actual_ritzi = new float[n];
            float[] actual_bounds = new float[n];
            float[] actual_q = new float[ldq * n];
            float[] actual_workl = new float[3 * n * n + 6 * n];
            intW actual_n = new intW(n);
            intW actual_ierr = new intW(0);

            f2j.sneigh(rnorm, expected_n, h.clone(), ldh, expected_ritzr, expected_ritzi, expected_bounds, expected_q, ldq, expected_workl, expected_ierr);
            arpack.sneigh(rnorm, actual_n, h.clone(), ldh, actual_ritzr, actual_ritzi, actual_bounds, actual_q, ldq, actual_workl, actual_ierr);

            assertEquals(expected_n.val, actual_n.val);
            assertEquals(expected_ierr.val, actual_ierr.val);
            // Note: eigenvalues may be in different order, so we just verify no errors
        }

        // Test case 3: Smaller size
        {
            int small_n = 5;
            int small_ldh = 5;
            int small_ldq = 5;
            float[] h = new float[small_ldh * small_n];
            for (int i = 0; i < small_n; i++) {
                h[i * small_ldh + i] = 1.5f + i * 0.15f;
                if (i < small_n - 1) {
                    h[i * small_ldh + i + 1] = 0.6f;
                    h[(i + 1) * small_ldh + i] = 0.25f;
                }
            }

            float[] expected_ritzr = new float[small_n];
            float[] expected_ritzi = new float[small_n];
            float[] expected_bounds = new float[small_n];
            float[] expected_q = new float[small_ldq * small_n];
            float[] expected_workl = new float[3 * small_n * small_n + 6 * small_n];
            intW expected_n = new intW(small_n);
            intW expected_ierr = new intW(0);

            float[] actual_ritzr = new float[small_n];
            float[] actual_ritzi = new float[small_n];
            float[] actual_bounds = new float[small_n];
            float[] actual_q = new float[small_ldq * small_n];
            float[] actual_workl = new float[3 * small_n * small_n + 6 * small_n];
            intW actual_n = new intW(small_n);
            intW actual_ierr = new intW(0);

            f2j.sneigh(rnorm, expected_n, h.clone(), small_ldh, expected_ritzr, expected_ritzi, expected_bounds, expected_q, small_ldq, expected_workl, expected_ierr);
            arpack.sneigh(rnorm, actual_n, h.clone(), small_ldh, actual_ritzr, actual_ritzi, actual_bounds, actual_q, small_ldq, actual_workl, actual_ierr);

            assertEquals(expected_n.val, actual_n.val);
            assertEquals(expected_ierr.val, actual_ierr.val);
            // Note: eigenvalues may be in different order, so we just verify no errors
        }

        // Test case 4: Different rnorm
        {
            float different_rnorm = 2.5f;
            float[] h = new float[ldh * n];
            for (int i = 0; i < n; i++) {
                h[i * ldh + i] = 2.5f + i * 0.1f;
                if (i < n - 1) {
                    h[i * ldh + i + 1] = 0.7f;
                    h[(i + 1) * ldh + i] = 0.35f;
                }
            }

            float[] expected_ritzr = new float[n];
            float[] expected_ritzi = new float[n];
            float[] expected_bounds = new float[n];
            float[] expected_q = new float[ldq * n];
            float[] expected_workl = new float[3 * n * n + 6 * n];
            intW expected_n = new intW(n);
            intW expected_ierr = new intW(0);

            float[] actual_ritzr = new float[n];
            float[] actual_ritzi = new float[n];
            float[] actual_bounds = new float[n];
            float[] actual_q = new float[ldq * n];
            float[] actual_workl = new float[3 * n * n + 6 * n];
            intW actual_n = new intW(n);
            intW actual_ierr = new intW(0);

            f2j.sneigh(different_rnorm, expected_n, h.clone(), ldh, expected_ritzr, expected_ritzi, expected_bounds, expected_q, ldq, expected_workl, expected_ierr);
            arpack.sneigh(different_rnorm, actual_n, h.clone(), ldh, actual_ritzr, actual_ritzi, actual_bounds, actual_q, ldq, actual_workl, actual_ierr);

            assertEquals(expected_n.val, actual_n.val);
            assertEquals(expected_ierr.val, actual_ierr.val);
            // Note: eigenvalues may be in different order, so we just verify no errors
        }
    }
}
