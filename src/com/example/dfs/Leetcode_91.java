package com.example.dfs;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_91 {
    public int numDecodings(String s) {
        char[] cs = s.toCharArray();
        Map<Integer, Integer> map = new HashMap<>();
        return dfs(cs, 0, map);
    }
    private int dfs(char[] cs, int pos, Map<Integer, Integer> map) {
        if(pos == cs.length) return 1;
        if(cs[pos] == '0') return 0;
        if(map.containsKey(pos)) {
            return map.get(pos);
        }
        int ans = dfs(cs, pos + 1, map);
        if(pos < cs.length - 1 && (cs[pos] == '1' || (cs[pos] == '2' && cs[pos + 1] <= '6'))) {
            ans += dfs(cs, pos + 2, map);
        }
        map.put(pos, ans);
        return ans;
    }
}
