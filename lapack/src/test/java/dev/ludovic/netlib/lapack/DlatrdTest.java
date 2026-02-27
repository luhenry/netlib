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

import static dev.ludovic.netlib.test.TestHelpers.*;

public class DlatrdTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        int nb = 3;

        double[] a_expected = generateSymmetricMatrix(n);
        double[] e_expected = new double[n];
        double[] tau_expected = new double[n];
        double[] w_expected = new double[n * nb];
        f2j.dlatrd("U", n, nb, a_expected, 0, n, e_expected, 0, tau_expected, 0, w_expected, 0, n);

        double[] a_actual = generateSymmetricMatrix(n);
        double[] e_actual = new double[n];
        double[] tau_actual = new double[n];
        double[] w_actual = new double[n * nb];
        lapack.dlatrd("U", n, nb, a_actual, 0, n, e_actual, 0, tau_actual, 0, w_actual, 0, n);

        // v3.1 uses DLARFP, v3.12 uses DLARFG, producing different but
        // mathematically equivalent Householder vectors. Only compare the
        // off-diagonal elements (e) which are deterministic.
        assertRelArrayEquals(e_expected, e_actual, depsilon * 100);
    }
}
