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

public class Dlasd1Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // dlasd1 merges two subproblems in D&C SVD
        // Use a small case: nl=3, nr=3, sqre=0 -> n=nl+nr+1=7
        int nl = 3;
        int nr = 3;
        int sqre = 0;
        int n = nl + nr + 1;
        int m = n + sqre;

        // D(1..NL): singular values of left sub-block (ascending)
        // D(NL+1): placeholder (set to 0 by dlasd1)
        // D(NL+2..N): singular values of right sub-block (ascending)
        double[] d_expected = new double[n];
        d_expected[0] = 1.0; d_expected[1] = 2.0; d_expected[2] = 3.0;
        d_expected[3] = 0.0;
        d_expected[4] = 1.5; d_expected[5] = 2.5; d_expected[6] = 3.5;

        doubleW alpha_expected = new doubleW(1.5);
        doubleW beta_expected = new doubleW(0.8);

        // U: block-diagonal identity for left and right singular vectors
        double[] u_expected = generateIdentityMatrix(n);

        // VT: block-diagonal identity for right singular vectors (transposed)
        double[] vt_expected = generateIdentityMatrix(m);

        // IDXQ: sort permutations for each sub-block (1-based, LOCAL indices)
        // Left block IDXQ(1..NL): identity since D(1..NL) is already ascending
        // Right block IDXQ(NL+2..N): LOCAL indices {1,2,3} since D(NL+2..N) is ascending
        int[] idxq_expected = new int[n];
        for (int i = 0; i < nl; i++) {
            idxq_expected[i] = i + 1;
        }
        for (int i = 0; i < nr; i++) {
            idxq_expected[nl + 1 + i] = i + 1;
        }

        int[] iwork_expected = new int[4 * n];
        double[] work_expected = new double[3 * m * m + 2 * m];
        intW info_expected = new intW(0);

        f2j.dlasd1(nl, nr, sqre, d_expected, 0, alpha_expected, beta_expected,
            u_expected, 0, n, vt_expected, 0, m, idxq_expected, 0,
            iwork_expected, 0, work_expected, 0, info_expected);

        double[] d_actual = new double[n];
        d_actual[0] = 1.0; d_actual[1] = 2.0; d_actual[2] = 3.0;
        d_actual[3] = 0.0;
        d_actual[4] = 1.5; d_actual[5] = 2.5; d_actual[6] = 3.5;

        doubleW alpha_actual = new doubleW(1.5);
        doubleW beta_actual = new doubleW(0.8);

        double[] u_actual = generateIdentityMatrix(n);

        double[] vt_actual = generateIdentityMatrix(m);

        int[] idxq_actual = new int[n];
        for (int i = 0; i < nl; i++) {
            idxq_actual[i] = i + 1;
        }
        for (int i = 0; i < nr; i++) {
            idxq_actual[nl + 1 + i] = i + 1;
        }

        int[] iwork_actual = new int[4 * n];
        double[] work_actual = new double[3 * m * m + 2 * m];
        intW info_actual = new intW(0);

        lapack.dlasd1(nl, nr, sqre, d_actual, 0, alpha_actual, beta_actual,
            u_actual, 0, n, vt_actual, 0, m, idxq_actual, 0,
            iwork_actual, 0, work_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(d_expected, d_actual, Math.scalb(depsilon, Math.getExponent(getMaxValue(d_expected))));
        assertArrayEquals(u_expected, u_actual, Math.scalb(depsilon, Math.getExponent(Math.max(getMaxValue(u_expected), 1.0))));
        assertArrayEquals(vt_expected, vt_actual, Math.scalb(depsilon, Math.getExponent(Math.max(getMaxValue(vt_expected), 1.0))));
    }
}
