package com.rick.sort;

/**
 * 希尔排序
 * 希尔排序的思想上是使数组中任意间隔为h的元素都是有序的。这样的数组被称为h有序数组。
 * 换句话说，一个h有序数组就是h个互相独立的有序数组编织在一起组成的一个数组
 */
public class ShellSort extends Sort {
    @Override
    protected void sort(Comparable[] a) {
        int n = a.length;
        int h =  1;
        while (h < n/3)
            h = h*3 + 1;  //1,4,13....
        while (h >= 1) {
            //将数组变为h有序
            for (int i = h; i < n; i++) {
                //将a[i]插入到a[i-1],a[i-2],a[i-3]...中
                for (int j = i; j >= h && compare(a[j], a[j-h]); j-=h) {
                    exch(j, j-h, a);
                }
            }
            h = h/3;
        }
    }

    public static void main(String[] args) {
        Comparable[] arr = {'S','H','E','L','L','S','O','R','T','E','X','A','M','P','L','E'};
        new ShellSort().sort(arr);
        show(arr);
    }
}
