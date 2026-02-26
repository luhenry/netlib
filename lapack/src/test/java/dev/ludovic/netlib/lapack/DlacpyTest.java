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

public class DlacpyTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test copying full matrix
        double[] expected = new double[N * N];
        f2j.dlacpy("A", N, N, dMatrix, 0, N, expected, 0, N);

        double[] actual = new double[N * N];
        lapack.dlacpy("A", N, N, dMatrix, 0, N, actual, 0, N);

        assertArrayEquals(expected, actual, depsilon);

        // Test copying upper triangular part
        double[] expected_upper = new double[N * N];
        f2j.dlacpy("U", N, N, dMatrix, 0, N, expected_upper, 0, N);

        double[] actual_upper = new double[N * N];
        lapack.dlacpy("U", N, N, dMatrix, 0, N, actual_upper, 0, N);

        assertArrayEquals(expected_upper, actual_upper, depsilon);

        // Test copying lower triangular part
        double[] expected_lower = new double[N * N];
        f2j.dlacpy("L", N, N, dMatrix, 0, N, expected_lower, 0, N);

        double[] actual_lower = new double[N * N];
        lapack.dlacpy("L", N, N, dMatrix, 0, N, actual_lower, 0, N);

        assertArrayEquals(expected_lower, actual_lower, depsilon);
    }
}
