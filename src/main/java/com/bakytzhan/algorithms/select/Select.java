package com.bakytzhan.algorithms.select;

import com.bakytzhan.algorithms.util.Metrics;

import java.util.Arrays;

public class Select {


    public static int select(int[] a, int k, Metrics m) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("k out of bounds");
        }
        return selectInRange(a, 0, a.length - 1, k, m, 1);
    }


    private static int selectInRange(int[] a, int left, int right, int k, Metrics m, int depth) {
        m.recordDepth(depth);

        int n = right - left + 1;

        if (n <= 10) {
            m.trackAllocation();
            int[] tmp = new int[n];
            for (int i = 0; i < n; i++) tmp[i] = a[left + i];
            Arrays.sort(tmp);
            return tmp[k];
        }

        // 1) Разбить на группы по 5 и собрать медианы
        int numGroups = (n + 4) / 5;
        int[] medians = new int[numGroups];
        m.trackAllocation();
        for (int gi = 0; gi < numGroups; gi++) {
            int gLeft = left + gi * 5;
            int gRight = Math.min(gLeft + 4, right);
            int size = gRight - gLeft + 1;

            // копируем группу во временный массив, сортируем и берём медиану
            int[] group = new int[size];
            m.trackAllocation();
            for (int i = 0; i < size; i++) group[i] = a[gLeft + i];

            // insertion sort для маленькой группы (size <=5)
            insertionSortSmall(group, m);

            medians[gi] = group[size / 2];
        }

        // 2) Рекурсивно найдём медиану медиан (pivot)
        int pivot = selectInRange(medians, 0, medians.length - 1, medians.length / 2, m, depth + 1);

        // 3) Partition исходного массива вокруг pivot (включая <= pivot в левую часть)
        // Найдём один индекс с pivot и поставим его в конец (right).
        int pivotIndex = -1;
        for (int i = left; i <= right; i++) {
            m.incrementComparisons();
            if (a[i] == pivot) { pivotIndex = i; break; }
        }
        if (pivotIndex == -1) {

            // если не нашли точного равенства, просто используем right как pivotIndex.
            pivotIndex = right;
        }
        swap(a, pivotIndex, right);

        // partition: все <= pivot слева
        int store = left;
        for (int i = left; i < right; i++) {
            m.incrementComparisons();
            if (a[i] <= pivot) {
                swap(a, store++, i);
            }
        }
        swap(a, store, right); // pivot в конечную позицию
        int rank = store - left; // количество элементов < = pivot в левой части minus offset

        if (k == rank) {
            return a[store];
        } else if (k < rank) {
            return selectInRange(a, left, store - 1, k, m, depth + 1);
        } else {
            return selectInRange(a, store + 1, right, k - rank - 1, m, depth + 1);
        }
    }

    // Вспомогательный: сортировка вставками для маленького массива
    private static void insertionSortSmall(int[] arr, Metrics m) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                m.incrementComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else break;
            }
            arr[j + 1] = key;
        }
    }

    private static void swap(int[] a, int i, int j) {
        if (i == j) return;
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
