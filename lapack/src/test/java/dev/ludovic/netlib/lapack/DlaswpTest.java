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

public class DlaswpTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Verify that applying a permutation forward (incx=1) then backward (incx=-1)
        // gives back the original matrix.
        // Use ipiv = {2, 3, 3} (1-based) with k1=1, k2=3:
        //   Forward: swap row 1<->2, then row 2<->3, then row 3<->3
        //   Result: rows cycle [orig2, orig3, orig1, ...]
        int n = 5;
        int[] ipiv = new int[]{2, 3, 3};

        double[] a_orig = generateMatrix(n, n, 1.0);
        double[] a = a_orig.clone();

        // Apply forward permutation
        lapack.dlaswp(n, a, 0, n, 1, 3, ipiv, 0, 1);

        // Verify the matrix actually changed
        boolean changed = false;
        for (int i = 0; i < n * n; i++) {
            if (a[i] != a_orig[i]) { changed = true; break; }
        }
        assertTrue(changed, "dlaswp should modify the matrix");

        // Apply backward permutation (incx = -1) to undo
        lapack.dlaswp(n, a, 0, n, 1, 3, ipiv, 0, -1);

        // Should get back the original
        assertArrayEquals(a_orig, a, depsilon);
    }
}
