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

import static dev.ludovic.netlib.test.TestHelpers.*;

public class IcnteqTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        int expected, actual;

        // Test counting specific values in array
        // intArray1 = [1, 2, 3, ..., 50], so count of value 1 should be 1
        expected = f2j.icnteq(N, intArray1, 1);
        actual = arpack.icnteq(N, intArray1, 1);
        assertEquals(expected, actual);

        // Count value that appears once (first element)
        expected = f2j.icnteq(N, intArray1, 1);
        actual = arpack.icnteq(N, intArray1, 1);
        assertEquals(expected, actual);

        // Count value that doesn't exist
        expected = f2j.icnteq(N, intArray1, 999);
        actual = arpack.icnteq(N, intArray1, 999);
        assertEquals(expected, actual);

        // Count in intArray3 (all zeros)
        expected = f2j.icnteq(N, intArray3, 0);
        actual = arpack.icnteq(N, intArray3, 0);
        assertEquals(expected, actual);

        // Count non-zero in array of zeros
        expected = f2j.icnteq(N, intArray3, 1);
        actual = arpack.icnteq(N, intArray3, 1);
        assertEquals(expected, actual);
    }
}
