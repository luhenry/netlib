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

public class DlartgTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test Givens rotation generation
        org.netlib.util.doubleW cs_expected = new org.netlib.util.doubleW(0.0);
        org.netlib.util.doubleW sn_expected = new org.netlib.util.doubleW(0.0);
        org.netlib.util.doubleW r_expected = new org.netlib.util.doubleW(0.0);
        f2j.dlartg(3.0, 4.0, cs_expected, sn_expected, r_expected);

        org.netlib.util.doubleW cs_actual = new org.netlib.util.doubleW(0.0);
        org.netlib.util.doubleW sn_actual = new org.netlib.util.doubleW(0.0);
        org.netlib.util.doubleW r_actual = new org.netlib.util.doubleW(0.0);
        lapack.dlartg(3.0, 4.0, cs_actual, sn_actual, r_actual);

        assertEquals(cs_expected.val, cs_actual.val, depsilon);
        assertEquals(sn_expected.val, sn_actual.val, depsilon);
        assertEquals(r_expected.val, r_actual.val, depsilon);

        // Test with zero second element
        f2j.dlartg(5.0, 0.0, cs_expected, sn_expected, r_expected);
        lapack.dlartg(5.0, 0.0, cs_actual, sn_actual, r_actual);

        assertEquals(cs_expected.val, cs_actual.val, depsilon);
        assertEquals(sn_expected.val, sn_actual.val, depsilon);
        assertEquals(r_expected.val, r_actual.val, depsilon);
    }
}
