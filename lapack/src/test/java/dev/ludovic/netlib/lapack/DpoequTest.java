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

public class DpoequTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        double[] s_expected = new double[N];
        doubleW scond_expected = new doubleW(0.0);
        doubleW amax_expected = new doubleW(0.0);
        intW info_expected = new intW(0);
        f2j.dpoequ(N, dPositiveDefiniteMatrix, 0, N, s_expected, 0, scond_expected, amax_expected, info_expected);

        double[] s_actual = new double[N];
        doubleW scond_actual = new doubleW(0.0);
        doubleW amax_actual = new doubleW(0.0);
        intW info_actual = new intW(0);
        lapack.dpoequ(N, dPositiveDefiniteMatrix, 0, N, s_actual, 0, scond_actual, amax_actual, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(s_expected, s_actual, depsilon);
        assertEquals(scond_expected.val, scond_actual.val, depsilon);
        assertEquals(amax_expected.val, amax_actual.val, depsilon);
    }
}
