package com.bakytzhan.algorithms.util;

import java.util.Random;

public class SortUtils {
    private static final Random random = new Random();


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static int partition(int[] arr, int left, int right, int pivot, Metrics m) {
        int i = left;
        for (int j = left; j < right; j++) {
            m.incrementComparisons();
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }


    public static void shuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            swap(arr, i, j);
        }
    }


    public static boolean isEmpty(int[] arr) {
        return arr == null || arr.length == 0;
    }
}
