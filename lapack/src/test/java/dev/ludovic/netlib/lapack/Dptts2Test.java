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

public class Dptts2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // DPTTS2 solves A*X=B using L*D*L^T factorization from DPTTRF
        // for a positive definite tridiagonal matrix
        int n = N_SMALL;

        // Create positive definite tridiagonal: diagonal dominant
        double[] d = new double[n]; // diagonal
        double[] e = new double[n - 1]; // off-diagonal
        for (int i = 0; i < n; i++) {
            d[i] = 4.0;
        }
        for (int i = 0; i < n - 1; i++) {
            e[i] = 1.0;
        }

        // Factor with dpttrf
        intW info = new intW(0);
        f2j.dpttrf(n, d, e, info);
        assertEquals(0, info.val);

        // Solve with dptts2
        double[] b_expected = generateDoubleArray(n, 1.0);
        double[] b_actual = b_expected.clone();

        f2j.dptts2(n, 1, d, e, b_expected, n);
        lapack.dptts2(n, 1, d, e, b_actual, n);

        assertArrayEquals(b_expected, b_actual, depsilon);
    }
}
