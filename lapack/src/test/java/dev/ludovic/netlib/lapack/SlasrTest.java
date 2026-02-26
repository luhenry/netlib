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

public class SlasrTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int m = N_SMALL;
        int n = N_SMALL;

        float[] c = new float[m - 1];
        float[] s = new float[m - 1];
        for (int i = 0; i < m - 1; i++) {
            float angle = (i + 1) * (float)Math.PI / (2.0f * m);
            c[i] = (float)Math.cos(angle);
            s[i] = (float)Math.sin(angle);
        }

        float[] a_expected = generateMatrixFloat(m, n, 1.0f);
        f2j.slasr("L", "V", "F", m, n, c, 0, s, 0, a_expected, 0, m);

        float[] a_actual = generateMatrixFloat(m, n, 1.0f);
        lapack.slasr("L", "V", "F", m, n, c.clone(), 0, s.clone(), 0, a_actual, 0, m);

        assertArrayEquals(a_expected, a_actual, Math.scalb(sepsilon, Math.getExponent(Math.max(getMaxValue(a_expected), 1.0f))));

        float[] cr = new float[n - 1];
        float[] sr = new float[n - 1];
        for (int i = 0; i < n - 1; i++) {
            float angle = (i + 1) * (float)Math.PI / (2.0f * n);
            cr[i] = (float)Math.cos(angle);
            sr[i] = (float)Math.sin(angle);
        }

        float[] a2_expected = generateMatrixFloat(m, n, 1.0f);
        f2j.slasr("R", "V", "F", m, n, cr, 0, sr, 0, a2_expected, 0, m);

        float[] a2_actual = generateMatrixFloat(m, n, 1.0f);
        lapack.slasr("R", "V", "F", m, n, cr.clone(), 0, sr.clone(), 0, a2_actual, 0, m);

        assertArrayEquals(a2_expected, a2_actual, Math.scalb(sepsilon, Math.getExponent(Math.max(getMaxValue(a2_expected), 1.0f))));
    }
}
