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

public class Slagv2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Generalized Schur factorization of 2x2 pencil (A,B) where B is upper triangular
        float[] a_expected = { 4.0f, 1.0f, 2.0f, 3.0f }; // 2x2 column-major
        float[] a_actual = a_expected.clone();
        float[] b_expected = { 5.0f, 0.0f, 1.0f, 2.0f }; // 2x2 upper triangular
        float[] b_actual = b_expected.clone();
        float[] alphar_expected = new float[2];
        float[] alphar_actual = new float[2];
        float[] alphai_expected = new float[2];
        float[] alphai_actual = new float[2];
        float[] beta_expected = new float[2];
        float[] beta_actual = new float[2];
        floatW csl_expected = new floatW(0.0f);
        floatW csl_actual = new floatW(0.0f);
        floatW snl_expected = new floatW(0.0f);
        floatW snl_actual = new floatW(0.0f);
        floatW csr_expected = new floatW(0.0f);
        floatW csr_actual = new floatW(0.0f);
        floatW snr_expected = new floatW(0.0f);
        floatW snr_actual = new floatW(0.0f);

        f2j.slagv2(a_expected, 2, b_expected, 2, alphar_expected, alphai_expected, beta_expected, csl_expected, snl_expected, csr_expected, snr_expected);
        lapack.slagv2(a_actual, 2, b_actual, 2, alphar_actual, alphai_actual, beta_actual, csl_actual, snl_actual, csr_actual, snr_actual);

        assertArrayEquals(a_expected, a_actual, sepsilon);
        assertArrayEquals(b_expected, b_actual, sepsilon);
        assertArrayEquals(alphar_expected, alphar_actual, sepsilon);
        assertArrayEquals(alphai_expected, alphai_actual, sepsilon);
        assertArrayEquals(beta_expected, beta_actual, sepsilon);
        assertEquals(csl_expected.val, csl_actual.val, sepsilon);
        assertEquals(snl_expected.val, snl_actual.val, sepsilon);
        assertEquals(csr_expected.val, csr_actual.val, sepsilon);
        assertEquals(snr_expected.val, snr_actual.val, sepsilon);
    }
}
