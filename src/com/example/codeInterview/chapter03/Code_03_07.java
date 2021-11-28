package com.example.codeInterview.chapter03;

import java.util.*;

public class Code_03_07 {
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
        int p, l, r;
        for(int i = 0; i < n; i++) {
            p = scanner.nextInt();
            l = scanner.nextInt();
            r = scanner.nextInt();
            infos.add(new Info(p, l, r));
        }
        Node tree = createBTree(root, infos);
        printBTreeByLevel(tree);
        printBTreeZigzag(tree);
    }

    public static void printBTreeByLevel(Node root) {
        if(root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Integer> hMap = new HashMap<>();
        queue.add(root);
        hMap.put(root, 1);
        Node tmp;
        int curH = 1;
        System.out.print("Level " + curH + " :");
        while (!queue.isEmpty()) {
            tmp = queue.poll();
            if(curH == hMap.get(tmp)) {
                System.out.print(" " + tmp.v);
            } else {
                curH++;
                System.out.print("\nLevel " + curH + " : " + tmp.v);
            }
            if(tmp.left != null) {
                queue.add(tmp.left);
                hMap.put(tmp.left, hMap.get(tmp) + 1);
            }
            if(tmp.right != null) {
                queue.add(tmp.right);
                hMap.put(tmp.right, hMap.get(tmp) + 1);
            }
        }
        System.out.println();
    }

    public static void printBTreeZigzag(Node root) {
        if(root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Integer> hMap = new HashMap<>();
        queue.add(root);
        hMap.put(root, 1);
        Node tmp;
        int curH = 1;
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            tmp = queue.poll();
            if(curH == hMap.get(tmp)) {
                list.add(tmp.v);
            } else {
                if(curH % 2 != 0) {
                    System.out.print("Level " + curH + " from left to right:");
                    for(int i = 0; i < list.size(); i++) {
                        System.out.print(" " + list.get(i));
                    }
                } else {
                    System.out.print("Level " + curH + " from right to left:");
                    for(int i = list.size() - 1; i >= 0; i--) {
                        System.out.print(" " + list.get(i));
                    }
                }
                System.out.println();
                list.clear();
                list.add(tmp.v);
                curH++;
            }
            if (tmp.left != null) {
                queue.add(tmp.left);
                hMap.put(tmp.left, hMap.get(tmp) + 1);
            }
            if (tmp.right != null) {
                queue.add(tmp.right);
                hMap.put(tmp.right, hMap.get(tmp) + 1);
            }
        }
        if(curH % 2 != 0) {
            System.out.print("Level " + curH + " from left to right:");
            for(int i = 0; i < list.size(); i++) {
                System.out.print(" " + list.get(i));
            }
            System.out.println();
        } else {
            System.out.print("Level " + curH + " from right to left:");
            for(int i = list.size() - 1; i >= 0; i--) {
                System.out.print(" " + list.get(i));
            }
            System.out.println();
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
    private static Node createBTree(int root, List<Info> infos) {
        Map<Integer, Node> map = new HashMap<>();
        map.put(root, new Node(root));
        for(Info info: infos) {
            Node p = getNode(info.p, map);
            p.left = getNode(info.l, map);
            p.right = getNode(info.r, map);
        }
        return map.get(root);
    }
}
