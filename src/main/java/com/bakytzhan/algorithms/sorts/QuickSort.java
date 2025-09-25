package com.bakytzhan.algorithms.sorts;

import com.bakytzhan.algorithms.util.Metrics;

import java.util.Random;

public class QuickSort {
    private static final int CUTOFF = 16; // для маленьких массивов используем вставки
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
        swap(arr, pivotIndex, right);

        int partitionIndex = partition(arr, left, right, pivot, metrics);

        // Сначала сортируем меньший кусок (для уменьшения глубины рекурсии)
        if (partitionIndex - left < right - partitionIndex) {
            quickSort(arr, left, partitionIndex - 1, metrics, depth + 1);
            quickSort(arr, partitionIndex + 1, right, metrics, depth + 1);
        } else {
            quickSort(arr, partitionIndex + 1, right, metrics, depth + 1);
            quickSort(arr, left, partitionIndex - 1, metrics, depth + 1);
        }
    }

    private static int partition(int[] arr, int left, int right, int pivot, Metrics metrics) {
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

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
