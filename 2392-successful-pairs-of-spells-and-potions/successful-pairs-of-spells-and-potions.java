import java.util.*;

class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = spells.length;
        int m = potions.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            long spell = spells[i];
            int left = 0, right = m - 1;
            int firstValid = m; // index of the first potion that meets the success condition

            // Binary search for smallest index where spell * potion >= success
            while (left <= right) {
                int mid = left + (right - left) / 2;
                long product = spell * (long) potions[mid];
                if (product >= success) {
                    firstValid = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            result[i] = m - firstValid; // count of valid potions
        }

        return result;
    }
}
