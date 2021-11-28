package com.example.codeInterview.chapter03;

import java.util.*;

public class Code_03_08 {
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
        Node[] errs = getTwoErrorNodes(tree);
        System.out.println(errs[1].v + " " + errs[0].v);
    }

    /**
     * 拿到搜索二叉树中两个错误的节点
     *
     * 中序遍历，举例，原始数据是 1，2，3，4，5，两种错误情况，
     * 1） 5，2，3，4，1，两次降序，第一次降序的较大值是错误节点，第二次降序的较小值是错误节点
     * 2）1，3，2，4，5，一次降序，该降序的两个值都是错误节点，且前者较大，后者较小
     *
     * @param root
     * @return
     */
    public static Node[] getTwoErrorNodes(Node root) {
        Node[] errs = new Node[2];
        if(root == null) {
            return errs;
        }
        Node pre = null;
        Node tmp = root;
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || tmp != null) {
            if(tmp != null) {
                stack.push(tmp);
                tmp = tmp.left;
            } else {
                tmp = stack.pop();
                if(pre != null && pre.v > tmp.v) {
                    errs[0] = errs[0] == null ? pre : errs[0];
                    errs[1] = tmp;
                }
                pre = tmp;
                tmp = tmp.right;
            }
        }
        return errs;
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
