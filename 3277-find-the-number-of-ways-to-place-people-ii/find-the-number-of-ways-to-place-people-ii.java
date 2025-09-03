import java.util.*;

class Solution {
    public int numberOfPairs(int[][] points) {
        int n = points.length;
        int count = 0;
        Arrays.sort(points, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return b[1] - a[1];
        });
        for (int i = 0; i < n; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; j++) {
                int x2 = points[j][0], y2 = points[j][1];
                if (x1 <= x2 && y1 >= y2) {
                    boolean valid = true;
                    for (int k = i + 1; k < j; k++) {
                        int x = points[k][0], y = points[k][1];
                        if (x >= x1 && x <= x2 && y <= y1 && y >= y2) {
                            valid = false;
                            break;
                        }
                    }

                    if (valid) count++;
                }
            }
        }

        return count;
    }
}