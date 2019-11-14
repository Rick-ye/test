package com.rick.search;

/**
 * 二叉树查找
 * @param <Key>
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        Key key;
        Value vlaue;
        Node left;
        Node right;
        int count;

        public Node(Key key, Value value, int count) {
            this.key = key;
            this.vlaue = value;
            this.count = count;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.count;
    }

    public Value get(Key key) {

        return get(root, key);
    }

    private Value get(Node x, Key key) {
        
        return null;
    }

    public void put(Key key, Value value) {

    }

    public void max() {

    }

    public void min() {

    }

    public void floor() {

    }

    public void ceiling() {

    }

    public Value select(Key key) {

        return null;
    }

    public int rank(Key key) {
        return 0;
    }

    public void delete(Key key) {

    }

    public void deleteMin() {

    }

    public void deleteMax() {

    }

    public Iterable<Key> keys() {

        return null;
    }
}
