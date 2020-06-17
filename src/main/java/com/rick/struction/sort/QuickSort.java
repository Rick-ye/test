package com.rick.struction.sort;

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
     * 排序-递归(未经优化的)
     * @param a
     * @param lo
     * @param hi
     */
    /*private void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi)
            return;

        int j = partition(a, lo, hi);   //切分
        sort(a, lo, j-1);           //将左半部分排序
        sort(a, j+1, hi);           //将有半部分排序
    }*/

    /**
     * 排序-递归（优化）
     * 在数组变小的时候也就是lo+M>=hi时，这些小数组使用插入排序算法，
     * 因为数组小时插入排序比快速排序更快。
     * @param a
     * @param lo
     * @param hi
     */
    /*private void sort(Comparable[] a, int lo, int hi) {
        int M = 15;
        if (hi <= lo + M){
            new InsertionSort().sort(a, lo, hi);
            return;
        }
        int j = partition(a, lo, hi);   //切分
        sort(a, lo, j-1);           //将左半部分排序
        sort(a, j+1, hi);           //将有半部分排序
    }*/

    /**
     * 在数组中存在大量重复元素的时候，可以使用三向切分的方式提高排序的效率
     * @param a
     * @param lo
     * @param hi
     */
    private void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi)
            return;
        Comparable v = a[lo];
        int lt = lo, i = lo+1, gt = hi;
        while (i <= gt) {
            int result = a[i].compareTo(v);
            if (result < 0)
                exch(lt++, i++, a);
            else if (result > 0)
                exch(i, gt--, a);
            else
                i++;
        }
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
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
//        Comparable[] a = { 'Q','U','I','C','I','C','Q','U','C','Q','I','U','C','Q','C','U' };
        new QuickSort().sort(a);
        show(a);
    }
}
