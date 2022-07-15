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
import org.junit.jupiter.params.provider.Arguments;

public class BLASTest {

  final static double depsilon = 1e-11d;
  final static float sepsilon = 1e-3f;

  final static BLAS f2j = F2jBLAS.getInstance();

  private static Stream<Arguments> BLASImplementations() throws Throwable {
    Stream instances = Stream.of(
      Arguments.of(F2jBLAS.getInstance()),
      Arguments.of(JNIBLAS.getInstance())
    );

    String[] fullVersion = System.getProperty("java.version").split("[+.\\-]+", 3);
    int major = Integer.parseInt(fullVersion[0]) > 1 ? Integer.parseInt(fullVersion[0]) : Integer.parseInt(fullVersion[1]);
    if (major >= 8) {
      instances = Stream.concat(instances, Stream.of(
        Arguments.of(Java8BLAS.getInstance())
      ));
    }
    if (major >= 11) {
      instances = Stream.concat(instances, Stream.of(
        Arguments.of(Java11BLAS.getInstance())
      ));
    }
    if (major >= 16) {
      instances = Stream.concat(instances, Stream.of(
        Arguments.of(VectorBLAS.getInstance())
      ));
    }
    if (major >= 17) {
      instances = Stream.concat(instances, Stream.of(
        Arguments.of((NativeBLAS)Class.forName("dev.ludovic.netlib.blas.ForeignLinkerBLAS").getMethod("getInstance").invoke(null))
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

  protected static final void dumpArray(String name, double[] arr) {
    System.out.print(name + ": ");
    for(double e : arr) {
      System.out.print(String.format("%.3f,", e));
    }
    System.out.println();
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
}
