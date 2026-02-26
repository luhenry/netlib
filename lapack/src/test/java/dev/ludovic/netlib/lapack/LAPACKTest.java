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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.api.Assertions.*;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class LAPACKTest {

  final static double depsilon = 1e-14d;
  final static float sepsilon = 1e-5f;

  final static LAPACK f2j = F2jLAPACK.getInstance();

  protected final int N = 50;
  protected final int N_SMALL = 10;
  protected final int M = 40;

  protected final int[] intArray1 = generateIntArray(N, 1);
  protected final int[] intArray2 = generateIntArray(N, 2);
  protected final int[] intArray3 = new int[N];

  protected final double[] dArray1 = generateDoubleArray(N, 1.0);
  protected final double[] dArray2 = generateDoubleArray(N, 2.0);
  protected final double[] dArray3 = new double[N];

  protected final float[] sArray1 = generateFloatArray(N, 1.0f);
  protected final float[] sArray2 = generateFloatArray(N, 2.0f);
  protected final float[] sArray3 = new float[N];

  protected final double[] dMatrix = generateMatrix(N, N, 1.0);
  protected final double[] dSymmetricMatrix = generateSymmetricMatrix(N);
  protected final double[] dPositiveDefiniteMatrix = generatePositiveDefiniteMatrix(N);

  protected final float[] sMatrix = generateMatrixFloat(N, N, 1.0f);
  protected final float[] sSymmetricMatrix = generateSymmetricMatrixFloat(N);
  protected final float[] sPositiveDefiniteMatrix = generatePositiveDefiniteMatrixFloat(N);

  private static Stream<Arguments> LAPACKImplementations() {
    Stream instances = Stream.of(
      Arguments.of(Named.of("LAPACK", LAPACK.getInstance())),
      Arguments.of(Named.of("NativeLAPACK", NativeLAPACK.getInstance())),
      Arguments.of(Named.of("JavaLAPACK", JavaLAPACK.getInstance())),
      Arguments.of(Named.of("F2jLAPACK", F2jLAPACK.getInstance())),
      Arguments.of(Named.of("JNILAPACK", JNILAPACK.getInstance()))
    );

    return instances;
  }

}
