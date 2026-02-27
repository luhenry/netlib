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

public class StgsenTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;

        float[] a = generateUpperTriangularMatrixFloat(n, 1.0f, 1.0f, 0.5f);

        float[] b = generateUpperTriangularMatrixFloat(n, n + 1.0f, 1.0f, 0.3f);

        boolean[] select = new boolean[n];
        for (int i = 0; i < 3; i++) {
            select[i] = true;
        }

        float[] a_expected = a.clone();
        float[] a_actual = a.clone();
        float[] b_expected = b.clone();
        float[] b_actual = b.clone();
        float[] alphar_expected = new float[n];
        float[] alphar_actual = new float[n];
        float[] alphai_expected = new float[n];
        float[] alphai_actual = new float[n];
        float[] beta_expected = new float[n];
        float[] beta_actual = new float[n];
        float[] q_expected = new float[1];
        float[] q_actual = new float[1];
        float[] z_expected = new float[1];
        float[] z_actual = new float[1];
        intW m_expected = new intW(0);
        intW m_actual = new intW(0);
        floatW pl_expected = new floatW(0.0f);
        floatW pl_actual = new floatW(0.0f);
        floatW pr_expected = new floatW(0.0f);
        floatW pr_actual = new floatW(0.0f);
        float[] dif_expected = new float[2];
        float[] dif_actual = new float[2];
        int lwork = 4 * n + 16;
        float[] work_expected = new float[lwork];
        float[] work_actual = new float[lwork];
        int liwork = 1;
        int[] iwork_expected = new int[liwork];
        int[] iwork_actual = new int[liwork];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.stgsen(0, false, false, select, n, a_expected, n, b_expected, n, alphar_expected, alphai_expected, beta_expected, q_expected, 1, z_expected, 1, m_expected, pl_expected, pr_expected, dif_expected, work_expected, lwork, iwork_expected, liwork, info_expected);
        assertEquals(0, info_expected.val);

        lapack.stgsen(0, false, false, select, n, a_actual, n, b_actual, n, alphar_actual, alphai_actual, beta_actual, q_actual, 1, z_actual, 1, m_actual, pl_actual, pr_actual, dif_actual, work_actual, lwork, iwork_actual, liwork, info_actual);
        assertEquals(0, info_actual.val);

        assertEquals(m_expected.val, m_actual.val);
        assertArrayEquals(alphar_expected, alphar_actual, sepsilon);
        assertArrayEquals(alphai_expected, alphai_actual, sepsilon);
        assertArrayEquals(beta_expected, beta_actual, sepsilon);
        assertArrayEquals(a_expected, a_actual, sepsilon);
        assertArrayEquals(b_expected, b_actual, sepsilon);
    }
}
