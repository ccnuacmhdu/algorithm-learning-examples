package com.example.codeInterview.chapter09;

public class Code_09_03 {
    //i:第i层，n:一共n层，flag:false表示下面节点（右孩子-凸），true表示上面节点（左孩子-凹）
    public static void printAll(int i, int n, boolean flag){
        print(1, n, true);
    }

    public static void print(int i, int n, boolean flag){
        if(i <= n){
            print(i+1,n,true);
            System.out.println(flag == true ? "凹" : "凸");
            print(i+1,n,false);
        }
    }

    public static void main(String[] args){
        printAll(1,3,true);
    }

}
