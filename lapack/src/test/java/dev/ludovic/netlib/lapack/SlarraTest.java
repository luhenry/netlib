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

public class SlarraTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test computation of splitting points for tridiagonal eigenvalue problem
        int n = N_SMALL;
        float[] d = new float[n];
        float[] e = new float[n];
        float[] e2 = new float[n];

        // Create a simple tridiagonal matrix
        for (int i = 0; i < n; i++) {
            d[i] = 2.0f + i * 0.1f;
            if (i < n - 1) {
                e[i] = 1.0f;
                e2[i] = 1.0f;
            }
        }

        float spltol = (float)Math.sqrt(1.1754944e-38f);
        float tnrm = 10.0f;
        int[] isplit_expected = new int[n];
        intW nsplit_expected = new intW(0);
        intW info_expected = new intW(0);

        f2j.slarra(n, d.clone(), e.clone(), e2.clone(), spltol, tnrm, nsplit_expected, isplit_expected, info_expected);

        int[] isplit_actual = new int[n];
        intW nsplit_actual = new intW(0);
        intW info_actual = new intW(0);

        lapack.slarra(n, d.clone(), e.clone(), e2.clone(), spltol, tnrm, nsplit_actual, isplit_actual, info_actual);

        assertEquals(nsplit_expected.val, nsplit_actual.val);
        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(isplit_expected, isplit_actual);
    }
}
