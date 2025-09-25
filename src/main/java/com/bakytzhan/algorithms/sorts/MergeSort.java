package com.bakytzhan.algorithms.sorts;

import com.bakytzhan.algorithms.util.Metrics;

import java.util.Arrays;

public class MergeSort {
    private static final int INSERTION_SORT_CUTOFF = 16;

    public static void sort(int[] a, Metrics m) {
        int[] buf = new int[a.length];
        m.trackAllocation();
        mergeSort(a, buf, 0, a.length, 1, m);
    }

    private static void mergeSort(int[] a, int[] buf, int lo, int hi, int depth, Metrics m) {
        if (hi - lo <= INSERTION_SORT_CUTOFF) {
            insertionSort(a, lo, hi, m);
            return;
        }
        m.updateDepth(depth);
        int mid = (lo + hi) / 2;
        mergeSort(a, buf, lo, mid, depth + 1, m);
        mergeSort(a, buf, mid, hi, depth + 1, m);
        merge(a, buf, lo, mid, hi, m);
    }

    private static void merge(int[] a, int[] buf, int lo, int mid, int hi, Metrics m) {
        int i = lo, j = mid, k = lo;
        while (i < mid && j < hi) {
            if (m.compare(a[i], a[j]) <= 0) {
                buf[k++] = a[i++];
            } else {
                buf[k++] = a[j++];
            }
        }
        while (i < mid) buf[k++] = a[i++];
        while (j < hi) buf[k++] = a[j++];
        System.arraycopy(buf, lo, a, lo, hi - lo);
    }

    private static void insertionSort(int[] a, int lo, int hi, Metrics m) {
        for (int i = lo + 1; i < hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo && m.compare(a[j], key) > 0) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }
}
