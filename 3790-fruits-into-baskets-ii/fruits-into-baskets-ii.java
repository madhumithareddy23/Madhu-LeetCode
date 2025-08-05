import java.util.Arrays;
class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        boolean[] used = new boolean[n]; // To track which baskets are already used
        int unplacedCount = 0;

        for (int i = 0; i < fruits.length; i++) {
            boolean placed = false;
            for (int j = 0; j < baskets.length; j++) {
                if (!used[j] && baskets[j] >= fruits[i]) {
                    used[j] = true; // Mark basket as used
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                unplacedCount++;
            }
        }

        return unplacedCount;
    }
}