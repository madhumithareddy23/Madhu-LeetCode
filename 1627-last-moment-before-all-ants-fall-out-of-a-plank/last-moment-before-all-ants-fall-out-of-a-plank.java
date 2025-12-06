class Solution {
    public int getLastMoment(int n, int[] left, int[] right) {
        int result = 0;

        for (int position : left) {
            if (position > result) {
                result = position;
            }
        }

        for (int position : right) {
            int time = n - position;
            if (time > result) {
                result = time;
            }
        }

        return result;
    }
}
