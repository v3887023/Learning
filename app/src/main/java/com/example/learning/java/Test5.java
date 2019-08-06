package com.example.learning.java;

import java.util.concurrent.atomic.AtomicInteger;

public class Test5 {
    private static final int THREAD_COUNT = 20;
    private static final AtomicInteger ai = new AtomicInteger();
    private volatile static int n = 0;

    private synchronized static void volatileIncrease() {
        n++;
    }

    private static void atomicIncrease() {
        ai.getAndIncrement();
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new VolatileCounterRunnable());
            threads[i].start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        println("---------volatile---------");
        println("sum is " + n);

        long end = System.currentTimeMillis();

        println("run time : " + (end - start) + "ms");
        println("");

        start = System.currentTimeMillis();
        threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new AtomicCounterRunnable());
            threads[i].start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        println("---------atomic---------");
        println("sum is " + ai);

        end = System.currentTimeMillis();

        println("run time : " + (end - start) + "ms");
    }

    private static void println(String message) {
        System.out.println(message);
    }

    private static class VolatileCounterRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                volatileIncrease();
            }
        }
    }

    private static class AtomicCounterRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                atomicIncrease();
            }
        }
    }
}
