class Solution {
    public int compress(char[] chars) {
        int index = 0; // write pointer
        int i = 0;     // read pointer
        
        while (i < chars.length) {
            char currentChar = chars[i];
            int count = 0;
            
            // Count consecutive same characters
            while (i < chars.length && chars[i] == currentChar) {
                i++;
                count++;
            }
            
            // Write the character
            chars[index++] = currentChar;
            
            // Write the count if greater than 1
            if (count > 1) {
                String countStr = String.valueOf(count);
                for (char c : countStr.toCharArray()) {
                    chars[index++] = c;
                }
            }
        }
        
        return index;
    }
}