package com.example.learning.java;

import java.util.Scanner;

public class Test16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int sum = 0;
        for (int i = 1; i < n; i++) {
            if (isPerfect(i)) {
                sum += i;
            }
        }

        System.out.println(sum);
    }

    private static boolean isPerfect(int n) {
        int sum = allFactorSquareSum(n);
        final int sqrt = (int) (Math.sqrt(sum));

        return  sum == sqrt * sqrt;
    }

    private static int allFactorSquareSum(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                sum += (i * i);
            }
        }

        return sum;
    }
}
