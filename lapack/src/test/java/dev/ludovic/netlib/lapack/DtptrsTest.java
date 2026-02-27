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

public class DtptrsTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Create an upper triangular matrix in packed format
        // Packed storage: column by column for upper triangular
        int ap_size = N * (N + 1) / 2;
        double[] ap = new double[ap_size];
        int idx = 0;
        for (int j = 0; j < N; j++) {
            for (int i = 0; i <= j; i++) {
                ap[idx++] = (i == j) ? (N + 1.0) : (1.0 / (i + j + 2.0));
            }
        }

        // Create right-hand side B
        double[] b_expected = generateDoubleArray(N, 1.0);
        double[] b_actual = b_expected.clone();

        intW info_expected = new intW(0);
        f2j.dtptrs("U", "N", "N", N, 1, ap, 0, b_expected, 0, N, info_expected);

        intW info_actual = new intW(0);
        lapack.dtptrs("U", "N", "N", N, 1, ap, 0, b_actual, 0, N, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(b_expected, b_actual, Math.scalb(depsilon, Math.getExponent(getMaxValue(b_expected))));
    }
}
