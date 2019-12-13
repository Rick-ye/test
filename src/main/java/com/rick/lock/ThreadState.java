package com.rick.lock;

import java.util.concurrent.TimeUnit;


public class ThreadState {

    public static void main(String[] args) {
        //TIME_WAITING 超时等待状态，不同于WAITING，他是可以在指定时间自行返回的。
        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        //WAITING 等待状态，进入该状态表示当前线程需要等待其他线程做出一些特定动作（通知或中断）
        new Thread(new Waiting(), "WaitingThread").start();
        //抢到锁的线程进入TIME_WAITING状态，没有抢到锁的进入BLOCK状态
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();
    }

    static class TimeWaiting implements Runnable {

        @Override
        public void run() {
            //while (true) {
                try {
                    TimeUnit.SECONDS.sleep(30);
                    while (true) {

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            //}
        }
    }

    static class Waiting implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable {

        @Override
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
