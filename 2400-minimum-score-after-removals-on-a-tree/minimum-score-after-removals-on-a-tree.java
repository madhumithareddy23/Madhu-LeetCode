import java.util.*;

class Solution {
    int[] in, out, xor;
    int time = 0;
    List<Integer>[] graph;

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        graph = new ArrayList[n];
        in = new int[n];
        out = new int[n];
        xor = new int[n];

        for (int i = 0; i < n; ++i) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        dfs(0, -1, nums);  // root is 0
        int totalXor = xor[0];
        int minScore = Integer.MAX_VALUE;

        // Try every pair of nodes representing the child side of the removed edge
        for (int i = 1; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int a = i, b = j;
                if (isAncestor(a, b)) {
                    int xorB = xor[b];
                    int xorA = xor[a] ^ xorB;
                    int xorRest = totalXor ^ xor[a];
                    minScore = Math.min(minScore, getScore(xorA, xorB, xorRest));
                } else if (isAncestor(b, a)) {
                    int xorA = xor[a];
                    int xorB = xor[b] ^ xorA;
                    int xorRest = totalXor ^ xor[b];
                    minScore = Math.min(minScore, getScore(xorA, xorB, xorRest));
                } else {
                    int xorA = xor[a], xorB = xor[b];
                    int xorRest = totalXor ^ xorA ^ xorB;
                    minScore = Math.min(minScore, getScore(xorA, xorB, xorRest));
                }
            }
        }

        return minScore;
    }

    private void dfs(int node, int parent, int[] nums) {
        in[node] = time++;
        xor[node] = nums[node];

        for (int nei : graph[node]) {
            if (nei == parent) continue;
            dfs(nei, node, nums);
            xor[node] ^= xor[nei];
        }

        out[node] = time++;
    }

    private boolean isAncestor(int u, int v) {
        return in[u] < in[v] && out[v] < out[u];
    }

    private int getScore(int a, int b, int c) {
        int max = Math.max(a, Math.max(b, c));
        int min = Math.min(a, Math.min(b, c));
        return max - min;
    }
}
