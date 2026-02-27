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

public class DsyrTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        double[] expected, dsyAcopy;

        f2j.dsyr("U", M, 2.0, dX, 1, expected = dsyA.clone(), M);
        blas.dsyr("U", M, 2.0, dX, 1, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);

        f2j.dsyr("L", M, 2.0, dX, 1, expected = dsyA.clone(), M);
        blas.dsyr("L", M, 2.0, dX, 1, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaZero(BLAS blas) {
        double[] expected, dsyAcopy;

        // alpha=0.0 should be a no-op (C unchanged)
        f2j.dsyr("U", M, 0.0, dX, 1, expected = dsyA.clone(), M);
        blas.dsyr("U", M, 0.0, dX, 1, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);

        f2j.dsyr("L", M, 0.0, dX, 1, expected = dsyA.clone(), M);
        blas.dsyr("L", M, 0.0, dX, 1, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaNegative(BLAS blas) {
        double[] expected, dsyAcopy;

        // alpha=-1.0
        f2j.dsyr("U", M, -1.0, dX, 1, expected = dsyA.clone(), M);
        blas.dsyr("U", M, -1.0, dX, 1, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);

        f2j.dsyr("L", M, -1.0, dX, 1, expected = dsyA.clone(), M);
        blas.dsyr("L", M, -1.0, dX, 1, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNegativeStride(BLAS blas) {
        double[] expected, dsyAcopy;

        f2j.dsyr("U", M, 2.0, dX, -1, expected = dsyA.clone(), M);
        blas.dsyr("U", M, 2.0, dX, -1, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);

        f2j.dsyr("L", M, 2.0, dX, -1, expected = dsyA.clone(), M);
        blas.dsyr("L", M, 2.0, dX, -1, dsyAcopy = dsyA.clone(), M);
        assertArrayEquals(expected, dsyAcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testInvalidArguments(BLAS blas) {
        // invalid uplo
        assertThrows(IllegalArgumentException.class, () -> {
            blas.dsyr("X", M, 2.0, dX, 1, dsyA.clone(), M);
        });
        // negative n
        assertThrows(IllegalArgumentException.class, () -> {
            blas.dsyr("U", -1, 2.0, dX, 1, dsyA.clone(), M);
        });
        // incx == 0
        assertThrows(IllegalArgumentException.class, () -> {
            blas.dsyr("U", M, 2.0, dX, 0, dsyA.clone(), M);
        });
        // lda too small
        assertThrows(IllegalArgumentException.class, () -> {
            blas.dsyr("U", M, 2.0, dX, 1, dsyA.clone(), M - 1);
        });
    }
}
