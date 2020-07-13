package com.rick.struction.collection.stack.linkedlist;

import java.util.Iterator;

/**
 * 栈（链表实现）
 *
 * 链表是一种递归的数据结构，它或者为空（null），或者是指向一个节点的引用
 * 该节点含有一个泛型的元素和一个指向另一条链表的引用
 *
 * 链表的达到了最优目标：
 * 1.它可以处理任意类型的数据
 * 2.所需的空间总是和集合的大小成正比
 * 3.操作所需的时间总是和集合大小无关
 * @param <T>
 */
public class Stack<T> implements Iterable<T> {

    /**
     * 第一个节点
     */
    private Node first;

    //链表大小
    private int n;

    //链表节点
    private class Node {
        Node next;
        T t;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private int size() {
        return n;
    }

    public void push(T t) {
        Node oldFirst = first;
        first = new Node();
        first.next = oldFirst;
        first.t = t;
        n++;
    }

    public T pop() {
        Node node = first;
        T t = node.t;
        first = node.next;
        n--;
        return t;
    }

    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        //当前节点
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
        Stack<String> stack = new Stack<>();
        stack.push("rick");
        stack.push("Tom");
        stack.push("Doris");
        System.out.println(stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.pop());


        Iterator<String> iterator = stack.iterator();
        System.out.println("栈数据：");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
