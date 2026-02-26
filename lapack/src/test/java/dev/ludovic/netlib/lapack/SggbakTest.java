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

public class SggbakTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        // First balance the matrix pair
        float[] a = generateMatrixFloat(n, n, 1.0f);
        float[] b = generateMatrixFloat(n, n, 2.0f);
        intW ilo = new intW(0);
        intW ihi = new intW(0);
        float[] lscale = new float[n];
        float[] rscale = new float[n];
        float[] work = new float[6 * n];
        intW info = new intW(0);

        f2j.sggbal("B", n, a, n, b, n, ilo, ihi, lscale, rscale, work, info);
        assertEquals(0, info.val);

        // Create eigenvector matrix (identity as placeholder)
        int m = n;
        float[] v_expected = new float[n * m];
        for (int i = 0; i < n; i++) v_expected[i + i * n] = 1.0f;
        float[] v_actual = v_expected.clone();

        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.sggbak("B", "R", n, ilo.val, ihi.val, lscale, rscale, m, v_expected, n, info_expected);
        assertEquals(0, info_expected.val);

        lapack.sggbak("B", "R", n, ilo.val, ihi.val, lscale, rscale, m, v_actual, n, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(v_expected, v_actual, sepsilon);
    }
}
