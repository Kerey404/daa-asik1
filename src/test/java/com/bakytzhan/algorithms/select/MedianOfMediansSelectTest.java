package com.bakytzhan.algorithms.select;

import com.bakytzhan.algorithms.util.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedianOfMediansSelectTest {

    @Test
    void testSmallArray() {
        int[] arr = {7, 2, 1, 6, 8, 5, 3, 4};
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        Metrics m = new Metrics();
        for (int k = 0; k < arr.length; k++) {
            int[] copy = arr.clone();
            int res = MedianOfMediansSelect.select(copy, k, m);
            assertEquals(sorted[k], res, "k=" + k + " failed");
        }
    }

    @Test
    void testRandomArray() {
        Random rnd = new Random(42);
        int[] arr = rnd.ints(200, -1000, 1000).toArray();
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        Metrics m = new Metrics();
        for (int k = 0; k < 50; k++) { // проверим 50 случайных k
            int[] copy = arr.clone();
            int res = MedianOfMediansSelect.select(copy, k, m);
            assertEquals(sorted[k], res);
        }
    }
}
