package com.example.math;

public class Leetcode_43 {
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")) return "0";
        int len1 = num1.length(), len2 = num2.length();
        int n = len1 + len2;
        int[] arr = new int[n];
        char[] c1 = num1.toCharArray();
        char[] c2 = num2.toCharArray();
        int idx = n - 1;
        for(int i = len1 - 1; i >= 0; i--) {
            int k = idx--;
            int x = c1[i] - '0';
            for(int j = len2 - 1; j >= 0; j--) {
                int y = c2[j] - '0';
                arr[k--] += x * y;
            }
        }
        for(int k = n - 1; k >= 1; k--) {
            int q = arr[k] % 10;
            int m = arr[k] / 10;
            arr[k] = q;
            arr[k - 1] += m;
        }
        StringBuffer sb = new StringBuffer();
        if(arr[0] != 0) sb.append(arr[0]);
        for(int i = 1; i < n; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }
}
