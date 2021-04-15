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

package dev.ludovic.netlib.benchmarks.blas.l1;

import dev.ludovic.netlib.benchmarks.blas.BLASBenchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import org.netlib.util.floatW;

@State(Scope.Thread)
public class SrotgBenchmark extends L1Benchmark {

    public floatW sa, saclone;
    public floatW sb, sbclone;
    public floatW c, cclone;
    public floatW s, sclone;

    @Setup(Level.Trial)
    public void setup() {
        sa = new floatW(randomFloat());
        sb = new floatW(randomFloat());
        c = new floatW(randomFloat());
        s = new floatW(randomFloat());
    }

    @Setup(Level.Invocation)
    public void setupIteration() {
        saclone = new floatW(sa.val);
        sbclone = new floatW(sb.val);
        cclone = new floatW(c.val);
        sclone = new floatW(s.val);
    }

    @Benchmark
    public void blas(Blackhole bh) {
        blas.srotg(saclone, sbclone, cclone, sclone);
        bh.consume(saclone);
        bh.consume(sbclone);
        bh.consume(cclone);
        bh.consume(sclone);
    }
}
