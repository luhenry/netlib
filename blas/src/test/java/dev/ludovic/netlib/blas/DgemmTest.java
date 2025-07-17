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

public class DgemmTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        double[] expected, dgeCcopy;

        f2j.dgemm("N", "N", M, N, K, 1.0, dgeA, M, dgeB, K, 2.0, expected = dgeC.clone(), M);
        blas.dgemm("N", "N", M, N, K, 1.0, dgeA, M, dgeB, K, 2.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "T", M, N, K, 1.0, dgeA, M, dgeBT, N, 2.0, expected = dgeC.clone(), M);
        blas.dgemm("N", "T", M, N, K, 1.0, dgeA, M, dgeBT, N, 2.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "N", M, N, K, 1.0, dgeAT, K, dgeB, K, 2.0, expected = dgeC.clone(), M);
        blas.dgemm("T", "N", M, N, K, 1.0, dgeAT, K, dgeB, K, 2.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "T", M, N, K, 1.0, dgeAT, K, dgeBT, N, 2.0, expected = dgeC.clone(), M);
        blas.dgemm("T", "T", M, N, K, 1.0, dgeAT, K, dgeBT, N, 2.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "N", M, N, K, 1.0, dgeA, M, dgeB, K, 0.0, expected = dgeC.clone(), M);
        blas.dgemm("N", "N", M, N, K, 1.0, dgeA, M, dgeB, K, 0.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "T", M, N, K, 1.0, dgeA, M, dgeBT, N, 0.0, expected = dgeC.clone(), M);
        blas.dgemm("N", "T", M, N, K, 1.0, dgeA, M, dgeBT, N, 0.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "N", M, N, K, 1.0, dgeAT, K, dgeB, K, 0.0, expected = dgeC.clone(), M);
        blas.dgemm("T", "N", M, N, K, 1.0, dgeAT, K, dgeB, K, 0.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "T", M, N, K, 1.0, dgeAT, K, dgeBT, N, 0.0, expected = dgeC.clone(), M);
        blas.dgemm("T", "T", M, N, K, 1.0, dgeAT, K, dgeBT, N, 0.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "N", M, N, K, 0.0, dgeA, M, dgeB, K, 1.0, expected = dgeC.clone(), M);
        blas.dgemm("N", "N", M, N, K, 0.0, dgeA, M, dgeB, K, 1.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "T", M, N, K, 0.0, dgeA, M, dgeBT, N, 1.0, expected = dgeC.clone(), M);
        blas.dgemm("N", "T", M, N, K, 0.0, dgeA, M, dgeBT, N, 1.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "N", M, N, K, 0.0, dgeAT, K, dgeB, K, 1.0, expected = dgeC.clone(), M);
        blas.dgemm("T", "N", M, N, K, 0.0, dgeAT, K, dgeB, K, 1.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "T", M/2, N, K, 0.0, dgeAT, K, dgeBT, N, 1.0, expected = dgeC.clone(), M/2);
        blas.dgemm("T", "T", M/2, N, K, 0.0, dgeAT, K, dgeBT, N, 1.0, dgeCcopy = dgeC.clone(), M/2);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "N", M/2, N, K, 1.0, dgeA, M/2, dgeB, K, 2.0, expected = dgeC.clone(), M/2);
        blas.dgemm("N", "N", M/2, N, K, 1.0, dgeA, M/2, dgeB, K, 2.0, dgeCcopy = dgeC.clone(), M/2);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "T", M/2, N, K, 1.0, dgeA, M/2, dgeBT, N, 2.0, expected = dgeC.clone(), M/2);
        blas.dgemm("N", "T", M/2, N, K, 1.0, dgeA, M/2, dgeBT, N, 2.0, dgeCcopy = dgeC.clone(), M/2);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "N", M/2, N, K, 1.0, dgeAT, K, dgeB, K, 2.0, expected = dgeC.clone(), M/2);
        blas.dgemm("T", "N", M/2, N, K, 1.0, dgeAT, K, dgeB, K, 2.0, dgeCcopy = dgeC.clone(), M/2);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "T", M/2, N, K, 1.0, dgeAT, K, dgeBT, N, 2.0, expected = dgeC.clone(), M/2);
        blas.dgemm("T", "T", M/2, N, K, 1.0, dgeAT, K, dgeBT, N, 2.0, dgeCcopy = dgeC.clone(), M/2);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "N", M/2, N, K, 1.0, dgeA, M/2, dgeB, K, 0.0, expected = dgeC.clone(), M/2);
        blas.dgemm("N", "N", M/2, N, K, 1.0, dgeA, M/2, dgeB, K, 0.0, dgeCcopy = dgeC.clone(), M/2);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "T", M/2, N, K, 1.0, dgeA, M/2, dgeBT, N, 0.0, expected = dgeC.clone(), M/2);
        blas.dgemm("N", "T", M/2, N, K, 1.0, dgeA, M/2, dgeBT, N, 0.0, dgeCcopy = dgeC.clone(), M/2);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "N", M/2, N, K, 1.0, dgeAT, K, dgeB, K, 0.0, expected = dgeC.clone(), M/2);
        blas.dgemm("T", "N", M/2, N, K, 1.0, dgeAT, K, dgeB, K, 0.0, dgeCcopy = dgeC.clone(), M/2);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "T", M/2, N, K, 1.0, dgeAT, K, dgeBT, N, 0.0, expected = dgeC.clone(), M/2);
        blas.dgemm("T", "T", M/2, N, K, 1.0, dgeAT, K, dgeBT, N, 0.0, dgeCcopy = dgeC.clone(), M/2);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "N", M/2, N, K, 0.0, dgeA, M/2, dgeB, K, 1.0, expected = dgeC.clone(), M/2);
        blas.dgemm("N", "N", M/2, N, K, 0.0, dgeA, M/2, dgeB, K, 1.0, dgeCcopy = dgeC.clone(), M/2);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "T", M/2, N, K, 0.0, dgeA, M/2, dgeBT, N, 1.0, expected = dgeC.clone(), M/2);
        blas.dgemm("N", "T", M/2, N, K, 0.0, dgeA, M/2, dgeBT, N, 1.0, dgeCcopy = dgeC.clone(), M/2);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "N", M/2, N, K, 0.0, dgeAT, K, dgeB, K, 1.0, expected = dgeC.clone(), M/2);
        blas.dgemm("T", "N", M/2, N, K, 0.0, dgeAT, K, dgeB, K, 1.0, dgeCcopy = dgeC.clone(), M/2);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "T", M/2, N, K, 0.0, dgeAT, K, dgeBT, N, 1.0, expected = dgeC.clone(), M/2);
        blas.dgemm("T", "T", M/2, N, K, 0.0, dgeAT, K, dgeBT, N, 1.0, dgeCcopy = dgeC.clone(), M/2);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "T", M, N/2, K, 0.0, dgeAT, K, dgeBT, N/2, 1.0, expected = dgeC.clone(), M);
        blas.dgemm("T", "T", M, N/2, K, 0.0, dgeAT, K, dgeBT, N/2, 1.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "N", M, N/2, K, 1.0, dgeA, M, dgeB, K, 2.0, expected = dgeC.clone(), M);
        blas.dgemm("N", "N", M, N/2, K, 1.0, dgeA, M, dgeB, K, 2.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "T", M, N/2, K, 1.0, dgeA, M, dgeBT, N/2, 2.0, expected = dgeC.clone(), M);
        blas.dgemm("N", "T", M, N/2, K, 1.0, dgeA, M, dgeBT, N/2, 2.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "N", M, N/2, K, 1.0, dgeAT, K, dgeB, K, 2.0, expected = dgeC.clone(), M);
        blas.dgemm("T", "N", M, N/2, K, 1.0, dgeAT, K, dgeB, K, 2.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "T", M, N/2, K, 1.0, dgeAT, K, dgeBT, N/2, 2.0, expected = dgeC.clone(), M);
        blas.dgemm("T", "T", M, N/2, K, 1.0, dgeAT, K, dgeBT, N/2, 2.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "N", M, N/2, K, 1.0, dgeA, M, dgeB, K, 0.0, expected = dgeC.clone(), M);
        blas.dgemm("N", "N", M, N/2, K, 1.0, dgeA, M, dgeB, K, 0.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "T", M, N/2, K, 1.0, dgeA, M, dgeBT, N/2, 0.0, expected = dgeC.clone(), M);
        blas.dgemm("N", "T", M, N/2, K, 1.0, dgeA, M, dgeBT, N/2, 0.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "N", M, N/2, K, 1.0, dgeAT, K, dgeB, K, 0.0, expected = dgeC.clone(), M);
        blas.dgemm("T", "N", M, N/2, K, 1.0, dgeAT, K, dgeB, K, 0.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "T", M, N/2, K, 1.0, dgeAT, K, dgeBT, N/2, 0.0, expected = dgeC.clone(), M);
        blas.dgemm("T", "T", M, N/2, K, 1.0, dgeAT, K, dgeBT, N/2, 0.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "N", M, N/2, K, 0.0, dgeA, M, dgeB, K, 1.0, expected = dgeC.clone(), M);
        blas.dgemm("N", "N", M, N/2, K, 0.0, dgeA, M, dgeB, K, 1.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "T", M, N/2, K, 0.0, dgeA, M, dgeBT, N/2, 1.0, expected = dgeC.clone(), M);
        blas.dgemm("N", "T", M, N/2, K, 0.0, dgeA, M, dgeBT, N/2, 1.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "N", M, N/2, K, 0.0, dgeAT, K, dgeB, K, 1.0, expected = dgeC.clone(), M);
        blas.dgemm("T", "N", M, N/2, K, 0.0, dgeAT, K, dgeB, K, 1.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "T", M, N/2, K, 0.0, dgeAT, K, dgeBT, N/2, 1.0, expected = dgeC.clone(), M);
        blas.dgemm("T", "T", M, N/2, K, 0.0, dgeAT, K, dgeBT, N/2, 1.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOffsetBoundsChecking(BLAS blas) {
        // Test case that reproduces the original bounds checking issue
        // Matrix A (2x3) with offset=1, stored in array of length 9
        double[] a = {1.0, 4.0, 7.0, 2.0, 5.0, 8.0, 3.0, 6.0, 9.0};
        double[] b = {1.0, 2.0, 3.0};
        double[] c = new double[2];
        double[] cExpected = new double[2];

        // This should not throw IndexOutOfBoundsException
        assertDoesNotThrow(() -> {
            blas.dgemm("N", "N", 2, 1, 3, 1.0, a, 1, 3, b, 0, 3, 0.0, c, 0, 2);
        });

        // Compare with F2J result
        f2j.dgemm("N", "N", 2, 1, 3, 1.0, a, 1, 3, b, 0, 3, 0.0, cExpected, 0, 2);
        blas.dgemm("N", "N", 2, 1, 3, 1.0, a, 1, 3, b, 0, 3, 0.0, c, 0, 2);
        assertArrayEquals(cExpected, c, depsilon);
    }
}
