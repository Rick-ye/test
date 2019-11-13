package com.rick.sort.heap;

import com.rick.collection.queue.Transaction;

import java.util.Date;

public class Test {

    @org.junit.Test
    public void testComparable() {
        int n = 11;
        MaxPQ pq = new MaxPQ(n);
        for (int i = 0; i < n; i++) {
            pq.insert(MaxPQ.randomChar());
        }
        pq.show();
        System.out.println(pq.delMax());
        pq.show();
    }

    @org.junit.Test
    public void testComparator() {
        int n = 11;
        MaxPQ<Transaction> pq = new MaxPQ<>(n + 1, new Transaction.AmountOrder());
        for (int i = 1; i < n+1; i++) {
            Transaction transaction = new Transaction("str" + i, new Date(), Double.valueOf(i));
            pq.insert(transaction);
        }
        for (int i = 1; i < n+1; i++) {
            System.out.println(pq.delMax().getWho());
        }
        pq.show();
    }


}
