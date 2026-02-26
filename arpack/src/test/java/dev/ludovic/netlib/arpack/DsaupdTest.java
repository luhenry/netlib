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

import org.netlib.util.intW;
import org.netlib.util.doubleW;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class DsaupdTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        // Test parameters - simple symmetric eigenvalue problem
        int n = 20;
        int nev = 3;      // Number of eigenvalues to compute
        int ncv = 10;     // Number of Arnoldi vectors
        int ldv = n;
        String bmat = "I";  // Standard eigenvalue problem (I*x = lambda*A*x)
        String which = "SA"; // Smallest algebraic eigenvalues
        int lworkl = ncv * (ncv + 8);

        // Create arrays
        double[] resid = new double[n];
        double[] v = new double[ldv * ncv];
        double[] workd = new double[3 * n];
        double[] workl = new double[lworkl];
        int[] iparam = new int[11];
        int[] ipntr = new int[11];

        // Initialize residual vector
        for (int i = 0; i < n; i++) {
            resid[i] = 1.0;
        }

        // Set iparam for reverse communication
        iparam[0] = 1;      // ishift: exact shifts
        iparam[2] = 300;    // max iterations
        iparam[6] = 1;      // mode: A*x = lambda*x

        // Initialize wrappers
        intW ido = new intW(0);
        doubleW tol = new doubleW(0.0); // Use machine precision
        intW info = new intW(0);

        // Call method - first iteration (ido=0 on input)
        arpack.dsaupd(ido, bmat, n, which, nev, tol, resid, ncv, v, ldv,
                      iparam, ipntr, workd, workl, lworkl, info);

        // Basic checks: ido should change to request matrix-vector operation
        assertTrue(ido.val == 1 || ido.val == -1 || ido.val == 99,
                   "ido should be 1, -1, or 99, but was: " + ido.val);

        // info should be 0 (normal) or 1 (max iterations reached) initially
        assertTrue(info.val >= 0, "info should be non-negative, but was: " + info.val);

        // If ido requests operation, ipntr should be valid
        if (ido.val == 1 || ido.val == -1) {
            assertTrue(ipntr[0] > 0 && ipntr[1] > 0,
                       "ipntr[0] and ipntr[1] should be positive for ido=" + ido.val);
        }
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testIterativeCall(ARPACK arpack) {
        // Test that we can perform multiple iterations
        int n = 20;
        int nev = 3;
        int ncv = 10;
        int ldv = n;
        String bmat = "I";
        String which = "LA"; // Largest algebraic
        int lworkl = ncv * (ncv + 8);

        double[] resid = new double[n];
        double[] v = new double[ldv * ncv];
        double[] workd = new double[3 * n];
        double[] workl = new double[lworkl];
        int[] iparam = new int[11];
        int[] ipntr = new int[11];

        // Initialize with random-like values
        for (int i = 0; i < n; i++) {
            resid[i] = Math.sin(i + 1.0);
        }

        iparam[0] = 1;
        iparam[2] = 300;
        iparam[6] = 1;

        intW ido = new intW(0);
        doubleW tol = new doubleW(1e-10);
        intW info = new intW(0);

        // First call
        arpack.dsaupd(ido, bmat, n, which, nev, tol, resid, ncv, v, ldv,
                      iparam, ipntr, workd, workl, lworkl, info);

        // Perform a few iterations with simple matrix-vector product (identity matrix)
        int maxIter = 5;
        for (int iter = 0; iter < maxIter && (ido.val == 1 || ido.val == -1); iter++) {
            // Simple A*x = x (identity matrix)
            int inPtr = ipntr[0] - 1;  // Convert to 0-based
            int outPtr = ipntr[1] - 1;
            for (int i = 0; i < n; i++) {
                workd[outPtr + i] = workd[inPtr + i];
            }

            // Continue iteration
            arpack.dsaupd(ido, bmat, n, which, nev, tol, resid, ncv, v, ldv,
                          iparam, ipntr, workd, workl, lworkl, info);
        }

        // After iterations, should still have valid state
        assertTrue(info.val >= 0, "info should be non-negative after iterations");
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testGeneralizedProblem(ARPACK arpack) {
        // Test generalized eigenvalue problem
        int n = 25;
        int nev = 4;
        int ncv = 12;
        int ldv = n;
        String bmat = "G";  // Generalized problem A*x = lambda*B*x
        String which = "SM"; // Smallest magnitude
        int lworkl = ncv * (ncv + 8);

        double[] resid = new double[n];
        double[] v = new double[ldv * ncv];
        double[] workd = new double[3 * n];
        double[] workl = new double[lworkl];
        int[] iparam = new int[11];
        int[] ipntr = new int[11];

        for (int i = 0; i < n; i++) {
            resid[i] = 1.0;
        }

        iparam[0] = 1;
        iparam[2] = 300;
        iparam[6] = 2;  // mode 2: A*x = lambda*M*x, M symmetric positive semi-definite

        intW ido = new intW(0);
        doubleW tol = new doubleW(0.0);
        intW info = new intW(0);

        arpack.dsaupd(ido, bmat, n, which, nev, tol, resid, ncv, v, ldv,
                      iparam, ipntr, workd, workl, lworkl, info);

        // For generalized problems, ido can also be 2 (B*x operation)
        assertTrue(ido.val == 1 || ido.val == -1 || ido.val == 2 || ido.val == 99,
                   "ido should be 1, -1, 2, or 99 for generalized problem, but was: " + ido.val);

        assertTrue(info.val >= 0, "info should be non-negative");

        if (ido.val == 2) {
            assertTrue(ipntr[0] > 0 && ipntr[1] > 0, "ipntr should be valid for ido=2");
        }
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testWithInitialResidual(ARPACK arpack) {
        // Test with user-provided initial residual vector
        int n = 20;
        int nev = 3;
        int ncv = 10;
        int ldv = n;
        String bmat = "I";
        String which = "SA";
        int lworkl = ncv * (ncv + 8);

        double[] resid = new double[n];
        double[] v = new double[ldv * ncv];
        double[] workd = new double[3 * n];
        double[] workl = new double[lworkl];
        int[] iparam = new int[11];
        int[] ipntr = new int[11];

        // Provide initial guess
        for (int i = 0; i < n; i++) {
            resid[i] = 1.0 / (i + 1.0);
        }

        iparam[0] = 1;
        iparam[2] = 300;
        iparam[6] = 1;

        intW ido = new intW(0);
        doubleW tol = new doubleW(0.0);
        intW info = new intW(1); // info=1 means use provided residual

        arpack.dsaupd(ido, bmat, n, which, nev, tol, resid, ncv, v, ldv,
                      iparam, ipntr, workd, workl, lworkl, info);

        assertTrue(ido.val == 1 || ido.val == -1 || ido.val == 99,
                   "ido should be valid");
        assertTrue(info.val >= 0, "info should be non-negative");
    }
}
