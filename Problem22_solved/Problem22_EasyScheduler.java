import java.util.*;

public class Problem22_EasyScheduler {

    // One request for a room/platform
    static class Request {
        String id;
        int start, end, priority;
        Request(String id, int s, int e, int p) {
            this.id = id; this.start = s; this.end = e; this.priority = p;
        }
    }

    // One resource with a next free time
    static class Resource {
        int id, nextFree;
        Resource(int id, int t) { this.id = id; this.nextFree = t; }
    }

    // All requests we collect
    static ArrayList<Request> reqs = new ArrayList<>();
    static int totalResources = 0, peakUsage = 0;
    static boolean scheduled = false;

    // a comes before b?
    static boolean better(Request a, Request b) {
        if (a.start != b.start) return a.start < b.start;
        if (a.priority != b.priority) return a.priority > b.priority;
        return a.id.compareTo(b.id) < 0;
    }

    // QuickSort by (start, -priority, id)
    static void quickSort(ArrayList<Request> a, int lo, int hi) {
        if (lo >= hi) return;
        Request p = a.get((lo + hi) / 2);
        int i = lo, j = hi;
        while (i <= j) {
            while (better(a.get(i), p)) i++;
            while (better(p, a.get(j))) j--;
            if (i <= j) {
                Request t = a.get(i);
                a.set(i, a.get(j));
                a.set(j, t);
                i++; j--;
            }
        }
        quickSort(a, lo, j);
        quickSort(a, i, hi);
    }

    static void addRequest(String id, int s, int e, int p) {
        reqs.add(new Request(id, s, e, p));
        scheduled = false;
        System.out.println("Added " + id + " (" + s + "-" + e + "), priority=" + p);
    }

    static void scheduleAll() {
        if (reqs.isEmpty()) {
            System.out.println("No requests to schedule.");
            return;
        }
        quickSort(reqs, 0, reqs.size() - 1);

        // Min-heap using Java PriorityQueue
        PriorityQueue<Resource> heap = new PriorityQueue<>(
                (r1, r2) -> Integer.compare(r1.nextFree, r2.nextFree));

        totalResources = 0;
        peakUsage = 0;

        System.out.println("Processing requests sorted by start time...");
        for (Request r : reqs) {
            int resId;
            if (!heap.isEmpty() && heap.peek().nextFree <= r.start) {
                resId = heap.poll().id;         // reuse room
            } else {
                resId = ++totalResources;       // create new room
            }
            heap.offer(new Resource(resId, r.end));
            peakUsage = Math.max(peakUsage, totalResources);
            System.out.println("- " + r.id + " (" + r.start + "-" + r.end +
                    "): Assigned to Resource " + resId + ".");
        }
        scheduled = true;
    }

    static void showStats() {
        if (!scheduled) {
            System.out.println("Run SCHEDULE first.");
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

            String[] in = line.split("\\s+");
            String cmd = in[0].toUpperCase();

            if (cmd.equals("REQUEST")) {
                String id = in[1];
                int s = Integer.parseInt(in[2]);
                int e = Integer.parseInt(in[3]);
                int p = (in.length >= 5) ? Integer.parseInt(in[4]) : 0;
                addRequest(id, s, e, p);
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

