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

import org.netlib.util.doubleW;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class DrotmgTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        // typical values
        assertDrotmg(blas, 2.0, 3.0, 4.0, 5.0);
        // dd1 negative (zeros everything, flag=-1)
        assertDrotmg(blas, -1.0, 2.0, 3.0, 4.0);
        // dy1 = 0 (identity, flag=-2)
        assertDrotmg(blas, 1.0, 1.0, 1.0, 0.0);
        // larger values
        assertDrotmg(blas, 5.0, 2.0, 10.0, 3.0);
        // both d1 and d2 negative
        assertDrotmg(blas, -2.0, -3.0, 4.0, 5.0);
        // very small values (near rescaling threshold)
        assertDrotmg(blas, 1e-12, 1e-12, 1.0, 1.0);
        // very large values (near rescaling threshold)
        assertDrotmg(blas, 1e12, 1e12, 1.0, 1.0);
        // d2 much larger than d1 (swap branch)
        assertDrotmg(blas, 1.0, 100.0, 2.0, 3.0);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testD1IsZero(BLAS blas) {
        assumeFalse(blas instanceof NativeBLAS);

        // d1 = 0 (degenerate, swap branch)
        assertDrotmg(blas, 0.0, 2.0, 1.0, 3.0);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNegativeD2SwapBranch(BLAS blas) {
        // dq2 < 0 triggers flag=-1 and zeros everything:
        // |dq1| <= |dq2| with dq2 < 0 (dd2 negative)
        assertDrotmg(blas, 1.0, -5.0, 1.0, 1.0);
        assertDrotmg(blas, 1.0, -2.0, 1.0, 2.0);
        assertDrotmg(blas, 0.5, -10.0, 1.0, 1.0);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testRescalingDD1WithFlag0(BLAS blas) {
        // flag=0 path (|dq1| > |dq2|) with dd1 needing upscaling (dd1 <= rgamsq)
        assertDrotmg(blas, 1e-10, 1e-11, 1.0, 0.5);
        assertDrotmg(blas, 1e-10, 1e-12, 1.0, 0.1);

        // flag=0 path with dd1 needing downscaling (dd1 >= gamsq)
        assertDrotmg(blas, 1e10, 1e9, 1.0, 0.5);
        assertDrotmg(blas, 1e8, 1e7, 1.0, 0.5);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testRescalingDD2WithFlag0(BLAS blas) {
        // flag=0 path (|dq1| > |dq2|) with dd2 needing upscaling
        assertDrotmg(blas, 1.0, 1e-10, 1.0, 0.5);
        assertDrotmg(blas, 10.0, 1e-10, 2.0, 0.1);

        // flag=0 path with dd2 needing downscaling
        assertDrotmg(blas, 1.0, 1e10, 1.0, 0.5);
        assertDrotmg(blas, 10.0, 1e10, 2.0, 0.1);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testRescalingWithFlag1(BLAS blas) {
        // flag=1 (swap) path: |dq1| <= |dq2|, dq2 >= 0
        // After swap, dd1=old_dd2/du, dd2=old_dd1/du
        // dd1 rescaling (dd1 = old_dd2 is small)
        assertDrotmg(blas, 1e-10, 1e-8, 1.0, 1.0);
        // dd1 rescaling (dd1 = old_dd2 is large)
        assertDrotmg(blas, 1e8, 1e10, 1.0, 1.0);
        // dd2 rescaling (dd2 = old_dd1 is small after swap)
        assertDrotmg(blas, 1e-12, 1.0, 1.0, 1.0);
        // dd2 rescaling (dd2 = old_dd1 is large after swap)
        assertDrotmg(blas, 1e12, 1e14, 1.0, 1.0);
    }

    /**
     * Helper to run drotmg on both f2j and blas and assert equality.
     */
    private void assertDrotmg(BLAS blas, double d1, double d2, double x1, double y1) {
        doubleW edd1 = new doubleW(d1);
        doubleW edd2 = new doubleW(d2);
        doubleW edx1 = new doubleW(x1);
        double[] expectedParam = new double[5];
        f2j.drotmg(edd1, edd2, edx1, y1, expectedParam);

        doubleW bdd1 = new doubleW(d1);
        doubleW bdd2 = new doubleW(d2);
        doubleW bdx1 = new doubleW(x1);
        double[] blasParam = new double[5];
        blas.drotmg(bdd1, bdd2, bdx1, y1, blasParam);

        assertEquals(edd1.val, bdd1.val, depsilon);
        assertEquals(edd2.val, bdd2.val, depsilon);
        assertEquals(edx1.val, bdx1.val, depsilon);
        assertArrayEquals(expectedParam, blasParam, depsilon);
    }
}
