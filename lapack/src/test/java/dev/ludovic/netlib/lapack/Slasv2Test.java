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

public class Slasv2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test SVD of 2x2 general matrix
        // Computes SVD of [f, g; 0, h]
        floatW ssmin_expected = new floatW(0.0f);
        floatW ssmax_expected = new floatW(0.0f);
        floatW snr_expected = new floatW(0.0f);
        floatW csr_expected = new floatW(0.0f);
        floatW snl_expected = new floatW(0.0f);
        floatW csl_expected = new floatW(0.0f);
        f2j.slasv2(3.0f, 4.0f, 5.0f, ssmin_expected, ssmax_expected, snr_expected, csr_expected, snl_expected, csl_expected);

        floatW ssmin_actual = new floatW(0.0f);
        floatW ssmax_actual = new floatW(0.0f);
        floatW snr_actual = new floatW(0.0f);
        floatW csr_actual = new floatW(0.0f);
        floatW snl_actual = new floatW(0.0f);
        floatW csl_actual = new floatW(0.0f);
        lapack.slasv2(3.0f, 4.0f, 5.0f, ssmin_actual, ssmax_actual, snr_actual, csr_actual, snl_actual, csl_actual);

        assertEquals(ssmin_expected.val, ssmin_actual.val, sepsilon);
        assertEquals(ssmax_expected.val, ssmax_actual.val, sepsilon);
        assertEquals(snr_expected.val, snr_actual.val, sepsilon);
        assertEquals(csr_expected.val, csr_actual.val, sepsilon);
        assertEquals(snl_expected.val, snl_actual.val, sepsilon);
        assertEquals(csl_expected.val, csl_actual.val, sepsilon);
    }
}
