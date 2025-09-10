import java.util.*;
class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;  // number of users
        List<Set<Integer>> userLang = new ArrayList<>();
        for (int[] lang : languages) {
            Set<Integer> set = new HashSet<>();
            for (int l : lang) set.add(l);
            userLang.add(set);
        }

        // Step 1: Find conflicting users (friendships that can't communicate)
        Set<Integer> conflictUsers = new HashSet<>();
        for (int[] f : friendships) {
            int u = f[0] - 1, v = f[1] - 1; // convert to 0-index
            Set<Integer> lu = userLang.get(u), lv = userLang.get(v);
            boolean ok = false;
            for (int l : lu) {
                if (lv.contains(l)) { ok = true; break; }
            }
            if (!ok) {
                conflictUsers.add(u);
                conflictUsers.add(v);
            }
        }
        if (conflictUsers.isEmpty()) return 0; // already fine

        // Step 2: For each language, count how many conflicting users already know it
        int ans = Integer.MAX_VALUE;
        for (int l = 1; l <= n; l++) {
            int count = 0;
            for (int u : conflictUsers) {
                if (userLang.get(u).contains(l)) count++;
            }
            ans = Math.min(ans, conflictUsers.size() - count);
        }
        return ans;
    }
}