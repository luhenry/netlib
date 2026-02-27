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

public class SlasdtTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;
        int msub = 3;

        intW lvl_expected = new intW(0);
        intW nd_expected = new intW(0);
        int[] inode_expected = new int[n];
        int[] ndiml_expected = new int[n];
        int[] ndimr_expected = new int[n];

        f2j.slasdt(n, lvl_expected, nd_expected, inode_expected, 0, ndiml_expected, 0, ndimr_expected, 0, msub);

        intW lvl_actual = new intW(0);
        intW nd_actual = new intW(0);
        int[] inode_actual = new int[n];
        int[] ndiml_actual = new int[n];
        int[] ndimr_actual = new int[n];

        lapack.slasdt(n, lvl_actual, nd_actual, inode_actual, 0, ndiml_actual, 0, ndimr_actual, 0, msub);

        assertEquals(lvl_expected.val, lvl_actual.val);
        assertEquals(nd_expected.val, nd_actual.val);
        assertArrayEquals(inode_expected, inode_actual);
        assertArrayEquals(ndiml_expected, ndiml_actual);
        assertArrayEquals(ndimr_expected, ndimr_actual);
    }
}
