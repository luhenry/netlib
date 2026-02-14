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

public class DlaswpTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test row swapping with forward increment
        int[] ipiv = new int[]{2, 1, 3};
        double[] expected = dMatrix.clone();
        f2j.dlaswp(N, expected, 0, N, 1, 3, ipiv, 0, 1);

        double[] actual = dMatrix.clone();
        lapack.dlaswp(N, actual, 0, N, 1, 3, ipiv, 0, 1);

        assertArrayEquals(expected, actual, depsilon);

        // Test row swapping with backward increment
        int[] ipiv2 = new int[]{3, 2, 1};
        double[] expected_back = dMatrix.clone();
        f2j.dlaswp(N, expected_back, 0, N, 1, 3, ipiv2, 0, -1);

        double[] actual_back = dMatrix.clone();
        lapack.dlaswp(N, actual_back, 0, N, 1, 3, ipiv2, 0, -1);

        assertArrayEquals(expected_back, actual_back, depsilon);
    }
}
