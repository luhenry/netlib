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

package dev.ludovic.netlib.benchmarks.blas.l3;

import dev.ludovic.netlib.benchmarks.blas.BLASBenchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
public class SgemmBenchmark extends L3Benchmark {

    @Param({"N", "T"})
    public String transa;
    @Param({"N", "T"})
    public String transb;

    @Param({"10", "1000"})
    public int m;
    @Param({"10", "1000"})
    public int n;
    @Param({"10", "1000"})
    public int k;

    public float alpha;
    public float[] a;
    public int lda;
    public float[] b;
    public int ldb;
    public float beta;
    public float[] c, cclone;
    public int ldc;

    @Setup(Level.Trial)
    public void setup() {
        alpha = randomFloat();
        a = randomFloatArray(k * m);
        b = randomFloatArray(k * n);
        beta = randomFloat();
        c = randomFloatArray(m * n);
    }

    @Benchmark
    public void blas(Blackhole bh) {
        blas.sgemm(transa, transb, m, n, k, alpha, a, transa.equals("N") ? m : k, b, transb.equals("N") ? k : n, beta, cclone = c.clone(), m);
        bh.consume(cclone);
    }
}
