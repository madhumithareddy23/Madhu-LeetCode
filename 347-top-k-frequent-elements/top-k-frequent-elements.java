class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] arr = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums)map.put(i, map.getOrDefault(i, 0) + 1);
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (buckets[freq] == null) buckets[freq] = new ArrayList<>();
            buckets[freq].add(key);
        }
        int c = 0;
        for(int i = buckets.length - 1; i >= 0 && c < k; i--) {
            if(buckets[i] == null) continue;
            for(int val : buckets[i]) {
                if (c == k) break;
                arr[c++] = val;
            }
        }
        return arr;
    }
}