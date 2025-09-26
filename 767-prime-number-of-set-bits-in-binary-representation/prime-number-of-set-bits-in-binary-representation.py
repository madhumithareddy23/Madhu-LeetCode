class Solution:
    def countPrimeSetBits(self, left: int, right: int) -> int:
        # Primes up to 20 (since max set bits in 10^6 is <= 20)
        primes = {2, 3, 5, 7, 11, 13, 17, 19}
        
        def count_bits(x):
            return bin(x).count("1")
        
        count = 0
        for num in range(left, right + 1):
            if count_bits(num) in primes:
                count += 1
        
        return count