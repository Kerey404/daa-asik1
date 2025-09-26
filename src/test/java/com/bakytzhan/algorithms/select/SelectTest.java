package com.bakytzhan.algorithms.select;

import com.bakytzhan.algorithms.util.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectTest {

    private final Random rnd = new Random(12345);

    @Test
    void testRandomK() {
        int n = 200;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = rnd.nextInt(1000) - 500; // допускаем дубликаты и отрицательные
        int k = rnd.nextInt(n);

        int[] copy = a.clone();
        Arrays.sort(copy);
        int expected = copy[k];

        Metrics m = new Metrics();
        int actual = Select.select(a, k, m);

        assertEquals(expected, actual);
    }

    @Test
    void testAllPositionsSmall() {
        int[] a = {7, 2, 5, 3, 3, 11, 0};
        int[] copy = a.clone();
        Arrays.sort(copy);
        for (int k = 0; k < a.length; k++) {
            Metrics m = new Metrics();
            int val = Select.select(a.clone(), k, m);
            assertEquals(copy[k], val);
        }
    }

    @Test
    void testDuplicates() {
        int[] a = {5,5,5,5,5,5};
        Metrics m = new Metrics();
        for (int k = 0; k < a.length; k++) {
            assertEquals(5, Select.select(a.clone(), k, m));
        }
    }

    @Test
    void testSingleElement() {
        int[] a = {42};
        Metrics m = new Metrics();
        assertEquals(42, Select.select(a, 0, m));
    }
}
