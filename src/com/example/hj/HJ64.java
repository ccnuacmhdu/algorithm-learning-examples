package com.example.hj;

import java.util.*;

/**
 * HJ64 MP3光标位置
 */
public class HJ64{
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            String s = sc.next();
            int curr = 1;
            int begin = 1;
            for(int i = 0;i < s.length();++i){
                char c = s.charAt(i);
                if (n <= 4) {
                    if (c == 'U') {
                        if (curr == 1) {
                            curr = n;
                        }else {
                            curr--;
                        }
                    }else if(c == 'D'){
                        if (curr == n) {
                            curr = 1;
                        }else {
                            curr++;
                        }
                    }
                }else {
                    if (c == 'U') {
                        if (curr == 1) {
                            curr = n;
                            begin = n - 3;
                        }else if(curr == begin){
                            curr--;
                            begin--;
                        }else{
                            curr--;
                        }
                    }else if(c == 'D'){
                        if(curr == n){
                            curr = 1;
                            begin = 1;
                        }else if(curr == begin + 3){
                            curr++;
                            begin++;
                        }else{
                            curr++;
                        }
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 1;i <= 4;++i){
                if (n >= i) {
                    sb.append(begin + i - 1).append(" ");
                }
            }
            System.out.println(sb.toString().trim());
            System.out.println(curr);
        }
    }
}
