package com.example.codeinterview.chapter03;

import java.util.*;

public class Code_03_04 {
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

        // 序列化+反序列化+序列化（为了测试反序列化代码是否正确，但会内存溢出。。）
        String serializeByPreRes1 = serializeByPre(tree);
//        Node deserializeByPreRes1 = deserializeByPre(serializeByPreRes1);
//        String serializeByPreRes2 = serializeByPre(deserializeByPreRes1);
        System.out.println(serializeByPreRes1);
        // 序列化+反序列化+序列化（为了测试反序列化代码是否正确，但会内存溢出。。）
        String serializeByLevelRes1 = serializeByLevel(tree);
//        Node deserializeByLevelRes1 = deserializeByLevel(serializeByLevelRes1);
//        String serializeByLevelRes2 = serializeByLevel(deserializeByLevelRes1);
        System.out.println(serializeByLevelRes1);

    }

    /**
     * 先序序列化
     *
     * @param root
     * @return
     */
    public static String serializeByPre(Node root) {
        if(root == null) {
            return "#!";
        }
        String res = root.v + "!";
        res += serializeByPre(root.left);
        res += serializeByPre(root.right);
        return res;
    }

    /**
     * 先序反序列化
     *
     * @param s
     * @return
     */
    public static Node deserializeByPre(String s) {
        String[] ss = s.split("!");
        Queue<String> queue = new LinkedList<>();
        for(int i = 0; i < ss.length; i++) {
            queue.add(ss[i]);
        }
        return deserializeByPre(queue);
    }
    private static Node deserializeByPre(Queue<String> queue) {
        String v = queue.poll();
        // 注意，字符串比较使用 equals，不要用 ==，否则有可能错误
        if("#".equals(v)) {
            return null;
        }
        Node root = new Node(Integer.valueOf(v));
        root.left = deserializeByPre(queue);
        root.right = deserializeByPre(queue);
        return root;
    }

    /**
     * 二叉树层次遍历序列化
     *
     * @param root
     * @return
     */
    public static String serializeByLevel(Node root) {
        if(root == null) {
            return "#!";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.v + "!");
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node tmp = null;
        while (!queue.isEmpty()) {
            tmp = queue.poll();
            if(tmp.left != null) {
                sb.append(tmp.left.v + "!");
                queue.add(tmp.left);
            } else {
                sb.append("#!");
            }

            if(tmp.right != null) {
                sb.append(tmp.right.v + "!");
                queue.add(tmp.right);
            } else {
                sb.append("#!");
            }
        }
        return sb.toString();
    }

    /**
     * 二叉树层次遍历反序列化
     *
     * @param s
     * @return
     */
    public static Node deserializeByLevel(String s) {
        String[] ss = s.split("!");
        Queue<Node> queue = new LinkedList<>();
        int index = 0;
        Node root = generateNodeByString(ss[index++]);
        queue.add(root);
        Node tmp = null;
        while (!queue.isEmpty()) {
            tmp = queue.poll();
            if(tmp != null) {
                tmp.left = generateNodeByString(ss[index++]);
                tmp.right = generateNodeByString(ss[index++]);
                if(tmp.left != null) {
                    queue.add(tmp.left);
                }
                if(tmp.right != null) {
                    queue.add(tmp.right);
                }
            }
        }
        return root;
    }
    private static Node generateNodeByString(String s) {
        if("#".equals(s)) {
            return null;
        }
        return new Node(Integer.valueOf(s));
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
