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

public class SrotmTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        float[] expectedX, expectedY, sXcopy, sYcopy;

        // flag=-1.0f: use full H matrix [[h11,h12],[h21,h22]]
        float[] sparam1 = {-1.0f, 1.0f, 0.0f, 0.0f, 1.0f};
        f2j.srotm(M, expectedX = sX.clone(), 1, expectedY = sY.clone(), 1, sparam1);
        blas.srotm(M, sXcopy = sX.clone(), 1, sYcopy = sY.clone(), 1, sparam1);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);

        // flag=0.0f: use H matrix [[1,h12],[h21,1]]
        float[] sparam2 = {0.0f, 0.0f, -0.5f, 0.5f, 0.0f};
        f2j.srotm(M, expectedX = sX.clone(), 1, expectedY = sY.clone(), 1, sparam2);
        blas.srotm(M, sXcopy = sX.clone(), 1, sYcopy = sY.clone(), 1, sparam2);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);

        // flag=1.0f: use H matrix [[h11,1],[-1,h22]]
        float[] sparam3 = {1.0f, 0.5f, 0.0f, 0.0f, 0.5f};
        f2j.srotm(M, expectedX = sX.clone(), 1, expectedY = sY.clone(), 1, sparam3);
        blas.srotm(M, sXcopy = sX.clone(), 1, sYcopy = sY.clone(), 1, sparam3);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);

        // flag=-2.0f: identity (no-op)
        float[] sparam4 = {-2.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        f2j.srotm(M, expectedX = sX.clone(), 1, expectedY = sY.clone(), 1, sparam4);
        blas.srotm(M, sXcopy = sX.clone(), 1, sYcopy = sY.clone(), 1, sparam4);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNonUnitStride(BLAS blas) {
        float[] expectedX, expectedY, sXcopy, sYcopy;
        int n = M / 2;

        // flag=-1.0f with incx=2, incy=2
        float[] sparam1 = {-1.0f, 1.0f, 0.0f, 0.0f, 1.0f};
        f2j.srotm(n, expectedX = sX.clone(), 2, expectedY = sY.clone(), 2, sparam1);
        blas.srotm(n, sXcopy = sX.clone(), 2, sYcopy = sY.clone(), 2, sparam1);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);

        // flag=0.0f with incx=2, incy=2
        float[] sparam2 = {0.0f, 0.0f, -0.5f, 0.5f, 0.0f};
        f2j.srotm(n, expectedX = sX.clone(), 2, expectedY = sY.clone(), 2, sparam2);
        blas.srotm(n, sXcopy = sX.clone(), 2, sYcopy = sY.clone(), 2, sparam2);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);

        // flag=1.0f with incx=2, incy=2
        float[] sparam3 = {1.0f, 0.5f, 0.0f, 0.0f, 0.5f};
        f2j.srotm(n, expectedX = sX.clone(), 2, expectedY = sY.clone(), 2, sparam3);
        blas.srotm(n, sXcopy = sX.clone(), 2, sYcopy = sY.clone(), 2, sparam3);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);

        // flag=-2.0f with incx=2, incy=2
        float[] sparam4 = {-2.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        f2j.srotm(n, expectedX = sX.clone(), 2, expectedY = sY.clone(), 2, sparam4);
        blas.srotm(n, sXcopy = sX.clone(), 2, sYcopy = sY.clone(), 2, sparam4);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSingleElement(BLAS blas) {
        float[] expectedX, expectedY, sXcopy, sYcopy;

        // n=1 with flag=-1.0f
        float[] sparam1 = {-1.0f, 1.0f, 0.0f, 0.0f, 1.0f};
        f2j.srotm(1, expectedX = sX.clone(), 1, expectedY = sY.clone(), 1, sparam1);
        blas.srotm(1, sXcopy = sX.clone(), 1, sYcopy = sY.clone(), 1, sparam1);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);

        // n=1 with flag=0.0f
        float[] sparam2 = {0.0f, 0.0f, -0.5f, 0.5f, 0.0f};
        f2j.srotm(1, expectedX = sX.clone(), 1, expectedY = sY.clone(), 1, sparam2);
        blas.srotm(1, sXcopy = sX.clone(), 1, sYcopy = sY.clone(), 1, sparam2);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);
    }
}
