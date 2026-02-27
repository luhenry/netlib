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

package dev.ludovic.netlib.lapack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;
import org.netlib.util.*;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class SpotrsTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // First, factor the matrix using spotrf
        float[] a_expected = sPositiveDefiniteMatrix.clone();
        float[] a_actual = sPositiveDefiniteMatrix.clone();

        intW info = new intW(0);
        f2j.spotrf("U", N, a_expected, 0, N, info);
        assertEquals(0, info.val, "Reference factorization should succeed");

        info.val = 0;
        lapack.spotrf("U", N, a_actual, 0, N, info);
        assertEquals(0, info.val, "Factorization should succeed");

        // Create right-hand side B
        float[] b_expected = generateFloatArray(N, 1.0f);
        float[] b_actual = b_expected.clone();

        // Solve using reference implementation
        info.val = 0;
        f2j.spotrs("U", N, 1, a_expected, 0, N, b_expected, 0, N, info);
        assertEquals(0, info.val, "Reference solve should succeed");

        // Solve using test implementation
        info.val = 0;
        lapack.spotrs("U", N, 1, a_actual, 0, N, b_actual, 0, N, info);
        assertEquals(0, info.val, "Solve should succeed");

        // Compare solutions
        assertArrayEquals(b_expected, b_actual, Math.scalb(sepsilon, Math.getExponent(getMaxValue(b_expected))));
    }
}
