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

public class DlaconTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;

        // dlacon estimates the 1-norm using a reverse communication interface
        double[] v_expected = new double[n];
        double[] x_expected = new double[n];
        int[] isgn_expected = new int[n];
        doubleW est_expected = new doubleW(0.0);
        intW kase_expected = new intW(0);

        double[] v_actual = new double[n];
        double[] x_actual = new double[n];
        int[] isgn_actual = new int[n];
        doubleW est_actual = new doubleW(0.0);
        intW kase_actual = new intW(0);

        // First call to start the iteration
        f2j.dlacon(n, v_expected, 0, x_expected, 0, isgn_expected, 0, est_expected, kase_expected);
        lapack.dlacon(n, v_actual, 0, x_actual, 0, isgn_actual, 0, est_actual, kase_actual);

        assertEquals(kase_expected.val, kase_actual.val);
        assertArrayEquals(x_expected, x_actual, depsilon);
    }
}
