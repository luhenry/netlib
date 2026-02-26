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

public class SlagtsTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // First factor with slagtf, then solve with slagts
        int n = 5;
        float[] a = { 4.0f, 5.0f, 6.0f, 7.0f, 8.0f };
        float lambda = 1.0f;
        float[] b = { 1.0f, 1.0f, 1.0f, 1.0f };
        float[] c = { 0.5f, 0.5f, 0.5f, 0.5f };
        float[] d = new float[n - 2];
        int[] in = new int[n];
        intW info = new intW(0);

        f2j.slagtf(n, a, lambda, b, c, 0.0f, d, in, info);
        assertEquals(0, info.val);

        // Solve (T - lambda*I)*x = y with JOB=1
        float[] y_expected = { 1.0f, 2.0f, 3.0f, 4.0f, 5.0f };
        float[] y_actual = y_expected.clone();
        floatW tol_expected = new floatW(0.0f);
        floatW tol_actual = new floatW(0.0f);
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.slagts(1, n, a, b, c, d, in, y_expected, tol_expected, info_expected);
        lapack.slagts(1, n, a, b, c, d, in, y_actual, tol_actual, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(y_expected, y_actual, sepsilon);
    }
}
