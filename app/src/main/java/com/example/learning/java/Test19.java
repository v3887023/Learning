package com.example.learning.java;

import com.example.learning.C;

public class Test19 {
    public static void main(String[] args) {
        A a = new A();
        A ab = new B();
        B b = new B();

        a(a);
        a(ab);
        a(b);
        b.b();
    }

    private static void a(B b) {
        b.a();
    }

    private static void a(A a) {
        a.a();
    }

    public static class A {
        protected void a() {
            Utils.println("A");
        }

        protected void b() {

        }

        public <T extends A> T get(int id) {
            return (T) this;
        }
    }

    static class B extends A {
        protected void a() {
            Utils.println("B");
        }
    }
}
