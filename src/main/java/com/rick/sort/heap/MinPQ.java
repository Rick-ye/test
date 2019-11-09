package com.rick.sort.heap;

/**
 *
 * @param <Item>
 */
public class MinPQ<Item extends Comparable<Item>> {

    private Item[] pq;

    private int N;

    public MinPQ(int min) {
        pq = (Item[]) new Comparable[min+1];
    }

    /**
     * 向堆中插入一个元素
     * @param k
     */
    public void insert(Item k) {
        pq[++N] = k;
        swin(N);
    }

    /**
     * 返回堆中最小元素
     * @return
     */
    public Item min() {
        return pq[1];
    }

    /**
     * 返回并删除最小元素
     * @return
     */
    public Item delMin() {
        Item item = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        return item;
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
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * 交换两个元素
     * @param i
     * @param j
     */
    private void exch(int i, int j) {
        Item temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /**
     * 上浮一个元素，使它找到合适的位置
     * @param k
     */
    private void swin(int k) {
        while (k > 1 && compare(k, k/2)) {
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
            if (j < N && compare(j+1, j))
                j++;
            if (!compare(j, k))
                break;
            exch(k, j);
            k = j;
        }
    }

    public static void main(String[] args) {

    }

}
