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

public class DtbsvTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        double[] expected, dXcopy;

        // uplo=U, trans=N, diag=N
        f2j.dtbsv("U", "N", "N", M, KU, dtbAU, KU + 1, expected = dX.clone(), 1);
        blas.dtbsv("U", "N", "N", M, KU, dtbAU, KU + 1, dXcopy = dX.clone(), 1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);

        // uplo=L, trans=N, diag=N
        f2j.dtbsv("L", "N", "N", M, KU, dtbAL, KU + 1, expected = dX.clone(), 1);
        blas.dtbsv("L", "N", "N", M, KU, dtbAL, KU + 1, dXcopy = dX.clone(), 1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);

        // uplo=U, trans=T, diag=N
        f2j.dtbsv("U", "T", "N", M, KU, dtbAU, KU + 1, expected = dX.clone(), 1);
        blas.dtbsv("U", "T", "N", M, KU, dtbAU, KU + 1, dXcopy = dX.clone(), 1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);

        // uplo=L, trans=T, diag=U
        f2j.dtbsv("L", "T", "U", M, KU, dtbAL, KU + 1, expected = dX.clone(), 1);
        blas.dtbsv("L", "T", "U", M, KU, dtbAL, KU + 1, dXcopy = dX.clone(), 1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAllUploTransCombinations(BLAS blas) {
        double[] expected, dXcopy;

        // uplo=L, trans=T, diag=N
        f2j.dtbsv("L", "T", "N", M, KU, dtbAL, KU + 1, expected = dX.clone(), 1);
        blas.dtbsv("L", "T", "N", M, KU, dtbAL, KU + 1, dXcopy = dX.clone(), 1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testUnitDiagonal(BLAS blas) {
        double[] expected, dXcopy;

        // uplo=U, trans=N, diag=U
        f2j.dtbsv("U", "N", "U", M, KU, dtbAU, KU + 1, expected = dX.clone(), 1);
        blas.dtbsv("U", "N", "U", M, KU, dtbAU, KU + 1, dXcopy = dX.clone(), 1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);

        // uplo=L, trans=N, diag=U
        f2j.dtbsv("L", "N", "U", M, KU, dtbAL, KU + 1, expected = dX.clone(), 1);
        blas.dtbsv("L", "N", "U", M, KU, dtbAL, KU + 1, dXcopy = dX.clone(), 1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);

        // uplo=U, trans=T, diag=U
        f2j.dtbsv("U", "T", "U", M, KU, dtbAU, KU + 1, expected = dX.clone(), 1);
        blas.dtbsv("U", "T", "U", M, KU, dtbAU, KU + 1, dXcopy = dX.clone(), 1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);

        // uplo=L, trans=T, diag=U (already in sanity, but included for completeness)
        f2j.dtbsv("L", "T", "U", M, KU, dtbAL, KU + 1, expected = dX.clone(), 1);
        blas.dtbsv("L", "T", "U", M, KU, dtbAL, KU + 1, dXcopy = dX.clone(), 1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNegativeStride(BLAS blas) {
        double[] expected, dXcopy;

        f2j.dtbsv("U", "N", "N", M, KU, dtbAU, KU + 1, expected = dX.clone(), -1);
        blas.dtbsv("U", "N", "N", M, KU, dtbAU, KU + 1, dXcopy = dX.clone(), -1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);

        f2j.dtbsv("L", "N", "N", M, KU, dtbAL, KU + 1, expected = dX.clone(), -1);
        blas.dtbsv("L", "N", "N", M, KU, dtbAL, KU + 1, dXcopy = dX.clone(), -1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);

        f2j.dtbsv("U", "T", "N", M, KU, dtbAU, KU + 1, expected = dX.clone(), -1);
        blas.dtbsv("U", "T", "N", M, KU, dtbAU, KU + 1, dXcopy = dX.clone(), -1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);

        f2j.dtbsv("L", "T", "N", M, KU, dtbAL, KU + 1, expected = dX.clone(), -1);
        blas.dtbsv("L", "T", "N", M, KU, dtbAL, KU + 1, dXcopy = dX.clone(), -1);
        assertRelArrayEquals(expected, dXcopy, dsolveEpsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testInvalidArguments(BLAS blas) {
        assertThrows(IllegalArgumentException.class, () -> { blas.dtbsv("X", "N", "N", M, KU, dtbAU, KU + 1, dX.clone(), 1); }); // invalid uplo
        assertThrows(IllegalArgumentException.class, () -> { blas.dtbsv("U", "X", "N", M, KU, dtbAU, KU + 1, dX.clone(), 1); }); // invalid trans
        assertThrows(IllegalArgumentException.class, () -> { blas.dtbsv("U", "N", "X", M, KU, dtbAU, KU + 1, dX.clone(), 1); }); // invalid diag
        assertThrows(IllegalArgumentException.class, () -> { blas.dtbsv("U", "N", "N", -1, KU, dtbAU, KU + 1, dX.clone(), 1); }); // negative n
        assertThrows(IllegalArgumentException.class, () -> { blas.dtbsv("U", "N", "N", M, -1, dtbAU, KU + 1, dX.clone(), 1); }); // negative k
        assertThrows(IllegalArgumentException.class, () -> { blas.dtbsv("U", "N", "N", M, KU, dtbAU, KU, dX.clone(), 1); }); // lda too small
        assertThrows(IllegalArgumentException.class, () -> { blas.dtbsv("U", "N", "N", M, KU, dtbAU, KU + 1, dX.clone(), 0); }); // incx == 0
    }
}
