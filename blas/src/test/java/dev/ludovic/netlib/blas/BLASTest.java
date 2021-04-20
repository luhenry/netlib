/*
 * Copyright (c) Ludovic Henry. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation. Ludovic Henry designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Ludovic Henry in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact hi@ludovic.dev or visit ludovic.dev if you need additional
 * information or have any questions.
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;

import dev.ludovic.netlib.BLAS;
import dev.ludovic.netlib.blas.*;

public class BLASTest {

  final static double depsilon = 1e-11d;
  final static float sepsilon = 1e-3f;

  final static BLAS f2j = NetlibF2jBLAS.getInstance();

  private static Stream<Arguments> BLASImplementations() {
    Stream instances = Stream.of(
      Arguments.of(NetlibF2jBLAS.getInstance()),
      Arguments.of(JavaBLAS.getInstance())
    );

    try {
      instances = Stream.concat(instances, Stream.of(VectorizedBLAS.getInstance()));
    } catch (NoClassDefFoundError e) {
    }

    try {
      instances = Stream.concat(instances, Stream.of(NativeBLAS.getInstance()));
    } catch (ExceptionInInitializerError e) {
    } catch (NoClassDefFoundError e) {
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
