/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //IF both are null both are same
        if (p==null && q==null) {
            return true;
        }
        //if one is null but not the other, trees are not same
        if (p==null || q==null) {
            return false;
        }
        //check current node values and recurse on children
        return (p.val==q.val)
            && isSameTree(p.left, q.left)
            && isSameTree(p.right, q.right);
    }
}