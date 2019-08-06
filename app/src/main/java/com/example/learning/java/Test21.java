package com.example.learning.java;

import static com.example.learning.java.Utils.print;

public class Test21 {
    public static void main(String[] args) {
        int a[] = new int[]{9, 5, 4, 8, 9, 1, 7, 6, 5, 1, 100};
        int target = 4;

        final int[] ints = calSubArray(a, target);

        if (ints == null) {
            print("no");
            return;
        }

        print(target + " = ");
        final int length = ints.length;
        for (int i = 0; i < length; i++) {
            print(ints[i]);
            if (i != length - 1) {
                print(" + ");
            }
        }
    }

    private static int[] calSubArray(int[] a, int target) {
        int length = a.length;
        int sum = 0;
        int start = 0;
        int current = -1;

        for (int i = 0; i < length; ) {
            if (current != i) {
                sum += a[i];
                current = i;
            }

            if (sum < target) {
                i++;
            } else if (sum == target) {
                return subArray(a, start, i + 1);
            } else {
                sum -= a[start];
                start++;
            }
        }

        return null;
    }

    private static int[] subArray(int[] array, int start, int end) {
        int len = end - start;
        int[] a = new int[len];
        System.arraycopy(array, start, a, 0, len);

        return a;
    }
}