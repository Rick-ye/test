package com.rick.lock;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) {

    }

    static class Wait implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + "flag is true. wait @" +
                                new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + "flag is false. wait @" +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));


            }
        }
    }
}
