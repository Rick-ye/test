package com.rick.collection.bag;

import java.util.Iterator;

public class Bag<T> implements Iterable<T> {

    private Node first;

    private int n;

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private class Node {
        Node next;
        T t;
    }

    public void add(T t) {
        Node oldFirst = first;
        first = new Node();
        first.next = oldFirst;
        first.t = t;
        n++;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private class ListIterator implements Iterator<T> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T t = current.t;
            current = current.next;
            return t;
        }

        @Override
        public void remove() {

        }
    }
}
