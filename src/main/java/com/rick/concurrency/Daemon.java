package com.rick.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 当java虚拟机中不存在非daemon线程时，java虚拟机会自动退出。
 * 通过如下测试发现，当主线程执行完成，java虚拟机将立刻退出，daemon线程立即终止，将不会在执行finally语句块
 */
public class Daemon {
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunnable(), "DaemonRunnable");
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunnable implements Runnable {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Daemon finally run.");
            }
        }
    }
}
