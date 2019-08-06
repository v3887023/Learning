package com.example.learning.java;

import java.util.concurrent.TimeUnit;

import static com.example.learning.java.Utils.print;

public class Test28 {
    public static void main(String[] args) {
//        Utils.println(a());

        final byte[] bytes = intToBytes(7233);
        int n = bytesToInt(bytes);
        Utils.println();
        Utils.println(n);

        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();
    }

    private static int a() {
        try {
            a(1);
        } finally {
            return a(3);
        }
    }

    private static byte[] intToBytes(int n) {
        byte[] bytes = new byte[4];

        bytes[3] = (byte) (n & 0xFF);

        n >>= 8;
        bytes[2] = (byte) (n & 0xFF);

        n >>= 8;
        bytes[1] = (byte) (n & 0xFF);

        n >>= 8;
        bytes[0] = (byte) (n & 0xFF);

        for (byte b : bytes) {
            print(b);
        }

        return bytes;
    }

    public static int bytesToInt(byte[] bytes) {
        return (bytes[3] & 0xff) | ((bytes[2] & 0xff) << 8) | ((bytes[1] & 0xff) << 16) | ((bytes[0] & 0xff) << 24);
    }

    private static int a(int n) {
        Utils.println("n = " + n);
        return n;
    }

    static class ADaemon implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("Starting ADaemon");
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("run?");
            }
        }
    }
}
