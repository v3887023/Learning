package com.example.learning.java;

import static com.example.learning.java.Utils.println;

public class Test13 {
    public static void main(String[] args) {
        int n = 128;
        final byte[] bytes = intToBytes(n);

        println(byteToBitsString(bytes[3]));

    }

    private static String intToBitsString(int n) {
        StringBuilder builder = new StringBuilder();

        final byte[] bytes = intToBytes(n);
        for (byte aByte : bytes) {
            builder.append(byteToBitsString(aByte)).append(' ');
        }

        return builder.toString();
    }

    private static String byteToBitsString(byte b) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            builder.append((b >> (7 - i) & 0x1) == 1 ? '1' : '0');
        }

        return builder.toString();
    }

    private static byte[] intToBytes(int n) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (n >> 24 & 0xFF);
        bytes[1] = (byte) (n >> 16 & 0xFF);
        bytes[2] = (byte) (n >> 8 & 0xFF);
        bytes[3] = (byte) (n & 0xFF);

        return bytes;
    }
}
