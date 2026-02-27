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

public class DggrqfTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // DGGRQF computes the GRQ factorization of an M-by-N matrix A and a P-by-N matrix B
        int m = N_SMALL;
        int p = N_SMALL;
        int n = N_SMALL;

        double[] a_expected = generateMatrix(m, n, 1.0);
        double[] taua_expected = new double[Math.min(m, n)];
        double[] b_expected = generateMatrix(p, n, 2.0);
        double[] taub_expected = new double[Math.min(p, n)];
        int lwork = Math.max(Math.max(n, m), p);
        double[] work_expected = new double[lwork];
        intW info_expected = new intW(0);

        f2j.dggrqf(m, p, n, a_expected, 0, m, taua_expected, 0, b_expected, 0, p, taub_expected, 0, work_expected, 0, lwork, info_expected);
        assertEquals(0, info_expected.val, "Reference dggrqf should succeed");

        double[] a_actual = generateMatrix(m, n, 1.0);
        double[] taua_actual = new double[Math.min(m, n)];
        double[] b_actual = generateMatrix(p, n, 2.0);
        double[] taub_actual = new double[Math.min(p, n)];
        double[] work_actual = new double[lwork];
        intW info_actual = new intW(0);

        lapack.dggrqf(m, p, n, a_actual, 0, m, taua_actual, 0, b_actual, 0, p, taub_actual, 0, work_actual, 0, lwork, info_actual);
        assertEquals(0, info_actual.val, "dggrqf should succeed");

        assertRelArrayEquals(a_expected, a_actual, 1000.0);
        assertRelArrayEquals(taua_expected, taua_actual, 1000.0);
        assertRelArrayEquals(b_expected, b_actual, 1000.0);
        assertRelArrayEquals(taub_expected, taub_actual, 1000.0);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testRectangular(LAPACK lapack) {
        int m = N_SMALL / 2;
        int p = N_SMALL;
        int n = N_SMALL;

        double[] a_expected = generateMatrix(m, n, 1.0);
        double[] taua_expected = new double[Math.min(m, n)];
        double[] b_expected = generateMatrix(p, n, 2.0);
        double[] taub_expected = new double[Math.min(p, n)];
        int lwork = Math.max(Math.max(n, m), p);
        double[] work_expected = new double[lwork];
        intW info_expected = new intW(0);

        f2j.dggrqf(m, p, n, a_expected, 0, m, taua_expected, 0, b_expected, 0, p, taub_expected, 0, work_expected, 0, lwork, info_expected);
        assertEquals(0, info_expected.val);

        double[] a_actual = generateMatrix(m, n, 1.0);
        double[] taua_actual = new double[Math.min(m, n)];
        double[] b_actual = generateMatrix(p, n, 2.0);
        double[] taub_actual = new double[Math.min(p, n)];
        double[] work_actual = new double[lwork];
        intW info_actual = new intW(0);

        lapack.dggrqf(m, p, n, a_actual, 0, m, taua_actual, 0, b_actual, 0, p, taub_actual, 0, work_actual, 0, lwork, info_actual);
        assertEquals(0, info_actual.val);

        assertRelArrayEquals(a_expected, a_actual, 1000.0);
        assertRelArrayEquals(taua_expected, taua_actual, 1000.0);
        assertRelArrayEquals(b_expected, b_actual, 1000.0);
        assertRelArrayEquals(taub_expected, taub_actual, 1000.0);
    }
}
