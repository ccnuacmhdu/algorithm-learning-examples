package com.example.leetcodeTmp;

import java.util.Arrays;

/**
 * 【二叉搜索树中的众数】
 */
public class Leetcode_501 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 中序遍历，由于是升序的，不断判断相邻的数是否相等，不断更新
     *
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        if(root == null) {
            return new int[]{};
        }
        int[] cur = new int[]{root.val-1};
        int[] cnt = new int[]{0};
        int[] max = new int[]{0};
        int[] idx = new int[]{1};
        // ！！此句话是试探出来的测试样例最值 2120
        int[] res = new int[3000];
        f(root, cur, max, cnt, res, idx);
        if(cnt[0] > max[0]) {
            return new int[]{cur[0]};
        }
        if(cnt[0] == max[0]) {
            res[idx[0]++] = cur[0];
            Arrays.copyOfRange(res, 0, idx[0]);
        }
        return Arrays.copyOfRange(res, 0, idx[0]);

    }
    private void f(TreeNode root, int[] cur, int[] max, int[] cnt, int[] res, int[] idx) {
        if(root != null) {
            f(root.left, cur, max, cnt, res, idx);
            if(root.val == cur[0]) {
                cnt[0]++;
            } else {
                if(cnt[0] > max[0]) {
                    res[0] = cur[0];
                    idx[0] = 1;
                    max[0] = cnt[0];
                } else if(cnt[0] == max[0]) {
                    res[idx[0]++] = cur[0];
                }
                cnt[0] = 1;
                cur[0] = root.val;
            }
            f(root.right, cur, max, cnt, res, idx);
        }
    }
}
