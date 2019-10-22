package com.rick.sort;

/**
 * 选择排序：
 * 基本思路：第一轮拿出第一个数与后面剩余的数进行比较，确定第一个数，
 * 第二轮取出第二个数与后面剩余的数进行比较，确定第二个数。以此类推。
 */
public class SelectionSort {
    public static int[] sort(int[] arr) {
        for (int i = 0; i<arr.length; i++) {
            for (int j = i + 1; j<arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp;
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {3,53,2,5,7,23,};
        arr = sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
