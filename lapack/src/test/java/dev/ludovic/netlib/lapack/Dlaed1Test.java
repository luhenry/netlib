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

public class Dlaed1Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // dlaed1 computes the updated eigensystem of a diagonal matrix after
        // modification by a rank-one symmetric matrix. It merges two subproblems.
        int n = 6;
        int cutpnt = 3;

        // D: eigenvalues sorted in two halves
        // D(1..CUTPNT) ascending, D(CUTPNT+1..N) ascending
        double[] d_expected = {1.0, 3.0, 5.0, 2.0, 4.0, 6.0};

        // Q: block-diagonal identity (eigenvectors of two subproblems)
        double[] q_expected = generateIdentityMatrix(n);

        // INDXQ: two LOCAL sort permutations (1-based)
        // Left half (1..CUTPNT): local perm sorting D(1..CUTPNT) ascending
        // Right half (CUTPNT+1..N): local perm sorting D(CUTPNT+1..N) ascending
        // Both halves are already ascending, so identity permutations {1,2,3}
        int[] indxq_expected = {1, 2, 3, 1, 2, 3};

        doubleW rho_expected = new doubleW(1.0);
        double[] work_expected = new double[4 * n + n * n];
        int[] iwork_expected = new int[4 * n];
        intW info_expected = new intW(0);

        f2j.dlaed1(n, d_expected, 0, q_expected, 0, n, indxq_expected, 0,
            rho_expected, cutpnt, work_expected, 0, iwork_expected, 0, info_expected);

        double[] d_actual = {1.0, 3.0, 5.0, 2.0, 4.0, 6.0};
        double[] q_actual = generateIdentityMatrix(n);
        int[] indxq_actual = {1, 2, 3, 1, 2, 3};
        doubleW rho_actual = new doubleW(1.0);
        double[] work_actual = new double[4 * n + n * n];
        int[] iwork_actual = new int[4 * n];
        intW info_actual = new intW(0);

        lapack.dlaed1(n, d_actual, 0, q_actual, 0, n, indxq_actual, 0,
            rho_actual, cutpnt, work_actual, 0, iwork_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(d_expected, d_actual, Math.scalb(depsilon, Math.getExponent(getMaxValue(d_expected))));
        assertArrayEquals(q_expected, q_actual, Math.scalb(depsilon, Math.getExponent(Math.max(getMaxValue(q_expected), 1.0))));
    }
}
