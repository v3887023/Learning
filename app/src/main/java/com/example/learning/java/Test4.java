package com.example.learning.java;

public class Test4 {
    private final static Object lock = new Object();
    private static final int COUNT = 1;
    private static boolean canCountNumber = true;

    public static void main(String[] args) {
        CounterNumber c1 = new CounterNumber("number");
        CounterLetter c2 = new CounterLetter("letter");

        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);

        t1.start();
        t2.start();
    }

    private static void println(String message) {
        System.out.println(message);
    }
    private static void print(int i) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(i + " ");
    }

    private static void print(char c) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(c+ " ");
    }

    private static class CounterNumber implements Runnable {
        private int i = 1;
        private String name;

        public CounterNumber(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            synchronized (lock) {
                while (i <= 52) {
                    if (canCountNumber) {
                        int end = i + COUNT;
                        for (; i < end; i++) {
                            print(i);
                        }
                        canCountNumber = false;
                        try {
                            lock.wait();
                            lock.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private static class CounterLetter implements Runnable {
        private char c = 'A';
        private String name;

        public CounterLetter(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            synchronized (lock) {
                while (c <= 'z') {
                    if (canCountNumber) {
                        continue;
                    }
                    char end = (char) (c + COUNT);
                    for (; c < end; c++) {
                        if (Character.isAlphabetic(c)) {
                            print(c);
                        } else {
                            end++;
                        }
                    }
                    canCountNumber = true;
                    try {
                        lock.notifyAll();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
