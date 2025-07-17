class Solution:
    def maximumLength(self, nums: List[int], k: int) -> int:
        n = len(nums)
        dp = [ [1]*k for _ in range(n) ]  # dp[i][mod] = longest valid subsequence ending at i with target mod
        max_len = 1

        for i in range(n):
            for j in range(i):
                mod = (nums[j] + nums[i]) % k
                dp[i][mod] = max(dp[i][mod], dp[j][mod] + 1)
                max_len = max(max_len, dp[i][mod])

        return max_len
