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

public class Dlamc2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        org.junit.jupiter.api.Assumptions.assumeFalse(lapack instanceof NativeLAPACK,
                "Internal routine not exposed by " + lapack.getClass().getSimpleName());

        intW beta_expected = new intW(0);
        intW t_expected = new intW(0);
        booleanW rnd_expected = new booleanW(false);
        doubleW eps_expected = new doubleW(0.0);
        intW emin_expected = new intW(0);
        doubleW rmin_expected = new doubleW(0.0);
        intW emax_expected = new intW(0);
        doubleW rmax_expected = new doubleW(0.0);

        intW beta_actual = new intW(0);
        intW t_actual = new intW(0);
        booleanW rnd_actual = new booleanW(false);
        doubleW eps_actual = new doubleW(0.0);
        intW emin_actual = new intW(0);
        doubleW rmin_actual = new doubleW(0.0);
        intW emax_actual = new intW(0);
        doubleW rmax_actual = new doubleW(0.0);

        f2j.dlamc2(beta_expected, t_expected, rnd_expected, eps_expected, emin_expected, rmin_expected, emax_expected, rmax_expected);
        lapack.dlamc2(beta_actual, t_actual, rnd_actual, eps_actual, emin_actual, rmin_actual, emax_actual, rmax_actual);

        assertEquals(beta_expected.val, beta_actual.val);
        assertEquals(t_expected.val, t_actual.val);
        assertEquals(rnd_expected.val, rnd_actual.val);
        assertEquals(eps_expected.val, eps_actual.val, depsilon);
        assertEquals(emin_expected.val, emin_actual.val);
        assertEquals(rmin_expected.val, rmin_actual.val, depsilon);
        assertEquals(emax_expected.val, emax_actual.val);
        assertEquals(rmax_expected.val, rmax_actual.val, depsilon);
    }
}
