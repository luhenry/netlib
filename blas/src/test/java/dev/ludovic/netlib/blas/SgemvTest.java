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

public class SgemvTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        float[] expected, sYcopy;

        f2j.sgemv("N", M, N,  1.0f, sgeA, M, sX, 1,  1.0f, expected = sY.clone(), 1);
        blas.sgemv("N", M, N,  1.0f, sgeA, M, sX, 1,  1.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.sgemv("N", M, N,  0.5f, sgeA, M, sX, 1,  1.0f, expected = sY.clone(), 1);
        blas.sgemv("N", M, N,  0.5f, sgeA, M, sX, 1,  1.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.sgemv("N", M, N, -0.5f, sgeA, M, sX, 1,  1.0f, expected = sY.clone(), 1);
        blas.sgemv("N", M, N, -0.5f, sgeA, M, sX, 1,  1.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.sgemv("N", M, N,  0.0f, sgeA, M, sX, 1,  1.0f, expected = sY.clone(), 1);
        blas.sgemv("N", M, N,  0.0f, sgeA, M, sX, 1,  1.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.sgemv("N", M, N,  1.0f, sgeA, M, sX, 1,  0.5f, expected = sY.clone(), 1);
        blas.sgemv("N", M, N,  1.0f, sgeA, M, sX, 1,  0.5f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.sgemv("N", M, N,  1.0f, sgeA, M, sX, 1, -0.5f, expected = sY.clone(), 1);
        blas.sgemv("N", M, N,  1.0f, sgeA, M, sX, 1, -0.5f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.sgemv("N", M, N,  1.0f, sgeA, M, sX, 1,  0.0f, expected = sY.clone(), 1);
        blas.sgemv("N", M, N,  1.0f, sgeA, M, sX, 1,  0.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.sgemv("T", M, N,  1.0f, sgeA, M, sX, 1,  1.0f, expected = sY.clone(), 1);
        blas.sgemv("T", M, N,  1.0f, sgeA, M, sX, 1,  1.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.sgemv("T", M, N,  0.5f, sgeA, M, sX, 1,  1.0f, expected = sY.clone(), 1);
        blas.sgemv("T", M, N,  0.5f, sgeA, M, sX, 1,  1.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.sgemv("T", M, N, -0.5f, sgeA, M, sX, 1,  1.0f, expected = sY.clone(), 1);
        blas.sgemv("T", M, N, -0.5f, sgeA, M, sX, 1,  1.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.sgemv("T", M, N,  0.0f, sgeA, M, sX, 1,  1.0f, expected = sY.clone(), 1);
        blas.sgemv("T", M, N,  0.0f, sgeA, M, sX, 1,  1.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.sgemv("T", M, N,  1.0f, sgeA, M, sX, 1,  0.5f, expected = sY.clone(), 1);
        blas.sgemv("T", M, N,  1.0f, sgeA, M, sX, 1,  0.5f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.sgemv("T", M, N,  1.0f, sgeA, M, sX, 1, -0.5f, expected = sY.clone(), 1);
        blas.sgemv("T", M, N,  1.0f, sgeA, M, sX, 1, -0.5f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        f2j.sgemv("T", M, N,  1.0f, sgeA, M, sX, 1,  0.0f, expected = sY.clone(), 1);
        blas.sgemv("T", M, N,  1.0f, sgeA, M, sX, 1,  0.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOffset1BoundsChecking(BLAS blas) {
        // Test case that reproduces the original bounds checking issue for sgemv
        // Matrix A (2x3) with offset=1, stored in array of length 9
        float[] a = {1.0f, 4.0f, 7.0f, 2.0f, 5.0f, 8.0f, 3.0f, 6.0f, 9.0f};
        float[] x = {1.0f, 2.0f, 3.0f};
        float[] y = new float[2];
        float[] yExpected = new float[2];

        // This should not throw IndexOutOfBoundsException
        assertDoesNotThrow(() -> {
            blas.sgemv("N", 2, 3, 1.0f, a, 1, 3, x, 0, 1, 0.0f, y, 0, 1);
        });

        // Compare with F2J result
        f2j.sgemv("N", 2, 3, 1.0f, a, 1, 3, x, 0, 1, 0.0f, yExpected, 0, 1);
        blas.sgemv("N", 2, 3, 1.0f, a, 1, 3, x, 0, 1, 0.0f, y, 0, 1);
        assertArrayEquals(yExpected, y, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOffset2BoundsChecking(BLAS blas) {
        // Test case that reproduces the original bounds checking issue for sgemv
        // Matrix A (2x3) with offset=1, stored in array of length 9
        float[] a = {1.0f, 4.0f, 7.0f, 2.0f, 5.0f, 8.0f, 3.0f, 6.0f, 9.0f, 10.0f};
        float[] x = {1.0f, 2.0f, 3.0f};
        float[] y = new float[2];
        float[] yExpected = new float[2];

        // This should not throw IndexOutOfBoundsException
        assertDoesNotThrow(() -> {
            blas.sgemv("N", 2, 3, 1.0f, a, 2, 3, x, 0, 1, 0.0f, y, 0, 1);
        });

        // Compare with F2J result
        f2j.sgemv("N", 2, 3, 1.0f, a, 2, 3, x, 0, 1, 0.0f, yExpected, 0, 1);
        blas.sgemv("N", 2, 3, 1.0f, a, 2, 3, x, 0, 1, 0.0f, y, 0, 1);
        assertArrayEquals(yExpected, y, sepsilon);
    }
}
