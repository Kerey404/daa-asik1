package com.bakytzhan.algorithms.util;

public class Metrics {
    private long comparisons = 0;
    private int maxDepth = 0;
    private long allocations = 0;


    public void incrementComparisons() {
        comparisons++;
    }


    public int compare(int a, int b) {
        comparisons++;
        return Integer.compare(a, b);
    }

    public void recordDepth(int depth) {
        if (depth > maxDepth) {
            maxDepth = depth;
        }
    }

    public void updateDepth(int depth) {
        recordDepth(depth);
    }

    public void trackAllocation() {
        allocations++;
    }


    public long getComparisons() {
        return comparisons;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public long getAllocations() {
        return allocations;
    }

    public void reset() {
        comparisons = 0;
        maxDepth = 0;
        allocations = 0;
    }
}
