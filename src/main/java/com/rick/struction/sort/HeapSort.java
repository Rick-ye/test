package com.rick.struction.sort;

/**
 * 堆排序
 */
public class HeapSort extends Sort {

    @Override
    protected void sort(Comparable[] a) {
        int N = a.length;
        //初始化堆的构造
        for (int k = N/2; k >= 1; k--) {
            sink(a, k, N);
        }
        //堆排序
        while (N > 1) {
            exch(1, --N, a);
            sink(a, 1, N);
        }

    }

    /**
     * 下沉元素
     * @param a 数组
     * @param k 指定的下沉的元素
     * @param N 数组大小
     */
    private void sink(Comparable[] a, int k, int N) {
        while (--N >= 2*k) {
            int j = 2*k;
            if (j < N && compare(a[j], a[j+1]))
                j++;
            if (!compare(a[k], a[j]))
                break;
            exch(k, j, a);
            k = j;
        }
    }

    public static void main(String[] args) {
        Comparable[] arr = {null,'S','O','R','T','E','X','A','M','P','L','E'};
        new HeapSort().sort(arr);
        show(arr);
    }
}
