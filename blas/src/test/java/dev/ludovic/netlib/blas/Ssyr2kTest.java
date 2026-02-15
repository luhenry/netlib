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

public class Ssyr2kTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        float[] expected, ssyAcopy;

        // uplo=U, trans=N: C := alpha*A*B^T + alpha*B*A^T + beta*C
        // A is n x k (M x K), lda=M; B is n x k (M x K), ldb=M
        f2j.ssyr2k("U", "N", M, K, 1.0f, sgeA, M, sgeB, M, 2.0f, expected = ssyA.clone(), M);
        blas.ssyr2k("U", "N", M, K, 1.0f, sgeA, M, sgeB, M, 2.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, 2 * sepsilon);

        // uplo=L, trans=N
        f2j.ssyr2k("L", "N", M, K, 1.0f, sgeA, M, sgeB, M, 2.0f, expected = ssyA.clone(), M);
        blas.ssyr2k("L", "N", M, K, 1.0f, sgeA, M, sgeB, M, 2.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, 2 * sepsilon);

        // uplo=U, trans=T: C := alpha*A^T*B + alpha*B^T*A + beta*C
        // A is k x n (K x M), lda=K; B is k x n (K x M), ldb=K
        f2j.ssyr2k("U", "T", M, K, 1.0f, sgeAT, K, sgeBT, K, 2.0f, expected = ssyA.clone(), M);
        blas.ssyr2k("U", "T", M, K, 1.0f, sgeAT, K, sgeBT, K, 2.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, 2 * sepsilon);

        // uplo=L, trans=T
        f2j.ssyr2k("L", "T", M, K, 1.0f, sgeAT, K, sgeBT, K, 2.0f, expected = ssyA.clone(), M);
        blas.ssyr2k("L", "T", M, K, 1.0f, sgeAT, K, sgeBT, K, 2.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, 2 * sepsilon);

        // beta=0.0 cases
        f2j.ssyr2k("U", "N", M, K, 1.0f, sgeA, M, sgeB, M, 0.0f, expected = ssyA.clone(), M);
        blas.ssyr2k("U", "N", M, K, 1.0f, sgeA, M, sgeB, M, 0.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, 2 * sepsilon);

        f2j.ssyr2k("L", "N", M, K, 1.0f, sgeA, M, sgeB, M, 0.0f, expected = ssyA.clone(), M);
        blas.ssyr2k("L", "N", M, K, 1.0f, sgeA, M, sgeB, M, 0.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, 2 * sepsilon);

        f2j.ssyr2k("U", "T", M, K, 1.0f, sgeAT, K, sgeBT, K, 0.0f, expected = ssyA.clone(), M);
        blas.ssyr2k("U", "T", M, K, 1.0f, sgeAT, K, sgeBT, K, 0.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, 2 * sepsilon);

        f2j.ssyr2k("L", "T", M, K, 1.0f, sgeAT, K, sgeBT, K, 0.0f, expected = ssyA.clone(), M);
        blas.ssyr2k("L", "T", M, K, 1.0f, sgeAT, K, sgeBT, K, 0.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, 2 * sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaZeroBetaScale(BLAS blas) {
        float[] expected, ssyAcopy;

        // alpha=0.0f, beta=2.0f: should just scale C by 2.0
        f2j.ssyr2k("U", "N", M, K, 0.0f, sgeA, M, sgeB, M, 2.0f, expected = ssyA.clone(), M);
        blas.ssyr2k("U", "N", M, K, 0.0f, sgeA, M, sgeB, M, 2.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, 2 * sepsilon);

        f2j.ssyr2k("L", "N", M, K, 0.0f, sgeA, M, sgeB, M, 2.0f, expected = ssyA.clone(), M);
        blas.ssyr2k("L", "N", M, K, 0.0f, sgeA, M, sgeB, M, 2.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, 2 * sepsilon);

        f2j.ssyr2k("U", "T", M, K, 0.0f, sgeAT, K, sgeBT, K, 2.0f, expected = ssyA.clone(), M);
        blas.ssyr2k("U", "T", M, K, 0.0f, sgeAT, K, sgeBT, K, 2.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, 2 * sepsilon);

        f2j.ssyr2k("L", "T", M, K, 0.0f, sgeAT, K, sgeBT, K, 2.0f, expected = ssyA.clone(), M);
        blas.ssyr2k("L", "T", M, K, 0.0f, sgeAT, K, sgeBT, K, 2.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, 2 * sepsilon);
    }
}
