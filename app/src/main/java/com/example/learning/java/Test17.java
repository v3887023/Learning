package com.example.learning.java;

import java.util.Scanner;

public class Test17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();

        int count = text.length();

        boolean lastLowCase = true;
        boolean currentLowCase;
        for (char c : text.toCharArray()) {
            currentLowCase = Character.isLowerCase(c);
            if (lastLowCase && !currentLowCase || !lastLowCase && currentLowCase) {
                count++;
            }
            lastLowCase = currentLowCase;
        }

        System.out.println(count);
    }
}
