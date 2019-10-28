package com.rick.sort;

/**
 * 排序算法抽象类
 */
public abstract class Sort {

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
            System.out.println(a[i]);
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

    public static void main(String[] args) {
        System.out.println(compare(1,3));
    }
}
