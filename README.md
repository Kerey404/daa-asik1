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

### Running CLI to generate CSV
The CLI outputs lines in the format:  
