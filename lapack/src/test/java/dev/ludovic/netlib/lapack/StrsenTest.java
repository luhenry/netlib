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

public class StrsenTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;

        // Upper triangular matrix in Schur form with distinct eigenvalues
        float[] t = generateUpperTriangularMatrixFloat(n, 1.0f, 1.0f, 0.5f);

        // Select first 3 eigenvalues
        boolean[] select = new boolean[n];
        for (int i = 0; i < 3; i++) {
            select[i] = true;
        }

        float[] t_expected = t.clone();
        float[] t_actual = t.clone();
        float[] q_expected = new float[1];
        float[] q_actual = new float[1];
        float[] wr_expected = new float[n];
        float[] wr_actual = new float[n];
        float[] wi_expected = new float[n];
        float[] wi_actual = new float[n];
        intW m_expected = new intW(0);
        intW m_actual = new intW(0);
        floatW s_expected = new floatW(0.0f);
        floatW s_actual = new floatW(0.0f);
        floatW sep_expected = new floatW(0.0f);
        floatW sep_actual = new floatW(0.0f);
        int lwork = Math.max(1, n);
        float[] work_expected = new float[lwork];
        float[] work_actual = new float[lwork];
        int liwork = 1;
        int[] iwork_expected = new int[liwork];
        int[] iwork_actual = new int[liwork];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.strsen("N", "N", select, n, t_expected, n, q_expected, 1, wr_expected, wi_expected, m_expected, s_expected, sep_expected, work_expected, lwork, iwork_expected, liwork, info_expected);
        assertEquals(0, info_expected.val);

        lapack.strsen("N", "N", select, n, t_actual, n, q_actual, 1, wr_actual, wi_actual, m_actual, s_actual, sep_actual, work_actual, lwork, iwork_actual, liwork, info_actual);
        assertEquals(0, info_actual.val);

        assertEquals(m_expected.val, m_actual.val);
        assertArrayEquals(wr_expected, wr_actual, sepsilon);
        assertArrayEquals(wi_expected, wi_actual, sepsilon);
        assertArrayEquals(t_expected, t_actual, sepsilon);
    }
}
