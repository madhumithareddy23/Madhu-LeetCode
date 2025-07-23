class Solution:
    def maximumGain(self, s: str, x: int, y: int) -> int:
        def remove_pair(s: str, first: str, second: str, score: int):
            stack = []
            points = 0
            for ch in s:
                if stack and stack[-1] == first and ch == second:
                    stack.pop()
                    points += score
                else:
                    stack.append(ch)
            return "".join(stack), points
        
        total = 0
        if x > y:
            # Remove "ab" first
            s, gain = remove_pair(s, 'a', 'b', x)
            total += gain
            s, gain = remove_pair(s, 'b', 'a', y)
            total += gain
        else:
            # Remove "ba" first
            s, gain = remove_pair(s, 'b', 'a', y)
            total += gain
            s, gain = remove_pair(s, 'a', 'b', x)
            total += gain
        
        return total
        