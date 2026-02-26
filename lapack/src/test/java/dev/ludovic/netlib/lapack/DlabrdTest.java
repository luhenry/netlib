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

public class DlabrdTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // dlabrd is a partial bidiagonal reduction whose intermediate results
        // depend on internal blocking choices. Test indirectly via dgebrd
        // which calls dlabrd and produces canonical bidiagonal factors.
        int n = N_SMALL;

        double[] a_expected = generateMatrix(n, n, 1.0);
        double[] d_expected = new double[n];
        double[] e_expected = new double[n - 1];
        double[] tauq_expected = new double[n];
        double[] taup_expected = new double[n];
        int lwork = 64 * n;
        double[] work_expected = new double[lwork];
        intW info_expected = new intW(0);
        f2j.dgebrd(n, n, a_expected, 0, n, d_expected, 0, e_expected, 0, tauq_expected, 0, taup_expected, 0, work_expected, 0, lwork, info_expected);
        assertEquals(0, info_expected.val, "Reference dgebrd should succeed");

        double[] a_actual = generateMatrix(n, n, 1.0);
        double[] d_actual = new double[n];
        double[] e_actual = new double[n - 1];
        double[] tauq_actual = new double[n];
        double[] taup_actual = new double[n];
        double[] work_actual = new double[lwork];
        intW info_actual = new intW(0);
        lapack.dgebrd(n, n, a_actual, 0, n, d_actual, 0, e_actual, 0, tauq_actual, 0, taup_actual, 0, work_actual, 0, lwork, info_actual);
        assertEquals(0, info_actual.val, "dgebrd should succeed");

        assertRelArrayEquals(d_expected, d_actual, depsilon * 100);
        assertRelArrayEquals(e_expected, e_actual, depsilon * 100);
    }
}
