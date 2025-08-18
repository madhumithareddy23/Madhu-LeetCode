import java.util.*;
class Solution {
    public boolean judgePoint24(int[] cards) {
        List<Double> nums = new ArrayList<>();
        for (int c : cards) nums.add((double) c);
        return dfs(nums);
    }

    private boolean dfs(List<Double> nums) {
        // Base case
        if (nums.size() == 1) {
            return Math.abs(nums.get(0) - 24) < 1e-6;
        }

        // Try all pairs
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) {
                if (i == j) continue;

                List<Double> next = new ArrayList<>();
                for (int k = 0; k < nums.size(); k++) {
                    if (k != i && k != j) next.add(nums.get(k));
                }

                // Try all operations
                for (double val : compute(nums.get(i), nums.get(j))) {
                    next.add(val);
                    if (dfs(next)) return true;
                    next.remove(next.size() - 1);
                }
            }
        }
        return false;
    }

    private List<Double> compute(double a, double b) {
        List<Double> res = new ArrayList<>();
        res.add(a + b);
        res.add(a - b);
        res.add(b - a);
        res.add(a * b);
        if (Math.abs(b) > 1e-6) res.add(a / b);
        if (Math.abs(a) > 1e-6) res.add(b / a);
        return res;
    }
}