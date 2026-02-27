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

package dev.ludovic.netlib.lapack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;
import org.netlib.util.*;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class Dlae2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test 2x2 symmetric eigenvalue decomposition
        // Matrix: [1.0, 2.0; 2.0, 3.0]
        doubleW rt1_expected = new doubleW(0.0);
        doubleW rt2_expected = new doubleW(0.0);
        f2j.dlae2(1.0, 2.0, 3.0, rt1_expected, rt2_expected);

        doubleW rt1_actual = new doubleW(0.0);
        doubleW rt2_actual = new doubleW(0.0);
        lapack.dlae2(1.0, 2.0, 3.0, rt1_actual, rt2_actual);

        assertEquals(rt1_expected.val, rt1_actual.val, depsilon);
        assertEquals(rt2_expected.val, rt2_actual.val, depsilon);

        // Test with diagonal matrix
        f2j.dlae2(5.0, 0.0, 2.0, rt1_expected, rt2_expected);
        lapack.dlae2(5.0, 0.0, 2.0, rt1_actual, rt2_actual);

        assertEquals(rt1_expected.val, rt1_actual.val, depsilon);
        assertEquals(rt2_expected.val, rt2_actual.val, depsilon);
    }
}
