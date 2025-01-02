package org.nitish;

import java.util.HashMap;
import java.util.Map;

class User {
    private String userId;
    private String name;
    private double accountBalance;
    private Map<String, Integer> portfolio;

    public User(String userId, String name, double accountBalance) {
        this.userId = userId;
        this.name = name;
        this.accountBalance = accountBalance;
        this.portfolio = new HashMap<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void deductBalance(double amount) {
        if (amount > accountBalance) {
            throw new IllegalArgumentException("Insufficient balance!");
        }
        accountBalance -= amount;
    }

    public void addBalance(double amount) {
        accountBalance += amount;
    }

    public void updatePortfolio(String stock, int quantity) {
        portfolio.put(stock, portfolio.getOrDefault(stock, 0) + quantity);
    }

    public Map<String, Integer> getPortfolio() {
        return portfolio;
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + name + ", Balance: " + accountBalance + ", Portfolio: " + portfolio;
    }
}
