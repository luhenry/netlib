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

public class DlasclTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test scaling full matrix
        intW info_expected = new intW(0);
        double[] expected = dMatrix.clone();
        f2j.dlascl("G", 0, 0, 1.0, 2.0, N, N, expected, 0, N, info_expected);

        intW info_actual = new intW(0);
        double[] actual = dMatrix.clone();
        lapack.dlascl("G", 0, 0, 1.0, 2.0, N, N, actual, 0, N, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(expected, actual, depsilon);

        // Test scaling upper triangular part
        info_expected.val = 0;
        double[] expected_upper = dMatrix.clone();
        f2j.dlascl("U", 0, 0, 2.0, 3.0, N, N, expected_upper, 0, N, info_expected);

        info_actual.val = 0;
        double[] actual_upper = dMatrix.clone();
        lapack.dlascl("U", 0, 0, 2.0, 3.0, N, N, actual_upper, 0, N, info_actual);

        assertEquals(info_expected.val, info_actual.val);
        assertArrayEquals(expected_upper, actual_upper, depsilon);
    }
}
