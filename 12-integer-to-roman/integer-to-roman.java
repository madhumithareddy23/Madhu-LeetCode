class Solution {
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder result = new StringBuilder();
        //Loop thru each symbol starting from target
        for(int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                num -= values[i];  //subtract the value
                result.append(symbols[i]); // Append the corresponding Roman symbol
            }
        }
        return result.toString();
    }
}