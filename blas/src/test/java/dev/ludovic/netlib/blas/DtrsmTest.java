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

public class DtrsmTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        double[] expected, dgeBcopy;

        // side=L, uplo=U, transa=N, diag=N
        f2j.dtrsm("L", "U", "N", "N", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("L", "U", "N", "N", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);

        // side=L, uplo=L, transa=N, diag=N
        f2j.dtrsm("L", "L", "N", "N", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("L", "L", "N", "N", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);

        // side=R, uplo=U, transa=N, diag=N
        f2j.dtrsm("R", "U", "N", "N", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("R", "U", "N", "N", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);

        // side=L, uplo=U, transa=T, diag=N
        f2j.dtrsm("L", "U", "T", "N", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("L", "U", "T", "N", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAllSideUploTransCombinations(BLAS blas) {
        double[] expected, dgeBcopy;

        // side=R, uplo=L, transa=N, diag=N
        f2j.dtrsm("R", "L", "N", "N", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("R", "L", "N", "N", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);

        // side=L, uplo=L, transa=T, diag=N
        f2j.dtrsm("L", "L", "T", "N", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("L", "L", "T", "N", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);

        // side=R, uplo=U, transa=T, diag=N
        f2j.dtrsm("R", "U", "T", "N", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("R", "U", "T", "N", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);

        // side=R, uplo=L, transa=T, diag=N
        f2j.dtrsm("R", "L", "T", "N", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("R", "L", "T", "N", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testUnitDiagonal(BLAS blas) {
        double[] expected, dgeBcopy;

        // side=L, uplo=U, transa=N, diag=U
        f2j.dtrsm("L", "U", "N", "U", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("L", "U", "N", "U", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);

        // side=L, uplo=L, transa=N, diag=U
        f2j.dtrsm("L", "L", "N", "U", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("L", "L", "N", "U", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);

        // side=R, uplo=U, transa=N, diag=U
        f2j.dtrsm("R", "U", "N", "U", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("R", "U", "N", "U", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);

        // side=R, uplo=L, transa=N, diag=U
        f2j.dtrsm("R", "L", "N", "U", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("R", "L", "N", "U", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);

        // side=L, uplo=U, transa=T, diag=U
        f2j.dtrsm("L", "U", "T", "U", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("L", "U", "T", "U", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);

        // side=L, uplo=L, transa=T, diag=U
        f2j.dtrsm("L", "L", "T", "U", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("L", "L", "T", "U", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);

        // side=R, uplo=U, transa=T, diag=U
        f2j.dtrsm("R", "U", "T", "U", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("R", "U", "T", "U", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);

        // side=R, uplo=L, transa=T, diag=U
        f2j.dtrsm("R", "L", "T", "U", M, N, 1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("R", "L", "T", "U", M, N, 1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaScaling(BLAS blas) {
        double[] expected, dgeBcopy;

        // alpha=2.0: side=L, uplo=U, transa=N, diag=N
        f2j.dtrsm("L", "U", "N", "N", M, N, 2.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("L", "U", "N", "N", M, N, 2.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);

        // alpha=2.0: side=R, uplo=L, transa=T, diag=N
        f2j.dtrsm("R", "L", "T", "N", M, N, 2.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("R", "L", "T", "N", M, N, 2.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);

        // alpha=2.0: side=L, uplo=L, transa=N, diag=U
        f2j.dtrsm("L", "L", "N", "U", M, N, 2.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("L", "L", "N", "U", M, N, 2.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);

        // alpha=2.0: side=R, uplo=U, transa=T, diag=U
        f2j.dtrsm("R", "U", "T", "U", M, N, 2.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("R", "U", "T", "U", M, N, 2.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);

        // alpha=-1.0: side=L, uplo=U, transa=N, diag=N
        f2j.dtrsm("L", "U", "N", "N", M, N, -1.0, dsyA, M, expected = dgeB.clone(), M);
        blas.dtrsm("L", "U", "N", "N", M, N, -1.0, dsyA, M, dgeBcopy = dgeB.clone(), M);
        assertRelArrayEquals(expected, dgeBcopy, dsolveEpsilon);
    }
}
