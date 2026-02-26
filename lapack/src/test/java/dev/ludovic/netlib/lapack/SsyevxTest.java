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

public class SsyevxTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        float[] a_expected = new float[n * n];
        float[] a_actual = new float[n * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                a_expected[i + j * n] = sSymmetricMatrix[i + j * N];
                a_actual[i + j * n] = sSymmetricMatrix[i + j * N];
            }
        }

        float[] w_expected = new float[n];
        float[] w_actual = new float[n];
        float[] z_expected = new float[n * n];
        float[] z_actual = new float[n * n];
        int[] ifail_expected = new int[n];
        int[] ifail_actual = new int[n];
        intW m_expected = new intW(0);
        intW m_actual = new intW(0);
        int lwork = 8 * n;
        float[] work_expected = new float[lwork];
        float[] work_actual = new float[lwork];
        int[] iwork_expected = new int[5 * n];
        int[] iwork_actual = new int[5 * n];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.ssyevx("N", "A", "U", n, a_expected, 0, n, 0.0f, 0.0f, 0, 0, 0.0f,
                    m_expected, w_expected, 0, z_expected, 0, n, work_expected, 0, lwork,
                    iwork_expected, 0, ifail_expected, 0, info_expected);
        assertEquals(0, info_expected.val, "Reference ssyevx should succeed");

        lapack.ssyevx("N", "A", "U", n, a_actual, 0, n, 0.0f, 0.0f, 0, 0, 0.0f,
                      m_actual, w_actual, 0, z_actual, 0, n, work_actual, 0, lwork,
                      iwork_actual, 0, ifail_actual, 0, info_actual);
        assertEquals(0, info_actual.val, "ssyevx should succeed");

        assertEquals(m_expected.val, m_actual.val);
        assertArrayEquals(w_expected, w_actual, sepsilon);
    }
}
