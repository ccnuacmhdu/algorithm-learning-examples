package com.example.leetcodeTmp;

/**
 * 【监控二叉树】
 */
public class Leetcode_968 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 二叉树套路题（能够把流程定义成检查每个节点为头的子树是否符合同一标准并且答案在其中）
     *
     * @param root
     * @return
     */
    public int minCameraCover(TreeNode root) {
        ReturnType returnType = process(root);
        return Math.min(returnType.a, returnType.b);
    }
    /**
     * 对于当前每个节点来说，都存在三种状态，下面三种状态分别对应需要的最少摄像头数命名为 a，b，c。
     * 1. 安装有摄像头（必定能被监控到，被自己监控）
     * 2. 没安装摄像头，能被监控到（左右孩子之一必定安装了摄像头）
     * 3. 没安装摄像头，不能被监控到（左右孩子必定都没安装摄像头）
     *
     * 递归，左右孩子返回最小值，再看看父亲是否安装来决定是否加 1，其实就是最后返回的 min(a, b)
     *
     * 对于情况 1，父亲安装了摄像头，两个孩子必定都能被监控到，所以孩子们取三种情况最小值即可
     * 对于情况 2，父亲没安装摄像头，要保证被监控到，那么孩子中至少有一个安装了摄像头，且要保证另一个孩子被监控到
     * 对于情况 3，父亲没安装摄像头，也要保证不被监控到，那么孩子们必须不安装摄像头且被监控到
     *
     * 边界处理，当是空树怎么办？遇到叶子节点递归回退返回什么？可以考虑只有一个节点的树，该节点
     * 左右两个空孩子返回的值肯定一样，并且最终 min(a, b) = 1
     **/
    private class ReturnType {
        public int a;
        public int b;
        public int c;

        public ReturnType(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    private ReturnType process(TreeNode root) {
        if(root == null) {
            // 由于有 a+a, a+b 的情况，故为了不超过 int 最大值，进行除以 2 处理。
            // 考虑递归回退出口及空树及单节点树，c 初始值只要满足 c >= min(a,b) 即可，
            // 不让 c 起作用，取得 min(a,b)，而 root=null 时，保证 min(a,b)=0，
            // 考虑单节点树，若 a=0, b=MAX/2，从左右空孩子返回 (1,0,MAX)，得到 min(a,b)=0，错误
            // 而 a=MAX/2,b=0，从左右孩子返回 (1,MAX/2,0)，min(a,b)=1，正确。推知下面这行代码
            return new ReturnType(Integer.MAX_VALUE/2, 0, 0);
        }
        ReturnType left = process(root.left);
        ReturnType right = process(root.right);
        int a = Math.min(left.c, Math.min(left.a, left.b)) + Math.min(right.c, Math.min(right.a, right.b)) + 1;
        int b = Math.min(left.a + right.a, Math.min(left.a + right.b, left.b + right.a));
        int c = left.b + right.b;
        return new ReturnType(a, b, c);
    }
}
