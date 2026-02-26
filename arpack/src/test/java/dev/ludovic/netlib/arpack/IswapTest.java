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

public class IswapTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        int[] expectedX, expectedY, actualX, actualY;

        // Test swapping two arrays with increment 1
        f2j.iswap(N, expectedX = intArray1.clone(), 1, expectedY = intArray2.clone(), 1);
        arpack.iswap(N, actualX = intArray1.clone(), 1, actualY = intArray2.clone(), 1);
        assertArrayEquals(expectedX, actualX);
        assertArrayEquals(expectedY, actualY);

        // Test swapping array with itself (should be no-op)
        f2j.iswap(N, expectedX = intArray1.clone(), 1, expectedY = intArray1.clone(), 1);
        arpack.iswap(N, actualX = intArray1.clone(), 1, actualY = intArray1.clone(), 1);
        assertArrayEquals(expectedX, actualX);
        assertArrayEquals(expectedY, actualY);

        // Test with increment 2 (every other element)
        f2j.iswap(N / 2, expectedX = intArray1.clone(), 2, expectedY = intArray2.clone(), 2);
        arpack.iswap(N / 2, actualX = intArray1.clone(), 2, actualY = intArray2.clone(), 2);
        assertArrayEquals(expectedX, actualX);
        assertArrayEquals(expectedY, actualY);
    }
}
