package com.rick.search;

import com.rick.collection.queue.Queue;

import java.util.Iterator;

/**
 * 基于拉链法的散列表
 * @param <Key>
 * @param <Value>
 */
public class SeparateChainingHashST<Key, Value> {
    //键值对数
    private int N;
    //散列表大小
    private int M;

    private SequentialSearchST<Key,Value>[] sts;

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int M) {
        this.M = M;
        sts = (SequentialSearchST<Key,Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            sts[i] = new SequentialSearchST<>();
        }
    }

    public void put(Key key, Value value) {
        sts[hash(key)].put(key, value);

    }

    public Value get(Key key) {
        return sts[hash(key)].get(key);
    }

    public int size() {
        N = 0;
        for (int i = 0; i < M; i++) {
            N += sts[i].size();
        }
        return N;
    }

    private int hash(Key key) {
        return key.hashCode() & 0x7fffffff % M;
    }

    /**
     * 删除操作
     * @param key
     */
    public void delete(Key key) {
        int i = hash(key);
        SequentialSearchST<Key, Value> st = sts[i];
        Iterable<Key> keys = st.keys();
        Iterator<Key> it = keys.iterator();
        while (it.hasNext()) {
            if (key.equals(it.next())) {
                st.delete(key);
                break;
            }
        }
    }

    /**
     * 迭代器
     * @return
     */
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < M; i++) {
            Iterable<Key> keys = sts[i].keys();
            Iterator<Key> it = keys.iterator();
            while (it.hasNext())
                queue.enqueue(it.next());
        }
        return queue;
    }

}
