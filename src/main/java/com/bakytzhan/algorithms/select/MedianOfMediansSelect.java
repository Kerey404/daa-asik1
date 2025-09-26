package com.bakytzhan.algorithms.select;

import com.bakytzhan.algorithms.util.Metrics;

import java.util.Arrays;

public class MedianOfMediansSelect {

    public static int select(int[] arr, int k, Metrics m) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        return select(arr, 0, arr.length - 1, k, m);
    }

    private static int select(int[] arr, int left, int right, int k, Metrics m) {
        if (left == right) return arr[left];

        int pivotIndex = pivot(arr, left, right, m);
        int partitionIndex = partition(arr, left, right, pivotIndex, m);

        if (k == partitionIndex) {
            return arr[k];
        } else if (k < partitionIndex) {
            return select(arr, left, partitionIndex - 1, k, m);
        } else {
            return select(arr, partitionIndex + 1, right, k, m);
        }
    }

    private static int pivot(int[] arr, int left, int right, Metrics m) {
        if (right - left < 5) {
            Arrays.sort(arr, left, right + 1);
            return (left + right) / 2;
        }

        int subRight = left;
        for (int i = left; i <= right; i += 5) {
            int subEnd = Math.min(i + 4, right);
            Arrays.sort(arr, i, subEnd + 1);
            int medianIndex = (i + subEnd) / 2;
            swap(arr, medianIndex, subRight++);
        }

        return pivot(arr, left, subRight - 1, m);
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex, Metrics m) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            m.incrementComparisons();
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, right, storeIndex);
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
