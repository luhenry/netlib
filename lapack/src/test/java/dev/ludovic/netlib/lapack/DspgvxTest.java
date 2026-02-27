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

public class DspgvxTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testAllEigenvalues(LAPACK lapack) {
        int n = N_SMALL;
        int ap_len = n * (n + 1) / 2;

        double[] ap = generatePackedSymmetricMatrix(n, n + 10.0);

        double[] bp = new double[ap_len];
        int k = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                bp[k++] = (i == j) ? n + 5.0 : 0.3 / (i + j + 1.0);
            }
        }

        double[] ap_expected = ap.clone();
        double[] ap_actual = ap.clone();
        double[] bp_expected = bp.clone();
        double[] bp_actual = bp.clone();
        intW m_expected = new intW(0);
        intW m_actual = new intW(0);
        double[] w_expected = new double[n];
        double[] w_actual = new double[n];
        double[] z_expected = new double[1];
        double[] z_actual = new double[1];
        double[] work_expected = new double[8 * n];
        double[] work_actual = new double[8 * n];
        int[] iwork_expected = new int[5 * n];
        int[] iwork_actual = new int[5 * n];
        int[] ifail_expected = new int[n];
        int[] ifail_actual = new int[n];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dspgvx(1, "N", "A", "U", n, ap_expected, bp_expected, 0.0, 0.0, 0, 0, 0.0, m_expected, w_expected, z_expected, 1, work_expected, iwork_expected, ifail_expected, info_expected);
        assertEquals(0, info_expected.val);
        assertEquals(n, m_expected.val);

        lapack.dspgvx(1, "N", "A", "U", n, ap_actual, bp_actual, 0.0, 0.0, 0, 0, 0.0, m_actual, w_actual, z_actual, 1, work_actual, iwork_actual, ifail_actual, info_actual);
        assertEquals(0, info_actual.val);
        assertEquals(n, m_actual.val);

        assertArrayEquals(w_expected, w_actual, 1e-13);
    }
}
