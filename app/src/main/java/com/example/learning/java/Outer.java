package com.example.learning.java;

public class Outer {
    public static void main(String[] args) {
        new Outer().new Inner().a();
    }

    public void b() {
        System.out.println("123");
    }

    class Inner {
        public void a() {
            Outer.this.b();
        }

        // 非静态内部类不能有静态的成员
//        public static void b() {
//
//        }
    }
}
