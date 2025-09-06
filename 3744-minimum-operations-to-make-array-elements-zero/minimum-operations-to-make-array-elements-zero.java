class Solution {
    // Prefix sum of steps from 1..n (steps(n): times dividing by 4 to reach 0)
    private long prefix(long n) {
        if (n <= 0) return 0L;
        long sum = 0L;
        long step = 1L;
        long start = 1L;              // block starts: 1, 4, 16, ...
        while (start <= n) {
            long end = Math.min(n, start * 4L - 1L);  // block ends: 3, 15, 63, ...
            long count = end - start + 1L;
            sum += count * step;
            start *= 4L;
            step++;
        }
        return sum;
    }

    public long minOperations(int[][] queries) {
        long total = 0L;
        for (int[] q : queries) {
            long l = q[0];
            long r = q[1];
            long sumSteps = prefix(r) - prefix(l - 1);
            // ceil(sumSteps / 2)
            total += (sumSteps + 1) / 2;
        }
        return total;
    }
}
