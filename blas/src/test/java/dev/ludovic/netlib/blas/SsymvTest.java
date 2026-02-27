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

public class SsymvTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        float[] expected, sYcopy;

        f2j.ssymv("U", M,  1.0f, ssyA, M, sX, 1,  1.0f, expected = sY.clone(), 1);
        blas.ssymv("U", M,  1.0f, ssyA, M, sX, 1,  1.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.ssymv("U", M,  0.5f, ssyA, M, sX, 1,  1.0f, expected = sY.clone(), 1);
        blas.ssymv("U", M,  0.5f, ssyA, M, sX, 1,  1.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.ssymv("U", M, -0.5f, ssyA, M, sX, 1,  1.0f, expected = sY.clone(), 1);
        blas.ssymv("U", M, -0.5f, ssyA, M, sX, 1,  1.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.ssymv("U", M,  0.0f, ssyA, M, sX, 1,  1.0f, expected = sY.clone(), 1);
        blas.ssymv("U", M,  0.0f, ssyA, M, sX, 1,  1.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.ssymv("U", M,  1.0f, ssyA, M, sX, 1,  0.5f, expected = sY.clone(), 1);
        blas.ssymv("U", M,  1.0f, ssyA, M, sX, 1,  0.5f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.ssymv("U", M,  1.0f, ssyA, M, sX, 1, -0.5f, expected = sY.clone(), 1);
        blas.ssymv("U", M,  1.0f, ssyA, M, sX, 1, -0.5f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.ssymv("U", M,  1.0f, ssyA, M, sX, 1,  0.0f, expected = sY.clone(), 1);
        blas.ssymv("U", M,  1.0f, ssyA, M, sX, 1,  0.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.ssymv("L", M,  1.0f, ssyA, M, sX, 1,  1.0f, expected = sY.clone(), 1);
        blas.ssymv("L", M,  1.0f, ssyA, M, sX, 1,  1.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.ssymv("L", M,  0.5f, ssyA, M, sX, 1,  1.0f, expected = sY.clone(), 1);
        blas.ssymv("L", M,  0.5f, ssyA, M, sX, 1,  1.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.ssymv("L", M, -0.5f, ssyA, M, sX, 1,  1.0f, expected = sY.clone(), 1);
        blas.ssymv("L", M, -0.5f, ssyA, M, sX, 1,  1.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.ssymv("L", M,  0.0f, ssyA, M, sX, 1,  1.0f, expected = sY.clone(), 1);
        blas.ssymv("L", M,  0.0f, ssyA, M, sX, 1,  1.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.ssymv("L", M,  1.0f, ssyA, M, sX, 1,  0.5f, expected = sY.clone(), 1);
        blas.ssymv("L", M,  1.0f, ssyA, M, sX, 1,  0.5f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.ssymv("L", M,  1.0f, ssyA, M, sX, 1, -0.5f, expected = sY.clone(), 1);
        blas.ssymv("L", M,  1.0f, ssyA, M, sX, 1, -0.5f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.ssymv("L", M,  1.0f, ssyA, M, sX, 1,  0.0f, expected = sY.clone(), 1);
        blas.ssymv("L", M,  1.0f, ssyA, M, sX, 1,  0.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNonUnitStride(BLAS blas) {
        float[] expected, sYcopy;
        int smallN = M / 2;

        f2j.ssymv("U", smallN, 1.0f, ssyA, M, sX, 2, 1.0f, expected = sY.clone(), 2);
        blas.ssymv("U", smallN, 1.0f, ssyA, M, sX, 2, 1.0f, sYcopy = sY.clone(), 2);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.ssymv("L", smallN, 1.0f, ssyA, M, sX, 2, 1.0f, expected = sY.clone(), 2);
        blas.ssymv("L", smallN, 1.0f, ssyA, M, sX, 2, 1.0f, sYcopy = sY.clone(), 2);
        assertArrayEquals(expected, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaZeroBetaZero(BLAS blas) {
        float[] expected, sYcopy;

        f2j.ssymv("U", M, 0.0f, ssyA, M, sX, 1, 0.0f, expected = sY.clone(), 1);
        blas.ssymv("U", M, 0.0f, ssyA, M, sX, 1, 0.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOffset(BLAS blas) {
        float[] expected, sYcopy;
        int smallN = M / 2;

        f2j.ssymv("U", smallN, 1.0f, ssyA, 0, M, sX, 0, 1, 1.0f, expected = sY.clone(), 0, 1);
        blas.ssymv("U", smallN, 1.0f, ssyA, 0, M, sX, 0, 1, 1.0f, sYcopy = sY.clone(), 0, 1);
        assertArrayEquals(expected, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNegativeStride(BLAS blas) {
        float[] expected, sYcopy;

        f2j.ssymv("U", M, 2.0f, ssyA, M, sX, -1, 2.0f, expected = sY.clone(), -1);
        blas.ssymv("U", M, 2.0f, ssyA, M, sX, -1, 2.0f, sYcopy = sY.clone(), -1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.ssymv("L", M, 2.0f, ssyA, M, sX, -1, 2.0f, expected = sY.clone(), -1);
        blas.ssymv("L", M, 2.0f, ssyA, M, sX, -1, 2.0f, sYcopy = sY.clone(), -1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        // mixed strides
        f2j.ssymv("U", M, 2.0f, ssyA, M, sX, -1, 2.0f, expected = sY.clone(), 1);
        blas.ssymv("U", M, 2.0f, ssyA, M, sX, -1, 2.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testInvalidUplo(BLAS blas) {
        // invalid uplo
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.ssymv("X", M, 1.0f, ssyA, M, sX, 1, 1.0f, sY.clone(), 1);
        });
        // negative n
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.ssymv("U", -1, 1.0f, ssyA, M, sX, 1, 1.0f, sY.clone(), 1);
        });
        // lda too small
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.ssymv("U", M, 1.0f, ssyA, M - 1, sX, 1, 1.0f, sY.clone(), 1);
        });
        // incx == 0
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.ssymv("U", M, 1.0f, ssyA, M, sX, 0, 1.0f, sY.clone(), 1);
        });
        // incy == 0
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.ssymv("U", M, 1.0f, ssyA, M, sX, 1, 1.0f, sY.clone(), 0);
        });
    }
}
