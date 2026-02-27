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

public class SlasrtTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;

        // Test ascending sort
        float[] d_expected = new float[]{5.0f, 3.0f, 8.0f, 1.0f, 9.0f, 2.0f, 7.0f, 4.0f, 6.0f, 10.0f};
        intW info_expected = new intW(0);
        f2j.slasrt("I", n, d_expected, 0, info_expected);

        float[] d_actual = new float[]{5.0f, 3.0f, 8.0f, 1.0f, 9.0f, 2.0f, 7.0f, 4.0f, 6.0f, 10.0f};
        intW info_actual = new intW(0);
        lapack.slasrt("I", n, d_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(d_expected, d_actual, sepsilon);

        // Test descending sort
        float[] d2_expected = new float[]{5.0f, 3.0f, 8.0f, 1.0f, 9.0f, 2.0f, 7.0f, 4.0f, 6.0f, 10.0f};
        info_expected.val = 0;
        f2j.slasrt("D", n, d2_expected, 0, info_expected);

        float[] d2_actual = new float[]{5.0f, 3.0f, 8.0f, 1.0f, 9.0f, 2.0f, 7.0f, 4.0f, 6.0f, 10.0f};
        info_actual.val = 0;
        lapack.slasrt("D", n, d2_actual, 0, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(d2_expected, d2_actual, sepsilon);
    }
}
