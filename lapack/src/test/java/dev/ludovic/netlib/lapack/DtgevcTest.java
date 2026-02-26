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

public class DtgevcTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;

        // S is upper triangular (quasi-triangular) with distinct eigenvalues
        double[] s = generateUpperTriangularMatrix(n, 1.0, 1.0, 0.5);

        // P is upper triangular with positive diagonal (well-conditioned)
        double[] p = generateUpperTriangularMatrix(n, n + 1.0, 1.0, 0.3);

        boolean[] select = new boolean[n];
        double[] vl = new double[1];
        double[] vr_expected = new double[n * n];
        double[] vr_actual = new double[n * n];
        double[] work_expected = new double[6 * n];
        double[] work_actual = new double[6 * n];
        intW m_expected = new intW(0);
        intW m_actual = new intW(0);
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dtgevc("R", "A", select, n, s, n, p, n, vl, 1, vr_expected, n, n, m_expected, work_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.dtgevc("R", "A", select, n, s, n, p, n, vl, 1, vr_actual, n, n, m_actual, work_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertEquals(m_expected.val, m_actual.val);
        assertArrayEquals(vr_expected, vr_actual, depsilon);
    }
}
