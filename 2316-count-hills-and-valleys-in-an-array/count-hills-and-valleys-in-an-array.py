class Solution:
    def countHillValley(self, nums: List[int]) -> int:
        count = 0
        n = len(nums)
        
        for i in range(1, n - 1):
            # Skip duplicate values to avoid counting the same hill/valley multiple times
            if nums[i] == nums[i - 1]:
                continue

            # Find closest non-equal left neighbor
            left = i - 1
            while left >= 0 and nums[left] == nums[i]:
                left -= 1

            # Find closest non-equal right neighbor
            right = i + 1
            while right < n and nums[right] == nums[i]:
                right += 1

            if left >= 0 and right < n:
                if nums[i] > nums[left] and nums[i] > nums[right]:
                    count += 1  # It's a hill
                elif nums[i] < nums[left] and nums[i] < nums[right]:
                    count += 1  # It's a valley

        return count
        