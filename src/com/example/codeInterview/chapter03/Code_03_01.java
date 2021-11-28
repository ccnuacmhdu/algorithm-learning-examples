package com.example.codeInterview.chapter03;

import java.util.*;

public class Code_03_01 {
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
        for(int i = 0; i < n; i++) {
            int parent = scanner.nextInt();
            int leftChild = scanner.nextInt();
            int rightChild = scanner.nextInt();
            Info info = new Info(parent, leftChild, rightChild);
            infos.add(info);
        }
        Node tree = createBinaryTree(root, infos);

//        List<Integer> res = new ArrayList<>();
//
//        preOrder(tree, res);
//        printTree(res);
//        res.clear();
//
//        inOrder(tree, res);
//        printTree(res);
//        res.clear();
//
//        postOrder(tree, res);
//        printTree(res);
//        res.clear();

        List<Integer> preRes = preOrderNonRecursive(tree);
        List<Integer> inRes = inOrderNonRecursive(tree);
        List<Integer> postRes = postOrderNonRecursive(tree);

        printTree(preRes);
        printTree(inRes);
        printTree(postRes);
    }

    public static void preOrder(Node tree, List<Integer> res) {
        if(tree == null) {
            return;
        }
        res.add(tree.v);
        preOrder(tree.left, res);
        preOrder(tree.right, res);
    }

    public static void inOrder(Node tree, List<Integer> res) {
        if(tree == null) {
            return;
        }
        inOrder(tree.left, res);
        res.add(tree.v);
        inOrder(tree.right, res);
    }

    public static void postOrder(Node tree, List<Integer> res) {
        if(tree == null) {
            return;
        }
        postOrder(tree.left, res);
        postOrder(tree.right, res);
        res.add(tree.v);
    }

    public static List<Integer> preOrderNonRecursive(Node tree) {
        List<Integer> res = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(tree);
        while (!stack.isEmpty()) {
            Node tmp = stack.pop();
            res.add(tmp.v);
            if(tmp.right != null) {
                stack.push(tmp.right);
            }
            if(tmp.left != null) {
                stack.push(tmp.left);
            }
        }
        return res;
    }

    public static List<Integer> postOrderNonRecursive(Node tree) {
        List<Integer> res = new ArrayList<>();
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(tree);
        while (!stack1.isEmpty()) {
            Node tmp = stack1.pop();
            stack2.push(tmp);
            if(tmp.left != null) {
                stack1.push(tmp.left);
            }
            if(tmp.right != null) {
                stack1.push(tmp.right);
            }
        }
        while (!stack2.isEmpty()) {
            res.add(stack2.pop().v);
        }
        return res;
    }

    public static List<Integer> inOrderNonRecursive(Node tree) {
        List<Integer> res = new ArrayList<>();
        if(tree != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || tree != null) {
                if(tree != null) {
                    stack.push(tree);
                    tree = tree.left;
                } else {
                    tree = stack.pop();
                    res.add(tree.v);
                    tree = tree.right;
                }
            }
        }
        return res;
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

    private static void printTree(List<Integer> res) {
        boolean first = true;
        for(Integer integer: res) {
            if(first) {
                System.out.print(integer);
                first = false;
            } else {
                System.out.print(" " + integer);
            }
        }
        System.out.println();
    }
}