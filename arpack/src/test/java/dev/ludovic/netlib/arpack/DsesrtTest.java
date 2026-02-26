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

import static dev.ludovic.netlib.test.TestHelpers.*;

public class DsesrtTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        int n = 10;
        int na = 10;
        int lda = 10;

        // Test case 1: "LA" (largest algebraic) with apply=true
        {
            double[] expected_x = dArray1.clone();
            double[] expected_a = new double[na * lda];
            for (int i = 0; i < na; i++) {
                for (int j = 0; j < lda; j++) {
                    expected_a[i * lda + j] = 1.0 + i * 0.1 + j * 0.01;
                }
            }

            double[] actual_x = expected_x.clone();
            double[] actual_a = expected_a.clone();

            f2j.dsesrt("LA", true, n, expected_x, na, expected_a, lda);
            arpack.dsesrt("LA", true, n, actual_x, na, actual_a, lda);

            assertArrayEquals(expected_x, actual_x, depsilon);
            assertArrayEquals(expected_a, actual_a, depsilon);
        }

        // Test case 2: "LA" (largest algebraic) with apply=false
        {
            double[] expected_x = dArray1.clone();
            double[] expected_a = new double[na * lda];
            for (int i = 0; i < na; i++) {
                for (int j = 0; j < lda; j++) {
                    expected_a[i * lda + j] = 1.0 + i * 0.1 + j * 0.01;
                }
            }

            double[] actual_x = expected_x.clone();
            double[] actual_a = expected_a.clone();

            f2j.dsesrt("LA", false, n, expected_x, na, expected_a, lda);
            arpack.dsesrt("LA", false, n, actual_x, na, actual_a, lda);

            assertArrayEquals(expected_x, actual_x, depsilon);
            assertArrayEquals(expected_a, actual_a, depsilon);
        }

        // Test case 3: "SA" (smallest algebraic) with apply=true
        {
            double[] expected_x = dArray1.clone();
            double[] expected_a = new double[na * lda];
            for (int i = 0; i < na; i++) {
                for (int j = 0; j < lda; j++) {
                    expected_a[i * lda + j] = 2.0 + i * 0.15 + j * 0.02;
                }
            }

            double[] actual_x = expected_x.clone();
            double[] actual_a = expected_a.clone();

            f2j.dsesrt("SA", true, n, expected_x, na, expected_a, lda);
            arpack.dsesrt("SA", true, n, actual_x, na, actual_a, lda);

            assertArrayEquals(expected_x, actual_x, depsilon);
            assertArrayEquals(expected_a, actual_a, depsilon);
        }

        // Test case 4: "SA" (smallest algebraic) with apply=false
        {
            double[] expected_x = dArray1.clone();
            double[] expected_a = new double[na * lda];
            for (int i = 0; i < na; i++) {
                for (int j = 0; j < lda; j++) {
                    expected_a[i * lda + j] = 2.0 + i * 0.15 + j * 0.02;
                }
            }

            double[] actual_x = expected_x.clone();
            double[] actual_a = expected_a.clone();

            f2j.dsesrt("SA", false, n, expected_x, na, expected_a, lda);
            arpack.dsesrt("SA", false, n, actual_x, na, actual_a, lda);

            assertArrayEquals(expected_x, actual_x, depsilon);
            assertArrayEquals(expected_a, actual_a, depsilon);
        }

        // Test case 5: Smaller size with "LA"
        {
            int small_n = 5;
            int small_na = 5;
            double[] expected_x = generateDoubleRange(small_n, 1.5);
            double[] expected_a = new double[small_na * lda];
            for (int i = 0; i < small_na; i++) {
                for (int j = 0; j < lda; j++) {
                    expected_a[i * lda + j] = 3.0 + i * 0.2 + j * 0.03;
                }
            }

            double[] actual_x = expected_x.clone();
            double[] actual_a = expected_a.clone();

            f2j.dsesrt("LA", true, small_n, expected_x, small_na, expected_a, lda);
            arpack.dsesrt("LA", true, small_n, actual_x, small_na, actual_a, lda);

            assertArrayEquals(expected_x, actual_x, depsilon);
            assertArrayEquals(expected_a, actual_a, depsilon);
        }

        // Test case 6: "BE" (both ends)
        {
            double[] expected_x = dArray2.clone();
            double[] expected_a = new double[na * lda];
            for (int i = 0; i < na; i++) {
                for (int j = 0; j < lda; j++) {
                    expected_a[i * lda + j] = 1.5 + i * 0.12 + j * 0.015;
                }
            }

            double[] actual_x = expected_x.clone();
            double[] actual_a = expected_a.clone();

            f2j.dsesrt("BE", true, n, expected_x, na, expected_a, lda);
            arpack.dsesrt("BE", true, n, actual_x, na, actual_a, lda);

            assertArrayEquals(expected_x, actual_x, depsilon);
            assertArrayEquals(expected_a, actual_a, depsilon);
        }
    }
}
