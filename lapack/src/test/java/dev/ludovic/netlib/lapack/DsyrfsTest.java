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

public class DsyrfsTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testUpper(LAPACK lapack) {
        // DSYRFS: iterative refinement for symmetric indefinite system
        int n = N_SMALL;
        int nrhs = 1;

        // Create symmetric indefinite matrix
        double[] a = new double[n * n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                double val = (i == j) ? (i % 2 == 0 ? 10.0 : -5.0) : 1.0 / (i + j + 1.0);
                a[i + j * n] = val;
                a[j + i * n] = val;
            }
        }

        // Create RHS
        double[] b = generateDoubleArray(n, 1.0);

        // Factor
        double[] af = a.clone();
        int[] ipiv = new int[n];
        double[] work_f = new double[n * 64];
        intW info = new intW(0);
        f2j.dsytrf("U", n, af, n, ipiv, work_f, work_f.length, info);
        assertEquals(0, info.val);

        // Get initial solution with dsytrs
        double[] x = b.clone();
        info.val = 0;
        f2j.dsytrs("U", n, nrhs, af, n, ipiv, x, n, info);
        assertEquals(0, info.val);

        // Refine with dsyrfs
        double[] x_expected = x.clone();
        double[] x_actual = x.clone();
        double[] ferr_expected = new double[nrhs];
        double[] ferr_actual = new double[nrhs];
        double[] berr_expected = new double[nrhs];
        double[] berr_actual = new double[nrhs];
        double[] work_expected = new double[3 * n];
        double[] work_actual = new double[3 * n];
        int[] iwork_expected = new int[n];
        int[] iwork_actual = new int[n];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dsyrfs("U", n, nrhs, a, n, af, n, ipiv, b, n, x_expected, n, ferr_expected, berr_expected, work_expected, iwork_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.dsyrfs("U", n, nrhs, a, n, af, n, ipiv, b, n, x_actual, n, ferr_actual, berr_actual, work_actual, iwork_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(x_expected, x_actual, depsilon);
        assertArrayEquals(ferr_expected, ferr_actual, depsilon);
        assertArrayEquals(berr_expected, berr_actual, depsilon);
    }
}
