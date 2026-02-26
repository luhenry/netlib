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

public class IsamaxTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        assertEquals(f2j.isamax(M, sX, 1), blas.isamax(M, sX, 1));
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testStridedAccess(BLAS blas) {
        // incx=2: strided access, only processes M/2 elements with stride 2
        int n = M / 2;
        assertEquals(f2j.isamax(n, sX, 2), blas.isamax(n, sX, 2));
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSingleElement(BLAS blas) {
        // n=1: single element should always return index 0
        assertEquals(f2j.isamax(1, sX, 1), blas.isamax(1, sX, 1));
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testZeroElements(BLAS blas) {
        // n=0: should return -1
        assertEquals(f2j.isamax(0, sX, 1), blas.isamax(0, sX, 1));
    }
}
