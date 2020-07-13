package com.rick.struction.search;

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
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value value) {
        if (h == null)
            return new Node(key, value, 1, RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0)
            h.left = put(h.left, key, value);
        else if (cmp > 0)
            h.right =  put(h.right, key, value);
        else
            h.value = value;
        return balance(h);
    }

    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * 使用递归查找key所对应的value
     * @param x
     * @param key
     * @return
     */
    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else
            return x.value;
    }

    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if (!isEmpty())
            root.color = BLACK;
    }

    /**
     * 为了删除一个节点，该方法不仅要在构造临时4-节点时
     * 沿着查找路径向下进行变换，还要在分解遗留的4-节点时
     * 沿着查找路径向上进行变化（平衡二叉查找树的过程）
     * @param h
     * @return
     */
    private Node deleteMin(Node h) {
        if (h.left == null) return null;
        //判断当前节点的左节点是否为2-节点
        if (!isRed(h.left) && !isRed(h.left.left))
            //即当左子节点为2-节点的时候执行这个方法
            h = moveRedLeft(h);
        h.left = deleteMin(h.left);
        return balance(h);
    }

    /**
     * 处理2-节点
     * @param h
     * @return
     */
    private Node moveRedLeft(Node h) {
        //将当前节点设为黑色，子节点为红。
        //假设h节点为红色，h.right和h.right.lect都为黑色
        //将h.left或者h.left子节点之一变为红色
        flipColors(h);
        //if兄弟节点为非2-节点，在兄弟节点(即右子节点)那里借一个节点过来。
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    /**
     * 删除最大值
     */
    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMax(root);
        if (!isEmpty())
            root.color = BLACK;
    }

    /**
     * 递归删除
     * @param h
     * @return
     */
    private Node deleteMax(Node h) {
        if (isRed(h.left)) h = rotateRight(h);
        if (h.right == null) return null;
        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);
        h.right = deleteMax(h.right);
        return balance(h);
    }

    /**
     * 处理2-节点
     * @param h
     * @return
     */
    private Node moveRedRight(Node h) {
        //假设h节点为红色，h.right和h.right.lect都为黑色
        //将h.right或者h.right子节点之一变为红色
        flipColors(h);
        //在兄弟节点中借节点
        if (!isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    public void delete(Key key) {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = delete(root, key);
        if (!isEmpty())
            root.color = BLACK;
    }

    private Node delete(Node h, Key key) {
        if (h == null) return null;
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && h.right == null)
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                h.value = get(h.right, min(h.right).key);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);

            } else {
                h.right = delete(h.right, key);
            }
        }
        return balance(h);
    }

    /**
     * 修复红黑树。
     * @param h
     * @return
     */
    private Node balance(Node h) {
        if (isRed(h.right))
            h = rotateLeft(h);
        //红色右链接(左链接不为红色)的左旋
        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        //连续出现两个红链接，右旋
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        //两个子节点都为红链接
        if (isRed(h.left) && isRed(h.right))
            flipColor(h);
        h.count = size(h.left) + size(h.right) + 1;
        return h;
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
     * 用于删除操作
     * @param x
     */
    private void flipColors(Node x) {
        x.color = BLACK;
        if (x.left != null)
            x.left.color = RED;
        if (x.right != null)
            x.right.color = RED;
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

    public boolean isEmpty() {
        return root == null;
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

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }



}
