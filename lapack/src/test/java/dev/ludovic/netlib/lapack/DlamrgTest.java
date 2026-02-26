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

public class DlamrgTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // Test merging two sorted lists
        // dlamrg expects both lists concatenated in a single array
        double[] a = new double[]{1.0, 3.0, 5.0, 7.0, 9.0, 2.0, 4.0, 6.0, 8.0};

        int[] index_expected = new int[9];
        f2j.dlamrg(5, 4, a.clone(), 0, 1, 1, index_expected, 0);

        int[] index_actual = new int[9];
        lapack.dlamrg(5, 4, a.clone(), 0, 1, 1, index_actual, 0);

        assertArrayEquals(index_expected, index_actual);

        // Test with descending order
        double[] a2 = new double[]{9.0, 7.0, 5.0, 3.0, 1.0, 8.0, 6.0, 4.0, 2.0};

        int[] index_expected2 = new int[9];
        f2j.dlamrg(5, 4, a2.clone(), 0, -1, -1, index_expected2, 0);

        int[] index_actual2 = new int[9];
        lapack.dlamrg(5, 4, a2.clone(), 0, -1, -1, index_actual2, 0);

        assertArrayEquals(index_expected2, index_actual2);
    }
}
