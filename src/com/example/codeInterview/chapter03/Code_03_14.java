package com.example.codeInterview.chapter03;

import java.util.*;

public class Code_03_14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int root = scanner.nextInt();
        List<Info> infos = new ArrayList<>();
        int p, l, r;
        for(int i = 0; i < n; i++) {
            p = scanner.nextInt();
            l = scanner.nextInt();
            r = scanner.nextInt();
            infos.add(new Info(p, l, r));
        }
        int nodeValue = scanner.nextInt();
        Map<Integer, Node> map = createBTree(root, infos);
        Node next = getNextNode(map.get(nodeValue));
        int res = next == null ? 0 : next.v;
        System.out.println(res);
    }

    public static Node getNextNode(Node node) {
        if(node == null) {
            return null;
        }
        if(node.right != null) {
            return getMostLeft(node.right);
        }
        Node parent = node.parent;
        while (parent != null && parent.right == node) {
            node = parent;
            parent = node.parent;
        }
        return parent;
    }
    private static Node getMostLeft(Node node) {
        if(node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private static class Node {
        public int v;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int v) {
            this.v = v;
        }
    }
    private static class Info {
        public int p;
        public int l;
        public int r;

        public Info(int p, int l, int r) {
            this.p = p;
            this.l = l;
            this.r = r;
        }
    }
    private static Node getNode(int v, Map<Integer, Node> map) {
        if(v == 0) {
            return null;
        }
        if(!map.containsKey(v)) {
            map.put(v, new Node(v));
        }
        return map.get(v);
    }
    private static Map<Integer, Node> createBTree(int root, List<Info> infos) {
        Map<Integer, Node> map = new HashMap<>();
        map.put(root, new Node(root));
        for(Info info: infos) {
            Node p = getNode(info.p, map);
            p.left = getNode(info.l, map);
            p.right = getNode(info.r, map);
            if(p.left != null) {
                p.left.parent = p;
            }
            if(p.right != null) {
                p.right.parent = p;
            }
        }
        return map;
    }


// [leetcode] 面试题 04.06. 后继者
//    private class TreeNode {
//        int val;
//        TreeNode left;
//        TreeNode right;
//        TreeNode(int x) { val = x; }
//    }
//    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
//        if(root == null || p == null) {
//            return null;
//        }
//        TreeNode pre = null;
//        Stack<TreeNode> stack = new Stack<>();
//        while(!stack.isEmpty() || root != null) {
//            if(root != null) {
//                stack.push(root);
//                root = root.left;
//            } else {
//                root = stack.pop();
//                if(pre == p) {
//                    return root;
//                }
//                pre = root;
//                root = root.right;
//            }
//        }
//        return null;
//    }

}
