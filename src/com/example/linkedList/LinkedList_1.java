package com.example.linkedList;

/**
 * 【题目】给定一个单链表的头节点head，节点的值类型是整型，再给定一个整数pivot。
 * 实现一个调整链表的函数，将链表调整为左部分都是值小于pivot的节点，中间部分都
 * 是值等于pivot的节点，右部分都是值大于pivot的节点。
 */
public class LinkedList_1 {
    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        print(head1);
        //Node res1 = listPartition1(head1, 5);
        Node res1 = listPartition2(head1, 5);
        print(res1);
    }

    private static class Node{
        public int v;
        public Node next;
        public Node(int v){
            this.v = v;
        }
    }


    //方案二：准备六个指针，分别是小于区、等于区、大于区的头尾指针，每个区做完
    //			比划分边界值小就放在小于区，比划分值大就放在大于区，否则放入等于区，最后合并即可
    public static Node listPartition2(Node head, int pivot){
        Node smallHead = null;
        Node smallTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node bigHead = null;
        Node bigTail = null;

        //注意下面一个区仅仅有一个节点的时候头和尾是一样的，如果再新加一个，
        //只需要加入尾部，尾指针后移即可，头指针一直是头指针不变。

        Node cur = head;
        while(cur != null){
            if(cur.v < pivot){
                if(smallHead == null){
                    smallHead = smallTail = cur;
                }else{
                    smallTail.next = cur;
                    smallTail = cur;
                }
            }else if(cur.v > pivot){
                if(bigHead == null){
                    bigHead = bigTail = cur;
                }else{
                    bigTail.next = cur;
                    bigTail = cur;
                }
            }else{
                if(equalHead == null){
                    equalHead = equalTail = cur;
                }else{
                    equalTail.next = cur;
                    equalTail = cur;
                }
            }
            cur = cur.next;
        }
        //下面就是把三个区合并的问题了，关键问题是有的区可能不存在！
        //最简单粗暴的方法就是分类讨论，8种情况
        if(smallTail != null && equalTail != null && bigTail != null){//111
            smallTail.next = equalHead;
            equalTail.next = bigHead;
            bigTail.next = null;
            return smallHead;
        }else if(smallTail != null && equalTail != null && bigTail == null){//110
            smallTail.next = equalHead;
            equalTail.next = null;
            return smallHead;
        }else if(smallTail != null && equalTail == null && bigTail != null){//101
            smallTail.next = bigHead;
            bigTail.next = null;
            return smallHead;
        }else if(smallTail != null && equalTail == null && bigTail == null){//100
            smallTail.next = null;
            return smallHead;
        }else if(smallTail == null && equalTail != null && bigTail != null){//011
            equalTail.next = bigHead;
            bigTail.next = null;
            return equalHead;
        }else if(smallTail == null && equalTail != null && bigTail == null){//010
            equalTail.next = null;
            return equalHead;
        }else if(smallTail == null && equalTail == null && bigTail != null){//001
            bigTail.next = null;
            return bigHead;
        }else{//000
            return head;
        }
    }

    //方案一：把链表节点放在了数组中，对数组做partition
    public static Node listPartition1(Node head, int pivot){
        if(head == null){
            return head;
        }
        int len = 0;
        Node cur = head;
        while(cur != null){
            len++;
            cur = cur.next;
        }
        Node[] a = new Node[len];
        int i = 0;
        cur = head;
        while(cur != null){
            a[i++] = cur;
            cur = cur.next;
        }
        //在数组中进行partition
        arrPartition(a, pivot);
        //放回原链表
        i = 1;
        for(; i < a.length; i++){
            a[i-1].next = a[i];
        }
        a[i-1].next = null;
        return a[0];
    }

    private static void arrPartition(Node[] a, int pivot){
        int left = -1;
        int right = a.length;
        int cur = 0;
        while(cur < right){
            if(a[cur].v < pivot){
                swap(a, ++left, cur++);
            }else if(a[cur].v > pivot){
                swap(a, --right, cur);
            }else{
                cur++;
            }
        }
    }

    private static void swap(Node[] a, int i, int j){
        Node tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static void print(Node head){
        while(head != null){
            System.out.print(head.v + " ");
            head = head.next;
        }
        System.out.println();
    }
}
