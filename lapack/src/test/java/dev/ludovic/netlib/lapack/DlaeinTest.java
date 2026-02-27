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

public class DlaeinTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Compute right eigenvector of upper Hessenberg matrix by inverse iteration
        int n = 4;
        // Upper Hessenberg matrix (upper triangular + one sub-diagonal)
        double[] h = {
            4.0, 1.0, 0.0, 0.0,
            2.0, 3.0, 0.5, 0.0,
            0.0, 1.0, 5.0, 0.3,
            0.0, 0.0, 0.8, 2.0
        };
        // Use a diagonal element as approximate eigenvalue (real)
        double wr = 4.0;
        double wi = 0.0;
        double[] vr_expected = new double[n];
        double[] vr_actual = new double[n];
        double[] vi_expected = new double[n];
        double[] vi_actual = new double[n];
        double[] b_expected = new double[(n + 1) * n];
        double[] b_actual = new double[(n + 1) * n];
        double[] work_expected = new double[n];
        double[] work_actual = new double[n];
        double eps3 = 1e-12;
        double smlnum = Double.MIN_NORMAL;
        double bignum = 1.0 / smlnum;
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dlaein(true, true, n, h, n, wr, wi, vr_expected, vi_expected, b_expected, n + 1, work_expected, eps3, smlnum, bignum, info_expected);
        lapack.dlaein(true, true, n, h, n, wr, wi, vr_actual, vi_actual, b_actual, n + 1, work_actual, eps3, smlnum, bignum, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(vr_expected, vr_actual, depsilon);
    }
}
