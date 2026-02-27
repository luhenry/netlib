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

public class Slasq2Test extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = 5;
        float[] z_expected = new float[4 * n];
        float[] d = {10.0f, 8.0f, 6.0f, 4.0f, 2.0f};
        float[] e = {0.5f, 0.4f, 0.3f, 0.2f};
        for (int i = 0; i < n; i++) {
            z_expected[4 * i] = d[i] * d[i];
            z_expected[4 * i + 1] = (i < n - 1) ? e[i] * e[i] : 0.0f;
            z_expected[4 * i + 2] = z_expected[4 * i];
            z_expected[4 * i + 3] = z_expected[4 * i + 1];
        }
        intW info_expected = new intW(0);
        f2j.slasq2(n, z_expected, 0, info_expected);

        float[] z_actual = new float[4 * n];
        for (int i = 0; i < n; i++) {
            z_actual[4 * i] = d[i] * d[i];
            z_actual[4 * i + 1] = (i < n - 1) ? e[i] * e[i] : 0.0f;
            z_actual[4 * i + 2] = z_actual[4 * i];
            z_actual[4 * i + 3] = z_actual[4 * i + 1];
        }
        intW info_actual = new intW(0);
        lapack.slasq2(n, z_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        // Only compare eigenvalues Z(0:n-1), not iteration counts and diagnostics
        float[] eigenvalues_expected = new float[n];
        float[] eigenvalues_actual = new float[n];
        System.arraycopy(z_expected, 0, eigenvalues_expected, 0, n);
        System.arraycopy(z_actual, 0, eigenvalues_actual, 0, n);
        assertArrayEquals(eigenvalues_expected, eigenvalues_actual, Math.scalb(sepsilon, Math.getExponent(Math.max(getMaxValue(eigenvalues_expected), 1.0f))));
    }
}
