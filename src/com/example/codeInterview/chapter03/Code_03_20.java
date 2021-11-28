package com.example.codeInterview.chapter03;

// [leetcode] 222. 完全二叉树的节点个数
public class Code_03_20 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int countNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return process(root, 1, mostLeftLevel(root, 1));
    }
    private int process(TreeNode root, int L, int H) {
        if(L == H) {
            return 1;
        }
        // 右孩子能到达最底层，说明左子树是满二叉树（找个完全二叉树实例图一看就明白）
        if((mostLeftLevel(root.right, L + 1)) == H) {
            return (1 << (H - L)) + process(root.right, L + 1, H);
        } else {
            // 右孩子不能到最底层，说明右孩子是满二叉树（找个完全二叉树实例图一看就明白）
            return (1 << (H - 1 - L)) + process(root.left, L + 1, H);
        }
    }

    /**
     * 当前节点root，所处第L层，得到能到达的最深层
     *
     * @param root
     * @param L
     * @return
     */
    private int mostLeftLevel(TreeNode root, int L) {
        while (root != null) {
            L++;
            root = root.left;
        }
        return L - 1;
    }
}
