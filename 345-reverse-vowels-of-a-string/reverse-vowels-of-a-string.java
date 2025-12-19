class Solution {
    public String reverseVowels(String s) {
        char[] a = s.toCharArray();
        int i = 0, j = a.length - 1;
        String v = "aeiouAEIOU";

        while (i < j) {
            if (v.indexOf(a[i]) == -1) i++;
            else if (v.indexOf(a[j]) == -1) j--;
            else {
                char t = a[i];
                a[i++] = a[j];
                a[j--] = t;
            }
        }
        return new String(a);
    }
}