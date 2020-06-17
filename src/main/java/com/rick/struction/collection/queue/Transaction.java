package com.rick.struction.collection.queue;

import java.util.Comparator;
import java.util.Date;

public class Transaction implements Comparable<Transaction> {
    private final String who;
    private final Date when;
    private final Double amount;
    public Transaction(String who, Date when, Double amount) {
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    @Override
    public int compareTo(Transaction o) {
        return 0;
    }

    public static class WhoOrder implements Comparator<Transaction> {

        @Override
        public int compare(Transaction o1, Transaction o2) {
            return o1.who.compareTo(o2.who);
        }
    }

    public static class WhenOrder implements Comparator<Transaction> {

        @Override
        public int compare(Transaction o1, Transaction o2) {
            return o1.when.compareTo(o1.when);
        }
    }

    public static class AmountOrder implements Comparator<Transaction> {

        @Override
        public int compare(Transaction o1, Transaction o2) {
            //return o1.amount.compareTo(o2.amount);
            if (o1.amount < o2.amount) return -1;
            if (o1.amount > o2.amount) return 1;
            return 0;
        }
    }

    public String getWho() {
        return who;
    }

    public Date getWhen() {
        return when;
    }

    public Double getAmount() {
        return amount;
    }
}
