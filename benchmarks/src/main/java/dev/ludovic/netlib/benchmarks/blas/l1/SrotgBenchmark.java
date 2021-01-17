/*
 * Copyright 2020, Ludovic Henry
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
 */

package dev.ludovic.netlib.benchmarks.blas.l1;

import dev.ludovic.netlib.benchmarks.blas.BLASBenchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import org.netlib.util.floatW;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
@Fork(value = 1, jvmArgsPrepend = {"--add-modules=jdk.incubator.vector"})
public class SrotgBenchmark extends BLASBenchmark {

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
