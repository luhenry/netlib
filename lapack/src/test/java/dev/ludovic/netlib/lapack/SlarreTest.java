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

public class SlarreTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test representation tree for eigenvalues
        float[] d = generateFloatArray(N_SMALL + 1, 1.0f);
        float[] e = generateFloatArray(N_SMALL, 0.1f);
        float[] e2 = generateFloatArray(N_SMALL, 0.01f);
        int[] isplit = new int[N_SMALL + 1];
        float[] w = new float[N_SMALL + 1];
        float[] werr = new float[N_SMALL + 1];
        float[] wgap = new float[N_SMALL + 1];
        int[] iblock = new int[N_SMALL + 1];
        int[] indexw = new int[N_SMALL + 1];
        float[] gers = new float[2 * N_SMALL];
        float[] work = new float[6 * N_SMALL];
        int[] iwork = new int[5 * N_SMALL];

        floatW vl_expected = new floatW(0.0f);
        floatW vu_expected = new floatW(0.0f);
        intW nsplit_expected = new intW(0);
        intW m_expected = new intW(0);
        floatW pivmin_expected = new floatW(0.0f);
        intW info_expected = new intW(0);
        f2j.slarre("A", N_SMALL, vl_expected, vu_expected, 0, 0, d, 0, e, 0, e2, 0, 1e-6f, 1e-6f, 1e-6f,
                   nsplit_expected, isplit, 0, m_expected, w, 0, werr, 0, wgap, 0, iblock, 0, indexw, 0,
                   gers, 0, pivmin_expected, work, 0, iwork, 0, info_expected);

        floatW vl_actual = new floatW(0.0f);
        floatW vu_actual = new floatW(0.0f);
        intW nsplit_actual = new intW(0);
        intW m_actual = new intW(0);
        floatW pivmin_actual = new floatW(0.0f);
        intW info_actual = new intW(0);
        lapack.slarre("A", N_SMALL, vl_actual, vu_actual, 0, 0, d, 0, e, 0, e2, 0, 1e-6f, 1e-6f, 1e-6f,
                      nsplit_actual, isplit, 0, m_actual, w, 0, werr, 0, wgap, 0, iblock, 0, indexw, 0,
                      gers, 0, pivmin_actual, work, 0, iwork, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertEquals(m_expected.val, m_actual.val);
    }
}
