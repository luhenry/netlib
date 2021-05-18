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

package dev.ludovic.netlib.benchmarks.blas.l1;

import dev.ludovic.netlib.benchmarks.blas.BLASBenchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import org.netlib.util.doubleW;

@State(Scope.Thread)
@Warmup(iterations = 3)
@Measurement(iterations = 3)
public class DrotgBenchmark extends L1Benchmark {

    public doubleW sa, saclone;
    public doubleW sb, sbclone;
    public doubleW c, cclone;
    public doubleW s, sclone;

    @Setup(Level.Trial)
    public void setup() {
        sa = new doubleW(randomDouble());
        sb = new doubleW(randomDouble());
        c = new doubleW(randomDouble());
        s = new doubleW(randomDouble());
    }

    @Benchmark
    public void run(Blackhole bh) {
        blas.drotg(saclone = new doubleW(sa.val), sbclone = new doubleW(sb.val), cclone = new doubleW(c.val), sclone = new doubleW(s.val));
        bh.consume(saclone);
        bh.consume(sbclone);
        bh.consume(cclone);
        bh.consume(sclone);
    }
}
