package com.rick.sort;

import org.apache.commons.lang3.time.StopWatch;

public class SortCompare {

    public static double time(String alg, Comparable[] a) {
        StopWatch watch = new StopWatch();
        watch.start();
        if ("Insertion".equals(alg))
            new InsertionSort().sort(a);
        if ("Selection".equals(alg))
            new SelectionSort().sort(a);
        if ("Shell".equals(alg))
            new ShellSort().sort(a);

        return watch.getTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0d;
        Double[] a = new Double[N];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < N; j++) {
                a[j] = Math.random();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = "Insertion";
        String alg2 = "Selection";
        String alg3 = "Shell";
        int N = Integer.parseInt("100");
        int T = Integer.parseInt("10000");
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        double t3 = timeRandomInput(alg3, N, T);

        System.out.println("Insertion: " + t1);
        System.out.println("Selection: " + t2);
        System.out.println("Shell: " + t3);
    }
}
