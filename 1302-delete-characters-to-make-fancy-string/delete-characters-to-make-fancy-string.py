class Solution:
    def makeFancyString(self, s: str) -> str:
        if len(s) < 3:
            return s

        result = [s[0], s[1]]  # Start with the first two characters

        for i in range(2, len(s)):
            # Only add s[i] if the last two characters in result are not equal to s[i]
            if not (s[i] == result[-1] and s[i] == result[-2]):
                result.append(s[i])

        return ''.join(result)
        