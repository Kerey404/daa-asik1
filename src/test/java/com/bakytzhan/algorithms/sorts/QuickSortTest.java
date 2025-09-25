package com.bakytzhan.algorithms.sorts;

import com.bakytzhan.algorithms.util.Metrics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        Metrics m = new Metrics();
        QuickSort.sort(arr, m);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testReverseArray() {
        int[] arr = {5, 4, 3, 2, 1};
        Metrics m = new Metrics();
        QuickSort.sort(arr, m);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testRandomArray() {
        int[] arr = {3, 1, 4, 1, 5, 9, 2};
        Metrics m = new Metrics();
        QuickSort.sort(arr, m);
        assertArrayEquals(new int[]{1, 1, 2, 3, 4, 5, 9}, arr);
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        Metrics m = new Metrics();
        QuickSort.sort(arr, m);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        Metrics m = new Metrics();
        QuickSort.sort(arr, m);
        assertArrayEquals(new int[]{42}, arr);
    }
}
