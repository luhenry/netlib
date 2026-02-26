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

public class DlarrkTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test computing one eigenvalue by bisection
        double[] d = generateDoubleArray(N_SMALL, 1.0);
        double[] e2 = generateDoubleArray(N_SMALL - 1, 0.01);
        double gl = 0.0;
        double gu = 100.0;
        double pivmin = 1e-10;
        double reltol = 1e-8;

        doubleW w_expected = new doubleW(0.0);
        doubleW werr_expected = new doubleW(0.0);
        intW info_expected = new intW(0);
        f2j.dlarrk(N_SMALL, 1, gl, gu, d, 0, e2, 0, pivmin, reltol, w_expected, werr_expected, info_expected);

        doubleW w_actual = new doubleW(0.0);
        doubleW werr_actual = new doubleW(0.0);
        intW info_actual = new intW(0);
        lapack.dlarrk(N_SMALL, 1, gl, gu, d, 0, e2, 0, pivmin, reltol, w_actual, werr_actual, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertEquals(w_expected.val, w_actual.val, depsilon);
        assertEquals(werr_expected.val, werr_actual.val, depsilon);
    }
}
