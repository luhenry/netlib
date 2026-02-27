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

public class SsymmTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        float[] expected, sgeCcopy;

        f2j.ssymm("L", "U", M, N, 1.0f, ssyA, M, sgeB, M, 2.0f, expected = sgeC.clone(), M);
        blas.ssymm("L", "U", M, N, 1.0f, ssyA, M, sgeB, M, 2.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("L", "L", M, N, 1.0f, ssyA, M, sgeB, M, 2.0f, expected = sgeC.clone(), M);
        blas.ssymm("L", "L", M, N, 1.0f, ssyA, M, sgeB, M, 2.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("R", "U", M, N, 1.0f, ssyA, N, sgeB, M, 2.0f, expected = sgeC.clone(), M);
        blas.ssymm("R", "U", M, N, 1.0f, ssyA, N, sgeB, M, 2.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("R", "L", M, N, 1.0f, ssyA, N, sgeB, M, 2.0f, expected = sgeC.clone(), M);
        blas.ssymm("R", "L", M, N, 1.0f, ssyA, N, sgeB, M, 2.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("L", "U", M, N, 1.0f, ssyA, M, sgeB, M, 0.0f, expected = sgeC.clone(), M);
        blas.ssymm("L", "U", M, N, 1.0f, ssyA, M, sgeB, M, 0.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("L", "L", M, N, 1.0f, ssyA, M, sgeB, M, 0.0f, expected = sgeC.clone(), M);
        blas.ssymm("L", "L", M, N, 1.0f, ssyA, M, sgeB, M, 0.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("R", "U", M, N, 1.0f, ssyA, N, sgeB, M, 0.0f, expected = sgeC.clone(), M);
        blas.ssymm("R", "U", M, N, 1.0f, ssyA, N, sgeB, M, 0.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("R", "L", M, N, 1.0f, ssyA, N, sgeB, M, 0.0f, expected = sgeC.clone(), M);
        blas.ssymm("R", "L", M, N, 1.0f, ssyA, N, sgeB, M, 0.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("L", "U", M, N, 0.0f, ssyA, M, sgeB, M, 1.0f, expected = sgeC.clone(), M);
        blas.ssymm("L", "U", M, N, 0.0f, ssyA, M, sgeB, M, 1.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("L", "L", M, N, 0.0f, ssyA, M, sgeB, M, 1.0f, expected = sgeC.clone(), M);
        blas.ssymm("L", "L", M, N, 0.0f, ssyA, M, sgeB, M, 1.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("R", "U", M, N, 0.0f, ssyA, N, sgeB, M, 1.0f, expected = sgeC.clone(), M);
        blas.ssymm("R", "U", M, N, 0.0f, ssyA, N, sgeB, M, 1.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("R", "L", M, N, 0.0f, ssyA, N, sgeB, M, 1.0f, expected = sgeC.clone(), M);
        blas.ssymm("R", "L", M, N, 0.0f, ssyA, N, sgeB, M, 1.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaZeroBetaZero(BLAS blas) {
        float[] expected, sgeCcopy;

        // alpha=0.0f, beta=0.0f: C should be zeroed out
        f2j.ssymm("L", "U", M, N, 0.0f, ssyA, M, sgeB, M, 0.0f, expected = sgeC.clone(), M);
        blas.ssymm("L", "U", M, N, 0.0f, ssyA, M, sgeB, M, 0.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("R", "L", M, N, 0.0f, ssyA, N, sgeB, M, 0.0f, expected = sgeC.clone(), M);
        blas.ssymm("R", "L", M, N, 0.0f, ssyA, N, sgeB, M, 0.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaZeroBetaNonZero(BLAS blas) {
        float[] expected, sgeCcopy;

        // alpha=0.0f, beta=2.0f: C := beta*C (exercises beta != 0.0 branch in alpha==0 path)
        f2j.ssymm("L", "U", M, N, 0.0f, ssyA, M, sgeB, M, 2.0f, expected = sgeC.clone(), M);
        blas.ssymm("L", "U", M, N, 0.0f, ssyA, M, sgeB, M, 2.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("R", "L", M, N, 0.0f, ssyA, N, sgeB, M, 2.0f, expected = sgeC.clone(), M);
        blas.ssymm("R", "L", M, N, 0.0f, ssyA, N, sgeB, M, 2.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAlphaTwoBetaHalf(BLAS blas) {
        float[] expected, sgeCcopy;

        // alpha=2.0f, beta=0.5f
        // Use relative tolerance since alpha=2 amplifies values, making absolute sepsilon too tight
        f2j.ssymm("L", "U", M, N, 2.0f, ssyA, M, sgeB, M, 0.5f, expected = sgeC.clone(), M);
        blas.ssymm("L", "U", M, N, 2.0f, ssyA, M, sgeB, M, 0.5f, sgeCcopy = sgeC.clone(), M);
        assertRelArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("L", "L", M, N, 2.0f, ssyA, M, sgeB, M, 0.5f, expected = sgeC.clone(), M);
        blas.ssymm("L", "L", M, N, 2.0f, ssyA, M, sgeB, M, 0.5f, sgeCcopy = sgeC.clone(), M);
        assertRelArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("R", "U", M, N, 2.0f, ssyA, N, sgeB, M, 0.5f, expected = sgeC.clone(), M);
        blas.ssymm("R", "U", M, N, 2.0f, ssyA, N, sgeB, M, 0.5f, sgeCcopy = sgeC.clone(), M);
        assertRelArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("R", "L", M, N, 2.0f, ssyA, N, sgeB, M, 0.5f, expected = sgeC.clone(), M);
        blas.ssymm("R", "L", M, N, 2.0f, ssyA, N, sgeB, M, 0.5f, sgeCcopy = sgeC.clone(), M);
        assertRelArrayEquals(expected, sgeCcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testAllSideUploCombinations(BLAS blas) {
        float[] expected, sgeCcopy;

        // All 4 SIDE x UPLO combos with non-trivial alpha/beta
        f2j.ssymm("L", "U", M, N, 1.5f, ssyA, M, sgeB, M, 0.75f, expected = sgeC.clone(), M);
        blas.ssymm("L", "U", M, N, 1.5f, ssyA, M, sgeB, M, 0.75f, sgeCcopy = sgeC.clone(), M);
        assertRelArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("L", "L", M, N, 1.5f, ssyA, M, sgeB, M, 0.75f, expected = sgeC.clone(), M);
        blas.ssymm("L", "L", M, N, 1.5f, ssyA, M, sgeB, M, 0.75f, sgeCcopy = sgeC.clone(), M);
        assertRelArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("R", "U", M, N, 1.5f, ssyA, N, sgeB, M, 0.75f, expected = sgeC.clone(), M);
        blas.ssymm("R", "U", M, N, 1.5f, ssyA, N, sgeB, M, 0.75f, sgeCcopy = sgeC.clone(), M);
        assertRelArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("R", "L", M, N, 1.5f, ssyA, N, sgeB, M, 0.75f, expected = sgeC.clone(), M);
        blas.ssymm("R", "L", M, N, 1.5f, ssyA, N, sgeB, M, 0.75f, sgeCcopy = sgeC.clone(), M);
        assertRelArrayEquals(expected, sgeCcopy, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testInvalidSideOrUplo(BLAS blas) {
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.ssymm("X", "U", M, N, 1.0f, ssyA, M, sgeB, M, 2.0f, sgeC.clone(), M);
        });
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            blas.ssymm("L", "X", M, N, 1.0f, ssyA, M, sgeB, M, 2.0f, sgeC.clone(), M);
        });
        // negative m
        assertThrows(IllegalArgumentException.class, () -> {
            blas.ssymm("L", "U", -1, N, 1.0f, ssyA, M, sgeB, M, 1.0f, sgeC.clone(), M);
        });
        // negative n
        assertThrows(IllegalArgumentException.class, () -> {
            blas.ssymm("L", "U", M, -1, 1.0f, ssyA, M, sgeB, M, 1.0f, sgeC.clone(), M);
        });
        // lda too small
        assertThrows(IllegalArgumentException.class, () -> {
            blas.ssymm("L", "U", M, N, 1.0f, ssyA, M - 1, sgeB, M, 1.0f, sgeC.clone(), M);
        });
        // ldb too small
        assertThrows(IllegalArgumentException.class, () -> {
            blas.ssymm("L", "U", M, N, 1.0f, ssyA, M, sgeB, M - 1, 1.0f, sgeC.clone(), M);
        });
        // ldc too small
        assertThrows(IllegalArgumentException.class, () -> {
            blas.ssymm("L", "U", M, N, 1.0f, ssyA, M, sgeB, M, 1.0f, sgeC.clone(), M - 1);
        });
    }
}
