package com.example.learning.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test3 {
    private static final int MAX_SLEEP_TIME = 2000;
    private static final int MAX_SIZE = 3;
    private static Random random;
    //    private static Fruit fruit;
    private static List<Fruit> fruits = new ArrayList<>(MAX_SIZE);

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

    static class Fruit {
    }

    static class Apple extends Fruit {
        @Override
        public String toString() {
            return Apple.class.getSimpleName();
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Apple;
        }
    }

    static class Orange extends Fruit {
        @Override
        public String toString() {
            return Orange.class.getSimpleName();
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Orange;
        }
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
                        if (fruits.size() >= MAX_SIZE) {
                            plate.wait();
                        } else {
                            int n = random.nextInt(100);
                            boolean isApple = n % 2 == 0;
                            Fruit fruit = isApple ? new Apple() : new Orange();

                            if (isApple) {
                                println(name + " put an apple");
                            } else {
                                println(name + " put an orange");
                            }
                            fruits.add(fruit);
                            printList(fruits);
                            println("");

                            plate.notifyAll();
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
                        int i = fruits.indexOf(new Apple());
                        if (i < 0 || fruits.size() == 0) {
                            plate.wait();
                        } else {
                            fruits.remove(i);
                            println(name + " get an " + "apple");
                            printList(fruits);
                            println("");
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
                        int i = fruits.indexOf(new Orange());
                        if (i < 0 || fruits.size() == 0) {
                            plate.wait();
                        } else {
                            fruits.remove(i);
                            println(name + " get an " + "orange");
                            printList(fruits);
                            println("");
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

