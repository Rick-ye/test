package com.rick.struction.search;

import com.rick.struction.collection.queue.Queue;

/**
 * 线性探测法实现散列表
 * @param <Key>
 * @param <Value>
 */
public class LinearProbingHashST<Key, Value> {

    private int N;

    private int M;

    private Key[] keys;

    private Value[] values;

    public LinearProbingHashST() {
        this(16);
    }

    public LinearProbingHashST(int M) {
        this.M = M;
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    /**
     * 存入一个键值
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        int i;
        for (i= hash(key); keys[i] != null; i = (i+1) % M)
            if (key.equals(keys[i])) {
                values[i] = value;
                return;
            }
        keys[i] = key;
        values[i] = value;
        N++;
    }

    /**
     * 获取一个键值
     * @param key
     * @return
     */
    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i+1) % M)
            if (key.equals(keys[i])) {
                return values[i];
            }
        return null;
    }

    /**
     * 删除操作
     * @param key
     */
    public void delete(Key key) {
        if (!contains(key)) return;
        int i = hash(key);
        //while循环找到要删除的键
        while (!key.equals(keys[i]))
            i = (i+1) % M;
        keys[i] = null;
        values[i] = null;
        i = (i+1) % M;
        //修复这个键值留下的空当
        while (keys[i] != null) {
            Key tempKey = keys[i];
            Value tempValue = values[i];
            keys[i] = null;
            values[i] = null;
            put(tempKey, tempValue);
            N--;
            i = (i+1) % M;
        }
        N--;
        if (N > 0 && N == M/8)
            resize(M/2);
    }

    /**
     * 调整数组大小
     * @param size
     */
    private void resize(int size) {
        LinearProbingHashST<Object, Object> t = new LinearProbingHashST<>(size);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null)
                t.put(keys[i], values[i]);
        }
        keys = (Key[]) t.keys;
        values = (Value[]) t.values;
        M = t.M;
    }

    public boolean contains(Key key) {
        for (int i = 0; i < M; i++) {
            if (keys[i].equals(key))
                return true;
        }
        return false;
    }

    public int size() {
        return N;
    }

    private int hash(Key key) {
        return key.hashCode() & 0x7fffffff % M;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < M; i++) {
            if (keys[i] != null)
                queue.enqueue(keys[i]);
        }
        return queue;
    }
}
