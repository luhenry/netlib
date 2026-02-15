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

public class DtpmvTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        double[] expected, dXcopy;

        // uplo=U, trans=N, diag=N
        f2j.dtpmv("U", "N", "N", M, dgeAU, expected = dX.clone(), 1);
        blas.dtpmv("U", "N", "N", M, dgeAU, dXcopy = dX.clone(), 1);
        assertArrayEquals(expected, dXcopy, depsilon);

        // uplo=L, trans=N, diag=N
        f2j.dtpmv("L", "N", "N", M, dgeAL, expected = dX.clone(), 1);
        blas.dtpmv("L", "N", "N", M, dgeAL, dXcopy = dX.clone(), 1);
        assertArrayEquals(expected, dXcopy, depsilon);

        // uplo=U, trans=T, diag=N
        f2j.dtpmv("U", "T", "N", M, dgeAU, expected = dX.clone(), 1);
        blas.dtpmv("U", "T", "N", M, dgeAU, dXcopy = dX.clone(), 1);
        assertArrayEquals(expected, dXcopy, depsilon);

        // uplo=L, trans=T, diag=U
        f2j.dtpmv("L", "T", "U", M, dgeAL, expected = dX.clone(), 1);
        blas.dtpmv("L", "T", "U", M, dgeAL, dXcopy = dX.clone(), 1);
        assertArrayEquals(expected, dXcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAllUploTransCombinations(BLAS blas) {
        double[] expected, dXcopy;

        // uplo=L, trans=T, diag=N
        f2j.dtpmv("L", "T", "N", M, dgeAL, expected = dX.clone(), 1);
        blas.dtpmv("L", "T", "N", M, dgeAL, dXcopy = dX.clone(), 1);
        assertArrayEquals(expected, dXcopy, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testUnitDiagonal(BLAS blas) {
        double[] expected, dXcopy;

        // uplo=U, trans=N, diag=U
        f2j.dtpmv("U", "N", "U", M, dgeAU, expected = dX.clone(), 1);
        blas.dtpmv("U", "N", "U", M, dgeAU, dXcopy = dX.clone(), 1);
        assertArrayEquals(expected, dXcopy, depsilon);

        // uplo=L, trans=N, diag=U
        f2j.dtpmv("L", "N", "U", M, dgeAL, expected = dX.clone(), 1);
        blas.dtpmv("L", "N", "U", M, dgeAL, dXcopy = dX.clone(), 1);
        assertArrayEquals(expected, dXcopy, depsilon);

        // uplo=U, trans=T, diag=U
        f2j.dtpmv("U", "T", "U", M, dgeAU, expected = dX.clone(), 1);
        blas.dtpmv("U", "T", "U", M, dgeAU, dXcopy = dX.clone(), 1);
        assertArrayEquals(expected, dXcopy, depsilon);

        // uplo=L, trans=T, diag=U (already in sanity, but included for completeness)
        f2j.dtpmv("L", "T", "U", M, dgeAL, expected = dX.clone(), 1);
        blas.dtpmv("L", "T", "U", M, dgeAL, dXcopy = dX.clone(), 1);
        assertArrayEquals(expected, dXcopy, depsilon);
    }
}
