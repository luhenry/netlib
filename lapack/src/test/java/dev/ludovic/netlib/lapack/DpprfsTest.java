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

public class DpprfsTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int ap_size = N * (N + 1) / 2;
        double[] ap_orig = new double[ap_size];
        double[] afp_expected = new double[ap_size];
        double[] afp_actual = new double[ap_size];
        int idx = 0;
        for (int j = 0; j < N; j++) {
            for (int i = 0; i <= j; i++) {
                double value = (i == j) ? (N + 1.0) : (1.0 / (i + j + 2.0));
                ap_orig[idx] = value;
                afp_expected[idx] = value;
                afp_actual[idx] = value;
                idx++;
            }
        }
        intW info = new intW(0);
        f2j.dpptrf("U", N, afp_expected, 0, info);
        assertEquals(0, info.val);
        info.val = 0;
        lapack.dpptrf("U", N, afp_actual, 0, info);
        assertEquals(0, info.val);
        double[] b = generateDoubleArray(N, 1.0);
        double[] x_expected = b.clone();
        double[] x_actual = b.clone();
        info.val = 0;
        f2j.dpptrs("U", N, 1, afp_expected, 0, x_expected, 0, N, info);
        assertEquals(0, info.val);
        info.val = 0;
        lapack.dpptrs("U", N, 1, afp_actual, 0, x_actual, 0, N, info);
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
        f2j.dpprfs("U", N, 1, ap_orig, 0, afp_expected, 0, b, 0, N, x_expected, 0, N, ferr_expected, 0, berr_expected, 0, work_expected, 0, iwork_expected, 0, info);
        assertEquals(0, info.val);
        info.val = 0;
        lapack.dpprfs("U", N, 1, ap_orig, 0, afp_actual, 0, b, 0, N, x_actual, 0, N, ferr_actual, 0, berr_actual, 0, work_actual, 0, iwork_actual, 0, info);
        assertEquals(0, info.val);
        assertArrayEquals(x_expected, x_actual, Math.scalb(depsilon, Math.getExponent(getMaxValue(x_expected))));
        // FERR is an error estimate that can vary between implementations
        assertArrayEquals(ferr_expected, ferr_actual, Math.max(depsilon, Math.abs(getMaxValue(ferr_expected)) * 5));
        // BERR should be near machine epsilon, but implementations can vary by small factors
        assertArrayEquals(berr_expected, berr_actual, depsilon * 10);
    }
}
