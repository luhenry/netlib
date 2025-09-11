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

import java.util.logging.Level;
import java.util.logging.Logger;

final class InstanceBuilder {

  private static final Logger log = Logger.getLogger(InstanceBuilder.class.getName());

  private static final LAPACK lapack;
  private static final NativeLAPACK nativeLapack;
  private static final JavaLAPACK javaLapack;

  static {
    nativeLapack = initializeNative();
    javaLapack = initializeJava();
    lapack = nativeLapack != null ? nativeLapack : javaLapack;

    log.info("Using " + lapack.getClass().getName());
  }

  public static LAPACK lapack() {
    return lapack;
  }

  private static NativeLAPACK initializeNative() {
    try {
      return JNILAPACK.getInstance();
    } catch (Throwable t) {
      log.log(Level.FINE, "Failed to load implementation from:" + JNILAPACK.class.getName(), t);
      return null;
    }
  }

  public static NativeLAPACK nativeLapack() {
    if (nativeLapack == null) {
      throw new RuntimeException("Unable to load native LAPACK implementation");
    }
    return nativeLapack;
  }

  private static JavaLAPACK initializeJava() {
    return F2jLAPACK.getInstance();
  }

  public static JavaLAPACK javaLapack() {
    return javaLapack;
  }
}
