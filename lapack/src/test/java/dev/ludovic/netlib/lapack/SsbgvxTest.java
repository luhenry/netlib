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

public class SsbgvxTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testAllEigenvalues(LAPACK lapack) {
        int n = N_SMALL;
        int ka = 2;
        int kb = 1;
        int ldab = ka + 1;
        int ldbb = kb + 1;

        float[] ab = generateBandedSymmetricMatrixFloat(n, ka, n + 10.0f, 0.5f);

        float[] bb = generateBandedSymmetricMatrixFloat(n, kb, n + 5.0f, 0.3f);

        float[] ab_expected = ab.clone();
        float[] ab_actual = ab.clone();
        float[] bb_expected = bb.clone();
        float[] bb_actual = bb.clone();
        float[] q_expected = new float[n * n];
        float[] q_actual = new float[n * n];
        intW m_expected = new intW(0);
        intW m_actual = new intW(0);
        float[] w_expected = new float[n];
        float[] w_actual = new float[n];
        float[] z_expected = new float[1];
        float[] z_actual = new float[1];
        float[] work_expected = new float[7 * n];
        float[] work_actual = new float[7 * n];
        int[] iwork_expected = new int[5 * n];
        int[] iwork_actual = new int[5 * n];
        int[] ifail_expected = new int[n];
        int[] ifail_actual = new int[n];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.ssbgvx("N", "A", "U", n, ka, kb, ab_expected, ldab, bb_expected, ldbb, q_expected, n, 0.0f, 0.0f, 0, 0, 0.0f, m_expected, w_expected, z_expected, 1, work_expected, iwork_expected, ifail_expected, info_expected);
        assertEquals(0, info_expected.val);
        assertEquals(n, m_expected.val);

        lapack.ssbgvx("N", "A", "U", n, ka, kb, ab_actual, ldab, bb_actual, ldbb, q_actual, n, 0.0f, 0.0f, 0, 0, 0.0f, m_actual, w_actual, z_actual, 1, work_actual, iwork_actual, ifail_actual, info_actual);
        assertEquals(0, info_actual.val);
        assertEquals(n, m_actual.val);

        assertArrayEquals(w_expected, w_actual, sepsilon);
    }
}
