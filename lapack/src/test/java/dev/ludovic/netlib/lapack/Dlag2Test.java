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

public class Dlag2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test eigenvalues of 2x2 generalized problem
        double[] a = new double[]{1.0, 2.0, 3.0, 4.0};  // 2x2 matrix in column-major
        double[] b = new double[]{2.0, 0.0, 0.0, 3.0};  // 2x2 matrix in column-major
        doubleW safmin = new doubleW(2.2250738585072014e-308);
        doubleW scale1_expected = new doubleW(0.0);
        doubleW scale2_expected = new doubleW(0.0);
        doubleW wr1_expected = new doubleW(0.0);
        doubleW wr2_expected = new doubleW(0.0);
        doubleW wi_expected = new doubleW(0.0);
        f2j.dlag2(a.clone(), 0, 2, b.clone(), 0, 2, safmin.val, scale1_expected, scale2_expected, wr1_expected, wr2_expected, wi_expected);

        doubleW scale1_actual = new doubleW(0.0);
        doubleW scale2_actual = new doubleW(0.0);
        doubleW wr1_actual = new doubleW(0.0);
        doubleW wr2_actual = new doubleW(0.0);
        doubleW wi_actual = new doubleW(0.0);
        lapack.dlag2(a.clone(), 0, 2, b.clone(), 0, 2, safmin.val, scale1_actual, scale2_actual, wr1_actual, wr2_actual, wi_actual);

        assertEquals(scale1_expected.val, scale1_actual.val, depsilon);
        assertEquals(scale2_expected.val, scale2_actual.val, depsilon);
        assertEquals(wr1_expected.val, wr1_actual.val, depsilon);
        assertEquals(wr2_expected.val, wr2_actual.val, depsilon);
        assertEquals(wi_expected.val, wi_actual.val, depsilon);
    }
}
