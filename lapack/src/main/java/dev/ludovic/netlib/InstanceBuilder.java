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

  public static final class LAPACK {
    private static final dev.ludovic.netlib.LAPACK instance = getInstanceImpl();

    public static dev.ludovic.netlib.LAPACK getInstance() {
      return instance;
    }

    private static dev.ludovic.netlib.LAPACK getInstanceImpl() {
      try {
        return dev.ludovic.netlib.NativeLAPACK.getInstance();
      } catch (Throwable t) {
        Logger.getLogger(LAPACK.class.getName()).warning("Failed to load implementation from:" + dev.ludovic.netlib.NativeLAPACK.class.getName());
      }
      return dev.ludovic.netlib.JavaLAPACK.getInstance();
    }
  }

  public static final class NativeLAPACK {
    private static final dev.ludovic.netlib.NativeLAPACK instance = getInstanceImpl();

    public static dev.ludovic.netlib.NativeLAPACK getInstance() {
      return instance;
    }

    private static dev.ludovic.netlib.NativeLAPACK getInstanceImpl() {
      try {
        return dev.ludovic.netlib.lapack.JNILAPACK.getInstance();
      } catch (Throwable t) {
        Logger.getLogger(NativeLAPACK.class.getName()).warning("Failed to load implementation from:" + dev.ludovic.netlib.lapack.JNILAPACK.class.getName());
      }
      throw new RuntimeException("Unable to load native implementation");
    }
  }

  public static final class JavaLAPACK {
    private static final dev.ludovic.netlib.JavaLAPACK instance = getInstanceImpl();

    public static dev.ludovic.netlib.JavaLAPACK getInstance() {
      return instance;
    }

    private static dev.ludovic.netlib.JavaLAPACK getInstanceImpl() {
      return dev.ludovic.netlib.lapack.F2jLAPACK.getInstance();
    }
  }
}
