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

public class Dlags2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test computation of sine and cosine for generalized problem
        booleanW upper = new booleanW(true);
        doubleW csu_expected = new doubleW(0.0);
        doubleW snu_expected = new doubleW(0.0);
        doubleW csv_expected = new doubleW(0.0);
        doubleW snv_expected = new doubleW(0.0);
        doubleW csq_expected = new doubleW(0.0);
        doubleW snq_expected = new doubleW(0.0);

        f2j.dlags2(upper.val, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, csu_expected, snu_expected, csv_expected, snv_expected, csq_expected, snq_expected);

        doubleW csu_actual = new doubleW(0.0);
        doubleW snu_actual = new doubleW(0.0);
        doubleW csv_actual = new doubleW(0.0);
        doubleW snv_actual = new doubleW(0.0);
        doubleW csq_actual = new doubleW(0.0);
        doubleW snq_actual = new doubleW(0.0);

        lapack.dlags2(upper.val, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, csu_actual, snu_actual, csv_actual, snv_actual, csq_actual, snq_actual);

        assertEquals(csu_expected.val, csu_actual.val, depsilon);
        assertEquals(snu_expected.val, snu_actual.val, depsilon);
        assertEquals(csv_expected.val, csv_actual.val, depsilon);
        assertEquals(snv_expected.val, snv_actual.val, depsilon);
        assertEquals(csq_expected.val, csq_actual.val, depsilon);
        assertEquals(snq_expected.val, snq_actual.val, depsilon);
    }
}
