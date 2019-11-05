package com.rick.sort;

import com.rick.sort.merge.MergeSort1;
import com.rick.sort.merge.MergeSort2;
import org.apache.commons.lang3.time.StopWatch;

/**
 * N = 10000,T=1000
 * Selection: 68991.0
 * Shell: 1160.0
 * Merge1: 1102.0
 * Merge2: 1060.0
 *
 * Insertion: 81694.0
 * Shell: 1149.0
 * Merge1: 1158.0
 * Merge2: 1041.0
 */
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
        if ("Merge1".equals(alg))
            new MergeSort1().sort(a);
        if ("Merge2".equals(alg))
            new MergeSort2().sort(a);
        if ("Quick".equals(alg))
            new QuickSort().sort(a);

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
        String alg4 = "Merge1";
        String alg5 = "Merge2";
        String alg6 = "Quick";
        int N = Integer.parseInt("10000");
        int T = Integer.parseInt("100");
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        /*double t3 = timeRandomInput(alg3, N, T);
        double t4 = timeRandomInput(alg4, N, T);
        double t5 = timeRandomInput(alg5, N, T);
        double t6 = timeRandomInput(alg6, N, T);*/

        System.out.println("Insertion: " + t1);
        System.out.println("Selection: " + t2);

        System.out.println("times: " + t2/t1);
        /*System.out.println("Shell: " + t3);
        System.out.println("Merge1: " + t4);
        System.out.println("Merge2: " + t5);
        System.out.println("Quick: " + t6);*/
    }
}
