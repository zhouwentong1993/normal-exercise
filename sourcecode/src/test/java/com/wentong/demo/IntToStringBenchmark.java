package com.wentong.demo;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;


/**
 * Benchmark                                Mode  Cnt         Score         Error  Units
 * IntToStringBenchmark.testStringValueOf  thrpt   10  24289192.977 ± 1214276.725  ops/s
 * IntToStringBenchmark.testUseAdd         thrpt   10  35486063.654 ± 1528481.209  ops/s
 */
public class IntToStringBenchmark {

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 5, time = 10, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 10, time = 10, timeUnit = TimeUnit.SECONDS)
    public void testStringValueOf() {
        int i = 123891823;
        String s = String.valueOf(i);
        s.length();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 5, time = 10, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 10, time = 10, timeUnit = TimeUnit.SECONDS)
    public void testUseAdd() {
        int i = 123891823;
        String s = i + "";
        s.length();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(IntToStringBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
