package com.example.learning.java;

public class Test18 {
    public static void main(String[] args) {
        A a = new A();
        A ab = new B();
        B b = new B();

        a(a);
        a(ab);
        a(b);
    }

    private static void a(B b) {
        b.a();
    }

    private static void a(A a) {
        a.a();
    }

    static class A {
        void a() {
            Utils.println("A");
        }
    }

    static class B extends A {
        void a() {
            Utils.println("B");
        }
    }
}
