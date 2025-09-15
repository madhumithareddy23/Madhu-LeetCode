class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        // Split the text into words
        String[] words = text.split(" ");
        
        // Convert brokenLetters into a set for quick lookup
        boolean[] broken = new boolean[26];
        for (char c : brokenLetters.toCharArray()) {
            broken[c - 'a'] = true;
        }
        
        int count = 0;
        
        // Check each word
        for (String word : words) {
            boolean canType = true;
            for (char c : word.toCharArray()) {
                if (broken[c - 'a']) {
                    canType = false;
                    break; // no need to check further, word cannot be typed
                }
            }
            if (canType) {
                count++;
            }
        }
        
        return count;
    }
}