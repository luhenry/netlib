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

public class Slaed5Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Secular equation for 2x2 rank-one update
        float[] d = { 1.0f, 3.0f };
        float norm = (float) Math.sqrt(0.6f * 0.6f + 0.8f * 0.8f);
        float[] z = { 0.6f / norm, 0.8f / norm };
        float rho = 2.0f;

        for (int i = 1; i <= 2; i++) {
            float[] delta_expected = new float[2];
            float[] delta_actual = new float[2];
            floatW dlam_expected = new floatW(0);
            floatW dlam_actual = new floatW(0);

            f2j.slaed5(i, d, z, delta_expected, rho, dlam_expected);
            lapack.slaed5(i, d, z, delta_actual, rho, dlam_actual);

            assertEquals(dlam_expected.val, dlam_actual.val, sepsilon);
            assertArrayEquals(delta_expected, delta_actual, sepsilon);
        }
    }
}
