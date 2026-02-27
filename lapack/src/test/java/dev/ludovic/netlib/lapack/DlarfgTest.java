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

public class DlarfgTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test Householder reflector generation
        double[] x_expected = new double[N_SMALL];
        for (int i = 0; i < N_SMALL; i++) {
            x_expected[i] = (i + 1) * 1.0;
        }
        doubleW alpha_expected = new doubleW(2.5);
        doubleW tau_expected = new doubleW(0.0);
        f2j.dlarfg(N_SMALL, alpha_expected, x_expected, 0, 1, tau_expected);

        double[] x_actual = new double[N_SMALL];
        for (int i = 0; i < N_SMALL; i++) {
            x_actual[i] = (i + 1) * 1.0;
        }
        doubleW alpha_actual = new doubleW(2.5);
        doubleW tau_actual = new doubleW(0.0);
        lapack.dlarfg(N_SMALL, alpha_actual, x_actual, 0, 1, tau_actual);

        assertEquals(alpha_expected.val, alpha_actual.val, depsilon);
        assertEquals(tau_expected.val, tau_actual.val, depsilon);
        assertArrayEquals(x_expected, x_actual, depsilon);
    }
}
