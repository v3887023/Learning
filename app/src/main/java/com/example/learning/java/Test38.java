package com.example.learning.java;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class Test38 {
    public static void main(String[] args) {
        Object o = new Object();

        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        Reference<Object> reference = new WeakReference<>(o, referenceQueue);

        o = null;
        System.gc();

        Utils.println(reference.get());
        Utils.println(reference.isEnqueued());
    }
}