package com.bakytzhan.algorithms.closest;

import com.bakytzhan.algorithms.util.Metrics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestPairTest {

    @Test
    void testSimple() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4),
                new ClosestPair.Point(1, 1),
                new ClosestPair.Point(2, 2)
        };
        Metrics m = new Metrics();
        double d = ClosestPair.closest(pts, m);
        assertEquals(Math.sqrt(2), d, 1e-9);
    }

    @Test
    void testTwoPoints() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(1, 1),
                new ClosestPair.Point(4, 5)
        };
        Metrics m = new Metrics();
        double d = ClosestPair.closest(pts, m);
        assertEquals(5.0, d, 1e-9);
    }

    @Test
    void testDuplicates() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(2, 2),
                new ClosestPair.Point(2, 2),
                new ClosestPair.Point(5, 5)
        };
        Metrics m = new Metrics();
        double d = ClosestPair.closest(pts, m);
        assertEquals(0.0, d, 1e-9);
    }
}
