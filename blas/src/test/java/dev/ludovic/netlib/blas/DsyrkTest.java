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

public class DsyrkTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        double[] expected, dsyAcopy;

        // uplo=U, trans=N: C := alpha*A*A^T + beta*C, A is n x k (M x K), lda=M
        f2j.dsyrk("U", "N", M, K, 1.0, dgeA, M, 2.0, expected = dsyA.clone(), M);
        blas.dsyrk("U", "N", M, K, 1.0, dgeA, M, 2.0, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);

        // uplo=L, trans=N
        f2j.dsyrk("L", "N", M, K, 1.0, dgeA, M, 2.0, expected = dsyA.clone(), M);
        blas.dsyrk("L", "N", M, K, 1.0, dgeA, M, 2.0, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);

        // uplo=U, trans=T: C := alpha*A^T*A + beta*C, A is k x n (K x M), lda=K
        f2j.dsyrk("U", "T", M, K, 1.0, dgeAT, K, 2.0, expected = dsyA.clone(), M);
        blas.dsyrk("U", "T", M, K, 1.0, dgeAT, K, 2.0, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);

        // uplo=L, trans=T
        f2j.dsyrk("L", "T", M, K, 1.0, dgeAT, K, 2.0, expected = dsyA.clone(), M);
        blas.dsyrk("L", "T", M, K, 1.0, dgeAT, K, 2.0, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);

        // beta=0.0 cases
        f2j.dsyrk("U", "N", M, K, 1.0, dgeA, M, 0.0, expected = dsyA.clone(), M);
        blas.dsyrk("U", "N", M, K, 1.0, dgeA, M, 0.0, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);

        f2j.dsyrk("L", "N", M, K, 1.0, dgeA, M, 0.0, expected = dsyA.clone(), M);
        blas.dsyrk("L", "N", M, K, 1.0, dgeA, M, 0.0, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);

        f2j.dsyrk("U", "T", M, K, 1.0, dgeAT, K, 0.0, expected = dsyA.clone(), M);
        blas.dsyrk("U", "T", M, K, 1.0, dgeAT, K, 0.0, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);

        f2j.dsyrk("L", "T", M, K, 1.0, dgeAT, K, 0.0, expected = dsyA.clone(), M);
        blas.dsyrk("L", "T", M, K, 1.0, dgeAT, K, 0.0, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaZeroBetaScale(BLAS blas) {
        double[] expected, dsyAcopy;

        // alpha=0.0, beta=2.0: should just scale C by 2.0
        f2j.dsyrk("U", "N", M, K, 0.0, dgeA, M, 2.0, expected = dsyA.clone(), M);
        blas.dsyrk("U", "N", M, K, 0.0, dgeA, M, 2.0, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);

        f2j.dsyrk("L", "N", M, K, 0.0, dgeA, M, 2.0, expected = dsyA.clone(), M);
        blas.dsyrk("L", "N", M, K, 0.0, dgeA, M, 2.0, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);

        f2j.dsyrk("U", "T", M, K, 0.0, dgeAT, K, 2.0, expected = dsyA.clone(), M);
        blas.dsyrk("U", "T", M, K, 0.0, dgeAT, K, 2.0, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);

        f2j.dsyrk("L", "T", M, K, 0.0, dgeAT, K, 2.0, expected = dsyA.clone(), M);
        blas.dsyrk("L", "T", M, K, 0.0, dgeAT, K, 2.0, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaTwo(BLAS blas) {
        double[] expected, dsyAcopy;

        // alpha=2.0, beta=1.0
        f2j.dsyrk("U", "N", M, K, 2.0, dgeA, M, 1.0, expected = dsyA.clone(), M);
        blas.dsyrk("U", "N", M, K, 2.0, dgeA, M, 1.0, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);

        f2j.dsyrk("L", "T", M, K, 2.0, dgeAT, K, 1.0, expected = dsyA.clone(), M);
        blas.dsyrk("L", "T", M, K, 2.0, dgeAT, K, 1.0, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);

        // alpha=2.0, beta=0.5
        f2j.dsyrk("U", "N", M, K, 2.0, dgeA, M, 0.5, expected = dsyA.clone(), M);
        blas.dsyrk("U", "N", M, K, 2.0, dgeA, M, 0.5, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);

        f2j.dsyrk("L", "N", M, K, 2.0, dgeA, M, 0.5, expected = dsyA.clone(), M);
        blas.dsyrk("L", "N", M, K, 2.0, dgeA, M, 0.5, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);
    }
}
