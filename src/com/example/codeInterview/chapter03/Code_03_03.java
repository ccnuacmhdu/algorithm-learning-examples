package com.example.codeInterview.chapter03;

import java.util.*;

public class Code_03_03 {

    private static class Node {
        public int v;
        public Node left;
        public Node right;

        public Node(int v) {
            this.v = v;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int root = scanner.nextInt();
        List<Info> infos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int parent = scanner.nextInt();
            int leftChild = scanner.nextInt();
            int rightChild = scanner.nextInt();
            Info info = new Info(parent, leftChild, rightChild);
            infos.add(info);
        }
        Node tree = createBinaryTree(root, infos);

        printTree(tree);
    }

    public static void printTree(Node root) {
        System.out.println("Binary Tree:");
        printInOrder(root, 0, "H", 15);
        System.out.println();
    }

    /**
     * 较为直观打印二叉树（此中序遍历顺序是右-根-左，此顺序正好是树左转90度，可以手动走一遍右-根-左遍历，就很明白啦，此种中序遍历确实很妙）
     *
     * @param root 树根
     * @param h 高度，为了看起来清楚，每个数左边空格数可以是是高度减1的某个长度（这里取len）倍
     * @param mark 标志是头节点，还是左孩子或右孩子
     * @param len Integer占最大宽度是数是Integer.MIN_VALUE（-2147483648），11位，再加上头节点标志或左/右孩子标志、左右空格，15位
     */
    private static void printInOrder(Node root, int h, String mark, int len) {
        if(root == null) {
            return;
        }
        printInOrder(root.right, h + 1, "R", len);
        String val = mark + root.v + mark;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        // 每一个都正好是一行
        System.out.println(getSpace(h * len) + val);
        printInOrder(root.left, h + 1, "L", len);
    }
    private static String getSpace(int n) {
        String space = " ";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(space);
        }
        return sb.toString();
    }

    private static class Info {
        public int parent;
        public int leftChild;
        public int rightChild;

        public Info (int parent, int leftChild, int rightChild) {
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }
    private static Node getNode(Map<Integer, Node> map, int v) {
        if(v == 0) {
            return null;
        }
        if(!map.containsKey(v)) {
            map.put(v, new Node(v));
        }
        return map.get(v);
    }
    private static Node createBinaryTree(int root, List<Info> infos) {
        Node tree = new Node(root);
        Map<Integer, Node> map = new HashMap<>();
        map.put(root, tree);
        for(Info info: infos) {
            Node parent = getNode(map, info.parent);
            parent.left = getNode(map, info.leftChild);
            parent.right = getNode(map, info.rightChild);
        }
        return tree;
    }
}
