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

public class DsbtrdTest extends LAPACKTest {

    // Pack a symmetric positive definite matrix into upper banded storage
    private double[] packUpperBanded(double[] matrix, int n, int kd) {
        int ldab = kd + 1;
        double[] ab = new double[ldab * n];
        for (int j = 0; j < n; j++) {
            for (int i = Math.max(0, j - kd); i <= j; i++) {
                ab[(kd + i - j) + j * ldab] = matrix[i + j * n];
            }
        }
        return ab;
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N;
        int kd = 3;
        int ldab = kd + 1;
        double[] ab = packUpperBanded(dPositiveDefiniteMatrix, n, kd);
        double[] d = new double[n];
        double[] e = new double[n - 1];
        double[] q = new double[n * n];
        double[] work = new double[n];
        intW info = new intW(0);

        lapack.dsbtrd("N", "U", n, kd, ab, 0, ldab, d, 0, e, 0, q, 0, n, work, 0, info);

        assertEquals(0, info.val, "dsbtrd should succeed");
    }
}
