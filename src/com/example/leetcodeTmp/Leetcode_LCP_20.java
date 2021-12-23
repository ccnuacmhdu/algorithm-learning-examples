package com.example.leetcodeTmp;

import java.util.HashMap;
import java.util.Map;

/**
 * 【快速公交】
 */
public class Leetcode_LCP_20 {
    /**
     * 【方式一：暴力递归（超时）】
     * 逆向考虑，从 target 到 0，几种情况如下。
     * 1. 一站一站地走
     * 2. 刚好当前站可做快车
     * 3. 当前站不能坐快车，但往前走几站就可做快车
     * 4. 当前站不能坐快车，但往后走几站就可做快车
     *
     * @param target
     * @param inc
     * @param dec
     * @param jump
     * @param cost
     * @return
     */
    public int busRapidTransit_00(int target, int inc, int dec, int[] jump, int[] cost) {
        return (int)(f0(target, inc, dec, jump, cost) % 1000000007);
    }
    public long f0(long target, long inc, long dec, int[] jump, int[] cost) {
        if(target == 0) {
            return 0;
        }
        if(target == 1) {
            return inc;
        }
        long minCost = target*inc;
        for(int i = 0; i < jump.length; i++) {
            long next = target / jump[i];
            long mod = target % jump[i];
            if(mod == 0) {
                minCost = Math.min(minCost, f0(next, inc, dec, jump, cost) + cost[i]);
            } else if(target > jump[i]) {
                // 举例：当前站是 6，jum[i] = 4，那么可以往前走到 4，再坐快车，也可以往后走到 8，再坐快车
                minCost = Math.min(minCost, f0(next, inc, dec, jump, cost) + mod*inc + cost[i]);
                minCost = Math.min(minCost, f0(next+1, inc, dec, jump, cost) + (jump[i]-mod)*dec + cost[i]);
            } else if(target < jump[i]) {
                // 举例：当前站是 3，jum[i] = 4，那么只能往后走到 4，再坐快车
                minCost = Math.min(minCost, f0(next+1, inc, dec, jump, cost) + (jump[i]-mod)*dec + cost[i]);
            }
        }
        return minCost;
    }

    /**
     * 【方式二：基于方式一改成记忆化搜索】
     * @param target
     * @param inc
     * @param dec
     * @param jump
     * @param cost
     * @return
     */
    public int busRapidTransit(int target, int inc, int dec, int[] jump, int[] cost) {
        Map<Long, Long> memory = new HashMap<>();
        return (int)(f(target, inc, dec, jump, cost, memory) % 1000000007);
    }
    public long f(long target, long inc, long dec, int[] jump, int[] cost, Map<Long, Long> memory) {
        if(target == 0) {
            return 0;
        }
        if(target == 1) {
            return inc;
        }
        if(memory.containsKey(target)) {
            return memory.get(target);
        }
        long minCost = target*inc;
        for(int i = 0; i < jump.length; i++) {
            long next = target / jump[i];
            long mod = target % jump[i];
            if(mod == 0) {
                minCost = Math.min(minCost, f(next, inc, dec, jump, cost, memory) + cost[i]);
            } else if(target > jump[i]) {
                minCost = Math.min(minCost, f(next, inc, dec, jump, cost, memory) + mod*inc + cost[i]);
            }
            if(mod != 0) {
                minCost = Math.min(minCost, f(next+1, inc, dec, jump, cost, memory) + (jump[i]-mod)*dec + cost[i]);
            }
        }
        memory.put(target, minCost);
        return minCost;
    }

    public static void main(String[] args) {
        int target = 980632;
        int inc = 2933;
        int dec = 5626;
        int[] jump = {6061,5876,6528,6680,5580,2772,6619,7365,9474,2136};
        int[] cost = {1792,6103,9708,6519,2305,8327,7393,9533,269,7938};
        System.out.println(new Leetcode_LCP_20().busRapidTransit_00(target, inc, dec, jump, cost));
        System.out.println(new Leetcode_LCP_20().busRapidTransit(target, inc, dec, jump, cost));
    }
}
