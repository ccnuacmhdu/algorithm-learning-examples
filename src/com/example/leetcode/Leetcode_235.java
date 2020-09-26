package com.example.leetcode;

import java.util.*;

/**
 * 【二叉搜索树的最近公共祖先】
 */
public class Leetcode_235 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 【方法一：适用普通二叉树的通用解法】
     * 把所有节点的父母存成 Hash 表，再找到 p 节点的所有父母 set，然后不断找 q 节点的父母，
     * 第一次出现在 set 中的那个父母就是最低公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null) {
            return null;
        }
        Map<TreeNode, TreeNode> par = new HashMap<>();
        if(root != null) {
            par.put(root, null);
        }
        parent(root, par);

        Set<TreeNode> set = new HashSet<>();
        while (par.containsKey(p)) {
            set.add(p);
            p = par.get(p);
        }

        while (!set.contains(q)) {
            q = par.get(q);
        }

        return q;
    }
    private void parent(TreeNode root, Map<TreeNode, TreeNode> par) {
        if(root != null) {
            if(root.left != null) {
                par.put(root.left, root);
            }
            if(root.right != null) {
                par.put(root.right, root);
            }
            parent(root.left, par);
            parent(root.right, par);
        }
    }

    /**
     * 【方法二：利用二叉搜索树的性质】
     * 分别找出祖先到 p,q 的路径，两个路径中第一个不相同的值的上一个就是最低公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor_02(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> list1 = new ArrayList<>();
        List<TreeNode> list2 = new ArrayList<>();
        path(root, p, list1);
        path(root, q, list2);

        TreeNode ancestor = null;
        for(int i = 0; i < list1.size() && i < list2.size(); i++) {
            if(list1.get(i) == list2.get(i)) {
                ancestor = list1.get(i);
            } else {
                break;
            }
        }
        return ancestor;
    }
    private void path(TreeNode root, TreeNode v, List<TreeNode> path) {
        while (root != v) {
            path.add(root);
            if(root.val > v.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        path.add(root);
    }

    /**
     * 【方法三：利用二叉搜索树性质】
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor_03(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = null;
        while (true) {
            if(p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if(p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                ancestor = root;
                break;
            }
        }
        return ancestor;
    }

    /**
     * 【方法四：递归】
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor_04(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val>p.val && root.val>q.val) return lowestCommonAncestor(root.left, p, q);
        if(root.val<p.val && root.val<q.val) return lowestCommonAncestor(root.right,p,q);
        return root;
    }
}
