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

public class Slaed6Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // slaed6 computes the positive or negative root of a 3-term secular equation:
        // f(x) = rho + z(1)/(d(1)-x) + z(2)/(d(2)-x) + z(3)/(d(3)-x)
        // d must be strictly increasing, z must be all positive.
        int kniter = 1;
        boolean orgati = true; // root between d(2) and d(3)
        float rho = 1.0f;

        float[] d_expected = {1.0f, 2.0f, 3.0f};
        float[] z_expected = {0.5f, 0.5f, 0.5f};
        // finit = f(0) = rho + z(1)/d(1) + z(2)/d(2) + z(3)/d(3)
        float finit = rho + z_expected[0] / d_expected[0] + z_expected[1] / d_expected[1] + z_expected[2] / d_expected[2];

        floatW tau_expected = new floatW(0.0f);
        intW info_expected = new intW(0);

        f2j.slaed6(kniter, orgati, rho, d_expected, 0, z_expected, 0, finit, tau_expected, info_expected);

        float[] d_actual = {1.0f, 2.0f, 3.0f};
        float[] z_actual = {0.5f, 0.5f, 0.5f};

        floatW tau_actual = new floatW(0.0f);
        intW info_actual = new intW(0);

        lapack.slaed6(kniter, orgati, rho, d_actual, 0, z_actual, 0, finit, tau_actual, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertEquals(tau_expected.val, tau_actual.val, sepsilon);
    }
}
