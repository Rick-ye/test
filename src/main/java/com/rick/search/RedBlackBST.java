package com.rick.search;

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

    /**
     * 当h右链接为红色时，需要左旋，将红链接左旋到左链接
     * @param h
     * @return
     */
    public Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.count = h.count;
        h.count = size(h.left);
        return x;
    }

    /**
     *
     * @param h
     * @return
     */
    public Node rotateRight(Node h) {
        Node x = h.left;

        return x;
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
