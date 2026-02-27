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

public class StgevcTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;

        float[] s = generateUpperTriangularMatrixFloat(n, 1.0f, 1.0f, 0.5f);

        float[] p = generateUpperTriangularMatrixFloat(n, n + 1.0f, 1.0f, 0.3f);

        boolean[] select = new boolean[n];
        float[] vl = new float[1];
        float[] vr_expected = new float[n * n];
        float[] vr_actual = new float[n * n];
        float[] work_expected = new float[6 * n];
        float[] work_actual = new float[6 * n];
        intW m_expected = new intW(0);
        intW m_actual = new intW(0);
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.stgevc("R", "A", select, n, s, n, p, n, vl, 1, vr_expected, n, n, m_expected, work_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.stgevc("R", "A", select, n, s, n, p, n, vl, 1, vr_actual, n, n, m_actual, work_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertEquals(m_expected.val, m_actual.val);
        assertArrayEquals(vr_expected, vr_actual, sepsilon);
    }
}
