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

public class DrotmgTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        // Test case 1: typical values
        {
            doubleW edd1 = new doubleW(2.0);
            doubleW edd2 = new doubleW(3.0);
            doubleW edx1 = new doubleW(4.0);
            double edy1 = 5.0;
            double[] expectedParam = new double[5];
            f2j.drotmg(edd1, edd2, edx1, edy1, expectedParam);

            doubleW bdd1 = new doubleW(2.0);
            doubleW bdd2 = new doubleW(3.0);
            doubleW bdx1 = new doubleW(4.0);
            double bdy1 = 5.0;
            double[] blasParam = new double[5];
            blas.drotmg(bdd1, bdd2, bdx1, bdy1, blasParam);

            assertEquals(edd1.val, bdd1.val, depsilon);
            assertEquals(edd2.val, bdd2.val, depsilon);
            assertEquals(edx1.val, bdx1.val, depsilon);
            assertArrayEquals(expectedParam, blasParam, depsilon);
        }

        // Test case 2: dd1 negative (triggers rescaling)
        {
            doubleW edd1 = new doubleW(-1.0);
            doubleW edd2 = new doubleW(2.0);
            doubleW edx1 = new doubleW(3.0);
            double edy1 = 4.0;
            double[] expectedParam = new double[5];
            f2j.drotmg(edd1, edd2, edx1, edy1, expectedParam);

            doubleW bdd1 = new doubleW(-1.0);
            doubleW bdd2 = new doubleW(2.0);
            doubleW bdx1 = new doubleW(3.0);
            double bdy1 = 4.0;
            double[] blasParam = new double[5];
            blas.drotmg(bdd1, bdd2, bdx1, bdy1, blasParam);

            assertEquals(edd1.val, bdd1.val, depsilon);
            assertEquals(edd2.val, bdd2.val, depsilon);
            assertEquals(edx1.val, bdx1.val, depsilon);
            assertArrayEquals(expectedParam, blasParam, depsilon);
        }

        // Test case 3: dy1 = 0 (should be identity-like)
        {
            doubleW edd1 = new doubleW(1.0);
            doubleW edd2 = new doubleW(1.0);
            doubleW edx1 = new doubleW(1.0);
            double edy1 = 0.0;
            double[] expectedParam = new double[5];
            f2j.drotmg(edd1, edd2, edx1, edy1, expectedParam);

            doubleW bdd1 = new doubleW(1.0);
            doubleW bdd2 = new doubleW(1.0);
            doubleW bdx1 = new doubleW(1.0);
            double bdy1 = 0.0;
            double[] blasParam = new double[5];
            blas.drotmg(bdd1, bdd2, bdx1, bdy1, blasParam);

            assertEquals(edd1.val, bdd1.val, depsilon);
            assertEquals(edd2.val, bdd2.val, depsilon);
            assertEquals(edx1.val, bdx1.val, depsilon);
            assertArrayEquals(expectedParam, blasParam, depsilon);
        }

        // Test case 4: larger values
        {
            doubleW edd1 = new doubleW(5.0);
            doubleW edd2 = new doubleW(2.0);
            doubleW edx1 = new doubleW(10.0);
            double edy1 = 3.0;
            double[] expectedParam = new double[5];
            f2j.drotmg(edd1, edd2, edx1, edy1, expectedParam);

            doubleW bdd1 = new doubleW(5.0);
            doubleW bdd2 = new doubleW(2.0);
            doubleW bdx1 = new doubleW(10.0);
            double bdy1 = 3.0;
            double[] blasParam = new double[5];
            blas.drotmg(bdd1, bdd2, bdx1, bdy1, blasParam);

            assertEquals(edd1.val, bdd1.val, depsilon);
            assertEquals(edd2.val, bdd2.val, depsilon);
            assertEquals(edx1.val, bdx1.val, depsilon);
            assertArrayEquals(expectedParam, blasParam, depsilon);
        }
    }
}
