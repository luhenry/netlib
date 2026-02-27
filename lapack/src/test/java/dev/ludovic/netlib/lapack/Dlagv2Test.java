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

public class Dlagv2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Generalized Schur factorization of 2x2 pencil (A,B) where B is upper triangular
        double[] a_expected = { 4.0, 1.0, 2.0, 3.0 }; // 2x2 column-major
        double[] a_actual = a_expected.clone();
        double[] b_expected = { 5.0, 0.0, 1.0, 2.0 }; // 2x2 upper triangular
        double[] b_actual = b_expected.clone();
        double[] alphar_expected = new double[2];
        double[] alphar_actual = new double[2];
        double[] alphai_expected = new double[2];
        double[] alphai_actual = new double[2];
        double[] beta_expected = new double[2];
        double[] beta_actual = new double[2];
        doubleW csl_expected = new doubleW(0.0);
        doubleW csl_actual = new doubleW(0.0);
        doubleW snl_expected = new doubleW(0.0);
        doubleW snl_actual = new doubleW(0.0);
        doubleW csr_expected = new doubleW(0.0);
        doubleW csr_actual = new doubleW(0.0);
        doubleW snr_expected = new doubleW(0.0);
        doubleW snr_actual = new doubleW(0.0);

        f2j.dlagv2(a_expected, 2, b_expected, 2, alphar_expected, alphai_expected, beta_expected, csl_expected, snl_expected, csr_expected, snr_expected);
        lapack.dlagv2(a_actual, 2, b_actual, 2, alphar_actual, alphai_actual, beta_actual, csl_actual, snl_actual, csr_actual, snr_actual);

        assertArrayEquals(a_expected, a_actual, depsilon);
        assertArrayEquals(b_expected, b_actual, depsilon);
        assertArrayEquals(alphar_expected, alphar_actual, depsilon);
        assertArrayEquals(alphai_expected, alphai_actual, depsilon);
        assertArrayEquals(beta_expected, beta_actual, depsilon);
        assertEquals(csl_expected.val, csl_actual.val, depsilon);
        assertEquals(snl_expected.val, snl_actual.val, depsilon);
        assertEquals(csr_expected.val, csr_actual.val, depsilon);
        assertEquals(snr_expected.val, snr_actual.val, depsilon);
    }
}
