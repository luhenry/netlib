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

public class Slanv2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test standardization of 2x2 real Schur block
        floatW a_expected = new floatW(1.0f);
        floatW b_expected = new floatW(2.0f);
        floatW c_expected = new floatW(3.0f);
        floatW d_expected = new floatW(4.0f);
        floatW rt1r_expected = new floatW(0.0f);
        floatW rt1i_expected = new floatW(0.0f);
        floatW rt2r_expected = new floatW(0.0f);
        floatW rt2i_expected = new floatW(0.0f);
        floatW cs_expected = new floatW(0.0f);
        floatW sn_expected = new floatW(0.0f);
        f2j.slanv2(a_expected, b_expected, c_expected, d_expected, rt1r_expected, rt1i_expected, rt2r_expected, rt2i_expected, cs_expected, sn_expected);

        floatW a_actual = new floatW(1.0f);
        floatW b_actual = new floatW(2.0f);
        floatW c_actual = new floatW(3.0f);
        floatW d_actual = new floatW(4.0f);
        floatW rt1r_actual = new floatW(0.0f);
        floatW rt1i_actual = new floatW(0.0f);
        floatW rt2r_actual = new floatW(0.0f);
        floatW rt2i_actual = new floatW(0.0f);
        floatW cs_actual = new floatW(0.0f);
        floatW sn_actual = new floatW(0.0f);
        lapack.slanv2(a_actual, b_actual, c_actual, d_actual, rt1r_actual, rt1i_actual, rt2r_actual, rt2i_actual, cs_actual, sn_actual);

        assertEquals(a_expected.val, a_actual.val, sepsilon);
        assertEquals(b_expected.val, b_actual.val, sepsilon);
        assertEquals(c_expected.val, c_actual.val, sepsilon);
        assertEquals(d_expected.val, d_actual.val, sepsilon);
        assertEquals(rt1r_expected.val, rt1r_actual.val, sepsilon);
        assertEquals(rt1i_expected.val, rt1i_actual.val, sepsilon);
        assertEquals(rt2r_expected.val, rt2r_actual.val, sepsilon);
        assertEquals(rt2i_expected.val, rt2i_actual.val, sepsilon);
        assertEquals(cs_expected.val, cs_actual.val, sepsilon);
        assertEquals(sn_expected.val, sn_actual.val, sepsilon);
    }
}
