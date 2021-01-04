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

package dev.ludovic.blas.benchmarks.l3;

import dev.ludovic.blas.benchmarks.BLASBenchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
@Fork(value = 1, jvmArgsPrepend = {"--add-modules=jdk.incubator.vector"})
public class DsymmBenchmark extends BLASBenchmark {

    @Param({"L", "R"})
    public String side;
    @Param({"U", "L"})
    public String uplo;

    @Param({"10", "1000"})
    public int m;
    @Param({"10", "1000"})
    public int n;

    public double alpha;
    public double[] a;
    public int lda;
    public double[] b;
    public int ldb;
    public double beta;
    public double[] c, cclone;
    public int ldc;

    @Setup(Level.Trial)
    public void setup() {
        alpha = randomDouble();
        a = randomDoubleArray(side.equals("L") ? m * m : n * n);
        b = randomDoubleArray(m * n);
        beta = randomDouble();
        c = randomDoubleArray(m * n);
    }

    @Setup(Level.Invocation)
    public void setupIteration() {
        cclone = c.clone();
    }

    @Benchmark
    public void blas(Blackhole bh) {
        blas.dsymm(side, uplo, m, n, alpha, a, side.equals("L") ? m : n, b, m, beta, cclone, m);
        bh.consume(cclone);
    }
}
