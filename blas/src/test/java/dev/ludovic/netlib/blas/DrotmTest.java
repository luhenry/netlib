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

public class DrotmTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        double[] expectedX, expectedY, dXcopy, dYcopy;

        // flag=-1.0: use full H matrix [[h11,h12],[h21,h22]]
        double[] dparam1 = {-1.0, 1.0, 0.0, 0.0, 1.0};
        f2j.drotm(M, expectedX = dX.clone(), 1, expectedY = dY.clone(), 1, dparam1);
        blas.drotm(M, dXcopy = dX.clone(), 1, dYcopy = dY.clone(), 1, dparam1);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);

        // flag=0.0: use H matrix [[1,h12],[h21,1]]
        double[] dparam2 = {0.0, 0.0, -0.5, 0.5, 0.0};
        f2j.drotm(M, expectedX = dX.clone(), 1, expectedY = dY.clone(), 1, dparam2);
        blas.drotm(M, dXcopy = dX.clone(), 1, dYcopy = dY.clone(), 1, dparam2);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);

        // flag=1.0: use H matrix [[h11,1],[-1,h22]]
        double[] dparam3 = {1.0, 0.5, 0.0, 0.0, 0.5};
        f2j.drotm(M, expectedX = dX.clone(), 1, expectedY = dY.clone(), 1, dparam3);
        blas.drotm(M, dXcopy = dX.clone(), 1, dYcopy = dY.clone(), 1, dparam3);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);

        // flag=-2.0: identity (no-op)
        double[] dparam4 = {-2.0, 0.0, 0.0, 0.0, 0.0};
        f2j.drotm(M, expectedX = dX.clone(), 1, expectedY = dY.clone(), 1, dparam4);
        blas.drotm(M, dXcopy = dX.clone(), 1, dYcopy = dY.clone(), 1, dparam4);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNonUnitStride(BLAS blas) {
        double[] expectedX, expectedY, dXcopy, dYcopy;
        int n = M / 2;

        // flag=-1.0 with incx=2, incy=2
        double[] dparam1 = {-1.0, 1.0, 0.0, 0.0, 1.0};
        f2j.drotm(n, expectedX = dX.clone(), 2, expectedY = dY.clone(), 2, dparam1);
        blas.drotm(n, dXcopy = dX.clone(), 2, dYcopy = dY.clone(), 2, dparam1);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);

        // flag=0.0 with incx=2, incy=2
        double[] dparam2 = {0.0, 0.0, -0.5, 0.5, 0.0};
        f2j.drotm(n, expectedX = dX.clone(), 2, expectedY = dY.clone(), 2, dparam2);
        blas.drotm(n, dXcopy = dX.clone(), 2, dYcopy = dY.clone(), 2, dparam2);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);

        // flag=1.0 with incx=2, incy=2
        double[] dparam3 = {1.0, 0.5, 0.0, 0.0, 0.5};
        f2j.drotm(n, expectedX = dX.clone(), 2, expectedY = dY.clone(), 2, dparam3);
        blas.drotm(n, dXcopy = dX.clone(), 2, dYcopy = dY.clone(), 2, dparam3);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);

        // flag=-2.0 with incx=2, incy=2
        double[] dparam4 = {-2.0, 0.0, 0.0, 0.0, 0.0};
        f2j.drotm(n, expectedX = dX.clone(), 2, expectedY = dY.clone(), 2, dparam4);
        blas.drotm(n, dXcopy = dX.clone(), 2, dYcopy = dY.clone(), 2, dparam4);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSingleElement(BLAS blas) {
        double[] expectedX, expectedY, dXcopy, dYcopy;

        // n=1 with flag=-1.0
        double[] dparam1 = {-1.0, 1.0, 0.0, 0.0, 1.0};
        f2j.drotm(1, expectedX = dX.clone(), 1, expectedY = dY.clone(), 1, dparam1);
        blas.drotm(1, dXcopy = dX.clone(), 1, dYcopy = dY.clone(), 1, dparam1);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);

        // n=1 with flag=0.0
        double[] dparam2 = {0.0, 0.0, -0.5, 0.5, 0.0};
        f2j.drotm(1, expectedX = dX.clone(), 1, expectedY = dY.clone(), 1, dparam2);
        blas.drotm(1, dXcopy = dX.clone(), 1, dYcopy = dY.clone(), 1, dparam2);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);
    }
}
