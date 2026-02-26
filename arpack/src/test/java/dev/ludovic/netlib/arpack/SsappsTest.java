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

public class SsappsTest extends ARPACKTest {

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
        float[] shift = generateFloatRange(np, 1.5f);  // Shifts to apply
        float[] v = generateFloatRange(n * (kev + np), 0.5f);  // Arnoldi basis
        float[] h = generateSymmetricTridiagonalFloat(kev + np);  // Hessenberg matrix
        float[] resid = generateFloatRange(n, 0.1f);  // Residual vector
        float[] q = new float[ldq * (kev + np)];  // Work array for rotations
        float[] workd = new float[2 * n];  // Work array

        // Call f2j reference implementation
        float[] expected_v = v.clone();
        float[] expected_h = h.clone();
        float[] expected_resid = resid.clone();
        float[] expected_q = q.clone();

        f2j.ssapps(n, kev, np, shift, expected_v, ldv, expected_h, ldh, expected_resid, expected_q, ldq, workd.clone());

        // Call implementation under test
        float[] actual_v = v.clone();
        float[] actual_h = h.clone();
        float[] actual_resid = resid.clone();
        float[] actual_q = q.clone();

        arpack.ssapps(n, kev, np, shift, actual_v, ldv, actual_h, ldh, actual_resid, actual_q, ldq, workd.clone());

        // Verify results
        assertArrayEquals(expected_v, actual_v, sepsilon);
        assertArrayEquals(expected_h, actual_h, sepsilon);
        assertArrayEquals(expected_resid, actual_resid, sepsilon);
        assertArrayEquals(expected_q, actual_q, sepsilon);
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

        float[] shift = generateFloatRange(np, 2.0f);
        float[] v = generateFloatRange(n * (kev + np), 1.0f);
        float[] h = generateSymmetricTridiagonalFloat(kev + np);
        float[] resid = generateFloatRange(n, 0.2f);
        float[] q = new float[ldq * (kev + np)];
        float[] workd = new float[2 * n];

        // Call f2j reference implementation
        float[] expected_v = v.clone();
        float[] expected_h = h.clone();
        float[] expected_resid = resid.clone();
        float[] expected_q = q.clone();

        f2j.ssapps(n, kev, np, shift, expected_v, ldv, expected_h, ldh, expected_resid, expected_q, ldq, workd.clone());

        // Call implementation under test
        float[] actual_v = v.clone();
        float[] actual_h = h.clone();
        float[] actual_resid = resid.clone();
        float[] actual_q = q.clone();

        arpack.ssapps(n, kev, np, shift, actual_v, ldv, actual_h, ldh, actual_resid, actual_q, ldq, workd.clone());

        // Verify results
        assertArrayEquals(expected_v, actual_v, sepsilon);
        assertArrayEquals(expected_h, actual_h, sepsilon);
        assertArrayEquals(expected_resid, actual_resid, sepsilon);
        assertArrayEquals(expected_q, actual_q, sepsilon);
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

        float[] shift = generateFloatRange(np, 0.5f);
        float[] v = generateFloatRange(n * (kev + np), 0.3f);
        float[] h = generateSymmetricTridiagonalFloat(kev + np);
        float[] resid = generateFloatRange(n, 0.1f);
        float[] q = new float[ldq * (kev + np)];
        float[] workd = new float[2 * n];

        // Call f2j reference implementation
        float[] expected_v = v.clone();
        float[] expected_h = h.clone();
        float[] expected_resid = resid.clone();
        float[] expected_q = q.clone();

        f2j.ssapps(n, kev, np, shift, expected_v, ldv, expected_h, ldh, expected_resid, expected_q, ldq, workd.clone());

        // Call implementation under test
        float[] actual_v = v.clone();
        float[] actual_h = h.clone();
        float[] actual_resid = resid.clone();
        float[] actual_q = q.clone();

        arpack.ssapps(n, kev, np, shift, actual_v, ldv, actual_h, ldh, actual_resid, actual_q, ldq, workd.clone());

        // Verify results
        assertArrayEquals(expected_v, actual_v, sepsilon);
        assertArrayEquals(expected_h, actual_h, sepsilon);
        assertArrayEquals(expected_resid, actual_resid, sepsilon);
        assertArrayEquals(expected_q, actual_q, sepsilon);
    }

}
