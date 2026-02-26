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

public class SstevxTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        // jobz = "N", range = "A": eigenvalues only, all eigenvalues
        float[] d_expected = generateFloatArray(n, 1.0f);
        float[] e_expected = generateFloatArray(n - 1, 0.5f);
        float[] w_expected = new float[n];
        float[] z_expected = new float[n * n];
        float[] work_expected = new float[5 * n];
        int[] iwork_expected = new int[5 * n];
        int[] ifail_expected = new int[n];
        intW m_expected = new intW(0);
        intW info_expected = new intW(0);
        f2j.sstevx("N", "A", n, d_expected, 0, e_expected, 0, 0.0f, 0.0f, 0, 0, 0.0f, m_expected, w_expected, 0, z_expected, 0, n, work_expected, 0, iwork_expected, 0, ifail_expected, 0, info_expected);

        float[] d_actual = generateFloatArray(n, 1.0f);
        float[] e_actual = generateFloatArray(n - 1, 0.5f);
        float[] w_actual = new float[n];
        float[] z_actual = new float[n * n];
        float[] work_actual = new float[5 * n];
        int[] iwork_actual = new int[5 * n];
        int[] ifail_actual = new int[n];
        intW m_actual = new intW(0);
        intW info_actual = new intW(0);
        lapack.sstevx("N", "A", n, d_actual, 0, e_actual, 0, 0.0f, 0.0f, 0, 0, 0.0f, m_actual, w_actual, 0, z_actual, 0, n, work_actual, 0, iwork_actual, 0, ifail_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertEquals(m_expected.val, m_actual.val);
        assertArrayEquals(w_expected, w_actual, Math.scalb(sepsilon, Math.getExponent(getMaxValue(w_expected))));
    }
}
