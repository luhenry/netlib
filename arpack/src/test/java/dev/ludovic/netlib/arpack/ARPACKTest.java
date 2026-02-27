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

import static dev.ludovic.netlib.test.TestHelpers.*;

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
  protected final int[] intArray1 = generateIntRange(N, 1);
  protected final int[] intArray2 = generateIntRange(N, 2);
  protected final int[] intArray3 = generateIntRange(N, 0);

  // Double arrays for testing
  protected final double[] dArray1 = generateDoubleRange(N, 1.0);
  protected final double[] dArray2 = generateDoubleRange(N, 2.0);
  protected final double[] dArrayZero = new double[N];

  // Float arrays for testing
  protected final float[] sArray1 = generateFloatRange(N, 1.0f);
  protected final float[] sArray2 = generateFloatRange(N, 2.0f);
  protected final float[] sArrayZero = new float[N];

  // Matrices for eigenvalue problems
  protected final double[] dMatrix = generateHilbertMatrix(N);
  protected final float[] sMatrix = convertToFloat(dMatrix);
}
