import java.util.*;

class Solution {
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length;
        int m = mana.length;
        
        // prefix sum of skills: prefixSkill[i] = sum of skill[0..i-1]
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + skill[i];
        }

        // st[j] = earliest “start time offset” for brewing potion j
        // That is, how much delay (shift) we need to add so that constraints hold
        long[] st = new long[m];
        st[0] = 0;  // for the first potion, no extra shift initially

        for (int j = 1; j < m; j++) {
            long offset = Long.MIN_VALUE;
            // we compute for each wizard i, the constraint needed
            for (int i = 0; i < n; i++) {
                // Time wizard i requires for potion j-1: prefix[i+1] * mana[j-1]
                long timePrev = prefix[i + 1] * (long) mana[j - 1];
                // Time wizard i requires for potion j: prefix[i] * mana[j]
                long timeCur = prefix[i] * (long) mana[j];
                
                // We need: st[j] + timeCur ≥ st[j-1] + timePrev
                // => st[j] ≥ st[j-1] + timePrev - timeCur
                long req = st[j - 1] + timePrev - timeCur;
                offset = Math.max(offset, req);
            }
            // st[j] must be at least that offset (or zero), since we can't “start before 0”
            st[j] = Math.max(offset, 0L);
        }

        // Finally, the finishing time is: st[m-1] + total work for the last potion
        return st[m - 1] + prefix[n] * (long) mana[m - 1];
    }
}
