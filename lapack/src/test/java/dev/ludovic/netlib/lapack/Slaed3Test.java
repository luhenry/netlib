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

public class Slaed3Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // slaed3 finds roots of the secular equation and updates eigenvectors.
        // It is internal to slaed1. We test it indirectly through slaed1
        // with a larger problem to exercise the secular equation solver.
        int n = 10;
        int cutpnt = 5;

        float[] d_expected = new float[n];
        for (int i = 0; i < cutpnt; i++) {
            d_expected[i] = (i + 1) * 1.5f;
        }
        for (int i = cutpnt; i < n; i++) {
            d_expected[i] = (i - cutpnt + 1) * 1.5f + 0.5f;
        }

        float[] q_expected = generateIdentityMatrixFloat(n);

        int[] indxq_expected = new int[n];
        for (int i = 0; i < cutpnt; i++) {
            indxq_expected[i] = i + 1;
        }
        for (int i = cutpnt; i < n; i++) {
            indxq_expected[i] = i - cutpnt + 1;
        }

        floatW rho_expected = new floatW(1.0f);
        float[] work_expected = new float[4 * n + n * n];
        int[] iwork_expected = new int[4 * n];
        intW info_expected = new intW(0);

        f2j.slaed1(n, d_expected, 0, q_expected, 0, n, indxq_expected, 0,
            rho_expected, cutpnt, work_expected, 0, iwork_expected, 0, info_expected);

        float[] d_actual = new float[n];
        for (int i = 0; i < cutpnt; i++) {
            d_actual[i] = (i + 1) * 1.5f;
        }
        for (int i = cutpnt; i < n; i++) {
            d_actual[i] = (i - cutpnt + 1) * 1.5f + 0.5f;
        }

        float[] q_actual = generateIdentityMatrixFloat(n);

        int[] indxq_actual = new int[n];
        for (int i = 0; i < cutpnt; i++) {
            indxq_actual[i] = i + 1;
        }
        for (int i = cutpnt; i < n; i++) {
            indxq_actual[i] = i - cutpnt + 1;
        }

        floatW rho_actual = new floatW(1.0f);
        float[] work_actual = new float[4 * n + n * n];
        int[] iwork_actual = new int[4 * n];
        intW info_actual = new intW(0);

        lapack.slaed1(n, d_actual, 0, q_actual, 0, n, indxq_actual, 0,
            rho_actual, cutpnt, work_actual, 0, iwork_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(d_expected, d_actual, Math.scalb(sepsilon, Math.getExponent(getMaxValue(d_expected))));
        assertArrayEquals(q_expected, q_actual, Math.scalb(sepsilon, Math.getExponent(Math.max(getMaxValue(q_expected), 1.0f))));
    }
}
