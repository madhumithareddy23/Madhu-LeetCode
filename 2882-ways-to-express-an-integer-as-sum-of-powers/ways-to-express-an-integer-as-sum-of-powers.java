import java.util.HashMap;
import java.util.Map;
class Solution {
    static final int MOD = 1_000_000_007;
    public int numberOfWays(int n, int x) {
        Map<String, Integer> memo = new HashMap<>();
        return countWays(n, x, 1, memo);
    }

    private int countWays(int n, int x, int curr, Map<String, Integer> memo) {
        int power = (int) Math.pow(curr, x);
        if (power > n) return 0;
        if (power == n) return 1;

        String key = n + "," + curr;
        if (memo.containsKey(key)) return memo.get(key);

        
        int include = countWays(n - power, x, curr + 1, memo);

        int exclude = countWays(n, x, curr + 1, memo);

        int total = (include + exclude) % MOD;
        memo.put(key, total);
        return total;
    }
}