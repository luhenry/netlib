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

public class Slag2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test eigenvalues of 2x2 generalized problem
        float[] a = new float[]{1.0f, 2.0f, 3.0f, 4.0f};  // 2x2 matrix in column-major
        float[] b = new float[]{2.0f, 0.0f, 0.0f, 3.0f};  // 2x2 matrix in column-major
        floatW safmin = new floatW(1.1754944e-38f);
        floatW scale1_expected = new floatW(0.0f);
        floatW scale2_expected = new floatW(0.0f);
        floatW wr1_expected = new floatW(0.0f);
        floatW wr2_expected = new floatW(0.0f);
        floatW wi_expected = new floatW(0.0f);
        f2j.slag2(a.clone(), 0, 2, b.clone(), 0, 2, safmin.val, scale1_expected, scale2_expected, wr1_expected, wr2_expected, wi_expected);

        floatW scale1_actual = new floatW(0.0f);
        floatW scale2_actual = new floatW(0.0f);
        floatW wr1_actual = new floatW(0.0f);
        floatW wr2_actual = new floatW(0.0f);
        floatW wi_actual = new floatW(0.0f);
        lapack.slag2(a.clone(), 0, 2, b.clone(), 0, 2, safmin.val, scale1_actual, scale2_actual, wr1_actual, wr2_actual, wi_actual);

        assertEquals(scale1_expected.val, scale1_actual.val, sepsilon);
        assertEquals(scale2_expected.val, scale2_actual.val, sepsilon);
        assertEquals(wr1_expected.val, wr1_actual.val, sepsilon);
        assertEquals(wr2_expected.val, wr2_actual.val, sepsilon);
        assertEquals(wi_expected.val, wi_actual.val, sepsilon);
    }
}
