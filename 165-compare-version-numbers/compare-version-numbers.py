class Solution:
    def compareVersion(self, version1: str, version2: str) -> int:
        v1 = list(map(int, version1.split(".")))
        v2 = list(map(int, version2.split(".")))
        
        # Make both lists the same length by padding with zeros
        max_len = max(len(v1), len(v2))
        v1.extend([0] * (max_len - len(v1)))
        v2.extend([0] * (max_len - len(v2)))
        
        # Compare revisions one by one
        for a, b in zip(v1, v2):
            if a < b:
                return -1
            elif a > b:
                return 1
        return 0
        