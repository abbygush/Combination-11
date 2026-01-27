# Problem 22: Resource Allocation Scheduler (Rooms/Platforms)

## Problem Description
You are building a scheduling system for a shared resource (e.g., meeting rooms, train platforms, operating theaters). Requests arrive with time intervals, and you must assign resources to maximize utilization while minimizing the total number of resources needed.

### The System Rules
1.  **Requests**: Each request has:
    *   `Request ID`
    *   `Start Time`
    *   `End Time`
    *   `Priority` (Optional: Higher priority requests are scheduled first)
2.  **Resources**: Resources (e.g., rooms) are created on-demand.
3.  **Scheduling Logic**:
    *   Requests are processed in order of `Start Time`. Ties broken by `Priority` (higher first), then by `Request ID`.
    *   A request is assigned to the first available resource that becomes free at or before its `Start Time`.
    *   If no resource is free, a new resource is created.
4.  **Goal**: Minimize the peak number of resources used at any point in time.

## Must Use Data Structures
*   **Sorting Algorithm**: To sort requests by Start Time (use QuickSort).
*   **Min-Heap**: To track resources by their `next_available_time`. The root is the resource that frees up earliest.
*   **Array/List**: To store the full schedule assignment (Request -> Resource mapping).

## Operations to Implement (CLI Commands)
*   `REQUEST <id> <start> <end> [priority]`: Add a scheduling request.
*   `SCHEDULE`: Process all requests and output the resource assignments.
*   `STATS`: Show total resources used and peak concurrent usage.

## Sample Execution

```text
> REQUEST R1 0 10
> REQUEST R2 5 15
> REQUEST R3 10 20
> REQUEST R4 15 25

> SCHEDULE
Processing requests sorted by start time...
- R1 (0-10): Assigned to Resource 1.
- R2 (5-15): Resource 1 busy. Assigned to Resource 2.
- R3 (10-20): Resource 1 frees at 10. Assigned to Resource 1.
- R4 (15-25): Resource 2 frees at 15. Assigned to Resource 2.

> STATS
Total Resources Created: 2
Peak Concurrent Usage: 2 (between time 5 and 10)
```
