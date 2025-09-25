package com.bakytzhan.algorithms.sorts;

import com.bakytzhan.algorithms.util.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {
    @Test
    void testRandomArray() {
        int[] a = {5, 2, 9, 1, 5, 6};
        int[] expected = a.clone();
        Arrays.sort(expected);
        Metrics m = new Metrics();
        MergeSort.sort(a, m);
        assertArrayEquals(expected, a);
    }

    @Test
    void testEmptyArray() {
        int[] a = {};
        Metrics m = new Metrics();
        MergeSort.sort(a, m);
        assertArrayEquals(new int[]{}, a);
    }

    @Test
    void testSortedArray() {
        int[] a = {1, 2, 3, 4, 5};
        int[] expected = a.clone();
        Metrics m = new Metrics();
        MergeSort.sort(a, m);
        assertArrayEquals(expected, a);
    }

    @Test
    void testReverseArray() {
        int[] a = {5, 4, 3, 2, 1};
        int[] expected = a.clone();
        Arrays.sort(expected);
        Metrics m = new Metrics();
        MergeSort.sort(a, m);
        assertArrayEquals(expected, a);
    }
}
