class Solution {
    public int calculate(String s) {
        Stack<Integer> stk = new Stack<>();
        int result = 0;
        int num = 0;
        int sign = 1;

        for(int i = 0;i < s.length();i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                num = 10*num + (ch-'0');
            }
            else if (ch == '+') {
                result += sign*num;
                sign = 1;
                num = 0;
            }
            else if (ch == '-') {
                result += sign*num;
                num = 0;
                sign = -1;
            }
            else if (ch == '(') {
                stk.push(result);
                stk.push(sign);
                result = 0;
                sign = 1;
                num = 0;
            }
            else if (ch == ')') {
                result += sign*num;
                result *= stk.pop();
                result += stk.pop();
                num = 0;
            }
        }
        return result += sign*num; 
    }
}