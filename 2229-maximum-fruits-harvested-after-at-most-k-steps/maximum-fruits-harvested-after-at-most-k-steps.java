class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int maxFruits = 0;
        int left = 0, sum = 0;

        // Sliding window from left to right
        for (int right = 0; right < n; right++) {
            sum += fruits[right][1];

            // Move left pointer to maintain the max distance constraint
            while (left <= right && !canReach(fruits[left][0], fruits[right][0], startPos, k)) {
                sum -= fruits[left][1];
                left++;
            }

            maxFruits = Math.max(maxFruits, sum);
        }

        return maxFruits;
    }

    // Check if the interval [left, right] can be reached within k steps
    private boolean canReach(int left, int right, int start, int k) {
        int toLeft = Math.abs(start - left) + (right - left);  // go to left first, then to right
        int toRight = Math.abs(start - right) + (right - left); // go to right first, then to left
        return Math.min(toLeft, toRight) <= k;
    }
}