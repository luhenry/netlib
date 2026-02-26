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

public class DtgsenTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;

        // A is upper triangular (quasi-triangular Schur form)
        double[] a = generateUpperTriangularMatrix(n, 1.0, 1.0, 0.5);

        // B is upper triangular
        double[] b = generateUpperTriangularMatrix(n, n + 1.0, 1.0, 0.3);

        // Select first 3 eigenvalues
        boolean[] select = new boolean[n];
        for (int i = 0; i < 3; i++) {
            select[i] = true;
        }

        double[] a_expected = a.clone();
        double[] a_actual = a.clone();
        double[] b_expected = b.clone();
        double[] b_actual = b.clone();
        double[] alphar_expected = new double[n];
        double[] alphar_actual = new double[n];
        double[] alphai_expected = new double[n];
        double[] alphai_actual = new double[n];
        double[] beta_expected = new double[n];
        double[] beta_actual = new double[n];
        double[] q_expected = new double[1];
        double[] q_actual = new double[1];
        double[] z_expected = new double[1];
        double[] z_actual = new double[1];
        intW m_expected = new intW(0);
        intW m_actual = new intW(0);
        doubleW pl_expected = new doubleW(0.0);
        doubleW pl_actual = new doubleW(0.0);
        doubleW pr_expected = new doubleW(0.0);
        doubleW pr_actual = new doubleW(0.0);
        double[] dif_expected = new double[2];
        double[] dif_actual = new double[2];
        int lwork = 4 * n + 16;
        double[] work_expected = new double[lwork];
        double[] work_actual = new double[lwork];
        int liwork = 1;
        int[] iwork_expected = new int[liwork];
        int[] iwork_actual = new int[liwork];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dtgsen(0, false, false, select, n, a_expected, n, b_expected, n, alphar_expected, alphai_expected, beta_expected, q_expected, 1, z_expected, 1, m_expected, pl_expected, pr_expected, dif_expected, work_expected, lwork, iwork_expected, liwork, info_expected);
        assertEquals(0, info_expected.val);

        lapack.dtgsen(0, false, false, select, n, a_actual, n, b_actual, n, alphar_actual, alphai_actual, beta_actual, q_actual, 1, z_actual, 1, m_actual, pl_actual, pr_actual, dif_actual, work_actual, lwork, iwork_actual, liwork, info_actual);
        assertEquals(0, info_actual.val);

        assertEquals(m_expected.val, m_actual.val);
        assertArrayEquals(alphar_expected, alphar_actual, depsilon);
        assertArrayEquals(alphai_expected, alphai_actual, depsilon);
        assertArrayEquals(beta_expected, beta_actual, depsilon);
        assertArrayEquals(a_expected, a_actual, depsilon);
        assertArrayEquals(b_expected, b_actual, depsilon);
    }
}
