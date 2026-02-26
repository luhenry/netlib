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

public class DopgtrTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testUpper(LAPACK lapack) {
        // DOPGTR: generate orthogonal matrix Q from tridiagonal reduction by DSPTRD
        int n = N_SMALL;

        // Create symmetric packed matrix (upper) - positive definite for good conditioning
        double[] ap = generatePackedSymmetricMatrix(n, n + 10.0);

        // Reduce to tridiagonal form with dsptrd
        double[] d = new double[n];
        double[] e = new double[n - 1];
        double[] tau = new double[n - 1];
        intW info = new intW(0);
        f2j.dsptrd("U", n, ap, d, e, tau, info);
        assertEquals(0, info.val);

        // Generate Q from the reflectors
        double[] q_expected = new double[n * n];
        double[] q_actual = new double[n * n];
        double[] work_expected = new double[n - 1];
        double[] work_actual = new double[n - 1];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.dopgtr("U", n, ap, tau, q_expected, n, work_expected, info_expected);
        assertEquals(0, info_expected.val);

        lapack.dopgtr("U", n, ap, tau, q_actual, n, work_actual, info_actual);
        assertEquals(0, info_actual.val);

        assertArrayEquals(q_expected, q_actual, depsilon);
    }
}
