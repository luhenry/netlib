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

public class DlaexcTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Swap two adjacent 1x1 diagonal blocks in upper quasi-triangular matrix
        int n = 4;
        // Upper triangular (quasi-triangular with 1x1 blocks)
        double[] t_expected = {
            5.0, 0.0, 0.0, 0.0,
            1.0, 3.0, 0.0, 0.0,
            2.0, 0.5, 7.0, 0.0,
            0.3, 0.4, 0.6, 2.0
        };
        double[] t_actual = t_expected.clone();
        double[] q_expected = new double[n * n];
        for (int i = 0; i < n; i++) q_expected[i + i * n] = 1.0;
        double[] q_actual = q_expected.clone();
        double[] work_expected = new double[n];
        double[] work_actual = new double[n];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        // Swap blocks at J1=1, N1=1, N2=1 (swap eigenvalues at positions 1 and 2)
        f2j.dlaexc(true, n, t_expected, n, q_expected, n, 1, 1, 1, work_expected, info_expected);
        lapack.dlaexc(true, n, t_actual, n, q_actual, n, 1, 1, 1, work_actual, info_actual);

        assertEquals(0, info_expected.val);
        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(t_expected, t_actual, depsilon);
        assertArrayEquals(q_expected, q_actual, depsilon);
    }
}
