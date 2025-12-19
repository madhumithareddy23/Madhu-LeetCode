class Solution {
    public String gcdOfStrings(String str1, String str2) {

        // If concatenations are not equal, no GCD string exists
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        // Find GCD of lengths
        int gcdLength = gcd(str1.length(), str2.length());

        // Return substring of that length
        return str1.substring(0, gcdLength);
    }

    // Helper method to find GCD of two numbers
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
