package com.example.binaryTree;

import java.util.*;

/**
 * 1. 二叉树遍历
 * 2. 二叉树边数和节点数的关系
 *    n = n0 + n1 + n2 （n表示节点总数，n0/n1/n2表示出度分别为0，1，2的节点数）
 *    e = n1 + 2 * n2 （e表示边数）
 *    n = e + 1
 *    n0 = n2 + 1
 */
public class BinaryTree {

    private static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void preOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preOrderRecursive(root.left);
        preOrderRecursive(root.right);
    }

    public static void inOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderRecursive(root.left);
        System.out.print(root.val + " ");
        inOrderRecursive(root.right);
    }

    public static void postOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderRecursive(root.left);
        postOrderRecursive(root.right);
        System.out.print(root.val + " ");
    }

    public static void preOrderUnRecursive(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(root);
            while (!stack.isEmpty()) {
                root = stack.pop();
                System.out.print(root.val + " ");
                if (root.right != null) {
                    stack.push(root.right);
                }
                if (root.left != null) {
                    stack.push(root.left);
                }
            }
        }
        System.out.println();
    }

    // 中序遍历（非递归）。左边界全部压栈，依次出栈（左-中），转向右侧入栈出栈（右）
    public static void inOrderUnRecursive(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    System.out.print(root.val + " ");
                    root = root.right;
                }
            }
        }
        System.out.println();
    }

    public static void postOrderUnRecursive(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack1 = new Stack<TreeNode>();
            Stack<TreeNode> stack2 = new Stack<TreeNode>();
            stack1.push(root);
            while (!stack1.isEmpty()) {
                root = stack1.pop();
                stack2.push(root);
                if (root.left != null) {
                    stack1.push(root.left);
                }
                if (root.right != null) {
                    stack1.push(root.right);
                }
            }
            while (!stack2.isEmpty()) {
                System.out.print(stack2.pop().val + " ");
            }
        }
        System.out.println();
    }

    // 二叉树宽度优先遍历，逐层从左到右输出（Leetcode_102）
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        List<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> que = new LinkedList<>();
        TreeNode t;
        que.add(root);
        while(!que.isEmpty()) {
            int size = que.size();
            list.clear();
            for(int i = 0; i < size; i++) {
                t = que.poll();
                list.add(t.val);
                if(t.left != null) {
                    que.add(t.left);
                }
                if(t.right != null) {
                    que.add(t.right);
                }
            }
            res.add(new ArrayList<Integer>(list));
        }
        return res;
    }

    // 二叉树深度（Leetcode_104）
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    public static void main(String[] args) {
        TreeNode head = new TreeNode(5);
        head.left = new TreeNode(3);
        head.right = new TreeNode(8);
        head.left.left = new TreeNode(2);
        head.left.right = new TreeNode(4);
        head.left.left.left = new TreeNode(1);
        head.right.left = new TreeNode(7);
        head.right.left.left = new TreeNode(6);
        head.right.right = new TreeNode(10);
        head.right.right.left = new TreeNode(9);
        head.right.right.right = new TreeNode(11);

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecursive(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecursive(head);
        System.out.println();
        System.out.print("post-order: ");
        postOrderRecursive(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        preOrderUnRecursive(head);
        inOrderUnRecursive(head);
        postOrderUnRecursive(head);
    }
}