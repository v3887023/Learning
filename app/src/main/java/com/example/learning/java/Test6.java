package com.example.learning.java;

public class Test6 {
    private Test6(){}
    private Test6(String s){}

    public static void main(String[] args){
        Object[] objects = T.class.getConstructors();

        for (Object o : objects) {
            println(o.toString());
        }
    }

    private static void println(String message) {
        System.out.println(message);
    }

    private static class T extends Test6{
        public T(String s){}
    }
}
