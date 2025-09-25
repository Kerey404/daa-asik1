package com.bakytzhan.algorithms.util;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    private final String fileName;

    public CSVWriter(String fileName) {
        this.fileName = fileName;
    }

    public void writeLine(String algo, int n, int run, long timeNs,
                          int depth, long comparisons, long allocations) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(String.format("%s,%d,%d,%d,%d,%d,%d%n",
                    algo, n, run, timeNs, depth, comparisons, allocations));
        } catch (IOException e) {
            throw new RuntimeException("Error writing CSV", e);
        }
    }
}
