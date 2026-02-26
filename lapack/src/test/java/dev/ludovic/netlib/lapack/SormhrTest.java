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

public class SormhrTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        int ilo = 1;
        int ihi = n;

        // First, perform Hessenberg reduction
        float[] a = sSymmetricMatrix.clone();
        float[] tau = new float[n - 1];
        float[] work = new float[n];
        intW info = new intW(0);

        lapack.sgehrd(n, ilo, ihi, a, 0, n, tau, 0, work, 0, n, info);
        assertEquals(0, info.val, "Hessenberg reduction should succeed");

        // Create a matrix C to apply Q to
        float[] c = generateMatrixFloat(n, n, 2.0f);

        // Apply Q from the left
        lapack.sormhr("L", "N", n, n, ilo, ihi, a, 0, n, tau, 0, c, 0, n, work, 0, n, info);
        assertEquals(0, info.val, "sormhr should succeed");
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testCompareWithReference(LAPACK lapack) {
        int n = N_SMALL;
        int ilo = 1;
        int ihi = n;

        float[] a = sSymmetricMatrix.clone();
        float[] tau = new float[n - 1];
        float[] work = new float[n];
        intW info = new intW(0);

        lapack.sgehrd(n, ilo, ihi, a, 0, n, tau, 0, work, 0, n, info);

        // Test implementation
        float[] c_test = generateMatrixFloat(n, n, 2.0f);
        lapack.sormhr("L", "N", n, n, ilo, ihi, a, 0, n, tau, 0, c_test, 0, n, work, 0, n, info);

        // Reference implementation
        float[] c_ref = generateMatrixFloat(n, n, 2.0f);
        f2j.sormhr("L", "N", n, n, ilo, ihi, a, 0, n, tau, 0, c_ref, 0, n, work, 0, n, info);

        assertArrayEquals(c_ref, c_test, sepsilon * n * n);
    }
}
