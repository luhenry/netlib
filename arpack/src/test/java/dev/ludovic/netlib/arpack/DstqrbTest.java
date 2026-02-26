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

public class DstqrbTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        int n = 10;

        // Test case 1: Simple tridiagonal matrix
        {
            double[] expected_d = generateDoubleRange(n, 1.0);
            double[] expected_e = generateDoubleRange(n - 1, 0.5);
            double[] expected_z = new double[n];
            double[] expected_work = new double[2 * n - 2];
            intW expected_info = new intW(0);

            f2j.dstqrb(n, expected_d, expected_e, expected_z, expected_work, expected_info);

            double[] actual_d = generateDoubleRange(n, 1.0);
            double[] actual_e = generateDoubleRange(n - 1, 0.5);
            double[] actual_z = new double[n];
            double[] actual_work = new double[2 * n - 2];
            intW actual_info = new intW(0);

            arpack.dstqrb(n, actual_d, actual_e, actual_z, actual_work, actual_info);

            double tol_d = Math.max(depsilon, depsilon * getMaxValue(expected_d));
            assertArrayEquals(expected_d, actual_d, tol_d);
            assertArrayEquals(expected_e, actual_e, depsilon);
            assertAbsArrayEquals(expected_z, actual_z, 1e-10);
            assertEquals(expected_info.val, actual_info.val);
        }

        // Test case 2: Different matrix values
        {
            double[] expected_d = generateDoubleRange(n, 2.0);
            double[] expected_e = generateDoubleRange(n - 1, 0.25);
            double[] expected_z = new double[n];
            double[] expected_work = new double[2 * n - 2];
            intW expected_info = new intW(0);

            f2j.dstqrb(n, expected_d, expected_e, expected_z, expected_work, expected_info);

            double[] actual_d = generateDoubleRange(n, 2.0);
            double[] actual_e = generateDoubleRange(n - 1, 0.25);
            double[] actual_z = new double[n];
            double[] actual_work = new double[2 * n - 2];
            intW actual_info = new intW(0);

            arpack.dstqrb(n, actual_d, actual_e, actual_z, actual_work, actual_info);

            double tol_d2 = Math.max(depsilon, depsilon * getMaxValue(expected_d));
            assertArrayEquals(expected_d, actual_d, tol_d2);
            assertArrayEquals(expected_e, actual_e, depsilon);
            assertAbsArrayEquals(expected_z, actual_z, 1e-10);
            assertEquals(expected_info.val, actual_info.val);
        }

        // Test case 3: Smaller matrix size
        {
            int small_n = 5;
            double[] expected_d = generateDoubleRange(small_n, 1.5);
            double[] expected_e = generateDoubleRange(small_n - 1, 0.1);
            double[] expected_z = new double[small_n];
            double[] expected_work = new double[2 * small_n - 2];
            intW expected_info = new intW(0);

            f2j.dstqrb(small_n, expected_d, expected_e, expected_z, expected_work, expected_info);

            double[] actual_d = generateDoubleRange(small_n, 1.5);
            double[] actual_e = generateDoubleRange(small_n - 1, 0.1);
            double[] actual_z = new double[small_n];
            double[] actual_work = new double[2 * small_n - 2];
            intW actual_info = new intW(0);

            arpack.dstqrb(small_n, actual_d, actual_e, actual_z, actual_work, actual_info);

            double tol_d3 = Math.max(depsilon, depsilon * getMaxValue(expected_d));
            assertArrayEquals(expected_d, actual_d, tol_d3);
            assertArrayEquals(expected_e, actual_e, depsilon);
            assertAbsArrayEquals(expected_z, actual_z, 1e-10);
            assertEquals(expected_info.val, actual_info.val);
        }
    }
}
