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

public class DtgexcTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;

        // A is upper triangular (quasi-triangular Schur form)
        double[] a = generateUpperTriangularMatrix(n, (double) n, -1.0, 0.5);

        // B is upper triangular
        double[] b = generateUpperTriangularMatrix(n, n + 1.0, 1.0, 0.3);

        double[] a_expected = a.clone();
        double[] a_actual = a.clone();
        double[] b_expected = b.clone();
        double[] b_actual = b.clone();
        double[] q_expected = new double[1];
        double[] q_actual = new double[1];
        double[] z_expected = new double[1];
        double[] z_actual = new double[1];
        // Move eigenvalue from position 1 to position n (1-based)
        intW ifst_expected = new intW(1);
        intW ifst_actual = new intW(1);
        intW ilst_expected = new intW(n);
        intW ilst_actual = new intW(n);
        int lwork = 4 * n + 16;
        double[] work_expected = new double[lwork];
        double[] work_actual = new double[lwork];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dtgexc(false, false, n, a_expected, n, b_expected, n, q_expected, 1, z_expected, 1, ifst_expected, ilst_expected, work_expected, lwork, info_expected);
        assertEquals(0, info_expected.val);

        lapack.dtgexc(false, false, n, a_actual, n, b_actual, n, q_actual, 1, z_actual, 1, ifst_actual, ilst_actual, work_actual, lwork, info_actual);
        assertEquals(0, info_actual.val);

        assertEquals(ifst_expected.val, ifst_actual.val);
        assertEquals(ilst_expected.val, ilst_actual.val);
        assertArrayEquals(a_expected, a_actual, depsilon);
        assertArrayEquals(b_expected, b_actual, depsilon);
    }
}
