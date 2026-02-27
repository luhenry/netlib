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

public class DlarrjTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test refinement of eigenvalue subset
        double[] d = generateDoubleArray(N_SMALL, 1.0);
        double[] e2 = generateDoubleArray(N_SMALL - 1, 0.01);
        double[] w = generateDoubleArray(N_SMALL, 1.0);
        double[] werr = new double[N_SMALL];
        double[] work = new double[2 * N_SMALL];
        int[] iwork = new int[2 * N_SMALL];
        double pivmin = 1e-10;
        double spdiam = 100.0;
        double rtol = 1e-8;

        intW info_expected = new intW(0);
        f2j.dlarrj(N_SMALL, d, 0, e2, 0, 1, N_SMALL, rtol, 0, w, 0, werr, 0, work, 0, iwork, 0, pivmin, spdiam, info_expected);

        intW info_actual = new intW(0);
        lapack.dlarrj(N_SMALL, d, 0, e2, 0, 1, N_SMALL, rtol, 0, w, 0, werr, 0, work, 0, iwork, 0, pivmin, spdiam, info_actual);

        assertEquals(info_expected.val, info_actual.val);
    }
}
