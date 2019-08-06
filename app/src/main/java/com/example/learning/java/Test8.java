package com.example.learning.java;

import static com.example.learning.java.Utils.println;

public class Test8 {
    static Thread t;
    private static int n;

    public static void main(String[] args) {
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!t.isInterrupted()) {

                }
                println("中断了");

                while (!t.isInterrupted()) {

                }
                println("又中断了");
            }
        });

        t.start();
        try {
            Thread.sleep(1000);
            t.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
