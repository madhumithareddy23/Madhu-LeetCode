class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        int maxScore = 0, currentSum = 0;
        int start = 0;

        for (int end = 0; end < nums.length; end++) {
            while (seen.contains(nums[end])) {
                seen.remove(nums[start]);
                currentSum -= nums[start];
                start++;
            }
            seen.add(nums[end]);
            currentSum += nums[end];
            maxScore = Math.max(maxScore, currentSum);
        }

        return maxScore;
    }
}