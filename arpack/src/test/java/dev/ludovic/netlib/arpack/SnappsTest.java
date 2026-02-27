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

public class SnappsTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        assumeFalse(arpack instanceof NativeARPACK && System.getProperty("os.name", "").toLowerCase().contains("mac"),
            "Skipped on macOS due to Accelerate float ABI issue");

        // Test parameters for applying complex shifts in non-symmetric problems
        int n = 20;       // Problem size
        int np = 3;       // Number of shifts to apply
        int ldv = 20;     // Leading dimension of v
        int ldh = 8;      // Leading dimension of h (kev + np)
        int ldq = 8;      // Leading dimension of q

        intW kev = new intW(5);  // Number of eigenvalues to keep

        // Initialize test arrays
        float[] shiftr = generateFloatRange(np, 1.5f);  // Real parts of shifts
        float[] shifti = generateFloatRange(np, 0.5f);  // Imaginary parts of shifts
        float[] v = generateFloatRange(n * (kev.val + np), 0.5f);  // Arnoldi basis
        float[] h = generateNonsymmetricHessenbergFloat(kev.val + np);  // Upper Hessenberg matrix
        float[] resid = generateFloatRange(n, 0.1f);  // Residual vector
        float[] q = new float[ldq * (kev.val + np)];  // Work array for rotations
        float[] workl = new float[(kev.val + np) * (kev.val + np)];  // Work array
        float[] workd = new float[2 * n];  // Work array

        // Call f2j reference implementation
        float[] expected_v = v.clone();
        float[] expected_h = h.clone();
        float[] expected_resid = resid.clone();
        float[] expected_q = q.clone();
        intW expected_kev = new intW(kev.val);

        f2j.snapps(n, expected_kev, np, shiftr.clone(), shifti.clone(), expected_v, ldv,
                   expected_h, ldh, expected_resid, expected_q, ldq, workl.clone(), workd.clone());

        // Call implementation under test
        float[] actual_v = v.clone();
        float[] actual_h = h.clone();
        float[] actual_resid = resid.clone();
        float[] actual_q = q.clone();
        intW actual_kev = new intW(kev.val);

        arpack.snapps(n, actual_kev, np, shiftr.clone(), shifti.clone(), actual_v, ldv,
                      actual_h, ldh, actual_resid, actual_q, ldq, workl.clone(), workd.clone());

        // Verify results
        // Note: Complex shift operations can have slightly larger numerical differences
        float tolerance = sepsilon * 100;  // More lenient tolerance for complex operations
        assertEquals(expected_kev.val, actual_kev.val, "kev should match");
        assertArrayEquals(expected_v, actual_v, tolerance);
        assertArrayEquals(expected_h, actual_h, tolerance);
        assertArrayEquals(expected_resid, actual_resid, tolerance);
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testDifferentShiftCounts(ARPACK arpack) {
        assumeFalse(arpack instanceof NativeARPACK && System.getProperty("os.name", "").toLowerCase().contains("mac"),
            "Skipped on macOS due to Accelerate float ABI issue");

        // Test with different number of shifts
        int n = 20;
        int np = 5;  // More shifts
        int ldv = 20;
        int ldh = 10;
        int ldq = 10;

        intW kev = new intW(5);

        float[] shiftr = generateFloatRange(np, 2.0f);
        float[] shifti = generateFloatRange(np, 1.0f);
        float[] v = generateFloatRange(n * (kev.val + np), 1.0f);
        float[] h = generateNonsymmetricHessenbergFloat(kev.val + np);
        float[] resid = generateFloatRange(n, 0.2f);
        float[] q = new float[ldq * (kev.val + np)];
        float[] workl = new float[(kev.val + np) * (kev.val + np)];
        float[] workd = new float[2 * n];

        // Call f2j reference implementation
        float[] expected_v = v.clone();
        float[] expected_h = h.clone();
        float[] expected_resid = resid.clone();
        intW expected_kev = new intW(kev.val);

        f2j.snapps(n, expected_kev, np, shiftr.clone(), shifti.clone(), expected_v, ldv,
                   expected_h, ldh, expected_resid, new float[ldq * (kev.val + np)], ldq,
                   workl.clone(), workd.clone());

        // Call implementation under test
        float[] actual_v = v.clone();
        float[] actual_h = h.clone();
        float[] actual_resid = resid.clone();
        intW actual_kev = new intW(kev.val);

        arpack.snapps(n, actual_kev, np, shiftr.clone(), shifti.clone(), actual_v, ldv,
                      actual_h, ldh, actual_resid, new float[ldq * (kev.val + np)], ldq,
                      workl.clone(), workd.clone());

        // Verify results
        // Note: Complex shift operations can have slightly larger numerical differences
        float tolerance = sepsilon * 100;  // More lenient tolerance for complex operations
        assertEquals(expected_kev.val, actual_kev.val, "kev should match");
        assertArrayEquals(expected_v, actual_v, tolerance);
        assertArrayEquals(expected_h, actual_h, tolerance);
        assertArrayEquals(expected_resid, actual_resid, tolerance);
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testLargerProblem(ARPACK arpack) {
        assumeFalse(arpack instanceof NativeARPACK && System.getProperty("os.name", "").toLowerCase().contains("mac"),
            "Skipped on macOS due to Accelerate float ABI issue");

        // Test with larger problem size
        int n = 30;
        int np = 2;
        int ldv = 30;
        int ldh = 9;
        int ldq = 9;

        intW kev = new intW(7);

        float[] shiftr = generateFloatRange(np, 0.5f);
        float[] shifti = generateFloatRange(np, 0.25f);
        float[] v = generateFloatRange(n * (kev.val + np), 0.3f);
        float[] h = generateNonsymmetricHessenbergFloat(kev.val + np);
        float[] resid = generateFloatRange(n, 0.1f);
        float[] q = new float[ldq * (kev.val + np)];
        float[] workl = new float[(kev.val + np) * (kev.val + np)];
        float[] workd = new float[2 * n];

        // Call f2j reference implementation
        float[] expected_v = v.clone();
        float[] expected_h = h.clone();
        float[] expected_resid = resid.clone();
        intW expected_kev = new intW(kev.val);

        f2j.snapps(n, expected_kev, np, shiftr.clone(), shifti.clone(), expected_v, ldv,
                   expected_h, ldh, expected_resid, new float[ldq * (kev.val + np)], ldq,
                   workl.clone(), workd.clone());

        // Call implementation under test
        float[] actual_v = v.clone();
        float[] actual_h = h.clone();
        float[] actual_resid = resid.clone();
        intW actual_kev = new intW(kev.val);

        arpack.snapps(n, actual_kev, np, shiftr.clone(), shifti.clone(), actual_v, ldv,
                      actual_h, ldh, actual_resid, new float[ldq * (kev.val + np)], ldq,
                      workl.clone(), workd.clone());

        // Verify results
        // Note: Complex shift operations can have slightly larger numerical differences
        float tolerance = sepsilon * 100;  // More lenient tolerance for complex operations
        assertEquals(expected_kev.val, actual_kev.val, "kev should match");
        assertArrayEquals(expected_v, actual_v, tolerance);
        assertArrayEquals(expected_h, actual_h, tolerance);
        assertArrayEquals(expected_resid, actual_resid, tolerance);
    }

}
