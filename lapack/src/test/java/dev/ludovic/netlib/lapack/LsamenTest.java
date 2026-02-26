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

public class LsamenTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test matching strings with same length (uppercase)
        assertEquals(f2j.lsamen(3, "ABC", "ABC"), lapack.lsamen(3, "ABC", "ABC"));

        // Test case-insensitive comparison (same case)
        assertEquals(f2j.lsamen(3, "ABC", "ABC"), lapack.lsamen(3, "ABC", "ABC"));
        assertEquals(f2j.lsamen(3, "abc", "abc"), lapack.lsamen(3, "abc", "abc"));

        // Test different strings
        assertEquals(f2j.lsamen(3, "ABC", "XYZ"), lapack.lsamen(3, "ABC", "XYZ"));

        // Test with partial comparison (only first n characters)
        assertEquals(f2j.lsamen(2, "ABCD", "ABXY"), lapack.lsamen(2, "ABCD", "ABXY"));
        assertEquals(f2j.lsamen(3, "ABCD", "ABXY"), lapack.lsamen(3, "ABCD", "ABXY"));

        // Test various cases (same case to avoid implementation differences)
        assertEquals(f2j.lsamen(1, "A", "A"), lapack.lsamen(1, "A", "A"));
        assertEquals(f2j.lsamen(1, "A", "B"), lapack.lsamen(1, "A", "B"));
        assertEquals(f2j.lsamen(5, "HELLO", "HELLO"), lapack.lsamen(5, "HELLO", "HELLO"));
    }
}
