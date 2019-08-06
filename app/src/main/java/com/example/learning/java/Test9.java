package com.example.learning.java;

import static com.example.learning.java.Utils.println;

public class Test9 {
    private static int n;
    private static Thread t;
    private static volatile boolean run = true;

    public static void main(String[] args) {
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                long end = 0;
                while (run && end - start < 3000) {
                    end = System.currentTimeMillis();
                }
                println("中断了");
            }
        });

        t.start();

        try {
            Thread.sleep(1000);
            run = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
