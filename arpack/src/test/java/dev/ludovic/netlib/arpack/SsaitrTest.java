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
import org.netlib.util.floatW;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class SsaitrTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        // Test parameters for Arnoldi iteration (reverse communication)
        int n = 20;       // Problem size
        int k = 5;        // Current Arnoldi factorization size
        int np = 3;       // Number of additional steps
        int mode = 1;     // Standard eigenvalue problem
        int ldv = 20;     // Leading dimension of v
        int ldh = 8;      // Leading dimension of h (k + np)
        String bmat = "I";  // Identity matrix (standard problem)

        intW ido = new intW(0);  // Reverse communication flag
        intW info = new intW(0);  // Error flag
        floatW rnorm = new floatW(0.0f);  // Residual norm

        float[] resid = generateFloatRange(n, 1.0f);  // Residual/starting vector
        float[] v = new float[ldv * (k + np + 1)];  // Arnoldi basis vectors
        float[] h = new float[ldh * (k + np)];  // Hessenberg matrix
        int[] ipntr = new int[3];  // Pointer to work arrays
        float[] workd = new float[3 * n];  // Work array

        // Call the method once to initialize
        arpack.ssaitr(ido, bmat, n, k, np, mode, resid, rnorm, v, ldv, h, ldh, ipntr, workd, info);

        // Basic checks: ido should indicate continuation or completion
        assertTrue(ido.val == 99 || ido.val == -1 || ido.val == 1,
                   "ido should be 99, -1, or 1, but was: " + ido.val);

        // info should be 0 (success) or positive (warning/error)
        assertTrue(info.val >= 0, "info should be non-negative, but was: " + info.val);

        // If completed successfully, verify outputs
        if (ido.val == 99 && info.val == 0) {
            // rnorm should be positive
            assertTrue(rnorm.val >= 0, "rnorm should be non-negative");
            // h should contain the Hessenberg matrix
            // v should contain orthonormal basis vectors
        }
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testGeneralizedProblem(ARPACK arpack) {
        // Test with generalized eigenvalue problem
        int n = 20;
        int k = 5;
        int np = 3;
        int mode = 2;  // Generalized eigenvalue problem
        int ldv = 20;
        int ldh = 8;
        String bmat = "G";  // Generalized problem

        intW ido = new intW(0);
        intW info = new intW(0);
        floatW rnorm = new floatW(0.0f);

        float[] resid = generateFloatRange(n, 1.0f);
        float[] v = new float[ldv * (k + np + 1)];
        float[] h = new float[ldh * (k + np)];
        int[] ipntr = new int[3];
        float[] workd = new float[3 * n];

        // Call the method
        arpack.ssaitr(ido, bmat, n, k, np, mode, resid, rnorm, v, ldv, h, ldh, ipntr, workd, info);

        // Basic checks
        assertTrue(ido.val == 99 || ido.val == -1 || ido.val == 1 || ido.val == 2,
                   "ido should be 99, -1, 1, or 2, but was: " + ido.val);
        assertTrue(info.val >= 0, "info should be non-negative, but was: " + info.val);
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testLargerFactorization(ARPACK arpack) {
        // Test with larger factorization size
        int n = 50;
        int k = 10;
        int np = 5;
        int mode = 1;
        int ldv = 50;
        int ldh = 15;
        String bmat = "I";

        intW ido = new intW(0);
        intW info = new intW(0);
        floatW rnorm = new floatW(0.0f);

        float[] resid = generateFloatRange(n, 0.5f);
        float[] v = new float[ldv * (k + np + 1)];
        float[] h = new float[ldh * (k + np)];
        int[] ipntr = new int[3];
        float[] workd = new float[3 * n];

        // Call the method
        arpack.ssaitr(ido, bmat, n, k, np, mode, resid, rnorm, v, ldv, h, ldh, ipntr, workd, info);

        // Basic checks
        assertTrue(ido.val == 99 || ido.val == -1 || ido.val == 1,
                   "ido should be 99, -1, or 1, but was: " + ido.val);
        assertTrue(info.val >= 0, "info should be non-negative, but was: " + info.val);
    }

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testWithPreInitializedBasis(ARPACK arpack) {
        // Test starting from k > 0 (continuing an existing factorization)
        int n = 20;
        int k = 3;  // Start with some existing basis vectors
        int np = 3;
        int mode = 1;
        int ldv = 20;
        int ldh = 6;
        String bmat = "I";

        intW ido = new intW(0);
        intW info = new intW(0);
        floatW rnorm = new floatW(0.0f);

        float[] resid = generateFloatRange(n, 1.0f);
        float[] v = new float[ldv * (k + np + 1)];
        // Initialize first k basis vectors with orthonormal vectors
        for (int i = 0; i < k; i++) {
            v[i * ldv + i] = 1.0f;  // Simple initialization
        }
        float[] h = new float[ldh * (k + np)];
        int[] ipntr = new int[3];
        float[] workd = new float[3 * n];

        // Call the method
        arpack.ssaitr(ido, bmat, n, k, np, mode, resid, rnorm, v, ldv, h, ldh, ipntr, workd, info);

        // Basic checks
        assertTrue(ido.val == 99 || ido.val == -1 || ido.val == 1,
                   "ido should be 99, -1, or 1, but was: " + ido.val);
        assertTrue(info.val >= 0, "info should be non-negative, but was: " + info.val);
    }
}
