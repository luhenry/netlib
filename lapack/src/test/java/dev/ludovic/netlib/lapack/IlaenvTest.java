/*
 * Copyright 2020, 2022, Ludovic Henry
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

public class IlaenvTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        // ilaenv returns implementation-specific tuning parameters (block sizes).
        // Native LAPACK may return different values than F2j (e.g., 32 vs 64).
        // Verify the return values are positive and reasonable.

        // ispec=1: optimal block size for DGETRF
        int nb = lapack.ilaenv(1, "DGETRF", " ", 100, 100, -1, -1);
        assertTrue(nb > 0, "Block size for DGETRF should be positive, got " + nb);
        assertTrue(nb <= 256, "Block size for DGETRF should be <= 256, got " + nb);

        // ispec=1: optimal block size for DGEQRF
        nb = lapack.ilaenv(1, "DGEQRF", " ", 100, 100, -1, -1);
        assertTrue(nb > 0, "Block size for DGEQRF should be positive, got " + nb);
        assertTrue(nb <= 256, "Block size for DGEQRF should be <= 256, got " + nb);

        // ispec=2: minimum block size
        int nbmin = lapack.ilaenv(2, "DGETRF", " ", 100, 100, -1, -1);
        assertTrue(nbmin >= 1, "Min block size should be >= 1, got " + nbmin);
    }
}
