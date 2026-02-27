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

public class DgbmvTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        double[] expected, dYcopy;
        int lda = KL + KU + 1;

        // trans=N, alpha=1.0, beta=2.0
        f2j.dgbmv("N", M, N, KL, KU, 1.0, dgbA, lda, dX, 1, 2.0, expected = dY.clone(), 1);
        blas.dgbmv("N", M, N, KL, KU, 1.0, dgbA, lda, dX, 1, 2.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        // trans=T, alpha=1.0, beta=2.0
        f2j.dgbmv("T", M, N, KL, KU, 1.0, dgbA, lda, dX, 1, 2.0, expected = dY.clone(), 1);
        blas.dgbmv("T", M, N, KL, KU, 1.0, dgbA, lda, dX, 1, 2.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        // trans=N, alpha=1.0, beta=0.0
        f2j.dgbmv("N", M, N, KL, KU, 1.0, dgbA, lda, dX, 1, 0.0, expected = dY.clone(), 1);
        blas.dgbmv("N", M, N, KL, KU, 1.0, dgbA, lda, dX, 1, 0.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        // trans=T, alpha=1.0, beta=0.0
        f2j.dgbmv("T", M, N, KL, KU, 1.0, dgbA, lda, dX, 1, 0.0, expected = dY.clone(), 1);
        blas.dgbmv("T", M, N, KL, KU, 1.0, dgbA, lda, dX, 1, 0.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        // trans=N, alpha=0.0, beta=1.0
        f2j.dgbmv("N", M, N, KL, KU, 0.0, dgbA, lda, dX, 1, 1.0, expected = dY.clone(), 1);
        blas.dgbmv("N", M, N, KL, KU, 0.0, dgbA, lda, dX, 1, 1.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        // trans=T, alpha=0.0, beta=1.0
        f2j.dgbmv("T", M, N, KL, KU, 0.0, dgbA, lda, dX, 1, 1.0, expected = dY.clone(), 1);
        blas.dgbmv("T", M, N, KL, KU, 0.0, dgbA, lda, dX, 1, 1.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaTwo(BLAS blas) {
        double[] expected, dYcopy;
        int lda = KL + KU + 1;

        // trans=N, alpha=2.0, beta=1.0
        f2j.dgbmv("N", M, N, KL, KU, 2.0, dgbA, lda, dX, 1, 1.0, expected = dY.clone(), 1);
        blas.dgbmv("N", M, N, KL, KU, 2.0, dgbA, lda, dX, 1, 1.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        // trans=T, alpha=2.0, beta=1.0
        f2j.dgbmv("T", M, N, KL, KU, 2.0, dgbA, lda, dX, 1, 1.0, expected = dY.clone(), 1);
        blas.dgbmv("T", M, N, KL, KU, 2.0, dgbA, lda, dX, 1, 1.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        // trans=N, alpha=2.0, beta=0.0
        f2j.dgbmv("N", M, N, KL, KU, 2.0, dgbA, lda, dX, 1, 0.0, expected = dY.clone(), 1);
        blas.dgbmv("N", M, N, KL, KU, 2.0, dgbA, lda, dX, 1, 0.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        // trans=T, alpha=2.0, beta=0.0
        f2j.dgbmv("T", M, N, KL, KU, 2.0, dgbA, lda, dX, 1, 0.0, expected = dY.clone(), 1);
        blas.dgbmv("T", M, N, KL, KU, 2.0, dgbA, lda, dX, 1, 0.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNonUnitStride(BLAS blas) {
        double[] expected, dYcopy;
        int lda = KL + KU + 1;
        // With incx=2 and incy=2, strided access needs (dim-1)*2 + 1 entries.
        // dX and dY have M=103 elements, so max dim = (M-1)/2 + 1 = 52.
        int smallDim = (M - 1) / 2 + 1;

        // trans=N: x has smallDim elements (stride 2), y has smallDim elements (stride 2)
        f2j.dgbmv("N", smallDim, smallDim, KL, KU, 1.0, dgbA, lda, dX, 2, 1.0, expected = dY.clone(), 2);
        blas.dgbmv("N", smallDim, smallDim, KL, KU, 1.0, dgbA, lda, dX, 2, 1.0, dYcopy = dY.clone(), 2);
        assertArrayEquals(expected, dYcopy, depsilon);

        // trans=T: x has smallDim elements (stride 2), y has smallDim elements (stride 2)
        f2j.dgbmv("T", smallDim, smallDim, KL, KU, 1.0, dgbA, lda, dX, 2, 1.0, expected = dY.clone(), 2);
        blas.dgbmv("T", smallDim, smallDim, KL, KU, 1.0, dgbA, lda, dX, 2, 1.0, dYcopy = dY.clone(), 2);
        assertArrayEquals(expected, dYcopy, depsilon);
    }
}
