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

public class DnappsTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        // Test parameters for applying complex shifts in non-symmetric problems
        int n = 20;       // Problem size
        int np = 3;       // Number of shifts to apply
        int ldv = 20;     // Leading dimension of v
        int ldh = 8;      // Leading dimension of h (kev + np)
        int ldq = 8;      // Leading dimension of q

        intW kev = new intW(5);  // Number of eigenvalues to keep

        // Initialize test arrays
        double[] shiftr = generateDoubleRange(np, 1.5);  // Real parts of shifts
        double[] shifti = generateDoubleRange(np, 0.5);  // Imaginary parts of shifts
        double[] v = generateDoubleRange(n * (kev.val + np), 0.5);  // Arnoldi basis
        double[] h = generateNonsymmetricHessenberg(kev.val + np);  // Upper Hessenberg matrix
        double[] resid = generateDoubleRange(n, 0.1);  // Residual vector
        double[] q = new double[ldq * (kev.val + np)];  // Work array for rotations
        double[] workl = new double[(kev.val + np) * (kev.val + np)];  // Work array
        double[] workd = new double[2 * n];  // Work array

        // Call f2j reference implementation
        double[] expected_v = v.clone();
        double[] expected_h = h.clone();
        double[] expected_resid = resid.clone();
        double[] expected_q = q.clone();
        intW expected_kev = new intW(kev.val);

        f2j.dnapps(n, expected_kev, np, shiftr.clone(), shifti.clone(), expected_v, ldv,
                   expected_h, ldh, expected_resid, expected_q, ldq, workl.clone(), workd.clone());

        // Call implementation under test
        double[] actual_v = v.clone();
        double[] actual_h = h.clone();
        double[] actual_resid = resid.clone();
        double[] actual_q = q.clone();
        intW actual_kev = new intW(kev.val);

        arpack.dnapps(n, actual_kev, np, shiftr.clone(), shifti.clone(), actual_v, ldv,
                      actual_h, ldh, actual_resid, actual_q, ldq, workl.clone(), workd.clone());

        // Verify results
        // Note: Complex shift operations can have slightly larger numerical differences
        double tolerance = depsilon * 100;  // More lenient tolerance for complex operations
        assertEquals(expected_kev.val, actual_kev.val, "kev should match");
        assertArrayEquals(expected_v, actual_v, tolerance);
        assertArrayEquals(expected_h, actual_h, tolerance);
        assertArrayEquals(expected_resid, actual_resid, tolerance);
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testDifferentShiftCounts(ARPACK arpack) {
        // Test with different number of shifts
        int n = 20;
        int np = 5;  // More shifts
        int ldv = 20;
        int ldh = 10;
        int ldq = 10;

        intW kev = new intW(5);

        double[] shiftr = generateDoubleRange(np, 2.0);
        double[] shifti = generateDoubleRange(np, 1.0);
        double[] v = generateDoubleRange(n * (kev.val + np), 1.0);
        double[] h = generateNonsymmetricHessenberg(kev.val + np);
        double[] resid = generateDoubleRange(n, 0.2);
        double[] q = new double[ldq * (kev.val + np)];
        double[] workl = new double[(kev.val + np) * (kev.val + np)];
        double[] workd = new double[2 * n];

        // Call f2j reference implementation
        double[] expected_v = v.clone();
        double[] expected_h = h.clone();
        double[] expected_resid = resid.clone();
        intW expected_kev = new intW(kev.val);

        f2j.dnapps(n, expected_kev, np, shiftr.clone(), shifti.clone(), expected_v, ldv,
                   expected_h, ldh, expected_resid, new double[ldq * (kev.val + np)], ldq,
                   workl.clone(), workd.clone());

        // Call implementation under test
        double[] actual_v = v.clone();
        double[] actual_h = h.clone();
        double[] actual_resid = resid.clone();
        intW actual_kev = new intW(kev.val);

        arpack.dnapps(n, actual_kev, np, shiftr.clone(), shifti.clone(), actual_v, ldv,
                      actual_h, ldh, actual_resid, new double[ldq * (kev.val + np)], ldq,
                      workl.clone(), workd.clone());

        // Verify results
        // Note: Complex shift operations can have slightly larger numerical differences
        double tolerance = depsilon * 100;  // More lenient tolerance for complex operations
        assertEquals(expected_kev.val, actual_kev.val, "kev should match");
        assertArrayEquals(expected_v, actual_v, tolerance);
        assertArrayEquals(expected_h, actual_h, tolerance);
        assertArrayEquals(expected_resid, actual_resid, tolerance);
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testLargerProblem(ARPACK arpack) {
        // Test with larger problem size
        int n = 30;
        int np = 2;
        int ldv = 30;
        int ldh = 9;
        int ldq = 9;

        intW kev = new intW(7);

        double[] shiftr = generateDoubleRange(np, 0.5);
        double[] shifti = generateDoubleRange(np, 0.25);
        double[] v = generateDoubleRange(n * (kev.val + np), 0.3);
        double[] h = generateNonsymmetricHessenberg(kev.val + np);
        double[] resid = generateDoubleRange(n, 0.1);
        double[] q = new double[ldq * (kev.val + np)];
        double[] workl = new double[(kev.val + np) * (kev.val + np)];
        double[] workd = new double[2 * n];

        // Call f2j reference implementation
        double[] expected_v = v.clone();
        double[] expected_h = h.clone();
        double[] expected_resid = resid.clone();
        intW expected_kev = new intW(kev.val);

        f2j.dnapps(n, expected_kev, np, shiftr.clone(), shifti.clone(), expected_v, ldv,
                   expected_h, ldh, expected_resid, new double[ldq * (kev.val + np)], ldq,
                   workl.clone(), workd.clone());

        // Call implementation under test
        double[] actual_v = v.clone();
        double[] actual_h = h.clone();
        double[] actual_resid = resid.clone();
        intW actual_kev = new intW(kev.val);

        arpack.dnapps(n, actual_kev, np, shiftr.clone(), shifti.clone(), actual_v, ldv,
                      actual_h, ldh, actual_resid, new double[ldq * (kev.val + np)], ldq,
                      workl.clone(), workd.clone());

        // Verify results
        // Note: Complex shift operations can have slightly larger numerical differences
        double tolerance = depsilon * 100;  // More lenient tolerance for complex operations
        assertEquals(expected_kev.val, actual_kev.val, "kev should match");
        assertArrayEquals(expected_v, actual_v, tolerance);
        assertArrayEquals(expected_h, actual_h, tolerance);
        assertArrayEquals(expected_resid, actual_resid, tolerance);
    }

}
