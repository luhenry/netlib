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

package dev.ludovic.netlib.blas.profiler;

import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.profile.ExternalProfiler;
import org.openjdk.jmh.profile.ProfilerException;
import org.openjdk.jmh.results.BenchmarkResult;
import org.openjdk.jmh.results.Result;
import org.openjdk.jmh.results.TextResult;
import org.openjdk.jmh.util.FileUtils;
import org.openjdk.jmh.util.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public final class VTuneProfiler implements ExternalProfiler {

    protected final String collect;
    protected final Path perfBinData;

    public VTuneProfiler() {
        collect = null;
        perfBinData = null;
    }

    public VTuneProfiler(String initParams) throws ProfilerException {
        try {
            collect = initParams.isEmpty() ? "uarch-exploration" : initParams;
            perfBinData = Files.createTempDirectory("jmh-vtune-bin-").toAbsolutePath();
        } catch (IOException e) {
            throw new ProfilerException(e);
        }
    }

    @Override
    public Collection<String> addJVMInvokeOptions(BenchmarkParams params) {
        long delay = TimeUnit.NANOSECONDS.toSeconds(params.getWarmup().getCount() *
                params.getWarmup().getTime().convertTo(TimeUnit.NANOSECONDS))
                + TimeUnit.SECONDS.toSeconds(1); // loosely account for the JVM lag
        return Arrays.asList("vtune", "-collect", collect, "-no-summary", "-loop-mode=loop-and-function", "-inline-mode=on", "-resume-after", String.valueOf(delay), "-result-dir", perfBinData.toString());
    }

    @Override
    public Collection<String> addJVMOptions(BenchmarkParams params) {
        return Collections.emptyList();
    }

    @Override
    public void beforeTrial(BenchmarkParams params) {}

    @Override
    public Collection<? extends Result> afterTrial(BenchmarkResult br, long pid, File stdOut, File stdErr) {
        try {
            Process process = new ProcessBuilder("vtune", "-report", "summary", "-r", perfBinData.toString()).start();

            Collection<String> lines = FileUtils.readAllLines(process.getInputStream());
            String out = Utils.join(lines, System.lineSeparator());
            return Collections.singleton(new TextResult(out, "vtune"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean allowPrintOut() {
        return false;
    }

    @Override
    public boolean allowPrintErr() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Intel VTune profiler";
    }

}

