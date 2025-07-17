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

public class DgemvTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        double[] expected, dYcopy;

        f2j.dgemv("N", M, N,  1.0, dgeA, M, dX, 1,  1.0, expected = dY.clone(), 1);
        blas.dgemv("N", M, N,  1.0, dgeA, M, dX, 1,  1.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dgemv("N", M, N,  0.5, dgeA, M, dX, 1,  1.0, expected = dY.clone(), 1);
        blas.dgemv("N", M, N,  0.5, dgeA, M, dX, 1,  1.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dgemv("N", M, N, -0.5, dgeA, M, dX, 1,  1.0, expected = dY.clone(), 1);
        blas.dgemv("N", M, N, -0.5, dgeA, M, dX, 1,  1.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dgemv("N", M, N,  0.0, dgeA, M, dX, 1,  1.0, expected = dY.clone(), 1);
        blas.dgemv("N", M, N,  0.0, dgeA, M, dX, 1,  1.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dgemv("N", M, N,  1.0, dgeA, M, dX, 1,  0.5, expected = dY.clone(), 1);
        blas.dgemv("N", M, N,  1.0, dgeA, M, dX, 1,  0.5, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dgemv("N", M, N,  1.0, dgeA, M, dX, 1, -0.5, expected = dY.clone(), 1);
        blas.dgemv("N", M, N,  1.0, dgeA, M, dX, 1, -0.5, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dgemv("N", M, N,  1.0, dgeA, M, dX, 1,  0.0, expected = dY.clone(), 1);
        blas.dgemv("N", M, N,  1.0, dgeA, M, dX, 1,  0.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dgemv("T", M, N,  1.0, dgeA, M, dX, 1,  1.0, expected = dY.clone(), 1);
        blas.dgemv("T", M, N,  1.0, dgeA, M, dX, 1,  1.0, dYcopy = dY.clone(), 1);
        // for (int i = 0; i < M; i++) {
        //     if (Math.abs(expected[i] - dYcopy[i]) > depsilon) {
        //         System.err.print(String.format("(0) %f != %f at %d\n", expected[i], dYcopy[i], i));
        //     }
        // }
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dgemv("T", M, N,  0.5, dgeA, M, dX, 1,  1.0, expected = dY.clone(), 1);
        blas.dgemv("T", M, N,  0.5, dgeA, M, dX, 1,  1.0, dYcopy = dY.clone(), 1);
        // for (int i = 0; i < M; i++) {
        //     if (Math.abs(expected[i] - dYcopy[i]) > depsilon) {
        //         System.err.print(String.format("(1) %f != %f at %d\n", expected[i], dYcopy[i], i));
        //     }
        // }
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dgemv("T", M, N, -0.5, dgeA, M, dX, 1,  1.0, expected = dY.clone(), 1);
        blas.dgemv("T", M, N, -0.5, dgeA, M, dX, 1,  1.0, dYcopy = dY.clone(), 1);
        // for (int i = 0; i < M; i++) {
        //     if (Math.abs(expected[i] - dYcopy[i]) > depsilon) {
        //         System.err.print(String.format("(2) %f != %f at %d\n", expected[i], dYcopy[i], i));
        //     }
        // }
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dgemv("T", M, N,  0.0, dgeA, M, dX, 1,  1.0, expected = dY.clone(), 1);
        blas.dgemv("T", M, N,  0.0, dgeA, M, dX, 1,  1.0, dYcopy = dY.clone(), 1);
        // for (int i = 0; i < M; i++) {
        //     if (Math.abs(expected[i] - dYcopy[i]) > depsilon) {
        //         System.err.print(String.format("(3) %f != %f at %d\n", expected[i], dYcopy[i], i));
        //     }
        // }
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dgemv("T", M, N,  1.0, dgeA, M, dX, 1,  0.5, expected = dY.clone(), 1);
        blas.dgemv("T", M, N,  1.0, dgeA, M, dX, 1,  0.5, dYcopy = dY.clone(), 1);
        // for (int i = 0; i < M; i++) {
        //     if (Math.abs(expected[i] - dYcopy[i]) > depsilon) {
        //         System.err.print(String.format("(4) %f != %f at %d\n", expected[i], dYcopy[i], i));
        //     }
        // }
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dgemv("T", M, N,  1.0, dgeA, M, dX, 1, -0.5, expected = dY.clone(), 1);
        blas.dgemv("T", M, N,  1.0, dgeA, M, dX, 1, -0.5, dYcopy = dY.clone(), 1);
        // for (int i = 0; i < M; i++) {
        //     if (Math.abs(expected[i] - dYcopy[i]) > depsilon) {
        //         System.err.print(String.format("(5) %f != %f at %d\n", expected[i], dYcopy[i], i));
        //     }
        // }
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dgemv("T", M, N,  1.0, dgeA, M, dX, 1,  0.0, expected = dY.clone(), 1);
        blas.dgemv("T", M, N,  1.0, dgeA, M, dX, 1,  0.0, dYcopy = dY.clone(), 1);
        // for (int i = 0; i < M; i++) {
        //     if (Math.abs(expected[i] - dYcopy[i]) > depsilon) {
        //         System.err.print(String.format("(6) %f != %f at %d\n", expected[i], dYcopy[i], i));
        //     }
        // }
        assertArrayEquals(expected, dYcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOffsetBoundsChecking(BLAS blas) {
        // Test case that reproduces the original bounds checking issue for dgemv
        // Matrix A (2x3) with offset=1, stored in array of length 9
        double[] a = {1.0, 4.0, 7.0, 2.0, 5.0, 8.0, 3.0, 6.0, 9.0};
        double[] x = {1.0, 2.0, 3.0};
        double[] y = new double[2];
        double[] yExpected = new double[2];

        // This should not throw IndexOutOfBoundsException
        assertDoesNotThrow(() -> {
            blas.dgemv("N", 2, 3, 1.0, a, 1, 3, x, 0, 1, 0.0, y, 0, 1);
        });

        // Compare with F2J result
        f2j.dgemv("N", 2, 3, 1.0, a, 1, 3, x, 0, 1, 0.0, yExpected, 0, 1);
        blas.dgemv("N", 2, 3, 1.0, a, 1, 3, x, 0, 1, 0.0, y, 0, 1);
        assertArrayEquals(yExpected, y, depsilon);
    }
}
