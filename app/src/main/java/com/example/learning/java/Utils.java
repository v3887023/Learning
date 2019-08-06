package com.example.learning.java;

public class Utils {

    public static void print(int integer) {
        System.out.print(integer);
    }

    public static void print(boolean b) {
        System.out.print(b);
    }

    public static void print(char c) {
        System.out.print(c);
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static void println(String tag, String message) {
        System.out.println(tag + ": " + message);
    }

    public static void println(String tag, Object object) {
        System.out.println(tag + ": " + object);
    }

    public static void println(String... strings) {
        StringBuilder builder = new StringBuilder();
        int length = strings.length;
        for (int i = 0; i < length; i++) {
            builder.append(strings[i]);
            if (i != length - 1) {
                builder.append(", ");
            }
        }
        System.out.println(builder.toString());
    }

    public static void println(Object object) {
        System.out.println(object);
    }

    public static void printLinkedList(ListNode head) {
        ListNode p = head;
        while (p != null) {
            print(p.val);
            print(" -> ");
            p = p.next;
        }
        println("NULL");
    }
}
