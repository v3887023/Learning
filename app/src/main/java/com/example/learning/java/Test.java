package com.example.learning.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    private static final int MAX_SLEEP_TIME = 2000;
    private static final int MAX_SIZE = 3;
    private static Random random;

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        random = new Random();

        Producer p1 = new Producer("p", list);
        Producer p2 = new Producer("p2", list);
        Consumer c1 = new Consumer("c", list);
        Consumer c2 = new Consumer("c2", list);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(p1);
//        executorService.execute(p2);
        executorService.execute(c1);
//        executorService.execute(c2);
    }

    private static void println(String message) {
        System.out.println(message + "\n");
    }

    private static <T> void printList(List<T> list) {
        System.out.println(list);
    }

    static class Producer implements Runnable {
        private final List<Integer> list;
        private String name;

        Producer(String name, List<Integer> list) {
            this.name = name;
            this.list = list;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(random.nextInt(MAX_SLEEP_TIME));

                    synchronized (list) {
                        println(name + " get the chance to run");
                        if (list.size() >= MAX_SIZE) {
                            println("list is full, " + name + " wait");
                            list.wait();
                            println(name + " wake up");
                        } else {
                            int n = random.nextInt(100);
                            list.add(n);
                            list.notifyAll();
                            println(name + " <-: " + n);
                            printList(list);
                        }
                    }
                    println(name + " out of synchronized block");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer implements Runnable {
        private final List<Integer> list;
        private String name;

        Consumer(String name, List<Integer> list) {
            this.name = name;
            this.list = list;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(random.nextInt(MAX_SLEEP_TIME));

                    synchronized (list) {
                        println(name + " get the chance to run");
                        if (list.size() == 0) {
                            println("list is empty, " + name + " wait");
                            list.wait();
                            println(name + " wake up");
                        } else {
                            int n = list.remove(0);
                            println(name + " ->: " + n);
                            printList(list);
                            list.notifyAll();
                        }
                    }
                    println(name + " out of synchronized block");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

