package com.bakytzhan.algorithms.bench;

import com.bakytzhan.algorithms.sorts.MergeSort;
import com.bakytzhan.algorithms.sorts.QuickSort;
import com.bakytzhan.algorithms.select.MedianOfMediansSelect;
import com.bakytzhan.algorithms.util.Metrics;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SelectBenchmark {

    @Param({"1000", "10000", "100000"})
    private int size;

    private int[] data;
    private Random random;

    @Setup(Level.Iteration)
    public void setUp() {
        random = new Random();
        data = random.ints(size, 0, size * 10).toArray();
    }

    @Benchmark
    public int quickSortSelect() {
        int[] copy = Arrays.copyOf(data, data.length);
        Metrics m = new Metrics();
        QuickSort.sort(copy, m);
        return copy[size / 2]; // медиана
    }

    @Benchmark
    public int mergeSortSelect() {
        int[] copy = Arrays.copyOf(data, data.length);
        Metrics m = new Metrics();
        MergeSort.sort(copy, m);
        return copy[size / 2];
    }

    @Benchmark
    public int medianOfMediansSelect() {
        int[] copy = Arrays.copyOf(data, data.length);
        Metrics m = new Metrics();
        return MedianOfMediansSelect.select(copy, size / 2, m);
    }
}
