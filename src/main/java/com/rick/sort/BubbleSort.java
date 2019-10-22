package com.rick.sort;

/**
 * 冒泡排序：
 * 基本思路：相邻的两个数进行两两比较，大的数沉底，直到确定了第一个数
 * 其中有两个循环，第一个循环控制循环的轮次，第二个循环控制每一轮次比较的次数。
 *
 * 数组中大的数“沉底”，小的数“冒泡上升”，
 */
public class BubbleSort {

    public static int[] sort(int[] arr) {
        for (int i = 0; i<arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {3,53,2,5,7,23,2};
        arr = sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
