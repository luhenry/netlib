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

public class DngetsTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        // Test parameters for getting complex shifts for implicit restart
        int ishift = 1;  // Exact shifts
        String which = "LM";  // Largest magnitude
        int n = 10;

        intW kev = new intW(5);  // Number of eigenvalues to keep
        intW np = new intW(3);   // Number of shifts to apply

        double[] ritzr = generateDoubleRange(n, 1.0);  // Real parts of Ritz values
        double[] ritzi = generateDoubleRange(n, 0.5);  // Imaginary parts of Ritz values
        double[] bounds = generateDoubleRange(n, 0.1);  // Error bounds
        double[] shiftr = new double[n];  // Real parts of output shifts
        double[] shifti = new double[n];  // Imaginary parts of output shifts

        // Call f2j reference implementation
        intW expected_kev = new intW(kev.val);
        intW expected_np = new intW(np.val);
        double[] expected_ritzr = ritzr.clone();
        double[] expected_ritzi = ritzi.clone();
        double[] expected_bounds = bounds.clone();
        double[] expected_shiftr = shiftr.clone();
        double[] expected_shifti = shifti.clone();

        f2j.dngets(ishift, which, expected_kev, expected_np, expected_ritzr, expected_ritzi, expected_bounds, expected_shiftr, expected_shifti);

        // Call implementation under test
        intW actual_kev = new intW(kev.val);
        intW actual_np = new intW(np.val);
        double[] actual_ritzr = ritzr.clone();
        double[] actual_ritzi = ritzi.clone();
        double[] actual_bounds = bounds.clone();
        double[] actual_shiftr = shiftr.clone();
        double[] actual_shifti = shifti.clone();

        arpack.dngets(ishift, which, actual_kev, actual_np, actual_ritzr, actual_ritzi, actual_bounds, actual_shiftr, actual_shifti);

        // Verify results
        assertEquals(expected_kev.val, actual_kev.val, "kev mismatch");
        assertEquals(expected_np.val, actual_np.val, "np mismatch");
        assertArrayEquals(expected_ritzr, actual_ritzr, depsilon);
        assertArrayEquals(expected_ritzi, actual_ritzi, depsilon);
        assertArrayEquals(expected_bounds, actual_bounds, depsilon);
        assertArrayEquals(expected_shiftr, actual_shiftr, depsilon);
        assertArrayEquals(expected_shifti, actual_shifti, depsilon);
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSmallestMagnitude(ARPACK arpack) {
        // Test with "SM" (smallest magnitude)
        int ishift = 1;
        String which = "SM";
        int n = 10;

        intW kev = new intW(5);
        intW np = new intW(3);

        double[] ritzr = generateDoubleRange(n, 1.0);
        double[] ritzi = generateDoubleRange(n, 0.5);
        double[] bounds = generateDoubleRange(n, 0.1);
        double[] shiftr = new double[n];
        double[] shifti = new double[n];

        // Call f2j reference implementation
        intW expected_kev = new intW(kev.val);
        intW expected_np = new intW(np.val);
        double[] expected_ritzr = ritzr.clone();
        double[] expected_ritzi = ritzi.clone();
        double[] expected_bounds = bounds.clone();
        double[] expected_shiftr = shiftr.clone();
        double[] expected_shifti = shifti.clone();

        f2j.dngets(ishift, which, expected_kev, expected_np, expected_ritzr, expected_ritzi, expected_bounds, expected_shiftr, expected_shifti);

        // Call implementation under test
        intW actual_kev = new intW(kev.val);
        intW actual_np = new intW(np.val);
        double[] actual_ritzr = ritzr.clone();
        double[] actual_ritzi = ritzi.clone();
        double[] actual_bounds = bounds.clone();
        double[] actual_shiftr = shiftr.clone();
        double[] actual_shifti = shifti.clone();

        arpack.dngets(ishift, which, actual_kev, actual_np, actual_ritzr, actual_ritzi, actual_bounds, actual_shiftr, actual_shifti);

        // Verify results
        assertEquals(expected_kev.val, actual_kev.val, "kev mismatch");
        assertEquals(expected_np.val, actual_np.val, "np mismatch");
        assertArrayEquals(expected_ritzr, actual_ritzr, depsilon);
        assertArrayEquals(expected_ritzi, actual_ritzi, depsilon);
        assertArrayEquals(expected_bounds, actual_bounds, depsilon);
        assertArrayEquals(expected_shiftr, actual_shiftr, depsilon);
        assertArrayEquals(expected_shifti, actual_shifti, depsilon);
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testLargestReal(ARPACK arpack) {
        // Test with "LR" (largest real part)
        int ishift = 1;
        String which = "LR";
        int n = 10;

        intW kev = new intW(5);
        intW np = new intW(3);

        double[] ritzr = generateDoubleRange(n, 2.0);
        double[] ritzi = generateDoubleRange(n, 1.0);
        double[] bounds = generateDoubleRange(n, 0.05);
        double[] shiftr = new double[n];
        double[] shifti = new double[n];

        // Call f2j reference implementation
        intW expected_kev = new intW(kev.val);
        intW expected_np = new intW(np.val);
        double[] expected_ritzr = ritzr.clone();
        double[] expected_ritzi = ritzi.clone();
        double[] expected_bounds = bounds.clone();
        double[] expected_shiftr = shiftr.clone();
        double[] expected_shifti = shifti.clone();

        f2j.dngets(ishift, which, expected_kev, expected_np, expected_ritzr, expected_ritzi, expected_bounds, expected_shiftr, expected_shifti);

        // Call implementation under test
        intW actual_kev = new intW(kev.val);
        intW actual_np = new intW(np.val);
        double[] actual_ritzr = ritzr.clone();
        double[] actual_ritzi = ritzi.clone();
        double[] actual_bounds = bounds.clone();
        double[] actual_shiftr = shiftr.clone();
        double[] actual_shifti = shifti.clone();

        arpack.dngets(ishift, which, actual_kev, actual_np, actual_ritzr, actual_ritzi, actual_bounds, actual_shiftr, actual_shifti);

        // Verify results
        assertEquals(expected_kev.val, actual_kev.val, "kev mismatch");
        assertEquals(expected_np.val, actual_np.val, "np mismatch");
        assertArrayEquals(expected_ritzr, actual_ritzr, depsilon);
        assertArrayEquals(expected_ritzi, actual_ritzi, depsilon);
        assertArrayEquals(expected_bounds, actual_bounds, depsilon);
        assertArrayEquals(expected_shiftr, actual_shiftr, depsilon);
        assertArrayEquals(expected_shifti, actual_shifti, depsilon);
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testLargestImaginary(ARPACK arpack) {
        // Test with "LI" (largest imaginary part)
        int ishift = 1;
        String which = "LI";
        int n = 10;

        intW kev = new intW(5);
        intW np = new intW(3);

        double[] ritzr = generateDoubleRange(n, 1.0);
        double[] ritzi = generateDoubleRange(n, 2.0);
        double[] bounds = generateDoubleRange(n, 0.08);
        double[] shiftr = new double[n];
        double[] shifti = new double[n];

        // Call f2j reference implementation
        intW expected_kev = new intW(kev.val);
        intW expected_np = new intW(np.val);
        double[] expected_ritzr = ritzr.clone();
        double[] expected_ritzi = ritzi.clone();
        double[] expected_bounds = bounds.clone();
        double[] expected_shiftr = shiftr.clone();
        double[] expected_shifti = shifti.clone();

        f2j.dngets(ishift, which, expected_kev, expected_np, expected_ritzr, expected_ritzi, expected_bounds, expected_shiftr, expected_shifti);

        // Call implementation under test
        intW actual_kev = new intW(kev.val);
        intW actual_np = new intW(np.val);
        double[] actual_ritzr = ritzr.clone();
        double[] actual_ritzi = ritzi.clone();
        double[] actual_bounds = bounds.clone();
        double[] actual_shiftr = shiftr.clone();
        double[] actual_shifti = shifti.clone();

        arpack.dngets(ishift, which, actual_kev, actual_np, actual_ritzr, actual_ritzi, actual_bounds, actual_shiftr, actual_shifti);

        // Verify results
        assertEquals(expected_kev.val, actual_kev.val, "kev mismatch");
        assertEquals(expected_np.val, actual_np.val, "np mismatch");
        assertArrayEquals(expected_ritzr, actual_ritzr, depsilon);
        assertArrayEquals(expected_ritzi, actual_ritzi, depsilon);
        assertArrayEquals(expected_bounds, actual_bounds, depsilon);
        assertArrayEquals(expected_shiftr, actual_shiftr, depsilon);
        assertArrayEquals(expected_shifti, actual_shifti, depsilon);
    }
}
