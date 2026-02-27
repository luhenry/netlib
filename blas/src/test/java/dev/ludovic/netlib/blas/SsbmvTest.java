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

public class SsbmvTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        float[] expected, sYcopy;
        int lda = KU + 1;

        // uplo=U, alpha=1.0f, beta=2.0f
        f2j.ssbmv("U", M, KU, 1.0f, ssbAU, lda, sX, 1, 2.0f, expected = sY.clone(), 1);
        blas.ssbmv("U", M, KU, 1.0f, ssbAU, lda, sX, 1, 2.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        // uplo=L, alpha=1.0f, beta=2.0f
        f2j.ssbmv("L", M, KU, 1.0f, ssbAL, lda, sX, 1, 2.0f, expected = sY.clone(), 1);
        blas.ssbmv("L", M, KU, 1.0f, ssbAL, lda, sX, 1, 2.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        // uplo=U, alpha=1.0f, beta=0.0f
        f2j.ssbmv("U", M, KU, 1.0f, ssbAU, lda, sX, 1, 0.0f, expected = sY.clone(), 1);
        blas.ssbmv("U", M, KU, 1.0f, ssbAU, lda, sX, 1, 0.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        // uplo=L, alpha=1.0f, beta=0.0f
        f2j.ssbmv("L", M, KU, 1.0f, ssbAL, lda, sX, 1, 0.0f, expected = sY.clone(), 1);
        blas.ssbmv("L", M, KU, 1.0f, ssbAL, lda, sX, 1, 0.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        // uplo=U, alpha=0.0f, beta=1.0f
        f2j.ssbmv("U", M, KU, 0.0f, ssbAU, lda, sX, 1, 1.0f, expected = sY.clone(), 1);
        blas.ssbmv("U", M, KU, 0.0f, ssbAU, lda, sX, 1, 1.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        // uplo=L, alpha=0.0f, beta=1.0f
        f2j.ssbmv("L", M, KU, 0.0f, ssbAL, lda, sX, 1, 1.0f, expected = sY.clone(), 1);
        blas.ssbmv("L", M, KU, 0.0f, ssbAL, lda, sX, 1, 1.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaZeroBetaTwo(BLAS blas) {
        float[] expected, sYcopy;
        int lda = KU + 1;

        // alpha=0.0f, beta=2.0f: should just scale y by 2.0
        f2j.ssbmv("U", M, KU, 0.0f, ssbAU, lda, sX, 1, 2.0f, expected = sY.clone(), 1);
        blas.ssbmv("U", M, KU, 0.0f, ssbAU, lda, sX, 1, 2.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.ssbmv("L", M, KU, 0.0f, ssbAL, lda, sX, 1, 2.0f, expected = sY.clone(), 1);
        blas.ssbmv("L", M, KU, 0.0f, ssbAL, lda, sX, 1, 2.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaTwoBetaZero(BLAS blas) {
        float[] expected, sYcopy;
        int lda = KU + 1;

        // alpha=2.0f, beta=0.0f: y = 2.0 * A * x
        f2j.ssbmv("U", M, KU, 2.0f, ssbAU, lda, sX, 1, 0.0f, expected = sY.clone(), 1);
        blas.ssbmv("U", M, KU, 2.0f, ssbAU, lda, sX, 1, 0.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.ssbmv("L", M, KU, 2.0f, ssbAL, lda, sX, 1, 0.0f, expected = sY.clone(), 1);
        blas.ssbmv("L", M, KU, 2.0f, ssbAL, lda, sX, 1, 0.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNonUnitStride(BLAS blas) {
        float[] expected, sYcopy;
        int lda = KU + 1;
        int smallN = M / 2;

        f2j.ssbmv("U", smallN, KU, 1.0f, ssbAU, lda, sX, 2, 1.0f, expected = sY.clone(), 2);
        blas.ssbmv("U", smallN, KU, 1.0f, ssbAU, lda, sX, 2, 1.0f, sYcopy = sY.clone(), 2);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.ssbmv("L", smallN, KU, 1.0f, ssbAL, lda, sX, 2, 1.0f, expected = sY.clone(), 2);
        blas.ssbmv("L", smallN, KU, 1.0f, ssbAL, lda, sX, 2, 1.0f, sYcopy = sY.clone(), 2);
        assertArrayEquals(expected, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNegativeStride(BLAS blas) {
        float[] expected, sYcopy;

        f2j.ssbmv("U", M, KU, 2.0f, ssbAU, KU + 1, sX, -1, 2.0f, expected = sY.clone(), -1);
        blas.ssbmv("U", M, KU, 2.0f, ssbAU, KU + 1, sX, -1, 2.0f, sYcopy = sY.clone(), -1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.ssbmv("L", M, KU, 2.0f, ssbAL, KU + 1, sX, -1, 2.0f, expected = sY.clone(), -1);
        blas.ssbmv("L", M, KU, 2.0f, ssbAL, KU + 1, sX, -1, 2.0f, sYcopy = sY.clone(), -1);
        assertArrayEquals(expected, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testInvalidUplo(BLAS blas) {
        int lda = KU + 1;
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.ssbmv("X", M, KU, 1.0f, ssbAU, lda, sX, 1, 2.0f, sY.clone(), 1);
        });
        // negative n
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.ssbmv("U", -1, KU, 1.0f, ssbAU, KU + 1, sX, 1, 2.0f, sY.clone(), 1);
        });
        // negative k
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.ssbmv("U", M, -1, 1.0f, ssbAU, KU + 1, sX, 1, 2.0f, sY.clone(), 1);
        });
        // lda too small
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.ssbmv("U", M, KU, 1.0f, ssbAU, KU, sX, 1, 2.0f, sY.clone(), 1);
        });
        // incx == 0
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.ssbmv("U", M, KU, 1.0f, ssbAU, KU + 1, sX, 0, 2.0f, sY.clone(), 1);
        });
        // incy == 0
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.ssbmv("U", M, KU, 1.0f, ssbAU, KU + 1, sX, 1, 2.0f, sY.clone(), 0);
        });
    }
}
