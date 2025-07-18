class Solution:
    def minimumDifference(self, nums: List[int]) -> int:
        from heapq import heappush, heappop
        from typing import List
        n = len(nums) // 3
        total = len(nums)

        # Step 1: prefix sums - maintain n largest values from the first 2n elements
        left_heap = []
        left_sums = [0] * total
        left_sum = 0
        for i in range(2 * n):
            heappush(left_heap, -nums[i])  # Max-heap via negation
            left_sum += nums[i]
            if len(left_heap) > n:
                left_sum += heappop(left_heap)  # pop returns negative value
            if len(left_heap) == n:
                left_sums[i] = left_sum

        # Step 2: suffix sums - maintain n smallest values from the last 2n elements
        right_heap = []
        right_sums = [0] * total
        right_sum = 0
        for i in range(total - 1, n - 1, -1):
            heappush(right_heap, nums[i])  # Min-heap
            right_sum += nums[i]
            if len(right_heap) > n:
                right_sum -= heappop(right_heap)
            if len(right_heap) == n:
                right_sums[i] = right_sum

        # Step 3: minimize the difference between left_sums and right_sums
        res = float('inf')
        for i in range(n - 1, 2 * n):
            res = min(res, left_sums[i] - right_sums[i + 1])

        return res
 