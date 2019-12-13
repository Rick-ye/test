package com.rick.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    int a = 0 ;
    ReentrantLock lock = new ReentrantLock();

    void writer() {
        lock.lock();
        try {
            Thread.sleep(1000);
            a++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void reader() {
        lock.lock();
        try {
            int i = a;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            new Thread(new WriterTask()).start();
            new Thread(new ReaderTask()).start();
        }


    }

    static class WriterTask implements Runnable {

        @Override
        public void run() {
            new ReentrantLockExample().writer();
        }
    }
    static class ReaderTask implements Runnable {

        @Override
        public void run() {
            new ReentrantLockExample().reader();
        }
    }
}
