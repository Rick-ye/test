package com.rick.search;

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

    public Value get(Key key) {
        if (isEmpty())
            return null;
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0)
            return values[i];
        else
            return null;

    }

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

    public int rank(Key key) {
        return 0;
    }

    public void delete(Key key) {
        int i = rank(key);
        if (i<=n && keys[i].compareTo(key) == 0) {
            for (int j = n; j > i; j--) {
                keys[j-1] = keys[j];
                values[j-1] = values[j];
            }
            keys[i] = null;
            values[i] = null;
            n--;
        }
    }
}
