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

public class Dlasv2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test SVD of 2x2 general matrix
        // Computes SVD of [f, g; 0, h]
        doubleW ssmin_expected = new doubleW(0.0);
        doubleW ssmax_expected = new doubleW(0.0);
        doubleW snr_expected = new doubleW(0.0);
        doubleW csr_expected = new doubleW(0.0);
        doubleW snl_expected = new doubleW(0.0);
        doubleW csl_expected = new doubleW(0.0);
        f2j.dlasv2(3.0, 4.0, 5.0, ssmin_expected, ssmax_expected, snr_expected, csr_expected, snl_expected, csl_expected);

        doubleW ssmin_actual = new doubleW(0.0);
        doubleW ssmax_actual = new doubleW(0.0);
        doubleW snr_actual = new doubleW(0.0);
        doubleW csr_actual = new doubleW(0.0);
        doubleW snl_actual = new doubleW(0.0);
        doubleW csl_actual = new doubleW(0.0);
        lapack.dlasv2(3.0, 4.0, 5.0, ssmin_actual, ssmax_actual, snr_actual, csr_actual, snl_actual, csl_actual);

        assertEquals(ssmin_expected.val, ssmin_actual.val, depsilon);
        assertEquals(ssmax_expected.val, ssmax_actual.val, depsilon);
        assertEquals(snr_expected.val, snr_actual.val, depsilon);
        assertEquals(csr_expected.val, csr_actual.val, depsilon);
        assertEquals(snl_expected.val, snl_actual.val, depsilon);
        assertEquals(csl_expected.val, csl_actual.val, depsilon);
    }
}
