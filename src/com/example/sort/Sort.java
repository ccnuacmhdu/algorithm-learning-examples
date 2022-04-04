package com.example.sort;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1. 常见排序算法汇总
 * 1）归并排序
 * 2）选择排序
 * 3）冒泡排序
 * 4）插入排序
 * 5）快速排序
 * 6）堆排序
 * <p>
 * 2. 对数器（验证算法正确性）
 * 1）有一个你想要测的方法 a；
 * 2）实现复杂度不好但是容易实现能保证正确的方法 b；
 * 3）实现一个随机样本产生器；
 * 4）把方法 a 和方法 b 跑相同的随机样本，看看得到的结果是否一样；
 * 5）如果有一个随机样本使得比对结果不一致，打印样本进行人工干预，改对方法 a；
 * 6）当样本数量很多时比对测试依然正确，可以确定方法 a 已经正确。
 * <p>
 * 3. 稳定性分析（以升序排序为例）
 * 1）稳定性概念：排序之后原来相等元素的相对顺序不变，则是稳定排序，否则不稳定。不基于比较的排序（桶排序）是稳定的。
 * 2）冒泡排序：严格比右边相邻数大的时候才与右边相邻数交换，就可保证稳定；如果与右边相邻数相等也与右边的相邻数交换，那就不稳定了。
 * 3）插入排序：严格比左边小的时候才往前搜索插入就能稳定；如果相等的话也往前继续搜索并插入，那就不稳定了。
 * 4）选择排序：不稳定。 8 1 8 7 9 简单选择排序: 第二次外循环8和8的相对顺序就发生了改变。
 * 5）归并排序：左右两侧合并的时候，当左右两侧值相等的时候，先合并左侧的数，就可用保证稳定；如果先合并右边的数就不稳定了。
 * 6）快速排序：不稳定。比如 6 6 6 4 序列，当前数是第一个 6，划分参考值是 5，由于 6 大于 5，会和大于区左边一个数 4 交换，导致不稳定。
 * 7）堆排序：不稳定。在堆上调整的过程本身就不是稳定的，堆排序的第一步把不断调成大根堆的过程中，比如序列 7 3 6 6 形成大根堆的过程中，就已经不稳定了。
 * <p>
 * 4. 常见排序时空复杂度及稳定性汇总
 * 时间复杂度    空间复杂度     稳定性
 * 冒泡排序     O(N^2)      O(1)        Y
 * 插入排序     O(N^2)      O(1)        Y
 * 选择排序     O(N^2)      O(1)        N
 * 归并排序     O(N*logN)   O(N)        Y
 * 快速排序     O(N*logN)   O(logN)     N
 * 堆排序     O(N*logN)   O(1)        N
 **/
public class Sort {
    public static void main(String[] args) {
        int testTime = 500000;//测试次数
        int size = 100;
        int scope = 100;
        boolean flag = true;
        for (int i = 0; i < testTime; i++) {
            int[] a = generateRandomArray(size, scope);
            int[] b = copyArray(a);
            quickSort(a);
            compare(b);
            if (isEqual(a, b) == false) {
                flag = false;
                print(a);
                print(b);
                break;
            }
        }
        System.out.println(flag);
    }

    /**
     * 冒泡排序
     *
     * @param a
     */
    public static void bubbleSort(int[] a) {
        if (a == null || a.length < 2) {
            return;
        }
        for (int i = a.length - 1; i > 0; i--) {
            boolean hasSwap = false;
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    hasSwap = true;
                }
            }
            if (!hasSwap) {
                break;
            }
        }
    }

    /**
     * 选择排序
     *
     * @param a
     */
    public static void selectSort(int[] a) {
        if (a == null || a.length < 2) {
            return;
        }
        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                minIndex = a[j] < a[minIndex] ? j : minIndex;
            }
            if (minIndex != i) {
                swap(a, minIndex, i);
            }
        }
    }

    /**
     * 插入排序
     *
     * @param a
     */
    public static void insertSort(int[] a) {
        if (a == null || a.length < 2) {
            return;
        }
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                swap(a, j, j - 1);
            }
        }
    }

    /**
     * 归并排序
     * <p>
     * 递归的时间复杂度估计之 master公式: T(N) = a*T(N/b) + O(N^d)
     * log(b, a) = d ---> O((N^d)*logN)
     * log(b, a) < d ---> O(N^d)
     * log(b, a) > d ---> O(N^(log(b, a)))
     * a 是调用子问题次数，N 是父问题规模，N/b 是子问题规模，O(N^d)是除了调用子问题之外的时间复杂度。
     * 从形式上来看，也可以看出来这个公式表明父问题平均分成了规模相同的若干个子问题才可以用上面的结论。
     *
     * @param a
     */
    public static void mergeSort(int[] a) {
        if (a == null || a.length < 2) {
            return;
        }
        mergeSort(a, 0, a.length - 1);
    }

    public static void mergeSort(int[] a, int l, int r) {
        if (r - l < 1) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        mergeSort(a, l, mid);
        mergeSort(a, mid + 1, r);
        merge(a, l, mid, r);
    }

    public static void merge(int[] a, int l, int mid, int r) {
        int tmp[] = new int[r - l + 1];
        int left = l;
        int right = mid + 1;
        int cnt = 0;
        while (left <= mid && right <= r) {
            if (a[left] <= a[right]) {
                tmp[cnt++] = a[left++];
            } else {
                tmp[cnt++] = a[right++];
            }
        }
        while (left <= mid) {
            tmp[cnt++] = a[left++];
        }
        while (right <= r) {
            tmp[cnt++] = a[right++];
        }
        for (int k = 0; k < cnt; k++) {
            a[l + k] = tmp[k];
        }
    }

    /**
     * 快速排序（过程图解点击 README.md 链接）
     * <p>
     * 时间：O(N*logN)-O(N^2)
     * 空间：O(logN)-O(N)
     *
     * @param a
     */
    public static void quickSort(int[] a) {
        if (a == null || a.length < 2) {
            return;
        }
        quickSort(a, 0, a.length - 1);
    }

    public static void quickSort(int[] a, int l, int r) {
        if (r > l) {
            int[] tmp = partition(a, l, r);
            quickSort(a, l, tmp[0] - 1);
            quickSort(a, tmp[1] + 1, r);
        }
    }

    // 荷兰国旗问题，返回等于 pivot 区域的左右边界
    public static int[] partition(int[] a, int l, int r) {
        int choose = l + (int) ((r - l) * Math.random());
        int pivot = a[choose];  // 要这样算出来，而不是直接用 a[choose] （这个每次调用 random 导致变化。。）
        int small = l - 1;  // 小于 pivot 区右边界
        int big = r + 1;    // 大于 pivot 区左边界
        int cur = l;
        while (cur < big) {
            if (a[cur] < pivot) {
                swap(a, cur++, ++small);
            } else if (a[cur] == pivot) {
                cur++;
            } else {
                swap(a, cur, --big);
            }
        }
        return new int[]{small + 1, big - 1};
    }

    /**
     * 堆排序
     * <p>
     * 堆
     * 1，堆就是用数组实现的完全二叉树结构
     * 2，完全二叉树中如果每棵子树的最大值都在顶部就是大根堆
     * 3，完全二叉树中如果每棵子树的最小值都在顶部就是小根堆
     * <p>
     * 用数组表示完全二叉树，a[i] 的左孩子是 a[i*2+1]，a[i] 的右孩子是 a[i*2+2]，a[i] 的父亲是 a[(i-1)/2]
     *
     * @param a
     */
    public static void heapSort(int[] a) {
        if (null == a || a.length < 2) {
            return;
        }
        for (int i = 0; i < a.length; i++) {
            heapInsert(a, i);
        }
        int size = a.length - 1;
        while (size > 0) {
            swap(a, 0, size--);
            heapify(a, size);
        }
    }

    public static void heapInsert(int[] a, int loc) {
        while (a[loc] > a[(loc - 1) / 2]) {
            swap(a, loc, (loc - 1) / 2);
            loc = (loc - 1) / 2;
        }
    }

    public static void heapify(int[] a, int size) {
        int l = 1;
        while (l <= size) {
            // 刚开始把 int max = l 放在 while 上面了！错
            int max = l;
            int r = l + 1;
            if (r <= size && a[r] > a[l]) {
                max = r;
            }
            if (a[max] <= a[l / 2]) {
                break;
            }
            swap(a, max, l / 2);
            l = max * 2 + 1;
        }
    }

    /**
     * 堆排序（借助 API）
     *
     * @param a
     */
    public static void heapSort2(int[] a) {
        if (null == a || a.length < 2) {
            return;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((o1, o2) -> o1.compareTo(o2));
        for (int i = 0; i < a.length; i++) {
            pq.add(a[i]);
        }
        int k = 0;
        while (!pq.isEmpty()) {
            a[k++] = pq.poll();
        }
    }

    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    // 测试（使用库排序函数 sort 作为对数器中能保证正确的排序算法，来检验自己写的排序算法的正确性）
    public static void compare(int[] a) {
        Arrays.sort(a);
    }

    /**
     * 产生随机数组，数组大小是 size，数组元素值都是在 [-scope, scope] 范围内
     * <p>
     * Math.random()---double---[0.0, 1.0)
     * Math.random()*x---double---[0.0, x)
     * (int)(Math.random()*x)---int---[0, x-1]
     *
     * @param size
     * @param scope
     * @return
     */
    public static int[] generateRandomArray(int size, int scope) {
        int[] a = new int[(int) ((size + 1) * Math.random())];
        for (int i = 0; i < a.length; i++) {
            a[i] = -scope + (int) (Math.random() * (2 * scope + 1));
        }
        return a;
    }

    // 拷贝数组 a 并返回新数组的引用
    public static int[] copyArray(int[] a) {
        if (a == null) {
            return null;
        }
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }

    // 判断两个数组是否完全相同
    public static boolean isEqual(int[] a, int[] b) {
        if (a != null && b == null) {
            return false;
        }
        if (a == null && b != null) {
            return false;
        }
        if (a == null && b == null) {
            return true;
        }
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public static void print(int[] a) {
        if (a == null) {
            return;
        }
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}



