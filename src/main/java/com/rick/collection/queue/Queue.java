package com.rick.collection.queue;

import java.util.Iterator;

/**
 * 队列（链表实现）
 *
 * 在结构化存储数据集时，链表是数组的一种重要的替代方式
 * @param <T>
 */
public class Queue<T> implements Iterable<T> {

    //队列的开头
    private Node first;

    //队列的结尾
    private Node last;

    //队列的长度
    private int n;

    private class Node {
        Node next;
        T t;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    /**
     * 向队列添加元素
     * 注意：如果队列为空，则first和last都指向新节点
     * 否则将旧的最后一个元素指向新节点
     * @param t
     */
    public void enqueue(T t) {
        Node oldLast = last;
        last = new Node();
        last.t = t;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    /**
     *
     * @return
     */
    private T dequeue() {
        T t = first.t;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;
        }
        return t;
    }

    public Iterator<T> iterator() {
        return new LinkIterator();
    }

    private class LinkIterator implements Iterator<T> {

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
        Queue<String> queue = new Queue<>();
        queue.enqueue("rick");
        queue.enqueue("Tom");
        queue.enqueue("Simba");
        System.out.println(queue.size());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue("Doris");

        Iterator<String> iterator = queue.iterator();
        System.out.println("队列数据：");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
