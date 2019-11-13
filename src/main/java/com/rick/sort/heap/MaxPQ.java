package com.rick.sort.heap;

import java.util.Comparator;
import java.util.Random;

/**
 * 基于堆的泛型优先队列的实现
 * @param <Key>
 */
public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int N = 0;
    //比较器
    private Comparator comparator;

    /**
     * 使用默认的Comparable比较
     * @param max
     */
    public MaxPQ(int max) {
        pq = (Key[]) new Comparable[max+1];
    }

    /**
     * 实现自己的比较器
     * @param max 数组大小
     * @param comparator 可以提工提供自己的比较器
     */
    public MaxPQ(int max, Comparator comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Comparable[max+1];
    }

    /**
     * 向堆中插入一个元素
     * @param k
     */
    public void insert(Key k) {
        pq[++N] = k;
        swin(N);
    }

    /**
     * 返回最大元素
     * @return
     */
    public Key max() {
        return pq[1];
    }

    /**
     * 删除并返回最大元素
     * @return
     */
    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        return max;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 返回元素个数
     * @return
     */
    public int size() {
        return N;
    }

    public void show() {
        for (int i = 0; i <= N; i++) {
            System.out.print(pq[i]+",");
        }
        System.out.println();
    }

    /**
     * 比较两个元素的大小
     * @param i
     * @param j
     * @return
     */
    private boolean compare(int i, int j) {
        if (comparator != null) {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * 交换两个元素
     * @param i
     * @param j
     */
    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /**
     * 上浮一个元素，使它找到合适的位置
     * @param k
     */
    private void swin(int k) {
        while (k > 1 && compare(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    /**
     * 下沉一个元素，使它找到合适的位置
     * @param k
     */
    private void sink(int k) {
        while (N >= 2*k) {
            int j = 2*k;
            if (j < N && compare(j, j+1))
                j++;
            if (!compare(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }

    public static char randomChar() {
        Character[] c = {'A','B','C','D','E','F','G','H','I','J','K','V','L','M','N',
                'O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        Random random = new Random();
        int i = random.nextInt(26);
        return c[i];
    }



}
