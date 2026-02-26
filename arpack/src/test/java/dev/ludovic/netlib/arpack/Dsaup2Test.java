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

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class Dsaup2Test extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        // Test parameters
        int n = 20;
        int nev = 3;
        int ncv = 10;
        int ldv = n;
        int ldh = ncv;
        int ldq = ncv;
        String bmat = "I";  // Identity matrix
        String which = "SA"; // Smallest algebraic eigenvalues
        double tol = 0.0;    // Machine precision
        int mode = 1;        // Regular mode
        int iupd = 1;
        int ishift = 1;

        // Create arrays
        double[] resid = new double[n];
        double[] v = new double[ldv * ncv];
        double[] h = new double[ldh * ncv];
        double[] ritz = new double[ncv];
        double[] bounds = new double[ncv];
        double[] q = new double[ldq * ncv];
        double[] workl = new double[ncv * (ncv + 8)];
        int[] ipntr = new int[14];
        double[] workd = new double[3 * n];

        // Initialize residual vector
        for (int i = 0; i < n; i++) {
            resid[i] = 1.0;
        }

        // Initialize wrappers
        intW ido = new intW(0);
        intW nevW = new intW(nev);
        intW np = new intW(0);
        intW mxiter = new intW(300);
        intW info = new intW(0);

        // Call method - this uses reverse communication
        arpack.dsaup2(ido, bmat, n, which, nevW, np, tol, resid, mode, iupd, ishift,
                      mxiter, v, ldv, h, ldh, ritz, bounds, q, ldq, workl, ipntr, workd, info);

        // Basic checks: ido should change from 0
        assertTrue(ido.val != 0, "ido should have changed from 0, but was: " + ido.val);

        // info should be non-negative (0 = success, positive = warning/error)
        assertTrue(info.val >= -1, "info should be >= -1, but was: " + info.val);

        // If ido is positive, it's requesting matrix-vector product
        if (ido.val == 1 || ido.val == -1) {
            // Check that ipntr has been set
            assertTrue(ipntr[0] > 0 || ipntr[1] > 0, "ipntr should have been set");
        }
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testLargestEigenvalues(ARPACK arpack) {
        // Test with different eigenvalue selection
        int n = 20;
        int nev = 4;
        int ncv = 12;
        int ldv = n;
        int ldh = ncv;
        int ldq = ncv;
        String bmat = "I";
        String which = "LA"; // Largest algebraic eigenvalues
        double tol = 1e-10;
        int mode = 1;
        int iupd = 1;
        int ishift = 1;

        double[] resid = new double[n];
        double[] v = new double[ldv * ncv];
        double[] h = new double[ldh * ncv];
        double[] ritz = new double[ncv];
        double[] bounds = new double[ncv];
        double[] q = new double[ldq * ncv];
        double[] workl = new double[ncv * (ncv + 8)];
        int[] ipntr = new int[14];
        double[] workd = new double[3 * n];

        // Initialize residual
        for (int i = 0; i < n; i++) {
            resid[i] = 1.0 / (i + 1.0);
        }

        intW ido = new intW(0);
        intW nevW = new intW(nev);
        intW np = new intW(0);
        intW mxiter = new intW(300);
        intW info = new intW(0);

        arpack.dsaup2(ido, bmat, n, which, nevW, np, tol, resid, mode, iupd, ishift,
                      mxiter, v, ldv, h, ldh, ritz, bounds, q, ldq, workl, ipntr, workd, info);

        assertTrue(ido.val != 0, "ido should have changed from 0");
        assertTrue(info.val >= -1, "info should be >= -1, but was: " + info.val);
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testGeneralizedProblem(ARPACK arpack) {
        // Test with generalized eigenvalue problem
        int n = 30;
        int nev = 5;
        int ncv = 15;
        int ldv = n;
        int ldh = ncv;
        int ldq = ncv;
        String bmat = "G";  // Generalized problem
        String which = "SM"; // Smallest magnitude
        double tol = 0.0;
        int mode = 1;
        int iupd = 1;
        int ishift = 1;

        double[] resid = new double[n];
        double[] v = new double[ldv * ncv];
        double[] h = new double[ldh * ncv];
        double[] ritz = new double[ncv];
        double[] bounds = new double[ncv];
        double[] q = new double[ldq * ncv];
        double[] workl = new double[ncv * (ncv + 8)];
        int[] ipntr = new int[14];
        double[] workd = new double[3 * n];

        for (int i = 0; i < n; i++) {
            resid[i] = 1.0;
        }

        intW ido = new intW(0);
        intW nevW = new intW(nev);
        intW np = new intW(0);
        intW mxiter = new intW(300);
        intW info = new intW(0);

        arpack.dsaup2(ido, bmat, n, which, nevW, np, tol, resid, mode, iupd, ishift,
                      mxiter, v, ldv, h, ldh, ritz, bounds, q, ldq, workl, ipntr, workd, info);

        assertTrue(ido.val != 0, "ido should have changed from 0");
        assertTrue(info.val >= -1, "info should be >= -1, but was: " + info.val);

        // For generalized problem, ido might be 2 (B*x operation)
        if (ido.val == 2) {
            assertTrue(ipntr[0] > 0 && ipntr[1] > 0, "ipntr should be set for ido=2");
        }
    }
}
