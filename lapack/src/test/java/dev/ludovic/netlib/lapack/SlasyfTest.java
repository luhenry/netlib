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

public class SlasyfTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        int nb = 4;

        // Positive definite symmetric matrix (use upper part)
        float[] a = new float[n * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                a[i + j * n] = sPositiveDefiniteMatrix[i + j * N];
            }
        }

        float[] a_expected = a.clone();
        float[] a_actual = a.clone();
        intW kb_expected = new intW(0);
        intW kb_actual = new intW(0);
        int[] ipiv_expected = new int[n];
        int[] ipiv_actual = new int[n];
        float[] w_expected = new float[n * nb];
        float[] w_actual = new float[n * nb];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.slasyf("U", n, nb, kb_expected, a_expected, n, ipiv_expected, w_expected, n, info_expected);
        assertEquals(0, info_expected.val);

        lapack.slasyf("U", n, nb, kb_actual, a_actual, n, ipiv_actual, w_actual, n, info_actual);
        assertEquals(0, info_actual.val);

        assertEquals(kb_expected.val, kb_actual.val);
        assertArrayEquals(ipiv_expected, ipiv_actual);
        assertArrayEquals(a_expected, a_actual, sepsilon);
    }
}
