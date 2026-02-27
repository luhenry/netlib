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

public class StpsvTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        float[] expected, sXcopy;

        // uplo=U, trans=N, diag=N
        f2j.stpsv("U", "N", "N", M, sgeAU, expected = sX.clone(), 1);
        blas.stpsv("U", "N", "N", M, sgeAU, sXcopy = sX.clone(), 1);
        assertRelArrayEquals(expected, sXcopy, ssolveEpsilon);

        // uplo=L, trans=N, diag=N
        f2j.stpsv("L", "N", "N", M, sgeAL, expected = sX.clone(), 1);
        blas.stpsv("L", "N", "N", M, sgeAL, sXcopy = sX.clone(), 1);
        assertRelArrayEquals(expected, sXcopy, ssolveEpsilon);

        // uplo=U, trans=T, diag=N
        f2j.stpsv("U", "T", "N", M, sgeAU, expected = sX.clone(), 1);
        blas.stpsv("U", "T", "N", M, sgeAU, sXcopy = sX.clone(), 1);
        assertRelArrayEquals(expected, sXcopy, ssolveEpsilon);

        // uplo=L, trans=T, diag=U
        f2j.stpsv("L", "T", "U", M, sgeAL, expected = sX.clone(), 1);
        blas.stpsv("L", "T", "U", M, sgeAL, sXcopy = sX.clone(), 1);
        assertRelArrayEquals(expected, sXcopy, ssolveEpsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAllUploTransCombinations(BLAS blas) {
        float[] expected, sXcopy;

        // uplo=L, trans=T, diag=N
        f2j.stpsv("L", "T", "N", M, sgeAL, expected = sX.clone(), 1);
        blas.stpsv("L", "T", "N", M, sgeAL, sXcopy = sX.clone(), 1);
        assertRelArrayEquals(expected, sXcopy, ssolveEpsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testUnitDiagonal(BLAS blas) {
        float[] expected, sXcopy;

        // uplo=U, trans=N, diag=U
        f2j.stpsv("U", "N", "U", M, sgeAU, expected = sX.clone(), 1);
        blas.stpsv("U", "N", "U", M, sgeAU, sXcopy = sX.clone(), 1);
        assertRelArrayEquals(expected, sXcopy, ssolveEpsilon);

        // uplo=L, trans=N, diag=U
        f2j.stpsv("L", "N", "U", M, sgeAL, expected = sX.clone(), 1);
        blas.stpsv("L", "N", "U", M, sgeAL, sXcopy = sX.clone(), 1);
        assertRelArrayEquals(expected, sXcopy, ssolveEpsilon);

        // uplo=U, trans=T, diag=U
        f2j.stpsv("U", "T", "U", M, sgeAU, expected = sX.clone(), 1);
        blas.stpsv("U", "T", "U", M, sgeAU, sXcopy = sX.clone(), 1);
        assertRelArrayEquals(expected, sXcopy, ssolveEpsilon);

        // uplo=L, trans=T, diag=U (already in sanity, but included for completeness)
        f2j.stpsv("L", "T", "U", M, sgeAL, expected = sX.clone(), 1);
        blas.stpsv("L", "T", "U", M, sgeAL, sXcopy = sX.clone(), 1);
        assertRelArrayEquals(expected, sXcopy, ssolveEpsilon);
    }
}
