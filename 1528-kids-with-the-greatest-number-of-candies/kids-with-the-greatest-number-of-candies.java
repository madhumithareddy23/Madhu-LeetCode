import java.util.*;

class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> result = new ArrayList<>();

        // Step 1: Find the maximum number of candies
        int max = 0;
        for (int candy : candies) {
            if (candy > max) {
                max = candy;
            }
        }

        // Step 2: Check for each kid
        for (int candy : candies) {
            result.add(candy + extraCandies >= max);
        }

        return result;
    }
}
