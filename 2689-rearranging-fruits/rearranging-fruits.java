import java.util.*;
class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> freq = new HashMap<>();
        TreeSet<Integer> allFruits = new TreeSet<>();

        for (int fruit : basket1) {
            freq.put(fruit, freq.getOrDefault(fruit, 0) + 1);
            allFruits.add(fruit);
        }

        for (int fruit : basket2) {
            freq.put(fruit, freq.getOrDefault(fruit, 0) - 1);
            allFruits.add(fruit);
        }

        List<Integer> toSwap = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int fruit = entry.getKey();
            int count = entry.getValue();

            if (count % 2 != 0) return -1;

            for (int i = 0; i < Math.abs(count) / 2; i++) {
                toSwap.add(fruit);
            }
        }

        Collections.sort(toSwap);

        long minFruit = allFruits.first(); // smallest fruit in both baskets
        long cost = 0;
        int n = toSwap.size();
        for (int i = 0; i < n / 2; i++) {
            cost += Math.min(toSwap.get(i), 2 * minFruit);
        }

        return cost;
    }
}