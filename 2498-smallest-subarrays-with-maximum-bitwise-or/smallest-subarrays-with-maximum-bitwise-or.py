from typing import List
class Solution:
    def smallestSubarrays(self, nums: List[int]) -> List[int]:
        n = len(nums)
        answer = [1] * n
        last_set_bit = [-1] * 32  # There are 32 bits in a 32-bit integer

        for i in range(n - 1, -1, -1):
            # Update last seen index for each bit in nums[i]
            for bit in range(32):
                if (nums[i] >> bit) & 1:
                    last_set_bit[bit] = i

            # Compute the furthest right index we need to go to get max OR
            max_pos = i
            for bit in range(32):
                if last_set_bit[bit] != -1:
                    max_pos = max(max_pos, last_set_bit[bit])

            answer[i] = max_pos - i + 1

        return answer
        