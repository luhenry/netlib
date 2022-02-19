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

import java.util.logging.Logger;

public final class InstanceBuilder {

  public static final class ARPACK {
    private static final dev.ludovic.netlib.ARPACK instance = getInstanceImpl();

    public static dev.ludovic.netlib.ARPACK getInstance() {
      return instance;
    }

    private static dev.ludovic.netlib.ARPACK getInstanceImpl() {
      try {
        return dev.ludovic.netlib.NativeARPACK.getInstance();
      } catch (Throwable t) {
        Logger.getLogger(ARPACK.class.getName()).warning("Failed to load implementation from:" + dev.ludovic.netlib.NativeARPACK.class.getName());
      }
      return dev.ludovic.netlib.JavaARPACK.getInstance();
    }
  }

  public static final class NativeARPACK {
    private static final dev.ludovic.netlib.NativeARPACK instance = getInstanceImpl();

    public static dev.ludovic.netlib.NativeARPACK getInstance() {
      return instance;
    }

    private static dev.ludovic.netlib.NativeARPACK getInstanceImpl() {
      try {
        return dev.ludovic.netlib.arpack.JNIARPACK.getInstance();
      } catch (Throwable t) {
        Logger.getLogger(NativeARPACK.class.getName()).warning("Failed to load implementation from:" + dev.ludovic.netlib.arpack.JNIARPACK.class.getName());
      }
      throw new RuntimeException("Unable to load native implementation");
    }
  }

  public static final class JavaARPACK {
    private static final dev.ludovic.netlib.JavaARPACK instance = getInstanceImpl();

    public static dev.ludovic.netlib.JavaARPACK getInstance() {
      return instance;
    }

    private static dev.ludovic.netlib.JavaARPACK getInstanceImpl() {
      return dev.ludovic.netlib.arpack.F2jARPACK.getInstance();
    }
  }
}
