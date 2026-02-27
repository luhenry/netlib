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

public class DorgbrTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;

        // First, perform bidiagonal reduction
        double[] a = generateMatrix(m, n, 1.0);
        double[] d = new double[Math.min(m, n)];
        double[] e = new double[Math.min(m, n) - 1];
        double[] tauq = new double[Math.min(m, n)];
        double[] taup = new double[Math.min(m, n)];
        double[] work = new double[Math.max(m, n)];
        intW info = new intW(0);

        lapack.dgebrd(m, n, a, 0, m, d, 0, e, 0, tauq, 0, taup, 0, work, 0, Math.max(m, n), info);
        assertEquals(0, info.val, "Bidiagonal reduction should succeed");

        // Generate Q from bidiagonal reduction
        double[] q = a.clone();
        int k = Math.min(m, n);
        lapack.dorgbr("Q", m, k, n, q, 0, m, tauq, 0, work, 0, Math.max(m, n), info);
        assertEquals(0, info.val, "dorgbr should succeed");
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testCompareWithReference(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;

        double[] a = generateMatrix(m, n, 1.0);
        double[] d = new double[Math.min(m, n)];
        double[] e = new double[Math.min(m, n) - 1];
        double[] tauq = new double[Math.min(m, n)];
        double[] taup = new double[Math.min(m, n)];
        double[] work = new double[Math.max(m, n)];
        intW info = new intW(0);

        lapack.dgebrd(m, n, a, 0, m, d, 0, e, 0, tauq, 0, taup, 0, work, 0, Math.max(m, n), info);

        // Test implementation
        double[] q_test = a.clone();
        int k = Math.min(m, n);
        lapack.dorgbr("Q", m, k, n, q_test, 0, m, tauq, 0, work, 0, Math.max(m, n), info);

        // Reference implementation
        double[] q_ref = a.clone();
        f2j.dorgbr("Q", m, k, n, q_ref, 0, m, tauq, 0, work, 0, Math.max(m, n), info);

        assertArrayEquals(q_ref, q_test, Math.scalb(depsilon, Math.getExponent(getMaxValue(q_ref)) + 4));
    }
}
