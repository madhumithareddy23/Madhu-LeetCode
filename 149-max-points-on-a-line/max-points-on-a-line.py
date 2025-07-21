class Solution:
    def maxPoints(self, points: List[List[int]]) -> int:
        from typing import List
        from collections import defaultdict
        from math import gcd
        n = len(points)
        if n <= 2:
            return n
        
        max_points = 0

        for i in range(n):
            slopes = defaultdict(int)
            duplicate = 1  # Count the base point itself
            for j in range(n):
                if i == j:
                    continue
                x1, y1 = points[i]
                x2, y2 = points[j]

                dx = x2 - x1
                dy = y2 - y1

                if dx == 0 and dy == 0:
                    duplicate += 1
                else:
                    g = gcd(dx, dy)
                    # Normalize slope to avoid float precision issues
                    slope = (dy // g, dx // g)
                    # Ensure consistent slope representation (handle negative direction)
                    if slope[1] < 0:
                        slope = (-slope[0], -slope[1])
                    elif slope[1] == 0:
                        slope = (1, 0)
                    slopes[slope] += 1

            max_points = max(max_points, max(slopes.values(), default=0) + duplicate)

        return max_points
        