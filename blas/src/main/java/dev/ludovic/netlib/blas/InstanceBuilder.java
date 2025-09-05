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

import java.util.logging.Logger;

final class InstanceBuilder {

  private static final Logger log = Logger.getLogger(InstanceBuilder.class.getName());

  private static final BLAS blas;
  private static final NativeBLAS nativeBlas;
  private static final JavaBLAS javaBlas;

  static {
    nativeBlas = initializeNative();
    javaBlas = initializeJava();

    if (nativeBlas == null) {
      if (javaBlas instanceof VectorBLAS) {
        log.info("Using JavaBLAS, with features requiring at least Java 16 and the incubating Vector API");
      } else if (javaBlas instanceof Java11BLAS) {
        log.info("Using JavaBLAS, with features requiring at least Java 11");
      } else if (javaBlas instanceof Java8BLAS) {
        log.info("Using JavaBLAS, with features requiring at least Java 8");
      } else {
        log.info("Using JavaBLAS");
      }
      blas = javaBlas;
    } else {
      log.info("Using native BLAS");
      blas = nativeBlas;
    }
  }

  public static BLAS blas() {
    return blas;
  }

  private static NativeBLAS initializeNative() {
    try {
      return JNIBLAS.getInstance();
    } catch (Throwable t) {
      log.fine("Failed to load implementation from:" + JNIBLAS.class.getName());
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
    String[] fullVersion = System.getProperty("java.version").split("[+.\\-]+", 2);
    int major = Integer.parseInt(fullVersion[0]);
    if (major >= 16) {
      try {
        log.finest("trying to return java 16 instance");
        return VectorBLAS.getInstance();
      } catch (Throwable t) {
        log.fine("Failed to load implementation from:" + VectorBLAS.class.getName());
      }
    }
    if (major >= 11) {
      log.finest("return java 11 instance");
      return Java11BLAS.getInstance();
    }
    log.finest("return java 8 instance");
    return Java8BLAS.getInstance();
  }

  public static JavaBLAS javaBlas() {
    return javaBlas;
  }
}
