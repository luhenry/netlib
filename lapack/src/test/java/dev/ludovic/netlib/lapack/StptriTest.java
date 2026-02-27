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

public class StptriTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Create an upper triangular matrix in packed format
        int ap_size = N * (N + 1) / 2;
        float[] ap_expected = new float[ap_size];
        float[] ap_actual = new float[ap_size];

        int idx = 0;
        for (int j = 0; j < N; j++) {
            for (int i = 0; i <= j; i++) {
                float value = (i == j) ? (N + 1.0f) : (1.0f / (i + j + 2.0f));
                ap_expected[idx] = value;
                ap_actual[idx] = value;
                idx++;
            }
        }

        // Invert using reference implementation
        intW info_expected = new intW(0);
        f2j.stptri("U", "N", N, ap_expected, 0, info_expected);
        assertEquals(0, info_expected.val, "Reference inversion should succeed");

        // Invert using test implementation
        intW info_actual = new intW(0);
        lapack.stptri("U", "N", N, ap_actual, 0, info_actual);
        assertEquals(0, info_actual.val, "Inversion should succeed");

        // Compare inverted matrices
        assertArrayEquals(ap_expected, ap_actual, Math.scalb(sepsilon, Math.getExponent(getMaxValue(ap_expected))));
    }
}
