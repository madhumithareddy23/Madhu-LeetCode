class Solution {
    public boolean isAnagram(String s, String t) {
        // Early exit if length are not equal
        if (s.length() != t.length()) return false;
        Map<Character, Integer> map = new HashMap<>();
        
        //Count Freq of each char in s
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c,0) + 1);
        }

        // Decrease freq based on char in t
        for (char c : t.toCharArray()) {
            if (!map.containsKey(c)) return false;
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0) map.remove(c);
        }
        return map.isEmpty(); // All characters matched
    }
}