package com.example.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_337 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }
    }


    public int rob(TreeNode root) {
        int[] ans = process(root);
        return Math.max(ans[0], ans[1]);
    }

    // 树形 DP，记录偷或不偷的最大收益
    public int[] process(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] leftData = process(root.left);
        int[] rightData = process(root.right);
        int[] ans = {
                Math.max(leftData[0], leftData[1]) + Math.max(rightData[0], rightData[1]),
                root.val + leftData[0] + rightData[0]};
        return ans;
    }

    // 记忆化搜索
//    public int rob(TreeNode root) {
//        return rob(root, true, new HashMap<String, Integer>());
//    }
//    // 记忆化搜索，要么打劫某节点，要么打劫某节点的两个孩子
//    public int rob(TreeNode root, boolean willRob, Map<String, Integer> map) {
//        if(root == null) {
//            return 0;
//        }
//        String k = root.toString() + willRob;
//        if(map.containsKey(k)) {
//            return map.get(k);
//        }
//        int ans;
//        if(willRob) {
//            int ans1 = root.val + rob(root.left, false, map) + rob(root.right, false, map);
//            int ans2 = rob(root.left, true, map) + rob(root.right, true, map);
//            ans = Math.max(ans1, ans2);
//        } else {
//            ans = rob(root.left, true, map) + rob(root.right, true, map);
//        }
//        map.put(k, ans);
//        return ans;
//    }
}
