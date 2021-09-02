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

package dev.ludovic.netlib;

import java.util.logging.Logger;

final class InstanceBuilder {

  public static final class BLAS {
    private static final dev.ludovic.netlib.BLAS instance = getInstanceImpl();

    public static dev.ludovic.netlib.BLAS getInstance() {
      return instance;
    }

    private static dev.ludovic.netlib.BLAS getInstanceImpl() {
      try {
        return dev.ludovic.netlib.NativeBLAS.getInstance();
      } catch (Throwable t) {
        Logger.getLogger(BLAS.class.getName()).warning("Failed to load implementation from:" + dev.ludovic.netlib.NativeBLAS.class.getName());
      }
      return dev.ludovic.netlib.JavaBLAS.getInstance();
    }
  }

  public static final class NativeBLAS {
    private static final dev.ludovic.netlib.NativeBLAS instance = getInstanceImpl();

    public static dev.ludovic.netlib.NativeBLAS getInstance() {
      return instance;
    }

    private static dev.ludovic.netlib.NativeBLAS getInstanceImpl() {
      try {
        return dev.ludovic.netlib.blas.JNIBLAS.getInstance();
      } catch (Throwable t) {
        Logger.getLogger(NativeBLAS.class.getName()).warning("Failed to load implementation from:" + dev.ludovic.netlib.blas.JNIBLAS.class.getName());
      }
      try {
        return (dev.ludovic.netlib.NativeBLAS)Class.forName("dev.ludovic.netlib.blas.ForeignLinkerBLAS").getMethod("getInstance").invoke(null);
      } catch (Throwable t) {
        Logger.getLogger(NativeBLAS.class.getName()).warning("Failed to load implementation from:" + "dev.ludovic.netlib.blas.ForeignLinkerBLAS");
      }
      throw new RuntimeException("Unable to load native implementation");
    }
  }

  public static final class JavaBLAS {
    private static final dev.ludovic.netlib.JavaBLAS instance = getInstanceImpl();

    public static dev.ludovic.netlib.JavaBLAS getInstance() {
      return instance;
    }

    private static dev.ludovic.netlib.JavaBLAS getInstanceImpl() {
      String[] fullVersion = System.getProperty("java.version").split("[+.\\-]+", 2);
      int major = Integer.parseInt(fullVersion[0]);
      if (major >= 16) {
        try {
          return dev.ludovic.netlib.blas.VectorBLAS.getInstance();
        } catch (Throwable t) {
          Logger.getLogger(JavaBLAS.class.getName()).warning("Failed to load implementation from:" + dev.ludovic.netlib.blas.VectorBLAS.class.getName());
        }
      }
      if (major >= 11) {
        return dev.ludovic.netlib.blas.Java11BLAS.getInstance();
      } else {
        return dev.ludovic.netlib.blas.Java8BLAS.getInstance();
      }
    }
  }
}
