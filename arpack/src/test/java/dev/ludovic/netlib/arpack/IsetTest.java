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

public class IsetTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        int[] expected, actual;

        // Test setting array to value 0 with increment 1
        f2j.iset(N, 0, expected = intArray1.clone(), 1);
        arpack.iset(N, 0, actual = intArray1.clone(), 1);
        assertArrayEquals(expected, actual);

        // Test setting array to value 42 with increment 1
        f2j.iset(N, 42, expected = intArray2.clone(), 1);
        arpack.iset(N, 42, actual = intArray2.clone(), 1);
        assertArrayEquals(expected, actual);

        // Test setting array to value -5 with increment 1
        f2j.iset(N, -5, expected = intArray3.clone(), 1);
        arpack.iset(N, -5, actual = intArray3.clone(), 1);
        assertArrayEquals(expected, actual);

        // Test with increment 2 (every other element)
        f2j.iset(N / 2, 99, expected = intArray1.clone(), 2);
        arpack.iset(N / 2, 99, actual = intArray1.clone(), 2);
        assertArrayEquals(expected, actual);
    }
}
