import java.util.*;

public class Problem22_EasyScheduler {

    // Represents one request for a resource
    static class Request {
        String id;
        int start;
        int end;
        int priority;
        int assignedResource; // which resource this request uses

        Request(String id, int start, int end, int priority) {
            this.id = id;
            this.start = start;
            this.end = end;
            this.priority = priority;
            this.assignedResource = 0;
        }
    }

    // Represents one resource (room / platform)
    static class Resource {
        int id;
        int nextFreeTime;

        Resource(int id, int nextFreeTime) {
            this.id = id;
            this.nextFreeTime = nextFreeTime;
        }
    }

    // Simple min-heap for Resource, ordered by nextFreeTime
    static class MinHeap {
        ArrayList<Resource> data = new ArrayList<>();

        int size() {
            return data.size();
        }

        Resource peek() {
            return data.get(0);
        }

        void push(Resource r) {
            data.add(r);
            siftUp(data.size() - 1);
        }

        Resource pop() {
            Resource root = data.get(0);
            Resource last = data.remove(data.size() - 1);
            if (!data.isEmpty()) {
                data.set(0, last);
                siftDown(0);
            }
            return root;
        }

        void siftUp(int i) {
            while (i > 0) {
                int p = (i - 1) / 2;
                if (data.get(p).nextFreeTime <= data.get(i).nextFreeTime) break;
                swap(i, p);
                i = p;
            }
        }

        void siftDown(int i) {
            int n = data.size();
            while (true) {
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                int smallest = i;

                if (left < n && data.get(left).nextFreeTime < data.get(smallest).nextFreeTime) {
                    smallest = left;
                }
                if (right < n && data.get(right).nextFreeTime < data.get(smallest).nextFreeTime) {
                    smallest = right;
                }
                if (smallest == i) break;
                swap(i, smallest);
                i = smallest;
            }
        }

        void swap(int i, int j) {
            Resource tmp = data.get(i);
            data.set(i, data.get(j));
            data.set(j, tmp);
        }
    }

    static ArrayList<Request> requests = new ArrayList<>();
    static int totalResources = 0;
    static int peakUsage = 0;
    static boolean scheduled = false;

    // Compare a before b (true if a should come first)
    static boolean better(Request a, Request b) {
        if (a.start != b.start) return a.start < b.start;          // earlier start first
        if (a.priority != b.priority) return a.priority > b.priority; // higher priority first
        return a.id.compareTo(b.id) < 0;                           // smaller id first
    }

    // QuickSort requests according to the rules
    static void quickSort(ArrayList<Request> arr, int lo, int hi) {
        if (lo >= hi) return;
        Request pivot = arr.get((lo + hi) / 2);
        int i = lo, j = hi;
        while (i <= j) {
            while (better(arr.get(i), pivot)) i++;
            while (better(pivot, arr.get(j))) j--;
            if (i <= j) {
                Request tmp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, tmp);
                i++;
                j--;
            }
        }
        quickSort(arr, lo, j);
        quickSort(arr, i, hi);
    }

    static void addRequest(String id, int start, int end, int priority) {
        requests.add(new Request(id, start, end, priority));
        scheduled = false;
        System.out.println("Added request " + id + " (" + start + "-" + end + "), priority " + priority);
    }

    static void scheduleAll() {
        if (requests.isEmpty()) {
            System.out.println("No requests to schedule.");
            return;
        }

        quickSort(requests, 0, requests.size() - 1);
        MinHeap heap = new MinHeap();
        totalResources = 0;
        peakUsage = 0;

        System.out.println("Processing requests sorted by start time...");
        for (Request r : requests) {
            int assignedId;

            if (heap.size() > 0 && heap.peek().nextFreeTime <= r.start) {
                Resource res = heap.pop(); // reuse existing resource
                assignedId = res.id;
            } else {
                assignedId = ++totalResources; // create new resource
            }

            r.assignedResource = assignedId;
            heap.push(new Resource(assignedId, r.end));
            peakUsage = Math.max(peakUsage, totalResources);

            System.out.println("- " + r.id + " (" + r.start + "-" + r.end + "): Assigned to Resource " + assignedId + ".");
        }

        scheduled = true;
    }

    static void showStats() {
        if (!scheduled) {
            System.out.println("Please run SCHEDULE first.");
            return;
        }
        System.out.println("\n=== STATS ===");
        System.out.println("Total Resources Created: " + totalResources);
        System.out.println("Peak Concurrent Usage: " + peakUsage);
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Commands: REQUEST <id> <start> <end> [priority] | SCHEDULE | STATS | EXIT\n");

        while (true) {
            System.out.print("> ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            String cmd = parts[0].toUpperCase();

            if (cmd.equals("REQUEST")) {
                String id = parts[1];
                int start = Integer.parseInt(parts[2]);
                int end = Integer.parseInt(parts[3]);
                int priority = (parts.length >= 5) ? Integer.parseInt(parts[4]) : 0;
                addRequest(id, start, end, priority);
            } else if (cmd.equals("SCHEDULE")) {
                scheduleAll();
            } else if (cmd.equals("STATS")) {
                showStats();
            } else if (cmd.equals("EXIT")) {
                break;
            } else {
                System.out.println("Unknown command.");
            }
        }

        sc.close();
    }
}

