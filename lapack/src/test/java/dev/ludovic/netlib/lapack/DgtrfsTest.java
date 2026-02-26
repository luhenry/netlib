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

public class DgtrfsTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        double[] dl_orig = generateDoubleArray(N - 1, 0.5);
        double[] dl_expected = dl_orig.clone();
        double[] dl_actual = dl_orig.clone();
        double[] d_orig = generateDoubleArray(N, 2.0);
        double[] d_expected = d_orig.clone();
        double[] d_actual = d_orig.clone();
        double[] du_orig = generateDoubleArray(N - 1, 0.5);
        double[] du_expected = du_orig.clone();
        double[] du_actual = du_orig.clone();
        double[] du2_expected = new double[N - 2];
        double[] du2_actual = new double[N - 2];
        int[] ipiv_expected = new int[N];
        int[] ipiv_actual = new int[N];
        intW info = new intW(0);
        f2j.dgttrf(N, dl_expected, 0, d_expected, 0, du_expected, 0, du2_expected, 0, ipiv_expected, 0, info);
        assertEquals(0, info.val);
        info.val = 0;
        lapack.dgttrf(N, dl_actual, 0, d_actual, 0, du_actual, 0, du2_actual, 0, ipiv_actual, 0, info);
        assertEquals(0, info.val);
        double[] b = generateDoubleArray(N, 1.0);
        double[] x_expected = b.clone();
        double[] x_actual = b.clone();
        info.val = 0;
        f2j.dgttrs("N", N, 1, dl_expected, 0, d_expected, 0, du_expected, 0, du2_expected, 0, ipiv_expected, 0, x_expected, 0, N, info);
        assertEquals(0, info.val);
        info.val = 0;
        lapack.dgttrs("N", N, 1, dl_actual, 0, d_actual, 0, du_actual, 0, du2_actual, 0, ipiv_actual, 0, x_actual, 0, N, info);
        assertEquals(0, info.val);
        double[] ferr_expected = new double[1];
        double[] ferr_actual = new double[1];
        double[] berr_expected = new double[1];
        double[] berr_actual = new double[1];
        double[] work_expected = new double[3 * N];
        double[] work_actual = new double[3 * N];
        int[] iwork_expected = new int[N];
        int[] iwork_actual = new int[N];
        info.val = 0;
        f2j.dgtrfs("N", N, 1, dl_orig, 0, d_orig, 0, du_orig, 0, dl_expected, 0, d_expected, 0, du_expected, 0, du2_expected, 0, ipiv_expected, 0, b, 0, N, x_expected, 0, N, ferr_expected, 0, berr_expected, 0, work_expected, 0, iwork_expected, 0, info);
        assertEquals(0, info.val);
        info.val = 0;
        lapack.dgtrfs("N", N, 1, dl_orig, 0, d_orig, 0, du_orig, 0, dl_actual, 0, d_actual, 0, du_actual, 0, du2_actual, 0, ipiv_actual, 0, b, 0, N, x_actual, 0, N, ferr_actual, 0, berr_actual, 0, work_actual, 0, iwork_actual, 0, info);
        assertEquals(0, info.val);
        // Refined solution comparison
        assertArrayEquals(x_expected, x_actual, Math.scalb(depsilon, Math.getExponent(getMaxValue(x_expected)) + 1));
        // FERR: forward error estimate (can vary between implementations)
        assertArrayEquals(ferr_expected, ferr_actual, Math.max(depsilon, Math.abs(getMaxValue(ferr_expected)) * 5));
        // BERR: backward error (should be near machine epsilon)
        assertArrayEquals(berr_expected, berr_actual, depsilon * 10);
    }
}
