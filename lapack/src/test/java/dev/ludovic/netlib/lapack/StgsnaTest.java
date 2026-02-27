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

public class StgsnaTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;

        // A is upper triangular (generalized Schur form)
        float[] a = generateUpperTriangularMatrixFloat(n, 1.0f, 1.0f, 0.5f);

        // B is upper triangular (generalized Schur form)
        float[] b = generateUpperTriangularMatrixFloat(n, n + 1.0f, 1.0f, 0.3f);

        // JOB='V': compute separation (eigenvector condition) only
        // HOWMNY='A': compute for all eigenvalues
        boolean[] select = new boolean[n];
        float[] vl = new float[1];
        float[] vr = new float[1];
        float[] s_expected = new float[n];
        float[] s_actual = new float[n];
        float[] dif_expected = new float[n];
        float[] dif_actual = new float[n];
        intW m_expected = new intW(0);
        intW m_actual = new intW(0);
        int lwork = 2 * n * (n + 2) + 16;
        float[] work_expected = new float[lwork];
        float[] work_actual = new float[lwork];
        int[] iwork_expected = new int[n + 6];
        int[] iwork_actual = new int[n + 6];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.stgsna("V", "A", select, n, a, n, b, n, vl, 1, vr, 1, s_expected, dif_expected, n, m_expected, work_expected, lwork, iwork_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.stgsna("V", "A", select, n, a, n, b, n, vl, 1, vr, 1, s_actual, dif_actual, n, m_actual, work_actual, lwork, iwork_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertEquals(m_expected.val, m_actual.val);
        assertArrayEquals(dif_expected, dif_actual, sepsilon);
    }
}
