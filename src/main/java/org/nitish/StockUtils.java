package org.nitish;

class StockUtils {
    static double calculateMovingAverage(double[] prices) {
        double sum = 0;
        for (double price : prices) {
            sum += price;
        }
        return sum / prices.length;
    }

    static double calculateRSI(double[] priceChanges) {
        double gains = 0, losses = 0;
        for (double change : priceChanges) {
            if (change > 0) gains += change;
            else losses -= change;
        }
        double avgGain = gains / priceChanges.length;
        double avgLoss = losses / priceChanges.length;
        return 100 - (100 / (1 + avgGain / avgLoss));
    }
}
