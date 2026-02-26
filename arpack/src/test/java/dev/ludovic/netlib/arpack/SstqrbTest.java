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
import static org.junit.jupiter.api.Assumptions.*;

import org.netlib.util.intW;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class SstqrbTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        assumeFalse(arpack instanceof NativeARPACK && System.getProperty("os.name", "").toLowerCase().contains("mac"));

        int n = 10;

        // Test case 1: Simple tridiagonal matrix
        {
            float[] expected_d = generateFloatRange(n, 1.0f);
            float[] expected_e = generateFloatRange(n - 1, 0.5f);
            float[] expected_z = new float[n];
            float[] expected_work = new float[2 * n - 2];
            intW expected_info = new intW(0);

            f2j.sstqrb(n, expected_d, expected_e, expected_z, expected_work, expected_info);

            float[] actual_d = generateFloatRange(n, 1.0f);
            float[] actual_e = generateFloatRange(n - 1, 0.5f);
            float[] actual_z = new float[n];
            float[] actual_work = new float[2 * n - 2];
            intW actual_info = new intW(0);

            arpack.sstqrb(n, actual_d, actual_e, actual_z, actual_work, actual_info);

            assertArrayEquals(expected_d, actual_d, sepsilon);
            assertArrayEquals(expected_e, actual_e, sepsilon);
            assertAbsArrayEquals(expected_z, actual_z, 1e-5f);
            assertEquals(expected_info.val, actual_info.val);
        }

        // Test case 2: Different matrix values
        {
            float[] expected_d = generateFloatRange(n, 2.0f);
            float[] expected_e = generateFloatRange(n - 1, 0.25f);
            float[] expected_z = new float[n];
            float[] expected_work = new float[2 * n - 2];
            intW expected_info = new intW(0);

            f2j.sstqrb(n, expected_d, expected_e, expected_z, expected_work, expected_info);

            float[] actual_d = generateFloatRange(n, 2.0f);
            float[] actual_e = generateFloatRange(n - 1, 0.25f);
            float[] actual_z = new float[n];
            float[] actual_work = new float[2 * n - 2];
            intW actual_info = new intW(0);

            arpack.sstqrb(n, actual_d, actual_e, actual_z, actual_work, actual_info);

            assertArrayEquals(expected_d, actual_d, sepsilon);
            assertArrayEquals(expected_e, actual_e, sepsilon);
            assertAbsArrayEquals(expected_z, actual_z, 1e-5f);
            assertEquals(expected_info.val, actual_info.val);
        }

        // Test case 3: Smaller matrix size
        {
            int small_n = 5;
            float[] expected_d = generateFloatRange(small_n, 1.5f);
            float[] expected_e = generateFloatRange(small_n - 1, 0.1f);
            float[] expected_z = new float[small_n];
            float[] expected_work = new float[2 * small_n - 2];
            intW expected_info = new intW(0);

            f2j.sstqrb(small_n, expected_d, expected_e, expected_z, expected_work, expected_info);

            float[] actual_d = generateFloatRange(small_n, 1.5f);
            float[] actual_e = generateFloatRange(small_n - 1, 0.1f);
            float[] actual_z = new float[small_n];
            float[] actual_work = new float[2 * small_n - 2];
            intW actual_info = new intW(0);

            arpack.sstqrb(small_n, actual_d, actual_e, actual_z, actual_work, actual_info);

            assertArrayEquals(expected_d, actual_d, sepsilon);
            assertArrayEquals(expected_e, actual_e, sepsilon);
            assertAbsArrayEquals(expected_z, actual_z, 1e-5f);
            assertEquals(expected_info.val, actual_info.val);
        }
    }
}
