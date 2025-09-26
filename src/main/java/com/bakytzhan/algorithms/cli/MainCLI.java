package com.bakytzhan.algorithms.cli;

import com.bakytzhan.algorithms.sorts.MergeSort;
import com.bakytzhan.algorithms.sorts.QuickSort;
import com.bakytzhan.algorithms.select.Select;
import com.bakytzhan.algorithms.closest.ClosestPair;
import com.bakytzhan.algorithms.util.Metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MainCLI {

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.out.println("Usage: java -jar daa-asik1.jar <algo> <n> <output.csv>");
            System.out.println("Algos: mergesort | quicksort | select | closest");
            return;
        }

        String algo = args[0];
        int n = Integer.parseInt(args[1]);
        String outFile = args[2];

        Metrics m = new Metrics();
        Random rnd = new Random();

        if (algo.equals("mergesort")) {
            int[] a = rnd.ints(n, -1000, 1000).toArray();
            MergeSort.sort(a, m);
        } else if (algo.equals("quicksort")) {
            int[] a = rnd.ints(n, -1000, 1000).toArray();
            QuickSort.sort(a, m);
        } else if (algo.equals("select")) {
            int[] a = rnd.ints(n, -1000, 1000).toArray();
            int k = rnd.nextInt(n);
            Select.select(a, k, m);
        } else if (algo.equals("closest")) {
            ClosestPair.Point[] pts = new ClosestPair.Point[n];
            for (int i = 0; i < n; i++) {
                pts[i] = new ClosestPair.Point(rnd.nextDouble() * 1000, rnd.nextDouble() * 1000);
            }
            ClosestPair.closest(pts, m);
        } else {
            System.out.println("Unknown algorithm: " + algo);
            return;
        }

        try (FileWriter fw = new FileWriter(outFile, true)) {
            fw.write(String.format("%s,%d,%d,%d,%d\n",
                    algo,
                    n,
                    m.getComparisons(),
                    m.getMaxDepth(),
                    m.getAllocations()
            ));
        }
        System.out.println("Done. Metrics written to " + outFile);
    }
}
