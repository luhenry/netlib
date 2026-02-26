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

public class SgegsTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;

        float[] a_expected = generateMatrixFloat(n, n, 1.0f);
        float[] b_expected = generatePositiveDefiniteMatrixFloat(n);
        float[] alphar_expected = new float[n];
        float[] alphai_expected = new float[n];
        float[] beta_expected = new float[n];
        float[] vsl_expected = new float[n * n];
        float[] vsr_expected = new float[n * n];
        int lwork = Math.max(1, 8 * n + 16);
        float[] work_expected = new float[lwork];
        intW info_expected = new intW(0);

        f2j.sgegs("V", "V", n, a_expected, 0, n, b_expected, 0, n, alphar_expected, 0, alphai_expected, 0, beta_expected, 0, vsl_expected, 0, n, vsr_expected, 0, n, work_expected, 0, lwork, info_expected);
        assertEquals(0, info_expected.val, "Reference sgegs should succeed");

        float[] a_actual = generateMatrixFloat(n, n, 1.0f);
        float[] b_actual = generatePositiveDefiniteMatrixFloat(n);
        float[] alphar_actual = new float[n];
        float[] alphai_actual = new float[n];
        float[] beta_actual = new float[n];
        float[] vsl_actual = new float[n * n];
        float[] vsr_actual = new float[n * n];
        float[] work_actual = new float[lwork];
        intW info_actual = new intW(0);

        lapack.sgegs("V", "V", n, a_actual, 0, n, b_actual, 0, n, alphar_actual, 0, alphai_actual, 0, beta_actual, 0, vsl_actual, 0, n, vsr_actual, 0, n, work_actual, 0, lwork, info_actual);
        assertEquals(0, info_actual.val, "sgegs should succeed");

        assertRelArrayEquals(alphar_expected, alphar_actual, 0.1f);
        assertRelArrayEquals(alphai_expected, alphai_actual, 0.1f);
        assertRelArrayEquals(beta_expected, beta_actual, 0.1f);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testNoVectors(LAPACK lapack) {
        int n = N_SMALL;

        float[] a_expected = generateMatrixFloat(n, n, 1.0f);
        float[] b_expected = generatePositiveDefiniteMatrixFloat(n);
        float[] alphar_expected = new float[n];
        float[] alphai_expected = new float[n];
        float[] beta_expected = new float[n];
        float[] vsl_expected = new float[1];
        float[] vsr_expected = new float[1];
        int lwork = Math.max(1, 8 * n + 16);
        float[] work_expected = new float[lwork];
        intW info_expected = new intW(0);

        f2j.sgegs("N", "N", n, a_expected, 0, n, b_expected, 0, n, alphar_expected, 0, alphai_expected, 0, beta_expected, 0, vsl_expected, 0, 1, vsr_expected, 0, 1, work_expected, 0, lwork, info_expected);
        assertEquals(0, info_expected.val);

        float[] a_actual = generateMatrixFloat(n, n, 1.0f);
        float[] b_actual = generatePositiveDefiniteMatrixFloat(n);
        float[] alphar_actual = new float[n];
        float[] alphai_actual = new float[n];
        float[] beta_actual = new float[n];
        float[] vsl_actual = new float[1];
        float[] vsr_actual = new float[1];
        float[] work_actual = new float[lwork];
        intW info_actual = new intW(0);

        lapack.sgegs("N", "N", n, a_actual, 0, n, b_actual, 0, n, alphar_actual, 0, alphai_actual, 0, beta_actual, 0, vsl_actual, 0, 1, vsr_actual, 0, 1, work_actual, 0, lwork, info_actual);
        assertEquals(0, info_actual.val);

        assertRelArrayEquals(alphar_expected, alphar_actual, 0.1f);
        assertRelArrayEquals(alphai_expected, alphai_actual, 0.1f);
        assertRelArrayEquals(beta_expected, beta_actual, 0.1f);
    }
}
