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

public class StrsylTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;

        // A is m x m upper triangular (special case of quasi-triangular)
        float[] a = generateUpperTriangularMatrixFloat(m, 1.0f, 1.0f, 0.5f);

        // B is n x n upper triangular with distinct diagonal entries
        float[] b = generateUpperTriangularMatrixFloat(n, m + 1.0f, 1.0f, 0.3f);

        // C is m x n general matrix (right-hand side, overwritten with solution)
        float[] c = new float[m * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                c[i + j * m] = 1.0f / (i + j + 1.0f);
            }
        }

        float[] c_expected = c.clone();
        float[] c_actual = c.clone();
        floatW scale_expected = new floatW(0.0f);
        floatW scale_actual = new floatW(0.0f);
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.strsyl("N", "N", 1, m, n, a, m, b, n, c_expected, m, scale_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.strsyl("N", "N", 1, m, n, a, m, b, n, c_actual, m, scale_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertEquals(scale_expected.val, scale_actual.val, sepsilon);
        assertArrayEquals(c_expected, c_actual, sepsilon);
    }
}
