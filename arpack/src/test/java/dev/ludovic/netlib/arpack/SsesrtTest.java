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

public class SsesrtTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        int n = 10;
        int na = 10;
        int lda = 10;

        // Test case 1: "LA" (largest algebraic) with apply=true
        {
            float[] expected_x = sArray1.clone();
            float[] expected_a = new float[na * lda];
            for (int i = 0; i < na; i++) {
                for (int j = 0; j < lda; j++) {
                    expected_a[i * lda + j] = 1.0f + i * 0.1f + j * 0.01f;
                }
            }

            float[] actual_x = expected_x.clone();
            float[] actual_a = expected_a.clone();

            f2j.ssesrt("LA", true, n, expected_x, na, expected_a, lda);
            arpack.ssesrt("LA", true, n, actual_x, na, actual_a, lda);

            assertArrayEquals(expected_x, actual_x, sepsilon);
            assertArrayEquals(expected_a, actual_a, sepsilon);
        }

        // Test case 2: "LA" (largest algebraic) with apply=false
        {
            float[] expected_x = sArray1.clone();
            float[] expected_a = new float[na * lda];
            for (int i = 0; i < na; i++) {
                for (int j = 0; j < lda; j++) {
                    expected_a[i * lda + j] = 1.0f + i * 0.1f + j * 0.01f;
                }
            }

            float[] actual_x = expected_x.clone();
            float[] actual_a = expected_a.clone();

            f2j.ssesrt("LA", false, n, expected_x, na, expected_a, lda);
            arpack.ssesrt("LA", false, n, actual_x, na, actual_a, lda);

            assertArrayEquals(expected_x, actual_x, sepsilon);
            assertArrayEquals(expected_a, actual_a, sepsilon);
        }

        // Test case 3: "SA" (smallest algebraic) with apply=true
        {
            float[] expected_x = sArray1.clone();
            float[] expected_a = new float[na * lda];
            for (int i = 0; i < na; i++) {
                for (int j = 0; j < lda; j++) {
                    expected_a[i * lda + j] = 2.0f + i * 0.15f + j * 0.02f;
                }
            }

            float[] actual_x = expected_x.clone();
            float[] actual_a = expected_a.clone();

            f2j.ssesrt("SA", true, n, expected_x, na, expected_a, lda);
            arpack.ssesrt("SA", true, n, actual_x, na, actual_a, lda);

            assertArrayEquals(expected_x, actual_x, sepsilon);
            assertArrayEquals(expected_a, actual_a, sepsilon);
        }

        // Test case 4: "SA" (smallest algebraic) with apply=false
        {
            float[] expected_x = sArray1.clone();
            float[] expected_a = new float[na * lda];
            for (int i = 0; i < na; i++) {
                for (int j = 0; j < lda; j++) {
                    expected_a[i * lda + j] = 2.0f + i * 0.15f + j * 0.02f;
                }
            }

            float[] actual_x = expected_x.clone();
            float[] actual_a = expected_a.clone();

            f2j.ssesrt("SA", false, n, expected_x, na, expected_a, lda);
            arpack.ssesrt("SA", false, n, actual_x, na, actual_a, lda);

            assertArrayEquals(expected_x, actual_x, sepsilon);
            assertArrayEquals(expected_a, actual_a, sepsilon);
        }

        // Test case 5: Smaller size with "LA"
        {
            int small_n = 5;
            int small_na = 5;
            float[] expected_x = generateFloatRange(small_n, 1.5f);
            float[] expected_a = new float[small_na * lda];
            for (int i = 0; i < small_na; i++) {
                for (int j = 0; j < lda; j++) {
                    expected_a[i * lda + j] = 3.0f + i * 0.2f + j * 0.03f;
                }
            }

            float[] actual_x = expected_x.clone();
            float[] actual_a = expected_a.clone();

            f2j.ssesrt("LA", true, small_n, expected_x, small_na, expected_a, lda);
            arpack.ssesrt("LA", true, small_n, actual_x, small_na, actual_a, lda);

            assertArrayEquals(expected_x, actual_x, sepsilon);
            assertArrayEquals(expected_a, actual_a, sepsilon);
        }

        // Test case 6: "BE" (both ends)
        {
            float[] expected_x = sArray2.clone();
            float[] expected_a = new float[na * lda];
            for (int i = 0; i < na; i++) {
                for (int j = 0; j < lda; j++) {
                    expected_a[i * lda + j] = 1.5f + i * 0.12f + j * 0.015f;
                }
            }

            float[] actual_x = expected_x.clone();
            float[] actual_a = expected_a.clone();

            f2j.ssesrt("BE", true, n, expected_x, na, expected_a, lda);
            arpack.ssesrt("BE", true, n, actual_x, na, actual_a, lda);

            assertArrayEquals(expected_x, actual_x, sepsilon);
            assertArrayEquals(expected_a, actual_a, sepsilon);
        }
    }
}
