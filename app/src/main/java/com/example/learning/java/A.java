package com.example.learning.java;

public class A {
    static {
        println("A: 静态代码块");
    }

    {
        println("A: 代码块");
    }

    public A() {
        println("A: 构造器");
    }

    private static void println(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        try {
            Class<?> aClass = Class.forName("com.example.learning.java.A");
            A a = (A) aClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    protected String s;

    protected String getS() {
        return s;
    }

    private static class B {
        static {
            println("B: 静态代码块");
        }

        public B() {
            println("B: 构造器");
        }

        private static void println(String message) {
            System.out.println(message);
        }
    }
}
