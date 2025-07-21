class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;

        while (i >= 0 || j >= 0 || carry != 0) {
            int bitA = i >= 0 ? a.charAt(i--) - '0' : 0;
            int bitB = j >= 0 ? b.charAt(j--) - '0' : 0;

            int sum = bitA + bitB + carry;
            sb.append(sum % 2);     // append current bit
            carry = sum / 2;        // update carry
        }

        return sb.reverse().toString();
    }
}