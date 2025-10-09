class Solution {
    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int maxEnergy = Integer.MIN_VALUE;

        // Start from the last index and move backward in steps of k
        // dp[i] = energy[i] + dp[i + k] (if i + k < n)
        for (int i = n - 1; i >= 0; i--) {
            if (i + k < n) {
                energy[i] += energy[i + k];
            }
            maxEnergy = Math.max(maxEnergy, energy[i]);
        }

        return maxEnergy;
    }
}