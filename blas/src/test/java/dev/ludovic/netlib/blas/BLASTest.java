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

package dev.ludovic.netlib.blas;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.provider.Arguments;

public class BLASTest {

  final static double depsilon = 1e-11d;
  final static double dsolveEpsilon = 1e-10d;
  final static float sepsilon = 1e-3f;
  final static float ssolveEpsilon = 1e-1f;

  final static BLAS f2j = F2jBLAS.getInstance();

  private static Stream<Arguments> BLASImplementations() throws Throwable {
    Stream instances = Stream.of(
      Arguments.of(Named.of("BLAS", BLAS.getInstance())),
      Arguments.of(Named.of("NativeBLAS", NativeBLAS.getInstance())),
      Arguments.of(Named.of("JavaBLAS", JavaBLAS.getInstance())),
      Arguments.of(Named.of("F2jBLAS", F2jBLAS.getInstance())),
      Arguments.of(Named.of("JNIBLAS", JNIBLAS.getInstance()))
    );

    String[] fullVersion = System.getProperty("java.version").split("[+.\\-]+", 3);
    int major = Integer.parseInt(fullVersion[0]) > 1 ? Integer.parseInt(fullVersion[0]) : Integer.parseInt(fullVersion[1]);
    if (major >= 8) {
      instances = Stream.concat(instances, Stream.of(
        Arguments.of(Named.of("Java8BLAS", Java8BLAS.getInstance()))
      ));
    }
    if (major >= 11) {
      instances = Stream.concat(instances, Stream.of(
        Arguments.of(Named.of("Java11BLAS", Java11BLAS.getInstance()))
      ));
    }
    if (major >= 16) {
      instances = Stream.concat(instances, Stream.of(
        Arguments.of(Named.of("VectorBLAS", VectorBLAS.getInstance()))
      ));
    }

    return instances;
  }

  protected static final double[] readArray(String name) {
    InputStream is = BLASTest.class.getResourceAsStream(name);
    assert is != null;
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
      String[] values = reader.readLine().split(",");
      double[] result = new double[values.length];
      for (int i = 0; i < values.length; i += 1) {
        result[i] = Double.parseDouble(values[i]);
      }
      return result;
    } catch (Throwable t) {
      t.printStackTrace();
      throw new UnsupportedOperationException(t);
    }
  }

  protected static final double[] transpose(String trans, double[] arr, int m, int n) {
    assert arr.length == m * n;
    double[] result = new double[n * m];
    if (trans.equals("N")) {
      for (int col = 0; col < n; col += 1) {
        for (int row = 0; row < m; row += 1) {
          result[col + row * n] = arr[row + col * m];
        }
      }
    } else {
      for (int row = 0; row < m; row += 1) {
        for (int col = 0; col < n; col += 1) {
          result[row + col * m] = arr[col + row * n];
        }
      }
    }
    return result;
  }

  protected static final double[] extractUPLO(String uplo, double[] arr, int n, int ldarr) {
    assert n <= ldarr;
    double[] result = new double[n * (n + 1) / 2];
    int i = 0;
    if (uplo.equals("U")) {
      for (int col = 0; col < n; col += 1) {
        for (int row = 0; row < col + 1; row += 1) {
          result[i++] = arr[row + col * ldarr];
        }
      }
    } else {
      for (int col = 0; col < n; col += 1) {
        for (int row = col; row < n; row += 1) {
          result[i++] = arr[row + col * ldarr];
        }
      }
    }
    assert i == n * (n + 1) / 2;
    return result;
  }

  protected static final double[] extractSymmetric(double[] arr, int n, int ldarr) {
    assert n <= ldarr;
    double[] result = new double[n * n];
    for (int col = 0; col < n; col += 1) {
      for (int row = 0; row < col + 1; row += 1) {
        result[row + col * n] = result[col + row * n] = arr[row + col * ldarr];
      }
    }
    return result;
  }

  protected static final float[] convertToFloat(double[] src) {
    float[] result = new float[src.length];
    for (int i = 0; i < src.length; i += 1) {
      result[i] = (float)src[i];
    }
    return result;
  }

  protected static final double[] extractBand(double[] ge, int m, int n, int kl, int ku, int ldge) {
    int ldab = kl + ku + 1;
    double[] ab = new double[ldab * n];
    for (int j = 0; j < n; j += 1) {
      for (int i = Math.max(0, j - ku); i <= Math.min(m - 1, j + kl); i += 1) {
        ab[(ku + i - j) + j * ldab] = ge[i + j * ldge];
      }
    }
    return ab;
  }

  protected static final double[] extractSymBand(String uplo, double[] sy, int n, int k, int ldsy) {
    int ldab = k + 1;
    double[] ab = new double[ldab * n];
    if (uplo.equals("U")) {
      for (int j = 0; j < n; j += 1) {
        for (int i = Math.max(0, j - k); i <= j; i += 1) {
          ab[(k + i - j) + j * ldab] = sy[i + j * ldsy];
        }
      }
    } else {
      for (int j = 0; j < n; j += 1) {
        for (int i = j; i <= Math.min(n - 1, j + k); i += 1) {
          ab[(i - j) + j * ldab] = sy[i + j * ldsy];
        }
      }
    }
    return ab;
  }

  protected static void assertRelArrayEquals(double[] expected, double[] actual, double relEpsilon) {
    org.junit.jupiter.api.Assertions.assertEquals(expected.length, actual.length);
    for (int i = 0; i < expected.length; i++) {
      org.junit.jupiter.api.Assertions.assertEquals(expected[i], actual[i], Math.max(Math.abs(expected[i]) * relEpsilon, depsilon));
    }
  }

  protected static void assertRelArrayEquals(float[] expected, float[] actual, float relEpsilon) {
    org.junit.jupiter.api.Assertions.assertEquals(expected.length, actual.length);
    for (int i = 0; i < expected.length; i++) {
      org.junit.jupiter.api.Assertions.assertEquals(expected[i], actual[i], Math.max(Math.abs(expected[i]) * relEpsilon, sepsilon));
    }
  }

  protected final int M = 103;
  protected final int N = 103;
  protected final int K = 103;

  // double[m, k]
  protected final double[] dgeA = readArray("/geA.mat");
  // double[k, n]
  protected final double[] dgeB = readArray("/geB.mat");
  // double[m, n]
  protected final double[] dgeC = readArray("/geC.mat");

  // double[m, m]
  protected final double[] dsyA = extractSymmetric(dgeA, M, M);

  // double[m]
  protected final double[] dX = readArray("/X.vec");
  // double[m]
  protected final double[] dY = readArray("/Y.vec");

  // double[k, m]
  protected final double[] dgeAT = transpose("N", dgeA, M, K);
  // double[n, k]
  protected final double[] dgeBT = transpose("N", dgeB, K, N);

  // double[m, m][U]
  protected final double[] dgeAU = extractUPLO("U", dgeA, M, M);
  // double[m, m][L]
  protected final double[] dgeAL = extractUPLO("L", dgeA, M, M);

  protected final int KL = 2;
  protected final int KU = 2;

  // double[(kl+ku+1), n] - general band storage of dgeA
  protected final double[] dgbA = extractBand(dgeA, M, N, KL, KU, M);
  // double[(ku+1), m] - symmetric band storage (upper) of dsyA
  protected final double[] dsbAU = extractSymBand("U", dsyA, M, KU, M);
  // double[(ku+1), m] - symmetric band storage (lower) of dsyA
  protected final double[] dsbAL = extractSymBand("L", dsyA, M, KU, M);
  // double[(ku+1), m] - triangular band storage (upper) of dgeA
  protected final double[] dtbAU = extractSymBand("U", dgeA, M, KU, M);
  // double[(ku+1), m] - triangular band storage (lower) of dgeA
  protected final double[] dtbAL = extractSymBand("L", dgeA, M, KU, M);

  // float[m, k]
  protected final float[] sgeA = convertToFloat(dgeA);
  // float[k, n]
  protected final float[] sgeB = convertToFloat(dgeB);
  // float[m, n]
  protected final float[] sgeC = convertToFloat(dgeC);

  // float[m, m]
  protected final float[] ssyA = convertToFloat(dsyA);

  // double[m]
  protected final float[] sX = convertToFloat(dX);
  // double[m]
  protected final float[] sY = convertToFloat(dY);

  // float[k, m]
  protected final float[] sgeAT = convertToFloat(dgeAT);
  // float[n, k]
  protected final float[] sgeBT = convertToFloat(dgeBT);

  // float[m, k][U]
  protected final float[] sgeAU = convertToFloat(dgeAU);
  // float[m, k][L]
  protected final float[] sgeAL = convertToFloat(dgeAL);

  // float[(kl+ku+1), n] - general band storage
  protected final float[] sgbA = convertToFloat(dgbA);
  // float[(ku+1), m] - symmetric band storage (upper)
  protected final float[] ssbAU = convertToFloat(dsbAU);
  // float[(ku+1), m] - symmetric band storage (lower)
  protected final float[] ssbAL = convertToFloat(dsbAL);
  // float[(ku+1), m] - triangular band storage (upper)
  protected final float[] stbAU = convertToFloat(dtbAU);
  // float[(ku+1), m] - triangular band storage (lower)
  protected final float[] stbAL = convertToFloat(dtbAL);
}
