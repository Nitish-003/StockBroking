package org.nitish;

interface AdvancedReport extends Report {
    void exportReportToCSV(String fileName);
    void exportReportToPDF(String fileName);
    double calculateAnnualReturns(double[] monthlyReturns);
}
