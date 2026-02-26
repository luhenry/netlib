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
import static org.junit.jupiter.api.Assumptions.*;

import org.netlib.util.intW;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class SngetsTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        assumeFalse(arpack instanceof NativeARPACK && System.getProperty("os.name", "").toLowerCase().contains("mac"));

        // Test parameters for getting complex shifts for implicit restart
        int ishift = 1;  // Exact shifts
        String which = "LM";  // Largest magnitude
        int n = 10;

        intW kev = new intW(5);  // Number of eigenvalues to keep
        intW np = new intW(3);   // Number of shifts to apply

        float[] ritzr = generateFloatRange(n, 1.0f);  // Real parts of Ritz values
        float[] ritzi = generateFloatRange(n, 0.5f);  // Imaginary parts of Ritz values
        float[] bounds = generateFloatRange(n, 0.1f);  // Error bounds
        float[] shiftr = new float[n];  // Real parts of output shifts
        float[] shifti = new float[n];  // Imaginary parts of output shifts

        // Call f2j reference implementation
        intW expected_kev = new intW(kev.val);
        intW expected_np = new intW(np.val);
        float[] expected_ritzr = ritzr.clone();
        float[] expected_ritzi = ritzi.clone();
        float[] expected_bounds = bounds.clone();
        float[] expected_shiftr = shiftr.clone();
        float[] expected_shifti = shifti.clone();

        f2j.sngets(ishift, which, expected_kev, expected_np, expected_ritzr, expected_ritzi, expected_bounds, expected_shiftr, expected_shifti);

        // Call implementation under test
        intW actual_kev = new intW(kev.val);
        intW actual_np = new intW(np.val);
        float[] actual_ritzr = ritzr.clone();
        float[] actual_ritzi = ritzi.clone();
        float[] actual_bounds = bounds.clone();
        float[] actual_shiftr = shiftr.clone();
        float[] actual_shifti = shifti.clone();

        arpack.sngets(ishift, which, actual_kev, actual_np, actual_ritzr, actual_ritzi, actual_bounds, actual_shiftr, actual_shifti);

        // Verify results
        assertEquals(expected_kev.val, actual_kev.val, "kev mismatch");
        assertEquals(expected_np.val, actual_np.val, "np mismatch");
        assertArrayEquals(expected_ritzr, actual_ritzr, sepsilon);
        assertArrayEquals(expected_ritzi, actual_ritzi, sepsilon);
        assertArrayEquals(expected_bounds, actual_bounds, sepsilon);
        assertArrayEquals(expected_shiftr, actual_shiftr, sepsilon);
        assertArrayEquals(expected_shifti, actual_shifti, sepsilon);
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSmallestMagnitude(ARPACK arpack) {
        assumeFalse(arpack instanceof NativeARPACK && System.getProperty("os.name", "").toLowerCase().contains("mac"));

        // Test with "SM" (smallest magnitude)
        int ishift = 1;
        String which = "SM";
        int n = 10;

        intW kev = new intW(5);
        intW np = new intW(3);

        float[] ritzr = generateFloatRange(n, 1.0f);
        float[] ritzi = generateFloatRange(n, 0.5f);
        float[] bounds = generateFloatRange(n, 0.1f);
        float[] shiftr = new float[n];
        float[] shifti = new float[n];

        // Call f2j reference implementation
        intW expected_kev = new intW(kev.val);
        intW expected_np = new intW(np.val);
        float[] expected_ritzr = ritzr.clone();
        float[] expected_ritzi = ritzi.clone();
        float[] expected_bounds = bounds.clone();
        float[] expected_shiftr = shiftr.clone();
        float[] expected_shifti = shifti.clone();

        f2j.sngets(ishift, which, expected_kev, expected_np, expected_ritzr, expected_ritzi, expected_bounds, expected_shiftr, expected_shifti);

        // Call implementation under test
        intW actual_kev = new intW(kev.val);
        intW actual_np = new intW(np.val);
        float[] actual_ritzr = ritzr.clone();
        float[] actual_ritzi = ritzi.clone();
        float[] actual_bounds = bounds.clone();
        float[] actual_shiftr = shiftr.clone();
        float[] actual_shifti = shifti.clone();

        arpack.sngets(ishift, which, actual_kev, actual_np, actual_ritzr, actual_ritzi, actual_bounds, actual_shiftr, actual_shifti);

        // Verify results
        assertEquals(expected_kev.val, actual_kev.val, "kev mismatch");
        assertEquals(expected_np.val, actual_np.val, "np mismatch");
        assertArrayEquals(expected_ritzr, actual_ritzr, sepsilon);
        assertArrayEquals(expected_ritzi, actual_ritzi, sepsilon);
        assertArrayEquals(expected_bounds, actual_bounds, sepsilon);
        assertArrayEquals(expected_shiftr, actual_shiftr, sepsilon);
        assertArrayEquals(expected_shifti, actual_shifti, sepsilon);
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

        float[] ritzr = generateFloatRange(n, 2.0f);
        float[] ritzi = generateFloatRange(n, 1.0f);
        float[] bounds = generateFloatRange(n, 0.05f);
        float[] shiftr = new float[n];
        float[] shifti = new float[n];

        // Call f2j reference implementation
        intW expected_kev = new intW(kev.val);
        intW expected_np = new intW(np.val);
        float[] expected_ritzr = ritzr.clone();
        float[] expected_ritzi = ritzi.clone();
        float[] expected_bounds = bounds.clone();
        float[] expected_shiftr = shiftr.clone();
        float[] expected_shifti = shifti.clone();

        f2j.sngets(ishift, which, expected_kev, expected_np, expected_ritzr, expected_ritzi, expected_bounds, expected_shiftr, expected_shifti);

        // Call implementation under test
        intW actual_kev = new intW(kev.val);
        intW actual_np = new intW(np.val);
        float[] actual_ritzr = ritzr.clone();
        float[] actual_ritzi = ritzi.clone();
        float[] actual_bounds = bounds.clone();
        float[] actual_shiftr = shiftr.clone();
        float[] actual_shifti = shifti.clone();

        arpack.sngets(ishift, which, actual_kev, actual_np, actual_ritzr, actual_ritzi, actual_bounds, actual_shiftr, actual_shifti);

        // Verify results
        assertEquals(expected_kev.val, actual_kev.val, "kev mismatch");
        assertEquals(expected_np.val, actual_np.val, "np mismatch");
        assertArrayEquals(expected_ritzr, actual_ritzr, sepsilon);
        assertArrayEquals(expected_ritzi, actual_ritzi, sepsilon);
        assertArrayEquals(expected_bounds, actual_bounds, sepsilon);
        assertArrayEquals(expected_shiftr, actual_shiftr, sepsilon);
        assertArrayEquals(expected_shifti, actual_shifti, sepsilon);
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

        float[] ritzr = generateFloatRange(n, 1.0f);
        float[] ritzi = generateFloatRange(n, 2.0f);
        float[] bounds = generateFloatRange(n, 0.08f);
        float[] shiftr = new float[n];
        float[] shifti = new float[n];

        // Call f2j reference implementation
        intW expected_kev = new intW(kev.val);
        intW expected_np = new intW(np.val);
        float[] expected_ritzr = ritzr.clone();
        float[] expected_ritzi = ritzi.clone();
        float[] expected_bounds = bounds.clone();
        float[] expected_shiftr = shiftr.clone();
        float[] expected_shifti = shifti.clone();

        f2j.sngets(ishift, which, expected_kev, expected_np, expected_ritzr, expected_ritzi, expected_bounds, expected_shiftr, expected_shifti);

        // Call implementation under test
        intW actual_kev = new intW(kev.val);
        intW actual_np = new intW(np.val);
        float[] actual_ritzr = ritzr.clone();
        float[] actual_ritzi = ritzi.clone();
        float[] actual_bounds = bounds.clone();
        float[] actual_shiftr = shiftr.clone();
        float[] actual_shifti = shifti.clone();

        arpack.sngets(ishift, which, actual_kev, actual_np, actual_ritzr, actual_ritzi, actual_bounds, actual_shiftr, actual_shifti);

        // Verify results
        assertEquals(expected_kev.val, actual_kev.val, "kev mismatch");
        assertEquals(expected_np.val, actual_np.val, "np mismatch");
        assertArrayEquals(expected_ritzr, actual_ritzr, sepsilon);
        assertArrayEquals(expected_ritzi, actual_ritzi, sepsilon);
        assertArrayEquals(expected_bounds, actual_bounds, sepsilon);
        assertArrayEquals(expected_shiftr, actual_shiftr, sepsilon);
        assertArrayEquals(expected_shifti, actual_shifti, sepsilon);
    }
}
