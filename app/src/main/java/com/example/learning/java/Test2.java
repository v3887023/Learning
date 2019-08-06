package com.example.learning.java;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test2 {
    private static final int MAX_SLEEP_TIME = 2000;
    private static final int MAX_SIZE = 3;
    private static Random random;
    private static Object fruit;
    private static boolean isApple;

    public static void main(String[] args) {
        Object plate = new Object();
        random = new Random();

        Father father = new Father("father", plate);
        Girl girl = new Girl("girl", plate);
        Boy boy = new Boy("boy", plate);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(father);
        executorService.execute(girl);
        executorService.execute(boy);
    }

    private static void println(String message) {
        System.out.println(message);
    }

    private static <T> void printList(List<T> list) {
        System.out.println(list);
    }

    static class Father implements Runnable {
        private final Object plate;
        private String name;

        Father(String name, Object plate) {
            this.name = name;
            this.plate = plate;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(random.nextInt(MAX_SLEEP_TIME));

                    synchronized (plate) {
                        if (fruit == null) {
                            int n = random.nextInt(100);
                            fruit = new Object();
                            isApple = n % 2 == 0;
                            plate.notifyAll();
                            println("***************");
                            println(name + " put an " + (isApple ? "apple" : "orange"));
                        } else {
                            plate.wait();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Girl implements Runnable {
        private final Object plate;
        private String name;

        Girl(String name, Object plate) {
            this.name = name;
            this.plate = plate;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(random.nextInt(MAX_SLEEP_TIME));

                    synchronized (plate) {
                        if (fruit == null) {
                            plate.wait();
                        } else {
                            if (isApple) {
                                fruit = null;
                                println(name + " get an " + "apple");
                                println("");
                            }
                            plate.notifyAll();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Boy implements Runnable {
        private final Object plate;
        private String name;

        Boy(String name, Object plate) {
            this.name = name;
            this.plate = plate;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(random.nextInt(MAX_SLEEP_TIME));

                    synchronized (plate) {
                        if (fruit == null) {
                            plate.wait();
                        } else {
                            if (!isApple) {
                                fruit = null;
                                println(name + " get an " + "orange");
                                println("");
                            }
                            plate.notifyAll();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

