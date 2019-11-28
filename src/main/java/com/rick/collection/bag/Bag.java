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
        return new ListIterator();
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

    public static void main(String[] args) {
        Bag<Integer> bag = new Bag<>();
        for (int i = 0; i < 10; i++) {
            bag.add(i);
        }
        Iterator<Integer> it = bag.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
