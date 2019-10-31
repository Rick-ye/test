package com.rick.sort;

/**
 * 冒泡排序：
 * 基本思路：相邻的两个数进行两两比较，大的数沉底，直到确定了第一个数
 * 其中有两个循环，第一个循环控制循环的轮次，第二个循环控制每一轮次比较的次数。
 *
 * 数组中大的数“沉底”，小的数“冒泡上升”，
 */
public class BubbleSort extends Sort {


    protected void sort(Comparable[] arr) {
        for (int i = 0; i<arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (compare(arr[j+1], arr[j])) {
                    exch(j, j+1, arr);
                }
            }
        }
    }

    public static void main(String[] args) {
        Comparable[] arr = {3,53,2,5,7,23,2};
        new BubbleSort().sort(arr);
        show(arr);
    }
}
