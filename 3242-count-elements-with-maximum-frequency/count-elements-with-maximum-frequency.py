from collections import Counter
from typing import List

class Solution:
    def maxFrequencyElements(self, nums: List[int]) -> int:
        freq = Counter(nums)                   # step 1: count frequencies
        max_freq = max(freq.values())          # step 2: find maximum frequency
        return sum(v for v in freq.values() if v == max_freq)  # step 3
