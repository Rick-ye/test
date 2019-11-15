package com.rick.search;

import com.rick.collection.queue.Queue;

/**
 * 有序二分查找
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int n;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n==0;
    }

    /**
     * 根据key获取value值
     * @param key key
     * @return
     */
    public Value get(Key key) {
        if (isEmpty())
            return null;
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0)
            return values[i];
        else
            return null;

    }

    /**
     * 插入键值对
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        int i = rank(key);
        if (i<n && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        for (int j = n; j > i; j--) {
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }
        keys[i] = key;
        values[i] = value;
        n++;
    }

    /**
     * while循环实现二分查找算法
     * @param key
     * @return
     */
    public int rank(Key key) {
        int lo = 0, hi = n-1;
        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    /**
     * 递归实现二分查找
     * @param key
     * @param lo
     * @param hi
     * @return
     */
    public int rank(Key key, int lo, int hi) {
        if (lo > hi)
            return lo;
        int mid = lo + (hi - lo)/2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp < 0)
            return rank(key, lo, mid - 1);
        else if (cmp > 0)
            return rank(key, mid + 1, hi);
        else
            return mid;
    }

    /**
     * 删除一个元素
     * @param key
     */
    public void delete(Key key) {
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            for (int j = i; j < n; j++) {
                keys[j]=keys[j+1];
                values[j] = values[j+1];
            }
            keys[n] = null;
            keys[n] = null;
            n--;
        }
    }

    public boolean contains(Key k) {
        int i = rank(k);
        if (k.compareTo(keys[i]) == 0)
            return true;
        return false;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i++) {
            queue.enqueue(keys[i]);
        }
        if (contains(hi))
            queue.enqueue(hi);
        return queue;
    }

    public Key select(int i) {
        return keys[i];
    }

}
