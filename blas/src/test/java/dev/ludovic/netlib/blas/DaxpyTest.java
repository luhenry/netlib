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

public class DaxpyTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        double[] expected, dYcopy;

        f2j.daxpy(M, 2.0, dX, 1, expected = dY.clone(), 1);
        blas.daxpy(M, 2.0, dX, 1, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.daxpy(M, 0.0, dX, 1, expected = dY.clone(), 1);
        blas.daxpy(M, 0.0, dX, 1, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.daxpy(M, -1.0, dX, 1, expected = dY.clone(), 1);
        blas.daxpy(M, -1.0, dX, 1, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNonUnitStride(BLAS blas) {
        double[] expected, dYcopy;
        int n = M / 2;

        f2j.daxpy(n, 2.0, dX, 2, expected = dY.clone(), 2);
        blas.daxpy(n, 2.0, dX, 2, dYcopy = dY.clone(), 2);
        assertArrayEquals(expected, dYcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNegativeStride(BLAS blas) {
        double[] expected, dYcopy;

        // incx=-1, incy=-1
        f2j.daxpy(M, 2.0, dX, -1, expected = dY.clone(), -1);
        blas.daxpy(M, 2.0, dX, -1, dYcopy = dY.clone(), -1);
        assertArrayEquals(expected, dYcopy, depsilon);

        // incx=-1, incy=1 (mixed)
        f2j.daxpy(M, 2.0, dX, -1, expected = dY.clone(), 1);
        blas.daxpy(M, 2.0, dX, -1, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        // non-unit negative stride
        int n = M / 2;
        f2j.daxpy(n, 2.0, dX, -2, expected = dY.clone(), -2);
        blas.daxpy(n, 2.0, dX, -2, dYcopy = dY.clone(), -2);
        assertArrayEquals(expected, dYcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testZeroN(BLAS blas) {
        double[] expected = dY.clone();
        double[] dYcopy = dY.clone();

        blas.daxpy(0, 2.0, dX, 1, dYcopy, 1);
        assertArrayEquals(expected, dYcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOffset(BLAS blas) {
        double[] expected, dYcopy;
        int n = M / 2;

        f2j.daxpy(n, 2.0, dX, 2, 1, expected = dY.clone(), 3, 1);
        blas.daxpy(n, 2.0, dX, 2, 1, dYcopy = dY.clone(), 3, 1);
        assertArrayEquals(expected, dYcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOutOfBound(BLAS blas) {
        assertThrows(java.lang.IndexOutOfBoundsException.class, () -> {
            blas.daxpy(M + 1, 2.0, dX, 1, dY.clone(), 1);
        });
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNullArray(BLAS blas) {
        assertThrows(java.lang.NullPointerException.class, () -> {
            blas.daxpy(M, 2.0, null, 1, dY.clone(), 1);
        });
        assertThrows(java.lang.NullPointerException.class, () -> {
            blas.daxpy(M, 2.0, dX, 1, null, 1);
        });
    }
}
