package com.example.greedy;

import java.util.*;

public class Leetcode_406 {
    // 原始：[[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
    // 按 hi 降序：[[7,0],[7,1],[6,1],[5,0],[5,2],[4,4]]
    // 按 ki 升序：[[7,0],[7,1],[6,1],[5,0],[5,2],[4,4]]
    // 按 ki 插入：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (p1, p2) -> p1[0] != p2[0] ? p2[0] - p1[0] : p1[1] - p2[1]);
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < people.length; i++) {
            list.add(people[i][1], people[i]);
        }
        return list.toArray(new int[0][0]);
    }
}
