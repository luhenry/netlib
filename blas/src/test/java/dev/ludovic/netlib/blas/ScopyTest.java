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

public class ScopyTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        float[] expected = new float[M];
        float[] actual = new float[M];

        f2j.scopy(M, sX, 1, expected, 1);
        blas.scopy(M, sX, 1, actual, 1);
        assertArrayEquals(expected, actual, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNonUnitStride(BLAS blas) {
        int n = M / 2;
        float[] expected, actual;

        // incx=2, incy=2
        expected = new float[M];
        actual = new float[M];
        f2j.scopy(n, sX, 2, expected, 2);
        blas.scopy(n, sX, 2, actual, 2);
        assertArrayEquals(expected, actual, sepsilon);

        // incx=1, incy=2
        expected = new float[M];
        actual = new float[M];
        f2j.scopy(n, sX, 1, expected, 2);
        blas.scopy(n, sX, 1, actual, 2);
        assertArrayEquals(expected, actual, sepsilon);

        // incx=2, incy=1
        expected = new float[M];
        actual = new float[M];
        f2j.scopy(n, sX, 2, expected, 1);
        blas.scopy(n, sX, 2, actual, 1);
        assertArrayEquals(expected, actual, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNegativeStride(BLAS blas) {
        float[] expected, actual;

        expected = new float[M];
        actual = new float[M];
        f2j.scopy(M, sX, -1, expected, -1);
        blas.scopy(M, sX, -1, actual, -1);
        assertArrayEquals(expected, actual, sepsilon);

        expected = new float[M];
        actual = new float[M];
        f2j.scopy(M, sX, -1, expected, 1);
        blas.scopy(M, sX, -1, actual, 1);
        assertArrayEquals(expected, actual, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testZeroN(BLAS blas) {
        float[] expected = sY.clone();
        float[] actual = sY.clone();

        f2j.scopy(0, sX, 1, expected, 1);
        blas.scopy(0, sX, 1, actual, 1);
        assertArrayEquals(expected, actual, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOffset(BLAS blas) {
        float[] expected = new float[M + 5];
        float[] actual = new float[M + 5];
        int n = M / 2;

        f2j.scopy(n, sX, 2, 1, expected, 3, 1);
        blas.scopy(n, sX, 2, 1, actual, 3, 1);
        assertArrayEquals(expected, actual, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOutOfBound(BLAS blas) {
        assertThrows(java.lang.IndexOutOfBoundsException.class, () -> {
            blas.scopy(M + 1, sX, 1, new float[M], 1);
        });
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNullArray(BLAS blas) {
        assertThrows(java.lang.NullPointerException.class, () -> {
            blas.scopy(M, null, 1, new float[M], 1);
        });
        assertThrows(java.lang.NullPointerException.class, () -> {
            blas.scopy(M, sX, 1, null, 1);
        });
    }
}
