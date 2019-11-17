package com.rick.search;

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
        int n = 10000;
        BinarySearchST<String, Integer> bsst = new BinarySearchST<>(2*n);
        System.out.println("before put");
        StopWatch watch = new StopWatch();
        watch.start();
        for (int i = 0; i < n; i++) {
            bsst.put(RandomStringUtils.randomAlphanumeric(5), i);
        }
        watch.split();
        System.out.println("put time: " + watch.getSplitTime());
        System.out.println(bsst.get(bsst.select(3005)));
        System.out.println("search time: "+watch.getTime());
    }

    /**
     * 测试无序链表
     * 插入慢
     * 查找看数据量的大小（十万和二分查找差不多）
     */
    @org.junit.Test
    public void testSequential() {
        int n = 100000;
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        StopWatch watch = new StopWatch();
        watch.start();
        for (int i = 0; i < n; i++) {
            st.put(RandomStringUtils.randomAlphanumeric(5), i);
        }
        watch.split();
        System.out.println("put time: " + watch.getSplitTime());
        System.out.println(st.get("fdksf"));
        System.out.println("search time: "+watch.getTime());
        /*Iterable<String> keys = st.keys();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Integer value = st.get(key);
            System.out.println(key + ": "+ value);
        }*/
    }


    @org.junit.Test
    public void testBST() {
        BST<String, Integer> bst = new BST<>();
        String[] a = {"S","E","A","R","C","H","E","X","A","M","P","L","E","Z"};
        for (int i = 0; i < a.length; i++) {
            bst.put(a[i], i);
        }

        Iterable<String> keys = bst.keys("E","U");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println(bst.max());
        //System.out.println(bst.floor("Q"));
        System.out.println(bst.ceiling("B"));
        System.out.println(bst.select(6));
        //bst.deleteMax();
        bst.deleteMin();

        System.out.println(bst.get("A"));
        System.out.println(bst.get("X"));
    }


}

























