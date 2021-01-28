/*
 * Copyright (c) Ludovic Henry. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation. Ludovic Henry designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Ludovic Henry in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact hi@ludovic.dev or visit ludovic.dev if you need additional
 * information or have any questions.
 */

package dev.ludovic.netlib.blas;

import dev.ludovic.netlib.BLAS;

public final class NetlibF2jBLAS extends NetlibWrapper {

  private static final NetlibF2jBLAS instance;

  static {
    instance = new NetlibF2jBLAS(new com.github.fommil.netlib.F2jBLAS());
  }

  protected NetlibF2jBLAS(com.github.fommil.netlib.BLAS _blas) {
    super(_blas);
  }

  public static BLAS getInstance() {
    return instance;
  }

  protected int idamaxK(int n, double[] x, int offsetx, int incx) {
    // F2j returns 1-based index because that's how Fortran works
    return super.idamaxK(n, x, offsetx, incx) - 1;
  }
  protected int isamaxK(int n, float[] x, int offsetx, int incx) {
    // F2j returns 1-based index because that's how Fortran works
    return super.isamaxK(n, x, offsetx, incx) - 1;
  }
}
