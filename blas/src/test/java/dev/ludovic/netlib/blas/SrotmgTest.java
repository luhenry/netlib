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

public class SrotmgTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        // Test case 1: typical values
        {
            floatW esd1 = new floatW(2.0f);
            floatW esd2 = new floatW(3.0f);
            floatW esx1 = new floatW(4.0f);
            float esy1 = 5.0f;
            float[] expectedParam = new float[5];
            f2j.srotmg(esd1, esd2, esx1, esy1, expectedParam);

            floatW bsd1 = new floatW(2.0f);
            floatW bsd2 = new floatW(3.0f);
            floatW bsx1 = new floatW(4.0f);
            float bsy1 = 5.0f;
            float[] blasParam = new float[5];
            blas.srotmg(bsd1, bsd2, bsx1, bsy1, blasParam);

            assertEquals(esd1.val, bsd1.val, sepsilon);
            assertEquals(esd2.val, bsd2.val, sepsilon);
            assertEquals(esx1.val, bsx1.val, sepsilon);
            assertArrayEquals(expectedParam, blasParam, sepsilon);
        }

        // Test case 2: sd1 negative (triggers rescaling)
        {
            floatW esd1 = new floatW(-1.0f);
            floatW esd2 = new floatW(2.0f);
            floatW esx1 = new floatW(3.0f);
            float esy1 = 4.0f;
            float[] expectedParam = new float[5];
            f2j.srotmg(esd1, esd2, esx1, esy1, expectedParam);

            floatW bsd1 = new floatW(-1.0f);
            floatW bsd2 = new floatW(2.0f);
            floatW bsx1 = new floatW(3.0f);
            float bsy1 = 4.0f;
            float[] blasParam = new float[5];
            blas.srotmg(bsd1, bsd2, bsx1, bsy1, blasParam);

            assertEquals(esd1.val, bsd1.val, sepsilon);
            assertEquals(esd2.val, bsd2.val, sepsilon);
            assertEquals(esx1.val, bsx1.val, sepsilon);
            assertArrayEquals(expectedParam, blasParam, sepsilon);
        }

        // Test case 3: sy1 = 0 (should be identity-like)
        {
            floatW esd1 = new floatW(1.0f);
            floatW esd2 = new floatW(1.0f);
            floatW esx1 = new floatW(1.0f);
            float esy1 = 0.0f;
            float[] expectedParam = new float[5];
            f2j.srotmg(esd1, esd2, esx1, esy1, expectedParam);

            floatW bsd1 = new floatW(1.0f);
            floatW bsd2 = new floatW(1.0f);
            floatW bsx1 = new floatW(1.0f);
            float bsy1 = 0.0f;
            float[] blasParam = new float[5];
            blas.srotmg(bsd1, bsd2, bsx1, bsy1, blasParam);

            assertEquals(esd1.val, bsd1.val, sepsilon);
            assertEquals(esd2.val, bsd2.val, sepsilon);
            assertEquals(esx1.val, bsx1.val, sepsilon);
            assertArrayEquals(expectedParam, blasParam, sepsilon);
        }

        // Test case 4: larger values
        {
            floatW esd1 = new floatW(5.0f);
            floatW esd2 = new floatW(2.0f);
            floatW esx1 = new floatW(10.0f);
            float esy1 = 3.0f;
            float[] expectedParam = new float[5];
            f2j.srotmg(esd1, esd2, esx1, esy1, expectedParam);

            floatW bsd1 = new floatW(5.0f);
            floatW bsd2 = new floatW(2.0f);
            floatW bsx1 = new floatW(10.0f);
            float bsy1 = 3.0f;
            float[] blasParam = new float[5];
            blas.srotmg(bsd1, bsd2, bsx1, bsy1, blasParam);

            assertEquals(esd1.val, bsd1.val, sepsilon);
            assertEquals(esd2.val, bsd2.val, sepsilon);
            assertEquals(esx1.val, bsx1.val, sepsilon);
            assertArrayEquals(expectedParam, blasParam, sepsilon);
        }
    }
}
