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

public class SlatrdTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // v3.1 uses SLARFP, v3.12 uses SLARFG, producing different but
        // mathematically equivalent Householder vectors. Only compare the
        // off-diagonal elements (e) which are deterministic.
        int n = N_SMALL;
        int nb = 3;

        float[] a_expected = generateSymmetricMatrixFloat(n);
        float[] e_expected = new float[n];
        float[] tau_expected = new float[n];
        float[] w_expected = new float[n * nb];
        f2j.slatrd("U", n, nb, a_expected, 0, n, e_expected, 0, tau_expected, 0, w_expected, 0, n);

        float[] a_actual = generateSymmetricMatrixFloat(n);
        float[] e_actual = new float[n];
        float[] tau_actual = new float[n];
        float[] w_actual = new float[n * nb];
        lapack.slatrd("U", n, nb, a_actual, 0, n, e_actual, 0, tau_actual, 0, w_actual, 0, n);

        // Off-diagonal elements should match regardless of reflector convention
        assertRelArrayEquals(e_expected, e_actual, sepsilon * 10);
    }
}
