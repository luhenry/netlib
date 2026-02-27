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

public class DtrmmTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        double[] expected, dgeBcopy;

        // side=L, uplo=U, transa=N, diag=N
        f2j.dtrmm("L", "U", "N", "N", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("L", "U", "N", "N", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);

        // side=L, uplo=L, transa=N, diag=N
        f2j.dtrmm("L", "L", "N", "N", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("L", "L", "N", "N", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);

        // side=R, uplo=U, transa=N, diag=N
        f2j.dtrmm("R", "U", "N", "N", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("R", "U", "N", "N", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);

        // side=L, uplo=U, transa=T, diag=N
        f2j.dtrmm("L", "U", "T", "N", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("L", "U", "T", "N", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAllSideUploTransCombinations(BLAS blas) {
        double[] expected, dgeBcopy;

        // side=R, uplo=L, transa=N, diag=N
        f2j.dtrmm("R", "L", "N", "N", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("R", "L", "N", "N", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);

        // side=L, uplo=L, transa=T, diag=N
        f2j.dtrmm("L", "L", "T", "N", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("L", "L", "T", "N", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);

        // side=R, uplo=U, transa=T, diag=N
        f2j.dtrmm("R", "U", "T", "N", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("R", "U", "T", "N", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);

        // side=R, uplo=L, transa=T, diag=N
        f2j.dtrmm("R", "L", "T", "N", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("R", "L", "T", "N", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testUnitDiagonal(BLAS blas) {
        double[] expected, dgeBcopy;

        // side=L, uplo=U, transa=N, diag=U
        f2j.dtrmm("L", "U", "N", "U", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("L", "U", "N", "U", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);

        // side=L, uplo=L, transa=N, diag=U
        f2j.dtrmm("L", "L", "N", "U", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("L", "L", "N", "U", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);

        // side=R, uplo=U, transa=N, diag=U
        f2j.dtrmm("R", "U", "N", "U", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("R", "U", "N", "U", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);

        // side=R, uplo=L, transa=N, diag=U
        f2j.dtrmm("R", "L", "N", "U", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("R", "L", "N", "U", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);

        // side=L, uplo=U, transa=T, diag=U
        f2j.dtrmm("L", "U", "T", "U", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("L", "U", "T", "U", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);

        // side=L, uplo=L, transa=T, diag=U
        f2j.dtrmm("L", "L", "T", "U", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("L", "L", "T", "U", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);

        // side=R, uplo=U, transa=T, diag=U
        f2j.dtrmm("R", "U", "T", "U", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("R", "U", "T", "U", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);

        // side=R, uplo=L, transa=T, diag=U
        f2j.dtrmm("R", "L", "T", "U", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("R", "L", "T", "U", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaZero(BLAS blas) {
        double[] expected, dgeBcopy;

        // alpha=0 should zero B: side=L, uplo=U, transa=N, diag=N
        f2j.dtrmm("L", "U", "N", "N", M, N, 0.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("L", "U", "N", "N", M, N, 0.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);

        // alpha=0: side=R, uplo=L, transa=T, diag=N
        f2j.dtrmm("R", "L", "T", "N", M, N, 0.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("R", "L", "T", "N", M, N, 0.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);

        // alpha=0: side=L, uplo=L, transa=N, diag=U
        f2j.dtrmm("L", "L", "N", "U", M, N, 0.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("L", "L", "N", "U", M, N, 0.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaScaling(BLAS blas) {
        double[] expected, dgeBcopy;

        // alpha=2.0: side=L, uplo=U, transa=N, diag=N
        f2j.dtrmm("L", "U", "N", "N", M, N, 2.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("L", "U", "N", "N", M, N, 2.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);

        // alpha=2.0: side=L, uplo=L, transa=T, diag=N
        f2j.dtrmm("L", "L", "T", "N", M, N, 2.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("L", "L", "T", "N", M, N, 2.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);

        // alpha=2.0: side=R, uplo=U, transa=T, diag=N
        f2j.dtrmm("R", "U", "T", "N", M, N, 2.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("R", "U", "T", "N", M, N, 2.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);

        // alpha=2.0: side=R, uplo=L, transa=N, diag=U
        f2j.dtrmm("R", "L", "N", "U", M, N, 2.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("R", "L", "N", "U", M, N, 2.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);

        // alpha=-1.0: side=L, uplo=U, transa=N, diag=N
        f2j.dtrmm("L", "U", "N", "N", M, N, -1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrmm("L", "U", "N", "N", M, N, -1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertArrayEquals(expected, dgeBcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testInvalidArguments(BLAS blas) {
        // invalid side
        assertThrows(IllegalArgumentException.class, () -> {
            blas.dtrmm("X", "U", "N", "N", M, N, 1.0, dsyA, M, dgeB.clone(), M);
        });
        // invalid uplo
        assertThrows(IllegalArgumentException.class, () -> {
            blas.dtrmm("L", "X", "N", "N", M, N, 1.0, dsyA, M, dgeB.clone(), M);
        });
        // invalid transa
        assertThrows(IllegalArgumentException.class, () -> {
            blas.dtrmm("L", "U", "X", "N", M, N, 1.0, dsyA, M, dgeB.clone(), M);
        });
        // invalid diag
        assertThrows(IllegalArgumentException.class, () -> {
            blas.dtrmm("L", "U", "N", "X", M, N, 1.0, dsyA, M, dgeB.clone(), M);
        });
        // negative m
        assertThrows(IllegalArgumentException.class, () -> {
            blas.dtrmm("L", "U", "N", "N", -1, N, 1.0, dsyA, M, dgeB.clone(), M);
        });
        // negative n
        assertThrows(IllegalArgumentException.class, () -> {
            blas.dtrmm("L", "U", "N", "N", M, -1, 1.0, dsyA, M, dgeB.clone(), M);
        });
        // lda too small
        assertThrows(IllegalArgumentException.class, () -> {
            blas.dtrmm("L", "U", "N", "N", M, N, 1.0, dsyA, M - 1, dgeB.clone(), M);
        });
        // ldb too small
        assertThrows(IllegalArgumentException.class, () -> {
            blas.dtrmm("L", "U", "N", "N", M, N, 1.0, dsyA, M, dgeB.clone(), M - 1);
        });
    }
}
