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

public class DhseinTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        // Build an upper Hessenberg matrix via dgehrd
        double[] a = generateMatrix(n, n, 1.0);
        double[] tau = new double[n - 1];
        int lwork_hrd = n * n;
        double[] work_hrd = new double[lwork_hrd];
        intW info = new intW(0);
        f2j.dgehrd(n, 1, n, a, 0, n, tau, 0, work_hrd, 0, lwork_hrd, info);
        assertEquals(0, info.val);

        // Zero out below sub-diagonal
        for (int j = 0; j < n - 2; j++) {
            for (int i = j + 2; i < n; i++) {
                a[i + j * n] = 0.0;
            }
        }

        // Get eigenvalues from dhseqr (eigenvalues only)
        double[] h_copy = a.clone();
        double[] wr = new double[n];
        double[] wi = new double[n];
        double[] work_hseqr = new double[n * n];
        f2j.dhseqr("E", "N", n, 1, n, h_copy, 0, n, wr, 0, wi, 0,
                    new double[1], 0, 1, work_hseqr, 0, n * n, info);
        assertEquals(0, info.val, "dhseqr should succeed");

        // Select all eigenvalues
        boolean[] select = new boolean[n];
        int mm = n;
        for (int i = 0; i < n; i++) {
            select[i] = true;
        }

        double[] vr_expected = new double[n * n];
        double[] vr_actual = new double[n * n];
        double[] wr_expected = wr.clone();
        double[] wr_actual = wr.clone();
        double[] wi_expected = wi.clone();
        double[] wi_actual = wi.clone();
        intW m_expected = new intW(0);
        intW m_actual = new intW(0);
        double[] work_expected = new double[(n + 2) * n];
        double[] work_actual = new double[(n + 2) * n];
        int[] ifaill = new int[mm];
        int[] ifailr_expected = new int[mm];
        int[] ifailr_actual = new int[mm];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dhsein("R", "Q", "N", select, 0, n, a.clone(), 0, n,
                    wr_expected, 0, wi_expected, 0,
                    new double[1], 0, 1, vr_expected, 0, n,
                    mm, m_expected, work_expected, 0,
                    ifaill, 0, ifailr_expected, 0, info_expected);
        assertEquals(0, info_expected.val, "Reference dhsein should succeed");

        lapack.dhsein("R", "Q", "N", select, 0, n, a.clone(), 0, n,
                      wr_actual, 0, wi_actual, 0,
                      new double[1], 0, 1, vr_actual, 0, n,
                      mm, m_actual, work_actual, 0,
                      ifaill, 0, ifailr_actual, 0, info_actual);
        assertEquals(0, info_actual.val, "dhsein should succeed");

        assertEquals(m_expected.val, m_actual.val);
    }
}
