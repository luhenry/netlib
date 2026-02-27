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

package dev.ludovic.netlib.blas;

import org.netlib.util.floatW;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class SrotmgTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        // typical values
        assertSrotmg(blas, 2.0f, 3.0f, 4.0f, 5.0f);
        // sd1 negative (zeros everything, flag=-1)
        assertSrotmg(blas, -1.0f, 2.0f, 3.0f, 4.0f);
        // sy1 = 0 (identity, flag=-2)
        assertSrotmg(blas, 1.0f, 1.0f, 1.0f, 0.0f);
        // larger values
        assertSrotmg(blas, 5.0f, 2.0f, 10.0f, 3.0f);
        // both d1 and d2 negative
        assertSrotmg(blas, -2.0f, -3.0f, 4.0f, 5.0f);
        // very small values (near rescaling threshold)
        assertSrotmg(blas, 1e-6f, 1e-6f, 1.0f, 1.0f);
        // very large values (near rescaling threshold)
        assertSrotmg(blas, 1e6f, 1e6f, 1.0f, 1.0f);
        // d2 much larger than d1 (swap branch)
        assertSrotmg(blas, 1.0f, 100.0f, 2.0f, 3.0f);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testD1IsZero(BLAS blas) {
        assumeFalse(blas instanceof NativeBLAS);

        // d1 = 0 (degenerate, swap branch)
        assertSrotmg(blas, 0.0f, 2.0f, 1.0f, 3.0f);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNegativeD2SwapBranch(BLAS blas) {
        // dq2 < 0 triggers flag=-1 and zeros everything:
        // |dq1| <= |dq2| with dq2 < 0 (dd2 negative)
        assertSrotmg(blas, 1.0f, -5.0f, 1.0f, 1.0f);
        assertSrotmg(blas, 1.0f, -2.0f, 1.0f, 2.0f);
        assertSrotmg(blas, 0.5f, -10.0f, 1.0f, 1.0f);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testRescalingSD1WithFlag0(BLAS blas) {
        // flag=0 path (|dq1| > |dq2|) with sd1 needing upscaling (sd1 <= rgamsq)
        assertSrotmg(blas, 1e-9f, 1e-10f, 1.0f, 0.5f);

        // flag=0 path with sd1 needing downscaling (sd1 >= gamsq)
        assertSrotmg(blas, 1e9f, 1e8f, 1.0f, 0.5f);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testRescalingSD2WithFlag0(BLAS blas) {
        // flag=0 path (|dq1| > |dq2|) with sd2 needing upscaling
        assertSrotmg(blas, 1.0f, 1e-9f, 1.0f, 0.5f);

        // flag=0 path with sd2 needing downscaling
        // Need |dq1|>|dq2| with large dd2: use large x1
        assertSrotmg(blas, 1.0f, 1e9f, 1e6f, 0.1f);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testRescalingWithFlag1(BLAS blas) {
        // flag=1 (swap) path: |dq1| <= |dq2|, dq2 >= 0
        // After swap, sd1=old_sd2/su, sd2=old_sd1/su
        // sd1 rescaling (sd1 = old_sd2 is small, upscaling)
        assertSrotmg(blas, 1e-9f, 1e-7f, 1.0f, 1.0f);
        // sd1 rescaling (sd1 = old_sd2 is large, downscaling)
        assertSrotmg(blas, 1e7f, 1e9f, 1.0f, 1.0f);
        // sd2 rescaling (sd2 = old_sd1 is small after swap, upscaling)
        assertSrotmg(blas, 1e-9f, 1.0f, 1.0f, 1.0f);
        // sd2 rescaling (sd2 = old_sd1 is large after swap, downscaling)
        assertSrotmg(blas, 1e9f, 1e11f, 1.0f, 1.0f);
    }

    /**
     * Helper to run srotmg on both f2j and blas and assert equality.
     */
    private void assertSrotmg(BLAS blas, float d1, float d2, float x1, float y1) {
        floatW esd1 = new floatW(d1);
        floatW esd2 = new floatW(d2);
        floatW esx1 = new floatW(x1);
        float[] expectedParam = new float[5];
        f2j.srotmg(esd1, esd2, esx1, y1, expectedParam);

        floatW bsd1 = new floatW(d1);
        floatW bsd2 = new floatW(d2);
        floatW bsx1 = new floatW(x1);
        float[] blasParam = new float[5];
        blas.srotmg(bsd1, bsd2, bsx1, y1, blasParam);

        assertEquals(esd1.val, bsd1.val, Math.max(Math.abs(esd1.val) * sepsilon, sepsilon));
        assertEquals(esd2.val, bsd2.val, Math.max(Math.abs(esd2.val) * sepsilon, sepsilon));
        assertEquals(esx1.val, bsx1.val, Math.max(Math.abs(esx1.val) * sepsilon, sepsilon));
        assertRelArrayEquals(expectedParam, blasParam, sepsilon);
    }
}
