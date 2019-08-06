package com.example.learning.java;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import static com.example.learning.java.Utils.println;

public class Test10 {
    private static final int MAX_SLEEP_TIME = 2000;
    private static final int MAX_SIZE = 3;
    private static Random random;

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(MAX_SIZE);
        random = new Random();

        Producer p1 = new Producer("p", queue);
        Producer p2 = new Producer("p2", queue);
        Consumer c1 = new Consumer("c", queue);
        Consumer c2 = new Consumer("c2", queue);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(p1);
//        executorService.execute(p2);
        executorService.execute(c1);
//        executorService.execute(c2);
    }

    static class Producer implements Runnable {
        private final BlockingQueue<Integer> queue;
        private String name;

        Producer(String name, BlockingQueue<Integer> queue) {
            this.name = name;
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(random.nextInt(MAX_SLEEP_TIME));

                    queue.put(produce());

                    println("*********");
                    println(queue);
                    println("*********");
                    println();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private Integer produce() {
            int t = random.nextInt(100);
            println(name + " <- " + t);

            return t;
        }
    }

    static class Consumer implements Runnable {
        private final BlockingQueue<Integer> queue;
        private String name;

        Consumer(String name, BlockingQueue<Integer> queue) {
            this.name = name;
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(random.nextInt(MAX_SLEEP_TIME));

                    consume(queue.take());

                    println("*********");
                    println(queue);
                    println("*********");
                    println();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void consume(Integer t) {
            println(name + " -> " + t);
        }
    }
}
