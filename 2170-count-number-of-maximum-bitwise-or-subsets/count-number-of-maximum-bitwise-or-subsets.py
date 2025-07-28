class Solution:
    def countMaxOrSubsets(self, nums: List[int]) -> int:
        self.max_or = 0
        self.count = 0

        # Step 1: Calculate the max OR possible
        for num in nums:
            self.max_or |= num  # OR all elements

        # Step 2: DFS to count subsets with that OR
        def dfs(index, current_or):
            if index == len(nums):
                if current_or == self.max_or:
                    self.count += 1
                return
            # Include nums[index]
            dfs(index + 1, current_or | nums[index])
            # Exclude nums[index]
            dfs(index + 1, current_or)

        dfs(0, 0)
        return self.count