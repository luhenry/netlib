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

public class SlansyTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        float[] work = new float[N];

        // Test 1-norm (upper triangular)
        float expected = f2j.slansy("1", "U", N, sSymmetricMatrix, 0, N, work, 0);
        float actual = lapack.slansy("1", "U", N, sSymmetricMatrix, 0, N, work, 0);
        assertEquals(expected, actual, Math.scalb(sepsilon, Math.getExponent(expected)));

        // Test Inf-norm (lower triangular)
        expected = f2j.slansy("I", "L", N, sSymmetricMatrix, 0, N, work, 0);
        actual = lapack.slansy("I", "L", N, sSymmetricMatrix, 0, N, work, 0);
        assertEquals(expected, actual, Math.scalb(sepsilon, Math.getExponent(expected)));

        // Test Frobenius norm
        expected = f2j.slansy("F", "U", N, sSymmetricMatrix, 0, N, work, 0);
        actual = lapack.slansy("F", "U", N, sSymmetricMatrix, 0, N, work, 0);
        assertEquals(expected, actual, Math.scalb(sepsilon, Math.getExponent(expected)));

        // Test Max norm
        expected = f2j.slansy("M", "U", N, sSymmetricMatrix, 0, N, work, 0);
        actual = lapack.slansy("M", "U", N, sSymmetricMatrix, 0, N, work, 0);
        assertEquals(expected, actual, Math.scalb(sepsilon, Math.getExponent(expected)));
    }
}
