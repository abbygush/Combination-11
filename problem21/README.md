# RealTimeMedianMonitor

This is a simple **Java console program** that keeps track of the **median** of numbers as you enter them.

You can keep adding numbers, and at any time ask the program to show the current median.

## What does it do?

* You enter numbers one by one
* The program stores them efficiently
* It always knows the median (the middle value)

The goal of this project is to demonstrate how the **two-heap method** works in a clear and practical way.


## Files in the Project

RealTimeMedianMonitor.java
README.md

Only standard Java is used. No extra libraries.

## How to Run the Program

### 1. Open a terminal in the folder where the file is saved

Make sure `RealTimeMedianMonitor.java` is in that folder.

### 2. Compile the program


javac RealTimeMedianMonitor.java


### 3. Run the program


java RealTimeMedianMonitor

⚠️ Note:

* Java is case‑sensitive
* Do NOT write `.java` when running


## Commands You Can Use

### Add a number

ADD 10
ADD 5
ADD 20


### Get the median

MEDIAN


### See internal heaps (for learning/debugging)

DEBUG

### Exit the program

EXIT

## Example

ADD 10
ADD 20
ADD 5
MEDIAN

Output:

Median: 10.0

## Why this project?

* To practice Java
* To understand priority queues
* To learn how real‑time median calculation works


## Author

Kidus Amaha

