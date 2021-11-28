package com.example.codeInterview.chapter03;

import java.util.*;

public class Code_03_02 {
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
        printEdge1(tree);
        printEdge2(tree);
    }

    /**
     * 标准1打印二叉树边界
     *
     * 1）标记二叉树每层的最左和最右节点
     * 2）先打印每层最左节点，后打印每层叶子节点（非最左、最右），再打印每层最右节点（非既是最左又是最右）
     *
     * @param root
     */
    public static void printEdge1(Node root) {
        if(root == null) {
            return;
        }
        int h = getHeight(root);
        int[][] mark = new int[h+1][2];
        markLR(root, 1, mark);
        for(int i = 1; i <= h; i++) {
            System.out.print(mark[i][0] + " ");
        }
        printLeaf(root, 1, mark);
        for(int i = h; i >= 1; i--) {
            if(!(mark[i][0] == mark[i][1])) {
                System.out.print(mark[i][1] + " ");
            }
        }
        System.out.println();
    }
    private static int getHeight(Node root) {
        if(root == null) {
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
    private static void markLR(Node root, int h, int[][] mark) {
        if(root == null) {
            return;
        }
        mark[h][0] = mark[h][0] == 0 ? root.v : mark[h][0];
        mark[h][1] = root.v;
        markLR(root.left, h + 1, mark);
        markLR(root.right, h + 1, mark);
    }
    private static void printLeaf(Node root, int h, int[][] mark) {
        if(root == null) {
            return;
        }
        if(root.left == null && root.right == null && mark[h][0] != root.v && mark[h][1] != root.v) {
            System.out.print(root.v + " ");
        }
        printLeaf(root.left, h + 1, mark);
        printLeaf(root.right, h + 1, mark);
    }

    /**
     * 标准2打印二叉树边界
     *
     * 先找到第一个既有左孩子又有右孩子的节点，该节点左树先根遍历打印，该节点右树后根遍历打印
     *
     * @param root
     */
    public static void printEdge2(Node root) {
        if(root == null) {
            return;
        }
        System.out.print(root.v + " ");
        if(root.left != null && root.right != null) {
            printLeft(root.left, true);
            printRight(root.right, true);
        } else {
            printEdge2(root.left == null ? root.right : root.left);
        }
        System.out.println();
    }
    private static void printLeft(Node root, boolean print) {
        if(root == null) {
            return;
        }
        if(print || (root.left == null && root.right == null)) {
            System.out.print(root.v + " ");
        }
        printLeft(root.left, print);
        printLeft(root.right, print && root.left == null ? true : false);
    }
    private static void printRight(Node root, boolean print) {
        if(root == null) {
            return;
        }
        printRight(root.left, print && root.right == null ? true : false);
        printRight(root.right, print);
        if(print || (root.left == null && root.right == null)) {
            System.out.print(root.v + " ");
        }
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
