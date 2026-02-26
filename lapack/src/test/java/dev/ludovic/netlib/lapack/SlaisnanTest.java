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

public class SlaisnanTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test with both NaN - slaisnan returns true when both inputs are NaN
        assertEquals(f2j.slaisnan(Float.NaN, Float.NaN), lapack.slaisnan(Float.NaN, Float.NaN));

        // Test with one NaN
        assertEquals(f2j.slaisnan(Float.NaN, 1.0f), lapack.slaisnan(Float.NaN, 1.0f));
        assertEquals(f2j.slaisnan(1.0f, Float.NaN), lapack.slaisnan(1.0f, Float.NaN));

        // Test with normal values
        assertEquals(f2j.slaisnan(1.0f, 2.0f), lapack.slaisnan(1.0f, 2.0f));
        assertEquals(f2j.slaisnan(0.0f, 0.0f), lapack.slaisnan(0.0f, 0.0f));
        assertEquals(f2j.slaisnan(1.0f, 1.0f), lapack.slaisnan(1.0f, 1.0f));

        // Test with infinity
        assertEquals(f2j.slaisnan(Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY),
                     lapack.slaisnan(Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY));
        assertEquals(f2j.slaisnan(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY),
                     lapack.slaisnan(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY));
    }
}
