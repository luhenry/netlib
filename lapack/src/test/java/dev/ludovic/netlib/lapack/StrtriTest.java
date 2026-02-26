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

public class StrtriTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Create an upper triangular matrix
        float[] a_expected = new float[N * N];
        float[] a_actual = new float[N * N];
        for (int j = 0; j < N; j++) {
            for (int i = 0; i <= j; i++) {
                float value = (i == j) ? (N + 1.0f) : (1.0f / (i + j + 2.0f));
                a_expected[i + j * N] = value;
                a_actual[i + j * N] = value;
            }
        }

        // Invert using reference implementation
        intW info_expected = new intW(0);
        f2j.strtri("U", "N", N, a_expected, 0, N, info_expected);
        assertEquals(0, info_expected.val, "Reference inversion should succeed");

        // Invert using test implementation
        intW info_actual = new intW(0);
        lapack.strtri("U", "N", N, a_actual, 0, N, info_actual);
        assertEquals(0, info_actual.val, "Inversion should succeed");

        // Compare inverted matrices
        assertArrayEquals(a_expected, a_actual, Math.scalb(sepsilon, Math.getExponent(getMaxValue(a_expected))));
    }
}
