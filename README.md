# DSA Problem Combination 11 – Java Implementation

## Overview

This repository contains the implementation of Problem Combination #11 for the Data Structures and Algorithms (DSA) course.
All problems are implemented using Java, following efficient algorithmic and data structure principles.

Each problem is organized into its own package, with a dedicated implementation and documentation.

## Problem Combination Details

Problem Combination #11 includes:

- **Problem #22 – Resource Allocation Scheduler**
- **Problem #53 – Multi-Class Auction System**
- **Problem #21 – Real-Time Median Monitor**

## Technologies Used

- **Programming Language:** Java
- **Core Data Structures:**
  - Priority Queues (Min-Heaps)
  - Stacks (ArrayDeque)
  - ArrayLists
  - HashMaps
- **Development Tools:**
  - JDK 8 or later
  - Any Java-supported IDE (IntelliJ IDEA, Eclipse, VS Code)

## Project Structure

Each problem folder contains:

- Source code files
- A README.md explaining the problem, logic, and execution steps

## Team Members & Contributions

### Abigiya Sirak

**Problem #22 – Resource Allocation Scheduler**
- Designed a resource allocation scheduler using QuickSort and PriorityQueue (min-heap).
- Optimized resource assignment with O(n log n) sorting and O(log k) heap operations.
- Implemented REQUEST, SCHEDULE, and STATS commands.
- Tracks minimum resources needed and peak concurrent usage.

### Nebiyu Samuel

**Problem #53 – Multi-Class Auction System**
- Implemented auction logic using a Stack (ArrayDeque) for bid tracking.
- Added bid withdrawal functionality with stack-based reversion.
- Managed minimum bid constraints and consecutive bid prevention.
- Implemented START, BID, WITHDRAW, CURRENT, and EXIT commands.

### Kidus Amaha

**Problem #21 – Real-Time Median Monitor**
- Built real-time median tracking using two PriorityQueues (min-heap and max-heap).
- Maintained balanced heaps for O(log n) median calculation.
- Implemented ADD, MEDIAN, and DEBUG operations.
- Demonstrated efficient two-heap method for continuous median tracking.

## How to Run the Code

1. **Clone the repository**
   ```bash
   git clone https://github.com/abbygush/Combination-11.git
   ```

2. **Follow each problem's README.md file to run**
   - Navigate to the specific problem folder
   - Compile and run the Java files as instructed in each README
