## Problem 22 – Resource Allocation Scheduler

This project implements a simple **resource allocation scheduler** (for meeting rooms, train platforms, etc.) based on **Problem 22** from your DSA work.

The goal is to take a set of time interval requests and assign them to the **minimum number of resources**, using:

- **QuickSort** to order requests
- Java’s built‑in **PriorityQueue** as a min‑heap to track which resource becomes free first
- An **ArrayList** to store all requests

The main code file is:

- `Problem22_EasyScheduler.java`

---

## How It Works (Short Version)

Each **request** has:

- `id` – request ID (e.g. `R1`)
- `start` – start time (integer)
- `end` – end time (integer)
- `priority` – optional (default 0, higher number = higher priority)

Scheduling rules:

1. **Sort** all requests by:
   - start time (smallest first)
   - then priority (higher first)
   - then ID (alphabetical)
2. Process requests in this order.
3. Use a **min-heap** (PriorityQueue) of resources ordered by `nextFreeTime`:
   - If the earliest-free resource is free at the request’s start time → **reuse** it.
   - Otherwise → **create a new resource**.
4. Track:
   - `Total Resources Created`
   - `Peak Concurrent Usage`

---

## CLI Commands

Run the program and then type commands like:

- `REQUEST <id> <start> <end> [priority]`
  - Example: `REQUEST R1 0 10`
  - Example with priority: `REQUEST R2 5 15 2`
- `SCHEDULE`
  - Sorts all requests and assigns resources.
- `STATS`
  - Shows total resources created and peak usage.
- `EXIT`
  - Quits the program.

Example session:

```text
REQUEST R1 0 10
REQUEST R2 5 15
REQUEST R3 10 20
REQUEST R4 15 25

SCHEDULE
STATS
```

---

## How to Compile and Run

From the `dsa` folder:

```bash
javac Problem22_EasyScheduler.java
java Problem22_EasyScheduler
```

Then enter commands as shown above.

