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

package dev.ludovic.netlib.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestHelpers {

  // ---- Assertion helpers ----

  public static void assertRelArrayEquals(double[] expected, double[] actual, double epsilon) {
    assertEquals(expected.length, actual.length);
    for (int i = 0; i < expected.length; i++) {
      assertRelEquals(expected[i], actual[i], epsilon, "Mismatch at index " + i);
    }
  }

  public static void assertRelArrayEquals(float[] expected, float[] actual, float epsilon) {
    assertEquals(expected.length, actual.length);
    for (int i = 0; i < expected.length; i++) {
      assertRelEquals(expected[i], actual[i], epsilon, "Mismatch at index " + i);
    }
  }

  public static void assertRelEquals(double expected, double actual, double epsilon) {
    assertRelEquals(expected, actual, epsilon, null);
  }

  public static void assertRelEquals(double expected, double actual, double epsilon, String message) {
    double scaledEpsilon = Math.scalb(epsilon, Math.getExponent(expected));
    assertEquals(expected, actual, Math.max(scaledEpsilon, epsilon), message);
  }

  public static void assertRelEquals(float expected, float actual, float epsilon) {
    assertRelEquals(expected, actual, epsilon, null);
  }

  public static void assertRelEquals(float expected, float actual, float epsilon, String message) {
    float scaledEpsilon = Math.scalb(epsilon, Math.getExponent(expected));
    assertEquals(expected, actual, Math.max(scaledEpsilon, epsilon), message);
  }

  public static void assertAbsArrayEquals(double[] expected, double[] actual, double epsilon) {
    assertEquals(expected.length, actual.length, "Array lengths differ");
    for (int i = 0; i < expected.length; i++) {
      assertEquals(Math.abs(expected[i]), Math.abs(actual[i]), epsilon, "Mismatch at index " + i);
    }
  }

  public static void assertAbsArrayEquals(float[] expected, float[] actual, float epsilon) {
    assertEquals(expected.length, actual.length, "Array lengths differ");
    for (int i = 0; i < expected.length; i++) {
      assertEquals(Math.abs(expected[i]), Math.abs(actual[i]), epsilon, "Mismatch at index " + i);
    }
  }

  public static void assertSortedGeneralizedEigenvalues(double[] alphar_expected, double[] beta_expected, double[] alphar_actual, double[] beta_actual, int n, double epsilon) {
    double[] eig_expected = new double[n];
    double[] eig_actual = new double[n];
    for (int i = 0; i < n; i++) {
      eig_expected[i] = Math.abs(beta_expected[i]) > epsilon ? alphar_expected[i] / beta_expected[i] : Double.POSITIVE_INFINITY;
      eig_actual[i] = Math.abs(beta_actual[i]) > epsilon ? alphar_actual[i] / beta_actual[i] : Double.POSITIVE_INFINITY;
    }
    java.util.Arrays.sort(eig_expected);
    java.util.Arrays.sort(eig_actual);
    for (int i = 0; i < n; i++) {
      if (Double.isInfinite(eig_expected[i]) && Double.isInfinite(eig_actual[i])) {
        continue;
      } else if (Double.isInfinite(eig_expected[i]) || Double.isInfinite(eig_actual[i])) {
        fail("Eigenvalue infinity mismatch at sorted index " + i);
      } else {
        assertRelEquals(eig_expected[i], eig_actual[i], epsilon);
      }
    }
  }

  // ---- Utility methods ----

  public static float[] convertToFloat(double[] src) {
    float[] result = new float[src.length];
    for (int i = 0; i < src.length; i++) {
      result[i] = (float) src[i];
    }
    return result;
  }

  public static double getMaxValue(double[] array) {
    double maxVal = 0.0;
    for (double val : array) {
      maxVal = Math.max(maxVal, Math.abs(val));
    }
    return maxVal;
  }

  public static float getMaxValue(float[] array) {
    float maxVal = 0.0f;
    for (float val : array) {
      maxVal = Math.max(maxVal, Math.abs(val));
    }
    return maxVal;
  }

  public static double[] matrixMultiply(double[] a, double[] b, int m, int n, int p) {
    // Multiply A (m x n) by B (n x p) to get C (m x p)
    // Matrices stored in column-major order
    double[] c = new double[m * p];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < p; j++) {
        double sum = 0.0;
        for (int k = 0; k < n; k++) {
          sum += a[i + k * m] * b[k + j * n];
        }
        c[i + j * m] = sum;
      }
    }
    return c;
  }

  public static double[] matrixTranspose(double[] a, int m, int n) {
    // Transpose A (m x n) to get A^T (n x m)
    // Input/output matrices stored in column-major order
    double[] at = new double[m * n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        at[j + i * n] = a[i + j * m];
      }
    }
    return at;
  }

  // ---- LAPACK-style generators ----

  public static int[] generateIntArray(int n, int seed) {
    int[] array = new int[n];
    for (int i = 0; i < n; i++) {
      array[i] = seed * (i + 1);
    }
    return array;
  }

  public static double[] generateDoubleArray(int n, double scale) {
    double[] array = new double[n];
    for (int i = 0; i < n; i++) {
      array[i] = scale * (i + 1);
    }
    return array;
  }

  public static float[] generateFloatArray(int n, float scale) {
    float[] array = new float[n];
    for (int i = 0; i < n; i++) {
      array[i] = scale * (i + 1);
    }
    return array;
  }

  public static double[] generateMatrix(int m, int n, double scale) {
    double[] matrix = new double[m * n];
    for (int j = 0; j < n; j++) {
      for (int i = 0; i < m; i++) {
        matrix[i + j * m] = scale * (i + 1) * (j + 1);
      }
    }
    return matrix;
  }

  public static float[] generateMatrixFloat(int m, int n, float scale) {
    float[] matrix = new float[m * n];
    for (int j = 0; j < n; j++) {
      for (int i = 0; i < m; i++) {
        matrix[i + j * m] = scale * (i + 1) * (j + 1);
      }
    }
    return matrix;
  }

  public static double[] generateSymmetricMatrix(int n) {
    double[] matrix = new double[n * n];
    for (int j = 0; j < n; j++) {
      for (int i = 0; i < n; i++) {
        double value = (i <= j) ? (i + 1) * (j + 1) : (j + 1) * (i + 1);
        matrix[i + j * n] = value;
      }
    }
    return matrix;
  }

  public static float[] generateSymmetricMatrixFloat(int n) {
    float[] matrix = new float[n * n];
    for (int j = 0; j < n; j++) {
      for (int i = 0; i < n; i++) {
        float value = (i <= j) ? (i + 1) * (j + 1) : (j + 1) * (i + 1);
        matrix[i + j * n] = value;
      }
    }
    return matrix;
  }

  public static double[] generatePositiveDefiniteMatrix(int n) {
    double[] matrix = new double[n * n];
    for (int i = 0; i < n; i++) {
      matrix[i + i * n] = n + 1.0;
      for (int j = i + 1; j < n; j++) {
        double value = 1.0 / (i + j + 2.0);
        matrix[i + j * n] = value;
        matrix[j + i * n] = value;
      }
    }
    return matrix;
  }

  public static float[] generatePositiveDefiniteMatrixFloat(int n) {
    float[] matrix = new float[n * n];
    for (int i = 0; i < n; i++) {
      matrix[i + i * n] = n + 1.0f;
      for (int j = i + 1; j < n; j++) {
        float value = 1.0f / (i + j + 2.0f);
        matrix[i + j * n] = value;
        matrix[j + i * n] = value;
      }
    }
    return matrix;
  }

  public static double[] generateIdentityMatrix(int n) {
    double[] matrix = new double[n * n];
    for (int i = 0; i < n; i++) {
      matrix[i + i * n] = 1.0;
    }
    return matrix;
  }

  public static float[] generateIdentityMatrixFloat(int n) {
    float[] matrix = new float[n * n];
    for (int i = 0; i < n; i++) {
      matrix[i + i * n] = 1.0f;
    }
    return matrix;
  }

  public static double[] generatePackedSymmetricMatrix(int n, double diag) {
    int len = n * (n + 1) / 2;
    double[] ap = new double[len];
    int k = 0;
    for (int j = 0; j < n; j++) {
      for (int i = 0; i <= j; i++) {
        ap[k++] = (i == j) ? diag : 1.0 / (i + j + 1.0);
      }
    }
    return ap;
  }

  public static float[] generatePackedSymmetricMatrixFloat(int n, float diag) {
    int len = n * (n + 1) / 2;
    float[] ap = new float[len];
    int k = 0;
    for (int j = 0; j < n; j++) {
      for (int i = 0; i <= j; i++) {
        ap[k++] = (i == j) ? diag : 1.0f / (i + j + 1.0f);
      }
    }
    return ap;
  }

  public static double[] generateBandedSymmetricMatrix(int n, int kd, double diag, double offdiagScale) {
    int ldab = kd + 1;
    double[] ab = new double[ldab * n];
    for (int j = 0; j < n; j++) {
      for (int i = Math.max(0, j - kd); i <= j; i++) {
        int k = kd + i - j;
        ab[k + j * ldab] = (i == j) ? diag : offdiagScale / (Math.abs(i - j) + 1.0);
      }
    }
    return ab;
  }

  public static float[] generateBandedSymmetricMatrixFloat(int n, int kd, float diag, float offdiagScale) {
    int ldab = kd + 1;
    float[] ab = new float[ldab * n];
    for (int j = 0; j < n; j++) {
      for (int i = Math.max(0, j - kd); i <= j; i++) {
        int k = kd + i - j;
        ab[k + j * ldab] = (i == j) ? diag : offdiagScale / (Math.abs(i - j) + 1.0f);
      }
    }
    return ab;
  }

  public static double[] generateUpperTriangularMatrix(int n, double diagBase, double diagSlope, double offdiagScale) {
    double[] matrix = new double[n * n];
    for (int j = 0; j < n; j++) {
      for (int i = 0; i <= j; i++) {
        matrix[i + j * n] = (i == j) ? (diagBase + diagSlope * i) : offdiagScale / (j - i + 1.0);
      }
    }
    return matrix;
  }

  public static float[] generateUpperTriangularMatrixFloat(int n, float diagBase, float diagSlope, float offdiagScale) {
    float[] matrix = new float[n * n];
    for (int j = 0; j < n; j++) {
      for (int i = 0; i <= j; i++) {
        matrix[i + j * n] = (i == j) ? (diagBase + diagSlope * i) : offdiagScale / (j - i + 1.0f);
      }
    }
    return matrix;
  }

  // ---- ARPACK-style generators (renamed to avoid conflicts) ----

  public static int[] generateIntRange(int n, int start) {
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = start + i;
    }
    return arr;
  }

  public static double[] generateDoubleRange(int n, double start) {
    double[] arr = new double[n];
    for (int i = 0; i < n; i++) {
      arr[i] = start + i * 0.1;
    }
    return arr;
  }

  public static float[] generateFloatRange(int n, float start) {
    float[] arr = new float[n];
    for (int i = 0; i < n; i++) {
      arr[i] = start + i * 0.1f;
    }
    return arr;
  }

  public static double[] generateHilbertMatrix(int n) {
    double[] mat = new double[n * n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j <= i; j++) {
        double val = 1.0 / (i + j + 1.0);
        mat[i * n + j] = val;
        mat[j * n + i] = val;
      }
    }
    return mat;
  }

  public static double[] generateUpperHessenberg(int n) {
    double[] h = new double[n * n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (j >= i - 1) {
          h[i * n + j] = 1.0 + i * 0.1 + j * 0.05;
        }
      }
    }
    return h;
  }

  public static float[] generateUpperHessenbergFloat(int n) {
    float[] h = new float[n * n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (j >= i - 1) {
          h[i * n + j] = 1.0f + i * 0.1f + j * 0.05f;
        }
      }
    }
    return h;
  }

  public static double[] generateSymmetricTridiagonal(int n) {
    double[] h = new double[n * n];
    for (int i = 0; i < n; i++) {
      h[i * n + i] = 2.0 + i * 0.1;
      if (i > 0) {
        h[i * n + (i - 1)] = 1.0 / (i + 1);
        h[(i - 1) * n + i] = 1.0 / (i + 1);
      }
    }
    return h;
  }

  public static float[] generateSymmetricTridiagonalFloat(int n) {
    float[] h = new float[n * n];
    for (int i = 0; i < n; i++) {
      h[i * n + i] = 2.0f + i * 0.1f;
      if (i > 0) {
        h[i * n + (i - 1)] = 1.0f / (i + 1);
        h[(i - 1) * n + i] = 1.0f / (i + 1);
      }
    }
    return h;
  }

  public static double[] generateNonsymmetricHessenberg(int n) {
    double[] h = new double[n * n];
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        h[i * n + j] = 1.0 / (i + j + 1.0);
      }
      if (i < n - 1) {
        h[(i + 1) * n + i] = 0.5 / (i + 1.0);
      }
    }
    return h;
  }

  public static float[] generateNonsymmetricHessenbergFloat(int n) {
    float[] h = new float[n * n];
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        h[i * n + j] = 1.0f / (i + j + 1.0f);
      }
      if (i < n - 1) {
        h[(i + 1) * n + i] = 0.5f / (i + 1.0f);
      }
    }
    return h;
  }
}
