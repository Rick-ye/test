package com.rick.sort;

/**
 * 选择排序算法
 * 首先，找到数组中最小的那个元素，其次，将它和数组的第一个元素交换位置；
 * 再次，在剩下的元素中找到最小的元素，将它与数组中的第二个元素交换位置；
 * 如此往复，直到将整个数组排序。因为它在不断地选择剩余元素中的最小元素
 */
public class SelectionSort extends Sort {

    /**
     * 优化版：减少交换的次数
     * 这几乎将效率提高了10倍
     * @param arr
     */
    @Override
    public void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;    //最小元素的索引
            for (int j = i + 1; j < arr.length; j++) {
                if (compare(arr[j], arr[min]))
                    min = j;
            }
            exch(i, min, arr);
        }
    }

    /*@Override
    protected void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if (compare(a[j], a[i]))
                    exch(j, i, a);
            }
        }
    }*/

    public static void main(String[] args) {
        //Comparable[] arr = {3,53,2,5,7,23,};
        Comparable[] arr = {'a','d','t','v','w','f','h','i'};
        //Comparable[] arr = {"ad","dd","dt"};
        new SelectionSort().sort(arr);
        show(arr);
    }
}
