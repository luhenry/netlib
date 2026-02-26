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

public class DdisnaTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testEigenvectors(LAPACK lapack) {
        // DDISNA computes reciprocal condition numbers for eigenvectors
        // of a symmetric matrix, given eigenvalues in decreasing order
        int n = 5;
        double[] d = {10.0, 7.0, 4.0, 2.0, 1.0}; // eigenvalues in decreasing order

        double[] sep_expected = new double[n];
        double[] sep_actual = new double[n];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.ddisna("E", n, n, d, sep_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.ddisna("E", n, n, d, sep_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(sep_expected, sep_actual, depsilon);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testLeftSingularVectors(LAPACK lapack) {
        // Reciprocal condition numbers for left singular vectors
        int m = 6, n = 4;
        int minmn = Math.min(m, n);
        double[] d = {10.0, 7.0, 3.0, 1.0}; // singular values in decreasing order

        double[] sep_expected = new double[minmn];
        double[] sep_actual = new double[minmn];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.ddisna("L", m, n, d, sep_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.ddisna("L", m, n, d, sep_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(sep_expected, sep_actual, depsilon);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testRightSingularVectors(LAPACK lapack) {
        // Reciprocal condition numbers for right singular vectors
        int m = 4, n = 6;
        int minmn = Math.min(m, n);
        double[] d = {10.0, 7.0, 3.0, 1.0}; // singular values in decreasing order

        double[] sep_expected = new double[minmn];
        double[] sep_actual = new double[minmn];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.ddisna("R", m, n, d, sep_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.ddisna("R", m, n, d, sep_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(sep_expected, sep_actual, depsilon);
    }
}
