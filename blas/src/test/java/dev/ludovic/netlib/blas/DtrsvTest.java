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

public class DtrsvTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        double[] expected, dXcopy;

        // uplo=U, trans=N, diag=N
        f2j.dtrsv("U", "N", "N", M, dsyA, M, expected = dX.clone(), 1);
        blas.dtrsv("U", "N", "N", M, dsyA, M, dXcopy = dX.clone(), 1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);

        // uplo=L, trans=N, diag=N
        f2j.dtrsv("L", "N", "N", M, dsyA, M, expected = dX.clone(), 1);
        blas.dtrsv("L", "N", "N", M, dsyA, M, dXcopy = dX.clone(), 1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);

        // uplo=U, trans=T, diag=N
        f2j.dtrsv("U", "T", "N", M, dsyA, M, expected = dX.clone(), 1);
        blas.dtrsv("U", "T", "N", M, dsyA, M, dXcopy = dX.clone(), 1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);

        // uplo=L, trans=T, diag=U
        f2j.dtrsv("L", "T", "U", M, dsyA, M, expected = dX.clone(), 1);
        blas.dtrsv("L", "T", "U", M, dsyA, M, dXcopy = dX.clone(), 1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAllUploTransCombinations(BLAS blas) {
        double[] expected, dXcopy;

        // uplo=L, trans=T, diag=N
        f2j.dtrsv("L", "T", "N", M, dsyA, M, expected = dX.clone(), 1);
        blas.dtrsv("L", "T", "N", M, dsyA, M, dXcopy = dX.clone(), 1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testUnitDiagonal(BLAS blas) {
        double[] expected, dXcopy;

        // uplo=U, trans=N, diag=U
        f2j.dtrsv("U", "N", "U", M, dsyA, M, expected = dX.clone(), 1);
        blas.dtrsv("U", "N", "U", M, dsyA, M, dXcopy = dX.clone(), 1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);

        // uplo=L, trans=N, diag=U
        f2j.dtrsv("L", "N", "U", M, dsyA, M, expected = dX.clone(), 1);
        blas.dtrsv("L", "N", "U", M, dsyA, M, dXcopy = dX.clone(), 1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);

        // uplo=U, trans=T, diag=U
        f2j.dtrsv("U", "T", "U", M, dsyA, M, expected = dX.clone(), 1);
        blas.dtrsv("U", "T", "U", M, dsyA, M, dXcopy = dX.clone(), 1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);

        // uplo=L, trans=T, diag=U (already in sanity, but included for completeness)
        f2j.dtrsv("L", "T", "U", M, dsyA, M, expected = dX.clone(), 1);
        blas.dtrsv("L", "T", "U", M, dsyA, M, dXcopy = dX.clone(), 1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);
    }
}
