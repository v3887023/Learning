package com.example.learning;

import com.example.learning.java.Utils;

public class C2 {
    private static  boolean run = true;

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stop();
        }).start();

        while (isRun()) {

        }

        Utils.println("123");
    }

    private synchronized static boolean isRun() {
        return run;
    }

    private  static void stop() {
        run = false;
    }
}