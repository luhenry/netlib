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

import static dev.ludovic.netlib.test.TestHelpers.*;

public class DsappsTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        // Test parameters for applying shifts
        int n = 20;       // Problem size
        int kev = 5;      // Number of eigenvalues to keep
        int np = 3;       // Number of shifts to apply
        int ldv = 20;     // Leading dimension of v
        int ldh = 8;      // Leading dimension of h (kev + np)
        int ldq = 8;      // Leading dimension of q

        // Initialize test arrays
        double[] shift = generateDoubleRange(np, 1.5);  // Shifts to apply
        double[] v = generateDoubleRange(n * (kev + np), 0.5);  // Arnoldi basis
        double[] h = generateSymmetricTridiagonal(kev + np);  // Hessenberg matrix
        double[] resid = generateDoubleRange(n, 0.1);  // Residual vector
        double[] q = new double[ldq * (kev + np)];  // Work array for rotations
        double[] workd = new double[2 * n];  // Work array

        // Call f2j reference implementation
        double[] expected_v = v.clone();
        double[] expected_h = h.clone();
        double[] expected_resid = resid.clone();
        double[] expected_q = q.clone();

        f2j.dsapps(n, kev, np, shift, expected_v, ldv, expected_h, ldh, expected_resid, expected_q, ldq, workd.clone());

        // Call implementation under test
        double[] actual_v = v.clone();
        double[] actual_h = h.clone();
        double[] actual_resid = resid.clone();
        double[] actual_q = q.clone();

        arpack.dsapps(n, kev, np, shift, actual_v, ldv, actual_h, ldh, actual_resid, actual_q, ldq, workd.clone());

        // Verify results
        assertArrayEquals(expected_v, actual_v, depsilon);
        assertArrayEquals(expected_h, actual_h, depsilon);
        assertArrayEquals(expected_resid, actual_resid, depsilon);
        assertArrayEquals(expected_q, actual_q, depsilon);
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testDifferentShiftCounts(ARPACK arpack) {
        // Test with different number of shifts
        int n = 20;
        int kev = 5;
        int np = 5;  // More shifts
        int ldv = 20;
        int ldh = 10;
        int ldq = 10;

        double[] shift = generateDoubleRange(np, 2.0);
        double[] v = generateDoubleRange(n * (kev + np), 1.0);
        double[] h = generateSymmetricTridiagonal(kev + np);
        double[] resid = generateDoubleRange(n, 0.2);
        double[] q = new double[ldq * (kev + np)];
        double[] workd = new double[2 * n];

        // Call f2j reference implementation
        double[] expected_v = v.clone();
        double[] expected_h = h.clone();
        double[] expected_resid = resid.clone();
        double[] expected_q = q.clone();

        f2j.dsapps(n, kev, np, shift, expected_v, ldv, expected_h, ldh, expected_resid, expected_q, ldq, workd.clone());

        // Call implementation under test
        double[] actual_v = v.clone();
        double[] actual_h = h.clone();
        double[] actual_resid = resid.clone();
        double[] actual_q = q.clone();

        arpack.dsapps(n, kev, np, shift, actual_v, ldv, actual_h, ldh, actual_resid, actual_q, ldq, workd.clone());

        // Verify results
        assertArrayEquals(expected_v, actual_v, depsilon);
        assertArrayEquals(expected_h, actual_h, depsilon);
        assertArrayEquals(expected_resid, actual_resid, depsilon);
        assertArrayEquals(expected_q, actual_q, depsilon);
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testLargerProblem(ARPACK arpack) {
        // Test with larger problem size
        int n = 30;  // Use moderate size to avoid numerical issues
        int kev = 6;
        int np = 2;
        int ldv = 30;
        int ldh = 8;
        int ldq = 8;

        double[] shift = generateDoubleRange(np, 0.5);
        double[] v = generateDoubleRange(n * (kev + np), 0.3);
        double[] h = generateSymmetricTridiagonal(kev + np);
        double[] resid = generateDoubleRange(n, 0.1);
        double[] q = new double[ldq * (kev + np)];
        double[] workd = new double[2 * n];

        // Call f2j reference implementation
        double[] expected_v = v.clone();
        double[] expected_h = h.clone();
        double[] expected_resid = resid.clone();
        double[] expected_q = q.clone();

        f2j.dsapps(n, kev, np, shift, expected_v, ldv, expected_h, ldh, expected_resid, expected_q, ldq, workd.clone());

        // Call implementation under test
        double[] actual_v = v.clone();
        double[] actual_h = h.clone();
        double[] actual_resid = resid.clone();
        double[] actual_q = q.clone();

        arpack.dsapps(n, kev, np, shift, actual_v, ldv, actual_h, ldh, actual_resid, actual_q, ldq, workd.clone());

        // Verify results
        assertArrayEquals(expected_v, actual_v, depsilon);
        assertArrayEquals(expected_h, actual_h, depsilon);
        assertArrayEquals(expected_resid, actual_resid, depsilon);
        assertArrayEquals(expected_q, actual_q, depsilon);
    }

}
