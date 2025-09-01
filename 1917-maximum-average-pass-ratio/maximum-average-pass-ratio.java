import java.util.PriorityQueue;
class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        // max-heap: store {gain, pass, total}
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));
        
        // compute initial gain for each class
        for (int[] c : classes) {
            int pass = c[0], total = c[1];
            double gain = getGain(pass, total);
            pq.offer(new double[]{gain, pass, total});
        }
        
        // assign each extra student
        for (int i = 0; i < extraStudents; i++) {
            double[] top = pq.poll();
            int pass = (int) top[1];
            int total = (int) top[2];
            
            // add one student
            pass++;
            total++;
            
            // recalc gain and push back
            pq.offer(new double[]{getGain(pass, total), pass, total});
        }
        
        // compute final average
        double sum = 0.0;
        while (!pq.isEmpty()) {
            double[] cur = pq.poll();
            sum += cur[1] / cur[2]; // pass / total
        }
        
        return sum / classes.length;
    }
    
    // Helper to compute marginal gain of adding one student
    private double getGain(int pass, int total) {
        return ((double)(pass + 1) / (total + 1)) - ((double) pass / total);
    }
}