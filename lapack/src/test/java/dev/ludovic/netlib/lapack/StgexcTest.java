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

public class StgexcTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;

        float[] a = generateUpperTriangularMatrixFloat(n, (float) n, -1.0f, 0.5f);

        float[] b = generateUpperTriangularMatrixFloat(n, n + 1.0f, 1.0f, 0.3f);

        float[] a_expected = a.clone();
        float[] a_actual = a.clone();
        float[] b_expected = b.clone();
        float[] b_actual = b.clone();
        float[] q_expected = new float[1];
        float[] q_actual = new float[1];
        float[] z_expected = new float[1];
        float[] z_actual = new float[1];
        intW ifst_expected = new intW(1);
        intW ifst_actual = new intW(1);
        intW ilst_expected = new intW(n);
        intW ilst_actual = new intW(n);
        int lwork = 4 * n + 16;
        float[] work_expected = new float[lwork];
        float[] work_actual = new float[lwork];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.stgexc(false, false, n, a_expected, n, b_expected, n, q_expected, 1, z_expected, 1, ifst_expected, ilst_expected, work_expected, lwork, info_expected);
        assertEquals(0, info_expected.val);

        lapack.stgexc(false, false, n, a_actual, n, b_actual, n, q_actual, 1, z_actual, 1, ifst_actual, ilst_actual, work_actual, lwork, info_actual);
        assertEquals(0, info_actual.val);

        assertEquals(ifst_expected.val, ifst_actual.val);
        assertEquals(ilst_expected.val, ilst_actual.val);
        assertArrayEquals(a_expected, a_actual, sepsilon);
        assertArrayEquals(b_expected, b_actual, sepsilon);
    }
}
