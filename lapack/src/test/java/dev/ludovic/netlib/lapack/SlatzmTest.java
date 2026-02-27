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

public class SlatzmTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Apply Householder matrix P from the left: P * [C1; C2]
        // C1 and C2 are parts of the same m-by-n matrix C with leading dimension m
        // C1 = C(1,:) starts at offset 0, C2 = C(2:m,:) starts at offset 1
        int m = 5;
        int n = 3;
        float[] v = { 0.5f, 0.3f, 0.7f, 0.2f }; // (m-1)-vector
        float tau = 1.5f;
        float[] c_expected = generateMatrixFloat(m, n, 1.0f);
        float[] c_actual = c_expected.clone();
        float[] work_expected = new float[n];
        float[] work_actual = new float[n];

        f2j.slatzm("L", m, n, v, 0, 1, tau, c_expected, 0, c_expected, 1, m, work_expected, 0);
        lapack.slatzm("L", m, n, v, 0, 1, tau, c_actual, 0, c_actual, 1, m, work_actual, 0);

        assertArrayEquals(c_expected, c_actual, sepsilon);
    }
}
