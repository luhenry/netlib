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

public class SsbgvdTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testEigenvaluesOnly(LAPACK lapack) {
        int n = N_SMALL;
        int ka = 2;
        int kb = 1;
        int ldab = ka + 1;
        int ldbb = kb + 1;

        float[] ab = generateBandedSymmetricMatrixFloat(n, ka, n + 10.0f, 0.5f);

        float[] bb = generateBandedSymmetricMatrixFloat(n, kb, n + 5.0f, 0.3f);

        int lwork = Math.max(1, 1 + 5 * n + 2 * n * n);
        int liwork = Math.max(1, 3 + 5 * n);

        float[] ab_expected = ab.clone();
        float[] ab_actual = ab.clone();
        float[] bb_expected = bb.clone();
        float[] bb_actual = bb.clone();
        float[] w_expected = new float[n];
        float[] w_actual = new float[n];
        float[] z_expected = new float[1];
        float[] z_actual = new float[1];
        float[] work_expected = new float[lwork];
        float[] work_actual = new float[lwork];
        int[] iwork_expected = new int[liwork];
        int[] iwork_actual = new int[liwork];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.ssbgvd("N", "U", n, ka, kb, ab_expected, ldab, bb_expected, ldbb, w_expected, z_expected, 1, work_expected, lwork, iwork_expected, liwork, info_expected);
        assertEquals(0, info_expected.val);

        lapack.ssbgvd("N", "U", n, ka, kb, ab_actual, ldab, bb_actual, ldbb, w_actual, z_actual, 1, work_actual, lwork, iwork_actual, liwork, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(w_expected, w_actual, sepsilon);
    }
}
