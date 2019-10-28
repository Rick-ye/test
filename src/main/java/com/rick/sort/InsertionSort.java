package com.rick.sort;

/**
 * 插入排序算法
 * 通常人们整理桥牌的方法是一张一张得来，将一张桥牌插入到其他已经有序的牌中的适当位置
 * 在计算机中，为了给要插入的元素腾出空间，我们需要将其余所有元素向右移动一位。这就是插入排序
 */
public class InsertionSort extends Sort {
    @Override
    protected void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && compare(a[j], a[j-1]); j--) {
                exch(j, j-1, a);
            }
        }
    }

    public static void main(String[] args) {
        Comparable[] arr = {3,53,2,5,7,23,};
        new InsertionSort().sort(arr);
        show(arr);
    }
}
