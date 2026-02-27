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

public class DlagtsTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // First factor with dlagtf, then solve with dlagts
        int n = 5;
        double[] a = { 4.0, 5.0, 6.0, 7.0, 8.0 };
        double lambda = 1.0;
        double[] b = { 1.0, 1.0, 1.0, 1.0 };
        double[] c = { 0.5, 0.5, 0.5, 0.5 };
        double[] d = new double[n - 2];
        int[] in = new int[n];
        intW info = new intW(0);

        f2j.dlagtf(n, a, lambda, b, c, 0.0, d, in, info);
        assertEquals(0, info.val);

        // Solve (T - lambda*I)*x = y with JOB=1
        double[] y_expected = { 1.0, 2.0, 3.0, 4.0, 5.0 };
        double[] y_actual = y_expected.clone();
        doubleW tol_expected = new doubleW(0.0);
        doubleW tol_actual = new doubleW(0.0);
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dlagts(1, n, a, b, c, d, in, y_expected, tol_expected, info_expected);
        lapack.dlagts(1, n, a, b, c, d, in, y_actual, tol_actual, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(y_expected, y_actual, depsilon);
    }
}
