class Solution {
    public boolean doesAliceWin(String s) {
        for (char c : s.toCharArray()) {
            if (isVowel(c)) {
                return true; // Alice can always move if there's a vowel
            }
        }
        return false; // no vowels -> Alice cannot move
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}