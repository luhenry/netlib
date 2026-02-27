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

public class Slaln2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Solve (ca*A - wr*D)*X = scale*B with 2x2 real system
        int na = 2;
        int nw = 1;
        float smin = 1e-5f;
        float ca = 1.0f;
        float[] a = { 4.0f, 0.5f, 0.3f, 3.0f }; // 2x2 column-major
        float d1 = 1.0f;
        float d2 = 1.0f;
        float[] b = { 1.0f, 2.0f }; // 2x1 RHS
        float wr = 0.5f;
        float wi = 0.0f;

        float[] x_expected = new float[2];
        float[] x_actual = new float[2];
        floatW scale_expected = new floatW(0.0f);
        floatW scale_actual = new floatW(0.0f);
        floatW xnorm_expected = new floatW(0.0f);
        floatW xnorm_actual = new floatW(0.0f);
        intW info_expected = new intW(0);
        intW info_actual = new intW(0);

        f2j.slaln2(false, na, nw, smin, ca, a, 2, d1, d2, b, 2, wr, wi, x_expected, 2, scale_expected, xnorm_expected, info_expected);
        lapack.slaln2(false, na, nw, smin, ca, a, 2, d1, d2, b, 2, wr, wi, x_actual, 2, scale_actual, xnorm_actual, info_actual);

        assertEquals(scale_expected.val, scale_actual.val, sepsilon);
        assertArrayEquals(x_expected, x_actual, sepsilon);
        assertEquals(xnorm_expected.val, xnorm_actual.val, sepsilon);
    }
}
