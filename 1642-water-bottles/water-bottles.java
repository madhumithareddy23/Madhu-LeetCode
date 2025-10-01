class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int totalDrank = 0;
        int empty = 0;

        // Drink until you cannot exchange anymore
        while (numBottles > 0) {
            totalDrank += numBottles;   // drink all current bottles
            empty += numBottles;        // now they become empty
            numBottles = empty / numExchange; // exchange for new full bottles
            empty = empty % numExchange;      // leftover empty bottles
        }

        return totalDrank;
    }
}
