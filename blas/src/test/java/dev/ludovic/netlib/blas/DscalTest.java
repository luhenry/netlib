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

public class DscalTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        double[] expected, dXcopy;

        f2j.dscal(M, 2.0, expected = dX.clone(), 1);
        blas.dscal(M, 2.0, dXcopy = dX.clone(), 1);
        assertArrayEquals(expected, dXcopy, depsilon);

        f2j.dscal(M, 2.0, expected = dX.clone(), -1);
        blas.dscal(M, 2.0, dXcopy = dX.clone(), -1);
        assertArrayEquals(expected, dXcopy, depsilon);

        f2j.dscal(M, 1.0, expected = dX.clone(), 1);
        blas.dscal(M, 1.0, dXcopy = dX.clone(), 1);
        assertArrayEquals(expected, dXcopy, depsilon);

        f2j.dscal(M, 1.0, expected = dX.clone(), -1);
        blas.dscal(M, 1.0, dXcopy = dX.clone(), -1);
        assertArrayEquals(expected, dXcopy, depsilon);

        f2j.dscal(M, 0.0, expected = dX.clone(), 1);
        blas.dscal(M, 0.0, dXcopy = dX.clone(), 1);
        assertArrayEquals(expected, dXcopy, depsilon);

        f2j.dscal(M, 0.0, expected = dX.clone(), -1);
        blas.dscal(M, 0.0, dXcopy = dX.clone(), -1);
        assertArrayEquals(expected, dXcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNonUnitStride(BLAS blas) {
        double[] expected, dXcopy;
        int n = M / 2;

        f2j.dscal(n, 2.0, expected = dX.clone(), 2);
        blas.dscal(n, 2.0, dXcopy = dX.clone(), 2);
        assertArrayEquals(expected, dXcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testZeroN(BLAS blas) {
        double[] expected = dX.clone();
        double[] dXcopy = dX.clone();

        blas.dscal(0, 2.0, dXcopy, 1);
        assertArrayEquals(expected, dXcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOffset(BLAS blas) {
        double[] expected, dXcopy;
        int n = M / 2;

        f2j.dscal(n, 2.0, expected = dX.clone(), 5, 1);
        blas.dscal(n, 2.0, dXcopy = dX.clone(), 5, 1);
        assertArrayEquals(expected, dXcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOutOfBound(BLAS blas) {
        assertThrows(java.lang.IndexOutOfBoundsException.class, () -> {
            blas.dscal(M + 1, 2.0, dX.clone(), 1);
        });
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNullArray(BLAS blas) {
        assertThrows(java.lang.NullPointerException.class, () -> {
            blas.dscal(M, 2.0, null, 1);
        });
    }
}
