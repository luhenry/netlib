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

public class DstegrTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        // jobz = "N", range = "A": eigenvalues only, all eigenvalues
        int lwork = 18 * n;
        int liwork = 10 * n;
        double[] d_expected = generateDoubleArray(n, 1.0);
        double[] e_expected = generateDoubleArray(n, 0.5);
        double[] w_expected = new double[n];
        double[] z_expected = new double[n * n];
        int[] isuppz_expected = new int[2 * n];
        double[] work_expected = new double[lwork];
        int[] iwork_expected = new int[liwork];
        intW m_expected = new intW(0);
        intW info_expected = new intW(0);
        f2j.dstegr("N", "A", n, d_expected, 0, e_expected, 0, 0.0, 0.0, 0, 0, 0.0, m_expected, w_expected, 0, z_expected, 0, n, isuppz_expected, 0, work_expected, 0, lwork, iwork_expected, 0, liwork, info_expected);

        double[] d_actual = generateDoubleArray(n, 1.0);
        double[] e_actual = generateDoubleArray(n, 0.5);
        double[] w_actual = new double[n];
        double[] z_actual = new double[n * n];
        int[] isuppz_actual = new int[2 * n];
        double[] work_actual = new double[lwork];
        int[] iwork_actual = new int[liwork];
        intW m_actual = new intW(0);
        intW info_actual = new intW(0);
        lapack.dstegr("N", "A", n, d_actual, 0, e_actual, 0, 0.0, 0.0, 0, 0, 0.0, m_actual, w_actual, 0, z_actual, 0, n, isuppz_actual, 0, work_actual, 0, lwork, iwork_actual, 0, liwork, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertEquals(m_expected.val, m_actual.val);
        assertArrayEquals(w_expected, w_actual, Math.scalb(depsilon, Math.getExponent(getMaxValue(w_expected))));
    }
}
