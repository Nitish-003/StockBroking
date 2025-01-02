package org.nitish;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StockMarketApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, User> users = new HashMap<>();

        StockTransaction transaction = new StockTransaction();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n\n---------Main Menu-----------:\n1. View User Details\n2. Add Funds\n3. Create New User\n4. Execute Transaction\n5. Generate Reports\n6. Exit\n\nEnter your Choice:");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter User ID:");
                    String userId = scanner.next();
                    User user = users.get(userId);
                    if (user != null) {
                        System.out.println(user);
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 2:
                    System.out.println("Enter User ID and Amount to Add:");
                    userId = scanner.next();
                    double amount = scanner.nextDouble();
                    user = users.get(userId);
                    if (user != null) {
                        user.addBalance(amount);
                        System.out.println("Funds added successfully. New Balance: " + user.getAccountBalance());
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 3:
                    System.out.println("Enter New User ID, Name, and Initial Balance:");
                    String newUserId = scanner.next();
                    String name = scanner.next();
                    double balance = scanner.nextDouble();
                    if (users.containsKey(newUserId)) {
                        System.out.println("User ID already exists.");
                    } else {
                        users.put(newUserId, new User(newUserId, name, balance));
                        System.out.println("User created successfully.");
                    }
                    break;

                case 4:
                    System.out.println("Enter User ID:");
                    userId = scanner.next();
                    user = users.get(userId);

                    if (user != null) {
                        System.out.println("\n\n---------Transaction Menu-----------:\n1. Buy a stock\n2. Sell a stock\n\nEnter your Choice:");
                        int transactionType = scanner.nextInt();

                        System.out.println("Enter Stock Name, Quantity, and Price:");
                        String stockName = scanner.next();
                        int quantity = scanner.nextInt();
                        double price = scanner.nextDouble();


                        if (transactionType == 1) {
                            StockMarket buyThread = new StockMarket(stockName, quantity, user, price, true, transaction);
                            System.out.println("Starting Buy transaction in the background");
                            buyThread.start();
                        } else if (transactionType == 2) {
                            StockMarket sellThread = new StockMarket(stockName, quantity, user, price, false, transaction);
                            System.out.println("Starting Sell transaction in the background");
                            sellThread.start();
                        } else {
                            System.out.println("Invalid transaction type.");
                        }
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 5:
                    System.out.println(transaction.generateSummaryReport());
                    break;

                case 6:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}

