import java.util.*;

class Solution {
    private Long[] memo;
    private int[] powerArr;
    private Map<Integer, Integer> cnt;
    private int[] nxt;
    private int n;

    public long maximumTotalDamage(int[] power) {
        Arrays.sort(power);
        this.powerArr = power;
        this.n = power.length;
        this.memo = new Long[n];
        this.cnt = new HashMap<>();
        this.nxt = new int[n];

        // Count frequencies and compute next valid index
        for (int i = 0; i < n; i++) {
            cnt.merge(power[i], 1, Integer::sum);
        }
        for (int i = 0; i < n; i++) {
            long target = (long) power[i] + 3;  // we need > power[i] + 2
            int idx = Arrays.binarySearch(power, (int) target);
            if (idx < 0) {
                idx = -idx - 1;
            }
            nxt[i] = idx;
        }

        return dfs(0);
    }

    private long dfs(int i) {
        if (i >= n) return 0;
        if (memo[i] != null) return memo[i];

        // Option 1: skip all spells of this damage
        int c = cnt.get(powerArr[i]);
        long skip = dfs(i + c);

        // Option 2: take all spells of this damage
        long take = (long) powerArr[i] * c + dfs(nxt[i]);

        long res = Math.max(skip, take);
        memo[i] = res;
        return res;
    }
}
