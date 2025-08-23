import java.util.*;
class Solution {
    private int n, m;
    private int[][] grid;
    private int[][] ps;       // 2D prefix sum of ones
    private int[][] rowPref;  // rowPref[i][j+1] = ones in row i up to col j
    private int[][] colPref;  // colPref[j][i+1] = ones in col j up to row i
    private Map<Long, Integer> memo = new HashMap<>();
    private static final int INF = 1_000_000_000;
    public int minimumSum(int[][] grid) {
        this.grid = grid;
        n = grid.length;
        m = grid[0].length;
        buildPrefixes();
        memo.clear();
        return dp(0, n - 1, 0, m - 1, 3);
    }

    // Build 2D + row/col prefixes
    private void buildPrefixes() {
        ps = new int[n + 1][m + 1];
        rowPref = new int[n][m + 1];
        colPref = new int[m][n + 1];

        for (int i = 0; i < n; i++) {
            int rowsum = 0;
            for (int j = 0; j < m; j++) {
                rowsum += grid[i][j];
                rowPref[i][j + 1] = rowsum;

                ps[i + 1][j + 1] = ps[i + 1][j] + ps[i][j + 1] - ps[i][j] + grid[i][j];
                colPref[j][i + 1] = colPref[j][i] + grid[i][j];
            }
        }
    }

    // Count ones in subgrid [r1..r2] x [c1..c2]
    private int ones(int r1, int r2, int c1, int c2) {
        if (r1 > r2 || c1 > c2) return 0;
        return ps[r2 + 1][c2 + 1] - ps[r1][c2 + 1] - ps[r2 + 1][c1] + ps[r1][c1];
    }

    private int rowCount(int i, int c1, int c2) {
        return rowPref[i][c2 + 1] - rowPref[i][c1];
    }

    private int colCount(int j, int r1, int r2) {
        return colPref[j][r2 + 1] - colPref[j][r1];
    }

    // Bounding box area of ones inside subgrid (0 if none)
    private int bboxArea(int r1, int r2, int c1, int c2) {
        if (ones(r1, r2, c1, c2) == 0) return 0;

        int minR = r1, maxR = r2;
        while (minR <= r2 && rowCount(minR, c1, c2) == 0) minR++;
        while (maxR >= r1 && rowCount(maxR, c1, c2) == 0) maxR--;

        int minC = c1, maxC = c2;
        while (minC <= c2 && colCount(minC, r1, r2) == 0) minC++;
        while (maxC >= c1 && colCount(maxC, r1, r2) == 0) maxC--;

        return (maxR - minR + 1) * (maxC - minC + 1);
    }

    private long key(int r1, int r2, int c1, int c2, int k) {
        // pack into base-31 (n,m <= 30, k <= 3)
        long res = r1;
        res = res * 31 + r2;
        res = res * 31 + c1;
        res = res * 31 + c2;
        res = res * 5 + k;   // small extra base for safety
        return res;
    }

    // dp(subgrid, up to k rectangles)
    private int dp(int r1, int r2, int c1, int c2, int k) {
        int cnt = ones(r1, r2, c1, c2);
        if (cnt == 0) return 0;       // nothing to cover
        if (k == 0) return INF;       // need rectangles but none allowed
        if (k == 1) return bboxArea(r1, r2, c1, c2);

        long K = key(r1, r2, c1, c2, k);
        Integer memoVal = memo.get(K);
        if (memoVal != null) return memoVal;

        int best = dp(r1, r2, c1, c2, k - 1); // you can always use fewer rectangles

        // vertical splits
        for (int c = c1; c < c2; c++) {
            int leftCnt = ones(r1, r2, c1, c);
            int rightCnt = cnt - leftCnt;

            if (leftCnt == 0) {
                best = Math.min(best, dp(r1, r2, c + 1, c2, k));
                continue;
            }
            if (rightCnt == 0) {
                best = Math.min(best, dp(r1, r2, c1, c, k));
                continue;
            }
            // both sides non-empty -> must give at least 1 rect to each
            for (int i = 1; i <= k - 1; i++) {
                int a = dp(r1, r2, c1, c, i);
                if (a >= INF) continue;
                int b = dp(r1, r2, c + 1, c2, k - i);
                if (b >= INF) continue;
                best = Math.min(best, a + b);
            }
        }

        // horizontal splits
        for (int r = r1; r < r2; r++) {
            int topCnt = ones(r1, r, c1, c2);
            int botCnt = cnt - topCnt;

            if (topCnt == 0) {
                best = Math.min(best, dp(r + 1, r2, c1, c2, k));
                continue;
            }
            if (botCnt == 0) {
                best = Math.min(best, dp(r1, r, c1, c2, k));
                continue;
            }
            for (int i = 1; i <= k - 1; i++) {
                int a = dp(r1, r, c1, c2, i);
                if (a >= INF) continue;
                int b = dp(r + 1, r2, c1, c2, k - i);
                if (b >= INF) continue;
                best = Math.min(best, a + b);
            }
        }

        memo.put(K, best);
        return best;
    }
}