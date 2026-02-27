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

public class SgghrdTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        int ilo = 1;
        int ihi = n;

        float[] a_expected = generateMatrixFloat(n, n, 1.0f);
        float[] b_expected = generatePositiveDefiniteMatrixFloat(n);
        // Initialize Q and Z as identity matrices for compq="I", compz="I"
        float[] q_expected = generateIdentityMatrixFloat(n);
        float[] z_expected = generateIdentityMatrixFloat(n);
        intW info_expected = new intW(0);

        f2j.sgghrd("I", "I", n, ilo, ihi, a_expected, 0, n, b_expected, 0, n, q_expected, 0, n, z_expected, 0, n, info_expected);
        assertEquals(0, info_expected.val, "Reference sgghrd should succeed");

        float[] a_actual = generateMatrixFloat(n, n, 1.0f);
        float[] b_actual = generatePositiveDefiniteMatrixFloat(n);
        float[] q_actual = generateIdentityMatrixFloat(n);
        float[] z_actual = generateIdentityMatrixFloat(n);
        intW info_actual = new intW(0);

        lapack.sgghrd("I", "I", n, ilo, ihi, a_actual, 0, n, b_actual, 0, n, q_actual, 0, n, z_actual, 0, n, info_actual);
        assertEquals(0, info_actual.val, "sgghrd should succeed");

        assertRelArrayEquals(a_expected, a_actual, 1000.0f);
        assertRelArrayEquals(b_expected, b_actual, 1000.0f);
        assertRelArrayEquals(q_expected, q_actual, 1000.0f);
        assertRelArrayEquals(z_expected, z_actual, 1000.0f);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testNoAccumulation(LAPACK lapack) {
        int n = N_SMALL;
        int ilo = 1;
        int ihi = n;

        float[] a_expected = generateMatrixFloat(n, n, 1.0f);
        float[] b_expected = generatePositiveDefiniteMatrixFloat(n);
        float[] q_expected = new float[1];
        float[] z_expected = new float[1];
        intW info_expected = new intW(0);

        f2j.sgghrd("N", "N", n, ilo, ihi, a_expected, 0, n, b_expected, 0, n, q_expected, 0, 1, z_expected, 0, 1, info_expected);
        assertEquals(0, info_expected.val);

        float[] a_actual = generateMatrixFloat(n, n, 1.0f);
        float[] b_actual = generatePositiveDefiniteMatrixFloat(n);
        float[] q_actual = new float[1];
        float[] z_actual = new float[1];
        intW info_actual = new intW(0);

        lapack.sgghrd("N", "N", n, ilo, ihi, a_actual, 0, n, b_actual, 0, n, q_actual, 0, 1, z_actual, 0, 1, info_actual);
        assertEquals(0, info_actual.val);

        assertRelArrayEquals(a_expected, a_actual, 1000.0f);
        assertRelArrayEquals(b_expected, b_actual, 1000.0f);
    }
}
