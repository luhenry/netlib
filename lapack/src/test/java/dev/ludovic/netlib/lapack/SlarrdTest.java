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

public class SlarrdTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test computing eigenvalue clusters
        float[] gers = new float[2 * N_SMALL];
        float[] d = generateFloatArray(N_SMALL, 1.0f);
        float[] e = generateFloatArray(N_SMALL - 1, 0.1f);
        float[] e2 = generateFloatArray(N_SMALL - 1, 0.01f);
        int[] isplit = new int[N_SMALL];
        float[] w = new float[N_SMALL];
        float[] werr = new float[N_SMALL];
        int[] iblock = new int[N_SMALL];
        int[] indexw = new int[N_SMALL];
        float[] work = new float[4 * N_SMALL];
        int[] iwork = new int[3 * N_SMALL];

        intW m_expected = new intW(0);
        floatW wl_expected = new floatW(0.0f);
        floatW wu_expected = new floatW(0.0f);
        intW info_expected = new intW(0);
        f2j.slarrd("A", "B", N_SMALL, 0.0f, 0.0f, 0, 0, gers, 0, 1e-6f, d, 0, e, 0, e2, 0, 1e-10f, 1, isplit, 0,
                   m_expected, w, 0, werr, 0, wl_expected, wu_expected, iblock, 0, indexw, 0, work, 0, iwork, 0, info_expected);

        intW m_actual = new intW(0);
        floatW wl_actual = new floatW(0.0f);
        floatW wu_actual = new floatW(0.0f);
        intW info_actual = new intW(0);
        lapack.slarrd("A", "B", N_SMALL, 0.0f, 0.0f, 0, 0, gers, 0, 1e-6f, d, 0, e, 0, e2, 0, 1e-10f, 1, isplit, 0,
                      m_actual, w, 0, werr, 0, wl_actual, wu_actual, iblock, 0, indexw, 0, work, 0, iwork, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertEquals(m_expected.val, m_actual.val);
    }
}
