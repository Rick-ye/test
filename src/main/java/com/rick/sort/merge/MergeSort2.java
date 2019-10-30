package com.rick.sort.merge;

import com.rick.sort.Sort;

/**
 * 自顶向上的归并排序算法
 */
public class MergeSort2 extends Sort {
    @Override
    protected void sort(Comparable[] a) {
        int n = a.length;
        aux = new Comparable[a.length];
        for (int sz = 1; sz < n; sz = sz+sz) {
            for (int lo = 0; lo < n-sz; lo += sz+sz) {
                merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, n-1));
            }
        }
    }

    public static void main(String[] args) {
        Comparable[] a = { 49, 38, 65, 97, 76, 13, 27, 50 };
        new MergeSort2().sort(a);
        show(a);
    }
}
