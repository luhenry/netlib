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

public class DlarreTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test representation tree for eigenvalues
        double[] d = generateDoubleArray(N_SMALL + 1, 1.0);
        double[] e = generateDoubleArray(N_SMALL, 0.1);
        double[] e2 = generateDoubleArray(N_SMALL, 0.01);
        int[] isplit = new int[N_SMALL + 1];
        double[] w = new double[N_SMALL + 1];
        double[] werr = new double[N_SMALL + 1];
        double[] wgap = new double[N_SMALL + 1];
        int[] iblock = new int[N_SMALL + 1];
        int[] indexw = new int[N_SMALL + 1];
        double[] gers = new double[2 * N_SMALL];
        double[] work = new double[6 * N_SMALL];
        int[] iwork = new int[5 * N_SMALL];

        doubleW vl_expected = new doubleW(0.0);
        doubleW vu_expected = new doubleW(0.0);
        intW nsplit_expected = new intW(0);
        intW m_expected = new intW(0);
        doubleW pivmin_expected = new doubleW(0.0);
        intW info_expected = new intW(0);
        f2j.dlarre("A", N_SMALL, vl_expected, vu_expected, 0, 0, d, 0, e, 0, e2, 0, 1e-8, 1e-8, 1e-8,
                   nsplit_expected, isplit, 0, m_expected, w, 0, werr, 0, wgap, 0, iblock, 0, indexw, 0,
                   gers, 0, pivmin_expected, work, 0, iwork, 0, info_expected);

        doubleW vl_actual = new doubleW(0.0);
        doubleW vu_actual = new doubleW(0.0);
        intW nsplit_actual = new intW(0);
        intW m_actual = new intW(0);
        doubleW pivmin_actual = new doubleW(0.0);
        intW info_actual = new intW(0);
        lapack.dlarre("A", N_SMALL, vl_actual, vu_actual, 0, 0, d, 0, e, 0, e2, 0, 1e-8, 1e-8, 1e-8,
                      nsplit_actual, isplit, 0, m_actual, w, 0, werr, 0, wgap, 0, iblock, 0, indexw, 0,
                      gers, 0, pivmin_actual, work, 0, iwork, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertEquals(m_expected.val, m_actual.val);
    }
}
