package com.example.leetcode;

/**
 * 【从中序与后序遍历序列构造二叉树】
 */
public class Leetcode_106 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 关键在于数学公式推导，关键在于所有数不重复，关键一点是后序最后一个是根，有根就确定了
     * 中序的根位置，确定左子树和右子树部分，关键一点是中序和后序的左右子树总是一样的啊。。
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return f(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }

    private TreeNode f(int[] inOrder, int s1, int t1, int[] postOrder, int s2, int t2) {
        if(s1 > t1 || s2 > t2) {
            return null;
        }
        TreeNode root = new TreeNode(postOrder[t2]);
        int rootInOrderLoc = -1;
        for(int i = s1; i <= t1; i++) {
            if(inOrder[i] == postOrder[t2]) {
                rootInOrderLoc = i;
                break;
            }
        }
        root.left = f(inOrder, s1, rootInOrderLoc, postOrder, s2, rootInOrderLoc-s1+s2-1);
        root.right = f(inOrder, rootInOrderLoc+1, t1, postOrder, rootInOrderLoc-s1+s2, t2-1);
        return root;
    }
}
