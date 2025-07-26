class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int value : nums)
        numSet.add(value);
        int lcs = 0;
        for(int value : numSet) {
            if (numSet.contains(value - 1))
            continue;
            else{
                int currNum = value;
                int currSub = 1;
                while (numSet.contains(currNum + 1)) {
                    currNum++;
                    currSub++;
                }
                lcs = Math.max(lcs, currSub);
            }
        }
        return lcs;
    }
}