/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root == null) return null;

        connectNodes(root.left, root.right);
        return root;
    }
    private void connectNodes(Node left, Node right) {
        if (left == null || right == null) return;

        // Connect left -> right
        left.next = right;

        // Connect children of the same parent
        connectNodes(left.left, left.right);
        connectNodes(right.left, right.right);

        // Connect across different parents
        connectNodes(left.right, right.left);
    }
}