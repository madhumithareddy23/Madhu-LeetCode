class Solution {
    public int maxProduct(int[] nums) {
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        int minEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];

            // Swap if negative (because it flips max and min)
            if (curr < 0) {
                int temp = maxEndingHere;
                maxEndingHere = minEndingHere;
                minEndingHere = temp;
            }

            // Update max/min ending here
            maxEndingHere = Math.max(curr, maxEndingHere * curr);
            minEndingHere = Math.min(curr, minEndingHere * curr);

            // Update overall max
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }
}