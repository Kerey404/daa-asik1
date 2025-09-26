package com.bakytzhan.algorithms.closest;

import com.bakytzhan.algorithms.util.Metrics;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static double closest(Point[] points, Metrics m) {
        if (points == null || points.length < 2) {
            throw new IllegalArgumentException("Need at least 2 points");
        }
        Point[] pts = points.clone();
        Arrays.sort(pts, Comparator.comparingDouble(p -> p.x)); // сортировка по X
        return closestRec(pts, 0, pts.length - 1, m, 1);
    }

    private static double closestRec(Point[] pts, int lo, int hi, Metrics m, int depth) {
        m.recordDepth(depth);
        if (hi - lo <= 3) {
            return bruteForce(pts, lo, hi, m);
        }

        int mid = (lo + hi) / 2;
        double midX = pts[mid].x;

        double d1 = closestRec(pts, lo, mid, m, depth + 1);
        double d2 = closestRec(pts, mid + 1, hi, m, depth + 1);
        double d = Math.min(d1, d2);

        // собрать "полосу" шириной 2d вокруг midX
        Point[] strip = new Point[hi - lo + 1];
        m.trackAllocation();
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (Math.abs(pts[i].x - midX) < d) {
                strip[count++] = pts[i];
            }
        }
        Arrays.sort(strip, 0, count, Comparator.comparingDouble(p -> p.y));

        // проверить соседей в полосе
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count && (strip[j].y - strip[i].y) < d; j++) {
                m.incrementComparisons();
                d = Math.min(d, distance(strip[i], strip[j]));
            }
        }

        return d;
    }

    private static double bruteForce(Point[] pts, int lo, int hi, Metrics m) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = lo; i <= hi; i++) {
            for (int j = i + 1; j <= hi; j++) {
                m.incrementComparisons();
                min = Math.min(min, distance(pts[i], pts[j]));
            }
        }
        return min;
    }

    private static double distance(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Вспомогательный класс точки
    public static class Point {
        public final double x, y;
        public Point(double x, double y) {
            this.x = x; this.y = y;
        }
    }
}
