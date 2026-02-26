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

public class Dlaln2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Solve (ca*A - wr*D)*X = scale*B with 2x2 real system
        int na = 2;
        int nw = 1;
        double smin = 1e-10;
        double ca = 1.0;
        double[] a = { 4.0, 0.5, 0.3, 3.0 }; // 2x2 column-major
        double d1 = 1.0;
        double d2 = 1.0;
        double[] b = { 1.0, 2.0 }; // 2x1 RHS
        double wr = 0.5;
        double wi = 0.0;

        double[] x_expected = new double[2];
        double[] x_actual = new double[2];
        doubleW scale_expected = new doubleW(0.0);
        doubleW scale_actual = new doubleW(0.0);
        doubleW xnorm_expected = new doubleW(0.0);
        doubleW xnorm_actual = new doubleW(0.0);
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dlaln2(false, na, nw, smin, ca, a, 2, d1, d2, b, 2, wr, wi, x_expected, 2, scale_expected, xnorm_expected, info_expected);
        lapack.dlaln2(false, na, nw, smin, ca, a, 2, d1, d2, b, 2, wr, wi, x_actual, 2, scale_actual, xnorm_actual, info_actual);

        assertEquals(scale_expected.val, scale_actual.val, depsilon);
        assertArrayEquals(x_expected, x_actual, depsilon);
        assertEquals(xnorm_expected.val, xnorm_actual.val, depsilon);
    }
}
