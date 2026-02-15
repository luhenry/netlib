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

public class SsyrkTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        float[] expected, ssyAcopy;

        // uplo=U, trans=N: C := alpha*A*A^T + beta*C, A is n x k (M x K), lda=M
        f2j.ssyrk("U", "N", M, K, 1.0f, sgeA, M, 2.0f, expected = ssyA.clone(), M);
        blas.ssyrk("U", "N", M, K, 1.0f, sgeA, M, 2.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, sepsilon);

        // uplo=L, trans=N
        f2j.ssyrk("L", "N", M, K, 1.0f, sgeA, M, 2.0f, expected = ssyA.clone(), M);
        blas.ssyrk("L", "N", M, K, 1.0f, sgeA, M, 2.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, sepsilon);

        // uplo=U, trans=T: C := alpha*A^T*A + beta*C, A is k x n (K x M), lda=K
        f2j.ssyrk("U", "T", M, K, 1.0f, sgeAT, K, 2.0f, expected = ssyA.clone(), M);
        blas.ssyrk("U", "T", M, K, 1.0f, sgeAT, K, 2.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, sepsilon);

        // uplo=L, trans=T
        f2j.ssyrk("L", "T", M, K, 1.0f, sgeAT, K, 2.0f, expected = ssyA.clone(), M);
        blas.ssyrk("L", "T", M, K, 1.0f, sgeAT, K, 2.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, sepsilon);

        // beta=0.0 cases
        f2j.ssyrk("U", "N", M, K, 1.0f, sgeA, M, 0.0f, expected = ssyA.clone(), M);
        blas.ssyrk("U", "N", M, K, 1.0f, sgeA, M, 0.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, sepsilon);

        f2j.ssyrk("L", "N", M, K, 1.0f, sgeA, M, 0.0f, expected = ssyA.clone(), M);
        blas.ssyrk("L", "N", M, K, 1.0f, sgeA, M, 0.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, sepsilon);

        f2j.ssyrk("U", "T", M, K, 1.0f, sgeAT, K, 0.0f, expected = ssyA.clone(), M);
        blas.ssyrk("U", "T", M, K, 1.0f, sgeAT, K, 0.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, sepsilon);

        f2j.ssyrk("L", "T", M, K, 1.0f, sgeAT, K, 0.0f, expected = ssyA.clone(), M);
        blas.ssyrk("L", "T", M, K, 1.0f, sgeAT, K, 0.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaZeroBetaScale(BLAS blas) {
        float[] expected, ssyAcopy;

        // alpha=0.0f, beta=2.0f: should just scale C by 2.0
        f2j.ssyrk("U", "N", M, K, 0.0f, sgeA, M, 2.0f, expected = ssyA.clone(), M);
        blas.ssyrk("U", "N", M, K, 0.0f, sgeA, M, 2.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, sepsilon);

        f2j.ssyrk("L", "N", M, K, 0.0f, sgeA, M, 2.0f, expected = ssyA.clone(), M);
        blas.ssyrk("L", "N", M, K, 0.0f, sgeA, M, 2.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, sepsilon);

        f2j.ssyrk("U", "T", M, K, 0.0f, sgeAT, K, 2.0f, expected = ssyA.clone(), M);
        blas.ssyrk("U", "T", M, K, 0.0f, sgeAT, K, 2.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, sepsilon);

        f2j.ssyrk("L", "T", M, K, 0.0f, sgeAT, K, 2.0f, expected = ssyA.clone(), M);
        blas.ssyrk("L", "T", M, K, 0.0f, sgeAT, K, 2.0f, ssyAcopy = ssyA.clone(), M);
        assertArrayEquals(expected, ssyAcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaTwo(BLAS blas) {
        float[] expected, ssyAcopy;

        // alpha=2.0f, beta=1.0f
        // Use relative tolerance since alpha=2 amplifies values, making absolute sepsilon too tight
        f2j.ssyrk("U", "N", M, K, 2.0f, sgeA, M, 1.0f, expected = ssyA.clone(), M);
        blas.ssyrk("U", "N", M, K, 2.0f, sgeA, M, 1.0f, ssyAcopy = ssyA.clone(), M);
        assertRelArrayEquals(expected, ssyAcopy, sepsilon);

        f2j.ssyrk("L", "T", M, K, 2.0f, sgeAT, K, 1.0f, expected = ssyA.clone(), M);
        blas.ssyrk("L", "T", M, K, 2.0f, sgeAT, K, 1.0f, ssyAcopy = ssyA.clone(), M);
        assertRelArrayEquals(expected, ssyAcopy, sepsilon);

        // alpha=2.0f, beta=0.5f
        f2j.ssyrk("U", "N", M, K, 2.0f, sgeA, M, 0.5f, expected = ssyA.clone(), M);
        blas.ssyrk("U", "N", M, K, 2.0f, sgeA, M, 0.5f, ssyAcopy = ssyA.clone(), M);
        assertRelArrayEquals(expected, ssyAcopy, sepsilon);

        f2j.ssyrk("L", "N", M, K, 2.0f, sgeA, M, 0.5f, expected = ssyA.clone(), M);
        blas.ssyrk("L", "N", M, K, 2.0f, sgeA, M, 0.5f, ssyAcopy = ssyA.clone(), M);
        assertRelArrayEquals(expected, ssyAcopy, sepsilon);
    }
}
