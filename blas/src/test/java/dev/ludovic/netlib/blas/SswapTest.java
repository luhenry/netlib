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

package dev.ludovic.netlib.blas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class SswapTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        float[] expectedX, expectedY, sXcopy, sYcopy;

        f2j.sswap(M, expectedX = sX.clone(), 1, expectedY = sY.clone(), 1);
        blas.sswap(M, sXcopy = sX.clone(), 1, sYcopy = sY.clone(), 1);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNonUnitStride(BLAS blas) {
        float[] expectedX, expectedY, sXcopy, sYcopy;
        int n = M / 2;

        f2j.sswap(n, expectedX = sX.clone(), 2, expectedY = sY.clone(), 2);
        blas.sswap(n, sXcopy = sX.clone(), 2, sYcopy = sY.clone(), 2);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNegativeStride(BLAS blas) {
        float[] expectedX, expectedY, sXcopy, sYcopy;

        f2j.sswap(M, expectedX = sX.clone(), -1, expectedY = sY.clone(), -1);
        blas.sswap(M, sXcopy = sX.clone(), -1, sYcopy = sY.clone(), -1);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);

        f2j.sswap(M, expectedX = sX.clone(), -1, expectedY = sY.clone(), 1);
        blas.sswap(M, sXcopy = sX.clone(), -1, sYcopy = sY.clone(), 1);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testZeroN(BLAS blas) {
        float[] expectedX = sX.clone();
        float[] expectedY = sY.clone();
        float[] sXcopy = sX.clone();
        float[] sYcopy = sY.clone();

        blas.sswap(0, sXcopy, 1, sYcopy, 1);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOffset(BLAS blas) {
        float[] expectedX, expectedY, sXcopy, sYcopy;
        int n = M / 2;

        f2j.sswap(n, expectedX = sX.clone(), 2, 1, expectedY = sY.clone(), 3, 1);
        blas.sswap(n, sXcopy = sX.clone(), 2, 1, sYcopy = sY.clone(), 3, 1);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOutOfBound(BLAS blas) {
        assertThrows(java.lang.IndexOutOfBoundsException.class, () -> {
            blas.sswap(M + 1, sX.clone(), 1, sY.clone(), 1);
        });
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNullArray(BLAS blas) {
        assertThrows(java.lang.NullPointerException.class, () -> {
            blas.sswap(M, null, 1, sY.clone(), 1);
        });
        assertThrows(java.lang.NullPointerException.class, () -> {
            blas.sswap(M, sX.clone(), 1, null, 1);
        });
    }
}
