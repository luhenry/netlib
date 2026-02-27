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

public class DsteinTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        // First compute eigenvalues using dstebz
        double[] d = generateDoubleArray(n, 1.0);
        double[] e = generateDoubleArray(n - 1, 0.5);
        double[] w = new double[n];
        int[] iblock = new int[n];
        int[] isplit = new int[n];
        double[] work_stebz = new double[4 * n];
        int[] iwork_stebz = new int[3 * n];
        intW m = new intW(0);
        intW nsplit = new intW(0);
        intW info_stebz = new intW(0);
        f2j.dstebz("A", "B", n, 0.0, 0.0, 0, 0, 0.0, d, 0, e, 0, m, nsplit, w, 0, iblock, 0, isplit, 0, work_stebz, 0, iwork_stebz, 0, info_stebz);
        assertEquals(0, info_stebz.val);

        int mval = m.val;
        // Now test dstein with the computed eigenvalues
        double[] d_orig = generateDoubleArray(n, 1.0);
        double[] e_orig = generateDoubleArray(n - 1, 0.5);

        double[] z_expected = new double[n * mval];
        double[] work_expected = new double[5 * n];
        int[] iwork_expected = new int[n];
        int[] ifail_expected = new int[mval];
        intW info_expected = new intW(0);
        f2j.dstein(n, d_orig, 0, e_orig, 0, mval, w, 0, iblock, 0, isplit, 0, z_expected, 0, n, work_expected, 0, iwork_expected, 0, ifail_expected, 0, info_expected);

        double[] d_orig2 = generateDoubleArray(n, 1.0);
        double[] e_orig2 = generateDoubleArray(n - 1, 0.5);

        double[] z_actual = new double[n * mval];
        double[] work_actual = new double[5 * n];
        int[] iwork_actual = new int[n];
        int[] ifail_actual = new int[mval];
        intW info_actual = new intW(0);
        lapack.dstein(n, d_orig2, 0, e_orig2, 0, mval, w, 0, iblock, 0, isplit, 0, z_actual, 0, n, work_actual, 0, iwork_actual, 0, ifail_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
    }
}
