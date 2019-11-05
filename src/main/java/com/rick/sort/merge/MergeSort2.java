package com.rick.sort.merge;

import com.rick.sort.Sort;

/**
 * 归并排序是一种渐进最优的基于比较排序的算法
 * 更准确的说，归并排序在最坏情况下的比较次数和任意基于比较的排序算法所需要的最少比较次数都是NlgN
 *
 * 归并排序空间复杂度不是最优的
 * 除了比较，算法的其他操作也很重要（如：访问数组）
 * 不进行比较也能进行某些数据排序
 *
 * 自顶向上的归并排序算法
 */
public class MergeSort2 extends Sort {
    @Override
    public void sort(Comparable[] a) {
        int n = a.length;
        aux = new Comparable[a.length];
        for (int sz = 1; sz < n; sz = sz+sz) {
            for (int lo = 0; lo < n-sz; lo += sz+sz) {
                merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, n-1));
            }
        }
    }

    public static void main(String[] args) {
        Comparable[] a = { 7, 1, 5, 8, 3, 2, 4, 6 };
        new MergeSort2().sort(a);
        show(a);
    }
}
