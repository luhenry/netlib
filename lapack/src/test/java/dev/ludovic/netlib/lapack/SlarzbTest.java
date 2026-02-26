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

import static dev.ludovic.netlib.test.TestHelpers.*;

public class SlarzbTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Apply block reflector from the left with DIRECT='B', STOREV='R'
        int m = 6;
        int n = 4;
        int k = 2; // number of reflectors
        int l = 3; // meaningful columns in V
        // V is k-by-l stored row-wise (ldv=k)
        float[] v = { 0.5f, 0.3f, 0.7f, 0.2f, 0.4f, 0.6f }; // k=2 rows, l=3 cols, column-major
        int ldv = k;
        // T is k-by-k upper triangular
        float[] t = { 1.5f, 0.0f, 0.3f, 1.2f }; // 2x2 column-major
        int ldt = k;
        float[] c_expected = generateMatrixFloat(m, n, 1.0f);
        float[] c_actual = c_expected.clone();
        float[] work_expected = new float[n * k];
        float[] work_actual = new float[n * k];

        f2j.slarzb("L", "N", "B", "R", m, n, k, l, v, ldv, t, ldt, c_expected, m, work_expected, n);
        lapack.slarzb("L", "N", "B", "R", m, n, k, l, v, ldv, t, ldt, c_actual, m, work_actual, n);

        assertArrayEquals(c_expected, c_actual, sepsilon);
    }
}
