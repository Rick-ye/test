package com.rick.search;

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

    private int hash(Key key) {
        return key.hashCode() & 0x7fffffff % M;
    }

    public void delete(Key key) {
        sts[hash(key)].delete(key);
    }

    public Iterable<Key> keys() {
        for (int i = 0; i < M; i++) {
            return sts[i].keys();
        }
        return null;
    }

}
