class Solution {
    public double soupServings(int n) {
        if (n >= 4800) return 1.0; // Optimization for large n

        int m = (n + 24) / 25; // Convert to "serving units"
        Double[][] memo = new Double[m + 1][m + 1];

        return dfs(m, m, memo);
    }

    private double dfs(int a, int b, Double[][] memo) {
        if (a <= 0 && b <= 0) return 0.5; // Both empty
        if (a <= 0) return 1.0; // A empty first
        if (b <= 0) return 0.0; // B empty first

        if (memo[a][b] != null) return memo[a][b];

        memo[a][b] = 0.25 * (
            dfs(a - 4, b, memo) +
            dfs(a - 3, b - 1, memo) +
            dfs(a - 2, b - 2, memo) +
            dfs(a - 1, b - 3, memo)
        );

        return memo[a][b];
    }
}