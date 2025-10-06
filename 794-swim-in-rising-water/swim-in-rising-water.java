import java.util.*;

class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        
        // Min-heap: stores [time, x, y]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{grid[0][0], 0, 0});
        
        int maxTime = 0;
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int t = curr[0], x = curr[1], y = curr[2];
            
            if (visited[x][y]) continue;
            visited[x][y] = true;
            
            maxTime = Math.max(maxTime, t);
            
            // If we reached destination
            if (x == n - 1 && y == n - 1) return maxTime;
            
            // Explore 4 directions
            for (int[] d : directions) {
                int nx = x + d[0];
                int ny = y + d[1];
                
                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]) {
                    pq.offer(new int[]{grid[nx][ny], nx, ny});
                }
            }
        }
        return -1; // should never reach here
    }
}
