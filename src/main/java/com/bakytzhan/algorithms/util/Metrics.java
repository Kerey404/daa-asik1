package com.bakytzhan.algorithms.util;

public class Metrics {
    private long comparisons = 0;
    private long allocations = 0;
    private int maxDepth = 0;

    public int compare(int a, int b) {
        comparisons++;
        return Integer.compare(a, b);
    }

    public void trackAllocation() {
        allocations++;
    }

    public void updateDepth(int depth) {
        if (depth > maxDepth) {
            maxDepth = depth;
        }
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getAllocations() {
        return allocations;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void reset() {
        comparisons = 0;
        allocations = 0;
        maxDepth = 0;
    }
}
