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

public class SsortrTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        int n = 10;  // Use smaller size for testing

        // Test case 1: "LA" (largest algebraic) with apply=true
        {
            float[] expected_x1 = sArray1.clone();
            float[] expected_x2 = sArray2.clone();
            f2j.ssortr("LA", true, n, expected_x1, expected_x2);

            float[] actual_x1 = sArray1.clone();
            float[] actual_x2 = sArray2.clone();
            arpack.ssortr("LA", true, n, actual_x1, actual_x2);

            assertArrayEquals(expected_x1, actual_x1, sepsilon);
            assertArrayEquals(expected_x2, actual_x2, sepsilon);
        }

        // Test case 2: "LA" (largest algebraic) with apply=false
        {
            float[] expected_x1 = sArray1.clone();
            float[] expected_x2 = sArray2.clone();
            f2j.ssortr("LA", false, n, expected_x1, expected_x2);

            float[] actual_x1 = sArray1.clone();
            float[] actual_x2 = sArray2.clone();
            arpack.ssortr("LA", false, n, actual_x1, actual_x2);

            assertArrayEquals(expected_x1, actual_x1, sepsilon);
            assertArrayEquals(expected_x2, actual_x2, sepsilon);
        }

        // Test case 3: "SA" (smallest algebraic) with apply=true
        {
            float[] expected_x1 = sArray1.clone();
            float[] expected_x2 = sArray2.clone();
            f2j.ssortr("SA", true, n, expected_x1, expected_x2);

            float[] actual_x1 = sArray1.clone();
            float[] actual_x2 = sArray2.clone();
            arpack.ssortr("SA", true, n, actual_x1, actual_x2);

            assertArrayEquals(expected_x1, actual_x1, sepsilon);
            assertArrayEquals(expected_x2, actual_x2, sepsilon);
        }

        // Test case 4: "SA" (smallest algebraic) with apply=false
        {
            float[] expected_x1 = sArray1.clone();
            float[] expected_x2 = sArray2.clone();
            f2j.ssortr("SA", false, n, expected_x1, expected_x2);

            float[] actual_x1 = sArray1.clone();
            float[] actual_x2 = sArray2.clone();
            arpack.ssortr("SA", false, n, actual_x1, actual_x2);

            assertArrayEquals(expected_x1, actual_x1, sepsilon);
            assertArrayEquals(expected_x2, actual_x2, sepsilon);
        }

        // Test case 5: "LA" with larger size
        {
            int largerN = 25;
            float[] expected_x1 = sArray1.clone();
            float[] expected_x2 = sArray2.clone();
            f2j.ssortr("LA", true, largerN, expected_x1, expected_x2);

            float[] actual_x1 = sArray1.clone();
            float[] actual_x2 = sArray2.clone();
            arpack.ssortr("LA", true, largerN, actual_x1, actual_x2);

            assertArrayEquals(expected_x1, actual_x1, sepsilon);
            assertArrayEquals(expected_x2, actual_x2, sepsilon);
        }
    }
}
