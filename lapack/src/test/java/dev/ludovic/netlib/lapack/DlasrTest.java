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

public class DlasrTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;

        // Cosines and sines for plane rotations
        double[] c = new double[m - 1];
        double[] s = new double[m - 1];
        for (int i = 0; i < m - 1; i++) {
            double angle = (i + 1) * Math.PI / (2.0 * m);
            c[i] = Math.cos(angle);
            s[i] = Math.sin(angle);
        }

        // Test left side, variable pivot, forward direction
        double[] a_expected = generateMatrix(m, n, 1.0);
        f2j.dlasr("L", "V", "F", m, n, c, 0, s, 0, a_expected, 0, m);

        double[] a_actual = generateMatrix(m, n, 1.0);
        lapack.dlasr("L", "V", "F", m, n, c.clone(), 0, s.clone(), 0, a_actual, 0, m);

        assertArrayEquals(a_expected, a_actual, Math.scalb(depsilon, Math.getExponent(Math.max(getMaxValue(a_expected), 1.0))));

        // Test right side, variable pivot, forward direction
        double[] cr = new double[n - 1];
        double[] sr = new double[n - 1];
        for (int i = 0; i < n - 1; i++) {
            double angle = (i + 1) * Math.PI / (2.0 * n);
            cr[i] = Math.cos(angle);
            sr[i] = Math.sin(angle);
        }

        double[] a2_expected = generateMatrix(m, n, 1.0);
        f2j.dlasr("R", "V", "F", m, n, cr, 0, sr, 0, a2_expected, 0, m);

        double[] a2_actual = generateMatrix(m, n, 1.0);
        lapack.dlasr("R", "V", "F", m, n, cr.clone(), 0, sr.clone(), 0, a2_actual, 0, m);

        assertArrayEquals(a2_expected, a2_actual, Math.scalb(depsilon, Math.getExponent(Math.max(getMaxValue(a2_expected), 1.0))));
    }
}
