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

public class SlaeinTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Compute right eigenvector of upper Hessenberg matrix by inverse iteration
        int n = 4;
        float[] h = {
            4.0f, 1.0f, 0.0f, 0.0f,
            2.0f, 3.0f, 0.5f, 0.0f,
            0.0f, 1.0f, 5.0f, 0.3f,
            0.0f, 0.0f, 0.8f, 2.0f
        };
        float wr = 4.0f;
        float wi = 0.0f;
        float[] vr_expected = new float[n];
        float[] vr_actual = new float[n];
        float[] vi_expected = new float[n];
        float[] vi_actual = new float[n];
        float[] b_expected = new float[(n + 1) * n];
        float[] b_actual = new float[(n + 1) * n];
        float[] work_expected = new float[n];
        float[] work_actual = new float[n];
        float eps3 = 1e-6f;
        float smlnum = Float.MIN_NORMAL;
        float bignum = 1.0f / smlnum;
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.slaein(true, true, n, h, n, wr, wi, vr_expected, vi_expected, b_expected, n + 1, work_expected, eps3, smlnum, bignum, info_expected);
        lapack.slaein(true, true, n, h, n, wr, wi, vr_actual, vi_actual, b_actual, n + 1, work_actual, eps3, smlnum, bignum, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(vr_expected, vr_actual, sepsilon);
    }
}
