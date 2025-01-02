package org.nitish;

interface Report {
    String generateSummaryReport();
    String generateDetailedReport();
    double calculateProfitOrLoss(double buyPrice, double sellPrice, int quantity);
}
