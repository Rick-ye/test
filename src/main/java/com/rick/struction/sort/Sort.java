package com.rick.struction.sort;

/**
 * 排序算法抽象类
 */
public abstract class Sort {

    protected Comparable[] aux = {};

    protected abstract void sort(Comparable[] a);

    /**
     * 比较两个数
     * compareTo():返回-1,0,1：如果a<b返回-1,a=b返回0,a>b返回1
     * @param a
     * @param b
     * @return
     */
    protected static boolean compare(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    /**
     * 交换数组元素
     * @param i
     * @param j
     * @param a
     */
    protected static void exch(int i, int j, Comparable[] a) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 打印数组元素
     * @param a
     */
    protected static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
    }

    /**
     * 判断数组是否已排序
     * @param a
     * @return
     */
    protected static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            if (compare(a[i], a[i+1]))
                return false;
        }
        return true;
    }

    /**
     * 原地归并算法
     * 将两个不同的有序数组归并到第三个数组中
     * @param a
     * @param lo
     * @param mid
     * @param hi
     */
    protected void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        //归并回a数组中
        for (int k = lo; k <= hi; k++) {
            if (i > mid)        //左半边用尽，取右半边的元素
                a[k] = aux[j++];
            else if (hi < j)    //右半边用尽，去做半边的元素
                a[k] = aux[i++];
            else if (compare(aux[j], aux[i])) //比较左半边的当前元素和右半边的当前元素，如果前者大于后者，
                a[k] = aux[j++];                //就取右半边的元素，否则取左半边的。
            else
                a[k] = aux[i++];
        }
    }

}
