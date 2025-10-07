import java.util.*;

class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] res = new int[n];
        Map<Integer, Integer> fullLakes = new HashMap<>(); // lake -> last day it rained
        TreeSet<Integer> dryDays = new TreeSet<>(); // indices of dry days
        
        for (int i = 0; i < n; i++) {
            int lake = rains[i];
            if (lake == 0) {
                // We can dry any lake on this day
                dryDays.add(i);
                res[i] = 1; // placeholder (can be changed later)
            } else {
                res[i] = -1; // raining day
                if (fullLakes.containsKey(lake)) {
                    // lake already full, find a dry day after last rain to dry it
                    Integer dryDay = dryDays.higher(fullLakes.get(lake));
                    if (dryDay == null) return new int[0]; // cannot prevent flood
                    res[dryDay] = lake; // dry this lake
                    dryDays.remove(dryDay);
                }
                fullLakes.put(lake, i);
            }
        }
        return res;
    }
}
