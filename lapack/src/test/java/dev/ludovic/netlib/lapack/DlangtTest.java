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

import static dev.ludovic.netlib.test.TestHelpers.*;

public class DlangtTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Create tridiagonal matrix: dl (subdiagonal), d (diagonal), du (superdiagonal)
        double[] dl = new double[N - 1];
        double[] d = new double[N];
        double[] du = new double[N - 1];

        for (int i = 0; i < N; i++) {
            d[i] = (i + 1) * 2.0;
        }
        for (int i = 0; i < N - 1; i++) {
            dl[i] = -(i + 1);
            du[i] = (i + 1);
        }

        // Test 1-norm
        double expected = f2j.dlangt("1", N, dl, 0, d, 0, du, 0);
        double actual = lapack.dlangt("1", N, dl, 0, d, 0, du, 0);
        assertEquals(expected, actual, Math.scalb(depsilon, Math.getExponent(expected)));

        // Test Inf-norm
        expected = f2j.dlangt("I", N, dl, 0, d, 0, du, 0);
        actual = lapack.dlangt("I", N, dl, 0, d, 0, du, 0);
        assertEquals(expected, actual, Math.scalb(depsilon, Math.getExponent(expected)));

        // Test Frobenius norm
        expected = f2j.dlangt("F", N, dl, 0, d, 0, du, 0);
        actual = lapack.dlangt("F", N, dl, 0, d, 0, du, 0);
        assertEquals(expected, actual, Math.scalb(depsilon, Math.getExponent(expected)));

        // Test Max norm
        expected = f2j.dlangt("M", N, dl, 0, d, 0, du, 0);
        actual = lapack.dlangt("M", N, dl, 0, d, 0, du, 0);
        assertEquals(expected, actual, Math.scalb(depsilon, Math.getExponent(expected)));
    }
}
