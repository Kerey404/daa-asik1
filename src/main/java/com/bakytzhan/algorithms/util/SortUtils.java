package com.bakytzhan.algorithms.util;

import com.bakytzhan.algorithms.util.Metrics;

public class SortUtils {


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static int partition(int[] arr, int left, int right, int pivot, Metrics metrics) {
        int i = left;
        for (int j = left; j < right; j++) {
            metrics.incrementComparisons();
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }
}
