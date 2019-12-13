package com.rick.lock;

public class FinalReferenceExample {
    final int[] intArray;
    static FinalReferenceExample obj;

    public FinalReferenceExample() {
        intArray = new int[1];
        intArray[0] = 1;
    }

    public static void writerOne() {
        obj = new FinalReferenceExample();
    }

    public static void writerSecond() {
        obj.intArray[0] = 2;
    }

    public static void reader() {
        int temp = obj.intArray[0];
        System.out.println(temp);
    }

    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        B b = new B();
        C c = new C();

        a.start();
//        a.join();
        b.start();
//        b.join();
        c.start();
    }

    static class A extends Thread {
        @Override
        public void run() {
            writerOne();
        }
    }
    static class B extends Thread {
        @Override
        public void run() {
            writerSecond();
        }
    }
    static class C extends Thread {
        @Override
        public void run() {
            reader();
        }
    }
}
