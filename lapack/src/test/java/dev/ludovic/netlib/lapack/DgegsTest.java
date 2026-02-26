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

package dev.ludovic.netlib.lapack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;
import org.netlib.util.*;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class DgegsTest extends LAPACKTest {

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testSanity(LAPACK lapack) {
        int n = N_SMALL;

        double[] a_expected = generateMatrix(n, n, 1.0);
        double[] b_expected = generatePositiveDefiniteMatrix(n);
        double[] alphar_expected = new double[n];
        double[] alphai_expected = new double[n];
        double[] beta_expected = new double[n];
        double[] vsl_expected = new double[n * n];
        double[] vsr_expected = new double[n * n];
        int lwork = Math.max(1, 8 * n + 16);
        double[] work_expected = new double[lwork];
        intW info_expected = new intW(0);

        f2j.dgegs("V", "V", n, a_expected, 0, n, b_expected, 0, n, alphar_expected, 0, alphai_expected, 0, beta_expected, 0, vsl_expected, 0, n, vsr_expected, 0, n, work_expected, 0, lwork, info_expected);
        assertEquals(0, info_expected.val, "Reference dgegs should succeed");

        double[] a_actual = generateMatrix(n, n, 1.0);
        double[] b_actual = generatePositiveDefiniteMatrix(n);
        double[] alphar_actual = new double[n];
        double[] alphai_actual = new double[n];
        double[] beta_actual = new double[n];
        double[] vsl_actual = new double[n * n];
        double[] vsr_actual = new double[n * n];
        double[] work_actual = new double[lwork];
        intW info_actual = new intW(0);

        lapack.dgegs("V", "V", n, a_actual, 0, n, b_actual, 0, n, alphar_actual, 0, alphai_actual, 0, beta_actual, 0, vsl_actual, 0, n, vsr_actual, 0, n, work_actual, 0, lwork, info_actual);
        assertEquals(0, info_actual.val, "dgegs should succeed");

        // Compare computed eigenvalues (alphar/beta, alphai/beta) with special handling for near-zero beta
        double[] eigenvalues_expected = new double[n];
        double[] eigenvalues_actual = new double[n];
        for (int i = 0; i < n; i++) {
            if (Math.abs(beta_expected[i]) > depsilon) {
                eigenvalues_expected[i] = alphar_expected[i] / beta_expected[i];
            } else {
                eigenvalues_expected[i] = Double.POSITIVE_INFINITY; // Infinite eigenvalue
            }
            if (Math.abs(beta_actual[i]) > depsilon) {
                eigenvalues_actual[i] = alphar_actual[i] / beta_actual[i];
            } else {
                eigenvalues_actual[i] = Double.POSITIVE_INFINITY; // Infinite eigenvalue
            }
        }
        java.util.Arrays.sort(eigenvalues_expected);
        java.util.Arrays.sort(eigenvalues_actual);

        // Compare sorted eigenvalues, filtering out infinities
        for (int i = 0; i < n; i++) {
            if (Double.isInfinite(eigenvalues_expected[i]) && Double.isInfinite(eigenvalues_actual[i])) {
                continue; // Both infinite, OK
            } else if (Double.isInfinite(eigenvalues_expected[i]) || Double.isInfinite(eigenvalues_actual[i])) {
                fail("Eigenvalue infinity mismatch at index " + i);
            } else {
                assertRelEquals(eigenvalues_expected[i], eigenvalues_actual[i], depsilon * 10);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("LAPACKImplementations")
    void testNoVectors(LAPACK lapack) {
        int n = N_SMALL;

        double[] a_expected = generateMatrix(n, n, 1.0);
        double[] b_expected = generatePositiveDefiniteMatrix(n);
        double[] alphar_expected = new double[n];
        double[] alphai_expected = new double[n];
        double[] beta_expected = new double[n];
        double[] vsl_expected = new double[1];
        double[] vsr_expected = new double[1];
        int lwork = Math.max(1, 8 * n + 16);
        double[] work_expected = new double[lwork];
        intW info_expected = new intW(0);

        f2j.dgegs("N", "N", n, a_expected, 0, n, b_expected, 0, n, alphar_expected, 0, alphai_expected, 0, beta_expected, 0, vsl_expected, 0, 1, vsr_expected, 0, 1, work_expected, 0, lwork, info_expected);
        assertEquals(0, info_expected.val);

        double[] a_actual = generateMatrix(n, n, 1.0);
        double[] b_actual = generatePositiveDefiniteMatrix(n);
        double[] alphar_actual = new double[n];
        double[] alphai_actual = new double[n];
        double[] beta_actual = new double[n];
        double[] vsl_actual = new double[1];
        double[] vsr_actual = new double[1];
        double[] work_actual = new double[lwork];
        intW info_actual = new intW(0);

        lapack.dgegs("N", "N", n, a_actual, 0, n, b_actual, 0, n, alphar_actual, 0, alphai_actual, 0, beta_actual, 0, vsl_actual, 0, 1, vsr_actual, 0, 1, work_actual, 0, lwork, info_actual);
        assertEquals(0, info_actual.val);

        // Compare computed eigenvalues (alphar/beta, alphai/beta) with special handling for near-zero beta
        double[] eigenvalues_expected = new double[n];
        double[] eigenvalues_actual = new double[n];
        for (int i = 0; i < n; i++) {
            if (Math.abs(beta_expected[i]) > depsilon) {
                eigenvalues_expected[i] = alphar_expected[i] / beta_expected[i];
            } else {
                eigenvalues_expected[i] = Double.POSITIVE_INFINITY; // Infinite eigenvalue
            }
            if (Math.abs(beta_actual[i]) > depsilon) {
                eigenvalues_actual[i] = alphar_actual[i] / beta_actual[i];
            } else {
                eigenvalues_actual[i] = Double.POSITIVE_INFINITY; // Infinite eigenvalue
            }
        }
        java.util.Arrays.sort(eigenvalues_expected);
        java.util.Arrays.sort(eigenvalues_actual);

        // Compare sorted eigenvalues, filtering out infinities
        for (int i = 0; i < n; i++) {
            if (Double.isInfinite(eigenvalues_expected[i]) && Double.isInfinite(eigenvalues_actual[i])) {
                continue; // Both infinite, OK
            } else if (Double.isInfinite(eigenvalues_expected[i]) || Double.isInfinite(eigenvalues_actual[i])) {
                fail("Eigenvalue infinity mismatch at index " + i);
            } else {
                assertRelEquals(eigenvalues_expected[i], eigenvalues_actual[i], depsilon * 10);
            }
        }
    }
}
