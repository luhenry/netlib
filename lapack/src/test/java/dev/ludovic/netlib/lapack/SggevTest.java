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

public class SggevTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Compute generalized eigenvalues of (A, B), no eigenvectors
        int n = 4;
        float[] a_expected = {
            4.0f, 1.0f, 0.0f, 0.5f,
            2.0f, 3.0f, 1.0f, 0.0f,
            0.0f, 0.5f, 5.0f, 0.3f,
            0.3f, 0.0f, 0.2f, 2.0f
        };
        float[] a_actual = a_expected.clone();
        float[] b_expected = {
            3.0f, 0.0f, 0.0f, 0.0f,
            1.0f, 2.0f, 0.0f, 0.0f,
            0.5f, 0.3f, 4.0f, 0.0f,
            0.2f, 0.1f, 0.5f, 1.0f
        };
        float[] b_actual = b_expected.clone();
        float[] alphar_expected = new float[n];
        float[] alphar_actual = new float[n];
        float[] alphai_expected = new float[n];
        float[] alphai_actual = new float[n];
        float[] beta_expected = new float[n];
        float[] beta_actual = new float[n];
        float[] vl = new float[1];
        float[] vr = new float[1];
        int lwork = 8 * n;
        float[] work_expected = new float[lwork];
        float[] work_actual = new float[lwork];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.sggev("N", "N", n, a_expected, n, b_expected, n,
                  alphar_expected, alphai_expected, beta_expected,
                  vl, 1, vr, 1, work_expected, lwork, info_expected);
        lapack.sggev("N", "N", n, a_actual, n, b_actual, n,
                     alphar_actual, alphai_actual, beta_actual,
                     vl, 1, vr, 1, work_actual, lwork, info_actual);

        assertEquals(0, info_expected.val);
        assertEquals(info_expected.val, info_actual.val);

        float[] eig_expected = new float[n];
        float[] eig_actual = new float[n];
        for (int i = 0; i < n; i++) {
            eig_expected[i] = alphar_expected[i] / beta_expected[i];
            eig_actual[i] = alphar_actual[i] / beta_actual[i];
        }
        java.util.Arrays.sort(eig_expected);
        java.util.Arrays.sort(eig_actual);
        assertArrayEquals(eig_expected, eig_actual, sepsilon);
    }
}
