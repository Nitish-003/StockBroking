package org.nitish;

class StockMarket extends Thread {
    private String stock;
    private int quantity;
    private User user;
    private double price;
    private boolean isBuy;
    private StockTransaction transaction;

    public StockMarket(String stock, int quantity, User user, double price, boolean isBuy, StockTransaction transaction) {
        this.stock = stock;
        this.quantity = quantity;
        this.user = user;
        this.price = price;
        this.isBuy = isBuy;
        this.transaction = transaction;
    }

    @Override
    public void run() {
        if (isBuy) {
            buyStock();
        } else {
            sellStock();
        }
    }

    private void buyStock() {
        double totalCost = price * quantity;
        double brokerage = transaction.calculateTransactionCost(totalCost, 0.02);
        double finalAmount = totalCost + brokerage;

        synchronized (user) {
            if (user.getAccountBalance() >= finalAmount) {
                user.deductBalance(finalAmount);
                user.updatePortfolio(stock, quantity);
                transaction.logTransaction("Bought " + quantity + " shares of " + stock + " for " + finalAmount + " (Brokerage: " + brokerage + ")");
                System.out.println("Buy transaction successful. Bought " + quantity + " shares of " + stock + " for " + finalAmount + " (Brokerage: " + brokerage + ")");
            } else {
                System.out.println("Insufficient balance for buying.");
            }
        }
    }

    private void sellStock() {
        synchronized (user) {
            int currentQuantity = user.getPortfolio().getOrDefault(stock, 0);
            if (currentQuantity >= quantity) {
                double totalGain = price * quantity;
                double tax = transaction.calculateTax(totalGain, 0.10);
                double finalGain = totalGain - tax;

                user.addBalance(finalGain);
                user.updatePortfolio(stock, -quantity);
                transaction.logTransaction("Sold " + quantity + " shares of " + stock + " for " + totalGain + " (Tax: " + tax + ")");
                System.out.println("Sell transaction successful. Sold " + quantity + " shares of " + stock + " for " + totalGain + " (Tax: " + tax + ")");
            } else {
                System.out.println("Not enough shares to sell.");
            }
        }
    }
}
