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

package dev.ludovic.netlib.benchmarks.blas;

import dev.ludovic.netlib.BLAS;

import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
@Fork(value = 1)
@Measurement(iterations = 3)
public abstract class BLASBenchmark {

    public BLAS blas;

    @Param({"f2j", "native", "java", "vector"})
    public String implementation;

    @Setup
    public void setupImplementation() {
        switch (implementation) {
        case "f2j":
            blas = dev.ludovic.netlib.blas.NetlibF2jBLAS.getInstance();
            break;
        case "java":
            blas = dev.ludovic.netlib.blas.JavaBLAS.getInstance();
            break;
        case "vector":
            blas = dev.ludovic.netlib.blas.VectorizedBLAS.getInstance();
            break;
        case "native":
            blas = dev.ludovic.netlib.blas.NativeBLAS.getInstance();
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
