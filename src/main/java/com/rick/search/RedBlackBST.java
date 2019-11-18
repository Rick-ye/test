package com.rick.search;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.regexp.internal.RE;

import java.util.Random;

/**
 * 平衡查找树（红黑二叉查找树，简称红黑树）
 *
 * # 红链接均为左连接；
 * # 没有任何一个节点同时和两条红链接相连；
 * # 该树是完全黑色平衡的，即任意空链接到根节点的路径上的黑链接数量相等
 *
 * @param <Key>
 * @param <Value>
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private Node root;

    //标记红黑树链接的颜色
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int count; //这颗子树中的总结点数
        private boolean color; //新增属性

        public Node(Key key, Value value, int count, boolean color) {
            this.key = key;
            this.value = value;
            this.count = count;
            this.color = color;
        }
    }


    public void put(Key key, Value value) {
        put(root, key, value);
    }

    private Node put(Node h, Key key, Value value) {
        if (h == null)
            return new Node(key, value, 1, RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0) return put(h.left, key, value);
        else if (cmp > 0) return put(h.right, key, value);
        else h.value = value;

        //连续出现两个红链接，右旋
        if (isRed(h.left) && isRed(h.left.left))
            rotateRight(h);
        //红色右链接(左链接不为红色)的左旋
        if (isRed(h.right) && !isRed(h.left))
            rotateLeft(h);
        //两个字节点都为红链接
        if (isRed(h.left) && isRed(h.right))
            flipColor(h);
        h.count = size(h.left) + size(h.right) + 1;
        return h;
    }

    public void deleteMin() {
        deleteMin(root);
    }

    private Node deleteMin(Node h) {
        if (h == null) return null;
        if (isRed(h.left))
            return h.left;

        return null;
    }

    /**
     * 当h右链接为红色时，需要左旋，将红链接左旋到左链接
     * @param h
     * @return
     */
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.count = h.count;
        h.count = size(h.left) + size(x.right) + 1;
        return x;
    }

    /**
     *
     * @param h
     * @return
     */
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.count = h.count;
        h.count = size(h.right) + size(h.left) + 1;
        return x;
    }


    /**
     * 将一个节点的两个子节点由红变黑，同时该节点也由黑变红
     * @param x
     */
    private void flipColor(Node x) {
        x.color = RED;
        x.left.color = BLACK;
        x.right.color = BLACK;
    }

    /**
     * 总结点数
     * @return
     */
    public int size() {
        return size(root);
    }

    /**
     * 计算当前子树中的总节点数
     * @param x
     * @return
     */
    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.count;
    }

    /**
     * 判断一个节点和它的父节点是否为红链接
     * （当提到一个节点的颜色时，通常指的是指向该节点的链接的颜色）
     * @param x
     * @return
     */
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

}
