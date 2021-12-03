package com.example.binaryTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * 1. 二叉树遍历
 * 2. 二叉树边数和节点数的关系
 *    n = n0 + n1 + n2 （n表示节点总数，n0/n1/n2表示出度分别为0，1，2的节点数）
 *    e = n1 + 2 * n2 （e表示边数）
 *    n = e + 1
 *    n0 = n2 + 1
 */
public class BinaryTree {

    private static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public static void preOrderRecursive(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preOrderRecursive(root.left);
        preOrderRecursive(root.right);
    }

    public static void inOrderRecursive(Node root) {
        if (root == null) {
            return;
        }
        inOrderRecursive(root.left);
        System.out.print(root.val + " ");
        inOrderRecursive(root.right);
    }

    public static void postOrderRecursive(Node root) {
        if (root == null) {
            return;
        }
        postOrderRecursive(root.left);
        postOrderRecursive(root.right);
        System.out.print(root.val + " ");
    }

    public static void preOrderUnRecursive(Node root) {
        if (root != null) {
            Stack<Node> stack = new Stack<Node>();
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
    public static void inOrderUnRecursive(Node root) {
        if (root != null) {
            Stack<Node> stack = new Stack<Node>();
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

    public static void postOrderUnRecursive(Node root) {
        if (root != null) {
            Stack<Node> stack1 = new Stack<Node>();
            Stack<Node> stack2 = new Stack<Node>();
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

    // 宽度遍历求最大宽度
    public static int treeBFS(Node root) {
        if (root != null) {
            LinkedList<Node> queue = new LinkedList<Node>();
            Map<Node, Integer> levelMap = new HashMap<Node, Integer>();

            int maxWidth = 0;
            int curLevel = 0;
            int curWidth = 0;

            queue.add(root);
            levelMap.put(root, 1);
            while (!queue.isEmpty()) {
                root = queue.poll();
                //System.out.print(root.val+" ");
                if (root.left != null) {
                    queue.add(root.left);
                    levelMap.put(root.left, levelMap.get(root) + 1);
                }
                if (root.right != null) {
                    queue.add(root.right);
                    levelMap.put(root.right, levelMap.get(root) + 1);
                }
                if (levelMap.get(root) > curLevel) {
                    curLevel = levelMap.get(root);
                    curWidth = 1;
                } else {
                    curWidth++;
                }
                maxWidth = Math.max(maxWidth, curWidth);
            }
            return maxWidth;
        }
        return 0;
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

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

        System.out.println("============BFS=============");
        System.out.println(treeBFS(head));
    }
}