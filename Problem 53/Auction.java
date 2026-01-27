import java.util.*;

record Bid(String user, int amount) {
    public String toString() { return amount() + ""; }
}

public class Auction {
    public static void main(String[] args) {
        var stack = new ArrayDeque<Bid>();
        var scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("> ");
            String[] p = scanner.nextLine().split(" ");
            
            switch (p[0]) {
                case "BID" -> {
                    try {
                        int amt = Integer.parseInt(p[2]);
                        if (!stack.isEmpty() && amt <= stack.peek().amount()) {
                            System.out.println("Error: Too low.");
                        } else {
                            stack.push(new Bid(p[1], amt));
                            System.out.println("Current: " + stack.peek().user() + " (" + stack.peek().amount() + ")");
                            System.out.println("Stack: " + stack);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Bid amount must be a whole number.");
                    }
                }
                case "WITHDRAW" -> {
                    if (stack.isEmpty()) {
                        System.out.println("Error: No bids to withdraw.");
                    } else {
                        var withdrawn = stack.pop();
                        System.out.println(withdrawn.user() + " retracted.");
                        if (stack.isEmpty()) System.out.println("No bids remaining.");
                        else System.out.println("Reverted to " + stack.peek().user() + " (" + stack.peek().amount() + ")");
                        System.out.println("Stack: " + stack);
                    }
                }
                case "CURRENT" -> {
                    if (stack.isEmpty()) System.out.println("No bids yet.");
                    else System.out.println("Current: " + stack.peek().user() + " (" + stack.peek().amount() + ")");
                }
                case "EXIT" -> { return; }
            }
        }
    }
}