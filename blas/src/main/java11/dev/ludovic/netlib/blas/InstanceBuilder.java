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

import java.util.logging.Level;
import java.util.logging.Logger;

final class InstanceBuilder {

  private static final Logger log = Logger.getLogger(InstanceBuilder.class.getName());

  private static final BLAS blas;
  private static final NativeBLAS nativeBlas;
  private static final JavaBLAS javaBlas;

  static {
    String allowNativeBlas = System.getProperty(BLAS.ALLOW_NATIVE_BLAS, "true");
    if (Boolean.parseBoolean(allowNativeBlas)) {
      nativeBlas = initializeNative();
    } else {
      log.info("Skip trying to load native BLAS implementation because system property " +
              BLAS.ALLOW_NATIVE_BLAS + " is " + allowNativeBlas);
      nativeBlas = null;
    }
    javaBlas = initializeJava();
    blas = nativeBlas != null ? nativeBlas : javaBlas;

    log.info("Using " + blas.getClass().getName());
  }

  public static BLAS blas() {
    return blas;
  }

  private static NativeBLAS initializeNative() {
    try {
      return JNIBLAS.getInstance();
    } catch (Throwable t) {
      log.log(Level.FINE, "Failed to load implementation from: " + JNIBLAS.class.getName(), t);
      return null;
    }
  }

  public static NativeBLAS nativeBlas() {
    if (nativeBlas == null) {
      throw new RuntimeException("Unable to load native implementation");
    }
    return nativeBlas;
  }

  private static JavaBLAS initializeJava() {
    log.finest("return java 11 instance");
    return Java11BLAS.getInstance();
  }

  public static JavaBLAS javaBlas() {
    return javaBlas;
  }
}
