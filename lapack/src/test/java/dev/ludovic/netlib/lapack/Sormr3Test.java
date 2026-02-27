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

public class Sormr3Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;
        int k = Math.min(m, n);
        int l = k;

        // First, perform RZ factorization
        float[] a = generateMatrixFloat(m, n, 1.0f);
        float[] tau = new float[k];
        float[] work = new float[m];
        intW info = new intW(0);

        lapack.stzrzf(m, n, a, 0, m, tau, 0, work, 0, m, info);
        assertEquals(0, info.val, "RZ factorization should succeed");

        // Create a matrix C to apply Q to
        float[] c = generateMatrixFloat(m, n, 2.0f);

        // Apply Q from the left
        lapack.sormr3("L", "N", m, n, k, l, a, 0, m, tau, 0, c, 0, m, work, 0, info);
        assertEquals(0, info.val, "sormr3 should succeed");
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testCompareWithReference(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;
        int k = Math.min(m, n);
        int l = k;

        float[] a = generateMatrixFloat(m, n, 1.0f);
        float[] tau = new float[k];
        float[] work = new float[m];
        intW info = new intW(0);

        lapack.stzrzf(m, n, a, 0, m, tau, 0, work, 0, m, info);

        // Test implementation
        float[] c_test = generateMatrixFloat(m, n, 2.0f);
        lapack.sormr3("L", "N", m, n, k, l, a, 0, m, tau, 0, c_test, 0, m, work, 0, info);

        // Reference implementation
        float[] c_ref = generateMatrixFloat(m, n, 2.0f);
        f2j.sormr3("L", "N", m, n, k, l, a, 0, m, tau, 0, c_ref, 0, m, work, 0, info);

        assertArrayEquals(c_ref, c_test, Math.scalb(sepsilon, Math.getExponent(getMaxValue(c_ref)) + 4));
    }
}
