package com.example.codeinterview.chapter03;

import java.util.*;

public class Code_03_12 {
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
        boolean isBST = isBST(tree);
        boolean isCBT = isCBT(tree);
        System.out.println(isBST);
        System.out.println(isCBT);
    }

    public static boolean isCBT(Node root) {
        if(root == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean leafAppeared = false;
        while (!queue.isEmpty()) {
            root = queue.poll();
            // 叶子一旦出现，后面层序遍历到的节点只能叶子。右孩子不是null且左孩子是null的情况一旦出现，就不可能是完全二叉树了
            if((leafAppeared && (root.left != null || root.right != null ))
                    || (root.right != null && root.left == null)) {
                return false;
            }
            if(root.left != null) {
                queue.add(root.left);
            }
            // 此判断不能放在这里，原因是下面案例就会判断错
//            else {
//                leafAppeared = true;
//            }
//            7 1
//            1 2 3
//            2 4 5
//            3 6 0
//            4 7 0
//            5 0 0
//            6 0 0
//            7 0 0
            if(root.right != null) {
                queue.add(root.right);
            } else {
                // 右孩子为null，必定要出现叶子（如果此时左孩子也是null，root就是叶子，如果左孩子不是null，那左孩子就只能是叶子）
                leafAppeared = true;
            }

        }
        return true;
    }

    private static class ReturnType {
        public boolean isBST;
        public int min;
        public int max;

        public ReturnType(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }
    private static ReturnType process(Node root) {
        if(root == null) {
            return new ReturnType(true, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        ReturnType leftData = process(root.left);
        ReturnType rightData = process(root.right);
        int min = Math.min(root.v, Math.min(leftData.min, rightData.min));
        int max = Math.max(root.v, Math.max(leftData.max, rightData.max));
        boolean isBST = leftData.max < root.v
                && rightData.min > root.v
                && leftData.isBST
                && rightData.isBST;
        return new ReturnType(isBST, min, max);
    }
    public static boolean isBST(Node root) {
        return process(root).isBST;
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
