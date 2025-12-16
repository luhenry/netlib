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

import java.util.logging.Level;
import java.util.logging.Logger;

final class InstanceBuilder {

  public static final String ALLOW_NATIVE_ARPACK = "dev.ludovic.netlib.arpack.allowNative";

  private static final Logger log = Logger.getLogger(InstanceBuilder.class.getName());

  private static final ARPACK arpack;
  private static final NativeARPACK nativeArpack;
  private static final JavaARPACK javaArpack;

  static {
    String allowNativeArpack = System.getProperty(ALLOW_NATIVE_ARPACK, "true");
    if (Boolean.parseBoolean(allowNativeArpack)) {
      nativeArpack = initializeNative();
    } else {
      log.info("Skip trying to load native BLAS implementation because system property " +
              ALLOW_NATIVE_ARPACK + " is " + allowNativeArpack);
      nativeArpack = null;
    }
    javaArpack = initializeJava();
    arpack = nativeArpack != null ? nativeArpack : javaArpack;

    log.info("Using " + arpack.getClass().getName());
  }

  public static ARPACK arpack() {
    return arpack;
  }

  private static NativeARPACK initializeNative() {
    try {
      return JNIARPACK.getInstance();
    } catch (Throwable t) {
      log.log(Level.FINE, "Failed to load implementation from: " + JNIARPACK.class.getName(), t);
      return null;
    }
  }

  public static NativeARPACK nativeArpack() {
    if (nativeArpack == null) {
      throw new RuntimeException("Unable to load native implementation");
    }
    return nativeArpack;
  }

  private static JavaARPACK initializeJava() {
    return F2jARPACK.getInstance();
  }

  public static JavaARPACK javaArpack() {
    return javaArpack;
  }
}
