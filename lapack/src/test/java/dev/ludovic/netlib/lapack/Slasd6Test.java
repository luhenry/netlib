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

public class Slasd6Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;

        float[] d_expected = new float[n];
        float[] e_expected = new float[n - 1];
        for (int i = 0; i < n; i++) {
            d_expected[i] = (i + 1) * 2.0f;
        }
        for (int i = 0; i < n - 1; i++) {
            e_expected[i] = 0.5f;
        }

        float[] u_expected = new float[n * n];
        float[] vt_expected = new float[n * n];
        float[] q_expected = new float[n * (11 + 2 * n + 8 * (int)(Math.log(n) / Math.log(2.0) + 1))];
        int[] iq_expected = new int[n * (3 + 3 * (int)(Math.log(n) / Math.log(2.0) + 1))];
        float[] work_expected = new float[3 * n * n + 4 * n];
        int[] iwork_expected = new int[8 * n];
        intW info_expected = new intW(0);

        f2j.sbdsdc("U", "I", n, d_expected, 0, e_expected, 0,
            u_expected, 0, n, vt_expected, 0, n,
            q_expected, 0, iq_expected, 0, work_expected, 0, iwork_expected, 0, info_expected);

        float[] d_actual = new float[n];
        float[] e_actual = new float[n - 1];
        for (int i = 0; i < n; i++) {
            d_actual[i] = (i + 1) * 2.0f;
        }
        for (int i = 0; i < n - 1; i++) {
            e_actual[i] = 0.5f;
        }

        float[] u_actual = new float[n * n];
        float[] vt_actual = new float[n * n];
        float[] q_actual = new float[n * (11 + 2 * n + 8 * (int)(Math.log(n) / Math.log(2.0) + 1))];
        int[] iq_actual = new int[n * (3 + 3 * (int)(Math.log(n) / Math.log(2.0) + 1))];
        float[] work_actual = new float[3 * n * n + 4 * n];
        int[] iwork_actual = new int[8 * n];
        intW info_actual = new intW(0);

        lapack.sbdsdc("U", "I", n, d_actual, 0, e_actual, 0,
            u_actual, 0, n, vt_actual, 0, n,
            q_actual, 0, iq_actual, 0, work_actual, 0, iwork_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(d_expected, d_actual, Math.scalb(sepsilon, Math.getExponent(getMaxValue(d_expected))));
        assertArrayEquals(u_expected, u_actual, Math.scalb(sepsilon, Math.getExponent(Math.max(getMaxValue(u_expected), 1.0f))));
        assertArrayEquals(vt_expected, vt_actual, Math.scalb(sepsilon, Math.getExponent(Math.max(getMaxValue(vt_expected), 1.0f))));
    }
}
