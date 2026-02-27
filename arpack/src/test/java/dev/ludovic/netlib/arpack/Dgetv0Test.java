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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class Dgetv0Test extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        // Test parameters
        int n = 20;
        int j = 1;
        int ldv = 20;
        String bmat = "I";  // Identity matrix
        int itry = 1;
        boolean initv = false;

        // Create arrays
        double[] v = new double[ldv * j];
        double[] resid = new double[n];
        int[] ipntr = new int[3];
        double[] workd = new double[3 * n];

        // Initialize wrappers
        intW ido = new intW(0);
        doubleW rnorm = new doubleW(0.0);
        intW ierr = new intW(0);

        // Call method - this uses reverse communication, may need multiple calls
        arpack.dgetv0(ido, bmat, itry, initv, n, j, v, ldv,
                      resid, rnorm, ipntr, workd, ierr);

        // Basic checks: method should either complete (ido=99) or request continuation (ido=-1 or ido=1)
        assertTrue(ido.val == 99 || ido.val == -1 || ido.val == 1,
                   "ido should be 99, -1, or 1, but was: " + ido.val);

        // ierr should be 0 (success) or positive (warning/error)
        assertTrue(ierr.val >= 0, "ierr should be non-negative, but was: " + ierr.val);

        // If completed successfully, rnorm should be positive
        if (ido.val == 99 && ierr.val == 0) {
            assertTrue(rnorm.val > 0, "rnorm should be positive when successful");
        }
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testWithInitialVector(ARPACK arpack) {
        // Test with initv=true and a pre-initialized vector
        int n = 20;
        int j = 1;
        int ldv = 20;
        String bmat = "I";
        int itry = 1;
        boolean initv = true;

        // Initialize resid with non-zero values
        double[] resid = generateDoubleRange(n, 1.0);
        double[] v = new double[ldv * j];
        int[] ipntr = new int[3];
        double[] workd = new double[3 * n];

        intW ido = new intW(0);
        doubleW rnorm = new doubleW(0.0);
        intW ierr = new intW(0);

        // Call method
        arpack.dgetv0(ido, bmat, itry, initv, n, j, v, ldv,
                      resid, rnorm, ipntr, workd, ierr);

        // Basic checks
        assertTrue(ido.val == 99 || ido.val == -1 || ido.val == 1,
                   "ido should be 99, -1, or 1, but was: " + ido.val);
        assertTrue(ierr.val >= 0, "ierr should be non-negative, but was: " + ierr.val);

        // With initv=true, the routine should use the provided resid vector
        // Check that resid was modified or rnorm computed
        if (ido.val == 99 && ierr.val == 0) {
            assertTrue(rnorm.val > 0, "rnorm should be positive when successful");
        }
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testDifferentSizes(ARPACK arpack) {
        // Test with different n value
        int n = 50;
        int j = 1;
        int ldv = 50;
        String bmat = "G";  // Generalized problem
        int itry = 0;
        boolean initv = false;

        double[] v = new double[ldv * j];
        double[] resid = new double[n];
        int[] ipntr = new int[3];
        double[] workd = new double[3 * n];

        intW ido = new intW(0);
        doubleW rnorm = new doubleW(0.0);
        intW ierr = new intW(0);

        // Call method
        arpack.dgetv0(ido, bmat, itry, initv, n, j, v, ldv,
                      resid, rnorm, ipntr, workd, ierr);

        // Basic checks
        assertTrue(ido.val == 99 || ido.val == -1 || ido.val == 1 || ido.val == 2,
                   "ido should be 99, -1, 1, or 2, but was: " + ido.val);
        assertTrue(ierr.val >= 0, "ierr should be non-negative, but was: " + ierr.val);
    }
}
