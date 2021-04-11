package com.example.codeinterview.chapter03;

// [leetcode] 108. 将有序数组转换为二叉搜索树
public class Code_03_13 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null) {
            return null;
        }
        return process(nums, 0, nums.length - 1);
    }
    private TreeNode process(int[] nums, int st, int en) {
        if(st > en) {
            return null;
        }
        int mid = st + ((en - st) >> 1);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = process(nums, st, mid - 1);
        root.right = process(nums, mid + 1, en);
        return root;
    }
}
