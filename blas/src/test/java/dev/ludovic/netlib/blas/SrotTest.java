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

public class SrotTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        float[] expectedX, expectedY, sXcopy, sYcopy;

        f2j.srot(M, expectedX = sX.clone(), 1, expectedY = sY.clone(), 1, 2.0f, 3.0f);
        blas.srot(M, sXcopy = sX.clone(), 1, sYcopy = sY.clone(), 1, 2.0f, 3.0f);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);

        f2j.srot(M, expectedX = sX.clone(), 1, expectedY = sY.clone(), 1, 0.0f, 3.0f);
        blas.srot(M, sXcopy = sX.clone(), 1, sYcopy = sY.clone(), 1, 0.0f, 3.0f);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);

        f2j.srot(M, expectedX = sX.clone(), 1, expectedY = sY.clone(), 1, 2.0f, 0.0f);
        blas.srot(M, sXcopy = sX.clone(), 1, sYcopy = sY.clone(), 1, 2.0f, 0.0f);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);

        f2j.srot(M, expectedX = sX.clone(), 1, expectedY = sY.clone(), 1, 0.0f, 0.0f);
        blas.srot(M, sXcopy = sX.clone(), 1, sYcopy = sY.clone(), 1, 0.0f, 0.0f);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);
    }
}
