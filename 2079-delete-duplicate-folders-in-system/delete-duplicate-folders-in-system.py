from typing import List
from collections import defaultdict

class TrieNode:
    def __init__(self):
        self.children = dict()
        self.name = ""
        self.serial = ""
        self.deleted = False
class Solution:
    def deleteDuplicateFolder(self, paths: List[List[str]]) -> List[List[str]]:
        root = TrieNode()

        # Step 1: Build the Trie
        for path in paths:
            node = root
            for folder in path:
                if folder not in node.children:
                    node.children[folder] = TrieNode()
                    node.children[folder].name = folder
                node = node.children[folder]

        # Step 2: Serialize subtrees
        serial_count = defaultdict(int)

        def serialize(node: TrieNode) -> str:
            if not node.children:
                node.serial = ""
                return node.serial

            serials = []
            for child in sorted(node.children.values(), key=lambda x: x.name):
                child_serial = serialize(child)
                serials.append(f"{child.name}({child_serial})")
            node.serial = ''.join(serials)
            serial_count[node.serial] += 1
            return node.serial

        serialize(root)

        # Step 3: Mark duplicates
        def mark_deletions(node: TrieNode):
            if node.serial and serial_count[node.serial] > 1:
                node.deleted = True
            for child in node.children.values():
                mark_deletions(child)

        mark_deletions(root)

        # Step 4: Collect remaining paths
        result = []

        def collect(node: TrieNode, path: List[str]):
            for name, child in node.children.items():
                if not child.deleted:
                    new_path = path + [name]
                    result.append(new_path)
                    collect(child, new_path)

        collect(root, [])
        return result
        