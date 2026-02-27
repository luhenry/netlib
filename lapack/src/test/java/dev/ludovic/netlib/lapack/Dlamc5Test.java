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

public class Dlamc5Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        org.junit.jupiter.api.Assumptions.assumeFalse(lapack instanceof NativeLAPACK,
                "Internal routine not exposed by " + lapack.getClass().getSimpleName());

        intW emax_expected = new intW(0);
        doubleW rmax_expected = new doubleW(0.0);
        intW emax_actual = new intW(0);
        doubleW rmax_actual = new doubleW(0.0);

        // Test with IEEE arithmetic
        f2j.dlamc5(2, 53, -1021, true, emax_expected, rmax_expected);
        lapack.dlamc5(2, 53, -1021, true, emax_actual, rmax_actual);
        assertEquals(emax_expected.val, emax_actual.val);
        assertEquals(rmax_expected.val, rmax_actual.val, depsilon);

        // Test without IEEE arithmetic
        emax_expected.val = 0;
        rmax_expected.val = 0.0;
        emax_actual.val = 0;
        rmax_actual.val = 0.0;
        f2j.dlamc5(2, 53, -1021, false, emax_expected, rmax_expected);
        lapack.dlamc5(2, 53, -1021, false, emax_actual, rmax_actual);
        assertEquals(emax_expected.val, emax_actual.val);
        assertEquals(rmax_expected.val, rmax_actual.val, depsilon);
    }
}
