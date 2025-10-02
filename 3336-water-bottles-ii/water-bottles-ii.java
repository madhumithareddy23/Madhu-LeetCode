class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int totalDrank = 0;
        int empty = 0;

        // Drink initial bottles
        totalDrank += numBottles;
        empty += numBottles;

        // Keep exchanging while possible
        while (empty >= numExchange) {
            empty -= numExchange;  // use empties for exchange
            numExchange++;         // harder for next time
            totalDrank++;          // drink the new bottle
            empty++;               // new bottle becomes empty
        }

        return totalDrank;
    }
}
