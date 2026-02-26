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

public class DggbalTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        double[] a_expected = generateMatrix(n, n, 1.0);
        double[] a_actual = a_expected.clone();
        double[] b_expected = generateMatrix(n, n, 2.0);
        double[] b_actual = b_expected.clone();
        intW ilo_expected = new intW(0);
        intW ilo_actual = new intW(0);
        intW ihi_expected = new intW(0);
        intW ihi_actual = new intW(0);
        double[] lscale_expected = new double[n];
        double[] lscale_actual = new double[n];
        double[] rscale_expected = new double[n];
        double[] rscale_actual = new double[n];
        double[] work_expected = new double[6 * n];
        double[] work_actual = new double[6 * n];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dggbal("B", n, a_expected, n, b_expected, n, ilo_expected, ihi_expected,
                    lscale_expected, rscale_expected, work_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.dggbal("B", n, a_actual, n, b_actual, n, ilo_actual, ihi_actual,
                      lscale_actual, rscale_actual, work_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(a_expected, a_actual, depsilon);
        assertArrayEquals(b_expected, b_actual, depsilon);
        assertArrayEquals(lscale_expected, lscale_actual, depsilon);
        assertArrayEquals(rscale_expected, rscale_actual, depsilon);
        assertEquals(ilo_expected.val, ilo_actual.val);
        assertEquals(ihi_expected.val, ihi_actual.val);
    }
}
