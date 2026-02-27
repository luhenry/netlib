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

public class DrotTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        double[] expectedX, expectedY, dXcopy, dYcopy;

        f2j.drot(M, expectedX = dX.clone(), 1, expectedY = dY.clone(), 1, 2.0, 3.0);
        blas.drot(M, dXcopy = dX.clone(), 1, dYcopy = dY.clone(), 1, 2.0, 3.0);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);

        f2j.drot(M, expectedX = dX.clone(), 1, expectedY = dY.clone(), 1, 0.0, 3.0);
        blas.drot(M, dXcopy = dX.clone(), 1, dYcopy = dY.clone(), 1, 0.0, 3.0);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);

        f2j.drot(M, expectedX = dX.clone(), 1, expectedY = dY.clone(), 1, 2.0, 0.0);
        blas.drot(M, dXcopy = dX.clone(), 1, dYcopy = dY.clone(), 1, 2.0, 0.0);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);

        f2j.drot(M, expectedX = dX.clone(), 1, expectedY = dY.clone(), 1, 0.0, 0.0);
        blas.drot(M, dXcopy = dX.clone(), 1, dYcopy = dY.clone(), 1, 0.0, 0.0);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNonUnitStride(BLAS blas) {
        double[] expectedX, expectedY, dXcopy, dYcopy;
        int n = M / 2;

        f2j.drot(n, expectedX = dX.clone(), 2, expectedY = dY.clone(), 2, 2.0, 3.0);
        blas.drot(n, dXcopy = dX.clone(), 2, dYcopy = dY.clone(), 2, 2.0, 3.0);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNegativeStride(BLAS blas) {
        double[] expectedX, expectedY, dXcopy, dYcopy;

        f2j.drot(M, expectedX = dX.clone(), -1, expectedY = dY.clone(), -1, 2.0, 3.0);
        blas.drot(M, dXcopy = dX.clone(), -1, dYcopy = dY.clone(), -1, 2.0, 3.0);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);

        f2j.drot(M, expectedX = dX.clone(), -1, expectedY = dY.clone(), 1, 2.0, 3.0);
        blas.drot(M, dXcopy = dX.clone(), -1, dYcopy = dY.clone(), 1, 2.0, 3.0);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testZeroN(BLAS blas) {
        double[] expectedX = dX.clone();
        double[] expectedY = dY.clone();
        double[] dXcopy = dX.clone();
        double[] dYcopy = dY.clone();

        blas.drot(0, dXcopy, 1, dYcopy, 1, 2.0, 3.0);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOffset(BLAS blas) {
        double[] expectedX, expectedY, dXcopy, dYcopy;
        int n = M / 2;

        f2j.drot(n, expectedX = dX.clone(), 2, 1, expectedY = dY.clone(), 3, 1, 2.0, 3.0);
        blas.drot(n, dXcopy = dX.clone(), 2, 1, dYcopy = dY.clone(), 3, 1, 2.0, 3.0);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOutOfBound(BLAS blas) {
        assertThrows(java.lang.IndexOutOfBoundsException.class, () -> {
            blas.drot(M + 1, dX.clone(), 1, dY.clone(), 1, 2.0, 3.0);
        });
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNullArray(BLAS blas) {
        assertThrows(java.lang.NullPointerException.class, () -> {
            blas.drot(M, null, 1, dY.clone(), 1, 2.0, 3.0);
        });
        assertThrows(java.lang.NullPointerException.class, () -> {
            blas.drot(M, dX.clone(), 1, null, 1, 2.0, 3.0);
        });
    }
}
