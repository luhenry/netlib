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

public class SsptrfTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testUpper(LAPACK lapack) {
        int n = N_SMALL;
        int ap_len = n * (n + 1) / 2;

        float[] ap_expected = new float[ap_len];
        int k = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                float val = (i == j) ? (i % 2 == 0 ? 10.0f : -5.0f) : 1.0f / (i + j + 1.0f);
                ap_expected[k++] = val;
            }
        }
        float[] ap_actual = ap_expected.clone();

        int[] ipiv_expected = new int[n];
        int[] ipiv_actual = new int[n];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.ssptrf("U", n, ap_expected, ipiv_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.ssptrf("U", n, ap_actual, ipiv_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(ipiv_expected, ipiv_actual);
        assertArrayEquals(ap_expected, ap_actual, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testLower(LAPACK lapack) {
        int n = N_SMALL;
        int ap_len = n * (n + 1) / 2;

        float[] ap_expected = new float[ap_len];
        int k = 0;
        for (int j = 0; j < n; j++) {
            for (int i = j; i < n; i++) {
                float val = (i == j) ? (i % 2 == 0 ? 10.0f : -5.0f) : 1.0f / (i + j + 1.0f);
                ap_expected[k++] = val;
            }
        }
        float[] ap_actual = ap_expected.clone();

        int[] ipiv_expected = new int[n];
        int[] ipiv_actual = new int[n];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.ssptrf("L", n, ap_expected, ipiv_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.ssptrf("L", n, ap_actual, ipiv_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(ipiv_expected, ipiv_actual);
        assertArrayEquals(ap_expected, ap_actual, sepsilon);
    }
}
