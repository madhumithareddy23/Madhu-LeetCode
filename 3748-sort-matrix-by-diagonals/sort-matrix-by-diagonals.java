class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;

        // Process bottom-left diagonals (including main diagonal)
        for (int i = 0; i < n; i++) {
            sortDiagonal(grid, i, 0, false); // from (i,0), descending
        }

        // Process top-right diagonals
        for (int j = 1; j < n; j++) {
            sortDiagonal(grid, 0, j, true);  // from (0,j), ascending
        }

        return grid;
    }

    // Helper function to extract, sort, and put back diagonal
    private void sortDiagonal(int[][] grid, int r, int c, boolean ascending) {
        int n = grid.length;
        int i = r, j = c;

        // Collect diagonal elements
        java.util.List<Integer> diag = new java.util.ArrayList<>();
        while (i < n && j < n) {
            diag.add(grid[i][j]);
            i++;
            j++;
        }

        // Sort based on triangle
        if (ascending) {
            java.util.Collections.sort(diag); // non-decreasing
        } else {
            java.util.Collections.sort(diag, java.util.Collections.reverseOrder()); // non-increasing
        }

        // Put back sorted diagonal
        i = r;
        j = c;
        int k = 0;
        while (i < n && j < n) {
            grid[i][j] = diag.get(k++);
            i++;
            j++;
        }
    }
}
