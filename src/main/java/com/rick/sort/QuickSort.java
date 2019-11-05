package com.rick.sort;

import java.util.Arrays;
import java.util.Collections;

/**
 * 快速排序
 * 将数组排序的方式则是当两个子数组都有序时整个数组也就自然有序了
 */
public class QuickSort extends Sort {
    @Override
    protected void sort(Comparable[] a) {
        //打乱数组
        Collections.shuffle(Arrays.asList(a));
        sort(a, 0, a.length - 1);
    }

    /**
     * 排序-递归
     * @param a
     * @param lo
     * @param hi
     */
    private void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi)
            return;
        int j = partition(a, lo, hi);   //切分
        sort(a, lo, j-1);           //将左半部分排序
        sort(a, j+1, hi);           //将有半部分排序
    }

    /**
     * 分区,每次以a[lo]（数组的第一个元素）为切分元素，
     * 小于a[lo]放在左边，大于a[lo]放在右边，
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    private int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi+1;   //左右扫描指针
        Comparable v = a[lo];   //切分元素
        while (true) {
            //扫描左右，检查扫描是否结束并交换元素
            while (compare(a[++i], v))
                if (i == hi)
                    break;
            while (compare(v, a[--j]))
                if (j == lo)
                    break;
            if (i >= j)
                break;
            exch(i, j, a);     //将v=a[j]放入正确的位置
        }
        exch(lo, j, a);     //将v=a[j]放入正确的位置
        return j;
    }

    public static void main(String[] args) {
        //Comparable[] a = { 49, 38, 65, 97, 76, 13, 27, 50 };
        Comparable[] a = { 'Q','U','I','C','K','S','O','R','T','E','X','A','M','P','L','E' };
        new QuickSort().sort(a);
        show(a);
    }
}
