class Solution:
    def maximumLength(self, nums: List[int]) -> int:
        from typing import List
        even = []
        odd = []
        mixed = []
        
        # Build three types of subsequences
        for num in nums:
            if num % 2 == 0:
                even.append(num)
                mixed.append(num)
            else:
                odd.append(num)
                mixed.append(num)
        
        # Case 1: All same parity (even-even or odd-odd): parity of sum is even
        len1 = len(even)
        len2 = len(odd)
        same_parity_max = max(len1, len2)
        
        # Case 2: Alternating parity (even-odd or odd-even): parity of sum is odd
        # Take as many as possible while alternating
        prev = -1
        alt_len = 0
        for num in nums:
            if prev == -1:
                alt_len += 1
                prev = num % 2
            elif num % 2 != prev:
                alt_len += 1
                prev = num % 2
        
        return max(same_parity_max, alt_len)