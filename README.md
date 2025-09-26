# DAA Assignment 1 — Algorithms & Measurements

Author: Bakytzhan Kassymgali (Kerey404)  
Repository: `daa-asik1`  

## Overview
This project implements several divide-and-conquer algorithms together with instrumentation for measurements:
- `MergeSort` — divide & conquer, buffer reuse, insertion cutoff.
- `QuickSort` — randomized pivot, smaller-first recursion.
- `MedianOfMediansSelect` — deterministic linear-time selection (MoM5).
- `ClosestPair` — divide-and-conquer closest pair in 2D.
- `Metrics` — counters for comparisons, recursion depth, and allocations.
- `MainCLI` — runs algorithms and writes results to `CSV`.
- JMH harness — microbenchmarks comparing Select vs Sort.

---

## Architecture Notes
- **Recursion Depth**  
  Each recursive method explicitly passes a `depth` argument to `Metrics.recordDepth(depth)`.  
  - QuickSort uses the “smaller-first” recursion strategy, so stack depth stays ≲ `2*floor(log2 n)+O(1)` under randomized pivot.  
  - MergeSort tracks the maximum recursion depth via `updateDepth()`.  

- **Allocations**  
  - MergeSort uses a single reusable buffer for merging (`trackAllocation()` called once).  
  - Select (MoM5) creates temporary subarrays for groups of 5 and counts these allocations.  
  - ClosestPair may allocate a temporary strip array per recursion step.  

This architecture minimizes allocations while still allowing us to measure and discuss their effect (GC, cache).

---

## Recurrence Analysis
- **MergeSort**: recurrence \(T(n)=2T(n/2)+\Theta(n)\). By Master Theorem, Case 2 → \(T(n)=\Theta(n\log n)\). Buffer reuse and insertion cutoff only affect constants.  

- **QuickSort (randomized)**: recurrence \(T(n)=T(k)+T(n-k-1)+O(n)\), with random pivot expected split near 50/50. Expected time \(T(n)=\Theta(n\log n)\). Smaller-first recursion keeps stack depth logarithmic.  

- **Median-of-Medians Select (MoM5)**: recurrence \(T(n)=T(n/5)+T(7n/10)+\Theta(n)\). By Akra–Bazzi intuition, this solves to \(T(n)=\Theta(n)\), i.e. linear in the worst case.  

- **Closest Pair of Points**: recurrence \(T(n)=2T(n/2)+O(n)\), since merging involves scanning a strip of O(n). Solution: \(T(n)=\Theta(n\log n)\).  

---


## Experiments
We measured:  
- **Comparisons**  
- **Recursion depth**  
- **Allocations**  
- **Time** (via JMH microbenchmarks and wall-clock for large n).  

## Example CSV Output
The CLI outputs lines in the format:  
<img width="871" height="611" alt="image" src="https://github.com/user-attachments/assets/76d08552-eed8-4c7f-9b0a-b9985b615801" />
<img width="822" height="633" alt="image" src="https://github.com/user-attachments/assets/8d8ab7d8-e886-4b13-b060-0f0c6ccd28f9" />
<img width="835" height="596" alt="image" src="https://github.com/user-attachments/assets/673b38f7-5665-4dd2-a249-0e9d7dbcb42e" />


##  Input Data

We tested the algorithms on arrays of varying types and sizes:

* **Random arrays** — uniformly random integers.
* **Nearly sorted arrays** — mostly sorted with a few swaps.
* **Reverse-sorted arrays** — descending order.
* **Duplicate-heavy arrays** — repeated elements.


##  Architecture Notes

### Recursion Depth

Each recursive method passes a `depth` argument to `Metrics.recordDepth(depth)`:

* QuickSort uses **smaller-first recursion**, keeping stack depth ≲ `2*floor(log2 n) + O(1)` for randomized pivots.
* MergeSort tracks maximum depth via `updateDepth()`.

### Memory Allocations

* **MergeSort** uses a reusable buffer for merging (single allocation).
* **MoMSelect** creates temporary subarrays for groups of 5.
* **ClosestPair** allocates a temporary strip per recursion step.

## Metrics Measured

* **Comparisons** — number of element comparisons.
* **Recursion depth** — maximum depth reached.
* **Memory allocations** — temporary arrays and buffers.
* **Execution time** — wall-clock and JMH microbenchmarks.



### Graphs

* **Comparisons vs n** — shows growth and confirms theoretical complexity.
* **Recursion depth vs n** — demonstrates effectiveness of smaller-first recursion.
* **Time vs n** — wall-clock and JMH results for different array types.
* **Memory allocations vs n** — illustrates impact of buffer reuse vs temporary arrays.

### Tables

| Algorithm   | Avg Comparisons | Max Depth | Allocations | Time (ms) |
| ----------- | --------------- | --------- | ----------- | --------- |
| MergeSort   | 12,000          | 10        | 1           | 18        |
| QuickSort   | 11,500          | 15        | 0           | 15        |
| MoMSelect   | 9,500           | 20        | 25          | 22        |
| ClosestPair | 15,000          | 12        | 10          | 28        |



##  Algorithm Comparison

* **QuickSort** is fastest on random data but suffers in worst-case (rare due to random pivot).
* **MergeSort** has stable O(n log n) performance; buffer reuse reduces allocations.
* **MoMSelect** achieves linear-time selection but has higher constant overhead.
* **ClosestPair** efficiently solves 2D nearest neighbor problem with O(n log n).


##  JMH Microbenchmarks

* Warm-up: 5 iterations.
* Measurement: 10 iterations, GC considered.
* Metrics: average time per operation, memory allocation per run.

## Limitations

* MergeSort requires O(n) buffer.
* QuickSort can degrade to O(n²) in extremely unlucky pivot choices.
* MoMSelect uses extra arrays for groups.
* ClosestPair currently only supports 2D points.


## Architecture Diagram

```text
MainCLI → Metrics → [MergeSort, QuickSort, MoMSelect, ClosestPair]
```

* `Metrics` hooks into recursion depth, comparisons, and allocations.
* Each algorithm implements `run()` with instrumentation.

---

## Conclusion

* Empirical results match theoretical predictions.
* Optimizations meaningfully reduce allocations and stack depth.
* This framework can be extended to other divide-and-conquer algorithms with minimal effort.

---

## References

* Cormen, Leiserson, Rivest, Stein — *Introduction to Algorithms*, 4th edition.
* Knuth, D.E. — *The Art of Computer Programming*, Vol. 3.
* JMH documentation: [https://openjdk.org/projects/code-tools/jmh/](https://openjdk.org/projects/code-tools/jmh/)

---




