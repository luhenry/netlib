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

public class DgerTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        double[] expected, dgeAcopy;

        f2j.dger(M, N, 2.0, dX, 1, dY, 1, expected = dgeA.clone(), M);
        blas.dger(M, N, 2.0, dX, 1, dY, 1, dgeAcopy = dgeA.clone(), M);
        assertArrayEquals(expected, dgeAcopy, depsilon);

        f2j.dger(M, N, 0.0, dX, 1, dY, 1, expected = dgeA.clone(), M);
        blas.dger(M, N, 0.0, dX, 1, dY, 1, dgeAcopy = dgeA.clone(), M);
        assertArrayEquals(expected, dgeAcopy, depsilon);

        f2j.dger(M, N, -1.0, dX, 1, dY, 1, expected = dgeA.clone(), M);
        blas.dger(M, N, -1.0, dX, 1, dY, 1, dgeAcopy = dgeA.clone(), M);
        assertArrayEquals(expected, dgeAcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNonUnitStride(BLAS blas) {
        double[] expected, dgeAcopy;
        int smallM = M / 2;
        int smallN = N / 2;

        f2j.dger(smallM, smallN, 2.0, dX, 2, dY, 2, expected = dgeA.clone(), M);
        blas.dger(smallM, smallN, 2.0, dX, 2, dY, 2, dgeAcopy = dgeA.clone(), M);
        assertArrayEquals(expected, dgeAcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOffset(BLAS blas) {
        double[] expected, dgeAcopy;
        int smallM = M / 2;
        int smallN = N / 2;

        f2j.dger(smallM, smallN, 2.0, dX, 1, 1, dY, 1, 1, expected = dgeA.clone(), 0, M);
        blas.dger(smallM, smallN, 2.0, dX, 1, 1, dY, 1, 1, dgeAcopy = dgeA.clone(), 0, M);
        assertArrayEquals(expected, dgeAcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNegativeStride(BLAS blas) {
        double[] expected, dgeAcopy;

        f2j.dger(M, N, 2.0, dX, -1, dY, -1, expected = dgeA.clone(), M);
        blas.dger(M, N, 2.0, dX, -1, dY, -1, dgeAcopy = dgeA.clone(), M);
        assertArrayEquals(expected, dgeAcopy, depsilon);

        f2j.dger(M, N, 2.0, dX, -1, dY, 1, expected = dgeA.clone(), M);
        blas.dger(M, N, 2.0, dX, -1, dY, 1, dgeAcopy = dgeA.clone(), M);
        assertArrayEquals(expected, dgeAcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testInvalidArgument(BLAS blas) {
        // negative m
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.dger(-1, N, 2.0, dX, 1, dY, 1, dgeA.clone(), M);
        });
        // negative n
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.dger(M, -1, 2.0, dX, 1, dY, 1, dgeA.clone(), M);
        });
        // incx == 0
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.dger(M, N, 2.0, dX, 0, dY, 1, dgeA.clone(), M);
        });
        // incy == 0
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.dger(M, N, 2.0, dX, 1, dY, 0, dgeA.clone(), M);
        });
        // lda too small
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.dger(M, N, 2.0, dX, 1, dY, 1, dgeA.clone(), M - 1);
        });
    }
}
