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

public class DsbmvTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        double[] expected, dYcopy;
        int lda = KU + 1;

        // uplo=U, alpha=1.0, beta=2.0
        f2j.dsbmv("U", M, KU, 1.0, dsbAU, lda, dX, 1, 2.0, expected = dY.clone(), 1);
        blas.dsbmv("U", M, KU, 1.0, dsbAU, lda, dX, 1, 2.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        // uplo=L, alpha=1.0, beta=2.0
        f2j.dsbmv("L", M, KU, 1.0, dsbAL, lda, dX, 1, 2.0, expected = dY.clone(), 1);
        blas.dsbmv("L", M, KU, 1.0, dsbAL, lda, dX, 1, 2.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        // uplo=U, alpha=1.0, beta=0.0
        f2j.dsbmv("U", M, KU, 1.0, dsbAU, lda, dX, 1, 0.0, expected = dY.clone(), 1);
        blas.dsbmv("U", M, KU, 1.0, dsbAU, lda, dX, 1, 0.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        // uplo=L, alpha=1.0, beta=0.0
        f2j.dsbmv("L", M, KU, 1.0, dsbAL, lda, dX, 1, 0.0, expected = dY.clone(), 1);
        blas.dsbmv("L", M, KU, 1.0, dsbAL, lda, dX, 1, 0.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        // uplo=U, alpha=0.0, beta=1.0
        f2j.dsbmv("U", M, KU, 0.0, dsbAU, lda, dX, 1, 1.0, expected = dY.clone(), 1);
        blas.dsbmv("U", M, KU, 0.0, dsbAU, lda, dX, 1, 1.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        // uplo=L, alpha=0.0, beta=1.0
        f2j.dsbmv("L", M, KU, 0.0, dsbAL, lda, dX, 1, 1.0, expected = dY.clone(), 1);
        blas.dsbmv("L", M, KU, 0.0, dsbAL, lda, dX, 1, 1.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaZeroBetaTwo(BLAS blas) {
        double[] expected, dYcopy;
        int lda = KU + 1;

        // alpha=0.0, beta=2.0: should just scale y by 2.0
        f2j.dsbmv("U", M, KU, 0.0, dsbAU, lda, dX, 1, 2.0, expected = dY.clone(), 1);
        blas.dsbmv("U", M, KU, 0.0, dsbAU, lda, dX, 1, 2.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dsbmv("L", M, KU, 0.0, dsbAL, lda, dX, 1, 2.0, expected = dY.clone(), 1);
        blas.dsbmv("L", M, KU, 0.0, dsbAL, lda, dX, 1, 2.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaTwoBetaZero(BLAS blas) {
        double[] expected, dYcopy;
        int lda = KU + 1;

        // alpha=2.0, beta=0.0: y = 2.0 * A * x
        f2j.dsbmv("U", M, KU, 2.0, dsbAU, lda, dX, 1, 0.0, expected = dY.clone(), 1);
        blas.dsbmv("U", M, KU, 2.0, dsbAU, lda, dX, 1, 0.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dsbmv("L", M, KU, 2.0, dsbAL, lda, dX, 1, 0.0, expected = dY.clone(), 1);
        blas.dsbmv("L", M, KU, 2.0, dsbAL, lda, dX, 1, 0.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNonUnitStride(BLAS blas) {
        double[] expected, dYcopy;
        int lda = KU + 1;
        int smallN = M / 2;

        f2j.dsbmv("U", smallN, KU, 1.0, dsbAU, lda, dX, 2, 1.0, expected = dY.clone(), 2);
        blas.dsbmv("U", smallN, KU, 1.0, dsbAU, lda, dX, 2, 1.0, dYcopy = dY.clone(), 2);
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dsbmv("L", smallN, KU, 1.0, dsbAL, lda, dX, 2, 1.0, expected = dY.clone(), 2);
        blas.dsbmv("L", smallN, KU, 1.0, dsbAL, lda, dX, 2, 1.0, dYcopy = dY.clone(), 2);
        assertArrayEquals(expected, dYcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNegativeStride(BLAS blas) {
        double[] expected, dYcopy;

        f2j.dsbmv("U", M, KU, 2.0, dsbAU, KU + 1, dX, -1, 2.0, expected = dY.clone(), -1);
        blas.dsbmv("U", M, KU, 2.0, dsbAU, KU + 1, dX, -1, 2.0, dYcopy = dY.clone(), -1);
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dsbmv("L", M, KU, 2.0, dsbAL, KU + 1, dX, -1, 2.0, expected = dY.clone(), -1);
        blas.dsbmv("L", M, KU, 2.0, dsbAL, KU + 1, dX, -1, 2.0, dYcopy = dY.clone(), -1);
        assertArrayEquals(expected, dYcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testInvalidUplo(BLAS blas) {
        int lda = KU + 1;
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.dsbmv("X", M, KU, 1.0, dsbAU, lda, dX, 1, 2.0, dY.clone(), 1);
        });
        // negative n
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.dsbmv("U", -1, KU, 1.0, dsbAU, KU + 1, dX, 1, 2.0, dY.clone(), 1);
        });
        // negative k
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.dsbmv("U", M, -1, 1.0, dsbAU, KU + 1, dX, 1, 2.0, dY.clone(), 1);
        });
        // lda too small
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.dsbmv("U", M, KU, 1.0, dsbAU, KU, dX, 1, 2.0, dY.clone(), 1);
        });
        // incx == 0
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.dsbmv("U", M, KU, 1.0, dsbAU, KU + 1, dX, 0, 2.0, dY.clone(), 1);
        });
        // incy == 0
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.dsbmv("U", M, KU, 1.0, dsbAU, KU + 1, dX, 1, 2.0, dY.clone(), 0);
        });
    }
}
