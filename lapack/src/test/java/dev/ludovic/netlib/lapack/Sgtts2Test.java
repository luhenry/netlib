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

public class Sgtts2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testNoTranspose(LAPACK lapack) {
        int n = N_SMALL;
        float[] dl = new float[n - 1];
        float[] d = new float[n];
        float[] du = new float[n - 1];
        float[] du2 = new float[n - 2];
        for (int i = 0; i < n; i++) {
            d[i] = 10.0f;
        }
        for (int i = 0; i < n - 1; i++) {
            dl[i] = 1.0f;
            du[i] = 1.0f;
        }

        int[] ipiv = new int[n];
        intW info = new intW(0);
        f2j.sgttrf(n, dl, d, du, du2, ipiv, info);
        assertEquals(0, info.val);

        float[] b_expected = generateFloatArray(n, 1.0f);
        float[] b_actual = b_expected.clone();

        f2j.sgtts2(0, n, 1, dl, d, du, du2, ipiv, b_expected, n);
        lapack.sgtts2(0, n, 1, dl, d, du, du2, ipiv, b_actual, n);

        assertArrayEquals(b_expected, b_actual, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testTranspose(LAPACK lapack) {
        int n = N_SMALL;
        float[] dl = new float[n - 1];
        float[] d = new float[n];
        float[] du = new float[n - 1];
        float[] du2 = new float[n - 2];
        for (int i = 0; i < n; i++) {
            d[i] = 10.0f;
        }
        for (int i = 0; i < n - 1; i++) {
            dl[i] = 1.0f;
            du[i] = 1.0f;
        }

        int[] ipiv = new int[n];
        intW info = new intW(0);
        f2j.sgttrf(n, dl, d, du, du2, ipiv, info);
        assertEquals(0, info.val);

        float[] b_expected = generateFloatArray(n, 1.0f);
        float[] b_actual = b_expected.clone();

        f2j.sgtts2(1, n, 1, dl, d, du, du2, ipiv, b_expected, n);
        lapack.sgtts2(1, n, 1, dl, d, du, du2, ipiv, b_actual, n);

        assertArrayEquals(b_expected, b_actual, sepsilon);
    }
}
