package com.example.learning.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test11 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }

        long start, end;

        start = System.currentTimeMillis();
        traversalWithIndex(list);
        end = System.currentTimeMillis();
        Utils.println("run time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        traversalWithForEach(list);
        end = System.currentTimeMillis();
        Utils.println("run time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        traversalWithIterator(list);
        end = System.currentTimeMillis();
        Utils.println("run time: " + (end - start) + "ms");
    }

    private static void traversalWithIndex(List<Integer> list) {
        int size = list.size();
        int n;
        for (int i = 0; i < size; i++) {
            n = list.get(i);
        }
    }

    private static void traversalWithForEach(List<Integer> list) {
        int n;
        for (int i : list) {
            n = i;
        }
    }

    private static void traversalWithIterator(List<Integer> list) {
        int n;
        for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) {
            n = iterator.next();
        }
    }
}

