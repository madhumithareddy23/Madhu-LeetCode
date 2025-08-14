class Solution {
    public int diagonalPrime(int[][] nums) {
        int n = nums.length;
        int maxPrime = 0;

        for (int i = 0; i < n; i++) {
            int val1 = nums[i][i]; // main diagonal
            int val2 = nums[i][n - i - 1]; // anti-diagonal

            if (isPrime(val1)) {
                maxPrime = Math.max(maxPrime, val1);
            }
            if (isPrime(val2)) {
                maxPrime = Math.max(maxPrime, val2);
            }
        }

        return maxPrime;
    }

    // Prime check helper
    private boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num == 2 || num == 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;

        // Check divisors up to sqrt(num)
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}