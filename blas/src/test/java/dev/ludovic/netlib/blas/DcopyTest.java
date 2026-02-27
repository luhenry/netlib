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

public class DcopyTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        double[] expected = new double[M];
        double[] actual = new double[M];

        f2j.dcopy(M, dX, 1, expected, 1);
        blas.dcopy(M, dX, 1, actual, 1);
        assertArrayEquals(expected, actual, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNonUnitStride(BLAS blas) {
        int n = M / 2;
        double[] expected, actual;

        // incx=2, incy=2
        expected = new double[M];
        actual = new double[M];
        f2j.dcopy(n, dX, 2, expected, 2);
        blas.dcopy(n, dX, 2, actual, 2);
        assertArrayEquals(expected, actual, depsilon);

        // incx=1, incy=2
        expected = new double[M];
        actual = new double[M];
        f2j.dcopy(n, dX, 1, expected, 2);
        blas.dcopy(n, dX, 1, actual, 2);
        assertArrayEquals(expected, actual, depsilon);

        // incx=2, incy=1
        expected = new double[M];
        actual = new double[M];
        f2j.dcopy(n, dX, 2, expected, 1);
        blas.dcopy(n, dX, 2, actual, 1);
        assertArrayEquals(expected, actual, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNegativeStride(BLAS blas) {
        double[] expected, actual;

        // incx=-1, incy=-1
        expected = new double[M];
        actual = new double[M];
        f2j.dcopy(M, dX, -1, expected, -1);
        blas.dcopy(M, dX, -1, actual, -1);
        assertArrayEquals(expected, actual, depsilon);

        // incx=-1, incy=1 (mixed)
        expected = new double[M];
        actual = new double[M];
        f2j.dcopy(M, dX, -1, expected, 1);
        blas.dcopy(M, dX, -1, actual, 1);
        assertArrayEquals(expected, actual, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testZeroN(BLAS blas) {
        double[] expected = dY.clone();
        double[] actual = dY.clone();

        f2j.dcopy(0, dX, 1, expected, 1);
        blas.dcopy(0, dX, 1, actual, 1);
        assertArrayEquals(expected, actual, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOffset(BLAS blas) {
        double[] expected = new double[M + 5];
        double[] actual = new double[M + 5];
        int n = M / 2;

        f2j.dcopy(n, dX, 2, 1, expected, 3, 1);
        blas.dcopy(n, dX, 2, 1, actual, 3, 1);
        assertArrayEquals(expected, actual, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOutOfBound(BLAS blas) {
        assertThrows(java.lang.IndexOutOfBoundsException.class, () -> {
            blas.dcopy(M + 1, dX, 1, new double[M], 1);
        });
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNullArray(BLAS blas) {
        assertThrows(java.lang.NullPointerException.class, () -> {
            blas.dcopy(M, null, 1, new double[M], 1);
        });
        assertThrows(java.lang.NullPointerException.class, () -> {
            blas.dcopy(M, dX, 1, null, 1);
        });
    }
}
