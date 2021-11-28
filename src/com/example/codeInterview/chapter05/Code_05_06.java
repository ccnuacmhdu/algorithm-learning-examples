package com.example.codeInterview.chapter05;

import java.util.*;

public class Code_05_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String start = scanner.next();
        String end = scanner.next();
        List<String> ss = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            ss.add(scanner.next());
        }
        List<List<String>> res = getShortestPaths(ss, start, end);
        if(res.size() == 0) {
            System.out.println("NO");
            return;
        }
        System.out.println("YES");
        for(List<String> list: res) {
            boolean first = true;
            for(String s: list) {
                if(first) {
                    System.out.print(s);
                    first = false;
                    continue;
                }
                System.out.print(" -> " + s);
            }
            System.out.println();
        }
    }
    public static Map<String, List<String>> getNexts(List<String> ss) {
        Map<String, List<String>> nexts = new HashMap<>();
        Set<String> set = new HashSet<>(ss);
        for(String s: ss) {
            nexts.put(s, getNext(s, set));
        }
        return nexts;
    }
    public static List<String> getNext(String s, Set<String> set) {
        List<String> next = new ArrayList<>();
        char[] chs = s.toCharArray();
        for(char c = 'a'; c <= 'z'; c++) {
            for(int i = 0; i < chs.length; i++) {
                if(chs[i] != c) {
                    char tmp = chs[i];
                    chs[i] = c;
                    if(set.contains(String.valueOf(chs))) {
                        next.add(String.valueOf(chs));
                    }
                    chs[i] = tmp;
                }
            }
        }
        Collections.sort(next); // 答案测试样例是要求字典序的
        return next;
    }

    public static Map<String, Integer> getShortestDistances(String start, Map<String, List<String>> nexts) {
        Map<String, Integer> distances = new HashMap<>();
        distances.put(start, 0);
        Set<String> set = new HashSet<>();
        set.add(start);
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            start = queue.poll();
            for(String s: nexts.get(start)) {
                if(!set.contains(s)) {
                    distances.put(s, distances.get(start) + 1);
                    queue.add(s);
                    set.add(s);
                }
            }
        }
        return distances;
    }

    public static void getShortestPaths(String start,
                                        String end,
                                        Map<String, List<String>> nexts,
                                        Map<String, Integer> distances,
                                        List<List<String>> res,
                                        LinkedList<String> solution) {
        solution.add(start);
        if(start.equals(end)) {
            res.add(new LinkedList<>(solution));
        } else {
            for(String next: nexts.get(start)) {
                if(distances.get(start) + 1 == distances.get(next)) {
                    getShortestPaths(next, end, nexts, distances, res, solution);
                }
            }
        }
        solution.pollLast();
    }

    public static List<List<String>> getShortestPaths(List<String> ss, String start, String end) {
        ss.add(start); // 重要，start可能不在ss列表里
        Map<String, List<String>> nexts = getNexts(ss);
        Map<String, Integer> distances = getShortestDistances(start, nexts);
        List<List<String>> res = new ArrayList<>();
        LinkedList<String> solution = new LinkedList<>();
        getShortestPaths(start, end, nexts, distances, res, solution);
        return res;
    }
}
