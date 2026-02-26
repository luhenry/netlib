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

package dev.ludovic.netlib.arpack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import org.netlib.util.intW;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class DsgetsTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        // Test parameters for getting shifts for implicit restart
        int ishift = 1;  // Exact shifts
        String which = "LA";  // Largest algebraic
        int n = 10;

        intW kev = new intW(5);  // Number of eigenvalues to keep
        intW np = new intW(3);   // Number of shifts to apply

        double[] ritz = generateDoubleRange(n, 1.0);  // Ritz values
        double[] bounds = generateDoubleRange(n, 0.1);  // Error bounds
        double[] shifts = new double[n];  // Output shifts

        // Call f2j reference implementation
        intW expected_kev = new intW(kev.val);
        intW expected_np = new intW(np.val);
        double[] expected_ritz = ritz.clone();
        double[] expected_bounds = bounds.clone();
        double[] expected_shifts = shifts.clone();

        f2j.dsgets(ishift, which, expected_kev, expected_np, expected_ritz, expected_bounds, expected_shifts);

        // Call implementation under test
        intW actual_kev = new intW(kev.val);
        intW actual_np = new intW(np.val);
        double[] actual_ritz = ritz.clone();
        double[] actual_bounds = bounds.clone();
        double[] actual_shifts = shifts.clone();

        arpack.dsgets(ishift, which, actual_kev, actual_np, actual_ritz, actual_bounds, actual_shifts);

        // Verify results
        assertEquals(expected_kev.val, actual_kev.val, "kev mismatch");
        assertEquals(expected_np.val, actual_np.val, "np mismatch");
        assertArrayEquals(expected_ritz, actual_ritz, depsilon);
        assertArrayEquals(expected_bounds, actual_bounds, depsilon);
        assertArrayEquals(expected_shifts, actual_shifts, depsilon);
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSmallestAlgebraic(ARPACK arpack) {
        // Test with "SA" (smallest algebraic)
        int ishift = 1;
        String which = "SA";
        int n = 10;

        intW kev = new intW(5);
        intW np = new intW(3);

        double[] ritz = generateDoubleRange(n, 1.0);
        double[] bounds = generateDoubleRange(n, 0.1);
        double[] shifts = new double[n];

        // Call f2j reference implementation
        intW expected_kev = new intW(kev.val);
        intW expected_np = new intW(np.val);
        double[] expected_ritz = ritz.clone();
        double[] expected_bounds = bounds.clone();
        double[] expected_shifts = shifts.clone();

        f2j.dsgets(ishift, which, expected_kev, expected_np, expected_ritz, expected_bounds, expected_shifts);

        // Call implementation under test
        intW actual_kev = new intW(kev.val);
        intW actual_np = new intW(np.val);
        double[] actual_ritz = ritz.clone();
        double[] actual_bounds = bounds.clone();
        double[] actual_shifts = shifts.clone();

        arpack.dsgets(ishift, which, actual_kev, actual_np, actual_ritz, actual_bounds, actual_shifts);

        // Verify results
        assertEquals(expected_kev.val, actual_kev.val, "kev mismatch");
        assertEquals(expected_np.val, actual_np.val, "np mismatch");
        assertArrayEquals(expected_ritz, actual_ritz, depsilon);
        assertArrayEquals(expected_bounds, actual_bounds, depsilon);
        assertArrayEquals(expected_shifts, actual_shifts, depsilon);
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testBothEnds(ARPACK arpack) {
        // Test with "BE" (both ends)
        int ishift = 1;
        String which = "BE";
        int n = 10;

        intW kev = new intW(5);
        intW np = new intW(3);

        double[] ritz = generateDoubleRange(n, 1.0);
        double[] bounds = generateDoubleRange(n, 0.1);
        double[] shifts = new double[n];

        // Call f2j reference implementation
        intW expected_kev = new intW(kev.val);
        intW expected_np = new intW(np.val);
        double[] expected_ritz = ritz.clone();
        double[] expected_bounds = bounds.clone();
        double[] expected_shifts = shifts.clone();

        f2j.dsgets(ishift, which, expected_kev, expected_np, expected_ritz, expected_bounds, expected_shifts);

        // Call implementation under test
        intW actual_kev = new intW(kev.val);
        intW actual_np = new intW(np.val);
        double[] actual_ritz = ritz.clone();
        double[] actual_bounds = bounds.clone();
        double[] actual_shifts = shifts.clone();

        arpack.dsgets(ishift, which, actual_kev, actual_np, actual_ritz, actual_bounds, actual_shifts);

        // Verify results
        assertEquals(expected_kev.val, actual_kev.val, "kev mismatch");
        assertEquals(expected_np.val, actual_np.val, "np mismatch");
        assertArrayEquals(expected_ritz, actual_ritz, depsilon);
        assertArrayEquals(expected_bounds, actual_bounds, depsilon);
        assertArrayEquals(expected_shifts, actual_shifts, depsilon);
    }
}
