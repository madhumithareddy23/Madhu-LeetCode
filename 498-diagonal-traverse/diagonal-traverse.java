class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0) return new int[0];

        int m = mat.length;
        int n = mat[0].length;
        int[] result = new int[m * n];

        int row = 0, col = 0;
        for (int i = 0; i < m * n; i++) {
            result[i] = mat[row][col];

            // Moving up-right
            if ((row + col) % 2 == 0) {
                if (col == n - 1) { // last column → move down
                    row++;
                } else if (row == 0) { // first row → move right
                    col++;
                } else { // normal up-right move
                    row--;
                    col++;
                }
            } 
            // Moving down-left
            else {
                if (row == m - 1) { // last row → move right
                    col++;
                } else if (col == 0) { // first column → move down
                    row++;
                } else { // normal down-left move
                    row++;
                    col--;
                }
            }
        }
        return result;
    }
}