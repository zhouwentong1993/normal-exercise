package com.wentong.demo;

import org.openjdk.jmh.annotations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
//@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
//@Fork(1)
//@BenchmarkMode(Mode.Throughput)
//@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class HashMapBenchmark {

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(1)
    @Warmup(iterations = 5, time = 10, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 10, time = 10, timeUnit = TimeUnit.SECONDS)
    public void testInitCap() {
        int size = 10;
        int cap = (int) (size / 0.75f + 1);
        Map<Integer, Integer> map = new HashMap<>(cap);
        for (int i = 0; i < size; i++) {
            map.put(i, i);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(1)
    @Warmup(iterations = 5, time = 10, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 10, time = 10, timeUnit = TimeUnit.SECONDS)
    public void testInitCapWithSize() {
        int size = 10;
        Map<Integer, Integer> map = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            map.put(i, i);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(1)
    @Warmup(iterations = 5, time = 10, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 10, time = 10, timeUnit = TimeUnit.SECONDS)
    public void noInitCap() {
        int size = 10;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            map.put(i, i);
        }
    }

}
