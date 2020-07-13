package com.rick.struction.concurrency;

import org.apache.commons.lang3.time.StopWatch;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

public class Lock {

    private static Unsafe unsafe;
    private static long valueOffSet;

    private static volatile int value = 0;

    static {
        try {
            @SuppressWarnings("ALL")
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            valueOffSet = unsafe.staticFieldOffset(Lock.class.getDeclaredField("value"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(Lock.class, valueOffSet, expect, update);
    }

    private final int get() {
        return value;
    }

    private int incrementAndGet() {
        for (;;){
            int current = get();
            int next = current + 1;
            //System.out.println("Failed Thread: " + Thread.currentThread().getName());
            if (compareAndSet(current, next)) {
//                System.out.println("Success Thread" + Thread.currentThread().getName());
                return next;
            }
        }
    }


    private static int syn = 0;

    private static Object lock = new Object();

    private synchronized static int getSyn() {
        return ++syn;
    }

    public static void main(String[] args) throws InterruptedException {
        StopWatch watch = new StopWatch();
        int count = 50;
        watch.start();
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {

            new Thread(new Task(countDownLatch)).start();

        }
        countDownLatch.await();
//        while (Thread.activeCount() > 1) {
//            Thread.yield();
//        }
        System.out.println("total time: " + watch.getTime());
        System.out.println("value: " + value);
        System.out.println("syn: " + syn);
    }

    static class Task implements Runnable {

        private CountDownLatch count;

        public Task(CountDownLatch count) {
            this.count = count;
        }

        @Override
        public void run() {
            for (int j = 0; j < 100000; j++) {

                new Lock().incrementAndGet();
                //getSyn();
            }
            count.countDown();
        }

    }


}

