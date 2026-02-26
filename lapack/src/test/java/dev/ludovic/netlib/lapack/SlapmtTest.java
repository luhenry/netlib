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

public class SlapmtTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test forward column permutation
        int[] piv = new int[]{3, 1, 2};
        float[] expected = generateMatrixFloat(N, 3, 1.0f);
        f2j.slapmt(true, N, 3, expected, 0, N, piv, 0);

        float[] actual = generateMatrixFloat(N, 3, 1.0f);
        lapack.slapmt(true, N, 3, actual, 0, N, piv, 0);

        assertArrayEquals(expected, actual, sepsilon);

        // Test backward column permutation
        int[] piv2 = new int[]{2, 3, 1};
        float[] expected_back = generateMatrixFloat(N, 3, 1.0f);
        f2j.slapmt(false, N, 3, expected_back, 0, N, piv2, 0);

        float[] actual_back = generateMatrixFloat(N, 3, 1.0f);
        lapack.slapmt(false, N, 3, actual_back, 0, N, piv2, 0);

        assertArrayEquals(expected_back, actual_back, sepsilon);
    }
}
