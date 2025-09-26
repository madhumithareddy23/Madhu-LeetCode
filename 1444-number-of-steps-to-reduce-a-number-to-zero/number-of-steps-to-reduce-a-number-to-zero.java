class Solution {
    public int numberOfSteps(int num) {
        int steps = 0;
        while (num > 0) {
            if (num % 2 == 0) {
                num /= 2;  // even → divide by 2
            } else {
                num -= 1;  // odd → subtract 1
            }
            steps++;
        }
        return steps;
    }
}