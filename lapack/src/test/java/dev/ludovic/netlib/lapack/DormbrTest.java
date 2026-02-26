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

public class DormbrTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;
        int k = Math.min(m, n);

        // First, perform bidiagonal reduction
        double[] a = generateMatrix(m, n, 1.0);
        double[] d = new double[k];
        double[] e = new double[k - 1];
        double[] tauq = new double[k];
        double[] taup = new double[k];
        double[] work = new double[Math.max(m, n)];
        intW info = new intW(0);

        lapack.dgebrd(m, n, a, 0, m, d, 0, e, 0, tauq, 0, taup, 0, work, 0, Math.max(m, n), info);
        assertEquals(0, info.val, "Bidiagonal reduction should succeed");

        // Create a matrix C to apply Q to
        double[] c = generateMatrix(m, n, 2.0);

        // Apply Q from the left
        lapack.dormbr("Q", "L", "N", m, n, k, a, 0, m, tauq, 0, c, 0, m, work, 0, Math.max(m, n), info);
        assertEquals(0, info.val, "dormbr should succeed");
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testCompareWithReference(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;
        int k = Math.min(m, n);

        double[] a = generateMatrix(m, n, 1.0);
        double[] d = new double[k];
        double[] e = new double[k - 1];
        double[] tauq = new double[k];
        double[] taup = new double[k];
        double[] work = new double[Math.max(m, n)];
        intW info = new intW(0);

        lapack.dgebrd(m, n, a, 0, m, d, 0, e, 0, tauq, 0, taup, 0, work, 0, Math.max(m, n), info);

        // Test implementation
        double[] c_test = generateMatrix(m, n, 2.0);
        lapack.dormbr("Q", "L", "N", m, n, k, a, 0, m, tauq, 0, c_test, 0, m, work, 0, Math.max(m, n), info);

        // Reference implementation
        double[] c_ref = generateMatrix(m, n, 2.0);
        f2j.dormbr("Q", "L", "N", m, n, k, a, 0, m, tauq, 0, c_ref, 0, m, work, 0, Math.max(m, n), info);

        assertArrayEquals(c_ref, c_test, Math.scalb(depsilon, Math.getExponent(getMaxValue(c_ref)) + 4));
    }
}
