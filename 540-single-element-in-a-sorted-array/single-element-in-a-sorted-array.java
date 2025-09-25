class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // Ensure mid is even (pointing at the first element of a pair)
            if (mid % 2 == 1) {
                mid--;
            }
            
            // If pair is valid, single element is on the right
            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else {
                // Otherwise, single element is on the left (or at mid)
                right = mid;
            }
        }
        
        return nums[left];
    }
}