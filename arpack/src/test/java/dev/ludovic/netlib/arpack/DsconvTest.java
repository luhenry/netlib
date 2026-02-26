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

public class DsconvTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        int n = 10;
        double tol = 1e-6;

        // Test case 1: All values converge (bounds < tol * ritz)
        {
            double[] ritz = generateDoubleRange(n, 5.0);
            double[] bounds = generateDoubleRange(n, 1e-7); // Very small bounds
            intW expected_nconv = new intW(0);
            intW actual_nconv = new intW(0);

            f2j.dsconv(n, ritz.clone(), bounds.clone(), tol, expected_nconv);
            arpack.dsconv(n, ritz.clone(), bounds.clone(), tol, actual_nconv);

            assertEquals(expected_nconv.val, actual_nconv.val);
        }

        // Test case 2: No values converge (large bounds)
        {
            double[] ritz = generateDoubleRange(n, 1.0);
            double[] bounds = generateDoubleRange(n, 1.0); // Large bounds
            intW expected_nconv = new intW(0);
            intW actual_nconv = new intW(0);

            f2j.dsconv(n, ritz.clone(), bounds.clone(), tol, expected_nconv);
            arpack.dsconv(n, ritz.clone(), bounds.clone(), tol, actual_nconv);

            assertEquals(expected_nconv.val, actual_nconv.val);
        }

        // Test case 3: Mixed convergence
        {
            double[] ritz = new double[n];
            double[] bounds = new double[n];
            for (int i = 0; i < n; i++) {
                ritz[i] = 1.0 + i * 0.5;
                bounds[i] = (i < n/2) ? 1e-8 : 1e-4; // Half converge, half don't
            }
            intW expected_nconv = new intW(0);
            intW actual_nconv = new intW(0);

            f2j.dsconv(n, ritz.clone(), bounds.clone(), tol, expected_nconv);
            arpack.dsconv(n, ritz.clone(), bounds.clone(), tol, actual_nconv);

            assertEquals(expected_nconv.val, actual_nconv.val);
        }

        // Test case 4: Smaller size
        {
            int small_n = 5;
            double[] ritz = generateDoubleRange(small_n, 2.0);
            double[] bounds = generateDoubleRange(small_n, 1e-7);
            intW expected_nconv = new intW(0);
            intW actual_nconv = new intW(0);

            f2j.dsconv(small_n, ritz.clone(), bounds.clone(), tol, expected_nconv);
            arpack.dsconv(small_n, ritz.clone(), bounds.clone(), tol, actual_nconv);

            assertEquals(expected_nconv.val, actual_nconv.val);
        }

        // Test case 5: Different tolerance
        {
            double strict_tol = 1e-10;
            double[] ritz = generateDoubleRange(n, 3.0);
            double[] bounds = generateDoubleRange(n, 1e-8);
            intW expected_nconv = new intW(0);
            intW actual_nconv = new intW(0);

            f2j.dsconv(n, ritz.clone(), bounds.clone(), strict_tol, expected_nconv);
            arpack.dsconv(n, ritz.clone(), bounds.clone(), strict_tol, actual_nconv);

            assertEquals(expected_nconv.val, actual_nconv.val);
        }
    }
}
