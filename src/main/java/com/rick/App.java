package com.rick;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {
        /*final ThreadLocal<Integer> local = new ThreadLocal<>();
        final ThreadLocal<String> str = new ThreadLocal<>();
        local.set(10);
        local.set(20);
        new Thread() {
            @Override
            public void run() {
                local.set(30);
                System.out.println(local.get());
//                local.remove();
//                System.out.println(local.get());
                str.set("rick");
                System.out.println(str.get());
            }
        }.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println( local.get() );*/
        System.out.println(16 * 2/3);

    }
}
