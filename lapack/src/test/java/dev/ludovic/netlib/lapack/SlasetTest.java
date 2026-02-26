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

public class SlasetTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test setting full matrix with different diagonal and off-diagonal values
        float[] expected = new float[N * N];
        f2j.slaset("A", N, N, 1.0f, 2.0f, expected, 0, N);

        float[] actual = new float[N * N];
        lapack.slaset("A", N, N, 1.0f, 2.0f, actual, 0, N);

        assertArrayEquals(expected, actual, sepsilon);

        // Test setting upper triangular part
        float[] expected_upper = new float[N * N];
        f2j.slaset("U", N, N, 3.0f, 4.0f, expected_upper, 0, N);

        float[] actual_upper = new float[N * N];
        lapack.slaset("U", N, N, 3.0f, 4.0f, actual_upper, 0, N);

        assertArrayEquals(expected_upper, actual_upper, sepsilon);

        // Test setting lower triangular part
        float[] expected_lower = new float[N * N];
        f2j.slaset("L", N, N, 5.0f, 6.0f, expected_lower, 0, N);

        float[] actual_lower = new float[N * N];
        lapack.slaset("L", N, N, 5.0f, 6.0f, actual_lower, 0, N);

        assertArrayEquals(expected_lower, actual_lower, sepsilon);
    }
}
