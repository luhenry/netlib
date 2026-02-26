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

public class SlalsaTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // slalsa is the D&C SVD tree-traversal routine for multiple RHS.
        // It is internal to slalsd. We test it indirectly through slalsd
        // with a problem large enough to exercise the multi-level D&C tree.
        int n = 8;
        int nrhs = 2;
        int smlsiz = 3; // small smlsiz forces multi-level D&C (must be >= 3)

        float[] d_expected = new float[n];
        float[] e_expected = new float[n - 1];
        for (int i = 0; i < n; i++) {
            d_expected[i] = (i + 1) * 2.0f;
        }
        for (int i = 0; i < n - 1; i++) {
            e_expected[i] = 0.5f;
        }

        float[] b_expected = new float[n * nrhs];
        for (int j = 0; j < nrhs; j++) {
            for (int i = 0; i < n; i++) {
                b_expected[i + j * n] = (i + 1.0f) * (j + 1.0f);
            }
        }

        float rcond = -1.0f;
        intW rank_expected = new intW(0);

        int nlvl = (int) Math.ceil(Math.log((double) n / (smlsiz + 1)) / Math.log(2.0)) + 1;
        nlvl = Math.max(nlvl, 1);
        float[] work_expected = new float[9 * n + 2 * n * smlsiz + 8 * n * nlvl + n * nrhs + (smlsiz + 1) * (smlsiz + 1)];
        int[] iwork_expected = new int[3 * n * nlvl + 11 * n];
        intW info_expected = new intW(0);

        f2j.slalsd("U", smlsiz, n, nrhs, d_expected, 0, e_expected, 0,
            b_expected, 0, n, rcond, rank_expected,
            work_expected, 0, iwork_expected, 0, info_expected);

        float[] d_actual = new float[n];
        float[] e_actual = new float[n - 1];
        for (int i = 0; i < n; i++) {
            d_actual[i] = (i + 1) * 2.0f;
        }
        for (int i = 0; i < n - 1; i++) {
            e_actual[i] = 0.5f;
        }

        float[] b_actual = new float[n * nrhs];
        for (int j = 0; j < nrhs; j++) {
            for (int i = 0; i < n; i++) {
                b_actual[i + j * n] = (i + 1.0f) * (j + 1.0f);
            }
        }

        intW rank_actual = new intW(0);
        float[] work_actual = new float[9 * n + 2 * n * smlsiz + 8 * n * nlvl + n * nrhs + (smlsiz + 1) * (smlsiz + 1)];
        int[] iwork_actual = new int[3 * n * nlvl + 11 * n];
        intW info_actual = new intW(0);

        lapack.slalsd("U", smlsiz, n, nrhs, d_actual, 0, e_actual, 0,
            b_actual, 0, n, rcond, rank_actual,
            work_actual, 0, iwork_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertEquals(rank_expected.val, rank_actual.val);
        assertArrayEquals(d_expected, d_actual, Math.scalb(sepsilon, Math.getExponent(getMaxValue(d_expected))));
        assertArrayEquals(b_expected, b_actual, Math.scalb(sepsilon, Math.getExponent(Math.max(getMaxValue(b_expected), 1.0f))));
    }
}
