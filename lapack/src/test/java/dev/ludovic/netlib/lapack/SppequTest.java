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

public class SppequTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testUpper(LAPACK lapack) {
        int n = N_SMALL;
        int ap_size = n * (n + 1) / 2;
        float[] ap = new float[ap_size];
        float[] pdm = generatePositiveDefiniteMatrixFloat(n);
        int k = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                ap[k++] = pdm[i + j * n];
            }
        }

        float[] s_expected = new float[n];
        float[] s_actual = new float[n];
        floatW scond_expected = new floatW(0);
        floatW scond_actual = new floatW(0);
        floatW amax_expected = new floatW(0);
        floatW amax_actual = new floatW(0);
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.sppequ("U", n, ap, s_expected, scond_expected, amax_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.sppequ("U", n, ap, s_actual, scond_actual, amax_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(s_expected, s_actual, sepsilon);
        assertEquals(scond_expected.val, scond_actual.val, sepsilon);
        assertEquals(amax_expected.val, amax_actual.val, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testLower(LAPACK lapack) {
        int n = N_SMALL;
        int ap_size = n * (n + 1) / 2;
        float[] ap = new float[ap_size];
        float[] pdm = generatePositiveDefiniteMatrixFloat(n);
        int k = 0;
        for (int j = 0; j < n; j++) {
            for (int i = j; i < n; i++) {
                ap[k++] = pdm[i + j * n];
            }
        }

        float[] s_expected = new float[n];
        float[] s_actual = new float[n];
        floatW scond_expected = new floatW(0);
        floatW scond_actual = new floatW(0);
        floatW amax_expected = new floatW(0);
        floatW amax_actual = new floatW(0);
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.sppequ("L", n, ap, s_expected, scond_expected, amax_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.sppequ("L", n, ap, s_actual, scond_actual, amax_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(s_expected, s_actual, sepsilon);
        assertEquals(scond_expected.val, scond_actual.val, sepsilon);
        assertEquals(amax_expected.val, amax_actual.val, sepsilon);
    }
}
