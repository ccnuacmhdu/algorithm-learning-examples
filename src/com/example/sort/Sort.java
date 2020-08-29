package com.example.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1. 常见排序算法汇总
 * 归并排序
 * 选择排序
 * 冒泡排序
 * 插入排序
 * 快速排序
 * 堆排序
 *
 * 2. 对数器（验证算法正确性）
 * 1）有一个你想要测的方法 a；
 * 2）实现复杂度不好但是容易实现能保证正确的方法 b；
 * 3）实现一个随机样本产生器；
 * 4）把方法 a 和方法 b 跑相同的随机样本，看看得到的结果是否一样；
 * 5）如果有一个随机样本使得比对结果不一致，打印样本进行人工干预，改对方法 a；
 * 6）当样本数量很多时比对测试依然正确，可以确定方法 a 已经正确。
 */
public class Sort{
    public static void main(String[] args){
        int testTime = 500000;//测试次数
        int size = 100;
        int scope = 100;
        boolean flag = true;
        for(int i = 0; i < testTime; i++){
            int[] a = generateRandomArray(size, scope);
            int[] b = copyArray(a);
            heapSort2(a);
            compare(b);
            if(isEqual(a, b) == false){
                flag  = false;
                print(a);
                print(b);
                break;
            }
        }
        System.out.println(flag);
    }

    //测试
    public static void compare(int[] a){
        Arrays.sort(a);
    }

    /*
        归并排序
            O(N*logN) O(N) 稳定
            递归的时间复杂度估计之
            master公式: T(N) = a*T(N/b) + O(N^d)
                        log(b, a) = d ---> O((N^d)*logN)
                        log(b, a) < d ---> O(N^d)
                        log(b, a) > d ---> O(N^(log(b, a)))
        a是调用子问题次数，N是父问题规模，N/b是子问题规模，O(N^d)是除了调用子问题之外的时间复杂度。
        从形式上来看，也可以看出来这个公式表明父问题平均分成了规模相同的若干个子问题才可以用下面的结论。
    */
    public static void mergeSort(int[] a){
        if(a == null || a.length < 2){
            return;
        }
        mergeSort(a, 0, a.length-1);
    }
    public static void mergeSort(int[] a, int l, int r){
        if(l == r){
            return;
        }
        int mid = l + ((r-l)>>1);
        mergeSort(a, l, mid);
        mergeSort(a, mid+1, r);
        merge(a, l, mid, r);
    }
    public static void merge(int[] a, int l, int mid, int r){
        int i = l;
        int j = mid + 1;
        int[] tmp = new int[r-l+1];
        int cnt = 0;
        while(i <= mid && j <= r){
            if(a[i] <= a[j]){
                tmp[cnt++] = a[i++];
            }else{
                tmp[cnt++] = a[j++];
            }
        }
        while(i <= mid){
            tmp[cnt++] = a[i++];
        }
        while(j <= r){
            tmp[cnt++] = a[j++];
        }
        for(int k = 0; k < cnt; k++){
            a[k+l] = tmp[k];
        }
    }

    /*
        选择排序
            O(N^2) O(1) 不稳定，如8 2 8 7 9第二次选择7与8交换导致两个8与原来的顺序颠倒
    */
    public static void selectSort(int[] a){
        if(a == null || a.length < 2){
            return;
        }
        for(int i = 0; i < a.length - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < a.length; j++){
                minIndex = a[j] < a[minIndex] ? j : minIndex;
            }
            swap(a, minIndex, i);
        }
    }
    public static void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /*
        冒泡排序
            O(N^2) O(1) 稳定
    */
    public static void bubbleSort(int[] a){
        if(a == null || a.length < 2){
            return;
        }
        for(int i = a.length - 1; i >= 1; i--){
            for(int j = 0; j < i; j++){
                if(a[j] > a[j+1]){
                    swap(a, j, j+1);
                }
            }
        }
    }

    /*
        插入排序
            O(N)-O(N^2) O(1) 稳定
    */
    public static void insertSort(int[] a){
        if(a == null || a.length < 2){
            return;
        }
        for(int i = 1; i < a.length; i++){
            for(int j = i-1; j >= 0 && a[j+1] < a[j]; j--){
                swap(a, j, j+1);
            }
        }
    }

    /*
        快速排序
            O(N*logN)-O(N^2) O(logN)-O(N)
            不稳定，比如6，6，6，4序列，当前数是第一个6，划分参考值是5，6>5，和大于区左边数4交换
    */
    public static void quickSort(int[] a){
        if(a == null || a.length < 2){
            return;
        }
        quickSort(a, 0, a.length-1);
    }
    public static void quickSort(int[] a, int l, int r){
        if(l < r){
            int[] tmp = partition(a, l, r);
            quickSort(a, l, tmp[0]-1);
            quickSort(a, tmp[1]+1, r);
        }
    }
    public static int[] partition(int[] a, int l, int r){
        int small = l - 1;
        int big = r + 1;
        int cur = l;
        int pivot = l + (int)(Math.random()*(r-l+1));
        int v = a[pivot]; // 没改进前，v也不可是a[l]，因为a[l]可能交换到后面导致a[l]值变化
        while(cur < big){
            if(a[cur] > v){
                swap(a, cur, --big);
            }else if(a[cur] < v){
                swap(a, cur++, ++small);
            }else{
                cur++;
            }
        }
        return new int[]{small+1, big-1};
    }

    /*
        堆排序（直接用JavaAPI）
        O(N*logN) O(1) 不稳定，不稳定，如4 1 2 2第一步形成小根堆，就已不稳定了
    */
    public static void heapSort1(int[] a){
        if(a == null || a.length < 2){
            return;
        }
        //默认就是小根堆，下面的比较器没必要传入，下面是为了帮助用大根堆的时候这样用
        PriorityQueue<Integer> que = new PriorityQueue<Integer>(100, new Comparator<Integer>(){
            public int compare(Integer x, Integer y){
                return x - y;
            }
        });
        for(int i = 0; i < a.length; i++){
            que.add(a[i]);
        }
        int cnt = 0;
        while(!que.isEmpty()){
            a[cnt++] = que.poll();
        }
    }

    /*
        堆排序（手动实现）
    */
    public static void heapSort2(int[] a){
        if(a == null || a.length < 2){
            return;
        }
        //1.先把数组调成大根堆
        for(int i = 0; i < a.length; i++){
            heapInsert(a, i);
        }
        //2.大根堆堆顶元素和堆尾元素交换，堆大小减1，堆顶元素下沉操作
        int heapSize = a.length;
        swap(a, 0, --heapSize);
        //3.重复2操作，直到堆大小为0
        while(heapSize > 0){
            heapify(a, 0, heapSize);
            swap(a, 0, --heapSize);
        }
    }
    public static void heapInsert(int[] a, int index){
        while(a[index] > a[(index-1)/2]){
            swap(a, index, (index-1)/2);
            index = (index-1)/2;
        }
    }
    public static void heapify(int[] a, int index, int heapSize){
        int left = 2*index + 1;
        while(left < heapSize){
            int right = left + 1;
            int big = left;
            if(right < heapSize && a[right] > a[left]){
                big = right;
            }
            if(a[index] >= a[big]){
                big = index;
            }

            if(big == index){
                break;
            }
            swap(a, big, index);
            index = big;
            left = 2*index + 1;
        }
    }
    //产生随机数组，数组大小自己设定，数组中元素的值范围是[0, scope]
    //Math.random()---double---[0,1)
    //Math.random()*x---double---[0,x)
    //(int)(Math.random()*x)---int---[0,x-1]
    public static int[] generateRandomArray(int size, int scope){
        int[] a = new int[(int)((size+1)*Math.random())];
        for(int i = 0; i < a.length; i++){
            a[i] = (int)((scope+1)*Math.random()) - (int)(scope*Math.random());
        }
        return a;
    }
    //拷贝数组
    public static int[] copyArray(int[] a){
        if(a == null){
            return null;
        }
        int[] b = new int[a.length];
        for(int i = 0; i < a.length; i++){
            b[i] = a[i];
        }
        return b;
    }
    //测试
    public static boolean isEqual(int[] a, int[] b){
        if(a != null && b == null){
            return false;
        }
        if(a == null && b != null){
            return false;
        }
        if(a == null && b == null){
            return true;
        }
        if(a.length != b.length){
            return false;
        }
        for(int i = 0; i < a.length; i++){
            if(a[i] != b[i]){
                return false;
            }
        }
        return true;
    }

    public static void print(int[] a){
        if(a == null){
            return;
        }
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }
}



