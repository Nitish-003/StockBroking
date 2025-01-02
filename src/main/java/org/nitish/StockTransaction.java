package org.nitish;

import java.util.ArrayList;
import java.util.List;

class StockTransaction implements Transaction, AdvancedReport {
    private List<String> transactionLog;

    public StockTransaction() {
        transactionLog = new ArrayList<>();
    }

    @Override
    public double calculateTransactionCost(double amount, double brokerageRate) {
        return amount * brokerageRate;
    }

    @Override
    public double calculateTax(double amount, double taxRate) {
        return amount * taxRate;
    }

    @Override
    public void executeTransaction(String stockName, int quantity) {
        System.out.println("Transaction executed for " + stockName + " with quantity " + quantity);
    }

    @Override
    public String generateSummaryReport() {
        return "Summary Report:\n" + String.join("\n", transactionLog);
    }

    @Override
    public String generateDetailedReport() {
        return "Detailed Report: Includes all transaction and profit/loss analysis.";
    }

    @Override
    public double calculateProfitOrLoss(double buyPrice, double sellPrice, int quantity) {
        return (sellPrice - buyPrice) * quantity;
    }

    @Override
    public void exportReportToCSV(String fileName) {
        System.out.println("Report exported to CSV: " + fileName);
    }

    @Override
    public void exportReportToPDF(String fileName) {
        System.out.println("Report exported to PDF: " + fileName);
    }

    @Override
    public double calculateAnnualReturns(double[] monthlyReturns) {
        double total = 0;
        for (double monthlyReturn : monthlyReturns) {
            total += monthlyReturn;
        }
        return total / 12;
    }

    public void logTransaction(String logEntry) {
        transactionLog.add(logEntry);
    }
}