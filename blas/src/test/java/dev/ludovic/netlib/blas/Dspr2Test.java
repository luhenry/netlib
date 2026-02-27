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

public class Dspr2Test extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        double[] expected, dgeAcopy;

        f2j.dspr2("U", M, 2.0, dX, 1, dY, 1, expected = dgeAU.clone());
        blas.dspr2("U", M, 2.0, dX, 1, dY, 1, dgeAcopy = dgeAU.clone());
        assertArrayEquals(expected, dgeAcopy, depsilon);

        f2j.dspr2("L", M, 2.0, dX, 1, dY, 1, expected = dgeAL.clone());
        blas.dspr2("L", M, 2.0, dX, 1, dY, 1, dgeAcopy = dgeAL.clone());
        assertArrayEquals(expected, dgeAcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaZero(BLAS blas) {
        double[] expected, dgeAcopy;

        // alpha=0.0 should be a no-op
        f2j.dspr2("U", M, 0.0, dX, 1, dY, 1, expected = dgeAU.clone());
        blas.dspr2("U", M, 0.0, dX, 1, dY, 1, dgeAcopy = dgeAU.clone());
        assertArrayEquals(expected, dgeAcopy, depsilon);

        f2j.dspr2("L", M, 0.0, dX, 1, dY, 1, expected = dgeAL.clone());
        blas.dspr2("L", M, 0.0, dX, 1, dY, 1, dgeAcopy = dgeAL.clone());
        assertArrayEquals(expected, dgeAcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNonUnitStride(BLAS blas) {
        double[] expected, dgeAcopy;
        int smallN = M / 2;

        f2j.dspr2("U", smallN, 2.0, dX, 2, dY, 2, expected = dgeAU.clone());
        blas.dspr2("U", smallN, 2.0, dX, 2, dY, 2, dgeAcopy = dgeAU.clone());
        assertArrayEquals(expected, dgeAcopy, depsilon);

        f2j.dspr2("L", smallN, 2.0, dX, 2, dY, 2, expected = dgeAL.clone());
        blas.dspr2("L", smallN, 2.0, dX, 2, dY, 2, dgeAcopy = dgeAL.clone());
        assertArrayEquals(expected, dgeAcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNegativeStride(BLAS blas) {
        double[] expected, dgeAcopy;

        f2j.dspr2("U", M, 2.0, dX, -1, dY, -1, expected = dgeAU.clone());
        blas.dspr2("U", M, 2.0, dX, -1, dY, -1, dgeAcopy = dgeAU.clone());
        assertArrayEquals(expected, dgeAcopy, depsilon);

        f2j.dspr2("L", M, 2.0, dX, -1, dY, -1, expected = dgeAL.clone());
        blas.dspr2("L", M, 2.0, dX, -1, dY, -1, dgeAcopy = dgeAL.clone());
        assertArrayEquals(expected, dgeAcopy, depsilon);

        f2j.dspr2("U", M, 2.0, dX, -1, dY, 1, expected = dgeAU.clone());
        blas.dspr2("U", M, 2.0, dX, -1, dY, 1, dgeAcopy = dgeAU.clone());
        assertArrayEquals(expected, dgeAcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testInvalidUplo(BLAS blas) {
        // invalid uplo
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.dspr2("X", M, 2.0, dX, 1, dY, 1, dgeAU.clone());
        });
        // negative n
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.dspr2("U", -1, 2.0, dX, 1, dY, 1, dgeAU.clone());
        });
        // incx == 0
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.dspr2("U", M, 2.0, dX, 0, dY, 1, dgeAU.clone());
        });
        // incy == 0
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.dspr2("U", M, 2.0, dX, 1, dY, 0, dgeAU.clone());
        });
    }
}
