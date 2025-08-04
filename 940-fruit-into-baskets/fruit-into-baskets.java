import java.util.HashMap;
class Solution {
    public int totalFruit(int[] fruits) {
        // Map to track fruit type and count in the current window
        HashMap<Integer, Integer> basket = new HashMap<>();
        int maxFruits = 0;
        int left = 0;

        // Sliding window
        for (int right = 0; right < fruits.length; right++) {
            int fruit = fruits[right];
            basket.put(fruit, basket.getOrDefault(fruit, 0) + 1);

            // If more than two types, shrink window from the left
            while (basket.size() > 2) {
                int leftFruit = fruits[left];
                basket.put(leftFruit, basket.get(leftFruit) - 1);
                if (basket.get(leftFruit) == 0) {
                    basket.remove(leftFruit);
                }
                left++;
            }

            // Update maximum fruits collected
            maxFruits = Math.max(maxFruits, right - left + 1);
        }

        return maxFruits;
    }
}