class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            int currentPrice = prices[i];

            // Update maxProfit manually
            int profit = currentPrice - minPrice;
            if (profit > maxProfit) {
                maxProfit = profit;
            }

            // Update minPrice manually
            if (currentPrice < minPrice) {
                minPrice = currentPrice;
            }
        }
        return maxProfit;
    }
}