package com.example.learning.java;


import android.media.audiofx.BassBoost;
import android.view.View;

import static com.example.learning.java.Utils.println;

public class Test7 {

    public static void main(String[] args) throws InterruptedException {
        A a = new B();
        a.a();
    }

    private static class A {
        protected int a;

        private A() {
            println("父类构造器");
        }

        private void a() {
            println("父类方法 a()");
        }
    }

    private static class B extends A {
        private int a;

        public B() {
            println("子类构造器");
            a = 1;
        }

        private void a() {
            println("子类方法 a()");
        }

    }

    interface C extends View.OnClickListener, BassBoost.OnParameterChangeListener {
         static final int a = 1;
         public abstract void a();
    }

}
