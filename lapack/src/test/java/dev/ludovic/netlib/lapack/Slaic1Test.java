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

public class Slaic1Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Incremental condition estimation: JOB=1 (largest singular value)
        int j = 3;
        float[] x = { 0.6f, 0.8f, 0.0f };
        float sest = 5.0f;
        float[] w = { 1.0f, 2.0f, 0.5f };
        float gamma = 3.0f;

        floatW sestpr_expected = new floatW(0.0f);
        floatW sestpr_actual = new floatW(0.0f);
        floatW s_expected = new floatW(0.0f);
        floatW s_actual = new floatW(0.0f);
        floatW c_expected = new floatW(0.0f);
        floatW c_actual = new floatW(0.0f);

        f2j.slaic1(1, j, x, sest, w, gamma, sestpr_expected, s_expected, c_expected);
        lapack.slaic1(1, j, x, sest, w, gamma, sestpr_actual, s_actual, c_actual);

        assertEquals(sestpr_expected.val, sestpr_actual.val, sepsilon);
        assertEquals(s_expected.val, s_actual.val, sepsilon);
        assertEquals(c_expected.val, c_actual.val, sepsilon);
    }
}
