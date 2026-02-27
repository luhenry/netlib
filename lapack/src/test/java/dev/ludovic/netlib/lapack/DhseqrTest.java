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

public class DhseqrTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        // First reduce to upper Hessenberg form using dgehrd
        double[] a = generateMatrix(n, n, 1.0);
        double[] tau = new double[n - 1];
        int lwork_hrd = n * n;
        double[] work_hrd = new double[lwork_hrd];
        intW info = new intW(0);

        f2j.dgehrd(n, 1, n, a, 0, n, tau, 0, work_hrd, 0, lwork_hrd, info);
        assertEquals(0, info.val, "dgehrd should succeed");

        // Zero out below sub-diagonal to get proper Hessenberg
        for (int j = 0; j < n - 2; j++) {
            for (int i = j + 2; i < n; i++) {
                a[i + j * n] = 0.0;
            }
        }

        double[] h_expected = a.clone();
        double[] h_actual = a.clone();
        double[] wr_expected = new double[n];
        double[] wr_actual = new double[n];
        double[] wi_expected = new double[n];
        double[] wi_actual = new double[n];
        // Initialize Z to identity for compz="I"
        double[] z_expected = generateIdentityMatrix(n);
        double[] z_actual = generateIdentityMatrix(n);
        int lwork = n * n;
        double[] work_expected = new double[lwork];
        double[] work_actual = new double[lwork];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dhseqr("S", "I", n, 1, n, h_expected, 0, n,
                    wr_expected, 0, wi_expected, 0,
                    z_expected, 0, n, work_expected, 0, lwork, info_expected);
        assertEquals(0, info_expected.val, "Reference dhseqr should succeed");

        lapack.dhseqr("S", "I", n, 1, n, h_actual, 0, n,
                      wr_actual, 0, wi_actual, 0,
                      z_actual, 0, n, work_actual, 0, lwork, info_actual);
        assertEquals(0, info_actual.val, "dhseqr should succeed");

        assertArrayEquals(wr_expected, wr_actual, depsilon * 100);
        assertArrayEquals(wi_expected, wi_actual, depsilon * 100);
    }
}
