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

public class SaxpyTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        float[] expected, sYcopy;

        f2j.saxpy(M, 2.0f, sX, 1, expected = sY.clone(), 1);
        blas.saxpy(M, 2.0f, sX, 1, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.saxpy(M, 0.0f, sX, 1, expected = sY.clone(), 1);
        blas.saxpy(M, 0.0f, sX, 1, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.saxpy(M, -1.0f, sX, 1, expected = sY.clone(), 1);
        blas.saxpy(M, -1.0f, sX, 1, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNonUnitStride(BLAS blas) {
        float[] expected, sYcopy;
        int n = M / 2;

        f2j.saxpy(n, 2.0f, sX, 2, expected = sY.clone(), 2);
        blas.saxpy(n, 2.0f, sX, 2, sYcopy = sY.clone(), 2);
        assertArrayEquals(expected, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNegativeStride(BLAS blas) {
        float[] expected, sYcopy;

        f2j.saxpy(M, 2.0f, sX, -1, expected = sY.clone(), -1);
        blas.saxpy(M, 2.0f, sX, -1, sYcopy = sY.clone(), -1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.saxpy(M, 2.0f, sX, -1, expected = sY.clone(), 1);
        blas.saxpy(M, 2.0f, sX, -1, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        int n = M / 2;
        f2j.saxpy(n, 2.0f, sX, -2, expected = sY.clone(), -2);
        blas.saxpy(n, 2.0f, sX, -2, sYcopy = sY.clone(), -2);
        assertArrayEquals(expected, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testZeroN(BLAS blas) {
        float[] expected = sY.clone();
        float[] sYcopy = sY.clone();

        blas.saxpy(0, 2.0f, sX, 1, sYcopy, 1);
        assertArrayEquals(expected, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOffset(BLAS blas) {
        float[] expected, sYcopy;
        int n = M / 2;

        f2j.saxpy(n, 2.0f, sX, 2, 1, expected = sY.clone(), 3, 1);
        blas.saxpy(n, 2.0f, sX, 2, 1, sYcopy = sY.clone(), 3, 1);
        assertArrayEquals(expected, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOutOfBound(BLAS blas) {
        assertThrows(java.lang.IndexOutOfBoundsException.class, () -> {
            blas.saxpy(M + 1, 2.0f, sX, 1, sY.clone(), 1);
        });
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNullArray(BLAS blas) {
        assertThrows(java.lang.NullPointerException.class, () -> {
            blas.saxpy(M, 2.0f, null, 1, sY.clone(), 1);
        });
        assertThrows(java.lang.NullPointerException.class, () -> {
            blas.saxpy(M, 2.0f, sX, 1, null, 1);
        });
    }
}
