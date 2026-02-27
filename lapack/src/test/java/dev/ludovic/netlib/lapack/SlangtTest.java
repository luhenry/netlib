/*
 * Copyright 2020, 2022, Ludovic Henry
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
import static org.junit.jupiter.api.Assumptions.*;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class SlangtTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        float[] dl = new float[N - 1];
        float[] d = new float[N];
        float[] du = new float[N - 1];

        for (int i = 0; i < N; i++) {
            d[i] = (i + 1) * 2.0f;
        }
        for (int i = 0; i < N - 1; i++) {
            dl[i] = -(i + 1);
            du[i] = (i + 1);
        }

        // Test 1-norm
        float expected = f2j.slangt("1", N, dl, 0, d, 0, du, 0);
        float actual = lapack.slangt("1", N, dl, 0, d, 0, du, 0);
        assertEquals(expected, actual, Math.scalb(sepsilon, Math.getExponent(expected)));

        // Test Inf-norm
        expected = f2j.slangt("I", N, dl, 0, d, 0, du, 0);
        actual = lapack.slangt("I", N, dl, 0, d, 0, du, 0);
        assertEquals(expected, actual, Math.scalb(sepsilon, Math.getExponent(expected)));

        // Test Frobenius norm
        expected = f2j.slangt("F", N, dl, 0, d, 0, du, 0);
        actual = lapack.slangt("F", N, dl, 0, d, 0, du, 0);
        assertEquals(expected, actual, Math.scalb(sepsilon, Math.getExponent(expected)));

        // Test Max norm
        expected = f2j.slangt("M", N, dl, 0, d, 0, du, 0);
        actual = lapack.slangt("M", N, dl, 0, d, 0, du, 0);
        assertEquals(expected, actual, Math.scalb(sepsilon, Math.getExponent(expected)));
    }
}
