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

public class ShgeqzTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // QZ iteration on Hessenberg-Triangular pair, eigenvalues only
        int n = 4;
        float[] h_expected = {
            4.0f, 2.0f, 0.0f, 0.0f,
            1.0f, 3.0f, 1.0f, 0.0f,
            0.0f, 0.5f, 5.0f, 0.8f,
            0.0f, 0.0f, 0.3f, 2.0f
        };
        float[] h_actual = h_expected.clone();
        float[] t_expected = {
            2.0f, 0.0f, 0.0f, 0.0f,
            1.0f, 3.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 1.0f, 0.0f,
            0.0f, 0.0f, 0.5f, 4.0f
        };
        float[] t_actual = t_expected.clone();
        float[] alphar_expected = new float[n];
        float[] alphar_actual = new float[n];
        float[] alphai_expected = new float[n];
        float[] alphai_actual = new float[n];
        float[] beta_expected = new float[n];
        float[] beta_actual = new float[n];
        float[] q = new float[1];
        float[] z = new float[1];
        float[] work_expected = new float[n];
        float[] work_actual = new float[n];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.shgeqz("E", "N", "N", n, 1, n, h_expected, n, t_expected, n,
                    alphar_expected, alphai_expected, beta_expected, q, 1, z, 1,
                    work_expected, n, info_expected);
        lapack.shgeqz("E", "N", "N", n, 1, n, h_actual, n, t_actual, n,
                      alphar_actual, alphai_actual, beta_actual, q, 1, z, 1,
                      work_actual, n, info_actual);

        assertEquals(0, info_expected.val);
        assertEquals(info_expected.val, info_actual.val);

        float[] eig_expected = new float[n];
        float[] eig_actual = new float[n];
        for (int i = 0; i < n; i++) {
            eig_expected[i] = alphar_expected[i] / beta_expected[i];
            eig_actual[i] = alphar_actual[i] / beta_actual[i];
        }
        java.util.Arrays.sort(eig_expected);
        java.util.Arrays.sort(eig_actual);
        assertArrayEquals(eig_expected, eig_actual, sepsilon);
    }
}
