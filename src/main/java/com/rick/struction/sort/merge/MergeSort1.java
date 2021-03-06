package com.rick.struction.sort.merge;

import com.rick.struction.sort.Sort;

/**
 * 归并算法
 * 将两个有序的数组归并成一个更大的有序数组
 *
 * 归并排序将数组分成两个子数组分别排序，并将两个有序的子数组归并
 *
 * 自顶向下的归并排序
 */
public class MergeSort1 extends Sort {

    @Override
    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    /**
     * 对子数组进行排序且归并
     * @param a
     * @param lo
     * @param hi
     */
    private void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi)
            return;
        int mid = lo + (hi - lo)/2;
        sort(a, lo, mid);       //对左半边排序
        sort(a, mid+1, hi); //对右半边排序
        merge(a, lo, mid, hi);   //归并结果
    }

    public static void main(String[] args) {
        Comparable[] a = { 7, 1, 5, 8, 3, 2, 4, 6 };
        new MergeSort1().sort(a);
        show(a);
    }

}
