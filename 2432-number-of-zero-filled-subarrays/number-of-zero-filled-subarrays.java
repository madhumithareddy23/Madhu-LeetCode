class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long count = 0;
        long currentZeros = 0;

        for (int num : nums) {
            if (num == 0) {
                currentZeros++;
                count += currentZeros;
            } else {
                currentZeros = 0;
            }
        }

        return count;
    }
}