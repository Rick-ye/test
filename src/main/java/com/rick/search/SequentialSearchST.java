package com.rick.search;

import org.junit.Test;

import java.util.Iterator;

public class SequentialSearchST<Key, Value> {
    //第一个节点
    private Node first;

    private int n;

    //链表的定义
    private class Node {
        private Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.value;
        }
        return null;
    }

    public void put(Key key, Value value) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        Node newFirst = new Node(key, value, first);
        first = newFirst;
        n++;
    }

    public int size() {
        return n;
    }

    public void delete(Key key) {
        /*for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.next = x.next.next;
                x.value = null;
                return;
            }
        }*/
    }

    public Iterable<Key> keys() {
        return new KeysIterable();
    }

    class KeysIterable implements Iterable<Key> {

        Node current = first;

        @Override
        public Iterator iterator() {

            return new Iterator() {
                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public Key next() {
                    Node node = current;
                    current = current.next;
                    return node.key;
                }
            };
        }
    }

    @Test
    public void test() {
        String[] a = {"S","E","A","R","C","H","E","X","A","M","P","L","E"};
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        for (int i = 0; i < a.length; i++) {
            st.put(a[i], i);
        }
        Iterable<String> keys = st.keys();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Integer value = st.get(key);
            System.out.println(key + ": "+ value);
        }
    }
}
