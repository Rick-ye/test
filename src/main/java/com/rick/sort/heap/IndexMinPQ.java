package com.rick.sort.heap;

/**
 * 基于关联索引的泛型优先队列的实现
 */
public class IndexMinPQ<Item extends Comparable<Item>> {

    private Item[] pq;

    private int N;

    /**
     * 创建一个最大容量为maxN的优先队列，索引的取值范围在0到maxN-1
     * @param maxN
     */
    public IndexMinPQ(int maxN) {
        pq = (Item[]) new Comparable[maxN];
    }

    /**
     * 插入一个元素，将它与索引k相关联
     * @param k
     * @param item
     */
    public void insert(int k, Item item) {
        if (N >= pq.length) {
            if (k > 2*N)
                resize(k);
            else
                resize(2*N);
        }
        N++;
        pq[k] = item;
    }

    /**
     * 将索引为k的元素设为item
     * @param k
     * @param item
     */
    public void change(int k, Item item) {
        pq[k] = item;
    }

    public int size() {
        return N;
    }

    /**
     * 是否存在索引为k的元素
     * @param k
     * @return
     */
    public boolean contains(int k) {
        if (k <= pq.length && pq[k] != null)
            return true;
        else
            return false;
    }

    /**
     * 删除索引为k及其关联的元素
     * @param k
     */
    public void delete(int k) {
        pq[k] = null;
        N--;
    }

    /**
     * 返回最小的元素
     * @return
     */
    public Item min() {
        Item item = pq[1];
//        exch(1, N--);
//        pq[N+1] = null;
//        sink(1);
        return item;
    }

    /**
     * 返回最小元素的索引
     * @return
     */
    public int minIndex() {
        return 1;
    }

    /**
     * 删除最小元素并返回它的索引值
     * @return
     */
    public int delMin() {
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        return 1;
    }

    /**
     * 队列是否为空
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 动态扩缩容数组
     * @param newSize
     */
    private void resize(int newSize) {
        Item[] items = (Item[]) new Comparable[newSize];
        for (int i = 1; i <= pq.length; i++) {
            items[i] = pq[i];
        }
        pq = items;
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
        int size = 5;
        IndexMinPQ<Character> pq = new IndexMinPQ<>(size);
        for (int i = 1; i < size; i++) {
            pq.insert(i, sortChar());
        }

        while (!pq.isEmpty()) {
            System.out.println(pq.min());
        }

    }

    static int i = 0;
    protected static char sortChar() {
        Character[] c = {'A','B','C','D','E','F','G','H','I','J','K','V','L','M','N',
                'O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        return c[i++];
    }

}
