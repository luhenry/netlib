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

public class SgeevTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        float[] a_expected = generateMatrixFloat(n, n, 1.0f);
        float[] a_actual = a_expected.clone();
        float[] wr_expected = new float[n];
        float[] wr_actual = new float[n];
        float[] wi_expected = new float[n];
        float[] wi_actual = new float[n];
        int lwork = 4 * n;
        float[] work_expected = new float[lwork];
        float[] work_actual = new float[lwork];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.sgeev("N", "N", n, a_expected, n, wr_expected, wi_expected,
                  new float[1], 1, new float[1], 1, work_expected, lwork, info_expected);
        assertEquals(0, info_expected.val, "Reference sgeev should succeed");

        lapack.sgeev("N", "N", n, a_actual, n, wr_actual, wi_actual,
                     new float[1], 1, new float[1], 1, work_actual, lwork, info_actual);
        assertEquals(0, info_actual.val, "sgeev should succeed");

        // Sort eigenvalues for comparison (ordering may differ between implementations)
        java.util.Arrays.sort(wr_expected);
        java.util.Arrays.sort(wr_actual);
        assertArrayEquals(wr_expected, wr_actual, sepsilon * 100);
    }
}
