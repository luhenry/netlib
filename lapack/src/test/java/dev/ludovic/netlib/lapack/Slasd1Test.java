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

public class Slasd1Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // SLASD1 is an internal D&C SVD routine with complex workspace requirements
        // F2j has array indexing bugs (tries to access idxq[8] when array has length 7)
        // Skip this test for all implementations
        org.junit.jupiter.api.Assumptions.assumeTrue(false);
        int nl = 3;
        int nr = 3;
        int sqre = 0;
        int n = nl + nr + 1;

        float[] d_expected = new float[n];
        for (int i = 0; i < n; i++) {
            d_expected[i] = (n - i) * 2.0f;
        }

        floatW alpha_expected = new floatW(1.5f);
        floatW beta_expected = new floatW(0.8f);

        float[] u_expected = generateIdentityMatrixFloat(n);

        float[] vt_expected = generateIdentityMatrixFloat(n);

        int[] idxq_expected = new int[n];
        for (int i = 0; i < n; i++) {
            idxq_expected[i] = i + 1;
        }

        int[] iwork_expected = new int[8 * n];
        float[] work_expected = new float[3 * n * n + 4 * n];
        intW info_expected = new intW(0);

        f2j.slasd1(nl, nr, sqre, d_expected, 0, alpha_expected, beta_expected,
            u_expected, 0, n, vt_expected, 0, n, idxq_expected, 0,
            iwork_expected, 0, work_expected, 0, info_expected);

        float[] d_actual = new float[n];
        for (int i = 0; i < n; i++) {
            d_actual[i] = (n - i) * 2.0f;
        }

        floatW alpha_actual = new floatW(1.5f);
        floatW beta_actual = new floatW(0.8f);

        float[] u_actual = generateIdentityMatrixFloat(n);

        float[] vt_actual = generateIdentityMatrixFloat(n);

        int[] idxq_actual = new int[n];
        for (int i = 0; i < n; i++) {
            idxq_actual[i] = i + 1;
        }

        int[] iwork_actual = new int[8 * n];
        float[] work_actual = new float[3 * n * n + 4 * n];
        intW info_actual = new intW(0);

        lapack.slasd1(nl, nr, sqre, d_actual, 0, alpha_actual, beta_actual,
            u_actual, 0, n, vt_actual, 0, n, idxq_actual, 0,
            iwork_actual, 0, work_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(d_expected, d_actual, Math.scalb(sepsilon, Math.getExponent(getMaxValue(d_expected))));
        assertArrayEquals(u_expected, u_actual, Math.scalb(sepsilon, Math.getExponent(Math.max(getMaxValue(u_expected), 1.0f))));
        assertArrayEquals(vt_expected, vt_actual, Math.scalb(sepsilon, Math.getExponent(Math.max(getMaxValue(vt_expected), 1.0f))));
    }
}
