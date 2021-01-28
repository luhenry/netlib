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

package dev.ludovic.netlib.benchmarks.arpack;

import dev.ludovic.netlib.ARPACK;

import org.openjdk.jmh.annotations.*;

import java.util.Random;

@State(Scope.Thread)
public abstract class ARPACKBenchmark {

    public ARPACK arpack;

    @Param({"f2j", "native", "java"})
    public String implementation;

    @Setup
    public void setupImplementation() {
        switch (implementation) {
        case "f2j":
            arpack = dev.ludovic.netlib.arpack.NetlibWrapper.wrap(new com.github.fommil.netlib.F2jARPACK());
            break;
        case "native":
            arpack = dev.ludovic.netlib.arpack.NetlibWrapper.wrap(com.github.fommil.netlib.ARPACK.getInstance());
            break;
        case "java":
            arpack = dev.ludovic.netlib.arpack.JavaARPACK.getInstance();
            break;
        default: throw new IllegalArgumentException("Unknown implementation = " + implementation);
        }
    }

    private final Random rand = new Random(0);

    protected double randomDouble() {
        return rand.nextDouble();
    }

    protected double[] randomDoubleArray(int n) {
        double[] res = new double[n];
        for (int i = 0; i < n; i++) {
            res[i] = rand.nextDouble();
        }
        return res;
    }

    protected float randomFloat() {
        return rand.nextFloat();
    }

    protected float[] randomFloatArray(int n) {
        float[] res = new float[n];
        for (int i = 0; i < n; i++) {
            res[i] = rand.nextFloat();
        }
        return res;
    }
}
