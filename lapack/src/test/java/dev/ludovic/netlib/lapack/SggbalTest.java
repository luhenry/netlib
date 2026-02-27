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

public class SggbalTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        float[] a_expected = generateMatrixFloat(n, n, 1.0f);
        float[] a_actual = a_expected.clone();
        float[] b_expected = generateMatrixFloat(n, n, 2.0f);
        float[] b_actual = b_expected.clone();
        intW ilo_expected = new intW(0);
        intW ilo_actual = new intW(0);
        intW ihi_expected = new intW(0);
        intW ihi_actual = new intW(0);
        float[] lscale_expected = new float[n];
        float[] lscale_actual = new float[n];
        float[] rscale_expected = new float[n];
        float[] rscale_actual = new float[n];
        float[] work_expected = new float[6 * n];
        float[] work_actual = new float[6 * n];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.sggbal("B", n, a_expected, n, b_expected, n, ilo_expected, ihi_expected,
                    lscale_expected, rscale_expected, work_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.sggbal("B", n, a_actual, n, b_actual, n, ilo_actual, ihi_actual,
                      lscale_actual, rscale_actual, work_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(a_expected, a_actual, sepsilon);
        assertArrayEquals(b_expected, b_actual, sepsilon);
        assertArrayEquals(lscale_expected, lscale_actual, sepsilon);
        assertArrayEquals(rscale_expected, rscale_actual, sepsilon);
        assertEquals(ilo_expected.val, ilo_actual.val);
        assertEquals(ihi_expected.val, ihi_actual.val);
    }
}
