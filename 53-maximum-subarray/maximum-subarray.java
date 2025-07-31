class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0]; // Initialize with the first element
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Choose to extend the subarray or start new
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}