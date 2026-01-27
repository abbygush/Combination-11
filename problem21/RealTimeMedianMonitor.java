import java.util.*;
public class RealTimeMedianMonitor {

    private PriorityQueue<Integer> maxHeap; // smaller half (max-heap)
    private PriorityQueue<Integer> minHeap; // larger half (min-heap)

    public RealTimeMedianMonitor() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void add(int value) {
        if (maxHeap.isEmpty() || value <= maxHeap.peek()) {
            maxHeap.offer(value);
        } else {
            minHeap.offer(value);
        }

        // Balance the heaps
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double getMedian() {
        if (maxHeap.isEmpty()) {
            throw new IllegalStateException("No elements present");
        }

        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.peek();
    }

    public void DEBUG() {
        System.out.println("LeftHeap (Max): " + maxHeap);
        System.out.println("RightHeap (Min): " + minHeap);
    }

    public static void main(String[] args) {
        RealTimeMedianMonitor monitor = new RealTimeMedianMonitor();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("EXIT")) {
                break;
            } 
            else if (input.equalsIgnoreCase("MEDIAN")) {
                try {
                    System.out.println("Median: " + monitor.getMedian());
                } catch (IllegalStateException e) {
                    System.out.println("No elements yet");
                }
            } 
            else if (input.equalsIgnoreCase("DEBUG")) {
                monitor.DEBUG();
            } 
            else if (input.startsWith("ADD")) {
                String[] parts = input.split("\\s+");
                if (parts.length == 2) {
                    try {
                        int value = Integer.parseInt(parts[1]);
                        monitor.add(value);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number");
                    }
                } else {
                    System.out.println("Usage: ADD <number>");
                }
            } 
            else {
                System.out.println("Unknown command");
            }
        }

        scanner.close();
    }
}