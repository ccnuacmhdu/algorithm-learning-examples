package com.example.leetcode;

import java.util.*;

public class Leetcode_406 {
    // 按照 h 降序、k 升序排序（保证了前面的大于等于后面的），再遍历一遍，按照 k 插入即可
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? (o1[1] - o2[1]) : (o2[0] - o1[0]));
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < people.length; i++) {
            list.add(people[i][1], people[i]);
        }
        return list.toArray(new int[][]{});
    }
}
