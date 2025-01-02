package org.nitish;

interface Transaction {
    double calculateTransactionCost(double amount, double brokerageRate);
    double calculateTax(double amount, double taxRate);
    void executeTransaction(String stockName, int quantity);
}