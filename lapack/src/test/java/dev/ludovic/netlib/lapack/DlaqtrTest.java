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

public class DlaqtrTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;

        // Upper triangular matrix with distinct diagonal entries
        double[] t = generateUpperTriangularMatrix(n, 1.0, 1.0, 0.5);

        // LREAL=true: solve T*x = scale*c (real quasi-triangular system)
        // B is not referenced when LREAL=true
        double[] b = new double[n];
        double w = 0.0;

        // RHS vector in X (size 2*n, but only first n used for LREAL=true)
        double[] x = new double[2 * n];
        for (int i = 0; i < n; i++) {
            x[i] = 1.0 / (i + 1.0);
        }

        double[] x_expected = x.clone();
        double[] x_actual = x.clone();
        double[] work_expected = new double[n];
        double[] work_actual = new double[n];
        doubleW scale_expected = new doubleW(0.0);
        doubleW scale_actual = new doubleW(0.0);
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dlaqtr(false, true, n, t, n, b, w, scale_expected, x_expected, work_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.dlaqtr(false, true, n, t, n, b, w, scale_actual, x_actual, work_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertEquals(scale_expected.val, scale_actual.val, depsilon);
        assertArrayEquals(x_expected, x_actual, depsilon);
    }
}
