package com.rick.search;

import java.util.Iterator;

/**
 * 无序链表的顺序查找
 * @param <Key> 键
 * @param <Value> 值
 */
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

    /**
     * 查找value
     * @param key
     * @return
     */
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.value;
        }
        return null;
    }

    /**
     * 存储key，value键值对，如果key值相同则覆盖
     * @param key
     * @param value
     */
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

    /**
     * 删除操作
     * @param key
     */
    public void delete(Key key) {
        //判断第一个key是否为目标key相同
        //相同则直接返回，否则进入for循环
        if (first.key.equals(key)) {
            first = first.next;
            n--;
            return;
        }
        //for循环从第二个节点开始进行判断
        for (Node x = first; x != null; x = x.next) {
            Node next = x.next;
            if (next != null && key.equals(next.key)) {
                x.next = next.next;
                n--;
                return;
            }

        }
    }

    /**
     * 返回一个迭代器
     * @return
     */
    public Iterable<Key> keys() {
        return new KeysIterable();
    }

    /**
     * 实现一个迭代器
     */
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

}
