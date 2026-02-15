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

public class StrsmTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        float[] expected, sgeBcopy;

        // side=L, uplo=U, transa=N, diag=N
        f2j.strsm("L", "U", "N", "N", M, N, 1.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("L", "U", "N", "N", M, N, 1.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);

        // side=L, uplo=L, transa=N, diag=N
        f2j.strsm("L", "L", "N", "N", M, N, 1.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("L", "L", "N", "N", M, N, 1.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);

        // side=R, uplo=U, transa=N, diag=N
        f2j.strsm("R", "U", "N", "N", M, N, 1.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("R", "U", "N", "N", M, N, 1.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);

        // side=L, uplo=U, transa=T, diag=N
        f2j.strsm("L", "U", "T", "N", M, N, 1.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("L", "U", "T", "N", M, N, 1.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAllSideUploTransCombinations(BLAS blas) {
        float[] expected, sgeBcopy;

        // side=R, uplo=L, transa=N, diag=N
        f2j.strsm("R", "L", "N", "N", M, N, 1.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("R", "L", "N", "N", M, N, 1.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);

        // side=L, uplo=L, transa=T, diag=N
        f2j.strsm("L", "L", "T", "N", M, N, 1.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("L", "L", "T", "N", M, N, 1.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);

        // side=R, uplo=U, transa=T, diag=N
        f2j.strsm("R", "U", "T", "N", M, N, 1.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("R", "U", "T", "N", M, N, 1.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);

        // side=R, uplo=L, transa=T, diag=N
        f2j.strsm("R", "L", "T", "N", M, N, 1.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("R", "L", "T", "N", M, N, 1.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testUnitDiagonal(BLAS blas) {
        float[] expected, sgeBcopy;

        // side=L, uplo=U, transa=N, diag=U
        f2j.strsm("L", "U", "N", "U", M, N, 1.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("L", "U", "N", "U", M, N, 1.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);

        // side=L, uplo=L, transa=N, diag=U
        f2j.strsm("L", "L", "N", "U", M, N, 1.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("L", "L", "N", "U", M, N, 1.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);

        // side=R, uplo=U, transa=N, diag=U
        f2j.strsm("R", "U", "N", "U", M, N, 1.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("R", "U", "N", "U", M, N, 1.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);

        // side=R, uplo=L, transa=N, diag=U
        f2j.strsm("R", "L", "N", "U", M, N, 1.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("R", "L", "N", "U", M, N, 1.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);

        // side=L, uplo=U, transa=T, diag=U
        f2j.strsm("L", "U", "T", "U", M, N, 1.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("L", "U", "T", "U", M, N, 1.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);

        // side=L, uplo=L, transa=T, diag=U
        f2j.strsm("L", "L", "T", "U", M, N, 1.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("L", "L", "T", "U", M, N, 1.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);

        // side=R, uplo=U, transa=T, diag=U
        f2j.strsm("R", "U", "T", "U", M, N, 1.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("R", "U", "T", "U", M, N, 1.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);

        // side=R, uplo=L, transa=T, diag=U
        f2j.strsm("R", "L", "T", "U", M, N, 1.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("R", "L", "T", "U", M, N, 1.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaScaling(BLAS blas) {
        float[] expected, sgeBcopy;

        // alpha=2.0: side=L, uplo=U, transa=N, diag=N
        f2j.strsm("L", "U", "N", "N", M, N, 2.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("L", "U", "N", "N", M, N, 2.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);

        // alpha=2.0: side=R, uplo=L, transa=T, diag=N
        f2j.strsm("R", "L", "T", "N", M, N, 2.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("R", "L", "T", "N", M, N, 2.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);

        // alpha=2.0: side=L, uplo=L, transa=N, diag=U
        f2j.strsm("L", "L", "N", "U", M, N, 2.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("L", "L", "N", "U", M, N, 2.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);

        // alpha=2.0: side=R, uplo=U, transa=T, diag=U
        f2j.strsm("R", "U", "T", "U", M, N, 2.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("R", "U", "T", "U", M, N, 2.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);

        // alpha=-1.0: side=L, uplo=U, transa=N, diag=N
        f2j.strsm("L", "U", "N", "N", M, N, -1.0f, ssyA, M, expected = sgeB.clone(), M);
        blas.strsm("L", "U", "N", "N", M, N, -1.0f, ssyA, M, sgeBcopy = sgeB.clone(), M);
        assertRelArrayEquals(expected, sgeBcopy, ssolveEpsilon);
    }
}
