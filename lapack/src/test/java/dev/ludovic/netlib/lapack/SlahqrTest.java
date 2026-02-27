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

public class SlahqrTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Eigenvalues of upper Hessenberg matrix via QR iteration
        int n = 4;
        float[] h_expected = {
            4.0f, 1.0f, 0.0f, 0.0f,
            2.0f, 3.0f, 0.5f, 0.0f,
            0.0f, 1.0f, 5.0f, 0.3f,
            0.0f, 0.0f, 0.8f, 2.0f
        };
        float[] h_actual = h_expected.clone();
        float[] wr_expected = new float[n];
        float[] wr_actual = new float[n];
        float[] wi_expected = new float[n];
        float[] wi_actual = new float[n];
        float[] z = new float[1];
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.slahqr(false, false, n, 1, n, h_expected, n, wr_expected, wi_expected, 1, n, z, 1, info_expected);
        lapack.slahqr(false, false, n, 1, n, h_actual, n, wr_actual, wi_actual, 1, n, z, 1, info_actual);

        assertEquals(0, info_expected.val);
        assertEquals(info_expected.val, info_actual.val);
        java.util.Arrays.sort(wr_expected);
        java.util.Arrays.sort(wr_actual);
        assertArrayEquals(wr_expected, wr_actual, sepsilon);
    }
}
