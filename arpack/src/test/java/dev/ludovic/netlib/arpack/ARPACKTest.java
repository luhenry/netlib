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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.provider.Arguments;

import dev.ludovic.netlib.arpack.ARPACK;

public class ARPACKTest {

  final static double depsilon = 1e-15d;
  final static float sepsilon = 1e-6f;

  final static ARPACK f2j = F2jARPACK.getInstance();

  private static Stream<Arguments> ARPACKImplementations() {
    Stream instances = Stream.of(
      Arguments.of(Named.of("ARPACK", ARPACK.getInstance())),
      Arguments.of(Named.of("NativeARPACK", NativeARPACK.getInstance())),
      Arguments.of(Named.of("JavaARPACK", JavaARPACK.getInstance())),
      Arguments.of(Named.of("F2jARPACK", F2jARPACK.getInstance())),
      Arguments.of(Named.of("JNIARPACK", JNIARPACK.getInstance()))
    );

    return instances;
  }

  // Test data dimensions
  protected final int N = 50;
  protected final int NCV = 10;
  protected final int NEV = 5;
  protected final int LDV = N;
  protected final int LDH = NCV;
  protected final int LDQ = NCV;
  protected final int LWORKL = 3 * NCV * NCV + 6 * NCV;

  // Integer arrays for testing
  protected final int[] intArray1 = generateIntArray(N, 1);
  protected final int[] intArray2 = generateIntArray(N, 2);
  protected final int[] intArray3 = generateIntArray(N, 0);

  // Double arrays for testing
  protected final double[] dArray1 = generateDoubleArray(N, 1.0);
  protected final double[] dArray2 = generateDoubleArray(N, 2.0);
  protected final double[] dArrayZero = new double[N];

  // Float arrays for testing
  protected final float[] sArray1 = generateFloatArray(N, 1.0f);
  protected final float[] sArray2 = generateFloatArray(N, 2.0f);
  protected final float[] sArrayZero = new float[N];

  // Matrices for eigenvalue problems
  protected final double[] dMatrix = generateSymmetricMatrix(N);
  protected final float[] sMatrix = convertToFloat(dMatrix);

  // Helper methods
  protected static int[] generateIntArray(int n, int start) {
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = start + i;
    }
    return arr;
  }

  protected static double[] generateDoubleArray(int n, double start) {
    double[] arr = new double[n];
    for (int i = 0; i < n; i++) {
      arr[i] = start + i * 0.1;
    }
    return arr;
  }

  protected static float[] generateFloatArray(int n, float start) {
    float[] arr = new float[n];
    for (int i = 0; i < n; i++) {
      arr[i] = start + i * 0.1f;
    }
    return arr;
  }

  protected static double[] generateSymmetricMatrix(int n) {
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

  protected static float[] convertToFloat(double[] arr) {
    float[] result = new float[arr.length];
    for (int i = 0; i < arr.length; i++) {
      result[i] = (float) arr[i];
    }
    return result;
  }

  protected static void assertArrayEquals(int[] expected, int[] actual) {
    org.junit.jupiter.api.Assertions.assertEquals(expected.length, actual.length);
    for (int i = 0; i < expected.length; i++) {
      org.junit.jupiter.api.Assertions.assertEquals(expected[i], actual[i], "Mismatch at index " + i);
    }
  }

  protected static void assertArrayEquals(double[] expected, double[] actual, double epsilon) {
    org.junit.jupiter.api.Assertions.assertEquals(expected.length, actual.length);
    for (int i = 0; i < expected.length; i++) {
      org.junit.jupiter.api.Assertions.assertEquals(expected[i], actual[i], epsilon, "Mismatch at index " + i);
    }
  }

  protected static void assertArrayEquals(float[] expected, float[] actual, float epsilon) {
    org.junit.jupiter.api.Assertions.assertEquals(expected.length, actual.length);
    for (int i = 0; i < expected.length; i++) {
      org.junit.jupiter.api.Assertions.assertEquals(expected[i], actual[i], epsilon, "Mismatch at index " + i);
    }
  }
}
