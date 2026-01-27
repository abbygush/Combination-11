
# Java Auction Program

A simple **console-based auction system** in Java that allows users to start an auction, place bids, withdraw bids, and determine the winner.  

## Features

- Start an auction with a **minimum bid**.  
- Users can **place bids**, but cannot bid twice in a row.  
- **Withdraw bids**, reverting to the previous highest bid.  
- Check the **current highest bid** at any time.  
- Exit the auction and declare the **winner**.  

## Requirements

- **Java 17+** (uses `record` and enhanced switch syntax)  
- Command-line interface  

## How to Run

1. **Compile** the program:  
   ```bash
   javac Auction.java
   ```

2. **Run** the program:  
   ```bash
   java Auction
   ```

## Commands

| Command               | Description                                         | Example         |
|-----------------------|-----------------------------------------------------|----------------|
| `START <amount>`      | Set minimum bid to start the auction               | `START 100`    |
| `BID <user> <amount>` | Place a bid (must be higher than current, no consecutive bids) | `BID Alice 150` |
| `WITHDRAW`            | Retract the last bid (reverts to previous)         | `WITHDRAW`     |
| `CURRENT`             | Show current highest bid / winner                  | `CURRENT`      |
| `EXIT`                | End auction and announce the winner                | `EXIT`         |  

## Example Session

```
> START 50
Auction started. Minimum bid: 50

> BID Alice 60
Current: Alice (60)
Stack: [60]

> BID Bob 70
Current: Bob (70)
Stack: [70, 60]

> WITHDRAW
Bob retracted.
Reverted to Alice (60)
Stack: [60]

> CURRENT
Current: Alice (60)

> EXIT
Auction ended! Winner: Alice with 60
```

## Notes

- Bid amounts must be **positive whole numbers**.  
- The **same user cannot bid consecutively**.  
- The program uses a **stack** to track bids, allowing easy withdrawal of the last bid.  
