package com.bakytzhan.algorithms.sorts;

import com.bakytzhan.algorithms.util.Metrics;
import com.bakytzhan.algorithms.util.SortUtils;

import java.util.Random;

public class QuickSort {
    private static final int CUTOFF = 16;
    private static final Random random = new Random();

    public static void sort(int[] arr, Metrics metrics) {
        quickSort(arr, 0, arr.length - 1, metrics, 1);
    }

    private static void quickSort(int[] arr, int left, int right, Metrics metrics, int depth) {
        if (left >= right) return;

        if (right - left + 1 <= CUTOFF) {
            insertionSort(arr, left, right, metrics);
            return;
        }

        int pivotIndex = left + random.nextInt(right - left + 1);
        int pivot = arr[pivotIndex];
        SortUtils.swap(arr, pivotIndex, right);

        int partitionIndex = SortUtils.partition(arr, left, right, pivot, metrics);

        if (partitionIndex - left < right - partitionIndex) {
            quickSort(arr, left, partitionIndex - 1, metrics, depth + 1);
            quickSort(arr, partitionIndex + 1, right, metrics, depth + 1);
        } else {
            quickSort(arr, partitionIndex + 1, right, metrics, depth + 1);
            quickSort(arr, left, partitionIndex - 1, metrics, depth + 1);
        }
    }

    private static void insertionSort(int[] arr, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                metrics.incrementComparisons();
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
