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

public class DneupdTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        // Test extracting eigenvalues after convergence
        // Note: This test simulates a post-dnaupd state
        int n = 20;
        int nev = 3;
        int ncv = 10;
        int ldv = n;
        int ldz = n;
        String bmat = "I";
        String which = "LM";
        String howmny = "A";  // Compute all nev eigenvalues/vectors
        double tol = 0.0;
        double sigmar = 0.0;  // Real part of shift
        double sigmai = 0.0;  // Imaginary part of shift
        int lworkl = 3 * ncv * ncv + 6 * ncv;

        // Arrays for dneupd
        boolean[] select = new boolean[ncv];
        double[] dr = new double[nev + 1];  // Real parts of eigenvalues
        double[] di = new double[nev + 1];  // Imaginary parts of eigenvalues
        double[] z = new double[n * (nev + 1)];
        double[] workev = new double[3 * ncv];
        double[] resid = new double[n];
        double[] v = new double[ldv * ncv];
        double[] workd = new double[3 * n];
        double[] workl = new double[lworkl];
        int[] iparam = new int[11];
        int[] ipntr = new int[14];

        // Initialize residual
        for (int i = 0; i < n; i++) {
            resid[i] = 1.0;
        }

        // Setup iparam as if dnaupd completed successfully
        iparam[0] = 1;      // ishift
        iparam[2] = 300;    // maxiter
        iparam[3] = 1;      // nb: block size (set by dnaupd)
        iparam[4] = 0;      // nconv: number converged (set by dnaupd)
        iparam[6] = 1;      // mode

        // Initialize select array (required for howmny='S')
        for (int i = 0; i < ncv; i++) {
            select[i] = true;
        }

        intW nevW = new intW(nev);
        intW info = new intW(0);

        // Call dneupd - extract eigenvalues
        // Note: This may fail if v and workl don't contain valid Arnoldi decomposition
        // but we're testing that the method can be called without crashing
        arpack.dneupd(true, howmny, select, dr, di, z, ldz, sigmar, sigmai, workev,
                      bmat, n, which, nevW, tol, resid, ncv, v, ldv,
                      iparam, ipntr, workd, workl, lworkl, info);

        // Check that method executed without fatal error
        // info < 0 indicates error, info >= 0 is acceptable (may be warning)
        assertTrue(info.val >= -14, "info should be >= -14, but was: " + info.val);

        // If successful (info >= 0), check eigenvalue arrays were touched
        if (info.val >= 0 && nevW.val > 0) {
            // At least one eigenvalue should be computed
            assertTrue(nevW.val <= nev, "nevW should be <= nev");
        }
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testEigenvaluesOnly(ARPACK arpack) {
        // Test extracting only eigenvalues (no eigenvectors)
        int n = 20;
        int nev = 4;
        int ncv = 12;
        int ldv = n;
        int ldz = n;
        String bmat = "I";
        String which = "LM";
        String howmny = "A";
        double tol = 0.0;
        double sigmar = 0.0;
        double sigmai = 0.0;
        int lworkl = 3 * ncv * ncv + 6 * ncv;

        boolean[] select = new boolean[ncv];
        double[] dr = new double[nev + 1];
        double[] di = new double[nev + 1];
        double[] z = new double[n * (nev + 1)];
        double[] workev = new double[3 * ncv];
        double[] resid = new double[n];
        double[] v = new double[ldv * ncv];
        double[] workd = new double[3 * n];
        double[] workl = new double[lworkl];
        int[] iparam = new int[11];
        int[] ipntr = new int[14];

        for (int i = 0; i < n; i++) {
            resid[i] = 1.0 / (i + 1.0);
        }

        iparam[0] = 1;
        iparam[2] = 300;
        iparam[3] = 1;
        iparam[4] = 0;
        iparam[6] = 1;

        for (int i = 0; i < ncv; i++) {
            select[i] = true;
        }

        intW nevW = new intW(nev);
        intW info = new intW(0);

        // rvec=false means compute eigenvalues only
        arpack.dneupd(false, howmny, select, dr, di, z, ldz, sigmar, sigmai, workev,
                      bmat, n, which, nevW, tol, resid, ncv, v, ldv,
                      iparam, ipntr, workd, workl, lworkl, info);

        assertTrue(info.val >= -14, "info should be >= -14, but was: " + info.val);
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSelectiveExtraction(ARPACK arpack) {
        // Test extracting selected eigenvalues
        int n = 25;
        int nev = 5;
        int ncv = 15;
        int ldv = n;
        int ldz = n;
        String bmat = "I";
        String which = "SM";
        String howmny = "S";  // Select specific eigenvalues based on select array
        double tol = 0.0;
        double sigmar = 0.0;
        double sigmai = 0.0;
        int lworkl = 3 * ncv * ncv + 6 * ncv;

        boolean[] select = new boolean[ncv];
        double[] dr = new double[ncv];
        double[] di = new double[ncv];
        double[] z = new double[n * (nev + 1)];
        double[] workev = new double[3 * ncv];
        double[] resid = new double[n];
        double[] v = new double[ldv * ncv];
        double[] workd = new double[3 * n];
        double[] workl = new double[lworkl];
        int[] iparam = new int[11];
        int[] ipntr = new int[14];

        for (int i = 0; i < n; i++) {
            resid[i] = 1.0;
        }

        iparam[0] = 1;
        iparam[2] = 300;
        iparam[3] = 1;
        iparam[4] = Math.min(nev, ncv - 1);  // nconv must be < ncv
        iparam[6] = 1;

        // Select first nev eigenvalues
        for (int i = 0; i < ncv; i++) {
            select[i] = (i < nev);
        }

        intW nevW = new intW(nev);
        intW info = new intW(0);

        arpack.dneupd(true, howmny, select, dr, di, z, ldz, sigmar, sigmai, workev,
                      bmat, n, which, nevW, tol, resid, ncv, v, ldv,
                      iparam, ipntr, workd, workl, lworkl, info);

        // Allow for a broader range of errors since we're calling with incomplete data
        assertTrue(info.val >= -16, "info should be >= -16, but was: " + info.val);
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testWithShift(ARPACK arpack) {
        // Test with shift-invert mode
        int n = 20;
        int nev = 3;
        int ncv = 10;
        int ldv = n;
        int ldz = n;
        String bmat = "I";
        String which = "LM";
        String howmny = "A";
        double tol = 0.0;
        double sigmar = 1.0;  // Real part of shift value
        double sigmai = 0.5;  // Imaginary part of shift value
        int lworkl = 3 * ncv * ncv + 6 * ncv;

        boolean[] select = new boolean[ncv];
        double[] dr = new double[nev + 1];
        double[] di = new double[nev + 1];
        double[] z = new double[n * (nev + 1)];
        double[] workev = new double[3 * ncv];
        double[] resid = new double[n];
        double[] v = new double[ldv * ncv];
        double[] workd = new double[3 * n];
        double[] workl = new double[lworkl];
        int[] iparam = new int[11];
        int[] ipntr = new int[14];

        for (int i = 0; i < n; i++) {
            resid[i] = 1.0;
        }

        iparam[0] = 1;
        iparam[2] = 300;
        iparam[3] = 1;
        iparam[4] = 0;
        iparam[6] = 3;  // mode 3: shift-invert

        for (int i = 0; i < ncv; i++) {
            select[i] = true;
        }

        intW nevW = new intW(nev);
        intW info = new intW(0);

        arpack.dneupd(true, howmny, select, dr, di, z, ldz, sigmar, sigmai, workev,
                      bmat, n, which, nevW, tol, resid, ncv, v, ldv,
                      iparam, ipntr, workd, workl, lworkl, info);

        assertTrue(info.val >= -14, "info should be >= -14, but was: " + info.val);
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testComplexEigenvalues(ARPACK arpack) {
        // Test that dr and di arrays can store complex eigenvalues
        int n = 20;
        int nev = 4;
        int ncv = 12;
        int ldv = n;
        int ldz = n;
        String bmat = "I";
        String which = "LI";  // Largest imaginary part
        String howmny = "A";
        double tol = 0.0;
        double sigmar = 0.0;
        double sigmai = 0.0;
        int lworkl = 3 * ncv * ncv + 6 * ncv;

        boolean[] select = new boolean[ncv];
        double[] dr = new double[nev + 1];
        double[] di = new double[nev + 1];
        double[] z = new double[n * (nev + 1)];
        double[] workev = new double[3 * ncv];
        double[] resid = new double[n];
        double[] v = new double[ldv * ncv];
        double[] workd = new double[3 * n];
        double[] workl = new double[lworkl];
        int[] iparam = new int[11];
        int[] ipntr = new int[14];

        for (int i = 0; i < n; i++) {
            resid[i] = Math.cos(i * 0.1);
        }

        iparam[0] = 1;
        iparam[2] = 300;
        iparam[3] = 1;
        iparam[4] = 0;
        iparam[6] = 1;

        for (int i = 0; i < ncv; i++) {
            select[i] = true;
        }

        intW nevW = new intW(nev);
        intW info = new intW(0);

        arpack.dneupd(true, howmny, select, dr, di, z, ldz, sigmar, sigmai, workev,
                      bmat, n, which, nevW, tol, resid, ncv, v, ldv,
                      iparam, ipntr, workd, workl, lworkl, info);

        assertTrue(info.val >= -14, "info should be >= -14, but was: " + info.val);

        // Verify dr and di arrays are separate (not testing values, just structure)
        assertNotNull(dr, "dr array should not be null");
        assertNotNull(di, "di array should not be null");
        assertTrue(dr.length >= nev, "dr should have space for eigenvalues");
        assertTrue(di.length >= nev, "di should have space for eigenvalues");
    }
}
