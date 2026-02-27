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

package dev.ludovic.netlib.arpack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import org.netlib.util.intW;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class SlaqrbTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        // Note: Some native implementations may have issues with output functions
        org.junit.jupiter.api.Assumptions.assumeFalse(arpack instanceof NativeARPACK,
                "Output function not fully supported by " + arpack.getClass().getSimpleName());

        int n = 10;
        int ldh = n;

        // Test case 1: wantt=true, full range
        {
            float[] expected_h = generateUpperHessenbergFloat(n);
            float[] expected_wr = new float[n];
            float[] expected_wi = new float[n];
            float[] expected_z = new float[n];
            intW expected_info = new intW(0);

            f2j.slaqrb(true, n, 1, n, expected_h, ldh, expected_wr, expected_wi, expected_z, expected_info);

            float[] actual_h = generateUpperHessenbergFloat(n);
            float[] actual_wr = new float[n];
            float[] actual_wi = new float[n];
            float[] actual_z = new float[n];
            intW actual_info = new intW(0);

            arpack.slaqrb(true, n, 1, n, actual_h, ldh, actual_wr, actual_wi, actual_z, actual_info);

            assertArrayEquals(expected_h, actual_h, sepsilon);
            assertArrayEquals(expected_wr, actual_wr, sepsilon);
            assertArrayEquals(expected_wi, actual_wi, sepsilon);
            assertArrayEquals(expected_z, actual_z, sepsilon);
            assertEquals(expected_info.val, actual_info.val);
        }

        // Test case 2: wantt=false, full range
        {
            float[] expected_h = generateUpperHessenbergFloat(n);
            float[] expected_wr = new float[n];
            float[] expected_wi = new float[n];
            float[] expected_z = new float[n];
            intW expected_info = new intW(0);

            f2j.slaqrb(false, n, 1, n, expected_h, ldh, expected_wr, expected_wi, expected_z, expected_info);

            float[] actual_h = generateUpperHessenbergFloat(n);
            float[] actual_wr = new float[n];
            float[] actual_wi = new float[n];
            float[] actual_z = new float[n];
            intW actual_info = new intW(0);

            arpack.slaqrb(false, n, 1, n, actual_h, ldh, actual_wr, actual_wi, actual_z, actual_info);

            assertArrayEquals(expected_h, actual_h, sepsilon);
            assertArrayEquals(expected_wr, actual_wr, sepsilon);
            assertArrayEquals(expected_wi, actual_wi, sepsilon);
            assertArrayEquals(expected_z, actual_z, sepsilon);
            assertEquals(expected_info.val, actual_info.val);
        }

        // Test case 3: wantt=true, partial range
        {
            int ilo = 2;
            int ihi = 8;
            float[] expected_h = generateUpperHessenbergFloat(n);
            float[] expected_wr = new float[n];
            float[] expected_wi = new float[n];
            float[] expected_z = new float[n];
            intW expected_info = new intW(0);

            f2j.slaqrb(true, n, ilo, ihi, expected_h, ldh, expected_wr, expected_wi, expected_z, expected_info);

            float[] actual_h = generateUpperHessenbergFloat(n);
            float[] actual_wr = new float[n];
            float[] actual_wi = new float[n];
            float[] actual_z = new float[n];
            intW actual_info = new intW(0);

            arpack.slaqrb(true, n, ilo, ihi, actual_h, ldh, actual_wr, actual_wi, actual_z, actual_info);

            assertArrayEquals(expected_h, actual_h, sepsilon);
            assertArrayEquals(expected_wr, actual_wr, sepsilon);
            assertArrayEquals(expected_wi, actual_wi, sepsilon);
            assertArrayEquals(expected_z, actual_z, sepsilon);
            assertEquals(expected_info.val, actual_info.val);
        }
    }
}
