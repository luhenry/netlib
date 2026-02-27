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

public class SsortcTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        int n = 10;  // Use smaller size for testing

        // Test case 1: "LA" (largest algebraic) with apply=true
        {
            float[] expected_xreal = sArray1.clone();
            float[] expected_ximag = sArray2.clone();
            float[] expected_y = sArrayZero.clone();
            f2j.ssortc("LA", true, n, expected_xreal, expected_ximag, expected_y);

            float[] actual_xreal = sArray1.clone();
            float[] actual_ximag = sArray2.clone();
            float[] actual_y = sArrayZero.clone();
            arpack.ssortc("LA", true, n, actual_xreal, actual_ximag, actual_y);

            assertArrayEquals(expected_xreal, actual_xreal, sepsilon);
            assertArrayEquals(expected_ximag, actual_ximag, sepsilon);
            assertArrayEquals(expected_y, actual_y, sepsilon);
        }

        // Test case 2: "LA" (largest algebraic) with apply=false
        {
            float[] expected_xreal = sArray1.clone();
            float[] expected_ximag = sArray2.clone();
            float[] expected_y = sArrayZero.clone();
            f2j.ssortc("LA", false, n, expected_xreal, expected_ximag, expected_y);

            float[] actual_xreal = sArray1.clone();
            float[] actual_ximag = sArray2.clone();
            float[] actual_y = sArrayZero.clone();
            arpack.ssortc("LA", false, n, actual_xreal, actual_ximag, actual_y);

            assertArrayEquals(expected_xreal, actual_xreal, sepsilon);
            assertArrayEquals(expected_ximag, actual_ximag, sepsilon);
            assertArrayEquals(expected_y, actual_y, sepsilon);
        }

        // Test case 3: "SA" (smallest algebraic) with apply=true
        {
            float[] expected_xreal = sArray1.clone();
            float[] expected_ximag = sArray2.clone();
            float[] expected_y = sArrayZero.clone();
            f2j.ssortc("SA", true, n, expected_xreal, expected_ximag, expected_y);

            float[] actual_xreal = sArray1.clone();
            float[] actual_ximag = sArray2.clone();
            float[] actual_y = sArrayZero.clone();
            arpack.ssortc("SA", true, n, actual_xreal, actual_ximag, actual_y);

            assertArrayEquals(expected_xreal, actual_xreal, sepsilon);
            assertArrayEquals(expected_ximag, actual_ximag, sepsilon);
            assertArrayEquals(expected_y, actual_y, sepsilon);
        }

        // Test case 4: "SA" (smallest algebraic) with apply=false
        {
            float[] expected_xreal = sArray1.clone();
            float[] expected_ximag = sArray2.clone();
            float[] expected_y = sArrayZero.clone();
            f2j.ssortc("SA", false, n, expected_xreal, expected_ximag, expected_y);

            float[] actual_xreal = sArray1.clone();
            float[] actual_ximag = sArray2.clone();
            float[] actual_y = sArrayZero.clone();
            arpack.ssortc("SA", false, n, actual_xreal, actual_ximag, actual_y);

            assertArrayEquals(expected_xreal, actual_xreal, sepsilon);
            assertArrayEquals(expected_ximag, actual_ximag, sepsilon);
            assertArrayEquals(expected_y, actual_y, sepsilon);
        }

        // Test case 5: "LA" with larger size
        {
            int largerN = 25;
            float[] expected_xreal = sArray1.clone();
            float[] expected_ximag = sArray2.clone();
            float[] expected_y = sArrayZero.clone();
            f2j.ssortc("LA", true, largerN, expected_xreal, expected_ximag, expected_y);

            float[] actual_xreal = sArray1.clone();
            float[] actual_ximag = sArray2.clone();
            float[] actual_y = sArrayZero.clone();
            arpack.ssortc("LA", true, largerN, actual_xreal, actual_ximag, actual_y);

            assertArrayEquals(expected_xreal, actual_xreal, sepsilon);
            assertArrayEquals(expected_ximag, actual_ximag, sepsilon);
            assertArrayEquals(expected_y, actual_y, sepsilon);
        }
    }
}
