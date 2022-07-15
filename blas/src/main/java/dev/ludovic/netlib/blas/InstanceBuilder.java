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

  private static final BLAS blas = getInstanceImpl();

  public static BLAS getInstance() {
    return blas;
  }

  private static dev.ludovic.netlib.blas.BLAS getInstanceImpl() {
    try {
      return NativeBLAS.getInstance();
    } catch (Throwable t) {
      log.warning("Failed to load implementation from:" + dev.ludovic.netlib.blas.NativeBLAS.class.getName());
    }
    return JavaBLAS.getInstance();
  }

  private static final NativeBLAS nativeBlas = getNativeInstanceImpl();

  public static NativeBLAS getNativeInstance() {
    return nativeBlas;
  }

  private static NativeBLAS getNativeInstanceImpl() {
    try {
      return JNIBLAS.getInstance();
    } catch (Throwable t) {
      log.warning("Failed to load implementation from:" + JNIBLAS.class.getName());
    }
    try {
      return (NativeBLAS)Class.forName("dev.ludovic.netlib.blas.ForeignLinkerBLAS").getMethod("getInstance").invoke(null);
    } catch (Throwable t) {
      log.warning("Failed to load implementation from:" + "dev.ludovic.netlib.blas.ForeignLinkerBLAS");
    }
    throw new RuntimeException("Unable to load native implementation");
  }

  private static final JavaBLAS javaBlas = getJavaInstanceImpl();

  public static JavaBLAS getJavaInstance() {
    return javaBlas;
  }

  private static JavaBLAS getJavaInstanceImpl() {
    String[] fullVersion = System.getProperty("java.version").split("[+.\\-]+", 2);
    int major = Integer.parseInt(fullVersion[0]);
    if (major >= 16) {
      try {
        return VectorBLAS.getInstance();
      } catch (Throwable t) {
        log.warning("Failed to load implementation from:" + VectorBLAS.class.getName());
      }
    }
    if (major >= 11) {
      return Java11BLAS.getInstance();
    } else {
      return Java8BLAS.getInstance();
    }
  }
}