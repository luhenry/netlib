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

public class DtgsylTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;

        // A is m x m upper triangular (quasi-triangular Schur form)
        double[] a = generateUpperTriangularMatrix(m, 1.0, 1.0, 0.5);

        // B is n x n upper triangular (quasi-triangular Schur form)
        double[] b = generateUpperTriangularMatrix(n, m + 1.0, 1.0, 0.3);

        // D is m x m upper triangular
        double[] d = generateUpperTriangularMatrix(m, 2 * m + 1.0, 1.0, 0.2);

        // E is n x n upper triangular
        double[] e = generateUpperTriangularMatrix(n, 2 * m + n + 1.0, 1.0, 0.1);

        // C is m x n general RHS matrix (overwritten with solution R)
        double[] c = new double[m * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                c[i + j * m] = 1.0 / (i + j + 1.0);
            }
        }

        // F is m x n general RHS matrix (overwritten with solution L)
        double[] f = new double[m * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                f[i + j * m] = 1.0 / (i + j + 2.0);
            }
        }

        double[] c_expected = c.clone();
        double[] c_actual = c.clone();
        double[] f_expected = f.clone();
        double[] f_actual = f.clone();
        doubleW scale_expected = new doubleW(0.0);
        doubleW scale_actual = new doubleW(0.0);
        doubleW dif_expected = new doubleW(0.0);
        doubleW dif_actual = new doubleW(0.0);
        int lwork = 1;
        double[] work_expected = new double[lwork];
        double[] work_actual = new double[lwork];
        int[] iwork_expected = new int[m + n + 6];
        int[] iwork_actual = new int[m + n + 6];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dtgsyl("N", 0, m, n, a, m, b, n, c_expected, m, d, m, e, n, f_expected, m, scale_expected, dif_expected, work_expected, lwork, iwork_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.dtgsyl("N", 0, m, n, a, m, b, n, c_actual, m, d, m, e, n, f_actual, m, scale_actual, dif_actual, work_actual, lwork, iwork_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertEquals(scale_expected.val, scale_actual.val, depsilon);
        assertArrayEquals(c_expected, c_actual, depsilon);
        assertArrayEquals(f_expected, f_actual, depsilon);
    }
}
