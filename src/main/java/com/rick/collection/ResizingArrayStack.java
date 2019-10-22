package com.rick.collection;

import java.util.Iterator;

/**
 * 下压栈(简称栈)是一种后进先出策略的集合类型
 * 该算法可以实现数组的自动扩缩容
 *
 * 优点：它几乎（但还没有）达到了任意集合类数据类型的实现的最佳性能
 * 1.每项操作的用时都与集合大小无关。
 * 2.空间需求总是不超过集合大小乘以一个常数
 * 缺陷：在调用pop和push方法时会调整数组大小，数组的复制比较消耗内存，这项操作的耗时和栈大小成正比。
 *
 * 技术点：
 * 1.泛型
 * 2.对象游离：在pop()方法中，被弹出的元素的引用仍然存在于数组中。实际上，这个元素已经是一个
 *   孤儿了，因为他永远不会被访问了，但是java的垃圾回收器并不知道，除非该引用被覆盖。
 *   即使这个元素已经不在不需要，但是数组中的引用仍然可以让他继续存在。
 * 3.迭代
 *
 * 总结：这份可迭代的泛型的stack API的实现是所有集合类抽象数据类型实现的模板
 * @param <T>
 */
public class ResizingArrayStack<T> implements Iterable<T> {

    //泛型数组
    private T[] arr = (T[]) new Object[1];

    //数组大小
    private int n = 0;

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 向栈中添加数据
     * @param t
     * @return
     */
    public void push(T t) {
        if (n == arr.length)
            resize(arr.length * 2);
        arr[n++] = t;
    }

    public T pop() {
        T t = arr[--n];
        arr[n] = null;  //避免对象游离（游离：保存一个不需要的对象的引用）
        if (n > 0 && n == arr.length / 4)
            resize(arr.length/2);
        return t;
    }


    /**
     * 动态扩缩容数组
     * @param newSize
     */
    private void resize(int newSize) {
        T[] t = (T[]) new Object[newSize];
        for (int i = 0; i < arr.length; i++) {
            t[i] = arr[i];
        }
        arr = t;
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    /**
     * 迭代器：就是一个实现了next()和hasNext()方法的类的对象
     */
    private class ReverseArrayIterator implements Iterator<T> {

        private int i = n;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return arr[--i];
        }

        @Override
        public void remove() {

        }
    }

    public static void main(String[] args) {
        ResizingArrayStack<String> strings = new ResizingArrayStack<>();
        strings.push("rick");
        strings.push("tom");
        strings.push("siri");
        System.out.println("栈大小：" + strings.size());
        System.out.println("弹出栈的第一个数据：" + strings.pop());
        System.out.println("栈大小：" + strings.size());
        System.out.println("栈是否为空：" + strings.isEmpty());
        Iterator<String> iterator = strings.iterator();
        System.out.println("迭代栈：");
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
    }
}
