package com.example.learning.java;

import static com.example.learning.java.Utils.println;

public class Test23 {
    public static void main(String[] args) {
        println(nthUglyNumber(6));
    }

    private static boolean isUgly(int num) {
        if (num <= 0) {
            return false;
        }

        if (num == 1) {
            return true;
        }

        int[] a = new int[num];
        a[0] = 1;

        int m2 = 0;
        int m3 = 0;
        int m5 = 0;

        int n;
        int u2 = 2;
        int u3 = 3;
        int u5 = 5;
        int x;
        for (int i = 1; i < num; ) {
            if (u2 <= u3 && u2 <= u5) {
                n = u2;
                m2++;
                x = 2;
            } else if (u3 < u2 && u3 <= u5) {
                n = u3;
                m3++;
                x = 3;
            } else {
                n = u5;
                m5++;
                x = 5;
            }

            if (n == num) {
                return true;
            }

            if (a[i - 1] != n) {
                a[i] = n;
                i++;
            }

            if (x == 2) {
                u2 = 2 * a[m2];
            } else if (x == 3) {
                u3 = 3 * a[m3];
            } else {
                u5 = 5 * a[m5];
            }
        }

        return false;
    }

    public static int nthUglyNumber(int n) {
        int[] a = new int[n];
        a[0] = 1;

        int i2 = 0;
        int i3 = 0;
        int i5 = 0;

        int temp;
        int n2 = 2;
        int n3 = 3;
        int n5 = 5;
        int x;
        for (int i = 1; i < n; ) {
            if (n2 <= n3 && n2 <= n5) {
                temp = n2;
                i2++;
                x = 2;
            } else if (n3 < n2 && n3 <= n5) {
                temp = n3;
                i3++;
                x = 3;
            } else {
                temp = n5;
                i5++;
                x = 5;
            }

            if (a[i - 1] != temp) {
                a[i] = temp;
                i++;
            }

            if (x == 2) {
                n2 = 2 * a[i2];
            } else if (x == 3) {
                n3 = 3 * a[i3];
            } else {
                n5 = 5 * a[i5];
            }
        }

        return a[n - 1];
    }
}
