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

public class Slar2vTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = 10;
        float[] x_expected = generateFloatArray(n, 1.0f);
        float[] y_expected = generateFloatArray(n, 2.0f);
        float[] z_expected = generateFloatArray(n, 3.0f);
        float[] c = generateFloatArray(n, 0.6f);  // cos
        float[] s = generateFloatArray(n, 0.8f);  // sin
        f2j.slar2v(n, x_expected, 0, y_expected, 0, z_expected, 0, 1, c, 0, s, 0, 1);

        float[] x_actual = generateFloatArray(n, 1.0f);
        float[] y_actual = generateFloatArray(n, 2.0f);
        float[] z_actual = generateFloatArray(n, 3.0f);
        lapack.slar2v(n, x_actual, 0, y_actual, 0, z_actual, 0, 1, c, 0, s, 0, 1);

        assertRelArrayEquals(x_expected, x_actual, sepsilon);
        assertRelArrayEquals(y_expected, y_actual, sepsilon);
        assertRelArrayEquals(z_expected, z_actual, sepsilon);
    }
}
