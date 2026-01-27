import java.util.*;

record Bid(String user, int amount) {
    public String toString() { return amount() + ""; }
}

public class Auction {
    public static void main(String[] args) {
        var stack = new ArrayDeque<Bid>();
        var scanner = new Scanner(System.in);
        String lastBidder = null;
        Integer minBid = null;
        
        while (true) {
            System.out.print("> ");
            String[] p = scanner.nextLine().split(" ");
            
            switch (p[0]) {
                case "START" -> {
                    if (p.length < 2) {
                        System.out.println("Error: Invalid format. Use: START <amount>");
                        break;
                    }
                    try {
                        int amt = Integer.parseInt(p[1]);
                        if (amt < 0) {
                            System.out.println("Error: Minimum bid must be a positive whole number.");
                        } else {
                            minBid = amt;
                            System.out.println("Auction started. Minimum bid: " + minBid);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Amount must be a whole number.");
                    }
                }
                case "BID" -> {
                    if (minBid == null) {
                        System.out.println("Error: Bid didn't start yet.");
                        break;
                    }
                    if (p.length < 3) {
                        System.out.println("Error: Invalid format. Use: BID <user> <amount>");
                        break;
                    }
                    String bidder = p[1];
                    if (bidder.equals(lastBidder)) {
                        System.out.println("Error: You cannot bid twice in a row.");
                        break;
                    }
                    try {
                        int amt = Integer.parseInt(p[2]);
                        if (amt < 0) {
                            System.out.println("Error: Bid amount must be a positive whole number.");
                        } else if (stack.isEmpty() && amt < minBid) {
                            System.out.println("Error: Minimum starting bid is " + minBid + ".");
                        } else if (!stack.isEmpty() && amt <= stack.peek().amount()) {
                            System.out.println("Error: Too low.");
                        } else {
                            stack.push(new Bid(bidder, amt));
                            lastBidder = bidder;
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
                        if (stack.isEmpty()) {
                            lastBidder = null;
                            System.out.println("No bids remaining.");
                        } else {
                            lastBidder = stack.peek().user();
                            System.out.println("Reverted to " + stack.peek().user() + " (" + stack.peek().amount() + ")");
                        }
                        System.out.println("Stack: " + stack);
                    }
                }
                case "CURRENT" -> {
                    if (minBid == null) {
                        System.out.println("Auction hasn't started yet.");
                    } else if (stack.isEmpty()) {
                        System.out.println("No bids yet. Minimum bid: " + minBid);
                    } else {
                        System.out.println("Current: " + stack.peek().user() + " (" + stack.peek().amount() + ")");
                    }
                }
                case "EXIT" -> {
                    if (stack.isEmpty()) {
                        System.out.println("No winner. Auction ended with no bids.");
                    } else {
                        System.out.println("Auction ended! Winner: " + stack.peek().user() + " with " + stack.peek().amount());
                    }
                    return;
                }
            }
        }
    }
}