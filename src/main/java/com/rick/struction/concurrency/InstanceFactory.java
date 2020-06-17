package com.rick.struction.concurrency;

public class InstanceFactory {
    private static class InstanceHolder {
        public static InstanceFactory i = new InstanceFactory();
    }
    public static InstanceFactory getInstance() {
        return InstanceHolder.i;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            A a = new A();
            a.start();

        }
    }

    static class A extends Thread {
        @Override
        public void run() {
            System.out.println(getInstance());
        }
    }
    static class B extends Thread {
        @Override
        public void run() {
            getInstance();
        }
    }
    static class C extends Thread {
        @Override
        public void run() {
            getInstance();
        }
    }
}
