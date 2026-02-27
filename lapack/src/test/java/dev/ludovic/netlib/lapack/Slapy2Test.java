/*
 * Copyright 2020, 2022, Ludovic Henry
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

import static dev.ludovic.netlib.test.TestHelpers.*;

public class Slapy2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test basic Pythagorean triple
        float expected = f2j.slapy2(3.0f, 4.0f);
        float actual = lapack.slapy2(3.0f, 4.0f);
        assertEquals(expected, actual, sepsilon);

        // Test with large values to ensure no overflow
        expected = f2j.slapy2(1e18f, 1e18f);
        actual = lapack.slapy2(1e18f, 1e18f);
        assertEquals(expected, actual, expected * sepsilon);

        // Test with small values to ensure no underflow
        expected = f2j.slapy2(1e-18f, 1e-18f);
        actual = lapack.slapy2(1e-18f, 1e-18f);
        assertEquals(expected, actual, expected * sepsilon);

        // Test with zero
        expected = f2j.slapy2(0.0f, 5.0f);
        actual = lapack.slapy2(0.0f, 5.0f);
        assertEquals(expected, actual, sepsilon);

        // Test with negative values
        expected = f2j.slapy2(-3.0f, -4.0f);
        actual = lapack.slapy2(-3.0f, -4.0f);
        assertEquals(expected, actual, sepsilon);
    }
}
