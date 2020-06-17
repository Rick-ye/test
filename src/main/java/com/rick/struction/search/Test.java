package com.rick.struction.search;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.util.Iterator;

public class Test {

    /**
     * 测试有序数组二分查找
     * 插入极其慢，但是查询效率很高
     * n = 1000000
     */
    @org.junit.Test
    public void testBinary() throws InterruptedException {
        String[] a = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E", "Z"};
        BinarySearchST<String, Integer> bsst = new BinarySearchST<>(a.length);
        for (int i = 0; i < a.length; i++) {
            bsst.put(a[i], i);
        }
        System.out.println(bsst.get("F"));
        Iterable<String> keys = bsst.keys();
        Iterator<String> it = keys.iterator();
        while (it.hasNext())
            System.out.print(it.next());
    }

//        System.out.println("before put");
//        StopWatch watch = new StopWatch();
//        watch.start();
//        for (int i = 0; i < n; i++) {
//            bsst.put(RandomStringUtils.randomAlphanumeric(5), i);
//        }
//        watch.split();
//        System.out.println("put time: " + watch.getSplitTime());
//        System.out.println(bsst.get(bsst.select(3005)));
//        System.out.println("search time: "+watch.getTime());
//    }

    /**
     * 测试无序链表
     * 插入慢
     * 查找看数据量的大小（十万和二分查找差不多）
     */
    @org.junit.Test
    public void testSequential() {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        String[] a = {"S","E","A","R","C","H","E","X","A","M","P","L","E","Z"};
        for (int i = 0; i < a.length; i++) {
            st.put(a[i], i);
        }
        System.out.println(st.get("E"));
    }

//        System.out.println(st.size());
//        st.delete("H");
//        System.out.println(st.size());
//        StopWatch watch = new StopWatch();
//        watch.start();
//        for (int i = 0; i < n; i++) {
//            st.put(RandomStringUtils.randomAlphanumeric(5), i);
//        }
//        watch.split();
//        System.out.println("put time: " + watch.getSplitTime());
//        System.out.println(st.get("fdksf"));
//        System.out.println("search time: "+watch.getTime());
        /*Iterable<String> keys = st.keys();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Integer value = st.get(key);
            System.out.println(key + ": "+ value);
        }*/



    @org.junit.Test
    public void testBST() {
        int n = 1000000;
        BST<String, Integer> bst = new BST<>();
//        String[] a = {"S","E","A","R","C","H","E","X","A","M","P","L","E","Z"};
////        for (int i = 0; i < a.length; i++) {
////            bst.put(a[i], i);
////        }
        StopWatch watch = new StopWatch();
        watch.start();
        for (int i = 0; i < n; i++) {
            bst.put(RandomStringUtils.randomAlphanumeric(5), i);
        }
        watch.split();
        System.out.println("put time: " + watch.getSplitTime());
//        Iterable<String> keys = bst.keys("E","U");
//        Iterator<String> it = keys.iterator();
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }

        System.out.println(bst.max());
        //System.out.println(bst.floor("Q"));
        System.out.println(bst.ceiling("B"));
        System.out.println(bst.select(6));
        //bst.deleteMax();
        bst.deleteMin();

        System.out.println(bst.get("A"));
        System.out.println(bst.get("X"));
    }

    @org.junit.Test
    public void testRedBlack() {
        int n = 1000000;
        RedBlackBST<String, Integer> rb = new RedBlackBST<>();
//        StopWatch watch = new StopWatch();
//        watch.start();
//        for (int i = 0; i < n; i++) {
//            rb.put(RandomStringUtils.randomAlphanumeric(5), i);
//        }
//        watch.split();
//        System.out.println("put time: " + watch.getSplitTime());
//        System.out.println(rb.get("fdksf"));
//        System.out.println("search time: "+watch.getTime());
        String[] a = {"S","E","A","R","C","H","E","X","A","M","P","L","E","Z"};
 //       String[] a = {"A","B","C"};
        for (int i = 0; i < a.length; i++) {
            rb.put(a[i], i);
        }
        rb.delete("A");
        rb.deleteMin();
        rb.deleteMax();
    }

    @org.junit.Test
    public void testChainingHash() {
        String[] a = {"S","E","A","R","C","H","E","X","A","M","P","L","E","Z"};
        SeparateChainingHashST<Object, Object> st = new SeparateChainingHashST<>(15);
        for (int i = 0; i < a.length; i++) {
            st.put(a[i], i);
        }
        System.out.println(st.size());
        st.delete("H");
        System.out.println(st.get("H"));
        Iterable<Object> keys = st.keys();
        Iterator<Object> it = keys.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println(st.size());
    }

    @org.junit.Test
    public void testProbingHash() {
        String[] a = {"S","E","A","R","C","H","E","X","A","M","P","L","E","Z"};
        LinearProbingHashST<Object, Object> st = new LinearProbingHashST<>(15);
        for (int i = 0; i < a.length; i++) {
            st.put(a[i], i);
        }
        System.out.println(st.size());
        st.delete("H");
        System.out.println(st.get("H"));
        System.out.println(st.get("A"));
        System.out.println(st.size());
        st.delete("A");
        Iterable<Object> keys = st.keys();
        Iterator<Object> it = keys.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public static void main(String[] args) {
        Integer i = 3;
        String s = "ab";
        System.out.println(i.hashCode());
        System.out.println(s.hashCode());
    }

}

























