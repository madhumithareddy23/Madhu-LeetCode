class Solution {
    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Step 1: Separate positive and negative numbers
        int[] positives = new int[n / 2];
        int[] negatives = new int[n / 2];
        int p = 0, q = 0;

        for (int num : nums) {
            if (num > 0) {
                positives[p++] = num;
            } else {
                negatives[q++] = num;
            }
        }

        // Step 2: Merge alternately starting with positive
        int i = 0, pos = 0, neg = 0;
        while (i < n) {
            result[i++] = positives[pos++];
            result[i++] = negatives[neg++];
        }

        return result;
    }
}